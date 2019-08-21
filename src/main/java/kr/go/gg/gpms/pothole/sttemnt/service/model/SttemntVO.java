package kr.go.gg.gpms.pothole.sttemnt.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import org.json.simple.JSONArray;

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
public class SttemntVO extends BaseVO {

    public SttemntVO() {
        super();
    }

    /**
     * sflag 공간검색을 통한 sttemntlist 접속 flag
     */
    @XmlElement
    private java.lang.String sflag;

    /**
     * pthmode 포트홀_신고 접수경로별 조회 구분 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String pthmode;

    /**
     * imode 포트홀_신고 편집모드 구분 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String imode;

    /**
     * GG_ID 포트홀_신고 (시군구) 위치 정보.ID (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String GG_ID;

    /**
     * RCPT_ROUTE 포트홀_신고 접수경로 구분 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String RCPT_ROUTE;

    /**
     * SMS_TIME_START 포트홀 신고정보 SMS 전송 시작 시간 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String SMS_TIME_START;

    /**
     * SMS_TIME_END 포트홀 신고정보 SMS 전송 마감 시간 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String SMS_TIME_END;

    /**
     * SMS_TIME_CRON 포트홀 신고정보 SMS 예약전송 시간 (2019년 고도화 사업)
     */
    @XmlElement
    private java.lang.String SMS_TIME_CRON;

	/**
     * TN_POTHOLE_STTEMNT.PTH_RG_NO,
     * 포트홀_신고_내역.포트홀_등록_번호
     */
    @XmlElement
    private java.lang.String PTH_RG_NO;

    /**
     * TN_POTHOLE_STTEMNT.PTH_PHOTO,
     * 포트홀_신고_내역.포트홀_등록_번호
     */
    @XmlElement
    private java.lang.String PTH_PHOTO;

    /**
     * TN_POTHOLE_STTEMNT.PTH_RG_NO_LAYER,
     * 포트홀_신고_내역.포트홀_등록_번호_레이어_배열
     */

    @XmlElement
    private JSONArray PTH_RG_NO_LAYER;

    /**
     * TN_POTHOLE_STTEMNT.CRDNT_X,
     * 포트홀_신고_내역.좌표_X
     */
    @XmlElement
    private java.lang.String CRDNT_X;

    /**
     * TN_POTHOLE_STTEMNT.CRDNT_Y,
     * 포트홀_신고_내역.좌표_Y
     */
    @XmlElement
    private java.lang.String CRDNT_Y;

    /**
     * TN_POTHOLE_STTEMNT.CORTN_X,
     * 포트홀_신고_내역.보정_X
     */
    @XmlElement
    private java.lang.String CORTN_X;

    /**
     * TN_POTHOLE_STTEMNT.CORTN_Y,
     * 포트홀_신고_내역.보정_Y
     */
    @XmlElement
    private java.lang.String CORTN_Y;

    /**
     * TN_POTHOLE_STTEMNT.TM_X,
     * 포트홀_신고_내역.TM_X
     */
    @XmlElement
    private java.lang.String TM_X;

    /**
     * TN_POTHOLE_STTEMNT.TM_Y,
     * 포트홀_신고_내역.TM_Y
     */
    @XmlElement
    private java.lang.String TM_Y;

    /**
     * TN_POTHOLE_STTEMNT.HEADG,
     * 포트홀_신고_내역.헤딩
     */
    @XmlElement
    private java.lang.String HEADG;

    /**
     * TN_POTHOLE_STTEMNT.APLCNT_TELNO,
     * 포트홀_신고_내역.신고자_전화번호
     */
    @XmlElement
    private java.lang.String APLCNT_TELNO;

    /**
     * TN_POTHOLE_STTEMNT.BSNM_NM,
     * 포트홀_신고_내역.사업자_명
     */
    @XmlElement
    private java.lang.String BSNM_NM;

    /**
     * TN_POTHOLE_STTEMNT.VHCLE_NO,
     * 포트홀_신고_내역.차량_번호
     */
    @XmlElement
    private java.lang.String VHCLE_NO;

