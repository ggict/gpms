
package kr.go.gg.gpms.userconnect.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 사용자접속로그
 *
 * @Class Name : UserConnectVO.java
 * @Description : UserConnect VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-10-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class UserConnectVO extends BaseVO {

	public UserConnectVO() {
		super();
	}
	
	/** 
	 * TL_USER_CONNECT.CONECT_LOG_NO, 
	 * 사용자접속로그.접속_로그_번호
	 */
	@XmlElement
	private java.lang.String CONECT_LOG_NO;

	/** 
	 * TL_USER_CONNECT.USER_NO, 
	 * 사용자접속로그.사용자_번호
	 */
	@XmlElement
	private java.lang.String USER_NO;

	/** 
	 * TL_USER_CONNECT.SESION_ID, 
	 * 사용자접속로그.세션_ID
	 */
	@XmlElement
	private java.lang.String SESION_ID;

	/** 
	 * TL_USER_CONNECT.CONECT_BEGIN_DT, 
	 * 사용자접속로그.접속_시작_일시
	 */
	@XmlElement
	private java.util.Date CONECT_BEGIN_DT;

	/** 
	 * TL_USER_CONNECT.CONECT_END_DT, 
	 * 사용자접속로그.접속_종료_일시
	 */
	@XmlElement
	private java.util.Date CONECT_END_DT;

	/**
	 * TL_USER_CONNECT.CONECT_LOG_NO, 
	 * 사용자접속로그.접속_로그_번호 
	 * @return
	 */
	@JsonProperty(value="CONECT_LOG_NO") 
	public java.lang.String getCONECT_LOG_NO() {
		return this.CONECT_LOG_NO;
	}
 
	 /**
	 * TL_USER_CONNECT.CONECT_LOG_NO, 
	 * 사용자접속로그.접속_로그_번호 
	 * @param conectLogNo
	 */
	public void setCONECT_LOG_NO(java.lang.String conectLogNo) {
		this.CONECT_LOG_NO = conectLogNo;
	}

	/**
	 * TL_USER_CONNECT.USER_NO, 
	 * 사용자접속로그.사용자_번호 
	 * @return
	 */
	@JsonProperty(value="USER_NO") 
	public java.lang.String getUSER_NO() {
		return this.USER_NO;
	}
 
	 /**
	 * TL_USER_CONNECT.USER_NO, 
	 * 사용자접속로그.사용자_번호 
	 * @param userNo
	 */
	public void setUSER_NO(java.lang.String userNo) {
		this.USER_NO = userNo;
	}

	/**
	 * TL_USER_CONNECT.SESION_ID, 
	 * 사용자접속로그.세션_ID 
	 * @return
	 */
	@JsonProperty(value="SESION_ID") 
	public java.lang.String getSESION_ID() {
		return this.SESION_ID;
	}
 
	 /**
	 * TL_USER_CONNECT.SESION_ID, 
	 * 사용자접속로그.세션_ID 
	 * @param sesionId
	 */
	public void setSESION_ID(java.lang.String sesionId) {
		this.SESION_ID = sesionId;
	}

	/**
	 * TL_USER_CONNECT.CONECT_BEGIN_DT, 
	 * 사용자접속로그.접속_시작_일시 
	 * @return
	 */
	@JsonProperty(value="CONECT_BEGIN_DT") 
	public java.util.Date getCONECT_BEGIN_DT() {
		return this.CONECT_BEGIN_DT;
	}
 
	 /**
	 * TL_USER_CONNECT.CONECT_BEGIN_DT, 
	 * 사용자접속로그.접속_시작_일시 
	 * @param conectBeginDt
	 */
	public void setCONECT_BEGIN_DT(java.util.Date conectBeginDt) {
		this.CONECT_BEGIN_DT = conectBeginDt;
	}

	/**
	 * TL_USER_CONNECT.CONECT_END_DT, 
	 * 사용자접속로그.접속_종료_일시 
	 * @return
	 */
	@JsonProperty(value="CONECT_END_DT") 
	public java.util.Date getCONECT_END_DT() {
		return this.CONECT_END_DT;
	}
 
	 /**
	 * TL_USER_CONNECT.CONECT_END_DT, 
	 * 사용자접속로그.접속_종료_일시 
	 * @param conectEndDt
	 */
	public void setCONECT_END_DT(java.util.Date conectEndDt) {
		this.CONECT_END_DT = conectEndDt;
	}

}
