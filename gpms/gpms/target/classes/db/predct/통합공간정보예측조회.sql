/**
 * [통합정보조회] - 예측모델 적용
 * 산정일 예측 정보 / 10년치 예측 정보 조회 
 * 
 * @PARAM p_CELL_IDS : 적용 대상 셀 ID 리스트
 * @PARAM p_PREDCT_CEN : 10년치 실행 여부 (FALSE : 산정일 / TRUE : 10년치 예측정보 조회)
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_OUTPUT : 싱행 결과 코드 (OUT)
 */
create or replace PROCEDURE      "PRC_CLAC_PREDCT_EVL" (
/*
    통합정보조회 예측모델 적용(산정일 예측/ 10년치 예측)
    TMP_CLAC_PREDCT_EVL 임시 테이블 사용

    생성일 : 2017-09-07
    생성자 : skc@muhanit.kr
*/
p_CELL_IDS          VARCHAR2,
p_PREDCT_CEN        VARCHAR2,
p_MODE              VARCHAR2,
o_OUTPUT    OUT     SYS_REFCURSOR
)
AS
v_PROCCODE          VARCHAR2(50);
v_PROCMSG           VARCHAR2(1024);

v_AC_LOW            NUMBER;
v_AC_MED            NUMBER;
v_AC_HI             NUMBER;
v_BLOCK_CR_LOW      NUMBER;
v_BLOCK_CR_MED      NUMBER;
v_BLOCK_CR_HI       NUMBER;
v_LC_LOW            NUMBER;
v_LC_MED            NUMBER;
v_LC_HI             NUMBER;
v_TC_LOW            NUMBER;
v_TC_MED            NUMBER;
v_TC_HI             NUMBER;
v_PTCHG_CR          NUMBER;
v_POTHOLE_CR        NUMBER;
v_RD_VAL            NUMBER;
v_RD_LOW            NUMBER;
v_RD_MED            NUMBER;
v_RD_HI             NUMBER;
v_IRI_VAL           NUMBER;
v_CALC_RD_VAL       NUMBER;

v_AC_IDX            NUMBER;
v_BC_IDX            NUMBER;
v_LC_IDX            NUMBER;
v_TC_IDX            NUMBER;
v_PTCHG_IDX         NUMBER;
v_POT_IDX           NUMBER;
v_RD_IDX            NUMBER;

v_RCI               TN_SM_DTA_GNL_STTUS.RCI%TYPE;
v_SCR               TN_SM_DTA_GNL_STTUS.SCR%TYPE;
v_GPCI              TN_SM_DTA_GNL_STTUS.GPCI%TYPE;
v_TRG_GPCI          TN_SM_DTA_GNL_STTUS.GPCI%TYPE;

v_PROCESS_LOG_NO    TL_SRVY_DTA_LOG.PROCESS_LOG_NO%TYPE;
v_MONTH             NUMBER(10);
v_TOT_MONTH         NUMBER(10);
v_INDEX          	INTEGER := 0;

v_CALC_YEAR         VARCHAR2(4);
v_CALC_MT           VARCHAR2(2);
v_PREDCT_DT         DATE;
v_CNTRWK_HIST_CNT   NUMBER;
v_CNTRWK_DT         VARCHAR2(6);
v_DMG_CUZ			DMG_CUZ;

v_CURSOR            SYS_REFCURSOR;
v_UUID              VARCHAR2(30);

