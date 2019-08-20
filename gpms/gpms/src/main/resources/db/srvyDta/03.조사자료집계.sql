/**
 * [포장상태 평가]
 * 공간보정 완료된 조사자료 집계 
 * 
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_SRVY_NO : 조사자료 번호
 * @PARAM p_ROUTE_CODE : 노선번호
 * @PARAM p_DIRECT_CODE : 행선코드
 * @PARAM p_TRACK : 차로
 * @PARAM p_FRMULA_NM : 적용 수식 명
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
CREATE OR REPLACE PROCEDURE      "PRC_AGGREGATE_GENERAL" (
/*
    조사자료 데이터 SECTION 단위 집계
    L/M/H의 실제 균열량을 가지고 다시 L/M/H를 뽑아야 하지 않는가??? (L/M/H가 균일하게 증가한다는 것이 이상함)
    평균 값으로 GPCI을 산툴해도 되는가
    
    생성일 : 2017-08-03
    생성자 : skc@muhanit.kr
*/
p_USER_NO          NUMBER,
p_SRVY_NO VARCHAR2,  /* 최소_구간_조사_자료.조사_번호 */ 
p_ROUTE_CODE VARCHAR2, /* 최소_구간_조사_자료.노선_코드 */
p_DIRECT_CODE VARCHAR2, /* 최소_구간_조사_자료.행선_코드 */
p_TRACK NUMBER,    /* 최소_구간_조사_자료.차로 */   
p_FRMULA_NM        VARCHAR2,
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
v_SRVY_YEAR TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR%TYPE;
v_SRVY_MT TN_MUMM_SCTN_SRVY_DTA.SRVY_MT%TYPE;
v_SRVY_NM TN_MUMM_SCTN_SRVY_DTA.SRVY_NM%TYPE;
v_EXMNR_NM TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM%TYPE;
v_ROUTE_CODE TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE%TYPE;
v_DIRECT_CODE TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE%TYPE;
v_TRACK TN_MUMM_SCTN_SRVY_DTA.TRACK%TYPE;
v_STRTPT TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
v_ENDPT TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
v_SRVY_DE  TN_MUMM_SCTN_SRVY_DTA.SRVY_DE%TYPE;
v_PROCESS_LOG_NO     TL_SRVY_DTA_LOG.PROCESS_LOG_NO%TYPE;
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


CURSOR jsmcim_data (
      i_SRVY_YEAR   VARCHAR2,
      i_SRVY_MT     VARCHAR2,
      i_ROUTE_CODE      VARCHAR2,
      i_DIRECT_CODE   VARCHAR2,
      i_TRACK       VARCHAR2
   ) IS 
