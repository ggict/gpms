


package kr.go.gg.gpms.mvroad.service.impl;

import java.util.List;

import kr.go.gg.gpms.mvroad.service.model.MvRoadVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * CELL_SECT
 *
 * @Class Name : cellSectDAO.java
 * @Description : CellSect DAO Class
 * @Modification Information
 *
 * @author kmh
 * @since 2017-08-22
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("mvRoadDAO")
public class MvRoadDAO extends BaseDAO {

	public String insertMvRoad(MvRoadVO mvroadVO) throws Exception {
		return (String) insert("mvRoadDAO.insertMvRoad", mvroadVO);
	}

	public MvRoadVO selectMvRoad(MvRoadVO mvroadVO) throws Exception {
		return (MvRoadVO) select("mvRoadDAO.selectMvRoad", mvroadVO);
	}

	public int deleteMvRoad(MvRoadVO mvroadVO) throws Exception {
		return delete("mvRoadDAO.deleteMvRoad", mvroadVO);
	}

	public int updateMvRoad(MvRoadVO mvroadVO) throws Exception {
		return (int) update("mvRoadDAO.updateMvRoad", mvroadVO);
	}

}
