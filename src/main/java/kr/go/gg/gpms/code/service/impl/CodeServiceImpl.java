package kr.go.gg.gpms.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;

/**
 * 공통코드
 *
 * @Class Name : CodeServiceImpl.java
 * @Description : Code Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("codeService")
public class CodeServiceImpl extends AbstractServiceImpl implements CodeService {

	@Resource(name = "codeDAO")
	private CodeDAO codeDAO;

	//@Resource(name="CodeIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공통코드(TC_CODE)을 등록한다.
	 * @param codeVO - 등록할 정보가 담긴 CodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCode(CodeVO codeVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//codeVO.setId(id);

		return codeDAO.insertCode( codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 수정한다.
	 * @param codeVO - 수정할 정보가 담긴 CodeVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCode(CodeVO codeVO) throws Exception {
		return codeDAO.updateCode( codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 삭제한다.
	 * @param codeVO - 삭제할 정보가 담긴 CodeVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCode(CodeVO codeVO) throws Exception {
		return codeDAO.deleteCode( codeVO);
	}

	/**
	 * 공통코드(TC_CODE)을 조회한다.
	 * @param codeVO - 조회할 정보가 담긴 CodeVO
	 * @return 조회한 TC_CODE
	 * @exception Exception
	 */
	public CodeVO selectCode(CodeVO codeVO) throws Exception {
		CodeVO resultVO = codeDAO.selectCode( codeVO);
		 
		return resultVO;
	}

	/**
	 * 공통코드(TC_CODE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return TC_CODE 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception {
		return codeDAO.selectCodeList( codeVO);
	}

	/**
	 * 공통코드(TC_CODE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return TC_CODE 총 갯수
	 * @exception
	 */
	public int selectCodeListTotalCount(CodeVO codeVO) {
		return codeDAO.selectCodeListTotalCount( codeVO);
	}

	@Override
	public CodeVO selectCodeInsertMax(CodeVO codeVO) {
		CodeVO resultVO = codeDAO.selectCodeInsertMax( codeVO);
		 
		return resultVO;
	}

}
