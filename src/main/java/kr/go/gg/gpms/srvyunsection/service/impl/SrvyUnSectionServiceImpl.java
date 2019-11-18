package kr.go.gg.gpms.srvyunsection.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.srvyunsection.service.SrvyUnSectionService;

@Service("srvyUnSectionService")
public class SrvyUnSectionServiceImpl extends AbstractServiceImpl implements SrvyUnSectionService {

	@Resource(name = "srvyUnSectionDAO")
	private SrvyUnSectionDAO srvyUnSectionDAO;

	@Override
	public Object list(Object object) throws Exception {
		return srvyUnSectionDAO.list(object);
	}
	
	@Override
	public Object sectionlocation(Object object) throws Exception {
		return srvyUnSectionDAO.sectionlocation(object);
	}
	
	@Override
	public Object unsectionlocation(Object object) throws Exception {
		return srvyUnSectionDAO.unsectionlocation(object);
	}
	
	@Override
	public Object srvyYearList(Object object) throws Exception {
		return srvyUnSectionDAO.srvyYearList(object);
	}
	@Override
	public Object chartList(Object object) throws Exception{
		return srvyUnSectionDAO.chartList(object);
	}

}
