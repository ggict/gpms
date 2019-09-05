var GRequest = {};

OpenLayers.Util.extend(GRequest,OpenLayers.Request);

GRequest.WMS = {

	service : "WMS",

	//version : "1.3.0",
	version : "1.1.1",

	request : null,

	//format : new OpenLayers.Format.SLD.v1_1_0(),
	//format : new OpenLayers.Format.SLD.v1_0_0(),
	format : new OpenLayers.Format.SLD.v1_0_0_GeoServer(),

	getCapability : function(serviceUrl, callback) {
		var params = {
			service : this.service,
			version : this.version,
			request : "GetCapabilities"
		};

		var obj = this;
		GMapUtil.sendProxyGet(serviceUrl, GUtil.fn_convert_objToStr(params), function(res) {
			obj.parseGetCapability(res, callback);
		});
	},

	parseGetCapability : function(res, callback) {
		var arr = [];

		var totalLayers = res.getElementsByTagName("Layer");

		for(var i=0, len=totalLayers.length; i < len; i++) {
			var grpLayers = totalLayers[i].getElementsByTagName("Layer");
			if(grpLayers.length > 0 && totalLayers[i].getElementsByTagName("Title")[0].text != "BASIC") {
				var groupArr = {
					title : totalLayers[i].getElementsByTagName("Title")[0].text,
					layers : []
				};

				for(var j=0, jLen=grpLayers.length; j < jLen; j++) {
					var obj = {
						name :  grpLayers[j].getElementsByTagName("Name")[0].text,
						style : grpLayers[j].getElementsByTagName("Style")[0].text,
						title : grpLayers[j].getElementsByTagName("Title")[0].text,
						left : grpLayers[j].getElementsByTagName("westBoundLongitude")[0].text,
						bottom : grpLayers[j].getElementsByTagName("southBoundLatitude")[0].text,
						right : grpLayers[j].getElementsByTagName("eastBoundLongitude")[0].text,
						top : grpLayers[j].getElementsByTagName("northBoundLatitude")[0].text
					};
					groupArr.layers.push(obj);
				}

				arr.push(groupArr);
			}
		}

		callback(arr);
	},

	getLengendGraphic : function(serviceUrl, parameters) {
		var params = {
			service : this.service,
			version : this.version,
			request : "GetLegendGraphic",
			layer : "",
			style : "",
			rule : "",
			sld_version : "1.1.0",
			format : "image/png",
			width : 16,
			height : 16
		};

		$.extend(params, parameters);
		return serviceUrl + GUtil.fn_convert_objToStr(params);
	},

	getStyles : function(serviceUrl, layers, callback) {
		var params = {
			service : this.service,
			//version : this.version,
			version : '1.1.1',
			request : "GetStyles",
			layers : layers
		};

		var obj = this;
		GMapUtil.sendProxyPost(serviceUrl, GUtil.fn_convert_objToStr(params), function(res)
			{
				obj.parseGetStyles(res, callback);
			//	callback(new GSLDTool(res,"xml"));
			}
		);
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
			//	callback(new GSLDTool(res,"xml"));
			}
		);
	},

	parseGetStyles : function(res, callback) {
		var obj = {
			xml : res,
			name : "",
			namedLayers : []
		};

		var sld = this.format.read(res);
		var name = sld.name;

		if(typeof name != "undefined" && name.length > 0) {
			obj.name = name;
		}

		var namedLayers = sld.namedLayers;

		for(var i in namedLayers) {
			var namedObj = {
				name : "",
				title : "",
				featureTypeName : "",
				userStyle : []
			};

			var name = namedLayers[i].name;
			if(name.length > 0) namedObj.name = name;

			if(namedLayers[i].hasOwnProperty('description')){
				var description = namedLayers[i].description;
				if(description.length > 0) {
					namedObj.title = description.title;
				}
			}else{
				namedObj.title = name;
			}

			if(namedLayers[i].hasOwnProperty('LayerFeatureConstraints')){
				var layerFeatureConstraints = namedLayers[i].LayerFeatureConstraints;
				if(layerFeatureConstraints.length > 0) {
					namedObj.featureTypeName = layerFeatureConstraints;
				}
			}else{
				namedObj.featureTypeName = name;
			}

			var userStyles = namedLayers[i].userStyles;
			for(var j = 0, jLen = userStyles.length; j < jLen; j++) {
				var userdObj = {
					name : "",
					title : "",
					rules : []
				};

				var name = userStyles[j].name;
				if(name.length > 0) userdObj.name = name;

				if(userStyles[j].hasOwnProperty('description')){
					var description = userStyles[j].description;
					if(description.length > 0)  userdObj.title = description;
				}else{
					userdObj.title = name;
				}

				var layerName = userStyles[j].layerName;
				if(layerName.length > 0) userdObj.title = layerName;

				var rules = userStyles[j].rules;

				for(var k = 0, kLen = rules.length; k < kLen; k++) {
					var ruleObj = {
						name : "",
						minScale : "",
						maxScale : "",
						symbolizer : {},
						filter : {}
					};

					// 룰 이름
					ruleObj.name = rules[k].name;

					// 최소 축척
					ruleObj.minScale = rules[k].minScaleDenominator;

					// 최대 축척
					ruleObj.maxScale = rules[k].maxScaleDenominator;

					// 필터
					var filter = rules[k].filter;
					if(filter) {

						//단일조건일 경우
						if(filter.property && filter.value) {
							ruleObj.filter.type = filter.type;
							ruleObj.filter.property = filter.property;
							ruleObj.filter.value = filter.value;
						}
						else if(filter.filters && (filter.typpe == "&&" || filter.typpe == "||" )) { //And 또는  Or를 이용한 다중조건일 경우
							ruleObj.filter.filters = filter.filters;
						}

					}

					var points = rules[k].symbolizer["Point"];
					var lines = rules[k].symbolizer["Line"];
					var polygons = rules[k].symbolizer["Polygon"];
					var texts = rules[k].symbolizer["Text"];

					if(points) {
						var pointObj = {};

						var size = points.pointSize;
						if(size.length > 0) {
							pointObj["size"] = size;
							if(points.name.indexOf('ImageMarker') != -1) {
								pointObj["opacity"] = points.graphicOpacity;
								// 텍스처 이미지 Base64값 가져오기
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

							pointObj["opacity"] = points.graphicOpacity;
						}

						ruleObj.symbolizer["point"] = pointObj;
					}

					if(lines) {
						var lineObj = {};

						var name = lines.name;
						if(name == "Line") {
							//선 색 strokeColor
							var strokeColor = lines.strokeColor ;
							lineObj["stroke"] = strokeColor;

							//선 두께 strokeWidth
							var strokeWidth = lines.strokeWidth ;
							lineObj["strokeWidth"] = strokeWidth;

							//선 투명도 strokeOpacity
							var strokeOpacity = lines.strokeOpacity ;
							lineObj["strokeOpacity"] = strokeOpacity;

							// 모서리 스타일 strokeLinecap
							var strokeLinecap = lines.strokeLinecap ;
							lineObj["strokeLinecap"] = strokeLinecap;

							//선 스타일 strokeLinecap
							var strokeDasharray = lines.strokeDasharray;
							if(strokeDasharray) {
								//console.log("선 스타일 strokeLinecap 지정안됨!");
							} else {
								lineObj["strokeDashArray"] = "solid";
							}
						} else if(name == "CompositeLineCap") {
							lineObj.arrow = true;
						} else if(name == "CompositeLineMarker") {
							lineObj.marker = true;
						}
						ruleObj.symbolizer["line"] = lineObj;
					}
					if(polygons) {
						var polyObj = {};

						if(polygons.fill) {
							//면색 fillColor
							var fillColor = polygons.fillColor;
							if(fillColor) polyObj["fillColor"] = fillColor;

							//면투명도 fillOpacity
							var fillOpacity = polygons.fillOpacity;
							if(fillOpacity) polyObj["fillOpacity"] = fillOpacity;
						}

						// 텍스처 이미지 Base64값 가져오기
						var externalGraphic = polygons.InlineContent;
						if(externalGraphic) polyObj["externalGraphic"] = externalGraphic;

						if(lines) {
                        	$.extend(true,polyObj,ruleObj.symbolizer.line);
                        	delete ruleObj.symbolizer.line;
                        }
						ruleObj.symbolizer["polygon"] = polyObj;
					}

					if(texts) {
						var textObj = {};

						var label = texts.label;
						if(label.length > 0) {
							textObj.label = label
						}

						//서체 fontFamily
						var fontFamily = texts.fontFamily;
						if(fontFamily) textObj["fontFamily"] = fontFamily;

						//글자 크기 fontSize
						var fontSize = texts.fontSize;
						if(fontSize) textObj["fontSize"] = fontSize;

						//글자 스타일 fontStyle
						var fontStyle = texts.fontStyle;
						if(fontStyle) textObj["fontStyle"] = fontStyle;

						//글자 두께 fontWeight
						var fontWeight = texts.fontWeight;
						if(fontWeight) textObj["fontWeight"] = fontWeight;


						if(texts.fill) {
							//글자 색
							var fillColor = texts.fillColor;
							if(fillColor) textObj["fillColor"] = fillColor;

							//글자 투명도
							var fillOpacity = texts.fillOpacity;
							if(fillOpacity) textObj["fillOpacity"] = fillOpacity;

							//배경 색
							var haloColor = texts.haloColor;
							if(haloColor) textObj["haloColor"] = haloColor;
							//배경 투명도
							var haloOpacity = texts.haloOpacity;
							if(haloOpacity) textObj["haloOpacity"] = haloOpacity;
						}

						// 벤더 옵션 처리
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

						ruleObj.symbolizer["text"] = textObj;
					}
					userdObj.rules.push(ruleObj);
				}
				namedObj.userStyle.push(userdObj);
			}
			obj.namedLayers.push(namedObj);
		}

		// 추가중


		/*var element = res.getElementsByTagName("se:Name");
		if(element.length > 0) {
			obj.name = res.getElementsByTagName("se:Name")[0].textContent;
		}

		var namedLayers = res.getElementsByTagName("sld:NamedLayer");
		for(var i=0, len=namedLayers.length; i < len; i++) {
			var namedObj = {
				name : "",
				title : "",
				featureTypeName : "",
				userStyle : []
			};

			element = namedLayers[i].getElementsByTagName("se:Name");
			if(element.length > 0) namedObj.name = element[0].textContent;

			element = namedLayers[i].getElementsByTagName("se:Description");
			if(element.length > 0) {
				var subElement = element[0].getElementsByTagName("se:Title");
				if(subElement.length > 0) namedObj.title = subElement[0].textContent;
			}

			element = namedLayers[i].getElementsByTagName("sld:LayerFeatureConstraints");
			if(element.length > 0) {
				subElement = element[0].getElementsByTagName("se:FeatureTypeName");
				if(subElement.length > 0) namedObj.featureTypeName = subElement[0].textContent;
			}

			var userStyles = namedLayers[i].getElementsByTagName("sld:UserStyle");
			for(var j=0, jLen=userStyles.length; j < jLen; j++) {
				var userdObj = {
					name : "",
					title : "",
					rules : []
				};

				element = userStyles[j].getElementsByTagName("se:Name");
				if(element.length > 0) userdObj.name = element[0].textContent;

				element = userStyles[j].getElementsByTagName("se:Description");
				if(element.length > 0) {
					subElement = element[0].getElementsByTagName("se:Title");
					if(subElement.length > 0) userdObj.title = subElement[0].textContent;
				}

				element = userStyles[j].getElementsByTagName("se:FeatureTypeStyle");
				if(element.length > 0) {
					subElement = element[0].getElementsByTagName("se:FeatureTypeName");
					if(subElement.length > 0) userdObj.title = subElement[0].textContent;
				}

				var rules = userStyles[j].getElementsByTagName("se:Rule");
				for(var k=0, kLen=rules.length; k < kLen; k++) {
					var ruleObj = {
						name : "",
						minScale : "",
						maxScale : "",
						symbolizer : {}
					};

					element = rules[k].getElementsByTagName("se:Name");
					if(element.length > 0) ruleObj.name = element[0].textContent;
					element = rules[k].getElementsByTagName("se:MinScaleDenominator");
					if(element.length > 0) ruleObj.minScale = element[0].textContent;
					element = rules[k].getElementsByTagName("se:MaxScaleDenominator");
					if(element.length > 0) ruleObj.maxScale = element[0].textContent;

					var points = rules[k].getElementsByTagName("se:PointSymbolizer");
					var lines = rules[k].getElementsByTagName("se:LineSymbolizer");
					var polygons = rules[k].getElementsByTagName("se:PolygonSymbolizer");
					var texts = rules[k].getElementsByTagName("se:TextSymbolizer");

					if(points.length > 0) {
						var pointObj = {};

						var svgParam = points[0].getElementsByTagName("se:Size");
						if(svgParam.length > 0) pointObj["size"] = svgParam[0].textContent;

						ruleObj.symbolizer["point"] = pointObj;
					}

					if(lines.length > 0) {

						var lineObj = {};
						for(var x=0, xLen=lines.length; x < xLen; x++) {
							var lineName = lines[x].getElementsByTagName("se:Name");
							if(lineName[0].textContent == "Line") {
								var svgParam = lines[x].getElementsByTagName("se:SvgParameter");
								for(var l=0, lLen=svgParam.length; l < lLen; l++) {
									//선 색 strokeColor
									if(svgParam[l].getAttribute("name") == "stroke" && svgParam[l].textContent.length > 1) {
										lineObj["stroke"] = svgParam[l].textContent;
									}
									//선 두께 strokeWidth
									else if(svgParam[l].getAttribute("name") == "stroke-width") {
										lineObj["strokeWidth"] = svgParam[l].textContent;
									}
									//선 투명도 strokeOpacity
									else if(svgParam[l].getAttribute("name") == "stroke-opacity") {
										lineObj["strokeOpacity"] = svgParam[l].textContent;
									}
									//모서리 스타일 strokeLinecap
									else if(svgParam[l].getAttribute("name") == "stroke-linecap") {
										lineObj["strokeLinecap"] = svgParam[l].textContent;
									}
									//선 스타일 strokeLinecap
									else if(svgParam[l].getAttribute("name") == "stroke-dasharray") {
										var dashArray = 0;
										var arr = svgParam[l].textContent.split(",");

										//점선 2.0,2.0
										if(parseInt(arr[0]) == 2) {
											dashArray = "dot";
										}
										//파선 7.0,3.0
										else if(parseInt(arr[0]) == 7) {
											dashArray = "dash";
										}
										//일점 쇄선 10.0,2.0,2.0,2.0
										else if(parseInt(arr[0]) == 10) {
											dashArray = "dashdot";
										}
										else {
											dashArray = "solid";
										}


										//이점 쇄선 10.0,2.0,2.0,2.0,2.0,2.0
										else if(dashArray == "10.0,2.0,2.0,2.0,2.0,2.0") {
										}


										lineObj["strokeDashArray"] = dashArray;
									}

									//모서리 스타일 strokeLinecap
									else if(svgParam[l].getAttribute("name") == "stroke-linecap") {
										lineObj["strokeLinecap"] = svgParam[l].text;
									}

								}

								if(!lineObj["strokeDashArray"]) {
									lineObj["strokeDashArray"] = "solid";
								}
							}
							else if(lineName[0].textContent == "CompositeLineCap") {
								lineObj.arrow = true;
							}
							else if(lineName[0].textContent == "CompositeLineMarker") {
								lineObj.marker = true;
							}
						}
						ruleObj.symbolizer["line"] = lineObj;
					};

					if(polygons.length > 0) {
						var polyObj = {};

						svgParam = polygons[0].getElementsByTagName("se:SvgParameter");
						for(l=0, lLen=svgParam.length; l < lLen; l++) {
							//면색 fillColor
							if(svgParam[l].getAttribute("name") == "fill") {
								polyObj["fillColor"] = svgParam[l].textContent;
							}
							//면투명도 fillOpacity
							else if(svgParam[l].getAttribute("name") == "fill-opacity") {
								polyObj["fillOpacity"] = svgParam[l].textContent;
							}
						}
						// 텍스처 이미지 Base64값 가져오기
						var graphic = polygons[0].getElementsByTagName("se:InlineContent");
						if(graphic.length > 0){
							polyObj["externalGraphic"] = graphic[0].textContent;
						}
						ruleObj.symbolizer["polygon"] = polyObj;
					}

					if(texts.length > 0) {
						var textObj = {};

						element = texts[0].getElementsByTagName("se:Label");
						if(element.length > 0) {
							subElement = element[0].getElementsByTagName("PropertyName");
							if(subElement.length > 0) textObj.label = subElement[0].textContent;
						}

						var fonts = texts[0].getElementsByTagName("se:Font");
						if(fonts.length > 0) {
							svgParam = fonts[0].getElementsByTagName("se:SvgParameter");
							for(l=0, lLen=svgParam.length; l < lLen; l++) {
								//서체 fontFamily
								if(svgParam[l].getAttribute("name") == "font-family") {
									textObj["fontFamily"] = svgParam[l].textContent;
								}
								//글자 크기 fontSize
								else if(svgParam[l].getAttribute("name") == "font-size") {
									textObj["fontSize"] = svgParam[l].textContent;
								}
								//글자 스타일 fontStyle
								else if(svgParam[l].getAttribute("name") == "font-style") {
									textObj["fontStyle"] = svgParam[l].textContent;
								}
								//글자 두께 fontWeight
								else if(svgParam[l].getAttribute("name") == "font-weight") {
									textObj["fontWeight"] = svgParam[l].textContent;
								}
							}
						}

						var fill = texts[0].getElementsByTagName("se:Fill");
						for(var l=0; l < fill.length; l++) {
							svgParam = fill[l].getElementsByTagName("se:SvgParameter");

							if(fill[l].previousSibling.nodeName == "se:Halo") {
								for(var m=0, mLen=svgParam.length; m < mLen; m++) {
									if(svgParam[l].getAttribute("name") == "fill") {
										//배경 색
										textObj["haloColor"] = svgParam[m].textContent;
									}
									else if(svgParam[l].getAttribute("name") == "fill-opacity") {
										//배경 투명도
										textObj["haloOpacity"] = svgParam[m].textContent;
									}
								}
							}
							else {
								for(var m=0, mLen=svgParam.length; m < mLen; m++) {
									if(svgParam[m].getAttribute("name") == "fill") {
										//글자 색
										textObj["fillColor"] = svgParam[m].textContent;
									}
									else if(svgParam[m].getAttribute("name") == "fill-opacity") {
										//글자 투명도
										textObj["fillOpacity"] = svgParam[m].textContent;
									}
								}
							}
						}
						var background = texts[0].getElementsByTagName("sld:VendorOption");
						for(var n=0, nLen=background.length ; n < nLen ; n++){
							if(background[n].getAttribute("name") == "background_type"){
								textObj["background_type"] = background[n].textContent;
							}
							else if(background[n].getAttribute("name") == "background_fill"){
								textObj["background_fill"] = background[n].textContent;
							}
							else if(background[n].getAttribute("name") == "background_line"){
								textObj["background_line"] = background[n].textContent;
							}
							else if(background[n].getAttribute("name") == "background_offset"){
								textObj["background_offset"] = background[n].textContent;
							}
							else if(background[n].getAttribute("name") == "background_align"){
								textObj["background_align"] = background[n].textContent;
							}
						}


						ruleObj.symbolizer["text"] = textObj;
					}
					userdObj.rules.push(ruleObj);
				}
				namedObj.userStyle.push(userdObj);
			}
			obj.namedLayers.push(namedObj);
		}*/

		if(callback) {
			callback(obj);
			return true;
		}
		else {
			return obj;
		}
	},

	getFeatureInfo : function(serviceUrl, map, options, callback, callbackParams) {
		var params = {
			service : this.service,
	    	version : this.version,
	    	request : "GetFeatureInfo",
	    	layers : "",
	    	styles : "",
	    	query_layers : "",
	    	info_format : "text/xml",
	    	format : "image/jpeg",
	    	feature_count : 9999,
	    	bbox : map.getExtent().toBBOX(),
	    	i : parseInt(map.getSize().w/2),
	    	j : parseInt(map.getSize().h/2),
	    	height : map.getSize().h,
	    	width : map.getSize().w
		};

		if(options.layers && !options.styles) {
			options.styles = options.layers;
		}
		if(options.layers && !options.query_layers) {
			options.query_layers = options.layers;
		}

		$.extend(params, options);

		var obj = this;
		GMapUtil.sendProxyGet(serviceUrl, GUtil.fn_convert_objToStr(params), function(res) {
			obj.parseGetFeatureInfo(res, callback, callbackParams);
		});
	},

	parseGetFeatureInfo : function(res, callback, callbackParams) {
		var arr = [];

		var layers = res.getElementsByTagName("Layer");

		for (var i = 0, len = layers.length; i < len; i++) {
			var obj = {};

			obj.name = layers[i].getAttribute("name");
			obj.fields = {};

			fields = layers[i].getElementsByTagName("Field");

			for(var j=0, fLen = fields.length; j < fLen; j++) {
				obj.fields[fields[j].getAttribute("name")] = $(fields[j]).text();
			}

			arr.push(obj);
		}

		callback(arr, callbackParams);
	}
};

