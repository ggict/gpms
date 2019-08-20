/****************************************************************
 *
 * 파일명 : OpenLayersEditCustom.js
 * 설  명 : OpenLayersEditor 커스터마이징 JavaScript
 *          OpenLayersEditor버전업을 위한 별도 구성
 ****************************************************************
 *          버전업시 필요한 작업
 *          1. loader.js에 본 파일 경로 추가
 *          2. 본 파일에 정의한 클래스 및 함수 변경여부 확인 후 수정
 *          3. 스타일(geosilk.css -> geosilk_custom.css에 변경내역 수정-주로 editorpanel class 명칭.)
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2015.07.27      최재훈       1.0             최초생성
 * 2015.08.26      최재훈       1.0             activate처리
 * 2015.08.27      윤은희       1.1             GDrawPath,GDrawPoint Class 추가
 */


/**********************************************
 * 클래스명 : OpenLayers.CustomEditor BASIC
 * 설  명 : 커스터마이징 Editor .
 * 인  자 : -
 * 사용법 : -
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.07.27    최재훈      신규작업
 *
 */

GEditor = OpenLayers.Class(OpenLayers.Editor,{
	/**
     * Property: map
     * {<OpenLayers.Map>} this gets set in the constructor.
     */
    map: null,

    /**
     * Property: id
     * {String} Unique identifier for the Editor.
     */
    id: null,

    /**
     * Property: editLayer
     * {<OpenLayers.Layer.Vector>} Editor workspace.
     */
    editLayer: null,

    /**
     * Property: editorPanel
     * {<OpenLayers.Editor.Control.EditorPanel>} Contains icons for active controls
     *     and gets set by startEditMode() and unset by stopEditMode().
     */
    editorPanel: null,

    /**
     * Property: editMode
     * {Boolean} The editor is active.
     */
    editMode: false,

    /**
     * Property: dialog
     * {<OpenLayers.Editor.Control.Dialog>} ...
     */
    dialog: null,

    /**
     * Property: status
     * @type {function(string, string)} Function to display states, receives status type and message
     */
    showStatus: function (status, message) {
        if (status === 'error') {
            alert(message);
        }
    },

    /**
     * Property: activeControls
     * {Array} ...
     */
    activeControls: [],

    /**
     * Property: editorControls
     * {Array} Contains names of all available editor controls. In particular
     *   this information is needed by this EditorPanel.
     */
    editorControls: ['CleanFeature', 'DeleteFeature', 'DeleteAllFeatures', 'Dialog', 'DrawHole', 'DrawRegular',
        'DrawPolygon', 'DrawPath', 'DrawPoint', 'DrawText', 'EditorPanel', 'ImportFeature',
        'MergeFeature', 'SnappingSettings', 'SplitFeature', 'CADTools',
        'TransformFeature', 'ContextMenu'],

    /**
     * Geometry types available for editing
     * {Array}
     */
    featureTypes: ['text', 'point', 'path', 'polygon', 'regular'],

    /**
     * Property: sourceLayers
     * {Array} ...
     */
    sourceLayers: [],

    /**
     * Property: parameters
     * {Object} ...
     */
    params: {},

    geoJSON: new OpenLayers.Format.GeoJSON(),

    /**
     * Property: options
     * {Object} ...
     */
    options: {},

    /**
     * Property: URL of processing service.
     * {String}
     */
    oleUrl: '',

    /**
     * Instantiated controls
     * {Objects}
     */
    controls: {},

    /**
     * Property: undoRedoActive
     * {Boolean} Indicates if the UndoRedo control is active. Only read on
     *     initialization right now. Default is true.
     */
    undoRedoActive: true,

    /**
     * @param {OpenLayers.Map} map A map that shall be equipped with an editor; can be left undefined in which case a map is created.
     * @param {Object} options
     */
    initialize: function (map, options) {

        OpenLayers.Util.extend(this, options);

        if (map instanceof OpenLayers.Map) {
            this.map = map;
        } else {
            this.map = new OpenLayers.Map();
        }

        if (!options) {
            options = {};
        }

        if (!options.dialog) {
            this.dialog = new OpenLayers.Editor.Control.Dialog();
            this.map.addControl(this.dialog);
        }

        this.id = OpenLayers.Util.createUniqueID('OpenLayers.Editor_');

        if (options.editLayer) {
            this.editLayer = options.editLayer;
        } else {
            this.editLayer = new OpenLayers.Layer.Vector('Editor', {
                displayInLayerSwitcher: false
            });
        }
        if (options.styleMap) {
            this.editLayer.styleMap = options.styleMap;
        } else {
        	// MAP_EDITOR.oStyleVectorLayer에서 정의한 스타일로 지정 - ehyun.
            this.editLayer.styleMap = editor.getStyleMapStyleLayer();
        }

        var selectionContext = {
            editor: this,
            layer: this.editLayer,
            controls: [
                'OpenLayers.Editor.Control.DeleteFeature',
                'OpenLayers.Editor.Control.CleanFeature',
                'OpenLayers.Editor.Control.MergeFeature',
                'OpenLayers.Editor.Control.SplitFeature'
            ]};
        this.editLayer.events.register('featureselected', selectionContext, this.selectionChanged);
        this.editLayer.events.register('featureunselected', selectionContext, this.selectionChanged);

        for (var i = 0, il = this.featureTypes.length; i < il; i++) {
            if (this.featureTypes[i] == 'polygon') {
                this.activeControls.push('GDrawPolygon');
            }
            else if (this.featureTypes[i] == 'path') {
                this.activeControls.push('GDrawPath');
            }
            else if (this.featureTypes[i] == 'point') {
                this.activeControls.push('GDrawPoint');
            }
            else if (this.featureTypes[i] == 'regular') {
                this.activeControls.push('DrawRegular');
            }
            else if (this.featureTypes[i] == 'text') {
                this.activeControls.push('DrawText');
            }
        }

        for (var i = 0, il = this.sourceLayers.length; i < il; i++) {
            var selectionContext = {
                editor: this,
                layer: this.sourceLayers[i],
                controls: ['OpenLayers.Editor.Control.ImportFeature']
            };
            this.sourceLayers[i].events.register('featureselected', selectionContext, this.selectionChanged);
            this.sourceLayers[i].events.register('featureunselected', selectionContext, this.selectionChanged);
            this.sourceLayers[i].styleMap = new OpenLayers.StyleMap({
                'default': new OpenLayers.Style({
                    fillColor: '#0c0',
                    fillOpacity: 0.8,
                    strokeColor: '#070',
                    strokeWidth: 2,
                    graphicZIndex: 1,
                    pointRadius: 5
                }),
                'select': new OpenLayers.Style({
                    fillColor: '#fc0',
                    strokeColor: '#f70',
                    graphicZIndex: 2
                }),
                'temporary': new OpenLayers.Style({
                    fillColor: '#fc0',
                    fillOpacity: 0.8,
                    strokeColor: '#f70',
                    strokeWidth: 2,
                    graphicZIndex: 2,
                    pointRadius: 5
                })
            });
            this.map.addLayer(this.sourceLayers[i]);
        }

        this.map.editor = this;
        this.map.addLayer(this.editLayer);
        this.map.addControl(new OpenLayers.Editor.Control.LayerSettings(this));

        if (this.undoRedoActive) {
            this.map.addControl(new OpenLayers.Editor.Control.UndoRedo(this.editLayer));
        }

        this.addEditorControls();

        return this;
    },

    /**
     * Enable or disable controls that depend on selected features.
     *
     * Requires an active SelectFeature control and the following context variables:
     * - editor: this
     * - layer: The layer with selected features.
     * - controls: An array of class names.
     */
    selectionChanged: function () {
        var selectFeature = this.editor.editorPanel.getControlsByClass('OpenLayers.Control.SelectFeature')[0];

        if (this.layer.selectedFeatures.length > 0 && selectFeature && selectFeature.active) {
            // enable controls
            for (var ic = 0, lic = this.controls.length; ic < lic; ic++) {
                var control = this.editor.editorPanel.getControlsByClass(this.controls[ic])[0];
                if (control) {
                    OpenLayers.Element.removeClass(control.panel_div, 'oleControlDisabled');
                }
            }
        } else {
            // disable controls
            for (var ic = 0, lic = this.controls.length; ic < lic; ic++) {
                var control = this.editor.editorPanel.getControlsByClass(this.controls[ic])[0];
                if (control) {
                    OpenLayers.Element.addClass(control.panel_div, 'oleControlDisabled');
                }
            }
        }

        this.editor.editorPanel.redraw();
    },

    /**
     * Makes the toolbar appear and allows editing
     */
    startEditMode: function () {
        this.editMode = true;
        this.editorPanel.activate();
    },

    /**
     * Hides the toolbar and prevents editing
     */
    stopEditMode: function () {
        this.editMode = false;
        this.editorPanel.deactivate();
    },

    /**
     * Initializes configured controls and shows them
     */
    addEditorControls: function () {
        var control = null, controls = [];
        var editor = this;

        for (var i = 0, len = editor.activeControls.length; i < len; i++) {
            control = editor.activeControls[i];

            if (OpenLayers.Util.indexOf(editor.editorControls, control) > -1) {

        		controls.push(new OpenLayers.Editor.Control[control](
                        editor.editLayer,
                        OpenLayers.Util.extend({
                        	id : control
                        }, editor.options[control])

                ));

            }

            switch (control) {

                case 'Separator':
                    controls.push(new OpenLayers.Control.Button({
                    	id: control,
                        displayClass: 'olControlSeparator'

                    }));
                    break;

                case 'Navigation':
                    controls.push(new OpenLayers.Control.Navigation(
                            OpenLayers.Util.extend(
                                    {
                                    id: control,
                                    title: OpenLayers.i18n('oleNavigation')},
                                    editor.options.Navigation)
                    ));
                    break;

                case 'CustomDragFeature':
                    controls.push(new GDragFeature(editor.editLayer,
                            OpenLayers.Util.extend(
                            		{
                            			id: control
                            		}, editor.options.DragFeature)
                    ));
                    break;

                case 'CustomModifyFeature':
                    controls.push(new GModifyFeature(editor.editLayer,
                            OpenLayers.Util.extend(
                                    {
	                                    id: control,
	                                    title: OpenLayers.i18n('oleModifyFeature')
                                    }, editor.options.ModifyFeature)
                    ));
                    break;

                case 'CustomTransformFeature':
                    controls.push(new GTransformFeature(editor.editLayer,
                            OpenLayers.Util.extend(
                                    {
                                    	id: control
                                    }, editor.options.TransformFeature)
                    ));
                    break;

                case 'GSnappingSettings':
                    controls.push(new GSnappingSettings(editor.editLayer,
                            OpenLayers.Util.extend(
                                    {
                                    	id: control
                                    }, editor.options.SnappingSettings)
                    ));
                    break;

                case 'GDrawPoint':
                    controls.push(new GDrawPoint(editor.editLayer,
                            OpenLayers.Util.extend(
                                    {
                                    	id: control
                                    }, editor.options.DrawPoint)
                    ));
                    break;

                case 'GDrawPath':
                    controls.push(new GDrawPath(editor.editLayer,
                            OpenLayers.Util.extend(
                                    {
                                    	id: control
                                    }, editor.options.DrawPath)
                    ));
                    break;

                case 'SelectFeature':
                    controls.push(new OpenLayers.Control.SelectFeature(
                            editor.sourceLayers.concat([editor.editLayer]),
                            OpenLayers.Util.extend(
                                    {
                                    	id: control,
                                        title: OpenLayers.i18n('oleSelectFeature'),
                                        clickout: true,
                                        toggle: false,
                                        multiple: false,
                                        hover: false,
                                        toggleKey: "ctrlKey",
                                        multipleKey: "ctrlKey",
                                        box: true
                                    },
                                    editor.options.SelectFeature)
                    ));
                    break;

                case 'DownloadFeature':
                    controls.push(new OpenLayers.Editor.Control.DownloadFeature(editor.editLayer,
                            OpenLayers.Util.extend({
                            	id: control
                            	}, this.DownloadFeature)
                    ));
                    break;

                case 'UploadFeature':
                    controls.push(new OpenLayers.Editor.Control.UploadFeature(editor.editLayer,
                            OpenLayers.Util.extend({
                            	id: control
                            	}, this.UploadFeature)
                    ));
                    break;
            }

            // Save instance in editor's controls mapping
            this.controls[control] = controls[controls.length - 1];
        }

        // Add toolbar to map
        this.editorPanel = this.createEditorPanel(controls);
        editor.map.addControl(this.editorPanel);
    },

    /**
     * Adds a control to the editor and its panel
     * @param {OpenLayers.Editor.Control} control
     */
    addEditorControl: function (control) {
        this.controls[control.CLASS_NAME] = control;
        this.editorPanel.addControls([control]);
        this.map.addControl(control);
    },

    /**
     * Instantiates the container which displays the tools.
     * To be called by OLE only and intended to be overridden by subclasses that want to display something else instead of the default toolbar
     * @param {Array.<OpenLayers.Control>} controls Editing controls
     * @return {OpenLayers.Editor.Control.EditorPanel} Widget to display editing tools
     */
    createEditorPanel: function (controls) {

        // remove controls from context menu
        if (this.controls['ContextMenu']) {
            var ctrls = this.controls['ContextMenu'].contextMenuControls || [];
            var i = ctrls.length;
            while (i--) {
                var pos = controls.indexOf(this.controls[ctrls[i]]);
                if (~pos) {
                    controls.splice(pos, 1);
                }
            }

            controls.splice(controls.indexOf(this.controls['ContextMenu']), 1);
        }

        var editorPanel = new OpenLayers.Editor.Control.EditorCustomPanel(this);
        editorPanel.addControls(controls);
        return editorPanel;
    },

    status: function (options) {
        if (options.type == 'error') {
            alert(options.content);
        }
    },

    /**
     * Destroys existing features and loads the provided one into editor
     * @param {Array.<OpenLayers.Feature.Vector>} features
     */
    loadFeatures: function (features) {
        this.editLayer.destroyFeatures();
        if (features) {
            this.editLayer.addFeatures(features);
            this.map.zoomToExtent(this.editLayer.getDataExtent());
        }
    },

    /**
     * Callback to update selected feature with result of server side processing
     */
    requestComplete: function (response) {
        var responseJSON = new OpenLayers.Format.JSON().read(response.responseText);
        this.map.editor.stopWaiting();
        if (!responseJSON) {
            this.showStatus('error', OpenLayers.i18n('oleNoJSON'))
        } else if (responseJSON.error) {
            this.showStatus('error', responseJSON.message)
        } else {
            if (responseJSON.params) {
                OpenLayers.Util.extend(this.params, responseJSON.params);
            }
            if (responseJSON.geo) {
                var geo = this.geoJSON.read(responseJSON.geo);
                this.editLayer.removeFeatures(this.editLayer.selectedFeatures);
                this.editLayer.addFeatures(this.toFeatures(geo));
                this.editLayer.events.triggerEvent('featureselected');
            }
        }
    },

    /**
     * Flattens multipolygons and returns a list of their features
     * @param {Object|Array} multiPolygon Geometry or list of geometries to flatten. Geometries can be of types
     *     OpenLayers.Geometry.MultiPolygon, OpenLayers.Geometry.Collection,
     *     OpenLayers.Geometry.Polygon.
     * @return {Array} List for features of type OpenLayers.Feature.Vector.
     */
    toFeatures: function (multiPolygon) {
        if (multiPolygon === null || typeof(multiPolygon) !== 'object') {
            throw new Error('Parameter does not match expected type.');
        }
        var features = [];
        if (!(multiPolygon instanceof Array)) {
            multiPolygon = [multiPolygon];
        }
        for (var i = 0, li = multiPolygon.length; i < li; i++) {
            if (multiPolygon[i].geometry.CLASS_NAME === 'OpenLayers.Geometry.MultiPolygon' ||
                    multiPolygon[i].geometry.CLASS_NAME === 'OpenLayers.Geometry.Collection') {
                for (var j = 0, lj = multiPolygon[i].geometry.components.length; j < lj; j++) {
                    features.push(new OpenLayers.Feature.Vector(
                            multiPolygon[i].geometry.components[j]
                    ));
                }
            } else if (multiPolygon[i].geometry.CLASS_NAME === 'OpenLayers.Geometry.Polygon') {
                features.push(new OpenLayers.Feature.Vector(multiPolygon[i].geometry));
            }
        }
        return features;
    },

    toMultiPolygon: function (features) {
        var components = [];
        for (var i = 0, l = features.length; i < l; i++) {
            if (features[i].geometry.CLASS_NAME === 'OpenLayers.Geometry.Polygon') {
                components.push(features[i].geometry);
            }
        }
        return new OpenLayers.Geometry.MultiPolygon(components);
    },

    startWaiting: function (panel_div) {
        OpenLayers.Element.addClass(panel_div, 'olEditorWaiting');
        OpenLayers.Element.addClass(this.map.div, 'olEditorWaiting');
        this.waitingDiv = panel_div;
    },

    stopWaiting: function () {
        OpenLayers.Element.removeClass(this.waitingDiv, 'olEditorWaiting');
        OpenLayers.Element.removeClass(this.map.div, 'olEditorWaiting');
    },

    isArray: function(o) {
	     return Object.prototype.toString.call(o) == '[object Array]';
	},

	getControlById:function(control){
		for(var ctrl in this.controls){
			if(ctrl == control){
				return this.controls[ctrl];
				break;
			}
    	}
	},

	deActivateAllEditControls: function(){
		for(var ctrl in this.controls){
    		c = this.controls[ctrl];
            c.deactivate();
    	}
	},

    activateControls: function (control_list){

    	map.deActiveAllControls();

    	if(this.isArray(control_list)){

    		for(var j = 0 ; j < control_list.length ; j++) {
    			var control = this.controls[control_list[j]];

	            if (control.type == OpenLayers.Control.TYPE_BUTTON) {
	                control.trigger();
	                return;
	            }

	            if (control.type == OpenLayers.Control.TYPE_TOGGLE) {
	                if (control.active) {
	                    control.deactivate();
	                } else {
	                    control.activate();
	                }
	                return;
	            }

	            if (this.allowDepress && control.active) {
	                control.deactivate();
	            } else {

	                var c;
	                for(var ctrl in this.controls){
                		c = this.controls[ctrl];
                		if (c != control && control_list.indexOf(c.id) == -1 ) {
 		                        c.deactivate();
 		                }
                	}
	               /* if(this.controls.length){
	                	for (var i=0, len=this.controls.length; i<len; i++) {
		                    c = this.controls[i];
		                    if (c != control &&
		                       (c.type === OpenLayers.Control.TYPE_TOOL || c.type == null) && control_list.indexOf(c.id) == -1 ) {
		                        c.deactivate();
		                    }
		                }
	                }else {
	                	for(var ctrl in this.controls){
	                		c = this.controls[ctrl];
	                		if (c != control && control_list.indexOf(c.id) == -1 ) {
	 		                        c.deactivate();
	 		                }
	                	}
	                }*/

	                control.activate();
	            }
    		}

    	}else{
    		var control = this.getControl(control_list);

    		if (!this.active) { return false; }
            if (control.type == OpenLayers.Control.TYPE_BUTTON) {
                control.trigger();
                return;
            }

            if (control.type == OpenLayers.Control.TYPE_TOGGLE) {
                if (control.active) {
                    control.deactivate();
                } else {
                    control.activate();
                }
                return;
            }

            if (this.allowDepress && control.active) {
                control.deactivate();
            } else {

                var c;
                var c;
                for(var ctrl in this.controls){
            		c = this.controls[ctrl];
            		if (c != control && control_list.indexOf(c.id) == -1 ) {
		                        c.deactivate();
		                }
            	}

                /*if(this.controls.length){
                	for (var i=0, len=this.controls.length; i<len; i++) {
	                    c = this.controls[i];
	                    if (c != control &&
	                       (c.type === OpenLayers.Control.TYPE_TOOL || c.type == null) && control_list.indexOf(c.id) == -1 ) {
	                        c.deactivate();
	                    }
	                }
                }else {
                	for(var ctrl in this.controls){
                		c = this.controls[ctrl];
                		if (c != control && control_list.indexOf(c.id) == -1 ) {
 		                        c.deactivate();
 		                }
                	}
                }*/

                control.activate();
            }
    	}

    },
    CLASS_NAME: 'GEditor'
});








