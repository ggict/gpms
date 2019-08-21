/* rpairTrgetGroupDAO.SELECTRpairTrgetGroupList */
/* 보수_대상_항목_그룹 조회 */
WITH RPMDCODES AS (
	SELECT  
		   tcrpairmthd.RPAIR_MTHD_CODE    /* 보수공법코드.보수_공법_코드 */
		 , tcrpairmthd.MSRC_CL_CODE    /* 보수공법코드.공법_분류_코드 */
		 , (SELECT tccode.CODE_NM    FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSRC_CL_CODE  ) MSRC_CL_NM
		 , tcrpairmthd.MSRC_NM    /* 보수공법코드.공법_명 */
		 , tcrpairmthd.MSRC_CT    /* 보수공법코드.공법_비용 */
		 , tcrpairmthd.MSRC_LCLAS_CODE    /* 보수공법코드.공법_대분류_코드 */
		 , tcrpairmthd.MSR_DM_CODE /* 공법선정비율_결정방식_코드 */
		 , NVL( (SELECT tccode.ATRB_VAL   FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSR_DM_CODE AND tccode.CL_CODE ='MSRD' ),'0') RPAIR_MTHD_CLASS
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
	   AND tcrpairmthd.DELETE_AT = 'N'  /* 보수공법코드.삭제_여부 */
	   AND tcrpairmthd.USE_AT = 'Y' /* 보수공법코드.사용_여부 */
)
, DEPTLIST AS (
	SELECT 
		tcdept.DEPT_CODE /* 부서_코드.부서_코드 */
		, tcdept.LOWEST_DEPT_NM DEPT_NM/* 부서_코드.최하위_부서_명 */
	FROM TC_DEPT tcdept
	WHERE tcdept.DELETE_AT = 'N'
		AND tcdept.USE_AT = 'Y'
		AND tcdept.DEPT_CODE IN ('6411399','6411799')
)
, repairtrgetSTS AS (
	SELECT  
		tnrpairtrgetgroup.DEPT_CODE /* 보수_대상_항목_그룹.부서코드 */
		, (SELECT tcdept.LOWEST_DEPT_NM AS DEPT_NM FROM GPMS.TC_DEPT tcdept WHERE tcdept.DEPT_CODE = tnrpairtrgetgroup.DEPT_CODE
		AND tcdept.USE_AT      = 'Y' AND tcdept.DELETE_AT   = 'N' ) DEPT_NM  
		, SUM(tnrpairtrgetgroup.BUDGET_ASIGN)  BUDGET_ASIGN
		, SUM(tnrpairtrgetgroup.ACCMLT_CALC) ACCMLT_CALC
	FROM TN_RPAIR_TRGET_GROUP tnrpairtrgetgroup
	INNER JOIN TN_RPAIR_TRGET_SLCTN tnrpairtrgetslctn
		ON tnrpairtrgetslctn.TRGET_SLCTN_NO = tnrpairtrgetgroup.TRGET_SLCTN_NO /* 보수_대상_선정.대상_선정_번호 */
		AND tnrpairtrgetslctn.DELETE_AT = 'N'
		AND tnrpairtrgetslctn.USE_AT    = 'Y'
	INNER JOIN TC_CODE celtcode
		ON celtcode.CODE_VAL   = tnrpairtrgetgroup.CELL_TYPE
		AND celtcode.CL_CODE   = 'CELT'
		AND celtcode.USE_AT    = 'Y'
		AND celtcode.DELETE_AT = 'N'
	LEFT OUTER JOIN TC_CODE vntccode
		ON vntccode.CODE_VAL   = tnrpairtrgetgroup.VMTC_GRAD
		AND vntccode.CL_CODE   = 'VNTC'
		AND vntccode.USE_AT    = 'Y'
		AND vntccode.DELETE_AT = 'N'
	INNER JOIN RPMDCODES
		ON RPMDCODES.RPAIR_MTHD_CODE= tnrpairtrgetgroup.RPAIR_MTHD_CODE
	WHERE 1 = 1      
		AND tnrpairtrgetgroup.TRGET_SLCTN_NO = 101 /* 보수_대상_항목_그룹.대상_선정_번호 */
		AND tnrpairtrgetgroup.SLCTN_AT ='Y'
		AND tnrpairtrgetgroup.DELETE_AT = 'N'
		AND tnrpairtrgetgroup.USE_AT    = 'Y'
	GROUP BY tnrpairtrgetgroup.DEPT_CODE 
)
     SELECT
      DEPTLIST.DEPT_CODE /* 보수공법코드.공법_명 */
      , DEPTLIST.DEPT_NM /* 보수공법코드.공법_비용 */
      , NVL(repairtrgetSTS.BUDGET_ASIGN,0) BUDGET_ASIGN
      , NVL(repairtrgetSTS.ACCMLT_CALC,0) ACCMLT_CALC
      FROM DEPTLIST
    LEFT OUTER JOIN repairtrgetSTS
    ON DEPTLIST.DEPT_CODE = repairtrgetSTS.DEPT_CODE
    ORDER BY	DEPTLIST.DEPT_CODE
     ;
     
     
     /* rpairTrgetGroupDAO.SELECTRpairTrgetGroupList */
