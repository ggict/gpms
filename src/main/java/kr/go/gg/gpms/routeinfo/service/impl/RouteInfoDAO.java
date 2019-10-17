


package kr.go.gg.gpms.routeinfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import egovframework.example.cmmn.impl.BaseDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;

/**
 * 노선_정보
 *
 * @Class Name : RouteInfoDAO.java
 * @Description : RouteInfo DAO Class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("routeInfoDAO")
public class RouteInfoDAO extends BaseDAO {

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 등록한다.
	 * @param routeInfoVO - 등록할 정보가 담긴 RouteInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public Integer insertRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return (Integer) insert("routeInfoDAO.insertRouteInfo", routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 수정한다.
	 * @param routeInfoVO - 수정할 정보가 담긴 RouteInfoVO
	 * @return 수정 결과
	 * @exception Exception
	 */
	public int updateRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return update("routeInfoDAO.updateRouteInfo", routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 삭제한다.
	 * @param routeInfoVO - 삭제할 정보가 담긴 RouteInfoVO
	 * @return 삭제 결과 
	 * @exception Exception
	 */
	public int deleteRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return delete("routeInfoDAO.deleteRouteInfo", routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	public RouteInfoVO selectRouteInfo(RouteInfoVO routeInfoVO) throws Exception {
		return (RouteInfoVO) select("routeInfoDAO.selectRouteInfo", routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO) 목록을 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return TN_ROUTE_INFO 목록
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RouteInfoVO> selectRouteInfoList(RouteInfoVO routeInfoVO) throws Exception {
		return (List<RouteInfoVO>)list("routeInfoDAO.selectRouteInfoList", routeInfoVO);
	}

	/**
	 * 노선_정보(TN_ROUTE_INFO)을 상세조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	public RouteInfoVO RouteInfoView(RouteInfoVO routeInfoVO) throws Exception {
		return (RouteInfoVO) select("routeInfoDAO.RouteInfoView", routeInfoVO);
	}
	
	/**
	 * 노선_정보(TN_ROUTE_INFO)을 상세조회 후 수정한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return 조회한 TN_ROUTE_INFO
	 * @exception Exception
	 */
	public int updateRouteInfoView(RouteInfoVO routeInfoVO) throws Exception {
		return update("routeInfoDAO.updateRouteInfoView", routeInfoVO);
	}
	/**
	 * 노선_정보(TN_ROUTE_INFO) 총 갯수를 조회한다.
	 * @param routeInfoVO - 조회할 정보가 담긴 RouteInfoVO
	 * @return TN_ROUTE_INFO 총 갯수
	 * @exception
	 */
	public int selectRouteInfoListTotalCount(RouteInfoVO routeInfoVO) {
		return (Integer) select("routeInfoDAO.selectRouteInfoListTotalCount", routeInfoVO);
	}
	
}
