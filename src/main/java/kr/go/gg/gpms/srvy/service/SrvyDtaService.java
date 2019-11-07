package kr.go.gg.gpms.srvy.service;

import java.io.File;
import java.util.List;

import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

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
	void convertExcel(String csvFileNm, String excelFileNm, SrvyDtaVO srvyDtaVO) throws Exception;
	
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
	
}

