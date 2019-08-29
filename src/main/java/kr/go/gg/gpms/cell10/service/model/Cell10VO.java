
package kr.go.gg.gpms.cell10.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CELL_10
 *
 * @Class Name : Cell10VO.java
 * @Description : Cell10 VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class Cell10VO extends BaseVO {

	public Cell10VO() {
		super();
	}

	@XmlElement
	private java.lang.String lon;

	@XmlElement
    private java.lang.String lat;

	/**
	 * CELL_10.GID,
	 * CELL_10.GID
	 */
	@XmlElement
	private java.lang.String GID;

	/**
	 * CELL_10.OBJECT_ID,
	 * CELL_10.OBJECT_ID
	 */
	@XmlElement
	private java.lang.String OBJECT_ID;

	/**
	 * CELL_10.ROUTE_CODE,
	 * CELL_10.ROUTE_CODE
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/**
	 * CELL_10.DIRECT_CODE,
	 * CELL_10.DIRECT_CODE
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/**
	 * CELL_10.TRACK,
	 * CELL_10.TRACK
	 */
	@XmlElement
	private java.lang.String TRACK;

	/**
	 * CELL_10.STRTPT,
	 * CELL_10.STRTPT
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/**
	 * CELL_10.ENDPT,
	 * CELL_10.ENDPT
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/**
	 * CELL_10.DEPT_CODE,
	 * CELL_10.DEPT_CODE
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/**
	 * CELL_10.CELL_TYPE,
	 * CELL_10.CELL_TYPE
	 */
	@XmlElement
	private java.lang.String CELL_TYPE;

	/**
	 * CELL_10.VMTC_GRAD,
	 * CELL_10.VMTC_GRAD
	 */
	@XmlElement
	private java.lang.String VMTC_GRAD;

	/**
	 * CELL_10.ROAD_GRAD,
	 * CELL_10.ROAD_GRAD
	 */
	@XmlElement
	private java.lang.String ROAD_GRAD;

	/**
	 * CELL_10.ADM_CODE,
	 * CELL_10.ADM_CODE
	 */
	@XmlElement
	private java.lang.String ADM_CODE;

	/**
	 * CELL_10.CREAT_DT,
	 * CELL_10.CREAT_DT
	 */
	@XmlElement
	private java.lang.String CREAT_DT;

	/**
	 * CELL_10.UPDUSR_NO,
	 * CELL_10.UPDUSR_NO
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/**
	 * CELL_10.UPDT_DT,
	 * CELL_10.UPDT_DT
	 */
	@XmlElement
	private java.lang.String UPDT_DT;

	/**
	 * CELL_10.CELL_ID,
	 * CELL_10.CELL_ID
	 */
	@XmlElement
	private java.lang.String CELL_ID;

	@XmlElement
	private java.lang.String ROAD_NO_VAL;

	@XmlElement
	private java.lang.String ROAD_NAME;

	@XmlElement
	private java.lang.String CELL_IDS;

	@XmlElement
	private List<String> CELL_ID_LIST;

	@XmlElement
	private java.lang.String ADM_NM;

	@XmlElement
	private java.lang.String LEN;

	@XmlElement
	private java.lang.String SCH_ROUTE_CODE;

	@XmlElement
	private java.lang.String SCH_DEPT_CODE;

	@XmlElement
	private java.lang.String SCH_DEPT_CODE1;

	@XmlElement
	private java.lang.String SCH_ROAD_CODE;

	@XmlElement
	private java.lang.String SCH_STRSRVY_DE;

	@XmlElement
	private java.lang.String SCH_ENDSRVY_DE;

	@XmlElement
	private java.lang.String DEPT_NM;

	@XmlElement
	private java.lang.String SECTLEN1;

	@XmlElement
	private java.lang.String SECTLEN2;

	@XmlElement
	private java.lang.String SECTLEN3;

	@XmlElement
	private java.lang.String SECTLEN4;

	@XmlElement
	private java.lang.String SUM_L;

	@XmlElement
	private java.lang.String OP_L;

	@XmlElement
	private java.lang.String NOP_L;

	@XmlElement
	private java.lang.String SUM_LEN;

	@XmlElement
	private java.lang.String NJR_LEN;

	@XmlElement
	private java.lang.String JBR_LEN;

	@XmlElement
	private java.lang.String UNTRACK_LEN;

	@XmlElement
	private java.lang.String ROUTE_NAME;

	@XmlElement
	private java.lang.String ROUTE_TYPE;

	@XmlElement
	private java.lang.String ADM;

	@XmlElement
	private java.lang.String TRACK2_L;

	@XmlElement
	private java.lang.String TRACK4_L;

	@XmlElement
	private java.lang.String TRACK6_L;

	@XmlElement
	private java.lang.String TRACK8_L;

	@XmlElement
	private java.lang.String TRACK10_L;

	@XmlElement
	private java.lang.String TRACK2;

	@XmlElement
	private java.lang.String TRACK4;

	@XmlElement
	private java.lang.String TRACK6;

	@XmlElement
	private java.lang.String TRACK8;

	@XmlElement
	private java.lang.String TRACK10;

	@XmlElement
	private java.lang.String JR_LEN;

	@XmlElement
	private java.lang.String STATS_YEAR;

	@XmlElement
	private java.lang.String SCH_ROAD_GRAD;

	@XmlElement
	private List<String> CELL_ID_ARR;

    @XmlElement
    private java.lang.String TRTS_BAC_CR;

    @XmlElement
    private java.lang.String VRTCAL_CR;

    @XmlElement
    private java.lang.String BLOCK_CR;

    @XmlElement
    private java.lang.String HRZNTAL_CR;

    @XmlElement
    private java.lang.String PTCHG_CR;

    @XmlElement
    private java.lang.String POTHOLE_CR;

    @XmlElement
    private java.lang.String RD_VAL;

    @XmlElement
    private java.lang.String IRI_VAL;

    @XmlElement
    private java.lang.String SRVY_YEAR_MIN;

    @XmlElement
    private java.lang.String SRVY_YEAR_MAX;

    @XmlElement
    private java.lang.String SRVY_YEAR;

	/**
	 * CELL_10.GID,
	 * CELL_10.GID
	 * @return
	 */
	@JsonProperty(value="GID")
	public java.lang.String getGID() {
		return this.GID;
	}

	 /**
	 * CELL_10.GID,
	 * CELL_10.GID
	 * @param g2Id
	 */
	public void setGID(java.lang.String gId) {
		this.GID = gId;
	}

	/**
	 * CELL_10.OBJECT_ID,
	 * CELL_10.OBJECT_ID
	 * @return
	 */
	@JsonProperty(value="OBJECT_ID")
	public java.lang.String getOBJECT_ID() {
		return this.OBJECT_ID;
	}

	 /**
	 * CELL_10.OBJECT_ID,
	 * CELL_10.OBJECT_ID
	 * @param objectId
	 */
	public void setOBJECT_ID(java.lang.String objectId) {
		this.OBJECT_ID = objectId;
	}

	/**
	 * CELL_10.ROUTE_CODE,
	 * CELL_10.ROUTE_CODE
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE")
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}

	 /**
	 * CELL_10.ROUTE_CODE,
	 * CELL_10.ROUTE_CODE
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * CELL_10.DIRECT_CODE,
	 * CELL_10.DIRECT_CODE
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE")
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}

	 /**
	 * CELL_10.DIRECT_CODE,
	 * CELL_10.DIRECT_CODE
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;

		setDIRECT_NM(this.DIRECT_CODE);
	}

	/**
	 * CELL_10.TRACK,
	 * CELL_10.TRACK
	 * @return
	 */
	@JsonProperty(value="TRACK")
	public java.lang.String getTRACK() {
		return this.TRACK;
	}

	 /**
	 * CELL_10.TRACK,
	 * CELL_10.TRACK
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * CELL_10.STRTPT,
	 * CELL_10.STRTPT
	 * @return
	 */
	@JsonProperty(value="STRTPT")
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}

	 /**
	 * CELL_10.STRTPT,
	 * CELL_10.STRTPT
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * CELL_10.ENDPT,
	 * CELL_10.ENDPT
	 * @return
	 */
	@JsonProperty(value="ENDPT")
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}

	 /**
	 * CELL_10.ENDPT,
	 * CELL_10.ENDPT
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * CELL_10.DEPT_CODE,
	 * CELL_10.DEPT_CODE
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE")
	public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}

	 /**
	 * CELL_10.DEPT_CODE,
	 * CELL_10.DEPT_CODE
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
		super.setDIRECT_NM(deptCode);
	}

	/**
	 * CELL_10.CELL_TYPE,
	 * CELL_10.CELL_TYPE
	 * @return
	 */
	@JsonProperty(value="CELL_TYPE")
	public java.lang.String getCELL_TYPE() {
		return this.CELL_TYPE;
	}

	 /**
	 * CELL_10.CELL_TYPE,
	 * CELL_10.CELL_TYPE
	 * @param cellType
	 */
	public void setCELL_TYPE(java.lang.String cellType) {
		this.CELL_TYPE = cellType;
	}

	/**
	 * CELL_10.VMTC_GRAD,
	 * CELL_10.VMTC_GRAD
	 * @return
	 */
	@JsonProperty(value="VMTC_GRAD")
	public java.lang.String getVMTC_GRAD() {
		return this.VMTC_GRAD;
	}

	 /**
	 * CELL_10.VMTC_GRAD,
	 * CELL_10.VMTC_GRAD
	 * @param vmtcGrad
	 */
	public void setVMTC_GRAD(java.lang.String vmtcGrad) {
		this.VMTC_GRAD = vmtcGrad;
	}

	/**
	 * CELL_10.ROAD_GRAD,
	 * CELL_10.ROAD_GRAD
	 * @return
	 */
	@JsonProperty(value="ROAD_GRAD")
	public java.lang.String getROAD_GRAD() {
		return this.ROAD_GRAD;
	}

	 /**
	 * CELL_10.ROAD_GRAD,
	 * CELL_10.ROAD_GRAD
	 * @param roadGrad
	 */
	public void setROAD_GRAD(java.lang.String roadGrad) {
		this.ROAD_GRAD = roadGrad;
	}

	/**
	 * CELL_10.ADM_CODE,
	 * CELL_10.ADM_CODE
	 * @return
	 */
	@JsonProperty(value="ADM_CODE")
	public java.lang.String getADM_CODE() {
		return this.ADM_CODE;
	}

	 /**
	 * CELL_10.ADM_CODE,
	 * CELL_10.ADM_CODE
	 * @param admCode
	 */
	public void setADM_CODE(java.lang.String admCode) {
		this.ADM_CODE = admCode;
	}

	/**
	 * CELL_10.CREAT_DT,
	 * CELL_10.CREAT_DT
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	public java.lang.String getCREAT_DT() {
		return this.CREAT_DT;
	}

	 /**
	 * CELL_10.CREAT_DT,
	 * CELL_10.CREAT_DT
	 * @param creatDt
	 */
	public void setCREAT_DT(java.lang.String creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * CELL_10.UPDUSR_NO,
	 * CELL_10.UPDUSR_NO
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO")
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}

	 /**
	 * CELL_10.UPDUSR_NO,
	 * CELL_10.UPDUSR_NO
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * CELL_10.UPDT_DT,
	 * CELL_10.UPDT_DT
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	public java.lang.String getUPDT_DT() {
		return this.UPDT_DT;
	}

	 /**
	 * CELL_10.UPDT_DT,
	 * CELL_10.UPDT_DT
	 * @param updtDt
	 */
	public void setUPDT_DT(java.lang.String updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * CELL_10.CELL_ID,
	 * CELL_10.CELL_ID
	 * @return
	 */
	@JsonProperty(value="CELL_ID")
	public java.lang.String getCELL_ID() {
		return this.CELL_ID;
	}

	 /**
	 * CELL_10.CELL_ID,
	 * CELL_10.CELL_ID
	 * @param cellId
	 */
	public void setCELL_ID(java.lang.String cellId) {
		this.CELL_ID = cellId;
	}


	/**
	 * ROAD_NO_VAL,
	 * ROAD_NO_VAL
	 * @return
	 */
	@JsonProperty(value="ROAD_NO_VAL")
	public java.lang.String getROAD_NO_VAL() {
		return this.ROAD_NO_VAL;
	}

	 /**
	 * ROAD_NO_VAL,
	 * ROAD_NO_VAL
	 * @param roadNoVal
	 */
	public void setROAD_NO_VAL(java.lang.String roadNoVal) {
		this.ROAD_NO_VAL = roadNoVal;
	}

	/**
	 * ROAD_NAME,
	 * ROAD_NAME
	 * @return
	 */
	@JsonProperty(value="ROAD_NAME")
	public java.lang.String getROAD_NAME() {
		return this.ROAD_NAME;
	}

	 /**
	 * ROAD_NAME,
	 * ROAD_NAME
	 * @param roadName
	 */
	public void setROAD_NAME(java.lang.String roadName) {
		this.ROAD_NAME = roadName;
	}

	@JsonProperty(value="CELL_ID_LIST")
	public List<String> getCELL_ID_LIST() {
		return CELL_ID_LIST;
	}

	public void setCELL_ID_LIST(List<String> cELL_ID_LIST) {
		this.CELL_ID_LIST = cELL_ID_LIST;
	}

	@JsonProperty(value="CELL_IDS")
	public java.lang.String getCELL_IDS() {
		return CELL_IDS;
	}

	public void setCELL_IDS(java.lang.String cELL_IDS) {
		this.CELL_IDS = cELL_IDS;
		List<String> cellIdList = new ArrayList<String>();
		if(CELL_IDS!=null && CELL_IDS.trim().length()>0){

			String[] cellidArray = CELL_IDS.split(",");
			if(cellidArray!=null && cellidArray.length>0){
				for(String cellId : cellidArray){
					if(cellId.trim().length()>0){
						cellIdList.add(cellId);
					}
				}
			}
		}

		this.CELL_ID_LIST = cellIdList;
	}

	@JsonProperty(value="ADM_NM")
	public java.lang.String getADM_NM() {
		return this.ADM_NM;
	}

	public void setADM_NM(java.lang.String aDM_NM) {
		this.ADM_NM = aDM_NM;
	}

	@JsonProperty(value="LEN")
	public java.lang.String getLEN() {
		return this.LEN;
	}

	public void setLEN(java.lang.String lEN) {
		this.LEN = lEN;
	}

	@JsonProperty(value="SCH_ROUTE_CODE")
	public java.lang.String getSCH_ROUTE_CODE() {
		return this.SCH_ROUTE_CODE;
	}

	public void setSCH_ROUTE_CODE(java.lang.String sCH_ROUTE_CODE) {
		this.SCH_ROUTE_CODE = sCH_ROUTE_CODE;
	}

	@JsonProperty(value="SCH_DEPT_CODE")
	public java.lang.String getSCH_DEPT_CODE() {
		return this.SCH_DEPT_CODE;
	}

	public void setSCH_DEPT_CODE(java.lang.String sCH_DEPT_CODE) {
		this.SCH_DEPT_CODE = sCH_DEPT_CODE;
	}

	@JsonProperty(value="SCH_DEPT_CODE1")
	public java.lang.String getSCH_DEPT_CODE1() {
		return this.SCH_DEPT_CODE1;
	}

	public void setSCH_DEPT_CODE1(java.lang.String sCH_DEPT_CODE1) {
		this.SCH_DEPT_CODE1 = sCH_DEPT_CODE1;
	}

	@JsonProperty(value="SCH_ROAD_CODE")
	public java.lang.String getSCH_ROAD_CODE() {
		return this.SCH_ROAD_CODE;
	}

	public void setSCH_ROAD_CODE(java.lang.String sCH_ROAD_CODE) {
		this.SCH_ROAD_CODE = sCH_ROAD_CODE;
	}

	@JsonProperty(value="SCH_STRSRVY_DE")
	public java.lang.String getSCH_STRSRVY_DE() {
		return this.SCH_STRSRVY_DE;
	}

	public void setSCH_STRSRVY_DE(java.lang.String sCH_STRSRVY_DE) {
		this.SCH_STRSRVY_DE = sCH_STRSRVY_DE;
	}

	@JsonProperty(value="SCH_ENDSRVY_DE")
	public java.lang.String getSCH_ENDSRVY_DE() {
		return this.SCH_ENDSRVY_DE;
	}

	public void setSCH_ENDSRVY_DE(java.lang.String sCH_ENDSRVY_DE) {
		this.SCH_ENDSRVY_DE = sCH_ENDSRVY_DE;
	}

	@JsonProperty(value="DEPT_NM")
	public java.lang.String getDEPT_NM() {
		return this.DEPT_NM;
	}

	public void setDEPT_NM(java.lang.String dEPT_NM) {
		this.DEPT_NM = dEPT_NM;
	}

	@JsonProperty(value="SECTLEN1")
	public java.lang.String getSECTLEN1() {
		return this.SECTLEN1;
	}

	public void setSECTLEN1(java.lang.String sECTLEN1) {
		this.SECTLEN1 = sECTLEN1;
	}

	@JsonProperty(value="SECTLEN2")
	public java.lang.String getSECTLEN2() {
		return this.SECTLEN2;
	}

	public void setSECTLEN2(java.lang.String sECTLEN2) {
		this.SECTLEN2 = sECTLEN2;
	}

	@JsonProperty(value="SECTLEN3")
	public java.lang.String getSECTLEN3() {
		return this.SECTLEN3;
	}

	public void setSECTLEN3(java.lang.String sECTLEN3) {
		this.SECTLEN3 = sECTLEN3;
	}

	@JsonProperty(value="SECTLEN4")
	public java.lang.String getSECTLEN4() {
		return this.SECTLEN4;
	}

	public void setSECTLEN4(java.lang.String sECTLEN4) {
		this.SECTLEN4 = sECTLEN4;
	}

	@JsonProperty(value="SUM_L")
	public java.lang.String getSUM_L() {
		return this.SUM_L;
	}

	public void setSUM_L(java.lang.String sUM_L) {
		this.SUM_L = sUM_L;
	}

	@JsonProperty(value="OP_L")
	public java.lang.String getOP_L() {
		return this.OP_L;
	}

	public void setOP_L(java.lang.String oP_L) {
		this.OP_L = oP_L;
	}

	@JsonProperty(value="NOP_L")
	public java.lang.String getNOP_L() {
		return this.NOP_L;
	}

	public void setNOP_L(java.lang.String nOP_L) {
		this.NOP_L = nOP_L;
	}

	@JsonProperty(value="SUM_LEN")
	public java.lang.String getSUM_LEN() {
		return this.SUM_LEN;
	}

	public void setSUM_LEN(java.lang.String sUM_LEN) {
		this.SUM_LEN = sUM_LEN;
	}

	@JsonProperty(value="NJR_LEN")
	public java.lang.String getNJR_LEN() {
		return this.NJR_LEN;
	}

	public void setNJR_LEN(java.lang.String nJR_LEN) {
		this.NJR_LEN = nJR_LEN;
	}

	@JsonProperty(value="JBR_LEN")
	public java.lang.String getJBR_LEN() {
		return this.JBR_LEN;
	}

	public void setJBR_LEN(java.lang.String jBR_LEN) {
		this.JBR_LEN = jBR_LEN;
	}

	@JsonProperty(value="UNTRACK_LEN")
	public java.lang.String getUNTRACK_LEN() {
		return this.UNTRACK_LEN;
	}

	public void setUNTRACK_LEN(java.lang.String uNTRACK_LEN) {
		this.UNTRACK_LEN = uNTRACK_LEN;
	}

	@JsonProperty(value="ROUTE_NAME")
	public java.lang.String getROUTE_NAME() {
		return this.ROUTE_NAME;
	}

	public void setROUTE_NAME(java.lang.String rOUTE_NAME) {
		this.ROUTE_NAME = rOUTE_NAME;
	}

	@JsonProperty(value="ROUTE_TYPE")
	public java.lang.String getROUTE_TYPE() {
		return this.ROUTE_TYPE;
	}

	public void setROUTE_TYPE(java.lang.String rOUTE_TYPE) {
		this.ROUTE_TYPE = rOUTE_TYPE;
	}

	@JsonProperty(value="ADM")
	public java.lang.String getADM() {
		return this.ADM;
	}

	public void setADM(java.lang.String aDM) {
		this.ADM = aDM;
	}

	@JsonProperty(value="TRACK2_L")
	public java.lang.String getTRACK2_L() {
		return this.TRACK2_L;
	}

	public void setTRACK2_L(java.lang.String tRACK2_L) {
		this.TRACK2_L = tRACK2_L;
	}

	@JsonProperty(value="TRACK4_L")
	public java.lang.String getTRACK4_L() {
		return this.TRACK4_L;
	}

	public void setTRACK4_L(java.lang.String tRACK4_L) {
		this.TRACK4_L = tRACK4_L;
	}

	@JsonProperty(value="TRACK6_L")
	public java.lang.String getTRACK6_L() {
		return this.TRACK6_L;
	}

	public void setTRACK6_L(java.lang.String tRACK6_L) {
		this.TRACK6_L = tRACK6_L;
	}

	@JsonProperty(value="TRACK8_L")
	public java.lang.String getTRACK8_L() {
		return this.TRACK8_L;
	}

	public void setTRACK8_L(java.lang.String tRACK8_L) {
		this.TRACK8_L = tRACK8_L;
	}

	@JsonProperty(value="TRACK10_L")
	public java.lang.String getTRACK10_L() {
		return this.TRACK10_L;
	}

	public void setTRACK10_L(java.lang.String tRACK10_L) {
		this.TRACK10_L = tRACK10_L;
	}

	@JsonProperty(value="TRACK2")
	public java.lang.String getTRACK2() {
		return this.TRACK2;
	}

	public void setTRACK2(java.lang.String tRACK2) {
		this.TRACK2 = tRACK2;
	}

	@JsonProperty(value="TRACK4")
	public java.lang.String getTRACK4() {
		return this.TRACK4;
	}

	public void setTRACK4(java.lang.String tRACK4) {
		this.TRACK4 = tRACK4;
	}

	@JsonProperty(value="TRACK6")
	public java.lang.String getTRACK6() {
		return this.TRACK6;
	}

	public void setTRACK6(java.lang.String tRACK6) {
		this.TRACK6 = tRACK6;
	}

	@JsonProperty(value="TRACK8")
	public java.lang.String getTRACK8() {
		return this.TRACK8;
	}

	public void setTRACK8(java.lang.String tRACK8) {
		this.TRACK8 = tRACK8;
	}

	@JsonProperty(value="TRACK10")
	public java.lang.String getTRACK10() {
		return this.TRACK10;
	}

	public void setTRACK10(java.lang.String tRACK10) {
		this.TRACK10 = tRACK10;
	}

	@JsonProperty(value="JR_LEN")
	public java.lang.String getJR_LEN() {
		return this.JR_LEN;
	}

	public void setJR_LEN(java.lang.String jR_LEN) {
		this.JR_LEN = jR_LEN;
	}

	@JsonProperty(value="STATS_YEAR")
	public java.lang.String getSTATS_YEAR() {
		return this.STATS_YEAR;
	}

	public void setSTATS_YEAR(java.lang.String sTATS_YEAR) {
		this.STATS_YEAR = sTATS_YEAR;
	}

	@JsonProperty(value="SCH_ROAD_GRAD")
    public java.lang.String getSCH_ROAD_GRAD() {
        return SCH_ROAD_GRAD;
    }

    public void setSCH_ROAD_GRAD(java.lang.String sCH_ROAD_GRAD) {
        SCH_ROAD_GRAD = sCH_ROAD_GRAD;
    }

    @JsonProperty(value="CELL_ID_ARR")
    public List<String> getCELL_ID_ARR() {
        return CELL_ID_ARR;
    }

    public void setCELL_ID_ARR(List<String> cELL_ID_ARR) {
        CELL_ID_ARR = cELL_ID_ARR;
    }

    @JsonProperty(value="TRTS_BAC_CR")
    public java.lang.String getTRTS_BAC_CR() {
        return TRTS_BAC_CR;
    }

    public void setTRTS_BAC_CR(java.lang.String tRTS_BAC_CR) {
        TRTS_BAC_CR = tRTS_BAC_CR;
    }

    @JsonProperty(value="VRTCAL_CR")
    public java.lang.String getVRTCAL_CR() {
        return VRTCAL_CR;
    }

    public void setVRTCAL_CR(java.lang.String vRTCAL_CR) {
        VRTCAL_CR = vRTCAL_CR;
    }

    @JsonProperty(value="BLOCK_CR")
    public java.lang.String getBLOCK_CR() {
        return BLOCK_CR;
    }

    public void setBLOCK_CR(java.lang.String bLOCK_CR) {
        BLOCK_CR = bLOCK_CR;
    }

    @JsonProperty(value="HRZNTAL_CR")
    public java.lang.String getHRZNTAL_CR() {
        return HRZNTAL_CR;
    }

    public void setHRZNTAL_CR(java.lang.String hRZNTAL_CR) {
        HRZNTAL_CR = hRZNTAL_CR;
    }

    @JsonProperty(value="PTCHG_CR")
    public java.lang.String getPTCHG_CR() {
        return PTCHG_CR;
    }

    public void setPTCHG_CR(java.lang.String pTCHG_CR) {
        PTCHG_CR = pTCHG_CR;
    }

    @JsonProperty(value="POTHOLE_CR")
    public java.lang.String getPOTHOLE_CR() {
        return POTHOLE_CR;
    }

    public void setPOTHOLE_CR(java.lang.String pOTHOLE_CR) {
        POTHOLE_CR = pOTHOLE_CR;
    }

    @JsonProperty(value="RD_VAL")
    public java.lang.String getRD_VAL() {
        return RD_VAL;
    }

    public void setRD_VAL(java.lang.String rD_VAL) {
        RD_VAL = rD_VAL;
    }

    @JsonProperty(value="IRI_VAL")
    public java.lang.String getIRI_VAL() {
        return IRI_VAL;
    }

    public void setIRI_VAL(java.lang.String iRI_VAL) {
        IRI_VAL = iRI_VAL;
    }

    @JsonProperty(value="SRVY_YEAR_MIN")
    public java.lang.String getSRVY_YEAR_MIN() {
        return SRVY_YEAR_MIN;
    }

    public void setSRVY_YEAR_MIN(java.lang.String sRVY_YEAR_MIN) {
        SRVY_YEAR_MIN = sRVY_YEAR_MIN;
    }

    @JsonProperty(value="SRVY_YEAR_MAX")
    public java.lang.String getSRVY_YEAR_MAX() {
        return SRVY_YEAR_MAX;
    }

    public void setSRVY_YEAR_MAX(java.lang.String sRVY_YEAR_MAX) {
        SRVY_YEAR_MAX = sRVY_YEAR_MAX;
    }

    @JsonProperty(value="SRVY_YEAR")
    public java.lang.String getSRVY_YEAR() {
        return SRVY_YEAR;
    }

    public void setSRVY_YEAR(java.lang.String sRVY_YEAR) {
        SRVY_YEAR = sRVY_YEAR;
    }

    @JsonProperty(value="lon")
    public java.lang.String getLon() {
        return lon;
    }

    public void setLon(java.lang.String lon) {
        this.lon = lon;
    }

    @JsonProperty(value="lat")
    public java.lang.String getLat() {
        return lat;
    }

    public void setLat(java.lang.String lat) {
        this.lat = lat;
    }
}