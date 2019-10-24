package kr.go.gg.gpms.srvy.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.srvy.service.SrvyDtaService;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStream;

/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelServiceImpl.java
 * @Description : SrvyDtaExcel Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("srvyDtaService")
public class SrvyDtaServiceImpl extends AbstractServiceImpl implements SrvyDtaService {

	/*
	@Resource(name = "srvyDtaDAO")
	private SrvyDtaDAO srvyDtaDAO;
	*/
	/**
	 * 압축풀기
	 * @param String fileName, File uploadFolder
	 * @return void
	 * @exception Exception
	 */
	@Override
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
                    //파일이면 파일 만들기
                    createFile(file, zis);
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
	
	private static void createFile(File file, ZipInputStream zis) throws Exception {

        //디렉토리 확인
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
	
	/**
	 * cvs -> excel 파일변환
	 * @param String csvFileNm, String excelFileNm
	 * @return void
	 * @exception Exception
	 */
	@Override
	public void convertExcel(String csvFileNm, String excelFileNm) throws Exception {
		
		XSSFWorkbook wb = new XSSFWorkbook();
		FileOutputStream fos = null;
        try {
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
	        fos = new FileOutputStream(excelFileNm);
	        wb.write(fos);
	        fos.flush();
	        fos.close();
		} catch (Exception e) {
			e.getMessage();
			fos.flush();
			fos.close();
			//e.printStackTrace();
		} finally {
			fos.flush();
			fos.close();
		}
	}
	
}
