
package kr.go.gg.gpms.mummsctnsrvydta.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 최소_구간_조사_자료
 *
 * @Class Name : MummSctnSrvyDtaVO.java
 * @Description : MummSctnSrvyDta VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class MummSctnSrvyDtaVO extends BaseVO {

	public MummSctnSrvyDtaVO() {
		super();
	}
	
	@XmlElement
    private java.lang.String rstFlag;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.DTA_NO, 
	 * 최소_구간_조사_자료.자료_번호
	 */
	@XmlElement
	private java.lang.String DTA_NO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NO, 
	 * 최소_구간_조사_자료.조사_번호
	 */
	@XmlElement
	private java.lang.String SRVY_NO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR, 
	 * 최소_구간_조사_자료.조사_년도
	 */
	@XmlElement
	private java.lang.String SRVY_YEAR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE, 
	 * 최소_구간_조사_자료.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE, 
	 * 최소_구간_조사_자료.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRACK, 
	 * 최소_구간_조사_자료.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.STRTPT, 
	 * 최소_구간_조사_자료.시점
	 */
	@XmlElement
	private java.lang.Integer STRTPT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.ENDPT, 
	 * 최소_구간_조사_자료.종점
	 */
	@XmlElement
	private java.lang.Integer ENDPT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.IRI_VAL, 
	 * 최소_구간_조사_자료.종단평탄성_값
	 */
	@XmlElement
	private java.lang.String IRI_VAL;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.RD_VAL, 
	 * 최소_구간_조사_자료.소성변형_값
	 */
	@XmlElement
	private java.lang.String RD_VAL;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.VRTCAL_CR, 
	 * 최소_구간_조사_자료.종방향_균열
	 */
	@XmlElement
	private java.lang.String VRTCAL_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.HRZNTAL_CR, 
	 * 최소_구간_조사_자료.횡방향_균열
	 */
	@XmlElement
	private java.lang.String HRZNTAL_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CNSTRCT_JOINT_CR, 
	 * 최소_구간_조사_자료.시공_줄눈_균열
	 */
	@XmlElement
	private java.lang.String CNSTRCT_JOINT_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRTS_BAC_CR, 
	 * 최소_구간_조사_자료.거북등_균열
	 */
	@XmlElement
	private java.lang.String TRTS_BAC_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.PTCHG_CR, 
	 * 최소_구간_조사_자료.패칭_균열
	 */
	@XmlElement
	private java.lang.String PTCHG_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CR_VAL, 
	 * 최소_구간_조사_자료.표면손상값
	 */
	@XmlElement
	private java.lang.String CR_VAL;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CR_IDX, 
	 * 최소_구간_조사_자료.표면손상지수
	 */
	@XmlElement
	private java.lang.String CR_IDX;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.RD_IDX, 
	 * 최소_구간_조사_자료.소성변형지수
	 */
	@XmlElement
	private java.lang.String RD_IDX;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.GPCI, 
	 * 최소_구간_조사_자료.포장상태지수
	 */
	@XmlElement
	private java.lang.String GPCI;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.PC_GRAD, 
	 * 최소_구간_조사_자료.포장상태등급
	 */
	@XmlElement
	private java.lang.String PC_GRAD;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CNTL_DFECT, 
	 * 최소_구간_조사_자료.지배_결함
	 */
	@XmlElement
	private java.lang.String CNTL_DFECT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_KND, 
	 * 최소_구간_조사_자료.조사_종류
	 */
	@XmlElement
	private java.lang.String SRVY_KND;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_MT, 
	 * 최소_구간_조사_자료.조사_월
	 */
	@XmlElement
	private java.lang.String SRVY_MT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.MEMO, 
	 * 최소_구간_조사_자료.메모
	 */
	@XmlElement
	private java.lang.String MEMO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_DE, 
	 * 최소_구간_조사_자료.조사_일자
	 */
	@XmlElement
	private java.lang.String SRVY_DE;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LA, 
	 * 최소_구간_조사_자료.TRACE1_LA
	 */
	@XmlElement
	private java.lang.String TRACE1_LA;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LO, 
	 * 최소_구간_조사_자료.TRACE1_LO
	 */
	@XmlElement
	private java.lang.String TRACE1_LO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE2_LA, 
	 * 최소_구간_조사_자료.TRACE2_LA
	 */
	@XmlElement
	private java.lang.String TRACE2_LA;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE2_LO, 
	 * 최소_구간_조사_자료.TRACE2_LO
	 */
	@XmlElement
	private java.lang.String TRACE2_LO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NM, 
	 * 최소_구간_조사_자료.조사_명
	 */
	@XmlElement
	private java.lang.String SRVY_NM;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM, 
	 * 최소_구간_조사_자료.조사자_명
	 */
	@XmlElement
	private java.lang.String EXMNR_NM;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_STRTPT_DC, 
	 * 최소_구간_조사_자료.구간_시점_설명
	 */
	@XmlElement
	private java.lang.String SCTN_STRTPT_DC;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_ENDPT_DC, 
	 * 최소_구간_조사_자료.구간_종점_설명
	 */
	@XmlElement
	private java.lang.String SCTN_ENDPT_DC;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.ROAD_NM, 
	 * 최소_구간_조사_자료.도로_명
	 */
	@XmlElement
	private java.lang.String ROAD_NM;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.FRNT_IMG_FILE_NM, 
	 * 최소_구간_조사_자료.전방_이미지_파일_명
	 */
	@XmlElement
	private java.lang.String FRNT_IMG_FILE_NM;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_1, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_1
	 */
	@XmlElement
	private java.lang.String RDSRFC_IMG_FILE_NM_1;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_2, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_2
	 */
	@XmlElement
	private java.lang.String RDSRFC_IMG_FILE_NM_2;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ, 
	 * 최소_구간_조사_자료.엑셀_자료_SEQ
	 */
	@XmlElement
	private java.lang.String EXCEL_DTA_SEQ;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.FRMULA_NO, 
	 * 최소_구간_조사_자료.수식_번호
	 */
	@XmlElement
	private java.lang.String FRMULA_NO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.POTHOLE_CR, 
	 * 최소_구간_조사_자료.포트홀_개수
	 */
	@XmlElement
	private java.lang.String POTHOLE_CR;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.RI_IDX, 
	 * 최소_구간_조사_자료.평탄성_지수
	 */
	@XmlElement
	private java.lang.String RI_IDX;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_AT, 
	 * 최소_구간_조사_자료.수정_여부
	 */
	@XmlElement
	private java.lang.String UPDT_AT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.DELETE_AT, 
	 * 최소_구간_조사_자료.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.USE_AT, 
	 * 최소_구간_조사_자료.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CRTR_NO, 
	 * 최소_구간_조사_자료.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.CREAT_DT, 
	 * 최소_구간_조사_자료.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.UPDUSR_NO, 
	 * 최소_구간_조사_자료.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_DT, 
	 * 최소_구간_조사_자료.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.AC_IDX, 
	 * 최소_구간_조사_자료.거북등균열_지수
	 */
	@XmlElement
	private java.lang.String AC_IDX;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.BLOCK_CR, 
	 * 최소_구간_조사_자료.블럭_균열
	 */
	@XmlElement
	private java.lang.String BLOCK_CR;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.LC_IDX, 
	 * 최소_구간_조사_자료.종방향균열_지수
	 */
	@XmlElement
	private java.lang.String LC_IDX;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.TC_IDX, 
	 * 최소_구간_조사_자료.횡방향균열_지수
	 */
	@XmlElement
	private java.lang.String TC_IDX;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.PTCHG_IDX, 
	 * 최소_구간_조사_자료.패칭_지수
	 */
	@XmlElement
	private java.lang.String PTCHG_IDX;
	
	/** 
	 * TN_MUMM_SCTN_SRVY_DTA.POTHOLE_IDX, 
	 * 최소_구간_조사_자료.포트홀_지수
	 */
	@XmlElement
	private java.lang.String POTHOLE_IDX;
	
	//노선명
	@XmlElement
	private java.lang.String ROUTE_NM;
	
	//시종점
	@XmlElement
	private java.lang.String STRTPTNL;
	
	//길이
	@XmlElement
	private java.lang.String LENGTH;
	
	
    @XmlElement
    private java.lang.String RPAIR_TA_AT;
    @XmlElement
	private java.lang.String FILE_NM;
    @XmlElement
	private java.lang.String DIRECTION;
	
    @XmlElement
	public String SCH_PERIOD_1;
    @XmlElement
	public String SCH_PERIOD_2;
    @XmlElement
	public String SCH_ROUTCODE;
    @XmlElement
	public String SCH_BSCODE;
    @XmlElement
	public String SCH_ROUTNM;
    @XmlElement
	public String SCH_STRTPT;
    @XmlElement
	public String SCH_ENDPT;
	
	// 노선번호 (숫자)
    @XmlElement
	private java.lang.String ROAD_NO_VAL;
	// 원인 기후
    @XmlElement
	private java.lang.String DMG_CUZ_CLMT;
	// 원인 교통량
    @XmlElement
	private java.lang.String DMG_CUZ_VMTC;
	// 원인 기타
    @XmlElement
	private java.lang.String DMG_CUZ_ETC;
   
    @XmlElement
    private java.lang.String CUZ;
  
    @XmlElement
    private java.lang.String CR;
	// 노선번호 (4자리)
    @XmlElement
	private java.lang.String ROAD_NO;
	// 노선등급
    @XmlElement
	private java.lang.String ROAD_GRAD;
	
	// 셀아이디
    @XmlElement
	private java.lang.String CELL_ID;
    @XmlElement
	private List<String> CELL_ID_ARR;
	
	// Object ID
    @XmlElement
	private java.lang.String OBJECT_ID;
    @XmlElement
	private List<String> OBJECT_ID_ARR;
	
	// BC_IDX
    @XmlElement
	private java.lang.String BC_IDX;
	
	// IRI (RCI)
    @XmlElement
	private java.lang.String IRI;
	
	// 코드명
    @XmlElement
	private java.lang.String CODE_NM;
	
	// 노선명
    @XmlElement
	private java.lang.String ROAD_NAME;
	
	// 예측 년도
	@XmlElement
	private java.lang.String PREDCT_YEAR;
	
	// 예측 월
	@XmlElement
	private java.lang.String PREDCT_MT;
	
	@XmlElement
	private java.lang.String RCI;
	
	@XmlElement
	private java.lang.String SCR;
	
	@XmlElement
	private java.lang.String PROCCODE;
	
	@XmlElement
	private java.lang.String PROCMSG;
	
	@XmlElement
	private java.lang.String AC_LOW;
	@XmlElement
	private java.lang.String AC_MED;
	@XmlElement
	private java.lang.String AC_HI;
	
	@XmlElement
	private java.lang.String BLOCK_CR_LOW;
	@XmlElement
    private java.lang.String BLOCK_CR_MED;
	@XmlElement
    private java.lang.String BLOCK_CR_HI;
    
	@XmlElement
    private java.lang.String LC_LOW;
	@XmlElement
    private java.lang.String LC_MED;
	@XmlElement
    private java.lang.String LC_HI;
    
	@XmlElement
    private java.lang.String TC_LOW;
	@XmlElement
    private java.lang.String TC_MED;
	@XmlElement
    private java.lang.String TC_HI;
    
    // 행선명
	@XmlElement
    private java.lang.String DIRECT_NM;
    // 관리기관
	@XmlElement
    private java.lang.String DEPT_CODE;
    // 셀종류
	@XmlElement
    private java.lang.String CELL_TYPE;
    // 교통용량
	@XmlElement
    private java.lang.String VMTC_GRAD;
    // 행정구역
	@XmlElement
    private java.lang.String ADM_CODE;
    
	@XmlElement
    private java.lang.String DIRECT_FLAG;
	@XmlElement
    private java.lang.String x;
	@XmlElement
    private java.lang.String y;
    
	
	@XmlElement
    private java.lang.String MINGPCI;
	@XmlElement
    private java.lang.String MAXGPCI;
    
	@XmlElement
    private java.lang.String CNTL_DFECT_DETAIL;
    
	@XmlElement
    private java.lang.String DEPT_NM;
	
	@XmlElement
    private java.lang.String SECT_CELL_ID;
	
	@XmlElement
    private java.lang.String SM_NO;
    
	@XmlElement
    private java.lang.String SCH_SRVY_YEAR;
	
	@XmlElement
	private java.lang.String SRVY_YEAR_MIN;
	
	@XmlElement
    private java.lang.String SRVY_YEAR_MAX;
    
	@XmlElement
	private String GEOJSON;
	
	@XmlElement
	private String MRG_RD_NM;
	
	@JsonProperty(value="MRG_RD_NM")
	public String getMRG_RD_NM() {
		return MRG_RD_NM;
	}

	public void setMRG_RD_NM(String mRG_RD_NM) {
		MRG_RD_NM = mRG_RD_NM;
	}

	@JsonProperty(value="GEOJSON")
	public String getGEOJSON() {
		return GEOJSON;
	}

	public void setGEOJSON(String gEOJSON) {
		GEOJSON = gEOJSON;
	}
	
	/**
	 * TN_MUMM_SCTN_SRVY_DTA.DTA_NO, 
	 * 최소_구간_조사_자료.자료_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="DTA_NO") 
	public java.lang.String getDTA_NO() {
		return this.DTA_NO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.DTA_NO, 
	 * 최소_구간_조사_자료.자료_번호 값설정
	 * @param dtaNo
	 */
	public void setDTA_NO(java.lang.String dtaNo) {
		this.DTA_NO = dtaNo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NO, 
	 * 최소_구간_조사_자료.조사_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NO") 
	public java.lang.String getSRVY_NO() {
		return this.SRVY_NO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NO, 
	 * 최소_구간_조사_자료.조사_번호 값설정
	 * @param srvyNo
	 */
	public void setSRVY_NO(java.lang.String srvyNo) {
		this.SRVY_NO = srvyNo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR, 
	 * 최소_구간_조사_자료.조사_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_YEAR") 
	public java.lang.String getSRVY_YEAR() {
		return this.SRVY_YEAR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_YEAR, 
	 * 최소_구간_조사_자료.조사_년도 값설정
	 * @param srvyYear
	 */
	public void setSRVY_YEAR(java.lang.String srvyYear) {
		this.SRVY_YEAR = srvyYear;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE, 
	 * 최소_구간_조사_자료.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.ROUTE_CODE, 
	 * 최소_구간_조사_자료.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE, 
	 * 최소_구간_조사_자료.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	 public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.DIRECT_CODE, 
	 * 최소_구간_조사_자료.행선_코드 값설정
	 * @param dIRECT_CODE
	 */

	public void setDIRECT_CODE(java.lang.String dIRECT_CODE) {
		this.DIRECT_CODE = dIRECT_CODE;
		super.setDIRECT_NM(this.DIRECT_CODE);
	}
	
	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACK, 
	 * 최소_구간_조사_자료.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACK, 
	 * 최소_구간_조사_자료.차로 값설정
	 * @param cartrk
	 */
	public void setTRACK(java.lang.String cartrk) {
		this.TRACK = cartrk;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.STRTPT, 
	 * 최소_구간_조사_자료.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.Integer getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.STRTPT, 
	 * 최소_구간_조사_자료.시점 값설정
	 * @param pnttm
	 */
	public void setSTRTPT(java.lang.Integer pnttm) {
		this.STRTPT = pnttm;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.ENDPT, 
	 * 최소_구간_조사_자료.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.Integer getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.ENDPT, 
	 * 최소_구간_조사_자료.종점 값설정
	 * @param tmnl
	 */
	public void setENDPT(java.lang.Integer tmnl) {
		this.ENDPT = tmnl;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.IRI_VAL, 
	 * 최소_구간_조사_자료.종단평탄성_값 값읽기
	 * @return
	 */
	@JsonProperty(value="IRI_VAL") 
	public java.lang.String getIRI_VAL() {
		return this.IRI_VAL;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.IRI_VAL, 
	 * 최소_구간_조사_자료.종단평탄성_값 값설정
	 * @param iriVal
	 */
	public void setIRI_VAL(java.lang.String iriVal) {
		this.IRI_VAL = iriVal;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.RD_VAL, 
	 * 최소_구간_조사_자료.소성변형_값 값읽기
	 * @return
	 */
	@JsonProperty(value="RD_VAL") 
	public java.lang.String getRD_VAL() {
		return this.RD_VAL;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.RD_VAL, 
	 * 최소_구간_조사_자료.소성변형_값 값설정
	 * @param rdVal
	 */
	public void setRD_VAL(java.lang.String rdVal) {
		this.RD_VAL = rdVal;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.VRTCAL_CR, 
	 * 최소_구간_조사_자료.종방향_균열 값읽기
	 * @return
	 */
	@JsonProperty(value="VRTCAL_CR") 
	public java.lang.String getVRTCAL_CR() {
		return this.VRTCAL_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.VRTCAL_CR, 
	 * 최소_구간_조사_자료.종방향_균열 값설정
	 * @param vrtcalCr
	 */
	public void setVRTCAL_CR(java.lang.String vrtcalCr) {
		this.VRTCAL_CR = vrtcalCr;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.HRZNTAL_CR, 
	 * 최소_구간_조사_자료.횡방향_균열 값읽기
	 * @return
	 */
	@JsonProperty(value="HRZNTAL_CR") 
	public java.lang.String getHRZNTAL_CR() {
		return this.HRZNTAL_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.HRZNTAL_CR, 
	 * 최소_구간_조사_자료.횡방향_균열 값설정
	 * @param hrzntalCr
	 */
	public void setHRZNTAL_CR(java.lang.String hrzntalCr) {
		this.HRZNTAL_CR = hrzntalCr;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CNSTRCT_JOINT_CR, 
	 * 최소_구간_조사_자료.시공_줄눈_균열 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_JOINT_CR") 
	public java.lang.String getCNSTRCT_JOINT_CR() {
		return this.CNSTRCT_JOINT_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CNSTRCT_JOINT_CR, 
	 * 최소_구간_조사_자료.시공_줄눈_균열 값설정
	 * @param cnstrctJointCr
	 */
	public void setCNSTRCT_JOINT_CR(java.lang.String cnstrctJointCr) {
		this.CNSTRCT_JOINT_CR = cnstrctJointCr;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRTS_BAC_CR, 
	 * 최소_구간_조사_자료.거북등_균열 값읽기
	 * @return
	 */
	@JsonProperty(value="TRTS_BAC_CR") 
	public java.lang.String getTRTS_BAC_CR() {
		return this.TRTS_BAC_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.TRTS_BAC_CR, 
	 * 최소_구간_조사_자료.거북등_균열 값설정
	 * @param trtsBacCr
	 */
	public void setTRTS_BAC_CR(java.lang.String trtsBacCr) {
		this.TRTS_BAC_CR = trtsBacCr;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.PTCHG_CR, 
	 * 최소_구간_조사_자료.패칭_균열 값읽기
	 * @return
	 */
	@JsonProperty(value="PTCHG_CR") 
	public java.lang.String getPTCHG_CR() {
		return this.PTCHG_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.PTCHG_CR, 
	 * 최소_구간_조사_자료.패칭_균열 값설정
	 * @param ptchgCr
	 */
	public void setPTCHG_CR(java.lang.String ptchgCr) {
		this.PTCHG_CR = ptchgCr;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CR_VAL, 
	 * 최소_구간_조사_자료.표면손상값 값읽기
	 * @return
	 */
	@JsonProperty(value="CR_VAL") 
	public java.lang.String getCR_VAL() {
		return this.CR_VAL;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CR_VAL, 
	 * 최소_구간_조사_자료.표면손상값 값설정
	 * @param crVal
	 */
	public void setCR_VAL(java.lang.String crVal) {
		this.CR_VAL = crVal;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CR_IDX, 
	 * 최소_구간_조사_자료.표면손상지수 값읽기
	 * @return
	 */
	@JsonProperty(value="CR_IDX") 
	public java.lang.String getCR_IDX() {
		return this.CR_IDX;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CR_IDX, 
	 * 최소_구간_조사_자료.표면손상지수 값설정
	 * @param crIdx
	 */
	public void setCR_IDX(java.lang.String crIdx) {
		this.CR_IDX = crIdx;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.RD_IDX, 
	 * 최소_구간_조사_자료.소성변형지수 값읽기
	 * @return
	 */
	@JsonProperty(value="RD_IDX") 
	public java.lang.String getRD_IDX() {
		return this.RD_IDX;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.RD_IDX, 
	 * 최소_구간_조사_자료.소성변형지수 값설정
	 * @param rdIdx
	 */
	public void setRD_IDX(java.lang.String rdIdx) {
		this.RD_IDX = rdIdx;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.GPCI, 
	 * 최소_구간_조사_자료.포장상태지수 값읽기
	 * @return
	 */
	@JsonProperty(value="GPCI") 
	public java.lang.String getGPCI() {
		return this.GPCI;
	}
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.PCI, 
	 * 최소_구간_조사_자료.포장상태지수 값설정
	 * @param pci
	 */
	public void setGPCI(java.lang.String GPCI) {
		this.GPCI = GPCI;
	}
	
	/**
	 * TN_MUMM_SCTN_SRVY_DTA.PC_GRAD, 
	 * 최소_구간_조사_자료.포장상태등급 값읽기
	 * @return
	 */
	@JsonProperty(value="PC_GRAD") 
	public java.lang.String getPC_GRAD() {
		return this.PC_GRAD;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.PC_GRAD, 
	 * 최소_구간_조사_자료.포장상태등급 값설정
	 * @param pcGrad
	 */
	public void setPC_GRAD(java.lang.String pcGrad) {
		this.PC_GRAD = pcGrad;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CNTL_DFECT, 
	 * 최소_구간_조사_자료.지배_결함 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTL_DFECT") 
	public java.lang.String getCNTL_DFECT() {
		return this.CNTL_DFECT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CNTL_DFECT, 
	 * 최소_구간_조사_자료.지배_결함 값설정
	 * @param cntlDfect
	 */
	public void setCNTL_DFECT(java.lang.String cntlDfect) {
		this.CNTL_DFECT = cntlDfect;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_KND, 
	 * 최소_구간_조사_자료.조사_종류 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_KND") 
	public java.lang.String getSRVY_KND() {
		return this.SRVY_KND;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_KND, 
	 * 최소_구간_조사_자료.조사_종류 값설정
	 * @param srvyKnd
	 */
	public void setSRVY_KND(java.lang.String srvyKnd) {
		this.SRVY_KND = srvyKnd;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_MT, 
	 * 최소_구간_조사_자료.조사_월 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_MT") 
	public java.lang.String getSRVY_MT() {
		return this.SRVY_MT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_MT, 
	 * 최소_구간_조사_자료.조사_월 값설정
	 * @param srvyMt
	 */
	public void setSRVY_MT(java.lang.String srvyMt) {
		this.SRVY_MT = srvyMt;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.MEMO, 
	 * 최소_구간_조사_자료.메모 값읽기
	 * @return
	 */
	@JsonProperty(value="MEMO") 
	public java.lang.String getMEMO() {
		return this.MEMO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.MEMO, 
	 * 최소_구간_조사_자료.메모 값설정
	 * @param memo
	 */
	public void setMEMO(java.lang.String memo) {
		this.MEMO = memo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_DE, 
	 * 최소_구간_조사_자료.조사_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_DE") 
	public java.lang.String getSRVY_DE() {
		return this.SRVY_DE;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_DE, 
	 * 최소_구간_조사_자료.조사_일자 값설정
	 * @param srvyDe
	 */
	public void setSRVY_DE(java.lang.String srvyDe) {
		this.SRVY_DE = srvyDe;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LA, 
	 * 최소_구간_조사_자료.TRACE1_LA 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACE1_LA") 
	public java.lang.String getTRACE1_LA() {
		return this.TRACE1_LA;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LA, 
	 * 최소_구간_조사_자료.TRACE1_LA 값설정
	 * @param trace1la
	 */
	public void setTRACE1_LA(java.lang.String trace1la) {
		this.TRACE1_LA = trace1la;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LO, 
	 * 최소_구간_조사_자료.TRACE1_LO 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACE1_LO") 
	public java.lang.String getTRACE1_LO() {
		return this.TRACE1_LO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE1_LO, 
	 * 최소_구간_조사_자료.TRACE1_LO 값설정
	 * @param trace1lo
	 */
	public void setTRACE1_LO(java.lang.String trace1lo) {
		this.TRACE1_LO = trace1lo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE2_LA, 
	 * 최소_구간_조사_자료.TRACE2_LA 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACE2_LA") 
	public java.lang.String getTRACE2_LA() {
		return this.TRACE2_LA;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.TRALTRCRTE2_LA, 
	 * 최소_구간_조사_자료.TRACE2_LA 값설정
	 * @param trace2la
	 */
	public void setTRACE2_LA(java.lang.String trace2la) {
		this.TRACE2_LA = trace2la;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE2_LO, 
	 * 최소_구간_조사_자료.TRACE2_LO 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACE2_LO") 
	public java.lang.String getTRACE2_LO() {
		return this.TRACE2_LO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.TRACE2_LO, 
	 * 최소_구간_조사_자료.TRACE2_LO 값설정
	 * @param trace2lo
	 */
	public void setTRACE2_LO(java.lang.String trace2lo) {
		this.TRACE2_LO = trace2lo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NM, 
	 * 최소_구간_조사_자료.조사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_NM") 
	public java.lang.String getSRVY_NM() {
		return this.SRVY_NM;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SRVY_NM, 
	 * 최소_구간_조사_자료.조사_명 값설정
	 * @param srvyNm
	 */
	public void setSRVY_NM(java.lang.String srvyNm) {
		this.SRVY_NM = srvyNm;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM, 
	 * 최소_구간_조사_자료.조사자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="EXMNR_NM") 
	public java.lang.String getEXMNR_NM() {
		return this.EXMNR_NM;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.EXMNR_NM, 
	 * 최소_구간_조사_자료.조사자_명 값설정
	 * @param exmnrNm
	 */
	public void setEXMNR_NM(java.lang.String exmnrNm) {
		this.EXMNR_NM = exmnrNm;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_STRTPT_DC, 
	 * 최소_구간_조사_자료.구간_시점_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="SCTN_STRTPT_DC") 
	public java.lang.String getSCTN_STRTPT_DC() {
		return this.SCTN_STRTPT_DC;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_STRTPT_DC, 
	 * 최소_구간_조사_자료.구간_시점_설명 값설정
	 * @param sctnPnttmDc
	 */
	public void setSCTN_STRTPT_DC(java.lang.String sctnPnttmDc) {
		this.SCTN_STRTPT_DC = sctnPnttmDc;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_ENDPT_DC, 
	 * 최소_구간_조사_자료.구간_종점_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="SCTN_ENDPT_DC") 
	public java.lang.String getSCTN_ENDPT_DC() {
		return this.SCTN_ENDPT_DC;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.SCTN_ENDPT_DC, 
	 * 최소_구간_조사_자료.구간_종점_설명 값설정
	 * @param sctnTmnlDc
	 */
	public void setSCTN_ENDPT_DC(java.lang.String sctnTmnlDc) {
		this.SCTN_ENDPT_DC = sctnTmnlDc;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.ROAD_NM, 
	 * 최소_구간_조사_자료.도로_명 값읽기
	 * @return
	 */
	@JsonProperty(value="ROAD_NM") 
	public java.lang.String getROAD_NM() {
		return this.ROAD_NM;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.ROAD_NM, 
	 * 최소_구간_조사_자료.도로_명 값설정
	 * @param roadNm
	 */
	public void setROAD_NM(java.lang.String roadNm) {
		this.ROAD_NM = roadNm;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.FRNT_IMG_FILE_NM, 
	 * 최소_구간_조사_자료.전방_이미지_파일_명 값읽기
	 * @return
	 */
	@JsonProperty(value="FRNT_IMG_FILE_NM") 
	public java.lang.String getFRNT_IMG_FILE_NM() {
		return this.FRNT_IMG_FILE_NM;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.FRNT_IMG_FILE_NM, 
	 * 최소_구간_조사_자료.전방_이미지_파일_명 값설정
	 * @param bferoomImgFileNm
	 */
	public void setFRNT_IMG_FILE_NM(java.lang.String bferoomImgFileNm) {
		this.FRNT_IMG_FILE_NM = bferoomImgFileNm;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_1, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_1 값읽기
	 * @return
	 */
	@JsonProperty(value="RDSRFC_IMG_FILE_NM_1") 
	public java.lang.String getRDSRFC_IMG_FILE_NM_1() {
		return this.RDSRFC_IMG_FILE_NM_1;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_1, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_1 값설정
	 * @param rdsrfcImgFileNm1
	 */
	public void setRDSRFC_IMG_FILE_NM_1(java.lang.String rdsrfcImgFileNm1) {
		this.RDSRFC_IMG_FILE_NM_1 = rdsrfcImgFileNm1;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_2, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_2 값읽기
	 * @return
	 */
	@JsonProperty(value="RDSRFC_IMG_FILE_NM_2") 
	public java.lang.String getRDSRFC_IMG_FILE_NM_2() {
		return this.RDSRFC_IMG_FILE_NM_2;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.RDSRFC_IMG_FILE_NM_2, 
	 * 최소_구간_조사_자료.노면_이미지_파일_명_2 값설정
	 * @param rdsrfcImgFileNm2
	 */
	public void setRDSRFC_IMG_FILE_NM_2(java.lang.String rdsrfcImgFileNm2) {
		this.RDSRFC_IMG_FILE_NM_2 = rdsrfcImgFileNm2;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ, 
	 * 최소_구간_조사_자료.엑셀_자료_SEQ 값읽기
	 * @return
	 */
	@JsonProperty(value="EXCEL_DTA_SEQ") 
	public java.lang.String getEXCEL_DTA_SEQ() {
		return this.EXCEL_DTA_SEQ;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.EXCEL_DTA_SEQ, 
	 * 최소_구간_조사_자료.엑셀_자료_SEQ 값설정
	 * @param excelDtaSeq
	 */
	public void setEXCEL_DTA_SEQ(java.lang.String excelDtaSeq) {
		this.EXCEL_DTA_SEQ = excelDtaSeq;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.FRMULA_NO, 
	 * 최소_구간_조사_자료.수식_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FRMULA_NO") 
	public java.lang.String getFRMULA_NO() {
		return this.FRMULA_NO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.FRMULA_NO, 
	 * 최소_구간_조사_자료.수식_번호 값설정
	 * @param frmulaNo
	 */
	public void setFRMULA_NO(java.lang.String frmulaNo) {
		this.FRMULA_NO = frmulaNo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.POTHOLE_CR, 
	 * 최소_구간_조사_자료.포트홀_개수 값읽기
	 * @return
	 */
	@JsonProperty(value="POTHOLE_CR") 
	public java.lang.String getPOTHOLE_CR() {
		return this.POTHOLE_CR;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.POTHOLE_CR, 
	 * 최소_구간_조사_자료.포트홀_개수 값설정
	 * @param potholeCo
	 */
	public void setPOTHOLE_CR(java.lang.String pOTHOLE_CR) {
		this.POTHOLE_CR = pOTHOLE_CR;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.RI_IDX, 
	 * 최소_구간_조사_자료.평탄성_지수 값읽기
	 * @return
	 */
	@JsonProperty(value="RI_IDX") 
	public java.lang.String getRI_IDX() {
		return this.RI_IDX;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.RI_IDX, 
	 * 최소_구간_조사_자료.평탄성_지수 값설정
	 * @param riIdx
	 */
	public void setRI_IDX(java.lang.String riIdx) {
		this.RI_IDX = riIdx;
	}
	
	/**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_AT, 
	 * 최소_구간_조사_자료.수정_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_AT") 
	public java.lang.String getUPDT_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_AT, 
	 * 최소_구간_조사_자료.수정_여부 값설정
	 * @param deleteAt
	 */
	public void setUPDT_AT(java.lang.String updtAt) {
		this.UPDT_AT = updtAt;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.DELETE_AT, 
	 * 최소_구간_조사_자료.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.DELETE_AT, 
	 * 최소_구간_조사_자료.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.USE_AT, 
	 * 최소_구간_조사_자료.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.USE_AT, 
	 * 최소_구간_조사_자료.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CRTR_NO, 
	 * 최소_구간_조사_자료.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CRTR_NO, 
	 * 최소_구간_조사_자료.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.CREAT_DT, 
	 * 최소_구간_조사_자료.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.CREAT_DT, 
	 * 최소_구간_조사_자료.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDUSR_NO, 
	 * 최소_구간_조사_자료.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDUSR_NO, 
	 * 최소_구간_조사_자료.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_DT, 
	 * 최소_구간_조사_자료.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_MUMM_SCTN_SRVY_DTA.UPDT_DT, 
	 * 최소_구간_조사_자료.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="ROUTE_NM") 
	public java.lang.String getROUTE_NM() {
		return this.ROUTE_NM;
	}

	public void setROUTE_NM(java.lang.String rOUTE_NM) {
		this.ROUTE_NM = rOUTE_NM;
	}

	@JsonProperty(value="STRTPTNL")
	public java.lang.String getSTRTPTNL() {
		return this.STRTPTNL;
	}

	public void setSTRTPTNL(java.lang.String pNTENDPT) {
		this.STRTPTNL = pNTENDPT;
	}

	@JsonProperty(value="LENGTH")
	public java.lang.String getLENGTH() {
		return this.LENGTH;
	}

	public void setLENGTH(java.lang.String lENGTH) {
		this.LENGTH = lENGTH;
	}

	@JsonProperty(value="FILE_NM")
	public java.lang.String getFILE_NM() {
		return this.FILE_NM;
	}

	public void setFILE_NM(java.lang.String fILE_NM) {
		this.FILE_NM = fILE_NM;
	}

	@JsonProperty(value="DIRECTION")
	public java.lang.String getDIRECTION() {
		return this.DIRECTION;
	}

	public void setDIRECTION(java.lang.String dIRECTION) {
		this.DIRECTION = dIRECTION;
	}

	@JsonProperty(value="SCH_PERIOD_1")
	public String getSCH_PERIOD_1() {
		return this.SCH_PERIOD_1;
	}

	public void setSCH_PERIOD_1(String sCH_PERIOD_1) {
		this.SCH_PERIOD_1 = sCH_PERIOD_1;
	}

	@JsonProperty(value="SCH_PERIOD_2")
	public String getSCH_PERIOD_2() {
		return this.SCH_PERIOD_2;
	}

	public void setSCH_PERIOD_2(String sCH_PERIOD_2) {
		this.SCH_PERIOD_2 = sCH_PERIOD_2;
	}

	@JsonProperty(value="SCH_ROUTCODE")
	public String getSCH_ROUTCODE() {
		return this.SCH_ROUTCODE;
	}

	public void setSCH_ROUTCODE(String sCH_ROUTCODE) {
		this.SCH_ROUTCODE = sCH_ROUTCODE;
	}

	@JsonProperty(value="SCH_BSCODE")
	public String getSCH_BSCODE() {
		return this.SCH_BSCODE;
	}

	public void setSCH_BSCODE(String sCH_BSCODE) {
		this.SCH_BSCODE = sCH_BSCODE;
	}

	@JsonProperty(value="SCH_ROUTNM")
	public String getSCH_ROUTNM() {
		return this.SCH_ROUTNM;
	}

	public void setSCH_ROUTNM(String sCH_ROUTNM) {
		this.SCH_ROUTNM = sCH_ROUTNM;
	}

	@JsonProperty(value="AC_IDX")
	public java.lang.String getAC_IDX() {
		return this.AC_IDX;
	}

	public void setAC_IDX(java.lang.String aC_IDX) {
		this.AC_IDX = aC_IDX;
	}

	@JsonProperty(value="BLOCK_CR")
	public java.lang.String getBLOCK_CR() {
		return this.BLOCK_CR;
	}

	public void setBLOCK_CR(java.lang.String bLOCK_CR) {
		this.BLOCK_CR = bLOCK_CR;
	}

	@JsonProperty(value="LC_IDX")
	public java.lang.String getLC_IDX() {
		return this.LC_IDX;
	}

	public void setLC_IDX(java.lang.String lC_IDX) {
		this.LC_IDX = lC_IDX;
	}

	@JsonProperty(value="TC_IDX")
	public java.lang.String getTC_IDX() {
		return this.TC_IDX;
	}

	public void setTC_IDX(java.lang.String tC_IDX) {
		this.TC_IDX = tC_IDX;
	}

	@JsonProperty(value="PTCHG_IDX")
	public java.lang.String getPTCHG_IDX() {
		return this.PTCHG_IDX;
	}

	public void setPTCHG_IDX(java.lang.String pTCHG_IDX) {
		this.PTCHG_IDX = pTCHG_IDX;
	}

	@JsonProperty(value="POTHOLE_IDX")
	public java.lang.String getPOTHOLE_IDX() {
		return this.POTHOLE_IDX;
	}

	public void setPOTHOLE_IDX(java.lang.String pOTHOLE_IDX) {
		this.POTHOLE_IDX = pOTHOLE_IDX;
	}

	@JsonProperty(value="SCH_STRTPT")
	public String getSCH_STRTPT() {
		return this.SCH_STRTPT;
	}

	public void setSCH_STRTPT(String sCH_STRTPT) {
		this.SCH_STRTPT = sCH_STRTPT;
	}

	@JsonProperty(value="SCH_ENDPT")
	public String getSCH_ENDPT() {
		return this.SCH_ENDPT;
	}

	public void setSCH_ENDPT(String sCH_ENDPT) {
		this.SCH_ENDPT = sCH_ENDPT;
	}

	@JsonProperty(value="ROAD_NO_VAL") 
	public java.lang.String getROAD_NO_VAL() {
		return ROAD_NO_VAL;
	}

	public void setROAD_NO_VAL(java.lang.String rOAD_NO_VAL) {
		ROAD_NO_VAL = rOAD_NO_VAL;
	}

	@JsonProperty(value="DMG_CUZ_CLMT") 
	public java.lang.String getDMG_CUZ_CLMT() {
		return DMG_CUZ_CLMT;
	}
	
	
	@JsonProperty(value="CUZ") 
	public java.lang.String getCUZ() {
		return CUZ;
	}
	public void setCUZ(java.lang.String CUZ) {
		CUZ = CUZ;
	}
	



	public void setDMG_CUZ_CLMT(java.lang.String dMG_CUZ_CLMT) {
		DMG_CUZ_CLMT = dMG_CUZ_CLMT;
	}

	@JsonProperty(value="DMG_CUZ_VMTC") 
	public java.lang.String getDMG_CUZ_VMTC() {
		return DMG_CUZ_VMTC;
	}

	public void setDMG_CUZ_VMTC(java.lang.String dMG_CUZ_VMTC) {
		DMG_CUZ_VMTC = dMG_CUZ_VMTC;
	}

	@JsonProperty(value="DMG_CUZ_ETC") 
	public java.lang.String getDMG_CUZ_ETC() {
		return DMG_CUZ_ETC;
	}

	public void setDMG_CUZ_ETC(java.lang.String dMG_CUZ_ETC) {
		DMG_CUZ_ETC = dMG_CUZ_ETC;
	}

	@JsonProperty(value="ROAD_NO") 
	public java.lang.String getROAD_NO() {
		return ROAD_NO;
	}

	public void setROAD_NO(java.lang.String rOAD_NO) {
		ROAD_NO = rOAD_NO;
	}

	@JsonProperty(value="ROAD_GRAD")
	public java.lang.String getROAD_GRAD() {
		return ROAD_GRAD;
	}

	public void setROAD_GRAD(java.lang.String rOAD_GRAD) {
		ROAD_GRAD = rOAD_GRAD;
	}

	@JsonProperty(value="CELL_ID")
    public java.lang.String getCELL_ID() {
        return CELL_ID;
    }

    public void setCELL_ID(java.lang.String cELL_ID) {
        CELL_ID = cELL_ID;
    }

    @JsonProperty(value="CELL_ID_ARR")
    public List<String> getCELL_ID_ARR() {
        return CELL_ID_ARR;
    }

    public void setCELL_ID_ARR(List<String> cELL_ID_ARR) {
        CELL_ID_ARR = cELL_ID_ARR;
    }
    
    @JsonProperty(value="BC_IDX")
    public java.lang.String getBC_IDX() {
        return BC_IDX;
    }

    public void setBC_IDX(java.lang.String bC_IDX) {
        BC_IDX = bC_IDX;
    }
    
    @JsonProperty(value="IRI")
    public java.lang.String getIRI() {
        return IRI;
    }

    public void setIRI(java.lang.String iRI) {
        IRI = iRI;
    }
    
    @JsonProperty(value="CODE_NM")
    public java.lang.String getCODE_NM() {
        return CODE_NM;
    }

    public void setCODE_NM(java.lang.String cODE_NM) {
        CODE_NM = cODE_NM;
    }
    
    @JsonProperty(value="ROAD_NAME")
    public java.lang.String getROAD_NAME() {
        return ROAD_NAME;
    }

    public void setROAD_NAME(java.lang.String rOAD_NAME) {
        ROAD_NAME = rOAD_NAME;
    }

    @JsonProperty(value="PREDCT_YEAR")
	public java.lang.String getPREDCT_YEAR() {
		return this.PREDCT_YEAR;
	}

	public void setPREDCT_YEAR(java.lang.String pREDCT_YEAR) {
		this.PREDCT_YEAR = pREDCT_YEAR;
	}

	@JsonProperty(value="PREDCT_MT")
	public java.lang.String getPREDCT_MT() {
		return this.PREDCT_MT;
	}

	public void setPREDCT_MT(java.lang.String pREDCT_MT) {
		this.PREDCT_MT = pREDCT_MT;
	}

	@JsonProperty(value="RCI")
	public java.lang.String getRCI() {
		return RCI;
	}

	public void setRCI(java.lang.String rCI) {
		RCI = rCI;
	}

	@JsonProperty(value="SCR")
	public java.lang.String getSCR() {
		return SCR;
	}

	public void setSCR(java.lang.String sCR) {
		SCR = sCR;
	}

	@JsonProperty(value="PROCCODE")
	public java.lang.String getPROCCODE() {
		return PROCCODE;
	}

	public void setPROCCODE(java.lang.String pROCCODE) {
		PROCCODE = pROCCODE;
	}

	@JsonProperty(value="PROCMSG")
	public java.lang.String getPROCMSG() {
		return PROCMSG;
	}

	public void setPROCMSG(java.lang.String pROCMSG) {
		PROCMSG = pROCMSG;
	}

	@JsonProperty(value="AC_LOW") 
    public java.lang.String getAC_LOW() {
        return AC_LOW;
    }

    public void setAC_LOW(java.lang.String aC_LOW) {
        AC_LOW = aC_LOW;
    }

    @JsonProperty(value="AC_MED") 
    public java.lang.String getAC_MED() {
        return AC_MED;
    }

    public void setAC_MED(java.lang.String aC_MED) {
        AC_MED = aC_MED;
    }

    @JsonProperty(value="AC_HI") 
    public java.lang.String getAC_HI() {
        return AC_HI;
    }

    public void setAC_HI(java.lang.String aC_HI) {
        AC_HI = aC_HI;
    }

    @JsonProperty(value="BLOCK_CR_LOW") 
    public java.lang.String getBLOCK_CR_LOW() {
        return BLOCK_CR_LOW;
    }

    public void setBLOCK_CR_LOW(java.lang.String bLOCK_CR_LOW) {
        BLOCK_CR_LOW = bLOCK_CR_LOW;
    }

    @JsonProperty(value="BLOCK_CR_MED")
    public java.lang.String getBLOCK_CR_MED() {
        return BLOCK_CR_MED;
    }

    public void setBLOCK_CR_MED(java.lang.String bLOCK_CR_MED) {
        BLOCK_CR_MED = bLOCK_CR_MED;
    }

    @JsonProperty(value="BLOCK_CR_HI")
    public java.lang.String getBLOCK_CR_HI() {
        return BLOCK_CR_HI;
    }

    public void setBLOCK_CR_HI(java.lang.String bLOCK_CR_HI) {
        BLOCK_CR_HI = bLOCK_CR_HI;
    }

    @JsonProperty(value="LC_LOW")
    public java.lang.String getLC_LOW() {
        return LC_LOW;
    }

    public void setLC_LOW(java.lang.String lC_LOW) {
        LC_LOW = lC_LOW;
    }

    @JsonProperty(value="LC_MED")
    public java.lang.String getLC_MED() {
        return LC_MED;
    }

    public void setLC_MED(java.lang.String lC_MED) {
        LC_MED = lC_MED;
    }

    @JsonProperty(value="LC_HI")
    public java.lang.String getLC_HI() {
        return LC_HI;
    }

    public void setLC_HI(java.lang.String lC_HI) {
        LC_HI = lC_HI;
    }

    @JsonProperty(value="TC_LOW")
    public java.lang.String getTC_LOW() {
        return TC_LOW;
    }

    public void setTC_LOW(java.lang.String tC_LOW) {
        TC_LOW = tC_LOW;
    }

    @JsonProperty(value="TC_MED")
    public java.lang.String getTC_MED() {
        return TC_MED;
    }

    public void setTC_MED(java.lang.String tC_MED) {
        TC_MED = tC_MED;
    }
    
    @JsonProperty(value="TC_HI")
    public java.lang.String getTC_HI() {
        return TC_HI;
    }

    public void setTC_HI(java.lang.String tC_HI) {
        TC_HI = tC_HI;
    }

    @JsonProperty(value="DEPT_CODE")
    public java.lang.String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(java.lang.String dEPT_CODE) {
        DEPT_CODE = dEPT_CODE;
    }

    @JsonProperty(value="CELL_TYPE")
    public java.lang.String getCELL_TYPE() {
        return CELL_TYPE;
    }

    public void setCELL_TYPE(java.lang.String cELL_TYPE) {
        CELL_TYPE = cELL_TYPE;
    }

    @JsonProperty(value="VMTC_GRAD")
    public java.lang.String getVMTC_GRAD() {
        return VMTC_GRAD;
    }

    public void setVMTC_GRAD(java.lang.String vMTC_GRAD) {
        VMTC_GRAD = vMTC_GRAD;
    }

    @JsonProperty(value="ADM_CODE")
    public java.lang.String getADM_CODE() {
        return ADM_CODE;
    }

    public void setADM_CODE(java.lang.String aDM_CODE) {
        ADM_CODE = aDM_CODE;
    }

    @JsonProperty(value="DIRECT_NM")
    public java.lang.String getDIRECT_NM() {
        return DIRECT_NM;
    }

    public void setDIRECT_NM(java.lang.String dIRECT_NM) {
        DIRECT_NM = dIRECT_NM;
    }

    @JsonProperty(value="OBJECT_ID_ARR")
    public List<String> getOBJECT_ID_ARR() {
        return OBJECT_ID_ARR;
    }

    public void setOBJECT_ID_ARR(List<String> oBJECT_ID_ARR) {
        OBJECT_ID_ARR = oBJECT_ID_ARR;
    }

    @JsonProperty(value="OBJECT_ID")
    public java.lang.String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(java.lang.String oBJECT_ID) {
        OBJECT_ID = oBJECT_ID;
    }

    @JsonProperty(value="DIRECT_FLAG")
    public java.lang.String getDIRECT_FLAG() {
        return DIRECT_FLAG;
    }

    public void setDIRECT_FLAG(java.lang.String dIRECT_FLAG) {
        DIRECT_FLAG = dIRECT_FLAG;
    }

    @JsonProperty(value="x")
    public java.lang.String getX() {
        return x;
    }

    public void setX(java.lang.String x) {
        this.x = x;
    }

    @JsonProperty(value="y")
    public java.lang.String getY() {
        return y;
    }

    public void setY(java.lang.String y) {
        this.y = y;
    }

    @JsonProperty(value="CR")
    public java.lang.String getCR() {
        return CR;
    }

    public void setCR(java.lang.String cR) {
        CR = cR;
    }

    @JsonProperty(value="MINGPCI")
    public java.lang.String getMINGPCI() {
        return MINGPCI;
    }

    public void setMINGPCI(java.lang.String mINGPCI) {
        MINGPCI = mINGPCI;
    }

    @JsonProperty(value="MAXGPCI")
    public java.lang.String getMAXGPCI() {
        return MAXGPCI;
    }

    public void setMAXGPCI(java.lang.String mAXGPCI) {
        MAXGPCI = mAXGPCI;
    }
    
    @JsonProperty(value="CNTL_DFECT_DETAIL")
    public java.lang.String getCNTL_DFECT_DETAIL() {
        return CNTL_DFECT_DETAIL;
    }

    public void setCNTL_DFECT_DETAIL(java.lang.String cNTL_DFECT_DETAIL) {
        CNTL_DFECT_DETAIL = cNTL_DFECT_DETAIL;
    }

    @JsonProperty(value="RPAIR_TA_AT")
	public java.lang.String getRPAIR_TA_AT() {
		return this.RPAIR_TA_AT;
	}

	public void setRPAIR_TA_AT(java.lang.String rPAIR_TA_AT) {
		this.RPAIR_TA_AT = rPAIR_TA_AT;
	}

	@JsonProperty(value="DEPT_NM")
    public java.lang.String getDEPT_NM() {
        return DEPT_NM;
    }

    public void setDEPT_NM(java.lang.String dEPT_NM) {
        DEPT_NM = dEPT_NM;
    }

    @JsonProperty(value="rstFlag")
    public java.lang.String getRstFlag() {
        return rstFlag;
    }

    public void setRstFlag(java.lang.String rstFlag) {
        this.rstFlag = rstFlag;
    }

    @JsonProperty(value="SECT_CELL_ID")
	public java.lang.String getSECT_CELL_ID() {
		return SECT_CELL_ID;
	}

	public void setSECT_CELL_ID(java.lang.String sECT_CELL_ID) {
		SECT_CELL_ID = sECT_CELL_ID;
	}

	@JsonProperty(value="SM_NO")
	public java.lang.String getSM_NO() {
		return SM_NO;
	}

	public void setSM_NO(java.lang.String sM_NO) {
		SM_NO = sM_NO;
	}

	@JsonProperty(value="SCH_SRVY_YEAR")
	public java.lang.String getSCH_SRVY_YEAR() {
		return SCH_SRVY_YEAR;
	}

	public void setSCH_SRVY_YEAR(java.lang.String sCH_SRVY_YEAR) {
		SCH_SRVY_YEAR = sCH_SRVY_YEAR;
	}

	@JsonProperty(value="SRVY_YEAR_MIN")
    public java.lang.String getSRVY_YEAR_MIN() {
        return SRVY_YEAR_MIN;
    }

    public void setSRVY_YEAR_MIN(java.lang.String sRVY_YEAR_MIN) {
        SRVY_YEAR_MIN = sRVY_YEAR_MIN;
    }

    @JsonProperty(value="SRVY_YEAR_MAX")
    public java.lang.String getSRVY_YEAR_MAX() {
        return SRVY_YEAR_MAX;
    }

    public void setSRVY_YEAR_MAX(java.lang.String sRVY_YEAR_MAX) {
        SRVY_YEAR_MAX = sRVY_YEAR_MAX;
    }
    
}
