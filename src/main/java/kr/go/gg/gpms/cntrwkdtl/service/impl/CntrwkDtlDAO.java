


package kr.go.gg.gpms.cntrwkdtl.service.impl;

import java.util.List;

import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 공사상세정보
 *
 * @Class Name : CntrwkDtlDAO.java
 * @Description : CntrwkDtl DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cntrwkDtlDAO")
public class CntrwkDtlDAO extends BaseDAO {

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 등록한다.
	 * @param cntrwkDtlVO - 등록할 정보가 담긴 CntrwkDtlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (String) insert("cntrwkDtlDAO.insertCntrwkDtl", cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 수정한다.
	 * @param cntrwkDtlVO - 수정할 정보가 담긴 CntrwkDtlVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return update("cntrwkDtlDAO.updateCntrwkDtl", cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return delete("cntrwkDtlDAO.deleteCntrwkDtl", cntrwkDtlVO);
	}
	
	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCntrwkDtlByCntrwkId(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return delete("cntrwkDtlDAO.deleteCntrwkDtlByCntrwkId", cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return 조회한 TN_CNTRWK_DTL
	 * @exception Exception
	 */
	public CntrwkDtlVO selectCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (CntrwkDtlVO) select("cntrwkDtlDAO.selectCntrwkDtl", cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return TN_CNTRWK_DTL 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkDtlList(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.selectCntrwkDtlList", cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 총 갯수를 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return TN_CNTRWK_DTL 총 갯수
	 * @exception
	 */
	public int selectCntrwkDtlListTotalCount(CntrwkDtlVO cntrwkDtlVO) {
		return (Integer) select("cntrwkDtlDAO.selectCntrwkDtlListTotalCount", cntrwkDtlVO);
	}

	/**
	 * 세부공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectCntrwkDtlListExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.selectCntrwkDtlListExcel", cntrwkDtlVO);
	}
	
	/**
	 * 세부공사정보(TN_CNTRWK) 명칭 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkDtlNmListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.selectCntrwkDtlNmListByCntrwkID", cntrwkDtlVO);
	}
	
	/**
	 * 세부공사정보(TN_CNTRWK) 세부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkDtlListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.selectCntrwkDtlListByCntrwkID", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.selectCntrwkStatsResult", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkMthdStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.selectCntrwkMthdStatsResult", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> selectCntrwkDeptStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.selectCntrwkDeptStatsResult", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 엑셀 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List cntrwkRoutCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.cntrwkRoutCntStatsExcel", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> cntrwkDeptCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.cntrwkDeptCntStatsExcel", cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 엑셀목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkDtlVO> cntrwkMthdCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return (List)list("cntrwkDtlDAO.cntrwkMthdCntStatsExcel", cntrwkDtlVO);
	}
	
	public List<CntrwkDtlVO> selectCntrwkRoutLenNewStats(CntrwkDtlVO cntrwkDtlVO) {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.selectCntrwkRoutLenNewStats", cntrwkDtlVO);
	}
	
	public List<CntrwkDtlVO> cntrwkRoutLenNewStatsExcel(CntrwkDtlVO cntrwkDtlVO) {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.cntrwkRoutLenNewStatsExcel", cntrwkDtlVO);
	}
	
	public List<CntrwkDtlVO> selectCntrwkDeptLenNewStats(CntrwkDtlVO cntrwkDtlVO) {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.selectCntrwkDeptLenNewStats", cntrwkDtlVO);
	}
	
	public List<CntrwkDtlVO> cntrwkDeptLenNewStatsExcel(CntrwkDtlVO cntrwkDtlVO) {
		return (List<CntrwkDtlVO>)list("cntrwkDtlDAO.cntrwkDeptLenNewStatsExcel", cntrwkDtlVO);
	}
}
