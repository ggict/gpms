package kr.go.gg.gpms.smdtalaststtus.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;

/**
 * 집계_자료_최신_현황
 * TN_SM_DTA_LAST_STTUS
 *
 * @Class Name : SmDtaLastSttusService.java
 * @Description : SmDtaLastSttus Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SmDtaLastSttusService {

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 등록한다.
	 * @param smDtaLastSttusVO - 등록할 정보가 담긴 SmDtaLastSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 수정한다.
	 * @param smDtaLastSttusVO - 수정할 정보가 담긴 SmDtaLastSttusVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 삭제한다.
	 * @param smDtaLastSttusVO - 삭제할 정보가 담긴 SmDtaLastSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return 조회한 TN_SM_DTA_LAST_STTUS
	 * @exception Exception
	 */
	SmDtaLastSttusVO selectSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	List<SmDtaLastSttusVO> selectSmDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

	List selectSmDtaLastSttusListExcel(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;
	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	int selectSmDtaLastSttusListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO);

	
	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록 을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	List<SmDtaLastSttusVO> selectRouteEvlList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception ;

	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록  총 갯수를 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	int selectRouteEvlListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO);
	
	/**
	 * 집계된 조사자료를 현행화 한다
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 */
	HashMap prcClacPredctRouteSrvyEvl(SmDtaLastSttusVO smDtaLastSttusVO);
	
	
	/**
	 * 10년치 예측 데이터를 생성한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 */
	HashMap prcClacPredctLast(SmDtaLastSttusVO smDtaLastSttusVO);
	
    /**
     * 평가정보 예측자료 목록을 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 예측자료 목록
     * @exception : Exception
     */
    List<SmDtaLastSttusVO> selectSrvyDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

    /**
     * 평가정보 예측자료 목록 총 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 예측자료 목록 총 갯수
     * @exception
     */
    int selectSrvyDtaLastSttusListCnt(SmDtaLastSttusVO smDtaLastSttusVO);

    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록을 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 공사자료 목록
     * @exception : Exception
     */
    List<SmDtaLastSttusVO> selectCntrwkListBySect(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록 총 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 공사자료 목록 총 갯수
     * @exception
     */
    int selectCntrwkListBySectCnt(SmDtaLastSttusVO smDtaLastSttusVO);
    
    /**
	 * 평가정보 예측 통계 정보를 조회한다.
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	List<SmDtaLastSttusVO> prcStatPredct(SmDtaLastSttusVO smDtaLastSttusVO, String mode) throws Exception;
	
	/**
	 * 평가정보 예측 통계 정보 > 엑셀 저장
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	List prcStatPredctExcel(SmDtaLastSttusVO smDtaLastSttusVO, String mode) throws Exception;

	/**
     * 평가정보 예측자료 상세정보의 이전 조사자료 GPCI 목록을 조회한다.
     * @author    : skc
     * @date      : 2017. 12. 08.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 조사자료 목록
     * @exception : Exception
     */
    List<SmDtaLastSttusVO> selectSmDtaGnlGPCIList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

    /**
     * 포장상태 평가정보 상세조회 - 현행정보조회
     * @author    : JOY
     * @date      : 2017. 12. 11.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 현행정보
     * @exception : Exception
     */
    SmDtaLastSttusVO selectSmDtaLastSttusBySrvy(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception;

    
}

