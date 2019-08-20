


package kr.go.gg.gpms.authority.service.impl;

import java.util.List;

import kr.go.gg.gpms.authority.service.model.AuthorityVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 권한그룹
 *
 * @Class Name : AuthorityDAO.java
 * @Description : Authority DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("authorityDAO")
public class AuthorityDAO extends BaseDAO {

	/**
	 * 권한그룹(TN_AUTHORITY)을 등록한다.
	 * @param authorityVO - 등록할 정보가 담긴 AuthorityVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAuthority(AuthorityVO authorityVO) throws Exception {
		return (String) insert("authorityDAO.insertAuthority", authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 수정한다.
	 * @param authorityVO - 수정할 정보가 담긴 AuthorityVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateAuthority(AuthorityVO authorityVO) throws Exception {
		return update("authorityDAO.updateAuthority", authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 삭제한다.
	 * @param authorityVO - 삭제할 정보가 담긴 AuthorityVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteAuthority(AuthorityVO authorityVO) throws Exception {
		return delete("authorityDAO.deleteAuthority", authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return 조회한 TN_AUTHORITY
	 * @exception Exception
	 */
	public AuthorityVO selectAuthority(AuthorityVO authorityVO) throws Exception {
		return (AuthorityVO) select("authorityDAO.selectAuthority", authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return TN_AUTHORITY 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AuthorityVO> selectAuthorityList(AuthorityVO authorityVO) throws Exception {
		return (List<AuthorityVO>)list("authorityDAO.selectAuthorityList", authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY) 총 갯수를 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return TN_AUTHORITY 총 갯수
	 * @exception
	 */
	public int selectAuthorityListTotalCount(AuthorityVO authorityVO) {
		return (Integer) select("authorityDAO.selectAuthorityListTotalCount", authorityVO);
	}
	
	/**
	 * 내부/외부 구분하여 권한(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AuthorityVO> selectAuthList(AuthorityVO authorityVO) throws Exception {
		return (List<AuthorityVO>)list("authorityDAO.selectAuthList", authorityVO);
	}
 
}
