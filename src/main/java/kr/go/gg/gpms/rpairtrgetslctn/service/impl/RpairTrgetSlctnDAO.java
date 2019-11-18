


package kr.go.gg.gpms.rpairtrgetslctn.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

/**
 * 보수_대상_선정
 *
 * @Class Name : RpairTrgetSlctnDAO.java
 * @Description : RpairTrgetSlctn DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairTrgetSlctnDAO")
public class RpairTrgetSlctnDAO extends BaseDAO {

    /**
     * 보수대상선정이력 목록을 조회
     */
    @SuppressWarnings("unchecked")
    public List<RpairTrgetSlctnVO> selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        return (List<RpairTrgetSlctnVO>)list("rpairTrgetSlctnDAO.selectRpairTrgetSlctnList", rpairTrgetSlctnVO);
    }

    /**
     * 보수대상선정시작 처리(보수_대상_선정 삭제)
     */
    public int deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        return delete("rpairTrgetSlctnDAO.deleteRpairTrgetSlctn", rpairTrgetSlctnVO);
    }

    /**
     * 보수대상선정시작 처리(보수_대상_선정 등록)
     */
    public String insertRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        return (String) insert("rpairTrgetSlctnDAO.insertRpairTrgetSlctn", rpairTrgetSlctnVO);
    }

    /**
     * 보수대상선정 노선 목록을 조회
     */
    @SuppressWarnings("unchecked")
    public List<RpairTrgetSlctnVO> selectRpairTrgetSlctnRouteCodeList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        return (List<RpairTrgetSlctnVO>)list("rpairTrgetSlctnDAO.selectRpairTrgetSlctnRouteCodeList", rpairTrgetSlctnVO);
    }

    /**
     * 보수대상선정 범위내 선정을 집계한다.
     * @param rpairTrgetSlctnVO
     * @return
     */
    public HashMap procRepairTargetRangeSelect(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
        logger.info("procRepairTargetRangeSelectVO: " + rpairTrgetSlctnVO.toString());
        HashMap param = new HashMap();
        param.put("P_USER_NO", rpairTrgetSlctnVO.getCRTR_NO());
        param.put("P_TRGET_SLCTN_NO", rpairTrgetSlctnVO.getTRGET_SLCTN_NO());
        param.put("P_ROUTE_CODE", rpairTrgetSlctnVO.getROUTE_CODE());
        param.put("P_START_END_CODE", rpairTrgetSlctnVO.getSTART_END_CODE());
        param.put("P_MODE", "NONE");

        HashMap resultVO = (HashMap) select("rpairTrgetSlctnDAO.PRC_REPAIR_TARGET_RANGE_SELECT", param);
        return resultVO;
    }

    /**
     * 연속구간 연결 및 보수공법 재선정
     * @param rpairTrgetSlctnVO
     * @return
     */
    public HashMap procRepairTargetRangeString(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
        logger.info("procRepairTargetRangeStringVO: " + rpairTrgetSlctnVO.toString());
        HashMap param = new HashMap();
        param.put("P_USER_NO", rpairTrgetSlctnVO.getCRTR_NO());
        param.put("P_TRGET_SLCTN_NO", rpairTrgetSlctnVO.getTRGET_SLCTN_NO());
        param.put("P_ANALS_UNIT_CODE", rpairTrgetSlctnVO.getANALS_UNIT_CODE());
        param.put("P_MODE", "NONE");

        HashMap resultVO = (HashMap) select("rpairTrgetSlctnDAO.PRC_REPAIR_TARGET_RANGE_STRING", param);
        return resultVO;
    }








	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return update("rpairTrgetSlctnDAO.updateRpairTrgetSlctn", rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 조회한 TN_RPAIR_TRGET_SLCTN
	 * @exception Exception
	 */
	public RpairTrgetSlctnVO selectRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return (RpairTrgetSlctnVO) select("rpairTrgetSlctnDAO.selectRpairTrgetSlctn", rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 총 갯수를 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 총 갯수
	 * @exception
	 */
	public int selectRpairTrgetSlctnListTotalCount(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return (Integer) select("rpairTrgetSlctnDAO.selectRpairTrgetSlctnListTotalCount", rpairTrgetSlctnVO);
	}

	/**
	 * 보수대상선정 항목을 집계한다.
	 */
	public HashMap procClacprcRepairTargets(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		logger.info("procClacprcRepairTargetsVO: " + rpairTrgetSlctnVO.toString());
		HashMap param = new HashMap();
		param.put("p_USER_NO", rpairTrgetSlctnVO.getCRTR_NO());
		param.put("p_TRGET_SLCTN_NO", rpairTrgetSlctnVO.getTRGET_SLCTN_NO());
		param.put("p_SLCTN_BUDGET",rpairTrgetSlctnVO.getSLCTN_BUDGET());
		param.put("p_MMNF_RATE", Float.parseFloat( rpairTrgetSlctnVO.getMMNF_RATE()));
		param.put("p_ST_RATE",  Float.parseFloat(rpairTrgetSlctnVO.getST_RATE()));
		param.put("p_PM_RATE",  Float.parseFloat(rpairTrgetSlctnVO.getPM_RATE()));
    	param.put("p_MODE", "NONE");

    	HashMap resultVO = (HashMap) select("rpairTrgetSlctnDAO.PRC_REPAIR_TARGETS", param);
    	logger.info("procClacprcRepairTargetsResultVO: " + resultVO.toString());
    	return resultVO;
	}

	public int updateRangeSelection(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return update("rpairTrgetSlctnDAO.updateRangeSelection", rpairTrgetSlctnVO);
	}




	/**
	 * 예산범위내 선정
	 * @param rpairTrgetSlctnVO
	 * @return
	 */
	public HashMap procRepairTargetBudgetRate(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		logger.info("procRepairTargetBudgetRateVO: " + rpairTrgetSlctnVO.toString());
		HashMap param = new HashMap();
		param.put("p_USER_NO", rpairTrgetSlctnVO.getCRTR_NO());
		param.put("p_TRGET_SLCTN_NO", rpairTrgetSlctnVO.getTRGET_SLCTN_NO());
		param.put("p_SLCTN_BUDGET", rpairTrgetSlctnVO.getSLCTN_BUDGET());
		param.put("p_DEPT_CODE", rpairTrgetSlctnVO.getDEPT_CODE());
		param.put("p_DECSN_MTHD_1_RATE", rpairTrgetSlctnVO.getDECSN_MTHD_1_RATE());
		param.put("p_DECSN_MTHD_2_RATE",  rpairTrgetSlctnVO.getDECSN_MTHD_2_RATE());
		param.put("p_DECSN_MTHD_3_RATE",  rpairTrgetSlctnVO.getDECSN_MTHD_3_RATE());
		param.put("p_DECSN_MTHD_4_RATE",  rpairTrgetSlctnVO.getDECSN_MTHD_4_RATE());
		param.put("p_DECSN_MTHD_5_RATE",  rpairTrgetSlctnVO.getDECSN_MTHD_5_RATE());
    	param.put("p_MODE", "NONE");

    	HashMap resultVO = (HashMap) select("rpairTrgetSlctnDAO.PRC_REPAIR_TARGET_BUDGET_RATE", param);
    	logger.info("procRepairTargetBudgetRateResultVO: " + resultVO.toString());
    	return resultVO;
	}

	public HashMap procRepairTargetComplete(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		logger.info("procRepairTargetCompleteVO: " + rpairTrgetSlctnVO.toString());
		HashMap param = new HashMap();
		param.put("p_USER_NO", rpairTrgetSlctnVO.getCRTR_NO());
		param.put("p_TRGET_SLCTN_NO", rpairTrgetSlctnVO.getTRGET_SLCTN_NO());
		param.put("p_MODE", "NONE");

    	HashMap resultVO = (HashMap) select("rpairTrgetSlctnDAO.PRC_REPAIR_TARGET_COMPLETE", param);
    	logger.info("procRepairTargetCompleteResultVO: " + resultVO.toString());
    	return resultVO;
	}



}
