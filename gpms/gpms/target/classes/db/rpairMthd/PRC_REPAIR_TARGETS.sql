CREATE OR REPLACE PROCEDURE GPMS."PRC_REPAIR_TARGETS" (
/*
    보수대상선정 집계
    보수대상선정 집계(현황 및 최신현황을 이용하여 보수 대상을 선정한다)
    
    생성일 : 2017-09-18
    생성자 : leehb1592@gmail.com
*/
p_USER_NO          NUMBER,
p_TRGET_SLCTN_NO TN_RPAIR_TRGET_SLCTN.TRGET_SLCTN_NO%TYPE,  /* 최소_구간_조사_자료.조사_번호 */
p_SLCTN_BUDGET NUMBER,
p_MMNF_RATE  NUMBER,
p_ST_RATE  NUMBER,
p_PM_RATE  NUMBER,
p_MODE VARCHAR2,
o_PROCCODE     OUT     VARCHAR2,
o_PROCMSG     OUT     VARCHAR2
)
AS
  
v_EXCEL_DTA_SEQ TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ%TYPE;

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

   
v_CALC_YEAR TN_SM_DTA_LAST_STTUS.CALC_YEAR%TYPE;
v_CALC_MT TN_SM_DTA_LAST_STTUS.CALC_MT%TYPE;
v_MMNF_AMT NUMBER;
v_ST_AMT NUMBER;
v_PM_AMT NUMBER;

v_MMNF_ACC_AMT NUMBER;
v_ST_ACC_AMT NUMBER;
v_PM_ACC_AMT NUMBER;

 

     
CURSOR rpairtrgetlist (
      i_TRGET_SLCTN_NO NUMBER ,
      i_CALC_YEAR   VARCHAR2,
      i_CALC_MT     VARCHAR2, 
      i_SLCTN_BUDGET NUMBER,
      i_MMNF_RATE  NUMBER,
      i_ST_RATE  NUMBER,
      i_PM_RATE  NUMBER
   ) IS 

