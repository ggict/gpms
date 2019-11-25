package egovframework.security.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import egovframework.cmmn.web.SessionManager;
import egovframework.security.service.SecuredObjectService;
import kr.go.gg.gpms.menu.service.MenuService;
import kr.go.gg.gpms.menu.service.model.MenuVO;
import kr.go.gg.gpms.sysconnect.service.SysConnectService;
import kr.go.gg.gpms.sysconnect.service.model.SysConnectVO;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

public class ReloadableFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	private SecuredObjectService securedObjectService;

	@Autowired
	SessionManager sessionManager;
	@Resource(name = "sysConnectService")
	private SysConnectService sysConnectService;
	@Resource(name = "menuService")
	private MenuService menuService;

	public ReloadableFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		this.requestMap = requestMap;

	}

	public void setSecuredObjectService(SecuredObjectService securedObjectService) {
		this.securedObjectService = securedObjectService;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		Collection<ConfigAttribute> result = null;
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				result = entry.getValue();
				break;
			}
		}

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.debug("unAuthorization Access url =" + request.getServletPath());
		} else {
			if (result != null) {
				try {
					String url = request.getServletPath();

					MemberInfo currentUser = sessionManager.getCurrentUser(request.getSession());
					// Authentication authentication =
					// SecurityContextHolder.getContext().getAuthentication();
					if (currentUser != null) {
						String sessionId = request.getSession().getId();
						SysConnectVO sysConnectVO = new SysConnectVO();
						sysConnectVO.setUSER_NO(currentUser.getUSER_NO());
						sysConnectVO.setSESION_ID(sessionId);
						MenuVO menuVO = new MenuVO();
						menuVO.setURL(url);
						MenuVO menuOne = menuService.selectMenuByURL(menuVO);
						if (menuOne != null) {
							sysConnectVO.setMENU_ID(menuOne.getMENU_ID());
							sysConnectService.insertSysConnect(sysConnectVO);
							logger.info("write connect log=" + sysConnectVO.getCONECT_LOG_NO());
						}
					}

					// sysConnectService
				} catch (Exception e) {
					logger.info("write connect log error=" + e.toString());
					throw new IllegalArgumentException(e);
				}

			}
		}
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void reload() throws Exception {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedMap = securedObjectService.getRolesAndUrl();

		Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadedMap.entrySet().iterator();

		// 이전 데이터 삭제
		requestMap.clear();

		while (iterator.hasNext()) {
			Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();

			requestMap.put(entry.getKey(), entry.getValue());
		}

		if (logger.isInfoEnabled()) {
			logger.info("Secured Url Resources - Role Mappings reloaded at Runtime!");
		}
	}

	public boolean checkRole(String url) throws Exception {
		boolean result = true;
		Iterator<Entry<RequestMatcher, Collection<ConfigAttribute>>> iterator = requestMap.entrySet().iterator();
		MemberInfo currentUser = sessionManager.getCurrentUser();

		FilterInvocation object = new FilterInvocation(null, url, "GET");
		HttpServletRequest request = object.getRequest();

		if(currentUser == null || currentUser.getAuthorities() == null) { return result; }

		while (iterator.hasNext()) {
			Entry<RequestMatcher, Collection<ConfigAttribute>> entry = iterator.next();

			if(entry.getKey().matches(request)){
				boolean matchAuth = false;
				for(GrantedAuthority auth : currentUser.getAuthorities()){
					SecurityConfig config = new SecurityConfig(auth.toString());
					if(entry.getValue().contains(config)){
						matchAuth = true;
					}
				}

				if(!matchAuth){
					result = false;
					return result;
				}
			}
		}

		return result;
	}

}
