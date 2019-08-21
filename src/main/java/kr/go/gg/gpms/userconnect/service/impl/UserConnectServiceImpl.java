package kr.go.gg.gpms.userconnect.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.userconnect.service.UserConnectService;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

/**
 * 사용자접속로그
 *
 * @Class Name : UserConnectServiceImpl.java
 * @Description : UserConnect Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-10-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("userConnectService")
public class UserConnectServiceImpl extends AbstractServiceImpl implements UserConnectService {

	@Resource(name = "userConnectDAO")
	private UserConnectDAO userConnectDAO;

	//@Resource(name="UserConnectIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 등록한다.
	 * @param userConnectVO - 등록할 정보가 담긴 UserConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserConnect(UserConnectVO userConnectVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//userConnectVO.setId(id);

		return userConnectDAO.insertUserConnect( userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 수정한다.
	 * @param userConnectVO - 수정할 정보가 담긴 UserConnectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateUserConnect(UserConnectVO userConnectVO) throws Exception {
		return userConnectDAO.updateUserConnect( userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 삭제한다.
	 * @param userConnectVO - 삭제할 정보가 담긴 UserConnectVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteUserConnect(UserConnectVO userConnectVO) throws Exception {
		return userConnectDAO.deleteUserConnect( userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT)을 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return 조회한 TL_USER_CONNECT
	 * @exception Exception
	 */
	public UserConnectVO selectUserConnect(UserConnectVO userConnectVO) throws Exception {
		return userConnectDAO.selectUserConnect( userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userConnectVO
	 * @return TL_USER_CONNECT 목록
	 * @exception Exception
	 */
	public List<UserConnectVO> selectUserConnectList(UserConnectVO userConnectVO) throws Exception {
		return userConnectDAO.selectUserConnectList( userConnectVO);
	}

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userConnectVO
	 * @return TL_USER_CONNECT 총 갯수
	 * @exception
	 */
	public int selectUserConnectListTotalCount(UserConnectVO userConnectVO) {
		return userConnectDAO.selectUserConnectListTotalCount( userConnectVO);
	}

}
