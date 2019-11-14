package kr.go.gg.gpms.mummsctnsrvydta.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;

/**
 * 최소_구간_조사_자료
 * TN_MUMM_SCTN_SRVY_DTA
 *
 * @Class Name : MummSctnSrvyDtaService.java
 * @Description : MummSctnSrvyDta Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MummSctnSrvyDtaService {
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA)상세를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return 조회한 TN_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	HashMap selectMummSctnSrvyDtaTab1(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
		
	/**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    MummSctnSrvyDtaVO selectMummSctnSrvyDta(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA)상세를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return 조회한 TN_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab2(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab3ListPage(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 개수를 조회한다.
	 * @return int형 
	 */
	int selectMummSctnSrvyDtaTab3ListPageTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO);
		
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 총 갯수
	 * @exception
	 */
	int selectMummSctnSrvyDtaListTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO);

	/**
	 * 통합정보조회 선택 cell의 산정 데이터를 조회한다
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	List<MummSctnSrvyDtaVO> prcClacPredctEvl(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	
	/**
	 * 통합정보조회 선택 cell의 10년치 예측정보를 조회한다.
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	List<MummSctnSrvyDtaVO> prcClacPredctEvlPredct(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	
	// ========================================================================================================== //
	// 통합정보조회 - 조사자료 : 선택한 셀로 섹션 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    MummSctnSrvyDtaVO mummSctnSrvyDtaSctnByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
	// 통합정보조회 - 조사자료 : 선택한 섹션의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    List<MummSctnSrvyDtaVO> mummSctnSrvyDtaSctnList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    // 통합정보조회 - 조사자료 : 선택한 셀의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    // 통합정보조회 - 조사정보 : 선택한 섹션의 셀 정보 조회. (섬네일 보여주기 위한 정보)
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellInfo(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 조회.
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaListByItgrtn(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;

    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 개수 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    int selectMummSctnSrvyDtaListByItgrtnCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO);
    
    /**
     * 통계 > 포장상태 평가 > 노선별통계 > 데이터조회
     * @author    : JOY
     * @date      : 2017. 11. 17.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    List<MummSctnSrvyDtaVO> mummRoutCntStatsGPCI(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    /**
     * 통계 > 포장상태 평가 > 노선별통계 > 엑셀
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    List mummRoutCntStatsExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    /**
     * 통계 > 포장상태 평가 > 관리기관별 > 데이터조회
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    List<MummSctnSrvyDtaVO> mummDeptCntStats(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    /**
     * 통계 > 포장상태 평가 > 관리기관별통계 > 엑셀
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    List<MummSctnSrvyDtaVO> mummDeptCntStatsExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    /**
     * 통합정보조회 > 포장상태평가조회 > 엑셀저장
     * @author    : JOY
     * @date      : 2018. 9. 12.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 VO
     * @return    : List
     * @exception : Exception
     */
    List integratedListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;

    MummSctnSrvyDtaVO selectMummSctnSrvyDtaByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    
    
    /**
     * 소성변형, 종단평탄성 정보
     * */
    public Object getrdairival(Object object) throws Exception;
}