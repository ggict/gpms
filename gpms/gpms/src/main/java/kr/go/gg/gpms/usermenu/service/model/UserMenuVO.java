
package kr.go.gg.gpms.usermenu.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 사용자권한메뉴
 *
 * @Class Name : UserMenuVO.java
 * @Description : UserMenu VO class
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
public class UserMenuVO extends UserMenuDefaultVO {

	public UserMenuVO() {
		super();
	}
	
	/** 
	 * TN_USER_MENU.AUTHOR_NO, 
	 * 사용자권한메뉴.권한_번호
	 */
	@XmlElement
	private String AUTHOR_NO;

	/** 
	 * TN_USER_MENU.DELETE_AT, 
	 * 사용자권한메뉴.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_USER_MENU.CRTR_NO, 
	 * 사용자권한메뉴.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_USER_MENU.CREAT_DT, 
	 * 사용자권한메뉴.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_USER_MENU.MENU_ID, 
	 * 사용자권한메뉴.메뉴_ID
	 */
	@XmlElement
	private java.lang.String MENU_ID;

	/** 
	 * TN_USER_MENU.USER_NO, 
	 * 사용자권한메뉴.사용자_번호
	 */
	@XmlElement
	private String USER_NO;

	/** 
	 * TN_USER_MENU.AUTHOR_ID, 
	 * 사용자권한메뉴.권한_ID
	 */
	@XmlElement
	private java.lang.String AUTHOR_ID;

	/**
	 * TN_USER_MENU.AUTHOR_NO, 
	 * 사용자권한메뉴.권한_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_NO") 
	public String getAUTHOR_NO() {
		return this.AUTHOR_NO;
	}
 
	 /**
	 * TN_USER_MENU.AUTHOR_NO, 
	 * 사용자권한메뉴.권한_번호 값설정
	 * @param authorNo
	 */
	public void setAUTHOR_NO(String authorNo) {
		this.AUTHOR_NO = authorNo;
	}

	/**
	 * TN_USER_MENU.DELETE_AT, 
	 * 사용자권한메뉴.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_USER_MENU.DELETE_AT, 
	 * 사용자권한메뉴.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_USER_MENU.CRTR_NO, 
	 * 사용자권한메뉴.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_USER_MENU.CRTR_NO, 
	 * 사용자권한메뉴.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_USER_MENU.CREAT_DT, 
	 * 사용자권한메뉴.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_USER_MENU.CREAT_DT, 
	 * 사용자권한메뉴.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_USER_MENU.MENU_ID, 
	 * 사용자권한메뉴.메뉴_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_ID") 
	public java.lang.String getMENU_ID() {
		return this.MENU_ID;
	}
 
	 /**
	 * TN_USER_MENU.MENU_ID, 
	 * 사용자권한메뉴.메뉴_ID 값설정
	 * @param menuId
	 */
	public void setMENU_ID(java.lang.String menuId) {
		this.MENU_ID = menuId;
	}

	/**
	 * TN_USER_MENU.USER_NO, 
	 * 사용자권한메뉴.사용자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_NO") 
	public String getUSER_NO() {
		return this.USER_NO;
	}
 
	 /**
	 * TN_USER_MENU.USER_NO, 
	 * 사용자권한메뉴.사용자_번호 값설정
	 * @param userNo
	 */
	public void setUSER_NO(String userNo) {
		this.USER_NO = userNo;
	}

	/**
	 * TN_USER_MENU.AUTHOR_ID, 
	 * 사용자권한메뉴.권한_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_ID") 
	public java.lang.String getAUTHOR_ID() {
		return this.AUTHOR_ID;
	}
 
	 /**
	 * TN_USER_MENU.AUTHOR_ID, 
	 * 사용자권한메뉴.권한_ID 값설정
	 * @param authorId
	 */
	public void setAUTHOR_ID(java.lang.String authorId) {
		this.AUTHOR_ID = authorId;
	}

}
