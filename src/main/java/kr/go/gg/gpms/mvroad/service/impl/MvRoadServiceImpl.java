package kr.go.gg.gpms.mvroad.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.mvroad.service.MvRoadService;
import kr.go.gg.gpms.mvroad.service.model.MvRoadVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * CELL_SECT
 *
 * @Class Name : Cell10ServiceImpl.java
 * @Description : Cell10 Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("mvRoadService")
public class MvRoadServiceImpl extends AbstractServiceImpl implements MvRoadService {

	@Resource(name = "mvRoadDAO")
	private MvRoadDAO mvroadDAO;

	public String insertMvRoad(MvRoadVO mvroadVO) throws Exception {

		return mvroadDAO.insertMvRoad(mvroadVO);
	}

	@Override
	public MvRoadVO selectMvRoad(MvRoadVO mvroadVO) throws Exception {
		MvRoadVO resultVO = mvroadDAO.selectMvRoad(mvroadVO);

		return resultVO;
	}

	@Override
	public int deleteMvRoad(MvRoadVO mvroadVO) throws Exception {
		return mvroadDAO.deleteMvRoad(mvroadVO);
	}

	public int updateMvRoad(MvRoadVO mvroadVO) throws Exception {

		return mvroadDAO.updateMvRoad(mvroadVO);
	}
}
