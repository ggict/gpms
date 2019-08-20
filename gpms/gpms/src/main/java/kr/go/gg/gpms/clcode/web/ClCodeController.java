

package kr.go.gg.gpms.clcode.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.clcode.service.ClCodeService;
import kr.go.gg.gpms.clcode.service.model.ClCodeVO;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.codeusemap.service.CodeUsemapService;
import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapVO;

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
 * @Class Name : ClCodeController.java
 * @Description : ClCode Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("clCodeController")
public class ClCodeController extends BaseController {

	@Resource(name = "clCodeService")
	private ClCodeService clCodeService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "codeUsemapService")
	private CodeUsemapService codeUsemapService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClCodeController.class);

	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다. (pageing)
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/clcode/selectClCodeList.do" })
	public String selectClCodeList(ClCodeVO clCodeVO, ModelMap model) throws Exception {
		clCodeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		clCodeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(clCodeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(clCodeVO.getPageUnit());
		paginationInfo.setPageSize(clCodeVO.getPageSize());

		clCodeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		clCodeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		clCodeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<ClCodeVO> items = clCodeService.selectClCodeList(clCodeVO);
		model.addAttribute("items", items);

		int totCnt = clCodeService.selectClCodeListTotalCount(clCodeVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/clcode/ClCodeList" ;
	}
	
	
	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다. (pageing)
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/clcode/codeList.do" })
	public String selectCodeList(ClCodeVO clCodeVO, ModelMap model) throws Exception {
		setSystem("mng");
		clCodeVO.setUsePage(false);		
		List<ClCodeVO> items = clCodeService.selectClCodeList(clCodeVO);
		model.addAttribute("items", items);
		return "/mng/code/codeList" ;
	}
	
	
	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다. (pageing)
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/clcode/selectClCodeList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectClCodeListRest(@RequestBody ClCodeVO clCodeVO, ModelMap model, HttpSession session) throws Exception {
		int pageIndex = 1;
		//clCodeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		//clCodeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		//PaginationInfo paginationInfo = new PaginationInfo();
		//paginationInfo.setCurrentPageNo(clCodeVO.getPageIndex());
		//paginationInfo.setRecordCountPerPage(clCodeVO.getPageUnit());
		//paginationInfo.setPageSize(clCodeVO.getPageSize());

		//clCodeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		//clCodeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		//clCodeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		clCodeVO.setUsePage(false);
		int total_count = clCodeService.selectClCodeListTotalCount(clCodeVO);
		List<ClCodeVO> items = clCodeService.selectClCodeList(clCodeVO);
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
	 * 분류코드(TC_CL_CODE) 목록을 조회한다. (pageing)
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/clcode/selectClCodeListPage.do" })
	public String selectClCodeListPage(ClCodeVO clCodeVO, ModelMap model) throws Exception {
		clCodeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		clCodeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(clCodeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(clCodeVO.getPageUnit());
		paginationInfo.setPageSize(clCodeVO.getPageSize());

		clCodeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		clCodeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		clCodeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<ClCodeVO> items = clCodeService.selectClCodeList(clCodeVO);
		model.addAttribute("items", items);

		int totCnt = clCodeService.selectClCodeListTotalCount(clCodeVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/clcode/ClCodeList" ;
	}
	
	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다. (pageing)
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/clcode/selectClCodeListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<ClCodeVO>  selectClCodeListPageRest(@RequestBody  ClCodeVO clCodeVO, ModelMap model, HttpSession session) throws Exception {
		clCodeVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		clCodeVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(clCodeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(clCodeVO.getPageUnit());
		paginationInfo.setPageSize(clCodeVO.getPageSize());

		clCodeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		clCodeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		clCodeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<ClCodeVO> items = clCodeService.selectClCodeList(clCodeVO);
		return items;
	}	
 
	
	
	/**
	 * 분류코드(TC_CL_CODE) 상세를 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/clcode/selectClCode.do"  })
	public String selectClCode(@ModelAttribute("searchVO") ClCodeVO clCodeVO, ModelMap model) throws Exception {
	
		model.addAttribute("clCodeVO", clCodeService.selectClCode(clCodeVO));
		return "/manage/clcode/ClCodeView";
	}
	
	/**
	 * 분류코드(TC_CL_CODE) 상세를 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return "/manage/clcode/ClCodeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/clcode/selectClCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ClCodeVO selectClCodeRest(@RequestBody  ClCodeVO clCodeVO, ModelMap model, HttpSession session) throws Exception {
		ClCodeVO clCodeVOOne = clCodeService.selectClCode(clCodeVO);
		return clCodeVOOne;
	}

	

	@RequestMapping(value = { "/manage/clcode/addClCodeView.do" })
	public String addClCodeView(@ModelAttribute("searchVO") ClCodeVO clCodeVO, Model model) throws Exception {
		model.addAttribute("clCodeVO", new ClCodeVO());
		return "/mng/code/ClCodeRegist";
	}

	@RequestMapping(value = { "/manage/clcode/addClCode.do"  })
	public String addClCode(ClCodeVO clCodeVO) throws Exception {
		clCodeService.insertClCode(clCodeVO);
		return "redirect:/manage/clcode/selectClCodeList.do";
	}
	
	@RequestMapping(value = {  "/api/clcode/addClCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ClCodeVO addClCodeRest(@RequestBody ClCodeVO clCodeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(clCodeVO);
		clCodeService.insertClCode(clCodeVO);
		clCodeVO.setResultSuccess("true");
		clCodeVO.setResultMSG("정상 등록되었습니다.");
		return clCodeVO;
	}
	

	@RequestMapping(value = { "/manage/clcode/updateClCodeView.do" })
	public String updateClCodeView(@ModelAttribute("searchVO") ClCodeVO clCodeVO, Model model) throws Exception {
		model.addAttribute("clCodeVO", clCodeService.selectClCode(clCodeVO));
		return "/mng/code/ClCodeUpdate";
	}

	@RequestMapping(value = { "/manage/clcode/updateClCode.do" })
	public String updateClCode(ClCodeVO clCodeVO) throws Exception {
		clCodeService.updateClCode(clCodeVO);
		return "redirect:/manage/clcode/selectClCodeList.do";
	}
	
	@RequestMapping(value = {  "/api/clcode/updateClCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ClCodeVO updateClCodeRest(@RequestBody ClCodeVO clCodeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(clCodeVO);
		clCodeService.updateClCode(clCodeVO);
		clCodeVO.setResultSuccess("true");
		clCodeVO.setResultMSG("정상 수정되었습니다.");
		return clCodeVO;
	}

	@RequestMapping(value = { "/manage/clcode/deleteClCode.do" })
	public String deleteClCode(ClCodeVO clCodeVO) throws Exception {
		clCodeService.deleteClCode(clCodeVO);
		return "redirect:/manage/clcode/selectClCodeList.do";
	}
	
	@RequestMapping(value = {   "/api/clcode/deleteClCode.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ClCodeVO deleteClCodeRest(@RequestBody ClCodeVO clCodeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(clCodeVO);
		clCodeService.deleteClCode(clCodeVO);
		
		CodeVO codeVO = new CodeVO();
		BindBeansToActiveUser(codeVO);
		codeVO.setCL_CODE(clCodeVO.getCL_CODE());
		codeService.deleteCode(codeVO);
		
		CodeUsemapVO codeUsemapVO = new CodeUsemapVO();
		BindBeansToActiveUser(codeUsemapVO);
		codeUsemapVO.setCL_CODE(codeVO.getCL_CODE());
		codeUsemapService.deleteCodeUsemap(codeUsemapVO);
		
		clCodeVO.setResultSuccess("true");
		clCodeVO.setResultMSG("정상 삭제되었습니다.");
		return clCodeVO;
	}

}