SELECT 	  i_SRVY_YEAR SRVY_YEAR    /* 최소_구간_조사_자료.조사_년도 */
		, i_SRVY_MT SRVY_MT    /* 최소_구간_조사_자료.조사_월 */
		, tnmummsctnsrvydta.ROUTE_CODE    /* 최소_구간_조사_자료.노선_코드 */
		, tnmummsctnsrvydta.DIRECT_CODE    /* 최소_구간_조사_자료.행선_코드 */
		, tnmummsctnsrvydta.TRACK    /* 최소_구간_조사_자료.차로 */
		, MAX(tnmummsctnsrvydta.SRVY_NO) SRVY_NO 
		, cellsect.STRTPT
		, cellsect.ENDPT
		, AVG(tnmummsctnsrvydta.IRI_VAL) IRI_VAL
		, AVG(tnmummsctnsrvydta.RD_VAL) RD_VAL
		, AVG(tnmummsctnsrvydta.CR_VAL) CR_VAL
		, AVG(tnmummsctnsrvydta.VRTCAL_CR) VRTCAL_CR
		, AVG(tnmummsctnsrvydta.HRZNTAL_CR) HRZNTAL_CR
		, AVG(tnmummsctnsrvydta.CNSTRCT_JOINT_CR)  CNSTRCT_JOINT_CR
		, AVG(tnmummsctnsrvydta.TRTS_BAC_CR) TRTS_BAC_CR
		, AVG(tnmummsctnsrvydta.PTCHG_CR) PTCHG_CR
		, AVG(tnmummsctnsrvydta.POTHOLE_CR) POTHOLE_CR
		, AVG(tnmummsctnsrvydta.XCR) XCR
		, AVG(tnmummsctnsrvydta.BLOCK_CR) BLOCK_CR
		, AVG(tnmummsctnsrvydta.CR_LT) CR_LT
		, AVG(tnmummsctnsrvydta.CR_WID) CR_WID
		, AVG(tnmummsctnsrvydta.SPI) SPI
		, AVG(tnmummsctnsrvydta.NHPCI) NHPCI
		, AVG(tnmummsctnsrvydta.AC_LOW) AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
		, AVG(tnmummsctnsrvydta.AC_MED) AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
		, AVG(tnmummsctnsrvydta.AC_HI) AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
		, AVG(tnmummsctnsrvydta.BLOCK_CR_LOW) BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
		, AVG(tnmummsctnsrvydta.BLOCK_CR_MED) BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
		, AVG(tnmummsctnsrvydta.BLOCK_CR_HI) BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
		, AVG(tnmummsctnsrvydta.LC_LOW) LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
		, AVG(tnmummsctnsrvydta.LC_MED) LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
		, AVG(tnmummsctnsrvydta.LC_HI) LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
		, AVG(tnmummsctnsrvydta.TC_LOW) TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
		, AVG(tnmummsctnsrvydta.TC_MED) TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
		, AVG(tnmummsctnsrvydta.TC_HI) TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
		, AVG(tnmummsctnsrvydta.AC_IDX) AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
		, AVG(tnmummsctnsrvydta.BC_IDX) BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
		, AVG(tnmummsctnsrvydta.LC_IDX) LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
		, AVG(tnmummsctnsrvydta.TC_IDX) TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
		, AVG(tnmummsctnsrvydta.PTCHG_IDX) PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
		, AVG(tnmummsctnsrvydta.POTHOLE_IDX) POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
		, AVG(tnmummsctnsrvydta.RD_LOW) RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
        , AVG(tnmummsctnsrvydta.RD_MED) RD_MED  /* 최소_구간_조사_자료.소성변형_MED */
        , AVG(tnmummsctnsrvydta.RD_HI) RD_HI  /* 최소_구간_조사_자료.소성변형_HI */
		, AVG(tnmummsctnsrvydta.RD_IDX) RD_IDX /* 최소_구간_조사_자료.소성변형_지수 */
		, AVG(tnmummsctnsrvydta.RCI) RCI /* 최소_구간_조사_자료.표면_조도_지수 */
		, AVG(tnmummsctnsrvydta.SCR) SCR /* 최소_구간_조사_자료.표면_상태_지수 */
		, AVG(tnmummsctnsrvydta.GPCI) GPCI /* 최소_구간_조사_자료.GPCI */
FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
 LEFT OUTER JOIN CELL_SECT cellsect
   ON 1=1 
  AND tnmummsctnsrvydta.ROUTE_CODE = cellsect.ROUTE_CODE                                    /* 최소_구간_조사_자료.노선_코드 */
  AND tnmummsctnsrvydta.DIRECT_CODE = cellsect.DIRECT_CODE                                  /* 최소_구간_조사_자료.행선_코드 */
  AND tnmummsctnsrvydta.TRACK = cellsect.TRACK                                             /* 최소_구간_조사_자료.차로 */
  AND tnmummsctnsrvydta.STRTPT >= cellsect.STRTPT                                                     /* CELL_SECT.시점 */
  AND tnmummsctnsrvydta.STRTPT <= cellsect.ENDPT                                                     /* CELL_SECT.시점 */
  AND tnmummsctnsrvydta.ENDPT >= cellsect.STRTPT                                                     /* CELL_SECT.종점 */
  AND tnmummsctnsrvydta.ENDPT <= cellsect.ENDPT   
