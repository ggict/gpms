package kr.go.gg.gpms.dwginfo.service;

import java.util.Map;

import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

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
public interface DwgInfo2Service {

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
    Map<String, String> selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception;
}

