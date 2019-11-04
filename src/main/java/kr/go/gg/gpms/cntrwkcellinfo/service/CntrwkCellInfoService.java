package kr.go.gg.gpms.cntrwkcellinfo.service;

import java.util.List;

import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoDefaultVO;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공사_셀_정보
 * TN_CNTRWK_CELL_INFO
 *
 * @Class Name : CntrwkCellInfoService.java
 * @Description : CntrwkCellInfo Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-31
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CntrwkCellInfoService {

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 등록한다.
	 * @param cntrwkCellInfoVO - 등록할 정보가 담긴 CntrwkCellInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 수정한다.
	 * @param cntrwkCellInfoVO - 수정할 정보가 담긴 CntrwkCellInfoVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 삭제한다.
	 * @param cntrwkCellInfoVO - 삭제할 정보가 담긴 CntrwkCellInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 조회한다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return 조회한 TN_CNTRWK_CELL_INFO
	 * @exception Exception
	 */
	CntrwkCellInfoVO selectCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	List<CntrwkCellInfoVO> selectCntrwkCellInfoList(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 총 갯수
	 * @exception
	 */
	int selectCntrwkCellInfoListTotalCount(CntrwkCellInfoVO cntrwkCellInfoVO);
	
	/**
	 * 공사_셀별 직전 포장년도를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	List<CntrwkCellInfoVO> selectPavYearListByCellId(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;
	
	/**
	 * 공사_셀별 직전 포장년도 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 총 갯수
	 * @exception
	 */
	int selectPavYearListByCellIdTotalCount(CntrwkCellInfoVO cntrwkCellInfoVO);
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 리스트를 가져온다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return CELL_10 노선정보 리스트
	 * @exception Exception
	 */
	List<Cell10VO> selectRouteInfoListByCellID(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)의 이전 포장상태를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	List<Cell10VO> selectCntrwkBeforeCellInfoList(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception;
}

