
package kr.go.gg.gpms.cntrwkdtl.service.model;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 공사상세정보
 *
 * @Class Name : CntrwkDtlVO.java
 * @Description : CntrwkDtl VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  	CntrwkDtlDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CntrwkDtlVO extends BaseVO {

	public CntrwkDtlVO() {
		super();
	}
	
	
	/** 
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_ID, 
	 * 공사상세정보.세부_공사_ID
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_ID;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_ID, 
	 * 공사상세정보.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_MTHD_CODE, 
	 * 공사상세정보.보수_공법_코드
	 */
	@XmlElement
	private java.lang.String RPAIR_MTHD_CODE;

	/** 
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_GROUP, 
	 * 공사상세정보.세부_공사_그룹
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_GROUP;

	/** 
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_NM, 
	 * 공사상세정보.세부_공사_명
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_NM;

	/** 
	 * TN_CNTRWK_DTL.TRACK_LEN, 
	 * 공사상세정보.1차로_연장
	 */
	@XmlElement
	private java.lang.String TRACK_LEN;

	/** 
	 * TN_CNTRWK_DTL.SPRVISN_CO_NO, 
	 * 공사상세정보.감리_업체_번호
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_NO;

	/** 
	 * TN_CNTRWK_DTL.SPRVISN_CO_NM, 
	 * 공사상세정보.감리_업체_명
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_NM;

	/** 
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNT_NO, 
	 * 공사상세정보.감리_업체_대표_번호
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_RPRSNT_NO;

	/** 
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사상세정보.감리_업체_대표자_명
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_RPRSNTV_NM;

	/** 
	 * TN_CNTRWK_DTL.SPRVISOR_NM, 
	 * 공사상세정보.감리원_명
	 */
	@XmlElement
	private java.lang.String SPRVISOR_NM;

	/** 
	 * TN_CNTRWK_DTL.SPRVISOR_TELNO, 
	 * 공사상세정보.감리원_전화번호
	 */
	@XmlElement
	private java.lang.String SPRVISOR_TELNO;

	/** 
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NO, 
	 * 공사상세정보.시공_업체_번호
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_NO;

	/** 
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NM, 
	 * 공사상세정보.시공_업체_명
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_NM;

	/** 
	 * TN_CNTRWK_DTL.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사상세정보.시공_업체_대표자_명
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_RPRSNTV_NM;

	/** 
	 * TN_CNTRWK_DTL.CNSTRCT_CO_TELNO, 
	 * 공사상세정보.시공_업체_전화번호
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_TELNO;

	/** 
	 * TN_CNTRWK_DTL.CNSTRCT_CO_CHARGER_NM, 
	 * 공사상세정보.시공_업체_담당자_명
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_CHARGER_NM;

	/** 
	 * TN_CNTRWK_DTL.SPT_HDCH_NM, 
	 * 공사상세정보.현장_소장_명
	 */
	@XmlElement
	private java.lang.String SPT_HDCH_NM;

	/** 
	 * TN_CNTRWK_DTL.SPT_HDCH_TELNO, 
	 * 공사상세정보.현장_소장_전화번호
	 */
	@XmlElement
	private java.lang.String SPT_HDCH_TELNO;

	/** 
	 * TN_CNTRWK_DTL.ROAD_NM, 
	 * 공사상세정보.도로_명
	 */
	@XmlElement
	private java.lang.String ROAD_NM;

	/** 
	 * TN_CNTRWK_DTL.ROUTE_CODE, 
	 * 공사상세정보.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_CODE;

	/** 
	 * TN_CNTRWK_DTL.ROUTE_NM, 
	 * 공사상세정보.노선_코드
	 */
	@XmlElement
	private java.lang.String ROUTE_NM;
	
	/** 
	 * TN_CNTRWK_DTL.DIRECT_CODE, 
	 * 공사상세정보.행선_코드
	 */
	@XmlElement
	private java.lang.String DIRECT_CODE;
	
	
	/** 
	 * TN_CNTRWK_DTL.TRACK, 
	 * 공사상세정보.차로
	 */
	@XmlElement
	private java.lang.String TRACK;

	/** 
	 * TN_CNTRWK_DTL.STRTPT, 
	 * 공사상세정보.시점
	 */
	@XmlElement
	private java.lang.String STRTPT;

	/** 
	 * TN_CNTRWK_DTL.ENDPT, 
	 * 공사상세정보.종점
	 */
	@XmlElement
	private java.lang.String ENDPT;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_STRTPT_NM, 
	 * 공사상세정보.공사_시점_명
	 */
	@XmlElement
	private java.lang.String CNTRWK_STRTPT_NM;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_ENDPT_NM, 
	 * 공사상세정보.공사_종점_명
	 */
	@XmlElement
	private java.lang.String CNTRWK_ENDPT_NM;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_AMOUNT, 
	 * 공사상세정보.공사_금액
	 */
	@XmlElement
	private java.lang.String CNTRWK_AMOUNT;

	/** 
	 * TN_CNTRWK_DTL.GVSLCT, 
	 * 공사상세정보.관급비
	 */
	@XmlElement
	private java.lang.String GVSLCT;

	/** 
	 * TN_CNTRWK_DTL.OUTSRCCT, 
	 * 공사상세정보.도급비
	 */
	@XmlElement
	private java.lang.String OUTSRCCT;

	/** 
	 * TN_CNTRWK_DTL.ETC_RLOCATCT, 
	 * 공사상세정보.기타_이설비
	 */
	@XmlElement
	private java.lang.String ETC_RLOCATCT;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_TMPRT, 
	 * 공사상세정보.공사_기온
	 */
	@XmlElement
	private java.lang.String CNTRWK_TMPRT;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_WETHR, 
	 * 공사상세정보.공사_날씨
	 */
	@XmlElement
	private java.lang.String CNTRWK_WETHR;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_TIME, 
	 * 공사상세정보.공사_시간
	 */
	@XmlElement
	private java.lang.String CNTRWK_TIME;

	/** 
	 * TN_CNTRWK_DTL.CNTRWK_CL, 
	 * 공사상세정보.공사_분류
	 */
	@XmlElement
	private java.lang.String CNTRWK_CL;

	/** 
	 * TN_CNTRWK_DTL.BUS_DVR_TRACK_AT, 
	 * 공사상세정보.버스_전용_차로_여부
	 */
	@XmlElement
	private java.lang.String BUS_DVR_TRACK_AT;

	/** 
	 * TN_CNTRWK_DTL.BEFORE_PAV_YEAR, 
	 * 공사상세정보.직전_포장_연도
	 */
	@XmlElement
	private java.lang.String BEFORE_PAV_YEAR;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_BEGIN_DE, 
	 * 공사상세정보.보수_시작_일자
	 */
	@XmlElement
	private java.lang.String RPAIR_BEGIN_DE;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_END_DE, 
	 * 공사상세정보.보수_종료_일자
	 */
	@XmlElement
	private java.lang.String RPAIR_END_DE;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_AR, 
	 * 공사상세정보.보수_면적
	 */
	@XmlElement
	private java.lang.String RPAIR_AR;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_BT, 
	 * 공사상세정보.보수_폭
	 */
	@XmlElement
	private java.lang.String RPAIR_BT;

	/** 
	 * TN_CNTRWK_DTL.PAV_STLE_CODE, 
	 * 공사상세정보.포장_형태_코드
	 */
	@XmlElement
	private java.lang.String PAV_STLE_CODE;

	/** 
	 * TN_CNTRWK_DTL.PAV_MSRC_ETC, 
	 * 공사상세정보.포장_공법_기타
	 */
	@XmlElement
	private java.lang.String PAV_MSRC_ETC;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_THICK_DC, 
	 * 공사상세정보.보수_두께_설명
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_DC;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_THICK, 
	 * 공사상세정보.보수_두께
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_THICK_BASE, 
	 * 공사상세정보.보수_두께_기층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_BASE;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_THICK_CNTR, 
	 * 공사상세정보.보수_두께_중간층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_CNTR;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_THICK_ASCON, 
	 * 공사상세정보.보수_두께_표층
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_ASCON;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_ETC, 
	 * 공사상세정보.포장_재료_표층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_ETC;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_CODE, 
	 * 공사상세정보.포장_재료_기층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_BASE_CODE;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_ETC, 
	 * 공사상세정보.포장_재료_기층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_BASE_ETC;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_CODE, 
	 * 공사상세정보.포장_재료_중간층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_CNTR_CODE;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_ETC, 
	 * 공사상세정보.포장_재료_중간층_기타
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_CNTR_ETC;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_MATRL_DETAIL_NM, 
	 * 공사상세정보.보수_재료_세부_명
	 */
	@XmlElement
	private java.lang.String RPAIR_MATRL_DETAIL_NM;

	/** 
	 * TN_CNTRWK_DTL.RPAIR_MATRL_PRDCT_CO_NM, 
	 * 공사상세정보.보수_재료_생산_업체_명
	 */
	@XmlElement
	private java.lang.String RPAIR_MATRL_PRDCT_CO_NM;

	/** 
	 * TN_CNTRWK_DTL.PRDCT_CO_TELNO, 
	 * 공사상세정보.생산_업체_전화번호
	 */
	@XmlElement
	private java.lang.String PRDCT_CO_TELNO;

	/** 
	 * TN_CNTRWK_DTL.ASPAT_RNS_CRRSPND_AT, 
	 * 공사상세정보.아스팔트_실명제_해당_여부
	 */
	@XmlElement
	private java.lang.String ASPAT_RNS_CRRSPND_AT;

	/** 
	 * TN_CNTRWK_DTL.RM, 
	 * 공사상세정보.비고
	 */
	@XmlElement
	private java.lang.String RM;

	/** 
	 * TN_CNTRWK_DTL.PROCESS_RESULT_MSSAGE, 
	 * 공사상세정보.처리_결과_메세지
	 */
	@XmlElement
	private java.lang.String PROCESS_RESULT_MSSAGE;

	/** 
	 * TN_CNTRWK_DTL.OPERT_BFE_PHOTO_NO, 
	 * 공사상세정보.작업_전_사진_번호
	 */
	@XmlElement
	private java.lang.String OPERT_BFE_PHOTO_NO;

	/** 
	 * TN_CNTRWK_DTL.OPERT_AFT_PHOTO_NO, 
	 * 공사상세정보.작업_후_사진_번호
	 */
	@XmlElement
	private java.lang.String OPERT_AFT_PHOTO_NO;

	/** 
	 * TN_CNTRWK_DTL.FILE_NO, 
	 * 공사상세정보.파일_번호
	 */
	@XmlElement
	private java.lang.String FILE_NO;

	/** 
	 * TN_CNTRWK_DTL.CONFM_STTUS_CODE, 
	 * 공사상세정보.승인_상태_코드
	 */
	@XmlElement
	private java.lang.String CONFM_STTUS_CODE;

	/** 
	 * TN_CNTRWK_DTL.CONFM_DT, 
	 * 공사상세정보.승인_일시
	 */
	@XmlElement
	private java.util.Date CONFM_DT;

	/** 
	 * TN_CNTRWK_DTL.CONFMER_NO, 
	 * 공사상세정보.승인자_번호
	 */
	@XmlElement
	private java.lang.String CONFMER_NO;

	/** 
	 * TN_CNTRWK_DTL.EXTRL_REGIST_AT, 
	 * 공사상세정보.외부_등록_여부
	 */
	@XmlElement
	private java.lang.String EXTRL_REGIST_AT;

	/** 
	 * TN_CNTRWK_DTL.USE_AT, 
	 * 공사상세정보.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_CNTRWK_DTL.DELETE_AT, 
	 * 공사상세정보.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_CNTRWK_DTL.CRTR_NO, 
	 * 공사상세정보.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_CNTRWK_DTL.CREAT_DT, 
	 * 공사상세정보.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_CNTRWK_DTL.UPDUSR_NO, 
	 * 공사상세정보.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_CNTRWK_DTL.UPDT_DT, 
	 * 공사상세정보.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/** 
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_CODE, 
	 * 공사상세정보.포장_재료_표층_코드
	 */
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_CODE;

	/** 
	 * TN_CNTRWK_DTL.PAINT_MSRC, 
	 * 공사상세정보.차선_도색_공법
	 */
	@XmlElement
	private java.lang.String PAINT_MSRC;

	/** 
	 * TN_CNTRWK_DTL.PAINT_YLWSLLN_AR, 
	 * 공사상세정보.차선_도색_황색실선_면적
	 */
	@XmlElement
	private java.lang.String PAINT_YLWSLLN_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_YLWDASHLN_AR, 
	 * 공사상세정보.차선_도색_황색파선_면적
	 */
	@XmlElement
	private java.lang.String PAINT_YLWDASHLN_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_WHSLLN_AR, 
	 * 공사상세정보.차선_도색_백색실선_면적
	 */
	@XmlElement
	private java.lang.String PAINT_WHSLLN_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_CRSLK_CO, 
	 * 공사상세정보.차선_도색_횡단보도_개수
	 */
	@XmlElement
	private java.lang.String PAINT_CRSLK_CO;

	/** 
	 * TN_CNTRWK_DTL.PAINT_CRSLK_AR, 
	 * 공사상세정보.차선_도색_횡단보도_면적
	 */
	@XmlElement
	private java.lang.String PAINT_CRSLK_AR;
	/** 
	 * TN_CNTRWK_DTL.PAINT_STOPLN_CO, 
	 * 공사상세정보.차선_도색_정지선_개수
	 */
	@XmlElement
	private java.lang.String PAINT_STOPLN_CO;

	/** 
	 * TN_CNTRWK_DTL.PAINT_STOPLN_AR, 
	 * 공사상세정보.차선_도색_정지선_면적
	 */
	@XmlElement
	private java.lang.String PAINT_STOPLN_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_CO, 
	 * 공사상세정보.차선_도색_방지턱_개수
	 */
	@XmlElement
	private java.lang.String PAINT_SPDBMP_CO;

	/** 
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_AR, 
	 * 공사상세정보.차선_도색_방지턱_면적
	 */
	@XmlElement
	private java.lang.String PAINT_SPDBMP_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_WHDASHLN_AR, 
	 * 공사상세정보.차선_도색_백색파선_면적
	 */
	@XmlElement
	private java.lang.String PAINT_WHDASHLN_AR;

	/** 
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_CO, 
	 * 공사상세정보.차선_도색_문자기호_개수
	 */
	@XmlElement
	private java.lang.String PAINT_CHRCTRSYMBL_CO;

	/** 
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_AR, 
	 * 공사상세정보.차선_도색_문자기호_면적
	 */
	@XmlElement
	private java.lang.String PAINT_CHRCTRSYMBL_AR;
	
	@XmlElement
	private java.lang.String DIRECT_POINT_NM;
	
	@XmlElement
	private java.lang.String OPERT_BFE_PHOTO_NM;

	@XmlElement
	private java.lang.String OPERT_AFT_PHOTO_NM;
	
	@XmlElement
	private java.lang.String PAV_MATRL_ASCON_NM;
	
	@XmlElement
	private java.lang.String PAV_MATRL_BASE_NM;
	
	@XmlElement
	private java.lang.String PAV_MATRL_CNTR_NM;
	
	@XmlElement
	private java.lang.String RPAIR_MTHD_NM;
	
	//통계_건수
	@XmlElement
	private java.lang.String CNT;
	
	//통계_백분율
	@XmlElement
	private java.lang.String PERC;
	
	//검색_착공일
	@XmlElement
	private java.lang.String  SCH_STRWRK_DE;
	
	//검색_준공일
	@XmlElement
	private java.lang.String  SCH_COMPET_DE;
	
	//검색_부서코드
	@XmlElement
	private java.lang.String  SCH_DEPT_CODE;
	
	//부서명
	@XmlElement
	private java.lang.String  DEPT_NM;
	
	//셀관리번호
	@XmlElement
	private java.lang.String CELL_ID;
	
	//검색_포장공법
	@XmlElement
	private java.lang.String SCH_RPAIR_MTHD;

	//검색_보수대상_선정년도
	@XmlElement
	private java.lang.String SLCTN_YEAR;
	
	//검색_공사년도
	@XmlElement
	private java.lang.String CNTRWK_YEAR;
	
	
	/**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_ID, 
	 * 공사상세정보.세부_공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_ID") 
	public java.lang.String getDETAIL_CNTRWK_ID() {
		return this.DETAIL_CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_ID, 
	 * 공사상세정보.세부_공사_ID 값설정
	 * @param detailCntrwkId
	 */
	public void setDETAIL_CNTRWK_ID(java.lang.String detailCntrwkId) {
		this.DETAIL_CNTRWK_ID = detailCntrwkId;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_ID, 
	 * 공사상세정보.공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_ID, 
	 * 공사상세정보.공사_ID 값설정
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_MTHD_CODE, 
	 * 공사상세정보.보수_공법_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MTHD_CODE") 
	public java.lang.String getRPAIR_MTHD_CODE() {
		return this.RPAIR_MTHD_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_MTHD_CODE, 
	 * 공사상세정보.보수_공법_코드 값설정
	 * @param rpairMthdCode
	 */
	public void setRPAIR_MTHD_CODE(java.lang.String rpairMthdCode) {
		this.RPAIR_MTHD_CODE = rpairMthdCode;
	}

	/**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_GROUP, 
	 * 공사상세정보.세부_공사_그룹 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_GROUP") 
	public java.lang.String getDETAIL_CNTRWK_GROUP() {
		return this.DETAIL_CNTRWK_GROUP;
	}
 
	 /**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_GROUP, 
	 * 공사상세정보.세부_공사_그룹 값설정
	 * @param detailCntrwkGroup
	 */
	public void setDETAIL_CNTRWK_GROUP(java.lang.String detailCntrwkGroup) {
		this.DETAIL_CNTRWK_GROUP = detailCntrwkGroup;
	}

	/**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_NM, 
	 * 공사상세정보.세부_공사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_NM") 
	public java.lang.String getDETAIL_CNTRWK_NM() {
		return this.DETAIL_CNTRWK_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.DETAIL_CNTRWK_NM, 
	 * 공사상세정보.세부_공사_명 값설정
	 * @param detailCntrwkNm
	 */
	public void setDETAIL_CNTRWK_NM(java.lang.String detailCntrwkNm) {
		this.DETAIL_CNTRWK_NM = detailCntrwkNm;
	}

	/**
	 * TN_CNTRWK_DTL.TRACK_LEN, 
	 * 공사상세정보.1차로_연장 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK_LEN") 
	public java.lang.String getTRACK_LEN() {
		return this.TRACK_LEN;
	}
 
	 /**
	 * TN_CNTRWK_DTL.TRACK_LEN, 
	 * 공사상세정보.1차로_연장 값설정
	 * @param trackLen
	 */
	public void setTRACK_LEN(java.lang.String trackLen) {
		this.TRACK_LEN = trackLen;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISN_CO_NO, 
	 * 공사상세정보.감리_업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_NO") 
	public java.lang.String getSPRVISN_CO_NO() {
		return this.SPRVISN_CO_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISN_CO_NO, 
	 * 공사상세정보.감리_업체_번호 값설정
	 * @param sprvisnCoNo
	 */
	public void setSPRVISN_CO_NO(java.lang.String sprvisnCoNo) {
		this.SPRVISN_CO_NO = sprvisnCoNo;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISN_CO_NM, 
	 * 공사상세정보.감리_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_NM") 
	public java.lang.String getSPRVISN_CO_NM() {
		return this.SPRVISN_CO_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISN_CO_NM, 
	 * 공사상세정보.감리_업체_명 값설정
	 * @param sprvisnCoNm
	 */
	public void setSPRVISN_CO_NM(java.lang.String sprvisnCoNm) {
		this.SPRVISN_CO_NM = sprvisnCoNm;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNT_NO, 
	 * 공사상세정보.감리_업체_대표_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_RPRSNT_NO") 
	public java.lang.String getSPRVISN_CO_RPRSNT_NO() {
		return this.SPRVISN_CO_RPRSNT_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNT_NO, 
	 * 공사상세정보.감리_업체_대표_번호 값설정
	 * @param sprvisnCoRprsntNo
	 */
	public void setSPRVISN_CO_RPRSNT_NO(java.lang.String sprvisnCoRprsntNo) {
		this.SPRVISN_CO_RPRSNT_NO = sprvisnCoRprsntNo;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사상세정보.감리_업체_대표자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_RPRSNTV_NM") 
	public java.lang.String getSPRVISN_CO_RPRSNTV_NM() {
		return this.SPRVISN_CO_RPRSNTV_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사상세정보.감리_업체_대표자_명 값설정
	 * @param sprvisnCoRprsntvNm
	 */
	public void setSPRVISN_CO_RPRSNTV_NM(java.lang.String sprvisnCoRprsntvNm) {
		this.SPRVISN_CO_RPRSNTV_NM = sprvisnCoRprsntvNm;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISOR_NM, 
	 * 공사상세정보.감리원_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISOR_NM") 
	public java.lang.String getSPRVISOR_NM() {
		return this.SPRVISOR_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISOR_NM, 
	 * 공사상세정보.감리원_명 값설정
	 * @param sprvisorNm
	 */
	public void setSPRVISOR_NM(java.lang.String sprvisorNm) {
		this.SPRVISOR_NM = sprvisorNm;
	}

	/**
	 * TN_CNTRWK_DTL.SPRVISOR_TELNO, 
	 * 공사상세정보.감리원_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISOR_TELNO") 
	public java.lang.String getSPRVISOR_TELNO() {
		return this.SPRVISOR_TELNO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPRVISOR_TELNO, 
	 * 공사상세정보.감리원_전화번호 값설정
	 * @param sprvisorTelno
	 */
	public void setSPRVISOR_TELNO(java.lang.String sprvisorTelno) {
		this.SPRVISOR_TELNO = sprvisorTelno;
	}

	/**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NO, 
	 * 공사상세정보.시공_업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_NO") 
	public java.lang.String getCNSTRCT_CO_NO() {
		return this.CNSTRCT_CO_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NO, 
	 * 공사상세정보.시공_업체_번호 값설정
	 * @param cnstrctCoNo
	 */
	public void setCNSTRCT_CO_NO(java.lang.String cnstrctCoNo) {
		this.CNSTRCT_CO_NO = cnstrctCoNo;
	}

	/**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NM, 
	 * 공사상세정보.시공_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_NM") 
	public java.lang.String getCNSTRCT_CO_NM() {
		return this.CNSTRCT_CO_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_NM, 
	 * 공사상세정보.시공_업체_명 값설정
	 * @param cnstrctCoNm
	 */
	public void setCNSTRCT_CO_NM(java.lang.String cnstrctCoNm) {
		this.CNSTRCT_CO_NM = cnstrctCoNm;
	}

	/**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사상세정보.시공_업체_대표자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_RPRSNTV_NM") 
	public java.lang.String getCNSTRCT_CO_RPRSNTV_NM() {
		return this.CNSTRCT_CO_RPRSNTV_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사상세정보.시공_업체_대표자_명 값설정
	 * @param cnstrctCoRprsntvNm
	 */
	public void setCNSTRCT_CO_RPRSNTV_NM(java.lang.String cnstrctCoRprsntvNm) {
		this.CNSTRCT_CO_RPRSNTV_NM = cnstrctCoRprsntvNm;
	}

	/**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_TELNO, 
	 * 공사상세정보.시공_업체_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_TELNO") 
	public java.lang.String getCNSTRCT_CO_TELNO() {
		return this.CNSTRCT_CO_TELNO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_TELNO, 
	 * 공사상세정보.시공_업체_전화번호 값설정
	 * @param cnstrctCoTelno
	 */
	public void setCNSTRCT_CO_TELNO(java.lang.String cnstrctCoTelno) {
		this.CNSTRCT_CO_TELNO = cnstrctCoTelno;
	}

	/**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_CHARGER_NM, 
	 * 공사상세정보.시공_업체_담당자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_CHARGER_NM") 
	public java.lang.String getCNSTRCT_CO_CHARGER_NM() {
		return this.CNSTRCT_CO_CHARGER_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNSTRCT_CO_CHARGER_NM, 
	 * 공사상세정보.시공_업체_담당자_명 값설정
	 * @param cnstrctCoChargerNm
	 */
	public void setCNSTRCT_CO_CHARGER_NM(java.lang.String cnstrctCoChargerNm) {
		this.CNSTRCT_CO_CHARGER_NM = cnstrctCoChargerNm;
	}

	/**
	 * TN_CNTRWK_DTL.SPT_HDCH_NM, 
	 * 공사상세정보.현장_소장_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPT_HDCH_NM") 
	public java.lang.String getSPT_HDCH_NM() {
		return this.SPT_HDCH_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPT_HDCH_NM, 
	 * 공사상세정보.현장_소장_명 값설정
	 * @param sptHdchNm
	 */
	public void setSPT_HDCH_NM(java.lang.String sptHdchNm) {
		this.SPT_HDCH_NM = sptHdchNm;
	}

	/**
	 * TN_CNTRWK_DTL.SPT_HDCH_TELNO, 
	 * 공사상세정보.현장_소장_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPT_HDCH_TELNO") 
	public java.lang.String getSPT_HDCH_TELNO() {
		return this.SPT_HDCH_TELNO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.SPT_HDCH_TELNO, 
	 * 공사상세정보.현장_소장_전화번호 값설정
	 * @param sptHdchTelno
	 */
	public void setSPT_HDCH_TELNO(java.lang.String sptHdchTelno) {
		this.SPT_HDCH_TELNO = sptHdchTelno;
	}

	/**
	 * TN_CNTRWK_DTL.ROAD_NM, 
	 * 공사상세정보.도로_명 값읽기
	 * @return
	 */
	@JsonProperty(value="ROAD_NM") 
	public java.lang.String getROAD_NM() {
		return this.ROAD_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.ROAD_NM, 
	 * 공사상세정보.도로_명 값설정
	 * @param roadNm
	 */
	public void setROAD_NM(java.lang.String roadNm) {
		this.ROAD_NM = roadNm;
	}

	/**
	 * TN_CNTRWK_DTL.ROUTE_CODE, 
	 * 공사상세정보.노선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.ROUTE_CODE, 
	 * 공사상세정보.노선_코드 값설정
	 * @param routeCode
	 */
	public void setROUTE_CODE(java.lang.String routeCode) {
		this.ROUTE_CODE = routeCode;
	}

	/**
	 * TN_CNTRWK_DTL.DIRECT_CODE, 
	 * 공사상세정보.행선_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DIRECT_CODE") 
	public java.lang.String getDIRECT_CODE() {
		return this.DIRECT_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.DIRECT_CODE, 
	 * 공사상세정보.행선_코드 값설정
	 * @param directCode
	 */
	public void setDIRECT_CODE(java.lang.String directCode) {
		this.DIRECT_CODE = directCode;
		
		setDIRECT_NM(this.DIRECT_CODE);
	}

	/**
	 * TN_CNTRWK_DTL.TRACK, 
	 * 공사상세정보.차로 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK") 
	public java.lang.String getTRACK() {
		return this.TRACK;
	}
 
	 /**
	 * TN_CNTRWK_DTL.TRACK, 
	 * 공사상세정보.차로 값설정
	 * @param track
	 */
	public void setTRACK(java.lang.String track) {
		this.TRACK = track;
	}

	/**
	 * TN_CNTRWK_DTL.STRTPT, 
	 * 공사상세정보.시점 값읽기
	 * @return
	 */
	@JsonProperty(value="STRTPT") 
	public java.lang.String getSTRTPT() {
		return this.STRTPT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.STRTPT, 
	 * 공사상세정보.시점 값설정
	 * @param strtpt
	 */
	public void setSTRTPT(java.lang.String strtpt) {
		this.STRTPT = strtpt;
	}

	/**
	 * TN_CNTRWK_DTL.ENDPT, 
	 * 공사상세정보.종점 값읽기
	 * @return
	 */
	@JsonProperty(value="ENDPT") 
	public java.lang.String getENDPT() {
		return this.ENDPT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.ENDPT, 
	 * 공사상세정보.종점 값설정
	 * @param endpt
	 */
	public void setENDPT(java.lang.String endpt) {
		this.ENDPT = endpt;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_STRTPT_NM, 
	 * 공사상세정보.공사_시점_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_STRTPT_NM") 
	public java.lang.String getCNTRWK_STRTPT_NM() {
		return this.CNTRWK_STRTPT_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_STRTPT_NM, 
	 * 공사상세정보.공사_시점_명 값설정
	 * @param cntrwkStrtptNm
	 */
	public void setCNTRWK_STRTPT_NM(java.lang.String cntrwkStrtptNm) {
		this.CNTRWK_STRTPT_NM = cntrwkStrtptNm;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_ENDPT_NM, 
	 * 공사상세정보.공사_종점_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ENDPT_NM") 
	public java.lang.String getCNTRWK_ENDPT_NM() {
		return this.CNTRWK_ENDPT_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_ENDPT_NM, 
	 * 공사상세정보.공사_종점_명 값설정
	 * @param cntrwkEndptNm
	 */
	public void setCNTRWK_ENDPT_NM(java.lang.String cntrwkEndptNm) {
		this.CNTRWK_ENDPT_NM = cntrwkEndptNm;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_AMOUNT, 
	 * 공사상세정보.공사_금액 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_AMOUNT") 
	public java.lang.String getCNTRWK_AMOUNT() {
		return this.CNTRWK_AMOUNT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_AMOUNT, 
	 * 공사상세정보.공사_금액 값설정
	 * @param cntrwkAmount
	 */
	public void setCNTRWK_AMOUNT(java.lang.String cntrwkAmount) {
		this.CNTRWK_AMOUNT = cntrwkAmount;
	}

	/**
	 * TN_CNTRWK_DTL.GVSLCT, 
	 * 공사상세정보.관급비 값읽기
	 * @return
	 */
	@JsonProperty(value="GVSLCT") 
	public java.lang.String getGVSLCT() {
		return this.GVSLCT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.GVSLCT, 
	 * 공사상세정보.관급비 값설정
	 * @param gvslct
	 */
	public void setGVSLCT(java.lang.String gvslct) {
		this.GVSLCT = gvslct;
	}

	/**
	 * TN_CNTRWK_DTL.OUTSRCCT, 
	 * 공사상세정보.도급비 값읽기
	 * @return
	 */
	@JsonProperty(value="OUTSRCCT") 
	public java.lang.String getOUTSRCCT() {
		return this.OUTSRCCT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.OUTSRCCT, 
	 * 공사상세정보.도급비 값설정
	 * @param outsrcct
	 */
	public void setOUTSRCCT(java.lang.String outsrcct) {
		this.OUTSRCCT = outsrcct;
	}

	/**
	 * TN_CNTRWK_DTL.ETC_RLOCATCT, 
	 * 공사상세정보.기타_이설비 값읽기
	 * @return
	 */
	@JsonProperty(value="ETC_RLOCATCT") 
	public java.lang.String getETC_RLOCATCT() {
		return this.ETC_RLOCATCT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.ETC_RLOCATCT, 
	 * 공사상세정보.기타_이설비 값설정
	 * @param etcRlocatct
	 */
	public void setETC_RLOCATCT(java.lang.String etcRlocatct) {
		this.ETC_RLOCATCT = etcRlocatct;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_TMPRT, 
	 * 공사상세정보.공사_기온 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_TMPRT") 
	public java.lang.String getCNTRWK_TMPRT() {
		return this.CNTRWK_TMPRT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_TMPRT, 
	 * 공사상세정보.공사_기온 값설정
	 * @param cntrwkTmprt
	 */
	public void setCNTRWK_TMPRT(java.lang.String cntrwkTmprt) {
		this.CNTRWK_TMPRT = cntrwkTmprt;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_WETHR, 
	 * 공사상세정보.공사_날씨 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_WETHR") 
	public java.lang.String getCNTRWK_WETHR() {
		return this.CNTRWK_WETHR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_WETHR, 
	 * 공사상세정보.공사_날씨 값설정
	 * @param cntrwkWethr
	 */
	public void setCNTRWK_WETHR(java.lang.String cntrwkWethr) {
		this.CNTRWK_WETHR = cntrwkWethr;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_TIME, 
	 * 공사상세정보.공사_시간 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_TIME") 
	public java.lang.String getCNTRWK_TIME() {
		return this.CNTRWK_TIME;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_TIME, 
	 * 공사상세정보.공사_시간 값설정
	 * @param cntrwkTime
	 */
	public void setCNTRWK_TIME(java.lang.String cntrwkTime) {
		this.CNTRWK_TIME = cntrwkTime;
	}

	/**
	 * TN_CNTRWK_DTL.CNTRWK_CL, 
	 * 공사상세정보.공사_분류 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_CL") 
	public java.lang.String getCNTRWK_CL() {
		return this.CNTRWK_CL;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CNTRWK_CL, 
	 * 공사상세정보.공사_분류 값설정
	 * @param cntrwkCl
	 */
	public void setCNTRWK_CL(java.lang.String cntrwkCl) {
		this.CNTRWK_CL = cntrwkCl;
	}

	/**
	 * TN_CNTRWK_DTL.BUS_DVR_TRACK_AT, 
	 * 공사상세정보.버스_전용_차로_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="BUS_DVR_TRACK_AT") 
	public java.lang.String getBUS_DVR_TRACK_AT() {
		return this.BUS_DVR_TRACK_AT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.BUS_DVR_TRACK_AT, 
	 * 공사상세정보.버스_전용_차로_여부 값설정
	 * @param busDvrTrackAt
	 */
	public void setBUS_DVR_TRACK_AT(java.lang.String busDvrTrackAt) {
		this.BUS_DVR_TRACK_AT = busDvrTrackAt;
	}

	/**
	 * TN_CNTRWK_DTL.BEFORE_PAV_YEAR, 
	 * 공사상세정보.직전_포장_연도 값읽기
	 * @return
	 */
	@JsonProperty(value="BEFORE_PAV_YEAR") 
	public java.lang.String getBEFORE_PAV_YEAR() {
		return this.BEFORE_PAV_YEAR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.BEFORE_PAV_YEAR, 
	 * 공사상세정보.직전_포장_연도 값설정
	 * @param beforePavYear
	 */
	public void setBEFORE_PAV_YEAR(java.lang.String beforePavYear) {
		this.BEFORE_PAV_YEAR = beforePavYear;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_BEGIN_DE, 
	 * 공사상세정보.보수_시작_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_BEGIN_DE") 
	public java.lang.String getRPAIR_BEGIN_DE() {
		return this.RPAIR_BEGIN_DE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_BEGIN_DE, 
	 * 공사상세정보.보수_시작_일자 값설정
	 * @param rpairBeginDe
	 */
	public void setRPAIR_BEGIN_DE(java.lang.String rpairBeginDe) {
		this.RPAIR_BEGIN_DE = rpairBeginDe;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_END_DE, 
	 * 공사상세정보.보수_종료_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_END_DE") 
	public java.lang.String getRPAIR_END_DE() {
		return this.RPAIR_END_DE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_END_DE, 
	 * 공사상세정보.보수_종료_일자 값설정
	 * @param rpairEndDe
	 */
	public void setRPAIR_END_DE(java.lang.String rpairEndDe) {
		this.RPAIR_END_DE = rpairEndDe;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_AR, 
	 * 공사상세정보.보수_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_AR") 
	public java.lang.String getRPAIR_AR() {
		return this.RPAIR_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_AR, 
	 * 공사상세정보.보수_면적 값설정
	 * @param rpairAr
	 */
	public void setRPAIR_AR(java.lang.String rpairAr) {
		this.RPAIR_AR = rpairAr;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_BT, 
	 * 공사상세정보.보수_폭 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_BT") 
	public java.lang.String getRPAIR_BT() {
		return this.RPAIR_BT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_BT, 
	 * 공사상세정보.보수_폭 값설정
	 * @param rpairBt
	 */
	public void setRPAIR_BT(java.lang.String rpairBt) {
		this.RPAIR_BT = rpairBt;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_STLE_CODE, 
	 * 공사상세정보.포장_형태_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_STLE_CODE") 
	public java.lang.String getPAV_STLE_CODE() {
		return this.PAV_STLE_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_STLE_CODE, 
	 * 공사상세정보.포장_형태_코드 값설정
	 * @param pavStleCode
	 */
	public void setPAV_STLE_CODE(java.lang.String pavStleCode) {
		this.PAV_STLE_CODE = pavStleCode;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MSRC_ETC, 
	 * 공사상세정보.포장_공법_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MSRC_ETC") 
	public java.lang.String getPAV_MSRC_ETC() {
		return this.PAV_MSRC_ETC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MSRC_ETC, 
	 * 공사상세정보.포장_공법_기타 값설정
	 * @param pavMsrcEtc
	 */
	public void setPAV_MSRC_ETC(java.lang.String pavMsrcEtc) {
		this.PAV_MSRC_ETC = pavMsrcEtc;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_THICK_DC, 
	 * 공사상세정보.보수_두께_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_DC") 
	public java.lang.String getRPAIR_THICK_DC() {
		return this.RPAIR_THICK_DC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_THICK_DC, 
	 * 공사상세정보.보수_두께_설명 값설정
	 * @param rpairThickDc
	 */
	public void setRPAIR_THICK_DC(java.lang.String rpairThickDc) {
		this.RPAIR_THICK_DC = rpairThickDc;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_THICK, 
	 * 공사상세정보.보수_두께 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK") 
	public java.lang.String getRPAIR_THICK() {
		return this.RPAIR_THICK;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_THICK, 
	 * 공사상세정보.보수_두께 값설정
	 * @param rpairThick
	 */
	public void setRPAIR_THICK(java.lang.String rpairThick) {
		this.RPAIR_THICK = rpairThick;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_THICK_BASE, 
	 * 공사상세정보.보수_두께_기층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_BASE") 
	public java.lang.String getRPAIR_THICK_BASE() {
		return this.RPAIR_THICK_BASE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_THICK_BASE, 
	 * 공사상세정보.보수_두께_기층 값설정
	 * @param rpairThickBase
	 */
	public void setRPAIR_THICK_BASE(java.lang.String rpairThickBase) {
		this.RPAIR_THICK_BASE = rpairThickBase;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_THICK_CNTR, 
	 * 공사상세정보.보수_두께_중간층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_CNTR") 
	public java.lang.String getRPAIR_THICK_CNTR() {
		return this.RPAIR_THICK_CNTR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_THICK_CNTR, 
	 * 공사상세정보.보수_두께_중간층 값설정
	 * @param rpairThickCntr
	 */
	public void setRPAIR_THICK_CNTR(java.lang.String rpairThickCntr) {
		this.RPAIR_THICK_CNTR = rpairThickCntr;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_THICK_ASCON, 
	 * 공사상세정보.보수_두께_표층 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_ASCON") 
	public java.lang.String getRPAIR_THICK_ASCON() {
		return this.RPAIR_THICK_ASCON;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_THICK_ASCON, 
	 * 공사상세정보.보수_두께_표층 값설정
	 * @param rpairThickAscon
	 */
	public void setRPAIR_THICK_ASCON(java.lang.String rpairThickAscon) {
		this.RPAIR_THICK_ASCON = rpairThickAscon;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_ETC, 
	 * 공사상세정보.포장_재료_표층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_ASCON_ETC") 
	public java.lang.String getPAV_MATRL_ASCON_ETC() {
		return this.PAV_MATRL_ASCON_ETC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_ETC, 
	 * 공사상세정보.포장_재료_표층_기타 값설정
	 * @param pavMatrlAsconEtc
	 */
	public void setPAV_MATRL_ASCON_ETC(java.lang.String pavMatrlAsconEtc) {
		this.PAV_MATRL_ASCON_ETC = pavMatrlAsconEtc;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_CODE, 
	 * 공사상세정보.포장_재료_기층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_BASE_CODE") 
	public java.lang.String getPAV_MATRL_BASE_CODE() {
		return this.PAV_MATRL_BASE_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_CODE, 
	 * 공사상세정보.포장_재료_기층_코드 값설정
	 * @param pavMatrlBaseCode
	 */
	public void setPAV_MATRL_BASE_CODE(java.lang.String pavMatrlBaseCode) {
		this.PAV_MATRL_BASE_CODE = pavMatrlBaseCode;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_ETC, 
	 * 공사상세정보.포장_재료_기층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_BASE_ETC") 
	public java.lang.String getPAV_MATRL_BASE_ETC() {
		return this.PAV_MATRL_BASE_ETC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_BASE_ETC, 
	 * 공사상세정보.포장_재료_기층_기타 값설정
	 * @param pavMatrlBaseEtc
	 */
	public void setPAV_MATRL_BASE_ETC(java.lang.String pavMatrlBaseEtc) {
		this.PAV_MATRL_BASE_ETC = pavMatrlBaseEtc;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_CODE, 
	 * 공사상세정보.포장_재료_중간층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_CNTR_CODE") 
	public java.lang.String getPAV_MATRL_CNTR_CODE() {
		return this.PAV_MATRL_CNTR_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_CODE, 
	 * 공사상세정보.포장_재료_중간층_코드 값설정
	 * @param pavMatrlCntrCode
	 */
	public void setPAV_MATRL_CNTR_CODE(java.lang.String pavMatrlCntrCode) {
		this.PAV_MATRL_CNTR_CODE = pavMatrlCntrCode;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_ETC, 
	 * 공사상세정보.포장_재료_중간층_기타 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_CNTR_ETC") 
	public java.lang.String getPAV_MATRL_CNTR_ETC() {
		return this.PAV_MATRL_CNTR_ETC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_CNTR_ETC, 
	 * 공사상세정보.포장_재료_중간층_기타 값설정
	 * @param pavMatrlCntrEtc
	 */
	public void setPAV_MATRL_CNTR_ETC(java.lang.String pavMatrlCntrEtc) {
		this.PAV_MATRL_CNTR_ETC = pavMatrlCntrEtc;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_MATRL_DETAIL_NM, 
	 * 공사상세정보.보수_재료_세부_명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MATRL_DETAIL_NM") 
	public java.lang.String getRPAIR_MATRL_DETAIL_NM() {
		return this.RPAIR_MATRL_DETAIL_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_MATRL_DETAIL_NM, 
	 * 공사상세정보.보수_재료_세부_명 값설정
	 * @param rpairMatrlDetailNm
	 */
	public void setRPAIR_MATRL_DETAIL_NM(java.lang.String rpairMatrlDetailNm) {
		this.RPAIR_MATRL_DETAIL_NM = rpairMatrlDetailNm;
	}

	/**
	 * TN_CNTRWK_DTL.RPAIR_MATRL_PRDCT_CO_NM, 
	 * 공사상세정보.보수_재료_생산_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_MATRL_PRDCT_CO_NM") 
	public java.lang.String getRPAIR_MATRL_PRDCT_CO_NM() {
		return this.RPAIR_MATRL_PRDCT_CO_NM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RPAIR_MATRL_PRDCT_CO_NM, 
	 * 공사상세정보.보수_재료_생산_업체_명 값설정
	 * @param rpairMatrlPrdctCoNm
	 */
	public void setRPAIR_MATRL_PRDCT_CO_NM(java.lang.String rpairMatrlPrdctCoNm) {
		this.RPAIR_MATRL_PRDCT_CO_NM = rpairMatrlPrdctCoNm;
	}

	/**
	 * TN_CNTRWK_DTL.PRDCT_CO_TELNO, 
	 * 공사상세정보.생산_업체_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="PRDCT_CO_TELNO") 
	public java.lang.String getPRDCT_CO_TELNO() {
		return this.PRDCT_CO_TELNO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PRDCT_CO_TELNO, 
	 * 공사상세정보.생산_업체_전화번호 값설정
	 * @param prdctCoTelno
	 */
	public void setPRDCT_CO_TELNO(java.lang.String prdctCoTelno) {
		this.PRDCT_CO_TELNO = prdctCoTelno;
	}

	/**
	 * TN_CNTRWK_DTL.ASPAT_RNS_CRRSPND_AT, 
	 * 공사상세정보.아스팔트_실명제_해당_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="ASPAT_RNS_CRRSPND_AT") 
	public java.lang.String getASPAT_RNS_CRRSPND_AT() {
		return this.ASPAT_RNS_CRRSPND_AT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.ASPAT_RNS_CRRSPND_AT, 
	 * 공사상세정보.아스팔트_실명제_해당_여부 값설정
	 * @param aspatRnsCrrspndAt
	 */
	public void setASPAT_RNS_CRRSPND_AT(java.lang.String aspatRnsCrrspndAt) {
		this.ASPAT_RNS_CRRSPND_AT = aspatRnsCrrspndAt;
	}

	/**
	 * TN_CNTRWK_DTL.RM, 
	 * 공사상세정보.비고 값읽기
	 * @return
	 */
	@JsonProperty(value="RM") 
	public java.lang.String getRM() {
		return this.RM;
	}
 
	 /**
	 * TN_CNTRWK_DTL.RM, 
	 * 공사상세정보.비고 값설정
	 * @param rm
	 */
	public void setRM(java.lang.String rm) {
		this.RM = rm;
	}

	/**
	 * TN_CNTRWK_DTL.PROCESS_RESULT_MSSAGE, 
	 * 공사상세정보.처리_결과_메세지 값읽기
	 * @return
	 */
	@JsonProperty(value="PROCESS_RESULT_MSSAGE") 
	public java.lang.String getPROCESS_RESULT_MSSAGE() {
		return this.PROCESS_RESULT_MSSAGE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PROCESS_RESULT_MSSAGE, 
	 * 공사상세정보.처리_결과_메세지 값설정
	 * @param processResultMssage
	 */
	public void setPROCESS_RESULT_MSSAGE(java.lang.String processResultMssage) {
		this.PROCESS_RESULT_MSSAGE = processResultMssage;
	}

	/**
	 * TN_CNTRWK_DTL.OPERT_BFE_PHOTO_NO, 
	 * 공사상세정보.작업_전_사진_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="OPERT_BFE_PHOTO_NO") 
	public java.lang.String getOPERT_BFE_PHOTO_NO() {
		return this.OPERT_BFE_PHOTO_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.OPERT_BFE_PHOTO_NO, 
	 * 공사상세정보.작업_전_사진_번호 값설정
	 * @param opertBfePhotoNo
	 */
	public void setOPERT_BFE_PHOTO_NO(java.lang.String opertBfePhotoNo) {
		this.OPERT_BFE_PHOTO_NO = opertBfePhotoNo;
	}

	/**
	 * TN_CNTRWK_DTL.OPERT_AFT_PHOTO_NO, 
	 * 공사상세정보.작업_후_사진_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="OPERT_AFT_PHOTO_NO") 
	public java.lang.String getOPERT_AFT_PHOTO_NO() {
		return this.OPERT_AFT_PHOTO_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.OPERT_AFT_PHOTO_NO, 
	 * 공사상세정보.작업_후_사진_번호 값설정
	 * @param opertAftPhotoNo
	 */
	public void setOPERT_AFT_PHOTO_NO(java.lang.String opertAftPhotoNo) {
		this.OPERT_AFT_PHOTO_NO = opertAftPhotoNo;
	}

	/**
	 * TN_CNTRWK_DTL.FILE_NO, 
	 * 공사상세정보.파일_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NO") 
	public java.lang.String getFILE_NO() {
		return this.FILE_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.FILE_NO, 
	 * 공사상세정보.파일_번호 값설정
	 * @param fileNo
	 */
	public void setFILE_NO(java.lang.String fileNo) {
		this.FILE_NO = fileNo;
	}

	/**
	 * TN_CNTRWK_DTL.CONFM_STTUS_CODE, 
	 * 공사상세정보.승인_상태_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="CONFM_STTUS_CODE") 
	public java.lang.String getCONFM_STTUS_CODE() {
		return this.CONFM_STTUS_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CONFM_STTUS_CODE, 
	 * 공사상세정보.승인_상태_코드 값설정
	 * @param confmSttusCode
	 */
	public void setCONFM_STTUS_CODE(java.lang.String confmSttusCode) {
		this.CONFM_STTUS_CODE = confmSttusCode;
	}

	/**
	 * TN_CNTRWK_DTL.CONFM_DT, 
	 * 공사상세정보.승인_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CONFM_DT") 
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCONFM_DT() {
		return this.CONFM_DT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CONFM_DT, 
	 * 공사상세정보.승인_일시 값설정
	 * @param confmDt
	 */
	public void setCONFM_DT(java.util.Date confmDt) {
		this.CONFM_DT = confmDt;
	}

	/**
	 * TN_CNTRWK_DTL.CONFMER_NO, 
	 * 공사상세정보.승인자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CONFMER_NO") 
	public java.lang.String getCONFMER_NO() {
		return this.CONFMER_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CONFMER_NO, 
	 * 공사상세정보.승인자_번호 값설정
	 * @param confmerNo
	 */
	public void setCONFMER_NO(java.lang.String confmerNo) {
		this.CONFMER_NO = confmerNo;
	}

	/**
	 * TN_CNTRWK_DTL.EXTRL_REGIST_AT, 
	 * 공사상세정보.외부_등록_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="EXTRL_REGIST_AT") 
	public java.lang.String getEXTRL_REGIST_AT() {
		return this.EXTRL_REGIST_AT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.EXTRL_REGIST_AT, 
	 * 공사상세정보.외부_등록_여부 값설정
	 * @param extrlRegistAt
	 */
	public void setEXTRL_REGIST_AT(java.lang.String extrlRegistAt) {
		this.EXTRL_REGIST_AT = extrlRegistAt;
	}

	/**
	 * TN_CNTRWK_DTL.USE_AT, 
	 * 공사상세정보.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.USE_AT, 
	 * 공사상세정보.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_CNTRWK_DTL.DELETE_AT, 
	 * 공사상세정보.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.DELETE_AT, 
	 * 공사상세정보.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_CNTRWK_DTL.CRTR_NO, 
	 * 공사상세정보.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CRTR_NO, 
	 * 공사상세정보.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_CNTRWK_DTL.CREAT_DT, 
	 * 공사상세정보.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.CREAT_DT, 
	 * 공사상세정보.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_CNTRWK_DTL.UPDUSR_NO, 
	 * 공사상세정보.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.UPDUSR_NO, 
	 * 공사상세정보.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_CNTRWK_DTL.UPDT_DT, 
	 * 공사상세정보.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_CNTRWK_DTL.UPDT_DT, 
	 * 공사상세정보.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_CODE, 
	 * 공사상세정보.포장_재료_표층_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="PAV_MATRL_ASCON_CODE") 
	public java.lang.String getPAV_MATRL_ASCON_CODE() {
		return this.PAV_MATRL_ASCON_CODE;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAV_MATRL_ASCON_CODE, 
	 * 공사상세정보.포장_재료_표층_코드 값설정
	 * @param pavMatrlAsconCode
	 */
	public void setPAV_MATRL_ASCON_CODE(java.lang.String pavMatrlAsconCode) {
		this.PAV_MATRL_ASCON_CODE = pavMatrlAsconCode;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_MSRC, 
	 * 공사상세정보.차선_도색_공법 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_MSRC") 
	public java.lang.String getPAINT_MSRC() {
		return this.PAINT_MSRC;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_MSRC, 
	 * 공사상세정보.차선_도색_공법 값설정
	 * @param paintMsrc
	 */
	public void setPAINT_MSRC(java.lang.String paintMsrc) {
		this.PAINT_MSRC = paintMsrc;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_YLWSLLN_AR, 
	 * 공사상세정보.차선_도색_황색실선_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_YLWSLLN_AR") 
	public java.lang.String getPAINT_YLWSLLN_AR() {
		return this.PAINT_YLWSLLN_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_YLWSLLN_AR, 
	 * 공사상세정보.차선_도색_황색실선_면적 값설정
	 * @param paintYlwsllnAr
	 */
	public void setPAINT_YLWSLLN_AR(java.lang.String paintYlwsllnAr) {
		this.PAINT_YLWSLLN_AR = paintYlwsllnAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_YLWDASHLN_AR, 
	 * 공사상세정보.차선_도색_황색파선_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_YLWDASHLN_AR") 
	public java.lang.String getPAINT_YLWDASHLN_AR() {
		return this.PAINT_YLWDASHLN_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_YLWDASHLN_AR, 
	 * 공사상세정보.차선_도색_황색파선_면적 값설정
	 * @param paintYlwdashlnAr
	 */
	public void setPAINT_YLWDASHLN_AR(java.lang.String paintYlwdashlnAr) {
		this.PAINT_YLWDASHLN_AR = paintYlwdashlnAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_WHSLLN_AR, 
	 * 공사상세정보.차선_도색_백색실선_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_WHSLLN_AR") 
	public java.lang.String getPAINT_WHSLLN_AR() {
		return this.PAINT_WHSLLN_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_WHSLLN_AR, 
	 * 공사상세정보.차선_도색_백색실선_면적 값설정
	 * @param paintWhsllnAr
	 */
	public void setPAINT_WHSLLN_AR(java.lang.String paintWhsllnAr) {
		this.PAINT_WHSLLN_AR = paintWhsllnAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_CRSLK_CO, 
	 * 공사상세정보.차선_도색_횡단보도_개수 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_CRSLK_CO") 
	public java.lang.String getPAINT_CRSLK_CO() {
		return this.PAINT_CRSLK_CO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_CRSLK_CO, 
	 * 공사상세정보.차선_도색_횡단보도_개수 값설정
	 * @param paintCrslkCo
	 */
	public void setPAINT_CRSLK_CO(java.lang.String paintCrslkCo) {
		this.PAINT_CRSLK_CO = paintCrslkCo;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_STOPLN_CO, 
	 * 공사상세정보.차선_도색_정지선_개수 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_STOPLN_CO") 
	public java.lang.String getPAINT_STOPLN_CO() {
		return this.PAINT_STOPLN_CO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_STOPLN_CO, 
	 * 공사상세정보.차선_도색_정지선_개수 값설정
	 * @param paintStoplnCo
	 */
	public void setPAINT_STOPLN_CO(java.lang.String paintStoplnCo) {
		this.PAINT_STOPLN_CO = paintStoplnCo;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_STOPLN_AR, 
	 * 공사상세정보.차선_도색_정지선_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_STOPLN_AR") 
	public java.lang.String getPAINT_STOPLN_AR() {
		return this.PAINT_STOPLN_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_STOPLN_AR, 
	 * 공사상세정보.차선_도색_정지선_면적 값설정
	 * @param paintStoplnAr
	 */
	public void setPAINT_STOPLN_AR(java.lang.String paintStoplnAr) {
		this.PAINT_STOPLN_AR = paintStoplnAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_CO, 
	 * 공사상세정보.차선_도색_방지턱_개수 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_SPDBMP_CO") 
	public java.lang.String getPAINT_SPDBMP_CO() {
		return this.PAINT_SPDBMP_CO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_CO, 
	 * 공사상세정보.차선_도색_방지턱_개수 값설정
	 * @param paintSpdbmpCo
	 */
	public void setPAINT_SPDBMP_CO(java.lang.String paintSpdbmpCo) {
		this.PAINT_SPDBMP_CO = paintSpdbmpCo;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_AR, 
	 * 공사상세정보.차선_도색_방지턱_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_SPDBMP_AR") 
	public java.lang.String getPAINT_SPDBMP_AR() {
		return this.PAINT_SPDBMP_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_SPDBMP_AR, 
	 * 공사상세정보.차선_도색_방지턱_면적 값설정
	 * @param paintSpdbmpAr
	 */
	public void setPAINT_SPDBMP_AR(java.lang.String paintSpdbmpAr) {
		this.PAINT_SPDBMP_AR = paintSpdbmpAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_WHDASHLN_AR, 
	 * 공사상세정보.차선_도색_백색파선_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_WHDASHLN_AR") 
	public java.lang.String getPAINT_WHDASHLN_AR() {
		return this.PAINT_WHDASHLN_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_WHDASHLN_AR, 
	 * 공사상세정보.차선_도색_백색파선_면적 값설정
	 * @param paintWhdashlnAr
	 */
	public void setPAINT_WHDASHLN_AR(java.lang.String paintWhdashlnAr) {
		this.PAINT_WHDASHLN_AR = paintWhdashlnAr;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_CO, 
	 * 공사상세정보.차선_도색_문자기호_개수 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_CHRCTRSYMBL_CO") 
	public java.lang.String getPAINT_CHRCTRSYMBL_CO() {
		return this.PAINT_CHRCTRSYMBL_CO;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_CO, 
	 * 공사상세정보.차선_도색_문자기호_개수 값설정
	 * @param paintChrctrsymblCo
	 */
	public void setPAINT_CHRCTRSYMBL_CO(java.lang.String paintChrctrsymblCo) {
		this.PAINT_CHRCTRSYMBL_CO = paintChrctrsymblCo;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_AR, 
	 * 공사상세정보.차선_도색_문자기호_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_CHRCTRSYMBL_AR") 
	public java.lang.String getPAINT_CHRCTRSYMBL_AR() {
		return this.PAINT_CHRCTRSYMBL_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_CHRCTRSYMBL_AR, 
	 * 공사상세정보.차선_도색_문자기호_면적 값설정
	 * @param paintChrctrsymblAr
	 */
	public void setPAINT_CHRCTRSYMBL_AR(java.lang.String paintChrctrsymblAr) {
		this.PAINT_CHRCTRSYMBL_AR = paintChrctrsymblAr;
	}

	/**
	 * @return the rOUTE_NM
	 */
	@JsonProperty(value="ROUTE_NM")
	public java.lang.String getROUTE_NM() {
		return this.ROUTE_NM;
	}

	/**
	 * @param rOUTE_NM the rOUTE_NM to set
	 */
	public void setROUTE_NM(java.lang.String rOUTE_NM) {
		this.ROUTE_NM = rOUTE_NM;
	}

	/**
	 * TN_CNTRWK_DTL.PAINT_CRSLK_AR, 
	 * 공사상세정보.차선_도색_횡단보도_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="PAINT_CRSLK_AR") 
	public java.lang.String getPAINT_CRSLK_AR() {
		return this.PAINT_CRSLK_AR;
	}
 
	 /**
	 * TN_CNTRWK_DTL.PAINT_CRSLK_AR, 
	 * 공사상세정보.차선_도색_횡단보도_면적 값설정
	 * @param paintCrslkAr
	 */
	public void setPAINT_CRSLK_AR(java.lang.String paintCrslkAr) {
		this.PAINT_CRSLK_AR = paintCrslkAr;
	}

	@JsonProperty(value="DIRECT_POINT_NM") 
	public java.lang.String getDIRECT_POINT_NM() {
		return DIRECT_POINT_NM;
	}

	public void setDIRECT_POINT_NM(java.lang.String directPointNm) {
		this.DIRECT_POINT_NM = directPointNm;
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

	public void setOPERT_AFT_PHOTO_NM(java.lang.String oOPERT_AFT_PHOTO_NM) {
		this.OPERT_AFT_PHOTO_NM = oOPERT_AFT_PHOTO_NM;
	}

	@JsonProperty(value="PAV_MATRL_ASCON_NM") 
	public java.lang.String getPAV_MATRL_ASCON_NM() {
		return this.PAV_MATRL_ASCON_NM;
	}

	public void setPAV_MATRL_ASCON_NM(java.lang.String pAV_MATRL_ASCON_NM) {
		this.PAV_MATRL_ASCON_NM = pAV_MATRL_ASCON_NM;
	}

	@JsonProperty(value="PAV_MATRL_BASE_NM") 
	public java.lang.String getPAV_MATRL_BASE_NM() {
		return this.PAV_MATRL_BASE_NM;
	}

	public void setPAV_MATRL_BASE_NM(java.lang.String pAV_MATRL_BASE_NM) {
		this.PAV_MATRL_BASE_NM = pAV_MATRL_BASE_NM;
	}

	@JsonProperty(value="PAV_MATRL_CNTR_NM") 
	public java.lang.String getPAV_MATRL_CNTR_NM() {
		return this.PAV_MATRL_CNTR_NM;
	}

	public void setPAV_MATRL_CNTR_NM(java.lang.String pAV_MATRL_CNTR_NM) {
		this.PAV_MATRL_CNTR_NM = pAV_MATRL_CNTR_NM;
	}

	@JsonProperty(value="RPAIR_MTHD_NM")
	public java.lang.String getRPAIR_MTHD_NM() {
		return this.RPAIR_MTHD_NM;
	}

	public void setRPAIR_MTHD_NM(java.lang.String rPAIR_MTHD_NM) {
		this.RPAIR_MTHD_NM = rPAIR_MTHD_NM;
	}

	@JsonProperty(value="CNT")
	public java.lang.String getCNT() {
		return this.CNT;
	}

	public void setCNT(java.lang.String cNT) {
		this.CNT = cNT;
	}

	@JsonProperty(value="PERC")
	public java.lang.String getPERC() {
		return this.PERC;
	}

	public void setPERC(java.lang.String pERC) {
		this.PERC = pERC;
	}

	@JsonProperty(value="SCH_STRWRK_DE")
	public java.lang.String getSCH_STRWRK_DE() {
		return this.SCH_STRWRK_DE;
	}

	public void setSCH_STRWRK_DE(java.lang.String sCH_STRWRK_DE) {
		this.SCH_STRWRK_DE = sCH_STRWRK_DE;
	}

	@JsonProperty(value="SCH_COMPET_DE")
	public java.lang.String getSCH_COMPET_DE() {
		return this.SCH_COMPET_DE;
	}

	public void setSCH_COMPET_DE(java.lang.String sCH_COMPET_DE) {
		this.SCH_COMPET_DE = sCH_COMPET_DE;
	}

	@JsonProperty(value="DEPT_NM")
	public java.lang.String getDEPT_NM() {
		return this.DEPT_NM;
	}

	public void setDEPT_NM(java.lang.String dEPT_NM) {
		this.DEPT_NM = dEPT_NM;
	}

	@JsonProperty(value="SCH_DEPT_CODE")
	public java.lang.String getSCH_DEPT_CODE() {
		return this.SCH_DEPT_CODE;
	}

	public void setSCH_DEPT_CODE(java.lang.String sCH_DEPT_CODE) {
		this.SCH_DEPT_CODE = sCH_DEPT_CODE;
	}

	@JsonProperty(value="CELL_ID")
	public java.lang.String getCELL_ID() {
		return this.CELL_ID;
	}

	public void setCELL_ID(java.lang.String cELL_ID) {
		this.CELL_ID = cELL_ID;
	}

	@JsonProperty(value="SCH_RPAIR_MTHD")
	public java.lang.String getSCH_RPAIR_MTHD() {
		return this.SCH_RPAIR_MTHD;
	}

	public void setSCH_RPAIR_MTHD(java.lang.String sCH_RPAIR_MTHD) {
		this.SCH_RPAIR_MTHD = sCH_RPAIR_MTHD;
	}

	@JsonProperty(value="SLCTN_YEAR")
	public java.lang.String getSLCTN_YEAR() {
		return SLCTN_YEAR;
	}

	public void setSLCTN_YEAR(java.lang.String sLCTN_YEAR) {
		SLCTN_YEAR = sLCTN_YEAR;
	}

	@JsonProperty(value="CNTRWK_YEAR")
	public java.lang.String getCNTRWK_YEAR() {
		return CNTRWK_YEAR;
	}

	public void setCNTRWK_YEAR(java.lang.String cNTRWK_YEAR) {
		CNTRWK_YEAR = cNTRWK_YEAR;
	}
	
	
}
