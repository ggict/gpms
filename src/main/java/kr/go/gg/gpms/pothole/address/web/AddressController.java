package kr.go.gg.gpms.pothole.address.web;

/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;*/
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.pothole.address.service.AddressService;
import kr.go.gg.gpms.pothole.address.service.model.AddressVO;
import kr.go.gg.gpms.pothole.sms.service.SmsService;
import kr.go.gg.gpms.pothole.sttemnt.service.SttemntService;
import kr.go.gg.gpms.pothole.sttemnt.service.model.SttemntVO;
import kr.go.gg.gpms.pothole.cmptnc.service.CmptncService;
import kr.go.gg.gpms.pothole.cmptnc.service.model.CmptncVO;




/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 주소변환 Controller
 * @Classname : AddressController.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 2. 7.
 */
@Controller("addressController")
public class AddressController extends BaseController {

    @Resource(name = "addressService")
    private AddressService addressService;

	@Resource(name = "deptService")
	private DeptService deptService;

    @Resource(name = "smsService")
    private SmsService smsService;

    @Resource(name = "sttemntService")
    private SttemntService sttemntService;

    @Resource(name = "cmptncService")
    private CmptncService cmptncService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;


	@RequestMapping(value = { "/pothole/address/test.do" })
    public String testpage(@ModelAttribute("searchVO") AddressVO addressVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/pothole/address/test" ;
    }

