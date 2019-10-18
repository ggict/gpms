package kr.go.gg.gpms.srvy.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import egovframework.cmmn.util.ExcelView;
import egovframework.cmmn.util.FileUploadUtils;
import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.security.service.impl.CustomAuthenticationProvider;
import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.mummsctnsrvydta.service.MummSctnSrvyDtaService;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.pavfrmula.service.PavFrmulaService;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.smdtagnlsttus.service.SmDtaGnlSttusService;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;
import kr.go.gg.gpms.smdtalaststtus.service.SmDtaLastSttusService;
import kr.go.gg.gpms.srvydtaexcel.service.SrvyDtaExcelService;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;
import kr.go.gg.gpms.srvydtalog.service.SrvyDtaLogService;
import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogVO;
import kr.go.gg.gpms.srvydtasttus.service.SrvyDtaSttusService;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStream;

//import org.springframework.security.core.context.SecurityContextHolder;

@RequestMapping(value = "/srvy/")
@Controller("srvyDtaController")
public class SrvyDtaController extends BaseController {

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "pathInfoProperties")
	protected Properties pathInfoProperties;

	@Resource(name = "customAuthenticationProvider")
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Resource(name = "srvyDtaExcelService")
	private SrvyDtaExcelService srvyDtaExcelService;

	@Resource(name = "srvyDtaLogService")
	private SrvyDtaLogService srvyDtaLogService;
	@Resource(name = "srvyDtaSttusService")
	private SrvyDtaSttusService srvyDtaSttusService;
	@Resource(name = "mummSctnSrvyDtaService")
	private MummSctnSrvyDtaService mummSctnSrvyDtaService;

	@Resource(name = "smDtaGnlSttusService")
	private SmDtaGnlSttusService smDtaGnlSttusService;

	@Resource(name = "smDtaLastSttusService")
	private SmDtaLastSttusService smDtaLastSttusService;

	@Resource(name = "pavFrmulaService")
	private PavFrmulaService pavFrmulaService;
	@Resource(name = "attachFileService")
	private AttachFileService attachFileService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Autowired
	SessionManager sessionManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyDtaController.class);

	@RequestMapping(value = "/srvyDtaList.do")
	public String srvyDtaList(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		return "/srvy/srvyDtaList";
	}

	/**
	 * 엑셀 조사자료 파일을 서버로 전송한다
	 *
	 * @param srvyDtaExcelVO
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/srvyDtaFileUpload.do")
	public String fileUpload(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, SrvyDtaLogVO srvyDtaLogVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		String resultCode = "";
		String resultMsg = "";

		int successCnt = 0;
		int failCnt = 0;

		String userNo = sessionManager.getUserNo();
		String funCallback = srvyDtaExcelVO.getCallBackFunction() == null ? "" : srvyDtaExcelVO.getCallBackFunction();

		/** validate request type */
		Assert.state(request instanceof MultipartHttpServletRequest, "request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/** extract files */
		final List<MultipartFile> files = multiRequest.getFiles("files");
		Assert.notNull(files, "files is null");
		Assert.state(files.size() > 0, "0 files exist");

		String filePath = pathInfoProperties.getProperty("file.upload.path");

		List<AttachFileVO> fileList = FileUploadUtils.saveFileList(filePath, "srvy", files);

		if (fileList.size() < 1 || userNo == null || userNo.equals("")) {
			resultCode = "ERROR";
			resultMsg = "등록오류발생";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss", Locale.KOREA);

			for (AttachFileVO file : fileList) {
				String logCode = "PCST0001"; // 진행
				Date currentTime = new Date();
				srvyDtaLogVO.setBEGIN_DT(new java.sql.Date(currentTime.getTime()));

				file.setUSE_AT("Y");
				file.setDELETE_AT("N");
				file.setCRTR_NO(userNo);
				file.setUPDUSR_NO(userNo);

				// 코드 값 불러오는 쿼리 필요

				// 파일 정보 DB 저장
				String fileNo = attachFileService.insertAttachFile(file);

				String sheetName = "DBLoading";
				String fileName = filePath + file.getFILE_COURS() + File.separator + file.getFILE_NM();
				//String fileName = file.getFILE_NM();
				//String fileCours = filePath + file.getFILE_COURS();
						
				int resultZipCnt = getReadZipDataCnt(fileName, filePath);
				
				//System.out.println("resultZipCnt: " + resultZipCnt);
				
				//zip 파일 전송 완료 추후 로직 수정
				String tmp1 = "end";
				if("end".equals(tmp1)) {
					return "jsonView";  
				}

				// 전송한 엑셀 파일의 개수
				int resultCnt = getReadXLDataCnt(fileName, sheetName);

				if (resultCnt < 0) {
					resultMsg = sheetName + " 시트가 없습니다.";
					failCnt++;
					logCode = "PCST0003";
				} else if (resultCnt == 0) {
					resultMsg = "데이터가 없습니다.";
					failCnt++;
					logCode = "PCST0003";
				} else {
					resultMsg = "파일전송이 성공하였습니다.";
					successCnt++;
					logCode = "PCST0002";
				}

				// 조사자료 엑셀 파일 저장
				srvyDtaExcelVO.setFILE_NO(fileNo);
				srvyDtaExcelVO.setDATA_CO(Integer.toString(resultCnt));
				srvyDtaExcelVO.setEVL_PROCESS_AT("N");
				srvyDtaExcelVO.setGPS_CORTN_PROCESS_AT("N");
				srvyDtaExcelVO.setPRDTMDL_PROCESS_AT("N");
				srvyDtaExcelVO.setSM_PROCESS_AT("N");
				srvyDtaExcelVO.setCRTR_NO(userNo);
				srvyDtaExcelVO.setUPDUSR_NO(userNo);
				srvyDtaExcelVO.setVAL_EVL_AT("N");
				srvyDtaExcelVO.setUSE_AT("Y");
				srvyDtaExcelVO.setDELETE_AT("N");

				String srvyNo = srvyDtaExcelService.insertSrvyDtaExcel(srvyDtaExcelVO);

				// 조사자료 엑셀 파일 업로드 로그 저장
				srvyDtaLogVO.setSRVY_NO(srvyNo);
				srvyDtaLogVO.setLOG_MSSAGE(resultMsg);
				srvyDtaLogVO.setPROCESS_SE("PCSE0001");
				srvyDtaLogVO.setPROCESS_STTUS(logCode);
				srvyDtaLogVO.setEND_DT(new java.sql.Date(currentTime.getTime()));
				srvyDtaLogVO.setCRTR_NO(userNo);

				srvyDtaLogService.insertSrvyDtaLog(srvyDtaLogVO);

				if (logCode.equals("PCST0003")) {
					continue;
				}

				String upLogCode = "PCST0001";
				resultMsg = "자료조사 validation check를 진행중 입니다.";

				// validation check
				Map<String, Object> validChkInfo = validReadXLData(fileName, sheetName);
				boolean validChk = (Boolean) validChkInfo.get("result");

				if (validChk) {
					upLogCode = "PCST0002";
					resultMsg = validChkInfo.get("resultMsg").toString();

					srvyDtaExcelVO.setSRVY_DE(validChkInfo.get("srvyDe").toString());
					srvyDtaExcelVO.setVAL_EVL_AT("Y");
					srvyDtaExcelVO.setSRVY_NO(srvyNo);

					srvyDtaExcelService.updateSrvyDtaExcel(srvyDtaExcelVO);

				} else {
					upLogCode = "PCST0003";
					if (validChkInfo.get("errorCol").toString().equals("")) {
						resultMsg = validChkInfo.get("resultMsg").toString();
					} else {
						String colName = validChkInfo.get("errorCol").toString();
						String rowIndex = validChkInfo.get("rowIndex").toString();

						resultMsg = colName + "컬럼의 " + rowIndex + "줄에 validation 오류 발생 : " + validChkInfo.get("resultMsg").toString();
					}

					/*
					 * srvyDtaExcelVO.setSRVY_DE(validChkInfo.get("srvyDe").toString
					 * ());
					 */
					srvyDtaExcelVO.setVAL_EVL_AT("N");
					srvyDtaExcelVO.setSRVY_NO(srvyNo);

					srvyDtaExcelService.updateSrvyDtaExcel(srvyDtaExcelVO);

				}

				// 초기화
				srvyDtaExcelVO.setSRVY_NO(null);

				srvyDtaLogVO.setPROCESS_SE("PCSE0002");
				srvyDtaLogVO.setPROCESS_STTUS(upLogCode);
				srvyDtaLogVO.setLOG_MSSAGE(resultMsg);

				srvyDtaLogService.insertSrvyDtaLog(srvyDtaLogVO);
			}

			resultCode = "MSG";
			resultMsg = "완료 되었습니다. [전송결과]를 확인해주세요.";

		}
		model.addAttribute("failCnt", failCnt);
		model.addAttribute("successCnt", successCnt);
		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수

		return "jsonView";
		// return "/cmmn/commonMsg";
	}

	@RequestMapping(value = "/srvyDtaUploadResultList.do")
	public String srvyDtaUploadResultList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		return "/srvy/srvyDtaUploadResultList";
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL) 업로드 결과 목록을 조회한다. (paging)
	 *
	 * @param SrvyDtaExcelVO
	 *            - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/srvyDtaUploadResultList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaUploadResultList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaUploadResultListRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getRows());
		srvyDtaExcelVO.setUsePage(true);

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaExcelUploadResultList(srvyDtaExcelVO);
		int totCnt = srvyDtaExcelService.selectSrvyDtaExcelUploadResultCount(srvyDtaExcelVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaExcelVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL) 업로드 결과 상세 목록을 조회한다. (paging)
	 *
	 * @param SrvyDtaExcelVO
	 *            - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/srvyDtaUploadResultList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaUploadFileList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaUploadFileListRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		if (srvyDtaExcelVO.getPROCESS_STTUS().equals("COMP")) {
			srvyDtaExcelVO.setPROCESS_STTUS("PCST0002");
		} else {
			srvyDtaExcelVO.setPROCESS_STTUS("PCST0003");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getRows());
		srvyDtaExcelVO.setUsePage(true);

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaExcelUploadFileList(srvyDtaExcelVO);
		int totCnt = srvyDtaExcelService.selectSrvyDtaExcelUploadFileCount(srvyDtaExcelVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaExcelVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL) 등록대상 목록을 조회한다. (paging)
	 *
	 * @param SrvyDtaExcelVO
	 *            - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/srvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaRegList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaRegListRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getRows());
		srvyDtaExcelVO.setUsePage(true);

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaCompList(srvyDtaExcelVO);
		int totCnt = srvyDtaExcelService.selectSrvyDtaCompCount(srvyDtaExcelVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaExcelVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL) 등록대상을 삭제한다.
	 *
	 * @param SrvyDtaExcelVO
	 *            - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/srvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvyDtaExcelDelete.do" })
	public String srvyDtaExcelDelete(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		if (srvyDtaExcelVO.getSRVY_NO() == null) {
			return "jsonView";
		}

		String[] srvyNoList = srvyDtaExcelVO.getSRVY_NO().split(",");
		int totCnt = 0;

		for (String srvyNo : srvyNoList) {
			srvyDtaExcelVO.setSRVY_NO(srvyNo);
			srvyDtaExcelVO.setUSE_AT("N");
			srvyDtaExcelVO.setDELETE_AT("Y");

			srvyDtaExcelService.updateSrvyDtaExcel(srvyDtaExcelVO);
			totCnt++;
		}

		model.addAttribute("delCnt", totCnt);

		return "jsonView";
	}

	/**
	 * 엑셀 조사자료를 최소구간조사 자료에 입력한다.
	 *
	 * @param srvyDtaExcelVO
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveDtaList.do")
	public @ResponseBody Map<String, Object> saveDtaList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, PavFrmulaVO pavFrmulaVO, ModelMap model, HttpServletRequest request, HttpSession session) {

		String o_PROCCODE = "";
		String o_PROCMSG = "";

		Map<String, Object> map = new HashMap<String, Object>();
		srvyDtaExcelVO.setUsePage(false);
		srvyDtaExcelVO.setUSE_AT("Y");
		srvyDtaExcelVO.setDELETE_AT("N");

		int totCount = 0;
		int successCount = 0;
		List<String> noFileList = new ArrayList<String>();

		try {
			// 선택 엑셀 데이터 조회
			List<SrvyDtaExcelVO> excelList = srvyDtaExcelService.selectSrvyDtaExcelList(srvyDtaExcelVO);
			// 엑셀 데이터가 없으면 종료
			if (excelList == null || excelList.size() == 0) {
				map.put("result", "nodata");
				map.put("resultMSG", "엑셀 데이터가 없습니다.");
				return map;
			}
			String filePath = pathInfoProperties.getProperty("file.upload.path");
			String frmula_nm = egovPropertyService.getString("FRMULA_NM", "NHPCI");
			
			if (StringUtils.isEmpty(pavFrmulaVO.getFRMULA_NM())) {
				pavFrmulaVO.setFRMULA_NM(frmula_nm);
			}
			pavFrmulaVO.setUSE_AT("Y");
			pavFrmulaVO.setDELETE_AT("N");
			// PavFrmulaVO pavFrmulaOne =
			String sheetName = "DBLoading";

			PavFrmulaVO pavFrmulaOne = pavFrmulaService.selectPavFrmula(pavFrmulaVO);
			
			// 산출식이 없으면 종료
			if (pavFrmulaOne == null || StringUtils.isEmpty(pavFrmulaOne.getFRMULA_NM())) {
				map.put("result", "noformula");
				map.put("resultMSG", "산출식 데이터가 없습니다.");
				return map;
			}

			totCount = excelList.size();
			successCount = 0;

			// 데이터(list) 수 만큼 for문 실행
			String fileName = "";
			for (SrvyDtaExcelVO srvyDtaExcelOne : excelList) {
				try {
					BindBeansToActiveUser(srvyDtaExcelOne);

					srvyDtaExcelOne.setFRMULA_NO(pavFrmulaOne.getFRMULA_NO());
					srvyDtaExcelOne.setFRMULA_NM(pavFrmulaOne.getFRMULA_NM());

					// 조사자료 등록 및 수식 평가
					if (srvyDtaExcelOne.getEVL_PROCESS_AT().equals("N")) {

						// i번째 파일명 가져오기
						AttachFileVO attachFileParam = new AttachFileVO();
						attachFileParam.setFILE_NO(srvyDtaExcelOne.getFILE_NO());
						attachFileParam.setUSE_AT("Y");
						attachFileParam.setDELETE_AT("N");
						AttachFileVO attachFileOne = attachFileService.selectAttachFile(attachFileParam);
						if (attachFileOne == null || StringUtils.isEmpty(attachFileOne.getFILE_COURS())) {
							/*
							 * map.put("result", "nofile"); map.put("resultMSG",
							 * "엑셀 파일이 없습니다.");
							 */
							noFileList.add(srvyDtaExcelOne.getFILE_NM());
							continue;
							// return map;
						}

						fileName = checkFilePath(filePath + attachFileOne.getFILE_COURS(), "path") + File.separator + checkFilePath(attachFileOne.getFILE_NM(), "name");
						fileName = fileName.replace("/", File.separator).replace("\\\\", File.separator);
						
						File fileExcel = new File(fileName);
						
						if (fileExcel.exists() && fileExcel.isFile()) {
							// 엑셀 POI 읽기
							//String excelXml = getReadXLData(fileName, sheetName);
							
							//sdh
							if (fileExcel.exists() && fileExcel.isFile()) {
							
							String excelDB = getReadDbData(fileName, sheetName);
							
							return map;
							}
							
							/*
							if(StringUtils.isNotEmpty(excelDB)) {
								//srvyDtaExcelService.insertSrvyDtaDetailExcel(srvyDtaExcelOne);	
							}
							*/
							
							if (StringUtils.isNotEmpty("")) {
								//excelXml = excelXml.replaceAll("\"", "");
								// LOGGER.info(excelXml);
								//srvyDtaExcelOne.setRECORDSET(excelXml);
								HashMap prc_result = srvyDtaExcelService.procSaveSurveyData(srvyDtaExcelOne);

								o_PROCCODE = prc_result.get("o_proccode").toString();
								o_PROCMSG = prc_result.get("o_procmsg").toString();

								if (!o_PROCCODE.equals("true")) {
									continue;
								}

								srvyDtaExcelOne = srvyDtaExcelService.selectSrvyDtaExcel(srvyDtaExcelOne);
								BindBeansToActiveUser(srvyDtaExcelOne);
							} else {
								/*
								 * map.put("result", "emptyexcelfile");
								 * map.put("resultMSG", "엑셀 파일이 비어있습니다.");
								 */
								continue;
							}
						} else {
							/*
							 * map.put("result", "noexistfile");
							 * map.put("resultMSG", "엑셀 파일이 없습니다.");
							 */
							noFileList.add(srvyDtaExcelOne.getFILE_NM());
							continue;
						}
					}

					// 공간 보정
					if (srvyDtaExcelOne.getGPS_CORTN_PROCESS_AT().equals("N")) {

						HashMap prc_result = srvyDtaExcelService.procSrvyDtaSysReflct(srvyDtaExcelOne);

						o_PROCCODE = prc_result.get("o_proccode").toString();
						o_PROCMSG = prc_result.get("o_procmsg").toString();

						if (!o_PROCCODE.equals("true")) {
							continue;
						}

						srvyDtaExcelOne = srvyDtaExcelService.selectSrvyDtaExcel(srvyDtaExcelOne);
						BindBeansToActiveUser(srvyDtaExcelOne);
					}

					// 집계 처리
					if (srvyDtaExcelOne.getSM_PROCESS_AT().equals("N")) {
						HashMap prc_result = srvyDtaExcelService.procAggregateGeneral(srvyDtaExcelOne);
						o_PROCCODE = (String) prc_result.get("o_proccode");
						o_PROCMSG = (String) prc_result.get("o_procmsg");

						if (!o_PROCCODE.equals("true")) {
							continue;
						}

					}

					if ("true".equals(o_PROCCODE)) {
						successCount++;
					}

				} catch (Exception e) {
				}
			}
		} catch (Exception e) {

		} finally {
			map.put("totCount", totCount);
			map.put("successCount", successCount);
			map.put("noFileList", noFileList);
			map.put("result", o_PROCCODE);
			map.put("resultMSG", o_PROCMSG);
		}

		return map;
	}

	/**
	 * 최소구간 자료를 가지고 공간데이터를 통한 검증
	 *
	 * @param srvyDtaExcelVO
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/reflectSrvyDta.do") public @ResponseBody
	 * Map<String, Object> saveDtaList(
	 *
	 * @ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model,
	 * HttpServletRequest request, HttpSession session) throws Exception {
	 * Map<String, Object> map = new HashMap<String, Object>();
	 *
	 * List<SrvyDtaExcelVO> srvyList =
	 * srvyDtaExcelService.selectSrvyDtaSrvyNoList(srvyDtaExcelVO);
	 *
	 * if(srvyList == null || srvyList.size() < 1) { return map; }
	 *
	 * String userNo = sessionManager.getUserNo();
	 * srvyDtaExcelVO.setCRTR_NO(userNo); List<String> result = new
	 * ArrayList<String>(); List<String> resultMSG = new ArrayList<String>();
	 *
	 * for(SrvyDtaExcelVO srvyNo : srvyList){
	 * srvyDtaExcelVO.setSRVY_NO(srvyNo.getSRVY_NO());
	 *
	 * HashMap prc_result = srvyDtaExcelService
	 * .procSrvyDtaSysReflct(srvyDtaExcelVO); result.add((String)
	 * prc_result.get("o_PROCCODE")); resultMSG.add((String)
	 * prc_result.get("o_PROCMSG"));
	 *
	 * }
	 *
	 * map.put("result", result); map.put("resultMSG", resultMSG);
	 *
	 * return map; }
	 */

	@RequestMapping(value = { "/api/getTargetSrvyDta.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaExcelVO targetSrvyDtaList(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		List<SrvyDtaExcelVO> srvyList = srvyDtaExcelService.selectSrvyDtaSrvyNoList(srvyDtaExcelVO);
		if (srvyList == null || srvyList.size() < 1) {
			return srvyDtaExcelVO;
		}

		return srvyList.get(0);
	}

	// 조사자료적용 집계/산정
	@RequestMapping(value = "/srvyDtaAnalTrgetList.do")
	public String srvyDtaAnalTrgetList(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		return "/srvy/anal/srvyDtaAnalTrgetList";
	}

	// 조사자료 분석 대상 목록 조회
	@RequestMapping(value = { "/api/srvyDtaAnalTrgetList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaAnalTrgetListRest(@RequestBody SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaSttusVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyDtaSttusVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaSttusVO.getRows());
		srvyDtaSttusVO.setUsePage(true);

		srvyDtaSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaSttusVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaSttusVO> items = srvyDtaSttusService.selectSrvyDtaAnalTrgetList(srvyDtaSttusVO);
		int totCnt = srvyDtaSttusService.selectSrvyDtaAnalTrgetListTotalCount(srvyDtaSttusVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaSttusVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	// 조사자료 분석 대상 삭제
	@RequestMapping(value = { "/api/delSrvyDtaAnalTrget.do" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delSrvyDtaAnalTrget(@ModelAttribute SrvyDtaSttusVO srvyDtaSttusVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		srvyDtaSttusVO.setUPDUSR_NO(sessionManager.getUserNo());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("res", srvyDtaSttusService.deleteSrvyDtaAnalTrget(srvyDtaSttusVO));
		map.put("srvyYear", srvyDtaSttusVO.getSRVY_YEAR());
		map.put("srvyMt", srvyDtaSttusVO.getSRVY_MT());

		return map;
	}

	/*
	 * @RequestMapping(value = "/analyzeDtaList.do") public @ResponseBody
	 * Map<String, Object> analyzeDtaList(
	 *
	 * @ModelAttribute SrvyDtaSttusVO searchVO, PavFrmulaVO pavFrmulaVO,
	 * ModelMap model, HttpServletRequest request, HttpSession session) throws
	 * Exception { int successCount = 0; Map<String, Object> map = new
	 * HashMap<String, Object>(); // 1. 조사자료 현황 조회 (JSMHHM) List<SrvyDtaSttusVO>
	 * srvyDtaSttusList = srvyDtaSttusService
	 * .selectSrvyDtaSttusNotPRDCTN_AT(searchVO); if (srvyDtaSttusList == null
	 * || srvyDtaSttusList.size() <= 0) { // 조사자료 현황이 없으면 종료. map.put("result",
	 * "emptylist"); map.put("resultMSG", "집계 대상 조사 자료가 없습니다."); return map; }
	 * else { String frmula_nm = egovPropertyService.getString("FRMULA_NM",
	 * "NHPCI"); if (StringUtils.isEmpty(pavFrmulaVO.getFRMULA_NM())) {
	 * pavFrmulaVO.setFRMULA_NM(frmula_nm); } pavFrmulaVO.setUSE_AT("Y");
	 * pavFrmulaVO.setDELETE_AT("N"); // PavFrmulaVO pavFrmulaOne = String
	 * sheetName = "DBLoading";
	 *
	 * PavFrmulaVO pavFrmulaOne = pavFrmulaService
	 * .selectPavFrmula(pavFrmulaVO); // 산출식이 없으면 종료 if (pavFrmulaOne == null ||
	 * StringUtils.isEmpty(pavFrmulaOne.getFRMULA_NM())) { map.put("result",
	 * "noformula"); map.put("resultMSG", "산출식 데이터가 없습니다."); return map; }
	 *
	 * // 조사자료 현황이 있으면 계속 진행 for (SrvyDtaSttusVO srvyDtaSttusVO :
	 * srvyDtaSttusList) { srvyDtaSttusVO.setFRMULA_NM(frmula_nm); HashMap
	 * prc_result = srvyDtaExcelService .procAggregateGeneral(srvyDtaSttusVO);
	 * String o_PROCCODE = (String) prc_result.get("o_PROCCODE"); String
	 * o_PROCMSG = (String) prc_result.get("o_PROCMSG"); map.put("result",
	 * o_PROCCODE); map.put("result", o_PROCCODE); if
	 * ("true".equals(o_PROCCODE)) { successCount++; } else {
	 * map.put("resultMSG", o_PROCMSG); return map; }
	 *
	 *
	 * } } map.put("resultCount", successCount);
	 *
	 * return map;
	 *
	 * }
	 */
	private String getReadXLData(String fileName, String sheetName) throws Exception {

		// 엑셀파일 실행
		FileInputStream fis = new FileInputStream(checkFilePath(fileName, "path"));

		/*
		 * // HFFS : .xls POIFSFileSystem fs = new POIFSFileSystem(fis);
		 * HSSFWorkbook wb = new HSSFWorkbook(fs); HSSFSheet sheet =
		 * wb.getSheet(sheetName);
		 */

		// XFFS : .xlsx
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		XSSFCell cur;

		String result = "";

		try {

			// 엑셀데이터 row, cell 건수 확인
			int rowNum = sheet.getPhysicalNumberOfRows();
			int cellNum = sheet.getRow(0).getLastCellNum();

			// 엑셀 식(formula)으로 된 데이터 읽기
			FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();

			// DOM생성
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// 루트 엘리먼트
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("NewDataSet");
			doc.appendChild(rootElement);

			// i => 엑셀 row의 수
			for (int i = 1; i < rowNum; i++) { // 헤더제외. 1부터 시작

				if (!formulaEval.evaluate(sheet.getRow(i).getCell(0)).formatAsString().equals("0.0")) {

					// XLdata 엘리먼트
					Element xlData = doc.createElement("XLData");
					rootElement.appendChild(xlData);

					// j => 엑셀 cell의 수
					for (int j = 0; j < cellNum; j++) {
						Element cellData = doc.createElement(sheet.getRow(0).getCell(j).getStringCellValue()); // row(0).cell(j).getValue()
																												// 헤더
						// cellData.appendChild(doc.createTextNode(
						// sheet.getRow(i).getCell(j).getStringCellValue() ));
						// // row(i).cell(j).getValue() value
						String val = formulaEval.evaluate(sheet.getRow(i).getCell(j)) == null ? "" : formulaEval.evaluate(sheet.getRow(i).getCell(j)).formatAsString();

						if (val.contains(".") && val.split("[.]")[1].equals("0")) {
							val = val.split("[.]")[0];
						}

						cellData.appendChild(doc.createTextNode(val));

						xlData.appendChild(cellData);
					}
				}
			}

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			/*
			 * transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			 * transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 */

			// DOMSource source = new DOMSource(doc);

			// StreamResult result = new StreamResult(new FileOutputStream(new
			// File("C:\\file.xml")));
			// 파일로 쓰지 않고 콘솔에 찍어보고 싶을 경우 다음을 사용 (디버깅용)
			// StreamResult result = new StreamResult(System.out);

			// transformer.transform(source, result);

			// System.out.println("File saved!");

			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			StringWriter writer = new StringWriter();
			try {
				transformer.transform(new DOMSource(doc), new StreamResult(writer));
				result = writer.getBuffer().toString();
			} catch (Exception e) {
				LOGGER.debug("예외발생내역:" + e);
			} finally {
				writer.close();
			}
			// http://theeye.pe.kr/archives/1437
		} catch (Exception e) {

			fis.close();
			LOGGER.debug("예외발생내역:" + e);

		} finally {
			fis.close();
		}

		return result;
	}
	
	//엑셀자료 DB INSERT
	private String getReadDbData(String fileName, String sheetName) throws Exception {

		// 엑셀파일 실행
		FileInputStream fis = new FileInputStream(checkFilePath(fileName, "path"));

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);

		String result = "";
		String colName = "";

		try {
			
			// 엑셀데이터 row, cell 건수 확인
			int rowNum = sheet.getPhysicalNumberOfRows();
			int cellNum = sheet.getRow(0).getLastCellNum();

			Map<String, Object> params = new HashMap<String, Object>();

			// 엑셀 식(formula)으로 된 데이터 읽기
			FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();

			// i => 엑셀 row의 수
			for (int i = 1; i < rowNum; i++) { // 헤더제외. 1부터 시작
				if (!formulaEval.evaluate(sheet.getRow(i).getCell(0)).formatAsString().equals("0.0")) {

					// j => 엑셀 cell의 수
					for (int j = 0; j < cellNum; j++) {
						colName = sheet.getRow(0).getCell(j).getStringCellValue();
						
						//셀 열 vo
						String val = formulaEval.evaluate(sheet.getRow(i).getCell(j)) == null ? "" : formulaEval.evaluate(sheet.getRow(i).getCell(j)).formatAsString();
						if (val.contains(".") && val.split("[.]")[1].equals("0")) {
							val = val.split("[.]")[0];
						}
						params.put(colName, val);
					}
					//srvyDtaExcelService.insertSrvyDtaDetailExcel(params);
				}
			}

		} catch (Exception e) {

			fis.close();
			LOGGER.debug("예외발생내역:" + e);
			e.printStackTrace();

		} finally {
			fis.close();
		}

		return result;
	}

	// 엑셀파일 건수 가져오기
	private int getReadXLDataCnt(String fileName, String sheetName) throws Exception {

		// 엑셀파일 실행
		FileInputStream fis = new FileInputStream(checkFilePath(fileName, "path"));

		// XFFS : .xlsx
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);

		int cnt = 0;

		try {

			if (sheet == null) {
				return -1;
			}

			// 엑셀데이터 row 건수 확인
			cnt = sheet.getPhysicalNumberOfRows() - 1;
		} catch (Exception e) {
			fis.close();
			LOGGER.debug("예외발생내역:" + e);

		} finally {
			fis.close();
		}

		return cnt;
	}

	// 엑셀파일 validation check
	private Map<String, Object> validReadXLData(String fileName, String sheetName) throws Exception {

		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();

		String srvyDe = "";

		List<EgovMap> cols = cmmnService.selectCols("tn_mumm_sctn_srvy_dta");

		// 엑셀파일 실행
		FileInputStream fis = new FileInputStream(checkFilePath(fileName, "path"));

		// XFFS : .xlsx
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);

		int cnt = 0;

		try {

			// 엑셀데이터 row, cell 건수 확인
			int rowNum = sheet.getPhysicalNumberOfRows();
			int cellNum = sheet.getRow(0).getLastCellNum();

			// 엑셀 식(formula)으로 된 데이터 읽기
			FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();

			//181109 wijy 수정 : 조사자료 날짜 체크
			//2행 1~2열의 날짜만 가지고 체크함
			String sSrvyYear = formulaEval.evaluate(sheet.getRow(1).getCell(0)) == null ? null : formulaEval.evaluate(sheet.getRow(1).getCell(0)).formatAsString().trim();
			String sSrvyMt = formulaEval.evaluate(sheet.getRow(1).getCell(1)) == null ? null : formulaEval.evaluate(sheet.getRow(1).getCell(1)).formatAsString().trim();

			sSrvyYear = sSrvyYear.replaceAll("\"", "");
			sSrvyMt = sSrvyMt.replaceAll("\"", "");

			int nSrvyYear = 0;
			int nSrvyMt = 0;

			if(sSrvyYear != null && !sSrvyYear.equals("")) nSrvyYear = (int)Float.parseFloat(sSrvyYear);
			if(sSrvyMt != null && !sSrvyMt.equals("")) nSrvyMt = (int)Float.parseFloat(sSrvyMt);

			Calendar today = Calendar.getInstance();
			Calendar c = (Calendar) today.clone();

			c.set(Calendar.YEAR, nSrvyYear);
			c.set(Calendar.MONTH, nSrvyMt - 1);

			//날짜가 요번달 이후인 경우
			if(today.compareTo(c) < 0) {
				map.put("result", result);
				map.put("errorCol", "");
				map.put("rowIndex", 0);
				map.put("resultMsg", "입력데이터의 조사날짜가 현재 날짜 이후입니다. 데이터를 확인해 주십시오.");
				return map;
			}



			// j => 엑셀 cell의 수
			for (int j = 0; j < cellNum; j++) {
				String colName = sheet.getRow(0).getCell(j).getStringCellValue();
				EgovMap colInfo = getCol(cols, colName);

				if (colInfo == null) {
					map.put("result", result);
					map.put("errorCol", "");
					map.put("rowIndex", "");
					map.put("resultMsg", "DB로딩시 필요한 컬럼명이 없습니다.");
					return map;
				}

				String colType = colInfo.get("dataType") == null ? null : colInfo.get("dataType").toString();
				String colLength = colInfo.get("dataLength") == null ? null : colInfo.get("dataLength").toString();
				String colPrecision = colInfo.get("dataPrecision") == null ? null : colInfo.get("dataPrecision").toString();
				String DataScale = colInfo.get("dataScale") == null ? null : colInfo.get("dataScale").toString();

				if (colName.equals("SRVY_DE")) {
					srvyDe = sheet.getRow(1).getCell(j).getStringCellValue();
				}

				// i => 엑셀 row의 수
				for (int i = 1; i < rowNum; i++) { // 헤더제외. 1부터 시작

					String val = formulaEval.evaluate(sheet.getRow(i).getCell(j)) == null ? null : formulaEval.evaluate(sheet.getRow(i).getCell(j)).formatAsString().trim();

					if (val == null) {
						continue;
					}

					if (val.contains(".") && val.split("[.]")[1].equals("0")) {
						val = val.split("[.]")[0];
					}

					if (val.contains("\"")) {
						val = val.replaceAll("\"", "");
					}

					// 길이 체크
					if (val.length() > Integer.parseInt(colLength)) {
						map.put("result", result);
						map.put("errorCol", colName);
						map.put("rowIndex", i);
						map.put("resultMsg", "길이 값이 다릅니다.(exel:" + val.length() + ", DB:" + colLength + ")");
						return map;
					}

					if (colType.equals("NUMBER") || colType.equals("DATE")) {
						if (!isStringDouble(val)) {
							map.put("result", result);
							map.put("errorCol", colName);
							map.put("rowIndex", i);
							map.put("resultMsg", "숫자 컬럼에 숫자 외 데이터가 존재합니다.");
							return map;
						}

						if (colPrecision == null && DataScale == null) {
							continue;
						}
						if (!val.contains(".")) {
							continue;
						}

						String val1 = val.split("[.]")[0];
						String val2 = val.split("[.]")[1];

						if (val1.length() > Integer.parseInt(colPrecision)) {
							map.put("result", result);
							map.put("errorCol", colName);
							map.put("rowIndex", i);
							map.put("resultMsg", "숫자 길이(precision)가 다릅니다.(exel:" + val1.length() + ", DB:" + colPrecision + ")");
							return map;
						}
						if (val2.length() > Integer.parseInt(DataScale)) {
							map.put("result", result);
							map.put("errorCol", colName);
							map.put("rowIndex", i);
							map.put("resultMsg", "숫자 길이(dataScale)가 다릅니다.(exel:" + val2.length() + ", DB:" + DataScale + ")");
							return map;
						}
					}
				}
			}

			result = true;
		} catch (Exception e) {
			fis.close();
			LOGGER.debug("예외발생내역:" + e);
			map.put("result", result);
			map.put("errorCol", "");
			map.put("rowIndex", "");
			map.put("resultMsg", "자료조사 validation check에 실패하였습니다. 파일 데이터의 확인이 필요합니다.");
			return map;
		} finally {
			fis.close();
		}

		map.put("srvyDe", srvyDe);

		map.put("result", result);
		map.put("errorCol", "");
		map.put("rowIndex", "");
		map.put("resultMsg", "자료조사 validation check에 성공하였습니다.");
		return map;
	}

	// 해당 컬럼 정보 조회
	private EgovMap getCol(List<EgovMap> colList, String colName) {
		EgovMap colInfo = null;

		for (EgovMap col : colList) {
			String colNm = col.get("columnName") == null ? null : col.get("columnName").toString();
			if (colNm.equalsIgnoreCase(colName)) {
				colInfo = col;
				break;
			}
		}
		return colInfo;
	}

	// 숫자 여부 체크
	private boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	// 엑셀파일 건수 가져오기
		private int getReadZipDataCnt(String fileName, String filePath) throws Exception {
			
			String path = checkFilePath(fileName, "path");
			String fileNm = "";
	        int cnt = 0;
	        
	        Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(currentDate);
			filePath += File.separator + "srvy" + File.separator + date;
			
			// 폴더 경로
			File uploadFolder =  new File(checkFilePath(filePath,"path"));
	        File zipFile = new File(path);
	        FileInputStream fis = null;
	        ZipInputStream zis = null;
	        ZipEntry zipentry = null;
	        
	        try {
	        	//파일 스트림
	            fis = new FileInputStream(zipFile);
	            
	            //Zip 파일 스트림
	            zis = new ZipInputStream(fis);
	            
	            //entry가 없을때까지 뽑기
	            while ((zipentry = zis.getNextEntry()) != null) {
	                fileNm = zipentry.getName();
	                File file = new File(uploadFolder, fileNm);
	                //entiry가 폴더면 폴더 생성
	                if (zipentry.isDirectory()) {
	                    file.mkdirs();
	                } else {
	                    //파일이면 파일 만들기
	                    createFile(file, zis);
	                }
	            }
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (zis != null)
	                zis.close();

	            if (fis != null)
	                fis.close();
			}
			return cnt;
		}
		
		private static void createFile(File file, ZipInputStream zis) throws Exception {

	        //디렉토리 확인
	        File parentDir = new File(file.getParent());

	        //디렉토리가 없으면 생성하자
	        if (!parentDir.exists()) {
	            parentDir.mkdirs();
	        }

	        //파일 스트림 선언
	        FileOutputStream fos = null;
	        try {
	        	fos = new FileOutputStream(file); 
	            byte[] buffer = new byte[256];
	            int size = 0;
	            //Zip스트림으로부터 byte뽑아내기
	            while ((size = zis.read(buffer)) > 0) {
	                //byte로 파일 만들기
	                fos.write(buffer, 0, size);
	            }
	        } catch (Exception e) {
	        	fos.close();
	            e.printStackTrace();
	        }
	    }
	

	@RequestMapping(value = "/selectSrvyExcelList.do")
	public String selectSrvyDtaList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
		model.addAttribute("roadNoList", roadNoList);

		return "/srvy/srvyExcelList";
	}

	@RequestMapping(value = "/selectSrvyDtaEvlList.do")
	public String selectSrvyDtaEvlList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		return "/srvy/srvyDtaEvlList";
	}

	/**
	 * 포장상태 평가정보 조회 _ 페이지 조회
	 *
	 * @param srvyDtaExcelVO
	 * @return "/srvy/srvyDtaEvlInfoList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectSrvyDtaEvlInfoList.do")
	public String srvyDtaEvlInfoList(@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		// 관리기관
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(new DeptVO()));

		// 도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");

		// 노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		if (routeInfoVO.getROAD_NO() != null) {

			routeInfoVO = routeInfoService.selectRouteInfo(routeInfoVO);

		}

		// 조사년도
		List<SmDtaGnlSttusVO> srvyYearList = smDtaGnlSttusService.selectSmDtaGnlSttusYearList(smDtaGnlSttusVO);

		// model input
		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("routeInfoVO", routeInfoVO);
		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);
		model.addAttribute("srvyYearList", srvyYearList);

		return "/srvy/srvyDtaEvlInfoList";
	}

	@RequestMapping(value = "/selectSrvyDtaEvlInfoListExcel.do")
	public View selectSrvyRoutLenStatsExcel(@ModelAttribute CntrwkDtlVO cntrwkDtlVO, SrvyDtaExcelVO srvyDtaExcelVO, MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

		List dataList = srvyDtaExcelService.selectSrvyDtaEvlInfoListExcel(mummSctnSrvyDtaVO);
		String[] excel_title = { "조사번호", "조사년도", "관리기관", "도로등급", "노선번호", "노선명", "행선", "차로", "시점(km)", "종점(km)", "GPCI", "주파손", "파손원인" };
		String[] excel_column = { "srvy_no", "srvy_de", "dept_code", "road_grad", "road_no_val", "road_nm", "direct_code", "track", "strtpt", "endpt", "gpci", "cr", "cuz" };

		model.addAttribute("file_name", "포장상태평가조회");
		model.addAttribute("file_name", "포장상태평가조회");
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();

		// ***************************** 데이터값 호출을 위한 기본 셋팅
		/*
		 * System.out.println("1123"); HashMap<String, Object> paraMap = new
		 * HashMap<String, Object>(); HashMap<String, Object> resultMap = new
		 * HashMap<String, Object>(); List result = new ArrayList(); List
		 * resultDept = new ArrayList(); List resultRout = new ArrayList();
		 * HashMap<String, Object> tempMap = new HashMap<String, Object>();
		 *
		 * Calendar calendar = Calendar.getInstance(); SimpleDateFormat
		 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 *
		 * HSSFWorkbook objWorkBook = new HSSFWorkbook(); HSSFSheet objSheet =
		 * null; HSSFRow objRow = null; HSSFCell objCell = null; // 셀 생성
		 *
		 * // 제목 폰트 HSSFFont font = objWorkBook.createFont();
		 * font.setFontHeightInPoints((short) 9); font.setBoldweight((short)
		 * font.BOLDWEIGHT_BOLD); font.setFontName("맑은고딕");
		 *
		 * // 제목 스타일에 폰트 적용, 정렬 HSSFCellStyle styleHd =
		 * objWorkBook.createCellStyle(); // 제목 스타일 styleHd.setFont(font);
		 * styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		 * styleHd.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		 * styleHd.setBorderLeft((short) 1); styleHd.setBorderBottom((short) 1);
		 * styleHd.setBorderTop((short) 1); styleHd.setBorderRight((short) 1);
		 *
		 * objSheet = objWorkBook.createSheet("포장상태평가 조회"); // 워크시트 생성
		 *
		 * objRow = objSheet.createRow(0); objCell = objRow.createCell(0);
		 * objCell.setCellValue("포장상태 평가 조회"); // String[] excel_title =
		 * {"조사년도", "관리기관", "도로등급", "노선번호", "노선명", "행선", // "차로", "시점(km)",
		 * "종점(km)", "GPCI"};
		 *
		 * result = srvyDtaExcelService
		 * .selectSrvyDtaEvlInfoListExcel(mummSctnSrvyDtaVO);
		 *
		 * objRow = objSheet.createRow(1); objCell = objRow.createCell(0);
		 * objCell.setCellValue("조사번호"); objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(1); objCell.setCellValue("조사년도");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(2); objCell.setCellValue("관리기관");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(3); objCell.setCellValue("도로등급");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(4); objCell.setCellValue("노선번호");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(5); objCell.setCellValue("노선명");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(6); objCell.setCellValue("행선");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(7); objCell.setCellValue("차로");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(8); objCell.setCellValue("시점(km)");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(9); objCell.setCellValue("종점(km)");
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(10); objCell.setCellValue("GPCI");
		 * objCell.setCellStyle(styleHd);
		 *
		 *
		 * for (int i = 0; i < result.size(); i++) { tempMap = (HashMap)
		 * result.get(i);
		 *
		 *
		 * // String[] excel_column = {"SRVY_DE", "DEPT_CODE", "ROAD_GRAD", //
		 * "ROAD_NO_VAL", //
		 * "ROAD_NM","DIRECT_CODE","TRACK","STRTPT","ENDPT","GPCI"};
		 *
		 * objRow = objSheet.createRow(i + 2);
		 *
		 * objCell = objRow.createCell(0);
		 * objCell.setCellValue(tempMap.get("SRVY_NO").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(1);
		 * objCell.setCellValue(tempMap.get("SRVY_DE").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(2);
		 * objCell.setCellValue(tempMap.get("DEPT_CODE").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(3);
		 * objCell.setCellValue(tempMap.get("ROAD_GRAD").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(4);
		 * objCell.setCellValue(tempMap.get("ROAD_NO_VAL").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(5);
		 * objCell.setCellValue(tempMap.get("ROAD_NM").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(6);
		 * objCell.setCellValue(tempMap.get("DIRECT_CODE").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(7);
		 * objCell.setCellValue(tempMap.get("TRACK").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(8);
		 * objCell.setCellValue(tempMap.get("STRTPT").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(9);
		 * objCell.setCellValue(tempMap.get("ENDPT").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * objCell = objRow.createCell(10);
		 * objCell.setCellValue(tempMap.get("GPCI").toString());
		 * objCell.setCellStyle(styleHd);
		 *
		 * }
		 *
		 * response.setContentType("Application/Msexcel"); response.setHeader(
		 * "Content-Disposition", "ATTachment; Filename=" +
		 * URLEncoder.encode("포장상태평가 조회", "UTF-8") + "_" +
		 * dateFormat.format(calendar.getTime()) + ".xls");
		 *
		 * OutputStream fileOut = response.getOutputStream();
		 * objWorkBook.write(fileOut); fileOut.close();
		 *
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */

	}

	@RequestMapping(value = "/srvyDtaExcelListExcel.do")
	public View selectSrvyRoutLenStatsExcel(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

		String roadNo = request.getParameter("ROAD_NO");
		String schSrbyDE1 = request.getParameter("SCH_SRVY_DE1");
		String schSrbyDE2 = request.getParameter("SCH_SRVY_DE2");

		srvyDtaExcelVO.setROAD_NO(roadNo);
		srvyDtaExcelVO.setSCH_SRVY_DE1(schSrbyDE1);
		srvyDtaExcelVO.setSCH_SRVY_DE2(schSrbyDE2);
		List dataList = srvyDtaExcelService.srvyDtaExcelListExcelDownload(srvyDtaExcelVO);
		String[] excel_title = { "파일명", "조사일자", "자료건수", "포장상태 평가 여부" };
		String[] excel_column = { "file_nm", "srvy_de", "data_co", "evl_process_at" };

		model.addAttribute("file_name", "조사자료 이력조회");
		model.addAttribute("file_name", "조사자료 이력조회");
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 포장상태 평가정보 조회 _ 목록 조회
	 *
	 * @param srvyDtaExcelVO
	 * @return "/srvy/srvyDtaEvlInfoList"
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaEvlInfoDetailList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaEvlInfoDetailList(@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mummSctnSrvyDtaVO.getPage());
			paginationInfo.setRecordCountPerPage(mummSctnSrvyDtaVO.getPageUnit());
			paginationInfo.setPageSize(mummSctnSrvyDtaVO.getRows());
			mummSctnSrvyDtaVO.setUsePage(true);

			mummSctnSrvyDtaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mummSctnSrvyDtaVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mummSctnSrvyDtaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			List<MummSctnSrvyDtaVO> items = srvyDtaExcelService.srvyDtaEvlInfoDetailList(mummSctnSrvyDtaVO);
			int totCnt = srvyDtaExcelService.srvyDtaEvlInfoDetailListCnt(mummSctnSrvyDtaVO);

			int total_page = 0;
			if (totCnt > 0)
				total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

			// 결과 JSON 저장
			map.put("page", mummSctnSrvyDtaVO.getPage());
			map.put("total", total_page);
			map.put("records", totCnt);
			map.put("rows", items);

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return map;
	}

	/**
	 * 포장상태 평가정보 조회 _ 상세조회 페이지 조회
	 *
	 * @param srvyDtaExcelVO
	 * @return "/srvy/srvyDtaEvlInfoDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectSrvyDtaEvlInfoDetail.do")
	public String srvyDtaEvlInfoDetail(@ModelAttribute SmDtaGnlSttusVO smDtaGnlSttusVO, MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		smDtaGnlSttusVO = smDtaGnlSttusService.selectSmDtaGnlSttus(smDtaGnlSttusVO);

		SmDtaGnlSttusVO param = new SmDtaGnlSttusVO();
		param.setROUTE_CODE(smDtaGnlSttusVO.getROUTE_CODE());
		param.setDIRECT_CODE(smDtaGnlSttusVO.getDIRECT_CODE());
		param.setTRACK(smDtaGnlSttusVO.getTRACK());
		param.setSTRTPT(smDtaGnlSttusVO.getSTRTPT());
		param.setENDPT(smDtaGnlSttusVO.getENDPT());

		mummSctnSrvyDtaVO.setSRVY_YEAR(mummSctnSrvyDtaVO.getSCH_SRVY_YEAR());

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);
		model.addAttribute("smDtaGnlSttusVO", smDtaGnlSttusVO);
		model.addAttribute("srvyYearList", smDtaGnlSttusService.selectSmDtaGnlSttusYearList(param));

		return "/srvy/srvyDtaEvlInfoDetail";
	}

	/**
	 * 포장상태 평가정보 조회 _ 상세조회
	 *
	 * @param srvyDtaExcelVO
	 * @return "/srvy/srvyDtaEvlInfoList"
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaEvlResearchInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MummSctnSrvyDtaVO srvyDtaEvlResearchInfo(@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		MummSctnSrvyDtaVO researchInfo = new MummSctnSrvyDtaVO();

		try {

			// 조사정보
			String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
			List<String> objArr = new ArrayList<String>();

			for (int i = 0; i < arr.length; i++) {

				objArr.add(arr[i]);

			}

			mummSctnSrvyDtaVO.setCELL_ID_ARR(objArr);

			researchInfo = mummSctnSrvyDtaService.mummSctnSrvyDtaSctnList(mummSctnSrvyDtaVO).get(0);

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return researchInfo;
	}

	/**
	 * 포장상태 평가정보 조회 _ 상세조회
	 *
	 * @param srvyDtaExcelVO
	 * @return "/srvy/srvyDtaEvlInfoList"
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/selectSrvyDtaEvlInfoDetail.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSrvyDtaEvlInfoDetail(@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(mummSctnSrvyDtaVO.getPage());
			paginationInfo.setRecordCountPerPage(mummSctnSrvyDtaVO.getPageUnit());
			paginationInfo.setPageSize(mummSctnSrvyDtaVO.getRows());
			mummSctnSrvyDtaVO.setUsePage(true);

			mummSctnSrvyDtaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			mummSctnSrvyDtaVO.setLastIndex(paginationInfo.getLastRecordIndex());
			mummSctnSrvyDtaVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			List<MummSctnSrvyDtaVO> items = srvyDtaExcelService.srvyDtaEvlInfoDetailList(mummSctnSrvyDtaVO);
			int totCnt = srvyDtaExcelService.srvyDtaEvlInfoDetailListCnt(mummSctnSrvyDtaVO);

			int total_page = 0;
			if (totCnt > 0)
				total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

			// 결과 JSON 저장
			map.put("page", mummSctnSrvyDtaVO.getPage());
			map.put("total", total_page);
			map.put("records", totCnt);
			map.put("rows", items);

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return map;
	}

	/**
	 * 관리자 > 수식관리 > 포장상태 평가수식조회 페이지 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 14.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/srvy/srvyDtaEvlFmla"
	 * @exception : Exception
	 */
	@RequestMapping(value = "/srvyDtaEvlFmla.do")
	public String srvyDtaEvlFmla(@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/srvy/srvyDtaEvlFmla";
	}

	/**
	 * 관리자 > 수식관리 > 수식 변수 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 14.
	 *
	 * @param : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
	 * @return : result
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaEvlInitFmlaVar.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PavFrmulaVO> srvyDtaEvlInitFmlaVar(@RequestBody PavFrmulaVO pavFrmulaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		List<PavFrmulaVO> result = new ArrayList<PavFrmulaVO>();

		try {

			result = srvyDtaExcelService.srvyDtaEvlInitFmlaVar(pavFrmulaVO);

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	/**
	 * 관리자 > 수식관리 > 수식 변수 변경
	 *
	 * @author : JOY
	 * @date : 2017. 11. 14.
	 *
	 * @param : VO - 조회할 정보가 담긴 VO
	 * @return : int
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaEvlCngVal.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody int srvyDtaEvlCngVal(@RequestBody PavFrmulaVO pavFrmulaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		PavFrmulaVO frmulaNo = new PavFrmulaVO();
		int result = 0;

		try {
			// 수식번호 조회
			frmulaNo = srvyDtaExcelService.selectPavFrmulaNo(pavFrmulaVO);
			pavFrmulaVO.setFRMULA_NO(frmulaNo.getFRMULA_NO());

			String nm = pavFrmulaVO.getFRMULA_NM();
			if(pavFrmulaVO.getVAR_INDEX().equals("지수3") && (nm.equals("AC") || nm.equals("BC") || nm.equals("LC") || nm.equals("TC") || nm.equals("RD"))){
				pavFrmulaVO.setFRMULA_NM("TRUE");
			}
			result = srvyDtaExcelService.updatePavFrmulaVar(pavFrmulaVO);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
