/**
 * [포장상태 평가]
 * 평가 완료된 조사자료 공간 정보 보정 
 * 
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_SRVY_NO : 조사자료 번호
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
create or replace PROCEDURE      "PRC_SRVY_DTA_SYS_REFLCT" (
/*
    조사자료 데이터 공간 보정
    
    생성일 : 2017-08-03
    생성자 : skc@muhanit.kr
*/
   p_USER_NO          NUMBER,
   p_SRVY_NO        NUMBER,
   o_PROCCODE     OUT     VARCHAR2,
   o_PROCMSG     OUT     VARCHAR2)
AS
   v_SRID           	NUMBER;
   v_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
   v_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
   p_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
   p_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
   v_TRACE_LON      	TN_MUMM_SCTN_SRVY_DTA.TRACE1_LO%TYPE;
   v_TRACE_LAT      	TN_MUMM_SCTN_SRVY_DTA.TRACE1_LA%TYPE;
   v_DTA_NO				TN_MUMM_SCTN_SRVY_DTA.DTA_NO%TYPE;
   v_SRVY_YEAR          TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR%TYPE;
   v_SRVY_MT            TN_MUMM_SCTN_SRVY_DTA.SRVY_MT%TYPE;
   v_DTA_STTUS_NO		TN_SRVY_DTA_STTUS.DTA_STTUS_NO%TYPE;
   v_ROUTE_CODE 	TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE%TYPE;
   v_DIRECT_CODE 	TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE%TYPE;
   v_TRACK 	TN_MUMM_SCTN_SRVY_DTA.TRACK%TYPE; 
   v_GEOM           	SDO_GEOMETRY;
   v_INDEX          	INTEGER := 0;
   v_UNIT               NUMBER;
   /* 로딩된 최소_구간_조사_자료 조회 l,m,h 추가 */
   CURSOR t_CURSOR (
   i_SRVY_NO TN_MUMM_SCTN_SRVY_DTA.SRVY_NO%TYPE,
   i_STRTPT TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE,
   i_ENDPT TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE
   ) IS
   WITH tnmummsctnsrvydta AS
    (
    SELECT
                tnmummsctnsrvydta.DTA_NO /* 최소_구간_조사_자료.자료_번호 */
                , tnmummsctnsrvydta.SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
                , tnmummsctnsrvydta.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                , tnmummsctnsrvydta.ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
                , tnmummsctnsrvydta.DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
                , tnmummsctnsrvydta.TRACK /* 최소_구간_조사_자료.차로 */
                , tnmummsctnsrvydta.STRTPT /* 최소_구간_조사_자료.시점 */
                , tnmummsctnsrvydta.ENDPT /* 최소_구간_조사_자료.종점 */
                , tnmummsctnsrvydta.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
                , tnmummsctnsrvydta.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
                , tnmummsctnsrvydta.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
                , tnmummsctnsrvydta.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
                , tnmummsctnsrvydta.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
                , tnmummsctnsrvydta.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
                , tnmummsctnsrvydta.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
                , tnmummsctnsrvydta.BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                , tnmummsctnsrvydta.CR_LT /* 최소_구간_조사_자료.균열_길이 */
                , tnmummsctnsrvydta.CR_WID /* 최소_구간_조사_자료.균열_폭 */
                , tnmummsctnsrvydta.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                , tnmummsctnsrvydta.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                , tnmummsctnsrvydta.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                , tnmummsctnsrvydta.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                , tnmummsctnsrvydta.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                , tnmummsctnsrvydta.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                , tnmummsctnsrvydta.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                , tnmummsctnsrvydta.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                , tnmummsctnsrvydta.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                , tnmummsctnsrvydta.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                , tnmummsctnsrvydta.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                , tnmummsctnsrvydta.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                , tnmummsctnsrvydta.AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                , tnmummsctnsrvydta.BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                , tnmummsctnsrvydta.LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                , tnmummsctnsrvydta.TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                , tnmummsctnsrvydta.PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                , tnmummsctnsrvydta.POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                , tnmummsctnsrvydta.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                , tnmummsctnsrvydta.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                , tnmummsctnsrvydta.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                , tnmummsctnsrvydta.XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                , tnmummsctnsrvydta.RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                , tnmummsctnsrvydta.SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                , tnmummsctnsrvydta.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                , tnmummsctnsrvydta.CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                , tnmummsctnsrvydta.RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                , tnmummsctnsrvydta.NHPCI /* 최소_구간_조사_자료.NHPCI */
                , tnmummsctnsrvydta.GPCI /* 최소_구간_조사_자료.GPCI */
                , tnmummsctnsrvydta.SPI /* 최소_구간_조사_자료.SPI */
                , tnmummsctnsrvydta.PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                , tnmummsctnsrvydta.CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                , tnmummsctnsrvydta.DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                , tnmummsctnsrvydta.DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                , tnmummsctnsrvydta.DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                , tnmummsctnsrvydta.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
                , tnmummsctnsrvydta.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
                , tnmummsctnsrvydta.MEMO /* 최소_구간_조사_자료.메모 */
                , tnmummsctnsrvydta.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
                , tnmummsctnsrvydta.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
                , tnmummsctnsrvydta.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
                , tnmummsctnsrvydta.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
                , tnmummsctnsrvydta.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
                , tnmummsctnsrvydta.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
                , tnmummsctnsrvydta.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
                , tnmummsctnsrvydta.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
                , tnmummsctnsrvydta.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
                , tnmummsctnsrvydta.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
                , tnmummsctnsrvydta.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
                , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                , tnmummsctnsrvydta.EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                , tnmummsctnsrvydta.FRMULA_NO FRMULA_NO/* 최소_구간_조사_자료.수식_번호 */
                , tnmummsctnsrvydta.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_균 */
                , tnmummsctnsrvydta.RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                , tnmummsctnsrvydta.UPDT_AT /* 최소_구간_조사_자료.수정_여부 */
                , tnmummsctnsrvydta.LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
                , tnmummsctnsrvydta.G2_LONLAT 
                , tnmummsctnsrvydta.G2_LONLAT_BUFFER
            FROM TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta     
            WHERE
             1 = 1
           AND tnmummsctnsrvydta.UPDT_AT = 'N'
           AND tnmummsctnsrvydta.USE_AT = 'Y'
           AND tnmummsctnsrvydta.DELETE_AT = 'N'
           AND tnmummsctnsrvydta.TRACE1_LO <> 0             
           AND tnmummsctnsrvydta.TRACE1_LO IS NOT NULL
           AND tnmummsctnsrvydta.TRACE1_LA <> 0             
           AND tnmummsctnsrvydta.TRACE1_LA IS NOT NULL
    )  
    SELECT tnmummsctnsrvydtacal.* FROM (
    
      SELECT /*+ INDEX( TN_MUMM_SCTN_SRVY_DTA TN_MUMM_LONLAT_BUFFER_IDX  ) */
                tnmummsctnsrvydta.DTA_NO /* 최소_구간_조사_자료.자료_번호 */
                , tnmummsctnsrvydta.SRVY_NO /* 최소_구간_조사_자료.조사_번호 */
                , tnmummsctnsrvydta.SRVY_YEAR /* 최소_구간_조사_자료.조사_년도 */
                , tnmummsctnsrvydta.ROUTE_CODE /* 최소_구간_조사_자료.노선_코드 */
                , tnmummsctnsrvydta.DIRECT_CODE /* 최소_구간_조사_자료.행선_코드 */
                , tnmummsctnsrvydta.TRACK /* 최소_구간_조사_자료.차로 */
                , cell10.STRTPT /* 최소_구간_조사_자료.시점 */
                , cell10.ENDPT /* 최소_구간_조사_자료.종점 */
                , tnmummsctnsrvydta.IRI_VAL /* 최소_구간_조사_자료.종단평탄성_값 */
                , tnmummsctnsrvydta.RD_VAL /* 최소_구간_조사_자료.소성변형_값 */
                , tnmummsctnsrvydta.VRTCAL_CR /* 최소_구간_조사_자료.종방향_균열 */
                , tnmummsctnsrvydta.HRZNTAL_CR /* 최소_구간_조사_자료.횡방향_균열 */
                , tnmummsctnsrvydta.CNSTRCT_JOINT_CR /* 최소_구간_조사_자료.시공_줄눈_균열 */
                , tnmummsctnsrvydta.TRTS_BAC_CR /* 최소_구간_조사_자료.거북등_균열 */
                , tnmummsctnsrvydta.PTCHG_CR /* 최소_구간_조사_자료.패칭_균열 */
                , tnmummsctnsrvydta.BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                , tnmummsctnsrvydta.CR_LT /* 최소_구간_조사_자료.균열_길이 */
                , tnmummsctnsrvydta.CR_WID /* 최소_구간_조사_자료.균열_폭 */
                , tnmummsctnsrvydta.AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                , tnmummsctnsrvydta.AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                , tnmummsctnsrvydta.AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                , tnmummsctnsrvydta.BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                , tnmummsctnsrvydta.BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                , tnmummsctnsrvydta.BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                , tnmummsctnsrvydta.LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                , tnmummsctnsrvydta.LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                , tnmummsctnsrvydta.LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                , tnmummsctnsrvydta.TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                , tnmummsctnsrvydta.TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                , tnmummsctnsrvydta.TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                , tnmummsctnsrvydta.AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                , tnmummsctnsrvydta.BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                , tnmummsctnsrvydta.LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                , tnmummsctnsrvydta.TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                , tnmummsctnsrvydta.PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                , tnmummsctnsrvydta.POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                , tnmummsctnsrvydta.RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                , tnmummsctnsrvydta.RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                , tnmummsctnsrvydta.RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                , tnmummsctnsrvydta.XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                , tnmummsctnsrvydta.RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                , tnmummsctnsrvydta.SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                , tnmummsctnsrvydta.CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                , tnmummsctnsrvydta.CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                , tnmummsctnsrvydta.RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                , tnmummsctnsrvydta.NHPCI /* 최소_구간_조사_자료.NHPCI */
                , tnmummsctnsrvydta.GPCI /* 최소_구간_조사_자료.GPCI */
                , tnmummsctnsrvydta.SPI /* 최소_구간_조사_자료.SPI */
                , tnmummsctnsrvydta.PC_GRAD /* 최소_구간_조사_자료.포장상태등급 */
                , tnmummsctnsrvydta.CNTL_DFECT /* 최소_구간_조사_자료.지배_결함 */
                , tnmummsctnsrvydta.DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                , tnmummsctnsrvydta.DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                , tnmummsctnsrvydta.DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                , tnmummsctnsrvydta.SRVY_KND /* 최소_구간_조사_자료.조사_종류 */
                , tnmummsctnsrvydta.SRVY_MT /* 최소_구간_조사_자료.조사_월 */
                , tnmummsctnsrvydta.MEMO /* 최소_구간_조사_자료.메모 */
                , tnmummsctnsrvydta.SRVY_DE /* 최소_구간_조사_자료.조사_일자 */
                , tnmummsctnsrvydta.TRACE1_LA /* 최소_구간_조사_자료.TRACE1_LA */
                , tnmummsctnsrvydta.TRACE1_LO /* 최소_구간_조사_자료.TRACE1_LO */
                , tnmummsctnsrvydta.TRACE2_LA /* 최소_구간_조사_자료.TRACE2_LA */
                , tnmummsctnsrvydta.TRACE2_LO /* 최소_구간_조사_자료.TRACE2_LO */
                , tnmummsctnsrvydta.SRVY_NM /* 최소_구간_조사_자료.조사_명 */
                , tnmummsctnsrvydta.EXMNR_NM /* 최소_구간_조사_자료.조사자_명 */
                , tnmummsctnsrvydta.SCTN_STRTPT_DC /* 최소_구간_조사_자료.구간_시점_설명 */
                , tnmummsctnsrvydta.SCTN_ENDPT_DC /* 최소_구간_조사_자료.구간_종점_설명 */
                , tnmummsctnsrvydta.ROAD_NM /* 최소_구간_조사_자료.도로_명 */
                , tnmummsctnsrvydta.FRNT_IMG_FILE_NM /* 최소_구간_조사_자료.전방_이미지_파일_명 */
                , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_1 /* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
                , tnmummsctnsrvydta.RDSRFC_IMG_FILE_NM_2 /* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
                , tnmummsctnsrvydta.EXCEL_DTA_SEQ /* 최소_구간_조사_자료.엑셀_자료_SEQ */
                , tnmummsctnsrvydta.FRMULA_NO FRMULA_NO/* 최소_구간_조사_자료.수식_번호 */
                , tnmummsctnsrvydta.POTHOLE_CR /* 최소_구간_조사_자료.포트홀_균 */
                , tnmummsctnsrvydta.RI_IDX /* 최소_구간_조사_자료.평탄성_지수 */
                , tnmummsctnsrvydta.UPDT_AT /* 최소_구간_조사_자료.수정_여부 */
                , tnmummsctnsrvydta.LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
                , tnmummsctnsrvydta.G2_LONLAT 
                , tnmummsctnsrvydta.G2_LONLAT_BUFFER
      , ROW_NUMBER () OVER ( PARTITION BY tnmummsctnsrvydta.DTA_NO ORDER BY SDO_GEOM.SDO_DISTANCE( cell10.G2_CENTROID , tnmummsctnsrvydta.G2_LONLAT , 1 ) 
                                     ASC) RN
      FROM tnmummsctnsrvydta
      INNER JOIN CELL_10 cell10
        ON  tnmummsctnsrvydta.ROUTE_CODE = cell10.ROUTE_CODE
        AND tnmummsctnsrvydta.DIRECT_CODE = cell10.DIRECT_CODE
        AND tnmummsctnsrvydta.TRACK = cell10.TRACK
        AND tnmummsctnsrvydta.SRVY_NO = i_SRVY_NO
        AND cell10.STRTPT >=  i_STRTPT AND  cell10.STRTPT <= i_ENDPT /* 최소_구간_조사_자료.시점 */
        AND cell10.ENDPT >= i_STRTPT AND  cell10.ENDPT <= i_ENDPT /* 최소_구간_조사_자료.시점 */
        AND tnmummsctnsrvydta.STRTPT >= (cell10.STRTPT-1000) AND  tnmummsctnsrvydta.STRTPT <= cell10.STRTPT +1000 /* 최소_구간_조사_자료.시점 */
        AND tnmummsctnsrvydta.ENDPT >= (cell10.ENDPT-1000) AND  tnmummsctnsrvydta.ENDPT <= cell10.ENDPT +1000 /* 최소_구간_조사_자료.시점 */
   --   AND SDO_GEOM.SDO_DISTANCE( cell10.G2_CENTROID , tnmummsctnsrvydta.G2_LONLAT , 1 ) <= 70
        AND SDO_RELATE( cell10.G2_SPATIAL , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 'mask=ANYINTERACT' ) = 'TRUE'
   --   AND SDO_GEOM.SDO_INTERSECTION( cell10.G2_EXTENT , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 1 ) IS NOT NULL
   --   AND SDO_GEOM.SDO_INTERSECTION( cell10.G2_SPATIAL , tnmummsctnsrvydta.G2_LONLAT_BUFFER , 1 ) IS NOT NULL
        WHERE 1=1                                                    
    ) tnmummsctnsrvydtacal WHERE tnmummsctnsrvydtacal.RN=1; 
   
