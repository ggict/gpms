package kr.go.gg.gpms.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;

/**
 * 부서_코드
 *
 * @Class Name : DeptServiceImpl.java
 * @Description : Dept Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("deptService")
public class DeptServiceImpl extends AbstractServiceImpl implements DeptService {

	@Resource(name = "deptDAO")
	private DeptDAO deptDAO;

	//@Resource(name="DeptIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 부서_코드(TC_DEPT)을 등록한다.
	 * @param deptVO - 등록할 정보가 담긴 DeptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertDept(DeptVO deptVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//deptVO.setId(id);

		return deptDAO.insertDept( deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 수정한다.
	 * @param deptVO - 수정할 정보가 담긴 DeptVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateDept(DeptVO deptVO) throws Exception {
		return deptDAO.updateDept( deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 삭제한다.
	 * @param deptVO - 삭제할 정보가 담긴 DeptVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteDept(DeptVO deptVO) throws Exception {
		return deptDAO.deleteDept( deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT)을 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return 조회한 TC_DEPT
	 * @exception Exception
	 */
	public DeptVO selectDept(DeptVO deptVO) throws Exception {
		DeptVO resultVO = deptDAO.selectDept( deptVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectDeptList(DeptVO deptVO) throws Exception {
		return deptDAO.selectDeptList( deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 총 갯수
	 * @exception
	 */
	public int selectDeptListTotalCount(DeptVO deptVO) {
		return deptDAO.selectDeptListTotalCount( deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectDeptCodeList(DeptVO deptVO) throws Exception {
		return deptDAO.selectDeptCodeList(deptVO);
	}

	/**
	 * 부서_코드(TC_DEPT) 상위 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectSHighDeptList(DeptVO deptVO) throws Exception {
		return deptDAO.selectSHighDeptList(deptVO);
	}

	/**
	 * 건설본부 남/북 부서_코드(TC_DEPT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectCntrwkDeptList(DeptVO deptVO) throws Exception {
		return deptDAO.selectCntrwkDeptList(deptVO);
	}


	/**
	 * 모니터링단원 관리 -> 지역 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectRegionList(DeptVO deptVO) throws Exception {
		return deptDAO.selectRegionList(deptVO);
	}

	/**
	 * 관할구역검색 -> 관할기관 셀렉트박스 리스트
	 * @param deptVO - 조회할 정보가 담긴 deptVO
	 * @return TC_DEPT 목록
	 * @exception Exception
	 */
	public List<DeptVO> selectCptcIsttList(DeptVO deptVO) throws Exception {
		return deptDAO.selectCptcIsttList(deptVO);
	}
}
