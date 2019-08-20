

package kr.go.gg.gpms.cntrwksctnhist.web;




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
import kr.go.gg.gpms.cntrwksctnhist.service.CntrwkSctnHistService;
import kr.go.gg.gpms.cntrwksctnhist.service.model.CntrwkSctnHistVO;



/**
 * @Class Name : CntrwkSctnHistController.java
 * @Description : CntrwkSctnHist Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("cntrwkSctnHistController")
public class CntrwkSctnHistController {

	@Resource(name = "cntrwkSctnHistService")
	private CntrwkSctnHistService cntrwkSctnHistService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CntrwkSctnHistController.class);

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다. (pageing)
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrwksctnhist/selectCntrwkSctnHistList.do" })
	public String selectCntrwkSctnHistList(CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model) throws Exception {
		cntrwkSctnHistVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkSctnHistVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkSctnHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkSctnHistVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkSctnHistVO.getPageSize());

		cntrwkSctnHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkSctnHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkSctnHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkSctnHistVO> items = cntrwkSctnHistService.selectCntrwkSctnHistList(cntrwkSctnHistVO);
		model.addAttribute("items", items);

		int totCnt = cntrwkSctnHistService.selectCntrwkSctnHistListTotalCount(cntrwkSctnHistVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/cntrwksctnhist/CntrwkSctnHistList" ;
	}
	
	
	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다. (pageing)
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwksctnhist/selectCntrwkSctnHistList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CntrwkSctnHistVO> selectCntrwkSctnHistListRest(@RequestBody CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model, HttpSession session) throws Exception {
		cntrwkSctnHistVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkSctnHistVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkSctnHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkSctnHistVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkSctnHistVO.getPageSize());

		cntrwkSctnHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkSctnHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkSctnHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkSctnHistVO> items = cntrwkSctnHistService.selectCntrwkSctnHistList(cntrwkSctnHistVO);
		return items;
	}
	
	
	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다. (pageing)
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrwksctnhist/selectCntrwkSctnHistListPage.do" })
	public String selectCntrwkSctnHistListPage(CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model) throws Exception {
		cntrwkSctnHistVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkSctnHistVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkSctnHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkSctnHistVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkSctnHistVO.getPageSize());

		cntrwkSctnHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkSctnHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkSctnHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkSctnHistVO> items = cntrwkSctnHistService.selectCntrwkSctnHistList(cntrwkSctnHistVO);
		model.addAttribute("items", items);

		int totCnt = cntrwkSctnHistService.selectCntrwkSctnHistListTotalCount(cntrwkSctnHistVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/cntrwksctnhist/CntrwkSctnHistList" ;
	}
	
	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다. (pageing)
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cntrwksctnhist/selectCntrwkSctnHistListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CntrwkSctnHistVO>  selectCntrwkSctnHistListPageRest(@RequestBody  CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model, HttpSession session) throws Exception {
		cntrwkSctnHistVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkSctnHistVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkSctnHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkSctnHistVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkSctnHistVO.getPageSize());

		cntrwkSctnHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkSctnHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkSctnHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkSctnHistVO> items = cntrwkSctnHistService.selectCntrwkSctnHistList(cntrwkSctnHistVO);
		return items;
	}	
 
	
	
	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 상세를 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrwksctnhist/selectCntrwkSctnHist.do"  })
	public String selectCntrwkSctnHist(@ModelAttribute("searchVO") CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model) throws Exception {
	
		model.addAttribute("cntrwkSctnHistVO", cntrwkSctnHistService.selectCntrwkSctnHist(cntrwkSctnHistVO));
		return "/manage/cntrwksctnhist/CntrwkSctnHistView";
	}
	
	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 상세를 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return "/manage/cntrwksctnhist/CntrwkSctnHistView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwksctnhist/selectCntrwkSctnHist.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkSctnHistVO selectCntrwkSctnHistRest(@RequestBody  CntrwkSctnHistVO cntrwkSctnHistVO, ModelMap model, HttpSession session) throws Exception {
		CntrwkSctnHistVO cntrwkSctnHistVOOne = cntrwkSctnHistService.selectCntrwkSctnHist(cntrwkSctnHistVO);
		return cntrwkSctnHistVOOne;
	}

	

	@RequestMapping(value = { "/manage/cntrwksctnhist/addCntrwkSctnHistView.do" })
	public String addCntrwkSctnHistView(@ModelAttribute("searchVO") CntrwkSctnHistVO cntrwkSctnHistVO, Model model) throws Exception {
		model.addAttribute("cntrwkSctnHistVO", new CntrwkSctnHistVO());
		return "/manage/cntrwksctnhist/CntrwkSctnHistRegist";
	}

	@RequestMapping(value = { "/manage/cntrwksctnhist/addCntrwkSctnHist.do"  })
	public String addCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		cntrwkSctnHistService.insertCntrwkSctnHist(cntrwkSctnHistVO);
		return "redirect:/manage/cntrwksctnhist/selectCntrwkSctnHistList.do";
	}
	
	@RequestMapping(value = {  "/api/cntrwksctnhist/addCntrwkSctnHist.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkSctnHistVO addCntrwkSctnHistRest(@RequestBody CntrwkSctnHistVO cntrwkSctnHistVO, HttpSession session) throws Exception {
		cntrwkSctnHistService.insertCntrwkSctnHist(cntrwkSctnHistVO);
		return cntrwkSctnHistVO;
	}
	

	@RequestMapping(value = { "/manage/cntrwksctnhist/updateCntrwkSctnHistView.do" })
	public String updateCntrwkSctnHistView(@ModelAttribute("searchVO") CntrwkSctnHistVO cntrwkSctnHistVO, Model model) throws Exception {
		model.addAttribute("cntrwkSctnHistVO", cntrwkSctnHistService.selectCntrwkSctnHist(cntrwkSctnHistVO));
		return "/manage/cntrwksctnhist/CntrwkSctnHistUpdate";
	}

	@RequestMapping(value = { "/manage/cntrwksctnhist/updateCntrwkSctnHist.do" })
	public String updateCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		cntrwkSctnHistService.updateCntrwkSctnHist(cntrwkSctnHistVO);
		return "redirect:/manage/cntrwksctnhist/selectCntrwkSctnHistList.do";
	}
	
	@RequestMapping(value = {  "/api/cntrwksctnhist/updateCntrwkSctnHist.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkSctnHistVO updateCntrwkSctnHistRest(@RequestBody CntrwkSctnHistVO cntrwkSctnHistVO, HttpSession session) throws Exception {
		cntrwkSctnHistService.updateCntrwkSctnHist(cntrwkSctnHistVO);
		return cntrwkSctnHistVO;
	}

	@RequestMapping(value = { "/manage/cntrwksctnhist/deleteCntrwkSctnHist.do" })
	public String deleteCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		cntrwkSctnHistService.deleteCntrwkSctnHist(cntrwkSctnHistVO);
		return "redirect:/manage/cntrwksctnhist/selectCntrwkSctnHistList.do";
	}
	
	@RequestMapping(value = {   "/api/cntrwksctnhist/deleteCntrwkSctnHist.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkSctnHistVO deleteCntrwkSctnHistRest(@RequestBody CntrwkSctnHistVO cntrwkSctnHistVO, HttpSession session) throws Exception {
		cntrwkSctnHistService.deleteCntrwkSctnHist(cntrwkSctnHistVO);
		return cntrwkSctnHistVO;
	}

}
