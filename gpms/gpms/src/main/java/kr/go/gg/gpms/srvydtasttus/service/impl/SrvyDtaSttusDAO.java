


package kr.go.gg.gpms.srvydtasttus.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;

/**
 * 조사_자료_현황
 *
 * @Class Name : SrvyDtaSttusDAO.java
 * @Description : SrvyDtaSttus DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyDtaSttusDAO")
public class SrvyDtaSttusDAO extends BaseDAO {

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 등록한다.
	 * @param srvyDtaSttusVO - 등록할 정보가 담긴 SrvyDtaSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return (Integer) insert("srvyDtaSttusDAO.insertSrvyDtaSttus", srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 수정한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return update("srvyDtaSttusDAO.updateSrvyDtaSttus", srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 삭제한다.
	 * @param srvyDtaSttusVO - 삭제할 정보가 담긴 SrvyDtaSttusVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return delete("srvyDtaSttusDAO.deleteSrvyDtaSttus", srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조회한 TN_SRVY_DTA_STTUS
	 * @exception Exception
	 */
	public SrvyDtaSttusVO selectSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return (SrvyDtaSttusVO) select("srvyDtaSttusDAO.selectSrvyDtaSttus", srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaSttusVO> selectSrvyDtaSttusList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return (List<SrvyDtaSttusVO>)list("srvyDtaSttusDAO.selectSrvyDtaSttusList", srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 총 갯수를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaSttusListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO) {
		return (Integer) select("srvyDtaSttusDAO.selectSrvyDtaSttusListTotalCount", srvyDtaSttusVO);
	}
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 총 갯수
	 * @exception
	 */
	public List<SrvyDtaSttusVO> selectSrvyDtaSttusNotPRDCTN_AT(SrvyDtaSttusVO srvyDtaSttusVO)throws Exception {
		return (List<SrvyDtaSttusVO>)list("srvyDtaSttusDAO.selectSrvyDtaSttusNotPRDCTN_AT", srvyDtaSttusVO); 
	}
	
	/**
	 * 조사_자료 분석 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaSttusVO> selectSrvyDtaAnalTrgetList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return (List<SrvyDtaSttusVO>)list("srvyDtaSttusDAO.selectSrvyDtaAnalTrgetList", srvyDtaSttusVO); 
	}
	
	/**
	 * 조사_자료 분석 대상 목록 총 갯수를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaAnalTrgetListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO) {
		return (Integer) select("srvyDtaSttusDAO.selectSrvyDtaAnalTrgetListTotalCount", srvyDtaSttusVO);
	}
	
	/**
	 * 조사_자료 분석 대상을 삭제한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int deleteSrvyDtaAnalTrget(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return update("srvyDtaSttusDAO.deleteSrvyDtaAnalTrget", srvyDtaSttusVO);
	}

}
