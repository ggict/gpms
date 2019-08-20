package kr.go.gg.gpms.srvydtaexcel.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

/**
 * 조사_자료_엑셀
 * TN_SRVY_DTA_EXCEL
 *
 * @Class Name : SrvyDtaExcelService.java
 * @Description : SrvyDtaExcel Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SrvyDtaExcelService {

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 등록한다.
	 * @param srvyDtaExcelVO - 등록할 정보가 담긴 SrvyDtaExcelVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 수정한다.
	 * @param srvyDtaExcelVO - 수정할 정보가 담긴 SrvyDtaExcelVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 삭제한다.
	 * @param srvyDtaExcelVO - 삭제할 정보가 담긴 SrvyDtaExcelVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return 조회한 TN_SRVY_DTA_EXCEL
	 * @exception Exception
	 */
	SrvyDtaExcelVO selectSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;

	
	public List selectSrvyDtaEvlInfoListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 총 갯수
	 * @exception
	 */
	int selectSrvyDtaExcelListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadResultList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과 갯수
	 * @exception
	 */
	int selectSrvyDtaExcelUploadResultCount(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadFileList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	int selectSrvyDtaExcelUploadFileCount(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록
	 * @exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaCompList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록 갯수
	 * @exception
	 */
	int selectSrvyDtaCompCount(SrvyDtaExcelVO srvyDtaExcelVO);	

	/**
	 * 조사자료 엑셀 데이터를 최소구간 조사 자료에 입력한다.
	 * @param srvyDtaExcelOne
	 * @return
	 */
	HashMap procSaveSurveyData(SrvyDtaExcelVO srvyDtaExcelOne);
	
	/**
	 * 입력한 조사자료 엑셀 데이터를 시스템에 반영한다.
	 * @param srvyDtaExcelVO
	 * @return
	 */
	HashMap procSrvyDtaSysReflct(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
	 * 최소구간 조사 자료를 이용하여 집계구간 조사자료 데이터를 산출한다.
	 * @param srvyDtaSttusVO
	 * @return
	 */
	HashMap procAggregateGeneral(SrvyDtaExcelVO srvyDtaExcelVO);

	/**
	 * 조사_자료 등록 대상 엑셀별 목록
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return 조사_자료 등록 대상 엑셀별 목록
	 * @exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaSrvyNoList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/api/srvyDtaExcelList.do"
	 * @exception Exception
	 */
	List<SrvyDtaExcelVO> srvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 갯수를 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	int srvyDtaExcelListCount(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 등록일자를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	List searchCreatDt(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사자료 예측 대상 목록을 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return 
	 * @exception Exception
	 */
	List<SrvyDtaExcelVO> selectSrvyDtaPredctList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
	
	/**
	 * 조사자료 예측 대상 목록의 갯수를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	int selectSrvyDtaPredctListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO);
	
	/**
     * 포장상태 평가정보 조회
     * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
     * @return 
     * @exception Exception
     */
    List<MummSctnSrvyDtaVO> srvyDtaEvlInfoDetailList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception;
    
    /**
     * 포장상태 평가정보 갯수 조회
     * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
     * @exception Exception
     */
    int srvyDtaEvlInfoDetailListCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO);
    
    /**
     * 관리자 > 수식관리 > 수식 변수 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @exception : Exception
     */
    List<PavFrmulaVO> srvyDtaEvlInitFmlaVar(PavFrmulaVO pavFrmulaVO) throws Exception;
    
    /**
     * 관리자 > 수식관리 > 수식 번호 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @exception : Exception
     */
    PavFrmulaVO selectPavFrmulaNo(PavFrmulaVO pavFrmulaVO) throws Exception;
    
    /**
     * 관리자 > 수식관리 > 수식 변경
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : int
     * @exception : Exception
     */
    int updatePavFrmulaVar(PavFrmulaVO pavFrmulaVO) throws Exception;

	List srvyDtaExcelListExcelDownload(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception;
}

