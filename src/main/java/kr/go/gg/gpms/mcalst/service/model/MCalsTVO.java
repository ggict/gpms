
package kr.go.gg.gpms.mcalst.service.model;

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
public class MCalsTVO extends BaseVO {

	public MCalsTVO() {
		super();
	}
	
	/** 
	 * M_CALS_T.GID
	 */
	@XmlElement
	private java.lang.String GID;

	/** 
	 * M_CALS_T.G2_DATASETID
	 */
	@XmlElement
	private java.lang.String G2_DATASETID;

	/** 
	 * M_CALS_T.G2_SPATIALTYPE
	 */
	@XmlElement
	private java.lang.String G2_SPATIALTYPE;

	/** 
	 * M_CALS_T.G2_XMIN
	 */
	@XmlElement
	private java.lang.String G2_XMIN;

	/** 
	 * M_CALS_T.G2_YMIN
	 */
	@XmlElement
	private java.lang.String G2_YMIN;

	/** 
	 * M_CALS_T.G2_XMAX
	 */
	@XmlElement
	private java.lang.String G2_XMAX;

	/** 
	 * M_CALS_T.G2_YMAX
	 */
	@XmlElement
	private java.lang.String G2_YMAX;

	/** 
	 * M_CALS_T.FACIL_ID
	 */
	@XmlElement
	private java.lang.String FACIL_ID;

	/** 
	 * M_CALS_T.BRDG_SEQ
	 */
	@XmlElement
	private java.lang.String BRDG_SEQ;

	/** 
	 * M_CALS_T.BRDG_NAME
	 */
	@XmlElement
	private java.lang.String BRDG_NAME;

	/** 
	 * M_CALS_T.ROAD_CODE
	 */
	@XmlElement
	private java.lang.String ROAD_CODE;

	/** 
	 * M_CALS_T.ROAD_NAME
	 */
	@XmlElement
	private java.lang.String ROAD_NAME;

	/** 
	 * M_CALS_T.ORG_TYPE
	 */
	@XmlElement
	private java.lang.String ORG_TYPE;

	/** 
	 * M_CALS_T.TYPE_NAME
	 */
	@XmlElement
	private java.lang.String TYPE_NAME;

	/** 
	 * M_CALS_T.ORG_OWN
	 */
	@XmlElement
	private java.lang.String ORG_OWN;

	/** 
	 * M_CALS_T.ORG_MANAGE
	 */
	@XmlElement
	private java.lang.String ORG_MANAGE;

	/** 
	 * M_CALS_T.FACIL_CLAS
	 */
	@XmlElement
	private java.lang.String FACIL_CLAS;

	/** 
	 * M_CALS_T.CLASS_NAME
	 */
	@XmlElement
	private java.lang.String CLASS_NAME;

	/** 
	 * M_CALS_T.BRDG_TYPE
	 */
	@XmlElement
	private java.lang.String BRDG_TYPE;

	/** 
	 * M_CALS_T.BRDG_TYPENM
	 */
	@XmlElement
	private java.lang.String BRDG_TYPENM;

	/** 
	 * M_CALS_T.BJCODE
	 */
	@XmlElement
	private java.lang.String BJCODE;
	
	/** 
	 * M_CALS_T.BJNAME
	 */
	@XmlElement
	private java.lang.String BJNAME;
	
	/** 
	 * M_CALS_T.ST_BJCODE
	 */
	@XmlElement
	private java.lang.String ST_BJCODE;
	
	/** 
	 * M_CALS_T.ST_LOC
	 */
	@XmlElement
	private java.lang.String ST_LOC;
	
	/** 
	 * M_CALS_T.ED_BJCODE
	 */
	@XmlElement
	private java.lang.String ED_BJCODE;
	
	/** 
	 * M_CALS_T.ED_LOC
	 */
	@XmlElement
	private java.lang.String ED_LOC;
	
	/** 
	 * M_CALS_T.MILE_AGE
	 */
	@XmlElement
	private java.lang.String MILE_AGE;
	
	/** 
	 * M_CALS_T.POST_ID
	 */
	@XmlElement
	private java.lang.String POST_ID;
	
	/** 
	 * M_CALS_T.POS_X
	 */
	@XmlElement
	private java.lang.String POS_X;
	
