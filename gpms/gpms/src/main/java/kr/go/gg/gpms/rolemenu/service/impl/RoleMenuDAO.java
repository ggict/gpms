


package kr.go.gg.gpms.rolemenu.service.impl;

import java.util.List;

import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.rolemenu.service.model.RoleMenuVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 권한역할메뉴
 *
 * @Class Name : RoleMenuDAO.java
 * @Description : RoleMenu DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("roleMenuDAO")
public class RoleMenuDAO extends BaseDAO {

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 등록한다.
	 * @param roleMenuVO - 등록할 정보가 담긴 RoleMenuVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		return (String) insert("roleMenuDAO.insertRoleMenu", roleMenuVO);
	}

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 삭제한다.
	 * @param roleMenuVO - 삭제할 정보가 담긴 RoleMenuVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		return delete("roleMenuDAO.deleteRoleMenu", roleMenuVO);
	}

	/**
	 * 권한역할메뉴(TN_ROLE_MENU)을 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return 조회한 TN_ROLE_MENU
	 * @exception Exception
	 */
	public RoleMenuVO selectRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		return (RoleMenuVO) select("roleMenuDAO.selectRoleMenu", roleMenuVO);
	}

	/**
	 * 권한이 없는메뉴를 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RoleMenuVO> selectRoleMenuNList(RoleMenuVO roleMenuVO) throws Exception {
		return (List<RoleMenuVO>)list("roleMenuDAO.selectRoleMenuNList", roleMenuVO);
	}
	
	/**
	 * 권한이 없는메뉴를조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return TN_ROLE_MENU 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RoleMenuVO> selectRoleMenuYList(RoleMenuVO roleMenuVO) throws Exception {
		return (List<RoleMenuVO>)list("roleMenuDAO.selectRoleMenuYList", roleMenuVO);
	}

	/**
	 * 권한이 없는메뉴 개수를조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	public int selectRoleMenuNListTotalCount(RoleMenuVO roleMenuVO) {
		return (Integer) select("roleMenuDAO.selectRoleMenuNListTotalCount", roleMenuVO);
	}

	/**
	 * 권한이 있는메뉴개수를조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return TN_ROLE_MENU 총 갯수
	 * @exception
	 */
	public int selectRoleMenuListYTotalCount(RoleMenuVO roleMenuVO) {
		return (Integer) select("roleMenuDAO.selectRoleMenuListYTotalCount", roleMenuVO);
	}
	
	public List<RoleMenuVO> selectRoleMenuURLList(RoleMenuVO roleMenuVO) {
		return (List<RoleMenuVO>)list("roleMenuDAO.selectRoleMenuURLList", roleMenuVO);
	}
	
	//권한코드 조회
	@SuppressWarnings("uncheked")
	public List<CodeVO> authorityList(CodeVO codeVO) throws Exception {
		return (List<CodeVO>)list("roleMenuDAO.authorityList", codeVO);
	}

}