    /**
     * TN_POTHOLE_STTEMNT.RCEPT_CN,
     * 포트홀_신고_내역.접수_내용
     */
    @XmlElement
    private java.lang.String RCEPT_CN;

    /**
     * TN_POTHOLE_STTEMNT.VHCLE_TYPE,
     * 포트홀_신고_내역.차량_유형
     */
    @XmlElement
    private java.lang.String VHCLE_TYPE;

    /**
     * TN_POTHOLE_STTEMNT.STTEMNT_DT,
     * 포트홀_신고_내역.신고_일시
     */
    @XmlElement
    private java.lang.String STTEMNT_DT;

    /**
     * TN_POTHOLE_STTEMNT.RN_ADRES,
     * 포트홀_신고_내역.도로명_주소
     */
    @XmlElement
    private java.lang.String RN_ADRES;

    /**
     * TN_POTHOLE_STTEMNT.LNM_ADRES,
     * 포트홀_신고_내역.지번_주소
     */
    @XmlElement
    private java.lang.String LNM_ADRES;

    /**
     * TN_POTHOLE_STTEMNT.RCEPT_DT,
     * 포트홀_신고_내역.접수_일시
     */
    @XmlElement
    private java.lang.String RCEPT_DT;

    /**
     * TN_POTHOLE_STTEMNT.RPAIR_DT,
     * 포트홀_신고_내역.보수_일시
     */
    @XmlElement
    private java.lang.String RPAIR_DT;

    /**
     * TN_POTHOLE_STTEMNT.DEPT_CODE,
     * 포트홀_신고_내역.부서_코드
     */
    @XmlElement
    private java.lang.String DEPT_CODE;

    /**
     * TN_POTHOLE_STTEMNT.EDEPT_CODE,
     * 포트홀_신고_내역.기타_부서_코드
     */
    @XmlElement
    private java.lang.String EDEPT_CODE;

    /**
     * TN_POTHOLE_STTEMNT.PRCS_TYPE,
     * 포트홀_신고_내역.처리_유형
     */
    @XmlElement
    private java.lang.String PRCS_TYPE;

    /**
     * TN_POTHOLE_STTEMNT.PRCS_STTUS,
     * 포트홀_신고_내역.처리_상태
     */
    @XmlElement
    private java.lang.String PRCS_STTUS;

    /**
     * TN_POTHOLE_STTEMNT.CORTN_AT,
     * 포트홀_신고_내역.보정_여부
     */
    @XmlElement
    private java.lang.String CORTN_AT;

    /**
     * TN_POTHOLE_STTEMNT.DPLCT_STTEMNT_AT,
     * 포트홀_신고_내역.중복_신고_여부
     */
    @XmlElement
    private java.lang.String DPLCT_STTEMNT_AT;

    /**
     * TN_POTHOLE_STTEMNT.DSM_RP_NO,
     * 포트홀_신고_내역.마스터_등록_번호
     */
    @XmlElement
    private java.lang.String DSM_RP_NO;

    /**
     * TN_POTHOLE_STTEMNT.SMS_SNDNG_AT,
     * 포트홀_신고_내역.SMS_발송_여부
     */
    @XmlElement
    private java.lang.String SMS_SNDNG_AT;

    /**
     * TN_POTHOLE_STTEMNT.CPT_MNG_NO,
     * 포트홀_신고_내역.노선_관리_번호
     */
    @XmlElement
    private java.lang.String CPT_MNG_NO;

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
     * TN_POTHOLE_STTEMNT.USE_AT,
     * 포트홀_신고_내역.사용_여부
     */
    @XmlElement
    private java.lang.String USE_AT;

    /**
     * TN_POTHOLE_STTEMNT.DELETE_AT,
     * 포트홀_신고_내역.삭제_여부
     */
    @XmlElement
    private java.lang.String DELETE_AT;

