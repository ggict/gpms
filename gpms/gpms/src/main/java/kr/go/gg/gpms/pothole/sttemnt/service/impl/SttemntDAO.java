package kr.go.gg.gpms.pothole.sttemnt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.gg.gpms.pothole.sttemnt.service.model.RpairVO;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * 포트홀 신고관리 DAO
 * @Classname : SttemntDAO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@Repository("sttemntDAO")
public class SttemntDAO extends BaseDAO {

    /**
     * 포트홀 신고정보를 수정한다. (PTH_STTMNT)
     * @author    : JOY
     * @date      : 2018. 3. 21.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePthSttmnt(SttemntVO sttemntVO) throws Exception {
    	return update("sttemntDAO.updatePthSttmnt", sttemntVO);
    }

    /**
     * 포트홀 신고정보를 수정한다.
     * @author    : JOY
     * @date      : 2018. 1. 11.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePotholeSttemnt(SttemntVO sttemntVO) throws Exception {
        return update("sttemntDAO.updatePotholeSttemnt", sttemntVO);
    }

    /**
     * 포트홀 신고정보를 등록한다.
     * @author    : lsk
     * @date      : 2019. 6. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int insertPotholeSttemnt(SttemntVO sttemntVO) throws Exception {
        return update("sttemntDAO.insertPotholeSttemnt", sttemntVO);
    }

    /**
     * 중복 포트홀 신고정보를 수정한다.
     * @author    : JOY
     * @date      : 2018. 6. 04.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updateDplctSttemntAt(SttemntVO sttemntVO) throws Exception {
        return update("sttemntDAO.updateDplctSttemntAt", sttemntVO);
    }

    /**
     * 포트홀 신고정보 담당자명, 연락처를 삭제한다.
     * @author    : JOY
     * @date      : 2018. 1. 22.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int deletePotholeSttemnt(SttemntVO sttemntVO) throws Exception {
        return delete("sttemntDAO.deletePotholeSttemnt", sttemntVO);
    }

    /**
     * 포트홀 신고정보 관리번호를 조회한다.
     * @author    : lsk
     * @date      : 2019. 6. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectPotholeRgNo(SttemntVO sttemntVO) throws Exception {
        return (SttemntVO) select("sttemntDAO.selectPotholeRgNo", sttemntVO);
    }


    /**
     * 위치정보의 주소를 조회한다.
     * @author    : lsk
     * @date      : 2019. 6. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectPotholeLAdres(SttemntVO sttemntVO) throws Exception {
        return (SttemntVO) select("sttemntDAO.selectPotholeLAdres", sttemntVO);
    }

    /**
     * 포트홀 신고정보를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectPotholeSttemnt(SttemntVO sttemntVO) throws Exception {
        return (SttemntVO) select("sttemntDAO.selectPotholeSttemnt", sttemntVO);
    }

    /**
     * 포트홀 신고관리 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPotholeSttemntList(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>)list("sttemntDAO.selectPotholeSttemntList", sttemntVO);
    }

    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPotholeSttemntListTEST(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>)list("sttemntDAO.selectPotholeSttemntListTEST", sttemntVO);
    }

    /**
     * 포트홀 신고관리 목록 개수를 조회한다.
     * @author    : JOY
     * @date      : 2018. 1. 10.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    public int selectPotholeSttemntListTotalCount(SttemntVO sttemntVO) {
        return (Integer) select ("sttemntDAO.selectPotholeSttemntListCount", sttemntVO);
    }

    /**
     * 포트홀 신고관리 목록의 파손유형 수를 조회한다.
     * @author    : YYK
     * @date      : 2018. 2. 11.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPotholeSttemntListPrcsCount(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>)list("sttemntDAO.selectPotholeSttemntListPrcsCount", sttemntVO);
    }

    /**
     * 포트홀_신고(PTH_STTMNT) 엑셀저장
     * @author    : yyk
     * @date      : 2018. 1. 11.
     * @param sttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return 엑셀파일
     * @exception Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectSttemntListExcel(SttemntVO sttemntVO) throws Exception {
        return (List) list("sttemntDAO.selectSttemntListExcel", sttemntVO);
    }

    /**
     * 포트홀_신고(PTH_STTMNT) 레이어 조회
     * @author    : YYK
     * @date      : 2018. 2. 12.
     * @param sttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return 엑셀파일
     * @exception Exception
     */
    @SuppressWarnings("rawtypes")
    public List selectPotholeSttemntLayer(SttemntVO sttemntVO) throws Exception {
        return (List) list("sttemntDAO.selectPotholeSttemntLayer", sttemntVO);
    }

