package kr.go.gg.gpms.pothole.sttemnt.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.CompanyService;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.map.service.MapService;
import kr.go.gg.gpms.pothole.cmptnc.service.CmptncService;
import kr.go.gg.gpms.pothole.cmptnc.service.model.CmptncVO;
import kr.go.gg.gpms.pothole.sms.service.SmsService;
import kr.go.gg.gpms.pothole.sttemnt.service.RpairService;
import kr.go.gg.gpms.pothole.sttemnt.service.SttemntService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import egovframework.cmmn.util.DateUtil;
import egovframework.cmmn.util.ExcelView;
import egovframework.cmmn.util.ExcelViewSttemnt;
import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 포트홀 신고관리 Controller
 * @Classname : SttemntController.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 *
 * ---------------------------------------------------------
 * 2019년 고도화 사업 - 포트홀_신고관리 접수경로 구분하여 조회,수정,삭제처리되도록 함.
 * sttemntVO.pthmode - 접수경로별 구분
 * T(default) : 스마트카드
 * M : 국토부 신고앱
 * C : 시군관리
 * -----------------------------------
 * sttemntVO.imode - 편집모드 구분 (수정, 등록)
 * EDIT(default) : 수정
 * ADD : 등록
 * ---------------------------------------------------------
*/
@Controller("sttemntController")
public class SttemntController extends BaseController {

	@Resource(name = "sttemntService")
    private SttemntService sttemntService;

    @Resource(name = "rpairService")
    private RpairService rpairService;

    @Resource(name = "cmptncService")
    private CmptncService cmptncService;

