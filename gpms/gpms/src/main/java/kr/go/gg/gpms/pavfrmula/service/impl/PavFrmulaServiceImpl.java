package kr.go.gg.gpms.pavfrmula.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavfrmula.service.PavFrmulaService;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;

/**
 * 포장_수식
 *
 * @Class Name : PavFrmulaServiceImpl.java
 * @Description : PavFrmula Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pavFrmulaService")
public class PavFrmulaServiceImpl extends AbstractServiceImpl implements PavFrmulaService {

	@Resource(name = "pavFrmulaDAO")
	private PavFrmulaDAO pavFrmulaDAO;

	//@Resource(name="PavFrmulaIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 등록한다.
	 * @param pavFrmulaVO - 등록할 정보가 담긴 PavFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//pavFrmulaVO.setId(id);

		return pavFrmulaDAO.insertPavFrmula( pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 수정한다.
	 * @param pavFrmulaVO - 수정할 정보가 담긴 PavFrmulaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return pavFrmulaDAO.updatePavFrmula( pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 삭제한다.
	 * @param pavFrmulaVO - 삭제할 정보가 담긴 PavFrmulaVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return pavFrmulaDAO.deletePavFrmula( pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return 조회한 TN_PAV_FRMULA
	 * @exception Exception
	 */
	public PavFrmulaVO selectPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		PavFrmulaVO resultVO = pavFrmulaDAO.selectPavFrmula( pavFrmulaVO);
		
		return resultVO;
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaVO
	 * @return TN_PAV_FRMULA 목록
	 * @exception Exception
	 */
	public List<PavFrmulaVO> selectPavFrmulaList(PavFrmulaVO pavFrmulaVO) throws Exception {
		return pavFrmulaDAO.selectPavFrmulaList( pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavFrmulaVO
	 * @return TN_PAV_FRMULA 총 갯수
	 * @exception
	 */
	public int selectPavFrmulaListTotalCount(PavFrmulaVO pavFrmulaVO) {
		return pavFrmulaDAO.selectPavFrmulaListTotalCount( pavFrmulaVO);
	}

}
