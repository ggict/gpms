


package kr.go.gg.gpms.cntrwksctnhist.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cntrwksctnhist.service.model.CntrwkSctnHistVO;

/**
 * 공사_구간_이력
 *
 * @Class Name : CntrwkSctnHistDAO.java
 * @Description : CntrwkSctnHist DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cntrwkSctnHistDAO")
public class CntrwkSctnHistDAO extends BaseDAO {

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 등록한다.
	 * @param cntrwkSctnHistVO - 등록할 정보가 담긴 CntrwkSctnHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return (Integer) insert("cntrwkSctnHistDAO.insertCntrwkSctnHist", cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 수정한다.
	 * @param cntrwkSctnHistVO - 수정할 정보가 담긴 CntrwkSctnHistVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return update("cntrwkSctnHistDAO.updateCntrwkSctnHist", cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 삭제한다.
	 * @param cntrwkSctnHistVO - 삭제할 정보가 담긴 CntrwkSctnHistVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return delete("cntrwkSctnHistDAO.deleteCntrwkSctnHist", cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return 조회한 TH_CNTRWK_SCTN_HIST
	 * @exception Exception
	 */
	public CntrwkSctnHistVO selectCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return (CntrwkSctnHistVO) select("cntrwkSctnHistDAO.selectCntrwkSctnHist", cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkSctnHistVO> selectCntrwkSctnHistList(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return (List<CntrwkSctnHistVO>)list("cntrwkSctnHistDAO.selectCntrwkSctnHistList", cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 총 갯수를 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 총 갯수
	 * @exception
	 */
	public int selectCntrwkSctnHistListTotalCount(CntrwkSctnHistVO cntrwkSctnHistVO) {
		return (Integer) select("cntrwkSctnHistDAO.selectCntrwkSctnHistListTotalCount", cntrwkSctnHistVO);
	}

}