WHERE  tnmummsctnsrvydta.ROUTE_CODE = i_ROUTE_CODE                                      /* 최소_구간_조사_자료.노선_코드 */
   AND tnmummsctnsrvydta.DIRECT_CODE = i_DIRECT_CODE                                   /* 최소_구간_조사_자료.행선_코드 */
   AND tnmummsctnsrvydta.TRACK = i_TRACK                                           /* 최소_구간_조사_자료.차로 */
   AND tnmummsctnsrvydta.UPDT_AT = 'Y'     /* 최소_구간_조사_자료.공간_보정_여부 */ 
   AND tnmummsctnsrvydta.DELETE_AT = 'N'  /* 최소_구간_조사_자료.삭제_여부 */
   AND tnmummsctnsrvydta.USE_AT = 'Y'  /* 최소_구간_조사_자료.사용_여부 */
   AND tnmummsctnsrvydta.SRVY_YEAR <= i_SRVY_YEAR
   AND tnmummsctnsrvydta.SRVY_MT <= i_SRVY_MT
   AND tnmummsctnsrvydta.LAST_DTA_AT = 'Y'
GROUP BY tnmummsctnsrvydta.ROUTE_CODE, tnmummsctnsrvydta.DIRECT_CODE, tnmummsctnsrvydta.TRACK, cellsect.STRTPT, cellsect.ENDPT, i_SRVY_YEAR, i_SRVY_MT;
   
