package kr.go.gg.gpms.pothole.sttst.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.pothole.sttst.service.SttstService;
import kr.go.gg.gpms.pothole.sttst.service.model.SttstVO;

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

import egovframework.cmmn.util.ExcelView;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 통계 Controller
 * @Classname : SttstController.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@Controller("sttstController")
public class SttstController extends BaseController {

	@Resource(name = "sttstService")
	private SttstService sttstService;

	@Resource(name = "deptService")
	private DeptService deptService;

    @Resource(name = "codeService")
    private CodeService codeService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SttstController.class);

	/**
	 * 통계 관할기관기준 처리현황 페이지를 조회한다.
	 * @author    : JOY
	 * @date      : 2018. 1. 22.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : "/pothole/sttst/byDate/datePeriodList"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/pothole/sttst/selectDatePeriodList.do" })
	public String selectDatePeriodList(@ModelAttribute("searchVO") SttstVO sttstVO, ModelMap model) throws Exception {

        // 관할기관
        DeptVO deptVO = new DeptVO();
        List<DeptVO> deptList = deptService.selectCptcIsttList(deptVO);
        model.addAttribute("deptList", deptList);

        // 접수경로
        CodeVO codeVO_TWO = new CodeVO();
        codeVO_TWO.setCL_CODE("PRRT");
        codeVO_TWO.setSidx("PRIOR_RANK");
        codeVO_TWO.setUsePage(false);
        List<CodeVO> prrtList = codeService.selectCodeList(codeVO_TWO);
        model.addAttribute("prrtList", prrtList);

        // 처리상태
        CodeVO codeVO_ONE = new CodeVO();
        codeVO_ONE.setCL_CODE("PRCS");
        codeVO_ONE.setSidx("CODE_VAL");
        codeVO_ONE.setUsePage(false);
        List<CodeVO> prcsList = codeService.selectCodeList(codeVO_ONE);
        model.addAttribute("prcsList", prcsList);

        // 최소 최대 날짜
        SttstVO sttstVO_ONE = sttstService.selectMinMaxYear();
        model.addAttribute("minyear", sttstVO_ONE.getMIN_YEAR());
        model.addAttribute("maxyear", sttstVO_ONE.getMAX_YEAR());


		return "/pothole/sttst/bydate/datePeriodList" ;
	}


    /**
     * 통계 날짜 기준 처리현황 목록을 조회한다.
     * @author    : YYK
     * @date      : 2018. 1. 26.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : map
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttst/selectDatePeriodList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectDatePeriodListRest(@RequestBody  SttstVO sttstVO, ModelMap model, HttpSession session) throws Exception {

        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        // 접수경로별, 기간별, 월별, 분기별 조회
    	if ( sttstVO.getPRCS_STTUS() == null || sttstVO.getPRCS_STTUS().isEmpty() ){
    		if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
	    		// 전체
				List<SttstVO> items = sttstService.selectDatePeriodListRest(sttstVO);
	            int total_count = sttstService.selectDatePeriodListRestTotalCount(sttstVO);

	            map.put("records", total_count);
	            map.put("rows", items);
    		} else {
    			// 접수경로별
				List<SttstVO> items = sttstService.selectDatePeriodListRestPthMode(sttstVO);
	            int total_count = sttstService.selectDatePeriodListRestPthModeTotalCount(sttstVO);

	            map.put("records", total_count);
	            map.put("rows", items);
    		}
    	}
        // 연별 + 처리상태별 조회
    	else {
    		if ( sttstVO.getPRCS_STTUS().equals("PRCS0000") ){
    			sttstVO.setPRCS_STTUS("");
    		}
    		if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
	    		// 전체
    			List<SttstVO> items = sttstService.selectDatePeriodListRestYear(sttstVO);
        		int total_count = sttstService.selectDatePeriodListRestYearTotalCount(sttstVO);

        		map.put("records", total_count);
                map.put("rows", items);
    		} else {
    			// 접수경로별
    			List<SttstVO> items = sttstService.selectDatePeriodListRestPthModeYear(sttstVO);
        		int total_count = sttstService.selectDatePeriodListRestYearTotalPthModeCount(sttstVO);

        		map.put("records", total_count);
                map.put("rows", items);
    		}
    	}

        return map;
    }


    /**
     * 통계 날짜 기준 처리현황 목록을 엑셀파일로 저장한다.
     * @author    : YYK
     * @date      : 2018. 1. 26.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : map
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/api/pothole/sttst/selectDatePeriodListExcel.do")
    public View selectDatePeriodListExcel(@ModelAttribute SttstVO sttstVO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {

    	List dataList = null;

		if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
    		// 전체
			dataList = sttstService.selectDatePeriodListRestExcel(sttstVO);

			if ( dataList.size() == 0 ) {
	            return null;
	        }

			String[] excel_title  = {"관할기관","전체신고(건)","신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율(처리완료÷신고)"};
	        String[] excel_column = {"LOWEST_DEPT_NM", "PRCS_SUM", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7", "PERCENT" };

	        model.addAttribute("file_name",    "통계_관할기관별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
	        model.addAttribute("excel_title",  excel_title);
	        model.addAttribute("excel_column", excel_column);
	        model.addAttribute("data_list",    dataList);
		} else {
			// 접수경로별
			dataList = sttstService.selectDatePeriodListRestExcelPthMode(sttstVO);

			if ( dataList.size() == 0 ) {
	            return null;
	        }

			String[] excel_title  = {"관할기관","전체신고(건)","신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율(처리완료÷신고)"};
	        String[] excel_column = {"LOWEST_DEPT_NM", "PRCS_SUM", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7", "PERCENT" };

	        model.addAttribute("file_name",    "통계_접수경로별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
	        model.addAttribute("excel_title",  excel_title);
	        model.addAttribute("excel_column", excel_column);
	        model.addAttribute("data_list",    dataList);
		}
        return new ExcelView();
    }


    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/api/pothole/sttst/selectDatePeriodListExcelYear.do")
    public View selectDatePeriodListExcelYear(@ModelAttribute SttstVO sttstVO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {

    	List dataList = null;

    	if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
    		// 전체
    		dataList = sttstService.selectDatePeriodListRestYearExcel(sttstVO);

    		if ( dataList.size() == 0 ) {
                return null;
            }

            String[] excel_title  = {"연도", "전체신고(건)", "신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)"};
            String[] excel_column = {"STTEMNT_DT","PRCS_COUNT", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7" };


            if (sttstVO.getSTTEMNT_DT_START() == null || sttstVO.getSTTEMNT_DT_START().isEmpty()){
            	model.addAttribute("file_name",    "통계_관할기관별_전체기간" );
    		}
            else {
            	model.addAttribute("file_name",    "통계_관할기관별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
            }
            model.addAttribute("excel_title",  excel_title);
            model.addAttribute("excel_column", excel_column);
            model.addAttribute("data_list",    dataList);
		} else {
			// 접수경로별
			dataList = sttstService.selectDatePeriodListRestYearPthModeExcel(sttstVO);

			if ( dataList.size() == 0 ) {
	            return null;
	        }

	        String[] excel_title  = {"연도", "전체신고(건)", "신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)"};
	        String[] excel_column = {"STTEMNT_DT","PRCS_COUNT", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7" };


	        if (sttstVO.getSTTEMNT_DT_START() == null || sttstVO.getSTTEMNT_DT_START().isEmpty()){
	        	model.addAttribute("file_name",    "통계_접수기관별_전체기간" );
			}
	        else {
	        	model.addAttribute("file_name",    "통계_접수기관별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
	        }
	        model.addAttribute("excel_title",  excel_title);
	        model.addAttribute("excel_column", excel_column);
	        model.addAttribute("data_list",    dataList);
		}

        return new ExcelView();
    }




    // ========================================== 신고자기준 ==========================================
	/**
     * 통계 신고자기준 신고현황 페이지를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 22.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : "/pothole/sttst/byBsnm/bsnmPeriodList"
     * @exception : Exception
     */
    @RequestMapping(value = { "/pothole/sttst/selectBsnmPeriodList.do" })
    public String selectBsnmPeriodList(@ModelAttribute("searchVO") SttstVO sttstVO, ModelMap model) throws Exception {

        SttstVO sttstVO_ONE = sttstService.selectMinMaxYear();
        model.addAttribute("minyear", sttstVO_ONE.getMIN_YEAR());
        model.addAttribute("maxyear", sttstVO_ONE.getMAX_YEAR());

        // 접수경로
        CodeVO codeVO_TWO = new CodeVO();
        codeVO_TWO.setCL_CODE("MTDT");
        codeVO_TWO.setSidx("PRIOR_RANK");
        codeVO_TWO.setUsePage(false);
        List<CodeVO> mtdtList = codeService.selectCodeList(codeVO_TWO);
        model.addAttribute("mtdtList", mtdtList);

        return "/pothole/sttst/bybsnm/bsnmPeriodList" ;
    }

    /**
     * 통계 신고자기준 신고현황 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 25.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : map
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttst/selectBsnmPeriodList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectBsnmPeriodListRest(@RequestBody  SttstVO sttstVO, ModelMap model, HttpSession session) throws Exception {

    	// 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		if ( sttstVO.getPRCS_STTUS() == null || sttstVO.getPRCS_STTUS().isEmpty() ){
        		if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
    	    		// 전체
        			List<SttstVO> items = sttstService.selectBsnmPeriodListRest(sttstVO);
        	        int total_count = sttstService.selectBsnmPeriodListRestTotalCount(sttstVO);

        	        map.put("records", total_count);
        	        map.put("rows", items);
        		} else {
        			// 접수경로 신고자별
        			List<SttstVO> items = sttstService.selectBsnmPeriodListPthModeRest(sttstVO);
        	        int total_count = sttstService.selectBsnmPeriodListRestTotalPthModeCount(sttstVO);

        	        map.put("records", total_count);
        	        map.put("rows", items);
        		}
        	}

	    } catch(SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch(Exception e ) {
			e.printStackTrace();
		}

    	return map;
    }

    /**
     * 통계 신고자기준 신고현황 목록 엑셀을 다운로드한다.
     * @author    : JOY
     * @date      : 2018. 1. 25.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : View
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/api/pothole/sttst/selectBsnmPeriodListExcel.do")
    public View selectBsnmPeriodListExcel(@ModelAttribute SttstVO sttstVO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {

    	if ( sttstVO.getPTHMODE() == null || sttstVO.getPTHMODE().isEmpty() ){
    		// 전체
    		List dataList = sttstService.selectBsnmPeriodListRestExcel(sttstVO);

            if ( dataList.size() == 0 ) {

                return null;
            }

            String[] excel_title  = {"지역구분","신고자","차량번호","전체신고(건)","신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율(처리완료÷전체)"};
            String[] excel_column = {"LOWEST_DEPT_NM","BSNM_NM", "VHCLE_NO", "PRCS_STTUS", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7", "PERCENT" };

            model.addAttribute("file_name",    "통계_신고자별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
            model.addAttribute("excel_title",  excel_title);
            model.addAttribute("excel_column", excel_column);
            model.addAttribute("data_list",    dataList);
		} else {
			// 접수경로 신고자별
			List dataList = sttstService.selectBsnmPeriodListRestPthModeExcel(sttstVO);

	        if ( dataList.size() == 0 ) {

	            return null;
	        }

	        String[] excel_title  = {"지역구분","신고자","차량번호","전체신고(건)","신고(건)","접수(건)","처리완료(건)","보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율(처리완료÷전체)"};
	        String[] excel_column = {"LOWEST_DEPT_NM","BSNM_NM", "VHCLE_NO", "PRCS_STTUS", "PRCS_STTUS1", "PRCS_STTUS2", "PRCS_STTUS3", "PRCS_STTUS4", "PRCS_STTUS5", "PRCS_STTUS6", "PRCS_STTUS7", "PERCENT" };

	        model.addAttribute("file_name",    "통계_신고자별_" + sttstVO.getSTTEMNT_DT_START() + "~" + sttstVO.getSTTEMNT_DT_END());
	        model.addAttribute("excel_title",  excel_title);
	        model.addAttribute("excel_column", excel_column);
	        model.addAttribute("data_list",    dataList);
		}

        return new ExcelView();
    }



}
