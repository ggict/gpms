package kr.go.gg.gpms.pavfrmula.service;

import java.util.List;

import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaDefaultVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 포장_수식
 * TN_PAV_FRMULA
 *
 * @Class Name : PavFrmulaService.java
 * @Description : PavFrmula Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PavFrmulaService {

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 등록한다.
	 * @param pavFrmulaVO - 등록할 정보가 담긴 PavFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception;

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 수정한다.
	 * @param pavFrmulaVO - 수정할 정보가 담긴 PavFrmulaVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception;

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 삭제한다.
	 * @param pavFrmulaVO - 삭제할 정보가 담긴 PavFrmulaVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception;

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return 조회한 TN_PAV_FRMULA
	 * @exception Exception
	 */
	PavFrmulaVO selectPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception;

	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaVO
	 * @return TN_PAV_FRMULA 목록
	 * @exception Exception
	 */
	List<PavFrmulaVO> selectPavFrmulaList(PavFrmulaVO pavFrmulaVO) throws Exception;

	/**
	 * 포장_수식(TN_PAV_FRMULA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaVO
	 * @return TN_PAV_FRMULA 총 갯수
	 * @exception
	 */
	int selectPavFrmulaListTotalCount(PavFrmulaVO pavFrmulaVO);

}

