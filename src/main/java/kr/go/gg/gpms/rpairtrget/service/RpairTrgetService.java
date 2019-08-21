package kr.go.gg.gpms.rpairtrget.service;

import java.util.List;

import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetDefaultVO;
import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보수_대상_항목
 * TN_RPAIR_TRGET
 *
 * @Class Name : RpairTrgetService.java
 * @Description : RpairTrget Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RpairTrgetService {

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 등록한다.
	 * @param rpairTrgetVO - 등록할 정보가 담긴 RpairTrgetVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 수정한다.
	 * @param rpairTrgetVO - 수정할 정보가 담긴 RpairTrgetVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 삭제한다.
	 * @param rpairTrgetVO - 삭제할 정보가 담긴 RpairTrgetVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return 조회한 TN_RPAIR_TRGET
	 * @exception Exception
	 */
	RpairTrgetVO selectRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetVO
	 * @return TN_RPAIR_TRGET 목록
	 * @exception Exception
	 */
	List<RpairTrgetVO> selectRpairTrgetList(RpairTrgetVO rpairTrgetVO) throws Exception;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetVO
	 * @return TN_RPAIR_TRGET 총 갯수
	 * @exception
	 */
	int selectRpairTrgetListTotalCount(RpairTrgetVO rpairTrgetVO);

}

