package kr.go.gg.gpms.pothole.monitor.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pothole.monitor.service.model.MonitorVO;

/**
 * Monitor 단원 관리
 * TN_MNTRNG_MBR
 *
 * @Class Name : MonitorDAO.java
 * @Description : Monitor DAO Class
 * @Modification Information
 *
 * @author yyk
 * @since 2018-01-03
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("monitorDAO")
public class MonitorDAO extends BaseDAO {

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 목록을 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MonitorVO> selectMntrngMbrList(MonitorVO monitorVO) throws Exception {
		return (List<MonitorVO>)list("monitorDAO.selectMntrngMbrList", monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 총 수를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 총 갯수
	 * @exception
	 */
	public int selectMntrngMbrListTotalCount(MonitorVO monitorVO) {
		return (Integer) select("monitorDAO.selectMntrngMbrListTotalCount", monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 상세정보를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 상세정보
	 * @exception
	 */
	public MonitorVO selectMntrngMbrDetail(MonitorVO monitorVO) {
		return (MonitorVO) select("monitorDAO.selectMntrngMbrDetail", monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int updateMntrngMbr(MonitorVO monitorVO) throws Exception {
		return (int) update("monitorDAO.updateMntrngMbr", monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다.(다중 위촉수정)
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int updateMntrngMbrEntrst(MonitorVO monitorVO) throws Exception {
		return (int) update("monitorDAO.updateMntrngMbrEntrst", monitorVO);
	}


	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 등록한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public String insertMntrngMbr(MonitorVO monitorVO) throws Exception {
		return (String) insert("monitorDAO.insertMntrngMbr", monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 삭제한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int deleteMntrngMbr(MonitorVO monitorVO) throws Exception {
		return (int) update("monitorDAO.deleteMntrngMbr", monitorVO);
	}



}
