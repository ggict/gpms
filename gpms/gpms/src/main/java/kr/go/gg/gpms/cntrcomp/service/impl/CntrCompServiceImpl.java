package kr.go.gg.gpms.cntrcomp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cntrcomp.service.CntrCompService;
import kr.go.gg.gpms.cntrcomp.service.model.CntrCompVO;

/**
 * 공사업체정보
 *
 * @Class Name : CntrCompServiceImpl.java
 * @Description : CntrComp Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cntrCompService")
public class CntrCompServiceImpl extends AbstractServiceImpl implements CntrCompService {

	@Resource(name = "cntrCompDAO")
	private CntrCompDAO cntrCompDAO;

	//@Resource(name="CntrCompIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 등록한다.
	 * @param cntrCompVO - 등록할 정보가 담긴 CntrCompVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrComp(CntrCompVO cntrCompVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrCompVO.setId(id);

		return cntrCompDAO.insertCntrComp( cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 수정한다.
	 * @param cntrCompVO - 수정할 정보가 담긴 CntrCompVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return cntrCompDAO.updateCntrComp( cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 삭제한다.
	 * @param cntrCompVO - 삭제할 정보가 담긴 CntrCompVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrComp(CntrCompVO cntrCompVO) throws Exception {
		return cntrCompDAO.deleteCntrComp( cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP)을 조회한다.
	 * @param cntrCompVO - 조회할 정보가 담긴 CntrCompVO
	 * @return 조회한 TN_CNTR_COMP
	 * @exception Exception
	 */
	public CntrCompVO selectCntrComp(CntrCompVO cntrCompVO) throws Exception {
		CntrCompVO resultVO = cntrCompDAO.selectCntrComp( cntrCompVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrCompVO
	 * @return TN_CNTR_COMP 목록
	 * @exception Exception
	 */
	public List<CntrCompVO> selectCntrCompList(CntrCompVO cntrCompVO) throws Exception {
		return cntrCompDAO.selectCntrCompList( cntrCompVO);
	}

	/**
	 * 공사업체정보(TN_CNTR_COMP) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrCompVO
	 * @return TN_CNTR_COMP 총 갯수
	 * @exception
	 */
	public int selectCntrCompListTotalCount(CntrCompVO cntrCompVO) {
		return cntrCompDAO.selectCntrCompListTotalCount( cntrCompVO);
	}

}
