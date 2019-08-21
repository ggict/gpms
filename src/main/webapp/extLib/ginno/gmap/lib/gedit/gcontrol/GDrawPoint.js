/**********************************************
 * 클래스명 : OpenLayers.Editor.Control.DrawCustomPoint
 * 설  명 : 커스터마이징 DrawPoint
 * 인  자 : -
 * 사용법 : -
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.08.28    윤은희      신규작업
 * 2015.08.28    윤은희		  added feature의 정보를 편집모니터에 등록
 * 2015.11.18	 	윤은희		  시설물 복제 시, 복제속성이 존재하지 않을 경우 수행하지 않도록.
 */
GDrawPoint = OpenLayers.Class(OpenLayers.Editor.Control.DrawPoint, {
	 title: OpenLayers.i18n('oleDrawPoint'),
	    featureType: 'point',

	    /**
	     * Constructor: OpenLayers.Editor.Control.DrawPath
	     * Create a new control for drawing points.
	     *
	     * Parameters:
	     * layer - {<OpenLayers.Layer.Vector>} Points will be added to this layer.
	     * options - {Object} An optional object whose properties will be used
	     *     to extend the control.
	     */
	    initialize: function (layer, options) {
	        this.callbacks = OpenLayers.Util.extend(this.callbacks, {
	            point: function (point) {
	                this.layer.events.triggerEvent('pointadded', {point: point});
	            }
	        });

	        OpenLayers.Control.DrawFeature.prototype.initialize.apply(this,
	                [layer, OpenLayers.Handler.Point, options]);

	        this.title = OpenLayers.i18n('oleDrawPoint');
	    },
	   
	    /**
	     * Method: draw point
	     */
	    drawFeature: function (geometry) {
	    	this.addDrawFeatures = function(){	    		
	    		var oStyle = MAP_EDITOR.fn_get_editFeatureStyle(feature);
	    		feature.style = oStyle;
	    			    		
	    		this.layer.addFeatures([feature]);
				this.featureAdded(feature);
				this.events.triggerEvent('featureadded', {feature: feature});				
				this.layer.drawFeature(feature, sEditLayer.toLowerCase());
	    	}
	    	
	    	if(editor.copyMode && COMMON.isEmptyObject(editor.copiedField) === true){
	    		COMMON.showMessage('편집오류 - 시설물 복제 & 복제된 속성이 존재하지 않습니다. [검색결과]창에서 복제할 시설물의 속성을 먼저 선택하여 주세요.');
	    		return;
	    	}
	    	
	    	var feature = new OpenLayers.Feature.Vector(geometry),
	    	proceed = this.layer.events.triggerEvent('sketchcomplete', {feature: feature});

	    	if (proceed !== false) {
	    		this.events.triggerEvent('beforefeatureadded', {feature: feature});
	    		
	    		var sG2Id = MAP_EDITOR.fn_get_newG2Id();
	    		var sEditLayer = COMMON.fn_get_editingLayer();	    
	    		
	    		var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(feature, sEditLayer.concat('.', sG2Id), OpenLayers.State.INSERT);
	        	feature = oGFeature.feature;
	           
   	            // check for EditRule - ehyun. 2016.04.05
	            var oEditRuleInfo = MAP_EDITOR.fn_get_editRuleInfo();	            
	            if(COMMON.isEmptyObject(oEditRuleInfo.operType.add) === false){		// 룰 적용 ex) GEditRule.checkRelationGeometryEnd, checkRelationGeometry(Contains)
	            	var sErrorMsg = [];
	            	GEditRule.editingGeometry = feature;

	            	for(var i=0,len=oEditRuleInfo.operType.add.length; i<len; i++){
	            		var sEditRule = oEditRuleInfo.operType.add[i].rule;	            		
	            		$.globalEval(sEditRule);

	            		if(COMMON.isEmptyObject(GEditRule.resultGeometry) === false){	            				            			
	            			if(GEditRule.resultGeometry.data.length > 0){
	            				
	            				// WTL_VALV_PS 이면 교차된 상수관 분할처리
	            				if(sEditLayer === 'WTL_VALV_PS'){
	            					editor.addUnDrawFeature(editor.refLayer, oGFeature.feature);
	            					editor.divideLine(oGFeature, GEditRule.resultGeometry.data[0].results[0].feature);
	            					map.activeControls("drag");
	            				}
	            				
	            				MAP_EDITOR.fn_add_featureToEditMonitor(feature, sEditLayer, sG2Id, oEditRuleInfo.option);
	            				this.addDrawFeatures();

	            				GEditRule.resultGeometry = {};
	            			}
	            		}
	            		else
	            			sErrorMsg.push(oEditRuleInfo.operType.add[i].errorMsg + '</br>');
	            	}
	            	if(sErrorMsg.length >0){
	            		COMMON.showMessage('편집룰오류 & [룰 위반] ' + sErrorMsg.join(''),4000);
	            		return;
	            	}
	            } else{	            	
	            	MAP_EDITOR.fn_add_featureToEditMonitor(feature, sEditLayer, sG2Id, oEditRuleInfo.option);
	            	this.addDrawFeatures();
	            }	                      
	        }
	    },
		deactivate: function () {
	        if (this.active) {
	            if (this.handler) {
	                this.handler.deactivate();
	            }
	            this.active = false;
	            if(this.map) {
	                OpenLayers.Element.removeClass(
	                    this.map.viewPortDiv,
	                    this.displayClass.replace(/ /g, "") + "Active"
	                );
	            }
	            this.events.triggerEvent("deactivate");
	             
	            var snapCtrl = map.getControl("GSnappingSettings");
	            if(snapCtrl){
	            	MAP_EDITOR.fn_remove_snapPopup();
	                snapCtrl.popup = null;
	            }
	            
	            return true;
	        }
	        
	        return false;
	    },
	    CLASS_NAME: 'GDrawPoint'
	});