package kr.go.gg.gpms.mcalst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.mcalst.service.MCalsTService;
import kr.go.gg.gpms.mcalst.service.model.MCalsTVO;

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

@Service("mcalstService")
public class MCalsTServiceImpl extends AbstractServiceImpl implements MCalsTService {

	@Resource(name = "mcalstDAO")
	private MCalsTDAO mcalstDAO;

	/**
	 * M_CALS_T(M_CALS_T) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 목록
	 * @exception Exception
	 */
	public List<MCalsTVO> selectMCalsTList(MCalsTVO mcalstVO) throws Exception {
		return mcalstDAO.selectMCalsTList(mcalstVO);
	}

	/**
	 * M_CALS_T(M_CALS_T) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mcalsTVO
	 * @return M_CALS_T 총 갯수
	 * @exception
	 */
	public int selectMCalsTListTotalCount(MCalsTVO mcalstVO) {
		return mcalstDAO.selectMCalsTListTotalCount(mcalstVO);
	}
}
