package kr.go.gg.gpms.sysuser.service.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kr.go.gg.gpms.base.model.BaseVO;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 시스템사용자
 *
 * @Class Name : SysUserVO.java
 * @Description : SysUser VO class
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
public class SysUserVO extends BaseVO  {

	public SysUserVO() {
		super();
	}
	
	
	// 시스템 Flag
	@XmlElement
	private String sFlag;
	

	/**
	 * TN_SYS_USER.USER_NO,
	 * 시스템사용자.사용자_번호
	 */
	@XmlElement
	private String USER_NO;

	/**
	 * TN_SYS_USER.USER_NM,
	 * 시스템사용자.사용자_이름
	 */
	@XmlElement
	private java.lang.String USER_NM;

	/**
	 * TN_SYS_USER.DEPT_CODE,
	 * 시스템사용자.부서_코드
	 */
	@XmlElement
	private java.lang.String DEPT_CODE;

	/**
	 * TN_SYS_USER.USE_AT,
	 * 시스템사용자.사용_여부
	 */
	@XmlElement
	private java.lang.String USE_AT;

	/**
	 * TN_SYS_USER.SECRET_NO,
	 * 시스템사용자.비밀_번호
	 */
	@XmlElement
	private java.lang.String SECRET_NO;

	/**
	 * TN_SYS_USER.USER_ID,
	 * 시스템사용자.사용자_ID
	 */
	@XmlElement
	private java.lang.String USER_ID;

	/**
	 * TN_SYS_USER.EMAIL,
	 * 시스템사용자.이메일
	 */
	@XmlElement
	private java.lang.String EMAIL;

	/**
	 * TN_SYS_USER.CTTPC,
	 * 시스템사용자.연락처
	 */
	@XmlElement
	private java.lang.String CTTPC;

	/**
	 * TN_SYS_USER.STPLAT_AGRE_AT,
	 * 시스템사용자.약관_동의_여부
	 */
	@XmlElement
	private java.lang.String STPLAT_AGRE_AT;

	/**
	 * TN_SYS_USER.DELETE_AT,
	 * 시스템사용자.삭제_여부
	 */
	@XmlElement
	private java.lang.String DELETE_AT;

	/**
	 * TN_SYS_USER.UPDUSR_NO,
	 * 시스템사용자.수정자_번호
	 */
	@XmlElement
	private String UPDUSR_NO;

	/**
	 * 수정자_ID
	 */

	@XmlElement
	private String UPDUSR_ID;

	/**
	 * TN_SYS_USER.UPDT_DT,
	 * 시스템사용자.수정_일시
	 */
	@XmlElement
	private java.util.Date UPDT_DT;

	/**
	 * TN_SYS_USER.CRTR_NO,
	 * 시스템사용자.생성자_번호
	 */
	@XmlElement
	private String CRTR_NO;

	/**
	 * TN_SYS_USER.CREAT_DT,
	 * 시스템사용자.생성_일시
	 */
	@XmlElement
	private java.util.Date CREAT_DT;

	/**
	 * TN_SYS_USER.PASSWORD_CHANGE_DT,
	 * 시스템사용자.암호_변경_일시
	 */
	@XmlElement
	private java.util.Date PASSWORD_CHANGE_DT;

	/**
	 * TN_SYS_USER.DELETE_DT,
	 * 시스템사용자.삭제_일시
	 */
	@XmlElement
	private java.util.Date DELETE_DT;

	/**
	 * TN_SYS_USER.USER_SE_CODE,
	 * 시스템사용자.사용자_구분_코드
	 */
	@XmlElement
	private String USER_SE_CODE;

	/**
	 * TN_SYS_USER.CONFM_AT,
	 * 시스템사용자.승인_여부
	 */
	@XmlElement
	private String CONFM_AT;

	/**
	 * TN_SYS_USER.CONFM_DT,
	 * 시스템사용자.승인_일시
	 */
	@XmlElement
	private java.util.Date CONFM_DT;

