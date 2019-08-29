GMap = OpenLayers.Class(OpenLayers.Map, {
	tempSelectControl : null,
    units: "m",
    numZoomLevels: 11,
    projection: "EPSG:4326",
    displayProjection: "EPSG:4326",
    fractionalZoom: true,
    allOverlays: false,
    initialize: function(div, options) {
        if (GError.debug) this.chkParams(div, options);
        if (arguments.length === 1 && typeof div === "object") {
            options = div;
            div = options && options.div
        }
        this.tileSize = new OpenLayers.Size(OpenLayers.Map.TILE_WIDTH, OpenLayers.Map.TILE_HEIGHT);
        this.paddingForPopups = new OpenLayers.Bounds(15, 15, 15, 15);
        this.theme = OpenLayers._getScriptLocation() +
            "theme/default/style.css";
        OpenLayers.Util.extend(this, options);
        var projCode = this.projection instanceof OpenLayers.Projection ? this.projection.projCode : this.projection;
        OpenLayers.Util.applyDefaults(this, OpenLayers.Projection.defaults[projCode]);
        if (this.maxExtent && !(this.maxExtent instanceof OpenLayers.Bounds)) this.maxExtent = new OpenLayers.Bounds(this.maxExtent);
        if (this.minExtent && !(this.minExtent instanceof OpenLayers.Bounds)) this.minExtent = new OpenLayers.Bounds(this.minExtent);
        if (this.restrictedExtent &&
            !(this.restrictedExtent instanceof OpenLayers.Bounds)) this.restrictedExtent = new OpenLayers.Bounds(this.restrictedExtent);
        if (this.center && !(this.center instanceof OpenLayers.LonLat)) this.center = new OpenLayers.LonLat(this.center);
        this.layers = [];
        this.id = OpenLayers.Util.createUniqueID("OpenLayers.Map_");
        this.div = OpenLayers.Util.getElement(div);
        if (!this.div) {
            this.div = document.createElement("div");
            this.div.style.height = "1px";
            this.div.style.width = "1px"
        }
        OpenLayers.Element.addClass(this.div, "olMap");
        var id =
            this.id + "_OpenLayers_ViewPort";
        this.viewPortDiv = OpenLayers.Util.createDiv(id, null, null, null, "relative", null, "hidden");
        this.viewPortDiv.style.width = "100%";
        this.viewPortDiv.style.height = "100%";
        this.viewPortDiv.className = "olMapViewport";
        this.div.appendChild(this.viewPortDiv);
        this.events = new OpenLayers.Events(this, this.viewPortDiv, null, this.fallThrough, {
            includeXY: true
        });
        if (OpenLayers.TileManager && this.tileManager !== null) {
            if (!(this.tileManager instanceof OpenLayers.TileManager)) this.tileManager = new OpenLayers.TileManager(this.tileManager);
            this.tileManager.addMap(this)
        }
        id = this.id + "_OpenLayers_Container";
        this.layerContainerDiv = OpenLayers.Util.createDiv(id);
        this.layerContainerDiv.style.zIndex = this.Z_INDEX_BASE["Popup"] - 1;
        this.layerContainerOriginPx = {
            x: 0,
            y: 0
        };
        this.applyTransform();
        this.viewPortDiv.appendChild(this.layerContainerDiv);
        this.updateSize();
        if (this.eventListeners instanceof Object) this.events.on(this.eventListeners);
        if (this.autoUpdateSize === true) {
            this.updateSizeDestroy = OpenLayers.Function.bind(this.updateSize, this);
            OpenLayers.Event.observe(window,
                "resize", this.updateSizeDestroy)
        }
        if (this.theme) {
            var addNode = true;
            var nodes = document.getElementsByTagName("link");
            for (var i = 0, len = nodes.length; i < len; ++i)
                if (OpenLayers.Util.isEquivalentUrl(nodes.item(i).href, this.theme)) {
                    addNode = false;
                    break
                }
            if (addNode) {
                var cssNode = document.createElement("link");
                cssNode.setAttribute("rel", "stylesheet");
                cssNode.setAttribute("type", "text/css");
                cssNode.setAttribute("href", this.theme);
                document.getElementsByTagName("head")[0].appendChild(cssNode)
            }
        }
        if (this.controls == null) {
            this.controls = [];
            if (OpenLayers.Control != null) {
                if (GDrag) this.controls.push(new GDrag({
                    id: "drag"                    
                }));
                if (GZoomIn) this.controls.push(new GZoomIn({
                    id: "zoomIn"
                }));
                if (GZoomOut) this.controls.push(new GZoomOut({
                    id: "zoomOut"
                }));
                if (GNavigationHistory) this.controls.push(new GNavigationHistory({
                    id: "naivgationHistory"
                }))
            }
        }
        for (var i = 0, len = this.controls.length; i < len; i++) this.addControlToMap(this.controls[i]);
        this.popups = [];
        this.unloadDestroy = OpenLayers.Function.bind(this.destroy, this);
        OpenLayers.Event.observe(window, "unload", this.unloadDestroy);
        if (options && options.layers) {
            delete this.center;
            delete this.zoom;
            this.addLayers(options.layers);
            if (options.center && !this.getCenter()) this.setCenter(options.center, options.zoom)
        }
        if (this.panMethod) this.panTween = new OpenLayers.Tween(this.panMethod);
        if (this.zoomMethod && this.applyTransform.transform) this.zoomTween = new OpenLayers.Tween(this.zoomMethod)
    },
    chkParams: function(div, options) {
        if (!OpenLayers.Util.getElement(div)) GError.create_obj(this, "id (\uc9c0\ub3c4\ub97c \ud45c\uc2dc\ud560 DIV ID)");
        if (options &&
            typeof options === "object") {
            if (!options.maxExtent) GError.create_obj(this, "options maxExtent (\ucd5c\ub300 \uc601\uc5ed)");
            if (!options.maxResolution) GError.create_obj(this, "options maxResolution (\ucd5c\ub300 \ud574\uc0c1\ub3c4)")
        } else GError.create_obj(this, "options")
    },
    getLayerByName: function(name) {
        var foundLayer = null;
        for (var i = 0, len = this.layers.length; i < len; i++) {
            var layer = this.layers[i];
            if (layer.name == name) {
                foundLayer = layer;
                break
            }
        }
        return foundLayer
    },
    removeLayerByName: function(name) {
        for (var i = 0, len =
                this.layers.length; i < len; i++) {
            var layer = this.layers[i];
            if (layer.name == name) {
                this.removeLayer(layer);
                break
            }
        }
    },
	activeControls: function(controls, editMode) {

		this.deActiveAllControls();
		// this.selectiveDeactivateControl(controls, editMode);
		
		if(typeof controls === "object") {
			if(controls.length && controls.length > 0) {
				for(var i = 0; i < controls.length; i++) {
					this.getControl(controls[i]).activate();
				}
			}
		}
		else {
			this.getControl(controls).activate();
		}
	},
	
	selectiveDeactivateControl: function(controls, editMode) {
	
		if(!editMode)
			this.deActiveAllControls();
		
		else if(CONFIG) {
			
			var aDeactiveControls = CONFIG.fn_get_deactiveControls();
			
			for(var i in this.controls) {
				if(this.controls[i].type != OpenLayers.Control.TYPE_TOGGLE) {
					// if(this.controls[i].CLASS_NAME.indexOf("OpenLayers.Editor.Control.EditorCustomPanel")
					// === -1 && this.controls[i].id != "mousePosition")
					if(aDeactiveControls.indexOf(this.controls[i].id) > -1){
						// console.log(this.controls[i].id + ' deactivated!! ');
						this.controls[i].deactivate();	
					}
				}
			}
			
		}
		
	},
	
	deActiveAllControls: function() {
		for(var i in this.controls) {
			if(this.controls[i].type != OpenLayers.Control.TYPE_TOGGLE) {
				// if(this.controls[i].CLASS_NAME.indexOf("OpenLayers.Editor.Control.EditorCustomPanel")
				// === -1 && this.controls[i].id != "mousePosition")
				if(this.controls[i].id != "mousePosition")
					this.controls[i].deactivate();	
			}
		}
	},
    removeAllPopups: function() {
        var len = this.popups.length;
        for (var i = len - 1; i >= 0; i--) this.removePopup(this.popups[i])
    },
    getResolutions: function() {
        return this.resolutions
    },
    movePrev: function() {
        this.getControl("naivgationHistory").previousTrigger()
    },
    moveNext: function() {
        this.getControl("naivgationHistory").nextTrigger()
    },
    isPrevStack: function() {
        if (this.getControl("naivgationHistory").previousStack.length > 1) return true;
        else return false
    },
    isNextStack: function() {
        if (this.getControl("naivgationHistory").nextStack.length > 0) return true;
        else return false
    },
    cleanMap : function(_aExceptionLayers) {
		var currControls = [];
		for(var i in this.controls) {
			if(this.controls[i].active) {
				currControls.push(this.controls[i]);
			}
			if(this.controls[i].id !== "mousePosition")
				this.controls[i].deactivate();
		}
		
				
		for(var i=0; i < currControls.length; i++) {
			currControls[i].activate();
		}
		
		for(var i in this.layers) {
			if(this.layers[i].CLASS_NAME == "GVector" || this.layers[i].CLASS_NAME == "OpenLayers.Layer.Vector") {
				if(_aExceptionLayers){
					if(_aExceptionLayers.indexOf(this.layers[i].name) === -1){
						this.layers[i].removeFeatures(this.layers[i].features);
					}
				}
				else
					this.layers[i].removeFeatures(this.layers[i].features);
			}
		}
		
		this.removeAllPopups();
	},
    getPopup: function(id) {
        for (var i in this.popups)
            if (this.popups[i].id == id) return this.popups[i];
        return false
    },
    setCenterScale: function(lonlat, scale) {
        if (scale) {
            this.center = lonlat;
            this.zoomToScale(scale)
        } else this.setCetner(lonlat)
    },
    zoomToFeature: function(feature, zoom) {
    	if($.isArray(feature)){		// 대장창에서 여러개 선택시 - Yu_mk
    		var initBounds = feature[0].geometry.getBounds();
    		var featuresBounds = {
    				bottom:initBounds.bottom,
    				left:initBounds.left,
    				right:initBounds.right,
    				top:initBounds.top
    		}
    		for(var i in feature) {
    			var bound=feature[i].geometry.getBounds();
        		if(featuresBounds.bottom > bound.bottom) {
        			featuresBounds.bottom = bound.bottom;
        		}
        		if(featuresBounds.left > bound.left) {
        			featuresBounds.left = bound.left;
        		}
        		if(featuresBounds.right < bound.right) {
        			featuresBounds.right = bound.right;
        		}
        		if(featuresBounds.top < bound.top) {
        			featuresBounds.top = bound.top;
        		}
    		}
    		
    		var featuresExtent = new OpenLayers.Bounds(featuresBounds.left, featuresBounds.bottom, featuresBounds.right, featuresBounds.top);
    		this.zoomToExtent(featuresExtent);
    	}
    	else if (zoom){
    		if(feature.length) this.setCenter(new GLonLat(feature[0].geometry.getCentroid().x, feature[0].geometry.getCentroid().y), zoom);
    		else this.setCenter(new GLonLat(feature.geometry.getCentroid().x, feature.geometry.getCentroid().y), zoom);
    	}
        else if (feature.geometry.CLASS_NAME == "OpenLayers.Geometry.Point") this.setCenter(new GLonLat(feature.geometry.getCentroid().x, feature.geometry.getCentroid().y), this.getNumZoomLevels() - 1);
        else this.zoomToExtent(feature.geometry.getBounds())
    },
    CLASS_NAME: "GMap"
});
GIndexMap = OpenLayers.Class({
    map: null,
    div: null,
    gindexMap: null,
    maxResolution: null,
    initialize: function(map, options) {
        if (!options.div) {
            this.div = document.createElement("div");
            $(this.div).addClass("olIndexMap").css({
                "width": "200px",
                "height": "200px",
                "position": "absolute",
                "z-index": "9999",
                "top": "86px",
                "right": "10px",
                "border": "1px solid #bbb",
                "background-color": "white"
            });
            $(map.div).append(this.div)
        } else this.div = document.getElementById(options.div);
        var lonlat;
        if (options && options.maxResolution) this.maxResolution =
            options.maxResolution;
        else this.maxResolution = Math.min(map.getMaxExtent().getWidth(), map.getMaxExtent().getHeight()) / Math.min($(this.div).css("width").replace("px", ""), $(this.div).css("height").replace("px", ""));
        this.gindexMap = new GMap(this.div, {
            maxExtent: options.maxExtent,
            maxResolution: this.maxResolution,
            projection: options.projection,
            controls: []
        });
        var layer = new GWMS("GIndexLayer", options.serviceUrl, {
            layers: options.layers,
            styles: options.styles,
            CRS: "EPSG:5181"
        });
        this.gindexMap.addLayer(layer);
        this.gindexMap.setBaseLayer(layer);
        this.gindexMap.addControl(new GZoomBoxIndex(map, {
            id: "indexMap"
        }));
        this.gindexMap.activeControls("indexMap");
        if (options && options.offsetPixel && options.offsetPixel.CLASS_NAME == "GPixel") {
            this.gindexMap.zoomToMaxExtent();
            lonlat = this.gindexMap.getLonLatFromPixel(this.gindexMap.getPixelFromLonLat(this.gindexMap.getMaxExtent().getCenterLonLat()).add(options.offsetPixel.x, options.offsetPixel.y));
            this.gindexMap.center = lonlat
        }
        map.events.register("moveend", this, function() {
            this.gindexMap.getControl("indexMap").handler.applyBox(map.getExtent())
        });
        this.gindexMap.zoomToMaxExtent()
    },
    show: function() {
        $(".olIndexMap").show()
    },
    hide: function() {
        $(".olIndexMap").hide()
    },
    toggle: function() {
        if ($(".olIndexMap").css("display") == "none") this.show();
        else this.hide()
    },
    isShow: function() {
        if ($(".olIndexMap").css("display") == "none") return false;
        else return true
    },
    getHeight: function() {
        return parseInt($(".olIndexMap").css("height").replace("px", ""))
    },
    getWidth: function() {
        return parseInt($(".olIndexMap").css("width").replace("px", ""))
    },
    getPosition: function() {
        var result = {
            left: parseInt($(".olIndexMap").css("left").replace("px", "")),
            bottom: parseInt($(".olIndexMap").css("bottom").replace("px", "")),
            right: parseInt($(".olIndexMap").css("right").replace("px", "")),
            top: parseInt($(".olIndexMap").css("top").replace("px", ""))
        };
        return result
    },
    setPosition: function(left, bottom, right, top) {
        if (left) $(".olIndexMap").css("left", left);
        if (right) $(".olIndexMap").css("right", right);
        if (bottom) $(".olIndexMap").css("bottom", bottom);
        if (top) $(".olIndexMap").css("top", top)
    },
    setHeight: function(height) {
        $(".olIndexMap").css("height",
            height)
    },
    setWidht: function(widht) {
        $(".olIndexMap").css("widht", widht)
    },
    changeLayer: function(layers, styles) {
        this.gindexMap.baseLayer.mergeNewParams({
            layers: layers,
            styles: styles
        })
    },
    CLASS_NAME: "GIndexMap"
});
OpenLayers.Util.getImagesLocation = function() {
    return ""
};
OpenLayers.DOTS_PER_INCH = 96;
var GMapUtil = {
    sendProxyGet: function(serviceUrl, params, callback) {
        $.get("/gmap/proxyGet.do", {
            url: encodeURIComponent(serviceUrl),
            params: encodeURIComponent(params)
        }, function(res) {
        	if(!GMapUtil.hasErr(res, this))
        		callback(res)
        });
    },
    sendProxyPost: function(serviceUrl, params, callback) {
        $.post("/gpms/proxyPost.do", {
            url: encodeURIComponent(serviceUrl),
            params: encodeURIComponent(params)
        }, function(res) {
        	if(!GMapUtil.hasErr(res, this))
        		callback(res)
        });
    },
    sendProxyPostSync: function(serviceUrl, params, callback) {
        $.ajax({
            type: "post",
            dataType: "xml",
            data: {
                url: encodeURIComponent(serviceUrl),
                params: encodeURIComponent(params)
            },
            async: false,
            url: "/gpms/proxyPost.do",
            success: function(res) {
                callback(res)
            },
            error: function(xhr, status, error) {
                alert("sendProxyPostSync \uc624\ub958\ubc1c\uc0dd.\n check2!. status = " + status + ", error=" + error)
            }
        })
    },
    hasErr: function(objRes, objReq) {
    	var nResElementSize  = $(objRes).length;
    	var blnHasError = false;
    	for(var i=0;i<nResElementSize;i++){
    		var oTmp = $(objRes)[i];
    		if(oTmp.attributes){
    			if(oTmp.getElementsByClassName("error").length > 0){
        			alert("Error Message - [" + oTmp.getElementsByClassName("error")[0].innerText +"]\n\n" + decodeURIComponent(decodeURIComponent(objReq.data)));
        			blnHasError = true;
        			break;
        		}
    		}
    	}
    	return blnHasError;
    }
};
GSLD = OpenLayers.Class(OpenLayers.Format.SLD, {
    CLASS_NAME: "GSLD"
});
OpenLayers.Format.SLD.v1_1 = OpenLayers.Class(OpenLayers.Format.Filter.v1_1_0, {
    namespaces: {
        sld: "http://www.opengis.net/sld",
        se: "http://www.opengis.net/se",
        ogc: "http://www.opengis.net/ogc",
        gml: "http://www.opengis.net/gml",
        xlink: "http://www.w3.org/1999/xlink",
        xsi: "http://www.w3.org/2001/XMLSchema-instance",
        version: "1.1.0"
    },
    defaultPrefix: "se",
    schemaLocation: null,
    defaultSymbolizer: {
        fillColor: "#808080",
        fillOpacity: 1,
        strokeColor: "#000000",
        strokeOpacity: 1,
        strokeWidth: 1,
        strokeDashstyle: "solid",
        pointRadius: 3,
        graphicName: "square"
    },
    initialize: function(options) {
        OpenLayers.Format.Filter.v1_1_0.prototype.initialize.apply(this, [options])
    },
    read: function(data, options) {
        options = OpenLayers.Util.applyDefaults(options, this.options);
        var sld = {
            namedLayers: options.namedLayersAsArray === true ? [] : {}
        };
        this.readChildNodes(data, sld);
        return sld
    },
    readOgcExpression: function(node) {
        var obj = {};
        this.readChildNodes(node, obj);
        var value = obj.value;
        if (value === undefined) value = this.getChildValue(node);
        return value
    },
    readers: OpenLayers.Util.applyDefaults({
        "sld": {
            "StyledLayerDescriptor": function(node,
                sld) {
                sld.version = node.getAttribute("version");
                this.readChildNodes(node, sld)
            },
            "NamedLayer": function(node, sld) {
                var layer = {
                    userStyles: [],
                    namedStyles: []
                };
                this.readChildNodes(node, layer);
                for (var i = 0, len = layer.userStyles.length; i < len; ++i) layer.userStyles[i].layerName = layer.name;
                if (sld.namedLayers instanceof Array) sld.namedLayers.push(layer);
                else sld.namedLayers[layer.name] = layer
            },
            "NamedStyle": function(node, layer) {
                layer.namedStyles.push(this.getChildName(node.firstChild))
            },
            "UserStyle": function(node, layer) {
                var obj = {
                    defaultsPerSymbolizer: true,
                    rules: []
                };
                this.readChildNodes(node, obj);
                var style = new OpenLayers.Style(this.defaultSymbolizer, obj);
                layer.userStyles.push(style)
            },
            "LayerFeatureConstraints": function(node, layer) {
                this.readChildNodes(node, layer)
            },
            "FeatureTypeConstraint": function(node, layer) {
                this.readChildNodes(node, layer)
            },
            "VendorOption" : function(node, symbolizer) {
                var Property = node.getAttribute("name");
                var symProperty = this.cssMap[Property];
                if (symProperty) {
                    var value = this.readOgcExpression(node);
                    if (value) symbolizer[symProperty] = value
                }
            }
        },
        "se": {
            "Name": function(node, obj) {
                obj.name = this.getChildValue(node)
            },
            "Title": function(node, obj) {
                obj.title = this.getChildValue(node)
            },
            "Description": function(node, obj) {
                obj.description = this.getChildValue(node)
            },
            "IsDefault": function(node, style) {
                if (this.getChildValue(node) == "1") style.isDefault = true
            },
            "CoverageStyle": function(node, style) {
                var obj = {
                    rules: []
                };
                this.readChildNodes(node, obj);
                style.rules = obj.rules
            },
            "FeatureTypeName": function(node, layer) {
                layer.LayerFeatureConstraints = this.getChildValue(node)
            },
            "FeatureTypeStyle": function(node, style) {
                var obj = {
                    rules: []
                };
                this.readChildNodes(node, obj);
                style.rules = obj.rules
            },
            "Rule": function(node, obj) {
                var rule = new OpenLayers.Rule;
                this.readChildNodes(node, rule);
                obj.rules.push(rule)
            },
            "ElseFilter": function(node, rule) {
                rule.elseFilter = true
            },
            "MinScaleDenominator": function(node, rule) {
                rule.minScaleDenominator = parseFloat(this.getChildValue(node))
            },
            "MaxScaleDenominator": function(node, rule) {
                rule.maxScaleDenominator = parseFloat(this.getChildValue(node))
            },
            "TextSymbolizer": function(node, rule) {
                var symbolizer = rule.symbolizer["Text"] || {};
                this.readChildNodes(node, symbolizer);
                rule.symbolizer["Text"] = symbolizer
            },
            "Label": function(node, symbolizer) {
                var obj = {};
                this.readChildNodes(node, obj);
                if (obj.property) symbolizer.label =
                    "${" + obj.property + "}";
                else {
                    var value = this.readOgcExpression(node);
                    if (value) symbolizer.label = value
                }
            },
            "Font": function(node, symbolizer) {
                this.readChildNodes(node, symbolizer)
            },
            "Halo": function(node, symbolizer) {
                var obj = {};
                this.readChildNodes(node, obj);
                symbolizer.haloRadius = obj.haloRadius;
                symbolizer.haloColor = obj.fillColor;
                symbolizer.haloOpacity = obj.fillOpacity
            },
            "Radius": function(node, symbolizer) {
                var radius = this.readOgcExpression(node);
                if (radius != null) symbolizer.haloRadius = radius
            },
            "LabelPlacement": function(node,
                symbolizer) {
                this.readChildNodes(node, symbolizer)
            },
            "LinePlacement": function(node, symbolizer) {
                this.readChildNodes(node, symbolizer);
            },
            "PointPlacement": function(node, symbolizer) {
                this.readChildNodes(node, symbolizer)
            },
            "Displacement": function(node, symbolizer) {
                this.readChildNodes(node, symbolizer)
            },
            "DisplacementX": function(node, symbolizer) {
                var displacementX = this.readOgcExpression(node);
                if (displacementX != null) symbolizer.displacementX = displacementX
            },
            "DisplacementY": function(node, symbolizer) {
                var displacementY = this.readOgcExpression(node);
                if (displacementY != null) symbolizer.displacementY = displacementY
            },
            "PolygonSymbolizer": function(node, rule) {
                var symbolizer = rule.symbolizer["Polygon"] || {};
                this.readChildNodes(node, symbolizer);
                rule.symbolizer["Polygon"] = symbolizer
            },
            "LineSymbolizer": function(node, rule) {
                var symbolizer = rule.symbolizer["Line"] || {};
                this.readChildNodes(node, symbolizer);
                rule.symbolizer["Line"] = symbolizer
            },
            "PointSymbolizer": function(node, rule) {
                var symbolizer = rule.symbolizer["Point"] || {};
                this.readChildNodes(node, symbolizer);
                rule.symbolizer["Point"] =
                    symbolizer
            },
            "RasterSymbolizer": function(node, rule) {
                var symbolizer = rule.symbolizer["Raster"] || {};
                this.readChildNodes(node, symbolizer);
                rule.symbolizer["Raster"] = symbolizer
            },
            "SvgParameter": function(node, symbolizer) {
                var Property = node.getAttribute("name");
                var symProperty = this.cssMap[Property];
                if (symProperty) {
                    var value = this.readOgcExpression(node);
                    if (value) symbolizer[symProperty] = value
                }
            },
            "Stroke": function(node, symbolizer) {
                symbolizer.stroke = true;
                this.readChildNodes(node, symbolizer)
            },
            "Fill": function(node,
                symbolizer) {
                symbolizer.fill = true;
                this.readChildNodes(node, symbolizer)
            },
            "CssParameter": function(node, symbolizer) {
                var cssProperty = node.getAttribute("name");
                var symProperty = this.cssMap[cssProperty];
                if (symProperty) {
                    var value = this.readOgcExpression(node);
                    if (value) symbolizer[symProperty] = value
                }
            },
            "Graphic": function(node, symbolizer) {
                symbolizer.graphic = true;
                var graphic = {};
                this.readChildNodes(node, graphic);
                var properties = ["strokeColor", "strokeWidth", "strokeOpacity", "strokeLinecap", "strokeLinejoin", "fillColor",
                    "fillOpacity", "graphicName", "rotation", "graphicFormat", "graphicContent" , "href" , "angleScale", "angleTranslation"
                ];
                var prop, value;
                for (var i = 0, len = properties.length; i < len; ++i) {
                    prop = properties[i];
                    value = graphic[prop];
                    if (value != undefined) symbolizer[prop] = value
                }
                if (graphic.opacity != undefined) symbolizer.graphicOpacity = graphic.opacity;
                if (graphic.size != undefined) symbolizer.pointSize = graphic.size;
                if (graphic.href != undefined) symbolizer.externalGraphic = graphic.href;
                if (graphic.rotation != undefined) symbolizer.rotation = graphic.rotation;
                if (graphic.angleScale != undefined) symbolizer.angleScale = graphic.angleScale;
                if (graphic.angleTranslation != undefined) symbolizer.angleTranslation = graphic.angleTranslation;
            },
            "ExternalGraphic": function(node,
                graphic) {
                this.readChildNodes(node, graphic)
            },
            "Mark": function(node, graphic) {
                this.readChildNodes(node, graphic)
            },
            "WellKnownName": function(node, graphic) {
                graphic.graphicName = this.getChildValue(node)
            },
            "Opacity": function(node, obj) {
                var opacity = this.readOgcExpression(node);
                if (opacity) obj.opacity = opacity
            },
            "Size": function(node, obj) {
                var size = this.readOgcExpression(node);
                if (size) obj.size = size
            },
            "Rotation": function(node, obj) {
                var rotation = this.readOgcExpression(node.firstChild);
                if (rotation) obj.rotation = rotation
            },
            "OnlineResource": function(node,
                obj) {
                obj.href = this.getAttributeNS(node, this.namespaces.xlink, "href")
            },
            "Format": function(node, graphic) {
                graphic.graphicFormat = this.getChildValue(node)
            },
            "InlineContent": function(node, graphic) {
                graphic.graphicContent = this.getChildValue(node)
            },
            "VendorOption" : function(node, symbolizer) {
            	var Property = node.getAttribute("name");
                var symProperty = this.cssMap[Property];
                if (symProperty) {
                    var value = this.readOgcExpression(node);
                    if (value) symbolizer[symProperty] = value
                }
            }
        }
    }, OpenLayers.Format.Filter.v1_1_0.prototype.readers),
    cssMap: {
        "stroke": "strokeColor",
        "stroke-opacity": "strokeOpacity",
        "stroke-width": "strokeWidth",
        "stroke-linecap": "strokeLinecap",
        "stroke-linejoin": "strokeLinejoin",
        "stroke-dasharray": "strokeDashstyle",
        "fill": "fillColor",
        "fill-opacity": "fillOpacity",
        "font-family": "fontFamily",
        "font-size": "fontSize",
        "font-weight": "fontWeight",
        "font-style": "fontStyle",
        "background_type":"backgroundType",
        "background_fill":"backgroundFill",
        "background_line":"backgroundLine",
        "background_offset":"backgroundOffset",
        "background_align":"backgroundAlign",
        "text_point_base":"textPointBase",
        "text_point_position":"textPointPosition",
        "text_point_arrange":"textPointArrange",
        "code_domain":"codeDomain",
        "text_arrange_pos":"textArrangePos",
        "text_arrange_line":"textArrangeLine",
        "text_arrange_gap":"textArrangeGap",
        "char-code":"charCode",
        "angle_scale":"angleScale",
        "angle_translation":"angleTranslation"
    },
    getCssProperty: function(sym) {
        var css = null;
        for (var prop in this.cssMap)
            if (this.cssMap[prop] == sym) {
                css = prop;
                break
            }
        return css
    },
    getGraphicFormat: function(href) {
        var format, regex;
        for (var key in this.graphicFormats)
            if (this.graphicFormats[key].test(href)) {
                format = key;
                break
            }
        return format || this.defautlGraphicFormat
    },
    defaultGraphicFormat: "image/png",
    graphicFormats: {
        "image/jpeg": /\.jpe?g$/i,
        "image/gif": /\.gif$/i,
        "image/png": /\.png$/i
    },
    write: function(sld) {
        return this.writers.sld.StyledLayerDescriptor.apply(this, [sld])
    },
    writers: OpenLayers.Util.applyDefaults({
        "sld": {
            "StyledLayerDescriptor": function(sld) {
                var root = this.createElementNSPlus("sld:StyledLayerDescriptor", {
                    attributes: {
                        "version": this.namespaces.version
                    }
                });
                root.setAttribute(null, "xmlns:se", this.namespaces.se);
                root.setAttribute(null, "xmlns:xlink", this.namespaces.xlink);
                root.setAttribute(null, "xmlns:xsi", this.namespaces.xsi);
                root.setAttribute(null,
                    "xmlns:sld", this.namespaces.sld);
                root.setAttribute(null, "xmlns:ogc", this.namespaces.ogc);
                if (sld.name) this.writeNode("Name", sld.name, root);
                if (sld.title) this.writeNode("title", sld.title, root);
                this.writeNode("Description", sld.description, root);
                if (sld.namedLayers instanceof Array)
                    for (var i = 0, len = sld.namedLayers.length; i < len; ++i) this.writeNode("sld:NamedLayer", sld.namedLayers[i], root);
                else
                    for (var name in sld.namedLayers) this.writeNode("sld:NamedLayer", sld.namedLayers[name], root);
                return root
            },
            "NamedLayer": function(layer) {
                var node =
                    this.createElementNSPlus("sld:NamedLayer");
                this.writeNode("se:Name", layer.name, node);
                this.writeNode("se:Description", layer.description, node);
                if (layer.LayerFeatureConstraints) this.writeNode("sld:LayerFeatureConstraints", layer.LayerFeatureConstraints, node);
                if (layer.namedStyles)
                    for (var i = 0, len = layer.namedStyles.length; i < len; ++i) this.writeNode("sld:NamedStyle", layer.namedStyles[i], node);
                if (layer.userStyles)
                    for (var i = 0, len = layer.userStyles.length; i < len; ++i) this.writeNode("sld:UserStyle", layer.userStyles[i],
                        node);
                return node
            },
            "NamedStyle": function(name) {
                var node = this.createElementNSPlus("sld:NamedStyle");
                this.writeNode("se:Name", name, node);
                return node
            },
            "UserStyle": function(style) {
                var node = this.createElementNSPlus("sld:UserStyle");
                if (style.name) this.writeNode("se:Name", style.name, node);
                if (style.title) this.writeNode("se:Title", style.title, node);
                this.writeNode("se:Description", style.description, node);
                if (style.isDefault) this.writeNode("se:IsDefault", style.isDefault, node);
                this.writeNode("se:FeatureTypeStyle",
                    style, node);
                return node
            },
            "LayerFeatureConstraints": function(FeatureConstraints) {
                var node = this.createElementNSPlus("sld:LayerFeatureConstraints");
                this.writeNode("sld:FeatureTypeConstraint", FeatureConstraints, node);
                return node
            },
            "FeatureTypeConstraint": function(constranint) {
                var node = this.createElementNSPlus("sld:FeatureTypeConstraint");
                this.writeNode("se:FeatureTypeName", constranint, node);
                return node
            },
            "Name": function(name) {
                return this.createElementNSPlus("se:Name", {
                    value: name
                })
            },
            "Title": function(title) {
                return this.createElementNSPlus("se:Title", {
                    value: title
                })
            },
            "Description": function(title) {
                var node = this.createElementNSPlus("se:Description");
                this.writeNode("Title", title, node);
                return node
            },
            "VendorOption" : function(obj) {
            	return this.createElementNSPlus("sld:VendorOption", {
                    attributes: {
                        name: this.getCssProperty(obj.key)
                    },
                    value: obj.symbolizer[obj.key]
                })
            }
        },
        "se": {
            "Name": function(name) {
                return this.createElementNSPlus("se:Name", {
                    value: name
                })
            },
            "Title": function(title) {
                return this.createElementNSPlus("se:Title", {
                    value: title
                })
            },
            "Description": function(title) {
                var node = this.createElementNSPlus("se:Description");
                this.writeNode("Title", title, node);
                return node
            },
            "Abstract": function(description) {
                return this.createElementNSPlus("Abstract", {
                    value: description
                })
            },
            "IsDefault": function(bool) {
                return this.createElementNSPlus("sd:IsDefault", {
                    value: bool ? "1" : "0"
                })
            },
            "FeatureTypeName": function(typename) {
                return this.createElementNSPlus("se:FeatureTypeName", {
                    value: typename
                })
            },
            "FeatureTypeStyle": function(style) {
                var node = this.createElementNSPlus("se:FeatureTypeStyle");
                for (var i = 0, len = style.rules.length; i < len; ++i) this.writeNode("Rule", style.rules[i], node);
                return node
            },
            "Rule": function(rule) {
                var node = this.createElementNSPlus("se:Rule");
                if (rule.name) this.writeNode("Name",
                    rule.name, node);
                if (rule.elseFilter) this.writeNode("ElseFilter", null, node);
                else if (rule.filter) this.writeNode("ogc:Filter", rule.filter, node);
                if (rule.minScaleDenominator != undefined) this.writeNode("MinScaleDenominator", rule.minScaleDenominator, node);
                if (rule.maxScaleDenominator != undefined) this.writeNode("MaxScaleDenominator", rule.maxScaleDenominator, node);
                var types = OpenLayers.Style.SYMBOLIZER_PREFIXES;
                var type, symbolizer;
                for (var i = 0, len = types.length; i < len; ++i) {
                    type = types[i];
                    symbolizer = rule.symbolizer[type];
                    if (symbolizer) this.writeNode(type + "Symbolizer", symbolizer, node)
                }
                return node
            },
            "ElseFilter": function() {
                return this.createElementNSPlus("se:ElseFilter")
            },
            "MinScaleDenominator": function(scale) {
                return this.createElementNSPlus("se:MinScaleDenominator", {
                    value: scale
                })
            },
            "MaxScaleDenominator": function(scale) {
                return this.createElementNSPlus("se:MaxScaleDenominator", {
                    value: scale
                })
            },
            "LineSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:LineSymbolizer", {
                    attributes: {
                        "version": this.VERSION
                    }
                });
                if (symbolizer.name) this.writeNode("Name", symbolizer.name, node);
                this.writeNode("Stroke", symbolizer, node);
                return node
            },
            "Stroke": function(symbolizer) {
                var node = this.createElementNSPlus("se:Stroke");
                if (symbolizer.strokeColor != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeColor"
                }, node);
                if (symbolizer.strokeOpacity != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeOpacity"
                }, node);
                if (symbolizer.strokeWidth != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeWidth"
                }, node);
                if (symbolizer.strokeLinecap != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeLinecap"
                }, node);
                if (symbolizer.strokeLinejoin != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeLinejoin"
                }, node);
                if (symbolizer.strokeDashstyle != undefined) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "strokeDashstyle"
                }, node);
                return node
            },
            "SvgParameter": function(obj) {
                return this.createElementNSPlus("se:SvgParameter", {
                    attributes: {
                        name: this.getCssProperty(obj.key)
                    },
                    value: obj.symbolizer[obj.key]
                })
            },
            "CssParameter": function(obj) {
                return this.createElementNSPlus("se:CssParameter", {
                    attributes: {
                        name: this.getCssProperty(obj.key)
                    },
                    value: obj.symbolizer[obj.key]
                })
            },
            "TextSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:TextSymbolizer", {
                    attributes: {
                        "version": this.VERSION
                    }
                });
                if (symbolizer.name) this.writeNode("Name", symbolizer.name, node);
                if (symbolizer.label != null) this.writeNode("Label", symbolizer.label, node);
                if (symbolizer.fontFamily != null || symbolizer.fontSize !=
                    null || symbolizer.fontWeight != null || symbolizer.fontStyle != null) this.writeNode("Font", symbolizer, node);
                if (symbolizer.haloRadius != null || symbolizer.haloColor != null || symbolizer.haloOpacity != null) this.writeNode("Halo", symbolizer, node);
                if (symbolizer.fillColor != null || symbolizer.fillOpacity != null) this.writeNode("Fill", symbolizer, node);
                if (symbolizer.displacementX != null && symbolizer.displacementY != null || symbolizer.name == "LineText") this.writeNode("LabelPlacement", symbolizer, node);
                
                if (symbolizer.textPointBase != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointBase"}, node);
                if (symbolizer.textPointPosition != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointPosition"}, node);
                if (symbolizer.textPointArrange != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointArrange"}, node);
                if (symbolizer.backgroundType != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundType"}, node);
                if (symbolizer.backgroundFill != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundFill"}, node);
                if (symbolizer.backgroundLine != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundLine"}, node);
                if (symbolizer.backgroundOffset != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundOffset"}, node);
                if (symbolizer.backgroundAlign != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundAlign"}, node);
                if (symbolizer.codeDomain != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"codeDomain"}, node);
                return node
            },
            "LabelPlacement": function(symbolizer) {
                var node =
                    this.createElementNSPlus("se:LabelPlacement");
                if (symbolizer.name == "LineText") this.writeNode("LinePlacement", symbolizer, node);
                else if (symbolizer.displacementX != null && symbolizer.displacementY != null) this.writeNode("PointPlacement", symbolizer, node);
                return node
            },
            "LinePlacement": function(symbolizer) {
                var node = this.createElementNSPlus("se:LinePlacement");
                if (symbolizer.textArrangePos != null) this.writeNode("VendorOption", {symbolizer: symbolizer, key:"textArrangePos"}, node);
                if (symbolizer.textArrangeLine != null) this.writeNode("VendorOption", {symbolizer: symbolizer, key:"textArrangeLine"}, node);
                if (symbolizer.textArrangeGap != null) this.writeNode("VendorOption", {symbolizer: symbolizer, key:"textArrangeGap"}, node);
                return node;
            },
            "VendorOption" : function(obj) {
            	return this.createElementNSPlus("se:VendorOption", {
                    attributes: {
                        name: this.getCssProperty(obj.key)
                    },
                    value: obj.symbolizer[obj.key]
                })
            },
            "PointPlacement": function(symbolizer) {
                var node = this.createElementNSPlus("se:PointPlacement");
                if (symbolizer.displacementX != null && symbolizer.displacementY !=
                    null) this.writeNode("Displacement", symbolizer, node);
                return node
            },
            "Displacement": function(symbolizer) {
                var node = this.createElementNSPlus("se:Displacement");
                if (symbolizer.displacementX != null && symbolizer.displacementY != null) {
                    this.writeNode("DisplacementX", symbolizer, node);
                    this.writeNode("DisplacementY", symbolizer, node)
                }
                return node
            },
            "DisplacementX": function(symbolizer) {
                return node = this.createElementNSPlus("se:DisplacementX", {
                    value: symbolizer.displacementX
                })
            },
            "DisplacementY": function(symbolizer) {
                return node =
                    this.createElementNSPlus("se:DisplacementY", {
                        value: symbolizer.displacementY
                    })
            },
            "Font": function(symbolizer) {
                var node = this.createElementNSPlus("se:Font");
                if (symbolizer.fontFamily) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fontFamily"
                }, node);
                if (symbolizer.fontStyle) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fontStyle"
                }, node);
                if (symbolizer.fontWeight) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fontWeight"
                }, node);
                if (symbolizer.fontSize) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fontSize"
                }, node);
                
                return node
            },
            "Label": function(label) {
                var node = this.createElementNSPlus("se:Label");
                var tokens = label.split("${");
                node.appendChild(this.createTextNode(tokens[0]));
                var item, last;
                for (var i = 1, len = tokens.length; i < len; i++) {
                    item = tokens[i];
                    last = item.indexOf("}");
                    if (last > 0) {
                        this.writeNode("ogc:PropertyName", {
                            property: item.substring(0, last)
                        }, node);
                        node.appendChild(this.createTextNode(item.substring(++last)))
                    } else node.appendChild(this.createTextNode("${" + item))
                }
                return node
            },
            "Halo": function(symbolizer) {
                var node = this.createElementNSPlus("se:Halo");
                if (symbolizer.haloRadius) this.writeNode("Radius", symbolizer.haloRadius, node);
                if (symbolizer.haloColor || symbolizer.haloOpacity) this.writeNode("Fill", {
                    fillColor: symbolizer.haloColor,
                    fillOpacity: symbolizer.haloOpacity
                }, node);
                return node
            },
            "Radius": function(value) {
                return node = this.createElementNSPlus("se:Radius", {
                    value: value
                })
            },
            "RasterSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:PolygonSymbolizer", {
                    attributes: {
                        "version": this.VERSION
                    }
                });
                if (symbolizer.name) this.writeNode("Name", symbolizer.name, node);
                if (symbolizer.Opacity != undefined) this.writeNode("Opacity", symbolizer, node);
                return node
            },
            "PolygonSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:PolygonSymbolizer", {
                    attributes: {
                        "version": this.VERSION
                    }
                });
                if (symbolizer.name) this.writeNode("Name", symbolizer.name, node);
                if (symbolizer.fillColor != undefined || symbolizer.fillOpacity != undefined) this.writeNode("Fill", symbolizer, node);
                if (symbolizer.strokeWidth != undefined || symbolizer.strokeColor !=
                    undefined || symbolizer.strokeOpacity != undefined || symbolizer.strokeDashstyle != undefined) this.writeNode("Stroke", symbolizer, node);
                return node
            },
            "Fill": function(symbolizer) {
                var node = this.createElementNSPlus("se:Fill");
                if (symbolizer.fillColor) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fillColor"
                }, node);
                if (symbolizer.fillOpacity != null) this.writeNode("SvgParameter", {
                    symbolizer: symbolizer,
                    key: "fillOpacity"
                }, node);
                return node
            },
            "PointSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:PointSymbolizer", {
                    attributes: {
                        "version": this.VERSION
                    }
                });
                if (symbolizer.name) this.writeNode("Name", symbolizer.name, node);
                this.writeNode("Graphic", symbolizer, node);
                return node
            },
            "Graphic": function(symbolizer) {
                if (symbolizer.graphic == true) {
                    var node = this.createElementNSPlus("se:Graphic");
                    if (symbolizer.graphicContent != undefined || symbolizer.href != undefined) this.writeNode("ExternalGraphic", symbolizer, node);
                    else this.writeNode("Mark", symbolizer, node);
                    if (symbolizer.pointSize != undefined) this.writeNode("Size", symbolizer.pointSize, node);
                    if (symbolizer.graphicOpacity !=
                        undefined) this.writeNode("Opacity", symbolizer.graphicOpacity, node);
                    if(symbolizer.rotation != undefined) this.writeNode("se:Rotation", symbolizer.rotation, node);
                    if(symbolizer.angleScale != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"angleScale"}, node);
                    if(symbolizer.angleTranslation != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"angleTranslation"}, node);
                    return node
                }
            },
            "ExternalGraphic": function(symbolizer) {
                var node = this.createElementNSPlus("se:ExternalGraphic");
                if(!symbolizer.href) {this.writeNode("InlineContent", symbolizer.graphicContent, node);}
                else {this.writeNode("OnlineResource", symbolizer.href, node);}
                var format = symbolizer.graphicFormat || this.getGraphicFormat(symbolizer.externalGraphic);
                this.writeNode("Format", format, node);
                return node
            },
            "Mark": function(symbolizer) {
                var node = this.createElementNSPlus("se:Mark");
                if (symbolizer.graphicName) this.writeNode("WellKnownName",
                    symbolizer.graphicName, node);
                this.writeNode("Fill", symbolizer, node);
                this.writeNode("Stroke", symbolizer, node);
                if (symbolizer.charCode != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"charCode"}, node);
                if (symbolizer.fillColor != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"fillColor"}, node);
                if (symbolizer.fontFamily != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"fontFamily"}, node);
                return node
            },
            "WellKnownName": function(name) {
                return this.createElementNSPlus("se:WellKnownName", {
                    value: name
                })
            },
            "Opacity": function(value) {
                return this.createElementNSPlus("se:Opacity", {
                    value: value
                })
            },
            "Size": function(value) {
                return this.createElementNSPlus("se:Size", {
                    value: value
                })
            },
            "Rotation": function(value) {
            	var node = this.createElementNSPlus("se:Rotation");
            	if (value != null) this.writeNode("ogc:PropertyName", {
                    property: value
                }, node);
                return node
            },
            "OnlineResource": function(href) {
                return this.createElementNSPlus("se:OnlineResource", {
                    attributes: {
                        "xlink:type": "simple",
                        "xlink:href": href
                    }
                })
            },
            "Format": function(format) {
                return this.createElementNSPlus("se:Format", {
                    value: format
                })
            },
            "InlineContent": function(Content) {
                return this.createElementNSPlus("se:InlineContent", {
                    attributes: {
                        encoding: "base64"
                    },
                    value: Content
                })
            }
        }
    }, OpenLayers.Format.Filter.v1_1_0.prototype.writers),
    CLASS_NAME: "OpenLayers.Format.SLD.v1_1"
});
OpenLayers.Format.SLD.v1_1_0 = OpenLayers.Class(OpenLayers.Format.SLD.v1_1, {
    VERSION: "1.1.0",
    schemaLocation: "http://www.opengis.net/sld http://schemas.opengis.net/sld/1.1.0/StyledLayerDescriptor.xsd",
    initialize: function(options) {
        OpenLayers.Format.SLD.v1_1.prototype.initialize.apply(this, [options])
    },
    CLASS_NAME: "OpenLayers.Format.SLD.v1_1_0"
});
var GRequest = {};
OpenLayers.Util.extend(GRequest,OpenLayers.Request);
GRequest.WMS = {
    service: "WMS",
    version: "1.3.0",
    request: null,
    format: new OpenLayers.Format.SLD.v1_1_0,
    getCapability: function(serviceUrl, callback) {
        var params = {
            service: this.service,
            version: this.version,
            request: "GetCapabilities"
        };
        var obj = this;
        GMapUtil.sendProxyGet(serviceUrl, GUtil.fn_convert_objToStr(params), function(res) {
            obj.parseGetCapability(res, callback)
        })
    },
    parseGetCapability: function(res, callback) {
        var arr = [];
        var totalLayers = res.getElementsByTagName("Layer");
        for (var i = 0, len = totalLayers.length; i < len; i++) {
            var grpLayers =
                totalLayers[i].getElementsByTagName("Layer");
            if (grpLayers.length > 0 && totalLayers[i].getElementsByTagName("Title")[0].text != "BASIC") {
                var groupArr = {
                    title: totalLayers[i].getElementsByTagName("Title")[0].text,
                    layers: []
                };
                for (var j = 0, jLen = grpLayers.length; j < jLen; j++) {
                    var obj = {
                        name: grpLayers[j].getElementsByTagName("Name")[0].text,
                        style: grpLayers[j].getElementsByTagName("Style")[0].text,
                        title: grpLayers[j].getElementsByTagName("Title")[0].text,
                        left: grpLayers[j].getElementsByTagName("westBoundLongitude")[0].text,
                        bottom: grpLayers[j].getElementsByTagName("southBoundLatitude")[0].text,
                        right: grpLayers[j].getElementsByTagName("eastBoundLongitude")[0].text,
                        top: grpLayers[j].getElementsByTagName("northBoundLatitude")[0].text
                    };
                    groupArr.layers.push(obj)
                }
                arr.push(groupArr)
            }
        }
        callback(arr)
    },
    getLengendGraphic: function(serviceUrl, parameters) {
        var params = {
            service: this.service,
            version: this.version,
            request: "GetLegendGraphic",
            layer: "",
            style: "",
            rule: "",
            sld_version: "1.1.0",
            format: "image/png",
            width: 16,
            height: 16
        };
        $.extend(params,
            parameters);
        return serviceUrl + GUtil.fn_convert_objToStr(params)
    },
    getStyles: function(serviceUrl, layers, callback) {
        var params = {
            service: this.service,
            version: this.version,
            request: "GetStyles",
            layers: layers
        };
        var obj = this;
        GMapUtil.sendProxyPost(serviceUrl, GUtil.fn_convert_objToStr(params), function(res) {
            obj.parseGetStyles(res, callback)
        })
    },	
	getStylesBySync : function(serviceUrl, layers, callback) {
		var params = {
			service : this.service,
			version : this.version,
			request : "GetStyles",
			layers : layers
		};
		
		var obj = this;
		GMapUtil.sendProxyPostSync(serviceUrl, GUtil.fn_convert_objToStr(params), function(res)
			{
				obj.parseGetStyles(res, callback);
			// callback(new GSLDTool(res,"xml"));
			}
		);
	},
    parseGetStyles: function(res, callback) {
        var obj = {
            xml: res,
            name: "",
            namedLayers: []
        };
        var sld = this.format.read(res);
        var name = sld.name;
        if (name.length > 0) obj.name = name;
        var namedLayers =
            sld.namedLayers;
        for (var i in namedLayers) {
            var namedObj = {
                name: "",
                title: "",
                featureTypeName: "",
                userStyle: []
            };
            var name = namedLayers[i].name;
            if (name.length > 0) namedObj.name = name;
            var description = namedLayers[i].description;
            if (description.length > 0) namedObj.title = description.title;
            var layerFeatureConstraints = namedLayers[i].LayerFeatureConstraints;
            if (layerFeatureConstraints.length > 0) namedObj.featureTypeName = layerFeatureConstraints;
            var userStyles = namedLayers[i].userStyles;
            for (var j = 0, jLen = userStyles.length; j <
                jLen; j++) {
                var userdObj = {
                    name: "",
                    title: "",
                    rules: []
                };
                var name = userStyles[j].name;
                if (name.length > 0) userdObj.name = name;
                var description = userStyles[j].description;
                if (description.length > 0) userdObj.title = description;
                var layerName = userStyles[j].layerName;
                if (layerName.length > 0) userdObj.title = layerName;
                var rules = userStyles[j].rules;
                for (var k = 0, kLen = rules.length; k < kLen; k++) {
                    var ruleObj = {
                        name: "",
                        minScale: "",
                        maxScale: "",
                        symbolizer : {}
                    };
                    ruleObj.name = rules[k].name;
                    ruleObj.minScale = rules[k].minScaleDenominator;
                    ruleObj.maxScale = rules[k].maxScaleDenominator;
                    var filter = rules[k].filter;
					if(filter){
						ruleObj.filter = new OpenLayers.Filter();
						ruleObj.filter.type = filter.type;
						ruleObj.filter.property = filter.property;
						ruleObj.filter.value = filter.value;
					}
                    var points = rules[k].symbolizer["Point"];
                    var lines = rules[k].symbolizer["Line"];
                    var polygons = rules[k].symbolizer["Polygon"];
                    var texts = rules[k].symbolizer["Text"];
                    if (points) {
                        var pointObj = {};
                        var size = points.pointSize;
                        if (size.length > 0) {
                        	pointObj["size"] = size;
                        	if(points.name.indexOf('ImageMarker') != -1) {
                        		pointObj["opacity"] = points.graphicOpacity;
                        		var externalGraphic;
                        		if(!points.href) externalGraphic = points.graphicContent;
                        		else externalGraphic = points.href;
                        		if(externalGraphic) pointObj["externalGraphic"] = externalGraphic;
                        		if(points.rotation) pointObj["rotation"] = points.rotation;
                        		if(points.angleScale) pointObj["angleScale"] = points.angleScale;
                        		if(points.angleTranslation) pointObj["angleTranslation"] = points.angleTranslation;
                        	} else if (points.name.indexOf('ShapeMarker') != -1) {
                        		pointObj["graphicName"] = points.graphicName;
                        		pointObj["fillColor"] = points.fillColor;
                        		pointObj["fillOpacity"] = points.fillOpacity;
                        		if(points.strokeColor) pointObj["strokeColor"] = points.strokeColor;
                        		if(points.strokeOpacity) pointObj["strokeOpacity"] = points.strokeOpacity;
                        		if(points.strokeWidth) pointObj["strokeWidth"] = points.strokeWidth;
                        		if(points.strokeLinejoin) pointObj["strokeLinejoin"] = points.strokeLinejoin;
                        		if(points.strokeLinecap) pointObj["strokeLinecap"] = points.strokeLinecap;
                        	}
                        }
                        ruleObj.symbolizer["point"] = pointObj
                    }
                    if (lines) {
                        var lineObj = {};
                        var name = lines.name;
                        if (name == "Line") {
                            var strokeColor = lines.strokeColor;
                            lineObj["stroke"] = strokeColor;
                            var strokeWidth = lines.strokeWidth;
                            lineObj["strokeWidth"] =
                                strokeWidth;
                            var strokeOpacity = lines.strokeOpacity;
                            lineObj["strokeOpacity"] = strokeOpacity;
                            var strokeLinecap = lines.strokeLinecap;
                            lineObj["strokeLinecap"] = strokeLinecap;
                            var strokeDasharray = lines.strokeDashstyle;
                            if (strokeDasharray) lineObj["strokeDashArray"] = strokeDasharray;/*
																								 * console.log("\uc120
																								 * \uc2a4\ud0c0\uc77c
																								 * strokeLinecap
																								 * \uc9c0\uc815\uc548\ub428!");
																								 */
                            else lineObj["strokeDashArray"] = "solid"
                        } else if (name == "CompositeLineCap") lineObj.arrow = true;
                        else if (name == "CompositeLineMarker") lineObj.marker = true;
                        ruleObj.symbolizer["line"] = lineObj
                    }
                    if (polygons) {
                        var polyObj = {};
                        if (polygons.fill) {
                            var fillColor = polygons.fillColor;
                            if (fillColor) polyObj["fillColor"] = fillColor;
                            var fillOpacity = polygons.fillOpacity;
                            if (fillOpacity) polyObj["fillOpacity"] = fillOpacity
                        }
                        var externalGraphic = polygons.InlineContent;
                        if (externalGraphic) polyObj["externalGraphic"] = externalGraphic;
                        if(lines) {
                        	$.extend(true,polyObj,ruleObj.symbolizer.line);
                        	delete ruleObj.symbolizer.line;
                        }
                        ruleObj.symbolizer["polygon"] = polyObj
                    }
                    if (texts) {
                        var textObj = {};
                        var label = texts.label;
                        if (label.length > 0) textObj.label = label;
                        var fontFamily = texts.fontFamily;
                        if (fontFamily) textObj["fontFamily"] = fontFamily;
                        var fontSize =
                            texts.fontSize;
                        if (fontSize) textObj["fontSize"] = fontSize;
                        var fontStyle = texts.fontStyle;
                        if (fontStyle) textObj["fontStyle"] = fontStyle;
                        var fontWeight = texts.fontWeight;
                        if (fontWeight) textObj["fontWeight"] = fontWeight;
                        if (texts.fill) {
                            var fillColor = texts.fillColor;
                            if (fillColor) textObj["fillColor"] = fillColor;
                            var fillOpacity = texts.fillOpacity;
                            if (fillOpacity) textObj["fillOpacity"] = fillOpacity;
                            var haloColor = texts.haloColor;
                            if (haloColor) textObj["haloColor"] = haloColor;
                            var haloOpacity = texts.haloOpacity;
                            if (haloOpacity) textObj["haloOpacity"] =
                                haloOpacity
                        }
                        if(texts.backgroundType !== "NONE") {
                        	var backgroundType = texts.backgroundType;
                        	if (backgroundType) textObj["backgroundType"] = backgroundType;
                        	var backgroundFill = texts.backgroundFill;
                        	if (backgroundFill) textObj["backgroundFill"] = backgroundFill;
                        	var backgroundLine = texts.backgroundLine;
                        	if (backgroundLine) textObj["backgroundLine"] = backgroundLine;
                        	var backgroundOffset = texts.backgroundOffset;
                        	if (backgroundOffset) textObj["backgroundOffset"] = backgroundOffset;
                        	var backgroundAlign = texts.backgroundAlign;
                        	if (backgroundAlign) textObj["backgroundAlign"] = backgroundAlign;
                        }
                        var textPointBase = texts.textPointBase;
                    	if (textPointBase) textObj["textPointBase"] = textPointBase;
                    	var textPointPosition = texts.textPointPosition;
                    	if (textPointPosition) textObj["textPointPosition"] = textPointPosition;
                    	var textPointArrange = texts.textPointArrange;
                    	if (textPointArrange) textObj["textPointArrange"] = textPointArrange;
                    	var codeDomain = texts.codeDomain;
                    	if (codeDomain) textObj["codeDomain"] = codeDomain;
                    	var textArrangePos = texts.textArrangePos;
                    	if (textArrangePos) textObj["textArrangePos"] = textArrangePos;
                    	var textArrangeLine = texts.textArrangeLine;
                    	if (textArrangeLine) textObj["textArrangeLine"] = textArrangeLine;
                    	var textArrangeGap = texts.textArrangeGap;
                    	if (textArrangeGap) textObj["textArrangeGap"] = textArrangeGap;
                    	
                    	
                        ruleObj.symbolizer["text"] = textObj
                    }
                    userdObj.rules.push(ruleObj)
                }
                namedObj.userStyle.push(userdObj)
            }
            obj.namedLayers.push(namedObj)
        }
        if (callback) {
            callback(obj);
            return true
        } else return obj
    },
    getFeatureInfo: function(serviceUrl, map, options, callback, callbackParams) {
        var params = {
            service: this.service,
            version: this.version,
            request: "GetFeatureInfo",
            layers: "",
            styles: "",
            query_layers: "",
            crs: "EPSG:4326",
            info_format: "text/xml",
            format: "image/jpeg",
            feature_count: 9999,
            bbox: map.getExtent().toBBOX(),
            i: parseInt(map.getSize().w /
                2),
            j: parseInt(map.getSize().h / 2),
            height: map.getSize().h,
            width: map.getSize().w
        };
        if (options.layers && !options.styles) options.styles = options.layers;
        if (options.layers && !options.query_layers) options.query_layers = options.layers;
        $.extend(params, options);
        var obj = this;
        GMapUtil.sendProxyGet(serviceUrl, GUtil.fn_convert_objToStr(params), function(res) {
            obj.parseGetFeatureInfo(res, callback, callbackParams)
        })
    },
    parseGetFeatureInfo: function(res, callback, callbackParams) {
        var arr = [];
        var layers = res.getElementsByTagName("Layer");
        for (var i = 0, len = layers.length; i < len; i++) {
            var obj = {};
            obj.name = layers[i].getAttribute("name");
            obj.fields = {};
            fields = layers[i].getElementsByTagName("Field");
            for (var j = 0, fLen = fields.length; j < fLen; j++) obj.fields[fields[j].getAttribute("name")] = $(fields[j]).text();
            arr.push(obj)
        }
        callback(arr, callbackParams)
    }
};
GRequest.WFS = {
    SERVICES: "WFS",
    VERSION: "1.1.0",
    REQUEST: null,
    format: {
        gml: new OpenLayers.Format.GML,
        filter: new OpenLayers.Format.Filter({
            version: "1.1.0"
        }),
        xml: new OpenLayers.Format.XML
    },
    getCapability: function(serviceUrl, callback) {
        var params = GUtil.fn_convert_objToStr({
            SERVICE: this.SERVICES,
            VERSION: this.VERSION,
            REQUEST: "GetCapabilities"
        });
        GMapUtil.sendProxyGet(serviceUrl, params, function(res) {
            var format = new OpenLayers.Format.WFSCapabilities({
                version: "1.1.0"
            });
            callback(format.read(res.xml))
        })
    },
    extendParams: function(params,
        options) {
        OpenLayers.Util.extend(params, options);
        if (options.tables && !(options.tables instanceof Array)) params.tables = [options.tables];
        if (options.fields && !(options.values instanceof Array)) params.fields = [options.fields];
        if (options.values && !(options.values instanceof Array)) params.values = [options.values];
        if (options.sortFields && !(options.sortFields instanceof Array)) params.sortFields = [options.sortFields];
        if (options.sortOrders && !(options.sortOrders instanceof Array)) params.sortOrders = [options.sortOrders]
    },
    getSortBy: function(fields, orders) {
        var str = "";
        str += "<ogc:SortBy>";
        for (var i = 0, len = fields.length; i < len; i++) {
            str += "<ogc:SortProperty>";
            str += "<ogc:PropertyName>";
            str += fields[i];
            str += "</ogc:PropertyName>";
            str += "<ogc:SortOrder>";
            str += orders[i] ? orders[i] : "ASC";
            str += "</ogc:SortOrder>";
            str += "</ogc:SortProperty>"
        }
        str += "</ogc:SortBy>";
        return str
    },
    getFeatureById: function(serviceUrl, parameters, callback, options) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            tables: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' + params.prefix + ":" + params.tables[i] + '" ' + useDomain + "  >";
            if (i < params.values.length) queryStr += '<ogc:Filter xmlns:ogc="http://www.opengis.net/ogc"><ogc:FeatureId fid="' + params.tables[i] + "." + params.values[i] + '"/></ogc:Filter>';
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr, callback, options)
    },
    getFeatureByMultiId: function(serviceUrl, parameters, callback, options, sync) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            tables: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        $.extend(params, parameters);
        var queryStr = "";
        var useDomain = params.useDomain ? 'useDomain="true"' : "";
        queryStr += '<wfs:Query typeName="' + params.prefix + ":" + params.tables[0] + '" ' + useDomain + "  >";
        queryStr += '<ogc:Filter xmlns:ogc="http://www.opengis.net/ogc">';
        for (var i = 0, len = params.values.length; i < len; i++) {
            queryStr += '<ogc:FeatureId fid="' + params.tables[0] + "." + params.values[i] + '"/>';
        }
        if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
        queryStr += "</ogc:Filter></wfs:Query>"
        this.getFeature(serviceUrl, params, queryStr, callback, options, sync)
    },
    
    getFeatureByComparison: function(serviceUrl, parameters, callback, options) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            type: "==",
            tables: [],
            fields: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' + params.prefix + ":" + params.tables[i] +
                '" ' + useDomain + "  >";
            var filter = new OpenLayers.Filter.Comparison({
                type: params.type,
                property: params.fields[i],
                value: params.values[i]
            });
// queryStr += this.format.filter.write(filter).xml;
            queryStr += this.format.xml.write(this.format.filter.write(filter));
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr, callback, options)
    },
    getFeatureByContains: function(serviceUrl, parameters, callback, options, sync) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            type: OpenLayers.Filter.Spatial.CONTAINS,
            tables: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        var oXMLHttpRequest = window.XMLHttpRequest;
        var bGecko = !!window.controllers,
            bIE = window.document.all && !window.opera,
            bIE7 = bIE && (window.navigator.userAgent.match(/MSIE ([\.0-9]+)/) && RegExp.$1 == 7 || window.navigator.userAgent.match("rv:11.0"));
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' +
                params.prefix + ":" + params.tables[i] + '" ' + useDomain + "  >";
            var filter = new OpenLayers.Filter.Spatial({
                type: params.type,
                property: "G2_SPATIAL",
                value: params.values[0],
            });
            if (oXMLHttpRequest && !bIE7) queryStr += this.format.xml.write(this.format.filter.write(filter));
            else queryStr += this.format.filter.write(filter).xml;
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr, callback, options,sync)
    },
    getFeatureByWithin: function(serviceUrl, parameters, callback, options, sync) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            type: OpenLayers.Filter.Spatial.WITHIN,
            tables: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        var oXMLHttpRequest = window.XMLHttpRequest;
        var bGecko = !!window.controllers,
            bIE = window.document.all && !window.opera,
            bIE7 = bIE && (window.navigator.userAgent.match(/MSIE ([\.0-9]+)/) && RegExp.$1 == 7 || window.navigator.userAgent.match("rv:11.0"));
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' +
                params.prefix + ":" + params.tables[i] + '" ' + useDomain + "  >";
            var filter = new OpenLayers.Filter.Spatial({
                type: params.type,
                property: "G2_SPATIAL",
                value: params.values[0],
            });
            if (oXMLHttpRequest && !bIE7) queryStr += this.format.xml.write(this.format.filter.write(filter));
            else queryStr += this.format.filter.write(filter).xml;
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr, callback, options,sync)
    },
    getFeatureByDWithin: function(serviceUrl, parameters, callback, options, sync) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            type: OpenLayers.Filter.Spatial.DWITHIN,
            tables: [],
            distance: 1E3,
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        var oXMLHttpRequest = window.XMLHttpRequest;
        var bGecko = !!window.controllers,
            bIE = window.document.all && !window.opera,
            bIE7 = bIE && (window.navigator.userAgent.match(/MSIE ([\.0-9]+)/) && RegExp.$1 == 7 || window.navigator.userAgent.match("rv:11.0"));
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' +
                params.prefix + ":" + params.tables[i] + '" ' + useDomain + "  >";
            var filter = new OpenLayers.Filter.Spatial({
                type: params.type,
                property: "G2_SPATIAL",
                value: params.values[0],
                distance: params.distance,
                distanceUnits: "m"
            });
            if (oXMLHttpRequest && !bIE7) queryStr += this.format.xml.write(this.format.filter.write(filter));
            else queryStr += this.format.filter.write(filter).xml;
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr,
            callback, options, sync)
    },
    getFeatureByGeometry: function(serviceUrl, parameters, callback, options, sync) {
        var params = {
            maxFeatures: 9999,
            prefix: "",
            type: OpenLayers.Filter.Spatial.INTERSECTS,
            tables: [],
            values: [],
            sortFields: [],
            sortOrders: [],
            useDomain: false
        };
        this.extendParams(params, parameters);
        var queryStr = "";
        for (var i = 0, len = params.tables.length; i < len; i++) {
            var useDomain = params.useDomain ? 'useDomain="true"' : "";
            queryStr += '<wfs:Query typeName="' + params.prefix + ":" + params.tables[i] + '" ' + useDomain + "  >";
            var filter = new OpenLayers.Filter.Spatial({
                type: params.type,
                property: "G2_SPATIAL",
                value: params.values[0]
            });
            queryStr += this.format.xml.write(this.format.filter.write(filter));
            if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
            queryStr += "</wfs:Query>"
        }
        this.getFeature(serviceUrl, params, queryStr, callback, options, sync)
    },    
    getFeature: function(serviceUrl, params, filter, callback, options, sync) {
        var wfsStr = "";
        wfsStr += '<wfs:GetFeature service="WFS" version="1.1.0" maxFeatures="' + params.maxFeatures + '" xmlns:ehmp="http://health-e-waterways.org" xmlns:wfs="http://www.opengis.net/wfs" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">';
        wfsStr += filter;
        wfsStr += "</wfs:GetFeature>";
        var control = this;
        if(sync){
        	GMapUtil.sendProxyPostSync(serviceUrl, wfsStr, function(res) {
                control.parseGetFeature(res, callback, options)
            })
        }else{
        	GMapUtil.sendProxyPost(serviceUrl, wfsStr, function(res) {
                control.parseGetFeature(res, callback, options)
            })	
        }        
    },
    parseGetFeature: function(res, callback, options) {
        if (res.responseXML) res = res.responseXML;
        var arr = [];
        var success = true;
        var featureCollection;
        if (this.getBrowserName() == "msie" || this.getBrowserName() == "firefox") featureCollection = res.getElementsByTagName("wfs:FeatureCollection");
        else featureCollection = res.getElementsByTagName("FeatureCollection");
        if (featureCollection && featureCollection[0]) {
            if (featureCollection[0].getAttribute("numberOfFeatures") != 0) {
                var featureMembers;
                if (this.getBrowserName() == "msie" || this.getBrowserName() == "firefox") featureMembers = featureCollection[0].getElementsByTagName("gml:featureMember");
                else featureMembers = featureCollection[0].getElementsByTagName("featureMember");
                for (var i = 0, len = featureMembers.length; i < len; i++) {
                    var tmpArr = featureMembers[i].firstChild.getAttribute("fid").split(".");
                    var tmpTable = tmpArr[0];
                    var index =
                        null;
                    for (var j in arr)
                        if (arr[j].table == tmpTable) {
                            index = j;
                            break
                        }
                    if (!index) {
                        var obj = {
                            table: tmpTable,
                            results: []
                        };
                        arr.push(obj)
                    } else obj = arr[index];
                    var result = {
                        g2id: tmpArr[1],
                        feature: null,
                        title: tmpArr[1],
                        fields: {}
                    };
                    var field = featureMembers[i].firstChild.firstChild;
                    while (field) {
                        if (field.tagName.replace(field.prefix + ":", "").toLowerCase() == "g2_spatial"){
                        	var parsedFeature = this.format.gml.parseFeature(field);
							parsedFeature.attributes.fid = featureMembers[i].firstChild.getAttribute("fid");
							parsedFeature.renderIntent = '';
							parsedFeature.featureType = (parsedFeature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','')).toLowerCase();
							parsedFeature.modified = {
									geometry : {}
							};
							result["feature"] = parsedFeature;
                        }
                        else {
                            if (options && options.titles && options.titles[obj.table] && field.tagName.replace(field.prefix + ":",
                                    "").toLowerCase() == options.titles[obj.table].toLowerCase())
                                if (typeof field.text === "undefined") result.title = field.textContent;
                                else result.title = field.text;
                            if (field.tagName.replace(field.prefix + ":", "").toLowerCase() != "boundedby")
                                if (typeof field.text === "undefined") result.fields[field.tagName.replace(field.prefix + ":", "")] = field.textContent;
                                else result.fields[field.tagName.replace(field.prefix + ":", "")] = field.text
                        }
                        field = field.nextSibling
                    }
                    result.feature.fid = featureMembers[i].firstChild.getAttribute("fid");
                    obj.results.push(result)
                }
            }
        } else success =
            false;
        if (options && options.alias) this.getRequestAlias(arr, success, callback, options);
        else callback({
            data: arr,
            success: function() {
                return success
            }
        })
    },
    getBrowserName: function() {
        var name = "";
        var ua = navigator.userAgent.toLowerCase();
        if (ua.indexOf("opera") != -1) name = "opera";
        else if (ua.indexOf("msie") != -1 || navigator.appName == "Netscape" && navigator.userAgent.search("Trident") != -1) name = "msie";
        else if (ua.indexOf("safari") != -1) name = "safari";
        else if (ua.indexOf("mozilla") != -1)
            if (ua.indexOf("firefox") != -1) name = "firefox";
            else name = "mozilla";
        return name
    },
    insert: function(serviceUrl, features, prefix, table, fields, values, callback) {
        if (features && !(features instanceof Array)) features = [features];
        var wfsStr = "";
        wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf">';
        wfsStr += '<wfs:Insert srsName="EPSG:4326">';
        wfsStr += "<" + prefix + ":" + table + " xmlns:" + prefix + '="http://geogate.g-inno.com/dataserver/' + prefix + '">';
        wfsStr += "<" + prefix + ":G2_SPATIAL>";
        wfsStr += this.createGmlXml(features);
        wfsStr += "</" + prefix + ":G2_SPATIAL>";
        if (fields && fields.length > 0) wfsStr += this.createAttrXml(prefix, fields, values);
        wfsStr += "</" + prefix + ":" + table + ">";
        wfsStr += "</wfs:Insert>";
        wfsStr += "</wfs:Transaction>";
        $("#txtTest").val(wfsStr);
        var control = this;
        GMapUtil.sendProxyPostSync(serviceUrl, wfsStr, function(res) {
            var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");
            if (transactionResponse.length > 0) {
                var arr = [];
                var totalInserted = transactionResponse[0].getElementsByTagName("wfs:totalInserted");
                var featureId = transactionResponse[0].getElementsByTagName("ogc:FeatureId");
                for (var i = 0, len = featureId.length; i < len; i++) arr.push(featureId[i].getAttribute("fid"));
                if (callback) callback({
                    count: totalInserted[0].text,
                    ids: arr
                })
            }
        })
    },
    update: function(serviceUrl, features, prefix, table, fields, values, value, callback) {
        if (features && !(features instanceof Array)) features = [features];
        var wfsStr =
            "";
        wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf">';
        wfsStr += '<wfs:Update typeName="' + prefix + ":" + table + '" xmlns:' + prefix + '="http://geogate.g-inno.com/dataserver/' + prefix + '">';
        wfsStr += "<wfs:Property>";
        wfsStr += "<wfs:Name>G2_SPATIAL</wfs:Name>";
        wfsStr += "<wfs:Value>";
        wfsStr +=
            this.createGmlXml(features);
        wfsStr += "</wfs:Value>";
        wfsStr += "</wfs:Property>";
        if (fields && fields.length > 0) wfsStr += this.updateAttrXml(fields, values);
        wfsStr += "<ogc:Filter>";
        wfsStr += '<ogc:PropertyIsEqualTo matchCase="true">';
        wfsStr += "<ogc:PropertyName>" + table + ".GID</ogc:PropertyName> ";
        wfsStr += "<ogc:Literal>" + value + "</ogc:Literal> ";
        wfsStr += "</ogc:PropertyIsEqualTo>";
        wfsStr += "</ogc:Filter>";
        wfsStr += "</wfs:Update>";
        wfsStr += "</wfs:Transaction>";
        var control = this;
        GMapUtil.sendProxyPostSync(serviceUrl, wfsStr,
            function(res) {
                var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");
                if (transactionResponse.length > 0) {
                    var totalUpdated = transactionResponse[0].getElementsByTagName("wfs:totalUpdated");
                    if (callback) callback({
                        count: totalUpdated[0].textContent
                    })
                }
            })
    },
    del: function(prefix, table, value, callback) {
        var wfsStr = "";
        wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf" releaseAction="ALL">';
        wfsStr += '<wfs:Delete typeName="' + prefix + ":" + table + '">';
        wfsStr += '<ogc:Filter xmlns:ogc="http://www.opengis.net/ogc"><ogc:FeatureId fid="' + table + "." + value + '"/></ogc:Filter>';
        wfsStr += "</wfs:Delete>";
        wfsStr += "</wfs:Transaction>";
        var control = this;
        GMapUtil.sendProxyPostSync(serviceUrl, wfsStr, function(res) {
            var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");
            if (transactionResponse.length > 0) {
                var totalDeleted = transactionResponse[0].getElementsByTagName("wfs:totalDeleted");
                if (callback) callback({
                    count: totalDeleted[0].textContent
                })
            }
        })
    },
    createGmlXml: function(features) {
        var lineCount = 0;
        for (var i in features)
            if (features[i].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") lineCount++;
        var xmlStr = "";
        if (features[0].geometry.CLASS_NAME == "OpenLayers.Geometry.Point") xmlStr += this.createPointXml(features[0].geometry);
        if (lineCount == 1) {
            xmlStr += '<gml:LineString xmlns:gml="http://www.opengis.net/gml">';
            xmlStr += this.createLineStringXml(features[0].geometry);
            xmlStr += "</gml:LineString>"
        } else if (lineCount > 1) {
            xmlStr += '<gml:MultiLineString xmlns:gml="http://www.opengis.net/gml" srsName="EPSG:4326">';
            for (var i in features)
                if (features[i].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
                    xmlStr += "<gml:lineStringMember><gml:LineString>";
                    xmlStr += this.createLineStringXml(features[i].geometry);
                    xmlStr += "</gml:LineString></gml:lineStringMember>"
                }
            xmlStr += "</gml:MultiLineString>"
        }
		
		if(features[0].geometry.CLASS_NAME == "OpenLayers.Geometry.Polygon"){
			xmlStr += '<gml:Polygon xmlns:gml="http://www.opengis.net/gml">';
			xmlStr += '<gml:exterior>';
			xmlStr += '<gml:LinearRing>';
			xmlStr += this.createPolygonXml(features[0].geometry);
			xmlStr += '</gml:LinearRing>';
			xmlStr += '</gml:exterior>';
			xmlStr += '</gml:Polygon>';
		}
        return xmlStr
    },
    createPointXml: function(geometry) {
        var str = "";
        str += '<gml:Point xmlns:gml="http://www.opengis.net/gml"><gml:pos>';
        str += geometry.x + " ";
        str += geometry.y;
        str += "</gml:pos></gml:Point>";
        return str
    },
    createLineStringXml: function(geometry) {
        var str =
            "";
        str += "<gml:posList>";
        for (var i in geometry.components) {
            str += geometry.components[i].x + " ";
            str += geometry.components[i].y + " "
        }
        str += "</gml:posList>";
        return str
    },
	createPolygonXml : function(geometry){
		var geom = geometry.components[0];
		var str = '';
		str += '<gml:posList srsDimension="2" dimension="2">';
		for (var i in geom.components) {
			str += geom.components[i].x + " ";
			str += geom.components[i].y + " ";
		}
		str += '</gml:posList>';
		return str;
	},
    createAttrXml: function(prefix, fields, values) {
        var str = "";
        for (var i = 0, len = fields.length; i < len; i++) str += "<" + prefix + ":" + fields[i] + ">" + values[i] + "</" + prefix + ":" + fields[i] + ">";
        return str
    },
    updateAttrXml: function(fields, values) {
        var str = "";
        for (var i = 0, len = fields.length; i < len; i++) {
            str += "<wfs:Property>";
            str += "<wfs:Name>" + fields[i] + "</wfs:Name>";
            str +=
                "<wfs:Value>" + values[i] + "</wfs:Value>";
            str += "</wfs:Property>"
        }
        return str
    },
    getRequestAlias: function(arr, success, callback, options) {
        var control = this;
        var tables = [];
        var fields = [];
        for (var i = 0, len = arr.length; i < len; i++)
            for (var j in arr[i].results[0].fields) {
                tables.push(arr[i].table);
                fields.push(j)
            }
        $.post("/gmap/attr/getAlias.do", {
            tables: tables.join(),
            fields: fields.join()
        }, function(res) {
            for (var i = 0, len = arr.length; i < len; i++) arr[i].alias = res.data[i];
            callback({
                data: arr,
                success: function() {
                    return success
                }
            })
        }, "json")
    },
    orderGetFeatureArr: function(arr, field, order) {
        var len = arr.length;
        for (var i = len - 1; i > 0; i--)
            for (var j = 0; j < i; j++)
                if (order.toLowerCase() == "desc") {
                    if (arr[j]["fields"][field] < arr[j + 1]["fields"][field]) GUtil.Array.fn_swap_element(arr, j, j + 1)
                } else if (arr[j]["fields"][field] > arr[j + 1]["fields"][field]) GUtil.Array.fn_swap_element(arr, j, j + 1)
    }
};
GRequest.WPS = {
    SERVICES: "WPS",
    VERSION: "1.0.0",
    format: {
        gml: new OpenLayers.Format.GML,
        filter: new OpenLayers.Format.Filter({
            version: "1.0.0"
        })
    },
    getNearFeature: function(serviceUrl, dataInputs, callback) {
        var params = {
            Service: this.SERVICES,
            Version: this.VERSION,
            Request: "Execute",
            Identifier: "NearFeature",
            DataInputs: "",
            Responsedocument: "NEARFEATURE_OUTPUT"
        };
        params.DataInputs = "[";
        params.DataInputs += GUtil.fn_convert_objToStr(dataInputs, ";");
        params.DataInputs += "]";
        var control = this;
        GMapUtil.sendProxyPost(serviceUrl,
            GUtil.fn_convert_objToStr(params),
            function(res) {
                control.parseGetFeature(res, callback)
            })
    },
    parseGetFeature: function(res, callback) {
        var arr = [];
        var success = true;
        var featureCollection = res.getElementsByTagName("wfs:FeatureCollection");
        if (featureCollection && featureCollection[0]) {
            var featureMembers = featureCollection[0].getElementsByTagName("gml:featureMember");
            for (var i = 0, len = featureMembers.length; i < len; i++)
                for (var i = 0, len = featureMembers.length; i < len; i++) {
                    var tables = featureMembers[i].firstChild;
                    var tmpTable =
                        tables.tagName;
                    var index = null;
                    for (var j in arr)
                        if (arr[j].table == tmpTable) {
                            index = j;
                            break
                        }
                    if (!index) {
                        var obj = {
                            table: tmpTable,
                            results: []
                        };
                        arr.push(obj)
                    } else obj = arr[index];
                    var result = {
                        feature: null,
                        fields: {}
                    };
                    var field = featureMembers[i].firstChild.firstChild;
                    while (field) {
                        if (field.tagName.toLowerCase() == "geometry"){
                        	var parsedFeature = this.format.gml.parseFeature(field);
							parsedFeature.attributes.fid = '';
							parsedFeature.renderIntent = '';
							parsedFeature.featureType = (parsedFeature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','')).toLowerCase();
							parsedFeature.modified = {
									geometry : {}
							};
							result["feature"] = parsedFeature;
                        }
                        else if (typeof field.text === "undefined") result.fields[field.tagName] = field.textContent;
                        else result.fields[field.tagName] =
                            field.text;
                        field = field.nextSibling
                    }
                    obj.results.push(result)
                }
        } else success = false;
        callback({
            data: arr,
            success: function() {
                return success
            }
        })
    },
    getHoldWaterInfo: function(serviceUrl, dataInputs, callback) {
        var params = {
            Service: this.SERVICES,
            Version: this.VERSION,
            Request: "Execute",
            Identifier: "HoldWater",
            DataInputs: "",
            Responsedocument: "HOLDWATER_OUTPUT"
        };
        params.DataInputs = "[";
        params.DataInputs += GUtil.fn_convert_objToStr(dataInputs, ";");
        params.DataInputs += "]";
        var control = this;
        GMapUtil.sendProxyPost(serviceUrl, GUtil.fn_convert_objToStr(params),
            function(res) {
                control.parseHoldWaterInfo(res, callback)
            })
    },
    parseHoldWaterInfo: function(res, callback) {
        var arr = [];
        var success = true;
        var results = {};
        var holdWater = res.getElementsByTagName("prof:HoldWater");
        var obj = {
            results: []
        };
        arr.push(obj);
        var result = {
            pipes: [],
            valves: [],
            fires: []
        };
        if (holdWater && holdWater[0]) {
            var field = holdWater[0].firstChild;
            var fieldText = field.text;
            if (typeof fieldText === "undefined") fieldText = field.textContent;
            while (field) {
                if (field.tagName.toLowerCase().split(":")[1] == "pipes") result["pipes"].push(fieldText);
                else if (field.tagName.toLowerCase().split(":")[1] == "valves") result["valves"].push(fieldText);
                else if (field.tagName.toLowerCase().split(":")[1] == "fires") result["fires"].push(fieldText);
                field = field.nextSibling
            }
            obj.results.push(result)
        } else success = false;
        callback({
            data: arr,
            success: function() {
                return success
            }
        })
    }
};
GLonLat = OpenLayers.Class(OpenLayers.LonLat, {
    CLASS_NAME: "GLonLat"
});
GBounds = OpenLayers.Class(OpenLayers.Bounds, {
    CLASS_NAME: "GBounds"
});
GBounds.fromString = function(str, ch) {
    if (!ch) var ch = ",";
    var bounds = str.split(ch);
    return GBounds.fromArray(bounds)
};
GBounds.fromArray = function(bbox) {
    return new GBounds(parseFloat(bbox[0]), parseFloat(bbox[1]), parseFloat(bbox[2]), parseFloat(bbox[3]))
};
GPixel = OpenLayers.Class(OpenLayers.Pixel, {
    CLASS_NAME: "GPixel"
});
GSize = OpenLayers.Class(OpenLayers.Size, {
    CLASS_NAME: "GSize"
});
GWMS = OpenLayers.Class(OpenLayers.Layer.WMS, {
    DEFAULT_PARAMS: {
        service: "WMS",
        version: "1.3.0",
        request: "GetMap",
        styles: "",
        exceptions: "application/vnd.ogc.se_blank",
        format: "image/jpeg",
        crs: "SR_ORG:6640",
        transparent: true
    },
    singleTile: true,
    ratio: 1,
    buffer: 0,
    transitionEffect: "resize",
    initialize: function(name, url, params, options) {
        if (GError.debug) this.chkParams(name, url, params, options);
        var newArguments = [];
        params = OpenLayers.Util.upperCaseObject(params);
        if (parseFloat(params.VERSION) >= 1.3 && !params.EXCEPTIONS) params.EXCEPTIONS =
            "application/vnd.ogc.se_blank";
        newArguments.push(name, url, params, options);
        OpenLayers.Layer.Grid.prototype.initialize.apply(this, newArguments);
        OpenLayers.Util.applyDefaults(this.params, OpenLayers.Util.upperCaseObject(this.DEFAULT_PARAMS));
        if (!this.noMagic && this.params.TRANSPARENT && this.params.TRANSPARENT.toString().toLowerCase() == "true") {
            if (options == null || !options.isBaseLayer) this.isBaseLayer = false;
            if (this.params.FORMAT == "image/jpeg") this.params.FORMAT = OpenLayers.Util.alphaHack() ? "image/gif" : "image/png"
        }
    },
    getParam: function(property) {
        if (property) {
            for (var i in this.params)
                if (i.toUpperCase() ==
                    property.toUpperCase()) return this.params[i];
            return false
        } else;
    },
    getParams: function() {
        return this.params
    },
    chkParams: function(name, url, params, options) {
        if (!name) GError.create_obj(this, "Layer Name(\ub808\uc774\uc5b4 \uba85)");
        else if (!url) GError.create_obj(this, "Url (\uc11c\ube44\uc2a4 \uc8fc\uc18c)");
        else if (!(params && params.layers)) GError.create_obj(this, "Parameter layers (\uc694\uccad \ub808\uc774\uc5b4 \uba85 \ub9ac\uc2a4\ud2b8)")
    },
    CLASS_NAME: "GWMS"
});
GWMSPost = OpenLayers.Class(OpenLayers.Layer.WMS, {
    tileClass: null,
    unsupportedBrowsers: [],
    SUPPORTED_TRANSITIONS: [],
    initialize: function(name, url, params, options) {
        var newArguments = [];
        newArguments.push(name, url, params, options);
        OpenLayers.Layer.WMS.prototype.initialize.apply(this, newArguments);
        this.tileClass = OpenLayers.Util.indexOf(this.unsupportedBrowsers, OpenLayers.Util.getBrowserName()) != -1 ? OpenLayers.Tile.Image : OpenLayers.Tile.Image.IFrame
    },
    addTile: function(bounds, position) {
        return new this.tileClass(this,
            position, bounds, null, this.tileSize)
    },
    getParam: function(property) {
        if (property) {
            for (var i in this.params)
                if (i.toUpperCase() == property.toUpperCase()) return this.params[i];
            return false
        } else;
    },
    getParams: function() {
        return this.params
    },
    chkParams: function(name, url, params, options) {
        if (!name) GError.create_obj(this, "Layer Name(\ub808\uc774\uc5b4 \uba85)");
        else if (!url) GError.create_obj(this, "Url (\uc11c\ube44\uc2a4 \uc8fc\uc18c)");
        else if (!(params && params.layers)) GError.create_obj(this, "Parameter layers (\uc694\uccad \ub808\uc774\uc5b4 \uba85 \ub9ac\uc2a4\ud2b8)")
    },
    CLASS_NAME: "GWMSPost"
});
GTileCache = OpenLayers.Class(OpenLayers.Layer.TileCache, {
    version: null,
    transitionEffect: "resize",
    buffer: 0,
    initialize: function(name, url, layername, options) {
        if (GError.debug) this.chkParams(name, url, layername, options);
        this.layername = layername;
        OpenLayers.Layer.Grid.prototype.initialize.apply(this, [name, url, {}, options]);
        this.extension = this.format.split("/")[1].toLowerCase()
    },
    getURL: function(bounds) {
        var res = this.map.getResolution();
        var bbox = this.maxExtent;
        var size = this.tileSize;
        var tileX = bounds.bottom;
        var tileY =
            bounds.left;
        var tileZ = this.serverResolutions != null ? OpenLayers.Util.indexOf(this.serverResolutions, res) : this.map.getZoom();

        function zeroPad(number, length) {
            number = String(number);
            var zeros = [];
            for (var i = 0; i < length; ++i) zeros.push("0");
            return zeros.join("").substring(0, length - number.length) + number
        }
        var components = [this.layername, zeroPad(tileZ, 2), zeroPad(parseInt(tileX / 1E6), 3), zeroPad(parseInt(tileX / 1E3) % 1E3, 3), zeroPad(parseInt(tileX) % 1E3, 3), zeroPad(parseInt(tileY / 1E6), 3), zeroPad(parseInt(tileY / 1E3) % 1E3,
            3), zeroPad(parseInt(tileY) % 1E3, 3) + "." + this.extension];
        var path = components.join("/");
        if (this.version) path += "?v=" + this.version;
        var url = this.url;
        if (url instanceof Array) url = this.selectUrl(path, url);
        url = url.charAt(url.length - 1) == "/" ? url : url + "/";
        return url + path
    },
    chkParams: function(name, url, layername, options) {
        if (!name) GError.create_obj(this, "Layer Name(\ub808\uc774\uc5b4 \uba85)");
        if (!url) GError.create_obj(this, "Url (\uc11c\ube44\uc2a4 \uc8fc\uc18c)");
        if (!layername) GError.create_obj(this, "TileCache Layer Name (\ud0c0\uc77c \uc11c\ube44\uc2a4 \uc774\ub984)")
    },
    CLASS_NAME: "GTileCache"
});
GVector = OpenLayers.Class(OpenLayers.Layer.Vector, {
	initialize: function(name, options) {
        OpenLayers.Layer.prototype.initialize.apply(this, arguments);
        if (!this.renderer || !this.renderer.supported()) {  
            this.assignRenderer();
        }
        if (!this.renderer || !this.renderer.supported()) {
            this.renderer = null;
            this.displayError();
        } 
        if (!this.styleMap) {
            this.styleMap = new OpenLayers.StyleMap();
        }
        this.features = [];
        this.selectedFeatures = [];
        this.unrenderedFeatures = {};
        if(this.strategies){
            for(var i=0, len=this.strategies.length; i<len; i++) {
                this.strategies[i].setLayer(this);
            }
        }
        if(options){
        	if(options.isWfsLayer){
        		var idNum = this.id.split("_")[1];
        		this.id = "WFSLayer_"+idNum;
        	}
        }
    },
	redraw: function() {
        var redrawn = false;
        if (this.map) {
            this.inRange = this.calculateInRange();
            var extent = this.getExtent();
            if (extent && this.inRange && this.visibility) {
                var zoomChanged = true;
                this.moveTo(extent, zoomChanged, false);
                this.events.triggerEvent("moveend", {
                    "zoomChanged": zoomChanged
                });
                redrawn = true
            }
        }
        if (this.map.paddingForPopups)
            for (var i in this.map.popups) {
                var popup = this.map.popups[i];
                if (popup && popup.attributes && popup.attributes.featureType && popup.attributes.featureType ==
                    "Text" && popup.type == "draw") {
                    if (popup.attributes["font-family"]) $("#" + popup.id).css("font-family", popup.attributes["font-family"]);
                    if (popup.attributes["font-size"]) $("#" + popup.id).css("font-size", popup.attributes["font-size"]);
                    if (popup.attributes["color"]) $("#" + popup.id).css("color", popup.attributes["color"])
                    if (popup.attributes["background"]) $("#" + popup.id).css("background", feature.attributes["background_fill"]);
                    if (popup.attributes["border"]) $("#" + popup.id).css("border", "1px solid " +feature.attributes["background_line"]);
                    
                }
                popup.updateSize()
            }
        return redrawn
    },
    drawFeature: function(feature, style) {
        // don't try to draw the feature with the renderer if the layer is not
        // drawn itself
        if (!this.drawn) {
            return;
        }
        if (typeof style != "object") {
            if(!style && feature.state === OpenLayers.State.DELETE) {
                style = "delete";
            }
            var renderIntent = style || feature.renderIntent;
            // ehyun. 2016.08.18 : 편집 feature에 style 속성지정하여, [select or delete]가
			// 아닌 경우는 편집레이어에 정의된 자신의 스타일로 feature그리도록(서브심볼포함)
            if(style === 'select' || style === 'delete' || style === 'blink'){
            	style = '';            	
            }else{
            	if(feature.attributes.fid !== undefined && feature.attributes.fid !== ''){
            		var oStyle;
            		if(feature.layer && feature.layer.name === editor.searchLayer.name)
            			style = 'search';
            		oStyle = style === 'search' ? MAP_EDITOR.fn_get_searchFeatureStyle(feature) : MAP_EDITOR.fn_get_editFeatureStyle(feature);            		

            		if(feature.renderIntent === '')
            			feature.renderIntent = 'default';
    	    		feature.style = oStyle;
    	    		
    	    		if(!style){
    	    			if(feature.renderIntent === 'delete')
        	    			feature.style = '';	
    	    		}
            	}
            	style = feature.style || this.style;
            }            	
            if (!style) {
                style = this.styleMap.createSymbolizer(feature, renderIntent);
            }
        }        
        var drawn = this.renderer.drawFeature(feature, style);
        // TODO remove the check for null when we get rid of Renderer.SVG
        if (drawn === false || drawn === null) {
            this.unrenderedFeatures[feature.id] = feature;
        } else {
            delete this.unrenderedFeatures[feature.id];
        }
    },
    parseStyle: function(feature, style) {
        if (typeof style != "object") {
            if (!style && feature.state === OpenLayers.State.DELETE) style = "delete";
            var renderIntent = style || feature.renderIntent;
            style = feature.style || this.style;
            if (!style) style = this.styleMap.createSymbolizer(feature, renderIntent)
        }
        return style
    },
    addPoint: function(lon, lat, attributes) {
        var point = new OpenLayers.Geometry.Point(lon, lat);
        var feature = new OpenLayers.Feature.Vector(point, attributes);
        this.addFeatures(feature);
        return feature
    },
    getGML: function() {
        var gml = new OpenLayers.Format.GML;
        return gml.write(this.features)
    },
    setGML: function(str) {
        var gml = new OpenLayers.Format.GML;
        var features = gml.read(str);
        if (features && features.length) this.addFeatures(features)
    },
    CLASS_NAME: "GVector"
});
OpenLayers.Layer.ArcGISCache = OpenLayers.Class(OpenLayers.Layer.XYZ, {
    url: null,
    tileOrigin: null,
    tileSize: new OpenLayers.Size(256, 256),
    useArcGISServer: true,
    type: "png",
    useScales: false,
    overrideDPI: false,
    initialize: function(name, url, options) {
        OpenLayers.Layer.XYZ.prototype.initialize.apply(this, arguments);
        if (this.resolutions) {
            this.serverResolutions = this.resolutions;
            this.maxExtent = this.getMaxExtentForResolution(this.resolutions[0])
        }
        if (this.layerInfo) {
            var info = this.layerInfo;
            var startingTileExtent = new OpenLayers.Bounds(info.fullExtent.xmin,
                info.fullExtent.ymin, info.fullExtent.xmax, info.fullExtent.ymax);
            this.projection = "EPSG:" + info.spatialReference.wkid;
            this.sphericalMercator = info.spatialReference.wkid == 102100;
            this.units = info.units == "esriFeet" ? "ft" : "m";
            if (!!info.tileInfo) {
                this.tileSize = new OpenLayers.Size(info.tileInfo.width || info.tileInfo.cols, info.tileInfo.height || info.tileInfo.rows);
                this.tileOrigin = new OpenLayers.LonLat(info.tileInfo.origin.x, info.tileInfo.origin.y);
                var upperLeft = new OpenLayers.Geometry.Point(startingTileExtent.left,
                    startingTileExtent.top);
                var bottomRight = new OpenLayers.Geometry.Point(startingTileExtent.right, startingTileExtent.bottom);
                if (this.useScales) this.scales = [];
                else this.resolutions = [];
                this.lods = [];
                for (var key in info.tileInfo.lods)
                    if (info.tileInfo.lods.hasOwnProperty(key)) {
                        var lod = info.tileInfo.lods[key];
                        if (this.useScales) this.scales.push(lod.scale);
                        else this.resolutions.push(lod.resolution);
                        var start = this.getContainingTileCoords(upperLeft, lod.resolution);
                        lod.startTileCol = start.x;
                        lod.startTileRow = start.y;
                        var end = this.getContainingTileCoords(bottomRight, lod.resolution);
                        lod.endTileCol = end.x;
                        lod.endTileRow = end.y;
                        this.lods.push(lod)
                    }
                this.maxExtent = this.calculateMaxExtentWithLOD(this.lods[0]);
                this.serverResolutions = this.resolutions;
                if (this.overrideDPI && info.tileInfo.dpi) OpenLayers.DOTS_PER_INCH = info.tileInfo.dpi
            }
        }
    },
    getContainingTileCoords: function(point, res) {
        return new OpenLayers.Pixel(Math.max(Math.floor((point.x - this.tileOrigin.lon) / (this.tileSize.w * res)), 0), Math.max(Math.floor((this.tileOrigin.lat -
            point.y) / (this.tileSize.h * res)), 0))
    },
    calculateMaxExtentWithLOD: function(lod) {
        var numTileCols = lod.endTileCol - lod.startTileCol + 1;
        var numTileRows = lod.endTileRow - lod.startTileRow + 1;
        var minX = this.tileOrigin.lon + lod.startTileCol * this.tileSize.w * lod.resolution;
        var maxX = minX + numTileCols * this.tileSize.w * lod.resolution;
        var maxY = this.tileOrigin.lat - lod.startTileRow * this.tileSize.h * lod.resolution;
        var minY = maxY - numTileRows * this.tileSize.h * lod.resolution;
        return new OpenLayers.Bounds(minX, minY, maxX, maxY)
    },
    calculateMaxExtentWithExtent: function(extent,
        res) {
        var upperLeft = new OpenLayers.Geometry.Point(extent.left, extent.top);
        var bottomRight = new OpenLayers.Geometry.Point(extent.right, extent.bottom);
        var start = this.getContainingTileCoords(upperLeft, res);
        var end = this.getContainingTileCoords(bottomRight, res);
        var lod = {
            resolution: res,
            startTileCol: start.x,
            startTileRow: start.y,
            endTileCol: end.x,
            endTileRow: end.y
        };
        return this.calculateMaxExtentWithLOD(lod)
    },
    getUpperLeftTileCoord: function(res) {
        var upperLeft = new OpenLayers.Geometry.Point(this.maxExtent.left, this.maxExtent.top);
        return this.getContainingTileCoords(upperLeft, res)
    },
    getLowerRightTileCoord: function(res) {
        var bottomRight = new OpenLayers.Geometry.Point(this.maxExtent.right, this.maxExtent.bottom);
        return this.getContainingTileCoords(bottomRight, res)
    },
    getMaxExtentForResolution: function(res) {
        var start = this.getUpperLeftTileCoord(res);
        var end = this.getLowerRightTileCoord(res);
        var numTileCols = end.x - start.x + 1;
        var numTileRows = end.y - start.y + 1;
        var minX = this.tileOrigin.lon + start.x * this.tileSize.w * res;
        var maxX = minX + numTileCols *
            this.tileSize.w * res;
        var maxY = this.tileOrigin.lat - start.y * this.tileSize.h * res;
        var minY = maxY - numTileRows * this.tileSize.h * res;
        return new OpenLayers.Bounds(minX, minY, maxX, maxY)
    },
    clone: function(obj) {
        if (obj == null) obj = new OpenLayers.Layer.ArcGISCache(this.name, this.url, this.options);
        return OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj])
    },
    initGriddedTiles: function(bounds) {
        delete this._tileOrigin;
        OpenLayers.Layer.XYZ.prototype.initGriddedTiles.apply(this, arguments)
    },
    getMaxExtent: function() {
        var resolution =
            this.map.getResolution();
        return this.maxExtent = this.getMaxExtentForResolution(resolution)
    },
    getTileOrigin: function() {
        if (!this._tileOrigin) {
            var extent = this.getMaxExtent();
            this._tileOrigin = new OpenLayers.LonLat(extent.left, extent.bottom)
        }
        return this._tileOrigin
    },
    getURL: function(bounds) {
        var res = this.getResolution();
        var originTileX = this.tileOrigin.lon + res * this.tileSize.w / 2;
        var originTileY = this.tileOrigin.lat - res * this.tileSize.h / 2;
        var center = bounds.getCenterLonLat();
        var point = {
            x: center.lon,
            y: center.lat
        };
        var x = Math.round(Math.abs((center.lon - originTileX) / (res * this.tileSize.w)));
        var y = Math.round(Math.abs((originTileY - center.lat) / (res * this.tileSize.h)));
        var z = this.map.getZoom();
        if (this.lods) {
            var lod = this.lods[this.map.getZoom()];
            if (x < lod.startTileCol || x > lod.endTileCol || (y < lod.startTileRow || y > lod.endTileRow)) return null
        } else {
            var start = this.getUpperLeftTileCoord(res);
            var end = this.getLowerRightTileCoord(res);
            if (x < start.x || x >= end.x || (y < start.y || y >= end.y)) return null
        }
        var url = this.url;
        var s = "" + x + y + z;
        if (OpenLayers.Util.isArray(url)) url =
            this.selectUrl(s, url);
        if (this.useArcGISServer) url = url + "/tile/${z}/${y}/${x}";
        else {
            x = "C" + this.zeroPad(x, 8, 16);
            y = "R" + this.zeroPad(y, 8, 16);
            z = "L" + this.zeroPad(z, 2, 16);
            url = url + "/${z}/${y}/${x}." + this.type
        }
        url = OpenLayers.String.format(url, {
            "x": x,
            "y": y,
            "z": z
        });
        return OpenLayers.Util.urlAppend(url, OpenLayers.Util.getParameterString(this.params))
    },
    zeroPad: function(num, len, radix) {
        var str = num.toString(radix || 10);
        while (str.length < len) str = "0" + str;
        return str
    },
    CLASS_NAME: "OpenLayers.Layer.ArcGISCache"
});
OpenLayers.Util.getElement = function() {
    var elements = [];
    for (var i = 0, len = arguments.length; i < len; i++) {
        var element = arguments[i];
        if (typeof element == "string") element = document.getElementById(element);
        if (arguments.length == 1) return element;
        elements.push(element)
    }
    return elements
};
OpenLayers.Util.isElement = function(o) {
    return !!(o && o.nodeType === 1)
};
if (typeof window.$ === "undefined") window.$ = OpenLayers.Util.getElement;
OpenLayers.Util.extend = function(destination, source) {
    destination = destination || {};
    if (source) {
        for (var property in source) {
            var value = source[property];
            if (value !== undefined) destination[property] = value
        }
        var sourceIsEvt = typeof window.Event == "function" && source instanceof window.Event;
        if (!sourceIsEvt && source.hasOwnProperty && source.hasOwnProperty("toString")) destination.toString = source.toString
    }
    return destination
};
OpenLayers.Util.removeItem = function(array, item) {
    for (var i = array.length - 1; i >= 0; i--)
        if (array[i] == item) array.splice(i, 1);
    return array
};
OpenLayers.Util.clearArray = function(array) {
    OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated", {
        "newMethod": "array = []"
    }));
    array.length = 0
};
OpenLayers.Util.indexOf = function(array, obj) {
    if (typeof array.indexOf == "function") return array.indexOf(obj);
    else {
        for (var i = 0, len = array.length; i < len; i++)
            if (array[i] == obj) return i;
        return -1
    }
};
OpenLayers.Util.modifyDOMElement = function(element, id, px, sz, position, border, overflow, opacity) {
    if (id) element.id = id;
    if (px) {
        element.style.left = px.x + "px";
        element.style.top = px.y + "px"
    }
    if (sz) {
        element.style.width = sz.w + "px";
        element.style.height = sz.h + "px"
    }
    if (position) element.style.position = position;
    if (border) element.style.border = border;
    if (overflow) element.style.overflow = overflow;
    if (parseFloat(opacity) >= 0 && parseFloat(opacity) < 1) {
        element.style.filter = "alpha(opacity=" + opacity * 100 + ")";
        element.style.opacity = opacity
    } else if (parseFloat(opacity) ==
        1) {
        element.style.filter = "";
        element.style.opacity = ""
    }
};
OpenLayers.Util.createDiv = function(id, px, sz, imgURL, position, border, overflow, opacity) {
    var dom = document.createElement("div");
    if (imgURL) dom.style.backgroundImage = "url(" + imgURL + ")";
    if (!id) id = OpenLayers.Util.createUniqueID("OpenLayersDiv");
    if (!position) position = "absolute";
    OpenLayers.Util.modifyDOMElement(dom, id, px, sz, position, border, overflow, opacity);
    return dom
};
OpenLayers.Util.createImage = function(id, px, sz, imgURL, position, border, opacity, delayDisplay) {
    var image = document.createElement("img");
    if (!id) id = OpenLayers.Util.createUniqueID("OpenLayersDiv");
    if (!position) position = "relative";
    OpenLayers.Util.modifyDOMElement(image, id, px, sz, position, border, null, opacity);
    if (delayDisplay) {
        image.style.display = "none";
        OpenLayers.Event.observe(image, "load", OpenLayers.Function.bind(OpenLayers.Util.onImageLoad, image));
        OpenLayers.Event.observe(image, "error", OpenLayers.Function.bind(OpenLayers.Util.onImageLoadError,
            image))
    }
    image.style.alt = id;
    image.galleryImg = "no";
    if (imgURL) image.src = imgURL;
    return image
};
OpenLayers.Util.setOpacity = function(element, opacity) {
    OpenLayers.Util.modifyDOMElement(element, null, null, null, null, null, null, opacity)
};
OpenLayers.Util.onImageLoad = function() {
    if (!this.viewRequestID || this.map && this.viewRequestID == this.map.viewRequestID) this.style.display = "";
    OpenLayers.Element.removeClass(this, "olImageLoadError")
};
OpenLayers.IMAGE_RELOAD_ATTEMPTS = 0;
OpenLayers.Util.onImageLoadError = function() {
    this._attempts = this._attempts ? this._attempts + 1 : 1;
    if (this._attempts <= OpenLayers.IMAGE_RELOAD_ATTEMPTS) {
        var urls = this.urls;
        if (urls && urls instanceof Array && urls.length > 1) {
            var src = this.src.toString();
            var current_url, k;
            for (k = 0; current_url = urls[k]; k++)
                if (src.indexOf(current_url) != -1) break;
            var guess = Math.floor(urls.length * Math.random());
            var new_url = urls[guess];
            k = 0;
            while (new_url == current_url && k++ < 4) {
                guess = Math.floor(urls.length * Math.random());
                new_url = urls[guess]
            }
            this.src =
                src.replace(current_url, new_url)
        } else this.src = this.src
    } else OpenLayers.Element.addClass(this, "olImageLoadError");
    this.style.display = ""
};
OpenLayers.Util.alphaHackNeeded = null;
OpenLayers.Util.alphaHack = function() {
    if (OpenLayers.Util.alphaHackNeeded == null) {
        var arVersion = navigator.appVersion.split("MSIE");
        var version = parseFloat(arVersion[1]);
        var filter = false;
        try {
            filter = !!document.body.filters
        } catch (e) {}
        OpenLayers.Util.alphaHackNeeded = filter && version >= 5.5 && version < 7
    }
    return OpenLayers.Util.alphaHackNeeded
};
OpenLayers.Util.modifyAlphaImageDiv = function(div, id, px, sz, imgURL, position, border, sizing, opacity) {
    OpenLayers.Util.modifyDOMElement(div, id, px, sz, position, null, null, opacity);
    var img = div.childNodes[0];
    if (imgURL) img.src = imgURL;
    OpenLayers.Util.modifyDOMElement(img, div.id + "_innerImage", null, sz, "relative", border);
    if (OpenLayers.Util.alphaHack()) {
        if (div.style.display != "none") div.style.display = "inline-block";
        if (sizing == null) sizing = "scale";
        div.style.filter = "progid:DXImageTransform.Microsoft" + ".AlphaImageLoader(src='" +
            img.src + "', " + "sizingMethod='" + sizing + "')";
        if (parseFloat(div.style.opacity) >= 0 && parseFloat(div.style.opacity) < 1) div.style.filter += " alpha(opacity=" + div.style.opacity * 100 + ")";
        img.style.filter = "alpha(opacity=0)"
    }
};
OpenLayers.Util.createAlphaImageDiv = function(id, px, sz, imgURL, position, border, sizing, opacity, delayDisplay) {
    var div = OpenLayers.Util.createDiv();
    var img = OpenLayers.Util.createImage(null, null, null, null, null, null, null, false);
    div.appendChild(img);
    if (delayDisplay) {
        img.style.display = "none";
        OpenLayers.Event.observe(img, "load", OpenLayers.Function.bind(OpenLayers.Util.onImageLoad, div));
        OpenLayers.Event.observe(img, "error", OpenLayers.Function.bind(OpenLayers.Util.onImageLoadError, div))
    }
    OpenLayers.Util.modifyAlphaImageDiv(div,
        id, px, sz, imgURL, position, border, sizing, opacity);
    return div
};
OpenLayers.Util.upperCaseObject = function(object) {
    var uObject = {};
    for (var key in object) uObject[key.toUpperCase()] = object[key];
    return uObject
};
OpenLayers.Util.applyDefaults = function(to, from) {
    to = to || {};
    var fromIsEvt = typeof window.Event == "function" && from instanceof window.Event;
    for (var key in from)
        if (to[key] === undefined || !fromIsEvt && from.hasOwnProperty && from.hasOwnProperty(key) && !to.hasOwnProperty(key)) to[key] = from[key];
    if (!fromIsEvt && from && from.hasOwnProperty && from.hasOwnProperty("toString") && !to.hasOwnProperty("toString")) to.toString = from.toString;
    return to
};
OpenLayers.Util.getParameterString = function(params) {
    var paramsArray = [];
    for (var key in params) {
        var value = params[key];
        if (value != null && typeof value != "function") {
            var encodedValue;
            if (typeof value == "object" && value.constructor == Array) {
                var encodedItemArray = [];
                var item;
                for (var itemIndex = 0, len = value.length; itemIndex < len; itemIndex++) {
                    item = value[itemIndex];
                    encodedItemArray.push(encodeURIComponent(item === null || item === undefined ? "" : item))
                }
                encodedValue = encodedItemArray.join(",")
            } else encodedValue = encodeURIComponent(value);
            paramsArray.push(encodeURIComponent(key) + "=" + encodedValue)
        }
    }
    return paramsArray.join("&")
};
OpenLayers.Util.urlAppend = function(url, paramStr) {
    var newUrl = url;
    if (paramStr) {
        var parts = (url + " ").split(/[?&]/);
        newUrl += parts.pop() === " " ? paramStr : parts.length ? "&" + paramStr : "?" + paramStr
    }
    return newUrl
};
OpenLayers.ImgPath = "";
OpenLayers.Util.getImagesLocation = function() {
    return OpenLayers.ImgPath || OpenLayers._getScriptLocation() + "img/"
};
OpenLayers.Util.Try = function() {
    var returnValue = null;
    for (var i = 0, len = arguments.length; i < len; i++) {
        var lambda = arguments[i];
        try {
            returnValue = lambda();
            break
        } catch (e) {}
    }
    return returnValue
};
OpenLayers.Util.getNodes = function(p, tagName) {
    var nodes = OpenLayers.Util.Try(function() {
        return OpenLayers.Util._getNodes(p.documentElement.childNodes, tagName)
    }, function() {
        return OpenLayers.Util._getNodes(p.childNodes, tagName)
    });
    return nodes
};
OpenLayers.Util._getNodes = function(nodes, tagName) {
    var retArray = [];
    for (var i = 0, len = nodes.length; i < len; i++)
        if (nodes[i].nodeName == tagName) retArray.push(nodes[i]);
    return retArray
};
OpenLayers.Util.getTagText = function(parent, item, index) {
    var result = OpenLayers.Util.getNodes(parent, item);
    if (result && result.length > 0) {
        if (!index) index = 0;
        if (result[index].childNodes.length > 1) return result.childNodes[1].nodeValue;
        else if (result[index].childNodes.length == 1) return result[index].firstChild.nodeValue
    } else return ""
};
OpenLayers.Util.getXmlNodeValue = function(node) {
    var val = null;
    OpenLayers.Util.Try(function() {
        val = node.text;
        if (!val) val = node.textContent;
        if (!val) val = node.firstChild.nodeValue
    }, function() {
        val = node.textContent
    });
    return val
};
OpenLayers.Util.mouseLeft = function(evt, div) {
    var target = evt.relatedTarget ? evt.relatedTarget : evt.toElement;
    while (target != div && target != null) target = target.parentNode;
    return target != div
};
OpenLayers.Util.DEFAULT_PRECISION = 14;
OpenLayers.Util.toFloat = function(number, precision) {
    if (precision == null) precision = OpenLayers.Util.DEFAULT_PRECISION;
    var number;
    if (precision == 0) number = parseFloat(number);
    else number = parseFloat(parseFloat(number).toPrecision(precision));
    return number
};
OpenLayers.Util.rad = function(x) {
    return x * Math.PI / 180
};
OpenLayers.Util.deg = function(x) {
    return x * 180 / Math.PI
};
OpenLayers.Util.VincentyConstants = {
    a: 6378137,
    b: 6356752.3142,
    f: 1 / 298.257223563
};
OpenLayers.Util.distVincenty = function(p1, p2) {
    var ct = OpenLayers.Util.VincentyConstants;
    var a = ct.a,
        b = ct.b,
        f = ct.f;
    var L = OpenLayers.Util.rad(p2.lon - p1.lon);
    var U1 = Math.atan((1 - f) * Math.tan(OpenLayers.Util.rad(p1.lat)));
    var U2 = Math.atan((1 - f) * Math.tan(OpenLayers.Util.rad(p2.lat)));
    var sinU1 = Math.sin(U1),
        cosU1 = Math.cos(U1);
    var sinU2 = Math.sin(U2),
        cosU2 = Math.cos(U2);
    var lambda = L,
        lambdaP = 2 * Math.PI;
    var iterLimit = 20;
    while (Math.abs(lambda - lambdaP) > 1E-12 && --iterLimit > 0) {
        var sinLambda = Math.sin(lambda),
            cosLambda =
            Math.cos(lambda);
        var sinSigma = Math.sqrt(cosU2 * sinLambda * (cosU2 * sinLambda) + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
        if (sinSigma == 0) return 0;
        var cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
        var sigma = Math.atan2(sinSigma, cosSigma);
        var alpha = Math.asin(cosU1 * cosU2 * sinLambda / sinSigma);
        var cosSqAlpha = Math.cos(alpha) * Math.cos(alpha);
        var cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
        var C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
        lambdaP = lambda;
        lambda = L + (1 - C) * f * Math.sin(alpha) * (sigma +
            C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)))
    }
    if (iterLimit == 0) return NaN;
    var uSq = cosSqAlpha * (a * a - b * b) / (b * b);
    var A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
    var B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
    var deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
    var s = b * A * (sigma - deltaSigma);
    var d = s.toFixed(3) / 1E3;
    return d
};
OpenLayers.Util.destinationVincenty = function(lonlat, brng, dist) {
    var u = OpenLayers.Util;
    var ct = u.VincentyConstants;
    var a = ct.a,
        b = ct.b,
        f = ct.f;
    var lon1 = lonlat.lon;
    var lat1 = lonlat.lat;
    var s = dist;
    var alpha1 = u.rad(brng);
    var sinAlpha1 = Math.sin(alpha1);
    var cosAlpha1 = Math.cos(alpha1);
    var tanU1 = (1 - f) * Math.tan(u.rad(lat1));
    var cosU1 = 1 / Math.sqrt(1 + tanU1 * tanU1),
        sinU1 = tanU1 * cosU1;
    var sigma1 = Math.atan2(tanU1, cosAlpha1);
    var sinAlpha = cosU1 * sinAlpha1;
    var cosSqAlpha = 1 - sinAlpha * sinAlpha;
    var uSq = cosSqAlpha * (a * a - b * b) / (b *
        b);
    var A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
    var B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
    var sigma = s / (b * A),
        sigmaP = 2 * Math.PI;
    while (Math.abs(sigma - sigmaP) > 1E-12) {
        var cos2SigmaM = Math.cos(2 * sigma1 + sigma);
        var sinSigma = Math.sin(sigma);
        var cosSigma = Math.cos(sigma);
        var deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
        sigmaP = sigma;
        sigma = s / (b * A) + deltaSigma
    }
    var tmp = sinU1 * sinSigma - cosU1 * cosSigma *
        cosAlpha1;
    var lat2 = Math.atan2(sinU1 * cosSigma + cosU1 * sinSigma * cosAlpha1, (1 - f) * Math.sqrt(sinAlpha * sinAlpha + tmp * tmp));
    var lambda = Math.atan2(sinSigma * sinAlpha1, cosU1 * cosSigma - sinU1 * sinSigma * cosAlpha1);
    var C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
    var L = lambda - (1 - C) * f * sinAlpha * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
    var revAz = Math.atan2(sinAlpha, -tmp);
    return new OpenLayers.LonLat(lon1 + u.deg(L), u.deg(lat2))
};
OpenLayers.Util.getParameters = function(url) {
    url = url || window.location.href;
    var paramsString = "";
    if (OpenLayers.String.contains(url, "?")) {
        var start = url.indexOf("?") + 1;
        var end = OpenLayers.String.contains(url, "#") ? url.indexOf("#") : url.length;
        paramsString = url.substring(start, end)
    }
    var parameters = {};
    var pairs = paramsString.split(/[&;]/);
    for (var i = 0, len = pairs.length; i < len; ++i) {
        var keyValue = pairs[i].split("=");
        if (keyValue[0]) {
            var key = decodeURIComponent(keyValue[0]);
            var value = keyValue[1] || "";
            value = decodeURIComponent(value.replace(/\+/g,
                " ")).split(",");
            if (value.length == 1) value = value[0];
            parameters[key] = value
        }
    }
    return parameters
};
OpenLayers.Util.getArgs = function(url) {
    OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated", {
        "newMethod": "OpenLayers.Util.getParameters"
    }));
    return OpenLayers.Util.getParameters(url)
};
OpenLayers.Util.lastSeqID = 0;
OpenLayers.Util.createUniqueID = function(prefix) {
    if (prefix == null) prefix = "id_";
    OpenLayers.Util.lastSeqID += 1;
    return prefix + OpenLayers.Util.lastSeqID
};
OpenLayers.INCHES_PER_UNIT = {
    "inches": 1,
    "ft": 12,
    "mi": 63360,
    "m": 39.3701,
    "km": 39370.1,
    "dd": 4374754,
    "yd": 36
};
OpenLayers.INCHES_PER_UNIT["in"] = OpenLayers.INCHES_PER_UNIT.inches;
OpenLayers.INCHES_PER_UNIT["degrees"] = OpenLayers.INCHES_PER_UNIT.dd;
OpenLayers.INCHES_PER_UNIT["nmi"] = 1852 * OpenLayers.INCHES_PER_UNIT.m;
OpenLayers.METERS_PER_INCH = .0254000508001016;
OpenLayers.Util.extend(OpenLayers.INCHES_PER_UNIT, {
    "Inch": OpenLayers.INCHES_PER_UNIT.inches,
    "Meter": 1 / OpenLayers.METERS_PER_INCH,
    "Foot": .3048006096012192 / OpenLayers.METERS_PER_INCH,
    "IFoot": .3048 / OpenLayers.METERS_PER_INCH,
    "ClarkeFoot": .3047972651151 / OpenLayers.METERS_PER_INCH,
    "SearsFoot": .30479947153867626 / OpenLayers.METERS_PER_INCH,
    "GoldCoastFoot": .3047997101815088 / OpenLayers.METERS_PER_INCH,
    "IInch": .0254 / OpenLayers.METERS_PER_INCH,
    "MicroInch": 2.54E-5 / OpenLayers.METERS_PER_INCH,
    "Mil": 2.54E-8 / OpenLayers.METERS_PER_INCH,
    "Centimeter": .01 / OpenLayers.METERS_PER_INCH,
    "Kilometer": 1E3 / OpenLayers.METERS_PER_INCH,
    "Yard": .9144018288036576 / OpenLayers.METERS_PER_INCH,
    "SearsYard": .914398414616029 / OpenLayers.METERS_PER_INCH,
    "IndianYard": .9143985307444408 / OpenLayers.METERS_PER_INCH,
    "IndianYd37": .91439523 / OpenLayers.METERS_PER_INCH,
    "IndianYd62": .9143988 / OpenLayers.METERS_PER_INCH,
    "IndianYd75": .9143985 / OpenLayers.METERS_PER_INCH,
    "IndianFoot": .30479951 / OpenLayers.METERS_PER_INCH,
    "IndianFt37": .30479841 / OpenLayers.METERS_PER_INCH,
    "IndianFt62": .3047996 / OpenLayers.METERS_PER_INCH,
    "IndianFt75": .3047995 / OpenLayers.METERS_PER_INCH,
    "Mile": 1609.3472186944373 / OpenLayers.METERS_PER_INCH,
    "IYard": .9144 / OpenLayers.METERS_PER_INCH,
    "IMile": 1609.344 / OpenLayers.METERS_PER_INCH,
    "NautM": 1852 / OpenLayers.METERS_PER_INCH,
    "Lat-66": 110943.31648893273 / OpenLayers.METERS_PER_INCH,
    "Lat-83": 110946.25736872235 / OpenLayers.METERS_PER_INCH,
    "Decimeter": .1 / OpenLayers.METERS_PER_INCH,
    "Millimeter": .001 / OpenLayers.METERS_PER_INCH,
    "Dekameter": 10 / OpenLayers.METERS_PER_INCH,
    "Decameter": 10 / OpenLayers.METERS_PER_INCH,
    "Hectometer": 100 / OpenLayers.METERS_PER_INCH,
    "GermanMeter": 1.0000135965 / OpenLayers.METERS_PER_INCH,
    "CaGrid": .999738 / OpenLayers.METERS_PER_INCH,
    "ClarkeChain": 20.1166194976 / OpenLayers.METERS_PER_INCH,
    "GunterChain": 20.11684023368047 / OpenLayers.METERS_PER_INCH,
    "BenoitChain": 20.116782494375872 / OpenLayers.METERS_PER_INCH,
    "SearsChain": 20.11676512155 / OpenLayers.METERS_PER_INCH,
    "ClarkeLink": .201166194976 / OpenLayers.METERS_PER_INCH,
    "GunterLink": .2011684023368047 / OpenLayers.METERS_PER_INCH,
    "BenoitLink": .20116782494375873 / OpenLayers.METERS_PER_INCH,
    "SearsLink": .2011676512155 / OpenLayers.METERS_PER_INCH,
    "Rod": 5.02921005842012 / OpenLayers.METERS_PER_INCH,
    "IntnlChain": 20.1168 / OpenLayers.METERS_PER_INCH,
    "IntnlLink": .201168 / OpenLayers.METERS_PER_INCH,
    "Perch": 5.02921005842012 / OpenLayers.METERS_PER_INCH,
    "Pole": 5.02921005842012 / OpenLayers.METERS_PER_INCH,
    "Furlong": 201.1684023368046 / OpenLayers.METERS_PER_INCH,
    "Rood": 3.778266898 / OpenLayers.METERS_PER_INCH,
    "CapeFoot": .3047972615 / OpenLayers.METERS_PER_INCH,
    "Brealey": 375 / OpenLayers.METERS_PER_INCH,
    "ModAmFt": .304812252984506 / OpenLayers.METERS_PER_INCH,
    "Fathom": 1.8288 / OpenLayers.METERS_PER_INCH,
    "NautM-UK": 1853.184 / OpenLayers.METERS_PER_INCH,
    "50kilometers": 5E4 / OpenLayers.METERS_PER_INCH,
    "150kilometers": 15E4 / OpenLayers.METERS_PER_INCH
});
OpenLayers.Util.extend(OpenLayers.INCHES_PER_UNIT, {
    "mm": OpenLayers.INCHES_PER_UNIT["Meter"] / 1E3,
    "cm": OpenLayers.INCHES_PER_UNIT["Meter"] / 100,
    "dm": OpenLayers.INCHES_PER_UNIT["Meter"] * 100,
    "km": OpenLayers.INCHES_PER_UNIT["Meter"] * 1E3,
    "kmi": OpenLayers.INCHES_PER_UNIT["nmi"],
    "fath": OpenLayers.INCHES_PER_UNIT["Fathom"],
    "ch": OpenLayers.INCHES_PER_UNIT["IntnlChain"],
    "link": OpenLayers.INCHES_PER_UNIT["IntnlLink"],
    "us-in": OpenLayers.INCHES_PER_UNIT["inches"],
    "us-ft": OpenLayers.INCHES_PER_UNIT["Foot"],
    "us-yd": OpenLayers.INCHES_PER_UNIT["Yard"],
    "us-ch": OpenLayers.INCHES_PER_UNIT["GunterChain"],
    "us-mi": OpenLayers.INCHES_PER_UNIT["Mile"],
    "ind-yd": OpenLayers.INCHES_PER_UNIT["IndianYd37"],
    "ind-ft": OpenLayers.INCHES_PER_UNIT["IndianFt37"],
    "ind-ch": 20.11669506 / OpenLayers.METERS_PER_INCH
});
OpenLayers.DOTS_PER_INCH = 96;
OpenLayers.Util.normalizeScale = function(scale) {
    var normScale = scale > 1 ? 1 / scale : scale;
    return normScale
};
OpenLayers.Util.getResolutionFromScale = function(scale, units) {
    var resolution;
    if (scale) {
        if (units == null) units = "degrees";
        var normScale = OpenLayers.Util.normalizeScale(scale);
        resolution = 1 / (normScale * OpenLayers.INCHES_PER_UNIT[units] * OpenLayers.DOTS_PER_INCH)
    }
    return resolution
};
OpenLayers.Util.getScaleFromResolution = function(resolution, units) {
    if (units == null) units = "degrees";
    var scale = resolution * OpenLayers.INCHES_PER_UNIT[units] * OpenLayers.DOTS_PER_INCH;
    return scale
};
OpenLayers.Util.safeStopPropagation = function(evt) {
    OpenLayers.Event.stop(evt, true)
};
OpenLayers.Util.pagePosition = function(forElement) {
    var valueT = 0,
        valueL = 0;
    var element = forElement;
    var child = forElement;
    while (element) {
        if (element == document.body)
            if (OpenLayers.Element.getStyle(child, "position") == "absolute") break;
        valueT += element.offsetTop || 0;
        valueL += element.offsetLeft || 0;
        child = element;
        try {
            element = element.offsetParent
        } catch (e) {
            OpenLayers.Console.error(OpenLayers.i18n("pagePositionFailed", {
                "elemId": element.id
            }));
            break
        }
    }
    element = forElement;
    while (element) {
        valueT -= element.scrollTop || 0;
        valueL -=
            element.scrollLeft || 0;
        element = element.parentNode
    }
    return [valueL, valueT]
};
OpenLayers.Util.isEquivalentUrl = function(url1, url2, options) {
    options = options || {};
    OpenLayers.Util.applyDefaults(options, {
        ignoreCase: true,
        ignorePort80: true,
        ignoreHash: true
    });
    var urlObj1 = OpenLayers.Util.createUrlObject(url1, options);
    var urlObj2 = OpenLayers.Util.createUrlObject(url2, options);
    for (var key in urlObj1)
        if (key !== "args")
            if (urlObj1[key] != urlObj2[key]) return false;
    for (var key in urlObj1.args) {
        if (urlObj1.args[key] != urlObj2.args[key]) return false;
        delete urlObj2.args[key]
    }
    for (var key in urlObj2.args) return false;
    return true
};
OpenLayers.Util.createUrlObject = function(url, options) {
    options = options || {};
    if (!/^\w+:\/\//.test(url)) {
        var loc = window.location;
        var port = loc.port ? ":" + loc.port : "";
        var fullUrl = loc.protocol + "//" + loc.host.split(":").shift() + port;
        if (url.indexOf("/") === 0) url = fullUrl + url;
        else {
            var parts = loc.pathname.split("/");
            parts.pop();
            url = fullUrl + parts.join("/") + "/" + url
        }
    }
    if (options.ignoreCase) url = url.toLowerCase();
    var a = document.createElement("a");
    a.href = url;
    var urlObject = {};
    urlObject.host = a.host.split(":").shift();
    urlObject.protocol =
        a.protocol;
    if (options.ignorePort80) urlObject.port = a.port == "80" || a.port == "0" ? "" : a.port;
    else urlObject.port = a.port == "" || a.port == "0" ? "80" : a.port;
    urlObject.hash = options.ignoreHash || a.hash === "#" ? "" : a.hash;
    var queryString = a.search;
    if (!queryString) {
        var qMark = url.indexOf("?");
        queryString = qMark != -1 ? url.substr(qMark) : ""
    }
    urlObject.args = OpenLayers.Util.getParameters(queryString);
    urlObject.pathname = a.pathname.charAt(0) == "/" ? a.pathname : "/" + a.pathname;
    return urlObject
};
OpenLayers.Util.removeTail = function(url) {
    var head = null;
    var qMark = url.indexOf("?");
    var hashMark = url.indexOf("#");
    if (qMark == -1) head = hashMark != -1 ? url.substr(0, hashMark) : url;
    else head = hashMark != -1 ? url.substr(0, Math.min(qMark, hashMark)) : url.substr(0, qMark);
    return head
};
OpenLayers.Util.getBrowserName = function() {
    var browserName = "";
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("opera") != -1) browserName = "opera";
    else if (ua.indexOf("msie") != -1) browserName = "msie";
    else if (ua.indexOf("safari") != -1) browserName = "safari";
    else if (ua.indexOf("mozilla") != -1)
        if (ua.indexOf("firefox") != -1) browserName = "firefox";
        else browserName = "mozilla";
    return browserName
};
OpenLayers.Util.getFormattedLonLat = function(coordinate, axis, dmsOption) {
    if (!dmsOption) dmsOption = "dms";
    var abscoordinate = Math.abs(coordinate);
    var coordinatedegrees = Math.floor(abscoordinate);
    var coordinateminutes = (abscoordinate - coordinatedegrees) / (1 / 60);
    var tempcoordinateminutes = coordinateminutes;
    coordinateminutes = Math.floor(coordinateminutes);
    var coordinateseconds = (tempcoordinateminutes - coordinateminutes) / (1 / 60);
    coordinateseconds = Math.round(coordinateseconds * 10);
    coordinateseconds /= 10;
    if (coordinatedegrees <
        10) coordinatedegrees = "0" + coordinatedegrees;
    var str = coordinatedegrees + "\u00b0";
    if (dmsOption.indexOf("dm") >= 0) {
        if (coordinateminutes < 10) coordinateminutes = "0" + coordinateminutes;
        str += coordinateminutes + "'";
        if (dmsOption.indexOf("dms") >= 0) {
            if (coordinateseconds < 10) coordinateseconds = "0" + coordinateseconds;
            str += coordinateseconds + '"'
        }
    }
    if (axis == "lon") str += coordinate < 0 ? OpenLayers.i18n("W") : OpenLayers.i18n("E");
    else str += coordinate < 0 ? OpenLayers.i18n("S") : OpenLayers.i18n("N");
    return str
};
OpenLayers.Util.isArray = function(a) {
    return Object.prototype.toString.call(a) === "[object Array]"
};
GDrag = OpenLayers.Class(OpenLayers.Control.Navigation, {
    CLASS_NAME: "GDrag"
});
GZoomIn = OpenLayers.Class(OpenLayers.Control.ZoomBox, {
    draw: function() {
        this.handler = new GBox(this, {
            done: this.zoomBox
        }, {
            keyMask: this.keyMask
        })
    },
    CLASS_NAME: "GZoomIn"
});
GZoomOut = OpenLayers.Class(OpenLayers.Control.ZoomBox, {
    out: true,
    draw: function() {
        this.handler = new GBox(this, {
            done: this.zoomBox
        }, {
            keyMask: this.keyMask
        })
    },
    CLASS_NAME: "GZoomOut"
});
GMeasure = OpenLayers.Class(OpenLayers.Control.Measure, {
    EVENT_TYPES: ["measure"],
    persist: true,
    handlerOptions: {
        multiLine: true,
        persistControl: true,
        layerOptions: {
            styleMap: new OpenLayers.StyleMap({
                "default": new OpenLayers.Style(null, {
                    rules: [new OpenLayers.Rule({
                        symbolizer: {
                            "Point": {
                                pointRadius: 4,
                                graphicName: "square",
                                fillColor: "#ffffff",
                                fillOpacity: 1,
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#ff8000"
                            },
                            "Line": {
                                strokeWidth: 3,
                                strokeOpacity: .7,
                                strokeColor: "#ff8000"
                            },
                            "Polygon": {
                                strokeWidth: 3,
                                strokeOpacity: .7,
                                strokeColor: "#ff8000",
                                fillColor: "#ee9900",
                                fillOpacity: 0
                            }
                        }
                    })]
                })
            })
        }
    },
    initialize: function(handler, options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.callbacks = OpenLayers.Util.extend({
            done: this.measureComplete
        }, this.callbacks);
        this.handlerOptions = OpenLayers.Util.extend({
            persist: this.persist
        }, this.handlerOptions);
        this.handler = new handler(this, this.callbacks, this.handlerOptions)
    },
    measureComplete: function(geometry) {
        this.events.triggerEvent("measure")
    },
    setStyle: function(obj) {
        for (var i in obj)
            for (var j in obj[i]) this.handlerOptions["layerOptions"]["styleMap"]["styles"]["default"]["rules"][0]["symbolizer"][i][j] =
                obj[i][j];
        this.handler.handlerOptions = this.handlerOptions
    },   
    CLASS_NAME: "GMeasure"
});
GNavigationHistory = OpenLayers.Class(OpenLayers.Control.NavigationHistory, {
    CLASS_NAME: "OpenLayers.Control.NavigationHistory"
});
GZoomBoxIndex = OpenLayers.Class(OpenLayers.Control.ZoomBox, {
    baseMap: null,
    draw: function() {
        this.handler = new GBox(this, {
            done: this.zoomBox
        }, {
            indexMap: true
        })
    },
    initialize: function(baseMap, options) {
        this.baseMap = baseMap;
        OpenLayers.Control.prototype.initialize.apply(this, [options])
    },
    zoomBox: function(position) {
        if (position instanceof OpenLayers.Bounds) {
            if (!this.out) {
                var minXY = this.map.getLonLatFromPixel(new OpenLayers.Pixel(position.left, position.bottom));
                var maxXY = this.map.getLonLatFromPixel(new OpenLayers.Pixel(position.right,
                    position.top));
                var bounds = new OpenLayers.Bounds(minXY.lon, minXY.lat, maxXY.lon, maxXY.lat)
            } else {
                var pixWidth = Math.abs(position.right - position.left);
                var pixHeight = Math.abs(position.top - position.bottom);
                var zoomFactor = Math.min(this.map.size.h / pixHeight, this.map.size.w / pixWidth);
                var extent = this.map.getExtent();
                var center = this.map.getLonLatFromPixel(position.getCenterPixel());
                var xmin = center.lon - extent.getWidth() / 2 * zoomFactor;
                var xmax = center.lon + extent.getWidth() / 2 * zoomFactor;
                var ymin = center.lat - extent.getHeight() /
                    2 * zoomFactor;
                var ymax = center.lat + extent.getHeight() / 2 * zoomFactor;
                var bounds = new OpenLayers.Bounds(xmin, ymin, xmax, ymax)
            }
            this.baseMap.zoomToExtent(bounds, true)
        } else this.baseMap.setCenter(this.map.getLonLatFromPixel(position), this.baseMap.numZoomLevels - 1)
    },
    CLASS_NAME: "GZoomBoxIndex"
});
GDrawFeature = OpenLayers.Class(OpenLayers.Control.DrawFeature, {
    inputTextPopup: null,
    seq: 0,
    drawFeature: function(geometry, attributes) {
        if (attributes && attributes.featureType && attributes.featureType == "Text") 
        	this.removeInputTextPopup();
        attributes.seq = this.seq;
        this.seq++;
        var feature = new OpenLayers.Feature.Vector(geometry, attributes);
        var proceed = this.layer.events.triggerEvent("sketchcomplete", {
            feature: feature
        });
        if (proceed !== false) {
        	console.log("GDrawFeature에서 feature그리기");
            feature.state = OpenLayers.State.INSERT;
            this.layer.addFeatures([feature]);
            this.featureAdded(feature);
            this.events.triggerEvent("featureadded", {
                feature: feature
            })
        }
        if (attributes && attributes.featureType && attributes.featureType == "Text") 
        	this.addInputTextPopup(feature)
    },
    addInputTextPopup: function(feature) {
    	console.log("GDrawFeature에서 input텍스트팝업 띄우기");
        var contentHtml = "";
        contentHtml += "<div class='olControlDrawInputText'>";
        contentHtml += "<textarea class='olControlDrawInputTextArea'></textarea>";
        contentHtml += "<img class='olControlDrawInputTextConfirm' src='/images/usolver/com/map/btn_submit.gif' alt='\ud655\uc778' title='\ud655\uc778' />";
        contentHtml += "<img class='olControlDrawInputTextCancel' src='/images/usolver/com/map/btn_close.gif' alt='\ub2eb\uae30' title='\ub2eb\uae30' />";
        contentHtml += "</div>";
        var lonlat = new GLonLat(feature.geometry.x, feature.geometry.y);
        this.inputTextPopup = new GPopup("drawInputText", lonlat, new GSize(500, 200), contentHtml, new OpenLayers.Pixel(0, 0));
        this.map.addPopup(this.inputTextPopup);
        this.inputTextPopup.updateSize();
        this.inputTextPopup.type = "draw";
        $(".olControlDrawInputTextArea").focus();
        $(".olControlDrawInputTextConfirm").click(this, function() {
            arguments[0].data.addTextPopup()
        });
        $(".olControlDrawInputTextCancel").click(this, function() {
            arguments[0].data.removeInputTextPopup()
        })
    },
    removeInputTextPopup: function() {
    	console.log("GDrawFeature에서 input텍스트팝업 제거");
        if (this.inputTextPopup) {
            this.map.removePopup(this.inputTextPopup);
            this.inputTextPopup = null
        }
        var len = this.layer.features.length;
        for (var i = len - 1; i >= 0; i--)
            if (this.layer.features[i].attributes.featureType == "Text") 
            	this.layer.removeFeatures(this.layer.features[i])
    },
    addTextPopup: function() {
    	console.log("GDrawFeature에서 텍스트팝업 띄우기");
        var str = $(".olControlDrawInputTextArea").val();
        if (GUtil.fn_trim(str) == "") return;
        str = str.replace(/\x20/gi, "&nbsp;");
        str = str.replace(/\x0D\x0A/gi, "<br/>");
        str = str.replace(/\x0D/gi, "<br/>");
        str = str.replace(/\n/gi, "<br/>");
        var contentHtml = "";
        contentHtml += "<div class='olControlDrawText off' id='drawText" + this.seq + "'>" + str + "</div>";
        var lonlat = this.inputTextPopup.getLonLat();
        var popup = new GPopup("drawPopup" + this.seq, lonlat, null, contentHtml, new OpenLayers.Pixel(0, 0));
        this.map.addPopup(popup);
        popup.updateSize();
        popup.type = "draw";
        popup.attributes = {
            "featureType": "Text",
            "fontFamily": $("#drawText" + this.seq).css("font-family"),
            "fontSize": $("#drawText" + this.seq).css("font-size").replace("px", ""),
            "fontColor": $("#drawText" + this.seq).css("color", "black"),
//yj. 추가함            
            "background_fill" : "rgba(255,255,255,1)",
            "background_line" : "rgba(255,255,255,1)",
            "seq": this.seq,
            "text": $(".olControlDrawInputTextArea").val(),
            "print": true
        };
        this.seq++;
        $(".olControlDrawText").unbind();
        $(".olControlDrawText").click(this.map, function() {
            var map = arguments[0].data;
            console.log("클릭한 textPopup id: " +this.id);
            if (map.getControl("drawSelect") && map.getControl("drawSelect").active) 
            	map.getControl("drawSelect").selectTextPopup(this);
            else if (map.getControl("drawEdit") && map.getControl("drawEdit").active){
            	console.log("텍스트팝업이 선택되었넹");
            	console.log(this);
//            	console.log(map.getControl("drawEdit"));
            	MAP.fn_get_drawTool().selectTextPopup(this);
            	
            }
        });
        this.removeInputTextPopup()
    },
    removeTextPopup: function() {
    	console.log("GDrawFeature에서 텍스트팝업 삭제");
        var id;
        $(".olControlDrawText").each(function() {
            $(this).hasClass("on");
            id = $(this).attr("id");
            return
        });
        for (var i in this.map.popups)
            if (this.map.popups[i].id == id) this.map.removePopup(this.map.popups[i])
    },
    CLASS_NAME: "GDrawFeature"
});
GPanZoomBar = OpenLayers.Class(OpenLayers.Control.PanZoomBar, {
    imgUrl: "/images/GMap/PanZoomBar/",
    imgZoomIn: "scale_plus.png",
    imgZoomOut: "scale_minus.png",
    imgZoomBasic: "scale_basic.png",
    imgZoomBarOn: "scale_on.png",
    imgZoomBar: "scale_bar.png",
    size: new GSize(25, 20),
    zoombarSize: new GSize(27, 7),
    flow: "right",
    offsetPixel: new GPixel(50, 0),
    draw: function(px) {
        OpenLayers.Control.prototype.draw.apply(this, arguments);
        this.moveToZoomBar();
        px = this.position.clone();
        this.buttons = [];
        var sz = this.size;
        var centered = new OpenLayers.Pixel(px.x +
            sz.w / 2, px.y);
        this._addButton("zoomin", this.imgUrl + this.imgZoomIn, centered.add(0, sz.h * 3 + 5), sz);
        centered = this._addZoomBar(centered.add(0, sz.h * 4 + 5));
        this._addButton("zoomout", this.imgUrl + this.imgZoomOut, centered, sz);
        return this.div
    },
    moveToZoomBar: function() {
        if (this.flow == "right") this.offsetPixel.x = $("#map").width() - this.offsetPixel.x;
        this.moveTo(this.offsetPixel)
    },
    _addZoomBar: function(centered) {
        var imgLocation = this.imgUrl;
        var id = this.id + "_" + this.map.id;
        var zoomsToEnd = this.map.getNumZoomLevels() - 1 - this.map.getZoom();
        var slider = OpenLayers.Util.createAlphaImageDiv(id, centered.add(-1, zoomsToEnd * this.zoomStopHeight), new OpenLayers.Size(27, 7), imgLocation + this.imgZoomBar, "absolute");
        this.slider = slider;
        this.sliderEvents = new OpenLayers.Events(this, slider, null, true, {
            includeXY: true
        });
        this.sliderEvents.on({
            "mousedown": this.zoomBarDown,
            "mousemove": this.zoomBarDrag,
            "mouseup": this.zoomBarUp,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });
        var sz = new OpenLayers.Size;
        sz.h = this.zoomStopHeight * this.map.getNumZoomLevels();
        sz.w = this.zoomStopWidth;
        var div = null;
        if (OpenLayers.Util.alphaHack()) {
            var id = this.id + "_" + this.map.id;
            div = OpenLayers.Util.createAlphaImageDiv(id, centered, new OpenLayers.Size(sz.w, this.zoomStopHeight), imgLocation + this.imgZoomBarOn, "absolute", null, "crop");
            div.style.height = sz.h + "px"
        } else div = OpenLayers.Util.createDiv("OpenLayers_Control_PanZoomBar_Zoombar" + this.map.id, centered, sz, imgLocation + this.imgZoomBarOn);
        this.zoombarDiv = div;
        this.divEvents = new OpenLayers.Events(this, div, null, true, {
            includeXY: true
        });
        this.divEvents.on({
            "mousedown": this.divClick,
            "mousemove": this.passEventToSlider,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });
        this.div.appendChild(div);
        this.zoomBasicDiv = OpenLayers.Util.createDiv("OpenLayers_Control_PanZoomBar_ZoombarBasic" + this.map.id, centered, new OpenLayers.Size(sz.w, this.zoomStopHeight * (this.map.getNumZoomLevels() - this.map.getZoom() - 1)), imgLocation + this.imgZoomBasic);
        this.divEvents = new OpenLayers.Events(this, this.zoomBasicDiv, null, true, {
            includeXY: true
        });
        this.divEvents.on({
            "mousedown": this.divClick,
            "mousemove": this.passEventToSlider,
            "dblclick": this.doubleClick,
            "click": this.doubleClick
        });
        this.div.appendChild(this.zoomBasicDiv);
        this.startTop = parseInt(div.style.top);
        this.div.appendChild(slider);
        this.map.events.register("zoomend", this, this.moveZoomBar);
        centered = centered.add(0, this.zoomStopHeight * this.map.getNumZoomLevels());
        return centered
    },
    zoomBarUp: function(evt) {
        if (!OpenLayers.Event.isLeftClick(evt)) return;
        if (this.mouseDragStart) {
            this.div.style.cursor = "";
            this.map.events.un({
                "mouseup": this.passEventToSlider,
                "mousemove": this.passEventToSlider,
                scope: this
            });
            var deltaY = this.zoomStart.y - evt.xy.y;
            var zoomLevel = this.map.zoom;
            if (!this.forceFixedZoomLevel && this.map.fractionalZoom) {
                zoomLevel += deltaY / this.zoomStopHeight;
                zoomLevel = Math.min(Math.max(zoomLevel, 0), this.map.getNumZoomLevels() - 1)
            } else zoomLevel += Math.round(deltaY / this.zoomStopHeight);
            if (zoomLevel > this.map.getNumZoomLevels() - 1) zoomLevel = this.map.getNumZoomLevels() - 1;
            else if (zoomLevel < 0) zoomLevel = 0;
            this.map.zoomTo(zoomLevel);
            this.mouseDragStart = null;
            this.zoomStart =
                null;
            OpenLayers.Event.stop(evt)
        }
    },
    moveZoomBar: function() {
        var newTop = (this.map.getNumZoomLevels() - 1 - this.map.getZoom()) * this.zoomStopHeight + this.startTop + 1;
        this.slider.style.top = newTop + "px";
        this.zoomBasicDiv.style.height = this.zoomStopHeight * (this.map.getNumZoomLevels() - this.map.getZoom() - 1) + "px"
    },
    CLASS_NAME: "GPanZoomBar"
});
OpenLayers.Control.PanZoom.X = 4;
OpenLayers.Control.PanZoom.Y = 4;
GSelectFeature = OpenLayers.Class(OpenLayers.Control.SelectFeature, {
    initialize: function(layers, options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        if (this.scope === null) this.scope = this;
        this.initLayer(layers);
        var callbacks = {
            click: this.clickFeature,
            clickout: this.clickoutFeature
        };
        if (this.hover) {
            callbacks.over = this.overFeature;
            callbacks.out = this.outFeature
        }
        this.callbacks = OpenLayers.Util.extend(callbacks, this.callbacks);
        this.handlers = {
            feature: new OpenLayers.Handler.Feature(this, this.layer,
                this.callbacks, {
                    geometryTypes: this.geometryTypes
                })
        };
        if (this.box) this.handlers.box = new GBox(this, {
            done: this.selectBox
        }, {
            boxDivClassName: "olHandlerBoxSelectFeature"
        })
    },
    clickFeature: function(feature) {
        if (!this.hover) {
            var selected = OpenLayers.Util.indexOf(feature.layer.selectedFeatures, feature) > -1;
            if (selected)
                if (this.toggleSelect()) this.unselect(feature);
                else {
                    if (!this.multipleSelect()) this.unselectAll({
                        except: feature
                    })
                } else {
                if (!this.multipleSelect()) this.unselectAll({
                    except: feature
                });
                this.select(feature)
            }
        } else if (this.onHoverClick) this.onHoverClick.call(this.scope,
            feature)
    },
    onUnselectAll: function() {},
    unselectAll: function(options) {
        var layers = this.layers || [this.layer];
        var layer, feature;
        for (var l = 0; l < layers.length; ++l) {
            layer = layers[l];
            for (var i = layer.selectedFeatures.length - 1; i >= 0; --i) {
                feature = layer.selectedFeatures[i];
                if (!options || options.except != feature) this.unselect(feature)
            }
        }
        $(".olControlDrawText").each(function() {
            $(this).removeClass("on");
            $(this).addClass("off")
        });
        this.onUnselectAll()
    },
    selectTextPopup: function(element) {},
    CLASS_NAME: "GSelectFeature"
});
GModifyFeature = OpenLayers.Class(OpenLayers.Control.ModifyFeature, {
    onDeactivate: function() {},
    virtualStyle: {
        pointRadius: 4,
        graphicName: "square",
        fillColor: "white",
        fillOpacity: .5,
        strokeWidth: 1,
        strokeOpacity: .8,
        strokeColor: "#333333"
    },
    vertexStyle: {
        pointRadius: 4,
        graphicName: "square",
        fillColor: "white",
        fillOpacity: 1,
        strokeWidth: 1,
        strokeOpacity: 1,
        strokeColor: "#333333"
    },
    dragStyle: {
        pointRadius: 4,
        graphicName: "square",
        fillColor: "white",
        fillOpacity: 1,
        strokeWidth: 1,
        strokeOpacity: 1,
        strokeColor: "#333333"
    },
    resizeStyle: {
        pointRadius: 4,
        graphicName: "square",
        fillColor: "white",
        fillOpacity: 1,
        strokeWidth: 1,
        strokeOpacity: 1,
        strokeColor: "#333333"
    },
    rotateStyle: {
        pointRadius: 4,
        graphicName: "square",
        fillColor: "white",
        fillOpacity: 1,
        strokeWidth: 1,
        strokeOpacity: 1,
        strokeColor: "#333333"
    },
    initialize: function(layer, options) {
        options = options || {};
        this.layer = layer;
        this.vertices = [];
        this.virtualVertices = [];
        this.virtualStyle = OpenLayers.Util.extend({}, this.layer.style || this.layer.styleMap.createSymbolizer(null, options.vertexRenderIntent));
        this.virtualStyle.fillOpacity =
            .3;
        this.virtualStyle.strokeOpacity = .3;
        this.deleteCodes = [46, 68];
        this.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        if (!OpenLayers.Util.isArray(this.deleteCodes)) this.deleteCodes = [this.deleteCodes];
        var selectOptions = {
            geometryTypes: this.geometryTypes,
            clickout: this.clickout,
            toggle: this.toggle,
            onBeforeSelect: this.beforeSelectFeature,
            onSelect: this.selectFeature,
            onUnselect: this.unselectFeature,
            scope: this
        };
        if (this.standalone === false) {
        	this.selectControl = new GSelectFeature(layer, selectOptions);
        	tempSelectControl = new GSelectFeature(layer, selectOptions);
        }
        var dragCallbacks = {
            down: function(pixel) {
                this.vertex = null;
                var feature = this.layer.getFeatureFromEvent(this.handlers.drag.evt);
                if (feature) this.dragStart(feature);
                else if (this.clickout) this._unselect = this.feature
            },
            move: function(pixel) {
                delete this._unselect;
                if (this.vertex) this.dragVertex(this.vertex, pixel)
            },
            up: function() {
                this.handlers.drag.stopDown = false;
                if (this._unselect) {
                    this.unselectFeature(this._unselect);
                    delete this._unselect
                }
            },
            done: function(pixel) {
                if (this.vertex) this.dragComplete(this.vertex)
            }
        };
        var dragOptions = {
            documentDrag: this.documentDrag,
            stopDown: false
        };
        var dragOptions = {
            geometryTypes: ["OpenLayers.Geometry.Point"],
            snappingOptions: this.snappingOptions,
            onStart: function(feature, pixel) {
                control.dragStart.apply(control, [feature, pixel])
            },
            onDrag: function(feature, pixel) {
                control.dragVertex.apply(control, [feature, pixel])
            },
            onComplete: function(feature) {
                control.dragComplete.apply(control, [feature])
            },
            featureCallbacks: {
                over: function(feature) {
                    if (control.standalone !== true || feature._sketch || control.feature ===
                        feature) control.dragControl.overFeature.apply(control.dragControl, [feature])
                }
            }
        };
        this.dragControl = new OpenLayers.Control.DragFeature(layer, dragOptions);
        var keyboardOptions = {
            keydown: this.handleKeypress
        };
        this.handlers = {
            keyboard: new OpenLayers.Handler.Keyboard(this, keyboardOptions),
            drag: new OpenLayers.Handler.Drag(this, dragCallbacks, dragOptions)
        }
    },
    selectFeature: function(feature) {
        this.feature = feature;
        this.modified = false;
        this.resetVertices();
        this.onModificationStart(this.feature);
        this.unSelectTextPopup()
    },
    selectTextPopup: function(element) {
        var active = $(element).hasClass("on");
        this.selectControl.unselectAll();
        if (!active) {
            $(element).removeClass("off");
            $(element).addClass("on");
            var popup;
            for (var i in this.map.popups)
                if (this.map.popups[i].attributes.seq == $(element).attr("id").replace("drawText", "")) 
                	popup = this.map.popups[i];
            popup.attributes.fontFamily = $(element).css("font-family");
            popup.attributes.fontSize = $(element).css("font-size");
            popup.attributes.fontColor = $(element).css("color");
            this.onModificationStart(popup)
        }
    },
    unSelectTextPopup: function() {
        $(".olControlDrawText").each(function() {
            $(this).removeClass("on");
            $(this).addClass("off")
        })
    },
    collectVertices: function() {
        this.vertices = [];
        this.virtualVertices = [];
        var control = this;

        function collectComponentVertices(geometry) {
            var i, vertex, component, len;
            if (geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
                vertex = new OpenLayers.Feature.Vector(geometry);
                vertex._sketch = true;
                control.vertices.push(vertex)
            } else {
                var numVert = geometry.components.length;
                if (geometry.CLASS_NAME == "OpenLayers.Geometry.LinearRing") numVert -=
                    1;
                for (i = 0; i < numVert; ++i) {
                    component = geometry.components[i];
                    if (component.CLASS_NAME == "OpenLayers.Geometry.Point") {
                        vertex = new OpenLayers.Feature.Vector(component, null, control.vertexStyle);
                        vertex._sketch = true;
                        control.vertices.push(vertex)
                    } else collectComponentVertices(component)
                }
                if (geometry.CLASS_NAME != "OpenLayers.Geometry.MultiPoint")
                    for (i = 0, len = geometry.components.length; i < len - 1; ++i) {
                        var prevVertex = geometry.components[i];
                        var nextVertex = geometry.components[i + 1];
                        if (prevVertex.CLASS_NAME == "OpenLayers.Geometry.Point" &&
                            nextVertex.CLASS_NAME == "OpenLayers.Geometry.Point") {
                            var x = (prevVertex.x + nextVertex.x) / 2;
                            var y = (prevVertex.y + nextVertex.y) / 2;
                            var point = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(x, y), null, control.virtualStyle);
                            point.geometry.parent = geometry;
                            point._index = i + 1;
                            point._sketch = true;
                            control.virtualVertices.push(point)
                        }
                    }
            }
        }
        collectComponentVertices.call(this, this.feature.geometry);
        this.layer.addFeatures(this.virtualVertices, {
            silent: true
        });
        this.layer.addFeatures(this.vertices, {
            silent: true
        })
    },
    collectDragHandle: function() {
        var geometry = this.feature.geometry;
        var center = geometry.getBounds().getCenterLonLat();
        var originGeometry = new OpenLayers.Geometry.Point(center.lon, center.lat);
        var origin = new OpenLayers.Feature.Vector(originGeometry, null, this.dragStyle);
        originGeometry.move = function(x, y) {
            OpenLayers.Geometry.Point.prototype.move.call(this, x, y);
            geometry.move(x, y)
        };
        origin._sketch = true;
        this.dragHandle = origin;
        this.layer.addFeatures([this.dragHandle], {
            silent: true
        })
    },
    collectRadiusHandle: function() {
        var geometry =
            this.feature.geometry;
        var bounds = geometry.getBounds();
        var center = bounds.getCenterLonLat();
        var originGeometry = new OpenLayers.Geometry.Point(center.lon, center.lat);
        var radiusGeometry = new OpenLayers.Geometry.Point(bounds.right, bounds.bottom);
        var radius;
        if (this.mode & OpenLayers.Control.ModifyFeature.ROTATE) radius = new OpenLayers.Feature.Vector(radiusGeometry, null, this.rotateStyle);
        else radius = new OpenLayers.Feature.Vector(radiusGeometry, null, this.resizeStyle);
        var resize = this.mode & OpenLayers.Control.ModifyFeature.RESIZE;
        var reshape = this.mode & OpenLayers.Control.ModifyFeature.RESHAPE;
        var rotate = this.mode & OpenLayers.Control.ModifyFeature.ROTATE;
        radiusGeometry.move = function(x, y) {
            OpenLayers.Geometry.Point.prototype.move.call(this, x, y);
            var dx1 = this.x - originGeometry.x;
            var dy1 = this.y - originGeometry.y;
            var dx0 = dx1 - x;
            var dy0 = dy1 - y;
            if (rotate) {
                var a0 = Math.atan2(dy0, dx0);
                var a1 = Math.atan2(dy1, dx1);
                var angle = a1 - a0;
                angle *= 180 / Math.PI;
                geometry.rotate(angle, originGeometry)
            }
            if (resize) {
                var scale, ratio;
                if (reshape) {
                    scale = dy1 / dy0;
                    ratio =
                        dx1 / dx0 / scale
                } else {
                    var l0 = Math.sqrt(dx0 * dx0 + dy0 * dy0);
                    var l1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
                    scale = l1 / l0
                }
                geometry.resize(scale, originGeometry, ratio)
            }
        };
        radius._sketch = true;
        this.radiusHandle = radius;
        this.layer.addFeatures([this.radiusHandle], {
            silent: true
        })
    },
    deactivate: function() {
        var deactivated = false;
        if (OpenLayers.Control.prototype.deactivate.apply(this, arguments)) {
            this.layer.removeFeatures(this.vertices, {
                silent: true
            });
            this.layer.removeFeatures(this.virtualVertices, {
                silent: true
            });
            this.vertices = [];
            this.dragControl.deactivate();
            var feature = this.feature;
            var valid = feature && feature.geometry && feature.layer;
            if (this.standalone === false) {
                if (valid) this.selectControl.unselect.apply(this.selectControl, [feature]);
                this.selectControl.deactivate()
            } else if (valid) this.unselectFeature(feature);
            this.unSelectTextPopup();
            this.onDeactivate();
            this.handlers.keyboard.deactivate();
            deactivated = true
        }
        return deactivated
    },
    CLASS_NAME: "GModifyFeature"
});
GProtocolWFS = function(options){
	options = OpenLayers.Util.applyDefaults(
	        options, OpenLayers.Protocol.WFS.DEFAULTS
	    );
	options.url = "/gpms/proxyPost.do?url="+encodeURIComponent(options.url);
	return new GProtocolWFS_v1_1_0(options);
}
	
GProtocolWFS_v1_1_0 = OpenLayers.Class(OpenLayers.Protocol.WFS.v1_1_0,{
	read: function(options) {
        OpenLayers.Protocol.prototype.read.apply(this, arguments);
        options = OpenLayers.Util.extend({}, options);
        OpenLayers.Util.applyDefaults(options, this.options || {});
        var response = new OpenLayers.Protocol.Response({requestType: "read"});

        var data = OpenLayers.Format.XML.prototype.write.apply(
            this.format, [this.format.writeNode("wfs:GetFeature", options)]
        );

        response.priv = GRequest.POST({
            url: options.url,
            callback: this.createCallback(this.handleRead, response, options),
            params: options.params,
            headers: options.headers,
            data: data,
        });

        return response;
    },
	CLASS_NAME: "GProtocolWFS_v1_1_0"
});

GRequest.POST = function(config) {
	config = OpenLayers.Util.extend(config, {method: "POST"});
    // set content type to application/xml if it isn't already set
    config.headers = config.headers ? config.headers : {};
    if(!("CONTENT-TYPE" in OpenLayers.Util.upperCaseObject(config.headers))) {
        config.headers["Content-Type"] = "application/xml";
    }
    return GRequest.issue(config);
}
GRequest.issue = function(config) {        
    // apply default config - proxy host may have changed
    var defaultConfig = OpenLayers.Util.extend(
        this.DEFAULT_CONFIG,
        {proxy: OpenLayers.ProxyHost}
    );
    config = config || {};
    config.headers = config.headers || {};
    config = OpenLayers.Util.applyDefaults(config, defaultConfig);
    config.headers = OpenLayers.Util.applyDefaults(config.headers, defaultConfig.headers);
    // Always set the "X-Requested-With" header to signal that this request
    // was issued through the XHR-object. Since header keys are case
    // insensitive and we want to allow overriding of the "X-Requested-With"
    // header through the user we cannot use applyDefaults, but have to
    // check manually whether we were called with a "X-Requested-With"
    // header.
    var customRequestedWithHeader = false,
        headerKey;
    for(headerKey in config.headers) {
        if (config.headers.hasOwnProperty( headerKey )) {
            if (headerKey.toLowerCase() === 'x-requested-with') {
                customRequestedWithHeader = true;
            }
        }
    }
    if (customRequestedWithHeader === false) {
        // we did not have a custom "X-Requested-With" header
        config.headers['X-Requested-With'] = 'XMLHttpRequest';
    }

    // create request, open, and set headers
    var request = new OpenLayers.Request.XMLHttpRequest();
   /*
	 * var url = OpenLayers.Util.urlAppend(config.url,
	 * OpenLayers.Util.getParameterString(config.params || {})); url =
	 * OpenLayers.Request.makeSameOrigin(url, config.proxy);
	 */
    var url = config.url+"&params="+encodeURIComponent(config.data);
    request.open(
        config.method, url, config.async, config.user, config.password
    );
    for(var header in config.headers) {
        request.setRequestHeader(header, config.headers[header]);
    }

    var events = this.events;

    // we want to execute runCallbacks with "this" as the
    // execution scope
    var self = this;
    
    request.onreadystatechange = function() {
        if(request.readyState == OpenLayers.Request.XMLHttpRequest.DONE) {
            var proceed = events.triggerEvent(
                "complete",
                {request: request, config: config, requestUrl: url}
            );
            if(proceed !== false) {
                self.runCallbacks(
                    {request: request, config: config, requestUrl: url}
                );
            }
        }
    };
    
    // send request (optionally with data) and return
    // call in a timeout for asynchronous requests so the return is
    // available before readyState == 4 for cached docs
    if(config.async === false) {
        request.send(config.data);
    } else {
        window.setTimeout(function(){
            if (request.readyState !== 0) { // W3C: 0-UNSENT
                request.send(config.data);
            }
        }, 0);
    }
    return request;
}
GGetFeature = OpenLayers.Class(OpenLayers.Control, {
    EVENT_TYPES: ["callback", "click"],
    serviceUrl: null,
    prefix: null,
    maxFeatures: 9999,
    tables: [],
    titles: {},
    persist: true,
    distance: 1,
    callbacks: null,
    currennt: false,
    alias: false,
    useDomain: false,
    layerTool: null,
    gml: new OpenLayers.Format.GML,
    result: null,
    sortFields: [],
    sortOrders: [],
    handlerOptions: {
        multiLine: true,
        persistControl: true,
        layerOptions: {
            styleMap: new OpenLayers.StyleMap({
                "default": new OpenLayers.Style(null, {
                    rules: [new OpenLayers.Rule({
                        symbolizer: {
                            "Point": {
                                pointRadius: 4,
                                graphicName: "cross",
                                fillColor: "#ffffff",
                                fillOpacity: 1,
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#0900ff"
                            },
                            "Line": {
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#333333"
                            },
                            "Polygon": {
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#333333",
                                fillColor: "#ffffff",
                                fillOpacity: .3
                            }
                        }
                    })]
                })
            })
        }
    },
    initialize: function(handler, options) {
        if (options.handlerOptions) OpenLayers.Util.extend(this.handlerOptions, options.handlerOptions);
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.EVENT_TYPES = GGetFeature.prototype.EVENT_TYPES.concat(OpenLayers.Control.prototype.EVENT_TYPES);
        this.callbacks = OpenLayers.Util.extend({
            done: this.getFeature
        }, this.callbacks);
        this.handlerOptions = OpenLayers.Util.extend({
            persist: this.persist
        }, this.handlerOptions);
        this.handler = new handler(this, this.callbacks, this.handlerOptions)
    },
    setTables: function(arr) {
        if (arr instanceof Object) this.tables = arr;
        else {
            this.tables = [];
            this.tables.push(arr)
        }
    },
    setDistance: function(distance) {
        this.distance = distance
    },
    getFeature: function(geometry) {
        this.events.triggerEvent(this.EVENT_TYPES[1], geometry);
        if (this.handler.radiusDist &&
            (this.handler.radiusDist == 0 || this.handler.radiusDist > 500)) {
            alert("\uac80\uc0c9\ubc18\uacbd\uc740 0m \uc640 500m \uc774\ub0b4\ub85c \uc124\uc815\ud574\uc8fc\uc138\uc694");
            return
        }
        if (this.layerTool) {
            this.tables = [];
            var layers = this.layerTool.getLayers({
                con: "attr",
                conVal: 1,
                order: "asc"
            });
            var sld = this.layerTool.getSld();
            var namedLayers = sld.namedLayers;
            for (var i in namedLayers) {
                var userStyles = namedLayers[i].userStyle;
                for (var j in userStyles) {
                    var rules = userStyles[j].rules;
                    for (var k in rules) {
                        if (rules[k].symbolizer.text) continue;
                        var count = 0;
                        var scale = parseInt(this.map.getScale());
                        var maxScale = rules[k].maxScale;
                        if (maxScale == 0) maxScale = parseInt(OpenLayers.Util.getScaleFromResolution(this.map.getResolutionForZoom(0), this.map.units));
                        if (maxScale >= scale && scale >= rules[k].minScale) {
                            for (var l in layers)
                                if (namedLayers[i].name == layers[l].theme)
                                    if (layers[l].show == 1) {
                                        var exist = false;
                                        for (var m in this.tables)
                                            if (this.tables[m] == layers[l].table) {
                                                exist = true;
                                                break
                                            }
                                        if (!exist) this.tables.push(layers[l].table)
                                    }
                            break
                        }
                    }
                }
            }
            if (this.tables.length <
                1) {
                alert("\uc870\uac74\uc5d0 \ub9de\ub294 \ub808\uc774\uc5b4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
                return
            }
        }
        var control = this;
        if (this.handler.CLASS_NAME == "GPoint") GRequest.WFS.getFeatureByDWithin(this.serviceUrl, {
            prefix: this.prefix,
            tables: this.tables,
            distance: this.distance,
            values: [geometry],
            sortFields: this.sortFields,
            sortOrders: this.sortOrders,
            useDomain: this.useDomain
        }, function(res) {
            control.result = res;
            control.events.triggerEvent(control.EVENT_TYPES[0], res)
        }, {
            alias: this.alias,
            titles: this.titles
        });
        else GRequest.WFS.getFeatureByGeometry(this.serviceUrl, {
            prefix: this.prefix,
            tables: this.tables,
            values: [geometry],
            sortFields: this.sortFields,
            sortOrders: this.sortOrders,
            useDomain: this.useDomain
        }, function(res) {
            control.result = res;
            control.events.triggerEvent(control.EVENT_TYPES[0], res)
        }, {
            alias: this.alias,
            titles: this.titles
        })
    },
    getResult: function() {
        return this.result
    },
    CLASS_NAME: "GGetFeature"
});
GAcss = OpenLayers.Class(OpenLayers.Control, {
    EVENT_TYPES: ["callback"],
    serviceUrl: null,
    persist: true,
    layers: [],
    styles: [],
    alias: [],
    types: [],
    width: 400,
    height: 300,
    format: "image/png",
    crs: "SR_ORG:6640",
    version: "1.0.0",
    bgcolor: "0xffffff",
    resultType: "XML",
    callbacks: null,
    imgPath: null,
    feature: null,
    handlerOptions: {
        multiLine: true,
        persistControl: true,
        layerOptions: {
            styleMap: new OpenLayers.StyleMap({
                "default": new OpenLayers.Style(null, {
                    rules: [new OpenLayers.Rule({
                        symbolizer: {
                            "Point": {
                                pointRadius: 4,
                                graphicName: "circle",
                                fillColor: "#ffffff",
                                fillOpacity: 1,
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#333333"
                            },
                            "Line": {
                                strokeWidth: 2,
                                strokeOpacity: 1,
                                strokeColor: "#ff0000"
                            },
                            "Polygon": {
                                strokeWidth: 3,
                                strokeOpacity: 1,
                                strokeColor: "#ff0000",
                                fillColor: "#ee9900",
                                fillOpacity: 0
                            }
                        }
                    })]
                })
            })
        }
    },
    res: {
        dist: 0,
        width: 0,
        height: 0,
        maxDep: 0,
        searchPoint: {
            start: {
                x: 0,
                y: 0
            },
            end: {
                x: 0,
                y: 0
            },
            dist: {
                x: 0,
                y: 0,
                signX: 0,
                signY: 0
            }
        },
        facilities: [],
        roads: [],
        offset: {
            left: 10,
            bottom: 20,
            right: 10,
            top: 40
        },
        baseLine: {
            top: 0,
            left: 0,
            bottom: 0,
            right: 0,
            width: 0,
            height: 0
        },
        eventDist: {
            move: false,
            startDist: 0,
            startDep: 0,
            endDist: 0,
            endDep: 0,
            startPos: {
                x: 0,
                y: 0
            },
            drawOffset: {
                x: 0,
                y: 0
            }
        },
        clear: function() {
            this.dist = 0;
            this.width = 0;
            this.height = 0;
            this.maxDep = 0;
            this.searchPoint = {
                start: {
                    x: 0,
                    y: 0
                },
                end: {
                    x: 0,
                    y: 0
                },
                dist: {
                    x: 0,
                    y: 0,
                    signX: 0,
                    signY: 0
                }
            };
            this.facilities = [];
            this.roads = [];
            this.distList = [];
            this.baseLine = {
                top: 0,
                left: 0,
                bottom: 0,
                right: 0,
                width: 0,
                height: 0
            }
        },
        setInitParam: function(width, height, dist) {
            this.setSize(width, height);
            this.setBaseLine();
            this.dist = parseFloat(dist)
        },
        setSize: function(width, height) {
            this.width =
                width;
            this.height = height
        },
        setBaseLine: function() {
            this.baseLine.left = parseFloat(this.offset.left);
            this.baseLine.bottom = parseFloat(this.height - this.offset.bottom);
            this.baseLine.right = parseFloat(this.width - this.offset.right);
            this.baseLine.top = parseFloat(this.offset.top);
            this.baseLine.width = parseFloat(this.width - (this.offset.left + this.offset.right));
            this.baseLine.height = parseFloat(this.height - (this.offset.bottom + this.offset.top))
        },
        setResInit: function() {
            this.setMaxDep()
        },
        setMaxDep: function() {
            for (var i =
                    0, len = this.facilities.length; i < len; i++)
                if (this.facilities[i].dep > this.maxDep) this.maxDep = parseFloat(this.facilities[i].dep)
        },
        draw: function(element, callback) {
            var tagStr = "";
            tagStr += this.drawBase();
            tagStr += this.drawRoads();
            tagStr += this.drawFacilities();
            tagStr += this.drawDistList();
            tagStr += this.drawEvent();
            $(element).html(tagStr);
            $("#eventDistLine", element).hide()
        },
        drawBase: function() {
            var str = "";
            str += '<v:line id="leftLine" from="' + this.baseLine.left + "," + this.baseLine.top + '" to="' + this.baseLine.left + "," +
                (this.baseLine.bottom + 5) + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="startText" from="' + (this.baseLine.left - 5) + "," + this.baseLine.top / 2 + '" to="' + (this.baseLine.left + 5) + "," + (this.baseLine.top / 2 + .1) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="A" style="font-size:12pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            str += '<v:line id="bottomLine" from="' + this.baseLine.left +
                "," + this.baseLine.bottom + '" to="' + this.baseLine.right + "," + this.baseLine.bottom + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="bottomLine" from="' + this.baseLine.left + "," + this.baseLine.top + '" to="' + this.baseLine.right + "," + this.baseLine.top + '" strokecolor="black" strokeweight="1pt">';
            str += '<v:stroke dashstyle="dash" />';
            str += "</v:line>";
            str += '<v:line id="rightLine" from="' + this.baseLine.right + "," + this.baseLine.top + '" to="' + this.baseLine.right + "," + (this.baseLine.bottom + 5) + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="endText" from="' + (this.baseLine.right - 5) + "," + this.baseLine.top / 2 + '" to="' + (this.baseLine.right + 5) + "," + (this.baseLine.top / 2 + .1) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="B" style="font-size:12pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            return str
        },
        drawFacilities: function() {
            var str = "";
            for (var i = 0, len = this.facilities.length; i < len; i++) str += this.drawFacility(this.facilities[i],
                i);
            return str
        },
        drawFacility: function(fac, i) {
            var str = "";
            var pixelX = GUtil.fn_fmt_fix(fac.dist * this.baseLine.width / this.dist, 2) + this.baseLine.left;
            var pos = {
                x: pixelX - 3,
                top: this.baseLine.top + 30,
                bottom: this.baseLine.bottom - 20
            };
            var posY = pos.top;
            if (this.maxDep && fac.dep != 0) posY = fac.dep * (pos.bottom - pos.top) / Math.ceil(this.maxDep);
            var ovalObj = {
                posX: "",
                posY: "",
                width: "",
                height: ""
            };
            if (fac.dip >= 0 && fac.dip <= 50) {
                ovalObj.posX = pos.x;
                ovalObj.posY = posY;
                ovalObj.size = 6
            } else if (fac.dip > 50 && fac.dip <= 100) {
                ovalObj.posX = pos.x -
                    1;
                ovalObj.posY = posY - 1;
                ovalObj.size = 8
            } else if (fac.dip > 100 && fac.dip <= 200) {
                ovalObj.posX = pos.x - 2;
                ovalObj.posY = posY - 2;
                ovalObj.size = 10
            } else if (fac.dip > 200 && fac.dip <= 300) {
                ovalObj.posX = pos.x - 3;
                ovalObj.posY = posY - 3;
                ovalObj.size = 12
            } else if (fac.dip > 300 && fac.dip <= 400) {
                ovalObj.posX = pos.x - 4;
                ovalObj.posY = posY - 4;
                ovalObj.size = 14
            } else if (fac.dip > 400) {
                ovalObj.posX = pos.x - 5;
                ovalObj.posY = posY - 5;
                ovalObj.size = 16
            }
            str += '<v:oval id="underPoi' + i + '" class="underPoi" strokecolor="' + fac.color + '" fillcolor="' + fac.color + '" style="position:absolute; left:' +
                ovalObj.posX + "px; top:" + ovalObj.posY + "px; width:" + ovalObj.size + "px; height:" + ovalObj.size + 'px;" />';
            str += '<v:line id="underFac' + i + '" table="' + fac.table + '" class="underFac" from="' + pixelX + "," + this.baseLine.top + '" to="' + pixelX + "," + this.baseLine.bottom + '" strokeweight="1pt">';
            str += '<v:stroke color="' + fac.color + '" />';
            str += "</v:line>";
            str += '<v:line from="' + (pos.x - 3) + "," + posY + '" to="' + (pos.x - 3 + .1) + "," + (posY - 20) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="' +
                fac.dep + '" style="font-size:6pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            return str
        },
        drawRoads: function() {
            var str = "";
            for (var i = 0, len = this.roads.length; i < len; i++) str += this.drawRoad(this.roads[i], i);
            return str
        },
        drawRoad: function(road, i) {
            var offset = {
                dist: -30,
                rect: -15
            };
            var str = "";
            var roadSX = GUtil.fn_fmt_fix(road.dist * this.baseLine.width / this.dist, 2) + this.baseLine.left;
            var roadEX = GUtil.fn_fmt_fix(road.distEnd * this.baseLine.width / this.dist, 2) + this.baseLine.left;
            if (roadSX <=
                this.baseLine.left) roadSX = this.baseLine.left;
            if (roadEX > this.baseLine.width + this.baseLine.left) roadEX = this.baseLine.width + this.baseLine.left;
            var destSE = roadEX - roadSX;
            str += '<v:rect strokecolor="#ffffff" fillcolor="' + road.color + '" style="position:absolute;top:' + (this.baseLine.top + offset.rect) + "px; left:" + roadSX + "px; width:" + destSE + 'px; height:15px" />';
            if (roadSX != this.baseLine.left) {
                str += '<v:line from="' + roadSX + "," + this.baseLine.top + '" to="' + roadSX + "," + this.baseLine.bottom + '" strokeweight="1pt" >';
                str +=
                    '<v:stroke dashstyle="dot" color="' + road.color + '" />';
                str += "</v:line>"
            }
            str += '<v:line from="' + roadEX + "," + this.baseLine.top + '" to="' + roadEX + "," + this.baseLine.bottom + '" strokeweight="1pt" >';
            str += '<v:stroke dashstyle="dot" color="' + road.color + '" />';
            str += "</v:line>";
            return str
        },
        drawDistList: function() {
            var distList = [];
            distList.push(0);
            for (var i = 0, len = this.facilities.length; i < len; i++) distList.push(parseFloat(this.facilities[i].dist));
            for (var i = 0, len = this.roads.length; i < len; i++) {
                if (this.roads[i].dist > 0) distList.push(parseFloat(this.roads[i].dist));
                var endDist = parseFloat(parseFloat(this.roads[i].distEnd));
                if (endDist < this.dist) distList.push(endDist)
            }
            distList.push(this.dist);
            for (var i = distList.length - 1; i > 0; i--)
                for (var j = 0; j < i; j++)
                    if (distList[j] > distList[j + 1]) {
                        var temp = distList[j];
                        distList[j] = distList[j + 1];
                        distList[j + 1] = temp
                    }
            var str = "";
            for (var i = 0, len = distList.length - 1; i < len; i++) str += this.drawFacDist(distList[i], distList[i + 1], i);
            return str
        },
        drawFacDist: function(start, end, i) {
            if (start == this.baseLine.left) return "";
            var offsetX = 5;
            var offsetY;
            if (i % 2 ==
                0) offsetY = 8;
            else offsetY = -8;
            var facDist = GUtil.fn_fmt_fix(end - start, 2);
            var facDistX = GUtil.fn_fmt_fix((start + facDist / 2) * this.baseLine.width / this.dist, 2) + this.offset.left;
            if (facDist == 0) return "";
            var str = "";
            str += '<v:line from="' + (facDistX - offsetX) + "," + (this.baseLine.bottom - offsetY) + '" to="' + (facDistX + offsetX) + "," + (this.baseLine.bottom - offsetY - .1) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="' + facDist + '" style="font-size:6pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            return str
        },
        drawEvent: function() {
            var str = "";
            str += '<v:line id="eventDistLine" from="0,0" to="0,0" strokecolor="red" strokeweight="1pt" />';
            str += '<v:line id="eventDistStr" from="0,0" to="0,0">';
            str += '<v:fill on="true" color="red" />';
            str += '<v:stroke color="red" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" string="" style="font-size:6pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal;" />';
            str += "</v:line>";
            str += "<div class='acssAttr'>";
            str += "<div style='margin-bottom:5px;'><span class='titLyr'></span></div>";
            str += "<div><span class='tit'>\uad6c\uacbd</span> : <span class='dip'></span></div>";
            str += "<div><span class='tit'>\uc2ec\ub3c4</span> : <span class='dep'></span></div>";
            str += "</div>";
            return str
        }
    },
    initEvent: function(element) {
        $(element).unbind("mousemove");
        $(".underPoi", element).unbind("click");
        $(".underFac", element).unbind("mouseover");
        $(".underFac", element).unbind("mouseout");
        $(".underFac", element).unbind("click")
    },
    positionEvent: function(element) {
        $(element).bind("mousemove", this, function(evt) {
            var control =
                evt.data;
            if (evt.offsetX >= 10 && evt.offsetX <= 590) {
                var diffX = control.res.searchPoint.dist.x * (evt.offsetX - 10) / 580;
                var diffY = control.res.searchPoint.dist.y * (evt.offsetX - 10) / 580;
                var lon = parseFloat(control.res.searchPoint.start.x) + parseFloat(diffX * control.res.searchPoint.dist.signX);
                var lat = parseFloat(control.res.searchPoint.start.y) + parseFloat(diffY * control.res.searchPoint.dist.signY);
                var lonlat = new OpenLayers.LonLat(lon, lat);
                control.feature.move(lonlat)
            }
        })
    },
    attrEvent: function(element, callback) {
        $(".underFac",
            element).bind("mouseover", this.res, function(evt) {
            var id = $(this).attr("id").replace("underFac", "");
            $(".acssAttr span.dip", element).text(evt.data.facilities[id].dip);
            $(".acssAttr span.dep", element).text(evt.data.facilities[id].dep);
            $(".acssAttr span.titLyr", element).text(evt.data.facilities[id].layer);
            $(".acssAttr", element).css("left", $(this).offset().left);
            $(".acssAttr", element).css("top", evt.clientY - $(element).position().top);
            $(".acssAttr", element).show()
        });
        $(".underFac", element).bind("mouseout",
            this.res,
            function(evt) {
                $(".acssAttr", element).hide()
            });
        $(".underFac", element).bind("click", this.res, function(evt) {
            var id = $(this).attr("id").replace("underFac", "");
            if (callback) callback(evt.data.facilities[id].table, evt.data.facilities[id].id)
        })
    },
    distEvent: function(element) {
        this.res.eventDist.drawOffset.x = $(element).position().left + 10;
        this.res.eventDist.drawOffset.y = $(element).position().top + 10;
        $(".underPoi", element).bind("click", this.res, function(evt) {
            var id = $(this).attr("id").replace("underPoi", "");
            if (evt.data.eventDist.move) {
                evt.data.eventDist.move = false;
                evt.data.eventDist.endDist = evt.data.facilities[id].dist;
                evt.data.eventDist.endDep = evt.data.facilities[id].dep;
                var distance = Math.abs(evt.data.eventDist.endDist - evt.data.eventDist.startDist);
                var dep = Math.abs(evt.data.eventDist.endDep - evt.data.eventDist.startDep);
                var left = evt.clientX - evt.data.eventDist.drawOffset.x + 15;
                var top = evt.clientY - evt.data.eventDist.drawOffset.y;
                $("#eventDistStr", element).attr("from", left + "," + top);
                $("#eventDistStr", element).attr("to",
                    parseFloat(left) + 10 + "," + (parseFloat(top) + .1));
                $("#eventDistStr textpath", element).attr("string", GUtil.fn_fmt_fix(Math.sqrt(distance * distance + dep * dep), 2));
                $(element).unbind("mousemove")
            } else {
                evt.data.eventDist.move = true;
                evt.data.eventDist.startDist = evt.data.facilities[id].dist;
                evt.data.eventDist.startDep = evt.data.facilities[id].dep;
                var left = $(this).css("left").replace("px", "");
                var top = $(this).css("top").replace("px", "");
                $("#eventDistLine", element).attr("from", parseFloat(left) + 4 + "," + (parseFloat(top) +
                    3));
                $("#eventDistLine", element).attr("to", parseFloat(left) + 4 + "," + (parseFloat(top) + 3));
                $("#eventDistLine", element).show();
                $(element).bind("mousemove", evt.data, function(evt) {
                    var control = evt.data;
                    var left = evt.clientX - evt.data.eventDist.drawOffset.x;
                    var top = evt.clientY - evt.data.eventDist.drawOffset.y;
                    $("#eventDistLine", element).attr("to", left + "," + top)
                })
            }
        })
    },
    activeEvent: function(element, type, callback) {
        this.initEvent(element);
        if (type == "p") this.positionEvent(element);
        else if (type == "d") this.distEvent(element);
        else if (type == "a") this.attrEvent(element, callback)
    },
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.EVENT_TYPES = GAcss.prototype.EVENT_TYPES.concat(OpenLayers.Control.prototype.EVENT_TYPES);
        this.callbacks = OpenLayers.Util.extend({
            mouseup: this.getFeature,
            create: function(point, geometry) {
                this.handler.layer.removeAllFeatures()
            }
        }, this.callbacks);
        this.handlerOptions = OpenLayers.Util.extend({
            persist: this.persist
        }, this.handlerOptions);
        this.handler = new GPath(this,
            this.callbacks, this.handlerOptions)
    },
    setStyle: function(obj) {
        for (var i in obj)
            for (var j in obj[i]) this.handlerOptions["layerOptions"]["styleMap"]["styles"]["default"]["rules"][0]["symbolizer"][i][j] = obj[i][j];
        this.handler.handlerOptions = this.handlerOptions
    },
    getFeature: function(point, geometry) {
        this.res.clear();
        var searchPoint = "";
        var offsetX = 2;
        var offsetY = 2;
        if (geometry.components.length == 2);
        if (geometry.components.length == 3) {
            searchPoint = geometry.getVertices()[0].x + "," + geometry.getVertices()[0].y + ",";
            searchPoint += geometry.getVertices()[1].x + "," + geometry.getVertices()[1].y;
            this.handler.finish();
            var point = new OpenLayers.Geometry.Point(geometry.getVertices()[1].x, geometry.getVertices()[1].y);
            this.feature = new OpenLayers.Feature.Vector(point);
            this.handler.layer.addFeatures(this.feature);
            this.res.setInitParam(this.width, this.height, geometry.getLength());
            this.insertLabel(geometry);
            var params = GUtil.fn_convert_objToStr({
                SERVICE: "ACSS",
                VERSION: this.version,
                REQUEST: "GetCSView",
                LAYERS: this.layers.join(),
                STYLES: this.styles.join(),
                TYPES: this.types.join(),
                SEARCHPOINT: searchPoint,
                CRS: this.crs,
                WIDTH: this.width,
                HEIGHT: this.height,
                FORMAT: this.format,
                BGCOLOR: this.bgcolor,
                RESULTTYPE: this.resultType
            });
            var obj = this;
            if (this.resultType == "VIEW") {
                this.imgPath = this.serviceUrl + params;
                this.events.triggerEvent(this.EVENT_TYPES[0])
            } else GMapUtil.sendProxyGet(this.serviceUrl, params, function(res) {
                obj.parseRes(res, obj)
            });
            $("*").css("cursor", "default")
        }
    },
    insertLabel: function(geometry) {
        var offset = 2;
        var offsetX;
        var offsetY;
        var styleA = {
            label: "A",
            fontSize: 20,
            fontWeight: "bold"
        };
        var styleB = {
            label: "B",
            fontSize: 20,
            fontWeight: "bold"
        };
        if (geometry.getVertices()[0].x - geometry.getVertices()[1].x < 0) offsetX = offset * -1;
        else offsetX = offset;
        if (geometry.getVertices()[0].y - geometry.getVertices()[1].y < 0) offsetY = offset * -1;
        else offsetY = offset;
        var posA = new OpenLayers.Geometry.Point(geometry.getVertices()[0].x + offsetX, geometry.getVertices()[0].y + offsetY);
        if (geometry.getVertices()[geometry.getVertices().length - 1].x - geometry.getVertices()[geometry.getVertices().length -
                2].x < 0) offsetX = offset * -1;
        else offsetX = offset;
        if (geometry.getVertices()[geometry.getVertices().length - 1].y - geometry.getVertices()[geometry.getVertices().length - 2].y < 0) offsetY = offset * -1;
        else offsetY = offset;
        var posB = new OpenLayers.Geometry.Point(geometry.getVertices()[geometry.getVertices().length - 1].x + offsetX, geometry.getVertices()[geometry.getVertices().length - 1].y + offsetY);
        var labelA = new OpenLayers.Feature.Vector(posA, null, styleA);
        var labelB = new OpenLayers.Feature.Vector(posB, null, styleB);
        this.handler.layer.addFeatures(labelA);
        this.handler.layer.addFeatures(labelB)
    },
    parseRes: function(res, obj) {
        var result = res.getElementsByTagName("CrossSectionResult");
        obj.res.distList.push(0);
        var underFacility, roads;
        if (result.length > 0) {
            var searchPoint = result[0].getElementsByTagName("SearchPoint");
            underFacility = result[0].getElementsByTagName("UnderFacility");
            roads = result[0].getElementsByTagName("Road");
            if (searchPoint.length > 0) {
                var start = searchPoint[0].getElementsByTagName("StartPoint");
                var end = searchPoint[0].getElementsByTagName("EndPoint");
                var len = searchPoint[0].getElementsByTagName("Length");
                var x, y;
                var text = "";
                if (start.length > 0) {
                    x = start[0].getElementsByTagName("X");
                    y = start[0].getElementsByTagName("Y");
                    if (x.length >= 0) {
                        text = x[0].text ? x[0].text : x[0].textContent;
                        obj.res.searchPoint.start.x = text
                    }
                    if (y.length >= 0) {
                        text = y[0].text ? y[0].text : y[0].textContent;
                        obj.res.searchPoint.start.y = text
                    }
                }
                if (end.length > 0) {
                    x = end[0].getElementsByTagName("X");
                    y = end[0].getElementsByTagName("Y");
                    if (x.length >= 0) {
                        text = x[0].text ? x[0].text : x[0].textContent;
                        obj.res.searchPoint.end.x =
                            text
                    }
                    if (y.length >= 0) {
                        text = y[0].text ? y[0].text : y[0].textContent;
                        obj.res.searchPoint.end.y = text
                    }
                }
                if (len.length > 0) {
                    text = len[0].text ? len[0].text : len[0].textContent;
                    obj.res.dist = text
                }
                if (obj.res.searchPoint.start.x - obj.res.searchPoint.end.x > 0) obj.res.searchPoint.dist.signX = -1;
                else obj.res.searchPoint.dist.signX = 1;
                if (obj.res.searchPoint.start.y - obj.res.searchPoint.end.y > 0) obj.res.searchPoint.dist.signY = -1;
                else obj.res.searchPoint.dist.signY = 1;
                obj.res.searchPoint.dist.x = Math.abs(obj.res.searchPoint.start.x -
                    obj.res.searchPoint.end.x);
                obj.res.searchPoint.dist.y = Math.abs(obj.res.searchPoint.start.y - obj.res.searchPoint.end.y)
            }
        }
        if (underFacility.length > 0) {
            var features = underFacility[0].getElementsByTagName("Feature");
            if (features.length > 0)
                for (var i = 0, len = features.length; i < len; i++) {
                    var feature = {
                        dist: 0,
                        table: "",
                        layer: "",
                        id: 0,
                        dep: 0,
                        dip: 0,
                        color: "",
                        type: "fac"
                    };
                    var dist = features[i].getElementsByTagName("Distance");
                    var table = features[i].getElementsByTagName("DataSetName");
                    var layer = features[i].getElementsByTagName("LayerName");
                    var id = features[i].getElementsByTagName("FeatureID");
                    var dep = features[i].getElementsByTagName("Depth");
                    var dip = features[i].getElementsByTagName("Diameter");
                    var color = features[i].getElementsByTagName("Color");
                    var text = "";
                    if (dist.length > 0) {
                        text = dist[0].text ? parseFloat(dist[0].text) : parseFloat(dist[0].textContent);
                        feature.dist = text
                    }
                    if (table.length > 0) {
                        text = table[0].text ? table[0].text : table[0].textContent;
                        feature.table = text
                    }
                    if (layer.length > 0) {
                        text = layer[0].text ? layer[0].text : layer[0].textContent;
                        feature.layer =
                            text
                    }
                    if (id.length > 0) {
                        text = id[0].text ? id[0].text : id[0].textContent;
                        feature.id = text
                    }
                    if (dep.length > 0) {
                        text = dep[0].text ? parseFloat(dep[0].text) : parseFloat(dep[0].textContent);
                        feature.dep = text
                    }
                    if (dip.length > 0) {
                        text = dip[0].text ? parseFloat(dip[0].text) : parseFloat(dip[0].textContent);
                        feature.dip = text
                    }
                    if (color.length > 0) {
                        text = color[0].text ? color[0].text : color[0].textContent;
                        feature.color = "#" + text.substring(3)
                    }
                    obj.res.facilities.push(feature)
                }
        }
        if (roads.length > 0) {
            var features = roads[0].getElementsByTagName("Feature");
            if (features.length > 0)
                for (var i = 0, len = features.length; i < len; i++) {
                    var feature = {
                        dist: 0,
                        table: "",
                        layer: "",
                        id: 0,
                        color: "",
                        type: "road"
                    };
                    var dist = features[i].getElementsByTagName("Distance1");
                    var distEnd = features[i].getElementsByTagName("Distance2");
                    var table = features[i].getElementsByTagName("DataSetName");
                    var layer = features[i].getElementsByTagName("LayerName");
                    var id = features[i].getElementsByTagName("FeatureID");
                    var color = features[i].getElementsByTagName("Color");
                    var text = "";
                    if (dist.length > 0) {
                        text = dist[0].text ?
                            parseFloat(dist[0].text) : parseFloat(dist[0].textContent);
                        feature.dist = text
                    }
                    if (distEnd.length > 0) {
                        text = distEnd[0].text ? parseFloat(distEnd[0].text) : parseFloat(distEnd[0].textContent);
                        feature.distEnd = text
                    }
                    if (table.length > 0) {
                        text = table[0].text ? table[0].text : table[0].textContent;
                        feature.table = text
                    }
                    if (layer.length > 0) {
                        text = layer[0].text ? layer[0].text : layer[0].textContent;
                        feature.layer = text
                    }
                    if (id.length > 0) {
                        text = id[0].text ? id[0].text : id[0].textContent;
                        feature.id = text
                    }
                    if (color.length > 0) {
                        text = color[0].text ? color[0].text :
                            color[0].textContent;
                        feature.color = "#" + text.substring(3)
                    }
                    obj.res.roads.push(feature)
                }
        }
        obj.res.setResInit();
        obj.events.triggerEvent(obj.EVENT_TYPES[0])
    },
    orderBySeq: function(arr, field, order) {
        var len = arr.length;
        for (var i = len - 1; i > 0; i--)
            for (var j = 0; j < i; j++)
                if (order.toLowerCase() == "desc") {
                    if (arr[j][field] < arr[j + 1][field]) GUtil.Array.fn_swap_element(arr, j, j + 1)
                } else if (arr[j][field] > arr[j + 1][field]) GUtil.Array.fn_swap_element(arr, j, j + 1)
    },
    getAlias: function() {
        return this.alias
    },
    getLayers: function() {
        return this.layers
    },
    getStyles: function() {
        return this.styles
    },
    getImgPath: function() {
        return this.imgPath
    },
    CLASS_NAME: "GAcss"
});
GAlis = OpenLayers.Class(OpenLayers.Control, {
    EVENT_TYPES: ["callback"],
    serviceUrl: null,
    persist: true,
    alias: false,
    service: "ALIS",
    version: "1.1.0",
    request: "GetLeakIsolation",
    layers: [],
    types: [],
    pipeDistance: .5,
    pipeJoinDistance: .01,
    valveDistance: .01,
    format: "text/xml",
    exceptions: "Xml",
    requesttype: "TOTAL",
    crs: "EPSG:4326",
    where: "",
    callbacks: null,
    handlerOptions: {
        persistLayer: true,
        layerOptions: {
            styleMap: new OpenLayers.StyleMap({
                "default": new OpenLayers.Style({
                    pointRadius: 4,
                    graphicName: "circle",
                    fillColor: "#ffffff",
                    fillOpacity: 1,
                    strokeWidth: 1,
                    strokeOpacity: 1,
                    strokeColor: "#333333"
                })
            })
        }
    },
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.EVENT_TYPES = GAlis.prototype.EVENT_TYPES.concat(OpenLayers.Control.prototype.EVENT_TYPES);
        this.callbacks = OpenLayers.Util.extend({
            done: this.getFeature
        }, this.callbacks);
        this.handlerOptions = OpenLayers.Util.extend({
            persist: this.persist
        }, this.handlerOptions);
        this.handler = new GPoint(this, this.callbacks, this.handlerOptions)
    },
    getFeature: function(geometry) {
        var control =
            this;
        var point = geometry.getCentroid().x + "," + geometry.getCentroid().y;
        var params = GUtil.fn_convert_objToStr({
            SERVICE: this.service,
            VERSION: this.version,
            REQUEST: this.request,
            LAYERS: this.layers.join(),
            TYPES: this.types.join(),
            LEAKPOINT: point,
            PIPEDISTANCE: this.pipeDistance,
            PIPEJOINDISTANCE: this.pipeJoinDistance,
            VALVEDISTANCE: this.valveDistance,
            FORMAT: this.format,
            EXCEPTIONS: this.exceptions,
            REQUESTTYPE: this.requesttype,
            CRS: this.crs,
            WHERES: this.where
        });
        GMapUtil.sendProxyPost(this.serviceUrl, params, function(res) {
            control.parseResponse(res)
        })
    },
    parseResponse: function(res) {
        var resObj = {
            pipes: [],
            valves: []
        };
        var success = true;
        var leakIsolationResult = res.getElementsByTagName("LeakIsolationResult");
        if (leakIsolationResult && leakIsolationResult.length > 0) {
            this.parseTable(leakIsolationResult[0], resObj, "Pipes");
            this.parseTable(leakIsolationResult[0], resObj, "Valves");
            if (resObj.pipes.length <= 0 && resObj.valves.length <= 0) success = false
        } else success = false;
/*
 * if (!this.alias) this.getRequestAlias(resObj, success); else
 * this.events.triggerEvent(this.EVENT_TYPES[0], { data: resObj, success:
 * function() { return success } })
 */
        this.events.triggerEvent(this.EVENT_TYPES[0], {
            data: resObj,
            success: function() {
                return success
            }
        })
    },
    parseTable: function(element, resObj, tagName) {
        var property = tagName.toLowerCase();
        var elements = element.getElementsByTagName(tagName);
        if (elements && elements.length > 0 && elements[0].textContent != "") {
            var elementArr = elements[0].textContent.split(",");
            for (var i = 0, len = elementArr.length; i < len; i++) {
                var tblArr = elementArr[i].split(".");
                var tblStr = tblArr[0];
                var index = false;
                for (var j in resObj[property])
                    if (resObj[property][j].table == tblStr) index = j;
                if (!index) {
                    var tblObj = {
                        table: tblStr,
                        alias: null,
                        ids: []
                    };
                    resObj[property].push(tblObj)
                } else tblObj = resObj[property][index];
                tblObj.ids.push(tblArr[1])
            }
        } else return false
    },
    getRequestAlias: function(obj, success) {
        if (!success) {
            this.events.triggerEvent(this.EVENT_TYPES[0], {
                data: null,
                success: function() {
                    return success
                }
            });
            return
        }
        var control = this;
        var paramsStr = "";
        for (var i = 0, len = obj.pipes.length; i < len; i++) paramsStr += obj.pipes[i].table + ":";
        for (var i = 0, len = obj.valves.length; i < len; i++) paramsStr += obj.valves[i].table + ":";
        $.post("/gmap/attr/getAlias.do", {
                data: paramsStr
            },
            function(res) {
                var index = 0;
                for (var i = 0, len = obj.pipes.length; i < len; i++) {
                    obj.pipes[i].alias = res.data[index][obj.pipes[i].table];
                    index++
                }
                for (var i = 0, len = obj.valves.length; i < len; i++) {
                    obj.valves[i].alias = res.data[index][obj.valves[i].table];
                    index++
                }
                control.events.triggerEvent(control.EVENT_TYPES[0], {
                    data: obj,
                    success: function() {
                        return success
                    }
                })
            }, "json")
    },
    CLASS_NAME: "GAlis"
});
GProfile = OpenLayers.Class(OpenLayers.Control, {
    EVENT_TYPES: ["callback"],
    serviceUrl: null,
    persist: true,
    width: 400,
    height: 300,
    version: "1.0.0",
    layer: "DEM",
    interval: 1,
    callbacks: null,
    feature: null,
    handlerOptions: {
        multiLine: true,
        persistControl: true,
        layerOptions: {
            styleMap: new OpenLayers.StyleMap({
                "default": new OpenLayers.Style(null, {
                    rules: [new OpenLayers.Rule({
                        symbolizer: {
                            "Point": {
                                pointRadius: 4,
                                graphicName: "circle",
                                fillColor: "#ffffff",
                                fillOpacity: 1,
                                strokeWidth: 1,
                                strokeOpacity: 1,
                                strokeColor: "#333333"
                            },
                            "Line": {
                                strokeWidth: 2,
                                strokeOpacity: 1,
                                strokeColor: "#ff0000"
                            },
                            "Polygon": {
                                strokeWidth: 3,
                                strokeOpacity: 1,
                                strokeColor: "#ff0000",
                                fillColor: "#ee9900",
                                fillOpacity: 0
                            }
                        }
                    })]
                })
            })
        }
    },
    res: {
        dist: 0,
        width: 0,
        height: 0,
        maxZ: 0,
        posWidth: 0,
        posHeight: 0,
        posList: [],
        offset: {
            left: 30,
            bottom: 20,
            right: 10,
            top: 40
        },
        baseLine: {
            top: 0,
            left: 0,
            bottom: 0,
            right: 0,
            width: 0,
            height: 0
        },
        eventDist: {
            move: false,
            startDist: 0,
            startZ: 0,
            endDist: 0,
            endDep: 0,
            startPos: {
                x: 0,
                y: 0
            },
            drawOffset: {
                x: 0,
                y: 0
            }
        },
        clear: function() {
            this.dist = 0;
            this.width = 0;
            this.height = 0;
            this.maxZ = 0;
            this.posList = [];
            this.baseLine = {
                top: 0,
                left: 0,
                bottom: 0,
                right: 0,
                width: 0,
                height: 0
            };
            this.eventDist = {
                move: false,
                startDist: 0,
                startZ: 0,
                endDist: 0,
                endZ: 0,
                startPos: {
                    x: 0,
                    y: 0
                },
                drawOffset: {
                    x: 0,
                    y: 0
                }
            }
        },
        setInitParam: function(width, height, dist) {
            this.setSize(width, height);
            this.setBaseLine();
            this.dist = parseFloat(dist)
        },
        setSize: function(width, height) {
            this.width = width;
            this.height = height
        },
        setBaseLine: function() {
            this.baseLine.left = parseFloat(this.offset.left);
            this.baseLine.bottom = parseFloat(this.height - this.offset.bottom);
            this.baseLine.right =
                parseFloat(this.width - this.offset.right);
            this.baseLine.top = parseFloat(this.offset.top);
            this.baseLine.width = parseFloat(this.width - (this.offset.left + this.offset.right));
            this.baseLine.height = parseFloat(this.height - (this.offset.bottom + this.offset.top))
        },
        setResInit: function() {
            this.setMaxZ();
            this.setPos()
        },
        setMaxZ: function() {
            for (var i = 0, len = this.posList.length; i < len; i++)
                if (this.posList[i].z > this.maxZ) this.maxZ = parseFloat(this.posList[i].z)
        },
        setPos: function() {
            this.posWidth = this.baseLine.width / (this.dist -
                1);
            this.posHeight = this.baseLine.height / this.maxZ
        },
        draw: function(element, callback) {
            var tagStr = "";
            tagStr += this.drawBase();
            tagStr += this.drawPosZList();
            tagStr += this.drawLattice();
            tagStr += this.drawEvent();
            $(element).html(tagStr)
        },
        drawBase: function() {
            var str = "";
            str += '<v:line id="leftLine" from="' + this.baseLine.left + "," + this.baseLine.top + '" to="' + this.baseLine.left + "," + (this.baseLine.bottom + 5) + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="startText" from="' + (this.baseLine.left - 5) + "," + this.baseLine.top /
                2 + '" to="' + (this.baseLine.left + 5) + "," + (this.baseLine.top / 2 + .1) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="A" style="font-size:12pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            str += '<v:line id="bottomLine" from="' + this.baseLine.left + "," + this.baseLine.bottom + '" to="' + this.baseLine.right + "," + this.baseLine.bottom + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="bottomLine" from="' +
                this.baseLine.left + "," + this.baseLine.top + '" to="' + this.baseLine.right + "," + this.baseLine.top + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="rightLine" from="' + this.baseLine.right + "," + this.baseLine.top + '" to="' + this.baseLine.right + "," + (this.baseLine.bottom + 5) + '" strokecolor="black" strokeweight="1pt" />';
            str += '<v:line id="endText" from="' + (this.baseLine.right - 5) + "," + this.baseLine.top / 2 + '" to="' + (this.baseLine.right + 5) + "," + (this.baseLine.top / 2 + .1) + '">';
            str += '<v:fill on="true" color="black" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" fitpath="false" string="B" style="font-size:12pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
            str += "</v:line>";
            return str
        },
        drawLattice: function() {
            var str = "";
            var LatticeXSize = Math.floor(this.posList.length / 10);
            var cipher = [500, 100, 10, 5];
            for (var i = 0, len = cipher.length; i < len; i++)
                if (LatticeXSize > cipher[i]) {
                    while (true) {
                        LatticeXSize--;
                        if (LatticeXSize % cipher[i] == 0) break
                    }
                    break
                }
            for (var i = LatticeXSize, len = this.posList.length; i < len; i += LatticeXSize) {
                var pixelX =
                    GUtil.fn_fmt_fix(this.posWidth * i, 2) + this.baseLine.left;
                str += '<v:line id="latticeRow' + i + '" from="' + pixelX + "," + this.baseLine.top + '" to="' + pixelX + "," + this.baseLine.bottom + '" strokecolor="black" strokeweight="1pt">';
                str += '<v:stroke dashstyle="dash" />';
                str += "</v:line>";
                str += '<v:line id="latticeText" from="' + (pixelX - 3) + "," + (this.baseLine.top - 7) + '" to="' + (pixelX + 3) + "," + (this.baseLine.top - 7 + .1) + '">';
                str += '<v:fill on="true" color="black" />';
                str += '<v:path textpathok="true" />';
                str += '<v:textpath on="true" fitpath="false" string="' +
                    i + 'm" style="font-size:8pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
                str += "</v:line>"
            }
            var LatticeYSize = Math.floor(this.maxZ / 4);
            for (var i = 0, len = cipher.length; i < len; i++)
                if (LatticeYSize > cipher[i]) {
                    while (true) {
                        LatticeYSize--;
                        if (LatticeYSize % cipher[i] == 0) break
                    }
                    break
                }
            for (var i = 0; i < this.maxZ; i += LatticeYSize) {
                var pixelY = this.baseLine.bottom - this.posHeight * i;
                str += '<v:line id="latticeCell' + i + '" from="' + this.baseLine.left + "," + pixelY + '" to="' + this.baseLine.right + "," + pixelY + '" strokecolor="black" strokeweight="1pt">';
                str += '<v:stroke dashstyle="dash" />';
                str += "</v:line>";
                str += '<v:line id="latticeText" from="' + (this.baseLine.left - 20) + "," + pixelY + '" to="' + (this.baseLine.left - 10) + "," + (pixelY + .1) + '">';
                str += '<v:fill on="true" color="black" />';
                str += '<v:path textpathok="true" />';
                str += '<v:textpath on="true" fitpath="false" string="' + i + 'm" style="font-size:8pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal" />';
                str += "</v:line>"
            }
            return str
        },
        drawPosZList: function() {
            var str = "";
            str += "<v:shape style='position:absolute;top:0px;left:0px;width:" +
                this.width + "px;height:" + this.height + "px' stroke='true' strokecolor='black' strokeweight='1' file='true' fillcolor='#08FF08' coordorigin='0 0' coordsize='" + this.width + ", " + this.height + "' >";
            str += "<v:path v='m ";
            for (var i = 0, len = this.posList.length; i < len; i++) {
                if (i == 1) str += " l ";
                else if (i != 0) str += ",";
                str += this.drawPosZ(this.posList[i], i)
            }
            str += "," + this.baseLine.right + "," + this.baseLine.bottom + "," + this.baseLine.left + "," + this.baseLine.bottom;
            str += " x e' />";
            str += "</v:shape>";
            $("#txtTest").val(str);
            return str
        },
        drawPosZ: function(pos, i) {
            var pixelX = Math.floor(this.posWidth * i, 2) + this.baseLine.left;
            var pixelY = Math.floor(this.baseLine.bottom - pos.z * this.baseLine.height / this.maxZ);
            return pixelX + "," + pixelY
        },
        drawEvent: function() {
            var str = "";
            str += '<v:line id="eventDistLine" from="0,0" to="0,0" strokecolor="red" strokeweight="1pt" />';
            str += '<v:line id="eventDistStr" from="0,0" to="0,0">';
            str += '<v:fill on="true" color="red" />';
            str += '<v:stroke color="red" />';
            str += '<v:path textpathok="true" />';
            str += '<v:textpath on="true" string="" style="font-size:8pt;font-family:dotum,\ub3cb\uc6c0;font-weight:normal;" />';
            str += "</v:line>";
            return str
        }
    },
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.EVENT_TYPES = GAcss.prototype.EVENT_TYPES.concat(OpenLayers.Control.prototype.EVENT_TYPES);
        this.callbacks = OpenLayers.Util.extend({
            done: this.getFeature
        }, this.callbacks);
        this.handlerOptions = OpenLayers.Util.extend({
            persist: this.persist
        }, this.handlerOptions);
        this.handler = new GPath(this, this.callbacks, this.handlerOptions)
    },
    getFeature: function(geometry) {
        this.res.clear();
        var searchPoint =
            "";
        if (geometry.components.length >= 2) {
            for (var i = 0, len = geometry.getVertices().length; i < len; i++) {
                if (i != 0) searchPoint += ",";
                searchPoint += geometry.getVertices()[i].x + "," + geometry.getVertices()[i].y
            }
            this.insertLabel(geometry);
            var point = new OpenLayers.Geometry.Point(geometry.getVertices()[geometry.getVertices().length - 1].x, geometry.getVertices()[geometry.getVertices().length - 1].y);
            this.feature = new OpenLayers.Feature.Vector(point);
            this.handler.layer.addFeatures(this.feature);
            this.res.setInitParam(this.width,
                this.height, geometry.getLength());
            var params = GUtil.fn_convert_objToStr({
                SERVICE: "WPS",
                VERSION: this.version,
                REQUEST: "Execute",
                IDENTIFIER: "Profile",
                DATAINPUTS: "[LAYER=" + this.layer + ";CLIP_LINE=" + searchPoint + ";INTERVAL=" + this.interval + "]",
                RESPONSEDOCUMENT: "PROFILE_OUTPUT"
            });
            $("#txtTest").val(params);
            var obj = this;
            GMapUtil.sendProxyGet(this.serviceUrl, params, function(res) {
                obj.parseRes(res, obj)
            })
        }
    },
    insertLabel: function(geometry) {
        var offset = 2;
        var offsetX;
        var offsetY;
        var styleA = {
            label: "A",
            fontSize: 20,
            fontWeight: "bold"
        };
        var styleB = {
            label: "B",
            fontSize: 20,
            fontWeight: "bold"
        };
        if (geometry.getVertices()[0].x - geometry.getVertices()[1].x < 0) offsetX = offset * -1;
        else offsetX = offset;
        if (geometry.getVertices()[0].y - geometry.getVertices()[1].y < 0) offsetY = offset * -1;
        else offsetY = offset;
        var posA = new OpenLayers.Geometry.Point(geometry.getVertices()[0].x + offsetX, geometry.getVertices()[0].y + offsetY);
        if (geometry.getVertices()[geometry.getVertices().length - 1].x - geometry.getVertices()[geometry.getVertices().length - 2].x < 0) offsetX = offset * -1;
        else offsetX = offset;
        if (geometry.getVertices()[geometry.getVertices().length - 1].y - geometry.getVertices()[geometry.getVertices().length - 2].y < 0) offsetY = offset * -1;
        else offsetY = offset;
        var posB = new OpenLayers.Geometry.Point(geometry.getVertices()[geometry.getVertices().length - 1].x + offsetX, geometry.getVertices()[geometry.getVertices().length - 1].y + offsetY);
        var labelA = new OpenLayers.Feature.Vector(posA, null, styleA);
        var labelB = new OpenLayers.Feature.Vector(posB, null, styleB);
        this.handler.layer.addFeatures(labelA);
        this.handler.layer.addFeatures(labelB)
    },
    parseRes: function(res, obj) {
        var profs = res.getElementsByTagName("prof:MeasurePoint");
        if (profs.length >= 0) {
            var profPoints = profs[0].getElementsByTagName("prof:Point");
            for (var i = 0, len = profPoints.length; i < len; i++) {
                var profXs = profPoints[i].getElementsByTagName("prof:X");
                var profYs = profPoints[i].getElementsByTagName("prof:Y");
                var profZs = profPoints[i].getElementsByTagName("prof:Z");
                var pos = {
                    x: parseFloat(profXs[0].text),
                    y: parseFloat(profYs[0].text),
                    z: parseFloat(profZs[0].text)
                };
                obj.res.posList.push(pos)
            }
            obj.res.dist = profPoints.length
        }
        obj.res.setResInit();
        obj.events.triggerEvent(obj.EVENT_TYPES[0])
    },
    CLASS_NAME: "GProfile"
});
GPath = OpenLayers.Class(OpenLayers.Handler.Path, {
    attributes: null,
    finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if (cancel || !this.persist) this.destroyFeature()
    },
    mousedown: function(evt) {
        if (this.lastDown && this.lastDown.equals(evt.xy)) return false;
        if (this.lastDown == null) {
            if (this.persist) this.destroyFeature();
            this.createFeature(evt.xy)
        } else if (this.lastUp == null ||
            !this.lastUp.equals(evt.xy)) this.addPoint(evt.xy);
        this.mouseDown = true;
        this.lastDown = evt.xy;
        this.drawing = true;
        this.callback("mousedown", [this.point.geometry, this.getGeometry()]);
        if (evt.button == "2") {
            this.rightclick(evt);
            return true
        }
        return false
    },
    rightclick: function(evt) {
        this.dblclick(evt);
        return false
    },
    mouseup: function(evt) {
        this.mouseDown = false;
        if (this.drawing) {
            if (this.freehandMode(evt)) {
                this.removePoint();
                this.finalize()
            } else {
                if (this.lastUp == null) this.addPoint(evt.xy);
                this.lastUp = evt.xy
            }
            this.callback("mouseup", [this.point.geometry, this.getGeometry()]);
            return false
        }
        if (this.point && this.point.geometry && this.getGeometry()) this.callback("mouseup", [this.point.geometry, this.getGeometry()]);
        return true
    },
    finish: function() {
        var index = this.line.geometry.components.length - 1;
        this.line.geometry.removeComponent(this.line.geometry.components[index]);
        this.removePoint();
        this.finalize();
        return false
    },
    addPoint: function(pixel) {
    	// 객체 추가시, 교차옵션적용[교차,상월,하월]
    	if(GDrawPath.mode && GDrawPath.crossOption !== 'cut'){
    		!GDrawPath.forDivideFeature ? GDrawPath.forDivideFeature = this.line.clone() : GDrawPath.forDivideFeature.geometry.components.push(this.point.geometry.clone());	
    	}    	
    	
    	this.layer.removeFeatures([this.point]);
        var lonlat = this.layer.getLonLatFromViewPortPx(pixel); 
        this.point = new OpenLayers.Feature.Vector(
            new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat)
        );
        this.line.geometry.addComponent(
            this.point.geometry, this.line.geometry.components.length
        );
        this.layer.addFeatures([this.point]);
        this.callback("point", [this.point.geometry, this.getGeometry()]);
        this.callback("modify", [this.point.geometry, this.getSketch()]);
        this.drawFeature();
        delete this.redoStack;
        
        this.replaceStartPoint = function(_nX, _nY){
        	for(var i=0, len=this.line.geometry.components.length; i<len; i++){
				if((this.point.geometry.x === this.line.geometry.components[i].x) && (this.point.geometry.y === this.line.geometry.components[i].y)){
					this.line.geometry.components[i].x = _nX;
					this.line.geometry.components[i].y = _nY;
				}
			}
			this.layer.features[3].geometry.x = _nX;
			this.layer.features[3].geometry.y = _nY;        						
			this.point = this.layer.features[3];
        }

		this.newCoordinates = function(sx, sy, nx, ny, flag){			// 시작점
																		// X좌표,
																		// 시작점
																		// Y좌표,
																		// 다음점
																		// X좌표,
																		// 다음점
																		// Y좌표,
																		// dist
																		// 연산 여부
			var objResult = {};			// 거리와 각을 이용해서 계산한 새로운 좌표점(x,y)
			var nGap = 0.5; 			// 50cm
			var nDist = 0;
			var nAngle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));							// 현재지점과
																								// 다음지점과의
																								// 각
			
			nDist = flag ? COMMON.fn_get_DistanceBy2Point(sx, sy, nx, ny)-nGap : nGap;		// 현재지점과
																							// 다음지점과의
																							// 거리
			objResult.x = sx + nDist * Math.cos(nAngle);
			objResult.y = sy + nDist * Math.sin(nAngle);

			return objResult;
		}

		
        // 객체 추가시, 교차옵션적용[교차, 상월, 하월] : path의 segment마다 교차옵션 적용하여 처리함.
        if(GDrawPath.mode && GDrawPath.crossOption !== 'cut'){
        	var sEditLayer = COMMON.fn_get_editingLayer();
        	if(GDrawPath.forDivideFeature && GDrawPath.forDivideFeature.geometry.components.length > 1){			

        		// intersect판단은 tolerance가 적용된 가상의 지점과 교차여부 판단하도록 처리함.
        		// 교차점에 일정 tolerance를 줘서 이전 입력 point 지점은 교차점 판단 지점에서 제외되도록 처리함.
				// intersect 연산을 위해서만 사용함.
        		var oTmpPointFeatureForCalc = GDrawPath.forDivideFeature.clone();
        		var sx, sy, nx, ny, angle, nDist;

        		sx = oTmpPointFeatureForCalc.geometry.components[0].x;			// 시작점
																				// X좌표
        		sy = oTmpPointFeatureForCalc.geometry.components[0].y;			// 시작점
																				// Y좌표
        		nx = oTmpPointFeatureForCalc.geometry.components[1].x;			// 다음점
																				// X좌표
        		ny = oTmpPointFeatureForCalc.geometry.components[1].y;			// 다음점
																				// Y좌표
        		nDist = GEditRule.tolerance;

        		angle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));					
        		oTmpPointFeatureForCalc.geometry.components[0].x = sx + nDist * Math.cos(angle);
        		oTmpPointFeatureForCalc.geometry.components[0].y = sy + nDist * Math.sin(angle);

        		var oIntersectsGeom = GEditRule.checkRelationGeometry(oTmpPointFeatureForCalc, sEditLayer, GEditRule.spatialOperType.INTERSECTS);
        		if(COMMON.isEmptyObject(oIntersectsGeom) === false && oIntersectsGeom.data.length === 1){
        			if(oIntersectsGeom.data[0].results.length >= 2){
        				// 방금 입력점 삭제 :
						// this.line(=this.layer.feature.geometry.components중
						// 3번째 item이랑 동일)에 동일점이 두번 add되어 있는데 둘다 삭제하지 않고 먼저 add한
						// 점만 삭제해야 하므로
        				var index = this.line.geometry.components.length - 2;
        				this.line.geometry.removeComponent(this.line.geometry.components[index]);
        				this.removePoint();

        				if(this.line.geometry.components.length > 1)
        					this.drawFeature();

        				var index = GDrawPath.forDivideFeature.geometry.components.length - 1;        				
        				OpenLayers.Util.removeItem(GDrawPath.forDivideFeature.geometry.components, GDrawPath.forDivideFeature.geometry.components[index]);        				

        				COMMON.showMessage('편집오류 &  ' + COMMON.fn_get_EditKorLayerNm(sEditLayer) + '를 2개 이상 만나면 처리를 할 수 없습니다.');
        			}
        			else{        				
        				// 입력 feature의 시작점과 intersect된 객체와의 거리가 입력 feature의 끝점보다
						// 가깝고 tolerance보다 작으면, 시작점이 접점(ex. 스냅을 걸었을 경우)에 해당하므로
						// divide 수행하지 않음.
        				var oSPointGeom = GGeoJson.getGeoJson('Point', GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-2]);	    							
        				var oEPointGeom = GGeoJson.getGeoJson('Point', GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-1]);	    							
        				var oCompLineStringGeom = GGeoJson.getGeoJson('LineString', oIntersectsGeom.data[0].results[0].feature.geometry.components);

        				var nSCompDist = GGeomJSTSOper.calcGeomDistance(oCompLineStringGeom, oSPointGeom);
        				var nECompDist = GGeomJSTSOper.calcGeomDistance(oCompLineStringGeom, oEPointGeom);

        				// if(nSCompDist < GEditRule.tolerance)이면, 접점(ex. 스냅을
						// 걸었을 경우)에 해당하는 경우이며 이때는 'do nothing'
        				if((nSCompDist > nECompDist) || (nSCompDist < nECompDist && nSCompDist > GEditRule.tolerance)){
        					switch(GDrawPath.crossOption){
        					case 'cross' : 
        									// #0. 입력선분할
        									var aDividedPosList = [], aDividedFrontPosList;
			        						if(GDrawPath.forDivideFeature.geometry.components.length > 2) 
						        			{
			        							var oTmpCloneCal = GDrawPath.forDivideFeature.geometry.clone();
			        							var oTmpCloneResult = GDrawPath.forDivideFeature.geometry.clone();
			        							for(var i=0, len=oTmpCloneCal.components.length-2; i<len; i++){	// 입력선의
																												// 마지막
																												// segment를
																												// 가지고
																												// 분할
																												// 연산하는데
																												// 사용
			        								OpenLayers.Util.removeItem(oTmpCloneCal.components, oTmpCloneCal.components[0]);
			        							}
			        							aDividedPosList = editor.getDivideLine(oTmpCloneCal.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
			        							
			        							// 분할 결과(aDividedPosList)를
												// 입력선(oTmpCloneResult)에 합치기
			        							var aTmpDividedPosList = [];
			        							for(var j=0, jLen=oTmpCloneResult.components.length-1; j<jLen; j++){	// 마지막
																														// 점은
																														// 분할
																														// 객체 중
																														// 2번째
																														// 분할
																														// 객체의
																														// 끝점에
																														// 해당하므로
																														// 제외시킴
			        								aTmpDividedPosList.push([oTmpCloneResult.components[j].x, oTmpCloneResult.components[j].y]);
			        							}			        		    				
			        							aTmpDividedPosList.push(aDividedPosList[0][aDividedPosList.length-1]);		// 분할
																															// 객체 중
																															// 1번째
																															// 선분의
																															// 끝점
			        							aDividedFrontPosList = aTmpDividedPosList;
					        				}
			        						else{
			        							aDividedPosList = editor.getDivideLine(GDrawPath.forDivideFeature.geometry.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
			        							aDividedFrontPosList = aDividedPosList[0];
			        						}

			        						// #1. divided 객체(입력된 line feature)는
											// 신규 관로로 (편집모니터에) 추가.
			        						var sG2Id = MAP_EDITOR.fn_get_newG2Id();
			        						var oGInnerFeature = editor.makeFeatureByPosList('linestring', aDividedFrontPosList, sEditLayer.concat('.', sG2Id));
			        						editor.addDrawFeature(editor.editLayer, oGInnerFeature, sEditLayer);
			        						MAP_EDITOR.fn_add_featureToEditMonitor(oGInnerFeature, sEditLayer, sG2Id);
			
			        						// #2. this.point 좌표점을 찾아서
											// 분할지점(교차되었던) 좌표점으로 대체
			        						var aDividedFrontObjEndPoint = aDividedFrontPosList[aDividedFrontPosList.length-1];
			        						this.replaceStartPoint(aDividedFrontObjEndPoint[0], aDividedFrontObjEndPoint[1]);
			        						this.drawFeature();
			
			        						// #3. 교차된 기존 시설물(oIntersectsGeom)
											// divide 수행
			        						var oGFeatureForDivideBaseLine, oGInnerFeatureForDivideTarget;
			        						var sFId = MAP_EDITOR.fn_get_fidByFeature(GDrawPath.forDivideFeature);
			        						if(GDrawPath.forDivideFeature.geometry.components.length > 2){
			        							var oTmpCloneCal = MAP_EDITOR.fn_clone_featureToGInnerFeature(GDrawPath.forDivideFeature);			        							
			        							for(var i=0, len=oTmpCloneCal.geometry.components.length-2; i<len; i++){	// 입력선의
																															// 마지막
																															// segment를
																															// 가지고
																															// 분할
																															// 연산하는데
																															// 사용
			        								OpenLayers.Util.removeItem(oTmpCloneCal.geometry.components, oTmpCloneCal.geometry.components[0]);
			        							}			        							
			        							oGFeatureForDivideBaseLine = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(oTmpCloneCal, sFId, oTmpCloneCal.state);
			        						}
			        						else{
			        							oGFeatureForDivideBaseLine = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(GDrawPath.forDivideFeature, sFId, GDrawPath.forDivideFeature.state);				        										        						
			        						}
			        						editor.addUnDrawFeature(editor.refLayer, oGFeatureForDivideBaseLine.feature);
			        						oGInnerFeatureForDivideTarget = oIntersectsGeom.data[0].results[0].feature;
			        						editor.divideLine(oGFeatureForDivideBaseLine, oGInnerFeatureForDivideTarget);
			        						
			        						break;
        					case 'over' :
		        						// #1. 선 분할
		        						var aDividedPosList = editor.getDivideLine(GDrawPath.forDivideFeature.geometry.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
		        						var aDividedFrontPosList = aDividedPosList[0];
		        						var aDividedBackPosList = aDividedPosList[1];
		
		        						// 분할된 첫번째 선의 끝 segment 정보 가져오기
		        						var aFrontPoint1 = aDividedFrontPosList[aDividedFrontPosList.length-2];  		// 끝
																														// segment
																														// 시작점
		        						var aFrontPoint2 = aDividedFrontPosList[aDividedFrontPosList.length-1];		// 끝
																													// segment
																													// 끝점
		
		        						// 분할된 두번째 선의 첫번째 segment 정보 가져오기
		        						var aBackPoint1 = aDividedBackPosList[0];  		// 첫
																						// segment
																						// 시작점
		        						var aBackPoint2 = aDividedBackPosList[1];			// 첫
																							// segment
																							// 끝점
		
		        						// #2. 교차점 찾아서 상월 geometry 생성
		        						var oForDivideLineStringGeom = GGeoJson.getGeoJson('LineString', GDrawPath.forDivideFeature.geometry.components);
		        						var oCrossPoint = GGeomJSTSOper.calcGeomIntersection(oForDivideLineStringGeom, oCompLineStringGeom);
		
		        						if(oCrossPoint.coordinate){
		        							var nAngle = COMMON.fn_get_angleToDegreeByDist((aFrontPoint2[1]-aFrontPoint1[1]), (aFrontPoint2[0]-aFrontPoint1[0]));	// fn_get_angleToDegreeByDist((ny-sy),
																																									// (nx-sx));
		        							var oOpt = {
		        									cx : oCrossPoint.coordinate.x,
		        									cy : oCrossPoint.coordinate.y,
		        									radius : 5,
		        									startAngle : nAngle+180,	// Degree
																				// (생성시킬
																				// arc를
																				// 입력
																				// geometry의
																				// 진행방향으로
																				// poslist를
																				// 생성하기
																				// 위한
																				// start
																				// 각도)
		        									endAngle :  nAngle,		// Degree
		        									segments : 10
		        							};
		        							var oFeatureArc = editor.getArcFeature(new OpenLayers.Geometry.Point(oOpt.cx,oOpt.cy), oOpt.radius, oOpt.startAngle, oOpt.endAngle, oOpt.segments);
		
		        							// 입력선의 마지막 segment의 시작점 - 여기가 교체
											// 시작지점
		        							var oSeg1 = GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-2];
		
		        							// 생성된 상월을 교차점이 존재하는 입력선 segment에
											// merge
		        							for(var i=0, len=this.line.geometry.components.length; i<len; i++){
		        								if((oSeg1.x === this.line.geometry.components[i].x) && (oSeg1.y === this.line.geometry.components[i].y)){
		        									var aCompo1=[], aCompo2=[];
		        									if(len === 3){												// 사용자
																												// 입력선(GDrawPath.forDivideFeature)이
																												// 1개의
																												// segment만
																												// 가지고
																												// 있었을경우,
																												// this.line은
																												// 중복점이
																												// 존재하므로
																												// length=3임
		        										aCompo1.push(this.line.geometry.components[0]);
		        										aCompo2 = this.line.geometry.components.slice(1, this.line.geometry.components.length);
		        									}
		        									else{
		        										aCompo1 = this.line.geometry.components.slice(0,i+1);
		        										aCompo2 = this.line.geometry.components.slice(i+1, this.line.geometry.components.length);
		        									}
		
		        									for(var j=0, jLen=oFeatureArc.geometry.components.length; j<jLen; j++){
		        										aCompo1.push(oFeatureArc.geometry.components[j]);
		        									}
		        									this.line.geometry.components = aCompo1.concat(aCompo2);
		        									
		        									break;
		        								}
		        							}
		        						}
		        						
		        						break;
        					case 'under' : break;
        					}
        					
        					// 초기화
            				var oPointGeomClone = this.point.geometry.clone();
            				GDrawPath.forDivideFeature.geometry.components = [];
            				GDrawPath.forDivideFeature.geometry.components.push(oPointGeomClone);
        				}
        			}
        		}
        	}
        }
    },
    CLASS_NAME: "GPath"
});
GPathMeasure = OpenLayers.Class(OpenLayers.Handler.Path, {
    popup: null,
    movePopup: true,
    partDist: [],
    measureDistance: function(geometry) {
        var subLength = geometry.getLength();
        var tempLength = subLength;
        var unit = "";
        tempLength *= OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT["km"];
        if (tempLength > 1) {
            subLength = tempLength.toFixed(2);
            unit = "km"
        } else {
            subLength = subLength.toFixed(2);
            unit = "m"
        }
        return [subLength, unit]
    },
    measureDistancePart: function() {
        var geometry = this.geometryClone();
        var vertices = geometry.getVertices();
        var points = [new OpenLayers.Geometry.Point(vertices[vertices.length - 2].x, vertices[vertices.length - 2].y), new OpenLayers.Geometry.Point(vertices[vertices.length - 1].x, vertices[vertices.length - 1].y)];
        var lineString = new OpenLayers.Geometry.LineString(points);
        return this.measureDistance(lineString)
    },
    measureDistanceAll: function() {
        var geometry = this.geometryClone();
        return this.measureDistance(geometry)
    },
    mousedown: function(evt) {
        if (this.lastDown && this.lastDown.equals(evt.xy)) return false;
        if (this.lastDown == null) {
            if (!this.multiLine) {
                if (this.persist) this.destroyFeature();
                this.removePopup()
            }
            this.createFeature(evt.xy)
        } else if (this.lastUp == null || !this.lastUp.equals(evt.xy)) this.addPoint(evt.xy);
        this.lastDown = evt.xy;
        this.drawing = true;
        if (this.freehandMode(evt)) {
            this.removePoint();
            this.finalize()
        } else {
            if (this.lastUp == null) this.addPoint(evt.xy);
            this.lastUp = evt.xy
        }
        var lonlat = this.map.getLonLatFromPixel(evt.xy);
        var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
        pointFeature.type = "measure" + GPathMeasureINDEX;
        this.layer.addFeatures(pointFeature);
        var popup;
        if (!this.count) {
            var contentHtml = "<div id='measureStart' class='olControlMeasurePopup olControlMeasurePopupStart'><span class='MeasureColor'>\uc2dc\uc791</span></div>";
            popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
            if (this.movePopup) {
                contentHtml = '<div class="olControlMeasureContent">' + '<div class="measureDist" >' + '<span class="measureResTit">\uc0c1\ub300\uac70\ub9ac</span>' + '<span class="measureResCon"></span>' + '<span class="measureResUnit"></span>' + "</div>" +
                    '<div class="MeasureAllDist" >' + '<span class="measureResTit">\ucd1d\uac70\ub9ac</span>' + '<span class="measureResCon"></span>' + '<span class="measureResUnit"></span>' + "</div>" + '<div class="MeasureEndDescript">\ub9c8\uc6b0\uc2a4 \uc624\ub978\ucabd \ubc84\ud2bc\uc744 \ub204\ub974\uc2dc\uba74 \ub05d\ub9c8\uce69\ub2c8\ub2e4</div>' + "</div>";
                this.popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(15, 5));
                this.map.addPopup(this.popup);
                this.popup.updateSize();
                this.popup.type = "measure" +
                    GPathMeasureINDEX
            }
            this.count = 1
        } else {
            contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupDefault'><span class='MeasureColor'>" + this.partDist[0] + "</span> " + this.partDist[1] + "</div>";
            popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
            this.count += 1
        }
        if (popup) {
            this.map.addPopup(popup);
            popup.type = "measure" + GPathMeasureINDEX;
            popup.updateSize()
        }
        if (evt.button == "2") {
            this.rightclick(evt);
            return true
        }
        return false
    },
    addPoint: function(pixel) {
        this.layer.removeFeatures([this.point]);
        var lonlat = this.control.map.getLonLatFromPixel(pixel);
        this.point = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
        this.line.geometry.addComponent(this.point.geometry, this.line.geometry.components.length);
        this.callback("point", [this.point.geometry, this.getGeometry()]);
        this.callback("modify", [this.point.geometry, this.getSketch()]);
        this.line.type = "measure" + GPathMeasureINDEX;
        this.drawFeature()
    },
    mousemove: function(evt) {
        if (this.drawing)
            if (this.mouseDown && this.freehandMode(evt)) this.addPoint(evt.xy);
            else {
                this.modifyFeature(evt.xy);
                if (this.popup) {
                    var resDist = this.measureDistancePart();
                    var allDist = this.measureDistanceAll();
                    $(this.popup.contentDiv).find(".measureDist .measureResCon").text(resDist[0]);
                    $(this.popup.contentDiv).find(".measureDist .measureResUnit").text(" " + resDist[1]);
                    $(this.popup.contentDiv).find(".MeasureAllDist .measureResCon").text(allDist[0]);
                    $(this.popup.contentDiv).find(".MeasureAllDist .measureResUnit").text(" " + allDist[1]);
                    this.partDist = this.measureDistancePart();
                    this.popup.updateSize();
                    evt.xy.x -= $(this.popup.div).parent().offset().left;
                    evt.xy.y -= $(this.popup.div).parent().offset().top;
                    this.popup.moveTo(evt.xy)
                }
            }
        return true
    },
    rightclick: function(evt) {
        this.dblclick(evt);
        return false
    },
    dblclick: function(evt) {
        if (this.map.popups[this.map.popups.length - 1].type == "measure") this.map.removePopup(this.map.popups[this.map.popups.length - 1]);
        var lonlat = this.map.getLonLatFromPixel(evt.xy);
        var allDist = this.measureDistanceAll();
        var contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupEnd'>\ucd1d\uac70\ub9ac : <span class='MeasureColor'>" +
            allDist[0] + "</span> " + allDist[1] + "</div>";
        var popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
        this.map.addPopup(popup);
        popup.type = "measure" + GPathMeasureINDEX;
        popup.updateSize();
        var gmap = this.layer.map;
        $(".olControlMeasureClose").click(function() {
            var remTyp = $(this).find("input").val();
            var map = gmap;
            var popups = map.popups;
            for (var i = map.layers.length - 1; i >= 0; i--)
                if (map.layers[i].type == "measure")
                    for (var j = map.layers[i].features.length - 1; j >= 0; j--)
                        if (map.layers[i].features[j].type ==
                            remTyp) map.layers[i].removeFeatures(map.layers[i].features[j]);
            for (var i = popups.length - 1; i >= 0; i--)
                if (popups[i].type == remTyp) map.removePopup(popups[i])
        });
        GPathMeasureINDEX++;
        this.count = 0;
        if (!this.freehandMode(evt)) {
            var index = this.line.geometry.components.length - 1;
            this.line.geometry.removeComponent(this.line.geometry.components[index]);
            this.removePoint();
            this.finalize()
        }
        if (this.popup) {
            this.map.removePopup(this.popup);
            this.popup = null
        }
        return false
    },
    activate: function() {
        if (!OpenLayers.Handler.prototype.activate.apply(this,
                arguments)) return false;
        var options = OpenLayers.Util.extend({
            displayInLayerSwitcher: false,
            calculateInRange: OpenLayers.Function.True
        }, this.layerOptions);
        this.layer = new OpenLayers.Layer.Vector(this.CLASS_NAME, options);
        this.layer.type = "measure";
        this.map.addLayer(this.layer);
        return true
    },
    deactivate: function() {
        if (this.drawing) return false;
        if (!OpenLayers.Handler.prototype.deactivate.apply(this, arguments)) return false;
        if (this.drawing) this.cancel();
        if (!this.persistControl) {
            this.layer.destroy(false);
            this.removePopup()
        }
        this.layer =
            null;
        return true
    },
    removePopup: function() {
        var len = this.map.popups.length;
        for (var i = len - 1; i >= 0; i--)
            if (this.map.popups[i].type == "measure") this.map.removePopup(this.map.popups[i])
    },
    CLASS_NAME: "GPathMeasure"
});
GPathMeasureINDEX = 0;
GBox = OpenLayers.Class(OpenLayers.Handler.Box, {
    indexMap: false,
    startBox: function(xy) {
        if (this.indexMap && this.zoomBox) this.removeBox();
        this.zoomBox = OpenLayers.Util.createDiv("zoomBox", this.dragHandler.start);
        this.zoomBox.className = this.boxDivClassName;
        this.zoomBox.style.border = "2px solid #000000";
        this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
        this.map.viewPortDiv.appendChild(this.zoomBox);
        OpenLayers.Element.addClass(this.map.viewPortDiv, "olDrawBox")
    },
    applyBox: function(bounds) {
        if (this.indexMap &&
            this.zoomBox) this.removeBox();
        this.dragHandler.start = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.left, bounds.top));
        var endPixel = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.right, bounds.bottom));
        var width = endPixel.x - this.dragHandler.start.x;
        var height = endPixel.y - this.dragHandler.start.y;
        this.zoomBox = OpenLayers.Util.createDiv("zoomBox", this.dragHandler.start);
        this.zoomBox.className = this.boxDivClassName;
        this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
        this.map.viewPortDiv.appendChild(this.zoomBox);
        this.zoomBox.style.width = width + "px";
        this.zoomBox.style.height = height + "px"
    },
    moveBox: function(xy) {
        var startX = this.dragHandler.start.x;
        var startY = this.dragHandler.start.y;
        var deltaX = Math.abs(startX - xy.x);
        var deltaY = Math.abs(startY - xy.y);
        var offset = this.getBoxOffsets();
        this.zoomBox.style.width = deltaX + offset.width + 1 + "px";
        this.zoomBox.style.height = deltaY + offset.height + 1 + "px";
        this.zoomBox.style.left = (xy.x < startX ? startX - deltaX - offset.left : startX - offset.left) + "px";
        this.zoomBox.style.top = (xy.y < startY ? startY -
            deltaY - offset.top : startY - offset.top) + "px"
    },
    endBox: function(end) {
        var result;
        if (Math.abs(this.dragHandler.start.x - end.x) > 5 || Math.abs(this.dragHandler.start.y - end.y) > 5) {
            var start = this.dragHandler.start;
            var top = Math.min(start.y, end.y);
            var bottom = Math.max(start.y, end.y);
            var left = Math.min(start.x, end.x);
            var right = Math.max(start.x, end.x);
            result = new OpenLayers.Bounds(left, bottom, right, top)
        } else result = this.dragHandler.start.clone();
        if (!this.indexMap) this.removeBox();
        this.callback("done", [result])
    },
    deactivate: function() {
        if (OpenLayers.Handler.prototype.deactivate.apply(this,
                arguments)) {
            if (this.dragHandler && this.dragHandler.deactivate) this.dragHandler.deactivate();
            return true
        } else return false
    },
    CLASS_NAME: "GBox"
});
GPolygonMeasure = OpenLayers.Class(OpenLayers.Handler.Polygon, {
    popup: null,
    measureArea: function() {
        var geometry = this.geometryClone();
        var subLength = geometry.getArea();
        var tempLength = subLength;
        tempLength *= Math.pow(OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT["km"], 2);
        if (tempLength > 1) subLength = tempLength.toFixed(2) + "km" + "<sup>2</" + "sup>";
        else subLength = subLength.toFixed(2) + "m" + "<sup>2</" + "sup>";
        return subLength
    },
    mousedown: function(evt) {
        if (this.lastDown && this.lastDown.equals(evt.xy)) return false;
        if (this.lastDown == null) {
            if (!this.multiLine) {
                if (this.persist) this.destroyFeature();
                this.removePopup()
            }
            this.createFeature(evt.xy)
        } else if (this.lastUp == null || !this.lastUp.equals(evt.xy)) this.addPoint(evt.xy);
        this.lastDown = evt.xy;
        this.drawing = true;
        if (this.freehandMode(evt)) {
            this.removePoint();
            this.finalize()
        } else {
            if (this.lastUp == null) this.addPoint(evt.xy);
            this.lastUp = evt.xy
        }
        var lonlat = this.map.getLonLatFromPixel(evt.xy);
        var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon,
            lonlat.lat));
        this.layer.addFeatures(pointFeature);
        var popup;
        if (!this.count) {
            var contentHtml = "<div id='measureStart' class='olControlMeasurePopup olControlMeasurePopupStart'><span class='MeasureColor'>\uc2dc\uc791</span></div>";
            popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
            contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupMovePoly'>\ucd1d\uba74\uc801 : <span class='MeasureColor'>" + this.measureArea() + "</span></div>";
            this.popup = new GPopup("measurePopup",
                lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
            this.map.addPopup(this.popup);
            this.popup.updateSize();
            this.popup.type = "measure";
            this.count = 1
        } else {
            if (this.count > 1) {
                contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupDefaultPoly'><span class='MeasureColor'>" + this.measureArea() + "</span></div>";
                popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5))
            }
            this.count += 1
        }
        if (popup) {
            this.map.addPopup(popup);
            popup.type = "measure";
            popup.updateSize()
        }
        if (evt.button == "2") {
            this.rightclick(evt);
            return true
        }
        return false
    },
    mousemove: function(evt) {
        if (this.drawing)
            if (this.mouseDown && this.freehandMode(evt)) this.addPoint(evt.xy);
            else {
                this.modifyFeature(evt.xy);
                if (this.popup) {
                    var contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupMovePoly' >\ucd1d\uba74\uc801 : <span class='MeasureColor'>" + this.measureArea() + "</span></div>";
                    this.popup.setContentHTML(contentHtml);
                    this.popup.updateSize();
                    this.popup.moveTo(evt.xy)
                }
            }
        return true
    },
    rightclick: function(evt) {
        this.dblclick(evt);
        return false
    },
    dblclick: function(evt) {
        if (this.count < 3) {
            alert("\uba74\uc801\uc740 3\uac1c \uc774\uc0c1\uc758 \uc9c0\uc810\uc744 \uc120\ud0dd\ud574\uc57c \ud569\ub2c8\ub2e4.");
            return false
        }
        if (this.map.popups[this.map.popups.length - 1].type == "measure") this.map.removePopup(this.map.popups[this.map.popups.length - 1]);
        var lonlat = this.map.getLonLatFromPixel(evt.xy);
        var contentHtml = "<div class='olControlMeasurePopup olControlMeasurePopupMovePoly' >\ucd1d\uba74\uc801 : <span class='MeasureColor'>" + this.measureArea() + "</span></div>";
        var popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
        this.map.addPopup(popup);
        popup.type = "measure";
        popup.updateSize();
        this.count = 0;
        if (!this.freehandMode(evt)) {
            var index = this.line.geometry.components.length - 1;
            this.line.geometry.removeComponent(this.line.geometry.components[index]);
            this.removePoint();
            this.finalize()
        }
        if (this.popup) {
            this.map.removePopup(this.popup);
            this.popup = null
        }
        return false
    },
    deactivate: function() {
        if (!OpenLayers.Handler.prototype.deactivate.apply(this,
                arguments)) return false;
        if (this.drawing) this.cancel();
        if (!this.persistControl) {
            this.layer.destroy(false);
            this.removePopup()
        }
        this.layer.prevLayer = true;
        this.layer = null;
        return true
    },
    removePopup: function() {
        var len = this.map.popups.length;
        for (var i = len - 1; i >= 0; i--)
            if (this.map.popups[i].type == "measure") this.map.removePopup(this.map.popups[i])
    },
    CLASS_NAME: "GPolygonMeasure"
});
GPoint = OpenLayers.Class(OpenLayers.Handler.Point, {
    attributes: null,
    finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if (cancel || !this.persist) this.destroyFeature()
    },
    CLASS_NAME: "GPoint"
});
GPolygon = OpenLayers.Class(OpenLayers.Handler.Polygon, {
    attributes: null,
    finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if (cancel || !this.persist) this.destroyFeature()
    },
    mousedown: function(evt) {
        if (this.lastDown && this.lastDown.equals(evt.xy)) return false;
        if (this.lastDown == null) {
            if (this.persist) this.destroyFeature();
            this.createFeature(evt.xy)
        } else if (this.lastUp ==
            null || !this.lastUp.equals(evt.xy)) this.addPoint(evt.xy);
        this.mouseDown = true;
        this.lastDown = evt.xy;
        this.drawing = true;
        this.callback("mousedown", [this.point.geometry, this.getGeometry()]);
        return false
    },
    mouseup: function(evt) {
        this.mouseDown = false;
        if (this.drawing) {
            if (this.freehandMode(evt)) {
                this.removePoint();
                this.finalize()
            } else {
                if (this.lastUp == null) this.addPoint(evt.xy);
                this.lastUp = evt.xy
            }
            this.callback("mouseup", [this.point.geometry, this.getGeometry()]);
            return false
        }
        this.callback("mouseup", [this.point.geometry,
            this.getGeometry()
        ]);
        return true
    },
    CLASS_NAME: "GPolygon"
});
GPolygonDraw = OpenLayers.Class(OpenLayers.Handler.Polygon, {
    attributes: null,
    finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if (cancel || !this.persist) this.destroyFeature()
    },
    CLASS_NAME: "GPolygonDraw"
});
GRegularPolygonDraw = OpenLayers.Class(OpenLayers.Handler.RegularPolygon, {
    attributes: null,
    callback: function(name, args) {
        if (this.callbacks[name]) this.callbacks[name].apply(this.control, [this.feature.geometry.clone(), this.attributes]);
        if (!this.persist && (name == "done" || name == "cancel")) this.clear()
    },
    CLASS_NAME: "GRegularPolygonDraw"
});
GRegularPolygonDrawAttr = OpenLayers.Class(OpenLayers.Handler.RegularPolygon, {
    attributes: null,
    dragStartX: 0,
    dragStartY: 0,
    down: function(evt) {
        this.fixedRadius = !!this.radius;
        var maploc = this.map.getLonLatFromPixel(evt.xy);
        this.origin = new OpenLayers.Geometry.Point(maploc.lon, maploc.lat);
        if (!this.fixedRadius || this.irregular) this.radius = this.map.getResolution();
        if (this.persist) this.clear();
        this.feature = new OpenLayers.Feature.Vector;
        this.createGeometry();
        this.callback("create", [this.origin, this.feature]);
        this.layer.addFeatures([this.feature], {
            silent: true
        });
        this.layer.drawFeature(this.feature, this.style);
        var lonlat = this.map.getLonLatFromPixel(evt.xy);
        this.dragStartX = lonlat.lon;
        this.dragStartY = lonlat.lat;
        var popup;
        var contentHtml = "<div class='olControlMeasurePopup olControlCircleAttr'><span class='MeasureColor'>0.0m</span></div>";
        this.popup = new GPopup("measurePopup", lonlat, null, contentHtml, new OpenLayers.Pixel(5, 5));
        this.map.addPopup(this.popup);
        this.popup.updateSize();
        this.popup.type = "attrCircle"
    },
    move: function(evt) {
        var maploc = this.map.getLonLatFromPixel(evt.xy);
        var point = new OpenLayers.Geometry.Point(maploc.lon, maploc.lat);
        if (this.irregular) {
            var ry = Math.sqrt(2) * Math.abs(point.y - this.origin.y) / 2;
            this.radius = Math.max(this.map.getResolution() / 2, ry)
        } else if (this.fixedRadius) this.origin = point;
        else {
            this.calculateAngle(point, evt);
            this.radius = Math.max(this.map.getResolution() / 2, point.distanceTo(this.origin))
        }
        this.modifyGeometry();
        if (this.irregular) {
            var dx = point.x - this.origin.x;
            var dy = point.y - this.origin.y;
            var ratio;
            if (dy == 0) ratio = dx / (this.radius * Math.sqrt(2));
            else ratio =
                dx / dy;
            this.feature.geometry.resize(1, this.origin, ratio);
            this.feature.geometry.move(dx / 2, dy / 2)
        }
        this.layer.drawFeature(this.feature, this.style);
        if (this.popup) {
            var lonlat = this.map.getLonLatFromPixel(evt.xy);
            var dragEndX = Math.abs(lonlat.lon);
            var dragEndY = Math.abs(lonlat.lat);
            this.radiusDist = GUtil.fn_fmt_fix(Math.sqrt(Math.pow(dragEndX - this.dragStartX, 2) + Math.pow(dragEndY - this.dragStartY, 2)), 1);
            contentHtml = "<div class='olControlMeasurePopup olControlCircleAttr'><span class='MeasureColor'>" + this.measureDistance(this.radiusDist) +
                "</span></div>";
            this.popup.setContentHTML(contentHtml);
            this.popup.updateSize();
            this.popup.moveTo(evt.xy)
        }
    },
    measureDistance: function(subLength) {
        var tempLength = subLength;
        tempLength *= OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT["km"];
        if (tempLength > 1) subLength = tempLength.toFixed(2) + "km";
        else subLength = subLength.toFixed(2) + "m";
        return subLength
    },
    up: function(evt) {
        this.finalize();
        if (this.start == this.last) this.callback("done", [evt.xy])
    },
    callback: function(name, args) {
        if (this.callbacks[name]) this.callbacks[name].apply(this.control, [this.feature.geometry.clone(), this.attributes]);
        if (!this.persist && (name == "done" || name == "cancel")) this.clear()
    },
    CLASS_NAME: "GRegularPolygonDrawAttr"
});
GPopup = OpenLayers.Class(OpenLayers.Popup, {
    offsetPixel: null,
    initialize: function(id, lonlat, contentSize, contentHTML, offsetPixel) {
        if (offsetPixel) this.offsetPixel = offsetPixel;
        if (id == null) id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
        this.id = id;
        this.lonlat = lonlat;
        this.contentSize = contentSize != null ? contentSize : new OpenLayers.Size(OpenLayers.Popup.WIDTH, OpenLayers.Popup.HEIGHT);
        if (contentHTML != null) this.contentHTML = contentHTML;
        this.opacity = OpenLayers.Popup.OPACITY;
        this.border = OpenLayers.Popup.BORDER;
        this.div = OpenLayers.Util.createDiv(this.id, null, null, null, null, null, "hidden");
        this.div.className = this.displayClass;
        var groupDivId = this.id + "_GroupDiv";
        this.groupDiv = OpenLayers.Util.createDiv(groupDivId, null, null, null, "relative", null, "hidden");
        var id = this.div.id + "_contentDiv";
        this.contentDiv = OpenLayers.Util.createDiv(id, null, this.contentSize.clone(), null, "relative");
        this.contentDiv.className = this.contentDisplayClass;
        this.groupDiv.appendChild(this.contentDiv);
        this.div.appendChild(this.groupDiv);
        this.registerEvents()
    },
    getLonLat: function() {
        return this.lonlat
    },
    moveTo: function(px) {
        if (px != null && this.div != null) {
            if (this.offsetPixel) px = px.add(this.offsetPixel.x, this.offsetPixel.y);
            this.div.style.left = px.x + "px";
            this.div.style.top = px.y + "px"
        }
    },
    CLASS_NAME: "GPopup"
});

GDrawTool = OpenLayers.Class({
    map: null,
    layer: null,
    controls: null,
    defaultStyle : {
		'Point' : {
			featureType : "Point",
			pointRadius : 6,
			graphicName: "circle",
			fillColor : "#C4F500",
			fillOpacity : 0.5,
			strokeWidth : 1,
			strokeOpacity : 1,
			strokeColor : "#94E619"
		},
		
		'Line' : {
			featureType : "Line",
			strokeColor : "#C4F500",
			strokeWidth : 2,
			strokeOpacity : 1,
			strokeDashstyle : "solid",
			strokeLinecap : "butt"
		},
		
		'Polygon' : {
			featureType : "Polygon",
			fillColor : "#C4F500",
			fillOpacity : 0.4,
			strokeColor : "#94E619",
			strokeWidth : 2,
			strokeOpacity : 1,
			strokeDashstyle : "solid"
		},
		
		'Image' : {
			featureType : "Image",
			graphicOpacity : 1,
			externalGraphic : "/images/usolver/com/map/marker/edit.png",
			graphicWidth : 32,
			graphicHeight :  32
		},
		
		'Text' : {
			featureType : "Text",
			pointRadius: 4,
            graphicName: "square",
            fillColor: "white",
            fillOpacity: 1,
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeColor: "#245282"
		}
	},
    initialize: function(map, options) {
    	console.log("GDrawTool에서 초기화");
        var controls = this;
        this.map = map;
        this.layer = new GVector("GDrawToolLayer", {
            styleMap: this.getStyleMap(),
            eventListeners: options.eventListeners
        });
        OpenLayers.Util.extend(this, options);
        map.addLayer(this.layer);
        this.controls = [new OpenLayers.Control.SelectFeature(this.layer, {
                id: "drawSelect",
                clickout: true,
                toggleKey: "ctrlKey",
                multipleKey: "shiftKey",
                box: true
            }), new OpenLayers.Control.ModifyFeature(this.layer, {
                id: "drawEdit",
                mode: OpenLayers.Control.ModifyFeature.RESHAPE | OpenLayers.Control.ModifyFeature.DRAG
            }), new GDrawFeature(this.layer, GPoint, {
                id: "drawPoint",
                handlerOptions: {
                    attributes: this.defaultStyle["Point"]
                }
            }), new GDrawFeature(this.layer, GPoint, {
                id: "drawSymbol",
                handlerOptions: {
                    attributes: this.defaultStyle["Image"]
                }
            }), 
            new GDrawFeature(this.layer, GPath, {
                id: "drawLine",
                handlerOptions: {
                    attributes: this.defaultStyle["Line"]
                }
            }), new GDrawFeature(this.layer, GRegularPolygonDraw, {
                id: "drawRect",
                handlerOptions: {
                    irregular: true,
                    attributes: this.defaultStyle["Polygon"]
                }
            }), new GDrawFeature(this.layer, GRegularPolygonDraw, {
                id: "drawCircle",
                handlerOptions: {
                    sides: 50,
                    attributes: this.defaultStyle["Polygon"]
                }
            }), new GDrawFeature(this.layer, GRegularPolygonDraw, {
                id: "drawEllipse",
                handlerOptions: {
                    sides: 50,
                    irregular: true,
                    attributes: this.defaultStyle["Polygon"]
                }
            }),
            new GDrawFeature(this.layer, GPolygonDraw, {
                id: "drawPolygon",
                handlerOptions: {
                    attributes: this.defaultStyle["Polygon"]
                }
            }), new GDrawFeature(this.layer, GPoint, {
                id: "drawText",
                handlerOptions: {
                    attributes: this.defaultStyle["Text"]
                }
            })
        ];
        map.addControls(this.controls);
        if (options) {
            if (options.onFeatureAdded) 
            	this.layer.events.register("featureadded", this, options.onFeatureAdded);
            if (options.onModificationStart) 
            	map.getControl("drawEdit").onModificationStart = options.onModificationStart;
            if (options.onSelect) 
            	map.getControl("drawSelect").onSelect = options.onSelect;
            if (options.onDeactivate) 
            	map.getControl("drawEdit").onDeactivate = options.onDeactivate;
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
    	console.log("GDrawTool에서 Feature삭제");
        this.removeTextPopup();
        var features = [];
        if (this.layer.selectedFeatures.length > 0)
            for (var i in this.layer.selectedFeatures) 
            	features.push(this.layer.selectedFeatures[i]);
        for (var i in this.map.controls)
            if (this.map.getControl("drawSelect") && this.map.getControl("drawSelect").active) 
            	this.map.activeControls("drawSelect");
            else if (this.map.getControl("drawEdit") && this.map.getControl("drawEdit").active) 
            	this.map.activeControls("drawEdit");
        else if (this.map.getControl("drawEditPoint") && this.map.getControl("drawEditPoint").active) 
        	this.map.activeControls("drawEditPoint");
        if (features.length > 0) 
        	this.layer.removeFeatures(features)
    },
    removeTextPopup: function() {
    	console.log("GDrawTool에서 textPopup제거");
        var id;
        $(".olControlDrawText").each(function() {
            if ($(this).hasClass("on")) 
            	id = $(this).parent().parent().parent().attr("id")
        });
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].id == id) 
            	this.map.removePopup(this.map.popups[i])
    },
    getSelectFeature: function() {
    	console.log("GDrawTool에서 선택된feature 얻기");
        var id;
        if (this.map.getLayerByName("GDrawToolLayer") && this.map.getLayerByName("GDrawToolLayer").selectedFeatures 
        		&& this.map.getLayerByName("GDrawToolLayer").selectedFeatures.length > 0) 
        	return this.map.getLayerByName("GDrawToolLayer").selectedFeatures[0];
        $(".olControlDrawText").each(function() {
            if ($(this).hasClass("on")) id = $(this).attr("id")
        });
        for (var i in this.map.popups)
            if (this.map.popups[i].attributes.seq == id.replace("drawText", "")) 
            	return this.map.popups[i];
        return false
    },
//yj. 편집완료한 글자그리기 적용    
    setTextAttr: function(feature) {
        var seq = feature.attributes.seq;
        console.log("요기 도착!! " +feature.attributes.background_fill +"\t" +feature.attributes.background_line);
        
        $("#drawText" + seq).css("font-family", feature.attributes.fontFamily);
        $("#drawText" + seq).css("font-size", feature.attributes.fontSize);
        $("#drawText" + seq).css("color", feature.attributes.fontColor);
        $("#drawText" + seq).css("background", feature.attributes.background_fill);
        $("#drawText" + seq).css("border", "1px solid " +feature.attributes.background_line);
        feature.updateSize()
    },
    addTextPopup: function(popup) {
    	console.log("GDrawTool에서 textPopup 추가");
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
    selectTextPopup: function(element) {
        var active = $(element).hasClass("on");
        this.unselectAll();
        console.log("GDrawTool에서 텍스트팝업 선택되었네");
//        this.selectControl.unselectAll();
        if (!active) {
            $(element).removeClass("off");
            $(element).addClass("on");
            var popup;
            for (var i in this.map.popups)
                if (this.map.popups[i].attributes.seq == $(element).attr("id").replace("drawText", "")) popup = this.map.popups[i];
            popup.attributes.fontFamily = $(element).css("font-family");
            popup.attributes.fontSize = $(element).css("font-size");
            popup.attributes.fontColor = $(element).css("color");
            this.onModificationStart(popup)
        }
    },
    removeAllFeatures: function() {
    	console.log("GDrawTool에서 모두 지우자");
        this.layer.removeAllFeatures();
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].type == "draw") this.map.removePopup(this.map.popups[i])
    },
    redraw: function() {
        this.layer.redraw()
    },
//yj. 임시로 추가한 부분    
    onUnselectAll: function() {},
    unselectAll: function(options) {
        var layers = this.layers || [this.layer];
        var layer, feature;
/*        for (var l = 0; l < layers.length; ++l) {
            layer = layers[l];
            for (var i = layer.selectedFeatures.length - 1; i >= 0; --i) {
                feature = layer.selectedFeatures[i];
                if (!options || options.except != feature) this.unselect(feature)
            }
        }*/
        $(".olControlDrawText").each(function() {
            $(this).removeClass("on");
            $(this).addClass("off")
        });
        this.onUnselectAll()
    },
    CLASS_NAME: "GDrawTool"
});

GSearchSpaceTool = OpenLayers.Class({
    map: null,
    layer: null,
    controls: null,
    defaultStyle: {
        "Point": {
            featureType: "Point",
            pointRadius: 6,
            graphicName: "cross",
            fillColor: "#CFF207",
            fillOpacity: 1,
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeColor: "#41B020"
        },
        "Line": {
            featureType: "Line",
            strokeColor: "#41B020",
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeDashstyle: "dash",
            strokeLinecap: "butt"
        },
        "Polygon": {
            featureType: "Polygon",
            fillColor: "#CFF207",
            fillOpacity: 0.2,
            strokeColor: "#41B020",
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeDashstyle: "dash"
        }
    },
    initialize: function(map, options) {
        var control = this;
        this.map = map;
        this.layer = new GVector("searchAreaLayer", {
        	searchCondition: {},
            styleMap: this.getStyleMap(),
            eventListeners: options.eventListeners
        });
        OpenLayers.Util.extend(this, options);
        map.addLayer(this.layer);
        this.controls = [new OpenLayers.Control.SelectFeature(this.layer, {
                id: "searchSelect",
                clickout: true,
                toggleKey: "ctrlKey",
                multipleKey: "shiftKey",
                box: true
            }), new GDrawFeature(this.layer, GPoint, {
                id: "searchPoint",
                handlerOptions: {
                    attributes: this.defaultStyle["Point"]
                }
            }), new GDrawFeature(this.layer, GRegularPolygonDraw, {
                id: "searchRect",
                handlerOptions: {
                	irregular: true,
                    attributes: this.defaultStyle["Polygon"]
                }
            }), new GDrawFeature(this.layer, GPolygonDraw, {
                id: "searchPolygon",
                handlerOptions: {
                    attributes: this.defaultStyle["Polygon"]
                }
            }), new GDrawFeature(this.layer, GPath, {
                id: "searchLine",
                handlerOptions: {
                    attributes: this.defaultStyle["Line"]
                }
            }), new GDrawFeature(this.layer, GRegularPolygonDraw, {
                id: "searchCircle",
                handlerOptions: {
                	 sides: 50,
                    attributes: this.defaultStyle["Polygon"]
                }
            })
        ];
        map.addControls(this.controls);
        if (options) {
            if (options.onFeatureAdded) this.layer.events.register("featureadded", this, options.onFeatureAdded);
            if (options.onSelect) map.getControl("searchSelect").onSelect = 
                options.onSelect;
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
            "default": style,
        });
        return styleMap
    },
    /*
	 * deleteFeature: function() { this.removeTextPopup(); var features = []; if
	 * (this.layer.selectedFeatures.length > 0) for (var i in
	 * this.layer.selectedFeatures)
	 * features.push(this.layer.selectedFeatures[i]); for (var i in
	 * this.map.controls) if (this.map.getControl("drawSelect") &&
	 * this.map.getControl("drawSelect").active)
	 * this.map.activeControls("drawSelect"); else if
	 * (this.map.getControl("drawEdit") &&
	 * this.map.getControl("drawEdit").active)
	 * this.map.activeControls("drawEdit"); else if
	 * (this.map.getControl("drawEditPoint") &&
	 * this.map.getControl("drawEditPoint").active)
	 * this.map.activeControls("drawEditPoint"); if (features.length > 0)
	 * this.layer.removeFeatures(features) },
	 */
    /*
	 * removeTextPopup: function() { var id;
	 * $(".olControlDrawText").each(function() { if ($(this).hasClass("on")) id =
	 * $(this).parent().parent().parent().attr("id") }); for (var i =
	 * this.map.popups.length - 1; i >= 0; i--) if (this.map.popups[i].id == id)
	 * this.map.removePopup(this.map.popups[i]) },
	 */
    /*
	 * getSelectFeature: function() { var id; if
	 * (this.map.getLayerByName("GDrawToolLayer") &&
	 * this.map.getLayerByName("GDrawToolLayer").selectedFeatures &&
	 * this.map.getLayerByName("GDrawToolLayer").selectedFeatures.length > 0)
	 * return this.map.getLayerByName("GDrawToolLayer").selectedFeatures[0];
	 * $(".olControlDrawText").each(function() { if ($(this).hasClass("on")) id =
	 * $(this).attr("id") }); for (var i in this.map.popups) if
	 * (this.map.popups[i].attributes.seq == id.replace("drawText", "")) return
	 * this.map.popups[i]; return false },
	 */
    /*
	 * setTextAttr: function(feature) { var seq = feature.attributes.seq;
	 * $("#drawText" + seq).css("font-family", feature.attributes.fontFamily);
	 * $("#drawText" + seq).css("font-size", feature.attributes.fontSize);
	 * $("#drawText" + seq).css("color", feature.attributes.fontColor);
	 * feature.updateSize() }, addTextPopup: function(popup) { var seq =
	 * popup.attributes.seq; var str = popup.attributes.text; str =
	 * str.replace(/\x20/gi, "&nbsp;"); str = str.replace(/\x0D\x0A/gi, "<br/>");
	 * str = str.replace(/\x0D/gi, "<br/>"); str = str.replace(/\n/gi, "<br/>");
	 * var contentHtml = ""; contentHtml += "<div class='olControlDrawText off'
	 * id='drawText" + seq + "'>" + str + "</div>"; var pop = new
	 * GPopup("drawPopup" + seq, popup.getLonLat(), null, contentHtml, new
	 * OpenLayers.Pixel(0, 0)); this.map.addPopup(pop); pop.type = "draw";
	 * pop.attributes = popup.attributes; this.setTextAttr(pop) },
	 */
    removeAllFeatures: function() {
        this.layer.removeAllFeatures();
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].type == "draw") this.map.removePopup(this.map.popups[i])
    },
    redraw: function() {
        this.layer.redraw()
    },
    CLASS_NAME: "GSearchSpaceTool"
});

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
            externalGraphic: "http://" + $(location).attr("host") + "/images/usolver/com/map/marker/edit.png",// "https://cdn2.iconfinder.com/data/icons/snipicons/500/edit-48.png",
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
            }),/*
				 * new GDrawFeature(this.layer, GPoint, { id: "drawPoint",
				 * handlerOptions: { attributes: this.defaultStyle["Point"] }
				 * }),
				 */ 
            new GDrawFeature(this.layer, GPoint, {
                id: "drawMemo",
                handlerOptions: {
                    attributes: this.defaultStyle["Image"]
                }
            })/*
				 * , new GDrawFeature(this.layer, GPath, { id: "drawLine",
				 * handlerOptions: { attributes: this.defaultStyle["Line"] } }),
				 * new GDrawFeature(this.layer, GRegularPolygonDraw, { id:
				 * "drawRect", handlerOptions: { irregular: true, attributes:
				 * this.defaultStyle["Polygon"] } }), new
				 * GDrawFeature(this.layer, GRegularPolygonDraw, { id:
				 * "drawCircle", handlerOptions: { sides: 50, irregular: true,
				 * attributes: this.defaultStyle["Polygon"] } }), new
				 * GDrawFeature(this.layer, GRegularPolygonDraw, { id:
				 * "drawEllipse", handlerOptions: { sides: 50, attributes:
				 * this.defaultStyle["Polygon"] } }), new
				 * GDrawFeature(this.layer, GPolygonDraw, { id: "drawPolygon",
				 * handlerOptions: { attributes: this.defaultStyle["Polygon"] }
				 * }), new GDrawFeature(this.layer, GPoint, { id: "drawText",
				 * handlerOptions: { attributes: this.defaultStyle["Text"] } })
				 */
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
            if ($(this).hasClass("on")) 
            	id = $(this).parent().parent().parent().attr("id")
        });
        for (var i = this.map.popups.length - 1; i >= 0; i--)
            if (this.map.popups[i].id == id) 
            	this.map.removePopup(this.map.popups[i])
    },
    getSelectFeature: function() {
        var id;
        if (this.map.getLayerByName("GMemoToolLayer") && this.map.getLayerByName("GMemoToolLayer").selectedFeatures 
        		&& this.map.getLayerByName("GMemoToolLayer").selectedFeatures.length > 0) 
        	return this.map.getLayerByName("GMemoToolLayer").selectedFeatures[0];
        $(".olControlDrawText").each(function() {
            if ($(this).hasClass("on")) 
            	id = $(this).attr("id")
        });
        for (var i in this.map.popups)
            if (this.map.popups[i].attributes.seq == id.replace("drawText", "")) 
            	return this.map.popups[i];
        return false
    },
    setTextAttr: function(feature) {
        var seq = feature.attributes.seq;
        $("#drawText" + seq).css("font-family", feature.attributes.fontFamily);
        $("#drawText" + seq).css("font-size", feature.attributes.fontSize);
        $("#drawText" + seq).css("color", feature.attributes.fontColor);
//        $("#drawText" + seq).css("background", feature.attributes.background_fill);
//        $("#drawText" + seq).css("border", "1px solid " +feature.attributes.background_line);
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
GSaveTool = OpenLayers.Class({
    map: null,
    xml: null,
    style: null,
    initialize: function(map) {
        this.map = map
    },
    getXML: function(mashupLayer) {
        this.xml = "<LAYERS>";
        this.parseMap();
        this.parseLayer();
        this.parseVector();
        this.parsePopup();
        this.parseMashupLayer(mashupLayer);
        this.xml += "</LAYERS>";
        return this.xml
    },
    parseMap: function() {
        this.xml += "<MAP>";
        var params = {
            left: this.map.getExtent().left,
            bottom: this.map.getExtent().bottom,
            right: this.map.getExtent().right,
            top: this.map.getExtent().top,
            width: this.map.getSize().w,
            height: this.map.getSize().h,
            resolution: this.map.getResolution()
        };
        this.write(params);
        this.xml += "</MAP>"
    },
    parseLayer: function() {
        for (var i = 0; i < this.map.layers.length; i++)
            if ((this.map.layers[i].CLASS_NAME == "GWMS" || this.map.layers[i].CLASS_NAME == "GWMSPost") && this.map.layers[i].visibility) {
                this.xml += '<LAYER type="wms">';
                var params = {
                    url: this.map.layers[i].url,
                    layers: this.map.layers[i].params.LAYERS,
                    styles: this.map.layers[i].params.STYLES,
                    format: this.map.layers[i].params.FORMAT,
                    version: this.map.layers[i].params.VERSION,
                    crs: this.map.layers[i].params.CRS,
                    service: this.map.layers[i].params.SERVICE,
                    request: this.map.layers[i].params.REQUEST,
                    exceptions: this.map.layers[i].params.EXCEPTIONS
                };
                if (this.map.layers[i].params.SLD_BODY) params.sldbody = this.map.layers[i].params.SLD_BODY;
                this.write(params);
                this.xml += "</LAYER>"
            } else if (this.map.layers[i].CLASS_NAME == "GTileCache" && this.map.layers[i].visibility) {
            this.xml += '<LAYER type="tilecache">';
            params = {
                url: this.map.layers[i].url,
                layername: this.map.layers[i].layername,
                scaleLevel: this.map.getZoom() + 2,
                maxLeft: this.map.layers[i].maxExtent.left,
                maxBottom: this.map.layers[i].maxExtent.bottom,
                maxRight: this.map.layers[i].maxExtent.right,
                maxTop: this.map.layers[i].maxExtent.top,
                extension: "." + this.map.layers[i].format.split("/")[1].toLowerCase()
            };
            this.write(params);
            this.xml += "</LAYER>"
        } else if (this.map.layers[i].CLASS_NAME == "OpenLayers.Layer.ArcGISCache" && this.map.layers[i].visibility) {
            this.xml += '<LAYER type="ArcGISCache">';
            var res = this.map.layers[i].getResolution();
            var start = this.map.layers[i].getUpperLeftTileCoord(res);
            var end = this.map.layers[i].getLowerRightTileCoord(res);
            var numTileCols =
                end.x - start.x + 1;
            var numTileRows = end.y - start.y + 1;
            params = {
                url: this.map.layers[i].url,
                tileOriginLon: this.map.layers[i].tileOrigin.lon,
                tileOriginLat: this.map.layers[i].tileOrigin.lat,
                minRows: numTileRows,
                minCols: numTileCols,
                centerX: this.map.getExtent().getCenterLonLat().lon,
                centerY: this.map.getExtent().getCenterLonLat().lat,
                scaleLevel: this.map.getZoom(),
                maxLeft: this.map.layers[i].maxExtent.left,
                maxBottom: this.map.layers[i].maxExtent.bottom,
                maxRight: this.map.layers[i].maxExtent.right,
                maxTop: this.map.layers[i].maxExtent.top
            };
            this.write(params);
            this.xml += "</LAYER>"
        }
    },
    parseVector: function() {
        for (var i = 0; i < this.map.layers.length; i++)
            if (this.map.layers[i].CLASS_NAME == "GVector" && this.map.layers[i].visibility) {
                this.xml += "<VECTORLAYER>";
                for (var j = 0; j < this.map.layers[i].features.length; j++)
                    if (this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
                        // if
						// (!this.map.layers[i].features[j].attributes.featureType)
						// continue;
                        var params;
                        var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);
                        if (/*
							 * this.map.layers[i].features[j].attributes.featureType ==
							 * "Image"
							 */style.externalGraphic) params = {
                            x: this.map.layers[i].features[j].geometry.x,
                            y: this.map.layers[i].features[j].geometry.y,
                            featureType: /* this.map.layers[i].features[j].attributes.featureType */"Image",
                            opacity: style./* graphicOpacity */fillOpacity,
                            width: style.graphicWidth,
                            height: style.graphicHeight,
                            image: style.externalGraphic
                        };
                        else if (/*
									 * this.map.layers[i].features[j].attributes.featureType ==
									 * "Point"
									 */style.graphicName) {
                            if (!(style.graphicName == "circle" || style.graphicName == "square")) style.graphicName == "circle";
                            params = {
                                x: this.map.layers[i].features[j].geometry.x,
                                y: this.map.layers[i].features[j].geometry.y,
                                featureType: /* this.map.layers[i].features[j].attributes.featureType */"Point",
                                radius: style.pointRadius,
                                graphicName: style.graphicName,
                                stroke: style.strokeWidth,
                                color: style.strokeColor,
                                opacity: style.strokeOpacity,
                                fillColor: style.fillColor,
                                fillOpacity: style.fillOpacity
                            }
                        }
                        this.xml += '<FEATURE type="point">';
                        this.write(params);
                        this.xml += "</FEATURE>"
                    } else if (this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
                    var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);
                    if (this.map.layers[i].features[j].attributes.featureType ==
                        "arrow") this.xml += '<FEATURE type="arrow">';
                    else this.xml += '<FEATURE type="lineString">';
                    var x = [];
                    var y = [];
                    for (var k = 0; k < this.map.layers[i].features[j].geometry.components.length; k++) {
                        x.push(this.map.layers[i].features[j].geometry.components[k].x);
                        y.push(this.map.layers[i].features[j].geometry.components[k].y)
                    }
                    var params = {
                        x: x,
                        y: y,
                        color: style.strokeColor,
                        opacity: style.strokeOpacity,
                        stroke: style.strokeWidth,
                        strokeDashstyle: style.strokeDashstyle,
                        strokeLinecap: style.strokeLinecap
                    };
                    this.write(params);
                    this.xml +=
                        "</FEATURE>"
                } else if (this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.Polygon") {
                    var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);
                    this.xml += '<FEATURE type="polygon">';
                    var x = [];
                    var y = [];
                    for (var k = 0; k < this.map.layers[i].features[j].geometry.components[0].components.length; k++) {
                        x.push(this.map.layers[i].features[j].geometry.components[0].components[k].x);
                        y.push(this.map.layers[i].features[j].geometry.components[0].components[k].y)
                    }
                    var params = {
                        x: x,
                        y: y,
                        color: style.strokeColor,
                        opacity: style.strokeOpacity,
                        stroke: style.strokeWidth,
                        fillColor: style.fillColor,
                        fillOpacity: style.fillOpacity,
                        label: style.label,
                        fontColor: style.fontColor,
                        centerX: this.map.layers[i].features[j].geometry.getCentroid().x,
                        centerY: this.map.layers[i].features[j].geometry.getCentroid().y,
                        strokeDashstyle: style.strokeDashstyle,
                        strokeLinecap: style.strokeLinecap
                    };
                    this.write(params);
                    this.xml += "</FEATURE>"
                } else if (this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.MultiPolygon") {
                    var style =
                        this.map.layers[i].parseStyle(this.map.layers[i].features[j]);
                    var feature = this.map.layers[i].features[j];
                    for (var k = 0; k < feature.geometry.components.length; k++) {
                        this.xml += '<FEATURE type="polygon">';
                        var x = [];
                        var y = [];
                        var polygonComponent = feature.geometry.components[k];
                        for (var l = 0; l < polygonComponent.components[0].components.length; l++) {
                            x.push(polygonComponent.components[0].components[l].x);
                            y.push(polygonComponent.components[0].components[l].y)
                        }
                        var params = {
                            x: x,
                            y: y,
                            color: style.strokeColor,
                            opacity: style.strokeOpacity,
                            stroke: style.strokeWidth,
                            fillColor: style.fillColor,
                            fillOpacity: style.fillOpacity,
                            label: style.label,
                            fontColor: style.fontColor,
                            centerX: this.map.layers[i].features[j].geometry.getCentroid().x,
                            centerY: this.map.layers[i].features[j].geometry.getCentroid().y,
                            strokeDashstyle: style.strokeDashstyle,
                            strokeLinecap: style.strokeLinecap
                        };
                        this.write(params);
                        this.xml += "</FEATURE>"
                    }
                }
                this.xml += "</VECTORLAYER>"
            }
    },
    parsePopup: function() {
        if (this.map.popups.length > 0) this.xml += "<POPUPS>";
        for (var i = 0; i < this.map.popups.length; i++)
            if (this.map.popups[i].attributes &&
                this.map.popups[i].attributes.print) {
                this.xml += "<POPUP>";
                if (!this.map.popups[i].attributes.fontFamily) this.map.popups[i].attributes.fontFamily = "\uad74\ub9bc";
                if (!this.map.popups[i].attributes.fontSize) this.map.popups[i].attributes.fontSize = "12";
                if (!this.map.popups[i].attributes.fontColor) this.map.popups[i].attributes.fontColor = "#000000";
                var params = {
                    x: this.map.popups[i].lonlat.lon,
                    y: this.map.popups[i].lonlat.lat,
                    width: $(this.map.popups[i].contentDiv).css("width").replace("px", ""),
                    height: $(this.map.popups[i].contentDiv).css("height").replace("px",
                        ""),
                    text: this.map.popups[i].attributes.text,
                    fontFamily: this.map.popups[i].attributes.fontFamily,
                    fontSize: this.map.popups[i].attributes.fontSize.replace("px", ""),
                    fontColor: this.map.popups[i].attributes.fontColor
                };
                this.write(params);
                this.xml += "</POPUP>"
            }
        if (this.map.popups.length > 0) this.xml += "</POPUPS>"
    },
    parseMashupLayer : function(mashupLayer) {
		this.xml += '<MASHUPLAYER type="'+mashupLayer.type+'">';
		var params = {
			url : mashupLayer.url,
			minX : mashupLayer.minX,
			maxX : mashupLayer.maxX,
			minY : mashupLayer.minY,
			maxY : mashupLayer.maxY,
		}
		this.write(params);
		this.xml += "</MASHUPLAYER>";
	},
    write: function(obj) {
        for (var i in obj) this.xml += "<" + i + ">" + encodeURIComponent(obj[i]) + "</" + i + ">"
    },
    CLASS_NAME: "GSaveTool"
});
GLengendTool = OpenLayers.Class({
    map: null,
    layers: null,
    callback: null,
    sld: {},
    allList: false,
    initialize: function(map, layerName, layers, callback) {
        this.map = map;
        if (layerName) this.layer = this.map.getLayerByName(layerName);
        else this.layer = this.map.baseLayer;
        var layerList = [];
        if (layers) layerList = layers;
        else layerList = this.layer.params.LAYERS;
        this.callback = callback;
        var control = this;
        GRequest.WMS.getStyles(this.layer.url, layerList + ",", function(res) {
            control.sld = res;
            control.parseStyle()
        });
        this.map.events.register("moveend",
            this,
            function() {
                this.parseStyle()
            })
    },
    parseStyle: function() {
        var arr = [];
        var curLayers = {};
        if (!this.allList) {
            var pLayers = this.layer.params.LAYERS.split(",");
            for (var i = 0, len = pLayers.length; i < len; i++) curLayers[pLayers[i]] = true
        }
        var namedLayers = this.sld.namedLayers;
        for (var i in namedLayers) {
            var userStyles = namedLayers[i].userStyle;
            for (var j in userStyles) {
                var rules = userStyles[j].rules;
                for (var k in rules) {
                    if (rules[k].symbolizer.text) continue;
                    if (!this.allList) {
                        var scale = parseInt(this.map.getScale());
                        var maxScale =
                            rules[k].maxScale;
                        if (maxScale == 0) maxScale = parseInt(OpenLayers.Util.getScaleFromResolution(this.map.getResolutionForZoom(0), this.map.units))
                    }
                    var count = 0;
                    if (curLayers[namedLayers[i].name] && maxScale >= scale && scale >= rules[k].minScale || this.allList)
                        if (!rules[k].symbolizer.Text) {
                            var lengendObj = {
                                layer: namedLayers[i].name,
                                style: userStyles[j].name,
                                rule: rules[k].name,
                                count: count
                            };
                            arr.push(lengendObj);
                            count++
                        }
                }
            }
        }
        this.callback(arr)
    },
    showFullList: function() {
        this.allList = true;
        this.parseStyle()
    },
    showCurrentList: function() {
        this.allList =
            false;
        this.parseStyle()
    },
    CLASS_NAME: "GLengendTool"
});
GTMapLayerTool = OpenLayers.Class({
    groups: [],
    tMaps: [],
    layers: [],
    cloneLayers: [],
    layerGroups: [],
    cloneLayerGroups: [],
    tMapId: null,
    defaultSld: null,
    sld: null,
    format: new OpenLayers.Format.SLD.v1_1_0,
    initialize: function(layers, tMaps, groups, layerGroups, options) {
        this.layers = layers;
        this.cloneLayers = this.getCloneLayers(layers);
        if (tMaps) this.tMaps = tMaps;
        if (groups) this.groups = groups;
        if (layerGroups) {
            this.layerGroups = layerGroups;
            this.cloneLayerGroups = this.getCloneLayerGroups(layerGroups)
        }
        this.changeNull(this.layers);
        this.changeNull(this.tMaps);
        this.changeNull(this.groups);
        if (options && options.tMapId) this.tMapId = options.tMapId;
        else if (tMaps && tMaps[0] && tMaps[0].id) this.tMapId = tMaps[0].id;
        control = this;
        
        if(options && options.serviceUrl && options.callback) {
        	
			if(options.sync){
				GRequest.WMS.getStylesBySync(options.serviceUrl,this.getLayers({retAttr:"theme"})+',', function(res) {

					control.defaultSld = res.xml.cloneNode(true);
					var bool = false;
					if(options.userStyle) {
						control.setUserStyle(options.userStyle, res);
						bool = true;
					}
					else {
						control.sld = res;
					}
					options.callback(res, bool);
				});
			}
			else{

				GRequest.WMS.getStyles(options.serviceUrl,this.getLayers({retAttr:"theme"})+',', function(res) {

					control.defaultSld = res.xml.cloneNode(true);
					var bool = false;
					if(options.userStyle) {
						control.setUserStyle(options.userStyle, res);
						bool = true;
					}
					else {
						control.sld = res;
					}
					options.callback(res, bool);
				});
			}
		}
    },
    setUserStyle: function(userStyle, res) {
        var xml = new OpenLayers.Format.XML;
        var str = decodeURIComponent(userStyle);
        var sldObj = xml.read(str);
        var userDesc = sldObj.getElementsByTagName("sld:StyledLayerDescriptor");
        var userNamedLayers = userDesc[0].getElementsByTagName("sld:NamedLayer");
        var desc = res.xml.getElementsByTagName("sld:StyledLayerDescriptor");
        var namedLayers = desc[0].getElementsByTagName("sld:NamedLayer");
        for (var i = 0, len = userNamedLayers.length; i < len; i++) {
            var userName;
            element = userNamedLayers[i].getElementsByTagName("se:Name");
            if (element.length > 0) userName = element[0].text;
            for (var j = 0, jLen = namedLayers.length; j < jLen; j++) {
                var name;
                element = namedLayers[j].getElementsByTagName("se:Name");
                if (element.length > 0) name = element[0].text;
                if (userName == name) {
                    desc[0].removeChild(namedLayers[j]);
                    desc[0].appendChild(userNamedLayers[i])
                }
            }
        }
        this.sld = GRequest.WMS.parseGetStyles(res.xml)
    },
    setLayers: function(layers, options) {
        this.layers = layers;
        control = this;
        if (options && options.serviceUrl && options.callback) GRequest.WMS.getStyles(options.serviceUrl, this.getLayers({
                retAttr: "theme"
            }) +
            ",",
            function(res) {
                control.sld = res;
                var sldStr = encodeURIComponent(JSON.stringify(res, replacer));
                var cloneSld = JSON.parse(decodeURIComponent(sldStr.replace("/+/g", "%20")), reviver);
                control.defaultSld = cloneSld;
                var bool = 0;
                if (options.userStyle) bool = 1;
                options.callback(res, bool)
            })
    },
    setLayerGroups: function() {
        var control = this;
        var layers = this.getThemeShowList("asc");
        var groupsIdx = [];
        for (var i in layers)
            for (var j in control.layers)
                if (layers[i] == control.layers[j].theme)
                    if (groupsIdx.length == 0 || groupsIdx.length > 0 &&
                        groupsIdx[groupsIdx.length - 1] != control.layers[j].layerGroup) groupsIdx.push(control.layers[j].layerGroup)
    },
    getLayerGroups: function() {
        return this.layerGroups
    },
    getTMapName: function() {
        for (var i in this.tMaps)
            if (this.tMaps[i].id == this.tMapId) return this.tMaps[i].name;
        return false
    },
    setTMapByName: function(name) {
        for (var i in this.tMaps)
            if (this.tMaps[i].name == name) {
                tMapId = this.tMaps[i].id;
                return true
            }
        return false
    },
    setTMapbyId: function(id) {
        for (var i in this.tMaps)
            if (this.tMaps[i].id == id) {
                this.tMapId = id;
                return true
            }
        return false
    },
    getTMapId: function() {
        return this.tMapId
    },
    getLayerGroups: function(options) {
        if (!options) return this.layerGroups;
        var results = [];
        var arr = [];
        if (options.con)
            if (options.conVal)
                for (var i in this.layerGroups)
                    if (options.reverse) {
                        if (this.layerGroups[i][options.con] != options.conVal) arr.push(this.layerGroups[i])
                    } else {
                        if (this.layerGroups[i][options.con] == options.conVal) arr.push(this.layerGroups[i])
                    } else
            for (i in this.layerGroups)
                if (options.reverse) {
                    if (!this.layerGroups[i][options.con]) arr.push(this.layerGroups[i])
                } else {
                    if (this.layerGroups[i][options.con]) arr.push(this.layerGroups[i])
                } else
            for (var i in this.layerGroups) arr.push(this.layerGroups[i]);
        if (options.order) this.orderBySeq(arr, "seq", options.order);
        if (options.retAttr) {
            var retAttr = [];
            if (!(options.retAttr instanceof Array)) retAttr = [options.retAttr];
            else retAttr = options.retAttr;
            for (var i in arr)
                for (var j in retAttr) results.push(arr[i][retAttr[j]])
        } else results = arr;
        return results
    },
    getLayers: function(options) {
        if (!options) return this.layers;
        var results = [];
        var arr = [];
        if (options.con)
            if (options.conVal)
                for (var i in this.layers)
                    if (options.reverse) {
                        if (this.layers[i][options.con] != options.conVal) arr.push(this.layers[i])
                    } else {
                        if (this.layers[i][options.con] ==
                            options.conVal) arr.push(this.layers[i])
                    } else
            for (i in this.layers)
                if (options.reverse) {
                    if (!this.layers[i][options.con]) arr.push(this.layers[i])
                } else {
                    if (this.layers[i][options.con]) arr.push(this.layers[i])
                } else
            for (var i in this.layers) arr.push(this.layers[i]);
        if (options.order) this.orderBySeq(arr, "seq", options.order);
        if (options.retAttr) {
            var retAttr = [];
            if (!(options.retAttr instanceof Array)) retAttr = [options.retAttr];
            else retAttr = options.retAttr;
            for (var i in arr)
                for (var j in retAttr) results.push(arr[i][retAttr[j]])
        } else results =
            arr;
        return results
    },
    getThemeList: function(order) {
        var options = {
            con: "tmapid",
            conVal: this.getTMapId(),
            order: order,
            retAttr: "theme"
        };
        return this.getLayers(options)
    },
    getThemeShowList: function(order) {
        var options = {
            con: "show",
            conVal: 1,
            order: order,
            retAttr: "theme"
        };
        return this.getLayers(options)
    },
    getAliasList: function(order) {
        var options = {
            con: "tmapid",
            conVal: this.getTMapId(),
            order: order,
            retAttr: "alias"
        };
        return this.getLayers(options)
    },
    getTableList: function(order) {
        var options = {
            con: "tmapid",
            conVal: this.getTMapId(),
            order: order,
            retAttr: "table"
        };
        return this.getLayers(options)
    },
    getLayersSize: function() {
        return this.layers.length
    },
    setLayerAttr: function(options) {
        if (options && options.con && options.conVal && options.attr) {
            var attrs = [];
            var values = [];
            if (!(options.attr instanceof Array)) attrs = [options.attr];
            else attrs = options.attr;
            if (!(options.value instanceof Array)) values = [options.value];
            else values = options.value;
            for (var i in this.layers)
                if (this.layers[i][options.con] == options.conVal) {
                    for (var j = 0, len = attrs.length; j < len; j++) this.layers[i][attrs[j]] =
                        values[j];
                    return true
                }
        } else return false
    },
    orderBySeq: function(arr, field, order) {
        var len = arr.length;
        for (var i = len - 1; i > 0; i--)
            for (var j = 0; j < i; j++)
                if (order.toLowerCase() == "desc") {
                    if (eval(arr[j][field]) < eval(arr[j + 1][field])) GUtil.Array.fn_swap_element(arr, j, j + 1)
                } else if (eval(arr[j][field]) > eval(arr[j + 1][field])) GUtil.Array.fn_swap_element(arr, j, j + 1)
    },
    changeNull: function(arr) {
        for (var i in arr)
            for (var j in arr[i])
                if (arr[i][j] == -1) arr[i][j] = null
    },
    setSld: function(sld) {
        this.sld = sld
    },
    getSld: function() {
        return this.sld
    },
    getSld_body: function(ruleView, baseLayer) {
        var tempXml = this.sld.xml.cloneNode(true);
        var sld = this.format.read(tempXml);
        var namedLayers = sld.namedLayers;
        var delLayers = [];
        for (var i in this.layers)
            if (this.layers[i].show != 1) delLayers.push(this.layers[i].theme);
        if (baseLayer)
            for (var i = delLayers.length - 1; i >= 0; i--)
                if (delLayers[i] == baseLayer) delLayers.splice(i, 1);
        if (ruleView) {
            var index = 0;
            for (var i in sld.namedLayers) {
                var name = "";
                namedLayersName = sld.namedLayers[i].name;
                if (namedLayersName.length > 0) name = namedLayersName;
                var check = false;
                for (var j in delLayers)
                    if (delLayers[j] == name) {
                        delete namedLayers[i];
                        check = true;
                        break;
                    }
                if (!check)
                    for (var j in this.sld.namedLayers[index].userStyle)
                        for (var k in this.sld.namedLayers[index].userStyle[j].rules)
                            if (typeof this.sld.namedLayers[index].userStyle[j].rules[k].hidden === "undefined" || this.sld.namedLayers[index].userStyle[j].rules[k].hidden) {
                            	
                            	// 2016.02.16 CJH 수정 - splice를 통해 배열의 크기가
								// 조정되는(줄어드는) 부분이 반영되지 않아 다른 룰을 hidden처리하게 되는 문제
								// 수정.
                                var featureTypeStyle = sld.namedLayers[i].userStyles[j];
                                var rules = featureTypeStyle.rules;
                                for (var l = 0, lLen = rules.length; l < lLen; l++) {
                                    var ruleName = rules[l].name;
                                    if (this.sld.namedLayers[index].userStyle[j].rules[k].name == ruleName) {

                                    	sld.namedLayers[i].userStyles[j].rules.splice(l, 1);
                                        if (rules.length == 1) {

                                        	delete sld.namedLayers[index]
                                        }
                                        break;
                                    }
                                }
                            }
                index++
            }
        } else
            for (var i in sld.namedLayers) {
                var name = "";
                var namedLayerName = sld.namedLayers[i].name;
                if (namedLayerName.length > 0) name = namedLayerName;
                for (var j in delLayers)
                    if (delLayers[j] == name) {
                        delete namedLayers[i];
                        break;
                    }
            }
        return this.format.write(sld)
    },
    getDefaultSld: function() {
        return this.defaultSld
    },
    getModifyStr: function() {
        var tempXml = this.sld.xml.cloneNode(true);
        var desc = tempXml.getElementsByTagName("sld:StyledLayerDescriptor");
        var namedLayers = desc[0].getElementsByTagName("sld:NamedLayer");
        var delLayers = [];
        for (var i in this.sld.namedLayers);
        for (var i = 0, len = namedLayers.length; i < len; i++) {
            var name;
            element = namedLayers[i].getElementsByTagName("se:Name");
            if (element.length > 0) name = element[0].text;
            for (var j in delLayers)
                if (delLayers[j] == name) desc[0].removeChild(namedLayers[i])
        }
        return tempXml.xml
    },
    backSld: function(layer) {},
    updateSld: function(layer) {
        layer.modify = true;
        for (var i = 0, len = this.sld.data.namedLayers.length; i < len; i++)
            if (layer.name == this.sld.data.namedLayers[i].name) {
                var userStyles = this.sld.data.namedLayers[i].userStyle;
                for (var j = 0, jLen = userStyles.length; j < jLen; j++) {
                    var rules = userStyles[j].rules;
                    for (var k = 0, kLen = rules.length; k < kLen; k++) {
                        if (rules[k].symbolizer["point"]) {
                            rules[k].symbolizer["point"].externalGraphic = layer.userStyle[j].rules[k].symbolizer.point.externalGraphic;
                            rules[k].symbolizer["point"].size = layer.userStyle[j].rules[k].symbolizer.point.size
                        }
                        if (rules[k].symbolizer["line"]) {
                            rules[k].symbolizer["line"].stroke =
                                layer.userStyle[j].rules[k].symbolizer.line.stroke;
                            rules[k].symbolizer["line"].strokeWidth = layer.userStyle[j].rules[k].symbolizer.line.strokeWidth;
                            rules[k].symbolizer["line"].strokeOpacity = layer.userStyle[j].rules[k].symbolizer.line.strokeOpacity;
                            rules[k].symbolizer["line"].strokeLinecap = layer.userStyle[j].rules[k].symbolizer.line.strokeLinecap;
                            if (rules[k].symbolizer["line"].strokeDashArray) {
                                var selStyle = layer.userStyle[j].rules[k].symbolizer.line.strokeDashArray;
                                if (selStyle == "dot") rules[k].symbolizer["line"].strokeDashArray =
                                    "2.0,2.0";
                                else if (selStyle == "dash") rules[k].symbolizer["line"].strokeDashArray = "7.0,3.0";
                                else if (selStyle == "dashdot") rules[k].symbolizer["line"].strokeDashArray = "10.0,2.0,2.0,2.0"
                            }
                        }
                        if (rules[k].symbolizer["polygon"]) {
                            rules[k].symbolizer["polygon"].fillColor = layer.userStyle[j].rules[k].symbolizer.polygon.fillColor;
                            rules[k].symbolizer["polygon"].fillOpacity = layer.userStyle[j].rules[k].symbolizer.polygon.fillOpacity
                        }
                        if (rules[k].symbolizer["text"]) {
                            rules[k].symbolizer["text"].fontFamily = layer.userStyle[j].rules[k].symbolizer.text.fontFamily;
                            rules[k].symbolizer["text"].fontSize = layer.userStyle[j].rules[k].symbolizer.text.fontSize;
                            rules[k].symbolizer["text"].fontStyle = layer.userStyle[j].rules[k].symbolizer.text.fontStyle;
                            rules[k].symbolizer["text"].fontWeight = layer.userStyle[j].rules[k].symbolizer.text.fontWeight;
                            rules[k].symbolizer["text"].haloColor = layer.userStyle[j].rules[k].symbolizer.text.haloColor;
                            rules[k].symbolizer["text"].haloOpacity = layer.userStyle[j].rules[k].symbolizer.text.haloOpacity;
                            rules[k].symbolizer["text"].fillColor =
                                layer.userStyle[j].rules[k].symbolizer.text.fillColor;
                            rules[k].symbolizer["text"].fillOpacity = layer.userStyle[j].rules[k].symbolizer.text.fillOpacity
                        }
                    }
                }
            }
    },
    getTMapGroup: function() {
        return this.groups
    },
    getTMap: function() {
        return this.tMaps
    },
    addTMap: function(obj) {
        this.tMaps.push(obj)
    },
    insertGroup: function(aGroupId, bGroupId) {
        aGroupId = parseInt(aGroupId);
        bGroupId = parseInt(bGroupId);
        this.orderBySeq(this.layerGroups, "seq", "asc");
        var idA;
        var idB;
        var seqA;
        var seqB;
        for (var i in this.layerGroups) {
            if (this.layerGroups[i].id ==
                aGroupId) {
                seqA = this.layerGroups[i].seq;
                idA = i
            }
            if (this.layerGroups[i].id == bGroupId) {
                seqB = this.layerGroups[i].seq;
                idB = i
            }
        }
        if (idA - idB > 0) {
            this.layerGroups[idA].seq = this.layerGroups[idB].seq;
            for (var i = idB, len = idA; i < len; i++) this.layerGroups[i].seq += 1
        } else {
            this.layerGroups[idA].seq = this.layerGroups[idB].seq;
            for (var i = idB, len = idA; i > len; i--) this.layerGroups[i].seq -= 1
        }
        this.setLayerSeq(this.layerGroups)
    },
    setLayerSeq: function(layerGroups) {
        var layerSeq = 1;
        this.orderBySeq(layerGroups, "seq", "asc");
        for (var i in layerGroups)
            for (var j in this.layers)
                if (layerGroups[i].id ==
                    this.layers[j].layerGroup) this.layers[j].seq = layerSeq++
    },
    insertLayer: function(layerId, targetId) {
        this.orderBySeq(this.layers, "seq", "asc");
        var layerIdx;
        var layerSeq;
        var targetIdx;
        var targetSeq;
        for (var i in this.layers) {
            if (this.layers[i].id == layerId) {
                layerSeq = this.layers[i].seq;
                layerIdx = i
            }
            if (this.layers[i].id == targetId) {
                targetSeq = this.layers[i].seq;
                targetIdx = i
            }
        }
        if (layerIdx - targetIdx > 0) {
            this.layers[layerIdx].seq = this.layers[targetIdx].seq;
            for (var i = targetIdx, len = layerIdx; i < len; i++) this.layers[i].seq +=
                1
        } else {
            this.layers[layerIdx].seq = this.layers[targetIdx].seq;
            for (var i = targetIdx, len = layerIdx; i > len; i--) this.layers[i].seq -= 1
        }
        this.orderBySeq(this.layers, "seq", "asc")
    },
    getCloneLayerGroups: function(obj) {
        var arr = [];
        if (obj)
            for (var i = 0, len = obj.length; i < len; i++) arr.push(this.cloneObj(obj[i]));
        else
            for (var i = 0, len = this.layerGroups.length; i < len; i++) arr.push(this.cloneObj(this.layerGroups[i]));
        return arr
    },
    getCloneLayers: function(obj) {
        var arr = [];
        if (obj)
            for (var i = 0, len = obj.length; i < len; i++) arr.push(this.cloneObj(obj[i]));
        else
            for (var i = 0, len = this.layers.length; i < len; i++) arr.push(this.cloneObj(this.layers[i]));
        return arr
    },
    cloneObj: function(obj) {
        var clone = {};
        for (var i in obj) clone[i] = obj[i];
        return clone
    },
    CLASS_NAME: "GTMapLayerTool"
});
GSLDTool = OpenLayers.Class({
    data: null,
    initialize: function(sld, type) {
        if (type == "xml") this.data = this.getSLDObj(sld);
        else if (type == "obj") this.data = sld.data
    },
    getData: function() {
        return this.data
    },
    getSLDObj: function(xml) {
        var data;
        var namedObj;
        var userdObj;
        var ruleObj;
        var namedLayers;
        var userStyles;
        var rules;
        data = this.readMapTopic(xml);
        namedLayers = xml.getElementsByTagName("sld:NamedLayer");
        for (var i = 0, iLen = namedLayers.length; i < iLen; i++) {
            namedObj = this.readNamedLayer(namedLayers[i]);
            userStyles = namedLayers[i].getElementsByTagName("sld:UserStyle");
            for (var j = 0, jLen = userStyles.length; j < jLen; j++) {
                userdObj = this.readUserStyle(userStyles[j]);
                rules = userStyles[j].getElementsByTagName("se:Rule");
                for (var k = 0, kLen = rules.length; k < kLen; k++) {
                    ruleObj = this.readRule(rules[k]);
                    userdObj.rules.push(ruleObj)
                }
                namedObj.userStyle.push(userdObj)
            }
            data.namedLayers.push(namedObj)
        }
        return data
    },
    readMapTopic: function(xml) {
        var data = {
            name: "",
            title: "",
            namedLayers: []
        };
        var element = xml.getElementsByTagName("se:Name");
        if (element.length > 0) data.name = xml.getElementsByTagName("se:Name")[0].textContent;
        element = xml.getElementsByTagName("se:Description");
        if (element.length > 0) {
            var subElement = element[0].getElementsByTagName("se:Title");
            if (subElement.length > 0) data.title = subElement[0].textContent;
        }
        return data
    },
    readNamedLayer: function(namedLayers) {
        var namedObj = {
            name: "",
            title: "",
            featureTypeName: "",
            userStyle: []
        };
        var element = namedLayers.getElementsByTagName("se:Name");
        if (element.length > 0) namedObj.name = element[0].textContent;
        element = namedLayers.getElementsByTagName("se:Description");
        if (element.length > 0) {
            var subElement = element[0].getElementsByTagName("se:Title");
            if (subElement.length > 0) namedObj.title = subElement[0].textContent
        }
        element = namedLayers.getElementsByTagName("sld:LayerFeatureConstraints");
        if (element.length > 0) {
            subElement = element[0].getElementsByTagName("se:FeatureTypeName");
            if (subElement.length > 0) namedObj.featureTypeName = subElement[0].textContent
        }
        return namedObj
    },
    readUserStyle: function(userStyles) {
        var userdObj = {
            name: "",
            title: "",
            featureTypeName: "",
            rules: []
        };
        var element = userStyles.getElementsByTagName("se:Name");
        if (element.length > 0) userdObj.name = element[0].textContent;
        element =
            userStyles.getElementsByTagName("se:Description");
        if (element.length > 0) {
            subElement = element[0].getElementsByTagName("se:Title");
            if (subElement.length > 0) userdObj.title = subElement[0].textContent
        }
        element = userStyles.getElementsByTagName("se:FeatureTypeStyle");
        if (element.length > 0) {
            subElement = element[0].getElementsByTagName("se:FeatureTypeName");
            if (subElement.length > 0) userdObj.featureTypeName = subElement[0].textContent
        }
        return userdObj
    },
    readRule: function(rules) {
        var ruleObj = {
            name: "",
            filterName: "",
            filterLiteral: "",
            minScale: "",
            maxScale: "",
            symbolizer: {}
        };
        var element = rules.getElementsByTagName("se:Name");
        if (element.length > 0) ruleObj.name = element[0].textContent;
        element = rules.getElementsByTagName("ogc:Filter");
        if (element.length > 0) {
            subElement = element[0].getElementsByTagName("PropertyIsEqualTo");
            if (subElement.length > 0) {
                ruleObj.filterName_equal = subElement[0].getElementsByTagName("ogc:PropertyName")[0].textContent;
                ruleObj.filterLiteral_equal = subElement[0].getElementsByTagName("Literal")[0].textContent
            }
            subElement = element[0].getElementsByTagName("PropertyIsGreaterThanOrEqualTo");
            if (subElement.length > 0) {
                ruleObj.filterName_more = [];
                ruleObj.more = [];
                for (var i = 0, len = subElement.length; i < len; i++) {
                    ruleObj.filterName_more.push(subElement[i].getElementsByTagName("ogc:PropertyName")[0].textContent);
                    ruleObj.more.push(subElement[i].getElementsByTagName("Literal")[0].textContent)
                }
            }
            subElement = element[0].getElementsByTagName("PropertyIsLessThan");
            if (subElement.length > 0) {
                ruleObj.filterName_less = [];
                ruleObj.less = [];
                for (var i = 0, len = subElement.length; i < len; i++) {
                    ruleObj.filterName_less.push(subElement[i].getElementsByTagName("ogc:PropertyName")[0].textContent);
                    ruleObj.less.push(subElement[i].getElementsByTagName("Literal")[0].textContent)
                }
            }
            subElement = element[0].getElementsByTagName("PropertyIsGreaterThan");
            if (subElement.length > 0) {
                ruleObj.filterName_greater = subElement[0].getElementsByTagName("ogc:PropertyName")[0].textContent;
                ruleObj.filterLiteral_greater = subElement[0].getElementsByTagName("Literal")[0].textContent
            }
        }
        element = rules.getElementsByTagName("se:MinScaleDenominator");
        if (element.length > 0) ruleObj.minScale = element[0].textContent;
        element = rules.getElementsByTagName("se:MaxScaleDenominator");
        if (element.length > 0) ruleObj.maxScale = element[0].textContent;
        var points = rules.getElementsByTagName("se:PointSymbolizer");
        var lines = rules.getElementsByTagName("se:LineSymbolizer");
        var polygons = rules.getElementsByTagName("se:PolygonSymbolizer");
        var texts = rules.getElementsByTagName("se:TextSymbolizer");
        if (points.length > 0) ruleObj.symbolizer["point"] = this.readPointSym(points);
        if (lines.length > 0)
            for (var i = 0, len = lines.length; i < len; i++)
                if (i == 0) ruleObj.symbolizer["line"] = this.readLineSym(lines[i]);
                else ruleObj.symbolizer["line" +
                    i] = this.readLineSym(lines[i]);
        if (polygons.length > 0) ruleObj.symbolizer["polygon"] = this.readPolySym(polygons);
        if (texts.length > 0) ruleObj.symbolizer["text"] = this.readTextSym(texts);
        return ruleObj
    },
    readPointSym: function(points) {
        var pointObj = {};
        pointObj["type"] = "point";
        pointObj["version"] = points[0].getAttribute("version");
        var symbolName = points[0].getElementsByTagName("se:Name");
        if (symbolName.length > 0) pointObj["name"] = symbolName[0].textContent;
        var svgParam = points[0].getElementsByTagName("se:Size");
        if (svgParam.length >
            0) pointObj["size"] = svgParam[0].textContent;
        svgParam = points[0].getElementsByTagName("se:Opacity");
        if (svgParam.length > 0) pointObj["opacity"] = "1.0";
        svgParam = points[0].getElementsByTagName("se:Rotation");
        if (svgParam.length > 0) {
            var rotation = svgParam[0].getElementsByTagName("ogc:PropertyName");
            if (rotation.length > 0) pointObj["rotation"] = rotation[0].textContent
        }
        svgParam = points[0].getElementsByTagName("se:Displacement");
        if (svgParam.length > 0) {
            var x = svgParam[0].getElementsByTagName("se:DisplacementX");
            if (x.length > 0) pointObj["displacementX"] =
                x[0].textContent;
            var y = svgParam[0].getElementsByTagName("se:DisplacementY");
            if (y.length > 0) pointObj["displacementY"] = y[0].textContent
        }
        var externalGraphics = points[0].getElementsByTagName("se:ExternalGraphic");
        if (externalGraphics.length > 0) {
            var inlineContents = externalGraphics[0].getElementsByTagName("se:InlineContent");
            if (inlineContents.length > 0) {
                pointObj["externalGraphic"] = inlineContents[0].textContent;
                pointObj["encoding"] = inlineContents[0].getAttribute("encoding")
            }
            var format = externalGraphics[0].getElementsByTagName("se:Format");
            if (format.length > 0) pointObj["format"] = format[0].textContent
        }
        var graphic = points[0].getElementsByTagName("se:Graphic");
        if (graphic.length > 0) {
            var mark = graphic[0].getElementsByTagName("se:Mark");
            if (mark.length > 0) {
                var vendor = mark[0].getElementsByTagName("sld:VendorOption");
                for (var i = 0, len = vendor.length; i < len; i++)
                    if (vendor[i].getAttribute("name") == "font-family") pointObj["markFont"] = vendor[i].textContent;
                    else if (vendor[i].getAttribute("name") == "char-code") pointObj["markCharCode"] = vendor[i].textContent;
                else if (vendor[i].getAttribute("name") ==
                    "fill") pointObj["markFill"] = vendor[i].textContent
            }
        }
        return pointObj
    },
    readLineSym: function(lines) {
        var lineObj = {};
        lineObj["type"] = "line";
        lineObj["version"] = lines.getAttribute("version");
        var symbolName = lines.getElementsByTagName("se:Name");
        if (symbolName.length > 0) lineObj["name"] = symbolName[0].textContent;
        if (lineObj["name"] == "CompositeLineCap") lineObj.arrow = true;
        if (lineObj["name"] == "CompositeLineMarker") lineObj.marker = true;
        var svgParam = lines.getElementsByTagName("se:SvgParameter");
        for (var l = 0, lLen = svgParam.length; l <
            lLen; l++) {
            if (svgParam[l].getAttribute("name") == "stroke") lineObj["stroke"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "stroke-width") lineObj["strokeWidth"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "stroke-opacity") lineObj["strokeOpacity"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "stroke-linecap") lineObj["strokeLinecap"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "stroke-dasharray") lineObj["strokeDasharray"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") ==
                "stroke-linejoin") lineObj["strokeLinejoin"] = svgParam[l].textContent
        }
        var svgParam = lines.getElementsByTagName("se:VendorOption");
        if (svgParam.length > 0) {
            lineObj["cap_style"] = [];
            lineObj["cap_size"] = [];
            lineObj["cap_color"] = [];
            lineObj["cap_fill"] = [];
            lineObj["cap_position"] = []
        }
        for (var l = 0, lLen = svgParam.length; l < lLen; l++) {
            if (svgParam[l].getAttribute("name") == "cap_direction") lineObj["cap_direction"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "cap_angle") lineObj["cap_angle"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") ==
                "start_cap") lineObj["start_cap"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "end_cap") lineObj["end_cap"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "cap_style") lineObj["cap_style"].push(svgParam[l].textContent);
            if (svgParam[l].getAttribute("name") == "cap_size") lineObj["cap_size"].push(svgParam[l].textContent);
            if (svgParam[l].getAttribute("name") == "cap_color") lineObj["cap_color"].push(svgParam[l].textContent);
            if (svgParam[l].getAttribute("name") == "cap_fill") lineObj["cap_fill"].push(svgParam[l].textContent);
            if (svgParam[l].getAttribute("name") == "cap_position") lineObj["cap_position"].push(svgParam[l].textContent)
        }
        return lineObj
    },
    readPolySym: function(polygons) {
        var polyObj = {};
        polyObj["type"] = "polygon";
        polyObj["version"] = polygons[0].getAttribute("version");
        var symbolName = polygons[0].getElementsByTagName("se:Name");
        if (symbolName.length > 0) polyObj["name"] = symbolName[0].textContent;
        svgParam = polygons[0].getElementsByTagName("se:SvgParameter");
        for (var l = 0, lLen = svgParam.length; l < lLen; l++) {
            if (svgParam[l].getAttribute("name") ==
                "fill") polyObj["fillColor"] = svgParam[l].textContent;
            if (svgParam[l].getAttribute("name") == "fill-opacity") polyObj["fillOpacity"] = svgParam[l].textContent
        }
        return polyObj
    },
    readTextSym: function(texts) {
        var textObj = {};
        textObj["type"] = "text";
        textObj["version"] = texts[0].getAttribute("version");
        var symbolName = texts[0].getElementsByTagName("se:Name");
        if (symbolName.length > 0) textObj["name"] = symbolName[0].textContent;
        element = texts[0].getElementsByTagName("se:Label");
        if (element.length > 0) {
            subElement = element[0].getElementsByTagName("ogc:PropertyName");
            if (subElement.length > 0) textObj["label"] = subElement[0].textContent
        }
        var fonts = texts[0].getElementsByTagName("se:Font");
        if (fonts.length > 0) {
            svgParam = fonts[0].getElementsByTagName("se:SvgParameter");
            for (var l = 0, lLen = svgParam.length; l < lLen; l++)
                if (svgParam[l].getAttribute("name") == "font-family") textObj["fontFamily"] = svgParam[l].textContent;
                else if (svgParam[l].getAttribute("name") == "font-size") textObj["fontSize"] = svgParam[l].textContent;
            else if (svgParam[l].getAttribute("name") == "font-style") textObj["fontStyle"] = svgParam[l].textContent;
            else if (svgParam[l].getAttribute("name") == "font-weight") textObj["fontWeight"] = svgParam[l].textContent
        }
        var LabelPlacement = texts[0].getElementsByTagName("se:LabelPlacement");
        if (LabelPlacement.length > 0) {
            var PointPlacement = LabelPlacement[0].getElementsByTagName("se:PointPlacement");
            var LinePlacement = LabelPlacement[0].getElementsByTagName("se:LinePlacement");
            if (PointPlacement.length > 0) {
                var Displacement = PointPlacement[0].getElementsByTagName("se:Displacement");
                if (Displacement.length > 0) {
                    var DisplacementX = Displacement[0].getElementsByTagName("se:DisplacementX");
                    if (DisplacementX.length > 0) textObj["displacementX"] = DisplacementX[0].textContent;
                    var DisplacementY = Displacement[0].getElementsByTagName("se:DisplacementY");
                    if (DisplacementY.length > 0) textObj["displacementY"] = DisplacementY[0].textContent
                }
            }
            if (LinePlacement.length > 0) {
                var VendorOption = LinePlacement[0].getElementsByTagName("se:VendorOption");
                for (var m = 0, mLen = VendorOption.length; m < mLen; m++)
                    if (VendorOption[m].getAttribute("name") == "text_arrange_pos") textObj["text_arrange_pos"] = VendorOption[m].textContent;
                    else if (VendorOption[m].getAttribute("name") == "text_arrange_line") textObj["text_arrange_line"] = VendorOption[m].textContent;
                else if (VendorOption[m].getAttribute("name") == "text_arrange_gap") textObj["text_arrange_gap"] = VendorOption[m].textContent;
            }
        }
        var halo = texts[0].getElementsByTagName("se:Halo");
        if (halo.length > 0) {
            var radius = halo[0].getElementsByTagName("se:Radius");
            if (radius.length > 0) textObj["radius"] = radius[0].textContent;
            var haloFill = halo[0].getElementsByTagName("se:Fill");
            if (haloFill.length > 0) {
                svgParam = haloFill[0].getElementsByTagName("se:SvgParameter");
                if (svgParam.length >
                    0)
                    for (m = 0, mLen = svgParam.length; m < mLen; m++)
                        if (svgParam[m].getAttribute("name") == "fill") textObj["haloColor"] = svgParam[m].textContent;
                        else if (svgParam[m].getAttribute("name") == "fill-opacity") textObj["haloOpacity"] = svgParam[m].textContent
            }
        }
        var fill = texts[0].getElementsByTagName("se:Fill");
        for (l = 0; l < fill.length; l++) {
            svgParam = fill[l].getElementsByTagName("se:SvgParameter");
            if (svgParam.length > 0)
                for (m = 0, mLen = svgParam.length; m < mLen; m++)
                    if (svgParam[m].getAttribute("name") == "fill") textObj["fillColor"] = svgParam[m].textContent;
                    else if (svgParam[m].getAttribute("name") ==
                "fill-opacity") textObj["fillOpacity"] = svgParam[m].textContent
        }
        var vendorOption = texts[0].getElementsByTagName("sld:VendorOption");
        for(var l =0; l < vendorOption.length; l++) {
        	if(vendorOption[l].getAttribute("name") === "text_point_base") textObj["textPointBase"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "text_point_position") textObj["textPointPosition"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "text_point_arrange") textObj["textPointArrange"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "background_type") textObj["backgroundType"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "background_fill") textObj["backgroundFill"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "background_line") textObj["backgroundLine"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "background_offset") textObj["backgroundOffset"] = vendorOption[l].textContent;
        	else if(vendorOption[l].getAttribute("name") === "background_align") textObj["backgroundAlign"] = vendorOption[l].textContent;
        }
        return textObj
    },
    getSLDXML: function(data) {
        var xml = '<?xml version="1.0" encoding="UTF-8" ?>';
        if (data)
            if (data) xml += this.writeMapTopic(this.data, data);
            else xml += this.writeMapTopic(data);
        else xml += this.writeMapTopic(this.data);
        return xml
    },
    writeMapTopic: function(data, opt) {
        var namedObj = data.namedLayers;
        var str;
        str = '<sld:StyledLayerDescriptor xsi:schemaLocation="" version="1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:se="http://www.opengis.net/se" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sld="http://www.opengis.net/sld">';
        str += "<se:Name>" + data.name + "</se:Name>";
        str += "<se:Description><se:Title>" + data.title + "</se:Title></se:Description>";
        if (opt == "default")
            for (var i = 0, len = layerTool.layers.length; i < len; i++)
                for (var l = 0, lLen = namedObj.length; l < lLen; l++) {
                    if (namedObj[l].name == "\ud589\uc815\uc74d\uba74\ub3d9") str += this.writeNamedLayer(namedObj[l])
                } else {
                    for (var j = 0, jLen = layerTool.layers.length; j < jLen; j++)
                        for (var k = 0, kLen = namedObj.length; k < kLen; k++)
                            if (layerTool.layers[j].alias == namedObj[k].name)
                                if (layerTool.layers[j].show == "1") namedObj[k].show =
                                    "1";
                                else namedObj[k].show = "0";
                    if (opt == "print")
                        for (var i = layerTool.layers.length - 1; i >= 0; i--)
                            for (var l = 0, lLen = namedObj.length; l < lLen; l++) {
                                if (layerTool.layers[i].alias == namedObj[l].name)
                                    if (namedObj[l].show == "1") str += this.writeNamedLayer(namedObj[l])
                            } else
                                for (var i = 0, len = layerTool.layers.length; i < len; i++)
                                    for (var l = 0, lLen = namedObj.length; l < lLen; l++)
                                        if (layerTool.layers[i].alias == namedObj[l].name)
                                            if (namedObj[l].show == "1") str += this.writeNamedLayer(namedObj[l])
                }
        str += "</sld:StyledLayerDescriptor>";
        return str
    },
    writeNamedLayer: function(namedObj) {
        var userdObj = namedObj.userStyle;
        var str;
        str = "<sld:NamedLayer>";
        str += "<se:Name>" + namedObj.name + "</se:Name>";
        str += "<se:Description><se:Title>" + namedObj.title + "</se:Title></se:Description>";
        str += "<sld:LayerFeatureConstraints><sld:FeatureTypeConstraint><se:FeatureTypeName>";
        str += namedObj.featureTypeName;
        str += "</se:FeatureTypeName></sld:FeatureTypeConstraint></sld:LayerFeatureConstraints>";
        for (var j = 0, jLen = userdObj.length; j < jLen; j++) str += this.writeUserStyle(userdObj[j]);
        str += "</sld:NamedLayer>";
        return str
    },
    writeUserStyle: function(userdObj) {
        var ruleObj = userdObj.rules;
        var str;
        var control = this;
        str = "<sld:UserStyle>";
        str += "<se:Name>" + userdObj.name + "</se:Name>";
        str += "<se:Description><se:Title>" + userdObj.title + "</se:Title></se:Description>";
        str += "<se:FeatureTypeStyle>";
        str += "<se:FeatureTypeName>" + userdObj.featureTypeName + "</se:FeatureTypeName>";
        for (var k = 0, kLen = ruleObj.length; k < kLen; k++)
            if (!(ruleObj[k].hidden != null && ruleObj[k].hidden)) str += this.writeRule(ruleObj[k]);
        str +=
            "</se:FeatureTypeStyle>";
        str += "</sld:UserStyle>";
        return str
    },
    writeRule: function(ruleObj, ruleOnOff) {
        var points;
        var lines;
        var polygons;
        var texts;
        var str;
        str = "<se:Rule>";
        str += "<se:Name>" + ruleObj.name + "</se:Name>";
        if (ruleObj.name == "\uadf8\uc678\uad00\ub85c_\uc8fc\uc11d" || ruleObj.name == "\uadf8\uc678\uad00\ub85c") {
            str += "<ogc:Filter>";
            str += '<And xmlns="http://www.opengis.net/ogc">';
            str += "<And>";
            str += "<And>";
            str += "<And>";
            str += "<PropertyIsGreaterThanOrEqualTo>";
            str += "<ogc:PropertyName>";
            str += "PLINE_KND_SE";
            str += "</ogc:PropertyName>";
            str += "<Literal>";
            str += "SAA006";
            str += "</Literal>";
            str += "</PropertyIsGreaterThanOrEqualTo>";
            str += "<PropertyIsLessThan>";
            str += "<ogc:PropertyName>";
            str += "PLINE_KND_SE";
            str += "</ogc:PropertyName>";
            str += "<Literal>";
            str += "SAA009";
            str += "</Literal>";
            str += "</PropertyIsLessThan>";
            str += "</And>";
            str += "<PropertyIsGreaterThanOrEqualTo>";
            str += "<ogc:PropertyName>";
            str += "PLINE_KND_SE";
            str += "</ogc:PropertyName>";
            str += "<Literal>";
            str += "SAA011";
            str += "</Literal>";
            str += "</PropertyIsGreaterThanOrEqualTo>";
            str += "</And>";
            str += "<PropertyIsLessThan>";
            str += "<ogc:PropertyName>";
            str += "PLINE_KND_SE";
            str += "</ogc:PropertyName>";
            str += "<Literal>";
            str += "SAA030";
            str += "</Literal>";
            str += "</PropertyIsLessThan>";
            str += "</And>";
            str += "<PropertyIsGreaterThanOrEqualTo>";
            str += "<ogc:PropertyName>";
            str += "PLINE_ET";
            str += "</ogc:PropertyName>";
            str += "<Literal>";
            str += "20.0";
            str += "</Literal>";
            str += "</PropertyIsGreaterThanOrEqualTo>";
            str += "</And>";
            str += "</ogc:Filter>"
        } else if (ruleObj.filterName_equal || ruleObj.filterLiteral_equal ||
            ruleObj.more || ruleObj.less) {
            str += "<ogc:Filter>";
            if (ruleObj.filterName_equal && ruleObj.filterName_greater || ruleObj.filterName_equal && ruleObj.more) str += "<ogc:And>";
            if (ruleObj.filterName_equal && ruleObj.filterLiteral_equal) {
                str += '<PropertyIsEqualTo xmlns="http://www.opengis.net/ogc"><ogc:PropertyName>';
                str += ruleObj.filterName_equal + "</ogc:PropertyName>";
                str += "<Literal>" + ruleObj.filterLiteral_equal + "</Literal>";
                str += "</PropertyIsEqualTo>"
            }
            if (ruleObj.filterName_greater && ruleObj.filterLiteral_greater) {
                str +=
                    "<PropertyIsGreaterThan>";
                str += "<ogc:PropertyName>";
                str += ruleObj.filterName_greater;
                str += "</ogc:PropertyName>";
                str += "<Literal>" + ruleObj.filterLiteral_greater + "</Literal>";
                str += "</PropertyIsGreaterThan>"
            }
            if (ruleObj.more && ruleObj.less) {
                var cnt = 0;
                str += '<And xmlns="http://www.opengis.net/ogc">';
                for (var j in ruleObj.more) cnt++;
                for (var i = 0; i < cnt; i++) {
                    if (cnt >= 2) str += "<And>";
                    str += "<PropertyIsGreaterThanOrEqualTo>";
                    str += "<ogc:PropertyName>";
                    str += ruleObj.filterName_more[i];
                    str += "</ogc:PropertyName>";
                    str += "<Literal>";
                    str += ruleObj.more[i];
                    str += "</Literal>";
                    str += "</PropertyIsGreaterThanOrEqualTo>";
                    str += "<PropertyIsLessThan>";
                    str += "<ogc:PropertyName>";
                    str += ruleObj.filterName_less[i];
                    str += "</ogc:PropertyName>";
                    str += "<Literal>";
                    str += ruleObj.less[i];
                    str += "</Literal>";
                    str += "</PropertyIsLessThan>";
                    if (cnt >= 2) str += "</And>"
                }
                str += "</And>"
            } else if (ruleObj.more)
                for (var i in ruleObj.more) {
                    str += "<PropertyIsGreaterThanOrEqualTo>";
                    str += "<ogc:PropertyName>";
                    str += ruleObj.filterName_more[i];
                    str += "</ogc:PropertyName>";
                    str += "<Literal>";
                    str += ruleObj.more[i];
                    str += "</Literal>";
                    str += "</PropertyIsGreaterThanOrEqualTo>"
                } else if (ruleObj.less)
                    for (var i in ruleObj.less) {
                        str += "<PropertyIsLessThan>";
                        str += "<ogc:PropertyName>";
                        str += ruleObj.filterName_less[i];
                        str += "</ogc:PropertyName>";
                        str += "<Literal>";
                        str += ruleObj.less[i];
                        str += "</Literal>";
                        str += "</PropertyIsLessThan>"
                    }
                if (ruleObj.filterName_equal && ruleObj.filterName_greater || ruleObj.filterName_equal && ruleObj.more) str += "</ogc:And>";
            str += "</ogc:Filter>"
        }
        str += "<se:MinScaleDenominator>" + ruleObj.minScale +
            "</se:MinScaleDenominator>";
        str += "<se:MaxScaleDenominator>" + ruleObj.maxScale + "</se:MaxScaleDenominator>";
        if (ruleObj.symbolizer["polygon"]) {
            polygons = ruleObj.symbolizer["polygon"];
            str += this.writePolySym(polygons)
        }
        if (ruleObj.symbolizer["line"]) {
            lines = ruleObj.symbolizer["line"];
            str += this.writeLineSym(lines);
            for (var i = 0, len = 5; i < len; i++)
                if (ruleObj.symbolizer["line" + i]) {
                    lines = ruleObj.symbolizer["line" + i];
                    str += this.writeLineSym(lines)
                }
        }
        if (ruleObj.symbolizer["point"]) {
            points = ruleObj.symbolizer["point"];
            str +=
                this.writePointSym(points)
        }
        if (ruleObj.symbolizer["text"]) {
            texts = ruleObj.symbolizer["text"];
            str += this.writeTextSym(texts)
        }
        str += "</se:Rule>";
        return str
    },
    writePointSym: function(points) {
        var str;
        str = '<se:PointSymbolizer version="' + points.version + '">';
        str += "<se:Name>" + points.name + "</se:Name>";
        if (points.name == "CharMarker" || points.name == "CharMarkerAngle") {
            str += "<se:Graphic>";
            str += "<se:Mark>";
            str += '<sld:VendorOption name="font-family">' + points.markFont + "</sld:VendorOption>";
            str += '<sld:VendorOption name="char-code">' +
                points.markCharCode + "</sld:VendorOption>";
            str += '<sld:VendorOption name="fill">' + points.markFill + "</sld:VendorOption>";
            str += "</se:Mark>";
            str += "<se:Opacity>" + points.opacity + "</se:Opacity>";
            str += "<se:Size>" + points.size + "</se:Size>";
            if (points.name == "CharMarkerAngle") {
                str += "<se:Rotation>";
                str += "<ogc:PropertyName>";
                str += points.rotation;
                str += "</ogc:PropertyName>";
                str += "</se:Rotation>"
            }
            str += "<se:Displacement>";
            str += "<se:DisplacementX>" + points.displacementX + "</se:DisplacementX>";
            str += "<se:DisplacementY>" +
                points.displacementY + "</se:DisplacementY>";
            str += "</se:Displacement>"
        } else {
            str += '<se:Graphic><se:ExternalGraphic><se:InlineContent encoding="' + points.encoding + '">' + points.externalGraphic + "</se:InlineContent>";
            str += "<se:Format>" + points.format + "</se:Format>";
            str += "</se:ExternalGraphic>";
            str += "<se:Opacity>" + points.opacity + "</se:Opacity>";
            str += "<se:Size>" + points.size + "</se:Size>";
            str += "<se:Rotation>" + points.rotation + "</se:Rotation>";
            str += "<se:Displacement><se:DisplacementX>" + points.displacementX + "</se:DisplacementX>";
            str += "<se:DisplacementY>" + points.displacementY + "</se:DisplacementY></se:Displacement>"
        }
        str += "</se:Graphic></se:PointSymbolizer>";
        return str
    },
    writeLineSym: function(lines) {
        var str;
        str = '<se:LineSymbolizer version="' + lines.version + '">';
        str += "<se:Name>" + lines.name + "</se:Name>";
        str += "<se:Stroke>";
        if (lines.stroke) str += '<se:SvgParameter name="stroke">' + lines.stroke + "</se:SvgParameter>";
        if (lines.strokeOpacity) str += '<se:SvgParameter name="stroke-opacity">' + lines.strokeOpacity + "</se:SvgParameter>";
        if (lines.strokeWidth) str +=
            '<se:SvgParameter name="stroke-width">' + lines.strokeWidth + "</se:SvgParameter>";
        if (lines.strokeLinejoin) str += '<se:SvgParameter name="stroke-linejoin">' + lines.strokeLinejoin + "</se:SvgParameter>";
        if (lines.strokeLinecap) str += '<se:SvgParameter name="stroke-linecap">' + lines.strokeLinecap + "</se:SvgParameter>";
        if (lines.strokeDasharray && lines.strokeDasharray != "solid") str += '<se:SvgParameter name="stroke-dasharray">' + lines.strokeDasharray + "</se:SvgParameter>";
        str += "</se:Stroke>";
        if (lines.cap_direction && lines.cap_angle) {
            str +=
                '<se:VendorOption name="cap_direction">' + lines.cap_direction + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_angle">' + lines.cap_angle + "</se:VendorOption>";
            str += '<se:VendorOption name="start_cap">' + lines.start_cap + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_style">' + lines.cap_style[0] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_size">' + lines.cap_size[0] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_color">' + lines.cap_color[0] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_fill">' +
                lines.cap_fill[0] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_position">' + lines.cap_position[0] + "</se:VendorOption>";
            str += '<se:VendorOption name="end_cap">' + lines.end_cap + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_style">' + lines.cap_style[1] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_size">' + lines.cap_size[1] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_color">' + lines.cap_color[1] + "</se:VendorOption>";
            str += '<se:VendorOption name="cap_fill">' + lines.cap_fill[1] +
                "</se:VendorOption>";
            str += '<se:VendorOption name="cap_position">' + lines.cap_position[1] + "</se:VendorOption>"
        }
        str += "</se:LineSymbolizer>";
        return str
    },
    writePolySym: function(polygons) {
        var str;
        str = '<se:PolygonSymbolizer version="' + polygons.version + '">';
        str += "<se:Name>" + polygons.name + "</se:Name>";
        str += "<se:Fill>";
        if (polygons.fillColor) str += '<se:SvgParameter name="fill">' + polygons.fillColor + "</se:SvgParameter>";
        if (polygons.fillOpacity) str += '<se:SvgParameter name="fill-opacity">' + polygons.fillOpacity + "</se:SvgParameter>";
        str += "</se:Fill>";
        str += "</se:PolygonSymbolizer>";
        return str
    },
    writeTextSym: function(texts) {
        var str;
        str = '<se:TextSymbolizer version="' + texts.version + '">';
        str += "<se:Name>" + texts.name + "</se:Name>";
        if (texts.label) str += "<se:Label><ogc:PropertyName>" + texts.label + "</ogc:PropertyName></se:Label>";
        else str += "<se:Label/>";
        str += "<se:Font>";
        if (texts.fontSize) str += '<se:SvgParameter name="font-size">' + texts.fontSize + "</se:SvgParameter>";
        if (texts.fontWeight) str += '<se:SvgParameter name="font-weight">' + texts.fontWeight +
            "</se:SvgParameter>";
        if (texts.fontFamily) str += '<se:SvgParameter name="font-family">' + texts.fontFamily + "</se:SvgParameter>";
        if (texts.fontStyle) str += '<se:SvgParameter name="font-style">' + texts.fontStyle + "</se:SvgParameter>";
        str += "</se:Font>";
        str += "<se:LabelPlacement>";
        if (texts.displacementX || texts.displacementY) {
            str += "<se:PointPlacement><se:Displacement>";
            if (texts.displacementX) str += "<se:DisplacementX>" + texts.displacementX + "</se:DisplacementX>";
            if (texts.displacementY) str += "<se:DisplacementY>" + texts.displacementY +
                "</se:DisplacementY>";
            str += "</se:Displacement></se:PointPlacement>"
        }
        if (texts.text_arrange_pos || texts.text_arrange_line || texts.text_arrange_gap) {
            str += "<se:LinePlacement>";
            if (texts.text_arrange_pos) str += '<se:VendorOption name="text_arrange_pos">' + texts.text_arrange_pos + "</se:VendorOption>";
            if (texts.text_arrange_line) str += '<se:VendorOption name="text_arrange_line">' + texts.text_arrange_line + "</se:VendorOption>";
            if (texts.text_arrange_gap) str += '<se:VendorOption name="text_arrange_gap">' + texts.text_arrange_gap +
                "</se:VendorOption>";
            str += "</se:LinePlacement>"
        }
        str += "</se:LabelPlacement>";
        if (texts.radius) {
            str += "<se:Halo>";
            str += "<se:Radius>" + texts.radius + "</se:Radius>";
            str += "<se:Fill>";
            if (texts.haloColor) str += '<se:SvgParameter name="fill">' + texts.haloColor + "</se:SvgParameter>";
            if (texts.haloOpacity) str += '<se:SvgParameter name="fill-opacity">' + texts.haloOpacity + "</se:SvgParameter>";
            str += "</se:Fill>";
            str += "</se:Halo>"
        }
        if (texts.fillColor) {
            str += "<se:Fill>";
            if (texts.fillColor) str += '<se:SvgParameter name="fill">' +
                texts.fillColor + "</se:SvgParameter>";
            if (texts.fillOpacity) str += '<se:SvgParameter name="fill-opacity">' + texts.fillOpacity + "</se:SvgParameter>";
            str += "</se:Fill>"
        }
        str += "</se:TextSymbolizer>";
        return str
    },
    searchNamedLayer: function(layerName) {
        var data = this.data;
        var namedLayers = data.namedLayers;
        var result;
        for (var i = 0, len = namedLayers.length; i < len; i++)
            if (namedLayers[i].name == layerName) return namedLayers[i]
    },
    searchRule: function(ruleName, chk) {
        var data = this.data;
        var namedLayers = data.namedLayers;
        var result;
        if (chk) {
            var res = [];
            var cnt = 0;
            for (var i = 0, len = namedLayers.length; i < len; i++) {
                var userStyle = namedLayers[i].userStyle;
                for (var j = 0, jLen = userStyle.length; j < jLen; j++) {
                    var rules = userStyle[j].rules;
                    for (var k = 0, kLen = rules.length; k < kLen; k++)
                        if (rules[k].name == ruleName) {
                            res.push(rules[k]);
                            res.push(i);
                            cnt++
                        }
                }
            }
            if (cnt > 1)
                for (var i = 0, len = res.length; i < len; i = i + 2) {
                    if (namedLayers[res[i + 1]].name == chk) return res[i]
                } else if (cnt == 1) return res[0]
        } else
            for (var i = 0, len = namedLayers.length; i < len; i++) {
                var userStyle = namedLayers[i].userStyle;
                for (var j =
                        0, jLen = userStyle.length; j < jLen; j++) {
                    var rules = userStyle[j].rules;
                    for (var k = 0, kLen = rules.length; k < kLen; k++)
                        if (rules[k].name == ruleName) return rules[k]
                }
            }
    },
    regNamedLayers: function(data, namedObj) {
        data.namedLayers.push(namedObj);
        return data
    },
    regUserStyle: function(namedObj, userdObj) {
        namedObj.userStyle.push(userdObj);
        return namedObj
    },
    regRules: function(userdObj, ruleObj) {
        userdObj.rules.push(ruleObj);
        return userdObj
    },
    regSymbolizer: function(ruleObj, symbolObj) {
        if (symbolObj.type == "point") ruleObj.symbolizer["point"] =
            symbolObj;
        else if (symbolObj.type == "line") ruleObj.symbolizer["line"] = symbolObj;
        else if (symbolObj.type == "polygon") ruleObj.symbolizer["polygon"] = symbolObj;
        else if (symbolObj.type == "text") ruleObj.symbolizer["text"] = symbolObj;
        return ruleObj
    },
    createMapTopic: function(params) {
        var data = {
            xml: params.xml,
            name: params.name,
            namedLayers: []
        };
        return data
    },
    createNamedLayer: function(params) {
        var namedObj = {
            name: params.name,
            title: params.title,
            featureTypeName: params.featureTypeName,
            userStyle: []
        };
        return namedObj
    },
    createUserStyle: function(params) {
        var userdObj = {
            name: params.name,
            title: params.title,
            rules: []
        };
        return userdObj
    },
    createRule: function(params, symbol) {
        var ruleObj = {
            name: params.name,
            filterName: params.filterName,
            filterLiteral: params.Literal,
            minScale: params.minScale,
            maxScale: params.maxScale,
            symbolizer: symbol
        };
        return ruleObj
    },
    createSymbolizer: function(params, type) {
        var pointObj;
        var lineObj;
        var polygonObj;
        var textObj;
        var symbolObj;
        if (type == "point") {
            if (params.name == "CharMarker" || params.name == "CharMarkerAngle") pointObj = {
                markFill: params.markFill,
                markFont: params.markFont,
                markCharCode: params.markCharCode,
                type: "point",
                version: params.version,
                name: params.name,
                size: params.size,
                opacity: params.opacity,
                rotation: params.rotation,
                displacementX: params.displacementX,
                displacementY: params.displacementY
            };
            else pointObj = {
                type: "point",
                version: params.version,
                name: params.name,
                size: params.size,
                opacity: params.opacity,
                rotation: params.rotation,
                displacementX: params.displacementX,
                displacementY: params.displacementY,
                externalGraphic: params.externalGraphic,
                encoding: params.encoding,
                format: params.format
            };
            symbolObj = pointObj
        } else if (type == "line") {
            lineObj = {
                type: "line",
                version: params.version,
                name: params.name,
                stroke: params.stroke,
                strokeWidth: params.strokeWidth,
                strokeOpacity: params.strokeOpacity,
                strokeLinecap: params.strokeLinecap,
                strokeDasharray: params.strokeDasharray,
                strokeLinejoin: params.strokeLinejoin
            };
            symbolObj = lineObj
        } else if (type == "polygon") {
            polygonObj = {
                type: "polygon",
                version: params.version,
                name: params.name,
                fillColor: params.fillColor,
                fillOpacity: params.fillOpacity
            };
            symbolObj = polygonObj
        } else if (type ==
            "text") {
            textObj = {
                type: "text",
                version: params.version,
                name: params.name,
                label: params.label,
                fontFamily: params.fontFamily,
                fontSize: params.fontSize,
                fontStyle: params.fontStyle,
                fontWeight: params.fontWeight,
                displacementX: params.displacementX,
                displacementY: params.displacementY,
                text_arrange_pos: params.text_arrange_pos,
                text_arrange_line: params.text_arrange_line,
                text_arrange_gap: params.text_arrange_gap,
                radius: params.radius,
                haloColor: params.haloColor,
                haloOpacity: params.haloOpacity,
                fillColor: params.fillColor,
                fillOpacity: params.fillOpacity
            };
            symbolObj = textObj
        }
        return symbolObj
    },
    updateNamedLayer: function(nameObj) {
        var hasNamedLayer = false;
        var namedLayers = this.data.namedLayers;
        for (var i = 0, len = namedLayers.length; i < len; i++)
            if (namedLayers[i].name == nameObj.name) {
                namedLayers[i] = nameObj;
                hasNamedLayer = true
            }
        if (!hasNamedLayer) namedLayers.push(nameObj)
    },
    delNamedLayer: function(layerName) {
        var hasNamedLayer = false;
        var namedLayers = this.data.namedLayers;
        for (var i = 0, len = namedLayers.length; i < len; i++)
            if (namedLayers[i].name == layerName) {
                hasNamedLayer = true;
                namedLayers.splice(i,
                    1);
                alert("\ud574\ub2f9 NamedLayer\ub97c \uc0ad\uc81c\ud558\uc600\uc2b5\ub2c8\ub2e4.")
            }
        if (!hasNamedLayer) alert("\ud574\ub2f9 NamedLayer\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.")
    },
    delRule: function(ruleName) {
        var hasRule = false;
        var namedLayers = this.data.namedLayers;
        for (var i = 0, len = namedLayers.length; i < len; i++) {
            var userStyle = namedLayers[i].userStyle;
            for (var j = 0, jLen = userStyle.length; j < jLen; j++) {
                var rules = userStyle[j].rules;
                for (var k = 0, kLen = rules.length; k < kLen; k++)
                    if (rules[k].name == ruleName) {
                        hasRule = true;
                        rules.splice(k,
                            1);
                        alert("\ud574\ub2f9 Rule\uc744 \uc0ad\uc81c\ud558\uc600\uc2b5\ub2c8\ub2e4.")
                    }
            }
        }
        if (!hasRule) alert("\ud574\ub2f9  Rule\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")
    },
    delSymbolizer: function(SymbolName) {
        var hasSymbol = false;
        var namedLayers = this.data.namedLayers;
        for (var i = 0, len = namedLayers.length; i < len; i++) {
            var userStyle = namedLayers[i].userStyle;
            for (var j = 0, jLen = userStyle.length; j < jLen; j++) {
                var rules = userStyle[j].rules;
                for (var k = 0, kLen = rules.length; k < kLen; k++) {
                    var symbol = rules[k].symbolizer;
                    if (symbol["point"] &&
                        symbol["point"].name == SymbolName) {
                        delete symbol["point"];
                        hasSymbol = true
                    } else if (symbol["line"] && symbol["line"].name == SymbolName) {
                        delete symbol["line"];
                        hasSymbol = true
                    } else if (symbol["polygon"] && symbol["polygon"].name == SymbolName) {
                        delete symbol["polygon"];
                        hasSymbol = true
                    } else if (symbol["text"] && symbol["text"].name == SymbolName) {
                        delete symbol["text"];
                        hasSymbol = true
                    }
                }
            }
        }
        if (!hasSymbol) alert("\ud574\ub2f9 Symbolizer\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
        else alert("\ud574\ub2f9 Symbolizer\ub97c \uc0ad\uc81c\ud558\uc600\uc2b5\ub2c8\ub2e4.")
    },
    CLASS_NAME: "GSLDTool"
});