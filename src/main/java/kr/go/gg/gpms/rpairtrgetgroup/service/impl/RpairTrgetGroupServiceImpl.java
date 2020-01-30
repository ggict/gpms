package kr.go.gg.gpms.rpairtrgetgroup.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.rpairtrgetgroup.service.RpairTrgetGroupService;
import kr.go.gg.gpms.rpairtrgetgroup.service.model.RpairTrgetGroupVO;

/**
 * 보수_대상_항목_그룹
 *
 * @Class Name : RpairTrgetGroupServiceImpl.java
 * @Description : RpairTrgetGroup Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-10-18
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("rpairTrgetGroupService")
public class RpairTrgetGroupServiceImpl extends AbstractServiceImpl implements RpairTrgetGroupService {

	@Resource(name = "rpairTrgetGroupDAO")
	private RpairTrgetGroupDAO rpairTrgetGroupDAO;

	//@Resource(name="RpairTrgetGroupIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
	 * @return TN_RPAIR_TRGET_GROUP 목록
	 * @exception Exception
	 */
	public List<RpairTrgetGroupVO> selectRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
	    return rpairTrgetGroupDAO.selectRpairTrgetGroupList( rpairTrgetGroupVO);
	}

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    public int selectRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
        return rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalCount( rpairTrgetGroupVO);
    }

    /**
     * 보수대상 우선순위 저장 처리
     */
    public int updatePRIORT(List<RpairTrgetGroupVO> lvo, RpairTrgetGroupVO vo) throws Exception {
        int ret = 0;

        for ( int i = 0; i < lvo.size(); i++ ) {
            RpairTrgetGroupVO rpairTrgetGroupVO = lvo.get(i);
            rpairTrgetGroupVO.setCRTR_NO(vo.getCRTR_NO());
            rpairTrgetGroupVO.setUPDUSR_NO(vo.getUPDUSR_NO());

            ret += rpairTrgetGroupDAO.updatePRIORT(rpairTrgetGroupVO);
        }

        return ret;
    }

    /**
     * 보수대상 CELL_ID 가져오기.
     */
    @Override
    public List<RpairTrgetGroupVO> selectRpairTrgetGroupCELLList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
        return rpairTrgetGroupDAO.selectRpairTrgetGroupCELLList( rpairTrgetGroupVO);
    }

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 대비 포장공사 진행 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 목록
     * @exception Exception
     */
    public List<RpairTrgetGroupVO> selectCntrwkByRpairTrgetGroupList(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
    	return rpairTrgetGroupDAO.selectCntrwkByRpairTrgetGroupList( rpairTrgetGroupVO);
    }

    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 대비 포장공사 진행 총 갯수를 조회한다.
     * @param searchVO - 조회할 정보가 담긴 rpairTrgetGroupVO
     * @return TN_RPAIR_TRGET_GROUP 총 갯수
     * @exception
     */
    public int selectCntrwkByRpairTrgetGroupListTotalCount(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
    	return rpairTrgetGroupDAO.selectCntrwkByRpairTrgetGroupListTotalCount( rpairTrgetGroupVO);
    }





	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 등록한다.
	 * @param rpairTrgetGroupVO - 등록할 정보가 담긴 RpairTrgetGroupVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		//String id = egovIdGnrService.getNextStringId();
		//rpairTrgetGroupVO.setId(id);

		return rpairTrgetGroupDAO.insertRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 수정한다.
	 * @param rpairTrgetGroupVO - 수정할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.updateRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 삭제한다.
	 * @param rpairTrgetGroupVO - 삭제할 정보가 담긴 RpairTrgetGroupVO
	 * @return int형
	 * @exception Exception
	 */
	public int deleteRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.deleteRpairTrgetGroup( rpairTrgetGroupVO);
	}

	/**
	 * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP)을 조회한다.
	 * @param rpairTrgetGroupVO - 조회할 정보가 담긴 RpairTrgetGroupVO
	 * @return 조회한 TN_RPAIR_TRGET_GROUP
	 * @exception Exception
	 */
	public RpairTrgetGroupVO selectRpairTrgetGroup(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		RpairTrgetGroupVO resultVO = rpairTrgetGroupDAO.selectRpairTrgetGroup( rpairTrgetGroupVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	@Override
	public int updateToggleTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.updateToggleTMPR_SLCTN_AT( rpairTrgetGroupVO);
	}

	@Override
	public int updateInitTMPR_SLCTN_AT(RpairTrgetGroupVO rpairTrgetGroupVO)  throws Exception {
		return rpairTrgetGroupDAO.updateInitTMPR_SLCTN_AT( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetDeptStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetDeptStatistics( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetMethodStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetMethodStatistics( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairTrgetAdminStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetAdminStatistics( rpairTrgetGroupVO);
	}

	@Override
	public RpairTrgetGroupVO selectRpairTrgetGroupListTotalSummary(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairTrgetGroupListTotalSummary( rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairRoutLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairRoutLenStats(rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairRoutLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairRoutLenStatsExcel(rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairDeptLenStats(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairDeptLenStats(rpairTrgetGroupVO);
	}

	@Override
	public List<RpairTrgetGroupVO> selectRpairDeptLenStatsExcel(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
		return rpairTrgetGroupDAO.selectRpairDeptLenStatsExcel(rpairTrgetGroupVO);
	}

	/**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 공용성 예측 모델 목록을 조회한다.
     * @param rpairTrgetGroupVO
     * @return
     * @throws Exception
     */
	@Override
    public Map<String, BigDecimal[]> selectRpairTrgetPredctStatistics(RpairTrgetGroupVO rpairTrgetGroupVO) throws Exception {
	    Map<String, BigDecimal> map = rpairTrgetGroupDAO.selectRpairTrgetPredctStatistics(rpairTrgetGroupVO);

	    // 현재년도
	    BigDecimal[][] a = new BigDecimal[5][5];
	    // 등급별 최소값
	    BigDecimal[][] b = new BigDecimal[5][1];

//	    a[0][0] = new BigDecimal(0.87755);
//	    a[0][1] = new BigDecimal(0.02268);
//	    a[0][2] = new BigDecimal(0.00907);
//        a[0][3] = new BigDecimal(0.00227);
//        a[0][4] = new BigDecimal(0.00454);
//
//        a[1][0] = BigDecimal.ZERO;
//        a[1][1] = new BigDecimal(0.06349);
//        a[1][2] = new BigDecimal(0.00227);
//        a[1][3] = new BigDecimal(0.00454);
//        a[1][4] = new BigDecimal(0.000);
//
//        a[2][0] = BigDecimal.ZERO;
//        a[2][1] = BigDecimal.ZERO;
//        a[2][2] = new BigDecimal(0.00680);
//        a[2][3] = new BigDecimal(0.000);
//        a[2][4] = new BigDecimal(0.000);
//
//        a[3][0] = BigDecimal.ZERO;
//        a[3][1] = BigDecimal.ZERO;
//        a[3][2] = BigDecimal.ZERO;
//        a[3][3] = new BigDecimal(0.00227);
//        a[3][4] = new BigDecimal(0.00227);
//
//        a[4][0] = BigDecimal.ZERO;
//        a[4][1] = BigDecimal.ZERO;
//        a[4][2] = BigDecimal.ZERO;
//        a[4][3] = BigDecimal.ZERO;
//        a[4][4] = new BigDecimal(0.00227);
//
//        b[0][0] = BigDecimal.ZERO;
//        b[1][0] = new BigDecimal(8);
//        b[2][0] = new BigDecimal(16);
//        b[3][0] = new BigDecimal(24);
//        b[4][0] = new BigDecimal(32);


	    a[0][0] = map.get("aa");
	    a[0][1] = map.get("ab");
	    a[0][2] = map.get("ac");
	    a[0][3] = map.get("ad");
	    a[0][4] = map.get("ae");

	    a[1][0] = map.get("ba");
	    a[1][1] = map.get("bb");
	    a[1][2] = map.get("bc");
	    a[1][3] = map.get("bd");
	    a[1][4] = map.get("be");

	    a[2][0] = map.get("ca");
	    a[2][1] = map.get("cb");
	    a[2][2] = map.get("cc");
	    a[2][3] = map.get("cd");
	    a[2][4] = map.get("ce");

	    a[3][0] = map.get("da");
	    a[3][1] = map.get("db");
	    a[3][2] = map.get("dc");
	    a[3][3] = map.get("dd");
	    a[3][4] = map.get("de");

	    a[4][0] = map.get("ea");
	    a[4][1] = map.get("eb");
	    a[4][2] = map.get("ec");
	    a[4][3] = map.get("ed");
	    a[4][4] = map.get("ee");

	    // 데이터가 NULL일 경우 차트 표시 못함.
	    if ( map.get("max") == null ) {
            return null;
        }

	    b[0][0] = BigDecimal.ZERO;
	    b[1][0] = map.get("max").divide(new BigDecimal(5));
	    b[2][0] = map.get("max").divide(new BigDecimal(5)).multiply(new BigDecimal(2));
	    b[3][0] = map.get("max").divide(new BigDecimal(5)).multiply(new BigDecimal(3));
	    b[4][0] = map.get("max").divide(new BigDecimal(5)).multiply(new BigDecimal(4));

	    // 경과년수별 전이행렬
	    List<BigDecimal[][]> list = new ArrayList<BigDecimal[][]>();
	    list.add(a);
	    list.add(productMatrix(list.get(0), a));
	    list.add(productMatrix(list.get(1), a));
	    list.add(productMatrix(list.get(2), a));
	    list.add(productMatrix(list.get(3), a));
	    list.add(productMatrix(list.get(4), a));
	    list.add(productMatrix(list.get(5), a));
	    list.add(productMatrix(list.get(6), a));
	    list.add(productMatrix(list.get(7), a));
	    list.add(productMatrix(list.get(8), a));

	    // 경과년수
	    BigDecimal[] elapseYyCnt = new BigDecimal[10];
	    // 증가량
	    BigDecimal[] incrsQy = new BigDecimal[10];
	    // 누적증가량
	    BigDecimal[] accmltIncrsQy = new BigDecimal[10];
	    // Log(경과년수)
	    BigDecimal[] logElapseYyCnt = new BigDecimal[10];
	    // Log(누적증가량)
	    BigDecimal[] logAccmltIncrsQy = new BigDecimal[10];

	    // 경과년수별 전이행렬
	    for ( int i = 0; i < list.size(); i++ ) {
	        // 전이행렬
	        BigDecimal[][] tranMtrix = productMatrix(list.get(i), b);

	        BigDecimal sum = BigDecimal.ZERO;
	        for ( int j = 0; j < tranMtrix.length; j++ ) {
	            sum = sum.add(tranMtrix[j][0]);
	        }
	        // 경과년수
	        elapseYyCnt[i] = new BigDecimal(i + 1);
	        // 증가량
	        incrsQy[i] = sum.divide(new BigDecimal(tranMtrix.length), 5);
	        // 누적증가량
	        accmltIncrsQy[i] = (i == 0 ? incrsQy[i] : incrsQy[i].add(accmltIncrsQy[i - 1]));
	        // Log(경과년수)
	        logElapseYyCnt[i] = new BigDecimal(Math.log10(elapseYyCnt[i].doubleValue()));

	        // log(0)는 계산이 안되어 0일 경우에는 차트 표시 안함.
	        if ( BigDecimal.ZERO.compareTo(accmltIncrsQy[i]) == 0 ) {
	            return null;
	        }
//	            logAccmltIncrsQy[i] = BigDecimal.ZERO;
//	        } else {
//    	        logAccmltIncrsQy[i] = new BigDecimal(Math.log10(accmltIncrsQy[i].doubleValue()));
//	        }
	        // Log(누적증가량)
	        logAccmltIncrsQy[i] = new BigDecimal(Math.log10(accmltIncrsQy[i].doubleValue()));
	    }

	    BigDecimal[] x = logElapseYyCnt;
	    BigDecimal[] x2 = new BigDecimal[x.length];
	    for ( int i = 0; i < x.length; i++ ) {
	        x2[i] = new BigDecimal(Math.pow(x[i].doubleValue(), 2));
	    }
	    BigDecimal[] y = logAccmltIncrsQy;
	    BigDecimal[] xy = new BigDecimal[x.length];
	    for ( int i = 0; i < x.length; i++ ) {
	        xy[i] = x[i].multiply(y[i]);
	    }
	    BigDecimal sumX = BigDecimal.ZERO;
	    for ( int i = 0; i < x.length; i++ ) {
	        sumX = sumX.add(x[i]);
        }
	    BigDecimal sumY = BigDecimal.ZERO;
	    for ( int i = 0; i < y.length; i++ ) {
            sumY = sumY.add(y[i]);
        }
	    BigDecimal sumXY = BigDecimal.ZERO;
	    for ( int i = 0; i < xy.length; i++ ) {
            sumXY = sumXY.add(xy[i]);
        }
	    BigDecimal sumX2 = BigDecimal.ZERO;
	    for ( int i = 0; i < x2.length; i++ ) {
            sumX2 = sumX2.add(x2[i]);
        }

	    // ((10*sumXY-sumX*sumY)/(10*sumX2-sumX^2))
	    BigDecimal a1 = (sumXY.multiply(new BigDecimal(10)).subtract(sumX.multiply(sumY))).divide((sumX2.multiply(new BigDecimal(10)).subtract(new BigDecimal(Math.pow(sumX.doubleValue(), 2)))), 5);
	    // sumXY/10-((10*sumXY-sumX*sumY)/(10*sumX2-sumX^2))*(sumX/10)
        BigDecimal a0 = sumY.divide(new BigDecimal(10)).subtract(a1.multiply((sumX.divide(new BigDecimal(10)))));

        // elapseYyCnt^(a1)*10(a0)
        BigDecimal[] predctY = new BigDecimal[x.length];
        for ( int i = 0; i < elapseYyCnt.length; i++ ) {
            predctY[i] = ((new BigDecimal(Math.pow(elapseYyCnt[i].doubleValue(), a1.doubleValue()))).multiply(new BigDecimal(Math.pow(10, a0.doubleValue())))).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        Map<String, BigDecimal[]> dataMap = new HashMap<String, BigDecimal[]>();
        dataMap.put("x", elapseYyCnt);
        dataMap.put("y", predctY);

	    return dataMap;
	}

	/**
	 * 행렬 곱셈
	 * @param a
	 * @param b
	 * @return
	 */
	public BigDecimal[][] productMatrix(BigDecimal[][] a, BigDecimal[][] b) throws Exception {
	    BigDecimal[][] answer = new BigDecimal[a.length][b[0].length];

        for(int i=0; i<answer.length;i++){
            for(int j=0; j<answer[0].length;j++){

                for(int k=0; k<a[0].length;k++){
                    if ( answer[i][j] == null ) {
                        answer[i][j] = BigDecimal.ZERO;
                    }
                    answer[i][j] = answer[i][j].add(a[i][k].multiply(b[k][j]));
                }

            }

        }

        return answer;
    }

}
