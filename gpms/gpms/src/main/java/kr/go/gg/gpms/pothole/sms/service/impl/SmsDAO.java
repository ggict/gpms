package kr.go.gg.gpms.pothole.sms.service.impl;

import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO2;

/**
 * SMS전송 DAO
 * @Classname : SmsDAO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 3. 14.
 */
@Repository("smsDAO")
public class SmsDAO extends BaseDAO2 {

    /**
     * 담당자에게 SMS를 전송한다.
     * @author    : JOY
     * @date      : 2018. 3. 8.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public String sendSMS(SttemntVO sttemntVO) throws Exception {
        return (String) insert("smsDAO.sendSMS", sttemntVO);
    }

    /**
     * 문자전송 시작시간에 문자전송 제한시간 동안의 신고 등록 건수를 담당자에게 SMS로 전송한다.
     * @author    : lsk
     * @date      : 2019. 7. 6.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public String sendStartSMS(SttemntVO sttemntVO) throws Exception {
        return (String) insert("smsDAO.sendStartSMS", sttemntVO);
    }

    /**
     * 담당자에게 SMS를 전송한다. (포트홀 신고 취하)
     * @author    : lsk
     * @date      : 2019. 8. 5.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public String sendDropSMS(SttemntVO sttemntVO) throws Exception {
        return (String) insert("smsDAO.sendDropSMS", sttemntVO);
    }

}
