package kr.go.gg.gpms.flaw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.flaw.service.FlawService;
import kr.go.gg.gpms.flaw.service.model.FlawVO;

/**
 * 공사하자정보
 *
 * @Class Name : FlawServiceImpl.java
 * @Description : Flaw Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("flawService")
public class FlawServiceImpl extends AbstractServiceImpl implements FlawService {

	@Resource(name = "flawDAO")
	private FlawDAO flawDAO;

	//@Resource(name="FlawIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사하자정보(TN_FLAW)을 등록한다.
	 * @param flawVO - 등록할 정보가 담긴 FlawVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertFlaw(FlawVO flawVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//flawVO.setId(id);

		return flawDAO.insertFlaw( flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 수정한다.
	 * @param flawVO - 수정할 정보가 담긴 FlawVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateFlaw(FlawVO flawVO) throws Exception {
		return flawDAO.updateFlaw( flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 삭제한다.
	 * @param flawVO - 삭제할 정보가 담긴 FlawVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteFlaw(FlawVO flawVO) throws Exception {
		return flawDAO.deleteFlaw( flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	public FlawVO selectFlaw(FlawVO flawVO) throws Exception {
		FlawVO resultVO = flawDAO.selectFlaw( flawVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 공사하자정보(TN_FLAW) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawVO
	 * @return TN_FLAW 목록
	 * @exception Exception
	 */
	public List<FlawVO> selectFlawList(FlawVO flawVO) throws Exception {
		return flawDAO.selectFlawList( flawVO);
	}

	/**
	 * 공사하자정보(TN_FLAW) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawVO
	 * @return TN_FLAW 총 갯수
	 * @exception
	 */
	public int selectFlawListTotalCount(FlawVO flawVO) {
		return flawDAO.selectFlawListTotalCount( flawVO);
	}
	
	/**
	 * 공사하자정보(TN_FLAW)을 조회한다.
	 * @param flawVO - 조회할 정보가 담긴 FlawVO
	 * @return 조회한 TN_FLAW
	 * @exception Exception
	 */
	public FlawVO selectFlawByCntrwkId(FlawVO flawVO) throws Exception {
		return flawDAO.selectFlawByCntrwkId(flawVO);
	}

}
