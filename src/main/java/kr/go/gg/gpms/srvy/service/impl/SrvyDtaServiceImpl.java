package kr.go.gg.gpms.srvy.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
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

@Service("srvyDtaService")
public class SrvyDtaServiceImpl extends AbstractServiceImpl implements SrvyDtaService {

	@Resource(name = "srvyDtaDAO")
	private SrvyDtaDAO srvyDtaDAO;
	
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
                        e.printStackTrace();
                    }
                }
            }
		} catch (Exception e) {
			e.getMessage();
			//e.printStackTrace();
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
	        				newSeCode = frmulaSeCode.substring(0,1).
	        							concat(String.valueOf(i+Integer.parseInt(frmulaSeCode.substring(1))-1));
	        				_arithFrmula = arithFrmla.replace(frmulaSeCode, newSeCode);
		        			dbRow.createCell(j).setCellFormula(_arithFrmula);
	        			}//if frmulaSeCode null 
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
	        fos.flush();
	        fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			fos.flush();
			fos.close();
			//e.printStackTrace();
		} finally {
			fos.flush();
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
			//int cellNum = sheet.getRow(0).getLastCellNum();

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
	
}
