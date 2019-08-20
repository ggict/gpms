package kr.go.gg.gpms.pothole.address.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

/**
 * 주소변환 Service
 * @Classname : AddressService.java
 * 
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 2. 7.
 */
public interface AddressService {

    /**
     * 신고정보 보정 프로시저를 실행한다.
     * @author    : JOY
     * @date      : 2018. 2. 7.
     * 
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : HashMap
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public HashMap procPrcPthCrdntCortn(SttemntVO sttemntVO) throws Exception ;
    
    /**
     * 신고정보 보정 후 보정좌표를 조회한다.
     * @author    : JOY
     * @date      : 2018. 2. 28.
     * 
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectCortnLonLat(SttemntVO sttemntVO) throws Exception ;
    
    /**
     * 주소 Update 후 SMS를 발송한다. (연락처가 없는 경우에는 발송 X)
     * @author    : JOY
     * @date      : 2018. 3. 9.
     * 
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : HashMap
     * @exception : Exception
     */
    @SuppressWarnings("rawtypes")
    public HashMap procPrcPthUdtSttmnt(SttemntVO sttemntVO) throws Exception ;
}

