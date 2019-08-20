


package kr.go.gg.gpms.pavsttusgradstdr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavsttusgradstdr.service.model.PavSttusGradStdrVO;

/**
 * 포장상태등급기준
 *
 * @Class Name : PavSttusGradStdrDAO.java
 * @Description : PavSttusGradStdr DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("pavSttusGradStdrDAO")
public class PavSttusGradStdrDAO extends BaseDAO {

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 등록한다.
	 * @param pavSttusGradStdrVO - 등록할 정보가 담긴 PavSttusGradStdrVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return (String) insert("pavSttusGradStdrDAO.insertPavSttusGradStdr", pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 수정한다.
	 * @param pavSttusGradStdrVO - 수정할 정보가 담긴 PavSttusGradStdrVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updatePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return update("pavSttusGradStdrDAO.updatePavSttusGradStdr", pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 삭제한다.
	 * @param pavSttusGradStdrVO - 삭제할 정보가 담긴 PavSttusGradStdrVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deletePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return delete("pavSttusGradStdrDAO.deletePavSttusGradStdr", pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return 조회한 TN_PAV_STTUS_GRAD_STDR
	 * @exception Exception
	 */
	public PavSttusGradStdrVO selectPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return (PavSttusGradStdrVO) select("pavSttusGradStdrDAO.selectPavSttusGradStdr", pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PavSttusGradStdrVO> selectPavSttusGradStdrList(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return (List<PavSttusGradStdrVO>)list("pavSttusGradStdrDAO.selectPavSttusGradStdrList", pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 총 갯수를 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 총 갯수
	 * @exception
	 */
	public int selectPavSttusGradStdrListTotalCount(PavSttusGradStdrVO pavSttusGradStdrVO) {
		return (Integer) select("pavSttusGradStdrDAO.selectPavSttusGradStdrListTotalCount", pavSttusGradStdrVO);
	}

}
