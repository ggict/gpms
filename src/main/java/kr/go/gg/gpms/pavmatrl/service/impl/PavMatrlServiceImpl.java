package kr.go.gg.gpms.pavmatrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavmatrl.service.PavMatrlService;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;

/**
 * 포장재료코드
 *
 * @Class Name : PavMatrlServiceImpl.java
 * @Description : PavMatrl Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pavMatrlService")
public class PavMatrlServiceImpl extends AbstractServiceImpl implements PavMatrlService {

	@Resource(name = "pavMatrlDAO")
	private PavMatrlDAO pavMatrlDAO;

	//@Resource(name="PavMatrlIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 등록한다.
	 * @param pavMatrlVO - 등록할 정보가 담긴 PavMatrlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//pavMatrlVO.setId(id);

		return pavMatrlDAO.insertPavMatrl( pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 수정한다.
	 * @param pavMatrlVO - 수정할 정보가 담긴 PavMatrlVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return pavMatrlDAO.updatePavMatrl( pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 삭제한다.
	 * @param pavMatrlVO - 삭제할 정보가 담긴 PavMatrlVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return pavMatrlDAO.deletePavMatrl( pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return 조회한 TC_PAV_MATRL
	 * @exception Exception
	 */
	public PavMatrlVO selectPavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		PavMatrlVO resultVO = pavMatrlDAO.selectPavMatrl( pavMatrlVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavMatrlVO
	 * @return TC_PAV_MATRL 목록
	 * @exception Exception
	 */
	public List<PavMatrlVO> selectPavMatrlList(PavMatrlVO pavMatrlVO) throws Exception {
		return pavMatrlDAO.selectPavMatrlList( pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavMatrlVO
	 * @return TC_PAV_MATRL 총 갯수
	 * @exception
	 */
	public int selectPavMatrlListTotalCount(PavMatrlVO pavMatrlVO) {
		return pavMatrlDAO.selectPavMatrlListTotalCount( pavMatrlVO);
	}
	
	/**
	 * 포장재료코드(TC_PAV_MATRL)를 포장재료명으로 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return 조회한 TC_PAV_MATRL
	 * @exception Exception
	 */
	public PavMatrlVO selectPavMatrlCode(PavMatrlVO pavMatrlVO) throws Exception {
		PavMatrlVO resultVO = pavMatrlDAO.selectPavMatrlCode( pavMatrlVO);
		return resultVO;
	}
}
