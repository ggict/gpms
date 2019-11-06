


package kr.go.gg.gpms.srvyrequstsctn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvyrequstsctn.service.model.SrvyRequstSctnVO;

/**
 * 조사_요청_구간
 *
 * @Class Name : SrvyRequstSctnDAO.java
 * @Description : SrvyRequstSctn DAO Class
 * @Modification Information
 *
 * @author 
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyRequstSctnDAO")
public class SrvyRequstSctnDAO extends BaseDAO {

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 등록한다.
	 * @param srvyRequstSctnVO - 등록할 정보가 담긴 SrvyRequstSctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return (String) insert("srvyRequstSctnDAO.insertSrvyRequstSctn", srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 수정한다.
	 * @param srvyRequstSctnVO - 수정할 정보가 담긴 SrvyRequstSctnVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return update("srvyRequstSctnDAO.updateSrvyRequstSctn", srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 삭제한다.
	 * @param srvyRequstSctnVO - 삭제할 정보가 담긴 SrvyRequstSctnVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return delete("srvyRequstSctnDAO.deleteSrvyRequstSctn", srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 조회한다.
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return 조회한 TN_SRVY_REQUST_SCTN
	 * @exception Exception
	 */
	public SrvyRequstSctnVO selectSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return (SrvyRequstSctnVO) select("srvyRequstSctnDAO.selectSrvyRequstSctn", srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 목록을 조회한다.
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return TN_SRVY_REQUST_SCTN 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyRequstSctnVO> selectSrvyRequstSctnList(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return (List<SrvyRequstSctnVO>)list("srvyRequstSctnDAO.selectSrvyRequstSctnList", srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 총 갯수를 조회한다.
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return TN_SRVY_REQUST_SCTN 총 갯수
	 * @exception
	 */
	public int selectSrvyRequstSctnListTotalCount(SrvyRequstSctnVO srvyRequstSctnVO) {
		return (Integer) select("srvyRequstSctnDAO.selectSrvyRequstSctnListTotalCount", srvyRequstSctnVO);
	}

}
