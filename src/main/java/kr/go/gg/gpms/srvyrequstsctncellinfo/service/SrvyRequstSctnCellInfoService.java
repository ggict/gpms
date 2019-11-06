package kr.go.gg.gpms.srvyrequstsctncellinfo.service;

import java.util.List;

import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.model.SrvyRequstSctnCellInfoDefaultVO;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.model.SrvyRequstSctnCellInfoVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 조사_요청_구간_셀_정보
 * TN_SRVY_REQUST_SCTN_CELL_INFO
 *
 * @Class Name : SrvyRequstSctnCellInfoService.java
 * @Description : SrvyRequstSctnCellInfo Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SrvyRequstSctnCellInfoService {

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 등록한다.
	 * @param srvyRequstSctnCellInfoVO - 등록할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 수정한다.
	 * @param srvyRequstSctnCellInfoVO - 수정할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 삭제한다.
	 * @param srvyRequstSctnCellInfoVO - 삭제할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 조회한다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 조회한 TN_SRVY_REQUST_SCTN_CELL_INFO
	 * @exception Exception
	 */
	SrvyRequstSctnCellInfoVO selectSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return TN_SRVY_REQUST_SCTN_CELL_INFO 목록
	 * @exception Exception
	 */
	List<SrvyRequstSctnCellInfoVO> selectSrvyRequstSctnCellInfoList(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return TN_SRVY_REQUST_SCTN_CELL_INFO 총 갯수
	 * @exception
	 */
	int selectSrvyRequstSctnCellInfoListTotalCount(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO);
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 리스트를 가져온다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return CELL_10 노선정보 리스트
	 * @exception Exception
	 */
	List<Cell10VO> selectRouteInfoListByCellID(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception;

}

