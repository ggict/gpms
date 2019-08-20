


package kr.go.gg.gpms.userauth.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;

/**
 * 사용자권한
 *
 * @Class Name : UserAuthDAO.java
 * @Description : UserAuth DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("userAuthDAO")
public class UserAuthDAO extends BaseDAO {

	/**
	 * 사용자권한(TN_USER_AUTH)을 등록한다.
	 * @param userAuthVO - 등록할 정보가 담긴 UserAuthVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserAuth(UserAuthVO userAuthVO) throws Exception {
		return (String) insert("userAuthDAO.insertUserAuth", userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 수정한다.
	 * @param userAuthVO - 수정할 정보가 담긴 UserAuthVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateUserAuth(UserAuthVO userAuthVO) throws Exception {
		return update("userAuthDAO.updateUserAuth", userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteUserAuth(UserAuthVO userAuthVO) throws Exception {
		return delete("userAuthDAO.deleteUserAuth", userAuthVO);
	}
	
	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteUserAuthByUserNo(UserAuthVO userAuthVO) throws Exception {
		return delete("userAuthDAO.deleteUserAuthByUserNo", userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return 조회한 TN_USER_AUTH
	 * @exception Exception
	 */
	public UserAuthVO selectUserAuth(UserAuthVO userAuthVO) throws Exception {
		return (UserAuthVO) select("userAuthDAO.selectUserAuth", userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return TN_USER_AUTH 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UserAuthVO> selectUserAuthList(UserAuthVO userAuthVO) throws Exception {
		return (List<UserAuthVO>)list("userAuthDAO.selectUserAuthList", userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH) 총 갯수를 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return TN_USER_AUTH 총 갯수
	 * @exception
	 */
	public int selectUserAuthListTotalCount(UserAuthVO userAuthVO) {
		return (Integer) select("userAuthDAO.selectUserAuthListTotalCount", userAuthVO);
	}

}