/* 보수_대상_항목_그룹 조회 */
WITH RPMDCODES AS (
	SELECT  
		   tcrpairmthd.RPAIR_MTHD_CODE    /* 보수공법코드.보수_공법_코드 */
		 , tcrpairmthd.MSRC_CL_CODE    /* 보수공법코드.공법_분류_코드 */
		 , (SELECT tccode.CODE_NM    FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSRC_CL_CODE  ) MSRC_CL_NM
		 , tcrpairmthd.MSRC_NM    /* 보수공법코드.공법_명 */
		 , tcrpairmthd.MSRC_CT    /* 보수공법코드.공법_비용 */
		 , tcrpairmthd.MSRC_LCLAS_CODE    /* 보수공법코드.공법_대분류_코드 */
		 , tcrpairmthd.MSR_DM_CODE /* 공법선정비율_결정방식_코드 */
		 , NVL( (SELECT tccode.ATRB_VAL   FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSR_DM_CODE AND tccode.CL_CODE ='MSRD' ),'0') RPAIR_MTHD_CLASS
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
	   AND tcrpairmthd.DELETE_AT = 'N'  /* 보수공법코드.삭제_여부 */
	   AND tcrpairmthd.USE_AT = 'Y' /* 보수공법코드.사용_여부 */
)
, repairtrgetSTS AS (
SELECT
	RPMDCODES.RPAIR_MTHD_CODE	/* 보수_대상_항목_그룹.보수_공법_코드 */
	, RPMDCODES.MSRC_NM 	/* 보수공법코드.공법_명 */
	, RPMDCODES.RPAIR_FEE 	/* 보수공법코드.공법_비용 */
	, RPMDCODES.MSR_DM_CODE
	, SUM(tnrpairtrgetgroup.BUDGET_ASIGN)  BUDGET_ASIGN
	, SUM (tnrpairtrgetgroup.ACCMLT_CALC) ACCMLT_CALC 
FROM TN_RPAIR_TRGET_GROUP tnrpairtrgetgroup
INNER JOIN TN_RPAIR_TRGET_SLCTN tnrpairtrgetslctn
	ON tnrpairtrgetslctn.TRGET_SLCTN_NO = tnrpairtrgetgroup.TRGET_SLCTN_NO	/* 보수_대상_선정.대상_선정_번호 */
	AND tnrpairtrgetslctn.DELETE_AT = 'N'
	AND tnrpairtrgetslctn.USE_AT    = 'Y'
INNER JOIN TC_CODE celtcode
	ON celtcode.CODE_VAL   = tnrpairtrgetgroup.CELL_TYPE
	AND celtcode.CL_CODE   = 'CELT'
	AND celtcode.USE_AT    = 'Y'
	AND celtcode.DELETE_AT = 'N'
LEFT OUTER JOIN TC_CODE vntccode
	ON vntccode.CODE_VAL   = tnrpairtrgetgroup.VMTC_GRAD
	AND vntccode.CL_CODE   = 'VNTC'
	AND vntccode.USE_AT    = 'Y'
	AND vntccode.DELETE_AT = 'N'
INNER JOIN RPMDCODES
	ON RPMDCODES.RPAIR_MTHD_CODE= tnrpairtrgetgroup.RPAIR_MTHD_CODE
WHERE 1 = 1
	AND tnrpairtrgetgroup.TRGET_SLCTN_NO = 101	/* 보수_대상_항목_그룹.대상_선정_번호 */
	AND tnrpairtrgetgroup.SLCTN_AT ='Y'
	AND tnrpairtrgetgroup.DELETE_AT = 'N'
	AND tnrpairtrgetgroup.USE_AT    = 'Y'
GROUP BY RPMDCODES.RPAIR_MTHD_CODE
	/* 보수_대상_항목_그룹.보수_공법_코드 */
	, RPMDCODES.MSRC_NM
	/* 보수공법코드.공법_명 */
	, RPMDCODES.RPAIR_FEE
	/* 보수공법코드.공법_비용 */
	, RPMDCODES.MSR_DM_CODE 
)
	SELECT
		RPMDCODES.MSRC_NM /* 보수공법코드.공법_명 */
		, RPMDCODES.RPAIR_FEE /* 보수공법코드.공법_비용 */	
		, RPMDCODES.MSR_DM_CODE
		, RPMDCODES.RPAIR_MTHD_CODE /* 보수_대상_항목_그룹.보수_공법_코드 */
		, RPMDCODES.MSRC_NM /* 보수공법코드.공법_명 */
		, RPMDCODES.RPAIR_FEE /* 보수공법코드.공법_비용 */
		, RPMDCODES.MSR_DM_CODE
		, NVL(repairtrgetSTS.BUDGET_ASIGN,0) BUDGET_ASIGN
		, NVL(repairtrgetSTS.ACCMLT_CALC,0) ACCMLT_CALC
	FROM RPMDCODES
	LEFT OUTER JOIN repairtrgetSTS
		ON RPMDCODES.RPAIR_MTHD_CODE= repairtrgetSTS.RPAIR_MTHD_CODE
        ORDER BY	RPMDCODES.RPAIR_MTHD_CODE  ;
        
        
        /* rpairTrgetGroupDAO.SELECTRpairTrgetGroupList */
