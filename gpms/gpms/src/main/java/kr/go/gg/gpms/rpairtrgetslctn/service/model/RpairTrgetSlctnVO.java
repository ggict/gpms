
package kr.go.gg.gpms.rpairtrgetslctn.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 보수_대상_선정
 *
 * @Class Name : RpairTrgetSlctnVO.java
 * @Description : RpairTrgetSlctn VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  	RpairTrgetSlctnDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairTrgetSlctnVO extends BaseVO {

	public RpairTrgetSlctnVO() {
		super();
	}
	
	/** 
	 * TN_RPAIR_TRGET_SLCTN.TRGET_SLCTN_NO, 
	 * 보수_대상_선정.대상_선정_번호
	 */
	@XmlElement
	private java.lang.String TRGET_SLCTN_NO;
	/** 
	 * RN, 
	 * 
	 */
	@XmlElement
	private java.lang.String RN;
	

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_OPERT_NM, 
	 * 보수_대상_선정.선정_작업_명
	 */
	@XmlElement
	private java.lang.String SLCTN_OPERT_NM;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_MTH, 
	 * 보수_대상_선정.선정_방법
	 */
	@XmlElement
	private java.lang.String SLCTN_MTH;
	
	@XmlElement
	private java.lang.String SLCTN_MTH_NM;
	

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_STTUS, 
	 * 보수_대상_선정.선정_상태
	 */
	@XmlElement
	private java.lang.String SLCTN_STTUS;
	
	@XmlElement
	private java.lang.String SLCTN_STTUS_NM;
	

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET, 
	 * 보수_대상_선정.선정_예산
	 */
	@XmlElement
	private java.lang.Long SLCTN_BUDGET;
	
	@XmlElement
	private java.lang.String MMNF_RATE;	
	@XmlElement
	private java.lang.String ST_RATE;
	@XmlElement
	private java.lang.String PM_RATE;
	
	
	
	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_DT, 
	 * 보수_대상_선정.선정_일시
	 */
	@XmlElement
	private java.util.Date SLCTN_DT;
	
	@XmlElement
	private String SLCTN_MONTH;
	

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DELETE_AT, 
	 * 보수_대상_선정.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.USE_AT, 
	 * 보수_대상_선정.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.CRTR_NO, 
	 * 보수_대상_선정.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.CREAT_DT, 
	 * 보수_대상_선정.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.UPDUSR_NO, 
	 * 보수_대상_선정.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.UPDT_DT, 
	 * 보수_대상_선정.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	
	/** 
	 * TN_RPAIR_TRGET_SLCTN.DEPT_CODE, 
	 * 보수_대상_선정.부서_코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_1_RATE, 
	 * 보수_대상_선정.결정_방식_1_비율
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_1_RATE;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_2_RATE, 
	 * 보수_대상_선정.결정_방식_2_비율
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_2_RATE;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_3_RATE, 
	 * 보수_대상_선정.결정_방식_3_비율
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_3_RATE;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_4_RATE, 
	 * 보수_대상_선정.결정_방식_4_비율
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_4_RATE;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_5_RATE, 
	 * 보수_대상_선정.결정_방식_5_비율
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_5_RATE;
	
	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_1_BUDGET, 
	 * 보수_대상_선정.결정_방식_1_예산
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_1_BUDGET;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_2_BUDGET, 
	 * 보수_대상_선정.결정_방식_2_예산
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_2_BUDGET;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_3_BUDGET, 
	 * 보수_대상_선정.결정_방식_3_예산
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_3_BUDGET;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_4_BUDGET, 
	 * 보수_대상_선정.결정_방식_4_예산
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_4_BUDGET;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_5_BUDGET, 
	 * 보수_대상_선정.결정_방식_5_예산
	 */
	@XmlElement
	private java.lang.Long  DECSN_MTHD_5_BUDGET;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET_1, 
	 * 보수_대상_선정.결정_방식_1_선정예산
	 */
	@XmlElement
	private java.lang.Long  SLCTN_BUDGET_1;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET_2, 
	 * 보수_대상_선정.결정_방식_2_선정예산
	 */
	@XmlElement
	private java.lang.Long  SLCTN_BUDGET_2;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET_3, 
	 * 보수_대상_선정.결정_방식_3_선정예산
	 */
	@XmlElement
	private java.lang.Long  SLCTN_BUDGET_3;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET_4, 
	 * 보수_대상_선정.결정_방식_4_선정예산
	 */
	@XmlElement
	private java.lang.Long  SLCTN_BUDGET_4;

	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET_5, 
	 * 보수_대상_선정.결정_방식_5_선정예산
	 */
	@XmlElement
	private java.lang.Long  SLCTN_BUDGET_5;
	/** 
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_PURPS, 
	 * 보수_대상_선정.산정_목적
	 */
	@XmlElement
	private java.lang.String SLCTN_PURPS;
	
	
	@XmlElement
	private java.lang.String ROAD_GRAD;
	
	
	@XmlElement
	private java.lang.String ROUTE_CODE;
	@XmlElement
	private java.lang.String ROAD_NO;
	
	@XmlElement
	private java.lang.String ROAD_NO_VAL;
	
	
	@XmlElement
	private java.lang.String ADM_CODE;
	
	
	/**
	 * TN_RPAIR_TRGET_SLCTN.TRGET_SLCTN_NO, 
	 * 보수_대상_선정.대상_선정_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="TRGET_SLCTN_NO") 
	public java.lang.String getTRGET_SLCTN_NO() {
		return this.TRGET_SLCTN_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.TRGET_SLCTN_NO, 
	 * 보수_대상_선정.대상_선정_번호 값설정
	 * @param trgetSlctnNo
	 */
	public void setTRGET_SLCTN_NO(java.lang.String trgetSlctnNo) {
		this.TRGET_SLCTN_NO = trgetSlctnNo;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_OPERT_NM, 
	 * 보수_대상_선정.선정_작업_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_OPERT_NM") 
	public java.lang.String getSLCTN_OPERT_NM() {
		return this.SLCTN_OPERT_NM;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_OPERT_NM, 
	 * 보수_대상_선정.선정_작업_명 값설정
	 * @param slctnOpertNm
	 */
	public void setSLCTN_OPERT_NM(java.lang.String slctnOpertNm) {
		this.SLCTN_OPERT_NM = slctnOpertNm;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_MTH, 
	 * 보수_대상_선정.선정_방법 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_MTH") 
	public java.lang.String getSLCTN_MTH() {
		return this.SLCTN_MTH;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_MTH, 
	 * 보수_대상_선정.선정_방법 값설정
	 * @param slctnMth
	 */
	public void setSLCTN_MTH(java.lang.String slctnMth) {
		this.SLCTN_MTH = slctnMth;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_STTUS, 
	 * 보수_대상_선정.선정_상태 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_STTUS") 
	public java.lang.String getSLCTN_STTUS() {
		return this.SLCTN_STTUS;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_STTUS, 
	 * 보수_대상_선정.선정_상태 값설정
	 * @param slctnSttus
	 */
	public void setSLCTN_STTUS(java.lang.String slctnSttus) {
		this.SLCTN_STTUS = slctnSttus;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET, 
	 * 보수_대상_선정.선정_예산 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_BUDGET") 
	public java.lang.Long getSLCTN_BUDGET() {
		return this.SLCTN_BUDGET;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_BUDGET, 
	 * 보수_대상_선정.선정_예산 값설정
	 * @param slctnBudget
	 */
	public void setSLCTN_BUDGET(java.lang.Long slctnBudget) {
		this.SLCTN_BUDGET = slctnBudget;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_DT, 
	 * 보수_대상_선정.선정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getSLCTN_DT() {
		return this.SLCTN_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_DT, 
	 * 보수_대상_선정.선정_일시 값설정
	 * @param slctnDt
	 */
	public void setSLCTN_DT(java.util.Date slctnDt) {
		this.SLCTN_DT = slctnDt;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DELETE_AT, 
	 * 보수_대상_선정.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DELETE_AT, 
	 * 보수_대상_선정.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.USE_AT, 
	 * 보수_대상_선정.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.USE_AT, 
	 * 보수_대상_선정.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.CRTR_NO, 
	 * 보수_대상_선정.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.CRTR_NO, 
	 * 보수_대상_선정.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.CREAT_DT, 
	 * 보수_대상_선정.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.CREAT_DT, 
	 * 보수_대상_선정.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.UPDUSR_NO, 
	 * 보수_대상_선정.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.UPDUSR_NO, 
	 * 보수_대상_선정.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.UPDT_DT, 
	 * 보수_대상_선정.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.UPDT_DT, 
	 * 보수_대상_선정.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * @return the sLCTN_MONTH
	 */
	@JsonProperty(value="SLCTN_MONTH") 
	public String getSLCTN_MONTH() {
		return SLCTN_MONTH;
	}

	/**
	 * @param sLCTN_MONTH the sLCTN_MONTH to set
	 */
	public void setSLCTN_MONTH(String sLCTN_MONTH) {
		SLCTN_MONTH = sLCTN_MONTH;
	}
	
	@JsonProperty(value="SLCTN_MTH_NM") 
	public java.lang.String getSLCTN_MTH_NM() {
		return SLCTN_MTH_NM;
	}

	public void setSLCTN_MTH_NM(java.lang.String sLCTN_MTH_NM) {
		SLCTN_MTH_NM = sLCTN_MTH_NM;
	}
	@JsonProperty(value="SLCTN_STTUS_NM") 
	public java.lang.String getSLCTN_STTUS_NM() {
		return SLCTN_STTUS_NM;
	}

	public void setSLCTN_STTUS_NM(java.lang.String sLCTN_STTUS_NM) {
		SLCTN_STTUS_NM = sLCTN_STTUS_NM;
	}
	@JsonProperty(value="MMNF_RATE") 
	public java.lang.String getMMNF_RATE() {
		return MMNF_RATE;
	}

	public void setMMNF_RATE(java.lang.String mMNF_RATE) {
		MMNF_RATE = mMNF_RATE;
	}
	@JsonProperty(value="ST_RATE") 
	public java.lang.String getST_RATE() {
		return ST_RATE;
	}

	public void setST_RATE(java.lang.String sT_RATE) {
		ST_RATE = sT_RATE;
	}
	@JsonProperty(value="PM_RATE") 
	public java.lang.String getPM_RATE() {
		return PM_RATE;
	}

	public void setPM_RATE(java.lang.String pM_RATE) {
		PM_RATE = pM_RATE;
	}
	
	
	/**
	 * TN_RPAIR_TRGET_SLCTN.DEPT_CODE, 
	 * 보수_대상_선정.부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE") 
	public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DEPT_CODE, 
	 * 보수_대상_선정.부서_코드 값설정
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_1_RATE, 
	 * 보수_대상_선정.결정_방식_1_비율 값읽기
	 * @return
	 */
	@JsonProperty(value="DECSN_MTHD_1_RATE") 
	public java.lang.Long getDECSN_MTHD_1_RATE() {
		return this.DECSN_MTHD_1_RATE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_1_RATE, 
	 * 보수_대상_선정.결정_방식_1_비율 값설정
	 * @param decsnMthd1Rate
	 */
	public void setDECSN_MTHD_1_RATE(java.lang.Long decsnMthd1Rate) {
		this.DECSN_MTHD_1_RATE = decsnMthd1Rate;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_2_RATE, 
	 * 보수_대상_선정.결정_방식_2_비율 값읽기
	 * @return
	 */
	@JsonProperty(value="DECSN_MTHD_2_RATE") 
	public java.lang.Long getDECSN_MTHD_2_RATE() {
		return this.DECSN_MTHD_2_RATE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_2_RATE, 
	 * 보수_대상_선정.결정_방식_2_비율 값설정
	 * @param decsnMthd2Rate
	 */
	public void setDECSN_MTHD_2_RATE(java.lang.Long decsnMthd2Rate) {
		this.DECSN_MTHD_2_RATE = decsnMthd2Rate;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_3_RATE, 
	 * 보수_대상_선정.결정_방식_3_비율 값읽기
	 * @return
	 */
	@JsonProperty(value="DECSN_MTHD_3_RATE") 
	public java.lang.Long getDECSN_MTHD_3_RATE() {
		return this.DECSN_MTHD_3_RATE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_3_RATE, 
	 * 보수_대상_선정.결정_방식_3_비율 값설정
	 * @param decsnMthd3Rate
	 */
	public void setDECSN_MTHD_3_RATE(java.lang.Long decsnMthd3Rate) {
		this.DECSN_MTHD_3_RATE = decsnMthd3Rate;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_4_RATE, 
	 * 보수_대상_선정.결정_방식_4_비율 값읽기
	 * @return
	 */
	@JsonProperty(value="DECSN_MTHD_4_RATE") 
	public java.lang.Long getDECSN_MTHD_4_RATE() {
		return this.DECSN_MTHD_4_RATE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_4_RATE, 
	 * 보수_대상_선정.결정_방식_4_비율 값설정
	 * @param decsnMthd4Rate
	 */
	public void setDECSN_MTHD_4_RATE(java.lang.Long decsnMthd4Rate) {
		this.DECSN_MTHD_4_RATE = decsnMthd4Rate;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_5_RATE, 
	 * 보수_대상_선정.결정_방식_5_비율 값읽기
	 * @return
	 */
	@JsonProperty(value="DECSN_MTHD_5_RATE") 
	public java.lang.Long getDECSN_MTHD_5_RATE() {
		return this.DECSN_MTHD_5_RATE;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.DECSN_MTHD_5_RATE, 
	 * 보수_대상_선정.결정_방식_5_비율 값설정
	 * @param decsnMthd5Rate
	 */
	public void setDECSN_MTHD_5_RATE(java.lang.Long decsnMthd5Rate) {
		this.DECSN_MTHD_5_RATE = decsnMthd5Rate;
	}

	/**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_PURPS, 
	 * 보수_대상_선정.산정_목적 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_PURPS") 
	public java.lang.String getSLCTN_PURPS() {
		return this.SLCTN_PURPS;
	}
 
	 /**
	 * TN_RPAIR_TRGET_SLCTN.SLCTN_PURPS, 
	 * 보수_대상_선정.산정_목적 값설정
	 * @param slctnPurps
	 */
	public void setSLCTN_PURPS(java.lang.String slctnPurps) {
		this.SLCTN_PURPS = slctnPurps;
	}
	@JsonProperty(value="DECSN_MTHD_1_BUDGET") 
	public java.lang.Long getDECSN_MTHD_1_BUDGET() {
		return this.DECSN_MTHD_1_BUDGET;
	}

	public void setDECSN_MTHD_1_BUDGET(java.lang.Long dECSN_MTHD_1_BUDGET) {
		this.DECSN_MTHD_1_BUDGET = dECSN_MTHD_1_BUDGET;
	}
	@JsonProperty(value="DECSN_MTHD_2_BUDGET") 
	public java.lang.Long getDECSN_MTHD_2_BUDGET() {
		return this.DECSN_MTHD_2_BUDGET;
	}

	public void setDECSN_MTHD_2_BUDGET(java.lang.Long dECSN_MTHD_2_BUDGET) {
		this.DECSN_MTHD_2_BUDGET = dECSN_MTHD_2_BUDGET;
	}
	@JsonProperty(value="DECSN_MTHD_3_BUDGET") 
	public java.lang.Long getDECSN_MTHD_3_BUDGET() {
		return this.DECSN_MTHD_3_BUDGET;
	}

	public void setDECSN_MTHD_3_BUDGET(java.lang.Long dECSN_MTHD_3_BUDGET) {
		this.DECSN_MTHD_3_BUDGET = dECSN_MTHD_3_BUDGET;
	}
	@JsonProperty(value="DECSN_MTHD_4_BUDGET") 
	public java.lang.Long getDECSN_MTHD_4_BUDGET() {
		return this.DECSN_MTHD_4_BUDGET;
	}

	public void setDECSN_MTHD_4_BUDGET(java.lang.Long dECSN_MTHD_4_BUDGET) {
		this.DECSN_MTHD_4_BUDGET = dECSN_MTHD_4_BUDGET;
	}
	@JsonProperty(value="DECSN_MTHD_5_BUDGET") 
	public java.lang.Long getDECSN_MTHD_5_BUDGET() {
		return this.DECSN_MTHD_5_BUDGET;
	}

	public void setDECSN_MTHD_5_BUDGET(java.lang.Long dECSN_MTHD_5_BUDGET) {
		this.DECSN_MTHD_5_BUDGET = dECSN_MTHD_5_BUDGET;
	}
	@JsonProperty(value="SLCTN_BUDGET_1") 
	public java.lang.Long getSLCTN_BUDGET_1() {
		return this.SLCTN_BUDGET_1;
	}

	public void setSLCTN_BUDGET_1(java.lang.Long slctn_budget_1) {
		this.SLCTN_BUDGET_1 = slctn_budget_1;
	}
	@JsonProperty(value="SLCTN_BUDGET_2") 
	public java.lang.Long getSLCTN_BUDGET_2() {
		return this.SLCTN_BUDGET_2;
	}

	public void setSLCTN_BUDGET_2(java.lang.Long slctn_budget_2) {
		this.SLCTN_BUDGET_2 = slctn_budget_2;
	}
	@JsonProperty(value="SLCTN_BUDGET_3") 
	public java.lang.Long getSLCTN_BUDGET_3() {
		return this.SLCTN_BUDGET_3;
	}

	public void setSLCTN_BUDGET_3(java.lang.Long slctn_budget_3) {
		this.SLCTN_BUDGET_3 = slctn_budget_3;
	}
	@JsonProperty(value="SLCTN_BUDGET_4") 
	public java.lang.Long getSLCTN_BUDGET_4() {
		return this.SLCTN_BUDGET_4;
	}

	public void setSLCTN_BUDGET_4(java.lang.Long slctn_budget_4) {
		this.SLCTN_BUDGET_4 = slctn_budget_4;
	}
	@JsonProperty(value="SLCTN_BUDGET_5") 
	public java.lang.Long getSLCTN_BUDGET_5() {
		return this.SLCTN_BUDGET_5;
	}

	public void setSLCTN_BUDGET_5(java.lang.Long slctn_budget_5) {
		this.SLCTN_BUDGET_5 = slctn_budget_5;
	}

	@JsonProperty(value="ROAD_GRAD") 
	public java.lang.String getROAD_GRAD() {
		return ROAD_GRAD;
	}

	public void setROAD_GRAD(java.lang.String rOAD_GRAD) {
		ROAD_GRAD = rOAD_GRAD;
	}
	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return ROAD_NO;
	}

	public void setROAD_NO(java.lang.String rOAD_NO) {
		ROAD_NO = rOAD_NO;
	}
	@JsonProperty(value="ROAD_NO_VAL") 
	public java.lang.String getROAD_NO_VAL() {
		return ROAD_NO_VAL;
	}

	public void setROAD_NO_VAL(java.lang.String rOAD_NO_VAL) {
		ROAD_NO_VAL = rOAD_NO_VAL;
	}
	@JsonProperty(value="ADM_CODE") 
	public java.lang.String getADM_CODE() {
		return ADM_CODE;
	}

	public void setADM_CODE(java.lang.String aDM_CODE) {
		ADM_CODE = aDM_CODE;
	}

	/**
	 * @return the rOUTE_CODE
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return ROUTE_CODE;
	}

	/**
	 * @param rOUTE_CODE the rOUTE_CODE to set
	 */
	public void setROUTE_CODE(java.lang.String rOUTE_CODE) {
		ROUTE_CODE = rOUTE_CODE;
	}
	/**
	 * @return the RN
	 */
	@JsonProperty(value="RN") 
	public java.lang.String getRN() {
		return RN;
	}

	public void setRN(java.lang.String rN) {
		RN = rN;
	}

}
