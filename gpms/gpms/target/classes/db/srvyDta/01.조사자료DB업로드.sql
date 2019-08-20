/**
 * [포장상태 평가]
 * 조사자료 파일 DB 업로드(EXCEL) & 포장상태 평가 
 * 
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_SRVY_NO : 조사자료 번호
 * @PARAM p_FRMULA_NM : 적용 수식 명
 * @PARAM P_ROW_COUNT : 전체 데이터 개수
 * @PARAM p_RECORDSET : 데이터 셋(XML)
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
CREATE OR REPLACE PROCEDURE      "PRC_SAVESURVEYDATA" (
/*
    조사자료 파일 등록 및 포장평가 수식 적용
    
    생성일 : 2017-08-03
    생성자 : muhanit.kr
*/
p_USER_NO          NUMBER,
p_SRVY_NO            NUMBER,
p_FRMULA_NM        VARCHAR2, 
P_ROW_COUNT             NUMBER,
p_RECORDSET        IN   CLOB,
p_MODE VARCHAR2,
o_PROCCODE     OUT     VARCHAR2,
o_PROCMSG     OUT     VARCHAR2
)
AS
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
v_SRID USER_SDO_GEOM_METADATA.SRID%TYPE;
v_DIMINFO USER_SDO_GEOM_METADATA.DIMINFO%TYPE;
v_G2_LONLAT  SDO_GEOMETRY;
v_G2_LONLAT_BUFFER SDO_GEOMETRY;
v_BASE_IDX_VAR NUMBER;
v_CR_IDX_VAR NUMBER;
v_RD_IDX_VAR NUMBER;
v_IRI_IDX_VAR NUMBER;
v_EXPONENT_VAR NUMBER;

v_DMG_CUZ DMG_CUZ;

v_AC_IDX NUMBER;
v_BC_IDX NUMBER;
v_LC_IDX NUMBER;
v_TC_IDX NUMBER;
v_PTCHG_IDX NUMBER;
v_POT_IDX NUMBER;

v_USE_AC_FN VARCHAR2(1);
v_USE_BC_FN VARCHAR2(1);
v_USE_LC_FN VARCHAR2(1);
v_USE_TC_FN VARCHAR2(1);
v_USE_RD_FN VARCHAR2(1);
v_USE_PTCHG_FN VARCHAR2(1);
v_USE_POT_FN VARCHAR2(1);
v_USE_RCI_FN VARCHAR2(1);
v_USE_SCR_FN VARCHAR2(1);
v_USE_GPCI_FN VARCHAR2(1);
v_existData VARCHAR2(10);
V_TEMPMSG VARCHAR2(4000);    
xtype        SYS.XMLTYPE;
   
