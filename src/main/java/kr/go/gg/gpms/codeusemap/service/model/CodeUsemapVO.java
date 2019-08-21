
package kr.go.gg.gpms.codeusemap.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 코드사용맵
 *
 * @Class Name : CodeUsemapVO.java
 * @Description : CodeUsemap VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-03-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class CodeUsemapVO extends BaseVO {

	public CodeUsemapVO() {
		super();
	}
	
	/** 
	 * TC_CODE_USEMAP.TABLE_NM, 
	 * 코드사용맵.테이블_명
	 */
	@XmlElement
	private java.lang.String TABLE_NM;
	/** 
	 * USER_TAB_COMMENTS.TABLE_COMMENTS, 
	 * 코드사용맵.테이블_설명
	 */
	@XmlElement
	private java.lang.String TABLE_COMMENTS;
	
	/** 
	 * USER_COL_COMMENTS.TABLE_COMMENTS, 
	 * 코드사용맵.칼럼_설명
	 */
	@XmlElement
	private java.lang.String COLUMN_COMMENTS;
	/** 
	 * TC_CODE_USEMAP.COLMN_NM, 
	 * 코드사용맵.칼럼_명
	 */
	@XmlElement
	private java.lang.String COLMN_NM;

	/** 
	 * TC_CODE_USEMAP.CL_CODE, 
	 * 코드사용맵.분류_코드
	 */
	@XmlElement
	private java.lang.String CL_CODE;
 

	/** 
	 * TC_CODE_USEMAP.USE_AT, 
	 * 코드사용맵.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TC_CODE_USEMAP.CRTR_NO, 
	 * 코드사용맵.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TC_CODE_USEMAP.CREAT_DT, 
	 * 코드사용맵.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/** 
	 * TC_CODE_USEMAP.UPDUSR_NO, 
	 * 코드사용맵.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TC_CODE_USEMAP.UPDT_DT, 
	 * 코드사용맵.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TC_CODE_USEMAP.TABLE_NM, 
	 * 코드사용맵.테이블_명 값읽기
	 * @return
	 */
	@JsonProperty(value="TABLE_NM") 
	public java.lang.String getTABLE_NM() {
		return this.TABLE_NM;
	}
 
	 /**
	 * TC_CODE_USEMAP.TABLE_NM, 
	 * 코드사용맵.테이블_명 값설정
	 * @param tableNm
	 */
	public void setTABLE_NM(java.lang.String tableNm) {
		this.TABLE_NM = tableNm;
	}

	/**
	 * TC_CODE_USEMAP.COLMN_NM, 
	 * 코드사용맵.칼럼_명 값읽기
	 * @return
	 */
	@JsonProperty(value="COLMN_NM") 
	public java.lang.String getCOLMN_NM() {
		return this.COLMN_NM;
	}
 
	 /**
	 * TC_CODE_USEMAP.COLMN_NM, 
	 * 코드사용맵.칼럼_명 값설정
	 * @param colmnNm
	 */
	public void setCOLMN_NM(java.lang.String colmnNm) {
		this.COLMN_NM = colmnNm;
	}

	/**
	 * TC_CODE_USEMAP.CL_CODE, 
	 * 코드사용맵.분류_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="CL_CODE") 
	public java.lang.String getCL_CODE() {
		return this.CL_CODE;
	}
 
	 /**
	 * TC_CODE_USEMAP.CL_CODE, 
	 * 코드사용맵.분류_코드 값설정
	 * @param clCode
	 */
	public void setCL_CODE(java.lang.String clCode) {
		this.CL_CODE = clCode;
	}
 
	/**
	 * TC_CODE_USEMAP.USE_AT, 
	 * 코드사용맵.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TC_CODE_USEMAP.USE_AT, 
	 * 코드사용맵.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TC_CODE_USEMAP.CRTR_NO, 
	 * 코드사용맵.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TC_CODE_USEMAP.CRTR_NO, 
	 * 코드사용맵.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TC_CODE_USEMAP.CREAT_DT, 
	 * 코드사용맵.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TC_CODE_USEMAP.CREAT_DT, 
	 * 코드사용맵.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TC_CODE_USEMAP.UPDUSR_NO, 
	 * 코드사용맵.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TC_CODE_USEMAP.UPDUSR_NO, 
	 * 코드사용맵.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TC_CODE_USEMAP.UPDT_DT, 
	 * 코드사용맵.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TC_CODE_USEMAP.UPDT_DT, 
	 * 코드사용맵.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}
	@JsonProperty(value="TABLE_COMMENTS") 
	public java.lang.String getTABLE_COMMENTS() {
		return TABLE_COMMENTS;
	}

	public void setTABLE_COMMENTS(java.lang.String tABLE_COMMENTS) {
		TABLE_COMMENTS = tABLE_COMMENTS;
	}
	@JsonProperty(value="COLUMN_COMMENTS") 
	public java.lang.String getCOLUMN_COMMENTS() {
		return COLUMN_COMMENTS;
	}

	public void setCOLUMN_COMMENTS(java.lang.String cOLUMN_COMMENTS) {
		COLUMN_COMMENTS = cOLUMN_COMMENTS;
	}

}
