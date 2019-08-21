package kr.go.gg.gpms.codeusemap.service;

import java.util.List;

import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapDefaultVO;
import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 코드사용맵
 * TC_CODE_USEMAP
 *
 * @Class Name : CodeUsemapService.java
 * @Description : CodeUsemap Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CodeUsemapService {

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 등록한다.
	 * @param codeUsemapVO - 등록할 정보가 담긴 CodeUsemapVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception;

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 수정한다.
	 * @param codeUsemapVO - 수정할 정보가 담긴 CodeUsemapVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception;

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 삭제한다.
	 * @param codeUsemapVO - 삭제할 정보가 담긴 CodeUsemapVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception;

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return 조회한 TC_CODE_USEMAP
	 * @exception Exception
	 */
	CodeUsemapVO selectCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception;

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeUsemapVO
	 * @return TC_CODE_USEMAP 목록
	 * @exception Exception
	 */
	List<CodeUsemapVO> selectCodeUsemapList(CodeUsemapVO codeUsemapVO) throws Exception;

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeUsemapVO
	 * @return TC_CODE_USEMAP 총 갯수
	 * @exception
	 */
	int selectCodeUsemapListTotalCount(CodeUsemapVO codeUsemapVO);

	List<CodeUsemapVO> selectColumnList(CodeUsemapVO codeUsemapVO);

	List<CodeUsemapVO>  selectTables(CodeUsemapVO codeUsemapVO);

}

