
package kr.go.gg.gpms.pavfrmulaidx.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 포장_수식_지수
 *
 * @Class Name : PavFrmulaIdxVO.java
 * @Description : PavFrmulaIdx VO class
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
public class PavFrmulaIdxVO extends BaseVO {

	public PavFrmulaIdxVO() {
		super();
	}
	
	/** 
	 * TN_PAV_FRMULA_IDX.IDX_NO, 
	 * 포장_수식_지수.지수_번호
	 */
	@XmlElement
	private java.lang.String IDX_NO;

	/** 
	 * TN_PAV_FRMULA_IDX.FRMULA_NO, 
	 * 포장_수식_지수.수식_번호
	 */
	@XmlElement
	private java.lang.String FRMULA_NO;

	/** 
	 * TN_PAV_FRMULA_IDX.IDX_NM, 
	 * 포장_수식_지수.지수_명
	 */
	@XmlElement
	private java.lang.String IDX_NM;

	/** 
	 * TN_PAV_FRMULA_IDX.IDX_VAL, 
	 * 포장_수식_지수.지수_값
	 */
	@XmlElement
	private java.lang.String IDX_VAL;

	/** 
	 * TN_PAV_FRMULA_IDX.DC, 
	 * 포장_수식_지수.설명
	 */
	@XmlElement
	private java.lang.String DC;

	/** 
	 * TN_PAV_FRMULA_IDX.DELETE_AT, 
	 * 포장_수식_지수.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_PAV_FRMULA_IDX.USE_AT, 
	 * 포장_수식_지수.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_PAV_FRMULA_IDX.CRTR_NO, 
	 * 포장_수식_지수.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_PAV_FRMULA_IDX.CREAT_DT, 
	 * 포장_수식_지수.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_PAV_FRMULA_IDX.UPDUSR_NO, 
	 * 포장_수식_지수.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_PAV_FRMULA_IDX.UPDT_DT, 
	 * 포장_수식_지수.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TN_PAV_FRMULA_IDX.IDX_NO, 
	 * 포장_수식_지수.지수_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="IDX_NO") 
	public java.lang.String getIDX_NO() {
		return this.IDX_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.IDX_NO, 
	 * 포장_수식_지수.지수_번호 값설정
	 * @param idxNo
	 */
	public void setIDX_NO(java.lang.String idxNo) {
		this.IDX_NO = idxNo;
	}

	/**
	 * TN_PAV_FRMULA_IDX.FRMULA_NO, 
	 * 포장_수식_지수.수식_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FRMULA_NO") 
	public java.lang.String getFRMULA_NO() {
		return this.FRMULA_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.FRMULA_NO, 
	 * 포장_수식_지수.수식_번호 값설정
	 * @param frmulaNo
	 */
	public void setFRMULA_NO(java.lang.String frmulaNo) {
		this.FRMULA_NO = frmulaNo;
	}

	/**
	 * TN_PAV_FRMULA_IDX.IDX_NM, 
	 * 포장_수식_지수.지수_명 값읽기
	 * @return
	 */
	@JsonProperty(value="IDX_NM") 
	public java.lang.String getIDX_NM() {
		return this.IDX_NM;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.IDX_NM, 
	 * 포장_수식_지수.지수_명 값설정
	 * @param idxNm
	 */
	public void setIDX_NM(java.lang.String idxNm) {
		this.IDX_NM = idxNm;
	}

	/**
	 * TN_PAV_FRMULA_IDX.IDX_VAL, 
	 * 포장_수식_지수.지수_값 값읽기
	 * @return
	 */
	@JsonProperty(value="IDX_VAL") 
	public java.lang.String getIDX_VAL() {
		return this.IDX_VAL;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.IDX_VAL, 
	 * 포장_수식_지수.지수_값 값설정
	 * @param idxVal
	 */
	public void setIDX_VAL(java.lang.String idxVal) {
		this.IDX_VAL = idxVal;
	}

	/**
	 * TN_PAV_FRMULA_IDX.DC, 
	 * 포장_수식_지수.설명 값읽기
	 * @return
	 */
	@JsonProperty(value="DC") 
	public java.lang.String getDC() {
		return this.DC;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.DC, 
	 * 포장_수식_지수.설명 값설정
	 * @param dc
	 */
	public void setDC(java.lang.String dc) {
		this.DC = dc;
	}

	/**
	 * TN_PAV_FRMULA_IDX.DELETE_AT, 
	 * 포장_수식_지수.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.DELETE_AT, 
	 * 포장_수식_지수.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_PAV_FRMULA_IDX.USE_AT, 
	 * 포장_수식_지수.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.USE_AT, 
	 * 포장_수식_지수.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_PAV_FRMULA_IDX.CRTR_NO, 
	 * 포장_수식_지수.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.CRTR_NO, 
	 * 포장_수식_지수.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_PAV_FRMULA_IDX.CREAT_DT, 
	 * 포장_수식_지수.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.CREAT_DT, 
	 * 포장_수식_지수.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_PAV_FRMULA_IDX.UPDUSR_NO, 
	 * 포장_수식_지수.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.UPDUSR_NO, 
	 * 포장_수식_지수.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_PAV_FRMULA_IDX.UPDT_DT, 
	 * 포장_수식_지수.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_PAV_FRMULA_IDX.UPDT_DT, 
	 * 포장_수식_지수.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
