
package kr.go.gg.gpms.pavmatrl.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 포장재료코드
 *
 * @Class Name : PavMatrlVO.java
 * @Description : PavMatrl VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-08
 * @version 1.0
 * @see
 *  	PavMatrlDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class PavMatrlVO extends BaseVO {

	public PavMatrlVO() {
		super();
	}
	
	/** 
	 * TC_PAV_MATRL.PAV_MATRL_CODE, 
	 * 포장재료코드.포장_재료_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_CODE;

	/** 
	 * TC_PAV_MATRL.PAV_MATRL_NM, 
	 * 포장재료코드.포장_재료_명
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_NM;

	/** 
	 * TC_PAV_MATRL.DELETE_AT, 
	 * 포장재료코드.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TC_PAV_MATRL.USE_AT, 
	 * 포장재료코드.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TC_PAV_MATRL.CRTR_NO, 
	 * 포장재료코드.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TC_PAV_MATRL.CREAT_DT, 
	 * 포장재료코드.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TC_PAV_MATRL.UPDUSR_NO, 
	 * 포장재료코드.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TC_PAV_MATRL.UPDT_DT, 
	 * 포장재료코드.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TC_PAV_MATRL.PAV_MATRL_CODE, 
	 * 포장재료코드.포장_재료_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_CODE") 
	public java.lang.String getPAV_MATRL_CODE() {
		return this.PAV_MATRL_CODE;
	}
 
	 /**
	 * TC_PAV_MATRL.PAV_MATRL_CODE, 
	 * 포장재료코드.포장_재료_코드 값설정
	 * @param pavMatrlCode
	 */
	public void setPAV_MATRL_CODE(java.lang.String pavMatrlCode) {
		this.PAV_MATRL_CODE = pavMatrlCode;
	}

	/**
	 * TC_PAV_MATRL.PAV_MATRL_NM, 
	 * 포장재료코드.포장_재료_명 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_NM") 
	public java.lang.String getPAV_MATRL_NM() {
		return this.PAV_MATRL_NM;
	}
 
	 /**
	 * TC_PAV_MATRL.PAV_MATRL_NM, 
	 * 포장재료코드.포장_재료_명 값설정
	 * @param pavMatrlNm
	 */
	public void setPAV_MATRL_NM(java.lang.String pavMatrlNm) {
		this.PAV_MATRL_NM = pavMatrlNm;
	}

	/**
	 * TC_PAV_MATRL.DELETE_AT, 
	 * 포장재료코드.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TC_PAV_MATRL.DELETE_AT, 
	 * 포장재료코드.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TC_PAV_MATRL.USE_AT, 
	 * 포장재료코드.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TC_PAV_MATRL.USE_AT, 
	 * 포장재료코드.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_PAV_MATRL.CRTR_NO, 
	 * 포장재료코드.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TC_PAV_MATRL.CRTR_NO, 
	 * 포장재료코드.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_PAV_MATRL.CREAT_DT, 
	 * 포장재료코드.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TC_PAV_MATRL.CREAT_DT, 
	 * 포장재료코드.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_PAV_MATRL.UPDUSR_NO, 
	 * 포장재료코드.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TC_PAV_MATRL.UPDUSR_NO, 
	 * 포장재료코드.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_PAV_MATRL.UPDT_DT, 
	 * 포장재료코드.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TC_PAV_MATRL.UPDT_DT, 
	 * 포장재료코드.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

}
