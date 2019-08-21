


package kr.go.gg.gpms.userconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

/**
 * 사용자접속로그
 *
 * @Class Name : UserConnectDAO.java
 * @Description : UserConnect DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-10-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("userConnectDAO")
public class UserConnectDAO extends BaseDAO {

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 등록한다.
	 * @param userConnectVO - 등록할 정보가 담긴 UserConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserConnect(UserConnectVO userConnectVO) throws Exception {
		return (String) insert("userConnectDAO.insertUserConnect", userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 수정한다.
	 * @param userConnectVO - 수정할 정보가 담긴 UserConnectVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateUserConnect(UserConnectVO userConnectVO) throws Exception {
		return update("userConnectDAO.updateUserConnect", userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 삭제한다.
	 * @param userConnectVO - 삭제할 정보가 담긴 UserConnectVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteUserConnect(UserConnectVO userConnectVO) throws Exception {
		return delete("userConnectDAO.deleteUserConnect", userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return 조회한 TL_USER_CONNECT
	 * @exception Exception
	 */
	public UserConnectVO selectUserConnect(UserConnectVO userConnectVO) throws Exception {
		return (UserConnectVO) select("userConnectDAO.selectUserConnect", userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 목록을 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return TL_USER_CONNECT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UserConnectVO> selectUserConnectList(UserConnectVO userConnectVO) throws Exception {
		return (List<UserConnectVO>)list("userConnectDAO.selectUserConnectList", userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 총 갯수를 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return TL_USER_CONNECT 총 갯수
	 * @exception
	 */
	public int selectUserConnectListTotalCount(UserConnectVO userConnectVO) {
		return (Integer) select("userConnectDAO.selectUserConnectListTotalCount", userConnectVO);
	}

}
