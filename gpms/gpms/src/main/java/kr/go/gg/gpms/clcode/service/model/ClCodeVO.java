
package kr.go.gg.gpms.clcode.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 분류코드
 *
 * @Class Name : ClCodeVO.java
 * @Description : ClCode VO class
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
public class ClCodeVO extends BaseVO {

	public ClCodeVO() {
		super();
	}
	
	/** 
	 * TC_CL_CODE.CL_CODE, 
	 * 분류코드.분류_코드
	 */
	@XmlElement
	private java.lang.String CL_CODE;

	/** 
	 * TC_CL_CODE.PRIOR_RANK, 
	 * 분류코드.우선_순위
	 */
	@XmlElement
	private String PRIOR_RANK;

	/** 
	 * TC_CL_CODE.CL_CODE_NM, 
	 * 분류코드.분류_코드_명
	 */
	@XmlElement
	private java.lang.String CL_CODE_NM;

	/** 
	 * TC_CL_CODE.CN, 
	 * 분류코드.내용
	 */
	@XmlElement
	private java.lang.String CN;

	/** 
	 * TC_CL_CODE.USE_AT, 
	 * 분류코드.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TC_CL_CODE.DELETE_AT, 
	 * 분류코드.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TC_CL_CODE.CRTR_NO, 
	 * 분류코드.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TC_CL_CODE.CREAT_DT, 
	 * 분류코드.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TC_CL_CODE.UPDUSR_NO, 
	 * 분류코드.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TC_CL_CODE.UPDT_DT, 
	 * 분류코드.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TC_CL_CODE.CL_CODE, 
	 * 분류코드.분류_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="CL_CODE") 
	public java.lang.String getCL_CODE() {
		return this.CL_CODE;
	}
 
	 /**
	 * TC_CL_CODE.CL_CODE, 
	 * 분류코드.분류_코드 값설정
	 * @param clCode
	 */
	public void setCL_CODE(java.lang.String clCode) {
		this.CL_CODE = clCode;
	}

	/**
	 * TC_CL_CODE.PRIOR_RANK, 
	 * 분류코드.우선_순위 값읽기
	 * @return
	 */
	@JsonProperty(value="PRIOR_RANK") 
	public String getPRIOR_RANK() {
		return this.PRIOR_RANK;
	}
 
	 /**
	 * TC_CL_CODE.PRIOR_RANK, 
	 * 분류코드.우선_순위 값설정
	 * @param priorRank
	 */
	public void setPRIOR_RANK(String priorRank) {
		this.PRIOR_RANK = priorRank;
	}

	/**
	 * TC_CL_CODE.CL_CODE_NM, 
	 * 분류코드.분류_코드_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CL_CODE_NM") 
	public java.lang.String getCL_CODE_NM() {
		return this.CL_CODE_NM;
	}
 
	 /**
	 * TC_CL_CODE.CL_CODE_NM, 
	 * 분류코드.분류_코드_명 값설정
	 * @param clCodeNm
	 */
	public void setCL_CODE_NM(java.lang.String clCodeNm) {
		this.CL_CODE_NM = clCodeNm;
	}

	/**
	 * TC_CL_CODE.CN, 
	 * 분류코드.내용 값읽기
	 * @return
	 */
	@JsonProperty(value="CN") 
	public java.lang.String getCN() {
		return this.CN;
	}
 
	 /**
	 * TC_CL_CODE.CN, 
	 * 분류코드.내용 값설정
	 * @param cn
	 */
	public void setCN(java.lang.String cn) {
		this.CN = cn;
	}

	/**
	 * TC_CL_CODE.USE_AT, 
	 * 분류코드.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TC_CL_CODE.USE_AT, 
	 * 분류코드.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_CL_CODE.DELETE_AT, 
	 * 분류코드.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TC_CL_CODE.DELETE_AT, 
	 * 분류코드.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TC_CL_CODE.CRTR_NO, 
	 * 분류코드.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TC_CL_CODE.CRTR_NO, 
	 * 분류코드.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_CL_CODE.CREAT_DT, 
	 * 분류코드.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TC_CL_CODE.CREAT_DT, 
	 * 분류코드.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_CL_CODE.UPDUSR_NO, 
	 * 분류코드.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TC_CL_CODE.UPDUSR_NO, 
	 * 분류코드.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_CL_CODE.UPDT_DT, 
	 * 분류코드.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		
		return this.UPDT_DT;
	}
 
	 /**
	 * TC_CL_CODE.UPDT_DT, 
	 * 분류코드.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