	/**
	 * TN_SYS_USER.CO_NO,
	 * 시스템사용자.업체_번호
	 */
	@XmlElement
	private String CO_NO;

	/**
	 * TN_SYS_USER.STPLAT_AGRE_DT,
	 * 시스템사용자.약관_동의_일시
	 */
	@XmlElement
	private java.util.Date STPLAT_AGRE_DT;

	/**
	 * TN_SYS_USER.RM,
	 * 시스템사용자.비고
	 */
	@XmlElement
	private String RM;

	/**
	 * TN_SYS_USER.CNTRCT_NM,
	 * 시스템사용자.계약_명
	 */
	@XmlElement
	private String CNTRCT_NM;

	/**
	 * TN_SYS_USER.CNTRCT_BEGIN_DE,
	 * 시스템사용자.계약_시작_일자
	 */
	@XmlElement
	private String CNTRCT_BEGIN_DE;

	/**
	 * TN_SYS_USER.CNTRCT_END_DE,
	 * 시스템사용자.계약_종료_일자
	 */
	@XmlElement
	private String CNTRCT_END_DE;

	/**
	 * TN_SYS_USER.CHRG_MANGR_NM,
	 * 시스템사용자.담당_감독자_명
	 */
	@XmlElement
	private String CHRG_MANGR_NM;

	/**
	 * TN_SYS_USER.CNTRWK_CO_NM,
	 * 시스템사용자.공사_업체_명
	 */
	@XmlElement
	private String CNTRWK_CO_NM;

	/**
	 * TN_SYS_USER.CONFM_AUTHOR_AT,
	 * 시스템사용자.승인_권한_여부
	 */
	@XmlElement
	private java.lang.String CONFM_AUTHOR_AT;
	
	/**
	 * TN_SYS_USER.BRTHDY,
	 * 시스템사용자.생년월일
	 */
	@XmlElement
	private String BRTHDY;

	/**
	 * TN_SYS_USER.CHRG_JOB,
	 * 시스템사용자.담당_업무
	 */
	@XmlElement
	private String CHRG_JOB;

	/**
	 * TN_SYS_USER.REQ_USER_GRP,
	 * 시스템사용자.신청_사용자_그룹
	 */
	@XmlElement
	private java.lang.String REQ_USER_GRP;

	/**
	 * TN_SYS_USER.REQ_MENUACC_ROLE,
	 * 시스템사용자.신청_메뉴접근_권한
	 */
	@XmlElement
	private java.lang.String REQ_MENUACC_ROLE;


	/** 사용자_구분  */
	@XmlElement
	private String USER_SE_NM;

	/** 부서_명  */
	@XmlElement
	private String DEPT_NM;

	/** 차상위 부서 코드 **/
	@XmlElement
	private String SEHIGH_DEPT_CODE;

	/** 최상위 부서 코드 **/
	@XmlElement
	private String UPPER_SEHIGH_DEPT_CODE;

	/** 이전 암호 **/
	@XmlElement
	private String BEF_SECRET_NO;

	/** 권한 ID **/
	@XmlElement
	private String AUTHOR_ID;

	/** 권한 명 **/
	@XmlElement
	private String AUTHOR_NM;

	/** 사용자 그룹 **/
	@XmlElement
	private String USER_GRP;

	/** 메뉴접근 권한 **/
	@XmlElement
	private String MENUACC_ROLE;

	@XmlElement
	private String MENU_NM;

	@XmlElement
	private String SCH_MENU_ID;

	@XmlElement
	private String SCH_MENU_NM;

	@XmlElement
	private String SCH_STRCON_DE;

	@XmlElement
	private String SCH_ENDCON_DE;

    /*
     * 데이터 최소 년도 조회
     * */
    @XmlElement
    private java.lang.String MIN_YEAR;
    /*
     * 데이터 최소 년도 조회
     * */
    @XmlElement
    private java.lang.String MAX_YEAR;
    
