
package kr.go.gg.gpms.rpairtrgetgroup.service.model;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	 * TN_RPAIR_TRGET_GROUP.MNG_RD_CD,
	 * 보수_대상_항목_그룹.관리_도로_코드
	 */
	@XmlElement
	private String MNG_RD_CD;

	/**
	 * TN_RPAIR_TRGET_GROUP.MNG_RD_NM,
	 * 보수_대상_항목_그룹.관리_도로_명
	 */
	@XmlElement
	private String MNG_RD_NM;

	/**
	 * TN_RPAIR_TRGET_GROUP.POTHOLE_QY,
	 * 보수_대상_항목_그룹.포트홀_량
	 */
	@XmlElement
	private String POTHOLE_QY;

	/**
	 * TN_RPAIR_TRGET_GROUP.PRIORT,
	 * 보수_대상_항목_그룹.우선순위
	 */
	@XmlElement
	private String PRIORT;

	/**
	 * 셀ID
	 */
	@XmlElement
	private String CELL_ID;
	
	/**
	 * PAV_CELL_LEN
	 */
	@XmlElement
	private String PAV_CELL_LEN;
	
	/**
	 * PAV_CELL_IDS
	 */
	@XmlElement
	private String PAV_CELL_IDS;

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

	@JsonProperty(value="MNG_RD_CD")
    public String getMNG_RD_CD() {
        return MNG_RD_CD;
    }

    public void setMNG_RD_CD(String mNG_RD_CD) {
        MNG_RD_CD = mNG_RD_CD;
    }

    @JsonProperty(value="MNG_RD_NM")
    public String getMNG_RD_NM() {
        return MNG_RD_NM;
    }

    public void setMNG_RD_NM(String mNG_RD_NM) {
        MNG_RD_NM = mNG_RD_NM;
    }

    @JsonProperty(value="PRIORT")
    public String getPRIORT() {
        return PRIORT;
    }

    public void setPRIORT(String pRIORT) {
        PRIORT = pRIORT;
    }

    @JsonProperty(value="POTHOLE_QY")
    public String getPOTHOLE_QY() {
        return POTHOLE_QY;
    }

    public void setPOTHOLE_QY(String pOTHOLE_QY) {
        POTHOLE_QY = pOTHOLE_QY;
    }

    @JsonProperty(value="CELL_ID")
    public String getCELL_ID() {
        return CELL_ID;
    }

    public void setCELL_ID(String cELL_ID) {
        CELL_ID = cELL_ID;
    }

    @JsonProperty(value="PAV_CELL_LEN")
	public String getPAV_CELL_LEN() {
		return PAV_CELL_LEN;
	}

	public void setPAV_CELL_LEN(String pAV_CELL_LEN) {
		PAV_CELL_LEN = pAV_CELL_LEN;
	}

	@JsonProperty(value="PAV_CELL_IDS")
	public String getPAV_CELL_IDS() {
		return PAV_CELL_IDS;
	}

	public void setPAV_CELL_IDS(String pAV_CELL_IDS) {
		PAV_CELL_IDS = pAV_CELL_IDS;
	}
	
	


}
