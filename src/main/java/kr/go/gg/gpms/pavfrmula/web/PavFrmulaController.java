

package kr.go.gg.gpms.pavfrmula.web;




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
import kr.go.gg.gpms.pavfrmula.service.PavFrmulaService;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;



/**
 * @Class Name : PavFrmulaController.java
 * @Description : PavFrmula Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("pavFrmulaController")
public class PavFrmulaController {

	@Resource(name = "pavFrmulaService")
	private PavFrmulaService pavFrmulaService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PavFrmulaController.class);

	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다. (pageing)
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmula/selectPavFrmulaList.do" })
	public String selectPavFrmulaList(PavFrmulaVO pavFrmulaVO, ModelMap model) throws Exception {
		pavFrmulaVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaVO.getPageSize());

		pavFrmulaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaVO> items = pavFrmulaService.selectPavFrmulaList(pavFrmulaVO);
		model.addAttribute("items", items);

		int totCnt = pavFrmulaService.selectPavFrmulaListTotalCount(pavFrmulaVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavfrmula/PavFrmulaList" ;
	}
	
	
	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다. (pageing)
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavfrmula/selectPavFrmulaList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavFrmulaVO> selectPavFrmulaListRest(@RequestBody PavFrmulaVO pavFrmulaVO, ModelMap model, HttpSession session) throws Exception {
		pavFrmulaVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaVO.getPageSize());

		pavFrmulaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaVO> items = pavFrmulaService.selectPavFrmulaList(pavFrmulaVO);
		return items;
	}
	
	
	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다. (pageing)
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmula/selectPavFrmulaListPage.do" })
	public String selectPavFrmulaListPage(PavFrmulaVO pavFrmulaVO, ModelMap model) throws Exception {
		pavFrmulaVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaVO.getPageSize());

		pavFrmulaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaVO> items = pavFrmulaService.selectPavFrmulaList(pavFrmulaVO);
		model.addAttribute("items", items);

		int totCnt = pavFrmulaService.selectPavFrmulaListTotalCount(pavFrmulaVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavfrmula/PavFrmulaList" ;
	}
	
	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다. (pageing)
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/pavfrmula/selectPavFrmulaListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<PavFrmulaVO>  selectPavFrmulaListPageRest(@RequestBody  PavFrmulaVO pavFrmulaVO, ModelMap model, HttpSession session) throws Exception {
		pavFrmulaVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavFrmulaVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavFrmulaVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavFrmulaVO.getPageUnit());
		paginationInfo.setPageSize(pavFrmulaVO.getPageSize());

		pavFrmulaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavFrmulaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavFrmulaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavFrmulaVO> items = pavFrmulaService.selectPavFrmulaList(pavFrmulaVO);
		return items;
	}	
 
	
	
	/**
	 * 포장_수식(TN_PAV_FRMULA) 상세를 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavfrmula/selectPavFrmula.do"  })
	public String selectPavFrmula(@ModelAttribute("searchVO") PavFrmulaVO pavFrmulaVO, ModelMap model) throws Exception {
	
		model.addAttribute("pavFrmulaVO", pavFrmulaService.selectPavFrmula(pavFrmulaVO));
		return "/manage/pavfrmula/PavFrmulaView";
	}
	
	/**
	 * 포장_수식(TN_PAV_FRMULA) 상세를 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return "/manage/pavfrmula/PavFrmulaView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavfrmula/selectPavFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaVO selectPavFrmulaRest(@RequestBody  PavFrmulaVO pavFrmulaVO, ModelMap model, HttpSession session) throws Exception {
		PavFrmulaVO pavFrmulaVOOne = pavFrmulaService.selectPavFrmula(pavFrmulaVO);
		return pavFrmulaVOOne;
	}

	

	@RequestMapping(value = { "/manage/pavfrmula/addPavFrmulaView.do" })
	public String addPavFrmulaView(@ModelAttribute("searchVO") PavFrmulaVO pavFrmulaVO, Model model) throws Exception {
		model.addAttribute("pavFrmulaVO", new PavFrmulaVO());
		return "/manage/pavfrmula/PavFrmulaRegist";
	}

	@RequestMapping(value = { "/manage/pavfrmula/addPavFrmula.do"  })
	public String addPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		pavFrmulaService.insertPavFrmula(pavFrmulaVO);
		return "redirect:/manage/pavfrmula/selectPavFrmulaList.do";
	}
	
	@RequestMapping(value = {  "/api/pavfrmula/addPavFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaVO addPavFrmulaRest(@RequestBody PavFrmulaVO pavFrmulaVO, HttpSession session) throws Exception {
		pavFrmulaService.insertPavFrmula(pavFrmulaVO);
		return pavFrmulaVO;
	}
	

	@RequestMapping(value = { "/manage/pavfrmula/updatePavFrmulaView.do" })
	public String updatePavFrmulaView(@ModelAttribute("searchVO") PavFrmulaVO pavFrmulaVO, Model model) throws Exception {
		model.addAttribute("pavFrmulaVO", pavFrmulaService.selectPavFrmula(pavFrmulaVO));
		return "/manage/pavfrmula/PavFrmulaUpdate";
	}

	@RequestMapping(value = { "/manage/pavfrmula/updatePavFrmula.do" })
	public String updatePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		pavFrmulaService.updatePavFrmula(pavFrmulaVO);
		return "redirect:/manage/pavfrmula/selectPavFrmulaList.do";
	}
	
	@RequestMapping(value = {  "/api/pavfrmula/updatePavFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaVO updatePavFrmulaRest(@RequestBody PavFrmulaVO pavFrmulaVO, HttpSession session) throws Exception {
		pavFrmulaService.updatePavFrmula(pavFrmulaVO);
		return pavFrmulaVO;
	}

	@RequestMapping(value = { "/manage/pavfrmula/deletePavFrmula.do" })
	public String deletePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		pavFrmulaService.deletePavFrmula(pavFrmulaVO);
		return "redirect:/manage/pavfrmula/selectPavFrmulaList.do";
	}
	
	@RequestMapping(value = {   "/api/pavfrmula/deletePavFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavFrmulaVO deletePavFrmulaRest(@RequestBody PavFrmulaVO pavFrmulaVO, HttpSession session) throws Exception {
		pavFrmulaService.deletePavFrmula(pavFrmulaVO);
		return pavFrmulaVO;
	}

}
