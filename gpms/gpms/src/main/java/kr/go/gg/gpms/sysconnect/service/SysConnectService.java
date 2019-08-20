package kr.go.gg.gpms.sysconnect.service;

import java.util.List;

import kr.go.gg.gpms.sysconnect.service.model.SysConnectDefaultVO;
import kr.go.gg.gpms.sysconnect.service.model.SysConnectVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템접속로그
 * TL_SYS_CONNECT
 *
 * @Class Name : SysConnectService.java
 * @Description : SysConnect Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SysConnectService {

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 등록한다.
	 * @param sysConnectVO - 등록할 정보가 담긴 SysConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSysConnect(SysConnectVO sysConnectVO) throws Exception;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 수정한다.
	 * @param sysConnectVO - 수정할 정보가 담긴 SysConnectVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSysConnect(SysConnectVO sysConnectVO) throws Exception;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 삭제한다.
	 * @param sysConnectVO - 삭제할 정보가 담긴 SysConnectVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSysConnect(SysConnectVO sysConnectVO) throws Exception;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return 조회한 TL_SYS_CONNECT
	 * @exception Exception
	 */
	SysConnectVO selectSysConnect(SysConnectVO sysConnectVO) throws Exception;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysConnectVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	List<SysConnectVO> selectSysConnectList(SysConnectVO sysConnectVO) throws Exception;

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysConnectVO
	 * @return TL_SYS_CONNECT 총 갯수
	 * @exception
	 */
	int selectSysConnectListTotalCount(SysConnectVO sysConnectVO);

}

