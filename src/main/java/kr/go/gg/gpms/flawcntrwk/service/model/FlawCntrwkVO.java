
package kr.go.gg.gpms.flawcntrwk.service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 하자_보수_공사
 *
 * @Class Name : FlawCntrwkVO.java
 * @Description : FlawCntrwk VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  	FlawCntrwkDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class FlawCntrwkVO extends BaseVO {

	public FlawCntrwkVO() {
		super();
	}
	
	/** 
	 * TN_FLAW_CNTRWK.CNTRWK_ID, 
	 * 하자_보수_공사.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_CNTRWK_ID, 
	 * 하자_보수_공사.하자_공사_ID
	 */
	@XmlElement
	private java.lang.String FLAW_CNTRWK_ID;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_ID, 
	 * 하자_보수_공사.하자_ID
	 */
	@XmlElement
	private java.lang.String FLAW_ID;

	/** 
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_ID, 
	 * 하자_보수_공사.세부_공사_ID
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_ID;

	/** 
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_GROUP, 
	 * 하자_보수_공사.세부_공사_그룹
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_GROUP;

	/** 
	 * TN_FLAW_CNTRWK.DETAIL_KND_NM, 
	 * 하자_보수_공사.세부_종류_명
	 */
	@XmlElement
	private java.lang.String DETAIL_KND_NM;

	/** 
	 * TN_FLAW_CNTRWK.CNTRWK_DE, 
	 * 하자_보수_공사.공사_일자
	 */
	@XmlElement
	private java.lang.String CNTRWK_DE;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_CO_NO, 
	 * 하자_보수_공사.하자_업체_번호
	 */
	@XmlElement
	private java.lang.String FLAW_CO_NO;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_CO_NM, 
	 * 하자_보수_공사.하자_업체_명
	 */
	@XmlElement
	private java.lang.String FLAW_CO_NM;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_CHCK_DE, 
	 * 하자_보수_공사.하자_검사_일자
	 */
	@XmlElement
	private java.lang.String FLAW_CHCK_DE;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_AR, 
	 * 하자_보수_공사.하자_면적
	 */
	@XmlElement
	private java.lang.String FLAW_AR;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_LEN, 
	 * 하자_보수_공사.하자_연장
	 */
	@XmlElement
	private java.lang.String FLAW_LEN;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_LC, 
	 * 하자_보수_공사.하자_위치
	 */
	@XmlElement
	private java.lang.String FLAW_LC;

	/** 
	 * TN_FLAW_CNTRWK.FLAW_CAUSE, 
	 * 하자_보수_공사.하자_원인
	 */
	@XmlElement
	private java.lang.String FLAW_CAUSE;

	/** 
	 * TN_FLAW_CNTRWK.ROAD_NM, 
	 * 하자_보수_공사.도로_명
	 */
	@XmlElement
	private java.lang.String ROAD_NM;

	/** 
	 * TN_FLAW_CNTRWK.ROUTE_CODE, 
	 * 하자_보수_공사.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_FLAW_CNTRWK.DIRECT_CODE, 
	 * 하자_보수_공사.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;

	/** 
	 * TN_FLAW_CNTRWK.TRACK, 
	 * 하자_보수_공사.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_FLAW_CNTRWK.STRTPT, 
	 * 하자_보수_공사.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/** 
	 * TN_FLAW_CNTRWK.ENDPT, 
	 * 하자_보수_공사.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/** 
	 * TN_FLAW_CNTRWK.PAV_STLE_CODE, 
	 * 하자_보수_공사.포장_형태_코드
	 */
	@XmlElement
	private java.lang.String PAV_STLE_CODE;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MSRC_ETC, 
	 * 하자_보수_공사.포장_공법_기타
	 */
	@XmlElement
	private java.lang.String PAV_MSRC_ETC;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_THICK_DC, 
	 * 하자_보수_공사.보수_두께_설명
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_DC;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_THICK, 
	 * 하자_보수_공사.보수_두께
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_AR, 
	 * 하자_보수_공사.보수_면적
	 */
	@XmlElement
	private java.lang.String RPAIR_AR;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_BT, 
	 * 하자_보수_공사.보수_폭
	 */
	@XmlElement
	private java.lang.String RPAIR_BT;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_LEN, 
	 * 하자_보수_공사.보수_연장
	 */
	@XmlElement
	private java.lang.String RPAIR_LEN;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_LC, 
	 * 하자_보수_공사.보수_위치
	 */
	@XmlElement
	private java.lang.String RPAIR_LC;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_DE, 
	 * 하자_보수_공사.보수_일자
	 */
	@XmlElement
	private java.lang.String RPAIR_DE;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_MATRL_DETAIL_NM, 
	 * 하자_보수_공사.보수_재료_세부_명
	 */
	@XmlElement
	private java.lang.String RPAIR_MATRL_DETAIL_NM;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_THICK_BASE, 
	 * 하자_보수_공사.보수_두께_기층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_BASE;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_THICK_CNTR, 
	 * 하자_보수_공사.보수_두께_중간층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_CNTR;

	/** 
	 * TN_FLAW_CNTRWK.RPAIR_THICK_ASCON, 
	 * 하자_보수_공사.보수_두께_표층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_ASCON;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_CODE, 
	 * 하자_보수_공사.포장_재료_표층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_CODE;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_ETC, 
	 * 하자_보수_공사.포장_재료_표층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_ETC;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_CODE, 
	 * 하자_보수_공사.포장_재료_기층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_BASE_CODE;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_ETC, 
	 * 하자_보수_공사.포장_재료_기층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_BASE_ETC;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_CODE, 
	 * 하자_보수_공사.포장_재료_중간층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_CNTR_CODE;

	/** 
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_ETC, 
	 * 하자_보수_공사.포장_재료_중간층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_CNTR_ETC;

	/** 
	 * TN_FLAW_CNTRWK.FILE_NO, 
	 * 하자_보수_공사.파일_번호
	 */
	@XmlElement
	private java.lang.String FILE_NO;

	/** 
	 * TN_FLAW_CNTRWK.OPERT_BFE_PHOTO_NO, 
	 * 하자_보수_공사.작업_전_사진_번호
	 */
	@XmlElement
	private java.lang.String OPERT_BFE_PHOTO_NO;

	/** 
	 * TN_FLAW_CNTRWK.OPERT_AFT_PHOTO_NO, 
	 * 하자_보수_공사.작업_후_사진_번호
	 */
	@XmlElement
	private java.lang.String OPERT_AFT_PHOTO_NO;

	/** 
	 * TN_FLAW_CNTRWK.USE_AT, 
	 * 하자_보수_공사.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_FLAW_CNTRWK.DELETE_AT, 
	 * 하자_보수_공사.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_FLAW_CNTRWK.CRTR_NO, 
	 * 하자_보수_공사.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_FLAW_CNTRWK.CREAT_DT, 
	 * 하자_보수_공사.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_FLAW_CNTRWK.UPDUSR_NO, 
	 * 하자_보수_공사.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_FLAW_CNTRWK.UPDT_DT, 
	 * 하자_보수_공사.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	/** 
	 * TN_FLAW_CNTRWK.RPAIR_MTHD_CODE, 
	 * 하자_보수_공사.보수_공법_코드
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_CODE;
	
	/** 
	 * 노선명
	 */
	@XmlElement
	private java.lang.String ROUTE_NM;
	
	/** 
	 * 공사 전 사진명
	 */
	@XmlElement
	private java.lang.String OPERT_BFE_PHOTO_NM;
	
	/** 
	 * 공사 후 사진명
	 */
	@XmlElement
	private java.lang.String OPERT_AFT_PHOTO_NM;
	
	/** 
	 * 포장재료명_표층
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_NM;
	
	/** 
	 * 보수공법명
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_NM;
	
	//셀관리번호
	@XmlElement
	private java.lang.String CELL_ID;

	/**
	 * TN_FLAW_CNTRWK.CNTRWK_ID, 
	 * 하자_보수_공사.공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.CNTRWK_ID, 
	 * 하자_보수_공사.공사_ID 값설정
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_CNTRWK_ID, 
	 * 하자_보수_공사.하자_공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CNTRWK_ID") 
	public java.lang.String getFLAW_CNTRWK_ID() {
		return this.FLAW_CNTRWK_ID;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_CNTRWK_ID, 
	 * 하자_보수_공사.하자_공사_ID 값설정
	 * @param flawCntrwkId
	 */
	public void setFLAW_CNTRWK_ID(java.lang.String flawCntrwkId) {
		this.FLAW_CNTRWK_ID = flawCntrwkId;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_ID, 
	 * 하자_보수_공사.하자_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_ID") 
	public java.lang.String getFLAW_ID() {
		return this.FLAW_ID;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_ID, 
	 * 하자_보수_공사.하자_ID 값설정
	 * @param flawId
	 */
	public void setFLAW_ID(java.lang.String flawId) {
		this.FLAW_ID = flawId;
	}

	/**
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_ID, 
	 * 하자_보수_공사.세부_공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_ID") 
	public java.lang.String getDETAIL_CNTRWK_ID() {
		return this.DETAIL_CNTRWK_ID;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_ID, 
	 * 하자_보수_공사.세부_공사_ID 값설정
	 * @param detailCntrwkId
	 */
	public void setDETAIL_CNTRWK_ID(java.lang.String detailCntrwkId) {
		this.DETAIL_CNTRWK_ID = detailCntrwkId;
	}

	/**
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_GROUP, 
	 * 하자_보수_공사.세부_공사_그룹 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_GROUP") 
	public java.lang.String getDETAIL_CNTRWK_GROUP() {
		return this.DETAIL_CNTRWK_GROUP;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.DETAIL_CNTRWK_GROUP, 
	 * 하자_보수_공사.세부_공사_그룹 값설정
	 * @param detailCntrwkGroup
	 */
	public void setDETAIL_CNTRWK_GROUP(java.lang.String detailCntrwkGroup) {
		this.DETAIL_CNTRWK_GROUP = detailCntrwkGroup;
	}

	/**
	 * TN_FLAW_CNTRWK.DETAIL_KND_NM, 
	 * 하자_보수_공사.세부_종류_명 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_KND_NM") 
	public java.lang.String getDETAIL_KND_NM() {
		return this.DETAIL_KND_NM;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.DETAIL_KND_NM, 
	 * 하자_보수_공사.세부_종류_명 값설정
	 * @param detailKndNm
	 */
	public void setDETAIL_KND_NM(java.lang.String detailKndNm) {
		this.DETAIL_KND_NM = detailKndNm;
	}

	/**
	 * TN_FLAW_CNTRWK.CNTRWK_DE, 
	 * 하자_보수_공사.공사_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_DE") 
	public java.lang.String getCNTRWK_DE() {
		return this.CNTRWK_DE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.CNTRWK_DE, 
	 * 하자_보수_공사.공사_일자 값설정
	 * @param cntrwkDe
	 */
	public void setCNTRWK_DE(java.lang.String cntrwkDe) {
		this.CNTRWK_DE = cntrwkDe;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_CO_NO, 
	 * 하자_보수_공사.하자_업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CO_NO") 
	public java.lang.String getFLAW_CO_NO() {
		return this.FLAW_CO_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_CO_NO, 
	 * 하자_보수_공사.하자_업체_번호 값설정
	 * @param flawCoNo
	 */
	public void setFLAW_CO_NO(java.lang.String flawCoNo) {
		this.FLAW_CO_NO = flawCoNo;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_CO_NM, 
	 * 하자_보수_공사.하자_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CO_NM") 
	public java.lang.String getFLAW_CO_NM() {
		return this.FLAW_CO_NM;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_CO_NM, 
	 * 하자_보수_공사.하자_업체_명 값설정
	 * @param flawCoNm
	 */
	public void setFLAW_CO_NM(java.lang.String flawCoNm) {
		this.FLAW_CO_NM = flawCoNm;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_CHCK_DE, 
	 * 하자_보수_공사.하자_검사_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CHCK_DE") 
	public java.lang.String getFLAW_CHCK_DE() {
		return this.FLAW_CHCK_DE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_CHCK_DE, 
	 * 하자_보수_공사.하자_검사_일자 값설정
	 * @param flawChckDe
	 */
	public void setFLAW_CHCK_DE(java.lang.String flawChckDe) {
		this.FLAW_CHCK_DE = flawChckDe;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_AR, 
	 * 하자_보수_공사.하자_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_AR") 
	public java.lang.String getFLAW_AR() {
		return this.FLAW_AR;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_AR, 
	 * 하자_보수_공사.하자_면적 값설정
	 * @param flawAr
	 */
	public void setFLAW_AR(java.lang.String flawAr) {
		this.FLAW_AR = flawAr;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_LEN, 
	 * 하자_보수_공사.하자_연장 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_LEN") 
	public java.lang.String getFLAW_LEN() {
		return this.FLAW_LEN;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_LEN, 
	 * 하자_보수_공사.하자_연장 값설정
	 * @param flawLen
	 */
	public void setFLAW_LEN(java.lang.String flawLen) {
		this.FLAW_LEN = flawLen;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_LC, 
	 * 하자_보수_공사.하자_위치 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_LC") 
	public java.lang.String getFLAW_LC() {
		return this.FLAW_LC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_LC, 
	 * 하자_보수_공사.하자_위치 값설정
	 * @param flawLc
	 */
	public void setFLAW_LC(java.lang.String flawLc) {
		this.FLAW_LC = flawLc;
	}

	/**
	 * TN_FLAW_CNTRWK.FLAW_CAUSE, 
	 * 하자_보수_공사.하자_원인 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_CAUSE") 
	public java.lang.String getFLAW_CAUSE() {
		return this.FLAW_CAUSE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FLAW_CAUSE, 
	 * 하자_보수_공사.하자_원인 값설정
	 * @param flawCause
	 */
	public void setFLAW_CAUSE(java.lang.String flawCause) {
		this.FLAW_CAUSE = flawCause;
	}

	/**
	 * TN_FLAW_CNTRWK.ROAD_NM, 
	 * 하자_보수_공사.도로_명 값읽기
	 * @return
	 */
	@JsonProperty(value="ROAD_NM") 
	public java.lang.String getROAD_NM() {
		return this.ROAD_NM;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.ROAD_NM, 
	 * 하자_보수_공사.도로_명 값설정
	 * @param roadNm
	 */
	public void setROAD_NM(java.lang.String roadNm) {
		this.ROAD_NM = roadNm;
	}

	/**
	 * TN_FLAW_CNTRWK.ROUTE_CODE, 
	 * 하자_보수_공사.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.ROUTE_CODE, 
	 * 하자_보수_공사.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_FLAW_CNTRWK.DIRECT_CODE, 
	 * 하자_보수_공사.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.DIRECT_CODE, 
	 * 하자_보수_공사.행선_코드 값설정
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
		setDIRECT_NM(this.DIRECT_CODE);
	}

	/**
	 * TN_FLAW_CNTRWK.TRACK, 
	 * 하자_보수_공사.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.TRACK, 
	 * 하자_보수_공사.차로 값설정
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * TN_FLAW_CNTRWK.STRTPT, 
	 * 하자_보수_공사.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.STRTPT, 
	 * 하자_보수_공사.시점 값설정
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * TN_FLAW_CNTRWK.ENDPT, 
	 * 하자_보수_공사.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.ENDPT, 
	 * 하자_보수_공사.종점 값설정
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_STLE_CODE, 
	 * 하자_보수_공사.포장_형태_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_STLE_CODE") 
	public java.lang.String getPAV_STLE_CODE() {
		return this.PAV_STLE_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_STLE_CODE, 
	 * 하자_보수_공사.포장_형태_코드 값설정
	 * @param pavStleCode
	 */
	public void setPAV_STLE_CODE(java.lang.String pavStleCode) {
		this.PAV_STLE_CODE = pavStleCode;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MSRC_ETC, 
	 * 하자_보수_공사.포장_공법_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MSRC_ETC") 
	public java.lang.String getPAV_MSRC_ETC() {
		return this.PAV_MSRC_ETC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MSRC_ETC, 
	 * 하자_보수_공사.포장_공법_기타 값설정
	 * @param pavMsrcEtc
	 */
	public void setPAV_MSRC_ETC(java.lang.String pavMsrcEtc) {
		this.PAV_MSRC_ETC = pavMsrcEtc;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_DC, 
	 * 하자_보수_공사.보수_두께_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_DC") 
	public java.lang.String getRPAIR_THICK_DC() {
		return this.RPAIR_THICK_DC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_DC, 
	 * 하자_보수_공사.보수_두께_설명 값설정
	 * @param rpairThickDc
	 */
	public void setRPAIR_THICK_DC(java.lang.String rpairThickDc) {
		this.RPAIR_THICK_DC = rpairThickDc;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_THICK, 
	 * 하자_보수_공사.보수_두께 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK") 
	public java.lang.String getRPAIR_THICK() {
		return this.RPAIR_THICK;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_THICK, 
	 * 하자_보수_공사.보수_두께 값설정
	 * @param rpairThick
	 */
	public void setRPAIR_THICK(java.lang.String rpairThick) {
		this.RPAIR_THICK = rpairThick;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_AR, 
	 * 하자_보수_공사.보수_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_AR") 
	public java.lang.String getRPAIR_AR() {
		return this.RPAIR_AR;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_AR, 
	 * 하자_보수_공사.보수_면적 값설정
	 * @param rpairAr
	 */
	public void setRPAIR_AR(java.lang.String rpairAr) {
		this.RPAIR_AR = rpairAr;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_BT, 
	 * 하자_보수_공사.보수_폭 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_BT") 
	public java.lang.String getRPAIR_BT() {
		return this.RPAIR_BT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_BT, 
	 * 하자_보수_공사.보수_폭 값설정
	 * @param rpairBt
	 */
	public void setRPAIR_BT(java.lang.String rpairBt) {
		this.RPAIR_BT = rpairBt;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_LEN, 
	 * 하자_보수_공사.보수_연장 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_LEN") 
	public java.lang.String getRPAIR_LEN() {
		return this.RPAIR_LEN;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_LEN, 
	 * 하자_보수_공사.보수_연장 값설정
	 * @param rpairLen
	 */
	public void setRPAIR_LEN(java.lang.String rpairLen) {
		this.RPAIR_LEN = rpairLen;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_LC, 
	 * 하자_보수_공사.보수_위치 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_LC") 
	public java.lang.String getRPAIR_LC() {
		return this.RPAIR_LC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_LC, 
	 * 하자_보수_공사.보수_위치 값설정
	 * @param rpairLc
	 */
	public void setRPAIR_LC(java.lang.String rpairLc) {
		this.RPAIR_LC = rpairLc;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_DE, 
	 * 하자_보수_공사.보수_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_DE") 
	public java.lang.String getRPAIR_DE() {
		return this.RPAIR_DE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_DE, 
	 * 하자_보수_공사.보수_일자 값설정
	 * @param rpairDe
	 */
	public void setRPAIR_DE(java.lang.String rpairDe) {
		this.RPAIR_DE = rpairDe;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_MATRL_DETAIL_NM, 
	 * 하자_보수_공사.보수_재료_세부_명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MATRL_DETAIL_NM") 
	public java.lang.String getRPAIR_MATRL_DETAIL_NM() {
		return this.RPAIR_MATRL_DETAIL_NM;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_MATRL_DETAIL_NM, 
	 * 하자_보수_공사.보수_재료_세부_명 값설정
	 * @param rpairMatrlDetailNm
	 */
	public void setRPAIR_MATRL_DETAIL_NM(java.lang.String rpairMatrlDetailNm) {
		this.RPAIR_MATRL_DETAIL_NM = rpairMatrlDetailNm;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_BASE, 
	 * 하자_보수_공사.보수_두께_기층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_BASE") 
	public java.lang.String getRPAIR_THICK_BASE() {
		return this.RPAIR_THICK_BASE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_BASE, 
	 * 하자_보수_공사.보수_두께_기층 값설정
	 * @param rpairThickBase
	 */
	public void setRPAIR_THICK_BASE(java.lang.String rpairThickBase) {
		this.RPAIR_THICK_BASE = rpairThickBase;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_CNTR, 
	 * 하자_보수_공사.보수_두께_중간층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_CNTR") 
	public java.lang.String getRPAIR_THICK_CNTR() {
		return this.RPAIR_THICK_CNTR;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_CNTR, 
	 * 하자_보수_공사.보수_두께_중간층 값설정
	 * @param rpairThickCntr
	 */
	public void setRPAIR_THICK_CNTR(java.lang.String rpairThickCntr) {
		this.RPAIR_THICK_CNTR = rpairThickCntr;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_ASCON, 
	 * 하자_보수_공사.보수_두께_표층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_ASCON") 
	public java.lang.String getRPAIR_THICK_ASCON() {
		return this.RPAIR_THICK_ASCON;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.RPAIR_THICK_ASCON, 
	 * 하자_보수_공사.보수_두께_표층 값설정
	 * @param rpairThickAscon
	 */
	public void setRPAIR_THICK_ASCON(java.lang.String rpairThickAscon) {
		this.RPAIR_THICK_ASCON = rpairThickAscon;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_CODE, 
	 * 하자_보수_공사.포장_재료_표층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_ASCON_CODE") 
	public java.lang.String getPAV_MATRL_ASCON_CODE() {
		return this.PAV_MATRL_ASCON_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_CODE, 
	 * 하자_보수_공사.포장_재료_표층_코드 값설정
	 * @param pavMatrlAsconCode
	 */
	public void setPAV_MATRL_ASCON_CODE(java.lang.String pavMatrlAsconCode) {
		this.PAV_MATRL_ASCON_CODE = pavMatrlAsconCode;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_ETC, 
	 * 하자_보수_공사.포장_재료_표층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_ASCON_ETC") 
	public java.lang.String getPAV_MATRL_ASCON_ETC() {
		return this.PAV_MATRL_ASCON_ETC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_ASCON_ETC, 
	 * 하자_보수_공사.포장_재료_표층_기타 값설정
	 * @param pavMatrlAsconEtc
	 */
	public void setPAV_MATRL_ASCON_ETC(java.lang.String pavMatrlAsconEtc) {
		this.PAV_MATRL_ASCON_ETC = pavMatrlAsconEtc;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_CODE, 
	 * 하자_보수_공사.포장_재료_기층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_BASE_CODE") 
	public java.lang.String getPAV_MATRL_BASE_CODE() {
		return this.PAV_MATRL_BASE_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_CODE, 
	 * 하자_보수_공사.포장_재료_기층_코드 값설정
	 * @param pavMatrlBaseCode
	 */
	public void setPAV_MATRL_BASE_CODE(java.lang.String pavMatrlBaseCode) {
		this.PAV_MATRL_BASE_CODE = pavMatrlBaseCode;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_ETC, 
	 * 하자_보수_공사.포장_재료_기층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_BASE_ETC") 
	public java.lang.String getPAV_MATRL_BASE_ETC() {
		return this.PAV_MATRL_BASE_ETC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_BASE_ETC, 
	 * 하자_보수_공사.포장_재료_기층_기타 값설정
	 * @param pavMatrlBaseEtc
	 */
	public void setPAV_MATRL_BASE_ETC(java.lang.String pavMatrlBaseEtc) {
		this.PAV_MATRL_BASE_ETC = pavMatrlBaseEtc;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_CODE, 
	 * 하자_보수_공사.포장_재료_중간층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_CNTR_CODE") 
	public java.lang.String getPAV_MATRL_CNTR_CODE() {
		return this.PAV_MATRL_CNTR_CODE;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_CODE, 
	 * 하자_보수_공사.포장_재료_중간층_코드 값설정
	 * @param pavMatrlCntrCode
	 */
	public void setPAV_MATRL_CNTR_CODE(java.lang.String pavMatrlCntrCode) {
		this.PAV_MATRL_CNTR_CODE = pavMatrlCntrCode;
	}

	/**
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_ETC, 
	 * 하자_보수_공사.포장_재료_중간층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_CNTR_ETC") 
	public java.lang.String getPAV_MATRL_CNTR_ETC() {
		return this.PAV_MATRL_CNTR_ETC;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.PAV_MATRL_CNTR_ETC, 
	 * 하자_보수_공사.포장_재료_중간층_기타 값설정
	 * @param pavMatrlCntrEtc
	 */
	public void setPAV_MATRL_CNTR_ETC(java.lang.String pavMatrlCntrEtc) {
		this.PAV_MATRL_CNTR_ETC = pavMatrlCntrEtc;
	}

	/**
	 * TN_FLAW_CNTRWK.FILE_NO, 
	 * 하자_보수_공사.파일_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NO") 
	public java.lang.String getFILE_NO() {
		return this.FILE_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.FILE_NO, 
	 * 하자_보수_공사.파일_번호 값설정
	 * @param fileNo
	 */
	public void setFILE_NO(java.lang.String fileNo) {
		this.FILE_NO = fileNo;
	}

	/**
	 * TN_FLAW_CNTRWK.OPERT_BFE_PHOTO_NO, 
	 * 하자_보수_공사.작업_전_사진_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="OPERT_BFE_PHOTO_NO") 
	public java.lang.String getOPERT_BFE_PHOTO_NO() {
		return this.OPERT_BFE_PHOTO_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.OPERT_BFE_PHOTO_NO, 
	 * 하자_보수_공사.작업_전_사진_번호 값설정
	 * @param opertBfePhotoNo
	 */
	public void setOPERT_BFE_PHOTO_NO(java.lang.String opertBfePhotoNo) {
		this.OPERT_BFE_PHOTO_NO = opertBfePhotoNo;
	}

	/**
	 * TN_FLAW_CNTRWK.OPERT_AFT_PHOTO_NO, 
	 * 하자_보수_공사.작업_후_사진_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="OPERT_AFT_PHOTO_NO") 
	public java.lang.String getOPERT_AFT_PHOTO_NO() {
		return this.OPERT_AFT_PHOTO_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.OPERT_AFT_PHOTO_NO, 
	 * 하자_보수_공사.작업_후_사진_번호 값설정
	 * @param opertAftPhotoNo
	 */
	public void setOPERT_AFT_PHOTO_NO(java.lang.String opertAftPhotoNo) {
		this.OPERT_AFT_PHOTO_NO = opertAftPhotoNo;
	}

	/**
	 * TN_FLAW_CNTRWK.USE_AT, 
	 * 하자_보수_공사.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.USE_AT, 
	 * 하자_보수_공사.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_FLAW_CNTRWK.DELETE_AT, 
	 * 하자_보수_공사.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.DELETE_AT, 
	 * 하자_보수_공사.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_FLAW_CNTRWK.CRTR_NO, 
	 * 하자_보수_공사.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.CRTR_NO, 
	 * 하자_보수_공사.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_FLAW_CNTRWK.CREAT_DT, 
	 * 하자_보수_공사.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.CREAT_DT, 
	 * 하자_보수_공사.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_FLAW_CNTRWK.UPDUSR_NO, 
	 * 하자_보수_공사.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.UPDUSR_NO, 
	 * 하자_보수_공사.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_FLAW_CNTRWK.UPDT_DT, 
	 * 하자_보수_공사.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_FLAW_CNTRWK.UPDT_DT, 
	 * 하자_보수_공사.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_MTHD_CODE, 
	 * 하자_보수_공사.보수_공법_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MTHD_CODE") 
	public java.lang.String getRPAIR_MTHD_CODE() {
		return RPAIR_MTHD_CODE;
	}

	/**
	 * TN_FLAW_CNTRWK.RPAIR_MTHD_CODE, 
	 * 하자_보수_공사.보수_공법_코드 값설정
	 * @param rpairMthdCode
	 */
	public void setRPAIR_MTHD_CODE(java.lang.String rpairMthdCode) {
		this.RPAIR_MTHD_CODE = rpairMthdCode;
	}
	
	@JsonProperty(value="ROUTE_NM") 
	public java.lang.String getROUTE_NM() {
		return this.ROUTE_NM;
	}

	public void setROUTE_NM(java.lang.String rOUTE_NM) {
		 this.ROUTE_NM = rOUTE_NM;
	}

	@JsonProperty(value="OPERT_BFE_PHOTO_NM") 
	public java.lang.String getOPERT_BFE_PHOTO_NM() {
		return this.OPERT_BFE_PHOTO_NM;
	}

	public void setOPERT_BFE_PHOTO_NM(java.lang.String oPERT_BFE_PHOTO_NM) {
		this.OPERT_BFE_PHOTO_NM = oPERT_BFE_PHOTO_NM;
	}

	@JsonProperty(value="OPERT_AFT_PHOTO_NM") 
	public java.lang.String getOPERT_AFT_PHOTO_NM() {
		return this.OPERT_AFT_PHOTO_NM;
	}

	public void setOPERT_AFT_PHOTO_NM(java.lang.String oPERT_AFT_PHOTO_NM) {
		this.OPERT_AFT_PHOTO_NM = oPERT_AFT_PHOTO_NM;
	}

	@JsonProperty(value="PAV_MATRL_ASCON_NM") 
	public java.lang.String getPAV_MATRL_ASCON_NM() {
		return this.PAV_MATRL_ASCON_NM;
	}

	public void setPAV_MATRL_ASCON_NM(java.lang.String pAV_MATRL_ASCON_NM) {
		this.PAV_MATRL_ASCON_NM = pAV_MATRL_ASCON_NM;
	}

	@JsonProperty(value="RPAIR_MTHD_NM") 
	public java.lang.String getRPAIR_MTHD_NM() {
		return this.RPAIR_MTHD_NM;
	}

	public void setRPAIR_MTHD_NM(java.lang.String rPAIR_MTHD_NM) {
		this.RPAIR_MTHD_NM = rPAIR_MTHD_NM;
	}

	@JsonProperty(value="CELL_ID") 
	public java.lang.String getCELL_ID() {
		return this.CELL_ID;
	}

	public void setCELL_ID(java.lang.String cELL_ID) {
		this.CELL_ID = cELL_ID;
	}
}
