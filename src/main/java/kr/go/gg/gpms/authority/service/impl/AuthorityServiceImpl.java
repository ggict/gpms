package kr.go.gg.gpms.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.authority.service.AuthorityService;
import kr.go.gg.gpms.authority.service.model.AuthorityVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 권한그룹
 *
 * @Class Name : AuthorityServiceImpl.java
 * @Description : Authority Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("authorityService")
public class AuthorityServiceImpl extends AbstractServiceImpl implements AuthorityService {

	@Resource(name = "authorityDAO")
	private AuthorityDAO authorityDAO;

	//@Resource(name="AuthorityIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 권한그룹(TN_AUTHORITY)을 등록한다.
	 * @param authorityVO - 등록할 정보가 담긴 AuthorityVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAuthority(AuthorityVO authorityVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//authorityVO.setId(id);

		return authorityDAO.insertAuthority( authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 수정한다.
	 * @param authorityVO - 수정할 정보가 담긴 AuthorityVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateAuthority(AuthorityVO authorityVO) throws Exception {
		return authorityDAO.updateAuthority( authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 삭제한다.
	 * @param authorityVO - 삭제할 정보가 담긴 AuthorityVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteAuthority(AuthorityVO authorityVO) throws Exception {
		return authorityDAO.deleteAuthority( authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return 조회한 TN_AUTHORITY
	 * @exception Exception
	 */
	public AuthorityVO selectAuthority(AuthorityVO authorityVO) throws Exception {
		AuthorityVO resultVO = authorityDAO.selectAuthority( authorityVO);
		 
		return resultVO;
	}

	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 authorityVO
	 * @return TN_AUTHORITY 목록
	 * @exception Exception
	 */
	public List<AuthorityVO> selectAuthorityList(AuthorityVO authorityVO) throws Exception {
		return authorityDAO.selectAuthorityList(authorityVO);
	}

	/**
	 * 권한그룹(TN_AUTHORITY) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 authorityVO
	 * @return TN_AUTHORITY 총 갯수
	 * @exception
	 */
	public int selectAuthorityListTotalCount(AuthorityVO authorityVO) {
		return authorityDAO.selectAuthorityListTotalCount(authorityVO);
	}
	
	/**
	 * 내부/외부 구분하여 권한(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @exception Exception
	 */
	public List<AuthorityVO> selectAuthList(AuthorityVO authorityVO) throws Exception {
		return authorityDAO.selectAuthList(authorityVO);
	}

}
