package kr.go.gg.gpms.rpairtrget.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairtrget.service.RpairTrgetService;
import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetVO;

/**
 * 보수_대상_항목
 *
 * @Class Name : RpairTrgetServiceImpl.java
 * @Description : RpairTrget Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("rpairTrgetService")
public class RpairTrgetServiceImpl extends AbstractServiceImpl implements RpairTrgetService {

	@Resource(name = "rpairTrgetDAO")
	private RpairTrgetDAO rpairTrgetDAO;

	//@Resource(name="RpairTrgetIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 등록한다.
	 * @param rpairTrgetVO - 등록할 정보가 담긴 RpairTrgetVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairTrgetVO.setId(id);

		return rpairTrgetDAO.insertRpairTrget( rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 수정한다.
	 * @param rpairTrgetVO - 수정할 정보가 담긴 RpairTrgetVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return rpairTrgetDAO.updateRpairTrget( rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 삭제한다.
	 * @param rpairTrgetVO - 삭제할 정보가 담긴 RpairTrgetVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return rpairTrgetDAO.deleteRpairTrget( rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return 조회한 TN_RPAIR_TRGET
	 * @exception Exception
	 */
	public RpairTrgetVO selectRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		RpairTrgetVO resultVO = rpairTrgetDAO.selectRpairTrget( rpairTrgetVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetVO
	 * @return TN_RPAIR_TRGET 목록
	 * @exception Exception
	 */
	public List<RpairTrgetVO> selectRpairTrgetList(RpairTrgetVO rpairTrgetVO) throws Exception {
		return rpairTrgetDAO.selectRpairTrgetList( rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetVO
	 * @return TN_RPAIR_TRGET 총 갯수
	 * @exception
	 */
	public int selectRpairTrgetListTotalCount(RpairTrgetVO rpairTrgetVO) {
		return rpairTrgetDAO.selectRpairTrgetListTotalCount( rpairTrgetVO);
	}

}
