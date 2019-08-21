/**
 * [포장상태 10년치 예측 추이 통계]
 *  
 * @PARAM p_ROUTE_CODE : 노선 코드
 * @PARAM p_DEPT_CODE : 관리기관 코드
 * @PARAM p_MODE : 실행 모드 (ROUTE : 노선통계 / DEPT : 관리기관통계)
 * @PARAM o_OUTPUT : 싱행 결과 데이터 (OUT)
 */
create or replace PROCEDURE      "PRC_STAT_PREDCT" (
/*
    포장상태 10년치 예측 추이 통계
    TMP_STAT_PREDCT 임시 테이블 사용

    생성일 : 2017-11-23
    생성자 : skc@muhanit.kr
*/
p_ROUTE_CODE          VARCHAR2,
p_DEPT_CODE        VARCHAR2,
p_MODE              VARCHAR2,
o_OUTPUT    OUT     SYS_REFCURSOR
)
AS
v_PROCCODE          VARCHAR2(50);
v_PROCMSG           VARCHAR2(1024);

v_GPCI              TN_SM_DTA_LAST_STTUS.GPCI%TYPE;

v_MONTH             NUMBER(10);
v_TOT_MONTH         NUMBER(10);
v_INDEX          	INTEGER := 0;

v_PREDCT_DT         DATE;

v_CURSOR            SYS_REFCURSOR;
v_RESULT_CURSOR		SYS_REFCURSOR;
v_UUID              VARCHAR2(30);

