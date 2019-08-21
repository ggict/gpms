


package kr.go.gg.gpms.rpairmthduntpc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cellsect.service.CellSectService;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.rpairmthduntpc.service.RpairMthdUntpcService;
import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcVO;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : RpairMthdUntpcController.java
 * @Description : RpairMthdUntpc Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairMthdUntpcController")
public class RpairMthdUntpcController extends BaseController {

	@Resource(name = "rpairMthdUntpcService")
	private RpairMthdUntpcService rpairMthdUntpcService;
	
	@Resource(name = "cellSectService")
	private CellSectService cellSectService;
	
	@Resource(name = "deptService")
	protected DeptService deptService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpairMthdUntpcController.class);

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 목록을 조회한다. (pageing)
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return "/rpairmthduntpc/RpairMthdUntpcList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/rpairmthduntpc/selectRpairMthdUntpcList.do" })
	public String selectRpairMthdUntpcList(@ModelAttribute("searchVO") RpairMthdUntpcVO rpairMthdUntpcVO, ModelMap model) throws Exception {
		
		// Do Nothing
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0001");
		model.addAttribute("untpcDoNot", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		// 균열 Sealing
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0003");
		model.addAttribute("untpcSeal", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		// 표면처리
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0004");
		model.addAttribute("untpcSurf", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		// 50m 덧씌우기
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0005");
		model.addAttribute("untpc50", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		// 50m 절삭 덧씌우기
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0006");
		model.addAttribute("untpc50CO", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		// 75m 절삭 덧씌우기
		rpairMthdUntpcVO.setRPAIR_MTHD_CODE("RPIR0007");
		model.addAttribute("untpc75CO", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		
		return "/repairtarget/unptc/repairtargetUntpcList" ;
	}
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 목록을 조회한다. (pageing)
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return "/rpairmthduntpc/RpairMthdUntpcList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairmthduntpc/selectRpairMthdUntpcList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectRpairMthdUntpcListRest(@RequestBody RpairMthdUntpcVO rpairMthdUntpcVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rpairMthdUntpcVO.getPage());
		paginationInfo.setRecordCountPerPage(rpairMthdUntpcVO.getPageUnit());
		paginationInfo.setPageSize(rpairMthdUntpcVO.getRows());
		rpairMthdUntpcVO.setUsePage(true);
		
		rpairMthdUntpcVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rpairMthdUntpcVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rpairMthdUntpcVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RpairMthdUntpcVO> items = rpairMthdUntpcService.selectRpairMthdUntpcList(rpairMthdUntpcVO);
		int total_count = rpairMthdUntpcService.selectRpairMthdUntpcListTotalCount(rpairMthdUntpcVO);
		int total_page = 0;
		if (total_count > 0)
			total_page = (int) Math.ceil((float) total_count / (float) rpairMthdUntpcVO.getPageSize());
		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("page", rpairMthdUntpcVO.getPage());
		map.put("total", total_page);
		map.put("records", total_count);
		map.put("rows", items);

		return map;
	}
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 상세를 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return "/rpairmthduntpc/RpairMthdUntpcView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/rpairmthduntpc/selectRpairMthdUntpc.do" })
	public String selectRpairMthdUntpc(@ModelAttribute("searchVO") RpairMthdUntpcVO rpairMthdUntpcVO, ModelMap model) throws Exception {
	
		model.addAttribute("rpairMthdUntpcVO", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		return "/rpairmthduntpc/RpairMthdUntpcView";
	}


	@RequestMapping(value = { "/rpairmthduntpc/addRpairMthdUntpcView.do" })
	public String addRpairMthdUntpcView(@ModelAttribute("searchVO") RpairMthdUntpcVO rpairMthdUntpcVO, Model model) throws Exception {
		model.addAttribute("rpairMthdUntpcVO", new RpairMthdUntpcVO());
		return "/rpairmthduntpc/RpairMthdUntpcRegist";
	}

	@RequestMapping(value = { "/api/rpairmthduntpc/addRpairMthdUntpc.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdUntpcVO addRpairMthdUntpc(@RequestBody RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		BindBeansToActiveUser(rpairMthdUntpcVO);
		rpairMthdUntpcService.insertRpairMthdUntpc(rpairMthdUntpcVO);
		rpairMthdUntpcVO.setResultSuccess("true");
		rpairMthdUntpcVO.setResultMSG("정상 등록되었습니다.");
		return rpairMthdUntpcVO;
	}
	
	@RequestMapping(value = { "/rpairmthduntpc/updateRpairMthdUntpcView.do" })
	public String updateRpairMthdUntpcView(@ModelAttribute("searchVO") RpairMthdUntpcVO rpairMthdUntpcVO, Model model) throws Exception {
		model.addAttribute("rpairMthdUntpcVO", rpairMthdUntpcService.selectRpairMthdUntpc(rpairMthdUntpcVO));
		return "/rpairmthduntpc/RpairMthdUntpcUpdate";
	}

	@RequestMapping(value = { "/api/rpairmthduntpc/updateRpairMthdUntpc.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdUntpcVO updateRpairMthdUntpc(@RequestBody RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		//변경 전 이력 등록
		BindBeansToActiveUser(rpairMthdUntpcVO);
		rpairMthdUntpcService.insertRpairMthdUntpcHist(rpairMthdUntpcVO);
		
		//공법 변경
		for(RpairMthdUntpcVO untpc : rpairMthdUntpcVO.getUNTPC_LIST()){
			BindBeansToActiveUser(untpc);
			untpc.setUSE_AT("Y");
			untpc.setDELETE_AT("N");
			rpairMthdUntpcService.updateRpairMthdUntpc(untpc);
		}
		
		rpairMthdUntpcVO.setResultSuccess("true");
		rpairMthdUntpcVO.setResultMSG("정상 저장되었습니다.");
		return rpairMthdUntpcVO;
	}

	@RequestMapping(value = { "/api/rpairmthduntpc/deleteRpairMthdUntpc.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdUntpcVO deleteRpairMthdUntpc(@RequestBody RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		BindBeansToActiveUser(rpairMthdUntpcVO);
		rpairMthdUntpcService.deleteRpairMthdUntpc(rpairMthdUntpcVO);
		rpairMthdUntpcVO.setResultSuccess("true");
		rpairMthdUntpcVO.setResultMSG("정상 삭제되었습니다.");
		return rpairMthdUntpcVO;
	}
	
	/**
	 * 보수대상 선정 > 예산 수준별 시나리오 분석
	 * @param rpairMthdUntpcVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rpairmthduntpc/selectRpairMthdUntpcSenario.do" })
	public String selectRpairMthdUntpcSenario(@ModelAttribute("searchVO") RpairMthdUntpcVO rpairMthdUntpcVO, DeptVO deptVO, Model model) throws Exception {
		model.addAttribute("cellSectVO", cellSectService.selectCellSectLenArea(null));
		model.addAttribute("avgUntpc", rpairMthdUntpcService.selectRpairMthdUntpcAvgTotAmount(rpairMthdUntpcVO));
		model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));
		model.addAttribute("type", rpairMthdUntpcVO.getType());
		return "/repairtarget/unptc/repairtargetUntpcSenario";
	}
	
	/**
	 * 보수대상 선정 > 예산수준별 시나리오 분석 정보를 조회한다
	 * @author    : skc
	 * @date      : 2017. 11. 27.
	 * 
	 * @param     : rpairMthdUntpcVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return    : map
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/api/rpairmthduntpc/selectStatUntpc.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<RpairMthdUntpcVO> selectStatUntpc(@RequestBody RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return rpairMthdUntpcService.prcStatUntpc(rpairMthdUntpcVO);
	}

}
