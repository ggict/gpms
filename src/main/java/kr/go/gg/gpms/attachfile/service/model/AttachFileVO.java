
package kr.go.gg.gpms.attachfile.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 공통첨부파일
 *
 * @Class Name : AttachFileVO.java
 * @Description : AttachFile VO class
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
public class AttachFileVO extends BaseVO {

	public AttachFileVO() {
		super();
	}
	
	/** 
	 * TN_ATTACH_FILE.FILE_NO, 
	 * 공통첨부파일.파일_번호
	 */
	@XmlElement
	private String FILE_NO;

	/** 
	 * TN_ATTACH_FILE.FILE_NM, 
	 * 공통첨부파일.파일_명
	 */
	@XmlElement
	private java.lang.String FILE_NM;

	/** 
	 * TN_ATTACH_FILE.ORGINL_FILE_NM, 
	 * 공통첨부파일.원본_파일_명
	 */
	@XmlElement
	private java.lang.String ORGINL_FILE_NM;

	/** 
	 * TN_ATTACH_FILE.FILE_COURS, 
	 * 공통첨부파일.파일_경로
	 */
	@XmlElement
	private java.lang.String FILE_COURS;

	/** 
	 * TN_ATTACH_FILE.FILE_SIZE, 
	 * 공통첨부파일.파일_크기
	 */
	@XmlElement
	private String FILE_SIZE;

	/** 
	 * TN_ATTACH_FILE.USE_AT, 
	 * 공통첨부파일.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/** 
	 * TN_ATTACH_FILE.DELETE_AT, 
	 * 공통첨부파일.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/** 
	 * TN_ATTACH_FILE.CRTR_NO, 
	 * 공통첨부파일.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/** 
	 * TN_ATTACH_FILE.CREAT_DT, 
	 * 공통첨부파일.생성_일시
	 */
	@XmlElement
	private java.sql.Date CREAT_DT;

	/** 
	 * TN_ATTACH_FILE.UPDUSR_NO, 
	 * 공통첨부파일.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/** 
	 * TN_ATTACH_FILE.UPDT_DT, 
	 * 공통첨부파일.수정_일시
	 */
	@XmlElement
	private java.sql.Date UPDT_DT;
	
	//파일번호
	@XmlElement
	private String FILE_NUM;
	
	/**
	 * TN_ATTACH_FILE.FILE_NO, 
	 * 공통첨부파일.파일_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NO") 
	public String getFILE_NO() {
		return this.FILE_NO;
	}
 
	 /**
	 * TN_ATTACH_FILE.FILE_NO, 
	 * 공통첨부파일.파일_번호 값설정
	 * @param fileNo
	 */
	public void setFILE_NO(String fileNo) {
		this.FILE_NO = fileNo;
	}

	/**
	 * TN_ATTACH_FILE.FILE_NM, 
	 * 공통첨부파일.파일_명 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_NM") 
	public java.lang.String getFILE_NM() {
		return this.FILE_NM;
	}
 
	 /**
	 * TN_ATTACH_FILE.FILE_NM, 
	 * 공통첨부파일.파일_명 값설정
	 * @param fileNm
	 */
	public void setFILE_NM(java.lang.String fileNm) {
		this.FILE_NM = fileNm;
	}

	/**
	 * TN_ATTACH_FILE.ORGINL_FILE_NM, 
	 * 공통첨부파일.원본_파일_명 값읽기
	 * @return
	 */
	@JsonProperty(value="ORGINL_FILE_NM") 
	public java.lang.String getORGINL_FILE_NM() {
		return this.ORGINL_FILE_NM;
	}
 
	 /**
	 * TN_ATTACH_FILE.ORGINL_FILE_NM, 
	 * 공통첨부파일.원본_파일_명 값설정
	 * @param orginlFileNm
	 */
	public void setORGINL_FILE_NM(java.lang.String orginlFileNm) {
		this.ORGINL_FILE_NM = orginlFileNm;
	}

	/**
	 * TN_ATTACH_FILE.FILE_COURS, 
	 * 공통첨부파일.파일_경로 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_COURS") 
	public java.lang.String getFILE_COURS() {
		return this.FILE_COURS;
	}
 
	 /**
	 * TN_ATTACH_FILE.FILE_COURS, 
	 * 공통첨부파일.파일_경로 값설정
	 * @param fileCours
	 */
	public void setFILE_COURS(java.lang.String fileCours) {
		this.FILE_COURS = fileCours;
	}

	/**
	 * TN_ATTACH_FILE.FILE_SIZE, 
	 * 공통첨부파일.파일_크기 값읽기
	 * @return
	 */
	@JsonProperty(value="FILE_SIZE") 
	public String getFILE_SIZE() {
		return this.FILE_SIZE;
	}
 
	 /**
	 * TN_ATTACH_FILE.FILE_SIZE, 
	 * 공통첨부파일.파일_크기 값설정
	 * @param fileMg
	 */
	public void setFILE_SIZE(String fileMg) {
		this.FILE_SIZE = fileMg;
	}

	/**
	 * TN_ATTACH_FILE.USE_AT, 
	 * 공통첨부파일.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT") 
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}
 
	 /**
	 * TN_ATTACH_FILE.USE_AT, 
	 * 공통첨부파일.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_ATTACH_FILE.DELETE_AT, 
	 * 공통첨부파일.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT") 
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}
 
	 /**
	 * TN_ATTACH_FILE.DELETE_AT, 
	 * 공통첨부파일.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_ATTACH_FILE.CRTR_NO, 
	 * 공통첨부파일.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO") 
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}
 
	 /**
	 * TN_ATTACH_FILE.CRTR_NO, 
	 * 공통첨부파일.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_ATTACH_FILE.CREAT_DT, 
	 * 공통첨부파일.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT") 
	public java.sql.Date getCREAT_DT() {
		return this.CREAT_DT;
	}
 
	 /**
	 * TN_ATTACH_FILE.CREAT_DT, 
	 * 공통첨부파일.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.sql.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_ATTACH_FILE.UPDUSR_NO, 
	 * 공통첨부파일.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO") 
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}
 
	 /**
	 * TN_ATTACH_FILE.UPDUSR_NO, 
	 * 공통첨부파일.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_ATTACH_FILE.UPDT_DT, 
	 * 공통첨부파일.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT") 
	public java.sql.Date getUPDT_DT() {
		return this.UPDT_DT;
	}
 
	 /**
	 * TN_ATTACH_FILE.UPDT_DT, 
	 * 공통첨부파일.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.sql.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	@JsonProperty(value="FILE_NUM") 
	public String getFILE_NUM() {
		return this.FILE_NUM;
	}

	public void setFILE_NUM(String fILE_NUM) {
		this.FILE_NUM = fILE_NUM;
	}
}
