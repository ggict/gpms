package kr.go.gg.gpms.mummsctnsrvydta.service.impl;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 최소_구간_조사_자료
 *
 * @Class Name : MummSctnSrvyDtaDAO.java
 * @Description : MummSctnSrvyDta DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mummSctnSrvyDtaDAO")
public class MummSctnSrvyDtaDAO extends BaseDAO {
	
	private final Logger log = Logger.getLogger(this.getClass()); //log4j
	

	// 단일 데이터 검색용
	public Object getSelect(String sqlMapId, Object parameter){
		Object result = null;

		try {
			long startTime = System.currentTimeMillis();

			result = select(sqlMapId, parameter);

			long endTime = System.currentTimeMillis();
			if (log.isDebugEnabled()) {
				log.debug("["+sqlMapId+"]query execute TIME : " + (endTime - startTime) + "(ms)]]");
			}
		}
		catch(Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
			throw new RuntimeException (e);
		}

		return result;
	}
	
		
	@SuppressWarnings("unchecked")
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab2(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab2", mummSctnSrvyDtaVO); 
	}
	
	 /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    public MummSctnSrvyDtaVO selectMummSctnSrvyDta(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (MummSctnSrvyDtaVO) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDta", mummSctnSrvyDtaVO);
    }
    
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA)상세를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return 조회한 TN_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	public HashMap selectMummSctnSrvyDtaTab1(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return (HashMap) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab1", mummSctnSrvyDtaVO);
	}
	
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 목록을 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaList", mummSctnSrvyDtaVO);
	}
	
	/**
	 * 20m 세부 내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaTab3ListPage(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab3ListPage", mummSctnSrvyDtaVO);
	}
		
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 20m 세부 내역 개수를 조회한다.
	 * @return int형 
	 */
	public int selectMummSctnSrvyDtaTab3ListPageTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
		return (Integer) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaTab3ListPageTotalCount", mummSctnSrvyDtaVO);
	}
		
	/**
	 * 최소_구간_조사_자료(TN_MUMM_SCTN_SRVY_DTA) 총 갯수를 조회한다.
	 * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 총 갯수
	 * @exception
	 */
	public int selectMummSctnSrvyDtaListTotalCount(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
		return (Integer) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListTotalCount", mummSctnSrvyDtaVO);
	}

	/**
	 * 통합정보조회 선택 cell의 산정 데이터를 조회한다
	 */
	@SuppressWarnings("unchecked")
	public List<MummSctnSrvyDtaVO> prcClacPredctEvl(MummSctnSrvyDtaVO mummSctnSrvyDtaVO, String predctCen) {
		HashMap param = new HashMap();
		
    	param.put("p_CELL_IDS", mummSctnSrvyDtaVO.getCELL_ID());
    	param.put("p_PREDCT_CEN", predctCen);
    	param.put("p_MODE", "NONE");
    	
    	List tmpList = (List<MummSctnSrvyDtaVO>) list("mummSctnSrvyDtaDAO.PRC_CLAC_PREDCT_EVL", param);
    	
    	System.out.println("tmpList: " + tmpList.toString());
    	
    	return tmpList;
	}
	
	
	// ========================================================================================================== //
	// 통합정보조회 - 조사자료 : 선택한 셀로 섹션 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public MummSctnSrvyDtaVO mummSctnSrvyDtaSctnByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (MummSctnSrvyDtaVO) select("mummSctnSrvyDtaDAO.mummSctnSrvyDtaSctnByCell", mummSctnSrvyDtaVO);
    }
	
	// 통합정보조회 - 조사자료 : 선택한 섹션의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaSctnList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>) list("mummSctnSrvyDtaDAO.mummSctnSrvyDtaSctnList", mummSctnSrvyDtaVO);
    }
    
    // 통합정보조회 - 조사자료 : 선택한 셀의 정보 조회
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>) list("mummSctnSrvyDtaDAO.mummSctnSrvyDtaCellList", mummSctnSrvyDtaVO);
    }
    
    // 통합정보조회 - 조사정보 : 선택한 섹션의 셀 정보 조회. (섬네일 보여주기 위한 정보)
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return mummSctnSrvyDtaVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> mummSctnSrvyDtaCellInfo(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>) list("mummSctnSrvyDtaDAO.mummSctnSrvyDtaCellInfo", mummSctnSrvyDtaVO);
    }
    
    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 조회.
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> selectMummSctnSrvyDtaListByItgrtn(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListByItgrtn", mummSctnSrvyDtaVO);
    }

    // 통합정보조회 - 상태평가 : 선택한 영역의 조사정보 리스트 개수 조회.
    /**
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 MummSctnSrvyDtaVO
     * @return "/manage/mummsctnsrvydta/MummSctnSrvyDtaList"
     * @exception Exception
     */
    public int selectMummSctnSrvyDtaListByItgrtnCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
        return (Integer) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaListByItgrtnCnt", mummSctnSrvyDtaVO);
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
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> mummRoutCntStatsGPCI(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.mummRoutCntStatsGPCI", mummSctnSrvyDtaVO);
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
        return (List)list("mummSctnSrvyDtaDAO.mummRoutCntStatsExcel", mummSctnSrvyDtaVO);
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
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> mummDeptCntStats(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>)list("mummSctnSrvyDtaDAO.mummDeptCntStats", mummSctnSrvyDtaVO);
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
        return (List)list("mummSctnSrvyDtaDAO.mummDeptCntStatsExcel", mummSctnSrvyDtaVO);
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
    @SuppressWarnings("unchecked")
    public List integratedListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List)list("mummSctnSrvyDtaDAO.integratedListExcel", mummSctnSrvyDtaVO);
    };
    
    public MummSctnSrvyDtaVO selectMummSctnSrvyDtaByCell(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (MummSctnSrvyDtaVO) select("mummSctnSrvyDtaDAO.selectMummSctnSrvyDtaByCell", mummSctnSrvyDtaVO);
    }
    
    public Object getrdairival(Object object) throws Exception{
    	return list("mummSctnSrvyDtaDAO.getrdairival", object);
    }
}
