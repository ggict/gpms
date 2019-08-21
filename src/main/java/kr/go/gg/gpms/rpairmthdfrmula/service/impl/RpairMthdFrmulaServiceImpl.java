package kr.go.gg.gpms.rpairmthdfrmula.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthdfrmula.service.RpairMthdFrmulaService;
import kr.go.gg.gpms.rpairmthdfrmula.service.model.RpairMthdFrmulaVO;

/**
 * 보수_공법_수식_관리
 *
 * @Class Name : RpairMthdFrmulaServiceImpl.java
 * @Description : RpairMthdFrmula Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("rpairMthdFrmulaService")
public class RpairMthdFrmulaServiceImpl extends AbstractServiceImpl implements RpairMthdFrmulaService {

	@Resource(name = "rpairMthdFrmulaDAO")
	private RpairMthdFrmulaDAO rpairMthdFrmulaDAO;

	//@Resource(name="RpairMthdFrmulaIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairMthdFrmulaVO.setId(id);

		return rpairMthdFrmulaDAO.insertRpairMthdFrmula( rpairMthdFrmulaVO);
	}
	
	/**
	 * 보수_공법_수식_관리_이력(TH_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdFrmulaHist(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		
		return rpairMthdFrmulaDAO.insertRpairMthdFrmulaHist( rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 수정한다.
	 * @param rpairMthdFrmulaVO - 수정할 정보가 담긴 RpairMthdFrmulaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return rpairMthdFrmulaDAO.updateRpairMthdFrmula( rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 삭제한다.
	 * @param rpairMthdFrmulaVO - 삭제할 정보가 담긴 RpairMthdFrmulaVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return rpairMthdFrmulaDAO.deleteRpairMthdFrmula( rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 조회한 TN_RPAIR_MTHD_FRMULA
	 * @exception Exception
	 */
	public RpairMthdFrmulaVO selectRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return rpairMthdFrmulaDAO.selectRpairMthdFrmula( rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 목록
	 * @exception Exception
	 */
	public List<RpairMthdFrmulaVO> selectRpairMthdFrmulaList(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return rpairMthdFrmulaDAO.selectRpairMthdFrmulaList( rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 총 갯수
	 * @exception
	 */
	public int selectRpairMthdFrmulaListTotalCount(RpairMthdFrmulaVO rpairMthdFrmulaVO) {
		return rpairMthdFrmulaDAO.selectRpairMthdFrmulaListTotalCount( rpairMthdFrmulaVO);
	}

}
