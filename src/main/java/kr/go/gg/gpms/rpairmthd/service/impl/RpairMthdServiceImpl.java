package kr.go.gg.gpms.rpairmthd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;

/**
 * 보수공법코드
 *
 * @Class Name : RpairMthdServiceImpl.java
 * @Description : RpairMthd Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("rpairMthdService")
public class RpairMthdServiceImpl extends AbstractServiceImpl implements RpairMthdService {

	@Resource(name = "rpairMthdDAO")
	private RpairMthdDAO rpairMthdDAO;

	//@Resource(name="RpairMthdIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 등록한다.
	 * @param rpairMthdVO - 등록할 정보가 담긴 RpairMthdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairMthdVO.setId(id);

		return rpairMthdDAO.insertRpairMthd( rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 수정한다.
	 * @param rpairMthdVO - 수정할 정보가 담긴 RpairMthdVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return rpairMthdDAO.updateRpairMthd( rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 삭제한다.
	 * @param rpairMthdVO - 삭제할 정보가 담긴 RpairMthdVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		return rpairMthdDAO.deleteRpairMthd( rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD)을 조회한다.
	 * @param rpairMthdVO - 조회할 정보가 담긴 RpairMthdVO
	 * @return 조회한 TC_RPAIR_MTHD
	 * @exception Exception
	 */
	public RpairMthdVO selectRpairMthd(RpairMthdVO rpairMthdVO) throws Exception {
		RpairMthdVO resultVO = rpairMthdDAO.selectRpairMthd( rpairMthdVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdVO
	 * @return TC_RPAIR_MTHD 목록
	 * @exception Exception
	 */
	public List<RpairMthdVO> selectRpairMthdList(RpairMthdVO rpairMthdVO) throws Exception {
		return rpairMthdDAO.selectRpairMthdList( rpairMthdVO);
	}

	/**
	 * 보수공법코드(TC_RPAIR_MTHD) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdVO
	 * @return TC_RPAIR_MTHD 총 갯수
	 * @exception
	 */
	public int selectRpairMthdListTotalCount(RpairMthdVO rpairMthdVO) {
		return rpairMthdDAO.selectRpairMthdListTotalCount( rpairMthdVO);
	}

}
