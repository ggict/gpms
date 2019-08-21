


package kr.go.gg.gpms.usermenu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.usermenu.service.model.UserMenuVO;

/**
 * 사용자권한메뉴
 *
 * @Class Name : UserMenuDAO.java
 * @Description : UserMenu DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("userMenuDAO")
public class UserMenuDAO extends BaseDAO {

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 등록한다.
	 * @param userMenuVO - 등록할 정보가 담긴 UserMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserMenu(UserMenuVO userMenuVO) throws Exception {
		return (String) insert("userMenuDAO.insertUserMenu", userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 수정한다.
	 * @param userMenuVO - 수정할 정보가 담긴 UserMenuVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateUserMenu(UserMenuVO userMenuVO) throws Exception {
		return update("userMenuDAO.updateUserMenu", userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 삭제한다.
	 * @param userMenuVO - 삭제할 정보가 담긴 UserMenuVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteUserMenu(UserMenuVO userMenuVO) throws Exception {
		return delete("userMenuDAO.deleteUserMenu", userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU)을 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return 조회한 TN_USER_MENU
	 * @exception Exception
	 */
	public UserMenuVO selectUserMenu(UserMenuVO userMenuVO) throws Exception {
		return (UserMenuVO) select("userMenuDAO.selectUserMenu", userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return TN_USER_MENU 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UserMenuVO> selectUserMenuList(UserMenuVO userMenuVO) throws Exception {
		return (List<UserMenuVO>)list("userMenuDAO.selectUserMenuList", userMenuVO);
	}

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 총 갯수를 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return TN_USER_MENU 총 갯수
	 * @exception
	 */
	public int selectUserMenuListTotalCount(UserMenuVO userMenuVO) {
		return (Integer) select("userMenuDAO.selectUserMenuListTotalCount", userMenuVO);
	}

}
