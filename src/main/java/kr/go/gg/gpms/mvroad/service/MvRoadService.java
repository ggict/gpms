package kr.go.gg.gpms.mvroad.service;

import java.util.List;

import kr.go.gg.gpms.mvroad.service.model.MvRoadVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

/**
 * CELL_SECT
 * CELL_SECT
 *
 * @Class Name : CellSectService.java
 * @Description : CellSect Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface MvRoadService {

	/**
	 * 특별관리구간 상세정보 조회한다.
	 * @param mvroadVO - 등록할 정보가 담긴 MvRoadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	MvRoadVO selectMvRoad(MvRoadVO mvroadVO) throws Exception;

	/**
	 * 특별관리구간 상세정보 등록한다.
	 * @param mvroadVO - 등록할 정보가 담긴 MvRoadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertMvRoad(MvRoadVO mvroadVO) throws Exception ;

	/**
	 * 특별관리구간 상세정보 삭제한다.
	 * @param mvroadVO - 등록할 정보가 담긴 MvRoadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	int deleteMvRoad(MvRoadVO mvroadVO) throws Exception ;

	/**
	 * 특별관리구간 상세정보 수정한다.
	 * @param mvroadVO - 등록할 정보가 담긴 MvRoadVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	int updateMvRoad(MvRoadVO mvroadVO) throws Exception ;


}

