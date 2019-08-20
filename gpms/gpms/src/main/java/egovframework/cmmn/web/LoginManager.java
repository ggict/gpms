package egovframework.cmmn.web;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

/**
 * @Class Name : LoginManager.java
 * @Description : 사용자 중복 체크 및 세션 관리
 * @Modification Information
 * 
 * @author skc@muhanit.kr
 * @since 2017-10-23
 * @version 1.0
 * @see
 *  
 * Copyright (C) All right reserved.
 */

public class LoginManager implements HttpSessionBindingListener {
	//loginManager instance
	public static LoginManager loginManager = null;
	//접속중인 session list
	public static Hashtable loginUsers = new Hashtable();

	public LoginManager() {
		super();
	}

	/**
	 * loginManager instance 생성 및 조회
	 * @return loginManager
	 */
	public static synchronized LoginManager getInstance() {
		if (loginManager == null) {
			loginManager = new LoginManager();
		}
		return loginManager;
	}

	/**
	 * 해당 ID를 가진 사용자 로그인 여부 체크
	 * @param sessionID
	 * @return isLogin
	 */
	public boolean isLogin(String sessionID) {
		boolean isLogin = false;
		Enumeration e = loginUsers.keys();
		String key = "";
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (sessionID.equals(key)) {
				isLogin = true;
			}
		}
		return isLogin;
	}

	/**
	 * 중복 로그인 막기 위해 아이디 사용중인지 체크
	 * @param userID
	 * @return isUsing
	 */
	public boolean isUsing(String userID) {
		boolean isUsing = false;
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (userID.equals(loginUsers.get(session))) {
				isUsing = true;
			}
		}
		return isUsing;
	}
	
	/**
	 * 동일 id를 가진 사용자 세션 제거
	 * @param userID
	 */
	public void removeSessionByUserId(String userID) {
		
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (userID.equals(loginUsers.get(session))) {
				session.invalidate();
			}
		}
		
	}

	/**
	 * 로그인 시 세션 리스트에 등록
	 * @param session
	 * @param userID
	 */
	public void setSession(HttpSession session, String userID) {
		loginUsers.put(session, userID);
	}
	
	/**
	 * 사용자
	 * @param userID
	 * @return
	 */
	public String getSessionId(String userID){
		String sessionId = null;
		
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (userID.equals(loginUsers.get(session))) {
				sessionId = session.getId();
				return sessionId;
			}
		}
		
		return sessionId;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
	}

	/**
	 * 세션이 끊겼을때 세션 리스트에 해당 세션 삭제
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUsers.remove(event.getSession());
	}

}
