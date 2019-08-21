/**
 * 지도 옵션 JS 파일
 */
var CONFIG = (function($, undefined){

	/**
	* 프록시 서비스 URL(POST)

	* @member {String} sPostProxyUrl
	*/
	var sPostProxyUrl 		= "/gpms/proxyPost.do";

	/**
	* 프로시 서비스 URL(GET)

	* @member {String} sPostProxyUrl
	*/
	var sGetProxyUrl 		= "/gpms/proxyGet.do";

	/**
	* 지도서비스 URL
	* @member {String} sServiceUrl
	*/
	// local server
	//var sServiceUrl 		= "http://192.168.0.205:8079/G2DataService/GService?";
	// 2018.11.02 도메인호스트 변경에 의한 변경
	var sServiceUrl        = "http://test.muhanit.kr:18079/G2DataService/GService?";
	//var sServiceUrl        = "http://www.muhanit.kr:18079/G2DataService/GService?";


	// real server
	//var sServiceUrl        = "http://105.0.111.9:8089/G2DataService/GService?";

	/**
	* 공간서버, DataHouse(=> wfs prefix로 사용될) 이름
	* @member {String} sDataHouse
	*/
	var sDataHouse 			= "dh_gpms";

	/**
	* 지도서비스 구축데이터 좌표계
	* @member {String} sDataHouseCRS
	*/
	var sDataHouseCRS			= "EPSG:5181";

	/**
	* 지도서비스 요청 좌표계
	* @member {String} sRequestCRS
	*/
	var sRequestCRS				= "EPSG:5181";

	/**
	* 지도서비스 좌표스케일 (유효소수점 자리수값)
	* @member {String}
	*/
	var sPrecision			= 3;

	/**
	* 편집모드일 경우 deactivate처리할 기본 컨트롤 id 목록
	* desc : 편집간 'SelectFeature' 컨트롤 활성화 유지를 위한 처리 - 지도기본 기능이용 시 모든컨트롤을  deactivate할 경우 feature 선택이 유지되지 않는 문제...
	* @member {String}
	*/
	var aSelectiveControls			= ["drag", "zoomOut", "zoomIn", "naivgationHistory", "measurePath", "measurePolygon"];


	/*
	* 2018.03.08. YYK. cleanMap() 의 exceptLayer 파라미터 값을 전송하는 용도
	* cleanMap() 시 지워지지 않을 레이어를 등록 후 파라미터로 전송함
	*/
	var exceptLayerList = ['GAttrLayerBase','GAttrLayer','GStatusLayerBase' ,'GStatusLayer','GTypeLayer', 'SttemntLayer', 'DmgtLayer', 'GOverlapLayer' ];


	var aLayerList = ['CELL_10','CELL_SECT']; // ,'M_CALS_T'

	// 2018.02.07 YYK 관할구역관리 레이어 추가
	//var cLayerList = ['CMPTNC_ZONE'];
	var cLayerList = ['CMPTNC_ZONE'];

	// 2018.02.07 YYK 신고관리(파손유형) 레이어 추가
	//var mLayerList = ['MV_POTHOLE_STTEMNT', 'DORO_A001'];
	//var mLayerList = ['MV_POTHOLE_STTEMNT', 'DORO_A001', 'LSMD_CONT_LDREG', 'N3A_B0010000', 'SIGUNGU' ];

    /*
     * 테마지도 _ 레이어 목록
     * */
    var tLayerList = ['MV_SRVYDTA_10', 'MV_GNLSTTUS_SECT', 'MV_THM_YEAR_10', 'MV_CELL_SECT_TYPE'];

	/**
	* GetMap서비스를 위한 설정정보 - 서비스영역/초기영역/최대해상도/서비스레벨/WMS버전/이미지요청포맷 etc
	* @member {Object} oServiceExtent
	*/
	var oGetMapInfo = {
			"serviceExtent" : [158291.5,379574.3,270901.4,526533.0],	//지도서비스 최대 영역 Obj //전국 -30000, -60000, 494288, 988576
			"initExtent" : [158291.5,379574.3,270901.4,526533.0],	//지도서비스 초기 영역 Obj
			"maxResolution" : "256",	// 2048																//지도서비스 GetMap maxResolution
			"zoomLevels" : "11",		// 14																//지도서비스 레벨 개수
			"layerOrder" : "desc",																			//지도서비스 GetMap Layer Order
			"imageFormat" : "image/jpeg",																	//지도서비스 GetMap 이미지 포맷 - image/jpeg , image/png, image/gif
			"version" : "1.3.0"																				//지도서비스 GetMap Version
	};

	/**
	* 시스템별 참조레이어(벡터)의 유효축척  - 편집 기본축척 (min:1417 max:300) 이외 영역이 넓은 레이어에 한해 유효축척 설정이 필요한 경우
	* fn_init_wfs에서 생성하는 참조레이어(vector)의 유효축척 기준이 됨
	* ★★ 서비스되고 있는 축척에서 소수점이하 값을 버린 수치로 설정해야함.
	* @member {Object} 시스템별 참조레이어의 유효축척
	*/
	var oRefWfsScaleInfo = {
		"RDL" : {
			"TEST_SAMPLE" : {
				"max" : "300",
				"min" : "2834"
			},
			"TEST_SAMPLE2" : {
				"max" : "300",
				"min" : "100000"
			}
		}
	};

	var fn_get_deactiveControls = function(){
		return aSelectiveControls;
	};


	/**
	* @description  지도서비스 좌표스케일 (유효소수점 자리수값)
	* @returns {number} 지도서비스 좌표스케일 (유효소수점 자리수값)
	*/
	var fn_get_mapPrecision = function (){
		return sPrecision;
	};

	/**
	* @description  프록시 POST 서비스URL 리턴
	* @returns {String} 프록시 POST 서비스URL
	*/
	var fn_get_postProxyUrl = function (){
		return sPostProxyUrl;
	};

	/**
	* @description  프록시 GET 서비스URL 리턴
	* @returns {String} 프록시 GET 서비스URL
	*/
	var fn_get_getProxyUrl = function (){
		return sGetProxyUrl;
	};

	/**
	* @description 지도 서비스URL 리턴
	* @returns {String} 지도서비스 URL
	*/
	var fn_get_serviceUrl = function (){
		return sServiceUrl;
	};

	/**
	* @description  DataHouse(wfs prefix) 이름 리턴
	* @returns {String} 지도서비스 DataHouse 이름
	*/
	var fn_get_dataHouseName = function (){
		return sDataHouse;
	};


	/**
	* @description  지도서비스 Extent 리턴
	* @returns {Object} 지도서비스 Extent
	*/
	var fn_get_getMapInfo = function (){
		return oGetMapInfo;
	};

	/**
	* @description  지도서비스 GetMap 기본 좌표계(CRS) 리턴
	* @returns {String} 지도서비스 GetMap 기본 좌표계(CRS)
	*/
	var fn_get_dataHouseCrs = function (){
		return sDataHouseCRS;
	};

	/**
	* @description  지도서비스 GetMap 기본 좌표계(CRS) 리턴
	* @returns {String} 지도서비스 GetMap 기본 좌표계(CRS)
	*/
	var fn_get_requestCrs = function (){
		return sRequestCRS;
	};

	/**
	* @description  지도서비스 GetMap 지도 레이어
	* @returns {String} 지도서비스 GetMap 지도 레이어
	*/
	var fn_get_layerList = function (){
		return aLayerList;
	}

	/**
	* @description  지도서비스 GetMap 지도 레이어
	* @returns {String} 지도서비스 GetMap 지도 레이어
	*/
	var fn_get_clayerList = function (){
		return cLayerList;
	}

	/**
	* @description  지도서비스 GetMap 지도 레이어
	* @returns {String} 지도서비스 GetMap 지도 레이어
	*/
	var fn_get_mlayerList = function (){
		return mLayerList;
	}

    var fn_get_exceptLayerList = function (){
        return exceptLayerList;
    }



	/**
	    * @description  지도서비스 GetMap 지도 레이어
	    * @returns {String} 지도서비스 GetMap 지도 레이어
	    */
	    var fn_get_tlayerList = function (){
	        return tLayerList;
	    }

	return{
		fn_get_serviceUrl 			: fn_get_serviceUrl,
		fn_get_dataHouseName 		: fn_get_dataHouseName,
		fn_get_dataHouseCrs 		: fn_get_dataHouseCrs,
		fn_get_requestCrs 			: fn_get_requestCrs,
		fn_get_getMapInfo			: fn_get_getMapInfo,
		fn_get_postProxyUrl			: fn_get_postProxyUrl,
		fn_get_getProxyUrl			: fn_get_getProxyUrl,
		fn_get_mapPrecision			: fn_get_mapPrecision,
		fn_get_deactiveControls		: fn_get_deactiveControls,
		fn_get_layerList 			: fn_get_layerList,
		fn_get_clayerList 			: fn_get_clayerList,
		fn_get_mlayerList 			: fn_get_mlayerList,
		fn_get_tlayerList           : fn_get_tlayerList,
		fn_get_exceptLayerList      : fn_get_exceptLayerList
	}
}(jQuery));