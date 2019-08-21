package kr.go.gg.gpms.usermenu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.usermenu.service.UserMenuService;
import kr.go.gg.gpms.usermenu.service.model.UserMenuVO;

/**
 * 사용자권한메뉴
 *
 * @Class Name : UserMenuServiceImpl.java
 * @Description : UserMenu Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("userMenuService")
public class UserMenuServiceImpl extends AbstractServiceImpl implements UserMenuService {

	@Resource(name = "userMenuDAO")
	private UserMenuDAO userMenuDAO;

	//@Resource(name="UserMenuIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 등록한다.
	 * @param userMenuVO - 등록할 정보가 담긴 UserMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserMenu(UserMenuVO userMenuVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//userMenuVO.setId(id);

		return userMenuDAO.insertUserMenu( userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 수정한다.
	 * @param userMenuVO - 수정할 정보가 담긴 UserMenuVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateUserMenu(UserMenuVO userMenuVO) throws Exception {
		return userMenuDAO.updateUserMenu( userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 삭제한다.
	 * @param userMenuVO - 삭제할 정보가 담긴 UserMenuVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteUserMenu(UserMenuVO userMenuVO) throws Exception {
		return userMenuDAO.deleteUserMenu( userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return 조회한 TN_USER_MENU
	 * @exception Exception
	 */
	public UserMenuVO selectUserMenu(UserMenuVO userMenuVO) throws Exception {
		UserMenuVO resultVO = userMenuDAO.selectUserMenu( userMenuVO);
		
		return resultVO;
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userMenuVO
	 * @return TN_USER_MENU 목록
	 * @exception Exception
	 */
	public List<UserMenuVO> selectUserMenuList(UserMenuVO userMenuVO) throws Exception {
		return userMenuDAO.selectUserMenuList( userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userMenuVO
	 * @return TN_USER_MENU 총 갯수
	 * @exception
	 */
	public int selectUserMenuListTotalCount(UserMenuVO userMenuVO) {
		return userMenuDAO.selectUserMenuListTotalCount( userMenuVO);
	}

}
