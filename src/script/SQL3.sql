------------------------------------------------------------------
-- 조사자료 분석 1단계 프로시저 (prc_savesurveydata) 변경 스크립트
-- TMP_TN_MUMM_SCTN_SRVY_DTA, TN_MUMM_SCTN_SRVY_DTA 테이블 컬럼 추가
------------------------------------------------------------------
CREATE OR REPLACE FUNCTION gpms.prc_savesurveydata(p_user_no text, p_srvy_no text, p_frmula_nm text, p_row_count text, p_mode text, OUT o_proccode text, OUT o_procmsg text)
 RETURNS record
 LANGUAGE plpgsql
AS $function$

DECLARE
    v_FRMULA_NO    TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_RCI_FRMULA_NO    TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_SCR_FRMULA_NO    TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_GPCI_FRMULA_NO    TN_PAV_FRMULA.FRMULA_NO%TYPE;
    v_BASE_VAL    TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_CR_VAL    TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_RD_VAL    TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_IRI_VAL   TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_EXPONENT_VAL     TN_PAV_FRMULA_IDX.IDX_VAL%TYPE;
    v_CR_IDX    TN_MUMM_SCTN_SRVY_DTA.CR_IDX%TYPE;
    v_RD_IDX    TN_MUMM_SCTN_SRVY_DTA.RD_IDX%TYPE;
    v_RI_IDX    TN_MUMM_SCTN_SRVY_DTA.RI_IDX%TYPE;
    v_XCR    TN_MUMM_SCTN_SRVY_DTA.XCR%TYPE;
    v_RCI    TN_MUMM_SCTN_SRVY_DTA.RCI%TYPE;
    v_SCR    TN_MUMM_SCTN_SRVY_DTA.SCR%TYPE;
    v_BLOCK_CR    TN_MUMM_SCTN_SRVY_DTA.BLOCK_CR%TYPE;
    v_CR_LT    TN_MUMM_SCTN_SRVY_DTA.CR_LT%TYPE;
    v_CR_WID    TN_MUMM_SCTN_SRVY_DTA.CR_WID%TYPE;
    v_CNTL_DFECT TN_MUMM_SCTN_SRVY_DTA.CNTL_DFECT%TYPE;
    v_DMG_CUZ_CLMT TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_CLMT%TYPE;
    v_DMG_CUZ_VMTC TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_VMTC%TYPE;
    v_DMG_CUZ_ETC TN_MUMM_SCTN_SRVY_DTA.DMG_CUZ_ETC%TYPE;
    v_NHPCI    TN_MUMM_SCTN_SRVY_DTA.NHPCI%TYPE;
    v_GPCI    TN_MUMM_SCTN_SRVY_DTA.GPCI%TYPE;
    v_SPI    TN_MUMM_SCTN_SRVY_DTA.SPI%TYPE;
    v_PC_GRAD    TN_MUMM_SCTN_SRVY_DTA.PC_GRAD%TYPE;
    v_EXCEL_DTA_SEQ TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;
    v_ROW_INDEX  TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;
    v_DTA_NO TN_MUMM_SCTN_SRVY_DTA.DTA_NO%TYPE;
    v_MIN_STRTPT TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    v_MAX_ENDPT TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    v_SRVY_NM TN_MUMM_SCTN_SRVY_DTA.SRVY_NM%TYPE;
    v_EXMNR_NM TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM%TYPE;
    v_ROUTE_CODE TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE%TYPE;
    v_DIRECT_CODE TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE%TYPE;
    v_TRACK TN_MUMM_SCTN_SRVY_DTA.TRACK%TYPE;
    v_STRTPT TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    v_ENDPT TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    v_SRVY_DE  TN_MUMM_SCTN_SRVY_DTA.SRVY_DE%TYPE;
    v_PROCESS_LOG_NO     TL_SRVY_DTA_LOG.PROCESS_LOG_NO%TYPE;
    v_SRID geometry_columns.srid%TYPE;      /* USER_SDO_GEOM_METADATA.SRID */

    v_G2_LONLAT  geometry;          /* SDO_GEOMETRY */
    v_G2_LONLAT_BUFFER geometry;    /* SDO_GEOMETRY */
    v_BASE_IDX_VAR numeric;
    v_CR_IDX_VAR numeric;
    v_RD_IDX_VAR numeric;
    v_IRI_IDX_VAR numeric;
    v_EXPONENT_VAR numeric;

    v_DMG_CUZ DMG_CUZ;

    v_AC_IDX numeric;
    v_BC_IDX numeric;
    v_LC_IDX numeric;
    v_TC_IDX numeric;
    v_PTCHG_IDX numeric;
    v_POT_IDX numeric;

    v_USE_AC_FN VARCHAR(1);
    v_USE_BC_FN VARCHAR(1);
    v_USE_LC_FN VARCHAR(1);
    v_USE_TC_FN VARCHAR(1);
    v_USE_RD_FN VARCHAR(1);
    v_USE_PTCHG_FN VARCHAR(1);
    v_USE_POT_FN VARCHAR(1);
    v_USE_RCI_FN VARCHAR(1);
    v_USE_SCR_FN VARCHAR(1);
    v_USE_GPCI_FN VARCHAR(1);
    v_existData text;
    V_TEMPMSG text;

    v_erow record;
    err_context text;

-- DECLARE RAISE_EXT exception;

