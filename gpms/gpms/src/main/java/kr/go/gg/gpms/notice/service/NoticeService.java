package kr.go.gg.gpms.notice.service;

import java.util.List;

import kr.go.gg.gpms.notice.service.model.NoticeVO;

/**
 * 공지사항
 * TN_NOTICE
 *
 * @Class Name : NoticeService.java
 * @Description : Notice Business class
 * @Modification Information
 *  
 *  Copyright (C)  All right reserved.
 */
public interface NoticeService {
	
	/**
	 * 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	List<NoticeVO> selectNoticeList(NoticeVO noticeVO) throws Exception;
	
	/**
	 * 공지사항(TN_NOTICE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 총 갯수
	 * @exception
	 */
	int selectNoticeListTotalCount(NoticeVO noticeVO);
	
	/**
	 * 공지사항(TN_NOTICE)을 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertNotice(NoticeVO noticeVO) throws Exception;
	
	/**
	 * 공지사항(TN_NOTICE)을  상세 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return 조회한 TN_NOTICE
	 * @exception Exception
	 */
	NoticeVO selectNoticeView(NoticeVO noticeVO) throws Exception;
	
	/**
	 * 공지사항(TN_NOTICE)을 삭제한다.
	 * @param noticeVO - 삭제할 정보가 담긴 noticeVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteNotice(NoticeVO noticeVO) throws Exception;

	/**
	 * 공지사항(TN_NOTICE)을 수정한다.
	 * @param noticeVO - 수정할 정보가 담긴 noticeVO
	 * @return int형
	 * @exception Exception
	 */
	int updateNotice(NoticeVO noticeVO) throws Exception;

	/**
	 * 공지사항(TN_NOTICE) 조회수를 수정한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return int형 
	 * @exception Exception
	 */
	int updateNoticeRdcnt(NoticeVO noticeVO) throws Exception;
	
	/**
	 * 메인 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	List<NoticeVO> selectMainNoticeList(NoticeVO noticeVO) throws Exception;
	
	/**
	 * @throws Exception 
	 * 메인 공지사항(TN_NOTICE) 목록 전체 건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 총 갯수
	 * @exception
	 */
	int selectMainNoticeListTotalCount(NoticeVO noticeVO);
	
	/**
	 * 시스템사용자(TN_SYS_USER)을 user_id로 조회한다.
	 * @param sysUserVO - 조회할 정보가 담긴 SysUserVO
	 * @return 조회한 TN_SYS_USER
	 * @exception Exception
	 *//*
	SysUserVO selectSysUserByID(SysUserVO sysUserVO) throws Exception;

	
	*/
}

