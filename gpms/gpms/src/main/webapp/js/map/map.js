/**
 * 지도 관련 JS 파일
 */

/**
* 맵 객체
* @member {Object} gMap
* @public
*/
var gMap = null;

/**
* 다음맵 객체
* @member {Object} daumMap
* @public
*/
var daumMap = null;

/**
* 다음맵 로드뷰 객체
* @member {Object} daumRoadMap
* @public
*/
var daumRoadMap = null;

/**
* 레이어 목록 (영문명/한글명)
* @member {Object} layerAliasList
* @public
*/
var layerAliasList = null; //레이어 관련 정보


var MAP = (/**
 * @param $
 * @param undefined
 * @returns {___anonymous11896_12532}
 */
/**
 * @param $
 * @param undefined
 * @returns {___anonymous11896_12532}
 */
function($,undefined){

    /**
     * 이미지 저장 Tool
     * @member {Object} saveTool
     */
    var saveTool = null;

    /**
    * @description 지도를 초기화 한다.
    */
    var fn_init_map = function(_oRes, _bUserStyle, _layerName){
        fn_create_map();

        fn_create_indexMap({layers : "N3A_G0100000", maxResolution : 1000, projection : new OpenLayers.Projection(CONFIG.fn_get_dataHouseCrs())});

        fn_init_wms(_layerName);
        fn_init_vector();

        fn_move_map();

        fn_create_daumMap(gMap);
        //this.LAYER.fn_set_layerInfo();
        this.LAYER.fn_init_dvLayerList(null, _bUserStyle, _layerName);

        // 화면 UI
        /*indexmap();
        toolpack();*/

        // 지도 저장 툴
        saveTool = new GSaveTool(gMap);
        this.CONTROL.fn_add_mapControl();



        // YYK 컨트롤 활성화
        //fn_layer_control('GStatusLayer');
        //gMap.activeControls(['stteSelectFeature', 'drag']);
        gMap.activeControls('drag');
    };

    /**
    * @description 지도 객체를 생성한다.
    */
    var fn_create_map = function(){

        var oExtent = CONFIG.fn_get_getMapInfo().serviceExtent;
        var nMaxResolution = CONFIG.fn_get_getMapInfo().maxResolution;
        var nZoomLevels = CONFIG.fn_get_getMapInfo().zoomLevels;
        var aResolution = fn_get_resolution(nMaxResolution, nZoomLevels);
        var sDataHouseCrs =  CONFIG.fn_get_dataHouseCrs();

        var mapOptions = {
                maxExtent :  oExtent,
                maxResolution: nMaxResolution,
                minResolution: aResolution[aResolution.length-1],
                resolutions: aResolution,
                projection: new OpenLayers.Projection(sDataHouseCrs),
                units: "m",
                numZoomLevels: nZoomLevels,
                autoUpdateSize : false,
                fractionalZoom : false,
                zoomMethod: null,
                eventListeners: {
                    "moveend": fn_event_moveend
                }
            };
        gMap = new GMap('map', mapOptions);

    };

    /**
    * @description 지도 해상도를 가져온다.
    * @param {Integer} _nMaxResolution : 최대 해상도 값
    * @param {Integer} _nZoomLevels : 전체 지도 zoom level
    */
    var fn_get_resolution = function(_nMaxResolution, _nZoomLevels){
        var aRtnRes = [];
        var i, nRes;

        for(i = 0 ; i < _nZoomLevels; i++){
            nRes = _nMaxResolution / Math.pow(2,i);
            aRtnRes.push(nRes);
        }

        return aRtnRes;
    };

    /**
    * @description 지도 이동 Event
    */
    var fn_event_moveend = function (e) {
        fn_get_scale();
    };

    /**
    * @description 현재 지도의 축척을 가져온다.
    */
    var fn_get_scale = function() {
        $("#scale").val(gMap.getScale().toFixed(2));
    };

    /**
    * @description resize된 지도영역을 업데이트한다.
    */
    var fn_update_resizeMap = function() {
        $("#dvMapLoading").center();
        if(gMap != undefined && gMap != null){
            gMap.updateSize();
            if(gMap.baseLayer.params.SLD_BODY != undefined){
                gMap.baseLayer.mergeNewParams({
                    'SLD_BODY': gMap.baseLayer.params.SLD_BODY
                });
            }
        }
    };

    /**
    * @description wms 지도 객체를 생성한다.
    */
    var fn_init_wms = function (_layerName) {
        var sLayerOrder = CONFIG.fn_get_getMapInfo().layerOrder;
        var sGetMapImageFormat = CONFIG.fn_get_getMapInfo().imageFormat;
        var sGetMapVersion = CONFIG.fn_get_getMapInfo().version;
        var sDataHouseCrs = CONFIG.fn_get_dataHouseCrs();
        var sRequestCrs = CONFIG.fn_get_requestCrs();
        var sLayerList = CONFIG.fn_get_layerList();
        var cLayerList = CONFIG.fn_get_clayerList();
        //var mLayerList = CONFIG.fn_get_mlayerList(); // YYK
        //var cLayerList = CONFIG.fn_get_clayerList();
        var tLayerList = CONFIG.fn_get_tlayerList();

/* 레이어관리에 모든 레이어가 뜨게끔 해서 확인 하는 용도
        if( _layerName == 'cmptncLayer' ){
        	sThemeList = "SIGUNGU,N3A_B0010000,LSMD_CONT_LDREG,DORO_A001" ;
        }
        else {
        	var sThemeList = layerTool.getThemeShowList(sLayerOrder, _layerName).join();
        }
        */
        var sThemeList = layerTool.getThemeShowList(sLayerOrder, _layerName).join();

        var sDefaultThemeList = "EMPTY_LAYER";
        var sSldBody;

        if (!sThemeList && sThemeList.length <= 0) {
            sThemeList = sDefaultThemeList;
            sSldBody = null;
        } else {

        	// YYK. ie11 ~ 이전 버전에 따라 serializeToString 안먹히는 현상 해결
			if ( chkBrowser == 'msie' ){ // ie 10 이하
				sSldBody = layerTool.getSld_body(true).xml ;
			}
			else if ( chkBrowser == 'msie11') { // ie11
				sSldBody = new XMLSerializer().serializeToString(layerTool.getSld_body(true));
			}
			else{
				sSldBody = new XMLSerializer().serializeToString(layerTool.getSld_body(true));
			}
        }

		var oWmsLayer = new GWMS("baseLayer", CONFIG.fn_get_serviceUrl(), {
            layers : sThemeList
            //,styles : sThemeList
            ,format : sGetMapImageFormat
            ,version : sGetMapVersion
            ,crs : new OpenLayers.Projection(sRequestCrs)
            ,transparent : true
            ,dataHouse : CONFIG.fn_get_dataHouseName()
            ,sld_body : sSldBody
        }, {
            isBaseLayer : true,
            singleTile : true,
            transitionEffect : 'resize',
            tileOptions: {maxGetUrlLength: 2048},
            projection : new OpenLayers.Projection(sDataHouseCrs),
            ratio : 1.0
        });

        gMap.addLayer(oWmsLayer);
        //gMap.layers[0].setVisibility(false);


// 지도 인쇄/저장용

		var oBGLayer = new GWMS("backgroundLayer", CONFIG.fn_get_serviceUrl(), {
            layers : "SIGUNGU,N3A_B0010000,DORO_A001" ,
            styles : "" ,
            format : sGetMapImageFormat,
            version : sGetMapVersion,
            crs : new OpenLayers.Projection(sRequestCrs),
            transparent : true,
            dataHouse : CONFIG.fn_get_dataHouseName()
            //sld_body : sSldBody
        }, {
            isBaseLayer : true,
            singleTile : true,
            transitionEffect : 'resize',
            tileOptions: {maxGetUrlLength: 2048},
            projection : new OpenLayers.Projection(sDataHouseCrs),
            ratio : 1.0
        });

        gMap.addLayer(oBGLayer);


        // 2017. 11. 06. JOY 테마지도 레이어 추가
        var oThemeLayer = new GWMS("themeLayer", CONFIG.fn_get_serviceUrl(), {
            layers : tLayerList,
            styles : tLayerList,
            format : sGetMapImageFormat,
            version : sGetMapVersion,
            crs : new OpenLayers.Projection(sRequestCrs),
            transparent : true,
            dataHouse : CONFIG.fn_get_dataHouseName(),
        }, {
            isBaseLayer : false,
            singleTile : true,
            transitionEffect : 'resize',
            tileOptions: {maxGetUrlLength: 2048},
            projection : new OpenLayers.Projection(sDataHouseCrs),
            ratio : 1.0
        });

        gMap.addLayer(oThemeLayer);
        gMap.getLayerByName("themeLayer").setVisibility(false)

        // 통합검색 시종점 레이어
        gMap.addLayer(new GVector("GAttrLayerMulti", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                fillColor : "\${fillColor}",
                                strokeColor : "\${strokeColor}",
                                strokeWidth: 3,
                                pointRadius: "\${pointRadius}",
                                fillOpacity : 0.5,
                                graphicName : "circle"
                            },
                            "Line" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}"
                            },
                            "Polygon" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}",
                                fillColor : "\${fillColor}",
                                fillOpacity : 0.2
                            }
                        }
                    }) ]
                })
            })
        }));

      //180806 wijy
		//교차로선택 표시용
		gMap.addLayer(new GVector("GlyrSelectCross", {
			styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                fillColor : "\${fillColor}",
                                strokeColor : "\${strokeColor}",
                                strokeWidth: 3,
                                pointRadius: "\${pointRadius}",
                                fillOpacity : 0.5,
                                graphicName : "circle"
                            },
                            "Line" : {
                                strokeWidth : 2,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}"
                            },
                            "Polygon" : {
                                strokeWidth : 2,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}",
                                fillColor : "\${fillColor}",
                                fillOpacity : 0.2
                            }
                        }
                    }) ]
                })
            })
		}));

        // 레이어 로드 완료/종료 시 로딩 바 보이기/숨기기
        oWmsLayer.events.register("loadstart", oWmsLayer, function() {
        	$("#dvMapLoading").show();
        });

        oWmsLayer.events.register("loadend", oWmsLayer, function() {
            $("#dvMapLoading").hide();

        });

    };

    /**
    * @description 다음맵 객체를 생성한다.
    * @param {Object} _oMap : 현재 지도 객체
    */
    var fn_create_daumMap = function(_oMap){
        daumMap = new GDaumMap("daumMap", {
            oMap : _oMap,
            center : _oMap.getCenter(),
            zoom : _oMap.getZoom()
        });
    };


    /**
    * @description vector 레이어를 생성한다.
    */

    // 스타일레이어
    var statusStyle = [
        new OpenLayers.Rule( {
            symbolizer : {
                externalGraphic: '/gpms/images/icon_marker0.png',
                graphicWidth: '34',
                graphicHeight: '34',
                rotation: "${angle}",
                graphicXOffset: -17,
                graphicYOffset: -17,
                graphicOpacity: 1,
                pthRgNo: '${pthRgNo}',
                pthmode: '${pthmode}',
                data: '${prcsSttus}',
                allData : '${allData}'
            }
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker1.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}' ,
                allData : '${allData}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0001"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker2.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}',
                allData : '${allData}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0002"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker3.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0003"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker4.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0004"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker5.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0005"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker6.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${prcsSttus}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "PRCS0006"
            })
        })
    ];

    var dmgtStyle = [
       new OpenLayers.Rule( {
           symbolizer : {
               externalGraphic: '/gpms/images/icon_marker0.png',
               graphicWidth: '34',
               graphicHeight: '34',
               rotation: "${angle}",
               graphicXOffset: -17,
               graphicYOffset: -17,
               graphicOpacity: 1,
               pthRgNo: '${pthRgNo}',
               pthmode: '${pthmode}',
               data: '${dmgType}',
               allData : '${allData}'
           }
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker1.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0001"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker2.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
         , filter : new OpenLayers.Filter.Comparison({
             type : "=="
             , property : "data"
             , value : "DMGT0002"
         })
     }),
     new OpenLayers.Rule({
         symbolizer : {
               externalGraphic : '/gpms/images/icon_marker3.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0003"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker4.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0004"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker5.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
               }
               , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0005"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker6.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0006"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker7.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0007"
           })
       }),
       new OpenLayers.Rule({
           symbolizer : {
               externalGraphic : '/gpms/images/icon_marker8.png',
               graphicWidth : '34',
               graphicHeight : '34',
               rotation : "${angle}",
               graphicXOffset : -17,
               graphicYOffset : -17,
               graphicOpacity : 1,
               pthRgNo : '${pthRgNo}',
               pthmode: '${pthmode}',
               data : '${dmgType}'
           }
           , filter : new OpenLayers.Filter.Comparison({
               type : "=="
               , property : "data"
               , value : "DMGT0008"
           })
       })
   ];

    var overlapStyle = [
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/${image}',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${dplctSttemntAt}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "N"
            })
        }),
        new OpenLayers.Rule({
            symbolizer : {
                externalGraphic : '/gpms/images/icon_marker_ol.png',
                graphicWidth : '34',
                graphicHeight : '34',
                rotation : "${angle}",
                graphicXOffset : -17,
                graphicYOffset : -17,
                graphicOpacity : 1,
                pthRgNo : '${pthRgNo}',
                pthmode: '${pthmode}',
                data : '${dplctSttemntAt}'
            }
            , filter : new OpenLayers.Filter.Comparison({
                type : "=="
                , property : "data"
                , value : "Y"
            })
        })
   ];


    var stteControl, dmgtControl;

    var fn_init_vector = function() {

        //검색 결과 표출용 레이어
        gMap.addLayer(new GVector("GAttrLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                fillColor : "\${fillColor}",
                                strokeColor : "\${strokeColor}",
                                strokeWidth: 3,
                                pointRadius: "\${pointRadius}",
                                fillOpacity : 0.5,
                                graphicName : "circle"
                            },
                            "Line" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}"
                            },
                            "Polygon" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}",
                                fillColor : "\${fillColor}",
                                fillOpacity : 0.2
                            }
                        }
                    }) ]
                }
                )
            , 'select' : new OpenLayers.Style({pointRadius: 35})
            })
        }));

        gMap.addLayer(new GVector("GAttrLayerMulti", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                fillColor : "\${fillColor}",
                                strokeColor : "\${strokeColor}",
                                strokeWidth: 3,
                                pointRadius: "\${pointRadius}",
                                fillOpacity : 0.5,
                                graphicName : "circle"
                            },
                            "Line" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}"
                            },
                            "Polygon" : {
                                strokeWidth : 3,
                                strokeOpacity : 1,
                                strokeColor : "\${strokeColor}",
                                fillColor : "\${fillColor}",
                                fillOpacity : 0.2
                            }
                        }
                    }) ]
                })
            	,'select': new OpenLayers.Style({pointRadius: 35})
            })
        }));

        // 2018. 02. 20. JOY. 포트홀 신고 현황 조회 레이어
        gMap.addLayer(new GVector("GStatusLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : null
            })
        }));

        // 2018. 02. 26. JOY. 파손유형별 신고현황 조회 레이어
        gMap.addLayer(new GVector("GTypeLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : null
            })
        }));

        // 2018. 02. 21. YYK. 포트홀 신고 현황 조회 레이어2
		gMap.addLayer(new GVector("SttemntLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                externalGraphic: '/gpms/images/${image}.png',
                                graphicWidth: '34',
                                graphicHeight: '34',
                                rotation: "${angle}",
                                graphicXOffset: -17,
                                graphicYOffset: -17,
                                graphicOpacity: 1,
                                pthRgNo: '${pthRgNo}',
                                pthmode: '${pthmode}',
                                //,prcsSttus: '${prcsSttus}'
                            }
                        }
                    }) ]
                })
            	,'select': new OpenLayers.Style({pointRadius: 35})
            })
        }));

        // 2018. 02. 22. YYK. 포트홀 파손유형별 조회 레이어
        gMap.addLayer(new GVector("DmgtLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : new OpenLayers.Style(null, {
                    rules : [ new OpenLayers.Rule( {
                        symbolizer : {
                            "Point" : {
                                externalGraphic: '/gpms/images/${image}.png',
                                graphicWidth: '34',
                                graphicHeight: '34',
                                rotation: "${angle}",
                                graphicXOffset: -17,
                                graphicYOffset: -17,
                                graphicOpacity: 1,
                                pthRgNo: '${pthRgNo}',
                                pthmode: '${pthmode}'
                            }
                        }
                    }) ]
                })
                ,'select': new OpenLayers.Style({pointRadius: 35})
            })
        }));

        // 2018. 03. 07. JOY. 중복신고 건 조회 레이어
        gMap.addLayer(new GVector("GOverlapLayer", {
            styleMap : new OpenLayers.StyleMap( {
                'default' : null
            })
        }));



