
package kr.go.gg.gpms.cntrcomp.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 공사업체정보
 *
 * @Class Name : CntrCompVO.java
 * @Description : CntrComp VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  	CntrCompDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CntrCompVO extends BaseVO {

	public CntrCompVO() {
		super();
	}
	
	/** 
	 * TN_CNTR_COMP.CNTRWK_ACMSLT_NO, 
	 * 공사업체정보.공사_실적_번호
	 */
	@XmlElement
	private java.lang.String CNTRWK_ACMSLT_NO;

	/** 
	 * TN_CNTR_COMP.CNTRWK_ID, 
	 * 공사업체정보.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;

	/** 
	 * TN_CNTR_COMP.CO_NO, 
	 * 공사업체정보.업체_번호
	 */
	@XmlElement
	private java.lang.String CO_NO;

	/** 
	 * TN_CNTR_COMP.EXEC_SE, 
	 * 공사업체정보.이행_구분
	 */
	@XmlElement
	private java.lang.String EXEC_SE;

	/** 
	 * TN_CNTR_COMP.USE_AT, 
	 * 공사업체정보.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_CNTR_COMP.DELETE_AT, 
	 * 공사업체정보.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_CNTR_COMP.CRTR_NO, 
	 * 공사업체정보.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_CNTR_COMP.CREAT_DT, 
	 * 공사업체정보.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_CNTR_COMP.UPDUSR_NO, 
	 * 공사업체정보.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_CNTR_COMP.UPDT_DT, 
	 * 공사업체정보.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TN_CNTR_COMP.CNTRWK_ACMSLT_NO, 
	 * 공사업체정보.공사_실적_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ACMSLT_NO") 
	public java.lang.String getCNTRWK_ACMSLT_NO() {
		return this.CNTRWK_ACMSLT_NO;
	}
 
	 /**
	 * TN_CNTR_COMP.CNTRWK_ACMSLT_NO, 
	 * 공사업체정보.공사_실적_번호 값설정
	 * @param cntrwkAcmsltNo
	 */
	public void setCNTRWK_ACMSLT_NO(java.lang.String cntrwkAcmsltNo) {
		this.CNTRWK_ACMSLT_NO = cntrwkAcmsltNo;
	}

	/**
	 * TN_CNTR_COMP.CNTRWK_ID, 
	 * 공사업체정보.공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTR_COMP.CNTRWK_ID, 
	 * 공사업체정보.공사_ID 값설정
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
	}

	/**
	 * TN_CNTR_COMP.CO_NO, 
	 * 공사업체정보.업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CO_NO") 
	public java.lang.String getCO_NO() {
		return this.CO_NO;
	}
 
	 /**
	 * TN_CNTR_COMP.CO_NO, 
	 * 공사업체정보.업체_번호 값설정
	 * @param coNo
	 */
	public void setCO_NO(java.lang.String coNo) {
		this.CO_NO = coNo;
	}

	/**
	 * TN_CNTR_COMP.EXEC_SE, 
	 * 공사업체정보.이행_구분 값읽기
	 * @return
	 */
	@JsonProperty(value="EXEC_SE") 
	public java.lang.String getEXEC_SE() {
		return this.EXEC_SE;
	}
 
	 /**
	 * TN_CNTR_COMP.EXEC_SE, 
	 * 공사업체정보.이행_구분 값설정
	 * @param execSe
	 */
	public void setEXEC_SE(java.lang.String execSe) {
		this.EXEC_SE = execSe;
	}

	/**
	 * TN_CNTR_COMP.USE_AT, 
	 * 공사업체정보.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_CNTR_COMP.USE_AT, 
	 * 공사업체정보.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_CNTR_COMP.DELETE_AT, 
	 * 공사업체정보.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_CNTR_COMP.DELETE_AT, 
	 * 공사업체정보.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_CNTR_COMP.CRTR_NO, 
	 * 공사업체정보.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_CNTR_COMP.CRTR_NO, 
	 * 공사업체정보.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_CNTR_COMP.CREAT_DT, 
	 * 공사업체정보.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_CNTR_COMP.CREAT_DT, 
	 * 공사업체정보.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_CNTR_COMP.UPDUSR_NO, 
	 * 공사업체정보.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_CNTR_COMP.UPDUSR_NO, 
	 * 공사업체정보.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_CNTR_COMP.UPDT_DT, 
	 * 공사업체정보.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_CNTR_COMP.UPDT_DT, 
	 * 공사업체정보.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
