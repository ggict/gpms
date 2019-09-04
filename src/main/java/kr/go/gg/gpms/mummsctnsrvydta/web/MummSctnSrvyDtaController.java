package kr.go.gg.gpms.mummsctnsrvydta.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.cntrwk.service.CntrwkService;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.mummsctnsrvydta.service.MummSctnSrvyDtaService;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.smdtagnlsttus.service.SmDtaGnlSttusService;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;

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
import org.springframework.web.servlet.View;

import egovframework.cmmn.util.DateUtil;
import egovframework.cmmn.util.ExcelView;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

//import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Class Name : MummSctnSrvyDtaController.java
 * @Description : MummSctnSrvyDta Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller("mummSctnSrvyDtaController")
public class MummSctnSrvyDtaController {

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "mummSctnSrvyDtaService")
	private MummSctnSrvyDtaService mummSctnSrvyDtaService;

	@Resource(name = "smDtaGnlSttusService")
	private SmDtaGnlSttusService smDtaGnlSttusService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "cntrwkService")
	private CntrwkService cntrwkService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MummSctnSrvyDtaController.class);

	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다. (pageing)
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectMummSctnSrvyDtaListPage(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mummSctnSrvyDtaVO.getPage());
		paginationInfo.setRecordCountPerPage(mummSctnSrvyDtaVO.getPageUnit());
		paginationInfo.setPageSize(mummSctnSrvyDtaVO.getRows());

		mummSctnSrvyDtaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mummSctnSrvyDtaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mummSctnSrvyDtaVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		List<MummSctnSrvyDtaVO> items = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaList(mummSctnSrvyDtaVO);
		int total_count = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaListTotalCount(mummSctnSrvyDtaVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) mummSctnSrvyDtaVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", mummSctnSrvyDtaVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 통합정보조회 - 포장상태 예측정보
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
	 * @exception Exception
	 *
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectPredctCentry.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> selectPredctCentry(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		List<MummSctnSrvyDtaVO> items = mummSctnSrvyDtaService
				.prcClacPredctEvlPredct(mummSctnSrvyDtaVO);

		return items;
	}

	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 상세를 조회한다.
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab1.do" })
	public String selectMummSctnSrvyDtaTab1(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		HashMap result = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaTab1(mummSctnSrvyDtaVO);
		model.addAttribute("mummSctnSrvyDtaVO", result);

		return "/srvy/mumm/mummSctnSrvyDtaView1";
	}

	@RequestMapping(value = { "/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab2.do" })
	public String selectMummSctnSrvyDtaTab2(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model, HttpServletRequest request) throws Exception {

		// mummSctnSrvyDtaVO.setSRVY_NO(request.getParameter("SRVY_NO"));
		List<?> data_list = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaTab2(mummSctnSrvyDtaVO);
		model.addAttribute("data_list", data_list);

		return "/srvy/mumm/mummSctnSrvyDtaView2";
	}

	@RequestMapping(value = { "/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab3.do" })
	public String selectMummSctnSrvyDtaTab3(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		HashMap result = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaTab1(mummSctnSrvyDtaVO);
		model.addAttribute("mummSctnSrvyDtaVO", result);

		return "/srvy/mumm/mummSctnSrvyDtaView3";
	}

	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다. (pageing)
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaTab3ListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectMummSctnSrvyDtaTab3ListPage(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mummSctnSrvyDtaVO.getPage());
		paginationInfo.setRecordCountPerPage(mummSctnSrvyDtaVO.getPageUnit());
		paginationInfo.setPageSize(mummSctnSrvyDtaVO.getRows());

		mummSctnSrvyDtaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mummSctnSrvyDtaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mummSctnSrvyDtaVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		List<MummSctnSrvyDtaVO> items = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaTab3ListPage(mummSctnSrvyDtaVO);
		int total_count = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaTab3ListPageTotalCount(mummSctnSrvyDtaVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) mummSctnSrvyDtaVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", mummSctnSrvyDtaVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	// ==========================================================================================================
	// //
	// 통합정보조회 - 조사자료 : 선택한 셀로 섹션 정보 조회
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return mummSctnSrvyDtaVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/mummSctnSrvyDtaSctnByCell.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MummSctnSrvyDtaVO mummSctnSrvyDtaSctnByCell(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		MummSctnSrvyDtaVO data = mummSctnSrvyDtaService.mummSctnSrvyDtaSctnByCell(mummSctnSrvyDtaVO);

		if (data != null && data != new MummSctnSrvyDtaVO()) {

			data.setRstFlag("1");
			return data;

		} else {

			MummSctnSrvyDtaVO tmp = new MummSctnSrvyDtaVO();
			tmp.setRstFlag("0");
			return tmp;

		}
	}

	// 통합정보조회 - 조사자료 : 선택한 섹션의 정보 조회
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return mummSctnSrvyDtaVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/mummSctnSrvyDtaSctnList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> mummSctnSrvyDtaSctnList(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// OBJECT_ID 리스트화 - IN 절에 넘길 파라미터 생성
		String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
		List<String> objArr = new ArrayList<String>();

		for (int i = 0; i < arr.length; i++) {

			objArr.add(arr[i]);

		}

		mummSctnSrvyDtaVO.setCELL_ID_ARR(objArr);

		// 데이터 조회
		List<MummSctnSrvyDtaVO> list = mummSctnSrvyDtaService
				.mummSctnSrvyDtaSctnList(mummSctnSrvyDtaVO);

		return list;
	}

	// 통합정보조회 - 조사자료 : 선택한 셀의 정보 조회
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return mummSctnSrvyDtaVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/mummSctnSrvyDtaCellList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellList(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// CELL_ID 리스트화 - IN 절에 넘길 파라미터 생성
		String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
		List<String> cellArr = new ArrayList<String>();
		List<MummSctnSrvyDtaVO> list = new ArrayList<MummSctnSrvyDtaVO>();

		try {

			for (int i = 0; i < arr.length; i++) {

				cellArr.add(arr[i]);

			}

			mummSctnSrvyDtaVO.setCELL_ID_ARR(cellArr);

			// 데이터 조회
			list = mummSctnSrvyDtaService
					.mummSctnSrvyDtaCellList(mummSctnSrvyDtaVO);

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (NullPointerException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// 통합정보조회 - 조사정보 : 섹션 리스트 페이지 조회
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/mummSctnSrvyDtaDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaSctnListView.do" })
	public String mummSctnSrvyDtaSctnListView(
			MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/srvy/mumm/mummSctnSrvyDtaSctnList";
	}

	// 통합정보조회 - 조사정보 : 이미지 및 로드뷰 페이지로 이동.
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/mummSctnSrvyDtaDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaImgRoadview.do" })
	public String mummSctnSrvyDtaImgRoadview(
			MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/srvy/mumm/mummSctnSrvyDtaImgRoadview";
	}

	// 통합정보조회 - 조사정보 : 조사정보조회 버튼 클릭 시 상세보기 페이지로 이동.
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/mummSctnSrvyDtaDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaDetail.do" })
	public String mummSctnSrvyDtaDetail(MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			SmDtaGnlSttusVO smDtaGnlSttusVO, ModelMap model) throws Exception {

		model.addAttribute("smDtaGnlSttusVO", smDtaGnlSttusService
				.selectSmDtaGnlSttusByCellId(smDtaGnlSttusVO));
		model.addAttribute("srvyYearList", smDtaGnlSttusService
				.selectSmDtaGnlSttusYearListByCellId(smDtaGnlSttusVO));

		return "/srvy/mumm/mummSctnSrvyDtaDetail";
	}

	// 통합정보조회 - 조사정보 : 포장상태 평가정보 평균 값 조회
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/mummSctnSrvyDtaDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaListBySrvyAvg.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MummSctnSrvyDtaVO selectMummSctnSrvyDtaListBySrvyAvg(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 셀 아이디 조회
		List<MummSctnSrvyDtaVO> dataList = mummSctnSrvyDtaService
				.mummSctnSrvyDtaCellInfo(mummSctnSrvyDtaVO);

		String cellIds = "";
		for (int i = 0; i < dataList.size(); i++) {

			if (i != 0) {

				cellIds += ",";

			}

			cellIds += dataList.get(i).getCELL_ID();

		}
		mummSctnSrvyDtaVO.setCELL_ID(cellIds);

		// 데이터 조회
		MummSctnSrvyDtaVO avg = mummSctnSrvyDtaService.prcClacPredctEvl(
				mummSctnSrvyDtaVO).get(0);

		// 코드명 받아오기
		CodeVO codeVO = new CodeVO();
		codeVO.setCODE_VAL(avg.getCNTL_DFECT());
		avg.setCODE_NM(codeService.selectCode(codeVO).getCODE_NM());

		return avg;
	}

	// 통합정보조회 - 조사정보 : 선택한 섹션의 셀 정보 조회.
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return mummSctnSrvyDtaVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/mummSctnSrvyDtaCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellInfo(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		List<MummSctnSrvyDtaVO> dataList = mummSctnSrvyDtaService
				.mummSctnSrvyDtaCellInfo(mummSctnSrvyDtaVO);

		return dataList;
	}

	// 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 및 평균값 조회.
	/**
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaListByItgrtn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectMummSctnSrvyDtaListByItgrtn(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mummSctnSrvyDtaVO.getPage());
		paginationInfo.setRecordCountPerPage(mummSctnSrvyDtaVO.getPageUnit());
		paginationInfo.setPageSize(mummSctnSrvyDtaVO.getRows());

		mummSctnSrvyDtaVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mummSctnSrvyDtaVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mummSctnSrvyDtaVO.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		MummSctnSrvyDtaVO avg = null;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			avg = mummSctnSrvyDtaService.prcClacPredctEvl(mummSctnSrvyDtaVO)
					.get(0);

			// 코드명 받아오기
			if (avg.getCNTL_DFECT() != null) {

				CodeVO codeVO = new CodeVO();
				codeVO.setCODE_VAL(avg.getCNTL_DFECT());
				avg.setCODE_NM(codeService.selectCode(codeVO).getCODE_NM());

			}

			// CELL_ID 리스트화 - IN 절에 넘길 파라미터 생성
			String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
			List<String> cellArr = new ArrayList<String>();

			for (int i = 0; i < arr.length; i++) {

				cellArr.add(arr[i]);

			}

			mummSctnSrvyDtaVO.setCELL_ID_ARR(cellArr);

			// 데이터 조회
			List<MummSctnSrvyDtaVO> items = mummSctnSrvyDtaService
					.selectMummSctnSrvyDtaListByItgrtn(mummSctnSrvyDtaVO);
			int total_count = mummSctnSrvyDtaService
					.selectMummSctnSrvyDtaListByItgrtnCnt(mummSctnSrvyDtaVO);

			int total_page = 0;
			if (total_count > 0)
				total_page = (int) Math.ceil((float) total_count
						/ (float) mummSctnSrvyDtaVO.getPageSize());

			map.put("page", mummSctnSrvyDtaVO.getPage());
			map.put("total", total_page);
			map.put("records", total_count);
			map.put("rows", items);
			map.put("userData", avg);

		} catch (Exception e) {

			// CELL_ID 리스트화 - IN 절에 넘길 파라미터 생성
			String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
			List<String> cellArr = new ArrayList<String>();

			for (int i = 0; i < arr.length; i++) {

				cellArr.add(arr[i]);

			}

			mummSctnSrvyDtaVO.setCELL_ID_ARR(cellArr);

			// 데이터 조회
			List<MummSctnSrvyDtaVO> items = mummSctnSrvyDtaService
					.selectMummSctnSrvyDtaListByItgrtn(mummSctnSrvyDtaVO);
			int total_count = mummSctnSrvyDtaService
					.selectMummSctnSrvyDtaListByItgrtnCnt(mummSctnSrvyDtaVO);

			int total_page = 0;
			if (total_count > 0)
				total_page = (int) Math.ceil((float) total_count
						/ (float) mummSctnSrvyDtaVO.getPageSize());

			map.put("page", mummSctnSrvyDtaVO.getPage());
			map.put("total", total_page);
			map.put("records", total_count);
			map.put("rows", items);
			map.put("userData", avg);

		}

		return map;
	}

	// 통합정보조회 - 상태평가 : '더보기' 시 페이지 이동.
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다. (pageing)
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaList.do" })
	public String selectMummSctnSrvyDtaList(
			MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("screen_title", "조사자료관리");

		HashMap paraMap = new HashMap();
		// 관할기관조회추가예정
		// model.addAttribute("bs_code_list", cmmservice.selectBscode(paraMap));
		// 노선명조회추가예정
		model.addAttribute("rout_code_list",
				cmmnService.selectRouteInfo(paraMap));
		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/srvy/mumm/mummSctnSrvyDtaList";
	}

	// 통합정보조회 - 상태평가 : '더보기' 시 포장상태 평가정보 평균 값 조회
	/**
	 * 통합정보조회 - 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 상세정보를 조회한다. (pageing)
	 *
	 * @param mummSctnSrvyDtaVO
	 *            - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return mummSctnSrvyDtaVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaListByItgrtnAvg.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MummSctnSrvyDtaVO selectMummSctnSrvyDtaListByItgrtnAvg(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		MummSctnSrvyDtaVO avg = mummSctnSrvyDtaService.prcClacPredctEvl(
				mummSctnSrvyDtaVO).get(0);

		// 코드명 받아오기
		CodeVO codeVO = new CodeVO();
		codeVO.setCODE_VAL(avg.getCNTL_DFECT());
		avg.setCODE_NM(codeService.selectCode(codeVO).getCODE_NM());

		return avg;
	}

	// 통합정보조회 - 상태평가 : '더보기' 시 공사정보 목록 조회
	/**
	 * 선택한 셀에 대한 공사정보(TN_CNTRWK) 목록을 조회한다. (pageing)
	 *
	 * @param cntrwkVO
	 *            - 조회할 정보가 담긴 CntrwkVO
	 * @return "/cntrwk/CntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwk/selectCntrwkListByCell.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCntrwkListByCell(
			@RequestBody CntrwkVO cntrwkVO, ModelMap model, HttpSession session)
			throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkVO.getPage());
		paginationInfo.setRecordCountPerPage(cntrwkVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkVO.getRows());

		cntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// objectid로 조회하는 경우
		if (cntrwkVO.getOBJECT_ID() != null && cntrwkVO.getOBJECT_ID() != "") {

			// 셀 아이디 조회
			MummSctnSrvyDtaVO mummSctnSrvyDtaVO = new MummSctnSrvyDtaVO();
			mummSctnSrvyDtaVO.setOBJECT_ID(cntrwkVO.getOBJECT_ID());
			List<MummSctnSrvyDtaVO> dataList = mummSctnSrvyDtaService
					.mummSctnSrvyDtaCellInfo(mummSctnSrvyDtaVO);

			String cellIds = "";
			for (int i = 0; i < dataList.size(); i++) {

				if (i != 0) {

					cellIds += ",";

				}

				cellIds += dataList.get(i).getCELL_ID();

			}

			cntrwkVO.setCELL_ID(cellIds);

		}

		String[] arr = cntrwkVO.getCELL_ID().split(",");
		List<String> cellArr = new ArrayList<String>();

		for (int i = 0; i < arr.length; i++) {

			cellArr.add(arr[i]);

		}

		cntrwkVO.setCELL_ID_ARR(cellArr);

		List<CntrwkVO> items = cntrwkService.selectCntrwkListByItgrtn(cntrwkVO);
		int total_count = cntrwkService.selectCntrwkListByItgrtnCnt(cntrwkVO);

		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count
					/ (float) cntrwkVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cntrwkVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 조사정보조회 - 조사정보 이미지 페이지를 조회한다.
	 *
	 * @author : JOY
	 * @date : 2017. 10. 25.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/srvy/mumm/mummSctnSrvyDtaDetailIMG";
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaDetailIMG.do" })
	public String mummSctnSrvyDtaDetailIMG(MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model) throws Exception {
		model.addAttribute("mummSctnSrvyDtaVO",
				mummSctnSrvyDtaService.selectMummSctnSrvyDta(mummSctnSrvyDtaVO));

		return "/srvy/mumm/mummSctnSrvyDtaDetailIMG";
	}

	/**
	 * 조사정보조회 - 조사정보 이미지 페이지를 조회한다.
	 *
	 * @author : JOY
	 * @date : 2017. 10. 25.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/srvy/mumm/mummSctnSrvyDtaDetailRV";
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaDetailRV.do" })
	public String mummSctnSrvyDtaDetailRV(MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model) throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/srvy/mumm/mummSctnSrvyDtaDetailRV";
	}

	// 통계

	/**
	 * 통계 > 포장상태 평가 > 노선별통계 페이지 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 16.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/stats/mumm/mummRoutCntStats"
	 * @exception : Exception
	 */
	@RequestMapping(value = "/mumm/mummRoutCntStats.do")
	public String selectRoutCntStats(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/stats/mumm/mummRoutCntStats";
	}

	/**
	 * 통계 > 포장상태 평가 > 노선별통계 > 데이터조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 17.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : List<MummSctnSrvyDtaVO>
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/mumm/mummRoutCntStatsGPCI.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> mummRoutCntStatsGPCI(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		List<MummSctnSrvyDtaVO> result = mummSctnSrvyDtaService
				.mummRoutCntStatsGPCI(mummSctnSrvyDtaVO);

		return result;
	}

	/**
	 * 통계 > 포장상태 평가 > 노선별통계 > 데이터조회 > 차트페이지 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 28.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/stats/mumm/mummRoutCntStatsChart"
	 * @exception : Exception
	 */
	@RequestMapping(value = "/mumm/mummRoutCntStatsChart.do")
	public String selectRoutCntStatsChart(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/stats/mumm/mummRoutCntStatsChart";
	}

	/**
	 * 통계 > 포장상태 평가 > 노선별통계 > 엑셀
	 *
	 * @author : JOY
	 * @date : 2017. 11. 20.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : View
	 * @exception : Exception
	 */
	@RequestMapping(value = "/api/mumm/mummRoutCntStatsExcel.do")
	public View mummRoutCntStatsExcel(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model, HttpServletRequest request, HttpSession session)
			throws Exception {
		List dataList = mummSctnSrvyDtaService
				.mummRoutCntStatsExcel(mummSctnSrvyDtaVO);

		String[] excel_title = { "노선번호", "노선명", "GPCI", "교통량/하부불량", "기후", "기타" };
		String[] excel_column = { "route_code", "road_nm", "gpci",
				"dmg_cuz_vmtc", "dmg_cuz_clmt", "dmg_cuz_etc" };

		model.addAttribute("file_name",
				"포장상태평가_노선별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 통계 > 포장상태 평가 > 관리기관별통계 페이지 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 16.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/stats/mumm/mummDeptCntStats"
	 * @exception : Exception
	 */
	@RequestMapping(value = "/mumm/mummDeptCntStats.do")
	public String selectDeptCntStats(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/stats/mumm/mummDeptCntStats";
	}

	/**
	 * 통계 > 포장상태 평가 > 관리기관별통계 > 데이터조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 20.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : List<MummSctnSrvyDtaVO>
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/mumm/mummDeptCntStats.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<MummSctnSrvyDtaVO> mummDeptCntStats(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		List<MummSctnSrvyDtaVO> result = mummSctnSrvyDtaService
				.mummDeptCntStats(mummSctnSrvyDtaVO);

		return result;
	}

	/**
	 * 통계 > 포장상태 평가 > 관리기관별통계 > 데이터조회 > 차트페이지 조회
	 *
	 * @author : JOY
	 * @date : 2017. 11. 28.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : "/stats/mumm/mummRoutCntStatsChart"
	 * @exception : Exception
	 */
	@RequestMapping(value = "/mumm/mummDeptCntStatsChart.do")
	public String selectDeptCntStatsChart(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model)
			throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);

		return "/stats/mumm/mummDeptCntStatsChart";
	}

	/**
	 * 통계 > 포장상태 평가 > 관리기관별통계 > 엑셀
	 *
	 * @author : JOY
	 * @date : 2017. 11. 20.
	 *
	 * @param : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return : View
	 * @exception : Exception
	 */
	@RequestMapping(value = "/api/mumm/mummDeptCntStatsExcel.do")
	public View mummDeptCntStatsExcel(
			@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model, HttpServletRequest request, HttpSession session)
			throws Exception {
		List dataList = mummSctnSrvyDtaService
				.mummDeptCntStatsExcel(mummSctnSrvyDtaVO);

		String[] excel_title = { "관리부서코드", "관리부서명", "GPCI", "교통량/하부불량", "기후",
				"기타" };
		String[] excel_column = { "dept_code", "dept_nm", "gpci",
				"dmg_cuz_vmtc", "dmg_cuz_clmt", "dmg_cuz_etc" };

		model.addAttribute("file_name",
				"포장상태평가_관리기관별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	@RequestMapping(value="/api/mumm/integratedListExcel.do")
    public View integratedListExcel(@ModelAttribute MummSctnSrvyDtaVO mummSctnSrvyDtaVO,  ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {

        String[] arr = mummSctnSrvyDtaVO.getCELL_ID().split(",");
        List<String> objArr = new ArrayList<String>();

        for ( int i = 0; i < arr.length; i++ ) {

            objArr.add(arr[i]);

        }

        mummSctnSrvyDtaVO.setCELL_ID_ARR(objArr);

        List dataList = mummSctnSrvyDtaService.integratedListExcel(mummSctnSrvyDtaVO);

        String[] excel_title  = {"도로등급", "노선번호", "노선명", "방향", "차로", "시점(km)", "종점(km)","거불등 균열","블럭 균열","종방향 균열"
        		,"횡방향 균열(m)","패칭(㎡)","포트홀(㎡)", "소성변형값","종탄평탄성값","거북등균열지수","블럭균열지수","종방향균열지수","횡방향균열지수","패칭지수",
        		"포트홍지수","소성변형값","표면조도지수","표면상태지수","GPCI","지배결함","주파손","조사년도","조사월","조사번호",
        		"조사명" , "당해년도"      , "예측1년" , "예측2년"  , "예측3년" , "예측4년" , "예측5년" , "예측6년"  , "예측7년" , "예측8년"
        		, "예측9년"    , "예측10년"
        		};
        String[] excel_column = {"road_grad", "route_code", "road_name", "direct_code", "track", "strtpt", "endpt","trts_bac_cr","block_cr","vrtcal_cr",
        		"hrzntal_cr","ptchg_cr","pothole_cr","rd_val","iri_val","ac_idx","bc_idx","lc_idx","tc_idx","ptchg_idx"
        		,"pothole_idx","rd_idx","rci","scr","gpci","cntl_dfect","year_mt","srvy_year","srvy_mt"
        		,"srvy_no", "srvy_nm","predct0","predct1","predct2","predct3","predct4","predct5","predct6","predct7","predct8","predct9","predct10"  //42
        	};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "통합정보_포장상태평가조회_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통합정보조회 - '더보기'페이지에서 셀 개별 평가정보 조회 페이지 이동.
	 *
	 * @param mummSctnSrvyDtaVO
	 * @param model
	 * @return "/srvy/mumm/mummSctnSrvyDtaCell"
	 * @throws Exception
	 */
	@RequestMapping(value = { "/mng/mummsctnsrvydta/mummSctnSrvyDtaCell.do" })
	public String mummSctnSrvyDtaCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO,
			ModelMap model) throws Exception {

		model.addAttribute("mummSctnSrvyDtaVO", mummSctnSrvyDtaVO);
		return "/srvy/mumm/mummSctnSrvyDtaCell";
	}

	@RequestMapping(value = { "/api/mummsctnsrvydta/selectMummSctnSrvyDtaByCell.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MummSctnSrvyDtaVO selectMummSctnSrvyDtaByCell(
			@RequestBody MummSctnSrvyDtaVO mummSctnSrvyDtaVO, ModelMap model,
			HttpSession session) throws Exception {

		// 데이터 조회
		MummSctnSrvyDtaVO result = mummSctnSrvyDtaService
				.selectMummSctnSrvyDtaByCell(mummSctnSrvyDtaVO);
		return result;

	}

}
