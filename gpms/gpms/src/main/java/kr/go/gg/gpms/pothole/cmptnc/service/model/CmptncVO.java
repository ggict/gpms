
package kr.go.gg.gpms.pothole.cmptnc.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 관할구역 검색 VO
 * @Classname : CmptncVO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CmptncVO extends BaseVO {

	public CmptncVO() {
		super();
	}


	/**
	 * CMPTNC_ZONE.G2_ID,
	 * 관할_노선.G2_ID
	 */
	@XmlElement
	private java.lang.String G2_ID	       ;

	/**
	 * CMPTNC_ZONE.CPT_MNG_NO,
	 * 관할_노선.노선_관리_번호
	 */
	@XmlElement
	private java.lang.String CPT_MNG_NO	   ;

	/**
	 * CMPTNC_ZONE.DEPT_CODE,
	 * 관할_노선.관할_기관
	 */
	@XmlElement
	private java.lang.String DEPT_CODE     ;

	/**
	 * CMPTNC_ZONE.ROAD_GRAD,
	 * 관할_노선.도로_등급
	 */
	@XmlElement
	private java.lang.String ROAD_GRAD     ;

	/**
	 * CMPTNC_ZONE.CHARGER_NM,
	 * 관할_노선.담당자_명
	 */
	@XmlElement
	private java.lang.String CHARGER_NM	   ;


	/**
	 * CMPTNC_ZONE.PRIOR_RANK,
	 * 관할_노선.
	 */
	@XmlElement
	private java.lang.String PRIOR_RANK	       ;


	/**
	 * CMPTNC_ZONE.CTTPC,
	 * 관할_노선.연락처
	 */
	@XmlElement
	private java.lang.String CTTPC	       ;

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.면적
	 */
	@XmlElement
	private java.lang.String AR	           ;



	/**
	 * CMPTNC_ZONE.ROUTE_NO,
	 * 관할_노선.노선_번호
	 */
	@XmlElement
	private java.lang.String ROUTE_NO	     ;

	/**
	 * CMPTNC_ZONE.ROUTE_NM,
	 * 관할_노선.노선_명
	 */
	@XmlElement
	private java.lang.String ROUTE_NM	     ;

	/**
	 * CMPTNC_ZONE.STRTPT_NM,
	 * 관할_노선.시점_명
	 */
	@XmlElement
	private java.lang.String STRTPT_NM     ;

	/**
	 * CMPTNC_ZONE.ENDPT_NM,
	 * 관할_노선.종점_명
	 */
	@XmlElement
	private java.lang.String ENDPT_NM	     ;


	/**
	 * CMPTNC_ZONE.CPTC_ISTT,
	 * 관할_노선.관할_기관
	 */
	@XmlElement
	private java.lang.String CPTC_ISTT     ;


	/**
	 * CMPTNC_ZONE.CHARGER_NM,
	 * 관할_노선.담당자_명_조건검색
	 */
	@XmlElement
	private java.lang.String SCH_CHARGER_NM	   ;



	/**
	 * CMPTNC_ZONE.LEN,
	 * 관할_노선.연장
	 */
	@XmlElement
	private java.lang.String LEN	         ;


	/**
	 * TC_DEPT,
	 * 관할_노선.지역 정보
	 */
	@XmlElement
	private String LOWEST_DEPT_NM   ;


	// ==================== 로그 ====================

	/**
	 * TH_CMPTNC_ZONE.HIST_MNG_NO
	 * 관할_노선_로그.지역 정보
	 */
	@XmlElement
	private String HIST_MNG_NO   ;

	/**
	 * TH_CMPTNC_ZONE.USE_AT
	 * 관할_노선_로그.사용여부
	 */
	@XmlElement
	private String USE_AT   ;

	/**
	 * TH_CMPTNC_ZONE.DELETE_AT
	 * 관할_노선_로그.삭제여부
	 */
	@XmlElement
	private String DELETE_AT   ;

	/**
	 * TH_CMPTNC_ZONE.CRTR_NO
	 * 관할_노선_로그.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO   ;

	/**
	 * TH_CMPTNC_ZONE.CREAT_DE
	 * 관할_노선_로그.생성_일자
	 */
	@XmlElement
	private String CREAT_DE   ;


    /*
     * TH_CMPTNC_ZONE.sidxexcel
     * 엑셀정렬기준
     * */
    @XmlElement
    private java.lang.String sidxexcel;

    /*
     * TH_CMPTNC_ZONE.sordexcel
     * 엑셀정렬
     * */
    @XmlElement
    private java.lang.String sordexcel;


	// ==================== 관할_구역_담당자정보 ====================

    /*
     * TH_CMPTNC_ZONE_POC.POC_MNG_NO
     * 엑셀정렬기준
     * */
    @XmlElement
    private java.lang.String POC_MNG_NO;




    @JsonProperty(value="PRIOR_RANK")
    public java.lang.String getPRIOR_RANK() {
		return PRIOR_RANK;
	}
	public void setPRIOR_RANK(java.lang.String pRIOR_RANK) {
		PRIOR_RANK = pRIOR_RANK;
	}

	@JsonProperty(value="sidxexcel")
	public java.lang.String getSidxexcel() {
		return sidxexcel;
	}
	public void setSidxexcel(java.lang.String sidxexcel) {
		this.sidxexcel = sidxexcel;
	}
	@JsonProperty(value="sordexcel")
	public java.lang.String getSordexcel() {
		return sordexcel;
	}
	public void setSordexcel(java.lang.String sordexcel) {
		this.sordexcel = sordexcel;
	}


	/**
	 * CMPTNC_ZONE.CPTC_ISTT,
	 * 관할_노선.관할_기관
	 */
	@JsonProperty(value="CPTC_ISTT")
	public java.lang.String getCPTC_ISTT() {
		return CPTC_ISTT;
	}
	public void setCPTC_ISTT(java.lang.String cPTC_ISTT) {
		CPTC_ISTT = cPTC_ISTT;
	}

	/**
	 * TH_CMPTNC_ZONE.HIST_MNG_NO
	 * 관할_노선_로그.지역 정보
	 */
	@JsonProperty(value="HIST_MNG_NO")
	public String getHIST_MNG_NO() {
		return HIST_MNG_NO;
	}

	public void setHIST_MNG_NO(String hIST_MNG_NO) {
		HIST_MNG_NO = hIST_MNG_NO;
	}

	/**
	 * TH_CMPTNC_ZONE.USE_AT
	 * 관할_노선_로그.사용여부
	 */
	@JsonProperty(value="USE_AT")
	public String getUSE_AT() {
		return USE_AT;
	}

	public void setUSE_AT(String uSE_AT) {
		USE_AT = uSE_AT;
	}

	/**
	 * TH_CMPTNC_ZONE.DELETE_AT
	 * 관할_노선_로그.삭제여부
	 */
	@JsonProperty(value="DELETE_AT")
	public String getDELETE_AT() {
		return DELETE_AT;
	}

	public void setDELETE_AT(String dELETE_AT) {
		DELETE_AT = dELETE_AT;
	}

	/**
	 * TH_CMPTNC_ZONE.CRTR_NO
	 * 관할_노선_로그.생성자_번호
	 */
	@JsonProperty(value="CRTR_NO")
	public String getCRTR_NO() {
		return CRTR_NO;
	}

	public void setCRTR_NO(String cRTR_NO) {
		CRTR_NO = cRTR_NO;
	}
	/**
	 * TH_CMPTNC_ZONE.CREAT_DE
	 * 관할_노선_로그.생성_일자
	 */
	@JsonProperty(value="CREAT_DE")
	public String getCREAT_DE() {
		return CREAT_DE;
	}

	public void setCREAT_DE(String cREAT_DE) {
		CREAT_DE = cREAT_DE;
	}

	/**
	 * TC_DEPT.LOWEST_DEPT_NM,
	 * 관할_노선.지역 정보
	 */
	@JsonProperty(value="LOWEST_DEPT_NM")
	public String getLOWEST_DEPT_NM() {
		return LOWEST_DEPT_NM;
	}

	public void setLOWEST_DEPT_NM(String lOWEST_DEPT_NM) {
		LOWEST_DEPT_NM = lOWEST_DEPT_NM;
	}


	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.G2_ID
	 */
	@JsonProperty(value="G2_ID")
	public java.lang.String getG2_ID() {
		return G2_ID;
	}

	public void setG2_ID(java.lang.String g2_ID) {
		G2_ID = g2_ID;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.노선_관리_번호
	 */
	@JsonProperty(value="CPT_MNG_NO")
	public java.lang.String getCPT_MNG_NO() {
		return CPT_MNG_NO;
	}

	public void setCPT_MNG_NO(java.lang.String cPT_MNG_NO) {
		CPT_MNG_NO = cPT_MNG_NO;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.도로_등급
	 */
	@JsonProperty(value="ROAD_GRAD")
	public java.lang.String getROAD_GRAD() {
		return ROAD_GRAD;
	}

	public void setROAD_GRAD(java.lang.String rOAD_GRAD) {
		ROAD_GRAD = rOAD_GRAD;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.노선_번호
	 */
	@JsonProperty(value="ROUTE_NO")
	public java.lang.String getROUTE_NO() {
		return ROUTE_NO;
	}

	public void setROUTE_NO(java.lang.String rOUTE_NO) {
		ROUTE_NO = rOUTE_NO;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.노선_명
	 */
	@JsonProperty(value="ROUTE_NM")
	public java.lang.String getROUTE_NM() {
		return ROUTE_NM;
	}

	public void setROUTE_NM(java.lang.String rOUTE_NM) {
		ROUTE_NM = rOUTE_NM;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.시점_명
	 */
	@JsonProperty(value="STRTPT_NM")
	public java.lang.String getSTRTPT_NM() {
		return STRTPT_NM;
	}

	public void setSTRTPT_NM(java.lang.String sTRTPT_NM) {
		STRTPT_NM = sTRTPT_NM;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.종점_명
	 */
	@JsonProperty(value="ENDPT_NM")
	public java.lang.String getENDPT_NM() {
		return ENDPT_NM;
	}

	public void setENDPT_NM(java.lang.String eNDPT_NM) {
		ENDPT_NM = eNDPT_NM;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.관할_기관
	 */
	@JsonProperty(value="DEPT_CODE")
	public java.lang.String getDEPT_CODE() {
		return DEPT_CODE;
	}

	public void setDEPT_CODE(java.lang.String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.담당자_명
	 */
	@JsonProperty(value="CHARGER_NM")
	public java.lang.String getCHARGER_NM() {
		return CHARGER_NM;
	}

	public void setCHARGER_NM(java.lang.String cHARGER_NM) {
		CHARGER_NM = cHARGER_NM;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.연락처
	 */
	@JsonProperty(value="CTTPC")
	public java.lang.String getCTTPC() {
		return CTTPC;
	}

	public void setCTTPC(java.lang.String cTTPC) {
		CTTPC = cTTPC;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.연장
	 */
	@JsonProperty(value="LEN")
	public java.lang.String getLEN() {
		return LEN;
	}

	public void setLEN(java.lang.String lEN) {
		LEN = lEN;
	}

	/**
	 * CMPTNC_ZONE.AR,
	 * 관할_노선.면적
	 */
	@JsonProperty(value="AR")
	public java.lang.String getAR() {
		return AR;
	}

	public void setAR(java.lang.String aR) {
		AR = aR;
	}

	@JsonProperty(value="SCH_CHARGER_NM")
	public java.lang.String getSCH_CHARGER_NM() {
		return SCH_CHARGER_NM;
	}
	public void setSCH_CHARGER_NM(java.lang.String sCH_CHARGER_NM) {
		SCH_CHARGER_NM = sCH_CHARGER_NM;
	}


	@JsonProperty(value="POC_MNG_NO")
    public java.lang.String getPOC_MNG_NO() {
		return POC_MNG_NO;
	}
	public void setPOC_MNG_NO(java.lang.String pOC_MNG_NO) {
		POC_MNG_NO = pOC_MNG_NO;
	}



}
