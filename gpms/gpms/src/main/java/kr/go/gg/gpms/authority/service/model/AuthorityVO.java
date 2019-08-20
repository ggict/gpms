
package kr.go.gg.gpms.authority.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 권한그룹
 *
 * @Class Name : AuthorityVO.java
 * @Description : Authority VO class
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
public class AuthorityVO extends BaseVO {

	public AuthorityVO() {
		super();
	}
	
	/** 
	 * TN_AUTHORITY.AUTHOR_ID, 
	 * 권한그룹.권한_ID
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
	 * TN_AUTHORITY.DC, 
	 * 권한그룹.설명
	 */
	@XmlElement
	private java.lang.String DC;

	/** 
	 * TN_AUTHORITY.USE_AT, 
	 * 권한그룹.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_AUTHORITY.CRTR_NO, 
	 * 권한그룹.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_AUTHORITY.CREAT_DT, 
	 * 권한그룹.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_AUTHORITY.DELETE_AT, 
	 * 권한그룹.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_AUTHORITY.UPDUSR_NO, 
	 * 권한그룹.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TN_AUTHORITY.UPDT_DT, 
	 * 권한그룹.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	//사용자구분
	@XmlElement
	private java.lang.String USER_SE;
	
	//사용자 그룹 여부 (사용자 그룹 & 메뉴접근 권한)
	@XmlElement
	private boolean userGroup = false;
	
	//메뉴접근 권한 (사용자 그룹 & 메뉴접근 권한)
	@XmlElement
	private boolean menuAuth = false;

	/**
	 * TN_AUTHORITY.AUTHOR_ID, 
	 * 권한그룹.권한_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_ID") 
	public java.lang.String getAUTHOR_ID() {
		return this.AUTHOR_ID;
	}
 
	 /**
	 * TN_AUTHORITY.AUTHOR_ID, 
	 * 권한그룹.권한_ID 값설정
	 * @param authorId
	 */
	public void setAUTHOR_ID(java.lang.String authorId) {
		this.AUTHOR_ID = authorId;
	}

	/**
	 * TN_AUTHORITY.AUTHOR_NM, 
	 * 권한그룹.권한_명칭 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_NM") 
	public java.lang.String getAUTHOR_NM() {
		return this.AUTHOR_NM;
	}
 
	 /**
	 * TN_AUTHORITY.AUTHOR_NM, 
	 * 권한그룹.권한_명칭 값설정
	 * @param authorNm
	 */
	public void setAUTHOR_NM(java.lang.String authorNm) {
		this.AUTHOR_NM = authorNm;
	}

	/**
	 * TN_AUTHORITY.DC, 
	 * 권한그룹.설명 값읽기
	 * @return
	 */
	@JsonProperty(value="DC") 
	public java.lang.String getDC() {
		return this.DC;
	}
 
	 /**
	 * TN_AUTHORITY.DC, 
	 * 권한그룹.설명 값설정
	 * @param dc
	 */
	public void setDC(java.lang.String dc) {
		this.DC = dc;
	}

	/**
	 * TN_AUTHORITY.USE_AT, 
	 * 권한그룹.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_AUTHORITY.USE_AT, 
	 * 권한그룹.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_AUTHORITY.CRTR_NO, 
	 * 권한그룹.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_AUTHORITY.CRTR_NO, 
	 * 권한그룹.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_AUTHORITY.CREAT_DT, 
	 * 권한그룹.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_AUTHORITY.CREAT_DT, 
	 * 권한그룹.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_AUTHORITY.DELETE_AT, 
	 * 권한그룹.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_AUTHORITY.DELETE_AT, 
	 * 권한그룹.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_AUTHORITY.UPDUSR_NO, 
	 * 권한그룹.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_AUTHORITY.UPDUSR_NO, 
	 * 권한그룹.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_AUTHORITY.UPDT_DT, 
	 * 권한그룹.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_AUTHORITY.UPDT_DT, 
	 * 권한그룹.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="USER_SE") 
	public java.lang.String getUSER_SE() {
		return this.USER_SE;
	}

	public void setUSER_SE(java.lang.String uSER_SE) {
		this.USER_SE = uSER_SE;
	}
	
	@JsonProperty(value="userGroup")
	public boolean getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(boolean userGroup) {
		this.userGroup = userGroup;
	}
	
	@JsonProperty(value="menuAuth")
	public boolean isMenuAuth() {
		return menuAuth;
	}

	public void setMenuAuth(boolean menuAuth) {
		this.menuAuth = menuAuth;
	}
	
	

	
}
