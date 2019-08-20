


package kr.go.gg.gpms.pavfrmula.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;

/**
 * 포장_수식
 *
 * @Class Name : PavFrmulaDAO.java
 * @Description : PavFrmula DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("pavFrmulaDAO")
public class PavFrmulaDAO extends BaseDAO {

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 등록한다.
	 * @param pavFrmulaVO - 등록할 정보가 담긴 PavFrmulaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return (Integer) insert("pavFrmulaDAO.insertPavFrmula", pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 수정한다.
	 * @param pavFrmulaVO - 수정할 정보가 담긴 PavFrmulaVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updatePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return update("pavFrmulaDAO.updatePavFrmula", pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 삭제한다.
	 * @param pavFrmulaVO - 삭제할 정보가 담긴 PavFrmulaVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deletePavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return delete("pavFrmulaDAO.deletePavFrmula", pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA)을 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return 조회한 TN_PAV_FRMULA
	 * @exception Exception
	 */
	public PavFrmulaVO selectPavFrmula(PavFrmulaVO pavFrmulaVO) throws Exception {
		return (PavFrmulaVO) select("pavFrmulaDAO.selectPavFrmula", pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA) 목록을 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return TN_PAV_FRMULA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PavFrmulaVO> selectPavFrmulaList(PavFrmulaVO pavFrmulaVO) throws Exception {
		return (List<PavFrmulaVO>)list("pavFrmulaDAO.selectPavFrmulaList", pavFrmulaVO);
	}

	/**
	 * 포장_수식(TN_PAV_FRMULA) 총 갯수를 조회한다.
	 * @param pavFrmulaVO - 조회할 정보가 담긴 PavFrmulaVO
	 * @return TN_PAV_FRMULA 총 갯수
	 * @exception
	 */
	public int selectPavFrmulaListTotalCount(PavFrmulaVO pavFrmulaVO) {
		return (Integer) select("pavFrmulaDAO.selectPavFrmulaListTotalCount", pavFrmulaVO);
	}

}
