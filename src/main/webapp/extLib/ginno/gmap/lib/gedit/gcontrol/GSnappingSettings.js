/**********************************************
 * 클래스명 : GSnappingSettings
 * 설  명 : 커스터마이징 DrawPoint
 * 인  자 : -
 * 사용법 : -
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.08.28    윤은희      신규작업
 *
 */
GSnappingSettings = OpenLayers.Class(OpenLayers.Editor.Control.SnappingSettings, {

    title: OpenLayers.i18n('oleSnappingSettings'),

    map : null,
    
    popup : null,
    
    layer: null,

    snapping: new OpenLayers.Control.Snapping(),

    tolerance: 10,
    
    preTolerance: 10,

    /**
     * @var {Array.<String>} Identifiers of checkboxes to enable snapping for individual layers
     */
    snappingLayers: null,

    //각 레이어의 스냅대상 지정 {node, vertex, edge}
    snappingPoints: {},
    /**
     * Layer that displays guide lines and snapping points
     * @var OpenLayers.Editor.Layer.Snapping
     */
    snappingGuideLayer: null,

    layerListDiv: null,

    toleranceInput: null,

    initialize: function (layer, options) {
        this.snappingLayers = [];
        this.layer = layer;
        this.map = options.map;
        
        if(options.tolerance){ 
        	this.tolerance = options.tolerance;
        	this.preTolerance = options.tolerance;
        }
        
        OpenLayers.Control.Button.prototype.initialize.apply(this, [options]);

        this.trigger = OpenLayers.Function.bind(this.setSnappingEnv, this);
        this.trigger = OpenLayers.Function.bind(this.changeSnapping, this);

        this.events.register("deactivate", this, this.onDeactivate);

        this.title = OpenLayers.i18n('oleSnappingSettings');
        
        
        //스냅기준거리 팝업생성
        /*var mouseXy = {};
		
		mouseXy.x 	= event.clientX;
		mouseXy.y	= event.clientY;
		var oLonlat = this.map.getLonLatFromPixel(mouseXy);
		
		var aContentHtml = [];
		
		aContentHtml.push('<div class="olControlSnapInfo">');
		aContentHtml.push('<span>Snap 기준 : ' + this.tolerance + ' (px)</span>');
		aContentHtml.push('</div>');
		
	    var oPopup = new GPopup("snapInfoPopup", oLonlat, null, aContentHtml.join(''), new OpenLayers.Pixel(3,3));
	    this.popup = oPopup;
	    this.map.addPopup(oPopup);*/
	    
        this.map.events.register('mousemove', this, this.mapMouseMove);
	    
    },

    deactivate: function () {
        OpenLayers.Control.Button.prototype.deactivate.call(this);
        if (this.map && this.map.editor && this.map.editor.dialog) {
            this.map.editor.dialog.hide();
        }

        //this.map.events.unregister('mousemove', this, this.mapMouseMove);
        
    },

    onDeactivate: function () {
        if (this.snapping.active) {
            this.activate();
        }
    },

    setSnappingEnv: function () {

        var content, toleranceHeader, layerHeader;

        this.activate();

    },
    
    mapMouseMove: function(){
    	
    	var editor = editor || map.editor;  
    	
    	var bSnapChecked = $("#chkOptEditSnap").is(":checked") ;
    	var bShowChecked = $("#chkShowSnapDist").is(":checked") ;
    	
		if(editor.editMode && this.popup && bSnapChecked && bShowChecked) {  
    		
    		//console.log(this.popup.getLonLat().lon +',' + this.popup.getLonLat().lat + '/' + event.clientX + ','+ event.clientY);
        	var oPixel = new OpenLayers.Pixel(event.clientX, event.clientY);  
        	
        	oPixel.x -= $(this.popup.div).parent().offset().left;
        	oPixel.y -= $(this.popup.div).parent().offset().top;
    		 
            this.popup.updateSize();
        	this.popup.moveTo(oPixel); 
    	
		}
    	 
    	
    	
	},
	
    redraw: function () {

        var layer, element, content;

        this.layerListDiv.innerHTML = '';

        for (var i = 0; i < this.map.layers.length; i++) {

            layer = this.map.layers[i];

            if (!(layer instanceof OpenLayers.Layer.Vector.RootContainer) &&
                    layer instanceof OpenLayers.Layer.Vector && !(layer instanceof OpenLayers.Editor.Layer.Snapping) &&
                    layer.name.search(/OpenLayers.Handler.+/) == -1) {

                content = document.createElement('div');

                element = document.createElement('input');
                element.type = 'checkbox';
                element.name = 'snappingLayer';
                element.id = 'Snapping.' + layer.id;
                element.value = 'true';
                if (this.snappingLayers.indexOf(layer) >= 0) {
                    element.checked = 'checked';
                    element.defaultChecked = 'selected'; // IE7 hack
                }
                content.appendChild(element);
                OpenLayers.Event.observe(element, 'click',
                        OpenLayers.Function.bind(this.setLayerSnapping, this, layer, element.checked));

                element = document.createElement('label');
                element.setAttribute('for', 'Snapping.' + layer.id);
                element.innerHTML = layer.name;
                OpenLayers.Event.observe(element, 'click', OpenLayers.Function.bind(function (event) {
                    // Allow to check checkbox by clicking its label even when drawing tools are active
                    OpenLayers.Event.stop(event, true);
                }, this));
                content.appendChild(element);

                this.layerListDiv.appendChild(content);
            }
        }
    },

    /**
     * Enables or disables a layer for snapping
     * @param {OpenLayers.Layer} layer
     * @param {Boolean} snappingEnabled Set TRUE to enable snapping to this layer's objects
     */
    setLayerSnapping: function (layer, snappingEnabled) {
        if (!snappingEnabled) {
        		this.snappingLayers.splice(this.snappingLayers.indexOf(layer), 1);
            
        } else {
        	this.addSnappingLayer(layer);
        }
    },

    addSnappingLayer:function(layer){
    	var snapLayerLen = this.snappingLayers.length;
    	var bInclude = false;
    	for(var i = 0; i < snapLayerLen ; i++) {
    		var oLayer = this.snappingLayers[i];
    		if(oLayer.name == layer.name) {
    			bInclude = true;
    			break;
    		} 
    	}
    	if(!bInclude)
    		this.snappingLayers.push(layer);
    },
    
    changeSnapping: function () {
    	
    	/*var nDist = $("#txtSnapDist").val();
		var sUnit = $("#selSnapUnit option:selected").text().toUpperCase();
		if(sUnit === "METER"){
			var nScale = map.getResolution();
			nDist *= parseFloat(1/nScale).toFixed();
		}
		
		nDist = nDist || 1;
		
        this.tolerance = nDist;*/
        
        //console.log("snap distance : " + nDist);
        
        if (this.snappingLayers.length > 0) {

            this.snapping.deactivate();
            var targets = [];
            for (var i = 0; i < this.snappingLayers.length; i++) {
                targets.push({
                    layer: this.snappingLayers[i],
                    tolerance: this.tolerance
                });
            }
            this.snapping = new OpenLayers.Control.Snapping({
                layer: this.layer,
                targets: targets
            });
            for (var i = 0; i < targets.length; i++) {
                var layer = targets[i].layer;
                if (layer.visibility === false) {
                    for (var j=0, len=layer.strategies.length; j<len; j++) {
                        if (layer.strategies[j].CLASS_NAME === 'OpenLayers.Strategy.BBOX') {
                            layer.strategies[j].update({force: true});
                        }
                    }
                }
            }
            this.snapping.activate();
        } else {
            if (this.snapping.active) {
                this.snapping.deactivate();
                this.snapping.targets = null;
            }
        }
        if (!this.snapping.active) this.deactivate();
    },

    setMap: function (map) {
        OpenLayers.Control.Button.prototype.setMap.apply(this, arguments);

        if (this.snappingGuideLayer === null) {
            this.snappingGuideLayer = this.createSnappingGuideLayer();
        }
    },

    /**
     * Adds a layer for guidelines to the map
     * @return {OpenLayers.Editor.Layer.Snapping}
     */
    createSnappingGuideLayer: function () {
        var snappingGuideLayer = new OpenLayers.Editor.Layer.Snapping(OpenLayers.i18n('Snapping Layer'), {
            visibility: false
        });
        this.map.addLayer(snappingGuideLayer);

        return snappingGuideLayer;
    },

    CLASS_NAME: "GSnappingSettings"
});