package kr.go.gg.gpms.smdtagnlsttus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.smdtagnlsttus.service.SmDtaGnlSttusService;
import kr.go.gg.gpms.smdtagnlsttus.service.model.SmDtaGnlSttusVO;

/**
 * 집계_자료_일반_현황
 *
 * @Class Name : SmDtaGnlSttusServiceImpl.java
 * @Description : SmDtaGnlSttus Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("smDtaGnlSttusService")
public class SmDtaGnlSttusServiceImpl extends AbstractServiceImpl implements SmDtaGnlSttusService {

	@Resource(name = "smDtaGnlSttusDAO")
	private SmDtaGnlSttusDAO smDtaGnlSttusDAO;

	//@Resource(name="SmDtaGnlSttusIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 등록한다.
	 * @param smDtaGnlSttusVO - 등록할 정보가 담긴 SmDtaGnlSttusVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//smDtaGnlSttusVO.setId(id);

		return smDtaGnlSttusDAO.insertSmDtaGnlSttus( smDtaGnlSttusVO);
	}

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 수정한다.
	 * @param smDtaGnlSttusVO - 수정할 정보가 담긴 SmDtaGnlSttusVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		return smDtaGnlSttusDAO.updateSmDtaGnlSttus( smDtaGnlSttusVO);
	}

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 삭제한다.
	 * @param smDtaGnlSttusVO - 삭제할 정보가 담긴 SmDtaGnlSttusVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		return smDtaGnlSttusDAO.deleteSmDtaGnlSttus( smDtaGnlSttusVO);
	}

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS)을 조회한다.
	 * @param smDtaGnlSttusVO - 조회할 정보가 담긴 SmDtaGnlSttusVO
	 * @return 조회한 TN_SM_DTA_GNL_STTUS
	 * @exception Exception
	 */
	public SmDtaGnlSttusVO selectSmDtaGnlSttus(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		SmDtaGnlSttusVO resultVO = smDtaGnlSttusDAO.selectSmDtaGnlSttus( smDtaGnlSttusVO);
		
		return resultVO;
	}

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 목록
	 * @exception Exception
	 */
	public List<SmDtaGnlSttusVO> selectSmDtaGnlSttusList(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		return smDtaGnlSttusDAO.selectSmDtaGnlSttusList( smDtaGnlSttusVO);
	}

	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 총 갯수
	 * @exception
	 */
	public int selectSmDtaGnlSttusListTotalCount(SmDtaGnlSttusVO smDtaGnlSttusVO) {
		return smDtaGnlSttusDAO.selectSmDtaGnlSttusListTotalCount( smDtaGnlSttusVO);
	}
	
	/**
	 * 집계_자료_일반_현황(TN_SM_DTA_GNL_STTUS) 조사년도 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 smDtaGnlSttusVO
	 * @return TN_SM_DTA_GNL_STTUS 목록
	 * @exception Exception
	 */
	public List<SmDtaGnlSttusVO> selectSmDtaGnlSttusYearList(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
		return smDtaGnlSttusDAO.selectSmDtaGnlSttusYearList( smDtaGnlSttusVO);
	}

	 
	/**
     * 통합정보조회 > 조사정보 조회 
     * 데이터가 같은 년도로 2개 이상 들어가있을경우 오류 발생(데이터 확인 필요)
     * @author    : skc
     * @date      : 2017. 12. 22.
     * 
     * @param     : smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return    : SmDtaGnlSttusVO
     * @exception : Exception
     */
    public SmDtaGnlSttusVO selectSmDtaGnlSttusByCellId(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
        return smDtaGnlSttusDAO.selectSmDtaGnlSttusByCellId(smDtaGnlSttusVO);
    };
    
    /**
     * 통합정보조회 > 조사정보 조회
     * 조사자료 전체 년도 조회 
     * @author    : skc
     * @date      : 2017. 12. 22.
     * 
     * @param     : smDtaGnlSttusVO - 조회할 정보가 담긴 smDtaGnlSttusVO
     * @return    : List<SmDtaGnlSttusVO>
     * @exception : Exception
     */
    public List<SmDtaGnlSttusVO> selectSmDtaGnlSttusYearListByCellId(SmDtaGnlSttusVO smDtaGnlSttusVO) throws Exception {
        return smDtaGnlSttusDAO.selectSmDtaGnlSttusYearListByCellId(smDtaGnlSttusVO);
    };
}
