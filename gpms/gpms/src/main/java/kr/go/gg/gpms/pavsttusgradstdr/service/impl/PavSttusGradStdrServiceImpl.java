package kr.go.gg.gpms.pavsttusgradstdr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavsttusgradstdr.service.PavSttusGradStdrService;
import kr.go.gg.gpms.pavsttusgradstdr.service.model.PavSttusGradStdrVO;

/**
 * 포장상태등급기준
 *
 * @Class Name : PavSttusGradStdrServiceImpl.java
 * @Description : PavSttusGradStdr Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("pavSttusGradStdrService")
public class PavSttusGradStdrServiceImpl extends AbstractServiceImpl implements PavSttusGradStdrService {

	@Resource(name = "pavSttusGradStdrDAO")
	private PavSttusGradStdrDAO pavSttusGradStdrDAO;

	//@Resource(name="PavSttusGradStdrIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 등록한다.
	 * @param pavSttusGradStdrVO - 등록할 정보가 담긴 PavSttusGradStdrVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//pavSttusGradStdrVO.setId(id);

		return pavSttusGradStdrDAO.insertPavSttusGradStdr( pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 수정한다.
	 * @param pavSttusGradStdrVO - 수정할 정보가 담긴 PavSttusGradStdrVO
	 * @return int형
	 * @exception Exception
	 */
	public int updatePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return pavSttusGradStdrDAO.updatePavSttusGradStdr( pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 삭제한다.
	 * @param pavSttusGradStdrVO - 삭제할 정보가 담긴 PavSttusGradStdrVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deletePavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return pavSttusGradStdrDAO.deletePavSttusGradStdr( pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR)을 조회한다.
	 * @param pavSttusGradStdrVO - 조회할 정보가 담긴 PavSttusGradStdrVO
	 * @return 조회한 TN_PAV_STTUS_GRAD_STDR
	 * @exception Exception
	 */
	public PavSttusGradStdrVO selectPavSttusGradStdr(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		PavSttusGradStdrVO resultVO = pavSttusGradStdrDAO.selectPavSttusGradStdr( pavSttusGradStdrVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 목록
	 * @exception Exception
	 */
	public List<PavSttusGradStdrVO> selectPavSttusGradStdrList(PavSttusGradStdrVO pavSttusGradStdrVO) throws Exception {
		return pavSttusGradStdrDAO.selectPavSttusGradStdrList( pavSttusGradStdrVO);
	}

	/**
	 * 포장상태등급기준(TN_PAV_STTUS_GRAD_STDR) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 pavSttusGradStdrVO
	 * @return TN_PAV_STTUS_GRAD_STDR 총 갯수
	 * @exception
	 */
	public int selectPavSttusGradStdrListTotalCount(PavSttusGradStdrVO pavSttusGradStdrVO) {
		return pavSttusGradStdrDAO.selectPavSttusGradStdrListTotalCount( pavSttusGradStdrVO);
	}

}