	/**
	 * 신고정보 보정 프로시저를 실행한다.
	 * @author    : JOY
	 * @date      : 2018. 2. 7.
	 *
	 * @param     : SttemntVO - 조회할 정보가 담긴 SttemntVO
	 * @return    : "/pothole/address/address"
	 * @exception : Exception
	 */
	@RequestMapping(value = { "/pothole/address/selectSttemntData.do" })
    public @ResponseBody String selectSttemntData(@ModelAttribute("searchVO") AddressVO addressVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String LogFlag = "";

        // (0) 인코딩처리
        /*int ascRepr = (int) addressVO.getREPR_NM().charAt(0);
        int ascVehc = (int) addressVO.getREPR_VEHC_NO().charAt(0);
        int ascCont = (int) addressVO.getACCEPT_CONT().charAt(0);

        if ( ascRepr != 40 && ( ascRepr < 44032 || ascRepr > 55197 )
                || ascVehc < 44032 || ascVehc > 55197
                || ascCont < 44032 || ascCont > 55197 ) {

            LogFlag = "================ ENCODING ERROR ================";
           // //System.out.println(LogFlag);
            return "999";

        }*/

	    // (1) 파라미터처리
	    LogFlag = "STEP 1. Parameter Setting";
	   // //System.out.println(LogFlag);

	    request.setCharacterEncoding("UTF-8");
	    SttemntVO sttemntVO = new SttemntVO();

	    // 2019년 고도화 사업 - 국토부 모바일 앱 신고 내역 추가
        // 접수경로 구분값 설정
	    sttemntVO.setPthmode       (addressVO.getVEHC_TYPE_CD());

	    // 신고 구분은 국토부 데이터일 때만 존재함. 신고 구분 Default값 설정함. (D001:신고접수, D002:취하요청)
	    String pthCd = isEmptyValue(addressVO.getPthCd(),"D001");;

	    if("D001".equals(pthCd)) {	//신고접수

		    sttemntVO.setPTH_RG_NO     (addressVO.getPOTHOLE_REGIST_NO().replace("-", ""));
		    sttemntVO.setCRDNT_X       (addressVO.getPOS_X());
		    sttemntVO.setCRDNT_Y       (addressVO.getPOS_Y());
		    sttemntVO.setHEADG         (addressVO.getHEADING());
		    sttemntVO.setAPLCNT_TELNO  (addressVO.getREPR_TEL());
		    sttemntVO.setBSNM_NM       (addressVO.getREPR_NM());
		    sttemntVO.setVHCLE_NO      (addressVO.getREPR_VEHC_NO());
		    sttemntVO.setRCEPT_CN      (addressVO.getACCEPT_CONT());
		    sttemntVO.setVHCLE_TYPE    (addressVO.getVEHC_TYPE_CD());
		    sttemntVO.setSTTEMNT_DT    (addressVO.getREP_DTIME());

		    //System.out.println("=============== INPUT DATA ===============");
		    //System.out.println("PTH_RG_NO      : " + sttemntVO.getPTH_RG_NO());
		    //System.out.println("CRDNT_X        : " + sttemntVO.getCRDNT_X());
		    //System.out.println("CRDNT_Y        : " + sttemntVO.getCRDNT_Y());
		    //System.out.println("HEADG          : " + sttemntVO.getHEADG());
		    //System.out.println("APLCNT_TELNO   : " + sttemntVO.getAPLCNT_TELNO());
		    //System.out.println("BSNM_NM        : " + sttemntVO.getBSNM_NM());
		    //System.out.println("VHCLE_NO       : " + sttemntVO.getVHCLE_NO());
		    //System.out.println("RCEPT_CN       : " + sttemntVO.getRCEPT_CN());
		    //System.out.println("VHCLE_TYPE     : " + sttemntVO.getVHCLE_TYPE());
		    //System.out.println("STTEMNT_DT     : " + sttemntVO.getSTTEMNT_DT());

		    // (2) error 처리
		    LogFlag = "STEP 2. Error Check";
	        //System.out.println(LogFlag);

		    if ( sttemntVO.getPTH_RG_NO().length() != 14 ) {
		        //System.out.println("ERROR #1 Short of PTH_RG_NO length OR PTH_RG_NO is NULL OR Over of PTH_RG_NO length");
		        return "101";
		        //return "";
		    }

		    if ( sttemntVO.getCRDNT_X().length() == 0 || sttemntVO.getCRDNT_X() == null
		            || sttemntVO.getCRDNT_X().equals("null") ) {
		        //System.out.println("ERROR #2 Short of CRDNT_X length OR CRDNT_X is NULL");
		        return "102";
		        //return "";
		    }
		    if ( sttemntVO.getCRDNT_X().length() > 20 ) {
	            //System.out.println("ERROR #2 Over of CRDNT_X length");
	            return "102";
	            //return "";
	        }
		    if ( Double.parseDouble(sttemntVO.getCRDNT_X()) < 0 ) {
	            //System.out.println("ERROR #2 CRDNT_X is minus value");
	            return "102";
	            //return "";
	        }

		    if ( sttemntVO.getCRDNT_Y().length() == 0 || sttemntVO.getCRDNT_Y() == null
		            || sttemntVO.getCRDNT_Y().equals("null") ) {
		        //System.out.println("ERROR #3 Short of CRDNT_Y length OR CRDNT_Y is NULL");
		        return "103";
		        //return "";
		    }
		    if ( sttemntVO.getCRDNT_Y().length() > 20 ) {
		        //System.out.println("ERROR #3 Over of CRDNT_Y length");
	            return "103";
	            //return "";
	        }
		    if ( Double.parseDouble(sttemntVO.getCRDNT_Y()) < 0 ) {
	            //System.out.println("ERROR #3 CRDNT_Y is minus value");
	            return "103";
	            //return "";
	        }

	        if ( sttemntVO.getHEADG().length() > 4 ) {
	            //System.out.println("ERROR #4 Over of HEADG length");
	            return "104";
	            //return "";
	        }
		    if ( Integer.parseInt(sttemntVO.getHEADG()) < 0 ) {

		        // 음수인 경우 -가 붙으면 길이가 3 이상이 될 수 있으므로 양수로 바꿔줌
		        /*int a = Integer.parseInt(sttemntVO.getHEADG()) / 360;
		        int b = Integer.parseInt(sttemntVO.getHEADG()) % 360;

		        int headg = ( 360 * ( ( a * -1 ) + 1 ) ) + b;

		        sttemntVO.setHEADG(Integer.toString(headg));*/
		        //System.out.println("ERROR #4 HEADG is minus value");
	            return "104";
	            //return "";
		    }
		    if ( sttemntVO.getHEADG().length() > 3 ) {
	            //System.out.println("ERROR #4 Over of HEADG length");
	            return "104";
	            //return "";
	        }
		    if ( sttemntVO.getHEADG().length() == 0 || sttemntVO.getHEADG() == null
		            || sttemntVO.getHEADG().equals("null") ) {
		        //System.out.println("ERROR #4 Short of HEADG length OR HEADG is NULL");
		        return "104";
		        //return "";
		    }

		    if ( sttemntVO.getAPLCNT_TELNO().length() == 0 || sttemntVO.getAPLCNT_TELNO() == null
	                || sttemntVO.getAPLCNT_TELNO().equals("null") ) {
		        //System.out.println("ERROR #7 Short of APLCNT_TELNO length OR APLCNT_TELNO is NULL");
		        return "107";
		        //return "";
		    }
		    if ( sttemntVO.getAPLCNT_TELNO().length() > 40 ) {
		        //System.out.println("ERROR #7 Over of APLCNT_TELNO length");
		        return "107";
	            //return "";
	        }

		    if ( sttemntVO.getBSNM_NM().length() == 0 || sttemntVO.getBSNM_NM() == null
	                || sttemntVO.getBSNM_NM().equals("null") ) {
		        //System.out.println("ERROR #8 Short of BSNM_NM length OR BSNM_NM is NULL");
		        return "108";
		        //return "";
		    }
		    if ( sttemntVO.getBSNM_NM().length() > 100 ) {
		        //System.out.println("ERROR #8 Over of BSNM_NM length");
	            return "108";
	            //return "";
	        }

		    if ( sttemntVO.getVHCLE_NO().length() == 0 || sttemntVO.getVHCLE_NO() == null
	                || sttemntVO.getVHCLE_NO().equals("null") ) {
		        //System.out.println("ERROR #10 Short of VHCLE_NO length OR VHCLE_NO is NULL");
		        return "110";
		        //return "";
		    }
		    if ( sttemntVO.getVHCLE_NO().length() > 50 ) {
	            //System.out.println("ERROR #10 Over of VHCLE_NO length");
	            return "110";
	            //return "";
	        }

		    if ( sttemntVO.getRCEPT_CN().length() == 0 || sttemntVO.getRCEPT_CN() == null
	                || sttemntVO.getRCEPT_CN().equals("null") ) {
		        //System.out.println("ERROR #9 Short of RCEPT_CN length OR RCEPT_CN is NULL");
		        return "109";
		        //return "";
		    }
		    if ( sttemntVO.getRCEPT_CN().length() > 50 ) {
	            //System.out.println("ERROR #9 Over of RCEPT_CN length");
	            return "109";
	            //return "";
	        }

		    if ( sttemntVO.getVHCLE_TYPE().length() != 1 || sttemntVO.getVHCLE_TYPE() == null
		            || sttemntVO.getVHCLE_TYPE().equals("null") ) {
		        //System.out.println("ERROR #8 Short of VHCLE_TYPE length OR VHCLE_TYPE is NULL OR Over of VHCLE_TYPE length");
		        return "111";
		        //return "";
		    }

		    if ( sttemntVO.getSTTEMNT_DT().length() == 0 || sttemntVO.getSTTEMNT_DT() == null
	                || sttemntVO.getSTTEMNT_DT().equals("null") || sttemntVO.getSTTEMNT_DT().length() != 14 ) {
		        //System.out.println("ERROR #12 Short of STTEMNT_DT length OR STTEMNT_DT is NULL OR Over of STTEMNT_DT length");
		        return "112";
		        //return "";
		    }
		    if ( Integer.parseInt(sttemntVO.getSTTEMNT_DT().substring(8)) >= 240000 ) {
	            //System.out.println("ERROR #12 Wrong STTEMNT_DT time");
	            return "112";
	            //return "";
	        }

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        try {
	            sdf.parse(sttemntVO.getSTTEMNT_DT().substring(0, 8));

	        } catch(Exception e){

	            e.printStackTrace();
	            //System.out.println("ERROR #12 Wrong STTEMNT_DT date");
	            return "112";
	        }


	    } else if("D002".equals(pthCd)) {	//취하요청 (포트홀 관리번호, 신고 구분만 넘어옴.)

	    	sttemntVO.setPTH_RG_NO     (addressVO.getPOTHOLE_REGIST_NO().replace("-", ""));

	    	// (2) error 처리
		    LogFlag = "STEP 2. Error Check";
	        //System.out.println(LogFlag);

		    if ( sttemntVO.getPTH_RG_NO().length() != 14 ) {
		        //System.out.println("ERROR #1 Short of PTH_RG_NO length OR PTH_RG_NO is NULL OR Over of PTH_RG_NO length");
		        return "R002";	//필수파라미터 누락
		        //return "";
		    }

	    }


	    SttemntVO dataYn = new SttemntVO();

	    try {

	        dataYn = sttemntService.selectPotholeSttemnt(sttemntVO);

	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    if ( dataYn != null ) {

	    	if("D001".equals(pthCd)) {	//신고접수, 취하요청은 data가 존재하여야 하므로 정상진행.
		    	//System.out.println("ERROR #901 Duplicate POTHOLE_STTEMNT data");
		        return "901";
		        //return "";
	    	}
	    } else if ( dataYn == null ) {

	    	if("D002".equals(pthCd)) {	//취하요청 data가 존재하여야 하므로 null일 경우 return.
		    	//System.out.println("ERROR #901 Duplicate POTHOLE_STTEMNT data");
		        return "R004";	//등록오류
		        //return "";
	    	}
	    }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(def);

	    try {

	    	// 보정 프로시저, 보정좌표 조회 - 신고접수일 경우 진행됨.
	    	if("D001".equals(pthCd)) {

		    	// (3) 보정 프로시저
		        LogFlag = "STEP 3. CORRECTION";
		        //System.out.println(LogFlag);

	    	    addressService.procPrcPthCrdntCortn(sttemntVO);
	    	    txManager.commit(txStatus);

	    	    // (4) 보정좌표 조회
	    	    LogFlag = "STEP 4. SELECT CORRECTION X, Y";
	            //System.out.println(LogFlag);

	            //SttemntVO cortnInfo = new SttemntVO();
	            //cortnInfo = addressService.selectCortnLonLat(sttemntVO);

	            // if ( cortnInfo != null ) {

	        	    // (5) 주소변환
	                /*LogFlag = "STEP 5. CONVERT TO ADDRESS";
	                //System.out.println(LogFlag);

	                URL url = new URL("http://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + cortnInfo.getCORTN_X() + "&y="  + cortnInfo.getCORTN_Y());
	                HttpURLConnection con = (HttpURLConnection) url.openConnection();
	                con.setRequestProperty("Authorization", "KakaoAK bac7660dce49778a59e8c87e91c754bd");
	                con.setRequestMethod("GET");

	                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

	                String line;
	                String param = "";

	                while((line = in.readLine()) != null) {
	                    param += line;
	                }

	                con.disconnect();

	                // JSON 처리
	                JSONObject jo = (JSONObject) JSONValue.parseWithException(param);
	                JSONArray arr = (JSONArray) jo.get("documents");
	                String document;

	                if ( !arr.isEmpty() ) {

	                    document = arr.get(0).toString();

	                    // 도로명주소
	                    JSONObject jornm = (JSONObject) JSONValue.parseWithException(document);
	                    JSONObject roadadres = (JSONObject) jornm.get("road_address");
	                    String rnm;

	                    if ( roadadres != null ) {

	                        jornm = (JSONObject) JSONValue.parseWithException(roadadres.toString());
	                        rnm = ( jornm.get("address_name") != null ) ? jornm.get("address_name").toString() : "" ;

	                    } else {

	                        rnm = "";

	                    }

	                    sttemntVO.setRN_ADRES(rnm);

	                    // 지번주소
	                    JSONObject jolnm = (JSONObject) JSONValue.parseWithException(document);
	                    JSONObject address = (JSONObject) jolnm.get("address");
	                    String lnm;

	                    if ( !address.equals("") && address != null ) {

	                        jolnm = (JSONObject) JSONValue.parseWithException(address.toString());
	                        lnm = ( jolnm.get("address_name") != null ) ? jolnm.get("address_name").toString() : "" ;

	                    } else {

	                        lnm = "";

	                    }

	                    sttemntVO.setLNM_ADRES(lnm);

	                    // (6) 주소정보 update
	                    LogFlag = "STEP 6. UPDATE ADDRESS";
	                    //System.out.println(LogFlag);

	                    addressService.procPrcPthUdtSttmnt(sttemntVO);

	                }*/
	    	}

            // (7) SEND SMS - 신고접수, 취하요청 모두 SMS 전송됨.
            LogFlag = "STEP 7. SEND SMS";
            //System.out.println(LogFlag);

            // 전체정보조회 (문자발송 시작시간,마감시간 포함하여 가져옴.)
            sttemntVO = sttemntService.selectPotholeSttemnt(sttemntVO);

            // 담당자 연락처가 있는 경우
            if ( sttemntVO != null && sttemntVO.getCTTPC() != null ) {

            	// 2019년 고도화 사업 - 포트홀 관할 구역 담당자가 여러명이 될 수 있도록 관할_구역_담당자_정보 Table 추가됨.
        		// 1. 포트홀 신고 내역 등록 시 관할 구역의 담당자가 여러명일 경우, 해당 구역의 모든 담당자에게 문자가 전송됨.
            	// 2. 문자전송 시작시간, 마감시간 설정값에 따라 SMS가 전송되고, 마감시간 이후 SMS 전송되지 않음.
            	//    문자전송 시작시간에 미전송된 SMS를 전송처리함. (pothole>crontab>Scheduler.java)

                // sms 전송 - 문자전송 시작시간 ~ 마감시간에 전송되도록 처리

                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date stddt = format.parse("yyyy-MM-dd " + sttemntVO.getSMS_TIME_START());
                Date enddt = format.parse("yyyy-MM-dd " + sttemntVO.getSMS_TIME_END());

                if (( ((date.compareTo(stddt)) > 0) && ((date.compareTo(enddt)) < 0) )){

            		String updateSTTUS = "";

                	//1. 포트홀_관할구역(CMPTNC_ZONE)의 대표 담당자에게 전송
                	if("D001".equals(pthCd)) {	//신고접수

                		smsService.sendSMS(sttemntVO);
                		updateSTTUS = "PRCS0002";

                	} else if("D002".equals(pthCd)) {	//취하요청

                		smsService.sendDropSMS(sttemntVO);
                		updateSTTUS = "PRCS0008";
                	}

            		//2. 포트홀_관할_구역_담당자(TN_CMPTNC_ZONE_POC) : 해당 관할 구역의 모든 담당자에게 전송 추가
            		CmptncVO cmptncVO = new CmptncVO();
            		SttemntVO sttemntResult = new SttemntVO();

            		// 신고일시, 주소정보
            		sttemntResult.setSTTEMNT_DT(sttemntVO.getSTTEMNT_DT());
        			sttemntResult.setLNM_ADRES(sttemntVO.getLNM_ADRES());

            		//관할_구역_부서_코드 입력
            		//cmptncVO.setCPT_MNG_NO(sttemntVO.getCPT_MNG_NO());
            		cmptncVO.setDEPT_CODE(sttemntVO.getDEPT_CODE());

            		//관할_구역_담당자 목록 가져오기
            		List<CmptncVO> items = cmptncService.selectCmptncZonePocList(cmptncVO);

            		// 관할_구역_담당자 모두에게 문자 전송함.
            		for(int i=0; i <items.size(); i++){

            			//관할_구역_담당자 정보에서 가져온 담당자명, 연락처, 부서_명
            			sttemntResult.setCHARGER_NM(items.get(i).getCHARGER_NM());
            			sttemntResult.setCTTPC(items.get(i).getCTTPC());
            			sttemntResult.setLOWEST_DEPT_NM(items.get(i).getLOWEST_DEPT_NM());

            			if("D001".equals(pthCd)) {	//신고접수

            				smsService.sendSMS(sttemntResult);

                    	} else if("D002".equals(pthCd)) {	//취하요청

                    		smsService.sendDropSMS(sttemntResult);
                    	}
            		}

                    // Flag update
            		// update 처리 필요함.
            		if("D001".equals(pthCd)) {	//신고접수

	                    sttemntVO.setPRCS_STTUS(updateSTTUS);
	                    sttemntVO.setSMS_SNDNG_AT("Y");

            		} else if("D002".equals(pthCd)) {	//취하요청

            			sttemntVO.setPRCS_STTUS(updateSTTUS);
            		}

            		sttemntService.updatePthSttmnt(sttemntVO);
                    sttemntService.updatePotholeSttemnt(sttemntVO);

                }

            }

	       /* } else {

	            LogFlag = "================ DATA OR INSERT ERROR ================";
	            //System.out.println(LogFlag);
	            return "999";

	        }*/

	    } catch (NullPointerException e) {

	        e.printStackTrace();
	        LogFlag = "================ NullPointerException ================";
            //System.out.println(LogFlag);
	        return "997";
	        //return "";
	    } catch (SQLException e) {

	        e.printStackTrace();
	        LogFlag = "================ SqlException ================";
            //System.out.println(LogFlag);
	        return "998";
	        //return "";
	    } catch (Exception e) {

	        e.printStackTrace();
	        LogFlag = "================ Exception ================";
            //System.out.println(LogFlag);
	        return "999";
	        //return "";
	    }

	    return "000";
	    //return "";
	}

}