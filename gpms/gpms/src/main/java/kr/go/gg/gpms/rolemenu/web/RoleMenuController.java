

package kr.go.gg.gpms.rolemenu.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.menu.service.model.MenuVO;
import kr.go.gg.gpms.rolemenu.service.RoleMenuService;
import kr.go.gg.gpms.rolemenu.service.model.RoleMenuVO;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @Class Name : RoleMenuController.java
 * @Description : RoleMenu Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("roleMenuController")
public class RoleMenuController extends BaseController {

	@Resource(name = "roleMenuService")
	private RoleMenuService roleMenuService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleMenuController.class);

	/**
	 * 권한역할메뉴(TN_ROLE_MENU) 목록을 조회한다. (pageing)
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return "/manage/rolemenu/RoleMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/rolemenu/selectRoleMenuList.do" })
	public String selectRoleMenuList(RoleMenuVO roleMenuVO, ModelMap model) throws Exception {
		setSystem("mng");
		CodeVO codeVO = new CodeVO();
		//권한코드 조회
		model.addAttribute("auth_list", roleMenuService.authorityList(codeVO));
		
		return "/auth/roleMenuList";
	}
	
	/**
	 * 권한역할메뉴(TN_ROLE_MENU) 목록을 조회한다. (pageing)
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return "/manage/rolemenu/RoleMenuList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/rolemenu/selectRoleMenuListNPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectRoleMenuListNPage(@RequestBody  RoleMenuVO roleMenuVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(roleMenuVO.getPage());
		paginationInfo.setRecordCountPerPage(roleMenuVO.getPageUnit());
		paginationInfo.setPageSize(roleMenuVO.getRows());
		roleMenuVO.setUsePage(false);
		
		roleMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		roleMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		roleMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<RoleMenuVO> items =  roleMenuService.selectRoleMenuNList(roleMenuVO);
		int total_count = roleMenuService.selectRoleMenuNListTotalCount(roleMenuVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) roleMenuVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", roleMenuVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
	
		return map;
	}
	
	@RequestMapping(value = {  "/api/rolemenu/selectRoleMenuListYPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectRoleMenuListYPage(@RequestBody  RoleMenuVO roleMenuVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(roleMenuVO.getPage());
		paginationInfo.setRecordCountPerPage(roleMenuVO.getPageUnit());
		paginationInfo.setPageSize(roleMenuVO.getRows());
		roleMenuVO.setUsePage(false);
		
		roleMenuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		roleMenuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		roleMenuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<RoleMenuVO> items =  roleMenuService.selectRoleMenuYList(roleMenuVO);
		int total_count = roleMenuService.selectRoleMenuListYTotalCount(roleMenuVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) roleMenuVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", roleMenuVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
	
		return map;
	}
 
	/**
	 * 권한역할메뉴(TN_ROLE_MENU) 상세를 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return "/manage/rolemenu/RoleMenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rolemenu/selectRoleMenu.do"  })
	public String selectRoleMenu(@ModelAttribute("searchVO") RoleMenuVO roleMenuVO, ModelMap model) throws Exception {
	
		model.addAttribute("roleMenuVO", roleMenuService.selectRoleMenu(roleMenuVO));
		return "/manage/rolemenu/RoleMenuView";
	}
	
	/**
	 * 권한역할메뉴(TN_ROLE_MENU) 상세를 조회한다.
	 * @param roleMenuVO - 조회할 정보가 담긴 RoleMenuVO
	 * @return "/manage/rolemenu/RoleMenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rolemenu/selectRoleMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RoleMenuVO selectRoleMenuRest(@RequestBody  RoleMenuVO roleMenuVO, ModelMap model, HttpSession session) throws Exception {
		RoleMenuVO roleMenuVOOne = roleMenuService.selectRoleMenu(roleMenuVO);
		return roleMenuVOOne;
	}

	

	@RequestMapping(value = { "/manage/rolemenu/addRoleMenuView.do" })
	public String addRoleMenuView(@ModelAttribute("searchVO") RoleMenuVO roleMenuVO, Model model) throws Exception {
		model.addAttribute("roleMenuVO", new RoleMenuVO());
		return "/manage/rolemenu/RoleMenuRegist";
	}

	@RequestMapping(value = { "/manage/rolemenu/addRoleMenu.do"  })
	public String addRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		roleMenuService.insertRoleMenu(roleMenuVO);
		return "redirect:/manage/rolemenu/selectRoleMenuList.do";
	}

	@RequestMapping(value = {  "/api/rolemenu/addRoleMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RoleMenuVO addRoleMenuRest(@RequestBody RoleMenuVO roleMenuVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(roleMenuVO);
		roleMenuService.insertRoleMenu(roleMenuVO);
		roleMenuVO.setResultSuccess("true");
		roleMenuVO.setResultMSG("정상 등록되었습니다.");
		reloadFilter();
		return roleMenuVO;
	}
	
	@RequestMapping(value = { "/manage/rolemenu/updateRoleMenuView.do" })
	public String updateRoleMenuView(@ModelAttribute("searchVO") RoleMenuVO roleMenuVO, Model model) throws Exception {
		model.addAttribute("roleMenuVO", roleMenuService.selectRoleMenu(roleMenuVO));
		return "/manage/rolemenu/RoleMenuUpdate";
	}
	
	@RequestMapping(value = { "/manage/rolemenu/deleteRoleMenu.do" })
	public String deleteRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
		roleMenuService.deleteRoleMenu(roleMenuVO);
		return "redirect:/manage/rolemenu/selectRoleMenuList.do";
	}
	
	@RequestMapping(value = {   "/api/rolemenu/deleteRoleMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RoleMenuVO deleteRoleMenuRest(@RequestBody RoleMenuVO roleMenuVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(roleMenuVO);
		roleMenuService.deleteRoleMenu(roleMenuVO);
		roleMenuVO.setResultSuccess("true");
		roleMenuVO.setResultMSG("정상 삭제되었습니다.");
		reloadFilter();
		return roleMenuVO;
	}

}
