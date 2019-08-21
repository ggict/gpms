package kr.go.gg.gpms.routeinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;

/**
 * 노선_정보
 *
 * @Class Name : RouteInfoServiceImpl.java
 * @Description : RouteInfo Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("routeInfoService")
public class RouteInfoServiceImpl extends AbstractServiceImpl implements RouteInfoService {

	@Resource(name = "routeInfoDAO")
	private RouteInfoDAO routeInfoDAO;

	//@Resource(name="RouteInfoIdGnrService")	
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 등록한다.
	 * @param routeInfoVO - 등록할 정보가 담긴 RouteInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//routeInfoVO.setId(id);

		return routeInfoDAO.insertRouteInfo( routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 수정한다.
	 * @param routeInfoVO - 수정할 정보가 담긴 RouteInfoVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return routeInfoDAO.updateRouteInfo( routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 삭제한다.
	 * @param routeInfoVO - 삭제할 정보가 담긴 RouteInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	public int deleteRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return routeInfoDAO.deleteRouteInfo( routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	public RouteInfoVO selectRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		RouteInfoVO resultVO = routeInfoDAO.selectRouteInfo( routeInfoVO);
		 
		return resultVO;
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 routeInfoVO
	 * @return TN_ROUTE_INFO 목록
	 * @exception Exception
	 */
	public List<RouteInfoVO> selectRouteInfoList(RouteInfoVO routeInfoVO) throws Exception {
		return routeInfoDAO.selectRouteInfoList( routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 routeInfoVO
	 * @return TN_ROUTE_INFO 총 갯수
	 * @exception
	 */
	public int selectRouteInfoListTotalCount(RouteInfoVO routeInfoVO) {
		return routeInfoDAO.selectRouteInfoListTotalCount( routeInfoVO);
	}
	
}
