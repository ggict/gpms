


package kr.go.gg.gpms.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.menu.service.model.MenuVO;

/**
 * 시스템메뉴
 *
 * @Class Name : MenuDAO.java
 * @Description : Menu DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("menuDAO")
public class MenuDAO extends BaseDAO {

	/**
	 * 시스템메뉴(TN_MENU)을 등록한다.
	 * @param menuVO - 등록할 정보가 담긴 MenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertMenu(MenuVO menuVO) throws Exception {
		return (String) insert("menuDAO.insertMenu", menuVO);
	}
	
	/**
	 * 시스템메뉴(TN_MENU)을 삭제한다.
	 * @param menuVO - 삭제할 정보가 담긴 MenuVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteMenu(MenuVO menuVO) throws Exception {
		return delete("menuDAO.deleteMenu", menuVO);
	}
	
	/**
	 * 시스템메뉴(TN_MENU)을 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return 조회한 TN_MENU
	 * @exception Exception
	 */
	public MenuVO selectMenu(MenuVO menuVO) throws Exception {
		return (MenuVO) select("menuDAO.selectMenu", menuVO);
	}
	
	/**
	 * 시스템메뉴(TN_MENU) 목록을 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return TN_MENU 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVO> selectMenuList(MenuVO menuVO) throws Exception {
		return (List<MenuVO>)list("menuDAO.selectMenuList", menuVO);
	}
	
	/**
	 * 시스템메뉴(TN_MENU) 총 갯수를 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return TN_MENU 총 갯수
	 * @exception
	 */
	public int selectMenuListTotalCount(MenuVO menuVO) {
		return (Integer) select("menuDAO.selectMenuListTotalCount", menuVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVO> selectSysCodeList(MenuVO menuVO) throws Exception {
		return (List<MenuVO>)list("menuDAO.selectSysCodeList", menuVO);
	}
	
		
	public int updateMenuCode(MenuVO menuVO) throws Exception {
		return update("menuDAO.updateMenuCode", menuVO);
	}
	
	public MenuVO selectMenuByURL(MenuVO menuVO) {
		return (MenuVO) select("menuDAO.selectMenuByURL", menuVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVO> selectSysCode2List(MenuVO menuVO) throws Exception {
		return (List<MenuVO>)list("menuDAO.selectSysCode2List", menuVO);
	}
}