    /**
     * TN_POTHOLE_STTEMNT.CRTR_NO,
     * 포트홀_신고_내역.생성자_번호
     */
    @XmlElement
    private java.lang.String CRTR_NO;

    /**
     * TN_POTHOLE_STTEMNT.CREAT_DE,
     * 포트홀_신고_내역.생성_일자
     */
    @XmlElement
    private java.lang.String CREAT_DE;

    /**
     * TN_POTHOLE_STTEMNT.UPDUSR_NO,
     * 포트홀_신고_내역.수정자_번호
     */
    @XmlElement
    private java.lang.String UPDUSR_NO;

    /**
     * TN_POTHOLE_STTEMNT.UPDT_DE,
     * 포트홀_신고_내역.수정_일자
     */
    @XmlElement
    private java.lang.String UPDT_DE;

    /**
     * TN_POTHOLE_STTEMNT.START_STTEMNT_DT,
     * 포트홀_신고_내역.신고_일자_시작
     */
    @XmlElement
    private java.lang.String STTEMNT_DT_START;

    /**
     * TN_POTHOLE_STTEMNT.END_STTEMNT_DT,
     * 포트홀_신고_내역.신고_일자_종료
     */
    @XmlElement
    private java.lang.String STTEMNT_DT_END;

    /**
     * TN_POTHOLE_STTEMNT.START_RPAIR_DT,
     * 포트홀_신고_내역.보수_일자_시작
     */
    @XmlElement
    private java.lang.String RPAIR_DT_START;

    /**
     * TN_POTHOLE_STTEMNT.END_RPAIR_DT,
     * 포트홀_신고_내역.보수_일자_종료
     */
    @XmlElement
    private java.lang.String RPAIR_DT_END;

    /**
     * TN_POTHOLE_RPAIR.DMG_TYPE,
     * 포트홀_보수_내역.파손유형
     */
    @XmlElement
    private java.lang.String DMG_TYPE;

    /**
     * TN_POTHOLE_RPAIR.RM,
     * 포트홀_보수_내역.비고
     */
    @XmlElement
    private java.lang.String RM;

    /**
     * TC_DEPT.LOWEST_DEPT_NM,
     * 부서.최하위_부서_명
     */
    @XmlElement
    private java.lang.String LOWEST_DEPT_NM;

    /**
     * TC_DEPT.LOWEST_DEPT_NM,
     * 부서.최하위_부서_명
     */
    @XmlElement
    private java.lang.String ROUTE_NM;

    /**
     * TN_POTHOLE_STTEMNT.PRCS_TYPE_NM,
     * 포트홀_신고_내역.처리_유형_이름
     */
    @XmlElement
    private java.lang.String PRCS_TYPE_NM;

    /**
     * TN_POTHOLE_STTEMNT.PRCS_STTUS_NM,
     * 포트홀_신고_내역.처리_상태_이름
     */
    @XmlElement
    private java.lang.String PRCS_STTUS_NM;

    /**
     * TN_POTHOLE_STTEMNT.DMG_TYPE_NM,
     * 포트홀_신고_내역.파손_유형_이름
     */
    @XmlElement
    private java.lang.String DMG_TYPE_NM;


    /**
     * TN_CODE_.CODE_NM_PRCS
     * 코드네임_처리상태
     */
    @XmlElement
    private java.lang.String CODE_NM_PRCS;

    /**
     * TN_CODE_.CODE_NM_DMGT
     * 코드네임_파손유형
     */
    @XmlElement
    private java.lang.String CODE_NM_DMGT;

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


    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태 합,
     * */
    @XmlElement
    private java.lang.String PRCS_SUM;


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
     * TN_MNTRNG_MBR.PRCS_STTUS8
     * 포트홀_모니터링_단원.처리_상태8 취하완료,
     * */
    @XmlElement
    private java.lang.String PRCS_STTUS8;

    /*
     * TN_POTHOLE_STTEMNT.PRV_RD_OPRT
     * 포트홀_신고_내역.민자도로사업자
     * */
    @XmlElement
    private java.lang.String PRV_RD_OPRT;

