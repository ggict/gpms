

package kr.go.gg.gpms.code.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : CodeController.java
 * @Description : Code Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("codeController")
public class CodeController extends BaseController {

	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);

	/**
	 * 공통코드(TC_CODE) 목록을 조회한다. (pageing)
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/code/selectCodeList.do" })
	public String selectCodeList(CodeVO codeVO, ModelMap model) throws Exception {
		codeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeVO.getPageUnit());
		paginationInfo.setPageSize(codeVO.getPageSize());

		codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeVO> items = codeService.selectCodeList(codeVO);
		model.addAttribute("items", items);

		int totCnt = codeService.selectCodeListTotalCount(codeVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/code/CodeList" ;
	}
	
	
	/**
	 * 공통코드(TC_CODE) 목록을 조회한다. (pageing)
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/code/selectCodeList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCodeListRest(@RequestBody CodeVO codeVO, ModelMap model, HttpSession session) throws Exception {
		int pageIndex = 1;
		codeVO.setUsePage(false);
		int total_count = codeService.selectCodeListTotalCount(codeVO);
		List<CodeVO> items = codeService.selectCodeList(codeVO);
		int total_page = 0;
		 // 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", pageIndex);
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	
	/**
	 * 공통코드(TC_CODE) 목록을 조회한다. (pageing)
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/code/selectCodeListPage.do" })
	public String selectCodeListPage(CodeVO codeVO, ModelMap model) throws Exception {
		codeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeVO.getPageUnit());
		paginationInfo.setPageSize(codeVO.getPageSize());

		codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeVO> items = codeService.selectCodeList(codeVO);
		model.addAttribute("items", items);

		int totCnt = codeService.selectCodeListTotalCount(codeVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/code/CodeList" ;
	}
	
	/**
	 * 공통코드(TC_CODE) 목록을 조회한다. (pageing)
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/code/selectCodeListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CodeVO>  selectCodeListPageRest(@RequestBody  CodeVO codeVO, ModelMap model, HttpSession session) throws Exception {
		codeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeVO.getPageUnit());
		paginationInfo.setPageSize(codeVO.getPageSize());

		codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeVO> items = codeService.selectCodeList(codeVO);
		return items;
	}	
 
	
	
	/**
	 * 공통코드(TC_CODE) 상세를 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/code/selectCode.do"  })
	public String selectCode(@ModelAttribute("searchVO") CodeVO codeVO, ModelMap model) throws Exception {
	
		model.addAttribute("codeVO", codeService.selectCode(codeVO));
		return "/manage/code/CodeView";
	}
	
	/**
	 * 공통코드(TC_CODE) 상세를 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return "/manage/code/CodeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/code/selectCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeVO selectCodeRest(@RequestBody  CodeVO codeVO, ModelMap model, HttpSession session) throws Exception {
		CodeVO codeVOOne = codeService.selectCode(codeVO);
		return codeVOOne;
	}

	

	@RequestMapping(value = { "/manage/code/addCodeView.do" })
	public String addCodeView(@ModelAttribute("searchVO") CodeVO codeVO, Model model) throws Exception {
		CodeVO codeOne = codeService.selectCodeInsertMax(codeVO);
		
		model.addAttribute("codeVO", codeOne);
		return "/mng/code/CodeRegist";
	}

	@RequestMapping(value = { "/manage/code/addCode.do"  })
	public String addCode(CodeVO codeVO) throws Exception {
		codeService.insertCode(codeVO);
		return "redirect:/manage/code/selectCodeList.do";
	}
	
	@RequestMapping(value = {  "/api/code/addCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeVO addCodeRest(@RequestBody CodeVO codeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(codeVO);
		codeService.insertCode(codeVO);
		codeVO.setResultSuccess("true");
		codeVO.setResultMSG("정상 등록되었습니다.");
		
		return codeVO;
	}
	

	@RequestMapping(value = { "/manage/code/updateCodeView.do" })
	public String updateCodeView(@ModelAttribute("searchVO") CodeVO codeVO, Model model) throws Exception {
		model.addAttribute("codeVO", codeService.selectCode(codeVO));
		return "/mng/code/CodeUpdate";
	}

	@RequestMapping(value = { "/manage/code/updateCode.do" })
	public String updateCode(CodeVO codeVO) throws Exception {
		codeService.updateCode(codeVO);
		return "redirect:/manage/code/selectCodeList.do";
	}
	
	@RequestMapping(value = {  "/api/code/updateCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeVO updateCodeRest(@RequestBody CodeVO codeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(codeVO);
		codeService.updateCode(codeVO);
		codeVO.setResultSuccess("true");
		codeVO.setResultMSG("정상 수정되었습니다.");
		
		return codeVO;
	}

	@RequestMapping(value = { "/manage/code/deleteCode.do" })
	public String deleteCode(CodeVO codeVO) throws Exception {
		codeService.deleteCode(codeVO);
		return "redirect:/manage/code/selectCodeList.do";
	}
	
	@RequestMapping(value = {   "/api/code/deleteCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeVO deleteCodeRest(@RequestBody CodeVO codeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(codeVO);
		codeService.deleteCode(codeVO);
		codeVO.setResultSuccess("true");
		codeVO.setResultMSG("정상 삭제되었습니다.");
		
		return codeVO;
	}
	
	@RequestMapping(value = {   "/api/code/selectCodeName.do" })
	public String selectCodeName(@ModelAttribute("CodeVO") CodeVO codeVO, ModelMap model) throws Exception{
		model.addAttribute("resultData", cmmnService.selectCodeName(codeVO));
		return "jsonViewHTML";
	}
	
	@RequestMapping(value = {   "/api/code/selectRouteInfo.do" })
	public String selectRouteInfo(@ModelAttribute("CodeVO") CodeVO codeVO, ModelMap model, HttpServletRequest request) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		Map<String, String> req = requestToHashMap(request);
		
		map.put("SCH_ROUTCODE", req.get("SCH_ROUTCODE"));
		model.addAttribute("resultData", cmmnService.selectRouteInfo(map));
		return "jsonViewHTML";
	}
	
	@RequestMapping(value = { "/api/code/selectCellType.do" })
	public String selectCellType(@ModelAttribute("CodeVO") CodeVO codeVO, HttpSession session, ModelMap model) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		model.addAttribute("typeList", cmmnService.selectCellType(map));
		
		return "jsonViewHTML";
	}
}
