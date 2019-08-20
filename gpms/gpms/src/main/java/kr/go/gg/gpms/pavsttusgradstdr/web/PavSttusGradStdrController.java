

package kr.go.gg.gpms.pavsttusgradstdr.web;




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
import kr.go.gg.gpms.pavsttusgradstdr.service.PavSttusGradStdrService;
import kr.go.gg.gpms.pavsttusgradstdr.service.model.PavSttusGradStdrVO;



/**
 * @Class Name : PavSttusGradStdrController.java
 * @Description : PavSttusGradStdr Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("pavSttusGradStdrController")
public class PavSttusGradStdrController {

	@Resource(name = "pavSttusGradStdrService")
	private PavSttusGradStdrService pavSttusGradStdrService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PavSttusGradStdrController.class);

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다. (pageing)
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavsttusgradstdr/selectPavSttusGradStdrList.do" })
	public String selectPavSttusGradStdrList(PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model) throws Exception {
		pavSttusGradStdrVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavSttusGradStdrVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavSttusGradStdrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavSttusGradStdrVO.getPageUnit());
		paginationInfo.setPageSize(pavSttusGradStdrVO.getPageSize());

		pavSttusGradStdrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavSttusGradStdrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavSttusGradStdrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavSttusGradStdrVO> items = pavSttusGradStdrService.selectPavSttusGradStdrList(pavSttusGradStdrVO);
		model.addAttribute("items", items);

		int totCnt = pavSttusGradStdrService.selectPavSttusGradStdrListTotalCount(pavSttusGradStdrVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavsttusgradstdr/PavSttusGradStdrList" ;
	}
	
	
	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다. (pageing)
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavsttusgradstdr/selectPavSttusGradStdrList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavSttusGradStdrVO> selectPavSttusGradStdrListRest(@RequestBody PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model, HttpSession session) throws Exception {
		pavSttusGradStdrVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavSttusGradStdrVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavSttusGradStdrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavSttusGradStdrVO.getPageUnit());
		paginationInfo.setPageSize(pavSttusGradStdrVO.getPageSize());

		pavSttusGradStdrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavSttusGradStdrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavSttusGradStdrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavSttusGradStdrVO> items = pavSttusGradStdrService.selectPavSttusGradStdrList(pavSttusGradStdrVO);
		return items;
	}
	
	
	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다. (pageing)
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavsttusgradstdr/selectPavSttusGradStdrListPage.do" })
	public String selectPavSttusGradStdrListPage(PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model) throws Exception {
		pavSttusGradStdrVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavSttusGradStdrVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavSttusGradStdrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavSttusGradStdrVO.getPageUnit());
		paginationInfo.setPageSize(pavSttusGradStdrVO.getPageSize());

		pavSttusGradStdrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavSttusGradStdrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavSttusGradStdrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavSttusGradStdrVO> items = pavSttusGradStdrService.selectPavSttusGradStdrList(pavSttusGradStdrVO);
		model.addAttribute("items", items);

		int totCnt = pavSttusGradStdrService.selectPavSttusGradStdrListTotalCount(pavSttusGradStdrVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavsttusgradstdr/PavSttusGradStdrList" ;
	}
	
	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다. (pageing)
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/pavsttusgradstdr/selectPavSttusGradStdrListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<PavSttusGradStdrVO>  selectPavSttusGradStdrListPageRest(@RequestBody  PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model, HttpSession session) throws Exception {
		pavSttusGradStdrVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavSttusGradStdrVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavSttusGradStdrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavSttusGradStdrVO.getPageUnit());
		paginationInfo.setPageSize(pavSttusGradStdrVO.getPageSize());

		pavSttusGradStdrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavSttusGradStdrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavSttusGradStdrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavSttusGradStdrVO> items = pavSttusGradStdrService.selectPavSttusGradStdrList(pavSttusGradStdrVO);
		return items;
	}	
 
	
	
	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 상세를 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavsttusgradstdr/selectPavSttusGradStdr.do"  })
	public String selectPavSttusGradStdr(@ModelAttribute("searchVO") PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model) throws Exception {
	
		model.addAttribute("pavSttusGradStdrVO", pavSttusGradStdrService.selectPavSttusGradStdr(pavSttusGradStdrVO));
		return "/manage/pavsttusgradstdr/PavSttusGradStdrView";
	}
	
	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 상세를 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return "/manage/pavsttusgradstdr/PavSttusGradStdrView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavsttusgradstdr/selectPavSttusGradStdr.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavSttusGradStdrVO selectPavSttusGradStdrRest(@RequestBody  PavSttusGradStdrVO pavSttusGradStdrVO, ModelMap model, HttpSession session) throws Exception {
		PavSttusGradStdrVO pavSttusGradStdrVOOne = pavSttusGradStdrService.selectPavSttusGradStdr(pavSttusGradStdrVO);
		return pavSttusGradStdrVOOne;
	}

	

	@RequestMapping(value = { "/manage/pavsttusgradstdr/addPavSttusGradStdrView.do" })
	public String addPavSttusGradStdrView(@ModelAttribute("searchVO") PavSttusGradStdrVO pavSttusGradStdrVO, Model model) throws Exception {
		model.addAttribute("pavSttusGradStdrVO", new PavSttusGradStdrVO());
		return "/manage/pavsttusgradstdr/PavSttusGradStdrRegist";
	}

	@RequestMapping(value = { "/manage/pavsttusgradstdr/addPavSttusGradStdr.do"  })
	public String addPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		pavSttusGradStdrService.insertPavSttusGradStdr(pavSttusGradStdrVO);
		return "redirect:/manage/pavsttusgradstdr/selectPavSttusGradStdrList.do";
	}
	
	@RequestMapping(value = {  "/api/pavsttusgradstdr/addPavSttusGradStdr.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavSttusGradStdrVO addPavSttusGradStdrRest(@RequestBody PavSttusGradStdrVO pavSttusGradStdrVO, HttpSession session) throws Exception {
		pavSttusGradStdrService.insertPavSttusGradStdr(pavSttusGradStdrVO);
		return pavSttusGradStdrVO;
	}
	

	@RequestMapping(value = { "/manage/pavsttusgradstdr/updatePavSttusGradStdrView.do" })
	public String updatePavSttusGradStdrView(@ModelAttribute("searchVO") PavSttusGradStdrVO pavSttusGradStdrVO, Model model) throws Exception {
		model.addAttribute("pavSttusGradStdrVO", pavSttusGradStdrService.selectPavSttusGradStdr(pavSttusGradStdrVO));
		return "/manage/pavsttusgradstdr/PavSttusGradStdrUpdate";
	}

	@RequestMapping(value = { "/manage/pavsttusgradstdr/updatePavSttusGradStdr.do" })
	public String updatePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		pavSttusGradStdrService.updatePavSttusGradStdr(pavSttusGradStdrVO);
		return "redirect:/manage/pavsttusgradstdr/selectPavSttusGradStdrList.do";
	}
	
	@RequestMapping(value = {  "/api/pavsttusgradstdr/updatePavSttusGradStdr.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavSttusGradStdrVO updatePavSttusGradStdrRest(@RequestBody PavSttusGradStdrVO pavSttusGradStdrVO, HttpSession session) throws Exception {
		pavSttusGradStdrService.updatePavSttusGradStdr(pavSttusGradStdrVO);
		return pavSttusGradStdrVO;
	}

	@RequestMapping(value = { "/manage/pavsttusgradstdr/deletePavSttusGradStdr.do" })
	public String deletePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		pavSttusGradStdrService.deletePavSttusGradStdr(pavSttusGradStdrVO);
		return "redirect:/manage/pavsttusgradstdr/selectPavSttusGradStdrList.do";
	}
	
	@RequestMapping(value = {   "/api/pavsttusgradstdr/deletePavSttusGradStdr.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavSttusGradStdrVO deletePavSttusGradStdrRest(@RequestBody PavSttusGradStdrVO pavSttusGradStdrVO, HttpSession session) throws Exception {
		pavSttusGradStdrService.deletePavSttusGradStdr(pavSttusGradStdrVO);
		return pavSttusGradStdrVO;
	}

}
