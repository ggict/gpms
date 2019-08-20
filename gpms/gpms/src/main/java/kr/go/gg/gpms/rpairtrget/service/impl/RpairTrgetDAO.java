


package kr.go.gg.gpms.rpairtrget.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.rpairtrget.service.model.RpairTrgetVO;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;

/**
 * 보수_대상_항목
 *
 * @Class Name : RpairTrgetDAO.java
 * @Description : RpairTrget DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("rpairTrgetDAO")
public class RpairTrgetDAO extends BaseDAO {

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 등록한다.
	 * @param rpairTrgetVO - 등록할 정보가 담긴 RpairTrgetVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return (String) insert("rpairTrgetDAO.insertRpairTrget", rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 수정한다.
	 * @param rpairTrgetVO - 수정할 정보가 담긴 RpairTrgetVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return update("rpairTrgetDAO.updateRpairTrget", rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 삭제한다.
	 * @param rpairTrgetVO - 삭제할 정보가 담긴 RpairTrgetVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return delete("rpairTrgetDAO.deleteRpairTrget", rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET)을 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return 조회한 TN_RPAIR_TRGET
	 * @exception Exception
	 */
	public RpairTrgetVO selectRpairTrget(RpairTrgetVO rpairTrgetVO) throws Exception {
		return (RpairTrgetVO) select("rpairTrgetDAO.selectRpairTrget", rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 목록을 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return TN_RPAIR_TRGET 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RpairTrgetVO> selectRpairTrgetList(RpairTrgetVO rpairTrgetVO) throws Exception {
		return (List<RpairTrgetVO>)list("rpairTrgetDAO.selectRpairTrgetList", rpairTrgetVO);
	}

	/**
	 * 보수_대상_항목(TN_RPAIR_TRGET) 총 갯수를 조회한다.
	 * @param rpairTrgetVO - 조회할 정보가 담긴 RpairTrgetVO
	 * @return TN_RPAIR_TRGET 총 갯수
	 * @exception
	 */
	public int selectRpairTrgetListTotalCount(RpairTrgetVO rpairTrgetVO) {
		return (Integer) select("rpairTrgetDAO.selectRpairTrgetListTotalCount", rpairTrgetVO);
	}
	
	
	
 
	 

}
