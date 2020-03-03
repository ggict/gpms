


package kr.go.gg.gpms.cellsect.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

/**
 * CELL_SECT
 *
 * @Class Name : cellSectDAO.java
 * @Description : CellSect DAO Class
 * @Modification Information
 *
 * @author kmh
 * @since 2017-08-22
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("cellSectDAO")
public class CellSectDAO extends BaseDAO {

	/**
	 * CELL_SECT(CELL_SECT)을 등록한다.
	 * @param cellSectVO - 등록할 정보가 담긴 CellSectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCellSect(CellSectVO cellSectVO) throws Exception {
		return (String) insert("cellSectDAO.insertCellSect", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 수정한다.
	 * @param cellSectVO - 수정할 정보가 담긴 CellSectVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCellSect(CellSectVO cellSectVO) throws Exception {
		return update("cellSectDAO.updateCellSect", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 삭제한다.
	 * @param cellSectVO - 삭제할 정보가 담긴 CellSectVO
	 * @return 삭제 결과
	 * @exception Exception
	 */
	public int deleteCellSect(CellSectVO cellSectVO) throws Exception {
		return delete("cellSectDAO.deleteCellSect", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 CellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	public CellSectVO selectCellSect(CellSectVO cellSectVO) throws Exception {
		return (CellSectVO) select("cellSectDAO.selectCellSect", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 목록을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 CellSectVO
	 * @return CELL_SECT 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CellSectVO> selectCellSectList(CellSectVO cellSectVO) throws Exception {
		return (List<CellSectVO>)list("cellSectDAO.selectCellSectList", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 총 갯수를 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 CellSectVO
	 * @return CELL_SECT 총 갯수
	 * @exception
	 */
	public int selectCellSectListTotalCount(CellSectVO cellSectVO) {
		return (Integer) select("cellSectDAO.selectCellSectListTotalCount", cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 섹션구분을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSectSe(CellSectVO cellSectVO) throws Exception {
		return update("cellSectDAO.updateSectSe", cellSectVO);
	}

	/**
	 * 총 노선 연장 및 면적을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 CellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	public CellSectVO selectCellSectLenArea(CellSectVO cellSectVO) throws Exception {
		return (CellSectVO) select("cellSectDAO.selectCellSectLenArea", cellSectVO);
	}
}
