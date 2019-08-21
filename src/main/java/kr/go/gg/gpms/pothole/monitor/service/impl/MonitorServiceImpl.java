package kr.go.gg.gpms.pothole.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pothole.monitor.service.MonitorService;
import kr.go.gg.gpms.pothole.monitor.service.model.MonitorVO;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;

/**
 * Monitor 단원 관리
 * TN_MNTRNG_MBR
 *
 * @Class Name : MonitorServiceImpl.java
 * @Description : Monitor Business Implement class
 * @Modification Information
 *
 * @author yyk
 * @since 2018-01-03
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("monitorService")
public class MonitorServiceImpl extends AbstractServiceImpl implements MonitorService {

	@Resource(name = "monitorDAO")
	private MonitorDAO monitorDAO;

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 목록을 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	@Override
	public List<MonitorVO> selectMntrngMbrList(MonitorVO monitorVO) throws Exception {
		return monitorDAO.selectMntrngMbrList(monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 총 수를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 총 갯수
	 * @exception
	 */
	@Override
	public int selectMntrngMbrListTotalCount(MonitorVO monitorVO) throws Exception {
		return monitorDAO.selectMntrngMbrListTotalCount(monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR) 상세정보를 조회한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 상세정보
	 * @exception
	 */
	public MonitorVO selectMntrngMbrDetail(MonitorVO monitorVO) throws Exception{
		return monitorDAO.selectMntrngMbrDetail(monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	@Override
	public int updateMntrngMbr(MonitorVO monitorVO) throws Exception {
		return monitorDAO.updateMntrngMbr(monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 수정한다. (다중 위촉수정)
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	@Override
	public int updateMntrngMbrEntrst(MonitorVO monitorVO) throws Exception {
		return monitorDAO.updateMntrngMbrEntrst(monitorVO);
	}


	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 등록한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public String insertMntrngMbr(MonitorVO monitorVO) throws Exception {
		return monitorDAO.insertMntrngMbr(monitorVO);
	}

	/**
	 * 모니터링 단원(TN_MNTRNG_MBR)을 삭제한다.
	 * @param monitorVO - 조회할 정보가 담긴 MonitorVO
	 * @return TN_MNTRNG_MBR 목록
	 * @exception Exception
	 */
	public int deleteMntrngMbr(MonitorVO monitorVO) throws Exception {
		return monitorDAO.deleteMntrngMbr(monitorVO);
	}
}
