/**
 * 예측모델 수식 도출 함수
 * author : skc@muhanit.kr
 * 2017-12-06 
 */

/**
 * 포장상태 평가 공용연수 도출 
 * 
 * @param p_VAL : 도출 대상 값
 * @param p_CELL_TYPE : 셀 타입 구분 (일반도로/교차로/특별관리구간 등등) - 공통코드 사용(CELT)
 * @param p_PRDTFML_TRGET : 예측모댈 수식 적용 대상 - 공통코드 사용(PRDT)
 */
CREATE OR REPLACE FUNCTION FN_GET_PREDCT_MONTH( p_VAL IN NUMBER,p_CELL_TYPE IN VARCHAR2, p_PRDTFML_TRGET IN VARCHAR2)
RETURN NUMBER
IS
    RET_VALUE NUMBER;
BEGIN
    /* 
        섹션별 공용성 예측 모델에 따른 예측 개월 수 산출

        생성일 : 2017-08-25
        생성자 : skc@muhanit.kr
    */
    WITH PREDCTFORMULAIDX AS( 
    SELECT NVL(RK_A,0) RK_A
         , NVL(RK_B,0) RK_B
         , NVL(RK_C,0) RK_C
      FROM TN_PREDCT_FRMULA_IDX 
     WHERE USE_AT = 'Y' 
       AND DELETE_AT = 'N' 
       AND CELL_TYPE = p_CELL_TYPE
       AND PRDTFML_TRGET = p_PRDTFML_TRGET)
    SELECT CASE WHEN RK_A = 0 AND RK_B = 0 AND RK_C = 0 THEN 0 
                WHEN p_VAL = 0 THEN 0 
                WHEN p_VAL = 10 THEN 0 
                WHEN p_PRDTFML_TRGET = 'PRDT0009' THEN ROUND(POWER(10, (LOG(10,(RK_A - p_VAL)/ RK_B)) * 1/RK_C),2) /* GPCI */
                ELSE ROUND(POWER(10,((LOG(10,p_VAL)-LOG(10,RK_B))/RK_C)),2) END
      INTO RET_VALUE
      FROM PREDCTFORMULAIDX;

    RET_VALUE :=GREATEST(RET_VALUE, 0);   

    RETURN (RET_VALUE);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 예측모델 적용 개별 결함 값 도출
 * 수식 변경 전 사용 모델(개별 결함값별 예측 값 도출 후 GPCI 산출 방법)
 * 
 * @param p_VAL : 도출 대상 값
 * @param p_MONTH : 공용연수
 * @param p_CELL_TYPE : 셀 타입 구분 (일반도로/교차로/특별관리구간 등등) - 공통코드 사용(CELT)
 * @param p_PRDTFML_TRGET : 예측모댈 수식 적용 대상 - 공통코드 사용(PRDT)
 */
CREATE OR REPLACE FUNCTION FN_GET_PREDCT_VAL( p_VAL IN NUMBER,p_MONTH IN NUMBER,p_CELL_TYPE IN VARCHAR2,p_PRDTFML_TRGET IN VARCHAR2)
RETURN NUMBER
IS
    RET_VALUE NUMBER;
    v_MONTH NUMBER;
BEGIN
    /* 
	        개월 수에 따른 예측 값 산출
	
	        생성일 : 2017-08-24
	        생성자 : skc@muhanit.kr
    */
    
    -- 공용 수명 도출
    v_MONTH := FN_GET_PREDCT_MONTH(p_VAL, p_CELL_TYPE, p_PRDTFML_TRGET);
    
    WITH PREDCTFORMULAIDX AS( 
    SELECT NVL(RK_A,0) RK_A
         , NVL(RK_B,0) RK_B
         , NVL(RK_C,0) RK_C
      FROM TN_PREDCT_FRMULA_IDX 
     WHERE USE_AT = 'Y' 
       AND DELETE_AT = 'N' 
       AND CELL_TYPE = p_CELL_TYPE
       AND PRDTFML_TRGET = p_PRDTFML_TRGET)
    SELECT CASE WHEN RK_A = 0 AND RK_B = 0 AND RK_C = 0 THEN p_VAL 
                ELSE RK_A + (RK_B * power((v_MONTH + p_MONTH),RK_C)) END
      INTO RET_VALUE 
     FROM PREDCTFORMULAIDX;

    RET_VALUE :=GREATEST(RET_VALUE, 0);   

    RETURN (RET_VALUE);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 예측모델 적용 GPCI 값 도출
 * 수식 변경 후 사용 모델(개별 결함값 제외, GPCI 값으로만 산출 방법)
 * 
 * @param p_VAL : GPCI 값
 * @param p_MONTH : 공용연수
 * @param p_CELL_TYPE : 셀 타입 구분 (일반도로/교차로/특별관리구간 등등) - 공통코드 사용(CELT)
 * @param p_PRDTFML_TRGET : 예측모댈 수식 적용 대상 - 공통코드 사용(PRDT)
 */
CREATE OR REPLACE FUNCTION FN_GET_PREDCT_GPCI( p_VAL IN NUMBER,p_MONTH IN NUMBER,p_CELL_TYPE IN VARCHAR2,p_PRDTFML_TRGET IN VARCHAR2)
RETURN NUMBER
IS
    RET_VALUE NUMBER;
    v_MONTH NUMBER;
BEGIN
    /* 
	        개월 수에 따른 예측 GPCI 산출
	
	        생성일 : 2017-11-13
	        생성자 : skc@muhanit.kr
    */

    -- 공용 수명 도출
    v_MONTH := FN_GET_PREDCT_MONTH(p_VAL, p_CELL_TYPE, p_PRDTFML_TRGET);

    WITH PREDCTFORMULAIDX AS( 
    SELECT NVL(RK_A,0) RK_A
         , NVL(RK_B,0) RK_B
         , NVL(RK_C,0) RK_C
      FROM TN_PREDCT_FRMULA_IDX 
     WHERE USE_AT = 'Y' 
       AND DELETE_AT = 'N' 
       AND CELL_TYPE = p_CELL_TYPE
       AND PRDTFML_TRGET = p_PRDTFML_TRGET)
    SELECT CASE WHEN RK_A = 0 AND RK_B = 0 AND RK_C = 0 THEN p_VAL 
                WHEN p_VAL = 0 THEN 0
                ELSE RK_A - (RK_B * power((v_MONTH + p_MONTH),RK_C)) END
      INTO RET_VALUE 
     FROM PREDCTFORMULAIDX;

    RET_VALUE :=GREATEST(RET_VALUE, 0);   

    RETURN (RET_VALUE);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;