BEGIN

    o_PROCMSG := 'Y';
    v_ROW_INDEX :=0;
    v_EXCEL_DTA_SEQ := 0;
    v_MIN_STRTPT :=0;
    v_MAX_ENDPT :=10;

    v_MIN_STRTPT := 0;
    v_MAX_ENDPT := 0;
    v_SRVY_NM := '';
    v_EXMNR_NM := '';
    v_ROUTE_CODE := '';
    v_DIRECT_CODE := '';
    v_TRACK := '';
    v_SRVY_DE := '';
    o_PROCCODE :='false';

    SELECT srid  /*, DIMINFO */
    INTO v_SRID  /* , v_DIMINFO */
    FROM geometry_columns
    WHERE f_table_name = 'tn_mumm_sctn_srvy_dta' AND f_geometry_column = 'g2_lonlat_buffer';

    SELECT nextval('SEQ_TL_SRVY_DTA_LOG') INTO v_PROCESS_LOG_NO;

    INSERT INTO TL_SRVY_DTA_LOG (
			PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
			, LOG_MSSAGE /* 조사_자료_로그.로그_메세지 */
			, CRTR_NO /* 조사_자료_로그.생성자_번호 */
			, BEGIN_DT /* 조사_자료_로그.시작_일시 */
			, SRVY_NO /* 조사_자료_로그.조사_번호 */
			, PROCESS_SE /* 조사_자료_로그.처리_구분 */
			, PROCESS_STTUS /* 조사_자료_로그.처리_상태 */
			, END_DT /* 조사_자료_로그.종료_일시 */
		) VALUES (
			v_PROCESS_LOG_NO::int /* 조사_자료_로그.처리_로그_번호 */
			, '조사 자료 등록 및 상태 평가 처리 시작' /* 조사_자료_로그.로그_메세지 */
			, p_USER_NO::int /* 조사_자료_로그.생성자_번호 */
			, now() /* 조사_자료_로그.시작_일시 */
			, p_SRVY_NO::int /* 조사_자료_로그.조사_번호 */
			, 'PCSE0003' /* 조사_자료_로그.처리_구분 */
			, 'PCST0001' /* 조사_자료_로그.처리_상태 */
			, NULL /* 조사_자료_로그.종료_일시 */
		);

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

    FOR v_erow IN
    (
        SELECT
			 SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
			, ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
			, DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
			, TRACK /* 최소_구간_조사_자료.차로 */
			, STRTPT /* 최소_구간_조사_자료.시점 */
			, ENDPT /* 최소_구간_조사_자료.종점 */
			, IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
			, RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
			, VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
			, HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
			, CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
			, TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
			, PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
			, CR_VAL /* 최소_구간_조사_자료.표면손상값 */
			, CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
			, RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
			, CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
			, SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
			, SRVY_MT /* 최소_구간_조사_자료.조사_월 */
			, MEMO /* 최소_구간_조사_자료.메모 */
			, SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
			, TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
			, TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
			, TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
			, TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
			, SRVY_NM /* 최소_구간_조사_자료.조사_명 */
			, EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
			, SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
			, SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
			, ROAD_NM /* 최소_구간_조사_자료.도로_명 */
			, FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
			, FRNT_IMG_FILE_COURS /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
			, CR_IMG_FILE_NM /* 최소_구간_조사_자료.균열_이미지_파일_명 */
			, CR_IMG_FILE_COURS /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
			, RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
			, RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
			, EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
			, FRMULA_NO /* 최소_구간_조사_자료.수식_번호 */
			, POTHOLE_CR /* 최소_구간_조사_자료.포트홀_균열 */
			, RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
			, BLOCK_CR 
		    , CR_LT 
		    , CR_WID 
		    , NHPCI 
		    , SPI 
		    , XCR 
		    , RCI 
		    , SCR 
		    , AC_LOW 
		    , AC_MED 
		    , AC_HI 
		    , LC_LOW 
		    , LC_MED 
		    , LC_HI 
		    , TC_LOW 
		    , TC_MED 
		    , TC_HI 
		    , AC_IDX 
		    , LC_IDX 
		    , TC_IDX 
		    , PTCHG_IDX 
		    , RD_LOW 
		    , RD_MED 
		    , RD_HI 
		    , STRTPT_TEST
		    , ENDPT_TEST
		    , BLOCK_CR_LOW 
		    , BLOCK_CR_MED 
		    , BLOCK_CR_HI 
		    , POTHOLE_IDX 
		    , DMG_CUZ_CLMT 
		    , DMG_CUZ_VMTC 
		    , DMG_CUZ_ETC 
		    , BC_IDX 
			, PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
			, GPCI /* 최소_구간_조사_자료.포장상태지수 */
        FROM TMP_MUMM_SCTN_SRVY_DTA
    )
    LOOP
        o_PROCMSG :='DATAEXISTS';
        v_ROW_INDEX := v_ROW_INDEX+1;
        v_EXCEL_DTA_SEQ := v_EXCEL_DTA_SEQ+1;

        v_NHPCI := 0;
        v_DTA_NO :=-1;
        v_existData :='N';
        IF v_ROW_INDEX = 1 THEN
            v_MIN_STRTPT := COALESCE(v_erow.STRTPT::numeric,0);
            v_MAX_ENDPT := v_erow.ENDPT::numeric;
        ELSE
            v_MIN_STRTPT :=GREATEST(LEAST(v_MIN_STRTPT::numeric, v_erow.STRTPT::numeric),0);
            v_MAX_ENDPT :=GREATEST(v_MAX_ENDPT::numeric, v_erow.ENDPT::numeric,0);
        END IF;

        v_SRVY_NM := v_erow.SRVY_NM;
        v_EXMNR_NM := v_erow.EXMNR_NM;
        v_ROUTE_CODE := v_erow.ROUTE_CODE;
        v_DIRECT_CODE := v_erow.DIRECT_CODE;
        v_TRACK := v_erow.TRACK;
        v_SRVY_DE := v_erow.SRVY_DE;

         o_PROCMSG :='GPCI 지수 도출';
        SELECT
            CASE v_USE_AC_FN WHEN 'N' THEN v_erow.AC_IDX::numeric WHEN 'Y' THEN FN_GET_AC_IDX( v_erow.AC_LOW::numeric,v_erow.AC_MED::numeric,v_erow.AC_HI::numeric) END /* AC_IDX */
             , CASE v_USE_BC_FN WHEN 'N' THEN v_erow.BC_IDX::numeric WHEN 'Y' THEN FN_GET_BC_IDX( v_erow.BLOCK_CR_LOW::numeric,v_erow.BLOCK_CR_MED::numeric,v_erow.BLOCK_CR_HI::numeric) END /* BC_IDX */
             , CASE v_USE_LC_FN WHEN 'N' THEN v_erow.LC_IDX::numeric WHEN 'Y' THEN FN_GET_LC_IDX( v_erow.LC_LOW::numeric,v_erow.LC_MED::numeric,v_erow.LC_HI::numeric) END /* LC_IDX */
             , CASE v_USE_TC_FN WHEN 'N' THEN v_erow.TC_IDX::numeric WHEN 'Y' THEN FN_GET_TC_IDX( v_erow.TC_LOW::numeric,v_erow.TC_MED::numeric,v_erow.TC_HI::numeric) END /* TC_IDX */
             , CASE v_USE_PTCHG_FN WHEN 'N' THEN v_erow.PTCHG_IDX::numeric WHEN 'Y' THEN FN_GET_PATCH_IDX( v_erow.PTCHG_CR::numeric) END /* PTCHG_IDX */
             , CASE v_USE_POT_FN WHEN 'N' THEN v_erow.POTHOLE_IDX::numeric WHEN 'Y' THEN FN_GET_POT_IDX( v_erow.POTHOLE_CR::numeric) END /* POT_IDX */
             , CASE v_USE_RD_FN WHEN 'N' THEN v_erow.RD_IDX::numeric WHEN 'Y' THEN FN_GET_RUT_IDX( v_erow.RD_LOW::numeric,v_erow.RD_MED::numeric,v_erow.RD_HI::numeric) END /* RD_IDX */
             , CASE v_USE_RCI_FN WHEN 'N' THEN v_erow.RCI::numeric WHEN 'Y' THEN FN_GET_RCI( v_erow.IRI_VAL::numeric ) END /* RCI */
        INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI;

        SELECT CASE v_USE_SCR_FN WHEN 'N' THEN v_erow.SCR::numeric WHEN 'Y' THEN FN_GET_SCR( v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX) END /* SCR */
        INTO v_SCR;

        o_PROCMSG :='GPCI 도출';
        SELECT CASE v_USE_GPCI_FN WHEN 'N' THEN v_erow.GPCI::numeric WHEN 'Y' THEN FN_GET_GPCI( v_SCR,v_RCI) END /* GPCI */
        INTO v_GPCI;

        o_PROCMSG :='GPCI GRADE 도출';
        SELECT FN_GET_IDX_GRADE('GPCI', v_erow.SRVY_YEAR, v_GPCI)
        INTO v_PC_GRAD;

        IF v_erow.SRVY_YEAR IS NOT NULL
        THEN
            BEGIN
            o_PROCMSG :='최소_구간_조사_자료 조회';
            IF p_MODE = 'DEBUG' THEN
                V_TEMPMSG := v_erow.SRVY_YEAR || '='|| v_erow.ROUTE_CODE ||'='|| v_erow.DIRECT_CODE || '='||v_erow.TRACK ||'='|| v_erow.STRTPT::numeric ||'='|| v_erow.ENDPT::numeric;
                RAISE NOTICE '%', V_TEMPMSG;
            END IF;

            /* 최소_구간_조사_자료 조회 */
            SELECT
                COALESCE(MAX(tnmummsctnsrvydta.DTA_NO),-1) /* 최소_구간_조사_자료.자료_번호 */
                INTO v_DTA_NO
            FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
                WHERE 1 = 1
                AND tnmummsctnsrvydta.SRVY_YEAR = v_erow.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                AND tnmummsctnsrvydta.ROUTE_CODE = v_erow.ROUTE_CODE  /* 최소_구간_조사_자료.노선_코드 */
                AND  tnmummsctnsrvydta.DIRECT_CODE = v_erow.DIRECT_CODE  /* 최소_구간_조사_자료.행선_코드 */
                AND  tnmummsctnsrvydta.TRACK = v_erow.TRACK  /* 최소_구간_조사_자료.차로 */
                AND  tnmummsctnsrvydta.STRTPT = v_erow.STRTPT::numeric   /* 최소_구간_조사_자료.시점 */
                AND  tnmummsctnsrvydta.ENDPT = v_erow.ENDPT::numeric   /* 최소_구간_조사_자료.종점 */
                AND tnmummsctnsrvydta.DELETE_AT ='N' /* 최소_구간_조사_자료.삭제_여부 */
                AND tnmummsctnsrvydta.USE_AT ='Y' /* 최소_구간_조사_자료.사용_여부 */
            ;
            if p_MODE = 'DEBUG' THEN
                RAISE NOTICE 'v_DTA_NO=%', v_DTA_NO;
            END IF;

                IF v_DTA_NO < 0 THEN
                    v_existData :='N';
                    v_DTA_NO := nextval('SEQ_TN_MUMM_SCTN_SRVY_DTA');
                    o_PROCMSG :='최소_구간_조사_자료 등록';
                    if p_MODE = 'DEBUG' THEN
                        RAISE NOTICE 'v_DTA_NO=신규1%', v_DTA_NO;
                    END IF;

                ELSE
                    v_existData :='Y';
                END IF;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                v_DTA_NO := nextval('SEQ_TN_MUMM_SCTN_SRVY_DTA');
                o_PROCMSG :='최소_구간_조사_자료 등록';

                if p_MODE = 'DEBUG' THEN
                    RAISE NOTICE 'v_DTA_NO=신규2%', v_DTA_NO;
                END IF;

            END;

            v_G2_LONLAT  := ST_Transform(ST_GeomFromText('POINT (' || v_erow.TRACE1_LO::numeric ||' '|| v_erow.TRACE1_LA::numeric ||')', 4326), 5181);
            v_G2_LONLAT_BUFFER := ST_Buffer(v_G2_LONLAT , 50, 'quad_segs=1');
            v_CNTL_DFECT := FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
            v_DMG_CUZ := FN_GET_DMG_CUZ(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
            v_DMG_CUZ_CLMT := v_DMG_CUZ.DMG_CUZ_CLMT;
            v_DMG_CUZ_VMTC := v_DMG_CUZ.DMG_CUZ_VMTC;
            v_DMG_CUZ_ETC := v_DMG_CUZ.DMG_CUZ_ETC;

            IF v_existData = 'N' THEN
               if p_MODE = 'DEBUG' THEN
                RAISE NOTICE '최소_구간_조사_자료 등록=%', v_DTA_NO;
               END IF;
            /* 최소_구간_조사_자료 등록 */
                INSERT INTO TN_MUMM_SCTN_SRVY_DTA (
                    DTA_NO /* 최소_구간_조사_자료.자료_번호 */
                    , SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
                    , SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                    , ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
                    , DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
                    , TRACK /* 최소_구간_조사_자료.차로 */
                    , STRTPT /* 최소_구간_조사_자료.시점 */
                    , ENDPT /* 최소_구간_조사_자료.종점 */
                    , IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
                    , RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
                    , VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
                    , HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
                    , CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
                    , TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
                    , PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
                    , BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                    , CR_LT /* 최소_구간_조사_자료.균열_길이 */
                    , CR_WID /* 최소_구간_조사_자료.균열_폭 */
                    , AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                    , AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                    , AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                    , BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                    , BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                    , BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                    , LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                    , LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                    , LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                    , TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                    , TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                    , TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                    , AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                    , BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                    , LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                    , TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                    , PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                    , POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                    , RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                    , RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                    , RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                    , XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                    , RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                    , SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                    , CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                    , CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                    , RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                    , NHPCI /* 최소_구간_조사_자료.NHPCI */
                    , GPCI /* 최소_구간_조사_자료.GPCI */
                    , SPI /* 최소_구간_조사_자료.SPI */
                    , PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                    , CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                    , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                    , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                    , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                    , SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
                    , SRVY_MT /* 최소_구간_조사_자료.조사_월 */
                    , MEMO /* 최소_구간_조사_자료.메모 */
                    , SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
                    , TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
                    , TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
                    , TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
                    , TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
                    , G2_LONLAT
                    , G2_LONLAT_BUFFER
                    , SRVY_NM /* 최소_구간_조사_자료.조사_명 */
                    , EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
                    , SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
                    , SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
                    , ROAD_NM /* 최소_구간_조사_자료.도로_명 */
                    , FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
					, FRNT_IMG_FILE_COURS /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
					, CR_IMG_FILE_NM /* 최소_구간_조사_자료.균열_이미지_파일_명 */
					, CR_IMG_FILE_COURS /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
                    , RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                    , RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                    , EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                    , FRMULA_NO /* 최소_구간_조사_자료.수식_번호 */
                    , POTHOLE_CR /* 최소_구간_조사_자료.포트홀_개수 */
                    , RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                    , UPDT_AT /* 최소_구간_조사_자료.수정여부 */
                    , DELETE_AT /* 최소_구간_조사_자료.삭제_여부 */
                    , USE_AT /* 최소_구간_조사_자료.사용_여부 */
                    , CRTR_NO /* 최소_구간_조사_자료.생성자_번호 */
                    , CREAT_DT /* 최소_구간_조사_자료.생성_일시 */
                    , UPDUSR_NO /* 최소_구간_조사_자료.수정자_번호 */
                    , UPDT_DT /* 최소_구간_조사_자료.수정_일시 */
                    , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터 */
                ) VALUES (
                    v_DTA_NO::int /* 최소_구간_조사_자료.자료_번호 */
                    , p_SRVY_NO::int /* 최소_구간_조사_자료.조사_번호 */
                    , NULLIF(v_erow.SRVY_YEAR,'') /* 최소_구간_조사_자료.조사_년도 */
                    , NULLIF(v_erow.ROUTE_CODE,'') /* 최소_구간_조사_자료.노선_코드 */
                    , NULLIF(v_erow.DIRECT_CODE,'') /* 최소_구간_조사_자료.행선_코드 */
                    , NULLIF(v_erow.TRACK,'') /* 최소_구간_조사_자료.차로 */
                    , COALESCE(v_erow.STRTPT,0)::numeric /* 최소_구간_조사_자료.시점 */
                    , COALESCE(v_erow.ENDPT,0)::numeric /* 최소_구간_조사_자료.종점 */
                    , COALESCE(v_erow.IRI_VAL,0)::numeric /* 최소_구간_조사_자료.종단평탄성_값 */
                    , COALESCE(v_erow.RD_VAL,0)::numeric /* 최소_구간_조사_자료.소성변형_값 */
                    , COALESCE(v_erow.VRTCAL_CR,0)::numeric /* 최소_구간_조사_자료.종방향_균열 */
                    , COALESCE(v_erow.HRZNTAL_CR,0)::numeric /* 최소_구간_조사_자료.횡방향_균열 */
                    , COALESCE(v_erow.CNSTRCT_JOINT_CR,0)::numeric /* 최소_구간_조사_자료.시공_줄눈_균열 */
                    , COALESCE(v_erow.TRTS_BAC_CR,0)::numeric /* 최소_구간_조사_자료.거북등_균열 */
                    , COALESCE(v_erow.PTCHG_CR,0)::numeric /* 최소_구간_조사_자료.패칭_균열 */
                    , COALESCE(v_erow.BLOCK_CR,0)::numeric /* 최소_구간_조사_자료.블럭_균열 */
                    , COALESCE(v_erow.CR_LT,0)::numeric /* 최소_구간_조사_자료.균열_길이 */
                    , COALESCE(v_erow.CR_WID,0)::numeric /* 최소_구간_조사_자료.균열_폭 */
                    , COALESCE(v_erow.AC_LOW,0)::numeric /* 최소_구간_조사_자료.거북등균열_LOW */
                    , COALESCE(v_erow.AC_MED,0)::numeric /* 최소_구간_조사_자료.거북등균열_MED */
                    , COALESCE(v_erow.AC_HI,0)::numeric /* 최소_구간_조사_자료.거북등균열_HI */
                    , COALESCE(v_erow.BLOCK_CR_LOW,0)::numeric /* 최소_구간_조사_자료.블럭균열_LOW */
                    , COALESCE(v_erow.BLOCK_CR_MED,0)::numeric /* 최소_구간_조사_자료.블럭균열_MED */
                    , COALESCE(v_erow.BLOCK_CR_HI,0)::numeric /* 최소_구간_조사_자료.블럭균열_HI */
                    , COALESCE(v_erow.LC_LOW,0)::numeric /* 최소_구간_조사_자료.종방향균열_LOW */
                    , COALESCE(v_erow.LC_MED,0)::numeric /* 최소_구간_조사_자료.종방향균열_MED */
                    , COALESCE(v_erow.LC_HI,0)::numeric /* 최소_구간_조사_자료.종방향균열_HI */
                    , COALESCE(v_erow.TC_LOW,0)::numeric /* 최소_구간_조사_자료.횡방향균열_LOW */
                    , COALESCE(v_erow.TC_MED,0)::numeric /* 최소_구간_조사_자료.횡방향균열_MED */
                    , COALESCE(v_erow.TC_HI,0)::numeric /* 최소_구간_조사_자료.횡방향균열_HI */
                    , v_AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                    , v_BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                    , v_LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                    , v_TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                    , v_PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                    , v_POT_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                    , COALESCE(v_erow.RD_LOW,0)::numeric /* 최소_구간_조사_자료.소성변형_LOW */
                    , COALESCE(v_erow.RD_MED,0)::numeric /* 최소_구간_조사_자료.소성변형_MED */
                    , COALESCE(v_erow.RD_HI,0)::numeric /* 최소_구간_조사_자료.소성변형_HI */
                    , COALESCE(v_erow.XCR,0)::numeric /* 최소_구간_조사_자료.NHPCI_균열 */
                    , v_RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                    , v_SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                    , COALESCE(v_erow.CR_VAL,0)::numeric /* 최소_구간_조사_자료.표면손상값 */
                    , v_CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                    , v_RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                    , COALESCE(v_erow.NHPCI,0)::numeric /* 최소_구간_조사_자료.포장상태지수 */
                    , v_GPCI /* 최소_구간_조사_자료.GPCI */
                    , COALESCE(v_erow.SPI,0)::numeric /* 최소_구간_조사_자료.SPI */
                    , v_PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                    , v_CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                    , v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                    , v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                    , v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_ */
                    , NULLIF(v_erow.SRVY_KND,'') /* 최소_구간_조사_자료.조사_종류 */
                    , NULLIF(v_erow.SRVY_MT,'') /* 최소_구간_조사_자료.조사_월 */
                    , NULLIF(v_erow.MEMO,'') /* 최소_구간_조사_자료.메모 */
                    , NULLIF(v_erow.SRVY_DE,'') /* 최소_구간_조사_자료.조사_일자 */
                    , COALESCE(v_erow.TRACE1_LA,0)::numeric /* 최소_구간_조사_자료.TRACE1_LA */
                    , COALESCE(v_erow.TRACE1_LO,0)::numeric /* 최소_구간_조사_자료.TRACE1_LO */
                    , COALESCE(v_erow.TRACE2_LA,0)::numeric /* 최소_구간_조사_자료.TRACE2_LA */
                    , COALESCE(v_erow.TRACE2_LO,0)::numeric /* 최소_구간_조사_자료.TRACE2_LO */
                    , v_G2_LONLAT
                    , v_G2_LONLAT_BUFFER
                    , NULLIF(v_erow.SRVY_NM,'') /* 최소_구간_조사_자료.조사_명 */
                    , NULLIF(v_erow.EXMNR_NM,'') /* 최소_구간_조사_자료.조사자_명 */
                    , NULLIF(v_erow.SCTN_STRTPT_DC,'') /* 최소_구간_조사_자료.구간_시점_설명 */
                    , NULLIF(v_erow.SCTN_ENDPT_DC,'') /* 최소_구간_조사_자료.구간_종점_설명 */
                    , NULLIF(v_erow.ROAD_NM,'')
                    , NULLIF(v_erow.FRNT_IMG_FILE_NM,'') /* 최소_구간_조사_자료.전방_이미지_파일_명 */
					, NULLIF(v_erow.FRNT_IMG_FILE_COURS,'') /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
					, NULLIF(v_erow.CR_IMG_FILE_NM,'') /* 최소_구간_조사_자료.균열_이미지_파일_명 */
					, NULLIF(v_erow.CR_IMG_FILE_COURS,'') /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
                    , NULLIF(v_erow.RDSRFC_IMG_FILE_NM_1,'') /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                    , NULLIF(v_erow.RDSRFC_IMG_FILE_NM_2,'') /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                    , v_EXCEL_DTA_SEQ::int /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                    , v_FRMULA_NO::int /* 최소_구간_조사_자료.수식_번호 */
                    , COALESCE(v_erow.POTHOLE_CR,0)::numeric /* 최소_구간_조사_자료.포트홀_개수 */
                    , v_RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                    , 'N'  /* 최소_구간_조사_자료.수정여부 */
                    , 'N' /* 최소_구간_조사_자료.삭제_여부 */
                    , 'Y' /* 최소_구간_조사_자료.사용_여부 */
                    , p_USER_NO::int /* 최소_구간_조사_자료.생성자_번호 */
                    , now() /* 최소_구간_조사_자료.생성_일시 */
                    , p_USER_NO::int /* 최소_구간_조사_자료.수정자_번호 */
                    , now() /* 최소_구간_조사_자료.수정_일시 */
                    , 'N' /* 최소_구간_조사_자료.최종_데이터_여부 */
                );

                --임시테이블 데이터 삭제
                DELETE FROM TMP_MUMM_SCTN_SRVY_DTA;

            ELSIF v_existData = 'Y' THEN
                o_PROCMSG :='최소_구간_조사_자료 수정';
                IF p_MODE = 'DEBUG' THEN
                    RAISE NOTICE '최소_구간_조사_자료 수정=%', v_DTA_NO;
                END IF;

                /* 최소_구간_조사_자료 수정 */
                UPDATE TN_MUMM_SCTN_SRVY_DTA SET
                    SRVY_NO = p_SRVY_NO::int /* 최소_구간_조사_자료.조사_번호 */
                    , CR_VAL = COALESCE(v_erow.CR_VAL,0)::numeric /* 최소_구간_조사_자료.표면손상값 */
                    , RD_VAL = COALESCE(v_erow.RD_VAL,0)::numeric /* 최소_구간_조사_자료.소성변형_값 */
                    , IRI_VAL = COALESCE(v_erow.IRI_VAL,0)::numeric /* 최소_구간_조사_자료.종단평탄성_값 */
                    , VRTCAL_CR = COALESCE(v_erow.VRTCAL_CR,0)::numeric /* 최소_구간_조사_자료.종방향_균열 */
                    , HRZNTAL_CR = COALESCE(v_erow.HRZNTAL_CR,0)::numeric /* 최소_구간_조사_자료.횡방향_균열 */
                    , CNSTRCT_JOINT_CR = COALESCE(v_erow.CNSTRCT_JOINT_CR,0)::numeric /* 최소_구간_조사_자료.시공_줄눈_균열 */
                    , TRTS_BAC_CR = COALESCE(v_erow.TRTS_BAC_CR,0)::numeric /* 최소_구간_조사_자료.거북등_균열 */
                    , PTCHG_CR = COALESCE(v_erow.PTCHG_CR,0)::numeric /* 최소_구간_조사_자료.패칭_균열 */
                    , SRVY_KND = NULLIF(v_erow.SRVY_KND,'') /* 최소_구간_조사_자료.조사_종류 */
                    , SRVY_MT = NULLIF(v_erow.SRVY_MT,'') /* 최소_구간_조사_자료.조사_월 */
                    , MEMO = NULLIF(v_erow.MEMO,'') /* 최소_구간_조사_자료.메모 */
                    , SRVY_DE = NULLIF(v_erow.SRVY_DE,'') /* 최소_구간_조사_자료.조사_일자 */
                    , TRACE1_LA = COALESCE(v_erow.TRACE1_LA,0)::numeric /* 최소_구간_조사_자료.TRACE1_LA */
                    , TRACE1_LO = COALESCE(v_erow.TRACE1_LO,0)::numeric /* 최소_구간_조사_자료.TRACE1_LO */
                    , TRACE2_LA = COALESCE(v_erow.TRACE2_LA,0)::numeric /* 최소_구간_조사_자료.TRACE2_LA */
                    , TRACE2_LO = COALESCE(v_erow.TRACE2_LO,0)::numeric /* 최소_구간_조사_자료.TRACE2_LO */
                    , G2_LONLAT = v_G2_LONLAT
                    , G2_LONLAT_BUFFER = v_G2_LONLAT_BUFFER
                    , SRVY_NM = NULLIF(v_erow.SRVY_NM,'') /* 최소_구간_조사_자료.조사_명 */
                    , EXMNR_NM = NULLIF(v_erow.EXMNR_NM,'') /* 최소_구간_조사_자료.조사자_명 */
                    , SCTN_STRTPT_DC = NULLIF(v_erow.SCTN_STRTPT_DC,'') /* 최소_구간_조사_자료.구간_시점_설명 */
                    , SCTN_ENDPT_DC = NULLIF(v_erow.SCTN_ENDPT_DC,'') /* 최소_구간_조사_자료.구간_종점_설명 */
                    , ROAD_NM = NULLIF(v_erow.ROAD_NM,'') /* 최소_구간_조사_자료.도로_명 */
                    , FRNT_IMG_FILE_NM = NULLIF(v_erow.FRNT_IMG_FILE_NM,'') /* 최소_구간_조사_자료.전방_이미지_파일_명 */
					, FRNT_IMG_FILE_COURS = NULLIF(v_erow.FRNT_IMG_FILE_COURS,'') /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
					, CR_IMG_FILE_NM = NULLIF(v_erow.CR_IMG_FILE_NM,'') /* 최소_구간_조사_자료.균열_이미지_파일_명 */
					, CR_IMG_FILE_COURS = NULLIF(v_erow.CR_IMG_FILE_COURS,'') /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
                    , RDSRFC_IMG_FILE_NM_1 = NULLIF(v_erow.RDSRFC_IMG_FILE_NM_1,'') /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                    , RDSRFC_IMG_FILE_NM_2 = NULLIF(v_erow.RDSRFC_IMG_FILE_NM_2,'') /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                    , POTHOLE_CR = COALESCE(v_erow.POTHOLE_CR,0)::numeric /* 최소_구간_조사_자료.포트홀_개수 */
                    , BLOCK_CR = COALESCE(v_erow.BLOCK_CR,0)::numeric /* 최소_구간_조사_자료.블럭_균열 */
                    , CR_LT = COALESCE(v_erow.CR_LT,0)::numeric /* 최소_구간_조사_자료.균열_길이 */
                    , CR_WID = COALESCE(v_erow.CR_WID,0)::numeric /* 최소_구간_조사_자료.균열_폭 */
                    , AC_LOW = COALESCE(v_erow.AC_LOW,0)::numeric /* 최소_구간_조사_자료.거북등균열_LOW */
                    , AC_MED = COALESCE(v_erow.AC_MED,0)::numeric /* 최소_구간_조사_자료.거북등균열_MED */
                    , AC_HI = COALESCE(v_erow.AC_HI,0)::numeric /* 최소_구간_조사_자료.거북등균열_HI */
                    , BLOCK_CR_LOW = COALESCE(v_erow.BLOCK_CR_LOW,0)::numeric /* 최소_구간_조사_자료.블럭균열_LOW */
                    , BLOCK_CR_MED = COALESCE(v_erow.BLOCK_CR_MED,0)::numeric /* 최소_구간_조사_자료.블럭균열_MED */
                    , BLOCK_CR_HI = COALESCE(v_erow.BLOCK_CR_HI,0)::numeric /* 최소_구간_조사_자료.블럭균열_HI */
                    , LC_LOW = COALESCE(v_erow.LC_LOW,0)::numeric /* 최소_구간_조사_자료.종방향균열_LOW */
                    , LC_MED = COALESCE(v_erow.LC_MED,0)::numeric /* 최소_구간_조사_자료.종방향균열_MED */
                    , LC_HI = COALESCE(v_erow.LC_HI,0)::numeric /* 최소_구간_조사_자료.종방향균열_HI */
                    , TC_LOW = COALESCE(v_erow.TC_LOW,0)::numeric /* 최소_구간_조사_자료.횡방향균열_LOW */
                    , TC_MED = COALESCE(v_erow.TC_MED,0)::numeric /* 최소_구간_조사_자료.횡방향균열_MED */
                    , TC_HI = COALESCE(v_erow.TC_HI,0)::numeric /* 최소_구간_조사_자료.횡방향균열_HI */
                    , AC_IDX = v_AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                    , BC_IDX = v_BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                    , LC_IDX = v_LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                    , TC_IDX = v_TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                    , PTCHG_IDX = v_PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                    , POTHOLE_IDX = v_POT_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                    , RD_LOW = COALESCE(v_erow.RD_LOW,0)::numeric /* 최소_구간_조사_자료.소성변형_LOW */
                    , RD_MED = COALESCE(v_erow.RD_MED,0)::numeric /* 최소_구간_조사_자료.소성변형_MED */
                    , RD_HI = COALESCE(v_erow.RD_HI,0)::numeric /* 최소_구간_조사_자료.소성변형_HI */
                    , XCR = COALESCE(v_erow.XCR,0)::numeric /* 최소_구간_조사_자료.NHPCI_균열 */
                    , RCI = v_RCI/* 최소_구간_조사_자료.표면_조도_지수 */
                    , SCR = v_SCR/* 최소_구간_조사_자료.표면_상태_지수 */
                    , CR_IDX = v_CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                    , RD_IDX = v_RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                    , RI_IDX = v_RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                    , NHPCI = COALESCE(v_erow.NHPCI,0)::numeric /* 최소_구간_조사_자료.포장상태지수 */
                    , GPCI = v_GPCI/* 최소_구간_조사_자료.GPCI */
                    , SPI = COALESCE(v_erow.SPI,0)::numeric /* 최소_구간_조사_자료.SPI */
                    , PC_GRAD = v_PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                    , CNTL_DFECT = v_CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                    , DMG_CUZ_CLMT = v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                    , DMG_CUZ_VMTC = v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                    , DMG_CUZ_ETC = v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                    , UPDT_AT ='N' /* 최소_구간_조사_자료.수정여부 */
                    , EXCEL_DTA_SEQ = v_EXCEL_DTA_SEQ::int /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                    , FRMULA_NO = v_FRMULA_NO::int /* 최소_구간_조사_자료.수식_번호 */
                    , UPDUSR_NO = p_USER_NO::int /* 최소_구간_조사_자료.수정자_번호 */
                    , UPDT_DT = now() /* 최소_구간_조사_자료.수정_일시 */
                    , LAST_DTA_AT = 'N' /* 최소_구간_조사_자료.최종_데이터_여부 */
                WHERE 1 = 1
                AND DTA_NO = v_DTA_NO::int /* 최소_구간_조사_자료.자료_번호 */
                ;
            END IF;

            o_PROCMSG :='조사_자료_현황 수정';
            /* 조사년도, 조사월, 노선코드, 행선코드, 차로, 시점, 종점        */
            UPDATE TN_SRVY_DTA_STTUS SET
                SRVY_KND = v_erow.SRVY_KND /* 조사_자료_현황.조사_종류 */
                , EXCEL_DTA_SEQ = v_EXCEL_DTA_SEQ::int /* 조사_자료_현황.엑셀_자료_SEQ */
                , SRVY_NO = p_SRVY_NO::int /* 조사_자료_현황.조사_번호 */
                , DTA_NO = v_DTA_NO::int     /* 조사_자료_현황.자료_번호 */
                , SM_PROCESS_AT = 'N' /* 조사_자료_현황.집계_구간_산출_여부 */
                , PRDTMDL_PROCESS_AT = 'N' /* 조사_자료_현황.예측_모델_적용_여부 */
                , UPDUSR_NO = p_USER_NO::int /* 조사_자료_현황.수정자_번호 */
                , UPDT_DT = now() /* 조사_자료_현황.수정_일시 */
            WHERE 1 = 1
                AND SRVY_YEAR = v_erow.SRVY_YEAR /* 조사_자료_현황.조사_년도 */
                AND SRVY_MT = v_erow.SRVY_MT /* 조사_자료_현황.조사_월 */
                AND ROUTE_CODE = v_erow.ROUTE_CODE  /* 조사_자료_현황.노선_코드 */
                AND  DIRECT_CODE = v_erow.DIRECT_CODE  /* 조사_자료_현황.행선_코드 */
                AND  TRACK = v_erow.TRACK  /* 조사_자료_현황.차로 */
                AND  STRTPT = v_erow.STRTPT::numeric  /* 조사_자료_현황.시점 */
                AND  ENDPT = v_erow.ENDPT::numeric  /* 조사_자료_현황.종점 */
                AND DELETE_AT ='N' /* 조사_자료_현황.삭제_여부 */
                AND USE_AT ='Y' /* 조사_자료_현황.사용_여부 */
            ;

            IF NOT FOUND THEN
                o_PROCMSG :='조사_자료_현황 등록';
                /* 조사_자료_현황 등록 */
                INSERT INTO TN_SRVY_DTA_STTUS (
                    DTA_STTUS_NO /* 조사_자료_현황.자료_현황_번호 */
                    , SRVY_YEAR /* 조사_자료_현황.조사_년도 */
                    , ROUTE_CODE /* 조사_자료_현황.노선_코드 */
                    , DIRECT_CODE /* 조사_자료_현황.행선_코드 */
                    , TRACK /* 조사_자료_현황.차로 */
                    , STRTPT /* 조사_자료_현황.시점 */
                    , ENDPT /* 조사_자료_현황.종점 */
                    , SRVY_KND /* 조사_자료_현황.조사_종류 */
                    , SRVY_MT /* 조사_자료_현황.조사_월 */
                    , EXCEL_DTA_SEQ /* 조사_자료_현황.엑셀_자료_SEQ */
                    , SRVY_NO /* 조사_자료_현황.조사_번호 */
                    , DTA_NO     /* 조사_자료_현황.자료_번호 */
                    , SM_PROCESS_AT /* 조사_자료_현황.집계_구간_산출_여부 */
                    , PRDTMDL_PROCESS_AT /* 조사_자료_현황.예측_모델_적용_여부 */
                    , DELETE_AT /* 조사_자료_현황.삭제_여부 */
                    , USE_AT /* 조사_자료_현황.사용_여부 */
                    , CRTR_NO /* 조사_자료_현황.생성자_번호 */
                    , CREAT_DT /* 조사_자료_현황.생성_일시 */
                    , UPDUSR_NO /* 조사_자료_현황.수정자_번호 */
                    , UPDT_DT /* 조사_자료_현황.수정_일시 */
                ) VALUES (
                    nextval('SEQ_TN_SRVY_DTA_STTUS') /* 조사_자료_현황.자료_현황_번호 */
                    , NULLIF(v_erow.SRVY_YEAR,'') /* 조사_자료_현황.조사_년도 */
                    , NULLIF(v_erow.ROUTE_CODE,'') /* 조사_자료_현황.노선_코드 */
                    , NULLIF(v_erow.DIRECT_CODE,'') /* 조사_자료_현황.행선_코드 */
                    , NULLIF(v_erow.TRACK,'') /* 조사_자료_현황.차로 */
                    , COALESCE(v_erow.STRTPT,0)::numeric /* 조사_자료_현황.시점 */
                    , COALESCE(v_erow.ENDPT,0)::numeric /* 조사_자료_현황.종점 */
                    , NULLIF(v_erow.SRVY_KND,'') /* 조사_자료_현황.조사_종류 */
                    , NULLIF(v_erow.SRVY_MT,'') /* 조사_자료_현황.조사_월 */
                    , v_EXCEL_DTA_SEQ::int /* 조사_자료_현황.엑셀_자료_SEQ */
                    , p_SRVY_NO::int /* 조사_자료_현황.조사_번호 */
                    , v_DTA_NO::int     /* 조사_자료_현황.자료_번호 */
                    , 'N' /* 조사_자료_현황.집계_구간_산출_여부 */
                    , 'N' /* 조사_자료_현황.예측_모델_적용_여부 */
                    , 'N' /* 조사_자료_현황.삭제_여부 */
                    , 'Y' /* 조사_자료_현황.사용_여부 */
                    , p_USER_NO::int /* 조사_자료_현황.생성자_번호 */
                    , now() /* 조사_자료_현황.생성_일시 */
                    , p_USER_NO::int /* 조사_자료_현황.수정자_번호 */
                    , now() /* 조사_자료_현황.수정_일시 */
                );

            END IF;

        END IF;

    END LOOP;

    raise notice '시종점 데이터 검증용%', '';

    /* 시종점 데이터 검증용 */
    UPDATE TN_MUMM_SCTN_SRVY_DTA
    SET STRTPT_TEST = COALESCE(STRTPT::numeric,0)
      , ENDPT_TEST = ENDPT::numeric
    WHERE DELETE_AT = 'N'
      AND USE_AT = 'Y'
      AND SRVY_NO = p_SRVY_NO::int;

      raise notice '조사_자료_엑셀.마스터 정보 수정%', '';

    /* 조사_자료_엑셀.마스터 정보 수정 */
    UPDATE TN_SRVY_DTA SET
         DATA_CO = v_ROW_INDEX::int /* 조사_자료_엑셀.데이터_건수 */
        , ROUTE_CODE = v_ROUTE_CODE /* 조사_자료_엑셀.노선_코드 */
        , DIRECT_CODE = v_DIRECT_CODE /* 조사_자료_엑셀.행선_코드 */
        , TRACK = v_TRACK /* 조사_자료_엑셀.차로 */
        , STRTPT = v_MIN_STRTPT::numeric /* 조사_자료_엑셀.시점 */
        , ENDPT = v_MAX_ENDPT::numeric /* 조사_자료_엑셀.종점 */
        , SRVY_DE = v_SRVY_DE /* 조사_자료_엑셀.조사_일자 */
        , SRVY_NM = v_SRVY_NM /* 조사_자료_엑셀.조사_명 */
        , EXMNR_NM = v_EXMNR_NM /* 조사_자료_엑셀.조사자_명 */
        , EVL_PROCESS_AT = 'Y' /* 조사_자료_엑셀.평가_처리_여부 */
        , UPDUSR_NO = p_USER_NO::int /* 조사_자료_엑셀.수정자_번호 */
        , UPDT_DT = now() /* 조사_자료_엑셀.수정_일시 */
        WHERE 1 = 1
        AND SRVY_NO = p_SRVY_NO::int /* 조사_자료_엑셀.조사_번호 */
        AND DELETE_AT = 'N' /* 조사_자료_엑셀.삭제_여부 */
        AND USE_AT = 'Y' /* 조사_자료_엑셀.사용_여부 */
        ;
    o_PROCMSG :='조사 자료 엑셀 수정';
    o_PROCCODE :='true';

raise notice '최종 데이터 여부 체크%', '';
    /* 최종 데이터 여부 체크*/
    SELECT ROUTE_CODE, DIRECT_CODE, TRACK, MIN(STRTPT), MAX(ENDPT)
      INTO v_ROUTE_CODE, v_DIRECT_CODE, v_TRACK, v_STRTPT, v_ENDPT
      FROM TN_MUMM_SCTN_SRVY_DTA
     WHERE SRVY_NO = P_SRVY_NO::int
     GROUP BY ROUTE_CODE, DIRECT_CODE, TRACK;

raise notice '최종 데이터 여부 체크2%', '';
    UPDATE TN_MUMM_SCTN_SRVY_DTA
       SET LAST_DTA_AT = 'N'
     WHERE LAST_DTA_AT = 'Y'
       AND USE_AT = 'Y'
       AND DELETE_AT = 'N'
       AND ROUTE_CODE = v_ROUTE_CODE
       AND DIRECT_CODE = v_DIRECT_CODE
       AND TRACK = v_TRACK
       AND STRTPT >= v_STRTPT::numeric
       AND ENDPT <= v_ENDPT::numeric;

raise notice '최종 데이터 여부 체크3%', '';
    UPDATE TN_MUMM_SCTN_SRVY_DTA
       SET LAST_DTA_AT = 'Y'
     WHERE DTA_NO IN (
                        SELECT DTA_NO FROM (
                            SELECT DTA_NO
                                 , USE_AT
                                 , DELETE_AT
                                 , ROW_NUMBER() OVER(PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT ORDER BY SRVY_YEAR || SRVY_MT DESC) RN
                            FROM TN_MUMM_SCTN_SRVY_DTA
                            WHERE USE_AT = 'Y'
                              AND DELETE_AT = 'N'
                              AND ROUTE_CODE = v_ROUTE_CODE
                              AND DIRECT_CODE = v_DIRECT_CODE
                              AND TRACK = v_TRACK
                              AND STRTPT >= v_STRTPT::numeric
                              AND ENDPT <= v_ENDPT::numeric
                        ) MAXLIST
                        WHERE MAXLIST.RN = 1
                        AND MAXLIST.USE_AT = 'Y'
                        AND MAXLIST.DELETE_AT = 'N'
                       );

    /* 조사_자료_로그 수정 */
    UPDATE TL_SRVY_DTA_LOG SET
    LOG_MSSAGE = '조사 자료 등록 및 상태 평가 처리 완료' /* 조사_자료_로그.로그_메세지 */
    , PROCESS_STTUS = 'PCST0002' /* 조사_자료_로그.처리_상태 */
    , END_DT = now() /* 조사_자료_로그.종료_일시 */
    WHERE 1 = 1
    AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
    ;

EXCEPTION
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';

        /* 조사_자료_로그 수정 */
		UPDATE TL_SRVY_DTA_LOG SET
        LOG_MSSAGE = '조사 자료 등록 및 상태 평가 처리 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
        , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
        , END_DT = now() /* 조사_자료_로그.종료_일시 */
		WHERE 1 = 1
		AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
        ;

        -- plpgsql exception handling..
        GET STACKED DIAGNOSTICS err_context = PG_EXCEPTION_CONTEXT;
        RAISE INFO 'Error Name:%',SQLERRM;
        RAISE INFO 'Error State:%', SQLSTATE;
        RAISE INFO 'Error Context:%', err_context;
    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;
END;

$function$;






------------------------------------------------------------------
-- 조사자료 분석 2단계 프로시저 (prc_srvy_dta_sys_reflct) 변경 스크립트
-- TMP_TN_MUMM_SCTN_SRVY_DTA, TN_MUMM_SCTN_SRVY_DTA 테이블 컬럼 추가
------------------------------------------------------------------
CREATE OR REPLACE FUNCTION gpms.prc_srvy_dta_sys_reflct(p_user_no text, p_srvy_no text, OUT o_proccode text, OUT o_procmsg text)
 RETURNS record
 LANGUAGE plpgsql
AS $function$

DECLARE
    v_SRID           	numeric;
    v_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    v_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    p_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
    p_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    v_TRACE_LON      	TN_MUMM_SCTN_SRVY_DTA.TRACE1_LO%TYPE;
    v_TRACE_LAT      	TN_MUMM_SCTN_SRVY_DTA.TRACE1_LA%TYPE;
    v_DTA_NO				TN_MUMM_SCTN_SRVY_DTA.DTA_NO%TYPE;
    v_SRVY_YEAR          TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR%TYPE;
    v_SRVY_MT            TN_MUMM_SCTN_SRVY_DTA.SRVY_MT%TYPE;
    v_DTA_STTUS_NO		TN_SRVY_DTA_STTUS.DTA_STTUS_NO%TYPE;
    v_ROUTE_CODE 	    TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE%TYPE;
    v_DIRECT_CODE 	    TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE%TYPE;
    v_TRACK 	            TN_MUMM_SCTN_SRVY_DTA.TRACK%TYPE;
    v_GEOM           	geometry;
    v_INDEX          	INTEGER := 0;
    v_UNIT               numeric;

    v_ROW_GRP           record;
    v_ROW               record;

    err_context text;

/* 로딩된 최소_구간_조사_자료 조회 l,m,h 추가 */
    t_CURSOR CURSOR FOR
    WITH tnmummsctnsrvydta AS
    (
    SELECT
        tnmummsctnsrvydta.DTA_NO /* 최소_구간_조사_자료.자료_번호 */
        , tnmummsctnsrvydta.SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
        , tnmummsctnsrvydta.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
        , tnmummsctnsrvydta.ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
        , tnmummsctnsrvydta.DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
        , tnmummsctnsrvydta.TRACK /* 최소_구간_조사_자료.차로 */
        , tnmummsctnsrvydta.STRTPT /* 최소_구간_조사_자료.시점 */
        , tnmummsctnsrvydta.ENDPT /* 최소_구간_조사_자료.종점 */
        , tnmummsctnsrvydta.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
        , tnmummsctnsrvydta.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
        , tnmummsctnsrvydta.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
        , tnmummsctnsrvydta.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
        , tnmummsctnsrvydta.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
        , tnmummsctnsrvydta.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
        , tnmummsctnsrvydta.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
        , tnmummsctnsrvydta.BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
        , tnmummsctnsrvydta.CR_LT /* 최소_구간_조사_자료.균열_길이 */
        , tnmummsctnsrvydta.CR_WID /* 최소_구간_조사_자료.균열_폭 */
        , tnmummsctnsrvydta.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
        , tnmummsctnsrvydta.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
        , tnmummsctnsrvydta.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
        , tnmummsctnsrvydta.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
        , tnmummsctnsrvydta.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
        , tnmummsctnsrvydta.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
        , tnmummsctnsrvydta.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
        , tnmummsctnsrvydta.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
        , tnmummsctnsrvydta.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
        , tnmummsctnsrvydta.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
        , tnmummsctnsrvydta.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
        , tnmummsctnsrvydta.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
        , tnmummsctnsrvydta.AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
        , tnmummsctnsrvydta.BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
        , tnmummsctnsrvydta.LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
        , tnmummsctnsrvydta.TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
        , tnmummsctnsrvydta.PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
        , tnmummsctnsrvydta.POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
        , tnmummsctnsrvydta.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
        , tnmummsctnsrvydta.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
        , tnmummsctnsrvydta.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
        , tnmummsctnsrvydta.XCR /* 최소_구간_조사_자료.NHPCI_균열 */
        , tnmummsctnsrvydta.RCI /* 최소_구간_조사_자료.표면_조도_지수 */
        , tnmummsctnsrvydta.SCR /* 최소_구간_조사_자료.표면_상태_지수 */
        , tnmummsctnsrvydta.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
        , tnmummsctnsrvydta.CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
        , tnmummsctnsrvydta.RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
        , tnmummsctnsrvydta.NHPCI /* 최소_구간_조사_자료.NHPCI */
        , tnmummsctnsrvydta.GPCI /* 최소_구간_조사_자료.GPCI */
        , tnmummsctnsrvydta.SPI /* 최소_구간_조사_자료.SPI */
        , tnmummsctnsrvydta.PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
        , tnmummsctnsrvydta.CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
        , tnmummsctnsrvydta.DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
        , tnmummsctnsrvydta.DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
        , tnmummsctnsrvydta.DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
        , tnmummsctnsrvydta.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
        , tnmummsctnsrvydta.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
        , tnmummsctnsrvydta.MEMO /* 최소_구간_조사_자료.메모 */
        , tnmummsctnsrvydta.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
        , tnmummsctnsrvydta.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
        , tnmummsctnsrvydta.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
        , tnmummsctnsrvydta.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
        , tnmummsctnsrvydta.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
        , tnmummsctnsrvydta.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
        , tnmummsctnsrvydta.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
        , tnmummsctnsrvydta.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
        , tnmummsctnsrvydta.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
        , tnmummsctnsrvydta.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
		, tnmummsctnsrvydta.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
		, tnmummsctnsrvydta.FRNT_IMG_FILE_COURS /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
		, tnmummsctnsrvydta.CR_IMG_FILE_NM /* 최소_구간_조사_자료.균열_이미지_파일_명 */
		, tnmummsctnsrvydta.CR_IMG_FILE_COURS /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
        , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
        , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
        , tnmummsctnsrvydta.EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
        , tnmummsctnsrvydta.FRMULA_NO FRMULA_NO/* 최소_구간_조사_자료.수식_번호 */
        , tnmummsctnsrvydta.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_균 */
        , tnmummsctnsrvydta.RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
        , tnmummsctnsrvydta.UPDT_AT /* 최소_구간_조사_자료.수정_여부 */
        , tnmummsctnsrvydta.LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
        , tnmummsctnsrvydta.G2_LONLAT
        , tnmummsctnsrvydta.G2_LONLAT_BUFFER
    FROM
        TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
    WHERE 1 = 1
        AND tnmummsctnsrvydta.UPDT_AT = 'N'
        AND tnmummsctnsrvydta.USE_AT = 'Y'
        AND tnmummsctnsrvydta.DELETE_AT = 'N'
        AND tnmummsctnsrvydta.TRACE1_LO <> 0
        AND tnmummsctnsrvydta.TRACE1_LO IS NOT NULL
        AND tnmummsctnsrvydta.TRACE1_LA <> 0
        AND tnmummsctnsrvydta.TRACE1_LA IS NOT NULL
    )
    SELECT
        tnmummsctnsrvydtacal.*
    FROM (
        SELECT /*+ INDEX( TN_MUMM_SCTN_SRVY_DTA TN_MUMM_LONLAT_BUFFER_IDX  ) */
            tnmummsctnsrvydta.DTA_NO /* 최소_구간_조사_자료.자료_번호 */
            , tnmummsctnsrvydta.SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
            , tnmummsctnsrvydta.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
            , tnmummsctnsrvydta.ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
            , tnmummsctnsrvydta.DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
            , tnmummsctnsrvydta.TRACK /* 최소_구간_조사_자료.차로 */
            , cell10.STRTPT /* 최소_구간_조사_자료.시점 */
            , cell10.ENDPT /* 최소_구간_조사_자료.종점 */
            , tnmummsctnsrvydta.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
            , tnmummsctnsrvydta.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
            , tnmummsctnsrvydta.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
            , tnmummsctnsrvydta.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
            , tnmummsctnsrvydta.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
            , tnmummsctnsrvydta.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
            , tnmummsctnsrvydta.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
            , tnmummsctnsrvydta.BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
            , tnmummsctnsrvydta.CR_LT /* 최소_구간_조사_자료.균열_길이 */
            , tnmummsctnsrvydta.CR_WID /* 최소_구간_조사_자료.균열_폭 */
            , tnmummsctnsrvydta.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
            , tnmummsctnsrvydta.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
            , tnmummsctnsrvydta.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
            , tnmummsctnsrvydta.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
            , tnmummsctnsrvydta.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
            , tnmummsctnsrvydta.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
            , tnmummsctnsrvydta.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
            , tnmummsctnsrvydta.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
            , tnmummsctnsrvydta.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
            , tnmummsctnsrvydta.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
            , tnmummsctnsrvydta.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
            , tnmummsctnsrvydta.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
            , tnmummsctnsrvydta.AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
            , tnmummsctnsrvydta.BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
            , tnmummsctnsrvydta.LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
            , tnmummsctnsrvydta.TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
            , tnmummsctnsrvydta.PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
            , tnmummsctnsrvydta.POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
            , tnmummsctnsrvydta.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
            , tnmummsctnsrvydta.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
            , tnmummsctnsrvydta.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
            , tnmummsctnsrvydta.XCR /* 최소_구간_조사_자료.NHPCI_균열 */
            , tnmummsctnsrvydta.RCI /* 최소_구간_조사_자료.표면_조도_지수 */
            , tnmummsctnsrvydta.SCR /* 최소_구간_조사_자료.표면_상태_지수 */
            , tnmummsctnsrvydta.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
            , tnmummsctnsrvydta.CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
            , tnmummsctnsrvydta.RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
            , tnmummsctnsrvydta.NHPCI /* 최소_구간_조사_자료.NHPCI */
            , tnmummsctnsrvydta.GPCI /* 최소_구간_조사_자료.GPCI */
            , tnmummsctnsrvydta.SPI /* 최소_구간_조사_자료.SPI */
            , tnmummsctnsrvydta.PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
            , tnmummsctnsrvydta.CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
            , tnmummsctnsrvydta.DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
            , tnmummsctnsrvydta.DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
            , tnmummsctnsrvydta.DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
            , tnmummsctnsrvydta.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
            , tnmummsctnsrvydta.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
            , tnmummsctnsrvydta.MEMO /* 최소_구간_조사_자료.메모 */
            , tnmummsctnsrvydta.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
            , tnmummsctnsrvydta.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
            , tnmummsctnsrvydta.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
            , tnmummsctnsrvydta.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
            , tnmummsctnsrvydta.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
            , tnmummsctnsrvydta.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
            , tnmummsctnsrvydta.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
            , tnmummsctnsrvydta.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
            , tnmummsctnsrvydta.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
            , tnmummsctnsrvydta.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
			, tnmummsctnsrvydta.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
			, tnmummsctnsrvydta.FRNT_IMG_FILE_COURS /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
			, tnmummsctnsrvydta.CR_IMG_FILE_NM /* 최소_구간_조사_자료.균열_이미지_파일_명 */
			, tnmummsctnsrvydta.CR_IMG_FILE_COURS /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
            , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
            , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
            , tnmummsctnsrvydta.EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
            , tnmummsctnsrvydta.FRMULA_NO FRMULA_NO/* 최소_구간_조사_자료.수식_번호 */
            , tnmummsctnsrvydta.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_균 */
            , tnmummsctnsrvydta.RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
            , tnmummsctnsrvydta.UPDT_AT /* 최소_구간_조사_자료.수정_여부 */
            , tnmummsctnsrvydta.LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
            , tnmummsctnsrvydta.G2_LONLAT
            , tnmummsctnsrvydta.G2_LONLAT_BUFFER
            , ROW_NUMBER () OVER ( PARTITION BY tnmummsctnsrvydta.DTA_NO
                                   /* ORDER BY ST_Distance( ST_Centroid(cell10.G2_SPATIAL, (SELECT DIMINFO FROM USER_SDO_GEOM_METADATA WHERE TABLE_NAME = 'CELL_10')) , tnmummsctnsrvydta.G2_LONLAT , 1 ) */
                                      ORDER BY ST_Distance( ST_Centroid(cell10.geom), tnmummsctnsrvydta.G2_LONLAT)
                                     ASC) RN
        FROM tnmummsctnsrvydta
        INNER JOIN
            CELL_10 cell10
        ON  tnmummsctnsrvydta.ROUTE_CODE = cell10.ROUTE_CODE
        AND tnmummsctnsrvydta.DIRECT_CODE = cell10.DIRECT_CODE
        AND tnmummsctnsrvydta.TRACK = cell10.TRACK
        AND tnmummsctnsrvydta.SRVY_NO = p_SRVY_NO::int
        AND cell10.STRTPT >=  p_STRTPT::numeric AND  cell10.STRTPT <= p_ENDPT::numeric /* 최소_구간_조사_자료.시점 */
        AND cell10.ENDPT >= p_STRTPT::numeric AND  cell10.ENDPT <= p_ENDPT::numeric /* 최소_구간_조사_자료.시점 */
        AND tnmummsctnsrvydta.STRTPT >= (cell10.STRTPT-1000) AND  tnmummsctnsrvydta.STRTPT <= cell10.STRTPT +1000 /* 최소_구간_조사_자료.시점 */
        AND tnmummsctnsrvydta.ENDPT >= (cell10.ENDPT-1000) AND  tnmummsctnsrvydta.ENDPT <= cell10.ENDPT +1000 /* 최소_구간_조사_자료.시점 */
   --   AND SDO_GEOM.SDO_DISTANCE( cell10.G2_CENTROID , tnmummsctnsrvydta.G2_LONLAT , 1 ) <= 70
        /* AND SDO_RELATE( cell10.G2_SPATIAL , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 'mask=ANYINTERACT' ) = 'TRUE' */
        AND ST_Intersects( cell10.geom, tnmummsctnsrvydta.G2_LONLAT_BUFFER) IS TRUE
   --   AND SDO_GEOM.SDO_INTERSECTION( cell10.G2_EXTENT , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 1 ) IS NOT NULL
   --   AND SDO_GEOM.SDO_INTERSECTION( cell10.G2_SPATIAL , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 1 ) IS NOT NULL
        WHERE 1=1
    ) tnmummsctnsrvydtacal
    WHERE tnmummsctnsrvydtacal.RN = 1
    ;


-- DECLARE RAISE_EXT exception;
BEGIN
    o_PROCMSG := 'Y';
    o_PROCCODE :='false';

    SELECT  CAST(ATRB_VAL as numeric) INTO v_UNIT FROM TC_CODE WHERE  CODE_VAL='UNIT0001' AND USE_AT='Y' AND DELETE_AT='N';

    SELECT ROUTE_CODE, DIRECT_CODE, TRACK, SRVY_YEAR, SRVY_MT
      INTO v_ROUTE_CODE, v_DIRECT_CODE, v_TRACK, v_SRVY_YEAR, v_SRVY_MT
      FROM TN_MUMM_SCTN_SRVY_DTA
     WHERE SRVY_NO = p_SRVY_NO::int
     GROUP BY ROUTE_CODE, DIRECT_CODE, TRACK, SRVY_YEAR, SRVY_MT;

    RAISE NOTICE 'v_UNIT = %', v_UNIT;

    INSERT INTO TL_SRVY_DTA_LOG (
        PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
      , LOG_MSSAGE /* 조사_자료_로그.로그_메세지 */
      , CRTR_NO /* 조사_자료_로그.생성자_번호 */
      , BEGIN_DT /* 조사_자료_로그.시작_일시 */
      , SRVY_NO /* 조사_자료_로그.조사_번호 */
      , PROCESS_SE /* 조사_자료_로그.처리_구분 */
      , PROCESS_STTUS /* 조사_자료_로그.처리_상태 */
      , END_DT /* 조사_자료_로그.종료_일시 */
    )
    VALUES (
        nextval('SEQ_TL_SRVY_DTA_LOG')
      , '조사 자료 공간 보정 시작' /* 조사_자료_로그.로그_메세지 */
      , p_USER_NO::int /* 조사_자료_로그.생성자_번호 */
      , now() /* 조사_자료_로그.시작_일시 */
      , p_SRVY_NO::int /* 조사_자료_로그.조사_번호 */
      , 'PCSE0007' /* 조사_자료_로그.처리_구분 */
      , 'PCST0001' /* 조사_자료_로그.처리_상태 */
      , NULL /* 조사_자료_로그.종료_일시 */
    );

	o_PROCMSG :='CELL_10 테이블의 SRID 조회';
	/* CELL_10 테이블의 SRID 조회 */
	/* SELECT SRID INTO v_SRID FROM USER_SDO_GEOM_METADATA WHERE TABLE_NAME = 'CELL_10' AND COLUMN_NAME = 'G2_SPATIAL'; */
    SELECT st_srid(geom) INTO v_SRID FROM cell_10 limit 1;

    RAISE NOTICE 'o_PROCMSG = %', o_PROCMSG;

    SELECT COALESCE(MIN(STRTPT),0) - 1000 , COALESCE(MAX(ENDPT),0) + 1000
    INTO p_STRTPT , p_ENDPT
    FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
    WHERE 1 = 1
     AND tnmummsctnsrvydta.UPDT_AT = 'N'
     AND tnmummsctnsrvydta.USE_AT = 'Y'
     AND tnmummsctnsrvydta.DELETE_AT = 'N'
     AND tnmummsctnsrvydta.SRVY_NO = p_SRVY_NO::int
     AND tnmummsctnsrvydta.TRACE1_LO <> 0
     AND tnmummsctnsrvydta.TRACE1_LO IS NOT NULL
     AND tnmummsctnsrvydta.TRACE1_LA <> 0
     AND tnmummsctnsrvydta.TRACE1_LA IS NOT NULL;

    RAISE NOTICE 'p_SRVY_NO = %', p_SRVY_NO;
    RAISE NOTICE 'p_STRTPT = %', p_STRTPT;
    RAISE NOTICE 'p_ENDPT = %', p_ENDPT;

    --음수 처리
    p_STRTPT := GREATEST(p_STRTPT, 0 ,0)::numeric;
   --p_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
   --p_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;

    FOR v_ROW IN t_CURSOR
    LOOP
      BEGIN
       o_PROCMSG :='경위도 좌표 변환';
       v_STRTPT := v_ROW.STRTPT;
       v_ENDPT := v_ROW.ENDPT;

	  IF v_STRTPT > -1 AND v_ENDPT > -1
      THEN
         UPDATE TN_MUMM_SCTN_SRVY_DTA 			/* 최소_구간_조사_자료 */
            SET STRTPT = v_STRTPT, 				/* 최소_구간_조사_자료.시점 */
                ENDPT = v_ENDPT,					/* 최소_구간_조사_자료.종점 */
                UPDT_DT = now(),
                MSG_CODE = 'U',                 /*test*/
                MSG_TEST = 'UPDATE : 시종점을 변경하였습니다.'
          WHERE DTA_NO = v_ROW.DTA_NO  /* 조사_자료_현황.자료_번호 */
            AND USE_AT = 'Y'
            AND DELETE_AT = 'N';

         UPDATE TN_SRVY_DTA_STTUS 				/* 조사_자료_현황 */
            SET STRTPT = v_STRTPT, 				/* 조사_자료_현황.시점 */
                ENDPT = v_ENDPT,					/* 최소_구간_조사_자료.종점 */
                UPDT_DT = now()					/* 조사_자료_현황.종점 */
          WHERE DTA_NO = v_ROW.DTA_NO 			/* 조사_자료_현황.자료_번호 */
            AND USE_AT = 'Y'
            AND DELETE_AT = 'N';
      END IF;
     END;
    END LOOP;

    /* 시종점이 중복되는 행은 ROWID가 큰값을 제거 */
    /*
    o_PROCMSG :='시종점이 중복값 제거';
    RAISE NOTICE 'o_PROCMSG = %', o_PROCMSG;
    DELETE FROM TN_MUMM_SCTN_SRVY_DTA -- 최소_구간_조사_자료
    WHERE DTA_NO IN (SELECT DTA_NO
						 FROM (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID );

    DELETE FROM TN_SRVY_DTA_STTUS   -- 조사_자료_현황
    WHERE DTA_NO IN (SELECT DTA_NO
						 FROM (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID );
    */

    UPDATE TN_MUMM_SCTN_SRVY_DTA /* 최소_구간_조사_자료 */
      SET USE_AT = 'N'
        , DELETE_AT = 'Y'
        , MSG_CODE = 'D'                 /*test*/
        , MSG_TEST = 'DELETE : 시종점이 중복되어 삭제합니다.'
    WHERE DTA_NO IN (SELECT DTA_NO
                     FROM (SELECT T.*
                                , T.ctid RID
                                , MAX (T.ctid) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
                                , COUNT (*) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) CNT
                            FROM TN_MUMM_SCTN_SRVY_DTA T
                           WHERE USE_AT = 'Y'
                             AND DELETE_AT = 'N'
                             --AND T.UPDT_AT = 'N'
                             AND T.ROUTE_CODE = v_ROUTE_CODE
                             AND T.DIRECT_CODE = v_DIRECT_CODE
                             AND T.TRACK = v_TRACK
                             AND T.SRVY_YEAR = v_SRVY_YEAR
                             AND T.SRVY_MT = v_SRVY_MT
                           ) as SUB
					  WHERE CNT > 1 AND RID < MAX_RID );

    UPDATE TN_SRVY_DTA_STTUS /* 조사_자료_현황 */
	  SET USE_AT = 'N'
		, DELETE_AT = 'Y'
    WHERE DTA_NO IN (SELECT T.DTA_NO
                     FROM TN_MUMM_SCTN_SRVY_DTA T
                     WHERE T.USE_AT = 'N'
                        AND T.DELETE_AT = 'Y'
                        --AND T.UPDT_AT = 'N'
                        AND T.ROUTE_CODE = v_ROUTE_CODE
                        AND T.DIRECT_CODE = v_DIRECT_CODE
                        AND T.TRACK = v_TRACK
                        AND T.SRVY_YEAR = v_SRVY_YEAR
                        AND T.SRVY_MT = v_SRVY_MT
                 );

             /*
             (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID )
             */

    o_PROCMSG :='없는 조사자료 값을 시종점 다음 값으로 입력';
    RAISE NOTICE 'o_PROCMSG = %', o_PROCMSG;
	/* 없는 조사자료 값은 시종점 다음값으로 강제 입력 */
    FOR v_ROW_GRP IN ( SELECT ROUTE_CODE			/* 최소_구간_조사_자료.노선_코드 */
                          , DIRECT_CODE			/* 최소_구간_조사_자료.행선_코드 */
                          , TRACK				/* 최소_구간_조사_자료.차로 */
                          , SRVY_YEAR
                          , SRVY_MT
                          , MIN(STRTPT) STRTPT		/* 최소_구간_조사_자료.시점 */
                          , MAX(ENDPT) ENDPT		/* 최소_구간_조사_자료.종점 */
                          --, DTA_NO			/* 최소_구간_조사_자료.자료_번호 */
                        FROM TN_MUMM_SCTN_SRVY_DTA /* 최소_구간_조사_자료 */
                       WHERE UPDT_AT = 'N'
                         AND SRVY_NO = p_SRVY_NO::int
                         AND USE_AT = 'Y'
                         AND DELETE_AT = 'N'
                       GROUP BY ROUTE_CODE
                              , DIRECT_CODE
                              , TRACK
                              , SRVY_YEAR
                              , SRVY_MT
                             -- , DTA_NO
                   )
    LOOP
    v_INDEX := 0 ;
        FOR v_ROW IN (SELECT   *
                        FROM TN_MUMM_SCTN_SRVY_DTA	/* 최소_구간_조사_자료 */
                        WHERE USE_AT = 'Y'
                          AND DELETE_AT = 'N'
                          --AND UPDT_AT = 'N'
                          AND ROUTE_CODE = v_ROW_GRP.ROUTE_CODE
                          AND DIRECT_CODE = v_ROW_GRP.DIRECT_CODE
                          AND TRACK = v_ROW_GRP.TRACK
                          AND SRVY_YEAR = v_ROW_GRP.SRVY_YEAR
                          AND SRVY_MT = v_ROW_GRP.SRVY_MT
                          AND SRVY_NO = p_SRVY_NO::int 
                         -- AND DTA_NO = v_ROW_GRP.DTA_NO
                        ORDER BY STRTPT, ENDPT)
        LOOP

            IF v_ROW.STRTPT <> v_ROW_GRP.STRTPT + v_INDEX * v_UNIT
            THEN
                v_DTA_NO := nextval('SEQ_TN_MUMM_SCTN_SRVY_DTA');
               -- v_DTA_STTUS_NO := SEQ_TN_SRVY_DTA_STTUS.NEXTVAL;
                RAISE NOTICE '%', v_DTA_NO;
                INSERT INTO TN_SRVY_DTA_STTUS   	/* 조사_자료_현황 */
					(  DTA_STTUS_NO					/* 조사_자료_현황.자료_현황_번호 */
					 , SRVY_YEAR					/* 조사_자료_현황.조사_년도 */
					 , ROUTE_CODE					/* 조사_자료_현황.노선_코드 */
					 , DIRECT_CODE					/* 조사_자료_현황.행선_코드 */
					 , TRACK						/* 조사_자료_현황.차로  */
					 , STRTPT 						/* 조사_자료_현황.시점 */
					 , ENDPT							/* 조사_자료_현황.종점 */
					 , SRVY_KND						/* 조사_자료_현황.조사_종류 */
					 , SRVY_MT						/* 조사_자료_현황.조사_월 */
					 , EXCEL_DTA_SEQ				/* 조사_자료_현황.엑셀_자료_SEQ */
					 , SRVY_NO						/* 조사_자료_현황.조사_번호 */
					 , DTA_NO						/* 조사_자료_현황.자료_번호 */
					 , SM_PROCESS_AT			/* 조사_자료_현황.집계_구간_산출_여부 */
					 , PRDTMDL_PROCESS_AT		/* 조사_자료_현황.예측_모델_적용_여부 */
					 , DELETE_AT					/* 조사_자료_현황.삭제_여부 */
					 , USE_AT						/* 조사_자료_현황.사용_여부 */
					 , CRTR_NO						/* 조사_자료_현황.생성자_번호 */
					 , CREAT_DT						/* 조사_자료_현황.생성_일자 */
					 , UPDUSR_NO					/* 조사_자료_현황.수정자_번호 */
					 , UPDT_DT						/* 조사_자료_현황.수정_일자 */
					)
					SELECT nextval('SEQ_TN_SRVY_DTA_STTUS')
						 , SRVY_YEAR
						 , ROUTE_CODE
						 , DIRECT_CODE
						 , TRACK
						 , v_ROW_GRP.STRTPT + v_INDEX * v_UNIT
						 , v_ROW_GRP.STRTPT + (v_INDEX + 1) * v_UNIT
						 , SRVY_KND
						 , SRVY_MT
						 , EXCEL_DTA_SEQ
						 , SRVY_NO
						 , v_DTA_NO
						 , SM_PROCESS_AT
						 , PRDTMDL_PROCESS_AT
						 , 'N'
						 , 'Y'
						 , P_USER_NO
						 , now()
						 , P_USER_NO
						 , now()
					 FROM TN_SRVY_DTA_STTUS
					WHERE DTA_NO = v_ROW.DTA_NO::int 
					  AND USE_AT = 'Y'
					  AND DELETE_AT = 'N';

          INSERT INTO TN_MUMM_SCTN_SRVY_DTA			/* 최소_구간_조사_자료 */
					(     DTA_NO							/* 최소_구간_조사_자료.자료_번호 */
						, SRVY_NO							/* 최소_구간_조사_자료.조사_번호 */
						, SRVY_YEAR							/* 최소_구간_조사_자료.조사_년도 */
						, ROUTE_CODE						/* 최소_구간_조사_자료.노선_코드 */
						, DIRECT_CODE						/* 최소_구간_조사_자료.행선_코드 */
						, TRACK							/* 최소_구간_조사_자료.차로 */
						, STRTPT								/* 최소_구간_조사_자료.시점 */
						, ENDPT								/* 최소_구간_조사_자료.종점 */
						, IRI_VAL							/* 최소_구간_조사_자료.종단평탄성_값 */
						, RD_VAL							/* 최소_구간_조사_자료.소성변형_값 */
						, VRTCAL_CR							/* 최소_구간_조사_자료.종방향_균열 */
						, HRZNTAL_CR						/* 최소_구간_조사_자료.횡방향_균열 */
						, CNSTRCT_JOINT_CR					/* 최소_구간_조사_자료.시공_줄눈_군열 */
						, TRTS_BAC_CR						/* 최소_구간_조사_자료.거북등_균열 */
						, PTCHG_CR							/* 최소_구간_조사_자료.패칭_균열 */
						, BLOCK_CR                          /* 최소_구간_조사_자료.블럭_균열 */
                        , CR_LT /* 최소_구간_조사_자료.균열_길이 */
                        , CR_WID /* 최소_구간_조사_자료.균열_폭 */
                        , AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                        , AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                        , AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                        , BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                        , BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                        , BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                        , LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                        , LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                        , LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                        , TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                        , TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                        , TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                        , AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                        , BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                        , LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                        , TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                        , PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                        , POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                        , RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                        , RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                        , RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                        , XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                        , RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                        , SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                        , CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                        , CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                        , RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                        , NHPCI /* 최소_구간_조사_자료.NHPCI */
                        , GPCI /* 최소_구간_조사_자료.GPCI */
                        , SPI /* 최소_구간_조사_자료.SPI */
						, PC_GRAD							/* 최소_구간_조사_자료.포장상태등급 */
						, CNTL_DFECT						/* 최소_구간_조사_자료.지배_결함 */
                        , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                        , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                        , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
						, SRVY_KND							/* 최소_구간_조사_자료.조사_종류 */
						, SRVY_MT							/* 최소_구간_조사_자료.조사_월 */
						, MEMO								/* 최소_구간_조사_자료.메모 */
						, SRVY_DE							/* 최소_구간_조사_자료.조사_일자 */
						, TRACE1_LA							/* 최소_구간_조사_자료.TRACE1_LA */
						, TRACE1_LO							/* 최소_구간_조사_자료.TRACE1_LO */
						, TRACE2_LA							/* 최소_구간_조사_자료.TRACE2_LA */
						, TRACE2_LO							/* 최소_구간_조사_자료.TRACE2_LO */
						, SRVY_NM							/* 최소_구간_조사_자료.조사_명 */
						, EXMNR_NM							/* 최소_구간_조사_자료.조사자_명 */
						, SCTN_STRTPT_DC						/* 최소_구간_조사_자료.구간_시점_설명 */
						, SCTN_ENDPT_DC						/* 최소_구간_조사_자료.구간_종점_설명 */
						, ROAD_NM							/* 최소_구간_조사_자료.도로_명 */
						, FRNT_IMG_FILE_NM					/* 최소_구간_조사_자료.전방_이미지_파일_명 */
						, FRNT_IMG_FILE_COURS				/* 최소_구간_조사_자료.전방_이미지_파일_경로 */
						, CR_IMG_FILE_NM					/* 최소_구간_조사_자료.균열_이미지_파일_명 */
						, CR_IMG_FILE_COURS					/* 최소_구간_조사_자료.균열_이미지_파일_경로 */
						, RDSRFC_IMG_FILE_NM_1				/* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
						, RDSRFC_IMG_FILE_NM_2				/* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
						, EXCEL_DTA_SEQ						/* 최소_구간_조사_자료.엑셀_자료_SEQ */
						, FRMULA_NO							/* 최소_구간_조사_자료.수식_번호 */
						, POTHOLE_CR						/* 최소_구간_조사_자료.포트홀_군열 */
						, RI_IDX							/* 최소_구간_조사_자료.평탄성_지수 */
						, UPDT_AT							/* 최소_구간_조사_자료.수정_여부 */
						, DELETE_AT							/* 최소_구간_조사_자료.삭제_여부 */
						, USE_AT							/* 최소_구간_조사_자료.사용_여부 */
						, CRTR_NO							/* 최소_구간_조사_자료.생성자_번호 */
						, CREAT_DT							/* 최소_구간_조사_자료.생성_일시 */
						, UPDUSR_NO							/* 최소_구간_조사_자료.수정자_번호 */
						, UPDT_DT							/* 최소_구간_조사_자료.수정_일시 */
                        , MSG_CODE                 /*test*/
                        , MSG_TEST
                        , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
					)
					SELECT v_DTA_NO
                        , SRVY_NO
                        , SRVY_YEAR
                        , ROUTE_CODE
                        , DIRECT_CODE
                        , TRACK
                        , v_ROW_GRP.STRTPT + v_INDEX * v_UNIT
						, v_ROW_GRP.STRTPT + (v_INDEX + 1) * v_UNIT
                        , IRI_VAL
                        , RD_VAL
                        , VRTCAL_CR
                        , HRZNTAL_CR
                        , CNSTRCT_JOINT_CR
                        , TRTS_BAC_CR
                        , PTCHG_CR
                        , BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                        , CR_LT /* 최소_구간_조사_자료.균열_길이 */
                        , CR_WID /* 최소_구간_조사_자료.균열_폭 */
                        , AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                        , AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                        , AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                        , BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                        , BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                        , BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                        , LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                        , LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                        , LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                        , TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                        , TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                        , TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                        , AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                        , BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                        , LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                        , TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                        , PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                        , POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                        , RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                        , RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                        , RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                        , XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                        , RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                        , SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                        , CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                        , CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                        , RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                        , NHPCI /* 최소_구간_조사_자료.NHPCI */
                        , GPCI /* 최소_구간_조사_자료.GPCI */
                        , SPI /* 최소_구간_조사_자료.SPI */
                        , PC_GRAD
                        , CNTL_DFECT
                        , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                        , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                        , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                        , SRVY_KND
                        , SRVY_MT
                        , MEMO
                        , SRVY_DE
                        , TRACE1_LA
                        , TRACE1_LO
                        , TRACE2_LA
                        , TRACE2_LO
                        , SRVY_NM
                        , EXMNR_NM
                        , SCTN_STRTPT_DC
                        , SCTN_ENDPT_DC
                        , ROAD_NM
						, FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
						, FRNT_IMG_FILE_COURS /* 최소_구간_조사_자료.전방_이미지_파일_경로 */
						, CR_IMG_FILE_NM /* 최소_구간_조사_자료.균열_이미지_파일_명 */
						, CR_IMG_FILE_COURS /* 최소_구간_조사_자료.균열_이미지_파일_경로 */
                        , RDSRFC_IMG_FILE_NM_1
                        , RDSRFC_IMG_FILE_NM_2
                        , EXCEL_DTA_SEQ
                        , FRMULA_NO
                        , POTHOLE_CR
                        , RI_IDX
                        , 'N'
                        , 'N'
                        , 'Y'
                        , P_USER_NO
                        , now()
                        , P_USER_NO
                        , now()
                        , 'I'                 /*test*/
                        , 'INSERT : 조사자료 값이 없어 시종점 다음 값으로 입력하였습니다.'
                        , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
					 FROM TN_MUMM_SCTN_SRVY_DTA
					WHERE DTA_NO = v_ROW.DTA_NO
					  AND USE_AT = 'Y'
					  AND DELETE_AT = 'N';

                v_INDEX := v_INDEX + 1;
            END IF;
            v_INDEX := v_INDEX + 1;
        END LOOP;
    END LOOP;

    o_PROCMSG :='조사 자료 공간 보정 처리 여부 등록';

    UPDATE TN_MUMM_SCTN_SRVY_DTA
       SET UPDT_AT = 'Y'
     WHERE UPDT_AT = 'N'
	   AND USE_AT = 'Y'
	   AND DELETE_AT = 'N'
     AND SRVY_NO = p_SRVY_NO::int;

    /* 조사_자료_엑셀.마스터 정보 수정 */
    UPDATE TN_SRVY_DTA_EXCEL SET
          GPS_CORTN_PROCESS_AT = 'Y' /* 조사_자료_엑셀.GPS_보정_처리_여부 */
        , UPDUSR_NO = p_USER_NO::int /* 조사_자료_엑셀.수정자_번호 */
        , UPDT_DT = now() /* 조사_자료_엑셀.수정_일시 */
        WHERE 1 = 1
        AND SRVY_NO = p_SRVY_NO::int /* 조사_자료_엑셀.조사_번호 */
        AND DELETE_AT = 'N' /* 조사_자료_엑셀.삭제_여부 */
        AND USE_AT = 'Y' /* 조사_자료_엑셀.사용_여부 */
        ;


    o_PROCMSG :='조사 자료 공간 보정 처리 완료';
    o_PROCCODE :='true';

    /* 조사_자료_로그 수정 */
    UPDATE TL_SRVY_DTA_LOG SET
    LOG_MSSAGE = '조사 자료 공간 보정 완료' /* 조사_자료_로그.로그_메세지 */
    , PROCESS_STTUS = 'PCST0002' /* 조사_자료_로그.처리_상태 */
    , END_DT = now() /* 조사_자료_로그.종료_일시 */
    WHERE PROCESS_SE = 'PCSE0007'
	  AND PROCESS_STTUS = 'PCST0001'
	  AND END_DT is null
    AND SRVY_NO = p_SRVY_NO::int;


    RAISE NOTICE '%', '조사자료 데이터 공간 보정 프로시저 정상 종료';

EXCEPTION
    WHEN OTHERS
    THEN
		o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';

        /* 조사_자료_로그 수정 */
		UPDATE TL_SRVY_DTA_LOG SET
            LOG_MSSAGE = '조사 자료 공간 보정 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
            , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
            , END_DT = now() /* 조사_자료_로그.종료_일시 */
		WHERE PROCESS_SE = 'PCSE0007'
		  AND END_DT is null
		  AND SRVY_NO = p_SRVY_NO;

        -- plpgsql exception handling..
        GET STACKED DIAGNOSTICS err_context = PG_EXCEPTION_CONTEXT;
        RAISE INFO 'Error Name:%',SQLERRM;
        RAISE INFO 'Error State:%', SQLSTATE;
        RAISE INFO 'Error Context:%', err_context;
      RAISE;
END;

$function$;



