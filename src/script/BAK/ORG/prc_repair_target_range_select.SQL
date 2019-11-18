CREATE OR REPLACE FUNCTION gpms.prc_repair_target_range_select(p_user_no numeric, p_trget_slctn_no numeric, p_slctn_budget numeric, p_dept_code text, p_decsn_mthd_1_rate numeric, p_decsn_mthd_2_rate numeric, p_decsn_mthd_3_rate numeric, p_decsn_mthd_4_rate numeric, p_decsn_mthd_5_rate numeric, p_mode text, OUT o_proccode text, OUT o_procmsg text)
 RETURNS record
 LANGUAGE plpgsql
AS $function$

DECLARE
    v_EXCEL_DTA_SEQ         TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;

    v_MIN_STRTPT            TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    v_MAX_ENDPT             TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    v_SRVY_YEAR             TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR%TYPE;
    v_SRVY_MT               TN_MUMM_SCTN_SRVY_DTA.SRVY_MT%TYPE;
    v_SRVY_NM               TN_MUMM_SCTN_SRVY_DTA.SRVY_NM%TYPE;
    v_EXMNR_NM              TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM%TYPE;
    v_ROUTE_CODE            TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE%TYPE;
    v_DIRECT_CODE           TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE%TYPE;
    v_TRACK                 TN_MUMM_SCTN_SRVY_DTA.TRACK%TYPE;
    v_STRTPT                TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    v_ENDPT                 TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;

    v_CALC_YEAR             TN_SM_DTA_LAST_STTUS.CALC_YEAR%TYPE;
    v_CALC_MT               TN_SM_DTA_LAST_STTUS.CALC_MT%TYPE;
    v_WF1_AMT               numeric;
    v_BF1_AMT               numeric;
    v_BF2_AMT               numeric;

    v_WF1_ACC_AMT           numeric;
    v_BF1_ACC_AMT           numeric;
    v_BF2_ACC_AMT           numeric;
    v_RPAIR_MTHD_CLASS      text;

    err_context             text;  /* plpgsql exception handling message */

    v_erow record;
    rpairtrgetlist cursor FOR
    WITH RPMDCODES AS
    (
    SELECT
           tcrpairmthd.RPAIR_MTHD_CODE    /* 보수공법코드.보수_공법_코드 */
         , tcrpairmthd.MSRC_CL_CODE    /* 보수공법코드.공법_분류_코드 */
         , (SELECT tccode.CODE_NM    FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSRC_CL_CODE  ) MSRC_CL_NM
         , tcrpairmthd.MSRC_NM    /* 보수공법코드.공법_명 */
         , tcrpairmthd.MSRC_CT    /* 보수공법코드.공법_비용 */
         , tcrpairmthd.MSRC_LCLAS_CODE    /* 보수공법코드.공법_대분류_코드 */
    --     , DECODE(tcrpairmthd.MSR_DM_CODE, 'MSRD0001','1', 'MSRD0002','2', 'MSRD0003','3','0') RPAIR_MTHD_CLASS  /* 보수공법코드.공법선정비율_결정방식_코드 */
         , tcrpairmthd.MSR_DM_CODE /* 공법선정비율_결정방식_코드 */
         , COALESCE( (SELECT tccode.ATRB_VAL   FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSR_DM_CODE AND tccode.CL_CODE ='MSRD' ),'0') RPAIR_MTHD_CLASS
         , tnrpairmthduntpc.CNTRWK_AMOUNT    /* 보수_공법_단가.공사_금액 */
         , tnrpairmthduntpc.PAINT_AMOUNT    /* 보수_공법_단가.차선_도색_금액 */
         , tnrpairmthduntpc.ETC_AMOUNT    /* 보수_공법_단가.기타_금액 */
         , tnrpairmthduntpc.TOT_AMOUNT  RPAIR_FEE  /* 보수_공법_단가.총_금액 */
      FROM GPMS.TC_RPAIR_MTHD  tcrpairmthd  /*** 보수공법코드 테이블 ***/
      INNER JOIN GPMS.TN_RPAIR_MTHD_UNTPC  tnrpairmthduntpc  /*** 보수_공법_단가 테이블 ***/
      ON tnrpairmthduntpc.RPAIR_MTHD_CODE  = tcrpairmthd.RPAIR_MTHD_CODE  /* 보수_공법_단가.보수_공법_코드 */
      AND tnrpairmthduntpc.USE_AT = 'Y'   /* 보수_공법_단가.사용_여부 */
      AND tnrpairmthduntpc.DELETE_AT = 'N'
      WHERE 1=1
       AND tcrpairmthd.DELETE_AT = 'N'                                   /* 보수공법코드.삭제_여부 */
       AND tcrpairmthd.USE_AT = 'Y'                                         /* 보수공법코드.사용_여부 */
    )
    , budgets AS
    (
    SELECT
        p_SLCTN_BUDGET TOTAL_AMT,
        p_SLCTN_BUDGET * p_DECSN_MTHD_1_RATE/100 WF1_AMT,
        p_SLCTN_BUDGET * p_DECSN_MTHD_2_RATE/100 BF1_AMT,
        p_SLCTN_BUDGET * p_DECSN_MTHD_3_RATE/100 BF2_AMT
    )

    SELECT
        CASE WHEN RPAIR_MTHD_CLASS = '0' THEN 'N_DNT'
             WHEN RPAIR_MTHD_CLASS = '1' THEN
                CASE WHEN SIGN((budgets.WF1_AMT) - ACCMLT_CALC) = -1 THEN 'N_WF1'
                ELSE 'Y_WF1' END
             WHEN RPAIR_MTHD_CLASS = '2' THEN
                CASE WHEN SIGN((budgets.BF1_AMT) - ACCMLT_CALC) = -1 THEN 'N_BF1'
                ELSE 'Y_BF1' END
             WHEN RPAIR_MTHD_CLASS = '3' THEN
                CASE WHEN SIGN((budgets.BF2_AMT) - ACCMLT_CALC) = -1 THEN 'N_BF2'
                ELSE 'Y_BF2' END
        END AS BUDGET_CECK,
        CASE WHEN RPAIR_MTHD_CLASS = '0' THEN 0
             WHEN RPAIR_MTHD_CLASS = '1' THEN
                CASE WHEN SIGN((budgets.WF1_AMT) - ACCMLT_CALC) = -1 THEN 0
                ELSE LIST.AMOUNT_CALC END
             WHEN RPAIR_MTHD_CLASS = '2' THEN
                CASE WHEN SIGN((budgets.BF1_AMT) - ACCMLT_CALC) = -1 THEN 0
                ELSE LIST.AMOUNT_CALC END
             WHEN RPAIR_MTHD_CLASS = '3' THEN
                CASE WHEN SIGN(( budgets.BF2_AMT) - ACCMLT_CALC) = -1 THEN 0
                ELSE LIST.AMOUNT_CALC END
        END AS BUDGET_ASIGN
        , LIST.*
    FROM (
        SELECT
            SUM(AMOUNT_CALC) OVER(PARTITION BY RPAIR_MTHD_CLASS ORDER BY LIST.ROW_NO ASC  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) ACCMLT_CALC
           ,LIST.*
        FROM (
            SELECT
                RPMDCODES.RPAIR_MTHD_CLASS RPAIR_MTHD_CLASS
                , RPMDCODES.MSR_DM_CODE
                , RPMDCODES.RPAIR_FEE RPAIR_FEE
                , (RPMDCODES.RPAIR_FEE * LIST.AR )   AMOUNT_CALC
                --ON RPMDCODES WHERE RPAIR_MTHD_CODE = LIST.RPAIR_MTHD
                , LIST.THRHLD - LIST.CALC_GPCI DMG_VAL
                /* 1 WF,2,3 BF */
                , CASE WHEN RPMDCODES.RPAIR_MTHD_CLASS = '1' THEN ROW_NUMBER() OVER(PARTITION BY RPMDCODES.RPAIR_MTHD_CLASS  ORDER BY LIST.THRHLD - LIST.CALC_GPCI DESC, LIST.VNTC_ORDER DESC, LIST.CELL_TYPE_ORDER DESC)
                  ELSE ROW_NUMBER() OVER(PARTITION BY RPMDCODES.RPAIR_MTHD_CLASS  ORDER BY (LIST.THRHLD - LIST.CALC_GPCI) ASC, LIST.VNTC_ORDER ASC, LIST.CELL_TYPE_ORDER DESC)
                  END AS ROW_NO
                , LIST.*
            FROM (
                SELECT
                    ( SELECT tnrouteinfo.ROAD_NAME    /* 노선_대장_마스터.도로_명 */
                      FROM  GPMS.TN_ROUTE_INFO  tnrouteinfo  /*** 노선_대장_마스터 테이블 ***/
                      WHERE tnrouteinfo.ROAD_NO = LIST.ROUTE_CODE ) ROAD_NAME
                  , ( SELECT tcdept.LOWEST_DEPT_NM AS DEPT_NM/*부서코드.최하위부서명*/
                      FROM GPMS.TC_DEPT  tcdept  /*** 부서_코드 테이블 ***/
                      WHERE tcdept.DEPT_CODE = LIST.DEPT_CODE::text    AND tcdept.USE_AT = 'Y'  AND tcdept.DELETE_AT = 'N'  ) DEPT_NM
                  , GPMS.FN_GET_CNTRWK_YEAR(LIST.ROUTE_CODE  , LIST.DIRECT_CODE , LIST.TRACK::numeric, LIST.STRTPT, LIST.ENDPT ) CNTRWK_YEAR
                  , LIST.ROAD_L LEN
                  , LIST.ROAD_A AR
                --, DECODE( ROUND (LIST.GPCI / 10,0), 1 ,'RPMD0006' ,2,'RPMD0005',3 ,'RPMD0004',4,'RPMD0003',5,'RPMD0003',6,'RPMD0002','RPMD0001')  AS RPAIR_MTHD    /* 집계_자료_일반_현황.GPCI */
                --, FN_GET_RPAIR_MTHD( LIST.CELL_TYPE,LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LCTC, LIST.RD) RPAIR_MTHD
                  , FN_GET_RPAIR_MTHD(LIST.CELL_TYPE, LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LC_IDX, LIST.RD_IDX, LIST.AC_IDX, 'GPCI') RPAIR_MTHD
                  , FN_GET_GPCI_LIMITVAL( LIST.CELL_TYPE, LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LC_IDX, LIST.RD_IDX, LIST.AC_IDX, 'GPCI')  THRHLD
                --, FN_GET_GPCI_LIMITVAL( LIST.CELL_TYPE,LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LCTC, LIST.RD) THRHLD
                --, 6 THRHLD
                  , LIST.*
                FROM (
                    SELECT /* cellSect.select  */
                          cellsect.OBJECT_ID    /* CELL_SECT. */
                        , tnsmdtagnlsttus.SRVY_YEAR    /* 집계_자료_일반_현황.조사_년도 */
                        , tnsmdtagnlsttus.SRVY_MT    /* 집계_자료_일반_현황.조사_월 */
                        , COALESCE(tnsmdtagnlsttus.GPCI, 999) AS GPCI    /* 집계_자료_일반_현황.GPCI */
                        , COALESCE(tnsmdtagnlsttus.PC_GRAD, 999) AS PC_GRAD     /* 집계_자료_일반_현황.포장상태등급 */
                        , cellsect.ROUTE_CODE    /* CELL_SECT.노선코드 */
                        , cellsect.DIRECT_CODE    /* CELL_SECT.방향 */
                        , cellsect.TRACK    /* CELL_SECT.차로 */
                        , cellsect.STRTPT    /* CELL_SECT.시점 */
                        , cellsect.ENDPT    /* CELL_SECT.종점 */
                        , cellsect.DEPT_CODE    /* CELL_SECT.부서 코드 */
                        , cellsect.CELL_TYPE    /* CELL_SECT.섹션구분 */
                        , celtcode.CODE_NM   CELL_TYPE_NM
                        , celtcode.ATRB_VAL CELL_TYPE_ORDER     /* 중요! 보수우선순위 결정은 코드의 속성값을 이용한다.    */
                        , vntccode.ATRB_VAL VNTC_ORDER    /* 중요! 보수우선순위 결정은 코드의 속성값을 이용한다.    */
                        , cellsect.VMTC_GRAD    /* CELL_SECT.교통량등급 */
                        , cellsect.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
                        , cellsect.ADM_CODE || '00000' ADM_CODE  /* CELL_SECT.행정구역코드 */
                        , cellsect.ROAD_L    /* CELL_SECT.도로 길이 */ -- 도로등급명
                        , cellsect.ROAD_A    /* CELL_SECT.도로 면적 */ -- 도로등급명
                        , tnsmdtalaststtus.IRI_VAL    /* 집계_자료_일반_현황.종단평탄성_값 */
                        , tnsmdtalaststtus.CR_VAL    /* 집계_자료_일반_현황.표면손상값 */
                        , tnsmdtalaststtus.RD_VAL    /* 집계_자료_일반_현황.소성변형_값 */
                        , tnsmdtalaststtus.BLOCK_CR    /* 집계_자료_일반_현황.블럭_균열 */
                        , tnsmdtalaststtus.TRTS_BAC_CR    /* 집계_자료_일반_현황.거북등_균열 */
                        , tnsmdtalaststtus.PTCHG_IDX    /* 집계_자료_일반_현황.패칭_지수 */
                        , tnsmdtalaststtus.CNTL_DFECT    /* 집계_자료_일반_현황.지배_결함 */
                        , tnsmdtalaststtus.DMG_CUZ_CLMT    /* 집계_자료_일반_현황.파손_원인_기후 */
                        , tnsmdtalaststtus.DMG_CUZ_VMTC    /* 집계_자료_일반_현황.파손_원인_교통량 */
                        , tnsmdtalaststtus.DMG_CUZ_ETC    /* 집계_자료_일반_현황.파손_원인_기타 */
                        , tnsmdtalaststtus.AC_IDX    /* 집계_자료_일반_현황.거북등균열_지수 */
                        , tnsmdtalaststtus.LC_IDX    /* 집계_자료_일반_현황.종방향균열_지수 */
                        , tnsmdtalaststtus.TC_IDX    /* 집계_자료_일반_현황.횡방향균열_지수 */
                        , tnsmdtalaststtus.RD_IDX    /* 집계_자료_일반_현황.소성변형지수 */
                        , tnsmdtalaststtus.POTHOLE_IDX    /* 집계_자료_일반_현황.포트홀_지수 */
                        , tnsmdtalaststtus.TRNSPORT_QY    /* 집계_자료_일반_현황.교통_량 */
                        , tnsmdtalaststtus.CALC_MT    /* 집계_자료_최신_현황.산정_월 */
                        , tnsmdtalaststtus.CALC_YEAR    /* 집계_자료_최신_현황.산정_년도 */
                        , tnsmdtalaststtus.GPCI  CALC_GPCI  /* 집계_자료_최신_현황.GPCI */
                        , tnsmdtalaststtus.PC_GRAD   CALC_PC_GRAD /* 집계_자료_최신_현황.포장상태등급 */
                        --, DECODE(((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)), 0, 0, (((100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX))/((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)))*100) LCTC
                        --, DECODE(((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)), 0, 0, (((100-tnsmdtalaststtus.rd_IDX))/((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)))*100) RD
                    FROM
                        GPMS.CELL_SECT  cellsect  /*** CELL_SECT 테이블 ***/
                    INNER JOIN
                        GPMS.TN_SM_DTA_GNL_STTUS  tnsmdtagnlsttus  /*** 집계_자료_일반_현황 테이블 ***/
                    ON 1=1
                    AND cellsect.ROUTE_CODE = tnsmdtagnlsttus.ROUTE_CODE                           /* CELL_SECT.노선코드 */
                    AND cellsect.DIRECT_CODE = tnsmdtagnlsttus.DIRECT_CODE                        /* CELL_SECT.방향 */
                    AND cellsect.TRACK = tnsmdtagnlsttus.TRACK                                     /* CELL_SECT.차로 */
                    AND cellsect.STRTPT = tnsmdtagnlsttus.STRTPT                                   /* CELL_SECT.시점 */
                    AND cellsect.ENDPT = tnsmdtagnlsttus.ENDPT                                     /* CELL_SECT.종점 */
                    AND tnsmdtagnlsttus.DELETE_AT = 'N'                          /* 집계_자료_일반_현황.삭제_여부 */
                    AND tnsmdtagnlsttus.USE_AT = 'Y'                                /* 집계_자료_일반_현황.사용_여부 */
                    AND tnsmdtagnlsttus.LAST_DTA_AT='Y'
                    INNER JOIN
                        TN_SM_DTA_LAST_STTUS  tnsmdtalaststtus  /*** 집계_자료_최신_현황 테이블 ***/
                    ON 1=1
                    AND cellsect.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE                           /* CELL_SECT.노선코드 */
                    AND cellsect.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE                        /* CELL_SECT.방향 */
                    AND cellsect.TRACK = tnsmdtalaststtus.TRACK                                     /* CELL_SECT.차로 */
                    AND cellsect.STRTPT = tnsmdtalaststtus.STRTPT                                   /* CELL_SECT.시점 */
                    AND cellsect.ENDPT = tnsmdtalaststtus.ENDPT                                     /* CELL_SECT.종점 */
                    AND tnsmdtalaststtus.DELETE_AT = 'N'                          /* 집계_자료_최신_현황.삭제_여부 */
                    AND tnsmdtalaststtus.USE_AT = 'Y'                                /* 집계_자료_최신_현황.사용_여부 */
                    INNER JOIN
                        TC_CODE  celtcode
                    ON
                        celtcode.CODE_VAL = cellsect.CELL_TYPE AND celtcode.CL_CODE = 'CELT' AND celtcode.USE_AT = 'Y'    AND celtcode.DELETE_AT = 'N'
                    LEFT OUTER JOIN
                        TC_CODE  vntccode
                    ON
                        vntccode.CODE_VAL = cellsect.VMTC_GRAD AND vntccode.CL_CODE = 'VNTC' AND vntccode.USE_AT = 'Y'    AND vntccode.DELETE_AT = 'N'
                    WHERE 1=1
                    AND ( ( p_DEPT_CODE IS NULL) OR (p_DEPT_CODE = '') OR ( cellsect.DEPT_CODE = p_DEPT_CODE::numeric) )

                   -- 셀타입별로구분. 2018. 09. 17
                   AND ( cellsect.CELL_TYPE = 'CELT0001' OR cellsect.CELL_TYPE = 'CELT0002' OR cellsect.CELL_TYPE = 'CELT0012' )

                ) LIST
            ) LIST
        LEFT OUTER JOIN
            RPMDCODES
        ON
            RPMDCODES.RPAIR_MTHD_CODE = LIST.RPAIR_MTHD
        WHERE RPMDCODES.RPAIR_MTHD_CLASS = v_RPAIR_MTHD_CLASS
        ) LIST
    ) LIST
    INNER JOIN
        budgets
    ON 1=1

    ORDER BY LIST.ROW_NO ASC
