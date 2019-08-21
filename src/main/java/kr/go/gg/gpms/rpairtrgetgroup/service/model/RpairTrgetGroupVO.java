
package kr.go.gg.gpms.rpairtrgetgroup.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetVO;
/**
 * 보수_대상_항목_그룹
 *
 * @Class Name : RpairTrgetGroupVO.java
 * @Description : RpairTrgetGroup VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *  	RpairTrgetGroupDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairTrgetGroupVO extends RpairTrgetVO {

	public RpairTrgetGroupVO() {
		super();
	}
	
	/** 
	 * TN_RPAIR_TRGET_GROUP.GROUP_ITEM_NO, 
	 * 보수_대상_항목_그룹.그룹_항목_번호
	 */
	@XmlElement
	private String GROUP_ITEM_NO;
	
	
	/** 
	 * TN_RPAIR_TRGET_GROUP.NODE_CO, 
	 * 보수_대상_항목_그룹.노드 개수
	 */
	@XmlElement
	private String NODE_CO;
	
	/**
	 * TN_RPAIR_TRGET_GROUP.GROUP_ITEM_NO, 
	 * 보수_대상_항목_그룹.그룹_항목_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="GROUP_ITEM_NO") 
	public String getGROUP_ITEM_NO() {
		return GROUP_ITEM_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET_GROUP.GROUP_ITEM_NO, 
	 * 보수_대상_항목_그룹.그룹_항목_번호 값설정
	 * @param groupItemNo
	 */
	public void setGROUP_ITEM_NO(String groupItemNo) {
		GROUP_ITEM_NO = groupItemNo;
	}
	
	
	@JsonProperty(value="NODE_CO") 
	public String getNODE_CO() {
		return NODE_CO;
	}

	public void setNODE_CO(String nODE_CO) {
		NODE_CO = nODE_CO;
	}

	
}
