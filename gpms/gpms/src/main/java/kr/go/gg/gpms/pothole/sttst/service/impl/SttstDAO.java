package kr.go.gg.gpms.pothole.sttst.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.gg.gpms.pothole.sttst.service.model.SttstVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

@Repository("sttstDAO")
public class SttstDAO extends BaseDAO {

	/**
     * 날짜 기준 처리현황 목록을 조회한다.
     * @author    : YYK
     * @date      : 2018. 1. 30.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectDatePeriodListRest(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectDatePeriodListRest", sttstVO);
    }

    /**
     * 날짜 기준 처리현황 목록 갯수를 조회한다.
     * @author    : YYK
     * @date      : 2018. 1. 30.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestTotalCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectDatePeriodListRestTotalCount", sttstVO);
    }

    /**
     * 날짜 기준 처리현황 목록을 조회한다. (엑셀 저장)
     * @author    : YYK
     * @date      : 2018. 1. 30.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestExcel(SttstVO sttstVO) throws Exception {
        return (List)list("sttstDAO.selectDatePeriodListRestExcel", sttstVO);
    }

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
    public List<SttstVO> selectDatePeriodListRestYear(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectDatePeriodListRestYear", sttstVO);
    }

    /**
     * 날짜 기준(연간) 처리현황 목록 갯수를 조회한다.
     * @author    : YYK
     * @date      : 2018. 2. 2.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestYearTotalCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectDatePeriodListRestYearTotalCount", sttstVO);
    }

    /**
     * 날짜 기준(연간) 처리현황 목록을 조회한다. (엑셀 저장)
     * @author    : YYK
     * @date      : 2018. 2. 2.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestYearExcel(SttstVO sttstVO) throws Exception {
        return (List)list("sttstDAO.selectDatePeriodListRestYearExcel", sttstVO);
    }




    /**
     * 데이터 최소, 최대 년도를 조회한다.
     * @author    : JOY
     * @date      : 2018. 2. 5.
     *
     * @param     : null
     * @return    : SttstVO
     * @exception : Exception
     */
    public SttstVO selectMinMaxYear() throws Exception {
        return (SttstVO) select("sttstDAO.selectMinMaxYear", null);
    }

	/**
     * 신고자기준 신고현황 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 25.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectBsnmPeriodListRest(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectBsnmPeriodListRest", sttstVO);
    }

    /**
     * 신고자기준 신고현황 목록 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 25.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectBsnmPeriodListRestTotalCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectBsnmPeriodListRestTotalCount", sttstVO);
    }

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
    public List selectBsnmPeriodListRestExcel(SttstVO sttstVO) throws Exception {
        return list("sttstDAO.selectBsnmPeriodListRestExcel", sttstVO);
    }

	/**
     * 날짜 기준 처리현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 11.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectDatePeriodListRestPthMode(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectDatePeriodListRestPthMode", sttstVO);
    }

    /**
     * 날짜 기준 처리현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 12.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestPthModeTotalCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectDatePeriodListRestPthModeTotalCount", sttstVO);
    }

    /**
     * 날짜 기준 처리현황 목록을 조회한다. (엑셀 저장)
	 * @author    : LHJ
	 * @date      : 2019. 7. 15.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestExcelPthMode(SttstVO sttstVO) throws Exception {
        return (List)list("sttstDAO.selectDatePeriodListRestExcelPthMode", sttstVO);
    }

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
    public List<SttstVO> selectDatePeriodListRestPthModeYear(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectDatePeriodListRestPthModeYear", sttstVO);
    }

    /**
     * 날짜 기준(연간) 처리현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectDatePeriodListRestYearTotalPthModeCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectDatePeriodListRestYearTotalPthModeCount", sttstVO);
    }

    /**
     * 날짜 기준(연간) 처리현황 목록을 조회한다. (엑셀 저장)
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectDatePeriodListRestYearPthModeExcel(SttstVO sttstVO) throws Exception {
        return (List)list("sttstDAO.selectDatePeriodListRestYearPthModeExcel", sttstVO);
    }

    /**
     * 신고자기준 신고현황 목록을 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : List
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttstVO> selectBsnmPeriodListPthModeRest(SttstVO sttstVO) throws Exception {
        return (List<SttstVO>) list("sttstDAO.selectBsnmPeriodListPthModeRest", sttstVO);
    }

    /**
     * 신고자기준 신고현황 목록 갯수를 조회한다.
	 * @author    : LHJ
	 * @date      : 2019. 7. 16.
     *
     * @param     : SttstVO - 조회할 정보가 담긴 SttstVO
     * @return    : int
     * @exception : Exception
     */
    public int selectBsnmPeriodListRestTotalPthModeCount(SttstVO sttstVO) throws Exception {
        return (Integer) select("sttstDAO.selectBsnmPeriodListRestTotalPthModeCount", sttstVO);
    }

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
    public List selectBsnmPeriodListRestPthModeExcel(SttstVO sttstVO) throws Exception {
        return list("sttstDAO.selectBsnmPeriodListRestPthModeExcel", sttstVO);
    }

}
