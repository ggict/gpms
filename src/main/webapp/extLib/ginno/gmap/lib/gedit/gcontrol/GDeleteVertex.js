GDeleteVertex = OpenLayers.Class(OpenLayers.Control.ModifyFeature, {
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
        var control = this;

        // configure the select control
        var selectOptions = {
            geometryTypes: this.geometryTypes,
            clickout: this.clickout,
            toggle: this.toggle,
            onBeforeSelect: this.beforeSelectFeature,
            onSelect: this.selectFeature,
            onUnselect: this.unselectFeature,
            scope: this
        };
        if(this.standalone === false) {
            this.selectControl = new OpenLayers.Control.SelectFeature(
                layer, selectOptions
            );
        }
        
        var dragCallbacks = {};
        // configure the drag control
        var dragOptions = {
            geometryTypes: ["OpenLayers.Geometry.Point"],
            onStart: function(feature, pixel) {
                control.dragStart.apply(control, [feature, pixel]);
            },
            onDrag: function(feature, pixel) {
                control.dragVertex.apply(control, [feature, pixel]);
            },
            onComplete: function(feature) {
                control.dragComplete.apply(control, [feature]);
            },
            featureCallbacks: {
                over: function(feature) {
                    /**
                     * In normal mode, the feature handler is set up to allow
                     * dragging of all points.  In standalone mode, we only
                     * want to allow dragging of sketch vertices and virtual
                     * vertices - or, in the case of a modifiable point, the
                     * point itself.
                     */
                    if(control.standalone !== true || feature._sketch ||
                       control.feature === feature) {
                        control.dragControl.overFeature.apply(
                            control.dragControl, [feature]);
                    }
                },
                // === Override is here ===
                // register a click callback as well to trigger a custom vector
                // layer event : "vertexclicked".
                click: function(feature) {
                    this.layer.events.triggerEvent(
                        "vertexclicked", {feature: feature}
                    );
                }
            }
        };
        this.dragControl = new OpenLayers.Control.DragFeature(
            layer, dragOptions
        );

        // configure the keyboard handler
        var keyboardOptions = {
            keydown: this.handleKeypress
        };
        
        this.handlers = {
            keyboard: new OpenLayers.Handler.Keyboard(this, keyboardOptions) 
        };
    }, 
    activate: function() {
        return ((this.standalone || this.selectControl.activate()) &&
                this.handlers.keyboard.activate() &&
                OpenLayers.Control.prototype.activate.apply(this, arguments));
    },
    deactivate: function() {
        var deactivated = false;
        // the return from the controls is unimportant in this case
        if(OpenLayers.Control.prototype.deactivate.apply(this, arguments)) {
            this.layer.removeFeatures(this.vertices, {silent: true});
            this.layer.removeFeatures(this.virtualVertices, {silent: true});
            this.vertices = [];
            this.dragControl.deactivate();
            var feature = this.feature;
            var valid = feature && feature.geometry && feature.layer;
            if(this.standalone === false) {
                if(valid) {
                    this.selectControl.unselect.apply(this.selectControl,
                                                      [feature]);
                }
                this.selectControl.deactivate();
            } else {
                if(valid) {
                    this.unselectFeature(feature);
                }
            }
            this.handlers.keyboard.deactivate();
            deactivated = true;
        }
        return deactivated;
    },
    setMap: function(map) {
        this.standalone || this.selectControl.setMap(map);
        this.dragControl.setMap(map);
        OpenLayers.Control.prototype.setMap.apply(this, arguments);
    },
    CLASS_NAME: "GDeleteVertex"
});