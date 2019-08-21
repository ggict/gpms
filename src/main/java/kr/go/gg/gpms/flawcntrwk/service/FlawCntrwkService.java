package kr.go.gg.gpms.flawcntrwk.service;

import java.util.List;

import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;

/**
 * 하자_보수_공사
 * TN_FLAW_CNTRWK
 *
 * @Class Name : FlawCntrwkService.java
 * @Description : FlawCntrwk Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface FlawCntrwkService {

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 등록한다.
	 * @param flawCntrwkVO - 등록할 정보가 담긴 FlawCntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 수정한다.
	 * @param flawCntrwkVO - 수정할 정보가 담긴 FlawCntrwkVO
	 * @return int형
	 * @exception Exception
	 */
	int updateFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception;
	
	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteFlawCntrwkByCntrwkDtlID(FlawCntrwkVO flawCntrwkVO) throws Exception;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return 조회한 TN_FLAW_CNTRWK
	 * @exception Exception
	 */
	FlawCntrwkVO selectFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawCntrwkVO
	 * @return TN_FLAW_CNTRWK 목록
	 * @exception Exception
	 */
	List<FlawCntrwkVO> selectFlawCntrwkList(FlawCntrwkVO flawCntrwkVO) throws Exception;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawCntrwkVO
	 * @return TN_FLAW_CNTRWK 총 갯수
	 * @exception
	 */
	int selectFlawCntrwkListTotalCount(FlawCntrwkVO flawCntrwkVO);

	/**
	 * 하자_보수_공사 엑셀 목록을 조회한다.
	 */
	List selectFlawCntrwkListExcel(FlawCntrwkVO flawCntrwkVO) throws Exception;
}

