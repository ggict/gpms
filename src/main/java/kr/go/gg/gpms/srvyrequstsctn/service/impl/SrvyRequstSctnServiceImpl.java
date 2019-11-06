package kr.go.gg.gpms.srvyrequstsctn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.srvyrequstsctn.service.SrvyRequstSctnService;
import kr.go.gg.gpms.srvyrequstsctn.service.model.SrvyRequstSctnVO;

/**
 * 조사_요청_구간
 *
 * @Class Name : SrvyRequstSctnServiceImpl.java
 * @Description : SrvyRequstSctn Business Implement class
 * @Modification Information
 *
 * @author 
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("srvyRequstSctnService")
public class SrvyRequstSctnServiceImpl extends AbstractServiceImpl implements SrvyRequstSctnService {

	@Resource(name = "srvyRequstSctnDAO")
	private SrvyRequstSctnDAO srvyRequstSctnDAO;

	//@Resource(name="SrvyDtaLogIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 등록한다.
	 * @param srvyRequstSctnVO - 등록할 정보가 담긴 SrvyRequstSctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//srvyRequstSctnVO.setId(id);

		return srvyRequstSctnDAO.insertSrvyRequstSctn(srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 수정한다.
	 * @param srvyRequstSctnVO - 수정할 정보가 담긴 SrvyRequstSctnVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return srvyRequstSctnDAO.updateSrvyRequstSctn( srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 삭제한다.
	 * @param srvyRequstSctnVO - 삭제할 정보가 담긴 SrvyRequstSctnVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return srvyRequstSctnDAO.deleteSrvyRequstSctn( srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 조회한다.
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return 조회한 TN_SRVY_REQUST_SCTN
	 * @exception Exception
	 */
	public SrvyRequstSctnVO selectSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		SrvyRequstSctnVO resultVO = srvyRequstSctnDAO.selectSrvyRequstSctn( srvyRequstSctnVO);
		 
		return resultVO;
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyRequstSctnVO
	 * @return TN_SRVY_REQUST_SCTN 목록
	 * @exception Exception
	 */
	public List<SrvyRequstSctnVO> selectSrvyRequstSctnList(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception {
		return srvyRequstSctnDAO.selectSrvyRequstSctnList( srvyRequstSctnVO);
	}

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyRequstSctnVO
	 * @return TN_SRVY_REQUST_SCTN 총 갯수
	 * @exception
	 */
	public int selectSrvyRequstSctnListTotalCount(SrvyRequstSctnVO srvyRequstSctnVO) {
		return srvyRequstSctnDAO.selectSrvyRequstSctnListTotalCount( srvyRequstSctnVO);
	}

}