    /*
     * 사용자 Flag ( C = 권한변경 신청, N = 신규사용자 신청 )
     * */
    @XmlElement
    private java.lang.String USER_FLAG;



    // 시스템 Flag
    @JsonProperty(value="sFlag")
	public String getsFlag() {
        return sFlag;
    }

    public void setsFlag(String sFlag) {
        this.sFlag = sFlag;
    }

    /**
	 * TN_SYS_USER.MIN_YEAR,
	 * 시스템사용자.데이터 최소년도
	 * @return
	 */
	@JsonProperty(value="MIN_YEAR")
	public java.lang.String getMIN_YEAR() {
		return MIN_YEAR;
	}

	public void setMIN_YEAR(java.lang.String mIN_YEAR) {
		MIN_YEAR = mIN_YEAR;
	}

	/**
	 * TN_SYS_USER.MAX_YEAR,
	 * 시스템사용자.데이터 최대년도
	 * @return
	 */
	@JsonProperty(value="MAX_YEAR")
	public java.lang.String getMAX_YEAR() {
		return MAX_YEAR;
	}

	public void setMAX_YEAR(java.lang.String mAX_YEAR) {
		MAX_YEAR = mAX_YEAR;
	}

	/**
	 * TN_SYS_USER.USER_NO,
	 * 시스템사용자.사용자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_NO")
	public String getUSER_NO() {
		return this.USER_NO;
	}

	 /**
	 * TN_SYS_USER.USER_NO,
	 * 시스템사용자.사용자_번호 값설정
	 * @param userNo
	 */
	public void setUSER_NO(String userNo) {
		this.USER_NO = userNo;
	}

	/**
	 * TN_SYS_USER.USER_NM,
	 * 시스템사용자.사용자_이름 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_NM")
	public java.lang.String getUSER_NM() {
		return this.USER_NM;
	}

	 /**
	 * TN_SYS_USER.USER_NM,
	 * 시스템사용자.사용자_이름 값설정
	 * @param userNm
	 */
	public void setUSER_NM(java.lang.String userNm) {
		this.USER_NM = userNm;
	}

	/**
	 * TN_SYS_USER.DEPT_CODE,
	 * 시스템사용자.부서_코드 값읽기
	 * @return
	 */
	@JsonProperty(value="DEPT_CODE")
	public java.lang.String getDEPT_CODE() {
		return this.DEPT_CODE;
	}

	 /**
	 * TN_SYS_USER.DEPT_CODE,
	 * 시스템사용자.부서_코드 값설정
	 * @param deptCode
	 */
	public void setDEPT_CODE(java.lang.String deptCode) {
		this.DEPT_CODE = deptCode;
	}

	/**
	 * TN_SYS_USER.USE_AT,
	 * 시스템사용자.사용_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="USE_AT")
	public java.lang.String getUSE_AT() {
		return this.USE_AT;
	}

	 /**
	 * TN_SYS_USER.USE_AT,
	 * 시스템사용자.사용_여부 값설정
	 * @param useAt
	 */
	public void setUSE_AT(java.lang.String useAt) {
		this.USE_AT = useAt;
	}

	/**
	 * TN_SYS_USER.SECRET_NO,
	 * 시스템사용자.비밀_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="SECRET_NO")
	public java.lang.String getSECRET_NO() {
		return this.SECRET_NO;
	}

	 /**
	 * TN_SYS_USER.SECRET_NO,
	 * 시스템사용자.비밀_번호 값설정
	 * @param secretNo
	 */
	public void setSECRET_NO(java.lang.String secretNo) {
		this.SECRET_NO = secretNo;
	}

	/**
	 * TN_SYS_USER.USER_ID,
	 * 시스템사용자.사용자_ID 값읽기
	 * @return
	 */
	@JsonProperty(value="USER_ID")
	public java.lang.String getUSER_ID() {
		return this.USER_ID;
	}

