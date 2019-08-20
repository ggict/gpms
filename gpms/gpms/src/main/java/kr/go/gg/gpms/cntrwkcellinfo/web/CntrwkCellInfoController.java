


package kr.go.gg.gpms.cntrwkcellinfo.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.cntrwkcellinfo.service.CntrwkCellInfoService;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;

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
 * @Class Name : CntrwkCellInfoController.java
 * @Description : CntrwkCellInfo Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-31
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("cntrwkCellInfoController")
public class CntrwkCellInfoController extends BaseController {

	@Resource(name = "cntrwkCellInfoService")
	private CntrwkCellInfoService cntrwkCellInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CntrwkCellInfoController.class);

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 목록을 조회한다. (pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkcellinfo/selectCntrwkCellInfoList.do" })
	public String selectCntrwkCellInfoList(CntrwkCellInfoVO cntrwkCellInfoVO, ModelMap model) throws Exception {
		return "/cntrwkcellinfo/CntrwkCellInfoList" ;
	}
	
	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 목록을 조회한다. (pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkcellinfo/selectCntrwkCellInfoList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectCntrwkCellInfoListRest(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkCellInfoVO.getPage());
		paginationInfo.setRecordCountPerPage(cntrwkCellInfoVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkCellInfoVO.getRows());
		cntrwkCellInfoVO.setUsePage(true);
		
		cntrwkCellInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkCellInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkCellInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkCellInfoVO> items = cntrwkCellInfoService.selectCntrwkCellInfoList(cntrwkCellInfoVO);
		int total_count = cntrwkCellInfoService.selectCntrwkCellInfoListTotalCount(cntrwkCellInfoVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cntrwkCellInfoVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", cntrwkCellInfoVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 상세를 조회한다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/cntrwkcellinfo/selectCntrwkCellInfo.do" })
	public String selectCntrwkCellInfo(@ModelAttribute("searchVO") CntrwkCellInfoVO cntrwkCellInfoVO, ModelMap model) throws Exception {
	
		model.addAttribute("cntrwkCellInfoVO", cntrwkCellInfoService.selectCntrwkCellInfo(cntrwkCellInfoVO));
		return "/cntrwkcellinfo/CntrwkCellInfoView";
	}


	@RequestMapping(value = { "/cntrwkcellinfo/addCntrwkCellInfoView.do" })
	public String addCntrwkCellInfoView(@ModelAttribute("searchVO") CntrwkCellInfoVO cntrwkCellInfoVO, Model model) throws Exception {
		model.addAttribute("cntrwkCellInfoVO", new CntrwkCellInfoVO());
		return "/cntrwkcellinfo/CntrwkCellInfoRegist";
	}

	@RequestMapping(value = { "/api/cntrwkcellinfo/addCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkCellInfoVO addCntrwkCellInfo(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		BindBeansToActiveUser(cntrwkCellInfoVO);
		cntrwkCellInfoService.insertCntrwkCellInfo(cntrwkCellInfoVO);
		cntrwkCellInfoVO.setResultSuccess("true");
		cntrwkCellInfoVO.setResultMSG("정상 등록되었습니다.");
		return cntrwkCellInfoVO;
	}
	
	@RequestMapping(value = { "/cntrwkcellinfo/updateCntrwkCellInfoView.do" })
	public String updateCntrwkCellInfoView(@ModelAttribute("searchVO") CntrwkCellInfoVO cntrwkCellInfoVO, Model model) throws Exception {
		model.addAttribute("cntrwkCellInfoVO", cntrwkCellInfoService.selectCntrwkCellInfo(cntrwkCellInfoVO));
		return "/cntrwkcellinfo/CntrwkCellInfoUpdate";
	}

	@RequestMapping(value = { "/api/cntrwkcellinfo/updateCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkCellInfoVO updateCntrwkCellInfo(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		BindBeansToActiveUser(cntrwkCellInfoVO);
		cntrwkCellInfoService.updateCntrwkCellInfo(cntrwkCellInfoVO);
		cntrwkCellInfoVO.setResultSuccess("true");
		cntrwkCellInfoVO.setResultMSG("정상 수정되었습니다.");
		return cntrwkCellInfoVO;
	}

	@RequestMapping(value = { "/api/cntrwkcellinfo/deleteCntrwkCellInfo.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody CntrwkCellInfoVO deleteCntrwkCellInfo(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		BindBeansToActiveUser(cntrwkCellInfoVO);
		cntrwkCellInfoService.deleteCntrwkCellInfo(cntrwkCellInfoVO);
		cntrwkCellInfoVO.setResultSuccess("true");
		cntrwkCellInfoVO.setResultMSG("정상 삭제되었습니다.");
		return cntrwkCellInfoVO;
	}

	
	/**
	 * 공사_셀별 포장년도을 조회한다. (pageing)
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkcellinfo/selectPavYearListByCellId.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectPavYearListByCellIdRest(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cntrwkCellInfoVO.getPage());
		paginationInfo.setRecordCountPerPage(cntrwkCellInfoVO.getPageUnit());
		paginationInfo.setPageSize(cntrwkCellInfoVO.getRows());
		cntrwkCellInfoVO.setUsePage(true);
		
		cntrwkCellInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cntrwkCellInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cntrwkCellInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntrwkCellInfoVO> items = cntrwkCellInfoService.selectPavYearListByCellId(cntrwkCellInfoVO);
		int total_count = cntrwkCellInfoService.selectPavYearListByCellIdTotalCount(cntrwkCellInfoVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) cntrwkCellInfoVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", cntrwkCellInfoVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)를 조회한다. 
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return "/cntrwkcellinfo/CntrwkCellInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/cntrwkcellinfo/selectPavYearListAll.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody List<CntrwkCellInfoVO> selectPavYearListAllRest(@RequestBody CntrwkCellInfoVO cntrwkCellInfoVO, ModelMap model) throws Exception {
		
		cntrwkCellInfoVO.setUsePage(false);
		List<CntrwkCellInfoVO> items = cntrwkCellInfoService.selectCntrwkCellInfoList(cntrwkCellInfoVO);
		
		return items;
	}
}
