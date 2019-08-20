package kr.go.gg.gpms.pothole.address.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import kr.go.gg.gpms.pothole.address.service.AddressService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 주소변환 ServiceImpl
 * @Classname : SttstServiceImpl.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 2. 7.
 */
@SuppressWarnings("deprecation")
@Service("addressService")
public class AddressServiceImpl extends AbstractServiceImpl implements AddressService {
	@Resource(name = "addressDAO")
	private AddressDAO addressDAO;

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
    public HashMap procPrcPthCrdntCortn(SttemntVO sttemntVO) throws Exception {
        return addressDAO.procPrcPthCrdntCortn(sttemntVO);
    }

    /**
     * 신고정보 보정 후 보정좌표를 조회한다.
     * @author    : JOY
     * @date      : 2018. 2. 28.
     * 
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectCortnLonLat(SttemntVO sttemntVO) throws Exception {
        return addressDAO.selectCortnLonLat(sttemntVO);
    }
    
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
    public HashMap procPrcPthUdtSttmnt(SttemntVO sttemntVO) throws Exception {
        return addressDAO.procPrcPthUdtSttmnt(sttemntVO);
    }
}