	 /**
	 * TN_SYS_USER.USER_ID,
	 * 시스템사용자.사용자_ID 값설정
	 * @param userId
	 */
	public void setUSER_ID(java.lang.String userId) {
		this.USER_ID = userId;
	}

	/**
	 * TN_SYS_USER.EMAIL,
	 * 시스템사용자.이메일 값읽기
	 * @return
	 */
	@JsonProperty(value="EMAIL")
	public java.lang.String getEMAIL() {
		return this.EMAIL;
	}

	 /**
	 * TN_SYS_USER.EMAIL,
	 * 시스템사용자.이메일 값설정
	 * @param email
	 */
	public void setEMAIL(java.lang.String email) {
		this.EMAIL = email;
	}

	/**
	 * TN_SYS_USER.CTTPC,
	 * 시스템사용자.연락처 값읽기
	 * @return
	 */
	@JsonProperty(value="CTTPC")
	public java.lang.String getCTTPC() {
		return this.CTTPC;
	}

	 /**
	 * TN_SYS_USER.CTTPC,
	 * 시스템사용자.연락처 값설정
	 * @param cttpc
	 */
	public void setCTTPC(java.lang.String cttpc) {
		this.CTTPC = cttpc;
	}

	/**
	 * TN_SYS_USER.STPLAT_AGRE_AT,
	 * 시스템사용자.약관_동의_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="STPLAT_AGRE_AT")
	public java.lang.String getSTPLAT_AGRE_AT() {
		return this.STPLAT_AGRE_AT;
	}

	 /**
	 * TN_SYS_USER.STPLAT_AGRE_AT,
	 * 시스템사용자.약관_동의_여부 값설정
	 * @param stplatAgreAt
	 */
	public void setSTPLAT_AGRE_AT(java.lang.String stplatAgreAt) {
		this.STPLAT_AGRE_AT = stplatAgreAt;
	}

	/**
	 * TN_SYS_USER.DELETE_AT,
	 * 시스템사용자.삭제_여부 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_AT")
	public java.lang.String getDELETE_AT() {
		return this.DELETE_AT;
	}

	 /**
	 * TN_SYS_USER.DELETE_AT,
	 * 시스템사용자.삭제_여부 값설정
	 * @param deleteAt
	 */
	public void setDELETE_AT(java.lang.String deleteAt) {
		this.DELETE_AT = deleteAt;
	}

	/**
	 * TN_SYS_USER.UPDUSR_NO,
	 * 시스템사용자.수정자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDUSR_NO")
	public String getUPDUSR_NO() {
		return this.UPDUSR_NO;
	}

	 /**
	 * TN_SYS_USER.UPDUSR_NO,
	 * 시스템사용자.수정자_번호 값설정
	 * @param updusrNo
	 */
	public void setUPDUSR_NO(String updusrNo) {
		this.UPDUSR_NO = updusrNo;
	}

	/**
	 * TN_SYS_USER.UPDT_DT,
	 * 시스템사용자.수정_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="UPDT_DT")
	public java.util.Date getUPDT_DT() {
		return this.UPDT_DT;
	}

	 /**
	 * TN_SYS_USER.UPDT_DT,
	 * 시스템사용자.수정_일시 값설정
	 * @param updtDt
	 */
	public void setUPDT_DT(java.util.Date updtDt) {
		this.UPDT_DT = updtDt;
	}

	/**
	 * TN_SYS_USER.CRTR_NO,
	 * 시스템사용자.생성자_번호 값읽기
	 * @return
	 */
	@JsonProperty(value="CRTR_NO")
	public String getCRTR_NO() {
		return this.CRTR_NO;
	}

	 /**
	 * TN_SYS_USER.CRTR_NO,
	 * 시스템사용자.생성자_번호 값설정
	 * @param crtrNo
	 */
	public void setCRTR_NO(String crtrNo) {
		this.CRTR_NO = crtrNo;
	}

