package kr.go.gg.gpms.dwginfo.service;

import java.util.List;

import kr.go.gg.gpms.dwginfo.service.model.DwgInfoDefaultVO;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * DWG_INFO
 * DWG_INFO
 *
 * @Class Name : DwgInfoService.java
 * @Description : DwgInfo Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface DwgInfoService {

	/**
	 * DWG_INFO(DWG_INFO)을 등록한다.
	 * @param dwgInfoVO - 등록할 정보가 담긴 DwgInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertDwgInfo(DwgInfoVO dwgInfoVO) throws Exception;

	/**
	 * DWG_INFO(DWG_INFO)을 수정한다.
	 * @param dwgInfoVO - 수정할 정보가 담긴 DwgInfoVO
	 * @return int형
	 * @exception Exception
	 */
	int updateDwgInfo(DwgInfoVO dwgInfoVO) throws Exception;

	/**
	 * DWG_INFO(DWG_INFO)을 삭제한다.
	 * @param dwgInfoVO - 삭제할 정보가 담긴 DwgInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteDwgInfo(DwgInfoVO dwgInfoVO) throws Exception;

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
	DwgInfoVO selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception;

	/**
	 * DWG_INFO(DWG_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 dwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	List<DwgInfoVO> selectDwgInfoList(DwgInfoVO dwgInfoVO) throws Exception;

	/**
	 * DWG_INFO(DWG_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 dwgInfoVO
	 * @return DWG_INFO 총 갯수
	 * @exception
	 */
	int selectDwgInfoListTotalCount(DwgInfoVO dwgInfoVO);
	
	/**
	 * DWG_INFO(DWG_INFO) 구간 목록을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	List<DwgInfoVO> selectDwgInfoSectList(DwgInfoVO dwgInfoVO) throws Exception;

}