	/** 
	 * M_CALS_T.POS_Y
	 */
	@XmlElement
	private java.lang.String POS_Y;
	
	/** 
	 * M_CALS_T.SPOS_X
	 */
	@XmlElement
	private java.lang.String SPOS_X;
	
	/** 
	 * M_CALS_T.SPOS_Y
	 */
	@XmlElement
	private java.lang.String SPOS_Y;
	
	/** 
	 * M_CALS_T.EPOS_X
	 */
	@XmlElement
	private java.lang.String EPOS_X;
	
	/** 
	 * M_CALS_T.EPOS_Y
	 */
	@XmlElement
	private java.lang.String EPOS_Y;
	
	/** 
	 * M_CALS_T.ST_ORGCODE
	 */
	@XmlElement
	private java.lang.String ST_ORGCODE;
	
	/** 
	 * M_CALS_T.ST_ORGLOC
	 */
	@XmlElement
	private java.lang.String ST_ORGLOC;
	
	/** 
	 * M_CALS_T.ED_ORGCODE
	 */
	@XmlElement
	private java.lang.String ED_ORGCODE;
	
	/** 
	 * M_CALS_T.ED_ORGLOC
	 */
	@XmlElement
	private java.lang.String ED_ORGLOC;
	
	@XmlElement
	private java.lang.String SPAN_NUM;
	
	@XmlElement
	private java.lang.String DECK_PAVTY_NAME;
	
	@XmlElement
	private java.lang.String DECK_PAV;
	
	/**
	 * M_CALS_T.GID 
	 * @return
	 */
	@JsonProperty(value="GID") 
	public java.lang.String getGID() {
		return this.GID;
	}
 
	 /**
	 * M_CALS_T.GID 
	 * @param g2Id
	 */
	public void setGID(java.lang.String gId) {
		this.GID = gId;
	}

	/**
	 * M_CALS_T.G2_DATASETID 
	 * @return
	 */
	@JsonProperty(value="G2_DATASETID") 
	public java.lang.String getG2_DATASETID() {
		return this.G2_DATASETID;
	}
 
	 /**
	 * M_CALS_T.G2_DATASETID 
	 * @param g2Datasetid
	 */
	public void setG2_DATASETID(java.lang.String g2Datasetid) {
		this.G2_DATASETID = g2Datasetid;
	}

	/**
	 * M_CALS_T.G2_SPATIALTYPE 
	 * @return
	 */
	@JsonProperty(value="G2_SPATIALTYPE") 
	public java.lang.String getG2_SPATIALTYPE() {
		return this.G2_SPATIALTYPE;
	}
 
	 /**
	 * M_CALS_T.G2_SPATIALTYPE 
	 * @param g2Spatialtype
	 */
	public void setG2_SPATIALTYPE(java.lang.String g2Spatialtype) {
		this.G2_SPATIALTYPE = g2Spatialtype;
	}

	/**
	 * M_CALS_T.G2_XMIN 
	 * @return
	 */
	@JsonProperty(value="G2_XMIN") 
	public java.lang.String getG2_XMIN() {
		return this.G2_XMIN;
	}
 
	 /**
	 * M_CALS_T.G2_XMIN 
	 * @param g2Xmin
	 */
	public void setG2_XMIN(java.lang.String g2Xmin) {
		this.G2_XMIN = g2Xmin;
	}

	/**
	 * M_CALS_T.G2_YMIN 
	 * @return
	 */
	@JsonProperty(value="G2_YMIN") 
	public java.lang.String getG2_YMIN() {
		return this.G2_YMIN;
	}
 
	 /**
	 * M_CALS_T.G2_YMIN 
	 * @param g2Ymin
	 */
	public void setG2_YMIN(java.lang.String g2Ymin) {
		this.G2_YMIN = g2Ymin;
	}

	/**
	 * M_CALS_T.G2_XMAX 
	 * @return
	 */
	@JsonProperty(value="G2_XMAX") 
	public java.lang.String getG2_XMAX() {
		return this.G2_XMAX;
	}
 
	 /**
	 * M_CALS_T.G2_XMAX 
	 * @param g2Xmax
	 */
	public void setG2_XMAX(java.lang.String g2Xmax) {
		this.G2_XMAX = g2Xmax;
	}

