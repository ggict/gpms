
/**
 * 섹션 공사 년도 목록 조회 
 * 
 * @param p_ROUTE_CODE : 노선번호
 * @param p_DIRECT_CODE : 행선코드
 * @param p_TRACK : 차로
 * @param p_STRTPT : 시점
 * @param p_ENDPT : 종점
 */
CREATE OR REPLACE FUNCTION GPMS.FN_GET_CNTRWK_YEARS(p_ROUTE_CODE IN VARCHAR2, p_DIRECT_CODE IN VARCHAR2, p_TRACK IN NUMBER, p_STRTPT IN NUMBER, p_ENDPT IN NUMBER)
RETURN VARCHAR2 IS

  RET_VALUE     VARCHAR2(4000);
BEGIN
    /* 
        섹션 공사 년도 목록 조회
        
        생성일 : 2017-09-13
        생성자 : muhanit.kr
    */
 
  
SELECT  LISTAGG(  tncntrwk.CNTRWK_YEAR,',')  WITHIN GROUP (ORDER BY CNTRWK_YEAR ASC) CNTRWK_YEAR INTO RET_VALUE
FROM(  
SELECT
    DISTINCT tncntrwk.CNTRWK_YEAR
  FROM CELL_10 cell10
  INNER JOIN TN_CNTRWK_CELL_INFO tncntrwkcellinfo
    ON cell10.CELL_ID = tncntrwkcellinfo.PAV_CELL_ID  
  INNER JOIN GPMS.TN_CNTRWK_DTL  tncntrwkdtl  /*** 공사상세정보 테이블 ***/
    ON tncntrwkdtl.DETAIL_CNTRWK_ID = tncntrwkcellinfo.DETAIL_CNTRWK_ID
    AND tncntrwkdtl.USE_AT = 'Y'                                                  /* 공사상세정보.사용_여부 */
   AND tncntrwkdtl.DELETE_AT = 'N'
  INNER JOIN   GPMS.TN_CNTRWK  tncntrwk  /*** 공사정보 테이블 ***/
 ON  tncntrwkdtl.CNTRWK_ID = tncntrwk.CNTRWK_ID                                            /* 공사상세정보.공사_ID */
 AND tncntrwk.USE_AT = 'Y'                                           /* 공사정보.사용_여부 */
   AND tncntrwk.DELETE_AT = 'N'            
 WHERE 1=1    
   AND cell10.ROUTE_CODE = p_ROUTE_CODE
   AND cell10.DIRECT_CODE = p_DIRECT_CODE
   AND cell10.TRACK = p_TRACK
   AND cell10.STRTPT >= p_STRTPT
   AND cell10.ENDPT <= p_ENDPT   
)   tncntrwk;


      RETURN (trim(RET_VALUE));
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN ('');
END FN_GET_CNTRWK_YEARS;



/**
 * 보수 공법별 GPCI 한계값 도출
 * 
 * @param p_CELL_TYPE : 셀 타입 구분 (일반도로/교차로/특별관리구간 등등) - 공통코드 사용(CELT)
 * @param p_VMTC_GRAD : 교통용량 구분(고용량/중용량/저용량) - 공통코드 사용(VNTC)
 * @param p_GPCI : GPCI 값
 * @param p_LCTC : 선형 균열 지수
 * @param p_AC : 거북등 균열 지수
 * @param p_FRMULA_NM : 적용 수식 명
 */
CREATE OR REPLACE FUNCTION      FN_GET_GPCI_LIMITVAL( p_CELL_TYPE IN VARCHAR2,p_VMTC_GRAD IN VARCHAR2,p_GPCI IN NUMBER,p_LCTC IN NUMBER,p_RD IN NUMBER, p_AC IN NUMBER, p_FRMULA_NM IN VARCHAR2)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
    v_SECT_STTUS VARCHAR2(10);
    v_VMTC_STTUS VARCHAR2(10);
    v_GPCI_STTUS VARCHAR2(10);
    v_LCTC_STTUS VARCHAR2(10);
    v_RD_STTUS VARCHAR2(10);
    v_AC_STTUS VARCHAR2(10);
    v_FRMULA_NO NUMBER;
