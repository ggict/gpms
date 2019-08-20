

WITH RPMDCODES AS
(
/*
SELECT '0' RPAIR_MTHD_CLASS, 'RPMD0001' RPAIR_MTHD_CODE, 'Do Nothing' CODE_NAME, 0 RPAIR_FEE, '0' YEAR, '현재 상태' INIT_GPCI FROM DUAL UNION 
SELECT '1' RPAIR_MTHD_CLASS, 'RPMD0002' RPAIR_MTHD_CODE, '예방적유지보수(PM)' CODE_NAME, 3000 RPAIR_FEE, '12' YEAR, '현재 상태' INIT_GPCI FROM DUAL UNION 
SELECT '2' RPAIR_MTHD_CLASS, 'RPMD0003' RPAIR_MTHD_CODE, '표면보수(ST)' CODE_NAME, 11000 RPAIR_FEE, '36' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0004' RPAIR_MTHD_CODE, '50mm 덧씌우기(OL)' CODE_NAME, 15000 RPAIR_FEE, '공용수명' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0005' RPAIR_MTHD_CODE, '50mm 절삭 덧씌우기' CODE_NAME, 18000 RPAIR_FEE, '공용수명' YEAR, '100' INIT_GPCI FROM DUAL UNION 
SELECT '3' RPAIR_MTHD_CLASS, 'RPMD0006' RPAIR_MTHD_CODE, '개질 절삭덧씌우기=MMNF' CODE_NAME, 21000 RPAIR_FEE, '공용수명 * 1.5' YEAR, '100' INIT_GPCI FROM DUAL   
*/
SELECT  
       tcrpairmthd.RPAIR_MTHD_CODE    /* 보수공법코드.보수_공법_코드 */
     , tcrpairmthd.MSRC_CL_CODE    /* 보수공법코드.공법_분류_코드 */
     , (SELECT tccode.CODE_NM    FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSRC_CL_CODE  ) MSRC_CL_NM
     , tcrpairmthd.MSRC_NM    /* 보수공법코드.공법_명 */
     , tcrpairmthd.MSRC_CT    /* 보수공법코드.공법_비용 */
     , tcrpairmthd.MSRC_LCLAS_CODE    /* 보수공법코드.공법_대분류_코드 */
     , DECODE(tcrpairmthd.MSR_DM_CODE, 'MSRD0001','1', 'MSRD0002','2', 'MSRD0003','3','0') RPAIR_MTHD_CLASS  /* 보수공법코드.공법선정비율_결정방식_코드 */
     , tnrpairmthduntpc.CNTRWK_AMOUNT    /* 보수_공법_단가.공사_금액 */
     , tnrpairmthduntpc.PAINT_AMOUNT    /* 보수_공법_단가.차선_도색_금액 */
     , tnrpairmthduntpc.ETC_AMOUNT    /* 보수_공법_단가.기타_금액 */
     , tnrpairmthduntpc.TOT_AMOUNT  RPAIR_FEE  /* 보수_공법_단가.총_금액 */
  FROM GPMS.TC_RPAIR_MTHD  tcrpairmthd  /*** 보수공법코드 테이블 ***/
  INNER JOIN GPMS.TN_RPAIR_MTHD_UNTPC  tnrpairmthduntpc  /*** 보수_공법_단가 테이블 ***/
  ON tnrpairmthduntpc.RPAIR_MTHD_CODE  = tcrpairmthd.RPAIR_MTHD_CODE  /* 보수_공법_단가.보수_공법_코드 */
  AND tnrpairmthduntpc.USE_AT = 'Y'   /* 보수_공법_단가.사용_여부 */
  AND tnrpairmthduntpc.DELETE_AT = 'N'
  WHERE 1=1
   AND tcrpairmthd.DELETE_AT = 'N'                                   /* 보수공법코드.삭제_여부 */
   AND tcrpairmthd.USE_AT = 'Y'                                         /* 보수공법코드.사용_여부 */
  -- order by tcrpairmthd.MSR_DM_CODE    /* 보수공법코드.공법선정비율_결정방식_코드 */
   
)

, budgets AS(
select 1000000000 TOTAL_AMT, 1000000000 * 0.8 MMNF_AMT, 1000000000 * 0.1 ST_AMT, 1000000000 * 0.1 PM_AMT FROM DUAL
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
, FN_GET_RPAIR_MTHD( LIST.CELL_TYPE,LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LCTC, LIST.RD) RPAIR_MTHD 
, FN_GET_GPCI_LIMITVAL( LIST.CELL_TYPE,LIST.VMTC_GRAD, LIST.CALC_GPCI, LIST.LCTC, LIST.RD) LIMIT_VAL       
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
     , DECODE(((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)), 0, 0, (((100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX))/((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)))*100) LCTC
     , DECODE(((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)), 0, 0, (((100-tnsmdtalaststtus.rd_IDX))/((100-tnsmdtalaststtus.AC_IDX) + (100-tnsmdtalaststtus.bC_IDX) + (100-tnsmdtalaststtus.lC_IDX) + (100-tnsmdtalaststtus.tC_IDX) + (100-tnsmdtalaststtus.PTCHG_IDX) + (100-tnsmdtalaststtus.POTHOLE_IDX) + (100-tnsmdtalaststtus.rd_IDX) + (100-tnsmdtalaststtus.rci)))*100) RD
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
/*
TN_RPAIR_TRGET
SELECT * FROM TN_RPAIR_TRGET
WHERE TRGET_SLCTN_NO=9
*/