package kr.go.gg.gpms.srvy.service;

import java.io.File;

/**
 * 조사_자료
 * TN_SRVY_DTA_EXCEL
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
	void convertExcel(String csvFileNm, String excelFileNm) throws Exception;
	
}

