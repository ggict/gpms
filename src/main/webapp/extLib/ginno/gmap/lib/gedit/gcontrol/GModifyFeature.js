GModifyFeature = OpenLayers.Class(OpenLayers.Control.ModifyFeature, {

	selectedVertex: null,
	/**
     * Constructor: OpenLayers.Control.ModifyFeature
     * Create a new modify feature control.
     *
     * Parameters:
     * layer - {<OpenLayers.Layer.Vector>} Layer that contains features that
     *     will be modified.
     * options - {Object} Optional object whose properties will be set on the
     *     control.
     */
    initialize: function(layer, options) {
        options = options || {};
        this.layer = layer;
        this.vertices = [];
        this.virtualVertices = [];
        this.virtualStyle = OpenLayers.Util.extend({},
            this.layer.style ||
            this.layer.styleMap.createSymbolizer(null, options.vertexRenderIntent)
        );
        this.virtualStyle.fillOpacity = 0.3;
        this.virtualStyle.strokeOpacity = 0.3;
        this.deleteCodes = [46, 68];
        this.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        if(!(OpenLayers.Util.isArray(this.deleteCodes))) {
            this.deleteCodes = [this.deleteCodes];
        }

        // configure the drag handler
        var dragCallbacks = {
            down: function(pixel) {
                this.vertex = null;
                var feature = this.layer.getFeatureFromEvent(
                        this.handlers.drag.evt);
                if (feature) {

                	if(this.selectedVertex && (this.selectedVertex.id != feature.id) && editor.getGeometryType(feature) == 'point')
                		this.layer.drawFeature(this.selectedVertex, 'default');

                	this.selectedVertex = feature;

                	//console.log('down - feature');
                    this.dragStart(feature);
                } else if (this.clickout) {
                	//console.log('down - clickout');
                	this.selectedVertex = null;
                    this._unselect = this.feature;
                }
            },
            move: function(pixel) {
            	//console.log('move');

            	this.selectedVertex = null;

                delete this._unselect;
                if (this.vertex) {
                    this.dragVertex(this.vertex, pixel);
                }
            },
            up: function() {
            	//console.log('up');
                this.handlers.drag.stopDown = false;
                if (this._unselect) {

                	//console.log('up = _unselect');
                    this.unselectFeature(this._unselect);
                    delete this._unselect;
                    this.selectedVertex = null;
                }
                else{
                	if(this.selectedVertex && this.vertex)
                		this.layer.drawFeature(this.selectedVertex, 'selectedvertex');
                }
            },
            done: function(pixel) {
            	//console.log('done');
                if (this.vertex) {
                	//console.log('done = vertex');
                    this.dragComplete(this.vertex);
                }
            }
        };
        var dragOptions = {
            documentDrag: this.documentDrag,
            stopDown: false
        };

        // configure the keyboard handler
        var keyboardOptions = {
            keydown: this.handleKeypress
        };

        this.handlers = {
            keyboard: new OpenLayers.Handler.Keyboard(this, keyboardOptions),
            drag: new OpenLayers.Handler.Drag(this, dragCallbacks, dragOptions)
        };
    },

    initSelectedVertex: function(){
    	var selectedVertex = this.selectedVertex;
    	if(selectedVertex){
    		this.layer.drawFeature(this.selectedVertex, 'default');
    		this.selectedVertex = null;
    	}

    },
    /**
     * APIMethod: selectFeature
     * Select a feature for modification in standalone mode. In non-standalone
     * mode, this method is called when a feature is selected by clicking.
     * Register a listener to the beforefeaturemodified event and return false
     * to prevent feature modification.
     *
     * Parameters:
     * feature - {<OpenLayers.Feature.Vector>} the selected feature.
     */
    selectFeature: function(feature) {
    	
        if (this.feature === feature ||
           (this.geometryTypes && OpenLayers.Util.indexOf(this.geometryTypes,
           feature.geometry.CLASS_NAME) == -1)) {
            return;
        }
        if (this.beforeSelectFeature(feature) !== false) {
            if (this.feature) {
                this.unselectFeature(this.feature);
            }
            this.feature = feature;
            this.layer.selectedFeatures.push(feature);
            this.layer.drawFeature(feature, 'select');
            this.modified = false;
            this.resetVertices();
            this.onModificationStart(this.feature);
        }
        // keep track of geometry modifications
        var modified = feature.modified;
        if (feature.geometry && !(modified && modified.geometry)) {
            this._originalGeometry = feature.geometry.clone();
        }
    },

    /**
     * APIMethod: unselectFeature
     * Called when the select feature control unselects a feature.
     *
     * Parameters:
     * feature - {<OpenLayers.Feature.Vector>} The unselected feature.
     */
    unselectFeature: function(feature) {
        this.layer.removeFeatures(this.vertices, {silent: true});
        this.vertices = [];
        this.layer.destroyFeatures(this.virtualVertices, {silent: true});
        this.virtualVertices = [];
        if(this.dragHandle) {
            this.layer.destroyFeatures([this.dragHandle], {silent: true});
            delete this.dragHandle;
        }
        if(this.radiusHandle) {
            this.layer.destroyFeatures([this.radiusHandle], {silent: true});
            delete this.radiusHandle;
        }
        this.layer.drawFeature(this.feature, 'select'); //CJH - 2016.02.19
        this.feature = null;
        OpenLayers.Util.removeItem(this.layer.selectedFeatures, feature);
        this.onModificationEnd(feature);
        this.layer.events.triggerEvent("afterfeaturemodified", {
            feature: feature,
            modified: this.modified
        });
        this.modified = false;
    },

	/**
     * Method: dragStart
     * Called by the drag handler before a feature is dragged.  This method is
     *     used to differentiate between points and vertices
     *     of higher order geometries.
     *
     * Parameters:
     * feature - {<OpenLayers.Feature.Vector>} The point or vertex about to be
     *     dragged.
     */
    dragStart: function(feature) {
    	
    	// 룰 적용하지 않도록 함 - ehyun 2016.07.29
    	var oEditRuleInfo = MAP_EDITOR.fn_get_editRuleInfo();
    	if(COMMON.isEmptyObject(oEditRuleInfo.operType.modify) === false){
    		for(var key in oEditRuleInfo.operType.modify){
    			var sEditRule = oEditRuleInfo.operType.modify[key].rule;
    			if(sEditRule.indexOf('checkRelationGeometryMoveToByOffset') > -1){
    				oEditRuleInfo.operType.modify.splice(key,1);
    				break;
    			}
    		}

    		if(oEditRuleInfo.operType.modify.length > 0){ // 'checkRelationGeometryMove' or 'checkRelationGeometryMoveEndPoint' 일때
    			COMMON.showMessage('편집룰오류 & [정점편집] 기능은 "동시 이동" 관련 룰을 적용하지 않습니다. <br>해당 룰을 체크 해지하신 후 사용하시기 바랍니다.');
    			return;
    		}
    	}
    	
        var isPoint = feature.geometry.CLASS_NAME == 'OpenLayers.Geometry.Point';
        if (!this.standalone &&
                ((!feature._sketch && isPoint) || !feature._sketch)) {
            if (this.toggle && this.feature === feature) {
                // mark feature for unselection
                this._unselect = feature;
            }
            this.selectFeature(feature);
        }
        if (feature._sketch || isPoint) {
            // feature is a drag or virtual handle or point
            this.vertex = feature;
            this.handlers.drag.stopDown = true;
        }
    },

    /**
     * Method: dragVertex
     * Called by the drag handler with each drag move of a vertex.
     *
     * Parameters:
     * vertex - {<OpenLayers.Feature.Vector>} The vertex being dragged.
     * pixel - {<OpenLayers.Pixel>} Pixel location of the mouse event.
     */
    dragVertex: function(vertex, pixel) {

        var pos = this.map.getLonLatFromViewPortPx(pixel);
        var geom = vertex.geometry;
        var geomClone = geom.clone();
        geom.move(pos.lon - geom.x, pos.lat - geom.y);
        this.modified = true;        
        GEditRule.dist = COMMON.fn_get_DistanceBy2Point(geomClone.x, geomClone.y, geom.x, geom.y);        // vertex 정점편집 시, 이동거리 - 2016.7.28 ehyun
        
        /**
         * Five cases:
         * 1) dragging a simple point
         * 2) dragging a virtual vertex
         * 3) dragging a drag handle
         * 4) dragging a real vertex
         * 5) dragging a radius handle
         */
        if(this.feature.geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
            // dragging a simple point
            this.layer.events.triggerEvent("vertexmodified", {
                vertex: vertex.geometry,
                feature: this.feature,
                pixel: pixel
            });
        } else {
            if(vertex._index) {
                // dragging a virtual vertex
                vertex.geometry.parent.addComponent(vertex.geometry,
                                                    vertex._index);
                // move from virtual to real vertex
                delete vertex._index;
                OpenLayers.Util.removeItem(this.virtualVertices, vertex);
                this.vertices.push(vertex);
            } else if(vertex == this.dragHandle) {
                // dragging a drag handle
                this.layer.removeFeatures(this.vertices, {silent: true});
                this.vertices = [];
                if(this.radiusHandle) {
                    this.layer.destroyFeatures([this.radiusHandle], {silent: true});
                    this.radiusHandle = null;
                }
            } else if(vertex !== this.radiusHandle) {
                // dragging a real vertex
                this.layer.events.triggerEvent("vertexmodified", {
                    vertex: vertex.geometry,
                    feature: this.feature,
                    pixel: pixel
                });
            }
            // dragging a radius handle - no special treatment
            if(this.virtualVertices.length > 0) {
                this.layer.destroyFeatures(this.virtualVertices, {silent: true});
                this.virtualVertices = [];
            }
            this.layer.drawFeature(this.feature, this.standalone ? undefined : 'select');
        }
        // keep the vertex on top so it gets the mouseout after dragging
        // this should be removed in favor of an option to draw under or
        // maintain node z-index
        this.layer.drawFeature(vertex);
    },

    /**
     * Method: dragComplete
     * Called by the drag handler when the feature dragging is complete.
     *
     * Parameters:
     * vertex - {<OpenLayers.Feature.Vector>} The vertex being dragged.
     */
	dragComplete: function(vertex) {
		
    	var olFeature = this.feature;
    	editor.oSearchResult.data[0].results[0].feature.geometry = olFeature.geometry; // 이동한 위치로 oSearchResult 갱신
    	
    	this.drawModifiedFeature = function(){
    		MAP_EDITOR.fn_proc_modifiedFeature(oOriginGInnerFeatureClone, sLayerName, sG2Id);
    		
	    	this.resetVertices();
	    	if(!oOriginGInnerFeatureClone.state)
	    		this.setFeatureState();
	        this.onModification(oOriginGInnerFeatureClone);
	        this.layer.events.triggerEvent("featuremodified", {feature: oOriginGInnerFeatureClone});
    	}

    	var sFId = MAP_EDITOR.fn_get_fidByFeature(this.feature);
    	var sLayerName = MAP_EDITOR.fn_get_tblNameByFeature(this.feature);
    	var sG2Id = MAP_EDITOR.fn_get_g2idByFeature(this.feature);
    	var oOriginGInnerFeatureClone = MAP_EDITOR.fn_deepClone_featureToGInnerFeature(this.feature); // 현재 편집된(dragCompleted 된) feature 원본. this.drawModifiedFeature()에서 this.feature를 썻을때 scope문제로 못 찾는 경우가 생겨서.
    	
    	var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(this.feature, sFId, '');
    	this.feature = oGFeature.feature;
    	
    	// 룰 적용 - ehyun.2016.05.09
    	/*var oEditRuleInfo = MAP_EDITOR.fn_get_editRuleInfo();        
    	if(COMMON.isEmptyObject(oEditRuleInfo.operType.modify) === false){
    		var sErrorMsg = [];

    		for(var key in oEditRuleInfo.operType.modify){
    			var sEditRule = oEditRuleInfo.operType.modify[key].rule;	
    			if(sEditRule.indexOf('checkRelationGeometryMoveToByOffset') > -1){
    				oEditRuleInfo.operType.modify.splice(key,1);
    				break;
    			}
    		}

    		if(oEditRuleInfo.operType.modify.length > 0){	// 'checkRelationGeometryMove' or 'checkRelationGeometryMoveEndPoint' 일때
    			// this.feature.modified 정보는 편집 시점을 기준으로, 편집 시도 바로 전 originalGeometry임.
    			// this.setFeatureState()를 사용하지 않고 아래와 같이 사용하는 이유는, vertex 정점편집시 마다 최종 편집이 완료된 바로 전 도형정보를 가져와야 하므로(매 편집 vertex마다 룰 적용위해)
    			var oEmJsonFeatureObj = editor.editingFeatures[sLayerName][sG2Id];                	
    			if(COMMON.isEmptyObject(oEmJsonFeatureObj) === false){      
    				var oGInnerFeature = editor.makeFeatureByPosList(oEmJsonFeatureObj.type, oEmJsonFeatureObj.posList, sFId);
    				this.feature.state = OpenLayers.State.UPDATE;
    				this.feature.modified = OpenLayers.Util.extend(this.feature.modified, {
    					geometry: oGInnerFeature.geometry,
    					control : 'CustomModifyFeature'
    				});
    			}
    			else
    				this.feature.modified = {};

    			GEditRule.editingGeometry = this.feature;

    			for(var i=0,len=oEditRuleInfo.operType.modify.length; i<len; i++){
    				var sEditRule = oEditRuleInfo.operType.modify[i].rule;

    				$.globalEval(sEditRule);

    				if(COMMON.isEmptyObject(GEditRule.resultGeometry) === false){
    					//if(GEditRule.resultGeometry.data.length > 0){
    						this.drawModifiedFeature();
    						GEditRule.resultGeometry = {};
    					//}
    				}
    				else{
    					if(sEditRule.indexOf('checkRelationGeometryMoveEndPoint') > -1)// 룰 수행 결과가 없는(연결된 관이 없어서) point일 경우,
            				this.drawModifiedFeature();            				 
            			else
            				sErrorMsg.push(oEditRuleInfo.operType.modify[i].errorMsg + '</br>');    
    					if(!oEditRuleInfo.operType.modify[i].errorMsg)	// 룰 수행 결과가 없는(연결된 관이 없어서) point or line일 경우
    						this.drawModifiedFeature();
    					else
    						sErrorMsg.push(oEditRuleInfo.operType.modify[i].errorMsg + '</br>');
    				}
    			}
    			if(sErrorMsg.length >0){   		   			
    				COMMON.showMessage('편집룰오류 & [룰 위반] ' + sErrorMsg.join(''));    				
    				return;
    			}
    		}
    		else			// checkRelationGeometryMoveToByOffset 일때
    			this.drawModifiedFeature();
        } else*/
        	this.drawModifiedFeature();
    },

    /**
     * Method: setFeatureState
     * Called when the feature is modified.  If the current state is not
     *     INSERT or DELETE, the state is set to UPDATE.
     */
    setFeatureState: function() {
        if(this.feature.state != OpenLayers.State.INSERT &&
           this.feature.state != OpenLayers.State.DELETE) {
            this.feature.state = OpenLayers.State.UPDATE;
            if (this.modified && this._originalGeometry) {
                var feature = this.feature;
                feature.modified = OpenLayers.Util.extend(feature.modified, {
                    geometry: this._originalGeometry
                });
                delete this._originalGeometry;
            }
        }
    },

    handleKeypress: function(evt) {
        var code = evt.keyCode;

        if(this.feature &&
           OpenLayers.Util.indexOf(this.deleteCodes, code) != -1) {

        	//this.resetVertices(); //--> edited BY CJH (2016.07.04)
        	// 수행시 collectVertices를 재수행해 id를 다시할당함. 그러나 아래  getFeatureFromEvent에서는 할당하기전 id를 가져와비교하고 있음.
        	// 꼭 수행해야한다면 getFeatureFromEvent 수행할때의 feature정보도갱신되도록 변경필요.
            var vertex = this.layer.getFeatureFromEvent(this.handlers.drag.evt);

            if(!vertex){
            	vertex = this.selectedVertex;
            }

            if(vertex){

            	var sGeomType = editor.getGeometryType(this.feature);

            	if(sGeomType == 'linestring'){
            		if(this.feature.geometry.components.length === 2){
            			COMMON.showMessage('편집룰 오류 & 선형데이터는 최소 2개의 정점이 필요합니다. 삭제작업이 중단됩니다!');
                		return false;
            		}

            	}
            	else if(sGeomType == 'polygon'){
            		if(this.feature.geometry.components[0].components.length === 4){
            			COMMON.showMessage('편집룰 오류 & 면형데이터는 최소 4개의 정점이 필요합니다. 삭제작업이 중단됩니다!');
            			return false;
            		}
            	}
            	//this.layer.drawFeature(vertex, 'blink');
            	if(code === 46 || code === 68){
                	if(!confirm('선택된 정점을 삭제하시겠습니까?')){
                		this.layer.drawFeature(vertex, 'select');
                		return false;
                	}
                }

                if (vertex &&  OpenLayers.Util.indexOf(this.vertices, vertex) != -1 && !this.handlers.drag.dragging && vertex.geometry.parent) {
                    // remove the vertex
                    vertex.geometry.parent.removeComponent(vertex.geometry);
                    this.layer.events.triggerEvent("vertexremoved", {
                        vertex: vertex.geometry,
                        feature: this.feature,
                        pixel: evt.xy
                    });
                    this.layer.drawFeature(this.feature, this.standalone ?
                                           undefined : 'select');
                    this.modified = true;
                    this.selectedVertex = null;
                    this.resetVertices();
                    this.setFeatureState();
                    this.onModification(this.feature);
                    this.layer.events.triggerEvent("featuremodified",{feature: this.feature});
                    
                    var sLayerName = MAP_EDITOR.fn_get_tblNameByFeature(this.feature);
                	var sG2Id = MAP_EDITOR.fn_get_g2idByFeature(this.feature);
                	var oOriginGInnerFeatureClone = MAP_EDITOR.fn_deepClone_featureToGInnerFeature(this.feature.clone()); // 현재 편집된(dragCompleted 된) feature 원본. this.drawModifiedFeature()에서 this.feature를 썻을때 scope문제로 못 찾는 경우가 생겨서.
                	
                    MAP_EDITOR.fn_proc_modifiedFeature(oOriginGInnerFeatureClone, sLayerName, sG2Id);
                    
                }
            }
            else{
            	//console.log('vertex삭제 실패 [keycode:' + code + '] this.layer.getFeatureFromEvent(this.handlers.drag.evt) 수행결과 없음');
            }

        }
    },
    CLASS_NAME: "GModifyFeature"
});