	/**
	 * M_CALS_T.G2_YMAX 
	 * @return
	 */
	@JsonProperty(value="G2_YMAX") 
	public java.lang.String getG2_YMAX() {
		return this.G2_YMAX;
	}
 
	 /**
	 * M_CALS_T.G2_YMAX 
	 * @param g2Ymax
	 */
	public void setG2_YMAX(java.lang.String g2Ymax) {
		this.G2_YMAX = g2Ymax;
	}

	/**
	 * M_CALS_T.FACIL_ID 
	 * @return
	 */
	@JsonProperty(value="FACIL_ID") 
	public java.lang.String getFACIL_ID() {
		return this.FACIL_ID;
	}

	public void setFACIL_ID(java.lang.String fACIL_ID) {
		this.FACIL_ID = fACIL_ID;
	}

	/**
	 * M_CALS_T.BRDG_SEQ 
	 * @return
	 */
	@JsonProperty(value="BRDG_SEQ") 
	public java.lang.String getBRDG_SEQ() {
		return this.BRDG_SEQ;
	}

	public void setBRDG_SEQ(java.lang.String bRDG_SEQ) {
		this.BRDG_SEQ = bRDG_SEQ;
	}

	/**
	 * M_CALS_T.BRDG_NAME 
	 * @return
	 */
	@JsonProperty(value="BRDG_NAME") 
	public java.lang.String getBRDG_NAME() {
		return this.BRDG_NAME;
	}

	public void setBRDG_NAME(java.lang.String bRDG_NAME) {
		this.BRDG_NAME = bRDG_NAME;
	}

	/**
	 * M_CALS_T.ROAD_CODE 
	 * @return
	 */
	@JsonProperty(value="ROAD_CODE") 
	public java.lang.String getROAD_CODE() {
		return this.ROAD_CODE;
	}

	public void setROAD_CODE(java.lang.String rOAD_CODE) {
		this.ROAD_CODE = rOAD_CODE;
	}

	/**
	 * M_CALS_T.ROAD_NAME 
	 * @return
	 */
	@JsonProperty(value="ROAD_NAME") 
	public java.lang.String getROAD_NAME() {
		return this.ROAD_NAME;
	}

	public void setROAD_NAME(java.lang.String rOAD_NAME) {
		this.ROAD_NAME = rOAD_NAME;
	}

	/**
	 * M_CALS_T.ORG_TYPE 
	 * @return
	 */
	@JsonProperty(value="ORG_TYPE") 
	public java.lang.String getORG_TYPE() {
		return this.ORG_TYPE;
	}

	public void setORG_TYPE(java.lang.String oRG_TYPE) {
		this.ORG_TYPE = oRG_TYPE;
	}

	/**
	 * M_CALS_T.TYPE_NAME 
	 * @return
	 */
	@JsonProperty(value="TYPE_NAME") 
	public java.lang.String getTYPE_NAME() {
		return this.TYPE_NAME;
	}

	public void setTYPE_NAME(java.lang.String tYPE_NAME) {
		this.TYPE_NAME = tYPE_NAME;
	}

	/**
	 * M_CALS_T.ORG_OWN 
	 * @return
	 */
	@JsonProperty(value="ORG_OWN") 
	public java.lang.String getORG_OWN() {
		return this.ORG_OWN;
	}

	public void setORG_OWN(java.lang.String oRG_OWN) {
		this.ORG_OWN = oRG_OWN;
	}

	/**
	 * M_CALS_T.ORG_MANAGE 
	 * @return
	 */
	@JsonProperty(value="ORG_MANAGE") 
	public java.lang.String getORG_MANAGE() {
		return this.ORG_MANAGE;
	}

	public void setORG_MANAGE(java.lang.String oRG_MANAGE) {
		this.ORG_MANAGE = oRG_MANAGE;
	}

	/**
	 * M_CALS_T.FACIL_CLAS 
	 * @return
	 */
	@JsonProperty(value="FACIL_CLAS") 
	public java.lang.String getFACIL_CLAS() {
		return this.FACIL_CLAS;
	}

	public void setFACIL_CLAS(java.lang.String fACIL_CLAS) {
		this.FACIL_CLAS = fACIL_CLAS;
	}

