package kr.go.gg.gpms.attachfile.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;

/**
 * 공통첨부파일
 *
 * @Class Name : AttachFileServiceImpl.java
 * @Description : AttachFile Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("attachFileService")
public class AttachFileServiceImpl extends AbstractServiceImpl implements AttachFileService {

	@Resource(name = "attachFileDAO")
	private AttachFileDAO attachFileDAO;

	//@Resource(name="AttachFileIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 등록한다.
	 * @param attachFileVO - 등록할 정보가 담긴 AttachFileVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAttachFile(AttachFileVO attachFileVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//attachFileVO.setId(id);

		return attachFileDAO.insertAttachFile( attachFileVO);
	}
	
	/**
	 * 공통첨부상세파일(TN_ATTACH_DETAIL_FILE)을 등록한다.
	 * @param attachFileVO - 등록할 정보가 담긴 AttachFileVO
	 * @return void
	 * @exception Exception
	 */
	public void insertAttachDetailFile(AttachFileVO attachFileVO) throws Exception {
		attachFileDAO.insertAttachDetailFile(attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 수정한다.
	 * @param attachFileVO - 수정할 정보가 담긴 AttachFileVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateAttachFile(AttachFileVO attachFileVO) throws Exception {
		return attachFileDAO.updateAttachFile( attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 삭제한다.
	 * @param attachFileVO - 삭제할 정보가 담긴 AttachFileVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
		return attachFileDAO.deleteAttachFile( attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return 조회한 TN_ATTACH_FILE
	 * @exception Exception
	 */
	public AttachFileVO selectAttachFile(AttachFileVO attachFileVO) throws Exception {
		AttachFileVO resultVO = attachFileDAO.selectAttachFile( attachFileVO);
		
		return resultVO;
	}
	
	/**
	 * 공통첨부상세파일(TN_ATTACH_DETAIL_FILE)을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return 조회한 TN_ATTACH_DETAIL_FILE
	 * @exception Exception
	 */
	public AttachFileVO selectAttachDetailFile(AttachFileVO attachFileVO) throws Exception {
		AttachFileVO resultVO = attachFileDAO.selectAttachDetailFile(attachFileVO);
		
		return resultVO;
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 attachFileVO
	 * @return TN_ATTACH_FILE 목록
	 * @exception Exception
	 */
	public List<AttachFileVO> selectAttachFileList(AttachFileVO attachFileVO) throws Exception {
		return attachFileDAO.selectAttachFileList( attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 attachFileVO
	 * @return TN_ATTACH_FILE 총 갯수
	 * @exception
	 */
	public int selectAttachFileListTotalCount(AttachFileVO attachFileVO) {
		return attachFileDAO.selectAttachFileListTotalCount( attachFileVO);
	}

}
