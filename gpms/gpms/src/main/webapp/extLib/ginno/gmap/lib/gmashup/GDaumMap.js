/**********************************************************************************
 * 파일명 : GDaumMap
 * 설 명 : 다음 매쉬업을 하기 위한 클래스 
 * 필요 라이브러리 : OpenLayers
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2015.10.06			임상수				1.0					최초 생성
 * 
 * 참고 자료
 * --------------------------------------------------------------------------------
 * OpenLayers
 * 출처 : http://openlayers.org/
 * 
 * 
**********************************************************************************/
GDaumMap = OpenLayers.Class({
	/**
     * Property: id
     * {String} 다음맵 유니크 아이디
     */
	id : null,
	
	/**
     * Property: projection
     * {<OpenLayers.Projection>} 다음 맵 좌표계 기본은 EPSG:4326 좌표계를 사용
     */
	projection : new OpenLayers.Projection("EPSG:4326"), 
	
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
	maxLevel : 11,
	
	/**
     * Property: overlayLayerType
     * {Number} 지도에 중첩된 레이어 타입
     */
	overlayLayerType : -1,
	
	/**
     * Property: roadView
     * {<GDaumMap.RoadView>} GDaumMap.RoadView 객체
     */
	roadView : null,
	
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
     * Constructor: GDaumMap
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
        
        if(this.oMap.getProjection() != this.projection) {
        	options.center.transform(this.oMap.getProjection(), this.projection);
        }
        
        var daumMapOption = {
        		center: new daum.maps.LatLng(options.center.lat, options.center.lon),
        		level: this.maxLevel - this.oMap.getZoom()  // 지도의 확대 레벨
        };
    	
    	this.map = this.createMap(daumMapOption);
    	
    	this.oMapSize = this.oMap.getSize();
    	
    	this.oMap.events.register("moveend", this, function(e){
    		this.syncMap(e.object);
    	});
    	
    	this.oMap.events.register("move", this, function(e){
    		 this.move(e.object);
    	});
    	
    	this.oMap.events.register("zoomend", this, function(e){
    		this.syncMap(e.object);
    	});
    	
    	this.oMap.events.register("updatesize", this, function(e){
    		this.syncMap(e.object);
    	});
    },
    
    /**
     * APIMethod: craeteMap
     * 다음 맵을 초기화하고 생성한다.
     *
     * Parameters:
     * options - 
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
         
     	// 다음맵을 생성합니다.
     	var map = new daum.maps.Map(this.div, options);
     	
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
    setRoadView : function(roadView) {
    	this.roadView = roadView;
    	
    	if(roadView) {
    		this.roadView.setMap(this);
    	}
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
    	this.map.setLevel(level);
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
    	return this.map.getLevel();
    },
    
    /**
     * APIMethod: setCenter
     * 좌표 위치로 지도가 이동한다.
     *
     * Parameters:
     * latlng - {<daum.maps.LatLng>} 이동시킬 좌표 - 좌표의값은 다음에서 사용하는 LatLng 객체.
     *
     */
    setCenter : function(latlon) {
    	this.map.setCenter(latlon);
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
		
		var latlng = new daum.maps.LatLng(center.lat, center.lon);
		
		if(resize) {
			if(this.div.children.length > 0) {
				for(var i = this.div.children.length - 1; i >= 0; i--) {
					this.div.removeChild(this.div.children[i]);
				}
			}
			this.map = this.createMap({center : latlng, level: this.maxLevel - this.oMap.getZoom() });
		} else {
			this.setCenter(latlng);
			this.setLevel(target.getZoom());
			
	    	var container = this.div.children[0].firstChild;
	    	
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
    			
    	var container = this.div.children[0].firstChild;
    	
    	var left = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("left"), 10);
    	var top = parseInt(target.viewPortDiv.firstChild.style.getPropertyValue("top"), 10);
    	
    	left = left - this.oPosition.left + this.position.left;
    	top = top - this.oPosition.top + this.position.top;
    	

    	//var center = target.getCenter().clone();
    	//console.log("[move] " + center.lon + "," + center.lat + ":"+ left + ","+top);
    	
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
		this.map.setMapTypeId(type);
    },
    
    /**
     * APIMethod: addOverlayMap
     * 지도에 레이어를 중첩한다.
     *
     * Parameters:
     * type - {Number}
     * 	5 - 로드뷰
     * 	6 - 지형정보
     * 	7 - 교통정보
     */
    addOverlayLayer : function(type) {
    	this.overlayLayerType = type;
    	this.map.addOverlayMapTypeId(type);
    },
    
    /**
     * APIMethod: removeOverlayLayer
     * 중첩된 레이어를 제거한다.
     *
     * Parameters:
     * type - {Number}
     * 	5 - 로드뷰
     * 	6 - 지형정보
     * 	7 - 교통정보
     */
    removeOverlayLayer : function(type) {
    	this.overlayLayerType = -1;
    	this.map.removeOverlayMapTypeId(type);
    },
    
    /**
     * APIMethod: addOverlayMap
     * 지도에 중천됩 레이어를 반환한다.
     */
    getOverlayLayerType : function() {
    	return this.overlayLayerType;
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
    
    startRoadView : function() {
    	// 로드뷰를 중첩
    	this.addOverlayLayer(5);
    	this.roadView.run();
    	return true;
    },
    
    endRoadView : function(e) {
    	// 로드뷰 중첩 해지
    	this.removeOverlayLayer(5);
    	this.roadView.stop();
    	return true;
    },
    
    /**
     * APIMethod: setRoadViewVisibility
     * 로드뷰를 켜고 끈다.
     *
     * Parameters:
     * visible - {Boolean}
     */
    setRoadViewVisibility : function(visible) {
    	this.roadView.setVisibility(visible);
    },
    
    getStaticMap : function(div) {
    	var staticMapOption = {
    		center : this.map.getCenter(),
    		level : this.getLevel()
    	};
    	return new daum.maps.StaticMap(div, staticMapOption);
    },
    CLASS_NAME: "GDaumMap"
});