	/**
	 * M_CALS_T.CLASS_NAME 
	 * @return
	 */
	@JsonProperty(value="CLASS_NAME") 
	public java.lang.String getCLASS_NAME() {
		return this.CLASS_NAME;
	}

	public void setCLASS_NAME(java.lang.String cLASS_NAME) {
		this.CLASS_NAME = cLASS_NAME;
	}

	/**
	 * M_CALS_T.BRDG_TYPE 
	 * @return
	 */
	@JsonProperty(value="BRDG_TYPE") 
	public java.lang.String getBRDG_TYPE() {
		return this.BRDG_TYPE;
	}

	public void setBRDG_TYPE(java.lang.String bRDG_TYPE) {
		this.BRDG_TYPE = bRDG_TYPE;
	}

	/**
	 * M_CALS_T.BRDG_TYPENM 
	 * @return
	 */
	@JsonProperty(value="BRDG_TYPENM") 
	public java.lang.String getBRDG_TYPENM() {
		return this.BRDG_TYPENM;
	}

	public void setBRDG_TYPENM(java.lang.String BRDG_TYPENM) {
		this.BRDG_TYPENM = BRDG_TYPENM;
	}

	/**
	 * M_CALS_T.BJCODE 
	 * @return
	 */
	@JsonProperty(value="BJCODE") 
	public java.lang.String getBJCODE() {
		return this.BJCODE;
	}

	public void setBJCODE(java.lang.String bJCODE) {
		this.BJCODE = bJCODE;
	}

	/**
	 * M_CALS_T.BJNAME 
	 * @return
	 */
	@JsonProperty(value="BJNAME") 
	public java.lang.String getBJNAME() {
		return this.BJNAME;
	}

	public void setBJNAME(java.lang.String bJNAME) {
		this.BJNAME = bJNAME;
	}

	/**
	 * M_CALS_T.ST_BJCODE 
	 * @return
	 */
	@JsonProperty(value="ST_BJCODE") 
	public java.lang.String getST_BJCODE() {
		return this.ST_BJCODE;
	}

	public void setST_BJCODE(java.lang.String sT_BJCODE) {
		this.ST_BJCODE = sT_BJCODE;
	}

	/**
	 * M_CALS_T.ST_LOC 
	 * @return
	 */
	@JsonProperty(value="ST_LOC") 
	public java.lang.String getST_LOC() {
		return this.ST_LOC;
	}

	public void setST_LOC(java.lang.String sT_LOC) {
		this.ST_LOC = sT_LOC;
	}

	/**
	 * M_CALS_T.ED_BJCODE 
	 * @return
	 */
	@JsonProperty(value="ED_BJCODE") 
	public java.lang.String getED_BJCODE() {
		return this.ED_BJCODE;
	}

	public void setED_BJCODE(java.lang.String eD_BJCODE) {
		this.ED_BJCODE = eD_BJCODE;
	}

	/**
	 * M_CALS_T.ED_LOC 
	 * @return
	 */
	@JsonProperty(value="ED_LOC") 
	public java.lang.String getED_LOC() {
		return this.ED_LOC;
	}

	public void setED_LOC(java.lang.String eD_LOC) {
		this.ED_LOC = eD_LOC;
	}

	/**
	 * M_CALS_T.MILE_AGE 
	 * @return
	 */
	@JsonProperty(value="MILE_AGE") 
	public java.lang.String getMILE_AGE() {
		return this.MILE_AGE;
	}

	public void setMILE_AGE(java.lang.String mILE_AGE) {
		this.MILE_AGE = mILE_AGE;
	}

	/**
	 * M_CALS_T.POST_ID 
	 * @return
	 */
	@JsonProperty(value="POST_ID") 
	public java.lang.String getPOST_ID() {
		return this.POST_ID;
	}

	public void setPOST_ID(java.lang.String pOST_ID) {
		this.POST_ID = pOST_ID;
	}

	/**
	 * M_CALS_T.POS_X 
	 * @return
	 */
	@JsonProperty(value="POS_X") 
	public java.lang.String getPOS_X() {
		return this.POS_X;
	}

	public void setPOS_X(java.lang.String pOS_X) {
		this.POS_X = pOS_X;
	}

