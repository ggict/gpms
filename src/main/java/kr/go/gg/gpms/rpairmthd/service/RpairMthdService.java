package kr.go.gg.gpms.rpairmthd.service;

import java.util.List;

import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdDefaultVO;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보수공법코드
 * TC_RPAIR_MTHD
 *
 * @Class Name : RpairMthdService.java
 * @Description : RpairMthd Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RpairMthdService {

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 등록한다.
	 * @param rpairMthdVO - 등록할 정보가 담긴 RpairMthdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairMthd(RpairMthdVO rpairMthdVO) throws Exception;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 수정한다.
	 * @param rpairMthdVO - 수정할 정보가 담긴 RpairMthdVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairMthd(RpairMthdVO rpairMthdVO) throws Exception;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 삭제한다.
	 * @param rpairMthdVO - 삭제할 정보가 담긴 RpairMthdVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRpairMthd(RpairMthdVO rpairMthdVO) throws Exception;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return 조회한 TC_RPAIR_MTHD
	 * @exception Exception
	 */
	RpairMthdVO selectRpairMthd(RpairMthdVO rpairMthdVO) throws Exception;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdVO
	 * @return TC_RPAIR_MTHD 목록
	 * @exception Exception
	 */
	List<RpairMthdVO> selectRpairMthdList(RpairMthdVO rpairMthdVO) throws Exception;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdVO
	 * @return TC_RPAIR_MTHD 총 갯수
	 * @exception
	 */
	int selectRpairMthdListTotalCount(RpairMthdVO rpairMthdVO);

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)를 보수공법명으로 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return 조회한 TC_RPAIR_MTHD
	 * @exception Exception
	 */
	RpairMthdVO selectRpairMthdCode(RpairMthdVO rpairMthdVO) throws Exception;

}

