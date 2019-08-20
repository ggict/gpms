package kr.go.gg.gpms.srvydtaexcel.service.model;

import javax.xml.bind.annotation.XmlElement;

import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name : SrvyDtaExcelDefaultVO.java
 * @Description : SrvyDtaExcel Default VO class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SrvyDtaExcelDefaultVO extends PavFrmulaVO {

    /** 성공 개수 */
	@XmlElement
    private String SUCCESS_CNT;
    /** 실패 개수 */
	@XmlElement
    private String FAIL_CNT;
    /** 자료조사 파일명 */
	@XmlElement
    private String FILE_NM;
    /** 사용자명 */
	@XmlElement
    private String USER_NM;
    /** 로그 처리 상태 */
	@XmlElement
    private String PROCESS_STTUS;
	/** 로그 메세지 */
	@XmlElement
	private String LOG_MSSAGE;
    
  
	@JsonProperty(value="SUCCESS_CNT") 
	public String getSUCCESS_CNT() {
		return SUCCESS_CNT;
	}

	public void setSUCCESS_CNT(java.lang.String sUCCESS_CNT) {
		this.SUCCESS_CNT = sUCCESS_CNT;
	}

	@JsonProperty(value="FAIL_CNT") 
	public String getFAIL_CNT() {
		return FAIL_CNT;
	}

	public void setFAIL_CNT(java.lang.String fAIL_CNT) {
		this.FAIL_CNT = fAIL_CNT;
	}
	
	@JsonProperty(value="FILE_NM") 
	public String getFILE_NM() {
		return FILE_NM;
	}

	public void setFILE_NM(String fILE_NM) {
		this.FILE_NM = fILE_NM;
	}

	@JsonProperty(value="USER_NM") 
	public String getUSER_NM() {
		return USER_NM;
	}

	public void setUSER_NM(String uSER_NM) {
		this.USER_NM = uSER_NM;
	}

	@JsonProperty(value="PROCESS_STTUS") 
	public String getPROCESS_STTUS() {
		return PROCESS_STTUS;
	}

	public void setPROCESS_STTUS(String pROCESS_STTUS) {
		this.PROCESS_STTUS = pROCESS_STTUS;
	}
	
	@JsonProperty(value="LOG_MSSAGE") 
	public String getLOG_MSSAGE() {
		return LOG_MSSAGE;
	}

	public void setLOG_MSSAGE(String lOG_MSSAGE) {
		this.LOG_MSSAGE = lOG_MSSAGE;
	}

	
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
