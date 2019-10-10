package kr.go.gg.gpms.base.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.notice.service.NoticeService;
import kr.go.gg.gpms.notice.service.model.NoticeVO;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;
//import org.springframework.security.core.context.SecurityContextHolder;



@Controller("mainController")
public class MainController extends BaseController{

	public static final String IS_MOBILE = "MOBILE";
	private static final String IS_PHONE = "PHONE";
	public static final String IS_TABLET = "TABLET";
	public static final String IS_PC = "PC";

	
	@Resource(name = "sysUserService")
	private SysUserService sysUserService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main.do")
	public String login(@ModelAttribute NoticeVO noticeVO, MemberInfo memberInfoVO,  ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		// SayHello sayHello = new SayHello();
		
		return "/main";
	}
	
	/**
	 * 지능형 사고예방 접속 페이지
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/iasp.do")
	public String login2(@ModelAttribute NoticeVO noticeVO, MemberInfo memberInfoVO,  ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		 String userAgent = request.getHeader("User-Agent").toUpperCase();
			
		    if(userAgent.indexOf(IS_MOBILE) > -1) {
		        if(userAgent.indexOf(IS_PHONE) == -1)
			    return "/m_main_iasp";
			else
			    return "/m_main_iasp";
		    } else
		return "/main_iasp";
	}
	
	
	@RequestMapping(value = "/pothole-main.do")
    public String potholeLogin(@ModelAttribute NoticeVO noticeVO, MemberInfo memberInfoVO,  ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
        // SayHello sayHello = new SayHello();
        
        return "/pothole-main";
    }
	
	@RequestMapping(value = "/change-main.do")
    public String changeLogin(@ModelAttribute NoticeVO noticeVO, MemberInfo memberInfoVO,  ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
        // SayHello sayHello = new SayHello();
        
        return "/change-main";
    }
	
	@RequestMapping(value = "/sessionCheck.do")
	public String sessionCheck(@ModelAttribute MemberInfo memberInfoVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		boolean result = false;
		
		memberInfoVO = sessionManager.getCurrentUser();
		if(memberInfoVO != null && memberInfoVO.getUSER_ID() != null){
			result = true;
		}
		
		model.addAttribute("result", result);
		
		return "jsonView";
	}
	
	
}
