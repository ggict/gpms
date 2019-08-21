


package kr.go.gg.gpms.flaw.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.flaw.service.model.FlawVO;

/**
 * 공사하자정보
 *
 * @Class Name : FlawDAO.java
 * @Description : Flaw DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("flawDAO")
public class FlawDAO extends BaseDAO {

	/**
	 * 공사하자정보(TN_FLAW)을 등록한다.
	 * @param flawVO - 등록할 정보가 담긴 FlawVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertFlaw(FlawVO flawVO) throws Exception {
		return (String) insert("flawDAO.insertFlaw", flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 수정한다.
	 * @param flawVO - 수정할 정보가 담긴 FlawVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateFlaw(FlawVO flawVO) throws Exception {
		return update("flawDAO.updateFlaw", flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 삭제한다.
	 * @param flawVO - 삭제할 정보가 담긴 FlawVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteFlaw(FlawVO flawVO) throws Exception {
		return delete("flawDAO.deleteFlaw", flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	public FlawVO selectFlaw(FlawVO flawVO) throws Exception {
		return (FlawVO) select("flawDAO.selectFlaw", flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return TN_FLAW 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FlawVO> selectFlawList(FlawVO flawVO) throws Exception {
		return (List<FlawVO>)list("flawDAO.selectFlawList", flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW) 총 갯수를 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return TN_FLAW 총 갯수
	 * @exception
	 */
	public int selectFlawListTotalCount(FlawVO flawVO) {
		return (Integer) select("flawDAO.selectFlawListTotalCount", flawVO);
	}
	
	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	public FlawVO selectFlawByCntrwkId(FlawVO flawVO) throws Exception {
		return (FlawVO) select("flawDAO.selectFlawByCntrwkId", flawVO);
	}

}
