package kr.go.gg.gpms.pothole.cmptnc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.pothole.cmptnc.service.CmptncService;
import kr.go.gg.gpms.pothole.cmptnc.service.model.CmptncVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
 * 관할구역 검색 컨트롤러
 * @Classname : CmptncController.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@Controller("cmptncController")
public class CmptncController extends BaseController {

	@Resource(name = "cmptncService")
	private CmptncService cmptncService;

	@Resource(name = "deptService")
	private DeptService deptService;


	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(CmptncController.class);

	/**
	 * 관할구역 목록을 조회한다.
	 * @author    : JOY
	 * @date      : 2018. 1. 4.
	 *
	 * @param     : CmptncVO - 조회할 정보가 담긴 CmptncVOVO
	 * @return    : "/pothole/cmptnc/cmptncList"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/pothole/cmptnc/selectCmptncList.do" })
	public String selectCmptncList(@ModelAttribute("searchVO") CmptncVO cmptncVO, DeptVO deptVO, ModelMap model) throws Exception {

    	// 관할기관 셀렉트 박스 리스트
		deptVO.setFlag("minja"); //민자도로사업자 세부목록 출력을 위한 flag
		List<DeptVO> deptcode = deptService.selectCptcIsttList(deptVO);

		model.addAttribute("deptcodeList",  deptcode);

		return "/pothole/cmptnc/cmptncList";
	}


	@SuppressWarnings("unused")
    @RequestMapping(value = { "/pothole/cmptnc/selectCmptncListGrid.do" })
	@ResponseBody
	public JSONObject selectCmptncListGrid(@ModelAttribute("searchVO") CmptncVO cmptncVO, DeptVO deptVO, ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = new JSONObject();

    	// 관할기관 셀렉트 박스 리스트 (jqgrid 내부 셀렉트박스)
        deptVO.setFlag("minja"); //민자도로사업자 세부목록 출력을 위한 flag

		List<DeptVO> deptcode = deptService.selectCptcIsttList(deptVO);
		//map.put("data",  cptcIstt);
		obj.put("list",  deptcode);

		return obj;
	}

	/**
	 * 관할구역 목록(CMPTNC_ZONE)을 조회한다.(pageing)
	 * @param cmptncVO - 조회할 정보가 담긴 CmptncVO
	 * @return 조회 리스트
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/pothole/cmptnc/selectCmptncInfoList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCmptncListRest(@RequestBody CmptncVO cmptncVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cmptncVO.getPage());
		paginationInfo.setRecordCountPerPage(cmptncVO.getPageUnit());
		paginationInfo.setPageSize(cmptncVO.getRows());
		cmptncVO.setUsePage(true);

		cmptncVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cmptncVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cmptncVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CmptncVO> items = cmptncService.selectCmptncList(cmptncVO);
		int total_count = cmptncService.selectCmptncListTotalCount(cmptncVO);

		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cmptncVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cmptncVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);
		map.put("search", cmptncVO);

		return map;
	}

	@RequestMapping(value="/api/cmptnc/cmptncListExcel.do")
    public View cmptncListExcel(@ModelAttribute CmptncVO cmptncVO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cmptncService.selectCmptncListExcel(cmptncVO);

        String[] excel_title  = {"관할기관","담당자","연락처","면적(㎡)"};
        String[] excel_column = {"LOWEST_DEPT_NM","CHARGER_NM","CTTPC","AR"};

        model.addAttribute("file_name",    "관할구역 상세조회_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/*
	@RequestMapping(value = { "/cmptnc/updateCmptnc.do" })
	public String updateCmptnc(@ModelAttribute("searchVO") CmptncVO cmptncVO) throws Exception {

		@RequestMapping(value = { "/cmptnc/updateCmptnc.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> updateCmptnc(@RequestBody CmptncVO cmptncVO, ModelMap model,HttpServletRequest request, HttpSession session) throws Exception {
	*/


	/*
	 * 관할구역 목록(CMPTNC_ZONE)을 수정한다.
	 * @author    : yyk
     * @date      : 2018. 1. 23.
	 * @param cmptncVO - 수정할 정보가 담긴 CmptncVO
	 * @exception Exception
	 */
    @RequestMapping(value = { "/pothole/cmptnc/updateCmptnc.do" })
	public String updateCmptnc(@ModelAttribute("searchVO") CmptncVO cmptncVO) throws Exception {
		BindBeansToActiveUser(cmptncVO);

		int result = cmptncService.updateCmptnc(cmptncVO);

		if(result > 0){
			cmptncService.insertCmptncLog(cmptncVO);
			cmptncVO.setResultSuccess("true");
			cmptncVO.setResultMSG("정상 수정되었습니다.");
		}
		else {
			cmptncVO.setResultMSG("실패하였습니다..");
		}
		return "jsonView";
	}



}
