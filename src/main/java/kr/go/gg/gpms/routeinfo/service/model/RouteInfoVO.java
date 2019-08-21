
package kr.go.gg.gpms.routeinfo.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 노선_대장_마스터
 *
 * @Class Name : RouteInfoVO.java
 * @Description : RouteInfo VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RouteInfoVO extends BaseVO {

	public RouteInfoVO() {
		super();
	}
	
	/** 
	 * TN_ROUTE_INFO.ROAD_NO, 
	 * 노선_대장_마스터.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO;

	/** 
	 * TN_ROUTE_INFO.INPUT_DATE, 
	 * 노선_대장_마스터.입력일
	 */
	@XmlElement
	private java.lang.String INPUT_DATE;

	/** 
	 * TN_ROUTE_INFO.ROAD_NAME, 
	 * 노선_대장_마스터.도로_명
	 */
	@XmlElement
	private java.lang.String ROAD_NAME;

	/** 
	 * TN_ROUTE_INFO.ROAD_TYPE, 
	 * 노선_대장_마스터.도로_종류
	 */
	@XmlElement
	private java.lang.String ROAD_TYPE;

	/** 
	 * TN_ROUTE_INFO.MCO, 
	 * 노선_대장_마스터.MCO
	 */
	@XmlElement
	private java.lang.String MCO;

	/** 
	 * TN_ROUTE_INFO.DSGDATE, 
	 * 노선_대장_마스터.DSGDATE
	 */
	@XmlElement
	private java.lang.String DSGDATE;

	/** 
	 * TN_ROUTE_INFO.D_DATE, 
	 * 노선_대장_마스터.D_DATE
	 */
	@XmlElement
	private java.lang.String D_DATE;

	/** 
	 * TN_ROUTE_INFO.J_DATE, 
	 * 노선_대장_마스터.J_DATE
	 */
	@XmlElement
	private java.lang.String J_DATE;

	/** 
	 * TN_ROUTE_INFO.JJ_DATE, 
	 * 노선_대장_마스터.JJ_DATE
	 */
	@XmlElement
	private java.lang.String JJ_DATE;

	/** 
	 * TN_ROUTE_INFO.ST_POINT, 
	 * 노선_대장_마스터.시점_명
	 */
	@XmlElement
	private java.lang.String ST_POINT;

	/** 
	 * TN_ROUTE_INFO.ST_PIC_FILE, 
	 * 노선_대장_마스터.ST_PIC_FILE
	 */
	@XmlElement
	private java.lang.String ST_PIC_FILE;

	/** 
	 * TN_ROUTE_INFO.ED_POINT, 
	 * 노선_대장_마스터.종점_명
	 */
	@XmlElement
	private java.lang.String ED_POINT;

	/** 
	 * TN_ROUTE_INFO.ED_PIC_FILE, 
	 * 노선_대장_마스터.ED_PIC_FILE
	 */
	@XmlElement
	private java.lang.String ED_PIC_FILE;

	/** 
	 * TN_ROUTE_INFO.IMPOPASS, 
	 * 노선_대장_마스터.IMPOPASS
	 */
	@XmlElement
	private java.lang.String IMPOPASS;

	/** 
	 * TN_ROUTE_INFO.REM, 
	 * 노선_대장_마스터.REM
	 */
	@XmlElement
	private java.lang.String REM;

	/** 
	 * TN_ROUTE_INFO.JUN, 
	 * 노선_대장_마스터.JUN
	 */
	@XmlElement
	private java.lang.String JUN;

	/** 
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_Y, 
	 * 노선_대장_마스터.도로_총_연장_중용포함
	 */
	@XmlElement
	private java.lang.String ROAD_TOT_LEN_JYG_Y;

	/** 
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_N, 
	 * 노선_대장_마스터.도로_총_연장_중용비포함
	 */
	@XmlElement
	private java.lang.String ROAD_TOT_LEN_JYG_N;

	/** 
	 * TN_ROUTE_INFO.ROAD_LEN_CMPT, 
	 * 노선_대장_마스터.도로_연장_전산화완료
	 */
	@XmlElement
	private java.lang.String ROAD_LEN_CMPT;

	/** 
	 * TN_ROUTE_INFO.ROAD_GRAD, 
	 * 노선_대장_마스터.도로등급
	 */
	@XmlElement
	private java.lang.String ROAD_GRAD;

	/** 
	 * TN_ROUTE_INFO.ROAD_NO_VAL, 
	 * 노선_대장_마스터.노선_번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO_VAL;
	
	@XmlElement
	private java.lang.String ROAD_NOS;
	
	@XmlElement
	private List<String> ROAD_NO_LIST;
	
	private java.lang.String DIRECT_FLAG;
	
	/**
	 * TN_ROUTE_INFO.ROAD_NO, 
	 * 노선_대장_마스터.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return this.ROAD_NO;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_NO, 
	 * 노선_대장_마스터.노선_번호 
	 * @param roadNo
	 */
	public void setROAD_NO(java.lang.String roadNo) {
		this.ROAD_NO = roadNo;
	}

	/**
	 * TN_ROUTE_INFO.INPUT_DATE, 
	 * 노선_대장_마스터.입력일 
	 * @return
	 */
	@JsonProperty(value="INPUT_DATE") 
	public java.lang.String getINPUT_DATE() {
		return this.INPUT_DATE;
	}
 
	 /**
	 * TN_ROUTE_INFO.INPUT_DATE, 
	 * 노선_대장_마스터.입력일 
	 * @param inputDate
	 */
	public void setINPUT_DATE(java.lang.String inputDate) {
		this.INPUT_DATE = inputDate;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_NAME, 
	 * 노선_대장_마스터.도로_명 
	 * @return
	 */
	@JsonProperty(value="ROAD_NAME") 
	public java.lang.String getROAD_NAME() {
		return this.ROAD_NAME;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_NAME, 
	 * 노선_대장_마스터.도로_명 
	 * @param roadName
	 */
	public void setROAD_NAME(java.lang.String roadName) {
		this.ROAD_NAME = roadName;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_TYPE, 
	 * 노선_대장_마스터.도로_종류 
	 * @return
	 */
	@JsonProperty(value="ROAD_TYPE") 
	public java.lang.String getROAD_TYPE() {
		return this.ROAD_TYPE;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_TYPE, 
	 * 노선_대장_마스터.도로_종류 
	 * @param roadType
	 */
	public void setROAD_TYPE(java.lang.String roadType) {
		this.ROAD_TYPE = roadType;
	}

	/**
	 * TN_ROUTE_INFO.MCO, 
	 * 노선_대장_마스터.MCO 
	 * @return
	 */
	@JsonProperty(value="MCO") 
	public java.lang.String getMCO() {
		return this.MCO;
	}
 
	 /**
	 * TN_ROUTE_INFO.MCO, 
	 * 노선_대장_마스터.MCO 
	 * @param mco
	 */
	public void setMCO(java.lang.String mco) {
		this.MCO = mco;
	}

	/**
	 * TN_ROUTE_INFO.DSGDATE, 
	 * 노선_대장_마스터.DSGDATE 
	 * @return
	 */
	@JsonProperty(value="DSGDATE") 
	public java.lang.String getDSGDATE() {
		return this.DSGDATE;
	}
 
	 /**
	 * TN_ROUTE_INFO.DSGDATE, 
	 * 노선_대장_마스터.DSGDATE 
	 * @param dsgdate
	 */
	public void setDSGDATE(java.lang.String dsgdate) {
		this.DSGDATE = dsgdate;
	}

	/**
	 * TN_ROUTE_INFO.D_DATE, 
	 * 노선_대장_마스터.D_DATE 
	 * @return
	 */
	@JsonProperty(value="D_DATE") 
	public java.lang.String getD_DATE() {
		return this.D_DATE;
	}
 
	 /**
	 * TN_ROUTE_INFO.D_DATE, 
	 * 노선_대장_마스터.D_DATE 
	 * @param DDate
	 */
	public void setD_DATE(java.lang.String DDate) {
		this.D_DATE = DDate;
	}

	/**
	 * TN_ROUTE_INFO.J_DATE, 
	 * 노선_대장_마스터.J_DATE 
	 * @return
	 */
	@JsonProperty(value="J_DATE") 
	public java.lang.String getJ_DATE() {
		return this.J_DATE;
	}
 
	 /**
	 * TN_ROUTE_INFO.J_DATE, 
	 * 노선_대장_마스터.J_DATE 
	 * @param JDate
	 */
	public void setJ_DATE(java.lang.String JDate) {
		this.J_DATE = JDate;
	}

	/**
	 * TN_ROUTE_INFO.JJ_DATE, 
	 * 노선_대장_마스터.JJ_DATE 
	 * @return
	 */
	@JsonProperty(value="JJ_DATE") 
	public java.lang.String getJJ_DATE() {
		return this.JJ_DATE;
	}
 
	 /**
	 * TN_ROUTE_INFO.JJ_DATE, 
	 * 노선_대장_마스터.JJ_DATE 
	 * @param jjDate
	 */
	public void setJJ_DATE(java.lang.String jjDate) {
		this.JJ_DATE = jjDate;
	}

	/**
	 * TN_ROUTE_INFO.ST_POINT, 
	 * 노선_대장_마스터.시점_명 
	 * @return
	 */
	@JsonProperty(value="ST_POINT") 
	public java.lang.String getST_POINT() {
		return this.ST_POINT;
	}
 
	 /**
	 * TN_ROUTE_INFO.ST_POINT, 
	 * 노선_대장_마스터.시점_명 
	 * @param stPoint
	 */
	public void setST_POINT(java.lang.String stPoint) {
		this.ST_POINT = stPoint;
	}

	/**
	 * TN_ROUTE_INFO.ST_PIC_FILE, 
	 * 노선_대장_마스터.ST_PIC_FILE 
	 * @return
	 */
	@JsonProperty(value="ST_PIC_FILE") 
	public java.lang.String getST_PIC_FILE() {
		return this.ST_PIC_FILE;
	}
 
	 /**
	 * TN_ROUTE_INFO.ST_PIC_FILE, 
	 * 노선_대장_마스터.ST_PIC_FILE 
	 * @param stPicFile
	 */
	public void setST_PIC_FILE(java.lang.String stPicFile) {
		this.ST_PIC_FILE = stPicFile;
	}

	/**
	 * TN_ROUTE_INFO.ED_POINT, 
	 * 노선_대장_마스터.종점_명 
	 * @return
	 */
	@JsonProperty(value="ED_POINT") 
	public java.lang.String getED_POINT() {
		return this.ED_POINT;
	}
 
	 /**
	 * TN_ROUTE_INFO.ED_POINT, 
	 * 노선_대장_마스터.종점_명 
	 * @param edPoint
	 */
	public void setED_POINT(java.lang.String edPoint) {
		this.ED_POINT = edPoint;
	}

	/**
	 * TN_ROUTE_INFO.ED_PIC_FILE, 
	 * 노선_대장_마스터.ED_PIC_FILE 
	 * @return
	 */
	@JsonProperty(value="ED_PIC_FILE") 
	public java.lang.String getED_PIC_FILE() {
		return this.ED_PIC_FILE;
	}
 
	 /**
	 * TN_ROUTE_INFO.ED_PIC_FILE, 
	 * 노선_대장_마스터.ED_PIC_FILE 
	 * @param edPicFile
	 */
	public void setED_PIC_FILE(java.lang.String edPicFile) {
		this.ED_PIC_FILE = edPicFile;
	}

	/**
	 * TN_ROUTE_INFO.IMPOPASS, 
	 * 노선_대장_마스터.IMPOPASS 
	 * @return
	 */
	@JsonProperty(value="IMPOPASS") 
	public java.lang.String getIMPOPASS() {
		return this.IMPOPASS;
	}
 
	 /**
	 * TN_ROUTE_INFO.IMPOPASS, 
	 * 노선_대장_마스터.IMPOPASS 
	 * @param impopass
	 */
	public void setIMPOPASS(java.lang.String impopass) {
		this.IMPOPASS = impopass;
	}

	/**
	 * TN_ROUTE_INFO.REM, 
	 * 노선_대장_마스터.REM 
	 * @return
	 */
	@JsonProperty(value="REM") 
	public java.lang.String getREM() {
		return this.REM;
	}
 
	 /**
	 * TN_ROUTE_INFO.REM, 
	 * 노선_대장_마스터.REM 
	 * @param rem
	 */
	public void setREM(java.lang.String rem) {
		this.REM = rem;
	}

	/**
	 * TN_ROUTE_INFO.JUN, 
	 * 노선_대장_마스터.JUN 
	 * @return
	 */
	@JsonProperty(value="JUN") 
	public java.lang.String getJUN() {
		return this.JUN;
	}
 
	 /**
	 * TN_ROUTE_INFO.JUN, 
	 * 노선_대장_마스터.JUN 
	 * @param jun
	 */
	public void setJUN(java.lang.String jun) {
		this.JUN = jun;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_Y, 
	 * 노선_대장_마스터.도로_총_연장_중용포함 
	 * @return
	 */
	@JsonProperty(value="ROAD_TOT_LEN_JYG_Y") 
	public java.lang.String getROAD_TOT_LEN_JYG_Y() {
		return this.ROAD_TOT_LEN_JYG_Y;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_Y, 
	 * 노선_대장_마스터.도로_총_연장_중용포함 
	 * @param roadTotLenJygY
	 */
	public void setROAD_TOT_LEN_JYG_Y(java.lang.String roadTotLenJygY) {
		this.ROAD_TOT_LEN_JYG_Y = roadTotLenJygY;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_N, 
	 * 노선_대장_마스터.도로_총_연장_중용비포함 
	 * @return
	 */
	@JsonProperty(value="ROAD_TOT_LEN_JYG_N") 
	public java.lang.String getROAD_TOT_LEN_JYG_N() {
		return this.ROAD_TOT_LEN_JYG_N;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_TOT_LEN_JYG_N, 
	 * 노선_대장_마스터.도로_총_연장_중용비포함 
	 * @param roadTotLenJygN
	 */
	public void setROAD_TOT_LEN_JYG_N(java.lang.String roadTotLenJygN) {
		this.ROAD_TOT_LEN_JYG_N = roadTotLenJygN;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_LEN_CMPT, 
	 * 노선_대장_마스터.도로_연장_전산화완료 
	 * @return
	 */
	@JsonProperty(value="ROAD_LEN_CMPT") 
	public java.lang.String getROAD_LEN_CMPT() {
		return this.ROAD_LEN_CMPT;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_LEN_CMPT, 
	 * 노선_대장_마스터.도로_연장_전산화완료 
	 * @param roadLenCmpt
	 */
	public void setROAD_LEN_CMPT(java.lang.String roadLenCmpt) {
		this.ROAD_LEN_CMPT = roadLenCmpt;
	}

	/**
	 * TN_ROUTE_INFO.ROAD_GRAD, 
	 * 노선_대장_마스터.도로등급 
	 * @return
	 */
	@JsonProperty(value="ROAD_GRAD") 
	public java.lang.String getROAD_GRAD() {
		return this.ROAD_GRAD;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_GRAD, 
	 * 노선_대장_마스터.도로등급 
	 * @param roadGrad
	 */
	public void setROAD_GRAD(java.lang.String roadGrad) {
		this.ROAD_GRAD = roadGrad;
	}
	
	/**
	 * TN_ROUTE_INFO.ROAD_NO_VAL, 
	 * 노선_대장_마스터.노선_번호 
	 * @return
	 */
	@JsonProperty(value="ROAD_NO_VAL") 
	public java.lang.String getROAD_NO_VAL() {
		return this.ROAD_NO_VAL;
	}
 
	 /**
	 * TN_ROUTE_INFO.ROAD_NO_VAL, 
	 * 노선_대장_마스터.노선_번호 
	 * @param roadNo
	 */
	public void setROAD_NO_VAL(java.lang.String roadNoVal) {
		this.ROAD_NO_VAL = roadNoVal;
	}

	@JsonProperty(value="ROAD_NOS") 
	public java.lang.String getROAD_NOS() {
		return this.ROAD_NOS;
	}

	public void setROAD_NOS(java.lang.String rOAD_NOS) {
		this.ROAD_NOS = rOAD_NOS;
		
		List<String> roadNoList = new ArrayList<String>();
		if(ROAD_NOS!=null && ROAD_NOS.trim().length()>0){
			
			String[] roadNoArray = ROAD_NOS.split(",");
			if(roadNoArray!=null && roadNoArray.length>0){
				for(String roadNo : roadNoArray){
					if(roadNo.trim().length()>0){
						roadNoList.add(roadNo);	
					}		
				}
			}
		}
		
		this.ROAD_NO_LIST = roadNoList;
	}

	@JsonProperty(value="ROAD_NO_LIST") 
	public List<String> getROAD_NO_LIST() {
		return this.ROAD_NO_LIST;
	}

	public void setROAD_NO_LIST(List<String> rOAD_NO_LIST) {
		this.ROAD_NO_LIST = rOAD_NO_LIST;
	}

	@JsonProperty(value="DIRECT_FLAG")
    public java.lang.String getDIRECT_FLAG() {
        return DIRECT_FLAG;
    }

    public void setDIRECT_FLAG(java.lang.String dIRECT_FLAG) {
        DIRECT_FLAG = dIRECT_FLAG;
    }

	
}
