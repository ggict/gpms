CREATE OR REPLACE FUNCTION gpms.prc_repair_target_range_string(p_user_no numeric, p_trget_slctn_no numeric, p_slctn_budget numeric, p_dept_code text, p_decsn_mthd_1_rate numeric, p_decsn_mthd_2_rate numeric, p_decsn_mthd_3_rate numeric, p_decsn_mthd_4_rate numeric, p_decsn_mthd_5_rate numeric, p_mode text, OUT o_proccode text, OUT o_procmsg text)
 RETURNS record
 LANGUAGE plpgsql
AS $function$

DECLARE
    v_CALC_YEAR             TN_SM_DTA_LAST_STTUS.CALC_YEAR%TYPE;
    v_CALC_MT               TN_SM_DTA_LAST_STTUS.CALC_MT%TYPE;
    v_WF1_AMT               numeric;
    v_BF1_AMT               numeric;
    v_BF2_AMT               numeric;
    v_RD_RATE               numeric;
    v_LCTC_RATE             numeric;
    v_WF1_ACC_AMT           numeric;
    v_BF1_ACC_AMT           numeric;
    v_BF2_ACC_AMT           numeric;
    v_RPAIR_MTHD_CLASS      text;
    v_MSR_DM_CODE           text;
    v_MSR_DM_CODE_CALC      text;
    v_RPAIR_MTHD            text;
    v_THRHLD                numeric;
    v_RPAIR_FEE             numeric;
    v_CNTRWK_YEAR           text;

    v_CELL_IDS              text;

    v_FRMULA_NO             TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_RCI_FRMULA_NO         TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_SCR_FRMULA_NO         TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_GPCI_FRMULA_NO        TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_BASE_VAL              TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_CR_VAL                TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_RD_VAL                TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_IRI_VAL               TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_EXPONENT_VAL          TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_CR_IDX                TN_MUMM_SCTN_SRVY_DTA.CR_IDX%TYPE;
    v_RD_IDX                TN_MUMM_SCTN_SRVY_DTA.RD_IDX%TYPE;
    v_RI_IDX                TN_MUMM_SCTN_SRVY_DTA.RI_IDX%TYPE;
    v_XCR                   TN_MUMM_SCTN_SRVY_DTA.XCR%TYPE;
    v_RCI                   TN_MUMM_SCTN_SRVY_DTA.RCI%TYPE;
    v_SCR                   TN_MUMM_SCTN_SRVY_DTA.SCR%TYPE;
    v_BLOCK_CR              TN_MUMM_SCTN_SRVY_DTA.BLOCK_CR%TYPE;
    v_CR_LT                 TN_MUMM_SCTN_SRVY_DTA.CR_LT%TYPE;
    v_CR_WID                TN_MUMM_SCTN_SRVY_DTA.CR_WID%TYPE;
    v_CNTL_DFECT            TN_MUMM_SCTN_SRVY_DTA.CNTL_DFECT%TYPE;
    v_DMG_CUZ_CLMT          TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_CLMT%TYPE;
    v_DMG_CUZ_VMTC          TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_VMTC%TYPE;
    v_DMG_CUZ_ETC           TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_ETC%TYPE;
    v_NHPCI                 TN_MUMM_SCTN_SRVY_DTA.NHPCI%TYPE;
    v_GPCI                  TN_MUMM_SCTN_SRVY_DTA.GPCI%TYPE;
    v_SPI                   TN_MUMM_SCTN_SRVY_DTA.SPI%TYPE;
    v_PC_GRAD               TN_MUMM_SCTN_SRVY_DTA.PC_GRAD%TYPE;
    v_EXCEL_DTA_SEQ         TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;
    v_ROW_INDEX             TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;
    v_DTA_NO                TN_MUMM_SCTN_SRVY_DTA.DTA_NO%TYPE;
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
    v_SRVY_DE               TN_MUMM_SCTN_SRVY_DTA.SRVY_DE%TYPE;
    v_PROCESS_LOG_NO        TL_SRVY_DTA_LOG.PROCESS_LOG_NO%TYPE;
    v_BASE_IDX_VAR          numeric;
    v_CR_IDX_VAR            numeric;
    v_RD_IDX_VAR            numeric;
    v_IRI_IDX_VAR           numeric;
    v_EXPONENT_VAR          numeric;

    v_DMG_CUZ               DMG_CUZ;

    v_AC_IDX                numeric;
    v_BC_IDX                numeric;
    v_LC_IDX                numeric;
    v_TC_IDX                numeric;
    v_PTCHG_IDX             numeric;
    v_POT_IDX               numeric;

    v_USE_AC_FN             varchar(1);
    v_USE_BC_FN             varchar(1);
    v_USE_LC_FN             varchar(1);
    v_USE_TC_FN             varchar(1);
    v_USE_RD_FN             varchar(1);
    v_USE_PTCHG_FN          varchar(1);
    v_USE_POT_FN            varchar(1);
    v_USE_RCI_FN            varchar(1);
    v_USE_SCR_FN            varchar(1);
    v_USE_GPCI_FN           varchar(1);

    v_existData             text;
    V_TEMPMSG               text;

    err_context text;   /* plpgsql exception handling message */

    v_erow record;
    rpairtrgetnodes CURSOR FOR
    WITH RECURSIVE NODELIST AS
    (
    SELECT /* tnRpairTrget.select leehb */
          tnrpairtrget.ROUTE_CODE    /* 보수_대상_항목.노선_코드 */
        , tnrpairtrget.DIRECT_CODE    /* 보수_대상_항목.행선_코드 */
        , tnrpairtrget.TRACK    /* 보수_대상_항목.차로 */
        , tnrpairtrget.STRTPT    /* 보수_대상_항목.시점 */
        , tnrpairtrget.ENDPT   /* 보수_대상_항목.종점 */
        , tnrpairtrget.CELL_TYPE    /* 보수_대상_항목.섹션_구분 */
        , tnrpairtrget.MSR_DM_CODE
        , tnrpairtrget.DEPT_CODE    /* 보수_대상_항목.부서코드 */
        , tnrpairtrget.VMTC_GRAD    /* 보수_대상_항목.교통량등급 */
        , tnrpairtrget.ROAD_GRAD    /* 보수_대상_항목.도로등급 */
        , tnrpairtrget.ADM_CODE   /* 보수_대상_항목.행정구역코드 */
        , nexttnrpairtrget.ROUTE_CODE   NEXT_ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , nexttnrpairtrget.DIRECT_CODE   NEXT_DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , nexttnrpairtrget.TRACK   NEXT_TRACK /* 보수_대상_항목.차로 */
        , nexttnrpairtrget.STRTPT  NEXT_STRTPT  /* 보수_대상_항목.시점 */
        , nexttnrpairtrget.ENDPT   NEXT_ENDPT /* 보수_대상_항목.종점 */
        , nexttnrpairtrget.CELL_TYPE    NEXT_CELL_TYPE/* 보수_대상_항목.섹션_구분 */
        , nexttnrpairtrget.MSR_DM_CODE NEXT_MSR_DM_CODE
        , nexttnrpairtrget.DEPT_CODE    NEXT_DEPT_CODE/* 보수_대상_항목.부서코드 */
        , nexttnrpairtrget.VMTC_GRAD   NEXT_VMTC_GRAD /* 보수_대상_항목.교통량등급 */
        , nexttnrpairtrget.ROAD_GRAD   NEXT_ROAD_GRAD /* 보수_대상_항목.도로등급 */
        , nexttnrpairtrget.ADM_CODE   NEXT_ADM_CODE/* 보수_대상_항목.행정구역코드 */
    FROM
        TN_RPAIR_TRGET  tnrpairtrget  /*** 보수_대상_항목 테이블 ***/
    LEFT OUTER JOIN
        TN_RPAIR_TRGET  nexttnrpairtrget /*** 보수_대상_항목 테이블 ***/
    ON  1=1
    AND tnrpairtrget.TRGET_SLCTN_NO = nexttnrpairtrget.TRGET_SLCTN_NO
    AND tnrpairtrget.ROUTE_CODE = nexttnrpairtrget.ROUTE_CODE   /* 보수_대상_항목.노선_코드 */
    AND  tnrpairtrget.DIRECT_CODE  = nexttnrpairtrget.DIRECT_CODE     /* 보수_대상_항목.행선_코드 */
    AND  tnrpairtrget.TRACK  = nexttnrpairtrget.TRACK     /* 보수_대상_항목.차로 */
    AND  tnrpairtrget.ENDPT  = nexttnrpairtrget.STRTPT     /* 보수_대상_항목.시점 */
    AND tnrpairtrget.CELL_TYPE =   nexttnrpairtrget.CELL_TYPE   /* 보수_대상_항목.섹션_구분 */
    AND tnrpairtrget.MSR_DM_CODE  =   nexttnrpairtrget.MSR_DM_CODE   /* 보수_대상_항목.보수_공법_코드 */
    AND tnrpairtrget.DEPT_CODE  =   nexttnrpairtrget.DEPT_CODE   /* 보수_대상_항목.부서_코드 */
    WHERE 1=1
     AND tnrpairtrget.TRGET_SLCTN_NO = p_TRGET_SLCTN_NO::numeric
     AND tnrpairtrget.MSR_DM_CODE IS NOT NULL     --136
     AND ( ( p_DEPT_CODE IS NULL) OR  ( tnrpairtrget.DEPT_CODE = p_DEPT_CODE) )
    --  AND tnrpairtrget.MSR_DM_CODE = v_MSR_DM_CODE   --136
    ORDER BY
        tnrpairtrget.ROUTE_CODE ASC,tnrpairtrget.DIRECT_CODE ASC,tnrpairtrget.TRACK ASC, tnrpairtrget.STRTPT ASC
    )
    , NODETREE AS
    (
    SELECT
        1 AS LVL_NO,
        ENDPT AS NODE_ENDPT,    /* CONNECT_BY_ROOT ENDPT NODE_ENDPT */
        NODELIST.*
    FROM NODELIST
    WHERE NODELIST.NEXT_STRTPT IS NULL

    UNION ALL

    SELECT
        LVL_NO+1 LVL_NO,
        NODETREE.NODE_ENDPT,     /* CONNECT_BY_ROOT ENDPT NODE_ENDPT */
        NODELIST.*
    FROM NODELIST
    JOIN NODETREE
    ON NODELIST.ENDPT = NODELIST.NEXT_ENDPT
    AND NODELIST.ROUTE_CODE = NODELIST.NEXT_ROUTE_CODE
    AND NODELIST.DIRECT_CODE = NODELIST.NEXT_DIRECT_CODE
    AND NODELIST.TRACK = NODELIST.NEXT_TRACK
    AND NODELIST.CELL_TYPE = NODELIST.NEXT_CELL_TYPE
    AND NODELIST.MSR_DM_CODE = NODELIST.NEXT_MSR_DM_CODE
    AND NODELIST.DEPT_CODE = NODELIST.NEXT_DEPT_CODE
    )
    , LEAFLIST AS
    (
    SELECT *
    FROM NODETREE
    WHERE         /* CONNECT_BY_ISLEAF = 1  | get only leaf node */
        ( SELECT COUNT(*) FROM NODELIST WHERE NODELIST.NEXT_STRTPT = NODETREE.STRTPT) = 0
    ORDER BY
        NODETREE.ROUTE_CODE ASC, NODETREE.DIRECT_CODE ASC, NODETREE.TRACK ASC, NODETREE.STRTPT ASC
    )

    SELECT
          tnsmdtalaststtus.SRVY_YEAR
        , tnsmdtalaststtus.SRVY_MT
        , tnsmdtalaststtus.ROUTE_CODE    /* 최소_구간_조사_자료.노선_코드 */
        , LEAFLIST.DEPT_CODE
        , LEAFLIST.ROAD_GRAD    /* 보수_대상_항목.도로등급 */
        , LEAFLIST.ADM_CODE   /* 보수_대상_항목.행정구역코드 */
        , tnsmdtalaststtus.DIRECT_CODE    /* 최소_구간_조사_자료.행선_코드 */
        , tnsmdtalaststtus.TRACK    /* 최소_구간_조사_자료.차로 */
      --, LISTAGG(  cellsect.CELL_ID,',')  WITHIN GROUP (ORDER BY CELL_ID ASC) CELL_IDS
        , ARRAY_TO_STRING( ARRAY_AGG(cellsect.CELL_ID ORDER BY CELL_ID ASC), ',' ) CELL_IDS
        , MAX(tnsmdtalaststtus.SRVY_NO) SRVY_NO
        , MAX(LEAFLIST.MSR_DM_CODE) MSR_DM_CODE
        , MAX(cellsect.VMTC_GRAD) VMTC_GRAD
        , MAX(LEAFLIST.LVL_NO) NODE_CO
        , LEAFLIST.STRTPT
        , LEAFLIST.NODE_ENDPT ENDPT
        , SUM(cellsect.ROAD_L) ROAD_L-- LEAFLIST.NODE_ENDPT - LEAFLIST.STRTPT
        , SUM(cellsect.ROAD_A) ROAD_A --  (LEAFLIST.NODE_ENDPT - LEAFLIST.STRTPT )* 3.5 AR
        , MAX(LEAFLIST.CELL_TYPE) CELL_TYPE
        , AVG(tnsmdtalaststtus.TRNSPORT_QY) TRNSPORT_QY
        , AVG(tnsmdtalaststtus.IRI_VAL) IRI_VAL
        , AVG(tnsmdtalaststtus.RD_VAL) RD_VAL
        , AVG(tnsmdtalaststtus.CR_VAL) CR_VAL
        , AVG(tnsmdtalaststtus.VRTCAL_CR) VRTCAL_CR
        , AVG(tnsmdtalaststtus.HRZNTAL_CR) HRZNTAL_CR
        , AVG(tnsmdtalaststtus.CNSTRCT_JOINT_CR)  CNSTRCT_JOINT_CR
        , AVG(tnsmdtalaststtus.TRTS_BAC_CR) TRTS_BAC_CR
        , AVG(tnsmdtalaststtus.PTCHG_CR) PTCHG_CR
        , AVG(tnsmdtalaststtus.POTHOLE_CR) POTHOLE_CR
        , AVG(tnsmdtalaststtus.XCR) XCR
        , AVG(tnsmdtalaststtus.BLOCK_CR) BLOCK_CR
        , AVG(tnsmdtalaststtus.CR_LT) CR_LT
        , AVG(tnsmdtalaststtus.CR_WID) CR_WID
        , AVG(tnsmdtalaststtus.SPI) SPI
        , AVG(tnsmdtalaststtus.NHPCI) NHPCI
        , AVG(tnsmdtalaststtus.AC_LOW) AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
        , AVG(tnsmdtalaststtus.AC_MED) AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
        , AVG(tnsmdtalaststtus.AC_HI) AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
        , AVG(tnsmdtalaststtus.BLOCK_CR_LOW) BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
        , AVG(tnsmdtalaststtus.BLOCK_CR_MED) BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
        , AVG(tnsmdtalaststtus.BLOCK_CR_HI) BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
        , AVG(tnsmdtalaststtus.LC_LOW) LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
        , AVG(tnsmdtalaststtus.LC_MED) LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
        , AVG(tnsmdtalaststtus.LC_HI) LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
        , AVG(tnsmdtalaststtus.TC_LOW) TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
        , AVG(tnsmdtalaststtus.TC_MED) TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
        , AVG(tnsmdtalaststtus.TC_HI) TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
        , AVG(tnsmdtalaststtus.AC_IDX) AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
        , AVG(tnsmdtalaststtus.BC_IDX) BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
        , AVG(tnsmdtalaststtus.LC_IDX) LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
        , AVG(tnsmdtalaststtus.TC_IDX) TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
        , AVG(tnsmdtalaststtus.PTCHG_IDX) PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
        , AVG(tnsmdtalaststtus.POTHOLE_IDX) POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
        , AVG(tnsmdtalaststtus.RD_LOW) RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
        , AVG(tnsmdtalaststtus.RD_MED) RD_MED  /* 최소_구간_조사_자료.소성변형_MED */
        , AVG(tnsmdtalaststtus.RD_HI) RD_HI  /* 최소_구간_조사_자료.소성변형_HI */
        , AVG(tnsmdtalaststtus.RD_IDX) RD_IDX /* 최소_구간_조사_자료.소성변형_지수 */
        , AVG(tnsmdtalaststtus.RCI) RCI /* 최소_구간_조사_자료.표면_조도_지수 */
        , AVG(tnsmdtalaststtus.SCR) SCR /* 최소_구간_조사_자료.표면_상태_지수 */
        , AVG(tnsmdtalaststtus.GPCI) GPCI /* 최소_구간_조사_자료.GPCI */
    FROM
        LEAFLIST
    INNER JOIN
        TN_SM_DTA_LAST_STTUS  tnsmdtalaststtus
    ON 1=1
    AND tnsmdtalaststtus.ROUTE_CODE = LEAFLIST.ROUTE_CODE                                    /* 최소_구간_조사_자료.노선_코드 */
    AND tnsmdtalaststtus.DIRECT_CODE = LEAFLIST.DIRECT_CODE                                  /* 최소_구간_조사_자료.행선_코드 */
    AND tnsmdtalaststtus.TRACK = LEAFLIST.TRACK                                             /* 최소_구간_조사_자료.차로 */
    AND tnsmdtalaststtus.STRTPT >= LEAFLIST.STRTPT                                                     /* CELL_SECT.시점 */
    AND tnsmdtalaststtus.STRTPT <= LEAFLIST.NODE_ENDPT                                                     /* CELL_SECT.시점 */
    AND tnsmdtalaststtus.ENDPT >= LEAFLIST.STRTPT                                                     /* CELL_SECT.종점 */
    AND tnsmdtalaststtus.ENDPT <= LEAFLIST.NODE_ENDPT
    INNER JOIN
        CELL_SECT  cellsect
    ON 1=1
    AND cellsect.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE                                    /* 최소_구간_조사_자료.노선_코드 */
    AND cellsect.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE                                  /* 최소_구간_조사_자료.행선_코드 */
    AND cellsect.TRACK = tnsmdtalaststtus.TRACK                                             /* 최소_구간_조사_자료.차로 */
    AND cellsect.STRTPT >= tnsmdtalaststtus.STRTPT                                                     /* CELL_SECT.시점 */
    AND cellsect.STRTPT <= tnsmdtalaststtus.ENDPT                                                     /* CELL_SECT.시점 */
    AND cellsect.ENDPT >= tnsmdtalaststtus.STRTPT                                                     /* CELL_SECT.종점 */
    AND cellsect.ENDPT <= tnsmdtalaststtus.ENDPT
    WHERE  1=1
     AND tnsmdtalaststtus.DELETE_AT = 'N'  /* 최소_구간_조사_자료.삭제_여부 */
     AND tnsmdtalaststtus.USE_AT = 'Y'  /* 최소_구간_조사_자료.사용_여부 */

    GROUP BY tnsmdtalaststtus.SRVY_YEAR , tnsmdtalaststtus.SRVY_MT ,tnsmdtalaststtus.ROUTE_CODE, tnsmdtalaststtus.DIRECT_CODE, tnsmdtalaststtus.TRACK, LEAFLIST.STRTPT, LEAFLIST.NODE_ENDPT
        , LEAFLIST.DEPT_CODE
        , LEAFLIST.VMTC_GRAD    /* 보수_대상_항목.교통량등급 */
        , LEAFLIST.ROAD_GRAD    /* 보수_대상_항목.도로등급 */
        , LEAFLIST.ADM_CODE   /* 보수_대상_항목.행정구역코드 */
    ;

