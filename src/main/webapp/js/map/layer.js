
MAP.LAYER = (function($,undefined){
	/**
	 * 범례 Tool
	 * @member {Object} legendTool
	 *//*
	var legendTool = null; // 지도 범례 관련 Tool

	*//**
	 * visible 대상 레이어 리스트
	 * @member {Array} layerList
	 *//*
	var layerList = [];

	*//**
	* @description 레이어와 레이어 별칭을 가진 데이터를 가져온다.
	*//*
	var fn_set_layerInfo = function(){
		$.ajax({
			url: contextPath + 'api/attr/getTableAliasList.do'
			,type: 'post'
			,dataType: 'json'
			,success: function(res){
				layerAliasList = res.data;

				fn_init_legend();
			}
			,error: function(a,b,msg){
			}
		});
	};

	*//**
	* @description 레이어의 별칭을 가져온다.
	* @param {String} _oLayerNm : 대상 레이어명
	*//*
	var fn_get_layerAlias = function(_oLayerNm){
		var alias = null;

		if(layerAliasList == null || layerAliasList.length < 1){return;}

		layerAliasList.forEach(function(data){
			if(data.LAYER_NM == _oLayerNm){
				alias = data.ALIAS_NM;
			}
		});

		return alias;
	};

	*//**
	* @description 범례 TOC를 생성한다.
	*//*
	var fn_init_legend = function() {
		//전체 레이어 목록
		layerList = gMap.baseLayer.params.LAYERS.slice();

		//jstree 생성
			$("#dvLayerList").jstree({
			"core" : {},
			"checkbox": {
				"three_state": false,
	            "keep_selected_style": false,
	            "aria-selected" : true
	        },
			"plugins" : [ "wholerow", "checkbox", "changed" ]
		});

		//jstree Event
		$("button").on("click", function() {
			$("#dvLayerList").jstree(true).select_node("child_node_1");
			$("#dvLayerList").jstree("select_node", "child_node_1");
			$.jstree.reference("#dvLayerList").select_node("child_node_1");
		});

		$("#dvLayerList")
		  // listen for event
		  .on("changed.jstree", function (e, data) {
			  var visibleList = [];

			  if(data.selected.length < 1) {
				  gMap.baseLayer.setVisibility(false);
				  return;
			  }else {
				  gMap.baseLayer.setVisibility(true);
			  }

			  for(var i=0; i<layerList.length; i ++) {
				  var layer = layerList[i];
				  if(data.selected.indexOf(layer) > -1) {
					  visibleList.push(layer);
				  }
			  }

			  gMap.baseLayer.params.LAYERS = visibleList;
			  gMap.baseLayer.params.STYLES = visibleList;
			  gMap.baseLayer.redraw(true);
			  gMap.baseLayer.setVisibility(false);
			  gMap.baseLayer.setVisibility(true);

		  })
		  // create the instance
		  .jstree();

		//범례 툴 생성
		legendTool = new GLengendTool(gMap, null, null, fn_set_legendInfo);
		legendTool.showFullList();

	};

	*//**
	* @description 조회할 레이어의 범례 심볼 및 정보를 가져온다.
	*//*
	var fn_set_legendInfo = function(res) {
		var legendList = [];

		res.forEach(function(legend) {
			var name = legend.layer;
			var children = [];
			var icon = "";

			if (legend.rule.length > 1) {
				legend.rule.forEach(function(rule) {
					var ruleNm = rule.name;

					icon = CONFIG.fn_get_serviceUrl()
					+ "Service=WMS&Version=1.3.0&Request=GetLegendGraphic&Layer="
					+ name + "&rule=" + ruleNm
					+ "&Width=20&Height=20&Format=image/png&SLD_VERSION=1.1.0";

					var ruleObj = {
							"id": name + "_" + ruleNm,
		                    "text": ruleNm,
		                    "icon": icon,
		                    "state": {
		                        "opened": false,
		                        "disabled": false,
		                        "selected": false,
		                        "checkbox_disabled": true
		                    },
		                    "children": false,
		                    "liAttributes": null,
		                    "aAttributes": null
					}

					children.push(ruleObj);
				})
				icon = "";

			} else {
				icon = CONFIG.fn_get_serviceUrl()
						+ "Service=WMS&Version=1.3.0&Request=GetLegendGraphic&Layer="
						+ name
						+ "&Width=20&Height=20&Format=image/png&SLD_VERSION=1.1.0";
			}

			var legendObj = {
				"id" : name,
				"text" : fn_get_layerAlias(name),
				"icon" : icon,
				"state": {
					"opened": false,
	                "disabled": false,
	                "selected": true
				},
				"children": children,
	            "liAttributes": null,
	            "aAttributes": null
			};

			legendList.push(legendObj);

		})

		legendList.reverse();

		$('#dvLayerList').jstree(true).settings.core.data = legendList;
		$('#dvLayerList').jstree(true).refresh();

		// 전체 select가 인식이 안되는 경우가 생겨 임시 추가
		$("#dvLayerList").jstree(true).select_node("child_node_1");
	};

	*//**
	* @description 범례 Tool을 가져온다.
	*//*
	var fn_get_legendTool = function(){
		return legendTool;
	};*/

	//=================================usolver 버전 레이어 관리 기능 추가===================================

	/**
	* @memberof USV.MAP
	* @method
	* @description 지도에서 사용되는 jsTree 생성
	* @author 임상수(2015.07.31)
	* @param {object} _oRes : GetStyle 결과 obj
	* @param {Boolean} _bUserStyle : userStyle 사용여부
	*/
	var fn_init_dvLayerList = function (_oRes, _bUserStyle, _layerName) {
		$("#dvLayerList").jstree({
			"plugins" : [ "themes", "json_data", "ui", "cookies", "types","checkbox" ],
			"themes" : { "theme" : "default" },
			"json_data" : fn_create_treeJson(null, _layerName),  //YYK
			"types" : {
				"valid_children" : [ "tmap" ],
				"types" : {
					"style" : {
						"valid_children" : "none"
					},
					"layer" : {
						"valid_children" : "style"
					}
					,"group" : {
						"valid_children" : "layer"
					},
					"tmap" : {
						"valid_children" : [ "layer" ],
						"rename" : false,
						"start_drag" : false,
						"move_node" : false,
						"delete_node" : false,
						"remove" : false
					}
				}
			}
		});

		$("#dvLayerList").bind("loaded.jstree", function() {
	        $("#dvLayerList li[show=1]").each( function() {
	        	$("#dvLayerList").jstree("check_node", $(this));
	        	var sLayerId = $(this).attr("id").replace("layer_","");
				var oNamedLayer = fn_find_sldNameLayer(sLayerId);
				fn_toggle_allRuleWmsLayer('on',oNamedLayer);
			});
	        $("#dvLayerList li[show=0]").each( function() {
	        	//$("#dvLayerList").jstree("check_node", $(this));
	        	var sLayerId = $(this).attr("id").replace("layer_","");
	        	var oNamedLayer = fn_find_sldNameLayer(sLayerId);
	        	fn_toggle_allRuleWmsLayer('off',oNamedLayer);
	        });

	        $("#dvLayerList").bind("open_node.jstree", function(e, _oData) {
	    		if($(_oData.rslt.obj[0]).attr("rel")=="layer") {
	    			$(_oData.rslt.obj[0]).find("li[rel=style]").each(function() {
	    				if($(this).attr("id").indexOf("symbol") >= 0) {
	    					var sTreeId = $(this).attr("id").replace("style_", "").replace("_symbol", "");
	    					var aTreeId = sTreeId.split("_");

	    					if($(this).find("a ins.jstree-icon").css("background-image").indexOf("blank") >= 0) {
	    						var oSld = layerTool.getSld();

	    						if(oSld.namedLayers[aTreeId[0]].userStyle[aTreeId[1]].rules[aTreeId[2]]){

	    						var oSymbolizer = oSld.namedLayers[aTreeId[0]].userStyle[aTreeId[1]].rules[aTreeId[2]].symbolizer;
	    						var sImgUrl;

	    						//rule view check
	                            if (!$("#dvLayerList").jstree("is_checked",_oData.rslt.obj[0])) {
	                            	oSld.namedLayers[aTreeId[0]].userStyle[aTreeId[1]].rules[aTreeId[2]].hidden = true;
	                            }

	                            if(oSymbolizer["point"]) {
	                            	sImgUrl = GRequest.WMS.getLengendGraphic(CONFIG.fn_get_serviceUrl(), {
	    								layer : oSld.namedLayers[aTreeId[0]].name,
	    								style : oSld.namedLayers[aTreeId[0]].userStyle[aTreeId[1]].name,
	    								rule : oSld.namedLayers[aTreeId[0]].userStyle[aTreeId[1]].rules[aTreeId[2]].name
	    							});
	    						}
	                            else {
	    							var oTmpSymbolizer = {
	    									width : 16,
	    									height : 16
	    								};
	    							if(oSymbolizer["polygon"]) {
	    								if(oSymbolizer["polygon"].externalGraphic){
	    									sImgUrl = contextPath + "map/gmap/getImageFromBase64.do?encodeImg="+encodeURIComponent(oSymbolizer["polygon"].externalGraphic);
	    								}
	    								else{
	    									oTmpSymbolizer.strFillColor = oSymbolizer["polygon"].fillColor.replace("#", "");
	    									if(oSymbolizer["polygon"]) {
	    										if(oSymbolizer["polygon"].stroke){
	    											oTmpSymbolizer.strColor = oSymbolizer["polygon"].stroke.replace("#", "");
	    										}
	    										else{
	    											oTmpSymbolizer.strColor = "ffffff";
	    										}

	    									}
	    									sImgUrl = contextPath + "map/gmap/getPolygonSymbol.do?" + GUtil.fn_convert_objToStr(oTmpSymbolizer);
	    								}
	    							} /*else if(oSymbolizer["line"]) {
										if(oSymbolizer["line"]["arrow"]) {
											oTmpSymbolizer.arrow = 1;
										}
										if(oSymbolizer["line"]["marker"]) {
											oTmpSymbolizer.marker = 1;
										}
										if(oSymbolizer["line"].stroke){
											oTmpSymbolizer.strColor = oSymbolizer["line"].stroke.replace("#", "");
										}
										else{
											oTmpSymbolizer.strColor = "ffffff";
										}
										sImgUrl = "/map/gmap/getLineSymbol.do?" + GUtil.fn_convert_objToStr(oTmpSymbolizer);
									}*/
								}
								$(this).find("a ins.jstree-icon").css("background-image", "url('"+sImgUrl+"')");

	    						}
	    					}
	    				}
	    			});
	    		}
	    	});
	        // 레이어 on/off
			$("#dvLayerList a ins.jstree-checkbox").on('click',function() {
/*
				if ( $(this).parent().parent().attr('id') == "layer_CMPTNC_ZONE"
					|| $(this).parent().parent().parent().parent().attr('id') == "layer_CMPTNC_ZONE"
					) {
					gMap.getLayerByName('baseLayer').setVisibility(true) ;
				}
*/
				//gMap.getLayerByName('baseLayer').setVisibility(true) ;
				var oTreeElement = $(this).parent().parent();
				if(oTreeElement.attr("id").indexOf("group_") > -1) {
					$(oTreeElement).find("li.layer").each(function(index) {
						var sLayerId = $(this).attr("id").replace("layer_","");
						var oNamedLayer = fn_find_sldNameLayer(sLayerId);

						if ($("#dvLayerList").jstree("is_checked", oTreeElement)) {
							fn_toggle_allRuleWmsLayer('off',oNamedLayer);
						} else {
							fn_toggle_allRuleWmsLayer('on',oNamedLayer);
						}
					});
				}
				else if (oTreeElement.attr("id").indexOf("layer_") > -1) {//
					var sLayerId = oTreeElement.attr("id").replace("layer_", "");
					var oNamedLayer;

					oNamedLayer = fn_find_sldNameLayer(sLayerId);{
					if ($("#dvLayerList").jstree("is_checked",oTreeElement)) {
						fn_toggle_allRuleWmsLayer('off',oNamedLayer);
					}
					else {
						fn_toggle_allRuleWmsLayer('on',oNamedLayer);
					}
				  }
				}
				else {
					var sRuleName = $(this).parent('a').text().trim();
					//var sLayerName = 'CMPTNC_ZONE';
					//var sLayerName = $(this).closest('.layer').find('a').eq(0)[0].text.trim();
					var sLayerName = $(this).closest('.layer').find('a').eq(0).text().trim();
					sLayerName = fn_get_EditEngLayerNm(sLayerName);

					if ($("#dvLayerList").jstree("is_checked",oTreeElement)) {
						fn_toggle_wmsRule('off',sLayerName,sRuleName);
					}
					else {
						fn_toggle_wmsRule('on',sLayerName,sRuleName);
					}
				}
				//fn_redraw_wms();
				fn_redraw_wms(_layerName);

			});
		});


	};


	/**
	* @memberof USV.MAP
	* @method
	* @description  jstree에 트리데이터로 사용되는 json을 생성
	* @author 임상수(2015.07.31)
	* @param {Object} _oLayers 트리를 구성할 레이어 목록
	*/
	var fn_create_treeJson = function (_oLayers, _layerName) {
		var sSelectedId = "";
		if (_oLayers) {
			sSelectedId = baseTMapId;

		} else {
			sSelectedId = layerTool.getTMapId();
		}

		//var oLayerGroups = layerTool.getLayerGroups();
		var aLayerList;
		if (_oLayers) {
			aLayerList = [];
			for ( var i in _oLayers) {
				if (_oLayers[i].tmapid == sSelectedId) {
					aLayerList.push(_oLayers[i]);
				}
			}
		} else {
			aLayerList = layerTool.getLayers( {
				con : 'tmapid',
				//conVal : sSelectedId,
				order : 'asc'
				,layer : _layerName
			});

		}

		var oSld = layerTool.getSld();

		var oJsonData = {
			data : []
		};

		//for ( var i in oLayerGroups) {

			/*var oGroupObj = {
				data : {
					title : oLayerGroups[i].name,
					icon : "/images/usolver/com/map/icon/Icon_Group.png"
				},
				attr : {
					'rel' : "group",
					'class' : "group",
					'id' : "group_" + oLayerGroups[i].id,
					'state' : "closed"
				},

				children : []
			};*/

			for ( var j in aLayerList) {
				//if (oLayerGroups[i].id == aLayerList[j].layerGroup) {//그룹이 없으니까 우선 주석 처리해봄

			        // 2018. 03. 26. JOY. 레이어관리 ( 포트홀만 ) 이름 alias
			        var nameAlias = "";

			        if ( aLayerList[j].alias == "포트홀_도로면_50만" ) {
			            nameAlias = "도로면";
			        } else if ( aLayerList[j].alias == "포트홀_관할_구역" ) {
			            nameAlias = "포트홀 관할 구역";
			        } else {
			            nameAlias = aLayerList[j].alias;
			        }

					var olayerInfo = {
						data : {
							//title : aLayerList[j].alias,
							title : nameAlias,
							icon : contextPath + "images/file.png"
						},
						attr : {
							'rel' : "layer",
							'class' : "layer",
							//'id' : "layer_" + aLayerList[j].id,
							'id' : "layer_" + aLayerList[j].table,
							'seq' : aLayerList[j].seq,
							'show' : aLayerList[j].show,
							'layerGroup' : aLayerList[j].layerGroup
						},
						children : []
					};

					switch(eval(aLayerList[j].layerType)) {
						case 1 :
							olayerInfo.data.icon = contextPath + "images/Icon_Point.png";
							break;
						case 2 :
							olayerInfo.data.icon = contextPath + "images/Icon_Line.png";
							break;
						case 3 :
							olayerInfo.data.icon = contextPath + "images/Icon_Area.png";
							break;
					}
					if (!_oLayers) {
						
						for ( var k in oSld.namedLayers) {
							if (oSld.namedLayers[k].name == aLayerList[j].theme) {
								var oUserStyles = oSld.namedLayers[k].userStyle;
								for ( var l in oUserStyles) {
									var oRules = oUserStyles[l].rules;
									for(var m in oRules) {
										var sIcon;
										if(oRules[m].symbolizer.text) {
											sIcon = contextPath + "images/text.gif";

											var fileObj = {
												data : {
													title : oRules[m].name,
													icon : sIcon
												},
												attr : {
													'rel' : "style",
													'class' : "style",
													'id' : "style_" + k + "_" + l + "_" + m + "_" + "text"
												}
											};
											olayerInfo.children.push(fileObj);
										}else {
											sIcon = contextPath + "images/blank.gif";

											var oFileInfo = {
												data : {
													title : oRules[m].name,
													icon : sIcon
												},
												attr : {
													'rel' : "style",
													'class' : "style",
													'id' : "style_" + k + "_" + l + "_" + m + "_" + "symbol"
												}
											};
											olayerInfo.children.push(oFileInfo);
										}
									}
								}
							}
						}
					}
					//oGroupObj.children.push(olayerInfo);
					oJsonData.data.push(olayerInfo);
				//}
			}

			//if (oGroupObj.children.length > 0)
			//oJsonData.data.push(oGroupObj);

		//}

		return oJsonData;
	};

	/**
	* @memberof USV.STYLE
	* @method
	* @description 선택한 레이어의 룰 전부를 on/off 하는 함수
	* @param {String} _sStatus - 'on'/'off' 선택
	* @param {Object/String} _sLayer - layerTool의 선택한 NamedLayer object or 선택한 레이어명(한글,영어명)
	* @author 이상호 (2016.07.12)
	*/
	var fn_toggle_allRuleWmsLayer = function(_sStatus,_sLayer) {
		var oNamedLayer,sLayerName;
		if(typeof _sLayer === 'object') {
			oNamedLayer = _sLayer;
		} else if(typeof _sLayer === 'string') {
			oNamedLayer = fn_find_sldNameLayer(_sLayer);
		}

		if(oNamedLayer != null){
			sLayerName = oNamedLayer.featureTypeName;
			var aRules = oNamedLayer.userStyle[0].rules;
			for(var j in aRules){
				var sRuleName = aRules[j].name;
				if(_sStatus === 'on'){
					fn_toggle_wmsRule('on',sLayerName,sRuleName);
				} else if (_sStatus === 'off') {
					fn_toggle_wmsRule('off',sLayerName,sRuleName);
				}
			}
		}
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description 룰을 on/off 하는 함수
	* @param {String} _sStatus - 'on'/'off' 선택
	* @param {String} _sLayerName - 선택한 레이어명(한글,영어명)
	* @param {String} _sRuleName - 선택한 룰이름
	* @author 이상호 (2016.07.12)
	*/
	var fn_toggle_wmsRule = function(_sStatus,_sLayerName,_sRuleName) {
		var sRuleName = _sRuleName.trim();
		var sLayerName = _sLayerName.trim();
		var oRule = fn_find_rule(sLayerName,sRuleName);
		var oNamedLayer = fn_find_sldNameLayer(sLayerName);
		if(_sStatus === "on"){
			oRule.hidden = false;
			if(!fn_check_wmsLayerShow(sLayerName)){
				fn_toggle_wmsLayer("on",sLayerName);
			}

		} else if(_sStatus === "off"){
			oRule.hidden = true;
			if(fn_check_wmsRuleAllOff(oNamedLayer)){
				fn_toggle_wmsLayer("off",sLayerName);
			}
		}
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description 레이어를 on/off 하는 함수
	* @param {String} _sStatus - 'on'/'off' 선택
	* @param {String} _sLayerName - 선택한 레이어명(한글,영어명)
	* @author 이상호 (2016.07.12)
	*/
	var fn_toggle_wmsLayer = function(_sStatus,_sLayerName){
		var sEngLayerName = fn_get_EditEngLayerNm(_sLayerName);
		var sLayerId;
		if(sEngLayerName != null) {
			sLayerId = fn_get_EditLayerId(sEngLayerName);
		} else {
			sLayerId = fn_get_EditLayerId(_sLayerName);
		}
		var nStatus = 0;
		if(_sStatus === "on"){
			nStatus = 1;
		} else if(_sStatus === "off"){
			nStatus = 0;
		}

		layerTool.setLayerAttr({
			con : "id",
			conVal : sLayerId,
			attr : "show",
			value : nStatus
		});
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description sld에서 layer의 id값, layer 영어명, 한글명으로 nameLayer 리턴
	* @param {String} _sId - layer의 id, layer 영어명, layer 한글명
	* @returns {Object} oNameLayers - layerTool에서 파싱된 nameLayer
	* @author 이상호 (2016.06.20)
	*/
	var fn_find_sldNameLayer = function(_sId){
	    var oSld = layerTool.getSld();
	    var oLayers = layerTool.layers;
		var oNameLayers = oSld.namedLayers;
		var _sSldName = _sId.trim();
//
//		for(var i in oLayers) {
//			if(oLayers[i].id === _sId) {
//				_sSldName = oLayers[i].table;
//			}
//		}
		
		for(var i in oNameLayers) {
			if(oNameLayers[i].featureTypeName === _sSldName || oNameLayers[i].name === _sSldName){
				return oNameLayers[i];
			}
		}

		return null;
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description layerTool에 파싱된 sld에서 rule 한글명으로 rule을 반환하는 함수
	* @param {String} _sRuleName - 선택한 rule의 한글명
	* @returns {Object} oReturnRule - rule Object
	* @author 이상호 (2016.06.20)
	*/
	var fn_find_rule = function(_sLayerName,_sRuleName) {

		var oSld = layerTool.sld;

		var aNamedLayer = oSld.namedLayers;
		var oReturnRule;

		for(var i in aNamedLayer) {
			var oNamedLayer = aNamedLayer[i];
			if(oNamedLayer.featureTypeName === _sLayerName.trim() || oNamedLayer.name === _sLayerName.trim()) {
				var aRules = oNamedLayer.userStyle[0].rules;
				for(var j in aRules) {
					var oRule = aRules[j];
					if(oRule.name === _sRuleName.trim()) {
						oReturnRule = oRule;
					}
				}
			}
		}
		return oReturnRule;
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description 현재 레이어의 on/off 상태 체크
	* @param {String} _sLayerName - 선택한 레이어명(한글,영어명)
	* @returns {Boolean} bCheck - 레이의어 on/off 상태 (true/false)
	* @author 이상호 (2016.07.12)
	*/
	var fn_check_wmsLayerShow = function(_sLayerName){
		var bCheck = false;
		var sEngLayerName;
		try{
			sEngLayerName = fn_get_EditEngLayerNm(_sLayerName);
		} catch(e){
			sEngLayerName = _sLayerName;
		}

		var oLayer = fn_get_layerInfo(sEngLayerName);
		if(oLayer) {
			if(oLayer.show == "1"){
				bCheck = true;
			}
		}
		return bCheck;
	}

	/**
	* @memberof USV.STYLE
	* @method
	* @description 현재 레이어 모든 룰 스타일이 off인지 체크
	* @param {Object/String} _sLayer - layerTool의 선택한 NamedLayer object or 선택한 레이어명(한글,영어명)
	* @returns {Boolean} bCheck -  모든 룰의 off 상태 (true/false)
	* @author 이상호 (2016.07.12)
	*/
	var fn_check_wmsRuleAllOff = function(_sLayer) {
		var bCheck = true;
		var oNamedLayer;
		if(typeof _sLayer === 'object') {
			oNamedLayer = _sLayer;
		} else if(typeof _sLayer === 'string') {
			oNamedLayer = fn_find_sldNameLayer(_sLayer);
		}
		var aRules = oNamedLayer.userStyle[0].rules;
		for(var i in aRules){
			var oRule = aRules[i];
			var bHidden = oRule.hidden;
			if(typeof bHidden !== 'undefined' && bHidden === false){
				bCheck = false;
			}
		}
		return bCheck;
	};


	/**
	* @memberof USV.MAP
	* @method
	* @description 지도의 스타일(SLD)이 변경되었거나 레이어 on/off시 지도를 redraw처리함
	* @author 임상수(2015.07.31)
	*/
	var fn_redraw_wms = function (layer) {
	    var sLayerOrder = null;
	    if ( layer == undefined || layer == 'cmptncLayer' || layer == 'sttemntLayer' ) {
	        sLayerOrder = CONFIG.fn_get_getMapInfo().layerOrder;   //asc(오름차순)
	    } else {
	        sLayerOrder = "";
	    }

		var sThemeList = layerTool.getThemeShowList(sLayerOrder, layer).join();
		//var sThemeList = fn_get_checkAllLayerNode();
		var sDefaultThemeList = "EMPTY_LAYER";
		var sSldBody;
		var sThemeListStyle;

		if (!sThemeList && sThemeList.length <= 0) {
			sThemeList = sDefaultThemeList;
			sSldBody = null;
		} else { // YYK. sSldBody 에 레이어 롤 입력

			// YYK. ie11 ~ 이전 버전에 따라 serializeToString 안먹히는 현상 해결
			if ( chkBrowser == 'msie' ){ // ie 10 이하
				sSldBody = layerTool.getSld_body(true).xml ;
			}
			else if ( chkBrowser == 'msie11') { // ie 11
				sSldBody = new XMLSerializer().serializeToString(layerTool.getSld_body(true));
				
				
				//IE 문제 해결을 위해 (네임스페이스에 공백이 들어가는 문제 해결을 하기 위해 조치)
				//<sld:NamedLayer> 기준으로 앞을 자르고 새로운 styledLayerDescriptor 붙인다.
				var styledLayerDescriptor = '<sld:StyledLayerDescriptor xmlns:sld="http://www.opengis.net/sld" version="1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd" xmlns:ogc="http://www.opengis.net/ogc" xmlns:gml="http://www.opengis.net/gml">';
				var namedLayer = '<sld:NamedLayer>';
				var namedLayerLength = namedLayer.length;
				var namedLayerIndex = sSldBody.indexOf(namedLayer);
				sSldBody = styledLayerDescriptor + sSldBody.substring(namedLayerIndex - namedLayerLength);
			}
			else{
				sSldBody = new XMLSerializer().serializeToString(layerTool.getSld_body(true));
			}
		}

		var oWmsLayer;
		if ( layer == undefined || layer == 'cmptncLayer' || layer == 'sttemntLayer' ) {
		    oWmsLayer = gMap.getLayerByName("baseLayer");
		} else {
		    // 2017. 11. 06. JOY 파라미터 추가.
		    oWmsLayer = gMap.getLayerByName(layer);
		}
		
		if(sThemeList == "EMPTY_LAYER"){
			oWmsLayer.setVisibility(false);
			return ;
		}
		if (oWmsLayer) {
			oWmsLayer.setVisibility(true);
			oWmsLayer.mergeNewParams( {
				layers : sThemeList
				,styles : ""
				,sld_body : sSldBody
				//,makeTheUrlLong :
			});
		}
	};

	/**
	* @memberof USV.STYLE
	* @method
	* @description 현재 트리에서 체크된 모든 레이어명 반환 함수
	* @author 이상호 (2016.10.25)
	*/
	var fn_get_checkAllLayerNode = function() {
		var aAllNodes = $("#dvLayerList li.layer").get().reverse();
		var aShowLayers = layerTool.getLayers({
            con: "show",
            conVal: 1
        });
		var returnNodes = "";

		for(var i in aAllNodes) {
			var oNode = $(aAllNodes).eq(i);
			var oLayer = layerTool.getLayers({
	            con: "id",
	            conVal: oNode.attr("id").replace("layer_","")
	        });
			if(aShowLayers.indexOf(oLayer[0]) != -1) {
				returnNodes += oLayer[0].theme+",";
			}
		}
		return returnNodes.slice(0,-1);
	};


	/**
	* @memberof USV.COMMON
	* @method
	* @description 레이어 한글명으로 영문명 추출
	* @author 최재훈(2015.09.14)
	* @param {String} _sLyrKorNm - 레이어(alias)명
	* @returns {String} 레이어영문(table)명
	*/
	var fn_get_EditEngLayerNm = function (_sLyrKorNm){
		_sLyrKorNm = $.trim(_sLyrKorNm);

		var sRtnLayerEngNm = null;
		var oLayerInfoList = null;

		    oLayerInfoList = fn_get_orgLayerTotInfoList();

		for(key in oLayerInfoList){
			if(oLayerInfoList[key].alias == _sLyrKorNm.replace(/ /gi, "_")){
				sRtnLayerEngNm = oLayerInfoList[key].table;
				break;
			}
		}

		if(!sRtnLayerEngNm){
			sRtnLayerEngNm = _sLyrKorNm;
		}
		return sRtnLayerEngNm;
	};

	/**
	* @memberof USV.COMMON
	* @method
	* @description (시스템이 제공하는 기초) 모든 레이어 정보 가져오기
	* @author 최재훈(2015.12.14)
	* @returns {Object} oLayerInfoList
	*/
	var fn_get_orgLayerTotInfoList = function (){
	    return MAIN.fn_get_layerTotInfoList();
	};

	/**
	* @memberof USV.COMMON
	* @method
	* @description 레이어 영문명으로 DataSetId 추출
	* @author 최재훈(2015.09.14)
	* @param {String} _sLyrEngNm - 레이어(table)명
	* @returns {String} 레이어 DataSetId
	*/
	var fn_get_EditLayerId = function (_sLyrEngNm){
		_sLyrEngNm = $.trim(_sLyrEngNm);
		return fn_get_layerInfo(_sLyrEngNm).id;
	};

	/**fn_get_layerTotInfoList
	* @memberof USV.COMMON
	* @method
	* @description 레이어 정보 가져오기
	* @author 최재훈(2015.11.17)
	* @param {String} _sLyrEngNm - 정보를 추출하고자 하는 레이어(table)명
	* @returns {Object} layerInfo
	*/
	var fn_get_layerInfo = function (_sLyrEngNm){
	    return MAIN.fn_get_layerInfoList(_sLyrEngNm);
	};

	// ***************************************************** //
	/*var fn_get_EditEngLayerNmTheme = function (_sLyrKorNm){
        _sLyrKorNm = $.trim(_sLyrKorNm);
        var sRtnLayerEngNm = null;
        var oLayerInfoList = fn_get_orgLayerTotInfoListTheme();
        for(key in oLayerInfoList){
            if(oLayerInfoList[key].alias == _sLyrKorNm){
                sRtnLayerEngNm = oLayerInfoList[key].table;
                break;
            }
        }

        if(!sRtnLayerEngNm){
            sRtnLayerEngNm = _sLyrKorNm;
        }
        return sRtnLayerEngNm;
    };

    var fn_get_orgLayerTotInfoListTheme = function (){
        return MAIN.fn_get_layerTotInfoListTheme();
    };*/

    // ***************************************************** //





	function fn_bmsCheck(_sLyrEngNm){
		fn_toggle_wmsLayer('on',_sLyrEngNm);
		fn_redraw_wms();
	}

	return {
		 /*fn_set_layerInfo		:		fn_set_layerInfo,
		 fn_set_legendInfo		:		fn_set_legendInfo,

		 fn_get_layerAlias		:		fn_get_layerAlias,
		 fn_get_legendTool		:		fn_get_legendTool*/

		 fn_init_dvLayerList		:		fn_init_dvLayerList,
		 fn_create_treeJson			:		fn_create_treeJson,
		 fn_toggle_allRuleWmsLayer	:		fn_toggle_allRuleWmsLayer,
		 fn_toggle_wmsRule			:		fn_toggle_wmsRule,
		 fn_toggle_wmsLayer			:		fn_toggle_wmsLayer,
		 fn_find_sldNameLayer		:		fn_find_sldNameLayer,
		 fn_find_rule				:		fn_find_rule,
		 fn_check_wmsLayerShow		:		fn_check_wmsLayerShow,
		 fn_check_wmsRuleAllOff		:		fn_check_wmsRuleAllOff,
		 fn_redraw_wms				:		fn_redraw_wms,
		 fn_get_checkAllLayerNode	:		fn_get_checkAllLayerNode,
		 fn_get_EditEngLayerNm		:		fn_get_EditEngLayerNm,
		 fn_get_orgLayerTotInfoList :		fn_get_orgLayerTotInfoList,
		 fn_get_EditLayerId			:		fn_get_EditLayerId,
		 fn_bmsCheck				:		fn_bmsCheck,
		 fn_get_layerInfo           :       fn_get_layerInfo/*,
		 fn_get_EditEngLayerNmTheme :       fn_get_EditEngLayerNmTheme,
		 fn_get_orgLayerTotInfoListTheme :  fn_get_orgLayerTotInfoListTheme*/
	}
 }(jQuery));