	/**
	 * TN_SYS_USER.CREAT_DT,
	 * 시스템사용자.생성_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="CREAT_DT")
	public java.util.Date getCREAT_DT() {
		return this.CREAT_DT;
	}

	 /**
	 * TN_SYS_USER.CREAT_DT,
	 * 시스템사용자.생성_일시 값설정
	 * @param creatDt
	 */
	public void setCREAT_DT(java.util.Date creatDt) {
		this.CREAT_DT = creatDt;
	}

	/**
	 * TN_SYS_USER.PASSWORD_CHANGE_DT,
	 * 시스템사용자.암호_변경_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="PASSWORD_CHANGE_DT")
	public java.util.Date getPASSWORD_CHANGE_DT() {
		return this.PASSWORD_CHANGE_DT;
	}

	 /**
	 * TN_SYS_USER.PASSWORD_CHANGE_DT,
	 * 시스템사용자.암호_변경_일시 값설정
	 * @param passwordChangeDt
	 */
	public void setPASSWORD_CHANGE_DT(java.util.Date passwordChangeDt) {
		this.PASSWORD_CHANGE_DT = passwordChangeDt;
	}

	/**
	 * TN_SYS_USER.DELETE_DT,
	 * 시스템사용자.삭제_일시 값읽기
	 * @return
	 */
	@JsonProperty(value="DELETE_DT")
	public java.util.Date getDELETE_DT() {
		return this.DELETE_DT;
	}

	 /**
	 * TN_SYS_USER.DELETE_DT,
	 * 시스템사용자.삭제_일시 값설정
	 * @param deleteDt
	 */
	public void setDELETE_DT(java.util.Date deleteDt) {
		this.DELETE_DT = deleteDt;
	}

	/**
	 * TN_SYS_USER.USER_SE_CODE,
	 * 시스템사용자.사용자_구분_코드
	 * @return
	 */
	@JsonProperty(value="USER_SE_CODE")
	public String getUSER_SE_CODE() {
		return USER_SE_CODE;
	}

	/**
	 * TN_SYS_USER.USER_SE_CODE,
	 * 시스템사용자.사용자_구분_코드
	 * @param userSeCode
	 */
	public void setUSER_SE_CODE(String userSeCode) {
		this.USER_SE_CODE = userSeCode;
	}

	/**
	 * TN_SYS_USER.CONFM_AT,
	 * 시스템사용자.승인_여부
	 * @return
	 */
	@JsonProperty(value="CONFM_AT")
	public String getCONFM_AT() {
		return CONFM_AT;
	}

	/**
	 * TN_SYS_USER.CONFM_AT,
	 * 시스템사용자.승인_여부
	 * @param confmAt
	 */
	public void setCONFM_AT(String confmAt) {
		this.CONFM_AT = confmAt;
	}

	/**
	 * TN_SYS_USER.CONFM_DT,
	 * 시스템사용자.승인_일시
	 * @return
	 */
	@JsonProperty(value="CONFM_DT")
	public java.util.Date getCONFM_DT() {
		return CONFM_DT;
	}

	/**
	 * TN_SYS_USER.CONFM_DT,
	 * 시스템사용자.승인_일시
	 * @param confmDt
	 */
	public void setCONFM_DT(java.util.Date confmDt) {
		this.CONFM_DT = confmDt;
	}

	/**
	 * TN_SYS_USER.CO_NO,
	 * 시스템사용자.업체_번호
	 * @return
	 */
	@JsonProperty(value="CO_NO")
	public String getCO_NO() {
		return CO_NO;
	}

	/**
	 * TN_SYS_USER.CO_NO,
	 * 시스템사용자.업체_번호
	 * @param coNo
	 */
	public void setCO_NO(String coNo) {
		this.CO_NO = coNo;
	}

	/**
	 * TN_SYS_USER.STPLAT_AGRE_DT,
	 * 시스템사용자.약관_동의_일시
	 * @return
	 */
	@JsonProperty(value="STPLAT_AGRE_DT")
	public java.util.Date getSTPLAT_AGRE_DT() {
		return STPLAT_AGRE_DT;
	}

