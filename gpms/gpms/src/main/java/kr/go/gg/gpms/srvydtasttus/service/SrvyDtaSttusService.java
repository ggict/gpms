package kr.go.gg.gpms.srvydtasttus.service;

import java.util.List;

import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusDefaultVO;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 조사_자료_현황
 * TN_SRVY_DTA_STTUS
 *
 * @Class Name : SrvyDtaSttusService.java
 * @Description : SrvyDtaSttus Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SrvyDtaSttusService {

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 등록한다.
	 * @param srvyDtaSttusVO - 등록할 정보가 담긴 SrvyDtaSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 수정한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 삭제한다.
	 * @param srvyDtaSttusVO - 삭제할 정보가 담긴 SrvyDtaSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조회한 TN_SRVY_DTA_STTUS
	 * @exception Exception
	 */
	SrvyDtaSttusVO selectSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 목록
	 * @exception Exception
	 */
	List<SrvyDtaSttusVO> selectSrvyDtaSttusList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

	/**
	 * 집계하지 않은 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다.
	 * @param srvyDtaSttusVO
	 * @return
	 * @throws Exception
	 */
	List<SrvyDtaSttusVO>  selectSrvyDtaSttusNotPRDCTN_AT(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 총 갯수
	 * @exception
	 */
	int selectSrvyDtaSttusListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO);
	
	/**
	 * 조사_자료 분석 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록
	 * @exception
	 */
	List<SrvyDtaSttusVO> selectSrvyDtaAnalTrgetList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;
	
	/**
	 * 조사_자료 분석 대상 목록 총 갯수를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록 총 갯수
	 * @exception
	 */
	int selectSrvyDtaAnalTrgetListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO);
	
	/**
	 * 조사_자료 분석 대상을 삭제한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	int deleteSrvyDtaAnalTrget(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception;

}

