


package kr.go.gg.gpms.company.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.company.service.model.CompanyVO;

/**
 * 업체정보
 *
 * @Class Name : CompanyDAO.java
 * @Description : Company DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("companyDAO")
public class CompanyDAO extends BaseDAO {

	/**
	 * 업체정보(TN_COMPANY)을 등록한다.
	 * @param companyVO - 등록할 정보가 담긴 CompanyVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertCompany(CompanyVO companyVO) throws Exception {
		return (String) insert("companyDAO.insertCompany", companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 수정한다.
	 * @param companyVO - 수정할 정보가 담긴 CompanyVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateCompany(CompanyVO companyVO) throws Exception {
		return update("companyDAO.updateCompany", companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 삭제한다.
	 * @param companyVO - 삭제할 정보가 담긴 CompanyVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteCompany(CompanyVO companyVO) throws Exception {
		return delete("companyDAO.deleteCompany", companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY)을 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return 조회한 TN_COMPANY
	 * @exception Exception
	 */
	public CompanyVO selectCompany(CompanyVO companyVO) throws Exception {
		return (CompanyVO) select("companyDAO.selectCompany", companyVO);
	}
	
	/**
	 * 업체정보(TN_COMPANY)을 조회한다.
	 * @param value - 조회할 정보
	 * @return 조회한 TN_COMPANY
	 * @exception Exception
	 */
	public CompanyVO selectCompanyExcel(CompanyVO companyVO) throws Exception {
		return (CompanyVO) select("companyDAO.selectCompanyExcel", companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY) 목록을 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return TN_COMPANY 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyVO> selectCompanyList(CompanyVO companyVO) throws Exception {
		return (List<CompanyVO>)list("companyDAO.selectCompanyList", companyVO);
	}

	/**
	 * 업체정보(TN_COMPANY) 총 갯수를 조회한다.
	 * @param companyVO - 조회할 정보가 담긴 CompanyVO
	 * @return TN_COMPANY 총 갯수
	 * @exception
	 */
	public int selectCompanyListTotalCount(CompanyVO companyVO) {
		return (Integer) select("companyDAO.selectCompanyListTotalCount", companyVO);
	}

}
