


package kr.go.gg.gpms.srvydtalog.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogVO;

/**
 * 조사_자료_로그
 *
 * @Class Name : SrvyDtaLogDAO.java
 * @Description : SrvyDtaLog DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyDtaLogDAO")
public class SrvyDtaLogDAO extends BaseDAO {

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 등록한다.
	 * @param srvyDtaLogVO - 등록할 정보가 담긴 SrvyDtaLogVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return (String) insert("srvyDtaLogDAO.insertSrvyDtaLog", srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 수정한다.
	 * @param srvyDtaLogVO - 수정할 정보가 담긴 SrvyDtaLogVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return update("srvyDtaLogDAO.updateSrvyDtaLog", srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 삭제한다.
	 * @param srvyDtaLogVO - 삭제할 정보가 담긴 SrvyDtaLogVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return delete("srvyDtaLogDAO.deleteSrvyDtaLog", srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return 조회한 TL_SRVY_DTA_LOG
	 * @exception Exception
	 */
	public SrvyDtaLogVO selectSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return (SrvyDtaLogVO) select("srvyDtaLogDAO.selectSrvyDtaLog", srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaLogVO> selectSrvyDtaLogList(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return (List<SrvyDtaLogVO>)list("srvyDtaLogDAO.selectSrvyDtaLogList", srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 총 갯수를 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaLogListTotalCount(SrvyDtaLogVO srvyDtaLogVO) {
		return (Integer) select("srvyDtaLogDAO.selectSrvyDtaLogListTotalCount", srvyDtaLogVO);
	}

}
