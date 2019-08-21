package kr.go.gg.gpms.strdwg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.strdwg.service.StrDwgService;
import kr.go.gg.gpms.strdwg.service.model.StrDwgVO;

/**
 * 구조물_도면_정보
 *
 * @Class Name : StrDwgServiceImpl.java
 * @Description : StrDwg Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-07-12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("strDwgService")
public class StrDwgServiceImpl extends AbstractServiceImpl implements StrDwgService {

	@Resource(name = "strDwgDAO")
	private StrDwgDAO strDwgDAO;

	//@Resource(name="StrDwgIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 구조물_도면_정보(STR_DWG)을 등록한다.
	 * @param strDwgVO - 등록할 정보가 담긴 StrDwgVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertStrDwg(StrDwgVO strDwgVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//strDwgVO.setId(id);

		return strDwgDAO.insertStrDwg( strDwgVO);
	}

	/**
	 * 구조물_도면_정보(STR_DWG)을 수정한다.
	 * @param strDwgVO - 수정할 정보가 담긴 StrDwgVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateStrDwg(StrDwgVO strDwgVO) throws Exception {
		return strDwgDAO.updateStrDwg( strDwgVO);
	}

	/**
	 * 구조물_도면_정보(STR_DWG)을 삭제한다.
	 * @param strDwgVO - 삭제할 정보가 담긴 StrDwgVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteStrDwg(StrDwgVO strDwgVO) throws Exception {
		return strDwgDAO.deleteStrDwg( strDwgVO);
	}

	/**
	 * 구조물_도면_정보(STR_DWG)을 조회한다.
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return 조회한 STR_DWG
	 * @exception Exception
	 */
	public StrDwgVO selectStrDwg(StrDwgVO strDwgVO) throws Exception {
		return strDwgDAO.selectStrDwg( strDwgVO);
	}

	/**
	 * 구조물_도면_정보(STR_DWG) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 strDwgVO
	 * @return STR_DWG 목록
	 * @exception Exception
	 */
	public List<StrDwgVO> selectStrDwgList(StrDwgVO strDwgVO) throws Exception {
		return strDwgDAO.selectStrDwgList( strDwgVO);
	}

	/**
	 * 구조물_도면_정보(STR_DWG) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 strDwgVO
	 * @return STR_DWG 총 갯수
	 * @exception
	 */
	public int selectStrDwgListTotalCount(StrDwgVO strDwgVO) {
		return strDwgDAO.selectStrDwgListTotalCount( strDwgVO);
	}
	
	/**
	 * 구조물_도면_정보(STR_DWG) 구간 목록을 조회한다.
	 * @param strDwgVO - 조회할 정보가 담긴 StrDwgVO
	 * @return STR_DWG 목록
	 * @exception Exception
	 */
	public List<StrDwgVO> selectStrDwgSectList(StrDwgVO strDwgVO) throws Exception {
		return strDwgDAO.selectStrDwgSectList(strDwgVO);
	}

}
