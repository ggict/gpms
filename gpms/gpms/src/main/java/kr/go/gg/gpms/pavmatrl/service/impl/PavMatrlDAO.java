


package kr.go.gg.gpms.pavmatrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.pavmatrl.service.model.PavMatrlVO;

/**
 * 포장재료코드
 *
 * @Class Name : PavMatrlDAO.java
 * @Description : PavMatrl DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-06-08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("pavMatrlDAO")
public class PavMatrlDAO extends BaseDAO {

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 등록한다.
	 * @param pavMatrlVO - 등록할 정보가 담긴 PavMatrlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return (String) insert("pavMatrlDAO.insertPavMatrl", pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 수정한다.
	 * @param pavMatrlVO - 수정할 정보가 담긴 PavMatrlVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updatePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return update("pavMatrlDAO.updatePavMatrl", pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 삭제한다.
	 * @param pavMatrlVO - 삭제할 정보가 담긴 PavMatrlVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deletePavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return delete("pavMatrlDAO.deletePavMatrl", pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL)을 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return 조회한 TC_PAV_MATRL
	 * @exception Exception
	 */
	public PavMatrlVO selectPavMatrl(PavMatrlVO pavMatrlVO) throws Exception {
		return (PavMatrlVO) select("pavMatrlDAO.selectPavMatrl", pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL) 목록을 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return TC_PAV_MATRL 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PavMatrlVO> selectPavMatrlList(PavMatrlVO pavMatrlVO) throws Exception {
		return (List<PavMatrlVO>)list("pavMatrlDAO.selectPavMatrlList", pavMatrlVO);
	}

	/**
	 * 포장재료코드(TC_PAV_MATRL) 총 갯수를 조회한다.
	 * @param pavMatrlVO - 조회할 정보가 담긴 PavMatrlVO
	 * @return TC_PAV_MATRL 총 갯수
	 * @exception
	 */
	public int selectPavMatrlListTotalCount(PavMatrlVO pavMatrlVO) {
		return (Integer) select("pavMatrlDAO.selectPavMatrlListTotalCount", pavMatrlVO);
	}

}
