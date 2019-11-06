package kr.go.gg.gpms.srvyrequstsctncellinfo.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.SrvyRequstSctnCellInfoService;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.model.SrvyRequstSctnCellInfoVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cell10.service.model.Cell10VO;

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
 * @Class Name : SrvyRequstSctnCellInfoController.java
 * @Description : SrvyRequstSctnCellInfo Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("srvyRequstSctnCellInfoController")
public class SrvyRequstSctnCellInfoController extends BaseController {

	@Resource(name = "srvyRequstSctnCellInfoService")
	private SrvyRequstSctnCellInfoService srvyRequstSctnCellInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrvyRequstSctnCellInfoController.class);

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 목록을 조회한다. (pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/srvyrequstsctncellinfo/selectSrvyRequstSctnCellInfoList.do" })
	public String selectCntrwkCellInfoList(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO, ModelMap model) throws Exception {
		return "/srvyrequstsctncellinfo/srvyRequstSctnCellInfoList" ;
	}
	
	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 목록을 조회한다. (pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/selectSrvyRequstSctnCellInfoList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectCntrwkCellInfoListRest(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srvyRequstSctnCellInfoVO.getPage());
		paginationInfo.setRecordCountPerPage(srvyRequstSctnCellInfoVO.getPageUnit());
		paginationInfo.setPageSize(srvyRequstSctnCellInfoVO.getRows());
		srvyRequstSctnCellInfoVO.setUsePage(true);
		
		srvyRequstSctnCellInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		srvyRequstSctnCellInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		srvyRequstSctnCellInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SrvyRequstSctnCellInfoVO> items = srvyRequstSctnCellInfoService.selectSrvyRequstSctnCellInfoList(srvyRequstSctnCellInfoVO);
		int total_count = srvyRequstSctnCellInfoService.selectSrvyRequstSctnCellInfoListTotalCount(srvyRequstSctnCellInfoVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) srvyRequstSctnCellInfoVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", srvyRequstSctnCellInfoVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 목록을 조회한다. (no pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/selectSrvyRequstSctnCellInfoAllList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectCntrwkCellInfoAllListRest(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO, ModelMap model) throws Exception {
		srvyRequstSctnCellInfoVO.setUsePage(false);
		if (srvyRequstSctnCellInfoVO.getPAV_CELL_ID_LIST() != null) {
			srvyRequstSctnCellInfoVO.setSRVY_REQUST_SCTN_NO(null);
		}
		List<Cell10VO> items = srvyRequstSctnCellInfoService.selectRouteInfoListByCellID(srvyRequstSctnCellInfoVO);
		
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", srvyRequstSctnCellInfoVO.getPage());
		map.put("rows", items);

		return map;
	}

	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/addCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyRequstSctnCellInfoVO addCntrwkCellInfo(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		BindBeansToActiveUser(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoService.insertSrvyRequstSctnCellInfo(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoVO.setResultSuccess("true");
		srvyRequstSctnCellInfoVO.setResultMSG("정상 등록되었습니다.");
		return srvyRequstSctnCellInfoVO;
	}
	
	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/updateCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyRequstSctnCellInfoVO updateCntrwkCellInfo(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		BindBeansToActiveUser(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoService.updateSrvyRequstSctnCellInfo(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoVO.setResultSuccess("true");
		srvyRequstSctnCellInfoVO.setResultMSG("정상 수정되었습니다.");
		return srvyRequstSctnCellInfoVO;
	}

	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/deleteCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SrvyRequstSctnCellInfoVO deleteCntrwkCellInfo(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		BindBeansToActiveUser(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoService.deleteSrvyRequstSctnCellInfo(srvyRequstSctnCellInfoVO);
		srvyRequstSctnCellInfoVO.setResultSuccess("true");
		srvyRequstSctnCellInfoVO.setResultMSG("정상 삭제되었습니다.");
		return srvyRequstSctnCellInfoVO;
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)를 조회한다. - 위치보기 CELL리스트 조회
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/srvyrequstsctncellinfo/selectPavYearListAll.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<SrvyRequstSctnCellInfoVO> selectPavYearListAllRest(@RequestBody SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO, ModelMap model) throws Exception {
		srvyRequstSctnCellInfoVO.setUsePage(false);
		List<SrvyRequstSctnCellInfoVO> items = srvyRequstSctnCellInfoService.selectSrvyRequstSctnCellInfoList(srvyRequstSctnCellInfoVO);
		
		return items;
	}
}
