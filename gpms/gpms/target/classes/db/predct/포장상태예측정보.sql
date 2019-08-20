/**
 * [포장상태 예측]
 * 집계 단위 포장상태 평가 정보 예측(산정일 기준)
 * 
 * @PARAM p_ROUTE_CODE : 노선 코드
 * @PARAM p_SRVY_NO : 조사자료 번호
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
create or replace PROCEDURE      "PRC_CLAC_PREDCT_ROUTE_SRVY_EVL" (
/*
    포장상태 집계단위 평가 (조사자료 / 노선)
    예측모델을 적용한 평가값 산출

    생성일 : 2017-09-14
    생성자 : skc@muhanit.kr
*/
p_ROUTE_CODE		    VARCHAR2,
p_SRVY_NO               VARCHAR2,
p_USER_NO               VARCHAR2,
p_MODE                  VARCHAR2,
o_PROCCODE      OUT     VARCHAR2,
o_PROCMSG       OUT     VARCHAR2
)
AS

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

v_MONTH             NUMBER(10);

v_CALC_YEAR         VARCHAR2(4);
v_CALC_MT           VARCHAR2(2);
v_CNTRWK_HIST_CNT   NUMBER;
v_CNTRWK_DT         VARCHAR2(6);
v_DMG_CUZ			DMG_CUZ;
v_CNTL_DFECT		TN_SM_DTA_LAST_STTUS.CNTL_DFECT%TYPE;
v_SM_NO             TN_SM_DTA_LAST_STTUS.SM_NO%TYPE;

