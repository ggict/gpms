/**
 * [예산 수준별 시나리오 분석]
 *  
 * @PARAM p_UNTPCS : 분석 대상 예산 목록
 * @PARAM p_DEPT_CODE : 예산집행기관 코드
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_OUTPUT : 싱행 결과 데이터 (OUT)
 */
create or replace PROCEDURE      "PRC_STAT_UNTPC" (
/*
    예산 수준별 시나리오 분석
    TMP_STAT_UNTPC 임시 테이블 사용

    생성일 : 2017-11-28
    생성자 : skc@muhanit.kr
*/
p_UNTPCS          	VARCHAR2,
p_DEPT_CODE         VARCHAR2,
p_MODE              VARCHAR2,
o_OUTPUT    OUT     SYS_REFCURSOR
)
AS
v_PROCCODE          VARCHAR2(50);
v_PROCMSG           VARCHAR2(1024);

v_ROAD_A			NUMBER(22,3);
v_ROAD_A_RATE		NUMBER(10,2);
v_TOT_ROAD_A		NUMBER(22,3);
v_AVG_UNTPC			NUMBER(10,2);

v_GPCI              TN_SM_DTA_LAST_STTUS.GPCI%TYPE;
v_TRG_GPCI          TN_SM_DTA_LAST_STTUS.GPCI%TYPE;
v_CALC_GPCI			TN_SM_DTA_LAST_STTUS.GPCI%TYPE;
v_CNTRWK_AFT_GPCI   TN_SM_DTA_LAST_STTUS.GPCI%TYPE;

v_MONTH             NUMBER(10);
v_TOT_MONTH         NUMBER(10);
v_CALC_MONTH		NUMBER(10,2);
v_INDEX          	INTEGER := 0;

v_CALC_YEAR         VARCHAR2(4);
v_CALC_MT           VARCHAR2(2);
v_PREDCT_DT         DATE;

v_CURSOR            SYS_REFCURSOR;
v_UUID              VARCHAR2(30);

CURSOR UNTPC_LIST (
    i_UNTPCS VARCHAR2
) IS 
SELECT REGEXP_SUBSTR(i_UNTPCS,'[^,]+', 1, LEVEL) UNTPC FROM DUAL
   CONNECT BY REGEXP_SUBSTR(i_UNTPCS, '[^,]+', 1, LEVEL) IS NOT NULL;

