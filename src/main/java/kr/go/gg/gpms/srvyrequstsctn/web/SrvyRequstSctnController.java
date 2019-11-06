

package kr.go.gg.gpms.srvyrequstsctn.web;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.security.core.context.SecurityContextHolder;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.cntrwkcellinfo.service.CntrwkCellInfoService;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;
import kr.go.gg.gpms.srvyrequstsctn.service.SrvyRequstSctnService;
import kr.go.gg.gpms.srvyrequstsctn.service.model.SrvyRequstSctnVO;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.SrvyRequstSctnCellInfoService;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.model.SrvyRequstSctnCellInfoVO;



/**
 * @Class Name : SrvyRequstSctnController.java
 * @Description : SrvyRequstSctn Controller class
 * @Modification Information
 *
 * @author 
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("srvyRequstSctnController")
public class SrvyRequstSctnController extends BaseController {

	@Resource(name = "srvyRequstSctnService")
	private SrvyRequstSctnService srvyRequstSctnService;
	
	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;
	
	@Resource(name = "srvyRequstSctnCellInfoService")
	private SrvyRequstSctnCellInfoService srvyRequstSctnCellInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyRequstSctnController.class);
	
	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 목록을 조회한다. (pageing)
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return "/srvyrequstsctn/SrvyRequstSctnList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvyrequstsctn/selectSrvyRequstSctnList.do" })
	public String selectSrvyRequstSctnList(SrvyRequstSctnVO srvyRequstSctnVO, ModelMap model) throws Exception {
		
		//도로 등급
		addCodeToModel("RDGD", "codesRDGD", model);
		
		//노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);
		model.addAttribute("roadNoList", roadNoList);
		
		
/*		srvyRequstSctnVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		srvyRequstSctnVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyRequstSctnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyRequstSctnVO.getPageUnit());
		paginationInfo.setPageSize(srvyRequstSctnVO.getPageSize());

		srvyRequstSctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyRequstSctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyRequstSctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyRequstSctnVO> items = srvyRequstSctnService.selectSrvyRequstSctnList(srvyRequstSctnVO);
		model.addAttribute("items", items);

		int totCnt = srvyRequstSctnService.selectSrvyRequstSctnListTotalCount(srvyRequstSctnVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);*/

		return "/srvyrequstsctn/srvyRequstSctnList" ;
	}
	
	@RequestMapping(value = { "/srvyrequstsctn/addSrvyRequstSctnView.do" })
	public String addSrvyRequstSctnView(@ModelAttribute("searchVO") SrvyRequstSctnVO srvyRequstSctnVO, ModelMap model) throws Exception {
		model.addAttribute("srvyRequstSctnVO", new SrvyRequstSctnVO());
//		CntrwkVO cntrwkVO = new CntrwkVO();
//		cntrwkVO.setCNTRWK_ID(cntrwkDtlVO.getCNTRWK_ID());
//		model.addAttribute("cntrwkVO", cntrwkService.selectCntrwk(cntrwkVO));
//
//		Integer cntrwk_start_year  = egovPropertyService.getInt("CNTRWK_START_YEAR", 2017);
//		model.addAttribute("cntrwkYears", getYears(cntrwk_start_year));
		// 반기구분코드
//		addCodeToModel("HTSE", model);
		//공사 구분
//		addCodeToModel("CWSE", model);
		//날씨 구분
//		addCodeToModel("WETH", model);

//		PavMatrlVO pavMatrlVOParam = new PavMatrlVO();
//		pavMatrlVOParam.setUsePage(false);
//		List<PavMatrlVO> pavMatrlVOItems = pavMatrlService.selectPavMatrlList(pavMatrlVOParam);
//		model.addAttribute("PavMatrls", pavMatrlVOItems);
//		RpairMthdVO rpairMthdVOParam = new RpairMthdVO();
//		rpairMthdVOParam.setUsePage(false);
//		List<RpairMthdVO> rpairMthdItems = rpairMthdService.selectRpairMthdList(rpairMthdVOParam);
//		model.addAttribute("RpairMthds", rpairMthdItems);

		//공사 업체
//		model.addAttribute("companyList", getCompanyList());

		return "/srvyrequstsctn/srvyRequstSctnRegist";
	}
	
	@RequestMapping(value = { "/srvyrequstsctn/addSrvyRequstSctn.do"  })
	public String addSrvyRequstSctn(@ModelAttribute SrvyRequstSctnVO srvyRequstSctnVO, SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO, BindingResult bindingResult, Model model, HttpServletRequest request, SessionStatus status, HttpSession session) throws Exception {
		Map<String, String> req = requestToHashMap(request);

		String action_flag = StringUtils.isNotEmpty(req.get("action_flag"))? req.get("action_flag").trim():"INSERT" ;
		// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";

		try {

			resultCode = "SAVE_SUCCESS";
			BindBeansToActiveUser(srvyRequstSctnVO);

			String srvyRequstSctnNo = srvyRequstSctnService.insertSrvyRequstSctn(srvyRequstSctnVO);
			//위치 정보 등록
			String cellIdList[] = srvyRequstSctnCellInfoVO.getPAV_CELL_ID().split(",");

			srvyRequstSctnCellInfoVO.setSRVY_REQUST_SCTN_NO(srvyRequstSctnNo);
			BindBeansToActiveUser(srvyRequstSctnCellInfoVO);

			for(String cellId : cellIdList){
				srvyRequstSctnCellInfoVO.setPAV_CELL_ID(cellId);
				srvyRequstSctnCellInfoService.insertSrvyRequstSctnCellInfo(srvyRequstSctnCellInfoVO);
			}

			model.addAttribute("insertKey", srvyRequstSctnNo);
			srvyRequstSctnVO.setResultSuccess("true");
			srvyRequstSctnVO.setResultMSG("정상 등록되었습니다.");
			// 결과 처리용 [수정X]
	    	model.addAttribute("resultCode", resultCode);
	    	model.addAttribute("resultMsg", resultMsg);
	    	model.addAttribute("callBackFunction", StringUtils.isNotEmpty(srvyRequstSctnVO.getCallBackFunction()) ?
	    			srvyRequstSctnVO.getCallBackFunction().trim():"" );	// 처리후 호출 함수

	    	status.setComplete();	//Double Submit 방지
		} catch (Exception e) {
			resultCode = "ERROR";
    		resultMsg = "등록 오류 발생";
    		LOGGER.error("조사요청구간 등록 오류", e);
		}

		return "/cmmn/commonMsg";
	}
	
	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다. (pageing)
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return "/cntrwkdtl/CntrwkDtlList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/srvyrequstsctn/selectSrvyRequstSctnList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object>  selectSrvyRequstSctnListRest(@RequestBody SrvyRequstSctnVO srvyRequstSctnVO, ModelMap model, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyRequstSctnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(srvyRequstSctnVO.getPageUnit());
		paginationInfo.setPageSize(srvyRequstSctnVO.getPageSize());

		srvyRequstSctnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyRequstSctnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyRequstSctnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyRequstSctnVO> items = srvyRequstSctnService.selectSrvyRequstSctnList(srvyRequstSctnVO);
		int total_count = srvyRequstSctnService.selectSrvyRequstSctnListTotalCount(srvyRequstSctnVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) srvyRequstSctnVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", srvyRequstSctnVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
		
		return map;
	}
	



}
