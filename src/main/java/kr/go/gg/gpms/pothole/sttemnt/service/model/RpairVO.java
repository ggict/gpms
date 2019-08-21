package kr.go.gg.gpms.pothole.sttemnt.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 포트홀 신고관리 VO
 * @Classname : SttemntVO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairVO extends BaseVO {

	public RpairVO() {
		super();
	}

	// 보수전사진 BFEFlag
    @XmlElement
    private String BFEflag;

	@JsonProperty(value="BFEflag")
    public String getBFEflag() {
		return BFEflag;
	}

	public void setBFEflag(String bFEflag) {
		BFEflag = bFEflag;
	}

	// 보수후사진 AFTFlag
    @XmlElement
    private String AFTflag;

    @JsonProperty(value="AFTflag")
	public String getAFTflag() {
		return AFTflag;
	}

	public void setAFTflag(String aFTflag) {
		AFTflag = aFTflag;
	}

	/**
     * pthmode 포트홀_신고 접수경로별 조회 구분 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String pthmode = "T";

	/**
     * TN_POTHOLE_RPAIR.RPRDTLS_MNG_NO,
     * 포트홀_보수_내역.보수_내역_관리_번호
     */
    @XmlElement
	private java.lang.String RPRDTLS_MNG_NO;

    /**
     * TN_POTHOLE_RPAIR.CO_NO,
     * 포트홀_보수_내역.업체_번호
     */
    @XmlElement
	private java.lang.String CO_NO;

    /**
     * TN_POTHOLE_RPAIR.RPRSCL_WIDTH,
     * 포트홀_보수_내역.보수규모_가로
     */
    @XmlElement
	private java.lang.String RPRSCL_WIDTH;

    /**
     * TN_POTHOLE_RPAIR.RPRSCL_VRTICL,
     * 포트홀_보수_내역.보수규모_세로
     */
    @XmlElement
	private java.lang.String RPRSCL_VRTICL;

    /**
     * TN_POTHOLE_RPAIR.RPRSCL_AR,
     * 포트홀_보수_내역.보수규모_면적
     */
    @XmlElement
	private java.lang.String RPRSCL_AR;

	/**
     * TN_POTHOLE_RPAIR.RPRSCL_DP,
     * 포트홀_보수_내역.보수규모_깊이
     */
    @XmlElement
	private java.lang.String RPRSCL_DP;

	/**
     * TN_POTHOLE_RPAIR.DMG_TYPE,
     * 포트홀_보수_내역.파손_유형
     */
    @XmlElement
	private java.lang.String DMG_TYPE;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_AMOUNT,
     * 포트홀_보수_내역.보수_금액
     */
    @XmlElement
	private java.lang.String RPAIR_AMOUNT;

	/**
     * TN_POTHOLE_RPAIR.CNFIRM_DT,
     * 포트홀_보수_내역.확인_일시
     */
    @XmlElement
	private java.lang.String CNFIRM_DT;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_DT,
     * 포트홀_보수_내역.보수_일시
     */
    @XmlElement
	private java.lang.String RPAIR_DT;

	/**
     * TN_POTHOLE_RPAIR.RM,
     * 포트홀_보수_내역.비고
     */
    @XmlElement
	private java.lang.String RM;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_BFE_PHOTO,
     * 포트홀_보수_내역.보수_전_사진
     */
    @XmlElement
	private java.lang.String RPAIR_BFE_PHOTO;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_AFT_PHOTO,
     * 포트홀_보수_내역.보수_후_사진
     */
    @XmlElement
	private java.lang.String RPAIR_AFT_PHOTO;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_BFE_PHOTO,
     * 포트홀_보수_내역.보수_전_사진 경로
     */
    @XmlElement
	private String[] RPAIR_BFE_PHOTO_PATH;

	/**
     * TN_POTHOLE_RPAIR.RPAIR_AFT_PHOTO,
     * 포트홀_보수_내역.보수_후_사진 경로
     */
    @XmlElement
	private String[] RPAIR_AFT_PHOTO_PATH;




	/**
     * TN_POTHOLE_RPAIR.USE_AT,
     * 포트홀_보수_내역.사용_여부
     */
    @XmlElement
	private java.lang.String USE_AT;

	/**
     * TN_POTHOLE_RPAIR.DELETE_AT,
     * 포트홀_보수_내역.삭제_여부
     */
    @XmlElement
	private java.lang.String DELETE_AT;

	/**
     * TN_POTHOLE_RPAIR.CRTR_NO,
     * 포트홀_보수_내역.생성자_번호
     */
    @XmlElement
	private java.lang.String CRTR_NO;

	/**
     * TN_POTHOLE_RPAIR.CREAT_DE,
     * 포트홀_보수_내역.생성_일자
     */
    @XmlElement
	private java.lang.String CREAT_DE;

	/**
     * TN_POTHOLE_RPAIR.UPDUSR_NO,
     * 포트홀_보수_내역.수정자_번호
     */
    @XmlElement
	private java.lang.String UPDUSR_NO;

	/**
     * TN_POTHOLE_RPAIR.UPDT_DE,
     * 포트홀_보수_내역.수정_일자
     */
    @XmlElement
	private java.lang.String UPDT_DE;

    /**
     * TN_POTHOLE_RPAIR.DELETE_DE,
     * 포트홀_보수_내역.삭제_일자
     */
    @XmlElement
    private java.lang.String DELETE_DE;

    /**
     * TN_POTHOLE_RPAIR.PTH_RG_NO,
     * 포트홀_보수_내역.포트홀_등록_번호
     */
    @XmlElement
    private java.lang.String PTH_RG_NO;

    /**
     * TC_DEPT.LOWEST_DEPT_NM,
     * 부서.최하위_부서_명
     */
    @XmlElement
    private java.lang.String LOWEST_DEPT_NM;

    /**
     * TN_POTHOLE_STTEMNT.CHARGER_NM,
     * 포트홀_신고_내역.담당자
     */
    @XmlElement
    private java.lang.String CHARGER_NM;

    /**
     * TN_POTHOLE_STTEMNT.CTTPC,
     * 포트홀_신고_내역.연락처
     */
    @XmlElement
    private java.lang.String CTTPC;

    /**
     * TN_POTHOLE_STTEMNT.CTTPC,
     * 포트홀_신고_내역.연락처
     */
    @XmlElement
    private java.lang.String ROUTE_NM;


    /*
     * TN_POTHOLE_RPAIR.DAYCNT,
     * 포트홀_보수_내역.처리_일수
     * */
    @XmlElement
    private java.lang.String DAYCNT;

    /*
     * TN_POTHOLE_RPAIR.PROCESS_DT,
     * 포트홀_보수_내역.처리_일시
     * */
    @XmlElement
    private java.lang.String PROCESS_DT;

    @XmlElement
    private java.lang.String STTEMNT_DT;

    /*
     * pthmode 포트홀_신고 접수경로별 조회 구분 (2019년 고도화 사업)
     * */
    @JsonProperty(value="pthmode")
    public java.lang.String getPthmode() {
		return pthmode;
	}
	public void setPthmode(java.lang.String pthmode) {
		this.pthmode = pthmode;
	}


	@JsonProperty(value="RPRDTLS_MNG_NO")
    public java.lang.String getRPRDTLS_MNG_NO() {
        return RPRDTLS_MNG_NO;
    }

    public void setRPRDTLS_MNG_NO(java.lang.String rPRDTLS_MNG_NO) {
        RPRDTLS_MNG_NO = rPRDTLS_MNG_NO;
    }

    @JsonProperty(value="CO_NO")
    public java.lang.String getCO_NO() {
        return CO_NO;
    }

    public void setCO_NO(java.lang.String cO_NO) {
        CO_NO = cO_NO;
    }

    @JsonProperty(value="RPRSCL_WIDTH")
    public java.lang.String getRPRSCL_WIDTH() {
        return RPRSCL_WIDTH;
    }

    public void setRPRSCL_WIDTH(java.lang.String rPRSCL_WIDTH) {
        RPRSCL_WIDTH = rPRSCL_WIDTH;
    }

    @JsonProperty(value="RPRSCL_VRTICL")
    public java.lang.String getRPRSCL_VRTICL() {
        return RPRSCL_VRTICL;
    }

    public void setRPRSCL_VRTICL(java.lang.String rPRSCL_VRTICL) {
        RPRSCL_VRTICL = rPRSCL_VRTICL;
    }

    @JsonProperty(value="RPRSCL_AR")
    public java.lang.String getRPRSCL_AR() {
        return RPRSCL_AR;
    }

    public void setRPRSCL_AR(java.lang.String rPRSCL_AR) {
        RPRSCL_AR = rPRSCL_AR;
    }

    @JsonProperty(value="RPRSCL_DP")
    public java.lang.String getRPRSCL_DP() {
        return RPRSCL_DP;
    }

    public void setRPRSCL_DP(java.lang.String rPRSCL_DP) {
        RPRSCL_DP = rPRSCL_DP;
    }

    @JsonProperty(value="DMG_TYPE")
    public java.lang.String getDMG_TYPE() {
        return DMG_TYPE;
    }

    public void setDMG_TYPE(java.lang.String dMG_TYPE) {
        DMG_TYPE = dMG_TYPE;
    }

    @JsonProperty(value="RPAIR_AMOUNT")
    public java.lang.String getRPAIR_AMOUNT() {
        return RPAIR_AMOUNT;
    }

    public void setRPAIR_AMOUNT(java.lang.String rPAIR_AMOUNT) {
        RPAIR_AMOUNT = rPAIR_AMOUNT;
    }

    @JsonProperty(value="CNFIRM_DT")
    public java.lang.String getCNFIRM_DT() {
        return CNFIRM_DT;
    }

    public void setCNFIRM_DT(java.lang.String cNFIRM_DT) {
        CNFIRM_DT = cNFIRM_DT;
    }

    @JsonProperty(value="RPAIR_DT")
    public java.lang.String getRPAIR_DT() {
        return RPAIR_DT;
    }

    public void setRPAIR_DT(java.lang.String rPAIR_DT) {
        RPAIR_DT = rPAIR_DT;
    }

    @JsonProperty(value="RM")
    public java.lang.String getRM() {
        return RM;
    }

    public void setRM(java.lang.String rM) {
        RM = rM;
    }

    @JsonProperty(value="RPAIR_BFE_PHOTO")
    public java.lang.String getRPAIR_BFE_PHOTO() {
        return RPAIR_BFE_PHOTO;
    }

    public void setRPAIR_BFE_PHOTO(java.lang.String rPAIR_BFE_PHOTO) {
        RPAIR_BFE_PHOTO = rPAIR_BFE_PHOTO;
    }

    @JsonProperty(value="RPAIR_AFT_PHOTO")
    public java.lang.String getRPAIR_AFT_PHOTO() {
        return RPAIR_AFT_PHOTO;
    }

    public void setRPAIR_AFT_PHOTO(java.lang.String rPAIR_AFT_PHOTO) {
        RPAIR_AFT_PHOTO = rPAIR_AFT_PHOTO;
    }

    @JsonProperty(value="USE_AT")
    public java.lang.String getUSE_AT() {
        return USE_AT;
    }

    public void setUSE_AT(java.lang.String uSE_AT) {
        USE_AT = uSE_AT;
    }

    @JsonProperty(value="DELETE_AT")
    public java.lang.String getDELETE_AT() {
        return DELETE_AT;
    }

    public void setDELETE_AT(java.lang.String dELETE_AT) {
        DELETE_AT = dELETE_AT;
    }

    @JsonProperty(value="CRTR_NO")
    public java.lang.String getCRTR_NO() {
        return CRTR_NO;
    }

    public void setCRTR_NO(java.lang.String cRTR_NO) {
        CRTR_NO = cRTR_NO;
    }

    @JsonProperty(value="CREAT_DE")
    public java.lang.String getCREAT_DE() {
        return CREAT_DE;
    }

    public void setCREAT_DE(java.lang.String cREAT_DE) {
        CREAT_DE = cREAT_DE;
    }

    @JsonProperty(value="UPDUSR_NO")
    public java.lang.String getUPDUSR_NO() {
        return UPDUSR_NO;
    }

    public void setUPDUSR_NO(java.lang.String uPDUSR_NO) {
        UPDUSR_NO = uPDUSR_NO;
    }

    @JsonProperty(value="UPDT_DE")
    public java.lang.String getUPDT_DE() {
        return UPDT_DE;
    }

    public void setUPDT_DE(java.lang.String uPDT_DE) {
        UPDT_DE = uPDT_DE;
    }

    @JsonProperty(value="DELETE_DE")
    public java.lang.String getDELETE_DE() {
        return DELETE_DE;
    }

    public void setDELETE_DE(java.lang.String dELETE_DE) {
        DELETE_DE = dELETE_DE;
    }

    @JsonProperty(value="PTH_RG_NO")
    public java.lang.String getPTH_RG_NO() {
        return PTH_RG_NO;
    }

    public void setPTH_RG_NO(java.lang.String pTH_RG_NO) {
        PTH_RG_NO = pTH_RG_NO;
    }

    @JsonProperty(value="LOWEST_DEPT_NM")
    public java.lang.String getLOWEST_DEPT_NM() {
        return LOWEST_DEPT_NM;
    }

    public void setLOWEST_DEPT_NM(java.lang.String lOWEST_DEPT_NM) {
        LOWEST_DEPT_NM = lOWEST_DEPT_NM;
    }

    @JsonProperty(value="CHARGER_NM")
    public java.lang.String getCHARGER_NM() {
        return CHARGER_NM;
    }

    public void setCHARGER_NM(java.lang.String cHARGER_NM) {
        CHARGER_NM = cHARGER_NM;
    }

    @JsonProperty(value="CTTPC")
    public java.lang.String getCTTPC() {
        return CTTPC;
    }

    public void setCTTPC(java.lang.String cTTPC) {
        CTTPC = cTTPC;
    }

    @JsonProperty(value="ROUTE_NM")
    public java.lang.String getROUTE_NM() {
        return ROUTE_NM;
    }

    public void setROUTE_NM(java.lang.String rOUTE_NM) {
        ROUTE_NM = rOUTE_NM;
    }

    @JsonProperty(value="RPAIR_BFE_PHOTO_PATH")
	public java.lang.String[] getRPAIR_BFE_PHOTO_PATH() {
		return RPAIR_BFE_PHOTO_PATH;
	}

	public void setRPAIR_BFE_PHOTO_PATH(java.lang.String[] rPAIR_BFE_PHOTO_PATH) {
		RPAIR_BFE_PHOTO_PATH = rPAIR_BFE_PHOTO_PATH;
	}

	@JsonProperty(value="RPAIR_AFT_PHOTO_PATH")
	public java.lang.String[] getRPAIR_AFT_PHOTO_PATH() {
		return RPAIR_AFT_PHOTO_PATH;
	}

	public void setRPAIR_AFT_PHOTO_PATH(java.lang.String[] rPAIR_AFT_PHOTO_PATH) {
		RPAIR_AFT_PHOTO_PATH = rPAIR_AFT_PHOTO_PATH;
	}

    @JsonProperty(value="DAYCNT")
    public java.lang.String getDAYCNT() {
        return DAYCNT;
    }
    public void setDAYCNT(java.lang.String dAYCNT) {
        DAYCNT = dAYCNT;
    }

    @JsonProperty(value="PROCESS_DT")
    public java.lang.String getPROCESS_DT() {
        return PROCESS_DT;
    }
    public void setPROCESS_DT(java.lang.String pROCESS_DT) {
        PROCESS_DT = pROCESS_DT;
    }

    @JsonProperty(value="STTEMNT_DT")
    public java.lang.String getSTTEMNT_DT() {
        return STTEMNT_DT;
    }

    public void setSTTEMNT_DT(java.lang.String sTTEMNT_DT) {
        STTEMNT_DT = sTTEMNT_DT;
    }


}