;

-- DECLARE RAISE_EXT exception;
BEGIN

    v_WF1_AMT :=0;
    v_BF1_AMT :=0;
    v_BF2_AMT :=0;
    v_WF1_ACC_AMT :=0;
    v_BF1_ACC_AMT :=0;
    v_BF2_ACC_AMT :=0;

    v_RPAIR_MTHD_CLASS := '1';

    o_PROCCODE :='false';
    o_PROCMSG := '보수대상선정 집계 초기화(삭제)준비';

    IF p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 메시지: %', o_PROCMSG;
    END IF;

    DELETE FROM TN_RPAIR_TRGET
    WHERE TRGET_SLCTN_NO = p_TRGET_SLCTN_NO::numeric;
    o_PROCMSG := '보수대상선정 집계 초기화(삭제)완료';

    SELECT TO_CHAR(now(), 'yyyy'),     TO_CHAR(now(), 'MM') into v_CALC_YEAR, v_CALC_MT;

    IF p_MODE = 'DEBUG' THEN
        --RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 메시지: %', o_PROCMSG;
    END IF;

    FOR v_erow IN rpairtrgetlist
    LOOP

     -- lOOP
    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , TRACK /* 보수_대상_항목.차로 */
        , STRTPT /* 보수_대상_항목.시점 */
        , ENDPT /* 보수_대상_항목.종점 */
        , CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , SRVY_MT /* 보수_대상_항목.조사_월 */
        , SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , GPCI /* 보수_대상_항목.GPCI */
        , PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , CALC_MT /* 보수_대상_항목.산정_월 */
        , CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , LEN /* 보수_대상_항목.연장 */
        , AR /* 보수_대상_항목.면적 */
        , RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , FIXING_AT /* 보수_대상_항목.고정_여부 */
        , BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , DELETE_AT /* 보수_대상_항목.삭제_여부 */
        , USE_AT /* 보수_대상_항목.사용_여부 */
        , CRTR_NO /* 보수_대상_항목.생성자_번호 */
        , CREAT_DT /* 보수_대상_항목.생성_일시 */
        , UPDUSR_NO /* 보수_대상_항목.수정자_번호 */
        , UPDT_DT /* 보수_대상_항목.수정_일시 */
        , DMG_VAL             /* 파손도_값      */
        , DEPT_CODE           /* 부서코드       */
        , TMPR_SLCTN_AT       /* 임시_선정_여부 */
        , MSR_DM_CODE         /* 공법선정비율_결정방식_코드 */
        , THRHLD              /* 임계값         */
        , VMTC_GRAD    /* 교통량등급 */
        , ROAD_GRAD    /* 도로등급 */
        , ADM_CODE   /* 행정구역코드 */
        , SLCTN_ORDR  /*  선정_순서 */
        )
     SELECT
        nextval('SEQ_TN_RPAIR_TRGET') TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , p_TRGET_SLCTN_NO::numeric TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , NULL SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , NULL ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , v_erow.ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , v_erow.DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , v_erow.TRACK /* 보수_대상_항목.차로 */
        , v_erow.STRTPT /* 보수_대상_항목.시점 */
        , v_erow.ENDPT /* 보수_대상_항목.종점 */
        , v_erow.CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , v_erow.SRVY_MT /* 보수_대상_항목.조사_월 */
        , v_erow.SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , v_erow.GPCI /* 보수_대상_항목.GPCI */
        , v_erow.PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , v_erow.CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , v_erow.CALC_MT /* 보수_대상_항목.산정_월 */
        , v_erow.CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , v_erow.CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , v_erow.CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , v_erow.TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , v_erow.LEN /* 보수_대상_항목.연장 */
        , v_erow.AR /* 보수_대상_항목.면적 */
        , v_erow.RPAIR_MTHD RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , v_erow.AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , v_erow.BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , 'N' FIXING_AT /* 보수_대상_항목.고정_여부 */
        , v_erow.BUDGET_CECK   /* 보수_대상_항목.예산_체크 */
        , v_erow.ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N') SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , NULL SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , 'N' /* 보수_대상_항목.삭제_여부 */
        , 'Y' /* 보수_대상_항목.사용_여부 */
        , p_USER_NO::numeric /* 보수_대상_항목.생성자_번호 */
        , now() /* 보수_대상_항목.생성_일시 */
        , p_USER_NO::numeric /* 보수_대상_항목.수정자_번호 */
        , now() /* 보수_대상_항목.수정_일시 */
        , v_erow.DMG_VAL     /* 파손도_값      */
        , v_erow.DEPT_CODE   /* 부서코드       */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N')    /* 임시_선정_여부 TMPR_SLCTN_AT */
        , v_erow.MSR_DM_CODE     /* 공법선정비율_결정방식_코드 */
        , v_erow.THRHLD          /* 임계값         */
        , v_erow.VMTC_GRAD    /* CELL_SECT.교통량등급 */
        , v_erow.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
        , v_erow.ADM_CODE  /* CELL_SECT.행정구역코드 */
        , v_erow.ROW_NO  /*  선정_순서 */
    ;

    IF v_erow.BUDGET_CECK = 'Y_WF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_WF1_AMT) INTO  v_WF1_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF2'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF2_AMT) INTO  v_BF2_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF1_AMT) INTO  v_BF1_AMT;
    END IF;

    END LOOP;
    --   CLOSE rpairtrgetlist;

    v_RPAIR_MTHD_CLASS := '2';

    FOR v_erow IN rpairtrgetlist
    LOOP

     -- lOOP
    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , TRACK /* 보수_대상_항목.차로 */
        , STRTPT /* 보수_대상_항목.시점 */
        , ENDPT /* 보수_대상_항목.종점 */
        , CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , SRVY_MT /* 보수_대상_항목.조사_월 */
        , SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , GPCI /* 보수_대상_항목.GPCI */
        , PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , CALC_MT /* 보수_대상_항목.산정_월 */
        , CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , LEN /* 보수_대상_항목.연장 */
        , AR /* 보수_대상_항목.면적 */
        , RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , FIXING_AT /* 보수_대상_항목.고정_여부 */
        , BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , DELETE_AT /* 보수_대상_항목.삭제_여부 */
        , USE_AT /* 보수_대상_항목.사용_여부 */
        , CRTR_NO /* 보수_대상_항목.생성자_번호 */
        , CREAT_DT /* 보수_대상_항목.생성_일시 */
        , UPDUSR_NO /* 보수_대상_항목.수정자_번호 */
        , UPDT_DT /* 보수_대상_항목.수정_일시 */
        , DMG_VAL             /* 파손도_값      */
        , DEPT_CODE           /* 부서코드       */
        , TMPR_SLCTN_AT       /* 임시_선정_여부 */
        , MSR_DM_CODE         /* 공법선정비율_결정방식_코드 */
        , THRHLD              /* 임계값         */
        , VMTC_GRAD    /* 교통량등급 */
        , ROAD_GRAD    /* 도로등급 */
        , ADM_CODE   /* 행정구역코드 */
        , SLCTN_ORDR  /*  선정_순서 */
        )
     SELECT
        nextval('SEQ_TN_RPAIR_TRGET') TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , p_TRGET_SLCTN_NO::numeric TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , NULL SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , NULL ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , v_erow.ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , v_erow.DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , v_erow.TRACK /* 보수_대상_항목.차로 */
        , v_erow.STRTPT /* 보수_대상_항목.시점 */
        , v_erow.ENDPT /* 보수_대상_항목.종점 */
        , v_erow.CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , v_erow.SRVY_MT /* 보수_대상_항목.조사_월 */
        , v_erow.SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , v_erow.GPCI /* 보수_대상_항목.GPCI */
        , v_erow.PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , v_erow.CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , v_erow.CALC_MT /* 보수_대상_항목.산정_월 */
        , v_erow.CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , v_erow.CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , v_erow.CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , v_erow.TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , v_erow.LEN /* 보수_대상_항목.연장 */
        , v_erow.AR /* 보수_대상_항목.면적 */
        , v_erow.RPAIR_MTHD RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , v_erow.AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , v_erow.BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , 'N' FIXING_AT /* 보수_대상_항목.고정_여부 */
        , v_erow.BUDGET_CECK   /* 보수_대상_항목.예산_체크 */
        , v_erow.ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N') SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , NULL SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , 'N' /* 보수_대상_항목.삭제_여부 */
        , 'Y' /* 보수_대상_항목.사용_여부 */
        , p_USER_NO::numeric /* 보수_대상_항목.생성자_번호 */
        , now() /* 보수_대상_항목.생성_일시 */
        , p_USER_NO::numeric /* 보수_대상_항목.수정자_번호 */
        , now() /* 보수_대상_항목.수정_일시 */
        , v_erow.DMG_VAL     /* 파손도_값      */
        , v_erow.DEPT_CODE   /* 부서코드       */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N')    /* 임시_선정_여부 TMPR_SLCTN_AT */
        , v_erow.MSR_DM_CODE     /* 공법선정비율_결정방식_코드 */
        , v_erow.THRHLD          /* 임계값         */
        , v_erow.VMTC_GRAD    /* CELL_SECT.교통량등급 */
        , v_erow.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
        , v_erow.ADM_CODE  /* CELL_SECT.행정구역코드 */
        , v_erow.ROW_NO  /*  선정_순서 */
    ;

    IF v_erow.BUDGET_CECK = 'Y_WF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_WF1_AMT) INTO  v_WF1_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF2'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF2_AMT) INTO  v_BF2_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF1_AMT) INTO  v_BF1_AMT;
    END IF;

    END LOOP;
   -- CLOSE rpairtrgetlist;

    v_RPAIR_MTHD_CLASS := '3';
    FOR v_erow IN rpairtrgetlist
    LOOP

     -- lOOP
    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , TRACK /* 보수_대상_항목.차로 */
        , STRTPT /* 보수_대상_항목.시점 */
        , ENDPT /* 보수_대상_항목.종점 */
        , CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , SRVY_MT /* 보수_대상_항목.조사_월 */
        , SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , GPCI /* 보수_대상_항목.GPCI */
        , PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , CALC_MT /* 보수_대상_항목.산정_월 */
        , CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , LEN /* 보수_대상_항목.연장 */
        , AR /* 보수_대상_항목.면적 */
        , RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , FIXING_AT /* 보수_대상_항목.고정_여부 */
        , BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , DELETE_AT /* 보수_대상_항목.삭제_여부 */
        , USE_AT /* 보수_대상_항목.사용_여부 */
        , CRTR_NO /* 보수_대상_항목.생성자_번호 */
        , CREAT_DT /* 보수_대상_항목.생성_일시 */
        , UPDUSR_NO /* 보수_대상_항목.수정자_번호 */
        , UPDT_DT /* 보수_대상_항목.수정_일시 */
        , DMG_VAL             /* 파손도_값      */
        , DEPT_CODE           /* 부서코드       */
        , TMPR_SLCTN_AT       /* 임시_선정_여부 */
        , MSR_DM_CODE         /* 공법선정비율_결정방식_코드 */
        , THRHLD              /* 임계값         */
        , VMTC_GRAD    /* 교통량등급 */
        , ROAD_GRAD    /* 도로등급 */
        , ADM_CODE   /* 행정구역코드 */
        , SLCTN_ORDR  /*  선정_순서 */
        )
     SELECT
        nextval('SEQ_TN_RPAIR_TRGET') TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , p_TRGET_SLCTN_NO::numeric TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , NULL SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , NULL ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , v_erow.ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , v_erow.DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , v_erow.TRACK /* 보수_대상_항목.차로 */
        , v_erow.STRTPT /* 보수_대상_항목.시점 */
        , v_erow.ENDPT /* 보수_대상_항목.종점 */
        , v_erow.CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , v_erow.SRVY_MT /* 보수_대상_항목.조사_월 */
        , v_erow.SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , v_erow.GPCI /* 보수_대상_항목.GPCI */
        , v_erow.PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , v_erow.CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , v_erow.CALC_MT /* 보수_대상_항목.산정_월 */
        , v_erow.CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , v_erow.CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , v_erow.CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , v_erow.TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , v_erow.LEN /* 보수_대상_항목.연장 */
        , v_erow.AR /* 보수_대상_항목.면적 */
        , v_erow.RPAIR_MTHD RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , v_erow.AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , v_erow.BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , 'N' FIXING_AT /* 보수_대상_항목.고정_여부 */
        , v_erow.BUDGET_CECK   /* 보수_대상_항목.예산_체크 */
        , v_erow.ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N') SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , NULL SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , 'N' /* 보수_대상_항목.삭제_여부 */
        , 'Y' /* 보수_대상_항목.사용_여부 */
        , p_USER_NO::numeric /* 보수_대상_항목.생성자_번호 */
        , now() /* 보수_대상_항목.생성_일시 */
        , p_USER_NO::numeric /* 보수_대상_항목.수정자_번호 */
        , now() /* 보수_대상_항목.수정_일시 */
        , v_erow.DMG_VAL     /* 파손도_값      */
        , v_erow.DEPT_CODE   /* 부서코드       */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N')   /* 임시_선정_여부 TMPR_SLCTN_AT */
        , v_erow.MSR_DM_CODE     /* 공법선정비율_결정방식_코드 */
        , v_erow.THRHLD          /* 임계값         */
        , v_erow.VMTC_GRAD    /* CELL_SECT.교통량등급 */
        , v_erow.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
        , v_erow.ADM_CODE  /* CELL_SECT.행정구역코드 */
        , v_erow.ROW_NO  /*  선정_순서 */
    ;

    IF v_erow.BUDGET_CECK = 'Y_WF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_WF1_AMT) INTO  v_WF1_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF2'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF2_AMT) INTO  v_BF2_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF1_AMT) INTO  v_BF1_AMT;
    END IF;

    END LOOP;

    v_RPAIR_MTHD_CLASS := '0';
    FOR v_erow IN rpairtrgetlist
    LOOP

     -- lOOP
    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , TRACK /* 보수_대상_항목.차로 */
        , STRTPT /* 보수_대상_항목.시점 */
        , ENDPT /* 보수_대상_항목.종점 */
        , CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , SRVY_MT /* 보수_대상_항목.조사_월 */
        , SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , GPCI /* 보수_대상_항목.GPCI */
        , PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , CALC_MT /* 보수_대상_항목.산정_월 */
        , CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , LEN /* 보수_대상_항목.연장 */
        , AR /* 보수_대상_항목.면적 */
        , RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , FIXING_AT /* 보수_대상_항목.고정_여부 */
        , BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , DELETE_AT /* 보수_대상_항목.삭제_여부 */
        , USE_AT /* 보수_대상_항목.사용_여부 */
        , CRTR_NO /* 보수_대상_항목.생성자_번호 */
        , CREAT_DT /* 보수_대상_항목.생성_일시 */
        , UPDUSR_NO /* 보수_대상_항목.수정자_번호 */
        , UPDT_DT /* 보수_대상_항목.수정_일시 */
        , DMG_VAL             /* 파손도_값      */
        , DEPT_CODE           /* 부서코드       */
        , TMPR_SLCTN_AT       /* 임시_선정_여부 */
        , MSR_DM_CODE         /* 공법선정비율_결정방식_코드 */
        , THRHLD              /* 임계값         */
        , VMTC_GRAD    /* 교통량등급 */
        , ROAD_GRAD    /* 도로등급 */
        , ADM_CODE   /* 행정구역코드 */
        , SLCTN_ORDR  /*  선정_순서 */
        )
     SELECT
        nextval('SEQ_TN_RPAIR_TRGET') TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , p_TRGET_SLCTN_NO::numeric TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , NULL SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , NULL ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , v_erow.ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , v_erow.DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , v_erow.TRACK /* 보수_대상_항목.차로 */
        , v_erow.STRTPT /* 보수_대상_항목.시점 */
        , v_erow.ENDPT /* 보수_대상_항목.종점 */
        , v_erow.CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , v_erow.SRVY_MT /* 보수_대상_항목.조사_월 */
        , v_erow.SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , v_erow.GPCI /* 보수_대상_항목.GPCI */
        , v_erow.PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , v_erow.CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , v_erow.CALC_MT /* 보수_대상_항목.산정_월 */
        , v_erow.CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , v_erow.CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , v_erow.CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , v_erow.TRNSPORT_QY /* 보수_대상_항목.교통량 */
        , v_erow.LEN /* 보수_대상_항목.연장 */
        , v_erow.AR /* 보수_대상_항목.면적 */
        , v_erow.RPAIR_MTHD RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , v_erow.AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , v_erow.BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , 'N' FIXING_AT /* 보수_대상_항목.고정_여부 */
        , v_erow.BUDGET_CECK   /* 보수_대상_항목.예산_체크 */
        , v_erow.ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N') SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , NULL SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , 'N' /* 보수_대상_항목.삭제_여부 */
        , 'Y' /* 보수_대상_항목.사용_여부 */
        , p_USER_NO::numeric /* 보수_대상_항목.생성자_번호 */
        , now() /* 보수_대상_항목.생성_일시 */
        , p_USER_NO::numeric /* 보수_대상_항목.수정자_번호 */
        , now() /* 보수_대상_항목.수정_일시 */
        , v_erow.DMG_VAL     /* 파손도_값      */
        , v_erow.DEPT_CODE   /* 부서코드       */
        , COALESCE(SUBSTR( v_erow.BUDGET_CECK,1,1),'Y','Y','N')    /* 임시_선정_여부 TMPR_SLCTN_AT */
        , v_erow.MSR_DM_CODE     /* 공법선정비율_결정방식_코드 */
        , v_erow.THRHLD          /* 임계값         */
        , v_erow.VMTC_GRAD    /* CELL_SECT.교통량등급 */
        , v_erow.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
        , v_erow.ADM_CODE  /* CELL_SECT.행정구역코드 */
        , v_erow.ROW_NO  /*  선정_순서 */
    ;

    IF v_erow.BUDGET_CECK = 'Y_WF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_WF1_AMT) INTO  v_WF1_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF2'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF2_AMT) INTO  v_BF2_AMT;
    END IF;

    IF v_erow.BUDGET_CECK = 'Y_BF1'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_BF1_AMT) INTO  v_BF1_AMT;
    END IF;

    END LOOP;

    --CLOSE rpairtrgetlist;

    UPDATE TN_RPAIR_TRGET_SLCTN SET
        SLCTN_STTUS = 'RTSS0003' /* 보수_대상_선정.선정_상태 (보수대상을 선정한다.)*/
        --, SLCTN_BUDGET = #SLCTN_BUDGET# /* 보수_대상_선정.선정_예산 */
        , UPDUSR_NO = p_USER_NO::numeric /* 보수_대상_선정.수정자_번호 */
        , UPDT_DT = now() /* 보수_대상_선정.수정_일시 */
    WHERE 1 = 1
    AND TRGET_SLCTN_NO = p_TRGET_SLCTN_NO::numeric /* 보수_대상_선정.대상_선정_번호 */
    ;

    o_PROCMSG :='보수대상선정 집계  완료';
    o_PROCCODE :='true';

    IF p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) WF1_AMT: %', v_WF1_AMT;
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) BF2_AMT: %', v_BF2_AMT;
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) BF1_AMT: %', v_BF1_AMT;
        --RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 메시지: %', o_PROCMSG;
    END IF;

EXCEPTION
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
        IF p_MODE = 'DEBUG' THEN
            RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_SELECT) 오류 발생: TRGET_SLCTN_NO =%  msg=%', p_TRGET_SLCTN_NO, o_PROCMSG;
        END IF;

        -- plpgsql exception handling..
        GET STACKED DIAGNOSTICS err_context = PG_EXCEPTION_CONTEXT;
        RAISE INFO 'Error Name:%',SQLERRM;
        RAISE INFO 'Error State:%', SQLSTATE;
        RAISE INFO 'Error Context:%', err_context;

    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;
END;

$function$
;
