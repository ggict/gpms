
package kr.go.gg.gpms.predctfrmulaidx.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 예측_수식_지수
 *
 * @Class Name : PredctFrmulaIdxVO.java
 * @Description : PredctFrmulaIdx VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-08-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class PredctFrmulaIdxVO extends BaseVO {

	public PredctFrmulaIdxVO() {
		super();
	}
	
	/** 
	 * TN_PREDCT_FRMULA_IDX.PREDCT_IDX_NO, 
	 * 예측_수식_지수.예측_지수_번호
	 */
	@XmlElement
	private java.lang.String PREDCT_IDX_NO;

	/** 
	 * TN_PREDCT_FRMULA_IDX.CELL_TYPE, 
	 * 예측_수식_지수.섹션_구분
	 */
	@XmlElement
	private java.lang.String CELL_TYPE;

	/** 
	 * TN_PREDCT_FRMULA_IDX.PRDTFML_TRGET, 
	 * 예측_수식_지수.예측수식_적용대상
	 */
	@XmlElement
	private java.lang.String PRDTFML_TRGET;

	/** 
	 * TN_PREDCT_FRMULA_IDX.RK_A, 
	 * 예측_수식_지수.계수_A
	 */
	@XmlElement
	private java.lang.String RK_A;

	/** 
	 * TN_PREDCT_FRMULA_IDX.RK_B, 
	 * 예측_수식_지수.계수_B
	 */
	@XmlElement
	private java.lang.String RK_B;

	/** 
	 * TN_PREDCT_FRMULA_IDX.RK_C, 
	 * 예측_수식_지수.계수_C
	 */
	@XmlElement
	private java.lang.String RK_C;

	/** 
	 * TN_PREDCT_FRMULA_IDX.DELETE_AT, 
	 * 예측_수식_지수.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_PREDCT_FRMULA_IDX.USE_AT, 
	 * 예측_수식_지수.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_PREDCT_FRMULA_IDX.CRTR_NO, 
	 * 예측_수식_지수.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_PREDCT_FRMULA_IDX.CREAT_DT, 
	 * 예측_수식_지수.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_PREDCT_FRMULA_IDX.UPDUSR_NO, 
	 * 예측_수식_지수.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_PREDCT_FRMULA_IDX.UPDT_DT, 
	 * 예측_수식_지수.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	@XmlElement
	private java.lang.String UPDUSR_NM;

	/**
	 * TN_PREDCT_FRMULA_IDX.PREDCT_IDX_NO, 
	 * 예측_수식_지수.예측_지수_번호 
	 * @return
	 */
	@JsonProperty(value="PREDCT_IDX_NO") 
	public java.lang.String getPREDCT_IDX_NO() {
		return this.PREDCT_IDX_NO;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.PREDCT_IDX_NO, 
	 * 예측_수식_지수.예측_지수_번호 
	 * @param predctIdxNo
	 */
	public void setPREDCT_IDX_NO(java.lang.String predctIdxNo) {
		this.PREDCT_IDX_NO = predctIdxNo;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.CELL_TYPE, 
	 * 예측_수식_지수.섹션_구분 
	 * @return
	 */
	@JsonProperty(value="CELL_TYPE") 
	public java.lang.String getCELL_TYPE() {
		return this.CELL_TYPE;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.CELL_TYPE, 
	 * 예측_수식_지수.섹션_구분 
	 * @param cellType
	 */
	public void setCELL_TYPE(java.lang.String cellType) {
		this.CELL_TYPE = cellType;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.PRDTFML_TRGET, 
	 * 예측_수식_지수.예측수식_적용대상 
	 * @return
	 */
	@JsonProperty(value="PRDTFML_TRGET") 
	public java.lang.String getPRDTFML_TRGET() {
		return this.PRDTFML_TRGET;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.PRDTFML_TRGET, 
	 * 예측_수식_지수.예측수식_적용대상 
	 * @param prdtfmlTrget
	 */
	public void setPRDTFML_TRGET(java.lang.String prdtfmlTrget) {
		this.PRDTFML_TRGET = prdtfmlTrget;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.RK_A, 
	 * 예측_수식_지수.계수_A 
	 * @return
	 */
	@JsonProperty(value="RK_A") 
	public java.lang.String getRK_A() {
		return this.RK_A;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.RK_A, 
	 * 예측_수식_지수.계수_A 
	 * @param rkA
	 */
	public void setRK_A(java.lang.String rkA) {
		this.RK_A = rkA;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.RK_B, 
	 * 예측_수식_지수.계수_B 
	 * @return
	 */
	@JsonProperty(value="RK_B") 
	public java.lang.String getRK_B() {
		return this.RK_B;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.RK_B, 
	 * 예측_수식_지수.계수_B 
	 * @param rkB
	 */
	public void setRK_B(java.lang.String rkB) {
		this.RK_B = rkB;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.RK_C, 
	 * 예측_수식_지수.계수_C 
	 * @return
	 */
	@JsonProperty(value="RK_C") 
	public java.lang.String getRK_C() {
		return this.RK_C;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.RK_C, 
	 * 예측_수식_지수.계수_C 
	 * @param rkC
	 */
	public void setRK_C(java.lang.String rkC) {
		this.RK_C = rkC;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.DELETE_AT, 
	 * 예측_수식_지수.삭제_여부 
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.DELETE_AT, 
	 * 예측_수식_지수.삭제_여부 
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.USE_AT, 
	 * 예측_수식_지수.사용_여부 
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.USE_AT, 
	 * 예측_수식_지수.사용_여부 
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.CRTR_NO, 
	 * 예측_수식_지수.생성자_번호 
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.CRTR_NO, 
	 * 예측_수식_지수.생성자_번호 
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.CREAT_DT, 
	 * 예측_수식_지수.생성_일시 
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.CREAT_DT, 
	 * 예측_수식_지수.생성_일시 
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.UPDUSR_NO, 
	 * 예측_수식_지수.수정자_번호 
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.UPDUSR_NO, 
	 * 예측_수식_지수.수정자_번호 
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_PREDCT_FRMULA_IDX.UPDT_DT, 
	 * 예측_수식_지수.수정_일시 
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_PREDCT_FRMULA_IDX.UPDT_DT, 
	 * 예측_수식_지수.수정_일시 
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="UPDUSR_NM") 
	public java.lang.String getUPDUSR_NM() {
		return this.UPDUSR_NM;
	}

	public void setUPDUSR_NM(java.lang.String uPDUSR_NM) {
		this.UPDUSR_NM = uPDUSR_NM;
	}

	
}
