
package kr.go.gg.gpms.sysconnect.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 시스템접속로그
 *
 * @Class Name : SysConnectVO.java
 * @Description : SysConnect VO class
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
public class SysConnectVO extends BaseVO {

	public SysConnectVO() {
		super();
	}
	
	/** 
	 * TL_SYS_CONNECT.CONECT_LOG_NO, 
	 * 시스템접속로그.접속_로그_번호
	 */
	@XmlElement
	private String CONECT_LOG_NO;

	/** 
	 * TL_SYS_CONNECT.USER_NO, 
	 * 시스템접속로그.사용자_번호
	 */
	@XmlElement
	private String USER_NO;

	/** 
	 * TL_SYS_CONNECT.MENU_ID, 
	 * 시스템접속로그.메뉴_ID
	 */
	@XmlElement
	private java.lang.String MENU_ID;

	/** 
	 * TL_SYS_CONNECT.SESION_ID, 
	 * 시스템접속로그.세션_ID
	 */
	@XmlElement
	private java.lang.String SESION_ID;

	/** 
	 * TL_SYS_CONNECT.CREAT_DT, 
	 * 시스템접속로그.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/**
	 * TL_SYS_CONNECT.CONECT_LOG_NO, 
	 * 시스템접속로그.접속_로그_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CONECT_LOG_NO") 
	public String getCONECT_LOG_NO() {
		return this.CONECT_LOG_NO;
	}
 
	 /**
	 * TL_SYS_CONNECT.CONECT_LOG_NO, 
	 * 시스템접속로그.접속_로그_번호 값설정
	 * @param conectLogNo
	 */
	public void setCONECT_LOG_NO(String conectLogNo) {
		this.CONECT_LOG_NO = conectLogNo;
	}

	/**
	 * TL_SYS_CONNECT.USER_NO, 
	 * 시스템접속로그.사용자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_NO") 
	public String getUSER_NO() {
		return this.USER_NO;
	}
 
	 /**
	 * TL_SYS_CONNECT.USER_NO, 
	 * 시스템접속로그.사용자_번호 값설정
	 * @param userNo
	 */
	public void setUSER_NO(String userNo) {
		this.USER_NO = userNo;
	}

	/**
	 * TL_SYS_CONNECT.MENU_ID, 
	 * 시스템접속로그.메뉴_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="MENU_ID") 
	public java.lang.String getMENU_ID() {
		return this.MENU_ID;
	}
 
	 /**
	 * TL_SYS_CONNECT.MENU_ID, 
	 * 시스템접속로그.메뉴_ID 값설정
	 * @param menuId
	 */
	public void setMENU_ID(java.lang.String menuId) {
		this.MENU_ID = menuId;
	}

	/**
	 * TL_SYS_CONNECT.SESION_ID, 
	 * 시스템접속로그.세션_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="SESION_ID") 
	public java.lang.String getSESION_ID() {
		return this.SESION_ID;
	}
 
	 /**
	 * TL_SYS_CONNECT.SESION_ID, 
	 * 시스템접속로그.세션_ID 값설정
	 * @param sesionId
	 */
	public void setSESION_ID(java.lang.String sesionId) {
		this.SESION_ID = sesionId;
	}

	/**
	 * TL_SYS_CONNECT.CREAT_DT, 
	 * 시스템접속로그.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TL_SYS_CONNECT.CREAT_DT, 
	 * 시스템접속로그.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

}
