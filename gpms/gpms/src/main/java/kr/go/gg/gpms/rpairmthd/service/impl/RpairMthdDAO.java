


package kr.go.gg.gpms.rpairmthd.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

/**
 * 보수공법코드
 *
 * @Class Name : RpairMthdDAO.java
 * @Description : RpairMthd DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairMthdDAO")
public class RpairMthdDAO extends BaseDAO {

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 등록한다.
	 * @param rpairMthdVO - 등록할 정보가 담긴 RpairMthdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return (String) insert("rpairMthdDAO.insertRpairMthd", rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 수정한다.
	 * @param rpairMthdVO - 수정할 정보가 담긴 RpairMthdVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return update("rpairMthdDAO.updateRpairMthd", rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 삭제한다.
	 * @param rpairMthdVO - 삭제할 정보가 담긴 RpairMthdVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return delete("rpairMthdDAO.deleteRpairMthd", rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return 조회한 TC_RPAIR_MTHD
	 * @exception Exception
	 */
	public RpairMthdVO selectRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return (RpairMthdVO) select("rpairMthdDAO.selectRpairMthd", rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return TC_RPAIR_MTHD 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RpairMthdVO> selectRpairMthdList(RpairMthdVO rpairMthdVO) throws Exception {
		return (List<RpairMthdVO>)list("rpairMthdDAO.selectRpairMthdList", rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 총 갯수를 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return TC_RPAIR_MTHD 총 갯수
	 * @exception
	 */
	public int selectRpairMthdListTotalCount(RpairMthdVO rpairMthdVO) {
		return (Integer) select("rpairMthdDAO.selectRpairMthdListTotalCount", rpairMthdVO);
	}

}
