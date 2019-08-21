package kr.go.gg.gpms.rolemenu.service;

import java.util.List;

import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.rolemenu.service.model.RoleMenuDefaultVO;
import kr.go.gg.gpms.rolemenu.service.model.RoleMenuVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 권한역할메뉴
 * TN_ROLE_MENU
 *
 * @Class Name : RoleMenuService.java
 * @Description : RoleMenu Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RoleMenuService {

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 등록한다.
	 * @param roleMenuVO - 등록할 정보가 담긴 RoleMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRoleMenu(RoleMenuVO roleMenuVO) throws Exception;

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 삭제한다.
	 * @param roleMenuVO - 삭제할 정보가 담긴 RoleMenuVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRoleMenu(RoleMenuVO roleMenuVO) throws Exception;

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return 조회한 TN_ROLE_MENU
	 * @exception Exception
	 */
	RoleMenuVO selectRoleMenu(RoleMenuVO roleMenuVO) throws Exception;

	/**
	 * 권한이 없는 메뉴를 조회한다
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	List<RoleMenuVO> selectRoleMenuNList(RoleMenuVO roleMenuVO) throws Exception;
	
	/**
	 * 권한이 있는 메뉴를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	List<RoleMenuVO> selectRoleMenuYList(RoleMenuVO roleMenuVO) throws Exception;
	
	/**
	 * 권한역할메뉴 url 정보(TN_ROLE_MENU) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	List<RoleMenuVO> selectRoleMenuURLList(RoleMenuVO roleMenuVO) throws Exception;
	
	/**
	 * 권한이 없는 메뉴개수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	int selectRoleMenuNListTotalCount(RoleMenuVO roleMenuVO);
	
	/**
	 * 권한이 있는 메뉴개수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	int selectRoleMenuListYTotalCount(RoleMenuVO roleMenuVO);

	//권한코드 조회
	List<CodeVO> authorityList(CodeVO codeVO) throws Exception;
	
}

