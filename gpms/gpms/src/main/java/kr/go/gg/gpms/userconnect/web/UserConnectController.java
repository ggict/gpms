


package kr.go.gg.gpms.userconnect.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.userconnect.service.UserConnectService;
import kr.go.gg.gpms.userconnect.service.model.UserConnectVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.gg.gpms.base.web.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Class Name : UserConnectController.java
 * @Description : UserConnect Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-10-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("userConnectController")
public class UserConnectController extends BaseController {

	@Resource(name = "userConnectService")
	private UserConnectService userConnectService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserConnectController.class);

	/**
	 * 사용자접속로그(TL_USER_CONNECT) 목록을 조회한다. (pageing)
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return "/userconnect/UserConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/userconnect/selectUserConnectList.do" })
	public String selectUserConnectList(@ModelAttribute("searchVO") UserConnectVO userConnectVO, ModelMap model) throws Exception {
		return "/userconnect/UserConnectList" ;
	}
	
	/**
	 * 사용자접속로그(TL_USER_CONNECT) 목록을 조회한다. (pageing)
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return "/userconnect/UserConnectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/userconnect/selectUserConnectList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectUserConnectListRest(@RequestBody UserConnectVO userConnectVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userConnectVO.getPage());
		paginationInfo.setRecordCountPerPage(userConnectVO.getPageUnit());
		paginationInfo.setPageSize(userConnectVO.getRows());
		userConnectVO.setUsePage(true);
		
		userConnectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userConnectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userConnectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<UserConnectVO> items = userConnectService.selectUserConnectList(userConnectVO);
		int total_count = userConnectService.selectUserConnectListTotalCount(userConnectVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) userConnectVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", userConnectVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 사용자접속로그(TL_USER_CONNECT) 상세를 조회한다.
	 * @param userConnectVO - 조회할 정보가 담긴 UserConnectVO
	 * @return "/userconnect/UserConnectView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/userconnect/selectUserConnect.do" })
	public String selectUserConnect(@ModelAttribute("searchVO") UserConnectVO userConnectVO, ModelMap model) throws Exception {
	
		model.addAttribute("userConnectVO", userConnectService.selectUserConnect(userConnectVO));
		return "/userconnect/UserConnectView";
	}


	@RequestMapping(value = { "/userconnect/addUserConnectView.do" })
	public String addUserConnectView(@ModelAttribute("searchVO") UserConnectVO userConnectVO, Model model) throws Exception {
		model.addAttribute("userConnectVO", new UserConnectVO());
		return "/userconnect/UserConnectRegist";
	}

	@RequestMapping(value = { "/api/userconnect/addUserConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserConnectVO addUserConnect(@RequestBody UserConnectVO userConnectVO) throws Exception {
		BindBeansToActiveUser(userConnectVO);
		userConnectService.insertUserConnect(userConnectVO);
		userConnectVO.setResultSuccess("true");
		userConnectVO.setResultMSG("정상 등록되었습니다.");
		return userConnectVO;
	}
	
	@RequestMapping(value = { "/userconnect/updateUserConnectView.do" })
	public String updateUserConnectView(@ModelAttribute("searchVO") UserConnectVO userConnectVO, Model model) throws Exception {
		model.addAttribute("userConnectVO", userConnectService.selectUserConnect(userConnectVO));
		return "/userconnect/UserConnectUpdate";
	}

	@RequestMapping(value = { "/api/userconnect/updateUserConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserConnectVO updateUserConnect(@RequestBody UserConnectVO userConnectVO) throws Exception {
		BindBeansToActiveUser(userConnectVO);
		userConnectService.updateUserConnect(userConnectVO);
		userConnectVO.setResultSuccess("true");
		userConnectVO.setResultMSG("정상 수정되었습니다.");
		return userConnectVO;
	}

	@RequestMapping(value = { "/api/userconnect/deleteUserConnect.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserConnectVO deleteUserConnect(@RequestBody UserConnectVO userConnectVO) throws Exception {
		BindBeansToActiveUser(userConnectVO);
		userConnectService.deleteUserConnect(userConnectVO);
		userConnectVO.setResultSuccess("true");
		userConnectVO.setResultMSG("정상 삭제되었습니다.");
		return userConnectVO;
	}

}