	/**
	 * TN_SYS_USER.STPLAT_AGRE_DT,
	 * 시스템사용자.약관_동의_일시
	 * @param stplatAgreDt
	 */
	public void setSTPLAT_AGRE_DT(java.util.Date stplatAgreDt) {
		this.STPLAT_AGRE_DT = stplatAgreDt;
	}

	/**
	 * TN_SYS_USER.RM,
	 * 시스템사용자.비고
	 * @return
	 */
	@JsonProperty(value="RM")
	public String getRM() {
		return RM;
	}

	/**
	 * TN_SYS_USER.RM,
	 * 시스템사용자.비고
	 * @param rm
	 */
	public void setRM(String rm) {
		this.RM = rm;
	}

	/**
	 * TN_SYS_USER.CNTRCT_NM,
	 * 시스템사용자.계약_명
	 * @return
	 */
	@JsonProperty(value="CNTRCT_NM")
	public String getCNTRCT_NM() {
		return CNTRCT_NM;
	}

	/**
	 * TN_SYS_USER.CNTRCT_NM,
	 * 시스템사용자.계약_명
	 * @param cntrctNm
	 */
	public void setCNTRCT_NM(String cntrctNm) {
		this.CNTRCT_NM = cntrctNm;
	}

	/**
	 * TN_SYS_USER.CNTRCT_BEGIN_DE,
	 * 시스템사용자.계약_시작_일자
	 * @return
	 */
	@JsonProperty(value="CNTRCT_BEGIN_DE")
	public String getCNTRCT_BEGIN_DE() {
		return CNTRCT_BEGIN_DE;
	}

	/**
	 * TN_SYS_USER.CNTRCT_BEGIN_DE,
	 * 시스템사용자.계약_시작_일자
	 * @param cntrctBeginDe
	 */
	public void setCNTRCT_BEGIN_DE(String cntrctBeginDe) {
		this.CNTRCT_BEGIN_DE = cntrctBeginDe;
	}

	/**
	 * TN_SYS_USER.CNTRCT_END_DE,
	 * 시스템사용자.계약_종료_일자
	 * @return
	 */
	@JsonProperty(value="CNTRCT_END_DE")
	public String getCNTRCT_END_DE() {
		return CNTRCT_END_DE;
	}

	/**
	 * TN_SYS_USER.CNTRCT_END_DE,
	 * 시스템사용자.계약_종료_일자
	 * @param cntrctEndDe
	 */
	public void setCNTRCT_END_DE(String cntrctEndDe) {
		this.CNTRCT_END_DE = cntrctEndDe;
	}

	/**
	 * TN_SYS_USER.CHRG_MANGR_NM,
	 * 시스템사용자.담당_감독자_명
	 * @return
	 */
	@JsonProperty(value="CHRG_MANGR_NM")
	public String getCHRG_MANGR_NM() {
		return CHRG_MANGR_NM;
	}

	/**
	 * TN_SYS_USER.CHRG_MANGR_NM,
	 * 시스템사용자.담당_감독자_명
	 * @param chrgMangrNm
	 */
	public void setCHRG_MANGR_NM(String chrgMangrNm) {
		this.CHRG_MANGR_NM = chrgMangrNm;
	}

	/**
	 * TN_SYS_USER.CNTRWK_CO_NM,
	 * 시스템사용자.공사_업체_명
	 * @return
	 */
	@JsonProperty(value="CNTRWK_CO_NM")
	public String getCNTRWK_CO_NM() {
		return CNTRWK_CO_NM;
	}

	/**
	 * TN_SYS_USER.CNTRWK_CO_NM,
	 * 시스템사용자.공사_업체_명
	 * @param cntrwkCoNm
	 */
	public void setCNTRWK_CO_NM(String cntrwkCoNm) {
		this.CNTRWK_CO_NM = cntrwkCoNm;
	}

