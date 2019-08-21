package kr.go.gg.gpms.predctfrmulaidx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.predctfrmulaidx.service.PredctFrmulaIdxService;
import kr.go.gg.gpms.predctfrmulaidx.service.model.PredctFrmulaIdxVO;

/**
 * 예측_수식_지수
 *
 * @Class Name : PredctFrmulaIdxServiceImpl.java
 * @Description : PredctFrmulaIdx Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-08-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("predctFrmulaIdxService")
public class PredctFrmulaIdxServiceImpl extends AbstractServiceImpl implements PredctFrmulaIdxService {

	@Resource(name = "predctFrmulaIdxDAO")
	private PredctFrmulaIdxDAO predctFrmulaIdxDAO;

	//@Resource(name="PredctFrmulaIdxIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 등록한다.
	 * @param predctFrmulaIdxVO - 등록할 정보가 담긴 PredctFrmulaIdxVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//predctFrmulaIdxVO.setId(id);

		return predctFrmulaIdxDAO.insertPredctFrmulaIdx( predctFrmulaIdxVO);
	}

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 수정한다.
	 * @param predctFrmulaIdxVO - 수정할 정보가 담긴 PredctFrmulaIdxVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		return predctFrmulaIdxDAO.updatePredctFrmulaIdx( predctFrmulaIdxVO);
	}

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 삭제한다.
	 * @param predctFrmulaIdxVO - 삭제할 정보가 담긴 PredctFrmulaIdxVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		return predctFrmulaIdxDAO.deletePredctFrmulaIdx( predctFrmulaIdxVO);
	}

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX)을 조회한다.
	 * @param predctFrmulaIdxVO - 조회할 정보가 담긴 PredctFrmulaIdxVO
	 * @return 조회한 TN_PREDCT_FRMULA_IDX
	 * @exception Exception
	 */
	public PredctFrmulaIdxVO selectPredctFrmulaIdx(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		return predctFrmulaIdxDAO.selectPredctFrmulaIdx( predctFrmulaIdxVO);
	}

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 predctFrmulaIdxVO
	 * @return TN_PREDCT_FRMULA_IDX 목록
	 * @exception Exception
	 */
	public List<PredctFrmulaIdxVO> selectPredctFrmulaIdxList(PredctFrmulaIdxVO predctFrmulaIdxVO) throws Exception {
		return predctFrmulaIdxDAO.selectPredctFrmulaIdxList( predctFrmulaIdxVO);
	}

	/**
	 * 예측_수식_지수(TN_PREDCT_FRMULA_IDX) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 predctFrmulaIdxVO
	 * @return TN_PREDCT_FRMULA_IDX 총 갯수
	 * @exception
	 */
	public int selectPredctFrmulaIdxListTotalCount(PredctFrmulaIdxVO predctFrmulaIdxVO) {
		return predctFrmulaIdxDAO.selectPredctFrmulaIdxListTotalCount( predctFrmulaIdxVO);
	}

}
