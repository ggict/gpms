package kr.go.gg.gpms.cntrcomp.service;

import java.util.List;

import kr.go.gg.gpms.cntrcomp.service.model.CntrCompDefaultVO;
import kr.go.gg.gpms.cntrcomp.service.model.CntrCompVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공사업체정보
 * TN_CNTR_COMP
 *
 * @Class Name : CntrCompService.java
 * @Description : CntrComp Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CntrCompService {

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 등록한다.
	 * @param cntrCompVO - 등록할 정보가 담긴 CntrCompVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCntrComp(CntrCompVO cntrCompVO) throws Exception;

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 수정한다.
	 * @param cntrCompVO - 수정할 정보가 담긴 CntrCompVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCntrComp(CntrCompVO cntrCompVO) throws Exception;

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 삭제한다.
	 * @param cntrCompVO - 삭제할 정보가 담긴 CntrCompVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCntrComp(CntrCompVO cntrCompVO) throws Exception;

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return 조회한 TN_CNTR_COMP
	 * @exception Exception
	 */
	CntrCompVO selectCntrComp(CntrCompVO cntrCompVO) throws Exception;

	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrCompVO
	 * @return TN_CNTR_COMP 목록
	 * @exception Exception
	 */
	List<CntrCompVO> selectCntrCompList(CntrCompVO cntrCompVO) throws Exception;

	/**
	 * 공사업체정보(TN_CNTR_COMP) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrCompVO
	 * @return TN_CNTR_COMP 총 갯수
	 * @exception
	 */
	int selectCntrCompListTotalCount(CntrCompVO cntrCompVO);

}

