
package kr.go.gg.gpms.srvydtasttus.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 조사_자료_현황
 *
 * @Class Name : SrvyDtaSttusVO.java
 * @Description : SrvyDtaSttus VO class
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
public class SrvyDtaSttusVO extends SrvyDtaSttusDefaultVO {

	public SrvyDtaSttusVO() {
		super();
	}
	
	/** 
	 * TN_SRVY_DTA_STTUS.DTA_STTUS_NO, 
	 * 조사_자료_현황.자료_현황_번호
	 */
	@XmlElement
	private java.lang.String DTA_STTUS_NO;

	/** 
	 * TN_SRVY_DTA_STTUS.SRVY_YEAR, 
	 * 조사_자료_현황.조사_년도
	 */
	@XmlElement
	private java.lang.String SRVY_YEAR;

	/** 
	 * TN_SRVY_DTA_STTUS.ROUTE_CODE, 
	 * 조사_자료_현황.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_SRVY_DTA_STTUS.DIRECT_CODE, 
	 * 조사_자료_현황.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;
	/** 
	 * TN_SRVY_DTA_STTUS.ROWLN_NM, 
	 * 조사_자료_현황.행선_명
	 */
	@XmlElement
	private java.lang.String ROWLN_NM;
	
	/** 
	 * TN_SRVY_DTA_STTUS.TRACK, 
	 * 조사_자료_현황.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_SRVY_DTA_STTUS.STRTPT, 
	 * 조사_자료_현황.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/** 
	 * TN_SRVY_DTA_STTUS.ENDPT, 
	 * 조사_자료_현황.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/** 
	 * TN_SRVY_DTA_STTUS.SRVY_KND, 
	 * 조사_자료_현황.조사_종류
	 */
	@XmlElement
	private java.lang.String SRVY_KND;

	/** 
	 * TN_SRVY_DTA_STTUS.SRVY_MT, 
	 * 조사_자료_현황.조사_월
	 */
	@XmlElement
	private java.lang.String SRVY_MT;

	/** 
	 * TN_SRVY_DTA_STTUS.EXCEL_DTA_SEQ, 
	 * 조사_자료_현황.엑셀_자료_SEQ
	 */
	@XmlElement
	private java.lang.String EXCEL_DTA_SEQ;

	/** 
	 * TN_SRVY_DTA_STTUS.SRVY_NO, 
	 * 조사_자료_현황.조사_번호
	 */
	@XmlElement
	private java.lang.String SRVY_NO;

	/** 
	 * TN_SRVY_DTA_STTUS.SM_PROCESS_AT, 
	 * 조사_자료_현황.집계_구간_산출_여부
	 */
	@XmlElement
	private java.lang.String SM_PROCESS_AT;
	
	/** 
	 * TN_PAV_FRMULA.FRMULA_NM, 
	 * 포장_수식.수식_명
	 */
	@XmlElement
	private java.lang.String FRMULA_NM;

	/** 
	 * TN_SRVY_DTA_STTUS.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_현황.예측_모델_적용_여부
	 */
	@XmlElement
	private java.lang.String PRDTMDL_PROCESS_AT;

	/** 
	 * TN_SRVY_DTA_STTUS.DELETE_AT, 
	 * 조사_자료_현황.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_SRVY_DTA_STTUS.USE_AT, 
	 * 조사_자료_현황.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_SRVY_DTA_STTUS.CRTR_NO, 
	 * 조사_자료_현황.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_SRVY_DTA_STTUS.CREAT_DT, 
	 * 조사_자료_현황.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_SRVY_DTA_STTUS.UPDUSR_NO, 
	 * 조사_자료_현황.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_SRVY_DTA_STTUS.UPDT_DT, 
	 * 조사_자료_현황.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	/** 
	 * TN_SRVY_DTA_STTUS.DTA_NO, 
	 * 조사_자료_현황.자료_번호
	 */
	@XmlElement
	private java.lang.String DTA_NO;

