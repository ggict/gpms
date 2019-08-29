
package kr.go.gg.gpms.cellsect.service.model;

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
public class CellSectVO extends BaseVO {

	public CellSectVO() {
		super();
	}
	
	/** 
	 * CELL_SECT.GID
	 */
	@XmlElement
	private java.lang.String GID;

	/** 
	 * CELL_SECT.OBJECT_ID
	 */
	@XmlElement
	private java.lang.String OBJECT_ID;

	/** 
	 * CELL_SECT.ROUTE_CODE
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * CELL_SECT.DIRECT_CODE
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * CELL_SECT.TRACK
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * CELL_SECT.STRTPT
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/** 
	 * CELL_SECT.ENDPT
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/** 
	 * CELL_SECT.DEPT_CODE
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/** 
	 * CELL_SECT.CELL_TYPE
	 */
	@XmlElement
	private java.lang.String CELL_TYPE;

	/** 
	 * CELL_SECT.VMTC_GRAD
	 */
	@XmlElement
	private java.lang.String VMTC_GRAD;

	/** 
	 * CELL_SECT.ROAD_GRAD
	 */
	@XmlElement
	private java.lang.String ROAD_GRAD;

	/** 
	 * CELL_SECT.ADM_CODE
	 */
	@XmlElement
	private java.lang.String ADM_CODE;

	/** 
	 * CELL_SECT.CREAT_DT
	 */
	@XmlElement
	private java.lang.String CREAT_DT;

	/** 
	 * CELL_SECT.UPDUSR_NO
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * CELL_SECT.UPDT_DT
	 */
	@XmlElement
	private java.lang.String UPDT_DT;
	
	/** 
	 * ROUTE_NM
	 */
	@XmlElement
	private java.lang.String ROUTE_NM;
	
	/** 
	 * 셀 타입 명
	 */
	@XmlElement
	private java.lang.String CELL_TYPE_NM;
	
	/** 
	 * 도로 연장
	 */
	@XmlElement
	private java.lang.String ROAD_L;
	
	/** 
	 * 도로 면적
	 */
	@XmlElement
	private java.lang.String ROAD_A;
	
	/** 
	 * 교통량
	 */
	@XmlElement
	private java.lang.String VNTC_GR;
	
	
	/**
	 * CELL_SECT.GID 
	 * @return
	 */
	@JsonProperty(value="GID") 
	public java.lang.String getGID() {
		return this.GID;
	}
 
	 /**
	 * CELL_SECT.GID 
	 * @param g2Id
	 */
	public void setGID(java.lang.String gId) {
		this.GID = gId;
	}

	/**
	 * CELL_SECT.OBJECT_ID 
	 * @return
	 */
	@JsonProperty(value="OBJECT_ID") 
	public java.lang.String getOBJECT_ID() {
		return this.OBJECT_ID;
	}
 
	 /**
	 * CELL_SECT.OBJECT_ID 
	 * @param objectId
	 */
	public void setOBJECT_ID(java.lang.String objectId) {
		this.OBJECT_ID = objectId;
	}

	/**
	 * CELL_SECT.ROUTE_CODE 
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * CELL_SECT.ROUTE_CODE 
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * CELL_SECT.DIRECT_CODE 
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * CELL_SECT.DIRECT_CODE 
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
		
		setDIRECT_NM(this.DIRECT_CODE);
	}
	
	/**
	 * CELL_SECT.TRACK 
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * CELL_SECT.TRACK 
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * CELL_SECT.STRTPT 
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * CELL_SECT.STRTPT 
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * CELL_SECT.ENDPT 
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * CELL_SECT.ENDPT 
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * CELL_SECT.DEPT_CODE 
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE") 
	public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}
 
	 /**
	 * CELL_SECT.DEPT_CODE 
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
	}
	
	/**
	 * CELL_SECT.VMTC_GRAD 
	 * @return
	 */
	@JsonProperty(value="VMTC_GRAD") 
	public java.lang.String getVMTC_GRAD() {
		return this.VMTC_GRAD;
	}
 
	 /**
	 * CELL_SECT.VMTC_GRAD 
	 * @param vmtcGrad
	 */
	public void setVMTC_GRAD(java.lang.String vmtcGrad) {
		this.VMTC_GRAD = vmtcGrad;
	}

	/**
	 * CELL_SECT.ROAD_GRAD 
	 * @return
	 */
	@JsonProperty(value="ROAD_GRAD") 
	public java.lang.String getROAD_GRAD() {
		return this.ROAD_GRAD;
	}
 
	 /**
	 * CELL_SECT.ROAD_GRAD 
	 * @param roadGrad
	 */
	public void setROAD_GRAD(java.lang.String roadGrad) {
		this.ROAD_GRAD = roadGrad;
	}

	/**
	 * CELL_SECT.ADM_CODE 
	 * @return
	 */
	@JsonProperty(value="ADM_CODE") 
	public java.lang.String getADM_CODE() {
		return this.ADM_CODE;
	}
 
	 /**
	 * CELL_SECT.ADM_CODE 
	 * @param admCode
	 */
	public void setADM_CODE(java.lang.String admCode) {
		this.ADM_CODE = admCode;
	}

	/**
	 * CELL_SECT.CREAT_DT 
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.lang.String getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * CELL_SECT.CREAT_DT 
	 * @param creatDt
	 */
	public void setCREAT_DT(java.lang.String creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * CELL_SECT.UPDUSR_NO 
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * CELL_SECT.UPDUSR_NO 
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * CELL_SECT.UPDT_DT 
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.lang.String getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * CELL_SECT.UPDT_DT 
	 * @param updtDt
	 */
	public void setUPDT_DT(java.lang.String updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="CELL_TYPE") 
	public java.lang.String getCELL_TYPE() {
		return this.CELL_TYPE;
	}

	public void setCELL_TYPE(java.lang.String CELL_TYPE) {
		this.CELL_TYPE = CELL_TYPE;
	}

	@JsonProperty(value="ROUTE_NM") 
	public java.lang.String getROUTE_NM() {
		return this.ROUTE_NM;
	}

	public void setROUTE_NM(java.lang.String rOUTE_NM) {
		this.ROUTE_NM = rOUTE_NM;
	}

	@JsonProperty(value="CELL_TYPE_NM") 
	public java.lang.String getCELL_TYPE_NM() {
		return this.CELL_TYPE_NM;
	}

	public void setCELL_TYPE_NM(java.lang.String cELL_TYPE_NM) {
		this.CELL_TYPE_NM = cELL_TYPE_NM;
	}

	@JsonProperty(value="ROAD_L")
	public java.lang.String getROAD_L() {
		return ROAD_L;
	}

	public void setROAD_L(java.lang.String rOAD_L) {
		ROAD_L = rOAD_L;
	}

	@JsonProperty(value="ROAD_A")
	public java.lang.String getROAD_A() {
		return ROAD_A;
	}

	public void setROAD_A(java.lang.String rOAD_A) {
		ROAD_A = rOAD_A;
	}

	@JsonProperty(value="VNTC_GR")
	public java.lang.String getVNTC_GR() {
		return this.VNTC_GR;
	}

	public void setVNTC_GR(java.lang.String vNTC_GR) {
		this.VNTC_GR = vNTC_GR;
	}
	
	
}
