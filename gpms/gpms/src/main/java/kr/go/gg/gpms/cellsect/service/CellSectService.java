package kr.go.gg.gpms.cellsect.service;

import java.util.List;

import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

/**
 * CELL_SECT
 * CELL_SECT
 *
 * @Class Name : CellSectService.java
 * @Description : CellSect Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CellSectService {

	/**
	 * CELL_SECT(CELL_SECT)을 등록한다.
	 * @param cellSectVO - 등록할 정보가 담긴 CellSectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCellSect(CellSectVO cellSectVO) throws Exception;

	/**
	 * CELL_SECT(CELL_SECT)을 수정한다.
	 * @param cellSectVO - 수정할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCellSect(CellSectVO cellSectVO) throws Exception;

	/**
	 * CELL_SECT(CELL_SECT)을 삭제한다.
	 * @param cellSectVO - 삭제할 정보가 담긴 cellSectVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCellSect(CellSectVO cellSectVO) throws Exception;

	/**
	 * CELL_SECT(CELL_SECT)을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	CellSectVO selectCellSect(CellSectVO cellSectVO) throws Exception;

	/**
	 * CELL_SECT(CELL_SECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cellSectVO
	 * @return CELL_SECT 목록
	 * @exception Exception
	 */
	List<CellSectVO> selectCellSectList(CellSectVO cellSectVO) throws Exception;

	/**
	 * CELL_SECT(CELL_SECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cellSectVO
	 * @return CELL_SECT 총 갯수
	 * @exception
	 */
	int selectCellSectListTotalCount(CellSectVO cellSectVO);

	/**
	 * CELL_SECT(CELL_SECT) 섹션구분을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	int updateSectSe(CellSectVO cellSectVO) throws Exception;
	
	/**
	 * 총 노선 연장 및 면적을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	CellSectVO selectCellSectLenArea(CellSectVO cellSectVO) throws Exception;
}

