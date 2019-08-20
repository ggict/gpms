
package kr.go.gg.gpms.menu.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 시스템메뉴
 *
 * @Class Name : MenuVO.java
 * @Description : Menu VO class
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
public class MenuVO extends BaseVO {

	public MenuVO() {
		super();
	}
	
	/** 
	 * TN_MENU.MENU_ID, 
	 * 시스템메뉴.메뉴_ID
	 */
	@XmlElement
	private java.lang.String MENU_ID;

	/** 
	 * TN_MENU.UPPER_MENU_ID, 
	 * 시스템메뉴.상위_메뉴_ID
	 */
	@XmlElement
	private java.lang.String UPPER_MENU_ID;
	
	/** 
	 * TN_MENU.USE_AT, 
	 * 시스템메뉴.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_MENU.DELETE_AT, 
	 * 시스템메뉴.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_MENU.UPDUSR_NO, 
	 * 시스템메뉴.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TN_MENU.UPDT_DT, 
	 * 시스템메뉴.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/** 
	 * TN_MENU.MENU_NM, 
	 * 시스템메뉴.메뉴_명
	 */
	@XmlElement
	private java.lang.String MENU_NM;

	/** 
	 * TN_MENU.CRTR_NO, 
	 * 시스템메뉴.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_MENU.CREAT_DT, 
	 * 시스템메뉴.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL
	 */
	@XmlElement
	private java.lang.String URL;

	/** 
	 * TN_MENU.MENU_DC, 
	 * 시스템메뉴.메뉴_설명
	 */
	@XmlElement
	private java.lang.String MENU_DC;
	
	//메뉴아이디PK
	@XmlElement
	private java.lang.String MENU_ID_KEY;
	
	//검색조건 - 시스템메뉴명
	@XmlElement
	private java.lang.String SCH_MENU_D;
	
	//검색조건 - 메뉴명
	@XmlElement
	private java.lang.String SCH_MENU_S;
	
	//시스템코드
	@XmlElement
	private java.lang.String SYS_CODE;
	
	//시스템명
	@XmlElement
	private java.lang.String SYS_NM;
	
	//메뉴레벨
	@XmlElement
	private java.lang.String MENU_DP;
	
	//메뉴코드
	@XmlElement
	private java.lang.String MENU_CODE;
	
	//저장여부
	@XmlElement
	private java.lang.String SAVEAT;
	
	//메뉴레벨
	@XmlElement
	private java.lang.String SCH_MENU_DP;
	
	//검색조건 - URL
	@XmlElement
	private java.lang.String SCH_URL;
	
	//검색조건 - URL
	@XmlElement
	private java.lang.String SCH_MENU_NM;
	
	/**
	 * TN_MENU.MENU_ID, 
	 * 시스템메뉴.메뉴_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_ID") 
	public java.lang.String getMENU_ID() {
		return this.MENU_ID;
	}
 
	 /**
	 * TN_MENU.MENU_ID, 
	 * 시스템메뉴.메뉴_ID 값설정
	 * @param menuId
	 */
	public void setMENU_ID(java.lang.String menuId) {
		this.MENU_ID = menuId;
	}

	/**
	 * TN_MENU.UPPER_MENU_ID, 
	 * 시스템메뉴.상위_메뉴_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="UPPER_MENU_ID") 
	public java.lang.String getUPPER_MENU_ID() {
		return this.UPPER_MENU_ID;
	}
 
	 /**
	 * TN_MENU.UPPER_MENU_ID, 
	 * 시스템메뉴.상위_메뉴_ID 값설정
	 * @param upperMenuId
	 */
	public void setUPPER_MENU_ID(java.lang.String upperMenuId) {
		this.UPPER_MENU_ID = upperMenuId;
	}

	/**
	 * TN_MENU.USE_AT, 
	 * 시스템메뉴.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_MENU.USE_AT, 
	 * 시스템메뉴.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_MENU.DELETE_AT, 
	 * 시스템메뉴.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_MENU.DELETE_AT, 
	 * 시스템메뉴.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_MENU.UPDUSR_NO, 
	 * 시스템메뉴.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_MENU.UPDUSR_NO, 
	 * 시스템메뉴.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}


	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}

	public void setUPDT_DT(java.util.Date uPDT_DT) {
		this.UPDT_DT = uPDT_DT;
	}

	/**
	 * TN_MENU.MENU_NM, 
	 * 시스템메뉴.메뉴_명 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_NM") 
	public java.lang.String getMENU_NM() {
		return this.MENU_NM;
	}
 
	 /**
	 * TN_MENU.MENU_NM, 
	 * 시스템메뉴.메뉴_명 값설정
	 * @param menuNm
	 */
	public void setMENU_NM(java.lang.String menuNm) {
		this.MENU_NM = menuNm;
	}