	/**
	 * TN_SRVY_DTA_STTUS.DTA_STTUS_NO, 
	 * 조사_자료_현황.자료_현황_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="DTA_STTUS_NO") 
	public java.lang.String getDTA_STTUS_NO() {
		return this.DTA_STTUS_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.DTA_STTUS_NO, 
	 * 조사_자료_현황.자료_현황_번호 값설정
	 * @param dtaSttusNo
	 */
	public void setDTA_STTUS_NO(java.lang.String dtaSttusNo) {
		this.DTA_STTUS_NO = dtaSttusNo;
	}

	/**
	 * TN_SRVY_DTA_STTUS.SRVY_YEAR, 
	 * 조사_자료_현황.조사_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_YEAR") 
	public java.lang.String getSRVY_YEAR() {
		return this.SRVY_YEAR;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.SRVY_YEAR, 
	 * 조사_자료_현황.조사_년도 값설정
	 * @param srvyYear
	 */
	public void setSRVY_YEAR(java.lang.String srvyYear) {
		this.SRVY_YEAR = srvyYear;
	}

	/**
	 * TN_SRVY_DTA_STTUS.ROUTE_CODE, 
	 * 조사_자료_현황.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.ROUTE_CODE, 
	 * 조사_자료_현황.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_SRVY_DTA_STTUS.DIRECT_CODE, 
	 * 조사_자료_현황.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.DIRECT_CODE, 
	 * 조사_자료_현황.행선_코드 값설정
	 * @param rowlnCode
	 */
	public void setDIRECT_CODE(java.lang.String rowlnCode) {
		this.DIRECT_CODE = rowlnCode;
	}

	/**
	 * TN_SRVY_DTA_STTUS.TRACK, 
	 * 조사_자료_현황.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.TRACK, 
	 * 조사_자료_현황.차로 값설정
	 * @param cartrk
	 */
	public void setTRACK(java.lang.String cartrk) {
		this.TRACK = cartrk;
	}

	/**
	 * TN_SRVY_DTA_STTUS.STRTPT, 
	 * 조사_자료_현황.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.STRTPT, 
	 * 조사_자료_현황.시점 값설정
	 * @param pnttm
	 */
	public void setSTRTPT(java.lang.String pnttm) {
		this.STRTPT = pnttm;
	}

	/**
	 * TN_SRVY_DTA_STTUS.ENDPT, 
	 * 조사_자료_현황.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.ENDPT, 
	 * 조사_자료_현황.종점 값설정
	 * @param tmnl
	 */
	public void setENDPT(java.lang.String tmnl) {
		this.ENDPT = tmnl;
	}

	/**
	 * TN_SRVY_DTA_STTUS.SRVY_KND, 
	 * 조사_자료_현황.조사_종류 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_KND") 
	public java.lang.String getSRVY_KND() {
		return this.SRVY_KND;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.SRVY_KND, 
	 * 조사_자료_현황.조사_종류 값설정
	 * @param srvyKnd
	 */
	public void setSRVY_KND(java.lang.String srvyKnd) {
		this.SRVY_KND = srvyKnd;
	}

