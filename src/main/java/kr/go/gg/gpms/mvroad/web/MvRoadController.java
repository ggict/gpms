


package kr.go.gg.gpms.mvroad.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.mvroad.service.MvRoadService;
import kr.go.gg.gpms.mvroad.service.model.MvRoadVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;

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

@Controller("mvRoadController")
public class MvRoadController extends BaseController {

	@Resource(name = "mvRoadService")
	private MvRoadService mvroadService;

    @Resource(name = "routeInfoService")
    private RouteInfoService routeInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MvRoadController.class);

	/**
	 * 특별관리구간 상세정보를 조회한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/MvRoadPop.do" })
	public String MvRoadPop(@ModelAttribute MvRoadVO mvroadVO,  ModelMap model, HttpServletRequest request) throws Exception {

		return "/mvroad/mvRoadPop" ;
	}

	/**
	 * 특별관리구간 상세정보를 조회한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/selectMvRoadPop.do" })
	public String selectMvRoadPop(@ModelAttribute MvRoadVO mvroadVO,  ModelMap model, HttpServletRequest request) throws Exception {

		String spcl_no = request.getParameter("spcl_no");
		mvroadVO.setSPCL_NO(spcl_no);

		MvRoadVO result = mvroadService.selectMvRoad(mvroadVO);

		if(result == null){
		    RouteInfoVO routeInfoVO = new RouteInfoVO();
	        routeInfoVO.setUsePage(false);
	        routeInfoVO.setSidx("ROAD_NO");
		    List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
		    model.addAttribute("roadNoList", roadNoList);

			model.addAttribute("mvroadVO", mvroadVO);
			return "/mvroad/mvRoadInsert" ;
		}else{
			result.setSPCL_NO(spcl_no);
			model.addAttribute("result", result);
			return "/mvroad/mvRoadSelect" ;
		}
	}

	/**
	 * 특별관리구간 상세정보를 등록 한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/insertMvRoad.do" })
	public String insertMvRoad(@ModelAttribute MvRoadVO mvroadVO) throws Exception {
		String spcl_no = mvroadVO.getSPCL_NO();

		mvroadService.insertMvRoad(mvroadVO);

		return "redirect:/mvroad/selectMvRoadPop.do?spcl_no="+spcl_no ;
	}

	/**
	 * 특별관리구간 상세정보를 삭제한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/deleteMvRoad.do" })
	public String deleteMvRoad(@ModelAttribute MvRoadVO mvroadVO, HttpServletRequest request) throws Exception {

		String spcl_no = request.getParameter("spcl_no");
		mvroadVO.setSPCL_NO(spcl_no);

		mvroadService.deleteMvRoad(mvroadVO);

		return "redirect:/mvroad/selectMvRoadPop.do?spcl_no="+spcl_no ;
	}

	/**
	 * 특별관리구간 상세정보를 수정한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/updatePgMvRoad.do" })
	public String updatePgMvRoad(@ModelAttribute MvRoadVO mvroadVO,  ModelMap model, HttpServletRequest request) throws Exception {

	    RouteInfoVO routeInfoVO = new RouteInfoVO();
        routeInfoVO.setUsePage(false);
        routeInfoVO.setSidx("ROAD_NO");
        List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
        model.addAttribute("roadNoList", roadNoList);

		String spcl_no = request.getParameter("spcl_no");
		mvroadVO.setSPCL_NO(spcl_no);

		MvRoadVO result = mvroadService.selectMvRoad(mvroadVO);
		result.setSPCL_NO(spcl_no);
		model.addAttribute("result", result);

		return "/mvroad/mvRoadUpdate" ;
	}

	/**
	 * 특별관리구간 상세정보를 수정한다.
	 * @param
	 * @return "/mvroad/mvRoadPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mvroad/updateMvRoad.do" })
	public String updateMvRoad(@ModelAttribute MvRoadVO mvroadVO, HttpServletRequest request) throws Exception {

		String spcl_no = mvroadVO.getSPCL_NO();

		mvroadService.updateMvRoad(mvroadVO);

		return "redirect:/mvroad/selectMvRoadPop.do?spcl_no="+spcl_no ;
	}

}
