
package kr.go.gg.gpms.mvroad.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CELL_SECT
 *
 * @Class Name : CellSectVO.java
 * @Description : CellSect VO class
 * @Modification Information
 *
 * @author kmh
 * @since 2017-08-22
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class MvRoadVO extends BaseVO {

	public MvRoadVO() {
		super();
	}


	@XmlElement
		private java.lang.String SPCL_NO;
	@XmlElement
		private java.lang.String BSNS_NM;
	@XmlElement
		private java.lang.String ROUTE_CODE;
	@XmlElement
		private java.lang.String CNTRWK_SCTN;
	@XmlElement
		private java.lang.String TRACK_CO;
	@XmlElement
		private java.lang.String BSNS_QY;
	@XmlElement
		private java.lang.String TOT_WCT;
	@XmlElement
		private java.lang.String BSNS_BEGIN_YEAR;
	@XmlElement
		private java.lang.String BSNS_END_YEAR;
	@XmlElement
		private java.lang.String COMPET_DE;
	@XmlElement
		private java.lang.String BSNS_OPERTN_MAN;
	@XmlElement
		private java.lang.String STRTPT_NM;
	@XmlElement
		private java.lang.String ENDPT_NM;
	@XmlElement
		private java.lang.String MAJOR_PASAGEPAPR;
	@XmlElement
		private java.lang.String PAV_MTRQLT;
	@XmlElement
		private java.lang.String PAV_THICK_ASCON;
	@XmlElement
		private java.lang.String PAV_THICK_BASE;
	@XmlElement
		private java.lang.String PAV_THICK_ASSTNBASE;
	@XmlElement
		private java.lang.String TUNNEL_KND;
	@XmlElement
		private java.lang.String TUNNEL_CO;
	@XmlElement
		private java.lang.String TUNNEL_LEN;
	@XmlElement
		private java.lang.String BRIDGE_KND;
	@XmlElement
		private java.lang.String BRIDGE_CO;
	@XmlElement
		private java.lang.String BRIDGE_LEN;

	public java.lang.String getSPCL_NO() {
		return SPCL_NO;
	}
	public void setSPCL_NO(java.lang.String sPCL_NO) {
		SPCL_NO = sPCL_NO;
	}
	public java.lang.String getBSNS_NM() {
		return BSNS_NM;
	}
	public void setBSNS_NM(java.lang.String bSNS_NM) {
		BSNS_NM = bSNS_NM;
	}
	public java.lang.String getROUTE_CODE() {
		return ROUTE_CODE;
	}
	public void setROUTE_CODE(java.lang.String rOUTE_CODE) {
		ROUTE_CODE = rOUTE_CODE;
	}
	public java.lang.String getCNTRWK_SCTN() {
		return CNTRWK_SCTN;
	}
	public void setCNTRWK_SCTN(java.lang.String cNTRWK_SCTN) {
		CNTRWK_SCTN = cNTRWK_SCTN;
	}
	public java.lang.String getTRACK_CO() {
		return TRACK_CO;
	}
	public void setTRACK_CO(java.lang.String tRACK_CO) {
		TRACK_CO = tRACK_CO;
	}
	public java.lang.String getBSNS_QY() {
		return BSNS_QY;
	}
	public void setBSNS_QY(java.lang.String bSNS_QY) {
		BSNS_QY = bSNS_QY;
	}
	public java.lang.String getTOT_WCT() {
		return TOT_WCT;
	}
	public void setTOT_WCT(java.lang.String tOT_WCT) {
		TOT_WCT = tOT_WCT;
	}
	public java.lang.String getBSNS_BEGIN_YEAR() {
		return BSNS_BEGIN_YEAR;
	}
	public void setBSNS_BEGIN_YEAR(java.lang.String bSNS_BEGIN_YEAR) {
		BSNS_BEGIN_YEAR = bSNS_BEGIN_YEAR;
	}
	public java.lang.String getBSNS_END_YEAR() {
		return BSNS_END_YEAR;
	}
	public void setBSNS_END_YEAR(java.lang.String bSNS_END_YEAR) {
		BSNS_END_YEAR = bSNS_END_YEAR;
	}
	public java.lang.String getCOMPET_DE() {
		return COMPET_DE;
	}
	public void setCOMPET_DE(java.lang.String cOMPET_DE) {
		COMPET_DE = cOMPET_DE;
	}
	public java.lang.String getBSNS_OPERTN_MAN() {
		return BSNS_OPERTN_MAN;
	}
	public void setBSNS_OPERTN_MAN(java.lang.String bSNS_OPERTN_MAN) {
		BSNS_OPERTN_MAN = bSNS_OPERTN_MAN;
	}
	public java.lang.String getSTRTPT_NM() {
		return STRTPT_NM;
	}
	public void setSTRTPT_NM(java.lang.String sTRTPT_NM) {
		STRTPT_NM = sTRTPT_NM;
	}
	public java.lang.String getENDPT_NM() {
		return ENDPT_NM;
	}
	public void setENDPT_NM(java.lang.String eNDPT_NM) {
		ENDPT_NM = eNDPT_NM;
	}
	public java.lang.String getMAJOR_PASAGEPAPR() {
		return MAJOR_PASAGEPAPR;
	}
	public void setMAJOR_PASAGEPAPR(java.lang.String mAJOR_PASAGEPAPR) {
		MAJOR_PASAGEPAPR = mAJOR_PASAGEPAPR;
	}
	public java.lang.String getPAV_MTRQLT() {
		return PAV_MTRQLT;
	}
	public void setPAV_MTRQLT(java.lang.String pAV_MTRQLT) {
		PAV_MTRQLT = pAV_MTRQLT;
	}
	public java.lang.String getPAV_THICK_ASCON() {
		return PAV_THICK_ASCON;
	}
	public void setPAV_THICK_ASCON(java.lang.String pAV_THICK_ASCON) {
		PAV_THICK_ASCON = pAV_THICK_ASCON;
	}
	public java.lang.String getPAV_THICK_BASE() {
		return PAV_THICK_BASE;
	}
	public void setPAV_THICK_BASE(java.lang.String pAV_THICK_BASE) {
		PAV_THICK_BASE = pAV_THICK_BASE;
	}
	public java.lang.String getPAV_THICK_ASSTNBASE() {
		return PAV_THICK_ASSTNBASE;
	}
	public void setPAV_THICK_ASSTNBASE(java.lang.String pAV_THICK_ASSTNBASE) {
		PAV_THICK_ASSTNBASE = pAV_THICK_ASSTNBASE;
	}
	public java.lang.String getTUNNEL_KND() {
		return TUNNEL_KND;
	}
	public void setTUNNEL_KND(java.lang.String tUNNEL_KND) {
		TUNNEL_KND = tUNNEL_KND;
	}
	public java.lang.String getTUNNEL_CO() {
		return TUNNEL_CO;
	}
	public void setTUNNEL_CO(java.lang.String tUNNEL_CO) {
		TUNNEL_CO = tUNNEL_CO;
	}
	public java.lang.String getTUNNEL_LEN() {
		return TUNNEL_LEN;
	}
	public void setTUNNEL_LEN(java.lang.String tUNNEL_LEN) {
		TUNNEL_LEN = tUNNEL_LEN;
	}
	public java.lang.String getBRIDGE_KND() {
		return BRIDGE_KND;
	}
	public void setBRIDGE_KND(java.lang.String bRIDGE_KND) {
		BRIDGE_KND = bRIDGE_KND;
	}
	public java.lang.String getBRIDGE_CO() {
		return BRIDGE_CO;
	}
	public void setBRIDGE_CO(java.lang.String bRIDGE_CO) {
		BRIDGE_CO = bRIDGE_CO;
	}
	public java.lang.String getBRIDGE_LEN() {
		return BRIDGE_LEN;
	}
	public void setBRIDGE_LEN(java.lang.String bRIDGE_LEN) {
		BRIDGE_LEN = bRIDGE_LEN;
	}



}