WITH RPMDCODES AS
(
SELECT '0' RPAIR_MTHD_CLASS, 'RPMD0001' RPAIR_MTHD_CODE, 'Do Nothing' CODE_NAME, 0 RPAIR_FEE, '0' YEAR, '현재 상태' INIT_GPCI FROM DUAL UNION 
SELECT '1' RPAIR_MTHD_CLASS, 'RPMD0002' RPAIR_MTHD_CODE, '예방적유지보수(PM)' CODE_NAME, 3000 RPAIR_FEE, '12' YEAR, '현재 상태' INIT_GPCI FROM DUAL UNION 
SELECT '2' RPAIR_MTHD_CLASS, 'RPMD0003' RPAIR_MTHD_CODE, '표면보수(ST)' CODE_NAME, 11000 RPAIR_FEE, '36' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0004' RPAIR_MTHD_CODE, '50mm 덧씌우기(OL)' CODE_NAME, 15000 RPAIR_FEE, '공용수명' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0005' RPAIR_MTHD_CODE, '50mm 절삭 덧씌우기' CODE_NAME, 18000 RPAIR_FEE, '공용수명' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0006' RPAIR_MTHD_CODE, '개질 절삭덧씌우기=MMNF' CODE_NAME, 21000 RPAIR_FEE, '공용수명 * 1.5' YEAR, '100' INIT_GPCI FROM DUAL   

)
, budgets AS(
select i_SLCTN_BUDGET TOTAL_AMT, i_SLCTN_BUDGET * i_MMNF_RATE MMNF_AMT, i_SLCTN_BUDGET * i_ST_RATE ST_AMT, i_SLCTN_BUDGET * i_PM_RATE PM_AMT FROM DUAL
)
SELECT
DECODE( RPAIR_MTHD_CLASS, '0', 'M',  '1', DECODE( SIGN((budgets.PM_AMT) - ACCMLT_CALC),-1, 'PN', 'PY'), '2', DECODE( SIGN((budgets.ST_AMT) - ACCMLT_CALC), -1, 'SN', 'SY'),'3', DECODE( SIGN((budgets.MMNF_AMT) - ACCMLT_CALC), -1, 'MN', 'MY') ) BUDGET_CECK 
,DECODE( RPAIR_MTHD_CLASS, '0', 0,  '1', DECODE( SIGN(( budgets.PM_AMT) - ACCMLT_CALC),-1, 0, LIST.AMOUNT_CALC ), '2', DECODE( SIGN((budgets.ST_AMT) - ACCMLT_CALC), -1, 0, LIST.AMOUNT_CALC ),'3', DECODE( SIGN((budgets.MMNF_AMT) - ACCMLT_CALC), -1, 0, LIST.AMOUNT_CALC ) ) BUDGET_ASIGN
,LIST.* FROM
(
SELECT
SUM(AMOUNT_CALC) OVER(PARTITION BY RPAIR_MTHD_CLASS ORDER BY RPAIR_MTHD ASC, GPCI ASC  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) ACCMLT_CALC 
,LIST.* FROM (
SELECT
( SELECT RPMDCODES.RPAIR_MTHD_CLASS FROM RPMDCODES WHERE RPAIR_MTHD_CODE = LIST.RPAIR_MTHD) RPAIR_MTHD_CLASS
, (SELECT RPMDCODES.RPAIR_FEE FROM RPMDCODES WHERE RPAIR_MTHD_CODE = LIST.RPAIR_MTHD) RPAIR_FEE 
, (SELECT RPMDCODES.RPAIR_FEE FROM RPMDCODES WHERE RPAIR_MTHD_CODE = LIST.RPAIR_MTHD ) * LIST.AR  AMOUNT_CALC  
, LIST.* FROM
(
SELECT
 (SELECT tnrouteinfo.ROAD_NAME    /* 노선_대장_마스터.도로_명 */ FROM  GPMS.TN_ROUTE_INFO  tnrouteinfo  /*** 노선_대장_마스터 테이블 ***/
  WHERE tnrouteinfo.ROAD_NO = LIST.ROUTE_CODE ) ROAD_NAME
, ( SELECT tcdept.LOWEST_DEPT_NM AS DEPT_NM/*부서코드.최하위부서명*/ FROM GPMS.TC_DEPT  tcdept  /*** 부서_코드 테이블 ***/
 WHERE tcdept.DEPT_CODE = LIST.DEPT_CODE    AND tcdept.USE_AT = 'Y'  AND tcdept.DELETE_AT = 'N'  ) DEPT_NM
, (SELECT tccode.CODE_NM   FROM GPMS.TC_CODE  tccode  
 WHERE tccode.CODE_VAL = LIST.CELL_TYPE AND tccode.CL_CODE = 'CELT' AND tccode.USE_AT = 'Y'    AND tccode.DELETE_AT = 'N' ) CELL_TYPE_NM
,  GPMS.FN_GET_CNTRWK_YEARS(LIST.ROUTE_CODE  , LIST.DIRECT_CODE , LIST.TRACK, LIST.STRTPT, LIST.ENDPT ) CNTRWK_YEAR
, LIST.ENDPT - LIST.STRTPT LEN
, (LIST.ENDPT - LIST.STRTPT) * 3.5 AR
, DECODE( ROUND (LIST.GPCI / 10,0), 1 ,'RPMD0006' ,2,'RPMD0005',3 ,'RPMD0004',4,'RPMD0003',5,'RPMD0003',6,'RPMD0002','RPMD0001')  AS RPAIR_MTHD    /* 집계_자료_일반_현황.GPCI */ 
,LIST.*
 FROM
( 
SELECT /* cellSect.select  */
       cellsect.OBJECT_ID    /* CELL_SECT. */
       , tnsmdtagnlsttus.SRVY_YEAR    /* 집계_자료_일반_현황.조사_년도 */
     , tnsmdtagnlsttus.SRVY_MT    /* 집계_자료_일반_현황.조사_월 */
     , NVL(tnsmdtagnlsttus.GPCI, 999) AS GPCI    /* 집계_자료_일반_현황.GPCI */
     , NVL(tnsmdtagnlsttus.PC_GRAD, 999) AS PC_GRAD     /* 집계_자료_일반_현황.포장상태등급 */
     , cellsect.ROUTE_CODE    /* CELL_SECT.노선코드 */
     , cellsect.DIRECT_CODE    /* CELL_SECT.행선 */
     , cellsect.TRACK    /* CELL_SECT.차로 */
     , cellsect.STRTPT    /* CELL_SECT.시점 */
     , cellsect.ENDPT    /* CELL_SECT.종점 */
     , cellsect.DEPT_CODE    /* CELL_SECT.부서 코드 */
     , cellsect.CELL_TYPE    /* CELL_SECT.섹션구분 */
     , cellsect.VMTC_GRAD    /* CELL_SECT.교통량등급 */
     , cellsect.ROAD_GRAD    /* CELL_SECT.도로등급 */ -- 도로등급명
     , cellsect.ADM_CODE || '00000' ADM_CODE  /* CELL_SECT.행정구역코드 */
     , cellsect.CREAT_DT    /* CELL_SECT. */
     , cellsect.UPDUSR_NO    /* CELL_SECT. */
     , cellsect.UPDT_DT    /* CELL_SECT. */
          
     , tnsmdtagnlsttus.IRI_VAL    /* 집계_자료_일반_현황.종단평탄성_값 */
     , tnsmdtagnlsttus.CR_VAL    /* 집계_자료_일반_현황.표면손상값 */
     , tnsmdtagnlsttus.RD_VAL    /* 집계_자료_일반_현황.소성변형_값 */
     , tnsmdtagnlsttus.BLOCK_CR    /* 집계_자료_일반_현황.블럭_균열 */
     , tnsmdtagnlsttus.TRTS_BAC_CR    /* 집계_자료_일반_현황.거북등_균열 */
     , tnsmdtagnlsttus.PTCHG_IDX    /* 집계_자료_일반_현황.패칭_지수 */
     , tnsmdtagnlsttus.CNTL_DFECT    /* 집계_자료_일반_현황.지배_결함 */
     , tnsmdtagnlsttus.DMG_CUZ_CLMT    /* 집계_자료_일반_현황.파손_원인_기후 */
     , tnsmdtagnlsttus.DMG_CUZ_VMTC    /* 집계_자료_일반_현황.파손_원인_교통량 */
     , tnsmdtagnlsttus.DMG_CUZ_ETC    /* 집계_자료_일반_현황.파손_원인_기타 */
     , tnsmdtagnlsttus.AC_IDX    /* 집계_자료_일반_현황.거북등균열_지수 */
     , tnsmdtagnlsttus.LC_IDX    /* 집계_자료_일반_현황.종방향균열_지수 */
     , tnsmdtagnlsttus.TC_IDX    /* 집계_자료_일반_현황.횡방향균열_지수 */
     , tnsmdtagnlsttus.POTHOLE_IDX    /* 집계_자료_일반_현황.포트홀_지수 */
     , tnsmdtagnlsttus.TRNSPORT_QY    /* 집계_자료_일반_현황.교통_량 */
     , tnsmdtalaststtus.CALC_MT    /* 집계_자료_최신_현황.산정_월 */
     , tnsmdtalaststtus.CALC_YEAR    /* 집계_자료_최신_현황.산정_년도 */
     , tnsmdtalaststtus.GPCI  CALC_GPCI  /* 집계_자료_최신_현황.GPCI */
     , tnsmdtalaststtus.PC_GRAD   CALC_PC_GRAD /* 집계_자료_최신_현황.포장상태등급 */
     
  FROM GPMS.CELL_SECT  cellsect  /*** CELL_SECT 테이블 ***/
  INNER JOIN GPMS.TN_SM_DTA_GNL_STTUS  tnsmdtagnlsttus  /*** 집계_자료_일반_현황 테이블 ***/
  ON 1=1
   AND cellsect.ROUTE_CODE = tnsmdtagnlsttus.ROUTE_CODE                           /* CELL_SECT.노선코드 */
   AND cellsect.DIRECT_CODE = tnsmdtagnlsttus.DIRECT_CODE                        /* CELL_SECT.행선 */
   AND cellsect.TRACK = tnsmdtagnlsttus.TRACK                                     /* CELL_SECT.차로 */
   AND cellsect.STRTPT = tnsmdtagnlsttus.STRTPT                                   /* CELL_SECT.시점 */
   AND cellsect.ENDPT = tnsmdtagnlsttus.ENDPT                                     /* CELL_SECT.종점 */
   AND tnsmdtagnlsttus.DELETE_AT = 'N'                          /* 집계_자료_일반_현황.삭제_여부 */
   AND tnsmdtagnlsttus.USE_AT = 'Y'                                /* 집계_자료_일반_현황.사용_여부 */
   AND tnsmdtagnlsttus.LAST_DTA_AT='Y'
   INNER JOIN TN_SM_DTA_LAST_STTUS  tnsmdtalaststtus  /*** 집계_자료_최신_현황 테이블 ***/
   ON 1=1
   AND cellsect.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE                           /* CELL_SECT.노선코드 */
   AND cellsect.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE                        /* CELL_SECT.행선 */
   AND cellsect.TRACK = tnsmdtalaststtus.TRACK                                     /* CELL_SECT.차로 */
   AND cellsect.STRTPT = tnsmdtalaststtus.STRTPT                                   /* CELL_SECT.시점 */
   AND cellsect.ENDPT = tnsmdtalaststtus.ENDPT                                     /* CELL_SECT.종점 */
   AND tnsmdtalaststtus.DELETE_AT = 'N'                          /* 집계_자료_최신_현황.삭제_여부 */
   AND tnsmdtalaststtus.USE_AT = 'Y'                                /* 집계_자료_최신_현황.사용_여부 */
   
    ) LIST
    ) LIST
    
    ORDER BY RPAIR_MTHD ASC, GPCI ASC
    )LIST 
    )LIST
    INNER JOIN budgets
    ON 1=1