CURSOR predct_data (
    i_CELL_IDS VARCHAR2
) IS 
WITH CELLS AS(
    SELECT REGEXP_SUBSTR(i_CELL_IDS,'[^,]+', 1, LEVEL) CELL_ID FROM DUAL
   CONNECT BY REGEXP_SUBSTR(i_CELL_IDS, '[^,]+', 1, LEVEL) IS NOT NULL
)
   SELECT tnmummsctnsrvydta.DTA_NO
		 , tnmummsctnsrvydta.SRVY_NO
		 , tnmummsctnsrvydta.SRVY_YEAR
		 , tnmummsctnsrvydta.SRVY_MT
		 , tnmummsctnsrvydta.ROUTE_CODE
		 , tnmummsctnsrvydta.DIRECT_CODE
		 , tnmummsctnsrvydta.TRACK
		 , tnmummsctnsrvydta.STRTPT
		 , tnmummsctnsrvydta.ENDPT
		 , tnmummsctnsrvydta.IRI_VAL
		 , tnmummsctnsrvydta.RD_VAL
		 , tnmummsctnsrvydta.RD_IDX
		 , tnmummsctnsrvydta.SRVY_DE
		 , tnmummsctnsrvydta.FRMULA_NO
		 , tnmummsctnsrvydta.AC_LOW
		 , tnmummsctnsrvydta.AC_MED
		 , tnmummsctnsrvydta.AC_HI
		 , tnmummsctnsrvydta.BLOCK_CR_LOW
		 , tnmummsctnsrvydta.BLOCK_CR_MED
		 , tnmummsctnsrvydta.BLOCK_CR_HI
		 , tnmummsctnsrvydta.LC_LOW
		 , tnmummsctnsrvydta.LC_MED
		 , tnmummsctnsrvydta.LC_HI
		 , tnmummsctnsrvydta.TC_LOW
		 , tnmummsctnsrvydta.TC_MED
		 , tnmummsctnsrvydta.TC_HI
		 , tnmummsctnsrvydta.RD_LOW
		 , tnmummsctnsrvydta.RD_MED
		 , tnmummsctnsrvydta.RD_HI
		 , tnmummsctnsrvydta.PTCHG_CR
		 , tnmummsctnsrvydta.POTHOLE_CR
		 , tnmummsctnsrvydta.AC_IDX
		 , tnmummsctnsrvydta.LC_IDX
		 , tnmummsctnsrvydta.TC_IDX
		 , tnmummsctnsrvydta.PTCHG_IDX
		 , tnmummsctnsrvydta.POTHOLE_IDX
		 , tnmummsctnsrvydta.BC_IDX
		 , tnmummsctnsrvydta.RCI
		 , tnmummsctnsrvydta.SCR
		 , tnmummsctnsrvydta.GPCI
		 , cell10.CELL_TYPE
	  FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta
	 INNER JOIN CELL_10 cell10
		ON cell10.ROUTE_CODE = tnmummsctnsrvydta.ROUTE_CODE
	   AND cell10.DIRECT_CODE = tnmummsctnsrvydta.DIRECT_CODE
	   AND cell10.TRACK = tnmummsctnsrvydta.TRACK
	   AND cell10.STRTPT = tnmummsctnsrvydta.STRTPT
	   AND cell10.ENDPT = tnmummsctnsrvydta.ENDPT
     INNER JOIN CELLS
        ON CELLS.CELL_ID = cell10.CELL_ID
	 WHERE tnmummsctnsrvydta.USE_AT = 'Y'
	   AND tnmummsctnsrvydta.DELETE_AT = 'N'
	   AND tnmummsctnsrvydta.LAST_DTA_AT = 'Y';

