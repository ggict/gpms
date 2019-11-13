


package kr.go.gg.gpms.attachfile.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;

/**
 * 공통첨부파일
 *
 * @Class Name : AttachFileDAO.java
 * @Description : AttachFile DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("attachFileDAO")
public class AttachFileDAO extends BaseDAO {

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 등록한다.
	 * @param attachFileVO - 등록할 정보가 담긴 AttachFileVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAttachFile(AttachFileVO attachFileVO) throws Exception {
		return (String) insert("attachFileDAO.insertAttachFile", attachFileVO);
	}
	
	/**
	 * 공통첨부상세파일(TN_ATTACH_DETAIL_FILE)을 등록한다.
	 * @param attachFileVO - 등록할 정보가 담긴 AttachFileVO
	 * @return void
	 * @exception Exception
	 */
	public void insertAttachDetailFile(AttachFileVO attachFileVO) throws Exception {
		insert("attachFileDAO.insertAttachDetailFile", attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 수정한다.
	 * @param attachFileVO - 수정할 정보가 담긴 AttachFileVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateAttachFile(AttachFileVO attachFileVO) throws Exception {
		return update("attachFileDAO.updateAttachFile", attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 삭제한다.
	 * @param attachFileVO - 삭제할 정보가 담긴 AttachFileVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
		return delete("attachFileDAO.deleteAttachFile", attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE)을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return 조회한 TN_ATTACH_FILE
	 * @exception Exception
	 */
	public AttachFileVO selectAttachFile(AttachFileVO attachFileVO) throws Exception {
		return (AttachFileVO) select("attachFileDAO.selectAttachFile", attachFileVO);
	}
	
	/**
	 * 공통첨부상세파일(TN_ATTACH_DETAIL_FILE)을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return 조회한 TN_ATTACH_DETAIL_FILE
	 * @exception Exception
	 */
	public AttachFileVO selectAttachDetailFile(AttachFileVO attachFileVO) throws Exception {
		return (AttachFileVO) select("attachFileDAO.selectAttachDetailFile", attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 목록을 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return TN_ATTACH_FILE 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AttachFileVO> selectAttachFileList(AttachFileVO attachFileVO) throws Exception {
		return (List<AttachFileVO>)list("attachFileDAO.selectAttachFileList", attachFileVO);
	}

	/**
	 * 공통첨부파일(TN_ATTACH_FILE) 총 갯수를 조회한다.
	 * @param attachFileVO - 조회할 정보가 담긴 AttachFileVO
	 * @return TN_ATTACH_FILE 총 갯수
	 * @exception
	 */
	public int selectAttachFileListTotalCount(AttachFileVO attachFileVO) {
		return (Integer) select("attachFileDAO.selectAttachFileListTotalCount", attachFileVO);
	}

}
