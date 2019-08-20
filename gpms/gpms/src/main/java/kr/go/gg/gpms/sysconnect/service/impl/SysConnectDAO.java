


package kr.go.gg.gpms.sysconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.sysconnect.service.model.SysConnectVO;

/**
 * 시스템접속로그
 *
 * @Class Name : SysConnectDAO.java
 * @Description : SysConnect DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("sysConnectDAO")
public class SysConnectDAO extends BaseDAO {

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 등록한다.
	 * @param sysConnectVO - 등록할 정보가 담긴 SysConnectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return (String) insert("sysConnectDAO.insertSysConnect", sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 수정한다.
	 * @param sysConnectVO - 수정할 정보가 담긴 SysConnectVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return update("sysConnectDAO.updateSysConnect", sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 삭제한다.
	 * @param sysConnectVO - 삭제할 정보가 담긴 SysConnectVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return delete("sysConnectDAO.deleteSysConnect", sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT)을 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return 조회한 TL_SYS_CONNECT
	 * @exception Exception
	 */
	public SysConnectVO selectSysConnect(SysConnectVO sysConnectVO) throws Exception {
		return (SysConnectVO) select("sysConnectDAO.selectSysConnect", sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysConnectVO> selectSysConnectList(SysConnectVO sysConnectVO) throws Exception {
		return (List<SysConnectVO>)list("sysConnectDAO.selectSysConnectList", sysConnectVO);
	}

	/**
	 * 시스템접속로그(TL_SYS_CONNECT) 총 갯수를 조회한다.
	 * @param sysConnectVO - 조회할 정보가 담긴 SysConnectVO
	 * @return TL_SYS_CONNECT 총 갯수
	 * @exception
	 */
	public int selectSysConnectListTotalCount(SysConnectVO sysConnectVO) {
		return (Integer) select("sysConnectDAO.selectSysConnectListTotalCount", sysConnectVO);
	}

}
