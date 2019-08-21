


package kr.go.gg.gpms.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.code.service.model.CodeVO;

/**
 * 공통코드
 *
 * @Class Name : CodeDAO.java
 * @Description : Code DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("codeDAO")
public class CodeDAO extends BaseDAO {

	/**
	 * 공통코드(TC_CODE)을 등록한다.
	 * @param codeVO - 등록할 정보가 담긴 CodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCode(CodeVO codeVO) throws Exception {
		return (String) insert("codeDAO.insertCode", codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 수정한다.
	 * @param codeVO - 수정할 정보가 담긴 CodeVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCode(CodeVO codeVO) throws Exception {
		return update("codeDAO.updateCode", codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 삭제한다.
	 * @param codeVO - 삭제할 정보가 담긴 CodeVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCode(CodeVO codeVO) throws Exception {
		return delete("codeDAO.deleteCode", codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return 조회한 TC_CODE
	 * @exception Exception
	 */
	public CodeVO selectCode(CodeVO codeVO) throws Exception {
		return (CodeVO) select("codeDAO.selectCode", codeVO);
	}

	/**
	 * 공통코드(TC_CODE) 목록을 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return TC_CODE 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception {
		return (List<CodeVO>)list("codeDAO.selectCodeList", codeVO);
	}

	/**
	 * 공통코드(TC_CODE) 총 갯수를 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return TC_CODE 총 갯수
	 * @exception
	 */
	public int selectCodeListTotalCount(CodeVO codeVO) {
		return (Integer) select("codeDAO.selectCodeListTotalCount", codeVO);
	}

	public CodeVO selectCodeInsertMax(CodeVO codeVO) {
		return (CodeVO) select("codeDAO.selectCodeInsertMax", codeVO);
	}

}