    // 프로시저 파라미터 배열
    @XmlElement
    private java.lang.String [] paramArr;

    @XmlElement
    private List<String> paramList;

    /*
     * TN_POTHOLE_STTEMNT.LOC_VIEW,
     * 포트홀_신고관리.위치도
     * */
    @XmlElement
    private java.lang.String LOC_VIEW;


    /*
     * TN_POTHOLE_STTEMNT.LCDO,
     * 포트홀_신고관리.위치도등록번호
     * */
    @XmlElement
    private java.lang.String LCDO;

    // 검색조건
    @XmlElement
    private java.lang.String SCH_PRCS_STTUS;

    @XmlElement
    private java.lang.String SCH_DMG_TYPE;

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

    /*
     * TN_POTHOLE_RPAIR.RPRDTLS_MNG_NO,
     * 포트홀_보수_내역.보수_관리_번호
     * */
    @XmlElement
    private java.lang.String RPRDTLS_MNG_NO;

    /*========================================*/



    /*
     * sflag 공간검색을 통한 sttemntlist 접속 flag
     * */
    @JsonProperty(value="sflag")
    public java.lang.String getSflag() {
		return sflag;
	}
	public void setSflag(java.lang.String sflag) {
		this.sflag = sflag;
	}

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

	/*
     * imode 포트홀_신고 편집모드 구분 (2019년 고도화 사업)
     * */
    @JsonProperty(value="imode")
    public java.lang.String getImode() {
		return imode;
	}
	public void setImode(java.lang.String imode) {
		this.imode = imode;
	}

	/*
     * GG_ID 포트홀_신고 (시군구) 위치 정보.ID (2019년 고도화 사업)
     * */
    @JsonProperty(value="GG_ID")
    public java.lang.String getGG_ID() {
		return GG_ID;
	}
	public void setGG_ID(java.lang.String gG_ID) {
		this.GG_ID = gG_ID;
	}

	/*
     * RCPT_ROUTE 포트홀_신고 접수경로 (2019년 고도화 사업)
     * */
    @JsonProperty(value="RCPT_ROUTE")
    public java.lang.String getRCPT_ROUTE() {
		return RCPT_ROUTE;
	}
	public void setRCPT_ROUTE(java.lang.String rCPT_ROUTE) {
		this.RCPT_ROUTE = rCPT_ROUTE;
	}

	/**
     * SMS_TIME_START 포트홀 신고정보 SMS 전송 시작 시간 (2019년 고도화 사업)
     */
    @JsonProperty(value="SMS_TIME_START")
    public java.lang.String getSMS_TIME_START() {
		return SMS_TIME_START;
	}
	public void setSMS_TIME_START(java.lang.String sMS_TIME_START) {
		this.SMS_TIME_START = sMS_TIME_START;
	}

    /**
     * SMS_TIME_END 포트홀 신고정보 SMS 전송 마감 시간 (2019년 고도화 사업)
     */
	@JsonProperty(value="SMS_TIME_END")
    public java.lang.String getSMS_TIME_END() {
		return SMS_TIME_END;
	}
	public void setSMS_TIME_END(java.lang.String sMS_TIME_END) {
		this.SMS_TIME_END = sMS_TIME_END;
	}

	/**
     * SMS_TIME_START 포트홀 신고정보 SMS 예약전송 시간 (2019년 고도화 사업)
     */
    @JsonProperty(value="SMS_TIME_CRON")
    public java.lang.String getSMS_TIME_CRON() {
		return SMS_TIME_CRON;
	}
	public void setSMS_TIME_CRON(java.lang.String sMS_TIME_CRON) {
		this.SMS_TIME_CRON = sMS_TIME_CRON;
	}

