GTransformFeature = OpenLayers.Class(OpenLayers.Editor.Control.TransformFeature, {
    
	/**
     * @type {OpenLayers.Layer.Vector}
     */
    editLayer: null,
    
	/**
     * @param {OpenLayers.Layer.Vector} editLayer
     */
    initialize: function(editLayer, refLayer){
        this.strategiesOnHold = [];
        
        OpenLayers.Control.TransformFeature.prototype.initialize.call(this, editLayer, {
            renderIntent: "transform",
            rotationHandleSymbolizer: "rotate",
            id:"CustomTransformFeature"
        });
        
        this.editLayer = editLayer;
        
        this.addStyles();
        
        this.events.on({
            'transformcomplete': function(e){
            	
            	this.drawTransformFeature = function(){            		
            		MAP_EDITOR.fn_check_SpatialValueChange(oOriginGInnerFeatureClone, sLayerName, sG2Id);
                	MAP_EDITOR.fn_call_saveMiddleBridge(sLayerName, sG2Id);		// 중간저장(공간정보) 갱신 - ehyun.2016.03.25
                	
                	// 편집하는 feature의 위치 속성 자동 갱신(행정동/법정동/도엽번호)
                	GEditRule.checkRelationLocFldValue(oOriginGInnerFeatureClone);

                	// 이동 전 위치의 feature를 MAP_EDITOR.oStyleVectorLayer에서 제거 후, 이동 후 위치에서 새로 생성/추가 후 다시 그림 - ehyun.
                	var oEditFeature = editor.getFeatureByFid(editor.editLayer, sFId);
        	    	if(oEditFeature){
        	    		editor.editLayer.destroyFeatures(oEditFeature, {silent: true});
                    	editor.addDrawFeature(editor.editLayer, oOriginGInnerFeatureClone, 'select');
        	    	}
        	    	
        	    	var oStyleFeature = editor.getFeatureByFid(editor.styleLayer, sFId);
        	    	if(oStyleFeature){
        	    		editor.styleLayer.destroyFeatures(oStyleFeature, {silent: true}); 
            			var oStyleGFeature = editor.createFeature(oOriginGInnerFeatureClone, sFId);
                    	editor.addDrawFeature(editor.styleLayer, oStyleGFeature, sLayerName);
        	    	}
        	    	
        	    	var oSearchFeature = editor.getFeatureByFid(editor.searchLayer, sFId);
        			if(oSearchFeature)
        				editor.searchLayer.destroyFeatures(oSearchFeature, {silent: true});
        			
        			editor.effectLayer.removeAllFeatures();
                	
                    oOriginGInnerFeatureClone.state = OpenLayers.State.UPDATE;
                    this.editLayer.events.triggerEvent("afterfeaturemodified", {
                        feature: oOriginGInnerFeatureClone
                    });
                    
                    if(editor.getGeometryType(oOriginGInnerFeatureClone) !== 'point')
                    	MAP_EDITOR.fn_draw_oneFeatureBorder(oOriginGInnerFeatureClone,'selectpoint');
            	}

            	var sFId = MAP_EDITOR.fn_get_fidByFeature(e.feature);
            	var sLayerName = MAP_EDITOR.fn_get_tblNameByFeature(e.feature);
            	var sG2Id = MAP_EDITOR.fn_get_g2idByFeature(e.feature);
            	var oOriginGInnerFeatureClone = MAP_EDITOR.fn_deepClone_featureToGInnerFeature(e.feature); // 현재 편집된(transformcomplete 된) feature 원본. this.drawTransformFeature()에서 e.feature를 썻을때 scope문제로 못 찾는 경우가 생겨서.
            	
            	var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(e.feature, sFId, '');
            	e.feature = oGFeature.feature;
            	
            	// 룰 적용 - ehyun.2016.05.09
            	var oEditRuleInfo = MAP_EDITOR.fn_get_editRuleInfo();        
                if(COMMON.isEmptyObject(oEditRuleInfo.operType.modify) === false){
                	for(var key in oEditRuleInfo.operType.modify){
                		var sEditRule = oEditRuleInfo.operType.modify[key].rule;	
                		if(sEditRule.indexOf('checkRelationGeometryMoveToByOffset') > -1){
                			oEditRuleInfo.operType.modify.splice(key,1);
                			break;
                		}
                	}
                	
                	if(oEditRuleInfo.operType.modify.length > 0){	// 'checkRelationGeometryMove' or 'checkRelationGeometryMoveEndPoint' 일때
                		// e.feature.modified 정보는 편집 시점을 기준으로, 편집 시도 바로 전 originalGeometry임.
                    	// Transform편집시 마다 최종 편집이 완료된 바로 전 도형정보를 가져와야 하므로
                    	var oEmJsonFeatureObj = editor.editingFeatures[sLayerName][sG2Id];
                		if(COMMON.isEmptyObject(oEmJsonFeatureObj) === false){
                			var oGInnerFeature = editor.makeFeatureByPosList(oEmJsonFeatureObj.type, oEmJsonFeatureObj.posList, sFId);
                			e.feature.state = OpenLayers.State.UPDATE;
                			e.feature.modified = OpenLayers.Util.extend(e.feature.modified, {
                                geometry: oGInnerFeature.geometry
                            });
                		}
                		else
                			e.feature.modified = {};

                		GEditRule.editingGeometry = e.feature;

                		if(e.feature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','') === 'LineString'){
                			// Transform 이전과 이후, feature의 첫번째 vertex를(정점 편집이 아닌 feature전체가 이동하므로 특정 점 하나만 기준으로 삼으면 됨) 가지고 이동 거리를 계산하여 이동수치를 찾아낸다.
                			GEditRule.offset.x = e.feature.geometry.components[0].x - e.feature.modified.geometry.components[0].x;
                			GEditRule.offset.y = e.feature.geometry.components[0].y - e.feature.modified.geometry.components[0].y;
                		}

                		for(var i=0,len=oEditRuleInfo.operType.modify.length; i<len; i++){
                			var sEditRule = oEditRuleInfo.operType.modify[i].rule;

                			$.globalEval(sEditRule);

                			this.drawTransformFeature();
                			GEditRule.resultGeometry = {};
                		}
                	}
                	else			// checkRelationGeometryMoveToByOffset 일때
                		this.drawTransformFeature();
                }	
                else 
                	this.drawTransformFeature();                	
            },
            scope: this
        });
        
        this.title = OpenLayers.i18n('oleTransformFeature');
    },
    /**
     * Adds style of box around object and handles shown during transformation
     */
    addStyles: function(){
        var control = this;
        this.editLayer.styleMap.styles.transform =new OpenLayers.Style({
            display: "${getDisplay}",
            cursor: "${role}",
            pointRadius: 5,
            fillColor: "#07f",
            strokeOpacity: "${getStrokeOpacity}",
            fillOpacity: 1,
            strokeColor: "${getStrokeColor}",
            strokeWidth: "${getStrokeWidth}",
            strokeDashstyle: '${getStrokeDashstyle}'
        }, {
            context: {
                getDisplay: function(feature) {
                    if(control.feature===null || control.feature.geometry instanceof OpenLayers.Geometry.Point){
                        return "none";
                    }
                    // hide the resize handle at the south-east corner
                    return feature.attributes.role === "se-resize" ? "none" : "";
                },
                getStrokeColor: function(feature){
                    return feature.geometry instanceof OpenLayers.Geometry.Point ? '#037' : "#ff00ff";
                },
                getStrokeOpacity: function(feature){
                    return feature.geometry instanceof OpenLayers.Geometry.Point ? 0.8 : 0.5;
                },
                getStrokeWidth: function(feature){
                    return feature.geometry instanceof OpenLayers.Geometry.Point ? 2 : 1;
                },
                getStrokeDashstyle: function(feature){
                    return feature.geometry instanceof OpenLayers.Geometry.Point ? 'solid' : 'longdash';
                }
            }
        });
        this.editLayer.styleMap.styles.rotate = new OpenLayers.Style({
            display: "${getDisplay}",
            pointRadius: 10,
            fillColor: "#ddd",
            fillOpacity: 1,
            strokeColor: "black",
            // Display arrow image (rotationHandle.png) unless Browser is IE which does not reliable support data URI
            externalGraphic: OpenLayers.Util.getBrowserName()==='msie' ? undefined : 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABcAAAAWCAYAAAArdgcFAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAAOnAAADnUBiCgbeAAAAAd0SU1FB9wIAgsPAyGVVyoAAAQFSURBVDjLjZVfTJNnFMZ//aQ0UVMgIi11Kngx1KUGBo0XU+bmmFnmErzRkBK2RBMUyfaV4cQ4t5GNm8UMTJTMbDSj4YI5GFmcMxvyZ4yIJJUbY5GNJv7ZQtGJNBoTL+yzC+NnugJykpO8Oe85z/u85z3vOUhiPj13tsfcX71PGzesV0ZGhgA5nU5tWF+gfXvf0089Z8yF4uc0XvjtnOkrKRbwXC1+uUjnzvaYiwL/4vNPtWTJEgHKyclRTU2Nurq6FA6HFY1GdfnyZXV3d+vgwYPKyckRIMMw9MnHR7Qg+Id1HwiQzWZTdXW1rly5omg0Oq9evXpVtbW1MgxDgGpr9mtO8OC3X5s2m01paWk6derUgqD/19OnT8tutwtQ68kTVopskgDIzl6hu3dnaGxspLKykvnk4cOHRCKRJJvD4eDatWs0NDSQkeFkdjZuA54wP1QfEKDNmzc/l6Xf70951DVr1igajaq0tFSA3q+tkSQMgO/PdAFgmmYSo4mJiRTm9+/ft9Z1dXWEQiGOHz8OQCAQAOCHrh8BMEaGB8ybN2/hcrnw+XxWYDgcZufOnfT29qYckJmZSV5eHu3t7Xi9XoqLiwHYtGkTq1evZioWY7D/V9P486/JZoDCwkJsNpsF0NLSgsfjobS0NAV86dKltLa2MjMzQygUStorKSkBYHx8otmYmooBkJubazk8evSI0dFRdu3ahcPhmPNhCwoKKCoqYmhoKMm+cuVKAGKxGGlP2SYSCcshHo+TSCRwu90poKZp8uDBAwBcLhfj4+NJ+0+rzzAM0jyeJ4ynpqYsh6ysLNLT07l+/XoK+Nq1a631jRs3Ugjcvn0bALfbhVFQ8GIAYGxsjMePHwNgt9vZtm0b3d3d3Lt3b860XLx4kUgkQllZWRLrS5cuAfDSxg0BJJGfnydA7e3tVj2fP39eDodDXq9Xg4ODSbXe0dGhrKws5efnKxKJWPbOzk4BWrXKI+v7HzvaIECFhYWanJy0nNva2rRs2TLZ7XZt3bpV5eXl8nq9ArRu3ToNDAwkHerz+QToUH1ASd8/1+1WbHqa+vp6Dhw4YF11enqaYDDIyMgI8Xgct9vNjh078Pv9SZUUDAZpamoiO3sFd+78awOegZ/p7DAr/O82AzQ1NbF7924WKz09PRw+fJhEIkHou2+orNr7rLc81cbPjln9Ys+ePVYPn0/D4bCqqqqsmKNHPtKCw6L15Ak5HOkCtHz5clVUVKitrU19fX0aGxtTf3+/gsGg/H6/nE6nANntdrV89aUWNeZGR4bMN8u2L2rMbX/9NQ3/fmHOMWflfC4ZGR4wz/78S/PQH8Pc+vsfZmfjZGZm8MIqD1u2vMI7b78V2PLqGy3zxf8Hbd5G4wGXKsEAAAAASUVORK5CYII=',
            graphicWidth:23,
            graphicHeight:22
        }, {
            context: {
                getDisplay: function(feature) {
                    if(control.feature===null || control.feature.geometry instanceof OpenLayers.Geometry.Point){
                        return "none";
                    }
                    // only display the rotate handle at the south-east corner
                    return feature.attributes.role === "se-rotate" ? "" : "none";
                }
            }
        });
    },
    
    activate: function(){
        // Disable BBOX strategy since it destroys all features whilst updating data
        for(var strategyIter=0; OpenLayers.Util.isArray(this.layer.strategies) && strategyIter<this.layer.strategies.length; strategyIter++){
            if(this.layer.strategies[strategyIter] instanceof OpenLayers.Strategy.BBOX){
                this.strategiesOnHold.push(this.layer.strategies[strategyIter]);
                this.layer.strategies[strategyIter].deactivate();
            }
        }
        
        var activated = OpenLayers.Control.TransformFeature.prototype.activate.call(this);
        if(this.feature===null || this.feature.geometry instanceof OpenLayers.Geometry.Point){
            // Re-render handles to hide them when control is activated initially without a feature selected so far
            this.editLayer.drawFeature(this.box, this.renderIntent);
            var f, handleIter;
            for(handleIter=0; handleIter<this.rotationHandles.length; handleIter++){
                f = this.rotationHandles[handleIter];
                this.editLayer.drawFeature(f, this.renderIntent);
            }
            for(handleIter=0; handleIter<this.handles.length; handleIter++){
                f = this.handles[handleIter];
                this.editLayer.drawFeature(f, this.renderIntent);
            }
        }
        
        this.events.on({
            setfeature: this.highlightTransformedFeature,
            scope: this
        });
        return activated;
    },
    /**
     * Method: createControl
     * Creates a DragFeature control for this control.
     */
    createControl: function() {
        var control = this;
        this.dragControl = new OpenLayers.Control.DragFeature(this.layer, {
            documentDrag: true,
            // avoid moving the feature itself - move the box instead
            moveFeature: function(pixel) {
                if(this.feature === control.feature) {
                    this.feature = control.box;
                }
                OpenLayers.Control.DragFeature.prototype.moveFeature.apply(this,
                    arguments);
            },
            // transform while dragging
            onDrag: function(feature, pixel) {
                if(feature === control.box) {
                    control.transformFeature({center: control.center});
                }
            },
            // set a new feature
            onStart: function(feature, pixel) {
            	
                var eligible = !control.geometryTypes ||
                    OpenLayers.Util.indexOf(control.geometryTypes,
                        feature.geometry.CLASS_NAME) !== -1;
                var i = OpenLayers.Util.indexOf(control.handles, feature);
                i += OpenLayers.Util.indexOf(control.rotationHandles,
                    feature);
                if(feature !== control.feature && feature !== control.box &&
                                                        i == -2 && eligible) {
                    control.setFeature(feature);
                }
            },
            onComplete: function(feature, pixel) {
                control.events.triggerEvent("transformcomplete",
                    {feature: control.feature});
            }
        });
    },
    	
    CLASS_NAME: 'GTransformFeature'
});
