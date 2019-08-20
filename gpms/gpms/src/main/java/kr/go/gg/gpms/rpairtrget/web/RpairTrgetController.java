

package kr.go.gg.gpms.rpairtrget.web;




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
import kr.go.gg.gpms.rpairtrget.service.RpairTrgetService;
import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetVO;



/**
 * @Class Name : RpairTrgetController.java
 * @Description : RpairTrget Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairTrgetController")
public class RpairTrgetController  extends BaseController {

	@Resource(name = "rpairTrgetService")
	private RpairTrgetService rpairTrgetService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpairTrgetController.class);

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다. (pageing)
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrget/selectRpairTrgetList.do" })
	public String selectRpairTrgetList(RpairTrgetVO rpairTrgetVO, ModelMap model) throws Exception {
		rpairTrgetVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetVO.getPageSize());

		rpairTrgetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetVO> items = rpairTrgetService.selectRpairTrgetList(rpairTrgetVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetService.selectRpairTrgetListTotalCount(rpairTrgetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrget/RpairTrgetList" ;
	}
	
	
	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다. (pageing)
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrget/selectRpairTrgetList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairTrgetVO> selectRpairTrgetListRest(@RequestBody RpairTrgetVO rpairTrgetVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetVO.getPageSize());

		rpairTrgetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetVO> items = rpairTrgetService.selectRpairTrgetList(rpairTrgetVO);
		return items;
	}
	
	
	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다. (pageing)
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrget/selectRpairTrgetListPage.do" })
	public String selectRpairTrgetListPage(RpairTrgetVO rpairTrgetVO, ModelMap model) throws Exception {
		rpairTrgetVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetVO.getPageSize());

		rpairTrgetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetVO> items = rpairTrgetService.selectRpairTrgetList(rpairTrgetVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetService.selectRpairTrgetListTotalCount(rpairTrgetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrget/RpairTrgetList" ;
	}
	
	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다. (pageing)
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/rpairtrget/selectRpairTrgetListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<RpairTrgetVO>  selectRpairTrgetListPageRest(@RequestBody  RpairTrgetVO rpairTrgetVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetVO.getPageSize());

		rpairTrgetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetVO> items = rpairTrgetService.selectRpairTrgetList(rpairTrgetVO);
		return items;
	}	
 
	
	
	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 상세를 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrget/selectRpairTrget.do"  })
	public String selectRpairTrget(@ModelAttribute("searchVO") RpairTrgetVO rpairTrgetVO, ModelMap model) throws Exception {
	
		model.addAttribute("rpairTrgetVO", rpairTrgetService.selectRpairTrget(rpairTrgetVO));
		return "/manage/rpairtrget/RpairTrgetView";
	}
	
	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 상세를 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return "/manage/rpairtrget/RpairTrgetView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrget/selectRpairTrget.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetVO selectRpairTrgetRest(@RequestBody  RpairTrgetVO rpairTrgetVO, ModelMap model, HttpSession session) throws Exception {
		RpairTrgetVO rpairTrgetVOOne = rpairTrgetService.selectRpairTrget(rpairTrgetVO);
		return rpairTrgetVOOne;
	}

	

	@RequestMapping(value = { "/manage/rpairtrget/addRpairTrgetView.do" })
	public String addRpairTrgetView(@ModelAttribute("searchVO") RpairTrgetVO rpairTrgetVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetVO", new RpairTrgetVO());
		return "/manage/rpairtrget/RpairTrgetRegist";
	}

	@RequestMapping(value = { "/manage/rpairtrget/addRpairTrget.do"  })
	public String addRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.insertRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 등록되었습니다.");
		return "redirect:/manage/rpairtrget/selectRpairTrgetList.do";
	}
	
	@RequestMapping(value = {  "/api/rpairtrget/addRpairTrget.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetVO addRpairTrgetRest(@RequestBody RpairTrgetVO rpairTrgetVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.insertRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 등록되었습니다.");
		return rpairTrgetVO;
	}
	

	@RequestMapping(value = { "/manage/rpairtrget/updateRpairTrgetView.do" })
	public String updateRpairTrgetView(@ModelAttribute("searchVO") RpairTrgetVO rpairTrgetVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetVO", rpairTrgetService.selectRpairTrget(rpairTrgetVO));
		return "/manage/rpairtrget/RpairTrgetUpdate";
	}

	@RequestMapping(value = { "/manage/rpairtrget/updateRpairTrget.do" })
	public String updateRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.updateRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/rpairtrget/selectRpairTrgetList.do";
	}
	
	@RequestMapping(value = {  "/api/rpairtrget/updateRpairTrget.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetVO updateRpairTrgetRest(@RequestBody RpairTrgetVO rpairTrgetVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.updateRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 수정되었습니다.");
		return rpairTrgetVO;
	}

	@RequestMapping(value = { "/manage/rpairtrget/deleteRpairTrget.do" })
	public String deleteRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.deleteRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/rpairtrget/selectRpairTrgetList.do";
	}
	
	@RequestMapping(value = {   "/api/rpairtrget/deleteRpairTrget.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetVO deleteRpairTrgetRest(@RequestBody RpairTrgetVO rpairTrgetVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetVO);
		rpairTrgetService.deleteRpairTrget(rpairTrgetVO);
		rpairTrgetVO.setResultSuccess("true");
		rpairTrgetVO.setResultMSG("정상 삭제되었습니다.");
		return rpairTrgetVO;
	}

}
