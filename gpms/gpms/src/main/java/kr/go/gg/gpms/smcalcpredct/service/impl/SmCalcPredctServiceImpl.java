package kr.go.gg.gpms.smcalcpredct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.smcalcpredct.service.SmCalcPredctService;
import kr.go.gg.gpms.smcalcpredct.service.model.SmCalcPredctVO;

/**
 * 집계_산정_예측
 *
 * @Class Name : SmCalcPredctServiceImpl.java
 * @Description : SmCalcPredct Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("smCalcPredctService")
public class SmCalcPredctServiceImpl extends AbstractServiceImpl implements SmCalcPredctService {

	@Resource(name = "smCalcPredctDAO")
	private SmCalcPredctDAO smCalcPredctDAO;

	//@Resource(name="SmCalcPredctIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 등록한다.
	 * @param smCalcPredctVO - 등록할 정보가 담긴 SmCalcPredctVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//smCalcPredctVO.setId(id);

		return smCalcPredctDAO.insertSmCalcPredct( smCalcPredctVO);
	}

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 수정한다.
	 * @param smCalcPredctVO - 수정할 정보가 담긴 SmCalcPredctVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception {
		return smCalcPredctDAO.updateSmCalcPredct( smCalcPredctVO);
	}

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 삭제한다.
	 * @param smCalcPredctVO - 삭제할 정보가 담긴 SmCalcPredctVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception {
		return smCalcPredctDAO.deleteSmCalcPredct( smCalcPredctVO);
	}

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 조회한다.
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return 조회한 TN_SM_CALC_PREDCT
	 * @exception Exception
	 */
	public SmCalcPredctVO selectSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception {
		return smCalcPredctDAO.selectSmCalcPredct( smCalcPredctVO);
	}

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smCalcPredctVO
	 * @return TN_SM_CALC_PREDCT 목록
	 * @exception Exception
	 */
	public List<SmCalcPredctVO> selectSmCalcPredctList(SmCalcPredctVO smCalcPredctVO) throws Exception {
		return smCalcPredctDAO.selectSmCalcPredctList( smCalcPredctVO);
	}

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smCalcPredctVO
	 * @return TN_SM_CALC_PREDCT 총 갯수
	 * @exception
	 */
	public int selectSmCalcPredctListTotalCount(SmCalcPredctVO smCalcPredctVO) {
		return smCalcPredctDAO.selectSmCalcPredctListTotalCount( smCalcPredctVO);
	}

}
