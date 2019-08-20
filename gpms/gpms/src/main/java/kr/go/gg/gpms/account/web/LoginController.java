package kr.go.gg.gpms.account.web;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;
import kr.go.gg.gpms.userconnect.service.UserConnectService;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmmn.web.LoginManager;
import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.security.service.impl.CustomAuthenticationProvider;

@Controller("loginController")
public class LoginController extends BaseController {

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	@Resource(name = "customAuthenticationProvider")
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Resource(name = "userConnectService")
	private UserConnectService userConnectService;

	@Resource(name = "passwordEncoder")
	private PasswordEncoder passwordEncoder;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	SessionManager sessionManager;

	@SuppressWarnings("unused")
    @RequestMapping(value = "/login.do")
	public String login(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		// SayHello sayHello = new SayHello();

		String userId = memberInfoVO.getUSER_ID();
		String password = URLDecoder.decode(memberInfoVO.getPassword(), "UTF-8");

		String hello = (userId + " asd!!~~~~~~~~~~~~ ");
		LOGGER.info(hello);
		SysUserVO sysUserMember = null;

		memberInfoVO.setCONFM_AT("Y");
		sysUserMember = sysUserService.selectSysUserByID(memberInfoVO);

		if (sysUserMember != null) {

			// 2018. 01. 08. JOY. 시스템 접근 권한 체크
			String[] authArr = sysUserMember.getREQ_MENUACC_ROLE().trim().split(",");

			if ( authArr.length == 1 && authArr[0].equals("ROLE_USER_POTHOLE") ) {

			    model.addAttribute("resultCode", "LOGIN_FAIL");
		        model.addAttribute("resultMsg", "접근 권한이 없습니다.");
		        model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));    // 처리후 호출 함수
		        return "/cmmn/commonMsg";

			}
			// ========================================

			String encPassword = passwordEncoder.encode(memberInfoVO.getPassword().trim());

			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss.SSSSSSSSS", Locale.KOREA);

			if (sysUserMember != null) {
				// not compare sting equals
				if (passwordEncoder.matches(password, sysUserMember.getSECRET_NO().trim()) && sysUserMember.getCONFM_AT().equals("Y")) {

					//사용자 중복체크
					if(sessionManager.checkDuplicate(sysUserMember.getUSER_NO())){
						model.addAttribute("resultCode", "DUPLICATE_USER");
						model.addAttribute("resultMsg", "현재 접속 중인 사용자입니다. 연결을 끊고 재접속 하시겠습니까?");
						model.addAttribute("userid", userId);	// 처리후 호출 함수
						model.addAttribute("userpw", password);	// 처리후 호출 함수
						model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));	// 처리후 호출 함수
						return "/cmmn/commonMsg";
					}

					// id and password match ok
					session = request.getSession();
					SessionManager.setMaxInactiveInterval(3600);
					sessionManager.setSession(session);
					sessionManager.setLoginManagerConfig(session, sysUserMember.getUSER_NO());
					sessionManager.setUser(sysUserMember.getUSER_ID());
					sessionManager.setAttribute("start_date", formatter.format(currentTime));
					doSpringSecurityAutoLogin(sysUserMember.getUSER_ID(), sysUserMember.getSECRET_NO(), request);


					//사용자 접속 로그 등록
					UserConnectVO userCon = new UserConnectVO();
					userCon.setUSER_NO(sysUserMember.getUSER_NO());
					userCon.setSESION_ID(request.getSession().getId());

					//LOGGER.info("userNo:" + userCon.getUSER_NO());
					//LOGGER.info("userNo:" + userCon.getSESION_ID());

					//String conNo = userConnectService.insertUserConnect(userCon);