GDaumMap.RoadView = OpenLayers.Class({
	/**
     * Property: id
     * {String} 다음맵 유니크 아이디
     */
	id : null,
	
	/**
     * Property: div
     * {DOMElement|String} The element that contains the map (or an id for
     *     that element)
     */
	div : null,
	
	/**
     * Property: map
     * {DOMElement|String} The element that contains the map (or an id for
     *     that element)
     */
	map : null,
	
	/**
     * Property: position
     * {<daum.maps.LatLng>} 로드뷰를 실행하고 맵을 클릭했을때 저장할 위치값
     */
	position : null,
	
	/**
     * Property: position
     * {<OpenLayers.Layer.Vector>} 로드뷰에 동동이를 추가하기 위한 임시 레이어
     */
	layer : null,
	
	/**
     * Property: mapWalker
     * {<GDaumMap.RoadView.MapWalker>} 로드맵 Walker
     */
	mapWalker : null,
	
	 /**
     * Constructor: GDaumMap.RoadView
     * 다음 로드뷰 클래스
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
		
		this.div = OpenLayers.Util.getElement(div); // 로드뷰를 표시할 div
		
		if(!this.div) {
			return;
		}
		
		this.roadView = new daum.maps.Roadview(this.div); // 로드뷰 객체
		this.roadViewClient = new daum.maps.RoadviewClient(); // 좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
		
		daum.maps.event.addListener(this.roadView, 'init', this.roadViewInit.bind(this));
		daum.maps.event.addListener(this.roadView, 'viewpoint_changed', this.roadViewPointChanged.bind(this));
		daum.maps.event.addListener(this.roadView, 'position_changed', this.positionChanged.bind(this));
	},
	
	/**
     * APIMethod: roadViewInit
     * 로드뷰가 초기화 될때 발생하는 이벤트
     *
     */
	roadViewInit : function() {
		this.mapWalker = new GDaumMap.RoadView.MapWalker(this.position);
	    this.mapWalker.setMap(this.map.map);
	},
	
	/**
     * APIMethod: roadViewPointChanged
     * 로드뷰의 시야가 변경되면 발생한다.
     *
     */
	roadViewPointChanged : function() {
		var viewpoint = this.roadView.getViewpoint();
		this.mapWalker.setAngle(viewpoint.pan);
	},
	
	/**
     * APIMethod: positionChanged
     * 로드뷰의 위치가 변경되면 반영한다.
     *
     */
	positionChanged : function() {
		var position = this.roadView.getPosition();
        this.mapWalker.setPosition(position);
	},
	
	/**
     * APIMethod: setMap
     * 로드뷰에서 사용할 맵을 지정한다.
     *
     * Parameters:
     * map - {<GDaumMap>}
     */
	setMap : function(map) {
		this.map = map;
	},
	
	/**
     * APIMethod: run
     * 로드뷰를 실행한다.
     *
     * Parameters:
     * map - {<GDaumMap>}
     */
	run : function() {
		this.layer = new OpenLayers.Layer.Vector("roadView Vector Layer");
		this.layer.events.register("nofeatureclick", this, this.click);
		this.map.oMap.addLayer(this.layer);
		
		this.div.style.display = "block";
	},
	
	/**
     * APIMethod: click
     * 클릭이벤트
     *
     * Parameters:
     * e - {<OpenLayers.ClickEvents>}
     */
	click : function(e) {
		var pixcel = new OpenLayers.Pixel(window.event.clientX, (window.event.clientY - this.map.oMap.div.offsetTop));
		var lonlat = this.map.oMap.getLonLatFromPixel(pixcel);
		
		if(this.map.oMap.getProjection() != this.map.projection) {
			lonlat.transform(this.map.oMap.getProjection(), this.map.projection);
		}
		
		this.position = new daum.maps.LatLng(lonlat.lat , lonlat.lon);
		
		this.roadViewClient.getNearestPanoId(this.position, 50, this.nearestPanoIdcallback.bind(this));
	},
	
	/**
	 * APIMethod: nearestPanoIdcallback
	 * 클릭한 지점의 가장 근접한 파노라마 아이디를 가져온다.
	 *
	 * Parameters:
	 * panoId - {Number} 파노라마 아이디
	 */
	nearestPanoIdcallback : function(panoId) {
		this.roadView.setPanoId(panoId, this.position);
	},
	
	/**
	 * APIMethod: stop
	 * 로드뷰를 중지한다.
	 *
	 * Parameters:
	 * map - {<GDaumMap>}
	 */
	stop : function() {
		if(this.layer) {
			this.map.oMap.removeLayer(this.layer);
		}
		
		if(this.mapWalker !== null) {
			this.mapWalker.destory();
		}
		
		this.layer = null;
		this.div.style.display = "none";
	},
	
	setVisibility : function(visible) {
		if(visible) {
    		this.div.style.visibility = "visible";
    	} else {
    		this.div.style.visibility = "hidden";
    	}
	},
	
	/* 통합정보조회 - 포장상태 조사정보 로드뷰 ...JOY */
	getRoadViewByItgrtn : function(x, y) {
	    
	    if ( x == undefined || y == undefined
	            || x == "" || y == "" || x == null || y == null ) {
	        return;
	        
	    } else {
	        
	        var roadviewContainer = document.getElementById("roadView");
	        var roadview = new parent.daum.maps.Roadview(roadviewContainer);
	        var roadviewClient = new parent.daum.maps.RoadviewClient();
	        
	        var center = parent.gMap.getCenter().clone();
	        center.lat = y;
	        center.lon = x;
	        center.transform(parent.gMap.getProjection(), parent.daumMap.projection);
	        
	        var position = new parent.daum.maps.LatLng(center.lat, center.lon);
	        
	        roadviewClient.getNearestPanoId(position, 50, function(panoId) {
	           roadview.setPanoId(panoId, position);
	        });
	        
	    }
	    
	},
	
	CLASS_NAME: "GDaumMap.RoadView"
});

