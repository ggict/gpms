package kr.go.gg.gpms.pothole.address.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 신고정보 보정 및 주소맵핑 VO
 * @Classname : AddressVO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 3. 8.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class AddressVO extends BaseVO {

    public AddressVO() {
        super();
    }

    /* 포트홀등록번호 */
    @XmlElement
    private java.lang.String POTHOLE_REGIST_NO;

    /* 좌표X */
    @XmlElement
    private java.lang.String POS_X;

    /* 좌표Y */
    @XmlElement
    private java.lang.String POS_Y;

    /* HEADING */
    @XmlElement
    private java.lang.String HEADING;

    /* 신고자전화번호 */
    @XmlElement
    private java.lang.String REPR_TEL;

    /* 신고자명 */
    @XmlElement
    private java.lang.String REPR_NM;

    /* 신고자차량번호 */
    @XmlElement
    private java.lang.String REPR_VEHC_NO;

    /* 접수내용 */
    @XmlElement
    private java.lang.String ACCEPT_CONT;

    /* 차량유형코드 */
    @XmlElement
    private java.lang.String VEHC_TYPE_CD;

    /* 신고일시 */
    @XmlElement
    private java.lang.String REP_DTIME;

    /* 신고구분 */
    @XmlElement
    private java.lang.String pthCd;


    @JsonProperty(value="POTHOLE_REGIST_NO")
    public java.lang.String getPOTHOLE_REGIST_NO() {
        return POTHOLE_REGIST_NO;
    }

    public void setPOTHOLE_REGIST_NO(java.lang.String pOTHOLE_REGIST_NO) {
        POTHOLE_REGIST_NO = pOTHOLE_REGIST_NO;
    }

    @JsonProperty(value="POS_X")
    public java.lang.String getPOS_X() {
        return POS_X;
    }

    public void setPOS_X(java.lang.String pOS_X) {
        POS_X = pOS_X;
    }

    @JsonProperty(value="POS_Y")
    public java.lang.String getPOS_Y() {
        return POS_Y;
    }

    public void setPOS_Y(java.lang.String pOS_Y) {
        POS_Y = pOS_Y;
    }

    @JsonProperty(value="HEADING")
    public java.lang.String getHEADING() {
        return HEADING;
    }

    public void setHEADING(java.lang.String hEADING) {
        HEADING = hEADING;
    }

    @JsonProperty(value="REPR_TEL")
    public java.lang.String getREPR_TEL() {
        return REPR_TEL;
    }

    public void setREPR_TEL(java.lang.String rEPR_TEL) {
        REPR_TEL = rEPR_TEL;
    }

    @JsonProperty(value="REPR_NM")
    public java.lang.String getREPR_NM() {
        return REPR_NM;
    }

    public void setREPR_NM(java.lang.String rEPR_NM) {
        REPR_NM = rEPR_NM;
    }

    @JsonProperty(value="REPR_VEHC_NO")
    public java.lang.String getREPR_VEHC_NO() {
        return REPR_VEHC_NO;
    }

    public void setREPR_VEHC_NO(java.lang.String rEPR_VEHC_NO) {
        REPR_VEHC_NO = rEPR_VEHC_NO;
    }

    @JsonProperty(value="ACCEPT_CONT")
    public java.lang.String getACCEPT_CONT() {
        return ACCEPT_CONT;
    }

    public void setACCEPT_CONT(java.lang.String aCCEPT_CONT) {
        ACCEPT_CONT = aCCEPT_CONT;
    }

    @JsonProperty(value="VEHC_TYPE_CD")
    public java.lang.String getVEHC_TYPE_CD() {
        return VEHC_TYPE_CD;
    }

    public void setVEHC_TYPE_CD(java.lang.String vEHC_TYPE_CD) {
        VEHC_TYPE_CD = vEHC_TYPE_CD;
    }

    @JsonProperty(value="REP_DTIME")
    public java.lang.String getREP_DTIME() {
        return REP_DTIME;
    }

    public void setREP_DTIME(java.lang.String rEP_DTIME) {
        REP_DTIME = rEP_DTIME;
    }

    @JsonProperty(value="pthCd")
    public java.lang.String getPthCd() {
        return pthCd;
    }

    public void setPthCd(java.lang.String pthCd) {
    	this.pthCd = pthCd;
    }
}