/* 보수_대상_항목_그룹 조회 */
WITH RPMDCODES AS (
	SELECT  
		   tcrpairmthd.RPAIR_MTHD_CODE    /* 보수공법코드.보수_공법_코드 */
		 , tcrpairmthd.MSRC_CL_CODE    /* 보수공법코드.공법_분류_코드 */
		 , (SELECT tccode.CODE_NM    FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSRC_CL_CODE  ) MSRC_CL_NM
		 , tcrpairmthd.MSRC_NM    /* 보수공법코드.공법_명 */
		 , tcrpairmthd.MSRC_CT    /* 보수공법코드.공법_비용 */
		 , tcrpairmthd.MSRC_LCLAS_CODE    /* 보수공법코드.공법_대분류_코드 */
		 , tcrpairmthd.MSR_DM_CODE /* 공법선정비율_결정방식_코드 */
		 , NVL( (SELECT tccode.ATRB_VAL   FROM GPMS.TC_CODE  tccode  WHERE tccode.CODE_VAL = tcrpairmthd.MSR_DM_CODE AND tccode.CL_CODE ='MSRD' ),'0') RPAIR_MTHD_CLASS
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
	   AND tcrpairmthd.DELETE_AT = 'N'  /* 보수공법코드.삭제_여부 */
	   AND tcrpairmthd.USE_AT = 'Y' /* 보수공법코드.사용_여부 */
)
, ADMINLIST AS (
	SELECT 
		NVL(GU.BJCD, CITY.BJCD) ADM_CODE
		, DECODE(GU.NAME, NULL, CITY.NAME, CITY.NAME ||' '|| GU.NAME) ADM_NM     
	FROM N3A_G0100000 CITY
	LEFT OUTER JOIN N3A_G0100000 GU
		ON GU.BJCD NOT LIKE '41__000000'
		AND SUBSTR(CITY.BJCD,1,4) = SUBSTR(GU.BJCD,1,4) 
	WHERE  1=1
		AND CITY.BJCD LIKE '41__000000'
)
, repairtrgetSTS AS (
SELECT  
	tnrpairtrgetgroup.ADM_CODE /* 보수_대상_항목_그룹.행정구역코드 */
	, SUM(tnrpairtrgetgroup.BUDGET_ASIGN)  BUDGET_ASIGN
	, SUM(tnrpairtrgetgroup.ACCMLT_CALC) ACCMLT_CALC /* 보수_대상_항목_그룹.섹션목록 */
FROM TN_RPAIR_TRGET_GROUP tnrpairtrgetgroup
INNER JOIN TN_RPAIR_TRGET_SLCTN tnrpairtrgetslctn
	ON tnrpairtrgetslctn.TRGET_SLCTN_NO = tnrpairtrgetgroup.TRGET_SLCTN_NO /* 보수_대상_선정.대상_선정_번호 */
	AND tnrpairtrgetslctn.DELETE_AT = 'N'
	AND tnrpairtrgetslctn.USE_AT    = 'Y'
INNER JOIN TC_CODE celtcode
	ON celtcode.CODE_VAL   = tnrpairtrgetgroup.CELL_TYPE
	AND celtcode.CL_CODE   = 'CELT'
	AND celtcode.USE_AT    = 'Y'
	AND celtcode.DELETE_AT = 'N'
LEFT OUTER JOIN TC_CODE vntccode
	ON vntccode.CODE_VAL   = tnrpairtrgetgroup.VMTC_GRAD
	AND vntccode.CL_CODE   = 'VNTC'
	AND vntccode.USE_AT    = 'Y'
	AND vntccode.DELETE_AT = 'N'
INNER JOIN RPMDCODES
	ON RPMDCODES.RPAIR_MTHD_CODE= tnrpairtrgetgroup.RPAIR_MTHD_CODE
WHERE 1 = 1
	AND tnrpairtrgetgroup.TRGET_SLCTN_NO = 101	/* 보수_대상_항목_그룹.대상_선정_번호 */
	AND tnrpairtrgetgroup.SLCTN_AT ='Y'
	AND tnrpairtrgetgroup.DELETE_AT = 'N'
	AND tnrpairtrgetgroup.USE_AT = 'Y'
	GROUP BY tnrpairtrgetgroup.ADM_CODE
)
	SELECT 
		ADMINLIST.ADM_CODE   
		,ADMINLIST.ADM_NM
		,NVL(repairtrgetSTS.BUDGET_ASIGN,0) BUDGET_ASIGN
		,NVL(repairtrgetSTS.ACCMLT_CALC,0) ACCMLT_CALC
	FROM ADMINLIST
	LEFT OUTER JOIN repairtrgetSTS
		ON  ADMINLIST.ADM_CODE    =  repairtrgetSTS.ADM_CODE   
	ORDER BY ADMINLIST.ADM_CODE   ASC
  
   
  ;