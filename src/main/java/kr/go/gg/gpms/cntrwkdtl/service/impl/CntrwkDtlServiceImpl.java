package kr.go.gg.gpms.cntrwkdtl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.cntrwkdtl.service.CntrwkDtlService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 공사상세정보
 *
 * @Class Name : CntrwkDtlServiceImpl.java
 * @Description : CntrwkDtl Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cntrwkDtlService")
public class CntrwkDtlServiceImpl extends AbstractServiceImpl implements CntrwkDtlService {

	@Resource(name = "cntrwkDtlDAO")
	private CntrwkDtlDAO cntrwkDtlDAO;

	//@Resource(name="CntrwkDtlIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 등록한다.
	 * @param cntrwkDtlVO - 등록할 정보가 담긴 CntrwkDtlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrwkDtlVO.setId(id);

		return cntrwkDtlDAO.insertCntrwkDtl( cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 수정한다.
	 * @param cntrwkDtlVO - 수정할 정보가 담긴 CntrwkDtlVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.updateCntrwkDtl( cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.deleteCntrwkDtl( cntrwkDtlVO);
	}
	
	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * @param cntrwkDtlVO - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrwkDtlByCntrwkId(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.deleteCntrwkDtlByCntrwkId( cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return 조회한 TN_CNTRWK_DTL
	 * @exception Exception
	 */
	public CntrwkDtlVO selectCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		CntrwkDtlVO resultVO = cntrwkDtlDAO.selectCntrwkDtl( cntrwkDtlVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkDtlVO
	 * @return TN_CNTRWK_DTL 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlList(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlList( cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkDtlVO
	 * @return TN_CNTRWK_DTL 총 갯수
	 * @exception
	 */
	public int selectCntrwkDtlListTotalCount(CntrwkDtlVO cntrwkDtlVO) {
		return cntrwkDtlDAO.selectCntrwkDtlListTotalCount( cntrwkDtlVO);
	}
	
	/**
	 * 세부공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkDtlListExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlListExcel( cntrwkDtlVO);
	}
	
	/**
	 * 세부공사정보(TN_CNTRWK) 명칭 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlNmListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlNmListByCntrwkID(cntrwkDtlVO);
	}
	
	/**
	 * 세부공사정보(TN_CNTRWK) 세부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlListByCntrwkID(cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkStatsResult(cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkMthdStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkMthdStatsResult(cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkDeptStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDeptStatsResult(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 엑셀 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkRoutCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkRoutCntStatsExcel( cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkDeptCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkDeptCntStatsExcel(cntrwkDtlVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 엑셀목록을 조회한다.
	 * @param cntrwkDtlVO - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkMthdCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkMthdCntStatsExcel(cntrwkDtlVO);
	}
	
}
