package kr.go.gg.gpms.userauth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.userauth.service.UserAuthService;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;

/**
 * 사용자권한
 *
 * @Class Name : UserAuthServiceImpl.java
 * @Description : UserAuth Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("userAuthService")
public class UserAuthServiceImpl extends AbstractServiceImpl implements UserAuthService {

	@Resource(name = "userAuthDAO")
	private UserAuthDAO userAuthDAO;

	//@Resource(name="UserAuthIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 사용자권한(TN_USER_AUTH)을 등록한다.
	 * @param userAuthVO - 등록할 정보가 담긴 UserAuthVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserAuth(UserAuthVO userAuthVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//userAuthVO.setId(id);

		return userAuthDAO.insertUserAuth( userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 수정한다.
	 * @param userAuthVO - 수정할 정보가 담긴 UserAuthVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateUserAuth(UserAuthVO userAuthVO) throws Exception {
		return userAuthDAO.updateUserAuth( userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteUserAuth(UserAuthVO userAuthVO) throws Exception {
		return userAuthDAO.deleteUserAuth( userAuthVO);
	}
	
	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteUserAuthByUserNo(UserAuthVO userAuthVO) throws Exception {
		return userAuthDAO.deleteUserAuthByUserNo( userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH)을 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return 조회한 TN_USER_AUTH
	 * @exception Exception
	 */
	public UserAuthVO selectUserAuth(UserAuthVO userAuthVO) throws Exception {
		UserAuthVO resultVO = userAuthDAO.selectUserAuth( userAuthVO);
		 
		return resultVO;
	}

	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userAuthVO
	 * @return TN_USER_AUTH 목록
	 * @exception Exception
	 */
	public List<UserAuthVO> selectUserAuthList(UserAuthVO userAuthVO) throws Exception {
		return userAuthDAO.selectUserAuthList( userAuthVO);
	}

	/**
	 * 사용자권한(TN_USER_AUTH) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userAuthVO
	 * @return TN_USER_AUTH 총 갯수
	 * @exception
	 */
	public int selectUserAuthListTotalCount(UserAuthVO userAuthVO) {
		return userAuthDAO.selectUserAuthListTotalCount( userAuthVO);
	}

}
