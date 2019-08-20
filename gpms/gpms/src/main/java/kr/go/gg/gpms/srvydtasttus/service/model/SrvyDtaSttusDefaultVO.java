package kr.go.gg.gpms.srvydtasttus.service.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import kr.go.gg.gpms.base.model.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name : SrvyDtaSttusDefaultVO.java
 * @Description : SrvyDtaSttus Default VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SrvyDtaSttusDefaultVO extends BaseVO {
	
	//조사 년 월
	@XmlElement
	private java.lang.String SRVY_DT;
	//데이터 개수
	@XmlElement
	private java.lang.String DTA_CNT;
	
	
	@JsonProperty(value="SRVY_DT") 
	public java.lang.String getSRVY_DT() {
		return SRVY_DT;
	}
	
	public void setSRVY_DT(java.lang.String srvyDt) {
		this.SRVY_DT = srvyDt;
	}
	
	@JsonProperty(value="DTA_CNT") 
	public java.lang.String getDTA_CNT() {
		return DTA_CNT;
	}
	
	public void setDTA_CNT(java.lang.String dtaCnt) {
		this.DTA_CNT = dtaCnt;
	}
	
	
	
}
