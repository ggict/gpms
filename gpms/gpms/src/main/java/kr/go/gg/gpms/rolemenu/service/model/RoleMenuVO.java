
package kr.go.gg.gpms.rolemenu.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 권한역할메뉴
 *
 * @Class Name : RoleMenuVO.java
 * @Description : RoleMenu VO class
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
public class RoleMenuVO extends BaseVO {

	public RoleMenuVO() {
		super();
	}
	
	/** 
	 * TN_ROLE_MENU.ROLE_NO, 
	 * 권한역할메뉴.역할_번호
	 */
	@XmlElement
	private String ROLE_NO;

	/** 
	 * TN_ROLE_MENU.AUTHOR_ID, 
	 * 권한역할메뉴.권한_ID
	 */
	@XmlElement
	private java.lang.String AUTHOR_ID;
	/** 
	 * TN_AUTHORITY.AUTHOR_NM, 
	 * 권한그룹.권한_명칭
	 */
	@XmlElement
	private java.lang.String AUTHOR_NM;
	/** 
	 * TN_ROLE_MENU.MENU_ID, 
	 * 권한역할메뉴.메뉴_ID
	 */
	@XmlElement
	private java.lang.String MENU_ID;
	/** 
	 * TN_MENU.MENU_NM, 
	 * 시스템메뉴.메뉴_명
	 */
	@XmlElement
	private java.lang.String MENU_NM;
	
	/** 
	 * TN_MENU.URL, 
	 * 시스템메뉴.URL
	 */
	@XmlElement
	private java.lang.String URL;
	/** 
	 * TN_ROLE_MENU.CRTR_NO, 
	 * 권한역할메뉴.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_ROLE_MENU.CREAT_DT, 
	 * 권한역할메뉴.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_ROLE_MENU.DELETE_AT, 
	 * 권한역할메뉴.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_ROLE_MENU.UPDUSR_NO, 
	 * 권한역할메뉴.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TN_ROLE_MENU.UPDT_DT, 
	 * 권한역할메뉴.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	//검색-권한
	private java.lang.String SCH_AUTH;
	//사용여부
	private java.lang.String USE_AT;

	/**
	 * TN_ROLE_MENU.ROLE_NO, 
	 * 권한역할메뉴.역할_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="ROLE_NO") 
	public String getROLE_NO() {
		return this.ROLE_NO;
	}
 
	 /**
	 * TN_ROLE_MENU.ROLE_NO, 
	 * 권한역할메뉴.역할_번호 값설정
	 * @param roleNo
	 */
	public void setROLE_NO(String roleNo) {
		this.ROLE_NO = roleNo;
	}

	/**
	 * TN_ROLE_MENU.AUTHOR_ID, 
	 * 권한역할메뉴.권한_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_ID") 
	public java.lang.String getAUTHOR_ID() {
		return this.AUTHOR_ID;
	}
 
	 /**
	 * TN_ROLE_MENU.AUTHOR_ID, 
	 * 권한역할메뉴.권한_ID 값설정
	 * @param authorId
	 */
	public void setAUTHOR_ID(java.lang.String authorId) {
		this.AUTHOR_ID = authorId;
	}

	/**
	 * TN_ROLE_MENU.MENU_ID, 
	 * 권한역할메뉴.메뉴_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_ID") 
	public java.lang.String getMENU_ID() {
		return this.MENU_ID;
	}
 
	 /**
	 * TN_ROLE_MENU.MENU_ID, 
	 * 권한역할메뉴.메뉴_ID 값설정
	 * @param menuId
	 */
	public void setMENU_ID(java.lang.String menuId) {
		this.MENU_ID = menuId;
	}

	/**
	 * TN_ROLE_MENU.CRTR_NO, 
	 * 권한역할메뉴.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_ROLE_MENU.CRTR_NO, 
	 * 권한역할메뉴.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_ROLE_MENU.CREAT_DT, 
	 * 권한역할메뉴.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_ROLE_MENU.CREAT_DT, 
	 * 권한역할메뉴.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_ROLE_MENU.DELETE_AT, 
	 * 권한역할메뉴.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_ROLE_MENU.DELETE_AT, 
	 * 권한역할메뉴.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_ROLE_MENU.UPDUSR_NO, 
	 * 권한역할메뉴.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_ROLE_MENU.UPDUSR_NO, 
	 * 권한역할메뉴.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_ROLE_MENU.UPDT_DT, 
	 * 권한역할메뉴.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_ROLE_MENU.UPDT_DT, 
	 * 권한역할메뉴.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}
	@JsonProperty(value="MENU_NM") 
	public java.lang.String getMENU_NM() {
		return MENU_NM;
	}

	public void setMENU_NM(java.lang.String mENU_NM) {
		MENU_NM = mENU_NM;
	}
	@JsonProperty(value="URL") 
	public java.lang.String getURL() {
		return URL;
	}

	public void setURL(java.lang.String uRL) {
		URL = uRL;
	}

	@JsonProperty(value="AUTHOR_NM") 
	public java.lang.String getAUTHOR_NM() {
		return this.AUTHOR_NM;
	}

	public void setAUTHOR_NM(java.lang.String aUTHOR_NM) {
		this.AUTHOR_NM = aUTHOR_NM;
	}

	@JsonProperty(value="SCH_AUTH") 
	public java.lang.String getSCH_AUTH() {
		return this.SCH_AUTH;
	}

	public void setSCH_AUTH(java.lang.String sCH_AUTH) {
		this.SCH_AUTH = sCH_AUTH;
	}

	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	public void setUSE_AT(java.lang.String uSE_AT) {
		this.USE_AT = uSE_AT;
	}
}
