


package kr.go.gg.gpms.srvy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.cmmn.service.impl.CmmnDAO;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelDAO.java
 * @Description : SrvyDtaExcel DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyDtaDAO")
public class SrvyDtaDAO extends BaseDAO {


	@Resource(name = "cmmnDAO")
	private CmmnDAO cmmnDAO;

	/**
	 * 조사_자료_수식(TN_SRVY_DTA_FRMULA) 목록을 조회한다.
	 * @param 
	 * @return TN_SRVY_DTA_FRMULA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectSrvyDtaFrmulaList() throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectSrvyDtaFrmulaList");
	}

	/**
	 * 조사_자료(TN_SRVY_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {
		return (String) insert("srvyDtaDAO.insertSrvyDta", srvyDtaVO);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectSrvyDtaList(SrvyDtaVO srvyDtaVO) throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectSrvyDtaList", srvyDtaVO);
	}
	
	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)를 등록한다.
	 * @param @param String fileName, String srvyNo
	 * @return void
	 * @exception Exception
	 */
	public void insertTmpExcelData(Map<String, Object> params) throws Exception {
		insert("srvyDtaDAO.insertTmpExcelData", params);
	}
	
	/**
	 * 조사자료 엑셀 데이터를 최소구간 조사 자료에 입력한다.
	 */
	public HashMap procSaveSurveyData(SrvyDtaVO srvyDtaOne) {
		logger.info("[SrvyDtaVO result] " + srvyDtaOne.toString());
		
		HashMap param = new HashMap();
    	param.put("p_USER_NO", srvyDtaOne.getCRTR_NO());
    	param.put("p_SRVY_NO", srvyDtaOne.getSRVY_NO());
    	param.put("p_FRMULA_NM", srvyDtaOne.getFRMULA_NM());
    	param.put("P_ROW_COUNT", srvyDtaOne.getDATA_CO());
    	//param.put("p_RECORDSET", srvyDtaOne.getRECORDSET());
    	param.put("p_MODE", "NONE");
    	HashMap resultVO = (HashMap) select("srvyDtaDAO.PRC_SAVESURVEYDATA", param);
    	
    	System.out.println("[SrvyDtaVO result] " + resultVO.toString());
    	return resultVO;
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA)을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return 조회한 TN_SRVY_DTA
	 * @exception Exception
	 */
	public SrvyDtaVO selectSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {
		return (SrvyDtaVO) select("srvyDtaDAO.selectSrvyDtaList", srvyDtaVO);
	}

	/**
	 * 입력한 조사자료 엑셀 데이터를 시스템에 반영한다.
	 */
	public HashMap procSrvyDtaSysReflct(SrvyDtaVO srvyDtalOne) {
		logger.info("[procSrvyDtaSysReflctResultVO params] " + srvyDtalOne.toString());
		
		HashMap param = new HashMap();
    	param.put("p_USER_NO", srvyDtalOne.getCRTR_NO());
    	param.put("p_SRVY_NO", srvyDtalOne.getSRVY_NO());
    	
    	HashMap resultVO = (HashMap) select("srvyDtaDAO.PRC_SRVY_DTA_SYS_REFLCT", param);
    	logger.info("procSrvyDtaSysReflctResultVO: " + resultVO.toString());
    	return resultVO;
	}
	
	public int updateSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {
		return update("srvyDtaDAO.updateSrvyDta", srvyDtaVO);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectSrvyDtaUploadResultList(SrvyDtaVO srvyDtaVO) throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectSrvyDtaUploadResultList", srvyDtaVO);
	}

	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과 갯수
	 * @exception
	 */
	public int selectSrvyDtaUploadResultCount(SrvyDtaVO srvyDtaVO) {
		return (Integer) select("srvyDtaDAO.selectSrvyDtaUploadResultCount", srvyDtaVO);
	}

	//==============================================================================
	
