/**********************************************************************************
 * 파일명 : GNaverMap
 * 설 명 : 네이버 매쉬업을 하기 위한 클래스 
 * 필요 라이브러리 : OpenLayers
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2015.10.14			임상수				1.0					최초 생성
 * 
 * 참고 자료
 * --------------------------------------------------------------------------------
 * OpenLayers
 * 출처 : http://openlayers.org/
 * 
 * 
 **********************************************************************************/
GNaverMap = OpenLayers.Class({
	/**
	 * Property: id
	 * {String} 다음맵 유니크 아이디
	 */
	id : null,
	
	/**
	 * Property: projection
	 * {<OpenLayers.Projection>} 다음 맵 좌표계 기본은 EPSG:4326 좌표계를 사용
	 */
	projection : new OpenLayers.Projection("EPSG:5179"), 
	
	/**
	 * Property: oMap
	 * {<OpenLayers.Map>} OpenLayers Map 객체
	 */
	oMap : null,
	
	/**
	 * Property: oPosition
	 * {GMahsupUtil.Position} OpenLayers Map의 container 정보를 담을 GMahsupUtil.Position 객체
	 */
	oPosition : new GMahsupUtil.Position(),
	
	/**
	 * Property: position
	 * {GMahsupUtil.Position} Daum Map의 container 정보를 담을 GMahsupUtil.Position 객체
	 */
	position : new GMahsupUtil.Position(),
	
	/**
	 * Property: map
	 * {<daum.maps.Map>} OpenLayers Map 객체
	 */
	map : null,
	
	/**
	 * Property: div
	 * {DOMElement|String} The element that contains the map (or an id for
	 *     that element)
	 */
	div: null,
	
	/**
	 * Property: level
	 * {Number} 다음 맵 최대 축척
	 */
	maxLevel : 14,
	
	/**
	 * Property: zoom
	 * {Number} 다음맵과 오픈레이어스 맵의 싱크를 맞추기 위한 줌 레벨
	 */
	zoom : -1,
	
	/**
	 * Property: oMapSize
	 * {Number} 
	 */
	oMapSize : null,
	
	/**
	 * Constructor: GNaverMap
	 * Constructor for a new OpenLayers.Map instance.  There are two possible
	 *     ways to call the map constructor.  See the examples below.
	 *
	 * Parameters:
	 * div - {DOMElement|String}  The element or id of an element in your page
	 *     that will contain the map.  May be omitted if the <div> option is
	 *     provided or if you intend to call the <render> method later.
	 * options - {Object} Optional object with properties to tag onto the map.
	 *
	 * (end)
	 */
	initialize: function (div, options) {
		
		// If only one argument is provided, check if it is an object.
		if(arguments.length === 1 && typeof div === "object") {
			options = div;
			div = options && options.div;
		}
		
		OpenLayers.Util.extend(this, options);
		
		if (this.id == null) {
			this.id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
		}
		
		this.div = OpenLayers.Util.getElement(div);
		if(!this.div) {
			this.div = document.createElement("div");
			this.div.style.height = "1px";
			this.div.style.width = "1px";
		}
		
		var center = options.center;
		
		if(this.oMap.getProjection() != this.projection) {
			center.transform(this.oMap.getProjection(), this.projection);
		}
		
		var latlng = new NUTMK(center.lon, center.lat);
		
		this.map = this.createMap({center : latlng, mapMode : 0});
		
		this.oMapSize = this.oMap.getSize();
		
		this.oMap.events.register("moveend", this, function(e){
			this.syncMap(e.object);
		});
		
		this.oMap.events.register("move", this, function(e){
			this.move(e.object);
		});
	},
	
	/**
	 * APIMethod: createMap 
	 * 네이버 맵을 생성하고 div를 초기화 합니다.
	 *
	 */
	createMap : function(options) {
		var w = this.oMap.div.offsetWidth * 2;
		var h = this.oMap.div.offsetHeight * 2;
		
		this.div.style.width = w + "px";
		this.div.style.height = h + "px";
		
		var oMapW = this.oMap.div.offsetWidth;
		var oMapH = this.oMap.div.offsetHeight;
		var oMapTop = this.oMap.div.offsetTop;
		
		var left = (oMapW - w) / 2;
		var top = ((oMapH - h) / 2) + oMapTop;
		
		this.div.style.left = left + "px";
		this.div.style.top = top + "px";
		
		var naverMapOption = { 
			point: options.center
			,mapMode : options.mapMode || 0
		};
		
		var map = new NMap(this.div, naverMapOption);
		map.setCenterAndZoom(naverMapOption.point, 12 - this.oMap.getZoom());
		
		var container = this.div.children[0];
		
		this.position.left = container.offsetLeft;
		this.position.top = container.offsetTop;
		
		this.div.style.position = "fixed";
		
		return map;
	},
	
	/**
	 * APIMethod: panTo
	 * 좌표 위치로 지도를 부드럽게 이동시킨다.
	 *
	 * Parameters:
	 * latlng - {<daum.maps.LatLng>} 이동시킬 좌표 - 좌표의값은 다음에서 사용하는 LatLng 객체.
	 *
	 */
	panTo : function(latlng) {
		this.map.panTo(latlng);
	},
	
	/**
	 * APIMethod: setLevel
	 * 다음 맵 레벨을 변경한다.
	 *
	 * Parameters:
	 * zoom - {Number} 줌 레벨.
	 *
	 */
	setLevel : function(level) {
		level = this.maxLevel - level;
		this.map.setZoom(level);
	},
	
	/**
	 * APIMethod: getLevel
	 * 다음 맵 레벨을 반환한다.
	 *
	 * Parameters:
	 * zoom - {Number} 줌 레벨.
	 *
	 */
	getLevel : function() {
		return this.map.getZoom();
	},
	
	/**
	 * APIMethod: setCenter
	 * 좌표 위치로 지도가 이동한다.
	 *
	 * Parameters:
	 * latlng - {<daum.maps.LatLng>} 이동시킬 좌표 - 좌표의값은 다음에서 사용하는 LatLng 객체.
	 *
	 */
	setCenterAndZoom : function(latlon, zoom) {
		var z = (this.maxLevel - zoom < 0 ? 0 : this.maxLevel - zoom);

		this.map.setCenter(latlon);
		this.map.setZoom(z);
	},
	
	/**
	 * APIMethod: syncMap
	 * 다음 맵과 타겟맵의 위치를 맞춘다.
	 *
	 * Parameters:
	 * target - {<OpenLayers.Map>} 위치를 맞출 기준 맵 
	 *
	 */
	syncMap : function(target) {
		
		var resize = !((this.oMapSize.w == target.getSize().w) && (this.oMapSize.h == target.getSize().h));
		
		var center = target.getCenter().clone();
		
		if(target.getProjection() != this.projection) {
			center.transform(target.getProjection(), this.projection);
		}
		var latlng = new NUTMK(center.lon, center.lat);
		
		//console.log("[naver syncMap] " + latlng.x + ","+ latlng.y);
		
		if(resize) {
			if(this.div.children.length > 0) {
				for(var i = this.div.children.length - 1; i >= 0; i--) {
					this.div.removeChild(this.div.children[i]);
				}
			}
			
			this.map = this.createMap({center : latlng, mapMode : 0});
			
			this.oPosition.left = 0;
			this.oPosition.top = 0;
		} else {
			this.setCenterAndZoom(latlng, target.getZoom() + 2);
			
			var container = this.div.children[0];
			
			var left = parseInt(container.style.getPropertyValue("left"), 10);
			var top = parseInt(container.style.getPropertyValue("top"), 10);
			
			this.position.left = left;
			this.position.top = top;
			
			left = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("left"), 10);
			top = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("top"), 10);
			
			this.oPosition.left = left;
			this.oPosition.top = top;
		}
		this.oMapSize = this.oMap.getSize();
	},
	
	/**
	 * APIMethod: move
	 * 맵을 이동한다.
	 *
	 * Parameters:
	 * target - {<OpenLayers.Map>} 위치를 맞출 기준 맵 
	 *
	 */
	move : function(target) {
		var container = this.div.children[0];
		
		var left = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("left"), 10) ;
		var top = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("top"), 10) ;
		
		left = left - this.oPosition.left + this.position.left;
		top = top - this.oPosition.top + this.position.top;
		
		container.style.left = left + "px";
		container.style.top = top + "px";
		
	},
	
	/**
	 * APIMethod: setMapMode
	 * 지도의 타입을 변경한다.
	 *
	 * Parameters:
	 * type - {Number}
	 * 	1 - 일반지도
	 * 	2 - 스카이뷰
	 * 	3 - 하이브리드(스카이뷰 + 레이블)
	 */
	setMapMode : function(type) {
		this.map.setMapMode(type);
	},
	
	/**
	 * APIMethod: isVisibility
	 * 지도가 켜져있는지 확인한다.
	 *
	 * Return:
	 * {Boolean} - visible
	 */
	isVisibility : function() {
    	return (this.div.style.visibility != "hidden" ? true : false);
    },
    
	/**
	 * APIMethod: setVisibility
	 * 지도를 켜고 끈다.
	 *
	 * Parameters:
	 * visible - {Boolean}
	 */
	setVisibility : function(visible) {
		if(visible) {
			this.div.style.visibility = "visible";
		} else {
			this.div.style.visibility = "hidden";
		}
	},
	CLASS_NAME: "GNaverMap"
});