GRequest.WFS = {

	SERVICES : "WFS",

	VERSION : "1.1.0",

	REQUEST : null,

	format : {
		gml : new OpenLayers.Format.GML(),
		filter : new OpenLayers.Format.Filter({ version : "1.1.0" }),
		xml : new OpenLayers.Format.XML(),
		geojson: new OpenLayers.Format.GeoJSON()
	},

	getCapability : function(serviceUrl, callback) {
		var params = GUtil.fn_convert_objToStr({
			SERVICE : this.SERVICES,
			VERSION : this.VERSION,
			REQUEST : "GetCapabilities"
		});

		GMapUtil.sendProxyGet(serviceUrl, params, function(res) {
			var format = new OpenLayers.Format.WFSCapabilities({version : "1.1.0"});
			callback(format.read(res.xml));
		});
	},

	extendParams : function(params, options) {
		OpenLayers.Util.extend(params, options);

		if(options.tables && !(options.tables instanceof Array)) {
			params.tables = [options.tables];
		}
		if(options.fields && !(options.values instanceof Array)) {
			params.fields = [options.fields];
		}
		if(options.values && !(options.values instanceof Array)) {
			params.values = [options.values];
		}
		if(options.sortFields && !(options.sortFields instanceof Array)) {
			params.sortFields = [options.sortFields];
		}
		if(options.sortOrders && !(options.sortOrders instanceof Array)) {
			params.sortOrders = [options.sortOrders];
		}

	},

	extendParambounds : function(params, options) {
		this.extendParams(params, options);
		if(options.boundaryFields && !(options.boundaryFields instanceof Array)) {
			params.boundaryFields = [options.boundaryFields];
		}
		if(options.boundaryValues && !(options.boundaryValues instanceof Array)) {
			params.boundaryValues = [options.boundaryValues];
		}

	},
	
	getSortBy : function(fields, orders) {
		var str = "";
		
		str += "<ogc:SortBy>";

		for(var i=0, len=fields.length; i < len; i++) {
			str += "<ogc:SortProperty>";
			str += "<ogc:PropertyName>";
			str += fields[i];
			str += "</ogc:PropertyName>";
			str += "<ogc:SortOrder>";
			str += orders[i]?orders[i]:"ASC";
			str += "</ogc:SortOrder>";
			str += "</ogc:SortProperty>";
		}

		str += "</ogc:SortBy>";

		return str;
	},

	getFeatureById : function(serviceUrl, parameters, callback, options) {
		var params = {
			maxFeatures : 9999,
			prefix : "",
			tables : [],
			values : [],
			sortFields : [],
			sortOrders : [],
			useDomain : false
		};

		this.extendParams(params, parameters);

		var queryStr = '';
		for(var i=0, len=params.tables.length; i < len; i++) {
			var useDomain = params.useDomain?'useDomain="true"':'';
			queryStr += '<wfs:Query typeName="' + params.prefix + ':' + params.tables[i] + '" ' + useDomain + '  >';

			if(i < params.values.length)
				queryStr += '<ogc:Filter xmlns:ogc=\"http://www.opengis.net/ogc\"><ogc:FeatureId fid=\"' + params.tables[i] + '.' + params.values[i] + '\"/></ogc:Filter>';

			if(params.sortFields.length > 0)
				queryStr += this.getSortBy(params.sortFields, params.sortOrders);

			queryStr += '</wfs:Query>';
		}

		this.getFeature(serviceUrl, params, queryStr, callback, options);
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
        queryStr += '<wfs:Query typeName="' + params.prefix + ":" + params.tables[0] + '" ' + useDomain + ">";
        queryStr += '<ogc:Filter xmlns:ogc="http://www.opengis.net/ogc">';
        for (var i = 0, len = params.values.length; i < len; i++) {
            queryStr += '<ogc:FeatureId fid="' + params.tables[0] + "." + params.values[i] + '"/>';
        }
        if (params.sortFields.length > 0) queryStr += this.getSortBy(params.sortFields, params.sortOrders);
        queryStr += "</ogc:Filter></wfs:Query>"
        this.getFeature(serviceUrl, params, queryStr, callback, options, sync)
    },

	getFeatureByComparison : function(serviceUrl, parameters, callback, options) {
		var params = {
			maxFeatures : 9999,
			prefix : "",
			type : "==",
			tables : [],
			fields : [],
			values : [],
			sortFields : [],
			sortOrders : [],
			useDomain : false,
			logicalOper : "AND"
		};

		this.extendParams(params, parameters);
		params.fields = GUtil.fn_lowercase(params.fields); //필드명 소문자 치환
		
		var queryStr = '';
		for(var i=0, len=params.tables.length; i < len; i++) {
			var useDomain = params.useDomain?'useDomain="true"':'';
			queryStr += '<wfs:Query typeName="' + params.prefix + ':' + params.tables[i] + '" ' + useDomain + '>';

			var filter;

			if($.isArray(params.fields[i])){
				var filterOpt = [];
				var filterType = OpenLayers.Filter.Logical.AND;

				for(var j=0; j<params.fields[i].length; j++){
				    //180719 wijy
                    //조회 type 추가
                    var sType ="==";
                    if($.isArray(params.type[i])) {
                        sType = params.type[i][j];
                        if(sType == null || sType == '')  sType = '==';
                    }

                    filterOpt.push(new OpenLayers.Filter.Comparison({
                        type : sType,
                        property : params.fields[i][j],
                        value : params.values[i][j]
                    }));
				}

				switch(params.logicalOper){
					case "AND":
						filterType = OpenLayers.Filter.Logical.AND;
						break;
					case "OR":
						filterType = OpenLayers.Filter.Logical.OR;
						break;
				}

				filter = new OpenLayers.Filter.Logical({
                    type: filterType,
                    filters: filterOpt
                });

			}else{
			    //180719 wijy
                //조회 type 추가
                var sType = "==";
                if($.isArray(params.type)) {
                    sType = params.type[i];
                    if(sType == null || sType == '')  sType = '==';
                }

                filter = new OpenLayers.Filter.Comparison({
                    type : sType,
                    property : params.fields[i],
                    value : params.values[i]
                });
			}
			queryStr += this.format.xml.write(this.format.filter.write(filter));
			if(params.sortFields.length > 0)
				queryStr += this.getSortBy(params.sortFields, params.sortOrders);

			queryStr += '</wfs:Query>';
		}

		this.getFeature(serviceUrl, params, queryStr, callback, options);
	},

	getFeatureByBoundary : function(serviceUrl, parameters, callback, options) {
		var params = {
			maxFeatures : 9999,
			prefix : "",
			type : "==",
			tables : [],
			fields : [],
			values : [],
			sortFields : [],
			sortOrders : [],
			useDomain : false,
			logicalOper : "AND",
			boundaryFields : [],
			boundaryValues : [],
		};

		this.extendParambounds(params, parameters);

		var queryStr = '';
		for(var i=0, len=params.tables.length; i < len; i++) {
			var useDomain = params.useDomain?'useDomain="true"':'';
			queryStr += '<wfs:Query typeName="' + params.prefix + ':' + params.tables[i] + '" ' + useDomain + '  >';

			var filter;

			if($.isArray(params.fields[i])){
				var filterOpt = [];
				var filterType = OpenLayers.Filter.Logical.AND;

				for(var j=0; j<params.fields[i].length; j++){
					filterOpt.push(new OpenLayers.Filter.Comparison({
						type : params.type,
						property : params.fields[i][j],
						value : params.values[i][j]
					}));
				}

				switch(params.logicalOper){
					case "AND":
						filterType = OpenLayers.Filter.Logical.AND;
						break;
					case "OR":
						filterType = OpenLayers.Filter.Logical.OR;
						break;
				}
				if($.isArray(params.boundaryFields[i])&&  params.boundaryFields[i].length ==2){

						filterOpt.push(new OpenLayers.Filter.Comparison({
							type : OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
							property : params.boundaryFields[i][0],
							value : params.boundaryValues[i][0]
						}));
						filterOpt.push(new OpenLayers.Filter.Comparison({
							type : OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO,
							property : params.boundaryFields[i][1],
							value : params.boundaryValues[i][1]
						}));

				}
				filter = new OpenLayers.Filter.Logical({
                    type: filterType,
                    filters: filterOpt
                });

			}else{
				filter = new OpenLayers.Filter.Comparison({
					type : params.type,
					property : params.fields[i],
					value : params.values[i]
				});
			}


			queryStr += this.format.xml.write(this.format.filter.write(filter));
			if(params.sortFields.length > 0)
				queryStr += this.getSortBy(params.sortFields, params.sortOrders);

			queryStr += '</wfs:Query>';
		}

		this.getFeature(serviceUrl, params, queryStr, callback, options);
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

	getFeatureByDWithin : function(serviceUrl, parameters, callback, options, sync) {
		var params = {
			maxFeatures : 9999,
			prefix : "",
			type : OpenLayers.Filter.Spatial.DWITHIN,
			tables : [],
			distance : 1000,
			values : [],
			sortFields : [],
			sortOrders : [],
			useDomain : false
		};

		this.extendParams(params, parameters);

		var queryStr = '';
		var oXMLHttpRequest    = window.XMLHttpRequest;
		var bGecko    = !!window.controllers,
		bIE        = window.document.all && !window.opera,
		bIE7    = bIE && ((window.navigator.userAgent.match(/MSIE ([\.0-9]+)/) && RegExp.$1 == 7) ||(window.navigator.userAgent.match("rv:11.0")));

		for (var i = 0, len = params.tables.length; i < len; i++) {
			var useDomain = params.useDomain?'useDomain="true"':'';
			queryStr += '<wfs:Query typeName="' + params.prefix + ':' + params.tables[i] + '" ' + useDomain + '  >';
			var filter = new OpenLayers.Filter.Spatial({
				type: params.type,
				property : "G2_SPATIAL",
				value: params.values[0],
				distance: params.distance,
				distanceUnits: 'm'
			});


			/*
			filterStr += this.format.xml.write(this.format.filter.write(filter));
			filterStr += '</wfs:Query>';
			*/
			if(oXMLHttpRequest && !bIE7)
				queryStr += this.format.xml.write(this.format.filter.write(filter));				//Chrome
			else
				queryStr += this.format.filter.write(filter).xml;	//IE

			if(params.sortFields.length > 0)
				queryStr += this.getSortBy(params.sortFields, params.sortOrders);

			queryStr += '</wfs:Query>';

		}

		//this.getFeature(serviceUrl, params, filterStr, callback, options);
		this.getFeature(serviceUrl, params, queryStr, callback, options, sync);
	},

	getFeatureByGeometry : function(serviceUrl, parameters, callback, options, sync) {
		var params = {
			maxFeatures : 3000,  //2018.03.25. YYK. 최대 feature 수 제한
			prefix : "",
			type : OpenLayers.Filter.Spatial.INTERSECTS,
			tables : [],
			values : [],
			sortFields : [],
			sortOrders : [],
			useDomain : false
		};

		this.extendParams(params, parameters);

		var queryStr = '';
		for (var i = 0, len = params.tables.length; i < len; i++) {
			var useDomain = params.useDomain?'useDomain="true"':'';
			queryStr += '<wfs:Query typeName="' + params.prefix + ':' + params.tables[i] + '" ' + useDomain + '>';
			var filter = new OpenLayers.Filter.Spatial({
				type: params.type,
				//property : "G2_SPATIAL",
				property : "geom",
				value: params.values[0]
			});

			queryStr += this.format.xml.write(this.format.filter.write(filter));

			//queryStr += this.format.filter.write(filter).xml;

			if(params.sortFields.length > 0)
				queryStr += this.getSortBy(params.sortFields, params.sortOrders);
				
			queryStr += '</wfs:Query>';
		}
		this.getFeature(serviceUrl, params, queryStr, callback, options, sync);
		//this.getFeature(serviceUrl, params, filterStr, callback, options);
	},


	getFeature : function(serviceUrl, params, filter, callback, options, sync) {
		var control = this;
		var prefix = params.prefix;
		var namespace = 'xmlns:'+prefix+'="http://www.openplans.org/'+prefix;
		var wfsStr = '';
		//wfsStr += '<wfs:GetFeature service="WFS" version="1.1.0" maxFeatures="' + params.maxFeatures + '" xmlns:ehmp="http://health-e-waterways.org" xmlns:wfs="http://www.opengis.net/wfs" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">';
		//wfsStr += '<wfs:GetFeature strict="true" service="WFS" version="1.1.0" maxFeatures="' + params.maxFeatures + '" '+namespace+'" xmlns:wfs="http://www.opengis.net/wfs" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">';
		wfsStr += '<wfs:GetFeature service="WFS" outputFormat="application/json" version="1.1.0" maxFeatures="' + params.maxFeatures + '" '+namespace+'" xmlns:wfs="http://www.opengis.net/wfs" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd">';
		wfsStr += filter;
		wfsStr += '</wfs:GetFeature>';
		
//		if(sync){
//			GMapUtil.sendProxyPostSync(
//					serviceUrl,
//					wfsStr,
//					function(res) {
//						control.parseGetFeature(res, callback, options);
//					}
//				);
//		}else{
//			GMapUtil.sendProxyPost(
//					serviceUrl,
//					wfsStr,
//					function(res) {
//						control.parseGetFeature(res, callback, options);
//					}
//				);
//		}
		
		var ogcProxy = contextPath + 'ogcProxy.jsp?'
		var url = ogcProxy + serviceUrl;
		fetch(url, {
			method: 'POST',
			body: wfsStr
		}).then(function(response) {
			return response.json();
		}).then(function(res) {
			console.log(res);
			control.parseJsonFeature(res, callback, options);
		});
		
	},
	
	parseJsonFeature: function(res, callback, options){
		var arr = [];
		var success = true;
		var featureCollection;

		var features = res.features;
		var featuresLen = features.length;
		
		if(!featuresLen) success = false;
		
		for (var i = 0; i < featuresLen; i++) {
			var feature = features[i];
			var tableid = feature.id;
			var geometry = feature.geometry;
			var properties = feature.properties;
			
			var tmpArr = tableid.split(".");
			var tmpTable = tmpArr[0];
			
			//같은 테이블인지 체크 후 테이블 아래로 여러 레코드 들이 들어가게 함
			var index = null;
			for(var j in arr) {
				if(arr[j].table == tmpTable) {
					index = j;
					break;
				};
			}
			
			if(!index) {
				var obj = {
					table : tmpTable,	//테이블 명
					results : []		//레코드 들
				};
				arr.push(obj);
			}else {
				obj = arr[index];
			}
			
			//한개의 레코드
			var result = {
				g2id : tmpArr[1],	//G2_ID 필드 (PK)
				feature : this.format.geojson.read(res)[0],	//도형
				title : tmpArr[1],	//제목
				fields : properties //필드들
			};
			result.feature.fid = feature.id;
			obj.results.push(result);
		}

		console.log(obj);
		
		if(options && options.alias) {
			this.getRequestAlias(arr, success, callback, options);
		}
		else {
			callback({
				data : arr,
				success : function() {
					return success;
				}
			});
		}
	},

	parseGetFeature : function(res, callback, options) {
		// 2018.03.25 YYK. 마커수 제한
		/*if ( res.documentElement.childElementCount >= 3000 ) {
			alert("검색된 결과가 너무 많습니다. \n검색범위를 좁혀주세요.");
			$('#mCtrlPan').trigger('click') ;
			$("#dvMapLoading").hide();
			return;
		}*/
		if(res.responseXML) {
			res = res.responseXML;
		}

		var arr = [];
		var success = true;

		var featureCollection;

		// CJH. 수정 this.getBrowserName()에서 - 자체 정의한 func으로 변경 처리하고 있으나 후에 openlayers버전을 올리는 게 맞을 듯....ie12는 또 다를수 있으니..
		// IE11부터는 msie가 userAgent에서 제외됨에 따른 처리!
		if(this.getBrowserName() == "msie" || this.getBrowserName() == "firefox" || this.getBrowserName() == "safari") {
			featureCollection = res.getElementsByTagName("wfs:FeatureCollection");
		}
		else {
			featureCollection = res.getElementsByTagName("FeatureCollection");
		}

		if(featureCollection && featureCollection[0]) {
			if(featureCollection[0].getAttribute("numberOfFeatures") != 0) {
				var featureMembers;
				if(this.getBrowserName() == "msie" || this.getBrowserName() == "firefox" || this.getBrowserName() == "safari") {
					featureMembers = featureCollection[0].getElementsByTagName("gml:featureMember");
				}
				else {
					featureMembers = featureCollection[0].getElementsByTagName("featureMember");
				}

				for(var i=0, len = featureMembers.length; i < len; i++) {
					var tmpArr = featureMembers[i].firstChild.getAttribute("fid").split(".");

					//같은 테이블인지 체크 후 테이블 아래로 여러 레코드 들이 들어가게 함
					var tmpTable = tmpArr[0];
					var index = null;
					for(var j in arr) {
						if(arr[j].table == tmpTable) {
							index = j;
							break;
						};
					}

				if(!index) {
						var obj = {
							table : tmpTable,	//테이블 명
							results : []		//레코드 들
						};
						arr.push(obj);
					}
					else {
						obj = arr[index];
					}

					//한개의 레코드
					var result = {
						g2id : tmpArr[1],	//G2_ID 필드 (PK)
						feature : null,		//도형
						title : tmpArr[1],	//제목
						fields : {}			//필드들
					};

					var field = featureMembers[i].firstChild.firstChild;
					while(field) {
						//도형
						if(field.tagName.replace(field.prefix+":", "").toLowerCase() == "g2_spatial") {
							// usolver에서 공통 obj로 관리하기 위해 OpenLayers가 생성한 Feature(parsedFeature)에 아래 Custom 속성을 추가함 - ehyun.2016.06.10
							var parsedFeature = this.format.gml.parseFeature(field);
							parsedFeature.attributes.fid = featureMembers[i].firstChild.getAttribute("fid");
							parsedFeature.renderIntent = '';
							parsedFeature.featureType = (parsedFeature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','')).toLowerCase();
							parsedFeature.modified = {
									geometry : {}
							};
							result["feature"] = parsedFeature;
						}
						//속성
						else {
							//대표 속성
							if(options && options.titles && options.titles[obj.table] && field.tagName.replace(field.prefix+":", "").toLowerCase() == options.titles[obj.table].toLowerCase()) {
								if(typeof field.text === 'undefined')
									result.title = field.textContent;
								else
									result.title = field.text;

								/*if(this.getBrowserName() == "msie" || this.getBrowserName() == "firefox") {
									result.title = field.text;
								}
								else {
									result.title = field.textContent;
								}*/
							}
							//속성
							if(field.tagName.replace(field.prefix+":", "").toLowerCase() != "boundedby") {

								if(typeof field.text === 'undefined')
									result.fields[field.tagName.replace(field.prefix+":", "")] = field.textContent;
								else
									result.fields[field.tagName.replace(field.prefix+":", "")] = field.text;

								/*if(this.getBrowserName() == "msie" || this.getBrowserName() == "firefox") {
									result.fields[field.tagName.replace(field.prefix+":", "").toLowerCase()] = field.text;
								}
								else {
									result.fields[field.tagName.replace(field.prefix+":", "").toLowerCase()] = field.textContent;
								}*/
							}
						}

						field = field.nextSibling;
					}

					result.feature.fid = featureMembers[i].firstChild.getAttribute("fid");
					obj.results.push(result);
				}
			}
		}
		else {
			success = false;
		}

		if(options && options.alias) {
			this.getRequestAlias(arr, success, callback, options);
		}
		else {
			callback({
				data : arr,
				success : function() {
					return success;
				}
			});
		}
	},

	// IE11부터는 msie가 userAgent에서 제외됨에 따른 처리!
	getBrowserName : function() {
		var name = "";
	    var ua = navigator.userAgent.toLowerCase();
	    if (ua.indexOf("opera") != -1) {
	        name = "opera";
	    } else if (ua.indexOf("msie") != -1 || (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1)) {
	        name = "msie";
	    } else if (ua.indexOf("safari") != -1) {
	        name = "safari";
	    } else if (ua.indexOf("mozilla") != -1) {
	        if (ua.indexOf("firefox") != -1) {
	            name = "firefox";
	        } else {
	            name = "mozilla";
	        }
	    }
	    return name;
	},

	insert : function(serviceUrl, features, prefix, table, fields, values, callback) {
		if(features && !(features instanceof Array)) {
			features = [features];
		}


		var wfsStr = '';
		wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf">';
		wfsStr += '<wfs:Insert>';
		wfsStr += '<' + prefix + ':' + table + ' xmlns:' + prefix + '="http://geogate.g-inno.com/dataserver/' + prefix + '">';
		wfsStr += '<' + prefix + ':G2_SPATIAL>';
		wfsStr += this.createGmlXml(features);
		wfsStr += '</' + prefix + ':G2_SPATIAL>';
		if(fields && fields.length > 0) wfsStr += this.createAttrXml(prefix, fields, values);
		wfsStr += '</' + prefix + ':' + table + '>';
		wfsStr += '</wfs:Insert>';
		wfsStr += '</wfs:Transaction>';

		$("#txtTest").val(wfsStr);

		var control = this;
		GMapUtil.sendProxyPostSync(
			serviceUrl,
			wfsStr,
			function(res) {
				if(res.responseXML) {
					res = res.responseXML;
				}

				var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");

				if(transactionResponse.length > 0) {
					var arr = [];

					var totalInserted = transactionResponse[0].getElementsByTagName("wfs:totalInserted");
					var featureId = transactionResponse[0].getElementsByTagName("ogc:FeatureId");

					for(var i=0, len=featureId.length; i < len; i++) {
						arr.push(featureId[i].getAttribute("fid"));
					}

					if(callback) {
						callback({
							count : totalInserted[0].text,
							ids : arr
						});
					}
				}
			}
		);
	},

	update : function(serviceUrl, features, prefix, table, fields, values, value, callback) {
		if(features && !(features instanceof Array)) {
			features = [features];
		}

		var wfsStr = '';
		wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf">';

		wfsStr += '<wfs:Update typeName="' + prefix + ':' + table + '" xmlns:' + prefix + '="http://geogate.g-inno.com/dataserver/' + prefix + '">';
		wfsStr += '<wfs:Property>';
		wfsStr += '<wfs:Name>G2_SPATIAL</wfs:Name>';
		wfsStr += '<wfs:Value>';
		wfsStr += this.createGmlXml(features);
		wfsStr += '</wfs:Value>';
		wfsStr += '</wfs:Property>';

		if(fields && fields.length > 0) wfsStr += this.updateAttrXml(fields, values);

		wfsStr += '<ogc:Filter>';
		wfsStr += '<ogc:PropertyIsEqualTo matchCase="true">';
		wfsStr += '<ogc:PropertyName>' + table + '.GID</ogc:PropertyName> ';
		wfsStr += '<ogc:Literal>' + value + '</ogc:Literal> ';
		wfsStr += '</ogc:PropertyIsEqualTo>';
		wfsStr += '</ogc:Filter>';

		wfsStr += '</wfs:Update>';
		wfsStr += '</wfs:Transaction>';

		var control = this;
		GMapUtil.sendProxyPostSync(
			serviceUrl,
			wfsStr,
			function(res) {
				var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");

				if(transactionResponse.length > 0) {
					var totalUpdated = transactionResponse[0].getElementsByTagName("wfs:totalUpdated");

					if(callback) {
						callback({
							count : totalUpdated[0].textContent
						});
					}
				}
			}
		);
	},

	del : function(serviceUrl, prefix, table, value, callback) {
		var wfsStr = '';
		wfsStr += '<wfs:Transaction xmlns:wfs="http://www.opengis.net/wfs" service="WFS" version="1.1.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sf="http://cite.opengeospatial.org/gmlsf" releaseAction="ALL">';
		wfsStr += '<wfs:Delete typeName="' + prefix + ':' + table + '">';
		wfsStr += '<ogc:Filter xmlns:ogc=\"http://www.opengis.net/ogc\"><ogc:FeatureId fid=\"' + table + '.' + value + '\"/></ogc:Filter>';
		wfsStr += '</wfs:Delete>';
		wfsStr += '</wfs:Transaction>';

		var control = this;
		GMapUtil.sendProxyPostSync(
			serviceUrl,
			wfsStr,
			function(res) {
				var transactionResponse = res.getElementsByTagName("wfs:TransactionResponse");

				if(transactionResponse.length > 0) {
					var totalDeleted = transactionResponse[0].getElementsByTagName("wfs:totalDeleted");

					if(callback) {
						callback({
							count : totalDeleted[0].textContent
						});
					}
				}
			}
		);
	},

	createGmlXml :function(features) {
		var lineCount = 0;
		for ( var i in features) {
			if (features[i].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString")
				lineCount++;
		}

		var xmlStr = "";

		if (features[0].geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
			xmlStr += this.createPointXml(features[0].geometry);
		}
		//LineString 이 1개
		if (lineCount == 1) {
			xmlStr += '<gml:LineString xmlns:gml="http://www.opengis.net/gml">';
			xmlStr += this.createLineStringXml(features[0].geometry);
			xmlStr += '</gml:LineString>';
		}
		//LineString 이 2 개 이상이면 MultiLineString (MultiCurve 는 WMS 오류 있음)
		else if (lineCount > 1) {
			xmlStr += '<gml:MultiLineString xmlns:gml="http://www.opengis.net/gml">';
			for ( var i in features) {
				if (features[i].geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
					xmlStr += '<gml:lineStringMember><gml:LineString>';
					xmlStr += this.createLineStringXml(features[i].geometry);
					xmlStr += '</gml:LineString></gml:lineStringMember>';
				}
			}
			xmlStr += '</gml:MultiLineString>';
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

		return xmlStr;
	},

	//point XML 생성
	createPointXml : function(geometry) {
		var str = '';
		str += '<gml:Point xmlns:gml="http://www.opengis.net/gml"><gml:pos>';
		str += geometry.x + " ";
		str += geometry.y;
		str += '</gml:pos></gml:Point>';
		return str;
	},

	//line String XML 을 생성
	createLineStringXml : function(geometry) {
		var str = '';
		str += '<gml:posList>';
		for ( var i in geometry.components) {
			str += geometry.components[i].x + " ";
			str += geometry.components[i].y + " ";
		}
		str += '</gml:posList>';
		return str;
	},

	//polygon String XML 을 생성
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

	createAttrXml : function(prefix, fields, values) {
		var str = '';
		for(var i=0,len=fields.length; i<len; i++) {
			//str += '<'+prefix+':'+fields[i]+'>'+values[i]+'</'+prefix+':'+fields[i]+'>';
			str += '<'+fields[i]+'>'+values[i]+'</'+fields[i]+'>';
		}
		return str;
	},

	updateAttrXml : function(fields, values) {
		var str = '';
		for(var i=0,len=fields.length; i<len; i++) {
			str += "<wfs:Property>";
			str += "<wfs:Name>" + fields[i] + "</wfs:Name>";
			str += "<wfs:Value>" + values[i] + "</wfs:Value>";
			str += "</wfs:Property>";
		}
		return str;
	},

	/**********************************************************************************
	 * 함수명 : getRequestAlias
	 * 설 명 : layer, field 명을 alias 명으로 변환
	 * 인 자 : obj (속성정보 결과 배열)
	 * 사용법 : getRequestAlias(obj)
	 * 작성일 : 2011.05.19
	 * 작성자 : 기술개발팀 최원석
	 * 수정일				수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2011.05.19		최원석		최초 생성
	 *
	 **********************************************************************************/
	getRequestAlias : function(arr, success, callback, options) {
		var control = this;

		var data = "";
		for(var i=0, len=arr.length; i < len; i++) {
			data += arr[i].table + ",";
			for (var j in arr[i].results[0].fields) {
				data += j + ",";
			}
			data +=":";
		}

		$.post(
			"/gpms/gmap/attr/getAlias.do",
			{
				data : data
			},
			function (res) {
				for(var i=0, len=arr.length; i < len; i++) {
					arr[i].alias = res.data[i];
				}

				//트리거 이벤트 실행
				callback({
					data : arr,
					success : function() {
						return success;
					}
				});
			},
			"json"
		);
	},

	orderGetFeatureArr : function(arr, field, order) {
		var len = arr.length;
		for(var i=len-1; i > 0; i--) {
			for(var j=0; j < i; j++) {
				if(order.toLowerCase() == 'desc') {
					if(arr[j]["fields"][field] < arr[j+1]["fields"][field]) {
						GUtil.Array.fn_swap_element(arr, j, j+1);
					}
				}
				else {
					if(arr[j]["fields"][field] > arr[j+1]["fields"][field]) {
						GUtil.Array.fn_swap_element(arr, j, j+1);
					}
				}
			}
		}
	}
};


GRequest.WPS = {

	SERVICES : "WPS",

	VERSION : "1.0.0",

	format : {
		gml : new OpenLayers.Format.GML(),
		filter : new OpenLayers.Format.Filter({ version : "1.0.0" })
	},


	/*
	dataInputs = {
		layer : null,
		position : null,
		distance : null,
		count : null,
		result : null,
		field : null
	};
	*/
	getNearFeature : function(serviceUrl, dataInputs, callback) {
		var params = {
			Service : this.SERVICES,
			Version : this.VERSION,
			Request : "Execute",
			Identifier : "NearFeature",
			DataInputs : "",
			Responsedocument : "NEARFEATURE_OUTPUT"
		};

		params.DataInputs = "[";
		params.DataInputs += GUtil.fn_convert_objToStr(dataInputs, ";");
		params.DataInputs += "]";

		var control = this;
		GMapUtil.sendProxyPost(
			serviceUrl,
			GUtil.fn_convert_objToStr(params),
			function(res) {
				control.parseGetFeature(res, callback);
			}
		);
	},

	parseGetFeature : function(res, callback) {
		var arr = [];
		var success = true;

		var featureCollection = res.getElementsByTagName("wfs:FeatureCollection");

		if(featureCollection && featureCollection[0]) {
			var featureMembers = featureCollection[0].getElementsByTagName("gml:featureMember");

			for(var i=0, len = featureMembers.length; i < len; i++) {
				for(var i=0, len = featureMembers.length; i < len; i++) {
					var tables = featureMembers[i].firstChild;

					var tmpTable = tables.tagName;
					var index = null;
					for(var j in arr) {
						if(arr[j].table == tmpTable) {
							index = j;
							break;
						};
					}

					if(!index) {
						var obj = {
							table : tmpTable,	//테이블 명
							results : []		//레코드 들
						};
						arr.push(obj);
					}
					else {
						obj = arr[index];
					}

					//한개의 레코드
					var result = {
						feature : null,		//도형
						fields : {}			//필드들
					};

					var field = featureMembers[i].firstChild.firstChild;
					while(field) {
						//도형
						if(field.tagName.toLowerCase() == "geometry") {
							// usolver에서 공통 obj로 관리하기 위해 OpenLayers가 생성한 Feature(parsedFeature)에 아래 Custom 속성을 추가함 - ehyun.2016.06.10
							var parsedFeature = this.format.gml.parseFeature(field);
							parsedFeature.attributes.fid = '';
							parsedFeature.renderIntent = '';
							parsedFeature.featureType = (parsedFeature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','')).toLowerCase();
							parsedFeature.modified = {
									geometry : {}
							};
							result["feature"] = parsedFeature;
						}
						//속성
						else {
							if(typeof field.text === 'undefined')
								result.fields[field.tagName] = field.textContent;
							else
								result.fields[field.tagName] = field.text;


						}
						field = field.nextSibling;
					}
					obj.results.push(result);
				}
			}
		}
		else {
			success = false;
		}

		callback({
			data : arr,
			success : function() {
				return success;
			}
		});
	},

	getHoldWaterInfo : function(serviceUrl, dataInputs, callback){
		var params = {
			Service : this.SERVICES,
			Version : this.VERSION,
			Request : "Execute",
			Identifier : "HoldWater",
			DataInputs : "",
			Responsedocument : "HOLDWATER_OUTPUT"
		};
		/*
  		params.DataInputs += "LOCTABLE=";
		params.DataInputs += 테이블명("TE_SMALL_BLCK") + ";";
		params.DataInputs += "LOCFIELD=";
		params.DataInputs += 테이블필드명("SMALL_BLCK_ID") + ";";
		params.DataInputs += "LOCVALUE=";
		params.DataInputs += 테이블필드값("K0000100") + ";";
		params.DataInputs += "DISTANCE=";
		params.DataInputs += 버퍼("10") + ";";
		params.DataInputs += "PIPEDISTANCE=";
		params.DataInputs += 관로버퍼("0.3") + ";";
		params.DataInputs += "ENDDISTANCE=";
		params.DataInputs += 관말버퍼("0.3");
		*/
		params.DataInputs = "[";
		params.DataInputs += GUtil.fn_convert_objToStr(dataInputs, ";");
		params.DataInputs += "]";

		var control = this;
		GMapUtil.sendProxyPost(
			serviceUrl,
			GUtil.fn_convert_objToStr(params),
			function(res) {
				control.parseHoldWaterInfo(res, callback);
			}
		);
	},

	parseHoldWaterInfo : function(res, callback) {
		var arr = [];
		var success = true;
		var results = {};
		var holdWater = res.getElementsByTagName("prof:HoldWater");

		var obj = {
			results : []
		};
		arr.push(obj);

		var result = {
				pipes : [],
				valves : [],
				fires : []
		};

		if(holdWater && holdWater[0]){
			var field = holdWater[0].firstChild;
			var fieldText = field.text;
			if(typeof fieldText === 'undefined'){
				fieldText = field.textContent;
			}
			while(field){
				if(field.tagName.toLowerCase().split(":")[1] == "pipes"){
					result["pipes"].push(fieldText);
				}
				else if(field.tagName.toLowerCase().split(":")[1] == "valves"){
					result["valves"].push(fieldText);
				}
				else if(field.tagName.toLowerCase().split(":")[1] == "fires"){
					result["fires"].push(fieldText);
				}
				field = field.nextSibling;
			}
			obj.results.push(result);
		}
		else {
			success = false;
		}

		callback({
			data : arr,
			success : function() {
				return success;
			}
		});
	}
};
GRequest.POST = function(config) {
	config = OpenLayers.Util.extend(config, {method: "POST"});
    // set content type to application/xml if it isn't already set
    config.headers = config.headers ? config.headers : {};
    if(!("CONTENT-TYPE" in OpenLayers.Util.upperCaseObject(config.headers))) {
        config.headers["Content-Type"] = "application/xml";
    }
    return GRequest.issue(config);
};

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
   /* var url = OpenLayers.Util.urlAppend(config.url,
        OpenLayers.Util.getParameterString(config.params || {}));
    url = OpenLayers.Request.makeSameOrigin(url, config.proxy);*/
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
};
