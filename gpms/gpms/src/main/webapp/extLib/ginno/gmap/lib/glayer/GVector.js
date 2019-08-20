/**********************************************************************************
 * 파일명 : GVector.js
 * 설 명 : OpenLayers.Layer.Vector 를 상속 받아 수정
 * 필요 라이브러리 : OpenLayers
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2011.04.19		최원석				0.1					최초 생성
 * 2016.08.18		윤은희									drawFeature() 추가
 * 
 * 
 * 참고 자료
 * --------------------------------------------------------------------------------
 * OpenLayers
 * 출처 : http://openlayers.org/
 * 
 * 
**********************************************************************************/

GVector = OpenLayers.Class(OpenLayers.Layer.Vector, {
	
	redraw: function() {
        var redrawn = false;
        if (this.map) {

            // min/max Range may have changed
            this.inRange = this.calculateInRange();

            // map's center might not yet be set
            var extent = this.getExtent();

            if (extent && this.inRange && this.visibility) {
                var zoomChanged = true;
                this.moveTo(extent, zoomChanged, false);
                this.events.triggerEvent("moveend",
                    {"zoomChanged": zoomChanged});
                redrawn = true;
            }
        }
		
		//창 닫기 에러 - 조건문 추가
		if(this.map.paddingForPopups) {
			for(var i in this.map.popups) {
				var popup = this.map.popups[i];
				if(popup && popup.attributes && popup.attributes.featureType && popup.attributes.featureType == 'Text' && popup.type == 'draw') {
					if(popup.attributes['font-family']) {
						$("#"+popup.id).css('font-family', popup.attributes['font-family']);
					}
					if(popup.attributes['font-size']) {
						$("#"+popup.id).css('font-size', popup.attributes['font-size']);
					}
					if(popup.attributes['color']) {
						$("#"+popup.id).css('color', popup.attributes['color']);
					}
				}
				
				popup.updateSize();
			}
		}
		
        return redrawn;
    },
    
    /**
     * APIMethod: drawFeature
     * Draw (or redraw) a feature on the layer.  If the optional style argument
     * is included, this style will be used.  If no style is included, the
     * feature's style will be used.  If the feature doesn't have a style,
     * the layer's style will be used.
     * 
     * This function is not designed to be used when adding features to 
     * the layer (use addFeatures instead). It is meant to be used when
     * the style of a feature has changed, or in some other way needs to 
     * visually updated *after* it has already been added to a layer. You
     * must add the feature to the layer for most layer-related events to 
     * happen.
     *
     * Parameters: 
     * feature - {<OpenLayers.Feature.Vector>} 
     * style - {String | Object} Named render intent or full symbolizer object.
     */
    drawFeature: function(feature, style) {
        // don't try to draw the feature with the renderer if the layer is not 
        // drawn itself
        if (!this.drawn) {
            return;
        }
        if (typeof style != "object") {
            if(!style && feature.state === OpenLayers.State.DELETE) {
                style = "delete";
            }
            var renderIntent = style || feature.renderIntent;
            // ehyun. 2016.08.18 : 편집 feature에 style 속성지정하여, [select or delete]가 아닌 경우는 편집레이어에 정의된 자신의 스타일로 feature그리도록(서브심볼포함)
            if(style === 'select' || style === 'delete' || style === 'blink'){
            	style = '';            	
            }
            else{
            	if(feature.attributes.fid !== undefined && feature.attributes.fid !== ''){
            		var oStyle;
            		if(feature.layer && feature.layer.name === editor.searchLayer.name)
            			style = 'search';
            		oStyle = style === 'search' ? MAP_EDITOR.fn_get_searchFeatureStyle(feature) : MAP_EDITOR.fn_get_editFeatureStyle(feature);            		

            		if(feature.renderIntent === '')
            			feature.renderIntent = 'default';
    	    		feature.style = oStyle;
    	    		
    	    		if(!style){
    	    			if(feature.renderIntent === 'delete')
        	    			feature.style = '';	
    	    		}
            	}
            	style = feature.style || this.style;
            }
            	
            if (!style) {
                style = this.styleMap.createSymbolizer(feature, renderIntent);
            }
        }
        
        var drawn = this.renderer.drawFeature(feature, style);
        //TODO remove the check for null when we get rid of Renderer.SVG
        if (drawn === false || drawn === null) {
            this.unrenderedFeatures[feature.id] = feature;
        } else {
            delete this.unrenderedFeatures[feature.id];
        }
    },
	
	parseStyle : function(feature, style) {
		// don't try to draw the feature with the renderer if the layer is not 
	    // drawn itself

	    if (typeof style != "object") {
	        if(!style && feature.state === OpenLayers.State.DELETE) {
	            style = "delete";
	        }
	        var renderIntent = style || feature.renderIntent;
	        style = feature.style || this.style;
	        if (!style) {
	            style = this.styleMap.createSymbolizer(feature, renderIntent);
	        }
	    }

		return style;
	},
    
    addPoint : function(lon, lat, attributes) {
    	var point  = new OpenLayers.Geometry.Point(lon, lat);
    	var feature = new OpenLayers.Feature.Vector(point, attributes);
    	
    	this.addFeatures(feature);
		
		return feature;
    },
    
    getGML : function() {
    	var gml = new OpenLayers.Format.GML();
    	return gml.write(this.features);
    },
    
    setGML : function(str) {
    	var gml = new OpenLayers.Format.GML();
    	var features = gml.read(str);
    	
    	if(features && features.length) {
    		this.addFeatures(features);
    	}
    },
    
	CLASS_NAME: "GVector"
});