	/**
	 * TN_SYS_USER.CONFM_AUTHOR_AT,
	 * 시스템사용자.승인_권한_여부
	 * @return
	 */
	@JsonProperty(value="CONFM_AUTHOR_AT")
	public java.lang.String getCONFM_AUTHOR_AT() {
		return this.CONFM_AUTHOR_AT;
	}

	 /**
	 * TN_SYS_USER.CONFM_AUTHOR_AT,
	 * 시스템사용자.승인_권한_여부
	 * @param confmAuthorAt
	 */
	public void setCONFM_AUTHOR_AT(java.lang.String confmAuthorAt) {
		this.CONFM_AUTHOR_AT = confmAuthorAt;
	}

	/**
	 * TN_SYS_USER.BRTHDY,
	 * 시스템사용자.생년월일
	 * @return
	 */
	@JsonProperty(value="BRTHDY")
	public String getBRTHDY() {
		return BRTHDY;
	}

	/**
	 * TN_SYS_USER.BRTHDY,
	 * 시스템사용자.생년월일
	 * @param brthdy
	 */
	public void setBRTHDY(String brthdy) {
		this.BRTHDY = brthdy;
	}


	/**
	 * TN_SYS_USER.CHRG_JOB,
	 * 시스템사용자.생년월일
	 * @return
	 */
	@JsonProperty(value="CHRG_JOB")
	public String getCHRG_JOB() {
		return CHRG_JOB;
	}

	/**
	 * TN_SYS_USER.CHRG_JOB,
	 * 시스템사용자.생년월일
	 * @param chrgJob
	 */
	public void setCHRG_JOB(String chrgJob) {
		this.CHRG_JOB = chrgJob;
	}

	/**
	 * TN_SYS_USER.REQ_USER_GRP,
	 * 시스템사용자.신청_사용자_그룹
	 * @return
	 */
	@JsonProperty(value="REQ_USER_GRP")
	public java.lang.String getREQ_USER_GRP() {
		return this.REQ_USER_GRP;
	}

	 /**
	 * TN_SYS_USER.REQ_USER_GRP,
	 * 시스템사용자.신청_사용자_그룹
	 * @param reqUserGrp
	 */
	public void setREQ_USER_GRP(java.lang.String reqUserGrp) {
		this.REQ_USER_GRP = reqUserGrp;
	}


	/**
	 * TN_SYS_USER.REQ_MENUACC_ROLE,
	 * 시스템사용자.신청_메뉴접근_권한
	 * @return
	 */
	@JsonProperty(value="REQ_MENUACC_ROLE")
	public java.lang.String getREQ_MENUACC_ROLE() {
		return this.REQ_MENUACC_ROLE;
	}

	 /**
	 * TN_SYS_USER.REQ_MENUACC_ROLE,
	 * 시스템사용자.신청_메뉴접근_권한
	 * @param reqMenuaccRole
	 */
	public void setREQ_MENUACC_ROLE(java.lang.String reqMenuaccRole) {
		this.REQ_MENUACC_ROLE = reqMenuaccRole;
	}

	/**
	 * 사용자_구분
	 */
	@JsonProperty(value="USER_SE_NM")
	public String getUSER_SE_NM() {
		return USER_SE_NM;
	}

	/**
	 * 사용자_구분
	 * @param userSeNm
	 */
	public void setUSER_SE_NM(String userSeNm) {
		this.USER_SE_NM = userSeNm;
	}

	/**
	 * 부서_명
	 */
	@JsonProperty(value="DEPT_NM")
	public String getDEPT_NM() {
		return DEPT_NM;
	}

	/**
	 * 부서_명
	 * @param deptNm
	 */
	public void setDEPT_NM(String deptNm) {
		this.DEPT_NM = deptNm;
	}

	/**
	 * 수정자명
	 */
	public String getUPDUSR_ID() {
		return UPDUSR_ID;
	}

