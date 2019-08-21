GDrawTool = OpenLayers.Class({

	map : null,

	layer : null,

	controls : null,

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

	initialize : function(map, options) {
		var control = this;

		this.map = map;

		//Vector 레이어 생성
		this.layer = new GVector(
			"GDrawToolLayer",
			{
				styleMap : this.getStyleMap(),
				eventListeners: options.eventListeners
			}
		);

		OpenLayers.Util.extend(this, options);

		//벡터 레이어 등록
		map.addLayer(this.layer);

		//그리기 도구 컨트롤 등록
		this.controls = [
			/*new GSelectFeature(this.layer, {
				id : 'drawSelect'
			}),*/
			/*
			new GSelectFeature(this.layer, {
				id : 'drawMultiSelect',
				toggleKey: "ctrlKey", // ctrl key removes from selection
                multipleKey: "shiftKey", // shift key adds to selection
                box: true
			}),
 */
			new OpenLayers.Control.SelectFeature(this.layer,{
				id : 'drawSelect',
                clickout: true,
                toggleKey: "ctrlKey", // ctrl key removes from selection
                multipleKey: "shiftKey", // shift key adds to selection
                box: true
            }),
			new OpenLayers.Control.ModifyFeature(this.layer, {
				id : 'drawEdit',
				mode : OpenLayers.Control.ModifyFeature.RESHAPE | OpenLayers.Control.ModifyFeature.DRAG
			}),
			/*new GModifyFeature(this.layer, {
				id : 'drawEdit',
				mode : OpenLayers.Control.ModifyFeature.DRAG | OpenLayers.Control.ModifyFeature.RESIZE
			}),*/
			/*
			new GModifyFeature(this.layer, {
				id : 'drawEditPoint',
				mode : OpenLayers.Control.ModifyFeature.RESHAPE
			}),*/
			new GDrawFeature(this.layer, GPoint, {
				id : 'drawPoint',
				handlerOptions : {
					attributes : this.defaultStyle['Point']
				}
			}),
			new GDrawFeature(this.layer, GPoint, {
				id : 'drawSymbol',
				handlerOptions : {
					attributes : this.defaultStyle['Image']
				}
			}),
			new GDrawFeature(this.layer, GPath, {
				id : 'drawLine',
				handlerOptions : {
					attributes : this.defaultStyle['Line']
				}
			}),
			new GDrawFeature(this.layer, GRegularPolygonDraw, {
				id : 'drawRect',
				handlerOptions : {
					irregular : true,
					attributes : this.defaultStyle['Polygon']
				}
			}),
			new GDrawFeature(this.layer, GRegularPolygonDraw, {
				id : 'drawCircle',
				handlerOptions : {
					sides : 50,
					irregular : true,
					attributes : this.defaultStyle['Polygon']
				}
			}),
			new GDrawFeature(this.layer, GRegularPolygonDraw, {
				id : 'drawEllipse',
				handlerOptions : {
					sides : 50,
					attributes : this.defaultStyle['Polygon']
				}
			}),
			new GDrawFeature(this.layer, GPolygonDraw, {
				id : 'drawPolygon',
				handlerOptions : {
					attributes : this.defaultStyle['Polygon']
				}
			}),
			new GDrawFeature(this.layer, GPoint, {
				id : 'drawText',
				handlerOptions : {
					attributes : this.defaultStyle['Text']
				}
			})
		];

		map.addControls(this.controls);

		if(options) {
			if(options.onFeatureAdded) {
				this.layer.events.register("featureadded", this, options.onFeatureAdded);
			}

			if(options.onModificationStart) {
				map.getControl('drawEdit').onModificationStart = options.onModificationStart;
//				map.getControl('drawEditPoint').onModificationStart = options.onModificationStart;
			}

			if(options.onSelect){
				map.getControl('drawSelect').onSelect = options.onSelect;
			}

			if(options.onDeactivate) {
				map.getControl('drawEdit').onDeactivate = options.onDeactivate;
//				map.getControl('drawEditPoint').onDeactivate = options.onDeactivate;
			}

			if(options.onUnselectAll) {
			//	map.getControl('drawEdit').selectControl.onUnselectAll = options.onUnselectAll;
//				map.getControl('drawEditPoint').selectControl.onUnselectAll = options.onUnselectAll;
			}
		}
	},

	getStyleMap : function() {
		//스타일 생성
		var style = new OpenLayers.Style(null);
		// 룰 생성
		style.addRules( [
			//점 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					pointRadius: "\${pointRadius}",		//크기
					//그래픽 이름  "circle", "square", "star", "x", "cross", "triangle" 지원
					//저장 기능에서는 circle, square 만 지원
					graphicName: "\${graphicName}",
					fillColor: "\${fillColor}",			//면 색상
					fillOpacity: "\${fillOpacity}",		//면 투명도
					strokeWidth: "\${strokeWidth}",		//선 굵기
					strokeOpacity: "\${strokeOpacity}",	//선 투명도
					strokeColor: "\${strokeColor}"		//선 색
				},
				filter : new OpenLayers.Filter.Comparison( {
					type : "==",
					property : "featureType",
					value : "Point"
				})
			}),
			//선 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					strokeColor : "\${strokeColor}", //색
					strokeWidth : "\${strokeWidth}", //굵기
					strokeOpacity : "\${strokeOpacity}", //투명도
					strokeDashstyle : "\${strokeDashstyle}", //스타일
					strokeLinecap : "\${strokeLinecap}" // 끝모양
				},
				filter : new OpenLayers.Filter.Comparison( {
					type : "==",
					property : "featureType",
					value : "Line"
				})
			}),
			//도형 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					fillColor : "\${fillColor}", //면 색
					fillOpacity : "\${fillOpacity}", //면 투명도
					strokeColor : "\${strokeColor}", //선 색
					strokeWidth : "\${strokeWidth}", //선 굵기
					strokeOpacity : "\${strokeOpacity}", //선 투명도
					strokeDashstyle : "\${strokeDashstyle}" // 선 스타일
				},
				filter : new OpenLayers.Filter.Comparison( {
					type : "==",
					property : "featureType",
					value : "Polygon"
				})
			}),
			//텍스트 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					pointRadius: "\${pointRadius}",
					graphicName: "\${graphicName}",
					fillColor: "\${fillColor}",
					fillOpacity: "\${fillOpacity}",
					strokeWidth: "\${strokeWidth}",
					strokeOpacity: "\${strokeOpacity}",
					strokeColor: "\${strokeColor}"
				},
				filter : new OpenLayers.Filter.Comparison( {
					type : "==",
					property : "featureType",
					value : "Text"
				})
			}),
			//이미지 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					graphicOpacity : "\${graphicOpacity}", //투명도
					externalGraphic : "\${externalGraphic}", //이미지
					graphicWidth : "\${graphicWidth}", //너비
					graphicHeight : "\${graphicHeight}" // 높이
				},
				filter : new OpenLayers.Filter.Comparison( {
					type : "==",
					property : "featureType",
					value : "Image"
				})
			}),
			// default 스타일
			new OpenLayers.Rule( {
				symbolizer : {
					 strokeColor: "#0033ff",
			            strokeOpacity: .7,
			            strokeWidth: 2,
			            fillColor: "#0033ff",
			            fillOpacity: 0,
			            graphicZIndex: 2,
			            cursor: "pointer"
				},
				elseFilter: true
			})
		]);

		// styleMap 생성
		var styleMap = new OpenLayers.StyleMap( {
			"default" : style
		});

		return styleMap;
	},

	deleteFeature : function() {
		this.removeTextPopup();

		var features = [];
		if (this.layer.selectedFeatures.length > 0) {
			for(var i in this.layer.selectedFeatures) {
				features.push(this.layer.selectedFeatures[i]);
			}
		}

		for(var i in this.map.controls) {
			if(this.map.getControl("drawSelect") && this.map.getControl("drawSelect").active) {
				this.map.activeControls("drawSelect");
			}
			else if(this.map.getControl("drawEdit") && this.map.getControl("drawEdit").active) {
				this.map.activeControls("drawEdit");
			}
			else if(this.map.getControl("drawEditPoint") && this.map.getControl("drawEditPoint").active) {
				this.map.activeControls("drawEditPoint");
			}
		}

		if(features.length > 0) {
			this.layer.removeFeatures(features);
		}
	},

	removeTextPopup : function() {
		var id;

		$(".olControlDrawText").each(function() {
			if($(this).hasClass("on")) {
				id = $(this).parent().parent().parent().attr("id");
			};
		});

		for(var i=this.map.popups.length-1; i >= 0; i--) {
			if(this.map.popups[i].id == id) {
				this.map.removePopup(this.map.popups[i]);
			}
		}
	},

	getSelectFeature : function() {
		var id;

		if(this.map.getLayerByName('GDrawToolLayer') && this.map.getLayerByName('GDrawToolLayer').selectedFeatures && this.map.getLayerByName('GDrawToolLayer').selectedFeatures.length > 0) {
			return this.map.getLayerByName('GDrawToolLayer').selectedFeatures[0];
		};

		$(".olControlDrawText").each(function() {
			if($(this).hasClass("on")) {
				id = $(this).attr('id');
			}
		});

		for(var i in this.map.popups) {
			if(this.map.popups[i].attributes.seq == id.replace("drawText", "")) {
				return this.map.popups[i];
			};
		}

		return false;
	},

	setTextAttr : function(feature) {
		var seq = feature.attributes.seq;
		$("#drawText"+seq).css('font-family', feature.attributes.fontFamily);
		$("#drawText"+seq).css('font-size', feature.attributes.fontSize);
		$("#drawText"+seq).css('color', feature.attributes.fontColor);
		feature.updateSize();
	},

	addTextPopup : function(popup) {
		var seq = popup.attributes.seq;
		var str = popup.attributes.text;

		str = str.replace(/\x20/gi, "&nbsp;");
		str = str.replace(/\x0D\x0A/gi, "<br/>");
		str = str.replace(/\x0D/gi, "<br/>");
		str = str.replace(/\n/gi, "<br/>");

		var contentHtml = "";
		contentHtml += "<div class='olControlDrawText off' id='drawText" + seq + "'>" + str + "</div>";

		var pop = new GPopup("drawPopup" + seq, popup.getLonLat(), null, contentHtml, new OpenLayers.Pixel(0,0));

		this.map.addPopup(pop);
		pop.type = "draw";
		pop.attributes = popup.attributes;
		this.setTextAttr(pop);
	},

	removeAllFeatures : function() {
		this.layer.removeAllFeatures();

		for(var i=this.map.popups.length-1; i >= 0; i--) {
			if(this.map.popups[i].type == 'draw') {
				this.map.removePopup(this.map.popups[i]);
			}
		}
	},

	redraw : function() {
		this.layer.redraw();
	},

	CLASS_NAME: "GDrawTool"
});