package kr.go.gg.gpms.srvyunsection.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.go.gg.gpms.base.model.BaseVO;

public class SrvyUnSectionVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = -2359182614123730081L;

	private String ROAD_NO;
	
	private String ROAD_NAME;
	
	private String ST_POINT;
	
	private String ED_POINT;
	
	private String ROAD_L;
	
	private String ROUTE_CODE;
	
	private String SRVY_YEAR;
	
	private String TOTAL_ROAD_L;
	
	private String DO_MANAGE_SCTN_LEN;
	
	private String PERSENT;
	
	private String GEOJSON;
	
	private transient int TOTAL_COUNT;

	@JsonProperty(value="ROUTE_CODE")
	public String getROUTE_CODE() {
		return ROUTE_CODE;
	}

	public void setROUTE_CODE(String rOUTE_CODE) {
		ROUTE_CODE = rOUTE_CODE;
	}

	@JsonProperty(value="ROAD_NO")
	public String getROAD_NO() {
		return ROAD_NO;
	}

	public void setROAD_NO(String rOAD_NO) {
		ROAD_NO = rOAD_NO;
	}
	
	@JsonProperty(value="ROAD_NAME")
	public String getROAD_NAME() {
		return ROAD_NAME;
	}

	public void setROAD_NAME(String rOAD_NAME) {
		ROAD_NAME = rOAD_NAME;
	}

	@JsonProperty(value="ST_POINT")
	public String getST_POINT() {
		return ST_POINT;
	}

	public void setST_POINT(String sT_POINT) {
		ST_POINT = sT_POINT;
	}

	@JsonProperty(value="ED_POINT")
	public String getED_POINT() {
		return ED_POINT;
	}

	public void setED_POINT(String eD_POINT) {
		ED_POINT = eD_POINT;
	}

	@JsonProperty(value="ROAD_L")
	public String getROAD_L() {
		return ROAD_L;
	}

	public void setROAD_L(String rOAD_L) {
		ROAD_L = rOAD_L;
	}

	@JsonProperty(value="TOTAL_ROAD_L")
	public String getTOTAL_ROAD_L() {
		return TOTAL_ROAD_L;
	}

	public void setTOTAL_ROAD_L(String tOTAL_ROAD_L) {
		TOTAL_ROAD_L = tOTAL_ROAD_L;
	}

	@JsonProperty(value="PERSENT")
	public String getPERSENT() {
		return PERSENT;
	}

	public void setPERSENT(String pERSENT) {
		PERSENT = pERSENT;
	}

	@JsonProperty(value="GEOJSON")
	public String getGEOJSON() {
		return GEOJSON;
	}

	public void setGEOJSON(String gEOJSON) {
		GEOJSON = gEOJSON;
	}

	@JsonProperty(value="SRVY_YEAR")
	public String getSRVY_YEAR() {
		return SRVY_YEAR;
	}

	public void setSRVY_YEAR(String sRVY_YEAR) {
		SRVY_YEAR = sRVY_YEAR;
	}

	@JsonProperty(value="TOTAL_COUNT")
	public int getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}

	public void setTOTAL_COUNT(int tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}

	@JsonProperty(value="DO_MANAGE_SCTN_LEN")
	public String getDO_MANAGE_SCTN_LEN() {
		return DO_MANAGE_SCTN_LEN;
	}

	public void setDO_MANAGE_SCTN_LEN(String dO_MANAGE_SCTN_LEN) {
		DO_MANAGE_SCTN_LEN = dO_MANAGE_SCTN_LEN;
	}
	
	
	
	
}
