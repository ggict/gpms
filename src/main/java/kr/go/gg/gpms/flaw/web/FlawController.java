

package kr.go.gg.gpms.flaw.web;




import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.flaw.service.FlawService;
import kr.go.gg.gpms.flaw.service.model.FlawVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : FlawController.java
 * @Description : Flaw Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("flawController")
public class FlawController  extends BaseController {

	@Resource(name = "flawService")
	private FlawService flawService;
	
	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlawController.class);

	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다. (pageing)
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/flaw/selectFlawList.do" })
	public String selectFlawList(FlawVO flawVO, ModelMap model) throws Exception {
		flawVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawVO.getPageUnit());
		paginationInfo.setPageSize(flawVO.getPageSize());

		flawVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawVO> items = flawService.selectFlawList(flawVO);
		model.addAttribute("items", items);

		int totCnt = flawService.selectFlawListTotalCount(flawVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/flaw/flawList" ;
	}
	
	
	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다. (pageing)
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/flaw/selectFlawList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<FlawVO> selectFlawListRest(@RequestBody FlawVO flawVO, ModelMap model, HttpSession session) throws Exception {
		flawVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawVO.getPageUnit());
		paginationInfo.setPageSize(flawVO.getPageSize());

		flawVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawVO> items = flawService.selectFlawList(flawVO);
		return items;
	}
	
	
	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다. (pageing)
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/flaw/selectFlawListPage.do" })
	public String selectFlawListPage(FlawVO flawVO, ModelMap model) throws Exception {
		flawVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawVO.getPageUnit());
		paginationInfo.setPageSize(flawVO.getPageSize());

		flawVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawVO> items = flawService.selectFlawList(flawVO);
		model.addAttribute("items", items);

		int totCnt = flawService.selectFlawListTotalCount(flawVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/flaw/flawList" ;
	}
	
	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다. (pageing)
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/flaw/selectFlawListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<FlawVO>  selectFlawListPageRest(@RequestBody  FlawVO flawVO, ModelMap model, HttpSession session) throws Exception {
		flawVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawVO.getPageUnit());
		paginationInfo.setPageSize(flawVO.getPageSize());

		flawVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawVO> items = flawService.selectFlawList(flawVO);
		return items;
	}	
 
	
	
	/**
	 * 공사하자정보(TN_FLAW) 상세를 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/flaw/selectFlaw.do"  })
	public String selectFlaw(@ModelAttribute("searchVO") FlawVO flawVO, ModelMap model) throws Exception {
	
		model.addAttribute("flawVO", flawService.selectFlawByCntrwkId(flawVO));
		return "/flaw/flawView";
	}
	
	/**
	 * 공사하자정보(TN_FLAW) 상세를 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return "/manage/flaw/FlawView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/flaw/selectFlaw.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawVO selectFlawRest(@RequestBody  FlawVO flawVO, ModelMap model, HttpSession session) throws Exception {
		FlawVO flawVOOne = flawService.selectFlaw(flawVO);
		return flawVOOne;
	}

	

	@RequestMapping(value = { "/flaw/updateFlawView.do" })
	public String updateFlawView(@ModelAttribute("searchVO") FlawVO flawVO, CodeVO codeVO, ModelMap model) throws Exception {
		String action_flag = "";
		
		//하자 보증금 종류 코드
		codeVO.setCL_CODE("FGKD");
		codeVO.setUSE_AT("Y");
		codeVO.setDELETE_AT("N");
		codeVO.setUsePage(false);
		List<CodeVO> fgkdList = codeService.selectCodeList(codeVO);
		
		//하자 기본 정보
		flawVO = flawService.selectFlawByCntrwkId(flawVO);
		
		//하자 등록 flag
		if(flawVO.getFLAW_ID() == null) {
			action_flag = "INSERT";
		} else {
			action_flag = "UPDATE";
		}
		
		model.addAttribute("fgkdList", fgkdList);
		model.addAttribute("result", flawVO);
		model.addAttribute("action_flag", action_flag);
		
		return "/flaw/flawUpdate";
	}

	
	@RequestMapping(value = {  "/api/flaw/addFlaw.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawVO addFlawRest(@RequestBody FlawVO flawVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(flawVO);
		flawService.insertFlaw(flawVO);
		flawVO.setResultSuccess("true");
		flawVO.setResultMSG("정상 등록되었습니다.");
		return flawVO;
	}

	
	@RequestMapping(value = { "/api/flaw/updateFlaw.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawVO updateFlawRest(@RequestBody FlawVO flawVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(flawVO);
		flawService.updateFlaw(flawVO);
		flawVO.setResultSuccess("true");
		flawVO.setResultMSG("정상 수정되었습니다.");
		return flawVO;
	}

	@RequestMapping(value = {   "/api/flaw/deleteFlaw.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawVO deleteFlawRest(@RequestBody FlawVO flawVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(flawVO);
		flawService.deleteFlaw(flawVO);
		flawVO.setResultSuccess("true");
		flawVO.setResultMSG("정상 삭제되었습니다.");
		return flawVO;
	}

}
