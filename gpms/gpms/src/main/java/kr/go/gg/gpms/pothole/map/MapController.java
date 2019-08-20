package kr.go.gg.gpms.pothole.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cell10.service.Cell10Service;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.map.service.MapService;
import kr.go.gg.gpms.pothole.sttemnt.service.SttemntService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

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

import com.ibatis.sqlmap.client.SqlMapException;

import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.security.service.impl.CustomAuthenticationProvider;
//import org.springframework.security.core.context.SecurityContextHolder;

@Controller("goodmapController")
public class MapController extends BaseController{

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "deptService")
	protected DeptService deptService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "customAuthenticationProvider")
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Resource(name = "mapService")
	private MapService mapService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	@Resource(name = "cell10Service")
	private Cell10Service cell10Service;

	@Resource(name = "sttemntService")
	private SttemntService sttemntService;


	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

	@RequestMapping(value = "/goodmap.do")
	public String map(@ModelAttribute MemberInfo memberInfoVO, DeptVO deptVO, SttemntVO sttemntVO,ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		//레이어 정보 조회
		List<EgovMap> layerInfoList = cmmnService.selectLaygerList(null);
		List<EgovMap> layerInfoListTheme = cmmnService.selectLayerListTheme(null);
		List<EgovMap> layerGroupInfoList = cmmnService.selectLyrGroupInfo(null);

		model.addAttribute("layerInfoList", layerInfoList);           // 파손유형별 조회 레이어
		model.addAttribute("layerInfoListTheme", layerInfoListTheme);
		model.addAttribute("layerGroupInfoList", layerGroupInfoList);

		session.setAttribute("system", "goodmap");

		// 2018. 04. 19. JOY. 현재 사용자 부서 비교
        MemberInfo memberinfo = SessionManager.getCurrentUser();
        String usrDeptCode = memberinfo.getDEPT_CODE();

        SttemntVO sttemntVO_USER = new SttemntVO();
        sttemntVO_USER.setDEPT_CODE(usrDeptCode);
        usrDeptCode = sttemntService.selectHighDeptCode(sttemntVO_USER);

        model.addAttribute("usrDeptCode", usrDeptCode);

		return "/pothole/map/goodmap";
	}

	@RequestMapping(value = { "/api/map/selectPrcsSttusListCnt.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectPrcsSttusListCnt(@RequestBody  SttemntVO sttemntVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        // 지도 - 처리상태 별 데이터 조회
        int totCnt = sttemntService.selectPotholeSttemntListTotalCount(sttemntVO);
        map.put("totCnt", totCnt);

        return map;
	}

	/**
	 * 지도 - 포트홀 신고 현황 조회
	 * @author    : JOY
	 * @date      : 2018. 2. 20.
	 *
	 * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
	 * @return    : Map<String, Object>
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/map/selectPrcsSttusList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectPrcsSttusList(@RequestBody  SttemntVO sttemntVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

	    Map<String, Object> map = new HashMap<String, Object>();
	    try{
        // 지도 - 처리상태 별 데이터 조회
	    List<SttemntVO> resultlst1 = sttemntService.selectPrcsSttusList(sttemntVO);
	    map.put("prcsSttus", resultlst1);

        for ( int i = 1; i <= 7; i++ ) {

            sttemntVO.setPRCS_STTUS("PRCS000" + i);

            List<SttemntVO> result = sttemntService.selectPrcsSttusList(sttemntVO);
            map.put("prcsSttus" + i, result);

        }

        // 지도 - 파손유형 별 데이터 조회
        List<SttemntVO> resultlst2 = sttemntService.selectDmgTypeList(sttemntVO);
        map.put("dmgType", resultlst2);

        for ( int i = 1; i <= 8; i++ ) {

            sttemntVO.setDMG_TYPE("DMGT000" + i);

            List<SttemntVO> result = sttemntService.selectDmgTypeList(sttemntVO);
            map.put("dmgType" + i, result);

        }

        // 지도 - 민자도로사업자 신고 건 조회
        map.put("prvRdOprtCnt", sttemntService.selectPrvRdOprtCount(sttemntVO));
	    } catch(SqlMapException e) {
	        e.printStackTrace();
	    } catch(NullPointerException e) {
	        e.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
        return map;

    }

	/**
     * 지도 - 포트홀 신고 건 조회
     * @author    : JOY
     * @date      : 2018. 5. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : Map<String, Object>
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/map/selectSttemntData.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectSttemntData(@RequestBody  SttemntVO sttemntVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        try{

        	// 접수경로 구분 초기화 - 구분값이 없을 경우, 스마트 카드 기본 조회됨.
        	if("".equals(sttemntVO.getPthmode()) || sttemntVO.getPthmode() == null ){

        		sttemntVO.setPthmode("T");

            }else{

            	sttemntVO.setPthmode(sttemntVO.getPthmode());
            }

            map.put("data", sttemntService.selectPotholeSttemnt(sttemntVO));

        } catch(SqlMapException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return map;

    }

}
