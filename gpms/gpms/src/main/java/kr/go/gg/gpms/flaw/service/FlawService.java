package kr.go.gg.gpms.flaw.service;

import java.util.List;

import kr.go.gg.gpms.flaw.service.model.FlawDefaultVO;
import kr.go.gg.gpms.flaw.service.model.FlawVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공사하자정보
 * TN_FLAW
 *
 * @Class Name : FlawService.java
 * @Description : Flaw Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface FlawService {

	/**
	 * 공사하자정보(TN_FLAW)을 등록한다.
	 * @param flawVO - 등록할 정보가 담긴 FlawVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertFlaw(FlawVO flawVO) throws Exception;

	/**
	 * 공사하자정보(TN_FLAW)을 수정한다.
	 * @param flawVO - 수정할 정보가 담긴 FlawVO
	 * @return int형
	 * @exception Exception
	 */
	int updateFlaw(FlawVO flawVO) throws Exception;

	/**
	 * 공사하자정보(TN_FLAW)을 삭제한다.
	 * @param flawVO - 삭제할 정보가 담긴 FlawVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteFlaw(FlawVO flawVO) throws Exception;

	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	FlawVO selectFlaw(FlawVO flawVO) throws Exception;

	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawVO
	 * @return TN_FLAW 목록
	 * @exception Exception
	 */
	List<FlawVO> selectFlawList(FlawVO flawVO) throws Exception;

	/**
	 * 공사하자정보(TN_FLAW) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawVO
	 * @return TN_FLAW 총 갯수
	 * @exception
	 */
	int selectFlawListTotalCount(FlawVO flawVO);
	
	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	FlawVO selectFlawByCntrwkId(FlawVO flawVO) throws Exception;

}

