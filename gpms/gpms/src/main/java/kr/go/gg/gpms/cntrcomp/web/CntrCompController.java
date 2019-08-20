

package kr.go.gg.gpms.cntrcomp.web;




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
import kr.go.gg.gpms.cntrcomp.service.CntrCompService;
import kr.go.gg.gpms.cntrcomp.service.model.CntrCompVO;



/**
 * @Class Name : CntrCompController.java
 * @Description : CntrComp Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("cntrCompController")
public class CntrCompController  extends BaseController {

	@Resource(name = "cntrCompService")
	private CntrCompService cntrCompService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CntrCompController.class);

	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다. (pageing)
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrcomp/selectCntrCompList.do" })
	public String selectCntrCompList(CntrCompVO cntrCompVO, ModelMap model) throws Exception {
		cntrCompVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrCompVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrCompVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrCompVO.getPageUnit());
		paginationInfo.setPageSize(cntrCompVO.getPageSize());

		cntrCompVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrCompVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrCompVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrCompVO> items = cntrCompService.selectCntrCompList(cntrCompVO);
		model.addAttribute("items", items);

		int totCnt = cntrCompService.selectCntrCompListTotalCount(cntrCompVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/cntrcomp/CntrCompList" ;
	}
	
	
	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다. (pageing)
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrcomp/selectCntrCompList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CntrCompVO> selectCntrCompListRest(@RequestBody CntrCompVO cntrCompVO, ModelMap model, HttpSession session) throws Exception {
		cntrCompVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrCompVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrCompVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrCompVO.getPageUnit());
		paginationInfo.setPageSize(cntrCompVO.getPageSize());

		cntrCompVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrCompVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrCompVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrCompVO> items = cntrCompService.selectCntrCompList(cntrCompVO);
		return items;
	}
	
	
	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다. (pageing)
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrcomp/selectCntrCompListPage.do" })
	public String selectCntrCompListPage(CntrCompVO cntrCompVO, ModelMap model) throws Exception {
		cntrCompVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrCompVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrCompVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrCompVO.getPageUnit());
		paginationInfo.setPageSize(cntrCompVO.getPageSize());

		cntrCompVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrCompVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrCompVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrCompVO> items = cntrCompService.selectCntrCompList(cntrCompVO);
		model.addAttribute("items", items);

		int totCnt = cntrCompService.selectCntrCompListTotalCount(cntrCompVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/cntrcomp/CntrCompList" ;
	}
	
	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다. (pageing)
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cntrcomp/selectCntrCompListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CntrCompVO>  selectCntrCompListPageRest(@RequestBody  CntrCompVO cntrCompVO, ModelMap model, HttpSession session) throws Exception {
		cntrCompVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrCompVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrCompVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrCompVO.getPageUnit());
		paginationInfo.setPageSize(cntrCompVO.getPageSize());

		cntrCompVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrCompVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrCompVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrCompVO> items = cntrCompService.selectCntrCompList(cntrCompVO);
		return items;
	}	
 
	
	
	/**
	 * 공사업체정보(TN_CNTR_COMP) 상세를 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/cntrcomp/selectCntrComp.do"  })
	public String selectCntrComp(@ModelAttribute("searchVO") CntrCompVO cntrCompVO, ModelMap model) throws Exception {
	
		model.addAttribute("cntrCompVO", cntrCompService.selectCntrComp(cntrCompVO));
		return "/manage/cntrcomp/CntrCompView";
	}
	
	/**
	 * 공사업체정보(TN_CNTR_COMP) 상세를 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return "/manage/cntrcomp/CntrCompView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrcomp/selectCntrComp.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrCompVO selectCntrCompRest(@RequestBody  CntrCompVO cntrCompVO, ModelMap model, HttpSession session) throws Exception {
		CntrCompVO cntrCompVOOne = cntrCompService.selectCntrComp(cntrCompVO);
		return cntrCompVOOne;
	}

	

	@RequestMapping(value = { "/manage/cntrcomp/addCntrCompView.do" })
	public String addCntrCompView(@ModelAttribute("searchVO") CntrCompVO cntrCompVO, ModelMap model) throws Exception {
		model.addAttribute("cntrCompVO", new CntrCompVO());
		return "/manage/cntrcomp/CntrCompRegist";
	}

	@RequestMapping(value = { "/manage/cntrcomp/addCntrComp.do"  })
	public String addCntrComp(CntrCompVO cntrCompVO) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.insertCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 등록되었습니다.");
		return "redirect:/manage/cntrcomp/selectCntrCompList.do";
	}
	
	@RequestMapping(value = {  "/api/cntrcomp/addCntrComp.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrCompVO addCntrCompRest(@RequestBody CntrCompVO cntrCompVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.insertCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 등록되었습니다.");
		return cntrCompVO;
	}
	

	@RequestMapping(value = { "/manage/cntrcomp/updateCntrCompView.do" })
	public String updateCntrCompView(@ModelAttribute("searchVO") CntrCompVO cntrCompVO, ModelMap model) throws Exception {
		model.addAttribute("cntrCompVO", cntrCompService.selectCntrComp(cntrCompVO));
		return "/manage/cntrcomp/CntrCompUpdate";
	}

	@RequestMapping(value = { "/manage/cntrcomp/updateCntrComp.do" })
	public String updateCntrComp(CntrCompVO cntrCompVO) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.updateCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/cntrcomp/selectCntrCompList.do";
	}
	
	@RequestMapping(value = {  "/api/cntrcomp/updateCntrComp.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrCompVO updateCntrCompRest(@RequestBody CntrCompVO cntrCompVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.updateCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 수정되었습니다.");
		return cntrCompVO;
	}

	@RequestMapping(value = { "/manage/cntrcomp/deleteCntrComp.do" })
	public String deleteCntrComp(CntrCompVO cntrCompVO) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.deleteCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/cntrcomp/selectCntrCompList.do";
	}
	
	@RequestMapping(value = {   "/api/cntrcomp/deleteCntrComp.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrCompVO deleteCntrCompRest(@RequestBody CntrCompVO cntrCompVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrCompVO);
		cntrCompService.deleteCntrComp(cntrCompVO);
		cntrCompVO.setResultSuccess("true");
		cntrCompVO.setResultMSG("정상 삭제되었습니다.");
		return cntrCompVO;
	}

}
