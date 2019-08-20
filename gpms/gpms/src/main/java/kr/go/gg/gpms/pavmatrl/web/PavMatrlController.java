

package kr.go.gg.gpms.pavmatrl.web;




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
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.pavmatrl.service.PavMatrlService;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;



/**
 * @Class Name : PavMatrlController.java
 * @Description : PavMatrl Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("pavMatrlController")
public class PavMatrlController  extends BaseController {

	@Resource(name = "pavMatrlService")
	private PavMatrlService pavMatrlService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PavMatrlController.class);

	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다. (pageing)
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavmatrl/selectPavMatrlList.do" })
	public String selectPavMatrlList(PavMatrlVO pavMatrlVO, ModelMap model) throws Exception {
		pavMatrlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavMatrlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavMatrlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavMatrlVO.getPageUnit());
		paginationInfo.setPageSize(pavMatrlVO.getPageSize());

		pavMatrlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavMatrlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavMatrlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavMatrlVO> items = pavMatrlService.selectPavMatrlList(pavMatrlVO);
		model.addAttribute("items", items);

		int totCnt = pavMatrlService.selectPavMatrlListTotalCount(pavMatrlVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavmatrl/PavMatrlList" ;
	}
	
	
	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다. (pageing)
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavmatrl/selectPavMatrlList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavMatrlVO> selectPavMatrlListRest(@RequestBody PavMatrlVO pavMatrlVO, ModelMap model, HttpSession session) throws Exception {
		pavMatrlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavMatrlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavMatrlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavMatrlVO.getPageUnit());
		paginationInfo.setPageSize(pavMatrlVO.getPageSize());

		pavMatrlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavMatrlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavMatrlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavMatrlVO> items = pavMatrlService.selectPavMatrlList(pavMatrlVO);
		return items;
	}
	
	
	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다. (pageing)
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavmatrl/selectPavMatrlListPage.do" })
	public String selectPavMatrlListPage(PavMatrlVO pavMatrlVO, ModelMap model) throws Exception {
		pavMatrlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavMatrlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavMatrlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavMatrlVO.getPageUnit());
		paginationInfo.setPageSize(pavMatrlVO.getPageSize());

		pavMatrlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavMatrlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavMatrlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavMatrlVO> items = pavMatrlService.selectPavMatrlList(pavMatrlVO);
		model.addAttribute("items", items);

		int totCnt = pavMatrlService.selectPavMatrlListTotalCount(pavMatrlVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/pavmatrl/PavMatrlList" ;
	}
	
	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다. (pageing)
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/pavmatrl/selectPavMatrlListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<PavMatrlVO>  selectPavMatrlListPageRest(@RequestBody  PavMatrlVO pavMatrlVO, ModelMap model, HttpSession session) throws Exception {
		pavMatrlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		pavMatrlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pavMatrlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pavMatrlVO.getPageUnit());
		paginationInfo.setPageSize(pavMatrlVO.getPageSize());

		pavMatrlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pavMatrlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pavMatrlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PavMatrlVO> items = pavMatrlService.selectPavMatrlList(pavMatrlVO);
		return items;
	}	
 
	
	
	/**
	 * 포장재료코드(TC_PAV_MATRL) 상세를 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/pavmatrl/selectPavMatrl.do"  })
	public String selectPavMatrl(@ModelAttribute("searchVO") PavMatrlVO pavMatrlVO, ModelMap model) throws Exception {
	
		model.addAttribute("pavMatrlVO", pavMatrlService.selectPavMatrl(pavMatrlVO));
		return "/manage/pavmatrl/PavMatrlView";
	}
	
	/**
	 * 포장재료코드(TC_PAV_MATRL) 상세를 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return "/manage/pavmatrl/PavMatrlView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pavmatrl/selectPavMatrl.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavMatrlVO selectPavMatrlRest(@RequestBody  PavMatrlVO pavMatrlVO, ModelMap model, HttpSession session) throws Exception {
		PavMatrlVO pavMatrlVOOne = pavMatrlService.selectPavMatrl(pavMatrlVO);
		return pavMatrlVOOne;
	}

	

	@RequestMapping(value = { "/manage/pavmatrl/addPavMatrlView.do" })
	public String addPavMatrlView(@ModelAttribute("searchVO") PavMatrlVO pavMatrlVO, ModelMap model) throws Exception {
		model.addAttribute("pavMatrlVO", new PavMatrlVO());
		return "/manage/pavmatrl/PavMatrlRegist";
	}

	@RequestMapping(value = { "/manage/pavmatrl/addPavMatrl.do"  })
	public String addPavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.insertPavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 등록되었습니다.");
		return "redirect:/manage/pavmatrl/selectPavMatrlList.do";
	}
	
	@RequestMapping(value = {  "/api/pavmatrl/addPavMatrl.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavMatrlVO addPavMatrlRest(@RequestBody PavMatrlVO pavMatrlVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.insertPavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 등록되었습니다.");
		return pavMatrlVO;
	}
	

	@RequestMapping(value = { "/manage/pavmatrl/updatePavMatrlView.do" })
	public String updatePavMatrlView(@ModelAttribute("searchVO") PavMatrlVO pavMatrlVO, ModelMap model) throws Exception {
		model.addAttribute("pavMatrlVO", pavMatrlService.selectPavMatrl(pavMatrlVO));
		return "/manage/pavmatrl/PavMatrlUpdate";
	}

	@RequestMapping(value = { "/manage/pavmatrl/updatePavMatrl.do" })
	public String updatePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.updatePavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/pavmatrl/selectPavMatrlList.do";
	}
	
	@RequestMapping(value = {  "/api/pavmatrl/updatePavMatrl.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavMatrlVO updatePavMatrlRest(@RequestBody PavMatrlVO pavMatrlVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.updatePavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 수정되었습니다.");
		return pavMatrlVO;
	}

	@RequestMapping(value = { "/manage/pavmatrl/deletePavMatrl.do" })
	public String deletePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.deletePavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/pavmatrl/selectPavMatrlList.do";
	}
	
	@RequestMapping(value = {   "/api/pavmatrl/deletePavMatrl.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PavMatrlVO deletePavMatrlRest(@RequestBody PavMatrlVO pavMatrlVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(pavMatrlVO);
		pavMatrlService.deletePavMatrl(pavMatrlVO);
		pavMatrlVO.setResultSuccess("true");
		pavMatrlVO.setResultMSG("정상 삭제되었습니다.");
		return pavMatrlVO;
	}

}
