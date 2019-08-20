package kr.go.gg.gpms.pothole.monitor.service;

import java.util.List;

import kr.go.gg.gpms.pothole.monitor.service.model.MonitorVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * Monitor 단원 관리
 * TN_MNTRNG_MBR
 *
 * @Class Name : MonitorService.java
 * @Description : Monitor Business class
 * @Modification Information
 *
 * @author yyk
 * @since 2018-01-03
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface MonitorService {

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 목록을 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public List<MonitorVO> selectMntrngMbrList(MonitorVO monitorVO) throws Exception;

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 총 수를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 총 갯수
	 * @exception
	 */
	public int selectMntrngMbrListTotalCount(MonitorVO monitorVO) throws Exception;

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 상세정보를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 상세정보
	 * @exception
	 */
	public MonitorVO selectMntrngMbrDetail(MonitorVO monitorVO) throws Exception;

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int updateMntrngMbr(MonitorVO monitorVO) throws Exception;

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다.(2018.11.13 다중위촉수정)
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int updateMntrngMbrEntrst(MonitorVO monitorVO) throws Exception;


	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 등록한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public String insertMntrngMbr(MonitorVO monitorVO) throws Exception ;


	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 삭제한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int deleteMntrngMbr(MonitorVO monitorVO) throws Exception ;
}

