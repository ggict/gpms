
package kr.go.gg.gpms.rpairmthdfrmula.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 보수_공법_수식_관리
 *
 * @Class Name : RpairMthdFrmulaVO.java
 * @Description : RpairMthdFrmula VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairMthdFrmulaVO extends BaseVO {

	public RpairMthdFrmulaVO() {
		super();
	}
	
	/** 
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_CODE, 
	 * 보수_공법_수식_관리.단계_상태_코드
	 */
	@XmlElement
	private java.lang.String STEP_STTUS_CODE;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.STEP_SE_CODE, 
	 * 보수_공법_수식_관리.단계_구분_코드
	 */
	@XmlElement
	private java.lang.String STEP_SE_CODE;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_NM, 
	 * 보수_공법_수식_관리.단계_상태_명
	 */
	@XmlElement
	private java.lang.String STEP_STTUS_NM;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_VAL, 
	 * 보수_공법_수식_관리.시작_값
	 */
	@XmlElement
	private java.lang.String BEGIN_VAL;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.END_VAL, 
	 * 보수_공법_수식_관리.종료_값
	 */
	@XmlElement
	private java.lang.String END_VAL;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.시작_연산_구분_코드
	 */
	@XmlElement
	private java.lang.String BEGIN_CALC_SE_CODE;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.END_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.종료_연산_구분_코드
	 */
	@XmlElement
	private java.lang.String END_CALC_SE_CODE;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.DELETE_AT, 
	 * 보수_공법_수식_관리.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.USE_AT, 
	 * 보수_공법_수식_관리.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.CRTR_NO, 
	 * 보수_공법_수식_관리.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.CREAT_DT, 
	 * 보수_공법_수식_관리.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.UPDUSR_NO, 
	 * 보수_공법_수식_관리.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_RPAIR_MTHD_FRMULA.UPDT_DT, 
	 * 보수_공법_수식_관리.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;
	
	/** 
	 * TN_RPAIR_MTHD_FRMULA.CALC_ORDR, 
	 * 보수_공법_수식_관리.계산_순서
	 */
	@XmlElement
	private  java.lang.String CALC_ORDR;
	
	/** 
	 * TN_RPAIR_MTHD_FRMULA.FRMULA_NO, 
	 * 보수_공법_수식_관리.수식_번호
	 */
	@XmlElement
	private  java.lang.String FRMULA_NO;
	
	// 보수 공법 수식 타입
	@XmlElement
	private String FRMULA_TYPE;
	
	// 보수 공법 수식 리스트
	@XmlElement
	private List<RpairMthdFrmulaVO> rpairMthdFrmulaList;

	// 보수 공법 이력 번호
	@XmlElement
	private String MF_HIST_NO;
	
	/**
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_CODE, 
	 * 보수_공법_수식_관리.단계_상태_코드 
	 * @return
	 */
	@JsonProperty(value="STEP_STTUS_CODE") 
	public java.lang.String getSTEP_STTUS_CODE() {
		return this.STEP_STTUS_CODE;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_CODE, 
	 * 보수_공법_수식_관리.단계_상태_코드 
	 * @param stepSttusCode
	 */
	public void setSTEP_STTUS_CODE(java.lang.String stepSttusCode) {
		this.STEP_STTUS_CODE = stepSttusCode;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.STEP_SE_CODE, 
	 * 보수_공법_수식_관리.단계_구분_코드 
	 * @return
	 */
	@JsonProperty(value="STEP_SE_CODE") 
	public java.lang.String getSTEP_SE_CODE() {
		return this.STEP_SE_CODE;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.STEP_SE_CODE, 
	 * 보수_공법_수식_관리.단계_구분_코드 
	 * @param stepSeCode
	 */
	public void setSTEP_SE_CODE(java.lang.String stepSeCode) {
		this.STEP_SE_CODE = stepSeCode;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_NM, 
	 * 보수_공법_수식_관리.단계_상태_명 
	 * @return
	 */
	@JsonProperty(value="STEP_STTUS_NM") 
	public java.lang.String getSTEP_STTUS_NM() {
		return this.STEP_STTUS_NM;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.STEP_STTUS_NM, 
	 * 보수_공법_수식_관리.단계_상태_명 
	 * @param stepSttusNm
	 */
	public void setSTEP_STTUS_NM(java.lang.String stepSttusNm) {
		this.STEP_STTUS_NM = stepSttusNm;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_VAL, 
	 * 보수_공법_수식_관리.시작_값 
	 * @return
	 */
	@JsonProperty(value="BEGIN_VAL") 
	public java.lang.String getBEGIN_VAL() {
		return this.BEGIN_VAL;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_VAL, 
	 * 보수_공법_수식_관리.시작_값 
	 * @param beginVal
	 */
	public void setBEGIN_VAL(java.lang.String beginVal) {
		this.BEGIN_VAL = beginVal;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.END_VAL, 
	 * 보수_공법_수식_관리.종료_값 
	 * @return
	 */
	@JsonProperty(value="END_VAL") 
	public java.lang.String getEND_VAL() {
		return this.END_VAL;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.END_VAL, 
	 * 보수_공법_수식_관리.종료_값 
	 * @param endVal
	 */
	public void setEND_VAL(java.lang.String endVal) {
		this.END_VAL = endVal;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.시작_연산_구분_코드 
	 * @return
	 */
	@JsonProperty(value="BEGIN_CALC_SE_CODE") 
	public java.lang.String getBEGIN_CALC_SE_CODE() {
		return this.BEGIN_CALC_SE_CODE;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.BEGIN_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.시작_연산_구분_코드 
	 * @param beginCalcSeCode
	 */
	public void setBEGIN_CALC_SE_CODE(java.lang.String beginCalcSeCode) {
		this.BEGIN_CALC_SE_CODE = beginCalcSeCode;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.END_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.종료_연산_구분_코드 
	 * @return
	 */
	@JsonProperty(value="END_CALC_SE_CODE") 
	public java.lang.String getEND_CALC_SE_CODE() {
		return this.END_CALC_SE_CODE;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.END_CALC_SE_CODE, 
	 * 보수_공법_수식_관리.종료_연산_구분_코드 
	 * @param endCalcSeCode
	 */
	public void setEND_CALC_SE_CODE(java.lang.String endCalcSeCode) {
		this.END_CALC_SE_CODE = endCalcSeCode;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.DELETE_AT, 
	 * 보수_공법_수식_관리.삭제_여부 
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.DELETE_AT, 
	 * 보수_공법_수식_관리.삭제_여부 
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.USE_AT, 
	 * 보수_공법_수식_관리.사용_여부 
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.USE_AT, 
	 * 보수_공법_수식_관리.사용_여부 
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.CRTR_NO, 
	 * 보수_공법_수식_관리.생성자_번호 
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.CRTR_NO, 
	 * 보수_공법_수식_관리.생성자_번호 
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.CREAT_DT, 
	 * 보수_공법_수식_관리.생성_일시 
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.CREAT_DT, 
	 * 보수_공법_수식_관리.생성_일시 
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.UPDUSR_NO, 
	 * 보수_공법_수식_관리.수정자_번호 
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.UPDUSR_NO, 
	 * 보수_공법_수식_관리.수정자_번호 
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_RPAIR_MTHD_FRMULA.UPDT_DT, 
	 * 보수_공법_수식_관리.수정_일시 
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_FRMULA.UPDT_DT, 
	 * 보수_공법_수식_관리.수정_일시 
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="CALC_ORDR") 
	public java.lang.String getCALC_ORDR() {
		return CALC_ORDR;
	}

	public void setCALC_ORDR(java.lang.String cALC_ORDR) {
		CALC_ORDR = cALC_ORDR;
	}

	@JsonProperty(value="FRMULA_NO") 
	public java.lang.String getFRMULA_NO() {
		return FRMULA_NO;
	}

	public void setFRMULA_NO(java.lang.String fRMULA_NO) {
		FRMULA_NO = fRMULA_NO;
	}

	@JsonProperty(value="FRMULA_TYPE") 
	public String getFRMULA_TYPE() {
		return FRMULA_TYPE;
	}

	public void setFRMULA_TYPE(String fRMULA_TYPE) {
		FRMULA_TYPE = fRMULA_TYPE;
	}

	@JsonProperty(value="rpairMthdFrmulaList") 
	public List<RpairMthdFrmulaVO> getRpairMthdFrmulaList() {
		return rpairMthdFrmulaList;
	}

	public void setRpairMthdFrmulaList(List<RpairMthdFrmulaVO> rpairMthdFrmulaList) {
		this.rpairMthdFrmulaList = rpairMthdFrmulaList;
	}

	@JsonProperty(value="MF_HIST_NO") 
	public String getMF_HIST_NO() {
		return MF_HIST_NO;
	}

	public void setMF_HIST_NO(String mF_HIST_NO) {
		MF_HIST_NO = mF_HIST_NO;
	}
	
	

}
