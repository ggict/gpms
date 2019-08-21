GMemoTool = OpenLayers.Class({
    map: null,
    layer: null,
    controls: null,
    defaultStyle: {
        "Point": {
            featureType: "Point",
            pointRadius: 6,
            graphicName: "cross",
            fillColor: "#ffffff",
            fillOpacity: 1,
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeColor: "#333333"
        },
        "Line": {
            featureType: "Line",
            strokeColor: "#000000",
            strokeWidth: 2,
            strokeOpacity: 1,
            strokeDashstyle: "solid",
            strokeLinecap: "butt"
        },
        "Polygon": {
            featureType: "Polygon",
            fillColor: "#e0e0e0",
            fillOpacity: 1,
            strokeColor: "#000000",
            strokeWidth: 2,
            strokeOpacity: 1,
            strokeDashstyle: "solid"
        },
        "Image": {
            featureType: "Image",
            graphicOpacity: 1,
            externalGraphic: "http://" + $(location).attr("host") + "/images/usolver/com/map/marker/edit.png",
            graphicWidth: 32,
            graphicHeight: 32
        },
        "Text": {
            featureType: "Text",
            pointRadius: 4,
            graphicName: "square",
            fillColor: "white",
            fillOpacity: 1,
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeColor: "#333333"
        }
    },
    initialize: function(map, options) {
        var control = this;
        this.map = map;
        this.layer = new GVector("GMemoToolLayer", {
            styleMap: this.getStyleMap(),
            eventListeners: options.eventListeners
        });
        OpenLayers.Util.extend(this, options);
        map.addLayer(this.layer);
        this.controls = [new OpenLayers.Control.SelectFeature(this.layer, {
                id: "drawSelectMemo",
                clickout: true,
                toggleKey: "ctrlKey",
                multipleKey: "shiftKey",
                box: true
            }), new OpenLayers.Control.ModifyFeature(this.layer, {
                id: "drawEditMemo",
                mode: OpenLayers.Control.ModifyFeature.RESHAPE | OpenLayers.Control.ModifyFeature.DRAG
            }), new GDrawFeature(this.layer, GPoint, {
                id: "drawMemo",
                handlerOptions: {
                    attributes: this.defaultStyle["Image"]
                }
            })/*,new GDrawFeature(this.layer, GPoint, {
                id: "drawMemoText",
                handlerOptions: {
                    attributes: this.defaultStyle["Text"]
                }
            })*/
        ];
        map.addControls(this.controls);
        if (options) {
            if (options.onFeatureAdded) this.layer.events.register("featureadded", this, options.onFeatureAdded);
            if (options.onModificationStart) map.getControl("drawEditMemo").onModificationStart = options.onModificationStart;
            if (options.onSelect) map.getControl("drawSelectMemo").onSelect = options.onSelect;
            if (options.onDeactivate) map.getControl("drawEditMemo").onDeactivate = options.onDeactivate;
            if (options.onUnselectAll);
        }
    },
    getStyleMap: function() {
        var style = new OpenLayers.Style(null);
        style.addRules([new OpenLayers.Rule({
            symbolizer: {
                pointRadius: "${pointRadius}",
                graphicName: "${graphicName}",
                fillColor: "${fillColor}",
                fillOpacity: "${fillOpacity}",
                strokeWidth: "${strokeWidth}",
                strokeOpacity: "${strokeOpacity}",
                strokeColor: "${strokeColor}"
            },
            filter: new OpenLayers.Filter.Comparison({
                type: "==",
                property: "featureType",
                value: "Point"
            })
        }), new OpenLayers.Rule({
            symbolizer: {
                strokeColor: "${strokeColor}",
                strokeWidth: "${strokeWidth}",
                strokeOpacity: "${strokeOpacity}",
                strokeDashstyle: "${strokeDashstyle}",
                strokeLinecap: "${strokeLinecap}"
            },
            filter: new OpenLayers.Filter.Comparison({
                type: "==",
                property: "featureType",
                value: "Line"
            })
        }), new OpenLayers.Rule({
            symbolizer: {
                fillColor: "${fillColor}",
                fillOpacity: "${fillOpacity}",
                strokeColor: "${strokeColor}",
                strokeWidth: "${strokeWidth}",
                strokeOpacity: "${strokeOpacity}",
                strokeDashstyle: "${strokeDashstyle}"
            },
            filter: new OpenLayers.Filter.Comparison({
                type: "==",
                property: "featureType",
                value: "Polygon"
            })
        }), new OpenLayers.Rule({
            symbolizer: {
                pointRadius: "${pointRadius}",
                graphicName: "${graphicName}",
                fillColor: "${fillColor}",
                fillOpacity: "${fillOpacity}",
                strokeWidth: "${strokeWidth}",
                strokeOpacity: "${strokeOpacity}",
                strokeColor: "${strokeColor}"
            },
            filter: new OpenLayers.Filter.Comparison({
                type: "==",
                property: "featureType",
                value: "Text"
            })
        }), new OpenLayers.Rule({
            symbolizer: {
                graphicOpacity: "${graphicOpacity}",
                externalGraphic: "${externalGraphic}",
                graphicWidth: "${graphicWidth}",
                graphicHeight: "${graphicHeight}"
            },
            filter: new OpenLayers.Filter.Comparison({
                type: "==",
                property: "featureType",
                value: "Image"
            })
        }), new OpenLayers.Rule({
            symbolizer: {
                strokeColor: "#0033ff",
                strokeOpacity: .7,
                strokeWidth: 2,
                fillColor: "#0033ff",
                fillOpacity: 0,
                graphicZIndex: 2,
                cursor: "pointer"
            },
            elseFilter: true
        })]);
        var styleMap = new OpenLayers.StyleMap({
            "default": style
        });
        return styleMap
    },
    deleteFeature: function() {
        this.removeTextPopup();
        var features = [];
        if (this.layer.selectedFeatures.length >
            0)
            for (var i in this.layer.selectedFeatures) features.push(this.layer.selectedFeatures[i]);
        for (var i in this.map.controls)
            if (this.map.getControl("drawSelect") && this.map.getControl("drawSelect").active) this.map.activeControls("drawSelect");
            else if (this.map.getControl("drawEdit") && this.map.getControl("drawEdit").active) this.map.activeControls("drawEdit");
        else if (this.map.getControl("drawEditPoint") && this.map.getControl("drawEditPoint").active) this.map.activeControls("drawEditPoint");
        if (features.length >
            0) this.layer.removeFeatures(features)
    },
    removeTextPopup: function() {
        var id;
        $(".olControlDrawText").each(function() {
            if ($(this).hasClass("on")) id = $(this).parent().parent().parent().attr("id")
        });
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].id == id) this.map.removePopup(this.map.popups[i])
    },
    getSelectFeature: function() {
        var id;
        if (this.map.getLayerByName("GMemoToolLayer") && this.map.getLayerByName("GMemoToolLayer").selectedFeatures && this.map.getLayerByName("GMemoToolLayer").selectedFeatures.length >
            0) return this.map.getLayerByName("GMemoToolLayer").selectedFeatures[0];
        $(".olControlDrawText").each(function() {
            if ($(this).hasClass("on")) id = $(this).attr("id")
        });
        for (var i in this.map.popups)
            if (this.map.popups[i].attributes.seq == id.replace("drawText", "")) return this.map.popups[i];
        return false
    },
    setTextAttr: function(feature) {
        var seq = feature.attributes.seq;
        $("#drawText" + seq).css("font-family", feature.attributes.fontFamily);
        $("#drawText" + seq).css("font-size", feature.attributes.fontSize);
        $("#drawText" +
            seq).css("color", feature.attributes.fontColor);
        feature.updateSize()
    },
    addTextPopup: function(popup) {
        var seq = popup.attributes.seq;
        var str = popup.attributes.text;
        str = str.replace(/\x20/gi, "&nbsp;");
        str = str.replace(/\x0D\x0A/gi, "<br/>");
        str = str.replace(/\x0D/gi, "<br/>");
        str = str.replace(/\n/gi, "<br/>");
        var contentHtml = "";
        contentHtml += "<div class='olControlDrawText off' id='drawText" + seq + "'>" + str + "</div>";
        var pop = new GPopup("drawPopup" + seq, popup.getLonLat(), null, contentHtml, new OpenLayers.Pixel(0, 0));
        this.map.addPopup(pop);
        pop.type = "draw";
        pop.attributes = popup.attributes;
        this.setTextAttr(pop)
    },
    removeAllFeatures: function() {
        this.layer.removeAllFeatures();
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].type == "draw") this.map.removePopup(this.map.popups[i])
    },
    redraw: function() {
        this.layer.redraw()
    },
    CLASS_NAME: "GMemoTool"
});