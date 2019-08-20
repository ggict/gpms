GPanZoomBar = OpenLayers.Class(OpenLayers.Control.PanZoomBar, {
	/**
	 * 툴바 이미지 정보
	 */
	imgUrl : "/images/GMap/PanZoomBar/",

	/**
	 * 축척 확대 툴바
	 */
	imgZoomIn : "scale_plus.png",
	
	/**
	 * 축척 축소 툴바
	 */
	imgZoomOut : "scale_minus.png",
	
	/**
	 * 현재 축척바 위쪽 표현 이미지
	 */
	imgZoomBasic : "scale_basic.png",
	
	/**
	 * 현재 축척바 아래쪽 표현 이미지
	 */
	imgZoomBarOn : "scale_on.png",
	
	/**
	 * 현재 축척바
	 */
	imgZoomBar : "scale_bar.png",
	
	/**
	 * 축척 확대/축소 이미지 사이즈
	 */
	size : new GSize(25, 20),
	
	/**
	 * 축척바 사이즈
	 */
	zoombarSize : new GSize(27,7),
	
	/**
	 * 정렬 기준 
	 */
	flow : "right",
	
	/**
	 *	위치 
	 */
	offsetPixel : new GPixel(50, 0),
	
	draw: function(px) {
        OpenLayers.Control.prototype.draw.apply(this, arguments);

		this.moveToZoomBar();
		
		px = this.position.clone();
		
        // place the controls
        this.buttons = [];

		var sz = this.size;
        var centered = new OpenLayers.Pixel(px.x+sz.w/2, px.y);
		
		/**
		 * 필요 없는 버튼 제거
		 */
        this._addButton("zoomin", this.imgUrl + this.imgZoomIn, centered.add(0, sz.h*3+5), sz);
        centered = this._addZoomBar(centered.add(0, sz.h*4 + 5));
        this._addButton("zoomout", this.imgUrl + this.imgZoomOut, centered, sz);
		
        return this.div;
    },
	
	moveToZoomBar : function() {
		/**
		 * 우측 정렬일 경우
		 */
		if(this.flow == "right") {
			this.offsetPixel.x = $("#map").width() - this.offsetPixel.x;
		}		
		this.moveTo(this.offsetPixel);
	},
	
	_addZoomBar:function(centered) {
		/**
		 * 전체적으로 이미지 경로 수정
		 */
        var imgLocation = this.imgUrl;
        
        var id = this.id + "_" + this.map.id;
        var zoomsToEnd = this.map.getNumZoomLevels() - 1 - this.map.getZoom();
        var slider = OpenLayers.Util.createAlphaImageDiv(id,
                       centered.add(-1, zoomsToEnd * this.zoomStopHeight), 
                       new OpenLayers.Size(27,7),
                       imgLocation+this.imgZoomBar,
                       "absolute");
        this.slider = slider;
        
        this.sliderEvents = new OpenLayers.Events(this, slider, null, true,
                                            {includeXY: true});
        this.sliderEvents.on({
            "mousedown": this.zoomBarDown,
            "mousemove": this.zoomBarDrag,
            "mouseup": this.zoomBarUp,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });
        
        var sz = new OpenLayers.Size();
        sz.h = this.zoomStopHeight * this.map.getNumZoomLevels();
        sz.w = this.zoomStopWidth;
        var div = null;
        
        if (OpenLayers.Util.alphaHack()) {
            var id = this.id + "_" + this.map.id;
            div = OpenLayers.Util.createAlphaImageDiv(id, centered,
                                      new OpenLayers.Size(sz.w, 
                                              this.zoomStopHeight),
                                      imgLocation + this.imgZoomBarOn, 
                                      "absolute", null, "crop");
            div.style.height = sz.h + "px";
        } else {
            div = OpenLayers.Util.createDiv(
                        'OpenLayers_Control_PanZoomBar_Zoombar' + this.map.id,
                        centered,
                        sz,
                        imgLocation+this.imgZoomBarOn);
        }
		
        this.zoombarDiv = div;
        
        this.divEvents = new OpenLayers.Events(this, div, null, true, 
                                                {includeXY: true});
												
        this.divEvents.on({
            "mousedown": this.divClick,
            "mousemove": this.passEventToSlider,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });
        
        this.div.appendChild(div);
		
		/**
		 * 축척바 위쪽 표현 DIV 추가
		 */
		this.zoomBasicDiv = OpenLayers.Util.createDiv(
                        'OpenLayers_Control_PanZoomBar_ZoombarBasic' + this.map.id,
                        centered,
                        new OpenLayers.Size(sz.w, this.zoomStopHeight * (this.map.getNumZoomLevels() - this.map.getZoom() - 1)),
                        imgLocation+this.imgZoomBasic);
						
		this.divEvents = new OpenLayers.Events(this, this.zoomBasicDiv, null, true, 
                                                {includeXY: true});
												
        this.divEvents.on({
            "mousedown": this.divClick,
            "mousemove": this.passEventToSlider,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });				
		
		this.div.appendChild(this.zoomBasicDiv);

        this.startTop = parseInt(div.style.top);
        this.div.appendChild(slider);

        this.map.events.register("zoomend", this, this.moveZoomBar);

        centered = centered.add(0, 
            this.zoomStopHeight * this.map.getNumZoomLevels());
        return centered; 
    },
	
	zoomBarUp:function(evt) {
        if (!OpenLayers.Event.isLeftClick(evt)) {
            return;
        }
        if (this.mouseDragStart) {
            this.div.style.cursor="";
            this.map.events.un({
                "mouseup": this.passEventToSlider,
                "mousemove": this.passEventToSlider,
                scope: this
            });
            var deltaY = this.zoomStart.y - evt.xy.y;
            var zoomLevel = this.map.zoom;
            if (!this.forceFixedZoomLevel && this.map.fractionalZoom) {
                zoomLevel += deltaY/this.zoomStopHeight;
                zoomLevel = Math.min(Math.max(zoomLevel, 0), 
                                     this.map.getNumZoomLevels() - 1);
            } else {
                zoomLevel += Math.round(deltaY/this.zoomStopHeight);
            }
			
			/**
			 * 최소, 최대 이상 축척으로 드래그 시 처리
			 */ 			
			if(zoomLevel > this.map.getNumZoomLevels()-1) {
				zoomLevel = this.map.getNumZoomLevels()-1;
			}
			else if(zoomLevel < 0) {
				zoomLevel = 0;
			}
			
            this.map.zoomTo(zoomLevel);
            this.mouseDragStart = null;
            this.zoomStart = null;
            OpenLayers.Event.stop(evt);
        }
    },
	
    moveZoomBar:function() {
        var newTop = 
            ((this.map.getNumZoomLevels()-1) - this.map.getZoom()) * 
            this.zoomStopHeight + this.startTop + 1;
        this.slider.style.top = newTop + "px";
		
		/**
		 * 축척바 위쪽의 DIV 높이 수정
		 */		
		this.zoomBasicDiv.style.height = (this.zoomStopHeight * (this.map.getNumZoomLevels() - this.map.getZoom() - 1)) + "px";
    },
	

	CLASS_NAME: "GPanZoomBar"
});


/**
 * Constant: X
 * {Integer}
 */
OpenLayers.Control.PanZoom.X = 4;

/**
 * Constant: Y
 * {Integer}
 */
OpenLayers.Control.PanZoom.Y = 4;
