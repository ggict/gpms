package kr.go.gg.gpms.company.service;

import java.util.List;

import kr.go.gg.gpms.company.service.model.CompanyDefaultVO;
import kr.go.gg.gpms.company.service.model.CompanyVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 업체정보
 * TN_COMPANY
 *
 * @Class Name : CompanyService.java
 * @Description : Company Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface CompanyService {

	/**
	 * 업체정보(TN_COMPANY)을 등록한다.
	 * @param companyVO - 등록할 정보가 담긴 CompanyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 업체정보(TN_COMPANY)을 수정한다.
	 * @param companyVO - 수정할 정보가 담긴 CompanyVO
	 * @return int형
	 * @exception Exception
	 */
	int updateCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 업체정보(TN_COMPANY)을 삭제한다.
	 * @param companyVO - 삭제할 정보가 담긴 CompanyVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 업체정보(TN_COMPANY)을 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return 조회한 TN_COMPANY
	 * @exception Exception
	 */
	CompanyVO selectCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 companyVO
	 * @return TN_COMPANY 목록
	 * @exception Exception
	 */
	List<CompanyVO> selectCompanyList(CompanyVO companyVO) throws Exception;

	/**
	 * 업체정보(TN_COMPANY) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 companyVO
	 * @return TN_COMPANY 총 갯수
	 * @exception
	 */
	int selectCompanyListTotalCount(CompanyVO companyVO);

}

