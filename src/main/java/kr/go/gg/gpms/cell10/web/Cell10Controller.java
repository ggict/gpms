


package kr.go.gg.gpms.cell10.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cell10.service.Cell10Service;
import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.Region;
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
import egovframework.cmmn.util.NumberUtil;
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

@Controller("cell10Controller")
public class Cell10Controller extends BaseController {

	@Resource(name = "cell10Service")
	private Cell10Service cell10Service;

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	private static final Logger LOGGER = LoggerFactory.getLogger(Cell10Controller.class);

	/**
	 * CELL_10(CELL_10) 목록을 조회한다. (pageing)
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return "/cell10/Cell10List"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cell10/selectCell10List.do" })
	public String selectCell10List(@ModelAttribute("searchVO") Cell10VO cell10VO, ModelMap model) throws Exception {
		//도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");

		//셀 종류 구분
		List<CodeVO> celtList = getCodeList("CELT");

		//교통 용량
		List<CodeVO> vntcList = getCodeList("VNTC");

		//관리주체
		model.addAttribute("deptList", deptService.selectCntrwkDeptList(new DeptVO()));

		//행정구역
		model.addAttribute("admList", cmmnService.selectAdmCodeList(new CodeVO()));

		//노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("celtList", celtList);
		model.addAttribute("vntcList", vntcList);
		model.addAttribute("roadNoList", roadNoList);

		return "/cell10/cell10List" ;
	}

	/**
	 * CELL_10(CELL_10) 목록을 조회한다. (pageing)
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return "/cell10/Cell10List"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cell10/selectCell10List.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectCell10ListRest(@RequestBody Cell10VO cell10VO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cell10VO.getPage());
		paginationInfo.setRecordCountPerPage(cell10VO.getPageUnit());
		paginationInfo.setPageSize(cell10VO.getRows());
		cell10VO.setUsePage(true);

		cell10VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cell10VO.setLastIndex(paginationInfo.getLastRecordIndex());
		cell10VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<Cell10VO> items = cell10Service.selectCell10List(cell10VO);
		int total_count = cell10Service.selectCell10ListTotalCount(cell10VO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cell10VO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cell10VO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}

	@RequestMapping(value = { "/api/cell10/selectRouteInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<Cell10VO> selectRouteInfoByCellID(@RequestBody Cell10VO cell10VO, ModelMap model) throws Exception {

		List<Cell10VO> items = cell10Service.selectCell10List(cell10VO);
		return items;
	}

	/**
	 * CELL_10(CELL_10) 상세를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return "/cell10/Cell10View"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cell10/selectCell10.do" })
	public String selectCell10(@ModelAttribute("searchVO") Cell10VO cell10VO, ModelMap model) throws Exception {

		model.addAttribute("cell10VO", cell10Service.selectCell10(cell10VO));
		return "/cell10/cell10View";
	}


	@RequestMapping(value = { "/api/cell10/selectRouteInfos.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<Cell10VO> selectRouteInfosByCellID(@RequestBody Cell10VO cell10VO, ModelMap model) throws Exception {

		List<Cell10VO> items = cell10Service.selectRouteInfoByCellID(cell10VO);
		return items;
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 노선별/관리기관별/도로등급별 통계 테이블 목록을 조회한다.
	 * @return "/stats/srvy/surveyAllLenStatsTable"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyAllLenStatsTable.do" })
	public String selectSurveyAllLenStatsTable(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/srvy/surveyAllLenStatsTable" ;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별/관리기관별/도로등급별 통계 테이블 엑셀목록을 조회한다.
	 * @return "/stats/srvy/surveyAllLenStatsTable"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyAllLenStatsExcel.do" })
	public void selectSurveyAllLenStatsExcel(Cell10VO cell10VO, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		 //***************************** 데이터값 호출을 위한 기본 셋팅
	   	 HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	 HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	 List result	= new ArrayList();
    	 List resultDept = new ArrayList();
    	 List resultRout = new ArrayList();
    	 HashMap<String,Object> tempMap = new HashMap<String,Object>();

    	 Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   	 HSSFWorkbook objWorkBook = new HSSFWorkbook();
	   	 HSSFSheet objSheet = null;
	   	 HSSFRow objRow = null;
	   	 HSSFCell objCell = null;       //셀 생성

	     //제목 폰트
	   	 HSSFFont font = objWorkBook.createFont();
	   	 font.setFontHeightInPoints((short)9);
	   	 font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
	   	 font.setFontName("맑은고딕");

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
         styleHd.setFont(font);
         styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd.setBorderLeft((short)1);
         styleHd.setBorderBottom((short)1);
         styleHd.setBorderTop((short)1);
         styleHd.setBorderRight((short)1);

         objSheet = objWorkBook.createSheet("통계");     //워크시트 생성

         objRow = objSheet.createRow(0);
         objCell = objRow.createCell(0);
         objCell.setCellValue("노선별 조사거리 통계");

         result = cell10Service.selectSrvyRoutLenStatsExcelResult(cell10VO);

         objRow = objSheet.createRow(1);
         objCell = objRow.createCell(0);
         objCell.setCellValue("노선번호");
         objCell.setCellStyle(styleHd);

         objCell = objRow.createCell(1);
         objCell.setCellValue("노선명");
         objCell.setCellStyle(styleHd);

         objCell = objRow.createCell(2);
         objCell.setCellValue("연장(km)");
         objCell.setCellStyle(styleHd);


         for(int i=0; i<result.size(); i++){
        	 tempMap = (HashMap) result.get(i);

        	 objRow = objSheet.createRow(i+2);
             objCell = objRow.createCell(0);
             objCell.setCellValue(tempMap.get("route_code").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(1);
             objCell.setCellValue(tempMap.get("road_name").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("len")));
             objCell.setCellStyle(styleHd);
         }

         resultDept =  cell10Service.selectSrvyDeptLenStatsExcel(cell10VO);

         int rowDept = result.size();
         objRow = objSheet.createRow(++rowDept);
         objCell = objRow.createCell(0);
         objCell.setCellValue("관리기관명");
         objCell.setCellStyle(styleHd);

         objCell = objRow.createCell(1);
         objCell.setCellValue("연장(km)");
         objCell.setCellStyle(styleHd);

         for(int i=0; i<resultDept.size(); i++){
        	 tempMap = (HashMap) resultDept.get(i);

        	 objRow = objSheet.createRow(++rowDept);
             objCell = objRow.createCell(0);
             objCell.setCellValue(tempMap.get("dept_nm").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(1);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("len")));
             objCell.setCellStyle(styleHd);
         }


         resultRout = cell10Service.selectSrvyRoadLenStatsExcel(cell10VO);

         int rowRout = (result.size() + resultDept.size())+1;
         objRow = objSheet.createRow(++rowRout);
         objCell = objRow.createCell(0);
         objCell.setCellValue("도로등급명");
         objCell.setCellStyle(styleHd);

         objCell = objRow.createCell(1);
         objCell.setCellValue("연장(km)");
         objCell.setCellStyle(styleHd);

         for(int i=0; i<resultRout.size(); i++){
        	 tempMap = (HashMap) resultRout.get(i);

        	 objRow = objSheet.createRow(++rowRout);
             objCell = objRow.createCell(0);
             objCell.setCellValue(tempMap.get("road_name").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(1);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("len")));
             objCell.setCellStyle(styleHd);
         }

         response.setContentType("Application/Msexcel");
         response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("포장상태조사구간통계","UTF-8")+"_"+dateFormat.format(calendar.getTime())+".xls");

         OutputStream fileOut  = response.getOutputStream();
         objWorkBook.write(fileOut);
         fileOut.close();

         response.getOutputStream().flush();
         response.getOutputStream().close();
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 테이블 목록을 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/selectSrvyRoutLenStatsTablePageList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSrvyRoutLenStatsTablePageList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		cell10VO.setUsePage(true);
		List<Cell10VO> items = cell10Service.selectSrvyRoutLenStatsTablePageList(cell10VO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cell10VO.getPage());
		map.put("total", total_page);
		map.put("rows", items);

		return map;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별/관리기관별/도로등급별 통계 목록을 조회한다.
	 * @return "/stats/route/selectSurveyAllLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyAllLenStats.do" })
	public String selectSurveyAllLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/srvy/surveyAllLenStats" ;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @return "/stats/route/selectSurveyRoutLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyRoutLenStats.do" })
	public String selectSurveyRoutLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/srvy/surveyRoutLenStats" ;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 목록을 조회한다.
	 * @return "/stats/route/selectSurveyDeptLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyDeptLenStats.do" })
	public String selectSurveyDeptLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/srvy/surveyDeptLenStats" ;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 목록을 조회한다.
	 * @return "/stats/route/selectSurveyRoadLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectSurveyRoadLenStats.do" })
	public String selectSurveyRoadLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/srvy/surveyRoadLenStats" ;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectSrvyRoutLenStatsResult.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectSrvyRoutLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyRoutLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectSrvyRoutLenStatsExcel.do")
    public View selectSrvyRoutLenStatsExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cell10Service.selectSrvyRoutLenStatsExcel(cell10VO);

        String[] excel_title  = {"노선번호","노선명","연장(m)"};
        String[] excel_column = {"route_code","road_name", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "포장상태조사구간_노선별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectSrvyDeptLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectSrvyDeptLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyDeptLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectSrvyDeptLenStatsExcel.do")
    public View selectSrvyDeptLenStatsExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cell10Service.selectSrvyDeptLenStatsExcel(cell10VO);

        String[] excel_title  = {"관리기관번호","관리기관명","연장(m)"};
        String[] excel_column = {"dept_code","dept_nm", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "포장상태조사구간_관리기관별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectSrvyRoadLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectSrvyRoadLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyRoadLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectSrvyRoadLenStatsExcel.do")
    public View selectSrvyRoadLenStatsExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cell10Service.selectSrvyRoadLenStatsExcel(cell10VO);

        String[] excel_title  = {"도로등급명","연장(m)"};
        String[] excel_column = {"road_name", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "포장상태조사구간_도로등급별통계_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/************************************************* 통계 > 노선현황 > 공통(노선별/관리기관별/차로별/시군구별) **************************************************/

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectRoutLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectRoutLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectRoutLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록 엑셀을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectRoutLenStatsResultExcel.do")
    public View selectRoutLenStatsResultExcel(@ModelAttribute Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
		List dataList = cell10Service.selectRoutLenStatsResultExcel(cell10VO);

        String[] excel_title  = {"노선번호","노선명","연장(m)"};
        String[] excel_column = {"road_no","road_name", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "노선현황_노선별GPMS총연장_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cell10/selectDeptLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectDeptLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectDeptLenStatsResult(cell10VO));

		return map;
	}

	@RequestMapping(value = { "/api/cell10/selectSrvyUniDeptLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectSrvyUniDeptLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyUniDeptLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectDeptLenStatsResultExcel.do")
    public View selectDeptLenStatsResultExcel(@ModelAttribute Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
		List dataList = cell10Service.selectDeptLenStatsResultExcel(cell10VO);

        String[] excel_title  = {"관리기관명","연장(m)"};
        String[] excel_column = {"dept_nm", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "노선현황_관리기관별GPMS총연장_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통계 > 노선현황 > 도로등급 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectGradLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectGradLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectGradLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 국토부 도로등급 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectSrvyUniRoadLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectSrvyUniRoadLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyUniRoadLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 국토부 노선별 전체 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectSrvyUniRoutLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectSrvyUniRoutLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectSrvyUniRoutLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 도로등급 통계 목록 엑셀을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectGradLenStatsResultExcel.do")
    public View selectGradLenStatsResultExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
		List dataList = cell10Service.selectGradLenStatsResultExcel(cell10VO);

        String[] excel_title  = {"도로등급명","연장(m)"};
        String[] excel_column = {"ROAD_NAME", "LEN"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "노선현황_도로등급별GPMS총연장_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }
	/************************************************* 통계 > 노선현황 > 노선별 통계 **************************************************/

	/**
	 * 통계 > 노선현황 > 노선별 통계를 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectRouteStats.do" })
	public String selectRouteStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/routeLenStatsTable";
	}

	/**
	 * 통계 > 노선현황 > 노선별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRouteLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectRouteLenStats.do" })
	public String selectRouteLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/routeLenStats" ;
	}

	/**
	 * 통계 > 노선현황 > 노선별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoutStatsPageList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/selectRoutStatsPageList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectRoutStatsPageList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        cell10VO.setUsePage(true);

        List<Cell10VO> items = cell10Service.selectRoutStatsPageList(cell10VO);

        //@SuppressWarnings("unchecked")
        //List<Cell10VO> chartTotal = cell10Service.selectSrvyUniRoadLenStatsResult(cell10VO);
        //@SuppressWarnings("unchecked")
        //List<Cell10VO> chartData = cell10Service.selectSrvyUniRoutLenStatsResult(cell10VO);

        int total_page = 0;

        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", cell10VO.getPage());
        map.put("total", total_page);
        map.put("rows", items);
        //map.put("chartTotal", chartTotal);
        //map.put("chartData", chartData);

        return map;
    }

	/**
	 * 통계 > 노선현황 > 노선별 통계 테이블 엑셀목록을 조회한다.
	 * @return
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectRoutStatsExcel.do" })
	public void selectRoutStatsExcel(Cell10VO cell10VO, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		 //***************************** 데이터값 호출을 위한 기본 셋팅
	   	 HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	 HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	 List resultNjr	= new ArrayList();
    	 List resultNjrA	= new ArrayList();
    	 List resultJb = new ArrayList();
    	 List resultJbA = new ArrayList();
    	 HashMap<String,Object> tempMap = new HashMap<String,Object>();

    	 Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   	 HSSFWorkbook objWorkBook = new HSSFWorkbook();
	   	 HSSFSheet objSheet = null;
	   	 HSSFRow objRow = null;
	   	 HSSFCell objCell = null;       //셀 생성

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
         styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd.setBorderLeft((short)1);
         styleHd.setBorderBottom((short)1);
         styleHd.setBorderTop((short)1);
         styleHd.setBorderRight((short)1);

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd1 = objWorkBook.createCellStyle();    //제목 스타일
         styleHd1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd1.setBorderLeft((short)1);
         styleHd1.setBorderBottom((short)1);
         styleHd1.setBorderTop((short)1);
         styleHd1.setBorderRight((short)1);
         styleHd1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);     //색 설정
         styleHd1.setFillPattern(CellStyle.SOLID_FOREGROUND);     //색 패턴 설정

         objSheet = objWorkBook.createSheet("노선별도로연장통계");     //워크시트 생성

         objRow = objSheet.createRow(0);
         objCell = objRow.createCell(0);
         objCell.setCellValue("도로등급");
         objSheet.addMergedRegion(new Region(0,(short)0,2,(short)0));
         objSheet.setColumnWidth(0, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(1);
         objCell.setCellValue("노선번호");
         objSheet.addMergedRegion(new Region(0,(short)1,2,(short)1));
         objSheet.setColumnWidth(1, objSheet.getColumnWidth(1)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(2);
         objCell.setCellValue("노선명");
         objSheet.addMergedRegion(new Region(0,(short)2,2,(short)2));
         objSheet.setColumnWidth(2, objSheet.getColumnWidth(2)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(3);
         objCell.setCellValue("국토부(2016)");
         objSheet.addMergedRegion(new Region(0,(short)3,0,(short)5));
         objSheet.setColumnWidth(3, objSheet.getColumnWidth(3)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(6);
         objCell.setCellValue("GPMS("+calendar.get(calendar.YEAR)+")");
         objSheet.addMergedRegion(new Region(0,(short)6,0,(short)9));
         objSheet.setColumnWidth(6, objSheet.getColumnWidth(6)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(1);
         objCell = objRow.createCell(3);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)3,2,(short)3));
         objSheet.setColumnWidth(3, objSheet.getColumnWidth(3)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)4,2,(short)4));
         objSheet.setColumnWidth(4, objSheet.getColumnWidth(4)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(5);
         objCell.setCellValue("미개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)5,2,(short)5));
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(5)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(6);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)6,2,(short)6));
         objSheet.setColumnWidth(6, objSheet.getColumnWidth(6)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(7);
         objCell.setCellValue("중용구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)7,1,(short)8));
         objSheet.setColumnWidth(7, objSheet.getColumnWidth(7)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(9);
         objCell.setCellValue("미개설구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)9,2,(short)9));
         objSheet.setColumnWidth(9, objSheet.getColumnWidth(9)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(2);
         objCell = objRow.createCell(7);
         objCell.setCellValue("국지도");
         objSheet.setColumnWidth(7, objSheet.getColumnWidth(7)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(8);
         objCell.setCellValue("지방도");
         objSheet.setColumnWidth(8, objSheet.getColumnWidth(8)+4096);
         objCell.setCellStyle(styleHd1);

         resultNjr = cell10Service.selectRoutNjrStatsExcelResult(cell10VO);//국지도 노선별 연장 목록

         int iRow = 3;

         for(int i=0; i<resultNjr.size(); i++){
        	 tempMap = (HashMap) resultNjr.get(i);

        	 int a = iRow;
        	 objRow = objSheet.createRow(iRow++);

        	 if(a == 3){
        		 objCell = objRow.createCell(0);
        		 objSheet.addMergedRegion(new Region(3,(short)0,14,(short)0));
        		 objCell.setCellValue("국지도");
        		 objCell.setCellStyle(styleHd1);
        	 }

             objCell = objRow.createCell(1);
             objCell.setCellValue(tempMap.get("route_code").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(tempMap.get("route_name").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(8);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(9);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd);
         }

         resultNjrA = cell10Service.selectRoutNjrAllStatsExcelResult(cell10VO);//국지도 노선별 전체 연장

         iRow = 14;

         for(int i=0; i<resultNjrA.size(); i++){
        	 tempMap = (HashMap) resultNjrA.get(i);

        	 objRow = objSheet.createRow(iRow++);
             objCell = objRow.createCell(1);
             objCell.setCellValue("전체");
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(2);
             objCell.setCellValue(resultNjr.size()+"개 노선");
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(8);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(9);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd1);
         }

         resultJb = cell10Service.selectRoutJbStatsExcelResult(cell10VO);//지방도 노선별 연장 목록

         for(int i=0; i<resultJb.size(); i++){
        	 tempMap = (HashMap) resultJb.get(i);

        	 int a = iRow;
        	 objRow = objSheet.createRow(iRow++);

        	 if(a == 15){
                 objCell = objRow.createCell(0);
                 objSheet.addMergedRegion(new Region(15,(short)0,59,(short)0));
                 objCell.setCellValue("지방도");
                 objCell.setCellStyle(styleHd1);
        	 }

             objCell = objRow.createCell(1);
             objCell.setCellValue(tempMap.get("route_code").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(tempMap.get("route_name").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(8);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(9);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd);
         }

         resultJbA = cell10Service.selectRoutJbAllStatsExcelResult(cell10VO);//지방도 노선별 전체 연장

         for(int i=0; i<resultJbA.size(); i++){
        	 tempMap = (HashMap) resultJbA.get(i);

        	 objRow = objSheet.createRow(iRow++);
             objCell = objRow.createCell(1);
             objCell.setCellValue("전체");
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(2);
             objCell.setCellValue(resultJb.size()+"개 노선");
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(8);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(9);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd1);
         }

         response.setContentType("Application/Msexcel");
         response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("노선별도로연장통계","UTF-8")+"_"+dateFormat.format(calendar.getTime())+".xls");

         OutputStream fileOut  = response.getOutputStream();
         objWorkBook.write(fileOut);
         fileOut.close();

         response.getOutputStream().flush();
         response.getOutputStream().close();
	}
	/************************************************* 통계 > 노선현황 > 관리기관별 통계 **************************************************/

	/**
	 * 통계 > 노선현황 > 관리기관별 통계를 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectDeptStats.do" })
	public String selectDeptStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/deptLenStatsTable";
	}

	/**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @return "/stats/route/selectDeptLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectDeptLenStats.do" })
	public String selectDeptLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/deptLenStats" ;
	}

	/**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoutStatsPageList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/selectDeptStatsPageList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectDeptStatsPageList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		cell10VO.setUsePage(true);
		List<Cell10VO> items = cell10Service.selectDeptStatsPageList(cell10VO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cell10VO.getPage());
		map.put("total", total_page);
		map.put("rows", items);

		return map;
	}
	/**
	 * 통계 > 노선현황 > 노선별 통계 테이블 엑셀목록을 조회한다.
	 * @return
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectDeptStatsPageListExcel.do" })
	public void selectDeptStatsPageListExcel(Cell10VO cell10VO, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		 //***************************** 데이터값 호출을 위한 기본 셋팅
	   	 HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	 HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	 List result	= new ArrayList();
    	 HashMap<String,Object> tempMap = new HashMap<String,Object>();

    	 Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   	 HSSFWorkbook objWorkBook = new HSSFWorkbook();
	   	 HSSFSheet objSheet = null;
	   	 HSSFRow objRow = null;
	   	 HSSFCell objCell = null;       //셀 생성

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
         styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd.setBorderLeft((short)1);
         styleHd.setBorderBottom((short)1);
         styleHd.setBorderTop((short)1);
         styleHd.setBorderRight((short)1);

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd1 = objWorkBook.createCellStyle();    //제목 스타일
         styleHd1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd1.setBorderLeft((short)1);
         styleHd1.setBorderBottom((short)1);
         styleHd1.setBorderTop((short)1);
         styleHd1.setBorderRight((short)1);
         styleHd1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);     //색 설정
         styleHd1.setFillPattern(CellStyle.SOLID_FOREGROUND);     //색 패턴 설정

         objSheet = objWorkBook.createSheet("관리기관별도로연장통계");     //워크시트 생성

         objRow = objSheet.createRow(0);
         objCell = objRow.createCell(0);
         objCell.setCellValue("관리기관");
         objSheet.addMergedRegion(new Region(0,(short)0,2,(short)0));
         objSheet.setColumnWidth(0, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(1);
         objCell.setCellValue("국토부(2016)");
         objSheet.addMergedRegion(new Region(0,(short)1,0,(short)3));
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("GPMS("+calendar.get(calendar.YEAR)+")");
         objSheet.addMergedRegion(new Region(0,(short)4,0,(short)7));
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(1);
         objCell = objRow.createCell(1);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)1,2,(short)1));
         objSheet.setColumnWidth(1, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(2);
         objCell.setCellValue("개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)2,2,(short)2));
         objSheet.setColumnWidth(2, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(3);
         objCell.setCellValue("미개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)3,2,(short)3));
         objSheet.setColumnWidth(3, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)4,2,(short)4));
         objSheet.setColumnWidth(4, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(5);
         objCell.setCellValue("중용구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)5,1,(short)6));
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(7);
         objCell.setCellValue("미개설구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)7,2,(short)7));
         objSheet.setColumnWidth(7, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(2);
         objCell = objRow.createCell(5);
         objCell.setCellValue("국지도");
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(6);
         objCell.setCellValue("지방도");
         objSheet.setColumnWidth(6, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         result = cell10Service.selectDeptStatsPageListExcel(cell10VO);//관리기관별 연장 목록

         int iRow = 3;

         for(int i=0; i<result.size(); i++){
        	 tempMap = (HashMap) result.get(i);

        	 objRow = objSheet.createRow(iRow++);
    		 objCell = objRow.createCell(0);
    		 objCell.setCellValue(tempMap.get("dept_nm").toString());
    		 objCell.setCellStyle(styleHd1);

             objCell = objRow.createCell(1);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd);
         }

         response.setContentType("Application/Msexcel");
         response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("관리기관별도로연장통계","UTF-8")+"_"+dateFormat.format(calendar.getTime())+".xls");

         OutputStream fileOut  = response.getOutputStream();
         objWorkBook.write(fileOut);
         fileOut.close();

         response.getOutputStream().flush();
         response.getOutputStream().close();
	}

	/************************************************* 통계 > 노선현황 > 차로별 통계 **************************************************/

	/**
	 * 통계 > 노선현황 > 차로별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoadGradeLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectTrackLenStats.do" })
	public String selectTrackLenStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/trackLenStats" ;
	}

	/**
	 * 통계 > 노선현황 > 차로별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoadGradeLenStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectTrackStats.do" })
	public String selectTrackStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/trackLenStatsTable" ;
	}

	/**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectTrackLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectTrackLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectTrackLenStatsResult(cell10VO));

		return map;
	}

	@RequestMapping(value = {   "/api/cell10/selectUniTrackLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectUniTrackLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectUniTrackLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectTrackLenStatsResultExcel.do")
    public View selectTrackLenStatsResultExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cell10Service.selectTrackLenStatsResultExcel(cell10VO);

        String[] excel_title  = {"차로","연장(m)"};
        String[] excel_column = {"track", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "노선현황_차로별_GPMS총연장_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 통계 > 노선현황 > 차로별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoutStatsPageList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/selectTrakStatsPageList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectTrakStatsPageList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		cell10VO.setUsePage(true);
		List<Cell10VO> items = cell10Service.selectTrakStatsPageList(cell10VO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cell10VO.getPage());
		map.put("total", total_page);
		map.put("rows", items);

		return map;
	}

	/**
	 * 통계 > 노선현황 > 노선별 통계 테이블 엑셀목록을 조회한다.
	 * @return
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectTrakStatsPageListExcel.do" })
	public void selectTrakStatsPageListExcel(Cell10VO cell10VO, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		 //***************************** 데이터값 호출을 위한 기본 셋팅
	   	 HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	 HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	 List result	= new ArrayList();
    	 HashMap<String,Object> tempMap = new HashMap<String,Object>();

    	 Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   	 HSSFWorkbook objWorkBook = new HSSFWorkbook();
	   	 HSSFSheet objSheet = null;
	   	 HSSFRow objRow = null;
	   	 HSSFCell objCell = null;       //셀 생성

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
         styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd.setBorderLeft((short)1);
         styleHd.setBorderBottom((short)1);
         styleHd.setBorderTop((short)1);
         styleHd.setBorderRight((short)1);

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd1 = objWorkBook.createCellStyle();    //제목 스타일
         styleHd1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd1.setBorderLeft((short)1);
         styleHd1.setBorderBottom((short)1);
         styleHd1.setBorderTop((short)1);
         styleHd1.setBorderRight((short)1);
         styleHd1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);     //색 설정
         styleHd1.setFillPattern(CellStyle.SOLID_FOREGROUND);     //색 패턴 설정

         objSheet = objWorkBook.createSheet("차로별도로연장통계");     //워크시트 생성

         objRow = objSheet.createRow(0);
         objCell = objRow.createCell(0);
         objCell.setCellValue("시군구");
         objSheet.addMergedRegion(new Region(0,(short)0,1,(short)0));
         objSheet.setColumnWidth(0, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(1);
         objCell.setCellValue("도로등급");
         objSheet.addMergedRegion(new Region(0,(short)1,1,(short)1));
         objSheet.setColumnWidth(1, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(2);
         objCell.setCellValue("국토부(2016)");
         objSheet.addMergedRegion(new Region(0,(short)2,0,(short)7));
         objSheet.setColumnWidth(2, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(8);
         objCell.setCellValue("GPMS("+calendar.get(calendar.YEAR)+")");
         objSheet.addMergedRegion(new Region(0,(short)8,0,(short)14));
         objSheet.setColumnWidth(8, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(1);
         objCell = objRow.createCell(2);
         objCell.setCellValue("총연장(m)");
         objSheet.setColumnWidth(2, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(3);
         objCell.setCellValue("2차로");
         objSheet.setColumnWidth(3, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("4차로");
         objSheet.setColumnWidth(4, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(5);
         objCell.setCellValue("6차로");
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(6);
         objCell.setCellValue("8차로");
         objSheet.setColumnWidth(6, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(7);
         objCell.setCellValue("10차로");
         objSheet.setColumnWidth(7, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(8);
         objCell.setCellValue("총연장(m)");
         objSheet.setColumnWidth(8, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(9);
         objCell.setCellValue("2차로");
         objSheet.setColumnWidth(9, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(10);
         objCell.setCellValue("4차로");
         objSheet.setColumnWidth(10, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(11);
         objCell.setCellValue("6차로");
         objSheet.setColumnWidth(11, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(12);
         objCell.setCellValue("8차로");
         objSheet.setColumnWidth(12, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(13);
         objCell.setCellValue("10차로");
         objSheet.setColumnWidth(13, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(14);
         objCell.setCellValue("중용구간(m)");
         objSheet.setColumnWidth(14, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);


         result = cell10Service.selectTrakStatsPageListExcel(cell10VO);//차로별 연장 목록

         int iRow = 2;

         for(int i=0; i<result.size(); i++){
        	 tempMap = (HashMap) result.get(i);

        	 objRow = objSheet.createRow(iRow++);
    		 objCell = objRow.createCell(0);
			 objCell.setCellValue(tempMap.get("adm_nm").toString());
    		 objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(1);
             objCell.setCellValue(tempMap.get("road_grad").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track2_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track4_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track6_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track8_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track10_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(8);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(9);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track2")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(10);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track4")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(11);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track6")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(12);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track8")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(13);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("track10")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(14);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jr_len")));
             objCell.setCellStyle(styleHd);
         }

         response.setContentType("Application/Msexcel");
         response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("차로별도로연장통계","UTF-8")+"_"+dateFormat.format(calendar.getTime())+".xls");

         OutputStream fileOut  = response.getOutputStream();
         objWorkBook.write(fileOut);
         fileOut.close();

         response.getOutputStream().flush();
         response.getOutputStream().close();
	}
	/************************************************* 통계 > 노선현황 > 시군구별 통계 **************************************************/
	/**
	 * 통계 > 노선현황 > 시군구별 통계를 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectAdmStats.do" })
	public String selectAdmStats(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/admStats";
	}

	/**
	 * 통계 > 노선현황 > 시군구별 통계를 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectAdmStatsTable.do" })
	public String selectAdmStatsTable(Cell10VO cell10VO, ModelMap model,HttpServletRequest request) throws Exception {

		return "/stats/route/admStatsTable";
	}

	/**
	 * 통계 > 노선현황 > 시군구별 통계를 조회한다.
	 * @return "/stats/route/selectAdmStats"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/selectAdmStatsPageList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectAdmStatsPageList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		cell10VO.setUsePage(true);
		List<Cell10VO> items = cell10Service.selectAdmStatsPageList(cell10VO);

		int total_page = 0;

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cell10VO.getPage());
		map.put("total", total_page);
		map.put("rows", items);

		return map;
	}

	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/cell10/selectAdmLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectAdmLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectAdmLenStatsResult(cell10VO));

		return map;
	}

	@RequestMapping(value = {   "/api/cell10/selectUniAdmLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectUniAdmLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectUniAdmLenStatsResult(cell10VO));

		return map;
	}

	@RequestMapping(value = {   "/api/cell10/selectAdmGradLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectAdmGradLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectAdmGradLenStatsResult(cell10VO));

		return map;
	}

	@RequestMapping(value = {   "api/cell10/selectUniAdmGradLenStatsResult.do" })
    public @ResponseBody Map<String, Object> selectUniAdmGradLenStatsResult(@RequestBody Cell10VO cell10VO,HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", cell10Service.selectUniAdmGradLenStatsResult(cell10VO));

		return map;
	}

	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 엑셀 목록 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@RequestMapping(value="/cell10/selectAdmLenStatsResultExcel.do")
    public View selectAdmLenStatsResultExcel(@ModelAttribute Cell10VO cell10VO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = cell10Service.selectAdmLenStatsResultExcel(cell10VO);

        String[] excel_title  = {"시·군구명","연장(m)"};
        String[] excel_column = {"adm_nm", "len"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "노선현황_시군구별_GPMS총연장_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }


	/**
	 * 통계 > 노선현황 > 노선별 통계 테이블 엑셀목록을 조회한다.
	 * @return
	 * @exception Exception
	 */
	@RequestMapping(value = { "/stats/selectAdmStatsPageListExcel.do" })
	public void selectAdmStatsPageListExcel(Cell10VO cell10VO, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		 //***************************** 데이터값 호출을 위한 기본 셋팅
	   	 HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	 HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	 List result	= new ArrayList();
    	 HashMap<String,Object> tempMap = new HashMap<String,Object>();

    	 Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   	 HSSFWorkbook objWorkBook = new HSSFWorkbook();
	   	 HSSFSheet objSheet = null;
	   	 HSSFRow objRow = null;
	   	 HSSFCell objCell = null;       //셀 생성

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
         styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd.setBorderLeft((short)1);
         styleHd.setBorderBottom((short)1);
         styleHd.setBorderTop((short)1);
         styleHd.setBorderRight((short)1);

	   	 //제목 스타일에 폰트 적용, 정렬
         HSSFCellStyle styleHd1 = objWorkBook.createCellStyle();    //제목 스타일
         styleHd1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         styleHd1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
         styleHd1.setBorderLeft((short)1);
         styleHd1.setBorderBottom((short)1);
         styleHd1.setBorderTop((short)1);
         styleHd1.setBorderRight((short)1);
         styleHd1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);     //색 설정
         styleHd1.setFillPattern(CellStyle.SOLID_FOREGROUND);     //색 패턴 설정

         objSheet = objWorkBook.createSheet("시군구별도로연장통계");     //워크시트 생성

         objRow = objSheet.createRow(0);
         objCell = objRow.createCell(0);
         objCell.setCellValue("시·군구별");
         objSheet.addMergedRegion(new Region(0,(short)0,2,(short)0));
         objSheet.setColumnWidth(0, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(1);
         objCell.setCellValue("국토부(2016)");
         objSheet.addMergedRegion(new Region(0,(short)1,0,(short)3));
         objSheet.setColumnWidth(1, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("GPMS("+calendar.get(calendar.YEAR)+")");
         objSheet.addMergedRegion(new Region(0,(short)4,0,(short)7));
         objSheet.setColumnWidth(4, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(1);
         objCell = objRow.createCell(1);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)1,2,(short)1));
         objSheet.setColumnWidth(1, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(2);
         objCell.setCellValue("개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)2,2,(short)2));
         objSheet.setColumnWidth(2, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(3);
         objCell.setCellValue("미개통도(m)");
         objSheet.addMergedRegion(new Region(1,(short)3,2,(short)3));
         objSheet.setColumnWidth(3, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(4);
         objCell.setCellValue("총연장(m)");
         objSheet.addMergedRegion(new Region(1,(short)4,2,(short)4));
         objSheet.setColumnWidth(4, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(5);
         objCell.setCellValue("중용구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)5,1,(short)6));
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(7);
         objCell.setCellValue("미개설구간(m)");
         objSheet.addMergedRegion(new Region(1,(short)7,2,(short)7));
         objSheet.setColumnWidth(7, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objRow = objSheet.createRow(2);
         objCell = objRow.createCell(5);
         objCell.setCellValue("국지도");
         objSheet.setColumnWidth(5, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         objCell = objRow.createCell(6);
         objCell.setCellValue("지방도");
         objSheet.setColumnWidth(6, objSheet.getColumnWidth(0)+4096);
         objCell.setCellStyle(styleHd1);

         result = cell10Service.selectAdmStatsPageListExcel(cell10VO);//시군구별 연장 목록

         int iRow = 3;

         for(int i=0; i<result.size(); i++){
        	 tempMap = (HashMap) result.get(i);

        	 objRow = objSheet.createRow(iRow++);
             objCell = objRow.createCell(0);
             objCell.setCellValue(tempMap.get("adm_nm").toString());
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(1);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(2);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("op_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(3);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("nop_l")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(4);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("sum_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(5);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("njr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(6);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("jbr_len")));
             objCell.setCellStyle(styleHd);

             objCell = objRow.createCell(7);
             objCell.setCellValue(NumberUtil.stripTrailingZeros(tempMap.get("untrack_len")));
             objCell.setCellStyle(styleHd);
         }

         response.setContentType("Application/Msexcel");
         response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("시군구별도로연장통계","UTF-8")+"_"+dateFormat.format(calendar.getTime())+".xls");

         OutputStream fileOut  = response.getOutputStream();
         objWorkBook.write(fileOut);
         fileOut.close();

         response.getOutputStream().flush();
         response.getOutputStream().close();
	}

	/**
	 * 통합정보조회 > 포장상태평가정보조회 > 상세조회 > 셀목록조회
	 * @author    : JOY
	 * @date      : 2018. 9. 12.
	 *
	 * @param     : cell10VO - 조회할 정보가 담긴 VO
	 * @return    : Map<String, Object>
	 * @exception : Exception
	 */
	@RequestMapping(value = {  "/api/cell10/selectCellList.do" }, method = {RequestMethod.POST, RequestMethod.GET} , consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object>  selectCellList(@RequestBody Cell10VO cell10VO, ModelMap model, HttpSession session) throws Exception {

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cell10VO.getPage());
        paginationInfo.setRecordCountPerPage(cell10VO.getPageUnit());
        paginationInfo.setPageSize(cell10VO.getRows());

        cell10VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cell10VO.setLastIndex(paginationInfo.getLastRecordIndex());
        cell10VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


        String[] arr = cell10VO.getCELL_ID().split(",");
        List<String> cellArr = new ArrayList<String>();

        for ( int i = 0; i < arr.length; i++ ) {

            cellArr.add(arr[i]);

        }

        cell10VO.setCELL_ID_ARR(cellArr);

        List<Cell10VO> items = cell10Service.selectCellList(cell10VO);
        int total_count = cell10Service.selectCellListCnt(cell10VO);

        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) cell10VO.getPageSize());
        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", cell10VO.getPage());
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);

        return map;
    }

	/**
	 * 경위도 좌표 이동 > 좌표 입력 시 cell 정보 조회
	 * @author    : JOY
	 * @date      : 2018. 10. 29.
	 *
	 * @param     : cell10VO - 조회할 정보가 담긴 cell10VO
	 * @return    : map
	 * @exception : Exception
	 */
	@RequestMapping(value = {  "/api/cell10/selectCellInfoByLonLat.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object>  selectCellInfoByLonLat(@RequestBody Cell10VO cell10VO, ModelMap model, HttpSession session) throws Exception {

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cell10VO.getPage());
        paginationInfo.setRecordCountPerPage(cell10VO.getPageUnit());
        paginationInfo.setPageSize(cell10VO.getRows());
        cell10VO.setUsePage(true);

        cell10VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cell10VO.setLastIndex(paginationInfo.getLastRecordIndex());
        cell10VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<Cell10VO> items = cell10Service.selectCellInfoByLonLat(cell10VO);
        int total_count = cell10Service.selectCellInfoByLonLatCnt(cell10VO);

        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) cell10VO.getPageSize());
        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", cell10VO.getPage());
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);

        return map;
    }
	
	
	/**
	 * 통계 > 노선현황 > 노선별 통계 목록을 조회한다.
	 * @return "/stats/route/selectRoutStatsPageList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/stats/test.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectTest(@RequestBody Cell10VO cell10VO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        cell10VO.setUsePage(true);

        List<Cell10VO> items = cell10Service.selectTest(cell10VO);

        int total_page = 0;

        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", cell10VO.getPage());
        map.put("total", total_page);
        map.put("rows", items);

        return map;
    }
}
