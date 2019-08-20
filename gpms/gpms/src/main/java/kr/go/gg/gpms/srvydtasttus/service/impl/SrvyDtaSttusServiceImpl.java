package kr.go.gg.gpms.srvydtasttus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvydtasttus.service.SrvyDtaSttusService;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;

/**
 * 조사_자료_현황
 *
 * @Class Name : SrvyDtaSttusServiceImpl.java
 * @Description : SrvyDtaSttus Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("srvyDtaSttusService")
public class SrvyDtaSttusServiceImpl extends AbstractServiceImpl implements SrvyDtaSttusService {

	@Resource(name = "srvyDtaSttusDAO")
	private SrvyDtaSttusDAO srvyDtaSttusDAO;

	//@Resource(name="SrvyDtaSttusIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 등록한다.
	 * @param srvyDtaSttusVO - 등록할 정보가 담긴 SrvyDtaSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//srvyDtaSttusVO.setId(id);

		return srvyDtaSttusDAO.insertSrvyDtaSttus( srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 수정한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.updateSrvyDtaSttus( srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 삭제한다.
	 * @param srvyDtaSttusVO - 삭제할 정보가 담긴 SrvyDtaSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.deleteSrvyDtaSttus( srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS)을 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조회한 TN_SRVY_DTA_STTUS
	 * @exception Exception
	 */
	public SrvyDtaSttusVO selectSrvyDtaSttus(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		SrvyDtaSttusVO resultVO = srvyDtaSttusDAO.selectSrvyDtaSttus( srvyDtaSttusVO);
		 
		return resultVO;
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 목록
	 * @exception Exception
	 */
	public List<SrvyDtaSttusVO> selectSrvyDtaSttusList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.selectSrvyDtaSttusList( srvyDtaSttusVO);
	}

	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaSttusListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO) {
		return srvyDtaSttusDAO.selectSrvyDtaSttusListTotalCount( srvyDtaSttusVO);
	}
	/**
	 * 조사_자료_현황(TN_SRVY_DTA_STTUS) 중 미 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return TN_SRVY_DTA_STTUS 
	 * @exception
	 */
	@Override
	public List<SrvyDtaSttusVO> selectSrvyDtaSttusNotPRDCTN_AT(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.selectSrvyDtaSttusNotPRDCTN_AT( srvyDtaSttusVO);
	}
	
	/**
	 * 조사_자료 분석 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록
	 * @exception
	 */
	public List<SrvyDtaSttusVO> selectSrvyDtaAnalTrgetList(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.selectSrvyDtaAnalTrgetList(srvyDtaSttusVO);
	}
	
	/**
	 * 조사_자료 분석 대상 목록 총 갯수를 조회한다.
	 * @param srvyDtaSttusVO - 조회할 정보가 담긴 SrvyDtaSttusVO
	 * @return 조사_자료 분석 대상 목록 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaAnalTrgetListTotalCount(SrvyDtaSttusVO srvyDtaSttusVO) {
		return srvyDtaSttusDAO.selectSrvyDtaAnalTrgetListTotalCount(srvyDtaSttusVO);
	}
	
	/**
	 * 조사_자료 분석 대상을 삭제한다.
	 * @param srvyDtaSttusVO - 수정할 정보가 담긴 SrvyDtaSttusVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int deleteSrvyDtaAnalTrget(SrvyDtaSttusVO srvyDtaSttusVO) throws Exception {
		return srvyDtaSttusDAO.deleteSrvyDtaAnalTrget(srvyDtaSttusVO);
	}

}
