package egovframework.security.service.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
import kr.go.gg.gpms.userconnect.service.UserConnectService;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import egovframework.cmmn.web.SessionManager;
/**
 * @Class Name : CustomLogoutHandler.java
 * @Description : 로그아웃 핸들러
 * @Modification Information
 * 
 * @author skc@muhanit.kr
 * @since 2017-10-23
 * @version 1.0
 * @see
 *  
 * Copyright (C) All right reserved.
 */
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {

	@Resource(name = "userConnectService")
	private UserConnectService userConnectService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogoutHandler.class);
	
	@Autowired
	SessionManager sessionManager;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {

		try{
	       //사용자 접속 종료 로그 등록
			MemberInfo memberInfoVO = sessionManager.getCurrentUser(request.getSession());
			
			if( memberInfoVO != null){
				UserConnectVO userCon = new UserConnectVO();
				userCon.setUSER_NO(memberInfoVO.getUSER_NO());
				userCon.setSESION_ID(request.getSession().getId());
				userConnectService.updateUserConnect(userCon);
			}
			
			request.getSession().invalidate();
			
		}catch(Exception e){}
		
		super.onLogoutSuccess(request, response, authentication);
		
	}
}
