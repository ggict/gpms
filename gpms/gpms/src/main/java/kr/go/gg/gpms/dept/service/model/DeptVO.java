
package kr.go.gg.gpms.dept.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 부서_코드
 *
 * @Class Name : DeptVO.java
 * @Description : Dept VO class
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
public class DeptVO extends BaseVO {

	public DeptVO() {
		super();
	}
	
	// 시스템 Flag
    @XmlElement
    private String sFlag;
    
    @JsonProperty(value="sFlag")
    public String getsFlag() {
        return sFlag;
    }

    public void setsFlag(String sFlag) {
        this.sFlag = sFlag;
    }

	/**
	 * TC_DEPT.DEPT_CODE,
	 * 부서_코드.부서_코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/**
	 * TC_DEPT.DEPT_NM,
	 * 부서_코드.부서_명
	 */
	@XmlElement
	private java.lang.String DEPT_NM;

	/**
	 * TC_DEPT.LOWEST_DEPT_NM,
	 * 부서_코드.최하위_부서_명
	 */
	@XmlElement
	private java.lang.String LOWEST_DEPT_NM;

	/**
	 * TC_DEPT.ODR,
	 * 부서_코드.차수
	 */
	@XmlElement
	private java.lang.String ODR;

	/**
	 * TC_DEPT.ORD,
	 * 부서_코드.서열
	 */
	@XmlElement
	private java.lang.String ORD;

	/**
	 * TC_DEPT.SEHIGH_DEPT_CODE,
	 * 부서_코드.차상위_부서_코드
	 */
	@XmlElement
	private java.lang.String SEHIGH_DEPT_CODE;

	/**
	 * TC_DEPT.TOP_DEPT_CODE,
	 * 부서_코드.최상위_부서_코드
	 */
	@XmlElement
	private java.lang.String TOP_DEPT_CODE;

	/**
	 * TC_DEPT.PSITN_DEPT_ODR,
	 * 부서_코드.소속_부서_차수
	 */
	@XmlElement
	private java.lang.String PSITN_DEPT_ODR;

	/**
	 * TC_DEPT.RPRSNT_DEPT_CODE,
	 * 부서_코드.대표_부서_코드
	 */
	@XmlElement
	private java.lang.String RPRSNT_DEPT_CODE;

	/**
	 * TC_DEPT.ISTDR_CLSF_NM,
	 * 부서_코드.기관장_직급_명
	 */
	@XmlElement
	private java.lang.String ISTDR_CLSF_NM;

	/**
	 * TC_DEPT.ADSTRD_CODE,
	 * 부서_코드.행정동_코드
	 */
	@XmlElement
	private java.lang.String ADSTRD_CODE;

	/**
	 * TC_DEPT.ZIP,
	 * 부서_코드.우편번호
	 */
	@XmlElement
	private java.lang.String ZIP;

	/**
	 * TC_DEPT.FAX_NO,
	 * 부서_코드.팩스_번호
	 */
	@XmlElement
	private java.lang.String FAX_NO;

	/**
	 * TC_DEPT.CREAT_DE,
	 * 부서_코드.생성_일자
	 */
	@XmlElement
	private java.lang.String CREAT_DE;

	/**
	 * TC_DEPT.ABL_DE,
	 * 부서_코드.폐지_일자
	 */
	@XmlElement
	private java.lang.String ABL_DE;

	/**
	 * TC_DEPT.LAST_OPERT_DE,
	 * 부서_코드.최종_작업_일자
	 */
	@XmlElement
	private java.lang.String LAST_OPERT_DE;

	/**
	 * TC_DEPT.INSTT_TY,
	 * 부서_코드.기관_유형
	 */
	@XmlElement
	private java.lang.String INSTT_TY;

	/**
	 * TC_DEPT.PREV_INSTT,
	 * 부서_코드.이전_기관
	 */
	@XmlElement
	private java.lang.String PREV_INSTT;

	/**
	 * TC_DEPT.EDC_TRAING_INSTT_AT,
	 * 부서_코드.교육_훈련_기관_여부
	 */
	@XmlElement
	private java.lang.String EDC_TRAING_INSTT_AT;

	/**
	 * TC_DEPT.USE_AT,
	 * 부서_코드.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/**
	 * TC_DEPT.DELETE_AT,
	 * 부서_코드.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/**
	 * TC_DEPT.CRTR_NO,
	 * 부서_코드.생성자_번호
	 */
	@XmlElement
	private java.math.BigDecimal CRTR_NO;

