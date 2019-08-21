package kr.go.gg.gpms.rpairtrgetslctn.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairtrgetslctn.service.RpairTrgetSlctnService;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

/**
 * 보수_대상_선정
 *
 * @Class Name : RpairTrgetSlctnServiceImpl.java
 * @Description : RpairTrgetSlctn Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("rpairTrgetSlctnService")
public class RpairTrgetSlctnServiceImpl extends AbstractServiceImpl implements RpairTrgetSlctnService {

	@Resource(name = "rpairTrgetSlctnDAO")
	private RpairTrgetSlctnDAO rpairTrgetSlctnDAO;

	//@Resource(name="RpairTrgetSlctnIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 등록한다.
	 * @param rpairTrgetSlctnVO - 등록할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairTrgetSlctnVO.setId(id);

		return rpairTrgetSlctnDAO.insertRpairTrgetSlctn( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.updateRpairTrgetSlctn( rpairTrgetSlctnVO);
	}
	
	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRangeSelection(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return rpairTrgetSlctnDAO.updateRangeSelection( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 삭제한다.
	 * @param rpairTrgetSlctnVO - 삭제할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.deleteRpairTrgetSlctn( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 조회한 TN_RPAIR_TRGET_SLCTN
	 * @exception Exception
	 */
	public RpairTrgetSlctnVO selectRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		RpairTrgetSlctnVO resultVO = rpairTrgetSlctnDAO.selectRpairTrgetSlctn( rpairTrgetSlctnVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 목록
	 * @exception Exception
	 */
	public List<RpairTrgetSlctnVO> selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.selectRpairTrgetSlctnList( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 총 갯수
	 * @exception
	 */
	public int selectRpairTrgetSlctnListTotalCount(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return rpairTrgetSlctnDAO.selectRpairTrgetSlctnListTotalCount( rpairTrgetSlctnVO);

	}
	
	/**
	 * 보수대상선정 항목을 집계한다.
	 */
	public HashMap procClacprcRepairTargets(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception{
		return rpairTrgetSlctnDAO.procClacprcRepairTargets(rpairTrgetSlctnVO);
	}
	/**
	 * 보수대상선정 범위내 선정을 집계한다.
	 */
	@Override
	public HashMap procRepairTargetRangeSelect(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetRangeSelect(rpairTrgetSlctnVO);
	}
	
	/**
	 * 연속구간 연결 및 보수공법 재선정
	 */
	@Override
	public HashMap procRepairTargetRangeString(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetRangeString(rpairTrgetSlctnVO);
	}

	/**
	 * 예산범위내 선정
	 */
	@Override
	public HashMap procRepairTargetBudgetRate(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetBudgetRate(rpairTrgetSlctnVO);
	}

	@Override
	public HashMap procRepairTarget(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		rpairTrgetSlctnDAO.updateRangeSelection( rpairTrgetSlctnVO);
		HashMap resultVO =rpairTrgetSlctnDAO.procRepairTargetRangeSelect(rpairTrgetSlctnVO);
		resultVO =rpairTrgetSlctnDAO.procRepairTargetRangeString(rpairTrgetSlctnVO);
		resultVO =rpairTrgetSlctnDAO.procRepairTargetBudgetRate(rpairTrgetSlctnVO);
		return resultVO;
	}

	@Override
	public HashMap procRepairTargetComplete(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		HashMap resultVO =rpairTrgetSlctnDAO.procRepairTargetComplete(rpairTrgetSlctnVO);
		return resultVO;
	}

	
	

}