    /**
     * 지도 - 처리상태 별 데이터 조회
     * @author    : JOY
     * @date      : 2018. 2. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPrcsSttusList(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>) list("sttemntDAO.selectPrcsSttusList", sttemntVO);
    }

    /**
     * 지도 - 파손유형 별 데이터 조회
     * @author    : JOY
     * @date      : 2018. 2. 26.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectDmgTypeList(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>) list("sttemntDAO.selectDmgTypeList", sttemntVO);
    }

    /**
     * 지도 - 민자도로사업자 신고 건 조회
     * @author    : JOY
     * @date      : 2018. 2. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int selectPrvRdOprtCount(SttemntVO sttemntVO) throws Exception {
        return (Integer) select("sttemntDAO.selectPrvRdOprtCount", sttemntVO);
    }

    /**
     * 중복 신고 목록을 조회한다.
     * @author    : JOY
     * @date      : 2018. 3. 7.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectOverlapSttemnt(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>) list("sttemntDAO.selectOverlapSttemnt", sttemntVO);
    }

    /**
     * 중복 신고 목록 개수를 조회한다.
     * @author    : JOY
     * @date      : 2018. 3. 7.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int selectOverlapCnt(SttemntVO sttemntVO) throws Exception {
        return (Integer) select("sttemntDAO.selectOverlapCnt", sttemntVO);
    }

    /**
     * 보정한 위치의 경위도 값을 조회한다.
     * @author    : JOY
     * @date      : 2018. 4. 4.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : Map<String, Object>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> selectLatLon(SttemntVO sttemntVO) throws Exception {
        return (Map<String, Object>) select("sttemntDAO.selectLatLon", sttemntVO);
    }

    /**
     * 위치보정 후 관할구역 및 주소를 맵핑한다.
     * @author    : JOY
     * @date      : 2018. 4. 2.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : HashMap
     * @exception : Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap procPrcPthCortnLoc(SttemntVO sttemntVO) throws Exception {

        HashMap param = new HashMap();

        param.put("p_PTH_RG_NO",    sttemntVO.getPTH_RG_NO());
        param.put("p_TM_X",         sttemntVO.getTM_X());
        param.put("p_TM_Y",         sttemntVO.getTM_Y());
        param.put("p_STTEMNT_DT",   sttemntVO.getSTTEMNT_DT());
        param.put("p_VHCLE_NO",     sttemntVO.getVHCLE_NO());

        String sqlId = "sttemntDAO.PRC_PTH_CORTN_LOC";

        HashMap resultVO = (HashMap) call(sqlId, param);

        return resultVO;
    }

    /**
     * 사용자의 상위 부서 조회
     * @author    : JOY
     * @date      : 2018. 4. 19.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : String
     * @exception : Exception
     */
    public String selectHighDeptCode(SttemntVO sttemntVO) throws Exception {
        return (String) select("sttemntDAO.selectHighDeptCode", sttemntVO);
    }


    /**
     * 포트홀 신고 정보를 삭제한다.
     * @author    : lsk
     * @date      : 2019. 6. 20.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int deletePotholeSttemntAll(SttemntVO sttemntVO) throws Exception {
        return delete("sttemntDAO.deletePotholeSttemntAll", sttemntVO);
    }


    /**
     * 포트홀 신고정보 위치 수정 시 좌표정보를 수정한다.
     * @author    : lsk
     * @date      : 2019. 6. 25.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePotholeXY(SttemntVO sttemntVO) throws Exception {
        return delete("sttemntDAO.updatePotholeXY", sttemntVO);
    }


    /**
     * 문자제한시간에 등록된 관할구역별 신고내역 등록 건수 조회한다.
     * @author    : lsk
     * @date      : 2019. 7. 5.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPotholeSttemntGrpCount(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>)list("sttemntDAO.selectPotholeSttemntGrpCount", sttemntVO);
    }


    /**
     * 문자제한시간에 등록된 포트홀_관리_번호를 조회한다.
     * @author    : lsk
     * @date      : 2019. 7. 5.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SttemntVO> selectPotholeSttemntGrpList(SttemntVO sttemntVO) throws Exception {
        return (List<SttemntVO>)list("sttemntDAO.selectPotholeSttemntGrpList", sttemntVO);
    }


    /**
     * 포트홀 신고정보 SMS 전송 설정 시간을 가져온다.
     * @author    : lsk
     * @date      : 2019. 7. 06.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : SttemntVO
     * @exception : Exception
     */
    public SttemntVO selectPotholeSMSTime(SttemntVO sttemntVO) throws Exception {
        return (SttemntVO) select("sttemntDAO.selectPotholeSMSTime", sttemntVO);
    }


    /**
     * 포트홀 신고정보 SMS 전송 후 상태값을 수정한다. (PTH_STTMNT)
     * @author    : lsk
     * @date      : 2019. 7. 7.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePthSttmntSttus(SttemntVO sttemntVO) throws Exception {
    	return update("sttemntDAO.updatePthSttmntSttus", sttemntVO);
    }


    /**
     * 포트홀 신고정보 SMS 전송 후 상태값을 수정한다. (TN_POTHOLE_STTEMNT)
     * @author    : lsk
     * @date      : 2019. 7. 7.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : int
     * @exception : Exception
     */
    public int updatePotholeSttemntSttus(SttemntVO sttemntVO) throws Exception {
    	return update("sttemntDAO.updatePotholeSttemntSttus", sttemntVO);
    }

    /**
     * 문자제한시간에 등록된 관할구역별 신고내역 등록 건수 조회한다.
     * @author    : lhj
     * @date      : 2019. 8. 1.
     *
     * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
     * @return    : List<SttemntVO>
     * @exception : Exception
     */
    public SttemntVO selectPthMPhoto(SttemntVO sttemntVO) throws Exception {
        return (SttemntVO) select("sttemntDAO.selectPthMPhoto", sttemntVO);
    }


}
