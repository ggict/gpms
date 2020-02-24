package kr.go.gg.gpms.predctmodel.service;

import java.util.Map;

import kr.go.gg.gpms.predctmodel.service.model.PredctModelVO;

/**
 * 예측 모델
 *
 * @Class Name : PredctModelService.java
 * @Description : PredctModel Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2020-02-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface PredctModelService {

    /**
     * 예측 모델 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map<String, Object> selectPredctModelList(PredctModelVO searchVO) throws Exception ;

}

