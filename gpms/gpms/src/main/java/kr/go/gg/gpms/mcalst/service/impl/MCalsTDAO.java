


package kr.go.gg.gpms.mcalst.service.impl;

import java.util.List;

import kr.go.gg.gpms.mcalst.service.model.MCalsTVO;

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
 * @since 2017-08-27
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mcalstDAO")
public class MCalsTDAO extends BaseDAO {


	/**
	 * M_CALS_T(M_CALS_T) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MCalsTVO> selectMCalsTList(MCalsTVO mcalstVO) throws Exception {
		return (List<MCalsTVO>)list("mcalstDAO.selectMCalsTList", mcalstVO);
	}

	/**
	 * M_CALS_T(M_CALS_T) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 총 갯수
	 * @exception
	 */
	public int selectMCalsTListTotalCount(MCalsTVO mcalstVO) {
		return (Integer) select("mcalstDAO.selectMCalsTListTotalCount", mcalstVO);
	}
	
}
