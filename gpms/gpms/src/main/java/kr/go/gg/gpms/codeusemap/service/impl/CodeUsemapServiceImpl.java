package kr.go.gg.gpms.codeusemap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.codeusemap.service.CodeUsemapService;
import kr.go.gg.gpms.codeusemap.service.model.CodeUsemapVO;

/**
 * 코드사용맵
 *
 * @Class Name : CodeUsemapServiceImpl.java
 * @Description : CodeUsemap Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("codeUsemapService")
public class CodeUsemapServiceImpl extends AbstractServiceImpl implements CodeUsemapService {

	@Resource(name = "codeUsemapDAO")
	private CodeUsemapDAO codeUsemapDAO;

	//@Resource(name="CodeUsemapIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 등록한다.
	 * @param codeUsemapVO - 등록할 정보가 담긴 CodeUsemapVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//codeUsemapVO.setId(id);

		return codeUsemapDAO.insertCodeUsemap( codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 수정한다.
	 * @param codeUsemapVO - 수정할 정보가 담긴 CodeUsemapVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return codeUsemapDAO.updateCodeUsemap( codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 삭제한다.
	 * @param codeUsemapVO - 삭제할 정보가 담긴 CodeUsemapVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		return codeUsemapDAO.deleteCodeUsemap( codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP)을 조회한다.
	 * @param codeUsemapVO - 조회할 정보가 담긴 CodeUsemapVO
	 * @return 조회한 TC_CODE_USEMAP
	 * @exception Exception
	 */
	public CodeUsemapVO selectCodeUsemap(CodeUsemapVO codeUsemapVO) throws Exception {
		CodeUsemapVO resultVO = codeUsemapDAO.selectCodeUsemap( codeUsemapVO);
		 
		return resultVO;
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeUsemapVO
	 * @return TC_CODE_USEMAP 목록
	 * @exception Exception
	 */
	public List<CodeUsemapVO> selectCodeUsemapList(CodeUsemapVO codeUsemapVO) throws Exception {
		return codeUsemapDAO.selectCodeUsemapList( codeUsemapVO);
	}

	/**
	 * 코드사용맵(TC_CODE_USEMAP) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeUsemapVO
	 * @return TC_CODE_USEMAP 총 갯수
	 * @exception
	 */
	public int selectCodeUsemapListTotalCount(CodeUsemapVO codeUsemapVO) {
		return codeUsemapDAO.selectCodeUsemapListTotalCount( codeUsemapVO);
	}

	@Override
	public List<CodeUsemapVO> selectColumnList(CodeUsemapVO codeUsemapVO) {
		return codeUsemapDAO.selectColumnList( codeUsemapVO);
	}

	@Override
	public List<CodeUsemapVO> selectTables(CodeUsemapVO codeUsemapVO) {
		return codeUsemapDAO.selectTables( codeUsemapVO);
	}

}
