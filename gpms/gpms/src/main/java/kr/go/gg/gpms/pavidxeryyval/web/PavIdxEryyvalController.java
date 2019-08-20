

package kr.go.gg.gpms.pavidxeryyval.web;




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
import kr.go.gg.gpms.pavidxeryyval.service.PavIdxEryyvalService;
import kr.go.gg.gpms.pavidxeryyval.service.model.PavIdxEryyvalVO;



/**
 * @Class Name : PavIdxEryyvalController.java
 * @Description : PavIdxEryyval Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("pavIdxEryyvalController")
public class PavIdxEryyvalController {

	@Resource(name = "pavIdxEryyvalService")
	private PavIdxEryyvalService pavIdxEryyvalService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PavIdxEryyvalController.class);

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다. (pageing)
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavidxeryyval/selectPavIdxEryyvalList.do" })
	public String selectPavIdxEryyvalList(PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model) throws Exception {
		pavIdxEryyvalVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavIdxEryyvalVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavIdxEryyvalVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavIdxEryyvalVO.getPageUnit());
		paginationInfo.setPageSize(pavIdxEryyvalVO.getPageSize());

		pavIdxEryyvalVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavIdxEryyvalVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavIdxEryyvalVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavIdxEryyvalVO> items = pavIdxEryyvalService.selectPavIdxEryyvalList(pavIdxEryyvalVO);
		model.addAttribute("items", items);

		int totCnt = pavIdxEryyvalService.selectPavIdxEryyvalListTotalCount(pavIdxEryyvalVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavidxeryyval/PavIdxEryyvalList" ;
	}
	
	
	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다. (pageing)
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavidxeryyval/selectPavIdxEryyvalList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavIdxEryyvalVO> selectPavIdxEryyvalListRest(@RequestBody PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model, HttpSession session) throws Exception {
		pavIdxEryyvalVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavIdxEryyvalVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavIdxEryyvalVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavIdxEryyvalVO.getPageUnit());
		paginationInfo.setPageSize(pavIdxEryyvalVO.getPageSize());

		pavIdxEryyvalVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavIdxEryyvalVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavIdxEryyvalVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavIdxEryyvalVO> items = pavIdxEryyvalService.selectPavIdxEryyvalList(pavIdxEryyvalVO);
		return items;
	}
	
	
	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다. (pageing)
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavidxeryyval/selectPavIdxEryyvalListPage.do" })
	public String selectPavIdxEryyvalListPage(PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model) throws Exception {
		pavIdxEryyvalVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavIdxEryyvalVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavIdxEryyvalVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavIdxEryyvalVO.getPageUnit());
		paginationInfo.setPageSize(pavIdxEryyvalVO.getPageSize());

		pavIdxEryyvalVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavIdxEryyvalVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavIdxEryyvalVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavIdxEryyvalVO> items = pavIdxEryyvalService.selectPavIdxEryyvalList(pavIdxEryyvalVO);
		model.addAttribute("items", items);

		int totCnt = pavIdxEryyvalService.selectPavIdxEryyvalListTotalCount(pavIdxEryyvalVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavidxeryyval/PavIdxEryyvalList" ;
	}
	
	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다. (pageing)
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/pavidxeryyval/selectPavIdxEryyvalListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<PavIdxEryyvalVO>  selectPavIdxEryyvalListPageRest(@RequestBody  PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model, HttpSession session) throws Exception {
		pavIdxEryyvalVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavIdxEryyvalVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavIdxEryyvalVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavIdxEryyvalVO.getPageUnit());
		paginationInfo.setPageSize(pavIdxEryyvalVO.getPageSize());

		pavIdxEryyvalVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavIdxEryyvalVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavIdxEryyvalVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavIdxEryyvalVO> items = pavIdxEryyvalService.selectPavIdxEryyvalList(pavIdxEryyvalVO);
		return items;
	}	
 
	
	
	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 상세를 조회한다.
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavidxeryyval/selectPavIdxEryyval.do"  })
	public String selectPavIdxEryyval(@ModelAttribute("searchVO") PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model) throws Exception {
	
		model.addAttribute("pavIdxEryyvalVO", pavIdxEryyvalService.selectPavIdxEryyval(pavIdxEryyvalVO));
		return "/manage/pavidxeryyval/PavIdxEryyvalView";
	}
	
	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 상세를 조회한다.
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return "/manage/pavidxeryyval/PavIdxEryyvalView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavidxeryyval/selectPavIdxEryyval.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavIdxEryyvalVO selectPavIdxEryyvalRest(@RequestBody  PavIdxEryyvalVO pavIdxEryyvalVO, ModelMap model, HttpSession session) throws Exception {
		PavIdxEryyvalVO pavIdxEryyvalVOOne = pavIdxEryyvalService.selectPavIdxEryyval(pavIdxEryyvalVO);
		return pavIdxEryyvalVOOne;
	}

	

	@RequestMapping(value = { "/manage/pavidxeryyval/addPavIdxEryyvalView.do" })
	public String addPavIdxEryyvalView(@ModelAttribute("searchVO") PavIdxEryyvalVO pavIdxEryyvalVO, Model model) throws Exception {
		model.addAttribute("pavIdxEryyvalVO", new PavIdxEryyvalVO());
		return "/manage/pavidxeryyval/PavIdxEryyvalRegist";
	}

	@RequestMapping(value = { "/manage/pavidxeryyval/addPavIdxEryyval.do"  })
	public String addPavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		pavIdxEryyvalService.insertPavIdxEryyval(pavIdxEryyvalVO);
		return "redirect:/manage/pavidxeryyval/selectPavIdxEryyvalList.do";
	}
	
	@RequestMapping(value = {  "/api/pavidxeryyval/addPavIdxEryyval.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavIdxEryyvalVO addPavIdxEryyvalRest(@RequestBody PavIdxEryyvalVO pavIdxEryyvalVO, HttpSession session) throws Exception {
		pavIdxEryyvalService.insertPavIdxEryyval(pavIdxEryyvalVO);
		return pavIdxEryyvalVO;
	}
	

	@RequestMapping(value = { "/manage/pavidxeryyval/updatePavIdxEryyvalView.do" })
	public String updatePavIdxEryyvalView(@ModelAttribute("searchVO") PavIdxEryyvalVO pavIdxEryyvalVO, Model model) throws Exception {
		model.addAttribute("pavIdxEryyvalVO", pavIdxEryyvalService.selectPavIdxEryyval(pavIdxEryyvalVO));
		return "/manage/pavidxeryyval/PavIdxEryyvalUpdate";
	}

	@RequestMapping(value = { "/manage/pavidxeryyval/updatePavIdxEryyval.do" })
	public String updatePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		pavIdxEryyvalService.updatePavIdxEryyval(pavIdxEryyvalVO);
		return "redirect:/manage/pavidxeryyval/selectPavIdxEryyvalList.do";
	}
	
	@RequestMapping(value = {  "/api/pavidxeryyval/updatePavIdxEryyval.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavIdxEryyvalVO updatePavIdxEryyvalRest(@RequestBody PavIdxEryyvalVO pavIdxEryyvalVO, HttpSession session) throws Exception {
		pavIdxEryyvalService.updatePavIdxEryyval(pavIdxEryyvalVO);
		return pavIdxEryyvalVO;
	}

	@RequestMapping(value = { "/manage/pavidxeryyval/deletePavIdxEryyval.do" })
	public String deletePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		pavIdxEryyvalService.deletePavIdxEryyval(pavIdxEryyvalVO);
		return "redirect:/manage/pavidxeryyval/selectPavIdxEryyvalList.do";
	}
	
	@RequestMapping(value = {   "/api/pavidxeryyval/deletePavIdxEryyval.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavIdxEryyvalVO deletePavIdxEryyvalRest(@RequestBody PavIdxEryyvalVO pavIdxEryyvalVO, HttpSession session) throws Exception {
		pavIdxEryyvalService.deletePavIdxEryyval(pavIdxEryyvalVO);
		return pavIdxEryyvalVO;
	}

}
