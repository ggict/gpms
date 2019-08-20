


package kr.go.gg.gpms.cntrcomp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cntrcomp.service.model.CntrCompVO;

/**
 * 공사업체정보
 *
 * @Class Name : CntrCompDAO.java
 * @Description : CntrComp DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cntrCompDAO")
public class CntrCompDAO extends BaseDAO {

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 등록한다.
	 * @param cntrCompVO - 등록할 정보가 담긴 CntrCompVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return (String) insert("cntrCompDAO.insertCntrComp", cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 수정한다.
	 * @param cntrCompVO - 수정할 정보가 담긴 CntrCompVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return update("cntrCompDAO.updateCntrComp", cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 삭제한다.
	 * @param cntrCompVO - 삭제할 정보가 담긴 CntrCompVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return delete("cntrCompDAO.deleteCntrComp", cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return 조회한 TN_CNTR_COMP
	 * @exception Exception
	 */
	public CntrCompVO selectCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return (CntrCompVO) select("cntrCompDAO.selectCntrComp", cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return TN_CNTR_COMP 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrCompVO> selectCntrCompList(CntrCompVO cntrCompVO) throws Exception {
		return (List<CntrCompVO>)list("cntrCompDAO.selectCntrCompList", cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP) 총 갯수를 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return TN_CNTR_COMP 총 갯수
	 * @exception
	 */
	public int selectCntrCompListTotalCount(CntrCompVO cntrCompVO) {
		return (Integer) select("cntrCompDAO.selectCntrCompListTotalCount", cntrCompVO);
	}

}
