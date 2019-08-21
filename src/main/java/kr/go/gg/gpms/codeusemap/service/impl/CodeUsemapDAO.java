


package kr.go.gg.gpms.codeusemap.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapVO;

/**
 * 코드사용맵
 *
 * @Class Name : CodeUsemapDAO.java
 * @Description : CodeUsemap DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("codeUsemapDAO")
public class CodeUsemapDAO extends BaseDAO {

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 등록한다.
	 * @param codeUsemapVO - 등록할 정보가 담긴 CodeUsemapVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return (String) insert("codeUsemapDAO.insertCodeUsemap", codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 수정한다.
	 * @param codeUsemapVO - 수정할 정보가 담긴 CodeUsemapVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return update("codeUsemapDAO.updateCodeUsemap", codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 삭제한다.
	 * @param codeUsemapVO - 삭제할 정보가 담긴 CodeUsemapVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return delete("codeUsemapDAO.deleteCodeUsemap", codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return 조회한 TC_CODE_USEMAP
	 * @exception Exception
	 */
	public CodeUsemapVO selectCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return (CodeUsemapVO) select("codeUsemapDAO.selectCodeUsemap", codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return TC_CODE_USEMAP 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CodeUsemapVO> selectCodeUsemapList(CodeUsemapVO codeUsemapVO) throws Exception {
		return (List<CodeUsemapVO>)list("codeUsemapDAO.selectCodeUsemapList", codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 총 갯수를 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return TC_CODE_USEMAP 총 갯수
	 * @exception
	 */
	public int selectCodeUsemapListTotalCount(CodeUsemapVO codeUsemapVO) {
		return (Integer) select("codeUsemapDAO.selectCodeUsemapListTotalCount", codeUsemapVO);
	}

	public List<CodeUsemapVO> selectTables(CodeUsemapVO codeUsemapVO) {
		return (List<CodeUsemapVO>)list("codeUsemapDAO.selectTables", codeUsemapVO);
	}

	public List<CodeUsemapVO> selectColumnList(CodeUsemapVO codeUsemapVO) {
		return (List<CodeUsemapVO>)list("codeUsemapDAO.selectColumnList", codeUsemapVO);
	}

}
