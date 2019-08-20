


package kr.go.gg.gpms.dept.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.dept.service.model.DeptVO;

/**
 * 부서_코드
 *
 * @Class Name : DeptDAO.java
 * @Description : Dept DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("deptDAO")
public class DeptDAO extends BaseDAO {

	/**
	 * 부서_코드(TC_DEPT)을 등록한다.
	 * @param deptVO - 등록할 정보가 담긴 DeptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertDept(DeptVO deptVO) throws Exception {
		return (String) insert("deptDAO.insertDept", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 수정한다.
	 * @param deptVO - 수정할 정보가 담긴 DeptVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateDept(DeptVO deptVO) throws Exception {
		return update("deptDAO.updateDept", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 삭제한다.
	 * @param deptVO - 삭제할 정보가 담긴 DeptVO
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteDept(DeptVO deptVO) throws Exception {
		return update("deptDAO.deleteDept", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return 조회한 TC_DEPT
	 * @exception Exception
	 */
	public DeptVO selectDept(DeptVO deptVO) throws Exception {
		return (DeptVO) select("deptDAO.selectDept", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DeptVO> selectDeptList(DeptVO deptVO) throws Exception {
		return (List<DeptVO>)list("deptDAO.selectDeptList", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 총 갯수를 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return TC_DEPT 총 갯수
	 * @exception
	 */
	public int selectDeptListTotalCount(DeptVO deptVO) {
		return (Integer) select("deptDAO.selectDeptListTotalCount", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DeptVO> selectDeptCodeList(DeptVO deptVO) throws Exception {
		return (List<DeptVO>)list("deptDAO.selectDeptCodeList", deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 상위 부서 목록을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DeptVO> selectSHighDeptList(DeptVO deptVO) throws Exception {
		return (List<DeptVO>)list("deptDAO.selectSHighDeptList", deptVO);
	}

	/**
	 * 건설본부 남/북 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectCntrwkDeptList(DeptVO deptVO) {
		return (List<DeptVO>)list("deptDAO.selectCntrwkDeptList", deptVO);
	}


	/**
	 * 모니터링단원 관리 -> 지역 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectRegionList(DeptVO deptVO) {
		return (List<DeptVO>)list("deptDAO.selectRegionList", deptVO);
	}

	/**
	 * 관할구역검색 관리 -> 관할기관 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectCptcIsttList(DeptVO deptVO) {
		return (List<DeptVO>)list("deptDAO.selectCptcIsttList", deptVO);
	}

}
