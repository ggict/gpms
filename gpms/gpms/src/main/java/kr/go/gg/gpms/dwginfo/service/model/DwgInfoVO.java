
package kr.go.gg.gpms.dwginfo.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DWG_INFO
 *
 * @Class Name : DwgInfoVO.java
 * @Description : DwgInfo VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class DwgInfoVO extends BaseVO {

	public DwgInfoVO() {
		super();
	}
	
	/** 
	 * DWG_INFO.MCO, 
	 * DWG_INFO.관리기관
	 */
	@XmlElement
	private java.lang.String MCO;

	/** 
	 * DWG_INFO.ROAD_NO, 
	 * DWG_INFO.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO;

	/** 
	 * DWG_INFO.SECT, 
	 * DWG_INFO.구간_번호
	 */
	@XmlElement
	private java.lang.String SECT;

	/** 
	 * DWG_INFO.HCODE, 
	 * DWG_INFO.이력_코드
	 */
	@XmlElement
	private java.lang.String HCODE;

	/** 
	 * DWG_INFO.SECT_ST, 
	 * DWG_INFO.도면_시점
	 */
	@XmlElement
	private java.math.BigDecimal SECT_ST;

	/** 
	 * DWG_INFO.SECT_ED, 
	 * DWG_INFO.도면_종점
	 */
	@XmlElement
	private java.math.BigDecimal SECT_ED;

	/** 
	 * DWG_INFO.DWG_LEN, 
	 * DWG_INFO.도면_연장
	 */
	@XmlElement
	private java.math.BigDecimal DWG_LEN;

	/** 
	 * DWG_INFO.DWG_NAME, 
	 * DWG_INFO.도면_명
	 */
	@XmlElement
	private java.lang.String DWG_NAME;

	/** 
	 * DWG_INFO.CON_NAME, 
	 * DWG_INFO.구간_도면_명
	 */
	@XmlElement
	private java.lang.String CON_NAME;

	/** 
	 * DWG_INFO.LOCAT, 
	 * DWG_INFO.법정동_위치
	 */
	@XmlElement
	private java.lang.String LOCAT;

	/** 
	 * DWG_INFO.DIV_ROAD_NO, 
	 * DWG_INFO.분기_도로_번호
	 */
	@XmlElement
	private java.lang.String DIV_ROAD_NO;

	/** 
	 * DWG_INFO.BY_DWG_NAME, 
	 * DWG_INFO.분기_도로_인접_도면_명
	 */
	@XmlElement
	private java.lang.String BY_DWG_NAME;

	/** 
	 * DWG_INFO.BYPASS, 
	 * DWG_INFO.우회도로_유무
	 */
	@XmlElement
	private java.lang.String BYPASS;

	/** 
	 * DWG_INFO.INPUT_DATE, 
	 * DWG_INFO.입력일
	 */
	@XmlElement
	private java.lang.String INPUT_DATE;

	/** 
	 * DWG_INFO.DWG_GUBN, 
	 * DWG_INFO.도면_구분
	 */
	@XmlElement
	private java.lang.String DWG_GUBN;

	/** 
	 * DWG_INFO.REM, 
	 * DWG_INFO.비고
	 */
	@XmlElement
	private java.lang.String REM;

	/** 
	 * DWG_INFO.SEQID, 
	 * DWG_INFO.SEQID
	 */
	@XmlElement
	private java.lang.String SEQID;
	
	/** 
	 * DWG_INFO.ROAD_NO_VAL, 
	 * DWG_INFO.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO_VAL;

	/**
	 * DWG_INFO.MCO, 
	 * DWG_INFO.관리기관 
	 * @return
	 */
	@JsonProperty(value="MCO") 
	public java.lang.String getMCO() {
		return this.MCO;
	}
 
	 /**
	 * DWG_INFO.MCO, 
	 * DWG_INFO.관리기관 
	 * @param mco
	 */
	public void setMCO(java.lang.String mco) {
		this.MCO = mco;
	}

	/**
	 * DWG_INFO.ROAD_NO, 
	 * DWG_INFO.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return this.ROAD_NO;
	}
 
	 /**
	 * DWG_INFO.ROAD_NO, 
	 * DWG_INFO.노선_번호 
	 * @param roadNo
	 */
	public void setROAD_NO(java.lang.String roadNo) {
		this.ROAD_NO = roadNo;
	}

	/**
	 * DWG_INFO.SECT, 
	 * DWG_INFO.구간_번호 
	 * @return
	 */
	@JsonProperty(value="SECT") 
	public java.lang.String getSECT() {
		return this.SECT;
	}
 
	 /**
	 * DWG_INFO.SECT, 
	 * DWG_INFO.구간_번호 
	 * @param sect
	 */
	public void setSECT(java.lang.String sect) {
		this.SECT = sect;
	}

	/**
	 * DWG_INFO.HCODE, 
	 * DWG_INFO.이력_코드 
	 * @return
	 */
	@JsonProperty(value="HCODE") 
	public java.lang.String getHCODE() {
		return this.HCODE;
	}
 
	 /**
	 * DWG_INFO.HCODE, 
	 * DWG_INFO.이력_코드 
	 * @param hcode
	 */
	public void setHCODE(java.lang.String hcode) {
		this.HCODE = hcode;
	}

	/**
	 * DWG_INFO.SECT_ST, 
	 * DWG_INFO.도면_시점 
	 * @return
	 */
	@JsonProperty(value="SECT_ST") 
	public java.math.BigDecimal getSECT_ST() {
		return this.SECT_ST;
	}
 
	 /**
	 * DWG_INFO.SECT_ST, 
	 * DWG_INFO.도면_시점 
	 * @param sectSt
	 */
	public void setSECT_ST(java.math.BigDecimal sectSt) {
		this.SECT_ST = sectSt;
	}

	/**
	 * DWG_INFO.SECT_ED, 
	 * DWG_INFO.도면_종점 
	 * @return
	 */
	@JsonProperty(value="SECT_ED") 
	public java.math.BigDecimal getSECT_ED() {
		return this.SECT_ED;
	}
 
	 /**
	 * DWG_INFO.SECT_ED, 
	 * DWG_INFO.도면_종점 
	 * @param sectEd
	 */
	public void setSECT_ED(java.math.BigDecimal sectEd) {
		this.SECT_ED = sectEd;
	}

	/**
	 * DWG_INFO.DWG_LEN, 
	 * DWG_INFO.도면_연장 
	 * @return
	 */
	@JsonProperty(value="DWG_LEN") 
	public java.math.BigDecimal getDWG_LEN() {
		return this.DWG_LEN;
	}
 
	 /**
	 * DWG_INFO.DWG_LEN, 
	 * DWG_INFO.도면_연장 
	 * @param dwgLen
	 */
	public void setDWG_LEN(java.math.BigDecimal dwgLen) {
		this.DWG_LEN = dwgLen;
	}

	/**
	 * DWG_INFO.DWG_NAME, 
	 * DWG_INFO.도면_명 
	 * @return
	 */
	@JsonProperty(value="DWG_NAME") 
	public java.lang.String getDWG_NAME() {
		return this.DWG_NAME;
	}
 
	 /**
	 * DWG_INFO.DWG_NAME, 
	 * DWG_INFO.도면_명 
	 * @param dwgName
	 */
	public void setDWG_NAME(java.lang.String dwgName) {
		this.DWG_NAME = dwgName;
	}

	/**
	 * DWG_INFO.CON_NAME, 
	 * DWG_INFO.구간_도면_명 
	 * @return
	 */
	@JsonProperty(value="CON_NAME") 
	public java.lang.String getCON_NAME() {
		return this.CON_NAME;
	}
 
	 /**
	 * DWG_INFO.CON_NAME, 
	 * DWG_INFO.구간_도면_명 
	 * @param conName
	 */
	public void setCON_NAME(java.lang.String conName) {
		this.CON_NAME = conName;
	}

	/**
	 * DWG_INFO.LOCAT, 
	 * DWG_INFO.법정동_위치 
	 * @return
	 */
	@JsonProperty(value="LOCAT") 
	public java.lang.String getLOCAT() {
		return this.LOCAT;
	}
 
	 /**
	 * DWG_INFO.LOCAT, 
	 * DWG_INFO.법정동_위치 
	 * @param locat
	 */
	public void setLOCAT(java.lang.String locat) {
		this.LOCAT = locat;
	}

	/**
	 * DWG_INFO.DIV_ROAD_NO, 
	 * DWG_INFO.분기_도로_번호 
	 * @return
	 */
	@JsonProperty(value="DIV_ROAD_NO") 
	public java.lang.String getDIV_ROAD_NO() {
		return this.DIV_ROAD_NO;
	}
 
	 /**
	 * DWG_INFO.DIV_ROAD_NO, 
	 * DWG_INFO.분기_도로_번호 
	 * @param divRoadNo
	 */
	public void setDIV_ROAD_NO(java.lang.String divRoadNo) {
		this.DIV_ROAD_NO = divRoadNo;
	}

	/**
	 * DWG_INFO.BY_DWG_NAME, 
	 * DWG_INFO.분기_도로_인접_도면_명 
	 * @return
	 */
	@JsonProperty(value="BY_DWG_NAME") 
	public java.lang.String getBY_DWG_NAME() {
		return this.BY_DWG_NAME;
	}
 
	 /**
	 * DWG_INFO.BY_DWG_NAME, 
	 * DWG_INFO.분기_도로_인접_도면_명 
	 * @param byDwgName
	 */
	public void setBY_DWG_NAME(java.lang.String byDwgName) {
		this.BY_DWG_NAME = byDwgName;
	}

	/**
	 * DWG_INFO.BYPASS, 
	 * DWG_INFO.우회도로_유무 
	 * @return
	 */
	@JsonProperty(value="BYPASS") 
	public java.lang.String getBYPASS() {
		return this.BYPASS;
	}
 
	 /**
	 * DWG_INFO.BYPASS, 
	 * DWG_INFO.우회도로_유무 
	 * @param bypass
	 */
	public void setBYPASS(java.lang.String bypass) {
		this.BYPASS = bypass;
	}

	/**
	 * DWG_INFO.INPUT_DATE, 
	 * DWG_INFO.입력일 
	 * @return
	 */
	@JsonProperty(value="INPUT_DATE") 
	public java.lang.String getINPUT_DATE() {
		return this.INPUT_DATE;
	}
 
	 /**
	 * DWG_INFO.INPUT_DATE, 
	 * DWG_INFO.입력일 
	 * @param inputDate
	 */
	public void setINPUT_DATE(java.lang.String inputDate) {
		this.INPUT_DATE = inputDate;
	}

	/**
	 * DWG_INFO.DWG_GUBN, 
	 * DWG_INFO.도면_구분 
	 * @return
	 */
	@JsonProperty(value="DWG_GUBN") 
	public java.lang.String getDWG_GUBN() {
		return this.DWG_GUBN;
	}
 
	 /**
	 * DWG_INFO.DWG_GUBN, 
	 * DWG_INFO.도면_구분 
	 * @param dwgGubn
	 */
	public void setDWG_GUBN(java.lang.String dwgGubn) {
		this.DWG_GUBN = dwgGubn;
	}

	/**
	 * DWG_INFO.REM, 
	 * DWG_INFO.비고 
	 * @return
	 */
	@JsonProperty(value="REM") 
	public java.lang.String getREM() {
		return this.REM;
	}
 
	 /**
	 * DWG_INFO.REM, 
	 * DWG_INFO.비고 
	 * @param rem
	 */
	public void setREM(java.lang.String rem) {
		this.REM = rem;
	}

	/**
	 * DWG_INFO.SEQID, 
	 * DWG_INFO.SEQID 
	 * @return
	 */
	@JsonProperty(value="SEQID") 
	public java.lang.String getSEQID() {
		return this.SEQID;
	}
 
	 /**
	 * DWG_INFO.SEQID, 
	 * DWG_INFO.SEQID 
	 * @param seqid
	 */
	public void setSEQID(java.lang.String seqid) {
		this.SEQID = seqid;
	}
	
	/**
	 * DWG_INFO.ROAD_NO_VAL, 
	 * DWG_INFO.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO_VAL") 
	public java.lang.String getROAD_NO_VAL() {
		return this.ROAD_NO_VAL;
	}
 
	 /**
	 * DWG_INFO.ROAD_NO_VAL, 
	 * DWG_INFO.노선_번호 
	 * @param roadNoVal
	 */
	public void setROAD_NO_VAL(java.lang.String roadNoVal) {
		this.ROAD_NO_VAL = roadNoVal;
	}

}
