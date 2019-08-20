package kr.go.gg.gpms.rpairmthduntpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthduntpc.service.RpairMthdUntpcService;
import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;

/**
 * 보수_공법_단가
 *
 * @Class Name : RpairMthdUntpcServiceImpl.java
 * @Description : RpairMthdUntpc Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("rpairMthdUntpcService")
public class RpairMthdUntpcServiceImpl extends AbstractServiceImpl implements RpairMthdUntpcService {

	@Resource(name = "rpairMthdUntpcDAO")
	private RpairMthdUntpcDAO rpairMthdUntpcDAO;

	//@Resource(name="RpairMthdUntpcIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairMthdUntpcVO.setId(id);

		return rpairMthdUntpcDAO.insertRpairMthdUntpc( rpairMthdUntpcVO);
	}
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthdUntpcHist(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
			return rpairMthdUntpcDAO.insertRpairMthdUntpcHist( rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 수정한다.
	 * @param rpairMthdUntpcVO - 수정할 정보가 담긴 RpairMthdUntpcVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return rpairMthdUntpcDAO.updateRpairMthdUntpc( rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 삭제한다.
	 * @param rpairMthdUntpcVO - 삭제할 정보가 담긴 RpairMthdUntpcVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return rpairMthdUntpcDAO.deleteRpairMthdUntpc( rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return 조회한 TN_RPAIR_MTHD_UNTPC
	 * @exception Exception
	 */
	public RpairMthdUntpcVO selectRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return rpairMthdUntpcDAO.selectRpairMthdUntpc( rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 목록
	 * @exception Exception
	 */
	public List<RpairMthdUntpcVO> selectRpairMthdUntpcList(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
		return rpairMthdUntpcDAO.selectRpairMthdUntpcList( rpairMthdUntpcVO);
	}

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	public int selectRpairMthdUntpcListTotalCount(RpairMthdUntpcVO rpairMthdUntpcVO) {
		return rpairMthdUntpcDAO.selectRpairMthdUntpcListTotalCount( rpairMthdUntpcVO);
	}
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 전체 단가 평균을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	public int selectRpairMthdUntpcAvgTotAmount(RpairMthdUntpcVO rpairMthdUntpcVO) {
		return rpairMthdUntpcDAO.selectRpairMthdUntpcAvgTotAmount( rpairMthdUntpcVO);
	}

	 
    /**
	 * 보수대상 선정 > 예산수준별 시나리오 분석
	 * @author    : skc
     * @date      : 2017. 11. 27.
     * 
	 * @param rpairMthdUntpcVO
	 * @return
	 */
    public List<RpairMthdUntpcVO> prcStatUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception {
    	return rpairMthdUntpcDAO.prcStatUntpc(rpairMthdUntpcVO);
	}
    
}
