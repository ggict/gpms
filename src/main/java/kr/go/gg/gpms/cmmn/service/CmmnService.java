package kr.go.gg.gpms.cmmn.service;

import java.util.HashMap;
import java.util.List;

import kr.go.gg.gpms.code.service.model.CodeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통 서비스
 * CmmnService
 *
 * @Class Name : CmmnService.java
 * @Description : Cmmn Business class
 * @Modification Information
 *
 * @author skc@muhanit.kr
 * @since 2017-04-19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CmmnService {

	/**
	 * 테이블 컬럼 목록을 조회한다.
	 * @param TABLE_NAME - 조회할 정보가 담긴 String
	 * @return 테이블 컬럼 정보
	 * @exception Exception
	 */
	List<EgovMap> selectCols(String TABLE_NAME) throws Exception;
	
	/**
	 * 노선정보을 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return ROUTE_CODE, ROUTE_NM
	 */
	List<?> selectRouteInfo(HashMap paraMap) throws Exception;

	/**
	 * 행선정보을 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return ROUTE_CODE, ROUTE_NM
	 */
	List<?> selectRln(HashMap paraMap) throws Exception;
	
	/**
	 * 행선정보을 조회한다.
	 * @param TN_ROUTE_INFO
	 * @return ROUTE_CODE, ROUTE_NM
	 */
	List<?> selectCtk(HashMap paraMap) throws Exception;
	
	/**
	 * 레이어명을 조회한다.
	 */
	String selectTableAlias(String table) throws Exception;
	
	/**
	 * 공간레이어 테이블 목록을 조회한다.
	 * @param 
	 * @return 테이블  정보
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	List<?> selectTableAliasList() throws Exception;
	
	/**
	 * 레이어컬럼을 조회한다.
	 */
	String selectFieldAlias(String table, String field) throws Exception;
		
	/**
	 * 지도 속성 조회시 공통코드명 항목을 조회한다.
	 */
	List<CodeVO> selectCodeName(CodeVO codeVO) throws Exception;
	
	/**
	 * 행정구역 코드명(N3A_G0100000)을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 codeVO
	 * @return N3A_G0100000 목록
	 * @exception Exception
	 */
	List<CodeVO> selectAdmCodeList(CodeVO codeVO) throws Exception;
	
	/**
	 * 레이어목록을 조회한다.
	 */
	List<EgovMap> selectLaygerList(EgovMap map) throws Exception;
	
	List<EgovMap> selectLayerListTheme(EgovMap map) throws Exception;
	
	/**
	 * 레이어그룹을 조회한다.
	 */
	List<EgovMap> selectLyrGroupInfo(EgovMap map) throws Exception;
	
	/**
	 * 셀 타입을 조회한다.
	 */
	List<?> selectCellType(HashMap paraMap) throws Exception;
	
	//차로 조회
    List<?> selectTrackFrom20(HashMap<String, Object> paramMap);
    
}

