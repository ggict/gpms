package kr.go.gg.gpms.cntrwkdtl.service.impl;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.cmmn.web.SessionManager;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.cntrwk.service.impl.CntrwkDAO;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
import kr.go.gg.gpms.cntrwkdtl.service.CntrwkDtlService;
import kr.go.gg.gpms.cntrwkdtl.service.model.CntrwkDtlVO;
import kr.go.gg.gpms.company.service.impl.CompanyDAO;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.pavmatrl.service.PavMatrlService;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

/**
 * 공사상세정보
 *
 * @Class Name : CntrwkDtlServiceImpl.java
 * @Description : CntrwkDtl Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 * 
 * 		Copyright (C) All right reserved.
 */

@Service("cntrwkDtlService")
public class CntrwkDtlServiceImpl extends AbstractServiceImpl implements CntrwkDtlService {

	@Resource(name = "cntrwkDtlDAO")
	private CntrwkDtlDAO cntrwkDtlDAO;

	@Resource(name = "cntrwkDAO")
	private CntrwkDAO cntrwkDAO;
	
	@Resource(name = "companyDAO")
	private CompanyDAO companyDAO;
	
	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	@Resource(name = "pavMatrlService")
	private PavMatrlService pavMatrlService;

	@Autowired
	SessionManager sessionManager;

	// @Resource(name="CntrwkDtlIdGnrService")
	// private EgovIdGnrService egovIdGnrService;

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 등록한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 등록할 정보가 담긴 CntrwkDtlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		// String id = egovIdGnrService.getNextStringId();
		// cntrwkDtlVO.setId(id);

