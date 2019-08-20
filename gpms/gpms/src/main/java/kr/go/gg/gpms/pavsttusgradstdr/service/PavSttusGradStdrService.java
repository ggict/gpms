package kr.go.gg.gpms.pavsttusgradstdr.service;

import java.util.List;

import kr.go.gg.gpms.pavsttusgradstdr.service.model.PavSttusGradStdrDefaultVO;
import kr.go.gg.gpms.pavsttusgradstdr.service.model.PavSttusGradStdrVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 포장상태등급기준
 * TN_PAV_STTUS_GRAD_STDR
 *
 * @Class Name : PavSttusGradStdrService.java
 * @Description : PavSttusGradStdr Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PavSttusGradStdrService {

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 등록한다.
	 * @param pavSttusGradStdrVO - 등록할 정보가 담긴 PavSttusGradStdrVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 수정한다.
	 * @param pavSttusGradStdrVO - 수정할 정보가 담긴 PavSttusGradStdrVO
	 * @return int형
	 * @exception Exception
	 */
	int updatePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 삭제한다.
	 * @param pavSttusGradStdrVO - 삭제할 정보가 담긴 PavSttusGradStdrVO
	 * @return int형 
	 * @exception Exception
	 */
	int deletePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return 조회한 TN_PAV_STTUS_GRAD_STDR
	 * @exception Exception
	 */
	PavSttusGradStdrVO selectPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 목록
	 * @exception Exception
	 */
	List<PavSttusGradStdrVO> selectPavSttusGradStdrList(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 총 갯수
	 * @exception
	 */
	int selectPavSttusGradStdrListTotalCount(PavSttusGradStdrVO pavSttusGradStdrVO);

}

