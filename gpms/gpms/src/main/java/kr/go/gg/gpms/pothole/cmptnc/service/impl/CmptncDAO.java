package kr.go.gg.gpms.pothole.cmptnc.service.impl;

import java.util.List;

import kr.go.gg.gpms.pothole.cmptnc.service.model.CmptncVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 관할구역 검색 DAO
 * @Classname : CmptncDAO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@Repository("cmptncDAO")
public class CmptncDAO extends BaseDAO {

	/**
	 * 관할_노선(CMPTNC_ZONE)을 조회한다.
	 * @param cmptncVO - 조회할 정보가 담긴 cmptncVO
	 * @return 조회 결과
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CmptncVO> selectCmptncList(CmptncVO cmptncVO) throws Exception {
		return (List<CmptncVO>)list("cmptncDAO.selectCmptncList", cmptncVO);
	}

	/**
	 * 관할_노선(CMPTNC_ZONE) 총 갯수를 조회한다.
	 * @param cmptncVO - 조회할 정보가 담긴 cmptncVO
	 * @return 조회 결과
	 * @exception Exception
	 */
	public int selectCmptncListTotalCount(CmptncVO cmptncVO) {
		return (Integer) select("cmptncDAO.selectCmptncListTotalCount", cmptncVO);
	}

	/**
	 * 관할_노선(CMPTNC_ZONE) 엑셀 저장
	 * @param cmptncVO - 조회할 정보가 담긴 cmptncVO
	 * @return 엑셀파일
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
    public List selectCmptncListExcel(CmptncVO cmptncVO) {
		return (List) list("cmptncDAO.selectCmptncListExcel", cmptncVO);
	}

	/**
	 * 관할_노선(CMPTNC_ZONE) 수정한다.
	 * @param cmptncVO - 조회할 정보가 담긴 cmptncVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCmptnc(CmptncVO cmptncVO) {
		return (Integer) update("cmptncDAO.updateCmptnc", cmptncVO);
	}

	/**
	 * 관할_노선(TH_CMPTNC_ZONE) 수정 로그 등록
	 * @param cmptncVO - 조회할 정보가 담긴 cmptncVO
	 * @return 로그 등록
	 * @exception Exception
	 */
	public String insertCmptncLog(CmptncVO cmptncVO) {
		return (String) insert("cmptncDAO.insertCmptncLog", cmptncVO);
	}

	/**
     * 담당자 연락처 조회
     * @author    : JOY
     * @date      : 2018. 3. 19.
     *
     * @param     : CmptncVO - 조회할 정보가 담긴 CmptncVO
     * @return    : CmptncVO
     * @exception : Exception
     */
    public CmptncVO selectChargerCttpc(CmptncVO cmptncVO) throws Exception {
        return (CmptncVO) select("cmptncDAO.selectChargerCttpc", cmptncVO);
    }

    /**
     * 담당자, 연락처 조회
     * @author    : JOY
     * @date      : 2018. 3. 21.
     *
     * @param     : CmptncVO - 조회할 정보가 담긴 CmptncVO
     * @return    : CmptncVO
     * @exception : Exception
     */
    public CmptncVO selectChargerNm(CmptncVO cmptncVO) throws Exception {
        return (CmptncVO) select("cmptncDAO.selectChargerNm", cmptncVO);
    }


    /**
     * 포트홀_관할_구역_담당자 목록을 조회한다.
     * @author    : lsk
     * @date      : 2019. 6. 27.
     *
     * @param     : CmptncVO - 조회할 정보가 담긴 CmptncVO
     * @return    : List<CmptncVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmptncVO> selectCmptncZonePocList(CmptncVO cmptncDAO) throws Exception {
        return (List<CmptncVO>)list("cmptncDAO.selectCmptncZonePocList", cmptncDAO);
    }

}
