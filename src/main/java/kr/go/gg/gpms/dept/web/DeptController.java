

package kr.go.gg.gpms.dept.web;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.security.core.context.SecurityContextHolder;



/**
 * @Class Name : DeptController.java
 * @Description : Dept Controller class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-05-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller("deptController")
public class DeptController extends BaseController{

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;
	
	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다. (pageing)
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return "/manage/dept/DeptList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/mng/dept/selectDeptList.do" })
	public String selectDeptList(DeptVO deptVO, ModelMap model) throws Exception {
		setSystem("mng");
		//부서 정보
		deptVO.setODR("2");
		List<DeptVO> deptCdList = deptService.selectDeptCodeList(deptVO);
		
		model.addAttribute("deptCdList", deptCdList);
	
		return "/mng/dept/deptList" ;
	}
	
	
	/**
	 * 부서_코드(TC_DEPT) 목록을 조회한다. (pageing)
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @return "/manage/dept/DeptList"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/dept/selectDeptList.do" }, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> selectDeptListRest(@RequestBody DeptVO deptVO, ModelMap model, HttpSession session) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptVO.getPage());
		paginationInfo.setRecordCountPerPage(deptVO.getPageUnit());
		paginationInfo.setPageSize(deptVO.getRows());
		
		deptVO.setDELETE_AT("N");
		deptVO.setUsePage(true);

		deptVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<DeptVO> items = deptService.selectDeptList(deptVO);
		int totCnt = deptService.selectDeptListTotalCount(deptVO);

		int total_page = 0;
		if (totCnt > 0)
			total_page = (int) Math.ceil((float) totCnt / (float) paginationInfo.getPageSize());

		// 결과 JSON 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", deptVO.getPage());
		map.put("total", total_page);
		map.put("records", totCnt);
		map.put("rows", items);
		
		return map;
	}
	
	

	/**
	 * 부서_코드(TC_DEPT) 를 조회한다.
	 * @param deptVO - 조회할 정보가 담긴 DeptVO
	 * @exception Exception
	 */
	@RequestMapping(value = { "/api/dept/selectDeptCdList.do"  }, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<DeptVO> selectDeptCdList(@ModelAttribute("searchVO") DeptVO deptVO, ModelMap model) throws Exception {
	
		List<DeptVO> result = deptService.selectDeptCodeList(deptVO);
		return result;
	}
	

}
