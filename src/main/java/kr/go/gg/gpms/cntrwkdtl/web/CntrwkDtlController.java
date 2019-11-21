
package kr.go.gg.gpms.cntrwkdtl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.cmmn.util.DateUtil;
import egovframework.cmmn.util.ExcelView;
import egovframework.cmmn.util.FileUploadUtils;
import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;
import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrcomp.service.model.CntrCompVO;
import kr.go.gg.gpms.cntrwk.service.CntrwkService;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.cntrwkcellinfo.service.CntrwkCellInfoService;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;
import kr.go.gg.gpms.cntrwkdtl.service.CntrwkDtlService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.flawcntrwk.service.FlawCntrwkService;
import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;
import kr.go.gg.gpms.pavmatrl.service.PavMatrlService;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

/**
 * @Class Name : CntrwkDtlController.java
 * @Description : CntrwkDtl Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *
 * 		Copyright (C) All right reserved.
 */

@Controller("cntrwkDtlController")
public class CntrwkDtlController extends BaseController {

	@Resource(name = "cntrwkDtlService")
	private CntrwkDtlService cntrwkDtlService;
	@Resource(name = "cntrwkService")
	private CntrwkService cntrwkService;
	@Resource(name = "flawCntrwkService")
	private FlawCntrwkService flawCntrwkService;
	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	@Resource(name = "pathInfoProperties")
	protected Properties pathInfoProperties;
	@Resource(name = "attachFileService")
	private AttachFileService attachFileService;
	@Resource(name = "deptService")
	protected DeptService deptService;
	@Resource(name = "cntrwkCellInfoService")
	private CntrwkCellInfoService cntrwkCellInfoService;
	@Autowired
	SessionManager sessionManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(CntrwkDtlController.class);

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다. (pageing)
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDtlList.do" })
	public String selectCntrwkDtlList(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO, CntrwkVO cntrwkVO,  ModelMap model, HttpServletRequest request)
			throws Exception {
		
		model.addAttribute("CNTRWK_SE",cntrwkVO.getCNTRWK_SE());
		return "/cntrwkdtl/cntrwkdtlList";
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다. (pageing)
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkDtlList.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CntrwkDtlVO> selectCntrwkDtlListRest(@RequestBody CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			HttpSession session) throws Exception {
		cntrwkDtlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkDtlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkDtlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkDtlVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkDtlVO.getPageSize());

		cntrwkDtlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkDtlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkDtlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkDtlVO> items = cntrwkDtlService.selectCntrwkDtlList(cntrwkDtlVO);
		return items;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다. (pageing)
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDtlListPage.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCntrwkDtlListPage(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			ModelMap model, HttpServletRequest request) throws Exception {
		cntrwkDtlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkDtlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkDtlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkDtlVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkDtlVO.getPageSize());

		cntrwkDtlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkDtlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkDtlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// cntrwkDtlVO.setCNTRWK_ID(request.getParameter("CNTRWK_ID"));
		List<CntrwkDtlVO> items = cntrwkDtlService.selectCntrwkDtlList(cntrwkDtlVO);
		int total_count = cntrwkDtlService.selectCntrwkDtlListTotalCount(cntrwkDtlVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cntrwkDtlVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cntrwkDtlVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다. (pageing)
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkDtlListPage.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CntrwkDtlVO> selectCntrwkDtlListPageRest(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			ModelMap model, HttpSession session) throws Exception {
		cntrwkDtlVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		cntrwkDtlVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkDtlVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cntrwkDtlVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkDtlVO.getPageSize());

		cntrwkDtlVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkDtlVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkDtlVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkDtlVO> items = cntrwkDtlService.selectCntrwkDtlList(cntrwkDtlVO);
		return items;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 상세를 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkDtl.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkDtlVO selectCntrwkDtlRest(@RequestBody CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			HttpSession session) throws Exception {

		CntrwkDtlVO cntrwkDtlVOOne = cntrwkDtlService.selectCntrwkDtl(cntrwkDtlVO);

		return cntrwkDtlVOOne;
	}

	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;
	@Resource(name = "pavMatrlService")
	private PavMatrlService pavMatrlService;

	@RequestMapping(value = { "/cntrwkdtl/addCntrwkDtlView.do" })
	public String addCntrwkDtlView(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO, CntrwkVO cntrwkVO, ModelMap model)
			throws Exception {
		model.addAttribute("cntrwkDtlVO", new CntrwkDtlVO());
		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));

		Integer cntrwk_start_year = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
		addCodeToModel("HTSE", model);
		// 공사 구분
		addCodeToModel("CWSE", model);
		// 날씨 구분
		addCodeToModel("WETH", model);

		PavMatrlVO pavMatrlVOParam = new PavMatrlVO();
		pavMatrlVOParam.setUsePage(false);
		List<PavMatrlVO> pavMatrlVOItems = pavMatrlService.selectPavMatrlList(pavMatrlVOParam);
		model.addAttribute("PavMatrls", pavMatrlVOItems);
		RpairMthdVO rpairMthdVOParam = new RpairMthdVO();
		rpairMthdVOParam.setUsePage(false);
		List<RpairMthdVO> rpairMthdItems = rpairMthdService.selectRpairMthdList(rpairMthdVOParam);
		model.addAttribute("RpairMthds", rpairMthdItems);

		// 공사 업체
		model.addAttribute("companyList", getCompanyList());

		return "/cntrwkdtl/cntrwkdtlRegist";
	}

