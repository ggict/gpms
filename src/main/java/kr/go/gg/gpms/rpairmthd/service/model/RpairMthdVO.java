
package kr.go.gg.gpms.rpairmthd.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 보수공법코드
 *
 * @Class Name : RpairMthdVO.java
 * @Description : RpairMthd VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-09
 * @version 1.0
 * @see
 *  	RpairMthdDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairMthdVO extends BaseVO {

	public RpairMthdVO() {
		super();
	}
	
	/** 
	 * TC_RPAIR_MTHD.RPAIR_MTHD_CODE, 
	 * 보수공법코드.보수_공법_코드
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_CODE;

	/** 
	 * TC_RPAIR_MTHD.MSRC_CL_CODE, 
	 * 보수공법코드.공법_분류_코드
	 */
	@XmlElement
	private java.lang.String MSRC_CL_CODE;

	/** 
	 * TC_RPAIR_MTHD.MSRC_NM, 
	 * 보수공법코드.공법_명
	 */
	@XmlElement
	private java.lang.String MSRC_NM;

	/** 
	 * TC_RPAIR_MTHD.DELETE_AT, 
	 * 보수공법코드.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TC_RPAIR_MTHD.USE_AT, 
	 * 보수공법코드.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;
	
	/** 
	 * TC_RPAIR_MTHD.MSRC_CT, 
	 * 보수공법코드.공법_대분류_코드
	 */
	@XmlElement
	private java.lang.String MSRC_CT;
	
	 
	/** 
	 * TC_RPAIR_MTHD.MSRC_LCLAS_NM, 
	 * 보수공법코드.공법_대분류_코드
	 */
	@XmlElement
	private java.lang.String MSRC_LCLAS_NM;
	
	/** 
	 * TC_RPAIR_MTHD.MSRC_LCLAS_CODE, 
	 * 보수공법코드.공법_비용
	 */
	@XmlElement
	private java.lang.String MSRC_LCLAS_CODE;
	
	
	/** 
	 * TC_RPAIR_MTHD.MSR_DM_CODE, 
	 * 보수공법코드.공법선정비율_결정방식_코드
	 */
	@XmlElement
	private java.lang.String MSR_DM_CODE;
	
	
	/** 
	 * TC_RPAIR_MTHD.MSR_DM_NM, 
	 * 보수공법코드.공법선정비율_결정방식_코드
	 */
	@XmlElement
	private java.lang.String MSR_DM_NM;
		
		
	/** 
	 * TC_RPAIR_MTHD.CRTR_NO, 
	 * 보수공법코드.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TC_RPAIR_MTHD.CREAT_DT, 
	 * 보수공법코드.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TC_RPAIR_MTHD.UPDUSR_NO, 
	 * 보수공법코드.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TC_RPAIR_MTHD.UPDT_DT, 
	 * 보수공법코드.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;
	
	@XmlElement
	private java.lang.String MSRC_CL_NM;

	/**
	 * TC_RPAIR_MTHD.RPAIR_MTHD_CODE, 
	 * 보수공법코드.보수_공법_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MTHD_CODE") 
	public java.lang.String getRPAIR_MTHD_CODE() {
		return this.RPAIR_MTHD_CODE;
	}
 
	 /**
	 * TC_RPAIR_MTHD.RPAIR_MTHD_CODE, 
	 * 보수공법코드.보수_공법_코드 값설정
	 * @param rpairMthdCode
	 */
	public void setRPAIR_MTHD_CODE(java.lang.String rpairMthdCode) {
		this.RPAIR_MTHD_CODE = rpairMthdCode;
	}

	/**
	 * TC_RPAIR_MTHD.MSRC_CL_CODE, 
	 * 보수공법코드.공법_분류_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="MSRC_CL_CODE") 
	public java.lang.String getMSRC_CL_CODE() {
		return this.MSRC_CL_CODE;
	}
 
	 /**
	 * TC_RPAIR_MTHD.MSRC_CL_CODE, 
	 * 보수공법코드.공법_분류_코드 값설정
	 * @param msrcClCode
	 */
	public void setMSRC_CL_CODE(java.lang.String msrcClCode) {
		this.MSRC_CL_CODE = msrcClCode;
	}

	/**
	 * TC_RPAIR_MTHD.MSRC_NM, 
	 * 보수공법코드.공법_명 값읽기
	 * @return
	 */
	@JsonProperty(value="MSRC_NM") 
	public java.lang.String getMSRC_NM() {
		return this.MSRC_NM;
	}
 
	 /**
	 * TC_RPAIR_MTHD.MSRC_NM, 
	 * 보수공법코드.공법_명 값설정
	 * @param msrcNm
	 */
	public void setMSRC_NM(java.lang.String msrcNm) {
		this.MSRC_NM = msrcNm;
	}

	/**
	 * TC_RPAIR_MTHD.DELETE_AT, 
	 * 보수공법코드.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TC_RPAIR_MTHD.DELETE_AT, 
	 * 보수공법코드.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TC_RPAIR_MTHD.USE_AT, 
	 * 보수공법코드.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TC_RPAIR_MTHD.USE_AT, 
	 * 보수공법코드.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_RPAIR_MTHD.CRTR_NO, 
	 * 보수공법코드.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TC_RPAIR_MTHD.CRTR_NO, 
	 * 보수공법코드.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_RPAIR_MTHD.CREAT_DT, 
	 * 보수공법코드.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TC_RPAIR_MTHD.CREAT_DT, 
	 * 보수공법코드.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_RPAIR_MTHD.UPDUSR_NO, 
	 * 보수공법코드.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TC_RPAIR_MTHD.UPDUSR_NO, 
	 * 보수공법코드.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_RPAIR_MTHD.UPDT_DT, 
	 * 보수공법코드.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TC_RPAIR_MTHD.UPDT_DT, 
	 * 보수공법코드.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="MSRC_CL_NM") 
	public java.lang.String getMSRC_CL_NM() {
		return MSRC_CL_NM;
	}

	public void setMSRC_CL_NM(java.lang.String mSRC_CL_NM) {
		MSRC_CL_NM = mSRC_CL_NM;
	}
	@JsonProperty(value="MSRC_CT") 
	public java.lang.String getMSRC_CT() {
		return MSRC_CT;
	}

	public void setMSRC_CT(java.lang.String mSRC_CT) {
		MSRC_CT = mSRC_CT;
	}
	@JsonProperty(value="MSRC_LCLAS_NM") 
	public java.lang.String getMSRC_LCLAS_NM() {
		return MSRC_LCLAS_NM;
	}

	public void setMSRC_LCLAS_NM(java.lang.String mSRC_LCLAS_NM) {
		MSRC_LCLAS_NM = mSRC_LCLAS_NM;
	}
	@JsonProperty(value="MSRC_LCLAS_CODE") 
	public java.lang.String getMSRC_LCLAS_CODE() {
		return MSRC_LCLAS_CODE;
	}

	public void setMSRC_LCLAS_CODE(java.lang.String mSRC_LCLAS_CODE) {
		MSRC_LCLAS_CODE = mSRC_LCLAS_CODE;
	}
	@JsonProperty(value="MSR_DM_CODE") 
	public java.lang.String getMSR_DM_CODE() {
		return MSR_DM_CODE;
	}

	public void setMSR_DM_CODE(java.lang.String mSR_DM_CODE) {
		MSR_DM_CODE = mSR_DM_CODE;
	}
	@JsonProperty(value="MSR_DM_NM") 
	public java.lang.String getMSR_DM_NM() {
		return MSR_DM_NM;
	}

	public void setMSR_DM_NM(java.lang.String mSR_DM_NM) {
		MSR_DM_NM = mSR_DM_NM;
	}
	
	

}
