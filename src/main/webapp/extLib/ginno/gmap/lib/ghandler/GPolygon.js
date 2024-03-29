GPolygon = OpenLayers.Class(OpenLayers.Handler.Polygon, {

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

    mousedown: function(evt) {
        // ignore double-clicks
        if (this.lastDown && this.lastDown.equals(evt.xy)) {
            return false;
        }
        if(this.lastDown == null) {
            if(this.persist) {
                this.destroyFeature();
            }
            this.createFeature(evt.xy);
        } else if((this.lastUp == null) || !this.lastUp.equals(evt.xy)) {
            this.addPoint(evt.xy);
        }
        this.mouseDown = true;
        this.lastDown = evt.xy;
        this.drawing = true;

        //mousedown callback 추가
        this.callback("mousedown", [this.point.geometry, this.getGeometry()]);

        return false;
    },

    mouseup: function (evt) {
        this.mouseDown = false;
        if(this.drawing) {
            if(this.freehandMode(evt)) {
                this.removePoint();
                this.finalize();
            } else {
                if(this.lastUp == null) {
                   this.addPoint(evt.xy);
                }
                this.lastUp = evt.xy;
            }

            //mouseup callback 추가
            this.callback("mouseup", [this.point.geometry, this.getGeometry()]);

            return false;
        }

        //mouseup callback 추가
        this.callback("mouseup", [this.point.geometry, this.getGeometry()]);

        return true;
    },

	CLASS_NAME: "GPolygon"
});