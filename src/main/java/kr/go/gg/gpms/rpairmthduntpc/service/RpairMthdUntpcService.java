package kr.go.gg.gpms.rpairmthduntpc.service;

import java.util.List;

import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcDefaultVO;
import kr.go.gg.gpms.rpairmthduntpc.service.model.RpairMthdUntpcVO;
import kr.go.gg.gpms.smdtalaststtus.service.model.SmDtaLastSttusVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보수_공법_단가
 * TN_RPAIR_MTHD_UNTPC
 *
 * @Class Name : RpairMthdUntpcService.java
 * @Description : RpairMthdUntpc Business class
 * @Modification Information
 *
 * @author muhanit.kr
 * @since 2017-11-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface RpairMthdUntpcService {

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;
	
	/**
	 * 보수_공법_단가_이력(TH_RPAIR_MTHD_UNTPC)을 등록한다.
	 * @param rpairMthdUntpcVO - 등록할 정보가 담긴 RpairMthdUntpcVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertRpairMthdUntpcHist(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 수정한다.
	 * @param rpairMthdUntpcVO - 수정할 정보가 담긴 RpairMthdUntpcVO
	 * @return int형
	 * @exception Exception
	 */
	int updateRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 삭제한다.
	 * @param rpairMthdUntpcVO - 삭제할 정보가 담긴 RpairMthdUntpcVO
	 * @return int형 
	 * @exception Exception
	 */
	int deleteRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC)을 조회한다.
	 * @param rpairMthdUntpcVO - 조회할 정보가 담긴 RpairMthdUntpcVO
	 * @return 조회한 TN_RPAIR_MTHD_UNTPC
	 * @exception Exception
	 */
	RpairMthdUntpcVO selectRpairMthdUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 목록
	 * @exception Exception
	 */
	List<RpairMthdUntpcVO> selectRpairMthdUntpcList(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;

	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	int selectRpairMthdUntpcListTotalCount(RpairMthdUntpcVO rpairMthdUntpcVO);
	
	/**
	 * 보수_공법_단가(TN_RPAIR_MTHD_UNTPC) 전체 단가 평균을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 rpairMthdUntpcVO
	 * @return TN_RPAIR_MTHD_UNTPC 총 갯수
	 * @exception
	 */
	int selectRpairMthdUntpcAvgTotAmount(RpairMthdUntpcVO rpairMthdUntpcVO);

	/**
	 * 보수대상 선정 > 예산수준별 시나리오 분석
	 * @param rpairMthdUntpcVO
	 * @return
	 */
	List<RpairMthdUntpcVO> prcStatUntpc(RpairMthdUntpcVO rpairMthdUntpcVO) throws Exception;
}