-- DECLARE RAISE_EXT exception;
BEGIN

    v_PROCCODE :='false';                    
    v_PROCMSG := '예산 수준별 시나리오 분석 시작';
    v_CNTRWK_AFT_GPCI := NULL;
    v_CALC_GPCI := NULL;
    
    -- 기본값 체크
    IF (p_UNTPCS IS NULL) OR (p_UNTPCS = '')
    THEN
      v_PROCCODE := 'false';
      v_PROCMSG := '투입예산 파라미터에 값이 없습니다.';

      OPEN o_OUTPUT FOR
      SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;

      RETURN;
    END IF;


	-- UUID 생성
    SELECT TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS')||dbms_random.string('U', 10)
      INTO v_UUID
      FROM DUAL;

    IF (p_DEPT_CODE IS NULL) OR (p_DEPT_CODE = '')
    THEN
        -- 최근 산정된 경기도 전체 GPCI 호출
        SELECT MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
             , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
             , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
             --, ROUND(AVG(tnsmdtalaststtus.gpci),2) GPCI
         INTO v_CALC_YEAR, v_CALC_MT, v_TRG_GPCI
        FROM CELL_SECT cellsect
        LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
          ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
         AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
         AND tnsmdtalaststtus.TRACK = cellsect.TRACK
         AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
         AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT 
         AND tnsmdtalaststtus.USE_AT = 'Y'
         AND tnsmdtalaststtus.DELETE_AT = 'N';
    ELSE
        -- 최근 산정된 경기도 전체 GPCI 호출
        SELECT MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
             , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
             , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
             --, ROUND(AVG(tnsmdtalaststtus.gpci),2) GPCI
         INTO v_CALC_YEAR, v_CALC_MT, v_TRG_GPCI
        FROM CELL_SECT cellsect
        LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
          ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
         AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
         AND tnsmdtalaststtus.TRACK = cellsect.TRACK
         AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
         AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT 
         AND tnsmdtalaststtus.USE_AT = 'Y'
         AND tnsmdtalaststtus.DELETE_AT = 'N'
       WHERE cellsect.DEPT_CODE = p_DEPT_CODE;
    END IF;

	-- 산정일로부터 날짜 계산
    SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_CALC_YEAR || v_CALC_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;


	-- 경기도 전체 포장도로 면적 조회
	SELECT ROUND(SUM(ROAD_A), 0) ROAD_A INTO v_TOT_ROAD_A FROM CELL_SECT;


	-- 평균 공사비 단가 조회
	SELECT ROUND(AVG(TOT_AMOUNT),0) 
	  INTO v_AVG_UNTPC
	  FROM TN_RPAIR_MTHD_UNTPC
	 WHERE USE_AT = 'Y' 
	   AND DELETE_AT = 'N'
	   AND TOT_AMOUNT <> 0;


    FOR v_erow IN UNTPC_LIST (p_UNTPCS)
    LOOP 
        v_GPCI := v_TRG_GPCI;
        
		-- 10년치 데이터 생성
		FOR v_INDEX IN 0 .. 10
        LOOP

            IF(v_INDEX = 0) THEN
                v_TOT_MONTH := v_MONTH + 12;
            ELSE
                v_TOT_MONTH :=  12;
            END IF;
            
            v_CALC_GPCI := NULL;
            v_CNTRWK_AFT_GPCI := NULL;
            
			-- 공사 후 GPCI로 인한 예측 GPCI 산출
			-- 처음 실행 시, 최근 산정된 경기도 전체 GPCI로 인한 GPCI 산출
			
            SELECT FN_GET_PREDCT_GPCI(v_GPCI, v_TOT_MONTH, 'CELT0001', 'PRDT0009') INTO v_CALC_GPCI FROM DUAL;
			
			-- 예산에 따른 공사 면적 측정
			v_ROAD_A := ROUND(v_erow.UNTPC / v_AVG_UNTPC,3);

			-- 공사 면적 비율 산출
			v_ROAD_A_RATE := ROUND((v_ROAD_A / v_TOT_ROAD_A) * 100,2);

			-- 공사 후 GPCI 산출
            -- 보고서 수식 : ROUND(v_ROAD_A_RATE * 10 + (1 - v_ROAD_A_RATE) * v_CALC_GPCI,2)
            -- 수치값 기준으로 유추한 적정 수식 적용 
			v_CNTRWK_AFT_GPCI := LEAST(ROUND((v_ROAD_A_RATE * 10 + (100 - v_ROAD_A_RATE) * v_CALC_GPCI)/100,2),10);

			-- 예측 날짜 계산
			SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;

			-- 계산된 정보를 임시테이블에 입력
			INSERT INTO TMP_STAT_UNTPC (
											UUID,
											PREDCT_YEAR,
											CNTRWK_UNTPC,
											ROAD_A,
											ROAD_A_RATE,
											GPCI,
											CALC_GPCI,
											CNTRWK_AFT_GPCI
										) VALUES (
											v_UUID
										  , TO_CHAR(v_PREDCT_DT, 'YYYY')
										  , v_erow.UNTPC
										  , v_ROAD_A
										  , v_ROAD_A_RATE
                                          , v_GPCI
										  , v_CALC_GPCI
										  , v_CNTRWK_AFT_GPCI
										);
                                        
            v_GPCI := v_CNTRWK_AFT_GPCI;
            
		END LOOP;
    END LOOP;

	v_PROCMSG :='예산 수준별 시나리오 분석 완료';   
	v_PROCCODE :='true';

	OPEN v_CURSOR FOR
		SELECT PREDCT_YEAR
			 , CNTRWK_UNTPC
			 , ROAD_A
			 , ROAD_A_RATE
			 , GPCI
             , FN_GET_PREDCT_MONTH(GPCI, 'CELT0001', 'PRDT0009') CALC_MONTH
			 , CALC_GPCI
			 , CNTRWK_AFT_GPCI
			 , v_PROCMSG AS "PROCMSG"             
             , v_PROCCODE AS "PROCCODE"
		  FROM TMP_STAT_UNTPC
		 WHERE UUID = v_UUID
		 ORDER BY CNTRWK_UNTPC, PREDCT_YEAR;

	o_OUTPUT := v_CURSOR;


EXCEPTION  
    WHEN OTHERS
    THEN
        v_PROCMSG := SUBSTR(SQLERRM,1,1000);
        v_PROCCODE :='false';

        OPEN o_OUTPUT FOR
        SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;
    RAISE;      
END PRC_STAT_UNTPC;