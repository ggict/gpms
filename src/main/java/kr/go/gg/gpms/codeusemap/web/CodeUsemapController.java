

package kr.go.gg.gpms.codeusemap.web;




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
import kr.go.gg.gpms.codeusemap.service.CodeUsemapService;
import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapVO;



/**
 * @Class Name : CodeUsemapController.java
 * @Description : CodeUsemap Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("codeUsemapController")
public class CodeUsemapController extends BaseController {

	@Resource(name = "codeUsemapService")
	private CodeUsemapService codeUsemapService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeUsemapController.class);

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다. (pageing)
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/codeusemap/selectCodeUsemapList.do" })
	public String selectCodeUsemapList(CodeUsemapVO codeUsemapVO, ModelMap model) throws Exception {
		codeUsemapVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeUsemapVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeUsemapVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeUsemapVO.getPageUnit());
		paginationInfo.setPageSize(codeUsemapVO.getPageSize());

		codeUsemapVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeUsemapVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeUsemapVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeUsemapVO> items = codeUsemapService.selectCodeUsemapList(codeUsemapVO);
		model.addAttribute("items", items);

		int totCnt = codeUsemapService.selectCodeUsemapListTotalCount(codeUsemapVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/codeusemap/CodeUsemapList" ;
	}
	
	
	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다. (pageing)
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/codeusemap/selectCodeUsemapList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCodeUsemapListRest(@RequestBody CodeUsemapVO codeUsemapVO, ModelMap model, HttpSession session) throws Exception {
 
		codeUsemapVO.setUsePage(false);
		List<CodeUsemapVO> items = codeUsemapService.selectCodeUsemapList(codeUsemapVO);
		int pageIndex = 1;
		int total_page = 0;
		int total_count = codeUsemapService.selectCodeUsemapListTotalCount(codeUsemapVO);
		 // 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", pageIndex);
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	
	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다. (pageing)
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/codeusemap/selectCodeUsemapListPage.do" })
	public String selectCodeUsemapListPage(CodeUsemapVO codeUsemapVO, ModelMap model) throws Exception {
		codeUsemapVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeUsemapVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeUsemapVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeUsemapVO.getPageUnit());
		paginationInfo.setPageSize(codeUsemapVO.getPageSize());

		codeUsemapVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeUsemapVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeUsemapVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeUsemapVO> items = codeUsemapService.selectCodeUsemapList(codeUsemapVO);
		model.addAttribute("items", items);

		int totCnt = codeUsemapService.selectCodeUsemapListTotalCount(codeUsemapVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/codeusemap/CodeUsemapList" ;
	}
	
	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다. (pageing)
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/codeusemap/selectCodeUsemapListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CodeUsemapVO>  selectCodeUsemapListPageRest(@RequestBody  CodeUsemapVO codeUsemapVO, ModelMap model, HttpSession session) throws Exception {
		codeUsemapVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		codeUsemapVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeUsemapVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeUsemapVO.getPageUnit());
		paginationInfo.setPageSize(codeUsemapVO.getPageSize());

		codeUsemapVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeUsemapVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeUsemapVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeUsemapVO> items = codeUsemapService.selectCodeUsemapList(codeUsemapVO);
		return items;
	}	
 

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다. (pageing)
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/codeusemap/selectColumnListRest.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CodeUsemapVO>  selectColumnListRest(@RequestBody  CodeUsemapVO codeUsemapVO, ModelMap model, HttpSession session) throws Exception {
		codeUsemapVO.setUsePage(false);
		List<CodeUsemapVO> items = codeUsemapService.selectColumnList(codeUsemapVO);
		return items;
	}	
	
	
	
	/**
	 * 코드사용맵(TC_CODE_USEMAP) 상세를 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/codeusemap/selectCodeUsemap.do"  })
	public String selectCodeUsemap(@ModelAttribute("searchVO") CodeUsemapVO codeUsemapVO, ModelMap model) throws Exception {
	
		model.addAttribute("codeUsemapVO", codeUsemapService.selectCodeUsemap(codeUsemapVO));
		return "/manage/codeusemap/CodeUsemapView";
	}
	
	/**
	 * 코드사용맵(TC_CODE_USEMAP) 상세를 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return "/manage/codeusemap/CodeUsemapView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/codeusemap/selectCodeUsemap.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeUsemapVO selectCodeUsemapRest(@RequestBody  CodeUsemapVO codeUsemapVO, ModelMap model, HttpSession session) throws Exception {
		CodeUsemapVO codeUsemapVOOne = codeUsemapService.selectCodeUsemap(codeUsemapVO);
		return codeUsemapVOOne;
	}

	

	@RequestMapping(value = { "/manage/codeusemap/addCodeUsemapView.do" })
	public String addCodeUsemapView(@ModelAttribute("searchVO") CodeUsemapVO codeUsemapVO, Model model) throws Exception {
		model.addAttribute("codeUsemapVO", codeUsemapVO);
		codeUsemapVO.setOWNER("GPMS");
		model.addAttribute("tables", codeUsemapService.selectTables(codeUsemapVO));
		return "/mng/code/CodeUsemapRegist";
	}

	@RequestMapping(value = { "/manage/codeusemap/addCodeUsemap.do"  })
	public String addCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		codeUsemapService.insertCodeUsemap(codeUsemapVO);
		return "redirect:/manage/codeusemap/selectCodeUsemapList.do";
	}
	
	@RequestMapping(value = {  "/api/codeusemap/addCodeUsemap.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeUsemapVO addCodeUsemapRest(@RequestBody CodeUsemapVO codeUsemapVO, HttpSession session) throws Exception {
		
		BindBeansToActiveUser(codeUsemapVO);
		codeUsemapService.insertCodeUsemap(codeUsemapVO);
		codeUsemapVO.setResultSuccess("true");
		codeUsemapVO.setResultMSG("정상 등록되었습니다.");
		return codeUsemapVO;
	}
	

	@RequestMapping(value = { "/manage/codeusemap/updateCodeUsemapView.do" })
	public String updateCodeUsemapView(@ModelAttribute("searchVO") CodeUsemapVO codeUsemapVO, Model model) throws Exception {
		model.addAttribute("codeUsemapVO", codeUsemapService.selectCodeUsemap(codeUsemapVO));
		
		codeUsemapVO.setOWNER("GPMS");
		model.addAttribute("tables", codeUsemapService.selectTables(codeUsemapVO));
		List<CodeUsemapVO> columns = codeUsemapService.selectColumnList(codeUsemapVO);
		model.addAttribute("columns", columns);
		return "/mng/code/CodeUsemapUpdate";
	}

	@RequestMapping(value = { "/manage/codeusemap/updateCodeUsemap.do" })
	public String updateCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		codeUsemapService.updateCodeUsemap(codeUsemapVO);
		return "redirect:/manage/codeusemap/selectCodeUsemapList.do";
	}
	
	@RequestMapping(value = {  "/api/codeusemap/updateCodeUsemap.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeUsemapVO updateCodeUsemapRest(@RequestBody CodeUsemapVO codeUsemapVO, HttpSession session) throws Exception {
		
		BindBeansToActiveUser(codeUsemapVO);
		codeUsemapService.updateCodeUsemap(codeUsemapVO);
		codeUsemapVO.setResultSuccess("true");
		codeUsemapVO.setResultMSG("정상 수정되었습니다.");
		return codeUsemapVO;
	}

	@RequestMapping(value = { "/manage/codeusemap/deleteCodeUsemap.do" })
	public String deleteCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		codeUsemapService.deleteCodeUsemap(codeUsemapVO);
		return "redirect:/manage/codeusemap/selectCodeUsemapList.do";
	}
	
	@RequestMapping(value = {   "/api/codeusemap/deleteCodeUsemap.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CodeUsemapVO deleteCodeUsemapRest(@RequestBody CodeUsemapVO codeUsemapVO, HttpSession session) throws Exception {
		
		BindBeansToActiveUser(codeUsemapVO);
		codeUsemapService.deleteCodeUsemap(codeUsemapVO);
		codeUsemapVO.setResultSuccess("true");
		codeUsemapVO.setResultMSG("정상 삭제되었습니다.");
		return codeUsemapVO;
	}

}
