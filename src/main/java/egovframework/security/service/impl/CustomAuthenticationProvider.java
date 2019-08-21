package egovframework.security.service.impl;

import java.util.ArrayList;
import java.util.List;



import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;
import kr.go.gg.gpms.userauth.service.UserAuthService;
import kr.go.gg.gpms.userauth.service.model.UserAuthVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import egovframework.cmmn.web.SessionManager;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	// @Resource(name = "mainService")
	// private MainService mainService;

	// @Autowired
	@Autowired
	UserAuthService userAuthService;

	@Autowired
	SessionManager sessionManager;

	// public void setMainService(MainService mainService) {
	// this.mainService = mainService;
	//
	// }

	/**
	 * 현재 로그인한 사용자를 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	protected MemberInfo getCurrentUser() throws Exception {
		return sessionManager.getCurrentUser();
	}

	  
	public Authentication authenticate() throws AuthenticationException {
		MemberInfo memberinfo = new MemberInfo();

		try {
			memberinfo = getCurrentUser();
			if (memberinfo != null && StringUtils.isNotEmpty(memberinfo.getUSER_ID())) {
				// SESSION HADLE
				
				 
				String user_id = memberinfo.getUSER_ID();
				String user_pw = "";

				List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
				UserAuthVO userAuthVO = new UserAuthVO();
				userAuthVO.setUSER_NO(memberinfo.getUSER_NO());
				userAuthVO.setSidx("USER_NM");
				userAuthVO.setSord("asc");
				List<UserAuthVO> userAuthList = userAuthService.selectUserAuthList(userAuthVO);
				for (UserAuthVO userAuth : userAuthList) {
					roles.add(new SimpleGrantedAuthority(userAuth.getAUTHOR_ID()));
				}
				memberinfo.setAuthorities(roles);
				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
				return result;
			} else {
				logger.info("사용자 크리덴셜 정보가 틀립니다. 에러가 발생합니다.");
				throw new BadCredentialsException("Bad credentials");
			}
		} catch (Exception e) {
			logger.info("사용자 크리덴셜 정보가 틀립니다. 에러가 발생합니다.");
			throw new BadCredentialsException("Bad credentials");
		}

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MemberInfo memberinfo = new MemberInfo();

		try {
			memberinfo = getCurrentUser();
			if (memberinfo != null && StringUtils.isNotEmpty(memberinfo.getUSER_ID())) {
				String user_id = memberinfo.getUSER_ID();
				String user_pw = "";

				List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
				UserAuthVO userAuthVO = new UserAuthVO();
				userAuthVO.setUSER_NO(memberinfo.getUSER_NO());
				userAuthVO.setSidx("USER_NM");
				userAuthVO.setSord("asc");
				userAuthVO.setUsePage(false);
				List<UserAuthVO> userAuthList = userAuthService.selectUserAuthList(userAuthVO);
				for (UserAuthVO userAuth : userAuthList) {
					roles.add(new SimpleGrantedAuthority(userAuth.getAUTHOR_ID()));
				}
				memberinfo.setAuthorities(roles);
				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
				return result;
			} else {
				logger.info("사용자 크리덴셜 정보가 틀립니다. 에러가 발생합니다.");
				throw new BadCredentialsException("Bad credentials");
			}
		} catch (Exception e) {
			logger.info("사용자 크리덴셜 정보가 틀립니다. 에러가 발생합니다.");
			throw new BadCredentialsException("Bad credentials");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}