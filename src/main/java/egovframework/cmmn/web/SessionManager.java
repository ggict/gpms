package egovframework.cmmn.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;
import kr.go.gg.gpms.userconnect.service.UserConnectService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component("sessionManager")
public class SessionManager{

	@Resource(name = "sysUserService")
	SysUserService sysUserService;

	@Resource(name = "userConnectService")
	UserConnectService userConnectService;

	LoginManager loginManager = LoginManager.getInstance();

	@Autowired
	@Qualifier("sessionRegistry")
	SessionRegistry sessionRegistry;

	/**
	 * 세션 등록(loginManager listener 연결)
	 * @param session
	 */
	public void setSession(HttpSession session){
		session.setAttribute("session.id", loginManager);
	}

	/**
	 * 사용자 중복 체크
	 * @param userNo
	 * @return
	 */
	public boolean checkDuplicate(String userNo){
		return loginManager.isUsing(userNo);
	}

	/**
	 * 사용자 세션 목록 추가
	 * @param session
	 * @param userNo
	 */
	public void setLoginManagerConfig(HttpSession session, String userNo){
		loginManager.setSession(session, userNo);
	}

	/**
	 * 사용자 세션 제거
	 * @param userNo
	 */
	public void invalidateSession(String userNo){
		loginManager.removeSessionByUserId(userNo);
	}

	/**
	 * 사용자 세션 ID 조회
	 * @param userNo
	 * @return
	 */
	public String getSessionId(String userNo){
		return loginManager.getSessionId(userNo);
	}

	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}


	public static MemberInfo getCurrentUser() throws Exception {
		HttpSession session = getSession();
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("userinfo");
		return memberinfo;
	}

	public static MemberInfo getCurrentUser(HttpSession session) throws Exception {
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("userinfo");
		return memberinfo;
	}

	public MemberInfo getUser() throws Exception {
		return getCurrentUser();
	}

	public void setAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	public void setUser(String userid) throws Exception {
		MemberInfo memberinfo;
		memberinfo = getCurrentUser(userid);
		setUserInfo(memberinfo);
	}

	public void setUserInfo(MemberInfo memberinfo) {
		getSession().setAttribute("userinfo", memberinfo);
	}

	private MemberInfo getCurrentUser(String userid) throws Exception {

		MemberInfo memberinfo = new MemberInfo();
		SysUserVO sysUserVO = null;
		memberinfo.setUSER_ID(userid);
		sysUserVO = sysUserService.selectSysUserByID(memberinfo);
		BeanUtils.copyProperties(sysUserVO, memberinfo);

		return memberinfo;
	}

	public static String getUserId() throws Exception {
		String userid = "";
		MemberInfo memberInfo = getCurrentUser();
		if (memberInfo != null) {
			userid = memberInfo.getUSER_ID();
		}
		return userid;
	}

	public static void setMaxInactiveInterval(int interval) {
		HttpSession session = getSession();
		session.setMaxInactiveInterval(interval);
	}

	public static String getUserNo() throws Exception {
		String userno = "";
		MemberInfo memberInfo = getCurrentUser();
		if (memberInfo != null) {
			userno = memberInfo.getUSER_NO();
		}
		return userno;
	}

	public static void setSystem(String sysNm) throws Exception {
		HttpSession session = getSession();
		session.setAttribute("system", sysNm);
	}
}
