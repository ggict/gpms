package kr.go.gg.gpms.clcode.service;

import java.util.List;

import kr.go.gg.gpms.clcode.service.model.ClCodeDefaultVO;
import kr.go.gg.gpms.clcode.service.model.ClCodeVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 분류코드
 * TC_CL_CODE
 *
 * @Class Name : ClCodeService.java
 * @Description : ClCode Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ClCodeService {

	/**
	 * 분류코드(TC_CL_CODE)을 등록한다.
	 * @param clCodeVO - 등록할 정보가 담긴 ClCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertClCode(ClCodeVO clCodeVO) throws Exception;

	/**
	 * 분류코드(TC_CL_CODE)을 수정한다.
	 * @param clCodeVO - 수정할 정보가 담긴 ClCodeVO
	 * @return int형
	 * @exception Exception
	 */
	int updateClCode(ClCodeVO clCodeVO) throws Exception;

	/**
	 * 분류코드(TC_CL_CODE)을 삭제한다.
	 * @param clCodeVO - 삭제할 정보가 담긴 ClCodeVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteClCode(ClCodeVO clCodeVO) throws Exception;

	/**
	 * 분류코드(TC_CL_CODE)을 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return 조회한 TC_CL_CODE
	 * @exception Exception
	 */
	ClCodeVO selectClCode(ClCodeVO clCodeVO) throws Exception;

	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 clCodeVO
	 * @return TC_CL_CODE 목록
	 * @exception Exception
	 */
	List<ClCodeVO> selectClCodeList(ClCodeVO clCodeVO) throws Exception;

	/**
	 * 분류코드(TC_CL_CODE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 clCodeVO
	 * @return TC_CL_CODE 총 갯수
	 * @exception
	 */
	int selectClCodeListTotalCount(ClCodeVO clCodeVO);

}

