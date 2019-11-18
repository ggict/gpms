CREATE OR REPLACE FUNCTION gpms.prc_repair_target_range_string(p_user_no numeric, p_trget_slctn_no numeric, p_anals_unit_code text, p_mode text, OUT o_proccode text, OUT o_procmsg text)
 RETURNS record
 LANGUAGE plpgsql
AS $function$

DECLARE
    ERR_CONTEXT TEXT;   /* plpgsql exception handling message */
    V_LEN NUMERIC;
    V_LEN2 NUMERIC;
    RCNT INT;

BEGIN
    V_LEN := 0;
    V_LEN2 := 0;
    RCNT := 0;

    /** 분석단위코드(ANUN)[200m : ANUN0001, 1km : ANUN0002] */
    IF P_ANALS_UNIT_CODE = 'ANUN0001' THEN
        V_LEN := 200;
    ELSIF P_ANALS_UNIT_CODE = 'ANUN0002' THEN
        V_LEN := 1000;
    END IF;

    IF P_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 분석 단위 길이 =%', V_LEN;
    END IF;

    DELETE FROM TN_RPAIR_TRGET_GROUP
    WHERE TRGET_SLCTN_NO = P_TRGET_SLCTN_NO;

    LOOP

        WITH
            /* 보수공법별 단가 */
            RPMD_CODES AS (
                SELECT
                    A.RPAIR_MTHD_CODE                    RPAIR_MTHD_CODE                  /* 보수_공법_코드(TC_RPAIR_MTHD.RPAIR_MTHD_CODE)[Do Nothing(RPIR0001), 노화방지공법(RPIR0002), 균열 Sealing(RPIR0003)...] */
                    , A.MSRC_CL_CODE                     MSRC_CL_CODE                     /* 공법 분류(RPMT)[75mm 절삭 덧씌우기(RPMT0007), 50mm 절삭 덧씌우기(RPMT0008), 50mm 덧씌우기(RPMT0009)...] */
                    , (SELECT Z.CODE_NM FROM TC_CODE Z WHERE Z.CODE_VAL = A.MSRC_CL_CODE AND Z.CL_CODE = 'RPMT') MSRC_CL_NM  /* 공법 분류(RPMT)[75mm 절삭 덧씌우기(RPMT0007), 50mm 절삭 덧씌우기(RPMT0008), 50mm 덧씌우기(RPMT0009)...] */
                    , A.MSRC_NM                          MSRC_NM                          /* 공법_명 */
                    , A.MSRC_CT                          MSRC_CT                          /* 공법_비용 */
                    , A.MSRC_LCLAS_CODE                  MSRC_LCLAS_CODE                  /* 공법 대분류(RMLC)[덧씌우기(RMLC0001), 예방적 유지보수(RMLC0002), Do Nothing(RMLC0003)] */
                    , A.MSR_DM_CODE                      MSR_DM_CODE                      /* 공법선정비율 결정방식(MSRD)[Worst-First(MSRD0001), Best-First(MSRD0002), Best-First2(MSRD0003)] */
                    , COALESCE((SELECT Z.ATRB_VAL FROM TC_CODE Z WHERE Z.CODE_VAL = A.MSR_DM_CODE AND Z.CL_CODE = 'MSRD'), '0') RPAIR_MTHD_CLASS  /* 공법선정비율 결정방식 속성값(MSRD)[Worst-First(1), Best-First(2), Best-First2(3)] */
                    , B.CNTRWK_AMOUNT                    CNTRWK_AMOUNT                    /* 공사_금액 */
                    , B.PAINT_AMOUNT                     PAINT_AMOUNT                     /* 차선_도색_금액 */
                    , B.ETC_AMOUNT                       ETC_AMOUNT                       /* 기타_금액 */
                    , B.TOT_AMOUNT                       RPAIR_FEE                        /* 총_금액 */
                FROM TC_RPAIR_MTHD  A                                                     /*** 보수공법코드 테이블 ***/
                INNER JOIN TN_RPAIR_MTHD_UNTPC  B                                         /*** 보수_공법_단가 테이블 ***/
                    ON B.RPAIR_MTHD_CODE                 = A.RPAIR_MTHD_CODE              /* 보수_공법_코드(TC_RPAIR_MTHD.RPAIR_MTHD_CODE)[Do Nothing(RPIR0001), 노화방지공법(RPIR0002), 균열 Sealing(RPIR0003)...] */
                    AND B.USE_AT                         = 'Y'                            /* 사용_여부 */
                    AND B.DELETE_AT                      = 'N'                            /* 삭제_여부 */
                WHERE
                    1 = 1
                    AND A.DELETE_AT                      = 'N'                            /* 삭제_여부 */
                    AND A.USE_AT                         = 'Y'                            /* 사용_여부 */
            )

        INSERT /* tnRpairTrgetGroup.insert leehb */
            INTO TN_RPAIR_TRGET_GROUP  (                /*** 보수_대상_항목_그룹 테이블 ***/
                GROUP_ITEM_NO                          /* 그룹_항목_번호             */
                , TRGET_SLCTN_NO                       /* 대상_선정_번호             */
                , SLCTN_STEP                           /* 선정_단계                  */
                , ITEM_SLCTN_STTUS                     /* 항목_선정_상태             */
                , ROUTE_CODE                           /* 노선_코드                  */
                , DIRECT_CODE                          /* 행선_코드                  */
                , TRACK                                /* 차로                       */
                , STRTPT                               /* 시점                       */
                , ENDPT                                /* 종점                       */
                , CELL_TYPE                            /* 셀_타입                    */
                , DEPT_CODE                            /* 부서코드                   */
                , SRVY_MT                              /* 조사_월                    */
                , SRVY_YEAR                            /* 조사_년도                  */
                , GPCI                                 /* GPCI                       */
                , PC_GRAD                              /* 포장상태등급               */
                , CALC_YEAR                            /* 산정_년도                  */
                , CALC_MT                              /* 산정_월                    */
                , CALC_GPCI                            /* 산정_GPCI                  */
                , CALC_PC_GRAD                         /* 산정_포장상태등급          */
                , DMG_VAL                              /* 파손도_값                  */
                , CNTRWK_YEAR                          /* 공사_년도                  */
                , TRNSPORT_QY                          /* 교통_량                    */
                , LEN                                  /* 연장                       */
                , AR                                   /* 면적                       */
                , RPAIR_MTHD_CODE                      /* 보수_공법_코드             */
                , MSR_DM_CODE                          /* 공법선정비율_결정방식_코드 */
                , THRHLD                               /* 임계값                     */
                , AMOUNT_CALC                          /* 금액_산정                  */
                , BUDGET_ASIGN                         /* 예산_배정                  */
                , FIXING_AT                            /* 고정_여부                  */
                , BUDGET_CECK                          /* 예산_체크                  */
                , ACCMLT_CALC                          /* 누적_산정                  */
                , SLCTN_AT                             /* 선정_여부                  */
                , SLCTN_DT                             /* 선정_일시                  */
                , TMPR_SLCTN_AT                        /* 임시_선정_여부             */
                , NODE_CO                              /* 노드 개수                  */
                , DELETE_AT                            /* 삭제_여부                  */
                , USE_AT                               /* 사용_여부                  */
                , CRTR_NO                              /* 생성자_번호                */
                , CREAT_DT                             /* 생성_일시                  */
                , UPDUSR_NO                            /* 수정자_번호                */
                , UPDT_DT                              /* 수정_일시                  */
                , VMTC_GRAD                            /* 교통량등급 */
                , ROAD_GRAD                            /* 도로등급 */
                , ADM_CODE                             /* 행정구역코드 */
                , CELL_IDS
                , MNG_RD_CD                            /* 관리_도로_코드 */
            ) (
                SELECT
                    NEXTVAL('SEQ_TN_RPAIR_TRGET_GROUP') GROUP_ITEM_NO  /* 그룹_항목_번호             */
                    , A.TRGET_SLCTN_NO                       /* 대상_선정_번호             */
                    , A.SLCTN_STEP                           /* 선정_단계                  */
                    , A.ITEM_SLCTN_STTUS                     /* 항목_선정_상태             */
                    , A.ROUTE_CODE                           /* 노선_코드                  */
                    , A.DIRECT_CODE                          /* 행선_코드                  */
                    , A.TRACK                                /* 차로                       */
                    , A.STRTPT                               /* 시점                       */
                    , A.STRTPT + V_LEN   ENDPT               /* 종점                       */
                    , A.CELL_TYPE                            /* 셀_타입                    */
                    , A.DEPT_CODE                            /* 부서코드                   */
                    , A.SRVY_MT                              /* 조사_월                    */
                    , A.SRVY_YEAR                            /* 조사_년도                  */
                    , A.AVER             GPCI                /* GPCI                       */
                    , FN_GET_IDX_GRADE('GPCI', A.SRVY_YEAR::TEXT, A.AVER::NUMERIC)::NUMERIC PC_GRAD  /* 포장상태등급               */
                    , TO_CHAR(NOW(), 'YYYY') CALC_YEAR       /* 산정_년도                  */
                    , TO_CHAR(NOW(), 'MM') CALC_MT           /* 산정_월                    */
                    , A.AVER             CALC_GPCI           /* 산정_GPCI                  */
                    , FN_GET_IDX_GRADE('GPCI', A.SRVY_YEAR::TEXT, A.AVER::NUMERIC)::NUMERIC CALC_PC_GRAD  /* 산정_포장상태등급          */
                    , NULL               DMG_VAL             /* 파손도_값                  */
                    , FN_GET_CNTRWK_YEAR(A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK::NUMERIC, A.STRTPT, A.ENDPT ) CNTRWK_YEAR  /* 공사_년도                  */
                    , FN_REPAIR_TARGET_RANGE_VALUE('TQ', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)::NUMERIC TRNSPORT_QY         /* 교통_량                    */
                    , FN_REPAIR_TARGET_RANGE_VALUE('LN', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)::NUMERIC LEN         /* 연장                    */
                    , FN_REPAIR_TARGET_RANGE_VALUE('AR', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)::NUMERIC AR         /* 면적                    */
                    , FN_REPAIR_TARGET_RANGE_VALUE('RM', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN) RPAIR_MTHD_CODE         /* 보수_공법_코드                    */
                    , FN_REPAIR_TARGET_RANGE_VALUE('MD', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN) MSR_DM_CODE         /* 공법선정비율_결정방식_코드                    */
                    , FN_REPAIR_TARGET_RANGE_VALUE('TH', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)::NUMERIC THRHLD              /* 임계값                    */
                    , (
                        SELECT
                            AA.RPAIR_FEE
                        FROM RPMD_CODES AA
                        WHERE
                            AA.RPAIR_MTHD_CODE    = FN_REPAIR_TARGET_RANGE_VALUE('RM', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)
                    )::NUMERIC * FN_REPAIR_TARGET_RANGE_VALUE('AR', A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT, A.STRTPT + V_LEN)::NUMERIC AMOUNT_CALC                          /* 금액_산정                  */
                    , 0                  BUDGET_ASIGN        /* 예산_배정                  */
                    , 'N'                FIXING_AT           /* 고정_여부                  */
                    , NULL               BUDGET_CECK         /* 예산_체크                  */
                    , NULL               ACCMLT_CALC         /* 누적_산정                  */
                    , 'Y'                SLCTN_AT            /* 선정_여부                  */
                    , NOW()              SLCTN_DT            /* 선정_일시                  */
                    , 'N'                TMPR_SLCTN_AT       /* 임시_선정_여부             */
                    , V_LEN/10               NODE_CO             /* 노드 개수                  */
                    , 'N'                                    /* 삭제_여부                  */
                    , 'Y'                                    /* 사용_여부                  */
                    , P_USER_NO::NUMERIC CRTR_NO             /* 생성자_번호                */
                    , NOW() CREAT_DT                         /* 생성_일시                  */
                    , P_USER_NO::NUMERIC UPDUSR_NO           /* 수정자_번호                */
                    , NOW()              UPDT_DT             /* 수정_일시                  */
                    , A.VMTC_GRAD                            /* 교통량등급 */
                    , A.ROAD_GRAD                            /* 도로등급 */
                    , A.ADM_CODE                             /* 행정구역코드 */
                    , NULL               CELL_IDS
                    , A.MNG_RD_CD        MNG_RD_CD           /* 관리_도로_코드 */
                FROM (
                    SELECT
                        A.*
                    FROM (
                        SELECT
                            A.*
--                            , B.MNG_RD_CD
                            , NULL MNG_RD_CD
                            , AVG(A.CALC_GPCI) OVER(PARTITION BY A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.DEPT_CODE, A.ROAD_GRAD, A.ADM_CODE/*, A.MNG_RD_CD*/ ORDER BY A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT ROWS BETWEEN CURRENT ROW AND (V_LEN/10 - 1) FOLLOWING) AVER
                        FROM TN_RPAIR_TRGET A
                        INNER JOIN CELL_10 B
                            ON B.ROUTE_CODE           = A.ROUTE_CODE   /* 보수_대상_항목.노선_코드 */
                            AND B.DIRECT_CODE         = A.DIRECT_CODE     /* 보수_대상_항목.행선_코드 */
                            AND B.TRACK               = A.TRACK     /* 보수_대상_항목.차로 */
                            AND B.STRTPT              = A.STRTPT
                            AND B.ENDPT               = A.ENDPT
                        WHERE
                            A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO
                    ) A
                    WHERE
                        EXISTS (
                            SELECT 1
                            FROM TN_RPAIR_TRGET AA
                            WHERE
                                1 = 1
                                AND AA.TRGET_SLCTN_NO = A.TRGET_SLCTN_NO
                                AND AA.ROUTE_CODE     = A.ROUTE_CODE   /* 보수_대상_항목.노선_코드 */
                                AND AA.DIRECT_CODE    = A.DIRECT_CODE     /* 보수_대상_항목.행선_코드 */
                                AND AA.TRACK          = A.TRACK     /* 보수_대상_항목.차로 */
                                AND AA.STRTPT         BETWEEN A.STRTPT AND A.STRTPT + V_LEN    /* 보수_대상_항목.시점 */
                                AND AA.ENDPT          BETWEEN A.STRTPT AND A.STRTPT + V_LEN
                                AND NOT EXISTS (
                                    SELECT 1
                                    FROM TN_RPAIR_TRGET_GROUP BB
                                    WHERE
                                        1 = 1
                                        AND BB.TRGET_SLCTN_NO = A.TRGET_SLCTN_NO
                                        AND BB.ROUTE_CODE     = A.ROUTE_CODE   /* 보수_대상_항목.노선_코드 */
                                        AND BB.DIRECT_CODE    = A.DIRECT_CODE     /* 보수_대상_항목.행선_코드 */
                                        AND BB.TRACK          = A.TRACK     /* 보수_대상_항목.차로 */
                                        AND AA.STRTPT         BETWEEN BB.STRTPT AND BB.ENDPT   /* 보수_대상_항목.시점 */
                                        AND AA.ENDPT          BETWEEN BB.STRTPT AND BB.ENDPT   /* 보수_대상_항목.시점 */
                                )
                            HAVING
                                COUNT(*) = V_LEN/10
                        )
                ) A
                ORDER BY A.AVER, A.TRGET_SLCTN_NO, A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK, A.STRTPT
                LIMIT 1
            );

        GET DIAGNOSTICS RCNT = ROW_COUNT;
        RAISE NOTICE '%', RCNT;

        EXIT WHEN V_LEN2 = 10;

        V_LEN2 := V_LEN2 + 1;
        IF P_MODE = 'DEBUG' THEN
            RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_STRING) 순번: %', V_LEN2;
        END IF;

    END LOOP;

    O_PROCCODE :='true';
    O_PROCMSG := '보수대상선정 집계 연속 구간 GPIC 재개산 및 공법 결정 완료';

    UPDATE TN_RPAIR_TRGET_SLCTN SET
        SLCTN_STTUS = 'RTSS0004' /* 보수_대상_선정.선정_상태 보수대상연속구간 조회(동일섹션 연속구간 선정) */
        , UPDUSR_NO = P_USER_NO::NUMERIC /* 보수_대상_선정.수정자_번호 */
        , UPDT_DT = NOW() /* 보수_대상_선정.수정_일시 */
    WHERE
        1 = 1
        AND TRGET_SLCTN_NO = P_TRGET_SLCTN_NO::NUMERIC /* 보수_대상_선정.대상_선정_번호 */
    ;

EXCEPTION
    WHEN OTHERS
    THEN
        O_PROCMSG := SUBSTR(SQLERRM,1,1000);
        O_PROCCODE :='false';
        IF P_MODE = 'DEBUG' THEN
            RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 오류 발생: TRGET_SLCTN_NO =%  msg=%', P_TRGET_SLCTN_NO, O_PROCMSG;
        END IF;

        -- plpgsql exception handling..
        GET STACKED DIAGNOSTICS ERR_CONTEXT = PG_EXCEPTION_CONTEXT;
        RAISE INFO 'Error Name:%',SQLERRM;
        RAISE INFO 'Error State:%', SQLSTATE;
        RAISE INFO 'Error Context:%', ERR_CONTEXT;
    RAISE;
END;

$function$
;
