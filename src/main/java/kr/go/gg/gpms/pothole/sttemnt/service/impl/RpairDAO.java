package kr.go.gg.gpms.pothole.sttemnt.service.impl;

import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 포트홀 신고관리 DAO
 * @Classname : RpairDAO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@Repository("rpairDAO")
public class RpairDAO extends BaseDAO {

    /**
     * 포트홀 보수정보를 추가한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public String insertPotholeRpair(RpairVO rpairVO) throws Exception {
        return (String) insert("rpairDAO.insertPotholeRpair", rpairVO);
    }
    
    /**
     * 포트홀 보수정보를 수정한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePotholeRpair(RpairVO rpairVO) throws Exception {
        return update("rpairDAO.updatePotholeRpair", rpairVO);
    }
    
    /**
     * 포트홀 보수정보를 삭제한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
    public int deletePotholeRpair(RpairVO rpairVO) throws Exception {
        return delete("rpairDAO.deletePotholeRpair", rpairVO);
    }
    
    /**
     * 포트홀 보수정보를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : RpairVO
     * @exception : Exception
     */
    public RpairVO selectPotholeRpair(RpairVO rpairVO) throws Exception {
        return (RpairVO) select("rpairDAO.selectPotholeRpair", rpairVO); 
    }
    
}
