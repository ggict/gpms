package kr.go.gg.gpms.strdwg.service;

import java.util.List;

import kr.go.gg.gpms.strdwg.service.model.StrDwgDefaultVO;
import kr.go.gg.gpms.strdwg.service.model.StrDwgVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 구조물_도면_정보
 * STR_DWG
 *
 * @Class Name : StrDwgService.java
 * @Description : StrDwg Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface StrDwgService {

	/**
	 * 구조물_도면_정보(STR_DWG)을 등록한다.
	 * @param strDwgVO - 등록할 정보가 담긴 StrDwgVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertStrDwg(StrDwgVO strDwgVO) throws Exception;

	/**
	 * 구조물_도면_정보(STR_DWG)을 수정한다.
	 * @param strDwgVO - 수정할 정보가 담긴 StrDwgVO
	 * @return int형
	 * @exception Exception
	 */
	int updateStrDwg(StrDwgVO strDwgVO) throws Exception;

	/**
	 * 구조물_도면_정보(STR_DWG)을 삭제한다.
	 * @param strDwgVO - 삭제할 정보가 담긴 StrDwgVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteStrDwg(StrDwgVO strDwgVO) throws Exception;

	/**
	 * 구조물_도면_정보(STR_DWG)을 조회한다.
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return 조회한 STR_DWG
	 * @exception Exception
	 */
	StrDwgVO selectStrDwg(StrDwgVO strDwgVO) throws Exception;

	/**
	 * 구조물_도면_정보(STR_DWG) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 strDwgVO
	 * @return STR_DWG 목록
	 * @exception Exception
	 */
	List<StrDwgVO> selectStrDwgList(StrDwgVO strDwgVO) throws Exception;

	/**
	 * 구조물_도면_정보(STR_DWG) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 strDwgVO
	 * @return STR_DWG 총 갯수
	 * @exception
	 */
	int selectStrDwgListTotalCount(StrDwgVO strDwgVO);
	
	/**
	 * 구조물_도면_정보(STR_DWG) 구간 목록을 조회한다.
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return STR_DWG 목록
	 * @exception Exception
	 */
	List<StrDwgVO> selectStrDwgSectList(StrDwgVO strDwgVO) throws Exception;

}