					model.addAttribute("resultCode", "CALLBACK");
					model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));	// 처리후 호출 함수
					return "/cmmn/commonMsg";
				} else {
					sessionManager.setUserInfo(null);
				}
			} else {
				sessionManager.setUserInfo(null);
			}
		}

		model.addAttribute("resultCode", "LOGIN_FAIL");
		model.addAttribute("resultMsg", "일치하는 사용자 정보가 없습니다.");
		model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));	// 처리후 호출 함수
		return "/cmmn/commonMsg";
	}

	// 2018. 01. 02. JOY.
	@SuppressWarnings("unused")
    @RequestMapping(value = "/goodlogin.do")
    public String goodogin(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
        // SayHello sayHello = new SayHello();

        String userId = memberInfoVO.getUSER_ID();
        String password = URLDecoder.decode(memberInfoVO.getPassword(), "UTF-8");

        String hello = (userId + " : welcome goodmorning mornitoring system ! ");
        LOGGER.info(hello);
        SysUserVO sysUserMember = null;

        memberInfoVO.setCONFM_AT("Y");
        sysUserMember = sysUserService.selectSysUserByID(memberInfoVO);

        if (sysUserMember != null) {

            // 2018. 01. 08. JOY. 시스템 접근 권한 체크
            String[] authArr = sysUserMember.getREQ_MENUACC_ROLE().trim().split(",");
            boolean allow = false;

            for ( int i = 0; i < authArr.length; i++ ) {

                if ( authArr[i].equals("ROLE_USER_POTHOLE") ) {

                    allow = true;
                    break;

                }

            }

            if ( allow == false ) {

                model.addAttribute("resultCode", "LOGIN_FAIL");
                model.addAttribute("resultMsg", "접근 권한이 없습니다.");
                //model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));    // 처리후 호출 함수
                return "/cmmn/commonMsg";

            }
            // ========================================

            String encPassword = passwordEncoder.encode(memberInfoVO.getPassword().trim());

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss.SSSSSSSSS", Locale.KOREA);

            if (sysUserMember != null) {
                // not compare sting equals
                if (passwordEncoder.matches(password, sysUserMember.getSECRET_NO().trim()) && sysUserMember.getCONFM_AT().equals("Y")) {

                    //사용자 중복체크
                    if(sessionManager.checkDuplicate(sysUserMember.getUSER_NO())){
                        model.addAttribute("resultCode", "DUPLICATE_USER");
                        model.addAttribute("resultMsg", "현재 접속 중인 사용자입니다. 연결을 끊고 재접속 하시겠습니까?");
                        model.addAttribute("userid", userId);   // 처리후 호출 함수
                        model.addAttribute("userpw", password); // 처리후 호출 함수
                        model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));    // 처리후 호출 함수
                        return "/cmmn/commonMsg";
                    }

                    // id and password match ok
                    session = request.getSession();
                    SessionManager.setMaxInactiveInterval(3600);
                    sessionManager.setSession(session);
                    sessionManager.setLoginManagerConfig(session, sysUserMember.getUSER_NO());
                    sessionManager.setUser(sysUserMember.getUSER_ID());
                    sessionManager.setAttribute("start_date", formatter.format(currentTime));
                    doSpringSecurityAutoLogin(sysUserMember.getUSER_ID(), sysUserMember.getSECRET_NO(), request);

                    //사용자 접속 로그 등록
                    UserConnectVO userCon = new UserConnectVO();
                    userCon.setUSER_NO(sysUserMember.getUSER_NO());
                    userCon.setSESION_ID(request.getSession().getId());

                    //LOGGER.info("userNo:" + userCon.getUSER_NO());
                    //LOGGER.info("userNo:" + userCon.getSESION_ID());

                    //String conNo = userConnectService.insertUserConnect(userCon);

                    model.addAttribute("resultCode", "CALLBACK");
                    model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));    // 처리후 호출 함수
                    return "/cmmn/commonMsg";
                } else {
                    sessionManager.setUserInfo(null);
                }
            } else {
                sessionManager.setUserInfo(null);
            }
        }

        model.addAttribute("resultCode", "LOGIN_FAIL");
        model.addAttribute("resultMsg", "일치하는 사용자 정보가 없습니다.");
        model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));    // 처리후 호출 함수
        return "/cmmn/commonMsg";
    }

	@RequestMapping(value = "/logout.do")
	public String logout(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		return null;
	}

	@RequestMapping(value = "/logoutok.do")
	public String logoutok(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
	     return "redirect:/main.do";
	}

	@RequestMapping(value = "/pothole-logout.do")
    public String potholelogout(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
        return null;
    }

    @RequestMapping(value = "/pothole-logoutok.do")
    public String potholelogoutok(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
        sessionManager.invalidateSession(SessionManager.getUserNo());
        return "redirect:/pothole-main.do";
    }

	private void doSpringSecurityAutoLogin(String username, String password, HttpServletRequest request) {

		try {
			// Must be called from request filtered by Spring Security,
			// otherwise SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication = customAuthenticationProvider.authenticate(token);
			LOGGER.debug("Logging in with [{}]", authentication.getPrincipal());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			LOGGER.error("Failure in autoLogin", e);
		}

	}

	@RequestMapping(value = "/accessDenied.do")
	public String accessDenied(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		model.addAttribute("resultCode", "MSG");
		model.addAttribute("resultMsg", "접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
		model.addAttribute("callBackFunction", nvl(memberInfoVO.getCallBackFunction()));	// 처리후 호출 함수
		return "/cmmn/commonMsg";
	}

	/**
	 * 중복된 사용자 이전 세션을 종료하고 새로운 세션 생성
	 * @param memberInfoVO
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setDupleUserPorc.do")
	public String setDupleUserPorc(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {

		SysUserVO sysUserMember = sysUserService.selectSysUserByID(memberInfoVO);

		if(sysUserMember != null){

			UserConnectVO userCon = new UserConnectVO();
			userCon.setUSER_NO(sysUserMember.getUSER_NO());
			userCon.setSESION_ID(sessionManager.getSessionId(sysUserMember.getUSER_NO()));
			userConnectService.updateUserConnect(userCon);

			sessionManager.invalidateSession(sysUserMember.getUSER_NO());
		}

		if(sysUserMember.getREQ_MENUACC_ROLE().equals("ROLE_USER_POTHOLE")){
			return goodogin(memberInfoVO, model, request, session);
		}
		else{
			return login(memberInfoVO, model, request, session);
		}
	}
}
