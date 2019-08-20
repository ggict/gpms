package kr.go.gg.gpms.pothole.sttst.service;

import java.util.List;

import kr.go.gg.gpms.pothole.sttst.service.model.SttstVO;

/**
 * 통계 Service
 * @Classname : SttstService.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
public interface SttstService {

	/**
	 * 날짜 기준 처리현황 목록을 조회한다.
	 * @author    : YYK
	 * @date      : 2018. 1. 30.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : List
	 * @exception : Exception
	 */
	public List<SttstVO> selectDatePeriodListRest(SttstVO sttstVO) throws Exception ;

	/**
	 * 날짜 기준 처리현황 목록 갯수를 조회한다.
	 * @author    : YYK
	 * @date      : 2018. 1. 30.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : int
	 * @exception : Exception
	 */
	public int selectDatePeriodListRestTotalCount(SttstVO sttstVO) throws Exception ;

	/**
     * 날짜 기준 처리현황 목록을 조회한다.
     * @author    : YYK
     * @date      : 2018. 1. 30.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestExcel(SttstVO sttstVO) throws Exception ;


	/**
     * 날짜 기준(연간) 처리현황 목록을 조회한다.
     * @author    : YYK
     * @date      : 2018. 2. 2.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectDatePeriodListRestYear(SttstVO sttstVO) throws Exception ;

    /**
     * 날짜 기준(연간) 처리현황 목록 갯수를 조회한다.
     * @author    : YYK
     * @date      : 2018. 2. 2.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestYearTotalCount(SttstVO sttstVO) throws Exception ;
    /**
     * 날짜 기준(연간) 처리현황 목록을 조회한다. (엑셀 저장)
     * @author    : YYK
     * @date      : 2018. 2. 2.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    public List<SttstVO> selectDatePeriodListRestYearExcel(SttstVO sttstVO) throws Exception ;

    /**
     * 데이터 최소, 최대 년도를 조회한다.
     * @author    : JOY
     * @date      : 2018. 2. 5.
     *
     * @param     : null
     * @return    : SttstVO
     * @exception : Exception
     */
    SttstVO selectMinMaxYear() throws Exception ;

	/**
	 * 신고자기준 신고현황 목록을 조회한다.
	 * @author    : JOY
	 * @date      : 2018. 1. 25.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : List
	 * @exception : Exception
	 */
	List<SttstVO> selectBsnmPeriodListRest(SttstVO sttstVO) throws Exception ;

	/**
	 * 신고자기준 신고현황 목록 갯수를 조회한다.
	 * @author    : JOY
	 * @date      : 2018. 1. 25.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : int
	 * @exception : Exception
	 */
	int selectBsnmPeriodListRestTotalCount(SttstVO sttstVO) throws Exception ;

	/**
     * 신고자기준 신고현황 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 25.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectBsnmPeriodListRestExcel(SttstVO sttstVO) throws Exception ;

	/**
	 * 날짜 기준 처리현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 11.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : List
	 * @exception : Exception
	 */
	public List<SttstVO> selectDatePeriodListRestPthMode(SttstVO sttstVO) throws Exception ;

	/**
	 * 날짜 기준 처리현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 12.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : int
	 * @exception : Exception
	 */
	public int selectDatePeriodListRestPthModeTotalCount(SttstVO sttstVO) throws Exception ;

	/**
     * 날짜 기준 처리현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 15.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestExcelPthMode(SttstVO sttstVO) throws Exception ;

    /**
     * 날짜 기준(연간) 처리현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectDatePeriodListRestPthModeYear(SttstVO sttstVO) throws Exception ;

    /**
     * 날짜 기준(연간) 처리현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestYearTotalPthModeCount(SttstVO sttstVO) throws Exception ;

    /**
     * 날짜 기준(연간) 처리현황 목록을 조회한다. (엑셀 저장)
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    public List<SttstVO> selectDatePeriodListRestYearPthModeExcel(SttstVO sttstVO) throws Exception ;

	/**
	 * 신고자기준 신고현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : List
	 * @exception : Exception
	 */
	List<SttstVO> selectBsnmPeriodListPthModeRest(SttstVO sttstVO) throws Exception ;

	/**
	 * 신고자기준 신고현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
	 *
	 * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
	 * @return    : int
	 * @exception : Exception
	 */
	int selectBsnmPeriodListRestTotalPthModeCount(SttstVO sttstVO) throws Exception ;

	/**
     * 신고자기준 신고현황 목록을 조회한다.
 	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectBsnmPeriodListRestPthModeExcel(SttstVO sttstVO) throws Exception ;
}

