


package kr.go.gg.gpms.rpairmthduntpc.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;

/**
 * 보수_공법_단가
 *
 * @Class Name : RpairMthdUntpcDAO.java
 * @Description : RpairMthdUntpc DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairMthdUntpcDAO")
public class RpairMthdUntpcDAO extends BaseDAO {

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return (String) insert("rpairMthdUntpcDAO.insertRpairMthdUntpc", rpairMthdUntpcVO);
	}
	
	/**
	 * 보수_공법_단가_이력(TH_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdUntpcHist(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return (String) insert("rpairMthdUntpcDAO.insertRpairMthdUntpcHist", rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 수정한다.
	 * @param rpairMthdUntpcVO - 수정할 정보가 담긴 RpairMthdUntpcVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return update("rpairMthdUntpcDAO.updateRpairMthdUntpc", rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 삭제한다.
	 * @param rpairMthdUntpcVO - 삭제할 정보가 담긴 RpairMthdUntpcVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return delete("rpairMthdUntpcDAO.deleteRpairMthdUntpc", rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return 조회한 TN_RPAIR_MTHD_UNTPC
	 * @exception Exception
	 */
	public RpairMthdUntpcVO selectRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return (RpairMthdUntpcVO) select("rpairMthdUntpcDAO.selectRpairMthdUntpc", rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 목록을 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RpairMthdUntpcVO> selectRpairMthdUntpcList(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return (List<RpairMthdUntpcVO>)list("rpairMthdUntpcDAO.selectRpairMthdUntpcList", rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 총 갯수를 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	public int selectRpairMthdUntpcListTotalCount(RpairMthdUntpcVO rpairMthdUntpcVO) {
		return (Integer) select("rpairMthdUntpcDAO.selectRpairMthdUntpcListTotalCount", rpairMthdUntpcVO);
	}
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 전체 단가 평균을 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	public int selectRpairMthdUntpcAvgTotAmount(RpairMthdUntpcVO rpairMthdUntpcVO) {
		return (Integer) select("rpairMthdUntpcDAO.selectRpairMthdUntpcAvgTotAmount", rpairMthdUntpcVO);
	}
	
	/**
     * 보수대상 선정 > 예산수준별 시나리오 분석
     * @author    : skc
     * @date      : 2017. 11. 27.
     * 
     * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
     * @return 
     * @exception
     */
    @SuppressWarnings("unchecked")
	public List<RpairMthdUntpcVO> prcStatUntpc(RpairMthdUntpcVO rpairMthdUntpcVO)  throws Exception {
		HashMap param = new HashMap();
		
    	param.put("p_UNTPCS", rpairMthdUntpcVO.getUNTPCS());
    	param.put("p_DEPT_CODE", rpairMthdUntpcVO.getDEPT_CODE());
    	param.put("p_MODE", "NONE");
    	
    	return (List<RpairMthdUntpcVO>) list("rpairMthdUntpcDAO.PRC_STAT_UNTPC", param);
	}

}