	@RequestMapping(value = { "/cntrwkdtl/updateCntrwkDtlView.do" })
	public String updateCntrwkDtlView(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			BindingResult bindingResult) throws Exception {
		model.addAttribute("cntrwkDtlVO", cntrwkDtlService.selectCntrwkDtl(cntrwkDtlVO));
		CntrwkVO cntrwkVO = new CntrwkVO();
		cntrwkVO.setCNTRWK_ID(cntrwkDtlVO.getCNTRWK_ID());
		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));

		CntrwkCellInfoVO cntrwkCellInfoVO = new CntrwkCellInfoVO();
		cntrwkCellInfoVO.setDETAIL_CNTRWK_ID(cntrwkDtlVO.getDETAIL_CNTRWK_ID());
		model.addAttribute("cntrwkCellInfoList", cntrwkCellInfoService.selectCntrwkCellInfoList(cntrwkCellInfoVO));

		Integer cntrwk_start_year = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
		addCodeToModel("HTSE", model);
		// 공사 구분
		addCodeToModel("CWSE", model);
		// 날씨 구분
		addCodeToModel("WETH", model);

		PavMatrlVO pavMatrlVOParam = new PavMatrlVO();
		pavMatrlVOParam.setUsePage(false);
		List<PavMatrlVO> pavMatrlVOItems = pavMatrlService.selectPavMatrlList(pavMatrlVOParam);
		model.addAttribute("PavMatrls", pavMatrlVOItems);
		RpairMthdVO rpairMthdVOParam = new RpairMthdVO();
		rpairMthdVOParam.setUsePage(false);
		List<RpairMthdVO> rpairMthdItems = rpairMthdService.selectRpairMthdList(rpairMthdVOParam);
		model.addAttribute("RpairMthds", rpairMthdItems);

		// 공사 업체
		model.addAttribute("companyList", getCompanyList());

		return "/cntrwkdtl/cntrwkdtlUpdate";
	}

	@RequestMapping(value = { "/cntrwkdtl/addCntrwkDtl.do" })
	public String addCntrwkDtl(@ModelAttribute CntrwkDtlVO cntrwkDtlVO,CntrwkVO cntrwkVO, CntrwkCellInfoVO cntrwkCellInfoVO,
			BindingResult bindingResult, Model model, HttpServletRequest request, SessionStatus status,
			HttpSession session) throws Exception {
		Map<String, String> req = requestToHashMap(request);
		String userNo = sessionManager.getUserNo();
		String action_flag = StringUtils.isNotEmpty(req.get("action_flag")) ? req.get("action_flag").trim() : "INSERT";
		// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";

		try {

			cntrwkDtlVO.setOPERT_BFE_PHOTO_NO(getFileNo(request, "file_before", "cntrwkDtl"));
			cntrwkDtlVO.setOPERT_AFT_PHOTO_NO(getFileNo(request, "file_after", "cntrwkDtl"));
			cntrwkDtlVO.setFILE_NO(getFileNo(request, "file_no", "cntrwkDtl"));

			resultCode = "SAVE_SUCCESS";
			BindBeansToActiveUser(cntrwkDtlVO);

			String cntrwkDtlId = cntrwkDtlService.insertCntrwkDtl(cntrwkDtlVO);
			System.out.println("CNTRWK_SE : " + cntrwkVO.getCNTRWK_SE());
			if(cntrwkVO.getCNTRWK_SE().equals("CWSE0008") || cntrwkVO.getCNTRWK_SE().equals("CWSE0009")) {
				HashMap<String, String> resultMap = new HashMap<>();
				resultMap.put("ROUTE_CODE", cntrwkDtlVO.getROUTE_CODE());
				resultMap.put("USER_NO", userNo);
				resultMap.put("p_MODE", "NONE");
				
				HashMap result = cntrwkService.prc_SaveData(resultMap);
			}
			// 위치 정보 등록
			String cellIdList[] = cntrwkCellInfoVO.getPAV_CELL_ID().split(",");
			String pavYear = cntrwkDtlVO.getRPAIR_END_DE() == null ? ""
					: cntrwkDtlVO.getRPAIR_END_DE().toString().substring(0, 4);

			cntrwkCellInfoVO.setDETAIL_CNTRWK_ID(cntrwkDtlId);
			cntrwkCellInfoVO.setPAV_YEAR(pavYear);

			for (String cellId : cellIdList) {
				cntrwkCellInfoVO.setPAV_CELL_ID(cellId);
				cntrwkCellInfoService.insertCntrwkCellInfo(cntrwkCellInfoVO);
			}

			model.addAttribute("insertKey", cntrwkDtlVO.getDETAIL_CNTRWK_ID());
			cntrwkDtlVO.setResultSuccess("true");
			cntrwkDtlVO.setResultMSG("정상 등록되었습니다.");
			// 결과 처리용 [수정X]
			model.addAttribute("resultCode", resultCode);
			model.addAttribute("resultMsg", resultMsg);
			model.addAttribute("callBackFunction",
					StringUtils.isNotEmpty(cntrwkDtlVO.getCallBackFunction()) ? cntrwkDtlVO.getCallBackFunction().trim()
							: ""); // 처리후 호출 함수

			status.setComplete(); // Double Submit 방지
		} catch (Exception e) {
			resultCode = "ERROR";
			resultMsg = "등록 오류 발생";
			LOGGER.error("공사등록 오류", e);
		}

		return "/cmmn/commonMsg";

	}

	@RequestMapping(value = { "/api/cntrwkdtl/addCntrwkDtl.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkDtlVO addCntrwkDtlRest(@RequestBody CntrwkDtlVO cntrwkDtlVO, HttpSession session)
			throws Exception {
		BindBeansToActiveUser(cntrwkDtlVO);
		cntrwkDtlService.insertCntrwkDtl(cntrwkDtlVO);
		cntrwkDtlVO.setResultSuccess("true");
		cntrwkDtlVO.setResultMSG("정상 등록되었습니다.");
		return cntrwkDtlVO;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 상세를 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDtl.do" })
	public String selectCntrwkDtl(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO,CntrwkVO cntrwkVO, AttachFileVO attachFileVO,
			ModelMap model, BindingResult bindingResult) throws Exception {
		model.addAttribute("cntrwkDtlVO", cntrwkDtlService.selectCntrwkDtl(cntrwkDtlVO));
		model.addAttribute("CNTRWK_SE",cntrwkVO.getCNTRWK_SE());
		return "/cntrwkdtl/cntrwkdtlView";
	}

	@RequestMapping(value = { "/cntrwkdtl/updateCntrwkDtl.do" })
	public String updateCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		BindBeansToActiveUser(cntrwkDtlVO);
		cntrwkDtlService.updateCntrwkDtl(cntrwkDtlVO);
		cntrwkDtlVO.setResultSuccess("true");
		cntrwkDtlVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/cntrwkdtl/selectCntrwkDtlList.do";
	}

	@RequestMapping(value = { "/api/cntrwkdtl/updateCntrwkDtl.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String updateCntrwkDtlRest(CntrwkDtlVO cntrwkDtlVO, CntrwkCellInfoVO cntrwkCellInfoVO,
			BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		String resultCode = "";
		String resultMsg = "";
		String funCallback = cntrwkDtlVO.getCallBackFunction() == null ? "" : cntrwkDtlVO.getCallBackFunction();

		String fileNo_before = getFileNo(request, "file_before", "cntrwkDtl");
		if (fileNo_before != null) {
			cntrwkDtlVO.setOPERT_BFE_PHOTO_NO(fileNo_before);
		}
		String fileNo_after = getFileNo(request, "file_after", "cntrwkDtl");
		if (fileNo_after != null) {
			cntrwkDtlVO.setOPERT_AFT_PHOTO_NO(fileNo_after);
		}
		String fileNo_no = getFileNo(request, "file_no", "cntrwkDtl");
		if (fileNo_no != null) {
			cntrwkDtlVO.setFILE_NO(fileNo_no);
		}

		BindBeansToActiveUser(cntrwkDtlVO);
		cntrwkDtlService.updateCntrwkDtl(cntrwkDtlVO);

		// 위치 정보 등록
		if (cntrwkCellInfoVO.getPAV_CELL_ID() != null && !cntrwkCellInfoVO.getPAV_CELL_ID().equals("")) {
			String cellIdList[] = cntrwkCellInfoVO.getPAV_CELL_ID().split(",");
			String pavYear = cntrwkDtlVO.getRPAIR_END_DE() == null ? ""
					: cntrwkDtlVO.getRPAIR_END_DE().toString().substring(0, 4);

			cntrwkCellInfoVO.setPAV_CELL_ID("");
			cntrwkCellInfoVO.setDETAIL_CNTRWK_ID(cntrwkDtlVO.getDETAIL_CNTRWK_ID());
			cntrwkCellInfoService.deleteCntrwkCellInfo(cntrwkCellInfoVO);

			cntrwkCellInfoVO.setPAV_YEAR(pavYear);

			for (String cellId : cellIdList) {
				cntrwkCellInfoVO.setPAV_CELL_ID(cellId);
				cntrwkCellInfoService.insertCntrwkCellInfo(cntrwkCellInfoVO);
			}
		}

		resultCode = "UPDATE_SUCCESS";
		resultMsg = "정상 수정되었습니다.";

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수

		return "/cmmn/commonMsg";
	}

	@RequestMapping(value = { "/cntrwkdtl/deleteCntrwkDtl.do" })
	public String deleteCntrwkDtl(CntrwkDtlVO cntrwkDtlVO, ModelMap model) throws Exception {
		BindBeansToActiveUser(cntrwkDtlVO);
		cntrwkDtlService.deleteCntrwkDtl(cntrwkDtlVO);
		model.addAttribute("resultCode", "DELETE_SUCCESS");
		model.addAttribute("resultMsg", "정상 삭제되었습니다.");
		return "/cmmn/commonMsg";
	}

	@RequestMapping(value = { "/api/cntrwkdtl/deleteCntrwkDtl.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkDtlVO deleteCntrwkDtlRest(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			FlawCntrwkVO flawCntrwkVO, CntrwkCellInfoVO cntrwkCellInfoVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cntrwkDtlVO);

		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkVO.setCNTRWK_ID(cntrwkDtlVO.getCNTRWK_ID());
		flawCntrwkVO.setDETAIL_CNTRWK_ID(cntrwkDtlVO.getDETAIL_CNTRWK_ID());
		flawCntrwkService.deleteFlawCntrwkByCntrwkDtlID(flawCntrwkVO);
		cntrwkCellInfoVO.setDETAIL_CNTRWK_ID(cntrwkDtlVO.getDETAIL_CNTRWK_ID());
		cntrwkCellInfoService.deleteCntrwkCellInfo(cntrwkCellInfoVO);

		cntrwkDtlService.deleteCntrwkDtl(cntrwkDtlVO);

		cntrwkDtlVO.setResultSuccess("true");
		cntrwkDtlVO.setResultMSG("정상 삭제되었습니다.");
		return cntrwkDtlVO;
	}

	// 포장공사 이력 엑셀 업로드 화면
	@RequestMapping(value = { "/cntrwkdtl/cntrwkDtlExcelUploadForm.do" })
	public String excelUploadForm(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			BindingResult bindingResult) throws Exception {

		model.addAttribute("cntrwkDtlVO", cntrwkDtlVO);

		return "/cntrwkdtl/cntrwkdtlExcelUpload";
	}

	// 포장공사 이력 엑셀 업로드
	@RequestMapping(value = "/cntrwkdtl/cntrwkDtlExcelUpload.do", method = RequestMethod.POST)
	public String excelUpload(@ModelAttribute("searchVO") CntrwkDtlVO cntrwkDtlVO, BindingResult bindingResult,
			HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		String resultMsg = "";
		String filePathNm = "";
		String userNo = sessionManager.getUserNo();
		
		/** validate request type */
		Assert.state(request instanceof MultipartHttpServletRequest, "request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/** extract files */
		final List<MultipartFile> files = multiRequest.getFiles("files");
		Assert.notNull(files, "files is null");
		Assert.state(files.size() > 0, "0 files exist");

		String filePath = pathInfoProperties.getProperty("file.upload.path");
		List<AttachFileVO> fileList = FileUploadUtils.saveFileList(filePath, "cntrwkDtl", files);

		try {
			
			for (AttachFileVO file : fileList) {

				Date currentDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String date = sdf.format(currentDate);

				filePathNm = checkFilePath(filePath, "path") + File.separator + "cntrwkDtl" + File.separator + date
						+ File.separator + checkFilePath(file.getFILE_NM(), "name");

				// db insert
				resultMsg = cntrwkDtlService.excelDBUpload(cntrwkDtlVO, filePathNm, userNo);
			}
		
		} catch (Exception e) {
			resultMsg = e.getMessage() + " 등록에 실패하였습니다.";
			model.addAttribute("resultMsg", resultMsg);

			return "jsonView";
		}
		
		if(resultMsg.equals("Success")) {
			resultMsg = "정상적으로 등록되었습니다.";
			model.addAttribute("resultMsg", resultMsg);
		}else {
			resultMsg += "등록에 실패하였습니다.";
			model.addAttribute("resultMsg", resultMsg);
		}
		return "jsonView";
	}

	@RequestMapping(value = "/cntrwkdtl/downloadexcel.do")
	public View cntrwkDtlListExcel(@ModelAttribute CntrwkDtlVO cntrwkDtlVO, ModelMap model, HttpServletRequest request,
			HttpSession session) throws Exception {
		List dataList = cntrwkDtlService.selectCntrwkDtlListExcel(cntrwkDtlVO);

		String[] excel_title = { "세부위치", "노선번호", "노선명", "행선", "차로", "시점(m)", "종점(m)", "작업시작일", "작업완료일", "공사비(천원)",
				"연장(m)", "보수폭(m)", "보수면적(㎡)", "보수표층두께(cm)", "보수중간층두께(cm)", "보수기층두께(cm)" };
		String[] excel_column = { "detail_cntrwk_nm", "route_code", "route_nm", "direct_nm", "track", "strtpt", "endpt",
				"rpair_begin_de", "rpair_end_de", "cntrwk_amount", "track_len", "rpair_bt", "rpair_ar",
				"rpair_thick_ascon", "rpair_thick_cntr", "rpair_thick_base" };

		// model.addAttribute("file_name", cntrwkVO.getEXCEL_FILE_NM() + "_" +
		// DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("file_name", "세부공사목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 건수 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkRoutCntStats.do" })
	public String selectCntrwkRoutCntStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkRoutCntStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 연장 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkRoutLenStats.do" })
	public String selectCntrwkRoutLenStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkRoutLenStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 예산 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkRoutAmountStats.do" })
	public String selectCntrwkRoutAmountStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkRoutAmountStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkStatsResult.do" })
	public @ResponseBody Map<String, Object> selectCntrwkStatsResult(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cntrwkDtlService.selectCntrwkStatsResult(cntrwkDtlVO));

		return map;
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 건수 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDeptCntStats.do" })
	public String selectCntrwkDeptCntStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkDeptCntStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 연장 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDeptLenStats.do" })
	public String selectCntrwkDeptLenStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkDeptLenStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 예산 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkDeptAmountStats.do" })
	public String selectCntrwkDeptAmountStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkDeptAmountStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkDeptStatsResult.do" })
	public @ResponseBody Map<String, Object> selectCntrwkDeptStatsResult(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cntrwkDtlService.selectCntrwkDeptStatsResult(cntrwkDtlVO));

		return map;
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 건수 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkMthdCntStats.do" })
	public String selectCntrwkMthdCntStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkMthdCntStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 연장 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkMthdLenStats.do" })
	public String selectCntrwkMthdLenStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkMthdLenStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 예산 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/stats/cntrwk/cntrwkRoutCntStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkdtl/selectCntrwkMthdAmountStats.do" })
	public String selectCntrwkMthdAmountStats(CntrwkDtlVO cntrwkDtlVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request) throws Exception {
		// 부서 정보 > 맵 컨트롤러 부터 조회 되도록 수정
		// model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));

		return "/stats/cntrwk/cntrwkMthdAmountStats";
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkdtl/selectCntrwkMthdStatsResult.do" })
	public @ResponseBody Map<String, Object> selectCntrwkMthdStatsResult(@RequestBody CntrwkDtlVO cntrwkDtlVO,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cntrwkDtlService.selectCntrwkMthdStatsResult(cntrwkDtlVO));

		return map;
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = "/cntrwkdtl/cntrwkRoutCntStatsExcel.do")
	public View cntrwkRoutCntStatsExcel(@ModelAttribute CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		List dataList = cntrwkDtlService.cntrwkRoutCntStatsExcel(cntrwkDtlVO);

		String[] excel_title = { "노선번호", "노선명", "공사건수(건)", "연장(m)", "공사비(천원)", "공사빈도(%)" };
		String[] excel_column = { "route_code", "route_nm", "cnt", "track_len", "cntrwk_amount", "perc" };

		// model.addAttribute("file_name", cntrwkVO.getEXCEL_FILE_NM() + "_" +
		// DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("file_name", "노선별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = "/cntrwkdtl/cntrwkDeptCntStatsExcel.do")
	public View cntrwkDeptCntStatsExcel(@ModelAttribute CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		List dataList = cntrwkDtlService.cntrwkDeptCntStatsExcel(cntrwkDtlVO);

		String[] excel_title = { "관리기관명", "공사건수(건)", "연장(m)", "공사비(천원)", "공사빈도(%)" };
		String[] excel_column = { "dept_nm", "cnt", "track_len", "cntrwk_amount", "perc" };

		// model.addAttribute("file_name", cntrwkVO.getEXCEL_FILE_NM() + "_" +
		// DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("file_name", "관리기관별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 엑셀목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	@RequestMapping(value = "/cntrwkdtl/cntrwkMthdCntStatsExcel.do")
	public View cntrwkMthdCntStatsExcel(@ModelAttribute CntrwkDtlVO cntrwkDtlVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		List dataList = cntrwkDtlService.cntrwkMthdCntStatsExcel(cntrwkDtlVO);

		String[] excel_title = { "공법명", "공사건수(건)", "연장(m)", "공사비(천원)", "공사빈도(%)" };
		String[] excel_column = { "rpair_mthd_nm", "cnt", "track_len", "cntrwk_amount", "perc" };

		// model.addAttribute("file_name", cntrwkVO.getEXCEL_FILE_NM() + "_" +
		// DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("file_name", "포장공법별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

}
