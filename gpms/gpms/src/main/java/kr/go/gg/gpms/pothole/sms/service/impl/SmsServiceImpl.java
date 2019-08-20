package kr.go.gg.gpms.pothole.sms.service.impl;

import javax.annotation.Resource;

import kr.go.gg.gpms.pothole.sms.service.SmsService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * SMS전송 ServiceImpl
 * @Classname : SmsServiceImpl.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 3. 14.
 */
@SuppressWarnings("deprecation")
@Service("smsService")
public class SmsServiceImpl extends AbstractServiceImpl implements SmsService {

	@Resource(name = "smsDAO")
	private SmsDAO smsDAO;

    /**
     * 담당자에게 SMS를 전송한다.
     * @author    : JOY
     * @date      : 2018. 3. 8.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : String
     * @exception : Exception
     */
    public String sendSMS(SttemntVO sttemntVO) throws Exception {
        return smsDAO.sendSMS(sttemntVO);
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
        return smsDAO.sendStartSMS(sttemntVO);
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
        return smsDAO.sendDropSMS(sttemntVO);
    }

}
