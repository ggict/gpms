package kr.go.gg.gpms.authority.service;

import java.util.List;

import kr.go.gg.gpms.authority.service.model.AuthorityVO;

/**
 * 권한그룹
 * TN_AUTHORITY
 *
 * @Class Name : AuthorityService.java
 * @Description : Authority Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface AuthorityService {

	/**
	 * 권한그룹(TN_AUTHORITY)을 등록한다.
	 * @param authorityVO - 등록할 정보가 담긴 AuthorityVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertAuthority(AuthorityVO authorityVO) throws Exception;

	/**
	 * 권한그룹(TN_AUTHORITY)을 수정한다.
	 * @param authorityVO - 수정할 정보가 담긴 AuthorityVO
	 * @return int형
	 * @exception Exception
	 */
	int updateAuthority(AuthorityVO authorityVO) throws Exception;

	/**
	 * 권한그룹(TN_AUTHORITY)을 삭제한다.
	 * @param authorityVO - 삭제할 정보가 담긴 AuthorityVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteAuthority(AuthorityVO authorityVO) throws Exception;

	/**
	 * 권한그룹(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return 조회한 TN_AUTHORITY
	 * @exception Exception
	 */
	AuthorityVO selectAuthority(AuthorityVO authorityVO) throws Exception;

	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 authorityVO
	 * @return TN_AUTHORITY 목록
	 * @exception Exception
	 */
	List<AuthorityVO> selectAuthorityList(AuthorityVO authorityVO) throws Exception;
 
	/**
	 * 권한그룹(TN_AUTHORITY) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 authorityVO
	 * @return TN_AUTHORITY 총 갯수
	 * @exception
	 */
	int selectAuthorityListTotalCount(AuthorityVO authorityVO);
	
	/**
	 * 내부/외부 구분하여 권한(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @exception Exception
	 */
	List<AuthorityVO> selectAuthList(AuthorityVO authorityVO) throws Exception;
 
}

