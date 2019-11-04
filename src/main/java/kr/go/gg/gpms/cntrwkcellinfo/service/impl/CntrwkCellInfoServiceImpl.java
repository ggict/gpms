package kr.go.gg.gpms.cntrwkcellinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cntrwkcellinfo.service.CntrwkCellInfoService;
import kr.go.gg.gpms.cntrwkcellinfo.service.model.CntrwkCellInfoVO;

/**
 * 공사_셀_정보
 *
 * @Class Name : CntrwkCellInfoServiceImpl.java
 * @Description : CntrwkCellInfo Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-31
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cntrwkCellInfoService")
public class CntrwkCellInfoServiceImpl extends AbstractServiceImpl implements CntrwkCellInfoService {

	@Resource(name = "cntrwkCellInfoDAO")
	private CntrwkCellInfoDAO cntrwkCellInfoDAO;

	//@Resource(name="CntrwkCellInfoIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 등록한다.
	 * @param cntrwkCellInfoVO - 등록할 정보가 담긴 CntrwkCellInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrwkCellInfoVO.setId(id);

		return cntrwkCellInfoDAO.insertCntrwkCellInfo( cntrwkCellInfoVO);
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 수정한다.
	 * @param cntrwkCellInfoVO - 수정할 정보가 담긴 CntrwkCellInfoVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.updateCntrwkCellInfo( cntrwkCellInfoVO);
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 삭제한다.
	 * @param cntrwkCellInfoVO - 삭제할 정보가 담긴 CntrwkCellInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.deleteCntrwkCellInfo( cntrwkCellInfoVO);
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)을 조회한다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return 조회한 TN_CNTRWK_CELL_INFO
	 * @exception Exception
	 */
	public CntrwkCellInfoVO selectCntrwkCellInfo(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.selectCntrwkCellInfo( cntrwkCellInfoVO);
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	public List<CntrwkCellInfoVO> selectCntrwkCellInfoList(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.selectCntrwkCellInfoList( cntrwkCellInfoVO);
	}

	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 총 갯수
	 * @exception
	 */
	public int selectCntrwkCellInfoListTotalCount(CntrwkCellInfoVO cntrwkCellInfoVO) {
		return cntrwkCellInfoDAO.selectCntrwkCellInfoListTotalCount( cntrwkCellInfoVO);
	}
	
	/**
	 * 공사_셀별 직전 포장년도를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	public List<CntrwkCellInfoVO> selectPavYearListByCellId(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.selectPavYearListByCellId( cntrwkCellInfoVO);
	}
	
	/**
	 * 공사_셀별 직전 포장년도 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 총 갯수
	 * @exception
	 */
	public int selectPavYearListByCellIdTotalCount(CntrwkCellInfoVO cntrwkCellInfoVO) {
		return cntrwkCellInfoDAO.selectPavYearListByCellIdTotalCount( cntrwkCellInfoVO);
	}
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 리스트를 가져온다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 cntrwkCellInfoVO
	 * @return CELL_10 노선정보 리스트
	 * @exception Exception
	 */
	public List<Cell10VO> selectRouteInfoListByCellID(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.selectRouteInfoListByCellID(cntrwkCellInfoVO);
	}
	
	/**
	 * 공사_셀_정보(TN_CNTRWK_CELL_INFO)의 이전 포장상태를 조회한다.
	 * @param cntrwkCellInfoVO - 조회할 정보가 담긴 CntrwkCellInfoVO
	 * @return TN_CNTRWK_CELL_INFO 목록
	 * @exception Exception
	 */
	public List<Cell10VO> selectCntrwkBeforeCellInfoList(CntrwkCellInfoVO cntrwkCellInfoVO) throws Exception {
		return cntrwkCellInfoDAO.selectCntrwkBeforeCellInfoList(cntrwkCellInfoVO);
	}
	
}
