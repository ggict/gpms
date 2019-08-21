package kr.go.gg.gpms.cntrwksctnhist.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cntrwksctnhist.service.CntrwkSctnHistService;
import kr.go.gg.gpms.cntrwksctnhist.service.model.CntrwkSctnHistVO;

/**
 * 공사_구간_이력
 *
 * @Class Name : CntrwkSctnHistServiceImpl.java
 * @Description : CntrwkSctnHist Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cntrwkSctnHistService")
public class CntrwkSctnHistServiceImpl extends AbstractServiceImpl implements CntrwkSctnHistService {

	@Resource(name = "cntrwkSctnHistDAO")
	private CntrwkSctnHistDAO cntrwkSctnHistDAO;

	//@Resource(name="CntrwkSctnHistIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 등록한다.
	 * @param cntrwkSctnHistVO - 등록할 정보가 담긴 CntrwkSctnHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrwkSctnHistVO.setId(id);

		return cntrwkSctnHistDAO.insertCntrwkSctnHist( cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 수정한다.
	 * @param cntrwkSctnHistVO - 수정할 정보가 담긴 CntrwkSctnHistVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return cntrwkSctnHistDAO.updateCntrwkSctnHist( cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 삭제한다.
	 * @param cntrwkSctnHistVO - 삭제할 정보가 담긴 CntrwkSctnHistVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return cntrwkSctnHistDAO.deleteCntrwkSctnHist( cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST)을 조회한다.
	 * @param cntrwkSctnHistVO - 조회할 정보가 담긴 CntrwkSctnHistVO
	 * @return 조회한 TH_CNTRWK_SCTN_HIST
	 * @exception Exception
	 */
	public CntrwkSctnHistVO selectCntrwkSctnHist(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		CntrwkSctnHistVO resultVO = cntrwkSctnHistDAO.selectCntrwkSctnHist( cntrwkSctnHistVO);
		
		return resultVO;
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 목록
	 * @exception Exception
	 */
	public List<CntrwkSctnHistVO> selectCntrwkSctnHistList(CntrwkSctnHistVO cntrwkSctnHistVO) throws Exception {
		return cntrwkSctnHistDAO.selectCntrwkSctnHistList( cntrwkSctnHistVO);
	}

	/**
	 * 공사_구간_이력(TH_CNTRWK_SCTN_HIST) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkSctnHistVO
	 * @return TH_CNTRWK_SCTN_HIST 총 갯수
	 * @exception
	 */
	public int selectCntrwkSctnHistListTotalCount(CntrwkSctnHistVO cntrwkSctnHistVO) {
		return cntrwkSctnHistDAO.selectCntrwkSctnHistListTotalCount( cntrwkSctnHistVO);
	}

}
