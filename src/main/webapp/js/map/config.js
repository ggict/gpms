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
	 * 프록시 서비스 URL(GET)
	 * @member {String} sPostProxyUrl
	 */
	var sGetProxyUrl 		= "/gpms/proxyGet.do";
	
	/**
	 * 프록시 서비스 URL
	 * @member {String} geoProxyUrl
	 */
	var geoProxyUrl 		= 'geoProxyPost.do?';
	
	/**
	* 공간서버, DataHouse(=> wfs prefix로 사용될) 이름
	* @member {String} sDataHouse
	*/
	//var sDataHouse 			= "dh_gpms";
	var sDataHouse 			= "gpms";
	
	/**
	* 지도서비스 URL
	* @member {String} sServiceUrl
	*/
	// local server
	//var sServiceUrl 		= "http://192.168.0.205:8079/G2DataService/GService?";
	// 2018.11.02 도메인호스트 변경에 의한 변경
	//var sServiceUrl        = "http://192.168.0.206:18080/geoserver/wms?";
	//var wfsServiceUrl        = "http://192.168.0.206:18080/geoserver/wfs?";
	//var sServiceUrl        = "http://127.0.0.1:18080/geoserver/"+sDataHouse+"/wms?";
	//var sServiceUrl        = "http://www.muhanit.kr:18079/G2DataService/GService?";
	//var sServiceUrl        = "http://192.168.0.206:18080/geoserver/"+sDataHouse+"/wms?";

	/*var sServiceUrl        = "http://192.168.0.206:18080/geoserver/wms?";
	var wfsServiceUrl        = "http://192.168.0.206:18080/geoserver/wfs?";*/
 
	var sServiceUrl        = "http://1.221.39.242:21525/geoserver/wms?";
	var wfsServiceUrl        = "http://1.221.39.242:21525/geoserver/wfs?";

	
	// real server
	//var sServiceUrl        = "http://105.0.111.9:8089/G2DataService/GService?";

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


	//var aLayerList = ['CELL_10','CELL_SECT']; // ,'M_CALS_T'
	//var aLayerList = ['CELL_10','CELL_SECT','M_CALS_T'];
	var aLayerList = ['CELL_10','CELL_SECT','M_CALS_T','CELL_10_4K','CELL_10_0301'];

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
			//"version" : "1.3.0"																				//지도서비스 GetMap Version
			"version" : "1.1.0"
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
	
	
	var serviceLayerInfo = {

	     "CELL_10" : {
	         
	         'tmapid' : '870',
	         'id' : '3764',
	         'table' : 'CELL_10',
	         'theme' : 'CELL_10',
	         'alias' : 'CELL_10',
	         'seq' : '3764',
	         'show' : '1',
	         'layerType' : '3',
	     },
	 
	     "CELL_SECT" : {
	         
	         'tmapid' : '870',
	         'id' : '3765',
	         'table' : 'CELL_SECT',
	         'theme' : 'CELL_SECT',
	         'alias' : 'CELL_SECT',
	         'seq' : '3765',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     /* 
	     "CELL_200" : {
	    	 
	    	 'tmapid' : '870',
	    	 'id' : '3800',
	    	 'table' : 'CELL_200',
	    	 'theme' : 'CELL_200',
	    	 'alias' : 'CELL_200',
	    	 'seq' : '3800',
	    	 'show' : '1',
	    	 'layerType' : '3',
	     },
	     */
	     
	     "CELL_10_0301" : {
	    	 
	    	 'tmapid' : '870',
	    	 'id' : '3801',
	    	 'table' : 'CELL_10_0301',
	    	 'theme' : 'CELL_10_0301',
	    	 'alias' : 'CELL_10_0301',
	    	 'seq' : '3801',
	    	 'show' : '1',
	    	 'layerType' : '3',
	     },
	     
	     "CELL_10_4K" : {
	    	 
	    	 'tmapid' : '870',
	    	 'id' : '3802',
	    	 'table' : 'CELL_10_4K',
	    	 'theme' : 'CELL_10_4K',
	    	 'alias' : 'CELL_10_4K',
	    	 'seq' : '3802',
	    	 'show' : '1',
	    	 'layerType' : '3',
	     },

	     "MV_CELL_SECT_TYPE" : {
	         
	         'tmapid' : '870',
	         'id' : '3768',
	         'table' : 'MV_CELL_SECT_TYPE',
	         'theme' : 'MV_CELL_SECT_TYPE',
	         'alias' : 'Section셀_종류',
	         'seq' : '3768',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "MV_GNLSTTUS_SECT_2017" : {
	         
	         'tmapid' : '870',
	         'id' : '3761',
	         'table' : 'MV_GNLSTTUS_SECT_2017',
	         'theme' : 'MV_GNLSTTUS_SECT_2017',
	         'alias' : 'Section셀_포장상태_2017',
	         'seq' : '3761',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "MV_GNLSTTUS_SECT_2018" : {
	         
	         'tmapid' : '870',
	         'id' : '3762',
	         'table' : 'MV_GNLSTTUS_SECT_2018',
	         'theme' : 'MV_GNLSTTUS_SECT_2018',
	         'alias' : 'Section셀_포장상태_2018',
	         'seq' : '3762',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "MV_SRVYDTA_10_2017" : {
	         
	         'tmapid' : '870',
	         'id' : '3758',
	         'table' : 'MV_SRVYDTA_10_2017',
	         'theme' : 'MV_SRVYDTA_10_2017',
	         'alias' : '10m셀_포장상태_2017',
	         'seq' : '3758',
	         'show' : '1',
	         'layerType' : '3',
	     },
	 
	     "MV_SRVYDTA_10_2018" : {
	         
	         'tmapid' : '870',
	         'id' : '3759',
	         'table' : 'MV_SRVYDTA_10_2018',
	         'theme' : 'MV_SRVYDTA_10_2018',
	         'alias' : '10m셀_포장상태_2018',
	         'seq' : '3759',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "MV_THM_YEAR_10" : {
	         
	         'tmapid' : '870',
	         'id' : '3767',
	         'table' : 'MV_THM_YEAR_10',
	         'theme' : 'MV_THM_YEAR_10',
	         'alias' : '10m셀_테마지도',
	         'seq' : '3767',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "SIGUNGU" : {
	         
	         'tmapid' : '870',
	         'id' : '3753',
	         'table' : 'SIGUNGU',
	         'theme' : 'SIGUNGU',
	         'alias' : '시군구',
	         'seq' : '3753',
	         'show' : '1',
	         'layerType' : '3',
	     },
	 
	     "DORO_A001" : {
	         
	         'tmapid' : '870',
	         'id' : '3754',
	         'table' : 'DORO_A001',
	         'theme' : 'DORO_A001',
	         'alias' : '도로면',
	         'seq' : '3754',
	         'show' : '1',
	         'layerType' : '3',
	     },
	 
	     "N3A_B0010000" : {
	         
	         'tmapid' : '870',
	         'id' : '3755',
	         'table' : 'N3A_B0010000',
	         'theme' : 'N3A_B0010000',
	         'alias' : '건물',
	         'seq' : '3755',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     "M_CALS_T" : {
	         
	         'tmapid' : '870',
	         'id' : '3775',
	         'table' : 'M_CALS_T',
	         'theme' : 'M_CALS_T',
	         'alias' : '교량',
	         'seq' : '3775',
	         'show' : '1',
	         'layerType' : '3',
	     },
	     
	     /*
	     "CMPTNC_ZONE" : {
	         
	         'tmapid' : '870',
	         'id' : '3769',
	         'table' : 'CMPTNC_ZONE',
	         'theme' : 'CMPTNC_ZONE',
	         'alias' : '포트홀_관할_구역',
	         'seq' : '3769',
	         'show' : '1',
	         'layerType' : '3',
	     },
	 
	     "MV_POTHOLE_STTEMNT" : {
	         
	         'tmapid' : '870',
	         'id' : '3774',
	         'table' : 'MV_POTHOLE_STTEMNT',
	         'theme' : 'MV_POTHOLE_STTEMNT',
	         'alias' : '포트홀_신고_관리',
	         'seq' : '3774',
	         'show' : '1',
	         'layerType' : '3',
	     },
	      */
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
	
	var fn_get_geoProxyUrl = function (){
		return contextPath + geoProxyUrl;
	};

	/**
	* @description 지도 서비스URL 리턴
	* @returns {String} 지도서비스 URL
	*/
	var fn_get_serviceUrl = function (){
		return sServiceUrl;
	};
	
	var fn_get_wfsServiceUrl = function (){
		return wfsServiceUrl;
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
    
    /**
     * @description  지도서비스 GetMap 지도 레이어
     * @returns {String} 지도서비스 GetMap 지도 레이어
     */
    var fn_get_tlayerList = function (){
    	return tLayerList;
    }
    
    /**
     * @description  지도서비스 레이어 목록
     * @returns {Object} 레이어 목록
     */    
    var fn_get_serviceLayerInfo = function (){
    	return serviceLayerInfo;
    }
    
    
    /**
     * @description 네임스페이스에 레이어 명칭을 합친다
     * @return {String} 네임스페이스:레이어 명칭
     * */
    var fn_prefixAppandName = function(name){
    	if(!name) return '';
		return sDataHouse + ':' + name;
	};
	
	
    /**
     * @description 레이어의 네임스페이스 url 생성
     * @return {String} 네임스페이스 url
     * */
	var fn_getNamespace = function(prefix){
		var _prefix = (prefix) ? prefix :sDataHouse;
		var namespace = 'xmlns:'+_prefix+'="http://www.openplans.org/'+_prefix;
		return namespace;
	}
	
    

	return{
		fn_get_serviceUrl 			: fn_get_serviceUrl,
		fn_get_wfsServiceUrl 		: fn_get_wfsServiceUrl,
		fn_get_dataHouseName 		: fn_get_dataHouseName,
		fn_get_dataHouseCrs 		: fn_get_dataHouseCrs,
		fn_get_requestCrs 			: fn_get_requestCrs,
		fn_get_getMapInfo			: fn_get_getMapInfo,
		fn_get_postProxyUrl			: fn_get_postProxyUrl,
		fn_get_getProxyUrl			: fn_get_getProxyUrl,
		fn_get_geoProxyUrl			: fn_get_geoProxyUrl,
		fn_get_mapPrecision			: fn_get_mapPrecision,
		fn_get_deactiveControls		: fn_get_deactiveControls,
		fn_get_layerList 			: fn_get_layerList,
		fn_get_clayerList 			: fn_get_clayerList,
		fn_get_mlayerList 			: fn_get_mlayerList,
		fn_get_tlayerList           : fn_get_tlayerList,
		fn_get_exceptLayerList      : fn_get_exceptLayerList,
		fn_get_serviceLayerInfo		: fn_get_serviceLayerInfo,
		fn_prefixAppandName			: fn_prefixAppandName,
		fn_getNamespace				: fn_getNamespace
	}
}(jQuery));