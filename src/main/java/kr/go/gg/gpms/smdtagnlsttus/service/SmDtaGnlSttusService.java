package kr.go.gg.gpms.smdtagnlsttus.service;

import java.util.List;

import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusDefaultVO;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 집계_자료_일반_현황
 * TN_SM_DTA_GNL_STTUS
 *
 * @Class Name : SmDtaGnlSttusService.java
 * @Description : SmDtaGnlSttus Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SmDtaGnlSttusService {

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 등록한다.
	 * @param smDtaGnlSttusVO - 등록할 정보가 담긴 SmDtaGnlSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 수정한다.
	 * @param smDtaGnlSttusVO - 수정할 정보가 담긴 SmDtaGnlSttusVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 삭제한다.
	 * @param smDtaGnlSttusVO - 삭제할 정보가 담긴 SmDtaGnlSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 조회한다.
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return 조회한 TN_SM_DTA_GNL_STTUS
	 * @exception Exception
	 */
	SmDtaGnlSttusVO selectSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 목록
	 * @exception Exception
	 */
	List<SmDtaGnlSttusVO> selectSmDtaGnlSttusList(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 총 갯수
	 * @exception
	 */
	int selectSmDtaGnlSttusListTotalCount(SmDtaGnlSttusVO smDtaGnlSttusVO);

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 조사년도 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 목록
	 * @exception Exception
	 */
	List<SmDtaGnlSttusVO> selectSmDtaGnlSttusYearList(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;
	
	/**
     * 통합정보조회 > 조사정보 조회 
     * 데이터가 같은 년도로 2개 이상 들어가있을경우 오류 발생(데이터 확인 필요)
     * @author    : skc
     * @date      : 2017. 12. 22.
     * 
     * @param     : smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return    : SmDtaGnlSttusVO
     * @exception : Exception
     */
	SmDtaGnlSttusVO selectSmDtaGnlSttusByCellId(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;
    
    /**
     * 통합정보조회 > 조사정보 조회
     * 조사자료 전체 년도 조회 
     * @author    : skc
     * @date      : 2017. 12. 22.
     * 
     * @param     : smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return    : List<SmDtaGnlSttusVO>
     * @exception : Exception
     */
    List<SmDtaGnlSttusVO> selectSmDtaGnlSttusYearListByCellId(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception;
}

