package kr.go.gg.gpms.predctmodel.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.example.cmmn.impl.BaseDAO;
import kr.go.gg.gpms.predctmodel.service.model.PredctModelVO;

/**
 * 예측 모델
 *
 * @Class Name : PredctModelDAO.java
 * @Description : PredctModel DAO Class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2020-02-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("predctModelDAO")
public class PredctModelDAO extends BaseDAO {

    /**
     * 예측 모델 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public Map<String, BigDecimal> selectPredctModelList(PredctModelVO searchVO) throws Exception {
        return (Map<String, BigDecimal>)select("predctModelDAO.selectPredctModelList", searchVO);
    }

}
