
package kr.go.gg.gpms.pavidxeryyval.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 포장_지수_초기값
 *
 * @Class Name : PavIdxEryyvalVO.java
 * @Description : PavIdxEryyval VO class
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
public class PavIdxEryyvalVO extends BaseVO {

	public PavIdxEryyvalVO() {
		super();
	}
	
	/** 
	 * TN_PAV_IDX_INITVAL.INITVAL_CR, 
	 * 포장_지수_초기값.초기값_CR
	 */
	@XmlElement
	private java.lang.String INITVAL_CR;

	/** 
	 * TN_PAV_IDX_INITVAL.INITVAL_RD, 
	 * 포장_지수_초기값.초기값_RD
	 */
	@XmlElement
	private java.lang.String INITVAL_RD;

	/** 
	 * TN_PAV_IDX_INITVAL.INITVAL_IRI, 
	 * 포장_지수_초기값.초기값_IRI
	 */
	@XmlElement
	private java.lang.String INITVAL_IRI;

	/** 
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_CR, 
	 * 포장_지수_초기값.기준_결함_CR
	 */
	@XmlElement
	private java.lang.String STDR_DFECT_CR;

	/** 
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_RD, 
	 * 포장_지수_초기값.기준_결함_RD
	 */
	@XmlElement
	private java.lang.String STDR_DFECT_RD;

	/** 
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_IRI, 
	 * 포장_지수_초기값.기준_결함_IRI
	 */
	@XmlElement
	private java.lang.String STDR_DFECT_IRI;

	/** 
	 * TN_PAV_IDX_INITVAL.CRTR_NO, 
	 * 포장_지수_초기값.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_PAV_IDX_INITVAL.CREAT_DT, 
	 * 포장_지수_초기값.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_PAV_IDX_INITVAL.UPDUSR_NO, 
	 * 포장_지수_초기값.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_PAV_IDX_INITVAL.UPDT_DT, 
	 * 포장_지수_초기값.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TN_PAV_IDX_INITVAL.INITVAL_CR, 
	 * 포장_지수_초기값.초기값_CR 값읽기
	 * @return
	 */
	@JsonProperty(value="INITVAL_CR") 
	public java.lang.String getINITVAL_CR() {
		return this.INITVAL_CR;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.INITVAL_CR, 
	 * 포장_지수_초기값.초기값_CR 값설정
	 * @param eryyvalCr
	 */
	public void setINITVAL_CR(java.lang.String eryyvalCr) {
		this.INITVAL_CR = eryyvalCr;
	}

	/**
	 * TN_PAV_IDX_INITVAL.INITVAL_RD, 
	 * 포장_지수_초기값.초기값_RD 값읽기
	 * @return
	 */
	@JsonProperty(value="INITVAL_RD") 
	public java.lang.String getINITVAL_RD() {
		return this.INITVAL_RD;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.INITVAL_RD, 
	 * 포장_지수_초기값.초기값_RD 값설정
	 * @param eryyvalRd
	 */
	public void setINITVAL_RD(java.lang.String eryyvalRd) {
		this.INITVAL_RD = eryyvalRd;
	}

	/**
	 * TN_PAV_IDX_INITVAL.INITVAL_IRI, 
	 * 포장_지수_초기값.초기값_IRI 값읽기
	 * @return
	 */
	@JsonProperty(value="INITVAL_IRI") 
	public java.lang.String getINITVAL_IRI() {
		return this.INITVAL_IRI;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.INITVAL_IRI, 
	 * 포장_지수_초기값.초기값_IRI 값설정
	 * @param eryyvalIri
	 */
	public void setINITVAL_IRI(java.lang.String eryyvalIri) {
		this.INITVAL_IRI = eryyvalIri;
	}

	/**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_CR, 
	 * 포장_지수_초기값.기준_결함_CR 값읽기
	 * @return
	 */
	@JsonProperty(value="STDR_DFECT_CR") 
	public java.lang.String getSTDR_DFECT_CR() {
		return this.STDR_DFECT_CR;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_CR, 
	 * 포장_지수_초기값.기준_결함_CR 값설정
	 * @param stdrDfectCr
	 */
	public void setSTDR_DFECT_CR(java.lang.String stdrDfectCr) {
		this.STDR_DFECT_CR = stdrDfectCr;
	}

	/**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_RD, 
	 * 포장_지수_초기값.기준_결함_RD 값읽기
	 * @return
	 */
	@JsonProperty(value="STDR_DFECT_RD") 
	public java.lang.String getSTDR_DFECT_RD() {
		return this.STDR_DFECT_RD;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_RD, 
	 * 포장_지수_초기값.기준_결함_RD 값설정
	 * @param stdrDfectRd
	 */
	public void setSTDR_DFECT_RD(java.lang.String stdrDfectRd) {
		this.STDR_DFECT_RD = stdrDfectRd;
	}

	/**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_IRI, 
	 * 포장_지수_초기값.기준_결함_IRI 값읽기
	 * @return
	 */
	@JsonProperty(value="STDR_DFECT_IRI") 
	public java.lang.String getSTDR_DFECT_IRI() {
		return this.STDR_DFECT_IRI;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.STDR_DFECT_IRI, 
	 * 포장_지수_초기값.기준_결함_IRI 값설정
	 * @param stdrDfectIri
	 */
	public void setSTDR_DFECT_IRI(java.lang.String stdrDfectIri) {
		this.STDR_DFECT_IRI = stdrDfectIri;
	}

	/**
	 * TN_PAV_IDX_INITVAL.CRTR_NO, 
	 * 포장_지수_초기값.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.CRTR_NO, 
	 * 포장_지수_초기값.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_PAV_IDX_INITVAL.CREAT_DT, 
	 * 포장_지수_초기값.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.CREAT_DT, 
	 * 포장_지수_초기값.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_PAV_IDX_INITVAL.UPDUSR_NO, 
	 * 포장_지수_초기값.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.UPDUSR_NO, 
	 * 포장_지수_초기값.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_PAV_IDX_INITVAL.UPDT_DT, 
	 * 포장_지수_초기값.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_PAV_IDX_INITVAL.UPDT_DT, 
	 * 포장_지수_초기값.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
