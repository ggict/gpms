package kr.go.gg.gpms.pavidxeryyval.service;

import java.util.List;

import kr.go.gg.gpms.pavidxeryyval.service.model.PavIdxEryyvalDefaultVO;
import kr.go.gg.gpms.pavidxeryyval.service.model.PavIdxEryyvalVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 포장_지수_초기값
 * TN_PAV_IDX_INITVAL
 *
 * @Class Name : PavIdxEryyvalService.java
 * @Description : PavIdxEryyval Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PavIdxEryyvalService {

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 등록한다.
	 * @param pavIdxEryyvalVO - 등록할 정보가 담긴 PavIdxEryyvalVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertPavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 수정한다.
	 * @param pavIdxEryyvalVO - 수정할 정보가 담긴 PavIdxEryyvalVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 삭제한다.
	 * @param pavIdxEryyvalVO - 삭제할 정보가 담긴 PavIdxEryyvalVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 조회한다.
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return 조회한 TN_PAV_IDX_INITVAL
	 * @exception Exception
	 */
	PavIdxEryyvalVO selectPavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavIdxEryyvalVO
	 * @return TN_PAV_IDX_INITVAL 목록
	 * @exception Exception
	 */
	List<PavIdxEryyvalVO> selectPavIdxEryyvalList(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavIdxEryyvalVO
	 * @return TN_PAV_IDX_INITVAL 총 갯수
	 * @exception
	 */
	int selectPavIdxEryyvalListTotalCount(PavIdxEryyvalVO pavIdxEryyvalVO);

}

