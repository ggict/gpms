
package kr.go.gg.gpms.smdtagnlsttus.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 집계_자료_일반_현황
 *
 * @Class Name : SmDtaGnlSttusVO.java
 * @Description : SmDtaGnlSttus VO class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-09-12
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class SmDtaGnlSttusVO extends BaseVO {

	public SmDtaGnlSttusVO() {
		super();
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SM_NO,
	 * 집계_자료_일반_현황.집계_번호
	 */
	@XmlElement
	private java.lang.String SM_NO;

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_NO,
	 * 집계_자료_일반_현황.조사_번호
	 */
	@XmlElement
	private java.lang.String SRVY_NO;

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_YEAR,
	 * 집계_자료_일반_현황.조사_년도
	 */
	@XmlElement
	private java.lang.String SRVY_YEAR;

	/**
	 * TN_SM_DTA_GNL_STTUS.ROUTE_CODE,
	 * 집계_자료_일반_현황.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/**
	 * TN_SM_DTA_GNL_STTUS.DIRECT_CODE,
	 * 집계_자료_일반_현황.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/**
	 * TN_SM_DTA_GNL_STTUS.TRACK,
	 * 집계_자료_일반_현황.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/**
	 * TN_SM_DTA_GNL_STTUS.STRTPT,
	 * 집계_자료_일반_현황.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/**
	 * TN_SM_DTA_GNL_STTUS.ENDPT,
	 * 집계_자료_일반_현황.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/**
	 * TN_SM_DTA_GNL_STTUS.IRI_VAL,
	 * 집계_자료_일반_현황.종단평탄성_값
	 */
	@XmlElement
	private java.lang.String IRI_VAL;

	/**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_CR,
	 * 집계_자료_일반_현황.포트홀_균열
	 */
	@XmlElement
	private java.lang.String POTHOLE_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_VAL,
	 * 집계_자료_일반_현황.소성변형_값
	 */
	@XmlElement
	private java.lang.String RD_VAL;

	/**
	 * TN_SM_DTA_GNL_STTUS.VRTCAL_CR,
	 * 집계_자료_일반_현황.종방향_균열
	 */
	@XmlElement
	private java.lang.String VRTCAL_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.HRZNTAL_CR,
	 * 집계_자료_일반_현황.횡방향_균열
	 */
	@XmlElement
	private java.lang.String HRZNTAL_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.CNSTRCT_JOINT_CR,
	 * 집계_자료_일반_현황.시공_줄눈_균열
	 */
	@XmlElement
	private java.lang.String CNSTRCT_JOINT_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.TRTS_BAC_CR,
	 * 집계_자료_일반_현황.거북등_균열
	 */
	@XmlElement
	private java.lang.String TRTS_BAC_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_CR,
	 * 집계_자료_일반_현황.패칭_균열
	 */
	@XmlElement
	private java.lang.String PTCHG_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_VAL,
	 * 집계_자료_일반_현황.표면손상값
	 */
	@XmlElement
	private java.lang.String CR_VAL;

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_IDX,
	 * 집계_자료_일반_현황.표면손상지수
	 */
	@XmlElement
	private java.lang.String CR_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_IDX,
	 * 집계_자료_일반_현황.소성변형지수
	 */
	@XmlElement
	private java.lang.String RD_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.RI_IDX,
	 * 집계_자료_일반_현황.평탄성_지수
	 */
	@XmlElement
	private java.lang.String RI_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.CNTL_DFECT,
	 * 집계_자료_일반_현황.지배_결함
	 */
	@XmlElement
	private java.lang.String CNTL_DFECT;

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_KND,
	 * 집계_자료_일반_현황.조사_종류
	 */
	@XmlElement
	private java.lang.String SRVY_KND;

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_MT,
	 * 집계_자료_일반_현황.조사_월
	 */
	@XmlElement
	private java.lang.String SRVY_MT;

	/**
	 * TN_SM_DTA_GNL_STTUS.FRMULA_NO,
	 * 집계_자료_일반_현황.수식_번호
	 */
	@XmlElement
	private java.lang.String FRMULA_NO;

	/**
	 * TN_SM_DTA_GNL_STTUS.TRNSPORT_QY,
	 * 집계_자료_일반_현황.교통_량
	 */
	@XmlElement
	private java.lang.String TRNSPORT_QY;

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_GRAD,
	 * 집계_자료_일반_현황.표면손상_등급
	 */
	@XmlElement
	private java.lang.String CR_GRAD;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_GRAD,
	 * 집계_자료_일반_현황.소성변형_등급
	 */
	@XmlElement
	private java.lang.String RD_GRAD;

	/**
	 * TN_SM_DTA_GNL_STTUS.IRI_GRAD,
	 * 집계_자료_일반_현황.종단평탄성_등급
	 */
	@XmlElement
	private java.lang.String IRI_GRAD;

	/**
	 * TN_SM_DTA_GNL_STTUS.DELETE_AT,
	 * 집계_자료_일반_현황.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/**
	 * TN_SM_DTA_GNL_STTUS.USE_AT,
	 * 집계_자료_일반_현황.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/**
	 * TN_SM_DTA_GNL_STTUS.CRTR_NO,
	 * 집계_자료_일반_현황.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/**
	 * TN_SM_DTA_GNL_STTUS.CREAT_DT,
	 * 집계_자료_일반_현황.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/**
	 * TN_SM_DTA_GNL_STTUS.UPDUSR_NO,
	 * 집계_자료_일반_현황.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/**
	 * TN_SM_DTA_GNL_STTUS.UPDT_DT,
	 * 집계_자료_일반_현황.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR,
	 * 집계_자료_일반_현황.블럭_균열
	 */
	@XmlElement
	private java.lang.String BLOCK_CR;

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_LT,
	 * 집계_자료_일반_현황.균열_길이
	 */
	@XmlElement
	private java.lang.String CR_LT;

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_WID,
	 * 집계_자료_일반_현황.균열_폭
	 */
	@XmlElement
	private java.lang.String CR_WID;

	/**
	 * TN_SM_DTA_GNL_STTUS.NHPCI,
	 * 집계_자료_일반_현황.NHPCI
	 */
	@XmlElement
	private java.lang.String NHPCI;

	/**
	 * TN_SM_DTA_GNL_STTUS.SPI,
	 * 집계_자료_일반_현황.SPI
	 */
	@XmlElement
	private java.lang.String SPI;

	/**
	 * TN_SM_DTA_GNL_STTUS.XCR,
	 * 집계_자료_일반_현황.NHPCI_균열
	 */
	@XmlElement
	private java.lang.String XCR;

	/**
	 * TN_SM_DTA_GNL_STTUS.RCI,
	 * 집계_자료_일반_현황.표면_조도_지수
	 */
	@XmlElement
	private java.lang.String RCI;

	/**
	 * TN_SM_DTA_GNL_STTUS.SCR,
	 * 집계_자료_일반_현황.표면_상태_지수
	 */
	@XmlElement
	private java.lang.String SCR;

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_LOW,
	 * 집계_자료_일반_현황.거북등균열_LOW
	 */
	@XmlElement
	private java.lang.String AC_LOW;

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_MED,
	 * 집계_자료_일반_현황.거북등균열_MED
	 */
	@XmlElement
	private java.lang.String AC_MED;

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_HI,
	 * 집계_자료_일반_현황.거북등균열_HI
	 */
	@XmlElement
	private java.lang.String AC_HI;

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_LOW,
	 * 집계_자료_일반_현황.종방향균열_LOW
	 */
	@XmlElement
	private java.lang.String LC_LOW;

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_MED,
	 * 집계_자료_일반_현황.종방향균열_MED
	 */
	@XmlElement
	private java.lang.String LC_MED;

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_HI,
	 * 집계_자료_일반_현황.종방향균열_HI
	 */
	@XmlElement
	private java.lang.String LC_HI;

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_LOW,
	 * 집계_자료_일반_현황.횡방향균열_LOW
	 */
	@XmlElement
	private java.lang.String TC_LOW;

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_MED,
	 * 집계_자료_일반_현황.횡방향균열_MED
	 */
	@XmlElement
	private java.lang.String TC_MED;

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_HI,
	 * 집계_자료_일반_현황.횡방향균열_HI
	 */
	@XmlElement
	private java.lang.String TC_HI;

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_IDX,
	 * 집계_자료_일반_현황.거북등균열_지수
	 */
	@XmlElement
	private java.lang.String AC_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_IDX,
	 * 집계_자료_일반_현황.종방향균열_지수
	 */
	@XmlElement
	private java.lang.String LC_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_IDX,
	 * 집계_자료_일반_현황.횡방향균열_지수
	 */
	@XmlElement
	private java.lang.String TC_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_IDX,
	 * 집계_자료_일반_현황.패칭_지수
	 */
	@XmlElement
	private java.lang.String PTCHG_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_LOW,
	 * 집계_자료_일반_현황.소성변형_LOW
	 */
	@XmlElement
	private java.lang.String RD_LOW;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_MED,
	 * 집계_자료_일반_현황.소성변형_MED
	 */
	@XmlElement
	private java.lang.String RD_MED;

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_HI,
	 * 집계_자료_일반_현황.소성변형_HI
	 */
	@XmlElement
	private java.lang.String RD_HI;

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_LOW,
	 * 집계_자료_일반_현황.블럭_균열_LOW
	 */
	@XmlElement
	private java.lang.String BLOCK_CR_LOW;

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_MED,
	 * 집계_자료_일반_현황.블럭_균열_MED
	 */
	@XmlElement
	private java.lang.String BLOCK_CR_MED;

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_HI,
	 * 집계_자료_일반_현황.블럭_균열_HI
	 */
	@XmlElement
	private java.lang.String BLOCK_CR_HI;

	/**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_IDX,
	 * 집계_자료_일반_현황.포트홀_지수
	 */
	@XmlElement
	private java.lang.String POTHOLE_IDX;

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_CLMT,
	 * 집계_자료_일반_현황.파손_원인_기후
	 */
	@XmlElement
	private java.lang.String DMG_CUZ_CLMT;

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_VMTC,
	 * 집계_자료_일반_현황.파손_원인_교통량
	 */
	@XmlElement
	private java.lang.String DMG_CUZ_VMTC;

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_ETC,
	 * 집계_자료_일반_현황.파손_원인_기타
	 */
	@XmlElement
	private java.lang.String DMG_CUZ_ETC;

	/**
	 * TN_SM_DTA_GNL_STTUS.GPCI,
	 * 집계_자료_일반_현황.GPCI
	 */
	@XmlElement
	private java.lang.String GPCI;

	/**
	 * TN_SM_DTA_GNL_STTUS.PC_GRAD,
	 * 집계_자료_일반_현황.포장상태등급
	 */
	@XmlElement
	private java.lang.String PC_GRAD;

	/**
	 * TN_SM_DTA_GNL_STTUS.BC_IDX,
	 * 집계_자료_일반_현황.블럭균열_지수
	 */
	@XmlElement
	private java.lang.String BC_IDX;

	/**
	 * 도로명
	 */
	@XmlElement
	private java.lang.String ROAD_NM;

	/**
	 * 도로 구분
	 */
	@XmlElement
	private java.lang.String ROAD_GRAD;

	/**
	 * 관리기관
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/**
	 * 셀 타입
	 */
	@XmlElement
	private java.lang.String CELL_TYPE;

	/**
	 * 교통용량
	 */
	@XmlElement
	private java.lang.String VMTC_GRAD;

	/**
	 * 행정동 코드
	 */
	@XmlElement
	private java.lang.String ADM_CODE;

	/**
	 * 셀 ID
	 */
	@XmlElement
	private java.lang.String CELL_ID;

	/**
	 * 노선번호
	 */
	@XmlElement
	private java.lang.String ROAD_NO_VAL;


	/**
	 * 주 파손 명
	 */
	@XmlElement
	private java.lang.String CNTL_DFECT_NM;



	// 2018.11.05 감소율 추가
	/**
	 * 거북등균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String AC_RDUCT_RATE;

	/**
	 * 블럭균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String BC_RDUCT_RATE;

	/**
	 * 종방향균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String LC_RDUCT_RATE;

	/**
	 * 횡방향균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String TC_RDUCT_RATE;

	/**
	 * 패칭균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String PTCHG_RDUCT_RATE;

	/**
	 * 포트홀균열의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String POT_RDUCT_RATE;

	/**
	 * 소성변형의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String RD_RDUCT_RATE;

	/**
	 * 종단평탄성의 SPI 감소률
	 */
	@XmlElement
	private java.lang.String RCI_RDUCT_RATE;
	
	/**
	 * 관리도로
	 */
	@XmlElement
	private String MRG_RD_NM;

	/**
	 * 공사구간
	 */
	@XmlElement
	private String CNTRWK_AT;

	/**
	 * 미개통구간
	 */
	@XmlElement
	private String UNOPN_AT;
	
	@JsonProperty(value="MRG_RD_NM")
	public String getMRG_RD_NM() {
		return MRG_RD_NM;
	}

	public void setMRG_RD_NM(String mRG_RD_NM) {
		MRG_RD_NM = mRG_RD_NM;
	}
	
	@JsonProperty(value="CNTRWK_AT")
	public String getCNTRWK_AT() {
		return CNTRWK_AT;
	}

	public void setCNTRWK_AT(String cNTRWK_AT) {
		CNTRWK_AT = cNTRWK_AT;
	}

	@JsonProperty(value="UNOPN_AT")
	public String getUNOPN_AT() {
		return UNOPN_AT;
	}

	public void setUNOPN_AT(String uNOPN_AT) {
		UNOPN_AT = uNOPN_AT;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SM_NO,
	 * 집계_자료_일반_현황.집계_번호
	 * @return
	 */
	@JsonProperty(value="SM_NO")
	public java.lang.String getSM_NO() {
		return this.SM_NO;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SM_NO,
	 * 집계_자료_일반_현황.집계_번호
	 * @param smNo
	 */
	public void setSM_NO(java.lang.String smNo) {
		this.SM_NO = smNo;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_NO,
	 * 집계_자료_일반_현황.조사_번호
	 * @return
	 */
	@JsonProperty(value="SRVY_NO")
	public java.lang.String getSRVY_NO() {
		return this.SRVY_NO;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SRVY_NO,
	 * 집계_자료_일반_현황.조사_번호
	 * @param srvyNo
	 */
	public void setSRVY_NO(java.lang.String srvyNo) {
		this.SRVY_NO = srvyNo;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_YEAR,
	 * 집계_자료_일반_현황.조사_년도
	 * @return
	 */
	@JsonProperty(value="SRVY_YEAR")
	public java.lang.String getSRVY_YEAR() {
		return this.SRVY_YEAR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SRVY_YEAR,
	 * 집계_자료_일반_현황.조사_년도
	 * @param srvyYear
	 */
	public void setSRVY_YEAR(java.lang.String srvyYear) {
		this.SRVY_YEAR = srvyYear;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.ROUTE_CODE,
	 * 집계_자료_일반_현황.노선_코드
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE")
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.ROUTE_CODE,
	 * 집계_자료_일반_현황.노선_코드
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.DIRECT_CODE,
	 * 집계_자료_일반_현황.행선_코드
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE")
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.DIRECT_CODE,
	 * 집계_자료_일반_현황.행선_코드
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
		super.setDIRECT_NM(directCode);
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TRACK,
	 * 집계_자료_일반_현황.차로
	 * @return
	 */
	@JsonProperty(value="TRACK")
	public java.lang.String getTRACK() {
		return this.TRACK;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TRACK,
	 * 집계_자료_일반_현황.차로
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.STRTPT,
	 * 집계_자료_일반_현황.시점
	 * @return
	 */
	@JsonProperty(value="STRTPT")
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.STRTPT,
	 * 집계_자료_일반_현황.시점
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.ENDPT,
	 * 집계_자료_일반_현황.종점
	 * @return
	 */
	@JsonProperty(value="ENDPT")
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.ENDPT,
	 * 집계_자료_일반_현황.종점
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.IRI_VAL,
	 * 집계_자료_일반_현황.종단평탄성_값
	 * @return
	 */
	@JsonProperty(value="IRI_VAL")
	public java.lang.String getIRI_VAL() {
		return this.IRI_VAL;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.IRI_VAL,
	 * 집계_자료_일반_현황.종단평탄성_값
	 * @param iriVal
	 */
	public void setIRI_VAL(java.lang.String iriVal) {
		this.IRI_VAL = iriVal;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_CR,
	 * 집계_자료_일반_현황.포트홀_균열
	 * @return
	 */
	@JsonProperty(value="POTHOLE_CR")
	public java.lang.String getPOTHOLE_CR() {
		return this.POTHOLE_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_CR,
	 * 집계_자료_일반_현황.포트홀_균열
	 * @param potholeCr
	 */
	public void setPOTHOLE_CR(java.lang.String potholeCr) {
		this.POTHOLE_CR = potholeCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_VAL,
	 * 집계_자료_일반_현황.소성변형_값
	 * @return
	 */
	@JsonProperty(value="RD_VAL")
	public java.lang.String getRD_VAL() {
		return this.RD_VAL;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_VAL,
	 * 집계_자료_일반_현황.소성변형_값
	 * @param rdVal
	 */
	public void setRD_VAL(java.lang.String rdVal) {
		this.RD_VAL = rdVal;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.VRTCAL_CR,
	 * 집계_자료_일반_현황.종방향_균열
	 * @return
	 */
	@JsonProperty(value="VRTCAL_CR")
	public java.lang.String getVRTCAL_CR() {
		return this.VRTCAL_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.VRTCAL_CR,
	 * 집계_자료_일반_현황.종방향_균열
	 * @param vrtcalCr
	 */
	public void setVRTCAL_CR(java.lang.String vrtcalCr) {
		this.VRTCAL_CR = vrtcalCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.HRZNTAL_CR,
	 * 집계_자료_일반_현황.횡방향_균열
	 * @return
	 */
	@JsonProperty(value="HRZNTAL_CR")
	public java.lang.String getHRZNTAL_CR() {
		return this.HRZNTAL_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.HRZNTAL_CR,
	 * 집계_자료_일반_현황.횡방향_균열
	 * @param hrzntalCr
	 */
	public void setHRZNTAL_CR(java.lang.String hrzntalCr) {
		this.HRZNTAL_CR = hrzntalCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CNSTRCT_JOINT_CR,
	 * 집계_자료_일반_현황.시공_줄눈_균열
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_JOINT_CR")
	public java.lang.String getCNSTRCT_JOINT_CR() {
		return this.CNSTRCT_JOINT_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CNSTRCT_JOINT_CR,
	 * 집계_자료_일반_현황.시공_줄눈_균열
	 * @param cnstrctJointCr
	 */
	public void setCNSTRCT_JOINT_CR(java.lang.String cnstrctJointCr) {
		this.CNSTRCT_JOINT_CR = cnstrctJointCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TRTS_BAC_CR,
	 * 집계_자료_일반_현황.거북등_균열
	 * @return
	 */
	@JsonProperty(value="TRTS_BAC_CR")
	public java.lang.String getTRTS_BAC_CR() {
		return this.TRTS_BAC_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TRTS_BAC_CR,
	 * 집계_자료_일반_현황.거북등_균열
	 * @param trtsBacCr
	 */
	public void setTRTS_BAC_CR(java.lang.String trtsBacCr) {
		this.TRTS_BAC_CR = trtsBacCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_CR,
	 * 집계_자료_일반_현황.패칭_균열
	 * @return
	 */
	@JsonProperty(value="PTCHG_CR")
	public java.lang.String getPTCHG_CR() {
		return this.PTCHG_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_CR,
	 * 집계_자료_일반_현황.패칭_균열
	 * @param ptchgCr
	 */
	public void setPTCHG_CR(java.lang.String ptchgCr) {
		this.PTCHG_CR = ptchgCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_VAL,
	 * 집계_자료_일반_현황.표면손상값
	 * @return
	 */
	@JsonProperty(value="CR_VAL")
	public java.lang.String getCR_VAL() {
		return this.CR_VAL;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CR_VAL,
	 * 집계_자료_일반_현황.표면손상값
	 * @param crVal
	 */
	public void setCR_VAL(java.lang.String crVal) {
		this.CR_VAL = crVal;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_IDX,
	 * 집계_자료_일반_현황.표면손상지수
	 * @return
	 */
	@JsonProperty(value="CR_IDX")
	public java.lang.String getCR_IDX() {
		return this.CR_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CR_IDX,
	 * 집계_자료_일반_현황.표면손상지수
	 * @param crIdx
	 */
	public void setCR_IDX(java.lang.String crIdx) {
		this.CR_IDX = crIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_IDX,
	 * 집계_자료_일반_현황.소성변형지수
	 * @return
	 */
	@JsonProperty(value="RD_IDX")
	public java.lang.String getRD_IDX() {
		return this.RD_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_IDX,
	 * 집계_자료_일반_현황.소성변형지수
	 * @param rdIdx
	 */
	public void setRD_IDX(java.lang.String rdIdx) {
		this.RD_IDX = rdIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RI_IDX,
	 * 집계_자료_일반_현황.평탄성_지수
	 * @return
	 */
	@JsonProperty(value="RI_IDX")
	public java.lang.String getRI_IDX() {
		return this.RI_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RI_IDX,
	 * 집계_자료_일반_현황.평탄성_지수
	 * @param riIdx
	 */
	public void setRI_IDX(java.lang.String riIdx) {
		this.RI_IDX = riIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CNTL_DFECT,
	 * 집계_자료_일반_현황.지배_결함
	 * @return
	 */
	@JsonProperty(value="CNTL_DFECT")
	public java.lang.String getCNTL_DFECT() {
		return this.CNTL_DFECT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CNTL_DFECT,
	 * 집계_자료_일반_현황.지배_결함
	 * @param cntlDfect
	 */
	public void setCNTL_DFECT(java.lang.String cntlDfect) {
		this.CNTL_DFECT = cntlDfect;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_KND,
	 * 집계_자료_일반_현황.조사_종류
	 * @return
	 */
	@JsonProperty(value="SRVY_KND")
	public java.lang.String getSRVY_KND() {
		return this.SRVY_KND;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SRVY_KND,
	 * 집계_자료_일반_현황.조사_종류
	 * @param srvyKnd
	 */
	public void setSRVY_KND(java.lang.String srvyKnd) {
		this.SRVY_KND = srvyKnd;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SRVY_MT,
	 * 집계_자료_일반_현황.조사_월
	 * @return
	 */
	@JsonProperty(value="SRVY_MT")
	public java.lang.String getSRVY_MT() {
		return this.SRVY_MT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SRVY_MT,
	 * 집계_자료_일반_현황.조사_월
	 * @param srvyMt
	 */
	public void setSRVY_MT(java.lang.String srvyMt) {
		this.SRVY_MT = srvyMt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.FRMULA_NO,
	 * 집계_자료_일반_현황.수식_번호
	 * @return
	 */
	@JsonProperty(value="FRMULA_NO")
	public java.lang.String getFRMULA_NO() {
		return this.FRMULA_NO;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.FRMULA_NO,
	 * 집계_자료_일반_현황.수식_번호
	 * @param frmulaNo
	 */
	public void setFRMULA_NO(java.lang.String frmulaNo) {
		this.FRMULA_NO = frmulaNo;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TRNSPORT_QY,
	 * 집계_자료_일반_현황.교통_량
	 * @return
	 */
	@JsonProperty(value="TRNSPORT_QY")
	public java.lang.String getTRNSPORT_QY() {
		return this.TRNSPORT_QY;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TRNSPORT_QY,
	 * 집계_자료_일반_현황.교통_량
	 * @param trnsportQy
	 */
	public void setTRNSPORT_QY(java.lang.String trnsportQy) {
		this.TRNSPORT_QY = trnsportQy;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_GRAD,
	 * 집계_자료_일반_현황.표면손상_등급
	 * @return
	 */
	@JsonProperty(value="CR_GRAD")
	public java.lang.String getCR_GRAD() {
		return this.CR_GRAD;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CR_GRAD,
	 * 집계_자료_일반_현황.표면손상_등급
	 * @param crGrad
	 */
	public void setCR_GRAD(java.lang.String crGrad) {
		this.CR_GRAD = crGrad;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_GRAD,
	 * 집계_자료_일반_현황.소성변형_등급
	 * @return
	 */
	@JsonProperty(value="RD_GRAD")
	public java.lang.String getRD_GRAD() {
		return this.RD_GRAD;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_GRAD,
	 * 집계_자료_일반_현황.소성변형_등급
	 * @param rdGrad
	 */
	public void setRD_GRAD(java.lang.String rdGrad) {
		this.RD_GRAD = rdGrad;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.IRI_GRAD,
	 * 집계_자료_일반_현황.종단평탄성_등급
	 * @return
	 */
	@JsonProperty(value="IRI_GRAD")
	public java.lang.String getIRI_GRAD() {
		return this.IRI_GRAD;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.IRI_GRAD,
	 * 집계_자료_일반_현황.종단평탄성_등급
	 * @param iriGrad
	 */
	public void setIRI_GRAD(java.lang.String iriGrad) {
		this.IRI_GRAD = iriGrad;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.DELETE_AT,
	 * 집계_자료_일반_현황.삭제_여부
	 * @return
	 */
	@JsonProperty(value="DELETE_AT")
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.DELETE_AT,
	 * 집계_자료_일반_현황.삭제_여부
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.USE_AT,
	 * 집계_자료_일반_현황.사용_여부
	 * @return
	 */
	@JsonProperty(value="USE_AT")
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.USE_AT,
	 * 집계_자료_일반_현황.사용_여부
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CRTR_NO,
	 * 집계_자료_일반_현황.생성자_번호
	 * @return
	 */
	@JsonProperty(value="CRTR_NO")
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CRTR_NO,
	 * 집계_자료_일반_현황.생성자_번호
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CREAT_DT,
	 * 집계_자료_일반_현황.생성_일시
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CREAT_DT,
	 * 집계_자료_일반_현황.생성_일시
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.UPDUSR_NO,
	 * 집계_자료_일반_현황.수정자_번호
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO")
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.UPDUSR_NO,
	 * 집계_자료_일반_현황.수정자_번호
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.UPDT_DT,
	 * 집계_자료_일반_현황.수정_일시
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.UPDT_DT,
	 * 집계_자료_일반_현황.수정_일시
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR,
	 * 집계_자료_일반_현황.블럭_균열
	 * @return
	 */
	@JsonProperty(value="BLOCK_CR")
	public java.lang.String getBLOCK_CR() {
		return this.BLOCK_CR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR,
	 * 집계_자료_일반_현황.블럭_균열
	 * @param blockCr
	 */
	public void setBLOCK_CR(java.lang.String blockCr) {
		this.BLOCK_CR = blockCr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_LT,
	 * 집계_자료_일반_현황.균열_길이
	 * @return
	 */
	@JsonProperty(value="CR_LT")
	public java.lang.String getCR_LT() {
		return this.CR_LT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CR_LT,
	 * 집계_자료_일반_현황.균열_길이
	 * @param crLt
	 */
	public void setCR_LT(java.lang.String crLt) {
		this.CR_LT = crLt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.CR_WID,
	 * 집계_자료_일반_현황.균열_폭
	 * @return
	 */
	@JsonProperty(value="CR_WID")
	public java.lang.String getCR_WID() {
		return this.CR_WID;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.CR_WID,
	 * 집계_자료_일반_현황.균열_폭
	 * @param crWid
	 */
	public void setCR_WID(java.lang.String crWid) {
		this.CR_WID = crWid;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.NHPCI,
	 * 집계_자료_일반_현황.NHPCI
	 * @return
	 */
	@JsonProperty(value="NHPCI")
	public java.lang.String getNHPCI() {
		return this.NHPCI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.NHPCI,
	 * 집계_자료_일반_현황.NHPCI
	 * @param nhpci
	 */
	public void setNHPCI(java.lang.String nhpci) {
		this.NHPCI = nhpci;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SPI,
	 * 집계_자료_일반_현황.SPI
	 * @return
	 */
	@JsonProperty(value="SPI")
	public java.lang.String getSPI() {
		return this.SPI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SPI,
	 * 집계_자료_일반_현황.SPI
	 * @param spi
	 */
	public void setSPI(java.lang.String spi) {
		this.SPI = spi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.XCR,
	 * 집계_자료_일반_현황.NHPCI_균열
	 * @return
	 */
	@JsonProperty(value="XCR")
	public java.lang.String getXCR() {
		return this.XCR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.XCR,
	 * 집계_자료_일반_현황.NHPCI_균열
	 * @param xcr
	 */
	public void setXCR(java.lang.String xcr) {
		this.XCR = xcr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RCI,
	 * 집계_자료_일반_현황.표면_조도_지수
	 * @return
	 */
	@JsonProperty(value="RCI")
	public java.lang.String getRCI() {
		return this.RCI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RCI,
	 * 집계_자료_일반_현황.표면_조도_지수
	 * @param rci
	 */
	public void setRCI(java.lang.String rci) {
		this.RCI = rci;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.SCR,
	 * 집계_자료_일반_현황.표면_상태_지수
	 * @return
	 */
	@JsonProperty(value="SCR")
	public java.lang.String getSCR() {
		return this.SCR;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.SCR,
	 * 집계_자료_일반_현황.표면_상태_지수
	 * @param scr
	 */
	public void setSCR(java.lang.String scr) {
		this.SCR = scr;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_LOW,
	 * 집계_자료_일반_현황.거북등균열_LOW
	 * @return
	 */
	@JsonProperty(value="AC_LOW")
	public java.lang.String getAC_LOW() {
		return this.AC_LOW;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.AC_LOW,
	 * 집계_자료_일반_현황.거북등균열_LOW
	 * @param acLow
	 */
	public void setAC_LOW(java.lang.String acLow) {
		this.AC_LOW = acLow;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_MED,
	 * 집계_자료_일반_현황.거북등균열_MED
	 * @return
	 */
	@JsonProperty(value="AC_MED")
	public java.lang.String getAC_MED() {
		return this.AC_MED;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.AC_MED,
	 * 집계_자료_일반_현황.거북등균열_MED
	 * @param acMed
	 */
	public void setAC_MED(java.lang.String acMed) {
		this.AC_MED = acMed;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_HI,
	 * 집계_자료_일반_현황.거북등균열_HI
	 * @return
	 */
	@JsonProperty(value="AC_HI")
	public java.lang.String getAC_HI() {
		return this.AC_HI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.AC_HI,
	 * 집계_자료_일반_현황.거북등균열_HI
	 * @param acHi
	 */
	public void setAC_HI(java.lang.String acHi) {
		this.AC_HI = acHi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_LOW,
	 * 집계_자료_일반_현황.종방향균열_LOW
	 * @return
	 */
	@JsonProperty(value="LC_LOW")
	public java.lang.String getLC_LOW() {
		return this.LC_LOW;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.LC_LOW,
	 * 집계_자료_일반_현황.종방향균열_LOW
	 * @param lcLow
	 */
	public void setLC_LOW(java.lang.String lcLow) {
		this.LC_LOW = lcLow;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_MED,
	 * 집계_자료_일반_현황.종방향균열_MED
	 * @return
	 */
	@JsonProperty(value="LC_MED")
	public java.lang.String getLC_MED() {
		return this.LC_MED;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.LC_MED,
	 * 집계_자료_일반_현황.종방향균열_MED
	 * @param lcMed
	 */
	public void setLC_MED(java.lang.String lcMed) {
		this.LC_MED = lcMed;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_HI,
	 * 집계_자료_일반_현황.종방향균열_HI
	 * @return
	 */
	@JsonProperty(value="LC_HI")
	public java.lang.String getLC_HI() {
		return this.LC_HI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.LC_HI,
	 * 집계_자료_일반_현황.종방향균열_HI
	 * @param lcHi
	 */
	public void setLC_HI(java.lang.String lcHi) {
		this.LC_HI = lcHi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_LOW,
	 * 집계_자료_일반_현황.횡방향균열_LOW
	 * @return
	 */
	@JsonProperty(value="TC_LOW")
	public java.lang.String getTC_LOW() {
		return this.TC_LOW;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TC_LOW,
	 * 집계_자료_일반_현황.횡방향균열_LOW
	 * @param tcLow
	 */
	public void setTC_LOW(java.lang.String tcLow) {
		this.TC_LOW = tcLow;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_MED,
	 * 집계_자료_일반_현황.횡방향균열_MED
	 * @return
	 */
	@JsonProperty(value="TC_MED")
	public java.lang.String getTC_MED() {
		return this.TC_MED;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TC_MED,
	 * 집계_자료_일반_현황.횡방향균열_MED
	 * @param tcMed
	 */
	public void setTC_MED(java.lang.String tcMed) {
		this.TC_MED = tcMed;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_HI,
	 * 집계_자료_일반_현황.횡방향균열_HI
	 * @return
	 */
	@JsonProperty(value="TC_HI")
	public java.lang.String getTC_HI() {
		return this.TC_HI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TC_HI,
	 * 집계_자료_일반_현황.횡방향균열_HI
	 * @param tcHi
	 */
	public void setTC_HI(java.lang.String tcHi) {
		this.TC_HI = tcHi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.AC_IDX,
	 * 집계_자료_일반_현황.거북등균열_지수
	 * @return
	 */
	@JsonProperty(value="AC_IDX")
	public java.lang.String getAC_IDX() {
		return this.AC_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.AC_IDX,
	 * 집계_자료_일반_현황.거북등균열_지수
	 * @param acIdx
	 */
	public void setAC_IDX(java.lang.String acIdx) {
		this.AC_IDX = acIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.LC_IDX,
	 * 집계_자료_일반_현황.종방향균열_지수
	 * @return
	 */
	@JsonProperty(value="LC_IDX")
	public java.lang.String getLC_IDX() {
		return this.LC_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.LC_IDX,
	 * 집계_자료_일반_현황.종방향균열_지수
	 * @param lcIdx
	 */
	public void setLC_IDX(java.lang.String lcIdx) {
		this.LC_IDX = lcIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.TC_IDX,
	 * 집계_자료_일반_현황.횡방향균열_지수
	 * @return
	 */
	@JsonProperty(value="TC_IDX")
	public java.lang.String getTC_IDX() {
		return this.TC_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.TC_IDX,
	 * 집계_자료_일반_현황.횡방향균열_지수
	 * @param tcIdx
	 */
	public void setTC_IDX(java.lang.String tcIdx) {
		this.TC_IDX = tcIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_IDX,
	 * 집계_자료_일반_현황.패칭_지수
	 * @return
	 */
	@JsonProperty(value="PTCHG_IDX")
	public java.lang.String getPTCHG_IDX() {
		return this.PTCHG_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.PTCHG_IDX,
	 * 집계_자료_일반_현황.패칭_지수
	 * @param ptchgIdx
	 */
	public void setPTCHG_IDX(java.lang.String ptchgIdx) {
		this.PTCHG_IDX = ptchgIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_LOW,
	 * 집계_자료_일반_현황.소성변형_LOW
	 * @return
	 */
	@JsonProperty(value="RD_LOW")
	public java.lang.String getRD_LOW() {
		return this.RD_LOW;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_LOW,
	 * 집계_자료_일반_현황.소성변형_LOW
	 * @param rdLow
	 */
	public void setRD_LOW(java.lang.String rdLow) {
		this.RD_LOW = rdLow;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_MED,
	 * 집계_자료_일반_현황.소성변형_MED
	 * @return
	 */
	@JsonProperty(value="RD_MED")
	public java.lang.String getRD_MED() {
		return this.RD_MED;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_MED,
	 * 집계_자료_일반_현황.소성변형_MED
	 * @param rdMed
	 */
	public void setRD_MED(java.lang.String rdMed) {
		this.RD_MED = rdMed;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.RD_HI,
	 * 집계_자료_일반_현황.소성변형_HI
	 * @return
	 */
	@JsonProperty(value="RD_HI")
	public java.lang.String getRD_HI() {
		return this.RD_HI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.RD_HI,
	 * 집계_자료_일반_현황.소성변형_HI
	 * @param rdHi
	 */
	public void setRD_HI(java.lang.String rdHi) {
		this.RD_HI = rdHi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_LOW,
	 * 집계_자료_일반_현황.블럭_균열_LOW
	 * @return
	 */
	@JsonProperty(value="BLOCK_CR_LOW")
	public java.lang.String getBLOCK_CR_LOW() {
		return this.BLOCK_CR_LOW;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_LOW,
	 * 집계_자료_일반_현황.블럭_균열_LOW
	 * @param blockCrLow
	 */
	public void setBLOCK_CR_LOW(java.lang.String blockCrLow) {
		this.BLOCK_CR_LOW = blockCrLow;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_MED,
	 * 집계_자료_일반_현황.블럭_균열_MED
	 * @return
	 */
	@JsonProperty(value="BLOCK_CR_MED")
	public java.lang.String getBLOCK_CR_MED() {
		return this.BLOCK_CR_MED;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_MED,
	 * 집계_자료_일반_현황.블럭_균열_MED
	 * @param blockCrMed
	 */
	public void setBLOCK_CR_MED(java.lang.String blockCrMed) {
		this.BLOCK_CR_MED = blockCrMed;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_HI,
	 * 집계_자료_일반_현황.블럭_균열_HI
	 * @return
	 */
	@JsonProperty(value="BLOCK_CR_HI")
	public java.lang.String getBLOCK_CR_HI() {
		return this.BLOCK_CR_HI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.BLOCK_CR_HI,
	 * 집계_자료_일반_현황.블럭_균열_HI
	 * @param blockCrHi
	 */
	public void setBLOCK_CR_HI(java.lang.String blockCrHi) {
		this.BLOCK_CR_HI = blockCrHi;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_IDX,
	 * 집계_자료_일반_현황.포트홀_지수
	 * @return
	 */
	@JsonProperty(value="POTHOLE_IDX")
	public java.lang.String getPOTHOLE_IDX() {
		return this.POTHOLE_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.POTHOLE_IDX,
	 * 집계_자료_일반_현황.포트홀_지수
	 * @param potholeIdx
	 */
	public void setPOTHOLE_IDX(java.lang.String potholeIdx) {
		this.POTHOLE_IDX = potholeIdx;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_CLMT,
	 * 집계_자료_일반_현황.파손_원인_기후
	 * @return
	 */
	@JsonProperty(value="DMG_CUZ_CLMT")
	public java.lang.String getDMG_CUZ_CLMT() {
		return this.DMG_CUZ_CLMT;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_CLMT,
	 * 집계_자료_일반_현황.파손_원인_기후
	 * @param dmgCuzClmt
	 */
	public void setDMG_CUZ_CLMT(java.lang.String dmgCuzClmt) {
		this.DMG_CUZ_CLMT = dmgCuzClmt;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_VMTC,
	 * 집계_자료_일반_현황.파손_원인_교통량
	 * @return
	 */
	@JsonProperty(value="DMG_CUZ_VMTC")
	public java.lang.String getDMG_CUZ_VMTC() {
		return this.DMG_CUZ_VMTC;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_VMTC,
	 * 집계_자료_일반_현황.파손_원인_교통량
	 * @param dmgCuzVmtc
	 */
	public void setDMG_CUZ_VMTC(java.lang.String dmgCuzVmtc) {
		this.DMG_CUZ_VMTC = dmgCuzVmtc;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_ETC,
	 * 집계_자료_일반_현황.파손_원인_기타
	 * @return
	 */
	@JsonProperty(value="DMG_CUZ_ETC")
	public java.lang.String getDMG_CUZ_ETC() {
		return this.DMG_CUZ_ETC;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.DMG_CUZ_ETC,
	 * 집계_자료_일반_현황.파손_원인_기타
	 * @param dmgCuzEtc
	 */
	public void setDMG_CUZ_ETC(java.lang.String dmgCuzEtc) {
		this.DMG_CUZ_ETC = dmgCuzEtc;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.GPCI,
	 * 집계_자료_일반_현황.GPCI
	 * @return
	 */
	@JsonProperty(value="GPCI")
	public java.lang.String getGPCI() {
		return this.GPCI;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.GPCI,
	 * 집계_자료_일반_현황.GPCI
	 * @param GPCI
	 */
	public void setGPCI(java.lang.String GPCI) {
		this.GPCI = GPCI;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.PC_GRAD,
	 * 집계_자료_일반_현황.포장상태등급
	 * @return
	 */
	@JsonProperty(value="PC_GRAD")
	public java.lang.String getPC_GRAD() {
		return this.PC_GRAD;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.PC_GRAD,
	 * 집계_자료_일반_현황.포장상태등급
	 * @param pcGrad
	 */
	public void setPC_GRAD(java.lang.String pcGrad) {
		this.PC_GRAD = pcGrad;
	}

	/**
	 * TN_SM_DTA_GNL_STTUS.BC_IDX,
	 * 집계_자료_일반_현황.블럭균열_지수
	 * @return
	 */
	@JsonProperty(value="BC_IDX")
	public java.lang.String getBC_IDX() {
		return this.BC_IDX;
	}

	 /**
	 * TN_SM_DTA_GNL_STTUS.BC_IDX,
	 * 집계_자료_일반_현황.블럭균열_지수
	 * @param bcIdx
	 */
	public void setBC_IDX(java.lang.String bcIdx) {
		this.BC_IDX = bcIdx;
	}

	@JsonProperty(value="ROAD_NM")
	public java.lang.String getROAD_NM() {
		return ROAD_NM;
	}

	public void setROAD_NM(java.lang.String rOAD_NM) {
		ROAD_NM = rOAD_NM;
	}

	@JsonProperty(value="ROAD_GRAD")
	public java.lang.String getROAD_GRAD() {
		return ROAD_GRAD;
	}

	public void setROAD_GRAD(java.lang.String rOAD_GRAD) {
		ROAD_GRAD = rOAD_GRAD;
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

	@JsonProperty(value="CELL_ID")
	public java.lang.String getCELL_ID() {
		return CELL_ID;
	}

	public void setCELL_ID(java.lang.String cELL_ID) {
		CELL_ID = cELL_ID;
	}

	@JsonProperty(value="ROAD_NO_VAL")
	public java.lang.String getROAD_NO_VAL() {
		return ROAD_NO_VAL;
	}

	public void setROAD_NO_VAL(java.lang.String rOAD_NO_VAL) {
		ROAD_NO_VAL = rOAD_NO_VAL;
	}

	@JsonProperty(value="CNTL_DFECT_NM")
	public java.lang.String getCNTL_DFECT_NM() {
		return CNTL_DFECT_NM;
	}

	public void setCNTL_DFECT_NM(java.lang.String cNTL_DFECT_NM) {
		CNTL_DFECT_NM = cNTL_DFECT_NM;
	}

	@JsonProperty(value="AC_RDUCT_RATE")
	public java.lang.String getAC_RDUCT_RATE() {
		return AC_RDUCT_RATE;
	}

	public void setAC_RDUCT_RATE(java.lang.String aC_RDUCT_RATE) {
		AC_RDUCT_RATE = aC_RDUCT_RATE;
	}

	@JsonProperty(value="BC_RDUCT_RATE")
	public java.lang.String getBC_RDUCT_RATE() {
		return BC_RDUCT_RATE;
	}

	public void setBC_RDUCT_RATE(java.lang.String bC_RDUCT_RATE) {
		BC_RDUCT_RATE = bC_RDUCT_RATE;
	}

	@JsonProperty(value="LC_RDUCT_RATE")
	public java.lang.String getLC_RDUCT_RATE() {
		return LC_RDUCT_RATE;
	}

	public void setLC_RDUCT_RATE(java.lang.String lC_RDUCT_RATE) {
		LC_RDUCT_RATE = lC_RDUCT_RATE;
	}

	@JsonProperty(value="TC_RDUCT_RATE")
	public java.lang.String getTC_RDUCT_RATE() {
		return TC_RDUCT_RATE;
	}

	public void setTC_RDUCT_RATE(java.lang.String tC_RDUCT_RATE) {
		TC_RDUCT_RATE = tC_RDUCT_RATE;
	}

	@JsonProperty(value="PTCHG_RDUCT_RATE")
	public java.lang.String getPTCHG_RDUCT_RATE() {
		return PTCHG_RDUCT_RATE;
	}

	public void setPTCHG_RDUCT_RATE(java.lang.String pTCHG_RDUCT_RATE) {
		PTCHG_RDUCT_RATE = pTCHG_RDUCT_RATE;
	}

	@JsonProperty(value="POT_RDUCT_RATE")
	public java.lang.String getPOT_RDUCT_RATE() {
		return POT_RDUCT_RATE;
	}

	public void setPOT_RDUCT_RATE(java.lang.String pOT_RDUCT_RATE) {
		POT_RDUCT_RATE = pOT_RDUCT_RATE;
	}

	@JsonProperty(value="RD_RDUCT_RATE")
	public java.lang.String getRD_RDUCT_RATE() {
		return RD_RDUCT_RATE;
	}

	public void setRD_RDUCT_RATE(java.lang.String rD_RDUCT_RATE) {
		RD_RDUCT_RATE = rD_RDUCT_RATE;
	}

	@JsonProperty(value="RCI_RDUCT_RATE")
	public java.lang.String getRCI_RDUCT_RATE() {
		return RCI_RDUCT_RATE;
	}

	public void setRCI_RDUCT_RATE(java.lang.String rCI_RDUCT_RATE) {
		RCI_RDUCT_RATE = rCI_RDUCT_RATE;
	}


}
