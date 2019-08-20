package kr.go.gg.gpms.pavfrmulaidx.service;

import java.util.List;

import kr.go.gg.gpms.pavfrmulaidx.service.model.PavFrmulaIdxDefaultVO;
import kr.go.gg.gpms.pavfrmulaidx.service.model.PavFrmulaIdxVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 포장_수식_지수
 * TN_PAV_FRMULA_IDX
 *
 * @Class Name : PavFrmulaIdxService.java
 * @Description : PavFrmulaIdx Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PavFrmulaIdxService {

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 등록한다.
	 * @param pavFrmulaIdxVO - 등록할 정보가 담긴 PavFrmulaIdxVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertPavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 수정한다.
	 * @param pavFrmulaIdxVO - 수정할 정보가 담긴 PavFrmulaIdxVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 삭제한다.
	 * @param pavFrmulaIdxVO - 삭제할 정보가 담긴 PavFrmulaIdxVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 조회한다.
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return 조회한 TN_PAV_FRMULA_IDX
	 * @exception Exception
	 */
	PavFrmulaIdxVO selectPavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaIdxVO
	 * @return TN_PAV_FRMULA_IDX 목록
	 * @exception Exception
	 */
	List<PavFrmulaIdxVO> selectPavFrmulaIdxList(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaIdxVO
	 * @return TN_PAV_FRMULA_IDX 총 갯수
	 * @exception
	 */
	int selectPavFrmulaIdxListTotalCount(PavFrmulaIdxVO pavFrmulaIdxVO);

}

