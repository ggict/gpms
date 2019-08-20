package kr.go.gg.gpms.menu.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.menu.service.model.MenuDefaultVO;
import kr.go.gg.gpms.menu.service.model.MenuVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템메뉴
 * TN_MENU
 *
 * @Class Name : MenuService.java
 * @Description : Menu Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MenuService {

	/**
	 * 시스템메뉴(TN_MENU)을 등록한다.
	 * @param menuVO - 등록할 정보가 담긴 MenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 시스템메뉴(TN_MENU)을 삭제한다.
	 * @param menuVO - 삭제할 정보가 담긴 MenuVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 시스템메뉴(TN_MENU)을 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return 조회한 TN_MENU
	 * @exception Exception
	 */
	MenuVO selectMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 시스템메뉴(TN_MENU) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 menuVO
	 * @return TN_MENU 목록
	 * @exception Exception
	 */
	List<MenuVO> selectMenuList(MenuVO menuVO) throws Exception;
	
	/**
	 * 시스템메뉴(TN_MENU) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 menuVO
	 * @return TN_MENU 총 갯수
	 * @exception
	 */
	int selectMenuListTotalCount(MenuVO menuVO);
		
	//검색-메뉴조회
	List selectSysCodeList(MenuVO menuVO) throws Exception;
		
	//수정
	int updateMenuCode(MenuVO menuVO) throws Exception;

	MenuVO selectMenuByURL(MenuVO menuVO);
	
	//검색-메뉴조회
	List selectSysCode2List(MenuVO menuVO) throws Exception;
}