	/**
	 * 최소구간 조사 자료를 이용하여 집계구간 조사자료 데이터를 산출한다.
	 * @param srvyDtaSttusVO
	 * @return
	 */
	public HashMap procAggregateGeneral(SrvyDtaExcelVO srvyDtaExcelOne) {
		HashMap<String, Comparable> param = new HashMap();

    	param.put("p_USER_NO", srvyDtaExcelOne.getCRTR_NO());
    	param.put("p_SRVY_NO", srvyDtaExcelOne.getSRVY_NO());
    	param.put("p_ROUTE_CODE", srvyDtaExcelOne.getROUTE_CODE());
    	param.put("p_DIRECT_CODE", srvyDtaExcelOne.getDIRECT_CODE());
    	param.put("p_TRACK", srvyDtaExcelOne.getTRACK());
    	param.put("p_STRTPT", srvyDtaExcelOne.getSTRTPT());
    	param.put("p_ENDPT", srvyDtaExcelOne.getENDPT());
    	param.put("p_FRMULA_NM", srvyDtaExcelOne.getFRMULA_NM());
    	param.put("p_MODE", "NONE");

    	HashMap resultVO = (HashMap) select("srvyDtaExcelDAO.PRC_AGGREGATE_GENERAL", param);
    	logger.info("procAggregateGeneralVO: " + resultVO.toString());
    	return resultVO;
	}

	/**
	 * 조사_자료 등록 대상 엑셀별 목록
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return 조사_자료 등록 대상 엑셀별 목록
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaSrvyNoList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaSrvyNoList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/api/srvyDtaExcelList.do"
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> srvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.srvyDtaExcelList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 갯수를 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int srvyDtaExcelListCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.srvyDtaExcelListCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 등록일자를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> searchCreatDt(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List)list("srvyDtaExcelDAO.searchCreatDt", srvyDtaExcelVO);
	}

	/**
	 * 조사자료 예측 대상 목록을 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaPredctList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaPredctList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료 예측 대상 목록의 갯수를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int selectSrvyDtaPredctListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaPredctListTotalCount", srvyDtaExcelVO);
	}

	/**
     * 포장상태 평가정보 조회
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> srvyDtaEvlInfoDetailList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>)list("srvyDtaExcelDAO.srvyDtaEvlInfoDetailList", mummSctnSrvyDtaVO);
    }


    public List selectSrvyDtaEvlInfoListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return cmmnDAO.getSelectList("srvyDtaExcelDAO.selectSrvyDtaEvlInfoListExcel", mummSctnSrvyDtaVO);
    }


    public List srvyDtaExcelListExcelDownload(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return cmmnDAO.getSelectList("srvyDtaExcelDAO.srvyDtaExcelListExcelDownload", srvyDtaExcelVO);
	}

    /**
     * 조사자료 예측 대상 목록의 갯수를 조회한다.
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @exception Exception
     */
    public int srvyDtaEvlInfoDetailListCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
        return (Integer) select("srvyDtaExcelDAO.srvyDtaEvlInfoDetailListCnt", mummSctnSrvyDtaVO);
    }

    /**
     * 관리자 > 수식관리 > 수식 변수 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     *
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : result
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<PavFrmulaVO> srvyDtaEvlInitFmlaVar(PavFrmulaVO pavFrmulaVO) throws Exception {
        return (List<PavFrmulaVO>)list("srvyDtaExcelDAO.srvyDtaEvlInitFmlaVar", pavFrmulaVO);
    }

    /**
     * 관리자 > 수식관리 > 수식 번호 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     *
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @exception : Exception
     */
    public PavFrmulaVO selectPavFrmulaNo(PavFrmulaVO pavFrmulaVO) throws Exception {
        return (PavFrmulaVO)select("srvyDtaExcelDAO.selectPavFrmulaNo", pavFrmulaVO);
    };

    /**
     * 관리자 > 수식관리 > 수식 변경
     * @author    : JOY
     * @date      : 2017. 11. 14.
     *
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePavFrmulaVar(PavFrmulaVO pavFrmulaVO) throws Exception {
        return update("srvyDtaExcelDAO.updatePavFrmulaVar", pavFrmulaVO);
    }
}
