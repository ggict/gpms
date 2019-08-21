
package kr.go.gg.gpms.strdwg.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 구조물_도면_정보
 *
 * @Class Name : StrDwgVO.java
 * @Description : StrDwg VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class StrDwgVO extends BaseVO {

	public StrDwgVO() {
		super();
	}
	
	/** 
	 * STR_DWG.ROAD_NO, 
	 * 구조물_도면_정보.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO;

	/** 
	 * STR_DWG.SECT, 
	 * 구조물_도면_정보.구간_번호
	 */
	@XmlElement
	private java.lang.String SECT;

	/** 
	 * STR_DWG.STR_COD, 
	 * 구조물_도면_정보.구조물_관리번호
	 */
	@XmlElement
	private java.lang.String STR_COD;

	/** 
	 * STR_DWG.INPUT_DATE, 
	 * 구조물_도면_정보.입력일
	 */
	@XmlElement
	private java.lang.String INPUT_DATE;

	/** 
	 * STR_DWG.DWG_FILE, 
	 * 구조물_도면_정보.파일_명
	 */
	@XmlElement
	private java.lang.String DWG_FILE;

	/** 
	 * STR_DWG.DWG_NAME, 
	 * 구조물_도면_정보.도면_명칭
	 */
	@XmlElement
	private java.lang.String DWG_NAME;

	/** 
	 * STR_DWG.DWG_TYPE, 
	 * 구조물_도면_정보.입력_방식
	 */
	@XmlElement
	private java.lang.String DWG_TYPE;

	/** 
	 * STR_DWG.REM, 
	 * 구조물_도면_정보.비고
	 */
	@XmlElement
	private java.lang.String REM;

	/** 
	 * STR_DWG.SEQID, 
	 * 구조물_도면_정보.SEQID
	 */
	@XmlElement
	private java.lang.String SEQID;
	
	/** 
	 * STR_DWG.ROAD_NO_VAL, 
	 * 구조물_도면_정보.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO_VAL;

	/**
	 * STR_DWG.ROAD_NO, 
	 * 구조물_도면_정보.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return this.ROAD_NO;
	}
 
	 /**
	 * STR_DWG.ROAD_NO, 
	 * 구조물_도면_정보.노선_번호 
	 * @param roadNo
	 */
	public void setROAD_NO(java.lang.String roadNo) {
		this.ROAD_NO = roadNo;
	}

	/**
	 * STR_DWG.SECT, 
	 * 구조물_도면_정보.구간_번호 
	 * @return
	 */
	@JsonProperty(value="SECT") 
	public java.lang.String getSECT() {
		return this.SECT;
	}
 
	 /**
	 * STR_DWG.SECT, 
	 * 구조물_도면_정보.구간_번호 
	 * @param sect
	 */
	public void setSECT(java.lang.String sect) {
		this.SECT = sect;
	}

	/**
	 * STR_DWG.STR_COD, 
	 * 구조물_도면_정보.구조물_관리번호 
	 * @return
	 */
	@JsonProperty(value="STR_COD") 
	public java.lang.String getSTR_COD() {
		return this.STR_COD;
	}
 
	 /**
	 * STR_DWG.STR_COD, 
	 * 구조물_도면_정보.구조물_관리번호 
	 * @param strCod
	 */
	public void setSTR_COD(java.lang.String strCod) {
		this.STR_COD = strCod;
	}

	/**
	 * STR_DWG.INPUT_DATE, 
	 * 구조물_도면_정보.입력일 
	 * @return
	 */
	@JsonProperty(value="INPUT_DATE") 
	public java.lang.String getINPUT_DATE() {
		return this.INPUT_DATE;
	}
 
	 /**
	 * STR_DWG.INPUT_DATE, 
	 * 구조물_도면_정보.입력일 
	 * @param inputDate
	 */
	public void setINPUT_DATE(java.lang.String inputDate) {
		this.INPUT_DATE = inputDate;
	}

	/**
	 * STR_DWG.DWG_FILE, 
	 * 구조물_도면_정보.파일_명 
	 * @return
	 */
	@JsonProperty(value="DWG_FILE") 
	public java.lang.String getDWG_FILE() {
		return this.DWG_FILE;
	}
 
	 /**
	 * STR_DWG.DWG_FILE, 
	 * 구조물_도면_정보.파일_명 
	 * @param dwgFile
	 */
	public void setDWG_FILE(java.lang.String dwgFile) {
		this.DWG_FILE = dwgFile;
	}

	/**
	 * STR_DWG.DWG_NAME, 
	 * 구조물_도면_정보.도면_명칭 
	 * @return
	 */
	@JsonProperty(value="DWG_NAME") 
	public java.lang.String getDWG_NAME() {
		return this.DWG_NAME;
	}
 
	 /**
	 * STR_DWG.DWG_NAME, 
	 * 구조물_도면_정보.도면_명칭 
	 * @param dwgName
	 */
	public void setDWG_NAME(java.lang.String dwgName) {
		this.DWG_NAME = dwgName;
	}

	/**
	 * STR_DWG.DWG_TYPE, 
	 * 구조물_도면_정보.입력_방식 
	 * @return
	 */
	@JsonProperty(value="DWG_TYPE") 
	public java.lang.String getDWG_TYPE() {
		return this.DWG_TYPE;
	}
 
	 /**
	 * STR_DWG.DWG_TYPE, 
	 * 구조물_도면_정보.입력_방식 
	 * @param dwgType
	 */
	public void setDWG_TYPE(java.lang.String dwgType) {
		this.DWG_TYPE = dwgType;
	}

	/**
	 * STR_DWG.REM, 
	 * 구조물_도면_정보.비고 
	 * @return
	 */
	@JsonProperty(value="REM") 
	public java.lang.String getREM() {
		return this.REM;
	}
 
	 /**
	 * STR_DWG.REM, 
	 * 구조물_도면_정보.비고 
	 * @param rem
	 */
	public void setREM(java.lang.String rem) {
		this.REM = rem;
	}

	/**
	 * STR_DWG.SEQID, 
	 * 구조물_도면_정보.SEQID 
	 * @return
	 */
	@JsonProperty(value="SEQID") 
	public java.lang.String getSEQID() {
		return this.SEQID;
	}
 
	 /**
	 * STR_DWG.SEQID, 
	 * 구조물_도면_정보.SEQID 
	 * @param seqid
	 */
	public void setSEQID(java.lang.String seqid) {
		this.SEQID = seqid;
	}
	
	/**
	 * STR_DWG.ROAD_NO_VAL, 
	 * 구조물_도면_정보.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO_VAL") 
	public java.lang.String getROAD_NO_VAL() {
		return this.ROAD_NO_VAL;
	}
 
	 /**
	 * STR_DWG.ROAD_NO_VAL, 
	 * 구조물_도면_정보.노선_번호 
	 * @param roadNoVal
	 */
	public void setROAD_NO_VAL(java.lang.String roadNoVal) {
		this.ROAD_NO_VAL = roadNoVal;
	}

}
