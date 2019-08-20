package kr.go.gg.gpms.pothole.sttemnt.service.impl;

import javax.annotation.Resource;

import kr.go.gg.gpms.pothole.sttemnt.service.RpairService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 포트홀 신고관리 Service Impl
 * @Classname : SttemntServiceImpl.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@SuppressWarnings("deprecation")
@Service("rpairService")
public class RpairServiceImpl extends AbstractServiceImpl implements RpairService {

	@Resource(name = "rpairDAO")
	private RpairDAO rpairDAO;

	/**
     * 포트홀 보수정보를 추가한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     * 
     * @param     : RpairVO - 조회할 정보가 담긴 RpairVO
     * @return    : int
     * @exception : Exception
     */
	@Override
    public String insertPotholeRpair(RpairVO rpairVO) throws Exception {
        return rpairDAO.insertPotholeRpair(rpairVO);
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
    @Override
    public int updatePotholeRpair(RpairVO rpairVO) throws Exception {
        return rpairDAO.updatePotholeRpair(rpairVO);
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
    @Override
    public int deletePotholeRpair(RpairVO rpairVO) throws Exception {
        return rpairDAO.deletePotholeRpair(rpairVO);
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
    @Override
    public RpairVO selectPotholeRpair(RpairVO rpairVO) throws Exception {
        return rpairDAO.selectPotholeRpair(rpairVO);
    }
    
}
