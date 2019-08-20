package kr.go.gg.gpms.sysuser.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.authority.service.AuthorityService;
import kr.go.gg.gpms.authority.service.model.AuthorityVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.CompanyService;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.menu.service.MenuService;
import kr.go.gg.gpms.menu.service.model.MenuVO;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;
import kr.go.gg.gpms.userauth.service.UserAuthService;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;

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
//import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Class Name : SysUserController.java
 * @Description : SysUser Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller("sysUserController")
public class SysUserController extends BaseController {

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "authorityService")
	private AuthorityService authorityService;

	@Resource(name = "userAuthService")
	private UserAuthService userAuthService;

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "passwordEncoder")
	private PasswordEncoder passwordEncoder;

	@Resource(name = "menuService")
	private MenuService menuService;


	@Autowired
	SessionManager sessionManager;

	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다. (pageing)
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/manage/sysuser/SysUserList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysuser/selectSysUserList.do" })
	public String selectSysUserList(SysUserVO sysUserVO, ModelMap model) throws Exception {
		sysUserVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysUserVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getPageSize());

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserList(sysUserVO);
		model.addAttribute("items", items);

		int totCnt = sysUserService.selectSysUserListTotalCount(sysUserVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/sysuser/SysUserList";
	}

	@RequestMapping(value = { "/mng/sysuser/sysUserList.do" })
	public String selectSysUserListGrid(SysUserVO sysUserVO,DeptVO deptVO, CompanyVO companyVO, CodeVO codeVO, HttpSession session, ModelMap model) throws Exception {
		setSystem("mng");

		//사용자 구분 코드 조회
		codeVO.setUSE_AT("Y");
		codeVO.setDELETE_AT("N");
		codeVO.setUsePage(false);
		codeVO.setCL_CODE("URSE");
		List<CodeVO> userSeList = codeService.selectCodeList(codeVO);

		//부서 정보
		deptVO.setODR("2");
		List<DeptVO> deptCdList = deptService.selectDeptCodeList(deptVO);

		//업체 정보
		companyVO.setUSE_AT("Y");
		companyVO.setUsePage(false);
		companyVO.setSidx("CO_NO");
		companyVO.setSord("asc");
		List<CompanyVO> compList = companyService.selectCompanyList(companyVO);

		model.addAttribute("sysUserVO", sysUserVO);
		model.addAttribute("deptCdList", deptCdList);
		model.addAttribute("compList", compList);
		model.addAttribute("userSeList", userSeList);

		return "/mng/user/userMngList";
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다. (pageing)
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/manage/sysuser/SysUserList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/sysuser/selectSysUserList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SysUserVO> selectSysUserListRest(@RequestBody SysUserVO sysUserVO, ModelMap model, HttpSession session) throws Exception {
		sysUserVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysUserVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getPageSize());

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserList(sysUserVO);
		return items;
	}

	/**
	 * 사용자 신청 목록 화면을 조회한다
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/mng/sysuser/selectApplyUser.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/sysuser/applyUserList.do" })
	public String selectApplyUser(@ModelAttribute("searchVO") SysUserVO sysUserVO, DeptVO deptVO, CompanyVO companyVO, CodeVO codeVO, ModelMap model) throws Exception {
		setSystem("mng");

		//사용자 구분 코드 조회
		codeVO.setUSE_AT("Y");
		codeVO.setDELETE_AT("N");
		codeVO.setUsePage(false);
		codeVO.setCL_CODE("URSE");
		List<CodeVO> userSeList = codeService.selectCodeList(codeVO);

		//부서 정보
		deptVO.setODR("2");
		List<DeptVO> deptCdList = deptService.selectDeptCodeList(deptVO);

		//업체 정보
		companyVO.setUSE_AT("Y");
		companyVO.setUsePage(false);
		companyVO.setSidx("CO_NO");
		companyVO.setSord("asc");
		List<CompanyVO> compList = companyService.selectCompanyList(companyVO);

		model.addAttribute("sysUserVO", sysUserVO);
		model.addAttribute("deptCdList", deptCdList);
		model.addAttribute("compList", compList);
		model.addAttribute("userSeList", userSeList);

		return "/mng/user/applyUserMngList";
	}

	/**
	 * 사용자 신청 목록을 조회한다
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/mng/sysuser/selectApplyUser.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/sysuser/selectApplyUser.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectApplyUserRest(@RequestBody SysUserVO sysUserVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPage());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getRows());

		sysUserVO.setUsePage(true);
		sysUserVO.setUSE_AT("Y");
		sysUserVO.setDELETE_AT("N");
		sysUserVO.setCONFM_AT("N");

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserList(sysUserVO);
		int totCnt = sysUserService.selectSysUserListTotalCount(sysUserVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", sysUserVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	@RequestMapping(value = { "/api/sysuser/applyConfirm.do" })
	public String applyConfirm(@ModelAttribute("searchVO") SysUserVO sysUserVO, UserAuthVO userAuthVO, ModelMap model) throws Exception {

		if (sysUserVO.getUSER_NO() == null) {
			return "jsonView";
		}

		// 사용자 승인 신청
		BindBeansToActiveUser(sysUserVO);
		sysUserVO.setCONFM_AT("Y");
		sysUserVO.setCONFM_DT(new Date(System.currentTimeMillis()));
		/*sysUserVO.setCONFM_AUTHOR_AT("N");*/
		sysUserService.updateSysUser(sysUserVO);

		//사용자 권한 삭제
        userAuthService.deleteUserAuthByUserNo(userAuthVO);

		// 사용자 그룹 등록
		BindBeansToActiveUser(userAuthVO);
		userAuthVO.setAUTHOR_ID(sysUserVO.getREQ_USER_GRP());
		userAuthService.insertUserAuth(userAuthVO);

        // 2018. 03. 13. JOY. tmp 삭제.
        sysUserService.deleteSysUserChg(sysUserVO);

		// 사용자 메뉴 등록
		if(sysUserVO.getREQ_MENUACC_ROLE() != null && !sysUserVO.getREQ_MENUACC_ROLE().trim().equals("")){
			UserAuthVO menuAuth = new UserAuthVO();
			String[] menuAuthList = sysUserVO.getREQ_MENUACC_ROLE().split(",");

			for(String userMenuAuth : menuAuthList){
				BindBeansToActiveUser(menuAuth);
				menuAuth.setUSER_NO(sysUserVO.getUSER_NO());
				menuAuth.setAUTHOR_ID(userMenuAuth);
				userAuthService.insertUserAuth(menuAuth);
			}
		}

		/*String[] userNoList = sysUserVO.getUSER_NO().split(",");
		int totCnt = 0;

		for (String userNo : userNoList) {
			sysUserVO.setUSER_NO(userNo);
			sysUserVO.setCONFM_AT("Y");
			sysUserVO.setCONFM_DT(new Date(System.currentTimeMillis()));

			sysUserService.updateSysUser(sysUserVO);
			totCnt++;
		}

		model.addAttribute("totCnt", totCnt);*/

		return "jsonView";
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다. (pageing)
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/manage/sysuser/SysUserList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysuser/selectSysUserListPage.do" })
	public String selectSysUserListPage(SysUserVO sysUserVO, ModelMap model) throws Exception {
		sysUserVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		sysUserVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getPageSize());

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserList(sysUserVO);
		model.addAttribute("items", items);

		int totCnt = sysUserService.selectSysUserListTotalCount(sysUserVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/sysuser/SysUserList";
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다. (pageing)
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/manage/sysuser/SysUserList"
	 * @exception Exception
	 */
	// @RequestMapping(value = { "/api/sysuser/selectSysUserListPage.do" },
	// method = RequestMethod.POST, consumes = {
	// MediaType.APPLICATION_JSON_VALUE }, produces = {
	// MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(value = { "/api/sysuser/selectSysUserListPage.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSysUserListPageRest(@RequestBody SysUserVO sysUserVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPage());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getRows());

		sysUserVO.setUsePage(true);
		sysUserVO.setUSE_AT("Y");
		sysUserVO.setDELETE_AT("N");
		sysUserVO.setCONFM_AT("Y");

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserList(sysUserVO);
		int totCnt = sysUserService.selectSysUserListTotalCount(sysUserVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", sysUserVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 상세를 조회한다.
	 *
	 * @param sysUserVO
	 *            - 조회할 정보가 담긴 SysUserVO
	 * @return "/manage/sysuser/SysUserView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/sysuser/selectSysUser.do" })
	public String selectSysUser(@ModelAttribute("searchVO") SysUserVO sysUserVO, AuthorityVO authorityVO, DeptVO deptVO, CompanyVO companyVO, CodeVO codeVO, ModelMap model) throws Exception {
		String userSeType = "";

		sysUserVO.setUSE_AT("Y");
		sysUserVO.setDELETE_AT("N");
		if ( sysUserVO.getUSER_FLAG().equals("N") ) {
		    sysUserVO = sysUserService.selectSysUser(sysUserVO);
		    sysUserVO.setUSER_FLAG("N");
		} else {
		    sysUserVO = sysUserService.selectSysUserChg(sysUserVO);
		    sysUserVO.setUSER_FLAG("C");
		}

		//사용자 구분 코드 조회
		codeVO.setUSE_AT("Y");
		codeVO.setDELETE_AT("N");
		codeVO.setUsePage(false);
		codeVO.setCL_CODE("URSE");
		List<CodeVO> userSeList = codeService.selectCodeList(codeVO);

		//부서 정보
		deptVO.setODR("2");
		List<DeptVO> deptCdList = deptService.selectDeptCodeList(deptVO);

		//업체 정보
		companyVO.setUSE_AT("Y");
		companyVO.setSidx("CO_NO");
		companyVO.setSord("asc");
		companyVO.setUsePage(false);
		List<CompanyVO> compList = companyService.selectCompanyList(companyVO);

		/*//사용자 권한 정보
		authorityVO.setUsePage(false);
		List<AuthorityVO> authList = authorityService.selectAuthorityList(authorityVO);*/


		if(sysUserVO.getUSER_SE_CODE().equals("URSE0001")){ //내부고객
			userSeType = "I";

			//사용자의 상위 부서 목록 정보
			deptVO.setODR("");
			deptVO.setDEPT_CODE(sysUserVO.getDEPT_CODE());
			List<DeptVO> shighDeptList = deptService.selectSHighDeptList(deptVO);

			DeptVO dept_1 = new DeptVO();
			DeptVO dept_2 = new DeptVO();
			DeptVO dept_3 = new DeptVO();

			switch(shighDeptList.size()){
				case 1:
					dept_1 = shighDeptList.get(0);
					break;
				case 2:
					dept_1 = shighDeptList.get(0);
					dept_2 = shighDeptList.get(1);
					break;
				case 3:
					dept_1 = shighDeptList.get(0);
					dept_2 = shighDeptList.get(1);
					dept_3 = shighDeptList.get(2);
					break;
			}

			model.addAttribute("dept_1", dept_1);
			model.addAttribute("dept_2", dept_2);
			model.addAttribute("dept_3", dept_3);
		}else{	//외부 고객
			userSeType = "O";
		}

		//사용자 그룹
		AuthorityVO authGroup = new AuthorityVO();
		authGroup.setUserGroup(true);
		authGroup.setUsePage(false);
		List<AuthorityVO> groupList = authorityService.selectAuthorityList(authGroup);

		//메뉴접근 권한
		authGroup.setUserGroup(false);
		authGroup.setMenuAuth(true);
		List<AuthorityVO> menuAuthList = authorityService.selectAuthorityList(authGroup);


		model.addAttribute("deptCdList", deptCdList);
		model.addAttribute("compList", compList);
		model.addAttribute("userSeList", userSeList);
		model.addAttribute("result", sysUserVO);
		model.addAttribute("type", userSeType);
		/*model.addAttribute("authList", authList);*/
		model.addAttribute("groupList", groupList);
		model.addAttribute("menuAuthList", menuAuthList);

		return "/mng/user/userMngView";
	}


	// 사용자 등록 요청 페이지 조회
	@RequestMapping(value = { "/user/sysuser/registSysUser.do" })
	public String registSysUser(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, DeptVO deptVO, CompanyVO companyVO, CodeVO codeVO, Model model) throws Exception {

		//부서 정보
		deptVO.setODR("2");
		List<DeptVO> deptCdList = deptService.selectDeptCodeList(deptVO);

		//업체 정보
		companyVO.setUSE_AT("Y");
		companyVO.setSidx("CO_NO");
		companyVO.setSord("asc");
		companyVO.setUsePage(false);

		List<CompanyVO> compList = companyService.selectCompanyList(companyVO);

		//사용자 그룹
		AuthorityVO authGroup = new AuthorityVO();
		authGroup.setUserGroup(true);
		authGroup.setUsePage(false);
		List<AuthorityVO> groupList = authorityService.selectAuthorityList(authGroup);

		//메뉴접근 권한
		authGroup.setUserGroup(false);
		authGroup.setMenuAuth(true);
		List<AuthorityVO> menuAuthList = authorityService.selectAuthorityList(authGroup);

		model.addAttribute("deptCdList", deptCdList);
		model.addAttribute("compList", compList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("menuAuthList", menuAuthList);

		return "/user/userRegist";
	}

	// 개인정보 승신 여부 페이지 조회
	@RequestMapping(value = { "/user/sysuser/infoAgreeCheck.do" })
	public String infoStplat(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model) throws Exception {
		return "/user/infoStplatView";
	}
	
	@RequestMapping(value = { "/user/sysuser/infoAgreeCheckPth.do" })
    public String infoStplatPth(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model) throws Exception {
        return "/user/infoStplatViewPth";
    }

	// 사용자 아이디 중복 체크
	@RequestMapping(value = { "/api/sysuser/checkUserIdOverlap.do" })
	public String checkUserIdOverlap(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model) throws Exception {
		boolean check = false;

		sysUserVO.setUSE_AT("Y");
		sysUserVO.setDELETE_AT("N");

		sysUserVO = sysUserService.selectSysUserByID(sysUserVO);

		if(sysUserVO == null){
			check = true;
		}

		model.addAttribute("result", check);

		return "jsonView";
	}

	//사용자 등록
	@RequestMapping(value = { "/api/sysuser/regSysUser.do" })
	public String regSysUser(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model) throws Exception {
		Date curDt = new Date(System.currentTimeMillis());

		sysUserVO.setUSE_AT("Y");
		sysUserVO.setDELETE_AT("N");

		sysUserVO.setCONFM_AT("N");
		/*sysUserVO.setCONFM_AUTHOR_AT("N");*/

		BindBeansToActiveUser(sysUserVO);

		sysUserVO.setSTPLAT_AGRE_DT(curDt);

		// 사용자 구분 코드
		if(sysUserVO.getUSER_SE_CODE().equals("in")){
			sysUserVO.setUSER_SE_CODE("URSE0001"); //내부
		}else{
			sysUserVO.setUSER_SE_CODE("URSE0002"); //외부
		}

		// 비밀번호 암호화
		String encPassword = passwordEncoder.encode(sysUserVO.getSECRET_NO().trim());
		sysUserVO.setSECRET_NO(encPassword);

		try {
		sysUserService.insertSysUser(sysUserVO);
		}catch(Exception e) {
		    e.printStackTrace();
		}

		return "jsonView";
	}

	//사용자 수정
	@RequestMapping(value = { "/api/sysuser/updtSysUser.do" })
	public String updtSysUserRest(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, UserAuthVO userAuthVO,HttpServletRequest request, Model model) throws Exception {
		BindBeansToActiveUser(sysUserVO);
		// 2018. 01. 08. JOY. TN_SYS_USER 테이블에 update 안되는 것 수정.
		sysUserVO.setREQ_MENUACC_ROLE(sysUserVO.getMENUACC_ROLE());
		sysUserService.updateSysUser(sysUserVO);

		//사용자 권한 삭제
		userAuthService.deleteUserAuthByUserNo(userAuthVO);

		// 사용자 그룹 등록
		BindBeansToActiveUser(userAuthVO);
		userAuthVO.setAUTHOR_ID(sysUserVO.getUSER_GRP());
		userAuthService.insertUserAuth(userAuthVO);

		// 사용자 메뉴 등록
		if(sysUserVO.getMENUACC_ROLE() != null && !sysUserVO.getMENUACC_ROLE().trim().equals("")){
			UserAuthVO menuAuth = new UserAuthVO();
			String[] menuAuthList = sysUserVO.getMENUACC_ROLE().split(",");

			for(String userMenuAuth : menuAuthList){
				BindBeansToActiveUser(menuAuth);
				menuAuth.setUSER_NO(sysUserVO.getUSER_NO());
				menuAuth.setAUTHOR_ID(userMenuAuth);
				userAuthService.insertUserAuth(menuAuth);
			}
		}

		model.addAttribute(sysUserVO);
		return "jsonView";
	}

	@SuppressWarnings("unused")
    @RequestMapping(value = { "/api/sysuser/delSysUser.do" })
	public String delSysUserRest(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, HttpServletRequest request, Model model) throws Exception {
		Date curDt = new Date(System.currentTimeMillis());
		BindBeansToActiveUser(sysUserVO);

		sysUserVO.setDELETE_AT("Y");

		if ( sysUserVO.getUSER_FLAG().equals("C") ) {

		    sysUserService.deleteSysUserChg(sysUserVO);

		} else {

		    sysUserService.deleteSysUser(sysUserVO);

		}



		return "jsonView";
	}

	@SuppressWarnings("static-access")
    @RequestMapping(value="/api/sysuser/updateUserView.do")
    public String updateUserView(
    		@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model, HttpServletRequest request, HttpSession session)
            throws Exception {

	    // 2018. 03. 12. JOY. 사용자권한
        AuthorityVO authGroup = new AuthorityVO();
        authGroup.setUserGroup(false);
        authGroup.setMenuAuth(true);
        authGroup.setFirstIndex(0);
        List<AuthorityVO> menuAuthList = authorityService.selectAuthorityList(authGroup);
        model.addAttribute("menuAuthList", menuAuthList);

        sysUserVO.setUSER_NO(sessionManager.getUserNo());
        sysUserVO.setUSE_AT("Y");
        sysUserVO.setDELETE_AT("N");
        sysUserVO = sysUserService.selectSysUser(sysUserVO);
        model.addAttribute("result", sysUserVO);

        model.addAttribute("chgAuth", sysUserService.selectSysUserChg(sysUserVO));

    	return "/user/userUpdate";
    }


	/**
	 * 사용자 패스워드 변경
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
    @SuppressWarnings({ "static-access", "unused" })
    @RequestMapping(value="/api/sysuser/updateUserSec.do")
    public String updateUserSec(
    		@ModelAttribute("sysUserVO") SysUserVO sysUserVO, Model model, HttpServletRequest request, HttpSession session)
            throws Exception {

    	// common 결과처리 변수 [수정X]
		String resultCode = "";
		String resultMsg = "";
    	int resultCnt = 0;
    	String callBackFunction = "";

    	String encBEFPassword = passwordEncoder.encode(sysUserVO.getBEF_SECRET_NO().trim());
    	String encPassword = passwordEncoder.encode(sysUserVO.getSECRET_NO().trim());

    	sysUserVO.setUSER_ID(sessionManager.getUserId());
    	sysUserVO = sysUserService.selectSysUserByID(sysUserVO);


    	try {
    		//이전 암호 체크
    		if(!sysUserVO.getSECRET_NO().equals(encBEFPassword)) {
    			resultMsg = "이전암호가 일치하지 않습니다.";
    		} else {
        		sysUserVO.setSECRET_NO(encPassword);
        		BindBeansToActiveUser(sysUserVO);

        		sysUserService.updateSysUser(sysUserVO);
        		resultMsg = "암호변경이 정상적으로 처리되었습니다.";
        	}

			resultCode = "MSG";
    	} catch(Exception e) {
    		resultCode = "ERROR";
    		resultMsg = e.toString();
    	}

		// 결과 처리용 [수정X]
    	model.addAttribute("resultCode", resultCode);
    	model.addAttribute("resultCnt", resultCnt);
    	model.addAttribute("resultMsg", resultMsg);
    	model.addAttribute("callBackFunction", nvl(sysUserVO.getCallBackFunction()));	// 처리후 호출 함수

    	return "/cmmn/commonMsg";
    }

    /**
     * 사용자 권한 변경 요청
     * @author    : JOY
     * @date      : 2018. 3. 12.
     *
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : Map<String, Object>
     * @exception : Exception
     */
    @RequestMapping(value="/api/sysuser/updateUserAuth.do", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> updateUserAuth(@RequestBody SysUserVO sysUserVO, Model model, HttpServletRequest request, HttpSession session)throws Exception {

        SysUserVO sysUserVO_ONE = new SysUserVO();
        sysUserVO_ONE = sysUserService.selectSysUser(sysUserVO);

        sysUserVO_ONE.setREQ_MENUACC_ROLE(sysUserVO.getREQ_MENUACC_ROLE());
        sysUserVO_ONE.setUSE_AT("Y");
        sysUserVO_ONE.setDELETE_AT("N");

        Map<String, Object> map = new HashMap<String, Object>();

        int tmpCnt = sysUserService.selectSysUserChgAuthListCnt(sysUserVO_ONE);

        if ( tmpCnt > 0 ) {

            map.put("msg", "이미 진행중인 권한 요청이 있습니다.");

        } else {

            sysUserService.insertSysUserChgAuth(sysUserVO_ONE);
            map.put("msg", "권한 변경 요청이 완료되었습니다.\n관리자 승인 후에 요청한 권한이 부여됩니다.");

        }

        return map;

    }

    /**
     * 사용자 권한 변경 요청 취소
     * @author    : JOY
     * @date      : 2018. 3. 16.
     *
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : Map<String, Object>
     * @exception : Exception
     */
    @RequestMapping(value="/api/sysuser/updateUserAuthCancel.do", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> updateUserAuthCancel(@RequestBody SysUserVO sysUserVO, Model model, HttpServletRequest request, HttpSession session)throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        try {

            sysUserService.deleteSysUserChg(sysUserVO);
            map.put("msg", "권한변경 요청을 취소를 완료하였습니다.");

        } catch (Exception e) {

            e.printStackTrace();
            map.put("msg", "권한변경 요청 취소를 실패하였습니다.");

        }

        return map;

    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value={"/mng/sysuser/selectSysUserLog.do"})
    public String selectSysUserLog(SysUserVO sysUserVO, Model model) throws Exception {

    	MenuVO menuVO = new MenuVO();
    	List<MenuVO> menuList = menuService.selectSysCode2List(menuVO);

    	model.addAttribute("menu_list", menuList);

        // 최소 최대 날짜
        SysUserVO sysUser_creatDt = sysUserService.selectMinMaxYear();
        model.addAttribute("minyear", sysUser_creatDt.getMIN_YEAR());
        model.addAttribute("maxyear", sysUser_creatDt.getMAX_YEAR());

    	return "/user/userLogList";
    }


	/**
	 * 시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다. (pageing)
	 *
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return "/api/sysuser/selectSysUserLogList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/sysuser/selectSysUserLogList.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectSysUserLogList(@RequestBody SysUserVO sysUserVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysUserVO.getPage());
		paginationInfo.setRecordCountPerPage(sysUserVO.getPageUnit());
		paginationInfo.setPageSize(sysUserVO.getRows());

		sysUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sysUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SysUserVO> items = sysUserService.selectSysUserLogList(sysUserVO);
		int totCnt = sysUserService.selectSysUserLogListTotalCount(sysUserVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", sysUserVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	@SuppressWarnings("rawtypes")
    @RequestMapping(value="/mng/sysuser/selectSysUserLogListExcel.do")
    public View selectSysUserLogListExcel(@ModelAttribute SysUserVO sysUserVO,	ModelMap model, HttpServletRequest request, HttpSession session)  throws Exception {
        List dataList = sysUserService.selectSysUserLogListExcel(sysUserVO);

        String[] excel_title  = {"사용자ID","사용자명","소속기관","접속메뉴","접속일시"};
        String[] excel_column = {"USER_ID","USER_NM","DEPT_NM","MENU_NM","CREAT_DT"};

        //model.addAttribute("file_name",    cntrwkVO.getEXCEL_FILE_NM() + "_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("file_name",    "사용자접속로그_" + DateUtil.getCurrentDateString("yyyy-MM-dd"));
        model.addAttribute("excel_title",  excel_title);
        model.addAttribute("excel_column", excel_column);
        model.addAttribute("data_list",    dataList);

        return new ExcelView();
    }

	/**
	 * 사용자 신청 건수 조회
	 * @author    : JOY
	 * @date      : 2018. 1. 9.
	 *
	 * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return    : int
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/sysuser/selectApplyUserCnt.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int selectApplyUserCnt(@RequestBody SysUserVO sysUserVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        sysUserVO.setUSE_AT("Y");
        sysUserVO.setDELETE_AT("N");
        sysUserVO.setCONFM_AT("N");
        sysUserVO.setUsePage(false);
        int usrCnt = sysUserService.selectSysUserListTotalCount(sysUserVO);

        return usrCnt;
    }
	
	/**
     * 사용자 부서 조회
     * @author    : JOY
     * @date      : 2018. 5. 10.
     *
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/sysuser/selectApplyUserDept.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody DeptVO selectApplyUserDept(@RequestBody DeptVO deptVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        DeptVO dept = deptService.selectDept(deptVO);

        return dept;
        
    }
    
    /**
     * 사용자 부서 조회
     * @author    : JOY
     * @date      : 2018. 5. 10.
     *
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : int
     * @exception : Exception
     */
    @RequestMapping(value = { "/api/sysuser/updateUserPswd.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody int updateUserPswd(@RequestBody SysUserVO sysUserVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

        Date date = new Date();
        
        sysUserVO.setPASSWORD_CHANGE_DT(date);
        sysUserVO.setSECRET_NO("vYd8+U8aYxI8i+xavdEmuYg2GYnkc++4D8bNW18hoLg4UCEslLUKcBhTiqdB7l2mVhC02mwY5C7a6gfhvlYpzw==");
        sysUserVO.setUPDUSR_NO("3");
        
        return sysUserService.updateSysUser(sysUserVO);
        
    }
}
