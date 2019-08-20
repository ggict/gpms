WITH NODELIST
AS
(     
     SELECT /* tnRpairTrget.select leehb */       
       tnrpairtrget.ROUTE_CODE    /* 보수_대상_항목.노선_코드 */
     , tnrpairtrget.DIRECT_CODE    /* 보수_대상_항목.행선_코드 */
     , tnrpairtrget.TRACK    /* 보수_대상_항목.차로 */
     , tnrpairtrget.STRTPT    /* 보수_대상_항목.시점 */
     , tnrpairtrget.ENDPT   /* 보수_대상_항목.종점 */     
     , tnrpairtrget.CELL_TYPE    /* 보수_대상_항목.섹션_구분 */
      , tnrpairtrget.MSR_DM_CODE  
         , tnrpairtrget.DEPT_CODE    /* 보수_대상_항목.부서코드 */
     , nexttnrpairtrget.ROUTE_CODE   NEXT_ROUTE_CODE /* 보수_대상_항목.노선_코드 */
     , nexttnrpairtrget.DIRECT_CODE   NEXT_DIRECT_CODE /* 보수_대상_항목.행선_코드 */
     , nexttnrpairtrget.TRACK   NEXT_TRACK /* 보수_대상_항목.차로 */
     , nexttnrpairtrget.STRTPT  NEXT_STRTPT  /* 보수_대상_항목.시점 */
     , nexttnrpairtrget.ENDPT   NEXT_ENDPT /* 보수_대상_항목.종점 */
     , nexttnrpairtrget.CELL_TYPE    NEXT_CELL_TYPE/* 보수_대상_항목.섹션_구분 */
     , nexttnrpairtrget.DEPT_CODE    NEXT_DEPT_CODE/* 보수_대상_항목.부서코드 */
      , nexttnrpairtrget.MSR_DM_CODE NEXT_MSR_DM_CODE
      , RANK() OVER(PARTITION BY tnrpairtrget.ROUTE_CODE    /* 보수_대상_항목.노선_코드 */
      , tnrpairtrget.DIRECT_CODE    /* 보수_대상_항목.행선_코드 */
     ORDER BY tnrpairtrget.STRTPT ASC) RNK_NO
  FROM TN_RPAIR_TRGET  tnrpairtrget  /*** 보수_대상_항목 테이블 ***/
  LEFT OUTER JOIN TN_RPAIR_TRGET  nexttnrpairtrget /*** 보수_대상_항목 테이블 ***/
  on  1=1
  AND tnrpairtrget.TRGET_SLCTN_NO = nexttnrpairtrget.TRGET_SLCTN_NO
  AND tnrpairtrget.ROUTE_CODE = nexttnrpairtrget.ROUTE_CODE   /* 보수_대상_항목.노선_코드 */
  AND  tnrpairtrget.DIRECT_CODE  = nexttnrpairtrget.DIRECT_CODE     /* 보수_대상_항목.행선_코드 */
AND  tnrpairtrget.TRACK  = nexttnrpairtrget.TRACK     /* 보수_대상_항목.차로 */
AND  tnrpairtrget.ENDPT  = nexttnrpairtrget.STRTPT     /* 보수_대상_항목.시점 */
AND tnrpairtrget.CELL_TYPE =   nexttnrpairtrget.CELL_TYPE   /* 보수_대상_항목.섹션_구분 */
AND tnrpairtrget.MSR_DM_CODE  =   nexttnrpairtrget.MSR_DM_CODE   /* 보수_대상_항목.보수_공법_코드 */
 WHERE 1=1
   AND tnrpairtrget.TRGET_SLCTN_NO = 78
   AND tnrpairtrget.MSR_DM_CODE IS NOT NULL     --136
  -- AND tnrpairtrget.MSR_DM_CODE =  'MSRD0001'
   ORDER BY tnrpairtrget.ROUTE_CODE ASC,tnrpairtrget.DIRECT_CODE ASC,tnrpairtrget.TRACK ASC, tnrpairtrget.STRTPT ASC
  ), NODETREE AS(
  SELECT LEVEL LVL_NO, CONNECT_BY_ISLEAF LEAF, CONNECT_BY_ROOT ENDPT NODE_ENDPT, NODELIST.* FROM NODELIST
  WHERE 1=1
  START WITH NODELIST.NEXT_STRTPT IS NULL
  CONNECT BY NOCYCLE PRIOR NODELIST.ENDPT = NODELIST.NEXT_ENDPT
  AND PRIOR NODELIST.ROUTE_CODE = NODELIST.NEXT_ROUTE_CODE
  AND PRIOR  NODELIST.DIRECT_CODE = NODELIST.NEXT_DIRECT_CODE
  AND PRIOR  NODELIST.TRACK = NODELIST.NEXT_TRACK
  AND PRIOR  NODELIST.CELL_TYPE = NODELIST.NEXT_CELL_TYPE
  AND PRIOR  NODELIST.MSR_DM_CODE = NODELIST.NEXT_MSR_DM_CODE
   ORDER SIBLINGS BY NODELIST.ROUTE_CODE ASC,NODELIST.DIRECT_CODE ASC,NODELIST.TRACK ASC, NODELIST.STRTPT ASC
  ) 