BEGIN

    v_PROCCODE :='false';                    
    v_PROCMSG := '포장상태 10년치 예측 추이 통계 시작';

     -- 기본값 체크
    IF (p_MODE IS NULL) OR (p_MODE = '')
    THEN
      v_PROCCODE := 'false';
      v_PROCMSG := '통계 대상 값이 없습니다.';

      OPEN o_OUTPUT FOR
      SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;

      RETURN;
    END IF;
       
    -- UUID 생성
    SELECT TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS')||dbms_random.string('U', 10)
      INTO v_UUID
      FROM DUAL;

    
    -- 노선 통계
	IF (p_MODE = 'ROUTE')
	THEN
		IF (p_ROUTE_CODE IS NULL) OR (p_ROUTE_CODE = '')
		THEN
			FOR v_erow IN (
				SELECT cellsect.ROAD_GRAD
					 , MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
					 , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
					 , NULL ROUTE_CODE
					 , NULL DEPT_CODE
					 , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
				FROM CELL_SECT cellsect
				LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
				ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
				AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
				AND tnsmdtalaststtus.TRACK = cellsect.TRACK
				AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
				AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT
				AND tnsmdtalaststtus.USE_AT = 'Y'
				AND tnsmdtalaststtus.DELETE_AT = 'N'
				GROUP BY ROLLUP(cellsect.ROAD_GRAD)
            )
            LOOP
                -- 산정일로부터 날짜 계산
                SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_erow.CALC_YEAR || v_erow.CALC_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;
                
                -- 산정일로부터 10년치 예측 데이터 생성
                FOR v_INDEX IN 0 .. 10
                LOOP
                    v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
        
                    -- 일반도로 기준으로 GPCI 예측 데이터 생성
                    v_GPCI := FN_GET_PREDCT_GPCI(v_erow.GPCI, v_TOT_MONTH, 'CELT0001', 'PRDT0009'); 
        
                    -- 예측 날짜 계산
                    SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;
        
                    -- 임시 테이블에 INSERT
                    INSERT INTO TMP_STAT_PREDCT(
                                                    UUID,
                                                    PREDCT_YEAR,
                                                    PREDCT_MT,
                                                    ROAD_GRAD,
                                                    ROUTE_CODE,
                                                    DEPT_CODE,
                                                    GPCI
                                                ) VALUES(
                                                    v_UUID
                                                  , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                                  , TO_CHAR(v_PREDCT_DT, 'MM')
                                                  , v_erow.ROAD_GRAD
                                                  , v_erow.ROUTE_CODE
                                                  , v_erow.DEPT_CODE
                                                  , v_GPCI
                                                );     
                END LOOP;
            END LOOP;

		ELSE
			FOR v_erow IN (
				SELECT cellsect.ROUTE_CODE
					 , cellsect.ROAD_GRAD
					 , MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
					 , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
					 , NULL DEPT_CODE
					 , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
				FROM CELL_SECT cellsect
				LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
				ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
				AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
				AND tnsmdtalaststtus.TRACK = cellsect.TRACK
				AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
				AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT
				AND tnsmdtalaststtus.USE_AT = 'Y'
				AND tnsmdtalaststtus.DELETE_AT = 'N'
				WHERE cellsect.ROUTE_CODE = p_ROUTE_CODE
				GROUP BY cellsect.ROUTE_CODE, cellsect.ROAD_GRAD
            )
            LOOP
                -- 산정일로부터 날짜 계산
                SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_erow.CALC_YEAR || v_erow.CALC_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;
        
                -- 산정일로부터 10년치 예측 데이터 생성
                FOR v_INDEX IN 0 .. 10
                LOOP
                    v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
        
                    -- 일반도로 기준으로 GPCI 예측 데이터 생성
                    v_GPCI := FN_GET_PREDCT_GPCI(v_erow.GPCI, v_TOT_MONTH, 'CELT0001', 'PRDT0009'); 
        
                    -- 예측 날짜 계산
                    SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;
        
                    -- 임시 테이블에 INSERT
                    INSERT INTO TMP_STAT_PREDCT(
                                                    UUID,
                                                    PREDCT_YEAR,
                                                    PREDCT_MT,
                                                    ROAD_GRAD,
                                                    ROUTE_CODE,
                                                    DEPT_CODE,
                                                    GPCI
                                                ) VALUES(
                                                    v_UUID
                                                  , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                                  , TO_CHAR(v_PREDCT_DT, 'MM')
                                                  , v_erow.ROAD_GRAD
                                                  , v_erow.ROUTE_CODE
                                                  , v_erow.DEPT_CODE
                                                  , v_GPCI
                                                );     
                END LOOP;
            END LOOP;
		END IF;
    -- 관리기관별 통계
	ELSIF (p_MODE = 'DEPT')
	THEN	
		IF (p_DEPT_CODE IS NULL) OR (p_DEPT_CODE = '')
		THEN
			FOR v_erow IN (
				SELECT tcdept.DEPT_CODE
					 , MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
					 , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
					 , NULL ROUTE_CODE
					 , NULL ROAD_GRAD
					 , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
				FROM TC_DEPT tcdept
                LEFT OUTER JOIN CELL_SECT cellsect
                ON tcdept.DEPT_CODE = cellsect.DEPT_CODE
				LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
				ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
				AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
				AND tnsmdtalaststtus.TRACK = cellsect.TRACK
				AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
				AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT
				AND tnsmdtalaststtus.USE_AT = 'Y'
				AND tnsmdtalaststtus.DELETE_AT = 'N'
                WHERE tcdept.DEPT_CODE IN ('6411399','6411799')
				GROUP BY ROLLUP(tcdept.DEPT_CODE)
            )
            LOOP
                -- 산정일로부터 날짜 계산
                SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_erow.CALC_YEAR || v_erow.CALC_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;
       
                -- 산정일로부터 10년치 예측 데이터 생성
                FOR v_INDEX IN 0 .. 10
                LOOP
                    v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
        
                    -- 일반도로 기준으로 GPCI 예측 데이터 생성
                    v_GPCI := FN_GET_PREDCT_GPCI(v_erow.GPCI, v_TOT_MONTH, 'CELT0001', 'PRDT0009'); 
        
                    -- 예측 날짜 계산
                    SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;
        
                    -- 임시 테이블에 INSERT
                    INSERT INTO TMP_STAT_PREDCT(
                                                    UUID,
                                                    PREDCT_YEAR,
                                                    PREDCT_MT,
                                                    ROAD_GRAD,
                                                    ROUTE_CODE,
                                                    DEPT_CODE,
                                                    GPCI
                                                ) VALUES(
                                                    v_UUID
                                                  , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                                  , TO_CHAR(v_PREDCT_DT, 'MM')
                                                  , v_erow.ROAD_GRAD
                                                  , v_erow.ROUTE_CODE
                                                  , v_erow.DEPT_CODE
                                                  , v_GPCI
                                                );     
                END LOOP;
            END LOOP;

		ELSE
			FOR v_erow IN (
				SELECT tcdept.DEPT_CODE
					 , MAX(NVL(tnsmdtalaststtus.CALC_YEAR, TO_CHAR(SYSDATE, 'YYYY'))) CALC_YEAR
					 , MAX(NVL(tnsmdtalaststtus.CALC_MT, TO_CHAR(SYSDATE, 'MM'))) CALC_MT
					 , NULL ROUTE_CODE
					 , NULL ROAD_GRAD
					 , ROUND(AVG(NVL(tnsmdtalaststtus.gpci,0)),2) GPCI
				FROM TC_DEPT tcdept
                LEFT OUTER JOIN CELL_SECT cellsect
                ON tcdept.DEPT_CODE = cellsect.DEPT_CODE
				LEFT OUTER JOIN TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
				ON tnsmdtalaststtus.ROUTE_CODE = cellsect.ROUTE_CODE
				AND tnsmdtalaststtus.DIRECT_CODE = cellsect.DIRECT_CODE
				AND tnsmdtalaststtus.TRACK = cellsect.TRACK
				AND tnsmdtalaststtus.STRTPT = cellsect.STRTPT
				AND tnsmdtalaststtus.ENDPT = cellsect.ENDPT
				AND tnsmdtalaststtus.USE_AT = 'Y'
				AND tnsmdtalaststtus.DELETE_AT = 'N'
                WHERE tcdept.DEPT_CODE IN ('6411399','6411799')
				  AND tcdept.DEPT_CODE = p_DEPT_CODE
				GROUP BY tcdept.DEPT_CODE
            )
            LOOP
                -- 산정일로부터 날짜 계산
                SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_erow.CALC_YEAR || v_erow.CALC_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;
           
                -- 산정일로부터 10년치 예측 데이터 생성
                FOR v_INDEX IN 0 .. 10
                LOOP
                    v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
        
                    -- 일반도로 기준으로 GPCI 예측 데이터 생성
                    v_GPCI := FN_GET_PREDCT_GPCI(v_erow.GPCI, v_TOT_MONTH, 'CELT0001', 'PRDT0009'); 
        
                    -- 예측 날짜 계산
                    SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;
        
                    -- 임시 테이블에 INSERT
                    INSERT INTO TMP_STAT_PREDCT(
                                                    UUID,
                                                    PREDCT_YEAR,
                                                    PREDCT_MT,
                                                    ROAD_GRAD,
                                                    ROUTE_CODE,
                                                    DEPT_CODE,
                                                    GPCI
                                                ) VALUES(
                                                    v_UUID
                                                  , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                                  , TO_CHAR(v_PREDCT_DT, 'MM')
                                                  , v_erow.ROAD_GRAD
                                                  , v_erow.ROUTE_CODE
                                                  , v_erow.DEPT_CODE
                                                  , v_GPCI
                                                );     
                END LOOP;
            END LOOP;

		END IF;
	ELSE
		v_PROCCODE := 'false';
		v_PROCMSG := '통계 대상 값이 없습니다.';

		OPEN o_OUTPUT FOR
		SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;

		RETURN;
	END IF;
    
	
    
	v_PROCMSG :='포장상태 10년치 예측 추이 통계 완료';   
	v_PROCCODE :='true';

    IF (((p_DEPT_CODE IS NOT NULL) OR (p_DEPT_CODE <> '')) AND p_MODE = 'DEPT') OR 
        (((p_ROUTE_CODE IS NOT NULL) OR (p_ROUTE_CODE <> '')) AND p_MODE = 'ROUTE')
    THEN
	-- 결과값 도출
        OPEN v_RESULT_CURSOR FOR
            SELECT tmpstatpredct.PREDCT_YEAR AS "PREDCT_YEAR"
                 , tmpstatpredct.PREDCT_MT AS "PREDCT_MT"
                 , NULL AS "ROAD_GPCI_1"
                 , NULL AS "ROAD_GPCI_2"
                 , NULL AS "ROAD_GPCI_3"
                 , NULL AS "DEPT_GPCI_1"
                 , NULL AS "DEPT_GPCI_2"
                 , NULL AS "DEPT_GPCI_3"
                 , NVL(gradecode.CODE_NM, '경기도 전체') AS "ROAD_GRAD"
                 , tmpstatpredct.ROUTE_CODE AS "ROUTE_CODE"
                 , NVL(tcdept.LOWEST_DEPT_NM, '경기도 전체') AS "DEPT_NM"
                 , tmpstatpredct.GPCI AS "GPCI"
                 , v_PROCMSG AS "PROCMSG"             
                 , v_PROCCODE AS "PROCCODE"
             FROM TMP_STAT_PREDCT tmpstatpredct
             LEFT OUTER JOIN TC_CODE gradecode
               ON gradecode.CODE_VAL = tmpstatpredct.ROAD_GRAD
              AND gradecode.USE_AT = 'Y'
              AND gradecode.DELETE_AT = 'N'
             LEFT OUTER JOIN TC_DEPT tcdept
               ON tcdept.DEPT_CODE = tmpstatpredct.DEPT_CODE
              AND tcdept.USE_AT = 'Y'
              AND tcdept.DELETE_AT = 'N'
            WHERE tmpstatpredct.UUID = v_UUID
         ORDER BY tmpstatpredct.PREDCT_YEAR, tmpstatpredct.PREDCT_MT;
    ELSE
        OPEN v_RESULT_CURSOR FOR
            SELECT tmpstatpredct.PREDCT_YEAR AS "PREDCT_YEAR"
                 , tmpstatpredct.PREDCT_MT AS "PREDCT_MT"
                 , SUM(DECODE(tmpstatpredct.ROAD_GRAD, 'RDGD0001', tmpstatpredct.GPCI)) AS "ROAD_GPCI_1" /* 도로등급 : 국지도 GPCI  */
                 , SUM(DECODE(tmpstatpredct.ROAD_GRAD, 'RDGD0002', tmpstatpredct.GPCI)) AS "ROAD_GPCI_2" /* 도로등급 : 지방도 GPCI  */
                 , SUM(DECODE(tmpstatpredct.ROAD_GRAD, NULL, tmpstatpredct.GPCI)) AS "ROAD_GPCI_3" /* 도로등급 : 전체 GPCI  */
                 , SUM(DECODE(tcdept.DEPT_CODE, '6411399', tmpstatpredct.GPCI)) AS "DEPT_GPCI_1" /* 부서 :  도로건설과 GPCI  */
                 , SUM(DECODE(tcdept.DEPT_CODE, '6411799', tmpstatpredct.GPCI)) AS "DEPT_GPCI_2" /* 부서 :  북부도로과 GPCI  */
                 , SUM(DECODE(tcdept.DEPT_CODE, NULL, tmpstatpredct.GPCI)) AS "DEPT_GPCI_3" /* 부서 :  전체 GPCI  */
                 , NULL AS "ROAD_GRAD"
                 , NULL AS "ROUTE_CODE"
                 , NULL AS "DEPT_NM"
                 , NULL AS "GPCI"
                 , v_PROCMSG AS "PROCMSG"             
                 , v_PROCCODE AS "PROCCODE"
             FROM TMP_STAT_PREDCT tmpstatpredct
             LEFT OUTER JOIN TC_CODE gradecode
               ON gradecode.CODE_VAL = tmpstatpredct.ROAD_GRAD
              AND gradecode.USE_AT = 'Y'
              AND gradecode.DELETE_AT = 'N'
             LEFT OUTER JOIN TC_DEPT tcdept
               ON tcdept.DEPT_CODE = tmpstatpredct.DEPT_CODE
              AND tcdept.USE_AT = 'Y'
              AND tcdept.DELETE_AT = 'N'
            WHERE tmpstatpredct.UUID = v_UUID
         GROUP BY tmpstatpredct.PREDCT_YEAR, tmpstatpredct.PREDCT_MT
         ORDER BY tmpstatpredct.PREDCT_YEAR, tmpstatpredct.PREDCT_MT;
        
    END IF;


	o_OUTPUT := v_RESULT_CURSOR;
	



EXCEPTION  
    WHEN OTHERS
    THEN
        v_PROCMSG := SUBSTR(SQLERRM,1,1000);
        v_PROCCODE :='false';

        OPEN o_OUTPUT FOR
        SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;
    RAISE;      
END PRC_STAT_PREDCT;