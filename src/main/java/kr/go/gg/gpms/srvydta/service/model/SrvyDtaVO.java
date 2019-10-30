
package kr.go.gg.gpms.srvydta.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelDefaultVO;
/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelVO.java
 * @Description : SrvyDtaExcel VO class
 * @Modification Information
 *
 * @author antihyun2@gmail.com
 * @since 2019-10-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyDtaVO extends SrvyDtaExcelDefaultVO {

	public SrvyDtaVO() {
		super();
	}
	
	@XmlElement
	private String SRVY_YEAR;
	
	@XmlElement
	private String SRVY_MT;
	
	@XmlElement
	private String ROUTE_CODE;
	
	@XmlElement
	private String DIRECT_CODE;
	
	@XmlElement
	private String TRACK;
	
	@XmlElement
	private String STRTPT;
	
	@XmlElement
	private String ENDPT;
	
	@XmlElement
	private String IRI_VAL;
	
	@XmlElement
	private String RD_VAL;
	
	@XmlElement
	private String VRTCAL_CR;
	
	@XmlElement
	private String HRZNTAL_CR;
	
	@XmlElement
	private String CNSTRCT_JOINT_CR;
	
	@XmlElement
	private String TRTS_BAC_CR;
	
	@XmlElement
	private String PTCHG_CR;
	
	@XmlElement
	private String POTHOLE_CR;
	
	@XmlElement
	private String CR_VAL;
	
	@XmlElement
	private String RDSRFC_IMG_FILE_NM_1;
	
	@XmlElement
	private String RDSRFC_IMG_FILE_NM_2;
	
	@XmlElement
	private String FRNT_IMG_FILE_NM;
	
	@XmlElement
	private String MEMO;
	
	@XmlElement
	private String SRVY_DE;
	
	@XmlElement
	private String SRVY_KND;
	
	@XmlElement
	private String TRACE1_LA;
	
	@XmlElement
	private String TRACE1_LO;
	
	@XmlElement
	private String TRACE2_LA;
	
	@XmlElement
	private String TRACE2_LO;
	
	@XmlElement
	private String SRVY_NM;
	
	@XmlElement
	private String EXMNR_NM;
	
	@XmlElement
	private String SCTN_STRTPT_DC;
	
	@XmlElement
	private String SCTN_ENDPT_DC;
	
	@XmlElement
	private String ROAD_NM;
	
	@XmlElement
	private String BLOCK_CR;
	
	@XmlElement
	private String CR_LT;
	
	@XmlElement
	private String CR_WID;
	
	@XmlElement
	private String AC_LOW;
	
	@XmlElement
	private String AC_MED;
	
	@XmlElement
	private String AC_HI;
	
	@XmlElement
	private String BLOCK_CR_LOW;
	
	@XmlElement
	private String BLOCK_CR_MED;
	
	@XmlElement
	private String BLOCK_CR_HI;
	
	@XmlElement
	private String LC_LOW;
	
	@XmlElement
	private String LC_MED;
	
	@XmlElement
	private String LC_HI;
	
	@XmlElement
	private String TC_LOW;
	
	@XmlElement
	private String TC_MED;
	
	@XmlElement
	private String TC_HI;
	
	@XmlElement
	private String RD_LOW;
	
	@XmlElement
	private String RD_MED;
	
	@XmlElement
	private String RD_HI;
	
	@XmlElement
	private String AC_IDX;
	
	@XmlElement
	private String BC_IDX;
	
	@XmlElement
	private String LC_IDX;
	
	@XmlElement
	private String TC_IDX;
	
	@XmlElement
	private String PTCHG_IDX;
	
	@XmlElement
	private String POTHOLE_IDX;
	
	@XmlElement
	private String RD_IDX;
	
	@XmlElement
	private String RCI;
	
	@XmlElement
	private String SCR;
	
	@XmlElement
	private String GPCI;
	
	@XmlElement
	private String NHPCI;
	
	@XmlElement
	private String SPI;
	
	@XmlElement
	private String XCR;

	public String getSRVY_YEAR() {
		return SRVY_YEAR;
	}

	public void setSRVY_YEAR(String sRVY_YEAR) {
		SRVY_YEAR = sRVY_YEAR;
	}

	public String getSRVY_MT() {
		return SRVY_MT;
	}

	public void setSRVY_MT(String sRVY_MT) {
		SRVY_MT = sRVY_MT;
	}

	public String getROUTE_CODE() {
		return ROUTE_CODE;
	}

	public void setROUTE_CODE(String rOUTE_CODE) {
		ROUTE_CODE = rOUTE_CODE;
	}

	public String getDIRECT_CODE() {
		return DIRECT_CODE;
	}

	public void setDIRECT_CODE(String dIRECT_CODE) {
		DIRECT_CODE = dIRECT_CODE;
	}

	public String getTRACK() {
		return TRACK;
	}

	public void setTRACK(String tRACK) {
		TRACK = tRACK;
	}

	public String getSTRTPT() {
		return STRTPT;
	}

	public void setSTRTPT(String sTRTPT) {
		STRTPT = sTRTPT;
	}

	public String getENDPT() {
		return ENDPT;
	}

	public void setENDPT(String eNDPT) {
		ENDPT = eNDPT;
	}

	public String getIRI_VAL() {
		return IRI_VAL;
	}

	public void setIRI_VAL(String iRI_VAL) {
		IRI_VAL = iRI_VAL;
	}

	public String getRD_VAL() {
		return RD_VAL;
	}

	public void setRD_VAL(String rD_VAL) {
		RD_VAL = rD_VAL;
	}

	public String getVRTCAL_CR() {
		return VRTCAL_CR;
	}

	public void setVRTCAL_CR(String vRTCAL_CR) {
		VRTCAL_CR = vRTCAL_CR;
	}

	public String getHRZNTAL_CR() {
		return HRZNTAL_CR;
	}

	public void setHRZNTAL_CR(String hRZNTAL_CR) {
		HRZNTAL_CR = hRZNTAL_CR;
	}

	public String getCNSTRCT_JOINT_CR() {
		return CNSTRCT_JOINT_CR;
	}

	public void setCNSTRCT_JOINT_CR(String cNSTRCT_JOINT_CR) {
		CNSTRCT_JOINT_CR = cNSTRCT_JOINT_CR;
	}

	public String getTRTS_BAC_CR() {
		return TRTS_BAC_CR;
	}

	public void setTRTS_BAC_CR(String tRTS_BAC_CR) {
		TRTS_BAC_CR = tRTS_BAC_CR;
	}

	public String getPTCHG_CR() {
		return PTCHG_CR;
	}

	public void setPTCHG_CR(String pTCHG_CR) {
		PTCHG_CR = pTCHG_CR;
	}

	public String getPOTHOLE_CR() {
		return POTHOLE_CR;
	}

	public void setPOTHOLE_CR(String pOTHOLE_CR) {
		POTHOLE_CR = pOTHOLE_CR;
	}

	public String getCR_VAL() {
		return CR_VAL;
	}

	public void setCR_VAL(String cR_VAL) {
		CR_VAL = cR_VAL;
	}

	public String getRDSRFC_IMG_FILE_NM_1() {
		return RDSRFC_IMG_FILE_NM_1;
	}

	public void setRDSRFC_IMG_FILE_NM_1(String rDSRFC_IMG_FILE_NM_1) {
		RDSRFC_IMG_FILE_NM_1 = rDSRFC_IMG_FILE_NM_1;
	}

	public String getRDSRFC_IMG_FILE_NM_2() {
		return RDSRFC_IMG_FILE_NM_2;
	}

	public void setRDSRFC_IMG_FILE_NM_2(String rDSRFC_IMG_FILE_NM_2) {
		RDSRFC_IMG_FILE_NM_2 = rDSRFC_IMG_FILE_NM_2;
	}

	public String getFRNT_IMG_FILE_NM() {
		return FRNT_IMG_FILE_NM;
	}

	public void setFRNT_IMG_FILE_NM(String fRNT_IMG_FILE_NM) {
		FRNT_IMG_FILE_NM = fRNT_IMG_FILE_NM;
	}

	public String getMEMO() {
		return MEMO;
	}

	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}

	public String getSRVY_DE() {
		return SRVY_DE;
	}

	public void setSRVY_DE(String sRVY_DE) {
		SRVY_DE = sRVY_DE;
	}

	public String getSRVY_KND() {
		return SRVY_KND;
	}

	public void setSRVY_KND(String sRVY_KND) {
		SRVY_KND = sRVY_KND;
	}

	public String getTRACE1_LA() {
		return TRACE1_LA;
	}

	public void setTRACE1_LA(String tRACE1_LA) {
		TRACE1_LA = tRACE1_LA;
	}

	public String getTRACE1_LO() {
		return TRACE1_LO;
	}

	public void setTRACE1_LO(String tRACE1_LO) {
		TRACE1_LO = tRACE1_LO;
	}

	public String getTRACE2_LA() {
		return TRACE2_LA;
	}

	public void setTRACE2_LA(String tRACE2_LA) {
		TRACE2_LA = tRACE2_LA;
	}

	public String getTRACE2_LO() {
		return TRACE2_LO;
	}

	public void setTRACE2_LO(String tRACE2_LO) {
		TRACE2_LO = tRACE2_LO;
	}

	public String getSRVY_NM() {
		return SRVY_NM;
	}

	public void setSRVY_NM(String sRVY_NM) {
		SRVY_NM = sRVY_NM;
	}

	public String getEXMNR_NM() {
		return EXMNR_NM;
	}

	public void setEXMNR_NM(String eXMNR_NM) {
		EXMNR_NM = eXMNR_NM;
	}

	public String getSCTN_STRTPT_DC() {
		return SCTN_STRTPT_DC;
	}

	public void setSCTN_STRTPT_DC(String sCTN_STRTPT_DC) {
		SCTN_STRTPT_DC = sCTN_STRTPT_DC;
	}

	public String getSCTN_ENDPT_DC() {
		return SCTN_ENDPT_DC;
	}

	public void setSCTN_ENDPT_DC(String sCTN_ENDPT_DC) {
		SCTN_ENDPT_DC = sCTN_ENDPT_DC;
	}

	public String getROAD_NM() {
		return ROAD_NM;
	}

	public void setROAD_NM(String rOAD_NM) {
		ROAD_NM = rOAD_NM;
	}

	public String getBLOCK_CR() {
		return BLOCK_CR;
	}

	public void setBLOCK_CR(String bLOCK_CR) {
		BLOCK_CR = bLOCK_CR;
	}

	public String getCR_LT() {
		return CR_LT;
	}

	public void setCR_LT(String cR_LT) {
		CR_LT = cR_LT;
	}

	public String getCR_WID() {
		return CR_WID;
	}

	public void setCR_WID(String cR_WID) {
		CR_WID = cR_WID;
	}

	public String getAC_LOW() {
		return AC_LOW;
	}

	public void setAC_LOW(String aC_LOW) {
		AC_LOW = aC_LOW;
	}

	public String getAC_MED() {
		return AC_MED;
	}

	public void setAC_MED(String aC_MED) {
		AC_MED = aC_MED;
	}

	public String getAC_HI() {
		return AC_HI;
	}

	public void setAC_HI(String aC_HI) {
		AC_HI = aC_HI;
	}

	public String getBLOCK_CR_LOW() {
		return BLOCK_CR_LOW;
	}

	public void setBLOCK_CR_LOW(String bLOCK_CR_LOW) {
		BLOCK_CR_LOW = bLOCK_CR_LOW;
	}

	public String getBLOCK_CR_MED() {
		return BLOCK_CR_MED;
	}

	public void setBLOCK_CR_MED(String bLOCK_CR_MED) {
		BLOCK_CR_MED = bLOCK_CR_MED;
	}

	public String getBLOCK_CR_HI() {
		return BLOCK_CR_HI;
	}

	public void setBLOCK_CR_HI(String bLOCK_CR_HI) {
		BLOCK_CR_HI = bLOCK_CR_HI;
	}

	public String getLC_LOW() {
		return LC_LOW;
	}

	public void setLC_LOW(String lC_LOW) {
		LC_LOW = lC_LOW;
	}

	public String getLC_MED() {
		return LC_MED;
	}

	public void setLC_MED(String lC_MED) {
		LC_MED = lC_MED;
	}

	public String getLC_HI() {
		return LC_HI;
	}

	public void setLC_HI(String lC_HI) {
		LC_HI = lC_HI;
	}

	public String getTC_LOW() {
		return TC_LOW;
	}

	public void setTC_LOW(String tC_LOW) {
		TC_LOW = tC_LOW;
	}

	public String getTC_MED() {
		return TC_MED;
	}

	public void setTC_MED(String tC_MED) {
		TC_MED = tC_MED;
	}

	public String getTC_HI() {
		return TC_HI;
	}

	public void setTC_HI(String tC_HI) {
		TC_HI = tC_HI;
	}

	public String getRD_LOW() {
		return RD_LOW;
	}

	public void setRD_LOW(String rD_LOW) {
		RD_LOW = rD_LOW;
	}

	public String getRD_MED() {
		return RD_MED;
	}

	public void setRD_MED(String rD_MED) {
		RD_MED = rD_MED;
	}

	public String getRD_HI() {
		return RD_HI;
	}

	public void setRD_HI(String rD_HI) {
		RD_HI = rD_HI;
	}

	public String getAC_IDX() {
		return AC_IDX;
	}

	public void setAC_IDX(String aC_IDX) {
		AC_IDX = aC_IDX;
	}

	public String getBC_IDX() {
		return BC_IDX;
	}

	public void setBC_IDX(String bC_IDX) {
		BC_IDX = bC_IDX;
	}

	public String getLC_IDX() {
		return LC_IDX;
	}

	public void setLC_IDX(String lC_IDX) {
		LC_IDX = lC_IDX;
	}

	public String getTC_IDX() {
		return TC_IDX;
	}

	public void setTC_IDX(String tC_IDX) {
		TC_IDX = tC_IDX;
	}

	public String getPTCHG_IDX() {
		return PTCHG_IDX;
	}

	public void setPTCHG_IDX(String pTCHG_IDX) {
		PTCHG_IDX = pTCHG_IDX;
	}

	public String getPOTHOLE_IDX() {
		return POTHOLE_IDX;
	}

	public void setPOTHOLE_IDX(String pOTHOLE_IDX) {
		POTHOLE_IDX = pOTHOLE_IDX;
	}

	public String getRD_IDX() {
		return RD_IDX;
	}

	public void setRD_IDX(String rD_IDX) {
		RD_IDX = rD_IDX;
	}

	public String getRCI() {
		return RCI;
	}

	public void setRCI(String rCI) {
		RCI = rCI;
	}

	public String getSCR() {
		return SCR;
	}

	public void setSCR(String sCR) {
		SCR = sCR;
	}

	public String getGPCI() {
		return GPCI;
	}

	public void setGPCI(String gPCI) {
		GPCI = gPCI;
	}

	public String getNHPCI() {
		return NHPCI;
	}

	public void setNHPCI(String nHPCI) {
		NHPCI = nHPCI;
	}

	public String getSPI() {
		return SPI;
	}

	public void setSPI(String sPI) {
		SPI = sPI;
	}

	public String getXCR() {
		return XCR;
	}

	public void setXCR(String xCR) {
		XCR = xCR;
	}

}
