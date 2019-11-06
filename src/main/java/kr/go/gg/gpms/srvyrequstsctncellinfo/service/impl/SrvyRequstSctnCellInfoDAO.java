


package kr.go.gg.gpms.srvyrequstsctncellinfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.srvyrequstsctncellinfo.service.model.SrvyRequstSctnCellInfoVO;

/**
 * 조사_요청_구간_셀_정보
 *
 * @Class Name : SrvyRequstSctnCellInfoDAO.java
 * @Description : SrvyRequstSctnCellInfo DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2019-10-30
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("srvyRequstSctnCellInfoDAO")
public class SrvyRequstSctnCellInfoDAO extends BaseDAO {

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 등록한다.
	 * @param srvyRequstSctnCellInfoVO - 등록할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return (String) insert("srvyRequstSctnCellInfoDAO.insertSrvyRequstSctnCellInfo", srvyRequstSctnCellInfoVO);
	}

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 수정한다.
	 * @param srvyRequstSctnCellInfoVO - 수정할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return update("srvyRequstSctnCellInfoDAO.updateSrvyRequstSctnCellInfo", srvyRequstSctnCellInfoVO);
	}

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 삭제한다.
	 * @param srvyRequstSctnCellInfoVO - 삭제할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return delete("srvyRequstSctnCellInfoDAO.deleteSrvyRequstSctnCellInfo", srvyRequstSctnCellInfoVO);
	}

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO)을 조회한다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return 조회한 TN_SRVY_REQUST_SCTN_CELL_INFO
	 * @exception Exception
	 */
	public SrvyRequstSctnCellInfoVO selectSrvyRequstSctnCellInfo(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return (SrvyRequstSctnCellInfoVO) select("srvyRequstSctnCellInfoDAO.selectSrvyRequstSctnCellInfo", srvyRequstSctnCellInfoVO);
	}

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 목록을 조회한다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return TN_SRVY_REQUST_SCTN_CELL_INFO 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SrvyRequstSctnCellInfoVO> selectSrvyRequstSctnCellInfoList(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return (List<SrvyRequstSctnCellInfoVO>)list("srvyRequstSctnCellInfoDAO.selectSrvyRequstSctnCellInfoList", srvyRequstSctnCellInfoVO);
	}

	/**
	 * 조사_요청_구간_셀_정보(TN_SRVY_REQUST_SCTN_CELL_INFO) 총 갯수를 조회한다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return TN_SRVY_REQUST_SCTN_CELL_INFO 총 갯수
	 * @exception
	 */
	public int selectSrvyRequstSctnCellInfoListTotalCount(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) {
		return (Integer) select("srvyRequstSctnCellInfoDAO.selectSrvyRequstSctnCellInfoListTotalCount", srvyRequstSctnCellInfoVO);
	}
	
	/**
	 * CELL_10(CELL_10) id에 따른 노선정보 리스트를 가져온다.
	 * @param srvyRequstSctnCellInfoVO - 조회할 정보가 담긴 SrvyRequstSctnCellInfoVO
	 * @return CELL_10 노선정보 리스트
	 * @exception
	 */
	public List<Cell10VO> selectRouteInfoListByCellID(SrvyRequstSctnCellInfoVO srvyRequstSctnCellInfoVO) throws Exception {
		return (List<Cell10VO>)list("srvyRequstSctnCellInfoDAO.selectRouteInfoListByCellID", srvyRequstSctnCellInfoVO);
	}

}
