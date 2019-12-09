


package kr.go.gg.gpms.cell10.service.impl;

import java.util.List;

import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * CELL_10
 *
 * @Class Name : Cell10DAO.java
 * @Description : Cell10 DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cell10DAO")
public class Cell10DAO extends BaseDAO {

	/**
	 * CELL_10(CELL_10)을 등록한다.
	 * @param cell10VO - 등록할 정보가 담긴 Cell10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCell10(Cell10VO cell10VO) throws Exception {
		return (String) insert("cell10DAO.insertCell10", cell10VO);
	}

	/**
	 * CELL_10(CELL_10)을 수정한다.
	 * @param cell10VO - 수정할 정보가 담긴 Cell10VO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCell10(Cell10VO cell10VO) throws Exception {
		return update("cell10DAO.updateCell10", cell10VO);
	}

	/**
	 * CELL_10(CELL_10)을 삭제한다.
	 * @param cell10VO - 삭제할 정보가 담긴 Cell10VO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCell10(Cell10VO cell10VO) throws Exception {
		return delete("cell10DAO.deleteCell10", cell10VO);
	}

	/**
	 * CELL_10(CELL_10)을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return 조회한 CELL_10
	 * @exception Exception
	 */
	public Cell10VO selectCell10(Cell10VO cell10VO) throws Exception {
		return (Cell10VO) select("cell10DAO.selectCell10", cell10VO);
	}

	/**
	 * CELL_10(CELL_10) 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectCell10List(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectCell10List", cell10VO);
	}

	/**
	 * CELL_10(CELL_10) 총 갯수를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 총 갯수
	 * @exception
	 */
	public int selectCell10ListTotalCount(Cell10VO cell10VO) {
		return (Integer) select("cell10DAO.selectCell10ListTotalCount", cell10VO);
	}
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보를 가져온다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 노선정보
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectRouteInfoByCellID(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectRouteInfoByCellID", cell10VO);
	}
	
	/**
	 * CELL_10(CELL_10) 셀타입을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCell10Type(CellSectVO cellSectVO) throws Exception {
		return update("cell10DAO.updateCell10Type", cellSectVO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyRoutLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyRoutLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyRoutLenStatsTablePageList(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectSrvyRoutLenStatsTablePageList", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyRoutLenStatsExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyRoutLenStatsExcel", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyDeptLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyDeptLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyDeptLenStatsExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyDeptLenStatsExcel", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyRoadLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyRoadLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyRoadLenStatsExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyRoadLenStatsExcel", cell10VO);
	}


	/**
	 * 통계 > 노선현황 >  노선별 > 노선GPMS 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectRoutLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectRoutLenStatsResult", cell10VO);
	}

	/**
	 * 통계 > 노선현황 >  노선별 > 노선GPMS 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectRoutLenStatsResultExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectRoutLenStatsResultExcel", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectDeptLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectDeptLenStatsResult", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyUniDeptLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyUniDeptLenStatsResult", cell10VO);
	}

	/**
	 * 통계 > 노선현황 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectDeptLenStatsResultExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectDeptLenStatsResultExcel", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 >  도로등급 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectGradLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectGradLenStatsResult", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyUniRoadLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyUniRoadLenStatsResult", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectSrvyUniRoutLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectSrvyUniRoutLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 >  도로등급 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectGradLenStatsResultExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectGradLenStatsResultExcel", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectRoutStatsPageList(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectRoutStatsPageList", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectDeptStatsPageList(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectDeptStatsPageList", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectTrackLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectTrackLenStatsResult", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectUniTrackLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectUniTrackLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectTrackLenStatsResultExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectTrackLenStatsResultExcel", cell10VO);
	}

	/**
	 * 통계 > 노선현황 > 차로별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectTrakStatsPageList(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectTrakStatsPageList", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectAdmLenStatsResult", cell10VO);
	}
	
	//차로별 연장 엑셀 목록 
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectTrakStatsPageListExcel(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectTrakStatsPageListExcel", cell10VO);
	}
	
	//국토부 시군구별 도로등급별 통계 조회
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectUniAdmGradLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectUniAdmGradLenStatsResult", cell10VO);
	}
	
	//gpms 시군구별 도로등급별 통계 조회
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmGradLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectAdmGradLenStatsResult", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectUniAdmLenStatsResult(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectUniAdmLenStatsResult", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 엑셀 목록 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmLenStatsResultExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectAdmLenStatsResultExcel", cell10VO);
	}
	
	//시군구별 국토부 엑셀 목록 조회
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmStatsPageListExcel(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectAdmStatsPageListExcel", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 시군구별 통계를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmStatsPageList(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectAdmStatsPageList", cell10VO);
	}
	
	/**
	 * 통계 > 노선현황 > 시군구별 차트 통계를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectAdmStatsPageChart(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectAdmStatsPageChart", cell10VO);
	}
	
	//통계 연도를 조회한다.
	@SuppressWarnings("unchecked")
	public List<Cell10VO> selectStatsYearList(Cell10VO cell10VO) throws Exception {
		return (List)list("cell10DAO.selectStatsYearList", cell10VO);
	}
	
	@SuppressWarnings("unchecked")
    public List<Cell10VO> selectCellList(Cell10VO cell10VO) throws Exception {
        return (List<Cell10VO>) list("cell10DAO.selectCellList", cell10VO);
    }
    
    public int selectCellListCnt(Cell10VO cell10VO) throws Exception {
        return (Integer) select("cell10DAO.selectCellListCnt", cell10VO);
    }
    
 // 경위도 좌표 이동 > 좌표 입력 시 cell 정보 조회
    @SuppressWarnings("unchecked")
    public List<Cell10VO> selectCellInfoByLonLat(Cell10VO cell10VO) throws Exception {
        return (List<Cell10VO>) list("cell10DAO.selectCellInfoByLonLat", cell10VO);
    }
    
    public int selectCellInfoByLonLatCnt(Cell10VO cell10VO) throws Exception
    {      
        return (Integer) select("cell10DAO.selectCellInfoByLonLatCnt", cell10VO);
    }
    
	public List<Cell10VO> selectTest(Cell10VO cell10VO) throws Exception {
		return (List<Cell10VO>)list("cell10DAO.selectTest", cell10VO);
	}

}
