package kr.go.gg.gpms.mummsctnsrvydta.service.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.gg.gpms.mummsctnsrvydta.service.MummSctnSrvyDtaService;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 최소_구간_조사_자료
 *
 * @Class Name : MummSctnSrvyDtaServiceImpl.java
 * @Description : MummSctnSrvyDta Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mummSctnSrvyDtaService")
public class MummSctnSrvyDtaServiceImpl extends AbstractServiceImpl implements MummSctnSrvyDtaService {

	@Resource(name = "mummSctnSrvyDtaDAO")
	private MummSctnSrvyDtaDAO mummSctnSrvyDtaDAO;

	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA)상세를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return 조회한 TN_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	public HashMap selectMummSctnSrvyDtaTab1(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		HashMap resultVO = (HashMap) mummSctnSrvyDtaDAO.getSelect("mummSctnSrvyDtaDAO.SelectMummSctnSrvyDtaTab1", mummSctnSrvyDtaVO);
		
		return resultVO;
		
	}
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 개수를 조회한다.
	 * @return MummSctnSrvyDtaVO 
	 */
	public MummSctnSrvyDtaVO selectMummSctnSrvyDta(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDta(mummSctnSrvyDtaVO);
	}
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA)상세를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return 조회한 TN_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab2(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab2(mummSctnSrvyDtaVO);
		
	}
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaList( mummSctnSrvyDtaVO);
	}
	
    
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab3ListPage(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab3ListPage( mummSctnSrvyDtaVO);
	}
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 개수를 조회한다.
	 * @return int형 
	 */
	public int selectMummSctnSrvyDtaTab3ListPageTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab3ListPageTotalCount( mummSctnSrvyDtaVO);
	}

	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 총 갯수
	 * @exception
	 */
	public int selectMummSctnSrvyDtaListTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
		return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListTotalCount( mummSctnSrvyDtaVO);
	}

	/**
	 * 통합정보조회 선택 cell의 산정 데이터를 조회한다
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	public List<MummSctnSrvyDtaVO> prcClacPredctEvl(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception{
		
		return mummSctnSrvyDtaDAO.prcClacPredctEvl(mummSctnSrvyDtaVO, "false"); 
	}
	
	/**
	 * 통합정보조회 선택 cell의 10년치 예측 목록을 조회한다
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
	public List<MummSctnSrvyDtaVO> prcClacPredctEvlPredct(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception{
		
		return mummSctnSrvyDtaDAO.prcClacPredctEvl(mummSctnSrvyDtaVO, "true");
	}
    
	// ========================================================================================================== //
	// 통합정보조회 - 조사자료 : 선택한 셀로 섹션 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public MummSctnSrvyDtaVO mummSctnSrvyDtaSctnByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        
        return mummSctnSrvyDtaDAO.mummSctnSrvyDtaSctnByCell(mummSctnSrvyDtaVO);
        
    }
    
	// 통합정보조회 - 조사자료 : 선택한 섹션의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaSctnList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        
        return mummSctnSrvyDtaDAO.mummSctnSrvyDtaSctnList(mummSctnSrvyDtaVO);
        
    }
    
    // 통합정보조회 - 조사자료 : 선택한 셀의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        
        return mummSctnSrvyDtaDAO.mummSctnSrvyDtaCellList(mummSctnSrvyDtaVO);
        
    }
    
    // 통합정보조회 - 조사정보 : 선택한 섹션의 셀 정보 조회. (섬네일 보여주기 위한 정보)
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellInfo(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception{
        
        return (List<MummSctnSrvyDtaVO>) mummSctnSrvyDtaDAO.mummSctnSrvyDtaCellInfo(mummSctnSrvyDtaVO);
        
    };
    
    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 조회.
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaListByItgrtn(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListByItgrtn(mummSctnSrvyDtaVO);
    }

    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 개수 조회.
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    public int selectMummSctnSrvyDtaListByItgrtnCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
        return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListByItgrtnCnt(mummSctnSrvyDtaVO);
    }
    
    /**
     * 통계 > 포장상태 평가 > 노선별통계 > 데이터조회
     * @author    : JOY
     * @date      : 2017. 11. 17.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    public List<MummSctnSrvyDtaVO> mummRoutCntStatsGPCI(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.mummRoutCntStatsGPCI(mummSctnSrvyDtaVO);
    };
    
    /**
     * 통계 > 포장상태 평가 > 노선별통계 > 엑셀
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    public List mummRoutCntStatsExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.mummRoutCntStatsExcel(mummSctnSrvyDtaVO);
    };
    
    
    /**
     * 통계 > 포장상태 평가 > 관리기관별통계 > 데이터조회
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    public List<MummSctnSrvyDtaVO> mummDeptCntStats(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.mummDeptCntStats(mummSctnSrvyDtaVO);
    };
    
    /**
     * 통계 > 포장상태 평가 > 관리기관별통계 > 엑셀
     * @author    : JOY
     * @date      : 2017. 11. 20.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return    : List<MummSctnSrvyDtaVO>
     * @exception : Exception
     */
    public List mummDeptCntStatsExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.mummDeptCntStatsExcel(mummSctnSrvyDtaVO);
    };
 
    /**
     * 통합정보조회 > 포장상태평가조회 > 엑셀저장
     * @author    : JOY
     * @date      : 2018. 9. 12.
     * 
     * @param     : mummSctnSrvyDtaVO - 조회할 정보가 담긴 VO
     * @return    : List
     * @exception : Exception
     */
    public List integratedListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.integratedListExcel(mummSctnSrvyDtaVO);
    };
    
    public MummSctnSrvyDtaVO selectMummSctnSrvyDtaByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaByCell(mummSctnSrvyDtaVO);
    }
}
