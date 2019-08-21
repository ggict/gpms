


package kr.go.gg.gpms.sysuser.service.impl;

import java.sql.SQLException;
import java.util.List;

import kr.go.gg.gpms.sysuser.service.model.SysUserVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 시스템사용자
 *
 * @Class Name : SysUserDAO.java
 * @Description : SysUser DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("sysUserDAO")
public class SysUserDAO extends BaseDAO {

	/**
	 * 시스템사용자(TN_SYS_USER)을 등록한다.
	 * @param sysUserVO - 등록할 정보가 담긴 SysUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSysUser(SysUserVO sysUserVO) throws Exception {
		return (String) insert("sysUserDAO.insertSysUser", sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 수정한다.
	 * @param sysUserVO - 수정할 정보가 담긴 SysUserVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSysUser(SysUserVO sysUserVO) throws Exception {
		return update("sysUserDAO.updateSysUser", sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 삭제한다.
	 * @param sysUserVO - 삭제할 정보가 담긴 SysUserVO
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteSysUser(SysUserVO sysUserVO) throws Exception {
		return update("sysUserDAO.deleteSysUser", sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 */
	public SysUserVO selectSysUser(SysUserVO sysUserVO) throws Exception {
		return (SysUserVO) select("sysUserDAO.selectSysUser", sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return TN_SYS_USER 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysUserVO> selectSysUserList(SysUserVO sysUserVO) throws Exception {
		return (List<SysUserVO>)list("sysUserDAO.selectSysUserList", sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 총 갯수를 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return TN_SYS_USER 총 갯수
	 * @exception
	 */
	public int selectSysUserListTotalCount(SysUserVO sysUserVO) {
		return (Integer) select("sysUserDAO.selectSysUserListTotalCount", sysUserVO);
	}
	/**
	 * 시스템사용자(TN_SYS_USER)을 user_id로 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 */
	public SysUserVO selectSysUserByID(SysUserVO sysUserVO) {
		return (SysUserVO) select("sysUserDAO.selectSysUserByID", sysUserVO);
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysUserVO> selectSysUserLogList(SysUserVO sysUserVO) throws Exception {
		return (List<SysUserVO>)list("sysUserDAO.selectSysUserLogList", sysUserVO);
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public int selectSysUserLogListTotalCount(SysUserVO sysUserVO) {
		return (Integer) select("sysUserDAO.selectSysUserLogListTotalCount", sysUserVO);
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectSysUserLogListExcel(SysUserVO sysUserVO) throws Exception {
		return (List)list("sysUserDAO.selectSysUserLogListExcel", sysUserVO);
	}


	/**
	 *시스템 접속 로그(TL_SYS_CONNECT)의 CREAT_DT 최대값, 최소값을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public SysUserVO selectMinMaxYear() throws Exception {
		return (SysUserVO) select("sysUserDAO.selectMinMaxYear", null);
	}
	
	/**
     * 사용자 권한 변경 요청
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : int
     * @exception : Exception
     */
    public String insertSysUserChgAuth(SysUserVO sysUserVO) throws Exception {
        return (String) insert("sysUserDAO.insertSysUserChgAuth", sysUserVO);
    }
    
    /**
     * 권한 변경 요청 목록 조회
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : List<SysUserVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SysUserVO> selectSysUserChgAuthList(SysUserVO sysUserVO) throws Exception {
        return (List<SysUserVO>) list("sysUserDAO.selectSysUserChgAuthList", sysUserVO);
    }
    
    /**
     * 권한 변경 요청 목록 개수 조회
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : List<SysUserVO>
     * @exception : Exception
     */
    public int selectSysUserChgAuthListCnt(SysUserVO sysUserVO) throws Exception {
        return (Integer) select("sysUserDAO.selectSysUserChgAuthListCnt", sysUserVO);
    }
    
    /**
     * 권한 변경 요청자 조회
     * @author    : JOY
     * @date      : 2018. 3. 13.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : SysUserVO
     * @exception : Exception
     */
    public SysUserVO selectSysUserChg(SysUserVO sysUserVO) throws Exception {
        return (SysUserVO) select("sysUserDAO.selectSysUserChg", sysUserVO);
    }
    
    /**
     * Temp Table 삭제
     * @author    : JOY
     * @date      : 2018. 3. 13.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : int
     * @exception : Exception
     */
    public int deleteSysUserChg(SysUserVO sysUserVO) throws Exception {
        return delete("sysUserDAO.deleteSysUserChg", sysUserVO);
    }

}
