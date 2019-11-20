


package kr.go.gg.gpms.routeinfo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
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

import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : RouteInfoController.java
 * @Description : RouteInfo Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("routeInfoController")
public class RouteInfoController extends BaseController {

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteInfoController.class);

	/**
	 * 노선_대장_마스터(TN_ROUTE_INFO) 목록을 조회한다. (pageing)
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return "/routeinfo/RouteInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/routeinfo/selectRouteInfoList.do" })
	public String selectRouteInfoList(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		//도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");
		
		//노선 번호
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		// 노선조회로 직접 접근한건지 아닌지 확인
		String directFlag = "";
		if ( routeInfoVO.getDIRECT_FLAG() != null && !routeInfoVO.getDIRECT_FLAG().equals("") ) {
		    
		    directFlag = "N";
		}
		
		if(routeInfoVO.getROAD_NO() != null){
			routeInfoVO = routeInfoService.selectRouteInfo(routeInfoVO);
		}
		
		// 직접접근 Flag Setting
		routeInfoVO.setDIRECT_FLAG(directFlag);
				
		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("routeInfoVO", routeInfoVO);
		
		return "/routeinfo/routeinfoList" ;
	}
	
	/**
	 * 노선_대장_마스터(TN_ROUTE_INFO) 목록을 조회한다. (pageing)
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return "/routeinfo/RouteInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/routeinfo/selectRouteInfoList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectRouteInfoListRest(@RequestBody RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(routeInfoVO.getPage());
		paginationInfo.setRecordCountPerPage(routeInfoVO.getPageUnit());
		paginationInfo.setPageSize(routeInfoVO.getRows());
		routeInfoVO.setUsePage(true);
		
		routeInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		routeInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		routeInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<RouteInfoVO> items = routeInfoService.selectRouteInfoList(routeInfoVO);
		int total_count = routeInfoService.selectRouteInfoListTotalCount(routeInfoVO);
		
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) routeInfoVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", routeInfoVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
		map.put("search", routeInfoVO);

		return map;
	}
	
	@RequestMapping(value = { "/routeinfo/routeInfoView.do" })
	public String routeInfoView(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, ModelMap model, HttpSession session) throws Exception {
		String result = "";
		
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		RouteInfoVO routeinfoVO = routeInfoService.RouteInfoView(routeInfoVO);
		model.addAttribute("routeInfoVO", routeinfoVO);
		
		String user = SessionManager.getCurrentUser().getREQ_USER_GRP();
		if(user.equals("ROLE_ADMIN")){
			 result =  "/routeinfo/routeinfoUpdate";
		 }else {
			 result = "/routeinfo/routeinfoView";
		 }
		 
		 return result;
	}
	
	@RequestMapping(value = { "/routeinfo/updateRouteInfo.do" })
	public String updateRouteInfoView(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, ModelMap model,BindingResult bindingResult,HttpServletRequest request, SessionStatus status, HttpSession session) throws Exception {
		Map<String, String> req = requestToHashMap(request);
		String action_flag = StringUtils.isNotEmpty(req.get("action_flag"))?
				req.get("action_flag").trim():"UPDATE" ;
		// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";
		String callbackFunction = StringUtils.isNotEmpty(routeInfoVO.getCallBackFunction()) 
									? routeInfoVO.getCallBackFunction().trim() : "";
									
		try {
			resultCode = "UPDATE_SUCCESS";
			resultMsg = "정상 수정되었습니다.";
			BindBeansToActiveUser(routeInfoVO);
			routeInfoService.updateRouteInfoView(routeInfoVO);
			routeInfoVO.setResultSuccess("true");
			routeInfoVO.setResultMSG(resultMsg);
	    	status.setComplete();	//Double Submit 방지
		} catch (Exception e) {
			resultCode = "ERROR";
    		resultMsg = "노선정보 수정 오류";
    		LOGGER.error("노선정보 수정 오류", e);    		
		}

		// 결과 처리용 [수정X]
    	model.addAttribute("resultCode", resultCode);
    	model.addAttribute("resultMsg", resultMsg);
    	model.addAttribute("callBackFunction",  callbackFunction);	// 처리후 호출 함수    	
		return "/cmmn/commonMsg";
	}
	
	/**
	 * 지정된 범위의 셀로 노선_대장_마스터(TN_ROUTE_INFO) 목록을 조회한다. (pageing)
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return "/routeinfo/RouteInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/routeinfo/selectRouteInfoListRange.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectRouteInfoListRestRange(@RequestBody RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(routeInfoVO.getPage());
		paginationInfo.setRecordCountPerPage(routeInfoVO.getPageUnit());
		paginationInfo.setPageSize(routeInfoVO.getRows());
		routeInfoVO.setUsePage(true);
		
		routeInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		routeInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		routeInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<RouteInfoVO> items = routeInfoService.selectRouteInfoList(routeInfoVO);
		int total_count = routeInfoService.selectRouteInfoListTotalCount(routeInfoVO);
		
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) routeInfoVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", routeInfoVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	@RequestMapping(value = { "/api/routeinfo/selectRouteInfoListByGrad.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RouteInfoVO> selectRouteInfoListByGradRest(@RequestBody RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> items = routeInfoService.selectRouteInfoList(routeInfoVO);
		
		return items;
	}
	

	/**
	 * 노선_대장_마스터(TN_ROUTE_INFO) 상세를 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return "/routeinfo/RouteInfoView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/routeinfo/selectRouteInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RouteInfoVO selectRouteInfoRest(@RequestBody RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
	
		routeInfoVO =  routeInfoService.selectRouteInfo(routeInfoVO);
		
		return routeInfoVO;
	}
	
	/**
	 * 노선_대장_마스터(TN_ROUTE_INFO) 상세를 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return "/routeinfo/RouteInfoView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/routeinfo/selectRouteInfo.do" })
	public String selectRouteInfo(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, ModelMap model) throws Exception {
	
		model.addAttribute("routeInfoVO", routeInfoService.selectRouteInfo(routeInfoVO));
		return "/routeinfo/routeinfoView";
	}


	@RequestMapping(value = { "/routeinfo/addRouteInfoView.do" })
	public String addRouteInfoView(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, Model model) throws Exception {
		model.addAttribute("routeInfoVO", new RouteInfoVO());
		return "/routeinfo/routeinfoRegist";
	}

	@RequestMapping(value = { "/api/routeinfo/addRouteInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RouteInfoVO addRouteInfo(@RequestBody RouteInfoVO routeInfoVO) throws Exception {
		BindBeansToActiveUser(routeInfoVO);
		routeInfoService.insertRouteInfo(routeInfoVO);
		routeInfoVO.setResultSuccess("true");
		routeInfoVO.setResultMSG("정상 등록되었습니다.");
		return routeInfoVO;
	}
	
	@RequestMapping(value = { "/routeinfo/updateRouteInfoView.do" })
	public String updateRouteInfoView(@ModelAttribute("searchVO") RouteInfoVO routeInfoVO, Model model) throws Exception {
		model.addAttribute("routeInfoVO", routeInfoService.selectRouteInfo(routeInfoVO));
		return "/routeinfo/routeinfoUpdate";
	}

	@RequestMapping(value = { "/api/routeinfo/updateRouteInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RouteInfoVO updateRouteInfo(@RequestBody RouteInfoVO routeInfoVO) throws Exception {
		BindBeansToActiveUser(routeInfoVO);
		routeInfoService.updateRouteInfo(routeInfoVO);
		routeInfoVO.setResultSuccess("true");
		routeInfoVO.setResultMSG("정상 수정되었습니다.");
		return routeInfoVO;
	}

	@RequestMapping(value = { "/api/routeinfo/deleteRouteInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RouteInfoVO deleteRouteInfo(@RequestBody RouteInfoVO routeInfoVO) throws Exception {
		BindBeansToActiveUser(routeInfoVO);
		routeInfoService.deleteRouteInfo(routeInfoVO);
		routeInfoVO.setResultSuccess("true");
		routeInfoVO.setResultMSG("정상 삭제되었습니다.");
		return routeInfoVO;
	}
	
}
