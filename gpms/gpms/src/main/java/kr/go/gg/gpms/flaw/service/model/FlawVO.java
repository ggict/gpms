
package kr.go.gg.gpms.flaw.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
import kr.go.gg.gpms.cntrwk.service.model.CntrwkVO;
/**
 * 공사하자정보
 *
 * @Class Name : FlawVO.java
 * @Description : Flaw VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  	FlawDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class FlawVO extends CntrwkVO {

	public FlawVO() {
		super();
	}
	
	/** 
	 * TN_FLAW.FLAW_ID, 
	 * 공사하자정보.하자_ID
	 */
	@XmlElement
	private java.lang.String FLAW_ID;

	/** 
	 * TN_FLAW.CNTRWK_ID, 
	 * 공사하자정보.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;

	/** 
	 * TN_FLAW.ORDER_DEPT_CODE, 
	 * 공사하자정보.발주_부서_코드
	 */
	@XmlElement
	private java.lang.String ORDER_DEPT_CODE;

	/** 
	 * TN_FLAW.FLAW_CHCK_DE, 
	 * 공사하자정보.하자_검사_일자
	 */
	@XmlElement
	private java.lang.String FLAW_CHCK_DE;

	/** 
	 * TN_FLAW.FLAW_GTN, 
	 * 공사하자정보.하자_보증금
	 */
	@XmlElement
	private java.lang.String FLAW_GTN;

	/** 
	 * TN_FLAW.FLAW_GTN_KND, 
	 * 공사하자정보.하자_보증금_종류
	 */
	@XmlElement
	private java.lang.String FLAW_GTN_KND;

	/** 
	 * TN_FLAW.WARNT_BEGIN_DE, 
	 * 공사하자정보.하자_담보_시작_일자
	 */
	@XmlElement
	private java.lang.String WARNT_BEGIN_DE;

	/** 
	 * TN_FLAW.WARNT_END_DE, 
	 * 공사하자정보.하자_담보_종료_일자
	 */
	@XmlElement
	private java.lang.String WARNT_END_DE;

	/** 
	 * TN_FLAW.USE_AT, 
	 * 공사하자정보.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_FLAW.DELETE_AT, 
	 * 공사하자정보.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_FLAW.DELETE_DT, 
	 * 공사하자정보.삭제_일시
	 */
	@XmlElement
	private java.sql.Date DELETE_DT;

	/** 
	 * TN_FLAW.CRTR_NO, 
	 * 공사하자정보.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_FLAW.CREAT_DT, 
	 * 공사하자정보.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_FLAW.UPDUSR_NO, 
	 * 공사하자정보.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_FLAW.UPDT_DT, 
	 * 공사하자정보.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/** 하자_보증금_종류_명 */
	@XmlElement
	private java.lang.String FLAW_GTN_KND_NM;
	
	/** 
	 * 하자_시작_일자
	 */
	@XmlElement
	private java.lang.String FLAW_BEGIN_DE;
	
	/** 
	 * 하자_종료_일자
	 */
	@XmlElement
	private java.lang.String FLAW_END_DE;
	
	/**
	 * 하자공사ID
	 */
	@XmlElement
	private java.lang.String FLAW_CNTRWK_ID;
	
	/**
	 * TN_FLAW.FLAW_ID, 
	 * 공사하자정보.하자_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_ID") 
	public java.lang.String getFLAW_ID() {
		return this.FLAW_ID;
	}
 
	 /**
	 * TN_FLAW.FLAW_ID, 
	 * 공사하자정보.하자_ID 값설정
	 * @param flawId
	 */
	public void setFLAW_ID(java.lang.String flawId) {
		this.FLAW_ID = flawId;
	}

	/**
	 * TN_FLAW.CNTRWK_ID, 
	 * 공사하자정보.공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_FLAW.CNTRWK_ID, 
	 * 공사하자정보.공사_ID 값설정
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
	}

	/**
	 * TN_FLAW.ORDER_DEPT_CODE, 
	 * 공사하자정보.발주_부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ORDER_DEPT_CODE") 
	public java.lang.String getORDER_DEPT_CODE() {
		return this.ORDER_DEPT_CODE;
	}
 
	 /**
	 * TN_FLAW.ORDER_DEPT_CODE, 
	 * 공사하자정보.발주_부서_코드 값설정
	 * @param orderDeptCode
	 */
	public void setORDER_DEPT_CODE(java.lang.String orderDeptCode) {
		this.ORDER_DEPT_CODE = orderDeptCode;
	}

	/**
	 * TN_FLAW.FLAW_CHCK_DE, 
	 * 공사하자정보.하자_검사_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CHCK_DE") 
	public java.lang.String getFLAW_CHCK_DE() {
		return this.FLAW_CHCK_DE;
	}
 
	 /**
	 * TN_FLAW.FLAW_CHCK_DE, 
	 * 공사하자정보.하자_검사_일자 값설정
	 * @param flawChckDe
	 */
	public void setFLAW_CHCK_DE(java.lang.String flawChckDe) {
		this.FLAW_CHCK_DE = flawChckDe;
	}

	/**
	 * TN_FLAW.FLAW_GTN, 
	 * 공사하자정보.하자_보증금 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_GTN") 
	public java.lang.String getFLAW_GTN() {
		return this.FLAW_GTN;
	}
 
	 /**
	 * TN_FLAW.FLAW_GTN, 
	 * 공사하자정보.하자_보증금 값설정
	 * @param flawGtn
	 */
	public void setFLAW_GTN(java.lang.String flawGtn) {
		this.FLAW_GTN = flawGtn;
	}

	/**
	 * TN_FLAW.FLAW_GTN_KND, 
	 * 공사하자정보.하자_보증금_종류 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_GTN_KND") 
	public java.lang.String getFLAW_GTN_KND() {
		return this.FLAW_GTN_KND;
	}
 
	 /**
	 * TN_FLAW.FLAW_GTN_KND, 
	 * 공사하자정보.하자_보증금_종류 값설정
	 * @param flawGtnKnd
	 */
	public void setFLAW_GTN_KND(java.lang.String flawGtnKnd) {
		this.FLAW_GTN_KND = flawGtnKnd;
	}

	/**
	 * TN_FLAW.WARNT_BEGIN_DE, 
	 * 공사하자정보.하자_담보_시작_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="WARNT_BEGIN_DE") 
	public java.lang.String getWARNT_BEGIN_DE() {
		return this.WARNT_BEGIN_DE;
	}
	
	 /**
	 * TN_FLAW.WARNT_BEGIN_DE, 
	 * 공사하자정보.하자_담보_시작_일자 값설정
	 * @param warntBeginDe
	 */
	public void setWARNT_BEGIN_DE(java.lang.String warntBeginDe) {
		this.WARNT_BEGIN_DE = warntBeginDe;
	}

	/**
	 * TN_FLAW.WARNT_END_DE, 
	 * 공사하자정보.하자_담보_종료_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="WARNT_END_DE") 
	public java.lang.String getWARNT_END_DE() {
		return this.WARNT_END_DE;
	}
	
	 /**
	 * TN_FLAW.WARNT_END_DE, 
	 * 공사하자정보.하자_담보_종료_일자 값설정
	 * @param warntEndDe
	 */
	public void setWARNT_END_DE(java.lang.String warntEndDe) {
		this.WARNT_END_DE = warntEndDe;
	}
	
	/**
	 * TN_FLAW.USE_AT, 
	 * 공사하자정보.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_FLAW.USE_AT, 
	 * 공사하자정보.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_FLAW.DELETE_AT, 
	 * 공사하자정보.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_FLAW.DELETE_AT, 
	 * 공사하자정보.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_FLAW.DELETE_DT, 
	 * 공사하자정보.삭제_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getDELETE_DT() {
		return this.DELETE_DT;
	}
 
	 /**
	 * TN_FLAW.DELETE_DT, 
	 * 공사하자정보.삭제_일시 값설정
	 * @param deleteDt
	 */
	public void setDELETE_DT(java.sql.Date deleteDt) {
		this.DELETE_DT = deleteDt;
	}

	/**
	 * TN_FLAW.CRTR_NO, 
	 * 공사하자정보.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_FLAW.CRTR_NO, 
	 * 공사하자정보.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_FLAW.CREAT_DT, 
	 * 공사하자정보.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_FLAW.CREAT_DT, 
	 * 공사하자정보.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_FLAW.UPDUSR_NO, 
	 * 공사하자정보.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_FLAW.UPDUSR_NO, 
	 * 공사하자정보.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_FLAW.UPDT_DT, 
	 * 공사하자정보.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_FLAW.UPDT_DT, 
	 * 공사하자정보.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="FLAW_GTN_KND_NM")
	public java.lang.String getFLAW_GTN_KND_NM() {
		return FLAW_GTN_KND_NM;
	}

	public void setFLAW_GTN_KND_NM(java.lang.String flawGtnKndNm) {
		this.FLAW_GTN_KND_NM = flawGtnKndNm;
	}

	@JsonProperty(value="FLAW_BEGIN_DE")
	public java.lang.String getFLAW_BEGIN_DE() {
		return this.FLAW_BEGIN_DE;
	}

	public void setFLAW_BEGIN_DE(java.lang.String fLAW_BEGIN_DE) {
		this.FLAW_BEGIN_DE = fLAW_BEGIN_DE;
	}

	@JsonProperty(value="FLAW_END_DE")
	public java.lang.String getFLAW_END_DE() {
		return this.FLAW_END_DE;
	}

	public void setFLAW_END_DE(java.lang.String fLAW_END_DE) {
		this.FLAW_END_DE = fLAW_END_DE;
	}

	@JsonProperty(value="FLAW_CNTRWK_ID")
	public java.lang.String getFLAW_CNTRWK_ID() {
		return this.FLAW_CNTRWK_ID;
	}

	public void setFLAW_CNTRWK_ID(java.lang.String fLAW_CNTRWK_ID) {
		this.FLAW_CNTRWK_ID = fLAW_CNTRWK_ID;
	}
	
}
