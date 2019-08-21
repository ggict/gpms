
package kr.go.gg.gpms.pavsttusgradstdr.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 포장상태등급기준
 *
 * @Class Name : PavSttusGradStdrVO.java
 * @Description : PavSttusGradStdr VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class PavSttusGradStdrVO extends BaseVO {

	public PavSttusGradStdrVO() {
		super();
	}
	
	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_NO, 
	 * 포장상태등급기준.등급_번호
	 */
	@XmlElement
	private java.math.BigDecimal GRAD_NO;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_STDR_CODE, 
	 * 포장상태등급기준.등급_기준_코드
	 */
	@XmlElement
	private java.lang.String GRAD_STDR_CODE;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.APPLC_YEAR, 
	 * 포장상태등급기준.적용_년도
	 */
	@XmlElement
	private java.lang.String APPLC_YEAR;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.SRVY_INFO_GRAD, 
	 * 포장상태등급기준.조사_정보_등급
	 */
	@XmlElement
	private java.lang.String SRVY_INFO_GRAD;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MUMM_STDR, 
	 * 포장상태등급기준.등급_최소_기준
	 */
	@XmlElement
	private java.math.BigDecimal GRAD_MUMM_STDR;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MXMM_STDR, 
	 * 포장상태등급기준.등급_최대_기준
	 */
	@XmlElement
	private java.math.BigDecimal GRAD_MXMM_STDR;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_JDGMNT_STDR, 
	 * 포장상태등급기준.등급_판정_기준
	 */
	@XmlElement
	private java.lang.String GRAD_JDGMNT_STDR;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_SCTN_STTUS, 
	 * 포장상태등급기준.등급_구간_상태
	 */
	@XmlElement
	private java.lang.String GRAD_SCTN_STTUS;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_COLOR_CODE, 
	 * 포장상태등급기준.등급_색상_코드
	 */
	@XmlElement
	private java.lang.String GRAD_COLOR_CODE;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.ALPHA, 
	 * 포장상태등급기준.ALPHA
	 */
	@XmlElement
	private java.lang.String ALPHA;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.DELETE_AT, 
	 * 포장상태등급기준.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.USE_AT, 
	 * 포장상태등급기준.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.CRTR_NO, 
	 * 포장상태등급기준.생성자_번호
	 */
	@XmlElement
	private java.math.BigDecimal CRTR_NO;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.CREAT_DT, 
	 * 포장상태등급기준.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.UPDUSR_NO, 
	 * 포장상태등급기준.수정자_번호
	 */
	@XmlElement
	private java.math.BigDecimal UPDUSR_NO;

	/** 
	 * TN_PAV_STTUS_GRAD_STDR.UPDT_DT, 
	 * 포장상태등급기준.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_NO, 
	 * 포장상태등급기준.등급_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_NO") 
	public java.math.BigDecimal getGRAD_NO() {
		return this.GRAD_NO;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_NO, 
	 * 포장상태등급기준.등급_번호 값설정
	 * @param gradNo
	 */
	public void setGRAD_NO(java.math.BigDecimal gradNo) {
		this.GRAD_NO = gradNo;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_STDR_CODE, 
	 * 포장상태등급기준.등급_기준_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_STDR_CODE") 
	public java.lang.String getGRAD_STDR_CODE() {
		return this.GRAD_STDR_CODE;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_STDR_CODE, 
	 * 포장상태등급기준.등급_기준_코드 값설정
	 * @param gradStdrCode
	 */
	public void setGRAD_STDR_CODE(java.lang.String gradStdrCode) {
		this.GRAD_STDR_CODE = gradStdrCode;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.APPLC_YEAR, 
	 * 포장상태등급기준.적용_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="APPLC_YEAR") 
	public java.lang.String getAPPLC_YEAR() {
		return this.APPLC_YEAR;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.APPLC_YEAR, 
	 * 포장상태등급기준.적용_년도 값설정
	 * @param applcYear
	 */
	public void setAPPLC_YEAR(java.lang.String applcYear) {
		this.APPLC_YEAR = applcYear;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.SRVY_INFO_GRAD, 
	 * 포장상태등급기준.조사_정보_등급 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_INFO_GRAD") 
	public java.lang.String getSRVY_INFO_GRAD() {
		return this.SRVY_INFO_GRAD;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.SRVY_INFO_GRAD, 
	 * 포장상태등급기준.조사_정보_등급 값설정
	 * @param srvyInfoGrad
	 */
	public void setSRVY_INFO_GRAD(java.lang.String srvyInfoGrad) {
		this.SRVY_INFO_GRAD = srvyInfoGrad;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MUMM_STDR, 
	 * 포장상태등급기준.등급_최소_기준 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_MUMM_STDR") 
	public java.math.BigDecimal getGRAD_MUMM_STDR() {
		return this.GRAD_MUMM_STDR;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MUMM_STDR, 
	 * 포장상태등급기준.등급_최소_기준 값설정
	 * @param gradMummStdr
	 */
	public void setGRAD_MUMM_STDR(java.math.BigDecimal gradMummStdr) {
		this.GRAD_MUMM_STDR = gradMummStdr;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MXMM_STDR, 
	 * 포장상태등급기준.등급_최대_기준 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_MXMM_STDR") 
	public java.math.BigDecimal getGRAD_MXMM_STDR() {
		return this.GRAD_MXMM_STDR;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_MXMM_STDR, 
	 * 포장상태등급기준.등급_최대_기준 값설정
	 * @param gradMxmmStdr
	 */
	public void setGRAD_MXMM_STDR(java.math.BigDecimal gradMxmmStdr) {
		this.GRAD_MXMM_STDR = gradMxmmStdr;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_JDGMNT_STDR, 
	 * 포장상태등급기준.등급_판정_기준 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_JDGMNT_STDR") 
	public java.lang.String getGRAD_JDGMNT_STDR() {
		return this.GRAD_JDGMNT_STDR;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_JDGMNT_STDR, 
	 * 포장상태등급기준.등급_판정_기준 값설정
	 * @param gradJdgmntStdr
	 */
	public void setGRAD_JDGMNT_STDR(java.lang.String gradJdgmntStdr) {
		this.GRAD_JDGMNT_STDR = gradJdgmntStdr;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_SCTN_STTUS, 
	 * 포장상태등급기준.등급_구간_상태 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_SCTN_STTUS") 
	public java.lang.String getGRAD_SCTN_STTUS() {
		return this.GRAD_SCTN_STTUS;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_SCTN_STTUS, 
	 * 포장상태등급기준.등급_구간_상태 값설정
	 * @param gradSctnSttus
	 */
	public void setGRAD_SCTN_STTUS(java.lang.String gradSctnSttus) {
		this.GRAD_SCTN_STTUS = gradSctnSttus;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_COLOR_CODE, 
	 * 포장상태등급기준.등급_색상_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="GRAD_COLOR_CODE") 
	public java.lang.String getGRAD_COLOR_CODE() {
		return this.GRAD_COLOR_CODE;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.GRAD_COLOR_CODE, 
	 * 포장상태등급기준.등급_색상_코드 값설정
	 * @param gradColorCode
	 */
	public void setGRAD_COLOR_CODE(java.lang.String gradColorCode) {
		this.GRAD_COLOR_CODE = gradColorCode;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.ALPHA, 
	 * 포장상태등급기준.ALPHA 값읽기
	 * @return
	 */
	@JsonProperty(value="ALPHA") 
	public java.lang.String getALPHA() {
		return this.ALPHA;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.ALPHA, 
	 * 포장상태등급기준.ALPHA 값설정
	 * @param alpha
	 */
	public void setALPHA(java.lang.String alpha) {
		this.ALPHA = alpha;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.DELETE_AT, 
	 * 포장상태등급기준.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.DELETE_AT, 
	 * 포장상태등급기준.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.USE_AT, 
	 * 포장상태등급기준.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.USE_AT, 
	 * 포장상태등급기준.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.CRTR_NO, 
	 * 포장상태등급기준.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.math.BigDecimal getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.CRTR_NO, 
	 * 포장상태등급기준.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.math.BigDecimal crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.CREAT_DT, 
	 * 포장상태등급기준.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.CREAT_DT, 
	 * 포장상태등급기준.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.UPDUSR_NO, 
	 * 포장상태등급기준.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.math.BigDecimal getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.UPDUSR_NO, 
	 * 포장상태등급기준.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.math.BigDecimal updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_PAV_STTUS_GRAD_STDR.UPDT_DT, 
	 * 포장상태등급기준.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_PAV_STTUS_GRAD_STDR.UPDT_DT, 
	 * 포장상태등급기준.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
