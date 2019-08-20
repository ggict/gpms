package kr.go.gg.gpms.pothole.monitor.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;











import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.pothole.monitor.service.MonitorService;
import kr.go.gg.gpms.pothole.monitor.service.model.MonitorVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : MonitorController.java
 * @Description : Monitor Controller class
 * @Modification Information
 *
 * @author YYK
 * @since 2018-01-03
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller("monitorController")
public class MonitorController extends BaseController {

	@Resource(name = "monitorService")
	private MonitorService monitorService;

	@Resource(name = "deptService")
	private DeptService deptService;


	@Autowired
	SessionManager sessionManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);


    @RequestMapping(value={"/mng/monitor/mntrngMbr.do"})
    public String mntrngMbr(DeptVO deptVO, Model model) throws Exception {

    	// 지역 셀렉트 박스 리스트
		List<DeptVO> RegionList = deptService.selectRegionList(deptVO);
		model.addAttribute("RegionList",  RegionList);

    	return "/pothole/monitor/mntrngMbrList";
    }


	/**
	 * 모니터링단원(TN_MNTRNG_MBR) 목록을 조회한다. (pageing)
	 *
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return "/monitor/mntrngMbrList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/monitor/selectMntrngMbrList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectMntrngMbrList(@RequestBody MonitorVO monitorVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(monitorVO.getPage());
		paginationInfo.setRecordCountPerPage(monitorVO.getPageUnit());
		paginationInfo.setPageSize(monitorVO.getRows());

		monitorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		monitorVO.setLastIndex(paginationInfo.getLastRecordIndex());
		monitorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<MonitorVO> items = monitorService.selectMntrngMbrList(monitorVO);
		int totCnt = monitorService.selectMntrngMbrListTotalCount(monitorVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", monitorVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}



    @RequestMapping(value={"/monitor/mntrngMbrView.do"})
    public String mntrngMbrView(MonitorVO monitorVO, DeptVO deptVO, Model model) throws Exception {

    	// 지역 셀렉트 박스 리스트
		List<DeptVO> RegionList = deptService.selectRegionList(deptVO);
		model.addAttribute("RegionList",  RegionList);

    	if( monitorVO.getVHCLE_NO() != null ){
			MonitorVO result= monitorService.selectMntrngMbrDetail(monitorVO);
			model.addAttribute("result",  result);
			model.addAttribute("sort",  "update");
    	}
    	else {
    		model.addAttribute("sort",  "insert");
    	}

    	return "/pothole/monitor/mntrngMbrView";
    }


	/**
	 * 모니터링단원(TN_MNTRNG_MBR) 을 수정/등록/삭제한다. (pageing)
	 *
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return map
	 * @exception Exception
	 */
	@RequestMapping(value = { "/monitor/updateMntrngMbr.do" }, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> updateMntrngMbr(@RequestBody MonitorVO monitorVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		int result = monitorService.updateMntrngMbr(monitorVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}


	@RequestMapping(value = { "/monitor/updateMntrngMbrEntrst.do" }, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> updateMntrngMbrEntrst(@RequestBody MonitorVO monitorVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(int i=0; i < monitorVO.getVHCLE_NO_ARR().length; i++){
			HashMap<String, String> VBmap = new HashMap<String, String>();

			VBmap.put("VHCLE_NO", monitorVO.getVHCLE_NO_ARR()[i]) ;
			VBmap.put("BSNM_NM", monitorVO.getBSNM_NM_ARR()[i]) ;

			list.add(VBmap);
		}
		monitorVO.setVHCLE_BSNM_LIST(list);

		int result = monitorService.updateMntrngMbrEntrst(monitorVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}


	@RequestMapping(value = { "/monitor/insertMntrngMbr.do" }, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> insertMntrngMbr(@RequestBody MonitorVO monitorVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		/* 2018.11.13
		insert 중 사용자 정보를 조회하여 사용자가 있을 경우 처리
		1. 삭제된 사용자일 경우 delete_at = 'Y'
		2. 삭제된 사용자도 아니고 그냥 중복입력된 경우
		*/

		MonitorVO mnt = monitorService.selectMntrngMbrDetail(monitorVO);

		if (mnt != null){
			if (mnt.getDELETE_AT().equals("Y") ){
				map.put("chk", "D");

			}else{
				map.put("chk", "Y");
			}

		}else{
			String result = monitorService.insertMntrngMbr(monitorVO);
		}

		return map;
	}

	@RequestMapping(value = { "/monitor/deleteMntrngMbr.do" }, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> deleteMntrngMbr(@RequestBody MonitorVO monitorVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		if( monitorVO.getVHCLE_NO() != null ){
			int result = monitorService.deleteMntrngMbr(monitorVO);
		}

		Map<String, Object> map = new HashMap<String, Object>();

		return map;
	}


	// 2018.03.06. YYK. 위촉여부 변경을 기존 DIV -> 윈도우팝업으로 수정
    @RequestMapping(value={"/monitor/mntrngMbrEntrust.do"})
    public String mntrngMbrEntrust(MonitorVO monitorVO, DeptVO deptVO, Model model) throws Exception {

    	return "/pothole/monitor/mntrngMbrEntrust";
	}


}
