
package kr.go.gg.gpms.rpairtrget.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;
/**
 * 보수_대상_항목
 *
 * @Class Name : RpairTrgetVO.java
 * @Description : RpairTrget VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  	RpairTrgetDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class RpairTrgetVO extends RpairTrgetSlctnVO {

	public RpairTrgetVO() {
		super();
	}
	
	/** 
	 * TN_RPAIR_TRGET.TRGET_ITEM_NO, 
	 * 보수_대상_항목.대상_항목_번호
	 */
	@XmlElement
	private String TRGET_ITEM_NO;
	
	

	/** 
	 * TN_RPAIR_TRGET_GROUP.SLCTN_ORDR, 
	 * 보수_대상_항목_그룹.선정_순서
	 */
	@XmlElement
	private String SLCTN_ORDR;
	
	
	/** 
	 * TN_RPAIR_TRGET.TRGET_SLCTN_NO, 
	 * 보수_대상_항목.대상_선정_번호
	 */
	@XmlElement
	private String TRGET_SLCTN_NO;

	/** 
	 * TN_RPAIR_TRGET.SLCTN_STEP, 
	 * 보수_대상_항목.선정_단계
	 */
	@XmlElement
	private String SLCTN_STEP;

	/** 
	 * TN_RPAIR_TRGET.ITEM_SLCTN_STTUS, 
	 * 보수_대상_항목.항목_선정_상태
	 */
	@XmlElement
	private java.lang.String ITEM_SLCTN_STTUS;
	/** 
	 * TN_RPAIR_TRGET.ITEM_SLCTN_STTUS, 
	 * 보수_대상_항목.항목_선정_상태_명
	 */
	@XmlElement
	private java.lang.String ITEM_SLCTN_STTUS_NM;
	
	@XmlElement
	private java.lang.String CELL_TYPE_NM;
	
	@XmlElement
	private java.lang.String MSRC_NM;
	
	@XmlElement
	private java.lang.String MSRC_CL_NM;
	/** 
	 * TN_RPAIR_TRGET.ROUTE_CODE, 
	 * 보수_대상_항목.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_RPAIR_TRGET.DIRECT_CODE, 
	 * 보수_대상_항목.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TN_RPAIR_TRGET.TRACK, 
	 * 보수_대상_항목.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_RPAIR_TRGET.STRTPT, 
	 * 보수_대상_항목.시점
	 */
	@XmlElement
	private String STRTPT;

	/** 
	 * TN_RPAIR_TRGET.ENDPT, 
	 * 보수_대상_항목.종점
	 */
	@XmlElement
	private String ENDPT;

	/** 
	 * TN_RPAIR_TRGET.CELL_TYPE, 
	 * 보수_대상_항목.섹션_구분
	 */
	@XmlElement
	private java.lang.String CELL_TYPE;

	/** 
	 * TN_RPAIR_TRGET.DEPT_CODE, 
	 * 보수_대상_항목.부서코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;
 
	
	
	/** 
	 * TN_RPAIR_TRGET.DEPT_CODE, 
	 * 보수_대상_항목.부서코드
	 */
	@XmlElement
	private java.lang.String DEPT_NM;
	
	/** 
	 * TN_RPAIR_TRGET.SRVY_MT, 
	 * 보수_대상_항목.조사_월
	 */
	@XmlElement
	private java.lang.String SRVY_MT;

	/** 
	 * TN_RPAIR_TRGET.SRVY_YEAR, 
	 * 보수_대상_항목.조사_년도
	 */
	@XmlElement
	private java.lang.String SRVY_YEAR;

	/** 
	 * TN_RPAIR_TRGET.GPCI, 
	 * 보수_대상_항목.GPCI
	 */
	@XmlElement
	private Double GPCI;

	/** 
	 * TN_RPAIR_TRGET.PC_GRAD, 
	 * 보수_대상_항목.포장상태등급
	 */
	@XmlElement
	private String PC_GRAD;
	/** 
	 * TN_RPAIR_TRGET_GROUP.DMG_VAL, 
	 * 보수_대상_항목.파손도_값
	 */
	@XmlElement
	private Double DMG_VAL;
	/** 
	 * TN_RPAIR_TRGET.CALC_YEAR, 
	 * 보수_대상_항목.산정_년도
	 */
	@XmlElement
	private java.lang.String CALC_YEAR;

	/** 
	 * TN_RPAIR_TRGET.CALC_MT, 
	 * 보수_대상_항목.산정_월
	 */
	@XmlElement
	private java.lang.String CALC_MT;

	/** 
	 * TN_RPAIR_TRGET.CALC_GPCI, 
	 * 보수_대상_항목.산정_GPCI
	 */
	@XmlElement
	private Double CALC_GPCI;

	/** 
	 * TN_RPAIR_TRGET.CALC_PC_GRAD, 
	 * 보수_대상_항목.산정_포장상태등급
	 */
	@XmlElement
	private String CALC_PC_GRAD;

	/** 
	 * TN_RPAIR_TRGET.CNTRWK_YEAR, 
	 * 보수_대상_항목.공사_년도
	 */
	@XmlElement
	private java.lang.String CNTRWK_YEAR;

	/** 
	 * TN_RPAIR_TRGET.TRNSPORT_QY, 
	 * 보수_대상_항목.교통량
	 */
	@XmlElement
	private String TRNSPORT_QY;
	
	/** 
	 * TN_RPAIR_TRGET.VMTC_GRAD, 
	 * 보수_대상_항목.교통량등급
	 */
	@XmlElement
	private String VMTC_GRAD;
	
	/** 
	 * TN_RPAIR_TRGET.VMTC_GRAD_NM, 
	 * 보수_대상_항목.교통량등급 명
	 */
	@XmlElement
	private String VMTC_GRAD_NM;
	
	/** 
	 * TN_RPAIR_TRGET.ROAD_GRAD, 
	 * 보수_대상_항목.도로등급
	 */
	@XmlElement
	private String ROAD_GRAD;
	
	/** 
	 * TN_RPAIR_TRGET.ROAD_GRAD_NM, 
	 * 보수_대상_항목.도로등급 명
	 */
	@XmlElement
	private String ROAD_GRAD_NM;
	
	
	/** 
	 * TN_RPAIR_TRGET.ADM_CODE, 
	 * 보수_대상_항목.행정구역코드
	 */
	@XmlElement
	private String ADM_CODE;
	
	/** 
	 * TN_RPAIR_TRGET.ADM_NM, 
	 * 보수_대상_항목.행정구역코드 명
	 */
	@XmlElement
	private String ADM_NM;

	/** 
	 * TN_RPAIR_TRGET.LEN, 
	 * 보수_대상_항목.연장
	 */
	@XmlElement
	private String LEN;
	
	@XmlElement
	private String KILLO_LEN;

	/** 
	 * TN_RPAIR_TRGET.AR, 
	 * 보수_대상_항목.면적
	 */
	@XmlElement
	private String AR;

	/** 
	 * TN_RPAIR_TRGET.RPAIR_MTHD_CODE, 
	 * 보수_대상_항목.보수_공법_코드
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_CODE;

	/** 
	 * TN_RPAIR_TRGET.MSR_DM_CODE, 
	 * 보수_대상_항목.공법선정비율_결정방식_코드
	 */
	@XmlElement
	private java.lang.String MSR_DM_CODE;
	
	/** 
	 * TN_RPAIR_TRGET.THRHLD, 
	 * 보수_대상_항목.임계값
	 */
	@XmlElement
	private java.lang.String THRHLD;
	
	/**
	 * 공법 비용
	 */
	@XmlElement
	private String RPAIR_FEE;
	
	/** 
	 * TN_RPAIR_TRGET.AMOUNT_CALC, 
	 * 보수_대상_항목.금액_산정
	 */
	@XmlElement
	private Long AMOUNT_CALC;
	
	/** 
	 * TN_RPAIR_TRGET.BUDGET_ASIGN, 
	 * 보수_대상_항목.예산_배정
	 */
	@XmlElement
	private String BUDGET_ASIGN;
	
	
	
	@XmlElement
	private String FIX_AMOUNT_CALC;
	
	@XmlElement
	private Long TOTAL_AMOUNT_CALC;	
	
	@XmlElement
	private String TOTAL_FIX_AMOUNT_CALC;
	
	@XmlElement
	private String FIX_BUDGET_ASIGN;
	
	/** 
	 * TN_RPAIR_TRGET.TOTAL_BUDGET_ASIGN, 
	 * 보수_대상_항목.전체_예산_배정
	 */
	@XmlElement
	private String TOTAL_BUDGET_ASIGN;
	
	@XmlElement
	private String TOTAL_FIX_BUDGET_ASIGN;
	
	
	@XmlElement
	private String TOTAL_TMPR_BUDGET_ASIGN;
	
	@XmlElement
	private String TOTAL_TMPR_FIX_BUDGET_ASIGN;
	
	@XmlElement
	private String TMPR_FIX_BUDGET_ASIGN;
	@XmlElement
	private String CELL_IDS;
	/** 
	 * TN_RPAIR_TRGET.FIXING_AT, 
	 * 보수_대상_항목.고정_여부
	 */
	@XmlElement
	private java.lang.String FIXING_AT;

	/** 
	 * TN_RPAIR_TRGET.BUDGET_CECK, 
	 * 보수_대상_항목.예산_체크
	 */
	@XmlElement
	private java.lang.String BUDGET_CECK;

	/** 
	 * TN_RPAIR_TRGET.ACCMLT_CALC, 
	 * 보수_대상_항목.누적_산정
	 */
	@XmlElement
	private String ACCMLT_CALC;

	/** 
	 * TN_RPAIR_TRGET.SLCTN_AT, 
	 * 보수_대상_항목.선정_여부
	 */
	@XmlElement
	private java.lang.String SLCTN_AT;
	
	@XmlElement
	private java.lang.String TMPR_SLCTN_AT;
	
	
	/** 
	 * TN_RPAIR_TRGET.SLCTN_DT, 
	 * 보수_대상_항목.선정_일시
	 */
	@XmlElement
	private java.util.Date SLCTN_DT;

	/** 
	 * TN_RPAIR_TRGET.DELETE_AT, 
	 * 보수_대상_항목.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_RPAIR_TRGET.USE_AT, 
	 * 보수_대상_항목.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_RPAIR_TRGET.CRTR_NO, 
	 * 보수_대상_항목.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_RPAIR_TRGET.CREAT_DT, 
	 * 보수_대상_항목.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_RPAIR_TRGET.UPDUSR_NO, 
	 * 보수_대상_항목.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TN_RPAIR_TRGET.UPDT_DT, 
	 * 보수_대상_항목.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TN_RPAIR_TRGET.TRGET_ITEM_NO, 
	 * 보수_대상_항목.대상_항목_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="TRGET_ITEM_NO") 
	public String getTRGET_ITEM_NO() {
		return this.TRGET_ITEM_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET.TRGET_ITEM_NO, 
	 * 보수_대상_항목.대상_항목_번호 값설정
	 * @param trgetItemNo
	 */
	public void setTRGET_ITEM_NO(String trgetItemNo) {
		this.TRGET_ITEM_NO = trgetItemNo;
	}

	/**
	 * TN_RPAIR_TRGET.TRGET_SLCTN_NO, 
	 * 보수_대상_항목.대상_선정_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="TRGET_SLCTN_NO") 
	public String getTRGET_SLCTN_NO() {
		return this.TRGET_SLCTN_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET.TRGET_SLCTN_NO, 
	 * 보수_대상_항목.대상_선정_번호 값설정
	 * @param trgetSlctnNo
	 */
	public void setTRGET_SLCTN_NO(String trgetSlctnNo) {
		this.TRGET_SLCTN_NO = trgetSlctnNo;
	}

	/**
	 * TN_RPAIR_TRGET.SLCTN_STEP, 
	 * 보수_대상_항목.선정_단계 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_STEP") 
	public String getSLCTN_STEP() {
		return this.SLCTN_STEP;
	}
 
	 /**
	 * TN_RPAIR_TRGET.SLCTN_STEP, 
	 * 보수_대상_항목.선정_단계 값설정
	 * @param slctnStep
	 */
	public void setSLCTN_STEP(String slctnStep) {
		this.SLCTN_STEP = slctnStep;
	}

	/**
	 * TN_RPAIR_TRGET.ITEM_SLCTN_STTUS, 
	 * 보수_대상_항목.항목_선정_상태 값읽기
	 * @return
	 */
	@JsonProperty(value="ITEM_SLCTN_STTUS") 
	public java.lang.String getITEM_SLCTN_STTUS() {
		return this.ITEM_SLCTN_STTUS;
	}
 
	 /**
	 * TN_RPAIR_TRGET.ITEM_SLCTN_STTUS, 
	 * 보수_대상_항목.항목_선정_상태 값설정
	 * @param itemSlctnSttus
	 */
	public void setITEM_SLCTN_STTUS(java.lang.String itemSlctnSttus) {
		this.ITEM_SLCTN_STTUS = itemSlctnSttus;
	}

	/**
	 * TN_RPAIR_TRGET.ROUTE_CODE, 
	 * 보수_대상_항목.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_RPAIR_TRGET.ROUTE_CODE, 
	 * 보수_대상_항목.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_RPAIR_TRGET.DIRECT_CODE, 
	 * 보수_대상_항목.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_RPAIR_TRGET.DIRECT_CODE, 
	 * 보수_대상_항목.행선_코드 값설정
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
	}

	/**
	 * TN_RPAIR_TRGET.TRACK, 
	 * 보수_대상_항목.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_RPAIR_TRGET.TRACK, 
	 * 보수_대상_항목.차로 값설정
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * TN_RPAIR_TRGET.STRTPT, 
	 * 보수_대상_항목.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.STRTPT, 
	 * 보수_대상_항목.시점 값설정
	 * @param strtpt
	 */
	public void setSTRTPT(String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * TN_RPAIR_TRGET.ENDPT, 
	 * 보수_대상_항목.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.ENDPT, 
	 * 보수_대상_항목.종점 값설정
	 * @param endpt
	 */
	public void setENDPT(String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * TN_RPAIR_TRGET.CELL_TYPE, 
	 * 보수_대상_항목.섹션_구분 값읽기
	 * @return
	 */
	@JsonProperty(value="CELL_TYPE") 
	public java.lang.String getCELL_TYPE() {
		return this.CELL_TYPE;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CELL_TYPE, 
	 * 보수_대상_항목.섹션_구분 값설정
	 * @param cellType
	 */
	public void setCELL_TYPE(java.lang.String cellType) {
		this.CELL_TYPE = cellType;
	}

	/**
	 * TN_RPAIR_TRGET.SRVY_MT, 
	 * 보수_대상_항목.조사_월 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_MT") 
	public java.lang.String getSRVY_MT() {
		return this.SRVY_MT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.SRVY_MT, 
	 * 보수_대상_항목.조사_월 값설정
	 * @param srvyMt
	 */
	public void setSRVY_MT(java.lang.String srvyMt) {
		this.SRVY_MT = srvyMt;
	}

	/**
	 * TN_RPAIR_TRGET.SRVY_YEAR, 
	 * 보수_대상_항목.조사_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="SRVY_YEAR") 
	public java.lang.String getSRVY_YEAR() {
		return this.SRVY_YEAR;
	}
 
	 /**
	 * TN_RPAIR_TRGET.SRVY_YEAR, 
	 * 보수_대상_항목.조사_년도 값설정
	 * @param srvyYear
	 */
	public void setSRVY_YEAR(java.lang.String srvyYear) {
		this.SRVY_YEAR = srvyYear;
	}

	/**
	 * TN_RPAIR_TRGET.GPCI, 
	 * 보수_대상_항목.GPCI 값읽기
	 * @return
	 */
	@JsonProperty(value="GPCI") 
	public Double getGPCI() {
		return this.GPCI;
	}
 
	 /**
	 * TN_RPAIR_TRGET.GPCI, 
	 * 보수_대상_항목.GPCI 값설정
	 * @param GPCI
	 */
	public void setGPCI(Double GPCI) {
		this.GPCI = GPCI;
	}

	/**
	 * TN_RPAIR_TRGET.PC_GRAD, 
	 * 보수_대상_항목.포장상태등급 값읽기
	 * @return
	 */
	@JsonProperty(value="PC_GRAD") 
	public String getPC_GRAD() {
		return this.PC_GRAD;
	}
 
	 /**
	 * TN_RPAIR_TRGET.PC_GRAD, 
	 * 보수_대상_항목.포장상태등급 값설정
	 * @param pcGrad
	 */
	public void setPC_GRAD(String pcGrad) {
		this.PC_GRAD = pcGrad;
	}

	/**
	 * TN_RPAIR_TRGET.CALC_YEAR, 
	 * 보수_대상_항목.산정_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="CALC_YEAR") 
	public java.lang.String getCALC_YEAR() {
		return this.CALC_YEAR;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CALC_YEAR, 
	 * 보수_대상_항목.산정_년도 값설정
	 * @param calcYear
	 */
	public void setCALC_YEAR(java.lang.String calcYear) {
		this.CALC_YEAR = calcYear;
	}

	/**
	 * TN_RPAIR_TRGET.CALC_MT, 
	 * 보수_대상_항목.산정_월 값읽기
	 * @return
	 */
	@JsonProperty(value="CALC_MT") 
	public java.lang.String getCALC_MT() {
		return this.CALC_MT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CALC_MT, 
	 * 보수_대상_항목.산정_월 값설정
	 * @param calcMt
	 */
	public void setCALC_MT(java.lang.String calcMt) {
		this.CALC_MT = calcMt;
	}

	/**
	 * TN_RPAIR_TRGET.CALC_GPCI, 
	 * 보수_대상_항목.산정_GPCI 값읽기
	 * @return
	 */
	@JsonProperty(value="CALC_GPCI") 
	public Double getCALC_GPCI() {
		return this.CALC_GPCI;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CALC_GPCI, 
	 * 보수_대상_항목.산정_GPCI 값설정
	 * @param calcGPCI
	 */
	public void setCALC_GPCI(Double calcGPCI) {
		this.CALC_GPCI = calcGPCI;
	}

	/**
	 * TN_RPAIR_TRGET.CALC_PC_GRAD, 
	 * 보수_대상_항목.산정_포장상태등급 값읽기
	 * @return
	 */
	@JsonProperty(value="CALC_PC_GRAD") 
	public String getCALC_PC_GRAD() {
		return this.CALC_PC_GRAD;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CALC_PC_GRAD, 
	 * 보수_대상_항목.산정_포장상태등급 값설정
	 * @param calcPcGrad
	 */
	public void setCALC_PC_GRAD(String calcPcGrad) {
		this.CALC_PC_GRAD = calcPcGrad;
	}

	/**
	 * TN_RPAIR_TRGET.CNTRWK_YEAR, 
	 * 보수_대상_항목.공사_년도 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_YEAR") 
	public java.lang.String getCNTRWK_YEAR() {
		return this.CNTRWK_YEAR;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CNTRWK_YEAR, 
	 * 보수_대상_항목.공사_년도 값설정
	 * @param cntrwkYear
	 */
	public void setCNTRWK_YEAR(java.lang.String cntrwkYear) {
		this.CNTRWK_YEAR = cntrwkYear;
	}

	/**
	 * TN_RPAIR_TRGET.TRNSPORT_QY, 
	 * 보수_대상_항목.교통량 값읽기
	 * @return
	 */
	@JsonProperty(value="TRNSPORT_QY") 
	public String getTRNSPORT_QY() {
		return this.TRNSPORT_QY;
	}
 
	 /**
	 * TN_RPAIR_TRGET.TRNSPORT_QY, 
	 * 보수_대상_항목.교통량 값설정
	 * @param vmtc
	 */
	public void setTRNSPORT_QY(String vmtc) {
		this.TRNSPORT_QY = vmtc;
	}

	/**
	 * TN_RPAIR_TRGET.LEN, 
	 * 보수_대상_항목.연장 값읽기
	 * @return
	 */
	@JsonProperty(value="LEN") 
	public String getLEN() {
		return this.LEN;
	}
 
	 /**
	 * TN_RPAIR_TRGET.LEN, 
	 * 보수_대상_항목.연장 값설정
	 * @param len
	 */
	public void setLEN(String len) {
		this.LEN = len;
	}

	/**
	 * TN_RPAIR_TRGET.AR, 
	 * 보수_대상_항목.면적 값읽기
	 * @return
	 */
	@JsonProperty(value="AR") 
	public String getAR() {
		return this.AR;
	}
 
	 /**
	 * TN_RPAIR_TRGET.AR, 
	 * 보수_대상_항목.면적 값설정
	 * @param ar
	 */
	public void setAR(String ar) {
		this.AR = ar;
	}

	/**
	 * TN_RPAIR_TRGET.RPAIR_MTHD_CODE, 
	 * 보수_대상_항목.보수_공법_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MTHD_CODE") 
	public java.lang.String getRPAIR_MTHD_CODE() {
		return this.RPAIR_MTHD_CODE;
	}
 
	 /**
	 * TN_RPAIR_TRGET.RPAIR_MTHD_CODE, 
	 * 보수_대상_항목.보수_공법_코드 값설정
	 * @param rpairMthdCode
	 */
	public void setRPAIR_MTHD_CODE(java.lang.String rpairMthdCode) {
		this.RPAIR_MTHD_CODE = rpairMthdCode;
	}

	/**
	 * TN_RPAIR_TRGET.AMOUNT_CALC, 
	 * 보수_대상_항목.금액_산정 값읽기
	 * @return
	 */
	@JsonProperty(value="AMOUNT_CALC") 
	public Long getAMOUNT_CALC() {
		return this.AMOUNT_CALC;
	}
 
	 /**
	 * TN_RPAIR_TRGET.AMOUNT_CALC, 
	 * 보수_대상_항목.금액_산정 값설정
	 * @param amountCalc
	 */
	public void setAMOUNT_CALC(Long amountCalc) {
		this.AMOUNT_CALC = amountCalc;
	}

	/**
	 * TN_RPAIR_TRGET.BUDGET_ASIGN, 
	 * 보수_대상_항목.예산_배정 값읽기
	 * @return
	 */
	@JsonProperty(value="BUDGET_ASIGN") 
	public String getBUDGET_ASIGN() {
		return this.BUDGET_ASIGN;
	}
 
	 /**
	 * TN_RPAIR_TRGET.BUDGET_ASIGN, 
	 * 보수_대상_항목.예산_배정 값설정
	 * @param budgetAsign
	 */
	public void setBUDGET_ASIGN(String budgetAsign) {
		this.BUDGET_ASIGN = budgetAsign;
	}

	/**
	 * TN_RPAIR_TRGET.FIXING_AT, 
	 * 보수_대상_항목.고정_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="FIXING_AT") 
	public java.lang.String getFIXING_AT() {
		return this.FIXING_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.FIXING_AT, 
	 * 보수_대상_항목.고정_여부 값설정
	 * @param fixingAt
	 */
	public void setFIXING_AT(java.lang.String fixingAt) {
		this.FIXING_AT = fixingAt;
	}

	/**
	 * TN_RPAIR_TRGET.BUDGET_CECK, 
	 * 보수_대상_항목.예산_체크 값읽기
	 * @return
	 */
	@JsonProperty(value="BUDGET_CECK") 
	public java.lang.String getBUDGET_CECK() {
		return this.BUDGET_CECK;
	}
 
	 /**
	 * TN_RPAIR_TRGET.BUDGET_CECK, 
	 * 보수_대상_항목.예산_체크 값설정
	 * @param budgetCeck
	 */
	public void setBUDGET_CECK(java.lang.String budgetCeck) {
		this.BUDGET_CECK = budgetCeck;
	}

	/**
	 * TN_RPAIR_TRGET.ACCMLT_CALC, 
	 * 보수_대상_항목.누적_산정 값읽기
	 * @return
	 */
	@JsonProperty(value="ACCMLT_CALC") 
	public String getACCMLT_CALC() {
		return this.ACCMLT_CALC;
	}
 
	 /**
	 * TN_RPAIR_TRGET.ACCMLT_CALC, 
	 * 보수_대상_항목.누적_산정 값설정
	 * @param accmltCalc
	 */
	public void setACCMLT_CALC(String accmltCalc) {
		this.ACCMLT_CALC = accmltCalc;
	}

	/**
	 * TN_RPAIR_TRGET.SLCTN_AT, 
	 * 보수_대상_항목.선정_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_AT") 
	public java.lang.String getSLCTN_AT() {
		return this.SLCTN_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.SLCTN_AT, 
	 * 보수_대상_항목.선정_여부 값설정
	 * @param slctnAt
	 */
	public void setSLCTN_AT(java.lang.String slctnAt) {
		this.SLCTN_AT = slctnAt;
	}

	/**
	 * TN_RPAIR_TRGET.SLCTN_DT, 
	 * 보수_대상_항목.선정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="SLCTN_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getSLCTN_DT() {
		return this.SLCTN_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.SLCTN_DT, 
	 * 보수_대상_항목.선정_일시 값설정
	 * @param slctnDt
	 */
	public void setSLCTN_DT(java.util.Date slctnDt) {
		this.SLCTN_DT = slctnDt;
	}

	/**
	 * TN_RPAIR_TRGET.DELETE_AT, 
	 * 보수_대상_항목.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.DELETE_AT, 
	 * 보수_대상_항목.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_RPAIR_TRGET.USE_AT, 
	 * 보수_대상_항목.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.USE_AT, 
	 * 보수_대상_항목.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_RPAIR_TRGET.CRTR_NO, 
	 * 보수_대상_항목.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CRTR_NO, 
	 * 보수_대상_항목.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_RPAIR_TRGET.CREAT_DT, 
	 * 보수_대상_항목.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.CREAT_DT, 
	 * 보수_대상_항목.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_RPAIR_TRGET.UPDUSR_NO, 
	 * 보수_대상_항목.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_RPAIR_TRGET.UPDUSR_NO, 
	 * 보수_대상_항목.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_RPAIR_TRGET.UPDT_DT, 
	 * 보수_대상_항목.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_RPAIR_TRGET.UPDT_DT, 
	 * 보수_대상_항목.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}
	@JsonProperty(value="ITEM_SLCTN_STTUS_NM") 
	public java.lang.String getITEM_SLCTN_STTUS_NM() {
		return ITEM_SLCTN_STTUS_NM;
	}

	public void setITEM_SLCTN_STTUS_NM(java.lang.String iTEM_SLCTN_STTUS_NM) {
		ITEM_SLCTN_STTUS_NM = iTEM_SLCTN_STTUS_NM;
	}
	@JsonProperty(value="CELL_TYPE_NM") 
	public java.lang.String getCELL_TYPE_NM() {
		return CELL_TYPE_NM;
	}

	public void setCELL_TYPE_NM(java.lang.String cELL_TYPE_NM) {
		CELL_TYPE_NM = cELL_TYPE_NM;
	}
	@JsonProperty(value="MSRC_NM") 
	public java.lang.String getMSRC_NM() {
		return MSRC_NM;
	}

	public void setMSRC_NM(java.lang.String mSRC_NM) {
		MSRC_NM = mSRC_NM;
	}
	@JsonProperty(value="DEPT_CODE") 
	public java.lang.String getDEPT_CODE() {
		return DEPT_CODE;
	}

	public void setDEPT_CODE(java.lang.String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	@JsonProperty(value="DEPT_NM") 
	public java.lang.String getDEPT_NM() {
		return DEPT_NM;
	}

	public void setDEPT_NM(java.lang.String dEPT_NM) {
		DEPT_NM = dEPT_NM;
	}
	@JsonProperty(value="DMG_VAL") 
	public Double getDMG_VAL() {
		return DMG_VAL;
	}

	public void setDMG_VAL(Double dMG_VAL) {
		DMG_VAL = dMG_VAL;
	}
	@JsonProperty(value="TMPR_SLCTN_AT") 
	public java.lang.String getTMPR_SLCTN_AT() {
		return TMPR_SLCTN_AT;
	}

	public void setTMPR_SLCTN_AT(java.lang.String tMPR_SLCTN_AT) {
		TMPR_SLCTN_AT = tMPR_SLCTN_AT;
	}
	@JsonProperty(value="MSR_DM_CODE")
	public java.lang.String getMSR_DM_CODE() {
		return MSR_DM_CODE;
	}

	public void setMSR_DM_CODE(java.lang.String mSR_DM_CODE) {
		MSR_DM_CODE = mSR_DM_CODE;
	}
	@JsonProperty(value="THRHLD")
	public java.lang.String getTHRHLD() {
		return THRHLD;
	}

	public void setTHRHLD(java.lang.String tHRHLD) {
		THRHLD = tHRHLD;
	}
	@JsonProperty(value="VMTC_GRAD")
	public String getVMTC_GRAD() {
		return VMTC_GRAD;
	}

	public void setVMTC_GRAD(String vMTC_GRAD) {
		VMTC_GRAD = vMTC_GRAD;
	}
	@JsonProperty(value="ROAD_GRAD")
	public String getROAD_GRAD() {
		return ROAD_GRAD;
	}

	public void setROAD_GRAD(String rOAD_GRAD) {
		ROAD_GRAD = rOAD_GRAD;
	}
	@JsonProperty(value="ADM_CODE")
	public String getADM_CODE() {
		return ADM_CODE;
	}

	public void setADM_CODE(String aDM_CODE) {
		ADM_CODE = aDM_CODE;
	}
	@JsonProperty(value="SLCTN_ORDR") 
	public String getSLCTN_ORDR() {
		return SLCTN_ORDR;
	}

	public void setSLCTN_ORDR(String sLCTN_ORDR) {
		SLCTN_ORDR = sLCTN_ORDR;
	}
	@JsonProperty(value="ROAD_GRAD_NM") 
	public String getROAD_GRAD_NM() {
		return ROAD_GRAD_NM;
	}

	public void setROAD_GRAD_NM(String rOAD_GRAD_NM) {
		ROAD_GRAD_NM = rOAD_GRAD_NM;
	}
	@JsonProperty(value="ADM_NM") 
	public String getADM_NM() {
		return ADM_NM;
	}

	public void setADM_NM(String aDM_NM) {
		ADM_NM = aDM_NM;
	}
	@JsonProperty(value="RPAIR_FEE") 
	public String getRPAIR_FEE() {
		return RPAIR_FEE;
	}

	public void setRPAIR_FEE(String rPAIR_FEE) {
		RPAIR_FEE = rPAIR_FEE;
	}
	@JsonProperty(value="VMTC_GRAD_NM") 
	public String getVMTC_GRAD_NM() {
		return VMTC_GRAD_NM;
	}

	public void setVMTC_GRAD_NM(String vMTC_GRAD_NM) {
		VMTC_GRAD_NM = vMTC_GRAD_NM;
	}
	@JsonProperty(value="TOTAL_BUDGET_ASIGN") 
	public String getTOTAL_BUDGET_ASIGN() {
		return this.TOTAL_BUDGET_ASIGN;
	}

	public void setTOTAL_BUDGET_ASIGN(String tOTAL_BUDGET_ASIGN) {
		this.TOTAL_BUDGET_ASIGN = tOTAL_BUDGET_ASIGN;
	}
	@JsonProperty(value="KILLO_LEN") 
	public String getKILLO_LEN() {
		return KILLO_LEN;
	}

	public void setKILLO_LEN(String kILLO_LEN) {
		KILLO_LEN = kILLO_LEN;
	}
	@JsonProperty(value="FIX_BUDGET_ASIGN") 
	public String getFIX_BUDGET_ASIGN() {
		return FIX_BUDGET_ASIGN;
	}

	public void setFIX_BUDGET_ASIGN(String fIX_BUDGET_ASIGN) {
		FIX_BUDGET_ASIGN = fIX_BUDGET_ASIGN;
	}
	@JsonProperty(value="TOTAL_AMOUNT_CALC")
	public Long getTOTAL_AMOUNT_CALC() {
		return TOTAL_AMOUNT_CALC;
	}

	public void setTOTAL_AMOUNT_CALC(Long tOTAL_AMOUNT_CALC) {
		TOTAL_AMOUNT_CALC = tOTAL_AMOUNT_CALC;
	}
	@JsonProperty(value="FIX_AMOUNT_CALC")
	public String getFIX_AMOUNT_CALC() {
		return FIX_AMOUNT_CALC;
	}

	public void setFIX_AMOUNT_CALC(String fIX_AMOUNT_CALC) {
		FIX_AMOUNT_CALC = fIX_AMOUNT_CALC;
	}

	/**
	 * @return the tOTAL_TMPR_FIX_BUDGET_ASIGN
	 */
	@JsonProperty(value="TOTAL_TMPR_FIX_BUDGET_ASIGN")
	public String getTOTAL_TMPR_FIX_BUDGET_ASIGN() {
		return TOTAL_TMPR_FIX_BUDGET_ASIGN;
	}

	/**
	 * @param tOTAL_TMPR_FIX_BUDGET_ASIGN the tOTAL_TMPR_FIX_BUDGET_ASIGN to set
	 */
	public void setTOTAL_TMPR_FIX_BUDGET_ASIGN(String tOTAL_TMPR_FIX_BUDGET_ASIGN) {
		TOTAL_TMPR_FIX_BUDGET_ASIGN = tOTAL_TMPR_FIX_BUDGET_ASIGN;
	}
	@JsonProperty(value="TOTAL_FIX_AMOUNT_CALC")
	public String getTOTAL_FIX_AMOUNT_CALC() {
		return TOTAL_FIX_AMOUNT_CALC;
	}

	public void setTOTAL_FIX_AMOUNT_CALC(String tOTAL_FIX_AMOUNT_CALC) {
		TOTAL_FIX_AMOUNT_CALC = tOTAL_FIX_AMOUNT_CALC;
	}
	@JsonProperty(value="TOTAL_FIX_BUDGET_ASIGN")
	public String getTOTAL_FIX_BUDGET_ASIGN() {
		return TOTAL_FIX_BUDGET_ASIGN;
	}

	public void setTOTAL_FIX_BUDGET_ASIGN(String tOTAL_FIX_BUDGET_ASIGN) {
		TOTAL_FIX_BUDGET_ASIGN = tOTAL_FIX_BUDGET_ASIGN;
	}
	@JsonProperty(value="TOTAL_TMPR_BUDGET_ASIGN")
	public String getTOTAL_TMPR_BUDGET_ASIGN() {
		return TOTAL_TMPR_BUDGET_ASIGN;
	}

	public void setTOTAL_TMPR_BUDGET_ASIGN(String tOTAL_TMPR_BUDGET_ASIGN) {
		TOTAL_TMPR_BUDGET_ASIGN = tOTAL_TMPR_BUDGET_ASIGN;
	}
	@JsonProperty(value="TMPR_FIX_BUDGET_ASIGN")
	public String getTMPR_FIX_BUDGET_ASIGN() {
		return TMPR_FIX_BUDGET_ASIGN;
	}

	public void setTMPR_FIX_BUDGET_ASIGN(String tMPR_FIX_BUDGET_ASIGN) {
		TMPR_FIX_BUDGET_ASIGN = tMPR_FIX_BUDGET_ASIGN;
	}
	@JsonProperty(value="CELL_IDS")
	public String getCELL_IDS() {
		return CELL_IDS;
	}

	public void setCELL_IDS(String cELL_IDS) {
		CELL_IDS = cELL_IDS;
	}

	@JsonProperty(value="MSRC_CL_NM")
	public java.lang.String getMSRC_CL_NM() {
		return MSRC_CL_NM;
	}

	public void setMSRC_CL_NM(java.lang.String mSRC_CL_NM) {
		MSRC_CL_NM = mSRC_CL_NM;
	}
	
	
}
