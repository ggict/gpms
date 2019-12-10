package kr.go.gg.gpms.rpairtrgetslctn.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Async;

import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

/**
 * 보수_대상_선정
 * TN_RPAIR_TRGET_SLCTN
 *
 * @Class Name : RpairTrgetSlctnService.java
 * @Description : RpairTrgetSlctn Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface RpairTrgetSlctnService {

    /**
     * 보수대상선정이력 목록을 조회
     */
    List<RpairTrgetSlctnVO> selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

    /**
     * 보수대상선정시작 처리
     */
    void addRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

    /**
     * 보수대상선정시작 처리(보수_대상_선정 삭제)
     */
    int deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수대상선정시작 처리(보수_대상_선정 등록)
	 */
	String insertRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수대상선정시작 처리(보수_대상_항목_그룹 등록)
	 */
	@Async
	void procRepairTarget(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
     * 보수대상선정시작 처리(보수_대상_항목_그룹 등록)
     */
    int selectRpairTrgetSlctnSlctnYearListCnt(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

    List<RpairTrgetSlctnVO> selectRpairTrgetSlctnRouteCodeList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;












	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 조회한 TN_RPAIR_TRGET_SLCTN
	 * @exception Exception
	 */
	RpairTrgetSlctnVO selectRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 총 갯수
	 * @exception
	 */
	int selectRpairTrgetSlctnListTotalCount(RpairTrgetSlctnVO rpairTrgetSlctnVO);

	/**
	 * 보수대상선정 집계를 진행한다.
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procClacprcRepairTargets(RpairTrgetSlctnVO rpairTrgetSlctnVO)throws Exception;

	/**
	 * 보수대상선정 범위내 선정을 진행한다.
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procRepairTargetRangeSelect(RpairTrgetSlctnVO rpairTrgetSlctnVO)throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 범위내 선정을 위한 수정을 한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRangeSelection(RpairTrgetSlctnVO rpairTrgetSlctnVO);
	/**
	 * 연속구간 연결 및 보수공법 재선정
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procRepairTargetRangeString(RpairTrgetSlctnVO rpairTrgetSlctnVO)throws Exception;
	/**
	 * 예산범위내 선정
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procRepairTargetBudgetRate(RpairTrgetSlctnVO rpairTrgetSlctnVO)throws Exception;

	/**
	 * 보수대상 선정 완료 처리
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procRepairTargetComplete(RpairTrgetSlctnVO rpairTrgetSlctnVO)  throws Exception;
}

