CREATE OR REPLACE FUNCTION PRC_REPAIR_TARGET_RANGE_SELECT(P_USER_NO NUMERIC, P_TRGET_SLCTN_NO NUMERIC, P_ROUTE_CODE TEXT, P_START_END_CODE TEXT, P_MODE TEXT, OUT O_PROCCODE TEXT, OUT O_PROCMSG TEXT)
RETURNS RECORD
LANGUAGE plpgsql
AS $function$

DECLARE
    ERR_CONTEXT TEXT;  /* plpgsql exception handling message */

BEGIN

    O_PROCCODE :='false';

    IF P_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 메시지: %', O_PROCMSG;
    END IF;

    DELETE FROM TN_RPAIR_TRGET
    WHERE
       TRGET_SLCTN_NO = P_TRGET_SLCTN_NO
       AND ROUTE_CODE = P_ROUTE_CODE;

    O_PROCMSG := CONCAT('보수대상선정 집계 초기화(삭제)완료 : ', P_ROUTE_CODE);



    IF p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 메시지: %', O_PROCMSG;
    END IF;

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

    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO                                                                 /* 대상_항목_번호 */
        , TRGET_SLCTN_NO                                                              /* 대상_선정_번호 */
        , SLCTN_STEP                                                                  /* 선정_단계 */
        , ITEM_SLCTN_STTUS                                                            /* 항목_선정_상태 */
        , ROUTE_CODE                                                                  /* 노선_코드 */
        , DIRECT_CODE                                                                 /* 행선_코드 */
        , TRACK                                                                       /* 차로 */
        , STRTPT                                                                      /* 시점 */
        , ENDPT                                                                       /* 종점 */
        , CELL_TYPE                                                                   /* 섹션_구분 */
        , SRVY_MT                                                                     /* 조사_월 */
        , SRVY_YEAR                                                                   /* 조사_년도 */
        , GPCI                                                                        /* GPCI */
        , PC_GRAD                                                                     /* 포장상태등급 */
        , CALC_YEAR                                                                   /* 산정_년도 */
        , CALC_MT                                                                     /* 산정_월 */
        , CALC_GPCI                                                                   /* 산정_GPCI */
        , CALC_PC_GRAD                                                                /* 산정_포장상태등급 */
        , CNTRWK_YEAR                                                                 /* 공사_년도 */
        , TRNSPORT_QY                                                                 /* 교통량 */
        , LEN                                                                         /* 연장 */
        , AR                                                                          /* 면적 */
        , RPAIR_MTHD_CODE                                                             /* 보수_공법_코드 */
        , AMOUNT_CALC                                                                 /* 금액_산정 */
        , BUDGET_ASIGN                                                                /* 예산_배정 */
        , FIXING_AT                                                                   /* 고정_여부 */
        , BUDGET_CECK                                                                 /* 예산_체크 */
        , ACCMLT_CALC                                                                 /* 누적_산정 */
        , SLCTN_AT                                                                    /* 선정_여부 */
        , SLCTN_DT                                                                    /* 선정_일시 */
        , DELETE_AT                                                                   /* 삭제_여부 */
        , USE_AT                                                                      /* 사용_여부 */
        , CRTR_NO                                                                     /* 생성자_번호 */
        , CREAT_DT                                                                    /* 생성_일시 */
        , UPDUSR_NO                                                                   /* 수정자_번호 */
        , UPDT_DT                                                                     /* 수정_일시 */
        , DMG_VAL                                                                     /* 파손도_값 */
        , DEPT_CODE                                                                   /* 부서코드 */
        , TMPR_SLCTN_AT                                                               /* 임시_선정_여부 */
        , MSR_DM_CODE                                                                 /* 공법선정비율_결정방식_코드 */
        , THRHLD                                                                      /* 임계값 */
        , VMTC_GRAD                                                                   /* 교통량등급 */
        , ROAD_GRAD                                                                   /* 도로등급 */
        , ADM_CODE                                                                    /* 행정구역코드 */
        , SLCTN_ORDR                                                                  /* 선정_순서 */
    ) (
        SELECT
            NEXTVAL('SEQ_TN_RPAIR_TRGET')            TRGET_ITEM_NO                    /* 대상_항목_번호 */
            , P_TRGET_SLCTN_NO                       TRGET_SLCTN_NO                   /* 대상_선정_번호 */
            , NULL                                   SLCTN_STEP                       /* 선정_단계 */
            , NULL                                   ITEM_SLCTN_STTUS                 /* 항목_선정_상태 */
            , A.ROUTE_CODE                           ROUTE_CODE                       /* 노선_코드 */
            , A.DIRECT_CODE                          DIRECT_CODE                      /* 행선_코드 */
            , A.TRACK                                TRACK                            /* 차로 */
            , A.STRTPT                               STRTPT                           /* 시점 */
            , A.ENDPT                                ENDPT                            /* 종점 */
            , A.CELL_TYPE                            CELL_TYPE                        /* 섹션_구분 */
            , A.SRVY_MT                              SRVY_MT                          /* 조사_월 */
            , A.SRVY_YEAR                            SRVY_YEAR                        /* 조사_년도 */
            , A.GPCI                                 GPCI                             /* GPCI */
            , A.PC_GRAD                              PC_GRAD                          /* 포장상태등급 */
            , A.CALC_YEAR                            CALC_YEAR                        /* 산정_년도 */
            , A.CALC_MT                              CALC_MT                          /* 산정_월 */
            , A.CALC_GPCI                            CALC_GPCI                        /* 산정_GPCI */
            , A.CALC_PC_GRAD                         CALC_PC_GRAD                     /* 산정_포장상태등급 */
            , A.CNTRWK_YEAR                          CNTRWK_YEAR                      /* 공사_년도 */
            , NULL                                   TRNSPORT_QY                      /* 교통량 */
            , A.LEN                                  LEN                              /* 연장 */
            , A.AR                                   AR                               /* 면적 */
            , A.RPAIR_MTHD                           RPAIR_MTHD_CODE                  /* 보수_공법_코드 */
            , A.AMOUNT_CALC                          AMOUNT_CALC                      /* 금액_산정 */
            , NULL                                   BUDGET_ASIGN                     /* 예산_배정 */
            , 'N'                                    FIXING_AT                        /* 고정_여부 */
            , NULL                                   BUDGET_CECK                      /* 예산_체크 */
            , NULL                                   ACCMLT_CALC                      /* 누적_산정 */
            , NULL                                   SLCTN_AT                         /* 선정_여부 */
            , NULL                                   SLCTN_DT                         /* 선정_일시 */
            , 'N'                                    DELETE_AT                        /* 삭제_여부 */
            , 'Y'                                    USE_AT                           /* 사용_여부 */
            , P_USER_NO                              CRTR_NO                          /* 생성자_번호 */
            , NOW()                                  CREAT_DT                         /* 생성_일시 */
            , P_USER_NO                              UPDUSR_NO                        /* 수정자_번호 */
            , NOW()                                  UPDT_DT                          /* 수정_일시 */
            , A.DMG_VAL                              DMG_VAL                          /* 파손도_값 */
            , A.DEPT_CODE                            DEPT_CODE                        /* 부서코드 */
            , 'Y'                                    TMPR_SLCTN_AT                    /* 임시_선정_여부 TMPR_SLCTN_AT */
            , A.MSR_DM_CODE                          MSR_DM_CODE                      /* 공법선정비율_결정방식_코드 */
            , A.THRHLD                               THRHLD                           /* 임계값 */
            , A.VMTC_GRAD                            VMTC_GRAD                        /* 교통량등급 */
            , A.ROAD_GRAD                            ROAD_GRAD                        /* 도로등급 */
            , A.ADM_CODE                             ADM_CODE                         /* 행정구역코드 */
            , NULL                                   SLCTN_ORDR                       /* 선정_순서 */
        FROM (
            SELECT
                B.RPAIR_MTHD_CLASS                   RPAIR_MTHD_CLASS                 /* 공법선정비율 결정방식 속성값(MSRD)[Worst-First(1), Best-First(2), Best-First2(3)] */
                , B.MSR_DM_CODE                      MSR_DM_CODE                      /* 공법선정비율 결정방식(MSRD)[Worst-First(MSRD0001), Best-First(MSRD0002), Best-First2(MSRD0003)] */
                , B.RPAIR_FEE                        RPAIR_FEE                        /* 총_금액 */
                , (B.RPAIR_FEE * A.AR)               AMOUNT_CALC                      /* (총_금액 * 면적)금액_산정 */
                , A.THRHLD - A.CALC_GPCI             DMG_VAL                          /* (임계값 - 산정_GPCI) 파손도_값 */
                , A.*
            FROM (
                SELECT
                    FN_GET_CNTRWK_YEAR(A.ROUTE_CODE, A.DIRECT_CODE, A.TRACK::NUMERIC, A.STRTPT, A.ENDPT) CNTRWK_YEAR  /* 공사년도(최근공사년도) */
                    , A.ROAD_L                         LEN                              /* 연장 */
                    , A.ROAD_A                         AR                               /* 면적 */
                    , FN_GET_RPAIR_MTHD(A.CELL_TYPE, A.VMTC_GRAD, A.CALC_GPCI, A.LC_IDX, A.RD_IDX, A.AC_IDX, 'GPCI') RPAIR_MTHD  /* 보수_공법_코드(TC_RPAIR_MTHD.RPAIR_MTHD_CODE)[Do Nothing(RPIR0001), 노화방지공법(RPIR0002), 균열 Sealing(RPIR0003)...] */
                    , FN_GET_GPCI_LIMITVAL(A.CELL_TYPE, A.VMTC_GRAD, A.CALC_GPCI, A.LC_IDX, A.RD_IDX, A.AC_IDX, 'GPCI') THRHLD  /* 임계값 */
                    , A.*
                FROM (
                    SELECT
                        B.SRVY_YEAR                  SRVY_YEAR                        /* 조사_년도 */
                        , B.SRVY_MT                  SRVY_MT                          /* 조사_월 */
                        , COALESCE(B.GPCI, 999)      GPCI                             /* GPCI */
                        , COALESCE(B.PC_GRAD, 99)    PC_GRAD                          /* 포장상태등급 */
                        , A.ROUTE_CODE               ROUTE_CODE                       /* 노선_코드 */
                        , A.DIRECT_CODE              DIRECT_CODE                      /* 방향 */
                        , A.TRACK                    TRACK                            /* 차로 */
                        , A.STRTPT                   STRTPT                           /* 시점 */
                        , A.ENDPT                    ENDPT                            /* 종점 */
                        , FLOOR(A.DEPT_CODE)         DEPT_CODE                        /* 부서_코드 */
                        , A.CELL_TYPE                CELL_TYPE                        /* 섹션_구분 */
                        , D.CODE_NM                  CELL_TYPE_NM                     /* 섹션_구분_명 */
                        , D.ATRB_VAL                 CELL_TYPE_ORDER                  /* 공법선정비율 결정방식 속성값(MSRD)[Worst-First(1), Best-First(2), Best-First2(3)] */
                        , E.ATRB_VAL                 VNTC_ORDER                       /* 공법선정비율 결정방식 속성값(MSRD)[Worst-First(1), Best-First(2), Best-First2(3)] */
                        , A.VMTC_GRAD                VMTC_GRAD                        /* 교통량등급 */
                        , A.ROAD_GRAD                ROAD_GRAD                        /* 도로등급 */
                        , A.ADM_CODE || '00000'      ADM_CODE                         /* 행정구역코드 */
                        , A.ROAD_L                   ROAD_L                           /* 도로 길이 */
                        , A.ROAD_A                   ROAD_A                           /* 도로 면적 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_PREDCT_VAL(B.IRI_VAL, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0008')
                            ELSE
                                FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_IRI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0008')
                            END                      IRI_VAL                          /* 종단평탄성_값, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , B.CR_VAL                   CR_VAL                           /* 표면손상값 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_PREDCT_VAL(B.RD_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_LOW */
                                + FN_GET_PREDCT_VAL(B.RD_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_MED */
                                + FN_GET_PREDCT_VAL(B.RD_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_HI */
                            ELSE
                                FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_RD, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_LOW */
                                + FN_GET_PREDCT_VAL(0, MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_MED */
                                + FN_GET_PREDCT_VAL(0, MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_HI */
                            END                      RD_VAL                           /* 소성변형_값, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , B.BLOCK_CR                 BLOCK_CR                         /* 블럭_균열 */
                        , B.TRTS_BAC_CR              TRTS_BAC_CR                      /* 거북등_균열 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_PATCH_IDX(B.PTCHG_CR)
                            ELSE
                                FN_GET_PATCH_IDX(COALESCE(F.INITVAL_PTCHG_CR, 0))
                            END                      PTCHG_IDX                        /* 패칭_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_AC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(B.AC_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.AC_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.AC_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_HI */
                                )
                            ELSE
                                FN_GET_AC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_AC_LOW, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_AC_MED, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_AC_HI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0001'), 0)  /* 거북등균열_HI */
                                )
                            END                      AC_IDX                           /* 거북등균열_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_BC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(B.BLOCK_CR_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.BLOCK_CR_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.BLOCK_CR_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_HI */
                                )
                            ELSE
                                FN_GET_BC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_BC_LOW, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_BC_MED, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_BC_HI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0002'), 0)  /* 블럭균열_HI */
                                )
                            END                      BC_IDX                           /* 블럭균열_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_LC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(B.LC_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.LC_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.LC_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_HI */
                                )
                            ELSE
                                FN_GET_LC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_LC_LOW, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_LC_MED, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_LC_HI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0003'), 0)  /* 종방향균열_HI */
                                )
                            END                      LC_IDX                           /* 종방향균열_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_TC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(B.TC_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.TC_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(B.TC_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_HI */
                                )
                            ELSE
                                FN_GET_TC_IDX(
                                    COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_TC_LOW, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_LOW */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_TC_MED, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_MED */
                                    , COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_TC_HI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004'), 0)  /* 횡방향균열_HI */
                                )
                            END                      TC_IDX                           /* 횡방향균열_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_RUT_IDX(
                                    FN_GET_PREDCT_VAL(B.RD_LOW, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_LOW */
                                    , FN_GET_PREDCT_VAL(B.RD_MED, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_MED */
                                    , FN_GET_PREDCT_VAL(B.RD_HI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0007')  /* 소성변형_HI */
                                )
                            ELSE
                                FN_GET_RUT_IDX(
                                    FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_RD, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004')  /* 소성변형_LOW */
                                    , FN_GET_PREDCT_VAL(0, MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004')  /* 소성변형_MED */
                                    , FN_GET_PREDCT_VAL(0, MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0004')  /* 소성변형_HI */
                                )
                            END                      RD_IDX                           /* 소성변형지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_POT_IDX(B.POTHOLE_CR)
                            ELSE
                                FN_GET_POT_IDX(COALESCE(F.INITVAL_POTHOLE_CR, 0))
                            END                      POTHOLE_IDX                      /* 포트홀_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_RCI(COALESCE(FN_GET_PREDCT_VAL(B.IRI_VAL, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0008'), 0))
                            ELSE
                                FN_GET_RCI(COALESCE(FN_GET_PREDCT_VAL(COALESCE(F.INITVAL_IRI, 0), MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0008'), 0))
                            END                      RCI                              /* 표면_조도_지수, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , NULL                       TRNSPORT_QY                      /* 교통_량 */
                        , TO_CHAR(NOW(), 'MM')       CALC_MT                          /* 산정_월 */
                        , TO_CHAR(NOW(), 'YYYY')     CALC_YEAR                        /* 산정_년도 */
                        , CASE WHEN C.RPAIR_END_DE IS NULL THEN
                                FN_GET_PREDCT_GPCI(B.GPCI, MONTHS_BETWEEN(B.SRVY_YEAR, B.SRVY_MT)::NUMERIC, A.CELL_TYPE, 'PRDT0009')
                            ELSE
                                FN_GET_PREDCT_GPCI(10, MONTHS_BETWEEN(SUBSTR(C.RPAIR_END_DE, 1, 4), SUBSTR(C.RPAIR_END_DE, 5, 6))::NUMERIC, A.CELL_TYPE, 'PRDT0009')
                            END                      CALC_GPCI                        /* GPCI, 조사일 이후 보수된 데이터 존재하면 수치 초기화 */
                        , B.PC_GRAD                  CALC_PC_GRAD                     /* 포장상태등급 */
                    FROM CELL_10 A                                                    /*** CELL_10 테이블 ***/
                    INNER JOIN TN_MUMM_SCTN_SRVY_DTA B                                /*** 최소_구간_조사_자료 테이블 ***/
                        ON 1 = 1
                        AND B.ROUTE_CODE             = A.ROUTE_CODE                   /* 노선코드 */
                        AND B.DIRECT_CODE            = A.DIRECT_CODE                  /* 방향 */
                        AND B.TRACK                  = A.TRACK                        /* 차로 */
                        AND B.STRTPT                 = A.STRTPT                       /* 시점 */
                        AND B.ENDPT                  = A.ENDPT                        /* 종점 */
                        AND B.DELETE_AT              = 'N'                            /* 삭제_여부 */
                        AND B.USE_AT                 = 'Y'                            /* 사용_여부 */
                        AND B.LAST_DTA_AT            = 'Y'                            /* 최종_자료_여부 */
                        AND B.ROUTE_CODE             = P_ROUTE_CODE                   /* 노선코드(메모리 문제로 노선코드별로 하나 걸어서 진행) */
                    LEFT OUTER JOIN (
                        SELECT
                            AA.ROUTE_CODE            ROUTE_CODE                       /* 노선코드 */
                            , AA.DIRECT_CODE         DIRECT_CODE                      /* 방향 */
                            , AA.TRACK               TRACK                            /* 차로 */
                            , AA.STRTPT              STRTPT                           /* 시점 */
                            , AA.ENDPT               ENDPT                            /* 종점 */
                            , SUBSTR(MAX(DD.RPAIR_END_DE), 0, 6) RPAIR_END_DE         /* 보수_종료_일자(YYYYMM) */
                        FROM TN_MUMM_SCTN_SRVY_DTA AA                                 /*** 최소_구간_조사_자료 테이블 ***/
                        INNER JOIN CELL_10 BB                                         /*** CELL_10 테이블 ***/
                            ON BB.ROUTE_CODE         = AA.ROUTE_CODE                  /* 노선코드 */
                            AND BB.DIRECT_CODE       = AA.DIRECT_CODE                 /* 방향 */
                            AND BB.TRACK             = AA.TRACK                       /* 차로 */
                            AND BB.STRTPT            = AA.STRTPT                      /* 시점 */
                            AND BB.ENDPT             = AA.ENDPT                       /* 종점 */
                        INNER JOIN TN_CNTRWK_CELL_INFO CC                             /*** 공사_셀_정보 테이블 ***/
                            ON CC.PAV_CELL_ID        = BB.CELL_ID                     /* 포장_셀_ID */
                        INNER JOIN TN_CNTRWK_DTL DD                                   /*** 공사상세정보 테이블 ***/
                            ON DD.DETAIL_CNTRWK_ID   = CC.DETAIL_CNTRWK_ID            /* 세부_공사_ID */
                            AND DD.USE_AT            = 'Y'                            /* 사용_여부 */
                            AND DD.DELETE_AT         = 'N'                            /* 삭제_여부 */
                            AND SUBSTR(DD.RPAIR_END_DE, 0, 6) > CONCAT(AA.SRVY_YEAR, AA.SRVY_MT)  /* 조사일 이후 보수된 데이터(수치 초기화 적용대상) */
                        WHERE
                            1 = 1
                            AND AA.DELETE_AT         = 'N'                            /* 삭제_여부 */
                            AND AA.USE_AT            = 'Y'                            /* 사용_여부 */
                            AND AA.LAST_DTA_AT       = 'Y'                            /* 최종_자료_여부 */
                            AND AA.ROUTE_CODE        = P_ROUTE_CODE                   /* 노선코드(메모리 문제로 노선코드별로 하나 걸어서 진행) */
                        GROUP BY
                            AA.ROUTE_CODE                                             /* 노선코드 */
                            , AA.DIRECT_CODE                                          /* 방향 */
                            , AA.TRACK                                                /* 차로 */
                            , AA.STRTPT                                               /* 시점 */
                            , AA.ENDPT                                                /* 종점 */
                    )  C                                                              /*** 조사일 이후 보수된 데이터 ***/
                        ON 1 = 1
                        AND C.ROUTE_CODE             = B.ROUTE_CODE                   /* 노선코드 */
                        AND C.DIRECT_CODE            = B.DIRECT_CODE                  /* 방향 */
                        AND C.TRACK                  = B.TRACK                        /* 차로 */
                        AND C.STRTPT                 = B.STRTPT                       /* 시점 */
                        AND C.ENDPT                  = B.ENDPT                        /* 종점 */
                    INNER JOIN TC_CODE D                                              /*** 공통코드 ***/
                        ON 1 = 1
                        AND D.CODE_VAL = A.CELL_TYPE                                  /* 코드_값 */
                        AND D.CL_CODE = 'CELT'                                        /* 분류_코드 - 셀속성 구분(CELT)[일반차로구간(CELT0001), 교차로(CELT0002), 교량(CELT0003)...] */
                        AND D.USE_AT = 'Y'                                            /* 사용_여부 */
                        AND D.DELETE_AT = 'N'                                         /* 삭제_여부 */
                    LEFT OUTER JOIN TC_CODE  E                                        /*** 공통코드 ***/
                        ON 1 = 1
                        AND E.CODE_VAL = A.VMTC_GRAD                                  /* 코드_값 */
                        AND E.CL_CODE = 'VNTC'                                        /* 분류_코드 - 교통용량 등급(VNTC)[고용량도로(VNTC0001), 중용량도로(VNTC0002), 저용량도로(VNTC0003)] */
                        AND E.USE_AT = 'Y'                                            /* 사용_여부 */
                        AND E.DELETE_AT = 'N'                                         /* 삭제_여부 */
                    INNER JOIN TN_PAV_IDX_ERYYVAL F                                   /*** 포장_지수_초기값 테이블 ***/
                        ON 1 = 1
                ) A
            ) A
            LEFT OUTER JOIN RPMD_CODES B                                              /*** 보수공법별 단가 ***/
                ON B.RPAIR_MTHD_CODE = A.RPAIR_MTHD                                   /* 보수_공법_코드(TC_RPAIR_MTHD.RPAIR_MTHD_CODE)[Do Nothing(RPIR0001), 노화방지공법(RPIR0002), 균열 Sealing(RPIR0003)...] */
        ) A
    );


    IF P_START_END_CODE = 'E' THEN
	    UPDATE TN_RPAIR_TRGET_SLCTN SET
	        SLCTN_STTUS = 'RTSS0003'                      /* 보수_대상_선정.선정_상태 (보수대상을 선정한다.)*/
	        , UPDUSR_NO = P_USER_NO                       /* 보수_대상_선정.수정자_번호 */
	        , UPDT_DT = NOW()                             /* 보수_대상_선정.수정_일시 */
	    WHERE
	        1 = 1
	        AND TRGET_SLCTN_NO = P_TRGET_SLCTN_NO;        /* 보수_대상_선정.대상_선정_번호 */
    END IF;

    O_PROCMSG :='보수대상선정 집계 완료';
    O_PROCCODE :='true';

EXCEPTION
    WHEN OTHERS
    THEN
        O_PROCMSG := SUBSTR(SQLERRM,1,1000);
        O_PROCCODE :='false';
        IF p_MODE = 'DEBUG' THEN
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
