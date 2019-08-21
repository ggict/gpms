package kr.go.gg.gpms.sysuser.service;

import java.util.List;

import kr.go.gg.gpms.sysuser.service.model.SysUserVO;

/**
 * 시스템사용자
 * TN_SYS_USER
 *
 * @Class Name : SysUserService.java
 * @Description : SysUser Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface SysUserService {

	/**
	 * 시스템사용자(TN_SYS_USER)을 등록한다.
	 * @param sysUserVO - 등록할 정보가 담긴 SysUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSysUser(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템사용자(TN_SYS_USER)을 수정한다.
	 * @param sysUserVO - 수정할 정보가 담긴 SysUserVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSysUser(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템사용자(TN_SYS_USER)을 삭제한다.
	 * @param sysUserVO - 삭제할 정보가 담긴 SysUserVO
	 * @return int형
	 * @exception Exception
	 */
	int deleteSysUser(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템사용자(TN_SYS_USER)을 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 */
	SysUserVO selectSysUser(SysUserVO sysUserVO) throws Exception;


	/**
	 * 시스템사용자(TN_SYS_USER)을 user_id로 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 */
	SysUserVO selectSysUserByID(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TN_SYS_USER 목록
	 * @exception Exception
	 */
	List<SysUserVO> selectSysUserList(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템사용자(TN_SYS_USER) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TN_SYS_USER 총 갯수
	 * @exception
	 */
	int selectSysUserListTotalCount(SysUserVO sysUserVO);

	/**
	 * 시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	List<SysUserVO> selectSysUserLogList(SysUserVO sysUserVO) throws Exception;

	/**
	 * 시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 총 갯수
	 * @exception
	 */
	int selectSysUserLogListTotalCount(SysUserVO sysUserVO);

	/**
	 * 시스템 접속 로그(TL_SYS_CONNECT) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	List selectSysUserLogListExcel(SysUserVO sysUserVO) throws Exception;


	/**
	 *시스템 접속 로그(TL_SYS_CONNECT)의 CREAT_DT 최대값, 최소값을 조회한다.
	 * @param
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	SysUserVO selectMinMaxYear() throws Exception ;

	/**
     * 사용자 권한 변경 요청
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : String
     * @exception : Exception
     */
	String insertSysUserChgAuth(SysUserVO sysUserVO) throws Exception ;
	
	/**
     * 권한 변경 요청 목록 조회
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : List<SysUserVO>
     * @exception : Exception
     */
    public List<SysUserVO> selectSysUserChgAuthList(SysUserVO sysUserVO) throws Exception ;

    /**
     * 권한 변경 요청 목록 개수 조회
     * @author    : JOY
     * @date      : 2018. 3. 12.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : List<SysUserVO>
     * @exception : Exception
     */
    int selectSysUserChgAuthListCnt(SysUserVO sysUserVO) throws Exception ;
    
    /**
     * 권한 변경 요청자 조회
     * @author    : JOY
     * @date      : 2018. 3. 13.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : SysUserVO
     * @exception : Exception
     */
    SysUserVO selectSysUserChg(SysUserVO sysUserVO) throws Exception ;
    
    /**
     * Temp Table 삭제
     * @author    : JOY
     * @date      : 2018. 3. 13.
     * 
     * @param     : SysUserVO - 조회할 정보가 담긴 SysUserVO
     * @return    : int
     * @exception : Exception
     */
    int deleteSysUserChg(SysUserVO sysUserVO) throws Exception;
}

