package kr.go.gg.gpms.attachfile.service;

import java.util.List;

import kr.go.gg.gpms.attachfile.service.model.AttachFileDefaultVO;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통첨부파일
 * TN_ATTACH_FILE
 *
 * @Class Name : AttachFileService.java
 * @Description : AttachFile Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface AttachFileService {

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 등록한다.
	 * @param attachFileVO - 등록할 정보가 담긴 AttachFileVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertAttachFile(AttachFileVO attachFileVO) throws Exception;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 수정한다.
	 * @param attachFileVO - 수정할 정보가 담긴 AttachFileVO
	 * @return int형
	 * @exception Exception
	 */
	int updateAttachFile(AttachFileVO attachFileVO) throws Exception;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 삭제한다.
	 * @param attachFileVO - 삭제할 정보가 담긴 AttachFileVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteAttachFile(AttachFileVO attachFileVO) throws Exception;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return 조회한 TN_ATTACH_FILE
	 * @exception Exception
	 */
	AttachFileVO selectAttachFile(AttachFileVO attachFileVO) throws Exception;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 attachFileVO
	 * @return TN_ATTACH_FILE 목록
	 * @exception Exception
	 */
	List<AttachFileVO> selectAttachFileList(AttachFileVO attachFileVO) throws Exception;

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 attachFileVO
	 * @return TN_ATTACH_FILE 총 갯수
	 * @exception
	 */
	int selectAttachFileListTotalCount(AttachFileVO attachFileVO);

}

