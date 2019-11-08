package kr.go.gg.gpms.pothole.crontab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import kr.go.gg.gpms.pothole.cmptnc.service.CmptncService;
import kr.go.gg.gpms.pothole.cmptnc.service.model.CmptncVO;
import kr.go.gg.gpms.pothole.sms.service.SmsService;
import kr.go.gg.gpms.pothole.sttemnt.service.SttemntService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;

@Component
public class Scheduler {

	@Resource(name = "smsService")
    private SmsService smsService;

    @Resource(name = "sttemntService")
    private SttemntService sttemntService;

    @Resource(name = "cmptncService")
    private CmptncService cmptncService;

    @Resource(name = "smsScheduler")
	protected ThreadPoolTaskScheduler smsScheduler;


    /**
	 * 2019년 고도화 사업 - 문자예약전송 schedule 재생성 스케줄러 (context-schedule.xml에 설정정보 저장됨.)
	 * 문자예약전송 schedule을 DB에 저장된 문자전송시간에 실행되도록 다시 만들어준다.
	 * @author    : lsk
	 * @date      : 2019. 7.
	 *
	 * @throws Exception
	 * @exception : Exception
	 */
    public void setCronTime() throws Exception{

    	//DB에서 시작시작 가져오기 --> scheduleInfo.properties에서 설정정보 가져오기 로 변경함.
    	SttemntVO sttemntSMSTime = new SttemntVO();
    	sttemntSMSTime = sttemntService.selectPotholeSMSTime(sttemntSMSTime);

		System.out.println("chk : " + sttemntSMSTime.getSMS_TIME_CRON());

		// 이전 스케줄러 shutdown
		smsScheduler.shutdown();

		// 현재 문자발송 시간으로 스케줄러 생성
		smsScheduler.initialize();

		Runnable task2 = new Runnable() {

	      public void run() {

	            try {
	            	// 문자예약발송 함수 호출
	            	cronSMSSend();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(e);
				}

	        }

	    };
		smsScheduler.schedule(task2, new CronTrigger(sttemntSMSTime.getSMS_TIME_CRON()));

    }


