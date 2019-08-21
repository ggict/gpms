package kr.go.gg.gpms.rpairtrgetslctn.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnDefaultVO;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 등록한다.
	 * @param rpairTrgetSlctnVO - 등록할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 삭제한다.
	 * @param rpairTrgetSlctnVO - 삭제할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 조회한 TN_RPAIR_TRGET_SLCTN
	 * @exception Exception
	 */
	RpairTrgetSlctnVO selectRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 목록
	 * @exception Exception
	 */
	List<RpairTrgetSlctnVO> selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception;

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
	 * 보수_대상_선정 한 트랜잭션으로 처리
	 * @param rpairTrgetSlctnVO
	 * @return
	 */
	HashMap procRepairTarget(RpairTrgetSlctnVO rpairTrgetSlctnVO)throws Exception;

	/**
	 * 보수대상 선정 완료 처리
	 * @param rpairTrgetSlctnVO
	 * @return
	 * @throws Exception
	 */
	HashMap procRepairTargetComplete(RpairTrgetSlctnVO rpairTrgetSlctnVO)  throws Exception;
}

