package kr.go.gg.gpms.clcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.clcode.service.ClCodeService;
import kr.go.gg.gpms.clcode.service.model.ClCodeVO;

/**
 * 분류코드
 *
 * @Class Name : ClCodeServiceImpl.java
 * @Description : ClCode Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("clCodeService")
public class ClCodeServiceImpl extends AbstractServiceImpl implements ClCodeService {

	@Resource(name = "clCodeDAO")
	private ClCodeDAO clCodeDAO;

	//@Resource(name="ClCodeIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 분류코드(TC_CL_CODE)을 등록한다.
	 * @param clCodeVO - 등록할 정보가 담긴 ClCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertClCode(ClCodeVO clCodeVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//clCodeVO.setId(id);

		return clCodeDAO.insertClCode( clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 수정한다.
	 * @param clCodeVO - 수정할 정보가 담긴 ClCodeVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateClCode(ClCodeVO clCodeVO) throws Exception {
		return clCodeDAO.updateClCode( clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 삭제한다.
	 * @param clCodeVO - 삭제할 정보가 담긴 ClCodeVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteClCode(ClCodeVO clCodeVO) throws Exception {
		return clCodeDAO.deleteClCode( clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return 조회한 TC_CL_CODE
	 * @exception Exception
	 */
	public ClCodeVO selectClCode(ClCodeVO clCodeVO) throws Exception {
		ClCodeVO resultVO = clCodeDAO.selectClCode( clCodeVO);
		 
		return resultVO;
	}

	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 clCodeVO
	 * @return TC_CL_CODE 목록
	 * @exception Exception
	 */
	public List<ClCodeVO> selectClCodeList(ClCodeVO clCodeVO) throws Exception {
		return clCodeDAO.selectClCodeList( clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 clCodeVO
	 * @return TC_CL_CODE 총 갯수
	 * @exception
	 */
	public int selectClCodeListTotalCount(ClCodeVO clCodeVO) {
		return clCodeDAO.selectClCodeListTotalCount( clCodeVO);
	}

}