CURSOR predct_data(
        i_ROUTE_CODE VARCHAR2,
        i_SRVY_NO VARCHAR2
    ) IS 
    SELECT tnsmdtagnlsttus.SM_NO /* 집계_자료_일반_현황.집계_번호 */
		 , tnsmdtagnlsttus.SRVY_NO /* 집계_자료_일반_현황.조사_번호 */
		 , tnsmdtagnlsttus.SRVY_YEAR /* 집계_자료_일반_현황.조사_년도 */
		 , tnsmdtagnlsttus.ROUTE_CODE /* 집계_자료_일반_현황.노선_코드 */
		 , tnsmdtagnlsttus.DIRECT_CODE /* 집계_자료_일반_현황.행선_코드 */
		 , tnsmdtagnlsttus.TRACK /* 집계_자료_일반_현황.차로 */
		 , tnsmdtagnlsttus.STRTPT /* 집계_자료_일반_현황.시점 */
		 , tnsmdtagnlsttus.ENDPT /* 집계_자료_일반_현황.종점 */
		 , tnsmdtagnlsttus.IRI_VAL /* 집계_자료_일반_현황.종단평탄성_값 */
		 , tnsmdtagnlsttus.POTHOLE_CR /* 집계_자료_일반_현황.포트홀_균열 */
		 , tnsmdtagnlsttus.RD_VAL /* 집계_자료_일반_현황.소성변형_값 */
		 , tnsmdtagnlsttus.VRTCAL_CR /* 집계_자료_일반_현황.종방향_균열 */
		 , tnsmdtagnlsttus.HRZNTAL_CR /* 집계_자료_일반_현황.횡방향_균열 */
		 , tnsmdtagnlsttus.CNSTRCT_JOINT_CR /* 집계_자료_일반_현황.시공_줄눈_균열 */
		 , tnsmdtagnlsttus.TRTS_BAC_CR /* 집계_자료_일반_현황.거북등_균열 */
		 , tnsmdtagnlsttus.PTCHG_CR /* 집계_자료_일반_현황.패칭_균열 */
		 , tnsmdtagnlsttus.CR_VAL /* 집계_자료_일반_현황.표면손상값 */
		 , tnsmdtagnlsttus.CR_IDX /* 집계_자료_일반_현황.표면손상지수 */
		 , tnsmdtagnlsttus.RD_IDX /* 집계_자료_일반_현황.소성변형지수 */
		 , tnsmdtagnlsttus.RI_IDX /* 집계_자료_일반_현황.평탄성_지수 */
		 , tnsmdtagnlsttus.CNTL_DFECT /* 집계_자료_일반_현황.지배_결함 */
		 , tnsmdtagnlsttus.SRVY_KND /* 집계_자료_일반_현황.조사_종류 */
		 , tnsmdtagnlsttus.SRVY_MT /* 집계_자료_일반_현황.조사_월 */
		 , tnsmdtagnlsttus.FRMULA_NO /* 집계_자료_일반_현황.수식_번호 */
		 , tnsmdtagnlsttus.TRNSPORT_QY /* 집계_자료_일반_현황.교통_량 */
		 , tnsmdtagnlsttus.CR_GRAD /* 집계_자료_일반_현황.표면손상_등급 */
		 , tnsmdtagnlsttus.RD_GRAD /* 집계_자료_일반_현황.소성변형_등급 */
		 , tnsmdtagnlsttus.IRI_GRAD /* 집계_자료_일반_현황.종단평탄성_등급 */
		 , tnsmdtagnlsttus.DELETE_AT /* 집계_자료_일반_현황.삭제_여부 */
		 , tnsmdtagnlsttus.USE_AT /* 집계_자료_일반_현황.사용_여부 */
		 , tnsmdtagnlsttus.CRTR_NO /* 집계_자료_일반_현황.생성자_번호 */
		 , tnsmdtagnlsttus.CREAT_DT /* 집계_자료_일반_현황.생성_일시 */
		 , tnsmdtagnlsttus.UPDUSR_NO /* 집계_자료_일반_현황.수정자_번호 */
		 , tnsmdtagnlsttus.UPDT_DT /* 집계_자료_일반_현황.수정_일시 */
		 , tnsmdtagnlsttus.BLOCK_CR /* 집계_자료_일반_현황.블럭_균열 */
		 , tnsmdtagnlsttus.CR_LT /* 집계_자료_일반_현황.균열_길이 */
		 , tnsmdtagnlsttus.CR_WID /* 집계_자료_일반_현황.균열_폭 */
		 , tnsmdtagnlsttus.NHPCI /* 집계_자료_일반_현황.NHPCI */
		 , tnsmdtagnlsttus.SPI /* 집계_자료_일반_현황.SPI */
		 , tnsmdtagnlsttus.XCR /* 집계_자료_일반_현황.NHPCI_균열 */
		 , tnsmdtagnlsttus.RCI /* 집계_자료_일반_현황.표면_조도_지수 */
		 , tnsmdtagnlsttus.SCR /* 집계_자료_일반_현황.표면_상태_지수 */
		 , tnsmdtagnlsttus.AC_LOW /* 집계_자료_일반_현황.거북등균열_LOW */
		 , tnsmdtagnlsttus.AC_MED /* 집계_자료_일반_현황.거북등균열_MED */
		 , tnsmdtagnlsttus.AC_HI /* 집계_자료_일반_현황.거북등균열_HI */
		 , tnsmdtagnlsttus.LC_LOW /* 집계_자료_일반_현황.종방향균열_LOW */
		 , tnsmdtagnlsttus.LC_MED /* 집계_자료_일반_현황.종방향균열_MED */
		 , tnsmdtagnlsttus.LC_HI /* 집계_자료_일반_현황.종방향균열_HI */
		 , tnsmdtagnlsttus.TC_LOW /* 집계_자료_일반_현황.횡방향균열_LOW */
		 , tnsmdtagnlsttus.TC_MED /* 집계_자료_일반_현황.횡방향균열_MED */
		 , tnsmdtagnlsttus.TC_HI /* 집계_자료_일반_현황.횡방향균열_HI */
		 , tnsmdtagnlsttus.AC_IDX /* 집계_자료_일반_현황.거북등균열_지수 */
		 , tnsmdtagnlsttus.LC_IDX /* 집계_자료_일반_현황.종방향균열_지수 */
		 , tnsmdtagnlsttus.TC_IDX /* 집계_자료_일반_현황.횡방향균열_지수 */
		 , tnsmdtagnlsttus.PTCHG_IDX /* 집계_자료_일반_현황.패칭_지수 */
		 , tnsmdtagnlsttus.RD_LOW /* 집계_자료_일반_현황.소성변형_LOW */
		 , tnsmdtagnlsttus.RD_MED /* 집계_자료_일반_현황.소성변형_MED */
		 , tnsmdtagnlsttus.RD_HI /* 집계_자료_일반_현황.소성변형_HI */
		 , tnsmdtagnlsttus.BLOCK_CR_LOW /* 집계_자료_일반_현황.블럭_균열_LOW */
		 , tnsmdtagnlsttus.BLOCK_CR_MED /* 집계_자료_일반_현황.블럭_균열_MED */
		 , tnsmdtagnlsttus.BLOCK_CR_HI /* 집계_자료_일반_현황.블럭_균열_HI */
		 , tnsmdtagnlsttus.POTHOLE_IDX /* 집계_자료_일반_현황.포트홀_지수 */
		 , tnsmdtagnlsttus.DMG_CUZ_CLMT /* 집계_자료_일반_현황.파손_원인_기후 */
		 , tnsmdtagnlsttus.DMG_CUZ_VMTC /* 집계_자료_일반_현황.파손_원인_교통량 */
		 , tnsmdtagnlsttus.DMG_CUZ_ETC /* 집계_자료_일반_현황.파손_원인_기타 */
		 , tnsmdtagnlsttus.GPCI /* 집계_자료_일반_현황.GPCI */
		 , tnsmdtagnlsttus.PC_GRAD /* 집계_자료_일반_현황.포장상태등급 */
		 , tnsmdtagnlsttus.BC_IDX /* 집계_자료_일반_현황.블럭균열_지수 */
		 , tnsmdtagnlsttus.LAST_DTA_AT /* 집계_자료_일반_현황.블럭균열_지수 */
		 , cellsect.CELL_TYPE
	  FROM TN_SM_DTA_GNL_STTUS tnsmdtagnlsttus
	 INNER JOIN CELL_SECT cellsect
		ON cellsect.ROUTE_CODE = tnsmdtagnlsttus.ROUTE_CODE
	   AND cellsect.DIRECT_CODE = tnsmdtagnlsttus.DIRECT_CODE
	   AND cellsect.TRACK = tnsmdtagnlsttus.TRACK
	   AND cellsect.STRTPT = tnsmdtagnlsttus.STRTPT
	   AND cellsect.ENDPT = tnsmdtagnlsttus.ENDPT
	WHERE tnsmdtagnlsttus.USE_AT = 'Y'
	  AND tnsmdtagnlsttus.DELETE_AT = 'N'
	  AND tnsmdtagnlsttus.LAST_DTA_AT = 'Y'
      AND (tnsmdtagnlsttus.ROUTE_CODE = i_ROUTE_CODE
              OR (    tnsmdtagnlsttus.SRVY_NO = i_SRVY_NO
                    OR EXISTS (
                                SELECT 'T' FROM TN_SRVY_DTA_STTUS tnsrvydtasttus
                                WHERE tnsrvydtasttus.PRDTMDL_PROCESS_AT = 'N'
                                  AND tnsrvydtasttus.SRVY_NO = i_SRVY_NO
                                  AND tnsrvydtasttus.SRVY_YEAR = tnsmdtagnlsttus.SRVY_YEAR
                                  AND tnsrvydtasttus.SRVY_MT = tnsmdtagnlsttus.SRVY_MT
                                  AND tnsrvydtasttus.ROUTE_CODE = tnsmdtagnlsttus.ROUTE_CODE
                                  AND tnsrvydtasttus.DIRECT_CODE = tnsmdtagnlsttus.DIRECT_CODE
                                  AND tnsrvydtasttus.TRACK = tnsmdtagnlsttus.TRACK
                                  AND tnsrvydtasttus.STRTPT >= tnsmdtagnlsttus.STRTPT
                                  AND tnsrvydtasttus.ENDPT <= tnsmdtagnlsttus.ENDPT
                                  AND tnsrvydtasttus.USE_AT = 'Y'
                                  AND tnsrvydtasttus.DELETE_AT = 'N'
                    )
                )
            )
	ORDER BY tnsmdtagnlsttus.ROUTE_CODE, tnsmdtagnlsttus.DIRECT_CODE, tnsmdtagnlsttus.TRACK, tnsmdtagnlsttus.STRTPT, tnsmdtagnlsttus.ENDPT;


