GIndexMap = OpenLayers.Class({
	/**
	 * 기준 지도 객체
	 */
	map : null,
	
	/**
	 * Div Container
	 */
	div : null,
	
	gindexMap : null,
	
	/**
	 * 최대 해상도
	 */
	maxResolution : null,
	
	/**********************************************************************************
	 * 함수명 : initialize (생성자 함수)
	 * 설 명 : GIndexMap 객체 생성
	 * 인 자 : map (기준 지도 객체), options (생성 옵션 들)
	 * 사용법 : initialize(map, options)
	 * 작성일 : 2011.04.25
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.04.25		최원석		최초 생성
	 * 
	 **********************************************************************************/
	initialize: function (map, options) {
		if(!options.div) {
    		this.div = document.createElement("div");
    		$(this.div).addClass("olIndexMap").css({
    			"width": "200px",
    			"height": "200px",
    			"position": "absolute",
    			"z-index": "9999",
    			"top": "86px",
    			"right": "10px",
    			"border": "1px solid #bbb",
    			"background-color": "white"
    		});
    		$(map.div).append(this.div);
    	} else {
    		this.div = document.getElementById(options.div);
    	}
		
		var lonlat;
					
		if (options && options.maxResolution) {
			this.maxResolution = options.maxResolution;
		}
		else {
			this.maxResolution = Math.min(map.getMaxExtent().getWidth(), map.getMaxExtent().getHeight()) / Math.min($(this.div).css("width").replace("px", ""), $(this.div).css("height").replace("px", ""));
		}
			
		this.gindexMap = new GMap(this.div, {
			maxExtent: options.maxExtent,
			maxResolution: this.maxResolution,
			projection: options.projection,
			controls : []
		});
		
		var layer = new GWMS(
			"GIndexLayer",
			options.serviceUrl,
			{
				layers : options.layers,
				styles : options.styles,
				CRS : "EPSG:5181"
			}
		);
		
		this.gindexMap.addLayer(layer);
		this.gindexMap.setBaseLayer(layer);
		
	    this.gindexMap.addControl(new GZoomBoxIndex(map, {id : "indexMap"}));
		this.gindexMap.activeControls("indexMap");
		
		if(options && options.offsetPixel && options.offsetPixel.CLASS_NAME == "GPixel") {
			this.gindexMap.zoomToMaxExtent();
			lonlat = this.gindexMap.getLonLatFromPixel(this.gindexMap.getPixelFromLonLat(this.gindexMap.getMaxExtent().getCenterLonLat()).add(options.offsetPixel.x, options.offsetPixel.y));
			this.gindexMap.center = lonlat;
		}
		
		map.events.register("moveend", this, function() {
			this.gindexMap.getControl("indexMap").handler.applyBox(map.getExtent());
		});
		
		this.gindexMap.zoomToMaxExtent();
	},
	
	/**********************************************************************************
	 * 함수명 : show
	 * 설 명 : 색인도 나타냄
	 * 사용법 : show()
	 * 작성일 : 2011.04.25
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.04.25		최원석		최초 생성
	 * 
	 **********************************************************************************/
	show: function() {
		$(".olIndexMap").show();
	},
	
	/**********************************************************************************
	 * 함수명 : hide
	 * 설 명 : 색인도 숨김
	 * 사용법 : hide()
	 * 작성일 : 2011.04.25
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.04.25		최원석		최초 생성
	 * 
	 **********************************************************************************/
	hide: function() {
		$(".olIndexMap").hide();
	},

	/**********************************************************************************
	 * 함수명 : toggle
	 * 설 명 : 색인도 show, hide 토글
	 * 사용법 : toggle()
	 * 작성일 : 2011.04.28
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.04.28		최원석		최초 생성
	 * 
	 **********************************************************************************/
	toggle : function() {
		if($(".olIndexMap").css("display") == "none") {
			this.show();
		}
		else {
			this.hide();
		}
	},
	
	/**********************************************************************************
	 * 함수명 : isShow
	 * 설 명 : 색인도 화면에 표시 되어 있는지 여부
	 * 사용법 : isShow()
	 * 작성일 : 2011.05.04
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.04		최원석		최초 생성
	 * 
	 **********************************************************************************/
	isShow : function() {
		if($(".olIndexMap").css("display") == "none") {
			return false;
		}
		else {
			return true;
		}
	},
	
	/**********************************************************************************
	 * 함수명 : getHeight
	 * 설 명 : 색인도의 너비 반환
	 * 사용법 : getHeight()
	 * 작성일 : 2011.05.04
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.04		최원석		최초 생성
	 * 
	 **********************************************************************************/
	getHeight : function() {
		return parseInt($(".olIndexMap").css("height").replace("px", ""));
	},
	
	/**********************************************************************************
	 * 함수명 : getWidth
	 * 설 명 : 색인도의 높이 반환
	 * 사용법 : getWidth()
	 * 작성일 : 2011.05.04
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.04		최원석		최초 생성
	 * 
	 **********************************************************************************/
	getWidth : function() {
		return parseInt($(".olIndexMap").css("width").replace("px", ""));
	},
	
	getPosition : function() {
		var result = {
			left : parseInt($(".olIndexMap").css("left").replace("px", "")),
			bottom : parseInt($(".olIndexMap").css("bottom").replace("px", "")),
			right : parseInt($(".olIndexMap").css("right").replace("px", "")),
			top : parseInt($(".olIndexMap").css("top").replace("px", ""))
		}

		return result;
	},
	
	setPosition : function(left, bottom, right, top) {
		if(left) $(".olIndexMap").css("left", left);
		if(right) $(".olIndexMap").css("right", right);
		if(bottom) $(".olIndexMap").css("bottom", bottom);
		if(top) $(".olIndexMap").css("top", top);
	},
	
	setHeight : function(height) {
		$(".olIndexMap").css("height", height);
	},
	
	setWidht : function(widht) {
		$(".olIndexMap").css("widht", widht);
	},
	
	changeLayer : function(layers, styles) {
		this.gindexMap.baseLayer.mergeNewParams({
			layers : layers,
			styles : styles
		});
	},
	
	CLASS_NAME: "GIndexMap"
});