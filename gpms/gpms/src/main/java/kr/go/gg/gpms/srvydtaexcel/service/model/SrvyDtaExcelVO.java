
package kr.go.gg.gpms.srvydtaexcel.service.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelVO.java
 * @Description : SrvyDtaExcel VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyDtaExcelVO extends SrvyDtaExcelDefaultVO {

	public SrvyDtaExcelVO() {
		super();
	}
	
	@XmlElement
	private java.lang.String CELL_ID;
	
	/** 
	 * TN_SRVY_DTA_EXCEL.SRVY_NO, 
	 * 조사_자료_엑셀.조사_번호
	 */
	@XmlElement
	private java.lang.String SRVY_NO;
	
	@XmlElement
	private java.lang.String SRVY_NOS;
	@XmlElement
	private List<String> SRVY_NO_LIST;

	

	/** 
	 * TN_SRVY_DTA_EXCEL.FILE_NO, 
	 * 조사_자료_엑셀.파일_번호
	 */
	@XmlElement
	private java.lang.String FILE_NO;
	/**
	 * 엑셀데이터XML
	 */
	@XmlElement
	private String RECORDSET; 

	/** 
	 * TN_SRVY_DTA_EXCEL.DATA_CO, 
	 * 조사_자료_엑셀.데이터_건수
	 */
	@XmlElement
	private java.lang.String DATA_CO;
	/** 
	 * TN_SRVY_DTA_EXCEL.ROUTE_CODE, 
	 * 조사_자료_엑셀.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_SRVY_DTA_EXCEL.DIRECT_CODE, 
	 * 조사_자료_엑셀.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TN_SRVY_DTA_EXCEL.TRACK, 
	 * 조사_자료_엑셀.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_SRVY_DTA_EXCEL.STRTPT, 
	 * 조사_자료_엑셀.시점
	 */
	@XmlElement
	private java.math.BigDecimal STRTPT;

	/** 
	 * TN_SRVY_DTA_EXCEL.ENDPT, 
	 * 조사_자료_엑셀.종점
	 */
	@XmlElement
	private java.math.BigDecimal ENDPT;

	/** 
	 * TN_SRVY_DTA_EXCEL.SRVY_DE, 
	 * 조사_자료_엑셀.조사_일자
	 */
	@XmlElement
	private java.lang.String SRVY_DE;

	/** 
	 * TN_SRVY_DTA_EXCEL.SRVY_NM, 
	 * 조사_자료_엑셀.조사_명
	 */
	@XmlElement
	private java.lang.String SRVY_NM;

	/** 
	 * TN_SRVY_DTA_EXCEL.EXMNR_NM, 
	 * 조사_자료_엑셀.조사자_명
	 */
	@XmlElement
	private java.lang.String EXMNR_NM;

	
	/** 
	 * TN_SRVY_DTA_EXCEL.EVL_PROCESS_AT, 
	 * 조사_자료_엑셀.평가_처리_여부
	 */
	@XmlElement
	private java.lang.String EVL_PROCESS_AT;
	
	/** 
	 * TN_SRVY_DTA_EXCEL.GPS_CORTN_PROCESS_AT, 
	 * 조사_자료_엑셀.GPS_보정_처리_여부
	 */
	@XmlElement
	private java.lang.String GPS_CORTN_PROCESS_AT;
	
	/** 
	 * TN_SRVY_DTA_EXCEL.SM_PROCESS_AT, 
	 * 조사_자료_엑셀.집계_처리_여부
	 */
	@XmlElement
	private java.lang.String SM_PROCESS_AT;

	/** 
	 * TN_SRVY_DTA_EXCEL.VAL_EVL_AT, 
	 * 조사_자료_엑셀.유효성_평가_여부
	 */
	@XmlElement
	private java.lang.String VAL_EVL_AT;

	/** 
	 * TN_SRVY_DTA_EXCEL.DELETE_AT, 
	 * 조사_자료_엑셀.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_SRVY_DTA_EXCEL.USE_AT, 
	 * 조사_자료_엑셀.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_SRVY_DTA_EXCEL.CRTR_NO, 
	 * 조사_자료_엑셀.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_SRVY_DTA_EXCEL.CREAT_DT, 
	 * 조사_자료_엑셀.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_SRVY_DTA_EXCEL.UPDUSR_NO, 
	 * 조사_자료_엑셀.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_SRVY_DTA_EXCEL.UPDT_DT, 
	 * 조사_자료_엑셀.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	/** 
	 * ROAD_NO, 
	 * 검색_노선번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO;
	
	/** 
	 * ROAD_NAME, 
	 * 검색_노선명
	 */
	@XmlElement
	private java.lang.String ROAD_NAME;
	
	
	/** 
	 * TN_SRVY_DTA_EXCEL.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_엑셀.예측모델_처리_여부
	 */
	@XmlElement
	private java.lang.String PRDTMDL_PROCESS_AT;
	
	@XmlElement
	private java.lang.String PROCESS_STTUS;
	
	@XmlElement
	private java.lang.String CRTR_NM;
	
	@XmlElement
	private java.lang.String SCH_SRVY_DE1;
	
	@XmlElement
	private java.lang.String SCH_SRVY_DE2;
	
	@XmlElement
	private java.lang.String EVL_PROCESS;
	
	@XmlElement
	private java.lang.String GPS_CORTN_PROCESS;
	
	@XmlElement
	private java.lang.String SM_PROCESS;
	
	@XmlElement
	private java.lang.String EVL_YEAR;
	
	@XmlElement
	private java.lang.String FILE_NM;

	@XmlElement
	private java.lang.String CALC_DE;
	
	@XmlElement
	private java.lang.String PREDCT_DE;
	
	@XmlElement
	private java.lang.String PRDTMDL_PROCESS;
	
	
	


	/**
	 * TN_SRVY_DTA_EXCEL.SRVY_NO, 
	 * 조사_자료_엑셀.조사_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NO") 
	public java.lang.String getSRVY_NO() {
		return this.SRVY_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.SRVY_NO, 
	 * 조사_자료_엑셀.조사_번호 값설정
	 * @param srvyNo
	 */
	public void setSRVY_NO(java.lang.String srvyNo) {
		this.SRVY_NO = srvyNo;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.FILE_NO, 
	 * 조사_자료_엑셀.파일_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NO") 
	public java.lang.String getFILE_NO() {
		return this.FILE_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.FILE_NO, 
	 * 조사_자료_엑셀.파일_번호 값설정
	 * @param fileNo
	 */
	public void setFILE_NO(java.lang.String fileNo) {
		this.FILE_NO = fileNo;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.DATA_CO, 
	 * 조사_자료_엑셀.데이터_건수 값읽기
	 * @return
	 */
	@JsonProperty(value="DATA_CO") 
	public java.lang.String getDATA_CO() {
		return this.DATA_CO;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.DATA_CO, 
	 * 조사_자료_엑셀.데이터_건수 값설정
	 * @param dataCo
	 */
	public void setDATA_CO(java.lang.String dataCo) {
		this.DATA_CO = dataCo;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.ROUTE_CODE, 
	 * 조사_자료_엑셀.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.ROUTE_CODE, 
	 * 조사_자료_엑셀.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.DIRECT_CODE, 
	 * 조사_자료_엑셀.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.DIRECT_CODE, 
	 * 조사_자료_엑셀.행선_코드 값설정
	 * @param rowlnCode
	 */
	public void setDIRECT_CODE(java.lang.String rowlnCode) {
		this.DIRECT_CODE = rowlnCode;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.TRACK, 
	 * 조사_자료_엑셀.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.TRACK, 
	 * 조사_자료_엑셀.차로 값설정
	 * @param cartrk
	 */
	public void setTRACK(java.lang.String cartrk) {
		this.TRACK = cartrk;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.STRTPT, 
	 * 조사_자료_엑셀.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.math.BigDecimal getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.STRTPT, 
	 * 조사_자료_엑셀.시점 값설정
	 * @param pnttm
	 */
	public void setSTRTPT(java.math.BigDecimal pnttm) {
		this.STRTPT = pnttm;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.ENDPT, 
	 * 조사_자료_엑셀.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.math.BigDecimal getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.ENDPT, 
	 * 조사_자료_엑셀.종점 값설정
	 * @param tmnl
	 */
	public void setENDPT(java.math.BigDecimal tmnl) {
		this.ENDPT = tmnl;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.SRVY_DE, 
	 * 조사_자료_엑셀.조사_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_DE") 
	public java.lang.String getSRVY_DE() {
		return this.SRVY_DE;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.SRVY_DE, 
	 * 조사_자료_엑셀.조사_일자 값설정
	 * @param srvyDe
	 */
	public void setSRVY_DE(java.lang.String srvyDe) {
		this.SRVY_DE = srvyDe;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.SRVY_NM, 
	 * 조사_자료_엑셀.조사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NM") 
	public java.lang.String getSRVY_NM() {
		return this.SRVY_NM;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.SRVY_NM, 
	 * 조사_자료_엑셀.조사_명 값설정
	 * @param srvyNm
	 */
	public void setSRVY_NM(java.lang.String srvyNm) {
		this.SRVY_NM = srvyNm;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.EXMNR_NM, 
	 * 조사_자료_엑셀.조사자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="EXMNR_NM") 
	public java.lang.String getEXMNR_NM() {
		return this.EXMNR_NM;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.EXMNR_NM, 
	 * 조사_자료_엑셀.조사자_명 값설정
	 * @param exmnrNm
	 */
	public void setEXMNR_NM(java.lang.String exmnrNm) {
		this.EXMNR_NM = exmnrNm;
	}
	/**
	 * TN_SRVY_DTA_EXCEL.EVL_PROCESS_AT, 
	 * 조사_자료_엑셀.평가_처리_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="EVL_PROCESS_AT") 
	public java.lang.String getEVL_PROCESS_AT() {
		return this.EVL_PROCESS_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.EVL_PROCESS_AT, 
	 * 조사_자료_엑셀.평가_처리_여부 값설정
	 * @param evlProcessAt
	 */
	public void setEVL_PROCESS_AT(java.lang.String evlProcessAt) {
		this.EVL_PROCESS_AT = evlProcessAt;
	}
	
	/**
	 * TN_SRVY_DTA_EXCEL.GPS_CORTN_PROCESS_AT, 
	 * 조사_자료_엑셀.GPS_보정_처리_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="GPS_CORTN_PROCESS_AT") 
	public java.lang.String getGPS_CORTN_PROCESS_AT() {
		return this.GPS_CORTN_PROCESS_AT;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.GPS_CORTN_PROCESS_AT, 
	 * 조사_자료_엑셀.GPS_보정_처리_여부 값설정
	 * @param gpsCortnProcessAt
	 */
	public void setGPS_CORTN_PROCESS_AT(java.lang.String gpsCortnProcessAt) {
		this.GPS_CORTN_PROCESS_AT = gpsCortnProcessAt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.SM_PROCESS_AT, 
	 * 조사_자료_엑셀.집계_처리_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="SM_PROCESS_AT") 
	public java.lang.String getSM_PROCESS_AT() {
		return this.SM_PROCESS_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.SM_PROCESS_AT, 
	 * 조사_자료_엑셀.집계_처리_여부 값설정
	 * @param smProcessAt
	 */
	public void setSM_PROCESS_AT(java.lang.String smProcessAt) {
		this.SM_PROCESS_AT = smProcessAt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.VAL_EVL_AT, 
	 * 조사_자료_엑셀.유효성_평가_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="VAL_EVL_AT") 
	public java.lang.String getVAL_EVL_AT() {
		return this.VAL_EVL_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.VAL_EVL_AT, 
	 * 조사_자료_엑셀.유효성_평가_여부 값설정
	 * @param valEvlAt
	 */
	public void setVAL_EVL_AT(java.lang.String valEvlAt) {
		this.VAL_EVL_AT = valEvlAt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.DELETE_AT, 
	 * 조사_자료_엑셀.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.DELETE_AT, 
	 * 조사_자료_엑셀.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.USE_AT, 
	 * 조사_자료_엑셀.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.USE_AT, 
	 * 조사_자료_엑셀.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.CRTR_NO, 
	 * 조사_자료_엑셀.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.CRTR_NO, 
	 * 조사_자료_엑셀.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.CREAT_DT, 
	 * 조사_자료_엑셀.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.CREAT_DT, 
	 * 조사_자료_엑셀.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.UPDUSR_NO, 
	 * 조사_자료_엑셀.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.UPDUSR_NO, 
	 * 조사_자료_엑셀.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_SRVY_DTA_EXCEL.UPDT_DT, 
	 * 조사_자료_엑셀.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.UPDT_DT, 
	 * 조사_자료_엑셀.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	
	

	public String getRECORDSET() {
		return RECORDSET;
	}

	public void setRECORDSET(String rECORDSET) {
		RECORDSET = rECORDSET;
	}
	@JsonProperty(value="SRVY_NO_LIST") 
	public List<String> getSRVY_NO_LIST() {
		return SRVY_NO_LIST;
	}

	public void setSRVY_NO_LIST(List<String> sRVY_NO_List) {
		SRVY_NO_LIST = sRVY_NO_List;
	}
	@JsonProperty(value="SRVY_NOS") 
	public java.lang.String getSRVY_NOS() {
		return SRVY_NOS;
	}

	public void setSRVY_NOS(java.lang.String sRVY_NOS) {
		SRVY_NOS = sRVY_NOS;
		List<String> srvyNoList = new ArrayList<String>();
		if(SRVY_NOS!=null && SRVY_NOS.trim().length()>0){
			
			String[] srbyArray = SRVY_NOS.split(",");
			if(srbyArray!=null && srbyArray.length>0){
				for(String srvyNo : srbyArray){
					if(srvyNo.trim().length()>0){
						srvyNoList.add(srvyNo);	
					}		
				}
			}
		}
		SRVY_NO_LIST = srvyNoList;
	}

	@JsonProperty(value="PROCESS_STTUS") 
	public java.lang.String getPROCESS_STTUS() {
		return PROCESS_STTUS;
	}

	public void setPROCESS_STTUS(java.lang.String pROCESS_STTUS) {
		PROCESS_STTUS = pROCESS_STTUS;
	}

	@JsonProperty(value="CRTR_NM") 
	public java.lang.String getCRTR_NM() {
		return CRTR_NM;
	}

	public void setCRTR_NM(java.lang.String cRTR_NM) {
		CRTR_NM = cRTR_NM;
	}

	@JsonProperty(value="SCH_SRVY_DE1") 
	public java.lang.String getSCH_SRVY_DE1() {
		return this.SCH_SRVY_DE1;
	}

	public void setSCH_SRVY_DE1(java.lang.String sCH_SRVY_DE1) {
		this.SCH_SRVY_DE1 = sCH_SRVY_DE1;
	}

	@JsonProperty(value="SCH_SRVY_DE2") 
	public java.lang.String getSCH_SRVY_DE2() {
		return this.SCH_SRVY_DE2;
	}

	public void setSCH_SRVY_DE2(java.lang.String sCH_SRVY_DE2) {
		this.SCH_SRVY_DE2 = sCH_SRVY_DE2;
	}

	@JsonProperty(value="EVL_PROCESS") 
	public java.lang.String getEVL_PROCESS() {
		return this.EVL_PROCESS;
	}

	public void setEVL_PROCESS(java.lang.String eVL_PROCESS) {
		this.EVL_PROCESS = eVL_PROCESS;
	}

	@JsonProperty(value="GPS_CORTN_PROCESS") 
	public java.lang.String getGPS_CORTN_PROCESS() {
		return this.GPS_CORTN_PROCESS;
	}

	public void setGPS_CORTN_PROCESS(java.lang.String gPS_CORTN_PROCESS) {
		this.GPS_CORTN_PROCESS = gPS_CORTN_PROCESS;
	}

	@JsonProperty(value="SM_PROCESS") 
	public java.lang.String getSM_PROCESS() {
		return this.SM_PROCESS;
	}

	public void setSM_PROCESS(java.lang.String sM_PROCESS) {
		this.SM_PROCESS = sM_PROCESS;
	}

	@JsonProperty(value="EVL_YEAR") 
	public java.lang.String getEVL_YEAR() {
		return this.EVL_YEAR;
	}

	public void setEVL_YEAR(java.lang.String eVL_YEAR) {
		this.EVL_YEAR = eVL_YEAR;
	}

	@JsonProperty(value="FILE_NM") 
	public java.lang.String getFILE_NM() {
		return this.FILE_NM;
	}

	public void setFILE_NM(java.lang.String fILE_NM) {
		this.FILE_NM = fILE_NM;
	}
	
	/**
	 * TN_SRVY_DTA_EXCEL.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_엑셀.예측모델_처리_여부 
	 * @return
	 */
	@JsonProperty(value="PRDTMDL_PROCESS_AT") 
	public java.lang.String getPRDTMDL_PROCESS_AT() {
		return this.PRDTMDL_PROCESS_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_EXCEL.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_엑셀.예측모델_처리_여부 
	 * @param prdtmdlProcessAt
	 */
	public void setPRDTMDL_PROCESS_AT(java.lang.String prdtmdlProcessAt) {
		this.PRDTMDL_PROCESS_AT = prdtmdlProcessAt;
	}

	@JsonProperty(value="CALC_DE") 
	public java.lang.String getCALC_DE() {
		return CALC_DE;
	}

	public void setCALC_DE(java.lang.String cALC_DE) {
		CALC_DE = cALC_DE;
	}

	@JsonProperty(value="PREDCT_DE") 
	public java.lang.String getPREDCT_DE() {
		return PREDCT_DE;
	}

	public void setPREDCT_DE(java.lang.String pREDCT_DE) {
		PREDCT_DE = pREDCT_DE;
	}

	@JsonProperty(value="PRDTMDL_PROCESS") 
	public java.lang.String getPRDTMDL_PROCESS() {
		return PRDTMDL_PROCESS;
	}

	public void setPRDTMDL_PROCESS(java.lang.String pRDTMDL_PROCESS) {
		PRDTMDL_PROCESS = pRDTMDL_PROCESS;
	}

	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return this.ROAD_NO;
	}

	public void setROAD_NO(java.lang.String rOAD_NO) {
		this.ROAD_NO = rOAD_NO;
	}

	@JsonProperty(value="ROAD_NAME") 
	public java.lang.String getROAD_NAME() {
		return this.ROAD_NAME;
	}

	public void setROAD_NAME(java.lang.String rOAD_NAME) {
		this.ROAD_NAME = rOAD_NAME;
	}

	@JsonProperty(value="CELL_ID") 
	public java.lang.String getCELL_ID() {
		return CELL_ID;
	}

	public void setCELL_ID(java.lang.String cELL_ID) {
		CELL_ID = cELL_ID;
	}
	
}
