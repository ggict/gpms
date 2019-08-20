


package kr.go.gg.gpms.dwginfo.service.impl;

import java.util.List;

import kr.go.gg.gpms.dwginfo.service.model.DwgInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

/**
 * DWG_INFO
 *
 * @Class Name : DwgInfoDAO.java
 * @Description : DwgInfo DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("dwgInfoDAO")
public class DwgInfoDAO extends BaseDAO {

	/**
	 * DWG_INFO(DWG_INFO)을 등록한다.
	 * @param dwgInfoVO - 등록할 정보가 담긴 DwgInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return (String) insert("dwgInfoDAO.insertDwgInfo", dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 수정한다.
	 * @param dwgInfoVO - 수정할 정보가 담긴 DwgInfoVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return update("dwgInfoDAO.updateDwgInfo", dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 삭제한다.
	 * @param dwgInfoVO - 삭제할 정보가 담긴 DwgInfoVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return delete("dwgInfoDAO.deleteDwgInfo", dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO)을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return 조회한 DWG_INFO
	 * @exception Exception
	 */
	public DwgInfoVO selectDwgInfo(DwgInfoVO dwgInfoVO) throws Exception {
		return (DwgInfoVO) select("dwgInfoDAO.selectDwgInfo", dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO) 목록을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DwgInfoVO> selectDwgInfoList(DwgInfoVO dwgInfoVO) throws Exception {
		return (List<DwgInfoVO>)list("dwgInfoDAO.selectDwgInfoList", dwgInfoVO);
	}

	/**
	 * DWG_INFO(DWG_INFO) 총 갯수를 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return DWG_INFO 총 갯수
	 * @exception
	 */
	public int selectDwgInfoListTotalCount(DwgInfoVO dwgInfoVO) {
		return (Integer) select("dwgInfoDAO.selectDwgInfoListTotalCount", dwgInfoVO);
	}
	
	/**
	 * DWG_INFO(DWG_INFO) 구간 목록을 조회한다.
	 * @param dwgInfoVO - 조회할 정보가 담긴 DwgInfoVO
	 * @return DWG_INFO 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DwgInfoVO> selectDwgInfoSectList(DwgInfoVO dwgInfoVO) throws Exception {
		return (List<DwgInfoVO>)list("dwgInfoDAO.selectDwgInfoSectList", dwgInfoVO);
	}

}
