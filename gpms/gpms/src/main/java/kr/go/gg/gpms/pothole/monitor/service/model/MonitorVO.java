
package kr.go.gg.gpms.pothole.monitor.service.model;

import java.util.ArrayList;
import java.util.Date;

import antlr.collections.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import kr.go.gg.gpms.base.model.BaseVO;
/**
 * Monitor 단원 관리
 * TN_MNTRNG_MBR
 *
 * @Class Name : MonitorVO.java
 * @Description : Monitor VO class
 * @Modification Information
 *
 * @author yyk
 * @since 2018-01-03
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

public class MonitorVO extends BaseVO  {

	public MonitorVO() {
		super();
	}

	/*차량_번호_사업자_명_리스트*/
	@XmlElement
	private ArrayList<?> VHCLE_BSNM_LIST;

	/*차량_번호*/
	@XmlElement
	private String VHCLE_NO;

	/*차량_번호_배열*/
	@XmlElement
	private String[] VHCLE_NO_ARR;

	/*부서_코드*/
	@XmlElement
	private String DEPT_CODE;

	/*사업자_명*/
	@XmlElement
	private String BSNM_NM;

	/*사업자_명_배열*/
	@XmlElement
	private String[] BSNM_NM_ARR;

	/*주소*/
	@XmlElement
	private String ADRES;

	/*연락처*/
	@XmlElement
	private String CTTPC;

	/*미터기_제작사*/
	@XmlElement
	private String METER_MAKR;

	/*모델_명*/
	@XmlElement
	private String MODEL_NM;

	/*카드_제작사*/
	@XmlElement
	private String CARD_MAKR;

	/*장착_여부*/
	@XmlElement
	private String MNTNG_AT;

	/*비고*/
	@XmlElement
	private String RM;

	/*사용_여부*/
	@XmlElement
	private String USE_AT  ;

	/*삭제_여부*/
	@XmlElement
	private String DELETE_AT;

	/*위촉_여부*/
	@XmlElement
	private String ENTRST_AT;

	/*위촉_일자*/
	@XmlElement
	private Date   ENTRST_DE;

    /*생성_일자*/
	@XmlElement
	private Date   CREAT_DE;

    /*수정_일자*/
	@XmlElement
	private Date   UPDT_DE;

	/*삭제_일자*/
	@XmlElement
	private Date   DELETE_DE;


	/*지역 정보 TC_DEPT */
	@XmlElement
	private String   LOWEST_DEPT_NM;


	/*차량_번호_배열*/
	@JsonProperty(value="VHCLE_NO_ARR")
	public String[] getVHCLE_NO_ARR() {
		return VHCLE_NO_ARR;
	}
	public void setVHCLE_NO_ARR(String[] vHCLE_NO_ARR) {
		VHCLE_NO_ARR = vHCLE_NO_ARR;
	}

	/*사업자_명_배열*/
	@JsonProperty(value="BSNM_NM_ARR")
	public String[] getBSNM_NM_ARR() {
		return BSNM_NM_ARR;
	}
	public void setBSNM_NM_ARR(String[] bSNM_NM_ARR) {
		BSNM_NM_ARR = bSNM_NM_ARR;
	}

	@JsonProperty(value="LOWEST_DEPT_NM")
	public String getLOWEST_DEPT_NM() {
		return LOWEST_DEPT_NM;
	}
	public void setLOWEST_DEPT_NM(String lOWEST_DEPT_NM) {
		LOWEST_DEPT_NM = lOWEST_DEPT_NM;
	}

	/*차량_번호*/
	@JsonProperty(value="VHCLE_NO")
	public String getVHCLE_NO() {
		return VHCLE_NO;
	}
	public void setVHCLE_NO(String vHCLE_NO) {
		VHCLE_NO = vHCLE_NO;
	}

	/* 부서_코드 */
	@JsonProperty(value="DEPT_CODE")
	public String getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}

	/* 사업자_명 */
	@JsonProperty(value="BSNM_NM")
	public String getBSNM_NM() {
		return BSNM_NM;
	}
	public void setBSNM_NM(String bSNM_NM) {
		BSNM_NM = bSNM_NM;
	}

	/* 주소 */
	@JsonProperty(value="ADRES")
	public String getADRES() {
		return ADRES;
	}
	public void setADRES(String aDRES) {
		ADRES = aDRES;
	}

	/* 연락처 */
	@JsonProperty(value="CTTPC")
	public String getCTTPC() {
		return CTTPC;
	}
	public void setCTTPC(String cTTPC) {
		CTTPC = cTTPC;
	}

	/* 미터기_제작사 */
	@JsonProperty(value="METER_MAKR")
	public String getMETER_MAKR() {
		return METER_MAKR;
	}
	public void setMETER_MAKR(String mETER_MAKR) {
		METER_MAKR = mETER_MAKR;
	}

	/* 모델_명 */
	@JsonProperty(value="MODEL_NM")
	public String getMODEL_NM() {
		return MODEL_NM;
	}
	public void setMODEL_NM(String mODEL_NM) {
		MODEL_NM = mODEL_NM;
	}

	/* 카드_제작사 */
	@JsonProperty(value="CARD_MAKR")
	public String getCARD_MAKR() {
		return CARD_MAKR;
	}
	public void setCARD_MAKR(String cARD_MAKR) {
		CARD_MAKR = cARD_MAKR;
	}

	/* 장착_여부 */
	@JsonProperty(value="MNTNG_AT")
	public String getMNTNG_AT() {
		return MNTNG_AT;
	}
	public void setMNTNG_AT(String mNTNG_AT) {
		MNTNG_AT = mNTNG_AT;
	}

	/* 비고 */
	@JsonProperty(value="RM")
	public String getRM() {
		return RM;
	}
	public void setRM(String rM) {
		RM = rM;
	}

	/* 사용_여부 */
	@JsonProperty(value="USE_AT")
	public String getUSE_AT() {
		return USE_AT;
	}
	public void setUSE_AT(String uSE_AT) {
		USE_AT = uSE_AT;
	}

	/* 삭제_여부 */
	@JsonProperty(value="DELETE_AT")
	public String getDELETE_AT() {
		return DELETE_AT;
	}
	public void setDELETE_AT(String dELETE_AT) {
		DELETE_AT = dELETE_AT;
	}

	/* 위촉_여부 */
	@JsonProperty(value="ENTRST_AT")
	public String getENTRST_AT() {
		return ENTRST_AT;
	}
	public void setENTRST_AT(String eNTRST_AT) {
		ENTRST_AT = eNTRST_AT;
	}

	/*위촉_일자*/
	@JsonProperty(value="ENTRST_DE")
	public Date getENTRST_DE() {
		return ENTRST_DE;
	}
	public void setENTRST_DE(Date eNTRST_DE) {
		ENTRST_DE = eNTRST_DE;
	}

	/* 생성_일자 */
	@JsonProperty(value="CREAT_DE")
	public Date getCREAT_DE() {
		return CREAT_DE;
	}
	public void setCREAT_DE(Date cREAT_DE) {
		CREAT_DE = cREAT_DE;
	}

	/* 수정_일자 */
	@JsonProperty(value="UPDT_DE")
	public Date getUPDT_DE() {
		return UPDT_DE;
	}
	public void setUPDT_DE(Date uPDT_DE) {
		UPDT_DE = uPDT_DE;
	}

	/* 삭제_일자 */
	@JsonProperty(value="DELETE_DE")
	public Date getDELETE_DE() {
		return DELETE_DE;
	}
	public void setDELETE_DE(Date dELETE_DE) {
		DELETE_DE = dELETE_DE;
	}

	/*차량_번호_사업자_명_리스트*/
	public ArrayList<?> getVHCLE_BSNM_LIST() {
		return VHCLE_BSNM_LIST;
	}
	public void setVHCLE_BSNM_LIST(ArrayList<?> list) {
		VHCLE_BSNM_LIST = list;
	}


}
