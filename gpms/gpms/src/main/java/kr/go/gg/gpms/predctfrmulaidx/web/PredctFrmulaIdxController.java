


package kr.go.gg.gpms.predctfrmulaidx.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.predctfrmulaidx.service.PredctFrmulaIdxService;
import kr.go.gg.gpms.predctfrmulaidx.service.model.PredctFrmulaIdxVO;

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
 * @Class Name : PredctFrmulaIdxController.java
 * @Description : PredctFrmulaIdx Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-08-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("predctFrmulaIdxController")
public class PredctFrmulaIdxController extends BaseController {

	@Resource(name = "predctFrmulaIdxService")
	private PredctFrmulaIdxService predctFrmulaIdxService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PredctFrmulaIdxController.class);

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param predctFrmulaIdxVO - 조회할 정보가 담긴 PredctFrmulaIdxVO
	 * @return "/predctfrmulaidx/PredctFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/predctfrmulaidx/selectPredctFrmulaIdxList.do" })
	public String selectPredctFrmulaIdxList(@ModelAttribute("searchVO") PredctFrmulaIdxVO predctFrmulaIdxVO, ModelMap model) throws Exception {
		setSystem("mng");
		predctFrmulaIdxVO.setUsePage(false);
		predctFrmulaIdxVO.setSidx("DFECT_KND,CELL_TYPE");
		return "/predctfrmulaidx/predctfrmulaidxList" ;
	}
	
	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 목록을 조회한다. (pageing)
	 * @param predctFrmulaIdxVO - 조회할 정보가 담긴 PredctFrmulaIdxVO
	 * @return "/predctfrmulaidx/PredctFrmulaIdxList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/predctfrmulaidx/selectPredctFrmulaIdxList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectPredctFrmulaIdxListRest(@RequestBody PredctFrmulaIdxVO predctFrmulaIdxVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(predctFrmulaIdxVO.getPage());
		paginationInfo.setRecordCountPerPage(predctFrmulaIdxVO.getPageUnit());
		paginationInfo.setPageSize(predctFrmulaIdxVO.getRows());
		predctFrmulaIdxVO.setUsePage(false);
		
		predctFrmulaIdxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		predctFrmulaIdxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		predctFrmulaIdxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<PredctFrmulaIdxVO> items = predctFrmulaIdxService.selectPredctFrmulaIdxList(predctFrmulaIdxVO);
		int total_count = predctFrmulaIdxService.selectPredctFrmulaIdxListTotalCount(predctFrmulaIdxVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) predctFrmulaIdxVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", predctFrmulaIdxVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 상세를 조회한다.
	 * @param predctFrmulaIdxVO - 조회할 정보가 담긴 PredctFrmulaIdxVO
	 * @return "/predctfrmulaidx/PredctFrmulaIdxView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/predctfrmulaidx/selectPredctFrmulaIdx.do" })
	public String selectPredctFrmulaIdx(@ModelAttribute("searchVO") PredctFrmulaIdxVO predctFrmulaIdxVO, ModelMap model) throws Exception {
	
		model.addAttribute("predctFrmulaIdxVO", predctFrmulaIdxService.selectPredctFrmulaIdx(predctFrmulaIdxVO));
		return "/predctfrmulaidx/PredctFrmulaIdxView";
	}


	@RequestMapping(value = { "/predctfrmulaidx/addPredctFrmulaIdxView.do" })
	public String addPredctFrmulaIdxView(@ModelAttribute("searchVO") PredctFrmulaIdxVO predctFrmulaIdxVO, Model model) throws Exception {
		model.addAttribute("predctFrmulaIdxVO", new PredctFrmulaIdxVO());
		return "/predctfrmulaidx/PredctFrmulaIdxRegist";
	}

	@RequestMapping(value = { "/api/predctfrmulaidx/addPredctFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PredctFrmulaIdxVO addPredctFrmulaIdx(@RequestBody PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		BindBeansToActiveUser(predctFrmulaIdxVO);
		predctFrmulaIdxService.insertPredctFrmulaIdx(predctFrmulaIdxVO);
		predctFrmulaIdxVO.setResultSuccess("true");
		predctFrmulaIdxVO.setResultMSG("정상 등록되었습니다.");
		return predctFrmulaIdxVO;
	}
	
	@RequestMapping(value = { "/predctfrmulaidx/updatePredctFrmulaIdxView.do" })
	public String updatePredctFrmulaIdxView(@ModelAttribute("searchVO") PredctFrmulaIdxVO predctFrmulaIdxVO, Model model) throws Exception {
		model.addAttribute("predctFrmulaIdxVO", predctFrmulaIdxService.selectPredctFrmulaIdx(predctFrmulaIdxVO));
		return "/predctfrmulaidx/PredctFrmulaIdxUpdate";
	}
	
	@RequestMapping(value = { "/predctfrmulaidx/updatePredctFrmulaIdx.do" })
	public String updatePredctFrmulaIdxRest(@ModelAttribute("searchVO") PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		BindBeansToActiveUser(predctFrmulaIdxVO);
		predctFrmulaIdxService.updatePredctFrmulaIdx(predctFrmulaIdxVO);
		predctFrmulaIdxVO.setResultSuccess("true");
		predctFrmulaIdxVO.setResultMSG("정상 수정되었습니다.");
		return "jsonView";
	}

	@RequestMapping(value = { "/api/predctfrmulaidx/updatePredctFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PredctFrmulaIdxVO updatePredctFrmulaIdx(@RequestBody PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		BindBeansToActiveUser(predctFrmulaIdxVO);
		predctFrmulaIdxService.updatePredctFrmulaIdx(predctFrmulaIdxVO);
		predctFrmulaIdxVO.setResultSuccess("true");
		predctFrmulaIdxVO.setResultMSG("정상 수정되었습니다.");
		return predctFrmulaIdxVO;
	}

	@RequestMapping(value = { "/api/predctfrmulaidx/deletePredctFrmulaIdx.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody PredctFrmulaIdxVO deletePredctFrmulaIdx(@RequestBody PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		BindBeansToActiveUser(predctFrmulaIdxVO);
		predctFrmulaIdxService.deletePredctFrmulaIdx(predctFrmulaIdxVO);
		predctFrmulaIdxVO.setResultSuccess("true");
		predctFrmulaIdxVO.setResultMSG("정상 삭제되었습니다.");
		return predctFrmulaIdxVO;
	}

}
