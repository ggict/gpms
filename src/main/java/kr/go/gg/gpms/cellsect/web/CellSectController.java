


package kr.go.gg.gpms.cellsect.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cell10.service.Cell10Service;
import kr.go.gg.gpms.cellsect.service.CellSectService;
import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

/**
 * @Class Name : Cell10Controller.java
 * @Description : Cell10 Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("cellsectController")
public class CellSectController extends BaseController {

	@Resource(name = "cellSectService")
	private CellSectService cellSectService;

	@Resource(name = "cell10Service")
	private Cell10Service cell10Service;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CellSectController.class);

	/**
	 * CELL_SECT(CELL_SECT) 목록을 조회한다. (pageing)
	 * @param cellSectVO - 조회할 정보가 담긴 CellSect
	 * @return "/cellsect/cellSectUpdate"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cellsect/selectCellSectUpdate.do" })
	public String selectCellSectUpdate(@ModelAttribute("searchVO") CellSectVO cellSectVO,  ModelMap model) throws Exception {

		return "/cellsect/cellSectUpdate" ;
	}

	/**
	 * CELL_SECT(CELL_SECT) 목록을 조회한다. (pageing)
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return "/cellSect/CellSectList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/selectCellSectList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectCellSectList(
			@RequestBody CellSectVO cellSectVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cellSectVO.getPage());
		paginationInfo.setRecordCountPerPage(cellSectVO.getPageUnit());
		paginationInfo.setPageSize(cellSectVO.getRows());
		cellSectVO.setUsePage(true);

		cellSectVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cellSectVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cellSectVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CellSectVO> items = cellSectService.selectCellSectList(cellSectVO);
		int totCnt = cellSectService.selectCellSectListTotalCount(cellSectVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt
					/ (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", cellSectVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);

		return map;
	}

	/**
	 * CELL_SECT(CELL_SECT) 섹션구분을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return "/cellSect/CellSectList"
	 * @exception Exception
	 */
	@RequestMapping(value = {  "/api/cellSect/updateSectSe.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CellSectVO updateSectSe(@RequestBody CellSectVO cellSectVO, HttpSession session) throws Exception {
		BindBeansToActiveUser(cellSectVO);
		cellSectService.updateSectSe(cellSectVO);
		//섹션구분에 따른 CELL_TYPE(CELL_10)수정
		cell10Service.updateCell10Type(cellSectVO);
		cellSectVO.setResultSuccess("true");
		cellSectVO.setResultMSG("정상 수정되었습니다.");
		return cellSectVO;
	}

	/**
	 * 도로대장(점) 목록을 조회한다.
	 * @param
	 * @return "/cellsect/staTotPop"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cellsect/selectStaTotPop.do" })
	public String selectStaTotPop(@ModelAttribute("searchVO") CellSectVO cellSectVO,  ModelMap model) throws Exception {

		return "/cellsect/staTotPop" ;
	}

	/**
     * 도로대장 DWG 파일 다운로드
     * @param
     * @return null
     * @exception Exception
     */
	@RequestMapping(value = "/cellsect/staTotDwgDownloadFile.do")
    public String staTotDwgDownloadFile(@ModelAttribute Map<String, String> paramMap, HttpServletResponse response, Model model) throws Exception {
	    Map<String, String> dataMap = cellSectService.selectStaTotDwgFileInfo(paramMap);



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

            String filePath = checkFilePath(subPath, "path");

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
}