	/**
	 * M_CALS_T.POS_Y 
	 * @return
	 */
	@JsonProperty(value="POS_Y") 
	public java.lang.String getPOS_Y() {
		return this.POS_Y;
	}

	public void setPOS_Y(java.lang.String pOS_Y) {
		this.POS_Y = pOS_Y;
	}

	/**
	 * M_CALS_T.SPOS_X 
	 * @return
	 */
	@JsonProperty(value="SPOS_X") 
	public java.lang.String getSPOS_X() {
		return this.SPOS_X;
	}

	public void setSPOS_X(java.lang.String sPOS_X) {
		this.SPOS_X = sPOS_X;
	}

	/**
	 * M_CALS_T.SPOS_Y 
	 * @return
	 */
	@JsonProperty(value="SPOS_Y") 
	public java.lang.String getSPOS_Y() {
		return this.SPOS_Y;
	}

	public void setSPOS_Y(java.lang.String sPOS_Y) {
		this.SPOS_Y = sPOS_Y;
	}

	/**
	 * M_CALS_T.ePOS_X 
	 * @return
	 */
	@JsonProperty(value="EPOS_X") 
	public java.lang.String getEPOS_X() {
		return this.EPOS_X;
	}

	public void setEPOS_X(java.lang.String ePOS_X) {
		this.EPOS_X = ePOS_X;
	}

	/**
	 * M_CALS_T.EPOS_Y 
	 * @return
	 */
	@JsonProperty(value="EPOS_Y") 
	public java.lang.String getEPOS_Y() {
		return this.EPOS_Y;
	}

	public void setEPOS_Y(java.lang.String ePOS_Y) {
		this.EPOS_Y = ePOS_Y;
	}

	/**
	 * M_CALS_T.ST_ORGCODE 
	 * @return
	 */
	@JsonProperty(value="ST_ORGCODE") 
	public java.lang.String getST_ORGCODE() {
		return this.ST_ORGCODE;
	}

	public void setST_ORGCODE(java.lang.String sT_ORGCODE) {
		this.ST_ORGCODE = sT_ORGCODE;
	}

	/**
	 * M_CALS_T.ST_ORGLOC 
	 * @return
	 */
	@JsonProperty(value="ST_ORGLOC") 
	public java.lang.String getST_ORGLOC() {
		return this.ST_ORGLOC;
	}

	public void setST_ORGLOC(java.lang.String sT_ORGLOC) {
		this.ST_ORGLOC = sT_ORGLOC;
	}

	/**
	 * M_CALS_T.ED_ORGCODE 
	 * @return
	 */
	@JsonProperty(value="ED_ORGCODE") 
	public java.lang.String getED_ORGCODE() {
		return this.ED_ORGCODE;
	}

	public void setED_ORGCODE(java.lang.String eD_ORGCODE) {
		this.ED_ORGCODE = eD_ORGCODE;
	}

	/**
	 * M_CALS_T.ED_ORGLOC 
	 * @return
	 */
	@JsonProperty(value="ED_ORGLOC") 
	public java.lang.String getED_ORGLOC() {
		return this.ED_ORGLOC;
	}

	public void setED_ORGLOC(java.lang.String eD_ORGLOC) {
		this.ED_ORGLOC = eD_ORGLOC;
	}

	@JsonProperty(value="SPAN_NUM") 
	public java.lang.String getSPAN_NUM() {
		return this.SPAN_NUM;
	}

	public void setSPAN_NUM(java.lang.String sPAN_NUM) {
		this.SPAN_NUM = sPAN_NUM;
	}
	
	@JsonProperty(value="DECK_PAVTY_NAME") 
	public java.lang.String getDECK_PAVTY_NAME() {
		return this.DECK_PAVTY_NAME;
	}

	public void setDECK_PAVTY_NAME(java.lang.String dECK_PAVTY_NAME) {
		this.DECK_PAVTY_NAME = dECK_PAVTY_NAME;
	}
	
	@JsonProperty(value="DECK_PAV") 
	public java.lang.String getDECK_PAV() {
		return this.DECK_PAV;
	}

	public void setDECK_PAV(java.lang.String dECK_PAV) {
		this.DECK_PAV = dECK_PAV;
	}
	
}
