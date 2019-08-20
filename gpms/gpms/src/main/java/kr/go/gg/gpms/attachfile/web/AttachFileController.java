

package kr.go.gg.gpms.attachfile.web;




import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.cmmn.util.SaveMapUtils;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : AttachFileController.java
 * @Description : AttachFile Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("attachFileController")
public class AttachFileController extends BaseController{

	@Resource(name = "attachFileService")
	private AttachFileService attachFileService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "pathInfoProperties")
	protected Properties pathInfoProperties;

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachFileController.class);

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다. (pageing)
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/attachfile/selectAttachFileList.do" })
	public String selectAttachFileList(AttachFileVO attachFileVO, ModelMap model) throws Exception {
		attachFileVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		attachFileVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(attachFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(attachFileVO.getPageUnit());
		paginationInfo.setPageSize(attachFileVO.getPageSize());

		attachFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		attachFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		attachFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AttachFileVO> items = attachFileService.selectAttachFileList(attachFileVO);
		model.addAttribute("items", items);

		int totCnt = attachFileService.selectAttachFileListTotalCount(attachFileVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/attachfile/AttachFileList" ;
	}


	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다. (pageing)
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/attachfile/selectAttachFileList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<AttachFileVO> selectAttachFileListRest(@RequestBody AttachFileVO attachFileVO, ModelMap model, HttpSession session) throws Exception {
		attachFileVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		attachFileVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(attachFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(attachFileVO.getPageUnit());
		paginationInfo.setPageSize(attachFileVO.getPageSize());

		attachFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		attachFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		attachFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AttachFileVO> items = attachFileService.selectAttachFileList(attachFileVO);
		return items;
	}


	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다. (pageing)
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/attachfile/selectAttachFileListPage.do" })
	public String selectAttachFileListPage(AttachFileVO attachFileVO, ModelMap model) throws Exception {
		attachFileVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		attachFileVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(attachFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(attachFileVO.getPageUnit());
		paginationInfo.setPageSize(attachFileVO.getPageSize());

		attachFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		attachFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		attachFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AttachFileVO> items = attachFileService.selectAttachFileList(attachFileVO);
		model.addAttribute("items", items);

		int totCnt = attachFileService.selectAttachFileListTotalCount(attachFileVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/manage/attachfile/AttachFileList" ;
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다. (pageing)
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/attachfile/selectAttachFileListPage.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<AttachFileVO>  selectAttachFileListPageRest(@RequestBody  AttachFileVO attachFileVO, ModelMap model, HttpSession session) throws Exception {
		attachFileVO.setPageUnit(egovPropertyService.getInt("pageUnit"));
		attachFileVO.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(attachFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(attachFileVO.getPageUnit());
		paginationInfo.setPageSize(attachFileVO.getPageSize());

		attachFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		attachFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		attachFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<AttachFileVO> items = attachFileService.selectAttachFileList(attachFileVO);
		return items;
	}



	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 상세를 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/manage/attachfile/selectAttachFile.do"  })
	public String selectAttachFile(@ModelAttribute("searchVO") AttachFileVO attachFileVO, ModelMap model) throws Exception {

		model.addAttribute("attachFileVO", attachFileService.selectAttachFile(attachFileVO));
		return "/manage/attachfile/AttachFileView";
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 상세를 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return "/manage/attachfile/AttachFileView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/attachfile/selectAttachFile.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AttachFileVO selectAttachFileRest(@RequestBody  AttachFileVO attachFileVO, ModelMap model, HttpSession session) throws Exception {
		AttachFileVO attachFileVOOne = attachFileService.selectAttachFile(attachFileVO);
		return attachFileVOOne;
	}



	@RequestMapping(value = { "/manage/attachfile/addAttachFileView.do" })
	public String addAttachFileView(@ModelAttribute("searchVO") AttachFileVO attachFileVO, Model model) throws Exception {
		model.addAttribute("attachFileVO", new AttachFileVO());
		return "/manage/attachfile/AttachFileRegist";
	}

	@RequestMapping(value = { "/manage/attachfile/addAttachFile.do"  })
	public String addAttachFile(AttachFileVO attachFileVO) throws Exception {
		attachFileService.insertAttachFile(attachFileVO);
		return "redirect:/manage/attachfile/selectAttachFileList.do";
	}

	@RequestMapping(value = {  "/api/attachfile/addAttachFile.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AttachFileVO addAttachFileRest(@RequestBody AttachFileVO attachFileVO, HttpSession session) throws Exception {
		attachFileService.insertAttachFile(attachFileVO);
		return attachFileVO;
	}


	@RequestMapping(value = { "/manage/attachfile/updateAttachFileView.do" })
	public String updateAttachFileView(@ModelAttribute("searchVO") AttachFileVO attachFileVO, Model model) throws Exception {
		model.addAttribute("attachFileVO", attachFileService.selectAttachFile(attachFileVO));
		return "/manage/attachfile/AttachFileUpdate";
	}

	@RequestMapping(value = { "/manage/attachfile/updateAttachFile.do" })
	public String updateAttachFile(AttachFileVO attachFileVO) throws Exception {
		attachFileService.updateAttachFile(attachFileVO);
		return "redirect:/manage/attachfile/selectAttachFileList.do";
	}

	@RequestMapping(value = {  "/api/attachfile/updateAttachFile.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AttachFileVO updateAttachFileRest(@RequestBody AttachFileVO attachFileVO, HttpSession session) throws Exception {
		attachFileService.updateAttachFile(attachFileVO);
		return attachFileVO;
	}

	@RequestMapping(value = { "/manage/attachfile/deleteAttachFile.do" })
	public String deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
		attachFileService.deleteAttachFile(attachFileVO);
		return "redirect:/manage/attachfile/selectAttachFileList.do";
	}

	@RequestMapping(value = {   "/api/attachfile/deleteAttachFile.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AttachFileVO deleteAttachFileRest(@RequestBody AttachFileVO attachFileVO, HttpSession session) throws Exception {
		attachFileService.deleteAttachFile(attachFileVO);
		return attachFileVO;
	}


	/**
	 * 파일 다운로드 처리
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping(value = "/attachfile/downloadFile.do")
	public String downloadFile(@ModelAttribute AttachFileVO attachFileVO, HttpServletResponse response, Model model) throws Exception {
		BufferedInputStream in = null;
		String resultCode = "";
		String resultMsg = "";
		try {
			String fileNo = attachFileVO.getFILE_NO();
			String fileName = "";
			String fileOriName = "";
			String subPath = "";

			// 공통파일 키에 의해 DB에서 가져와야 하는 경우
			if( fileNo!=null && !fileNo.equals("") ) {
				attachFileVO = attachFileService.selectAttachFile(attachFileVO);

				if( attachFileVO !=null ) {
					fileName = attachFileVO.getFILE_NM();
					fileOriName = attachFileVO.getORGINL_FILE_NM();
					subPath = attachFileVO.getFILE_COURS();
				}
			}

			String filePath = pathInfoProperties.getProperty("file.upload.path") + checkFilePath(subPath, "path");

			File uFile = new File(filePath, checkFilePath(fileName, "name"));
			int fSize = (int) uFile.length();

			if (fSize > 0) {
				in = new BufferedInputStream(new FileInputStream(uFile));
				// String mimetype = servletContext.getMimeType(requestedFile);
				String mimetype = "text/html";

				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ URLEncoder.encode(fileOriName,"UTF-8") + "\"");
				response.setContentLength(fSize);

				FileCopyUtils.copy(in, response.getOutputStream());
				in.close();
				in = null;
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			else {
				/*//setContentType을 프로젝트 환경에 맞추어 변경
				response.setContentType("application/x-msdownload");
				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>파일이 손상되었습니다.</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();*/

				resultCode = "ERROR";
				resultMsg = "파일이 서버에 존재하지 않습니다.";
				model.addAttribute("resultCode", resultCode);
				model.addAttribute("resultMsg", resultMsg);

				return "/cmmn/commonMsg";
			}
		} catch(Exception e) {
			//setContentType을 프로젝트 환경에 맞추어 변경
			/*response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>파일을 찾을 수 없습니다.</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();*/

			resultCode = "ERROR";
			resultMsg = "파일이 서버에 존재하지 않습니다.";

			model.addAttribute("resultCode", resultCode);
			model.addAttribute("resultMsg", resultMsg);
			return "/cmmn/commonMsg";
		} finally {
			if (in != null) in.close();
		}

		return null;
	}

	//saveMapImage
	@RequestMapping(value = "/saveMapImage.do")
	public void saveMapImage( @ModelAttribute AttachFileVO attachFileVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		Map<String, String> req = requestToHashMap(request);

		String decodeStr =  URLDecoder.decode(req.get("data"), "UTF-8");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
		String fileName;

		if(req.get("imgNm") == null || req.get("imgNm").equals("")){
			 fileName = "Map_"+dateFormat.format(calendar.getTime());
		}else{
			 //사용자가 입력한 파일명으로 이미지 저장
			 fileName = req.get("imgNm");
		}

		SaveMapUtils saveMapUtil = new SaveMapUtils();

		saveMapUtil.setType("save");
		saveMapUtil.setRootPath(request.getSession().getServletContext().getRealPath("/"));
		BufferedImage image = saveMapUtil.createImages(decodeStr);

        response.reset();
        response.setContentType("Image/png");

        String header = request.getHeader("User-Agent");

        if (header.contains("MSIE") || header.contains("Trident")) {
	    	fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	    }else if (header.contains("Chrome")) {
	    	fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	    }

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".png");

	    OutputStream ios = response.getOutputStream();

    	try {
			ImageIO.write(image, "png", ios);
			response.flushBuffer();
			ios.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
	 * 첨부파일 등록 객체 생성
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
    @RequestMapping(value="/attachfile/getfileForm.do")
    public String fileForm(
    		@ModelAttribute AttachFileVO attachFileVO, Model model, HttpServletRequest request)
            throws Exception {
    	Map<String, String> req = requestToHashMap(request);

    	attachFileVO = attachFileService.selectAttachFile(attachFileVO);
    	String filePath = pathInfoProperties.getProperty("file.upload.webpath");

    	model.addAttribute("file_info", attachFileVO);
    	model.addAttribute("file_path", filePath);
    	model.addAttribute("file_column", req.get("FILE_COLUMN"));
    	model.addAttribute("file_type", req.get("FILE_TYPE"));
    	model.addAttribute("file_mode", req.get("FILE_MODE"));


    	/*// 첨부파일 루트
    	//model.addAttribute("file_path", "/files");
    	model.addAttribute("file_path", fileUploadProperties.getProperty("file.upload.webpath"));

    	// 파일 정보
    	model.addAttribute("file_info", searchVO);

    	// 이미지 인 경우, 이미지 크기 제한
    	model.addAttribute("limit_w", StringUtil.parseInt( request.getParameter("LIMIT_WIDTH"), 100) );
    	model.addAttribute("limit_h", StringUtil.parseInt( request.getParameter("LIMIT_HEIGHT"), 100) );*/

        return "/cmmn/fileForm";
    }

  	/**
	 * 지도 이미지 인쇄/출력
	 * @param
	 * @return "/printImgMap.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/printImgMap.do" })
	public String printImgMap(@ModelAttribute AttachFileVO attachFileVO,  ModelMap model
			, HttpSession session, HttpServletRequest request) throws Exception {

		Map<String, String> req = requestToHashMap(request);

		MemberInfo memberinfo = (MemberInfo) session.getAttribute("userinfo");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

		model.addAttribute("userNm",memberinfo.getUSER_NM());
		model.addAttribute("toDay",dateFormat.format(calendar.getTime()));
		model.addAttribute("mapType", req.get("mapType"));

		return "/map/mapPrint" ;
	}
    
    // Tomcat쪽 소스
    /*@RequestMapping(value = { "/printImgMap.do" })
    public String printImgMap(@RequestParam Map<String, Object> map,  ModelMap model
            , HttpServletRequest request) throws Exception {

        model.addAttribute("userNm", map.get("userNm"));
        model.addAttribute("mapType", map.get("mapType"));
        model.addAttribute("gData", map.get("gData"));
        model.addAttribute("center", map.get("center"));
        model.addAttribute("zoom", map.get("zoom"));
        model.addAttribute("extent", map.get("extent"));
        model.addAttribute("testobj", map.get("extent"));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        model.addAttribute("toDay",dateFormat.format(calendar.getTime()));

        return "/map/mapPrint" ;
    }*/

  	/**
	 * 지도 이미지 파일 저장
	 * @param
	 * @return "/saveImgMap.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/saveImgMap.do" })
	public String saveImgMap(@ModelAttribute AttachFileVO attachFileVO
			, HttpServletRequest request, ModelMap model) throws Exception {

		Map<String, String> req = requestToHashMap(request);

		model.addAttribute("mapType", req.get("mapType"));

		return "/map/saveImgMap";
	}

}
