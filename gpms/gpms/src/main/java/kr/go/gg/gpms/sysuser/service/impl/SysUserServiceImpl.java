package kr.go.gg.gpms.sysuser.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.SysUserVO;

/**
 * 시스템사용자
 *
 * @Class Name : SysUserServiceImpl.java
 * @Description : SysUser Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("sysUserService")
public class SysUserServiceImpl extends AbstractServiceImpl implements SysUserService {

	@Resource(name = "sysUserDAO")
	private SysUserDAO sysUserDAO;

	//@Resource(name="SysUserIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 시스템사용자(TN_SYS_USER)을 등록한다.
	 * @param sysUserVO - 등록할 정보가 담긴 SysUserVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSysUser(SysUserVO sysUserVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//sysUserVO.setId(id);

		return sysUserDAO.insertSysUser( sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 수정한다.
	 * @param sysUserVO - 수정할 정보가 담긴 SysUserVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSysUser(SysUserVO sysUserVO) throws Exception {
		return sysUserDAO.updateSysUser( sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 삭제한다.
	 * @param sysUserVO - 삭제할 정보가 담긴 SysUserVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteSysUser(SysUserVO sysUserVO) throws Exception {
		return sysUserDAO.deleteSysUser( sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER)을 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 */
	public SysUserVO selectSysUser(SysUserVO sysUserVO) throws Exception {
		SysUserVO resultVO = sysUserDAO.selectSysUser( sysUserVO);

		return resultVO;
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TN_SYS_USER 목록
	 * @exception Exception
	 */
	public List<SysUserVO> selectSysUserList(SysUserVO sysUserVO) throws Exception {
		return sysUserDAO.selectSysUserList( sysUserVO);
	}

	/**
	 * 시스템사용자(TN_SYS_USER) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TN_SYS_USER 총 갯수
	 * @exception
	 */
	public int selectSysUserListTotalCount(SysUserVO sysUserVO) {
		return sysUserDAO.selectSysUserListTotalCount( sysUserVO);
	}

	@Override
	public SysUserVO selectSysUserByID(SysUserVO sysUserVO) throws Exception {
		SysUserVO resultVO = sysUserDAO.selectSysUserByID( sysUserVO);

		return resultVO;
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public List<SysUserVO> selectSysUserLogList(SysUserVO sysUserVO) throws Exception {
		return sysUserDAO.selectSysUserLogList( sysUserVO);
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 총 갯수
	 * @exception
	 */
	public int selectSysUserLogListTotalCount(SysUserVO sysUserVO) {
		return sysUserDAO.selectSysUserLogListTotalCount( sysUserVO);
	}

	/**
	 *시스템 접속 로그(TL_SYS_CONNECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 sysUserVO
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public List selectSysUserLogListExcel(SysUserVO sysUserVO) throws Exception {
		return sysUserDAO.selectSysUserLogListExcel( sysUserVO);
	}


	/**
	 *시스템 접속 로그(TL_SYS_CONNECT)의 CREAT_DT 최대값, 최소값을 조회한다.
	 * @param
	 * @return TL_SYS_CONNECT 목록
	 * @exception Exception
	 */
	public SysUserVO selectMinMaxYear() throws Exception {
		return sysUserDAO.selectMinMaxYear();
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
        return sysUserDAO.insertSysUserChgAuth(sysUserVO);
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
    public List<SysUserVO> selectSysUserChgAuthList(SysUserVO sysUserVO) throws Exception {
        return sysUserDAO.selectSysUserChgAuthList( sysUserVO);
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
        return sysUserDAO.selectSysUserChgAuthListCnt(sysUserVO);
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
        return sysUserDAO.selectSysUserChg(sysUserVO);
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
        return sysUserDAO.deleteSysUserChg(sysUserVO);
    }
    
}