	/**
	 * 2019년 고도화 사업 - SMS 전송 스케줄러
	 * 문자전송 마감시간 이후 등록된 신고 내역일 경우 문자전송 시작시간이 되면 관할구역 담당자에게 전송한다.
	 * @author    : lsk
	 * @date      : 2019. 7.
	 *
	 * @throws Exception
	 * @exception : Exception
	 */
    public void cronSMSSend() throws Exception{

		String LogFlag = "SMS 전송시작합니다. ";
		System.out.println(LogFlag);

		try {

			SttemntVO sttemntVO = new SttemntVO();

			// SMS 전송 여부 - 미전송
			sttemntVO.setSMS_SNDNG_AT("N");

			Date today = new Date();
			Date yesterday = new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

            // 전일 문자전송 마감시간 ~ 문자전송 시작시간
            yesterday.setTime(today.getTime()-((long)1000*60*60*24));

            //DB에서 시작시작 가져오기 --> CREAT_DE(접수일시)에 시간정보가 저장되지 않고 있어 시간정보 가져오지 않음.
            //SttemntVO sttemntSMSTime = new SttemntVO();
        	//sttemntSMSTime = sttemntService.selectPotholeSMSTime(sttemntVO);
            //String SMSStartTime = sttemntSMSTime.getSMS_TIME_START();

            // CREAT_DE(접수일시)에 시간정보가 저장되지 않고 있어 날짜 정보만 넘김.
			//sttemntVO.setSTTEMNT_DT_START(simpleDate.format(yesterday));
			//sttemntVO.setSTTEMNT_DT_END(simpleDate.format(today));
            //테스트용
            sttemntVO.setSTTEMNT_DT_START("2018-05-14 ");
            sttemntVO.setSTTEMNT_DT_END("2018-05-15 ");

			LogFlag = "STTEMNT_DT_START : " + sttemntVO.getSTTEMNT_DT_START();
			System.out.println(LogFlag);

			LogFlag = "STTEMNT_DT_END : " + sttemntVO.getSTTEMNT_DT_END();
			System.out.println(LogFlag);

			// SMS미전송 신고내역 건수 가져오기 (문자전송 마감시간~문자전송 시작시간에 등록된 신고내역)

			// 문자제한시간에 등록된 관할구역별 신고내역 등록 건수 및 대표담당자정보
			// 전일부터 현재까지 접수된 신고내역 중 문자 미전송 AND 처리상태가 [접수]상태인 건을 대상으로 문자를 전송함.
			// (TN_POTHOLE_STTEMNT>SMS_SNDNG_AT, CREAT_DE, PRCS_STTUS로 확인가능함.)
			List<SttemntVO> sttemntGrpCnt = sttemntService.selectPotholeSttemntGrpCount(sttemntVO);
			List<SttemntVO> sttemntGrpList = sttemntService.selectPotholeSttemntGrpList(sttemntVO);

			LogFlag = "sttemntGrpCnt cnt : " + sttemntGrpCnt.size();
			System.out.println(LogFlag);

    		// 1. sms 전송
			for(int i=0; i <sttemntGrpCnt.size(); i++){

    			//1-1. 포트홀_관할구역(CMPTNC_ZONE)의 대표 담당자에게 전송
        		//smsService.sendStartSMS(sttemntGrpCnt.get(i));

        		CmptncVO cmptncVO = new CmptncVO();
        		SttemntVO sttemntResult = new SttemntVO();

        		// 등록 건수
        		sttemntResult.setPTH_RG_NO(sttemntGrpCnt.get(i).getPTH_RG_NO());

        		//관할_구역_부서_코드 입력
        		cmptncVO.setDEPT_CODE(sttemntGrpCnt.get(i).getDEPT_CODE());

        		LogFlag = i + ". pth_count : " + sttemntGrpCnt.get(i).getPTH_RG_NO()
    					+ " / LOWEST_DEPT_NM : " + sttemntGrpCnt.get(i).getLOWEST_DEPT_NM()
        				+ " / DEPT_CODE : " + sttemntGrpCnt.get(i).getDEPT_CODE();
				System.out.println(LogFlag);

        		//관할_구역_담당자 목록 가져오기
        		List<CmptncVO> items = cmptncService.selectCmptncZonePocList(cmptncVO);

        		// 관할_구역_담당자 모두에게 문자 전송함.
        		for(int j=0; j <items.size(); j++){

        			//관할_구역_담당자 정보에서 가져온 담당자명, 연락처, 부서_명
        			sttemntResult.setCHARGER_NM(items.get(j).getCHARGER_NM());
        			sttemntResult.setCTTPC(items.get(j).getCTTPC());
        			sttemntResult.setLOWEST_DEPT_NM(items.get(j).getLOWEST_DEPT_NM());

        			//1-2. 포트홀_관할_구역_담당자(TN_CMPTNC_ZONE_POC) : 해당 관할 구역의 모든 담당자에게 전송 추가
        			//smsService.sendStartSMS(sttemntResult);

        			LogFlag = i + "-" + j + ". pth_count : " + sttemntResult.getPTH_RG_NO()
        					+ " / LOWEST_DEPT_NM : " + sttemntResult.getLOWEST_DEPT_NM()
            				+ " / DEPT_CODE : " + sttemntResult.getCHARGER_NM();
    				System.out.println(LogFlag);
        		}
    		}

			// 2. Flag update
			for(int i2=0; i2 <sttemntGrpList.size(); i2++){

				SttemntVO sttemntResult2 = new SttemntVO();

				sttemntResult2.setPTH_RG_NO(sttemntGrpList.get(i2).getPTH_RG_NO());
				sttemntResult2.setPthmode(sttemntGrpList.get(i2).getPthmode());

				LogFlag = i2 + ". pth_update : " + sttemntResult2.getPTH_RG_NO()
    					+ " / pthmode : " + sttemntResult2.getPthmode();
				System.out.println(LogFlag);

				// update
				sttemntResult2.setPRCS_STTUS("PRCS0002");
				sttemntResult2.setSMS_SNDNG_AT("Y");
                sttemntService.updatePthSttmntSttus(sttemntResult2);
                sttemntService.updatePotholeSttemntSttus(sttemntResult2);

			}

		} catch (NullPointerException e) {

	        e.printStackTrace();
	        LogFlag = "================ NullPointerException ================";
            System.out.println(LogFlag);

	    } catch (Exception e) {

	        e.printStackTrace();
	        LogFlag = "================ Exception ================";
            System.out.println(LogFlag);

	    }
	}

}