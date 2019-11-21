


package kr.go.gg.gpms.cntrwk.service.impl;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 공사정보
 *
 * @Class Name : CntrwkDAO.java
 * @Description : Cntrwk DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cntrwkDAO")
public class CntrwkDAO extends BaseDAO {

	/**
	 * 공사정보(TN_CNTRWK)을 등록한다.
	 * @param cntrwkVO - 등록할 정보가 담긴 CntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return (String) insert("cntrwkDAO.insertCntrwk", cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 수정한다.
	 * @param cntrwkVO - 수정할 정보가 담긴 CntrwkVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return update("cntrwkDAO.updateCntrwk", cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 삭제한다.
	 * @param cntrwkVO - 삭제할 정보가 담긴 CntrwkVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return delete("cntrwkDAO.deleteCntrwk", cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return 조회한 TN_CNTRWK
	 * @exception Exception
	 */
	public CntrwkVO selectCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return (CntrwkVO) select("cntrwkDAO.selectCntrwk", cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkVO> selectCntrwkList(CntrwkVO cntrwkVO) throws Exception {
		return (List<CntrwkVO>)list("cntrwkDAO.selectCntrwkList", cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 총 갯수
	 * @exception
	 */
	public int selectCntrwkListTotalCount(CntrwkVO cntrwkVO) {
		return (Integer) select("cntrwkDAO.selectCntrwkListTotalCount", cntrwkVO);
	}
	
	/**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 목록을 조회한다.
     * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
     * @return TN_CNTRWK 목록
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<CntrwkVO> selectCntrwkListByItgrtn(CntrwkVO cntrwkVO) throws Exception {
        return (List<CntrwkVO>)list("cntrwkDAO.selectCntrwkListByItgrtn", cntrwkVO);
    }

    /**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
     * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
     * @return TN_CNTRWK 총 갯수
     * @exception
     */
    public int selectCntrwkListByItgrtnCnt(CntrwkVO cntrwkVO) {
        return (Integer) select("cntrwkDAO.selectCntrwkListByItgrtnCnt", cntrwkVO);
    }
	
	/**
	 * 공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkListExcel(CntrwkVO cntrwkVO) throws Exception {
		return (List)list("cntrwkDAO.selectCntrwkListExcel", cntrwkVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK) 연도 목록을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkVO> selectCntrwkYearList(CntrwkVO cntrwkVO) throws Exception {
		return (List<CntrwkVO>)list("cntrwkDAO.selectCntrwkYearList", cntrwkVO);
	}
	
	/**
	 * 정비공사내역 엑셀 종합 통계를 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public HashMap selectCntrwkHistTotalExcel(CntrwkVO cntrwkVO) throws Exception {
		return (HashMap)select("cntrwkDAO.selectCntrwkHistTotalExcel", cntrwkVO);
	}
	
	/**
	 * 정비공사내역 엑셀 상세 목록을 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkHistListExcel(CntrwkVO cntrwkVO) throws Exception {
		return (List)list("cntrwkDAO.selectCntrwkHistListExcel", cntrwkVO);
	}
	
    /**
	 * 공사정보(TN_CNTRWK)에 따른 셀ID(CELL_10)를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/api/cntrwk/selectCntrwkCellId
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkVO> selectCntrwkCellId(CntrwkVO cntrwkVO) throws Exception {
		return (List<CntrwkVO>)list("cntrwkDAO.selectCntrwkCellId", cntrwkVO);
	}
	
	/**
     * 공사이력 조회
     * @author    : JOY
     * @date      : 2017. 11. 16.
     * 
     * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CntrwkVO> selectCntrwkListBySctn(CntrwkVO cntrwkVO) throws Exception {
        return (List<CntrwkVO>)list("cntrwkDAO.selectCntrwkListBySctn", cntrwkVO);
    };
    
    /**
     * 공사이력 갯수 조회
     * @author    : JOY
     * @date      : 2017. 11. 16.
     * 
     * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public int selectCntrwkListBySctnCnt(CntrwkVO cntrwkVO) {
        return (Integer) select("cntrwkDAO.selectCntrwkListBySctnCnt", cntrwkVO);
    };

    /**
	 * 집계자료_최신_현황
	 * @date		: 2019.11.19
	 * 
	 * @param		: CNTRWK_SE
	 * @exception	: Exception
	 */
	public HashMap prc_SaveData(HashMap resultMap) {
		HashMap resultVO = (HashMap) select("cntrwkDAO.prc_SaveData",resultMap);
		System.out.println("resultVO : " + resultVO.toString());
		return resultVO;
	}
}
