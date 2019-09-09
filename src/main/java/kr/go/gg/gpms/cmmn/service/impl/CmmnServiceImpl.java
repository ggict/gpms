package kr.go.gg.gpms.cmmn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.model.CodeVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통
 *
 * @Class Name : CmmnServiceImpl.java
 * @Description : Cmmn Business Implement class
 * @Modification Information
 *
 * @author skc@muhanit.kr
 * @since 2017-04-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("cmmnService")
public class CmmnServiceImpl extends AbstractServiceImpl implements CmmnService {

	@Resource(name = "cmmnDAO")
	private CmmnDAO cmmnDAO;


	/**
	 * 테이블 컬럼 목록을 조회한다.
	 * @param TABLE_NAME - 조회할 정보가 담긴 String
	 * @return 테이블 컬럼 정보
	 * @exception Exception
	 */
	public List<EgovMap> selectCols(String TABLE_NAME) throws Exception {
		return cmmnDAO.selectCols(TABLE_NAME);
	}

	/**
	 * 노선정보을 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return 
	 */
	public List selectRouteInfo(HashMap paraMap) throws Exception {
		return cmmnDAO.getSelectList("cmmnDAO.selectRouteInfo", paraMap);
	}
	
	/**
	 * 행선정보를 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return 
	 */
	public List selectRln(HashMap paraMap) throws Exception {
		List code_list = cmmnDAO.getSelectList("cmmnDAO.selectRln", paraMap);
    	String val1 = (String) ((Map)code_list.get(0)).get("strtpt_nm");
    	String val2 = (String) ((Map)code_list.get(0)).get("endpt_nm");
    	
    	code_list = new ArrayList<HashMap>();
    	HashMap code = new HashMap();
    	
    	code.put("CODE", "S");
    	code.put("VAL", val1 + "(상행)");
    	code_list.add( code );

    	code = new HashMap();
    	code.put("CODE", "E");
    	code.put("VAL", val2 + "(하행)");
    	code_list.add( code );
    	
        return code_list;
	}
	
	/**
	 * 차로정보를 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return 
	 */
	public List selectCtk(HashMap paraMap) throws Exception {
    	List code_list = cmmnDAO.getSelectList("cmmnDAO.selectCtk", paraMap);
    	int charo = Integer.parseInt((String) ((Map)code_list.get(0)).get("track"));

    	HashMap code = new HashMap();    	
    	
    	for(int a=1; a<=charo; a++){
    		code = new HashMap();
        	code.put("CODE", a);
        	code.put("VAL", a);
        	code_list.add( code );
    	}
    	
        return code_list;
    }

	/**
	 * 레이어명을 조회한다.
	 */
	@Override
	public String selectTableAlias(String table) throws Exception {
		return cmmnDAO.selectTableAlias(table);
	}
	
	/**
	 * 공간레이어 테이블 목록을 조회한다.
	 * @param 
	 * @return 테이블  정보
	 * @exception Exception
	 */
	public List selectTableAliasList() throws Exception {
		return cmmnDAO.getSelectList("cmmnDAO.selectTableAliasList", null);
	}
	
	
	/**
	 * 레이어컬럼을 조회한다.
	 */
	@Override
	public String selectFieldAlias(String table, String field) throws Exception {
		return cmmnDAO.selectFieldAlias(table, field);
	}
	
	
	/**
	 * 지도 속성 조회시 공통코드명 항목을 조회한다.
	 */
	public List<CodeVO> selectCodeName(CodeVO codeVO) throws Exception {
		return cmmnDAO.selectCodeName( codeVO);
	}
	
	/**
	 * 행정구역 코드명(N3A_G0100000)을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return N3A_G0100000 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectAdmCodeList(CodeVO codeVO) throws Exception {
		return cmmnDAO.selectAdmCodeList(codeVO);
	}
	
	/**
	 * 레이어목록을 조회한다.
	 */
	@Override
	public List<EgovMap> selectLaygerList(EgovMap map) throws Exception {
		return cmmnDAO.selectLaygerList(map);
	}
	
	@Override
    public List<EgovMap> selectLayerListTheme(EgovMap map) throws Exception {
        return cmmnDAO.selectLayerListTheme(map);
    }
	
	/**
	 * 레이어그룹을 조회한다.
	 */
	@Override
	public List<EgovMap> selectLyrGroupInfo(EgovMap map) throws Exception {
		return cmmnDAO.selectLyrGroupInfo(map);
	}
	
	/**
	 * 셀 타입을 조회한다.
	 */
	public List selectCellType(HashMap paraMap) throws Exception {
		return cmmnDAO.getSelectList("cmmnDAO.selectCellType", paraMap);
	}
	
	//차로 조회
    @Override
    public List<?> selectTrackFrom20(HashMap<String, Object> paramMap) {
        return cmmnDAO.selectTrackFrom20("cmmnDAO.selectTrackFrom20", paramMap);
    }
}
