package kr.go.gg.gpms.usermenu.service;

import java.util.List;

import kr.go.gg.gpms.usermenu.service.model.UserMenuDefaultVO;
import kr.go.gg.gpms.usermenu.service.model.UserMenuVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자권한메뉴
 * TN_USER_MENU
 *
 * @Class Name : UserMenuService.java
 * @Description : UserMenu Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UserMenuService {

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 등록한다.
	 * @param userMenuVO - 등록할 정보가 담긴 UserMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertUserMenu(UserMenuVO userMenuVO) throws Exception;

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 수정한다.
	 * @param userMenuVO - 수정할 정보가 담긴 UserMenuVO
	 * @return int형
	 * @exception Exception
	 */
	int updateUserMenu(UserMenuVO userMenuVO) throws Exception;

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 삭제한다.
	 * @param userMenuVO - 삭제할 정보가 담긴 UserMenuVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteUserMenu(UserMenuVO userMenuVO) throws Exception;

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return 조회한 TN_USER_MENU
	 * @exception Exception
	 */
	UserMenuVO selectUserMenu(UserMenuVO userMenuVO) throws Exception;

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userMenuVO
	 * @return TN_USER_MENU 목록
	 * @exception Exception
	 */
	List<UserMenuVO> selectUserMenuList(UserMenuVO userMenuVO) throws Exception;

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 userMenuVO
	 * @return TN_USER_MENU 총 갯수
	 * @exception
	 */
	int selectUserMenuListTotalCount(UserMenuVO userMenuVO);

}

