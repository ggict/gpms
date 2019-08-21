

package kr.go.gg.gpms.company.web;




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
import kr.go.gg.gpms.company.service.CompanyService;
import kr.go.gg.gpms.company.service.model.CompanyVO;



/**
 * @Class Name : CompanyController.java
 * @Description : Company Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("companyController")
public class CompanyController extends BaseController{

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다. (pageing)
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/company/companyList.do" })
	public String selectCompanyList(CompanyVO companyVO, ModelMap model) throws Exception {
		setSystem("mng");
		
		// 업체상태코드
		addCodeToModel("COST", model);

		return "/mng/company/companyList" ;
	}
	 
	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다. (pageing)
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/company/searchCompany.do" })
	public String searchCompanyList(CompanyVO companyVO, ModelMap model) throws Exception {
		// 업체상태코드
		addCodeToModel("COST", model);

		return "/company/searchCompany" ;
	}
	
	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다. (pageing)
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/company/selectCompanyList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCompanyListRest(@RequestBody CompanyVO companyVO, ModelMap model, HttpSession session) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(companyVO.getPage());
		paginationInfo.setRecordCountPerPage(companyVO.getPageUnit());
		paginationInfo.setPageSize(companyVO.getRows());
		
		companyVO.setUsePage(true);
		companyVO.setUSE_AT("Y");
		companyVO.setDELETE_AT("N");
			 

		companyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		companyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		companyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CompanyVO> items = companyService.selectCompanyList(companyVO);
		int totCnt = companyService.selectCompanyListTotalCount(companyVO);
		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", companyVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);
		
		return map;
	}
	
	
	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다. (pageing)
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/company/selectCompanyListPage.do" })
	public String selectCompanyListPage(CompanyVO companyVO, ModelMap model) throws Exception {
		companyVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		companyVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(companyVO.getPageUnit());
		paginationInfo.setPageSize(companyVO.getPageSize());

		companyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		companyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		companyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CompanyVO> items = companyService.selectCompanyList(companyVO);
		model.addAttribute("items", items);

		int totCnt = companyService.selectCompanyListTotalCount(companyVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/company/CompanyList" ;
	}
	
	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다. (pageing)
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/company/selectCompanyListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CompanyVO>  selectCompanyListPageRest(@RequestBody  CompanyVO companyVO, ModelMap model, HttpSession session) throws Exception {
		companyVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		companyVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(companyVO.getPageUnit());
		paginationInfo.setPageSize(companyVO.getPageSize());

		companyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		companyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		companyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CompanyVO> items = companyService.selectCompanyList(companyVO);
		return items;
	}	
 
	
	
	/**
	 * 업체정보(TN_COMPANY) 상세를 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/company/selectCompany.do"  })
	public String selectCompany(@ModelAttribute("searchVO") CompanyVO companyVO, ModelMap model) throws Exception {
	
		model.addAttribute("companyVO", companyService.selectCompany(companyVO));
		return "/manage/company/CompanyView";
	}
	
	/**
	 * 업체정보(TN_COMPANY) 상세를 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return "/manage/company/CompanyView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/company/selectCompany.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CompanyVO selectCompanyRest(@RequestBody  CompanyVO companyVO, ModelMap model, HttpSession session) throws Exception {
		CompanyVO companyVOOne = companyService.selectCompany(companyVO);
		return companyVOOne;
	}

	

	@RequestMapping(value = { "/manage/company/addCompanyView.do" })
	public String addCompanyView(@ModelAttribute("searchVO") CompanyVO companyVO, ModelMap model) throws Exception {
		model.addAttribute("companyVO", new CompanyVO());
		// 업체상태코드
		addCodeToModel("COST", model);
		return "/mng/company/CompanyRegist";
	}

	@RequestMapping(value = { "/manage/company/addCompany.do"  })
	public String addCompany(CompanyVO companyVO) throws Exception {
		companyService.insertCompany(companyVO);
		return "redirect:/manage/company/selectCompanyList.do";
	}
	
	@RequestMapping(value = {  "/api/company/addCompany.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CompanyVO addCompanyRest(@RequestBody CompanyVO companyVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(companyVO);
		companyService.insertCompany(companyVO);
		companyVO.setResultSuccess("true");
		companyVO.setResultMSG("정상 등록되었습니다.");
		
		return companyVO;
	}
	

	@RequestMapping(value = { "/manage/company/updateCompanyView.do" })
	public String updateCompanyView(@ModelAttribute("searchVO") CompanyVO companyVO, ModelMap model) throws Exception {
		model.addAttribute("companyVO", companyService.selectCompany(companyVO));
		// 업체상태코드
		addCodeToModel("COST", model);
		return "/mng/company/CompanyUpdate";
	}

	@RequestMapping(value = { "/manage/company/updateCompany.do" })
	public String updateCompany(CompanyVO companyVO) throws Exception {
		companyService.updateCompany(companyVO);
		return "redirect:/manage/company/selectCompanyList.do";
	}
	
	@RequestMapping(value = {  "/api/company/updateCompany.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CompanyVO updateCompanyRest(@RequestBody CompanyVO companyVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(companyVO);
		companyService.updateCompany(companyVO);
		companyVO.setResultSuccess("true");
		companyVO.setResultMSG("정상 수정되었습니다.");
		
		return companyVO;
	}

	@RequestMapping(value = { "/manage/company/deleteCompany.do" })
	public String deleteCompany(CompanyVO companyVO) throws Exception {
		companyService.deleteCompany(companyVO);
		return "redirect:/manage/company/selectCompanyList.do";
	}
	
	@RequestMapping(value = {   "/api/company/deleteCompany.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CompanyVO deleteCompanyRest(@RequestBody CompanyVO companyVO, HttpSession session) throws Exception {
		
		BindBeansToActiveUser(companyVO);
		companyService.deleteCompany(companyVO);
		companyVO.setResultSuccess("true");
		companyVO.setResultMSG("정상 삭제되었습니다.");
		return companyVO;
		
	}

}
