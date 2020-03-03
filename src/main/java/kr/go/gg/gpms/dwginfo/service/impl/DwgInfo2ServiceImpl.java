package kr.go.gg.gpms.dwginfo.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.dwginfo.service.DwgInfo2Service;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

/**
 * DWG_INFO
 *
 * @Class Name : DwgInfoServiceImpl.java
 * @Description : DwgInfo Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("dwgInfo2Service")
public class DwgInfo2ServiceImpl extends AbstractServiceImpl implements DwgInfo2Service {

	@Resource(name = "dwgInfo2DAO")
	private DwgInfo2DAO dwgInfo2DAO;

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
	public Map<String, String> selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfo2DAO.selectDwgInfo( dwgInfoVO);
	}

}
