
package kr.go.gg.gpms.rpairmthduntpc.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 보수_공법_단가
 *
 * @Class Name : RpairMthdUntpcVO.java
 * @Description : RpairMthdUntpc VO class
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
public class RpairMthdUntpcVO extends BaseVO {

	public RpairMthdUntpcVO() {
		super();
	}
	
	/** 
	 * TN_RPAIR_MTHD_UNTPC.UNTPC_MANAGE_NO, 
	 * 보수_공법_단가.단가_관리_번호
	 */
	@XmlElement
	private java.lang.String UNTPC_MANAGE_NO;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.RPAIR_MTHD_CODE, 
	 * 보수_공법_단가.보수_공법_코드
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_CODE;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.CNTRWK_AMOUNT, 
	 * 보수_공법_단가.공사_금액
	 */
	@XmlElement
	private java.lang.String CNTRWK_AMOUNT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.PAINT_AMOUNT, 
	 * 보수_공법_단가.차선_도색_금액
	 */
	@XmlElement
	private java.lang.String PAINT_AMOUNT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.ETC_AMOUNT, 
	 * 보수_공법_단가.기타_금액
	 */
	@XmlElement
	private java.lang.String ETC_AMOUNT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.TOT_AMOUNT, 
	 * 보수_공법_단가.총_금액
	 */
	@XmlElement
	private java.lang.String TOT_AMOUNT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.DSGN_DT, 
	 * 보수_공법_단가.설계_일시
	 */
	@XmlElement
	private java.util.Date DSGN_DT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.USE_AT, 
	 * 보수_공법_단가.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.DELETE_AT, 
	 * 보수_공법_단가.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.CRTR_NO, 
	 * 보수_공법_단가.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.CREAT_DT, 
	 * 보수_공법_단가.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.UPDUSR_NO, 
	 * 보수_공법_단가.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_RPAIR_MTHD_UNTPC.UPDT_DT, 
	 * 보수_공법_단가.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;
	
	/**
	 * TH_RPAIR_MTHD_UNTPC.UNTPC_HIST_NO, 
	 * 보수_공법_단가_이력.단가_이력_번호
	 */
	@XmlElement
	private java.lang.String UNTPC_HIST_NO;
	
	/**
	 * 보수공법 리스트
	 */
	@XmlElement
	private List<RpairMthdUntpcVO> UNTPC_LIST;
	
	/**
	 * 투입예산
	 */
	@XmlElement
	private java.lang.String UNTPCS;
	
	/**
	 * 예측 년도
	 */
	@XmlElement
	private java.lang.String PREDCT_YEAR;
	
	/**
	 * 공사 투입 단가
	 */
	@XmlElement
	private java.lang.String CNTRWK_UNTPC;
	
	/**
	 * GPCI
	 */
	@XmlElement
	private java.lang.String GPCI;
	
	/**
	 * 부서코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;
	
	/**
	 * 시스템 접근 타입
	 */
	@XmlElement
	private java.lang.String type;
	

