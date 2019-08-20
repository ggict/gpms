package kr.go.gg.gpms.cntrwksctnhist.service;

import java.util.List;

import kr.go.gg.gpms.cntrwksctnhist.service.model.CntrwkSctnHistDefaultVO;
import kr.go.gg.gpms.cntrwksctnhist.service.model.CntrwkSctnHistVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공사_구간_이력
 * TH_CNTRWK_SCTN_HIST
 *
 * @Class Name : CntrwkSctnHistService.java
 * @Description : CntrwkSctnHist Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CntrwkSctnHistService {

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 등록한다.
	 * @param cntrwkSctnHistVO - 등록할 정보가 담긴 CntrwkSctnHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 수정한다.
	 * @param cntrwkSctnHistVO - 수정할 정보가 담긴 CntrwkSctnHistVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 삭제한다.
	 * @param cntrwkSctnHistVO - 삭제할 정보가 담긴 CntrwkSctnHistVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return 조회한 TH_CNTRWK_SCTN_HIST
	 * @exception Exception
	 */
	CntrwkSctnHistVO selectCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 목록
	 * @exception Exception
	 */
	List<CntrwkSctnHistVO> selectCntrwkSctnHistList(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 총 갯수
	 * @exception
	 */
	int selectCntrwkSctnHistListTotalCount(CntrwkSctnHistVO cntrwkSctnHistVO);

}