-- DECLARE RAISE_EXT exception;
BEGIN

    v_PROCCODE :='false';                    
    v_PROCMSG := '통합정보조회 예측 모델 처리 시작';
    v_CALC_YEAR := TO_CHAR(SYSDATE, 'YYYY');
    v_CALC_MT   := TO_CHAR(SYSDATE, 'MM');

    -- 기본값 체크
    IF (p_CELL_IDS IS NULL) OR (p_CELL_IDS = '')
    THEN
      v_PROCCODE := 'false';
      v_PROCMSG := '셀ID 파라미터에 값이 없습니다.';
      
      OPEN o_OUTPUT FOR
      SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;
            
      RETURN;
    END IF;

	-- 기본값 체크
    IF (p_PREDCT_CEN IS NULL) OR (p_PREDCT_CEN = '')
    THEN
      v_PROCCODE := 'false';
      v_PROCMSG := '예측모델 파라미터에 값이 없습니다.';
      
      OPEN o_OUTPUT FOR
      SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;
      
      RETURN;
    END IF;
   
    FOR v_erow IN predct_data (p_CELL_IDS)
    LOOP 
         -- 공사 이력 체크(조사일 - 현재 사이) : 공사 여부로만 판단
        SELECT COUNT(*), MAX(SUBSTR(tncntrwkdtl.RPAIR_END_DE,0,6)) 
          INTO v_CNTRWK_HIST_CNT, v_CNTRWK_DT
          FROM TN_CNTRWK_CELL_INFO tncntrwkcellinfo
         INNER JOIN TN_CNTRWK_DTL tncntrwkdtl
            ON tncntrwkdtl.DETAIL_CNTRWK_ID = tncntrwkcellinfo.DETAIL_CNTRWK_ID
           AND tncntrwkdtl.USE_AT = 'Y'
           AND tncntrwkdtl.DELETE_AT = 'N'
         INNER JOIN CELL_10 cell10
            ON cell10.CELL_ID = tncntrwkcellinfo.PAV_CELL_ID
         WHERE cell10.ROUTE_CODE = v_erow.ROUTE_CODE
           AND cell10.DIRECT_CODE = v_erow.DIRECT_CODE
           AND cell10.TRACK = v_erow.TRACK
           AND cell10.STRTPT >= v_erow.STRTPT
           AND cell10.ENDPT <= v_erow.ENDPT
           AND SUBSTR(tncntrwkdtl.RPAIR_END_DE,0,6) <= v_erow.SRVY_YEAR || v_erow.SRVY_MT
           AND TO_CHAR(SYSDATE, 'YYYYMM') >= v_erow.SRVY_YEAR || v_erow.SRVY_MT;


        -- 공사 이력이 있는경우 초기화 및 공사일로부터 개월 수 체크
        IF (v_CNTRWK_HIST_CNT = 0) OR (v_CNTRWK_DT IS NULL)
        THEN
            v_AC_LOW := v_erow.AC_LOW;
            v_AC_MED := v_erow.AC_MED;
            v_AC_HI := v_erow.AC_HI;
            v_BLOCK_CR_LOW := v_erow.BLOCK_CR_LOW;
            v_BLOCK_CR_MED := v_erow.BLOCK_CR_MED;
            v_BLOCK_CR_HI := v_erow.BLOCK_CR_HI; 
            v_LC_LOW := v_erow.LC_LOW;
            v_LC_MED := v_erow.LC_MED;
            v_LC_HI := v_erow.LC_HI;
            v_TC_LOW := v_erow.TC_LOW;
            v_TC_MED := v_erow.TC_MED;
            v_TC_HI := v_erow.TC_HI;
            v_PTCHG_CR := v_erow.PTCHG_CR;
            v_POTHOLE_CR := v_erow.POTHOLE_CR;
            v_RD_VAL := v_erow.RD_VAL;
            v_RD_LOW := v_erow.RD_LOW;
            v_RD_MED := v_erow.RD_MED;
            v_RD_HI := v_erow.RD_HI;
            v_IRI_VAL := v_erow.IRI_VAL;
            v_TRG_GPCI := v_erow.GPCI;
            
            -- 없는 경우 GPCI을 통한 개월 수 역계산 + 조사일로부터 현재 날짜 역계산
            SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_erow.SRVY_YEAR || v_erow.SRVY_MT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;

        ELSE
            -- 결함값 초기화
            SELECT NVL(INITVAL_AC_LOW,0)
                 , NVL(INITVAL_AC_MED,0)
                 , NVL(INITVAL_AC_HI,0)
                 , NVL(INITVAL_BC_LOW,0)
                 , NVL(INITVAL_BC_MED,0)
                 , NVL(INITVAL_BC_HI,0)
                 , NVL(INITVAL_LC_LOW,0)
                 , NVL(INITVAL_LC_MED,0)
                 , NVL(INITVAL_LC_HI,0)
                 , NVL(INITVAL_TC_LOW,0)
                 , NVL(INITVAL_TC_MED,0)
                 , NVL(INITVAL_TC_HI,0)
                 , NVL(INITVAL_PTCHG_CR,0)
                 , NVL(INITVAL_POTHOLE_CR,0)
                 , NVL(INITVAL_RD,0)
                 , NVL(INITVAL_RD,0)
                 , 0
                 , 0
                 , NVL(INITVAL_IRI,0)
                 , 10
              INTO v_AC_LOW, v_AC_MED, v_AC_HI, v_BLOCK_CR_LOW, v_BLOCK_CR_MED, v_BLOCK_CR_HI
                 , v_LC_LOW, v_LC_MED, v_LC_HI, v_TC_LOW, v_TC_MED, v_TC_HI
                 , v_PTCHG_CR, v_POTHOLE_CR, v_RD_VAL, v_RD_LOW, v_RD_MED, v_RD_HI, v_IRI_VAL
                 , v_TRG_GPCI
             FROM TN_PAV_IDX_ERYYVAL;

            -- 공사일로부터 현재 개월 수 계산
            SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_CNTRWK_DT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;

        END IF;

		IF (p_PREDCT_CEN = 'false')
		THEN
           
            --v_CALC_RD_VAL := FN_GET_PREDCT_VAL(v_RD_VAL, v_MONTH, v_erow.CELL_TYPE, 'DFCT0006');
            
			 -- 산정일 예측모델 적용
			WITH calc_idx AS (
					-- 예측모델 적용 / 지수 산출
					SELECT FN_GET_PREDCT_VAL(v_AC_LOW, v_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_LOW
						 , FN_GET_PREDCT_VAL(v_AC_MED, v_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_MED
						 , FN_GET_PREDCT_VAL(v_AC_HI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_HI
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_LOW, v_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_LOW
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_MED, v_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_MED
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_HI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_HI
						 , FN_GET_PREDCT_VAL(v_LC_LOW, v_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_LOW
						 , FN_GET_PREDCT_VAL(v_LC_MED, v_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_MED
						 , FN_GET_PREDCT_VAL(v_LC_HI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_HI
						 , FN_GET_PREDCT_VAL(v_TC_LOW, v_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_LOW
						 , FN_GET_PREDCT_VAL(v_TC_MED, v_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_MED
						 , FN_GET_PREDCT_VAL(v_TC_HI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_HI
                         , FN_GET_PREDCT_VAL(v_RD_LOW, v_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_LOW
                         , FN_GET_PREDCT_VAL(v_RD_MED, v_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_MED
                         , FN_GET_PREDCT_VAL(v_RD_HI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_HI
						 , FN_GET_PREDCT_VAL(v_IRI_VAL, v_MONTH, v_erow.CELL_TYPE, 'PRDT0008') IRI
                         , FN_GET_PREDCT_GPCI(v_TRG_GPCI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0009') GPCI
					FROM DUAL)
				SELECT FN_GET_AC_IDX(AC_LOW, AC_MED, AC_HI) AC_IDX
					 , FN_GET_BC_IDX(BC_LOW, BC_MED, BC_HI) BC_IDX
					 , FN_GET_LC_IDX(LC_LOW, LC_MED, LC_HI) LC_IDX
					 , FN_GET_TC_IDX(TC_LOW, TC_MED, TC_HI) TC_IDX
					 , FN_GET_RUT_IDX(RD_LOW, RD_MED, RD_HI) RD_IDX
					 , FN_GET_RCI(IRI) RCI
                     , GPCI
				  INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_RD_IDX, v_RCI, v_GPCI
				  FROM calc_idx;

				v_PTCHG_IDX := FN_GET_PATCH_IDX(v_PTCHG_CR);
				v_POT_IDX   := FN_GET_POT_IDX(v_POTHOLE_CR);

				v_SCR       := FN_GET_SCR(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX);
				--v_GPCI      := FN_GET_GPCI(v_SCR, v_RCI);

				v_DMG_CUZ	:= FN_GET_DMG_CUZ(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);

                v_PROCMSG :='조사 자료 예측 모델 처리 완료';   
                v_PROCCODE :='true';
				
                OPEN v_CURSOR FOR
                SELECT  v_CALC_YEAR AS "PREDCT_YEAR"
                      , v_CALC_MT AS "PREDCT_MT"
                      , v_AC_IDX AS "AC_IDX"
                      , v_BC_IDX AS "BC_IDX"
                      , v_LC_IDX AS "LC_IDX"
                      , v_TC_IDX AS "TC_IDX"
                      , v_RD_IDX AS "RD_IDX"
                      , v_PTCHG_IDX AS "PTCHG_IDX"
                      , v_POT_IDX AS "POTHOLE_IDX"
                      , v_SCR AS "SCR"
                      , v_RCI AS "RCI"
                      , v_GPCI AS "GPCI"
                      , FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI) AS "CNTL_DFECT"
                      , v_DMG_CUZ.DMG_CUZ_CLMT AS "DMG_CUZ_CLMT"
                      , v_DMG_CUZ.DMG_CUZ_VMTC AS "DMG_CUZ_VMTC"
                      , v_DMG_CUZ.DMG_CUZ_ETC AS "DMG_CUZ_ETC"
                      , '' AS "RPAIR_TA_AT"
                      , v_PROCCODE AS "PROCCODE"
                      , v_PROCMSG AS "PROCMSG"
                  FROM DUAL;
                  
		ELSE
            -- uuid 생성
            SELECT TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS')||dbms_random.string('U', 10)
              INTO v_UUID
              FROM DUAL;
              
			-- 산정일로부터 10년치 예측 데이터 생성
			FOR v_INDEX IN 0 .. 10
			LOOP
				v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
                
                --v_CALC_RD_VAL := FN_GET_PREDCT_VAL(v_RD_VAL, v_TOT_MONTH, v_erow.CELL_TYPE, 'DFCT0006');
                
				WITH calc_idx AS (
					-- 예측모델 적용 / 지수 산출
					SELECT FN_GET_PREDCT_VAL(v_AC_LOW, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_LOW
						 , FN_GET_PREDCT_VAL(v_AC_MED, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_MED
						 , FN_GET_PREDCT_VAL(v_AC_HI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0001') AC_HI
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_LOW, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_LOW
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_MED, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_MED
						 , FN_GET_PREDCT_VAL(v_BLOCK_CR_HI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0002') BC_HI
						 , FN_GET_PREDCT_VAL(v_LC_LOW, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_LOW
						 , FN_GET_PREDCT_VAL(v_LC_MED, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_MED
						 , FN_GET_PREDCT_VAL(v_LC_HI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0003') LC_HI
						 , FN_GET_PREDCT_VAL(v_TC_LOW, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_LOW
						 , FN_GET_PREDCT_VAL(v_TC_MED, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_MED
						 , FN_GET_PREDCT_VAL(v_TC_HI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0004') TC_HI
						 , FN_GET_PREDCT_VAL(v_RD_LOW, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_LOW
                         , FN_GET_PREDCT_VAL(v_RD_MED, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_MED
                         , FN_GET_PREDCT_VAL(v_RD_HI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0007') RD_HI
						 , FN_GET_PREDCT_VAL(v_IRI_VAL, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0008') IRI
                         , FN_GET_PREDCT_GPCI(v_TRG_GPCI, v_TOT_MONTH, v_erow.CELL_TYPE, 'PRDT0009') GPCI
					FROM DUAL)
				SELECT FN_GET_AC_IDX(AC_LOW, AC_MED, AC_HI) AC_IDX
					 , FN_GET_BC_IDX(BC_LOW, BC_MED, BC_HI) BC_IDX
					 , FN_GET_LC_IDX(LC_LOW, LC_MED, LC_HI) LC_IDX
					 , FN_GET_TC_IDX(TC_LOW, TC_MED, TC_HI) TC_IDX
					 , FN_GET_RUT_IDX(RD_LOW, RD_MED, RD_HI) RD_IDX
					 , FN_GET_RCI(IRI) RCI
                     , GPCI
				  INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_RD_IDX, v_RCI, v_GPCI
				  FROM calc_idx;

				v_PTCHG_IDX := FN_GET_PATCH_IDX(v_PTCHG_CR);
				v_POT_IDX   := FN_GET_POT_IDX(v_POTHOLE_CR);

				v_SCR       := FN_GET_SCR(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX);
				--v_GPCI      := FN_GET_GPCI(v_SCR, v_RCI);

				-- 예측 날짜 계산
				SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;
                
                -- 임시 테이블에 INSERT
                INSERT INTO TMP_CLAC_PREDCT_EVL(
                                                UUID,
                                                PREDCT_YEAR,
                                                PREDCT_MT,
                                                AC_IDX,
                                                BC_IDX,
                                                LC_IDX,
                                                TC_IDX,
                                                RD_IDX,
                                                PTCHG_IDX,
                                                POTHOLE_IDX,
                                                SCR,
                                                RCI,
                                                GPCI,
                                                CNTL_DFECT,
                                                DMG_CUZ_CLMT,
                                                DMG_CUZ_VMTC,
                                                DMG_CUZ_ETC
                                            ) VALUES(
                                                v_UUID
                                              , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                              , TO_CHAR(v_PREDCT_DT, 'MM')
                                              , v_AC_IDX
                                              , v_BC_IDX
                                              , v_LC_IDX
                                              , v_TC_IDX
                                              , v_RD_IDX
                                              , v_PTCHG_IDX
                                              , v_POT_IDX
                                              , v_SCR
                                              , v_RCI
                                              , v_GPCI
                                              , NULL
                                              , v_DMG_CUZ.DMG_CUZ_CLMT
                                              , v_DMG_CUZ.DMG_CUZ_VMTC
                                              , v_DMG_CUZ.DMG_CUZ_ETC);                  
			END LOOP;
            
           
            v_PROCMSG :='조사 자료 예측 모델 처리 완료';   
            v_PROCCODE :='true';
    
            -- 결과값 도출
            OPEN v_CURSOR FOR
                WITH CLAC_PREDCT_EVL AS
                (
                    SELECT PREDCT_YEAR AS "PREDCT_YEAR"
                         , PREDCT_MT AS "PREDCT_MT"
                         , AVG(AC_IDX) AS "AC_IDX"
                         , AVG(BC_IDX) AS "BC_IDX"
                         , AVG(LC_IDX) AS "LC_IDX"
                         , AVG(TC_IDX) AS "TC_IDX"
                         , AVG(RD_IDX) AS "RD_IDX"
                         , AVG(PTCHG_IDX) AS "PTCHG_IDX"
                         , AVG(POTHOLE_IDX) AS "POTHOLE_IDX"
                         , AVG(SCR) AS "SCR"
                         , AVG(RCI) AS "RCI"
                         , AVG(GPCI) AS "GPCI"
                         , FN_GET_CNTL_DFECT(AVG(AC_IDX), AVG(BC_IDX), AVG(LC_IDX), AVG(TC_IDX), AVG(PTCHG_IDX), AVG(POTHOLE_IDX), AVG(RD_IDX), AVG(RCI)) AS "CNTL_DFECT"
                         , MIN(ABS(6 - AVG(GPCI))) OVER( ) "MIN_VAL"
                         , ABS(6 - AVG(GPCI)) "CALC_ABS"
                       --  , v_PROCCODE AS "PROCCODE"
                       --  , v_PROCMSG AS "PROCMSG"
                     FROM TMP_CLAC_PREDCT_EVL
                    WHERE UUID = v_UUID
                    GROUP BY PREDCT_YEAR, PREDCT_MT --, v_PROCCODE, v_PROCMSG
                )
                SELECT PREDCT_YEAR
                     , PREDCT_MT
                     , AC_IDX
                     , BC_IDX
                     , LC_IDX
                     , TC_IDX
                     , RD_IDX
                     , PTCHG_IDX
                     , POTHOLE_IDX
                     , SCR
                     , RCI
                     , GPCI
                     , CNTL_DFECT
                     , '' DMG_CUZ_CLMT
                     , '' DMG_CUZ_VMTC
                     , '' DMG_CUZ_ETC
                     , DECODE( MIN_VAL, CALC_ABS, 'Y', 'N') RPAIR_TA_AT
                     --, PROCCODE
                     --, PROCMSG
                  FROM CLAC_PREDCT_EVL
                 ORDER BY PREDCT_YEAR, PREDCT_MT;
            
		END IF;

    END LOOP;
    
	o_OUTPUT := v_CURSOR;
    
    

EXCEPTION  
    WHEN OTHERS
    THEN
        v_PROCMSG := SUBSTR(SQLERRM,1,1000);
        v_PROCCODE :='false';

        OPEN o_OUTPUT FOR
        SELECT v_PROCCODE AS "PROCCODE", v_PROCMSG AS "PROCMSG" FROM DUAL;
    RAISE;      
END PRC_CLAC_PREDCT_EVL;