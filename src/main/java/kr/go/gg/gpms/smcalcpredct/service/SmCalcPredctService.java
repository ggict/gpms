package kr.go.gg.gpms.smcalcpredct.service;

import java.util.List;

import kr.go.gg.gpms.smcalcpredct.service.model.SmCalcPredctDefaultVO;
import kr.go.gg.gpms.smcalcpredct.service.model.SmCalcPredctVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 집계_산정_예측
 * TN_SM_CALC_PREDCT
 *
 * @Class Name : SmCalcPredctService.java
 * @Description : SmCalcPredct Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SmCalcPredctService {

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 등록한다.
	 * @param smCalcPredctVO - 등록할 정보가 담긴 SmCalcPredctVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 수정한다.
	 * @param smCalcPredctVO - 수정할 정보가 담긴 SmCalcPredctVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 삭제한다.
	 * @param smCalcPredctVO - 삭제할 정보가 담긴 SmCalcPredctVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT)을 조회한다.
	 * @param smCalcPredctVO - 조회할 정보가 담긴 SmCalcPredctVO
	 * @return 조회한 TN_SM_CALC_PREDCT
	 * @exception Exception
	 */
	SmCalcPredctVO selectSmCalcPredct(SmCalcPredctVO smCalcPredctVO) throws Exception;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smCalcPredctVO
	 * @return TN_SM_CALC_PREDCT 목록
	 * @exception Exception
	 */
	List<SmCalcPredctVO> selectSmCalcPredctList(SmCalcPredctVO smCalcPredctVO) throws Exception;

	/**
	 * 집계_산정_예측(TN_SM_CALC_PREDCT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smCalcPredctVO
	 * @return TN_SM_CALC_PREDCT 총 갯수
	 * @exception
	 */
	int selectSmCalcPredctListTotalCount(SmCalcPredctVO smCalcPredctVO);

}