,LEAFLIST AS (
  SELECT * FROM NODETREE
  WHERE NODETREE.LEAF = 1
  ORDER BY NODETREE.ROUTE_CODE ASC,NODETREE.DIRECT_CODE ASC,NODETREE.TRACK ASC, NODETREE.STRTPT ASC
) 
SELECT     
        LEAFLIST.ROUTE_CODE    /* 최소_구간_조사_자료.노선_코드 */
        , LEAFLIST.DEPT_CODE
        , LEAFLIST.DIRECT_CODE    /* 최소_구간_조사_자료.행선_코드 */
        , LEAFLIST.TRACK    /* 최소_구간_조사_자료.차로 */
        , LISTAGG(  cellsect.CELL_ID,',')  WITHIN GROUP (ORDER BY CELL_ID ASC) CELL_IDS
        , MAX(LEAFLIST.MSR_DM_CODE) MSR_DM_CODE
        , MAX(cellsect.VMTC_GRAD) VMTC_GRAD
        , LEAFLIST.STRTPT
        , LEAFLIST.NODE_ENDPT ENDPT
        , SUM(cellsect.ROAD_L) ROAD_L-- LEAFLIST.NODE_ENDPT - LEAFLIST.STRTPT
        , SUM(cellsect.ROAD_A) ROAD_A --  (LEAFLIST.NODE_ENDPT - LEAFLIST.STRTPT )* 3.5 AR
        , COUNT(cellsect.ROAD_L) CELL_COUNT
        , MAX(LEAFLIST.CELL_TYPE) CELL_TYPE
    
FROM LEAFLIST 
 INNER JOIN  TN_SM_DTA_LAST_STTUS  tnsmdtalaststtus
   ON 1=1 
  AND tnsmdtalaststtus.ROUTE_CODE = LEAFLIST.ROUTE_CODE                                    /* 최소_구간_조사_자료.노선_코드 */
  AND tnsmdtalaststtus.DIRECT_CODE = LEAFLIST.DIRECT_CODE                                  /* 최소_구간_조사_자료.행선_코드 */
  AND tnsmdtalaststtus.TRACK = LEAFLIST.TRACK                                             /* 최소_구간_조사_자료.차로 */
  AND tnsmdtalaststtus.STRTPT >= LEAFLIST.STRTPT                                                     /* CELL_SECT.시점 */
  AND tnsmdtalaststtus.STRTPT <= LEAFLIST.NODE_ENDPT                                                     /* CELL_SECT.시점 */
  AND tnsmdtalaststtus.ENDPT >= LEAFLIST.STRTPT                                                     /* CELL_SECT.종점 */
  AND tnsmdtalaststtus.ENDPT <= LEAFLIST.NODE_ENDPT
  INNER JOIN CELL_SECT  cellsect
  ON 1=1 
  AND cellsect.ROUTE_CODE = tnsmdtalaststtus.ROUTE_CODE                                    /* 최소_구간_조사_자료.노선_코드 */
  AND cellsect.DIRECT_CODE = tnsmdtalaststtus.DIRECT_CODE                                  /* 최소_구간_조사_자료.행선_코드 */
  AND cellsect.TRACK = tnsmdtalaststtus.TRACK                                             /* 최소_구간_조사_자료.차로 */
  AND cellsect.STRTPT >= tnsmdtalaststtus.STRTPT                                                     /* CELL_SECT.시점 */
  AND cellsect.STRTPT <= tnsmdtalaststtus.ENDPT                                                     /* CELL_SECT.시점 */
  AND cellsect.ENDPT >= tnsmdtalaststtus.STRTPT                                                     /* CELL_SECT.종점 */
  AND cellsect.ENDPT <= tnsmdtalaststtus.ENDPT

GROUP BY LEAFLIST.ROUTE_CODE, LEAFLIST.DIRECT_CODE, LEAFLIST.TRACK, LEAFLIST.STRTPT, LEAFLIST.NODE_ENDPT, LEAFLIST.DEPT_CODE ;    