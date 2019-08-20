

package kr.go.gg.gpms.smdtagnlsttus.web;




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
import kr.go.gg.gpms.smdtagnlsttus.service.SmDtaGnlSttusService;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;



/**
 * @Class Name : SmDtaGnlSttusController.java
 * @Description : SmDtaGnlSttus Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("smDtaGnlSttusController")
public class SmDtaGnlSttusController {

	@Resource(name = "smDtaGnlSttusService")
	private SmDtaGnlSttusService smDtaGnlSttusService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmDtaGnlSttusController.class);

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다. (pageing)
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/smdtagnlsttus/selectSmDtaGnlSttusList.do" })
	public String selectSmDtaGnlSttusList(SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model) throws Exception {
		smDtaGnlSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		smDtaGnlSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaGnlSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smDtaGnlSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaGnlSttusVO.getPageSize());

		smDtaGnlSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaGnlSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaGnlSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SmDtaGnlSttusVO> items = smDtaGnlSttusService.selectSmDtaGnlSttusList(smDtaGnlSttusVO);
		model.addAttribute("items", items);

		int totCnt = smDtaGnlSttusService.selectSmDtaGnlSttusListTotalCount(smDtaGnlSttusVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/smdtagnlsttus/SmDtaGnlSttusList" ;
	}
	
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다. (pageing)
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smdtagnlsttus/selectSmDtaGnlSttusList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SmDtaGnlSttusVO> selectSmDtaGnlSttusListRest(@RequestBody SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model, HttpSession session) throws Exception {
		smDtaGnlSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		smDtaGnlSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaGnlSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smDtaGnlSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaGnlSttusVO.getPageSize());

		smDtaGnlSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaGnlSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaGnlSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SmDtaGnlSttusVO> items = smDtaGnlSttusService.selectSmDtaGnlSttusList(smDtaGnlSttusVO);
		return items;
	}
	
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다. (pageing)
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/smdtagnlsttus/selectSmDtaGnlSttusListPage.do" })
	public String selectSmDtaGnlSttusListPage(SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model) throws Exception {
		smDtaGnlSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		smDtaGnlSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaGnlSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smDtaGnlSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaGnlSttusVO.getPageSize());

		smDtaGnlSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaGnlSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaGnlSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SmDtaGnlSttusVO> items = smDtaGnlSttusService.selectSmDtaGnlSttusList(smDtaGnlSttusVO);
		model.addAttribute("items", items);

		int totCnt = smDtaGnlSttusService.selectSmDtaGnlSttusListTotalCount(smDtaGnlSttusVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/smdtagnlsttus/SmDtaGnlSttusList" ;
	}
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다. (pageing)
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/smdtagnlsttus/selectSmDtaGnlSttusListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SmDtaGnlSttusVO>  selectSmDtaGnlSttusListPageRest(@RequestBody  SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model, HttpSession session) throws Exception {
		smDtaGnlSttusVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		smDtaGnlSttusVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaGnlSttusVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smDtaGnlSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaGnlSttusVO.getPageSize());

		smDtaGnlSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaGnlSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaGnlSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SmDtaGnlSttusVO> items = smDtaGnlSttusService.selectSmDtaGnlSttusList(smDtaGnlSttusVO);
		return items;
	}	
 
	
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 상세를 조회한다.
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/smdtagnlsttus/selectSmDtaGnlSttus.do"  })
	public String selectSmDtaGnlSttus(@ModelAttribute("searchVO") SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model) throws Exception {
	
		model.addAttribute("smDtaGnlSttusVO", smDtaGnlSttusService.selectSmDtaGnlSttus(smDtaGnlSttusVO));
		return "/manage/smdtagnlsttus/SmDtaGnlSttusView";
	}
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 상세를 조회한다.
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return "/manage/smdtagnlsttus/SmDtaGnlSttusView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smdtagnlsttus/selectSmDtaGnlSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaGnlSttusVO selectSmDtaGnlSttusRest(@RequestBody  SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model, HttpSession session) throws Exception {
		SmDtaGnlSttusVO smDtaGnlSttusVOOne = smDtaGnlSttusService.selectSmDtaGnlSttus(smDtaGnlSttusVO);
		return smDtaGnlSttusVOOne;
	}

	

	@RequestMapping(value = { "/manage/smdtagnlsttus/addSmDtaGnlSttusView.do" })
	public String addSmDtaGnlSttusView(@ModelAttribute("searchVO") SmDtaGnlSttusVO smDtaGnlSttusVO, Model model) throws Exception {
		model.addAttribute("smDtaGnlSttusVO", new SmDtaGnlSttusVO());
		return "/manage/smdtagnlsttus/SmDtaGnlSttusRegist";
	}

	@RequestMapping(value = { "/manage/smdtagnlsttus/addSmDtaGnlSttus.do"  })
	public String addSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		smDtaGnlSttusService.insertSmDtaGnlSttus(smDtaGnlSttusVO);
		return "redirect:/manage/smdtagnlsttus/selectSmDtaGnlSttusList.do";
	}
	
	@RequestMapping(value = {  "/api/smdtagnlsttus/addSmDtaGnlSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaGnlSttusVO addSmDtaGnlSttusRest(@RequestBody SmDtaGnlSttusVO smDtaGnlSttusVO, HttpSession session) throws Exception {
		smDtaGnlSttusService.insertSmDtaGnlSttus(smDtaGnlSttusVO);
		return smDtaGnlSttusVO;
	}
	

	@RequestMapping(value = { "/manage/smdtagnlsttus/updateSmDtaGnlSttusView.do" })
	public String updateSmDtaGnlSttusView(@ModelAttribute("searchVO") SmDtaGnlSttusVO smDtaGnlSttusVO, Model model) throws Exception {
		model.addAttribute("smDtaGnlSttusVO", smDtaGnlSttusService.selectSmDtaGnlSttus(smDtaGnlSttusVO));
		return "/manage/smdtagnlsttus/SmDtaGnlSttusUpdate";
	}

	@RequestMapping(value = { "/manage/smdtagnlsttus/updateSmDtaGnlSttus.do" })
	public String updateSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		smDtaGnlSttusService.updateSmDtaGnlSttus(smDtaGnlSttusVO);
		return "redirect:/manage/smdtagnlsttus/selectSmDtaGnlSttusList.do";
	}
	
	@RequestMapping(value = {  "/api/smdtagnlsttus/updateSmDtaGnlSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaGnlSttusVO updateSmDtaGnlSttusRest(@RequestBody SmDtaGnlSttusVO smDtaGnlSttusVO, HttpSession session) throws Exception {
		smDtaGnlSttusService.updateSmDtaGnlSttus(smDtaGnlSttusVO);
		return smDtaGnlSttusVO;
	}

	@RequestMapping(value = { "/manage/smdtagnlsttus/deleteSmDtaGnlSttus.do" })
	public String deleteSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		smDtaGnlSttusService.deleteSmDtaGnlSttus(smDtaGnlSttusVO);
		return "redirect:/manage/smdtagnlsttus/selectSmDtaGnlSttusList.do";
	}
	
	@RequestMapping(value = {   "/api/smdtagnlsttus/deleteSmDtaGnlSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaGnlSttusVO deleteSmDtaGnlSttusRest(@RequestBody SmDtaGnlSttusVO smDtaGnlSttusVO, HttpSession session) throws Exception {
		smDtaGnlSttusService.deleteSmDtaGnlSttus(smDtaGnlSttusVO);
		return smDtaGnlSttusVO;
	}
	

}