-- DECLARE RAISE_EXT exception;
BEGIN
    xtype := SYS.XMLTYPE.createxml (p_RECORDSET);                                               
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
    


    SELECT SRID, DIMINFO into v_SRID, v_DIMINFO FROM USER_SDO_GEOM_METADATA WHERE TABLE_NAME = 'TN_MUMM_SCTN_SRVY_DTA' AND COLUMN_NAME = 'G2_LONLAT_BUFFER';
    select SEQ_TL_SRVY_DTA_LOG.NEXTVAL into v_PROCESS_LOG_NO from dual;
    
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
			v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
			, '조사 자료 등록 및 상태 평가 처리 시작' /* 조사_자료_로그.로그_메세지 */
			, p_USER_NO /* 조사_자료_로그.생성자_번호 */
			, SYSDATE /* 조사_자료_로그.시작_일시 */
			, p_SRVY_NO /* 조사_자료_로그.조사_번호 */
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
          FROM DUAL; 
            
        
    
       
    FOR v_erow IN
        (SELECT  TRIM( REPLACE( josadata.EXTRACT ('//XLData/SRVY_YEAR/text()').getstringval(),'"','') ) AS SRVY_YEAR, /* 최소_구간_조사_자료.조사_년도 */
        TRIM( REPLACE(josadata.EXTRACT ('//XLData/SRVY_MT/text()').getstringval(),'"','') ) AS SRVY_MT, /* 최소_구간_조사_자료.조사_월 */
        TRIM( REPLACE(josadata.EXTRACT ('//XLData/ROUTE_CODE/text()').getstringval(),'"','') ) AS ROUTE_CODE, /* 최소_구간_조사_자료.노선_코드 */
        TRIM( REPLACE(josadata.EXTRACT ('//XLData/DIRECT_CODE/text()').getstringval(),'"','') ) AS DIRECT_CODE, /* 최소_구간_조사_자료.행선_코드 */
        TRIM( REPLACE(josadata.EXTRACT ('//XLData/TRACK/text()').getstringval() ,'"','')) AS TRACK, /* 최소_구간_조사_자료.차로 */
        ROUND(TO_NUMBER(TRIM( REPLACE(josadata.EXTRACT ('//XLData/STRTPT/text()').getstringval(),'"','') )),0) AS STRTPT, /* 최소_구간_조사_자료.시점 */
        ROUND(TO_NUMBER(TRIM( REPLACE(josadata.EXTRACT ('//XLData/ENDPT/text()').getstringval(),'"','') )),0) AS ENDPT, /* 최소_구간_조사_자료.종점 */
        TRIM( josadata.EXTRACT ('//XLData/CR_VAL/text()').getstringval() ) AS CR_VAL, /* 최소_구간_조사_자료.표면손상값 */
        TRIM( josadata.EXTRACT ('//XLData/RD_VAL/text()').getstringval() ) AS RD_VAL, /* 최소_구간_조사_자료.소성변형_값 */
        TRIM( josadata.EXTRACT ('//XLData/IRI_VAL/text()').getstringval() ) AS IRI_VAL, /* 최소_구간_조사_자료.종단평탄성_값 */
        TRIM( josadata.EXTRACT ('//XLData/VRTCAL_CR/text()').getstringval() ) AS VRTCAL_CR, /* 최소_구간_조사_자료.종방향_균열 */
        TRIM( josadata.EXTRACT ('//XLData/HRZNTAL_CR/text()').getstringval() ) AS HRZNTAL_CR, /* 최소_구간_조사_자료.횡방향_균열 */
        TRIM( josadata.EXTRACT ('//XLData/CNSTRCT_JOINT_CR/text()').getstringval() ) AS CNSTRCT_JOINT_CR,  /* 최소_구간_조사_자료.시공_줄눈_균열 */
        TRIM( josadata.EXTRACT ('//XLData/TRTS_BAC_CR/text()').getstringval() ) AS TRTS_BAC_CR, /* 최소_구간_조사_자료.거북등_균열 */
        TRIM( josadata.EXTRACT ('//XLData/PTCHG_CR/text()').getstringval() ) AS PTCHG_CR, /* 최소_구간_조사_자료.패칭_균열 */
        TRIM( josadata.EXTRACT ('//XLData/POTHOLE_CR/text()').getstringval() ) AS POTHOLE_CR, /* 최소_구간_조사_자료.포트홀_개수 */
        TRIM( josadata.EXTRACT ('//XLData/RDSRFC_IMG_FILE_NM_1/text()').getstringval() ) AS RDSRFC_IMG_FILE_NM_1, /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
        TRIM( josadata.EXTRACT ('//XLData/RDSRFC_IMG_FILE_NM_2/text()').getstringval() ) AS RDSRFC_IMG_FILE_NM_2, /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
        TRIM( josadata.EXTRACT ('//XLData/FRNT_IMG_FILE_NM/text()').getstringval() ) AS FRNT_IMG_FILE_NM, /* 최소_구간_조사_자료.전방_이미지_파일_명 */
        TRIM( josadata.EXTRACT ('//XLData/MEMO/text()').getstringval() ) AS MEMO, /* 최소_구간_조사_자료.메모 */
        TRIM( josadata.EXTRACT ('//XLData/SRVY_DE/text()').getstringval() ) AS SRVY_DE, /* 최소_구간_조사_자료.조사_일자 */
        TRIM( josadata.EXTRACT ('//XLData/SRVY_KND/text()').getstringval() ) AS SRVY_KND, /* 최소_구간_조사_자료.조사_종류 */
        ROUND(TO_NUMBER(TRIM( josadata.EXTRACT ('//XLData/TRACE1_LA/text()').getstringval() )),6) AS TRACE1_LA,/* 최소_구간_조사_자료.TRACE1_LA */
        ROUND(TO_NUMBER(TRIM( josadata.EXTRACT ('//XLData/TRACE1_LO/text()').getstringval() )),6) AS TRACE1_LO, /* 최소_구간_조사_자료.TRACE1_LO */
        ROUND(TO_NUMBER(TRIM( josadata.EXTRACT ('//XLData/TRACE2_LA/text()').getstringval() )),6) AS TRACE2_LA, /* 최소_구간_조사_자료.TRACE2_LA */
        ROUND(TO_NUMBER(TRIM( josadata.EXTRACT ('//XLData/TRACE2_LO/text()').getstringval() )),6) AS TRACE2_LO, /* 최소_구간_조사_자료.TRACE2_LO */
        TRIM( josadata.EXTRACT ('//XLData/SRVY_NM/text()').getstringval() ) AS SRVY_NM, /* 최소_구간_조사_자료.조사_명 */
        TRIM( josadata.EXTRACT ('//XLData/EXMNR_NM/text()').getstringval() ) AS EXMNR_NM,/* 최소_구간_조사_자료.조사자_명 */
        TRIM( josadata.EXTRACT ('//XLData/SCTN_STRTPT_DC/text()').getstringval() ) AS SCTN_STRTPT_DC, /* 최소_구간_조사_자료.구간_시점_설명 */
        TRIM( josadata.EXTRACT ('//XLData/SCTN_ENDPT_DC/text()').getstringval() ) AS SCTN_ENDPT_DC, /* 최소_구간_조사_자료.구간_종점_설명 */
        TRIM( josadata.EXTRACT ('//XLData/ROAD_NM/text()').getstringval() ) AS ROAD_NM , /* 최소_구간_조사_자료.도로_명 */
        TRIM( josadata.EXTRACT ('//XLData/BLOCK_CR/text()').getstringval() ) AS BLOCK_CR, /* 최소_구간_조사_자료.블럭_균열 */
        TRIM( josadata.EXTRACT ('//XLData/CR_LT/text()').getstringval() ) AS CR_LT, /* 최소_구간_조사_자료.균열_길이 */
        TRIM( josadata.EXTRACT ('//XLData/CR_WID/text()').getstringval() ) AS CR_WID, /* 최소_구간_조사_자료.균열_폭 */
        TRIM( josadata.EXTRACT ('//XLData/XCR/text()').getstringval() ) AS XCR, /* 최소_구간_조사_자료.NHPCI_균열 */
        TRIM( josadata.EXTRACT ('//XLData/SPI/text()').getstringval() ) AS SPI, /* 최소_구간_조사_자료.SPI */
        TRIM( josadata.EXTRACT ('//XLData/NHPCI/text()').getstringval() ) AS NHPCI, /* 최소_구간_조사_자료.NHPCI */
        TRIM( josadata.EXTRACT ('//XLData/GPCI/text()').getstringval() ) AS GPCI, /* 최소_구간_조사_자료.GPCI */
        TRIM( josadata.EXTRACT ('//XLData/AC_LOW/text()').getstringval() ) AS AC_LOW, /* 최소_구간_조사_자료.거북등균열_LOW */
        TRIM( josadata.EXTRACT ('//XLData/AC_MED/text()').getstringval() ) AS AC_MED, /* 최소_구간_조사_자료.거북등균열_MED */
        TRIM( josadata.EXTRACT ('//XLData/AC_HI/text()').getstringval() ) AS AC_HI, /* 최소_구간_조사_자료.거북등균열_HI */
        TRIM( josadata.EXTRACT ('//XLData/LC_LOW/text()').getstringval() ) AS LC_LOW, /* 최소_구간_조사_자료.종방향균열_LOW */
        TRIM( josadata.EXTRACT ('//XLData/LC_MED/text()').getstringval() ) AS LC_MED, /* 최소_구간_조사_자료.종방향균열_MED */
        TRIM( josadata.EXTRACT ('//XLData/LC_HI/text()').getstringval() ) AS LC_HI, /* 최소_구간_조사_자료.종방향균열_HI */
        TRIM( josadata.EXTRACT ('//XLData/TC_LOW/text()').getstringval() ) AS TC_LOW, /* 최소_구간_조사_자료.횡방향균열_LOW */
        TRIM( josadata.EXTRACT ('//XLData/TC_MED/text()').getstringval() ) AS TC_MED, /* 최소_구간_조사_자료.횡방향균열_MED */
        TRIM( josadata.EXTRACT ('//XLData/TC_HI/text()').getstringval() ) AS TC_HI, /* 최소_구간_조사_자료.횡방향균열_HI */
        TRIM( josadata.EXTRACT ('//XLData/AC_IDX/text()').getstringval() ) AS AC_IDX, /* 최소_구간_조사_자료.거북등균열_지수 */
        TRIM( josadata.EXTRACT ('//XLData/BC_IDX/text()').getstringval() ) AS BC_IDX, /* 최소_구간_조사_자료.블럭균열_지수 */
        TRIM( josadata.EXTRACT ('//XLData/LC_IDX/text()').getstringval() ) AS LC_IDX, /* 최소_구간_조사_자료.종방향균열_지수 */
        TRIM( josadata.EXTRACT ('//XLData/TC_IDX/text()').getstringval() ) AS TC_IDX, /* 최소_구간_조사_자료.횡방향균열_지수 */
        TRIM( josadata.EXTRACT ('//XLData/PTCHG_IDX/text()').getstringval() ) AS PTCHG_IDX, /* 최소_구간_조사_자료.패칭_지수 */
        TRIM( josadata.EXTRACT ('//XLData/RD_LOW/text()').getstringval() ) AS RD_LOW, /* 최소_구간_조사_자료.소성변형_LOW */
        TRIM( josadata.EXTRACT ('//XLData/RD_MED/text()').getstringval() ) AS RD_MED, /* 최소_구간_조사_자료.소성변형_MED */
        TRIM( josadata.EXTRACT ('//XLData/RD_HI/text()').getstringval() ) AS RD_HI, /* 최소_구간_조사_자료.소성변형_HI */
        TRIM( josadata.EXTRACT ('//XLData/BLOCK_CR_LOW/text()').getstringval() ) AS BLOCK_CR_LOW, /* 최소_구간_조사_자료.블럭_균열_LOW */
        TRIM( josadata.EXTRACT ('//XLData/BLOCK_CR_MED/text()').getstringval() ) AS BLOCK_CR_MED, /* 최소_구간_조사_자료.블럭_균열_MED */
        TRIM( josadata.EXTRACT ('//XLData/BLOCK_CR_HI/text()').getstringval() ) AS BLOCK_CR_HI, /* 최소_구간_조사_자료.블럭_균열_HI */
        TRIM( josadata.EXTRACT ('//XLData/POTHOLE_IDX/text()').getstringval() ) AS POTHOLE_IDX, /* 최소_구간_조사_자료.포트홀_지수 */
        TRIM( josadata.EXTRACT ('//XLData/RD_IDX/text()').getstringval() ) AS RD_IDX, /* 최소_구간_조사_자료.소성변형_지수 */
        TRIM( josadata.EXTRACT ('//XLData/RCI/text()').getstringval() ) AS RCI, /* 최소_구간_조사_자료.RCI */
        TRIM( josadata.EXTRACT ('//XLData/SCR/text()').getstringval() ) AS SCR /* 최소_구간_조사_자료.SCR */
        FROM TABLE (XMLSEQUENCE (xtype.EXTRACT ('/NewDataSet/XLData'))) josadata)
    LOOP
        o_PROCMSG :='DATAEXISTS';  
        v_ROW_INDEX := v_ROW_INDEX+1;
        v_EXCEL_DTA_SEQ := v_EXCEL_DTA_SEQ+1;
        --v_MIN_STRTPT TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
        --v_MAX_ENDPT TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;      
        v_NHPCI := 0;
        v_DTA_NO :=-1;
        v_existData :='N';
        IF v_ROW_INDEX = 1 THEN
            v_MIN_STRTPT := v_erow.STRTPT;
            v_MAX_ENDPT := v_erow.ENDPT;
        ELSE
            v_MIN_STRTPT :=GREATEST(LEAST(v_MIN_STRTPT, v_erow.STRTPT),0);
            v_MAX_ENDPT :=GREATEST(v_MAX_ENDPT, v_erow.ENDPT,0);    
        END IF;
        
        v_SRVY_NM := v_erow.SRVY_NM;
        v_EXMNR_NM := v_erow.EXMNR_NM;
        v_ROUTE_CODE := v_erow.ROUTE_CODE;
        v_DIRECT_CODE := v_erow.DIRECT_CODE;
        v_TRACK := v_erow.TRACK;
        v_SRVY_DE := v_erow.SRVY_DE;
       
        
        --o_PROCMSG :='nhpci 도출';
        --nhpci 도출
        --select   1/POWER((0.33+ (0.003 * CR)+ (0.004 * RD) + ( 0.0183* IRI ) ),2) INTO v_PCI        from dual;
        -- ELECT NVL( ROUND(  1/POWER((v_BASE_IDX_VAR+ (v_CR_IDX_VAR  * TO_NUMBER( v_erow.XCR))+ (v_RD_IDX_VAR * TO_NUMBER(v_erow.RD_VAL)) + ( v_IRI_IDX_VAR  * TO_NUMBER(v_erow.IRI_VAL) ) ), v_EXPONENT_VAR ), 2),-1) 
        --INTO v_NHPCI  FROM DUAL;
        --v_PC_GRAD := 'E';
        --o_PROCMSG :='nhpci GRADE 도출';
        --SELECT FN_GET_IDX_GRADE(NVL(p_FRMULA_NM,'NHPCI'), v_erow.SRVY_YEAR, v_NHPCI) INTO v_PC_GRAD FROM DUAL;
        
         o_PROCMSG :='GPCI 지수 도출';
        SELECT DECODE( v_USE_AC_FN, 'N', v_erow.AC_IDX, 'Y', FN_GET_AC_IDX( v_erow.AC_LOW,v_erow.AC_MED,v_erow.AC_HI) ) /* AC_IDX */
             , DECODE( v_USE_BC_FN, 'N', v_erow.BC_IDX, 'Y', FN_GET_BC_IDX( v_erow.BLOCK_CR_LOW,v_erow.BLOCK_CR_MED,v_erow.BLOCK_CR_HI) ) /* BC_IDX */
             , DECODE( v_USE_LC_FN, 'N', v_erow.LC_IDX, 'Y', FN_GET_LC_IDX( v_erow.LC_LOW,v_erow.LC_MED,v_erow.LC_HI) ) /* LC_IDX */
             , DECODE( v_USE_TC_FN, 'N', v_erow.TC_IDX, 'Y', FN_GET_TC_IDX( v_erow.TC_LOW,v_erow.TC_MED,v_erow.TC_HI) ) /* TC_IDX */
             , DECODE( v_USE_PTCHG_FN, 'N', v_erow.PTCHG_IDX, 'Y', FN_GET_PATCH_IDX( v_erow.PTCHG_CR) ) /* PTCHG_IDX */
             , DECODE( v_USE_POT_FN, 'N', v_erow.POTHOLE_IDX, 'Y', FN_GET_POT_IDX( v_erow.POTHOLE_CR) ) /* POT_IDX */
             , DECODE( v_USE_RD_FN, 'N', v_erow.RD_IDX, 'Y', FN_GET_RUT_IDX( v_erow.RD_LOW,v_erow.RD_MED,v_erow.RD_HI) ) /* RD_IDX */
             , DECODE( v_USE_RCI_FN, 'N', v_erow.RCI, 'Y', FN_GET_RCI( v_erow.IRI_VAL ) ) /* RCI */
        INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI             
        FROM DUAL;             
        
        SELECT DECODE( v_USE_SCR_FN, 'N', v_erow.SCR, 'Y', FN_GET_SCR( v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX) ) /* SCR */
        INTO v_SCR
        FROM DUAL;
         
        o_PROCMSG :='GPCI 도출';
        SELECT DECODE( v_USE_GPCI_FN, 'N', v_erow.GPCI, 'Y', FN_GET_GPCI( v_SCR,v_RCI) ) /* GPCI */
        INTO v_GPCI 
        FROM DUAL;
        
        
        o_PROCMSG :='GPCI GRADE 도출';
        SELECT FN_GET_IDX_GRADE('GPCI', v_erow.SRVY_YEAR, v_GPCI) INTO v_PC_GRAD FROM DUAL;
           
        
        --SELECT NVL(ROAD_SE,'') INTO v_ROAD_SE FROM TN_ROUTE_INFO WHERE ROUTE_CODE = v_erow.ROUTE_CODE;
        
        IF v_erow.SRVY_YEAR IS NOT NULL
        THEN
            
            BEGIN
            o_PROCMSG :='최소_구간_조사_자료 조회';
            if p_MODE = 'DEBUG' THEN
                V_TEMPMSG := v_erow.SRVY_YEAR || '='|| v_erow.ROUTE_CODE ||'='|| v_erow.DIRECT_CODE || '='||v_erow.TRACK ||'='|| TO_NUMBER(v_erow.STRTPT) ||'='|| TO_NUMBER(v_erow.ENDPT);            
                DBMS_OUTPUT.PUT_LINE(V_TEMPMSG);
            END IF;
            
            /* 최소_구간_조사_자료 조회 */
            SELECT
                NVL(MAX(tnmummsctnsrvydta.DTA_NO),-1) /* 최소_구간_조사_자료.자료_번호 */
                INTO v_DTA_NO        
            FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
                WHERE 1 = 1
                AND tnmummsctnsrvydta.SRVY_YEAR = v_erow.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                AND tnmummsctnsrvydta.ROUTE_CODE = v_erow.ROUTE_CODE  /* 최소_구간_조사_자료.노선_코드 */
                AND  tnmummsctnsrvydta.DIRECT_CODE = v_erow.DIRECT_CODE  /* 최소_구간_조사_자료.행선_코드 */
                AND  tnmummsctnsrvydta.TRACK = v_erow.TRACK  /* 최소_구간_조사_자료.차로 */
                AND  tnmummsctnsrvydta.STRTPT = TO_NUMBER(v_erow.STRTPT)   /* 최소_구간_조사_자료.시점 */
                AND  tnmummsctnsrvydta.ENDPT = TO_NUMBER(v_erow.ENDPT)   /* 최소_구간_조사_자료.종점 */
                AND tnmummsctnsrvydta.DELETE_AT ='N' /* 최소_구간_조사_자료.삭제_여부 */
                AND tnmummsctnsrvydta.USE_AT ='Y' /* 최소_구간_조사_자료.사용_여부 */
            ;
            if p_MODE = 'DEBUG' THEN
                DBMS_OUTPUT.PUT_LINE('v_DTA_NO='||v_DTA_NO);
            END IF;            
            
                IF v_DTA_NO < 0 THEN
                    v_existData :='N';
                    v_DTA_NO := SEQ_TN_MUMM_SCTN_SRVY_DTA.NEXTVAL;
                    o_PROCMSG :='최소_구간_조사_자료 등록';
                    if p_MODE = 'DEBUG' THEN
                        DBMS_OUTPUT.PUT_LINE('v_DTA_NO=신규1'||v_DTA_NO);
                    END IF; 
                    
                ELSE
                    v_existData :='Y';
                END IF;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                v_DTA_NO := SEQ_TN_MUMM_SCTN_SRVY_DTA.NEXTVAL;
                o_PROCMSG :='최소_구간_조사_자료 등록';
                        
                if p_MODE = 'DEBUG' THEN
                    DBMS_OUTPUT.PUT_LINE('v_DTA_NO=신규2'||v_DTA_NO);
                END IF;
                
            END;
            

            v_G2_LONLAT  := SDO_CS.TRANSFORM(SDO_GEOMETRY(2001, 4326, SDO_POINT_TYPE(v_erow.TRACE1_LO, v_erow.TRACE1_LA, NULL), NULL, NULL), v_SRID  ) ;
            v_G2_LONLAT_BUFFER :=SDO_GEOM.SDO_BUFFER (  v_G2_LONLAT  , v_DIMINFO, 50);

            v_CNTL_DFECT := FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
            
            v_DMG_CUZ := FN_GET_DMG_CUZ(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
            v_DMG_CUZ_CLMT := v_DMG_CUZ.DMG_CUZ_CLMT;
            v_DMG_CUZ_VMTC := v_DMG_CUZ.DMG_CUZ_VMTC;
            v_DMG_CUZ_ETC := v_DMG_CUZ.DMG_CUZ_ETC;
            
            IF v_existData = 'N' THEN
               
               if p_MODE = 'DEBUG' THEN
                DBMS_OUTPUT.PUT_LINE('최소_구간_조사_자료 등록='||v_DTA_NO);
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
                    , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
                ) VALUES (
                    v_DTA_NO /* 최소_구간_조사_자료.자료_번호 */
                    , p_SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
                    , v_erow.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                    , v_erow.ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
                    , v_erow.DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
                    , v_erow.TRACK /* 최소_구간_조사_자료.차로 */
                    , v_erow.STRTPT /* 최소_구간_조사_자료.시점 */
                    , v_erow.ENDPT /* 최소_구간_조사_자료.종점 */
                    , v_erow.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
                    , v_erow.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
                    , v_erow.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
                    , v_erow.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
                    , v_erow.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
                    , v_erow.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
                    , v_erow.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
                    , v_erow.BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                    , v_erow.CR_LT /* 최소_구간_조사_자료.균열_길이 */
                    , v_erow.CR_WID /* 최소_구간_조사_자료.균열_폭 */
                    , v_erow.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                    , v_erow.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                    , v_erow.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                    , v_erow.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                    , v_erow.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                    , v_erow.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                    , v_erow.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                    , v_erow.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                    , v_erow.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                    , v_erow.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                    , v_erow.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                    , v_erow.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                    , v_AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                    , v_BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                    , v_LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                    , v_TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                    , v_PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                    , v_POT_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                    , v_erow.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                    , v_erow.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                    , v_erow.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                    , v_erow.XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                    , v_RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                    , v_SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                    , v_erow.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                    , v_CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                    , v_RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                    , v_erow.NHPCI /* 최소_구간_조사_자료.포장상태지수 */
                    , v_GPCI /* 최소_구간_조사_자료.GPCI */
                    , v_erow.SPI /* 최소_구간_조사_자료.SPI */
                    , v_PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                    , v_CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                    , v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                    , v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                    , v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                    , v_erow.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
                    , v_erow.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
                    , v_erow.MEMO /* 최소_구간_조사_자료.메모 */
                    , v_erow.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
                    , v_erow.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
                    , v_erow.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
                    , v_erow.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
                    , v_erow.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
                    , v_G2_LONLAT
                    , v_G2_LONLAT_BUFFER
                    , v_erow.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
                    , v_erow.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
                    , v_erow.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
                    , v_erow.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
                    , v_erow.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
                    , v_erow.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
                    , v_erow.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                    , v_erow.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                    , v_EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                    , v_FRMULA_NO /* 최소_구간_조사_자료.수식_번호 */
                    , v_erow.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_개수 */
                    , v_RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                    , 'N'  /* 최소_구간_조사_자료.수정여부 */
                    , 'N' /* 최소_구간_조사_자료.삭제_여부 */
                    , 'Y' /* 최소_구간_조사_자료.사용_여부 */
                    , p_USER_NO /* 최소_구간_조사_자료.생성자_번호 */
                    , SYSDATE /* 최소_구간_조사_자료.생성_일시 */
                    , p_USER_NO /* 최소_구간_조사_자료.수정자_번호 */
                    , SYSDATE /* 최소_구간_조사_자료.수정_일시 */
                    , 'N' /* 최소_구간_조사_자료.최종_데이터_여부 */
                );  
            ELSIF v_existData = 'Y' THEN
                o_PROCMSG :='최소_구간_조사_자료 수정';
                if p_MODE = 'DEBUG' THEN
                    DBMS_OUTPUT.PUT_LINE('최소_구간_조사_자료 수정='||v_DTA_NO);
                END IF;
                
                /* 최소_구간_조사_자료 수정 */
                UPDATE TN_MUMM_SCTN_SRVY_DTA SET
                    SRVY_NO = p_SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
                    , CR_VAL = v_erow.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                    , RD_VAL = v_erow.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
                    , IRI_VAL = v_erow.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */                
                    , VRTCAL_CR = v_erow.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
                    , HRZNTAL_CR = v_erow.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
                    , CNSTRCT_JOINT_CR = v_erow.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
                    , TRTS_BAC_CR = v_erow.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
                    , PTCHG_CR = v_erow.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
                    , SRVY_KND = v_erow.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
                    , SRVY_MT = v_erow.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
                    , MEMO = v_erow.MEMO /* 최소_구간_조사_자료.메모 */
                    , SRVY_DE = v_erow.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
                    , TRACE1_LA = v_erow.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
                    , TRACE1_LO = v_erow.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
                    , TRACE2_LA = v_erow.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
                    , TRACE2_LO = v_erow.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
                    , G2_LONLAT = v_G2_LONLAT
                    , G2_LONLAT_BUFFER = v_G2_LONLAT_BUFFER
                    , SRVY_NM = v_erow.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
                    , EXMNR_NM = v_erow.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
                    , SCTN_STRTPT_DC = v_erow.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
                    , SCTN_ENDPT_DC = v_erow.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
                    , ROAD_NM = v_erow.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
                    , FRNT_IMG_FILE_NM = v_erow.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
                    , RDSRFC_IMG_FILE_NM_1 = v_erow.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                    , RDSRFC_IMG_FILE_NM_2 = v_erow.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                    , POTHOLE_CR = v_erow.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_개수 */  
                    , BLOCK_CR = v_erow.BLOCK_CR/* 최소_구간_조사_자료.블럭_균열 */
                    , CR_LT = v_erow.CR_LT/* 최소_구간_조사_자료.균열_길이 */
                    , CR_WID = v_erow.CR_WID/* 최소_구간_조사_자료.균열_폭 */
                    , AC_LOW = v_erow.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                    , AC_MED = v_erow.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                    , AC_HI = v_erow.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                    , BLOCK_CR_LOW = v_erow.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                    , BLOCK_CR_MED = v_erow.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                    , BLOCK_CR_HI = v_erow.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                    , LC_LOW = v_erow.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                    , LC_MED = v_erow.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                    , LC_HI = v_erow.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                    , TC_LOW = v_erow.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                    , TC_MED = v_erow.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                    , TC_HI = v_erow.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                    , AC_IDX = v_AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                    , BC_IDX = v_BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                    , LC_IDX = v_LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                    , TC_IDX = v_TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                    , PTCHG_IDX = v_PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                    , POTHOLE_IDX = v_POT_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                    , RD_LOW = v_erow.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                    , RD_MED = v_erow.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                    , RD_HI = v_erow.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                    , XCR = v_erow.XCR/* 최소_구간_조사_자료.NHPCI_균열 */
                    , RCI = v_RCI/* 최소_구간_조사_자료.표면_조도_지수 */
                    , SCR = v_SCR/* 최소_구간_조사_자료.표면_상태_지수 */              
                    , CR_IDX = v_CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                    , RD_IDX = v_RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                    , RI_IDX = v_RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                    , NHPCI = v_erow.NHPCI /* 최소_구간_조사_자료.포장상태지수 */
                    , GPCI = v_GPCI/* 최소_구간_조사_자료.GPCI */
                    , SPI = v_erow.SPI/* 최소_구간_조사_자료.SPI */
                    , PC_GRAD = v_PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                    , CNTL_DFECT = v_CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                    , DMG_CUZ_CLMT = v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                    , DMG_CUZ_VMTC = v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                    , DMG_CUZ_ETC = v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                    , UPDT_AT ='N' /* 최소_구간_조사_자료.수정여부 */
                    , EXCEL_DTA_SEQ = v_EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                    , FRMULA_NO = v_FRMULA_NO /* 최소_구간_조사_자료.수식_번호 */
                    , UPDUSR_NO = p_USER_NO /* 최소_구간_조사_자료.수정자_번호 */
                    , UPDT_DT = SYSDATE /* 최소_구간_조사_자료.수정_일시 */
                    , LAST_DTA_AT = 'N' /* 최소_구간_조사_자료.최종_데이터_여부 */
                WHERE 1 = 1
                AND DTA_NO = v_DTA_NO /* 최소_구간_조사_자료.자료_번호 */
                ;
            END IF;
            
            o_PROCMSG :='조사_자료_현황 수정';
            /* 조사년도, 조사월, 노선코드, 행선코드, 차로, 시점, 종점        */ 
            UPDATE TN_SRVY_DTA_STTUS SET
                SRVY_KND = v_erow.SRVY_KND /* 조사_자료_현황.조사_종류 */
                , EXCEL_DTA_SEQ = v_EXCEL_DTA_SEQ /* 조사_자료_현황.엑셀_자료_SEQ */
                , SRVY_NO = p_SRVY_NO /* 조사_자료_현황.조사_번호 */
                , DTA_NO = v_DTA_NO     /* 조사_자료_현황.자료_번호 */
                , SM_PROCESS_AT = 'N' /* 조사_자료_현황.집계_구간_산출_여부 */
                , PRDTMDL_PROCESS_AT = 'N' /* 조사_자료_현황.예측_모델_적용_여부 */
                , UPDUSR_NO = p_USER_NO /* 조사_자료_현황.수정자_번호 */
                , UPDT_DT = SYSDATE /* 조사_자료_현황.수정_일시 */
            WHERE 1 = 1
                AND SRVY_YEAR = v_erow.SRVY_YEAR /* 조사_자료_현황.조사_년도 */
                AND SRVY_MT = v_erow.SRVY_MT /* 조사_자료_현황.조사_월 */
                AND ROUTE_CODE = v_erow.ROUTE_CODE  /* 조사_자료_현황.노선_코드 */
                AND  DIRECT_CODE = v_erow.DIRECT_CODE  /* 조사_자료_현황.행선_코드 */
                AND  TRACK = v_erow.TRACK  /* 조사_자료_현황.차로 */
                AND  STRTPT = TO_NUMBER(v_erow.STRTPT)  /* 조사_자료_현황.시점 */
                AND  ENDPT = TO_NUMBER(v_erow.ENDPT)  /* 조사_자료_현황.종점 */
                AND DELETE_AT ='N' /* 조사_자료_현황.삭제_여부 */
                AND USE_AT ='Y' /* 조사_자료_현황.사용_여부 */
            ;
            
            
                            
            IF SQL%NOTFOUND THEN
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
                SEQ_TN_SRVY_DTA_STTUS.NEXTVAL /* 조사_자료_현황.자료_현황_번호 */
                , v_erow.SRVY_YEAR /* 조사_자료_현황.조사_년도 */
                , v_erow.ROUTE_CODE /* 조사_자료_현황.노선_코드 */
                , v_erow.DIRECT_CODE /* 조사_자료_현황.행선_코드 */
                , v_erow.TRACK /* 조사_자료_현황.차로 */
                , v_erow.STRTPT /* 조사_자료_현황.시점 */
                , v_erow.ENDPT /* 조사_자료_현황.종점 */
                , v_erow.SRVY_KND /* 조사_자료_현황.조사_종류 */
                , v_erow.SRVY_MT /* 조사_자료_현황.조사_월 */
                , v_EXCEL_DTA_SEQ /* 조사_자료_현황.엑셀_자료_SEQ */
                , p_SRVY_NO /* 조사_자료_현황.조사_번호 */
                , v_DTA_NO     /* 조사_자료_현황.자료_번호 */
                , 'N' /* 조사_자료_현황.집계_구간_산출_여부 */
                , 'N' /* 조사_자료_현황.예측_모델_적용_여부 */
                , 'N' /* 조사_자료_현황.삭제_여부 */
                , 'Y' /* 조사_자료_현황.사용_여부 */
                , p_USER_NO /* 조사_자료_현황.생성자_번호 */
                , SYSDATE /* 조사_자료_현황.생성_일시 */
                , p_USER_NO /* 조사_자료_현황.수정자_번호 */
                , SYSDATE /* 조사_자료_현황.수정_일시 */
                );
                          
            END IF;
                
                      
        END IF;
        
    END LOOP;
    
    /* 시종점 데이터 검증용 */
    UPDATE TN_MUMM_SCTN_SRVY_DTA
    SET STRTPT_TEST = STRTPT
      , ENDPT_TEST = ENDPT
    WHERE DELETE_AT = 'N'
      AND USE_AT = 'Y'
      AND SRVY_NO = p_SRVY_NO;
    
    /* 조사_자료_엑셀.마스터 정보 수정 */
    UPDATE TN_SRVY_DTA_EXCEL SET
         DATA_CO = v_ROW_INDEX /* 조사_자료_엑셀.데이터_건수 */
        , ROUTE_CODE = v_ROUTE_CODE /* 조사_자료_엑셀.노선_코드 */
        , DIRECT_CODE = v_DIRECT_CODE /* 조사_자료_엑셀.행선_코드 */
        , TRACK = v_TRACK /* 조사_자료_엑셀.차로 */
        , STRTPT = v_MIN_STRTPT /* 조사_자료_엑셀.시점 */
        , ENDPT = v_MAX_ENDPT /* 조사_자료_엑셀.종점 */
        , SRVY_DE = v_SRVY_DE /* 조사_자료_엑셀.조사_일자 */
        , SRVY_NM = v_SRVY_NM /* 조사_자료_엑셀.조사_명 */
        , EXMNR_NM = v_EXMNR_NM /* 조사_자료_엑셀.조사자_명 */
        , EVL_PROCESS_AT = 'Y' /* 조사_자료_엑셀.평가_처리_여부 */
        , UPDUSR_NO = p_USER_NO /* 조사_자료_엑셀.수정자_번호 */
        , UPDT_DT = SYSDATE /* 조사_자료_엑셀.수정_일시 */
        WHERE 1 = 1
        AND SRVY_NO = p_SRVY_NO /* 조사_자료_엑셀.조사_번호 */
        AND DELETE_AT = 'N' /* 조사_자료_엑셀.삭제_여부 */
        AND USE_AT = 'Y' /* 조사_자료_엑셀.사용_여부 */
        ;   
    o_PROCMSG :='조사 자료 엑셀 수정';   
    o_PROCCODE :='true';
    
    /* 최종 데이터 여부 체크*/
    SELECT ROUTE_CODE, DIRECT_CODE, TRACK, MIN(STRTPT), MAX(ENDPT) 
      INTO v_ROUTE_CODE, v_DIRECT_CODE, v_TRACK, v_STRTPT, v_ENDPT
      FROM TN_MUMM_SCTN_SRVY_DTA
     WHERE SRVY_NO = P_SRVY_NO
     GROUP BY ROUTE_CODE, DIRECT_CODE, TRACK;
    
    UPDATE TN_MUMM_SCTN_SRVY_DTA
       SET LAST_DTA_AT = 'N'
     WHERE LAST_DTA_AT = 'Y'
       AND USE_AT = 'Y'
       AND DELETE_AT = 'N'
       AND ROUTE_CODE = v_ROUTE_CODE
       AND DIRECT_CODE = v_DIRECT_CODE
       AND TRACK = v_TRACK
       AND STRTPT >= v_STRTPT
       AND ENDPT <= v_ENDPT;
       
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
                              AND STRTPT >= v_STRTPT
                              AND ENDPT <= v_ENDPT
                        ) MAXLIST
                        WHERE MAXLIST.RN = 1
                        AND MAXLIST.USE_AT = 'Y'
                        AND MAXLIST.DELETE_AT = 'N'
                       );
        
    /* 조사_자료_로그 수정 */
    UPDATE TL_SRVY_DTA_LOG SET
    LOG_MSSAGE = '조사 자료 등록 및 상태 평가 처리 완료' /* 조사_자료_로그.로그_메세지 */
    , PROCESS_STTUS = 'PCST0002' /* 조사_자료_로그.처리_상태 */
    , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
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
        , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
		WHERE 1 = 1
		AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
        ;
    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;      
END PRC_SAVESURVEYDATA;