package kr.go.gg.gpms.userauth.service;

import java.util.List;

import kr.go.gg.gpms.userauth.service.model.UserAuthDefaultVO;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자권한
 * TN_USER_AUTH
 *
 * @Class Name : UserAuthService.java
 * @Description : UserAuth Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UserAuthService {

	/**
	 * 사용자권한(TN_USER_AUTH)을 등록한다.
	 * @param userAuthVO - 등록할 정보가 담긴 UserAuthVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertUserAuth(UserAuthVO userAuthVO) throws Exception;

	/**
	 * 사용자권한(TN_USER_AUTH)을 수정한다.
	 * @param userAuthVO - 수정할 정보가 담긴 UserAuthVO
	 * @return int형
	 * @exception Exception
	 */
	int updateUserAuth(UserAuthVO userAuthVO) throws Exception;

	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteUserAuth(UserAuthVO userAuthVO) throws Exception;
	
	/**
	 * 사용자권한(TN_USER_AUTH)을 삭제한다.
	 * @param userAuthVO - 삭제할 정보가 담긴 UserAuthVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteUserAuthByUserNo(UserAuthVO userAuthVO) throws Exception;

	/**
	 * 사용자권한(TN_USER_AUTH)을 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return 조회한 TN_USER_AUTH
	 * @exception Exception
	 */
	UserAuthVO selectUserAuth(UserAuthVO userAuthVO) throws Exception;

	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userAuthVO
	 * @return TN_USER_AUTH 목록
	 * @exception Exception
	 */
	List<UserAuthVO> selectUserAuthList(UserAuthVO userAuthVO) throws Exception;

	/**
	 * 사용자권한(TN_USER_AUTH) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userAuthVO
	 * @return TN_USER_AUTH 총 갯수
	 * @exception
	 */
	int selectUserAuthListTotalCount(UserAuthVO userAuthVO);

}

