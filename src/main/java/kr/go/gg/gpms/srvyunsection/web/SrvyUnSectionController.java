package kr.go.gg.gpms.srvyunsection.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.srvyunsection.service.SrvyUnSectionService;
import kr.go.gg.gpms.srvyunsection.service.model.SrvyUnSectionVO;


@Controller
public class SrvyUnSectionController {

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;
	
	@Resource(name = "srvyUnSectionService")
	private SrvyUnSectionService srvyUnSectionService;

	@Resource(name = "propertiesService")	
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyUnSectionController.class);

	
	/**
	 * 리스트 화면 이동 
	 * */
	@RequestMapping(value = "/srvy/srvyunsectionList.do")
	public String srvyDtaList(SrvyUnSectionVO srvyUnSectionVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		
		//노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
		
		//조사연도 리스트
		List<SrvyUnSectionVO> srvyYearList = (List<SrvyUnSectionVO>) srvyUnSectionService.srvyYearList(srvyUnSectionVO);
		
		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("srvyYearList", srvyYearList);
		
		return "/srvy/unsection/srvyUnSectionList" ;
	}

	
	/**
	 * 차트 화면 이동 
	 * */
	@RequestMapping(value = { "/srvy/selectSrvyUnSectionChart.do" })
	public Object selectSrvyUnSectionChart(SrvyUnSectionVO srvyUnSectionVO, HttpServletRequest request, ModelMap model) throws Exception {
		model.addAttribute("srvyUnSectionVO", srvyUnSectionVO);
		return "/srvy/unsection/srvyUnSectionChart";
	}

	
	/**
	 * 리스트 조회 
	 * */
	@ResponseBody
	@RequestMapping(value = { "/api/srvyunsection/selectSrvyUnSectionList.do" })
	public Object selectSrvyDtaSttusList(@RequestBody SrvyUnSectionVO srvyUnSectionVO, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyUnSectionVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyUnSectionVO.getPageUnit());
		paginationInfo.setPageSize(srvyUnSectionVO.getRows());
		srvyUnSectionVO.setUsePage(srvyUnSectionVO.isUsePage());
		
		srvyUnSectionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyUnSectionVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyUnSectionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SrvyUnSectionVO> items = (List<SrvyUnSectionVO>) srvyUnSectionService.list(srvyUnSectionVO);
		model.addAttribute("items", items);
		
		int total_count = 0;
		if(items != null && items.size() > 0) {
			total_count = items.get(0).getTOTAL_COUNT();
		}
		
		int total_page = 0;
		if(total_count > 0){
			total_page = (int) Math.ceil((float) total_count / (float) srvyUnSectionVO.getPageSize());
		}	

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", srvyUnSectionVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
		map.put("search", srvyUnSectionVO);

		return map;
	}
	

	/**
	 * 조사구간 조회 
	 * */
	@RequestMapping(value = { "/api/srvyunsection/sectionlocation.do" })
	public String sectionlocation(@RequestBody SrvyUnSectionVO srvyUnSectionVO, ModelMap model) throws Exception {
		
		String routeCode = srvyUnSectionVO.getROUTE_CODE();
		if(routeCode == null || "".equals(routeCode.trim())) {
			model.addAttribute("succ", false);	
			model.addAttribute("msg", "requierd RouteCode");	
			return "jsonView";
		}
		
		String srvyYear = srvyUnSectionVO.getSRVY_YEAR();
		if(srvyYear == null || "".equals(srvyYear.trim())) {
			model.addAttribute("succ", false);
			model.addAttribute("msg", "requierd SrvyYear");	
			return "jsonView";
		}
		
		SrvyUnSectionVO item = (SrvyUnSectionVO) srvyUnSectionService.sectionlocation(srvyUnSectionVO);
		model.addAttribute("succ", true);	
		model.addAttribute("item", item);
		return "jsonView";
	}

	
	/**
	 * 미조사구간 조회 
	 * */
	@RequestMapping(value = { "/api/srvyunsection/unsectionlocation.do" })
	public String unsectionlocation(@RequestBody SrvyUnSectionVO srvyUnSectionVO, ModelMap model) throws Exception {
		
		String routeCode = srvyUnSectionVO.getROUTE_CODE();
		if(routeCode == null || "".equals(routeCode.trim())) {
			model.addAttribute("succ", false);	
			model.addAttribute("msg", "requierd RouteCode");	
			return "jsonView";
		}
		
		String srvyYear = srvyUnSectionVO.getSRVY_YEAR();
		if(srvyYear == null || "".equals(srvyYear.trim())) {
			model.addAttribute("succ", false);
			model.addAttribute("msg", "requierd SrvyYear");	
			return "jsonView";
		}
		
		SrvyUnSectionVO item = (SrvyUnSectionVO) srvyUnSectionService.unsectionlocation(srvyUnSectionVO);
		model.addAttribute("succ", true);	
		model.addAttribute("item", item);
		return "jsonView";
	}
	
}
