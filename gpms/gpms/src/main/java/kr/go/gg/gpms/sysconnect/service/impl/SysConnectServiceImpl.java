package kr.go.gg.gpms.sysconnect.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.sysconnect.service.SysConnectService;
import kr.go.gg.gpms.sysconnect.service.model.SysConnectVO;

/**
 * 시스템접속로그
 *
 * @Class Name : SysConnectServiceImpl.java
 * @Description : SysConnect Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("sysConnectService")
public class SysConnectServiceImpl extends AbstractServiceImpl implements SysConnectService {

	@Resource(name = "sysConnectDAO")
	private SysConnectDAO sysConnectDAO;

	//@Resource(name="SysConnectIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 등록한다.
	 * @param sysConnectVO - 등록할 정보가 담긴 SysConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSysConnect(SysConnectVO sysConnectVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//sysConnectVO.setId(id);

		return sysConnectDAO.insertSysConnect( sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 수정한다.
	 * @param sysConnectVO - 수정할 정보가 담긴 SysConnectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return sysConnectDAO.updateSysConnect( sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 삭제한다.
	 * @param sysConnectVO - 삭제할 정보가 담긴 SysConnectVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return sysConnectDAO.deleteSysConnect( sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return 조회한 TL_SYS_CONNECT
	 * @exception Exception
	 */
	public SysConnectVO selectSysConnect(SysConnectVO sysConnectVO) throws Exception {
		SysConnectVO resultVO = sysConnectDAO.selectSysConnect( sysConnectVO);
		 
		return resultVO;
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysConnectVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public List<SysConnectVO> selectSysConnectList(SysConnectVO sysConnectVO) throws Exception {
		return sysConnectDAO.selectSysConnectList( sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysConnectVO
	 * @return TL_SYS_CONNECT 총 갯수
	 * @exception
	 */
	public int selectSysConnectListTotalCount(SysConnectVO sysConnectVO) {
		return sysConnectDAO.selectSysConnectListTotalCount( sysConnectVO);
	}

}
