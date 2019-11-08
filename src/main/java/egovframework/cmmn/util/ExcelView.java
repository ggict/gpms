package egovframework.cmmn.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelView  extends AbstractExcelViewCustom {

		@Override
		protected void buildExcelDocument(Map<String,Object> modelMap, SXSSFWorkbook workbook,

			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String listType=  (String)modelMap.get("listType");
			if(listType!=null && "object".equals(listType)){
				writeListObject(modelMap, workbook, response);
			}
			else{
				writeListMap(modelMap, workbook, response);
			}

		}

		private void writeListMap(Map<String, Object> modelMap, SXSSFWorkbook workbook, HttpServletResponse response) throws UnsupportedEncodingException {
			String file_name = (String) modelMap.get("file_name");
			//file_name = URLEncoder.encode(file_name,"UTF-8");

			String[] excel_title = (String[]) modelMap.get("excel_title");
			String[] excel_column = (String[]) modelMap.get("excel_column");
			List<Map> data_list = (List<Map>)modelMap.get("data_list");

	        Sheet worksheet = null;
	        Row row = null;

	        worksheet = workbook.createSheet(file_name);

	        // YYK. 엑셀저장 헤더 디자인 추가
	        CellStyle styleHd = workbook.createCellStyle();
	        styleHd.setAlignment(CellStyle.ALIGN_CENTER);
	        styleHd.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	        styleHd.setAlignment(CellStyle.ALIGN_CENTER);
	        styleHd.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        styleHd.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        styleHd.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        styleHd.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        styleHd.setAlignment(CellStyle.ALIGN_CENTER);
			styleHd.setVerticalAlignment (CellStyle.VERTICAL_CENTER);
			styleHd.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			styleHd.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	        CellStyle cs1 = workbook.createCellStyle();
	        cs1.setAlignment(CellStyle.ALIGN_CENTER);
	        cs1.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	        cs1.setAlignment(CellStyle.ALIGN_CENTER);
	        cs1.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderBottom(HSSFCellStyle.BORDER_THIN);

	        Cell c;

	        row = worksheet.createRow(0);
	        for( int i=0; i<excel_title.length; i++) {
	        	c = row.createCell(i);
	        	c.setCellStyle(styleHd);
				c.setCellValue(excel_title[i]);
	        }

	        String tmp = "";
	        for(int i=0;i<data_list.size();i++){
				row = worksheet.createRow(i+1);
				Map map = (Map) data_list.get(i);
				for( int column=0; column<excel_column.length; column++) {
					tmp = ""+ map.get( excel_column[column] );
					if( "null".equals(tmp) )
						tmp = "";
					c = row.createCell(column);
					c.setCellStyle(cs1);
					c.setCellValue(tmp);
				}
	        }

	        for( int i=0; i<excel_title.length; i++) {
	        	//worksheet.autoSizeColumn(i);
	        	worksheet.setColumnWidth(i,worksheet.getColumnWidth(i)+4096);
	        }

			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(file_name,"UTF-8")+".xlsx");
		}

		private void writeListObject(Map<String, Object> modelMap, SXSSFWorkbook workbook, HttpServletResponse response) throws Exception {
			String file_name = (String) modelMap.get("file_name");
			//file_name = URLEncoder.encode(file_name,"UTF-8");

			String[] excel_title = (String[]) modelMap.get("excel_title");
			String[] excel_column = (String[]) modelMap.get("excel_column");
			List data_list = (List)modelMap.get("data_list");

	        Sheet worksheet = null;
	        Row row = null;

	        worksheet = workbook.createSheet(file_name);


	        CellStyle cs1 = workbook.createCellStyle();
	        cs1.setAlignment(CellStyle.ALIGN_CENTER);
	        cs1.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
	        cs1.setAlignment(CellStyle.ALIGN_CENTER);
	        cs1.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cs1.setBorderBottom(HSSFCellStyle.BORDER_THIN);

	        Cell c;

	        row = worksheet.createRow(0);
	        for( int i=0; i<excel_title.length; i++) {
	        	c = row.createCell(i);
	        	c.setCellStyle(cs1);
				c.setCellValue(excel_title[i]);
	        }


	        for(int i=0;i<data_list.size();i++){
				row = worksheet.createRow(i+1);
				Object objData = data_list.get(i);

//excel_column
				for( int column=0; column<excel_column.length; column++) {

					c = row.createCell(column);
					c.setCellStyle(cs1);
					String cellValue = "";
					try {
						cellValue = ""+ BeanUtils.getProperty(objData, excel_column[column]);
						if( "null".equals(cellValue) )
							cellValue = "";
						c.setCellValue(cellValue);

					} catch (Exception ex) {
					    throw ex;
						//System.out.println("Exception : " + ex.toString());
						//System.out.println("StackTrace : " + ex.getStackTrace());
					}

				}
	        }

	        for( int i=0; i<excel_title.length; i++) {
	        	//worksheet.autoSizeColumn(i);
	        	worksheet.setColumnWidth(i,worksheet.getColumnWidth(i)+4096);
	        }

			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(file_name,"UTF-8")+".xlsx");
		}
	}
