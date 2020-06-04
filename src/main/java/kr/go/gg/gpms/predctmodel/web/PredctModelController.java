package kr.go.gg.gpms.predctmodel.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.cmmn.util.DateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.predctmodel.service.PredctModelService;
import kr.go.gg.gpms.predctmodel.service.model.PredctModelVO;

/**
 * @Class Name : PredctModelController.java
 * @Description : PredctModel Controller class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2020-02-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller("predctModelController")
public class PredctModelController extends BaseController {

    @Resource(name = "predctModelService")
    private PredctModelService predctModelService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService egovPropertyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PredctModelController.class);


    /**
     * 통계 > 보수대상 선정 > 관리기관별통계 페이지 조회
     */
    @RequestMapping(value = "/predctModel/predctModelList.do")
    public String predctModelList(@ModelAttribute PredctModelVO searchVO, ModelMap model) throws Exception {

        // 선정년도 (2017 ~ 현재연도)
        model.addAttribute("slctnYearList", DateUtil.getSlctnYearList());
        model.addAttribute("predctModelVO", searchVO);

        return "/predctModel/predctModelList";
    }

    /**
     * 예측 모델 목록을 조회한다.
     * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/api/predctModel/selectPredctModelList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Map<String, Object> selectPredctModelList(@RequestBody PredctModelVO searchVO, ModelMap model, HttpSession session) throws Exception {
        searchVO.setUsePage(false);

        // 선형균열지수
        searchVO.setPredctModelKndSe("LCI");
        Map<String, Object> lciData = predctModelService.selectPredctModelList(searchVO);

        // 면형균열지수
        searchVO.setPredctModelKndSe("ACI");
        Map<String, Object> aciData = predctModelService.selectPredctModelList(searchVO);

        // 패칭 및 포트홀 지수
        searchVO.setPredctModelKndSe("PATI");
        Map<String, Object> patiData = predctModelService.selectPredctModelList(searchVO);

        // 소성변형지수
        searchVO.setPredctModelKndSe("RUTI");
        Map<String, Object> rutiData = predctModelService.selectPredctModelList(searchVO);

        // 경기도 포장상태평가지수
        searchVO.setPredctModelKndSe("GPCI");
        Map<String, Object> gpciData = predctModelService.selectPredctModelList(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lciData", lciData);
        map.put("aciData", aciData);
        map.put("patiData", patiData);
        map.put("rutiData", rutiData);
        map.put("gpciData", gpciData);

        return map;
    }

}
