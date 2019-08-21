package kr.go.gg.gpms.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cmmn.service.impl.CmmnDAO;
import kr.go.gg.gpms.menu.service.MenuService;
import kr.go.gg.gpms.menu.service.model.MenuVO;

/**
 * 시스템메뉴
 *
 * @Class Name : MenuServiceImpl.java
 * @Description : Menu Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {

	@Resource(name = "menuDAO")
	private MenuDAO menuDAO;

	@Resource(name = "cmmnDAO")
	private CmmnDAO cmmnDAO;
	
	// @Resource(name="MenuIdGnrService")
	// private EgovIdGnrService egovIdGnrService;

	/**
	 * 시스템메뉴(TN_MENU)을 등록한다.
	 * 
	 * @param menuVO
	 *            - 등록할 정보가 담긴 MenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertMenu(MenuVO menuVO) throws Exception {
		// String id = egovIdGnrService.getNextStringId();
		// menuVO.setId(id);

		return menuDAO.insertMenu(menuVO);
	}

	/**
	 * 시스템메뉴(TN_MENU)을 삭제한다.
	 * 
	 * @param menuVO
	 *            - 삭제할 정보가 담긴 MenuVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteMenu(MenuVO menuVO) throws Exception {
		return menuDAO.deleteMenu(menuVO);
	}

	/**
	 * 시스템메뉴(TN_MENU)을 조회한다.
	 * 
	 * @param menuVO
	 *            - 조회할 정보가 담긴 MenuVO
	 * @return 조회한 TN_MENU
	 * @exception Exception
	 */
	public MenuVO selectMenu(MenuVO menuVO) throws Exception {
		MenuVO resultVO = menuDAO.selectMenu(menuVO);

		return resultVO;
	}

	/**
	 * 시스템메뉴(TN_MENU) 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 menuVO
	 * @return TN_MENU 목록
	 * @exception Exception
	 */
	public List<MenuVO> selectMenuList(MenuVO menuVO) throws Exception {
		return menuDAO.selectMenuList(menuVO);
	}

	/**
	 * 시스템메뉴(TN_MENU) 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 menuVO
	 * @return TN_MENU 총 갯수
	 * @exception
	 */
	public int selectMenuListTotalCount(MenuVO menuVO) {
		return menuDAO.selectMenuListTotalCount(menuVO);
	}
	
	//검색-메뉴명
	public List selectSysCodeList(MenuVO menuVO) throws Exception{
		return menuDAO.selectSysCodeList(menuVO);
	}
	
	//수정
	public int updateMenuCode(MenuVO menuVO) throws Exception {
		return menuDAO.updateMenuCode(menuVO);
	}
	
	@Override
	public MenuVO selectMenuByURL(MenuVO menuVO) {
		MenuVO resultVO = menuDAO.selectMenuByURL(menuVO);

		return resultVO;
	}
	
	//검색-메뉴명
	public List selectSysCode2List(MenuVO menuVO) throws Exception{
		return menuDAO.selectSysCode2List(menuVO);
	}

}
