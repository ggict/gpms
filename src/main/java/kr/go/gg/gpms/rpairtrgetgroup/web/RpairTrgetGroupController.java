package kr.go.gg.gpms.rpairtrgetgroup.web;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairtrgetgroup.service.RpairTrgetGroupService;
import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;
import kr.go.gg.gpms.rpairtrgetslctn.service.RpairTrgetSlctnService;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

/**
 * @Class Name : RpairTrgetGroupController.java
 * @Description : RpairTrgetGroup Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairTrgetGroupController")
public class RpairTrgetGroupController  extends BaseController {

	@Resource(name = "rpairTrgetGroupService")
	private RpairTrgetGroupService rpairTrgetGroupService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "rpairTrgetSlctnService")
	private RpairTrgetSlctnService rpairTrgetSlctnService;
	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RpairTrgetGroupController.class);

	/**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록
     */
    @RequestMapping(value = {  "/api/rpairtrgetgroup/selectRpairTrgetGroupListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public  @ResponseBody Map<String, Object>  selectRpairTrgetGroupListPageRest(@RequestBody  RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
        int pageIndex = 1;
        int pageSize = egovPropertyService.getInt("pageSize");
        if (rpairTrgetGroupVO.getPageIndex() > 0) {
            pageIndex = rpairTrgetGroupVO.getPageIndex();
        } else {
            rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
        }

        if (rpairTrgetGroupVO.getPageSize() > 0) {
            pageSize = rpairTrgetGroupVO.getPageSize();
        } else {
            rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));
        }

        if (rpairTrgetGroupVO.getPageUnit() <= 0) {
            rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
        }

        int firstIndex = pageSize * pageIndex - pageSize;
        int lastIndex = firstIndex + pageSize;
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageSize);
        paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

        rpairTrgetGroupVO.setFirstIndex(firstIndex);
        rpairTrgetGroupVO.setLastIndex(lastIndex);
        rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        rpairTrgetGroupVO.setUsePage(true);

        rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
        rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 노선코드 가져오기.
        if(StringUtils.isNotEmpty( rpairTrgetGroupVO.getROAD_NO_VAL()) && StringUtils.isEmpty( rpairTrgetGroupVO.getROUTE_CODE())){
            //ROAD_NO_VAL
            String routeCode = "";
            RouteInfoVO routeInfoVO = new RouteInfoVO();
            //노선 번호
            routeInfoVO.setUsePage(false);
            routeInfoVO.setROAD_NO_VAL(rpairTrgetGroupVO.getROAD_NO_VAL());
            routeInfoVO.setSidx("ROAD_NO");
            List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
            if(roadNoList!=null && roadNoList.size()>0){
                rpairTrgetGroupVO.setROUTE_CODE(roadNoList.get(0).getROAD_NO());
            }
        }

        // 보수대상선정 그룹 목록
        List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetGroupList(rpairTrgetGroupVO);
        // 보수대상선정 그룹 갯수
        int total_count = rpairTrgetGroupService.selectRpairTrgetGroupListTotalCount(rpairTrgetGroupVO);
        // 보수대상선정 그룹 페이지 갯수
        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) pageSize);

        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", pageIndex);
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);

        return map;
    }

    /**
     * 보수대상 우선순위저장 처리
     */
    @RequestMapping(value = {  "/api/rpairtrgetgroup/updatePRIORT.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody RpairTrgetGroupVO updatePRIORT(@RequestBody List<RpairTrgetGroupVO> lvo, HttpSession session) throws Exception {
        RpairTrgetGroupVO vo = new RpairTrgetGroupVO();
        BindBeansToActiveUser(vo);

        try {
            // 보수대상 우선순위저장 처리
            rpairTrgetGroupService.updatePRIORT(lvo, vo);

            vo.setResultSuccess("true");
            vo.setResultMSG("정상 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            vo.setResultSuccess("false");
            vo.setResultMSG("오류가 발생하였습니다.");
        }

        return vo;
    }

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)의 구간 셀 ID 목록을 조회한다.
     * @param rpairTrgetGroupVO
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetGroupCELLListRest.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody List<RpairTrgetGroupVO> selectRpairTrgetGroupCELLListRest(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
        rpairTrgetGroupVO.setUsePage(false);

        List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetGroupCELLList(rpairTrgetGroupVO);
        return items;
    }











	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다. (pageing)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrgetgroup/selectRpairTrgetGroupList.do" })
	public String selectRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {
		rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetGroupVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

		rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetGroupList(rpairTrgetGroupVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetGroupService.selectRpairTrgetGroupListTotalCount(rpairTrgetGroupVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrgetgroup/RpairTrgetGroupList" ;
	}


	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다. (pageing)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetGroupList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairTrgetGroupVO> selectRpairTrgetGroupListRest(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetGroupVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

		rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetGroupList(rpairTrgetGroupVO);
		return items;
	}


	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다. (pageing)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrgetgroup/selectRpairTrgetGroupListPage.do" })
	public String selectRpairTrgetGroupListPage(RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {
		rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairTrgetGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rpairTrgetGroupVO.getPageUnit());
		paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

		rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetGroupList(rpairTrgetGroupVO);
		model.addAttribute("items", items);

		int totCnt = rpairTrgetGroupService.selectRpairTrgetGroupListTotalCount(rpairTrgetGroupVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/rpairtrgetgroup/RpairTrgetGroupList" ;
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다. (pageing)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/rpairtrgetgroup/downloadExcel.do" })
	public  String  downloadExcel(  RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		int pageIndex = 1;
		java.math.BigDecimal total_amount = new java.math.BigDecimal(0);
		java.math.BigDecimal total_fix_budget_asign = new java.math.BigDecimal(0);

		int pageSize = egovPropertyService.getInt("pageSize");
		if (rpairTrgetGroupVO.getPageIndex() > 0) {
			pageIndex = rpairTrgetGroupVO.getPageIndex();
		} else {
			rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		}

		if (rpairTrgetGroupVO.getPageSize() > 0) {
			pageSize = rpairTrgetGroupVO.getPageSize();
		} else {
			rpairTrgetGroupVO.setPageSize(egovPropertyService.getInt("pageSize"));
		}

		if (rpairTrgetGroupVO.getPageUnit() <= 0) {
			rpairTrgetGroupVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		}
		RpairTrgetSlctnVO rpairTrgetSlctnVO = new RpairTrgetSlctnVO();
		rpairTrgetSlctnVO.setTRGET_SLCTN_NO(rpairTrgetGroupVO.getTRGET_SLCTN_NO());
		RpairTrgetSlctnVO rpairTrgetSlctnOne = rpairTrgetSlctnService.selectRpairTrgetSlctn(rpairTrgetSlctnVO);
		model.addAttribute("rpairTrgetSlctnVO", rpairTrgetSlctnOne);

		int firstIndex = pageSize * pageIndex - pageSize;
		int lastIndex = firstIndex + pageSize;
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(pageSize);
		paginationInfo.setPageSize(rpairTrgetGroupVO.getPageSize());

		rpairTrgetGroupVO.setFirstIndex(firstIndex);
		rpairTrgetGroupVO.setLastIndex(lastIndex);
		rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		//전체 다운로드
		rpairTrgetGroupVO.setUsePage(false);

		rpairTrgetGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairTrgetGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairTrgetGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		if(StringUtils.isNotEmpty( rpairTrgetGroupVO.getROAD_NO_VAL()) && StringUtils.isEmpty( rpairTrgetGroupVO.getROUTE_CODE())){
			//ROAD_NO_VAL
			String routeCode = "";
			RouteInfoVO routeInfoVO = new RouteInfoVO();
			//노선 번호
			routeInfoVO.setUsePage(false);
			routeInfoVO.setROAD_NO_VAL(rpairTrgetGroupVO.getROAD_NO_VAL());
			routeInfoVO.setSidx("ROAD_NO");
			List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
			if(roadNoList!=null && roadNoList.size()>0){
				rpairTrgetGroupVO.setROUTE_CODE(roadNoList.get(0).getROAD_NO());
			}
		}
		List<RpairTrgetGroupVO> rpairTrgetItems = rpairTrgetGroupService.selectRpairTrgetGroupList(rpairTrgetGroupVO);
		int total_count = rpairTrgetGroupService.selectRpairTrgetGroupListTotalCount(rpairTrgetGroupVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) pageSize);
		// 결과 JSON 저장
		if(rpairTrgetItems!=null && rpairTrgetItems.size()>0){
			//totCnt = items.get(0).getTOTAL_COUNT().intValue();
			total_amount = java.math.BigDecimal.valueOf( Long.parseLong( rpairTrgetItems.get(0).getTOTAL_FIX_AMOUNT_CALC()));
			total_fix_budget_asign = java.math.BigDecimal.valueOf( Long.parseLong( rpairTrgetItems.get(0).getTOTAL_FIX_BUDGET_ASIGN()));
		}
		//total_amount=0;


		model.addAttribute("page", pageIndex);
		model.addAttribute("total", total_page);
		model.addAttribute("records", total_count);
		model.addAttribute("total_amount", total_amount);
		model.addAttribute("total_fix_budget_asign", total_fix_budget_asign);
		model.addAttribute("rpairTrgetItems", rpairTrgetItems);

		//String browserName = getBrowser(request);
		String browserName ="Chrome";
		String fileName = "보수대상엑셀.xls";

		if (browserName.equals("MSIE")) {
			// URLEncode하고 +문자만 공백으로 바꾸는 경우
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (browserName.equals("Chrome")) {
			// char단위로 검색하여 ~표시보다 char값이 높을 때(ascii코드값이 아닌경우)만 URLEncode한다.

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			fileName = sb.toString();
		} else {
			// latin1(8859_1)
			fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		return "/repairtarget/repairtargetExcel";
	}



	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 상세를 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/rpairtrgetgroup/selectRpairTrgetGroup.do"  })
	public String selectRpairTrgetGroup(@ModelAttribute("searchVO") RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {

		model.addAttribute("rpairTrgetGroupVO", rpairTrgetGroupService.selectRpairTrgetGroup(rpairTrgetGroupVO));
		return "/manage/rpairtrgetgroup/RpairTrgetGroupView";
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 상세를 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetGroup.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO selectRpairTrgetGroupRest(@RequestBody  RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		RpairTrgetGroupVO rpairTrgetGroupVOOne = rpairTrgetGroupService.selectRpairTrgetGroup(rpairTrgetGroupVO);
		return rpairTrgetGroupVOOne;
	}
	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 집계정보를 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return "/manage/rpairtrgetgroup/RpairTrgetGroupView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetGroupListTotalSummary.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO selectRpairTrgetGroupListTotalSummaryRest(@RequestBody  RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		RpairTrgetGroupVO rpairTrgetGroupVOOne = rpairTrgetGroupService.selectRpairTrgetGroupListTotalSummary(rpairTrgetGroupVO);
		return rpairTrgetGroupVOOne;
	}


	@RequestMapping(value = { "/manage/rpairtrgetgroup/addRpairTrgetGroupView.do" })
	public String addRpairTrgetGroupView(@ModelAttribute("searchVO") RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetGroupVO", new RpairTrgetGroupVO());
		return "/manage/rpairtrgetgroup/RpairTrgetGroupRegist";
	}

	@RequestMapping(value = { "/manage/rpairtrgetgroup/addRpairTrgetGroup.do"  })
	public String addRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.insertRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 등록되었습니다.");
		return "redirect:/manage/rpairtrgetgroup/selectRpairTrgetGroupList.do";
	}

	@RequestMapping(value = {  "/api/rpairtrgetgroup/addRpairTrgetGroup.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO addRpairTrgetGroupRest(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.insertRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 등록되었습니다.");
		return rpairTrgetGroupVO;
	}


	@RequestMapping(value = { "/manage/rpairtrgetgroup/updateRpairTrgetGroupView.do" })
	public String updateRpairTrgetGroupView(@ModelAttribute("searchVO") RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {
		model.addAttribute("rpairTrgetGroupVO", rpairTrgetGroupService.selectRpairTrgetGroup(rpairTrgetGroupVO));
		return "/manage/rpairtrgetgroup/RpairTrgetGroupUpdate";

	}

	@RequestMapping(value = { "/manage/rpairtrgetgroup/updateRpairTrgetGroup.do" })
	public String updateRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.updateRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 수정되었습니다.");
		return "redirect:/manage/rpairtrgetgroup/selectRpairTrgetGroupList.do";
	}

	@RequestMapping(value = {  "/api/rpairtrgetgroup/updateRpairTrgetGroup.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO updateRpairTrgetGroupRest(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.updateRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 수정되었습니다.");
		return rpairTrgetGroupVO;
	}

	@RequestMapping(value = {  "/api/rpairtrgetgroup/updateToggleTMPR_SLCTN_AT.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO updateToggleTMPR_SLCTN_AT(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.updateToggleTMPR_SLCTN_AT(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 수정되었습니다.");
		RpairTrgetGroupVO rpairTrgetGroupVOOne = rpairTrgetGroupService.selectRpairTrgetGroupListTotalSummary(rpairTrgetGroupVO);
		rpairTrgetGroupVOOne.setResultSuccess("true");
		rpairTrgetGroupVOOne.setResultMSG("정상 수정되었습니다.");
		return rpairTrgetGroupVOOne;
		//return rpairTrgetGroupVO;
	}

	@RequestMapping(value = {  "/api/rpairtrgetgroup/updateInitTMPR_SLCTN_AT.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO updateInitTMPR_SLCTN_AT(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.updateInitTMPR_SLCTN_AT(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 수정되었습니다.");
		return rpairTrgetGroupVO;
	}

	@RequestMapping(value = { "/manage/rpairtrgetgroup/deleteRpairTrgetGroup.do" })
	public String deleteRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.deleteRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/manage/rpairtrgetgroup/selectRpairTrgetGroupList.do";
	}

	@RequestMapping(value = {   "/api/rpairtrgetgroup/deleteRpairTrgetGroup.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairTrgetGroupVO deleteRpairTrgetGroupRest(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(rpairTrgetGroupVO);
		rpairTrgetGroupService.deleteRpairTrgetGroup(rpairTrgetGroupVO);
		rpairTrgetGroupVO.setResultSuccess("true");
		rpairTrgetGroupVO.setResultMSG("정상 삭제되었습니다.");
		return rpairTrgetGroupVO;
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetDeptStatistics.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairTrgetGroupVO> selectRpairTrgetDeptStatistics(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetDeptStatistics(rpairTrgetGroupVO);
		return items;
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 보수공법 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetMethodStatistics.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairTrgetGroupVO> selectRpairTrgetMethodStatistics(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetMethodStatistics(rpairTrgetGroupVO);
		return items;
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 행정구역 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetAdminStatistics.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairTrgetGroupVO> selectRpairTrgetAdminStatistics(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetAdminStatistics(rpairTrgetGroupVO);
		return items;
	}


	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetDeptStatistics2.do" }, method = RequestMethod.POST)
	public View   downloadExcelRpairTrgetDeptStatistics2(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetDeptStatistics(rpairTrgetGroupVO);

        String[] excel_title  = {"예산집행기관","예산(원)"};
        String[] excel_column = {"dept_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setDEPT_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "관리기관별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");


		return new ExcelView();
	}



	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 보수공법 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetMethodStatistics2.do" }, method = RequestMethod.POST)
	public View downloadExcelRpairTrgetMethodStatistics2(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetMethodStatistics(rpairTrgetGroupVO);

		String[] excel_title  = {"보수공법종류","예산(원)"};
        String[] excel_column = {"msrc_cl_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setMSRC_CL_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "보수공법별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");

		return new ExcelView();
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 행정구역 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetAdminStatistics2.do" }, method = RequestMethod.POST)
	public View downloadExcelRpairTrgetAdminStatistics2(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);

		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetAdminStatistics(rpairTrgetGroupVO);

		String[] excel_title  = {"단위행정구역","예산(원)"};
        String[] excel_column = {"adm_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setADM_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "행정구역별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");
		return new ExcelView();
	}


	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetDeptStatistics.do" }, method = RequestMethod.POST)
	public String   downloadExcelRpairTrgetDeptStatistics(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model,  HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);
		rpairTrgetGroupVO.setTMPR_SLCTN_AT("Y");
		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetDeptStatistics(rpairTrgetGroupVO);

        String[] excel_title  = {"예산집행기관","예산(원)"};
        String[] excel_column = {"dept_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setDEPT_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
        String fileName =  "관리기관별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd")+".xls";
      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    fileName);
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");

      //String browserName = getBrowser(request);
  		String browserName ="Chrome";

  		if (browserName.equals("MSIE")) {
  			// URLEncode하고 +문자만 공백으로 바꾸는 경우
  			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
  		} else if (browserName.equals("Chrome")) {
  			// char단위로 검색하여 ~표시보다 char값이 높을 때(ascii코드값이 아닌경우)만 URLEncode한다.

  			StringBuffer sb = new StringBuffer();
  			for (int i = 0; i < fileName.length(); i++) {
  				char c = fileName.charAt(i);
  				if (c > '~') {
  					sb.append(URLEncoder.encode("" + c, "UTF-8"));
  				} else {
  					sb.append(c);
  				}
  			}
  			fileName = sb.toString();
  		} else {
  			// latin1(8859_1)
  			fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
  		}

  		response.setContentType("application/octet-stream");
  		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

  		response.setHeader("Content-Transfer-Encoding", "binary");
  		response.setHeader("Pragma", "no-cache");
  		response.setHeader("Expires", "0");

        return "/repairtarget/repairtargetDeptStatisticsExcel";
	}



	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 보수공법 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetMethodStatistics.do" }, method = RequestMethod.POST)
	public String downloadExcelRpairTrgetMethodStatistics(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model,  HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);
		rpairTrgetGroupVO.setTMPR_SLCTN_AT("Y");
		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetMethodStatistics(rpairTrgetGroupVO);

		String[] excel_title  = {"보수공법종류","예산(원)"};
        String[] excel_column = {"msrc_cl_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setMSRC_CL_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        String fileName =  "보수공법별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd")+".xls";
        model.addAttribute("file_name",    fileName);
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");

      //String browserName = getBrowser(request);
  		String browserName ="Chrome";

  		if (browserName.equals("MSIE")) {
  			// URLEncode하고 +문자만 공백으로 바꾸는 경우
  			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
  		} else if (browserName.equals("Chrome")) {
  			// char단위로 검색하여 ~표시보다 char값이 높을 때(ascii코드값이 아닌경우)만 URLEncode한다.

  			StringBuffer sb = new StringBuffer();
  			for (int i = 0; i < fileName.length(); i++) {
  				char c = fileName.charAt(i);
  				if (c > '~') {
  					sb.append(URLEncoder.encode("" + c, "UTF-8"));
  				} else {
  					sb.append(c);
  				}
  			}
  			fileName = sb.toString();
  		} else {
  			// latin1(8859_1)
  			fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
  		}

  		response.setContentType("application/octet-stream");
  		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

  		response.setHeader("Content-Transfer-Encoding", "binary");
  		response.setHeader("Pragma", "no-cache");
  		response.setHeader("Expires", "0");
        return "/repairtarget/repairtargetMethodStatisticsExcel";
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 행정구역 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairtrgetgroup/downloadExcelRpairTrgetAdminStatistics.do" }, method = RequestMethod.POST)
	public String downloadExcelRpairTrgetAdminStatistics(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model,  HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		rpairTrgetGroupVO.setUsePage(false);
		rpairTrgetGroupVO.setTMPR_SLCTN_AT("Y");
		List<RpairTrgetGroupVO> items = rpairTrgetGroupService.selectRpairTrgetAdminStatistics(rpairTrgetGroupVO);

		String[] excel_title  = {"단위행정구역","예산(원)"};
        String[] excel_column = {"adm_nm","amount_calc"};

        Long total_amount_calc = new Long(0);
        if(items!=null && items.size()>0){
        	total_amount_calc = items.get(0).getTOTAL_AMOUNT_CALC();
        }
        RpairTrgetGroupVO sumRpairTrgetGroupVO = new RpairTrgetGroupVO();
        sumRpairTrgetGroupVO.setADM_NM("합계");
        sumRpairTrgetGroupVO.setAMOUNT_CALC(total_amount_calc);
        items.add(sumRpairTrgetGroupVO);
        String fileName =  "행정구역별통계목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd")+".xls";

      //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",   fileName);
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    items);
        model.addAttribute("listType",    "object");
      //String browserName = getBrowser(request);
  		String browserName ="Chrome";


  		if (browserName.equals("MSIE")) {
  			// URLEncode하고 +문자만 공백으로 바꾸는 경우
  			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
  		} else if (browserName.equals("Chrome")) {
  			// char단위로 검색하여 ~표시보다 char값이 높을 때(ascii코드값이 아닌경우)만 URLEncode한다.

  			StringBuffer sb = new StringBuffer();
  			for (int i = 0; i < fileName.length(); i++) {
  				char c = fileName.charAt(i);
  				if (c > '~') {
  					sb.append(URLEncoder.encode("" + c, "UTF-8"));
  				} else {
  					sb.append(c);
  				}
  			}
  			fileName = sb.toString();
  		} else {
  			// latin1(8859_1)
  			fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
  		}

  		response.setContentType("application/octet-stream");
  		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

  		response.setHeader("Content-Transfer-Encoding", "binary");
  		response.setHeader("Pragma", "no-cache");
  		response.setHeader("Expires", "0");

        return "/repairtarget/repairtargetAdminStatisticsExcel";
	}

	// 2019 신규 통계지표.

	/**
	 * 통계 > 보수대상 선정 > 노선별통계 페이지 조회
	 */
	@RequestMapping(value = "/rpairtrgetgroup/rpairRoutLenStats.do")
	public String viewRpairRoutLenStats(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {

		// 선정년도 (2017 ~ 현재연도)
		model.addAttribute("slctnYearList", DateUtil.getSlctnYearList());

		model.addAttribute("rpairTrgetGroupVO", rpairTrgetGroupVO);

		return "/stats/rpair/rpairRoutLenStats";
	}

	/**
	 * 통계 > 보수대상 선정 > 노선별통계 > 데이터조회
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairRoutLenStats.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectRpairRoutLenStats(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model,HttpSession session) throws Exception {

		// 데이터 조회
		List<RpairTrgetGroupVO> result = rpairTrgetGroupService.selectRpairRoutLenStats(rpairTrgetGroupVO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", rpairTrgetGroupVO.getPage());
		map.put("total", total_page);
		map.put("rows", result);

		return map;
	}

	/**
	 * 통계 > 보수대상 선정 > 노선별통계 > 엑셀
	 */
	@RequestMapping(value = "/rpairtrgetgroup/rpairRoutLenStatsExcel.do")
	public View rpairRoutLenStatsExcel(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		List dataList = rpairTrgetGroupService.selectRpairRoutLenStatsExcel(rpairTrgetGroupVO);

		String[] excel_title = { "노선번호", "노선명", "연장", "구간갯수" };
		String[] excel_column = { "route_code", "road_nm", "group_len","group_cnt" };

		model.addAttribute("file_name","보수구간선정_노선별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

	/**
	 * 통계 > 보수대상 선정 > 관리기관별통계 페이지 조회
	 */
	@RequestMapping(value = "/rpairtrgetgroup/rpairDeptLenStats.do")
	public String rpairDeptLenStats(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model) throws Exception {

		// 선정년도 (2017 ~ 현재연도)
		model.addAttribute("slctnYearList", DateUtil.getSlctnYearList());

		model.addAttribute("rpairTrgetGroupVO", rpairTrgetGroupVO);

		return "/stats/rpair/rpairDeptLenStats";
	}

	/**
	 * 통계 > 보수대상 선정 > 관리기관별통계 > 데이터조회
	 */
	@RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairDeptLenStats.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectRpairDeptLenStats(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model,HttpSession session) throws Exception {

		// 데이터 조회
		List<RpairTrgetGroupVO> result = rpairTrgetGroupService.selectRpairDeptLenStats(rpairTrgetGroupVO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", rpairTrgetGroupVO.getPage());
		map.put("total", total_page);
		map.put("rows", result);

		return map;
	}

	/**
	 * 통계 > 보수대상 선정 > 관리기관별통계 > 엑셀
	 */
	@RequestMapping(value = "/rpairtrgetgroup/rpairDeptLenStatsExcel.do")
	public View rpairDeptLenStatsExcel(@ModelAttribute RpairTrgetGroupVO rpairTrgetGroupVO,ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		List dataList = rpairTrgetGroupService.selectRpairDeptLenStatsExcel(rpairTrgetGroupVO);

		String[] excel_title = { "관리부서명", "연장", "구간갯수" };
		String[] excel_column = { "dept_nm", "group_len", "group_cnt" };

		model.addAttribute("file_name","보수대상산정_관리기관별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
		model.addAttribute("excel_title", excel_title);
		model.addAttribute("excel_column", excel_column);
		model.addAttribute("data_list", dataList);

		return new ExcelView();
	}

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 공용성 예측 모델 목록을 조회한다.
     * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/api/rpairtrgetgroup/selectRpairTrgetPredctStatistics.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectRpairTrgetPredctStatistics(@RequestBody RpairTrgetGroupVO rpairTrgetGroupVO, ModelMap model, HttpSession session) throws Exception {
        rpairTrgetGroupVO.setUsePage(false);

        Map<String, BigDecimal[]> data = rpairTrgetGroupService.selectRpairTrgetPredctStatistics(rpairTrgetGroupVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);

        return map;
    }

}