    /*
     * TN_POTHOLE_STTEMNT.LOC_VIEW,
     * 포트홀_신고관리.위치도
     * */
    @JsonProperty(value="LCDO")
	public java.lang.String getLCDO() {
		return LCDO;
	}
	public void setLCDO(java.lang.String lCDO) {
		LCDO = lCDO;
	}
	/*
     * TN_POTHOLE_STTEMNT.LOC_VIEW,
     * 포트홀_신고관리.위치도
     * */
    @JsonProperty(value="lOC_VIEW")
	public java.lang.String getLOC_VIEW() {
		return LOC_VIEW;
	}
	public void setLOC_VIEW(java.lang.String lOC_VIEW) {
		LOC_VIEW = lOC_VIEW;
	}


    /*
     * TN_POTHOLE_STTEMNT_.PTH_RG_NO_LAYER
     * 포트홀_신고관리.신고번호_레이어_배열,
     * */
    @JsonProperty(value="PTH_RG_NO_LAYER")
    public JSONArray getPTH_RG_NO_LAYER() {
		return PTH_RG_NO_LAYER;
	}
	public void setPTH_RG_NO_LAYER(JSONArray pTH_RG_NO_LAYER) {
		PTH_RG_NO_LAYER = pTH_RG_NO_LAYER;
	}

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS1
     * 포트홀_모니터링_단원.처리_상태 합계,
     * */
    @JsonProperty(value="PRCS_SUM")
    public java.lang.String getPRCS_SUM() {
        return PRCS_SUM;
    }
	public void setPRCS_SUM(java.lang.String pRCS_SUM) {
        PRCS_SUM = pRCS_SUM;
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

    /*
     * TN_MNTRNG_MBR.PRCS_STTUS8
     * 포트홀_모니터링_단원.처리_상태8 좌표오류,
     * */
    @JsonProperty(value="PRCS_STTUS8")
    public java.lang.String getPRCS_STTUS8() {
        return PRCS_STTUS8;
    }

    public void setPRCS_STTUS8(java.lang.String pRCS_STTUS8) {
        PRCS_STTUS8 = pRCS_STTUS8;
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

	@JsonProperty(value="CODE_NM_PRCS")
    public java.lang.String getCODE_NM_PRCS() {
		return CODE_NM_PRCS;
	}
	public void setCODE_NM_PRCS(java.lang.String cODE_NM_PRCS) {
		CODE_NM_PRCS = cODE_NM_PRCS;
	}
	@JsonProperty(value="CODE_NM_DMGT")
	public java.lang.String getCODE_NM_DMGT() {
		return CODE_NM_DMGT;
	}
	public void setCODE_NM_DMGT(java.lang.String cODE_NM_DMGT) {
		CODE_NM_DMGT = cODE_NM_DMGT;
	}

	@JsonProperty(value="PTH_RG_NO")
    public java.lang.String getPTH_RG_NO() {
        return PTH_RG_NO;
    }
    public void setPTH_RG_NO(java.lang.String pTH_RG_NO) {
        PTH_RG_NO = pTH_RG_NO;
    }

    @JsonProperty(value="PTH_PHOTO")
    public java.lang.String getPTH_PHOTO() {
        return PTH_PHOTO;
    }
    public void setPTH_PHOTO(java.lang.String pTH_PHOTO) {
    	PTH_PHOTO = pTH_PHOTO;
    }

    @JsonProperty(value="CRDNT_X")
    public java.lang.String getCRDNT_X() {
        return CRDNT_X;
    }
    public void setCRDNT_X(java.lang.String cRDNT_X) {
        CRDNT_X = cRDNT_X;
    }

    @JsonProperty(value="CRDNT_Y")
    public java.lang.String getCRDNT_Y() {
        return CRDNT_Y;
    }
    public void setCRDNT_Y(java.lang.String cRDNT_Y) {
        CRDNT_Y = cRDNT_Y;
    }

    @JsonProperty(value="CORTN_X")
    public java.lang.String getCORTN_X() {
        return CORTN_X;
    }
    public void setCORTN_X(java.lang.String cORTN_X) {
        CORTN_X = cORTN_X;
    }

    @JsonProperty(value="CORTN_Y")
    public java.lang.String getCORTN_Y() {
        return CORTN_Y;
    }
    public void setCORTN_Y(java.lang.String cORTN_Y) {
        CORTN_Y = cORTN_Y;
    }

    @JsonProperty(value="TM_X")
    public java.lang.String getTM_X() {
        return TM_X;
    }
    public void setTM_X(java.lang.String tM_X) {
        TM_X = tM_X;
    }

    @JsonProperty(value="TM_Y")
    public java.lang.String getTM_Y() {
        return TM_Y;
    }

    public void setTM_Y(java.lang.String tM_Y) {
        TM_Y = tM_Y;
    }

    @JsonProperty(value="HEADG")
    public java.lang.String getHEADG() {
        return HEADG;
    }
    public void setHEADG(java.lang.String hEADG) {
        HEADG = hEADG;
    }

    @JsonProperty(value="APLCNT_TELNO")
    public java.lang.String getAPLCNT_TELNO() {
        return APLCNT_TELNO;
    }
    public void setAPLCNT_TELNO(java.lang.String aPLCNT_TELNO) {
        APLCNT_TELNO = aPLCNT_TELNO;
    }

    @JsonProperty(value="BSNM_NM")
    public java.lang.String getBSNM_NM() {
        return BSNM_NM;
    }
    public void setBSNM_NM(java.lang.String bSNM_NM) {
        BSNM_NM = bSNM_NM;
    }

    @JsonProperty(value="VHCLE_NO")
    public java.lang.String getVHCLE_NO() {
        return VHCLE_NO;
    }
    public void setVHCLE_NO(java.lang.String vHCLE_NO) {
        VHCLE_NO = vHCLE_NO;
    }

    @JsonProperty(value="RCEPT_CN")
    public java.lang.String getRCEPT_CN() {
        return RCEPT_CN;
    }
    public void setRCEPT_CN(java.lang.String rCEPT_CN) {
        RCEPT_CN = rCEPT_CN;
    }

    @JsonProperty(value="VHCLE_TYPE")
    public java.lang.String getVHCLE_TYPE() {
        return VHCLE_TYPE;
    }
    public void setVHCLE_TYPE(java.lang.String vHCLE_TYPE) {
        VHCLE_TYPE = vHCLE_TYPE;
    }

    @JsonProperty(value="STTEMNT_DT")
    public java.lang.String getSTTEMNT_DT() {
        return STTEMNT_DT;
    }
    public void setSTTEMNT_DT(java.lang.String sTTEMNT_DT) {
        STTEMNT_DT = sTTEMNT_DT;
    }

    @JsonProperty(value="RN_ADRES")
    public java.lang.String getRN_ADRES() {
        return RN_ADRES;
    }
    public void setRN_ADRES(java.lang.String rN_ADRES) {
        RN_ADRES = rN_ADRES;
    }

    @JsonProperty(value="LNM_ADRES")
    public java.lang.String getLNM_ADRES() {
        return LNM_ADRES;
    }
    public void setLNM_ADRES(java.lang.String lNM_ADRES) {
        LNM_ADRES = lNM_ADRES;
    }

    @JsonProperty(value="RCEPT_DT")
    public java.lang.String getRCEPT_DT() {
        return RCEPT_DT;
    }
    public void setRCEPT_DT(java.lang.String rCEPT_DT) {
        RCEPT_DT = rCEPT_DT;
    }

    @JsonProperty(value="RPAIR_DT")
    public java.lang.String getRPAIR_DT() {
        return RPAIR_DT;
    }
    public void setRPAIR_DT(java.lang.String rPAIR_DT) {
        RPAIR_DT = rPAIR_DT;
    }

    @JsonProperty(value="DEPT_CODE")
    public java.lang.String getDEPT_CODE() {
        return DEPT_CODE;
    }
    public void setDEPT_CODE(java.lang.String dEPT_CODE) {
        DEPT_CODE = dEPT_CODE;
    }

    @JsonProperty(value="EDEPT_CODE")
    public java.lang.String getEDEPT_CODE() {
        return EDEPT_CODE;
    }
    public void setEDEPT_CODE(java.lang.String eDEPT_CODE) {
        EDEPT_CODE = eDEPT_CODE;
    }

    @JsonProperty(value="PRCS_TYPE")
    public java.lang.String getPRCS_TYPE() {
        return PRCS_TYPE;
    }
    public void setPRCS_TYPE(java.lang.String pRCS_TYPE) {
        PRCS_TYPE = pRCS_TYPE;
    }

    @JsonProperty(value="PRCS_STTUS")
    public java.lang.String getPRCS_STTUS() {
        return PRCS_STTUS;
    }
    public void setPRCS_STTUS(java.lang.String pRCS_STTUS) {
        PRCS_STTUS = pRCS_STTUS;
    }

    @JsonProperty(value="CORTN_AT")
    public java.lang.String getCORTN_AT() {
        return CORTN_AT;
    }
    public void setCORTN_AT(java.lang.String cORTN_AT) {
        CORTN_AT = cORTN_AT;
    }

    @JsonProperty(value="DPLCT_STTEMNT_AT")
    public java.lang.String getDPLCT_STTEMNT_AT() {
        return DPLCT_STTEMNT_AT;
    }
    public void setDPLCT_STTEMNT_AT(java.lang.String dPLCT_STTEMNT_AT) {
        DPLCT_STTEMNT_AT = dPLCT_STTEMNT_AT;
    }

    @JsonProperty(value="DSM_RP_NO")
    public java.lang.String getDSM_RP_NO() {
        return DSM_RP_NO;
    }
    public void setDSM_RP_NO(java.lang.String DSM_RP_NO) {
        this.DSM_RP_NO = DSM_RP_NO;
    }

    @JsonProperty(value="SMS_SNDNG_AT")
    public java.lang.String getSMS_SNDNG_AT() {
        return SMS_SNDNG_AT;
    }
    public void setSMS_SNDNG_AT(java.lang.String sMS_SNDNG_AT) {
        SMS_SNDNG_AT = sMS_SNDNG_AT;
    }

    @JsonProperty(value="CPT_MNG_NO")
    public java.lang.String getCPT_MNG_NO() {
        return CPT_MNG_NO;
    }
    public void setCPT_MNG_NO(java.lang.String CPT_MNG_NO) {
        this.CPT_MNG_NO = CPT_MNG_NO;
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

    @JsonProperty(value="STTEMNT_DT_START")
    public java.lang.String getSTTEMNT_DT_START() {
        return STTEMNT_DT_START;
    }
    public void setSTTEMNT_DT_START(java.lang.String sTTEMNT_DT_START) {
        STTEMNT_DT_START = sTTEMNT_DT_START;
    }

    @JsonProperty(value="STTEMNT_DT_END")
    public java.lang.String getSTTEMNT_DT_END() {
        return STTEMNT_DT_END;
    }
    public void setSTTEMNT_DT_END(java.lang.String sTTEMNT_DT_END) {
        STTEMNT_DT_END = sTTEMNT_DT_END;
    }

    @JsonProperty(value="RPAIR_DT_START")
    public java.lang.String getRPAIR_DT_START() {
        return RPAIR_DT_START;
    }
    public void setRPAIR_DT_START(java.lang.String rPAIR_DT_START) {
        RPAIR_DT_START = rPAIR_DT_START;
    }

    @JsonProperty(value="RPAIR_DT_END")
    public java.lang.String getRPAIR_DT_END() {
        return RPAIR_DT_END;
    }
    public void setRPAIR_DT_END(java.lang.String rPAIR_DT_END) {
        RPAIR_DT_END = rPAIR_DT_END;
    }

    @JsonProperty(value="DMG_TYPE")
    public java.lang.String getDMG_TYPE() {
        return DMG_TYPE;
    }
    public void setDMG_TYPE(java.lang.String dMG_TYPE) {
        DMG_TYPE = dMG_TYPE;
    }

    @JsonProperty(value="RM")
    public java.lang.String getRM() {
        return RM;
    }
    public void setRM(java.lang.String rM) {
        RM = rM;
    }

    @JsonProperty(value="LOWEST_DEPT_NM")
    public java.lang.String getLOWEST_DEPT_NM() {
        return LOWEST_DEPT_NM;
    }
    public void setLOWEST_DEPT_NM(java.lang.String lOWEST_DEPT_NM) {
        LOWEST_DEPT_NM = lOWEST_DEPT_NM;
    }

    @JsonProperty(value="ROUTE_NM")
    public java.lang.String getROUTE_NM() {
        return ROUTE_NM;
    }
    public void setROUTE_NM(java.lang.String rOUTE_NM) {
        ROUTE_NM = rOUTE_NM;
    }

    @JsonProperty(value="PRCS_TYPE_NM")
    public java.lang.String getPRCS_TYPE_NM() {
        return PRCS_TYPE_NM;
    }
    public void setPRCS_TYPE_NM(java.lang.String pRCS_TYPE_NM) {
        PRCS_TYPE_NM = pRCS_TYPE_NM;
    }

    @JsonProperty(value="PRCS_STTUS_NM")
    public java.lang.String getPRCS_STTUS_NM() {
        return PRCS_STTUS_NM;
    }
    public void setPRCS_STTUS_NM(java.lang.String pRCS_STTUS_NM) {
        PRCS_STTUS_NM = pRCS_STTUS_NM;
    }

    @JsonProperty(value="DMG_TYPE_NM")
    public java.lang.String getDMG_TYPE_NM() {
        return DMG_TYPE_NM;
    }
    public void setDMG_TYPE_NM(java.lang.String dMG_TYPE_NM) {
        DMG_TYPE_NM = dMG_TYPE_NM;
    }

    @JsonProperty(value="PRV_RD_OPRT")
    public java.lang.String getPRV_RD_OPRT() {
        return PRV_RD_OPRT;
    }

    public void setPRV_RD_OPRT(java.lang.String pRV_RD_OPRT) {
        PRV_RD_OPRT = pRV_RD_OPRT;
    }

    @JsonProperty(value="paramArr")
    public java.lang.String[] getParamArr() {
        return paramArr;
    }
    public void setParamArr(java.lang.String[] paramArr) {
        this.paramArr = paramArr;
    }

    @JsonProperty(value="paramList")
    public List<String> getParamList() {
        return paramList;
    }
    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    @JsonProperty(value="SCH_PRCS_STTUS")
    public java.lang.String getSCH_PRCS_STTUS() {
        return SCH_PRCS_STTUS;
    }
    public void setSCH_PRCS_STTUS(java.lang.String sCH_PRCS_STTUS) {
        SCH_PRCS_STTUS = sCH_PRCS_STTUS;
    }

    @JsonProperty(value="SCH_DMG_TYPE")
    public java.lang.String getSCH_DMG_TYPE() {
        return SCH_DMG_TYPE;
    }
    public void setSCH_DMG_TYPE(java.lang.String sCH_DMG_TYPE) {
        SCH_DMG_TYPE = sCH_DMG_TYPE;
    }

    @JsonProperty(value="PROCESS_DT")
    public java.lang.String getPROCESS_DT() {
        return PROCESS_DT;
    }
    public void setPROCESS_DT(java.lang.String pROCESS_DT) {
        PROCESS_DT = pROCESS_DT;
    }

    @JsonProperty(value="DAYCNT")
    public java.lang.String getDAYCNT() {
        return DAYCNT;
    }
    public void setDAYCNT(java.lang.String dAYCNT) {
        DAYCNT = dAYCNT;
    }

    @JsonProperty(value="RPRDTLS_MNG_NO")
    public java.lang.String getRPRDTLS_MNG_NO() {
        return RPRDTLS_MNG_NO;
    }
    public void setRPRDTLS_MNG_NO(java.lang.String rPRDTLS_MNG_NO) {
    	RPRDTLS_MNG_NO = rPRDTLS_MNG_NO;
    }

}