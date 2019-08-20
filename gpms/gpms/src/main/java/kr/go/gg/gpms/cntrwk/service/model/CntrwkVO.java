
package kr.go.gg.gpms.cntrwk.service.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 공사정보
 *
 * @Class Name : CntrwkVO.java
 * @Description : Cntrwk VO class 
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-07
 * @version 1.0
 * @see
 *  	CntrwkDefaultVO use BaseVO
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CntrwkVO extends BaseVO {

	public CntrwkVO() {
		super();
	}
	
	/** 
	 * TN_CNTRWK.CNTRWK_ID, 
	 * 공사정보.공사_ID
	 */
	@XmlElement
	private java.lang.String CNTRWK_ID;
	
	/** 
     * TN_CNTRWK.CNTRWK_ID, 
     * 공사정보.세부_공사_ID
     */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_ID;

	/** 
	 * TN_CNTRWK.FULL_CNTRWK_NM, 
	 * 공사정보.전체_공사_명
	 */
	@XmlElement
	private java.lang.String FULL_CNTRWK_NM;

	/** 
	 * TN_CNTRWK.CNTRWK_SE, 
	 * 공사정보.공사_구분
	 */
	@XmlElement
	private java.lang.String CNTRWK_SE;

	/** 
	 * TN_CNTRWK.DEPT_CODE, 
	 * 공사정보.부서_코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/** 
	 * TN_CNTRWK.DETAIL_CNTRWK_NM, 
	 * 공사정보.세부_공사_명
	 */
	@XmlElement
	private java.lang.String DETAIL_CNTRWK_NM;

	/** 
	 * TN_CNTRWK.CNTRWK_CL, 
	 * 공사정보.공사_분류
	 */
	@XmlElement
	private java.lang.String CNTRWK_CL;

	/** 
	 * TN_CNTRWK.CNTRWK_YEAR, 
	 * 공사정보.공사_연도
	 */
	@XmlElement
	private java.lang.String CNTRWK_YEAR;

	/** 
	 * TN_CNTRWK.HT_SE, 
	 * 공사정보.반기_구분
	 */
	@XmlElement
	private java.lang.String HT_SE;

	/** 
	 * TN_CNTRWK.SPRVISR_NM, 
	 * 공사정보.감독원_명
	 */
	@XmlElement
	private java.lang.String SPRVISR_NM;

	/** 
	 * TN_CNTRWK.SPRVISR_TELNO, 
	 * 공사정보.감독원_전화번호
	 */
	@XmlElement
	private java.lang.String SPRVISR_TELNO;

	/** 
	 * TN_CNTRWK.SPRVISN_CO_NO, 
	 * 공사정보.감리_업체_번호
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_NO;

	/** 
	 * TN_CNTRWK.SPRVISN_CO_NM, 
	 * 공사정보.감리_업체_명
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_NM;

	/** 
	 * TN_CNTRWK.SPRVISN_CO_RPRSNT_TELNO, 
	 * 공사정보.감리_업체_대표_전화번호
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_RPRSNT_TELNO;

	/** 
	 * TN_CNTRWK.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사정보.감리_업체_대표자_명
	 */
	@XmlElement
	private java.lang.String SPRVISN_CO_RPRSNTV_NM;

	/** 
	 * TN_CNTRWK.CNSTRCT_CO_NO, 
	 * 공사정보.시공_업체_번호
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_NO;

	/** 
	 * TN_CNTRWK.CNSTRCT_CO_NM, 
	 * 공사정보.시공_업체_명
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_NM;

	/** 
	 * TN_CNTRWK.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사정보.시공_업체_대표자_명
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_RPRSNTV_NM;

	/** 
	 * TN_CNTRWK.CNSTRCT_CO_TELNO, 
	 * 공사정보.시공_업체_전화번호
	 */
	@XmlElement
	private java.lang.String CNSTRCT_CO_TELNO;

	/** 
	 * TN_CNTRWK.SPT_AGENT_NM, 
	 * 공사정보.현장_대리인_명
	 */
	@XmlElement
	private java.lang.String SPT_AGENT_NM;

	/** 
	 * TN_CNTRWK.SPT_AGENT_TELNO, 
	 * 공사정보.현장_대리인_전화번호
	 */
	@XmlElement
	private java.lang.String SPT_AGENT_TELNO;

	/** 
	 * TN_CNTRWK.TRACK_LEN, 
	 * 공사정보.1차로_연장
	 */
	@XmlElement
	private java.lang.String TRACK_LEN;

	/** 
	 * TN_CNTRWK.STRWRK_DE, 
	 * 공사정보.착공_일자
	 */
	@XmlElement
	private java.lang.String STRWRK_DE;

	/** 
	 * TN_CNTRWK.COMPET_DE, 
	 * 공사정보.준공_일자
	 */
	@XmlElement
	private java.lang.String COMPET_DE;

	/** 
	 * TN_CNTRWK.CNTRWK_OPERT_CN, 
	 * 공사정보.공사_작업_내용
	 */
	@XmlElement
	private java.lang.String CNTRWK_OPERT_CN;

	/** 
	 * TN_CNTRWK.FLAW_BEGIN_DE, 
	 * 공사정보.하자_시작_일자
	 */
	@XmlElement
	private java.lang.String FLAW_BEGIN_DE;

	/** 
	 * TN_CNTRWK.FLAW_END_DE, 
	 * 공사정보.하자_종료_일자
	 */
	@XmlElement
	private java.lang.String FLAW_END_DE;

	/** 
	 * TN_CNTRWK.TOT_AMOUNT, 
	 * 공사정보.총_금액
	 */
	@XmlElement
	private java.lang.String TOT_AMOUNT;

	/** 
	 * TN_CNTRWK.RPAIR_THICK_DC, 
	 * 공사정보.보수_두께_설명
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK_DC;

	/** 
	 * TN_CNTRWK.RPAIR_THICK, 
	 * 공사정보.보수_두께
	 */
	@XmlElement
	private java.lang.String RPAIR_THICK;

	/** 
	 * TN_CNTRWK.RPAIR_AR, 
	 * 공사정보.보수_면적
	 */
	@XmlElement
	private java.lang.String RPAIR_AR;

	/** 
	 * TN_CNTRWK.RPAIR_BT, 
	 * 공사정보.보수_폭
	 */
	@XmlElement
	private java.lang.String RPAIR_BT;

	/** 
	 * TN_CNTRWK.USE_AT, 
	 * 공사정보.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_CNTRWK.DELETE_AT, 
	 * 공사정보.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_CNTRWK.CRTR_NO, 
	 * 공사정보.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_CNTRWK.CREAT_DT, 
	 * 공사정보.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TN_CNTRWK.UPDUSR_NO, 
	 * 공사정보.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_CNTRWK.UPDT_DT, 
	 * 공사정보.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/** 
	 * TN_CNTRWK.EXTRL_REGIST_AT, 
	 * 공사정보.외부_등록_여부
	 */
	@XmlElement
	private java.lang.String EXTRL_REGIST_AT;

	/** 
	 * TN_CNTRWK.FILE_NO, 
	 * 공사정보.파일_번호
	 */
	@XmlElement
	private java.lang.String FILE_NO;
	
	/** 공사_분류_명 */
	@XmlElement
	private java.lang.String HT_SE_NM;
	
	/** 공사_분류_명 */
	@XmlElement
	private java.lang.String CNTRWK_CL_NM;
	
	/** 공사_구분_명 */
	@XmlElement
	private java.lang.String CNTRWK_SE_NM;
	
	/** 부서_명 */
	@XmlElement
	private java.lang.String DEPT_NM;
	
	//검색 - 계약기간 시작일
	@XmlElement
	private java.lang.String SCH_STRWRK_DE;
	
	//검색 - 계약기간 종료일
	@XmlElement
	private java.lang.String SCH_COMPET_DE;
	
	//검색 - 공사명
	@XmlElement
	private java.lang.String SCH_CNTRWK_NM;
	
	//검색 - 시공업체
	@XmlElement
	private java.lang.String SCH_CO_NM;
	
	//검색 - 공사구분
	@XmlElement
	private java.lang.String SCH_CNTRWK_SE;
	
	//노선번호
	@XmlElement
	private java.lang.String ROUTE_CODE;
	
	//셀관리번호
	@XmlElement
	private java.lang.String CELL_ID;
	
	// 검색셀리스트
	private List<String> CELL_ID_ARR;
	
	// 섹션번호
	private java.lang.String OBJECT_ID;
	
	// 섹션리스트
	private List<String> OBJECT_ID_ARR;

	/**
	 * TN_CNTRWK.CNTRWK_ID, 
	 * 공사정보.공사_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_ID") 
	public java.lang.String getCNTRWK_ID() {
		return this.CNTRWK_ID;
	}
 
	 /**
	 * TN_CNTRWK.CNTRWK_ID, 
	 * 공사정보.공사_ID 값설정
	 * @param cntrwkId
	 */
	public void setCNTRWK_ID(java.lang.String cntrwkId) {
		this.CNTRWK_ID = cntrwkId;
	}

	/**
	 * TN_CNTRWK.FULL_CNTRWK_NM, 
	 * 공사정보.전체_공사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="FULL_CNTRWK_NM") 
	public java.lang.String getFULL_CNTRWK_NM() {
		return this.FULL_CNTRWK_NM;
	}
 
	 /**
	 * TN_CNTRWK.FULL_CNTRWK_NM, 
	 * 공사정보.전체_공사_명 값설정
	 * @param fullCntrwkNm
	 */
	public void setFULL_CNTRWK_NM(java.lang.String fullCntrwkNm) {
		this.FULL_CNTRWK_NM = fullCntrwkNm;
	}

	/**
	 * TN_CNTRWK.CNTRWK_SE, 
	 * 공사정보.공사_구분 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_SE") 
	public java.lang.String getCNTRWK_SE() {
		return this.CNTRWK_SE;
	}
 
	 /**
	 * TN_CNTRWK.CNTRWK_SE, 
	 * 공사정보.공사_구분 값설정
	 * @param cntrwkSe
	 */
	public void setCNTRWK_SE(java.lang.String cntrwkSe) {
		this.CNTRWK_SE = cntrwkSe;
	}

	@JsonProperty(value="DETAIL_CNTRWK_ID") 
	public java.lang.String getDETAIL_CNTRWK_ID() {
        return DETAIL_CNTRWK_ID;
    }

    public void setDETAIL_CNTRWK_ID(java.lang.String dETAIL_CNTRWK_ID) {
        DETAIL_CNTRWK_ID = dETAIL_CNTRWK_ID;
    }

    /**
	 * TN_CNTRWK.DEPT_CODE, 
	 * 공사정보.부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE") 
	public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}
 
	 /**
	 * TN_CNTRWK.DEPT_CODE, 
	 * 공사정보.부서_코드 값설정
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
	}

	/**
	 * TN_CNTRWK.DETAIL_CNTRWK_NM, 
	 * 공사정보.세부_공사_명 값읽기
	 * @return
	 */
	@JsonProperty(value="DETAIL_CNTRWK_NM") 
	public java.lang.String getDETAIL_CNTRWK_NM() {
		return this.DETAIL_CNTRWK_NM;
	}
 
	 /**
	 * TN_CNTRWK.DETAIL_CNTRWK_NM, 
	 * 공사정보.세부_공사_명 값설정
	 * @param detailCntrwkNm
	 */
	public void setDETAIL_CNTRWK_NM(java.lang.String detailCntrwkNm) {
		this.DETAIL_CNTRWK_NM = detailCntrwkNm;
	}

	/**
	 * TN_CNTRWK.CNTRWK_CL, 
	 * 공사정보.공사_분류 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_CL") 
	public java.lang.String getCNTRWK_CL() {
		return this.CNTRWK_CL;
	}
 
	 /**
	 * TN_CNTRWK.CNTRWK_CL, 
	 * 공사정보.공사_분류 값설정
	 * @param cntrwkCl
	 */
	public void setCNTRWK_CL(java.lang.String cntrwkCl) {
		this.CNTRWK_CL = cntrwkCl;
	}

	/**
	 * TN_CNTRWK.CNTRWK_YEAR, 
	 * 공사정보.공사_연도 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_YEAR") 
	public java.lang.String getCNTRWK_YEAR() {
		return this.CNTRWK_YEAR;
	}
 
	 /**
	 * TN_CNTRWK.CNTRWK_YEAR, 
	 * 공사정보.공사_연도 값설정
	 * @param cntrwkYear
	 */
	public void setCNTRWK_YEAR(java.lang.String cntrwkYear) {
		this.CNTRWK_YEAR = cntrwkYear;
	}

	/**
	 * TN_CNTRWK.HT_SE, 
	 * 공사정보.반기_구분 값읽기
	 * @return
	 */
	@JsonProperty(value="HT_SE") 
	public java.lang.String getHT_SE() {
		return this.HT_SE;
	}
 
	 /**
	 * TN_CNTRWK.HT_SE, 
	 * 공사정보.반기_구분 값설정
	 * @param htSe
	 */
	public void setHT_SE(java.lang.String htSe) {
		this.HT_SE = htSe;
	}

	/**
	 * TN_CNTRWK.SPRVISR_NM, 
	 * 공사정보.감독원_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISR_NM") 
	public java.lang.String getSPRVISR_NM() {
		return this.SPRVISR_NM;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISR_NM, 
	 * 공사정보.감독원_명 값설정
	 * @param sprvisrNm
	 */
	public void setSPRVISR_NM(java.lang.String sprvisrNm) {
		this.SPRVISR_NM = sprvisrNm;
	}

	/**
	 * TN_CNTRWK.SPRVISR_TELNO, 
	 * 공사정보.감독원_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISR_TELNO") 
	public java.lang.String getSPRVISR_TELNO() {
		return this.SPRVISR_TELNO;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISR_TELNO, 
	 * 공사정보.감독원_전화번호 값설정
	 * @param sprvisrTelno
	 */
	public void setSPRVISR_TELNO(java.lang.String sprvisrTelno) {
		this.SPRVISR_TELNO = sprvisrTelno;
	}

	/**
	 * TN_CNTRWK.SPRVISN_CO_NO, 
	 * 공사정보.감리_업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_NO") 
	public java.lang.String getSPRVISN_CO_NO() {
		return this.SPRVISN_CO_NO;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISN_CO_NO, 
	 * 공사정보.감리_업체_번호 값설정
	 * @param sprvisnCoNo
	 */
	public void setSPRVISN_CO_NO(java.lang.String sprvisnCoNo) {
		this.SPRVISN_CO_NO = sprvisnCoNo;
	}

	/**
	 * TN_CNTRWK.SPRVISN_CO_NM, 
	 * 공사정보.감리_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_NM") 
	public java.lang.String getSPRVISN_CO_NM() {
		return this.SPRVISN_CO_NM;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISN_CO_NM, 
	 * 공사정보.감리_업체_명 값설정
	 * @param sprvisnCoNm
	 */
	public void setSPRVISN_CO_NM(java.lang.String sprvisnCoNm) {
		this.SPRVISN_CO_NM = sprvisnCoNm;
	}

	/**
	 * TN_CNTRWK.SPRVISN_CO_RPRSNT_TELNO, 
	 * 공사정보.감리_업체_대표_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_RPRSNT_TELNO") 
	public java.lang.String getSPRVISN_CO_RPRSNT_TELNO() {
		return this.SPRVISN_CO_RPRSNT_TELNO;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISN_CO_RPRSNT_TELNO, 
	 * 공사정보.감리_업체_대표_전화번호 값설정
	 * @param sprvisnCoRprsntTelno
	 */
	public void setSPRVISN_CO_RPRSNT_TELNO(java.lang.String sprvisnCoRprsntTelno) {
		this.SPRVISN_CO_RPRSNT_TELNO = sprvisnCoRprsntTelno;
	}

	/**
	 * TN_CNTRWK.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사정보.감리_업체_대표자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPRVISN_CO_RPRSNTV_NM") 
	public java.lang.String getSPRVISN_CO_RPRSNTV_NM() {
		return this.SPRVISN_CO_RPRSNTV_NM;
	}
 
	 /**
	 * TN_CNTRWK.SPRVISN_CO_RPRSNTV_NM, 
	 * 공사정보.감리_업체_대표자_명 값설정
	 * @param sprvisnCoRprsntvNm
	 */
	public void setSPRVISN_CO_RPRSNTV_NM(java.lang.String sprvisnCoRprsntvNm) {
		this.SPRVISN_CO_RPRSNTV_NM = sprvisnCoRprsntvNm;
	}

	/**
	 * TN_CNTRWK.CNSTRCT_CO_NO, 
	 * 공사정보.시공_업체_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_NO") 
	public java.lang.String getCNSTRCT_CO_NO() {
		return this.CNSTRCT_CO_NO;
	}
 
	 /**
	 * TN_CNTRWK.CNSTRCT_CO_NO, 
	 * 공사정보.시공_업체_번호 값설정
	 * @param cnstrctCoNo
	 */
	public void setCNSTRCT_CO_NO(java.lang.String cnstrctCoNo) {
		this.CNSTRCT_CO_NO = cnstrctCoNo;
	}

	/**
	 * TN_CNTRWK.CNSTRCT_CO_NM, 
	 * 공사정보.시공_업체_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_NM") 
	public java.lang.String getCNSTRCT_CO_NM() {
		return this.CNSTRCT_CO_NM;
	}
 
	 /**
	 * TN_CNTRWK.CNSTRCT_CO_NM, 
	 * 공사정보.시공_업체_명 값설정
	 * @param cnstrctCoNm
	 */
	public void setCNSTRCT_CO_NM(java.lang.String cnstrctCoNm) {
		this.CNSTRCT_CO_NM = cnstrctCoNm;
	}

	/**
	 * TN_CNTRWK.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사정보.시공_업체_대표자_명 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_RPRSNTV_NM") 
	public java.lang.String getCNSTRCT_CO_RPRSNTV_NM() {
		return this.CNSTRCT_CO_RPRSNTV_NM;
	}
 
	 /**
	 * TN_CNTRWK.CNSTRCT_CO_RPRSNTV_NM, 
	 * 공사정보.시공_업체_대표자_명 값설정
	 * @param cnstrctCoRprsntvNm
	 */
	public void setCNSTRCT_CO_RPRSNTV_NM(java.lang.String cnstrctCoRprsntvNm) {
		this.CNSTRCT_CO_RPRSNTV_NM = cnstrctCoRprsntvNm;
	}

	/**
	 * TN_CNTRWK.CNSTRCT_CO_TELNO, 
	 * 공사정보.시공_업체_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CNSTRCT_CO_TELNO") 
	public java.lang.String getCNSTRCT_CO_TELNO() {
		return this.CNSTRCT_CO_TELNO;
	}
 
	 /**
	 * TN_CNTRWK.CNSTRCT_CO_TELNO, 
	 * 공사정보.시공_업체_전화번호 값설정
	 * @param cnstrctCoTelno
	 */
	public void setCNSTRCT_CO_TELNO(java.lang.String cnstrctCoTelno) {
		this.CNSTRCT_CO_TELNO = cnstrctCoTelno;
	}

	/**
	 * TN_CNTRWK.SPT_AGENT_NM, 
	 * 공사정보.현장_대리인_명 값읽기
	 * @return
	 */
	@JsonProperty(value="SPT_AGENT_NM") 
	public java.lang.String getSPT_AGENT_NM() {
		return this.SPT_AGENT_NM;
	}
 
	 /**
	 * TN_CNTRWK.SPT_AGENT_NM, 
	 * 공사정보.현장_대리인_명 값설정
	 * @param sptAgentNm
	 */
	public void setSPT_AGENT_NM(java.lang.String sptAgentNm) {
		this.SPT_AGENT_NM = sptAgentNm;
	}

	/**
	 * TN_CNTRWK.SPT_AGENT_TELNO, 
	 * 공사정보.현장_대리인_전화번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SPT_AGENT_TELNO") 
	public java.lang.String getSPT_AGENT_TELNO() {
		return this.SPT_AGENT_TELNO;
	}
 
	 /**
	 * TN_CNTRWK.SPT_AGENT_TELNO, 
	 * 공사정보.현장_대리인_전화번호 값설정
	 * @param sptAgentTelno
	 */
	public void setSPT_AGENT_TELNO(java.lang.String sptAgentTelno) {
		this.SPT_AGENT_TELNO = sptAgentTelno;
	}

	/**
	 * TN_CNTRWK.TRACK_LEN, 
	 * 공사정보.1차로_연장 값읽기
	 * @return
	 */
	@JsonProperty(value="TRACK_LEN") 
	public java.lang.String getTRACK_LEN() {
		return this.TRACK_LEN;
	}
 
	 /**
	 * TN_CNTRWK.TRACK_LEN, 
	 * 공사정보.1차로_연장 값설정
	 * @param trackLen
	 */
	public void setTRACK_LEN(java.lang.String trackLen) {
		this.TRACK_LEN = trackLen;
	}

	/**
	 * TN_CNTRWK.STRWRK_DE, 
	 * 공사정보.착공_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="STRWRK_DE") 
	public java.lang.String getSTRWRK_DE() {
		return this.STRWRK_DE;
	}
 
	 /**
	 * TN_CNTRWK.STRWRK_DE, 
	 * 공사정보.착공_일자 값설정
	 * @param strwrkDe
	 */
	public void setSTRWRK_DE(java.lang.String strwrkDe) {
		this.STRWRK_DE = strwrkDe;
	}

	/**
	 * TN_CNTRWK.COMPET_DE, 
	 * 공사정보.준공_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="COMPET_DE") 
	public java.lang.String getCOMPET_DE() {
		return this.COMPET_DE;
	}
 
	 /**
	 * TN_CNTRWK.COMPET_DE, 
	 * 공사정보.준공_일자 값설정
	 * @param competDe
	 */
	public void setCOMPET_DE(java.lang.String competDe) {
		this.COMPET_DE = competDe;
	}

	/**
	 * TN_CNTRWK.CNTRWK_OPERT_CN, 
	 * 공사정보.공사_작업_내용 값읽기
	 * @return
	 */
	@JsonProperty(value="CNTRWK_OPERT_CN") 
	public java.lang.String getCNTRWK_OPERT_CN() {
		return this.CNTRWK_OPERT_CN;
	}
 
	 /**
	 * TN_CNTRWK.CNTRWK_OPERT_CN, 
	 * 공사정보.공사_작업_내용 값설정
	 * @param cntrwkOpertCn
	 */
	public void setCNTRWK_OPERT_CN(java.lang.String cntrwkOpertCn) {
		this.CNTRWK_OPERT_CN = cntrwkOpertCn;
	}

	/**
	 * TN_CNTRWK.FLAW_BEGIN_DE, 
	 * 공사정보.하자_시작_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_BEGIN_DE") 
	public java.lang.String getFLAW_BEGIN_DE() {
		return this.FLAW_BEGIN_DE;
	}
 
	 /**
	 * TN_CNTRWK.FLAW_BEGIN_DE, 
	 * 공사정보.하자_시작_일자 값설정
	 * @param flawBeginDe
	 */
	public void setFLAW_BEGIN_DE(java.lang.String flawBeginDe) {
		this.FLAW_BEGIN_DE = flawBeginDe;
	}

	/**
	 * TN_CNTRWK.FLAW_END_DE, 
	 * 공사정보.하자_종료_일자 값읽기
	 * @return
	 */
	@JsonProperty(value="FLAW_END_DE") 
	public java.lang.String getFLAW_END_DE() {
		return this.FLAW_END_DE;
	}
 
	 /**
	 * TN_CNTRWK.FLAW_END_DE, 
	 * 공사정보.하자_종료_일자 값설정
	 * @param flawEndDe
	 */
	public void setFLAW_END_DE(java.lang.String flawEndDe) {
		this.FLAW_END_DE = flawEndDe;
	}

	/**
	 * TN_CNTRWK.TOT_AMOUNT, 
	 * 공사정보.총_금액 값읽기
	 * @return
	 */
	@JsonProperty(value="TOT_AMOUNT") 
	public java.lang.String getTOT_AMOUNT() {
		return this.TOT_AMOUNT;
	}
 
	 /**
	 * TN_CNTRWK.TOT_AMOUNT, 
	 * 공사정보.총_금액 값설정
	 * @param totAmount
	 */
	public void setTOT_AMOUNT(java.lang.String totAmount) {
		this.TOT_AMOUNT = totAmount;
	}

	/**
	 * TN_CNTRWK.RPAIR_THICK_DC, 
	 * 공사정보.보수_두께_설명 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK_DC") 
	public java.lang.String getRPAIR_THICK_DC() {
		return this.RPAIR_THICK_DC;
	}
 
	 /**
	 * TN_CNTRWK.RPAIR_THICK_DC, 
	 * 공사정보.보수_두께_설명 값설정
	 * @param rpairThickDc
	 */
	public void setRPAIR_THICK_DC(java.lang.String rpairThickDc) {
		this.RPAIR_THICK_DC = rpairThickDc;
	}

	/**
	 * TN_CNTRWK.RPAIR_THICK, 
	 * 공사정보.보수_두께 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_THICK") 
	public java.lang.String getRPAIR_THICK() {
		return this.RPAIR_THICK;
	}
 
	 /**
	 * TN_CNTRWK.RPAIR_THICK, 
	 * 공사정보.보수_두께 값설정
	 * @param rpairThick
	 */
	public void setRPAIR_THICK(java.lang.String rpairThick) {
		this.RPAIR_THICK = rpairThick;
	}

	/**
	 * TN_CNTRWK.RPAIR_AR, 
	 * 공사정보.보수_면적 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_AR") 
	public java.lang.String getRPAIR_AR() {
		return this.RPAIR_AR;
	}
 
	 /**
	 * TN_CNTRWK.RPAIR_AR, 
	 * 공사정보.보수_면적 값설정
	 * @param rpairAr
	 */
	public void setRPAIR_AR(java.lang.String rpairAr) {
		this.RPAIR_AR = rpairAr;
	}

	/**
	 * TN_CNTRWK.RPAIR_BT, 
	 * 공사정보.보수_폭 값읽기
	 * @return
	 */
	@JsonProperty(value="RPAIR_BT") 
	public java.lang.String getRPAIR_BT() {
		return this.RPAIR_BT;
	}
 
	 /**
	 * TN_CNTRWK.RPAIR_BT, 
	 * 공사정보.보수_폭 값설정
	 * @param rpairBt
	 */
	public void setRPAIR_BT(java.lang.String rpairBt) {
		this.RPAIR_BT = rpairBt;
	}

	/**
	 * TN_CNTRWK.USE_AT, 
	 * 공사정보.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_CNTRWK.USE_AT, 
	 * 공사정보.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_CNTRWK.DELETE_AT, 
	 * 공사정보.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_CNTRWK.DELETE_AT, 
	 * 공사정보.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_CNTRWK.CRTR_NO, 
	 * 공사정보.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_CNTRWK.CRTR_NO, 
	 * 공사정보.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_CNTRWK.CREAT_DT, 
	 * 공사정보.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_CNTRWK.CREAT_DT, 
	 * 공사정보.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_CNTRWK.UPDUSR_NO, 
	 * 공사정보.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_CNTRWK.UPDUSR_NO, 
	 * 공사정보.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_CNTRWK.UPDT_DT, 
	 * 공사정보.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_CNTRWK.UPDT_DT, 
	 * 공사정보.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_CNTRWK.EXTRL_REGIST_AT, 
	 * 공사정보.외부_등록_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="EXTRL_REGIST_AT") 
	public java.lang.String getEXTRL_REGIST_AT() {
		return this.EXTRL_REGIST_AT;
	}
 
	 /**
	 * TN_CNTRWK.EXTRL_REGIST_AT, 
	 * 공사정보.외부_등록_여부 값설정
	 * @param extrlRegistAt
	 */
	public void setEXTRL_REGIST_AT(java.lang.String extrlRegistAt) {
		this.EXTRL_REGIST_AT = extrlRegistAt;
	}

	/**
	 * TN_CNTRWK.FILE_NO, 
	 * 공사정보.파일_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NO") 
	public java.lang.String getFILE_NO() {
		return this.FILE_NO;
	}
 
	 /**
	 * TN_CNTRWK.FILE_NO, 
	 * 공사정보.파일_번호 값설정
	 * @param fileNo
	 */
	public void setFILE_NO(java.lang.String fileNo) {
		this.FILE_NO = fileNo;
	}
	
	/** 공사_분류_명 */
	@JsonProperty(value="HT_SE_NM") 
	public java.lang.String getHT_SE_NM() {
		return HT_SE_NM;
	}

	/** 공사_분류_명 */
	public void setHT_SE_NM(java.lang.String htSeNm) {
		this.HT_SE_NM = htSeNm;
	}

	/** 공사_분류_명 */
	@JsonProperty(value="CNTRWK_CL_NM") 
	public java.lang.String getCNTRWK_CL_NM() {
		return CNTRWK_CL_NM;
	}

	/** 공사_분류_명 */
	public void setCNTRWK_CL_NM(java.lang.String cntrwkClNm) {
		this.CNTRWK_CL_NM = cntrwkClNm;
	}

	/** 공사_구분_명 */
	@JsonProperty(value="CNTRWK_SE_NM") 
	public java.lang.String getCNTRWK_SE_NM() {
		return CNTRWK_SE_NM;
	}

	/** 공사_구분_명 */
	public void setCNTRWK_SE_NM(java.lang.String cntrwkSeNm) {
		this.CNTRWK_SE_NM = cntrwkSeNm;
	}

	/** 부서_명 */
	@JsonProperty(value="DEPT_NM") 
	public java.lang.String getDEPT_NM() {
		return DEPT_NM;
	}

	/** 부서_명 */
	public void setDEPT_NM(java.lang.String deptNm) {
		this.DEPT_NM = deptNm;
	}

	@JsonProperty(value="SCH_STRWRK_DE") 
	public java.lang.String getSCH_STRWRK_DE() {
		return SCH_STRWRK_DE;
	}

	public void setSCH_STRWRK_DE(java.lang.String sCH_STRWRK_DE) {
		this.SCH_STRWRK_DE = sCH_STRWRK_DE;
	}

	@JsonProperty(value="SCH_COMPET_DE") 
	public java.lang.String getSCH_COMPET_DE() {
		return SCH_COMPET_DE;
	}

	public void setSCH_COMPET_DE(java.lang.String sCH_COMPET_DE) {
		this.SCH_COMPET_DE = sCH_COMPET_DE;
	}

	@JsonProperty(value="SCH_CNTRWK_NM") 
	public java.lang.String getSCH_CNTRWK_NM() {
		return SCH_CNTRWK_NM;
	}

	public void setSCH_CNTRWK_NM(java.lang.String sCH_CNTRWK_NM) {
		this.SCH_CNTRWK_NM = sCH_CNTRWK_NM;
	}

	@JsonProperty(value="SCH_CO_NM") 
	public java.lang.String getSCH_CO_NM() {
		return SCH_CO_NM;
	}

	public void setSCH_CO_NM(java.lang.String sCH_CO_NM) {
		this.SCH_CO_NM = sCH_CO_NM;
	}

	@JsonProperty(value="SCH_CNTRWK_SE") 
	public java.lang.String getSCH_CNTRWK_SE() {
		return this.SCH_CNTRWK_SE;
	}

	public void setSCH_CNTRWK_SE(java.lang.String sCH_CNTRWK_SE) {
		this.SCH_CNTRWK_SE = sCH_CNTRWK_SE;
	}

	@JsonProperty(value="ROUTE_CODE") 
	public java.lang.String getROUTE_CODE() {
		return this.ROUTE_CODE;
	}

	public void setROUTE_CODE(java.lang.String rOUTE_CODE) {
		this.ROUTE_CODE = rOUTE_CODE;
	}

	@JsonProperty(value="CELL_ID") 
	public java.lang.String getCELL_ID() {
		return this.CELL_ID;
	}

	public void setCELL_ID(java.lang.String cELL_ID) {
		this.CELL_ID = cELL_ID;
	}

	@JsonProperty(value="CELL_ID_ARR") 
    public List<String> getCELL_ID_ARR() {
        return CELL_ID_ARR;
    }

    public void setCELL_ID_ARR(List<String> cELL_ID_ARR) {
        CELL_ID_ARR = cELL_ID_ARR;
    }

    @JsonProperty(value="OBJECT_ID")
    public java.lang.String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(java.lang.String oBJECT_ID) {
        OBJECT_ID = oBJECT_ID;
    }

    @JsonProperty(value="OBJECT_ID_ARR")
    public List<String> getOBJECT_ID_ARR() {
        return OBJECT_ID_ARR;
    }

    public void setOBJECT_ID_ARR(List<String> oBJECT_ID_ARR) {
        OBJECT_ID_ARR = oBJECT_ID_ARR;
    }
	
}
