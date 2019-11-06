package kr.go.gg.gpms.srvyrequstsctn.service;

import java.util.List;

import kr.go.gg.gpms.srvyrequstsctn.service.model.SrvyRequstSctnDefaultVO;
import kr.go.gg.gpms.srvyrequstsctn.service.model.SrvyRequstSctnVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 조사_요청_구간
 * TN_SRVY_REQUST_SCTN
 *
 * @Class Name : SrvyRequstSctnService.java
 * @Description : SrvyRequsSctn Business class
 * @Modification Information
 *
 * @author 
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SrvyRequstSctnService {

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 등록한다.
	 * @param srvyRequstSctnVO - 등록할 정보가 담긴 SrvyRequstSctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 수정한다.
	 * @param srvyRequstSctnVO - 수정할 정보가 담긴 SrvyRequstSctnVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 삭제한다.
	 * @param srvyRequstSctnVO - 삭제할 정보가 담긴 SrvyRequstSctnVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN)을 조회한다.
	 * @param srvyRequstSctnVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return 조회한 TL_SRVY_DTA_LOG
	 * @exception Exception
	 */
	SrvyRequstSctnVO selectSrvyRequstSctn(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return TL_SRVY_DTA_LOG 목록
	 * @exception Exception
	 */
	List<SrvyRequstSctnVO> selectSrvyRequstSctnList(SrvyRequstSctnVO srvyRequstSctnVO) throws Exception;

	/**
	 * 조사_요청_구간(TN_SRVY_REQUST_SCTN) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 SrvyRequstSctnVO
	 * @return TN_SRVY_REQUST_SCTN 총 갯수
	 * @exception
	 */
	int selectSrvyRequstSctnListTotalCount(SrvyRequstSctnVO srvyRequstSctnVO);

}

