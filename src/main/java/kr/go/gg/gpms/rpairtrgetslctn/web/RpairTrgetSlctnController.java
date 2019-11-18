

package kr.go.gg.gpms.rpairtrgetslctn.web;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairtrgetgroup.service.RpairTrgetGroupService;
import kr.go.gg.gpms.rpairtrgetslctn.service.RpairTrgetSlctnService;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;



/**
 * @Class Name : RpairTrgetSlctnController.java
 * @Description : RpairTrgetSlctn Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairTrgetSlctnController")
public class RpairTrgetSlctnController  extends BaseController {

	@Resource(name = "rpairTrgetSlctnService")
	private RpairTrgetSlctnService rpairTrgetSlctnService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "cmmnService")
	private CmmnService cmmnService;
	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;
	@Resource(name = "rpairTrgetGroupService")
	private RpairTrgetGroupService rpairTrgetGroupService;
	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;


	private static final Logger LOGGER = LoggerFactory.getLogger(RpairTrgetSlctnController.class);

    /**
     * 보수대상선정 왼쪽 화면
     */
    @RequestMapping(value = { "/rpairtrgetslctn/common_repairtarget.do" })
    public String common_repairtarget(ModelMap model) throws Exception {
        // 분석단위코드(ANUN)[200m(ANUN0001), 1km(ANUN0002)]
        List<CodeVO> anunList = getCodeList("ANUN");
        model.addAttribute("anunList", anunList);

        // 선정년도
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        List<Integer> slctnYearList = new ArrayList<Integer>();
        for ( int i = Integer.valueOf(year); i >= 2018; i-- ) {
            slctnYearList.add(i);
        }

        model.addAttribute("anunList", anunList);
        model.addAttribute("slctnYearList", slctnYearList);

        return "/repairtarget/common_repairtarget";
    }

    /**
     * 보수대상선정이력 목록을 조회
     */
    @RequestMapping(value = { "/api/rpairtrgetslctn/selectRpairTrgetSlctnList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody List<RpairTrgetSlctnVO> selectRpairTrgetSlctnListRest(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model, HttpSession session) throws Exception {
        rpairTrgetSlctnVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
        rpairTrgetSlctnVO.setPageSize(egovPropertyService.getInt("pageSize"));
        rpairTrgetSlctnVO.setUsePage(false);

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(rpairTrgetSlctnVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(rpairTrgetSlctnVO.getPageUnit());
        paginationInfo.setPageSize(rpairTrgetSlctnVO.getPageSize());

        rpairTrgetSlctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        rpairTrgetSlctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
        rpairTrgetSlctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<RpairTrgetSlctnVO> items = rpairTrgetSlctnService.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);

        return items;
    }

    /**
     * 보수대상선정시작 처리
     */
    @RequestMapping(value = {  "/api/rpairtrgetslctn/addRpairTrgetSlctn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody RpairTrgetSlctnVO addRpairTrgetSlctnRest(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, HttpSession session) throws Exception {
        BindBeansToActiveUser(rpairTrgetSlctnVO);

        try {
            // 보수대상선정시작(보수_대상_선정 삭제/등록)
            rpairTrgetSlctnService.addRpairTrgetSlctn(rpairTrgetSlctnVO);
            // 보수대상선정시작 처리(보수_대상_항목_그룹 등록)
            rpairTrgetSlctnService.procRepairTarget(rpairTrgetSlctnVO);

            rpairTrgetSlctnVO.setResultSuccess("true");
            rpairTrgetSlctnVO.setResultMSG("정상 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            rpairTrgetSlctnVO.setResultSuccess("false");
            rpairTrgetSlctnVO.setResultMSG("오류가 발생하였습니다.");
        }

        return rpairTrgetSlctnVO;
    }

    /**
     * 보수대상선정 목록
     */
    @RequestMapping(value = { "/rpairtrgetslctn/selectRpairTrgetSlctn.do"  })
    public String selectRpairTrgetSlctn(@ModelAttribute("frmRpairTrgetSlctn") RpairTrgetSlctnVO rpairTrgetSlctnVO,  ModelMap model) throws Exception {
        //도로 등급
        addCodeToModel("RDGD","roadGradList", model);

        //노선 번호
        RouteInfoVO routeInfoVO = new RouteInfoVO();
        routeInfoVO.setUsePage(false);
        routeInfoVO.setSidx("ROAD_NO");
        List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
        model.addAttribute("roadNoList", roadNoList);

        //행정구역
        model.addAttribute("admList", cmmnService.selectAdmCodeList(new CodeVO()));

        //관리 도로
        addCodeToModel("MNRD", "mngRdList", model);

        // 보수대상선정 상세정보
        RpairTrgetSlctnVO rpairTrgetSlctnOne = rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO);
        model.addAttribute("rpairTrgetSlctnVO", rpairTrgetSlctnOne);

        return "/repairtarget/repairtargetSlctnView";
    }




//    @RequestMapping(value = {  "/api/rpairtrgetslctn/rangeSelection.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
//    public @ResponseBody RpairTrgetSlctnVO doRangeSelection(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, HttpSession session) throws Exception {
//
//        rpairTrgetSlctnVO.setSLCTN_STTUS("RTSS0003");
//
//        BindBeansToActiveUser(rpairTrgetSlctnVO);
//        RpairTrgetSlctnVO rpairTrgetSlctnVOOne = rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO);
//
//        if(rpairTrgetSlctnVOOne!=null && rpairTrgetSlctnVO.getSLCTN_BUDGET()>0){
//
//            HashMap hashResult= rpairTrgetSlctnService.procRepairTarget(rpairTrgetSlctnVO);
//
//            rpairTrgetSlctnVO.setResultSuccess("true");
//            rpairTrgetSlctnVO.setResultMSG("정상 등록되었습니다.");
//        }
//
//        return rpairTrgetSlctnVO;
//    }



// ################################################################################





	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 목록을 조회한다. (pageing)
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return "/manage/rpairtrgetslctn/RpairTrgetSlctnList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrgetslctn/selectRpairTrgetSlctnList.do" })
	public String selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model) throws Exception {
		rpairTrgetSlctnVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetSlctnVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetSlctnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetSlctnVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetSlctnVO.getPageSize());

		rpairTrgetSlctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetSlctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetSlctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetSlctnVO> items = rpairTrgetSlctnService.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetSlctnService.selectRpairTrgetSlctnListTotalCount(rpairTrgetSlctnVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrgetslctn/RpairTrgetSlctnList" ;
	}





	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 목록을 조회한다. (pageing)
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return "/manage/rpairtrgetslctn/RpairTrgetSlctnList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrgetslctn/selectRpairTrgetSlctnListPage.do" })
	public String selectRpairTrgetSlctnListPage(RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model) throws Exception {
		rpairTrgetSlctnVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetSlctnVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetSlctnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetSlctnVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetSlctnVO.getPageSize());

		rpairTrgetSlctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetSlctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetSlctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetSlctnVO> items = rpairTrgetSlctnService.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetSlctnService.selectRpairTrgetSlctnListTotalCount(rpairTrgetSlctnVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrgetslctn/RpairTrgetSlctnList" ;
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 목록을 조회한다. (pageing)
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return "/manage/rpairtrgetslctn/RpairTrgetSlctnList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/rpairtrgetslctn/selectRpairTrgetSlctnListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<RpairTrgetSlctnVO>  selectRpairTrgetSlctnListPageRest(@RequestBody  RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetSlctnVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetSlctnVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetSlctnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetSlctnVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetSlctnVO.getPageSize());

		rpairTrgetSlctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetSlctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetSlctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetSlctnVO> items = rpairTrgetSlctnService.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);
		return items;
	}





	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 상세를 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return "/manage/rpairtrgetslctn/RpairTrgetSlctnView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetslctn/selectRpairTrgetSlctn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetSlctnVO selectRpairTrgetSlctnRest(@RequestBody  RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model, HttpSession session) throws Exception {
		RpairTrgetSlctnVO rpairTrgetSlctnVOOne = rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO);
		return rpairTrgetSlctnVOOne;
	}



	@RequestMapping(value = { "/manage/rpairtrgetslctn/addRpairTrgetSlctnView.do" })
	public String addRpairTrgetSlctnView(@ModelAttribute("searchVO") RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetSlctnVO", new RpairTrgetSlctnVO());
		return "/manage/rpairtrgetslctn/RpairTrgetSlctnRegist";
	}

	@RequestMapping(value = { "/manage/rpairtrgetslctn/addRpairTrgetSlctn.do"  })
	public String addRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {



		BindBeansToActiveUser(rpairTrgetSlctnVO);
		rpairTrgetSlctnService.insertRpairTrgetSlctn(rpairTrgetSlctnVO);
		rpairTrgetSlctnVO.setResultSuccess("true");
		rpairTrgetSlctnVO.setResultMSG("정상 등록되었습니다.");


		return "redirect:/manage/rpairtrgetslctn/selectRpairTrgetSlctnList.do";
	}


	@RequestMapping(value = {  "/api/rpairtrgetslctn/saveComplete.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetSlctnVO doSaveComplete(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, HttpSession session) throws Exception {

		rpairTrgetSlctnVO.setSLCTN_STTUS("RTSS0010");

		BindBeansToActiveUser(rpairTrgetSlctnVO);
		RpairTrgetSlctnVO rpairTrgetSlctnVOOne = rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO);
		if(rpairTrgetSlctnVOOne!=null){
			HashMap hashResult= rpairTrgetSlctnService.procRepairTargetComplete(rpairTrgetSlctnVO);
			rpairTrgetSlctnVO.setResultSuccess("true");
			rpairTrgetSlctnVO.setResultMSG("정상 처리 되었습니다.");
		}


		return rpairTrgetSlctnVO;
	}




	@RequestMapping(value = { "/rpairtrgetslctn/intro.do" })
	public String introRpairTrgetSlctnView(@ModelAttribute("searchVO") RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model) throws Exception {
		//model.addAttribute("rpairTrgetSlctnVO", rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO));
		return "/repairtarget/intro";
	}

	@RequestMapping(value = { "/manage/rpairtrgetslctn/updateRpairTrgetSlctnView.do" })
	public String updateRpairTrgetSlctnView(@ModelAttribute("searchVO") RpairTrgetSlctnVO rpairTrgetSlctnVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetSlctnVO", rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO));
		return "/manage/rpairtrgetslctn/RpairTrgetSlctnUpdate";
	}

	@RequestMapping(value = { "/manage/rpairtrgetslctn/updateRpairTrgetSlctn.do" })
	public String updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetSlctnVO);
		rpairTrgetSlctnService.updateRpairTrgetSlctn(rpairTrgetSlctnVO);
		rpairTrgetSlctnVO.setResultSuccess("true");
		rpairTrgetSlctnVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/rpairtrgetslctn/selectRpairTrgetSlctnList.do";
	}

	@RequestMapping(value = {  "/api/rpairtrgetslctn/updateRpairTrgetSlctn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetSlctnVO updateRpairTrgetSlctnRest(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetSlctnVO);
		rpairTrgetSlctnService.updateRpairTrgetSlctn(rpairTrgetSlctnVO);
		rpairTrgetSlctnVO.setResultSuccess("true");
		rpairTrgetSlctnVO.setResultMSG("정상 수정되었습니다.");
		return rpairTrgetSlctnVO;
	}

	@RequestMapping(value = { "/manage/rpairtrgetslctn/deleteRpairTrgetSlctn.do" })
	public String deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetSlctnVO);
		rpairTrgetSlctnService.deleteRpairTrgetSlctn(rpairTrgetSlctnVO);
		rpairTrgetSlctnVO.setResultSuccess("true");
		rpairTrgetSlctnVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/rpairtrgetslctn/selectRpairTrgetSlctnList.do";
	}

	@RequestMapping(value = {   "/api/rpairtrgetslctn/deleteRpairTrgetSlctn.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetSlctnVO deleteRpairTrgetSlctnRest(@RequestBody RpairTrgetSlctnVO rpairTrgetSlctnVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetSlctnVO);
		rpairTrgetSlctnService.deleteRpairTrgetSlctn(rpairTrgetSlctnVO);
		rpairTrgetSlctnVO.setResultSuccess("true");
		rpairTrgetSlctnVO.setResultMSG("정상 삭제되었습니다.");
		return rpairTrgetSlctnVO;
	}
}
