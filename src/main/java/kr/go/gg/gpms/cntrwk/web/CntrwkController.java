

package kr.go.gg.gpms.cntrwk.web;




import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrwk.service.CntrwkService;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.cntrwkcellinfo.service.CntrwkCellInfoService;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;
import kr.go.gg.gpms.cntrwkdtl.service.CntrwkDtlService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.flawcntrwk.service.FlawCntrwkService;
import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.rpairtrgetgroup.service.RpairTrgetGroupService;
import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;
import kr.go.gg.gpms.rpairtrgetslctn.service.RpairTrgetSlctnService;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.View;

import egovframework.cmmn.util.DateUtil;
import egovframework.cmmn.util.ExcelView;
import egovframework.cmmn.util.NumberUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



/**
 * @Class Name : CntrwkController.java
 * @Description : Cntrwk Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("cntrwkController")
public class CntrwkController  extends BaseController {

	@Resource(name = "cntrwkService")
	private CntrwkService cntrwkService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "cntrwkDtlService")
	private CntrwkDtlService cntrwkDtlService;
	@Resource(name = "cntrwkCellInfoService")
	private CntrwkCellInfoService cntrwkCellInfoService;	
	@Resource(name = "flawCntrwkService")
	private FlawCntrwkService flawCntrwkService;
	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;
	@Resource(name = "rpairTrgetSlctnService")
	private RpairTrgetSlctnService rpairTrgetSlctnService;
	@Resource(name = "rpairTrgetGroupService")
	private RpairTrgetGroupService rpairTrgetGroupService;
		
	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CntrwkController.class);

	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwk/selectCntrwkList.do" })
	public String selectCntrwkList(CntrwkVO cntrwkVO, ModelMap model) throws Exception {
		//공사 구분
		addCodeToModel("CWSE", model);

		return "/cntrwk/cntrwkList" ;
	}
	
	
	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cntrwk/selectCntrwkList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectCntrwkListRest(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {	
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkVO.getPage());
		paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkVO.getRows());
		
		cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		System.out.println("cntrwkVO.getSCH_STRWRK_DE()"+cntrwkVO.getSCH_STRWRK_DE());
		String SCH_STRWRK_DE = cntrwkVO.getSCH_STRWRK_DE();
		String SCH_COMPET_DE = cntrwkVO.getSCH_COMPET_DE();
		
		cntrwkVO.setSCH_STRWRK_DE(SCH_STRWRK_DE.replace("-",""));
		cntrwkVO.setSCH_COMPET_DE(SCH_COMPET_DE.replace("-",""));
		System.out.println("SCH_COMPET_DE"+cntrwkVO.getSCH_COMPET_DE());
		List<CntrwkVO> items = cntrwkService.selectCntrwkList(cntrwkVO);
		int total_count = cntrwkService.selectCntrwkListTotalCount(cntrwkVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cntrwkVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", cntrwkVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
	
		return map;
	}
	
	
	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwk/selectCntrwkListPage.do" })
	public String selectCntrwkListPage(CntrwkVO cntrwkVO, ModelMap model) throws Exception {
		cntrwkVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkVO.getPageSize());

		cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkVO> items = cntrwkService.selectCntrwkList(cntrwkVO);
		model.addAttribute("items", items);

		int totCnt = cntrwkService.selectCntrwkListTotalCount(cntrwkVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/cntrwk/CntrwkList" ;
	}
	
	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cntrwk/selectCntrwkListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CntrwkVO>  selectCntrwkListPageRest(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {
		cntrwkVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkVO.getPageSize());

		cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkVO> items = cntrwkService.selectCntrwkList(cntrwkVO);
		return items;
	}	
 
    /**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
     * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
     * @return "/cntrwk/CntrwkList"
     * @exception Exception
     */
    @RequestMapping(value = {  "/api/cntrwk/selectCntrwkListByItgrtn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectCntrwkListByItgrtn(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {    
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cntrwkVO.getPage());
        paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
        paginationInfo.setPageSize(cntrwkVO.getRows());
        
        cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
        cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        
        // CELL_ID 리스트화 - IN 절에 넘길 파라미터 생성
        String[] arr = cntrwkVO.getCELL_ID().split(",");
        List<String> cellArr = new ArrayList<String>();
        
        for ( int i = 0; i < arr.length; i++ ) {
        
            cellArr.add(arr[i]);

        }
        
        cntrwkVO.setCELL_ID_ARR(cellArr);

        Map<String, Object> map = new HashMap<String, Object>();
        
        List<CntrwkVO> items = cntrwkService.selectCntrwkListByItgrtn(cntrwkVO);
        int total_count = cntrwkService.selectCntrwkListByItgrtnCnt(cntrwkVO);
        
        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) cntrwkVO.getPageSize());
        // 결과 JSON 저장
        
    
        map.put("page", cntrwkVO.getPage());
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);
    
        return map;
    }
	
    /**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
     * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
     * @return "/cntrwk/CntrwkList"
     * @exception Exception
     */
    @RequestMapping(value = {  "/api/cntrwk/selectCntrwkListBySctn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectCntrwkListBySctn(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {    
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cntrwkVO.getPage());
        paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
        paginationInfo.setPageSize(cntrwkVO.getRows());
        
        cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
        cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        List<CntrwkVO> items = cntrwkService.selectCntrwkListBySctn(cntrwkVO);
        int total_count = cntrwkService.selectCntrwkListBySctnCnt(cntrwkVO);
        
        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) cntrwkVO.getPageSize());
        // 결과 JSON 저장
        
    
        map.put("page", cntrwkVO.getPage());
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);
    
        return map;
    }
	
	
	
	
	/**
	 * 공사정보(TN_CNTRWK) 상세를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwk/selectCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkVO selectCntrwkRest(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {
		CntrwkVO cntrwkVOOne = cntrwkService.selectCntrwk(cntrwkVO);
		return cntrwkVOOne;
	}

	
	/**
	 * 사용함
	 * @param cntrwkVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/cntrwk/addCntrwkView.do" })
	public String addCntrwkView(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, CompanyVO companyVO, ModelMap model) throws Exception {
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(new DeptVO()));
		model.addAttribute("cntrwkVO", new CntrwkVO());
		Integer cntrwk_start_year  = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
		addCodeToModel("HTSE", model);
		//공사 구분
		addCodeToModel("CWSE", model);
		//공사 분류
		addCodeToModel("CWCL", model);

		//공사 업체
		model.addAttribute("companyList", getCompanyList());
				
		return "/cntrwk/cntrwkRegist";
	}

	@RequestMapping(value = { "/cntrwk/addCntrwk.do"  })
	public String addCntrwk(@ModelAttribute CntrwkVO cntrwkVO, ModelMap model,BindingResult bindingResult,HttpServletRequest request, SessionStatus status, HttpSession session) throws Exception {
		// 처리 상태
		Map<String, String> req = requestToHashMap(request);
		
    	String action_flag = StringUtils.isNotEmpty(req.get("action_flag")) ?
    			req.get("action_flag").trim():"INSERT" ;
    	// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";
		try {
			resultCode = "SAVE_SUCCESS";
			BindBeansToActiveUser(cntrwkVO);
			cntrwkService.insertCntrwk(cntrwkVO);
			model.addAttribute("insertKey", cntrwkVO.getCNTRWK_ID());
			cntrwkVO.setResultSuccess("true");
			cntrwkVO.setResultMSG("정상 등록되었습니다.");
			// 결과 처리용 [수정X]
	    	model.addAttribute("resultCode", resultCode);
	    	model.addAttribute("resultMsg", resultMsg);
	    	model.addAttribute("callBackFunction", StringUtils.isNotEmpty(cntrwkVO.getCallBackFunction()) ?
	    			cntrwkVO.getCallBackFunction().trim():"" );	// 처리후 호출 함수

	    	status.setComplete();	//Double Submit 방지
		} catch (Exception e) {
			resultCode = "ERROR";
    		resultMsg = "등록 오류 발생";
    		LOGGER.error("공사등록 오류", e);    		
		}
		
		return "/cmmn/commonMsg";
	}
	
	@RequestMapping(value = { "/cntrwk/updateCntrwk.do" })
	public String updateCntrwk(@ModelAttribute CntrwkVO cntrwkVO, ModelMap model,BindingResult bindingResult,HttpServletRequest request, SessionStatus status, HttpSession session) throws Exception {
		Map<String, String> req = requestToHashMap(request);
		
		String action_flag = StringUtils.isNotEmpty(req.get("action_flag"))?
				req.get("action_flag").trim():"UPDATE" ;
		// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";
		try {
			resultCode = "UPDATE_SUCCESS";
			BindBeansToActiveUser(cntrwkVO);
			CntrwkVO cntrwkVOOne = cntrwkService.selectCntrwk(cntrwkVO);
			BeanUtils.copyProperties(cntrwkVOOne, cntrwkVO);
			BindBeansToActiveUser(cntrwkVOOne);
			
			cntrwkService.updateCntrwk(cntrwkVOOne);
			model.addAttribute("insertKey", cntrwkVOOne.getCNTRWK_ID());
			cntrwkVO.setResultSuccess("true");
			cntrwkVO.setResultMSG("정상 수정되었습니다.");
			// 결과 처리용 [수정X]
	    	model.addAttribute("resultCode", resultCode);
	    	model.addAttribute("resultMsg", resultMsg);
	    	model.addAttribute("callBackFunction", StringUtils.isNotEmpty(cntrwkVO.getCallBackFunction()) ?
	    			cntrwkVO.getCallBackFunction().trim():"" );	// 처리후 호출 함수

	    	status.setComplete();	//Double Submit 방지
		} catch (Exception e) {
			resultCode = "ERROR";
    		resultMsg = "등록 오류 발생";
    		LOGGER.error("공사등록 오류", e);    		
		}
		
		return "/cmmn/commonMsg";
	 
	}
	
	@RequestMapping(value = {  "/api/cntrwk/addCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkVO addCntrwkRest(@RequestBody CntrwkVO cntrwkVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrwkVO);
		cntrwkService.insertCntrwk(cntrwkVO);
		cntrwkVO.setResultSuccess("true");
		cntrwkVO.setResultMSG("정상 등록되었습니다.");
		return cntrwkVO;
	}
	

	@RequestMapping(value = { "/cntrwk/updateCntrwkView.do" })
	public String updateCntrwkView(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, CompanyVO companyVO, ModelMap model) throws Exception {
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(new DeptVO()));		
		Integer cntrwk_start_year  = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
		addCodeToModel("HTSE", model);
		//공사 구분
		addCodeToModel("CWSE", model);
		//공사 분류
		addCodeToModel("CWCL", model);
		
		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));
		
		//공사 업체
		model.addAttribute("companyList", getCompanyList());
		
		return "/cntrwk/cntrwkUpdate";
	}
	
	@RequestMapping(value = { "/cntrwk/selectCntrwkView.do" })
	public String selectCntrwkView(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, ModelMap model) throws Exception {
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(new DeptVO()));		
		Integer cntrwk_start_year  = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
		addCodeToModel("HTSE", model);
		//공사 구분
		addCodeToModel("CWSE", model);
		//공사 분류
		addCodeToModel("CWCL", model);
		
		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));
		return "/cntrwk/cntrwkView";
	}
	
	/**
	 * 공사정보(TN_CNTRWK) 상세를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwk/selectCntrwk.do"  })
	public String selectCntrwk(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, ModelMap model) throws Exception {
	
		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));
		return "/cntrwk/cntrwkUpdate";
	}

	
	
	@RequestMapping(value = {  "/api/cntrwk/updateCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkVO updateCntrwkRest(@RequestBody CntrwkVO cntrwkVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrwkVO);
		cntrwkService.updateCntrwk(cntrwkVO);
		cntrwkVO.setResultSuccess("true");
		cntrwkVO.setResultMSG("정상 수정되었습니다.");
		return cntrwkVO;
	}

	@RequestMapping(value = { "/cntrwk/deleteCntrwk.do" })
	public String deleteCntrwk(CntrwkVO cntrwkVO) throws Exception {
		BindBeansToActiveUser(cntrwkVO);
		cntrwkService.deleteCntrwk(cntrwkVO);
		cntrwkVO.setResultSuccess("true");
		cntrwkVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/cntrwk/selectCntrwkList.do";
	}
	
	@RequestMapping(value = {   "/api/cntrwk/deleteCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkVO deleteCntrwkRest(@RequestBody CntrwkVO cntrwkVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrwkVO);
		cntrwkService.deleteCntrwk(cntrwkVO);
		
		CntrwkDtlVO cntrwkDtlVO = new CntrwkDtlVO();
		cntrwkDtlVO.setCNTRWK_ID(cntrwkVO.getCNTRWK_ID());
		BindBeansToActiveUser(cntrwkDtlVO);
		cntrwkDtlService.deleteCntrwkDtlByCntrwkId(cntrwkDtlVO);
		
		FlawCntrwkVO flawCntrwkVO = new FlawCntrwkVO();
		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkVO.setCNTRWK_ID(cntrwkVO.getCNTRWK_ID());
		flawCntrwkService.deleteFlawCntrwkByCntrwkDtlID(flawCntrwkVO);
		
		CntrwkCellInfoVO cntrwkCellInfoVO = new CntrwkCellInfoVO();
		cntrwkCellInfoVO.setCNTRWK_ID(cntrwkVO.getCNTRWK_ID());
		cntrwkCellInfoService.deleteCntrwkCellInfo(cntrwkCellInfoVO);		
		
		cntrwkVO.setResultSuccess("true");
		cntrwkVO.setResultMSG("정상 삭제되었습니다.");
		return cntrwkVO;
	}
	
	@RequestMapping(value="/api/cntrwk/cntrwkListExcel.do")
    public View cntrwkListExcel(@ModelAttribute CntrwkVO cntrwkVO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cntrwkService.selectCntrwkListExcel(cntrwkVO);
        
        String[] excel_title  = {"공사구분","공사명","착공일","준공일","하자시작일","하자종료일","시공사","총 공사연장(km)","총 보수금액(천원)","총 공사면적(m²)"};
        String[] excel_column = {"cntrwk_se","full_cntrwk_nm","strwrk_de","compet_de","flaw_begin_de","flaw_end_de","cnstrct_co_nm","track_len","tot_amount","rpair_ar"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "포장공사목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }
	
	//유지보수 실적집계 엑셀출력 화면
	@RequestMapping(value = { "/cntrwk/setDownloadReport.do" })
	public String setDownloadReport(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, DeptVO deptVO, Model model, HttpServletRequest request) throws Exception {
		//공사별 연도 목록
		model.addAttribute("yearList", cntrwkService.selectCntrwkYearList(cntrwkVO));
		//사업소 목록
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(deptVO));
		
		return "/cntrwk/cntrwkReportView";
	}
	
	//유지보수 실적집계 엑셀출력
	@RequestMapping(value = { "/api/cntrwk/downloadReport.do" })
	public String downloadReport(@ModelAttribute("searchVO") CntrwkVO cntrwkVO, DeptVO deptVO, HttpServletResponse response, Model model, HttpServletRequest request) throws Exception {
		
		String filename = request.getSession().getServletContext().getRealPath("/") 
				+ "template/template_cntrwkSummaryExcel.xls";
		
		FileInputStream fis = new FileInputStream(checkFilePath(filename, "path"));
   	 	POIFSFileSystem fs = new POIFSFileSystem(fis);
   	 	HSSFWorkbook wb = new HSSFWorkbook(fs);
   	 	HSSFSheet sheet = wb.getSheetAt(0);
   	 	
   	 	try {
   	 		String year = cntrwkVO.getCNTRWK_YEAR() + "년";
   	 		sheet.getRow(0).getCell(0).setCellValue(year);
   	 		
   	 		if(cntrwkVO.getDEPT_CODE().isEmpty()) {
   	 			sheet.getRow(4).getCell(0).setCellValue("전체");
   	 		} else {
   	 			deptVO.setDEPT_CODE(cntrwkVO.getDEPT_CODE());
   	 			deptVO = deptService.selectDept(deptVO);
   	 			
   	 			sheet.getRow(4).getCell(0).setCellValue(deptVO.getDEPT_NM());
   	 		}
   	 		
   	 		//CNTRWK_SE
   	 		//CNTRWK_SE : 공사 구분
   	 		//CWSE0001 : 노후포장도로정비
   	 		//CWSE0002 : 긴급복구 연간단가
   	 		//CWSE0003 : 굴착복구공사(사후정비비)
   	 		//CWSE0006 : 기타
   	 		
   	 		Map map; 
   	 		HSSFRow row; 
   	 		int iRow = 8; 
   	 		int iBaseCursor = iRow;
   	 		
   	 		//총계
	        List<?> dataList; HashMap hMap;
   	 		
	        hMap = cntrwkService.selectCntrwkHistTotalExcel(cntrwkVO);
	        sheet.getRow(4).getCell(4).setCellValue(hMap.get("route_cn").toString() + "개 노선 " + hMap.get("detail_cntrwk_cn").toString() + "개소 정비");
	        sheet.getRow(4).getCell(5).setCellValue(nvl(hMap.get("min_rpair_bt")) + "-" + nvl(hMap.get("MAX_RPAIR_BT")));
	        sheet.getRow(4).getCell(6).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_len")));
	        sheet.getRow(4).getCell(7).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_ar")) / 100);
	        sheet.getRow(4).getCell(8).setCellValue(NumberUtil.parseDouble(hMap.get("cntrwk_amount")) / 1000000);
	        
	        //노후포장도로 정비
	        cntrwkVO.setCNTRWK_SE("CWSE0001");
	        hMap = cntrwkService.selectCntrwkHistTotalExcel(cntrwkVO);
	        //소계
	        iBaseCursor = setHeaderCell(sheet, 5, hMap, iBaseCursor, "노후포장도로 정비", wb);
	        sheet.getRow(iBaseCursor).getCell(0).getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_THIN);
   	 		
	        //본문
	        iRow = iBaseCursor + 1;
	        dataList = cntrwkService.selectCntrwkHistListExcel(cntrwkVO);
	        for (int i = 0; i < dataList.size(); i++) {
				map = (Map) dataList.get(i);
				iBaseCursor = iRow + i;
				row = sheet.createRow(iBaseCursor);
				setLoopCell(row, map);
			}
	        sheet.addMergedRegion(new CellRangeAddress(iRow - 1, iBaseCursor, 0, 0));
	        
	        //긴급복구 연간단가
	        cntrwkVO.setCNTRWK_SE("CWSE0002");
	        hMap = cntrwkService.selectCntrwkHistTotalExcel(cntrwkVO);
	        //소계
	        iBaseCursor = setHeaderCell(sheet, 6, hMap, iBaseCursor, "긴급복구 연간단가", wb);
	        sheet.getRow(iBaseCursor).getCell(0).getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        
	        //본문
	        iRow = iBaseCursor + 1;
	        dataList = cntrwkService.selectCntrwkHistListExcel(cntrwkVO);
	        for (int i = 0; i < dataList.size(); i++) {
				map = (Map) dataList.get(i);
				iBaseCursor = iRow + i;
				row = sheet.createRow(iBaseCursor);
				setLoopCell(row, map);
			}
	        sheet.addMergedRegion(new CellRangeAddress(iRow - 1, iBaseCursor, 0, 0));
	        
	        //굴착복구공사
	        cntrwkVO.setCNTRWK_SE("CWSE0003");
	        hMap = cntrwkService.selectCntrwkHistTotalExcel(cntrwkVO);
	        //소계
	        iBaseCursor = setHeaderCell(sheet, 7, hMap, iBaseCursor, "굴착복구공사", wb);
	        sheet.getRow(iBaseCursor).getCell(0).getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_THIN);
   	 		
	        //본문
	        iRow = iBaseCursor + 1;
	        dataList = cntrwkService.selectCntrwkHistListExcel(cntrwkVO);
	        for (int i = 0; i < dataList.size(); i++) {
				map = (Map) dataList.get(i);
				iBaseCursor = iRow + i;
				row = sheet.createRow(iBaseCursor);
				setLoopCell(row, map);
			}
	        sheet.addMergedRegion(new CellRangeAddress(iRow - 1, iBaseCursor, 0, 0));
	        
	        //기타
	        cntrwkVO.setCNTRWK_SE("dataList");
	        hMap = cntrwkService.selectCntrwkHistTotalExcel(cntrwkVO);
	        //소계
	        iBaseCursor = setHeaderCell(sheet, 8, hMap, iBaseCursor, "기타", wb);
	        sheet.getRow(iBaseCursor).getCell(0).getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_THIN);
   	 		
	        //본문
	        iRow = iBaseCursor + 1;
	        dataList = cntrwkService.selectCntrwkHistListExcel(cntrwkVO);
	        for (int i = 0; i < dataList.size(); i++) {
				map = (Map) dataList.get(i);
				iBaseCursor = iRow + i;
				row = sheet.createRow(iBaseCursor);
				setLoopCell(row, map);
			}
	        
	        CellRangeAddress cellRangeAddress = new CellRangeAddress(iRow - 1, iBaseCursor, 0, 0);
	        sheet.addMergedRegion(cellRangeAddress);
	        
	        //Last Row, First Column : Bottom Border
			HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM, cellRangeAddress, sheet, wb);
			//Last Row, except First Column
	        cellRangeAddress = new CellRangeAddress(iBaseCursor, iBaseCursor, 1, 19);
			HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM, cellRangeAddress, sheet, wb);
			
			//셀에 맞춤
	        cellRangeAddress = new CellRangeAddress(10, iBaseCursor, 1, 19);
	        HSSFCellStyle cs;
	        for (int i = 10; i <= iBaseCursor; i++) {
				for (int j = 1; j <= 21; j++) {
					cs = sheet.getRow(i).getCell(j).getCellStyle();
					cs.setShrinkToFit(true);
			        sheet.getRow(i).getCell(j).setCellStyle(cs);
				}
			}
	        
	    	fis.close();
	 		response.setContentType("Application/Msexcel");
	        response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode(year + "_노후포장도로_정비공사","UTF-8")+".xls");
	 		ServletOutputStream out = response.getOutputStream();
	 		wb.write(out);
	 		out.flush(); 
   	 		
   	 	} catch (Exception e) {
   	 		fis.close(); 
	    	// 결과 처리용 [수정X]
	    	model.addAttribute("resultCode", "ERROR");
	    	model.addAttribute("resultMsg", "엑셀저장 오류발생");
	    	model.addAttribute("callBackFunction", nvl(cntrwkVO.getCallBackFunction()));	// 처리후 호출 함수
	    	
	    	return "/cmmn/commonMsg";
	    	
   	 	} finally {
	    	fis.close();
		}
	   	 
		return null;
	}
	
	 private int setHeaderCell(HSSFSheet sheet, int fixedLoc, HashMap hMap, int iBaseCursor, String subTitle, HSSFWorkbook wb) throws Exception{
    	//상단
	 	sheet.getRow(fixedLoc).getCell(4).setCellValue(hMap.get("route_cn").toString() + "개 노선 " + hMap.get("detail_cntrwk_cn").toString() + "개소 정비");
        sheet.getRow(fixedLoc).getCell(5).setCellValue(nvl(hMap.get("min_rpair_bt")) + "-" + nvl(hMap.get("max_rpair_bt")));
        sheet.getRow(fixedLoc).getCell(6).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_len")));
        sheet.getRow(fixedLoc).getCell(7).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_ar")) / 100);
        sheet.getRow(fixedLoc).getCell(8).setCellValue(NumberUtil.parseDouble(hMap.get("cntrwk_amount")) / 1000000);
        //하부
        iBaseCursor = iBaseCursor + 1;
		HSSFRow row = sheet.createRow(iBaseCursor);
		row.createCell(0).setCellValue(subTitle);
		row.createCell(1).setCellValue("소 계");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue(hMap.get("route_cn").toString() + "개 노선 " + hMap.get("detail_cntrwk_cn").toString() + "개소 정비");
		row.createCell(5).setCellValue(nvl(hMap.get("min_rpair_bt")) + "-" + nvl(hMap.get("max_rpair_bt")));
		row.createCell(6).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_len")));
		row.createCell(7).setCellValue(NumberUtil.parseDouble(hMap.get("rpair_ar")) / 100);
		row.createCell(8).setCellValue(NumberUtil.parseDouble(hMap.get("cntrwk_amount")) / 1000000);
		row.createCell(9).setCellValue("");
		row.createCell(10).setCellValue("");
		row.createCell(11).setCellValue("");
		row.createCell(12).setCellValue("");
		row.createCell(13).setCellValue("");
		row.createCell(14).setCellValue("");
		row.createCell(15).setCellValue("");
		row.createCell(16).setCellValue("");
		row.createCell(17).setCellValue("");
		row.createCell(18).setCellValue("");
		row.createCell(19).setCellValue("");
		row.createCell(20).setCellValue("");
		row.createCell(21).setCellValue("");

		CellRangeAddress cellRangeAddress = new CellRangeAddress(iBaseCursor, iBaseCursor, 1, 21);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_MEDIUM, cellRangeAddress, sheet, wb);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		return iBaseCursor;
    }
	    
    private void setLoopCell(HSSFRow row, Map map) throws Exception {
		row.createCell(1).setCellValue(nvl(map.get("route_nm")));
		row.createCell(2).setCellValue(nvl(map.get("road_nm")));
		row.createCell(3).setCellValue(nvl(map.get("cntrwk_cl_nm")));
		row.createCell(4).setCellValue(nvl(map.get("detail_cntrwk_nm")));
		row.createCell(5).setCellValue(NumberUtil.parseDouble(map.get("rpair_bt")));
		row.createCell(6).setCellValue(NumberUtil.parseDouble(map.get("rpair_len")));
		row.createCell(7).setCellValue(NumberUtil.parseDouble(map.get("rpair_ar")) / 100);
		row.createCell(8).setCellValue(NumberUtil.parseDouble(map.get("cntrwk_amount")) / 1000000);
		row.createCell(9).setCellValue(nvl(map.get("rpair_matrl_prdct_co_nm")));
		row.createCell(10).setCellValue(nvl(map.get("cnstrct_co_nm")));
		row.createCell(11).setCellValue(nvl(map.get("before_pav_year")));
		row.createCell(12).setCellValue(nvl(map.get("rpair_begin_de")));
		row.createCell(13).setCellValue(nvl(map.get("rpair_end_de")));
		row.createCell(14).setCellValue(NumberUtil.parseDouble(map.get("rpair_thick_ascon")));
		row.createCell(15).setCellValue(NumberUtil.parseDouble(map.get("rpair_thick_cntr")));
		row.createCell(16).setCellValue(NumberUtil.parseDouble(map.get("rpair_thick_base")));
		row.createCell(17).setCellValue(nvl(map.get("pav_matrl_ascon_nm")));
		row.createCell(18).setCellValue(nvl(map.get("pav_matrl_cntr_nm")));
		row.createCell(19).setCellValue(nvl(map.get("pav_matrl_base_nm")));
		row.createCell(20).setCellValue(nvl(map.get("dept_nm")));
		row.createCell(21).setCellValue(nvl(map.get("full_cntrwk_nm")));
    }
	    
    /**
	 * 공사정보(TN_CNTRWK)에 따른 셀ID(CELL_10)를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/api/cntrwk/selectCntrwkCellId
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cntrwk/selectCntrwkCellId.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectCntrwkCellId(@RequestBody  CntrwkVO cntrwkVO, ModelMap model, HttpSession session) throws Exception {	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", cntrwkService.selectCntrwkCellId(cntrwkVO));
	
		return map;
	}
		
	/**
	 * 2019신규
	 * [포장공사이력관리 > 포장공사진행현황]
	 * 보수대상선정 대비 포장공사 진행현황 view
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/cntrwk/cntrwkProgressList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwk/cntrwkProgressList.do" })
	public String selectRouteInfoList(@ModelAttribute("searchVO") RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {
		
		// 산정년도
		RpairTrgetSlctnVO rpairTrgetSlctnVO = new RpairTrgetSlctnVO();
		rpairTrgetSlctnVO.setUSE_AT("Y");
		rpairTrgetSlctnVO.setUsePage(false);
		List<RpairTrgetSlctnVO> slctnYearList = rpairTrgetSlctnService.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);
		
		//도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");
		
		//노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		model.addAttribute("slctnYearList", slctnYearList);
		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("roadNoList", roadNoList);
		
		return "/cntrwk/cntrwkProgressList" ;
	}
		
	/**
	 * 2019신규
	 * [포장공사이력관리 > 포장공사진행현황]
	 * 보수대상선정 대비 포장공사 진행현황 조회 api
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/cntrwk/cntrwkProgressList"
	 * @exception Exception
	 */
	//api/cntrwk/selectRpairTrgetGroupList.do
    @RequestMapping(value = {  "/api/cntrwk/selectCntrwkProgressList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public  @ResponseBody Map<String, Object>  selectRpairTrgetGroupListPageRest(@RequestBody  RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
        int pageIndex = 1;
        int pageSize = egovPropertyService.getInt("pageSize");
        if (rpairTrgetGroupVO.getPageIndex() > 0) {
            pageIndex = rpairTrgetGroupVO.getPageIndex();
        } else {
            rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
        }

        if (rpairTrgetGroupVO.getPageSize() > 0) {
            pageSize = rpairTrgetGroupVO.getPageSize();
        } else {
            rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));
        }

        if (rpairTrgetGroupVO.getPageUnit() <= 0) {
            rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
        }

        int firstIndex = pageSize * pageIndex - pageSize;
        int lastIndex = firstIndex + pageSize;
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageSize);
        paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

        rpairTrgetGroupVO.setFirstIndex(firstIndex);
        rpairTrgetGroupVO.setLastIndex(lastIndex);
        rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        rpairTrgetGroupVO.setUsePage(true);

        rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
        rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 보수대상선정 그룹 목록
        List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectCntrwkByRpairTrgetGroupList(rpairTrgetGroupVO);
        // 보수대상선정 그룹 갯수
        int total_count = rpairTrgetGroupService.selectCntrwkByRpairTrgetGroupListTotalCount(rpairTrgetGroupVO);
        // 보수대상선정 그룹 페이지 갯수
        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) pageSize);

        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", pageIndex);
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);

        return map;
    }
		
}
