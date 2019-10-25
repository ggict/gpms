package kr.go.gg.gpms.cell10.service;

import java.util.List;

import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

/**
 * CELL_10
 * CELL_10
 *
 * @Class Name : Cell10Service.java
 * @Description : Cell10 Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface Cell10Service {

	/**
	 * CELL_10(CELL_10)을 등록한다.
	 * @param cell10VO - 등록할 정보가 담긴 Cell10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCell10(Cell10VO cell10VO) throws Exception;

	/**
	 * CELL_10(CELL_10)을 수정한다.
	 * @param cell10VO - 수정할 정보가 담긴 Cell10VO
	 * @return int형
	 * @exception Exception
	 */
	int updateCell10(Cell10VO cell10VO) throws Exception;

	/**
	 * CELL_10(CELL_10)을 삭제한다.
	 * @param cell10VO - 삭제할 정보가 담긴 Cell10VO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCell10(Cell10VO cell10VO) throws Exception;

	/**
	 * CELL_10(CELL_10)을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return 조회한 CELL_10
	 * @exception Exception
	 */
	Cell10VO selectCell10(Cell10VO cell10VO) throws Exception;

	/**
	 * CELL_10(CELL_10) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	List<Cell10VO> selectCell10List(Cell10VO cell10VO) throws Exception;

	/**
	 * CELL_10(CELL_10) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 총 갯수
	 * @exception
	 */
	int selectCell10ListTotalCount(Cell10VO cell10VO);

	/**
	 * CELL_10(CELL_10) id에 따른 노선정보를 가져온다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 노선정보
	 * @exception Exception
	 */
	List<Cell10VO> selectRouteInfoByCellID(Cell10VO cell10VO) throws Exception;
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 리스트를 가져온다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return CELL_10 노선정보 리스트
	 * @exception Exception
	 */
	List<Cell10VO> selectRouteInfoListByCellID(Cell10VO cell10VO) throws Exception;
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 총 갯수를 가져온다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @return int 노선정보 총 갯수
	 * @exception Exception
	 */
	int selectRouteInfoListByCellIDTotalCount(Cell10VO cell10VO) throws Exception;
	
	/**
	 * CELL_10(CELL_10) 셀타입을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCell10Type(CellSectVO cellSectVO) throws Exception;

	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyRoutLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyRoutLenStatsExcelResult(Cell10VO cell10VO) throws Exception;
		 
	 
	 /**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	List<Cell10VO> selectSrvyRoutLenStatsTablePageList(Cell10VO cell10VO) throws Exception;
 
	/**
	 * 통계 > 포장상태 조사구간 > 노선별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyRoutLenStatsExcel(Cell10VO cell10VO) throws Exception;
	 

	/**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyDeptLenStatsResult(Cell10VO cell10VO) throws Exception;

	 /**
	 * 통계 > 포장상태 조사구간 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyDeptLenStatsExcel(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyRoadLenStatsResult(Cell10VO cell10VO) throws Exception;
 
	/**
	 * 통계 > 포장상태 조사구간 > 도로등급별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectSrvyRoadLenStatsExcel(Cell10VO cell10VO) throws Exception;
	 

	/**
	 * 통계 > 노선현황 >  노선별 > 노선GPMS 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectRoutLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 >  노선별 > 노선GPMS 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectRoutLenStatsResultExcel(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectDeptLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectSrvyUniDeptLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 관리기관별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectDeptLenStatsResultExcel(Cell10VO cell10VO) throws Exception;
	 
	/**
	 * 통계 > 노선현황 >  도로등급 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectGradLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectSrvyUniRoadLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectSrvyUniRoutLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	/**
	 * 통계 > 노선현황 >  도로등급 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectGradLenStatsResultExcel(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 노선별 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	 List<Cell10VO> selectRoutStatsPageList(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 노선별 통계 엑셀목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectRoutNjrStatsExcelResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectRoutNjrAllStatsExcelResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectRoutJbStatsExcelResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectRoutJbAllStatsExcelResult(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 관리기관별 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	 List<Cell10VO> selectDeptStatsPageList(Cell10VO cell10VO) throws Exception;
	 
	 //관리기관별 통계 엑셀 목록
	 List selectDeptStatsPageListExcel(Cell10VO cell10VO) throws Exception;
		
	 /**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectTrackLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectUniTrackLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 차로별 GPMS 통계 엑셀 목록을 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectTrackLenStatsResultExcel(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 차로별 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	 List<Cell10VO> selectTrakStatsPageList(Cell10VO cell10VO) throws Exception;
	 
	 //차로별 연장 엑셀 목록
	 List selectTrakStatsPageListExcel(Cell10VO cell10VO) throws Exception;
	 
	 //국토부 시군구별 도로등급별 통계 조회
	 List selectUniAdmGradLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 //gpms 시군구별 도로등급별 통계 조회
	 List selectAdmGradLenStatsResult(Cell10VO cell10VO) throws Exception;
		 
	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectAdmLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	 List selectUniAdmLenStatsResult(Cell10VO cell10VO) throws Exception;
	 
	/**
	 * 통계 > 노선현황 > 시군구별 통계(GPMS)를 엑셀 목록 조회한다.
	 * @param cell10VO - 조회할 정보가 담긴 Cell10VO
	 * @exception Exception
	 */
	 List selectAdmLenStatsResultExcel(Cell10VO cell10VO) throws Exception;
	 
	 //시군구별 국토부 엑셀 목록 조회
	 List selectAdmStatsPageListExcel(Cell10VO cell10VO) throws Exception;
	 
	 /**
	 * 통계 > 노선현황 > 시군구별 통계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cell10VO
	 * @return CELL_10 목록
	 * @exception Exception
	 */
	List<Cell10VO> selectAdmStatsPageList(Cell10VO cell10VO) throws Exception;
	
	//통계 연도를 조회한다.
	List<Cell10VO> selectStatsYearList(Cell10VO cell10VO) throws Exception;
	
	List<Cell10VO> selectCellList(Cell10VO cell10VO) throws Exception;
    int selectCellListCnt(Cell10VO cell10VO) throws Exception;
    
    // 경위도 좌표 이동 > 좌표 입력 시 cell 정보 조회
    List<Cell10VO> selectCellInfoByLonLat(Cell10VO cell10VO) throws Exception;
    int selectCellInfoByLonLatCnt(Cell10VO cell10VO) throws Exception;
}