	/**
	 * TN_RPAIR_MTHD_UNTPC.UNTPC_MANAGE_NO, 
	 * 보수_공법_단가.단가_관리_번호 
	 * @return
	 */
	@JsonProperty(value="UNTPC_MANAGE_NO") 
	public java.lang.String getUNTPC_MANAGE_NO() {
		return this.UNTPC_MANAGE_NO;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.UNTPC_MANAGE_NO, 
	 * 보수_공법_단가.단가_관리_번호 
	 * @param untpcManageNo
	 */
	public void setUNTPC_MANAGE_NO(java.lang.String untpcManageNo) {
		this.UNTPC_MANAGE_NO = untpcManageNo;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.RPAIR_MTHD_CODE, 
	 * 보수_공법_단가.보수_공법_코드 
	 * @return
	 */
	@JsonProperty(value="RPAIR_MTHD_CODE") 
	public java.lang.String getRPAIR_MTHD_CODE() {
		return this.RPAIR_MTHD_CODE;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.RPAIR_MTHD_CODE, 
	 * 보수_공법_단가.보수_공법_코드 
	 * @param rpairMthdCode
	 */
	public void setRPAIR_MTHD_CODE(java.lang.String rpairMthdCode) {
		this.RPAIR_MTHD_CODE = rpairMthdCode;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.CNTRWK_AMOUNT, 
	 * 보수_공법_단가.공사_금액 
	 * @return
	 */
	@JsonProperty(value="CNTRWK_AMOUNT") 
	public java.lang.String getCNTRWK_AMOUNT() {
		return this.CNTRWK_AMOUNT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.CNTRWK_AMOUNT, 
	 * 보수_공법_단가.공사_금액 
	 * @param cntrwkAmount
	 */
	public void setCNTRWK_AMOUNT(java.lang.String cntrwkAmount) {
		this.CNTRWK_AMOUNT = cntrwkAmount;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.PAINT_AMOUNT, 
	 * 보수_공법_단가.차선_도색_금액 
	 * @return
	 */
	@JsonProperty(value="PAINT_AMOUNT") 
	public java.lang.String getPAINT_AMOUNT() {
		return this.PAINT_AMOUNT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.PAINT_AMOUNT, 
	 * 보수_공법_단가.차선_도색_금액 
	 * @param paintAmount
	 */
	public void setPAINT_AMOUNT(java.lang.String paintAmount) {
		this.PAINT_AMOUNT = paintAmount;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.ETC_AMOUNT, 
	 * 보수_공법_단가.기타_금액 
	 * @return
	 */
	@JsonProperty(value="ETC_AMOUNT") 
	public java.lang.String getETC_AMOUNT() {
		return this.ETC_AMOUNT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.ETC_AMOUNT, 
	 * 보수_공법_단가.기타_금액 
	 * @param etcAmount
	 */
	public void setETC_AMOUNT(java.lang.String etcAmount) {
		this.ETC_AMOUNT = etcAmount;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.TOT_AMOUNT, 
	 * 보수_공법_단가.총_금액 
	 * @return
	 */
	@JsonProperty(value="TOT_AMOUNT") 
	public java.lang.String getTOT_AMOUNT() {
		return this.TOT_AMOUNT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.TOT_AMOUNT, 
	 * 보수_공법_단가.총_금액 
	 * @param totAmount
	 */
	public void setTOT_AMOUNT(java.lang.String totAmount) {
		this.TOT_AMOUNT = totAmount;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.DSGN_DT, 
	 * 보수_공법_단가.설계_일시 
	 * @return
	 */
	@JsonProperty(value="DSGN_DT") 
	public java.util.Date getDSGN_DT() {
		return this.DSGN_DT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.DSGN_DT, 
	 * 보수_공법_단가.설계_일시 
	 * @param dsgnDt
	 */
	public void setDSGN_DT(java.util.Date dsgnDt) {
		this.DSGN_DT = dsgnDt;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.USE_AT, 
	 * 보수_공법_단가.사용_여부 
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.USE_AT, 
	 * 보수_공법_단가.사용_여부 
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.DELETE_AT, 
	 * 보수_공법_단가.삭제_여부 
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.DELETE_AT, 
	 * 보수_공법_단가.삭제_여부 
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.CRTR_NO, 
	 * 보수_공법_단가.생성자_번호 
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.CRTR_NO, 
	 * 보수_공법_단가.생성자_번호 
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.CREAT_DT, 
	 * 보수_공법_단가.생성_일시 
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.CREAT_DT, 
	 * 보수_공법_단가.생성_일시 
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.UPDUSR_NO, 
	 * 보수_공법_단가.수정자_번호 
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.UPDUSR_NO, 
	 * 보수_공법_단가.수정자_번호 
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_RPAIR_MTHD_UNTPC.UPDT_DT, 
	 * 보수_공법_단가.수정_일시 
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_RPAIR_MTHD_UNTPC.UPDT_DT, 
	 * 보수_공법_단가.수정_일시 
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="UNTPC_HIST_NO") 
	public java.lang.String getUNTPC_HIST_NO() {
		return UNTPC_HIST_NO;
	}

	public void setUNTPC_HIST_NO(java.lang.String uNTPC_HIST_NO) {
		UNTPC_HIST_NO = uNTPC_HIST_NO;
	}

	@JsonProperty(value="UNTPC_LIST") 
	public List<RpairMthdUntpcVO> getUNTPC_LIST() {
		return UNTPC_LIST;
	}

	public void setUNTPC_LIST(List<RpairMthdUntpcVO> uNTPC_LIST) {
		UNTPC_LIST = uNTPC_LIST;
	}

	@JsonProperty(value="UNTPCS") 
	public java.lang.String getUNTPCS() {
		return UNTPCS;
	}

	public void setUNTPCS(java.lang.String uNTPCS) {
		UNTPCS = uNTPCS;
	}

	@JsonProperty(value="PREDCT_YEAR") 
	public java.lang.String getPREDCT_YEAR() {
		return PREDCT_YEAR;
	}

	public void setPREDCT_YEAR(java.lang.String pREDCT_YEAR) {
		PREDCT_YEAR = pREDCT_YEAR;
	}

	@JsonProperty(value="CNTRWK_UNTPC") 
	public java.lang.String getCNTRWK_UNTPC() {
		return CNTRWK_UNTPC;
	}

	public void setCNTRWK_UNTPC(java.lang.String cNTRWK_UNTPC) {
		CNTRWK_UNTPC = cNTRWK_UNTPC;
	}

	@JsonProperty(value="GPCI") 
	public java.lang.String getGPCI() {
		return GPCI;
	}

	public void setGPCI(java.lang.String gPCI) {
		GPCI = gPCI;
	}

	@JsonProperty(value="type") 
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	@JsonProperty(value="DEPT_CODE")
	public java.lang.String getDEPT_CODE() {
		return DEPT_CODE;
	}

	public void setDEPT_CODE(java.lang.String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}

	
	
}
