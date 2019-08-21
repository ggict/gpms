
package kr.go.gg.gpms.srvydtalog.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 조사_자료_로그
 *
 * @Class Name : SrvyDtaLogVO.java
 * @Description : SrvyDtaLog VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyDtaLogVO extends BaseVO {

	public SrvyDtaLogVO() {
		super();
	}
	
	/** 
	 * TL_SRVY_DTA_LOG.PROCESS_LOG_NO, 
	 * 조사_자료_로그.처리_로그_번호
	 */
	@XmlElement
	private java.lang.String PROCESS_LOG_NO;

	/** 
	 * TL_SRVY_DTA_LOG.LOG_MSSAGE, 
	 * 조사_자료_로그.로그_메세지
	 */
	@XmlElement
	private java.lang.String LOG_MSSAGE;

	/** 
	 * TL_SRVY_DTA_LOG.CRTR_NO, 
	 * 조사_자료_로그.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TL_SRVY_DTA_LOG.BEGIN_DT, 
	 * 조사_자료_로그.시작_일시
	 */
	@XmlElement
	private java.sql.Date BEGIN_DT;

	/** 
	 * TL_SRVY_DTA_LOG.SRVY_NO, 
	 * 조사_자료_로그.조사_번호
	 */
	@XmlElement
	private java.lang.String SRVY_NO;

	/** 
	 * TL_SRVY_DTA_LOG.PROCESS_SE, 
	 * 조사_자료_로그.처리_구분
	 */
	@XmlElement
	private java.lang.String PROCESS_SE;

	/** 
	 * TL_SRVY_DTA_LOG.PROCESS_STTUS, 
	 * 조사_자료_로그.처리_상태
	 */
	@XmlElement
	private java.lang.String PROCESS_STTUS;

	/** 
	 * TL_SRVY_DTA_LOG.END_DT, 
	 * 조사_자료_로그.종료_일시
	 */
	@XmlElement
	private java.sql.Date END_DT;

	/**
	 * TL_SRVY_DTA_LOG.PROCESS_LOG_NO, 
	 * 조사_자료_로그.처리_로그_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="PROCESS_LOG_NO") 
	public java.lang.String getPROCESS_LOG_NO() {
		return this.PROCESS_LOG_NO;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.PROCESS_LOG_NO, 
	 * 조사_자료_로그.처리_로그_번호 값설정
	 * @param processLogNo
	 */
	public void setPROCESS_LOG_NO(java.lang.String processLogNo) {
		this.PROCESS_LOG_NO = processLogNo;
	}

	/**
	 * TL_SRVY_DTA_LOG.LOG_MSSAGE, 
	 * 조사_자료_로그.로그_메세지 값읽기
	 * @return
	 */
	@JsonProperty(value="LOG_MSSAGE") 
	public java.lang.String getLOG_MSSAGE() {
		return this.LOG_MSSAGE;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.LOG_MSSAGE, 
	 * 조사_자료_로그.로그_메세지 값설정
	 * @param logMssage
	 */
	public void setLOG_MSSAGE(java.lang.String logMssage) {
		this.LOG_MSSAGE = logMssage;
	}

	/**
	 * TL_SRVY_DTA_LOG.CRTR_NO, 
	 * 조사_자료_로그.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.CRTR_NO, 
	 * 조사_자료_로그.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TL_SRVY_DTA_LOG.BEGIN_DT, 
	 * 조사_자료_로그.시작_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="BEGIN_DT") 
	public java.sql.Date getBEGIN_DT() {
		return this.BEGIN_DT;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.BEGIN_DT, 
	 * 조사_자료_로그.시작_일시 값설정
	 * @param beginDt
	 */
	public void setBEGIN_DT(java.sql.Date beginDt) {
		this.BEGIN_DT = beginDt;
	}

	/**
	 * TL_SRVY_DTA_LOG.SRVY_NO, 
	 * 조사_자료_로그.조사_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NO") 
	public java.lang.String getSRVY_NO() {
		return this.SRVY_NO;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.SRVY_NO, 
	 * 조사_자료_로그.조사_번호 값설정
	 * @param srvyNo
	 */
	public void setSRVY_NO(java.lang.String srvyNo) {
		this.SRVY_NO = srvyNo;
	}

	/**
	 * TL_SRVY_DTA_LOG.PROCESS_SE, 
	 * 조사_자료_로그.처리_구분 값읽기
	 * @return
	 */
	@JsonProperty(value="PROCESS_SE") 
	public java.lang.String getPROCESS_SE() {
		return this.PROCESS_SE;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.PROCESS_SE, 
	 * 조사_자료_로그.처리_구분 값설정
	 * @param processSe
	 */
	public void setPROCESS_SE(java.lang.String processSe) {
		this.PROCESS_SE = processSe;
	}

	/**
	 * TL_SRVY_DTA_LOG.PROCESS_STTUS, 
	 * 조사_자료_로그.처리_상태 값읽기
	 * @return
	 */
	@JsonProperty(value="PROCESS_STTUS") 
	public java.lang.String getPROCESS_STTUS() {
		return this.PROCESS_STTUS;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.PROCESS_STTUS, 
	 * 조사_자료_로그.처리_상태 값설정
	 * @param processSttus
	 */
	public void setPROCESS_STTUS(java.lang.String processSttus) {
		this.PROCESS_STTUS = processSttus;
	}

	/**
	 * TL_SRVY_DTA_LOG.END_DT, 
	 * 조사_자료_로그.종료_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="END_DT") 
	public java.sql.Date getEND_DT() {
		return this.END_DT;
	}
 
	 /**
	 * TL_SRVY_DTA_LOG.END_DT, 
	 * 조사_자료_로그.종료_일시 값설정
	 * @param endDt
	 */
	public void setEND_DT(java.sql.Date endDt) {
		this.END_DT = endDt;
	}

}
