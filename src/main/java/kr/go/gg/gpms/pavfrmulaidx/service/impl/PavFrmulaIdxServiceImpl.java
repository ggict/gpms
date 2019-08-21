package kr.go.gg.gpms.pavfrmulaidx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavfrmulaidx.service.PavFrmulaIdxService;
import kr.go.gg.gpms.pavfrmulaidx.service.model.PavFrmulaIdxVO;

/**
 * 포장_수식_지수
 *
 * @Class Name : PavFrmulaIdxServiceImpl.java
 * @Description : PavFrmulaIdx Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pavFrmulaIdxService")
public class PavFrmulaIdxServiceImpl extends AbstractServiceImpl implements PavFrmulaIdxService {

	@Resource(name = "pavFrmulaIdxDAO")
	private PavFrmulaIdxDAO pavFrmulaIdxDAO;

	//@Resource(name="PavFrmulaIdxIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 등록한다.
	 * @param pavFrmulaIdxVO - 등록할 정보가 담긴 PavFrmulaIdxVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertPavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//pavFrmulaIdxVO.setId(id);

		return pavFrmulaIdxDAO.insertPavFrmulaIdx( pavFrmulaIdxVO);
	}

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 수정한다.
	 * @param pavFrmulaIdxVO - 수정할 정보가 담긴 PavFrmulaIdxVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		return pavFrmulaIdxDAO.updatePavFrmulaIdx( pavFrmulaIdxVO);
	}

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 삭제한다.
	 * @param pavFrmulaIdxVO - 삭제할 정보가 담긴 PavFrmulaIdxVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		return pavFrmulaIdxDAO.deletePavFrmulaIdx( pavFrmulaIdxVO);
	}

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX)을 조회한다.
	 * @param pavFrmulaIdxVO - 조회할 정보가 담긴 PavFrmulaIdxVO
	 * @return 조회한 TN_PAV_FRMULA_IDX
	 * @exception Exception
	 */
	public PavFrmulaIdxVO selectPavFrmulaIdx(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		PavFrmulaIdxVO resultVO = pavFrmulaIdxDAO.selectPavFrmulaIdx( pavFrmulaIdxVO);
		 
		return resultVO;
	}

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaIdxVO
	 * @return TN_PAV_FRMULA_IDX 목록
	 * @exception Exception
	 */
	public List<PavFrmulaIdxVO> selectPavFrmulaIdxList(PavFrmulaIdxVO pavFrmulaIdxVO) throws Exception {
		return pavFrmulaIdxDAO.selectPavFrmulaIdxList( pavFrmulaIdxVO);
	}

	/**
	 * 포장_수식_지수(TN_PAV_FRMULA_IDX) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaIdxVO
	 * @return TN_PAV_FRMULA_IDX 총 갯수
	 * @exception
	 */
	public int selectPavFrmulaIdxListTotalCount(PavFrmulaIdxVO pavFrmulaIdxVO) {
		return pavFrmulaIdxDAO.selectPavFrmulaIdxListTotalCount( pavFrmulaIdxVO);
	}

}
