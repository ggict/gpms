package kr.go.gg.gpms.srvydtalog.service;

import java.util.List;

import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogDefaultVO;
import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 조사_자료_로그
 * TL_SRVY_DTA_LOG
 *
 * @Class Name : SrvyDtaLogService.java
 * @Description : SrvyDtaLog Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SrvyDtaLogService {

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 등록한다.
	 * @param srvyDtaLogVO - 등록할 정보가 담긴 SrvyDtaLogVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 수정한다.
	 * @param srvyDtaLogVO - 수정할 정보가 담긴 SrvyDtaLogVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 삭제한다.
	 * @param srvyDtaLogVO - 삭제할 정보가 담긴 SrvyDtaLogVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return 조회한 TL_SRVY_DTA_LOG
	 * @exception Exception
	 */
	SrvyDtaLogVO selectSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 목록
	 * @exception Exception
	 */
	List<SrvyDtaLogVO> selectSrvyDtaLogList(SrvyDtaLogVO srvyDtaLogVO) throws Exception;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 총 갯수
	 * @exception
	 */
	int selectSrvyDtaLogListTotalCount(SrvyDtaLogVO srvyDtaLogVO);

}

