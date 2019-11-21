
package kr.go.gg.gpms.srvydta.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelDefaultVO;
/**
 * 조사_자료
 *
 * @Class Name : SrvyDtaVO.java
 * @Description : SrvyDta VO class
 * @Modification Information
 *
 * @author antihyun2@gmail.com
 * @since 2019-10-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
/**
 * @author antih
 *
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyDtaVO extends SrvyDtaExcelDefaultVO {

	public SrvyDtaVO() {
		super();
	}
	
	@XmlElement
	private java.lang.String SRVY_NO;
	
	@XmlElement
	private java.lang.String FILE_NO;
	
	@XmlElement
	private java.lang.String DATA_CO;
	
	@XmlElement
	private java.lang.String EVL_PROCESS_AT;
	
	@XmlElement
	private java.lang.String GPS_CORTN_PROCESS_AT;
	
	@XmlElement
	private java.lang.String SM_PROCESS_AT;
	
	@XmlElement
	private java.lang.String VAL_EVL_AT;
	
	@XmlElement
	private java.lang.String DELETE_AT;
	
	@XmlElement
	private java.lang.String USE_AT;
	
	@XmlElement
	private java.lang.String CRTR_NO;
	
	@XmlElement
	private java.sql.Date CREAT_DT;
	
	@XmlElement
	private java.lang.String UPDUSR_NO;
	
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	@XmlElement
	private java.lang.String PRDTMDL_PROCESS_AT;
	
	@XmlElement
	private java.lang.String PROCESS_STTUS;
	
	@XmlElement
	private java.lang.String CRTR_NM;
	
	@XmlElement
	private java.lang.String SCH_SRVY_DE1;
	
	@XmlElement
	private java.lang.String SCH_SRVY_DE2;
	
	@XmlElement
	private java.lang.String EVL_PROCESS;
	
	@XmlElement
	private java.lang.String GPS_CORTN_PROCESS;
	
	@XmlElement
	private java.lang.String SM_PROCESS;
	
	@XmlElement
	private java.lang.String EVL_YEAR;
	
	@XmlElement
	private java.lang.String FILE_NM;
	
	@XmlElement
	private java.lang.String CALC_DE;
	
	@XmlElement
	private java.lang.String PREDCT_DE;
	
	@XmlElement
	private java.lang.String PRDTMDL_PROCESS;
	
	@XmlElement
	private String SRVY_YEAR;
	
	@XmlElement
	private String SRVY_MT;
	
	@XmlElement
	private String ROAD_NO;
	
	@XmlElement
	private String ROAD_NAME;
	
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
	
	@XmlElement
	private String EXCEL_FILE_COURS;

	@XmlElement
	private String SUCCESS_KND;
	
	@XmlElement
	private String SE_CD;
	

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

	public String getROAD_NO() {
		return ROAD_NO;
	}

	public void setROAD_NO(String rOAD_NO) {
		ROAD_NO = rOAD_NO;
	}

	public String getROAD_NAME() {
		return ROAD_NAME;
	}

	public void setROAD_NAME(String rOAD_NAME) {
		ROAD_NAME = rOAD_NAME;
	}

	public java.lang.String getFILE_NO() {
		return FILE_NO;
	}

	public void setFILE_NO(java.lang.String fILE_NO) {
		FILE_NO = fILE_NO;
	}

	public java.lang.String getDATA_CO() {
		return DATA_CO;
	}

	public void setDATA_CO(java.lang.String dATA_CO) {
		DATA_CO = dATA_CO;
	}

	public java.lang.String getEVL_PROCESS_AT() {
		return EVL_PROCESS_AT;
	}

	public void setEVL_PROCESS_AT(java.lang.String eVL_PROCESS_AT) {
		EVL_PROCESS_AT = eVL_PROCESS_AT;
	}

	public java.lang.String getGPS_CORTN_PROCESS_AT() {
		return GPS_CORTN_PROCESS_AT;
	}

	public void setGPS_CORTN_PROCESS_AT(java.lang.String gPS_CORTN_PROCESS_AT) {
		GPS_CORTN_PROCESS_AT = gPS_CORTN_PROCESS_AT;
	}

	public java.lang.String getSM_PROCESS_AT() {
		return SM_PROCESS_AT;
	}

	public void setSM_PROCESS_AT(java.lang.String sM_PROCESS_AT) {
		SM_PROCESS_AT = sM_PROCESS_AT;
	}

	public java.lang.String getVAL_EVL_AT() {
		return VAL_EVL_AT;
	}

	public void setVAL_EVL_AT(java.lang.String vAL_EVL_AT) {
		VAL_EVL_AT = vAL_EVL_AT;
	}

	public java.lang.String getDELETE_AT() {
		return DELETE_AT;
	}

	public void setDELETE_AT(java.lang.String dELETE_AT) {
		DELETE_AT = dELETE_AT;
	}

	public java.lang.String getUSE_AT() {
		return USE_AT;
	}

	public void setUSE_AT(java.lang.String uSE_AT) {
		USE_AT = uSE_AT;
	}

	public java.lang.String getCRTR_NO() {
		return CRTR_NO;
	}

	public void setCRTR_NO(java.lang.String cRTR_NO) {
		CRTR_NO = cRTR_NO;
	}

	public java.sql.Date getCREAT_DT() {
		return CREAT_DT;
	}

	public void setCREAT_DT(java.sql.Date cREAT_DT) {
		CREAT_DT = cREAT_DT;
	}

	public java.lang.String getUPDUSR_NO() {
		return UPDUSR_NO;
	}

	public void setUPDUSR_NO(java.lang.String uPDUSR_NO) {
		UPDUSR_NO = uPDUSR_NO;
	}

	public java.sql.Date getUPDT_DT() {
		return UPDT_DT;
	}

	public void setUPDT_DT(java.sql.Date uPDT_DT) {
		UPDT_DT = uPDT_DT;
	}

	public java.lang.String getPRDTMDL_PROCESS_AT() {
		return PRDTMDL_PROCESS_AT;
	}

	public void setPRDTMDL_PROCESS_AT(java.lang.String pRDTMDL_PROCESS_AT) {
		PRDTMDL_PROCESS_AT = pRDTMDL_PROCESS_AT;
	}

	public java.lang.String getPROCESS_STTUS() {
		return PROCESS_STTUS;
	}

	public void setPROCESS_STTUS(java.lang.String pROCESS_STTUS) {
		PROCESS_STTUS = pROCESS_STTUS;
	}

	public java.lang.String getCRTR_NM() {
		return CRTR_NM;
	}

	public void setCRTR_NM(java.lang.String cRTR_NM) {
		CRTR_NM = cRTR_NM;
	}

	public java.lang.String getSCH_SRVY_DE1() {
		return SCH_SRVY_DE1;
	}

	public void setSCH_SRVY_DE1(java.lang.String sCH_SRVY_DE1) {
		SCH_SRVY_DE1 = sCH_SRVY_DE1;
	}

	public java.lang.String getSCH_SRVY_DE2() {
		return SCH_SRVY_DE2;
	}

	public void setSCH_SRVY_DE2(java.lang.String sCH_SRVY_DE2) {
		SCH_SRVY_DE2 = sCH_SRVY_DE2;
	}

	public java.lang.String getEVL_PROCESS() {
		return EVL_PROCESS;
	}

	public void setEVL_PROCESS(java.lang.String eVL_PROCESS) {
		EVL_PROCESS = eVL_PROCESS;
	}

	public java.lang.String getGPS_CORTN_PROCESS() {
		return GPS_CORTN_PROCESS;
	}

	public void setGPS_CORTN_PROCESS(java.lang.String gPS_CORTN_PROCESS) {
		GPS_CORTN_PROCESS = gPS_CORTN_PROCESS;
	}

	public java.lang.String getSM_PROCESS() {
		return SM_PROCESS;
	}

	public void setSM_PROCESS(java.lang.String sM_PROCESS) {
		SM_PROCESS = sM_PROCESS;
	}

	public java.lang.String getEVL_YEAR() {
		return EVL_YEAR;
	}

	public void setEVL_YEAR(java.lang.String eVL_YEAR) {
		EVL_YEAR = eVL_YEAR;
	}

	public java.lang.String getFILE_NM() {
		return FILE_NM;
	}

	public void setFILE_NM(java.lang.String fILE_NM) {
		FILE_NM = fILE_NM;
	}

	public java.lang.String getCALC_DE() {
		return CALC_DE;
	}

	public void setCALC_DE(java.lang.String cALC_DE) {
		CALC_DE = cALC_DE;
	}

	public java.lang.String getPREDCT_DE() {
		return PREDCT_DE;
	}

	public void setPREDCT_DE(java.lang.String pREDCT_DE) {
		PREDCT_DE = pREDCT_DE;
	}

	public java.lang.String getPRDTMDL_PROCESS() {
		return PRDTMDL_PROCESS;
	}

	public void setPRDTMDL_PROCESS(java.lang.String pRDTMDL_PROCESS) {
		PRDTMDL_PROCESS = pRDTMDL_PROCESS;
	}
	
	@JsonProperty(value="SRVY_NO")
	public java.lang.String getSRVY_NO() {
		return SRVY_NO;
	}

	public void setSRVY_NO(java.lang.String sRVY_NO) {
		SRVY_NO = sRVY_NO;
	}

	public String getEXCEL_FILE_COURS() {
		return EXCEL_FILE_COURS;
	}

	public void setEXCEL_FILE_COURS(String eXCEL_FILE_COURS) {
		EXCEL_FILE_COURS = eXCEL_FILE_COURS;
	}

	public String getSUCCESS_KND() {
		return SUCCESS_KND;
	}

	public void setSUCCESS_KND(String sUCCESS_KND) {
		SUCCESS_KND = sUCCESS_KND;
	}

	public String getSE_CD() {
		return SE_CD;
	}

	public void setSE_CD(String sE_CD) {
		SE_CD = sE_CD;
	}
	

}
