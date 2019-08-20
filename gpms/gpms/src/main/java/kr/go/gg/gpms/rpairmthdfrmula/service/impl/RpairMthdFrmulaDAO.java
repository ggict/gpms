


package kr.go.gg.gpms.rpairmthdfrmula.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthdfrmula.service.model.RpairMthdFrmulaVO;

/**
 * 보수_공법_수식_관리
 *
 * @Class Name : RpairMthdFrmulaDAO.java
 * @Description : RpairMthdFrmula DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairMthdFrmulaDAO")
public class RpairMthdFrmulaDAO extends BaseDAO {

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return (String) insert("rpairMthdFrmulaDAO.insertRpairMthdFrmula", rpairMthdFrmulaVO);
	}
	
	/**
	 * 보수_공법_수식_관리_이력(TH_RPAIR_MTHD_FRMULA)을 등록한다.
	 * @param rpairMthdFrmulaVO - 등록할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdFrmulaHist(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return (String) insert("rpairMthdFrmulaDAO.insertRpairMthdFrmulaHist", rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 수정한다.
	 * @param rpairMthdFrmulaVO - 수정할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return update("rpairMthdFrmulaDAO.updateRpairMthdFrmula", rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 삭제한다.
	 * @param rpairMthdFrmulaVO - 삭제할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return delete("rpairMthdFrmulaDAO.deleteRpairMthdFrmula", rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA)을 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return 조회한 TN_RPAIR_MTHD_FRMULA
	 * @exception Exception
	 */
	public RpairMthdFrmulaVO selectRpairMthdFrmula(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return (RpairMthdFrmulaVO) select("rpairMthdFrmulaDAO.selectRpairMthdFrmula", rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 목록을 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RpairMthdFrmulaVO> selectRpairMthdFrmulaList(RpairMthdFrmulaVO rpairMthdFrmulaVO) throws Exception {
		return (List<RpairMthdFrmulaVO>)list("rpairMthdFrmulaDAO.selectRpairMthdFrmulaList", rpairMthdFrmulaVO);
	}

	/**
	 * 보수_공법_수식_관리(TN_RPAIR_MTHD_FRMULA) 총 갯수를 조회한다.
	 * @param rpairMthdFrmulaVO - 조회할 정보가 담긴 RpairMthdFrmulaVO
	 * @return TN_RPAIR_MTHD_FRMULA 총 갯수
	 * @exception
	 */
	public int selectRpairMthdFrmulaListTotalCount(RpairMthdFrmulaVO rpairMthdFrmulaVO) {
		return (Integer) select("rpairMthdFrmulaDAO.selectRpairMthdFrmulaListTotalCount", rpairMthdFrmulaVO);
	}

}
