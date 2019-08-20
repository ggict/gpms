

package kr.go.gg.gpms.srvydtalog.web;




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
import kr.go.gg.gpms.srvydtalog.service.SrvyDtaLogService;
import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogVO;



/**
 * @Class Name : SrvyDtaLogController.java
 * @Description : SrvyDtaLog Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("srvyDtaLogController")
public class SrvyDtaLogController {

	@Resource(name = "srvyDtaLogService")
	private SrvyDtaLogService srvyDtaLogService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyDtaLogController.class);

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다. (pageing)
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtalog/selectSrvyDtaLogList.do" })
	public String selectSrvyDtaLogList(SrvyDtaLogVO srvyDtaLogVO, ModelMap model) throws Exception {
		srvyDtaLogVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaLogVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaLogVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaLogVO.getPageSize());

		srvyDtaLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaLogVO> items = srvyDtaLogService.selectSrvyDtaLogList(srvyDtaLogVO);
		model.addAttribute("items", items);

		int totCnt = srvyDtaLogService.selectSrvyDtaLogListTotalCount(srvyDtaLogVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/srvydtalog/SrvyDtaLogList" ;
	}
	
	
	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다. (pageing)
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtalog/selectSrvyDtaLogList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SrvyDtaLogVO> selectSrvyDtaLogListRest(@RequestBody SrvyDtaLogVO srvyDtaLogVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaLogVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaLogVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaLogVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaLogVO.getPageSize());

		srvyDtaLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaLogVO> items = srvyDtaLogService.selectSrvyDtaLogList(srvyDtaLogVO);
		return items;
	}
	
	
	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다. (pageing)
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtalog/selectSrvyDtaLogListPage.do" })
	public String selectSrvyDtaLogListPage(SrvyDtaLogVO srvyDtaLogVO, ModelMap model) throws Exception {
		srvyDtaLogVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaLogVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaLogVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaLogVO.getPageSize());

		srvyDtaLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaLogVO> items = srvyDtaLogService.selectSrvyDtaLogList(srvyDtaLogVO);
		model.addAttribute("items", items);

		int totCnt = srvyDtaLogService.selectSrvyDtaLogListTotalCount(srvyDtaLogVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/srvydtalog/SrvyDtaLogList" ;
	}
	
	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다. (pageing)
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/srvydtalog/selectSrvyDtaLogListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SrvyDtaLogVO>  selectSrvyDtaLogListPageRest(@RequestBody  SrvyDtaLogVO srvyDtaLogVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaLogVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaLogVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaLogVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaLogVO.getPageSize());

		srvyDtaLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaLogVO> items = srvyDtaLogService.selectSrvyDtaLogList(srvyDtaLogVO);
		return items;
	}	
 
	
	
	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 상세를 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/srvydtalog/selectSrvyDtaLog.do"  })
	public String selectSrvyDtaLog(@ModelAttribute("searchVO") SrvyDtaLogVO srvyDtaLogVO, ModelMap model) throws Exception {
	
		model.addAttribute("srvyDtaLogVO", srvyDtaLogService.selectSrvyDtaLog(srvyDtaLogVO));
		return "/manage/srvydtalog/SrvyDtaLogView";
	}
	
	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 상세를 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return "/manage/srvydtalog/SrvyDtaLogView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtalog/selectSrvyDtaLog.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaLogVO selectSrvyDtaLogRest(@RequestBody  SrvyDtaLogVO srvyDtaLogVO, ModelMap model, HttpSession session) throws Exception {
		SrvyDtaLogVO srvyDtaLogVOOne = srvyDtaLogService.selectSrvyDtaLog(srvyDtaLogVO);
		return srvyDtaLogVOOne;
	}

	

	@RequestMapping(value = { "/manage/srvydtalog/addSrvyDtaLogView.do" })
	public String addSrvyDtaLogView(@ModelAttribute("searchVO") SrvyDtaLogVO srvyDtaLogVO, Model model) throws Exception {
		model.addAttribute("srvyDtaLogVO", new SrvyDtaLogVO());
		return "/manage/srvydtalog/SrvyDtaLogRegist";
	}

	@RequestMapping(value = { "/manage/srvydtalog/addSrvyDtaLog.do"  })
	public String addSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		srvyDtaLogService.insertSrvyDtaLog(srvyDtaLogVO);
		return "redirect:/manage/srvydtalog/selectSrvyDtaLogList.do";
	}
	
	@RequestMapping(value = {  "/api/srvydtalog/addSrvyDtaLog.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaLogVO addSrvyDtaLogRest(@RequestBody SrvyDtaLogVO srvyDtaLogVO, HttpSession session) throws Exception {
		srvyDtaLogService.insertSrvyDtaLog(srvyDtaLogVO);
		return srvyDtaLogVO;
	}
	

	@RequestMapping(value = { "/manage/srvydtalog/updateSrvyDtaLogView.do" })
	public String updateSrvyDtaLogView(@ModelAttribute("searchVO") SrvyDtaLogVO srvyDtaLogVO, Model model) throws Exception {
		model.addAttribute("srvyDtaLogVO", srvyDtaLogService.selectSrvyDtaLog(srvyDtaLogVO));
		return "/manage/srvydtalog/SrvyDtaLogUpdate";
	}

	@RequestMapping(value = { "/manage/srvydtalog/updateSrvyDtaLog.do" })
	public String updateSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		srvyDtaLogService.updateSrvyDtaLog(srvyDtaLogVO);
		return "redirect:/manage/srvydtalog/selectSrvyDtaLogList.do";
	}
	
	@RequestMapping(value = {  "/api/srvydtalog/updateSrvyDtaLog.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaLogVO updateSrvyDtaLogRest(@RequestBody SrvyDtaLogVO srvyDtaLogVO, HttpSession session) throws Exception {
		srvyDtaLogService.updateSrvyDtaLog(srvyDtaLogVO);
		return srvyDtaLogVO;
	}

	@RequestMapping(value = { "/manage/srvydtalog/deleteSrvyDtaLog.do" })
	public String deleteSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		srvyDtaLogService.deleteSrvyDtaLog(srvyDtaLogVO);
		return "redirect:/manage/srvydtalog/selectSrvyDtaLogList.do";
	}
	
	@RequestMapping(value = {   "/api/srvydtalog/deleteSrvyDtaLog.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaLogVO deleteSrvyDtaLogRest(@RequestBody SrvyDtaLogVO srvyDtaLogVO, HttpSession session) throws Exception {
		srvyDtaLogService.deleteSrvyDtaLog(srvyDtaLogVO);
		return srvyDtaLogVO;
	}

}
