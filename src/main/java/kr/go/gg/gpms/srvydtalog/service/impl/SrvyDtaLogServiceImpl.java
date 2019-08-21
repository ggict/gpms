package kr.go.gg.gpms.srvydtalog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvydtalog.service.SrvyDtaLogService;
import kr.go.gg.gpms.srvydtalog.service.model.SrvyDtaLogVO;

/**
 * 조사_자료_로그
 *
 * @Class Name : SrvyDtaLogServiceImpl.java
 * @Description : SrvyDtaLog Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("srvyDtaLogService")
public class SrvyDtaLogServiceImpl extends AbstractServiceImpl implements SrvyDtaLogService {

	@Resource(name = "srvyDtaLogDAO")
	private SrvyDtaLogDAO srvyDtaLogDAO;

	//@Resource(name="SrvyDtaLogIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 등록한다.
	 * @param srvyDtaLogVO - 등록할 정보가 담긴 SrvyDtaLogVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//srvyDtaLogVO.setId(id);

		return srvyDtaLogDAO.insertSrvyDtaLog( srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 수정한다.
	 * @param srvyDtaLogVO - 수정할 정보가 담긴 SrvyDtaLogVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return srvyDtaLogDAO.updateSrvyDtaLog( srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 삭제한다.
	 * @param srvyDtaLogVO - 삭제할 정보가 담긴 SrvyDtaLogVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return srvyDtaLogDAO.deleteSrvyDtaLog( srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG)을 조회한다.
	 * @param srvyDtaLogVO - 조회할 정보가 담긴 SrvyDtaLogVO
	 * @return 조회한 TL_SRVY_DTA_LOG
	 * @exception Exception
	 */
	public SrvyDtaLogVO selectSrvyDtaLog(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		SrvyDtaLogVO resultVO = srvyDtaLogDAO.selectSrvyDtaLog( srvyDtaLogVO);
		 
		return resultVO;
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 목록
	 * @exception Exception
	 */
	public List<SrvyDtaLogVO> selectSrvyDtaLogList(SrvyDtaLogVO srvyDtaLogVO) throws Exception {
		return srvyDtaLogDAO.selectSrvyDtaLogList( srvyDtaLogVO);
	}

	/**
	 * 조사_자료_로그(TL_SRVY_DTA_LOG) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaLogVO
	 * @return TL_SRVY_DTA_LOG 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaLogListTotalCount(SrvyDtaLogVO srvyDtaLogVO) {
		return srvyDtaLogDAO.selectSrvyDtaLogListTotalCount( srvyDtaLogVO);
	}

}
