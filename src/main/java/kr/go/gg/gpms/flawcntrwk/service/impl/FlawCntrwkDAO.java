


package kr.go.gg.gpms.flawcntrwk.service.impl;

import java.util.List;

import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 하자_보수_공사
 *
 * @Class Name : FlawCntrwkDAO.java
 * @Description : FlawCntrwk DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("flawCntrwkDAO")
public class FlawCntrwkDAO extends BaseDAO {

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 등록한다.
	 * @param flawCntrwkVO - 등록할 정보가 담긴 FlawCntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return (String) insert("flawCntrwkDAO.insertFlawCntrwk", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 수정한다.
	 * @param flawCntrwkVO - 수정할 정보가 담긴 FlawCntrwkVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return update("flawCntrwkDAO.updateFlawCntrwk", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return delete("flawCntrwkDAO.deleteFlawCntrwk", flawCntrwkVO);
	}
	
	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteFlawCntrwkByCntrwkDtlID(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return delete("flawCntrwkDAO.deleteFlawCntrwkByCntrwkDtlID", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return 조회한 TN_FLAW_CNTRWK
	 * @exception Exception
	 */
	public FlawCntrwkVO selectFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return (FlawCntrwkVO) select("flawCntrwkDAO.selectFlawCntrwk", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return TN_FLAW_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FlawCntrwkVO> selectFlawCntrwkList(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return (List<FlawCntrwkVO>)list("flawCntrwkDAO.selectFlawCntrwkList", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 총 갯수를 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return TN_FLAW_CNTRWK 총 갯수
	 * @exception
	 */
	public int selectFlawCntrwkListTotalCount(FlawCntrwkVO flawCntrwkVO) {
		return (Integer) select("flawCntrwkDAO.selectFlawCntrwkListTotalCount", flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사 엑셀목록을 조회한다.
	 */
	@SuppressWarnings("unchecked")
	public List selectFlawCntrwkListExcel(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return (List) list("flawCntrwkDAO.selectFlawCntrwkListExcel", flawCntrwkVO);
	}
}
