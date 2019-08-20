package kr.go.gg.gpms.pavidxeryyval.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavidxeryyval.service.PavIdxEryyvalService;
import kr.go.gg.gpms.pavidxeryyval.service.model.PavIdxEryyvalVO;

/**
 * 포장_지수_초기값
 *
 * @Class Name : PavIdxEryyvalServiceImpl.java
 * @Description : PavIdxEryyval Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pavIdxEryyvalService")
public class PavIdxEryyvalServiceImpl extends AbstractServiceImpl implements PavIdxEryyvalService {

	@Resource(name = "pavIdxEryyvalDAO")
	private PavIdxEryyvalDAO pavIdxEryyvalDAO;

	//@Resource(name="PavIdxEryyvalIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 등록한다.
	 * @param pavIdxEryyvalVO - 등록할 정보가 담긴 PavIdxEryyvalVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertPavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//pavIdxEryyvalVO.setId(id);

		return pavIdxEryyvalDAO.insertPavIdxEryyval( pavIdxEryyvalVO);
	}

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 수정한다.
	 * @param pavIdxEryyvalVO - 수정할 정보가 담긴 PavIdxEryyvalVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		return pavIdxEryyvalDAO.updatePavIdxEryyval( pavIdxEryyvalVO);
	}

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 삭제한다.
	 * @param pavIdxEryyvalVO - 삭제할 정보가 담긴 PavIdxEryyvalVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		return pavIdxEryyvalDAO.deletePavIdxEryyval( pavIdxEryyvalVO);
	}

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL)을 조회한다.
	 * @param pavIdxEryyvalVO - 조회할 정보가 담긴 PavIdxEryyvalVO
	 * @return 조회한 TN_PAV_IDX_INITVAL
	 * @exception Exception
	 */
	public PavIdxEryyvalVO selectPavIdxEryyval(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		PavIdxEryyvalVO resultVO = pavIdxEryyvalDAO.selectPavIdxEryyval( pavIdxEryyvalVO);
		 
		return resultVO;
	}

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavIdxEryyvalVO
	 * @return TN_PAV_IDX_INITVAL 목록
	 * @exception Exception
	 */
	public List<PavIdxEryyvalVO> selectPavIdxEryyvalList(PavIdxEryyvalVO pavIdxEryyvalVO) throws Exception {
		return pavIdxEryyvalDAO.selectPavIdxEryyvalList( pavIdxEryyvalVO);
	}

	/**
	 * 포장_지수_초기값(TN_PAV_IDX_INITVAL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavIdxEryyvalVO
	 * @return TN_PAV_IDX_INITVAL 총 갯수
	 * @exception
	 */
	public int selectPavIdxEryyvalListTotalCount(PavIdxEryyvalVO pavIdxEryyvalVO) {
		return pavIdxEryyvalDAO.selectPavIdxEryyvalListTotalCount( pavIdxEryyvalVO);
	}

}
