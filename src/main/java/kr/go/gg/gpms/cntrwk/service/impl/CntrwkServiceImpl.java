package kr.go.gg.gpms.cntrwk.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.cntrwk.service.CntrwkService;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 공사정보
 *
 * @Class Name : CntrwkServiceImpl.java
 * @Description : Cntrwk Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cntrwkService")
public class CntrwkServiceImpl extends AbstractServiceImpl implements CntrwkService {

	@Resource(name = "cntrwkDAO")
	private CntrwkDAO cntrwkDAO;

	//@Resource(name="CntrwkIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사정보(TN_CNTRWK)을 등록한다.
	 * @param cntrwkVO - 등록할 정보가 담긴 CntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwk(CntrwkVO cntrwkVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrwkVO.setId(id);

		return cntrwkDAO.insertCntrwk( cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 수정한다.
	 * @param cntrwkVO - 수정할 정보가 담긴 CntrwkVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.updateCntrwk( cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 삭제한다.
	 * @param cntrwkVO - 삭제할 정보가 담긴 CntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrwk(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.deleteCntrwk( cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK)을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return 조회한 TN_CNTRWK
	 * @exception Exception
	 */
	public CntrwkVO selectCntrwk(CntrwkVO cntrwkVO) throws Exception {
		CntrwkVO resultVO = cntrwkDAO.selectCntrwk( cntrwkVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List<CntrwkVO> selectCntrwkList(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkList( cntrwkVO);
	}

	/**
	 * 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 총 갯수
	 * @exception
	 */
	public int selectCntrwkListTotalCount(CntrwkVO cntrwkVO) {
		return cntrwkDAO.selectCntrwkListTotalCount( cntrwkVO);
	}
	
	/**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 cntrwkVO
     * @return TN_CNTRWK 목록
     * @exception Exception
     */
    public List<CntrwkVO> selectCntrwkListByItgrtn(CntrwkVO cntrwkVO) throws Exception{
        return cntrwkDAO.selectCntrwkListByItgrtn( cntrwkVO);
    }

    /**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 cntrwkVO
     * @return TN_CNTRWK 총 갯수
     * @exception
     */
    public int selectCntrwkListByItgrtnCnt(CntrwkVO cntrwkVO){
        return cntrwkDAO.selectCntrwkListByItgrtnCnt( cntrwkVO);
    }
    
	/**
	 * 공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkListExcel(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkListExcel( cntrwkVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK) 연도 목록을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CntrwkVO> selectCntrwkYearList(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkYearList(cntrwkVO);
	}
	
	
	/**
	 * 정비공사내역 엑셀 종합 통계를 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public HashMap selectCntrwkHistTotalExcel(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkHistTotalExcel(cntrwkVO);
	}
	
	/**
	 * 정비공사내역 엑셀 상세 목록을 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkHistListExcel(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkHistListExcel(cntrwkVO);
	}
	
    /**
	 * 공사정보(TN_CNTRWK)에 따른 셀ID(CELL_10)를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/api/cntrwk/selectCntrwkCellId
	 * @exception Exception
	 */
	public List<CntrwkVO> selectCntrwkCellId(CntrwkVO cntrwkVO) throws Exception {
		return cntrwkDAO.selectCntrwkCellId( cntrwkVO);
	}
	
	/**
     * 공사이력 조회
     * @author    : JOY
     * @date      : 2017. 11. 16.
     * 
     * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
     * @exception : Exception
     */
	public List<CntrwkVO> selectCntrwkListBySctn(CntrwkVO cntrwkVO) throws Exception {
	    return cntrwkDAO.selectCntrwkListBySctn(cntrwkVO);
	};
    
    /**
     * 공사이력 갯수 조회
     * @author    : JOY
     * @date      : 2017. 11. 16.
     * 
     * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
     * @exception : Exception
     */
	public int selectCntrwkListBySctnCnt(CntrwkVO cntrwkVO) {
	    return cntrwkDAO.selectCntrwkListBySctnCnt(cntrwkVO);
	};

	/**
	 * 집계자료_최신_현황
	 * @date		: 2019.11.19
	 * 
	 * @param		: CNTRWK_SE
	 * @exception	: Exception
	 */
	public HashMap prc_SaveData(HashMap resultMap) {
		return cntrwkDAO.prc_SaveData(resultMap);		
	};
}
