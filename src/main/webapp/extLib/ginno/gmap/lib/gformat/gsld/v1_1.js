/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Rule.js
 * @requires OpenLayers/Format/SLD.js
 * @requires OpenLayers/Format/Filter/v1_1_0.js
 */

/**
 * Class: OpenLayers.Format.SLD.v1
 * Superclass for SLD version 1 parsers.
 *
 * Inherits from:
 *  - <OpenLayers.Format.Filter.v1_1_0>
 */
OpenLayers.Format.SLD.v1_1 = OpenLayers.Class(OpenLayers.Format.Filter.v1_1_0, {

    /**
     * Property: namespaces
     * {Object} Mapping of namespace aliases to namespace URIs.
     */
    namespaces: {
        sld: "http://www.opengis.net/sld",
    	se:"http://www.opengis.net/se",
        ogc: "http://www.opengis.net/ogc",
        gml: "http://www.opengis.net/gml",
        xlink: "http://www.w3.org/1999/xlink",
        xsi: "http://www.w3.org/2001/XMLSchema-instance",
        version : "1.1.0"
    },

    /**
     * Property: defaultPrefix
     * se 로 해야하나?
     */
    defaultPrefix: "se",

    /**
     * Property: schemaLocation
     * {String} Schema location for a particular minor version.
     */
    schemaLocation: null,

    /**
     * APIProperty: defaultSymbolizer.
     * {Object} A symbolizer with the SLD defaults.
     */
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

    /**
     * Constructor: OpenLayers.Format.SLD.v1_1
     * Instances of this class are not created directly.  Use the
     *     <OpenLayers.Format.SLD> constructor instead.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be set on
     *     this instance.
     */
    initialize: function(options) {
        OpenLayers.Format.Filter.v1_1_0.prototype.initialize.apply(this, [options]);
    },

    /**
     * Method: read
     *
     * Parameters:
     * data - {DOMElement} An SLD document element.
     * options - {Object} Options for the reader.
     *
     * Valid options:
     * namedLayersAsArray - {Boolean}  Generate a namedLayers array.  If false,
     *     the namedLayers property value will be an object keyed by layer name.
     *     Default is false.
     *
     * Returns:
     * {Object} An object representing the SLD.
     */
    read: function(data, options) {
        options = OpenLayers.Util.applyDefaults(options, this.options);
        //options.namedLayersAsArray = true; // 수정
       // alert("options" +options.namedLayersAsArray);
        var sld = {
        		 namedLayers: options.namedLayersAsArray === true ? [] : {}
        };
        this.readChildNodes(data, sld);
        return sld;
    },

    /**
	* Method: readOgcExpression
	* Limited support for OGC expressions.
	*
	* Parameters:
	* node - {DOMElement} A DOM element that contains an ogc:expression.
	*
	* Returns:
	* {String} A value to be used in a symbolizer.
	*/
    readOgcExpression: function(node) {
       var obj = {};
       this.readChildNodes(node, obj);
       var value = obj.value;
       if(value === undefined) {
           value = this.getChildValue(node);
       }
       return value;
   },


    /**
     * Property: readers
     * Contains public functions, grouped by namespace prefix, that will
     *     be applied when a namespaced node is found matching the function
     *     name.  The function will be applied in the scope of this parser
     *     with two arguments: the node being read and a context object passed
     *     from the parent.
     */
    readers: OpenLayers.Util.applyDefaults(	{
        "sld": {
            "StyledLayerDescriptor": function(node, sld) {
                sld.version = node.getAttribute("version");
                this.readChildNodes(node, sld);

            },
            "NamedLayer": function(node, sld) {
            	//alert("V1.1");
            	var layer = {
                    userStyles: [],
                    namedStyles:[]
                };

                this.readChildNodes(node, layer);
                // give each of the user styles this layer name
                for(var i=0, len=layer.userStyles.length; i<len; ++i) {
                    layer.userStyles[i].layerName = layer.name;
                }
                if(sld.namedLayers instanceof Array) {
                	sld.namedLayers.push(layer);
                } else {
                    sld.namedLayers[layer.name] = layer;
                }


            },
            "NamedStyle": function(node, layer) {
                layer.namedStyles.push(
                    this.getChildName(node.firstChild)
                );
            },
            "UserStyle": function(node, layer) {
                var obj = {defaultsPerSymbolizer: true, rules: []};
                this.readChildNodes(node, obj);
                var style = new OpenLayers.Style(this.defaultSymbolizer, obj);
                layer.userStyles.push(style);
            },
            "LayerFeatureConstraints" : function (node , layer){
            	//alert("LayerFeatureConstraints");
            	  this.readChildNodes(node, layer);
            },
            "FeatureTypeConstraint" : function (node , layer)
            {
            	this.readChildNodes(node, layer);
            	// alert("FeatureTypeConstraint "  + layer.LayerFeatureConstraints);
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
                 obj.name = this.getChildValue(node);
             },
             "Title": function(node, obj) {
                 obj.title = this.getChildValue(node);
             },
             "Description": function(node, obj) {
                 obj.description = this.getChildValue(node);
             },
             "IsDefault": function(node, style) {
                 if(this.getChildValue(node) == "1") {
                     style.isDefault = true;
                 }
             },

             "CoverageStyle":function(node , style){
            	 var obj = {
                         rules: []
                     };
            	 this.readChildNodes(node, obj);
                 style.rules = obj.rules;
             },
             "FeatureTypeName": function(node, layer) {
            	 layer.LayerFeatureConstraints = this.getChildValue(node);
             },
             "FeatureTypeStyle": function(node, style) {
                 // OpenLayers doesn't have a place for FeatureTypeStyle
                 // Name, Title, Abstract, FeatureTypeName, or
                 // SemanticTypeIdentifier so, we make a temporary object
                 // and later just use the Rule(s).
                 var obj = {
                     rules: []
                 };
                 this.readChildNodes(node, obj);
                 style.rules = obj.rules;
             },
             "Rule": function(node, obj) {
                 var rule = new OpenLayers.Rule();
                 this.readChildNodes(node, rule);
                 obj.rules.push(rule);
             },
             "ElseFilter": function(node, rule) {
                 rule.elseFilter = true;
             },
             "MinScaleDenominator": function(node, rule) {
                 rule.minScaleDenominator = parseFloat(this.getChildValue(node));
             },
             "MaxScaleDenominator": function(node, rule) {
                 rule.maxScaleDenominator = parseFloat(this.getChildValue(node));
             },
             "TextSymbolizer": function(node, rule) {
                 // OpenLayers doens't do painter's order, instead we extend
                 var symbolizer = rule.symbolizer["Text"] || {};
                 this.readChildNodes(node, symbolizer);
                 // in case it didn't exist before
                 rule.symbolizer["Text"] = symbolizer;
             },
             "Label": function(node, symbolizer) {
                 // only supporting literal or property name
                 var obj = {};
                 this.readChildNodes(node, obj);
                 if(obj.property) {
                     symbolizer.label = "${" + obj.property + "}";
                 } else {
                     var value = this.readOgcExpression(node);
                     if(value) {
                         symbolizer.label = value;
                     }
                 }
             },
             "Font": function(node, symbolizer) {
               this.readChildNodes(node, symbolizer);
             },

             "Halo": function(node, symbolizer) {
                 // halo has a fill, so send fresh object
                 var obj = {};
                 this.readChildNodes(node, obj);
                 symbolizer.haloRadius = obj.haloRadius;
                 symbolizer.haloColor = obj.fillColor;
                 symbolizer.haloOpacity = obj.fillOpacity;
             },
             "Radius": function(node, symbolizer) {
                 var radius = this.readOgcExpression(node);
                 if(radius != null) {
                     // radius is only used for halo
                     symbolizer.haloRadius = radius;
                 }
             },

             "LabelPlacement" : function(node, symbolizer) {
            	 this.readChildNodes(node, symbolizer);
             },
             "LinePlacement": function(node, symbolizer) {
                 this.readChildNodes(node, symbolizer);
             },
             "PointPlacement" : function(node, symbolizer) {
            	 this.readChildNodes(node, symbolizer);
             },
             "Displacement" : function(node, symbolizer) {
            	 this.readChildNodes(node, symbolizer);
             },
             "DisplacementX" : function(node, symbolizer) {
            	 var displacementX = this.readOgcExpression(node);
                 if(displacementX != null) {
                     // radius is only used for halo
                     symbolizer.displacementX = displacementX;
                 }
             },
             "DisplacementY" : function(node, symbolizer) {
            	 var displacementY = this.readOgcExpression(node);
                 if(displacementY != null) {
                     // radius is only used for halo
                     symbolizer.displacementY = displacementY;
                 }
             },

             "PolygonSymbolizer": function(node, rule) {
                 // OpenLayers doens't do painter's order, instead we extend
                 var symbolizer = rule.symbolizer["Polygon"] || {};
                 this.readChildNodes(node, symbolizer);
                 // in case it didn't exist before

                 rule.symbolizer["Polygon"] = symbolizer;
             },
             "LineSymbolizer": function(node, rule) {
                 // OpenLayers doesn't do painter's order, instead we extend
                 var symbolizer = rule.symbolizer["Line"] || {};
                 this.readChildNodes(node, symbolizer);
                 // in case it didn't exist before
                 rule.symbolizer["Line"] = symbolizer;
             },
             "PointSymbolizer": function(node, rule) {
                 // OpenLayers doens't do painter's order, instead we extend
                 var symbolizer = rule.symbolizer["Point"] || {};
                 this.readChildNodes(node, symbolizer);
                 // in case it didn't exist before
                 rule.symbolizer["Point"] = symbolizer;
             },"RasterSymbolizer": function(node, rule){
            	 var symbolizer = rule.symbolizer["Raster"] || {};
                 this.readChildNodes(node, symbolizer);
                 // in case it didn't exist before
                 rule.symbolizer["Raster"] = symbolizer;
             },
             "SvgParameter":function(node, symbolizer){
            	 var Property = node.getAttribute("name");
                 var symProperty = this.cssMap[Property];
                 if(symProperty) {
                     // Limited support for parsing of OGC expressions
                     var value = this.readOgcExpression(node);
                     // always string, could be an empty string
                     if(value) {
                         symbolizer[symProperty] = value;
                     }
                 }
                 },
             "Stroke": function(node, symbolizer) {
                 symbolizer.stroke = true;
                 this.readChildNodes(node, symbolizer);
             },
             "Fill": function(node, symbolizer) {
                 symbolizer.fill = true;
                 this.readChildNodes(node, symbolizer);
             },
             "CssParameter": function(node, symbolizer) {
                 var cssProperty = node.getAttribute("name");
                 var symProperty = this.cssMap[cssProperty];
                 if(symProperty) {
                     // Limited support for parsing of OGC expressions
                     var value = this.readOgcExpression(node);
                     // always string, could be an empty string
                     if(value) {
                         symbolizer[symProperty] = value;
                     }
                 }
             },
             "Graphic": function(node, symbolizer) {
                 symbolizer.graphic = true;
                 var graphic = {};
                 // painter's order not respected here, clobber previous with next
                 this.readChildNodes(node, graphic);
                 // directly properties with names that match symbolizer properties
                 var properties = [
                     "strokeColor", "strokeWidth", "strokeOpacity",
                     "strokeLinecap", "strokeLinejoin", "fillColor", "fillOpacity",
                     "graphicName", "rotation", "graphicFormat"  ,
                     "graphicContent" , "href", "angleScale", "angleTranslation"
                 ];
                 var prop, value;
                 for(var i=0, len=properties.length; i<len; ++i) {
                     prop = properties[i];
                     value = graphic[prop];
                     if(value != undefined) {

                    		 symbolizer[prop] = value;
                     }
                 }
                 // set other generic properties with specific graphic property names
                 if(graphic.opacity != undefined) {
                     symbolizer.graphicOpacity = graphic.opacity;
                 }
                 if(graphic.size != undefined) {
                     symbolizer.pointSize= graphic.size ;
                 }
                 if(graphic.href != undefined) {
                     symbolizer.externalGraphic = graphic.href;
                 }
                 if(graphic.rotation != undefined) {
                     symbolizer.rotation = graphic.rotation;
                 }
                 if(graphic.angleScale != undefined) {
                	 symbolizer.angleScale = graphic.angleScale;
                 }
                 if(graphic.angleTranslation != undefined) {
                	 symbolizer.angleTranslation = graphic.angleTranslation;
                 }
             },
             "ExternalGraphic": function(node, graphic) {
                 this.readChildNodes(node, graphic);
             },
             "Mark": function(node, graphic) {
                 this.readChildNodes(node, graphic);
             },
             "WellKnownName": function(node, graphic) {
                 graphic.graphicName = this.getChildValue(node);
             },
             "Opacity": function(node, obj) {
                 var opacity = this.readOgcExpression(node);
                 // always string, could be empty string
                 if(opacity) {
                     obj.opacity = opacity;
                 }
             },
             "Size": function(node, obj) {
                 var size = this.readOgcExpression(node);
                 // always string, could be empty string
                 if(size) {
                     obj.size = size;
                 }
             },
             "Rotation": function(node, obj) {
                 var rotation = this.readOgcExpression(node.firstChild);
                 // always string, could be empty string
                 if(rotation) {
                     obj.rotation = rotation;
                 }
             },
             "OnlineResource": function(node, obj) {
                 obj.href = this.getAttributeNS(
                     node, this.namespaces.xlink, "href"
                 );
             },
             "Format": function(node, graphic) {
                 graphic.graphicFormat = this.getChildValue(node);
             },
             "InlineContent":function(node, graphic){
            	 graphic.graphicContent = this.getChildValue(node);
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

    /**
     * Property: cssMap
     * {Object} Object mapping supported css property names to OpenLayers
     *     symbolizer property names.
     */
    cssMap: {
        "stroke": "strokeColor",
        "stroke-opacity": "strokeOpacity",
        "stroke-width": "strokeWidth",
        "stroke-linecap": "strokeLinecap",
        "stroke-linejoin":"strokeLinejoin",
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

    /**
     * Method: getCssProperty
     * Given a symbolizer property, get the corresponding CSS property
     *     from the <cssMap>.
     *
     * Parameters:
     * sym - {String} A symbolizer property name.
     *
     * Returns:
     * {String} A CSS property name or null if none found.
     */
    getCssProperty: function(sym) {
        var css = null;
        for(var prop in this.cssMap) {
            if(this.cssMap[prop] == sym) {
                css = prop;
                break;
            }
        }
        return css;
    },

    /**
     * Method: getGraphicFormat
     * Given a href for an external graphic, try to determine the mime-type.
     *     This method doesn't try too hard, and will fall back to
     *     <defautlGraphicFormat> if one of the known <graphicFormats> is not
     *     the file extension of the provided href.
     *
     * Parameters:
     * href - {String}
     *
     * Returns:
     * {String} The graphic format.
     */
    getGraphicFormat: function(href) {
        var format, regex;
        for(var key in this.graphicFormats) {
            if(this.graphicFormats[key].test(href)) {
                format = key;
                break;
            }
        }
        return format || this.defautlGraphicFormat;
    },

    /**
     * Property: defaultGraphicFormat
     * {String} If none other can be determined from <getGraphicFormat>, this
     *     default will be returned.
     */
    defaultGraphicFormat: "image/png",

    /**
     * Property: graphicFormats
     * {Object} Mapping of image mime-types to regular extensions matching
     *     well-known file extensions.
     */
    graphicFormats: {
        "image/jpeg": /\.jpe?g$/i,
        "image/gif": /\.gif$/i,
        "image/png": /\.png$/i
    },

    /**
     * Method: write
     *
     * Parameters:
     * sld - {Object} An object representing the SLD.
     *
     * Returns:
     * {DOMElement} The root of an SLD document.
     */


    write: function(sld) {
        return this.writers.sld.StyledLayerDescriptor.apply(this, [sld]);
    },

    /* Property: writers
     * As a compliment to the readers property, this structure contains public
     *     writing functions grouped by namespace alias and named like the
     *     node names they produce.
     *     {
     *
     */

    writers: OpenLayers.Util.applyDefaults({
        "sld": {
            "StyledLayerDescriptor": function(sld) {

    			/*var url ="xmlns:ogc=" + this.namespaces.ogc;
    			url +=" xmlns:se=" +  + " xmlns:xlink=" + this.namespaces["xlink"];
    			url +=" xmlns:xsi=" + this.namespaces["xsi"] + " xmlns:sld="+ this.namespaces["sld"];
    			url +=" version='1.1.0'";*/ //xsi:schemaLocation='' */


                 var root = this.createElementNSPlus(
                    "sld:StyledLayerDescriptor",
                    {attributes: {
//YYK. ie9 에서 오류날 경우 아래 사용
                    	"version": this.namespaces.version
                   //     "extend": "STANDARD_MAP",
//                    	"se": this.namespaces["se"],
//                    	"xlink":  this.namespaces["xlink"],
//                    	"xsi" :this.namespaces["xsi"] ,
                    	,"ogc" :this.namespaces["ogc"]
//                    	"xsi:schemaLocation": this.schemaLocation ? this.schemaLocation : ""
                    	 }
                    });

/*
                 root.setAttribute(null, "xmlns:se", this.namespaces.se);
                 root.setAttribute(null, "xmlns:xlink", this.namespaces.xlink);
                 root.setAttribute(null, "xmlns:xsi", this.namespaces.xsi);
                 root.setAttribute(null, "xmlns:sld", this.namespaces.sld);
                 root.setAttribute(null, "xmlns:ogc", this.namespaces.ogc);
*/
                 //root.null = "xmlns:ogc";

//    			 var root = this.createElementNS( url ,"sld:StyledLayerDescriptor");

                /*add in optional name
                 * "xsi:schemaLocation": this.schemaLocation
                 * */

                if(sld.name) {
                    this.writeNode("Name", sld.name, root);
                }

                //add in optional title
                if(sld.title) {
                    this.writeNode("title", sld.title, root);
                }

                // add in optional description
            	this.writeNode("Description", sld.description, root);

                // add in named layers
                // allow namedLayers to be an array
                if(sld.namedLayers instanceof Array) {
                    for(var i=0, len=sld.namedLayers.length; i<len; ++i) {
                        this.writeNode("sld:NamedLayer", sld.namedLayers[i], root);
                    }
                } else {
                    for(var name in sld.namedLayers) {
                        this.writeNode("sld:NamedLayer", sld.namedLayers[name], root);
                    }
                }

                return root;
            },
            "NamedLayer": function(layer) {
                var node = this.createElementNSPlus("sld:NamedLayer");

                // add in required name
                this.writeNode("se:Name", layer.name, node);

                this.writeNode("se:Description", layer.description, node);

                // optional sld:LayerFeatureConstraints here
                    if(layer.LayerFeatureConstraints) {
                        this.writeNode("sld:LayerFeatureConstraints", layer.LayerFeatureConstraints, node);
                    }

                // add in named styles
                if(layer.namedStyles) {
                    for(var i=0, len=layer.namedStyles.length; i<len; ++i) {
                        this.writeNode(
                            "sld:NamedStyle", layer.namedStyles[i], node
                        );
                    }
                }

                // add in user styles
                if(layer.userStyles) {
                    for(var i=0, len=layer.userStyles.length; i<len; ++i) {
                        this.writeNode(
                            "sld:UserStyle", layer.userStyles[i], node
                        );
                    }
                }


                return node;
            },

            "NamedStyle": function(name) {
                var node = this.createElementNSPlus("sld:NamedStyle");
                this.writeNode("se:Name", name, node);
                return node;
            },
            "UserStyle": function(style) {
                var node = this.createElementNSPlus("sld:UserStyle");

                // add in optional name
                if(style.name) {
                    this.writeNode("se:Name", style.name, node);
                }
                // add in optional title
                if(style.title) {
                    this.writeNode("se:Title", style.title, node);
                }
                // add in optional description
                this.writeNode("se:Description", style.description, node);

                // add isdefault
                if(style.isDefault) {
                    this.writeNode("se:IsDefault", style.isDefault, node);
                }

                // add FeatureTypeStyles
                this.writeNode("se:FeatureTypeStyle", style, node);

                return node;
            	} ,
            "LayerFeatureConstraints" : function ( FeatureConstraints){
            	var node = this.createElementNSPlus("sld:LayerFeatureConstraints");
           	 	this.writeNode("sld:FeatureTypeConstraint", FeatureConstraints, node);
           	 	return node;

            },
            "FeatureTypeConstraint" : function (constranint) {
            	var node = this.createElementNSPlus("sld:FeatureTypeConstraint");
            	this.writeNode("se:FeatureTypeName", constranint, node);
             	return node;
             },
             "Name": function(name) {
                 return this.createElementNSPlus("se:Name", {value: name});
             },
             "Title": function(title) {
                 return this.createElementNSPlus("se:Title", {value: title});
             },
             "Description" : function (title){
             	var node = this.createElementNSPlus("se:Description");
             	this.writeNode("Title", title, node);
             	return node;
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
                return this.createElementNSPlus("se:Name", {value: name});
            },
            "Title": function(title) {
                return this.createElementNSPlus("se:Title", {value: title});
            },
            "Description" : function (title){
            	var node = this.createElementNSPlus("se:Description");
            	 this.writeNode("Title", title, node);
            	 return node;
            },
            "Abstract": function(description) {
                return this.createElementNSPlus(
                    "Abstract", {value: description}
                );
            },
            "IsDefault": function(bool) {
                return this.createElementNSPlus(
                    "sd:IsDefault", {value: (bool) ? "1" : "0"}
                );
            },
            "FeatureTypeName" : function (typename){
            	 return this.createElementNSPlus(
                         "se:FeatureTypeName", {value: typename}
                     );
            },

            "FeatureTypeStyle": function(style) {
                var node = this.createElementNSPlus("se:FeatureTypeStyle");

                // OpenLayers currently stores no Name, Title, Abstract,
                // FeatureTypeName, or SemanticTypeIdentifier information
                // related to FeatureTypeStyle

                // add in rules
                for(var i=0, len=style.rules.length; i<len; ++i) {
                    this.writeNode("Rule", style.rules[i], node);
                }

                return node;
            },
            "Rule": function(rule) {
                var node = this.createElementNSPlus("se:Rule");

                // add in optional name
                if(rule.name) {
                    this.writeNode("Name", rule.name, node);
                }
                /*add in optional title
                if(rule.title) {
                    this.writeNode("Title", rule.title, node);
                }
                // add in optional description
                if(rule.description) {
                    this.writeNode("Abstract", rule.description, node);
                }*/

                // add in LegendGraphic here

                // add in optional filters
                if(rule.elseFilter) {
                    this.writeNode("ElseFilter", null, node);
                } else if(rule.filter) {
                    this.writeNode("ogc:Filter", rule.filter, node);
                }

                // add in scale limits
                if(rule.minScaleDenominator != undefined) {
                    this.writeNode(
                        "MinScaleDenominator", rule.minScaleDenominator, node
                    );
                }
                if(rule.maxScaleDenominator != undefined) {
                    this.writeNode(
                        "MaxScaleDenominator", rule.maxScaleDenominator, node
                    );
                }

                // add in symbolizers (relies on geometry type keys)
                var types = OpenLayers.Style.SYMBOLIZER_PREFIXES;
                var type, symbolizer;
                for(var i=0, len=types.length; i<len; ++i) {
                    type = types[i];
                    symbolizer = rule.symbolizer[type];
                    if(symbolizer) {
                        this.writeNode(
                            type + "Symbolizer", symbolizer, node
                        );
                    }
                }
                return node;

            },
            "ElseFilter": function() {
                return this.createElementNSPlus("se:ElseFilter");
            },
            "MinScaleDenominator": function(scale) {
                return this.createElementNSPlus(
                    "se:MinScaleDenominator", {value: scale}
                );
            },
            "MaxScaleDenominator": function(scale) {
                return this.createElementNSPlus(
                    "se:MaxScaleDenominator", {value: scale}
                );
            },

            "LineSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:LineSymbolizer" ,
                		 {attributes: {"version": this.VERSION  }} );
                if (symbolizer.name)
                	this.writeNode("Name" , symbolizer.name , node);

                this.writeNode("Stroke", symbolizer, node);
                return node;
            },
            "Stroke": function(symbolizer) {
                var node = this.createElementNSPlus("se:Stroke");

                // GraphicFill here

                // GraphicStroke here

                // add in CssParameters
                if(symbolizer.strokeColor != undefined) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "strokeColor"},
                        node
                    );
                }
                if(symbolizer.strokeOpacity != undefined) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "strokeOpacity"},
                        node
                    );
                }
                if(symbolizer.strokeWidth != undefined) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "strokeWidth"},
                        node
                    );
                }

                if(symbolizer.strokeLinecap != undefined) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "strokeLinecap"},
                        node
                    );
                }

                if(symbolizer.strokeLinejoin != undefined) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "strokeLinejoin"},
                        node
                    );
                }
                    if(symbolizer.strokeDashstyle != undefined) {
                        this.writeNode(
                            "SvgParameter",
                            {symbolizer: symbolizer, key: "strokeDashstyle"},
                            node
                        );
                    }

                return node;
            },
            "SvgParameter": function(obj) {
                // not handling ogc:expressions for now
                return this.createElementNSPlus("se:SvgParameter", {
                    attributes: {name: this.getCssProperty(obj.key)},
                    value: obj.symbolizer[obj.key]
                });
            },
            "CssParameter": function(obj) {
                // not handling ogc:expressions for now
                return this.createElementNSPlus("se:CssParameter", {
                    attributes: {name: this.getCssProperty(obj.key)},
                    value: obj.symbolizer[obj.key]
                });
            },
            "TextSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:TextSymbolizer" ,{attributes: {
            		"version": this.VERSION
        		} });
                if (symbolizer.name)
                	this.writeNode("Name" , symbolizer.name , node);
                // add in optional Label
                if(symbolizer.label != null) {
                    this.writeNode("Label", symbolizer.label, node);
                }
                // add in optional Font
                if(symbolizer.fontFamily != null ||
                   symbolizer.fontSize != null ||
                   symbolizer.fontWeight != null ||
                   symbolizer.fontStyle != null) {
                    this.writeNode("Font", symbolizer, node);
                }
                // add in optional Halo

                if(symbolizer.haloRadius != null ||
                   symbolizer.haloColor != null ||
                   symbolizer.haloOpacity != null) {
                    this.writeNode("Halo", symbolizer, node);
                }
                // add in optional Fill
                if(symbolizer.fillColor != null ||
                   symbolizer.fillOpacity != null) {
                    this.writeNode("Fill", symbolizer, node);
                }

                // add in optional LabelPlacement
                if(symbolizer.displacementX != null &&
                		symbolizer.displacementY != null || symbolizer.name == "LineText") {
                    this.writeNode("LabelPlacement", symbolizer, node);
                }

                // Vendor Optin 추가
                if (symbolizer.textPointBase != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointBase"}, node);
                if (symbolizer.textPointPosition != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointPosition"}, node);
                if (symbolizer.textPointArrange != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"textPointArrange"}, node);
                if (symbolizer.backgroundType != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundType"}, node);
                if (symbolizer.backgroundFill != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundFill"}, node);
                if (symbolizer.backgroundLine != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundLine"}, node);
                if (symbolizer.backgroundOffset != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundOffset"}, node);
                if (symbolizer.backgroundAlign != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"backgroundAlign"}, node);
                if (symbolizer.codeDomain != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"codeDomain"}, node);
                return node;
            },
            "LabelPlacement" : function(symbolizer) {
            	var node = this.createElementNSPlus("se:LabelPlacement");
            	if(symbolizer.name == "LineText") {
                    this.writeNode("LinePlacement", symbolizer, node);

                }
            	else if(symbolizer.displacementX != null &&
                		symbolizer.displacementY != null) {
            		this.writeNode("PointPlacement", symbolizer, node);
            	}

            	return node;
            },
            "LinePlacement" : function(symbolizer) {
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
            "PointPlacement" : function(symbolizer) {
            	var node = this.createElementNSPlus("se:PointPlacement");
            	if(symbolizer.displacementX != null &&
                		symbolizer.displacementY != null) {
                    this.writeNode("Displacement", symbolizer, node);

                }
            	return node;
            },
            "Displacement" : function(symbolizer) {
            	var node = this.createElementNSPlus("se:Displacement");
            	if(symbolizer.displacementX != null &&
                		symbolizer.displacementY != null) {
                    this.writeNode("DisplacementX", symbolizer, node);
                    this.writeNode("DisplacementY", symbolizer, node);
                }
            	return node;
            },
            "DisplacementX" : function(symbolizer) {
            	return node = this.createElementNSPlus("se:DisplacementX", {
            		value : symbolizer.displacementX
            	});
            },
            "DisplacementY" : function(symbolizer) {
            	return node = this.createElementNSPlus("se:DisplacementY", {
            		value : symbolizer.displacementY
            	});
            },


            "Font": function(symbolizer) {
                var node = this.createElementNSPlus("se:Font");
                // add in CssParameters
                if(symbolizer.fontFamily) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fontFamily"},
                        node
                    );
                }
                if(symbolizer.fontStyle) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fontStyle"},
                        node
                    );
                }
                if(symbolizer.fontWeight) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fontWeight"},
                        node
                    );
                }
                if(symbolizer.fontSize) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fontSize"},
                        node
                    );
                }
                return node;
            },
            "Label": function(label) {
                // only the simplest of ogc:expression handled
                // {label: "some text and a ${propertyName}"}
            	   var node = this.createElementNSPlus("se:Label");
                   var tokens = label.split("${");
                   node.appendChild(this.createTextNode(tokens[0]));
                   var item, last;
                   for(var i=1, len=tokens.length; i<len; i++) {
                       item = tokens[i];
                       last = item.indexOf("}");
                       if(last > 0) {
                           this.writeNode(
                               "ogc:PropertyName",
                               {property: item.substring(0, last)},
                               node
                           );
                           node.appendChild(
                               this.createTextNode(item.substring(++last))
                           );
                       } else {
                           // no ending }, so this is a literal ${
                           node.appendChild(
                               this.createTextNode("${" + item)
                           );
                       }
                   }
                   return node;
            },
            "Halo": function(symbolizer) {
                var node = this.createElementNSPlus("se:Halo");
                if(symbolizer.haloRadius) {
                    this.writeNode("Radius", symbolizer.haloRadius, node);
                }
                if(symbolizer.haloColor || symbolizer.haloOpacity) {
                    this.writeNode("Fill", {
                        fillColor: symbolizer.haloColor,
                        fillOpacity: symbolizer.haloOpacity
                    }, node);
                }
                return node;
            },
            "Radius": function(value) {
                return node = this.createElementNSPlus("se:Radius", {
                    value: value
                });
            },
            "RasterSymbolizer":function (symbolizer){
            	   var node = this.createElementNSPlus("se:PolygonSymbolizer" ,{attributes: {
               		"version": this.VERSION
           		} });
            	   if (symbolizer.name)
                   	this.writeNode("Name" , symbolizer.name , node);

            	   if (symbolizer.Opacity != undefined)
            	   {
            		   this.writeNode("Opacity", symbolizer, node);
            	   }

                   return node;
            },
            "PolygonSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:PolygonSymbolizer" ,{attributes: {
            		"version": this.VERSION
        		} });

                if (symbolizer.name)
                	this.writeNode("Name" , symbolizer.name , node);

                if(symbolizer.fillColor != undefined ||
                   symbolizer.fillOpacity != undefined) {
                    this.writeNode("Fill", symbolizer, node);
                }
                if(symbolizer.strokeWidth != undefined ||
                   symbolizer.strokeColor != undefined ||
                   symbolizer.strokeOpacity != undefined ||
                   symbolizer.strokeDashstyle != undefined) {
                    this.writeNode("Stroke", symbolizer, node);
                }


                return node;
            },
            "Fill": function(symbolizer) {
                var node = this.createElementNSPlus("se:Fill");

                // GraphicFill here

                // add in CssParameters
                if(symbolizer.fillColor) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fillColor"},
                        node
                    );
                }
                if(symbolizer.fillOpacity != null) {
                    this.writeNode(
                        "SvgParameter",
                        {symbolizer: symbolizer, key: "fillOpacity"},
                        node
                    );
                }
                return node;
            },
            "PointSymbolizer": function(symbolizer) {
                var node = this.createElementNSPlus("se:PointSymbolizer" ,{attributes: {
            		"version": this.VERSION
        		}} );

                if (symbolizer.name)
                	this.writeNode("Name" , symbolizer.name , node);

                this.writeNode("Graphic", symbolizer, node);
                return node;
            },
            "Graphic": function(symbolizer) {
            	if (symbolizer.graphic == true )
            	{
	                var node = this.createElementNSPlus("se:Graphic");

	                if(symbolizer.graphicContent != undefined  || symbolizer.href != undefined) {
	                    this.writeNode("ExternalGraphic", symbolizer, node);
	                } else {
	                    this.writeNode("Mark", symbolizer, node);
	                }
	                if(symbolizer.pointSize != undefined) {
	                    this.writeNode("Size", symbolizer.pointSize, node);
	                }
	                if(symbolizer.graphicOpacity != undefined) {
	                    this.writeNode("Opacity", symbolizer.graphicOpacity, node);
	                }
	                if(symbolizer.rotation != undefined) {
	                	this.writeNode("se:Rotation", symbolizer.rotation, node);
	                }
	                if(symbolizer.angleScale != undefined) {
	                	this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"angleScale"}, node);
	                }
	                if(symbolizer.angleTranslation != undefined) {
	                	this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"angleTranslation"}, node);
	                }
                return node;
            	}
            },
            "ExternalGraphic": function(symbolizer) {
                var node = this.createElementNSPlus("se:ExternalGraphic");
                /*
                this.writeNode(
                    "OnlineResource", symbolizer.externalGraphic, node
                );
                var str = symbolizer.graphicContent;

                alert("처음" + str);
                str.replace(/+/g, " ");
                alert('+    ' +str);
                str.replace(/=/g, " ");
                alert('=    ' +str);
                str.replace(///g, " ");
                alert('/    ' + str);*/

               // alert("ExternalGraphic");

               // this.writeNode("InlineContent" ,encodeURIComponent(symbolizer.graphicContent) , node);
                if(!symbolizer.href){
                	this.writeNode("InlineContent" ,symbolizer.graphicContent , node);
                } else {
                	this.writeNode("OnlineResource", symbolizer.href, node);
                }

                var format = symbolizer.graphicFormat || this.getGraphicFormat(symbolizer.externalGraphic);
                this.writeNode("Format", format, node);
                return node;
            },
            "Mark": function(symbolizer) {
                var node = this.createElementNSPlus("se:Mark");
                if(symbolizer.graphicName) {
                    this.writeNode("WellKnownName", symbolizer.graphicName, node);
                }
                /*this.writeNode("Fill", symbolizer, node);
                this.writeNode("Stroke", symbolizer, node);*/
                if (symbolizer.charCode != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"charCode"}, node);
                if (symbolizer.fillColor != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"fillColor"}, node);
                if (symbolizer.fontFamily != null) this.writeNode("sld:VendorOption", {symbolizer: symbolizer, key:"fontFamily"}, node);
                return node;
            },
            "WellKnownName": function(name) {
                return this.createElementNSPlus("se:WellKnownName", {
                    value: name
                });
            },
            "Opacity": function(value) {
                return this.createElementNSPlus("se:Opacity", {
                    value: value
                });
            },
            "Size": function(value) {
                return this.createElementNSPlus("se:Size", {
                    value: value
                });
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
                });
            },
            "Format": function(format) {
                return this.createElementNSPlus("se:Format", {
                    value: format
                });
           	},
            "InlineContent": function(Content) {
                return this.createElementNSPlus("se:InlineContent", {
                	attributes: {encoding: "base64"},
                    value: Content
                });
           	}
            }
         },
       OpenLayers.Format.Filter.v1_1_0.prototype.writers),
    CLASS_NAME: "OpenLayers.Format.SLD.v1_1"

});