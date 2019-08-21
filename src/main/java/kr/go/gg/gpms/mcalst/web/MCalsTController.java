


package kr.go.gg.gpms.mcalst.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.mcalst.service.MCalsTService;
import kr.go.gg.gpms.mcalst.service.model.MCalsTVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : Cell10Controller.java
 * @Description : Cell10 Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("mcalstcontroller")
public class MCalsTController extends BaseController {

	@Resource(name = "mcalstService")
	private MCalsTService mcalstService;
	
	@Resource(name = "cmmnService")
    private CmmnService cmmnService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MCalsTController.class);

	/**
	 * M_CALS_T(M_CALS_T) 목록을 조회한다. (pageing)
	 * @param MCalsTVO - 조회할 정보가 담긴 MCalsTVO
	 * @return "/mcalst/selectMCalsTList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mcalst/selectMCalsTList.do" })
	public String selectMCalsTList(@ModelAttribute("searchVO") MCalsTVO mcalstVO,  ModelMap model) throws Exception {
		
		return "/mcalst/selectMCalsTList" ;
	}
	
	/**
	 * M_CALS_T(M_CALS_T) 목록을 조회한다. (pageing)
	 * @param MCalsTVO - 조회할 정보가 담긴 MCalsTVO
	 * @return "/mcalst/selectMCalsTList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/selectMCalsTList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectMCalsTList(
			@RequestBody MCalsTVO mcalstVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mcalstVO.getPage());
		paginationInfo.setRecordCountPerPage(mcalstVO.getPageUnit());
		paginationInfo.setPageSize(mcalstVO.getRows());
		mcalstVO.setUsePage(true);

		mcalstVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mcalstVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mcalstVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<MCalsTVO> items = mcalstService.selectMCalsTList(mcalstVO);
		int totCnt = mcalstService.selectMCalsTListTotalCount(mcalstVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt
					/ (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", mcalstVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}
	
	//180719 wijy
    //통합정보조회 > 포장상태평가 > 다중선택 > 직접입력
    //노선목록조회
    @RequestMapping(value="/api/ms/selRouteName.do")
    public @ResponseBody Map<String, Object> selRouteName() throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        
        retMap.put("routeNm", cmmnService.selectRouteInfo(null));
        return retMap;
    }
    
    //노선방향조회
    @RequestMapping(value="/api/ms/selRouteDirection.do")
    public @ResponseBody Map<String, Object> selRouteDirection(@RequestParam HashMap<String, Object> paramMap) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        
        retMap.put("routeDir", cmmnService.selectRln(paramMap));
        return retMap;
    }
    
    //노선차로조회
    @RequestMapping(value="/api/ms/selRouteTrack.do")
    public @ResponseBody Map<String, Object> selRouteTrack(@RequestParam HashMap<String, Object> paramMap) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        
        retMap.put("track", cmmnService.selectTrackFrom20(paramMap));
        return retMap;
    }
    
    
	
}
