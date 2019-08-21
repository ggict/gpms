package kr.go.gg.gpms.predctfrmulaidx.service;

import java.util.List;

import kr.go.gg.gpms.predctfrmulaidx.service.model.PredctFrmulaIdxDefaultVO;
import kr.go.gg.gpms.predctfrmulaidx.service.model.PredctFrmulaIdxVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 예측_수식_지수
 * TN_PREDCT_FRMULA_IDX
 *
 * @Class Name : PredctFrmulaIdxService.java
 * @Description : PredctFrmulaIdx Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-08-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PredctFrmulaIdxService {

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 등록한다.
	 * @param predctFrmulaIdxVO - 등록할 정보가 담긴 PredctFrmulaIdxVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertPredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 수정한다.
	 * @param predctFrmulaIdxVO - 수정할 정보가 담긴 PredctFrmulaIdxVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 삭제한다.
	 * @param predctFrmulaIdxVO - 삭제할 정보가 담긴 PredctFrmulaIdxVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 조회한다.
	 * @param predctFrmulaIdxVO - 조회할 정보가 담긴 PredctFrmulaIdxVO
	 * @return 조회한 TN_PREDCT_FRMULA_IDX
	 * @exception Exception
	 */
	PredctFrmulaIdxVO selectPredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 predctFrmulaIdxVO
	 * @return TN_PREDCT_FRMULA_IDX 목록
	 * @exception Exception
	 */
	List<PredctFrmulaIdxVO> selectPredctFrmulaIdxList(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 predctFrmulaIdxVO
	 * @return TN_PREDCT_FRMULA_IDX 총 갯수
	 * @exception
	 */
	int selectPredctFrmulaIdxListTotalCount(PredctFrmulaIdxVO predctFrmulaIdxVO);

}

