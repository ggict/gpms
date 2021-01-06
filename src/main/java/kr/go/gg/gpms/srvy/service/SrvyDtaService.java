package kr.go.gg.gpms.srvy.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.scheduling.annotation.Async;

import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;

/**
 * 조사_자료
 * TN_SRVY_DTA
 *
 * @Class Name : SrvyDtaService.java
 * @Description : SrvyDta Business class
 * @Modification Information
 *
 * @author antihyun2@gmail.com
 * @since 2019-10-23
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface SrvyDtaService {

	/**
	 * 조사자료 파일 압축풀기
	 * @param String fileName, File uploadFolder
	 * @return void
	 * @exception Exception
	 */
	void decmprsFile(String fileName, File uploadFolder) throws Exception;

	/**
	 * cvs -> excel 파일변환
	 * @param String csvFileNm, String excelFileNm
	 * @return void
	 * @exception Exception
	 */
	String convertExcel(String csvFileNm, String excelFileNm, SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 엑셀파일 이미지명 조회
	 * @param String excelFileNm
	 * @return list
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectImageList(String excelFileNm) throws Exception;

	/**
	 * 엑셀파일을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return excel file SrvyDtaVO
	 * @exception Exception
	 */
	SrvyDtaVO readExcel(SrvyDtaVO srvyDtaVO, String excelFileNm) throws Exception;

	/**
	 * 조사_자료(TN_SRVY_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료(TN_SRVY_DTA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectSrvyDtaList(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사자료 엑셀 데이터를 최소구간 조사 자료에 입력한다.
	 * @param srvyDtaExcelOne
	 * @return
	 */
	HashMap procSaveSurveyData(SrvyDtaVO srvyDtaOne);

	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)를 등록한다.
	 * @param String fileName, String srvyNo
	 * @return void
	 * @exception Exception
	 */
	void insertTmpExcelData(String fileName, String rootFileCours, SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료(TN_SRVY_DTA)을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return 조회한 TN_SRVY_DTA
	 * @exception Exception
	 */
	SrvyDtaVO selectSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 입력한 조사자료 엑셀 데이터를 시스템에 반영한다.
	 * @param srvyDtaExcelVO
	 * @return
	 */
	HashMap procSrvyDtaSysReflct(SrvyDtaVO srvyDtaOne);

	/**
	 * 조사_자료(TN_SRVY_DTA)를 수정한다.
	 * @param srvyDtaVO - 수정할 정보가 담긴 srvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectSrvyDtaUploadResultList(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과 갯수
	 * @exception
	 */
	int selectSrvyDtaUploadResultCount(SrvyDtaVO srvyDtaVO);

	/**
	 * 분석결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectAnalDataPopupResultList(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 분석결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 결과 갯수
	 * @exception
	 */
	int selectAnalDataPopupResultCount(SrvyDtaVO srvyDtaVO);

//	/**
//	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 조회한다.
//	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
//	 * @return 조회한 TMP_MUMM_SCTN_SRVY_DTA
//	 * @exception Exception
//	 */
//	SrvyDtaVO selectTmpExcelData() throws Exception;

	/**
	 * 최소구간 조사 자료를 이용하여 집계구간 조사자료 데이터를 산출한다.
	 * @param srvyDtaSttusVO
	 * @return
	 */
	HashMap procAggregateGeneral(SrvyDtaVO srvyDtaVO);

	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	int updateTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectSrvyDtaUploadFileList(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	int selectSrvyDtaUploadFileCount(SrvyDtaVO srvyDtaVO);

	/**
	 * AI_자료(TMP_AI_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertAiDta(SrvyDtaVO srvyDtaVO) throws Exception;

	/**
	 * AI_자료(TMP_AI_DTA)를 조회한다.
	 * @param
	 * @return 조회한 TMP_AI_DTA
	 * @exception Exception
	 */
	List<SrvyDtaVO> selectAiDtaList() throws Exception;

	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	int updateImgInfoOfTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception;

	@Async
	void procSrvyDtaAi(AttachFileVO attachFileParam, SrvyDtaVO srvyDtaVO, SrvyDtaVO srvyDtaOne, String fileName) throws Exception;


//	void deleteTmpMummSctnSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception;

	void deleteAnalReset(SrvyDtaVO srvyDtaVO) throws Exception;

	void evalReset(SrvyDtaVO srvyDtaVO) throws Exception;

}

