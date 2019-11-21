


package kr.go.gg.gpms.srvydtaexcel.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.go.gg.gpms.cmmn.service.impl.CmmnDAO;
import kr.go.gg.gpms.mummsctnsrvydta.service.model.MummSctnSrvyDtaVO;
import kr.go.gg.gpms.pavfrmula.service.model.PavFrmulaVO;
import kr.go.gg.gpms.srvydtaexcel.service.model.SrvyDtaExcelVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 조사_자료_엑셀
 *
 * @Class Name : SrvyDtaExcelDAO.java
 * @Description : SrvyDtaExcel DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyDtaExcelDAO")
public class SrvyDtaExcelDAO extends BaseDAO {


	@Resource(name = "cmmnDAO")
	private CmmnDAO cmmnDAO;


	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 등록한다.
	 * @param srvyDtaExcelVO - 등록할 정보가 담긴 SrvyDtaExcelVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (String) insert("srvyDtaExcelDAO.insertSrvyDtaExcel", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 수정한다.
	 * @param srvyDtaExcelVO - 수정할 정보가 담긴 SrvyDtaExcelVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return update("srvyDtaExcelDAO.updateSrvyDtaExcel", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 삭제한다.
	 * @param srvyDtaExcelVO - 삭제할 정보가 담긴 SrvyDtaExcelVO
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return delete("srvyDtaExcelDAO.deleteSrvyDtaExcel", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL)을 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return 조회한 TN_SRVY_DTA_EXCEL
	 * @exception Exception
	 */
	public SrvyDtaExcelVO selectSrvyDtaExcel(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (SrvyDtaExcelVO) select("srvyDtaExcelDAO.selectSrvyDtaExcel", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 목록을 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaExcelList", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 총 갯수를 조회한다.
	 * @param srvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 총 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaExcelListTotalCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadResultList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaExcelUploadResultList", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelUploadResultCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaExcelUploadResultCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록을 조회.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaExcelUploadFileList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaExcelUploadFileList", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 파일 업로드 결과 상세 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 파일 업로드 결과  상세 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaExcelUploadFileCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaExcelUploadFileCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaCompList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaCompList", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 조사자료 등록 대상 목록 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return TN_SRVY_DTA_EXCEL 조사자료 등록 대상 목록 갯수
	 * @exception
	 */
	public int selectSrvyDtaCompCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaCompCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료 등록 대상 엑셀별 목록
	 * @param searchVO - 조회할 정보가 담긴 srvyDtaExcelVO
	 * @return 조사_자료 등록 대상 엑셀별 목록
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaSrvyNoList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaSrvyNoList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return "/api/srvyDtaExcelList.do"
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> srvyDtaExcelList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.srvyDtaExcelList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료엑셀(TN_SRVY_DTA_EXCEL)목록을 갯수를 조회한다. (paging)
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int srvyDtaExcelListCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.srvyDtaExcelListCount", srvyDtaExcelVO);
	}

	/**
	 * 조사_자료_엑셀(TN_SRVY_DTA_EXCEL) 등록일자를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> searchCreatDt(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List)list("srvyDtaExcelDAO.searchCreatDt", srvyDtaExcelVO);
	}

	/**
	 * 조사자료 예측 대상 목록을 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @return
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyDtaExcelVO> selectSrvyDtaPredctList(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return (List<SrvyDtaExcelVO>)list("srvyDtaExcelDAO.selectSrvyDtaPredctList", srvyDtaExcelVO);
	}

	/**
	 * 조사자료 예측 대상 목록의 갯수를 조회한다.
	 * @param SrvyDtaExcelVO - 조회할 정보가 담긴 SrvyDtaExcelVO
	 * @exception Exception
	 */
	public int selectSrvyDtaPredctListTotalCount(SrvyDtaExcelVO srvyDtaExcelVO) {
		return (Integer) select("srvyDtaExcelDAO.selectSrvyDtaPredctListTotalCount", srvyDtaExcelVO);
	}

	/**
     * 포장상태 평가정보 조회
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @return
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<MummSctnSrvyDtaVO> srvyDtaEvlInfoDetailList(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return (List<MummSctnSrvyDtaVO>)list("srvyDtaExcelDAO.srvyDtaEvlInfoDetailList", mummSctnSrvyDtaVO);
    }


    public List selectSrvyDtaEvlInfoListExcel(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) throws Exception {
        return cmmnDAO.getSelectList("srvyDtaExcelDAO.selectSrvyDtaEvlInfoListExcel", mummSctnSrvyDtaVO);
    }


    public List srvyDtaExcelListExcelDownload(SrvyDtaExcelVO srvyDtaExcelVO) throws Exception {
		return cmmnDAO.getSelectList("srvyDtaExcelDAO.srvyDtaExcelListExcelDownload", srvyDtaExcelVO);
	}

    /**
     * 조사자료 예측 대상 목록의 갯수를 조회한다.
     * @param mummSctnSrvyDtaVO - 조회할 정보가 담긴 mummSctnSrvyDtaVO
     * @exception Exception
     */
    public int srvyDtaEvlInfoDetailListCnt(MummSctnSrvyDtaVO mummSctnSrvyDtaVO) {
        return (Integer) select("srvyDtaExcelDAO.srvyDtaEvlInfoDetailListCnt", mummSctnSrvyDtaVO);
    }

    /**
     * 관리자 > 수식관리 > 수식 변수 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     *
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @return    : result
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<PavFrmulaVO> srvyDtaEvlInitFmlaVar(PavFrmulaVO pavFrmulaVO) throws Exception {
        return (List<PavFrmulaVO>)list("srvyDtaExcelDAO.srvyDtaEvlInitFmlaVar", pavFrmulaVO);
    }

    /**
     * 관리자 > 수식관리 > 수식 번호 조회
     * @author    : JOY
     * @date      : 2017. 11. 14.
     *
     * @param     : pavFrmulaVO - 조회할 정보가 담긴 pavFrmulaVO
     * @exception : Exception
     */
    public PavFrmulaVO selectPavFrmulaNo(PavFrmulaVO pavFrmulaVO) throws Exception {
        return (PavFrmulaVO)select("srvyDtaExcelDAO.selectPavFrmulaNo", pavFrmulaVO);
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
        return update("srvyDtaExcelDAO.updatePavFrmulaVar", pavFrmulaVO);
    }
}
