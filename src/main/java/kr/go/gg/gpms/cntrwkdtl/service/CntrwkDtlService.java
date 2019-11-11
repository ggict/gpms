package kr.go.gg.gpms.cntrwkdtl.service;

import java.util.List;

import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;

/**
 * 공사상세정보
 * TN_CNTRWK_DTL
 *
 * @Class Name : CntrwkDtlService.java
 * @Description : CntrwkDtl Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CntrwkDtlService {

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 등록한다.
	 * @param cntrwkDtlVO - 등록할 정보가 담긴 CntrwkDtlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 수정한다.
	 * @param cntrwkDtlVO - 수정할 정보가 담긴 CntrwkDtlVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	
	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrwkDtlByCntrwkId(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return 조회한 TN_CNTRWK_DTL
	 * @exception Exception
	 */
	CntrwkDtlVO selectCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkDtlVO
	 * @return TN_CNTRWK_DTL 목록
	 * @exception Exception
	 */
	List<CntrwkDtlVO> selectCntrwkDtlList(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkDtlVO
	 * @return TN_CNTRWK_DTL 총 갯수
	 * @exception
	 */
	int selectCntrwkDtlListTotalCount(CntrwkDtlVO cntrwkDtlVO);
	
	/**
	 * 세부공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List selectCntrwkDtlListExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception;

	/**
	 * 세부공사정보(TN_CNTRWK) 명칭 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List<CntrwkDtlVO> selectCntrwkDtlNmListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception ;
	
	/**
	 * 세부공사정보(TN_CNTRWK) 세부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List<CntrwkDtlVO> selectCntrwkDtlListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 List selectCntrwkStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	 
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 List selectCntrwkMthdStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	 
 	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 List selectCntrwkDeptStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	 
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 엑셀 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	List cntrwkRoutCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 List cntrwkDeptCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 엑셀목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 List cntrwkMthdCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception;
	 
	/**
	 * 공사정보(TN_CNTRWK_DTL)엑셀을 DB에 저장한다.
	 * @param cntrwkDtlVO - 저장할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	 String excelDBUpload(CntrwkDtlVO cntrwkDtlVO, String filePathNm, String userNo) throws Exception;
}

