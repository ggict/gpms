package kr.go.gg.gpms.srvy.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.srvy.service.SrvyDtaService;
import kr.go.gg.gpms.srvydta.service.model.SrvyDtaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;
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
	public void convertExcel(String csvFileNm, String excelFileNm, SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		
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
	            XSSFRow currentRow=sheet.createRow(RowNum);
	            for(int i=0;i<str.length;i++){
	                currentRow.createCell(i).setCellValue(str[i]);
	            }
	        }
	        
	        //노선코드 4자리수 채우기
	        String route_code = String.format("%04d", Integer.parseInt(srvyDtaExcelVO.getROUTE_CODE()));
	        
	        //설정 시트 추가
	        XSSFSheet OptSheet = wb.createSheet("설정");
	        XSSFRow optRow = OptSheet.createRow(1);
	        optRow.createCell(1).setCellValue("노선번호");
	        optRow.createCell(2).setCellValue(route_code);

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
	        	//dbRow.createCell(0).setCellValue(SRVY_YEAR//파라미터);
	        	//dbRow.createCell(1).setCellValue(SRVY_MT//파라미터);
	        	
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
	
}
