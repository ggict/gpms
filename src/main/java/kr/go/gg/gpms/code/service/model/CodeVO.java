
package kr.go.gg.gpms.code.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.clcode.service.model.ClCodeVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 공통코드
 *
 * @Class Name : CodeVO.java
 * @Description : Code VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CodeVO extends ClCodeVO {

	public CodeVO() {
		super();
	}

	/**
	 * TC_CODE.CODE_VAL,
	 * 공통코드.코드_값
	 */
	@XmlElement
	private java.lang.String CODE_VAL;




	/**
	 * TC_CODE.UPPER_CODE_VAL,
	 * 공통코드.상위_코드_값
	 */
	@XmlElement
	private java.lang.String UPPER_CODE_VAL;

	/**
	 * TC_CODE.CL_CODE,
	 * 공통코드.분류_코드
	 */
	@XmlElement
	private java.lang.String CL_CODE;

	/**
	 * TC_CODE.PRIOR_RANK,
	 * 공통코드.우선_순위
	 */
	@XmlElement
	private String PRIOR_RANK;

	/**
	 * TC_CODE.CN,
	 * 공통코드.내용
	 */
	@XmlElement
	private java.lang.String CN;

	/**
	 * TC_CODE.CODE_NM,
	 * 공통코드.코드_명
	 */
	@XmlElement
	private java.lang.String CODE_NM;


	/**
	 * TC_CODE.ATRB_VAL,
	 * 공통코드.속성_값
	 */
	@XmlElement
	private java.lang.String ATRB_VAL;

	/**
	 * TC_CODE.USE_AT,
	 * 공통코드.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/**
	 * TC_CODE.DELETE_AT,
	 * 공통코드.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/**
	 * TC_CODE.CRTR_NO,
	 * 공통코드.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/**
	 * TC_CODE.CREAT_DT,
	 * 공통코드.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/**
	 * TC_CODE.UPDUSR_NO,
	 * 공통코드.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/**
	 * TC_CODE.UPDT_DT,
	 * 공통코드.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * 코드에 따른 테이블명
	 */
	@XmlElement
	private java.lang.String TABLE_NM;

	/**
	 * 코드 컬럼명
	 */
	@XmlElement
	private java.lang.String COLUM_VAL;

	/**
	 * 코드 컬럼명
	 */
	@XmlElement
	private java.lang.String ADM_CODE;

	/**
	 * 코드 컬럼명
	 */
	@XmlElement
	private java.lang.String BJCD;

	/**
	 * 코드 컬럼명
	 */
	@XmlElement
	private java.lang.String NAME;
	public java.lang.String getBJCD() {
		return BJCD;
	}

	public void setBJCD(java.lang.String bJCD) {
		BJCD = bJCD;
	}

	public java.lang.String getNAME() {
		return NAME;
	}

	public void setNAME(java.lang.String nAME) {
		NAME = nAME;
	}

	/**
	 * TC_CODE.CODE_VAL,
	 * 공통코드.코드_값 값읽기
	 * @return
	 */
	@JsonProperty(value="ADM_CODE")
	public java.lang.String getADM_CODE() {
		return this.ADM_CODE;
	}

	 /**
	 * TC_CODE.CODE_VAL,
	 * 공통코드.코드_값 값설정
	 * @param codeValue
	 */
	public void setADM_CODE(java.lang.String ADM_CODE) {
		this.ADM_CODE = ADM_CODE;
	}

	/**
	 * TC_CODE.CODE_VAL,
	 * 공통코드.코드_값 값읽기
	 * @return
	 */
	@JsonProperty(value="CODE_VAL")
	public java.lang.String getCODE_VAL() {
		return this.CODE_VAL;
	}

	 /**
	 * TC_CODE.CODE_VAL,
	 * 공통코드.코드_값 값설정
	 * @param codeValue
	 */
	public void setCODE_VAL(java.lang.String codeValue) {
		this.CODE_VAL = codeValue;
	}

	/**
	 * TC_CODE.UPPER_CODE_VAL,
	 * 공통코드.상위_코드_값 값읽기
	 * @return
	 */
	@JsonProperty(value="UPPER_CODE_VAL")
	public java.lang.String getUPPER_CODE_VAL() {
		return this.UPPER_CODE_VAL;
	}

	 /**
	 * TC_CODE.UPPER_CODE_VAL,
	 * 공통코드.상위_코드_값 값설정
	 * @param upperCodeValue
	 */
	public void setUPPER_CODE_VAL(java.lang.String upperCodeValue) {
		this.UPPER_CODE_VAL = upperCodeValue;
	}

	/**
	 * TC_CODE.CL_CODE,
	 * 공통코드.분류_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="CL_CODE")
	public java.lang.String getCL_CODE() {
		return this.CL_CODE;
	}

	 /**
	 * TC_CODE.CL_CODE,
	 * 공통코드.분류_코드 값설정
	 * @param clCode
	 */
	public void setCL_CODE(java.lang.String clCode) {
		this.CL_CODE = clCode;
	}

	/**
	 * TC_CODE.PRIOR_RANK,
	 * 공통코드.우선_순위 값읽기
	 * @return
	 */
	@JsonProperty(value="PRIOR_RANK")
	public String getPRIOR_RANK() {
		return this.PRIOR_RANK;
	}

	 /**
	 * TC_CODE.PRIOR_RANK,
	 * 공통코드.우선_순위 값설정
	 * @param priorRank
	 */
	public void setPRIOR_RANK(String priorRank) {
		this.PRIOR_RANK = priorRank;
	}

	/**
	 * TC_CODE.CN,
	 * 공통코드.내용 값읽기
	 * @return
	 */
	@JsonProperty(value="CN")
	public java.lang.String getCN() {
		return this.CN;
	}

	 /**
	 * TC_CODE.CN,
	 * 공통코드.내용 값설정
	 * @param cn
	 */
	public void setCN(java.lang.String cn) {
		this.CN = cn;
	}

	/**
	 * TC_CODE.CODE_NM,
	 * 공통코드.코드_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CODE_NM")
	public java.lang.String getCODE_NM() {
		return this.CODE_NM;
	}

	 /**
	 * TC_CODE.CODE_NM,
	 * 공통코드.코드_명 값설정
	 * @param codeNm
	 */
	public void setCODE_NM(java.lang.String codeNm) {
		this.CODE_NM = codeNm;
	}

	/**
	 * TC_CODE.USE_AT,
	 * 공통코드.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT")
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	 /**
	 * TC_CODE.USE_AT,
	 * 공통코드.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_CODE.DELETE_AT,
	 * 공통코드.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT")
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}

	 /**
	 * TC_CODE.DELETE_AT,
	 * 공통코드.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TC_CODE.CRTR_NO,
	 * 공통코드.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO")
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}

	 /**
	 * TC_CODE.CRTR_NO,
	 * 공통코드.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_CODE.CREAT_DT,
	 * 공통코드.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}

	 /**
	 * TC_CODE.CREAT_DT,
	 * 공통코드.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_CODE.UPDUSR_NO,
	 * 공통코드.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO")
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}

	 /**
	 * TC_CODE.UPDUSR_NO,
	 * 공통코드.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_CODE.UPDT_DT,
	 * 공통코드.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}

	 /**
	 * TC_CODE.UPDT_DT,
	 * 공통코드.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}
	@JsonProperty(value="ATRB_VAL")
	public java.lang.String getATRB_VAL() {
		return ATRB_VAL;
	}

	public void setATRB_VAL(java.lang.String artb_val) {
		ATRB_VAL = artb_val;
	}

	@JsonProperty(value="TABLE_NM")
	public java.lang.String getTABLE_NM() {
		return this.TABLE_NM;
	}

	public void setTABLE_NM(java.lang.String tABLE_NM) {
		this.TABLE_NM = tABLE_NM;
	}

	@JsonProperty(value="COLUM_VAL")
	public java.lang.String getCOLUM_VAL() {
		return this.COLUM_VAL;
	}

	public void setCOLUM_VAL(java.lang.String cOLUM_VAL) {
		this.COLUM_VAL = cOLUM_VAL;
	}
}
