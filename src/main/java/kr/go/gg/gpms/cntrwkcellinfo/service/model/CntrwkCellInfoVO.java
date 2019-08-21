
package kr.go.gg.gpms.cntrwkcellinfo.service.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 공사_셀_정보
 *
 * @Class Name : CntrwkCellInfoVO.java
 * @Description : CntrwkCellInfo VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-31
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CntrwkCellInfoVO extends BaseVO {

	public CntrwkCellInfoVO() {
		super();
	}
	
	/** 
	 * TN_CNTRWK_CELL_INFO.DETAIL_CNTRWK_ID, 
	 * 공사_셀_정보.세부_공사_ID
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_ID;

	/** 
	 * TN_CNTRWK_CELL_INFO.PAV_CELL_ID, 
	 * 공사_셀_정보.포장_셀_ID
	 */
	@XmlElement
	private java.lang.String PAV_CELL_ID;

	/** 
	 * TN_CNTRWK_CELL_INFO.PAV_YEAR, 
	 * 공사_셀_정보.포장_년도
	 */
	@XmlElement
	private java.lang.String PAV_YEAR;

	/** 
	 * TN_CNTRWK_CELL_INFO.CNTRWK_ID, 
	 * 공사_셀_정보.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;

	@XmlElement
	private List<String> PAV_CELL_ID_LIST;
	
	@XmlElement
	private java.lang.String PAV_CELL_IDS;
	
	/**
	 * TN_CNTRWK_CELL_INFO.DETAIL_CNTRWK_ID, 
	 * 공사_셀_정보.세부_공사_ID 
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_ID") 
	public java.lang.String getDETAIL_CNTRWK_ID() {
		return this.DETAIL_CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTRWK_CELL_INFO.DETAIL_CNTRWK_ID, 
	 * 공사_셀_정보.세부_공사_ID 
	 * @param detailCntrwkId
	 */
	public void setDETAIL_CNTRWK_ID(java.lang.String detailCntrwkId) {
		this.DETAIL_CNTRWK_ID = detailCntrwkId;
	}

	/**
	 * TN_CNTRWK_CELL_INFO.PAV_CELL_ID, 
	 * 공사_셀_정보.포장_셀_ID 
	 * @return
	 */
	@JsonProperty(value="PAV_CELL_ID") 
	public java.lang.String getPAV_CELL_ID() {
		return this.PAV_CELL_ID;
	}
 
	 /**
	 * TN_CNTRWK_CELL_INFO.PAV_CELL_ID, 
	 * 공사_셀_정보.포장_셀_ID 
	 * @param pavCellId
	 */
	public void setPAV_CELL_ID(java.lang.String pavCellId) {
		this.PAV_CELL_ID = pavCellId;
	}

	/**
	 * TN_CNTRWK_CELL_INFO.PAV_YEAR, 
	 * 공사_셀_정보.포장_년도 
	 * @return
	 */
	@JsonProperty(value="PAV_YEAR") 
	public java.lang.String getPAV_YEAR() {
		return this.PAV_YEAR;
	}
 
	 /**
	 * TN_CNTRWK_CELL_INFO.PAV_YEAR, 
	 * 공사_셀_정보.포장_년도 
	 * @param pavYear
	 */
	public void setPAV_YEAR(java.lang.String pavYear) {
		this.PAV_YEAR = pavYear;
	}

	/**
	 * TN_CNTRWK_CELL_INFO.CNTRWK_ID, 
	 * 공사_셀_정보.공사_ID 
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTRWK_CELL_INFO.CNTRWK_ID, 
	 * 공사_셀_정보.공사_ID 
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
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

}
