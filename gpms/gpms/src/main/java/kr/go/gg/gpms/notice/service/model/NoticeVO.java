
package kr.go.gg.gpms.notice.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * 공지사항
 *
 * @Class Name : NoticeVO.java
 * @Description : Notice VO class
 * @Modification Information
 *
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@XmlRootElement
@SuppressWarnings("serial")
public class NoticeVO extends BaseVO  {

	public NoticeVO() {
		super();
	}
	
	/** 
	 * TN_NOTICE.SEQ_NO, 
	 * 공지사항.일련_번호
	 */
	@XmlElement
	private java.lang.String SEQ_NO;
	
	/** 
	 * TN_NOTICE.POS, 
	 * 공지사항.POS
	 */
	@XmlElement
	private java.lang.String POS;
	
	/** 
	 * TN_NOTICE.SUB, 
	 * 공지사항.SUB
	 */
	@XmlElement
	private java.lang.String SUB;
	
	/** 
	 * TN_NOTICE.DEP, 
	 * 공지사항.DEP
	 */
	@XmlElement
	private java.lang.String DEP;
	
	/** 
	 * TN_NOTICE.WRTER, 
	 * 공지사항.작성자
	 */
	@XmlElement
	private java.lang.String WRTER;
	
	/** 
	 * TN_NOTICE.SJ, 
	 * 공지사항.제목
	 */
	@XmlElement
	private java.lang.String SJ;
	
	/** 
	 * TN_NOTICE.CN, 
	 * 공지사항.내용
	 */
	@XmlElement
	private java.lang.String CN;
	
	/** 
	 * TN_NOTICE.RDCNT, 
	 * 공지사항.조회수
	 */
	@XmlElement
	private java.lang.String RDCNT;
	
	/** 
	 * TN_NOTICE.REGIST_DT, 
	 * 공지사항.등록_일시
	 */
	@XmlElement
	private java.lang.String REGIST_DT;
	
	/** 
	 * TN_NOTICE.FILE_NM, 
	 * 공지사항.파일_명
	 */
	@XmlElement
	private java.lang.String FILE_NM;
	
	/** 
	 * TN_NOTICE.POPUP_PD, 
	 * 공지사항.팝업_기간
	 */
	@XmlElement
	private java.lang.String POPUP_PD;
	
	/** 
	 * TN_NOTICE.ORGINL_FILE_NM, 
	 * 공지사항.원본_파일명
	 */
	@XmlElement
	private java.lang.String ORGINL_FILE_NM;
	
	/** 
	 * TN_NOTICE.ANSWER, 
	 * 공지사항.답변
	 */
	@XmlElement
	private java.lang.String ANSWER;
	
	/** 
	 * TN_NOTICE.ANSWRR, 
	 * 공지사항.답변자
	 */
	@XmlElement
	private java.lang.String ANSWRR;
	
	/** 
	 * TN_NOTICE.ANSWER_DT, 
	 * 공지사항.답변_일시
	 */
	@XmlElement
	private java.lang.String ANSWER_DT;

	//검색_제목및내용
	@XmlElement
	private java.lang.String SCH_SJCN;
	
	//검색_입력
	@XmlElement
	private java.lang.String SCH_SJCN_TXT;
	
	@JsonProperty(value="SEQ_NO")
	public java.lang.String getSEQ_NO() {
		return this.SEQ_NO;
	}

	public void setSEQ_NO(java.lang.String sEQ_NO) {
		this.SEQ_NO = sEQ_NO;
	}

	@JsonProperty(value="POS")
	public java.lang.String getPOS() {
		return this.POS;
	}

	public void setPOS(java.lang.String pOS) {
		this.POS = pOS;
	}

	@JsonProperty(value="SUB")
	public java.lang.String getSUB() {
		return this.SUB;
	}

	public void setSUB(java.lang.String sUB) {
		this.SUB = sUB;
	}

	@JsonProperty(value="DEP")
	public java.lang.String getDEP() {
		return this.DEP;
	}

	public void setDEP(java.lang.String dEP) {
		this.DEP = dEP;
	}

	@JsonProperty(value="WRTER")
	public java.lang.String getWRTER() {
		return this.WRTER;
	}

	public void setWRTER(java.lang.String wRTER) {
		this.WRTER = wRTER;
	}

	@JsonProperty(value="SJ")
	public java.lang.String getSJ() {
		return this.SJ;
	}

	public void setSJ(java.lang.String sJ) {
		this.SJ = sJ;
	}

	@JsonProperty(value="CN")
	public java.lang.String getCN() {
		return this.CN;
	}

	public void setCN(java.lang.String cN) {
		this.CN = cN;
	}

	@JsonProperty(value="RDCNT")
	public java.lang.String getRDCNT() {
		return this.RDCNT;
	}

	public void setRDCNT(java.lang.String rDCNT) {
		this.RDCNT = rDCNT;
	}

	@JsonProperty(value="REGIST_DT")
	public java.lang.String getREGIST_DT() {
		return this.REGIST_DT;
	}

	public void setREGIST_DT(java.lang.String rEGIST_DT) {
		this.REGIST_DT = rEGIST_DT;
	}

	@JsonProperty(value="FILE_NM")
	public java.lang.String getFILE_NM() {
		return this.FILE_NM;
	}

	public void setFILE_NM(java.lang.String fILE_NM) {
		this.FILE_NM = fILE_NM;
	}

	@JsonProperty(value="POPUP_PD")
	public java.lang.String getPOPUP_PD() {
		return this.POPUP_PD;
	}

	public void setPOPUP_PD(java.lang.String pOPUP_PD) {
		this.POPUP_PD = pOPUP_PD;
	}

	@JsonProperty(value="ORGINL_FILE_NM")
	public java.lang.String getORGINL_FILE_NM() {
		return this.ORGINL_FILE_NM;
	}

	public void setORGINL_FILE_NM(java.lang.String oRGINL_FILE_NM) {
		this.ORGINL_FILE_NM = oRGINL_FILE_NM;
	}

	@JsonProperty(value="ANSWER")
	public java.lang.String getANSWER() {
		return this.ANSWER;
	}

	public void setANSWER(java.lang.String aNSWER) {
		this.ANSWER = aNSWER;
	}

	@JsonProperty(value="ANSWRR")
	public java.lang.String getANSWRR() {
		return this.ANSWRR;
	}

	public void setANSWRR(java.lang.String aNSWRR) {
		this.ANSWRR = aNSWRR;
	}

	@JsonProperty(value="ANSWER_DT")
	public java.lang.String getANSWER_DT() {
		return this.ANSWER_DT;
	}

	public void setANSWER_DT(java.lang.String aNSWER_DT) {
		this.ANSWER_DT = aNSWER_DT;
	}

	@JsonProperty(value="SCH_SJCN")
	public java.lang.String getSCH_SJCN() {
		return this.SCH_SJCN;
	}

	public void setSCH_SJCN(java.lang.String sCH_SJCN) {
		this.SCH_SJCN = sCH_SJCN;
	}

	@JsonProperty(value="SCH_SJCN_TXT")
	public java.lang.String getSCH_SJCN_TXT() {
		return this.SCH_SJCN_TXT;
	}

	public void setSCH_SJCN_TXT(java.lang.String sCH_SJCN_TXT) {
		this.SCH_SJCN_TXT = sCH_SJCN_TXT;
	}
	
}
