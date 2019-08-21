/**
 * [포장상태 예측]
 * 집계 단위 포장상태 평가 정보 예측(10년치 기준)
 * 
 * @PARAM p_ROUTE_CODE : 노선 코드
 * @PARAM p_SRVY_NO : 조사자료 번호
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
create or replace PROCEDURE      "PRC_CLAC_PREDCT_LAST" (
/*
    SECTION 단위 10년치 예측 자료 생성

    생성일 : 2017-08-24
    생성자 : skc@muhanit.kr
*/
p_ROUTE_CODE		VARCHAR2,
p_SRVY_NO           VARCHAR2,  /* 최소_구간_조사_자료.조사_번호 */ 
p_USER_NO           NUMBER,
p_MODE              VARCHAR2,
o_PROCCODE  OUT     VARCHAR2,
o_PROCMSG   OUT     VARCHAR2
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
v_RD_HI            NUMBER;
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

CURSOR predct_data (
    i_ROUTE_CODE VARCHAR2,
    i_SRVY_NO VARCHAR2
) IS 
     SELECT   tnsmdtalaststtus.SRVY_YEAR
            , tnsmdtalaststtus.SRVY_MT
            , tnsmdtalaststtus.SRVY_NO
            , tnsmdtalaststtus.ROUTE_CODE
            , tnsmdtalaststtus.DIRECT_CODE
            , tnsmdtalaststtus.TRACK
            , tnsmdtalaststtus.STRTPT
            , tnsmdtalaststtus.ENDPT
            , tnsmdtalaststtus.AC_LOW
            , tnsmdtalaststtus.AC_MED
            , tnsmdtalaststtus.AC_HI
            , tnsmdtalaststtus.BLOCK_CR_LOW
            , tnsmdtalaststtus.BLOCK_CR_MED
            , tnsmdtalaststtus.BLOCK_CR_HI
            , tnsmdtalaststtus.LC_LOW
            , tnsmdtalaststtus.LC_MED
            , tnsmdtalaststtus.LC_HI
            , tnsmdtalaststtus.TC_LOW
            , tnsmdtalaststtus.TC_MED
            , tnsmdtalaststtus.TC_HI
            , tnsmdtalaststtus.PTCHG_CR
            , tnsmdtalaststtus.POTHOLE_CR
            , tnsmdtalaststtus.RD_VAL
            , tnsmdtalaststtus.RD_LOW
            , tnsmdtalaststtus.RD_MED
            , tnsmdtalaststtus.RD_HI
            , tnsmdtalaststtus.PTCHG_IDX
            , tnsmdtalaststtus.POTHOLE_IDX
            , tnsmdtalaststtus.RD_IDX
            , tnsmdtalaststtus.IRI_VAL
            , tnsmdtalaststtus.GPCI
            , tnsmdtalaststtus.CALC_YEAR
            , tnsmdtalaststtus.CALC_MT
            , cellsect.CELL_TYPE
    FROM TN_SM_DTA_LAST_STTUS tnsmdtalaststtus
    INNER JOIN CELL_SECT cellsect
            ON cellsect.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE
           AND cellsect.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE
           AND cellsect.TRACK = tnsmdtalaststtus.TRACK
           AND cellsect.STRTPT = tnsmdtalaststtus.STRTPT 
           AND cellsect.ENDPT = tnsmdtalaststtus.ENDPT
    WHERE tnsmdtalaststtus.USE_AT = 'Y'
      AND tnsmdtalaststtus.DELETE_AT = 'N'
      AND (tnsmdtalaststtus.ROUTE_CODE = i_ROUTE_CODE
              OR ( tnsmdtalaststtus.SRVY_NO = i_SRVY_NO
                    OR EXISTS (
                                SELECT 'T' FROM TN_SRVY_DTA_STTUS tnsrvydtasttus
                                WHERE tnsrvydtasttus.PRDTMDL_PROCESS_AT = 'N'
                                  AND tnsrvydtasttus.SRVY_NO = i_SRVY_NO
                                  AND tnsrvydtasttus.SRVY_YEAR = tnsmdtalaststtus.SRVY_YEAR
                                  AND tnsrvydtasttus.SRVY_MT = tnsmdtalaststtus.SRVY_MT
                                  AND tnsrvydtasttus.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE
                                  AND tnsrvydtasttus.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE
                                  AND tnsrvydtasttus.TRACK = tnsmdtalaststtus.TRACK
                                  AND tnsrvydtasttus.STRTPT >= tnsmdtalaststtus.STRTPT
                                  AND tnsrvydtasttus.ENDPT <= tnsmdtalaststtus.ENDPT
                                  AND tnsrvydtasttus.USE_AT = 'Y'
                                  AND tnsrvydtasttus.DELETE_AT = 'N'
                            )
                    )
            );