/* 로딩 된 조사자료의 경위도 좌표로와 20m CELL 공간DB로 시종점을 갱신 */
BEGIN
    o_PROCMSG := 'Y';
    o_PROCCODE :='false';
    SELECT  TO_NUMBER(ATRB_VAL) into v_UNIT FROM TC_CODE WHERE  CODE_VAL='UNIT0001' AND USE_AT='Y' AND DELETE_AT='N';
    
    SELECT ROUTE_CODE, DIRECT_CODE, TRACK, SRVY_YEAR, SRVY_MT
      into v_ROUTE_CODE, v_DIRECT_CODE, v_TRACK, v_SRVY_YEAR, v_SRVY_MT
      FROM TN_MUMM_SCTN_SRVY_DTA
     WHERE SRVY_NO = p_SRVY_NO
     GROUP BY ROUTE_CODE, DIRECT_CODE, TRACK, SRVY_YEAR, SRVY_MT;
   
    DBMS_OUTPUT.PUT_LINE('v_UNIT = ' || v_UNIT);
    
    INSERT INTO TL_SRVY_DTA_LOG (
        PROCESS_LOG_NO /* 조사_자료_로그.처리_로그_번호 */
      , LOG_MSSAGE /* 조사_자료_로그.로그_메세지 */
      , CRTR_NO /* 조사_자료_로그.생성자_번호 */
      , BEGIN_DT /* 조사_자료_로그.시작_일시 */
      , SRVY_NO /* 조사_자료_로그.조사_번호 */
      , PROCESS_SE /* 조사_자료_로그.처리_구분 */
      , PROCESS_STTUS /* 조사_자료_로그.처리_상태 */
      , END_DT /* 조사_자료_로그.종료_일시 */
    )
    VALUES (
        SEQ_TL_SRVY_DTA_LOG.NEXTVAL
      , '조사 자료 공간 보정 시작' /* 조사_자료_로그.로그_메세지 */
      , p_USER_NO /* 조사_자료_로그.생성자_번호 */
      , SYSDATE /* 조사_자료_로그.시작_일시 */
      , p_SRVY_NO /* 조사_자료_로그.조사_번호 */
      , 'PCSE0007' /* 조사_자료_로그.처리_구분 */
      , 'PCST0001' /* 조사_자료_로그.처리_상태 */
      , NULL /* 조사_자료_로그.종료_일시 */
    );
    
	o_PROCMSG :='CELL_10 테이블의 SRID 조회';  
	/* CELL_10 테이블의 SRID 조회 */
	SELECT SRID
      INTO v_SRID 
	  FROM USER_SDO_GEOM_METADATA
	 WHERE TABLE_NAME = 'CELL_10'
    AND COLUMN_NAME = 'G2_SPATIAL';
    
    DBMS_OUTPUT.PUT_LINE('o_PROCMSG = ' || o_PROCMSG);
       
  select nvl(min(STRTPT),0) - 1000 , nvl(max(ENDPT),0) + 1000 
  into p_STRTPT , p_ENDPT    
    from TN_MUMM_SCTN_SRVY_DTA tnmummsctnsrvydta     
    WHERE
     1 = 1
   AND tnmummsctnsrvydta.UPDT_AT = 'N'
   AND tnmummsctnsrvydta.USE_AT = 'Y'
   AND tnmummsctnsrvydta.DELETE_AT = 'N'
   AND tnmummsctnsrvydta.SRVY_NO = p_SRVY_NO
   AND tnmummsctnsrvydta.TRACE1_LO <> 0             
   AND tnmummsctnsrvydta.TRACE1_LO IS NOT NULL
   AND tnmummsctnsrvydta.TRACE1_LA <> 0             
   AND tnmummsctnsrvydta.TRACE1_LA IS NOT NULL;
    
    DBMS_OUTPUT.PUT_LINE('p_SRVY_NO = ' || p_SRVY_NO);
    DBMS_OUTPUT.PUT_LINE('p_STRTPT = ' || p_STRTPT);
    DBMS_OUTPUT.PUT_LINE('p_ENDPT = ' || p_ENDPT);
    
    --음수 처리               
    p_STRTPT := GREATEST(p_STRTPT  , 0 ,0);  
     --p_STRTPT         	TN_MUMM_SCTN_SRVY_DTA.STRTPT%TYPE;
   --p_ENDPT       		TN_MUMM_SCTN_SRVY_DTA.ENDPT%TYPE;
    
    FOR v_ROW IN t_CURSOR( p_SRVY_NO, p_STRTPT, p_ENDPT )
    LOOP
      BEGIN
       o_PROCMSG :='경위도 좌표 변환';  
       v_STRTPT := v_ROW.STRTPT;
       v_ENDPT := v_ROW.ENDPT;
		
	  IF v_STRTPT > -1 AND v_ENDPT > -1
      THEN
         UPDATE TN_MUMM_SCTN_SRVY_DTA 			/* 최소_구간_조사_자료 */
            SET STRTPT = v_STRTPT, 				/* 최소_구간_조사_자료.시점 */
                ENDPT = v_ENDPT,					/* 최소_구간_조사_자료.종점 */
                UPDT_DT = SYSDATE,
                MSG_CODE = 'U',                 /*test*/
                MSG_TEST = 'UPDATE : 시종점을 변경하였습니다.'
          WHERE DTA_NO = v_ROW.DTA_NO  /* 조사_자료_현황.자료_번호 */
            AND USE_AT = 'Y'
            AND DELETE_AT = 'N';

         UPDATE TN_SRVY_DTA_STTUS 				/* 조사_자료_현황 */
            SET STRTPT = v_STRTPT, 				/* 조사_자료_현황.시점 */
                ENDPT = v_ENDPT,					/* 최소_구간_조사_자료.종점 */
                UPDT_DT = SYSDATE					/* 조사_자료_현황.종점 */
          WHERE DTA_NO = v_ROW.DTA_NO 			/* 조사_자료_현황.자료_번호 */
            AND USE_AT = 'Y'
            AND DELETE_AT = 'N';
      END IF;
     END; 
   END LOOP;
  
  o_PROCMSG :='시종점이 중복값 제거'; 
  DBMS_OUTPUT.PUT_LINE('o_PROCMSG = ' || o_PROCMSG);
   /* 시종점이 중복되는 행은 ROWID가 큰값을 제거 */ 
   /*
   DELETE FROM TN_MUMM_SCTN_SRVY_DTA  최소_구간_조사_자료 
      WHERE DTA_NO IN (SELECT DTA_NO
						 FROM (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID );
   
   DELETE FROM TN_SRVY_DTA_STTUS 조사_자료_현황
    WHERE DTA_NO IN (SELECT DTA_NO
						 FROM (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID ); */
                       
                       
  UPDATE TN_MUMM_SCTN_SRVY_DTA /* 최소_구간_조사_자료 */
	  SET USE_AT = 'N'
		, DELETE_AT = 'Y'
    , MSG_CODE = 'D',                 /*test*/
    MSG_TEST = 'DELETE : 시종점이 중복되어 삭제합니다.'
      WHERE DTA_NO IN (SELECT DTA_NO
						 FROM (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T 
							   WHERE USE_AT = 'Y'
								 AND DELETE_AT = 'N'
                                 --AND T.UPDT_AT = 'N'
                                 AND T.ROUTE_CODE = v_ROUTE_CODE
                                 AND T.DIRECT_CODE = v_DIRECT_CODE
                                 AND T.TRACK = v_TRACK
                                 AND T.SRVY_YEAR = v_SRVY_YEAR
                                 AND T.SRVY_MT = v_SRVY_MT
                 )
					   WHERE CNT > 1 AND RID < MAX_RID );
   
   UPDATE TN_SRVY_DTA_STTUS /* 조사_자료_현황 */
	  SET USE_AT = 'N'
		, DELETE_AT = 'Y'
    WHERE DTA_NO IN (SELECT T.DTA_NO
						 FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE T.USE_AT = 'N'
								 AND T.DELETE_AT = 'Y'
								 --AND T.UPDT_AT = 'N'
                 AND T.ROUTE_CODE = v_ROUTE_CODE
                 AND T.DIRECT_CODE = v_DIRECT_CODE
                 AND T.TRACK = v_TRACK 
                 AND T.SRVY_YEAR = v_SRVY_YEAR
                 AND T.SRVY_MT = v_SRVY_MT
                 );
             
             /*
             (SELECT T.*
									, T.ROWID RID
									, MAX (T.ROWID) OVER (PARTITION BY T.ROUTE_CODE, T.DIRECT_CODE, T.TRACK, T.STRTPT) MAX_RID
									, COUNT (*) OVER (PARTITION BY ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT) CNT
								FROM TN_MUMM_SCTN_SRVY_DTA T
							   WHERE UPDT_AT = 'N'
								 AND USE_AT = 'Y'
								 AND DELETE_AT = 'N')
					   WHERE CNT > 1 AND RID < MAX_RID )
             */

	o_PROCMSG :='없는 조사자료 값을 시종점 다음 값으로 입력'; 	
    DBMS_OUTPUT.PUT_LINE('o_PROCMSG = ' || o_PROCMSG);
	/* 없는 조사자료 값은 시종점 다음값으로 강제 입력 */
	FOR v_ROW_GRP IN ( SELECT ROUTE_CODE			/* 최소_구간_조사_자료.노선_코드 */
                          , DIRECT_CODE			/* 최소_구간_조사_자료.행선_코드 */
                          , TRACK				/* 최소_구간_조사_자료.차로 */
                          , SRVY_YEAR
                          , SRVY_MT
                          , MIN(STRTPT) STRTPT		/* 최소_구간_조사_자료.시점 */
                          , MAX(ENDPT) ENDPT		/* 최소_구간_조사_자료.종점 */
                          --, DTA_NO			/* 최소_구간_조사_자료.자료_번호 */
                        FROM TN_MUMM_SCTN_SRVY_DTA /* 최소_구간_조사_자료 */
                       WHERE UPDT_AT = 'N'
                         AND SRVY_NO = p_SRVY_NO
                         AND USE_AT = 'Y'
                         AND DELETE_AT = 'N'
                       GROUP BY ROUTE_CODE
                              , DIRECT_CODE
                              , TRACK
                              , SRVY_YEAR
                              , SRVY_MT
                             -- , DTA_NO
                   )
    LOOP
    v_INDEX := 0 ;
        FOR v_ROW IN (SELECT   *
                        FROM TN_MUMM_SCTN_SRVY_DTA	/* 최소_구간_조사_자료 */
                        WHERE USE_AT = 'Y'
                          AND DELETE_AT = 'N'
                          --AND UPDT_AT = 'N'
                          AND ROUTE_CODE = v_ROW_GRP.ROUTE_CODE 
                          AND DIRECT_CODE = v_ROW_GRP.DIRECT_CODE 
                          AND TRACK = v_ROW_GRP.TRACK 
                          AND SRVY_YEAR = v_ROW_GRP.SRVY_YEAR
                          AND SRVY_MT = v_ROW_GRP.SRVY_MT
                          AND SRVY_NO = p_SRVY_NO
                         -- AND DTA_NO = v_ROW_GRP.DTA_NO
                        ORDER BY STRTPT, ENDPT)
        LOOP
          
            IF v_ROW.STRTPT <> v_ROW_GRP.STRTPT + v_INDEX * v_UNIT 
            THEN
                v_DTA_NO := SEQ_TN_MUMM_SCTN_SRVY_DTA.NEXTVAL;
               -- v_DTA_STTUS_NO := SEQ_TN_SRVY_DTA_STTUS.NEXTVAL;
                DBMS_OUTPUT.PUT_LINE( v_DTA_NO );
                INSERT INTO TN_SRVY_DTA_STTUS   	/* 조사_자료_현황 */
					(  DTA_STTUS_NO					/* 조사_자료_현황.자료_현황_번호 */
					 , SRVY_YEAR					/* 조사_자료_현황.조사_년도 */
					 , ROUTE_CODE					/* 조사_자료_현황.노선_코드 */
					 , DIRECT_CODE					/* 조사_자료_현황.행선_코드 */
					 , TRACK						/* 조사_자료_현황.차로  */
					 , STRTPT 						/* 조사_자료_현황.시점 */
					 , ENDPT							/* 조사_자료_현황.종점 */
					 , SRVY_KND						/* 조사_자료_현황.조사_종류 */
					 , SRVY_MT						/* 조사_자료_현황.조사_월 */
					 , EXCEL_DTA_SEQ				/* 조사_자료_현황.엑셀_자료_SEQ */
					 , SRVY_NO						/* 조사_자료_현황.조사_번호 */
					 , DTA_NO						/* 조사_자료_현황.자료_번호 */
					 , SM_PROCESS_AT			/* 조사_자료_현황.집계_구간_산출_여부 */
					 , PRDTMDL_PROCESS_AT		/* 조사_자료_현황.예측_모델_적용_여부 */
					 , DELETE_AT					/* 조사_자료_현황.삭제_여부 */
					 , USE_AT						/* 조사_자료_현황.사용_여부 */
					 , CRTR_NO						/* 조사_자료_현황.생성자_번호 */
					 , CREAT_DT						/* 조사_자료_현황.생성_일자 */
					 , UPDUSR_NO					/* 조사_자료_현황.수정자_번호 */
					 , UPDT_DT						/* 조사_자료_현황.수정_일자 */
					)
					SELECT SEQ_TN_SRVY_DTA_STTUS.NEXTVAL			
						 , SRVY_YEAR
						 , ROUTE_CODE
						 , DIRECT_CODE
						 , TRACK
						 , v_ROW_GRP.STRTPT + v_INDEX * v_UNIT
						 , v_ROW_GRP.STRTPT + (v_INDEX + 1) * v_UNIT
						 , SRVY_KND
						 , SRVY_MT
						 , EXCEL_DTA_SEQ
						 , SRVY_NO
						 , v_DTA_NO	
						 , SM_PROCESS_AT
						 , PRDTMDL_PROCESS_AT
						 , 'N'	
						 , 'Y'	
						 , P_USER_NO	
						 , SYSDATE
						 , P_USER_NO
						 , SYSDATE
					 FROM TN_SRVY_DTA_STTUS
					WHERE DTA_NO = v_ROW.DTA_NO
					  AND USE_AT = 'Y'
					  AND DELETE_AT = 'N';
                
          INSERT INTO TN_MUMM_SCTN_SRVY_DTA			/* 최소_구간_조사_자료 */
					(     DTA_NO							/* 최소_구간_조사_자료.자료_번호 */
						, SRVY_NO							/* 최소_구간_조사_자료.조사_번호 */
						, SRVY_YEAR							/* 최소_구간_조사_자료.조사_년도 */
						, ROUTE_CODE						/* 최소_구간_조사_자료.노선_코드 */
						, DIRECT_CODE						/* 최소_구간_조사_자료.행선_코드 */
						, TRACK							/* 최소_구간_조사_자료.차로 */
						, STRTPT								/* 최소_구간_조사_자료.시점 */
						, ENDPT								/* 최소_구간_조사_자료.종점 */
						, IRI_VAL							/* 최소_구간_조사_자료.종단평탄성_값 */
						, RD_VAL							/* 최소_구간_조사_자료.소성변형_값 */
						, VRTCAL_CR							/* 최소_구간_조사_자료.종방향_균열 */
						, HRZNTAL_CR						/* 최소_구간_조사_자료.횡방향_균열 */
						, CNSTRCT_JOINT_CR					/* 최소_구간_조사_자료.시공_줄눈_군열 */
						, TRTS_BAC_CR						/* 최소_구간_조사_자료.거북등_균열 */
						, PTCHG_CR							/* 최소_구간_조사_자료.패칭_균열 */
						, BLOCK_CR                          /* 최소_구간_조사_자료.블럭_균열 */
                        , CR_LT /* 최소_구간_조사_자료.균열_길이 */
                        , CR_WID /* 최소_구간_조사_자료.균열_폭 */
                        , AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                        , AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                        , AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                        , BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                        , BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                        , BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                        , LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                        , LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                        , LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                        , TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                        , TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                        , TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                        , AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                        , BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                        , LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                        , TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                        , PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                        , POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                        , RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                        , RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                        , RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                        , XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                        , RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                        , SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                        , CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                        , CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                        , RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                        , NHPCI /* 최소_구간_조사_자료.NHPCI */
                        , GPCI /* 최소_구간_조사_자료.GPCI */
                        , SPI /* 최소_구간_조사_자료.SPI */
						, PC_GRAD							/* 최소_구간_조사_자료.포장상태등급 */
						, CNTL_DFECT						/* 최소_구간_조사_자료.지배_결함 */
                        , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                        , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                        , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
						, SRVY_KND							/* 최소_구간_조사_자료.조사_종류 */
						, SRVY_MT							/* 최소_구간_조사_자료.조사_월 */
						, MEMO								/* 최소_구간_조사_자료.메모 */
						, SRVY_DE							/* 최소_구간_조사_자료.조사_일자 */
						, TRACE1_LA							/* 최소_구간_조사_자료.TRACE1_LA */
						, TRACE1_LO							/* 최소_구간_조사_자료.TRACE1_LO */
						, TRACE2_LA							/* 최소_구간_조사_자료.TRACE2_LA */
						, TRACE2_LO							/* 최소_구간_조사_자료.TRACE2_LO */
						, SRVY_NM							/* 최소_구간_조사_자료.조사_명 */
						, EXMNR_NM							/* 최소_구간_조사_자료.조사자_명 */
						, SCTN_STRTPT_DC						/* 최소_구간_조사_자료.구간_시점_설명 */
						, SCTN_ENDPT_DC						/* 최소_구간_조사_자료.구간_종점_설명 */
						, ROAD_NM							/* 최소_구간_조사_자료.도로_명 */
						, FRNT_IMG_FILE_NM					/* 최소_구간_조사_자료.전방_이미지_파일_명 */
						, RDSRFC_IMG_FILE_NM_1				/* 최소_구간_조사_자료.노면_이미지_파일_명_1 */
						, RDSRFC_IMG_FILE_NM_2				/* 최소_구간_조사_자료.노면_이미지_파일_명_2 */
						, EXCEL_DTA_SEQ						/* 최소_구간_조사_자료.엑셀_자료_SEQ */
						, FRMULA_NO							/* 최소_구간_조사_자료.수식_번호 */
						, POTHOLE_CR						/* 최소_구간_조사_자료.포트홀_군열 */
						, RI_IDX							/* 최소_구간_조사_자료.평탄성_지수 */
						, UPDT_AT							/* 최소_구간_조사_자료.수정_여부 */
						, DELETE_AT							/* 최소_구간_조사_자료.삭제_여부 */
						, USE_AT							/* 최소_구간_조사_자료.사용_여부 */
						, CRTR_NO							/* 최소_구간_조사_자료.생성자_번호 */
						, CREAT_DT							/* 최소_구간_조사_자료.생성_일시 */
						, UPDUSR_NO							/* 최소_구간_조사_자료.수정자_번호 */
						, UPDT_DT							/* 최소_구간_조사_자료.수정_일시 */
                        , MSG_CODE                 /*test*/
                        , MSG_TEST 
                        , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
					)
					SELECT v_DTA_NO
                        , SRVY_NO
                        , SRVY_YEAR
                        , ROUTE_CODE
                        , DIRECT_CODE
                        , TRACK
                        , v_ROW_GRP.STRTPT + v_INDEX * v_UNIT
						, v_ROW_GRP.STRTPT + (v_INDEX + 1) * v_UNIT
                        , IRI_VAL
                        , RD_VAL
                        , VRTCAL_CR
                        , HRZNTAL_CR
                        , CNSTRCT_JOINT_CR
                        , TRTS_BAC_CR
                        , PTCHG_CR
                        , BLOCK_CR /* 최소_구간_조사_자료.블럭_균열 */
                        , CR_LT /* 최소_구간_조사_자료.균열_길이 */
                        , CR_WID /* 최소_구간_조사_자료.균열_폭 */
                        , AC_LOW /* 최소_구간_조사_자료.거북등균열_LOW */
                        , AC_MED /* 최소_구간_조사_자료.거북등균열_MED */
                        , AC_HI /* 최소_구간_조사_자료.거북등균열_HI */
                        , BLOCK_CR_LOW /* 최소_구간_조사_자료.블럭균열_LOW */
                        , BLOCK_CR_MED /* 최소_구간_조사_자료.블럭균열_MED */
                        , BLOCK_CR_HI /* 최소_구간_조사_자료.블럭균열_HI */
                        , LC_LOW /* 최소_구간_조사_자료.종방향균열_LOW */
                        , LC_MED /* 최소_구간_조사_자료.종방향균열_MED */
                        , LC_HI /* 최소_구간_조사_자료.종방향균열_HI */
                        , TC_LOW /* 최소_구간_조사_자료.횡방향균열_LOW */
                        , TC_MED /* 최소_구간_조사_자료.횡방향균열_MED */
                        , TC_HI /* 최소_구간_조사_자료.횡방향균열_HI */
                        , AC_IDX /* 최소_구간_조사_자료.거북등균열_지수 */
                        , BC_IDX /* 최소_구간_조사_자료.블럭균열_지수 */
                        , LC_IDX /* 최소_구간_조사_자료.종방향균열_지수 */
                        , TC_IDX /* 최소_구간_조사_자료.횡방향균열_지수 */
                        , PTCHG_IDX /* 최소_구간_조사_자료.패칭_지수 */
                        , POTHOLE_IDX /* 최소_구간_조사_자료.포트홀_지수 */
                        , RD_LOW /* 최소_구간_조사_자료.소성변형_LOW */
                        , RD_MED /* 최소_구간_조사_자료.소성변형_MED */
                        , RD_HI /* 최소_구간_조사_자료.소성변형_HI */
                        , XCR /* 최소_구간_조사_자료.NHPCI_균열 */
                        , RCI /* 최소_구간_조사_자료.표면_조도_지수 */
                        , SCR /* 최소_구간_조사_자료.표면_상태_지수 */
                        , CR_VAL /* 최소_구간_조사_자료.표면손상값 */
                        , CR_IDX /* 최소_구간_조사_자료.표면손상지수 */
                        , RD_IDX /* 최소_구간_조사_자료.소성변형지수 */
                        , NHPCI /* 최소_구간_조사_자료.NHPCI */
                        , GPCI /* 최소_구간_조사_자료.GPCI */
                        , SPI /* 최소_구간_조사_자료.SPI */
                        , PC_GRAD
                        , CNTL_DFECT
                        , DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                        , DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                        , DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
                        , SRVY_KND
                        , SRVY_MT
                        , MEMO
                        , SRVY_DE
                        , TRACE1_LA
                        , TRACE1_LO
                        , TRACE2_LA
                        , TRACE2_LO
                        , SRVY_NM
                        , EXMNR_NM
                        , SCTN_STRTPT_DC
                        , SCTN_ENDPT_DC
                        , ROAD_NM
                        , FRNT_IMG_FILE_NM
                        , RDSRFC_IMG_FILE_NM_1
                        , RDSRFC_IMG_FILE_NM_2
                        , EXCEL_DTA_SEQ
                        , FRMULA_NO
                        , POTHOLE_CR
                        , RI_IDX
                        , 'N'
                        , 'N'
                        , 'Y'
                        , P_USER_NO
                        , SYSDATE
                        , P_USER_NO
                        , SYSDATE
                        , 'I'                 /*test*/
                        , 'INSERT : 조사자료 값이 없어 시종점 다음 값으로 입력하였습니다.'
                        , LAST_DTA_AT /* 최소_구간_조사_자료.최종_데이터_여부 */
					 FROM TN_MUMM_SCTN_SRVY_DTA
					WHERE DTA_NO = v_ROW.DTA_NO
					  AND USE_AT = 'Y'
					  AND DELETE_AT = 'N';
            
                v_INDEX := v_INDEX + 1;
            END IF;
            v_INDEX := v_INDEX + 1;
        END LOOP;
    END LOOP;
    
    o_PROCMSG :='조사 자료 공간 보정 처리 여부 등록'; 
	
    UPDATE TN_MUMM_SCTN_SRVY_DTA
       SET UPDT_AT = 'Y'
     WHERE UPDT_AT = 'N'
	   AND USE_AT = 'Y'
	   AND DELETE_AT = 'N'
     AND SRVY_NO = p_SRVY_NO;
    
    /* 조사_자료_엑셀.마스터 정보 수정 */
    UPDATE TN_SRVY_DTA_EXCEL SET
          GPS_CORTN_PROCESS_AT = 'Y' /* 조사_자료_엑셀.GPS_보정_처리_여부 */
        , UPDUSR_NO = p_USER_NO /* 조사_자료_엑셀.수정자_번호 */
        , UPDT_DT = SYSDATE /* 조사_자료_엑셀.수정_일시 */
        WHERE 1 = 1
        AND SRVY_NO = p_SRVY_NO /* 조사_자료_엑셀.조사_번호 */
        AND DELETE_AT = 'N' /* 조사_자료_엑셀.삭제_여부 */
        AND USE_AT = 'Y' /* 조사_자료_엑셀.사용_여부 */
        ;   
    
    
    o_PROCMSG :='조사 자료 공간 보정 처리 완료';   
    o_PROCCODE :='true';
            
    /* 조사_자료_로그 수정 */
    UPDATE TL_SRVY_DTA_LOG SET
    LOG_MSSAGE = '조사 자료 공간 보정 완료' /* 조사_자료_로그.로그_메세지 */
    , PROCESS_STTUS = 'PCST0002' /* 조사_자료_로그.처리_상태 */
    , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
    WHERE PROCESS_SE = 'PCSE0007'
	  AND PROCESS_STTUS = 'PCST0001'
	  AND END_DT is null
    AND SRVY_NO = p_SRVY_NO;
  
EXCEPTION
   WHEN OTHERS
   THEN
		o_PROCMSG := SUBSTR(SQLERRM,1,1000);
        o_PROCCODE :='false';
		
        /* 조사_자료_로그 수정 */
		UPDATE TL_SRVY_DTA_LOG SET
        LOG_MSSAGE = '조사 자료 공간 보정 오류:' || o_PROCMSG /* 조사_자료_로그.로그_메세지 */
        , PROCESS_STTUS = 'PCST0003' /* 조사_자료_로그.처리_상태 */
        , END_DT = SYSDATE /* 조사_자료_로그.종료_일시 */
		WHERE PROCESS_SE = 'PCSE0007'
		  AND END_DT is null
		  AND SRVY_NO = p_SRVY_NO;	   
      RAISE;
END PRC_SRVY_DTA_SYS_REFLCT;