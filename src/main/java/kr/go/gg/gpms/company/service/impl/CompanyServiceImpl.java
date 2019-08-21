package kr.go.gg.gpms.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.company.service.CompanyService;
import kr.go.gg.gpms.company.service.model.CompanyVO;

/**
 * 업체정보
 *
 * @Class Name : CompanyServiceImpl.java
 * @Description : Company Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("companyService")
public class CompanyServiceImpl extends AbstractServiceImpl implements CompanyService {

	@Resource(name = "companyDAO")
	private CompanyDAO companyDAO;

	//@Resource(name="CompanyIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 업체정보(TN_COMPANY)을 등록한다.
	 * @param companyVO - 등록할 정보가 담긴 CompanyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCompany(CompanyVO companyVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//companyVO.setId(id);

		return companyDAO.insertCompany( companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 수정한다.
	 * @param companyVO - 수정할 정보가 담긴 CompanyVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateCompany(CompanyVO companyVO) throws Exception {
		return companyDAO.updateCompany( companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 삭제한다.
	 * @param companyVO - 삭제할 정보가 담긴 CompanyVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteCompany(CompanyVO companyVO) throws Exception {
		return companyDAO.deleteCompany( companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return 조회한 TN_COMPANY
	 * @exception Exception
	 */
	public CompanyVO selectCompany(CompanyVO companyVO) throws Exception {
		CompanyVO resultVO = companyDAO.selectCompany( companyVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 companyVO
	 * @return TN_COMPANY 목록
	 * @exception Exception
	 */
	public List<CompanyVO> selectCompanyList(CompanyVO companyVO) throws Exception {
		return companyDAO.selectCompanyList( companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 companyVO
	 * @return TN_COMPANY 총 갯수
	 * @exception
	 */
	public int selectCompanyListTotalCount(CompanyVO companyVO) {
		return companyDAO.selectCompanyListTotalCount( companyVO);
	}

}
