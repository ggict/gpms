

package kr.go.gg.gpms.flawcntrwk.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cntrwkdtl.service.CntrwkDtlService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.flaw.service.FlawService;
import kr.go.gg.gpms.flaw.service.model.FlawVO;
import kr.go.gg.gpms.flawcntrwk.service.FlawCntrwkService;
import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;
import kr.go.gg.gpms.pavmatrl.service.PavMatrlService;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : FlawCntrwkController.java
 * @Description : FlawCntrwk Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("flawCntrwkController")
public class FlawCntrwkController  extends BaseController {

	@Resource(name = "flawCntrwkService")
	private FlawCntrwkService flawCntrwkService;

	@Resource(name = "pavMatrlService")
	private PavMatrlService pavMatrlService;

	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	@Resource(name = "flawService")
	private FlawService flawService;

	@Resource(name = "cntrwkDtlService")
	private CntrwkDtlService cntrwkDtlService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FlawCntrwkController.class);

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다. (pageing)
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/flawcntrwk/selectFlawCntrwkList.do" })
	public String selectFlawCntrwkList(FlawCntrwkVO flawCntrwkVO, ModelMap model) throws Exception {
		/*flawCntrwkVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawCntrwkVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawCntrwkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawCntrwkVO.getPageUnit());
		paginationInfo.setPageSize(flawCntrwkVO.getPageSize());

		flawCntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawCntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawCntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawCntrwkVO> items = flawCntrwkService.selectFlawCntrwkList(flawCntrwkVO);
		model.addAttribute("items", items);

		int totCnt = flawCntrwkService.selectFlawCntrwkListTotalCount(flawCntrwkVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("flawCntrwkVO", flawCntrwkVO);*/

		return "/flawcntrwk/flawcntrwkList" ;
	}


	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다. (pageing)
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/flawcntrwk/selectFlawCntrwkList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<FlawCntrwkVO> selectFlawCntrwkListRest(@RequestBody FlawCntrwkVO flawCntrwkVO, ModelMap model, HttpSession session) throws Exception {
		flawCntrwkVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawCntrwkVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawCntrwkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawCntrwkVO.getPageUnit());
		paginationInfo.setPageSize(flawCntrwkVO.getPageSize());

		flawCntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawCntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawCntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawCntrwkVO> items = flawCntrwkService.selectFlawCntrwkList(flawCntrwkVO);
		return items;
	}


	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다. (pageing)
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/flawcntrwk/selectFlawCntrwkListPage.do" }, method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectFlawCntrwkListPage(@RequestBody FlawCntrwkVO flawCntrwkVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawCntrwkVO.getPage());
		paginationInfo.setRecordCountPerPage(flawCntrwkVO.getPageUnit());
		paginationInfo.setPageSize(flawCntrwkVO.getRows());

		flawCntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawCntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawCntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		//flawCntrwkVO.setCNTRWK_ID(request.getParameter("CNTRWK_ID"));
		List<FlawCntrwkVO> items = flawCntrwkService.selectFlawCntrwkList(flawCntrwkVO);
		int total_count = flawCntrwkService.selectFlawCntrwkListTotalCount(flawCntrwkVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) flawCntrwkVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", flawCntrwkVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다. (pageing)
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/flawcntrwk/selectFlawCntrwkListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<FlawCntrwkVO>  selectFlawCntrwkListPageRest(@RequestBody  FlawCntrwkVO flawCntrwkVO, ModelMap model, HttpSession session) throws Exception {
		flawCntrwkVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		flawCntrwkVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(flawCntrwkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(flawCntrwkVO.getPageUnit());
		paginationInfo.setPageSize(flawCntrwkVO.getPageSize());

		flawCntrwkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		flawCntrwkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		flawCntrwkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<FlawCntrwkVO> items = flawCntrwkService.selectFlawCntrwkList(flawCntrwkVO);
		return items;
	}



	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 상세를 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/flawcntrwk/selectFlawCntrwk.do"  })
	public String selectFlawCntrwk(@ModelAttribute("searchVO") FlawCntrwkVO flawCntrwkVO, FlawVO flawVO, PavMatrlVO pavMatrlVO, RpairMthdVO rpairMthdVO, CntrwkDtlVO cntrwkDtlVO, AttachFileVO attachFileVO, ModelMap model) throws Exception {
		//포장 재료
		pavMatrlVO.setUSE_AT("Y");
		pavMatrlVO.setDELETE_AT("N");
		pavMatrlVO.setUsePage(false);
		List<PavMatrlVO> pavMatrlList = pavMatrlService.selectPavMatrlList(pavMatrlVO);

		//포장 공법
		rpairMthdVO.setUSE_AT("Y");
		rpairMthdVO.setDELETE_AT("N");
		rpairMthdVO.setUsePage(false);
		List<RpairMthdVO> rpairMthdList = rpairMthdService.selectRpairMthdList(rpairMthdVO);

		//세부 공사 정보
		List<CntrwkDtlVO> cntrwkDtlList = cntrwkDtlService.selectCntrwkDtlNmListByCntrwkID(cntrwkDtlVO);

		//하자 정보
		flawVO = flawService.selectFlawByCntrwkId(flawVO);

		flawCntrwkVO = flawCntrwkService.selectFlawCntrwk(flawCntrwkVO);

		model.addAttribute("pavMatrlList", pavMatrlList);
		model.addAttribute("rpairMthdList", rpairMthdList);
		model.addAttribute("cntrwkDtlList", cntrwkDtlList);
		model.addAttribute("flawCntrwkVO", flawCntrwkVO);
		model.addAttribute("flawVO", flawVO);
		return "/flawcntrwk/flawcntrwkView";
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 상세를 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return "/manage/flawcntrwk/FlawCntrwkView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/flawcntrwk/selectFlawCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawCntrwkVO selectFlawCntrwkRest(@RequestBody  FlawCntrwkVO flawCntrwkVO, ModelMap model, HttpSession session) throws Exception {
		FlawCntrwkVO flawCntrwkVOOne = flawCntrwkService.selectFlawCntrwk(flawCntrwkVO);
		return flawCntrwkVOOne;
	}



	@RequestMapping(value = { "/flawcntrwk/addFlawCntrwkView.do" })
	public String addFlawCntrwkView(@ModelAttribute("searchVO") FlawCntrwkVO flawCntrwkVO, FlawVO flawVO, PavMatrlVO pavMatrlVO, RpairMthdVO rpairMthdVO, CntrwkDtlVO cntrwkDtlVO, ModelMap model) throws Exception {

		//포장 재료
		pavMatrlVO.setUSE_AT("Y");
		pavMatrlVO.setDELETE_AT("N");
		pavMatrlVO.setUsePage(false);
		List<PavMatrlVO> pavMatrlList = pavMatrlService.selectPavMatrlList(pavMatrlVO);

		//포장 공법
		rpairMthdVO.setUSE_AT("Y");
		rpairMthdVO.setDELETE_AT("N");
		rpairMthdVO.setUsePage(false);
		List<RpairMthdVO> rpairMthdList = rpairMthdService.selectRpairMthdList(rpairMthdVO);

		//세부 공사 정보
		List<CntrwkDtlVO> cntrwkDtlList = cntrwkDtlService.selectCntrwkDtlNmListByCntrwkID(cntrwkDtlVO);

		//하자 정보
		flawVO = flawService.selectFlawByCntrwkId(flawVO);

		model.addAttribute("pavMatrlList", pavMatrlList);
		model.addAttribute("rpairMthdList", rpairMthdList);
		model.addAttribute("cntrwkDtlList", cntrwkDtlList);
		model.addAttribute("flawCntrwkVO", flawCntrwkVO);
		model.addAttribute("flawVO", flawVO);

		//공사 업체
		model.addAttribute("companyList", getCompanyList());

		return "/flawcntrwk/flawcntrwkRegist";
	}

	@RequestMapping(value = { "/api/flawcntrwk/selectCntrwkDtl.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CntrwkDtlVO> selectCntrwkDtl(@RequestBody CntrwkDtlVO cntrwkDtlVO, FlawCntrwkVO flawCntrwkVO, HttpSession session) throws Exception {

		//세부 공사 정보
		List<CntrwkDtlVO> cntrwkDtl = cntrwkDtlService.selectCntrwkDtlListByCntrwkID(cntrwkDtlVO);

		return cntrwkDtl;
	}

	@RequestMapping(value = { "/api/flawcntrwk/addFlawCntrwk.do"  }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String addFlawCntrwk(@ModelAttribute("searchVO") FlawCntrwkVO flawCntrwkVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {

		String resultCode = "";
		String resultMsg = "";
		String funCallback = flawCntrwkVO.getCallBackFunction() == null ? ""
				: flawCntrwkVO.getCallBackFunction();

		flawCntrwkVO.setOPERT_BFE_PHOTO_NO(getFileNo(request, "file_before", "flawCntrwk"));

		flawCntrwkVO.setOPERT_AFT_PHOTO_NO(getFileNo(request, "file_after", "flawCntrwk"));

		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkService.insertFlawCntrwk(flawCntrwkVO);
		resultCode = "SAVE_SUCCESS";
		resultMsg = "정상 등록되었습니다.";

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수
		return "/cmmn/commonMsg";
	}




	@RequestMapping(value = { "/flawcntrwk/updateFlawCntrwkView.do" })
	public String updateFlawCntrwkView(@ModelAttribute("searchVO") FlawCntrwkVO flawCntrwkVO, FlawVO flawVO, PavMatrlVO pavMatrlVO, RpairMthdVO rpairMthdVO, CntrwkDtlVO cntrwkDtlVO, ModelMap model) throws Exception {
		//포장 재료
		pavMatrlVO.setUSE_AT("Y");
		pavMatrlVO.setDELETE_AT("N");
		pavMatrlVO.setUsePage(false);
		List<PavMatrlVO> pavMatrlList = pavMatrlService.selectPavMatrlList(pavMatrlVO);

		//포장 공법
		rpairMthdVO.setUSE_AT("Y");
		rpairMthdVO.setDELETE_AT("N");
		rpairMthdVO.setUsePage(false);
		List<RpairMthdVO> rpairMthdList = rpairMthdService.selectRpairMthdList(rpairMthdVO);

		//세부 공사 정보
		List<CntrwkDtlVO> cntrwkDtlList = cntrwkDtlService.selectCntrwkDtlNmListByCntrwkID(cntrwkDtlVO);

		//하자 정보
		flawVO = flawService.selectFlawByCntrwkId(flawVO);

		flawCntrwkVO = flawCntrwkService.selectFlawCntrwk(flawCntrwkVO);

		//공사 업체
		model.addAttribute("companyList", getCompanyList());

		model.addAttribute("pavMatrlList", pavMatrlList);
		model.addAttribute("rpairMthdList", rpairMthdList);
		model.addAttribute("cntrwkDtlList", cntrwkDtlList);
		model.addAttribute("flawCntrwkVO", flawCntrwkVO);
		model.addAttribute("flawVO", flawVO);
		return "/flawcntrwk/flawcntrwkUpdate";
	}

	@RequestMapping(value = { "/api/flawcntrwk/updateFlawCntrwk.do" }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String updateFlawCntrwk(FlawCntrwkVO flawCntrwkVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		String resultCode = "";
		String resultMsg = "";
		String funCallback = flawCntrwkVO.getCallBackFunction() == null ? ""
				: flawCntrwkVO.getCallBackFunction();

		String fileNo_before = getFileNo(request, "file_before", "flawCntrwk");
		if(fileNo_before != null){
			flawCntrwkVO.setOPERT_BFE_PHOTO_NO(fileNo_before);
		}

		String fileNo_after = getFileNo(request, "file_after", "flawCntrwk");
		if(fileNo_after != null){
			flawCntrwkVO.setOPERT_AFT_PHOTO_NO(fileNo_after);
		}

		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkService.updateFlawCntrwk(flawCntrwkVO);
		resultCode = "UPDATE_SUCCESS";
		resultMsg = "정상 수정되었습니다.";

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수
		return "/cmmn/commonMsg";
	}

	@RequestMapping(value = {  "/api/flawcntrwk/updateFlawCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawCntrwkVO updateFlawCntrwkRest(@RequestBody FlawCntrwkVO flawCntrwkVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkService.updateFlawCntrwk(flawCntrwkVO);
		flawCntrwkVO.setResultSuccess("true");
		flawCntrwkVO.setResultMSG("정상 수정되었습니다.");
		return flawCntrwkVO;
	}

	@RequestMapping(value = { "/flawcntrwk/deleteFlawCntrwk.do" })
	public String deleteFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkService.deleteFlawCntrwk(flawCntrwkVO);
		flawCntrwkVO.setResultSuccess("true");
		flawCntrwkVO.setResultMSG("정상 삭제되었습니다.");
		return "redirect:/flawcntrwk/selectFlawCntrwkList.do";
	}

	@RequestMapping(value = {   "/api/flawcntrwk/deleteFlawCntrwk.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody FlawCntrwkVO deleteFlawCntrwkRest(@RequestBody FlawCntrwkVO flawCntrwkVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(flawCntrwkVO);
		flawCntrwkService.deleteFlawCntrwk(flawCntrwkVO);
		flawCntrwkVO.setResultSuccess("true");
		flawCntrwkVO.setResultMSG("정상 삭제되었습니다.");
		return flawCntrwkVO;
	}

	@RequestMapping(value="/flawcntrwk/downloadexcel.do")
    public View downloadexcel(@ModelAttribute FlawCntrwkVO flawCntrwkVO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
		List dataList = flawCntrwkService.selectFlawCntrwkListExcel(flawCntrwkVO);

        String[] excel_title  = {"노선번호","노선명","행선","차로","보수공법","보수재료","보수일자","연장(m)","폭(m)","면적(㎡)","보수표층두께(cm)","보수중간층두께(cm)","보수기층두께(cm)"};
        String[] excel_column = {"route_code","route_nm","direct_nm","track","rpair_mthd_nm","pav_matrl_ascon_nm","rpair_de","rpair_len","rpair_bt","rpair_ar","rpair_thick_ascon","rpair_thick_cntr","rpair_thick_base"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "하자보수공사목록_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }
}
