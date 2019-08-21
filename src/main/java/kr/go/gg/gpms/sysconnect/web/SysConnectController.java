

package kr.go.gg.gpms.sysconnect.web;




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
import kr.go.gg.gpms.sysconnect.service.SysConnectService;
import kr.go.gg.gpms.sysconnect.service.model.SysConnectVO;



/**
 * @Class Name : SysConnectController.java
 * @Description : SysConnect Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("sysConnectController")
public class SysConnectController {

	@Resource(name = "sysConnectService")
	private SysConnectService sysConnectService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysConnectController.class);

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다. (pageing)
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysconnect/selectSysConnectList.do" })
	public String selectSysConnectList(SysConnectVO sysConnectVO, ModelMap model) throws Exception {
		sysConnectVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysConnectVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysConnectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysConnectVO.getPageUnit());
		paginationInfo.setPageSize(sysConnectVO.getPageSize());

		sysConnectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysConnectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysConnectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysConnectVO> items = sysConnectService.selectSysConnectList(sysConnectVO);
		model.addAttribute("items", items);

		int totCnt = sysConnectService.selectSysConnectListTotalCount(sysConnectVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/sysconnect/SysConnectList" ;
	}
	
	
	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다. (pageing)
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/sysconnect/selectSysConnectList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SysConnectVO> selectSysConnectListRest(@RequestBody SysConnectVO sysConnectVO, ModelMap model, HttpSession session) throws Exception {
		sysConnectVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysConnectVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysConnectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysConnectVO.getPageUnit());
		paginationInfo.setPageSize(sysConnectVO.getPageSize());

		sysConnectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysConnectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysConnectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysConnectVO> items = sysConnectService.selectSysConnectList(sysConnectVO);
		return items;
	}
	
	
	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다. (pageing)
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysconnect/selectSysConnectListPage.do" })
	public String selectSysConnectListPage(SysConnectVO sysConnectVO, ModelMap model) throws Exception {
		sysConnectVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysConnectVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysConnectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysConnectVO.getPageUnit());
		paginationInfo.setPageSize(sysConnectVO.getPageSize());

		sysConnectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysConnectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysConnectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysConnectVO> items = sysConnectService.selectSysConnectList(sysConnectVO);
		model.addAttribute("items", items);

		int totCnt = sysConnectService.selectSysConnectListTotalCount(sysConnectVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/sysconnect/SysConnectList" ;
	}
	
	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다. (pageing)
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/sysconnect/selectSysConnectListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SysConnectVO>  selectSysConnectListPageRest(@RequestBody  SysConnectVO sysConnectVO, ModelMap model, HttpSession session) throws Exception {
		sysConnectVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysConnectVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysConnectVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysConnectVO.getPageUnit());
		paginationInfo.setPageSize(sysConnectVO.getPageSize());

		sysConnectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysConnectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysConnectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysConnectVO> items = sysConnectService.selectSysConnectList(sysConnectVO);
		return items;
	}	
 
	
	
	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 상세를 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysconnect/selectSysConnect.do"  })
	public String selectSysConnect(@ModelAttribute("searchVO") SysConnectVO sysConnectVO, ModelMap model) throws Exception {
	
		model.addAttribute("sysConnectVO", sysConnectService.selectSysConnect(sysConnectVO));
		return "/manage/sysconnect/SysConnectView";
	}
	
	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 상세를 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return "/manage/sysconnect/SysConnectView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/sysconnect/selectSysConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SysConnectVO selectSysConnectRest(@RequestBody  SysConnectVO sysConnectVO, ModelMap model, HttpSession session) throws Exception {
		SysConnectVO sysConnectVOOne = sysConnectService.selectSysConnect(sysConnectVO);
		return sysConnectVOOne;
	}

	

	@RequestMapping(value = { "/manage/sysconnect/addSysConnectView.do" })
	public String addSysConnectView(@ModelAttribute("searchVO") SysConnectVO sysConnectVO, Model model) throws Exception {
		model.addAttribute("sysConnectVO", new SysConnectVO());
		return "/manage/sysconnect/SysConnectRegist";
	}

	@RequestMapping(value = { "/manage/sysconnect/addSysConnect.do"  })
	public String addSysConnect(SysConnectVO sysConnectVO) throws Exception {
		sysConnectService.insertSysConnect(sysConnectVO);
		return "redirect:/manage/sysconnect/selectSysConnectList.do";
	}
	
	@RequestMapping(value = {  "/api/sysconnect/addSysConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SysConnectVO addSysConnectRest(@RequestBody SysConnectVO sysConnectVO, HttpSession session) throws Exception {
		sysConnectService.insertSysConnect(sysConnectVO);
		return sysConnectVO;
	}
	

	@RequestMapping(value = { "/manage/sysconnect/updateSysConnectView.do" })
	public String updateSysConnectView(@ModelAttribute("searchVO") SysConnectVO sysConnectVO, Model model) throws Exception {
		model.addAttribute("sysConnectVO", sysConnectService.selectSysConnect(sysConnectVO));
		return "/manage/sysconnect/SysConnectUpdate";
	}

	@RequestMapping(value = { "/manage/sysconnect/updateSysConnect.do" })
	public String updateSysConnect(SysConnectVO sysConnectVO) throws Exception {
		sysConnectService.updateSysConnect(sysConnectVO);
		return "redirect:/manage/sysconnect/selectSysConnectList.do";
	}
	
	@RequestMapping(value = {  "/api/sysconnect/updateSysConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SysConnectVO updateSysConnectRest(@RequestBody SysConnectVO sysConnectVO, HttpSession session) throws Exception {
		sysConnectService.updateSysConnect(sysConnectVO);
		return sysConnectVO;
	}

	@RequestMapping(value = { "/manage/sysconnect/deleteSysConnect.do" })
	public String deleteSysConnect(SysConnectVO sysConnectVO) throws Exception {
		sysConnectService.deleteSysConnect(sysConnectVO);
		return "redirect:/manage/sysconnect/selectSysConnectList.do";
	}
	
	@RequestMapping(value = {   "/api/sysconnect/deleteSysConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SysConnectVO deleteSysConnectRest(@RequestBody SysConnectVO sysConnectVO, HttpSession session) throws Exception {
		sysConnectService.deleteSysConnect(sysConnectVO);
		return sysConnectVO;
	}

}