/**********************************************
 * 클래스명 : OpenLayers.Editor.Control.EditorCustomPanel
 * 설  명 : 커스터마이징 Editor Panel .
 * 인  자 : -
 * 사용법 : -
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.08.03    최재훈      신규작업
 *
 */
OpenLayers.Editor.Control.EditorCustomPanel = OpenLayers.Class(OpenLayers.Editor.Control.EditorPanel, {
    /*
     * {boolean} Whether to show by default. Leave value FALSE and show by starting editor's edit mode.
     */
    autoActivate: false,

    /**
     * Constructor: OpenLayers.Editor.Control.EditorPanel
     * Create an editing toolbar for a given editor.
     *
     * Parameters:
     * editor - {<OpenLayers.Editor>}
     * options - {Object}
     */
    initialize: function (editor, options) {
        OpenLayers.Control.Panel.prototype.initialize.apply(this, [options]);
    },

    draw: function() {
        OpenLayers.Control.Panel.prototype.draw.apply(this, arguments);
        if (!this.active) {
            this.div.style.display = 'none';
        }
        return this.div;
    },

    redraw: function(){
        if (!this.active) {
            this.div.style.display = 'none';
        }

        OpenLayers.Control.Panel.prototype.redraw.apply(this, arguments);

        if (this.active) {
            this.div.style.display = '';
        }
    },


    /**********************************************
     * 함수명 : activateControl
     * 설  명 : EditPanel상의 control클릭 시 수행.
     * 인  자 : 활성화할 control
     * 사용법 : activateControl(control)
     *
     * 수정일        수정자      수정내용
     * ------        ------     -------------------
     * 2015.07.27    최재훈      신규작업
     *
     */
    activateControl: function (control) {

    	//alert('OpenLayers.Editor.Control.EditorPanel.Custom - activateControl');
    	map.deActiveAllControls();

        if (!this.active) { return false; }
        if (control.type == OpenLayers.Control.TYPE_BUTTON) {
            control.trigger();
            return;
        }
        if (control.type == OpenLayers.Control.TYPE_TOGGLE) {
            if (control.active) {
                control.deactivate();
            } else {
                control.activate();
            }
            return;
        }
        if (this.allowDepress && control.active) {
            control.deactivate();
        } else {
            var c;
            for (var i=0, len=this.controls.length; i<len; i++) {
                c = this.controls[i];
                if (c != control &&
                   (c.type === OpenLayers.Control.TYPE_TOOL || c.type == null)) {
                    c.deactivate();
                }
            }
            control.activate();
        }
    },
    CLASS_NAME: 'OpenLayers.Editor.Control.EditorCustomPanel'
});