	/**
	 * 수정자명
	 * @param updusrId
	 */
	public void setUPDUSR_ID(String updusrId) {
		this.UPDUSR_ID = updusrId;
	}

	@JsonProperty(value="SEHIGH_DEPT_CODE")
	public String getSEHIGH_DEPT_CODE() {
		return SEHIGH_DEPT_CODE;
	}

	public void setSEHIGH_DEPT_CODE(String sehighDeptCode) {
		this.SEHIGH_DEPT_CODE = sehighDeptCode;
	}

	@JsonProperty(value="UPPER_SEHIGH_DEPT_CODE")
	public String getUPPER_SEHIGH_DEPT_CODE() {
		return UPPER_SEHIGH_DEPT_CODE;
	}

	public void setUPPER_SEHIGH_DEPT_CODE(String upperSehighDeptCode) {
		this.UPPER_SEHIGH_DEPT_CODE = upperSehighDeptCode;
	}

	@JsonProperty(value="BEF_SECRET_NO")
	public String getBEF_SECRET_NO() {
		return BEF_SECRET_NO;
	}

	public void setBEF_SECRET_NO(String bEF_SECRET_NO) {
		BEF_SECRET_NO = bEF_SECRET_NO;
	}

	@JsonProperty(value="AUTHOR_ID")
	public String getAUTHOR_ID() {
		return AUTHOR_ID;
	}

	public void setAUTHOR_ID(String aUTHOR_ID) {
		AUTHOR_ID = aUTHOR_ID;
	}

	@JsonProperty(value="AUTHOR_NM")
	public String getAUTHOR_NM() {
		return AUTHOR_NM;
	}

	public void setAUTHOR_NM(String aUTHOR_NM) {
		AUTHOR_NM = aUTHOR_NM;
	}

	@JsonProperty(value="USER_GRP")
	public String getUSER_GRP() {
		return USER_GRP;
	}

	public void setUSER_GRP(String uSER_GRP) {
		USER_GRP = uSER_GRP;
	}

	@JsonProperty(value="MENUACC_ROLE")
	public String getMENUACC_ROLE() {
		return MENUACC_ROLE;
	}

	public void setMENUACC_ROLE(String mENUACC_ROLE) {
		MENUACC_ROLE = mENUACC_ROLE;
	}

	@JsonProperty(value="MENU_NM")
	public String getMENU_NM() {
		return this.MENU_NM;
	}

	public void setMENU_NM(String mENU_NM) {
		this.MENU_NM = mENU_NM;
	}

	@JsonProperty(value="SCH_MENU_ID")
	public String getSCH_MENU_ID() {
		return this.SCH_MENU_ID;
	}

	public void setSCH_MENU_ID(String sCH_MENU_ID) {
		this.SCH_MENU_ID = sCH_MENU_ID;
	}

	@JsonProperty(value="SCH_MENU_NM")
	public String getSCH_MENU_NM() {
		return this.SCH_MENU_NM;
	}

	public void setSCH_MENU_NM(String sCH_MENU_NM) {
		this.SCH_MENU_NM = sCH_MENU_NM;
	}

	@JsonProperty(value="SCH_STRCON_DE")
	public String getSCH_STRCON_DE() {
		return this.SCH_STRCON_DE;
	}

	public void setSCH_STRCON_DE(String sCH_STRCON_DE) {
		this.SCH_STRCON_DE = sCH_STRCON_DE;
	}

	@JsonProperty(value="SCH_ENDCON_DE")
	public String getSCH_ENDCON_DE() {
		return this.SCH_ENDCON_DE;
	}

	public void setSCH_ENDCON_DE(String sCH_ENDCON_DE) {
		this.SCH_ENDCON_DE = sCH_ENDCON_DE;
	}

	@JsonProperty(value="USER_FLAG")
    public java.lang.String getUSER_FLAG() {
        return USER_FLAG;
    }

    public void setUSER_FLAG(java.lang.String uSER_FLAG) {
        USER_FLAG = uSER_FLAG;
    }



}