    @Resource(name = "deptService")
    private DeptService deptService;

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "companyService")
    private CompanyService companyService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService egovPropertyService;

    @Resource(name = "attachFileService")
    protected AttachFileService attachFileService;

    @Resource(name = "txManager")
    protected DataSourceTransactionManager txManager;

	@Resource(name = "mapService")
	private MapService mapService;

	@Resource(name = "smsService")
	private SmsService smsService;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SttemntController.class);


    /**
     * 포트홀 신고관리 페이지를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : "/pothole/sttemnt/sttemntList"
     * @exception : Exception
     */
    @RequestMapping(value = { "/pothole/sttemnt/selectSttemntList.do" })
    public String selectSttemntList(@ModelAttribute("searchVO") SttemntVO sttemntVO, ModelMap model ) throws Exception {

        // 관할기관
        DeptVO deptVO = new DeptVO();
        List<DeptVO> deptList = deptService.selectCptcIsttList(deptVO);
        model.addAttribute("deptList", deptList);

        // 2018. 04. 19. JOY. 현재 사용자 부서 비교
        MemberInfo memberinfo = SessionManager.getCurrentUser();
        String usrDeptCode = memberinfo.getDEPT_CODE();

        SttemntVO sttemntVO_USER = new SttemntVO();
        sttemntVO_USER.setDEPT_CODE(usrDeptCode);
        usrDeptCode = sttemntService.selectHighDeptCode(sttemntVO_USER);

        model.addAttribute("usrDeptCode", usrDeptCode);

        // 처리상태
        CodeVO codeVO_ONE = new CodeVO();
        codeVO_ONE.setCL_CODE("PRCS");
        codeVO_ONE.setSidx("CODE_VAL");
        codeVO_ONE.setUsePage(false);
        List<CodeVO> prcsList = codeService.selectCodeList(codeVO_ONE);
        model.addAttribute("prcsList", prcsList);

        // 파손유형
        CodeVO codeVO_TWO = new CodeVO();
        codeVO_TWO.setCL_CODE("DMGT");
        codeVO_TWO.setSidx("CODE_VAL");
        codeVO_TWO.setUsePage(false);
        List<CodeVO> dmgtList = codeService.selectCodeList(codeVO_TWO);
        model.addAttribute("dmgtList", dmgtList);

        // 접수경로
        CodeVO codeVO_THREE = new CodeVO();
        codeVO_THREE.setCL_CODE("PRRT");
        codeVO_THREE.setSidx("PRIOR_RANK");
        codeVO_THREE.setUsePage(false);
        List<CodeVO> prrtList = codeService.selectCodeList(codeVO_THREE);
        model.addAttribute("prrtList", prrtList);

        // 신고정보 공간검색용 PTH_RG_NO_LAYER 값 넘김
        JSONArray pthRgNo = sttemntVO.getPTH_RG_NO_LAYER();
        //String[] pthRgNo = sttemntVO.getPTH_RG_NO_LAYER();
        //Map<String, Object> map = new HashMap<String, Object>()

        /*
        sttemntVO.setPTH_RG_NO_LAYER(null);
        JSONArray aa = null ;
        sttemntVO.setPTH_RG_NO_LAYER(aa);
        */
        if (pthRgNo ==null){
        	model.addAttribute("pthRgNo", "[]");
        	//pthRgNo.clear();
        }
        else {
        	model.addAttribute("pthRgNo", pthRgNo);
        	//pthRgNo.clear();
        }

        // 민자도로사업자신고정보조회 상세조회용: goodmap.jsp에서 DEPT_CODE 값 넘김
        model.addAttribute("prv_deptCode", sttemntVO.getDEPT_CODE());
/*
        // sflag 넘김 (공간검색용)
        model.addAttribute("sflag", sttemntVO.getSflag());
*/
        return "/pothole/sttemnt/sttemntList" ;
    }

    /**
     * 포트홀 신고관리 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : map
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/selectSttemntList.do" }, method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectSttemntListRest(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(sttemntVO.getPage());
        paginationInfo.setRecordCountPerPage(sttemntVO.getPageUnit());
        paginationInfo.setPageSize(sttemntVO.getRows());
        sttemntVO.setUsePage(true);

        sttemntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        sttemntVO.setLastIndex(paginationInfo.getLastRecordIndex());
        sttemntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // JSONArray 초기화

        if ( !sttemntVO.getPTH_RG_NO_LAYER().isEmpty() ){
        	if(sttemntVO.getPTH_RG_NO_LAYER().get(0) == null ){
            	sttemntVO.setPTH_RG_NO_LAYER(new JSONArray());
            }
        }

        List<SttemntVO> items = sttemntService.selectPotholeSttemntList(sttemntVO);
        int total_count = sttemntService.selectPotholeSttemntListTotalCount(sttemntVO);
        List<SttemntVO> prcs = sttemntService.selectPotholeSttemntListPrcsCount(sttemntVO);

        int total_page = 0;
        if (total_count > 0)
            total_page = (int) Math.ceil((float) total_count / (float) sttemntVO.getPageSize());
        // 결과 JSON 저장
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", sttemntVO.getPage());
        map.put("total", total_page);
        map.put("records", total_count);
        map.put("rows", items);
        map.put("prcs", prcs);

        return map;
    }



    /**
     * 포트홀 신고관리 상세정보 조회페이지를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : "/pothole/sttemnt/sttemntView"
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/pothole/sttemnt/selectSttemntView.do" })
    public String selectSttemntView(@ModelAttribute("searchVO") SttemntVO sttemntVO, HttpServletRequest request, ModelMap model) throws Exception {

    	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	sttemntVO.setPthmode(isEmptyValue(sttemntVO.getPthmode(),"T"));
    	sttemntVO.setImode(isEmptyValue(sttemntVO.getImode(),""));
    	sttemntVO.setGG_ID(isEmptyValue(sttemntVO.getGG_ID(),""));

        // 등록
        if("ADD".equals(sttemntVO.getImode())) {

        	SttemntVO sttemntResult1 = sttemntService.selectPotholeRgNo(sttemntVO);
        	String pthRgNo = sttemntResult1.getPTH_RG_NO();
        	sttemntVO.setPTH_RG_NO(pthRgNo);

        	System.out.println("add -> view pthRgNo : " + sttemntVO.getPTH_RG_NO());
        }

    	String fileRealPath = pathInfoProperties.getProperty("file.upload.path");
    	model.addAttribute("fileRealPath", fileRealPath);

        // 관할기관
        DeptVO deptVO = new DeptVO();
        List<DeptVO> deptList = deptService.selectCptcIsttList(deptVO);
        model.addAttribute("deptList", deptList);

        // 처리상태
        CodeVO codeVO_ONE = new CodeVO();
        codeVO_ONE.setCL_CODE("PRCS");
        codeVO_ONE.setSidx("CODE_VAL");
        codeVO_ONE.setUsePage(false);
        List<CodeVO> prcsList = codeService.selectCodeList(codeVO_ONE);
        model.addAttribute("prcsList", prcsList);

        // 파손유형
        CodeVO codeVO_TWO = new CodeVO();
        codeVO_TWO.setCL_CODE("DMGT");
        codeVO_TWO.setSidx("CODE_VAL");
        codeVO_TWO.setUsePage(false);
        List<CodeVO> dmgtList = codeService.selectCodeList(codeVO_TWO);
        model.addAttribute("dmgtList", dmgtList);

        // 상세정보
        SttemntVO sttemntResult = sttemntService.selectPotholeSttemnt(sttemntVO);
        sttemntResult.setPthmode(sttemntVO.getPthmode());
        model.addAttribute("sttemntVO", sttemntResult);

        // 2018. 04. 19. JOY. 현재 사용자 부서 비교
        MemberInfo memberinfo = SessionManager.getCurrentUser();
        String usrDeptCode = memberinfo.getDEPT_CODE();
        String sttDeptCode = sttemntResult.getDEPT_CODE();

        SttemntVO sttemntVO_USER = new SttemntVO();
        sttemntVO_USER.setDEPT_CODE(usrDeptCode);
        usrDeptCode = sttemntService.selectHighDeptCode(sttemntVO_USER);

        if ( sttDeptCode != null && usrDeptCode != null && sttDeptCode.equals(usrDeptCode) ) {
            model.addAttribute("matchDept", "m"); // matched
            System.out.println("matchDept : m");
        } else {
            model.addAttribute("matchDept", "u"); // unmatched
            System.out.println("matchDept : u");
        }

        if(!"C".equals(sttemntVO.getPthmode())){

	        // 2018. 03. 07. JOY. 중복 신고 정보
	        SttemntVO sttemntVO_ONE = new SttemntVO();
	        sttemntVO_ONE.setDSM_RP_NO(sttemntVO.getPTH_RG_NO());
	        sttemntVO_ONE.setPthmode(sttemntVO.getPthmode());
	        List<SttemntVO> overlapList = sttemntService.selectOverlapSttemnt(sttemntVO_ONE);
	        int overlapCnt = sttemntService.selectOverlapCnt(sttemntVO_ONE);
	        model.addAttribute("overlapList", overlapList);
	        model.addAttribute("overlapCnt", overlapCnt);
        }

        // 시공사
        CompanyVO companyVO = new CompanyVO();
        companyVO.setSidx("CO_NO");
        companyVO.setUsePage(false);
        List<CompanyVO> companyResult = companyService.selectCompanyList(companyVO);
        model.addAttribute("companyResult", companyResult);

        // 보수정보
        RpairVO rpairVO = new RpairVO();
        rpairVO.setPTH_RG_NO(sttemntVO.getPTH_RG_NO());
        rpairVO.setPthmode(sttemntVO.getPthmode());
        RpairVO rpairResult = rpairService.selectPotholeRpair(rpairVO);
        if ( rpairResult == null ) {
            rpairResult = new RpairVO();
        }

        model.addAttribute("rpairVO", rpairResult);

        // 담당자 정보 + 노선명
        CmptncVO cmptncVO = new CmptncVO();
        cmptncVO.setCPT_MNG_NO(sttemntVO.getCPT_MNG_NO());
        //CmptncVO cmptncResult = cmptncService.selectCmptncRoute(cmptncVO);
        //model.addAttribute("cmptncVO", cmptncResult);


        // 2018.03.13. YYK. 위치도(이미지) 보여줌
        Map<String, String> req = requestToHashMap(request);
        String filePath = pathInfoProperties.getProperty("file.upload.webpath");

        AttachFileVO result;
        AttachFileVO attachFileVO = new AttachFileVO();
        attachFileVO.setFILE_NO(sttemntResult.getLCDO()); // YYK 확인
        result = attachFileService.selectAttachFile(attachFileVO);
        model.addAttribute("lcdo", result);


        // 2018.01.14. YYK. 첨부파일(이미지) 보여줌
        // 2018.06.05. YYK. 첨부파일 여러개 보여줌..
        if(rpairResult.getRPAIR_BFE_PHOTO() != null){
        	String[] arrRPAIR_BFE_PHOTO = rpairResult.getRPAIR_BFE_PHOTO().split("/");

        	for(int i=0; i<arrRPAIR_BFE_PHOTO.length; i++){
        		if(arrRPAIR_BFE_PHOTO[i] != null){
        			attachFileVO.setFILE_NO(arrRPAIR_BFE_PHOTO[i]);
                    result = attachFileService.selectAttachFile(attachFileVO);
                    model.addAttribute("bfe_file_info"+i, result);
        		}
            }
        }

        if(rpairResult.getRPAIR_AFT_PHOTO() != null){
	        String[] arrRPAIR_AFT_PHOTO = rpairResult.getRPAIR_AFT_PHOTO().split("/");

	        for(int i=0; i<arrRPAIR_AFT_PHOTO.length; i++){
	        	if(arrRPAIR_AFT_PHOTO[i] != null){
		        	attachFileVO.setFILE_NO(arrRPAIR_AFT_PHOTO[i]);
		            result = attachFileService.selectAttachFile(attachFileVO);
		            model.addAttribute("aft_file_info"+i, result);
	        	}
	        }
        }


        // 국토부 앱 사진조회
        // lhj
        AttachFileVO result2;
        AttachFileVO attachFileVO2 = new AttachFileVO();
        attachFileVO.setFILE_NO(sttemntVO.getLCDO()); // YYK 확인
        result2 = attachFileService.selectAttachFile(attachFileVO2);
        model.addAttribute("lcdo", result2);


        // 2018.01.14. YYK. 첨부파일(이미지) 보여줌
        // 2018.06.05. YYK. 첨부파일 여러개 보여줌..
        if(sttemntResult.getPTH_PHOTO() != null){
        	String[] arrPTH_PHOTO = sttemntResult.getPTH_PHOTO().split("/");

        	for(int i=0; i<arrPTH_PHOTO.length; i++){
        		if(arrPTH_PHOTO[i] != null){
        			attachFileVO2.setFILE_NO(arrPTH_PHOTO[i]);
                    result2 = attachFileService.selectAttachFile(attachFileVO2);
                    model.addAttribute("PTH_PHOTO"+i, result2);
        		}
            }
        }

/*        attachFileVO.setFILE_NO(rpairResult.getRPAIR_BFE_PHOTO());
        result = attachFileService.selectAttachFile(attachFileVO);
        model.addAttribute("file_info1", result);

        attachFileVO.setFILE_NO(rpairResult.getRPAIR_AFT_PHOTO());
        result = attachFileService.selectAttachFile(attachFileVO);
        model.addAttribute("file_info2", result);*/

        //model.addAttribute("rpairBfe", result);
        //model.addAttribute("rpairAft", result);

        model.addAttribute("file_column", req.get("FILE_COLUMN"));
    	model.addAttribute("file_type", req.get("FILE_TYPE"));
    	model.addAttribute("file_mode", req.get("FILE_MODE"));
    	model.addAttribute("file_path", filePath);

        return "/pothole/sttemnt/sttemntView" ;
    }


    /**
     * 포트홀 신고관리 상세정보 수정페이지를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : "/pothole/sttemnt/sttemntUpdate"
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/pothole/sttemnt/selectSttemntUpdate.do" })
    public String selectSttemntUpdate(@ModelAttribute("searchVO") SttemntVO sttemntVO, HttpServletRequest request, ModelMap model) throws Exception {

        String fileRealPath = pathInfoProperties.getProperty("file.upload.path");
        model.addAttribute("fileRealPath", fileRealPath);

        // 관할기관
        DeptVO deptVO = new DeptVO();
        deptVO.setFlag("minja"); //민자도로사업자 세부목록 출력을 위한 flag
        List<DeptVO> deptList = deptService.selectCptcIsttList(deptVO);
        model.addAttribute("deptList", deptList);

        // 처리상태
        CodeVO codeVO_ONE = new CodeVO();
        codeVO_ONE.setCL_CODE("PRCS");
        codeVO_ONE.setSidx("CODE_VAL");
        codeVO_ONE.setUsePage(false);
        List<CodeVO> prcsList = codeService.selectCodeList(codeVO_ONE);
        model.addAttribute("prcsList", prcsList);

        // 파손유형
        CodeVO codeVO_TWO = new CodeVO();
        codeVO_TWO.setCL_CODE("DMGT");
        codeVO_TWO.setSidx("CODE_VAL");
        codeVO_TWO.setUsePage(false);
        List<CodeVO> dmgtList = codeService.selectCodeList(codeVO_TWO);
        model.addAttribute("dmgtList", dmgtList);

        // 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	sttemntVO.setPthmode(isEmptyValue(sttemntVO.getPthmode(),"T"));
    	sttemntVO.setImode(isEmptyValue(sttemntVO.getImode(),""));
    	sttemntVO.setGG_ID(isEmptyValue(sttemntVO.getGG_ID(),""));
        System.out.println("pthmode : " + sttemntVO.getPthmode() + ", imode : " + sttemntVO.getImode() + ", GG_ID : " + sttemntVO.getGG_ID());

        // 등록
        if("ADD".equals(sttemntVO.getImode())) {

        	sttemntVO.setCPT_MNG_NO(isEmptyValue(request.getParameter("cptg2Id"),"0"));
        	System.out.println("ADD - GG_ID : " + sttemntVO.getGG_ID());

        	// 관할기관
        	MemberInfo memberinfo = SessionManager.getCurrentUser();
	        String usrDeptCode = memberinfo.getDEPT_CODE();

	        sttemntVO.setDEPT_CODE(usrDeptCode);
	        usrDeptCode = sttemntService.selectHighDeptCode(sttemntVO);

	        model.addAttribute("usrDeptCode", usrDeptCode);

        	// 상세정보
	        SttemntVO sttemntResult1 = sttemntService.selectPotholeLAdres(sttemntVO);
	        sttemntVO.setLNM_ADRES(sttemntResult1.getLNM_ADRES());
        	model.addAttribute("sttemntVO", sttemntVO);

        	RpairVO rpairVO = new RpairVO();
        	model.addAttribute("rpairVO", rpairVO);

        	// 담당자 정보 + 관할구역번호
	        CmptncVO cmptncVO = new CmptncVO();
	        cmptncVO.setCPT_MNG_NO(SessionManager.getUserNo());

        // 수정
        } else {

        	System.out.println("UPDATE - GG_ID : " + sttemntVO.getImode());

	        // 상세정보
	        SttemntVO sttemntResult = sttemntService.selectPotholeSttemnt(sttemntVO);
	        model.addAttribute("sttemntVO", sttemntResult);

	        // 보수정보
	        RpairVO rpairVO = new RpairVO();
	        rpairVO.setPTH_RG_NO(sttemntVO.getPTH_RG_NO());
	        rpairVO.setPthmode(sttemntVO.getPthmode());
	        RpairVO rpairResult = rpairService.selectPotholeRpair(rpairVO);
	        if ( rpairResult == null ) {

	            rpairResult = new RpairVO();

	        }
	        model.addAttribute("rpairVO", rpairResult);

	        // 담당자 정보 + 노선명
	        CmptncVO cmptncVO = new CmptncVO();
	        cmptncVO.setCPT_MNG_NO(sttemntVO.getCPT_MNG_NO());
	        //CmptncVO cmptncResult = cmptncService.selectCmptncRoute(cmptncVO);
	        //model.addAttribute("cmptncVO", cmptncResult);


	        // 첨부파일(이미지) 보여줌
	        Map<String, String> req = requestToHashMap(request);
	        String filePath = pathInfoProperties.getProperty("file.upload.webpath");

	        AttachFileVO result;
	        AttachFileVO attachFileVO = new AttachFileVO();

	        // 2018.06.05. YYK. 첨부파일 여러개 보여줌..
	        if(rpairResult.getRPAIR_BFE_PHOTO() != null){
	        	String[] arrRPAIR_BFE_PHOTO = rpairResult.getRPAIR_BFE_PHOTO().split("/");

	        	model.addAttribute("bfe_file_length", arrRPAIR_BFE_PHOTO.length);

	        	for(int i=0; i<arrRPAIR_BFE_PHOTO.length; i++){
	        		if(arrRPAIR_BFE_PHOTO[i] != null){
	        			attachFileVO.setFILE_NO(arrRPAIR_BFE_PHOTO[i]);
	                    result = attachFileService.selectAttachFile(attachFileVO);
	                    model.addAttribute("bfe_file_info"+i, result);
	        		}
	            }
	        }

	        if(rpairResult.getRPAIR_AFT_PHOTO() != null){
		        String[] arrRPAIR_AFT_PHOTO = rpairResult.getRPAIR_AFT_PHOTO().split("/");

		        model.addAttribute("aft_file_length", arrRPAIR_AFT_PHOTO.length);

		        for(int i=0; i<arrRPAIR_AFT_PHOTO.length; i++){
		        	if(arrRPAIR_AFT_PHOTO[i] != null){
			        	attachFileVO.setFILE_NO(arrRPAIR_AFT_PHOTO[i]);
			            result = attachFileService.selectAttachFile(attachFileVO);
			            model.addAttribute("aft_file_info"+i, result);
		        	}
		        }
	        }

	/*        attachFileVO.setFILE_NO(rpairResult.getRPAIR_BFE_PHOTO());
	        result = attachFileService.selectAttachFile(attachFileVO);
	        model.addAttribute("file_info1", result);

	        attachFileVO.setFILE_NO(rpairResult.getRPAIR_AFT_PHOTO());
	        result = attachFileService.selectAttachFile(attachFileVO);
	        model.addAttribute("file_info2", result);*/

	        //model.addAttribute("rpairBfe", result);
	        //model.addAttribute("rpairAft", result);

	        model.addAttribute("file_column", req.get("FILE_COLUMN"));
	        model.addAttribute("file_type", req.get("FILE_TYPE"));
	        model.addAttribute("file_mode", req.get("FILE_MODE"));
	        model.addAttribute("file_path", filePath);
        }

        return "/pothole/sttemnt/sttemntUpdate" ;

    }

    /**
     * 포트홀 신고정보 상세조회 내용을 수정한다. (신고정보)
     * @author    : JOY
     * @date      : 2018. 1. 11.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/updateSttemntViewSttemnt.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int updateSttemntViewSttemnt(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

    	sttemntVO.setUSE_AT("Y");
        sttemntVO.setDELETE_AT("N");
        sttemntVO.setUPDUSR_NO(SessionManager.getUserNo());

        // 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	sttemntVO.setPthmode(isEmptyValue(sttemntVO.getPthmode(),"T"));
    	sttemntVO.setImode(isEmptyValue(sttemntVO.getImode(),""));
    	sttemntVO.setGG_ID(isEmptyValue(sttemntVO.getGG_ID(),""));
        System.out.println("pthmode : " + sttemntVO.getPthmode() + ", imode : " + sttemntVO.getImode() + ", GG_ID : " + sttemntVO.getGG_ID());

        int rValue = -1;
        sttemntService.updatePthSttmnt(sttemntVO);

        // 등록
        if("ADD".equals(sttemntVO.getImode())){

        	SttemntVO sttemntResult = sttemntService.selectPotholeRgNo(sttemntVO);
        	String pthRgNo = sttemntResult.getPTH_RG_NO();
        	sttemntVO.setPTH_RG_NO(pthRgNo);

        	System.out.println("add pthRgNo : " + sttemntVO.getPTH_RG_NO());

        	sttemntService.insertPotholeSttemnt(sttemntVO);

        	String pthRgNoRe = pthRgNo.replace("-", "");

        	System.out.println("add pthRgNoRe : " + pthRgNoRe);

        	rValue = Integer.parseInt(pthRgNoRe);

        //수정
        }else{

        	sttemntService.updatePotholeSttemnt(sttemntVO);
        	rValue = sttemntService.updateDplctSttemntAt(sttemntVO);

        }
        return rValue;

    }

    // 보수정보에 값이 있는지 체크
    public boolean checkRpairInfo(RpairVO rpairVO, HttpServletRequest request) throws Exception {

        if ( !rpairVO.getDMG_TYPE().equals("") ) { return true; }
        if ( !rpairVO.getRPAIR_AMOUNT().equals("") ) { return true; }
        if ( !rpairVO.getRPRSCL_WIDTH().equals("") ) { return true; }
        if ( !rpairVO.getRPRSCL_VRTICL().equals("") ) { return true; }
        if ( !rpairVO.getRPRSCL_AR().equals("") ) { return true; }
        if ( !rpairVO.getRPRSCL_DP().equals("") ) { return true; }
        if ( !rpairVO.getRM().equals("") ) { return true; }
        if ( !rpairVO.getRPAIR_DT().equals("") ) { return true; }
        if ( !rpairVO.getCNFIRM_DT().equals("") ) { return true; }
        if ( getFileNoArr(request, "RPAIR_BFE_PHOTO", "rpair") != null ) { return true; }
        if ( getFileNoArr(request, "RPAIR_AFT_PHOTO", "rpair") != null ) { return true; }

        return false;

    }

    /**
     * 포트홀 신고정보 상세조회 내용을 수정한다. (보수정보)
     * @author    : JOY
     * @date      : 2018. 1. 11.
     *
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */

    @SuppressWarnings("unused")
    @RequestMapping(value = { "/api/pothole/sttemnt/updateSttemntViewRpair.do" }, method = {RequestMethod.GET, RequestMethod.POST} )
    public void updateSttemntViewRpair(RpairVO rpairVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {

        try{

            RpairVO rpairVO_ONE = new RpairVO();

            // 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
            rpairVO.setPthmode(isEmptyValue(rpairVO.getPthmode(),"T"));
            String imode = isEmptyValue(request.getParameter("imode"),"");
            String GG_ID = isEmptyValue(request.getParameter("GG_ID"),"");

            // 시군관리 포트홀 신고 정보 처음 등록시에는 G2_ID값이 넘어옴.
            if("ADD".equals(imode)){
            	SttemntVO sttemntVO = new SttemntVO();
            	sttemntVO.setPthmode(isEmptyValue(rpairVO.getPthmode(),"T"));
            	sttemntVO.setGG_ID(GG_ID);
            	//G2_ID로 해당 포트홀 관리 번호 가져오기
            	SttemntVO sttemntResult = sttemntService.selectPotholeRgNo(sttemntVO);
            	rpairVO.setPTH_RG_NO(sttemntResult.getPTH_RG_NO());
            }

            rpairVO_ONE.setPTH_RG_NO(rpairVO.getPTH_RG_NO());
            rpairVO_ONE.setPthmode(isEmptyValue(rpairVO.getPthmode(),"T"));

            RpairVO rpairResult = rpairService.selectPotholeRpair(rpairVO_ONE);


            String resultCode = "";
            String resultMsg = "";

            int result = 0;

            if ( rpairResult == null ) {

                if ( checkRpairInfo(rpairVO, request) )
                {

                    rpairVO_ONE.setCRTR_NO(SessionManager.getUserNo());
                    // 보수정보가 없는 경우 신규 등록
                    rpairService.insertPotholeRpair(rpairVO_ONE);

                    rpairVO.setUSE_AT("Y");
                    rpairVO.setDELETE_AT("N");
                    rpairVO.setUPDUSR_NO(SessionManager.getUserNo());

                    rpairResult = rpairService.selectPotholeRpair(rpairVO_ONE);

                } else {

                    resultCode = "UPDATE_SUCCESS";
                    resultMsg = "정상 수정되었습니다.";

                    model.addAttribute("result", result); // 처리후 호출함수

                }
            }
            System.out.println("chk1");


            // ============= 첨부파일(이미지) 첨부 부분 =============
            String bfe="";
        	String aft="";

        	String[] arrRPAIR_BFE_PHOTO = null;
        	String[] arrRPAIR_AFT_PHOTO = null;

        	if(rpairResult.getRPAIR_BFE_PHOTO() != null){
            	arrRPAIR_BFE_PHOTO = rpairResult.getRPAIR_BFE_PHOTO().split("/");
            }
        	if(rpairResult.getRPAIR_AFT_PHOTO() != null){
            	arrRPAIR_AFT_PHOTO = rpairResult.getRPAIR_AFT_PHOTO().split("/");
            }

            for(int i=0; i<4; i++){
            	String bfeNo = getFileNoArr(request, "RPAIR_BFE_PHOTO"+i, "rpair");
            	String aftNo = getFileNoArr(request, "RPAIR_AFT_PHOTO"+i, "rpair");

            	if (bfeNo != ""  ){
            		bfe += bfeNo + "/" ; // 추가 파일no
            	}
            	else if (rpairVO.getBFEflag().indexOf("b_img"+ i) != -1){
            		bfe += "" ;
            	}
            	else {
            		if(arrRPAIR_BFE_PHOTO != null){
            			if (arrRPAIR_BFE_PHOTO.length > i)
                			bfe += arrRPAIR_BFE_PHOTO[i] + "/"; // 기존 가진 파일no
            		}
            	}

            	if (aftNo != ""  ){
            		aft += aftNo + "/" ; // 추가 파일no
            	}
            	else if (rpairVO.getAFTflag().indexOf("a_img"+ i) != -1){
            		aft += "" ;
            	}
            	else {
            		if(arrRPAIR_AFT_PHOTO != null){
    	        		if (arrRPAIR_AFT_PHOTO.length > i)
    	        			aft += arrRPAIR_AFT_PHOTO[i] + "/"; // 기존 가진 파일no
            		}
            	}

            }
            rpairVO.setRPAIR_BFE_PHOTO(bfe);
            rpairVO.setRPAIR_AFT_PHOTO(aft);

            System.out.println("chk2");

    /*
        	if(bfe != null && bfe != "" && !bfe.isEmpty()){
        		rpairVO.setRPAIR_BFE_PHOTO(bfe);
        	} else {
        		if (rpairVO.getBFEflag().equals("delImg") ){
        			rpairVO.setRPAIR_BFE_PHOTO("");
        		}else{
        			rpairVO.setRPAIR_BFE_PHOTO(rpairResult.getRPAIR_BFE_PHOTO());
        		}
        	}
        	if(aft != null && aft != "" && !aft.isEmpty()){
        		rpairVO.setRPAIR_AFT_PHOTO(aft);
        	} else {
        		if (rpairVO.getAFTflag().equals("delImg") ){
        			rpairVO.setRPAIR_AFT_PHOTO("");
        		}else{
        			rpairVO.setRPAIR_AFT_PHOTO(rpairResult.getRPAIR_AFT_PHOTO());
        		}
        	}
        	*/

            // DAYCNT 값 처리
            if ( rpairVO.getDAYCNT() != null ) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date stsDate = sdf.parse(rpairVO.getSTTEMNT_DT().split(" ")[0]);
                Date endDate = sdf.parse(sdf.format(new Date()));

                int dayCnt = (int) (( endDate.getTime() - stsDate.getTime() ) / ( 24 * 60 * 60 * 1000 ));
                rpairVO.setDAYCNT(String.valueOf(dayCnt));

            } else {

                rpairVO.setDAYCNT(null);

            }
            System.out.println("chk3");

        	String funCallback = rpairVO.getCallBackFunction() == null ? ""
        			: rpairVO.getCallBackFunction();

        	result = rpairService.updatePotholeRpair(rpairVO);

        	System.out.println("chk4");

    		resultCode = "UPDATE_SUCCESS";
    		resultMsg = "정상 수정되었습니다.";

    		model.addAttribute("result", result); // 처리후 호출함수

    		System.out.println("chk5");

        } catch(SQLException e) {
    		e.printStackTrace();
    	} catch(NullPointerException e) {
    		e.printStackTrace();
    	} catch(Exception e ) {
    		e.printStackTrace();
    	}
    }


    /**
     * 포트홀 신고정보 상세조회 내용을 삭제한다.
     * @author    : JOY
     * @date      : 2018. 1. 11.
     *
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/deletePotholeRpair.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int deletePotholeRpair(@RequestBody RpairVO rpairVO, ModelMap model) throws Exception {

    	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
        rpairVO.setPthmode(isEmptyValue(rpairVO.getPthmode(),"T"));

    	rpairVO.setUPDUSR_NO(SessionManager.getUserNo());
        return rpairService.deletePotholeRpair(rpairVO);
    }


    /**
     * 포트홀 신고정보 상세조회 내용을 삭제한다.
     * @author    : JOY
     * @date      : 2018. 1. 11.
     *
     * @param     :
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/deletePotholeSttemntAll.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int deletePotholeSttemntAll(@RequestBody RpairVO rpairVO, ModelMap model) throws Exception {

    	SttemntVO sttemntVO = new SttemntVO();

    	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	rpairVO.setPthmode(isEmptyValue(rpairVO.getPthmode(),"T"));
    	sttemntVO.setPthmode(rpairVO.getPthmode());

        rpairVO.setUPDUSR_NO(SessionManager.getUserNo());

        sttemntVO.setUPDUSR_NO(SessionManager.getUserNo());
        sttemntVO.setPTH_RG_NO(rpairVO.getPTH_RG_NO());

        System.out.println("delete  : " + rpairVO.getPthmode() + " / " + rpairVO.getPTH_RG_NO() + " / " + rpairVO.getRPRDTLS_MNG_NO());

        rpairService.deletePotholeRpair(rpairVO);

        return sttemntService.deletePotholeSttemntAll(sttemntVO);
    }


    /**
     * 포트홀 신고정보 위치 수정 시 좌표정보를 수정한다.
     * @author    : lsk
     * @date      : 2019. 6. 25.
     *
     * @param     :
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/updatePotholeXY.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int updatePotholeXY(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

    	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	sttemntVO.setPthmode(isEmptyValue(sttemntVO.getPthmode(),"T"));

    	//G2_ID로 해당 포트홀 관리 번호 가져오기
    	SttemntVO sttemntResult = sttemntService.selectPotholeRgNo(sttemntVO);
    	sttemntVO.setPTH_RG_NO(sttemntResult.getPTH_RG_NO());

        System.out.println("update  : " + sttemntVO.getPthmode() + " / " + sttemntVO.getPTH_RG_NO()
        		+ " / " + sttemntVO.getCORTN_X() + " / " + sttemntVO.getCORTN_Y());

        return sttemntService.updatePotholeXY(sttemntVO);
    }


    /**
     * 포트홀 신고관리 목록을 엑셀로 저장한다.
     * @author    : yyk
     * @date      : 2018. 1. 11.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : ExcelView()
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/api/sttemnt/sttemntListExcel.do")
    public View sttemntListExcel(@ModelAttribute SttemntVO sttemntVO, ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {

        // JSONArray 초기화
    	if ( sttemntVO.getPTH_RG_NO_LAYER().equals("[]") || sttemntVO.getPTH_RG_NO_LAYER().size() == 0 ){
    		sttemntVO.setPTH_RG_NO_LAYER(new JSONArray());
    	}
    	else {
    		sttemntVO.setPTH_RG_NO("");
    	}
    	List dataList = sttemntService.selectSttemntListExcel(sttemntVO);

        String[] excel_title  = {"등록번호","차량번호","신고자","좌표X","좌표Y","도로명","지번주소","신고일시","보수일자","처리일시","처리기간(일)","관할기관","파손유형","처리상태", "비고"};
        String[] excel_column = {"PTH_RG_NO","VHCLE_NO","BSNM_NM","CORTN_X","CORTN_Y","RN_ADRES","LNM_ADRES","STTEMNT_DT","RPAIR_DT","PROCESS_DT","DAYCNT","LOWEST_DEPT_NM","DMG_TYPE_NM","PRCS_STTUS_NM","RM"};

        model.addAttribute("file_name",    "포트홀 신고관리_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }


    /**
     * 포트홀 신고관리 상세정보 - 전일(어제~오늘) 검색 수 파악
     * @author    : YYK
     * @date      : 2018. 1. 24.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : ExcelView()
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/sttemnt/sttemntViewExcelCnt.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> sttemntViewExcelCnt(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	int total_count = sttemntService.selectPotholeSttemntListTotalCount(sttemntVO);
    	map.put("total_count" , total_count   );

		return map;
    }


    /**
     * 포트홀 신고관리 상세정보 엑셀 저장(이미지 포함)
     * @author    : YYK
     * @date      : 2018. 1. 20.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : ExcelView()
     * @exception : Exception
     */
    @RequestMapping(value="/api/sttemnt/sttemntViewExcel.do")
    public View sttemntViewExcel(@ModelAttribute SttemntVO sttemntVO, RpairVO rpairVO,ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
    	model.addAttribute("file_name", "포트홀 신고정보_"+ sttemntVO.getPTH_RG_NO() + "_" + DateUtil.getCurrentDateString("yyyyMMdd_hhmmss"));

		return new ExcelViewSttemnt();
    }

    /**
     * 담당자에게 SMS를 전송한다.
     * @author    : JOY
     * @date      : 2018. 3. 8.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/sttemnt/sendSMS.do" }, method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String sendSMS(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(def);

        String result = "";

        try {

            // SMS 전송
            smsService.sendSMS(sttemntVO);

            result = "{\"msg\" : \"success\"}";

            txManager.commit(txStatus);

            sttemntVO.setPRCS_STTUS("PRCS0002"); // 접수상태
            // 처리상태 UPDATE
            sttemntService.updatePotholeSttemnt(sttemntVO);
            sttemntService.updatePthSttmnt(sttemntVO);

        } catch (NullPointerException e) {

            txManager.rollback(txStatus);
            e.printStackTrace();
            return "{\"msg\" : \"fail-null\"}";

        } catch(Exception e) {

            txManager.rollback(txStatus);
            e.printStackTrace();
            return "{\"msg\" : \"fail-exception\"}";
        }

        return result;
    }


	/**
	 * 위치도(배경맵이미지)를 저장한다.
	 * @author    : YYK
     * @date      : 2018. 3. 13.
	 * @param
	 * @return "jsonView"
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/sttemnt/saveImageToSttemntView.do")
	public String saveImageToSttemntView( @ModelAttribute SttemntVO sttemntVO, HttpServletRequest request, HttpServletResponse res, Model model) throws Exception {
		Map<String, String> req = requestToHashMap(request);

		try {
			String decodeStr =  URLDecoder.decode(req.get("datas"), "UTF-8");

			// YYK 위치도에 들어갈 마커 크기를 키움
			decodeStr = decodeStr.replace("<width>34</width>", "<width>100</width>");
			decodeStr = decodeStr.replace("<height>34</height>", "<height>100</height>");
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
			String fileName =  "Map_"+dateFormat.format(calendar.getTime());

			if(fileName.equals("")) fileName = "save";

			mapService.setType("save");
			mapService.setRootPath(request.getSession().getServletContext().getRealPath("/"));
			BufferedImage image = mapService.createImages(decodeStr);


			// 2018. 03. 12. YYK
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(currentDate);

			String path_date = "/rpair/" + date + "/" ;


			// 폴더 경로
			String path = pathInfoProperties.getProperty("file.upload.path") + path_date ;

			File uploadFolder =  new File(checkFilePath(path,"path"));

			if(!uploadFolder.exists() || !uploadFolder.isFile()){
				uploadFolder.mkdirs();
			}

			if(uploadFolder.exists()){
				File file = new File( path +fileName+ ".png"); // 이미지저장경로
				ImageIO.write(image, "png", file); // 파일형식

				AttachFileVO fileVO = new AttachFileVO();

				fileVO.setFILE_NM(file.getName());
				fileVO.setFILE_COURS(path_date);
				fileVO.setORGINL_FILE_NM(fileName + ".png");
				//file.getFreeSpace();
				fileVO.setUSE_AT("Y");
				fileVO.setDELETE_AT("N");

				//File file = new File(pathInfoProperties.getProperty("file.upload.path") + "/rpair/" + "가즈아아.." + ".png"); // 이미지저장경로

				String fileNo = attachFileService.insertAttachFile(fileVO);

				sttemntVO.setLCDO(fileNo);

				model.addAttribute("fileVO", fileVO); //이미지 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return "jsonView";
	}

	/**
	 * 담당자 연락처 조회
	 * @author    : JOY
	 * @date      : 2018. 3. 19.
	 *
	 * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
	 * @return    : String
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/pothole/sttemnt/selectChargerCttpc.do" }, method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectChargerCttpc(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

	    Map<String, Object> map = new HashMap<String, Object>();

	    CmptncVO cmptncVO = new CmptncVO();
	    cmptncVO.setCHARGER_NM(sttemntVO.getCHARGER_NM());
	    CmptncVO result = cmptncService.selectChargerCttpc(cmptncVO);

	    if ( result != null ) {

	        map.put("cttpc", result.getCTTPC());

	    }

	    return map;

    }

	/**
     * 담당자, 연락처 조회
     * @author    : JOY
     * @date      : 2018. 3. 21.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : String
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/pothole/sttemnt/selectChargerNm.do" }, method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectChargerNm(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        CmptncVO cmptncVO = new CmptncVO();
        cmptncVO.setDEPT_CODE(sttemntVO.getDEPT_CODE());
        CmptncVO result = cmptncService.selectChargerNm(cmptncVO);

        if ( result != null ) {

            map.put("chargernm", result.getCHARGER_NM());
            map.put("cttpc", result.getCTTPC());

        }

        return map;

    }

    /**
     * 보정한 위치의 경위도 값을 조회한다.
     * @author    : JOY
     * @date      : 2018. 4. 4.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = { "/api/pothole/sttemnt/selectLatLon.do" }, method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody SttemntVO selectLatLon(@RequestBody SttemntVO sttemntVO, ModelMap model) throws Exception {

        //Map<String, Object> result = sttemntService.selectLatLon(sttemntVO);

    	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
    	sttemntVO.setPthmode(isEmptyValue(sttemntVO.getPthmode(),"T"));

        SttemntVO sttemntVO_ONE = sttemntService.selectPotholeSttemnt(sttemntVO);
        sttemntVO_ONE.setTM_X(sttemntVO.getTM_X());
        sttemntVO_ONE.setTM_Y(sttemntVO.getTM_Y());
        sttemntVO_ONE.setPthmode(sttemntVO.getPthmode());

        HashMap rst = new HashMap();

        try {

            rst = sttemntService.procPrcPthCortnLoc(sttemntVO_ONE);

        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        sttemntVO_ONE.setResultSuccess      ((String) rst.get("o_RESULT"));
        sttemntVO_ONE.setDEPT_CODE          ((String) rst.get("o_DEPT_CODE"));
        sttemntVO_ONE.setCORTN_X            ((String) rst.get("o_CORTN_X"));
        sttemntVO_ONE.setCORTN_Y            ((String) rst.get("o_CORTN_Y"));
        sttemntVO_ONE.setTM_X               ((String) rst.get("o_TM_X"));
        sttemntVO_ONE.setTM_Y               ((String) rst.get("o_TM_Y"));
        sttemntVO_ONE.setLNM_ADRES          ((String) rst.get("o_LNM_ADRES"));
        sttemntVO_ONE.setDPLCT_STTEMNT_AT   ((String) rst.get("o_DPLCT_STTEMNT_AT"));
        sttemntVO_ONE.setDSM_RP_NO          ((String) rst.get("o_DSM_RP_NO"));
        sttemntVO_ONE.setCPT_MNG_NO         ((String) rst.get("o_CPT_MNG_NO"));
        sttemntVO_ONE.setCHARGER_NM         ((String) rst.get("o_CHARGER_NM"));
        sttemntVO_ONE.setCTTPC              ((String) rst.get("o_CTTPC"));

        return sttemntVO_ONE;

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/pothole/sttemnt/sttemntPthMPhoto.do" })
    public String selectPthMPhoto(@ModelAttribute("searchVO") SttemntVO sttemntVO, @RequestParam(value="pth_photo", required=false) String pthPhoto, ModelMap model) throws Exception {

    	model.addAttribute("photo", pthPhoto); //이미지 출력

        return "/pothole/sttemnt/sttemntPhoto" ;

    }
}
