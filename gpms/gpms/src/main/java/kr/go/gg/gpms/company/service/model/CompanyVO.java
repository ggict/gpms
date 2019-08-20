
package kr.go.gg.gpms.company.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 업체정보
 *
 * @Class Name : CompanyVO.java
 * @Description : Company VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CompanyVO extends BaseVO {

	public CompanyVO() {
		super();
	}
	
	/** 
	 * TN_COMPANY.CO_NO, 
	 * 업체정보.업체_번호
	 */
	@XmlElement
	private java.lang.String CO_NO;

	/** 
	 * TN_COMPANY.CO_NM, 
	 * 업체정보.업체_명
	 */
	@XmlElement
	private java.lang.String CO_NM;

	/** 
	 * TN_COMPANY.RPRSNTV_NM, 
	 * 업체정보.대표자_명
	 */
	@XmlElement
	private java.lang.String RPRSNTV_NM;

	/** 
	 * TN_COMPANY.BIZ_LOC, 
	 * 업체정보.영업_소재지
	 */
	@XmlElement
	private java.lang.String BIZ_LOC;

	/** 
	 * TN_COMPANY.RPRSNT_TEL_NO, 
	 * 업체정보.대표_전화_번호
	 */
	@XmlElement
	private java.lang.String RPRSNT_TEL_NO;

	/** 
	 * TN_COMPANY.BIZ_NO, 
	 * 업체정보.사업자_번호
	 */
	@XmlElement
	private java.lang.String BIZ_NO;

	/** 
	 * TN_COMPANY.CHARGER_NM, 
	 * 업체정보.담당자_명
	 */
	@XmlElement
	private java.lang.String CHARGER_NM;

	/** 
	 * TN_COMPANY.CHARGER_TEL_NO, 
	 * 업체정보.담당자_전화_번호
	 */
	@XmlElement
	private java.lang.String CHARGER_TEL_NO;

	/** 
	 * TN_COMPANY.CO_STTUS_CODE, 
	 * 업체정보.업체_상태_코드
	 */
	@XmlElement
	private java.lang.String CO_STTUS_CODE;
	
	
	/** 
	 * TN_COMPANY.CO_STTUS_NM, 
	 * 업체정보.업체_상태
	 */
	@XmlElement
	private java.lang.String CO_STTUS_NM;

	/** 
	 * TN_COMPANY.REGIST_INDUTY, 
	 * 업체정보.등록_업종
	 */
	@XmlElement
	private java.lang.String REGIST_INDUTY;

	/** 
	 * TN_COMPANY.MFRC_CNTRWK, 
	 * 업체정보.주력_공사
	 */
	@XmlElement
	private java.lang.String MFRC_CNTRWK;

	/** 
	 * TN_COMPANY.MAJOR_CNTRWK_AREA, 
	 * 업체정보.주요_공사_지역
	 */
	@XmlElement
	private java.lang.String MAJOR_CNTRWK_AREA;

	/** 
	 * TN_COMPANY.CRTR_NO, 
	 * 업체정보.생성자_번호
	 */
	@XmlElement
	private java.math.BigDecimal CRTR_NO;

	/** 
	 * TN_COMPANY.CREAT_DT, 
	 * 업체정보.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_COMPANY.UPDUSR_NO, 
	 * 업체정보.수정자_번호
	 */
	@XmlElement
	private java.math.BigDecimal UPDUSR_NO;

	/** 
	 * TN_COMPANY.UPDT_DT, 
	 * 업체정보.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/** 
	 * TN_COMPANY.USE_AT, 
	 * 업체정보.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_COMPANY.DELETE_AT, 
	 * 업체정보.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/**
	 * TN_COMPANY.CO_NO, 
	 * 업체정보.업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CO_NO") 
		public java.lang.String getCO_NO() {
		return this.CO_NO;
	}
 
	 /**
	 * TN_COMPANY.CO_NO, 
	 * 업체정보.업체_번호 값설정
	 * @param coNo
	 */
	public void setCO_NO(java.lang.String coNo) {
		this.CO_NO = coNo;
	}

	/**
	 * TN_COMPANY.CO_NM, 
	 * 업체정보.업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CO_NM") 
		public java.lang.String getCO_NM() {
		return this.CO_NM;
	}
 
	 /**
	 * TN_COMPANY.CO_NM, 
	 * 업체정보.업체_명 값설정
	 * @param coNm
	 */
	public void setCO_NM(java.lang.String coNm) {
		this.CO_NM = coNm;
	}

	/**
	 * TN_COMPANY.RPRSNTV_NM, 
	 * 업체정보.대표자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPRSNTV_NM") 
		public java.lang.String getRPRSNTV_NM() {
		return this.RPRSNTV_NM;
	}
 
	 /**
	 * TN_COMPANY.RPRSNTV_NM, 
	 * 업체정보.대표자_명 값설정
	 * @param rprsntvNm
	 */
	public void setRPRSNTV_NM(java.lang.String rprsntvNm) {
		this.RPRSNTV_NM = rprsntvNm;
	}

	/**
	 * TN_COMPANY.BIZ_LOC, 
	 * 업체정보.영업_소재지 값읽기
	 * @return
	 */
	@JsonProperty(value="BIZ_LOC") 
		public java.lang.String getBIZ_LOC() {
		return this.BIZ_LOC;
	}
 
	 /**
	 * TN_COMPANY.BIZ_LOC, 
	 * 업체정보.영업_소재지 값설정
	 * @param bizLoc
	 */
	public void setBIZ_LOC(java.lang.String bizLoc) {
		this.BIZ_LOC = bizLoc;
	}

	/**
	 * TN_COMPANY.RPRSNT_TEL_NO, 
	 * 업체정보.대표_전화_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="RPRSNT_TEL_NO") 
		public java.lang.String getRPRSNT_TEL_NO() {
		return this.RPRSNT_TEL_NO;
	}
 
	 /**
	 * TN_COMPANY.RPRSNT_TEL_NO, 
	 * 업체정보.대표_전화_번호 값설정
	 * @param rprsntTelNo
	 */
	public void setRPRSNT_TEL_NO(java.lang.String rprsntTelNo) {
		this.RPRSNT_TEL_NO = rprsntTelNo;
	}

	/**
	 * TN_COMPANY.BIZ_NO, 
	 * 업체정보.사업자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="BIZ_NO") 
		public java.lang.String getBIZ_NO() {
		return this.BIZ_NO;
	}
 
	 /**
	 * TN_COMPANY.BIZ_NO, 
	 * 업체정보.사업자_번호 값설정
	 * @param bizNo
	 */
	public void setBIZ_NO(java.lang.String bizNo) {
		this.BIZ_NO = bizNo;
	}

	/**
	 * TN_COMPANY.CHARGER_NM, 
	 * 업체정보.담당자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CHARGER_NM") 
		public java.lang.String getCHARGER_NM() {
		return this.CHARGER_NM;
	}
 
	 /**
	 * TN_COMPANY.CHARGER_NM, 
	 * 업체정보.담당자_명 값설정
	 * @param chargerNm
	 */
	public void setCHARGER_NM(java.lang.String chargerNm) {
		this.CHARGER_NM = chargerNm;
	}

	/**
	 * TN_COMPANY.CHARGER_TEL_NO, 
	 * 업체정보.담당자_전화_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CHARGER_TEL_NO") 
		public java.lang.String getCHARGER_TEL_NO() {
		return this.CHARGER_TEL_NO;
	}
 
	 /**
	 * TN_COMPANY.CHARGER_TEL_NO, 
	 * 업체정보.담당자_전화_번호 값설정
	 * @param chargerTelNo
	 */
	public void setCHARGER_TEL_NO(java.lang.String chargerTelNo) {
		this.CHARGER_TEL_NO = chargerTelNo;
	}

	/**
	 * TN_COMPANY.CO_STTUS_CODE, 
	 * 업체정보.업체_상태_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="CO_STTUS_CODE") 
		public java.lang.String getCO_STTUS_CODE() {
		return this.CO_STTUS_CODE;
	}
 
	 /**
	 * TN_COMPANY.CO_STTUS_CODE, 
	 * 업체정보.업체_상태_코드 값설정
	 * @param coSttusCode
	 */
	public void setCO_STTUS_CODE(java.lang.String coSttusCode) {
		this.CO_STTUS_CODE = coSttusCode;
	}

	/**
	 * TN_COMPANY.REGIST_INDUTY, 
	 * 업체정보.등록_업종 값읽기
	 * @return
	 */
	@JsonProperty(value="REGIST_INDUTY") 
		public java.lang.String getREGIST_INDUTY() {
		return this.REGIST_INDUTY;
	}
 
	 /**
	 * TN_COMPANY.REGIST_INDUTY, 
	 * 업체정보.등록_업종 값설정
	 * @param registInduty
	 */
	public void setREGIST_INDUTY(java.lang.String registInduty) {
		this.REGIST_INDUTY = registInduty;
	}

	/**
	 * TN_COMPANY.MFRC_CNTRWK, 
	 * 업체정보.주력_공사 값읽기
	 * @return
	 */
	@JsonProperty(value="MFRC_CNTRWK") 
		public java.lang.String getMFRC_CNTRWK() {
		return this.MFRC_CNTRWK;
	}
 
	 /**
	 * TN_COMPANY.MFRC_CNTRWK, 
	 * 업체정보.주력_공사 값설정
	 * @param mfrcCntrwk
	 */
	public void setMFRC_CNTRWK(java.lang.String mfrcCntrwk) {
		this.MFRC_CNTRWK = mfrcCntrwk;
	}

	/**
	 * TN_COMPANY.MAJOR_CNTRWK_AREA, 
	 * 업체정보.주요_공사_지역 값읽기
	 * @return
	 */
	@JsonProperty(value="MAJOR_CNTRWK_AREA") 
		public java.lang.String getMAJOR_CNTRWK_AREA() {
		return this.MAJOR_CNTRWK_AREA;
	}
 
	 /**
	 * TN_COMPANY.MAJOR_CNTRWK_AREA, 
	 * 업체정보.주요_공사_지역 값설정
	 * @param majorCntrwkArea
	 */
	public void setMAJOR_CNTRWK_AREA(java.lang.String majorCntrwkArea) {
		this.MAJOR_CNTRWK_AREA = majorCntrwkArea;
	}

	/**
	 * TN_COMPANY.CRTR_NO, 
	 * 업체정보.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
		public java.math.BigDecimal getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_COMPANY.CRTR_NO, 
	 * 업체정보.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.math.BigDecimal crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_COMPANY.CREAT_DT, 
	 * 업체정보.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_COMPANY.CREAT_DT, 
	 * 업체정보.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_COMPANY.UPDUSR_NO, 
	 * 업체정보.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
		public java.math.BigDecimal getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_COMPANY.UPDUSR_NO, 
	 * 업체정보.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.math.BigDecimal updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_COMPANY.UPDT_DT, 
	 * 업체정보.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_COMPANY.UPDT_DT, 
	 * 업체정보.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_COMPANY.USE_AT, 
	 * 업체정보.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_COMPANY.USE_AT, 
	 * 업체정보.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_COMPANY.DELETE_AT, 
	 * 업체정보.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_COMPANY.DELETE_AT, 
	 * 업체정보.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}
	
	@JsonProperty(value="CO_STTUS_NM") 
	public java.lang.String getCO_STTUS_NM() {
		return CO_STTUS_NM;
	}

	public void setCO_STTUS_NM(java.lang.String cO_STTUS_NM) {
		CO_STTUS_NM = cO_STTUS_NM;
	}

}