BEGIN
    /* 
        보수 공법 도출
        * sect, vmtc, GPCI 동일 조건하에 ltc_sttus_code rd_sttus_code : lctc0001 null / lctc0001 rd0001 의 값이 보수 공법 결정 테이블에 존재할 경우
          공법을 제대로 도출 못할 수 있음.

        생성일 : 2017-09-21
        생성자 : skc@muhanit.kr
    */

   v_SECT_STTUS := NULL;
    v_VMTC_STTUS := NULL;
    v_GPCI_STTUS := NULL;
    v_LCTC_STTUS := NULL;
    v_RD_STTUS := NULL;
    v_AC_STTUS := NULL;

  -- 대상 수식 번호 조회
    SELECT FRMULA_NO INTO v_FRMULA_NO FROM TN_PAV_FRMULA
     WHERE FRMULA_NM LIKE '%' || p_FRMULA_NM || '%';
     
    WITH srvy_data AS (
        SELECT DECODE (p_CELL_TYPE, 'CELT0012', 'SECT0004', 'CELT0002', 'SECT0005', 'SECT0006') SECT
             , DECODE (p_VMTC_GRAD, 'VNTC0003', 'VMTC0003', 'VMTC0004') VMTC
             , p_GPCI GPCI
             , p_LCTC LCTC
             , p_RD RD 
             , p_AC AC
         FROM DUAL
    )
    , srvy_sttus_data AS (
        SELECT SECT
             , VMTC
             , GPCI
             , LCTC
             , RD
             , GPCIfrmula.STEP_STTUS_CODE GPCI_STTUS
             , lctcfrmula.STEP_STTUS_CODE LCTC_STTUS
             , rdfrmula.STEP_STTUS_CODE RD_STTUS
             , acfrmula.STEP_STTUS_CODE AC_STTUS
          FROM srvy_data 
         INNER JOIN TN_RPAIR_MTHD_FRMULA GPCIfrmula
                 ON GPCIfrmula.STEP_SE_CODE = 'RPST0002'
                AND GPCIfrmula.USE_AT = 'Y'
                AND GPCIfrmula.DELETE_AT = 'N'
                AND GPCIfrmula.FRMULA_NO = v_FRMULA_NO
                AND (CASE WHEN GPCIfrmula.BEGIN_VAL IS NOT NULL THEN (CASE WHEN GPCIfrmula.BEGIN_CALC_SE_CODE = 'OPTR0001' AND GPCIfrmula.BEGIN_VAL < GPCI THEN 'TRUE'
                          WHEN GPCIfrmula.BEGIN_CALC_SE_CODE = 'OPTR0002' AND GPCIfrmula.BEGIN_VAL <= GPCI THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
                AND (CASE WHEN GPCIfrmula.END_VAL IS NOT NULL THEN (CASE WHEN GPCIfrmula.END_CALC_SE_CODE = 'OPTR0003' AND GPCIfrmula.END_VAL > GPCI THEN 'TRUE'
                          WHEN GPCIfrmula.END_CALC_SE_CODE = 'OPTR0004' AND GPCIfrmula.END_VAL >= GPCI THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
         INNER JOIN TN_RPAIR_MTHD_FRMULA lctcfrmula
                 ON lctcfrmula.STEP_SE_CODE = 'RPST0004'
                AND lctcfrmula.USE_AT = 'Y'
                AND lctcfrmula.DELETE_AT = 'N'
                AND lctcfrmula.FRMULA_NO = v_FRMULA_NO
                AND (CASE WHEN lctcfrmula.BEGIN_VAL IS NOT NULL THEN (CASE WHEN lctcfrmula.BEGIN_CALC_SE_CODE = 'OPTR0001' AND lctcfrmula.BEGIN_VAL < LCTC THEN 'TRUE'
                          WHEN lctcfrmula.BEGIN_CALC_SE_CODE = 'OPTR0002' AND lctcfrmula.BEGIN_VAL <= LCTC THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
                AND (CASE WHEN lctcfrmula.END_VAL IS NOT NULL THEN (CASE WHEN lctcfrmula.END_CALC_SE_CODE = 'OPTR0003' AND lctcfrmula.END_VAL > LCTC THEN 'TRUE'
                          WHEN lctcfrmula.END_CALC_SE_CODE = 'OPTR0004' AND lctcfrmula.END_VAL >= LCTC THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
         INNER JOIN TN_RPAIR_MTHD_FRMULA rdfrmula
                 ON rdfrmula.STEP_SE_CODE = 'RPST0005'
                AND rdfrmula.USE_AT = 'Y'
                AND rdfrmula.DELETE_AT = 'N'
                AND rdfrmula.FRMULA_NO = v_FRMULA_NO
                AND (CASE WHEN rdfrmula.BEGIN_VAL IS NOT NULL THEN (CASE WHEN rdfrmula.BEGIN_CALC_SE_CODE = 'OPTR0001' AND rdfrmula.BEGIN_VAL < RD THEN 'TRUE'
                          WHEN rdfrmula.BEGIN_CALC_SE_CODE = 'OPTR0002' AND rdfrmula.BEGIN_VAL <= RD THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
                AND (CASE WHEN rdfrmula.END_VAL IS NOT NULL THEN (CASE WHEN rdfrmula.END_CALC_SE_CODE = 'OPTR0003' AND rdfrmula.END_VAL > RD THEN 'TRUE'
                          WHEN rdfrmula.END_CALC_SE_CODE = 'OPTR0004' AND rdfrmula.END_VAL >= RD THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
         INNER JOIN TN_RPAIR_MTHD_FRMULA acfrmula
                 ON acfrmula.STEP_SE_CODE = 'RPST0006'
                AND acfrmula.USE_AT = 'Y'
                AND acfrmula.DELETE_AT = 'N'
                AND acfrmula.FRMULA_NO = v_FRMULA_NO
                AND (CASE WHEN acfrmula.BEGIN_VAL IS NOT NULL THEN (CASE WHEN acfrmula.BEGIN_CALC_SE_CODE = 'OPTR0001' AND acfrmula.BEGIN_VAL < AC THEN 'TRUE'
                          WHEN acfrmula.BEGIN_CALC_SE_CODE = 'OPTR0002' AND acfrmula.BEGIN_VAL <= AC THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
                AND (CASE WHEN acfrmula.END_VAL IS NOT NULL THEN (CASE WHEN acfrmula.END_CALC_SE_CODE = 'OPTR0003' AND acfrmula.END_VAL > AC THEN 'TRUE'
                          WHEN acfrmula.END_CALC_SE_CODE = 'OPTR0004' AND acfrmula.END_VAL >= AC THEN 'TRUE' ELSE 'FALSE' END) ELSE 'TRUE' END) = 'TRUE'
    )
    SELECT --map.RPAIR_MTHD_CODE
        NVL(tnrpairmthdfrmula.BEGIN_VAL , tnrpairmthdfrmula.END_VAL)    
      INTO RET_VALUE
      FROM TN_RPAIR_MTHD_DECSN_MAP map
      INNER JOIN TN_RPAIR_MTHD_FRMULA  tnrpairmthdfrmula  
      ON 1=1  AND tnrpairmthdfrmula.STEP_SE_CODE = 'RPST0002'                               
   AND tnrpairmthdfrmula.DELETE_AT = 'N'                              
   AND tnrpairmthdfrmula.USE_AT = 'Y'
   AND tnrpairmthdfrmula.STEP_STTUS_CODE = map.GPCI_STTUS_CODE
     WHERE EXISTS (
             SELECT 'T' FROM SRVY_STTUS_DATA 
             WHERE map.SECT_STTUS_CODE = SECT
              AND (map.VMTC_STTUS_CODE IS NULL OR map.VMTC_STTUS_CODE = VMTC)
              AND map.GPCI_STTUS_CODE = GPCI_STTUS
              AND (map.LTC_STTUS_CODE IS NULL OR map.LTC_STTUS_CODE = LCTC_STTUS)
              AND (map.RD_STTUS_CODE IS NULL OR map.RD_STTUS_CODE = RD_STTUS)
              AND (map.AC_STTUS_CODE IS NULL OR map.AC_STTUS_CODE = AC_STTUS)
          )
       AND map.FRMULA_NO = v_FRMULA_NO   
       AND map.USE_AT = 'Y'
       AND map.DELETE_AT = 'N';

   DBMS_OUTPUT.PUT_LINE('v_SECT_STTUS='||v_SECT_STTUS);
    DBMS_OUTPUT.PUT_LINE('v_VMTC_STTUS='||v_VMTC_STTUS);
    DBMS_OUTPUT.PUT_LINE('v_GPCI_STTUS='||v_GPCI_STTUS);
    DBMS_OUTPUT.PUT_LINE('v_LCTC_STTUS='||v_LCTC_STTUS);
    DBMS_OUTPUT.PUT_LINE('v_RD_STTUS='||v_RD_STTUS);
    DBMS_OUTPUT.PUT_LINE('v_AC_STTUS='||v_AC_STTUS);

    DBMS_OUTPUT.PUT_LINE('RET_VALUE='||RET_VALUE);

    RETURN (RET_VALUE);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END; 



/**
 * GPCI 등급 도출
 * 
 * @param p_GRAD_STDR_CODE
 * @param p_APPLC_YEAR
 * @param p_IDX_VAL
 */
CREATE OR REPLACE FUNCTION      FN_GET_IDX_GRADE(p_GRAD_STDR_CODE IN VARCHAR2, p_APPLC_YEAR IN VARCHAR2,  p_IDX_VAL IN NUMBER)
RETURN VARCHAR2 IS
  RET_VALUE     TN_PAV_STTUS_GRAD_STDR.SRVY_INFO_GRAD%TYPE;
BEGIN
    /* 
        포장 상태 등급 (PC_GRADE) 산출
        
        생성일 : 2017-07-21
        생성자 : muhanit.kr
    */
    WITH GRADLIST AS(
    SELECT  
          tnpavsttusgradstdr.SRVY_INFO_GRAD    /* 포장상태등급기준.조사_정보_등급 */
          ,tnpavsttusgradstdr.APPLC_YEAR
          , TO_NUMBER(tnpavsttusgradstdr.APPLC_YEAR) - TO_NUMBER(p_APPLC_YEAR) DIFF_YEAR 
    --      , MAX(tnpavsttusgradstdr.APPLC_YEAR) OVER(PARTITION BY tnpavsttusgradstdr.GRAD_STDR_CODE) MAX_APPLC_YEAR
          , ROW_NUMBER() OVER(ORDER BY tnpavsttusgradstdr.APPLC_YEAR ASC) YEAR_RNK
      FROM GPMS.TN_PAV_STTUS_GRAD_STDR  tnpavsttusgradstdr  /*** 포장상태등급기준 테이블 ***/
     WHERE 1=1
       AND tnpavsttusgradstdr.GRAD_STDR_CODE = p_GRAD_STDR_CODE --'NHPCI'                  /* 포장상태등급기준.등급_기준_코드 */
    --   AND tnpavsttusgradstdr.APPLC_YEAR = '2006'                          /* 포장상태등급기준.적용_년도 */
    --   AND tnpavsttusgradstdr.SRVY_INFO_GRAD = #SRVY_INFO_GRAD#                  /* 포장상태등급기준.조사_정보_등급 */
       AND TO_NUMBER(tnpavsttusgradstdr.GRAD_MUMM_STDR) <= p_IDX_VAL                  /* 포장상태등급기준.등급_최소_기준 */
       AND TO_NUMBER(tnpavsttusgradstdr.GRAD_MXMM_STDR) > p_IDX_VAL                  /* 포장상태등급기준.등급_최대_기준 */
       AND tnpavsttusgradstdr.DELETE_AT = 'N'                            /* 포장상태등급기준.삭제_여부 */
       AND tnpavsttusgradstdr.USE_AT = 'Y'                               /* 포장상태등급기준.사용_여부 */
    )
    SELECT NVL(SRVY_INFO_GRAD,'') INTO RET_VALUE --SRVY_INFO_GRAD
    FROM(
    SELECT 
        SRVY_INFO_GRAD,APPLC_YEAR, 
        DECODE(DIFF_YEAR,0,100000, YEAR_RNK) RNK_NO, 
        ROW_NUMBER() OVER (ORDER BY DECODE(DIFF_YEAR,0,100000, YEAR_RNK) DESC) ORDER_NO   
    FROM GRADLIST
    ) 
    WHERE ORDER_NO=1;
    
      

      RETURN (trim(RET_VALUE));
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN ('');
END FN_GET_IDX_GRADE;



/**
 * 평가 수식 사용 여부 도출
 * 
 * @param p_FRMULA_NM : 적용 수식 명
 */
CREATE OR REPLACE FUNCTION FN_IS_USE_FORMULAR( p_FRMULA_NM IN TN_PAV_FRMULA.FRMULA_NM%TYPE )
RETURN VARCHAR2
IS
    RET_VALUE VARCHAR2(1);
BEGIN
    /* 
        표면 상태 지수(SRC) 사용 여부 확인
        
        생성일 : 2017-07-21
        생성자 : muhanit.kr
    */
    SELECT DECODE( NVL(tnpavfrmula.FRMULA_NO, -1), -1, 'N','Y') 
        INTO RET_VALUE     /* 포장_수식_지수.수식_번호 */
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
     WHERE tnpavfrmula.FRMULA_NM = p_FRMULA_NM                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
    ;
   
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN 'N';

END;




/**
 * 사용자별 메뉴접근 권한 조회
 * 
 * @param p_USER_NO : 사용자 번호
 */
CREATE OR REPLACE FUNCTION FN_GET_USER_MENU_AUTH(p_USER_NO IN NUMBER)
RETURN VARCHAR2 IS
  RET_VALUE     VARCHAR2(4000);
BEGIN
    /* 
        사용자별 메뉴접근 권한 조회 ( 관리자 / 도청사용자인 사용자 그룹 제외 )

        생성일 : 2017-10-24
        생성자 : skc@muhanit.kr
    */

    SELECT LISTAGG(AUTHOR_ID,',') WITHIN GROUP (ORDER BY USER_NO ASC) MENU_AUTH INTO RET_VALUE
      FROM TN_USER_AUTH
     WHERE USER_NO = p_USER_NO
       AND AUTHOR_ID NOT IN ('ROLE_ADMIN', 'ROLE_USER');

    RETURN (TRIM(RET_VALUE));

EXCEPTION 
WHEN OTHERS THEN 
  RETURN ('');
END FN_GET_USER_MENU_AUTH; 