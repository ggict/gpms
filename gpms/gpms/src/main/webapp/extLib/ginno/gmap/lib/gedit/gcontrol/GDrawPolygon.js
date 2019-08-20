/**
 * @copyright  2011 geOps
 * @license    https://github.com/geops/ole/blob/master/license.txt
 * @link       https://github.com/geops/ole
 */

/**
 * Class: GDrawPolygon
 * The DeleteFeature provides a button to delete all selected features
 *     from a given layer.
 *
 * Inherits from:
 *  - <OpenLayers.Control.DrawFeature>
 */
GDrawPolygon = OpenLayers.Class(OpenLayers.Editor.Control.DrawPolygon, {

    /**
     * Property: minArea
     * {Number} Minimum area of new polygons.
     */
    minArea: 0,

    title: OpenLayers.i18n('oleDrawPolygon'),
   
    featureType: 'polygon',

    /**
     * Constructor: OpenLayers.Editor.Control.DrawPolygon
     * Create a new control for drawing polygons.
     *
     * Parameters:
     * layer - {<OpenLayers.Layer.Vector>} Polygons will be added to this layer.
     * options - {Object} An optional object whose properties will be used
     *     to extend the control.
     */
    initialize: function (layer, options) {
        this.callbacks = OpenLayers.Util.extend(this.callbacks, {
            point: function(point) {
                this.layer.events.triggerEvent('pointadded', {point: point});
            }
        });
        
        OpenLayers.Control.DrawFeature.prototype.initialize.apply(this,
            [layer, OpenLayers.Handler.Polygon, options]);

        this.title = OpenLayers.i18n('oleDrawPolygon');
    },

    /**
     * Method: draw polygon only if area greater than or equal to minArea
     */
    drawFeature: function (geometry) {
        var feature = new OpenLayers.Feature.Vector(geometry),
            proceed = this.layer.events.triggerEvent('sketchcomplete', {feature: feature});
        
    	if(editor.copyMode && COMMON.isEmptyObject(editor.copiedField) === true){
    		COMMON.showMessage('편집오류 - 시설물 복제 & 복제된 속성이 존재하지 않습니다. [검색결과]창에서 복제할 시설물의 속성을 먼저 선택하여 주세요.');
    		return;
    	}
    	
        if (proceed !== false && geometry.getArea() >= this.minArea) {
        	var sG2Id = MAP_EDITOR.fn_get_newG2Id();
    		var sEditLayer = COMMON.fn_get_editingLayer();
    		
    		 var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(feature, sEditLayer.concat('.', sG2Id), OpenLayers.State.INSERT);
         	feature = oGFeature.feature;
         	
         	 var oEditRuleInfo = MAP_EDITOR.fn_get_editRuleInfo();
         	MAP_EDITOR.fn_add_featureToEditMonitor(feature, sEditLayer, sG2Id, oEditRuleInfo.option);         	
    		
         	var oStyle = MAP_EDITOR.fn_get_editFeatureStyle(feature);
    		feature.style = oStyle;
    		
            this.layer.addFeatures([feature]);
            this.featureAdded(feature);
            this.events.triggerEvent('featureadded', {feature : feature});            
    		this.layer.drawFeature(feature, sEditLayer.toLowerCase());    		
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
    CLASS_NAME: 'GDrawPolygon'
});