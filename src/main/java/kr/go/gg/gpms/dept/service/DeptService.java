package kr.go.gg.gpms.dept.service;

import java.util.List;

import kr.go.gg.gpms.dept.service.model.DeptVO;

/**
 * 부서_코드
 * TC_DEPT
 *
 * @Class Name : DeptService.java
 * @Description : Dept Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface DeptService {

	/**
	 * 부서_코드(TC_DEPT)을 등록한다.
	 * @param deptVO - 등록할 정보가 담긴 DeptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertDept(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT)을 수정한다.
	 * @param deptVO - 수정할 정보가 담긴 DeptVO
	 * @return int형
	 * @exception Exception
	 */
	int updateDept(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT)을 삭제한다.
	 * @param deptVO - 삭제할 정보가 담긴 DeptVO
	 * @return int형
	 * @exception Exception
	 */
	int deleteDept(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT)을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return 조회한 TC_DEPT
	 * @exception Exception
	 */
	DeptVO selectDept(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	List<DeptVO> selectDeptList(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 총 갯수
	 * @exception
	 */
	int selectDeptListTotalCount(DeptVO deptVO);

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	List<DeptVO> selectDeptCodeList(DeptVO deptVO) throws Exception;

	/**
	 * 부서_코드(TC_DEPT) 상위 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	List<DeptVO> selectSHighDeptList(DeptVO deptVO) throws Exception;


	/**
	 * 건설본부 남/북 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	List<DeptVO> selectCntrwkDeptList(DeptVO deptVO) throws Exception;


	/**
	 * 모니터링단원 관리 -> 지역 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	List<DeptVO> selectRegionList(DeptVO deptVO) throws Exception;

	/**
	 * 관할구역검색 관리 -> 관할기관 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectCptcIsttList(DeptVO deptVO) throws Exception;

}

