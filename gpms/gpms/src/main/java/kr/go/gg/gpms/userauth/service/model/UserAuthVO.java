
package kr.go.gg.gpms.userauth.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 사용자권한
 *
 * @Class Name : UserAuthVO.java
 * @Description : UserAuth VO class
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
public class UserAuthVO extends BaseVO {

	public UserAuthVO() {
		super();
	}
	
	/** 
	 * TN_USER_AUTH.USER_NO, 
	 * 사용자권한.사용자_번호
	 */
	@XmlElement
	private String USER_NO;

	/** 
	 * TN_USER_AUTH.AUTHOR_ID, 
	 * 사용자권한.권한_ID
	 */
	@XmlElement
	private java.lang.String AUTHOR_ID;

	/** 
	 * TN_USER_AUTH.CRTR_NO, 
	 * 사용자권한.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_USER_AUTH.CREAT_DT, 
	 * 사용자권한.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;
	
	//사용자명
	@XmlElement
	private java.lang.String USER_NM;
	
	//검색 - 사용자명
	@XmlElement
	private java.lang.String SCH_USER_NM;
	
	//사용자ID
	@XmlElement
	private java.lang.String USER_ID;
	
	//사용자생년월일
	@XmlElement
	private java.lang.String BRTHDY;
	
	//사용여부
	@XmlElement
	private java.lang.String USE_AT;
	
	//권한명
	@XmlElement
	private java.lang.String AUTHOR_NM;

	/**
	 * TN_USER_AUTH.USER_NO, 
	 * 사용자권한.사용자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_NO") 
	public String getUSER_NO() {
		return this.USER_NO;
	}
 
	 /**
	 * TN_USER_AUTH.USER_NO, 
	 * 사용자권한.사용자_번호 값설정
	 * @param userNo
	 */
	public void setUSER_NO(String userNo) {
		this.USER_NO = userNo;
	}

	/**
	 * TN_USER_AUTH.AUTHOR_ID, 
	 * 사용자권한.권한_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="AUTHOR_ID") 
	public java.lang.String getAUTHOR_ID() {
		return this.AUTHOR_ID;
	}
 
	 /**
	 * TN_USER_AUTH.AUTHOR_ID, 
	 * 사용자권한.권한_ID 값설정
	 * @param authorId
	 */
	public void setAUTHOR_ID(java.lang.String authorId) {
		this.AUTHOR_ID = authorId;
	}

	/**
	 * TN_USER_AUTH.CRTR_NO, 
	 * 사용자권한.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_USER_AUTH.CRTR_NO, 
	 * 사용자권한.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_USER_AUTH.CREAT_DT, 
	 * 사용자권한.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_USER_AUTH.CREAT_DT, 
	 * 사용자권한.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	@JsonProperty(value="USER_NM") 
	public java.lang.String getUSER_NM() {
		return this.USER_NM;
	}

	public void setUSER_NM(java.lang.String uSER_NM) {
		this.USER_NM = uSER_NM;
	}

	@JsonProperty(value="SCH_USER_NM") 
	public java.lang.String getSCH_USER_NM() {
		return this.SCH_USER_NM;
	}

	public void setSCH_USER_NM(java.lang.String sCH_USER_NM) {
		this.SCH_USER_NM = sCH_USER_NM;
	}

	@JsonProperty(value="USER_ID") 
	public java.lang.String getUSER_ID() {
		return this.USER_ID;
	}

	public void setUSER_ID(java.lang.String uSER_ID) {
		this.USER_ID = uSER_ID;
	}

	@JsonProperty(value="BRTHDY") 
	public java.lang.String getBRTHDY() {
		return this.BRTHDY;
	}

	public void setBRTHDY(java.lang.String bRTHDY) {
		this.BRTHDY = bRTHDY;
	}

	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	public void setUSE_AT(java.lang.String uSE_AT) {
		this.USE_AT = uSE_AT;
	}

	@JsonProperty(value="AUTHOR_NM") 
	public java.lang.String getAUTHOR_NM() {
		return this.AUTHOR_NM;
	}

	public void setAUTHOR_NM(java.lang.String aUTHOR_NM) {
		this.AUTHOR_NM = aUTHOR_NM;
	}
	
}
