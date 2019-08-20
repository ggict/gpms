


package kr.go.gg.gpms.smdtalaststtus.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cmmn.service.impl.CmmnDAO;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

/**
 * 집계_자료_최신_현황
 *
 * @Class Name : SmDtaLastSttusDAO.java
 * @Description : SmDtaLastSttus DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("smDtaLastSttusDAO")
public class SmDtaLastSttusDAO extends BaseDAO {

	

	@Resource(name = "cmmnDAO")
	private CmmnDAO cmmnDAO;
	
	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 등록한다.
	 * @param smDtaLastSttusVO - 등록할 정보가 담긴 SmDtaLastSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return (Integer) insert("smDtaLastSttusDAO.insertSmDtaLastSttus", smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 수정한다.
	 * @param smDtaLastSttusVO - 수정할 정보가 담긴 SmDtaLastSttusVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return update("smDtaLastSttusDAO.updateSmDtaLastSttus", smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 삭제한다.
	 * @param smDtaLastSttusVO - 삭제할 정보가 담긴 SmDtaLastSttusVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return delete("smDtaLastSttusDAO.deleteSmDtaLastSttus", smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return 조회한 TN_SM_DTA_LAST_STTUS
	 * @exception Exception
	 */
	public SmDtaLastSttusVO selectSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return (SmDtaLastSttusVO) select("smDtaLastSttusDAO.selectSmDtaLastSttus", smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 목록을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SmDtaLastSttusVO> selectSmDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return (List<SmDtaLastSttusVO>)list("smDtaLastSttusDAO.selectSmDtaLastSttusList", smDtaLastSttusVO);
	}

	List selectSmDtaLastSttusListExcel(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception{
		return cmmnDAO.getSelectList("smDtaLastSttusDAO.selectSmDtaLastSttusListExcel", smDtaLastSttusVO);
	}
	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 총 갯수를 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	public int selectSmDtaLastSttusListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO) {
		return (Integer) select("smDtaLastSttusDAO.selectSmDtaLastSttusListTotalCount", smDtaLastSttusVO);
	}
	
	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록 을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SmDtaLastSttusVO> selectRouteEvlList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return (List<SmDtaLastSttusVO>)list("smDtaLastSttusDAO.selectRouteEvlList", smDtaLastSttusVO);
	}

	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록  총 갯수를 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	public int selectRouteEvlListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO) {
		return (Integer) select("smDtaLastSttusDAO.selectRouteEvlListTotalCount", smDtaLastSttusVO);
	}
	
	/**
	 * 집계된 조사자료를 현행화 한다
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap prcClacPredctRouteSrvyEvl(SmDtaLastSttusVO smDtaLastSttusVO) {
		HashMap param = new HashMap();
 
		param.put("p_ROUTE_CODE", smDtaLastSttusVO.getROAD_NO());
		param.put("p_SRVY_NO", smDtaLastSttusVO.getSRVY_NO());
    	param.put("p_USER_NO", smDtaLastSttusVO.getCRTR_NO());
    	param.put("p_MODE", "NONE");
    	HashMap resultVO = (HashMap) call("smDtaLastSttusDAO.PRC_CLAC_PREDCT_ROUTE_SRVY_EVL", param);
    	return resultVO;
	}
	
	/**
	 * 10년치 예측 데이터를 생성한다.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap prcClacPredctLast(SmDtaLastSttusVO smDtaLastSttusVO) {
		HashMap param = new HashMap();

		param.put("p_ROUTE_CODE", smDtaLastSttusVO.getROAD_NO());
		param.put("p_SRVY_NO", smDtaLastSttusVO.getSRVY_NO());
    	param.put("p_USER_NO", smDtaLastSttusVO.getCRTR_NO());
    	param.put("p_MODE", "NONE");
    	HashMap resultVO = (HashMap) call("smDtaLastSttusDAO.PRC_CLAC_PREDCT_LAST", param);
    	return resultVO;
	}

	@SuppressWarnings("unchecked")
    public List<SmDtaLastSttusVO> selectSrvyDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return (List<SmDtaLastSttusVO>)list("smDtaLastSttusDAO.selectSrvyDtaLastSttusList", smDtaLastSttusVO);
    }

    /**
     * 포장상태 평가 - 노선단위 수시병가 대상 목록  총 갯수를 조회한다.
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
     * @return TN_SM_DTA_LAST_STTUS 총 갯수
     * @exception
     */
    public int selectSrvyDtaLastSttusListCnt(SmDtaLastSttusVO smDtaLastSttusVO) {
        return (Integer) select("smDtaLastSttusDAO.selectSrvyDtaLastSttusListCnt", smDtaLastSttusVO);
    }
    
    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록을 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 공사자료 목록
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SmDtaLastSttusVO> selectCntrwkListBySect(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return (List<SmDtaLastSttusVO>)list("smDtaLastSttusDAO.selectCntrwkListBySect", smDtaLastSttusVO);
    }

    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록 총 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 공사자료 목록 총 갯수
     * @exception
     */
    public int selectCntrwkListBySectCnt(SmDtaLastSttusVO smDtaLastSttusVO) {
        return (Integer) select("smDtaLastSttusDAO.selectCntrwkListBySectCnt", smDtaLastSttusVO);
    }
    
    /**
     * 통계 > 포장상태 예측 데이터를 조회한다
     * @author    : skc
     * @date      : 2017. 11. 23.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 
     * @exception
     */
    @SuppressWarnings("unchecked")
	public List<SmDtaLastSttusVO> prcStatPredct(SmDtaLastSttusVO smDtaLastSttusVO, String mode) throws Exception {
		HashMap param = new HashMap();
		
    	param.put("p_ROUTE_CODE", smDtaLastSttusVO.getROUTE_CODE());
    	param.put("p_DEPT_CODE", smDtaLastSttusVO.getDEPT_CODE());
    	param.put("p_MODE", mode);
    	
    	return (List<SmDtaLastSttusVO>) procForList("smDtaLastSttusDAO.PRC_STAT_PREDCT", param);
	}
    
    /**
     * 통계 > 포장상태 예측 > 엑셀저장
     * @author    : skc
     * @date      : 2017. 11. 24.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 
     * @exception
     */
    @SuppressWarnings("unchecked")
	public List prcStatPredctExcel(SmDtaLastSttusVO smDtaLastSttusVO, String mode)  throws Exception {
		HashMap param = new HashMap();
		
    	param.put("p_ROUTE_CODE", smDtaLastSttusVO.getROUTE_CODE());
    	param.put("p_DEPT_CODE", smDtaLastSttusVO.getDEPT_CODE());
    	param.put("p_MODE", mode);
    	
    	return (List) procForList("smDtaLastSttusDAO.PRC_STAT_PREDCT_EXCEL", param);
	}
    
    /**
     * 평가정보 예측자료 상세정보의 이전 조사자료 GPCI 목록을 조회한다.
     * @author    : skc
     * @date      : 2017. 12. 08.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 조사자료 목록
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SmDtaLastSttusVO> selectSmDtaGnlGPCIList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return (List<SmDtaLastSttusVO>)list("smDtaLastSttusDAO.selectSmDtaGnlGPCIList", smDtaLastSttusVO);
    }
    
    /**
     * 포장상태 평가정보 상세조회 - 현행정보조회
     * @author    : JOY
     * @date      : 2017. 12. 11.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 현행정보
     * @exception : Exception
     */
    public SmDtaLastSttusVO selectSmDtaLastSttusBySrvy(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return (SmDtaLastSttusVO) select("smDtaLastSttusDAO.selectSmDtaLastSttusBySrvy", smDtaLastSttusVO);
    }

}
