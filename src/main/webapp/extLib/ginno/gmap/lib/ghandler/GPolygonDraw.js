GPolygonDraw = OpenLayers.Class(OpenLayers.Handler.Polygon, {

	attributes : null,

	finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if(cancel || !this.persist) {
            this.destroyFeature();
        }
    },

	CLASS_NAME: "GPolygonDraw"
});