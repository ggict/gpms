package kr.go.gg.gpms.srvyunsection.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;

@Repository("srvyUnSectionDAO")
public class SrvyUnSectionDAO extends BaseDAO {
	
	@SuppressWarnings("unchecked")
	public Object list(Object object) throws Exception {	
		return list("srvyUnSectionDAO.list", object);
	}
	
	@SuppressWarnings("unchecked")
	public Object sectionlocation(Object object) throws Exception {	
		return select("srvyUnSectionDAO.sectionlocation", object);
	}
	
	@SuppressWarnings("unchecked")
	public Object unsectionlocation(Object object) throws Exception {	
		return select("srvyUnSectionDAO.unsectionlocation", object);
	}

	@SuppressWarnings("unchecked")
	public Object srvyYearList(Object object) throws Exception {	
		return list("srvyUnSectionDAO.srvyYearList", object);
	}
	
}