	/**
	 * TN_SRVY_DTA_STTUS.SRVY_MT, 
	 * 조사_자료_현황.조사_월 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_MT") 
	public java.lang.String getSRVY_MT() {
		return this.SRVY_MT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.SRVY_MT, 
	 * 조사_자료_현황.조사_월 값설정
	 * @param srvyMt
	 */
	public void setSRVY_MT(java.lang.String srvyMt) {
		this.SRVY_MT = srvyMt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.EXCEL_DTA_SEQ, 
	 * 조사_자료_현황.엑셀_자료_SEQ 값읽기
	 * @return
	 */
	@JsonProperty(value="EXCEL_DTA_SEQ") 
	public java.lang.String getEXCEL_DTA_SEQ() {
		return this.EXCEL_DTA_SEQ;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.EXCEL_DTA_SEQ, 
	 * 조사_자료_현황.엑셀_자료_SEQ 값설정
	 * @param excelDtaSeq
	 */
	public void setEXCEL_DTA_SEQ(java.lang.String excelDtaSeq) {
		this.EXCEL_DTA_SEQ = excelDtaSeq;
	}

	/**
	 * TN_SRVY_DTA_STTUS.SRVY_NO, 
	 * 조사_자료_현황.조사_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NO") 
	public java.lang.String getSRVY_NO() {
		return this.SRVY_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.SRVY_NO, 
	 * 조사_자료_현황.조사_번호 값설정
	 * @param srvyNo
	 */
	public void setSRVY_NO(java.lang.String srvyNo) {
		this.SRVY_NO = srvyNo;
	}

	/**
	 * TN_SRVY_DTA_STTUS.SM_PROCESS_AT, 
	 * 조사_자료_현황.집계_구간_산출_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="SM_PROCESS_AT") 
	public java.lang.String getSM_PROCESS_AT() {
		return this.SM_PROCESS_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.SM_PROCESS_AT, 
	 * 조사_자료_현황.집계_구간_산출_여부 값설정
	 * @param smSctnPrdctnAt
	 */
	public void setSM_PROCESS_AT(java.lang.String smSctnPrdctnAt) {
		this.SM_PROCESS_AT = smSctnPrdctnAt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_현황.예측_모델_적용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="PRDTMDL_PROCESS_AT") 
	public java.lang.String getPRDTMDL_PROCESS_AT() {
		return this.PRDTMDL_PROCESS_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.PRDTMDL_PROCESS_AT, 
	 * 조사_자료_현황.예측_모델_적용_여부 값설정
	 * @param predictModlApplcAt
	 */
	public void setPRDTMDL_PROCESS_AT(java.lang.String predictModlApplcAt) {
		this.PRDTMDL_PROCESS_AT = predictModlApplcAt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.DELETE_AT, 
	 * 조사_자료_현황.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.DELETE_AT, 
	 * 조사_자료_현황.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.USE_AT, 
	 * 조사_자료_현황.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.USE_AT, 
	 * 조사_자료_현황.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.CRTR_NO, 
	 * 조사_자료_현황.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.CRTR_NO, 
	 * 조사_자료_현황.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_SRVY_DTA_STTUS.CREAT_DT, 
	 * 조사_자료_현황.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.CREAT_DT, 
	 * 조사_자료_현황.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_SRVY_DTA_STTUS.UPDUSR_NO, 
	 * 조사_자료_현황.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.UPDUSR_NO, 
	 * 조사_자료_현황.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_SRVY_DTA_STTUS.UPDT_DT, 
	 * 조사_자료_현황.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_SRVY_DTA_STTUS.UPDT_DT, 
	 * 조사_자료_현황.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	
 

	/**
	 * TN_SRVY_DTA_STTUS.DTA_NO, 
	 * 조사_자료_현황.자료_번호 값읽기
	 * @@return
	 */
	public java.lang.String getDTA_NO() {
		return DTA_NO;
	}
	
	/**
	 * TN_SRVY_DTA_STTUS.DTA_NO, 
	 * 조사_자료_현황.자료_번호 값설정
	 * @param dtaNo
	 */
	public void setDTA_NO(java.lang.String dtaNo) {
		this.DTA_NO = dtaNo;
	}

	/**
	 * @return the rOWLN_NM
	 */
	@JsonProperty(value="ROWLN_NM") 
	public java.lang.String getROWLN_NM() {
		return ROWLN_NM;
	}

	/**
	 * @param rOWLN_NM the rOWLN_NM to set
	 */
	public void setROWLN_NM(java.lang.String rOWLN_NM) {
		ROWLN_NM = rOWLN_NM;
	}

	/**
	 * @return the fRMULA_NM
	 */
	@JsonProperty(value="FRMULA_NM") 
	public java.lang.String getFRMULA_NM() {
		return FRMULA_NM;
	}

	/**
	 * @param fRMULA_NM the fRMULA_NM to set
	 */
	public void setFRMULA_NM(java.lang.String fRMULA_NM) {
		FRMULA_NM = fRMULA_NM;
	}
	
	

	
	
}
