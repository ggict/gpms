

package kr.go.gg.gpms.userauth.web;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.security.core.context.SecurityContextHolder;




import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.authority.service.model.AuthorityVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;
import kr.go.gg.gpms.userauth.service.UserAuthService;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;



/**
 * @Class Name : UserAuthController.java
 * @Description : UserAuth Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("userAuthController")
public class UserAuthController extends BaseController{

	@Resource(name = "userAuthService")
	private UserAuthService userAuthService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthController.class);

	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다. (pageing)
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/userauth/selectUserAuthList.do" })
	public String selectUserAuthList(UserAuthVO userAuthVO, ModelMap model) throws Exception {
		return "/auth/userAuthList";
	}
	
	
	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다. (pageing)
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/userauth/selectUserAuthList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<UserAuthVO> selectUserAuthListRest(@RequestBody UserAuthVO userAuthVO, ModelMap model, HttpSession session) throws Exception {
		userAuthVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userAuthVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userAuthVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userAuthVO.getPageUnit());
		paginationInfo.setPageSize(userAuthVO.getPageSize());

		userAuthVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userAuthVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userAuthVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserAuthVO> items = userAuthService.selectUserAuthList(userAuthVO);
		return items;
	}
	
	
	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다. (pageing)
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/userauth/selectUserAuthListPage.do" })
	public String selectUserAuthListPage(UserAuthVO userAuthVO, ModelMap model) throws Exception {
		userAuthVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userAuthVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userAuthVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userAuthVO.getPageUnit());
		paginationInfo.setPageSize(userAuthVO.getPageSize());

		userAuthVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userAuthVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userAuthVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserAuthVO> items = userAuthService.selectUserAuthList(userAuthVO);
		model.addAttribute("items", items);

		int totCnt = userAuthService.selectUserAuthListTotalCount(userAuthVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/userauth/UserAuthList" ;
	}
	
	/**
	 * 사용자권한(TN_USER_AUTH) 목록을 조회한다. (pageing)
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/userauth/selectUserAuthListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectAuthorityListPage(@RequestBody UserAuthVO userAuthVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userAuthVO.getPage());
		paginationInfo.setRecordCountPerPage(userAuthVO.getPageUnit());
		paginationInfo.setPageSize(userAuthVO.getRows());
		
		userAuthVO.setUsePage(true);
		userAuthVO.setUSE_AT("Y");
		//userAuthVO.setDELETE_AT("N");

		userAuthVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userAuthVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userAuthVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<UserAuthVO> items = userAuthService.selectUserAuthList(userAuthVO);
		int totCnt = userAuthService.selectUserAuthListTotalCount(userAuthVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", userAuthVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);
		
		return map;
	}	
 
	
	/**
	 * 사용자권한(TN_USER_AUTH) 상세를 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/userauth/selectUserAuth.do"  })
	public String selectUserAuth(@ModelAttribute("searchVO") UserAuthVO userAuthVO, ModelMap model) throws Exception {
	
		model.addAttribute("userAuthVO", userAuthService.selectUserAuth(userAuthVO));
		return "/manage/userauth/UserAuthView";
	}
	
	/**
	 * 사용자권한(TN_USER_AUTH) 상세를 조회한다.
	 * @param userAuthVO - 조회할 정보가 담긴 UserAuthVO
	 * @return "/manage/userauth/UserAuthView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/userauth/selectUserAuth.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserAuthVO selectUserAuthRest(@RequestBody  UserAuthVO userAuthVO, ModelMap model, HttpSession session) throws Exception {
		UserAuthVO userAuthVOOne = userAuthService.selectUserAuth(userAuthVO);
		return userAuthVOOne;
	}

	

	@RequestMapping(value = { "/manage/userauth/addUserAuthView.do" })
	public String addUserAuthView(@ModelAttribute("searchVO") UserAuthVO userAuthVO, Model model) throws Exception {
		model.addAttribute("userAuthVO", new UserAuthVO());
		return "/manage/userauth/UserAuthRegist";
	}

	@RequestMapping(value = { "/manage/userauth/addUserAuth.do"  })
	public String addUserAuth(UserAuthVO userAuthVO) throws Exception {
		userAuthService.insertUserAuth(userAuthVO);
		return "redirect:/manage/userauth/selectUserAuthList.do";
	}
	
	@RequestMapping(value = {  "/api/userauth/addUserAuth.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserAuthVO addUserAuthRest(@RequestBody UserAuthVO userAuthVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(userAuthVO);
		userAuthService.insertUserAuth(userAuthVO);
		userAuthVO.setResultSuccess("true");
		userAuthVO.setResultMSG("정상 등록되었습니다.");
		return userAuthVO;
	}
	

	@RequestMapping(value = { "/manage/userauth/updateUserAuthView.do" })
	public String updateUserAuthView(@ModelAttribute("searchVO") UserAuthVO userAuthVO, Model model) throws Exception {
		model.addAttribute("userAuthVO", userAuthService.selectUserAuth(userAuthVO));
		return "/manage/userauth/UserAuthUpdate";
	}

	@RequestMapping(value = { "/manage/userauth/updateUserAuth.do" })
	public String updateUserAuth(UserAuthVO userAuthVO) throws Exception {
		userAuthService.updateUserAuth(userAuthVO);
		return "redirect:/manage/userauth/selectUserAuthList.do";
	}
	
	@RequestMapping(value = {  "/api/userauth/updateUserAuth.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserAuthVO updateUserAuthRest(@RequestBody UserAuthVO userAuthVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(userAuthVO);
		userAuthService.updateUserAuth(userAuthVO);
		userAuthVO.setResultSuccess("true");
		userAuthVO.setResultMSG("정상 수정되었습니다.");
		return userAuthVO;
	}

	@RequestMapping(value = { "/manage/userauth/deleteUserAuth.do" })
	public String deleteUserAuth(UserAuthVO userAuthVO) throws Exception {
		userAuthService.deleteUserAuth(userAuthVO);
		return "redirect:/manage/userauth/selectUserAuthList.do";
	}
	
	@RequestMapping(value = {   "/api/userauth/deleteUserAuth.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserAuthVO deleteUserAuthRest(@RequestBody UserAuthVO userAuthVO, HttpSession session) throws Exception {
		userAuthService.deleteUserAuth(userAuthVO);
		return userAuthVO;
	}
	
	/**
	 * 사용자 권한 체크
	 * @param userAuthVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/userauth/checkAuth.do"  })
	public String checkAuth(UserAuthVO userAuthVO, HttpServletRequest request, Model model) throws Exception {
		Map<String, String> req = requestToHashMap(request);
		
		String url = req.get("url");
		boolean result = checkRole(url);
		
		model.addAttribute("result", result);
		
		return "jsonView";
	}
	
}
