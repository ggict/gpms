

package kr.go.gg.gpms.usermenu.web;




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
import kr.go.gg.gpms.usermenu.service.UserMenuService;
import kr.go.gg.gpms.usermenu.service.model.UserMenuVO;



/**
 * @Class Name : UserMenuController.java
 * @Description : UserMenu Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("userMenuController")
public class UserMenuController {

	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuController.class);

	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다. (pageing)
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/usermenu/selectUserMenuList.do" })
	public String selectUserMenuList(UserMenuVO userMenuVO, ModelMap model) throws Exception {
		userMenuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userMenuVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userMenuVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userMenuVO.getPageUnit());
		paginationInfo.setPageSize(userMenuVO.getPageSize());

		userMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserMenuVO> items = userMenuService.selectUserMenuList(userMenuVO);
		model.addAttribute("items", items);

		int totCnt = userMenuService.selectUserMenuListTotalCount(userMenuVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/usermenu/UserMenuList" ;
	}
	
	
	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다. (pageing)
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/usermenu/selectUserMenuList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<UserMenuVO> selectUserMenuListRest(@RequestBody UserMenuVO userMenuVO, ModelMap model, HttpSession session) throws Exception {
		userMenuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userMenuVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userMenuVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userMenuVO.getPageUnit());
		paginationInfo.setPageSize(userMenuVO.getPageSize());

		userMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserMenuVO> items = userMenuService.selectUserMenuList(userMenuVO);
		return items;
	}
	
	
	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다. (pageing)
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/usermenu/selectUserMenuListPage.do" })
	public String selectUserMenuListPage(UserMenuVO userMenuVO, ModelMap model) throws Exception {
		userMenuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userMenuVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userMenuVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userMenuVO.getPageUnit());
		paginationInfo.setPageSize(userMenuVO.getPageSize());

		userMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserMenuVO> items = userMenuService.selectUserMenuList(userMenuVO);
		model.addAttribute("items", items);

		int totCnt = userMenuService.selectUserMenuListTotalCount(userMenuVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/usermenu/UserMenuList" ;
	}
	
	/**
	 * 사용자권한메뉴(TN_USER_MENU) 목록을 조회한다. (pageing)
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/usermenu/selectUserMenuListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<UserMenuVO>  selectUserMenuListPageRest(@RequestBody  UserMenuVO userMenuVO, ModelMap model, HttpSession session) throws Exception {
		userMenuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		userMenuVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userMenuVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userMenuVO.getPageUnit());
		paginationInfo.setPageSize(userMenuVO.getPageSize());

		userMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserMenuVO> items = userMenuService.selectUserMenuList(userMenuVO);
		return items;
	}	
 
	
	
	/**
	 * 사용자권한메뉴(TN_USER_MENU) 상세를 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/usermenu/selectUserMenu.do"  })
	public String selectUserMenu(@ModelAttribute("searchVO") UserMenuVO userMenuVO, ModelMap model) throws Exception {
	
		model.addAttribute("userMenuVO", userMenuService.selectUserMenu(userMenuVO));
		return "/manage/usermenu/UserMenuView";
	}
	
	/**
	 * 사용자권한메뉴(TN_USER_MENU) 상세를 조회한다.
	 * @param userMenuVO - 조회할 정보가 담긴 UserMenuVO
	 * @return "/manage/usermenu/UserMenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/usermenu/selectUserMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserMenuVO selectUserMenuRest(@RequestBody  UserMenuVO userMenuVO, ModelMap model, HttpSession session) throws Exception {
		UserMenuVO userMenuVOOne = userMenuService.selectUserMenu(userMenuVO);
		return userMenuVOOne;
	}

	

	@RequestMapping(value = { "/manage/usermenu/addUserMenuView.do" })
	public String addUserMenuView(@ModelAttribute("searchVO") UserMenuVO userMenuVO, Model model) throws Exception {
		model.addAttribute("userMenuVO", new UserMenuVO());
		return "/manage/usermenu/UserMenuRegist";
	}

	@RequestMapping(value = { "/manage/usermenu/addUserMenu.do"  })
	public String addUserMenu(UserMenuVO userMenuVO) throws Exception {
		userMenuService.insertUserMenu(userMenuVO);
		return "redirect:/manage/usermenu/selectUserMenuList.do";
	}
	
	@RequestMapping(value = {  "/api/usermenu/addUserMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserMenuVO addUserMenuRest(@RequestBody UserMenuVO userMenuVO, HttpSession session) throws Exception {
		userMenuService.insertUserMenu(userMenuVO);
		return userMenuVO;
	}
	

	@RequestMapping(value = { "/manage/usermenu/updateUserMenuView.do" })
	public String updateUserMenuView(@ModelAttribute("searchVO") UserMenuVO userMenuVO, Model model) throws Exception {
		model.addAttribute("userMenuVO", userMenuService.selectUserMenu(userMenuVO));
		return "/manage/usermenu/UserMenuUpdate";
	}

	@RequestMapping(value = { "/manage/usermenu/updateUserMenu.do" })
	public String updateUserMenu(UserMenuVO userMenuVO) throws Exception {
		userMenuService.updateUserMenu(userMenuVO);
		return "redirect:/manage/usermenu/selectUserMenuList.do";
	}
	
	@RequestMapping(value = {  "/api/usermenu/updateUserMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserMenuVO updateUserMenuRest(@RequestBody UserMenuVO userMenuVO, HttpSession session) throws Exception {
		userMenuService.updateUserMenu(userMenuVO);
		return userMenuVO;
	}

	@RequestMapping(value = { "/manage/usermenu/deleteUserMenu.do" })
	public String deleteUserMenu(UserMenuVO userMenuVO) throws Exception {
		userMenuService.deleteUserMenu(userMenuVO);
		return "redirect:/manage/usermenu/selectUserMenuList.do";
	}
	
	@RequestMapping(value = {   "/api/usermenu/deleteUserMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserMenuVO deleteUserMenuRest(@RequestBody UserMenuVO userMenuVO, HttpSession session) throws Exception {
		userMenuService.deleteUserMenu(userMenuVO);
		return userMenuVO;
	}

}