	/**
	 * TN_MENU.CRTR_NO, 
	 * 시스템메뉴.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_MENU.CRTR_NO, 
	 * 시스템메뉴.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_MENU.CREAT_DT, 
	 * 시스템메뉴.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_MENU.CREAT_DT, 
	 * 시스템메뉴.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL 값읽기
	 * @return
	 */
	@JsonProperty(value="URL") 
	public java.lang.String getURL() {
		return this.URL;
	}
 
	 /**
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL 값설정
	 * @param url
	 */
	public void setURL(java.lang.String url) {
		this.URL = url;
	}

	/**
	 * TN_MENU.MENU_DC, 
	 * 시스템메뉴.메뉴_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_DC") 
	public java.lang.String getMENU_DC() {
		return this.MENU_DC;
	}
 
	 /**
	 * TN_MENU.MENU_DC, 
	 * 시스템메뉴.메뉴_설명 값설정
	 * @param menuDc
	 */
	public void setMENU_DC(java.lang.String menuDc) {
		this.MENU_DC = menuDc;
	}

	@JsonProperty(value="SYS_CODE") 
	public java.lang.String getSYS_CODE() {
		return this.SYS_CODE;
	}

	public void setSYS_CODE(java.lang.String sYS_CODE) {
		SYS_CODE = sYS_CODE;
	}

	@JsonProperty(value="SYS_NM") 
	public java.lang.String getSYS_NM() {
		return this.SYS_NM;
	}

	public void setSYS_NM(java.lang.String sYS_NM) {
		this.SYS_NM = sYS_NM;
	}

	@JsonProperty(value="MENU_DP") 
	public java.lang.String getMENU_DP() {
		return this.MENU_DP;
	}

	public void setMENU_DP(java.lang.String mENU_DP) {
		this.MENU_DP = mENU_DP;
	}

	@JsonProperty(value="MENU_CODE") 
	public java.lang.String getMENU_CODE() {
		return this.MENU_CODE;
	}

	public void setMENU_CODE(java.lang.String mENU_CODE) {
		this.MENU_CODE = mENU_CODE;
	}
	
	@JsonProperty(value="SAVEAT") 
	public java.lang.String getSAVEAT() {
		return this.SAVEAT;
	}

	public void setSAVEAT(java.lang.String sAVEAT) {
		this.SAVEAT = sAVEAT;
	}

	@JsonProperty(value="SCH_MENU_D") 
	public java.lang.String getSCH_MENU_D() {
		return this.SCH_MENU_D;
	}

	public void setSCH_MENU_D(java.lang.String sCH_MENU_D) {
		this.SCH_MENU_D = sCH_MENU_D;
	}

	@JsonProperty(value="SCH_MENU_S") 
	public java.lang.String getSCH_MENU_S() {
		return this.SCH_MENU_S;
	}

	public void setSCH_MENU_S(java.lang.String sCH_MENU_S) {
		this.SCH_MENU_S = sCH_MENU_S;
	}

	@JsonProperty(value="SCH_MENU_DP") 
	public java.lang.String getSCH_MENU_DP() {
		return this.SCH_MENU_DP;
	}

	public void setSCH_MENU_DP(java.lang.String sCH_MENU_DP) {
		this.SCH_MENU_DP = sCH_MENU_DP;
	}
	
	/**
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL 값읽기
	 * @return
	 */
	@JsonProperty(value="SCH_URL") 
	public java.lang.String getSCH_URL() {
		return this.SCH_URL;
	}
 
	 /**
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL 값설정
	 * @param url
	 */
	public void setSCH_URL(java.lang.String url) {
		this.SCH_URL = url;
	}

	@JsonProperty(value="MENU_ID_KEY") 
	public java.lang.String getMENU_ID_KEY() {
		return this.MENU_ID_KEY;
	}

	public void setMENU_ID_KEY(java.lang.String mENU_ID_KEY) {
		this.MENU_ID_KEY = mENU_ID_KEY;
	}

	@JsonProperty(value="SCH_MENU_NM") 
	public java.lang.String getSCH_MENU_NM() {
		return this.SCH_MENU_NM;
	}

	public void setSCH_MENU_NM(java.lang.String sCH_MENU_NM) {
		this.SCH_MENU_NM = sCH_MENU_NM;
	}
	
}
