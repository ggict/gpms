

package kr.go.gg.gpms.srvydtasttus.web;




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
import kr.go.gg.gpms.srvydtasttus.service.SrvyDtaSttusService;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;



/**
 * @Class Name : SrvyDtaSttusController.java
 * @Description : SrvyDtaSttus Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("srvyDtaSttusController")
public class SrvyDtaSttusController {

	@Resource(name = "srvyDtaSttusService")
	private SrvyDtaSttusService srvyDtaSttusService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyDtaSttusController.class);

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다. (pageing)
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtasttus/selectSrvyDtaSttusList.do" })
	public String selectSrvyDtaSttusList(SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model) throws Exception {
		srvyDtaSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaSttusVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaSttusVO.getPageSize());

		srvyDtaSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaSttusVO> items = srvyDtaSttusService.selectSrvyDtaSttusList(srvyDtaSttusVO);
		model.addAttribute("items", items);

		int totCnt = srvyDtaSttusService.selectSrvyDtaSttusListTotalCount(srvyDtaSttusVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/srvydtasttus/SrvyDtaSttusList" ;
	}
	
	
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다. (pageing)
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtasttus/selectSrvyDtaSttusList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SrvyDtaSttusVO> selectSrvyDtaSttusListRest(@RequestBody SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaSttusVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaSttusVO.getPageSize());

		srvyDtaSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaSttusVO> items = srvyDtaSttusService.selectSrvyDtaSttusList(srvyDtaSttusVO);
		return items;
	}
	
	
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다. (pageing)
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtasttus/selectSrvyDtaSttusListPage.do" })
	public String selectSrvyDtaSttusListPage(SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model) throws Exception {
		srvyDtaSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaSttusVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaSttusVO.getPageSize());

		srvyDtaSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaSttusVO> items = srvyDtaSttusService.selectSrvyDtaSttusList(srvyDtaSttusVO);
		model.addAttribute("items", items);

		int totCnt = srvyDtaSttusService.selectSrvyDtaSttusListTotalCount(srvyDtaSttusVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/srvydtasttus/SrvyDtaSttusList" ;
	}
	
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다. (pageing)
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/srvydtasttus/selectSrvyDtaSttusListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SrvyDtaSttusVO>  selectSrvyDtaSttusListPageRest(@RequestBody  SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaSttusVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaSttusVO.getPageSize());

		srvyDtaSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaSttusVO> items = srvyDtaSttusService.selectSrvyDtaSttusList(srvyDtaSttusVO);
		return items;
	}	
 
	
	
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 상세를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtasttus/selectSrvyDtaSttus.do"  })
	public String selectSrvyDtaSttus(@ModelAttribute("searchVO") SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model) throws Exception {
	
		model.addAttribute("srvyDtaSttusVO", srvyDtaSttusService.selectSrvyDtaSttus(srvyDtaSttusVO));
		return "/manage/srvydtasttus/SrvyDtaSttusView";
	}
	
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 상세를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return "/manage/srvydtasttus/SrvyDtaSttusView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtasttus/selectSrvyDtaSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaSttusVO selectSrvyDtaSttusRest(@RequestBody  SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model, HttpSession session) throws Exception {
		SrvyDtaSttusVO srvyDtaSttusVOOne = srvyDtaSttusService.selectSrvyDtaSttus(srvyDtaSttusVO);
		return srvyDtaSttusVOOne;
	}

	

	@RequestMapping(value = { "/manage/srvydtasttus/addSrvyDtaSttusView.do" })
	public String addSrvyDtaSttusView(@ModelAttribute("searchVO") SrvyDtaSttusVO srvyDtaSttusVO, Model model) throws Exception {
		model.addAttribute("srvyDtaSttusVO", new SrvyDtaSttusVO());
		return "/manage/srvydtasttus/SrvyDtaSttusRegist";
	}

	@RequestMapping(value = { "/manage/srvydtasttus/addSrvyDtaSttus.do"  })
	public String addSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		srvyDtaSttusService.insertSrvyDtaSttus(srvyDtaSttusVO);
		return "redirect:/manage/srvydtasttus/selectSrvyDtaSttusList.do";
	}
	
	@RequestMapping(value = {  "/api/srvydtasttus/addSrvyDtaSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaSttusVO addSrvyDtaSttusRest(@RequestBody SrvyDtaSttusVO srvyDtaSttusVO, HttpSession session) throws Exception {
		srvyDtaSttusService.insertSrvyDtaSttus(srvyDtaSttusVO);
		return srvyDtaSttusVO;
	}
	

	@RequestMapping(value = { "/manage/srvydtasttus/updateSrvyDtaSttusView.do" })
	public String updateSrvyDtaSttusView(@ModelAttribute("searchVO") SrvyDtaSttusVO srvyDtaSttusVO, Model model) throws Exception {
		model.addAttribute("srvyDtaSttusVO", srvyDtaSttusService.selectSrvyDtaSttus(srvyDtaSttusVO));
		return "/manage/srvydtasttus/SrvyDtaSttusUpdate";
	}

	@RequestMapping(value = { "/manage/srvydtasttus/updateSrvyDtaSttus.do" })
	public String updateSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		srvyDtaSttusService.updateSrvyDtaSttus(srvyDtaSttusVO);
		return "redirect:/manage/srvydtasttus/selectSrvyDtaSttusList.do";
	}
	
	@RequestMapping(value = {  "/api/srvydtasttus/updateSrvyDtaSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaSttusVO updateSrvyDtaSttusRest(@RequestBody SrvyDtaSttusVO srvyDtaSttusVO, HttpSession session) throws Exception {
		srvyDtaSttusService.updateSrvyDtaSttus(srvyDtaSttusVO);
		return srvyDtaSttusVO;
	}

	@RequestMapping(value = { "/manage/srvydtasttus/deleteSrvyDtaSttus.do" })
	public String deleteSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		srvyDtaSttusService.deleteSrvyDtaSttus(srvyDtaSttusVO);
		return "redirect:/manage/srvydtasttus/selectSrvyDtaSttusList.do";
	}
	
	@RequestMapping(value = {   "/api/srvydtasttus/deleteSrvyDtaSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaSttusVO deleteSrvyDtaSttusRest(@RequestBody SrvyDtaSttusVO srvyDtaSttusVO, HttpSession session) throws Exception {
		srvyDtaSttusService.deleteSrvyDtaSttus(srvyDtaSttusVO);
		return srvyDtaSttusVO;
	}

}
