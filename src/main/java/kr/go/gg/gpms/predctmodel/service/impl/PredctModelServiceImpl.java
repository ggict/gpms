package kr.go.gg.gpms.predctmodel.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.predctmodel.service.PredctModelService;
import kr.go.gg.gpms.predctmodel.service.model.PredctModelVO;

/**
 * 예측 모델
 *
 * @Class Name : PredctModelServiceImpl.java
 * @Description : PredctModel Business Implement class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2020-02-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("predctModelService")
public class PredctModelServiceImpl extends AbstractServiceImpl implements PredctModelService {

    @Resource(name = "predctModelDAO")
    private PredctModelDAO predctModelDAO;


    /**
     * 보수_대상_항목_그룹(TN_RPAIR_TRGET_GROUP) 공용성 예측 모델 목록을 조회한다.
     * @param rpairTrgetGroupVO
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> selectPredctModelList(PredctModelVO searchVO) throws Exception {
        Map<String, BigDecimal> map = predctModelDAO.selectPredctModelList(searchVO);

        // 현재년도
        BigDecimal[][] a = new BigDecimal[5][5];
        // 등급별 최소값
        BigDecimal[][] b = new BigDecimal[5][1];

//        a[0][0] = new BigDecimal(0.87755);
//        a[0][1] = new BigDecimal(0.02268);
//        a[0][2] = new BigDecimal(0.00907);
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
            // 경과년수
            BigDecimal[] elapseYyCnt = {
                new BigDecimal(1)
                , new BigDecimal(2)
                , new BigDecimal(3)
                , new BigDecimal(4)
                , new BigDecimal(5)
                , new BigDecimal(6)
                , new BigDecimal(7)
                , new BigDecimal(8)
                , new BigDecimal(9)
                , new BigDecimal(10)
            };
            // elapseYyCnt^(a1)*10^(a0)
            BigDecimal[] predctY = new BigDecimal[10];

            if ( "LCI".equals(searchVO.getPredctModelKndSe()) ) {
                // 테스트 데이터
                BigDecimal[] c0 = {
                    new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                };

                for ( int i = 0; i < 10; i++ ) {
                    // 10 - 4 * b0 / 36.0
                    predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(c0[i]).divide(new BigDecimal(36.0), MathContext.DECIMAL32));
                }
            } else if ( "ACI".equals(searchVO.getPredctModelKndSe()) ) {
                // 테스트 데이터
                BigDecimal[] c0 = {
                    new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                };

                for ( int i = 0; i < 10; i++ ) {
                    // 10 - 4 * F2 / 10.5
                    predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(c0[i]).divide(new BigDecimal(10.5), MathContext.DECIMAL32));
                }
            } else if ( "PATI".equals(searchVO.getPredctModelKndSe()) ) {
                // 테스트 데이터
                BigDecimal[] c0 = {
                    new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                };

                for ( int i = 0; i < 10; i++ ) {
                    // 10 - 4 * F2 / 10.5
                    predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(c0[i]).divide(new BigDecimal(28.0), MathContext.DECIMAL32));
                }
            } else if ( "RUTI".equals(searchVO.getPredctModelKndSe()) ) {
                // 테스트 데이터
                BigDecimal[] c0 = {
                    new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                };

                for ( int i = 0; i < 10; i++ ) {
                    // 10 - 4 * F2 / 10.5
                    predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(c0[i]).divide(new BigDecimal(15.0), MathContext.DECIMAL32));
                }
            } else if ( "GPCI".equals(searchVO.getPredctModelKndSe()) ) {
                // 테스트 데이터
                BigDecimal[] c0 = {
                    new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                    , new BigDecimal(0)
                };

                for ( int i = 0; i < 10; i++ ) {
                    // 10 - 4 * F2 / 10.5
                    predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(c0[i]).divide(new BigDecimal(10.5), MathContext.DECIMAL32));
                }
            }

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("x", elapseYyCnt);
            dataMap.put("y", predctY);
            dataMap.put("predctModelKndSe", searchVO.getPredctModelKndSe());

            return dataMap;
        }

        b[0][0] = BigDecimal.ZERO;
        b[1][0] = map.get("max").divide(new BigDecimal(5), MathContext.DECIMAL32);
        b[2][0] = map.get("max").divide(new BigDecimal(5), MathContext.DECIMAL32).multiply(new BigDecimal(2));
        b[3][0] = map.get("max").divide(new BigDecimal(5), MathContext.DECIMAL32).multiply(new BigDecimal(3));
        b[4][0] = map.get("max").divide(new BigDecimal(5), MathContext.DECIMAL32).multiply(new BigDecimal(4));

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
            incrsQy[i] = sum.divide(new BigDecimal(tranMtrix.length), MathContext.DECIMAL32);
            // 누적증가량
            accmltIncrsQy[i] = (i == 0 ? incrsQy[i] : incrsQy[i].add(accmltIncrsQy[i - 1]));
            // Log(경과년수)
            logElapseYyCnt[i] = new BigDecimal(Math.log10(elapseYyCnt[i].doubleValue()));

            // log(0)는 계산이 안되어 0일 경우에는 차트 표시 안함.
            if ( BigDecimal.ZERO.compareTo(accmltIncrsQy[i]) == 0 ) {
                return null;
            }
//              logAccmltIncrsQy[i] = BigDecimal.ZERO;
//          } else {
//              logAccmltIncrsQy[i] = new BigDecimal(Math.log10(accmltIncrsQy[i].doubleValue()));
//          }
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
        BigDecimal a1 = (sumXY.multiply(new BigDecimal(10)).subtract(sumX.multiply(sumY))).divide((sumX2.multiply(new BigDecimal(10)).subtract(new BigDecimal(Math.pow(sumX.doubleValue(), 2)))), MathContext.DECIMAL32);
        // sumXY/10-((10*sumXY-sumX*sumY)/(10*sumX2-sumX^2))*(sumX/10)
        BigDecimal a0 = sumY.divide(new BigDecimal(10), MathContext.DECIMAL32).subtract(a1.multiply((sumX.divide(new BigDecimal(10), MathContext.DECIMAL32))));

        // elapseYyCnt^(a1)*10^(a0)
        BigDecimal[] predctY = new BigDecimal[x.length];
        for ( int i = 0; i < elapseYyCnt.length; i++ ) {
            BigDecimal b0 = ((new BigDecimal(Math.pow(elapseYyCnt[i].doubleValue(), a1.doubleValue()))).multiply(new BigDecimal(Math.pow(10, a0.doubleValue())))).setScale(2, BigDecimal.ROUND_HALF_UP);

            if ( "LCI".equals(searchVO.getPredctModelKndSe()) ) {
                // 10 - 4 * b0 / 36.0
                predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(b0).divide(new BigDecimal(36.0), MathContext.DECIMAL32));
            } else if ( "ACI".equals(searchVO.getPredctModelKndSe()) ) {
                // 10 - 4 * F2 / 10.5
                predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(b0).divide(new BigDecimal(10.5), MathContext.DECIMAL32));
            } else if ( "PATI".equals(searchVO.getPredctModelKndSe()) ) {
                // 10 - 4 * F2 / 10.5
                predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(b0).divide(new BigDecimal(28.0), MathContext.DECIMAL32));
            } else if ( "RUTI".equals(searchVO.getPredctModelKndSe()) ) {
                // 10 - 4 * F2 / 10.5
                predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(b0).divide(new BigDecimal(15.0), MathContext.DECIMAL32));
            } else if ( "GPCI".equals(searchVO.getPredctModelKndSe()) ) {
                // 10 - 4 * F2 / 10.5
                predctY[i] = (new BigDecimal(10)).subtract((new BigDecimal(10)).multiply(b0).divide(new BigDecimal(10.5), MathContext.DECIMAL32));
            }
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("x", elapseYyCnt);
        dataMap.put("y", predctY);
        dataMap.put("predctModelKndSe", searchVO.getPredctModelKndSe());

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
