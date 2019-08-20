

package kr.go.gg.gpms.pavfrmulaidx.web;




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
import kr.go.gg.gpms.pavfrmulaidx.service.PavFrmulaIdxService;
import kr.go.gg.gpms.pavfrmulaidx.service.model.PavFrmulaIdxVO;



/**
 * @Class Name : PavFrmulaIdxController.java
 * @Description : PavFrmulaIdx Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("pavFrmulaIdxController")
public class PavFrmulaIdxController {

	@Resource(name = "pavFrmulaIdxService")
	private PavFrmulaIdxService pavFrmulaIdxService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PavFrmulaIdxController.class);

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmulaidx/selectPavFrmulaIdxList.do" })
	public String selectPavFrmulaIdxList(PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model) throws Exception {
		pavFrmulaIdxVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaIdxVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaIdxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaIdxVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaIdxVO.getPageSize());

		pavFrmulaIdxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaIdxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaIdxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaIdxVO> items = pavFrmulaIdxService.selectPavFrmulaIdxList(pavFrmulaIdxVO);
		model.addAttribute("items", items);

		int totCnt = pavFrmulaIdxService.selectPavFrmulaIdxListTotalCount(pavFrmulaIdxVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavfrmulaidx/PavFrmulaIdxList" ;
	}
	
	
	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavfrmulaidx/selectPavFrmulaIdxList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavFrmulaIdxVO> selectPavFrmulaIdxListRest(@RequestBody PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model, HttpSession session) throws Exception {
		pavFrmulaIdxVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaIdxVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaIdxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaIdxVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaIdxVO.getPageSize());

		pavFrmulaIdxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaIdxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaIdxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaIdxVO> items = pavFrmulaIdxService.selectPavFrmulaIdxList(pavFrmulaIdxVO);
		return items;
	}
	
	
	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmulaidx/selectPavFrmulaIdxListPage.do" })
	public String selectPavFrmulaIdxListPage(PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model) throws Exception {
		pavFrmulaIdxVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaIdxVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaIdxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaIdxVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaIdxVO.getPageSize());

		pavFrmulaIdxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaIdxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaIdxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaIdxVO> items = pavFrmulaIdxService.selectPavFrmulaIdxList(pavFrmulaIdxVO);
		model.addAttribute("items", items);

		int totCnt = pavFrmulaIdxService.selectPavFrmulaIdxListTotalCount(pavFrmulaIdxVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavfrmulaidx/PavFrmulaIdxList" ;
	}
	
	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/pavfrmulaidx/selectPavFrmulaIdxListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<PavFrmulaIdxVO>  selectPavFrmulaIdxListPageRest(@RequestBody  PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model, HttpSession session) throws Exception {
		pavFrmulaIdxVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaIdxVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaIdxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaIdxVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaIdxVO.getPageSize());

		pavFrmulaIdxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaIdxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaIdxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaIdxVO> items = pavFrmulaIdxService.selectPavFrmulaIdxList(pavFrmulaIdxVO);
		return items;
	}	
 
	
	
	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 상세를 조회한다.
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmulaidx/selectPavFrmulaIdx.do"  })
	public String selectPavFrmulaIdx(@ModelAttribute("searchVO") PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model) throws Exception {
	
		model.addAttribute("pavFrmulaIdxVO", pavFrmulaIdxService.selectPavFrmulaIdx(pavFrmulaIdxVO));
		return "/manage/pavfrmulaidx/PavFrmulaIdxView";
	}
	
	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 상세를 조회한다.
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return "/manage/pavfrmulaidx/PavFrmulaIdxView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavfrmulaidx/selectPavFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaIdxVO selectPavFrmulaIdxRest(@RequestBody  PavFrmulaIdxVO pavFrmulaIdxVO, ModelMap model, HttpSession session) throws Exception {
		PavFrmulaIdxVO pavFrmulaIdxVOOne = pavFrmulaIdxService.selectPavFrmulaIdx(pavFrmulaIdxVO);
		return pavFrmulaIdxVOOne;
	}

	

	@RequestMapping(value = { "/manage/pavfrmulaidx/addPavFrmulaIdxView.do" })
	public String addPavFrmulaIdxView(@ModelAttribute("searchVO") PavFrmulaIdxVO pavFrmulaIdxVO, Model model) throws Exception {
		model.addAttribute("pavFrmulaIdxVO", new PavFrmulaIdxVO());
		return "/manage/pavfrmulaidx/PavFrmulaIdxRegist";
	}

	@RequestMapping(value = { "/manage/pavfrmulaidx/addPavFrmulaIdx.do"  })
	public String addPavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		pavFrmulaIdxService.insertPavFrmulaIdx(pavFrmulaIdxVO);
		return "redirect:/manage/pavfrmulaidx/selectPavFrmulaIdxList.do";
	}
	
	@RequestMapping(value = {  "/api/pavfrmulaidx/addPavFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaIdxVO addPavFrmulaIdxRest(@RequestBody PavFrmulaIdxVO pavFrmulaIdxVO, HttpSession session) throws Exception {
		pavFrmulaIdxService.insertPavFrmulaIdx(pavFrmulaIdxVO);
		return pavFrmulaIdxVO;
	}
	

	@RequestMapping(value = { "/manage/pavfrmulaidx/updatePavFrmulaIdxView.do" })
	public String updatePavFrmulaIdxView(@ModelAttribute("searchVO") PavFrmulaIdxVO pavFrmulaIdxVO, Model model) throws Exception {
		model.addAttribute("pavFrmulaIdxVO", pavFrmulaIdxService.selectPavFrmulaIdx(pavFrmulaIdxVO));
		return "/manage/pavfrmulaidx/PavFrmulaIdxUpdate";
	}

	@RequestMapping(value = { "/manage/pavfrmulaidx/updatePavFrmulaIdx.do" })
	public String updatePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		pavFrmulaIdxService.updatePavFrmulaIdx(pavFrmulaIdxVO);
		return "redirect:/manage/pavfrmulaidx/selectPavFrmulaIdxList.do";
	}
	
	@RequestMapping(value = {  "/api/pavfrmulaidx/updatePavFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaIdxVO updatePavFrmulaIdxRest(@RequestBody PavFrmulaIdxVO pavFrmulaIdxVO, HttpSession session) throws Exception {
		pavFrmulaIdxService.updatePavFrmulaIdx(pavFrmulaIdxVO);
		return pavFrmulaIdxVO;
	}

	@RequestMapping(value = { "/manage/pavfrmulaidx/deletePavFrmulaIdx.do" })
	public String deletePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		pavFrmulaIdxService.deletePavFrmulaIdx(pavFrmulaIdxVO);
		return "redirect:/manage/pavfrmulaidx/selectPavFrmulaIdxList.do";
	}
	
	@RequestMapping(value = {   "/api/pavfrmulaidx/deletePavFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaIdxVO deletePavFrmulaIdxRest(@RequestBody PavFrmulaIdxVO pavFrmulaIdxVO, HttpSession session) throws Exception {
		pavFrmulaIdxService.deletePavFrmulaIdx(pavFrmulaIdxVO);
		return pavFrmulaIdxVO;
	}

}
