package kr.go.gg.gpms.smdtalaststtus.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.smdtalaststtus.service.SmDtaLastSttusService;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;
import kr.go.gg.gpms.srvydtaexcel.service.SrvyDtaExcelService;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

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
import org.springframework.web.servlet.View;

import egovframework.cmmn.util.DateUtil;
import egovframework.cmmn.util.ExcelView;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : SmDtaLastSttusController.java
 * @Description : SmDtaLastSttus Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-09-12
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller("smDtaLastSttusController")
public class SmDtaLastSttusController extends BaseController {

	@Resource(name = "smDtaLastSttusService")
	private SmDtaLastSttusService smDtaLastSttusService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "srvyDtaExcelService")
	private SrvyDtaExcelService srvyDtaExcelService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SmDtaLastSttusController.class);

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 목록을 조회한다. (pageing)
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/smdtalaststtus/SmDtaLastSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSmDtaLastSttusList.do" })
	public String selectSmDtaLastSttusList(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {
		return "/smdtalaststtus/SmDtaLastSttusList";
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 목록을 조회한다. (pageing)
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/smdtalaststtus/SmDtaLastSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastSttusList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSmDtaLastSttusListRest(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaLastSttusVO.getPage());
		paginationInfo.setRecordCountPerPage(smDtaLastSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaLastSttusVO.getRows());
		smDtaLastSttusVO.setUsePage(true);

		smDtaLastSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaLastSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaLastSttusVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		List<SmDtaLastSttusVO> items = smDtaLastSttusService
				.selectSmDtaLastSttusList(smDtaLastSttusVO);
		int total_count = smDtaLastSttusService
				.selectSmDtaLastSttusListTotalCount(smDtaLastSttusVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) smDtaLastSttusVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", smDtaLastSttusVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 상세를 조회한다.
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/smdtalaststtus/SmDtaLastSttusView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSmDtaLastSttus.do" })
	public String selectSmDtaLastSttus(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {

		model.addAttribute("smDtaLastSttusVO",
				smDtaLastSttusService.selectSmDtaLastSttus(smDtaLastSttusVO));
		return "/smdtalaststtus/SmDtaLastSttusView";
	}

	@RequestMapping(value = { "/smdtalaststtus/addSmDtaLastSttusView.do" })
	public String addSmDtaLastSttusView(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			Model model) throws Exception {
		model.addAttribute("smDtaLastSttusVO", new SmDtaLastSttusVO());
		return "/smdtalaststtus/SmDtaLastSttusRegist";
	}

	@RequestMapping(value = { "/api/smdtalaststtus/addSmDtaLastSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaLastSttusVO addSmDtaLastSttus(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		BindBeansToActiveUser(smDtaLastSttusVO);
		smDtaLastSttusService.insertSmDtaLastSttus(smDtaLastSttusVO);
		smDtaLastSttusVO.setResultSuccess("true");
		smDtaLastSttusVO.setResultMSG("정상 등록되었습니다.");
		return smDtaLastSttusVO;
	}

	@RequestMapping(value = { "/smdtalaststtus/updateSmDtaLastSttusView.do" })
	public String updateSmDtaLastSttusView(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			Model model) throws Exception {
		model.addAttribute("smDtaLastSttusVO",
				smDtaLastSttusService.selectSmDtaLastSttus(smDtaLastSttusVO));
		return "/smdtalaststtus/SmDtaLastSttusUpdate";
	}

	@RequestMapping(value = { "/api/smdtalaststtus/updateSmDtaLastSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaLastSttusVO updateSmDtaLastSttus(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		BindBeansToActiveUser(smDtaLastSttusVO);
		smDtaLastSttusService.updateSmDtaLastSttus(smDtaLastSttusVO);
		smDtaLastSttusVO.setResultSuccess("true");
		smDtaLastSttusVO.setResultMSG("정상 수정되었습니다.");
		return smDtaLastSttusVO;
	}

	@RequestMapping(value = { "/api/smdtalaststtus/deleteSmDtaLastSttus.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaLastSttusVO deleteSmDtaLastSttus(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		BindBeansToActiveUser(smDtaLastSttusVO);
		smDtaLastSttusService.deleteSmDtaLastSttus(smDtaLastSttusVO);
		smDtaLastSttusVO.setResultSuccess("true");
		smDtaLastSttusVO.setResultMSG("정상 삭제되었습니다.");
		return smDtaLastSttusVO;
	}

	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록 을 조회한다.
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectRouteEvlList.do" })
	public String selectRouteEvlList(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		// 노선 번호
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService
				.selectRouteInfoList(routeInfoVO);

		model.addAttribute("roadNoList", roadNoList);
		return "/srvy/srvyDtaRouteEvlList";
	}

	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록 을 조회한다. (pageing)
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectRouteEvlList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectRouteEvlListRest(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smDtaLastSttusVO.getPage());
		paginationInfo.setRecordCountPerPage(smDtaLastSttusVO.getPageUnit());
		paginationInfo.setPageSize(smDtaLastSttusVO.getRows());
		smDtaLastSttusVO.setUsePage(true);

		smDtaLastSttusVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smDtaLastSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smDtaLastSttusVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		List<SmDtaLastSttusVO> items = smDtaLastSttusService
				.selectRouteEvlList(smDtaLastSttusVO);
		int total_count = smDtaLastSttusService
				.selectRouteEvlListTotalCount(smDtaLastSttusVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) smDtaLastSttusVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", smDtaLastSttusVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 포장상태 평가 - 노선단위 수시병가를 실행한다.
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/routeEvl.do" })
	public String routeEvl(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		BindBeansToActiveUser(smDtaLastSttusVO);

		HashMap result = smDtaLastSttusService
				.prcClacPredctRouteSrvyEvl(smDtaLastSttusVO);

		model.addAttribute("PROCCODE", result.get("o_PROCCODE"));
		model.addAttribute("PROCMSG", result.get("o_PROCMSG"));

		return "jsonView";
	}

	/**
	 * 포장상태 예측 - 목록 조회
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSrvyDtaPredctList.do" })
	public String selectSrvyDtaPredctList(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			RouteInfoVO routeInfoVO, ModelMap model) throws Exception {

		return "/smdtalaststtus/srvyDtaPredctList";
	}

	/**
	 * 포장상태 예측 - 목록 조회 을 조회한다. (pageing)
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSrvyDtaPredctList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSrvyDtaPredctListRest(
			@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model)
			throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getRows());
		srvyDtaExcelVO.setUsePage(true);

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService
				.selectSrvyDtaPredctList(srvyDtaExcelVO);
		int total_count = srvyDtaExcelService
				.selectSrvyDtaPredctListTotalCount(srvyDtaExcelVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) srvyDtaExcelVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaExcelVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 포장상태 예측 - 예측자료 생성
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/srvy/srvyDtaRouteEvlList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/createPredct.do" })
	public String createPredct(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			RouteInfoVO routeInfoVO, ModelMap model) {

		int totCnt = 0;
		int successCnt = 0;

		try {
			BindBeansToActiveUser(smDtaLastSttusVO);

			String srvyNoList[] = smDtaLastSttusVO.getSRVY_NO().split(",");

			totCnt = srvyNoList.length;

			for (String srvyNo : srvyNoList) {
				smDtaLastSttusVO.setSRVY_NO(srvyNo);
				HashMap result = smDtaLastSttusService
						.prcClacPredctLast(smDtaLastSttusVO);

				if (result.get("o_PROCCODE").equals("true")) {
					successCnt++;
				}
			}
		} catch (Exception e) {
		}

		model.addAttribute("totCnt", totCnt);
		model.addAttribute("successCnt", successCnt);

		return "jsonView";
	}

	/**
	 * 포장상태 예측 - 예측정보 상세조회 페이지를 조회한다.
	 * 
	 * @author : JOY
	 * @date : 2017. 10. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : "/smdtalaststtus/selectSrvyDtaLastSttusList"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSrvyDtaLastSttusList.do" })
	public String selectSrvyDtaLastSttusList(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {

		// 도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");

		// 노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService
				.selectRouteInfoList(routeInfoVO);

		if (routeInfoVO.getROAD_NO() != null) {

			routeInfoVO = routeInfoService.selectRouteInfo(routeInfoVO);

		}
		// SECTION 구분
		model.addAttribute("sectionType",
				cmmnService.selectCellType(new HashMap<String, String>()));

		// 관리기관
		model.addAttribute("deptList",
				deptService.selectCntrwkDeptList(new DeptVO()));

		// model input
		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("smDtaLastSttusVO", smDtaLastSttusVO);

		return "/smdtalaststtus/srvyDtaLastSttusList";

	}

	@RequestMapping(value = "/smdtalaststtus/selectSrvyDtaLastSttusListExcel.do")
	public View selectSrvyDtaLastSttusListExcel(
			@ModelAttribute CntrwkDtlVO cntrwkDtlVO,
			SrvyDtaExcelVO srvyDtaExcelVO, MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response, SmDtaLastSttusVO smDtaLastSttusVO)
			throws Exception {

		List dataList = smDtaLastSttusService
				.selectSmDtaLastSttusListExcel(smDtaLastSttusVO);
		String[] excel_title = { "집계번호", "산정년도", "노선번호", "노선명", "도로등급", "관리기관", 
				"섹션구분", "행선", "차로", "시점(km)", "종점(km)", "GPCI", "주파손","파손원인", "보수도래시기" };
		String[] excel_column = { "sm_no", "calc_year", "road_no_val",
				"road_name", "road_grad", "dept_code", "sect_se",
				"direct_code", "track", "strtpt", "endpt", "gpci","cr","cuz","rpair_ta" };

		model.addAttribute("file_name", "포장상태예측조회");
		model.addAttribute("file_name", "포장상태예측조회");
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 포장상태 예측 - 예측정보 상세조회 목록 조회한다.
	 * 
	 * @author : JOY
	 * @date : 2017. 10. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSrvyDtaLastSttusList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> apiSelectSrvyDtaLastSttusList(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(smDtaLastSttusVO.getPage());
			paginationInfo
					.setRecordCountPerPage(smDtaLastSttusVO.getPageUnit());
			paginationInfo.setPageSize(smDtaLastSttusVO.getRows());
			smDtaLastSttusVO.setUsePage(true);

			smDtaLastSttusVO
					.setFirstIndex(paginationInfo.getFirstRecordIndex());
			smDtaLastSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
			smDtaLastSttusVO.setRecordCountPerPage(paginationInfo
					.getRecordCountPerPage());

			List<SmDtaLastSttusVO> items = smDtaLastSttusService
					.selectSrvyDtaLastSttusList(smDtaLastSttusVO);
			int total_count = smDtaLastSttusService
					.selectSrvyDtaLastSttusListCnt(smDtaLastSttusVO);
			int total_page = 0;
			if (total_count > 0)
				total_page = (int) Math.ceil((float) total_count
						/ (float) smDtaLastSttusVO.getPageSize());

			// 결과 JSON 저장
			map.put("page", smDtaLastSttusVO.getPage());
			map.put("total", total_page);
			map.put("records", total_count);
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
	 * 포장상태 예측 - 상세정보 페이지를 조회한다.
	 * 
	 * @author : JOY
	 * @date : 2017. 10. 24.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : "/smdtalaststtus/srvyDtaLastSttusDetail"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSrvyDtaLastSttusDetail.do" })
	public String selectSrvyDtaLastSttusDetail(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {

		model.addAttribute("smDtaLastSttusVO", smDtaLastSttusVO);
		model.addAttribute("result",
				smDtaLastSttusService.selectSmDtaLastSttus(smDtaLastSttusVO));
		model.addAttribute("srvyList",
				smDtaLastSttusService.selectSmDtaGnlGPCIList(smDtaLastSttusVO));

		return "/smdtalaststtus/srvyDtaLastSttusDetail";

	}

	/**
	 * 포장상태 예측 - 상세정보의 공사정보를 조회한다.
	 * 
	 * @author : JOY
	 * @date : 2017. 10. 24.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectCntrwkListBySect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCntrwkListBySect(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(smDtaLastSttusVO.getPage());
			paginationInfo
					.setRecordCountPerPage(smDtaLastSttusVO.getPageUnit());
			paginationInfo.setPageSize(smDtaLastSttusVO.getRows());
			smDtaLastSttusVO.setUsePage(true);

			smDtaLastSttusVO
					.setFirstIndex(paginationInfo.getFirstRecordIndex());
			smDtaLastSttusVO.setLastIndex(paginationInfo.getLastRecordIndex());
			smDtaLastSttusVO.setRecordCountPerPage(paginationInfo
					.getRecordCountPerPage());

			List<SmDtaLastSttusVO> items = smDtaLastSttusService
					.selectCntrwkListBySect(smDtaLastSttusVO);
			int total_count = smDtaLastSttusService
					.selectCntrwkListBySectCnt(smDtaLastSttusVO);
			int total_page = 0;
			if (total_count > 0)
				total_page = (int) Math.ceil((float) total_count
						/ (float) smDtaLastSttusVO.getPageSize());

			// 결과 JSON 저장
			map.put("page", smDtaLastSttusVO.getPage());
			map.put("total", total_page);
			map.put("records", total_count);
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
	 * 통계 > 포장상태 예측 > 노선별 통계 페이지 조회
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/smdtalaststtus/SmDtaLastSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSmDtaLastRoutCntStats.do" })
	public String selectSmDtaLastRoutCntStats(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {
		return "/stats/smdtalast/smDtaLastRoutCntStats";
	}

	/**
	 * 통계 > 포장상태 예측 정보를 조회한다
	 * 
	 * @author : skc
	 * @date : 2017. 11. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastStats.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SmDtaLastSttusVO> selectSmDtaLastStats(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		return smDtaLastSttusService.prcStatPredct(smDtaLastSttusVO,
				smDtaLastSttusVO.getPrc_mode());
	}

	/**
	 * 통계 > 포장상태 예측 > 노선별 통계 > 도로등급별 엑셀저장
	 * 
	 * @author : skc
	 * @date : 2017. 11. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastStatsGradExcel.do" })
	public View selectSmDtaLastStatsGradExcel(
			@ModelAttribute SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		smDtaLastSttusVO.setROUTE_CODE(null);
		List dataList = smDtaLastSttusService.prcStatPredctExcel(
				smDtaLastSttusVO, smDtaLastSttusVO.getPrc_mode());

		String FileNm = "포장상태예측_도로구분별통계_";
		String[] excel_title = { "예측년도", "국지도 GPCI", "지방도 GPCI", "경기도 전체 GPCI" };
		String[] excel_column = { "predct_year", "road_gpci_1", "road_gpci_2",
				"road_gpci_3" };

		model.addAttribute("file_name",
				FileNm + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 통계 > 포장상태 예측 > 노선별 통계 > 노선별 엑셀저장
	 * 
	 * @author : skc
	 * @date : 2017. 11. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastStatsRouteExcel.do" })
	public View selectSmDtaLastStatsRouteExcel(
			@ModelAttribute SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		List dataList = smDtaLastSttusService.prcStatPredctExcel(
				smDtaLastSttusVO, "ROUTE");

		String FileNm = "포장상태예측_노선별통계_";
		String[] excel_title = { "예측년도", "노선번호", "GPCI" };
		String[] excel_column = { "predct_year", "route_code", "gpci" };

		model.addAttribute("file_name",
				FileNm + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 통계 > 포장상태 예측 > 관리기관별 통계 페이지 조회
	 * 
	 * @param smDtaLastSttusVO
	 *            - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return "/smdtalaststtus/SmDtaLastSttusList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smdtalaststtus/selectSmDtaLastDeptCntStats.do" })
	public String selectSmDtaLastDeptCntStats(
			@ModelAttribute("searchVO") SmDtaLastSttusVO smDtaLastSttusVO,
			ModelMap model) throws Exception {
		return "/stats/smdtalast/smDtaLastDeptCntStats";
	}

	/**
	 * 통계 > 포장상태 예측 > 관리기관 통계 > 관리기관별 엑셀저장
	 * 
	 * @author : skc
	 * @date : 2017. 11. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastStatsDeptListExcel.do" })
	public View selectSmDtaLastStatsDeptListExcel(
			@ModelAttribute SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		smDtaLastSttusVO.setDEPT_CODE(null);
		List dataList = smDtaLastSttusService.prcStatPredctExcel(
				smDtaLastSttusVO, smDtaLastSttusVO.getPrc_mode());

		String FileNm = "포장상태예측_관리기관별_전체_통계_";
		String[] excel_title = { "예측년도", "북부도로과 GPCI", "도로건설과 GPCI",
				"경기도 전체 GPCI" };
		String[] excel_column = { "predct_year", "dept_gpci_1", "dept_gpci_2",
				"dept_gpci_3" };

		model.addAttribute("file_name",
				FileNm + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 통계 > 포장상태 예측 > 관리기관 통계 > 관리기관별 엑셀저장
	 * 
	 * @author : skc
	 * @date : 2017. 11. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastStatsDeptExcel.do" })
	public View selectSmDtaLastStatsDeptExcel(
			@ModelAttribute SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {
		List dataList = smDtaLastSttusService.prcStatPredctExcel(
				smDtaLastSttusVO, smDtaLastSttusVO.getPrc_mode());

		String FileNm = "포장상태예측_관리기관별_통계_";
		String[] excel_title = { "예측년도", "관리기관명", "GPCI" };
		String[] excel_column = { "predct_year", "dept_nm", "gpci" };

		model.addAttribute("file_name",
				FileNm + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 포장상태 예측 - 예측정보 상세조회 목록 조회한다.
	 * 
	 * @author : JOY
	 * @date : 2017. 10. 23.
	 * 
	 * @param : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/smdtalaststtus/selectSmDtaLastSttusBySrvy.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmDtaLastSttusVO apiSelectSmDtaLastSttusBySrvy(
			@RequestBody SmDtaLastSttusVO smDtaLastSttusVO, ModelMap model)
			throws Exception {

		SmDtaLastSttusVO result = new SmDtaLastSttusVO();

		try {

			result = smDtaLastSttusService
					.selectSmDtaLastSttusBySrvy(smDtaLastSttusVO);

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
