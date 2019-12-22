package egovframework.cmmn.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class SrvyDtaImageFileVO implements Serializable {

    /** 자료_번호 */
    private String DTA_NO;

    /** 전방_이미지_파일_명 */
    private String FRNT_IMG_FILE_NM;

    /** 전방_이미지_파일_경로 */
    private String FRNT_IMG_FILE_COURS;

    /** 균열_이미지_파일_명 */
    private String CR_IMG_FILE_NM;

    /** 균열_이미지_파일_경로 */
    private String CR_IMG_FILE_COURS;

    public String getDTA_NO() {
        return DTA_NO;
    }

    public void setDTA_NO(String dTA_NO) {
        DTA_NO = dTA_NO;
    }

    public String getFRNT_IMG_FILE_NM() {
        return FRNT_IMG_FILE_NM;
    }

    public void setFRNT_IMG_FILE_NM(String fRNT_IMG_FILE_NM) {
        FRNT_IMG_FILE_NM = fRNT_IMG_FILE_NM;
    }

    public String getFRNT_IMG_FILE_COURS() {
        return FRNT_IMG_FILE_COURS;
    }

    public void setFRNT_IMG_FILE_COURS(String fRNT_IMG_FILE_COURS) {
        FRNT_IMG_FILE_COURS = fRNT_IMG_FILE_COURS;
    }

    public String getCR_IMG_FILE_NM() {
        return CR_IMG_FILE_NM;
    }

    public void setCR_IMG_FILE_NM(String cR_IMG_FILE_NM) {
        CR_IMG_FILE_NM = cR_IMG_FILE_NM;
    }

    public String getCR_IMG_FILE_COURS() {
        return CR_IMG_FILE_COURS;
    }

    public void setCR_IMG_FILE_COURS(String cR_IMG_FILE_COURS) {
        CR_IMG_FILE_COURS = cR_IMG_FILE_COURS;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
