package kr.go.gg.gpms.notice.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.notice.service.NoticeService;
import kr.go.gg.gpms.notice.service.model.NoticeVO;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.property.EgovPropertyService;
//import org.springframework.security.core.context.SecurityContextHolder;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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

@Controller("noticeController")
public class NoticeController extends BaseController {

	@Resource(name = "noticeService")
	protected NoticeService noticeService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	@Autowired
	SessionManager sessionManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

	/**
	 * 공지사항(TN_NOTICE) 목록을 조회한다. (pageing)
	 * 
	 * @param noticeVO
	 *            - 조회할 정보가 담긴 NoticeVO
	 * @return "/mng/notice/selectNoticeList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/notice/selectNoticeList.do" })
	public String selectNoticeList(NoticeVO noticeVO, ModelMap model) throws Exception {

		return "/mng/notice/noticeList";
	}

	/**
	 * 공지사항(TN_NOTICE) 목록을 조회한다. (pageing)
	 * 
	 * @param noticeVO
	 *            - 조회할 정보가 담긴 NoticeVO
	 * @return "/mng/notice/selectNoticeListPage"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/notice/selectNoticeListPage.do" }, method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectNoticeListPage(@RequestBody NoticeVO noticeVO, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(noticeVO.getPage());
		paginationInfo.setRecordCountPerPage(noticeVO.getPageUnit());
		paginationInfo.setPageSize(noticeVO.getRows());
		
		noticeVO.setUsePage(true);

		noticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		noticeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		noticeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<NoticeVO> items = noticeService.selectNoticeList(noticeVO);
		int totCnt = noticeService.selectNoticeListTotalCount(noticeVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", noticeVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);
		
		return map;
	}
	
	/**
	 * 공지사항(TN_NOTICE) 등록한다.
	 * 
	 * @param noticeVO
	 *            - 조회할 정보가 담긴 NoticeVO
	 * @return "/notice/addNotice"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/notice/noticeRegist.do" })
	public String noticeRegist(@ModelAttribute("searchVO") NoticeVO noticeVO, ModelMap model, HttpSession session) throws Exception {
		
		MemberInfo memberinfo = (MemberInfo) session.getAttribute("userinfo");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		noticeVO.setWRTER(memberinfo.getUSER_NM());
		noticeVO.setREGIST_DT(dateFormat.format(calendar.getTime()));
		
		model.addAttribute("noticeVO", noticeVO);
		
		return "/mng/notice/noticeRegist";
	}
	
	/**
	 * 공지사항(TN_NOTICE) 등록한다.
	 * 
	 * @param noticeVO
	 *            - 조회할 정보가 담긴 NoticeVO
	 * @return "/notice/addNotice"
	 * @exception Exception
	 */
	/*@RequestMapping(value = {  "/api/notice/addNotice.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody NoticeVO addNotice(@RequestBody NoticeVO noticeVO, BindingResult bindingResult, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		
		//noticeVO.setPOS(getFileNo(request, "file_notice", "notice"));
		
		BindBeansToActiveUser(noticeVO);
		noticeService.insertNotice(noticeVO);
		noticeVO.setResultSuccess("true");
		noticeVO.setResultMSG("정상 등록되었습니다.");
		return noticeVO;
	}*/
	@RequestMapping(value = {  "/api/notice/addNotice.do" }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String addNotice(@ModelAttribute("searchVO") NoticeVO noticeVO, BindingResult bindingResult, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		String resultCode = "";
		String resultMsg = "";
		String funCallback = noticeVO.getCallBackFunction() == null ? ""
				: noticeVO.getCallBackFunction();
		
		noticeVO.setPOS(getFileNo(request, "file_notice", "notice"));
		
		BindBeansToActiveUser(noticeVO);
		noticeService.insertNotice(noticeVO);
		resultCode = "SAVE_SUCCESS";
		resultMsg = "정상 등록되었습니다.";
		
		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수
		return "/cmmn/commonMsg";
	}
	
	/**
	 * 공지사항(TN_NOTICE)을  상세 조회한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return "/api/notice/selectNoticeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/notice/selectNoticeUpdate.do"  }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String selectNoticeUpdate(@ModelAttribute("searchVO") NoticeVO noticeVO, ModelMap model) throws Exception {
		//세부 공지사항 정보
		noticeVO = noticeService.selectNoticeView(noticeVO);
		
		model.addAttribute("noticeVO", noticeVO);
		
		return "/mng/notice/noticeUpdate";
	}
	
	/**
	 * 공지사항(TN_NOTICE)을  상세 조회한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return "/api/notice/selectNoticeView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/notice/selectNoticeView.do"  }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String selectNoticeView(@ModelAttribute("searchVO") NoticeVO noticeVO, ModelMap model) throws Exception {
		//세부 공지사항 정보
		noticeVO = noticeService.selectNoticeView(noticeVO);
		
		model.addAttribute("noticeVO", noticeVO);
		
		return "/mng/notice/noticeView";
	}
	
	/**
	 * 공지사항(TN_NOTICE)을  삭제한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return "/api/notice/deleteNotice"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/notice/deleteNotice.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody NoticeVO deleteNotice(@RequestBody NoticeVO noticeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(noticeVO);
		noticeService.deleteNotice(noticeVO);
		noticeVO.setResultSuccess("true");
		noticeVO.setResultMSG("정상 삭제되었습니다.");
		return noticeVO;
	}
	
	/**
	 * 공지사항(TN_NOTICE)을 수정한다.
	 * @param noticeVO - 수정할 정보가 담긴 noticeVO
	 * @return int형
	 * @exception Exception
	 */
	/*@RequestMapping(value = {  "/api/notice/updateNotice.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody NoticeVO updateNotice(@RequestBody NoticeVO noticeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(noticeVO);
		noticeService.updateNotice(noticeVO);
		noticeVO.setResultSuccess("true");
		noticeVO.setResultMSG("정상 수정되었습니다.");
		return noticeVO;
	}*/
	
	@RequestMapping(value = { "/api/notice/updateNotice.do" }, method = {RequestMethod.GET, RequestMethod.POST} )
	public String updateNotice(NoticeVO noticeVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		String resultCode = "";
		String resultMsg = "";
		String funCallback = noticeVO.getCallBackFunction() == null ? ""
				: noticeVO.getCallBackFunction();
		
		String fileNo = getFileNo(request, "file_notice", "notice");
		if(fileNo != null){
			noticeVO.setPOS(fileNo);
		}
		
		BindBeansToActiveUser(noticeVO);
		noticeService.updateNotice(noticeVO);
		resultCode = "UPDATE_SUCCESS";
		resultMsg = "정상 수정되었습니다.";
		
		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("callBackFunction", funCallback); // 처리후 호출 함수
		return "/cmmn/commonMsg";
	}
	
	/**
	 * 공지사항(TN_NOTICE) 조회수를 수정한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return "/api/notice/noticeRdcntIncrese"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/notice/updateNoticeRdcnt.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody NoticeVO updateNoticeRdcnt(@RequestBody NoticeVO noticeVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(noticeVO);
		noticeService.updateNoticeRdcnt(noticeVO);
		noticeVO.setResultSuccess("true");
		noticeVO.setResultMSG("정상 수정되었습니다.");
		return noticeVO;
	}
	
	/**
	 * 메인 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 NoticeVO
	 * @exception Exception
	 */
	@RequestMapping(value = {   "/api/notice/selectMainNoticeList.do" })
    public @ResponseBody Map<String, Object> selectMainNoticeList(@RequestBody NoticeVO noticeVO, HttpServletRequest request, ModelMap model, HttpSession session)  throws Exception {	

		noticeVO.setUsePage(true);

		Map<String, Object> map = new HashMap<String, Object>();
		List<NoticeVO> ntlist = noticeService.selectMainNoticeList(noticeVO);
		int total_count = noticeService.selectMainNoticeListTotalCount(noticeVO);
		
		map.put("data", ntlist);
		map.put("totCnt", total_count);
		map.put("cntpage", noticeVO.getRecordCountPerPage());
	
		return map;
	}
	
}
