package kr.go.gg.gpms.pavmatrl.service;

import java.util.List;

import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlDefaultVO;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 포장재료코드
 * TC_PAV_MATRL
 *
 * @Class Name : PavMatrlService.java
 * @Description : PavMatrl Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PavMatrlService {

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 등록한다.
	 * @param pavMatrlVO - 등록할 정보가 담긴 PavMatrlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertPavMatrl(PavMatrlVO pavMatrlVO) throws Exception;

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 수정한다.
	 * @param pavMatrlVO - 수정할 정보가 담긴 PavMatrlVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePavMatrl(PavMatrlVO pavMatrlVO) throws Exception;

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 삭제한다.
	 * @param pavMatrlVO - 삭제할 정보가 담긴 PavMatrlVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePavMatrl(PavMatrlVO pavMatrlVO) throws Exception;

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return 조회한 TC_PAV_MATRL
	 * @exception Exception
	 */
	PavMatrlVO selectPavMatrl(PavMatrlVO pavMatrlVO) throws Exception;

	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavMatrlVO
	 * @return TC_PAV_MATRL 목록
	 * @exception Exception
	 */
	List<PavMatrlVO> selectPavMatrlList(PavMatrlVO pavMatrlVO) throws Exception;

	/**
	 * 포장재료코드(TC_PAV_MATRL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavMatrlVO
	 * @return TC_PAV_MATRL 총 갯수
	 * @exception
	 */
	int selectPavMatrlListTotalCount(PavMatrlVO pavMatrlVO);

	/**
	 * 포장재료코드(TC_PAV_MATRL)를 포장재료명으로 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return 조회한 TC_PAV_MATRL
	 * @exception Exception
	 */
	PavMatrlVO selectPavMatrlCode(PavMatrlVO pavMatrlVO) throws Exception;
}