-- DECLARE RAISE_EXT exception;
BEGIN

    -- SELECT  TO_NUMBER(ATRB_VAL) into v_UNIT FROM TC_CODE WHERE  CODE_VAL='UNIT0002' AND USE_AT='Y' AND DELETE_AT='N';
    v_WF1_AMT :=0;
    v_BF1_AMT :=0;
    v_BF2_AMT :=0;
    v_WF1_ACC_AMT :=0;
    v_BF1_ACC_AMT :=0;
    v_BF2_ACC_AMT :=0;

    v_RPAIR_MTHD_CLASS := '1';
    v_MSR_DM_CODE := 'MSRD0001';
    --     , DECODE(tcrpairmthd.MSR_DM_CODE, 'MSRD0001','1', 'MSRD0002','2', 'MSRD0003','3','0') RPAIR_MTHD_CLASS  /* 보수공법코드.공법선정비율_결정방식_코드 */
    o_PROCCODE :='false';
    o_PROCMSG := '보수대상선정 집계 연속 구간 산출 초기화(삭제)준비';

    if p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_STRING) 메시지: %', o_PROCMSG;
    END IF;

    DELETE FROM TN_RPAIR_TRGET_GROUP
    WHERE TRGET_SLCTN_NO = p_TRGET_SLCTN_NO::numeric;
    o_PROCMSG := '보수대상선정 집계 연속 구간 산출 초기화(삭제)완료';

    SELECT TO_CHAR(now(), 'yyyy'),     TO_CHAR(now(), 'MM') INTO v_CALC_YEAR, v_CALC_MT;

    if p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_STRING) 메시지: %', o_PROCMSG;
    END IF;


    SELECT FN_IS_USE_FORMULAR('AC_IDX' )
        ,FN_IS_USE_FORMULAR('BC_IDX' )
        ,FN_IS_USE_FORMULAR('LC_IDX' )
        ,FN_IS_USE_FORMULAR('TC_IDX' )
        ,FN_IS_USE_FORMULAR('RD_IDX' )
        ,FN_IS_USE_FORMULAR('PTCHG_IDX' )
        ,FN_IS_USE_FORMULAR('POT_IDX' )
        ,FN_IS_USE_FORMULAR('RCI' )
        ,FN_IS_USE_FORMULAR('SCR' )
        ,FN_IS_USE_FORMULAR('GPCI' )
     INTO v_USE_AC_FN,v_USE_BC_FN,v_USE_LC_FN,v_USE_TC_FN,v_USE_RD_FN,v_USE_PTCHG_FN,v_USE_POT_FN,v_USE_RCI_FN,v_USE_SCR_FN,v_USE_GPCI_FN
     ;

    o_PROCMSG := '보수대상선정 집계 연속 구간 GPIC 재개산 및 공법 결정 준비';

    if p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_STRING) 메시지: %', o_PROCMSG;
    END IF;

    FOR v_erow IN rpairtrgetnodes
    LOOP

        --   CLOSE rpairtrgetlist;
        --v_RPAIR_MTHD_CLASS := '2';
        -- 포함되는 섹션이 없으면 집계하지 않는다.
        CONTINUE WHEN v_erow.STRTPT IS NULL;

        SELECT CASE v_USE_AC_FN WHEN 'N' THEN v_erow.AC_IDX WHEN 'Y' THEN FN_GET_AC_IDX( v_erow.AC_LOW,v_erow.AC_MED,v_erow.AC_HI) END /* AC_IDX */
                 , CASE v_USE_BC_FN WHEN 'N' THEN v_erow.BC_IDX WHEN 'Y' THEN FN_GET_BC_IDX( v_erow.BLOCK_CR_LOW,v_erow.BLOCK_CR_MED,v_erow.BLOCK_CR_HI) END /* BC_IDX */
                 , CASE v_USE_LC_FN WHEN 'N' THEN v_erow.LC_IDX WHEN 'Y' THEN FN_GET_LC_IDX( v_erow.LC_LOW,v_erow.LC_MED,v_erow.LC_HI) END /* LC_IDX */
                 , CASE v_USE_TC_FN WHEN 'N' THEN v_erow.TC_IDX WHEN 'Y' THEN FN_GET_TC_IDX( v_erow.TC_LOW,v_erow.TC_MED,v_erow.TC_HI) END /* TC_IDX */
                 , CASE v_USE_PTCHG_FN WHEN 'N' THEN v_erow.PTCHG_IDX WHEN 'Y' THEN FN_GET_PATCH_IDX( v_erow.PTCHG_CR) END /* PTCHG_IDX */
                 , CASE v_USE_POT_FN WHEN 'N' THEN v_erow.POTHOLE_IDX WHEN 'Y' THEN FN_GET_POT_IDX( v_erow.POTHOLE_CR) END/* POT_IDX */
                 , CASE v_USE_RD_FN WHEN 'N' THEN v_erow.RD_IDX WHEN 'Y' THEN FN_GET_RUT_IDX( v_erow.RD_LOW,v_erow.RD_MED,v_erow.RD_HI) END /* RD_IDX */
                 , CASE v_USE_RCI_FN WHEN 'N' THEN v_erow.RCI WHEN 'Y' THEN FN_GET_RCI( v_erow.IRI_VAL ) END /* RCI */
        INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI
        ;

        RAISE NOTICE '노드 시작:% | 노드 종료 : %', v_erow.STRTPT, v_erow.ENDPT;


        raise notice '%, %, %, %, %, %, %', v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX;
        SELECT CASE v_USE_SCR_FN WHEN 'N' THEN v_erow.SCR WHEN 'Y' THEN FN_GET_SCR( v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX::numeric) END /* SCR */
        INTO v_SCR
        ;

        o_PROCMSG :='GPCI 도출';
        SELECT CASE v_USE_GPCI_FN WHEN 'N' THEN v_erow.GPCI WHEN 'Y' THEN FN_GET_GPCI( v_SCR::numeric, v_RCI::numeric ) END /* GPCI */
        INTO v_GPCI
        ;

        o_PROCMSG :='GPCI GRADE 도출';

        SELECT FN_GET_IDX_GRADE('GPCI', v_erow.SRVY_YEAR::text, v_GPCI::numeric) INTO v_PC_GRAD;

        o_PROCMSG :='지배결함 도출';
        v_CNTL_DFECT := FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX::numeric, v_RCI::numeric);

        v_DMG_CUZ := FN_GET_DMG_CUZ(v_AC_IDX,v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX::numeric, v_RCI::numeric);
        v_DMG_CUZ_CLMT := v_DMG_CUZ.DMG_CUZ_CLMT;
        v_DMG_CUZ_VMTC := v_DMG_CUZ.DMG_CUZ_VMTC;
        v_DMG_CUZ_ETC := v_DMG_CUZ.DMG_CUZ_ETC;

        SELECT CASE ( (10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)) WHEN 0 THEN 0 ELSE (((10 - v_LC_IDX) + (10 - v_TC_IDX))/((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)))*100 END, /* LCTC_RATE */
               CASE ((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX ) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)) WHEN 0 THEN 0 ELSE (((10 - v_RD_IDX))/((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI) ))*100 END /* RD_RATE */
        INTO v_LCTC_RATE, v_RD_RATE
        ;
    --    v_LCTC_RATE := DECODE(( (10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)), 0, 0, (((10 - v_LC_IDX) + (10 - v_TC_IDX))/((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)))*100) ;
    --    v_RD_RATE  := DECODE(((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX ) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI)), 0, 0, (((10 - v_RD_IDX))/((10 - v_AC_IDX) + (10 - v_BC_IDX ) + (10 - v_LC_IDX) + (10 -  v_TC_IDX ) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX ) + (10 - v_RD_IDX) + (10 - v_RCI) ))*100) ;

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
        ),
        RPMDRSLT AS (
        SELECT
            FN_GET_CNTRWK_YEAR( v_erow.ROUTE_CODE  , v_erow.DIRECT_CODE , v_erow.TRACK::numeric, v_erow.STRTPT, v_erow.ENDPT ) CNTRWK_YEAR
            , FN_GET_RPAIR_MTHD(v_erow.CELL_TYPE, v_erow.VMTC_GRAD, v_GPCI::numeric, v_LC_IDX::numeric, v_RD_IDX::numeric, v_AC_IDX::numeric, 'GPCI') RPAIR_MTHD
            --, FN_GET_RPAIR_MTHD( v_erow.CELL_TYPE, v_erow.VMTC_GRAD, v_GPCI, v_LCTC_RATE, v_RD_RATE) RPAIR_MTHD
            --, FN_GET_GPCI_LIMITVAL( v_erow.CELL_TYPE, v_erow.VMTC_GRAD, v_GPCI, v_LCTC_RATE, v_RD_RATE ) THRHLD
            ,   FN_GET_GPCI_LIMITVAL( v_erow.CELL_TYPE, v_erow.VMTC_GRAD, v_GPCI::numeric, v_LC_IDX::numeric, v_RD_IDX::numeric, v_AC_IDX::numeric, 'GPCI')  THRHLD
            --, 6 THRHLD
            --INTO v_CNTRWK_YEAR, v_RPAIR_MTHD, v_THRHLD
        )
        SELECT
            RPMDRSLT.CNTRWK_YEAR, RPMDRSLT.RPAIR_MTHD, RPMDRSLT.THRHLD  , RPMDCODES.RPAIR_FEE, RPMDCODES.MSR_DM_CODE
        INTO v_CNTRWK_YEAR, v_RPAIR_MTHD, v_THRHLD, v_RPAIR_FEE, v_MSR_DM_CODE_CALC
        FROM RPMDRSLT
        INNER JOIN RPMDCODES
            ON RPMDRSLT.RPAIR_MTHD  = RPMDCODES.RPAIR_MTHD_CODE ;

        INSERT /* tnRpairTrgetGroup.insert leehb */
               INTO TN_RPAIR_TRGET_GROUP  ( /*** 보수_대상_항목_그룹 테이블 ***/
               GROUP_ITEM_NO                                                                   /* 그룹_항목_번호             */
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
             , NODE_CO                              /* 노드 개수             */
             , DELETE_AT                            /* 삭제_여부                  */
             , USE_AT                               /* 사용_여부                  */
             , CRTR_NO                              /* 생성자_번호                */
             , CREAT_DT                             /* 생성_일시                  */
             , UPDUSR_NO                            /* 수정자_번호                */
             , UPDT_DT                              /* 수정_일시                  */
            , VMTC_GRAD    /* 교통량등급 */
            , ROAD_GRAD    /* 도로등급 */
            , ADM_CODE   /* 행정구역코드 */
            , CELL_IDS
        )
        SELECT
               nextval('SEQ_TN_RPAIR_TRGET_GROUP')    /* 그룹_항목_번호            (GROUP_ITEM_NO) 0 */
             , p_TRGET_SLCTN_NO::numeric      /* 대상_선정_번호            (TRGET_SLCTN_NO) 0 */
             , NULL          /* 선정_단계                 (SLCTN_STEP) 0 */
             , NULL   /* 항목_선정_상태            (ITEM_SLCTN_STTUS)  */
             , v_erow.ROUTE_CODE          /* 노선_코드                 (ROUTE_CODE)  */
             , v_erow.DIRECT_CODE         /* 행선_코드                 (DIRECT_CODE)  */
             , v_erow.TRACK               /* 차로                      (TRACK)  */
             , v_erow.STRTPT              /* 시점                      (STRTPT) 0 */
             , v_erow.ENDPT               /* 종점                      (ENDPT) 0 */
             , v_erow.CELL_TYPE           /* 셀_타입                   (CELL_TYPE)  */
             , v_erow.DEPT_CODE           /* 부서코드                  (DEPT_CODE)  */
             , v_erow.SRVY_MT             /* 조사_월                   (SRVY_MT)  */
             , v_erow.SRVY_YEAR           /* 조사_년도                 (SRVY_YEAR)  */
             , v_GPCI                /* GPCI                      (GPCI) 0 */
             , v_PC_GRAD             /* 포장상태등급              (PC_GRAD) 0 */
             , v_CALC_YEAR           /* 산정_년도                 (CALC_YEAR)  */
             , v_CALC_MT             /* 산정_월                   (CALC_MT)  */
             , v_GPCI           /* 산정_GPCI                 (CALC_GPCI) 0 */
             , v_PC_GRAD        /* 산정_포장상태등급         (CALC_PC_GRAD) 0 */
             , v_THRHLD -v_GPCI             /* 파손도_값                 (DMG_VAL) 0 */
             , v_CNTRWK_YEAR         /* 공사_년도                 (CNTRWK_YEAR)  */
             , v_erow.TRNSPORT_QY         /* 교통_량                   (TRNSPORT_QY) 0 */
             , v_erow.ROAD_L                 /* 연장                      (LEN) 0 */
             , v_erow.ROAD_A                  /* 면적                      (AR) 0 */
             , v_RPAIR_MTHD                    /* 보수_공법_코드            (RPAIR_MTHD_CODE)  */
             , v_MSR_DM_CODE_CALC         /* 공법선정비율_결정방식_코드(MSR_DM_CODE)  */
             , v_THRHLD             /* 임계값                    (THRHLD) 0 */
             , ( v_RPAIR_FEE * v_erow.ROAD_A )         /* 금액_산정                 (AMOUNT_CALC) 0 */
             , 0        /* 예산_배정                 (BUDGET_ASIGN) 0 */
             , 'N'           /* 고정_여부                 (FIXING_AT)  */
             , NULL         /* 예산_체크                 (BUDGET_CECK)  */
             , NULL         /* 누적_산정                 (ACCMLT_CALC) 0 */
             , 'N'            /* 선정_여부                 (SLCTN_AT)  */
             , NULL            /* 선정_일시                 (SLCTN_DT) SYSDATE */
             , 'N'       /* 임시_선정_여부            (TMPR_SLCTN_AT)  */
             , v_erow.NODE_CO                              /* 노드 개수             */
             , 'N'           /* 삭제_여부                 (DELETE_AT)  */
             , 'Y'              /* 사용_여부                 (USE_AT)  */
             , p_USER_NO::numeric            /* 생성자_번호               (CRTR_NO) 0 */
             , now()            /* 생성_일시                 (CREAT_DT) SYSDATE */
             , p_USER_NO::numeric           /* 수정자_번호               (UPDUSR_NO) 0 */
             , now()             /* 수정_일시                 (UPDT_DT) SYSDATE */
             , v_erow.VMTC_GRAD    /* CELL_SECT.교통량등급 */
             , v_erow.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
             , v_erow.ADM_CODE  /* CELL_SECT.행정구역코드 */
             , v_erow.CELL_IDS
         ;


    END LOOP;

    o_PROCCODE :='true';
    o_PROCMSG := '보수대상선정 집계 연속 구간 GPIC 재개산 및 공법 결정 완료';

   UPDATE TN_RPAIR_TRGET_SLCTN SET
        SLCTN_STTUS = 'RTSS0004' /* 보수_대상_선정.선정_상태 보수대상연속구간 조회(동일섹션 연속구간 선정) */
        --, SLCTN_BUDGET = #SLCTN_BUDGET# /* 보수_대상_선정.선정_예산 */
        , UPDUSR_NO = p_USER_NO::numeric /* 보수_대상_선정.수정자_번호 */
        , UPDT_DT = now() /* 보수_대상_선정.수정_일시 */
    WHERE 1 = 1
    AND TRGET_SLCTN_NO = p_TRGET_SLCTN_NO::numeric /* 보수_대상_선정.대상_선정_번호 */
    ;

    if p_MODE = 'DEBUG' THEN
        RAISE NOTICE '보수대상선정 집계(PRC_REPAIR_TARGET_RANGE_STRING) 메시지: %', o_PROCMSG;
    END IF;


EXCEPTION
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
        if p_MODE = 'DEBUG' THEN
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
