


package kr.go.gg.gpms.rpairtrgetgroup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;

/**
 * 보수_대상_항목_그룹
 *
 * @Class Name : RpairTrgetGroupDAO.java
 * @Description : RpairTrgetGroup DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairTrgetGroupDAO")
public class RpairTrgetGroupDAO extends BaseDAO {

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다.
     * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 목록
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<RpairTrgetGroupVO> selectRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
        return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairTrgetGroupList", rpairTrgetGroupVO);
    }

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 총 갯수를 조회한다.
     * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    public int selectRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
        return (Integer) select("rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalCount", rpairTrgetGroupVO);
    }

    /**
     * 보수대상 우선순위 저장 처리
     */
    public int updatePRIORT(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
        return update("rpairTrgetGroupDAO.updatePRIORT", rpairTrgetGroupVO);
    }








	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 등록한다.
	 * @param rpairTrgetGroupVO - 등록할 정보가 담긴 RpairTrgetGroupVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return (String) insert("rpairTrgetGroupDAO.insertRpairTrgetGroup", rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 수정한다.
	 * @param rpairTrgetGroupVO - 수정할 정보가 담긴 RpairTrgetGroupVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return update("rpairTrgetGroupDAO.updateRpairTrgetGroup", rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 삭제한다.
	 * @param rpairTrgetGroupVO - 삭제할 정보가 담긴 RpairTrgetGroupVO
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return delete("rpairTrgetGroupDAO.deleteRpairTrgetGroup", rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	public RpairTrgetGroupVO selectRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return (RpairTrgetGroupVO) select("rpairTrgetGroupDAO.selectRpairTrgetGroup", rpairTrgetGroupVO);
	}

	public int updateToggleTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return update("rpairTrgetGroupDAO.updateToggleTMPR_SLCTN_AT", rpairTrgetGroupVO);
	}

	public int updateInitTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return update("rpairTrgetGroupDAO.updateInitTMPR_SLCTN_AT", rpairTrgetGroupVO);
	}

	@SuppressWarnings("unchecked")
	public List<RpairTrgetGroupVO> selectRpairTrgetGroupCELLList(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairTrgetGroupCELLList", rpairTrgetGroupVO);
	}
	@SuppressWarnings("unchecked")
	public List<RpairTrgetGroupVO> selectRpairTrgetDeptStatistics(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairTrgetDeptStatistics", rpairTrgetGroupVO);
	}
	@SuppressWarnings("unchecked")
	public List<RpairTrgetGroupVO> selectRpairTrgetMethodStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairTrgetMethodStatistics", rpairTrgetGroupVO);
	}
	@SuppressWarnings("unchecked")
	public List<RpairTrgetGroupVO> selectRpairTrgetAdminStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairTrgetAdminStatistics", rpairTrgetGroupVO);
	}

	public RpairTrgetGroupVO selectRpairTrgetGroupListTotalSummary(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception{
		return (RpairTrgetGroupVO) select("rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalSummary", rpairTrgetGroupVO);
	}

	public List<RpairTrgetGroupVO> selectRpairRoutLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairRoutLenStats", rpairTrgetGroupVO);
	}

	public List<RpairTrgetGroupVO> selectRpairRoutLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairRoutLenStatsExcel", rpairTrgetGroupVO);
	}

	public List<RpairTrgetGroupVO> selectRpairDeptLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairDeptLenStats", rpairTrgetGroupVO);
	}

	public List<RpairTrgetGroupVO> selectRpairDeptLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) {
		return (List<RpairTrgetGroupVO>)list("rpairTrgetGroupDAO.selectRpairDeptLenStatsExcel", rpairTrgetGroupVO);
	}

}
