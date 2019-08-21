
package kr.go.gg.gpms.smcalcpredct.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 집계_산정_예측
 *
 * @Class Name : SmCalcPredctVO.java
 * @Description : SmCalcPredct VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SmCalcPredctVO extends BaseVO {

	public SmCalcPredctVO() {
		super();
	}
	
	/** 
	 * TN_SM_CALC_PREDCT.CALC_YEAR, 
	 * 집계_산정_예측.산정_년도
	 */
	@XmlElement
	private java.lang.String CALC_YEAR;

	/** 
	 * TN_SM_CALC_PREDCT.CALC_MT, 
	 * 집계_산정_예측.산정_월
	 */
	@XmlElement
	private java.lang.String CALC_MT;

	/** 
	 * TN_SM_CALC_PREDCT.PREDCT_YEAR, 
	 * 집계_산정_예측.예측_년도
	 */
	@XmlElement
	private java.lang.String PREDCT_YEAR;

	/** 
	 * TN_SM_CALC_PREDCT.PREDCT_MT, 
	 * 집계_산정_예측.예측_월
	 */
	@XmlElement
	private java.lang.String PREDCT_MT;

	/** 
	 * TN_SM_CALC_PREDCT.ROUTE_CODE, 
	 * 집계_산정_예측.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_SM_CALC_PREDCT.DIRECT_CODE, 
	 * 집계_산정_예측.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TN_SM_CALC_PREDCT.TRACK, 
	 * 집계_산정_예측.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_SM_CALC_PREDCT.STRTPT, 
	 * 집계_산정_예측.시점
	 */
	@XmlElement
	private java.math.BigDecimal STRTPT;

	/** 
	 * TN_SM_CALC_PREDCT.ENDPT, 
	 * 집계_산정_예측.종점
	 */
	@XmlElement
	private java.math.BigDecimal ENDPT;

	/** 
	 * TN_SM_CALC_PREDCT.AC_IDX, 
	 * 집계_산정_예측.거북등균열_지수
	 */
	@XmlElement
	private java.math.BigDecimal AC_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.LC_IDX, 
	 * 집계_산정_예측.종방향균열_지수
	 */
	@XmlElement
	private java.math.BigDecimal LC_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.TC_IDX, 
	 * 집계_산정_예측.횡방향균열_지수
	 */
	@XmlElement
	private java.math.BigDecimal TC_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.BC_IDX, 
	 * 집계_산정_예측.블럭균열_지수
	 */
	@XmlElement
	private java.math.BigDecimal BC_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.PTCHG_IDX, 
	 * 집계_산정_예측.패칭_지수
	 */
	@XmlElement
	private java.math.BigDecimal PTCHG_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.POTHOLE_IDX, 
	 * 집계_산정_예측.포트홀_지수
	 */
	@XmlElement
	private java.math.BigDecimal POTHOLE_IDX;

	/** 
	 * TN_SM_CALC_PREDCT.RCI, 
	 * 집계_산정_예측.표면_조도_지수
	 */
	@XmlElement
	private java.math.BigDecimal RCI;

	/** 
	 * TN_SM_CALC_PREDCT.SCR, 
	 * 집계_산정_예측.표면_상태_지수
	 */
	@XmlElement
	private java.math.BigDecimal SCR;

	/** 
	 * TN_SM_CALC_PREDCT.GPCI, 
	 * 집계_산정_예측.GPCI
	 */
	@XmlElement
	private java.math.BigDecimal GPCI;

	/** 
	 * TN_SM_CALC_PREDCT.SRVY_YEAR, 
	 * 집계_산정_예측.조사_년도
	 */
	@XmlElement
	private java.lang.String SRVY_YEAR;

	/** 
	 * TN_SM_CALC_PREDCT.SRVY_MT, 
	 * 집계_산정_예측.조사_월
	 */
	@XmlElement
	private java.lang.String SRVY_MT;

	/** 
	 * TN_SM_CALC_PREDCT.PREDCT_NO, 
	 * 집계_산정_예측.예측_번호
	 */
	@XmlElement
	private java.math.BigDecimal PREDCT_NO;

	/** 
	 * TN_SM_CALC_PREDCT.RD_IDX, 
	 * 집계_산정_예측.소성변형지수
	 */
	@XmlElement
	private java.math.BigDecimal RD_IDX;

	/**
	 * TN_SM_CALC_PREDCT.CALC_YEAR, 
	 * 집계_산정_예측.산정_년도 
	 * @return
	 */
	@JsonProperty(value="CALC_YEAR") 
	public java.lang.String getCALC_YEAR() {
		return this.CALC_YEAR;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.CALC_YEAR, 
	 * 집계_산정_예측.산정_년도 
	 * @param calcYear
	 */
	public void setCALC_YEAR(java.lang.String calcYear) {
		this.CALC_YEAR = calcYear;
	}

	/**
	 * TN_SM_CALC_PREDCT.CALC_MT, 
	 * 집계_산정_예측.산정_월 
	 * @return
	 */
	@JsonProperty(value="CALC_MT") 
	public java.lang.String getCALC_MT() {
		return this.CALC_MT;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.CALC_MT, 
	 * 집계_산정_예측.산정_월 
	 * @param calcMt
	 */
	public void setCALC_MT(java.lang.String calcMt) {
		this.CALC_MT = calcMt;
	}

	/**
	 * TN_SM_CALC_PREDCT.PREDCT_YEAR, 
	 * 집계_산정_예측.예측_년도 
	 * @return
	 */
	@JsonProperty(value="PREDCT_YEAR") 
	public java.lang.String getPREDCT_YEAR() {
		return this.PREDCT_YEAR;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.PREDCT_YEAR, 
	 * 집계_산정_예측.예측_년도 
	 * @param predctYear
	 */
	public void setPREDCT_YEAR(java.lang.String predctYear) {
		this.PREDCT_YEAR = predctYear;
	}

	/**
	 * TN_SM_CALC_PREDCT.PREDCT_MT, 
	 * 집계_산정_예측.예측_월 
	 * @return
	 */
	@JsonProperty(value="PREDCT_MT") 
	public java.lang.String getPREDCT_MT() {
		return this.PREDCT_MT;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.PREDCT_MT, 
	 * 집계_산정_예측.예측_월 
	 * @param predctMt
	 */
	public void setPREDCT_MT(java.lang.String predctMt) {
		this.PREDCT_MT = predctMt;
	}

	/**
	 * TN_SM_CALC_PREDCT.ROUTE_CODE, 
	 * 집계_산정_예측.노선_코드 
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.ROUTE_CODE, 
	 * 집계_산정_예측.노선_코드 
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_SM_CALC_PREDCT.DIRECT_CODE, 
	 * 집계_산정_예측.행선_코드 
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.DIRECT_CODE, 
	 * 집계_산정_예측.행선_코드 
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
	}

	/**
	 * TN_SM_CALC_PREDCT.TRACK, 
	 * 집계_산정_예측.차로 
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.TRACK, 
	 * 집계_산정_예측.차로 
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * TN_SM_CALC_PREDCT.STRTPT, 
	 * 집계_산정_예측.시점 
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.math.BigDecimal getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.STRTPT, 
	 * 집계_산정_예측.시점 
	 * @param strtpt
	 */
	public void setSTRTPT(java.math.BigDecimal strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * TN_SM_CALC_PREDCT.ENDPT, 
	 * 집계_산정_예측.종점 
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.math.BigDecimal getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.ENDPT, 
	 * 집계_산정_예측.종점 
	 * @param endpt
	 */
	public void setENDPT(java.math.BigDecimal endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * TN_SM_CALC_PREDCT.AC_IDX, 
	 * 집계_산정_예측.거북등균열_지수 
	 * @return
	 */
	@JsonProperty(value="AC_IDX") 
	public java.math.BigDecimal getAC_IDX() {
		return this.AC_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.AC_IDX, 
	 * 집계_산정_예측.거북등균열_지수 
	 * @param acIdx
	 */
	public void setAC_IDX(java.math.BigDecimal acIdx) {
		this.AC_IDX = acIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.LC_IDX, 
	 * 집계_산정_예측.종방향균열_지수 
	 * @return
	 */
	@JsonProperty(value="LC_IDX") 
	public java.math.BigDecimal getLC_IDX() {
		return this.LC_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.LC_IDX, 
	 * 집계_산정_예측.종방향균열_지수 
	 * @param lcIdx
	 */
	public void setLC_IDX(java.math.BigDecimal lcIdx) {
		this.LC_IDX = lcIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.TC_IDX, 
	 * 집계_산정_예측.횡방향균열_지수 
	 * @return
	 */
	@JsonProperty(value="TC_IDX") 
	public java.math.BigDecimal getTC_IDX() {
		return this.TC_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.TC_IDX, 
	 * 집계_산정_예측.횡방향균열_지수 
	 * @param tcIdx
	 */
	public void setTC_IDX(java.math.BigDecimal tcIdx) {
		this.TC_IDX = tcIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.BC_IDX, 
	 * 집계_산정_예측.블럭균열_지수 
	 * @return
	 */
	@JsonProperty(value="BC_IDX") 
	public java.math.BigDecimal getBC_IDX() {
		return this.BC_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.BC_IDX, 
	 * 집계_산정_예측.블럭균열_지수 
	 * @param bcIdx
	 */
	public void setBC_IDX(java.math.BigDecimal bcIdx) {
		this.BC_IDX = bcIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.PTCHG_IDX, 
	 * 집계_산정_예측.패칭_지수 
	 * @return
	 */
	@JsonProperty(value="PTCHG_IDX") 
	public java.math.BigDecimal getPTCHG_IDX() {
		return this.PTCHG_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.PTCHG_IDX, 
	 * 집계_산정_예측.패칭_지수 
	 * @param ptchgIdx
	 */
	public void setPTCHG_IDX(java.math.BigDecimal ptchgIdx) {
		this.PTCHG_IDX = ptchgIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.POTHOLE_IDX, 
	 * 집계_산정_예측.포트홀_지수 
	 * @return
	 */
	@JsonProperty(value="POTHOLE_IDX") 
	public java.math.BigDecimal getPOTHOLE_IDX() {
		return this.POTHOLE_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.POTHOLE_IDX, 
	 * 집계_산정_예측.포트홀_지수 
	 * @param potholeIdx
	 */
	public void setPOTHOLE_IDX(java.math.BigDecimal potholeIdx) {
		this.POTHOLE_IDX = potholeIdx;
	}

	/**
	 * TN_SM_CALC_PREDCT.RCI, 
	 * 집계_산정_예측.표면_조도_지수 
	 * @return
	 */
	@JsonProperty(value="RCI") 
	public java.math.BigDecimal getRCI() {
		return this.RCI;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.RCI, 
	 * 집계_산정_예측.표면_조도_지수 
	 * @param rci
	 */
	public void setRCI(java.math.BigDecimal rci) {
		this.RCI = rci;
	}

	/**
	 * TN_SM_CALC_PREDCT.SCR, 
	 * 집계_산정_예측.표면_상태_지수 
	 * @return
	 */
	@JsonProperty(value="SCR") 
	public java.math.BigDecimal getSCR() {
		return this.SCR;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.SCR, 
	 * 집계_산정_예측.표면_상태_지수 
	 * @param scr
	 */
	public void setSCR(java.math.BigDecimal scr) {
		this.SCR = scr;
	}

	/**
	 * TN_SM_CALC_PREDCT.GPCI, 
	 * 집계_산정_예측.GPCI 
	 * @return
	 */
	@JsonProperty(value="GPCI") 
	public java.math.BigDecimal getGPCI() {
		return this.GPCI;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.GPCI, 
	 * 집계_산정_예측.GPCI 
	 * @param gpci
	 */
	public void setGPCI(java.math.BigDecimal gpci) {
		this.GPCI = gpci;
	}

	/**
	 * TN_SM_CALC_PREDCT.SRVY_YEAR, 
	 * 집계_산정_예측.조사_년도 
	 * @return
	 */
	@JsonProperty(value="SRVY_YEAR") 
	public java.lang.String getSRVY_YEAR() {
		return this.SRVY_YEAR;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.SRVY_YEAR, 
	 * 집계_산정_예측.조사_년도 
	 * @param srvyYear
	 */
	public void setSRVY_YEAR(java.lang.String srvyYear) {
		this.SRVY_YEAR = srvyYear;
	}

	/**
	 * TN_SM_CALC_PREDCT.SRVY_MT, 
	 * 집계_산정_예측.조사_월 
	 * @return
	 */
	@JsonProperty(value="SRVY_MT") 
	public java.lang.String getSRVY_MT() {
		return this.SRVY_MT;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.SRVY_MT, 
	 * 집계_산정_예측.조사_월 
	 * @param srvyMt
	 */
	public void setSRVY_MT(java.lang.String srvyMt) {
		this.SRVY_MT = srvyMt;
	}

	/**
	 * TN_SM_CALC_PREDCT.PREDCT_NO, 
	 * 집계_산정_예측.예측_번호 
	 * @return
	 */
	@JsonProperty(value="PREDCT_NO") 
	public java.math.BigDecimal getPREDCT_NO() {
		return this.PREDCT_NO;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.PREDCT_NO, 
	 * 집계_산정_예측.예측_번호 
	 * @param predctNo
	 */
	public void setPREDCT_NO(java.math.BigDecimal predctNo) {
		this.PREDCT_NO = predctNo;
	}

	/**
	 * TN_SM_CALC_PREDCT.RD_IDX, 
	 * 집계_산정_예측.소성변형지수 
	 * @return
	 */
	@JsonProperty(value="RD_IDX") 
	public java.math.BigDecimal getRD_IDX() {
		return this.RD_IDX;
	}
 
	 /**
	 * TN_SM_CALC_PREDCT.RD_IDX, 
	 * 집계_산정_예측.소성변형지수 
	 * @param rdIdx
	 */
	public void setRD_IDX(java.math.BigDecimal rdIdx) {
		this.RD_IDX = rdIdx;
	}

}
