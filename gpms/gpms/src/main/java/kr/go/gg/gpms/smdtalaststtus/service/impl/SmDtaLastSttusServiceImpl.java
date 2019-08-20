package kr.go.gg.gpms.smdtalaststtus.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;
import kr.go.gg.gpms.smdtalaststtus.service.SmDtaLastSttusService;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;

/**
 * 집계_자료_최신_현황
 *
 * @Class Name : SmDtaLastSttusServiceImpl.java
 * @Description : SmDtaLastSttus Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("smDtaLastSttusService")
public class SmDtaLastSttusServiceImpl extends AbstractServiceImpl implements SmDtaLastSttusService {

	@Resource(name = "smDtaLastSttusDAO")
	private SmDtaLastSttusDAO smDtaLastSttusDAO;

	//@Resource(name="SmDtaLastSttusIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 등록한다.
	 * @param smDtaLastSttusVO - 등록할 정보가 담긴 SmDtaLastSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//smDtaLastSttusVO.setId(id);

		return smDtaLastSttusDAO.insertSmDtaLastSttus( smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 수정한다.
	 * @param smDtaLastSttusVO - 수정할 정보가 담긴 SmDtaLastSttusVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return smDtaLastSttusDAO.updateSmDtaLastSttus( smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 삭제한다.
	 * @param smDtaLastSttusVO - 삭제할 정보가 담긴 SmDtaLastSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return smDtaLastSttusDAO.deleteSmDtaLastSttus( smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS)을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return 조회한 TN_SM_DTA_LAST_STTUS
	 * @exception Exception
	 */
	public SmDtaLastSttusVO selectSmDtaLastSttus(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		SmDtaLastSttusVO resultVO = smDtaLastSttusDAO.selectSmDtaLastSttus( smDtaLastSttusVO);
		 
		return resultVO;
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	public List<SmDtaLastSttusVO> selectSmDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return smDtaLastSttusDAO.selectSmDtaLastSttusList( smDtaLastSttusVO);
	}

	/**
	 * 집계_자료_최신_현황(TN_SM_DTA_LAST_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	public int selectSmDtaLastSttusListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO) {
		return smDtaLastSttusDAO.selectSmDtaLastSttusListTotalCount( smDtaLastSttusVO);
	}
	
	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록 을 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 목록
	 * @exception Exception
	 */
	public List<SmDtaLastSttusVO> selectRouteEvlList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return smDtaLastSttusDAO.selectRouteEvlList(smDtaLastSttusVO);
	}

	/**
	 * 포장상태 평가 - 노선단위 수시병가 대상 목록  총 갯수를 조회한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 * @return TN_SM_DTA_LAST_STTUS 총 갯수
	 * @exception
	 */
	public int selectRouteEvlListTotalCount(SmDtaLastSttusVO smDtaLastSttusVO) {
		return smDtaLastSttusDAO.selectRouteEvlListTotalCount(smDtaLastSttusVO);
	}
	
	/**
	 * 집계된 조사자료를 현행화 한다
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 */
	public HashMap prcClacPredctRouteSrvyEvl(SmDtaLastSttusVO smDtaLastSttusVO) {
		return smDtaLastSttusDAO.prcClacPredctRouteSrvyEvl(smDtaLastSttusVO);
	}
	
	/**
	 * 10년치 예측 데이터를 생성한다.
	 * @param smDtaLastSttusVO - 조회할 정보가 담긴 SmDtaLastSttusVO
	 */
	public HashMap prcClacPredctLast(SmDtaLastSttusVO smDtaLastSttusVO){
		return smDtaLastSttusDAO.prcClacPredctLast(smDtaLastSttusVO);
	}
	

    /**
     * 평가정보 예측자료 목록을 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param     : smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return    : 예측자료 목록
     * @exception : Exception
     */
    public List<SmDtaLastSttusVO> selectSrvyDtaLastSttusList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return smDtaLastSttusDAO.selectSrvyDtaLastSttusList(smDtaLastSttusVO);
    }

    /**
     * 평가정보 예측자료 목록 총 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return 예측자료 목록 총 갯수
     * @exception
     */
    public int selectSrvyDtaLastSttusListCnt(SmDtaLastSttusVO smDtaLastSttusVO) {
        return smDtaLastSttusDAO.selectSrvyDtaLastSttusListCnt(smDtaLastSttusVO);
    }

    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록을 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 공사자료 목록
     * @exception : Exception
     */
    public List<SmDtaLastSttusVO> selectCntrwkListBySect(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return smDtaLastSttusDAO.selectCntrwkListBySect(smDtaLastSttusVO);
    };

    /**
     * 평가정보 예측자료 상세정보의 공사정보 목록 총 갯수를 조회한다.
     * @author    : JOY
     * @date      : 2017. 10. 23.
     * 
     * @param smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return 공사자료 목록 총 갯수
     * @exception
     */
    public int selectCntrwkListBySectCnt(SmDtaLastSttusVO smDtaLastSttusVO) {
        return smDtaLastSttusDAO.selectCntrwkListBySectCnt(smDtaLastSttusVO);
    };
    
    /**
	 * 평가정보 예측 통계 정보를 조회한다.
	 * @author    : skc
     * @date      : 2017. 11. 23.
     * 
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
    public List<SmDtaLastSttusVO> prcStatPredct(SmDtaLastSttusVO smDtaLastSttusVO, String mode) throws Exception {
    	return smDtaLastSttusDAO.prcStatPredct(smDtaLastSttusVO, mode);
	}
    
    /**
	 * 평가정보 예측 통계 정보 > 엑셀 저장
	 * @author    : skc
     * @date      : 2017. 11. 24.
     * 
	 * @param mummSctnSrvyDtaVO
	 * @return
	 */
    public List<SmDtaLastSttusVO> prcStatPredctExcel(SmDtaLastSttusVO smDtaLastSttusVO, String mode) throws Exception {
    	return smDtaLastSttusDAO.prcStatPredctExcel(smDtaLastSttusVO, mode);
	}

    /**
     * 평가정보 예측자료 상세정보의 이전 조사자료 GPCI 목록을 조회한다.
     * @author    : skc
     * @date      : 2017. 12. 08.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 조사자료 목록
     * @exception : Exception
     */
    public List<SmDtaLastSttusVO> selectSmDtaGnlGPCIList(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return smDtaLastSttusDAO.selectSmDtaGnlGPCIList(smDtaLastSttusVO);
    };
    
    /**
     * 포장상태 평가정보 상세조회 - 현행정보조회
     * @author    : JOY
     * @date      : 2017. 12. 11.
     * 
     * @param     : smDtaLastSttusVO - 조회할 정보가 담긴 smDtaLastSttusVO
     * @return    : 현행정보
     * @exception : Exception
     */
    public SmDtaLastSttusVO selectSmDtaLastSttusBySrvy(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
        return smDtaLastSttusDAO.selectSmDtaLastSttusBySrvy(smDtaLastSttusVO);
    }

	@Override
	public List selectSmDtaLastSttusListExcel(SmDtaLastSttusVO smDtaLastSttusVO) throws Exception {
		return smDtaLastSttusDAO.selectSmDtaLastSttusListExcel(smDtaLastSttusVO);
	}
    
}
