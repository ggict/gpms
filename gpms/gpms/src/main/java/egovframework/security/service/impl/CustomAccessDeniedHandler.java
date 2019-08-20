package egovframework.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private String accessDeniedUrl;

	public CustomAccessDeniedHandler() {
	}

	public CustomAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String allowedUrl[] = { "/gpms/accessDenied.do", "accessDenied.do" };
		
		try {
			accessDeniedUrl = allowedUrl[Integer.parseInt(accessDeniedUrl)];
		} catch(NumberFormatException e) {
			request.getSession().setAttribute("message", " 잘못된 접근입니다.");
		} catch(ArrayIndexOutOfBoundsException e) {
			request.getSession().setAttribute("message", " 잘못된 입력입니다.");
		}
		
		response.sendRedirect(accessDeniedUrl);
		request.getSession().setAttribute("message", " Sorry You don't have privileges to view this page!!!");

	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}
