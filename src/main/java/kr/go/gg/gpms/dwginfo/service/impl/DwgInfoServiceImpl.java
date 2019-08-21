package kr.go.gg.gpms.dwginfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.dwginfo.service.DwgInfoService;
import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

/**
 * DWG_INFO
 *
 * @Class Name : DwgInfoServiceImpl.java
 * @Description : DwgInfo Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("dwgInfoService")
public class DwgInfoServiceImpl extends AbstractServiceImpl implements DwgInfoService {

	@Resource(name = "dwgInfoDAO")
	private DwgInfoDAO dwgInfoDAO;

	//@Resource(name="DwgInfoIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * DWG_INFO(DWG_INFO)을 등록한다.
	 * @param dwgInfoVO - 등록할 정보가 담긴 DwgInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//dwgInfoVO.setId(id);

		return dwgInfoDAO.insertDwgInfo( dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 수정한다.
	 * @param dwgInfoVO - 수정할 정보가 담긴 DwgInfoVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfoDAO.updateDwgInfo( dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 삭제한다.
	 * @param dwgInfoVO - 삭제할 정보가 담긴 DwgInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfoDAO.deleteDwgInfo( dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
	public DwgInfoVO selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfoDAO.selectDwgInfo( dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 dwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	public List<DwgInfoVO> selectDwgInfoList(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfoDAO.selectDwgInfoList( dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 dwgInfoVO
	 * @return DWG_INFO 총 갯수
	 * @exception
	 */
	public int selectDwgInfoListTotalCount(DwgInfoVO dwgInfoVO) {
		return dwgInfoDAO.selectDwgInfoListTotalCount( dwgInfoVO);
	}
	
	/**
	 * DWG_INFO(DWG_INFO) 구간 목록을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DwgInfoVO> selectDwgInfoSectList(DwgInfoVO dwgInfoVO) throws Exception {
		return dwgInfoDAO.selectDwgInfoSectList(dwgInfoVO);
	}

}
