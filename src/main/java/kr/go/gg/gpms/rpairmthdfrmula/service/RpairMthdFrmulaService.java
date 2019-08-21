package kr.go.gg.gpms.rpairmthdfrmula.service;

import java.util.List;

import kr.go.gg.gpms.rpairmthdfrmula.service.model.RpairMthdFrmulaDefaultVO;
import kr.go.gg.gpms.rpairmthdfrmula.service.model.RpairMthdFrmulaVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보수_공법_수식_관리
 * TN_RPAIR_MTHD_FRMULA
 *
 * @Class Name : RpairMthdFrmulaService.java
 * @Description : RpairMthdFrmula Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RpairMthdFrmulaService {

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;
	
	/**
	 * 보수_공법_수식_관리_이력(TH_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairMthdFrmulaHist(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 수정한다.
	 * @param rpairMthdFrmulaVO - 수정할 정보가 담긴 RpairMthdFrmulaVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 삭제한다.
	 * @param rpairMthdFrmulaVO - 삭제할 정보가 담긴 RpairMthdFrmulaVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 조회한 TN_RPAIR_MTHD_FRMULA
	 * @exception Exception
	 */
	RpairMthdFrmulaVO selectRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 목록
	 * @exception Exception
	 */
	List<RpairMthdFrmulaVO> selectRpairMthdFrmulaList(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 총 갯수
	 * @exception
	 */
	int selectRpairMthdFrmulaListTotalCount(RpairMthdFrmulaVO rpairMthdFrmulaVO);

}

