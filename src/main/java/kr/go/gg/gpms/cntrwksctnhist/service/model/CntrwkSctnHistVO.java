
package kr.go.gg.gpms.cntrwksctnhist.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 공사_구간_이력
 *
 * @Class Name : CntrwkSctnHistVO.java
 * @Description : CntrwkSctnHist VO class
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
public class CntrwkSctnHistVO extends BaseVO {

	public CntrwkSctnHistVO() {
		super();
	}
	
	/** 
	 * TH_CNTRWK_SCTN_HIST.CNTRWKSCTN_NO, 
	 * 공사_구간_이력.공사구간_번호
	 */
	@XmlElement
	private java.lang.String CNTRWKSCTN_NO;

	/** 
	 * TH_CNTRWK_SCTN_HIST.ROUTE_CODE, 
	 * 공사_구간_이력.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TH_CNTRWK_SCTN_HIST.DIRECT_CODE, 
	 * 공사_구간_이력.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TH_CNTRWK_SCTN_HIST.TRACK, 
	 * 공사_구간_이력.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TH_CNTRWK_SCTN_HIST.STRTPT, 
	 * 공사_구간_이력.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/** 
	 * TH_CNTRWK_SCTN_HIST.ENDPT, 
	 * 공사_구간_이력.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/** 
	 * TH_CNTRWK_SCTN_HIST.RPAIR_DE, 
	 * 공사_구간_이력.보수_일자
	 */
	@XmlElement
	private java.lang.String RPAIR_DE;

	/** 
	 * TH_CNTRWK_SCTN_HIST.CRTR_NO, 
	 * 공사_구간_이력.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TH_CNTRWK_SCTN_HIST.CREAT_DT, 
	 * 공사_구간_이력.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TH_CNTRWK_SCTN_HIST.DELETE_AT, 
	 * 공사_구간_이력.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TH_CNTRWK_SCTN_HIST.USE_AT, 
	 * 공사_구간_이력.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TH_CNTRWK_SCTN_HIST.UPDUSR_NO, 
	 * 공사_구간_이력.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TH_CNTRWK_SCTN_HIST.UPDT_DT, 
	 * 공사_구간_이력.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TH_CNTRWK_SCTN_HIST.CNTRWKSCTN_NO, 
	 * 공사_구간_이력.공사구간_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWKSCTN_NO") 
	public java.lang.String getCNTRWKSCTN_NO() {
		return this.CNTRWKSCTN_NO;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.CNTRWKSCTN_NO, 
	 * 공사_구간_이력.공사구간_번호 값설정
	 * @param cntrwksctnNo
	 */
	public void setCNTRWKSCTN_NO(java.lang.String cntrwksctnNo) {
		this.CNTRWKSCTN_NO = cntrwksctnNo;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.ROUTE_CODE, 
	 * 공사_구간_이력.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.ROUTE_CODE, 
	 * 공사_구간_이력.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.DIRECT_CODE, 
	 * 공사_구간_이력.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.DIRECT_CODE, 
	 * 공사_구간_이력.행선_코드 값설정
	 * @param rowlnCode
	 */
	public void setDIRECT_CODE(java.lang.String rowlnCode) {
		this.DIRECT_CODE = rowlnCode;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.TRACK, 
	 * 공사_구간_이력.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.TRACK, 
	 * 공사_구간_이력.차로 값설정
	 * @param cartrk
	 */
	public void setTRACK(java.lang.String cartrk) {
		this.TRACK = cartrk;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.STRTPT, 
	 * 공사_구간_이력.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.STRTPT, 
	 * 공사_구간_이력.시점 값설정
	 * @param pnttm
	 */
	public void setSTRTPT(java.lang.String pnttm) {
		this.STRTPT = pnttm;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.ENDPT, 
	 * 공사_구간_이력.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.ENDPT, 
	 * 공사_구간_이력.종점 값설정
	 * @param tmnl
	 */
	public void setENDPT(java.lang.String tmnl) {
		this.ENDPT = tmnl;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.RPAIR_DE, 
	 * 공사_구간_이력.보수_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_DE") 
	public java.lang.String getRPAIR_DE() {
		return this.RPAIR_DE;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.RPAIR_DE, 
	 * 공사_구간_이력.보수_일자 값설정
	 * @param mendngDe
	 */
	public void setRPAIR_DE(java.lang.String mendngDe) {
		this.RPAIR_DE = mendngDe;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.CRTR_NO, 
	 * 공사_구간_이력.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.CRTR_NO, 
	 * 공사_구간_이력.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.CREAT_DT, 
	 * 공사_구간_이력.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.CREAT_DT, 
	 * 공사_구간_이력.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.DELETE_AT, 
	 * 공사_구간_이력.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.DELETE_AT, 
	 * 공사_구간_이력.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.USE_AT, 
	 * 공사_구간_이력.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.USE_AT, 
	 * 공사_구간_이력.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.UPDUSR_NO, 
	 * 공사_구간_이력.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.UPDUSR_NO, 
	 * 공사_구간_이력.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TH_CNTRWK_SCTN_HIST.UPDT_DT, 
	 * 공사_구간_이력.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TH_CNTRWK_SCTN_HIST.UPDT_DT, 
	 * 공사_구간_이력.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
