package kr.go.gg.gpms.rolemenu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.rolemenu.service.RoleMenuService;
import kr.go.gg.gpms.rolemenu.service.model.RoleMenuVO;

/**
 * 권한역할메뉴
 *
 * @Class Name : RoleMenuServiceImpl.java
 * @Description : RoleMenu Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("roleMenuService")
public class RoleMenuServiceImpl extends AbstractServiceImpl implements RoleMenuService {

	@Resource(name = "roleMenuDAO")
	private RoleMenuDAO roleMenuDAO;

	//@Resource(name="RoleMenuIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 등록한다.
	 * @param roleMenuVO - 등록할 정보가 담긴 RoleMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//roleMenuVO.setId(id);

		return roleMenuDAO.insertRoleMenu( roleMenuVO);
	}

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 삭제한다.
	 * @param roleMenuVO - 삭제할 정보가 담긴 RoleMenuVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		return roleMenuDAO.deleteRoleMenu( roleMenuVO);
	}

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return 조회한 TN_ROLE_MENU
	 * @exception Exception
	 */
	public RoleMenuVO selectRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		RoleMenuVO resultVO = roleMenuDAO.selectRoleMenu( roleMenuVO);
		 
		return resultVO;
	}

	/**
	 * 권한이 없는메뉴를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	public List<RoleMenuVO> selectRoleMenuNList(RoleMenuVO roleMenuVO) throws Exception {
		return roleMenuDAO.selectRoleMenuNList( roleMenuVO);
	}
	
	/**
	 * 권한이 있는메뉴를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	public List<RoleMenuVO> selectRoleMenuYList(RoleMenuVO roleMenuVO) throws Exception {
		return roleMenuDAO.selectRoleMenuYList( roleMenuVO);
	}

	/**
	 * 권한이 있는 메뉴 개수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	public int selectRoleMenuListYTotalCount(RoleMenuVO roleMenuVO) {
		return roleMenuDAO.selectRoleMenuListYTotalCount( roleMenuVO);
	}
	
	/**
	 * 권한이 없는 메뉴개수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 roleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	public int selectRoleMenuNListTotalCount(RoleMenuVO roleMenuVO) {
		return roleMenuDAO.selectRoleMenuNListTotalCount( roleMenuVO);
	}

	@Override
	public List<RoleMenuVO> selectRoleMenuURLList(RoleMenuVO roleMenuVO) throws Exception {
		return roleMenuDAO.selectRoleMenuURLList( roleMenuVO);
	}
	
	//권한코드 조회
	public List<CodeVO> authorityList(CodeVO codeVO) throws Exception {
		return roleMenuDAO.authorityList(codeVO);
	}

}
