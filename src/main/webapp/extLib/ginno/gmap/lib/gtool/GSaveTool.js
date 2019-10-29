GSaveTool = OpenLayers.Class( {

	map : null,

	xml : null,

	style : null,

	initialize : function(map) {
		this.map = map;
	},

	getXML : function(mashupLayer) {
		this.xml = "<LAYERS>";

		this.parseMap();
		this.parseLayer();
		this.parseVector();
		this.parsePopup();
		this.parseMashupLayer(mashupLayer);

		this.xml += "</LAYERS>";
		return this.xml;
	},

	parseMap : function() {
		this.xml += "<MAP>";

		var params = {
			left : this.map.getExtent().left,
			bottom : this.map.getExtent().bottom,
			right : this.map.getExtent().right,
			top : this.map.getExtent().top,
			width : this.map.getSize().w,
			height : this.map.getSize().h,
			resolution : this.map.getResolution()
		};

		this.write(params);

		this.xml += "</MAP>";
	},

	parseLayer : function() {
		for ( var i = 0; i < this.map.layers.length; i++) {
			if ((this.map.layers[i].CLASS_NAME == "GWMS" || this.map.layers[i].CLASS_NAME == "GWMSPost") && this.map.layers[i].visibility) {
				this.xml += '<LAYER type="wms">';

				var params = {
					url : this.map.layers[i].url,
					layers : this.map.layers[i].params.LAYERS,
					styles : this.map.layers[i].params.STYLES,
					format : this.map.layers[i].params.FORMAT,
					version : this.map.layers[i].params.VERSION,
					crs : this.map.layers[i].params.CRS,
					service : this.map.layers[i].params.SERVICE,
					request : this.map.layers[i].params.REQUEST,
					exceptions : this.map.layers[i].params.EXCEPTIONS
				};
				/*

				if(this.map.layers[i].params.SLD_BODY) {
					params.sldbody = this.map.layers[i].params.SLD_BODY;
				};
*/
				this.write(params);

				this.xml += "</LAYER>";
			}
			else if (this.map.layers[i].CLASS_NAME == "GTileCache" && this.map.layers[i].visibility) {
				this.xml += '<LAYER type="tilecache">';

				params = {
					url : this.map.layers[i].url,
					layername : this.map.layers[i].layername,
					scaleLevel : this.map.getZoom()+2,
					maxLeft : this.map.layers[i].maxExtent.left,
					maxBottom : this.map.layers[i].maxExtent.bottom,
					maxRight : this.map.layers[i].maxExtent.right,
					maxTop : this.map.layers[i].maxExtent.top,
					extension : "."+ this.map.layers[i].format.split('/')[1].toLowerCase()
				};

				this.write(params);

				this.xml += "</LAYER>";
			}
			else if(this.map.layers[i].CLASS_NAME == "OpenLayers.Layer.ArcGISCache" && this.map.layers[i].visibility) {
				this.xml += '<LAYER type="ArcGISCache">';

				var res = this.map.layers[i].getResolution();
				var start = this.map.layers[i].getUpperLeftTileCoord(res);
		        var end = this.map.layers[i].getLowerRightTileCoord(res);

		        var numTileCols = (end.x - start.x) + 1;
		        var numTileRows = (end.y - start.y) + 1;
				params = {
					url : this.map.layers[i].url,
					tileOriginLon : this.map.layers[i].tileOrigin.lon,
					tileOriginLat : this.map.layers[i].tileOrigin.lat,
					minRows : numTileRows,
					minCols : numTileCols,
					centerX : this.map.getExtent().getCenterLonLat().lon,
					centerY : this.map.getExtent().getCenterLonLat().lat,
					scaleLevel : this.map.getZoom(),
					maxLeft : this.map.layers[i].maxExtent.left,
					maxBottom : this.map.layers[i].maxExtent.bottom,
					maxRight : this.map.layers[i].maxExtent.right,
					maxTop : this.map.layers[i].maxExtent.top
				};

				this.write(params);

				this.xml += "</LAYER>";
			}
		}
	},

	parseVector : function() {
		for(var i=0; i < this.map.layers.length; i++) {
			if(this.map.layers[i].CLASS_NAME == "GVector" && this.map.layers[i].visibility) {

				this.xml += "<VECTORLAYER>";
				for(var j=0; j < this.map.layers[i].features.length; j++) {
					if(this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
						/*if(!this.map.layers[i].features[j].attributes.featureType) {
							continue;
						}*/
						var x = this.map.layers[i].features[j].geometry.x;
						var y = this.map.layers[i].features[j].geometry.y;
						var ex = this.map.getExtent();
						if ( x > ex.left && x < ex.right && y > ex.bottom && y < ex.top ) {


						var params = {};
						var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);

						if(/*this.map.layers[i].features[j].attributes.featureType == "Image"*/style.externalGraphic) {

							// 2018.02.23 YYK. 마커있는 벡터레이어 추가
							if(this.map.layers[i].features[j].layer.name == "GAttrLayer"
								|| this.map.layers[i].features[j].layer.name == "GAttrLayerMulti"
								|| this.map.layers[i].features[j].layer.name == "GStatusLayer"
								|| this.map.layers[i].features[j].layer.name == "GTypeLayer"
								|| this.map.layers[i].features[j].layer.name == "SttemntLayer"
								|| this.map.layers[i].features[j].layer.name == "DmgtLayer"
								|| this.map.layers[i].features[j].layer.name == "GOverlapLayer"


								){

								params = {
										x : this.map.layers[i].features[j].geometry.x,
										y : this.map.layers[i].features[j].geometry.y,
										featureType : "Image",
										opacity : 1,
										width : style.graphicWidth,
										height : style.graphicHeight,
										image : style.externalGraphic
										,angle : style.rotation
										,rotation : style.rotation
									};

							}
							else{
								params = {
									x : this.map.layers[i].features[j].geometry.x,
									y : this.map.layers[i].features[j].geometry.y,
									featureType : "Image",
									opacity : style.fillOpacity,
									width : style.graphicWidth,
									height : style.graphicHeight,
									image : style.externalGraphic
								};
							}
						}
						else if (/*this.map.layers[i].features[j].attributes.featureType == "Point"*/style.graphicName) {

							if(!(style.graphicName == "circle" || style.graphicName == "square")) {
								style.graphicName == "circle";
							}

							// YYK radius 값 없을경우 0
							var radius = 0 ;
							if ( style.pointRadius != "" && style.pointRadius != "undefined" ) {
								radius = style.pointRadius
							}

							params = {
								x : this.map.layers[i].features[j].geometry.x,
								y : this.map.layers[i].features[j].geometry.y,
								featureType : "Point",
								radius : radius

								,
								graphicName : style.graphicName,
								stroke : style.strokeWidth,
								color : style.strokeColor,
								opacity : style.strokeOpacity,
								fillColor : style.fillColor,
								fillOpacity : style.fillOpacity
							};

						}

						this.xml += '<FEATURE type="point">';

						this.write(params);

						this.xml += "</FEATURE>";
						}
					}
					else if(this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {

						var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);

						if(this.map.layers[i].features[j].attributes.featureType == "arrow") {
							this.xml += '<FEATURE type="arrow">';
						}
						else {
							this.xml += '<FEATURE type="lineString">';
						}

						var x = [];
						var y = [];

						for(var k = 0; k < this.map.layers[i].features[j].geometry.components.length; k++) {
							x.push(this.map.layers[i].features[j].geometry.components[k].x);
							y.push(this.map.layers[i].features[j].geometry.components[k].y);
						}

						var params = {
							x : x,
							y : y,
							color : style.strokeColor,
							opacity : style.strokeOpacity,
							stroke : style.strokeWidth,
							strokeDashstyle : style.strokeDashstyle,
							strokeLinecap : style.strokeLinecap
						};

						this.write(params);

						this.xml += "</FEATURE>";
					}
					else if(this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.Polygon") {
						var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);

						this.xml += '<FEATURE type="polygon">';

						var x = [];
						var y = [];

						for(var k = 0; k < this.map.layers[i].features[j].geometry.components[0].components.length; k++) {
							x.push(this.map.layers[i].features[j].geometry.components[0].components[k].x);
							y.push(this.map.layers[i].features[j].geometry.components[0].components[k].y);
						}

						var params = {
							x : x,
							y : y,
							color : style.strokeColor,
							opacity : style.strokeOpacity,
							stroke : style.strokeWidth,
							fillColor : style.fillColor,
							fillOpacity : style.fillOpacity,
							label : style.label,
							fontColor : style.fontColor,
							centerX : this.map.layers[i].features[j].geometry.getCentroid().x,
							centerY : this.map.layers[i].features[j].geometry.getCentroid().y,
							strokeDashstyle : style.strokeDashstyle,
							strokeLinecap : style.strokeLinecap
						};
						this.write(params);

						this.xml += "</FEATURE>";
					}
					else if(this.map.layers[i].features[j].geometry.CLASS_NAME == "OpenLayers.Geometry.MultiPolygon") {
						var style = this.map.layers[i].parseStyle(this.map.layers[i].features[j]);

						var feature = this.map.layers[i].features[j];

						for(var k=0; k < feature.geometry.components.length; k++) {
							this.xml += '<FEATURE type="polygon">';

							var x = [];
							var y = [];

							var polygonComponent = feature.geometry.components[k];

							for(var l=0; l < polygonComponent.components[0].components.length; l++) {
								x.push(polygonComponent.components[0].components[l].x);
								y.push(polygonComponent.components[0].components[l].y);
							}

							var params = {
								x : x,
								y : y,
								color : style.strokeColor,
								opacity : style.strokeOpacity,
								stroke : style.strokeWidth,
								fillColor : style.fillColor,
								fillOpacity : style.fillOpacity,
								label : style.label,
								fontColor : style.fontColor,
								centerX : this.map.layers[i].features[j].geometry.getCentroid().x,
								centerY : this.map.layers[i].features[j].geometry.getCentroid().y,
								strokeDashstyle : style.strokeDashstyle,
								strokeLinecap : style.strokeLinecap
							};

							this.write(params);

							this.xml += "</FEATURE>";
						}
					}
				}

				this.xml += "</VECTORLAYER>";
			}
		}
	},

	parsePopup : function() {
		if(this.map.popups.length > 0) this.xml += "<POPUPS>";

		for(var i=0; i < this.map.popups.length; i++) {
			if(this.map.popups[i].attributes && this.map.popups[i].attributes.print) {
				this.xml += "<POPUP>";

				if(!this.map.popups[i].attributes.fontFamily) {
					this.map.popups[i].attributes.fontFamily = "굴림";
				}
				if(!this.map.popups[i].attributes.fontSize) {
					this.map.popups[i].attributes.fontSize = "12";
				}
				if(!this.map.popups[i].attributes.fontColor)
					this.map.popups[i].attributes.fontColor = "#000000";

				var params = {
					x : this.map.popups[i].lonlat.lon,
					y : this.map.popups[i].lonlat.lat,
					width : $(this.map.popups[i].contentDiv).css("width").replace("px",""),
					height : $(this.map.popups[i].contentDiv).css("height").replace("px",""),
					text : this.map.popups[i].attributes.text,
					fontFamily : this.map.popups[i].attributes.fontFamily,
					fontSize : this.map.popups[i].attributes.fontSize.replace("px", ""),
					fontColor : this.map.popups[i].attributes.fontColor
				};

				this.write(params);

				this.xml += "</POPUP>";
			}
		}

		if(this.map.popups.length > 0) this.xml += "</POPUPS>";
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

	write : function(obj) {
		for ( var i in obj) {
			this.xml += "<" + i + ">" + encodeURIComponent(obj[i])
					+ "</" + i + ">";
		}
	},

	CLASS_NAME : "GSaveTool"
});