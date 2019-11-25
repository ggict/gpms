package egovframework.cmmn.util;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.text.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.xml.bind.DatatypeConverter;

import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;

/*
 * 2018.01.25. YYK. 팝업화면 엑셀저장 기능
 *
 */

public class ExcelViewSttemnt extends AbstractExcelViewCustom {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, SXSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SttemntVO sttemntVO = (SttemntVO) model.get("sttemntVO");
		RpairVO rpairVO = (RpairVO) model.get("rpairVO");

		String strClient = request.getHeader("User-Agent");

		String file_name = (String) model.get("file_name");
		//file_name = new String ( file_name.getBytes("KSC5601"), "8859_1");

		HSSFWorkbook wb = new HSSFWorkbook();      //워크북 생성


		//---------------------------------------------------------------
		//엑셀 처리 시작
		//Workbook objWorkBook = new HSSFWorkbook();      //워크북 생성
		HSSFSheet objSheet = wb.createSheet("new sheet");     //워크시트 생성
		Row objRow = null;        //로우 생성
		Cell objCell = null;       //셀 생성


		//-----------------------------------------------------------------
		//스타일 설정


		//스타일 객체 생성
		CellStyle styleHd = wb.createCellStyle();    //제목 스타일
		CellStyle styleSub = wb.createCellStyle();   //부제목 스타일
		CellStyle styleBody = wb.createCellStyle();   //내용 스타일


