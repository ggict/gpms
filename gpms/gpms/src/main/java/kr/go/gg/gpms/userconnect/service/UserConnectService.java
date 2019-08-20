package kr.go.gg.gpms.userconnect.service;

import java.util.List;

import kr.go.gg.gpms.userconnect.service.model.UserConnectDefaultVO;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자접속로그
 * TL_USER_CONNECT
 *
 * @Class Name : UserConnectService.java
 * @Description : UserConnect Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-10-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UserConnectService {

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 등록한다.
	 * @param userConnectVO - 등록할 정보가 담긴 UserConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertUserConnect(UserConnectVO userConnectVO) throws Exception;

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 수정한다.
	 * @param userConnectVO - 수정할 정보가 담긴 UserConnectVO
	 * @return int형
	 * @exception Exception
	 */
	int updateUserConnect(UserConnectVO userConnectVO) throws Exception;

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 삭제한다.
	 * @param userConnectVO - 삭제할 정보가 담긴 UserConnectVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteUserConnect(UserConnectVO userConnectVO) throws Exception;

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return 조회한 TL_USER_CONNECT
	 * @exception Exception
	 */
	UserConnectVO selectUserConnect(UserConnectVO userConnectVO) throws Exception;

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userConnectVO
	 * @return TL_USER_CONNECT 목록
	 * @exception Exception
	 */
	List<UserConnectVO> selectUserConnectList(UserConnectVO userConnectVO) throws Exception;

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userConnectVO
	 * @return TL_USER_CONNECT 총 갯수
	 * @exception
	 */
	int selectUserConnectListTotalCount(UserConnectVO userConnectVO);

}

