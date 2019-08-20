


package kr.go.gg.gpms.dwginfo.web;

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
import kr.go.gg.gpms.dwginfo.service.DwgInfoService;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

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

/**
 * @Class Name : DwgInfoController.java
 * @Description : DwgInfo Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("dwgInfoController")
public class DwgInfoController extends BaseController {

	@Resource(name = "dwgInfoService")
	private DwgInfoService dwgInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DwgInfoController.class);

	/**
	 * DWG_INFO(DWG_INFO) 목록을 조회한다. (pageing)
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return "/dwginfo/DwgInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/dwginfo/selectDwgInfoList.do" })
	public String selectDwgInfoList(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, ModelMap model) throws Exception {
		dwgInfoVO.setSidx("DWG_NAME");
		//구간 목록
		List<DwgInfoVO> dwgSectList = dwgInfoService.selectDwgInfoSectList(dwgInfoVO);
		
		model.addAttribute("dwgSectList", dwgSectList);
		model.addAttribute("dwgInfoVO", dwgInfoVO);
		return "/dwginfo/dwginfoList" ;
	}
	
	/**
	 * DWG_INFO(DWG_INFO) 목록을 조회한다. (pageing)
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return "/dwginfo/DwgInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/dwginfo/selectDwgInfoList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectDwgInfoListRest(@RequestBody DwgInfoVO dwgInfoVO, ModelMap model) throws Exception {
		dwgInfoVO.setUsePage(false);
		
		List<DwgInfoVO> items = dwgInfoService.selectDwgInfoList(dwgInfoVO);
		int total_count = dwgInfoService.selectDwgInfoListTotalCount(dwgInfoVO);
		
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * DWG_INFO(DWG_INFO) 상세를 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return "/dwginfo/DwgInfoView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/dwginfo/selectDwgInfo.do" })
	public String selectDwgInfo(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, ModelMap model) throws Exception {
	
		model.addAttribute("dwgInfoVO", dwgInfoService.selectDwgInfo(dwgInfoVO));
		return "/dwginfo/dwginfoView";
	}


	@RequestMapping(value = { "/dwginfo/addDwgInfoView.do" })
	public String addDwgInfoView(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, Model model) throws Exception {
		model.addAttribute("dwgInfoVO", new DwgInfoVO());
		return "/dwginfo/dwginfoRegist";
	}

	@RequestMapping(value = { "/api/dwginfo/addDwgInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody DwgInfoVO addDwgInfo(@RequestBody DwgInfoVO dwgInfoVO) throws Exception {
		BindBeansToActiveUser(dwgInfoVO);
		dwgInfoService.insertDwgInfo(dwgInfoVO);
		dwgInfoVO.setResultSuccess("true");
		dwgInfoVO.setResultMSG("정상 등록되었습니다.");
		return dwgInfoVO;
	}
	
	@RequestMapping(value = { "/dwginfo/updateDwgInfoView.do" })
	public String updateDwgInfoView(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, Model model) throws Exception {
		model.addAttribute("dwgInfoVO", dwgInfoService.selectDwgInfo(dwgInfoVO));
		return "/dwginfo/dwginfoUpdate";
	}

	@RequestMapping(value = { "/api/dwginfo/updateDwgInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody DwgInfoVO updateDwgInfo(@RequestBody DwgInfoVO dwgInfoVO) throws Exception {
		BindBeansToActiveUser(dwgInfoVO);
		dwgInfoService.updateDwgInfo(dwgInfoVO);
		dwgInfoVO.setResultSuccess("true");
		dwgInfoVO.setResultMSG("정상 수정되었습니다.");
		return dwgInfoVO;
	}

	@RequestMapping(value = { "/api/dwginfo/deleteDwgInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody DwgInfoVO deleteDwgInfo(@RequestBody DwgInfoVO dwgInfoVO) throws Exception {
		BindBeansToActiveUser(dwgInfoVO);
		dwgInfoService.deleteDwgInfo(dwgInfoVO);
		dwgInfoVO.setResultSuccess("true");
		dwgInfoVO.setResultMSG("정상 삭제되었습니다.");
		return dwgInfoVO;
	}
	

	@RequestMapping(value = { "/api/dwginfo/checkDwgInfo.do" })
	public String checkDwgInfo(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if(dwgInfoVO.getDWG_NAME() == null) { return null; }
		List<String> fileList = new ArrayList<String>(); 
		
		String[] dwgList = dwgInfoVO.getDWG_NAME().split(",");
		
		String dwgPath = pathInfoProperties.getProperty("file.dwg.path");
		
		for(String dwg : dwgList){
			String fullPath = checkFilePath(dwgPath, "path") + "\\" 
					+ checkFilePath(dwgInfoVO.getROAD_NO(), "path") + "\\" 
					+ checkFilePath(dwg, "name");
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
	
	@RequestMapping(value = { "/api/dwginfo/downloadDwgInfo.do" })
	public String downloadDwgInfo(@ModelAttribute("searchVO") DwgInfoVO dwgInfoVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if(dwgInfoVO.getDWG_NAME() == null) { return null; }
		
		String[] dwgList = dwgInfoVO.getDWG_NAME().split(",");
		
		String dwgPath = pathInfoProperties.getProperty("file.dwg.path");
		String filePath = pathInfoProperties.getProperty("file.upload.path");
		String zipNm = "dwgInfo.zip";
		
		// Set the content type based to zip
		try{
			
			File zipFile = FileUploadUtils.createDwgZipFile(dwgList, filePath, "dwgInfo", dwgInfoVO.getROAD_NO(), dwgPath);
			
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
