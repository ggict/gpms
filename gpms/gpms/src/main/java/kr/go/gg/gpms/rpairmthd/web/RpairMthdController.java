

package kr.go.gg.gpms.rpairmthd.web;




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
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;



/**
 * @Class Name : RpairMthdController.java
 * @Description : RpairMthd Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairMthdController")
public class RpairMthdController  extends BaseController {

	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpairMthdController.class);

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다. (pageing)
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairmthd/selectRpairMthdList.do" })
	public String selectRpairMthdList(RpairMthdVO rpairMthdVO, ModelMap model) throws Exception {
		rpairMthdVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairMthdVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairMthdVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairMthdVO.getPageUnit());
		paginationInfo.setPageSize(rpairMthdVO.getPageSize());

		rpairMthdVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairMthdVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairMthdVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairMthdVO> items = rpairMthdService.selectRpairMthdList(rpairMthdVO);
		model.addAttribute("items", items);

		int totCnt = rpairMthdService.selectRpairMthdListTotalCount(rpairMthdVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairmthd/RpairMthdList" ;
	}
	
	
	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다. (pageing)
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairmthd/selectRpairMthdList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairMthdVO> selectRpairMthdListRest(@RequestBody RpairMthdVO rpairMthdVO, ModelMap model, HttpSession session) throws Exception {
		rpairMthdVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairMthdVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairMthdVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairMthdVO.getPageUnit());
		paginationInfo.setPageSize(rpairMthdVO.getPageSize());

		rpairMthdVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairMthdVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairMthdVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairMthdVO> items = rpairMthdService.selectRpairMthdList(rpairMthdVO);
		return items;
	}
	
	
	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다. (pageing)
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairmthd/selectRpairMthdListPage.do" })
	public String selectRpairMthdListPage(RpairMthdVO rpairMthdVO, ModelMap model) throws Exception {
		rpairMthdVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairMthdVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairMthdVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairMthdVO.getPageUnit());
		paginationInfo.setPageSize(rpairMthdVO.getPageSize());

		rpairMthdVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairMthdVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairMthdVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairMthdVO> items = rpairMthdService.selectRpairMthdList(rpairMthdVO);
		model.addAttribute("items", items);

		int totCnt = rpairMthdService.selectRpairMthdListTotalCount(rpairMthdVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairmthd/RpairMthdList" ;
	}
	
	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다. (pageing)
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/rpairmthd/selectRpairMthdListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<RpairMthdVO>  selectRpairMthdListPageRest(@RequestBody  RpairMthdVO rpairMthdVO, ModelMap model, HttpSession session) throws Exception {
		rpairMthdVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairMthdVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairMthdVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairMthdVO.getPageUnit());
		paginationInfo.setPageSize(rpairMthdVO.getPageSize());

		rpairMthdVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairMthdVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairMthdVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairMthdVO> items = rpairMthdService.selectRpairMthdList(rpairMthdVO);
		return items;
	}	
 
	
	
	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 상세를 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairmthd/selectRpairMthd.do"  })
	public String selectRpairMthd(@ModelAttribute("searchVO") RpairMthdVO rpairMthdVO, ModelMap model) throws Exception {
	
		model.addAttribute("rpairMthdVO", rpairMthdService.selectRpairMthd(rpairMthdVO));
		return "/manage/rpairmthd/RpairMthdView";
	}
	
	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 상세를 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return "/manage/rpairmthd/RpairMthdView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairmthd/selectRpairMthd.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdVO selectRpairMthdRest(@RequestBody  RpairMthdVO rpairMthdVO, ModelMap model, HttpSession session) throws Exception {
		RpairMthdVO rpairMthdVOOne = rpairMthdService.selectRpairMthd(rpairMthdVO);
		return rpairMthdVOOne;
	}

	

	@RequestMapping(value = { "/manage/rpairmthd/addRpairMthdView.do" })
	public String addRpairMthdView(@ModelAttribute("searchVO") RpairMthdVO rpairMthdVO, ModelMap model) throws Exception {
		model.addAttribute("rpairMthdVO", new RpairMthdVO());
		return "/manage/rpairmthd/RpairMthdRegist";
	}

	@RequestMapping(value = { "/manage/rpairmthd/addRpairMthd.do"  })
	public String addRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.insertRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 등록되었습니다.");
		return "redirect:/manage/rpairmthd/selectRpairMthdList.do";
	}
	
	@RequestMapping(value = {  "/api/rpairmthd/addRpairMthd.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdVO addRpairMthdRest(@RequestBody RpairMthdVO rpairMthdVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.insertRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 등록되었습니다.");
		return rpairMthdVO;
	}
	

	@RequestMapping(value = { "/manage/rpairmthd/updateRpairMthdView.do" })
	public String updateRpairMthdView(@ModelAttribute("searchVO") RpairMthdVO rpairMthdVO, ModelMap model) throws Exception {
		model.addAttribute("rpairMthdVO", rpairMthdService.selectRpairMthd(rpairMthdVO));
		return "/manage/rpairmthd/RpairMthdUpdate";
	}

	@RequestMapping(value = { "/manage/rpairmthd/updateRpairMthd.do" })
	public String updateRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.updateRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/rpairmthd/selectRpairMthdList.do";
	}
	
	@RequestMapping(value = {  "/api/rpairmthd/updateRpairMthd.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdVO updateRpairMthdRest(@RequestBody RpairMthdVO rpairMthdVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.updateRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 수정되었습니다.");
		return rpairMthdVO;
	}

	@RequestMapping(value = { "/manage/rpairmthd/deleteRpairMthd.do" })
	public String deleteRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.deleteRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/rpairmthd/selectRpairMthdList.do";
	}
	
	@RequestMapping(value = {   "/api/rpairmthd/deleteRpairMthd.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdVO deleteRpairMthdRest(@RequestBody RpairMthdVO rpairMthdVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairMthdVO);
		rpairMthdService.deleteRpairMthd(rpairMthdVO);
		rpairMthdVO.setResultSuccess("true");
		rpairMthdVO.setResultMSG("정상 삭제되었습니다.");
		return rpairMthdVO;
	}

}