-- DECLARE RAISE_EXT exception;
BEGIN

    o_PROCCODE :='false';                    
    o_PROCMSG := '포장상태 노선단위 수시평가 시작';
    v_CALC_YEAR := TO_CHAR(SYSDATE, 'YYYY');
    v_CALC_MT   := TO_CHAR(SYSDATE, 'MM');

    -- 기본값 체크
    IF ((p_ROUTE_CODE IS NULL) OR (p_ROUTE_CODE = '')) AND ((p_SRVY_NO IS NULL) OR (p_SRVY_NO = ''))
    THEN
      o_PROCCODE := 'false';
      o_PROCMSG := '포장상태 집계평가 파라미터에 값이 없습니다.';

      RETURN;
    END IF;

    FOR v_erow IN predct_data(p_ROUTE_CODE, p_SRVY_NO)
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
            v_GPCI  := v_erow.GPCI;

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
                 , v_GPCI
             FROM TN_PAV_IDX_ERYYVAL;
             
            -- 공사일로부터 현재 개월 수 계산
            SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_CNTRWK_DT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;

        END IF;
        

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
                     , FN_GET_PREDCT_GPCI(v_GPCI, v_MONTH, v_erow.CELL_TYPE, 'PRDT0009') GPCI
				  FROM DUAL)
		SELECT AC_LOW
             , AC_MED
             , AC_HI
             , BC_LOW
             , BC_MED
             , BC_HI
             , LC_LOW
             , LC_MED
             , LC_HI
             , TC_LOW
             , TC_MED
             , TC_HI
             , (RD_LOW + RD_MED + RD_HI) RD
             , RD_LOW
             , RD_MED
             , RD_HI
             , IRI
             , FN_GET_AC_IDX(NVL(AC_LOW,0), NVL(AC_MED,0), NVL(AC_HI,0)) AC_IDX
			 , FN_GET_BC_IDX(NVL(BC_LOW,0), NVL(BC_MED,0), NVL(BC_HI,0)) BC_IDX
			 , FN_GET_LC_IDX(NVL(LC_LOW,0), NVL(LC_MED,0), NVL(LC_HI,0)) LC_IDX
			 , FN_GET_TC_IDX(NVL(TC_LOW,0), NVL(TC_MED,0), NVL(TC_HI,0)) TC_IDX
             , FN_GET_RUT_IDX(RD_LOW, RD_MED, RD_HI)
			 , FN_GET_RCI(NVL(IRI,0)) RCI
             , GPCI
		  INTO v_AC_LOW, v_AC_MED, v_AC_HI, v_BLOCK_CR_LOW, v_BLOCK_CR_MED, v_BLOCK_CR_HI
             , v_LC_LOW, v_LC_MED, v_LC_HI, v_TC_LOW, v_TC_MED, v_TC_HI
             , v_RD_VAL, v_RD_LOW, v_RD_MED, v_RD_HI, v_IRI_VAL
             , v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_RD_IDX, v_RCI
             , v_GPCI
		  FROM calc_idx;
          
      --  v_RD_IDX    := FN_GET_RUT_IDX(v_RD_LOW, v_RD_MED, v_RD_HI);
		v_PTCHG_IDX := FN_GET_PATCH_IDX(v_PTCHG_CR);
		v_POT_IDX   := FN_GET_POT_IDX(v_POTHOLE_CR);

		v_SCR       := FN_GET_SCR(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX);
		--v_GPCI      := FN_GET_GPCI(v_SCR, v_RCI);

		v_CNTL_DFECT := FN_GET_CNTL_DFECT(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
		v_DMG_CUZ	:= FN_GET_DMG_CUZ(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX, v_RCI);
        

        o_PROCMSG :='조사 자료 집계 최신 현황 수정';                    
        /* smDtaLastSttusDAO.updateSmDtaLastSttus */
        /* 집계_자료_최신_현황 수정 */
        UPDATE TN_SM_DTA_LAST_STTUS SET
          SRVY_NO = v_erow.SRVY_NO /* 집계_자료_최신_현황.조사_번호 */
        , SRVY_YEAR = v_erow.SRVY_YEAR /* 집계_자료_최신_현황.조사_년도 */
        , ROUTE_CODE = v_erow.ROUTE_CODE /* 집계_자료_최신_현황.노선_코드 */
        , DIRECT_CODE = v_erow.DIRECT_CODE /* 집계_자료_최신_현황.행선_코드 */
        , TRACK = v_erow.TRACK /* 집계_자료_최신_현황.차로 */
        , STRTPT = v_erow.STRTPT /* 집계_자료_최신_현황.시점 */
        , ENDPT = v_erow.ENDPT /* 집계_자료_최신_현황.종점 */
        , IRI_VAL = v_IRI_VAL  /* 집계_자료_최신_현황.종단평탄성_값 */
        , RD_VAL = v_RD_VAL  /* 집계_자료_최신_현황.소성변형_값 */
        , VRTCAL_CR = v_erow.VRTCAL_CR /* 집계_자료_최신_현황.종방향_균열 */
        , HRZNTAL_CR = v_erow.HRZNTAL_CR /* 집계_자료_최신_현황.횡방향_균열 */
        , CNSTRCT_JOINT_CR = v_erow.CNSTRCT_JOINT_CR /* 집계_자료_최신_현황.시공_줄눈_균열 */
        , TRTS_BAC_CR = v_erow.TRTS_BAC_CR /* 집계_자료_최신_현황.거북등_균열 */
        , PTCHG_CR = v_PTCHG_CR  /* 집계_자료_최신_현황.패칭_균열 */
        , CR_VAL = v_erow.CR_VAL /* 집계_자료_최신_현황.표면손상값 */
        , CR_IDX = v_erow.CR_IDX /* 집계_자료_최신_현황.표면손상지수 */
        , RD_IDX = v_RD_IDX  /* 집계_자료_최신_현황.소성변형지수 */
        , PC_GRAD = v_erow.PC_GRAD /* 집계_자료_최신_현황.포장상태등급 */
        , CNTL_DFECT = v_CNTL_DFECT /* 집계_자료_최신_현황.지배_결함 */
        , SRVY_KND = v_erow.SRVY_KND /* 집계_자료_최신_현황.조사_종류 */
        , SRVY_MT = v_erow.SRVY_MT /* 집계_자료_최신_현황.조사_월 */
        , UPDUSR_NO = p_USER_NO  /* 집계_자료_최신_현황.수정자_번호 */
        , UPDT_DT = SYSDATE /* 집계_자료_최신_현황.수정_일시 */
        , FRMULA_NO = v_erow.FRMULA_NO /* 집계_자료_최신_현황.수식_번호 */
        , TRNSPORT_QY = v_erow.TRNSPORT_QY /* 집계_자료_최신_현황.교통_량 */
        , CR_GRAD = v_erow.CR_GRAD /* 집계_자료_최신_현황.표면손상_등급 */
        , RD_GRAD = v_erow.RD_GRAD /* 집계_자료_최신_현황.소성변형_등급 */
        , IRI_GRAD = v_erow.IRI_GRAD /* 집계_자료_최신_현황.종단평탄성_등급 */
        , RI_IDX = v_erow.RI_IDX /* 집계_자료_최신_현황.평탄성_지수 */
        , BLOCK_CR = v_erow.BLOCK_CR /* 집계_자료_최신_현황.블럭_균열 */
        , CR_LT = v_erow.CR_LT /* 집계_자료_최신_현황.균열_길이 */
        , CR_WID = v_erow.CR_WID /* 집계_자료_최신_현황.균열_폭 */
        , GPCI = v_GPCI /* 집계_자료_최신_현황.GPCI */
        , NHPCI = v_erow.NHPCI /* 집계_자료_최신_현황.NHPCI */
        , SPI = v_erow.SPI /* 집계_자료_최신_현황.SPI */
        , XCR = v_erow.XCR /* 집계_자료_최신_현황.NHPCI_균열 */
        , RCI = v_RCI /* 집계_자료_최신_현황.표면_조도_지수 */
        , SCR = v_SCR /* 집계_자료_최신_현황.표면_상태_지수 */
        , AC_LOW = v_AC_LOW /* 집계_자료_최신_현황.거북등균열_LOW */
        , AC_MED = v_AC_MED /* 집계_자료_최신_현황.거북등균열_MED */
        , AC_HI = v_AC_HI /* 집계_자료_최신_현황.거북등균열_HI */
        , LC_LOW = v_LC_LOW /* 집계_자료_최신_현황.종방향균열_LOW */
        , LC_MED = v_LC_MED /* 집계_자료_최신_현황.종방향균열_MED */
        , LC_HI = v_LC_HI /* 집계_자료_최신_현황.종방향균열_HI */
        , TC_LOW = v_TC_LOW /* 집계_자료_최신_현황.횡방향균열_LOW */
        , TC_MED = v_TC_MED /* 집계_자료_최신_현황.횡방향균열_MED */
        , TC_HI = v_TC_HI /* 집계_자료_최신_현황.횡방향균열_HI */
        , RD_LOW = v_RD_LOW /* 집계_자료_최신_현황.소성변형_LOW */
        , RD_MED = v_RD_MED /* 집계_자료_최신_현황.소성변형_MED */
        , RD_HI = v_RD_HI /* 집계_자료_최신_현황.소성변형_HI */
        , AC_IDX = v_AC_IDX /* 집계_자료_최신_현황.거북등균열_지수 */
        , LC_IDX = v_LC_IDX /* 집계_자료_최신_현황.종방향균열_지수 */
        , TC_IDX = v_TC_IDX /* 집계_자료_최신_현황.횡방향균열_지수 */
        , PTCHG_IDX = v_PTCHG_IDX /* 집계_자료_최신_현황.패칭_지수 */
        , POTHOLE_CR = v_POTHOLE_CR /* 집계_자료_최신_현황.포트홀_균열 */
        , BLOCK_CR_LOW = v_BLOCK_CR_LOW /* 집계_자료_최신_현황.블럭_균열_LOW */
        , BLOCK_CR_MED = v_BLOCK_CR_MED /* 집계_자료_최신_현황.블럭_균열_MED */
        , BLOCK_CR_HI = v_BLOCK_CR_HI /* 집계_자료_최신_현황.블럭_균열_HI */
        , POTHOLE_IDX = v_POT_IDX /* 집계_자료_최신_현황.포트홀_지수 */
        , DMG_CUZ_CLMT = v_DMG_CUZ.DMG_CUZ_CLMT /* 집계_자료_최신_현황.파손_원인_기후 */
        , DMG_CUZ_VMTC = v_DMG_CUZ.DMG_CUZ_VMTC /* 집계_자료_최신_현황.파손_원인_교통량 */
        , DMG_CUZ_ETC = v_DMG_CUZ.DMG_CUZ_ETC /* 집계_자료_최신_현황.파손_원인_기타 */
        , BC_IDX = v_BC_IDX /* 집계_자료_최신_현황.블럭균열_지수 */
        , CALC_MT = v_CALC_MT /* 집계_자료_최신_현황.산정_월 */
        , CALC_YEAR = v_CALC_YEAR /* 집계_자료_최신_현황.산정_년도 */
        WHERE 1 = 1
         AND ROUTE_CODE = v_erow.ROUTE_CODE
         AND DIRECT_CODE = v_erow.DIRECT_CODE
         AND TRACK = v_erow.TRACK
         AND STRTPT = v_erow.STRTPT
         AND ENDPT = v_erow.ENDPT
         AND USE_AT = 'Y'
         AND DELETE_AT = 'N';
        
        IF SQL%NOTFOUND THEN
            o_PROCMSG :='집계 자료 최신 현황 등록';    
            /* smDtaLastSttusDAO.insertSmDtaLastSttus */
			/* 집계_자료_최신_현황 등록 */
			INSERT INTO TN_SM_DTA_LAST_STTUS (
				  SM_NO /* 집계_자료_최신_현황.집계_번호 */
				, SRVY_NO /* 집계_자료_최신_현황.조사_번호 */
				, SRVY_YEAR /* 집계_자료_최신_현황.조사_년도 */
				, ROUTE_CODE /* 집계_자료_최신_현황.노선_코드 */
				, DIRECT_CODE /* 집계_자료_최신_현황.행선_코드 */
				, TRACK /* 집계_자료_최신_현황.차로 */
				, STRTPT /* 집계_자료_최신_현황.시점 */
				, ENDPT /* 집계_자료_최신_현황.종점 */
				, IRI_VAL /* 집계_자료_최신_현황.종단평탄성_값 */
				, RD_VAL /* 집계_자료_최신_현황.소성변형_값 */
				, VRTCAL_CR /* 집계_자료_최신_현황.종방향_균열 */
				, HRZNTAL_CR /* 집계_자료_최신_현황.횡방향_균열 */
				, CNSTRCT_JOINT_CR /* 집계_자료_최신_현황.시공_줄눈_균열 */
				, TRTS_BAC_CR /* 집계_자료_최신_현황.거북등_균열 */
				, PTCHG_CR /* 집계_자료_최신_현황.패칭_균열 */
				, CR_VAL /* 집계_자료_최신_현황.표면손상값 */
				, CR_IDX /* 집계_자료_최신_현황.표면손상지수 */
				, RD_IDX /* 집계_자료_최신_현황.소성변형지수 */
				, PC_GRAD /* 집계_자료_최신_현황.포장상태등급 */
				, CNTL_DFECT /* 집계_자료_최신_현황.지배_결함 */
				, SRVY_KND /* 집계_자료_최신_현황.조사_종류 */
				, SRVY_MT /* 집계_자료_최신_현황.조사_월 */
				, DELETE_AT /* 집계_자료_최신_현황.삭제_여부 */
				, USE_AT /* 집계_자료_최신_현황.사용_여부 */
				, CRTR_NO /* 집계_자료_최신_현황.생성자_번호 */
				, CREAT_DT /* 집계_자료_최신_현황.생성_일시 */
				, UPDUSR_NO /* 집계_자료_최신_현황.수정자_번호 */
				, UPDT_DT /* 집계_자료_최신_현황.수정_일시 */
				, FRMULA_NO /* 집계_자료_최신_현황.수식_번호 */
				, TRNSPORT_QY /* 집계_자료_최신_현황.교통_량 */
				, CR_GRAD /* 집계_자료_최신_현황.표면손상_등급 */
				, RD_GRAD /* 집계_자료_최신_현황.소성변형_등급 */
				, IRI_GRAD /* 집계_자료_최신_현황.종단평탄성_등급 */
				, RI_IDX /* 집계_자료_최신_현황.평탄성_지수 */
				, BLOCK_CR /* 집계_자료_최신_현황.블럭_균열 */
				, CR_LT /* 집계_자료_최신_현황.균열_길이 */
				, CR_WID /* 집계_자료_최신_현황.균열_폭 */
				, GPCI /* 집계_자료_최신_현황.GPCI */
				, NHPCI /* 집계_자료_최신_현황.NHPCI */
				, SPI /* 집계_자료_최신_현황.SPI */
				, XCR /* 집계_자료_최신_현황.NHPCI_균열 */
				, RCI /* 집계_자료_최신_현황.표면_조도_지수 */
				, SCR /* 집계_자료_최신_현황.표면_상태_지수 */
				, AC_LOW /* 집계_자료_최신_현황.거북등균열_LOW */
				, AC_MED /* 집계_자료_최신_현황.거북등균열_MED */
				, AC_HI /* 집계_자료_최신_현황.거북등균열_HI */
				, LC_LOW /* 집계_자료_최신_현황.종방향균열_LOW */
				, LC_MED /* 집계_자료_최신_현황.종방향균열_MED */
				, LC_HI /* 집계_자료_최신_현황.종방향균열_HI */
				, TC_LOW /* 집계_자료_최신_현황.횡방향균열_LOW */
				, TC_MED /* 집계_자료_최신_현황.횡방향균열_MED */
				, TC_HI /* 집계_자료_최신_현황.횡방향균열_HI */
				, RD_LOW /* 집계_자료_최신_현황.소성변형_LOW */
				, RD_MED /* 집계_자료_최신_현황.소성변형_MED */
				, RD_HI /* 집계_자료_최신_현황.소성변형_HI */
				, AC_IDX /* 집계_자료_최신_현황.거북등균열_지수 */
				, LC_IDX /* 집계_자료_최신_현황.종방향균열_지수 */
				, TC_IDX /* 집계_자료_최신_현황.횡방향균열_지수 */
				, PTCHG_IDX /* 집계_자료_최신_현황.패칭_지수 */
				, POTHOLE_CR /* 집계_자료_최신_현황.포트홀_균열 */
				, BLOCK_CR_LOW /* 집계_자료_최신_현황.블럭_균열_LOW */
				, BLOCK_CR_MED /* 집계_자료_최신_현황.블럭_균열_MED */
				, BLOCK_CR_HI /* 집계_자료_최신_현황.블럭_균열_HI */
				, POTHOLE_IDX /* 집계_자료_최신_현황.포트홀_지수 */
				, DMG_CUZ_CLMT /* 집계_자료_최신_현황.파손_원인_기후 */
				, DMG_CUZ_VMTC /* 집계_자료_최신_현황.파손_원인_교통량 */
				, DMG_CUZ_ETC /* 집계_자료_최신_현황.파손_원인_기타 */
				, BC_IDX /* 집계_자료_최신_현황.블럭균열_지수 */
				, CALC_MT /* 집계_자료_최신_현황.산정_월 */
				, CALC_YEAR /* 집계_자료_최신_현황.산정_년도 */
			) VALUES (
				  SEQ_TN_SM_DTA_LAST_STTUS.NEXTVAL /* 집계_자료_최신_현황.집계_번호 */
				, v_erow.SRVY_NO /* 집계_자료_최신_현황.조사_번호 */
				, v_erow.SRVY_YEAR /* 집계_자료_최신_현황.조사_년도 */
				, v_erow.ROUTE_CODE /* 집계_자료_최신_현황.노선_코드 */
				, v_erow.DIRECT_CODE /* 집계_자료_최신_현황.행선_코드 */
				, v_erow.TRACK /* 집계_자료_최신_현황.차로 */
				, v_erow.STRTPT /* 집계_자료_최신_현황.시점 */
				, v_erow.ENDPT /* 집계_자료_최신_현황.종점 */
				, v_IRI_VAL /* 집계_자료_최신_현황.종단평탄성_값 */
				, v_RD_VAL /* 집계_자료_최신_현황.소성변형_값 */
				, v_erow.VRTCAL_CR /* 집계_자료_최신_현황.종방향_균열 */
				, v_erow.HRZNTAL_CR /* 집계_자료_최신_현황.횡방향_균열 */
				, v_erow.CNSTRCT_JOINT_CR /* 집계_자료_최신_현황.시공_줄눈_균열 */
				, v_erow.TRTS_BAC_CR /* 집계_자료_최신_현황.거북등_균열 */
				, v_PTCHG_CR /* 집계_자료_최신_현황.패칭_균열 */
				, v_erow.CR_VAL /* 집계_자료_최신_현황.표면손상값 */
				, v_erow.CR_IDX /* 집계_자료_최신_현황.표면손상지수 */
				, v_RD_IDX /* 집계_자료_최신_현황.소성변형지수 */
				, v_erow.PC_GRAD /* 집계_자료_최신_현황.포장상태등급 */
				, v_CNTL_DFECT /* 집계_자료_최신_현황.지배_결함 */
				, v_erow.SRVY_KND /* 집계_자료_최신_현황.조사_종류 */
				, v_erow.SRVY_MT /* 집계_자료_최신_현황.조사_월 */
				, 'N' /* 집계_자료_최신_현황.삭제_여부 */
				, 'Y' /* 집계_자료_최신_현황.사용_여부 */
				, p_USER_NO /* 집계_자료_최신_현황.생성자_번호 */
				, SYSDATE /* 집계_자료_최신_현황.생성_일시 */
				, p_USER_NO /* 집계_자료_최신_현황.수정자_번호 */
				, SYSDATE /* 집계_자료_최신_현황.수정_일시 */
				, v_erow.FRMULA_NO /* 집계_자료_최신_현황.수식_번호 */
				, v_erow.TRNSPORT_QY /* 집계_자료_최신_현황.교통_량 */
				, v_erow.CR_GRAD /* 집계_자료_최신_현황.표면손상_등급 */
				, v_erow.RD_GRAD /* 집계_자료_최신_현황.소성변형_등급 */
				, v_erow.IRI_GRAD /* 집계_자료_최신_현황.종단평탄성_등급 */
				, v_erow.RI_IDX /* 집계_자료_최신_현황.평탄성_지수 */
				, v_erow.BLOCK_CR /* 집계_자료_최신_현황.블럭_균열 */
				, v_erow.CR_LT /* 집계_자료_최신_현황.균열_길이 */
				, v_erow.CR_WID /* 집계_자료_최신_현황.균열_폭 */
				, v_GPCI /* 집계_자료_최신_현황.GPCI */
				, v_erow.NHPCI /* 집계_자료_최신_현황.NHPCI */
				, v_erow.SPI /* 집계_자료_최신_현황.SPI */
				, v_erow.XCR /* 집계_자료_최신_현황.NHPCI_균열 */
				, v_RCI /* 집계_자료_최신_현황.표면_조도_지수 */
				, v_SCR /* 집계_자료_최신_현황.표면_상태_지수 */
				, v_AC_LOW /* 집계_자료_최신_현황.거북등균열_LOW */
				, v_AC_MED /* 집계_자료_최신_현황.거북등균열_MED */
				, v_AC_HI /* 집계_자료_최신_현황.거북등균열_HI */
				, v_LC_LOW /* 집계_자료_최신_현황.종방향균열_LOW */
				, v_LC_MED /* 집계_자료_최신_현황.종방향균열_MED */
				, v_LC_HI /* 집계_자료_최신_현황.종방향균열_HI */
				, v_TC_LOW /* 집계_자료_최신_현황.횡방향균열_LOW */
				, v_TC_MED /* 집계_자료_최신_현황.횡방향균열_MED */
				, v_TC_HI /* 집계_자료_최신_현황.횡방향균열_HI */
				, v_RD_LOW /* 집계_자료_최신_현황.소성변형_LOW */
				, v_RD_MED /* 집계_자료_최신_현황.소성변형_MED */
				, v_RD_HI /* 집계_자료_최신_현황.소성변형_HI */
				, v_AC_IDX /* 집계_자료_최신_현황.거북등균열_지수 */
				, v_LC_IDX /* 집계_자료_최신_현황.종방향균열_지수 */
				, v_TC_IDX /* 집계_자료_최신_현황.횡방향균열_지수 */
				, v_PTCHG_IDX /* 집계_자료_최신_현황.패칭_지수 */
				, v_POTHOLE_CR /* 집계_자료_최신_현황.포트홀_균열 */
				, v_BLOCK_CR_LOW /* 집계_자료_최신_현황.블럭_균열_LOW */
				, v_BLOCK_CR_MED /* 집계_자료_최신_현황.블럭_균열_MED */
				, v_BLOCK_CR_HI /* 집계_자료_최신_현황.블럭_균열_HI */
				, v_POT_IDX /* 집계_자료_최신_현황.포트홀_지수 */
				, v_DMG_CUZ.DMG_CUZ_CLMT /* 집계_자료_최신_현황.파손_원인_기후 */
				, v_DMG_CUZ.DMG_CUZ_VMTC /* 집계_자료_최신_현황.파손_원인_교통량 */
				, v_DMG_CUZ.DMG_CUZ_ETC /* 집계_자료_최신_현황.파손_원인_기타 */
				, v_BC_IDX /* 집계_자료_최신_현황.블럭균열_지수 */
				, v_CALC_MT /* 집계_자료_최신_현황.산정_월 */
				, v_CALC_YEAR /* 집계_자료_최신_현황.산정_년도 */
			);    
        END IF;

    END LOOP;

	o_PROCMSG :='포장상태 노선단위 수시평가 처리 완료';   
	o_PROCCODE :='true';
    

EXCEPTION  
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';

    RAISE;      
END PRC_CLAC_PREDCT_ROUTE_SRVY_EVL;