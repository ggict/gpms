package kr.go.gg.gpms.pothole.sttst.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 통계 VO
 * @Classname : SttstVO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SttstVO extends BaseVO {

	public SttstVO() {
		super();
	}

	/*
     * TN_POTHOLE_STTEMNT.CORTN_X
     * 포트홀_신고_내역.보정_X,
     * */
    @XmlElement
    private java.lang.String CORTN_X;

    /*
     * TN_POTHOLE_STTEMNT.CORTN_Y
     * 포트홀_신고_내역.보정_Y,
     * */
    @XmlElement
    private java.lang.String CORTN_Y;

	/*
	 * TN_MNTRNG_MBR.DEPT_CODE
	 * 포트홀_모니터링_단원.부서_코드,
	 * */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/*
	 * TN_MNTRNG_MBR.DEPT_CODE
	 * 포트홀_모니터링_단원.관할기관기준 처리현황.접수경로,
	 * */
	@XmlElement
	private java.lang.String PTHMODE;

	/*
	 * TN_MNTRNG_MBR.DEPT_CODE
	 * 포트홀_모니터링_단원.신고자기준 신고현황.접수경로,
	 * */
	@XmlElement
	private java.lang.String MTDT_NM;

	/* TN_MNTRNG_MBR.LOWEST_DEPT_NM
     * 포트홀_모니터링_단원.최하위_부서_명,
     * */
    @XmlElement
    private java.lang.String LOWEST_DEPT_NM;

	/*
     * TN_MNTRNG_MBR.BSNM_NM
     * 포트홀_모니터링_단원.사업자_명,
     * */
    @XmlElement
    private java.lang.String BSNM_NM;

    /*
     * TN_MNTRNG_MBR.VHCLE_NO
     * 포트홀_모니터링_단원.차량_번호,
     * */
    @XmlElement
    private java.lang.String VHCLE_NO;

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT
     * 포트홀_신고_내역.신고_일시
     * */
    @XmlElement
    private java.lang.String STTEMNT_DT;

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT_START
     * 포트홀_신고_내역.신고_일시_시작
     * */
    @XmlElement
    private java.lang.String STTEMNT_DT_START;

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT_END
     * 포트홀_신고_내역.신고_일시_종료
     * */
    @XmlElement
    private java.lang.String STTEMNT_DT_END;

    /*
     * TN_POTHOLE_STTEMNT.PERCENT
     * 포트홀_신고_내역.백분율
     * */
    @XmlElement
    private java.lang.Double PERCENT;

    /*
     * TN_POTHOLE_STTEMNT.sidxexcel
     * 포트홀_신고_내역.엑셀정렬기준
     * */
    @XmlElement
    private java.lang.String sidxexcel;

    /*
     * TN_POTHOLE_STTEMNT.sordexcel
     * 포트홀_신고_내역.엑셀정렬
     * */
    @XmlElement
    private java.lang.String sordexcel;

    /**
     * TN_POTHOLE_STTEMNT.PRCS_STTUS,
     * 포트홀_신고_내역.처리_상태
     */
    @XmlElement
    private java.lang.String PRCS_STTUS;



    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태1 신고,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS1;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS2
     * 포트홀_모니터링_단원.처리_상태2 접수,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS2;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS3
     * 포트홀_모니터링_단원.처리_상태3 처리완료,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS3;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS4
     * 포트홀_모니터링_단원.처리_상태4 보수예정,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS4;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS5
     * 포트홀_모니터링_단원.처리_상태5 재배정요청,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS5;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS6
     * 포트홀_모니터링_단원.처리_상태6 기타,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS6;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태7 좌표오류,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS7;

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태 신고합계,
     * */
    @XmlElement
    private java.lang.String PRCS_SUM ;


    /**
     * TN_POTHOLE_STTEMNT.PRCS_COUNT,
     * 포트홀_신고_내역.처리_상태_카운트
     */
    @XmlElement
    private java.lang.String PRCS_COUNT;
    @XmlElement
    private java.lang.String PRCS_COUNT1;
    @XmlElement
    private java.lang.String PRCS_COUNT2;
    @XmlElement
    private java.lang.String PRCS_COUNT3;
    @XmlElement
    private java.lang.String PRCS_COUNT4;
    @XmlElement
    private java.lang.String PRCS_COUNT5;
    @XmlElement
    private java.lang.String PRCS_COUNT6;
    @XmlElement
    private java.lang.String PRCS_COUNT7;
    @XmlElement
    private java.lang.String PRCS_COUNT8;
    @XmlElement
    private java.lang.String PRCS_COUNT9;
    @XmlElement
    private java.lang.String PRCS_COUNT10;
    @XmlElement
    private java.lang.String PRCS_COUNT11;
    @XmlElement
    private java.lang.String PRCS_COUNT12;

    /*
     * 데이터 최소 년도 조회
     * */
    @XmlElement
    private java.lang.String MIN_YEAR;


    /*
     * 데이터 최대 년도 조회
     * */
    @XmlElement
    private java.lang.String MAX_YEAR;







    /*
     * TN_POTHOLE_STTEMNT.PRCS_SUM
     * 포트홀_통계.처리_상태 신고합계,
     * */
    @JsonProperty(value="PRCS_SUM")
    public java.lang.String getPRCS_SUM() {
		return PRCS_SUM;
	}
	public void setPRCS_SUM(java.lang.String pRCS_SUM) {
		PRCS_SUM = pRCS_SUM;
	}

	/*
     * TN_POTHOLE_STTEMNT.CORTN_X
     * 포트홀_신고_내역.보정_X,
     * */
    @JsonProperty(value="CORTN_X")
    public java.lang.String getCORTN_X() {
        return CORTN_X;
    }

    public void setCORTN_X(java.lang.String cORTN_X) {
        CORTN_X = cORTN_X;
    }

    /*
     * TN_POTHOLE_STTEMNT.CORTN_Y
     * 포트홀_신고_내역.보정_Y,
     * */
    @JsonProperty(value="CORTN_Y")
    public java.lang.String getCORTN_Y() {
        return CORTN_Y;
    }

    public void setCORTN_Y(java.lang.String cORTN_Y) {
        CORTN_Y = cORTN_Y;
    }

	/* 포트홀_신고_내역.처리_상태_카운트 VO */
    /*
     * TN_POTHOLE_STTEMNT.PRCS_STTUS,
     * 포트홀_신고_내역.처리_상태_카운트
     * */
    @JsonProperty(value="PRCS_COUNT")
    public java.lang.String getPRCS_COUNT() {
		return PRCS_COUNT;
	}
	public void setPRCS_COUNT(java.lang.String pRCS_COUNT) {
		PRCS_COUNT = pRCS_COUNT;
	}
	@JsonProperty(value="PRCS_COUNT1")
	public java.lang.String getPRCS_COUNT1() {
		return PRCS_COUNT1;
	}
	public void setPRCS_COUNT1(java.lang.String pRCS_COUNT1) {
		PRCS_COUNT1 = pRCS_COUNT1;
	}
	@JsonProperty(value="PRCS_COUNT2")
	public java.lang.String getPRCS_COUNT2() {
		return PRCS_COUNT2;
	}
	public void setPRCS_COUNT2(java.lang.String pRCS_COUNT2) {
		PRCS_COUNT2 = pRCS_COUNT2;
	}
	@JsonProperty(value="PRCS_COUNT3")
	public java.lang.String getPRCS_COUNT3() {
		return PRCS_COUNT3;
	}
	public void setPRCS_COUNT3(java.lang.String pRCS_COUNT3) {
		PRCS_COUNT3 = pRCS_COUNT3;
	}
	@JsonProperty(value="PRCS_COUNT4")
	public java.lang.String getPRCS_COUNT4() {
		return PRCS_COUNT4;
	}
	public void setPRCS_COUNT4(java.lang.String pRCS_COUNT4) {
		PRCS_COUNT4 = pRCS_COUNT4;
	}
	@JsonProperty(value="PRCS_COUNT5")
	public java.lang.String getPRCS_COUNT5() {
		return PRCS_COUNT5;
	}
	public void setPRCS_COUNT5(java.lang.String pRCS_COUNT5) {
		PRCS_COUNT5 = pRCS_COUNT5;
	}
	@JsonProperty(value="PRCS_COUNT6")
	public java.lang.String getPRCS_COUNT6() {
		return PRCS_COUNT6;
	}
	public void setPRCS_COUNT6(java.lang.String pRCS_COUNT6) {
		PRCS_COUNT6 = pRCS_COUNT6;
	}
	@JsonProperty(value="PRCS_COUNT7")
	public java.lang.String getPRCS_COUNT7() {
		return PRCS_COUNT7;
	}
	public void setPRCS_COUNT7(java.lang.String pRCS_COUNT7) {
		PRCS_COUNT7 = pRCS_COUNT7;
	}
	@JsonProperty(value="PRCS_COUNT8")
	public java.lang.String getPRCS_COUNT8() {
		return PRCS_COUNT8;
	}
	public void setPRCS_COUNT8(java.lang.String pRCS_COUNT8) {
		PRCS_COUNT8 = pRCS_COUNT8;
	}
	@JsonProperty(value="PRCS_COUNT9")
	public java.lang.String getPRCS_COUNT9() {
		return PRCS_COUNT9;
	}
	public void setPRCS_COUNT9(java.lang.String pRCS_COUNT9) {
		PRCS_COUNT9 = pRCS_COUNT9;
	}
	@JsonProperty(value="PRCS_COUNT10")
	public java.lang.String getPRCS_COUNT10() {
		return PRCS_COUNT10;
	}
	public void setPRCS_COUNT10(java.lang.String pRCS_COUNT10) {
		PRCS_COUNT10 = pRCS_COUNT10;
	}
	@JsonProperty(value="PRCS_COUNT11")
	public java.lang.String getPRCS_COUNT11() {
		return PRCS_COUNT11;
	}
	public void setPRCS_COUNT11(java.lang.String pRCS_COUNT11) {
		PRCS_COUNT11 = pRCS_COUNT11;
	}
	@JsonProperty(value="PRCS_COUNT12")
	public java.lang.String getPRCS_COUNT12() {
		return PRCS_COUNT12;
	}
	public void setPRCS_COUNT12(java.lang.String pRCS_COUNT12) {
		PRCS_COUNT12 = pRCS_COUNT12;
	}


	/* 포트홀_신고_내역.처리_상태 VO */
    /*
     * TN_POTHOLE_STTEMNT.PRCS_STTUS,
     * 포트홀_신고_내역.처리_상태
     * */
    @JsonProperty(value="PRCS_STTUS")
    public java.lang.String getPRCS_STTUS() {
		return PRCS_STTUS;
	}
	public void setPRCS_STTUS(java.lang.String pRCS_STTUS) {
		PRCS_STTUS = pRCS_STTUS;
	}


    /*
     * TN_MNTRNG_MBR.DEPT_CODE
     * 포트홀_모니터링_단원.부서_코드,
     * */
    @JsonProperty(value="DEPT_CODE")
    public java.lang.String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(java.lang.String dEPT_CODE) {
        DEPT_CODE = dEPT_CODE;
    }

    /*
     * TN_MNTRNG_MBR.DEPT_CODE
     * 포트홀_모니터링_단원.관할기관기준 처리현황.접수경로,
     * */
    @JsonProperty(value="PTHMODE")
    public java.lang.String getPTHMODE() {
        return PTHMODE;
    }

    public void setPTHMODE(java.lang.String pTHMODE) {
    	PTHMODE = pTHMODE;
    }

    /*
     * TN_MNTRNG_MBR.DEPT_CODE
     * 포트홀_모니터링_단원.신고자기준 신고현황.접수경로,
     * */
    @JsonProperty(value="MTDT_NM")
    public java.lang.String getMTDT_NM() {
        return MTDT_NM;
    }

    public void setMTDT_NM(java.lang.String mTDT_NM) {
    	MTDT_NM = mTDT_NM;
    }

    /*
     * TN_MNTRNG_MBR.LOWEST_DEPT_NM
     * 포트홀_모니터링_단원.최하위_부서_명,
     * */
    @JsonProperty(value="LOWEST_DEPT_NM")
    public java.lang.String getLOWEST_DEPT_NM() {
        return LOWEST_DEPT_NM;
    }

    public void setLOWEST_DEPT_NM(java.lang.String lOWEST_DEPT_NM) {
        LOWEST_DEPT_NM = lOWEST_DEPT_NM;
    }

    /*
     * TN_MNTRNG_MBR.BSNM_NM
     * 포트홀_모니터링_단원.사업자_명,
     * */
    @JsonProperty(value="BSNM_NM")
    public java.lang.String getBSNM_NM() {
        return BSNM_NM;
    }

    public void setBSNM_NM(java.lang.String bSNM_NM) {
        BSNM_NM = bSNM_NM;
    }

    /*
     * TN_MNTRNG_MBR.VHCLE_NO
     * 포트홀_모니터링_단원.차량_번호,
     * */
    @JsonProperty(value="VHCLE_NO")
    public java.lang.String getVHCLE_NO() {
        return VHCLE_NO;
    }

    public void setVHCLE_NO(java.lang.String vHCLE_NO) {
        VHCLE_NO = vHCLE_NO;
    }

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT
     * 포트홀_신고_내역.신고_일시
     * */
    @JsonProperty(value="STTEMNT_DT")
    public java.lang.String getSTTEMNT_DT() {
        return STTEMNT_DT;
    }

    public void setSTTEMNT_DT(java.lang.String sTTEMNT_DT) {
        STTEMNT_DT = sTTEMNT_DT;
    }

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT_START
     * 포트홀_신고_내역.신고_일시_시작
     * */
    @JsonProperty(value="STTEMNT_DT_START")
    public java.lang.String getSTTEMNT_DT_START() {
        return STTEMNT_DT_START;
    }

    public void setSTTEMNT_DT_START(java.lang.String sTTEMNT_DT_START) {
        STTEMNT_DT_START = sTTEMNT_DT_START;
    }

    /*
     * TN_POTHOLE_STTEMNT.STTEMNT_DT_END
     * 포트홀_신고_내역.신고_일시_종료
     * */
    @JsonProperty(value="STTEMNT_DT_END")
    public java.lang.String getSTTEMNT_DT_END() {
        return STTEMNT_DT_END;
    }

    public void setSTTEMNT_DT_END(java.lang.String sTTEMNT_DT_END) {
        STTEMNT_DT_END = sTTEMNT_DT_END;
    }

    /*
     * TN_POTHOLE_STTEMNT.PERCENT
     * 포트홀_신고_내역.백분율
     * */
    @JsonProperty(value="PERCENT")
    public java.lang.Double getPERCENT() {
        return PERCENT;
    }

    public void setPERCENT(java.lang.Double pERCENT) {
        PERCENT = pERCENT;
    }

    /*
     * TN_POTHOLE_STTEMNT.sidxexcel
     * 포트홀_신고_내역.엑셀정렬기준
     * */
    @JsonProperty(value="sidxexcel")
    public java.lang.String getSidxexcel() {
        return sidxexcel;
    }

    public void setSidxexcel(java.lang.String sidxExcel) {
        sidxexcel = sidxExcel;
    }

    /*
     * TN_POTHOLE_STTEMNT.sordexcel
     * 포트홀_신고_내역.엑셀정렬
     * */
    @JsonProperty(value="sordexcel")
    public java.lang.String getSordexcel() {
        return sordexcel;
    }

    public void setSordexcel(java.lang.String sordExcel) {
        sordexcel = sordExcel;
    }

    /*
     * 데이터 최소 년도 조회
     * */
    @JsonProperty(value="MIN_YEAR")
    public java.lang.String getMIN_YEAR() {
        return MIN_YEAR;
    }

    public void setMIN_YEAR(java.lang.String mIN_YEAR) {
        MIN_YEAR = mIN_YEAR;
    }

    /*
     * 데이터 최대 년도 조회
     * */
    @JsonProperty(value="MAX_YEAR")
    public java.lang.String getMAX_YEAR() {
        return MAX_YEAR;
    }

    public void setMAX_YEAR(java.lang.String mAX_YEAR) {
        MAX_YEAR = mAX_YEAR;
    }




    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태1 신고,
     * */
    @JsonProperty(value="PRCS_STTUS1")
    public java.lang.String getPRCS_STTUS1() {
        return PRCS_STTUS1;
    }

    public void setPRCS_STTUS1(java.lang.String pRCS_STTUS1) {
        PRCS_STTUS1 = pRCS_STTUS1;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS2
     * 포트홀_모니터링_단원.처리_상태2 접수,
     * */
    @JsonProperty(value="PRCS_STTUS2")
    public java.lang.String getPRCS_STTUS2() {
        return PRCS_STTUS2;
    }

    public void setPRCS_STTUS2(java.lang.String pRCS_STTUS2) {
        PRCS_STTUS2 = pRCS_STTUS2;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS3
     * 포트홀_모니터링_단원.처리_상태3 처리완료,
     * */
    @JsonProperty(value="PRCS_STTUS3")
    public java.lang.String getPRCS_STTUS3() {
        return PRCS_STTUS3;
    }

    public void setPRCS_STTUS3(java.lang.String pRCS_STTUS3) {
        PRCS_STTUS3 = pRCS_STTUS3;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS4
     * 포트홀_모니터링_단원.처리_상태4 보수예정,
     * */
    @JsonProperty(value="PRCS_STTUS4")
    public java.lang.String getPRCS_STTUS4() {
        return PRCS_STTUS4;
    }

    public void setPRCS_STTUS4(java.lang.String pRCS_STTUS4) {
        PRCS_STTUS4 = pRCS_STTUS4;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS5
     * 포트홀_모니터링_단원.처리_상태5 재배정요청,
     * */
    @JsonProperty(value="PRCS_STTUS5")
    public java.lang.String getPRCS_STTUS5() {
        return PRCS_STTUS5;
    }

    public void setPRCS_STTUS5(java.lang.String pRCS_STTUS5) {
        PRCS_STTUS5 = pRCS_STTUS5;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS6
     * 포트홀_모니터링_단원.처리_상태6 기타,
     * */
    @JsonProperty(value="PRCS_STTUS6")
    public java.lang.String getPRCS_STTUS6() {
        return PRCS_STTUS6;
    }

    public void setPRCS_STTUS6(java.lang.String pRCS_STTUS6) {
        PRCS_STTUS6 = pRCS_STTUS6;
    }

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS7
     * 포트홀_모니터링_단원.처리_상태7 좌표오류,
     * */
    @JsonProperty(value="PRCS_STTUS7")
    public java.lang.String getPRCS_STTUS7() {
        return PRCS_STTUS7;
    }

    public void setPRCS_STTUS7(java.lang.String pRCS_STTUS7) {
        PRCS_STTUS7 = pRCS_STTUS7;
    }


}
