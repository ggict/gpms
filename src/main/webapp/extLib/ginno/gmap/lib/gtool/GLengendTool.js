/**********************************************************************************
 * 파일명 : GLengendTool.js
 * 설 명 : 범례 구성을 위한 툴 개념의 클래스
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2011.05.19		최원석				0.1					최초 생성
 * 
 * 
**********************************************************************************/
GLengendTool = OpenLayers.Class({
	/**
	 * 지도 객체
	 */
	map : null,
	
	/**
	 * 레이어 명
	 */
	layers : null,
	
	/**
	 * 콜백 함수
	 */
	callback : null,
	
	/**
	 * sld 객체
	 */
	 sld : {},
	
	/**
	 * 전체 범례/현재 범례 여부
	 */
	allList : false,
	
	/**********************************************************************************
	 * 함수명 : initialize (생성자 함수)
	 * 설 명 : GLengendTool 객체 생성
	 * 인 자 : map (지도 객체), layerName (WMS 레이어 명), layers (호출할 레이어 목록), callback (요청 처리 후 실행될 함수)
	 * 사용법 : initialize(map, layerName, callback)
	 * 작성일 : 2011.05.19
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.04.18		최원석		최초생성
	 * 
	 **********************************************************************************/
	initialize : function(map, layerName, layers, callback) {
		this.map = map;
		
		if(layerName) {
			this.layer = this.map.getLayerByName(layerName);
		}
		else {
			this.layer = this.map.baseLayer;
		}
		
		var layerList = [];
		if(layers) {
			layerList = layers;
		}
		else { 
			layerList = this.layer.params.LAYERS;
		}

		this.callback = callback;
		
		var control = this;
		
		GRequest.WMS.getStyles(this.layer.url, layerList + ",", function(res) {
			control.sld = res;
			control.parseStyle();
		});
		
		
		this.map.events.register("moveend", this, function(){
			//this.parseStyle();
		});
	},
	
	/**********************************************************************************
	 * 함수명 : parseStyle
	 * 설 명 : XML 을 Parsing 해서 json Object 를 생성
	 * 사용법 : parseStyle()
	 * 
	 * 작성일 : 2011.05.19
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.19		최원석		최초 생성
	 * 2011.06.29		최원석		새로운 Viewer 맵 정의에 맞게 수정
	 * 								
	 **********************************************************************************/
	parseStyle : function() {
		var arr = [];
		var curLayers = {};
		if(!this.allList) {
			//var pLayers = this.layer.params.LAYERS.split(",");
			var pLayers = this.layer.params.LAYERS;
			for(var i=0, len=pLayers.length; i < len; i++) {
				curLayers[pLayers[i]] = true;
			}
		}
		
		var namedLayers = this.sld.namedLayers;
		for(var i in namedLayers) {
			var userStyles = namedLayers[i].userStyle;
			var ruleObj = [];
			
			for(var j in userStyles) {
				var rules = userStyles[j].rules;
				var scale = parseInt(this.map.getScale());
				var maxScale = 0;
				var minScale = 0;
				var count = 0;
				
				for(var k in rules) {
					if(typeof rules[k].symbolizer == "undefined" || rules[k].symbolizer == null) continue;
					
					maxScale = rules[k].maxScale;
					minScale = rules[k].minScale;
					
					if(!this.allList) {
						if(maxScale == 0) {
							maxScale = parseInt(OpenLayers.Util.getScaleFromResolution(this.map.getResolutionForZoom(0), this.map.units));
						}	
					}
					ruleObj.push(rules[k]);	
				}
				
				if((curLayers[namedLayers[i].name] && maxScale >= scale && scale >= minScale) || this.allList) {
					
					var lengendObj = {
						layer : namedLayers[i].name,
						style : userStyles[j].name,
						rule : ruleObj,
					};
					
					arr.push(lengendObj);
				
				}
			}
		}

		this.callback(arr);
	},
	
	/**********************************************************************************
	 * 함수명 : showFullList
	 * 설 명 : 전체 범례를 반환
	 * 사용법 : showFullList()
	 * 작성일 : 2011.05.19
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.19		최원석		최초 생성
	 * 
	 **********************************************************************************/
	showFullList: function() {
		this.allList = true;	
		this.parseStyle();	
	},
	
	/**********************************************************************************
	 * 함수명 : showCurrentList
	 * 설 명 : 현재 범례를 반환
	 * 사용법 : showCurrentList()
	 * 작성일 : 2011.05.19
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.19		최원석		최초 생성
	 * 
	 **********************************************************************************/
	showCurrentList: function() {
		this.allList = false;
		this.parseStyle();
	},
	
	CLASS_NAME: "GLengendTool"
});
	