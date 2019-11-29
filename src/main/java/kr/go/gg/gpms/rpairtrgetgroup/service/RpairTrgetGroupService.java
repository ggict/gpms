package kr.go.gg.gpms.rpairtrgetgroup.service;

import java.util.List;

import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;

/**
 * 보수_대상_항목_그룹
 * TN_RPAIR_TRGET_GROUP
 *
 * @Class Name : RpairTrgetGroupService.java
 * @Description : RpairTrgetGroup Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface RpairTrgetGroupService {

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 목록
     * @exception Exception
     */
    List<RpairTrgetGroupVO> selectRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    int selectRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception ;

    /**
     * 보수대상 우선순위 저장 처리
     */
    int updatePRIORT(List<RpairTrgetGroupVO> lvo, RpairTrgetGroupVO vo) throws Exception ;

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)의 구간 셀 ID 목록을 조회한다.
     * @param rpairTrgetGroupVO
     * @return
     * @throws Exception
     */
    List<RpairTrgetGroupVO> selectRpairTrgetGroupCELLList(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception ;


    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 대비 포장공사 진행 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 목록
     * @exception Exception
     */
    List<RpairTrgetGroupVO> selectCntrwkByRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 대비 포장공사 진행 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    int selectCntrwkByRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception ;














	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 등록한다.
	 * @param rpairTrgetGroupVO - 등록할 정보가 담긴 RpairTrgetGroupVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 수정한다.
	 * @param rpairTrgetGroupVO - 수정할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 삭제한다.
	 * @param rpairTrgetGroupVO - 삭제할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	int deleteRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	RpairTrgetGroupVO selectRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;

	/**
	 * 임시 선택을 반전 처리한다.
	 * @param rpairTrgetGroupVO
	 * @return
	 * @throws Exception
	 */
	int updateToggleTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception;
	/**
	 * 임시 선택을 초기화 처리한다.
	 * @param rpairTrgetGroupVO
	 * @return
	 * @throws Exception
	 */
	int updateInitTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception ;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO
	 * @return
	 * @throws Exception
	 */
	List<RpairTrgetGroupVO> selectRpairTrgetDeptStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception ;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 보수공법 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO
	 * @return
	 * @throws Exception
	 */
	List<RpairTrgetGroupVO> selectRpairTrgetMethodStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception ;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 행정구역 별 통계 목록을 조회한다.
	 * @param rpairTrgetGroupVO
	 * @return
	 * @throws Exception
	 */
	List<RpairTrgetGroupVO> selectRpairTrgetAdminStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception ;
	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 집계정보를  조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	RpairTrgetGroupVO selectRpairTrgetGroupListTotalSummary(RpairTrgetGroupVO rpairTrgetGroupVO)throws Exception ;
	
	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 노선별 통계 목록을 조회한다. (2019 신규)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	List<RpairTrgetGroupVO> selectRpairRoutLenStats(RpairTrgetGroupVO rpairTrgetGroupVO)throws Exception ;
	
	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 노선별 통계 엑셀 다운로드 (2019 신규)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	List<RpairTrgetGroupVO> selectRpairRoutLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO)throws Exception ;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관별 통계 목록을 조회한다. (2019 신규)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	List<RpairTrgetGroupVO> selectRpairDeptLenStats(RpairTrgetGroupVO rpairTrgetGroupVO)throws Exception ;
	
	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 관리기관별 통계 엑셀 다운로드 (2019 신규)
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	List<RpairTrgetGroupVO> selectRpairDeptLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO)throws Exception ;
	

}