//  2018.04.11 마커 클릭 이벤트 숨김 처리
/*
    // ====== YYK. GStatusLayer control 등록 ======
    	var stteFeature, sttePopup;

		stteControl = new OpenLayers.Control.SelectFeature(

        	[gMap.getLayerByName('GStatusLayer'), gMap.getLayerByName('SttemntLayer') ]
            ,{onSelect: function(feature) {

	            if ( $('#sttemnt_tool .'+feature.attributes.allData.PRCS_STTUS).hasClass('bgchk') ){
		            stteFeature = feature;
	                var pos = feature.geometry;
	                if (sttePopup) {
	                    gMap.removePopup(sttePopup);
	                }
	                sttePopup = new OpenLayers.Popup("chicken",
	                    new OpenLayers.LonLat(pos.x, pos.y),
	                    new OpenLayers.Size(300,140),
	                    "<table style='width:300px; table-layout:fixed' class='selFeature'>"
	                    + "<tbody>"
	                    + "<tr><th>등록번호</th><td>"     + feature.attributes.allData.PTH_RG_NO.substring(0,8) + Lpad(feature.attributes.allData.PTH_RG_NO.substring(9), 3) +  "</td></tr>"
	                    + "<tr><th>차량번호</th><td>"     + nullToEmpty(feature.attributes.allData.VHCLE_NO) +   "</td></tr>"
	                    + "<tr><th>주소</th><td title='"+ nullToEmpty(feature.attributes.allData.LNM_ADRES) +"'>"     + nullToEmpty(feature.attributes.allData.LNM_ADRES) +  "</td></tr>"
	                    + "<tr><th>담당자</th><td>"      + nullToEmpty(feature.attributes.allData.CHARGER_NM) + "</td></tr>"
	                    + "<tr><th>연락처</th><td>"      + nullToEmpty(feature.attributes.allData.CTTPC) +      "</td></tr>"
	                    + "<tr><th>신고일시</th><td>"     + nullToEmpty(feature.attributes.allData.STTEMNT_DT.substring(0, 19)) + "</td></tr>"
	                    + "</tbody>"
	                    + "</table>"
	                    ,false
	                    );

	                stteFeature.Popup = sttePopup;
	                gMap.addPopup(sttePopup)

			        }

	            }
        	 ,onUnselect: function(feature) {

            	 gMap.removePopup(feature.Popup);
                 feature.Popup.destroy();
                 feature.Popup = null;

            	 }
        	 ,id : 'stteSelectFeature'
        	}


        )

        //gMap.setCenter(new OpenLayers.LonLat(0, 0), 3);
        gMap.addControl(stteControl);

        stteControl.activate();
        //this.getControl(controls).activate()




//  2018.04.11 마커 클릭 이벤트 숨김 처리
    // ====== YYK. GTypeLayer control 등록 ======
        var dmgtFeature, dmgtPopup;

        dmgtControl = new OpenLayers.Control.SelectFeature(
        	[gMap.getLayerByName('GTypeLayer'), gMap.getLayerByName('DmgtLayer') ]
            ,{onSelect: function(feature) {

            	if ( $('#dmgt_tool .'+feature.attributes.allData.DMG_TYPE).hasClass('bgchk') ){
	            	dmgtFeature = feature;
	                var pos = feature.geometry;
	                if (dmgtPopup) {
	                    gMap.removePopup(dmgtPopup);
	                }
	                dmgtPopup = new OpenLayers.Popup("chicken",
	                    new OpenLayers.LonLat(pos.x, pos.y),
	                    new OpenLayers.Size(300,140),
	                    "<table style='width:300px; table-layout:fixed' class='selFeature'>"
	                    + "<tbody>"
	                    + "<tr><th>등록번호</th><td>"     + feature.attributes.allData.PTH_RG_NO.substring(0,8) + Lpad(feature.attributes.allData.PTH_RG_NO.substring(9), 3) +  "</td></tr>"
	                    + "<tr><th>차량번호</th><td>"     + nullToEmpty(feature.attributes.allData.VHCLE_NO) +   "</td></tr>"
	                    + "<tr><th>주소</th><td title='" + nullToEmpty(feature.attributes.allData.LNM_ADRES) +"'>"     + nullToEmpty(feature.attributes.allData.LNM_ADRES) +  "</td></tr>"
	                    + "<tr><th>담당자</th><td>"      + nullToEmpty(feature.attributes.allData.CHARGER_NM) + "</td></tr>"
	                    + "<tr><th>연락처</th><td>"      + nullToEmpty(feature.attributes.allData.CTTPC) +      "</td></tr>"
	                    + "<tr><th>신고일시</th><td>"     + nullToEmpty(feature.attributes.allData.STTEMNT_DT.substring(0, 19)) + "</td></tr>"
	                    + "</tbody>"
	                    + "</table>"
	                    ,false
	                    );

	                dmgtFeature.Popup = dmgtPopup;
	                gMap.addPopup(dmgtPopup)
	            }

            }
        	 ,onUnselect: function(feature) {
            	 gMap.removePopup(feature.Popup);
                 feature.Popup.destroy();
                 feature.Popup = null;

            	 }
        	 ,id : 'dmgtSelectFeature'
        	}
        )

        //gMap.setCenter(new OpenLayers.LonLat(0, 0), 3);
        gMap.addControl(dmgtControl);

        // 초기 deactivate
        dmgtControl.activate();
*/



    };



    /**
    * @description 인덱스 맵을 생성한다.
    * @param {Object} _oOptions : 지도 객체
    */
    var fn_create_indexMap = function (_oOptions) {
        var oIndexMapOption = {
                maxExtent : CONFIG.fn_get_getMapInfo().serviceExtent,
                serviceUrl : CONFIG.fn_get_serviceUrl(),
                projection : _oOptions.projection,
                layers : _oOptions.layers,
                styles : _oOptions.layers,
                maxResolution : _oOptions.maxResolution,
                div : "dvIndexMap"
        };

        var oIndexMap = new GIndexMap(gMap, oIndexMapOption);

        oIndexMap.show();

    };


    /**
    * @description 지도를 영역으로 이동시킨다.
    * @param {Object} _sInitExtent : 이동 시킬 위치의 extent
    */
    var fn_move_map = function (_sInitExtent) {
        if(_sInitExtent != "null" && _sInitExtent){
            var oExtent = JSON.parse(sInitExtent);
            var initExtent = new OpenLayers.Bounds(oExtent.left, oExtent.bottom, oExtent.right, oExtent.top);
            gMap.zoomToExtent(initExtent);
        }
        else{
            oExtent = CONFIG.fn_get_getMapInfo().initExtent;

            gMap.zoomToExtent(oExtent);
        }
    };



    /**
    * @description styleMap을 생성한다.
    */
    var fn_get_StyleMap = function(){
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
    };

    /**
    * @description 현재 영역의 지도 이미지를 저장한다.
    */
    var fn_save_map = function() {
        var msg = "현재화면의 지도를 저장하시겠습니까?";

        if (confirm(msg)) {
            $("#download_frm").find("#data").val(
                    encodeURIComponent(fn_encoding_map()));
            $("#download_frm").attr("action", contextPath + "saveMapImage.do");
            $("#download_frm").submit();
        }
    };

    /**
    * @description 저장할 지도 이미지의 data를 가져온다.
    */
    var fn_encoding_map = function() {
        var data = null;

        var oTempDiv = document.createElement("div");
        oTempDiv.style.visibility = "hidden";
        oTempDiv.style.height = $(gMap.div).height() + "px";
        oTempDiv.style.width = $(gMap.div).width() + "px";

        var oTempMap = daumMap.getStaticMap(oTempDiv);
        sTileMapUrl = $(oTempDiv).find("img").attr("src");
        sTileMapUrl = sTileMapUrl.replace("IW=0", "IW=" + $(gMap.div).width());
        sTileMapUrl = sTileMapUrl.replace("IH=0", "IH=" + $(gMap.div).height());
        $(oTempMap).remove();

        data = saveTool.getXML({
            type : 'daum',
            url : sTileMapUrl
        });

        return data;
    };


    /**
    * @description 속성값을 이용해 해당 feature를 가져온다.
    * @param {Object} _oMap : 지도 객체
    * @param {Object} _oTables : 조회할 테이블 리스트
    * @param {Object} _oFields : 검색할 필드 리스트
    * @param {Object} _oValues : 검색할 속성 값 리스트
    * @param {String} _oMarkerTable : point를 추가 표출할 테이블명
    * @param {String} _oLogicOper : 논리 연산자 (AND / OR)
    * @param {Object} _oAttribute : style를 변경할 속성정보
    * @param {Object} _oZoom : zoom 허용 여부
    * @param {Object} _oAttrIdx : Multi Layer 사용 시 GAttrLayer 순서 Index
    * @param {Object} _oMultiIdx : Multi Layer 사용 시 GAttrLayerMulti 순서 Index
    *
    */
    var fn_get_selectFeatureByAttr = function(_oMap, _oTables, _oFields, _oValues, _oMarkerTable, _oLogicOper, _oAttribute, _oZoom, _oAttrIdx, _oMultiIdx){
    	/*$("#goodMap .top-tool #status").hasClass('on') ?
    			_oMap.activeControls(['stteSelectFeature', 'drag']) : _oMap.activeControls(['dmgtSelectFeature', 'drag'])*/
    	_oMap.activeControls('drag');

        if ( _oAttrIdx != undefined && _oMultiIdx != undefined ) {

            var attrLayer = _oMap.getLayerByName('GAttrLayer');
            var multiLayer = _oMap.getLayerByName('GAttrLayerMulti');

            // JOY. 2017. 11. 27.
            var idxArr = [ _oAttrIdx, _oMultiIdx ];

            for ( var i = 0; i < idxArr.length; i++ ) {

                if ( idxArr[i] == 0 ) {

                    idxArr[i] = 2;

                } else if ( idxArr[i] == 1 ) {

                    idxArr[i] = 3;

                }

            }

            _oMap.setLayerIndex(attrLayer, idxArr[0]);
            _oMap.setLayerIndex(multiLayer, idxArr[1]);

        }

        _oMap.getLayerByName('GAttrLayer').removeFeatures(
                _oMap.getLayerByName('GAttrLayer').features);

        var feature = GRequest.WFS.getFeatureByComparison(
                            CONFIG.fn_get_serviceUrl(),
                                {
                                    maxFeatures : 9999,
                                    prefix : CONFIG.fn_get_dataHouseName(),
                                    type : "==",
                                    tables : _oTables, //["DORO_TOT_GRS80_50"],
                                    fields :_oFields, //[["ROAD_NO","SECT"]]
                                    values : _oValues, //[[roadNo, sect]]
                                    sortFields : [],
                                    sortOrders : [],
                                    useDomain : false,
                                    logicalOper : _oLogicOper

                                },
                                function(res) {
                                    if(res.data.length < 1){return;}
                                    var attribute = null;

                                    var attrExistYn = false;

                                    for(var i=0; i< res.data.length; i++){
                                        for ( var j = 0, resultLen = res.data[i].results.length; j < resultLen; j++) {
                                            if(_oAttribute != undefined){
                                                attribute = fn_get_attribute(_oAttribute, res.data[i].results[j]);
                                                if(attribute != null){
                                                    attrExistYn = true;
                                                }
                                            }

                                            if(attribute == null){
                                                // 지도에 도형 표출
                                                res.data[i].results[j].feature.attributes = {
                                                    fillColor : '#ff0000',
                                                    strokeColor : '#ff0000'
                                                };
                                            }else{
                                                res.data[i].results[j].feature.attributes = attribute;
                                            }

                                            res.data[i].results[j].feature.data = res.data[i].results[j].fields;

                                            _oMap.getLayerByName('GAttrLayer').addFeatures(res.data[i].results[j].feature);

                                            if(typeof _oMarkerTable != "undefined" && _oMarkerTable == res.data[i].table) {
                                                var point = res.data[i].results[j].feature.geometry.getCentroid();
                                                point.attributes = {
                                                        fillColor : '#ff0000',
                                                        strokeColor : '#ff0000',
                                                        pointRadius : 4
                                                }

                                                var newFeature = new OpenLayers.Feature.Vector(point, point.attributes);

                                                _oMap.getLayerByName('GAttrLayer').addFeatures(newFeature);

                                            }
                                        }
                                    }

                                    if(_oAttribute != undefined && _oAttribute.field != undefined && _oAttribute.value != undefined && !attrExistYn){
                                        alert("위치정보가 존재하지 않습니다.");
                                    }

                                    if ( _oZoom != false ) {

                                       _oMap.zoomToExtent(_oMap.getLayerByName('GAttrLayer').getDataExtent());

                                    }

                                }
                            );
    }





    /////////////////////////////////////////////////////////////////////////////////
    /**
    * 2017.09.08
    * MultiSelect 시 전체 노선 선택을 위한 Function
    *  - 선택 된 모든 노선/셀을 그려주는 레이어
    *
    * @description 속성값을 이용해 해당 feature를 가져온다.
    * @param {Object} _oMap : 지도 객체
    * @param {Object} _oTables : 조회할 테이블 리스트
    * @param {Object} _oFields : 검색할 필드 리스트
    * @param {Object} _oValues : 검색할 속성 값 리스트
    * @param {String} _oMarkerTable : point를 추가 표출할 테이블명
    * @param {String} _oLogicOper : 논리 연산자 (AND / OR)
    * @param {Object} _oAttribute : style를 변경할 속성정보
    * @param {Object} _oZoom : zoom 허용 여부
    * @param {Object} _oAttrIdx : Multi Layer 사용 시 GAttrLayer 순서 Index
    * @param {Object} _oMultiIdx : Multi Layer 사용 시 GAttrLayerMulti 순서 Index
    */
    var fn_get_selectFeatureByAttrMulti = function(_oMap, _oTables, _oFields, _oValues, _oMarkerTable, _oLogicOper, _oAttribute, _oZoom, _oAttrIdx, _oMultiIdx){

        var attrLayer = _oMap.getLayerByName('GAttrLayer');
        var multiLayer = _oMap.getLayerByName('GAttrLayerMulti');

        // JOY. 2017. 11. 27.
        var idxArr = [ _oAttrIdx, _oMultiIdx ];

        for ( var i = 0; i < idxArr.length; i++ ) {

            if ( idxArr[i] == 0 ) {

                idxArr[i] = 2;

            } else if ( idxArr[i] == 1 ) {

                idxArr[i] = 3;

            }

        }

        _oMap.setLayerIndex(attrLayer, idxArr[0]);
        _oMap.setLayerIndex(multiLayer, idxArr[1]);

        _oMap.activeControls("drag");

        _oMap.getLayerByName('GAttrLayerMulti').removeFeatures(
                _oMap.getLayerByName('GAttrLayerMulti').features);

        var feature = GRequest.WFS.getFeatureByComparison(
                            CONFIG.fn_get_serviceUrl(),
                                {
                                    maxFeatures : 9999,
                                    prefix : CONFIG.fn_get_dataHouseName(),
                                    type : "==",
                                    tables : _oTables, //["DORO_TOT_GRS80_50"],
                                    fields :_oFields, //[["ROAD_NO","SECT"]]
                                    values : _oValues, //[[roadNo, sect]]
                                    sortFields : [],
                                    sortOrders : [],
                                    useDomain : false,
                                    logicalOper : _oLogicOper

                                },
                                function(res) {
                                    if(res.data.length < 1){return;}
                                    var attribute = null;

                                    for(var i=0; i< res.data.length; i++){
                                        for ( var j = 0, resultLen = res.data[i].results.length; j < resultLen; j++) {

                                            if(_oAttribute != undefined){
                                                attribute = fn_get_attribute(_oAttribute, res.data[i].results[j]);
                                            }

                                            if(attribute == null){
                                                // 지도에 도형 표출
                                                res.data[i].results[j].feature.attributes = {
                                                    fillColor : '#0033ff',
                                                    strokeColor : '#0033ff'
                                                };
                                            }else{
                                                res.data[i].results[j].feature.attributes = attribute;
                                            }

                                            res.data[i].results[j].feature.data = res.data[i].results[j].fields;

                                            _oMap.getLayerByName('GAttrLayerMulti').addFeatures(res.data[i].results[j].feature);

                                            if(typeof _oMarkerTable != "undefined" && _oMarkerTable == res.data[i].table) {
                                                var point = res.data[i].results[j].feature.geometry.getCentroid();
                                                point.attributes = {
                                                        fillColor : '#0033ff',
                                                        strokeColor : '#0033ff',
                                                        pointRadius : 4
                                                }

                                                var newFeature = new OpenLayers.Feature.Vector(point, point.attributes);

                                                _oMap.getLayerByName('GAttrLayerMulti').addFeatures(newFeature);
                                            }

                                        }
                                    }

                                    if ( _oZoom != false ) {
                                        _oMap.zoomToExtent(_oMap.getLayerByName('GAttrLayerMulti').getDataExtent());

                                    }
                                }
                            );
    }

    var fn_get_selectFeatureByAttrBoundary = function(_oMap, _oTables, _oFields, _oValues, _oMarkerTable, _oLogicOper, _oAttribute, _oZoom, _oAttrIdx, _oMultiIdx, _boundaryFields, _boundaryValues){
        _oMap.activeControls("drag");

        if ( _oAttrIdx != undefined && _oMultiIdx != undefined ) {

            var attrLayer = _oMap.getLayerByName('GAttrLayer');
            var multiLayer = _oMap.getLayerByName('GAttrLayerMulti');

            _oMap.setLayerIndex(attrLayer, _oAttrIdx);
            _oMap.setLayerIndex(multiLayer, _oMultiIdx);

        }

        _oMap.getLayerByName('GAttrLayer').removeFeatures(
                _oMap.getLayerByName('GAttrLayer').features);

        var feature = GRequest.WFS.getFeatureByBoundary(
                            CONFIG.fn_get_serviceUrl(),
                                {
                                    maxFeatures : 9999,
                                    prefix : CONFIG.fn_get_dataHouseName(),
                                    type : "==",
                                    tables : _oTables, //["DORO_TOT_GRS80_50"],
                                    fields :_oFields, //[["ROAD_NO","SECT"]]
                                    values : _oValues, //[[roadNo, sect]]
                                    sortFields : [],
                                    sortOrders : [],
                                    useDomain : false,
                                    logicalOper : _oLogicOper,
                                     boundaryFields : _boundaryFields,
                                        boundaryValues : _boundaryValues

                                },
                                function(res) {
                                    if(res.data.length < 1){return;}
                                    var attribute = null;

                                    var attrExistYn = false;

                                    for(var i=0; i< res.data.length; i++){
                                        for ( var j = 0, resultLen = res.data[i].results.length; j < resultLen; j++) {

                                            if(_oAttribute != undefined){
                                                attribute = fn_get_attribute(_oAttribute, res.data[i].results[j]);
                                                if(attribute != null){
                                                    attrExistYn = true;
                                                }
                                            }

                                            if(attribute == null){
                                                // 지도에 도형 표출
                                                res.data[i].results[j].feature.attributes = {
                                                    fillColor : '#0033ff',
                                                    strokeColor : '#0033ff'
                                                };
                                            }else{
                                                res.data[i].results[j].feature.attributes = attribute;
                                            }

                                            res.data[i].results[j].feature.data = res.data[i].results[j].fields;

                                            _oMap.getLayerByName('GAttrLayer').addFeatures(res.data[i].results[j].feature);

                                            if(typeof _oMarkerTable != "undefined" && _oMarkerTable == res.data[i].table) {
                                                var point = res.data[i].results[j].feature.geometry.getCentroid();
                                                point.attributes = {
                                                        fillColor : '#0033ff',
                                                        strokeColor : '#0033ff',
                                                        pointRadius : 4
                                                }

                                                var newFeature = new OpenLayers.Feature.Vector(point, point.attributes);

                                                _oMap.getLayerByName('GAttrLayer').addFeatures(newFeature);
                                            }

                                        }
                                    }

                                    if(_oAttribute != undefined && _oAttribute.field != undefined && _oAttribute.value != undefined && !attrExistYn){
                                        alert("위치정보가 존재하지 않습니다.");
                                    }

                                    if ( _oZoom != false ) {

                                       _oMap.zoomToExtent(_oMap.getLayerByName('GAttrLayer').getDataExtent());

                                    }

                                }
                            );
    }



    /////////////////////////////////////////////////////////////////////////////////

    /**
    * @description 입력한 속성 스타일 값을 가져온다.
    * @param {Object} _oAttr : 사용자 정의 속성 정보
    * @param {Object} _obj : 검색된 feature
    */
    var fn_get_attribute = function(_oAttr, _obj){

        if(_obj.fields[_oAttr.field] != _oAttr.value){
            var obj = null;
            if(_oAttr.fillColor != undefined || _oAttr.fillColor != null){
                obj = {
                        fillColor : _oAttr.fillColor,
                        strokeColor : _oAttr.strokeColor
                };
            }
            return obj;
         }

        return _oAttr.attributes;
    };

    /////////////////////////////////////////////////////////////////////////////////////
    /**
     * @memberof USV.MAP
     * @method
     * @description 배경지도 영상 조회
     * @author 임상수(2015.10.06)
     * @param {Object}
     *            _oEl : #btn_map_satelite HTML element 개체
     */
    var fn_show_externalSateliteMap = function(maptype) {

        //if ($("#mCtrlSateliteMap").parent("li").hasClass("active")) {
        //  $(".maptool").parent("li").removeClass("active");

        var roadmapControl = document.getElementById('mCtrlSatenomalMap');
        var skyviewControl = document.getElementById('mCtrlSateliteMap');

        if (maptype === 'roadmap') {
            MAP.fn_get_daumMap().setVisibility(true);
            MAP.fn_get_daumMap().setMapMode(1);
            roadmapControl.className = 'selected_btn';
            skyviewControl.className = 'btn';
        } else {
            MAP.fn_get_daumMap().setVisibility(true);
            MAP.fn_get_daumMap().setMapMode(2);
            skyviewControl.className = 'selected_btn';
            roadmapControl.className = 'btn';
        }

        return false;
    };

    /**
     * @memberof USV.MAP
     * @method
     * @description 배경지도(다음 맵) 리턴
     * @author 임상수(2015.07.31)
     * @returns {String} 배경지도(다음 맵)
     */
    var fn_get_daumMap = function() {
        return daumMap;
    }


    /**
     * 경위도 좌표 이동
     * @description 입력한 경위도 좌표로 지도 이동한다.
     */
    //var markers = [];
    var marker;

    function fn_LonLatmove(lon,lat,wndId){

        var currentLatLon = new parent.daum.maps.LatLng(lat, lon);
        var currentLoc = new parent.OpenLayers.LonLat(lon, lat);

        currentLoc.transform(parent.gMap.displayProjection, parent.gMap.getProjection()); // daum -> ginno

        //사용자가 접속한 현재 위치가 수원시를 벗어나면 알림 메세지 표시
        if(!parent.gMap.isValidLonLat(currentLoc)){
            alert("현재위치가 경기도를 벗어났습니다.")
        }else{
            //지도 중심을 이동 시킵니다
            parent.gMap.setCenter(currentLoc,5);

            //마커여러개가 생기는것을 방지 하기 위해 초기화
            if(parent.markersLoc.length != 0){
                parent.markersLoc[0].setMap(null);
                parent.markersLoc = [];
            }

            //마커를 생성합니다
            var marker = new parent.daum.maps.Marker({
                position : currentLatLon
            });

            //마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(parent.MAP.fn_get_daumMap().map);
            parent.markersLoc.push(marker);

            //창 닫기
            //COMMON_UTIL.cmWindowClose(wndId);
        }
    }

    /**
     * @method
     * @description 속성을 이용해 feature 조회하고 callback반환
     *              callback Parameter : result feature List
     * @param {Object} _oParam
     * @author 180323 - wijy
     */

    var fn_get_getFeatureAndDrawByAttr = function(_oParam) {
        var oParam = {
                /*지도 객체*/
                map : gMap,
                /*feature를 표시할 레이어 명칭 (default : GAttrLayer)*/
                layerNm : 'GAttrLayer',
                /*연산type*/
                types : [],
                /*조회할 테이블 목록*/
                tables : [],
                /*검색할 필드 목록*/
                fields : [],
                /*검색할 속성 값 목록*/
                values : [],
                /*정렬할 필드 목록*/
                sortFields : [],
                /*정렬 형태*/
                sortOrders : [],
                /*point marker를 추가 표출할 테이블명*/
                markerTable : [],
                /*논리 연산자 (AND / OR)*/
                logicOper : 'AND',
                /*feature Attribute*/
                attribute : {
                    attributes : {
                        fillColor : '#ff0000',
                        strokeColor : '#ff0000'
                    }
                },
                layerIndex : 0,                     /*레이어 표출순서*/
                clearBeforeDraw : false,            /*결과표시전에 대상 레이어 clear여부*/
                callback : null,
                bDrawFeats : true                   /*도형 그릴지 여부*/
        };

        if(_oParam != null && typeof _oParam != 'undefined') {
            OpenLayers.Util.extend(oParam, _oParam);
        }

        var oLayer = oParam.map.getLayerByName(oParam.layerNm);
        oParam.map.setLayerIndex(oLayer, oParam.layerIndex);

        //클리어
        if(oParam.clearBeforeDraw) {
            oLayer.removeAllFeatures();
            oLayer.removeFeatures(oLayer.features);
        }

        //WFS요청 전송
        var feature = GRequest.WFS.getFeatureByComparison(CONFIG.fn_get_serviceUrl(), {
            maxFeatures : 9999,
            prefix : CONFIG.fn_get_dataHouseName(),
            type : oParam.types,
            tables : oParam.tables,
            fields : oParam.fields,
            values : oParam.values,
            sortFields : oParam.sortFields,
            sortOrders : oParam.sortOrders,
            useDomain : false,
            logicalOper : oParam.logicOper
        }, cb_get_feature);

        //callback
        function cb_get_feature(res) {
            var attrExistYn = false;
            var attr = null;

            //반환값
            var oReturn = {
                result : {code : 'ERR',msg : ''},
                features : []
            };

            if(res.data.length > 0) {
                oReturn.result = {code : 'SUCCESS', msg : '성공'};

                //feature list 생성
                for(var i in res.data) {
                    var oData = res.data[i];
                    var oTab = {
                        tableNm : oData.table,
                        resultFeature : []
                    };

                    for(var j in oData.results) {
                        var oFeat = oData.results[j];

                        if(oParam.attribute != null) {
                            attr = MAP.fn_get_attribute(oParam.attribute, oFeat);
                            if(attr != null) attrExistYn = true;
                        }
                        oFeat.feature.data = oFeat.fields;
                        oFeat.feature.attributes = attr;

                        var oLayer = oParam.map.getLayerByName(oParam.layerNm);
                        if(oParam.bDrawFeats)
                            oLayer.addFeatures(oFeat.feature);

                        if(typeof oParam.markerTable != 'undefined' && oParam.markerTable == oData.table) {
                            var oPoint = oFeat.feature.geometry.getCentroid();
                            oPoint.attributes = {
                                    fillColor : '#ff0000',
                                    strokeColor : '#ff0000',
                                    pointRadius : 4
                            }

                            var newFeat = new OpenLayers.Feature.Vector(oPoint, oPoint.attributes);
                            if(oParam.bDrawFeats)
                                oLayer.addFeatures(newFeat);
                        }
                        oTab.resultFeature.push(oFeat);
                    }
                    oReturn.features.push(oTab);
                }

                if(oParam.fields.length > 0 && oParam.values.length > 0 && !attrExistYn)
                    oReturn.result = {code : 'NO_RESULT', msg : '결과 없음'};
            } else {
                oReturn.result = {code : 'NO_RESULT', msg : '결과 없음'};
            }

            if(typeof oParam.callback == 'function')
                oParam.callback(oReturn);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
    * @description 입력한 속성 스타일 값을 가져온다.
    * @param {Object} _oAttr : 사용자 정의 속성 정보
    * @param {Object} _obj : 검색된 feature
    */
    var fn_get_attribute = function(_oAttr, _obj){

        if(_obj.fields[_oAttr.field] != _oAttr.value){
            var obj = null;
            if(_oAttr.fillColor != undefined || _oAttr.fillColor != null){
                obj = {
                        fillColor : _oAttr.fillColor,
                        strokeColor : _oAttr.strokeColor
                };
            }
            return obj;
         }

        return _oAttr.attributes;
    };


    /**
     * @description 등록중인 위치 정보를 삭제한다.
     * @param {String} table : 테이블명
     * @param {String} value : 삭제할 feature G2_ID
     */
     var fn_del_feature = function(table, value) {

    	 var msg = "0";

    	 //alert("table : " + table + " / value : " + value);

    	 GRequest.WFS.del(
    			    CONFIG.fn_get_serviceUrl(),
					CONFIG.fn_get_dataHouseName(),
					table,
					value,
					function(res) {
						//alert("table : " + table + " / value : " + value + " / del : " + res);
						msg = "1";
					});

    	 return msg;

     };



    return {
        fn_init_map                 :               fn_init_map,

        fn_create_map               :               fn_create_map,
        fn_create_daumMap           :               fn_create_daumMap,

        fn_init_wms                 :               fn_init_wms,
        fn_init_vector              :               fn_init_vector,

        fn_save_map                 :               fn_save_map,
        fn_encoding_map             :               fn_encoding_map,

        fn_get_resolution           :               fn_get_resolution,
        fn_event_moveend            :               fn_event_moveend,
        fn_get_scale                :               fn_get_scale,
        fn_get_StyleMap             :               fn_get_StyleMap,
        fn_update_resizeMap         :               fn_update_resizeMap,

        fn_move_map                 :               fn_move_map,

        fn_get_selectFeatureByAttr  :               fn_get_selectFeatureByAttr,
        fn_get_selectFeatureByAttrMulti:            fn_get_selectFeatureByAttrMulti,
        fn_get_selectFeatureByAttrBoundary:         fn_get_selectFeatureByAttrBoundary,
        fn_show_externalSateliteMap :               fn_show_externalSateliteMap,
        fn_get_daumMap              :               fn_get_daumMap,

        fn_LonLatmove               :               fn_LonLatmove,
        statusStyle                 :               statusStyle,
        dmgtStyle                   :               dmgtStyle,
        overlapStyle                :               overlapStyle,
        //fn_layer_control            :               fn_layer_control

        fn_get_getFeatureAndDrawByAttr :            fn_get_getFeatureAndDrawByAttr,
        fn_get_attribute            :               fn_get_attribute,
        fn_del_feature				:				fn_del_feature

    }
}(jQuery));

