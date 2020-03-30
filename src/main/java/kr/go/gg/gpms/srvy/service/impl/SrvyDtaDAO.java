


package kr.go.gg.gpms.srvy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.cmmn.service.impl.CmmnDAO;
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
    	param.put("p_FRMULA_NM", "GPCI");
    	param.put("P_ROW_COUNT", srvyDtaOne.getDATA_CO());
    	//param.put("p_RECORDSET", srvyDtaOne.getRECORDSET());
    	param.put("p_MODE", "NONE");

   		HashMap  resultVO = (HashMap) select("srvyDtaDAO.PRC_SAVESURVEYDATA", param);
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

	/**
	 * 최소구간 조사 자료를 이용하여 집계구간 조사자료 데이터를 산출한다.
	 * @param srvyDtaSttusVO
	 * @return
	 */
	public HashMap procAggregateGeneral(SrvyDtaVO srvyDtaOne) {
		HashMap<String, Comparable> param = new HashMap();

    	param.put("p_USER_NO", srvyDtaOne.getCRTR_NO());
    	param.put("p_SRVY_NO", srvyDtaOne.getSRVY_NO());
    	param.put("p_ROUTE_CODE", srvyDtaOne.getROUTE_CODE());
    	param.put("p_DIRECT_CODE", srvyDtaOne.getDIRECT_CODE());
    	param.put("p_TRACK", srvyDtaOne.getTRACK());
    	param.put("p_STRTPT", srvyDtaOne.getSTRTPT());
    	param.put("p_ENDPT", srvyDtaOne.getENDPT());
    	param.put("p_FRMULA_NM", "GPCI");
    	param.put("p_MODE", "NONE");

    	HashMap resultVO = (HashMap) select("srvyDtaDAO.PRC_AGGREGATE_GENERAL", param);
    	logger.info("procAggregateGeneralVO: " + resultVO.toString());
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

	/**
	 * 분석결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectAnalDataPopupResultList(SrvyDtaVO srvyDtaVO) throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectAnalDataPopupResultList", srvyDtaVO);
	}

	/**
	 * 분석결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 결과 갯수
	 * @exception
	 */
	public int selectAnalDataPopupResultCount(SrvyDtaVO srvyDtaVO) {
		return (Integer) select("srvyDtaDAO.selectAnalDataPopupResultCount", srvyDtaVO);
	}

	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return 조회한 TMP_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	public SrvyDtaVO selectTmpExcelData(SrvyDtaVO srvyDtaOne) throws Exception {
		return (SrvyDtaVO) select("srvyDtaDAO.selectTmpExcelData", srvyDtaOne);
	}

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
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception {
		return update("srvyDtaDAO.updateTmpExcelData", srvyDtaVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectSrvyDtaUploadFileList(SrvyDtaVO srvyDtaVO) throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectSrvyDtaUploadFileList", srvyDtaVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaUploadFileCount(SrvyDtaVO srvyDtaVO) {
		return (Integer) select("srvyDtaDAO.selectSrvyDtaUploadFileCount", srvyDtaVO);
	}

	/**
	 * AI_자료(TMP_AI_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAiDta(SrvyDtaVO srvyDtaVO) throws Exception {
		return (String) insert("srvyDtaDAO.insertAiDta", srvyDtaVO);
	}

	/**
	 * AI_자료(TMP_AI_DTA)를 조회한다.
	 * @param
	 * @return 조회한 TMP_AI_DTA
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaVO> selectAiDtaList() throws Exception {
		return (List<SrvyDtaVO>)list("srvyDtaDAO.selectAiDtaList");
	}

	/**
	 * AI_자료(TMP_AI_DTA)을 삭제한다.
	 * @param
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteAiDta() throws Exception {
		return delete("srvyDtaDAO.deleteAiDta");
	}

	/**
	 * 임시_최소_구간_조사_자료 (TMP_MUMM_SCTN_SRVY_DTA)을 삭제한다.
	 * @param
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteTmpMummSctnSrvyDta(SrvyDtaVO srvyDtaOne) throws Exception {
	    return delete("srvyDtaDAO.deleteTmpMummSctnSrvyDta", srvyDtaOne);
	}

	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateImgInfoOfTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception {
		return update("srvyDtaDAO.updateImgInfoOfTmpExcelData", srvyDtaVO);
	}

    /**
     * 최소_구간_조사_자료 (TN_MUMM_SCTN_SRVY_DTA)을 삭제한다.
     * @param
     * @return 삭제 결과
     * @exception Exception
     */
    public int deleteTnMummSctnSrvyDta(SrvyDtaVO srvyDtaOne) throws Exception {
        return delete("srvyDtaDAO.deleteTnMummSctnSrvyDta", srvyDtaOne);
    }

    /**
     * 집계_자료_일반_현황 (TN_SM_DTA_GNL_STTUS)을 삭제한다.
     * @param
     * @return 삭제 결과
     * @exception Exception
     */
    public int deleteTnSmDtaGnlSttus(SrvyDtaVO srvyDtaOne) throws Exception {
        return delete("srvyDtaDAO.deleteTnSmDtaGnlSttus", srvyDtaOne);
    }
}
