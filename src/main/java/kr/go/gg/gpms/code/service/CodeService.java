package kr.go.gg.gpms.code.service;

import java.util.List;

import kr.go.gg.gpms.code.service.model.CodeDefaultVO;
import kr.go.gg.gpms.code.service.model.CodeVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통코드
 * TC_CODE
 *
 * @Class Name : CodeService.java
 * @Description : Code Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CodeService {

	/**
	 * 공통코드(TC_CODE)을 등록한다.
	 * @param codeVO - 등록할 정보가 담긴 CodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCode(CodeVO codeVO) throws Exception;

	/**
	 * 공통코드(TC_CODE)을 수정한다.
	 * @param codeVO - 수정할 정보가 담긴 CodeVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCode(CodeVO codeVO) throws Exception;

	/**
	 * 공통코드(TC_CODE)을 삭제한다.
	 * @param codeVO - 삭제할 정보가 담긴 CodeVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCode(CodeVO codeVO) throws Exception;

	/**
	 * 공통코드(TC_CODE)을 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return 조회한 TC_CODE
	 * @exception Exception
	 */
	CodeVO selectCode(CodeVO codeVO) throws Exception;

	/**
	 * 공통코드(TC_CODE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return TC_CODE 목록
	 * @exception Exception
	 */
	List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception;

	/**
	 * 공통코드(TC_CODE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return TC_CODE 총 갯수
	 * @exception
	 */
	int selectCodeListTotalCount(CodeVO codeVO);

	/**
	 * 등록하기 위한 최대값 정보를 조회한다.
	 * @param codeVO
	 * @return
	 */
	CodeVO selectCodeInsertMax(CodeVO codeVO);

}

