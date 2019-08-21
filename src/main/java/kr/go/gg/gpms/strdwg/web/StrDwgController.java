


package kr.go.gg.gpms.strdwg.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;
import kr.go.gg.gpms.strdwg.service.StrDwgService;
import kr.go.gg.gpms.strdwg.service.model.StrDwgVO;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.cmmn.util.FileUploadUtils;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : StrDwgController.java
 * @Description : StrDwg Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("strDwgController")
public class StrDwgController extends BaseController {

	@Resource(name = "strDwgService")
	private StrDwgService strDwgService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StrDwgController.class);

	/**
	 * 구조물_도면_정보(STR_DWG) 목록을 조회한다. (pageing)
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return "/strdwg/StrDwgList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/strdwg/selectStrDwgList.do" })
	public String selectStrDwgList(@ModelAttribute("searchVO") StrDwgVO strDwgVO, ModelMap model) throws Exception {
		strDwgVO.setSidx("DWG_FILE");
		//구간 목록
		List<StrDwgVO> dwgSectList = strDwgService.selectStrDwgSectList(strDwgVO);
		
		model.addAttribute("dwgSectList", dwgSectList);
		model.addAttribute("strDwgVO", strDwgVO);
		return "/strdwg/strdwgList" ;
	}
	
	/**
	 * 구조물_도면_정보(STR_DWG) 목록을 조회한다. (pageing)
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return "/strdwg/StrDwgList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/strdwg/selectStrDwgList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectStrDwgListRest(@RequestBody StrDwgVO strDwgVO, ModelMap model) throws Exception {
		strDwgVO.setUsePage(false);
		
		List<StrDwgVO> items = strDwgService.selectStrDwgList(strDwgVO);
		int total_count = strDwgService.selectStrDwgListTotalCount(strDwgVO);
		
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 구조물_도면_정보(STR_DWG) 상세를 조회한다.
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return "/strdwg/StrDwgView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/strdwg/selectStrDwg.do" })
	public String selectStrDwg(@ModelAttribute("searchVO") StrDwgVO strDwgVO, ModelMap model) throws Exception {
	
		model.addAttribute("strDwgVO", strDwgService.selectStrDwg(strDwgVO));
		return "/strdwg/strdwgView";
	}


	@RequestMapping(value = { "/strdwg/addStrDwgView.do" })
	public String addStrDwgView(@ModelAttribute("searchVO") StrDwgVO strDwgVO, Model model) throws Exception {
		model.addAttribute("strDwgVO", new StrDwgVO());
		return "/strdwg/strdwgRegist";
	}

	@RequestMapping(value = { "/api/strdwg/addStrDwg.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody StrDwgVO addStrDwg(@RequestBody StrDwgVO strDwgVO) throws Exception {
		BindBeansToActiveUser(strDwgVO);
		strDwgService.insertStrDwg(strDwgVO);
		strDwgVO.setResultSuccess("true");
		strDwgVO.setResultMSG("정상 등록되었습니다.");
		return strDwgVO;
	}
	
	@RequestMapping(value = { "/strdwg/updateStrDwgView.do" })
	public String updateStrDwgView(@ModelAttribute("searchVO") StrDwgVO strDwgVO, Model model) throws Exception {
		model.addAttribute("strDwgVO", strDwgService.selectStrDwg(strDwgVO));
		return "/strdwg/strdwgUpdate";
	}

	@RequestMapping(value = { "/api/strdwg/updateStrDwg.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody StrDwgVO updateStrDwg(@RequestBody StrDwgVO strDwgVO) throws Exception {
		BindBeansToActiveUser(strDwgVO);
		strDwgService.updateStrDwg(strDwgVO);
		strDwgVO.setResultSuccess("true");
		strDwgVO.setResultMSG("정상 수정되었습니다.");
		return strDwgVO;
	}

	@RequestMapping(value = { "/api/strdwg/deleteStrDwg.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody StrDwgVO deleteStrDwg(@RequestBody StrDwgVO strDwgVO) throws Exception {
		BindBeansToActiveUser(strDwgVO);
		strDwgService.deleteStrDwg(strDwgVO);
		strDwgVO.setResultSuccess("true");
		strDwgVO.setResultMSG("정상 삭제되었습니다.");
		return strDwgVO;
	}
	
	@RequestMapping(value = { "/api/strdwg/checkStrDwg.do" })
	public String checkStrDwg(@ModelAttribute("searchVO") StrDwgVO strDwgVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if(strDwgVO.getDWG_FILE() == null) { return null; }
		List<String> fileList = new ArrayList<String>(); 
		
		String[] dwgList = strDwgVO.getDWG_FILE().split(",");
		
		String dwgPath = pathInfoProperties.getProperty("file.dwg.path");
		
		for(String dwg : dwgList){
			String fullPath = checkFilePath(dwgPath, "path") + "\\" 
							+ checkFilePath(strDwgVO.getROAD_NO(), "path") 
							+ "\\" + checkFilePath(dwg, "name");
			File dwgfile = new File(fullPath + ".dwg");
			File dwffile = new File(fullPath + ".dwf");
			
			if(!dwgfile.exists()){
				fileList.add(dwg + ".dwg");
			}
			
			if(!dwffile.exists()){
				fileList.add(dwg + ".dwf");
			}
		}
		
		model.addAttribute("fileList", fileList);
		
		return "jsonView";
	}
	
	@RequestMapping(value = { "/api/strdwg/downloadStrDwg.do" })
	public String downloadStrDwg(@ModelAttribute("searchVO") StrDwgVO strDwgVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if(strDwgVO.getDWG_FILE() == null) { return null; }
		
		String[] dwgList = strDwgVO.getDWG_FILE().split(",");
		
		String dwgPath = pathInfoProperties.getProperty("file.dwg.path");
		String filePath = pathInfoProperties.getProperty("file.upload.path");
		String zipNm = "strDwg.zip";
		
		// Set the content type based to zip
		try{
			File zipFile = FileUploadUtils.createDwgZipFile(dwgList, filePath, "strDwg", strDwgVO.getROAD_NO(), dwgPath);
			
			response.setContentType("Content-type: text/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + zipNm + "\"");
			
			OutputStream os = response.getOutputStream();
			InputStream in = new FileInputStream(zipFile);
            byte b[] = new byte[(int)zipFile.length()];
            int leng = 0;
             
            while( (leng = in.read(b)) > 0 ){
                os.write(b,0,leng);
            }
            
            in.close();
            os.close();

            
		}catch(IOException ex){
			
		}
		
		return null;
	}

}
