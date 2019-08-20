package kr.go.gg.gpms.mcalst.service;

import java.util.List;

import kr.go.gg.gpms.mcalst.service.model.MCalsTVO;

/**
 * CELL_SECT
 * CELL_SECT
 *
 * @Class Name : MCalsTService.java
 * @Description : MCalsT Business class
 * @Modification Information
 *
 * @author kmh
 * @since 2017-08-27
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MCalsTService {

	/**
	 * M_CALS_T(M_CALS_T) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 목록
	 * @exception Exception
	 */
	List<MCalsTVO> selectMCalsTList(MCalsTVO mcalstVO) throws Exception;

	/**
	 * M_CALS_T(M_CALS_T) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 총 갯수
	 * @exception
	 */
	int selectMCalsTListTotalCount(MCalsTVO mcalstVO);
	
}