		return cntrwkDtlDAO.insertCntrwkDtl(cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 수정한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 수정할 정보가 담긴 CntrwkDtlVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.updateCntrwkDtl(cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.deleteCntrwkDtl(cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 삭제한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 삭제할 정보가 담긴 CntrwkDtlVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteCntrwkDtlByCntrwkId(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.deleteCntrwkDtlByCntrwkId(cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL)을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @return 조회한 TN_CNTRWK_DTL
	 * @exception Exception
	 */
	public CntrwkDtlVO selectCntrwkDtl(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		CntrwkDtlVO resultVO = cntrwkDtlDAO.selectCntrwkDtl(cntrwkDtlVO);
		/*
		 * if (resultVO == null) throw processException("info.nodata.msg");
		 */
		return resultVO;
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 cntrwkDtlVO
	 * @return TN_CNTRWK_DTL 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlList(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlList(cntrwkDtlVO);
	}

	/**
	 * 공사상세정보(TN_CNTRWK_DTL) 총 갯수를 조회한다. @param searchVO - 조회할 정보가 담긴
	 * cntrwkDtlVO @return TN_CNTRWK_DTL 총 갯수 @exception
	 */
	public int selectCntrwkDtlListTotalCount(CntrwkDtlVO cntrwkDtlVO) {
		return cntrwkDtlDAO.selectCntrwkDtlListTotalCount(cntrwkDtlVO);
	}

	/**
	 * 세부공사정보(TN_CNTRWK) 엑셀목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List selectCntrwkDtlListExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlListExcel(cntrwkDtlVO);
	}

	/**
	 * 세부공사정보(TN_CNTRWK) 명칭 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlNmListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlNmListByCntrwkID(cntrwkDtlVO);
	}

	/**
	 * 세부공사정보(TN_CNTRWK) 세부 목록을 조회한다.
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 cntrwkVO
	 * @return TN_CNTRWK 목록
	 * @exception Exception
	 */
	public List<CntrwkDtlVO> selectCntrwkDtlListByCntrwkID(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDtlListByCntrwkID(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkStatsResult(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkMthdStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkMthdStatsResult(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List selectCntrwkDeptStatsResult(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDeptStatsResult(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 노선별 통계 엑셀 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkRoutCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkRoutCntStatsExcel(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 관리기관별 통계 목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkDeptCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkDeptCntStatsExcel(cntrwkDtlVO);
	}

	/**
	 * 공사정보(TN_CNTRWK, TN_CNTRWK_DTL) 공법별 통계 엑셀목록을 조회한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 조회할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public List cntrwkMthdCntStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkMthdCntStatsExcel(cntrwkDtlVO);
	}
	
	@Override
	public List<CntrwkDtlVO> selectCntrwkRoutLenNewStats(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkRoutLenNewStats(cntrwkDtlVO);
	}
	
	@Override
	public List<CntrwkDtlVO> cntrwkRoutLenNewStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkRoutLenNewStatsExcel(cntrwkDtlVO);
	}
	
	@Override
	public List<CntrwkDtlVO> selectCntrwkDeptLenNewStats(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.selectCntrwkDeptLenNewStats(cntrwkDtlVO);
	}
	
	@Override
	public List<CntrwkDtlVO> cntrwkDeptLenNewStatsExcel(CntrwkDtlVO cntrwkDtlVO) throws Exception {
		return cntrwkDtlDAO.cntrwkDeptLenNewStatsExcel(cntrwkDtlVO);
	}
	
	/**
	 * 업체정보(TN_COMPANY)을 조회한다.
	 * @param value - 조회할 정보가 담긴 CompanyVO
	 * @return 조회한 TN_COMPANY
	 * @exception Exception
	 */
	public CompanyVO selectCompanyExcel(CompanyVO companyVO) throws Exception {
		CompanyVO resultVO = companyDAO.selectCompanyExcel(companyVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 공사정보(TN_CNTRWK)을 등록한다.
	 * @param cntrwkVO - 등록할 정보가 담긴 CntrwkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCntrwk(CntrwkVO cntrwkVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cntrwkVO.setId(id);

		return cntrwkDAO.insertCntrwk( cntrwkVO);
	}
	
	/**
	 * 공사정보(TN_CNTRWK_DTL)엑셀을 DB에 저장한다.
	 * 
	 * @param cntrwkDtlVO
	 *            - 저장할 정보가 담긴 CntrwkDtlVO
	 * @exception Exception
	 */
	public String excelDBUpload(String filePathNm, String userNo) throws Exception {
		String resultMsg = "";
		CntrwkVO _cntrwkVO = new CntrwkVO();	
		
		// 파일경로
		FileInputStream fis = new FileInputStream(filePathNm);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Map<String, Object> map = new HashMap<>();
		Map<String, String> cntrwkMap = new HashMap<>();
		
		// 파일의 row의 갯수
		int rowindex = sheet.getPhysicalNumberOfRows();
			
		// row의 0 헤더 제외
		for (int i = 1; i < rowindex; i++) {
			CntrwkVO cntrwkVO = new CntrwkVO();
			BeanUtils.copyProperties(_cntrwkVO, cntrwkVO);
			XSSFRow rows = sheet.getRow(i);

			for (int j = 0; j < rows.getLastCellNum(); j++) {
				// column은 고정
				String column = sheet.getRow(0).getCell(j).getStringCellValue();

				// value는 column 다음 row부터
				XSSFCell cell = sheet.getRow(i).getCell(j);

				map = validationCheck(i, cell, column, cntrwkVO);
				cntrwkVO = (CntrwkVO) map.get("cntrwkVO");
				resultMsg = (String) map.get("resultMsg");
						
				if(!"".equals(resultMsg)){
					throw new Exception(resultMsg);
				}
			}

			cntrwkVO.setCRTR_NO(userNo);
			cntrwkVO.setUSE_AT("Y");
			cntrwkVO.setDELETE_AT("N");

			// insert query
			insertCntrwk(cntrwkVO);
			cntrwkMap.put(cntrwkVO.getFULL_CNTRWK_NM(), cntrwkVO.getCNTRWK_ID());
			
		}
		CntrwkDtlVO cntrwkDtlVO = new CntrwkDtlVO();
		String detail = excelDetailDBUpload(cntrwkDtlVO,cntrwkMap, filePathNm, userNo);
		if(!"Success".equals(detail)) {
			throw new Exception(detail);
		}

		workbook.close();
		resultMsg = "Success";
		return resultMsg;
		
	}
	public String excelDetailDBUpload(CntrwkDtlVO cntrwkDtlVO,Map<String, String> cntrwkMap, String filePathNm, String userNo) throws Exception {
		String resultMsg = "";

		// 파일경로
		FileInputStream fis = new FileInputStream(filePathNm);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(1);

		Map<String, Object> map = new HashMap<>();
		// 파일의 row의 갯수
		int rowindex = sheet.getPhysicalNumberOfRows();
	
		// row의 0 헤더 제외
		for (int i = 1; i < rowindex; i++) {
			CntrwkDtlVO _cntrwkDtlVO = new CntrwkDtlVO();
			BeanUtils.copyProperties(cntrwkDtlVO, _cntrwkDtlVO);

			XSSFRow rows = sheet.getRow(i);

			for (int j = 0; j < rows.getLastCellNum(); j++) {

				// column은 고정
				String column = sheet.getRow(0).getCell(j).getStringCellValue();

				// value는 column 다음 row부터
				XSSFCell cell = sheet.getRow(i).getCell(j);
				map = validationCheckDetail(i,cntrwkMap, cell, column, cntrwkDtlVO);
				cntrwkDtlVO = (CntrwkDtlVO) map.get("cntrwkDtlVO");
				resultMsg = (String) map.get("resultMsg");
				
				if(!"".equals(resultMsg)){
					throw new Exception(resultMsg);
				}
			}

			cntrwkDtlVO.setCRTR_NO(userNo);
			cntrwkDtlVO.setUSE_AT("Y");
			cntrwkDtlVO.setDELETE_AT("N");

			// insert query
			insertCntrwkDtl(cntrwkDtlVO);
			
		}

		workbook.close();
		resultMsg = "Success";
		return resultMsg;
	}
	
	public Map<String, Object> validationCheck(int i, XSSFCell cell, String column, CntrwkVO cntrwkVO)throws Exception {
		String value = "";
		String resultMsg = "";
		int CNTRWK_AMOUNT = 0;
		CompanyVO companyVO = new CompanyVO();
		Map<String, Object> map = new HashMap<>();
		
		try {
			// Validation Check - Cell Type
			if (cell == null) {
				value = "";
			} else {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;

				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf(cell.getNumericCellValue());
					break;

				case Cell.CELL_TYPE_BLANK:
					value = "";
					break;

				case Cell.CELL_TYPE_ERROR:

					resultMsg = "오류가 발생하였습니다." + value;
					break;
				}
			}

			// Validation Check - Cell Value Check

			switch (column) {
			case "사업소코드":
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setDEPT_CODE(value);
					break;

				} else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}

			case "공사년도" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setCNTRWK_YEAR(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "반기코드" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setHT_SE(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "공사구분코드" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setCNTRWK_SE(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "공사분류코드" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setCNTRWK_CL(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "공사명" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setFULL_CNTRWK_NM(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "착공일" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setSTRWRK_DE(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "준공일" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setCOMPET_DE(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "시공사" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					CompanyVO _companyVO = new CompanyVO();
					companyVO.setCO_NM(value);
					_companyVO = selectCompanyExcel(companyVO);
					cntrwkVO.setCNSTRCT_CO_NO(_companyVO.getCO_NO());
					cntrwkVO.setCNSTRCT_CO_RPRSNTV_NM(_companyVO.getRPRSNTV_NM());
					cntrwkVO.setCNSTRCT_CO_TELNO(_companyVO.getRPRSNT_TEL_NO());
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			case "포장두께" :
				if(value != "") {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					cntrwkVO.setRPAIR_THICK_DC(value);
					break;
				}else {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				}
			}
		} catch (Exception e) {
			resultMsg += "기본정보에서 문제발생";
			map.put("resultMsg", resultMsg);
			return map;
		}

		map.put("resultMsg", resultMsg);
		map.put("cntrwkVO", cntrwkVO);
		return map;
	}

	public Map<String, Object> validationCheckDetail(int i,Map<String, String> cntrwkMap, XSSFCell cell, String column, CntrwkDtlVO cntrwkDtlVO)throws Exception {
		String value = "";
		String resultMsg = "";
		int CNTRWK_AMOUNT = 0;
		Map<String, Object> map = new HashMap<>();
		CntrwkVO _cntrwkVO = new CntrwkVO();
		
		try {
			// Validation Check - Cell Type
			if (cell == null) {
				value = "";
			} else {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;

				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf(cell.getNumericCellValue());
					break;

				case Cell.CELL_TYPE_BLANK:
					value = "";
					break;

				case Cell.CELL_TYPE_ERROR:

					resultMsg = "오류가 발생하였습니다." + value;
					break;
				}
			}

			// Validation Check - Cell Value Check

			switch (column) {

			case "공사명":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					if(!cntrwkMap.isEmpty()) {
						cntrwkDtlVO.setCNTRWK_ID(cntrwkMap.get(value));
						break;
					}
					break;
				}
				
			case "세부위치":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setDETAIL_CNTRWK_NM(value);
					break;
				}
						
			case "작업시작일":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setRPAIR_BEGIN_DE(value);
					break;
				}
				
			case "작업완료일":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setRPAIR_END_DE(value);
					break;
				}	

			case "공사비(천원)":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setCNTRWK_AMOUNT(value);
					break;
				}
				
			case "포장공법코드":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setPAV_STLE_CODE(value);
					break;
				}	
				
			case "보수표층두께(cm)":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setRPAIR_THICK_ASCON(value);
					break;
				}
				
			case "보수중간층두께(cm)":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setRPAIR_THICK_CNTR(value);
					break;
				}	
				
			case "보수기층두께(cm)":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setRPAIR_THICK_BASE(value);
					break;
				}	
			
			case "표층포장재료코드":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setPAV_MATRL_ASCON_CODE(value);
					break;
				}	
			
			case "중간층포장재료코드":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setPAV_MATRL_CNTR_CODE(value);
					break;
				}	
				
			case "기층포장재료코드":
				if (value == "") {
					resultMsg = i + " 줄의 " + column + " 칼럼은 필수항목입니다.";
					break;
				} else {
					cntrwkDtlVO.setPAV_MATRL_BASE_CODE(value);
					break;
				}	
			}
			
		} catch (Exception e) {
			resultMsg += "세부공사 등록에서 오류발생.";
			map.put("resultMsg", resultMsg);
			return map;
		}

		map.put("resultMsg", resultMsg);
		map.put("cntrwkDtlVO", cntrwkDtlVO);
		return map;
	}

	@Override
	public CntrwkVO selectCntrwk(CntrwkVO cntrwkVO) throws Exception {
		CntrwkVO resultVO = cntrwkDAO.selectCntrwkExcel(cntrwkVO);
		return resultVO;
	}


}
