
package kr.go.gg.gpms.srvyrequstsctncellinfo.service.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 조사_요청_구간_셀_정보
 *
 * @Class Name : SrvyRequstSctnCellInfoVO.java
 * @Description : SrvyRequstSctnCellInfo VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SrvyRequstSctnCellInfoVO extends BaseVO {

	public SrvyRequstSctnCellInfoVO() {
		super();
	}
	
	/** 
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간_셀_정보.조사_요청_구간_번호
	 */
	@XmlElement
	private java.lang.String SRVY_REQUST_SCTN_NO;

	/** 
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.PAV_CELL_ID, 
	 * 조사_요청_구간_셀_정보.포장_셀_ID
	 */
	@XmlElement
	private java.lang.String PAV_CELL_ID;

	/** 
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.CRTR_NO, 
	 * 조사_요청_구간_셀_정보.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.CREAT_DT, 
	 * 조사_요청_구간_셀_정보.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	@XmlElement
	private List<String> PAV_CELL_ID_LIST;
	
	@XmlElement
	private java.lang.String PAV_CELL_IDS;
	
	@XmlElement
	private java.lang.String ROUTE_CODE;
	
	/**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간_셀_정보.조사_요청_구간_번호
	 * @return
	 */
	@JsonProperty(value="SRVY_REQUST_SCTN_NO") 
	public java.lang.String getSRVY_REQUST_SCTN_NO() {
		return this.SRVY_REQUST_SCTN_NO;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.SRVY_REQUST_SCTN_NO, 
	 * 조사_요청_구간_셀_정보.조사_요청_구간_번호
	 * @param srvyRequstSctnNo
	 */
	public void setSRVY_REQUST_SCTN_NO(java.lang.String srvyRequstSctnNo) {
		this.SRVY_REQUST_SCTN_NO = srvyRequstSctnNo;
	}

	/**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.PAV_CELL_ID, 
	 * 조사_요청_구간_셀_정보.포장_셀_ID 
	 * @return
	 */
	@JsonProperty(value="PAV_CELL_ID") 
	public java.lang.String getPAV_CELL_ID() {
		return this.PAV_CELL_ID;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.PAV_CELL_ID, 
	 * 조사_요청_구간_셀_정보.포장_셀_ID 
	 * @param pavCellId
	 */
	public void setPAV_CELL_ID(java.lang.String pavCellId) {
		this.PAV_CELL_ID = pavCellId;
	}

	/**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.CRTR_NO, 
	 * 조사_요청_구간_셀_정보.생성자_번호
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.CRTR_NO, 
	 * 조사_요청_구간_셀_정보.생성자_번호
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_SRVY_REQUST_SCTN_CELL_INFO.CREAT_DT, 
	 * 조사_요청_구간_셀_정보.생성_일시
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_SRVY_REQUST_SCTN_CELL_INFOCREAT_DT, 
	 * 조사_요청_구간_셀_정보.생성_일시
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	@JsonProperty(value="PAV_CELL_ID_LIST") 
	public List<String> getPAV_CELL_ID_LIST() {
		return PAV_CELL_ID_LIST;
	}

	public void setPAV_CELL_ID_LIST(List<String> pAV_CELL_ID_LIST) {
		this.PAV_CELL_ID_LIST = pAV_CELL_ID_LIST;
	}
	
	@JsonProperty(value="PAV_CELL_IDS")
	public java.lang.String getPAV_CELL_IDS() {
		return PAV_CELL_IDS;
	}

	public void setPAV_CELL_IDS(java.lang.String pavCellIds) {
		this.PAV_CELL_IDS = pavCellIds;
		List<String> cellIdList = new ArrayList<String>();
		if(this.PAV_CELL_IDS!=null && this.PAV_CELL_IDS.trim().length()>0){
			
			String[] cellidArray = this.PAV_CELL_IDS.split(",");
			if(cellidArray!=null && cellidArray.length>0){
				for(String cellId : cellidArray){
					if(cellId.trim().length()>0){
						cellIdList.add(cellId);	
					}		
				}
			}
		}
		
		this.PAV_CELL_ID_LIST = cellIdList;
	}
	
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return ROUTE_CODE;
	}

	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

}