		//제목 폰트
		Font font = wb.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);


		//부제목 폰트
		Font font2 = wb.createFont();
		font2.setFontHeightInPoints((short)10);
		font2.setBoldweight((short)font.BOLDWEIGHT_BOLD);



		//제목 스타일에 폰트 적용, 정렬
		styleHd.setFont(font);
		styleHd.setBorderBottom(CellStyle.BORDER_THIN);
		styleHd.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleHd.setBorderLeft(CellStyle.BORDER_THIN);
		styleHd.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleHd.setBorderRight(CellStyle.BORDER_THIN);
		styleHd.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleHd.setBorderTop(CellStyle.BORDER_THIN);
		styleHd.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);

		styleSub.setBorderBottom(CellStyle.BORDER_THIN);
		styleSub.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderLeft(CellStyle.BORDER_THIN);
		styleSub.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderRight(CellStyle.BORDER_THIN);
		styleSub.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderTop(CellStyle.BORDER_THIN);
		styleSub.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setAlignment (CellStyle.ALIGN_CENTER);
		styleSub.setVerticalAlignment (CellStyle.VERTICAL_CENTER);

		styleHd.setAlignment(CellStyle.ALIGN_CENTER);
		styleHd.setVerticalAlignment (CellStyle.VERTICAL_CENTER);
		styleHd.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
		styleHd.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		//부제목 스타일 설정

		styleSub.setFont(font2);

		styleSub.setBorderBottom(CellStyle.BORDER_THIN);
		styleSub.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderLeft(CellStyle.BORDER_THIN);
		styleSub.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderRight(CellStyle.BORDER_THIN);
		styleSub.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setBorderTop(CellStyle.BORDER_THIN);
		styleSub.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleSub.setAlignment (CellStyle.ALIGN_CENTER);
		styleSub.setVerticalAlignment (CellStyle.VERTICAL_CENTER);
		//styleSub.setWrapText(true);
		styleSub.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		styleSub.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//styleSub.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
		//styleSub.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


		//내용 스타일 설정
		styleBody.setBorderBottom(CellStyle.BORDER_THIN);
		styleBody.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleBody.setBorderLeft(CellStyle.BORDER_THIN);
		styleBody.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleBody.setBorderRight(CellStyle.BORDER_THIN);
		styleBody.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleBody.setBorderTop(CellStyle.BORDER_THIN);
		styleBody.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
		styleBody.setAlignment (CellStyle.ALIGN_CENTER);
		styleBody.setVerticalAlignment (CellStyle.VERTICAL_CENTER);


		//-------------------------------------------------------
		//길이 설정
		objSheet.setColumnWidth((short)0,(short)3600);
		objSheet.setColumnWidth((short)1,(short)7600);
		objSheet.setColumnWidth((short)2,(short)3600);
		objSheet.setColumnWidth((short)3,(short)7600);

		//-------------------------------------------------------

		//1행
		// 설정
		objRow = objSheet.createRow((short)0);
		objRow.setHeight ((short) 0x200);

		//병합
		objSheet.addMergedRegion(new Region(0,(short)0,0,(short)3));

		// 내용
		objCell = objRow.createCell((short)0);
		//objCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		objCell.setCellValue("처리상태");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleHd);


		//-------------------------------------------
		//2행
		// 설정
		objRow = objSheet.createRow((short)1);
		objRow.setHeight ((short) 0x200);

		//병합
		objSheet.addMergedRegion(new Region(1,(short)1,1,(short)3));

		// 내용
		objCell = objRow.createCell((short)0);
		//objCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		objCell.setCellValue("처리상태");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getPRCS_STTUS_NM());
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);

		//-------------------------------------------
		//3행
		// 설정
		objRow = objSheet.createRow((short)2);
		objRow.setHeight ((short) 0x200);

		// 병합
		objSheet.addMergedRegion(new Region(2,(short)0,2,(short)3));
		// 내용
		objCell = objRow.createCell((short)0);
		objCell.setCellValue("신고정보");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleHd);

		//-------------------------------------------
		//4행
		objRow = objSheet.createRow((short)3);
		objRow.setHeight ((short) 0x150);

		//
		objCell = objRow.createCell((short)0);
		objCell.setCellValue("등록번호");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getPTH_RG_NO());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellValue("관할기관");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)3);
		String aa = (String)sttemntVO.getLOWEST_DEPT_NM();
		objCell.setCellValue((String)sttemntVO.getLOWEST_DEPT_NM());
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//5행
		objRow = objSheet.createRow((short)4);
		objRow.setHeight ((short) 0x150);

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("신고자");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getBSNM_NM());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellValue("차량번호");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)3);
		objCell.setCellValue((String)sttemntVO.getVHCLE_NO());
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//6행
		objRow = objSheet.createRow((short)5);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(5,(short)1,5,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("신고일시");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getSTTEMNT_DT());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);

		//길이 설정



		//-------------------------------------------
		//7행
		objRow = objSheet.createRow((short)6);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(6,(short)1,6,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("도로명");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getRN_ADRES());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);



		//-------------------------------------------
		//8행
		objRow = objSheet.createRow((short)7);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(7,(short)1,7,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("지번주소");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getLNM_ADRES());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);



		//-------------------------------------------
		//9행 (보수정보)
		objRow = objSheet.createRow((short)8);
		objRow.setHeight ((short) 0x200);

		//병합
		objSheet.addMergedRegion(new Region(8,(short)0,8,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("보수정보");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleHd);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleHd);


		//-------------------------------------------
		//10행
		objRow = objSheet.createRow((short)9);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(9,(short)1,9,(short)3));


		objCell = objRow.createCell((short)0);
		objCell.setCellValue("파손유형");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)sttemntVO.getDMG_TYPE_NM());
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);

		//-------------------------------------------
		//11행
		objRow = objSheet.createRow((short)10);
		objRow.setHeight ((short) 0x150);


		objCell = objRow.createCell((short)0);
		objCell.setCellValue("담당자");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)rpairVO.getCHARGER_NM());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellValue("연락처");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)3);
		objCell.setCellValue((String)rpairVO.getCTTPC());
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//12행
		objRow = objSheet.createRow((short)11);
		objRow.setHeight ((short) 0x150);

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("시공사");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		//objCell.setCellValue((String)rpairVO.getCO_NO());
		objCell.setCellValue("현장팀");
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellValue("보수금액(원)");
		objCell.setCellStyle(styleSub);

		// 천단위 콤마 찍기 포맷변환

		String getRpairAmount = (String)rpairVO.getRPAIR_AMOUNT() ;
		Long rpairAmount = (long) 0;
		String rpairAmountStr = null;

		if ( getRpairAmount != null && !getRpairAmount.isEmpty()) {
			rpairAmount = Long.parseLong(getRpairAmount);

			DecimalFormat decimal = new DecimalFormat("#,###");
			rpairAmountStr = decimal.format(rpairAmount);
		}

		objCell = objRow.createCell((short)3);
		objCell.setCellValue(rpairAmountStr);
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//13행
		objRow = objSheet.createRow((short)12);
		objRow.setHeight ((short) 0x150);


		//병합
		objSheet.addMergedRegion(new Region(12,(short)1,12,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("보수규모");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);

		String rprscl1 = (String)rpairVO.getRPRSCL_WIDTH();
		String rprscl2 = (String)rpairVO.getRPRSCL_VRTICL();
		String rprscl3 = (String)rpairVO.getRPRSCL_AR();
		String rprscl4 = (String)rpairVO.getRPRSCL_DP();

		String RPRSCL_WIDTH  = rprscl1.isEmpty() ? "" :  "가로(m): "+ rprscl1;
		String RPRSCL_VRTICL = rprscl2.isEmpty() ? "" :  "   세로(m): "+  rprscl2;
		String RPRSCL_AR     = rprscl3.isEmpty() ? "" :  "   면적(㎡): "+ rprscl3;
		String RPRSCL_DP     = rprscl4.isEmpty() ? "" :  "   깊이(m): "+  rprscl4;


		//String RPRSCL_WIDTH = "가로(m): "+ (String)rpairVO.getRPRSCL_WIDTH();
		//String RPRSCL_VRTICL = ",  세로(m): "+ (String)rpairVO.getRPRSCL_VRTICL();
		//String RPRSCL_AR = ",  면적(㎡): "+ (String)rpairVO.getRPRSCL_AR();
		//String RPRSCL_DP = ",  깊이(m): "+ (String)rpairVO.getRPRSCL_DP();
		String RPRSCL = RPRSCL_WIDTH + RPRSCL_VRTICL + RPRSCL_AR + RPRSCL_DP ;
		objCell.setCellValue(RPRSCL);
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//14행

		objRow = objSheet.createRow((short)13);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(13,(short)1,13,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("비고");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)rpairVO.getRM());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);


		/*
		//-------------------------------------------
		//15행 위치도 (사용안함)

		objRow = objSheet.createRow((short)14);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(14,(short)0,14,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("위치도");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);

		//-------------------------------------------
		//16행 위치도 (사용안함)

		objRow = objSheet.createRow((short)15);
		objRow.setHeight ((short) 0x1000);

		//병합
		objSheet.addMergedRegion(new Region(15,(short)0,15,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);

		// YYk. 위치도 삽입 부분
		String loc_view = (String)sttemntVO.getLOC_VIEW();

		//BASE64Decoder

		try {

			//String data = loc_view.split(",")[1];

			//byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);


			//if (loc_view != null && !loc_view.isEmpty() ){
			    drawSheet( objSheet, wb, 0, 15, 4, 16, loc_view);
			//}

		}catch(NullPointerException e) {
    		e.printStackTrace();
    	} catch(Exception e ) {
    		e.printStackTrace();
    	}
*/

		//-------------------------------------------
		//16행 보수전, 보수후
		objRow = objSheet.createRow((short)14);
		objRow.setHeight ((short) 0x150);

		//병합
		objSheet.addMergedRegion(new Region(14,(short)0,14,(short)1));
		objSheet.addMergedRegion(new Region(14,(short)2,14,(short)3));

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("보수 전");
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)2);
		objCell.setCellValue("보수 후");
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//17행 확인일자, 보수일자
		objRow = objSheet.createRow((short)15);
		objRow.setHeight ((short) 0x150);

		objCell = objRow.createCell((short)0);
		objCell.setCellValue("확인일자");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)1);
		objCell.setCellValue((String)rpairVO.getCNFIRM_DT());
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)2);
		objCell.setCellValue("보수일자");
		objCell.setCellStyle(styleSub);

		objCell = objRow.createCell((short)3);
		objCell.setCellValue((String)rpairVO.getRPAIR_DT());
		objCell.setCellStyle(styleBody);


		//-------------------------------------------
		//18행 이미지 부분

		String[] bfePhoto = rpairVO.getRPAIR_BFE_PHOTO_PATH();
		String[] aftPhoto = rpairVO.getRPAIR_AFT_PHOTO_PATH();

		int maxlength = bfePhoto.length >= aftPhoto.length ? bfePhoto.length : aftPhoto.length ;

		if ( maxlength > 0 ){
			for (int i=0; i < maxlength; i++ ){
				objRow = objSheet.createRow((short)16+ i);
				objRow.setHeight ((short) 0x800);

				//병합
				objSheet.addMergedRegion(new Region(16+i,(short)0,16+i,(short)1));
				objSheet.addMergedRegion(new Region(16+i,(short)2,16+i,(short)3));

				objCell = objRow.createCell((short)0);
				//objCell.setCellValue("보수 전 이미지가 없습니다.");
				objCell.setCellStyle(styleBody);

				objCell = objRow.createCell((short)1);
				objCell.setCellStyle(styleBody);
				objCell = objRow.createCell((short)2);
				//objCell.setCellValue("보수 후 이미지가 없습니다.");
				objCell.setCellStyle(styleBody);

				objCell = objRow.createCell((short)3);
				objCell.setCellStyle(styleBody);

				try {
					drawSheet( objSheet, wb, 0, 16+i, 2, 17+i, bfePhoto[i]);
				} catch (Exception e) {
				    throw e;
				}

				try {
					drawSheet( objSheet, wb, 0, 16+i, 2, 17+i, aftPhoto[i]);
				} catch (Exception e) {
				    throw e;
				}
			}
		}


		/*objRow = objSheet.createRow((short)16);
		objRow.setHeight ((short) 0x800);

		//병합
		objSheet.addMergedRegion(new Region(16,(short)0,16,(short)1));
		objSheet.addMergedRegion(new Region(16,(short)2,16,(short)3));

		objCell = objRow.createCell((short)0);
		//objCell.setCellValue("보수 전 이미지가 없습니다.");
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)1);
		objCell.setCellStyle(styleBody);
		objCell = objRow.createCell((short)2);
		//objCell.setCellValue("보수 후 이미지가 없습니다.");
		objCell.setCellStyle(styleBody);

		objCell = objRow.createCell((short)3);
		objCell.setCellStyle(styleBody);
*/

	/*	// 이미지 삽입 부분

		String[] bfePhoto = rpairVO.getRPAIR_BFE_PHOTO_PATH();
		String[] aftPhoto = rpairVO.getRPAIR_AFT_PHOTO_PATH();

		try {
			if ( bfePhoto.length > 0 ){
				for (int i=0; i < bfePhoto.length; i++ ){
					drawSheet( objSheet, wb, 0, 16, 2, 17, bfePhoto[i]);
				}
			}
		} catch (Exception e) {

		}

		try {
			if ( aftPhoto.length > 0 ){
				for (int i=0; i < aftPhoto.length; i++ ){
					drawSheet( objSheet, wb, 0, 16, 2, 17, aftPhoto[i]);
				}
			}
		} catch (Exception e) {

		}
*/
		//-------------------------------------------


		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_name,"UTF-8") + ".xls");
		response.setContentType("application/vnd.ms-excel; charset=gb2312");

        // Write the file out.
        //FileOutputStream fileOut = new FileOutputStream(fileName); // 이미지 삽입된 엑셀파일
        //OutputStream fileOut= response.getOutputStream();
        OutputStream fileOut = new BufferedOutputStream(response.getOutputStream());

        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();

	}

    private static void drawSheet( HSSFSheet sheet, HSSFWorkbook wb, int row1, int col1, int row2, int col2, String imgPath ) throws IOException {
        // Create the drawing patriarch.  This is the top level container for
        // all shapes. This will clear out any existing shapes for that sheet.
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		HSSFCreationHelper helper = wb.getCreationHelper();
        HSSFClientAnchor anchor;
        //anchor = helper.createClientAnchor();
        		anchor = new HSSFClientAnchor(0,0,0,0,(short)row1,col1,(short)row2,col2); // 이미지 크기조절은 여기서..
                //anchor = new HSSFClientAnchor(0,0,0,40,(short)1,15,(short)2,16); // 이미지 크기조절은 여기서..


        anchor.setAnchorType( 2 );
        patriarch.createPicture(anchor, loadPicture( imgPath, wb )); // 삽입 할 이미지
    }

    @SuppressWarnings("unused")
	private static int loadPicture( String path, HSSFWorkbook wb ) throws IOException {
        int pictureIndex;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream( path);
            if (fis == null){
            	return -1;
            }
            bos = new ByteArrayOutputStream( );
            int c;
            while ( (c = fis.read()) != -1) {
                bos.write( c );
            }
            pictureIndex = wb.addPicture( bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG  );
        } finally {
            if (fis != null) fis.close();
            if (bos != null) bos.close();
        }
        return pictureIndex;
    }


}


