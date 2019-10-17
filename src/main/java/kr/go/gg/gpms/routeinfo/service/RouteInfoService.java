package kr.go.gg.gpms.routeinfo.service;

import java.util.List;

import kr.go.gg.gpms.routeinfo.service.model.RouteInfoDefaultVO;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 노선_정보
 * TN_ROUTE_INFO
 *
 * @Class Name : RouteInfoService.java
 * @Description : RouteInfo Business class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RouteInfoService {

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 등록한다.
	 * @param routeInfoVO - 등록할 정보가 담긴 RouteInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	Integer insertRouteInfo(RouteInfoVO routeInfoVO) throws Exception;

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 수정한다.
	 * @param routeInfoVO - 수정할 정보가 담긴 RouteInfoVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRouteInfo(RouteInfoVO routeInfoVO) throws Exception;

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 삭제한다.
	 * @param routeInfoVO - 삭제할 정보가 담긴 RouteInfoVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRouteInfo(RouteInfoVO routeInfoVO) throws Exception;

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	RouteInfoVO selectRouteInfo(RouteInfoVO routeInfoVO) throws Exception;

	/**
	 * 노선_정보(TN_ROUTE_INFO) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 routeInfoVO
	 * @return TN_ROUTE_INFO 목록
	 * @exception Exception
	 */
	List<RouteInfoVO> selectRouteInfoList(RouteInfoVO routeInfoVO) throws Exception;

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 상세 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	RouteInfoVO RouteInfoView(RouteInfoVO routeInfoVO) throws Exception;
	
	/**
	 * 노선_정보(TN_ROUTE_INFO)을 상세 조회 후 수정한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	int updateRouteInfoView(RouteInfoVO routeInfoVO) throws Exception;
	
	/**
	 * 노선_정보(TN_ROUTE_INFO) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 routeInfoVO
	 * @return TN_ROUTE_INFO 총 갯수
	 * @exception
	 */
	int selectRouteInfoListTotalCount(RouteInfoVO routeInfoVO);
	
}

