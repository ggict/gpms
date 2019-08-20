package kr.go.gg.gpms.flawcntrwk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.flawcntrwk.service.FlawCntrwkService;
import kr.go.gg.gpms.flawcntrwk.service.model.FlawCntrwkVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 하자_보수_공사
 *
 * @Class Name : FlawCntrwkServiceImpl.java
 * @Description : FlawCntrwk Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("flawCntrwkService")
public class FlawCntrwkServiceImpl extends AbstractServiceImpl implements FlawCntrwkService {

	@Resource(name = "flawCntrwkDAO")
	private FlawCntrwkDAO flawCntrwkDAO;

	//@Resource(name="FlawCntrwkIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 등록한다.
	 * @param flawCntrwkVO - 등록할 정보가 담긴 FlawCntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//flawCntrwkVO.setId(id);

		return flawCntrwkDAO.insertFlawCntrwk( flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 수정한다.
	 * @param flawCntrwkVO - 수정할 정보가 담긴 FlawCntrwkVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return flawCntrwkDAO.updateFlawCntrwk( flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return flawCntrwkDAO.deleteFlawCntrwk( flawCntrwkVO);
	}
	
	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 삭제한다.
	 * @param flawCntrwkVO - 삭제할 정보가 담긴 FlawCntrwkVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteFlawCntrwkByCntrwkDtlID(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return flawCntrwkDAO.deleteFlawCntrwkByCntrwkDtlID( flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK)을 조회한다.
	 * @param flawCntrwkVO - 조회할 정보가 담긴 FlawCntrwkVO
	 * @return 조회한 TN_FLAW_CNTRWK
	 * @exception Exception
	 */
	public FlawCntrwkVO selectFlawCntrwk(FlawCntrwkVO flawCntrwkVO) throws Exception {
		FlawCntrwkVO resultVO = flawCntrwkDAO.selectFlawCntrwk( flawCntrwkVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawCntrwkVO
	 * @return TN_FLAW_CNTRWK 목록
	 * @exception Exception
	 */
	public List<FlawCntrwkVO> selectFlawCntrwkList(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return flawCntrwkDAO.selectFlawCntrwkList( flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사(TN_FLAW_CNTRWK) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 flawCntrwkVO
	 * @return TN_FLAW_CNTRWK 총 갯수
	 * @exception
	 */
	public int selectFlawCntrwkListTotalCount(FlawCntrwkVO flawCntrwkVO) {
		return flawCntrwkDAO.selectFlawCntrwkListTotalCount( flawCntrwkVO);
	}

	/**
	 * 하자_보수_공사 엑셀목록을 조회한다.
	 */
	public List selectFlawCntrwkListExcel(FlawCntrwkVO flawCntrwkVO) throws Exception {
		return flawCntrwkDAO.selectFlawCntrwkListExcel( flawCntrwkVO);
	}
}
