package kr.go.gg.gpms.srvyunsection.service;

import java.util.List;

import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusDefaultVO;
import kr.go.gg.gpms.srvydtasttus.service.model.SrvyDtaSttusVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface SrvyUnSectionService {

	public Object list(Object object) throws Exception;

	public Object sectionlocation(Object object) throws Exception;
	
	public Object unsectionlocation(Object object) throws Exception;
	
	public Object srvyYearList(Object object) throws Exception;
	
}

