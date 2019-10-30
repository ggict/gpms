
package kr.go.gg.gpms.pavfrmula.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
import kr.go.gg.gpms.pavfrmulaidx.service.model.PavFrmulaIdxVO;
/**
 * 포장_수식
 *
 * @Class Name : PavFrmulaVO.java
 * @Description : PavFrmula VO class
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
public class PavFrmulaVO extends BaseVO {

	public PavFrmulaVO() {
		super();
	}
	@XmlElement
	private List<PavFrmulaIdxVO> frmulaIdxList;
	/** 
	 * TN_PAV_FRMULA.FRMULA_NO, 
	 * 포장_수식.수식_번호
	 */
	@XmlElement
	private java.lang.String FRMULA_NO;

	/** 
	 * TN_PAV_FRMULA.PARNTS_FRMULA_NO, 
	 * 포장_수식.부모_수식_번호
	 */
	@XmlElement
	private java.lang.String PARNTS_FRMULA_NO;

	/** 
	 * TN_PAV_FRMULA.FRMULA_NM, 
	 * 포장_수식.수식_명
	 */
	@XmlElement
	private java.lang.String FRMULA_NM;

	/** 
	 * TN_PAV_FRMULA.ARITH_FRMLA, 
	 * 포장_수식.산술_식
	 */
	@XmlElement
	private java.lang.String ARITH_FRMLA;

	@XmlElement
    private java.lang.String IDX_NM;
	
	@XmlElement
    private java.lang.String IDX_VAL;
    
	/** 
	 * TN_PAV_FRMULA.DC, 
	 * 포장_수식.설명
	 */
	@XmlElement
	private java.lang.String DC;

	/** 
	 * TN_PAV_FRMULA.ARITH_SN, 
	 * 포장_수식.산술_순번
	 */
	@XmlElement
	private java.lang.String ARITH_SN;

	/** 
	 * TN_PAV_FRMULA.DELETE_AT, 
	 * 포장_수식.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_PAV_FRMULA.USE_AT, 
	 * 포장_수식.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_PAV_FRMULA.CRTR_NO, 
	 * 포장_수식.생성자_번호
	 */
	@XmlElement
	private java.lang.String CRTR_NO;

	/** 
	 * TN_PAV_FRMULA.CREAT_DT, 
	 * 포장_수식.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_PAV_FRMULA.UPDUSR_NO, 
	 * 포장_수식.수정자_번호
	 */
	@XmlElement
	private java.lang.String UPDUSR_NO;

	/** 
	 * TN_PAV_FRMULA.UPDT_DT, 
	 * 포장_수식.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;

	@XmlElement
    private java.lang.String VAR;
	
	@XmlElement
    private java.lang.String VAR_INDEX;
	
	/** 
	 * 수식_구분_코드
	 */
	@XmlElement
    private java.lang.String FRMULA_SE_CODE;
	
	/**
	 * TN_PAV_FRMULA.FRMULA_NO, 
	 * 포장_수식.수식_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FRMULA_NO") 
	public java.lang.String getFRMULA_NO() {
		return this.FRMULA_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA.FRMULA_NO, 
	 * 포장_수식.수식_번호 값설정
	 * @param frmulaNo
	 */
	public void setFRMULA_NO(java.lang.String frmulaNo) {
		this.FRMULA_NO = frmulaNo;
	}

	/**
	 * TN_PAV_FRMULA.PARNTS_FRMULA_NO, 
	 * 포장_수식.부모_수식_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="PARNTS_FRMULA_NO") 
	public java.lang.String getPARNTS_FRMULA_NO() {
		return this.PARNTS_FRMULA_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA.PARNTS_FRMULA_NO, 
	 * 포장_수식.부모_수식_번호 값설정
	 * @param parntsFrmulaNo
	 */
	public void setPARNTS_FRMULA_NO(java.lang.String parntsFrmulaNo) {
		this.PARNTS_FRMULA_NO = parntsFrmulaNo;
	}

	/**
	 * TN_PAV_FRMULA.FRMULA_NM, 
	 * 포장_수식.수식_명 값읽기
	 * @return
	 */
	@JsonProperty(value="FRMULA_NM") 
	public java.lang.String getFRMULA_NM() {
		return this.FRMULA_NM;
	}
 
	 /**
	 * TN_PAV_FRMULA.FRMULA_NM, 
	 * 포장_수식.수식_명 값설정
	 * @param frmulaNm
	 */
	public void setFRMULA_NM(java.lang.String frmulaNm) {
		this.FRMULA_NM = frmulaNm;
	}

	/**
	 * TN_PAV_FRMULA.ARITH_FRMLA, 
	 * 포장_수식.산술_식 값읽기
	 * @return
	 */
	@JsonProperty(value="ARITH_FRMLA") 
	public java.lang.String getARITH_FRMLA() {
		return this.ARITH_FRMLA;
	}
 
	 /**
	 * TN_PAV_FRMULA.ARITH_FRMLA, 
	 * 포장_수식.산술_식 값설정
	 * @param arithFrmla
	 */
	public void setARITH_FRMLA(java.lang.String arithFrmla) {
		this.ARITH_FRMLA = arithFrmla;
	}

	/**
	 * TN_PAV_FRMULA.DC, 
	 * 포장_수식.설명 값읽기
	 * @return
	 */
	@JsonProperty(value="DC") 
	public java.lang.String getDC() {
		return this.DC;
	}
 
	 /**
	 * TN_PAV_FRMULA.DC, 
	 * 포장_수식.설명 값설정
	 * @param dc
	 */
	public void setDC(java.lang.String dc) {
		this.DC = dc;
	}

	/**
	 * TN_PAV_FRMULA.ARITH_SN, 
	 * 포장_수식.산술_순번 값읽기
	 * @return
	 */
	@JsonProperty(value="ARITH_SN") 
	public java.lang.String getARITH_SN() {
		return this.ARITH_SN;
	}
 
	 /**
	 * TN_PAV_FRMULA.ARITH_SN, 
	 * 포장_수식.산술_순번 값설정
	 * @param arithSn
	 */
	public void setARITH_SN(java.lang.String arithSn) {
		this.ARITH_SN = arithSn;
	}

	/**
	 * TN_PAV_FRMULA.DELETE_AT, 
	 * 포장_수식.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_PAV_FRMULA.DELETE_AT, 
	 * 포장_수식.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_PAV_FRMULA.USE_AT, 
	 * 포장_수식.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_PAV_FRMULA.USE_AT, 
	 * 포장_수식.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_PAV_FRMULA.CRTR_NO, 
	 * 포장_수식.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public java.lang.String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA.CRTR_NO, 
	 * 포장_수식.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(java.lang.String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_PAV_FRMULA.CREAT_DT, 
	 * 포장_수식.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_PAV_FRMULA.CREAT_DT, 
	 * 포장_수식.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_PAV_FRMULA.UPDUSR_NO, 
	 * 포장_수식.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public java.lang.String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_PAV_FRMULA.UPDUSR_NO, 
	 * 포장_수식.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(java.lang.String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_PAV_FRMULA.UPDT_DT, 
	 * 포장_수식.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_PAV_FRMULA.UPDT_DT, 
	 * 포장_수식.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}
	@JsonProperty(value="FrmulaIdxList")
	public List<PavFrmulaIdxVO> getFrmulaIdxList() {
		return frmulaIdxList;
	}

	public void setFrmulaIdxList(List<PavFrmulaIdxVO> frmulaIdxList) {
		this.frmulaIdxList = frmulaIdxList;
	}

	@JsonProperty(value="IDX_NM")
    public java.lang.String getIDX_NM() {
        return IDX_NM;
    }

    public void setIDX_NM(java.lang.String iDX_NM) {
        IDX_NM = iDX_NM;
    }

    @JsonProperty(value="IDX_VAL")
    public java.lang.String getIDX_VAL() {
        return IDX_VAL;
    }

    public void setIDX_VAL(java.lang.String iDX_VAL) {
        IDX_VAL = iDX_VAL;
    }

    @JsonProperty(value="VAR")
    public java.lang.String getVAR() {
        return VAR;
    }

    public void setVAR(java.lang.String vAR) {
        VAR = vAR;
    }

    @JsonProperty(value="VAR_INDEX")
    public java.lang.String getVAR_INDEX() {
        return VAR_INDEX;
    }

    public void setVAR_INDEX(java.lang.String vAR_INDEX) {
        VAR_INDEX = vAR_INDEX;
    }

    @JsonProperty(value="FRMULA_SE_CODE")
	public java.lang.String getFRMULA_SE_CODE() {
		return FRMULA_SE_CODE;
	}

	public void setFRMULA_SE_CODE(java.lang.String fRMULA_SE_CODE) {
		FRMULA_SE_CODE = fRMULA_SE_CODE;
	}
    
    
    
}
