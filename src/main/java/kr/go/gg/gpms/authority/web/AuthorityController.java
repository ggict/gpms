

package kr.go.gg.gpms.authority.web;




import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.authority.service.AuthorityService;
import kr.go.gg.gpms.authority.service.model.AuthorityVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : AuthorityController.java
 * @Description : Authority Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("authorityController")
public class AuthorityController {

	@Resource(name = "authorityService")
	private AuthorityService authorityService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);

	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다. (pageing)
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/authority/selectAuthorityList.do" })
	public String selectAuthorityList(AuthorityVO authorityVO, ModelMap model) throws Exception {
		authorityVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		authorityVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorityVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorityVO.getPageUnit());
		paginationInfo.setPageSize(authorityVO.getPageSize());

		authorityVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorityVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorityVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AuthorityVO> items = authorityService.selectAuthorityList(authorityVO);
		model.addAttribute("items", items);

		int totCnt = authorityService.selectAuthorityListTotalCount(authorityVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/authority/AuthorityList" ;
	}
	
	
	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다. (pageing)
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/authority/selectAuthorityList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<AuthorityVO> selectAuthorityListRest(@RequestBody AuthorityVO authorityVO, ModelMap model, HttpSession session) throws Exception {
		authorityVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		authorityVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorityVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorityVO.getPageUnit());
		paginationInfo.setPageSize(authorityVO.getPageSize());

		authorityVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorityVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorityVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AuthorityVO> items = authorityService.selectAuthorityList(authorityVO);
		return items;
	}
	
	
	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다. (pageing)
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/authority/selectAuthorityListPage.do" })
	public String selectAuthorityListPage(AuthorityVO authorityVO, ModelMap model) throws Exception {
		authorityVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		authorityVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorityVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorityVO.getPageUnit());
		paginationInfo.setPageSize(authorityVO.getPageSize());

		authorityVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorityVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorityVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AuthorityVO> items = authorityService.selectAuthorityList(authorityVO);
		model.addAttribute("items", items);

		int totCnt = authorityService.selectAuthorityListTotalCount(authorityVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/authority/AuthorityList" ;
	}
	
	/**
	 * 권한그룹(TN_AUTHORITY) 목록을 조회한다. (pageing)
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/authority/selectAuthorityListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<AuthorityVO>  selectAuthorityListPageRest(@RequestBody  AuthorityVO authorityVO, ModelMap model, HttpSession session) throws Exception {
		authorityVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		authorityVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorityVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorityVO.getPageUnit());
		paginationInfo.setPageSize(authorityVO.getPageSize());

		authorityVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorityVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorityVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AuthorityVO> items = authorityService.selectAuthorityList(authorityVO);
		return items;
	}	
 
	
	
	/**
	 * 권한그룹(TN_AUTHORITY) 상세를 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/authority/selectAuthority.do"  })
	public String selectAuthority(@ModelAttribute("searchVO") AuthorityVO authorityVO, ModelMap model) throws Exception {
	
		model.addAttribute("authorityVO", authorityService.selectAuthority(authorityVO));
		return "/manage/authority/AuthorityView";
	}
	
	/**
	 * 권한그룹(TN_AUTHORITY) 상세를 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @return "/manage/authority/AuthorityView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/authority/selectAuthority.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AuthorityVO selectAuthorityRest(@RequestBody  AuthorityVO authorityVO, ModelMap model, HttpSession session) throws Exception {
		AuthorityVO authorityVOOne = authorityService.selectAuthority(authorityVO);
		return authorityVOOne;
	}

	

	@RequestMapping(value = { "/manage/authority/addAuthorityView.do" })
	public String addAuthorityView(@ModelAttribute("searchVO") AuthorityVO authorityVO, Model model) throws Exception {
		model.addAttribute("authorityVO", new AuthorityVO());
		return "/manage/authority/AuthorityRegist";
	}

	@RequestMapping(value = { "/manage/authority/addAuthority.do"  })
	public String addAuthority(AuthorityVO authorityVO) throws Exception {
		authorityService.insertAuthority(authorityVO);
		return "redirect:/manage/authority/selectAuthorityList.do";
	}
	
	@RequestMapping(value = {  "/api/authority/addAuthority.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AuthorityVO addAuthorityRest(@RequestBody AuthorityVO authorityVO, HttpSession session) throws Exception {
		authorityService.insertAuthority(authorityVO);
		return authorityVO;
	}
	

	@RequestMapping(value = { "/manage/authority/updateAuthorityView.do" })
	public String updateAuthorityView(@ModelAttribute("searchVO") AuthorityVO authorityVO, Model model) throws Exception {
		model.addAttribute("authorityVO", authorityService.selectAuthority(authorityVO));
		return "/manage/authority/AuthorityUpdate";
	}

	@RequestMapping(value = { "/manage/authority/updateAuthority.do" })
	public String updateAuthority(AuthorityVO authorityVO) throws Exception {
		authorityService.updateAuthority(authorityVO);
		return "redirect:/manage/authority/selectAuthorityList.do";
	}
	
	@RequestMapping(value = {  "/api/authority/updateAuthority.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AuthorityVO updateAuthorityRest(@RequestBody AuthorityVO authorityVO, HttpSession session) throws Exception {
		authorityService.updateAuthority(authorityVO);
		return authorityVO;
	}

	@RequestMapping(value = { "/manage/authority/deleteAuthority.do" })
	public String deleteAuthority(AuthorityVO authorityVO) throws Exception {
		authorityService.deleteAuthority(authorityVO);
		return "redirect:/manage/authority/selectAuthorityList.do";
	}
	
	@RequestMapping(value = {   "/api/authority/deleteAuthority.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AuthorityVO deleteAuthorityRest(@RequestBody AuthorityVO authorityVO, HttpSession session) throws Exception {
		authorityService.deleteAuthority(authorityVO);
		return authorityVO;
	}

	/**
	 * 내부/외부 구분하여 권한(TN_AUTHORITY)을 조회한다.
	 * @param authorityVO - 조회할 정보가 담긴 AuthorityVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/authority/selectAuthList.do"  }, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<AuthorityVO> selectAuthList(@ModelAttribute("searchVO") AuthorityVO authorityVO, ModelMap model) throws Exception {
	
		List<AuthorityVO> result = authorityService.selectAuthList(authorityVO);
		return result;
	}
	
}
