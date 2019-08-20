


package kr.go.gg.gpms.cmmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.gg.gpms.code.service.model.CodeVO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통
 *
 * @Class Name : CmmnDao.java
 * @Description : Cmmn Dao class
 * @Modification Information
 *
 * @author skc@muhanit.kr
 * @since 2017-04-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("cmmnDAO")
public class CmmnDAO extends BaseDAO {
	private final Logger log = Logger.getLogger(this.getClass()); //log4j
	
	// 목록 조회용
		public List<?> getSelectList(String sqlMapId, Object parameter){
			List<?> list;

			try {
				long startTime = System.currentTimeMillis();

				list = list(sqlMapId, parameter);

				long endTime = System.currentTimeMillis();
				if (log.isDebugEnabled()) {
					log.debug("["+sqlMapId+"]query execute TIME : " + (endTime - startTime) + "(ms)]]");
				}
			}
			catch(Exception e) {
				if (log.isDebugEnabled()) {
					log.debug(e.getMessage());
				}
				throw new RuntimeException (e);
			}

			return list;
		}
	/**
	 * 테이블 컬럼 목록을 조회한다.
	 * @param TABLE_NAME - 조회할 정보가 담긴 String
	 * @return 테이블 컬럼 정보
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectCols(String TABLE_NAME) throws Exception {
		return (List<EgovMap>)list("cmmnDAO.selectCols", TABLE_NAME);
	}
	
	
	
	/**
	 * 레이어명을 조회한다.
	 */
	public String selectTableAlias(String table) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("cmmnDAO.selectTableAlias", table);
	}
	
	/**
	 * 레이어컬럼을 조회한다.
	 */
	public String selectFieldAlias(String table, String field) throws Exception {
		Map<String, String> inputMap = new HashMap<String, String>();
		
		inputMap.put("table", table);
		inputMap.put("field", field);
		
		return (String)getSqlMapClientTemplate().queryForObject("cmmnDAO.selectFieldAlias", inputMap);
	}
	
	/**
	 * 지도 속성 조회시 공통코드명 항목을 조회한다.
	 */
	@SuppressWarnings("unchecked")
	public List<CodeVO> selectCodeName(CodeVO codeVO) {
		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("COLUM_VAL", codeVO.getCOLUM_VAL());
		inputMap.put("CODE_VAL", codeVO.getCODE_VAL());
		inputMap.put("CODE_NM", codeVO.getCODE_NM());
		inputMap.put("TABLE_NM", codeVO.getTABLE_NM()); 
		
		return (List<CodeVO>) list("cmmnDAO.selectCodeName", inputMap);
	}
	
	/**
	 * 행정구역 코드명(N3A_G0100000)을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return N3A_G0100000 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectAdmCodeList(CodeVO codeVO) {
		return (List<CodeVO>)list("cmmnDAO.selectAdmCodeList", codeVO);
	}
	
	/**
	 * 레이어목록을 조회한다.
	 */
	public List<EgovMap> selectLaygerList(EgovMap map) throws Exception {
		return (List<EgovMap>)list("cmmnDAO.selectLaygerList", map);
	}
	
	public List<EgovMap> selectLayerListTheme(EgovMap map) throws Exception {
        return (List<EgovMap>)list("cmmnDAO.selectLayerListTheme", map);
    }
	
	/**
	 * 레이어그룹을 조회한다.
	 */
	public List<EgovMap> selectLyrGroupInfo(EgovMap map) throws Exception {
		return (List<EgovMap>)list("cmmnDAO.selectLyrGroupInfo", map);
	}
	
	//차로 조회
    public List<?> selectTrackFrom20(String string, HashMap<String, Object> paramMap) {
        return (List<EgovMap>) list("cmmnDAO.selectLane", paramMap);
    }
	
}