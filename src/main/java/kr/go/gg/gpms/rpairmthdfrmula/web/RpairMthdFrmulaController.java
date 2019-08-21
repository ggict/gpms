


package kr.go.gg.gpms.rpairmthdfrmula.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.go.gg.gpms.rpairmthdfrmula.service.RpairMthdFrmulaService;
import kr.go.gg.gpms.rpairmthdfrmula.service.model.RpairMthdFrmulaVO;
import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcVO;

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
 * @Class Name : RpairMthdFrmulaController.java
 * @Description : RpairMthdFrmula Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("rpairMthdFrmulaController")
public class RpairMthdFrmulaController extends BaseController {

	@Resource(name = "rpairMthdFrmulaService")
	private RpairMthdFrmulaService rpairMthdFrmulaService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpairMthdFrmulaController.class);

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 목록을 조회한다. (pageing)
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return "/rpairmthdfrmula/RpairMthdFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/rpairmthdfrmula/selectRpairMthdFrmulaList.do" })
	public String selectRpairMthdFrmulaList(@ModelAttribute("searchVO") RpairMthdFrmulaVO rpairMthdFrmulaVO, ModelMap model) throws Exception {
		
		return "/repairtarget/frmula/repairtargetFrmulaList" ;
	}
	
	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 목록을 조회한다. (pageing)
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return "/rpairmthdfrmula/RpairMthdFrmulaList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/rpairmthdfrmula/selectRpairMthdFrmulaList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  @ResponseBody Map<String, Object> selectRpairMthdFrmulaListRest(@RequestBody RpairMthdFrmulaVO rpairMthdFrmulaVO, ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		rpairMthdFrmulaVO.setFRMULA_NO("2"); //GPCI
		rpairMthdFrmulaVO.setUsePage(false);
		
		//GPCI
		rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0002");
		map.put("gpciList", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));
		
		//LCTC
		if(rpairMthdFrmulaVO.getFRMULA_TYPE().equals("NOMAL") || rpairMthdFrmulaVO.getFRMULA_TYPE().equals("SPATIAL")){
			//LCTC_1
			rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0004");
			rpairMthdFrmulaVO.setCALC_ORDR("1");
			map.put("lctcList1", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));
			
			//LCTC_2
			rpairMthdFrmulaVO.setCALC_ORDR("2");
			rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0004");
			map.put("lctcList2", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));
			
			rpairMthdFrmulaVO.setCALC_ORDR(null);
		}else{
			rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0004");
			map.put("lctcList", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));
		}
		
		//RD
		rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0005");
		map.put("rdList", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));
		
		//AC
		rpairMthdFrmulaVO.setSTEP_SE_CODE("RPST0006");
		map.put("acList", rpairMthdFrmulaService.selectRpairMthdFrmulaList(rpairMthdFrmulaVO));

		return map;
	}
	
	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 상세를 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return "/rpairmthdfrmula/RpairMthdFrmulaView"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/rpairmthdfrmula/selectRpairMthdFrmula.do" })
	public String selectRpairMthdFrmula(@ModelAttribute("searchVO") RpairMthdFrmulaVO rpairMthdFrmulaVO, ModelMap model) throws Exception {
	
		model.addAttribute("rpairMthdFrmulaVO", rpairMthdFrmulaService.selectRpairMthdFrmula(rpairMthdFrmulaVO));
		return "/rpairmthdfrmula/RpairMthdFrmulaView";
	}


	@RequestMapping(value = { "/rpairmthdfrmula/addRpairMthdFrmulaView.do" })
	public String addRpairMthdFrmulaView(@ModelAttribute("searchVO") RpairMthdFrmulaVO rpairMthdFrmulaVO, Model model) throws Exception {
		model.addAttribute("rpairMthdFrmulaVO", new RpairMthdFrmulaVO());
		return "/rpairmthdfrmula/RpairMthdFrmulaRegist";
	}

	@RequestMapping(value = { "/api/rpairmthdfrmula/addRpairMthdFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdFrmulaVO addRpairMthdFrmula(@RequestBody RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		BindBeansToActiveUser(rpairMthdFrmulaVO);
		rpairMthdFrmulaService.insertRpairMthdFrmula(rpairMthdFrmulaVO);
		rpairMthdFrmulaVO.setResultSuccess("true");
		rpairMthdFrmulaVO.setResultMSG("정상 등록되었습니다.");
		return rpairMthdFrmulaVO;
	}
	
	@RequestMapping(value = { "/rpairmthdfrmula/updateRpairMthdFrmulaView.do" })
	public String updateRpairMthdFrmulaView(@ModelAttribute("searchVO") RpairMthdFrmulaVO rpairMthdFrmulaVO, Model model) throws Exception {
		model.addAttribute("rpairMthdFrmulaVO", rpairMthdFrmulaService.selectRpairMthdFrmula(rpairMthdFrmulaVO));
		return "/rpairmthdfrmula/RpairMthdFrmulaUpdate";
	}

	@RequestMapping(value = { "/api/rpairmthdfrmula/updateRpairMthdFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdFrmulaVO updateRpairMthdFrmula(@RequestBody RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		BindBeansToActiveUser(rpairMthdFrmulaVO);
		//공법 변경
		for(RpairMthdFrmulaVO frmula : rpairMthdFrmulaVO.getRpairMthdFrmulaList()){
			BindBeansToActiveUser(frmula);
			rpairMthdFrmulaService.insertRpairMthdFrmulaHist(frmula);
			rpairMthdFrmulaService.updateRpairMthdFrmula(frmula);
		}
				
		rpairMthdFrmulaVO.setResultSuccess("true");
		rpairMthdFrmulaVO.setResultMSG("정상 저장되었습니다.");
		return rpairMthdFrmulaVO;
	}

	@RequestMapping(value = { "/api/rpairmthdfrmula/deleteRpairMthdFrmula.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody RpairMthdFrmulaVO deleteRpairMthdFrmula(@RequestBody RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		BindBeansToActiveUser(rpairMthdFrmulaVO);
		rpairMthdFrmulaService.deleteRpairMthdFrmula(rpairMthdFrmulaVO);
		rpairMthdFrmulaVO.setResultSuccess("true");
		rpairMthdFrmulaVO.setResultMSG("정상 삭제되었습니다.");
		return rpairMthdFrmulaVO;
	}

}
