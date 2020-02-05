

package kr.go.gg.gpms.srvydtaexcel.web;




import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.srvy.service.SrvyDtaService;
import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;
import kr.go.gg.gpms.srvydtaexcel.service.SrvyDtaExcelService;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;



/**
 * @Class Name : SrvyDtaExcelController.java
 * @Description : SrvyDtaExcel Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("srvyDtaExcelController")
public class SrvyDtaExcelController extends BaseController {

	@Resource(name = "srvyDtaExcelService")
	private SrvyDtaExcelService srvyDtaExcelService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyDtaExcelController.class);

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다. (pageing)
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvydtaexcel/selectSrvyDtaExcelList.do" })
	public String selectSrvyDtaExcelList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model) throws Exception {

		// 노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		if (routeInfoVO.getROAD_NO() != null) {

			routeInfoVO = routeInfoService.selectRouteInfo(routeInfoVO);

		}

		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("routeInfoVO", routeInfoVO);

		return "/srvy/excel/srvyDtaExcelList" ;
	}

	@RequestMapping(value = { "/srvydtaexcel/selectSrvyDtaExcelCompList.do" })
	public String selectSrvyDtaExcelCompList(@ModelAttribute SrvyDtaExcelVO srvyDtaExcelVO, HttpServletRequest request, ModelMap model) throws Exception {
		Map<String, String> req = requestToHashMap(request);

		model.addAttribute("srvyDtaExcelVO", srvyDtaExcelVO);
		model.addAttribute("cnt", req.get("cnt"));
		return "/srvy/excel/srvyDtaExcelCompList" ;
	}

	@RequestMapping(value = { "/srvydtaexcel/selectSrvyDtaExcelFailList.do" })
	public String selectSrvyDtaExcelFailList(@ModelAttribute SrvyDtaVO srvyDtaVO, HttpServletRequest request, ModelMap model) throws Exception {

		model.addAttribute("srvyDtaVO", srvyDtaVO);
		return "/srvy/excel/srvyDtaExcelFailList" ;
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다. (pageing)
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtaexcel/selectSrvyDtaExcelList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SrvyDtaExcelVO> selectSrvyDtaExcelListRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaExcelVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaExcelVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getPageSize());

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaExcelList(srvyDtaExcelVO);
		return items;
	}


	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다. (pageing)
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvydtaexcel/selectSrvyDtaExcelListPage.do" })
	public String selectSrvyDtaExcelListPage(SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model) throws Exception {
		srvyDtaExcelVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaExcelVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getPageSize());

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaExcelList(srvyDtaExcelVO);
		model.addAttribute("items", items);

		int totCnt = srvyDtaExcelService.selectSrvyDtaExcelListTotalCount(srvyDtaExcelVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/srvy/excel/srvyDtaExcelList" ;
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다. (pageing)
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/srvydtaexcel/selectSrvyDtaExcelListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SrvyDtaExcelVO>  selectSrvyDtaExcelListPageRest(@RequestBody  SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpSession session) throws Exception {
		srvyDtaExcelVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyDtaExcelVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyDtaExcelVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyDtaExcelVO.getPageUnit());
		paginationInfo.setPageSize(srvyDtaExcelVO.getPageSize());

		srvyDtaExcelVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyDtaExcelVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyDtaExcelVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyDtaExcelVO> items = srvyDtaExcelService.selectSrvyDtaExcelList(srvyDtaExcelVO);
		return items;
	}



	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 상세를 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvydtaexcel/selectSrvyDtaExcel.do"  })
	public String selectSrvyDtaExcel(@ModelAttribute("searchVO") SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model) throws Exception {

		model.addAttribute("srvyDtaExcelVO", srvyDtaExcelService.selectSrvyDtaExcel(srvyDtaExcelVO));
		return "/srvy/excel/srvyDtaExcelView";
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 상세를 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvydtaexcel/selectSrvyDtaExcel.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaExcelVO selectSrvyDtaExcelRest(@RequestBody  SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model, HttpSession session) throws Exception {
		SrvyDtaExcelVO srvyDtaExcelVOOne = srvyDtaExcelService.selectSrvyDtaExcel(srvyDtaExcelVO);
		return srvyDtaExcelVOOne;
	}



	@RequestMapping(value = { "/srvydtaexcel/addSrvyDtaExcelView.do" })
	public String addSrvyDtaExcelView(@ModelAttribute("searchVO") SrvyDtaExcelVO srvyDtaExcelVO, Model model) throws Exception {
		model.addAttribute("srvyDtaExcelVO", new SrvyDtaExcelVO());
		return "/srvy/excel/srvyDtaExcelRegist";
	}

	@RequestMapping(value = { "/srvydtaexcel/addSrvyDtaExcel.do"  })
	public String addSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		srvyDtaExcelService.insertSrvyDtaExcel(srvyDtaExcelVO);
		return "redirect:/srvy/excel/selectSrvyDtaExcelList.do";
	}

	@RequestMapping(value = {  "/api/srvydtaexcel/addSrvyDtaExcel.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaExcelVO addSrvyDtaExcelRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, HttpSession session) throws Exception {
		srvyDtaExcelService.insertSrvyDtaExcel(srvyDtaExcelVO);
		return srvyDtaExcelVO;
	}


	@RequestMapping(value = { "/srvydtaexcel/updateSrvyDtaExcelView.do" })
	public String updateSrvyDtaExcelView(@ModelAttribute("searchVO") SrvyDtaExcelVO srvyDtaExcelVO, Model model) throws Exception {
		model.addAttribute("srvyDtaExcelVO", srvyDtaExcelService.selectSrvyDtaExcel(srvyDtaExcelVO));
		return "/srvy/excel/srvyDtaExcelUpdate";
	}

	@RequestMapping(value = { "/srvydtaexcel/updateSrvyDtaExcel.do" })
	public String updateSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		srvyDtaExcelService.updateSrvyDtaExcel(srvyDtaExcelVO);
		return "redirect:/srvy/excel/selectSrvyDtaExcelList.do";
	}

	@RequestMapping(value = {  "/api/srvydtaexcel/updateSrvyDtaExcel.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaExcelVO updateSrvyDtaExcelRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, HttpSession session) throws Exception {
		srvyDtaExcelService.updateSrvyDtaExcel(srvyDtaExcelVO);
		return srvyDtaExcelVO;
	}

	@RequestMapping(value = { "/srvydtaexcel/deleteSrvyDtaExcel.do" })
	public String deleteSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		srvyDtaExcelService.deleteSrvyDtaExcel(srvyDtaExcelVO);
		return "redirect:/srvy/excel/selectSrvyDtaExcelList.do";
	}

	@RequestMapping(value = {   "/api/srvydtaexcel/deleteSrvyDtaExcel.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyDtaExcelVO deleteSrvyDtaExcelRest(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, HttpSession session) throws Exception {
		srvyDtaExcelService.deleteSrvyDtaExcel(srvyDtaExcelVO);
		return srvyDtaExcelVO;
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다. (paging)
	 *
	 * @param SrvyDtaExcelVO
	 *            - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/api/srvyDtaExcelList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyDtaExcelList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> srvyDtaExcelList(
			@RequestBody SrvyDtaExcelVO srvyDtaExcelVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {

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
				.srvyDtaExcelList(srvyDtaExcelVO);
		int totCnt = srvyDtaExcelService.srvyDtaExcelListCount(srvyDtaExcelVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt
					/ (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", srvyDtaExcelVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 등록일자를 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/srvy/excel/SrvyDtaExcelView"
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/srvy/searchCreatDt.do" })
    public @ResponseBody Map<String, Object> searchCreatDt(@RequestBody SrvyDtaExcelVO srvyDtaExcelVO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", srvyDtaExcelService.searchCreatDt(srvyDtaExcelVO));

		return map;
	}

	@RequestMapping(value = "/analDataPopupList.do")
	public String analDataPopupList(@ModelAttribute SrvyDtaVO srvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("analDataPopupList.do start!: " + srvyDtaVO.toString());


		model.addAttribute("srvyDtaVO", srvyDtaVO);
		return "/srvy/excel/analDataPopupList" ;
	}

	@RequestMapping(value = { "/srvy/excel/srvyDtaExcelUploadList.do" })
	public String selectsrvyDtaExcelUploadList(@ModelAttribute SrvyDtaVO srvyDtaVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		File path = new File("D:/wasfs/PMS_UploadFile/srvy_org");
		String files[] = path.list();

		if(files.length > 0){
		    for(int i=0; i < files.length; i++){
		  		System.out.println(files[i]) ;
		    }
		}

		model.addAttribute("files", files);

		return "/srvy/excel/srvyDtaExcelUploadList" ;
	}



}
