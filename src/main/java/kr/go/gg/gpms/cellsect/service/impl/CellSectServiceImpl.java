package kr.go.gg.gpms.cellsect.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.cellsect.service.CellSectService;
import kr.go.gg.gpms.cellsect.service.model.CellSectVO;

/**
 * CELL_SECT
 *
 * @Class Name : Cell10ServiceImpl.java
 * @Description : Cell10 Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-13
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("cellSectService")
public class CellSectServiceImpl extends AbstractServiceImpl implements CellSectService {

	@Resource(name = "cellSectDAO")
	private CellSectDAO cellSectDAO;

	//@Resource(name="Cell10IdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * CELL_SECT(CELL_SECT)을 등록한다.
	 * @param cellSectVO - 등록할 정보가 담긴 cellSectVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCellSect(CellSectVO cellSectVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//cellSectVO.setId(id);

		return cellSectDAO.insertCellSect(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 수정한다.
	 * @param cellSectVO - 수정할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCellSect(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.updateCellSect(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 삭제한다.
	 * @param cellSectVO - 삭제할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteCellSect(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.deleteCellSect(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT)을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	public CellSectVO selectCellSect(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.selectCellSect(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cellSectVO
	 * @return CELL_SECT 목록
	 * @exception Exception
	 */
	public List<CellSectVO> selectCellSectList(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.selectCellSectList(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 cellSectVO
	 * @return CELL_SECT 총 갯수
	 * @exception
	 */
	public int selectCellSectListTotalCount(CellSectVO cellSectVO) {
		return cellSectDAO.selectCellSectListTotalCount(cellSectVO);
	}

	/**
	 * CELL_SECT(CELL_SECT) 섹션구분을 수정한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateSectSe(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.updateSectSe( cellSectVO);
	}

	/**
	 * 총 노선 연장 및 면적을 조회한다.
	 * @param cellSectVO - 조회할 정보가 담긴 cellSectVO
	 * @return 조회한 CELL_SECT
	 * @exception Exception
	 */
	public CellSectVO selectCellSectLenArea(CellSectVO cellSectVO) throws Exception {
		return cellSectDAO.selectCellSectLenArea(cellSectVO);
	}

}
