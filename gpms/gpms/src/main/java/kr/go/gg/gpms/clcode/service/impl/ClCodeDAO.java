


package kr.go.gg.gpms.clcode.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.clcode.service.model.ClCodeVO;

/**
 * 분류코드
 *
 * @Class Name : ClCodeDAO.java
 * @Description : ClCode DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("clCodeDAO")
public class ClCodeDAO extends BaseDAO {

	/**
	 * 분류코드(TC_CL_CODE)을 등록한다.
	 * @param clCodeVO - 등록할 정보가 담긴 ClCodeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertClCode(ClCodeVO clCodeVO) throws Exception {
		return (String) insert("clCodeDAO.insertClCode", clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 수정한다.
	 * @param clCodeVO - 수정할 정보가 담긴 ClCodeVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateClCode(ClCodeVO clCodeVO) throws Exception {
		return update("clCodeDAO.updateClCode", clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 삭제한다.
	 * @param clCodeVO - 삭제할 정보가 담긴 ClCodeVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteClCode(ClCodeVO clCodeVO) throws Exception {
		return delete("clCodeDAO.deleteClCode", clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE)을 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return 조회한 TC_CL_CODE
	 * @exception Exception
	 */
	public ClCodeVO selectClCode(ClCodeVO clCodeVO) throws Exception {
		return (ClCodeVO) select("clCodeDAO.selectClCode", clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE) 목록을 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return TC_CL_CODE 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ClCodeVO> selectClCodeList(ClCodeVO clCodeVO) throws Exception {
		return (List<ClCodeVO>)list("clCodeDAO.selectClCodeList", clCodeVO);
	}

	/**
	 * 분류코드(TC_CL_CODE) 총 갯수를 조회한다.
	 * @param clCodeVO - 조회할 정보가 담긴 ClCodeVO
	 * @return TC_CL_CODE 총 갯수
	 * @exception
	 */
	public int selectClCodeListTotalCount(ClCodeVO clCodeVO) {
		return (Integer) select("clCodeDAO.selectClCodeListTotalCount", clCodeVO);
	}

}
