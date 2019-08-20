package kr.go.gg.gpms.cntrwk.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;

/**
 * 공사정보
 * TN_CNTRWK
 *
 * @Class Name : CntrwkService.java
 * @Description : Cntrwk Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CntrwkService {

	/**
	 * 공사정보(TN_CNTRWK)을 등록한다.
	 * @param cntrwkVO - 등록할 정보가 담긴 CntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCntrwk(CntrwkVO cntrwkVO) throws Exception;

	/**
	 * 공사정보(TN_CNTRWK)을 수정한다.
	 * @param cntrwkVO - 수정할 정보가 담긴 CntrwkVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCntrwk(CntrwkVO cntrwkVO) throws Exception;

	/**
	 * 공사정보(TN_CNTRWK)을 삭제한다.
	 * @param cntrwkVO - 삭제할 정보가 담긴 CntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrwk(CntrwkVO cntrwkVO) throws Exception;

	/**
	 * 공사정보(TN_CNTRWK)을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return 조회한 TN_CNTRWK
	 * @exception Exception
	 */
	CntrwkVO selectCntrwk(CntrwkVO cntrwkVO) throws Exception;

	/**
	 * 공사정보(TN_CNTRWK) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List<CntrwkVO> selectCntrwkList(CntrwkVO cntrwkVO) throws Exception;

	/**
	 * 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 총 갯수
	 * @exception
	 */
	int selectCntrwkListTotalCount(CntrwkVO cntrwkVO);
	
	/**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 cntrwkVO
     * @return TN_CNTRWK 목록
     * @exception Exception
     */
    List<CntrwkVO> selectCntrwkListByItgrtn(CntrwkVO cntrwkVO) throws Exception;

    /**
     * 통합정보조회 - 공사정보(TN_CNTRWK) 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 cntrwkVO
     * @return TN_CNTRWK 총 갯수
     * @exception
     */
    int selectCntrwkListByItgrtnCnt(CntrwkVO cntrwkVO);
	
	/**
	 * 공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List selectCntrwkListExcel(CntrwkVO cntrwkVO) throws Exception;
	
	/**
	 * 공사정보(TN_CNTRWK) 연도 목록을 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List<CntrwkVO> selectCntrwkYearList(CntrwkVO cntrwkVO) throws Exception;
	
	/**
	 * 정비공사내역 엑셀 종합 통계를 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	HashMap selectCntrwkHistTotalExcel(CntrwkVO cntrwkVO) throws Exception;
	
	/**
	 * 정비공사내역 엑셀 상세 목록을 조회한다
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	List selectCntrwkHistListExcel(CntrwkVO cntrwkVO) throws Exception;
	
    /**
	 * 공사정보(TN_CNTRWK)에 따른 셀ID(CELL_10)를 조회한다.
	 * @param cntrwkVO - 조회할 정보가 담긴 CntrwkVO
	 * @return "/api/cntrwk/selectCntrwkCellId
	 * @exception Exception
	 */
	List<CntrwkVO> selectCntrwkCellId(CntrwkVO cntrwkVO) throws Exception;
	
	/**
	 * 공사이력 조회
	 * @author    : JOY
	 * @date      : 2017. 11. 16.
	 * 
	 * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
	 * @exception : Exception
	 */
	List<CntrwkVO> selectCntrwkListBySctn(CntrwkVO cntrwkVO) throws Exception;
	
	/**
     * 공사이력 갯수 조회
     * @author    : JOY
     * @date      : 2017. 11. 16.
     * 
     * @param     : cntrwkVO - 조회할 정보가 담긴 cntrwkVO
     * @exception : Exception
     */
	int selectCntrwkListBySctnCnt(CntrwkVO cntrwkVO);

}

