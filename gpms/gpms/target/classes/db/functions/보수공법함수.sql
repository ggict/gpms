/**
 * 보수공법 도출 함수
 * author : skc@muhanit.kr
 * 2017-12-06 
 */


/**
 * 보수공법 도출
 * 
 * @param p_CELL_TYPE : 셀 타입 구분 (일반도로/교차로/특별관리구간 등등) - 공통코드 사용(CELT)
 * @param p_VMTC_GRAD : 교통용량 구분(고용량/중용량/저용량) - 공통코드 사용(VNTC)
 * @param p_GPCI : GPCI 값
 * @param p_LCTC : 선형 균열 지수
 * @param p_AC : 거북등 균열 지수
 * @param p_FRMULA_NM : 적용 수식 명
 */
CREATE OR REPLACE FUNCTION FN_GET_RPAIR_MTHD( p_CELL_TYPE IN VARCHAR2,p_VMTC_GRAD IN VARCHAR2,p_GPCI IN NUMBER,p_LCTC IN NUMBER,p_RD IN NUMBER, p_AC IN NUMBER, p_FRMULA_NM IN VARCHAR2)
RETURN VARCHAR2
IS
    RET_VALUE VARCHAR2(10);
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
    SELECT map.RPAIR_MTHD_CODE
      INTO RET_VALUE
      FROM TN_RPAIR_MTHD_DECSN_MAP map
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