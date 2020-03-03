package kr.go.gg.gpms.dwginfo.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO3;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

/**
 * DWG_INFO
 *
 * @Class Name : DwgInfoDAO.java
 * @Description : DwgInfo DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("dwgInfo2DAO")
public class DwgInfo2DAO extends BaseDAO3 {

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
	public Map<String, String> selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return (Map<String, String>) select("dwgInfo2DAO.selectDwgInfo", dwgInfoVO);
	}

}
