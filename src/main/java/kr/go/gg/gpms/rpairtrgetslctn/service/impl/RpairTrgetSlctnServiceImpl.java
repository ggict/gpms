package kr.go.gg.gpms.rpairtrgetslctn.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.go.gg.gpms.rpairtrgetslctn.service.RpairTrgetSlctnService;
import kr.go.gg.gpms.rpairtrgetslctn.service.model.RpairTrgetSlctnVO;

/**
 * 보수_대상_선정
 *
 * @Class Name : RpairTrgetSlctnServiceImpl.java
 * @Description : RpairTrgetSlctn Business Implement class
 * @Modification Information
 *
 * @author leehb1592@gmail.com
 * @since 2017-09-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@EnableAsync
@Service("rpairTrgetSlctnService")
public class RpairTrgetSlctnServiceImpl extends AbstractServiceImpl implements RpairTrgetSlctnService {

    @Resource(name = "rpairTrgetSlctnDAO")
	private RpairTrgetSlctnDAO rpairTrgetSlctnDAO;

//    @Resource(name = "transactionManager")
//    private DataSourceTransactionManager transactionManager;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    DataSource dataSource;

	//@Resource(name="RpairTrgetSlctnIdGnrService")
	//private EgovIdGnrService egovIdGnrService;

	/**
	 * 보수대상선정이력 목록을 조회
	 */
	public List<RpairTrgetSlctnVO> selectRpairTrgetSlctnList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
	    return rpairTrgetSlctnDAO.selectRpairTrgetSlctnList(rpairTrgetSlctnVO);
	}

	/**
     * 보수대상선정시작 처리
     */
    public void addRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        // 보수_대상_선정 삭제
        deleteRpairTrgetSlctn(rpairTrgetSlctnVO);
        // 보수_대상_선정 등록
        insertRpairTrgetSlctn(rpairTrgetSlctnVO);
    }

    /**
     * 보수대상선정시작 처리(보수_대상_선정 삭제)
     */
    public int deleteRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        return rpairTrgetSlctnDAO.deleteRpairTrgetSlctn(rpairTrgetSlctnVO);
    }

	/**
	 * 보수대상선정시작 처리(보수_대상_선정 등록)
	 */
	public String insertRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
	    return rpairTrgetSlctnDAO.insertRpairTrgetSlctn(rpairTrgetSlctnVO);
	}

	/**
	 * 보수대상선정시작 처리(보수_대상_항목_그룹 등록)
	 */
	@Async
    public void procRepairTarget(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        // Connection 오브젝트 생성, 저장소 바인딩, 참조변수 값 리턴
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setAutoCommit(false); // 트랜잭션 시작



	    // 전체노선을 한번에 돌릴경우 메모리 오류 발생하여 메모리 오류 방지를 위해 노선별로 처리
	    List<RpairTrgetSlctnVO> routeCodeList = rpairTrgetSlctnDAO.selectRpairTrgetSlctnRouteCodeList(rpairTrgetSlctnVO);

	    for ( int i = 0; i < routeCodeList.size(); i++ ) {
    	        if ( i == 0 )  {  // 시작
    	            rpairTrgetSlctnVO.setSTART_END_CODE("S");
    	        } else if ( routeCodeList.size() - 1 == i ) { // 종료
    	            rpairTrgetSlctnVO.setSTART_END_CODE("E");
    	        } else {
    	            rpairTrgetSlctnVO.setSTART_END_CODE("");
    	        }
    	        rpairTrgetSlctnVO.setROUTE_CODE(routeCodeList.get(i).getROUTE_CODE());

    	        rpairTrgetSlctnDAO.procRepairTargetRangeSelect(rpairTrgetSlctnVO);
    	        rpairTrgetSlctnDAO.procRepairTargetRangeString(rpairTrgetSlctnVO);

    	        // 진행_률 수정
    	        rpairTrgetSlctnVO.setPROGRS_RT((i + 1) + "/" + routeCodeList.size());
    	        rpairTrgetSlctnDAO.updateRpairTrgetSlctnProgrsRt(rpairTrgetSlctnVO);

    	        conn.commit(); // 성공
	    }

	    DataSourceUtils.releaseConnection(conn, dataSource); // 커넥션을 닫음

        // 동기화 작업을 종료하고 저장소를 비운다
//        TransactionSynchronizationManager.unbindResource(this.dataSource);
//        TransactionSynchronizationManager.clearSynchronization();
	}

	/**
     * 보수대상선정시작 처리(보수_대상_항목_그룹 등록)
     */
	public int selectRpairTrgetSlctnSlctnYearListCnt(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
	    return rpairTrgetSlctnDAO.selectRpairTrgetSlctnSlctnYearListCnt(rpairTrgetSlctnVO);
	}

	public List<RpairTrgetSlctnVO> selectRpairTrgetSlctnRouteCodeList(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
	    return rpairTrgetSlctnDAO.selectRpairTrgetSlctnRouteCodeList(rpairTrgetSlctnVO);
	}
















    public HashMap procRepairTargetComplete(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
        HashMap resultVO = rpairTrgetSlctnDAO.procRepairTargetComplete(rpairTrgetSlctnVO);
        return resultVO;
    }



	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.updateRpairTrgetSlctn( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 수정한다.
	 * @param rpairTrgetSlctnVO - 수정할 정보가 담긴 RpairTrgetSlctnVO
	 * @return int형
	 * @exception Exception
	 */
	public int updateRangeSelection(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return rpairTrgetSlctnDAO.updateRangeSelection( rpairTrgetSlctnVO);
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN)을 조회한다.
	 * @param rpairTrgetSlctnVO - 조회할 정보가 담긴 RpairTrgetSlctnVO
	 * @return 조회한 TN_RPAIR_TRGET_SLCTN
	 * @exception Exception
	 */
	public RpairTrgetSlctnVO selectRpairTrgetSlctn(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		RpairTrgetSlctnVO resultVO = rpairTrgetSlctnDAO.selectRpairTrgetSlctn( rpairTrgetSlctnVO);
		/*
		if (resultVO == null)
			throw processException("info.nodata.msg");
		*/
		return resultVO;
	}

	/**
	 * 보수_대상_선정(TN_RPAIR_TRGET_SLCTN) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairTrgetSlctnVO
	 * @return TN_RPAIR_TRGET_SLCTN 총 갯수
	 * @exception
	 */
	public int selectRpairTrgetSlctnListTotalCount(RpairTrgetSlctnVO rpairTrgetSlctnVO) {
		return rpairTrgetSlctnDAO.selectRpairTrgetSlctnListTotalCount( rpairTrgetSlctnVO);

	}

	/**
	 * 보수대상선정 항목을 집계한다.
	 */
	public HashMap procClacprcRepairTargets(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception{
		return rpairTrgetSlctnDAO.procClacprcRepairTargets(rpairTrgetSlctnVO);
	}
	/**
	 * 보수대상선정 범위내 선정을 집계한다.
	 */
	@Override
	public HashMap procRepairTargetRangeSelect(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetRangeSelect(rpairTrgetSlctnVO);
	}

	/**
	 * 연속구간 연결 및 보수공법 재선정
	 */
	@Override
	public HashMap procRepairTargetRangeString(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetRangeString(rpairTrgetSlctnVO);
	}

	/**
	 * 예산범위내 선정
	 */
	@Override
	public HashMap procRepairTargetBudgetRate(RpairTrgetSlctnVO rpairTrgetSlctnVO) throws Exception {
		return rpairTrgetSlctnDAO.procRepairTargetBudgetRate(rpairTrgetSlctnVO);
	}








}
