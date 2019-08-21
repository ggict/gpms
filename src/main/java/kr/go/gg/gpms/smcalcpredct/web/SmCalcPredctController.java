


package kr.go.gg.gpms.smcalcpredct.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.smcalcpredct.service.SmCalcPredctService;
import kr.go.gg.gpms.smcalcpredct.service.model.SmCalcPredctVO;

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
 * @Class Name : SmCalcPredctController.java
 * @Description : SmCalcPredct Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("smCalcPredctController")
public class SmCalcPredctController extends BaseController {

	@Resource(name = "smCalcPredctService")
	private SmCalcPredctService smCalcPredctService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmCalcPredctController.class);

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 목록을 조회한다. (pageing)
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return "/smcalcpredct/SmCalcPredctList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smcalcpredct/selectSmCalcPredctList.do" })
	public String selectSmCalcPredctList(@ModelAttribute("searchVO") SmCalcPredctVO smCalcPredctVO, ModelMap model) throws Exception {
		return "/smcalcpredct/SmCalcPredctList" ;
	}
	
	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 목록을 조회한다. (pageing)
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return "/smcalcpredct/SmCalcPredctList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smcalcpredct/selectSmCalcPredctList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectSmCalcPredctListRest(@RequestBody SmCalcPredctVO smCalcPredctVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smCalcPredctVO.getPage());
		paginationInfo.setRecordCountPerPage(smCalcPredctVO.getPageUnit());
		paginationInfo.setPageSize(smCalcPredctVO.getRows());
		smCalcPredctVO.setUsePage(true);
		
		smCalcPredctVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smCalcPredctVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smCalcPredctVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<SmCalcPredctVO> items = smCalcPredctService.selectSmCalcPredctList(smCalcPredctVO);
		int total_count = smCalcPredctService.selectSmCalcPredctListTotalCount(smCalcPredctVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) smCalcPredctVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", smCalcPredctVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 목록을 조회한다.
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return "/smcalcpredct/SmCalcPredctList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/smcalcpredct/selectSmCalcPredctListGraph.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<SmCalcPredctVO> selectSmCalcPredctListGraph(@RequestBody SmCalcPredctVO smCalcPredctVO, ModelMap model) throws Exception {
		smCalcPredctVO.setUsePage(false);
		return smCalcPredctService.selectSmCalcPredctList(smCalcPredctVO);
	}
	
	
	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 상세를 조회한다.
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return "/smcalcpredct/SmCalcPredctView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/smcalcpredct/selectSmCalcPredct.do" })
	public String selectSmCalcPredct(@ModelAttribute("searchVO") SmCalcPredctVO smCalcPredctVO, ModelMap model) throws Exception {
	
		model.addAttribute("smCalcPredctVO", smCalcPredctService.selectSmCalcPredct(smCalcPredctVO));
		return "/smcalcpredct/SmCalcPredctView";
	}


	@RequestMapping(value = { "/smcalcpredct/addSmCalcPredctView.do" })
	public String addSmCalcPredctView(@ModelAttribute("searchVO") SmCalcPredctVO smCalcPredctVO, Model model) throws Exception {
		model.addAttribute("smCalcPredctVO", new SmCalcPredctVO());
		return "/smcalcpredct/SmCalcPredctRegist";
	}

	@RequestMapping(value = { "/api/smcalcpredct/addSmCalcPredct.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmCalcPredctVO addSmCalcPredct(@RequestBody SmCalcPredctVO smCalcPredctVO) throws Exception {
		BindBeansToActiveUser(smCalcPredctVO);
		smCalcPredctService.insertSmCalcPredct(smCalcPredctVO);
		smCalcPredctVO.setResultSuccess("true");
		smCalcPredctVO.setResultMSG("정상 등록되었습니다.");
		return smCalcPredctVO;
	}
	
	@RequestMapping(value = { "/smcalcpredct/updateSmCalcPredctView.do" })
	public String updateSmCalcPredctView(@ModelAttribute("searchVO") SmCalcPredctVO smCalcPredctVO, Model model) throws Exception {
		model.addAttribute("smCalcPredctVO", smCalcPredctService.selectSmCalcPredct(smCalcPredctVO));
		return "/smcalcpredct/SmCalcPredctUpdate";
	}

	@RequestMapping(value = { "/api/smcalcpredct/updateSmCalcPredct.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmCalcPredctVO updateSmCalcPredct(@RequestBody SmCalcPredctVO smCalcPredctVO) throws Exception {
		BindBeansToActiveUser(smCalcPredctVO);
		smCalcPredctService.updateSmCalcPredct(smCalcPredctVO);
		smCalcPredctVO.setResultSuccess("true");
		smCalcPredctVO.setResultMSG("정상 수정되었습니다.");
		return smCalcPredctVO;
	}

	@RequestMapping(value = { "/api/smcalcpredct/deleteSmCalcPredct.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody SmCalcPredctVO deleteSmCalcPredct(@RequestBody SmCalcPredctVO smCalcPredctVO) throws Exception {
		BindBeansToActiveUser(smCalcPredctVO);
		smCalcPredctService.deleteSmCalcPredct(smCalcPredctVO);
		smCalcPredctVO.setResultSuccess("true");
		smCalcPredctVO.setResultMSG("정상 삭제되었습니다.");
		return smCalcPredctVO;
	}

}
