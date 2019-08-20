package kr.go.gg.gpms.pothole.sttemnt.service;

import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;

/**
 * 포트홀 신고관리 Service
 * @Classname : SttemntService.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
public interface RpairService {

    /**
     * 포트홀 보수정보를 추가한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public String insertPotholeRpair(RpairVO rpairVO) throws Exception ;
    
    /**
     * 포트홀 보수정보를 수정한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePotholeRpair(RpairVO rpairVO) throws Exception ;
    
    /**
     * 포트홀 보수정보를 삭제한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public int deletePotholeRpair(RpairVO rpairVO) throws Exception ;
    
    /**
     * 포트홀 보수정보를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : RpairVO
     * @exception : Exception
     */
    public RpairVO selectPotholeRpair(RpairVO rpairVO) throws Exception ;
    
}