GDaumMap.RoadView.MapWalker = OpenLayers.Class({
	
	/**
     * Property: walker
     * {<daum.maps.CustomOverlay>} 다음 커스텀 오버레이 객체
     */
	walker : null,
	
	/**
     * Property: div
     * {DOMElement|String} The element that contains the map (or an id for
     *     that element)
     */
	div : null,
	
	/**
     * Constructor: GDaumMap.RoadView.MapWalker
     * 다음에서 제공하는 동동이를 사용하는 클래스
     * 동동이란? 로드뷰를 사용할때 시야각을 표현해주는 이미지 아이콘
     *
     * Parameters:
     * position - {<daum.maps.LatLng>} 동동이를 생성할 좌표
     *
     * (end)
     */
	initialize : function(position) {
		
	    this.div = document.createElement('div');
	    var figure = document.createElement('div');
	    var angleBack = document.createElement('div');

	    this.div.className = 'MapWalker';
	    figure.className = 'figure';
	    angleBack.className = 'angleBack';

	    this.div.appendChild(angleBack);
	    this.div.appendChild(figure);

	    this.walker = new daum.maps.CustomOverlay({
	        position: position,
	        content: this.div,
	        yAnchor: 1
	    });
	},
	
	destory : function() {
		this.walker = null;
		if(this.div.parentNode)
			this.div.parentNode.removeChild(this.div);
	},
	
	/**
     * APIMethod: setAngle
     * 앵글을 계산한다.
     *
     * Parameters:
     * angle - {Float}
     */
	setAngle : function(angle) {
		var threshold = 22.5; 
		
	    for(var i = 0; i < 16; i++){ 
	        if(angle > (threshold * i) && angle < (threshold * (i + 1))){
	        	
	            var className = 'm' + i;
	            this.div.className = this.div.className.split(' ')[0];
	            this.div.className += (' ' + className);
	            
	            break;
	        }
	    }
	},
	
	/**
     * APIMethod: setPosition
     * 동동이의 위치를 변경한다.
     *
     * Parameters:
     * position - {<daum.maps.LatLng>}
     */
	setPosition : function(position) {
		this.walker.setPosition(position);
	},
	
	/**
     * APIMethod: setMap
     * 동동이를 띄울 타겟맵을 지정한다.
     *
     * Parameters:
     * map - {<daum.maps.LatLng>}
     */
	setMap : function(map) {
		this.walker.setMap(map);
	},
	
	CLASS_NAME: "GDaumMap.RoadView.MapWalker"
});
