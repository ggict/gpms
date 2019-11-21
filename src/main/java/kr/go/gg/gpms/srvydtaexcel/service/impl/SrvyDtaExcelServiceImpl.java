package kr.go.gg.gpms.srvydtaexcel.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
import kr.go.gg.gpms.srvydtaexcel.service.SrvyDtaExcelService;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelServiceImpl.java
 * @Description : SrvyDtaExcel Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("srvyDtaExcelService")
public class SrvyDtaExcelServiceImpl extends AbstractServiceImpl implements SrvyDtaExcelService {

	@Resource(name = "srvyDtaExcelDAO")
	private SrvyDtaExcelDAO srvyDtaExcelDAO;

	//@Resource(name="SrvyDtaExcelIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 등록한다.
	 * @param srvyDtaExcelVO - 등록할 정보가 담긴 SrvyDtaExcelVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//srvyDtaExcelVO.setId(id);

		return srvyDtaExcelDAO.insertSrvyDtaExcel( srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 수정한다.
	 * @param srvyDtaExcelVO - 수정할 정보가 담긴 SrvyDtaExcelVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.updateSrvyDtaExcel( srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 삭제한다.
	 * @param srvyDtaExcelVO - 삭제할 정보가 담긴 SrvyDtaExcelVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.deleteSrvyDtaExcel( srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return 조회한 TN_SRVY_DTA_EXCEL
	 * @exception Exception
	 */
	public SrvyDtaExcelVO selectSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		SrvyDtaExcelVO resultVO = srvyDtaExcelDAO.selectSrvyDtaExcel( srvyDtaExcelVO);
		 
		return resultVO;
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaExcelList( srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.selectSrvyDtaExcelListTotalCount( srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadResultList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaExcelUploadResultList( srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelUploadResultCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.selectSrvyDtaExcelUploadResultCount(srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadFileList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaExcelUploadFileList(srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelUploadFileCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.selectSrvyDtaExcelUploadFileCount(srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록
	 * @exception
	 */
	public List<SrvyDtaExcelVO> selectSrvyDtaCompList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaCompList(srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaCompCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.selectSrvyDtaCompCount(srvyDtaExcelVO);
	}
	
	/**
	 * 조사_자료 등록 대상 엑셀별 목록
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return 조사_자료 등록 대상 엑셀별 목록
	 * @exception
	 */
	@Override
	public List<SrvyDtaExcelVO> selectSrvyDtaSrvyNoList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaSrvyNoList(srvyDtaExcelVO);
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/api/srvyDtaExcelList.do"
	 * @exception Exception
	 */
	public List<SrvyDtaExcelVO> srvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.srvyDtaExcelList(srvyDtaExcelVO);
	}
	
	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 갯수를 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int srvyDtaExcelListCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.srvyDtaExcelListCount(srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 등록일자를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public List searchCreatDt(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.searchCreatDt(srvyDtaExcelVO);
	}
	
	/**
	 * 조사자료 예측 대상 목록을 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return 
	 * @exception Exception
	 */
	public List<SrvyDtaExcelVO> selectSrvyDtaPredctList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return srvyDtaExcelDAO.selectSrvyDtaPredctList(srvyDtaExcelVO);
	}
	
	/**
	 * 조사자료 예측 대상 목록의 갯수를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int selectSrvyDtaPredctListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return srvyDtaExcelDAO.selectSrvyDtaPredctListTotalCount(srvyDtaExcelVO);
	}
	
	/**
     * 포장상태 평가정보 조회
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return 
     * @exception Exception
     */
    public List<MummSctnSrvyDtaVO> srvyDtaEvlInfoDetailList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return srvyDtaExcelDAO.srvyDtaEvlInfoDetailList(mummSctnSrvyDtaVO);
    }
    
    /**
     * 포장상태 평가정보 갯수 조회
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @exception Exception
     */
    public int srvyDtaEvlInfoDetailListCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
        return srvyDtaExcelDAO.srvyDtaEvlInfoDetailListCnt(mummSctnSrvyDtaVO);
    };
    
    /**
     * 관리자 > 수식관리 > 수식 변수 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : result
     * @exception : Exception
     */
    public List<PavFrmulaVO> srvyDtaEvlInitFmlaVar(PavFrmulaVO pavFrmulaVO) throws Exception {
        return srvyDtaExcelDAO.srvyDtaEvlInitFmlaVar(pavFrmulaVO);
    };
    
    /**
     * 관리자 > 수식관리 > 수식 번호 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @exception : Exception
     */
    public PavFrmulaVO selectPavFrmulaNo(PavFrmulaVO pavFrmulaVO) throws Exception {
        return srvyDtaExcelDAO.selectPavFrmulaNo(pavFrmulaVO);
    };
    
    /**
     * 관리자 > 수식관리 > 수식 변경
     * @author    : JOY
     * @date      : 2017. 11. 14.
     * 
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePavFrmulaVar(PavFrmulaVO pavFrmulaVO) throws Exception {
        return srvyDtaExcelDAO.updatePavFrmulaVar(pavFrmulaVO);
    }

	@Override
	public List selectSrvyDtaEvlInfoListExcel(
			MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
		// TODO Auto-generated method stub
		return srvyDtaExcelDAO.selectSrvyDtaEvlInfoListExcel(mummSctnSrvyDtaVO);
	}

	@Override
	public List srvyDtaExcelListExcelDownload(
			SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		// TODO Auto-generated method stub
		return srvyDtaExcelDAO.srvyDtaExcelListExcelDownload(srvyDtaExcelVO);
	}
	
}
