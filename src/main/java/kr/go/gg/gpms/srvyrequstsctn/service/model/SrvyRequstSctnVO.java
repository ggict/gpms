
package kr.go.gg.gpms.srvyrequstsctn.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 조사_요청_구간
 *
 * @Class Name : SrvyDtaLogVO.java
 * @Description : SrvyDtaLog VO class
 * @Modification Information
 *
 * @author 
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyRequstSctnVO extends BaseVO {

	public SrvyRequstSctnVO() {
		super();
	}
	
	/** 
//	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간.조사_요청_구간_번호
	 */
	@XmlElement
	private java.lang.String SRVY_REQUST_SCTN_NO;

	/** 
	 * TN_SRVY_REQUST_SCTN.SRVY_NM, 
	 * 조사_요청_구간.조사_명
	 */
	@XmlElement
	private java.lang.String SRVY_NM;

	/** 
	 * TN_SRVY_REQUST_SCTN.SRVY_CN, 
	 * 조사_요청_구간.조사_내용
	 */
	@XmlElement
	private java.lang.String SRVY_CN;

	/** 
	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_DE, 
	 * 조사_요청_구간.조사_요청_일자
	 */
	@XmlElement
	private java.lang.String SRVY_REQUST_DE;
	
	@XmlElement
	private java.lang.String SRVY_REQUST_DE_STRT;
	
	@XmlElement
	private java.lang.String SRVY_REQUST_DE_ENDT;

	/** 
	 * TN_SRVY_REQUST_SCTN.ROUTE_CODE, 
	 * 조사_요청_구간.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;
	
	@XmlElement
	private java.lang.String ROAD_NAME;

	/** 
	 * TN_SRVY_REQUST_SCTN.DIRECT_CODE, 
	 * 조사_요청_구간.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;
	
	@XmlElement
    private java.lang.String DIRECT_NM;

	/** 
	 * TN_SRVY_REQUST_SCTN.TRACK, 
	 * 조사_요청_구간.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_SRVY_REQUST_SCTN.STRTPT, 
	 * 조사_요청_구간.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;
	
	/** 
	 * TN_SRVY_REQUST_SCTN.ENDPT, 
	 * 조사_요청_구간.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;
	
	/** 
	 * TN_SRVY_REQUST_SCTN.UPDUSR_NO, 
	 * 조사_요청_구간.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;
	
	/** 
	 * TN_SRVY_REQUST_SCTN.UPDT_DT, 
	 * 조사_요청_구간.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간.조사_요청_구간_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_REQUST_SCTN_NO") 
	public java.lang.String getSRVY_REQUST_SCTN_NO() {
		return this.SRVY_REQUST_SCTN_NO;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간.조사_요청_구간_번호 값설정
	 * @param srvyRequstSctnNo
	 */
	public void setSRVY_REQUST_SCTN_NO(java.lang.String srvyRequstSctnNo) {
		this.SRVY_REQUST_SCTN_NO = srvyRequstSctnNo;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_NM, 
	 * 조사_요청_구간.조사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NM") 
	public java.lang.String getSRVY_NM() {
		return this.SRVY_NM;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN.SRVY_NM, 
	 * 조사_요청_구간.조사_명 값설정
	 * @param srvyNm
	 */
	public void setSRVY_NM(java.lang.String srvyNm) {
		this.SRVY_NM = srvyNm;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_CN, 
	 * 조사_요청_구간.조사_내용 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_CN") 
	public java.lang.String getSRVY_CN() {
		return this.SRVY_CN;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_CN, 
	 * 조사_요청_구간.조사_내용 값설정
	 * @param srvyCn
	 */
	public void setSRVY_CN(java.lang.String srvyCn) {
		this.SRVY_CN = srvyCn;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_DE, 
	 * 조사_요청_구간.조사_요청_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_REQUST_DE") 
	public java.lang.String getSRVY_REQUST_DE() {
		return this.SRVY_REQUST_DE;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.SRVY_REQUST_DE, 
	 * 조사_요청_구간.조사_요청_일자 값설정
	 * @param srvyRequstDe
	 */
	public void setSRVY_REQUST_DE(java.lang.String srvyRequstDe) {
		this.SRVY_REQUST_DE = srvyRequstDe;
	}
	
	@JsonProperty(value="SRVY_REQUST_DE_STRT") 
	public java.lang.String getSRVY_REQUST_DE_STRT() {
		return this.SRVY_REQUST_DE_STRT;
	}
	
	public void setSRVY_REQUST_DE_STRT(java.lang.String srvyRequstDeStrt) {
		this.SRVY_REQUST_DE_STRT = srvyRequstDeStrt;
	}
	
	@JsonProperty(value="SRVY_REQUST_DE_ENDT") 
	public java.lang.String getSRVY_REQUST_DE_ENDT() {
		return this.SRVY_REQUST_DE_ENDT;
	}
	
	public void setSRVY_REQUST_DE_ENDT(java.lang.String srvyRequstDeEndt) {
		this.SRVY_REQUST_DE_ENDT = srvyRequstDeEndt;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.ROUTE_CODE, 
	 * 조사_요청_구간.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.ROUTE_CODE, 
	 * 조사_요청_구간.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}
	
	@JsonProperty(value="ROAD_NAME") 
	public java.lang.String getROAD_NAME() {
		return this.ROAD_NAME;
	}
	
	public void setROAD_NAME(java.lang.String roadName) {
		this.ROAD_NAME = roadName;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.DIRECT_CODE, 
	 * 조사_요청_구간.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.DIRECT_CODE, 
	 * 조사_요청_구간.행선_코드 값설정
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
	}
	
	@JsonProperty(value="DIRECT_NM") 
	public java.lang.String getDIRECT_NM() {
		return this.DIRECT_NM;
	}
	
	public void setDIRECT_NM(java.lang.String directNm) {
		this.DIRECT_NM = directNm;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.TRACK, 
	 * 조사_요청_구간.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.TRACK, 
	 * 조사_요청_구간.차로 값설정
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.STRTPT, 
	 * 조사_요청_구간.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.STRTPT, 
	 * 조사_요청_구간.시점 값설정
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.ENDPT, 
	 * 조사_요청_구간.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.ENDPT, 
	 * 조사_요청_구간.종점 값설정
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.UPDUSR_NO, 
	 * 조사_요청_구간.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.UPDUSR_NO, 
	 * 조사_요청_구간.종점 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.UPDT_DT, 
	 * 조사_요청_구간.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
	
	/**
	 * TN_SRVY_REQUST_SCTN.UPDT_DT, 
	 * 조사_요청_구간.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}


}