-- DECLARE RAISE_EXT exception;
BEGIN

    o_PROCCODE :='false';                    
    o_PROCMSG := '조사자료 예측 모델 처리 시작';

     -- 기본값 체크
     IF ((p_ROUTE_CODE IS NULL) OR (p_ROUTE_CODE = '')) AND ((p_SRVY_NO IS NULL) OR (p_SRVY_NO = ''))
    THEN
      o_PROCCODE := 'false';
      o_PROCMSG := '조사자료 예측 모델 처리 파라미터에 값이 없습니다.';

      RETURN;
    END IF;

    IF ((p_SRVY_NO IS NOT NULL) OR (p_SRVY_NO <> ''))
    THEN
         -- 조사자료 처리 로그 등록
        SELECT SEQ_TL_SRVY_DTA_LOG.NEXTVAL into v_PROCESS_LOG_NO from dual;
    
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
            , '조사 자료 예측 모델 처리 시작' /* 조사_자료_로그.로그_메세지 */
            , p_USER_NO /* 조사_자료_로그.생성자_번호 */
            , SYSDATE /* 조사_자료_로그.시작_일시 */
            , p_SRVY_NO /* 조사_자료_로그.조사_번호 */
            , 'PCSE0008' /* 조사_자료_로그.처리_구분 */
            , 'PCST0001' /* 조사_자료_로그.처리_상태 */
            , NULL /* 조사_자료_로그.종료_일시 */
        );
    END IF;

    --평가자료 현행화
    GPMS.PRC_CLAC_PREDCT_ROUTE_SRVY_EVL(p_ROUTE_CODE, p_SRVY_NO, p_USER_NO, p_MODE, o_PROCCODE, o_PROCMSG);
    
    IF (o_PROCCODE = 'false')
    THEN
        IF ((p_SRVY_NO IS NOT NULL) OR (p_SRVY_NO <> ''))
        THEN
            /* 조사_자료_로그 수정 */
            UPDATE TL_SRVY_DTA_LOG SET
            LOG_MSSAGE = '조사 자료 예측 모델 처리 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
            , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
            , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
            WHERE 1 = 1
            AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
            ;
        END IF;
      RETURN;
    END IF;

    FOR v_erow IN predct_data (p_ROUTE_CODE, p_SRVY_NO)
    LOOP 
         -- 대상 집계 예측 데이터 삭제
        DELETE FROM TN_SM_CALC_PREDCT 
         WHERE ROUTE_CODE = v_erow.ROUTE_CODE
           AND DIRECT_CODE = v_erow.DIRECT_CODE
           AND TRACK = v_erow.TRACK
           AND STRTPT = v_erow.STRTPT
           AND ENDPT = v_erow.ENDPT;
    
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

            -- 조사일로부터 현재 날짜 역계산
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
                 , NVL(INITVAL_RD,0) INITVAL_RD_LOW
                 , NVL(INITVAL_RD,0) INITVAL_RD_MED
                 , NVL(INITVAL_RD,0) INITVAL_RD_HI
                 , NVL(INITVAL_IRI,0)
                 , 10
              INTO v_AC_LOW, v_AC_MED, v_AC_HI, v_BLOCK_CR_LOW, v_BLOCK_CR_MED, v_BLOCK_CR_HI
                 , v_LC_LOW, v_LC_MED, v_LC_HI, v_TC_LOW, v_TC_MED, v_TC_HI
                 , v_PTCHG_CR, v_POTHOLE_CR, v_RD_VAL
                 , v_RD_LOW, v_RD_MED, v_RD_HI, v_IRI_VAL
                 , v_TRG_GPCI
             FROM TN_PAV_IDX_ERYYVAL;

            -- 공사일로부터 현재 개월 수 계산
            SELECT ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE(v_CNTRWK_DT, 'YYYYMM')),0) INTO v_MONTH FROM DUAL;

        END IF;

        -- 산정일로부터 10년치 예측 데이터 생성
        FOR v_INDEX IN 0 .. 10
        LOOP
            v_TOT_MONTH := v_MONTH + (v_INDEX * 12);
            
            IF p_MODE = 'DEBUG' 
            THEN
                DBMS_OUTPUT.PUT_LINE('v_INDEX='||v_INDEX);
                DBMS_OUTPUT.PUT_LINE('v_TOT_MONTH='||v_TOT_MONTH);
            END IF;
                

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
                 , FN_GET_RUT_IDX(v_RD_LOW, v_RD_MED, v_RD_HI) RD_IDX
                 , FN_GET_RCI(IRI) RCI
                 , GPCI
              INTO v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_RD_IDX, v_RCI, v_GPCI
            
              FROM calc_idx;

            --v_RD_IDX    := FN_GET_RUT_IDX(v_RD_LOW, v_RD_MED, v_RD_HI);
            v_PTCHG_IDX := FN_GET_PATCH_IDX(v_PTCHG_CR);
            v_POT_IDX   := FN_GET_POT_IDX(v_POTHOLE_CR);

            v_SCR       := FN_GET_SCR(v_AC_IDX, v_BC_IDX, v_LC_IDX, v_TC_IDX, v_PTCHG_IDX, v_POT_IDX, v_RD_IDX);
            --v_GPCI      := FN_GET_GPCI(v_SCR, v_RCI);

            -- 예측 날짜 계산
            SELECT ADD_MONTHS(SYSDATE,(v_INDEX * 12)) INTO v_PREDCT_DT FROM DUAL;

            -- 예측 데이터 입력
            INSERT INTO TN_SM_CALC_PREDCT (
                                            PREDCT_NO,
                                            CALC_YEAR,
                                            CALC_MT,
                                            PREDCT_YEAR,
                                            PREDCT_MT,
                                            ROUTE_CODE,
                                            DIRECT_CODE,
                                            TRACK,
                                            STRTPT,
                                            ENDPT,
                                            AC_IDX,
                                            BC_IDX,
                                            LC_IDX,
                                            TC_IDX,
                                            PTCHG_IDX,
                                            POTHOLE_IDX,
                                            RD_IDX,
                                            RCI,
                                            SCR,
                                            GPCI,
                                            SRVY_YEAR,
                                            SRVY_MT
                                        ) VALUES (
                                              SEQ_TN_SM_CALC_PREDCT.NEXTVAL
                                            , v_erow.CALC_YEAR
                                            , v_erow.CALC_MT
                                            , TO_CHAR(v_PREDCT_DT, 'YYYY')
                                            , TO_CHAR(v_PREDCT_DT, 'MM')
                                            , v_erow.ROUTE_CODE
                                            , v_erow.DIRECT_CODE
                                            , v_erow.TRACK
                                            , v_erow.STRTPT
                                            , v_erow.ENDPT
                                            , v_AC_IDX
                                            , v_BC_IDX
                                            , v_LC_IDX
                                            , v_TC_IDX
                                            , v_PTCHG_IDX
                                            , v_POT_IDX
                                            , v_RD_IDX
                                            , v_RCI
                                            , v_SCR
                                            , v_GPCI
                                            , v_erow.SRVY_YEAR
                                            , v_erow.SRVY_MT
                                        );
        END LOOP;

    END LOOP;
    
    -- 보수 도래 시기 update
    FOR i IN (
        WITH RPAIR_TA_LIST AS (
            SELECT
                  MIN(ABS(6 - tnsmcalcpredct.GPCI)) OVER(PARTITION BY tnsmcalcpredct.ROUTE_CODE, tnsmcalcpredct.DIRECT_CODE, tnsmcalcpredct.TRACK, tnsmcalcpredct.STRTPT, tnsmcalcpredct.ENDPT  ) MIN_VAL
                , ABS(6 - tnsmcalcpredct.GPCI) CALC_ABS
                , tnsmcalcpredct.* 
            FROM TN_SM_CALC_PREDCT tnsmcalcpredct
            ORDER BY tnsmcalcpredct.ROUTE_CODE, tnsmcalcpredct.DIRECT_CODE, tnsmcalcpredct.TRACK, tnsmcalcpredct.STRTPT, tnsmcalcpredct.ENDPT, tnsmcalcpredct.PREDCT_YEAR, tnsmcalcpredct.PREDCT_MT
        )
        SELECT rpairtalist.ROUTE_CODE
             , rpairtalist.DIRECT_CODE
             , rpairtalist.TRACK
             , rpairtalist.STRTPT
             , rpairtalist.ENDPT
             , MAX(rpairtalist.CALC_YEAR) CALC_YEAR
             , MIN(rpairtalist.PREDCT_YEAR) PREDCT_YEAR
             , MIN(rpairtalist.PREDCT_YEAR) - MAX(rpairtalist.CALC_YEAR) RPAIR_TA_VAL
         FROM RPAIR_TA_LIST rpairtalist
        WHERE rpairtalist.MIN_VAL = rpairtalist.CALC_ABS
          AND (rpairtalist.ROUTE_CODE = p_ROUTE_CODE 
                OR EXISTS ( SELECT 'T' FROM TN_MUMM_SCTN_SRVY_DTA TNMUMMSCTNSRVYDTA
                                WHERE TNMUMMSCTNSRVYDTA.SRVY_NO = p_SRVY_NO
                                  AND TNMUMMSCTNSRVYDTA.USE_AT = 'Y'
                                  AND TNMUMMSCTNSRVYDTA.DELETE_AT = 'N'
                                  AND TNMUMMSCTNSRVYDTA.ROUTE_CODE = rpairtalist.ROUTE_CODE
                                   AND TNMUMMSCTNSRVYDTA.DIRECT_CODE = rpairtalist.DIRECT_CODE
                                   AND TNMUMMSCTNSRVYDTA.TRACK = rpairtalist.TRACK
                                   AND TNMUMMSCTNSRVYDTA.STRTPT = rpairtalist.STRTPT
                                   AND TNMUMMSCTNSRVYDTA.ENDPT = rpairtalist.ENDPT))
         GROUP BY rpairtalist.ROUTE_CODE
             , rpairtalist.DIRECT_CODE
             , rpairtalist.TRACK
             , rpairtalist.STRTPT
             , rpairtalist.ENDPT
    ) LOOP
    
        UPDATE TN_SM_DTA_LAST_STTUS
           SET RPAIR_TA = i.RPAIR_TA_VAL
         WHERE ROUTE_CODE = i.ROUTE_CODE
           AND DIRECT_CODE = i.DIRECT_CODE
           AND TRACK = i.TRACK
           AND STRTPT = i.STRTPT
           AND ENDPT = i.ENDPT;
                                   
    END LOOP;
    

    IF ((p_SRVY_NO IS NOT NULL) OR (p_SRVY_NO <> ''))
    THEN
        -- STTUS 예측 여부 Y
        UPDATE TN_SRVY_DTA_STTUS SET
              PRDTMDL_PROCESS_AT = 'Y' /* 조사_자료_현황.집계_구간_산출_여부 */
            , UPDUSR_NO = p_USER_NO /* 조사_자료_현황.수정자_번호 */
            , UPDT_DT = SYSDATE /* 조사_자료_현황.수정_일시 */
        WHERE 1 = 1
            AND SRVY_NO = p_SRVY_NO
            AND DELETE_AT ='N' /* 조사_자료_현황.삭제_여부 */
            AND USE_AT ='Y' /* 조사_자료_현황.사용_여부 */
            AND PRDTMDL_PROCESS_AT = 'N';
        o_PROCMSG :='조사 자료 예측 모델 적용 현황 갱신';
    
        /* 조사_자료_로그 수정 */
        UPDATE TL_SRVY_DTA_LOG SET
        LOG_MSSAGE = '조사 자료 예측 모델 처리 완료' /* 조사_자료_로그.로그_메세지 */
        , PROCESS_STTUS = 'PCST0002' /* 조사_자료_로그.처리_상태 */
        , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
        , SRVY_NO = p_SRVY_NO
        WHERE 1 = 1
        AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
        ;
    
        /* 조사_자료_엑셀 수정 */
        UPDATE TN_SRVY_DTA_EXCEL SET
          PRDTMDL_PROCESS_AT = 'Y'
         WHERE 1 = 1
           AND SRVY_NO = p_SRVY_NO;
    END IF;

    o_PROCMSG :='조사 자료 예측 모델 처리 완료';   
    o_PROCCODE :='true';

EXCEPTION  
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
        
        IF ((p_SRVY_NO IS NOT NULL) OR (p_SRVY_NO <> ''))
        THEN
            /* 조사_자료_로그 수정 */
            UPDATE TL_SRVY_DTA_LOG SET
            LOG_MSSAGE = '조사 자료 예측 모델 처리 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
            , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
            , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
            WHERE 1 = 1
            AND PROCESS_LOG_NO = v_PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
            ;
        END IF;
    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;      
END PRC_CLAC_PREDCT_LAST;