	/**
	 * TC_DEPT.CREAT_DT,
	 * 부서_코드.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/**
	 * TC_DEPT.UPDUSR_NO,
	 * 부서_코드.수정자_번호
	 */
	@XmlElement
	private java.math.BigDecimal UPDUSR_NO;

	/**
	 * TC_DEPT.UPDT_DT,
	 * 부서_코드.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TC_DEPT.TEL_NO,
	 * 부서_코드.전화_번호
	 */
	@XmlElement
	private java.lang.String TEL_NO;

	@XmlElement
	private java.lang.String UPPER_SEHIGH_DEPT_CODE;

	/**
	 * 민자도로사업자 구분을 위한 flag
	 */
	@XmlElement
	private java.lang.String flag;




	/**
	 * 민자도로사업자 구분을 위한 flag
	 * @return
	 */
	@JsonProperty(value="flag")
	public java.lang.String getFlag() {
		return flag;
	}
	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}


	/**
	 * TC_DEPT.DEPT_CODE,
	 * 부서_코드.부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE")
		public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}

	 /**
	 * TC_DEPT.DEPT_CODE,
	 * 부서_코드.부서_코드 값설정
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
	}

	/**
	 * TC_DEPT.DEPT_NM,
	 * 부서_코드.부서_명 값읽기
	 * @return
	 */
	@JsonProperty(value="DEPT_NM")
		public java.lang.String getDEPT_NM() {
		return this.DEPT_NM;
	}

	 /**
	 * TC_DEPT.DEPT_NM,
	 * 부서_코드.부서_명 값설정
	 * @param deptNm
	 */
	public void setDEPT_NM(java.lang.String deptNm) {
		this.DEPT_NM = deptNm;
	}

	/**
	 * TC_DEPT.LOWEST_DEPT_NM,
	 * 부서_코드.최하위_부서_명 값읽기
	 * @return
	 */
	@JsonProperty(value="LOWEST_DEPT_NM")
		public java.lang.String getLOWEST_DEPT_NM() {
		return this.LOWEST_DEPT_NM;
	}

	 /**
	 * TC_DEPT.LOWEST_DEPT_NM,
	 * 부서_코드.최하위_부서_명 값설정
	 * @param lowestDeptNm
	 */
	public void setLOWEST_DEPT_NM(java.lang.String lowestDeptNm) {
		this.LOWEST_DEPT_NM = lowestDeptNm;
	}

	/**
	 * TC_DEPT.ODR,
	 * 부서_코드.차수 값읽기
	 * @return
	 */
	@JsonProperty(value="ODR")
		public java.lang.String getODR() {
		return this.ODR;
	}

	 /**
	 * TC_DEPT.ODR,
	 * 부서_코드.차수 값설정
	 * @param odr
	 */
	public void setODR(java.lang.String odr) {
		this.ODR = odr;
	}

	/**
	 * TC_DEPT.ORD,
	 * 부서_코드.서열 값읽기
	 * @return
	 */
	@JsonProperty(value="ORD")
		public java.lang.String getORD() {
		return this.ORD;
	}

	 /**
	 * TC_DEPT.ORD,
	 * 부서_코드.서열 값설정
	 * @param ord
	 */
	public void setORD(java.lang.String ord) {
		this.ORD = ord;
	}

	/**
	 * TC_DEPT.SEHIGH_DEPT_CODE,
	 * 부서_코드.차상위_부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="SEHIGH_DEPT_CODE")
		public java.lang.String getSEHIGH_DEPT_CODE() {
		return this.SEHIGH_DEPT_CODE;
	}

	 /**
	 * TC_DEPT.SEHIGH_DEPT_CODE,
	 * 부서_코드.차상위_부서_코드 값설정
	 * @param sehighDeptCode
	 */
	public void setSEHIGH_DEPT_CODE(java.lang.String sehighDeptCode) {
		this.SEHIGH_DEPT_CODE = sehighDeptCode;
	}

	/**
	 * TC_DEPT.TOP_DEPT_CODE,
	 * 부서_코드.최상위_부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="TOP_DEPT_CODE")
		public java.lang.String getTOP_DEPT_CODE() {
		return this.TOP_DEPT_CODE;
	}

	 /**
	 * TC_DEPT.TOP_DEPT_CODE,
	 * 부서_코드.최상위_부서_코드 값설정
	 * @param topDeptCode
	 */
	public void setTOP_DEPT_CODE(java.lang.String topDeptCode) {
		this.TOP_DEPT_CODE = topDeptCode;
	}

	/**
	 * TC_DEPT.PSITN_DEPT_ODR,
	 * 부서_코드.소속_부서_차수 값읽기
	 * @return
	 */
	@JsonProperty(value="PSITN_DEPT_ODR")
		public java.lang.String getPSITN_DEPT_ODR() {
		return this.PSITN_DEPT_ODR;
	}

	 /**
	 * TC_DEPT.PSITN_DEPT_ODR,
	 * 부서_코드.소속_부서_차수 값설정
	 * @param psitnDeptOdr
	 */
	public void setPSITN_DEPT_ODR(java.lang.String psitnDeptOdr) {
		this.PSITN_DEPT_ODR = psitnDeptOdr;
	}

	/**
	 * TC_DEPT.RPRSNT_DEPT_CODE,
	 * 부서_코드.대표_부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="RPRSNT_DEPT_CODE")
		public java.lang.String getRPRSNT_DEPT_CODE() {
		return this.RPRSNT_DEPT_CODE;
	}

	 /**
	 * TC_DEPT.RPRSNT_DEPT_CODE,
	 * 부서_코드.대표_부서_코드 값설정
	 * @param rprsntDeptCode
	 */
	public void setRPRSNT_DEPT_CODE(java.lang.String rprsntDeptCode) {
		this.RPRSNT_DEPT_CODE = rprsntDeptCode;
	}

	/**
	 * TC_DEPT.ISTDR_CLSF_NM,
	 * 부서_코드.기관장_직급_명 값읽기
	 * @return
	 */
	@JsonProperty(value="ISTDR_CLSF_NM")
		public java.lang.String getISTDR_CLSF_NM() {
		return this.ISTDR_CLSF_NM;
	}

	 /**
	 * TC_DEPT.ISTDR_CLSF_NM,
	 * 부서_코드.기관장_직급_명 값설정
	 * @param istdrClsfNm
	 */
	public void setISTDR_CLSF_NM(java.lang.String istdrClsfNm) {
		this.ISTDR_CLSF_NM = istdrClsfNm;
	}

	/**
	 * TC_DEPT.ADSTRD_CODE,
	 * 부서_코드.행정동_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ADSTRD_CODE")
		public java.lang.String getADSTRD_CODE() {
		return this.ADSTRD_CODE;
	}

	 /**
	 * TC_DEPT.ADSTRD_CODE,
	 * 부서_코드.행정동_코드 값설정
	 * @param adstrdCode
	 */
	public void setADSTRD_CODE(java.lang.String adstrdCode) {
		this.ADSTRD_CODE = adstrdCode;
	}

	/**
	 * TC_DEPT.ZIP,
	 * 부서_코드.우편번호 값읽기
	 * @return
	 */
	@JsonProperty(value="ZIP")
		public java.lang.String getZIP() {
		return this.ZIP;
	}

	 /**
	 * TC_DEPT.ZIP,
	 * 부서_코드.우편번호 값설정
	 * @param zip
	 */
	public void setZIP(java.lang.String zip) {
		this.ZIP = zip;
	}

	/**
	 * TC_DEPT.FAX_NO,
	 * 부서_코드.팩스_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FAX_NO")
		public java.lang.String getFAX_NO() {
		return this.FAX_NO;
	}

	 /**
	 * TC_DEPT.FAX_NO,
	 * 부서_코드.팩스_번호 값설정
	 * @param faxNo
	 */
	public void setFAX_NO(java.lang.String faxNo) {
		this.FAX_NO = faxNo;
	}

	/**
	 * TC_DEPT.CREAT_DE,
	 * 부서_코드.생성_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DE")
		public java.lang.String getCREAT_DE() {
		return this.CREAT_DE;
	}

	 /**
	 * TC_DEPT.CREAT_DE,
	 * 부서_코드.생성_일자 값설정
	 * @param creatDe
	 */
	public void setCREAT_DE(java.lang.String creatDe) {
		this.CREAT_DE = creatDe;
	}

	/**
	 * TC_DEPT.ABL_DE,
	 * 부서_코드.폐지_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="ABL_DE")
		public java.lang.String getABL_DE() {
		return this.ABL_DE;
	}

	 /**
	 * TC_DEPT.ABL_DE,
	 * 부서_코드.폐지_일자 값설정
	 * @param ablDe
	 */
	public void setABL_DE(java.lang.String ablDe) {
		this.ABL_DE = ablDe;
	}

	/**
	 * TC_DEPT.LAST_OPERT_DE,
	 * 부서_코드.최종_작업_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="LAST_OPERT_DE")
		public java.lang.String getLAST_OPERT_DE() {
		return this.LAST_OPERT_DE;
	}

	 /**
	 * TC_DEPT.LAST_OPERT_DE,
	 * 부서_코드.최종_작업_일자 값설정
	 * @param lastOpertDe
	 */
	public void setLAST_OPERT_DE(java.lang.String lastOpertDe) {
		this.LAST_OPERT_DE = lastOpertDe;
	}

	/**
	 * TC_DEPT.INSTT_TY,
	 * 부서_코드.기관_유형 값읽기
	 * @return
	 */
	@JsonProperty(value="INSTT_TY")
		public java.lang.String getINSTT_TY() {
		return this.INSTT_TY;
	}

	 /**
	 * TC_DEPT.INSTT_TY,
	 * 부서_코드.기관_유형 값설정
	 * @param insttTy
	 */
	public void setINSTT_TY(java.lang.String insttTy) {
		this.INSTT_TY = insttTy;
	}

	/**
	 * TC_DEPT.PREV_INSTT,
	 * 부서_코드.이전_기관 값읽기
	 * @return
	 */
	@JsonProperty(value="PREV_INSTT")
		public java.lang.String getPREV_INSTT() {
		return this.PREV_INSTT;
	}

	 /**
	 * TC_DEPT.PREV_INSTT,
	 * 부서_코드.이전_기관 값설정
	 * @param prevInstt
	 */
	public void setPREV_INSTT(java.lang.String prevInstt) {
		this.PREV_INSTT = prevInstt;
	}

	/**
	 * TC_DEPT.EDC_TRAING_INSTT_AT,
	 * 부서_코드.교육_훈련_기관_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="EDC_TRAING_INSTT_AT")
		public java.lang.String getEDC_TRAING_INSTT_AT() {
		return this.EDC_TRAING_INSTT_AT;
	}

	 /**
	 * TC_DEPT.EDC_TRAING_INSTT_AT,
	 * 부서_코드.교육_훈련_기관_여부 값설정
	 * @param edcTraingInsttAt
	 */
	public void setEDC_TRAING_INSTT_AT(java.lang.String edcTraingInsttAt) {
		this.EDC_TRAING_INSTT_AT = edcTraingInsttAt;
	}

	/**
	 * TC_DEPT.USE_AT,
	 * 부서_코드.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT")
		public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	 /**
	 * TC_DEPT.USE_AT,
	 * 부서_코드.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_DEPT.DELETE_AT,
	 * 부서_코드.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT")
		public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}

	 /**
	 * TC_DEPT.DELETE_AT,
	 * 부서_코드.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TC_DEPT.CRTR_NO,
	 * 부서_코드.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO")
		public java.math.BigDecimal getCRTR_NO() {
		return this.CRTR_NO;
	}

	 /**
	 * TC_DEPT.CRTR_NO,
	 * 부서_코드.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.math.BigDecimal crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_DEPT.CREAT_DT,
	 * 부서_코드.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}

	 /**
	 * TC_DEPT.CREAT_DT,
	 * 부서_코드.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_DEPT.UPDUSR_NO,
	 * 부서_코드.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO")
		public java.math.BigDecimal getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}

	 /**
	 * TC_DEPT.UPDUSR_NO,
	 * 부서_코드.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.math.BigDecimal updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_DEPT.UPDT_DT,
	 * 부서_코드.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
		public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}

	 /**
	 * TC_DEPT.UPDT_DT,
	 * 부서_코드.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TC_DEPT.TEL_NO,
	 * 부서_코드.전화_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="TEL_NO")
		public java.lang.String getTEL_NO() {
		return this.TEL_NO;
	}

	 /**
	 * TC_DEPT.TEL_NO,
	 * 부서_코드.전화_번호 값설정
	 * @param telNo
	 */
	public void setTEL_NO(java.lang.String telNo) {
		this.TEL_NO = telNo;
	}

	@JsonProperty(value="UPPER_SEHIGH_DEPT_CODE")
	public java.lang.String getUPPER_SEHIGH_DEPT_CODE() {
		return UPPER_SEHIGH_DEPT_CODE;
	}

	public void setUPPER_SEHIGH_DEPT_CODE(java.lang.String upperSehighDeptCode) {
		UPPER_SEHIGH_DEPT_CODE = upperSehighDeptCode;
	}


}
