package egovframework.cmmn.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.cmmn.service.EgovFileMngService;
import egovframework.cmmn.service.SrvyDtaImageFileVO;
import egovframework.cmmn.util.EgovResourceCloseHelper;


/**
 * @Class Name : EgovImageProcessController.java
 * @Description :
 * @Modification Information
 *
 *    수정일       	수정자         수정내용
 *    ----------   ---------     -------------------
 *    2009.04.02	이삼섭			최초생성
 *    2014.03.31	유지보수		fileSn 오류수정
 *    2018.08.31	이정은		MimeType 중복설정 제거
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
@Controller
public class EgovImageProcessController extends HttpServlet {

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovImageProcessController.class);

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getSrvyDtaImage.do")
    public void getSrvyDtaImage(ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletResponse response) throws Exception {
        FileInputStream fis = null;

        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;

        try {
    		String DTA_NO = (String)commandMap.get("DTA_NO");
    		String TYPE = (String)commandMap.get("TYPE");  // 1: 노면이미지, 2: 균열분석이미지

    		SrvyDtaImageFileVO vo = new SrvyDtaImageFileVO();
    		vo.setDTA_NO(DTA_NO);

    		SrvyDtaImageFileVO fvo = fileService.selectSrvyDtaImageFileInf(vo);

    		File file = null;

		    if ( "1".equals(TYPE) ) {
		        file = new File(fvo.getFRNT_IMG_FILE_COURS(), fvo.getFRNT_IMG_FILE_NM());
		    } else {
		        file = new File(fvo.getCR_IMG_FILE_COURS(), fvo.getCR_IMG_FILE_NM());
		    }
		    fis = new FileInputStream(file);

		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
		    	bStream.write(imgByte);
		    }

			String type = "";

			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			if (ext != null && !"".equals(ext)) {
			    if ("jpg".equals(ext.toLowerCase())) {
				type = "image/jpeg";
			    } else {
			    	type = "image/" + ext.toLowerCase();
			    }
			    /*type = "image/" + fvo.getFileExtsn().toLowerCase();*/

			} else {
				LOGGER.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
		} finally {
			EgovResourceCloseHelper.close(bStream, in, fis);
		}
    }
}