-- DECLARE RAISE_EXT exception;
BEGIN
   
    -- SELECT  TO_NUMBER(ATRB_VAL) into v_UNIT FROM TC_CODE WHERE  CODE_VAL='UNIT0002' AND USE_AT='Y' AND DELETE_AT='N';
    
    o_PROCCODE :='false';                    
    o_PROCMSG := '조사 자료 집계 준비';
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
     -- 기본값 체크
   IF (p_SRVY_NO IS NULL) OR (p_SRVY_NO = '')
   THEN
      o_PROCCODE := 'N';
      o_PROCMSG := '조사번호 파라미터에 값이 없습니다.';
      RETURN;
   ELSIF (p_ROUTE_CODE IS NULL OR p_ROUTE_CODE = '')
   THEN
      o_PROCCODE := 'N';
      o_PROCMSG := '노선코드 파라미터에 값이 없습니다.';
      RETURN;
   ELSIF (p_DIRECT_CODE IS NULL OR p_DIRECT_CODE = '')
   THEN
      o_PROCCODE := 'N';
      o_PROCMSG := '행선코드 파라미터에 값이 없습니다.';
      RETURN;
   ELSIF (p_TRACK IS NULL OR p_TRACK = '')
   THEN
      o_PROCCODE := 'N';
      o_PROCMSG := '차로 파라미터에 값이 없습니다.';
      RETURN;
   END IF;
 
 
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
        , '조사 자료 집계 처리 시작' /* 조사_자료_로그.로그_메세지 */
        , p_USER_NO /* 조사_자료_로그.생성자_번호 */
        , SYSDATE /* 조사_자료_로그.시작_일시 */
        , p_SRVY_NO /* 조사_자료_로그.조사_번호 */
        , 'PCSE0004' /* 조사_자료_로그.처리_구분 */
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

    -- 조사자료 년도, 월을 가져온다.   
   SELECT SRVY_YEAR, SRVY_MT 
     INTO v_SRVY_YEAR, v_SRVY_MT 
     FROM TN_MUMM_SCTN_SRVY_DTA
    WHERE USE_AT= 'Y'
      AND DELETE_AT = 'N'
      AND SRVY_NO = p_SRVY_NO
      AND UPDT_AT = 'Y'
    GROUP BY SRVY_YEAR, SRVY_MT;
    
     -- lOOP
   FOR v_erow IN jsmcim_data (v_SRVY_YEAR, v_SRVY_MT , p_ROUTE_CODE  , p_DIRECT_CODE  , p_TRACK)
   LOOP      
        -- 포함되는 섹션이 없으면 집계하지 않는다.
        CONTINUE WHEN v_erow.STRTPT IS NULL; 
        
        --nhpci 도출
        --select   1/POWER((0.33+ (0.003 * CR)+ (0.004 * RD) + ( 0.0183* IRI ) ),2) INTO v_PCI        from dual;
        --SELECT NVL( ROUND(  1/POWER((v_BASE_IDX_VAR+ (v_CR_IDX_VAR  * TO_NUMBER( v_erow.XCR))+ (v_RD_IDX_VAR * TO_NUMBER(v_erow.RD_VAL)) + ( v_IRI_IDX_VAR  * TO_NUMBER(v_erow.IRI_VAL) ) ), v_EXPONENT_VAR ), 2),-1) 
        --INTO v_NHPCI  FROM DUAL;
        --v_PC_GRAD := 'E';
        --o_PROCMSG :='nhpci GRADE 도출';
        --SELECT FN_GET_IDX_GRADE(NVL(p_FRMULA_NM,'NHPCI'), v_erow.SRVY_YEAR, v_NHPCI) INTO v_PC_GRAD FROM DUAL;
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
        SELECT DECODE( v_USE_GPCI_FN, 'N', v_erow.GPCI, 'Y', FN_GET_GPCI( v_SCR,v_RCI ) ) /* GPCI */
        INTO v_GPCI 
        FROM DUAL;
        
        
        o_PROCMSG :='GPCI GRADE 도출';
        SELECT FN_GET_IDX_GRADE('GPCI', v_erow.SRVY_YEAR, v_GPCI) INTO v_PC_GRAD FROM DUAL;
        
        o_PROCMSG :='지배결함 도출';
        v_CNTL_DFECT := FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX, v_RCI);
            
        v_DMG_CUZ := FN_GET_DMG_CUZ(v_AC_IDX,v_BC_IDX,v_LC_IDX,v_TC_IDX,v_PTCHG_IDX,v_POT_IDX,v_RD_IDX, v_RCI);
        v_DMG_CUZ_CLMT := v_DMG_CUZ.DMG_CUZ_CLMT;
        v_DMG_CUZ_VMTC := v_DMG_CUZ.DMG_CUZ_VMTC;
        v_DMG_CUZ_ETC := v_DMG_CUZ.DMG_CUZ_ETC;
        
        o_PROCMSG :='조사 자료 집계 일반 현황 수정';                    
        /* smDtaGnlSttusDAO.updateSmDtaGnlSttus */
		/* 집계_자료_일반_현황 수정 */
		UPDATE TN_SM_DTA_GNL_STTUS SET		
          IRI_VAL = v_erow.IRI_VAL /* 집계_자료_일반_현황.종단평탄성_값 */
        , POTHOLE_CR = v_erow.POTHOLE_CR /* 집계_자료_일반_현황.포트홀_균열 */
        , RD_VAL = v_erow.RD_VAL /* 집계_자료_일반_현황.소성변형_값 */ 
        , VRTCAL_CR = v_erow.VRTCAL_CR /* 집계_자료_일반_현황.종방향_균열 */
        , HRZNTAL_CR = v_erow.HRZNTAL_CR /* 집계_자료_일반_현황.횡방향_균열 */
        , CNSTRCT_JOINT_CR = v_erow.CNSTRCT_JOINT_CR /* 집계_자료_일반_현황.시공_줄눈_균열 */
        , TRTS_BAC_CR = v_erow.TRTS_BAC_CR /* 집계_자료_일반_현황.거북등_균열 */
        , PTCHG_CR = v_erow.PTCHG_CR /* 집계_자료_일반_현황.패칭_균열 */
        , CR_VAL = v_erow.CR_VAL /* 집계_자료_일반_현황.표면손상값 */
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
        , RD_IDX = v_RD_IDX /* 최소_구간_조사_자료.소성변형_지수 */
        , XCR = v_erow.XCR/* 최소_구간_조사_자료.NHPCI_균열 */
        , RCI = v_RCI/* 최소_구간_조사_자료.표면_조도_지수 */
        , SCR = v_SCR/* 최소_구간_조사_자료.표면_상태_지수 */   
        , NHPCI = v_erow.NHPCI /* 최소_구간_조사_자료.포장상태지수 */
        , GPCI = v_GPCI/* 최소_구간_조사_자료.GPCI */
        , SPI = v_erow.SPI/* 최소_구간_조사_자료.SPI */
        , PC_GRAD = v_PC_GRAD /* 집계_자료_일반_현황.포장상태등급 */
        , CNTL_DFECT = v_CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
        , DMG_CUZ_CLMT = v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
        , DMG_CUZ_VMTC = v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
        , DMG_CUZ_ETC = v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
        , SRVY_KND = '1' /* 집계_자료_일반_현황.조사_종류 */
        , FRMULA_NO = v_FRMULA_NO /* 집계_자료_일반_현황.수식_번호 */
        --, TRNSPORT_QY = v_erow.TRNSPORT_QY /* 집계_자료_일반_현황.교통_량 */
        , UPDUSR_NO = p_USER_NO /* 집계_자료_일반_현황.수정자_번호 */
        , UPDT_DT = SYSDATE /* 집계_자료_일반_현황.수정_일시 */
        , LAST_DTA_AT = 'N' /* 집계_자료_일반_현황.최종_데이터_여부 */
		WHERE 1 = 1
         AND SRVY_YEAR = v_erow.SRVY_YEAR 
         AND SRVY_MT = v_erow.SRVY_MT
         AND ROUTE_CODE = v_erow.ROUTE_CODE
         AND DIRECT_CODE = v_erow.DIRECT_CODE
         AND TRACK = v_erow.TRACK
         AND STRTPT = v_erow.STRTPT
         AND ENDPT = v_erow.ENDPT;

   

        IF SQL%NOTFOUND
        THEN
            o_PROCMSG :='조사 자료 집계 일반 현황 등록';    
            /* smDtaGnlSttusDAO.insertSmDtaGnlSttus */
            /* 집계_자료_일반_현황 등록 */		
            INSERT INTO TN_SM_DTA_GNL_STTUS (
            SM_NO /* 집계_자료_일반_현황.집계_번호 */
            , SRVY_NO /* 집계_자료_일반_현황.조사_번호 */
            , SRVY_YEAR /* 집계_자료_일반_현황.조사_년도 */
            , ROUTE_CODE /* 집계_자료_일반_현황.노선_코드 */
            , DIRECT_CODE /* 집계_자료_일반_현황.행선_코드 */
            , TRACK /* 집계_자료_일반_현황.차로 */
            , STRTPT /* 집계_자료_일반_현황.시점 */
            , ENDPT /* 집계_자료_일반_현황.종점 */
            , IRI_VAL /* 집계_자료_일반_현황.종단평탄성_값 */
            , POTHOLE_CR /* 집계_자료_일반_현황.포트홀_균열  */
            , RD_VAL /* 집계_자료_일반_현황.소성변형_값 */
            , VRTCAL_CR /* 집계_자료_일반_현황.종방향_균열 */
            , HRZNTAL_CR /* 집계_자료_일반_현황.횡방향_균열 */
            , CNSTRCT_JOINT_CR /* 집계_자료_일반_현황.시공_줄눈_균열 */
            , TRTS_BAC_CR /* 집계_자료_일반_현황.거북등_균열 */
            , PTCHG_CR /* 집계_자료_일반_현황.패칭_균열 */
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
            , CR_VAL /* 집계_자료_일반_현황.표면손상값 */
            , CR_IDX /* 집계_자료_일반_현황.표면손상지수 */
            , RD_IDX /* 집계_자료_일반_현황.소성변형지수 */
            , RI_IDX /* 집계_자료_일반_현황.평탄성_지수 */
            , NHPCI /* 최소_구간_조사_자료.NHPCI */
            , GPCI /* 최소_구간_조사_자료.GPCI */
            , SPI /* 최소_구간_조사_자료.SPI */
            , PC_GRAD /* 집계_자료_일반_현황.포장상태등급 */
            , CNTL_DFECT /* 집계_자료_일반_현황.지배_결함 */
            , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
            , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
            , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
            , SRVY_KND /* 집계_자료_일반_현황.조사_종류 */
            , SRVY_MT /* 집계_자료_일반_현황.조사_월 */
            , FRMULA_NO /* 집계_자료_일반_현황.수식_번호 */
            , TRNSPORT_QY /* 집계_자료_일반_현황.교통_량 */
            , CR_GRAD /* 집계_자료_일반_현황.표면손상_등급 */
            , RD_GRAD /* 집계_자료_일반_현황.소성변형_등급 */
            , IRI_GRAD /* 집계_자료_일반_현황.종단평탄성_등급 */
            , DELETE_AT /* 집계_자료_일반_현황.삭제_여부 */
            , USE_AT /* 집계_자료_일반_현황.사용_여부 */
            , CRTR_NO /* 집계_자료_일반_현황.생성자_번호 */
            , CREAT_DT /* 집계_자료_일반_현황.생성_일시 */
            , UPDUSR_NO /* 집계_자료_일반_현황.수정자_번호 */
            , UPDT_DT /* 집계_자료_일반_현황.수정_일시 */
            , LAST_DTA_AT /* 집계_자료_일반_현황.최종_데이터_여부 */
            ) VALUES (
            SEQ_TN_SM_DTA_GNL_STTUS.NEXTVAL /* 집계_자료_일반_현황.집계_번호 */
            , p_SRVY_NO /* 집계_자료_일반_현황.조사_번호 */
            , v_erow.SRVY_YEAR /* 집계_자료_일반_현황.조사_년도 */
            , v_erow.ROUTE_CODE /* 집계_자료_일반_현황.노선_코드 */
            , v_erow.DIRECT_CODE /* 집계_자료_일반_현황.행선_코드 */
            , v_erow.TRACK /* 집계_자료_일반_현황.차로 */
            , v_erow.STRTPT /* 집계_자료_일반_현황.시점 */
            , v_erow.ENDPT /* 집계_자료_일반_현황.종점 */
            , v_erow.IRI_VAL /* 집계_자료_일반_현황.종단평탄성_값 */
            , v_erow.POTHOLE_CR /* 집계_자료_일반_현황.포트홀_균열 */
            , v_erow.RD_VAL /* 집계_자료_일반_현황.소성변형_값 */
            , v_erow.VRTCAL_CR /* 집계_자료_일반_현황.종방향_균열 */
            , v_erow.HRZNTAL_CR /* 집계_자료_일반_현황.횡방향_균열 */
            , v_erow.CNSTRCT_JOINT_CR /* 집계_자료_일반_현황.시공_줄눈_균열 */
            , v_erow.TRTS_BAC_CR /* 집계_자료_일반_현황.거북등_균열 */
            , v_erow.PTCHG_CR /* 집계_자료_일반_현황.패칭_균열 */
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
            , v_erow.CR_VAL /* 집계_자료_일반_현황.표면손상값 */
            , NULL /* 집계_자료_일반_현황.표면손상지수 */
            , v_RD_IDX /* 집계_자료_일반_현황.소성변형지수 */
            , NULL /* 집계_자료_일반_현황.평탄성_지수 */
            , v_erow.NHPCI /* 최소_구간_조사_자료.포장상태지수 */
            , v_GPCI /* 최소_구간_조사_자료.GPCI */
            , v_erow.SPI /* 최소_구간_조사_자료.SPI */
            , v_PC_GRAD /* 집계_자료_일반_현황.포장상태등급 */
            , v_CNTL_DFECT /* 집계_자료_일반_현황.지배_결함 */
            , v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
            , v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
            , v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
            , '1' /* 집계_자료_일반_현황.조사_종류 */
            , v_erow.SRVY_MT /* 집계_자료_일반_현황.조사_월 */
            , v_FRMULA_NO /* 집계_자료_일반_현황.수식_번호 */
            , NULL /* 집계_자료_일반_현황.교통_량 */
            , NULL /* 집계_자료_일반_현황.표면손상_등급 */
            , NULL /* 집계_자료_일반_현황.소성변형_등급 */
            , NULL /* 집계_자료_일반_현황.종단평탄성_등급 */
            , 'N' /* 집계_자료_일반_현황.삭제_여부 */
            , 'Y' /* 집계_자료_일반_현황.사용_여부 */
            , p_USER_NO /* 집계_자료_일반_현황.생성자_번호 */
            , SYSDATE /* 집계_자료_일반_현황.생성_일시 */
            , p_USER_NO /* 집계_자료_일반_현황.수정자_번호 */
            , SYSDATE /* 집계_자료_일반_현황.수정_일시 */
            , 'N' /* 집계_자료_일반_현황.최종_데이터_여부 */
            );
                    
        END IF;
    
    END LOOP;

    UPDATE TN_SRVY_DTA_STTUS SET
          SM_PROCESS_AT = 'Y' /* 조사_자료_현황.집계_구간_산출_여부 */
        , UPDUSR_NO = p_USER_NO /* 조사_자료_현황.수정자_번호 */
        , UPDT_DT = SYSDATE /* 조사_자료_현황.수정_일시 */
    WHERE 1 = 1
        AND SRVY_YEAR = v_SRVY_YEAR  /* 조사_자료_현황.조사_년도 */
        AND SRVY_MT = v_SRVY_MT /* 조사_자료_현황.조사_월 */
        AND ROUTE_CODE = p_ROUTE_CODE   /* 조사_자료_현황.노선_코드 */
        AND DIRECT_CODE = p_DIRECT_CODE  /* 조사_자료_현황.행선_코드 */
        AND TRACK = p_TRACK  /* 조사_자료_현황.차로 */
        AND DELETE_AT ='N' /* 조사_자료_현황.삭제_여부 */
        AND USE_AT ='Y' /* 조사_자료_현황.사용_여부 */
        AND SM_PROCESS_AT = 'N';
    o_PROCMSG :='조사 자료 집계 현황 갱신';
 
    /* 조사_자료_엑셀 수정 */
    UPDATE TN_SRVY_DTA_EXCEL SET
      SM_PROCESS_AT = 'Y'
     WHERE 1 = 1
       AND SRVY_NO = p_SRVY_NO;
 
    /* 최종 데이터 여부 체크*/
    UPDATE TN_SM_DTA_GNL_STTUS
       SET LAST_DTA_AT = 'N'
     WHERE LAST_DTA_AT = 'Y'
       AND USE_AT = 'Y'
       AND DELETE_AT = 'N'
       AND ROUTE_CODE = p_ROUTE_CODE
       AND DIRECT_CODE = p_DIRECT_CODE
       AND TRACK = p_TRACK;
       
    UPDATE TN_SM_DTA_GNL_STTUS
       SET LAST_DTA_AT = 'Y'
     WHERE SM_NO IN (
                        SELECT SM_NO FROM (
                            SELECT SM_NO
                                 , USE_AT
                                 , DELETE_AT
                                 , ROW_NUMBER() OVER(PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT ORDER BY SRVY_YEAR || SRVY_MT DESC) RN
                            FROM TN_SM_DTA_GNL_STTUS
                            WHERE USE_AT = 'Y'
                              AND DELETE_AT = 'N'
                              AND ROUTE_CODE = p_ROUTE_CODE
                              AND DIRECT_CODE = p_DIRECT_CODE
                              AND TRACK = p_TRACK
                            ) MAXLIST
                            WHERE RN = 1
                        );
                        
    o_PROCMSG :='조사 자료 집계 완료';   
    o_PROCCODE :='true';

EXCEPTION  
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
        /* 조사_자료_로그 수정 */
        UPDATE TL_SRVY_DTA_LOG SET
        LOG_MSSAGE = '조사 자료 집계 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
        , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
        , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
        WHERE 1 = 1
        AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
        ;
    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;      
END PRC_AGGREGATE_GENERAL;