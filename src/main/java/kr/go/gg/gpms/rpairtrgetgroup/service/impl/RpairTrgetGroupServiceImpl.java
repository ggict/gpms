package kr.go.gg.gpms.rpairtrgetgroup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.rpairtrgetgroup.service.RpairTrgetGroupService;
import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;

/**
 * 보수_대상_항목_그룹
 *
 * @Class Name : RpairTrgetGroupServiceImpl.java
 * @Description : RpairTrgetGroup Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("rpairTrgetGroupService")
public class RpairTrgetGroupServiceImpl extends AbstractServiceImpl implements RpairTrgetGroupService {

	@Resource(name = "rpairTrgetGroupDAO")
	private RpairTrgetGroupDAO rpairTrgetGroupDAO;

	//@Resource(name="RpairTrgetGroupIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
	 * @return TN_RPAIR_TRGET_GROUP 목록
	 * @exception Exception
	 */
	public List<RpairTrgetGroupVO> selectRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
	    return rpairTrgetGroupDAO.selectRpairTrgetGroupList( rpairTrgetGroupVO);
	}

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    public int selectRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
        return rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalCount( rpairTrgetGroupVO);
    }

    /**
     * 보수대상 우선순위 저장 처리
     */
    public int updatePRIORT(List<RpairTrgetGroupVO> lvo, RpairTrgetGroupVO vo) throws Exception {
        int ret = 0;

        for ( int i = 0; i < lvo.size(); i++ ) {
            RpairTrgetGroupVO rpairTrgetGroupVO = lvo.get(i);
            rpairTrgetGroupVO.setCRTR_NO(vo.getCRTR_NO());
            rpairTrgetGroupVO.setUPDUSR_NO(vo.getUPDUSR_NO());

            ret += rpairTrgetGroupDAO.updatePRIORT(rpairTrgetGroupVO);
        }

        return ret;
    }







	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 등록한다.
	 * @param rpairTrgetGroupVO - 등록할 정보가 담긴 RpairTrgetGroupVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairTrgetGroupVO.setId(id);

		return rpairTrgetGroupDAO.insertRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 수정한다.
	 * @param rpairTrgetGroupVO - 수정할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.updateRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 삭제한다.
	 * @param rpairTrgetGroupVO - 삭제할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.deleteRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	public RpairTrgetGroupVO selectRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		RpairTrgetGroupVO resultVO = rpairTrgetGroupDAO.selectRpairTrgetGroup( rpairTrgetGroupVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	@Override
	public int updateToggleTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.updateToggleTMPR_SLCTN_AT( rpairTrgetGroupVO);
	}

	@Override
	public int updateInitTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return rpairTrgetGroupDAO.updateInitTMPR_SLCTN_AT( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetGroupCELLList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetGroupCELLList( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetDeptStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetDeptStatistics( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetMethodStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetMethodStatistics( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetAdminStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetAdminStatistics( rpairTrgetGroupVO);
	}

	@Override
	public RpairTrgetGroupVO selectRpairTrgetGroupListTotalSummary(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalSummary( rpairTrgetGroupVO);
	}
	
	@Override
	public List<RpairTrgetGroupVO> selectRpairRoutLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairRoutLenStats(rpairTrgetGroupVO);
	}
	
	@Override
	public List<RpairTrgetGroupVO> selectRpairRoutLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairRoutLenStatsExcel(rpairTrgetGroupVO);
	}
	
	@Override
	public List<RpairTrgetGroupVO> selectRpairDeptLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairDeptLenStats(rpairTrgetGroupVO);
	}
	
	@Override
	public List<RpairTrgetGroupVO> selectRpairDeptLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairDeptLenStatsExcel(rpairTrgetGroupVO);
	}

}
