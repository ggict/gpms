package kr.go.gg.gpms.pothole.address.service.impl;

import java.util.HashMap;

import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 주소변환 DAO
 * @Classname : AddressDAO.java
 * 
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 2. 7.
 */
@Repository("addressDAO")
public class AddressDAO extends BaseDAO {

    /**
     * 신고정보 보정 프로시저를 실행한다.
     * @author    : JOY
     * @date      : 2018. 2. 7.
     * 
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : HashMap
     * @exception : Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap procPrcPthCrdntCortn(SttemntVO sttemntVO) throws Exception {
        
        HashMap param = new HashMap();
        
        param.put("p_PTH_RG_NO",    sttemntVO.getPTH_RG_NO());
        param.put("p_CRDNT_X",      sttemntVO.getCRDNT_X());
        param.put("p_CRDNT_Y",      sttemntVO.getCRDNT_Y());
        param.put("p_HEADG",        sttemntVO.getHEADG());
        param.put("p_APCT_TELNO",   sttemntVO.getAPLCNT_TELNO());
        param.put("p_BSNM_NM",      sttemntVO.getBSNM_NM());
        param.put("p_VHCLE_NO",     sttemntVO.getVHCLE_NO());
        param.put("p_RCEPT_CN",     sttemntVO.getRCEPT_CN());
        param.put("p_VHCLE_TYPE",   sttemntVO.getVHCLE_TYPE());
        param.put("p_STTEMNT_DT",   sttemntVO.getSTTEMNT_DT());
        
        HashMap resultVO = (HashMap) call("addressDAO.PRC_PTH_CRDNT_CORTN", param);
        
        return resultVO ;
        
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
        return (SttemntVO) select("addressDAO.selectCortnLonLat", sttemntVO);
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap procPrcPthUdtSttmnt(SttemntVO sttemntVO) throws Exception {
        
        HashMap param = new HashMap();
        
        param.put("p_PTH_RG_NO",    sttemntVO.getPTH_RG_NO());
        param.put("p_RN_ADRES",     sttemntVO.getRN_ADRES());
        param.put("p_LNM_ADRES",    sttemntVO.getLNM_ADRES());
        
        HashMap resultVO = (HashMap) call("addressDAO.PRC_PTH_UDT_STTMNT", param);
        
        return resultVO ;
        
    }
    
}
