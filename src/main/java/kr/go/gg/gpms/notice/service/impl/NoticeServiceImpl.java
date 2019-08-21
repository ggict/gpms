package kr.go.gg.gpms.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.notice.service.NoticeService;
import kr.go.gg.gpms.notice.service.model.NoticeVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

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

@Service("noticeService")
public class NoticeServiceImpl extends AbstractServiceImpl implements NoticeService {
	
	@Resource(name = "noticeDAO")
	private NoticeDAO noticeDAO;


	/**
	 * 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) throws Exception {
		return noticeDAO.selectNoticeList(noticeVO);
	}

	/**
	 * 공지사항(TN_NOTICE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 총 갯수
	 * @exception
	 */
	public int selectNoticeListTotalCount(NoticeVO noticeVO) {
		return noticeDAO.selectNoticeListTotalCount(noticeVO);
	}
	
	/**
	 * 공지사항(TN_NOTICE)을 등록한다.
	 * @param noticeVO - 등록할 정보가 담긴 NoticeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertNotice(NoticeVO noticeVO) throws Exception {
		return noticeDAO.insertNotice(noticeVO);
	}

	/**
	 * 공지사항(TN_NOTICE)을  상세 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return 조회한 TN_NOTICE
	 * @exception Exception
	 */
	public NoticeVO selectNoticeView(NoticeVO noticeVO) throws Exception {
		NoticeVO resultVO = noticeDAO.selectNoticeView(noticeVO);
		
		//조회수 1 증가 후 저장
		Integer rd = Integer.parseInt(resultVO.getRDCNT())+1;
		noticeVO.setRDCNT(rd.toString());
		noticeVO.setSEQ_NO(resultVO.getSEQ_NO());
		noticeDAO.updateNoticeRdcnt(noticeVO);
		
		return resultVO;
	}
	
	/**
	 * 공지사항(TN_NOTICE)을 삭제한다.
	 * @param noticeVO - 삭제할 정보가 담긴 noticeVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteNotice(NoticeVO noticeVO) throws Exception {
		return noticeDAO.deleteNotice(noticeVO);
	}
	
	/**
	 * 공지사항(TN_NOTICE)을 수정한다.
	 * @param noticeVO - 수정할 정보가 담긴 noticeVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		return noticeDAO.updateNotice(noticeVO);
	}

	/**
	 * 공지사항(TN_NOTICE) 조회수를 수정한다.
	 * @param noticeVO - 조회할 정보가 담긴 NoticeVO
	 * @return int형 
	 * @exception Exception
	 */
	public int updateNoticeRdcnt(NoticeVO noticeVO) throws Exception {
		return noticeDAO.updateNoticeRdcnt(noticeVO);
	}
	
	/**
	 * 메인 공지사항(TN_NOTICE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 목록
	 * @exception Exception
	 */
	public List<NoticeVO> selectMainNoticeList(NoticeVO noticeVO) throws Exception {
		return noticeDAO.selectMainNoticeList(noticeVO);
	}
	
	/**
	 * 메인 공지사항(TN_NOTICE) 목록 전체 건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 noticeVO
	 * @return TN_NOTICE 총 갯수
	 * @exception
	 */
	public int selectMainNoticeListTotalCount(NoticeVO noticeVO){
		return noticeDAO.selectMainNoticeListTotalCount(noticeVO);
	}
	
	
/*
	@Override
	public SysUserVO selectSysUserByID(SysUserVO sysUserVO) throws Exception {
		SysUserVO resultVO = sysUserDAO.selectSysUserByID( sysUserVO);
		
		return resultVO;
	}
	*/

}