;
   
-- DECLARE RAISE_EXT exception;
BEGIN
   
    -- SELECT  TO_NUMBER(ATRB_VAL) into v_UNIT FROM TC_CODE WHERE  CODE_VAL='UNIT0002' AND USE_AT='Y' AND DELETE_AT='N';
    v_MMNF_AMT :=0;
    v_ST_AMT :=0;
    v_PM_AMT :=0;
    v_MMNF_ACC_AMT :=0;
    v_ST_ACC_AMT :=0;
    v_PM_ACC_AMT :=0;
    


    o_PROCCODE :='false';                    
    o_PROCMSG := '보수대상선정 집계 초기화(삭제)준비';
    
    if p_MODE = 'DEBUG' THEN
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) 메시지: '||o_PROCMSG);
        
    END IF;
    
    DELETE TN_RPAIR_TRGET
    WHERE TRGET_SLCTN_NO = p_TRGET_SLCTN_NO;
    o_PROCMSG := '보수대상선정 집계 초기화(삭제)완료';

    SELECT TO_CHAR(SYSDATE, 'yyyy'),     TO_CHAR(SYSDATE, 'MM') into v_CALC_YEAR, v_CALC_MT from dual;
    
    if p_MODE = 'DEBUG' THEN
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) 메시지: '||o_PROCMSG);
        
    END IF;
             
    
    FOR v_erow IN rpairtrgetlist(p_TRGET_SLCTN_NO, v_CALC_YEAR, v_CALC_MT, p_SLCTN_BUDGET ,p_MMNF_RATE  ,p_ST_RATE  ,p_PM_RATE  )
    LOOP 
    
     -- lOOP
    INSERT INTO TN_RPAIR_TRGET (
        TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */
        , ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , TRACK /* 보수_대상_항목.차로 */
        , STRTPT /* 보수_대상_항목.시점 */
        , ENDPT /* 보수_대상_항목.종점 */
        , CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , SRVY_MT /* 보수_대상_항목.조사_월 */
        , SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , GPCI /* 보수_대상_항목.GPCI */
        , PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , CALC_MT /* 보수_대상_항목.산정_월 */
        , CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , VMTC /* 보수_대상_항목.교통량 */
        , LEN /* 보수_대상_항목.연장 */
        , AR /* 보수_대상_항목.면적 */
        , RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , FIXING_AT /* 보수_대상_항목.고정_여부 */
        , BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , DELETE_AT /* 보수_대상_항목.삭제_여부 */
        , USE_AT /* 보수_대상_항목.사용_여부 */
        , CRTR_NO /* 보수_대상_항목.생성자_번호 */
        , CREAT_DT /* 보수_대상_항목.생성_일시 */
        , UPDUSR_NO /* 보수_대상_항목.수정자_번호 */
        , UPDT_DT /* 보수_대상_항목.수정_일시 */
        ) 
     SELECT
        SEQ_TN_RPAIR_TRGET.NEXTVAL TRGET_ITEM_NO /* 보수_대상_항목.대상_항목_번호 */
        , p_TRGET_SLCTN_NO TRGET_SLCTN_NO /* 보수_대상_항목.대상_선정_번호 */
        , NULL SLCTN_STEP /* 보수_대상_항목.선정_단계 */
        , NULL ITEM_SLCTN_STTUS /* 보수_대상_항목.항목_선정_상태 */            
        , v_erow.ROUTE_CODE /* 보수_대상_항목.노선_코드 */
        , v_erow.DIRECT_CODE /* 보수_대상_항목.행선_코드 */
        , v_erow.TRACK /* 보수_대상_항목.차로 */
        , v_erow.STRTPT /* 보수_대상_항목.시점 */
        , v_erow.ENDPT /* 보수_대상_항목.종점 */
        , v_erow.CELL_TYPE /* 보수_대상_항목.섹션_구분 */
        , v_erow.SRVY_MT /* 보수_대상_항목.조사_월 */
        , v_erow.SRVY_YEAR /* 보수_대상_항목.조사_년도 */
        , v_erow.GPCI /* 보수_대상_항목.GPCI */
        , v_erow.PC_GRAD /* 보수_대상_항목.포장상태등급 */
        , v_erow.CALC_YEAR /* 보수_대상_항목.산정_년도 */
        , v_erow.CALC_MT /* 보수_대상_항목.산정_월 */
        , v_erow.CALC_GPCI /* 보수_대상_항목.산정_GPCI */
        , v_erow.CALC_PC_GRAD /* 보수_대상_항목.산정_포장상태등급 */
        , v_erow.CNTRWK_YEAR /* 보수_대상_항목.공사_년도 */
        , 0 VMTC /* 보수_대상_항목.교통량 */
        , v_erow.LEN /* 보수_대상_항목.연장 */
        , v_erow.AR /* 보수_대상_항목.면적 */
        , v_erow.RPAIR_MTHD RPAIR_MTHD_CODE /* 보수_대상_항목.보수_공법_코드 */
        , v_erow.AMOUNT_CALC /* 보수_대상_항목.금액_산정 */
        , v_erow.BUDGET_ASIGN /* 보수_대상_항목.예산_배정 */
        , 'N' FIXING_AT /* 보수_대상_항목.고정_여부 */
        , v_erow.BUDGET_CECK /* 보수_대상_항목.예산_체크 */
        , v_erow.ACCMLT_CALC /* 보수_대상_항목.누적_산정 */
        , 'N' SLCTN_AT /* 보수_대상_항목.선정_여부 */
        , NULL SLCTN_DT /* 보수_대상_항목.선정_일시 */
        , 'N' /* 보수_대상_항목.삭제_여부 */
        , 'Y' /* 보수_대상_항목.사용_여부 */
        , p_USER_NO /* 보수_대상_항목.생성자_번호 */
        , SYSDATE /* 보수_대상_항목.생성_일시 */
        , p_USER_NO /* 보수_대상_항목.수정자_번호 */
        , SYSDATE /* 보수_대상_항목.수정_일시 */
    FROM dual;
    
    IF v_erow.BUDGET_CECK = 'MY'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_MMNF_AMT) INTO  v_MMNF_AMT FROM DUAL;
    END IF;
    
    IF v_erow.BUDGET_CECK = 'PY'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_PM_AMT) INTO  v_PM_AMT FROM DUAL;
    END IF;
    
    
    IF v_erow.BUDGET_CECK = 'SY'    THEN
        SELECT greatest(v_erow.BUDGET_ASIGN,v_ST_AMT) INTO  v_ST_AMT FROM DUAL;
    END IF;
    


    END LOOP;
  
     
                        
    o_PROCMSG :='보수대상선정 집계 완료';   
    o_PROCCODE :='true';
    if p_MODE = 'DEBUG' THEN
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) MMNF_AMT: '||v_MMNF_AMT);
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) PM_AMT: '||v_PM_AMT);
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) ST_AMT: '||v_ST_AMT);
        DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) 메시지: '||o_PROCMSG);
    END IF;
     

EXCEPTION  
    WHEN OTHERS
    THEN
        o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
        if p_MODE = 'DEBUG' THEN
            DBMS_OUTPUT.PUT_LINE('보수대상선정 집계(PRC_REPAIR_TARGETS) 오류 발생: TRGET_SLCTN_NO ='||p_TRGET_SLCTN_NO ||' msg='|| o_PROCMSG );            
        END IF;
    --  o_errmsg := SUBSTR(SQLERRM,1,100);
    -- o_errmsg :=  SUBSTR(SQLERRM+'이상있는 라인'+v_seq,1,100);
    RAISE;      
END PRC_REPAIR_TARGETS;
/
