


package kr.go.gg.gpms.notice.service.impl;

import java.util.List;

import kr.go.gg.gpms.notice.service.model.NoticeVO;

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

@Repository("noticeDAO")
public class NoticeDAO extends BaseDAO {
	
	/**
	 * 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) throws Exception {
		return (List<NoticeVO>)list("noticeDAO.selectNoticeList", noticeVO);
	}

	/**
	 * 공지사항(TN_NOTICE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 총 갯수
	 * @exception
	 */
	public int selectNoticeListTotalCount(NoticeVO noticeVO) {
		return (Integer) select("noticeDAO.selectNoticeListTotalCount", noticeVO);
	}
	

	/**
	 * 공지사항(TN_NOTICE)을 등록한다.
	 * @param noticeVO - 등록할 정보가 담긴 NoticeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertNotice(NoticeVO noticeVO) throws Exception {
		return (String) insert("noticeDAO.insertNotice", noticeVO);
	}

	/**
	 * 공지사항(TN_NOTICE)을  상세 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return 조회한 TN_NOTICE
	 * @exception Exception
	 */
	public NoticeVO selectNoticeView(NoticeVO noticeVO) throws Exception {
		return (NoticeVO) select("noticeDAO.selectNoticeView", noticeVO);
	}
	
	/**
	 * 공지사항(TN_NOTICE)을 삭제한다.
	 * @param noticeVO - 삭제할 정보가 담긴 noticeVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteNotice(NoticeVO noticeVO) throws Exception {
		return delete("noticeDAO.deleteNotice", noticeVO);
	}

	
	
	/**
	 * 공지사항(TN_NOTICE)을 수정한다.
	 * @param noticeVO - 삭제할 정보가 담긴 noticeVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		return update("noticeDAO.updateNotice", noticeVO);
	}

	
	/**
	 * 공지사항(TN_NOTICE)을 수정한다.
	 * @param noticeVO - 삭제할 정보가 담긴 noticeVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateNoticeRdcnt(NoticeVO noticeVO) throws Exception {
		return update("noticeDAO.updateNoticeRdcnt", noticeVO);
	}
	
	/**
	 * 메인 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<NoticeVO> selectMainNoticeList(NoticeVO noticeVO) throws Exception {
		return (List<NoticeVO>)list("noticeDAO.selectMainNoticeList", noticeVO);
	}

	/**
	 * 메인 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	public int selectMainNoticeListTotalCount(NoticeVO noticeVO) {
		return (Integer) select("noticeDAO.selectMainNoticeListTotalCount", noticeVO);
	}
	
	/**
	 * 시스템사용자(TN_SYS_USER)을 user_id로 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 *//*
	public SysUserVO selectSysUserByID(SysUserVO sysUserVO) {
		return (SysUserVO) select("sysUserDAO.selectSysUserByID", sysUserVO);
	}*/
		
}
