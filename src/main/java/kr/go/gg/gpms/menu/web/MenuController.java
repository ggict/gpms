

package kr.go.gg.gpms.menu.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.menu.service.MenuService;
import kr.go.gg.gpms.menu.service.model.MenuVO;

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
 * @Class Name : MenuController.java
 * @Description : Menu Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("menuController")
public class MenuController extends BaseController  {

	@Resource(name = "menuService")
	private MenuService menuService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	/**
	 * 시스템메뉴(TN_MENU) 상세를 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return "/manage/menu/MenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/menu/selectMenu.do"  })
	public String selectMenu(@ModelAttribute("searchVO") MenuVO menuVO, ModelMap model) throws Exception {
		addCodeToModel("SYSM", model);
		model.addAttribute("menuVO", menuService.selectMenu(menuVO));
		return "/menu/menuView";
	}

	/**
	 * 시스템메뉴(TN_MENU) 상세를 조회한다.
	 * @param menuVO - 조회할 정보가 담긴 MenuVO
	 * @return "/manage/menu/MenuView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/menu/selectMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MenuVO selectMenuRest(@RequestBody  MenuVO menuVO, ModelMap model, HttpSession session) throws Exception {
		MenuVO menuVOOne = menuService.selectMenu(menuVO);
		return menuVOOne;
	}

	@RequestMapping(value = { "/manage/menu/addMenuView.do" })
	public String addMenuView(@ModelAttribute("searchVO") MenuVO menuVO, ModelMap model, HttpServletRequest request) throws Exception {
		addCodeToModel("SYSM", model);
		int menuDepth = 1;
		menuVO.setMENU_DP(""+menuDepth);

		if(StringUtils.isNotEmpty(menuVO.getUPPER_MENU_ID())){
			menuDepth = 2;
			MenuVO menuParam = new MenuVO();
			menuParam.setMENU_ID(menuVO.getUPPER_MENU_ID());
			MenuVO menuUpper = menuService.selectMenu(menuParam);
			model.addAttribute("upperMenuVO",menuUpper);
			menuVO.setUPPER_MENU_ID(menuUpper.getMENU_ID());
			if(StringUtils.isNotEmpty(menuUpper.getSYS_CODE())){
				menuVO.setSYS_CODE(menuUpper.getSYS_CODE());
			}

			if(StringUtils.isNotEmpty(menuUpper.getMENU_DP())){
				menuDepth = Integer.parseInt(menuUpper.getMENU_DP())+1;
				menuVO.setMENU_DP(""+menuDepth);
			}
		}

		model.addAttribute("menuVO",menuVO);
		return "/menu/menuRegist";
	}

	@RequestMapping(value = {  "/api/menu/addSysMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MenuVO addSysMenu(@RequestBody MenuVO menuVO, HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		BindBeansToActiveUser(menuVO);
		menuService.insertMenu(menuVO);
		menuVO.setResultSuccess("true");
		menuVO.setResultMSG("정상 등록되었습니다.");
		return menuVO;
	}

	@RequestMapping(value = {  "/api/menu/addMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MenuVO addMenu(@RequestBody MenuVO menuVO, HttpSession session, HttpServletRequest request) throws Exception {
		//메뉴명등록
		BindBeansToActiveUser(menuVO);
		menuService.insertMenu(menuVO);

		//스프링 시큐리티 url&role 재로딩
		reloadFilter();

		menuVO.setResultSuccess("true");
		menuVO.setResultMSG("정상 등록되었습니다.");
		return menuVO;
	}

	@RequestMapping(value = { "/manage/menu/updateMenuView.do" })
	public String updateMenuView(@ModelAttribute("searchVO") MenuVO menuVO, Model model) throws Exception {
		model.addAttribute("menuVO", menuService.selectMenu(menuVO));
		return "/manage/menu/MenuUpdate";
	}

	@RequestMapping(value = { "/mng/menu/selectUserMenuList.do" })
	public String selectUserMenuList(MenuVO menuVO, ModelMap model) throws Exception {
		setSystem("mng");
		model.addAttribute("screen_title", "시스템메뉴 관리");
		addCodeToModel("SYSM", model);

		return "/menu/menuList" ;
	}

	@RequestMapping(value = {  "/api/menu/selectMenuListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectMenuListPage(@RequestBody  MenuVO menuVO, ModelMap model, HttpSession session) throws Exception {
		int pageIndex = 1;
		int pageSize = egovPropertyService.getInt("pageSize");
		if (menuVO.getPageIndex() > 0) {
			pageIndex = menuVO.getPageIndex();
		} else {
			menuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		}

		if (menuVO.getPageSize() > 0) {
			pageSize = menuVO.getPageSize();
		} else {
			menuVO.setPageSize(egovPropertyService.getInt("pageSize"));
		}

		if (menuVO.getPageUnit() <= 0) {
			menuVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		}

		String sidx = "def";
		String sord = "ASC";
		if (StringUtils.isNotEmpty(menuVO.getSidx())) {
			sidx = menuVO.getSidx();
		} else {
			menuVO.setSidx(sidx);
		}
		if (StringUtils.isNotEmpty(menuVO.getSord())) {
			sord = menuVO.getSord();
		} else {
			menuVO.setSord(sord);
		}

		int firstIndex = pageSize * pageIndex - pageSize;
		int lastIndex = firstIndex + pageSize;
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(pageSize);
		paginationInfo.setPageSize(menuVO.getPageSize());

		menuVO.setFirstIndex(firstIndex);
		menuVO.setLastIndex(lastIndex);
		menuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		menuVO.setUsePage(true);

		List<MenuVO> items = menuService.selectMenuList(menuVO);
		int total_count = menuService.selectMenuListTotalCount(menuVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) pageSize);
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", pageIndex);
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	@RequestMapping(value="/api/menu/selectMenuNmList.do")
    public void selectMenuNmList(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> req = requestToHashMap(request);

		String param =  req.get("param");

    	MenuVO mv = new MenuVO();

    	mv.setSYS_CODE(param);

        StringBuffer xmlSb = new StringBuffer();
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
        xmlSb.append(xmlHeader);

        List xmlData = menuService.selectSysCodeList(mv);

        if( xmlData!=null) {
        	xmlSb.append("<code>");

        	for (int i = 0; i < xmlData.size(); i++) {
        		mv = (MenuVO) xmlData.get(i);
        		xmlSb.append("<item>");
        		xmlSb.append("<code_value>"+mv.getMENU_ID()+"</code_value>");
        		xmlSb.append("<code_name>"+mv.getMENU_NM()+"</code_name>");
        		xmlSb.append("</item>");
			}
        	xmlSb.append("</code>");
        }

        response.setContentType("application/xml");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print( xmlSb.toString() );
    }

	@RequestMapping(value = {  "/api/menu/updateMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MenuVO updateClCodeRest(@RequestBody MenuVO menuVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(menuVO);
		menuService.updateMenuCode(menuVO);

		//스프링 시큐리티 url&role 재로딩
		reloadFilter();

		menuVO.setResultSuccess("true");
		menuVO.setResultMSG("정상 수정되었습니다.");
		return menuVO;
	}

	@RequestMapping(value = {   "/api/menu/deleteMenu.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MenuVO deleteMenu(@RequestBody MenuVO menuVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(menuVO);
		menuService.deleteMenu(menuVO);

		//스프링 시큐리티 url&role 재로딩
		reloadFilter();

		menuVO.setResultSuccess("true");
		menuVO.setResultMSG("정상 삭제되었습니다.");

		return menuVO;
	}


	/**
	 * 통합정보조회 페이지 조회
	 * @author    : JOY
	 * @date      : 2017. 11. 6.
	 *
	 * @return    : "/topmenu/integratedView"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/topmenu/selectIntegratedView.do" })
	public String selectIntegratedView(@ModelAttribute("searchVO") MenuVO menuVO, ModelMap model, HttpServletRequest request) throws Exception {

		return "/topmenu/integratedView";

	}

	/**
     * 테마지도 페이지 조회
     * @author    : JOY
     * @date      : 2017. 11. 6.
     *
     * @return    : "/topmenu/integratedView"
     * @exception : Exception
     */
    @RequestMapping(value = { "/topmenu/selectThemeMap.do" })
    public String selectThemeMap(@ModelAttribute("searchVO") MenuVO menuVO, ModelMap model, HttpServletRequest request) throws Exception {

        return "/topmenu/themeMap";

    }


/* goodmap.jsp 로 합침
	*//**
	 * 포트홀 신고정보 공간검색 페이지 조회
	 * @author    : YYK
	 * @date      : 2018. 02. 19.
	 *
	 * @return    : "/pothole/sttemntMapView"
	 * @exception : Exception
	 *//*
	@RequestMapping(value = { "pothole/map/selectSttemntMapView.do" })
	public String selectSttemntMapView(@ModelAttribute("searchVO") MenuVO menuVO, ModelMap model, HttpServletRequest request) throws Exception {

		return "/pothole/map/sttemntMapView";
		//return "/topmenu/integratedView";
	}
*/



}