package kr.go.gg.gpms.srvy.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import egovframework.cmmn.util.EgovProperties;
import egovframework.cmmn.util.FileUploadUtils;
import egovframework.cmmn.util.ZipUtils;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.attachfile.service.impl.AttachFileDAO;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.mummsctnsrvydta.service.impl.MummSctnSrvyDtaDAO;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;
import kr.go.gg.gpms.srvy.service.SrvyDtaService;
import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStream;

/**
 * 조사_자료_수식
 *
 * @Class Name : SrvyDtaExcelServiceImpl.java
 * @Description : SrvyDtaExcel Business Implement class
 * @Modification Information
 *
 * @author antihyun2@gmail.com
 * @since 2019-10-23
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@EnableAsync
@Service("srvyDtaService")
public class SrvyDtaServiceImpl extends AbstractServiceImpl implements SrvyDtaService {

	@Resource(name = "srvyDtaDAO")
	private SrvyDtaDAO srvyDtaDAO;
	
	@Resource(name = "attachFileDAO")
	private AttachFileDAO attachFileDAO;
	
	@Resource(name = "mummSctnSrvyDtaDAO")
	private MummSctnSrvyDtaDAO mummSctnSrvyDtaDAO;
	
	@Resource(name = "pathInfoProperties")
	protected Properties pathInfoProperties;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Autowired
    DataSource dataSource;
	
	/**
	 * 압축풀기
	 * @param String fileName, File uploadFolder
	 * @return void
	 * @exception Exception
	 */
	public void decmprsFile(String fileName, File uploadFolder) throws Exception {
		
        File zipFile = new File(fileName);
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry zipentry = null;
        String fileNm = "";
        
        try {
            // 균열분석이미지 png폴더 생성
            File pngFolder = new File(uploadFolder + File.separator + "균열분석이미지");
            pngFolder.mkdirs();
        	
        	//파일 스트림
            fis = new FileInputStream(zipFile);
            
            //Zip 파일 스트림
            zis = new ZipInputStream(fis);
            
            //entry가 없을때까지 뽑기
            while ((zipentry = zis.getNextEntry()) != null) {
                fileNm = zipentry.getName();
                File file = new File(uploadFolder, fileNm);
                //entiry가 폴더면 폴더 생성
                if (zipentry.isDirectory()) {
                    file.mkdirs();
                } else {
                	File parentDir = new File(file.getParent());

                    //디렉토리 없으면 생성
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }

                    //파일 스트림
                    FileOutputStream fos = null;
                    try {
                    	fos = new FileOutputStream(file); 
                        byte[] buffer = new byte[256];
                        int size = 0;
                        //Zip스트림으로부터 byte 뽑아내기
                        while ((size = zis.read(buffer)) > 0) {
                            //byte로 파일 만들기
                            fos.write(buffer, 0, size);
                        }
                    } catch (Exception e) {
                    	fos.close();
                    }
                }
            }
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (zis != null)
                zis.close();

            if (fis != null)
                fis.close();
		}
	}
	
	/**
	 * cvs -> excel 파일변환
	 * @param String csvFileNm, String excelFileNm
	 * @return void
	 * @exception Exception
	 */
	public void convertExcel(String csvFileNm, String excelFileNm, SrvyDtaVO srvyDtaVO) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		FileOutputStream fos = null;
        try {
        	//csv 파일 변환
	        XSSFSheet sheet = wb.createSheet("분석자료");
	        String currentLine=null;
	        int RowNum=-1;
	        BufferedReader br = new BufferedReader(new FileReader(csvFileNm));
	        while ((currentLine = br.readLine()) != null) {
	            String str[] = currentLine.split(",");
	            RowNum++;
	            XSSFRow currentRow = sheet.createRow(RowNum);
	            for(int i=0;i<str.length;i++){
	                currentRow.createCell(i).setCellValue(str[i]);
	            }
	        }
	        
	        //조사일자 변환
	        String srvy_year = srvyDtaVO.getSRVY_DE().substring(0,4);
	        String srvy_mt = srvyDtaVO.getSRVY_DE().substring(5,7);
	        String srvy_de = srvyDtaVO.getSRVY_DE().replace("-", "");
	        
	        //노선코드 4자리수 채우기
	        String route_code = String.format("%04d", Integer.parseInt(srvyDtaVO.getROAD_NO()));
	        
	        //설정 시트 추가
	        XSSFSheet OptSheet = wb.createSheet("설정");
	        XSSFRow optRow = null;
	        
	        for(int i=1; i<8; i++ ) {
	        	optRow = OptSheet.createRow(i);
        		if(i==1) {
        			optRow.createCell(1).setCellValue("노선번호");
	    	        optRow.createCell(2).setCellValue(srvyDtaVO.getROAD_NO());	
        		} else if(i==2) {
        			optRow.createCell(1).setCellValue("도로명");
	    	        optRow.createCell(2).setCellValue(srvyDtaVO.getROAD_NAME());
        		} else if(i==3) {
        			optRow.createCell(1).setCellValue("조사구간시점");
	    	        optRow.createCell(2).setCellValue(sheet.getRow(5).getCell(1).getStringCellValue());
        		} else if(i==4) {
        			optRow.createCell(1).setCellValue("조사구간종점");
	    	        optRow.createCell(2).setCellValue(sheet.getRow(6).getCell(1).getStringCellValue());
        		} else if(i==5) {
        			optRow.createCell(1).setCellValue("조사종류");
	    	        optRow.createCell(2).setCellValue("1");
        		} else if(i==6) {
        			optRow.createCell(1).setCellValue("차로폭");
	    	        optRow.createCell(2).setCellValue("3.5");
        		} else if(i==7) {
        			optRow.createCell(1).setCellValue("차로길이");
	    	        optRow.createCell(2).setCellValue("10");
        		}
	        }

	        //조사_자료_수식(TN_SRVY_DTA_FRMULA) 목록을 조회
	        List<SrvyDtaVO> frmulaList = srvyDtaDAO.selectSrvyDtaFrmulaList();
	        
	        //DBLoading 시트 추가
	        XSSFSheet dbSheet = wb.createSheet("DBLoading");
	        //DBLoading 첫행
	        XSSFRow dbFirstRow = dbSheet.createRow(0);
	        //첫행 컬럼
	        for(int i=0; i<frmulaList.size(); i++) {
	        	dbFirstRow.createCell(i).setCellValue(frmulaList.get(i).getFRMULA_NM());
	        }
	        
	        //첫행 컬럼명이므로 1부터 시작
	        int rowNum = sheet.getPhysicalNumberOfRows()-13;
	        String _arithFrmula = "";
	        String newSeCode = "";
	        String frmulaSeCode = "";
	        String arithFrmla = "";
	        
	        //row 반복
	        for(int i=1; i<=rowNum; i++) {
	        	XSSFRow dbRow = dbSheet.createRow(i);
	        	//cell 반복
	        	for(int j=0; j<frmulaList.size(); j++) {
	        		arithFrmla = frmulaList.get(j).getARITH_FRMLA();
	        		frmulaSeCode = frmulaList.get(j).getFRMULA_SE_CODE();
	        		if(StringUtils.isNotEmpty(arithFrmla)) {
	        			if(StringUtils.isNotEmpty(frmulaSeCode)) {
	        				if(!frmulaSeCode.contains(",")) {	//코드 단건
	        					if(frmulaSeCode.charAt(1)>=47 && frmulaSeCode.charAt(1)<=58) {	//숫자
	        						newSeCode = frmulaSeCode.substring(0,1).concat(String.valueOf(i+Integer.parseInt(frmulaSeCode.substring(1))-1));
	        					} else {	//문자
	        						newSeCode = frmulaSeCode.substring(0,2).concat(String.valueOf(i+Integer.parseInt(frmulaSeCode.substring(2))-1));
	        					}
	        					_arithFrmula = arithFrmla.replace(frmulaSeCode, newSeCode);
	        				} else {	//코드 다건
	        					char arrayKey;
	        					String arrayCode = "";
	        					String[] arrayFrmulaSeCode = frmulaSeCode.split(",");
	        					int arrayLength = arrayFrmulaSeCode.length;
        					
	        					for(int k=0; k<arrayLength; k++) {
	        						arrayKey = arrayFrmulaSeCode[k].charAt(1);
	        						arrayCode = arrayFrmulaSeCode[k];
	        						if(arrayKey>=47 && arrayKey<=58) {	//숫자
	        							newSeCode = arrayCode.substring(0,1).concat(String.valueOf(i+Integer.parseInt(arrayCode.substring(1))-1));
	        						}else {	//문자
	        							newSeCode = arrayCode.substring(0,2).concat(String.valueOf(i+Integer.parseInt(arrayCode.substring(2))-1));
	        						}
	        						if(k==0) {
	        							_arithFrmula = arithFrmla.replace(arrayCode, newSeCode);
	        						} else {
	        							_arithFrmula = _arithFrmula.replace(arrayCode, newSeCode);
	        						}
	        					}
	        				}
	        				
		        			dbRow.createCell(j).setCellFormula(_arithFrmula);
	        			}//if frmulaSeCode null 
	        			else if("0".equals(arithFrmla)) {
	        				dbRow.createCell(j).setCellValue("0");
	        			}
        				else {
        					dbRow.createCell(j).setCellFormula(arithFrmla);
        				}
		        	}//if arithFrmla null
        			else {
        				dbRow.createCell(j).setCellValue("");
        			}
	        	//파라미터 값 적용
	        	dbRow.createCell(0).setCellValue(srvy_year);					//조사년도
	        	dbRow.createCell(1).setCellValue(srvy_mt);						//조사월
	        	dbRow.createCell(2).setCellValue(route_code);					//노선 4자수 가공
	        	dbRow.createCell(3).setCellValue(srvyDtaVO.getDIRECT_CODE());	//행성
	        	dbRow.createCell(4).setCellValue(srvyDtaVO.getTRACK());			//차선
	        	dbRow.createCell(20).setCellValue(srvy_de);			//조사일자
	        	}//for j
	        }//for i
	        
	        fos = new FileOutputStream(excelFileNm);
	        wb.write(fos);
	        
	        fos.close();
		} catch (Exception e) {
			fos.close();
		} 
	}
	
	/**
	 * 엑셀파일 이미지명 조회
	 * @param String excelFileNm
	 * @return list
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectImageList(String excelFileNm) throws Exception {

		// 엑셀파일 실행
		FileInputStream fis = new FileInputStream(excelFileNm);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("DBLoading");
		List<SrvyDtaVO> list = new ArrayList<SrvyDtaVO>();
		String img = "";
		
		try {
			// 엑셀데이터 row, cell 건수 확인
			int rowNum = sheet.getPhysicalNumberOfRows();

			// 엑셀 식(formula)으로 된 데이터 읽기
			FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();

			// i => 엑셀 row의 수
			for (int i = 1; i < rowNum; i++) { // 헤더제외. 1부터 시작
				img = formulaEval.evaluate(sheet.getRow(i).getCell(16)).formatAsString();
				
				SrvyDtaVO vo = new SrvyDtaVO();
				vo.setRDSRFC_IMG_FILE_NM_1(img);
				list.add(vo);
			}
			
		} catch (Exception e) {
			fis.close();
		} finally {
			fis.close();
		}
		return list;
	}
	
	/**
	 * 엑셀파일을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return excel file SrvyDtaVO
	 * @exception Exception
	 */
	public SrvyDtaVO readExcel(SrvyDtaVO srvyDtaVO, String excelFileNm) throws Exception {
		
		FileInputStream fis = new FileInputStream(excelFileNm);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("DBLoading");
		
		FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
		int rowNum = sheet.getPhysicalNumberOfRows();	//총 row수
		int dataCo = rowNum-1;	//헤더 제거
		String routeCode = sheet.getRow(1).getCell(2).getStringCellValue();
		String directCode = sheet.getRow(1).getCell(3).getStringCellValue();
		String track = sheet.getRow(1).getCell(4).getStringCellValue();
		Double _strtpt = Double.parseDouble(formulaEval.evaluate(sheet.getRow(1).getCell(5)).formatAsString());
		int strtpt = (int) Math.floor(_strtpt); 
		Double _endpt = Double.parseDouble(formulaEval.evaluate(sheet.getRow(dataCo).getCell(6)).formatAsString());
		int endpt = (int) Math.floor(_endpt);
		String srvyDe = sheet.getRow(1).getCell(20).getStringCellValue();
		String _srvyNm = formulaEval.evaluate(sheet.getRow(1).getCell(26)).formatAsString();
		String srvyNm = _srvyNm.replace("\"", "");
		
		srvyDtaVO.setDATA_CO(Integer.toString(dataCo));	
		srvyDtaVO.setROUTE_CODE(routeCode);
		srvyDtaVO.setDIRECT_CODE(directCode);
		srvyDtaVO.setTRACK(track);
		srvyDtaVO.setSTRTPT(String.valueOf(strtpt));
		srvyDtaVO.setENDPT(String.valueOf(endpt));
		srvyDtaVO.setSRVY_DE(srvyDe);
		srvyDtaVO.setSRVY_NM(srvyNm);
		 
		return srvyDtaVO;
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {

		return srvyDtaDAO.insertSrvyDta(srvyDtaVO);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectSrvyDtaList(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.selectSrvyDtaList(srvyDtaVO);
	}
	
	@Override
	public HashMap procSaveSurveyData(SrvyDtaVO srvyDtaOne) {
		return srvyDtaDAO.procSaveSurveyData(srvyDtaOne);
	}
	
	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)를 등록한다.
	 * @param @param String fileName, String srvyNo
	 * @return void
	 * @exception Exception
	 */
	public void insertTmpExcelData(String fileName, String rootFileCours) throws Exception {
		
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("DBLoading");
		String colName = "";
		int rowNum = sheet.getPhysicalNumberOfRows();
		int cellNum = sheet.getRow(0).getLastCellNum();
		Map<String, Object> params = new HashMap<String, Object>();
		// 도면이미지 파일명
		String jpgFileName = "";

		// 엑셀 식(formula)으로 된 데이터 읽기
		FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();

		// i => 엑셀 row의 수
		for (int i = 1; i < rowNum; i++) { // 헤더제외. 1부터 시작
			if (!formulaEval.evaluate(sheet.getRow(i).getCell(0)).formatAsString().equals("0.0")) {

				// j => 엑셀 cell의 수
				for (int j = 0; j < cellNum; j++) {
					colName = sheet.getRow(0).getCell(j).getStringCellValue();
					
					//셀 열 vo
					String val = formulaEval.evaluate(sheet.getRow(i).getCell(j)) == null ? "" : formulaEval.evaluate(sheet.getRow(i).getCell(j)).formatAsString();
					if (val.contains(".") && val.split("[.]")[1].equals("0")) {
						val = val.split("[.]")[0];
					}
					val = val.replace("\"", "").trim();
					val = val.replace("#NUM!", "0").trim();
					params.put(colName, val);

					// 노면이미지파일명 ex> "지371호-상행-2차로-3_3구간-(지중)_s000010000.jpg"
					if ("RDSRFC_IMG_FILE_NM_1".equals(colName)) {
						jpgFileName = val;
					}
				}
				
				/* 노면이미지파일명을 균열이미지파일명 컬럼에 별도로 넣음
				   frnt_img_file_nm,frnt_img_file_cours,cr_img_file_nm,cr_img_file_cours
				 */
				//String imageFileCours = fileCoursParam.replace("_분석결과","_표면결함");
				String pngFileName = jpgFileName.replace(".jpg", ".png");
				params.put("RDSRFC_IMG_FILE_NM_2", pngFileName);
				params.put("FRNT_IMG_FILE_NM", jpgFileName);
				params.put("FRNT_IMG_FILE_COURS", rootFileCours + File.separator + "JPG");
				params.put("CR_IMG_FILE_NM", pngFileName);
				params.put("CR_IMG_FILE_COURS", rootFileCours + File.separator + "균열분석이미지");
				
				srvyDtaDAO.insertTmpExcelData(params);
			}
		}
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA)을 조회한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return 조회한 TN_SRVY_DTA
	 * @exception Exception
	 */
	public SrvyDtaVO selectSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {
		SrvyDtaVO resultVO = srvyDtaDAO.selectSrvyDta(srvyDtaVO);
		 
		return resultVO;
	}
	
	/**
	 * 입력한 조사자료 엑셀 데이터를 시스템에 반영한다.
	 */
	@Override
	public HashMap procSrvyDtaSysReflct(SrvyDtaVO srvyDtaOne) {
		return srvyDtaDAO.procSrvyDtaSysReflct(srvyDtaOne);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA)를 수정한다.
	 * @param srvyDtaVO - 수정할 정보가 담긴 srvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSrvyDta(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.updateSrvyDta(srvyDtaVO);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectSrvyDtaUploadResultList(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.selectSrvyDtaUploadResultList(srvyDtaVO);
	}
	
	/**
	 * 조사_자료(TN_SRVY_DTA) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과 갯수
	 * @exception
	 */
	public int selectSrvyDtaUploadResultCount(SrvyDtaVO srvyDtaVO) {
		return srvyDtaDAO.selectSrvyDtaUploadResultCount(srvyDtaVO);
	}
	
	/**
	 * 분석결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectAnalDataPopupResultList(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.selectAnalDataPopupResultList(srvyDtaVO);
	}
	
	/**
	 * 분석결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_MUMM_SCTN_SRVY_DTA 결과 갯수
	 * @exception
	 */
	public int selectAnalDataPopupResultCount(SrvyDtaVO srvyDtaVO) {
		return srvyDtaDAO.selectAnalDataPopupResultCount(srvyDtaVO);
	}
	
	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 조회한다.
	 * @param 
	 * @return 조회한 TMP_MUMM_SCTN_SRVY_DTA
	 * @exception Exception
	 */
	public SrvyDtaVO selectTmpExcelData() throws Exception {
		SrvyDtaVO resultVO = srvyDtaDAO.selectTmpExcelData();
		return resultVO;
	}
	
	/**
	 * 최소구간 조사 자료를 이용하여 집계구간 조사자료 데이터를 산출한다.
	 * @param srvyDtaSttusVO
	 * @return
	 */
	@Override
	public HashMap procAggregateGeneral(SrvyDtaVO srvyDtaVO) {
		return srvyDtaDAO.procAggregateGeneral(srvyDtaVO);
	}
	
	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.updateTmpExcelData(srvyDtaVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 목록
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectSrvyDtaUploadFileList(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.selectSrvyDtaUploadFileList(srvyDtaVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaVO
	 * @return TN_SRVY_DTA 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaUploadFileCount(SrvyDtaVO srvyDtaVO) {
		return srvyDtaDAO.selectSrvyDtaUploadFileCount(srvyDtaVO);
	}
	
	/**
	 * AI_자료(TMP_AI_DTA)를 등록한다.
	 * @param srvyDtaVO - 등록할 정보가 담긴 srvyDtaVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertAiDta(SrvyDtaVO srvyDtaVO) throws Exception {

		return srvyDtaDAO.insertAiDta(srvyDtaVO);
	}
	
	/**
	 * AI_자료(TMP_AI_DTA)를 조회한다.
	 * @param 
	 * @return 조회한 TMP_AI_DTA
	 * @exception Exception
	 */
	public List<SrvyDtaVO> selectAiDtaList() throws Exception {
		return srvyDtaDAO.selectAiDtaList();
	}
	
	/**
	 * 임시_최소_구간_조사_자료(TMP_MUMM_SCTN_SRVY_DTA)을 수정한다.
	 * @param srvyDtaVO - 조회할 정보가 담긴 SrvyDtaVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateImgInfoOfTmpExcelData(SrvyDtaVO srvyDtaVO) throws Exception {
		return srvyDtaDAO.updateImgInfoOfTmpExcelData(srvyDtaVO);
	}
	
	@Async
    public void procSrvyDtaAi(AttachFileVO attachFileParam, SrvyDtaVO srvyDtaVO) throws Exception {
        // Connection 오브젝트 생성, 저장소 바인딩, 참조변수 값 리턴
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setAutoCommit(false); // 트랜잭션 시작
        
        List<AttachFileVO> imgList = attachFileDAO.selectAttachDetailFileImgList(attachFileParam);
		
		for(int k=0; k<imgList.size(); k++) {
			String imgFilePath = imgList.get(k).getFILE_COURS() + File.separator + imgList.get(k).getORGINL_FILE_NM();
			String imgFileNm = imgList.get(k).getORGINL_FILE_NM();
			
		    File aiFileNm = new File(imgFilePath);

		    HttpClient client = new DefaultHttpClient();
		    HttpPost post = new HttpPost("http://test.muhanit.kr:21542/analyzer/");

		    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		    builder.addBinaryBody("image", aiFileNm);
		    builder.addTextBody("modules", "crack");
		    HttpEntity entity = builder.build();
		    //
		    post.setEntity(entity);
		    HttpResponse responseTmp = client.execute(post);

		    HttpEntity entity2 = responseTmp.getEntity();
		    String responseString = EntityUtils.toString(entity2, "UTF-8");
		    
		    //=======================================================================================
		    
			JSONParser paser = new JSONParser();

			JSONObject obj = (JSONObject) paser.parse(responseString);
			
			// [results]
			JSONArray parse_results_list = (JSONArray) obj.get("results");
			for (int i = 0; i < parse_results_list.size(); i++) {
				JSONObject result_i = (JSONObject) parse_results_list.get(i);

				// [results.ARRAYS.result_image] - Base64 encoded PNG image 
				String result_image = (String)result_i.get("result_image");
				
				// Base64 PNG image -> decode -> save
				String pngFileName = imgList.get(k).getORGINL_FILE_NM().replace(".jpg", ".png");
				String pngFileFullPath = pathInfoProperties.getProperty("file.upload.path") + imgList.get(k).getROOT_FILE_COURS() + File.separator + "균열분석이미지" + File.separator + pngFileName;
				// png file download
			    byte[] data = Base64.decodeBase64(result_image);
			    try (OutputStream stream = new FileOutputStream(pngFileFullPath)) {
			        stream.write(data);
			    }
				
				// [results.ARRAYS[0].region_result] - 균열이 확인된 CELL에 대하여 정보를 가진 CELL배열 
				JSONArray region_result_list = (JSONArray)result_i.get("region_result");
				
				for (int j = 0; j < region_result_list.size(); j++) {
					JSONObject imsi = (JSONObject) region_result_list.get(j);
					
					// [results.ARRAYS[0].region_result.ARRAYS.region_type] 해당 CELL의 균열 종류 - {"ac","tc",....}
					srvyDtaVO.setREGION_TYPE((String) imsi.get("region_type"));
					
					// [results.ARRAYS[0].region_result.ARRAYS.area] 해당 균열CELL의 면적
					if(imsi.get("area") != null && !"".equals(imsi.get("area"))) {
						srvyDtaVO.setAREA(String.valueOf(imsi.get("area")));	
					}
					
					// [results.ARRAYS[0].region_result.ARRAYS.length] 해당 균열CELL의 길이
					if(imsi.get("length") != null && !"".equals(imsi.get("length"))) {
						srvyDtaVO.setLEN(String.valueOf(imsi.get("length")));	
					} 
					
					// [results.ARRAYS[0].region_result.ARRAYS.severity] 해당 균열CELL의 심각도 - {"medium","low",...}
					srvyDtaVO.setSEVERITY((String) imsi.get("severity"));
					
					srvyDtaVO.setRESULT_IMAGE(result_image);
					
					//srvyDtaVO.setRDSRFC_IMG_FILE_NM_1(testFileNm);
					//srvyDtaService.insertAiDta(srvyDtaVO);
					srvyDtaDAO.insertAiDta(srvyDtaVO);
					
					// tn_mumm_sctn_srvy_dta : source(jpg)이미지 / result(png)이미지 경로 저장
					//mummSctnSrvyDtaDAO.updateMummSctnSrvyDta()
				}
			}
			

			
			//select
			List<SrvyDtaVO> aiDtaList = srvyDtaDAO.selectAiDtaList();
			
			String regionType = "";
			String severity = "";
			String val = ""; 
			srvyDtaVO.setRDSRFC_IMG_FILE_NM_1(imgFileNm);
			for(int i=0; i<aiDtaList.size(); i++) {
				regionType = aiDtaList.get(i).getREGION_TYPE();
				severity = aiDtaList.get(i).getSEVERITY();
				val = aiDtaList.get(i).getAI_SUM_VALUE();
			
				if("tc".equals(regionType) && "low".equals(severity)) {
					srvyDtaVO.setTC_LOW(val);
				}
				if("tc".equals(regionType) && "medium".equals(severity)) {
					srvyDtaVO.setTC_MED(val);
				}
				if("tc".equals(regionType) && "hi".equals(severity)) {
					srvyDtaVO.setTC_HI(val);
				}
				
				if("lc".equals(regionType) && "low".equals(severity)) {
					srvyDtaVO.setLC_LOW(val);
				}
				if("lc".equals(regionType) && "medium".equals(severity)) {
					srvyDtaVO.setLC_MED(val);
				}
				if("lc".equals(regionType) && "hi".equals(severity)) {
					srvyDtaVO.setLC_HI(val);
				}
				
				if("ac".equals(regionType) && "low".equals(severity)) {
					srvyDtaVO.setAC_LOW(val);
				}
				if("ac".equals(regionType) && "med".equals(severity)) {
					srvyDtaVO.setAC_MED(val);
				}
				if("ac".equals(regionType) && "hi".equals(severity)) {
					srvyDtaVO.setAC_HI(val);
				}
				
				if("patch".equals(regionType)) {
					srvyDtaVO.setPTCHG_CR(val);
				}
				
				if("phothole".equals(regionType)) {
					srvyDtaVO.setPOTHOLE_CR(val);
				}
				
				srvyDtaDAO.updateTmpExcelData(srvyDtaVO);
				conn.commit();
			}
			
				srvyDtaDAO.deleteAiDta();
		}
	   
	    DataSourceUtils.releaseConnection(conn, dataSource); // 커넥션을 닫음

        // 동기화 작업을 종료하고 저장소를 비운다
        TransactionSynchronizationManager.unbindResource(this.dataSource);
        TransactionSynchronizationManager.clearSynchronization();
	}
	
}
