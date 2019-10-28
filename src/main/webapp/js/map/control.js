
MAP.CONTROL = (function($, undefined){


	/**
	 * 속성 컨트롤 Tool
	 * @member {Object} attrControls
	 */
	var attrControls = null;

	/**
	 * 포장 셀 리스트
	 * @member {Array} selectFidList
	 */
	var selectFidList = [];


	/**
	 * 포장 셀 교차로 리스트
	 * @member {Array} crossCellList
	 */
	var crossCellList = [];

	/**
     * 조사자료 조회 시 여러 셀 선택 되었을 경우 셀 리스트
     * @member {Array} multiCellList
     */
    var multiCellList = [];

	/**
	 * 포장 셀 컨트롤 옵션
	 * @member {Object} option
	 */
	var option = {}

	//180807 wijy
    /**
     * 통합검색 셀 선택시 노선, 방향별 결과목록
     * @member {Object} oCellsPerRD
     */
    var oCellsPerRD = {};

    //180809 wijy
    //통합검색 셀 시종점입력검색
    var listDef = [$.Deferred(), $.Deferred(), $.Deferred()];


	/**
	* @description 지도 컨트롤 이벤트를 추가한다.
	*/
	var fn_add_mapControl = function() {
		fn_init_attr('');
		init_cellSel();
		init_selSectControls();

		init_inteSelRoad();
		init_inteSelResearch();
		// YYK 201802119
		init_inteSelSttemnt();
		//init_inteSelDmgt();
		init_inteSelRange();
		init_inteSelBrdg();
		init_cellSelLonLat();

		//2019년 고도화 사업 - 포트홀신고정보(시군관리) 편집용 컨트롤 추가
        init_editPothole();

		/**
		* 지도 기본 컨트롤
		*/
		// 경위도 위치 이동
		$("#mCtrlLonLatMove").bind("click", function() {
			COMMON_UTIL.cmWindowOpen('경위도 좌표 이동', contextPath + 'selectLonLatMoveView.do',590, 100, false, null, 'mvLonlan');
		});

		// 지도 이동
		$("#mCtrlPan").bind("click", function() {
			/*
			$("#goodMap .top-tool #status").hasClass('on') ?
				gMap.activeControls(['stteSelectFeature', 'drag']) : gMap.activeControls(['dmgtSelectFeature', 'drag'])
			*/
			gMap.activeControls("drag");
		});

		// 지도 확대
		$("#mCtrlZoomIn").bind("click", function() {
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'zoomIn']) : gMap.activeControls(['dmgtSelectFeature', 'zoomIn'])*/
			gMap.activeControls("zoomIn");
		});

		// 지도 축소
		$("#mCtrlZoomOut").bind("click", function() {
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'zoomOut']) : gMap.activeControls(['dmgtSelectFeature', 'zoomOut'])*/
			gMap.activeControls("zoomOut");
		});

		// 전체영역으로 이동
		$("#mCtrlFullExt").bind("click", function() {
			gMap.zoomToMaxExtent();
			/*$("#goodMap .top-tool #status").hasClass('on') ?
				gMap.activeControls(['stteSelectFeature', 'drag']) : gMap.activeControls(['dmgtSelectFeature', 'drag'])*/
			gMap.activeControls("drag");
		});

		// 이전화면으로 이동
		$("#mCtrlPrev").bind("click", function() {
			gMap.movePrev();
		});

		// 다음화면으로 이동
		$("#mCtrlNext").bind("click", function() {
			gMap.moveNext();
		});

		// 새로고침 (지도 내 명칭 '초기화')
		$("#mCtrlClear").bind("click", function() {
			if ( $('#header #gnb').hasClass('good-header') ){
				gMap.cleanMeasure();
				/*$("#goodMap .top-tool #status").hasClass('on') ?
						gMap.activeControls(['stteSelectFeature', 'drag']) : gMap.activeControls(['dmgtSelectFeature', 'drag'])*/
				gMap.activeControls("drag");
				gMap.getLayerByName('GAttrLayer').removeAllFeatures();
				gMap.getLayerByName('SttemntLayer').removeAllFeatures();
				gMap.getLayerByName('DmgtLayer').removeAllFeatures();
				gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
				gMap.getLayerByName('GOverlapLayer').removeAllFeatures();

				$('#locDraw').hide();
				return;
			}
			gMap.cleanMap();
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'drag']) : gMap.activeControls(['dmgtSelectFeature', 'drag'])*/
			gMap.activeControls("drag");
		});

		// 거리 측정
		$("#mCtrlMesureLength").bind("click", function() {
			if ( $('#header #gnb').hasClass('good-header') ){
				/*$("#goodMap .top-tool #status").hasClass('on') ?
						gMap.activeControls(['stteSelectFeature', 'measurePath']) : gMap.activeControls(['dmgtSelectFeature', 'measurePath'])*/
				gMap.activeControls("measurePath");
				return;
			}
			gMap.cleanMap();
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'measurePath']) : gMap.activeControls(['dmgtSelectFeature', 'measurePath'])*/
			gMap.activeControls("measurePath");
		});

		// 면적 측정
		$("#mCtrlMesureArea").bind("click", function() {
			if ( $('#header #gnb').hasClass('good-header') ){
				/*$("#goodMap .top-tool #status").hasClass('on') ?
						gMap.activeControls(['stteSelectFeature', 'measurePolygon']) : gMap.activeControls(['dmgtSelectFeature', 'measurePolygon'])*/
				gMap.activeControls("measurePolygon");
				return;
			}
			gMap.cleanMap();
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'measurePolygon']) : gMap.activeControls(['dmgtSelectFeature', 'measurePolygon'])*/
			gMap.activeControls("measurePolygon");
		});

		//단일통합조회
		$("#mCtrlIdentify").bind("click", function(){
			//마우스 포인터 변경
			fn_cursor_map('attrPoint');
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'attrPoint']) : gMap.activeControls(['dmgtSelectFeature', 'attrPoint'])*/
			gMap.activeControls("attrPoint");
			//범위 추가
			//gMap.getControl("attrPoint").setDistance(50);
			gMap.cleanMap();
		});

		//다각형통합조회
		$("#mCtrlIdentifyPoly").bind("click", function(){
			/*$("#goodMap .top-tool #status").hasClass('on') ?
					gMap.activeControls(['stteSelectFeature', 'attrPolygon']) : gMap.activeControls(['dmgtSelectFeature', 'attrPolygon'])*/
			gMap.activeControls("attrPolygon");
			gMap.cleanMap();
		});

		// 지도 인쇄
		$("#mCtrlMapPrint").bind("click", function() {
			var center = gMap.getCenter();
			var oTrans_sLatLng = center.transform(parent.gMap.getProjection(), daumMap.projection.projCode);
			searchAddrFromCoords(new kakao.maps.LatLng(oTrans_sLatLng.lat, oTrans_sLatLng.lon), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
		    // 좌표로 행정동 주소 정보를 요청합니다
		    var geocoder = new kakao.maps.services.Geocoder();
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);

		}

		function displayCenterInfo(result, status) {
		    if (status === kakao.maps.services.Status.OK) {

            	var mapType;
    			if($("#mCtrlSateliteMap").hasClass("selected_btn") == true){
    				mapType = "skyView";
    			}else{
    				mapType = "staticMap";
    			}

    			var popWidth = $(window).width();
    			var popHeight = $(window).height();
                var initData = {
    				address : result[0].address.address_name
    			};

    			COMMON_UTIL.cmWindowOpen('현재 화면 인쇄', contextPath + 'printImgMap.do?mapType='+mapType, popWidth * 0.7 , popHeight * 0.7+ 110, false, null, 'center', initData);
		    }
		}

		// 지도 이미지
		$("#mCtrlPrint").bind("click", function() {
			var mapType;
			if($("#mCtrlSateliteMap").hasClass("selected_btn") == true){
				mapType = "skyView";
			}else{
				mapType = "staticMap";
			}
			COMMON_UTIL.cmWindowOpen('지도 이미지 저장', contextPath + 'saveImgMap.do?mapType='+mapType,350, 100, true, null, 'center');
		});

		// 축적 이동
		$('#scale').bind("keypress", function(e) {
			var scale = $(this).val();

			if (scale != "" && e.which == 13) // the enter key code
				gMap.zoomToScale(scale);
		});

		//지도 컨트롤 활성화 ui
		/*$(".maptool").bind("click", function() {
			$(".maptool").parent("li").removeClass("active");
			$(this).parent("li").addClass("active");
		});*/

		// 17.10.16 JOY 수정
		// 18.05.25.YYK 추가 수정
		// : 위치통합검색/경위도에서 지도조작 클릭시에는 팝업 안 닫힘, 위치통합/경위도 끼리는 닫힘
		//지도 컨트롤 활성화 ui
        $(".maptool").bind("click", function() {
            $(".maptool").parent("li").removeClass("active");
            $("#researchInfo").parent().removeClass("active"); // 조사정보 control 초기화
            $(this).parent("li").addClass("active");

            // 다른 팝업이 열리는 경우 활성화 된 조회 컨트롤 닫기
            var wndpop = $.window.getAll();

            for ( var i = 0; i < wndpop.length; i++ ) {

                var wndid = wndpop[i].getWindowId();

                if ( $("#" + wndid).find("iframe").contents().find("body").hasClass("left-tool") ) {
                	if ($(this).hasClass('t1') || $(this).hasClass('t2')) {
                		wndpop[i].close();
                	}
                }

            }
        });

		// 조회 컨트롤 활성화 ui
        $(".selecttool").bind("click", function() {

            $(".selecttool").parent("li").removeClass("active");
            $(this).parent("li").addClass("active");

            // 다른 팝업이 열리는 경우 활성화 된 조회 컨트롤 닫기
            var wndpop = $.window.getAll();
            var len = wndpop.length;

            for ( var i = len - 1; i >= 0; i-- ) {

                var wndid = wndpop[i].getWindowId();

                if ( $("#" + wndid).find("iframe").contents().find("body").hasClass("right-tool")
                        || $("#" + wndid).find("iframe").contents().find("body").hasClass("research") ) {

                    if ( $("#" + wndid).find("iframe").contents().find("body").hasClass("research") ) {

                        rshInfoCnt--;
                        $(".selecttool").parent("li").removeClass("active");
                        $("#mCtrlPan").parent().addClass("active");
                        gMap.cleanMap();
                        gMap.activeControls("drag");
                        bottomClose();

                    }

                    wndpop[i].close();

                }

            }

        });

		//위치 통합조회
		$("#mCtrlLocSearch").bind("click", function(){
			COMMON_UTIL.cmWindowOpen('위치 통합검색(키워드검색)', contextPath + 'gmap/selectLocation.do',322, 100, false, null, 'locsearch');
		});

		/**
		* 속성조회
		*/
		//속성창닫기
		$(".btnCloseAttr").bind("click", function(){
			$(".divAttr").hide();
		});

		/**
		* 포장 셀 등록
		*/
		// 포장 셀 등록
		$("#dvChoiceNSRes").dialog({
			width: 400,
			height:150,
			autoOpen : false,
			position : {
				my : "right-230 center+80", at : "center", of : window
			}
		});

		//사용안함
		/*
		$("#btn_cellSelectWithClick").bind("click", function(){
			//마우스 포인터 변경
			gMap.activeControls("cellPoint");

			//도움말
			$("#dv_cellSelectionHelp span:eq(0)").css("display", "inline-block");
			$("#dv_cellSelectionHelp span:eq(1)").css("display", "none");
		});
		*/

		//181106 wijy 추가
		//다중선택 Open
		$("#btn_openMultiSelectDv").on("click", function(e) {
			//다중선택창 open
			var bHasOn = $(this).hasClass("on");
			if(bHasOn) {
				$("#dv_multiSelectPoly").slideUp();
				$(this).removeClass("on");

				//노선검색부분
				$("#dv_inputForSelectCells").slideUp();
				//$("#btn_cellSelectWithInput").removeClass("on");
				//$("#btn_cellSelectWithInput").parent().removeClass("sel");

				//도움말
				$("#dv_cellSelectionHelp").slideUp();
			} else {
				$("#dv_multiSelectPoly").slideDown();
				$(this).addClass("on");
			}
			$("#dv_multiSelectPoly").find("li").removeClass("sel");
		});

		//노선입력 Open
		/*$("#btn_cellSelectWithInput").on("click", function(e) {
			var bHasOn = $(this).hasClass("on");
			if(bHasOn) {
				$("#dv_inputForSelectCells").slideUp();
				$(this).removeClass("on");
				$(this).parent().removeClass("sel");
			} else {
				//노선binding 및 초기화

				fn_set_routeInfo();
				$("#sel_cs_route option:eq(0)").prop("selected", true);
			    $("#sel_cs_direct").html("<option value=''></option>");
			    $("#sel_cs_track").html("<option value=''></option>");
			    $("#ip_cs_strtpt").val("0");
			    $("#ip_cs_endpt").val("0");

				$("#dv_inputForSelectCells").slideDown();
				$(this).addClass("on");
			}

			//도움말
			$("#dv_cellSelectionHelp").slideUp();
		});*/

		//다각형 선택 클릭
		$("#btn_cellSelectWithPolygon").on("click", function(){
			//노선검색부분
			$("#dv_inputForSelectCells").slideUp();
			//$("#btn_cellSelectWithInput").removeClass("on");
			//$("#btn_cellSelectWithInput").parent().removeClass("sel");
			//도움말
			$("#dv_cellSelectionHelp").slideDown();

			//컨트롤 On
			gMap.activeControls(["drag", "cellPolygon"]);
		});

		//선형선택 클릭
		$("#btn_cellSelectWithPolyline").on("click", function(e) {
			//노선검색부분
			$("#dv_inputForSelectCells").slideUp();
			//$("#btn_cellSelectWithInput").removeClass("on");
			//$("#btn_cellSelectWithInput").parent().removeClass("sel");

			//도움말
			$("#dv_cellSelectionHelp").slideDown();

			//컨트롤 On
			gMap.activeControls(["drag", "cellPath"]);
		});


		//노선검색 events
		//노선 변경
		$("#sel_cs_route").on("change keyup", function(e) {
			var sSelRoute = $("#sel_cs_route option:selected").val();
			if(sSelRoute != "") fn_set_routeDir(sSelRoute);
		});

		//방향 변경
		$("#sel_cs_direct").on("change keyup", function(e) {
			var sSelRoute = $("#sel_cs_route option:selected").val();
			var sSelDir = $("#sel_cs_direct option:selected").val();

			if(sSelRoute != "" && sSelDir != "") fn_set_track(sSelRoute, sSelDir);
		});

		//노선추가 클릭
		$("#btn_addCellsFromInput").on("click", function(e) {
			var sRouteCode = $("#sel_cs_route option:selected").val();
			var sDirectCode = $("#sel_cs_direct option:selected").val();
			var sTrack = $("#sel_cs_track option:selected").val();
			var sStrtpt = $("#ip_cs_strtpt").val();
			var sEndpt = $("#ip_cs_endpt").val();

			if(sStrtpt == '') sStrtpt = 0;
			if(sEndpt == '') sEndpt = 0;

			//값 체크
			var reg = /^[0-9]*$/;
			if(!reg.test(sStrtpt)) {
				alert("시점은 숫자만 입력 가능합니다.");
				return false;
			}
			if(!reg.test(sEndpt)) {
				alert("종점은 숫자만 입력 가능합니다.");
				return false;
			}

			//노선정보 검색
			MAP.fn_get_getFeatureAndDrawByAttr({
	            map : parent.gMap,
	            layerNm : "GAttrLayerMulti",
	            types : [["==", "==", "==", ">=", "<="]],
	            tables : ["CELL_10"],
	            fields : [["ROUTE_CODE", "DIRECT_CODE", "TRACK", "STRTPT", "ENDPT"]],
	            values : [[sRouteCode, sDirectCode, sTrack, sStrtpt, sEndpt]],
	            attribute : {
	                attributes : {
	                     fillColor : '#0033ff',
	                     strokeColor : '#0033ff'
	                }
	            },
	            clearBeforeDraw : false,
	            callback : cb_cellSelWithInputFeats
	        });
		});


		$("#btn_cellSelectReset").bind("click", function(){
			gMap.cleanMap();
			gMap.activeControls("drag");
			selectFidList = [];

			//도움말
			$("#dv_cellSelectionHelp").slideUp();
			/*$("#dv_cellSelectionHelp span:eq(0)").css("display", "none");
			$("#dv_cellSelectionHelp span:eq(1)").css("display", "none");*/
		});

		$("#btn_cellSelectFinish").bind("click", function(){
			gMap.activeControls("drag");

			var cellIds = "";

			
			// 수정 사유: 멀티노선을 선택하였을 경우 - 노선 선택 팝업창에서 GAttrLayer 레이어를 사용하고 있어서 기능이 제대로 작동하지 않는다.
			//var features = gMap.getLayerByName('GAttrLayerMulti').features;
			var features = gMap.getLayerByName('GAttrLayer').features;

			/* 2018.11.05 200개 제한 제거
			if(features.length > 200){
				alert("선택한 셀의 수가 너무 많습니다.\n공사구간을 나눠서 등록해주시기 바랍니다.(최대 200개)");
				return;
			}
			 */

			if(features.length < 1){
				alert("선택된 데이터가 없습니다. 지도에서 선택을 해주십시오.");
				return;
			}

			for(var i in features){
				if(i!=0){cellIds += ","}
				var cell_id = features[i].data.CELL_ID || features[i].data.cell_id;
				cellIds += cell_id;
			}

			option.cellCount = features.length;

			try {
				$("#dvCellSelctionTool").dialog("close");
				$("#dvChoiceNSRes").dialog("close");		//혹시 열려있을 노선선택팝업

				if(option.clearMap == undefined || option.clearMap){
					gMap.cleanMap();
				}
				gMap.activeControls("drag");

				option.iframe[option.callback](cellIds, option);

				selectFidList = [];

				wWindowShowAll();
				bottomOpen();

				$('#dvRestore').dialog();
				$('#dvRestore').dialog('close');

			} catch(e){

			    alert(e);
			    return;

			}
		});

		//181107 wijy 부모객체에서만 event처리되도록 수정
		$("#dvCellSelctionTool .toolbtn li a").on("click", function(e) {
			$(this).parents(".toolbtn").find("li").removeClass("sel");
			$(this).parent().addClass("sel");
		});

		select_mouseEvent();
		select_SctnMouseEvent();
	};

	//왼쪽 채우기
	function fillLeft(value, n) {
	    var len = value.length;
	    var max = parseInt(n);
	    if ( len > max ) return value;
	    else {
	        var result = "";
	        for ( var i = 0; i < max - len; i++ ) result += "0";
	        return result + value;
	    }
	}

	//노선검색 후 콜백
	var cb_cellSelWithInputFeats = function(data) {
		var oRes = data.result;
		if(oRes == null || oRes.code == "NO_RESULT") {
			alert("결과가 없습니다. 검색값을 확인해 주십시오.");
		} else {
		}

	}

	/** 포장공사 위치입력 : 노선검색 위치추가 */
	//노선목록 세팅
	var fn_set_routeInfo = function() {
		 $.ajax({
	        url : contextPath + 'api/ms/selRouteName.do',
	        type : 'POST',
	        dataType : 'json',
	        success :cb_set_routeInfo,
	        error : function(a, b, c) {
	            //error
	        }
	    });
	}

	var cb_set_routeInfo = function(data) {
		var list = data.routeNm;
	    if(list != null && list.length > 0) {
	        var sHtml = '<option value="">노선 선택</option>';
	        for(var i in list) {
	            var oData = list[i];
	            sHtml += '<option value="' + fillLeft(oData.CODE_VAL, 4) + '">' + '[' +  oData.CODE_VAL + ']  ' + oData.CODE_NM + '</option>';
	        }
	        $('#sel_cs_route').html(sHtml);
	    } else {
	        //error
	    }
	}


	//방향 세팅
	var fn_set_routeDir = function(_routeCode) {
		$.ajax({
	        url : contextPath + 'api/ms/selRouteDirection.do',
	        type : 'POST',
	        dataType : 'json',
	        data : {ROUTE_CODE : _routeCode},
	        success : cb_set_routeDir,
	        error : function(a, b, c) {
	            //error
	        }
	    });
	}

	var cb_set_routeDir = function(data) {
		var list = data.routeDir;
	    if(list != null && list.length > 0) {
	        var sHtml = '';
	        for(var i in list) {
	            var oData = list[i];
	            sHtml += '<option value="' + oData.CODE + '">' + oData.VAL + '</option>';
	        }
	        $("#sel_cs_direct").html(sHtml);
	        $("#sel_cs_direct option:eq(0)").trigger("change");
	    } else {
	        //error
	    }
	}


	//차로 세팅
	var fn_set_track = function(_routeCode, _directCode) {
		 $.ajax({
	        url : contextPath + 'api/ms/selRouteTrack.do',
	        type : 'POST',
	        dataType : 'json',
	        data : {ROUTE_CODE : _routeCode, DIRECT_CODE : _directCode},
	        success : cb_set_track,
	        error : function(a, b, c) {
	            //error
	        }
	    });
	}

	var cb_set_track = function(data) {
		var list = data.track;
	    if(list != null && list.length > 0) {
	        var sHtml = '';
	        for(var i in list) {
	            var oData = list[i];
	            sHtml += '<option value="' + oData.TRACK + '">' + oData.TRACK + '</option>';
	        }
	        $("#sel_cs_track").html(sHtml);
	        $("#sel_cs_track option:eq(0)").trigger("change");
	    } else {
	        //error
	    }
	}



	/** 속성 조회 */

	/**
	* @description 속성 조회 컨트롤을 추가한다.
	* @param {String} _oChkTable : 체크할 테이블명
	*/
	var fn_init_attr = function(_oChkTable) {
		//test code insert
		var table=[];

		if(_oChkTable == '' || _oChkTable == null){
			for(var i=0; i<gMap.baseLayer.params.LAYERS.length; i++){
				//뷰레이어일경우에만 속성조회
				if(gMap.baseLayer.params.LAYERS[i].indexOf("MV_") == 0){
					table.push(gMap.baseLayer.params.LAYERS[i]);
				}
			}
		}

		//속성조회 컨트롤 생성
		attrControls = {
			point : new GGetFeature(GPoint, {
	            //serviceUrl : CONFIG.fn_get_serviceUrl(),
	            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
				prefix : CONFIG.fn_get_dataHouseName(),
				tables : table,
				persist	: true,
				excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
				titles : [ "nscode", "bjd_nam", "hjd_nam" ],
				id : "attrPoint",
				alias : true
			}),
			polygon : new GGetFeature(GPolygon, {
				persist	: false,
	            //serviceUrl : CONFIG.fn_get_serviceUrl(),
	            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
				prefix : CONFIG.fn_get_dataHouseName(),
				tables : table,
				excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
				id : "attrPolygon",
				alias : true
			})
		};

		for ( var i in attrControls) {
			attrControls[i].events.on( {
				"callback" : fn_event_attr
			});

		  // 속성 조회 컨트롤 추가
			gMap.addControl(attrControls[i]);

		}
	};

	/**
	* @description 속성 조회 callback
	*/
	var fn_event_attr = function(res) {
		if(typeof $("#dvMapLoading") == "object") $("#dvMapLoading").hide();
		if (res.success()) {
			if (res.data.length == "0") {
				//영역내에 해당하는 자료가 없음
				alert('검색 결과가 없습니다.');

				//거리 표시 삭제
				for(var i=gMap.popups.length-1; i >=0; i--) {
					if(gMap.popups[i].type == "attrCircle") {
						gMap.removePopup(gMap.popups[i]);
					}
				}

				//검색 조건 피쳐 삭제
				/*if(gMap.getControl("attrPoint") && gMap.getControl("attrPoint").handler && gMap.getControl("attrPoint").handler.layer && gMap.getControl("attrPoint").handler.layer.features && gMap.getControl("attrPoint").handler.layer.features.length > 0) {
					gMap.getControl("attrPoint").handler.layer.removeAllFeatures();

				}*/
			}else {
				fn_create_attrTitle(res);
			}

		} else {
			//요청 실패
			alert('요청 실패');
		}
	};

	/**
	* @description 속성 조회 대상 목록을 생성한다.
	* @param {Object} _obj : 속성 조회 대상 객체
	*/
	var fn_create_attrTitle = function(_obj) {
		 //거리 표시 삭제
		 for(var i=gMap.popups.length-1; i >=0; i--) {
			 if(gMap.popups[i].type == "attrCircle") {
				 gMap.removePopup(gMap.popups[i]);
			 }
		 }

		//검색 조건 피쳐 삭제
		if(gMap.getControl("attrPoint") && gMap.getControl("attrPoint").handler && gMap.getControl("attrPoint").handler.layer && gMap.getControl("attrPoint").handler.layer.features && gMap.getControl("attrPoint").handler.layer.features.length > 0) {

			gMap.getControl("attrPoint").handler.layer.removeAllFeatures();

		}

		// 이전 결과 삭제
		gMap.getLayerByName('GAttrLayer').removeFeatures(
				gMap.getLayerByName('GAttrLayer').features);

		// 속성 검색 결과 창 생성
		var tagStr = "";
		for ( var i = 0, len = _obj.data.length; i < len; i++) {
			var  facilityListUrl= "";
			var  g2IdValueList="";
			var  facilityCnt=0;

			//대장 조회 건수 시작
			for ( var j = 0, resultLen = _obj.data[i].results.length; j < resultLen; j++) {
				g2IdValueList=g2IdValueList+_obj.data[i].results[j].g2id+",";
				++facilityCnt;
			}

			//대장 조회 건수 끝
			tagStr += "<h3><span id='divAttList'>" + _obj.data[i].alias[_obj.data[i].table.toUpperCase()] + "</span>&nbsp;"+facilityCnt+"건"+ ""+facilityListUrl+"</h3>";
			tagStr += "<div align='center'>";
			tagStr += "<ul>";

			for ( var j = 0, resultLen = _obj.data[i].results.length; j < resultLen; j++) {
				tagStr += "<li tblInx='" + i + "' retInx='" + j + "' >"
						+ _obj.data[i].results[j].g2id + "</li>";

				// 지도에 도형 표출
				_obj.data[i].results[j].feature.attributes = {
					fillColor : '#ffff00',
					strokeColor : '#ffff00'
				};

				_obj.data[i].results[j].feature.id = _obj.data[i].table + "."
						+ _obj.data[i].results[j].g2id;

				gMap.getLayerByName('GAttrLayer').addFeatures(
						_obj.data[i].results[j].feature);
			}

			tagStr += "</ul>";
			tagStr += "</div>";
		}

		$(".divAttr").dialog({
			title: '속성조회',
			resizable: false,
			height: 450,
			width: 800
		});

		$(".divAttr .popTit h1").text("속성조회");
		$(".divAttr #attContent").html(
				"<div id='divAttMnu'></div><div id='divAttContent'></div>");

		$(".divAttr #attContent #divAttMnu").html(tagStr).accordion({
			autoHeight : false,
			navigation : true
		});

		fn_create_attrContent(_obj, 0, 0);
		$(".divAttr #divAttMnu ul li").click(
				_obj,
				function() {
					fn_create_attrContent(arguments[0].data,
							$(this).attr("tblInx"), $(this).attr("retInx"));
					return false;
				});

	};

	/**
	* @description 지도 위의 마우스 포인터를 변경한다.
	* @param {String} _sCursor : 변경 대상 커서명
	*/
	function fn_cursor_map(_sCursor) {
		if(_sCursor=='attrPoint'){
			//포인터조회
			$("#map").css("cursor", "url('" + contextPath + "images/cur/info_off.cur')");
		}else{
			$("#map").css("cursor", "Hand");
		}
	}

	/**
	* @description 속성 조회 상세 결과 리스트를 생성한다.
	* @param {Object} _obj : 속성조회 결과를 가지고 있는 객체
	* @param {Integer} _oTblInx : 테이블 index
	* @param {Integer} _oRetInx : 선택한 객체 index
	* @param {boolean} _oAlis : alias 여부
	*/
	var fn_create_attrContent = function(_obj, _oTblInx, _oRetInx, _oAlis) {

		//선택한 featuer 초기화
		fn_create_dtlinfo(_obj, _oTblInx, _oRetInx);
		/* 테이블 스타일 수정*/
		$("#tbIdenti").css("margin-left","5px");
		$("#tbIdenti td").css("text-align","center");

		var features = gMap.getLayerByName('GAttrLayer').features;
		for ( var i = features.length - 1; i >= 0; i--) {
			if (features[i].attributes.select) {
				gMap.getLayerByName('GAttrLayer').removeFeatures(features[i]);
			}
		}

		if (_oAlis) {
			_obj.data[0].results[0].feature.attributes = {
				fillColor : '#ff0000',
				strokeColor : '#ff0000',
				select : true
			};

			gMap.getLayerByName('GAttrLayer').addFeatures(
					_obj.data[0].results[0].feature);
		} else {
			//선택한 것만 빨간색으로 표시
			if (gMap.getLayerByName('GAttrLayer').getFeatureById(
					_obj.data[_oTblInx].table + "."
							+ _obj.data[_oTblInx].results[_oRetInx].g2id)) {
				var feature = gMap.getLayerByName('GAttrLayer').getFeatureById(
						_obj.data[_oTblInx].table + "."
								+ _obj.data[_oTblInx].results[_oRetInx].g2id).clone();
				feature.attributes = {
					fillColor : '#ff0000',
					strokeColor : '#ff0000',
					select : true
				};

				gMap.getLayerByName('GAttrLayer').addFeatures(feature);
			}
		}

		//코드테이블에서 코드값 조회
		var cdeNam = ""; //실제 코드값
		var conFld = ""; //검색 조건 필드
		var tblNam = ""; //테이블
		var cdeVal = ""; //변환 전 코드값
		var flgCde = false;	//코드테이블 여부 구분

		for ( var i in _obj.data[_oTblInx].results[_oRetInx].fields) {
			if(i.toUpperCase() == "DEPT_CODE") { //관리기관
				conFld = "DEPT_CODE";
				cdeNam = "LOWEST_DEPT_NM";
				tblNam = "TC_DEPT";
				flgCde = true;
			}else if(i.toUpperCase() == "ROAD_GRAD") { //도로등급
				conFld = "CODE_VAL";
				cdeNam = "CODE_NM";
				tblNam = "TC_CODE";
				flgCde = true;
			}else if(i.toUpperCase() == "CELL_TYPE") { //셀 종류
				conFld = "CODE_VAL";
				cdeNam = "CODE_NM";
				tblNam = "TC_CODE";
				flgCde = true;
			}else if(i.toUpperCase() == "CNTL_DFECT") { //지배결함
				conFld = "CODE_VAL";
				cdeNam = "CODE_NM";
				tblNam = "TC_CODE";
				flgCde = true;
			}else if(i.toUpperCase() == "DIRECT_CODE") { //행선
				if(_obj.data[_oTblInx].results[_oRetInx].fields["DIRECT_CODE"] == 'S'){
					$("#DIRECT_CODE").text('상행');
				}else if(_obj.data[_oTblInx].results[_oRetInx].fields["DIRECT_CODE"] == 'E'){
					$("#DIRECT_CODE").text('하행');
				}else if(_obj.data[_oTblInx].results[_oRetInx].fields["DIRECT_CODE"] == 'SE'){
					$("#DIRECT_CODE").text('양방향');
				}
				flgCde = false;
			}else if(i.toUpperCase() == "VMTC_GRAD") { //교통량등급
				conFld = "CODE_VAL";
				cdeNam = "CODE_NM";
				tblNam = "TC_CODE";
				flgCde = true;
			}else if(i.toUpperCase() == "ROUTE_CODE") { //노선코드
				conFld = "ROAD_NO";
				cdeNam = "ROAD_NAME";
				tblNam = "TN_ROUTE_INFO";
				flgCde = true;
			}else if(i.toUpperCase() == "ADM_CODE") { //행정구역코드
				conFld = "BJCD";
				cdeNam = "NAME";
				tblNam = "N3A_G0100000";
				flgCde = true;
			}else if(i.toUpperCase() == "GPCI") { //GPCI
				if(parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["GPCI"]) == '999'){
					$("#GPCI").text('-');
				}
				flgCde = false;
			}else {
				flgCde = false;
			}

			$("#undefined").hide();

			if(i.toUpperCase() == "DEPT_CODE") { //관리기관
				cdeVal = parseInt(_obj.data[_oTblInx].results[_oRetInx].fields[i]);
			}else{
				cdeVal = _obj.data[_oTblInx].results[_oRetInx].fields[i];
			}
			if(flgCde) fn_get_cdeval(conFld,tblNam,cdeNam,cdeVal);
		}
	};

	/**
	* @description 공통코드 값을 가져온다.
	* @param {String} _oConFld : 컬럼 값
	* @param {String} _oTblNam : 테이블 명
	* @param {String} _oCdeNam : 코드 명
	* @param {String} _oCdeVal : 코드 값
	*/
	var fn_get_cdeval = function(_oConFld,_oTblNam,_oCdeNam,_oCdeVal) {
		$.post(contextPath + "api/code/selectCodeName.do", {
			COLUM_VAL : _oConFld,
			TABLE_NM : _oTblNam,
			CODE_NM : _oCdeNam,
			CODE_VAL : _oCdeVal
		}, function(res) {
			if(res && res.resultData && res.resultData.length > 0) {
				var resultCde = res.resultData[0]["code_VAL"];
				var resultVal = res.resultData[0]["code_NM"];
					if(resultCde) {
						var objTd = eval(document.getElementById(resultCde));
						if(objTd) {

							objTd.innerHTML = resultVal;
						}
					}
			}
		}, 'json');
	};

	/**
	* @description 속성조회 상세정보를 생성한다.
	* @param {Object} _obj : 속성조회 결과를 가지고 있는 객체
	* @param {Integer} _oTblInx : 테이블 index
	* @param {Integer} _oRetInx : 선택한 객체 index
	*/
	var fn_create_dtlinfo = function(_obj,_oTblInx,_oRetInx) {
		var tagStr = "";

		tagStr += "<table id='tbIdenti' width='492px;'cellpadding='0' cellspacing='0' border='1'>";
		tagStr += "<colgroup> <col width='20%'/> <col width='20%'/><col width='20%'/><col width='20%'/</colgroup>";
		tagStr += "<tr><td  style='font-size:12px;border-width:0;'>ㅡ 기본정보 ㅡ</td></tr>"
		tagStr += "<tbody>";

		tagStr += "<tr>"
				+ "<th>"+_obj.data[_oTblInx].alias.fields["CELL_TYPE"]+"</th>"
				+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["CELL_TYPE"]+"' colsapn='3'>"+_obj.data[_oTblInx].results[_oRetInx].fields["CELL_TYPE"]+"</td>"
				+ "<th>"+_obj.data[_oTblInx].alias.fields["VMTC_GRAD"]+"</th>"
				+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["VMTC_GRAD"]+"'>"+_obj.data[_oTblInx].results[_oRetInx].fields["VMTC_GRAD"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
				+ "<th>"+_obj.data[_oTblInx].alias.fields["DEPT_CODE"]+"</th>"
				+ "<td id='"+parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["DEPT_CODE"])+"'>"+_obj.data[_oTblInx].results[_oRetInx].fields["DEPT_CODE"]+"</td>"
				+ "<th>"+_obj.data[_oTblInx].alias.fields["DIRECT_CODE"]+"</th>"
				+ "<td id='DIRECT_CODE'>"+_obj.data[_oTblInx].results[_oRetInx].fields["DIRECT_CODE"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["ADM_CODE"]+"</th>"
					+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["ADM_CODE"]+"'>"+_obj.data[_oTblInx].results[_oRetInx].fields["ADM_CODE"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["SRVY_YEAR"]+"</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["SRVY_YEAR"]+"-"+_obj.data[_oTblInx].results[_oRetInx].fields["SRVY_MT"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["ROUTE_CODE"]+"</th>"
					+ "<td>"+parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["ROUTE_CODE"])+"</td>"
					+ "<th>노선명</th>"
					+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["ROUTE_CODE"]+"'></td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["TRACK"]+"</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["TRACK"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["STRTPT"]+"-"+_obj.data[_oTblInx].alias.fields["ENDPT"]+"(m)</th>"
					+ "<td>"+parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["STRTPT"])+" - "+parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["ENDPT"])+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["ROAD_GRAD"]+"</th>"
					+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["ROAD_GRAD"]+"' colspan='3'>"+_obj.data[_oTblInx].results[_oRetInx].fields["ROAD_GRAD"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr><td  style='font-size:12px;border-width:0;'>ㅡ 포장상태정보 ㅡ</td></tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["GPCI"]+"</th>"
					+ "<td colspan='3' id='GPCI'>"+parseInt(_obj.data[_oTblInx].results[_oRetInx].fields["GPCI"])+"</td>"
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["IRI_VAL"]+"(m/km)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["IRI_VAL"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["RD_VAL"]+"(mm)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["RD_VAL"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th rowspan='5'>"+_obj.data[_oTblInx].alias.fields["CR_VAL"]+"(%)</th>"
					+ "<td rowspan='5'>"+_obj.data[_oTblInx].results[_oRetInx].fields["CR_VAL"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["AC_IDX"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["AC_IDX"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["BLOCK_CR"]+"(m)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["BLOCK_CR"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["LC_IDX"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["LC_IDX"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["TC_IDX"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["TC_IDX"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["POTHOLE_IDX"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["POTHOLE_IDX"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["PTCHG_IDX"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["PTCHG_IDX"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["CNTL_DFECT"]+"</th>"
					+ "<td id='"+_obj.data[_oTblInx].results[_oRetInx].fields["CNTL_DFECT"]+"'>"+_obj.data[_oTblInx].results[_oRetInx].fields["CNTL_DFECT"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["DMG_CUZ_CLMT"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["DMG_CUZ_CLMT"]+"</td>";
		tagStr += "</tr>";
		tagStr += "<tr>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["DMG_CUZ_VMTC"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["DMG_CUZ_VMTC"]+"</td>"
					+ "<th>"+_obj.data[_oTblInx].alias.fields["DMG_CUZ_ETC"]+"(%)</th>"
					+ "<td>"+_obj.data[_oTblInx].results[_oRetInx].fields["DMG_CUZ_ETC"]+"</td>";
		tagStr += "</tr>";
		tagStr += "</tbody>";
		tagStr += "</table>";

		$(".divAttr #divAttContent").html(tagStr);
	}


	/** 포장셀 컨트롤 */
	/**
	* @description 포장셀 컨트롤을 등록한다.
	*/
	var init_cellSel = function(){
		//181123 wijy 다중컨트롤 활성화를 위해 handler변경
		var selControls = {
				point : new GGetFeature(GPoint, {
					persist	: false,
		            //serviceUrl : CONFIG.fn_get_serviceUrl(),
		            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_10"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "cellPoint"
				}),
				//polygon : new GGetFeature(GPolygon, {
				polygon : new GGetFeature(OpenLayers.Handler.Polygon, {
					persist	: false,
		            //serviceUrl : CONFIG.fn_get_serviceUrl(),
		            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_10"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "cellPolygon"
				}),
				//181107 wijy
				//선형선택 컨트롤 추가
		        //line : new GGetFeature(GPath, {
				line : new GGetFeature(OpenLayers.Handler.Path, {
		            persist : false,
		            //serviceUrl : CONFIG.fn_get_serviceUrl(),
		            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
		            prefix : CONFIG.fn_get_dataHouseName(),
		            tables : [ "CELL_10" ],
		            excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
		            id : "cellPath"
		        })
			};

		for ( var i in selControls) {
			selControls[i].events.on( {
				"callback" : event_selFeature,
				"mousemove" : function(){}
			});

		  // 속성 조회 컨트롤 추가
			gMap.addControl(selControls[i]);

		}
	};


	var init_cellSelLonLat = function(){
        var selControls = {
                point : new GGetFeature(GPoint, {
                    persist : true,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["CELL_10"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "cellPointLL"
                })
            };

        for ( var i in selControls) {
            selControls[i].events.on( {
                "callback" : event_selFeatureLonLat,
                "mousemove" : function(){}
            });

          // 속성 조회 컨트롤 추가
            gMap.addControl(selControls[i]);

        }
    };

	/**
	* @description 포장셀 컨트롤을 활성화 시킨다.
	* @param {String} _sTableNm : 테이블명
	*/
	var activate_cellSel = function(_sTableNm, data){
	    // 2017.10.12 ERROR : 개체가 'select_node' 속성이나 메서드를 지원하지 않습니다.
		//$("#dvLayerList").jstree(true).select_node("#"+_sTableNm);
		option = data;
		selectFidList = [];

		$("#dvCellSelctionTool").find(".toolbtn").find("li").removeClass("sel");

		// 수정 여부
		if(option.cellIdList != undefined && option.cellIdList.length > 0 && option.selField != undefined && option.selField.length > 0){
			selectFidList = option.cellIdList;

			//MAP.fn_get_selectFeatureByAttr(gMap, [_sTableNm], [option.selField], [option.cellIdList], null, "OR");
			MAP.fn_get_selectFeatureByAttrMulti(gMap, [_sTableNm], [option.selField], [option.cellIdList], null, "OR");
		}

		$("#dvCellSelctionTool").dialog({
			close : function() {
				// x 버튼으로 창 복원 추가 (2015.12.18)
				try {
					wWindowShowAll();
					if(option.clearMap == undefined || option.clearMap){
						$("#mCtrlClear").click();
					}
					bottomOpen();
				} catch(e){}

				//181107 wijy : 초기화
				$(".toolbtn li").removeClass("sel");
				$(".toolbtn a").removeClass("on");
				$("#dv_multiSelectPoly").hide();
				$("#dv_inputForSelectCells").hide();
				$("#dv_cellSelectionHelp").hide();
			},
			dialogClass : "ui-dvCellSelctionTool",
			open : function(e, ui) {
				//181106 wijy position 변경
				$(".ui-dvCellSelctionTool").offset({top:110, left:"50%"});
			},
	        width: 250
		}).dialog("open");

		$("#mCtrlClear").click();

		// 열린 창 최소화
		$.window.hideAll();

		//하단영역 닫기
		bottomClose();
	};

	/**
	* @description 포장셀 마우스 event
	*/
	var select_mouseEvent = function(){
		//다중선택시
		$(document).on("mouseenter", ".tr_nsInfosRow", function(evt) {
			$(this).css("background-color", "#ddd");
			var idx = $(this).find("td:eq(0)").text();

			var feats = get_crossCellFeat(idx);

			for(var i = 0; i<feats.length; i++) {
				feats[i].attributes = {
						fillColor : '#0000FF',
						strokeColor : '#0000FF'
				};

				gMap.getLayerByName('GAttrLayer').addFeatures(feats[i]);

			}
		}).on("mouseleave", ".tr_nsInfosRow", function(evt) {
			$(this).css("background-color", "#fff");

			var idx = $(this).find("td:eq(0)").text();
			var feats = get_crossCellFeat(idx);

			for(var i = 0; i<feats.length; i++) {

				gMap.getLayerByName('GAttrLayer').removeFeatures(feats[i]);

			}
		}).on("click", ".tr_nsInfosRow", function(evt) {
			var idx = $(this).find("td:eq(0)").text();

			var feats = get_crossCellFeat(idx);

			for(var i = 0; i<feats.length; i++) {
				add_feature(feats[i], "GAttrLayer");
			}

			$("#res_ChoidNSList").html('');
			$("#dvChoiceNSRes").dialog("close");
		});
	};

	/**
	* @description 포장셀 컨트롤 event
	*/
	var event_selFeature = function(res){
		//로딩바 숨김
		$("#dvMapLoading").hide();
		
		if (!res.success()) {return;}

		//검색 조건 피쳐 삭제
		if(gMap.getControl(res.object.id) && gMap.getControl(res.object.id).handler
				&& gMap.getControl(res.object.id).handler.layer && gMap.getControl(res.object.id).handler.layer.features
				&& gMap.getControl(res.object.id).handler.layer.features.length > 0) {
			gMap.getControl(res.object.id).handler.layer.removeAllFeatures();

		}

		if(res.data == undefined || res.data.length < 1) {return;}

		for(var i=0; i<res.data.length; i++){
			if(res.data[i].results == undefined || res.data[i].results.length < 1) {continue;}

			var evt = gMap.getControl(res.object.id).handler.evt;
			set_dataField(res.data[i].results);

			if(check_intersection(res.data[i].results)){
				var cellIds = get_cellIds(res.data[i].results);
				check_routeInfo(cellIds, res.data[i].results, res.object.id);
				continue;
			}

			//181107 wijy 수정 : targetLayer 파라미터 추가
			// 수정 사유: 멀티노선을 선택하였을 경우 - 노선 선택 팝업창에서 GAttrLayer 레이어를 사용하고 있어서 기능이 제대로 작동하지 않는다.
			if(res.object.id == "cellPoint"){
				//add_feature(res.data[i].results[0].feature, evt, "GAttrLayerMulti");
				add_feature(res.data[i].results[0].feature, evt, "GAttrLayer");
			}else{
				for(var j=0; j<res.data[i].results.length; j++){
					//add_feature(res.data[i].results[j].feature, evt, "GAttrLayerMulti");
					add_feature(res.data[i].results[j].feature, evt, "GAttrLayer");
				}
			}
		}
	};

	// 위경도 위치조회-> 셀선택1개만되게
	var event_selFeatureLonLat = function(res){

	    if ( gMap.getLayerByName("GAttrLayerMulti").features.length != 0 ) {
	        alert("이미 선택된 레이어가 존재합니다.");
	        parent.gMap.activeControls("drag");
	        $("#dvMapLoading").hide();
	        return;
	    }

        if (!res.success()) {return;}

        
     // 검색 조건 피쳐 삭제
        if( gMap.getControl(res.object.id)
                && gMap.getControl(res.object.id).handler
                && gMap.getControl(res.object.id).handler.layer
                && gMap.getControl(res.object.id).handler.layer.features
                && gMap.getControl(res.object.id).handler.layer.features.length > 0 ) {

            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();
        }

        try {

            for ( var i = 0; i < res.data.length; i++ ) {
                if ( res.data[i].results == undefined || res.data[i].results.length < 1 ) { continue; }

                var evt = gMap.getControl(res.object.id).handler.evt;
                set_dataField(res.data[i].results);

                // 2018.11.20 2개 이상일 경우 continue 제거

				/*if(check_intersection(res.data[i].results)){
                    var cellIds = get_cellIds(res.data[i].results);
                    //check_routeInfo(cellIds, res.data[i].results, res.object.id);
                    //continue;
                }*/
				for(var j=0; j<res.data[i].results.length; j++){
					add_feature(res.data[i].results[j].feature, evt, "GAttrLayerMulti");
				}
            }

            $("#dvMapLoading").hide();

            if ( option.callback != undefined && option.callback != "" ) {
                // 함수 콜백
                option.iframe[option.callback](option);
            }
        } catch(err) {
            alert(err);
        }
    };


	/**
	* @description 선택된 셀 중 교차로 여부를 체크한다.
	* @param {Array} _oResList : 체크 대상 리스트
	*/
	var check_intersection = function(_oResList){
		var routeCodeList = [];
		var result = false;

		for(var i in _oResList){
			var route_code = _oResList[i].fields.ROUTE_CODE || _oResList[i].fields.route_code;
			routeCodeList.push(route_code);
		}

		var chkList = COMMON_UTIL.unique(routeCodeList);

		if(chkList.length > 1){ result = true; }

		return result;
	};

	/**
	* @description feature를 vector layer에 등록한다.
	* @param {Object} _oFeature : 등록 대상 feature
	* @param {Object} _oEvt : 컨트롤 event
	* //181107 wijy 추가
	* @param {String} _sTargetLayer : 대상레이어
	*/
	var add_feature = function(_oFeature, _oEvt, _sTargetLayer){
		var fid = _oFeature.data.CELL_ID || _oFeature.data.cell_id;
		// 기존에 remove_feature는 GAttrLayer에서 하고 addFeature는 GAttrLayerMulti에서 하고 있음. 확인 필요
		if(_sTargetLayer == null || _sTargetLayer == "") _sTargetLayer = "GAttrLayer";

		if(_oEvt != undefined && _oEvt.ctrlKey){
			remove_feature(fid, _sTargetLayer);
			return;
		}

		remove_feature(fid, _sTargetLayer);
		selectFidList.push(fid);

		// 지도에 도형 표출
		_oFeature.attributes = {
			fillColor : '#0033ff',
			strokeColor : '#0033ff'
		};

		gMap.getLayerByName(_sTargetLayer).addFeatures(_oFeature);
	};

	/**
    * @description feature를 vector layer에 등록한다. ( Multi Layer )
    * @param {Object} _oFeature : 등록 대상 feature
    * @param {Object} _oEvt : 컨트롤 event
    */
    var add_feature_multi = function(_oFeature, _oEvt){
        var fid = _oFeature.data.CELL_ID || _oFeature.data.cell_id;

        if(_oEvt != undefined && _oEvt.ctrlKey){
            remove_feature(fid);
            return;
        }

        remove_feature(fid);
        selectFidList.push(fid);

        // 지도에 도형 표출
        _oFeature.attributes = {
            fillColor : '#ffffff',
            strokeColor : '#ffffff'
        };

        gMap.getLayerByName('GAttrLayerMulti').addFeatures(_oFeature);
    };

	/**
	* @description 교량 feature를 vector layer에 등록한다.
	* @param {Object} _oFeature : 등록 대상 feature
	* @param {Object} _oEvt : 컨트롤 event
	*/
    //181107 wijy : GAttrLayer사용하도록 수정
	var add_brdg_feature = function(_oFeature, _oEvt){
		var fid = _oFeature.data.BRDG_SEQ || _oFeature.data.brdg_seq;

		if(_oEvt != undefined && _oEvt.ctrlKey){
			remove_feature(fid);
			return;
		}

		remove_feature(fid, "GAttrLayer");
		selectFidList.push(fid);

		// 지도에 도형 표출
		_oFeature.attributes = {
			fillColor : '#0033ff',
			strokeColor : '#0033ff'
		};

		//gMap.getLayerByName('GAttrLayer').addFeatures(_oFeature);
		gMap.getLayerByName('GAttrLayer').addFeatures(_oFeature);

	};

	/**
	* @description section feature를 vector layer에 등록한다.
	* @param {Object} _oFeature : 등록 대상 feature
	* @param {Object} _oEvt : 컨트롤 event
	*/
	var add_sect_feature = function(_oFeature, _oEvt){

		var fid = _oFeature.data.OBJECT_ID;

		if(_oEvt != undefined && _oEvt.ctrlKey){
			remove_feature(fid);
			return;
		}

		remove_feature(fid);
		selectFidList.push(fid);

		// 지도에 도형 표출
		_oFeature.attributes = {
			fillColor : '#0033ff',
			strokeColor : '#0033ff'
		};

		gMap.getLayerByName('GAttrLayerMulti').addFeatures(_oFeature);
	};



	/*============ YYK 20180219 ============*/
	/**
	* @description section marker를 vector layer에 등록한다.
	* @param {Object} _oMarker : 등록 대상 marker
	* @param {Object} _oEvt : 컨트롤 event
	*/
	var add_sttemnt_feature = function(_oFeature, _oEvt){

	    // JOY
		var pth = _oFeature.data.allData.PTH_RG_NO;
		var tm_x = _oFeature.data.allData.TM_X;
		var tm_y = _oFeature.data.allData.TM_Y;
		var prcsSttus = _oFeature.data.allData.PRCS_STTUS;
		var headG = _oFeature.data.allData.HEADG;
		var pthmode = _oFeature.data.allData.pthmode;

		/*var pth = _oFeature.data.PTH_RG_NO;
        var tm_x = _oFeature.data.TM_X;
        var tm_y = _oFeature.data.TM_Y;
        var prcsSttus = _oFeature.data.PRCS_STTUS;
        var headG = _oFeature.data.HEADG;*/

		if(_oEvt != undefined && _oEvt.ctrlKey){
			remove_feature(pth,"SttemntLayer");
			return;
		}

		remove_feature(pth,"SttemntLayer");

		// 레이어 조회
		var SttemntLayer = gMap.getLayerByName("SttemntLayer");

		//========== 레이어에 마커추가 + 회전 ==========
	    var size = new OpenLayers.Size(21,25);
	    var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	    //for(var i=0; i< xy.length; i++){

        // 마커값 넣기
        //var icon = new OpenLayers.Icon('/gpms/images/icon_marker1', size, offset );

	    if ( prcsSttus != undefined && prcsSttus != "" ) { // YYK dmgt값이 없을 경우 마커 x
			SttemntLayer.addFeatures(
		             new OpenLayers.Feature.Vector(
		                     new OpenLayers.Geometry.Point( tm_x, tm_y )
		                     ,{
		                    	 allData     : _oFeature.data.allData
		                    	 /*allData     : _oFeature.data*/
		                         ,image      : prcsSttus
		                         , angle     : headG * 1 - 90
		                         , pthRgNo   : pth
		                         , pthmode	 : pthmode
		                     }
		             )
			);
	    }
	};


	/*============ YYK. 2018.02.27 ============*/
	/**
	* @description section Feature를 vector layer에 등록한다.
	* @param {Object} _oFeature : 등록 대상 Feature
	* @param {Object} _oEvt : 컨트롤 event
	*/
	var add_dmgt_feature = function(_oFeature, _oEvt){

		var pth = _oFeature.data.allData.PTH_RG_NO;
		var tm_x = _oFeature.data.allData.TM_X;
		var tm_y = _oFeature.data.allData.TM_Y;
		var dmgt = _oFeature.data.allData.DMG_TYPE;
		var headG = _oFeature.data.allData.HEADG;
		var pthmode = _oFeature.data.allData.pthmode;

		/*var pth = _oFeature.data.PTH_RG_NO;
        var tm_x = _oFeature.data.TM_X;
        var tm_y = _oFeature.data.TM_Y;
        var dmgt = _oFeature.data.DMG_TYPE;
        var headG = _oFeature.data.HEADG;*/

		if(_oEvt != undefined && _oEvt.ctrlKey){
			remove_feature(pth,"DmgtLayer");
			return;
		}

		remove_feature(pth,"DmgtLayer");

		// 레이어 조회
		var DmgtLayer = gMap.getLayerByName("DmgtLayer");

		//========== 레어이에 마커추가 + 회전 ==========
	    var size = new OpenLayers.Size(21,25);
	    var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	    //for(var i=0; i< xy.length; i++){

        // 마커값 넣기
        //var icon = new OpenLayers.Icon('/gpms/images/icon_marker1', size, offset );


	    if ( dmgt != undefined && dmgt != "" ) { // YYK dmgt값이 없을 경우 마커 x
		    DmgtLayer.addFeatures(
		             new OpenLayers.Feature.Vector(
		                     new OpenLayers.Geometry.Point( tm_x, tm_y )
		                     ,{
		                    	 allData     : _oFeature.data.allData
		                    	 /*allData     : _oFeature.data*/
		                    	 ,image      : dmgt
		                         , angle     : headG * 1 - 90
		                         , pthRgNo   : pth
		                         , pthmode	 : pthmode
		                     }
		             )
			);
	    }
	};



	/**
	* @description feature를 vector layer에서 삭제한다.
	* @param {String} _sFid : 삭제 대상 id
	* //181107 wijy 레이어 조건 추가
	* @param {String} _sTargetLayer : 삭제 대상 Layer
	*/
	var remove_feature = function(_sFid, _sTargetLayer){
		if(_sTargetLayer == null || _sTargetLayer == "") _sTargetLayer = "GAttrLayer";

		selectFidList.splice(selectFidList.indexOf(_sFid));

		var chkId = "";

		for(var i=0; i<gMap.getLayerByName(_sTargetLayer).features.length; i++){
			var feature = gMap.getLayerByName(_sTargetLayer).features[i];

			if((_sTargetLayer == "SttemntLayer") || (_sTargetLayer == "DmgtLayer")){
				chkId = feature.data.pthRgNo;
			} else{
				chkId = feature.data.CELL_ID || feature.data.cell_id;
			}

			if(chkId != _sFid){continue;}

			gMap.getLayerByName(_sTargetLayer).removeFeatures(feature);
		}
	};

	/**
	* @description 여러개의 노선이 선택되었는지 체크한다.
	* @param {String} _sCellIds : cell id
	* @param {Object} _oRes : feature 리스트
	* @param {String} _sCheckType : 체크 대상 (단일검색 / 다중검색)
	*/
	var check_routeInfo_new = function(_sCellIds, _oRes, _sCheckType) {
        var sUrl = contextPath + 'api/cell10/selectRouteInfos.do';

        $.ajax({
            url : sUrl,
            type : 'post',
            dataType : 'json',
            contentType : 'application/json',
            data : JSON.stringify({
                CELL_IDS : _sCellIds
            }),
            success : function(data) {
                if (data.length < 2) {
                    return;
                }
                crossCellList = [];
                var html = "";

                html = set_routeInfo_Multi(_oRes, data);

                $("#res_ChoidNSList").html(html);
                $("#dvChoiceNSRes").dialog("open");
            },
            error : function(a, b, msg) {
            }
        });
    };

    //181107 wijy : 선형 체크 추가,  18.11.20 cellPointLL 추가
	var check_routeInfo = function(_sCellIds, _oRes, _sCheckType){
		var sUrl = "";

		switch(_sCheckType){
			case "cellPoint":
				sUrl = contextPath + 'api/cell10/selectRouteInfo.do';
				break;
			case "cellPolygon":
				sUrl = contextPath + 'api/cell10/selectRouteInfos.do';
				break;
			case "cellPath" :
				sUrl = contextPath + 'api/cell10/selectRouteInfos.do';
				break;
			case "cellPointLL" :
				sUrl = contextPath + 'api/cell10/selectRouteInfos.do';
				break;
		}

		$.ajax({
			url: sUrl
			,type: 'post'
			,dataType: 'json'
			,contentType : 'application/json'
			,data: JSON.stringify( {CELL_IDS : _sCellIds})
			,success: function(data){
				if(data.length < 2){ return; }
				crossCellList = [];
				var html = "";

				switch(_sCheckType){
					case "cellPoint":
						html = set_routeInfo_single(_oRes, data);
						break;
					case "cellPolygon":
						html = set_routeInfo_Multi(_oRes, data);
						break;
					case "cellPath" :
						html = set_routeInfo_Multi(_oRes, data);
						break;
					case "cellPointLL" :
						html = set_routeInfo_Multi(_oRes, data);
						break;
				}

				$("#res_ChoidNSList").html(html);
				$("#dvChoiceNSRes").dialog("open");
			}
			,error: function(a,b,msg){
			}
		});
	};

	/**
	* @description 단일 검색의 중용구간 목록을 보여준다.
	* @param {Object} _oRes : feature 리스트
	* @param {Object} _oData : 중용구간 목록 정보
	*/
	var set_routeInfo_single = function(_oRes, _oData){
		var html = "<table class='tblist' style='background-color:#fff;'>" +
						"<colgroup>" +
						"<col width='10%'/>" +
						"<col width='20%'/>" +
						"<col width='10%'/>" +
						"<col width='10%'/>" +
						"<col width='10%'/>" +
						"<col width='10%'/>" +
						"<col width='20%'/>" +
					"</colgroup>" +
					"<thead>" +
						"<tr>" +
							"<th scope='col'>노선코드</th>" +
							"<th scope='col'>노선명</th>" +
							"<th scope='col'>행선</th>" +
							"<th scope='col'>차로</th>" +
							"<th scope='col'>시점</th>" +
							"<th scope='col'>종점</th>" +
							"<th scope='col'>도로구분</th>" +
						"</tr>" +
					"</thead>";

		for(var i = 0; i <_oData.length; i++) {
		var routeCd = parseInt(_oData[i].ROUTE_CODE);
		var feats = [];

		html +=
			"<tbody>" +
				"<tr class='tr_nsInfosRow'>" +
					"<td>" + routeCd + "</td>" +
					"<td>" + _oData[i].ROAD_NAME + "</td>" +
					"<td>" + _oData[i].DIRECT_NM + "</td>" +
					"<td>" + _oData[i].TRACK + "</td>" +
					"<td>" + _oData[i].STRTPT + "</td>" +
					"<td>" + _oData[i].ENDPT + "</td>" +
					"<td>" + _oData[i].ROAD_GRAD + "</td>" +
				"</tr>" +
			"</tbody>";

		var route_code = _oRes[0].feature.data.ROUTE_CODE || _oRes[0].feature.data.route_code;
		if(route_code == routeCd){
			feats.push(_oRes[0].feature);
		}

		crossCellList.push({
			routeCd	:	routeCd,
			feats	:	feats
		});

		}
		html += "</table>";

		return html;
	};

	/**
	* @description 다중 검색의 중용구간 목록을 보여준다.
	* @param {Object} _oRes : feature 리스트
	* @param {Object} _oData : 중용구간 목록 정보
	*/
	var set_routeInfo_Multi = function(_oRes, _oData){
		var html = "<table class='tblist' style='background-color:#fff;'>" +
					"<colgroup>" +
						"<col width='20%'/>" +
						"<col width='50%'/>" +
						"<col width='30%'/>" +
					"</colgroup>" +
					"<thead>" +
						"<tr>" +
							"<th scope='col'>노선번호</th>" +
							"<th scope='col'>노선명</th>" +
							"<th scope='col'>도로구분</th>" +
						"</tr>" +
					"</thead>";

		for(var i = 0; i <_oData.length; i++) {
		var routeCd = parseInt(_oData[i].ROUTE_CODE);
		var feats = [];

		html +=
			"<tbody>" +
				"<tr class='tr_nsInfosRow'>" +
					"<td>" + routeCd + "</td>" +
					"<td>" + _oData[i].ROAD_NAME + "</td>" +
					"<td>" + _oData[i].ROAD_GRAD + "</td>" +
				"</tr>" +
			"</tbody>";

		for(var j=0; j <_oRes.length; j++){
			var route_code = _oRes[j].feature.data.ROUTE_CODE || _oRes[j].feature.data.route_code;
			if(route_code != routeCd){
				continue; 
			}
			feats.push(_oRes[j].feature);
		}

		crossCellList.push({
			routeCd	:	routeCd,
			feats	:	feats
		});

		}
		html += "</table>";

		return html;
	}

	/**
	* @description 셀 필드 정보를 feature.data에 복사한다.
	*/
	var set_dataField = function(res){
		for(var i in res){
			var result = res[i];
			result.feature.data = result.fields;
			//181106 wijy추가
			result.feature.g2id = result.g2id;
		}
	}

	/**
	* @description cell_id 값을 배열로 가져온다.
	*/
	var get_cellIds = function(res){
		var cellIds = "";

		for(var i in res){
			var feature = res[i].feature;

			if(i != 0){cellIds += ",";}
			cellIds += feature.data.CELL_ID || feature.data.cell_id;
		}

		return cellIds;
	};


	/**
	* @description 교차로 구간의 feature들을 가져온다.
	* @param {String} _sRouteCd : 가져올 대상 노선 코드
	*/
	var get_crossCellFeat = function(_sRouteCd){
		var feats = [];
		for(var i = 0; i<crossCellList.length; i++) {
			if(crossCellList[i].routeCd == _sRouteCd) {
				feats = crossCellList[i].feats;
				break;
			}
		}
		return feats;
	};


	// =========================== ROUTE INFO =========================== //
	/** 통합정보조회 컨트롤 */
    /**
    * @description 통합정보조회 컨트롤을 등록한다. - 노선조회
    */
    var init_inteSelRoad = function(){

        var selControls = {
                point : new GGetFeature(GPoint, {
                    persist : true,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["DORO_TOT_GRS80_50"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len"],
                    id : "selPointRoad"
                }),
                polygon : new GGetFeature(GPolygon, {
                    persist : false,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["DORO_TOT_GRS80_50"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len"],
                    id : "selPolygonRoad"
                })
            };

        for ( var i in selControls) {
            selControls[i].events.on( {
                "callback" : event_selFeatureRoad,
                "mousemove" : function(){}
            });

            // 속성 조회 컨트롤 추가
            gMap.addControl(selControls[i]);

        }
    };

    /**
     * @description 통합정보조회 컨트롤 event - 노선조회
     */
    var event_selFeatureRoad = function(res) {

        if ( !res.success() ) { return; }

        // 검색 조건 피쳐 삭제
        if( gMap.getControl(res.object.id)
                && gMap.getControl(res.object.id).handler
                && gMap.getControl(res.object.id).handler.layer
                && gMap.getControl(res.object.id).handler.layer.features
                && gMap.getControl(res.object.id).handler.layer.features.length > 0 ) {

            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();

        }

        // 검색할 내용이 선택되지 않은 경우
        if( res.data == undefined || res.data.length < 1 ) {

        	var features = gMap.getLayerByName("GAttrLayer").features;
            //if ( gMap.getLayerByName("GAttrLayerMulti").features.length == 0 ) {
        	if ( features.length == 0 ) {
                if ( $("#btn_selPoint").parent().hasClass("on") ) {
                    gMap.activeControls("selPointRoad");

                } else if ( $("#btn_selPolygon").parent().hasClass("on") ) {
                    gMap.activeControls("selPolygonRoad");
                }
                alert("선택한 범위 내에 검색된 노선이 없습니다.");

            } else {
                gMap.activeControls("drag");
            }

            return;
        }

        try {
            // 검색할 내용이 선택된 경우
            for ( var i = 0; i < res.data.length; i++ ) {
            	if ( res.data[i].results == undefined
                        || res.data[i].results.length < 1 ) {
                    continue;

                } else if ( res.data[i].results.length > 500 ) {
                    alert("검색할 노선이 너무 많습니다.");
                    gMap.cleanMap();

                    return;
                }

                var evt = gMap.getControl(res.object.id).handler.evt;
                set_dataField(res.data[i].results);

                for ( var j=0; j<res.data[i].results.length; j++ ) {

                    var feature = res.data[i].results[j].feature;
                    feature.attributes = {
                            fillColor : '#0033ff',
                            strokeColor : '#0033ff'
                     };
                    //add_feature(res.data[i].results[j].feature, evt);
                    //gMap.getLayerByName('GAttrLayerMulti').addFeatures(feature);
                    gMap.getLayerByName('GAttrLayer').addFeatures(feature);

                }

            }

            if ( option.callback != undefined && option.callback != "" ) {
                // 함수 콜백
                option.iframe[option.callback](option);

            }
        } catch(err) {
            alert(err);
        }
    }


    // =========================== RESEARCH INFO =========================== //

    /** 통합정보조회 컨트롤 */
    /**
    * @description 통합정보조회 컨트롤을 등록한다. - 포장상태 조사정보
    */
    var init_inteSelResearch = function(){
        var selControls = {
                point : new GGetFeature(GPoint, {
                    persist : true,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["CELL_10"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPointResearch"
                }),
                polygon : new GGetFeature(GPolygon, {
                    persist : false,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["CELL_10"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPolygonResearch"
                })
            };

        for ( var i in selControls) {
            selControls[i].events.on( {
                "callback" : event_selFeatureResearch,
                "mousemove" : function(){}
            });

            // 속성 조회 컨트롤 추가
            gMap.addControl(selControls[i]);

        }
    };

    /**
    * @description 통합정보조회 컨트롤 event - 포장상태 조사정보
    */
    var event_selFeatureResearch = function(res) {

        if ( !res.success() ) { return; }

        // 검색 조건 피쳐 삭제
        if( gMap.getControl(res.object.id)
                && gMap.getControl(res.object.id).handler
                && gMap.getControl(res.object.id).handler.layer
                && gMap.getControl(res.object.id).handler.layer.features
                && gMap.getControl(res.object.id).handler.layer.features.length > 0 ) {

            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();

        }

        // 검색할 내용이 선택되지 않은 경우
        if( res.data == undefined || res.data.length < 1 ) {

            if ( parent.gMap.getLayerByName("GAttrLayerMulti").features.length == 0 ) {

            	//alert("선택된 범위내에 자료가 없습니다.");
                alert("선택된 셀이 없습니다.");
                parent.gMap.activeControls("drag");

            }

            return;

        }

        try {

            // 검색할 내용이 선택된 경우
            for(var i=0; i<res.data.length; i++){

                if( res.data[i].results == undefined
                        || res.data[i].results.length < 1 ) {

                    continue;

                } else if ( res.data[i].results.length > 200 ) {

                    alert("검색할 셀이 너무 많습니다.");
                    gMap.cleanMap();

                    return;

                }

                var evt = gMap.getControl(res.object.id).handler.evt;
                set_dataField(res.data[i].results);

                var cellIds = get_cellIds(res.data[i].results);
                check_researchInfo(cellIds, res.data[i].results, res.object.id);

                for ( var j = 0; j < res.data[i].results.length; j++ ) {

                    var feature = res.data[i].results[j].feature;
                    feature.attributes = {
                            fillColor : '#0033ff',
                            strokeColor : '#0033ff'
                     };

                    // gMap.getLayerByName('GAttrLayerMulti').addFeatures(feature);
                    add_sect_feature(res.data[i].results[j].feature, evt);

                }

                // 셀이 여러개인 경우 콜백함수 삭제
                if ( res.data[i].results.length > 1 ) {

                    option.callback = "";

                }

            }

            if ( option.callback != undefined && option.callback != "" ) {

                // 함수 콜백
                option.iframe[option.callback](option);

            }

        } catch(err) {

            alert(err);

        }

    };



    // =========================== STTEMNT INFO =========================== //

    /** 2018.02.18. YYK. 포트홀신고관리 컨트롤
    *
    * @description 포트홀신고관리 컨트롤을 등록
    */
    // JOY
    var init_inteSelSttemnt = function(){
        var selControls = {
                point : new GGetFeature(GPoint, {
                    persist : true,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPointSttemnt"
                }),
                polygon : new GGetFeature(GPolygon, {
                    persist : false,
                    //serviceUrl : CONFIG.fn_get_serviceUrl(),
                    serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPolygonSttemnt"
                })

            };

        /*var selControls = {
                point : new GGetFeature(GPoint, {
                    persist : true,
                    serviceUrl : CONFIG.fn_get_serviceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["MV_POTHOLE_STTEMNT", "MV_POTHOLE_STTEMNT_DMG"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPointSttemnt"
                }),
                polygon : new GGetFeature(GPolygon, {
                    persist : false,
                    serviceUrl : CONFIG.fn_get_serviceUrl(),
                    prefix : CONFIG.fn_get_dataHouseName(),
                    tables : ["MV_POTHOLE_STTEMNT", "MV_POTHOLE_STTEMNT_DMG"],
                    excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
                    id : "selPolygonSttemnt"
                })

            };*/

        for ( var i in selControls) {
            selControls[i].events.on( {
                "callback" : event_selFeatureSttemnt,
                "mousemove" : function(){}
            });
            // 속성 조회 컨트롤 추가
            gMap.addControl(selControls[i]);
        }


        // 2019년 고도화 사업 - 포트홀 편집.선택 컨트롤 추가
        var selPthEditControls = {
				point : new GGetFeature(GPoint, {
					persist	: true,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["PTH_CTSMNT"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selPointPthEdit"
				})
			};

        for ( var i in selPthEditControls) {
        	selPthEditControls[i].events.on( {
                "callback" : event_selFeatureSttemnt,
                "mousemove" : function(){}
            });
            // 속성 조회 컨트롤 추가
            gMap.addControl(selPthEditControls[i]);
        }

    };

    /**
    * @description 신고정보 공간검색 컨트롤 event
    */
    var event_selFeatureSttemnt = function(res) {

        // 레이어 선택 ( GStatusLayer / GTypeLayer )
        var layerNm = $("#status").hasClass("on") ? "GStatusLayer" : "GTypeLayer";
        var filterNm = $("#status").hasClass("on") ? "sttemnt" : "dmgtype";

        // 검색 feature 갯수
        var cnt = 0;

        // feature
        var feature = gMap.getLayerByName(layerNm).features;
        var matchArr = [];

        var evt = gMap.getControl(res.object.id).handler.evt;

        var len = $("#pointArea").val();

        var chkFeature_id = '0';

        // 단일 Point인 경우
        if ( $("#btn_pthSelect").parent().hasClass("on") ) {
        	if(res.data.length > 0){

		        // 단일 Point인 경우
		    	for ( var j=0; j<res.data[0].results.length; j++ ) {

		    		chkFeature_id = res.data[0].results[j].g2id;
		        }
        	}
        }

        // 검색영역에 해당하는 feature 갯수 조회
        for ( var i = 0; i < feature.length; i++ ) {

            if ( $("#btn_selPolygon").parent().hasClass("on") ) {

                // Polygon인 경우
                if ( spaceGeo.intersects( feature[i].geometry ) ) {

                    if ( feature[i].data.data != null && feature[i].data.data != undefined && feature[i].data.data != ""
                            && $("#" + filterNm + feature[i].data.data.substring(7)).hasClass("bgchk") ) {

                        cnt++;
                        matchArr.push(feature[i]);

                    }

                }

            } else if ( $("#btn_pthSelect").parent().hasClass("on") ) {

                // 단일 Point인 경우
            	if ( feature[i].data.data != null && feature[i].data.data != undefined && feature[i].data.data != ""
                    && $("#" + filterNm + feature[i].data.data.substring(7)).hasClass("bgchk") ) {

            		if((chkFeature_id == feature[i].data.allData.GG_ID) &&
            		   (feature[i].data.pthmode == "C") &&
            		   (feature[i].data.allData.DELETE_AT != "Y") &&
            		   (feature[i].data.allData.DPLCT_STTEMNT_AT != "Y")){
		                cnt++;
		                matchArr.push(feature[i]);
	            	}

	            }

            } else {

                // Point인 경우
                var dist = spaceGeo.distanceTo( feature[i].geometry);

                if ( dist <= len ) {

                    if ( feature[i].data.data != null && feature[i].data.data != undefined && feature[i].data.data != ""
                        && $("#" + filterNm + feature[i].data.data.substring(7)).hasClass("bgchk") ) {

                        cnt++;
                        matchArr.push(feature[i]);

                    }

                }

            }

        }

        // 마커 갯수 제한
        if ( cnt <= 0 ) {

        	if ( $("#btn_pthSelect").parent().hasClass("on") ) {

        		alert("편집 대상이 아닙니다. \n시군관리용 포트홀을 선택하십시오.");

        	} else {

        		alert("검색 결과가 없습니다. \n기간 및 조건을 변경하거나 검색범위를 다르게 하십시오.");

        	}


            $('#mCtrlPan').trigger('click') ;
            $("#dvMapLoading").hide();
            return;

        }

        if ( cnt >= 250 ) {

            alert("검색된 결과가 너무 많습니다. \n검색범위를 좁혀주세요.");
            $('#mCtrlPan').trigger('click') ;
            $("#dvMapLoading").hide();
            return;

        }

        try{

			//관할구역 확인
 			GRequest.WFS.getFeatureByGeometry(
				//CONFIG.fn_get_serviceUrl(),
	            CONFIG.fn_get_wfsServiceUrl(),
				{
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CMPTNC_ZONE"],
					values : [matchArr[0].geometry]
				},function(area_res){

					var area_chk = false;

					//포트홀 신고등록 - 선택일 경우, 관할구역을 먼저 확인
					if ( $("#btn_pthSelect").parent().hasClass("on") ) {

						if(area_res.data.length == 0){
							alert("관할구역 이외의 지역에 입력하실 수 없습니다.");
							$('#mCtrlPan').trigger('click') ;
				            $("#dvMapLoading").hide();
				            return;
						}

						for(var i=0;i<area_res.data[0].results.length;i++){

							var f_deptCode = area_res.data[0].results[0].fields.DEPT_CODE;
							var userDeptCode = $("#USER_DEPT_CODE").val();

							if(f_deptCode == userDeptCode){

								area_chk = true;
								break;
							}
						}

					//신고정보 공간검색은 관할구역 확인하지 않음.
					} else {

						area_chk = true;
					}

					if(!area_chk) {

						alert("관할구역 이외의 지역에 입력하실 수 없습니다.");
						$('#mCtrlPan').trigger('click') ;
			            $("#dvMapLoading").hide();
			            return;

					} else{

				        // 마커 생성
				        for ( var i = 0; i < matchArr.length; i++ ) {

				            // 신고현황 탭 필터 체크 되어있는 경우에만
				            if ( $("#" + filterNm + matchArr[i].data.data.substring(7)).hasClass("bgchk") ) {

				                if ( layerNm == "GStatusLayer" ) {

				                    add_sttemnt_feature(matchArr[i], evt);

				                } else {

				                    add_dmgt_feature(matchArr[i], evt);

				                }

				            }

				        }

				        if ( option.callback != undefined && option.callback != "" ) {
				            // 함수 콜백
				            option.iframe[option.callback](option);
				        }

				        $("#dvMapLoading").hide();

					}

			});
		}catch(e){
			$('#mCtrlPan').trigger('click') ;
            $("#dvMapLoading").hide();
            return;
		}



    };

    // 임시저장
    /*var event_selFeatureSttemnt = function(res) {
        // 로딩바 시작

        if ( !res.success() ) {
            $("#dvMapLoading").hide();
            return;
        }*/
/*
        // 검색 조건 피쳐 삭제
        if( gMap.getControl(res.object.id)
                && gMap.getControl(res.object.id).handler
                && gMap.getControl(res.object.id).handler.layer
                && gMap.getControl(res.object.id).handler.layer.features
                && gMap.getControl(res.object.id).handler.layer.features.length > 0 ) {

            //gMap.getControl(res.object.id).handler.layer.removeAllFeatures();
            gMap.getLayerByName('SttemntLayer').removeAllFeatures()
        }
*/

        // 2018.03.02. YYK. 검색할 내용이 없는 경우
        /*if( res.data == undefined || res.data.length < 1 ) {
            alert("검색 결과가 없습니다. \n기간 및 조건을 변경하거나 검색범위를 다르게 하십시오.");
            $("#dvMapLoading").hide();
            $('#mCtrlPan').trigger('click') ;
            //parent.gMap.activeControls("drag");
            return;
        }

        try {
            // YYK. SttemntMapView.jsp 의 start, end 값
            //var start = parent.$('.window_frame').contents().find('#STTEMNT_DT_START').val().replace(/-/gi, '')
            //var end = parent.$('.window_frame').contents().find('#STTEMNT_DT_END').val().replace(/-/gi, '')

            // 2018.03.12. YYK. toptool 의 날짜값으로 검색하게 변경됨
            var start = parent.$('.top-tool #STTEMNT_DT_START').val().replace(/-/gi, '')
            var end = parent.$('.top-tool #STTEMNT_DT_END').val().replace(/-/gi, '');

            // 2018.04.02. YYK. top-tool 의 체크에 따라 공간검색시 나타나는 신고상태 변경됨
            var sttemntChk = parent.$('.top-tool #sttemnt_tool').find('.bgchk') ;
            var sttemntArr = [];
            var dmgtchk = parent.$('.top-tool #dmgt_tool').find('.bgchk') ;
            var dmgtArr = [];

            for (var k=0; k<sttemntChk.length; k++) {
                sttemntArr.push( sttemntChk[k].id.replace('sttemnt', 'PRCS000') );
            }
            for (var k=0; k<dmgtchk.length; k++) {
                dmgtArr.push( dmgtchk[k].id.replace('dmgtype', 'DMGT000') );
            }

            //parent.$('.top-tool #sttemnt'+num).hasClass(bgchk)

            var count = 0;


            if( gMap.getLayerByName('SttemntLayer').features.length > 0 || gMap.getLayerByName('DmgtLayer').features.length > 0 ) {
                gMap.getLayerByName('SttemntLayer').removeAllFeatures()
                gMap.getLayerByName('DmgtLayer').removeAllFeatures()
            }

            // 검색할 내용이 선택된 경우
            for(var i=0; i<res.data.length; i++){

                var evt = gMap.getControl(res.object.id).handler.evt;
                set_dataField(res.data[i].results);


                var chk_pth = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #PTH_RG_NO").val();
                if (chk_pth !="" && chk_pth != undefined ){
                    chk_pth = searchPthRgNo(chk_pth);
                }
                var chk_bsmn  = parent.$('#goodMap').find("#BSNM_NM").val();
                var chk_vhcle = parent.$('#goodMap').find("#VHCLE_NO").val();
                var chk_dept  = parent.$('#goodMap').find("#DEPT_CODE").val();
                var chk_prcs  = parent.$('#goodMap').find("#PRCS_STTUS").val();
                var chk_dmg   = parent.$('#goodMap').find("#DMG_STTUS").val();*/

                /*var chk_bsmn = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #BSNM_NM").val();
                var chk_vhcle = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #VHCLE_NO").val();
                var chk_dept = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #DEPT_CODE").val();
                var chk_prcs = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #PRCS_STTUS").val();
                var chk_dmg = parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #DMG_STTUS").val();*/

/*
                <!-- 포트홀 신고관리 메뉴의 검색조건 setting START -->
                <input type="hidden"    id="PTH_RG_NO"         name="PTH_RG_NO"         value="" />
                <input type="hidden"    id="BSNM_NM"           name="BSNM_NM"           value="" />
                <input type="hidden"    id="VHCLE_NO"          name="VHCLE_NO"          value="" />
                <input type="hidden"    id="DEPT_CODE"         name="DEPT_CODE"         value="" />
                <input type="hidden"    id="PRCS_STTUS"        name="PRCS_STTUS"        value="" />
                <input type="hidden"    id="DMG_TYPE"          name="DMG_TYPE"          value="" />
                <input type="hidden"    id="RPAIR_DT_START"    name="RPAIR_DT_START"    value="" />
                <input type="hidden"    id="RPAIR_DT_END"      name="RPAIR_DT_END"      value="" />
                */

                /*for ( var j = 0; j < res.data[i].results.length; j++ ) {

                    var s = res.data[i].results[j].fields.STTEMNT_DT ; // 선택된 날짜에 해당하는 값 체크
                    var p = res.data[i].results[j].fields.PRCS_STTUS ;
                    var d = res.data[i].results[j].fields.DMG_TYPE ;

                    var pth = res.data[i].results[j].fields.PTH_RG_NO ;
                    var bsmn  = res.data[i].results[j].fields.BSNM_NM ;
                    var vhcle = res.data[i].results[j].fields.VHCLE_NO ;
                    var dept  = res.data[i].results[j].fields.DEPT_CODE ;
                    var prcs  = res.data[i].results[j].fields.PRCS_STTUS ;
                    var dmg   = res.data[i].results[j].fields.DMG_TYPE ;

                    if ( s >= start && s <= end && sttemntArr.indexOf(p) > -1 ){
                        if (    ( chk_pth   == "" || chk_pth   == undefined || pth.indexOf( chk_pth ) != -1 )
                            &&  ( chk_bsmn  == "" || chk_bsmn  == undefined || bsmn.indexOf( chk_bsmn ) != -1 )
                            &&  ( chk_vhcle == "" || chk_vhcle == undefined || vhcle.indexOf( chk_vhcle ) != -1 )
                            &&  ( chk_dept  == "" || chk_dept  == undefined || dept.indexOf( chk_dept ) != -1 )
                            &&  ( chk_prcs  == "" || chk_prcs  == undefined || prcs.indexOf( chk_prcs ) != -1 )
                            &&  ( chk_dmg   == "" || chk_dmg   == undefined || dmg.indexOf( chk_dmg ) != -1 )

                        ) {
                            count ++;
                        }
                    }*/
                    /*if ( count > 500 ) {
                        alert("검색된 결과가 너무 많습니다. \n검색범위를 좁혀주세요.");
                        $('#mCtrlPan').trigger('click') ;
                        $("#dvMapLoading").hide();
                        return;
                    }*/

                /*}

                if (count <= 0){
                    alert("검색 결과가 없습니다. \n기간 및 조건을 변경하거나 검색범위를 다르게 하십시오.");
                    $('#mCtrlPan').trigger('click') ;
                    $("#dvMapLoading").hide();

                    return;
                }

                for ( var j = 0; j < res.data[i].results.length; j++ ) {

                    var s = res.data[i].results[j].fields.STTEMNT_DT ; // 선택된 날짜에 해당하는 값 체크
                    var p = res.data[i].results[j].fields.PRCS_STTUS ;
                    var d = res.data[i].results[j].fields.DMG_TYPE ;

                    var pth   = res.data[i].results[j].fields.PTH_RG_NO ;
                    var bsmn  = res.data[i].results[j].fields.BSNM_NM ;
                    var vhcle = res.data[i].results[j].fields.VHCLE_NO ;
                    var dept  = res.data[i].results[j].fields.DEPT_CODE ;
                    var prcs  = res.data[i].results[j].fields.PRCS_STTUS ;
                    var dmg   = res.data[i].results[j].fields.DMG_TYPE ;

                    if ( s >= start && s <= end && sttemntArr.indexOf(p) > -1 ){
                        if ( (       chk_pth   == "" || chk_pth   == undefined || pth.indexOf( chk_pth ) != -1 )
                                && ( chk_bsmn  == "" || chk_bsmn  == undefined || bsmn.indexOf( chk_bsmn ) != -1 )
                                && ( chk_vhcle == "" || chk_vhcle == undefined || vhcle.indexOf( chk_vhcle ) != -1 )
                                && ( chk_dept  == "" || chk_dept  == undefined || dept.indexOf( chk_dept ) != -1 )
                                && ( chk_prcs  == "" || chk_prcs  == undefined || prcs.indexOf( chk_prcs ) != -1 )
                                && ( chk_dmg   == "" || chk_dmg   == undefined || dmg.indexOf( chk_dmg ) != -1 )

                        ) {
                                var feature = res.data[i].results[j].feature;
                                feature.attributes = {
                                        fillColor : '#0033ff',
                                        strokeColor : '#0033ff'
                                };

                                // feature 추가
                                add_sttemnt_feature(res.data[i].results[j].feature, evt);

                        }

                    }

                    if ( s >= start && s <= end && dmgtArr.indexOf(d) > -1 ){
                        var feature = res.data[i].results[j].feature;
                        console.log(feature);
                        feature.attributes = {
                                fillColor : '#0033ff',
                                strokeColor : '#0033ff'
                        };

                        // feature 추가
                        add_dmgt_feature(res.data[i].results[j].feature, evt);

                    }
                }*/
/*
                // 셀이 여러개인 경우 콜백함수 삭제
                if ( res.data[i].results.length > 1 ) {
                    option.callback = "";
                }
                */
            /*}

            // 2018.03.25. YYK. 신고현황이 on 일때 신고현황값이 없거나 파손현황이 on일때 파손현황 값이 없을 경우 경고
            if ( $('#status').hasClass('on') && gMap.getLayerByName("SttemntLayer").features.length <= 0 ){
                alert("검색 결과가 없습니다. \n기간 및 조건을 변경하거나 검색범위를 다르게 하십시오.");
                $('#mCtrlPan').trigger('click') ;
                $("#dvMapLoading").hide();
                return;
            }
            if ( $('#dmg').hasClass('on') && gMap.getLayerByName("DmgtLayer").features.length <= 0 ){
                alert("검색 결과가 없습니다. \n기간 및 조건을 변경하거나 검색범위를 다르게 하십시오.");
                $('#mCtrlPan').trigger('click') ;
                $("#dvMapLoading").hide();
                return;
            }

            if ( gMap.getLayerByName("SttemntLayer")  ) {

            }


            if ( option.callback != undefined && option.callback != "" ) {
                // 함수 콜백
                option.iframe[option.callback](option);
            }

            $("#dvMapLoading").hide();

        } catch(err) {
            $("#dvMapLoading").hide();
            alert(err);
        }
        $("#dvMapLoading").hide();
    };*/

    // =========================== STTEMNT END =========================== //


    /**
     * 통합정보조회 - 조사정보조회
        * @description 여러개의 셀이 선택되었는지 체크한다.
        * @param {String} _sCellIds : CELL_ID
        * @param {Object} _oRes : feature 리스트
        * @param {String} _sCheckType : 체크 대상 (단일검색 / 다중검색)
        */
        var check_researchInfo = function(_sCellIds, _oRes, _sCheckType){
            var sUrl = "";

            switch(_sCheckType){
                case "selPointResearch":
                    sUrl = contextPath + 'api/mummsctnsrvydta/mummSctnSrvyDtaCellList.do';
                    break;
            }

            $.ajax({
                url: sUrl
                ,type: 'post'
                ,dataType: 'json'
                ,contentType : 'application/json'
                ,data: JSON.stringify( { CELL_ID : _sCellIds } )
                ,success: function(data){

                    var cellArr = _sCellIds.split(",");

                    if ( cellArr.length < 2 && data.length < 1 ) {

                        alert("선택 된 셀에 대한 조사정보가 없습니다.");
                        gMap.cleanMap();

                        if(typeof $("#dvMapLoading") == "object") $("#dvMapLoading").hide();
                        return;

                    } else if ( cellArr.length < 2 && data.length < 2 ) {

                        return;

                    } else if ( data.length < 1 ) {

                        alert("선택 된 셀에 대한 조사정보가 없습니다.");
                        gMap.cleanMap();

                        if(typeof $("#dvMapLoading") == "object") $("#dvMapLoading").hide();

                        return;

                    }

                    multiCellList = [];
                    var html = "";

                    switch(_sCheckType){
                        case "selPointResearch":
                            html = set_researchInfo_single(_oRes, data);
                            break;
                    }

                    $("#res_ChoiceSctnList").html(html);
                    $("#dvChoiceSctnRes").dialog();
                    $("#dvChoiceSctnRes").dialog("open");
                    $("#dvChoiceSctnRes").parent().css( { "width" : "600px", "z-index" : "9999" } );

                }
                ,error: function(a,b,msg){
                }
            });
        };

        /**
        * 통합정보조회 - 조사정보조회
        * @description 단일 검색의 중용구간 목록을 보여준다.
        * @param {Object} _oRes : feature 리스트
        * @param {Object} _oData : 중용구간 목록 정보
        */
        var set_researchInfo_single = function(_oRes, _oData){
            var html = "";
            html += "<table class='tblist' style='background-color:#fff;'>"
                + "    <colgroup>"
                + "        <col width='10%'/>"
                + "        <col width='20%'/>"
                + "        <col width='20%'/>"
                + "        <col width='12%'/>"
                + "        <col width='12%'/>"
                + "        <col width='13%'/>"
                + "        <col width='13%'/>"
                + "    </colgroup>"
                + "    <thead>"
                + "        <tr>"
                + "            <th scope='col'>노선번호</th>"
                + "            <th scope='col'>노선명</th>"
                + "            <th scope='col'>도로등급</th>"
                + "            <th scope='col'>차로</th>"
                + "            <th scope='col'>행선</th>"
                + "            <th scope='col'>시점</th>"
                + "            <th scope='col'>종점</th>"
                + "        </tr>"
                + "    </thead>"
                + "    <tbody>";

          for ( var i = 0; i < _oData.length; i++ ) {

              var cellId = _oData[i].CELL_ID ||  _oData[i].cell_id;
              var feats = [];

              html += "<tr class='tr_SctnInfosRows'>"
                    + "    <td style='display: none;'>" + cellId + "</td>"
                    + "    <td>" + _oData[i].ROAD_NO_VAL + "</td>"
                    + "    <td>" + _oData[i].ROAD_NM + "</td>"
                    + "    <td>" + _oData[i].ROAD_GRAD + "</td>"
                    + "    <td>" + _oData[i].TRACK + "</td>"
                    + "    <td>" + _oData[i].DIRECT_NM + "</td>"
                    + "    <td>" + _oData[i].STRTPT + "</td>"
                    + "    <td>" + _oData[i].ENDPT + "</td>"
                    + "</tr>";

              for ( var j = 0; j < _oRes.length; j++ ) {
            	  var cell_id = _oRes[j].feature.data.CELL_ID || _oRes[j].feature.data.cell_id;
                  if(cell_id == cellId){

                      feats.push(_oRes[j].feature);
                      break;

                  }

              }

              multiCellList.push({

                  CELL_ID   :   cellId,
                  feats     :   feats

              });

          }

          html += "</tbody>"
                + "</table>";

            return html;
        };

     /**
      * @description 섹션리스트 마우스 event
      */
      var select_SctnMouseEvent = function(){
          //다중선택시
          $(document).on("mouseenter", ".tr_SctnInfosRows", function(evt) {

              $(this).css("background-color", "#ddd");
              var idx = $(this).find("td:eq(0)").text();
              var feats = get_multiCellFeat(idx);

              for(var i = 0; i<feats.length; i++) {
                  feats[i].attributes = {
                          fillColor : '#ffffff',
                          strokeColor : '#ffffff'
                  };

                  gMap.getLayerByName('GAttrLayerMulti').addFeatures(feats[i]);

              }

          }).on("mouseleave", ".tr_SctnInfosRows", function(evt) {

              $(this).css("background-color", "#fff");

              var idx = $(this).find("td:eq(0)").text();
              var feats = get_multiCellFeat(idx);

              for(var i = 0; i<feats.length; i++) {

                  gMap.getLayerByName('GAttrLayerMulti').removeFeatures(feats[i]);

              }

          }).on("click", ".tr_SctnInfosRows", function(evt) {

              gMap.cleanMap();

              var idx = $(this).find("td:eq(0)").text();
              var feats = get_multiCellFeat(idx);

              for(var i = 0; i<feats.length; i++) {
                  add_feature_multi(feats[i]);
                  option.callback = "fnCheckFeatures";
              }

              if ( option.callback != undefined && option.callback != "" ) {

                  // 함수 콜백
                  option.iframe[option.callback](option);

              }

              $("#res_ChoiceSctnList").html('');
              $("#dvChoiceSctnRes").dialog("close");

          });
      };

      /**
       * @description multi select 된 셀 리스트 조회
       */
      var get_multiCellFeat = function(_sCellId){
          var feats = [];
          for(var i = 0; i<multiCellList.length; i++) {
        	  var cell_id = multiCellList[i].CELL_ID || multiCellList[i].cell_id;
              if(cell_id == _sCellId) {
                  feats = multiCellList[i].feats;
                  break;
              }
          }
          return feats;
      };

    // =========================== STATUS EVALUATION =========================== //

	/** 통합정보조회 컨트롤 */
	/**
	* @description 통합정보조회 컨트롤을 등록한다. - 포장상태평가 (셀조회)
	*/
	var init_inteSelRange = function(){
		var selControls = {
				point : new GGetFeature(GPoint, {
					persist	: true,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_10"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selPoint"
				}),
				polygon : new GGetFeature(GPolygon, {
					persist	: false,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_10"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selPolygon"
				}),
	            // 180718 wijy
	            // 선형선택 컨트롤 추가
	            line : new GGetFeature(GPath, {
	                persist : false,
	                //serviceUrl : CONFIG.fn_get_serviceUrl(),
	                serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
	                prefix : CONFIG.fn_get_dataHouseName(),
	                tables : [ "CELL_10" ],
	                excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
	                id : "selPath"
	            })
			};

		for ( var i in selControls) {
			selControls[i].events.on( {
				"callback" : event_selFeatureRange,
				"mousemove" : function(){}
			});

		    // 속성 조회 컨트롤 추가
            gMap.addControl(selControls[i]);

		}

		//180809 wijy
        //통합정보조회 노선선택 컨트롤 추가
        var selRouteControl = new GGetFeature(GPoint, {
            persist : true,
            //serviceUrl : CONFIG.fn_get_serviceUrl(),
            serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
            prefix : CONFIG.fn_get_dataHouseName(),
            tables : [ "CELL_10" ],
            excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
            id : "selRouteControl"
        });
        selRouteControl.events.on({callback : cb_selRouteControl});
        gMap.addControl(selRouteControl);
	};

	//180809 wijy
    //통합정보조회 노선선택 컨트롤 callback
    var cb_selRouteControl =  function(res) {
        if(!res.success()) return;
        
        
        //json key 명칭을 대문자 치환
        var jsonKeyUpperCase = function(obj){
        	var retJson = {};
        	function execute(obj, newJson){
        		for(var key in obj){
        			if(!key) continue;
        			
        			var value = obj[key];
        			var upperKeyName = key.toUpperCase();
        			
        			if(typeof value === 'object' && !Array.isArray(value)){
        				newJson[''+upperKeyName] = {};
        				execute(value, newJson[''+upperKeyName]);
        			}else{
        				newJson[''+upperKeyName] = obj[key]; 
        			}
        		}
        	}
        	execute(obj, retJson);
        	return retJson;
        }
        

        // 검색 조건 피쳐 삭제
        if (gMap.getControl(res.object.id) && gMap.getControl(res.object.id).handler && gMap.getControl(res.object.id).handler.layer && gMap.getControl(res.object.id).handler.layer.features && gMap.getControl(res.object.id).handler.layer.features.length > 0) {
            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();
        }


        //결과조회
        if(res.data != undefined && res.data.length > 0) {
            var oRes = res.data[0];
            set_dataField(oRes.results);

            var oTempLyr = gMap.getLayerByName("GlyrSelectCross");

            //2개 이상의 결과가 있더라도 무조건 한개만 선택함
            var oData = oRes.results[0];

            oData.feature.attributes = {
                fillColor : '#28959c',
                strokeColor : '#ff0000'
            };


            if(oTempLyr.features.length == 0) {
                //처음 클릭인 경우
                oTempLyr.addFeatures(oData.feature);
            } else if(oTempLyr.features.length > 0) {
                //두번째 클릭인 경우

                //기존 데이터의 노선, 방향, 차로값 검색
                var o1stData = oTempLyr.features[0];
                var o1stAttr = jsonKeyUpperCase(o1stData.data);
                var oAttr = jsonKeyUpperCase(oData.fields);
                
                if(o1stAttr.ROUTE_CODE == oAttr.ROUTE_CODE && o1stAttr.DIRECT_CODE == oAttr.DIRECT_CODE && o1stAttr.TRACK == oAttr.TRACK) {
                    //동일값이 선택된 경우
                    oTempLyr.addFeatures(oData.feature);
                } else {
                    //노선, 방향, 차로가 다름
                    alert("처음 선택한 노선과 노선, 방향, 차로 정보가 다릅니다.\r\n노선선택은 동일노선, 동일방향, 동일차로만 선택 가능합니다.\r\n기존에 선택된 노선 : [" +
                            o1stAttr.ROUTE_CODE + "] " + (o1stAttr.DIRECT_CODE == "S"?"상행" : "하행") + " " + o1stAttr.TRACK + "차로");
                    return;
                }
            }

            //두 점이 모두 선택된 뒤 control deactive
            if(oTempLyr.features.length > 1) {
                gMap.activeControls("drag");
                //loading 표시
                $("#dvMapLoading").show();

                //선택된 셀 정보로 공간조회하여 가운데 있는 셀 목록까지 모두 가져옴
                var o1stCell = jsonKeyUpperCase(oTempLyr.features[0].data);
                var o2ndCell = jsonKeyUpperCase(oTempLyr.features[1].data);

                var nStrtpt = o1stCell.STRTPT;
                if(nStrtpt > o2ndCell.STRTPT) nStrtpt = o2ndCell.STRTPT;
                var nEndpt = o1stCell.ENDPT;
                if(nEndpt < o1stCell.ENDPT) nEndpt = o2stCell.data.ENDPT;

                //도형검색하여 multilayer에 추가
                MAP.fn_get_getFeatureAndDrawByAttr({
                    map : parent.gMap,
                    layerNm : "GAttrLayerMulti",
                    types : [["==", "==", "==", ">=", "<="]],
                    tables : ["CELL_10"],
                    fields : [["ROUTE_CODE", "DIRECT_CODE", "TRACK", "STRTPT", "ENDPT"]],
                    values : [[o1stCell.ROUTE_CODE, o1stCell.DIRECT_CODE, o1stCell.TRACK, nStrtpt, nEndpt]],
                    attribute : {
                        attributes : {
                             fillColor : '#0033ff',
                             strokeColor : '#0033ff'
                        }
                    },
                    clearBeforeDraw : false,
                    callback : cb_search_selRouteControl
                });

                //속성검색하여 selectbox등 세팅
                fn_set_ui_selects({
                    route_code : o1stCell.ROUTE_CODE,
                    direct_code : o1stCell.DIRECT_CODE,
                    track : o1stCell.TRACK,
                    strtpt : nStrtpt,
                    endpt : nEndpt
                });
            }


        } else {
            //결과없음

        }
    }

  //속성조회
    var fn_set_ui_selects = function(_oData) {
        var oTargetIframeUrl = contextPath + "topmenu/selectIntegratedView.do";
        var oTargetIframe = $(document).find("iframe[src='" + oTargetIframeUrl + "']");

        var oRoute = $(oTargetIframe).contents().find("#sel_ms_ui_route").children("option[value='" + _oData.route_code + "']");
        if(oRoute.length > 0) {
            $(oRoute).prop("selected", true);
            //방향검색
            var oTargetframeCont = $(oTargetIframe)[0].contentWindow;

            var def = $.Deferred();
            def.resolve({data : _oData, target : oTargetIframe});

            //selectbox 방향 및 차로 세팅
            oTargetframeCont.fnSetUserInputRouteDir(_oData.route_code);

            $.when(MAP.CONTROL.ms_ui_listDef[1], MAP.CONTROL.ms_ui_listDef[2], def.promise()).done(function(_res1, _res2, _data) {
                //selectbox 세팅 (빙향 및 차로)
                var oTarget = _data.target;
                var oTargetCont = $(oTarget).contents();
                $(oTargetCont).find("#sel_ms_ui_dir option[value='" + _data.data.direct_code + "']").prop("selected", true);
                $(oTargetCont).find("#sel_ms_ui_track option[value='" + _data.data.track + "']").prop("selected", true);

                $(oTargetCont).find("#ip_ms_ui_strtpt").val(Math.floor(_data.data.strtpt * 1));
                $(oTargetCont).find("#ip_ms_ui_endpt").val(Math.floor(_data.data.endpt * 1));
                $("#dvMapLoading").hide();

                //def 초기화
                MAP.CONTROL.ms_ui_listDef = [$.Deferred(), $.Deferred(), $.Deferred()];
            });

        } else {
            //error
        }
    }


    //도형검색 콜백
    var cb_search_selRouteControl = function(res) {
        //검색도형 삭제
        if(res.result.code == "SUCCESS")
            gMap.getLayerByName("GlyrSelectCross").removeAllFeatures();
    }

    /**
    * @description 통합정보조회 컨트롤 event - 포장상태평가 (셀조회)
    */
    var event_selFeatureRange = function(res) {

        $("#dvMapLoading").hide();

        MAP.CONTROL.oCellsPerRD = {};
        if (!res.success()) {
            return;
        }

        // 검색 조건 피쳐 삭제
        if (gMap.getControl(res.object.id) && gMap.getControl(res.object.id).handler && gMap.getControl(res.object.id).handler.layer && gMap.getControl(res.object.id).handler.layer.features && gMap.getControl(res.object.id).handler.layer.features.length > 0) {
            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();

        }
        // 검색할 내용이 선택되지 않은 경우
        if (res.data == undefined || res.data.length < 1) {
            if (parent.gMap.getLayerByName("GAttrLayer").features.length == 0) {
                if ($("#btn_selPoint").parent().hasClass("on")) {
                    parent.gMap.activeControls("selPoint");
                } else if ($("#btn_selPolygon").parent().hasClass("on")) {
                    parent.gMap.activeControls("selPolygon");
                }

                alert("선택된 셀이 없습니다.");
            } else {
                parent.gMap.activeControls("drag");
            }
            return;
        }

        try {
            // 검색할 내용이 선택된 경우
            for (var i = 0; i < res.data.length; i++) {
                if (res.data[i].results == undefined || res.data[i].results.length < 1) {
                    continue;

                } else if (res.data[i].results.length > 200) {
                    alert("검색할 셀이 너무 많습니다.");
                    gMap.cleanMap();
                    return;
                }

                // 교차로 선택 추가 -- 180806 wijy
                var evt = gMap.getControl(res.object.id).handler.evt;
                event = evt;
                set_dataField(res.data[i].results);

                if (check_intersection(res.data[i].results)) {
                    var cellIds = get_cellIds(res.data[i].results);
                    check_routeInfo_new(cellIds, res.data[i].results, res.object.id);
                    continue;
                }

                for ( var j=0; j<res.data[i].results.length; j++ ) {

                    var feature = res.data[i].results[j].feature;
                    feature.attributes = {
                            fillColor : '#0033ff',
                            strokeColor : '#0033ff'
                     };
                    add_feature(res.data[i].results[j].feature, evt, "GAttrLayer");
                    //gMap.getLayerByName('GAttrLayerMulti').addFeatures(feature);

                }
            }

            if (option.callback != undefined && option.callback != "") {

                // 함수 콜백
                option.iframe[option.callback](option);

            }

        } catch (err) {

            alert(err);

        }

    };
    /*var event_selFeatureRange = function(res) {

        if ( !res.success() ) { return; }

        // 검색 조건 피쳐 삭제
        if( gMap.getControl(res.object.id)
                && gMap.getControl(res.object.id).handler
                && gMap.getControl(res.object.id).handler.layer
                && gMap.getControl(res.object.id).handler.layer.features
                && gMap.getControl(res.object.id).handler.layer.features.length > 0 ) {

            gMap.getControl(res.object.id).handler.layer.removeAllFeatures();

        }

        // 검색할 내용이 선택되지 않은 경우
        if( res.data == undefined || res.data.length < 1 ) {

            if ( parent.gMap.getLayerByName("GAttrLayerMulti").features.length == 0 ) {

                if ( $("#btn_selPoint").parent().hasClass("on") ) {

                    parent.gMap.activeControls("selPoint");

                } else if ( $("#btn_selPolygon").parent().hasClass("on") ) {

                    parent.gMap.activeControls("selPolygon");

                }

                alert("선택된 셀이 없습니다.");

            } else {

                parent.gMap.activeControls("drag");

            }

            return;

        }

        try {

            // 검색할 내용이 선택된 경우
            for(var i=0; i<res.data.length; i++){

                if( res.data[i].results == undefined
                        || res.data[i].results.length < 1 ) {

                    continue;

                } else if ( res.data[i].results.length > 200 ) {

                    alert("검색할 셀이 너무 많습니다.");
                    gMap.cleanMap();

                    return;

                }

                var evt = gMap.getControl(res.object.id).handler.evt;
                set_dataField(res.data[i].results);

                for ( var j=0; j<res.data[i].results.length; j++ ) {

                    var feature = res.data[i].results[j].feature;
                    feature.attributes = {
                            fillColor : '#0033ff',
                            strokeColor : '#0033ff'
                     };
                    //add_feature(res.data[i].results[j].feature, evt);
                    gMap.getLayerByName('GAttrLayerMulti').addFeatures(feature);

                }

            }

            if ( option.callback != undefined && option.callback != "" ) {

                // 함수 콜백
                option.iframe[option.callback](option);

            }

        } catch(err) {

            alert(err);

        }

    };*/

    // =========================== BRIDGE INFO =========================== //
    //통합조회_교량
    var init_inteSelBrdg = function(){
    	var selBrdgControls = {
				point : new GGetFeature(GPoint, {
					persist	: true,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["M_CALS_T"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selBrdgPoint"
				}),
				polygon : new GGetFeature(GPolygon, {
					persist	: false,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["M_CALS_T"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selBrdgPolygon"
				})
			};

		for ( var i in selBrdgControls) {
			selBrdgControls[i].events.on( {
				"callback" : event_selFeatureBrdg,
				"mousemove" : function(){}
			});

		    // 속성 조회 컨트롤 추가
            gMap.addControl(selBrdgControls[i]);

		}
    };

	//통합조회_교량
	var event_selFeatureBrdg = function(res) {
		if(typeof $("#dvMapLoading") == "object") $("#dvMapLoading").hide();
		if (res.success()) {
			if (res.data.length == "0") {
				//영역내에 해당하는 자료가 없음
				alert('검색 결과가 없습니다.');

				//점 피쳐 삭제
				if(gMap.getControl("selBrdgPoint") && gMap.getControl("selBrdgPoint").handler && gMap.getControl("selBrdgPoint").handler.layer && gMap.getControl("selBrdgPoint").handler.layer.features && gMap.getControl("selBrdgPoint").handler.layer.features.length > 0) {
					gMap.getControl("selBrdgPoint").handler.layer.removeAllFeatures();
				}

				//폴리곤 피쳐 삭제
				if(gMap.getControl("selBrdgPolygon") && gMap.getControl("selBrdgPolygon").handler && gMap.getControl("selBrdgPolygon").handler.layer && gMap.getControl("selBrdgPolygon").handler.layer.features && gMap.getControl("selBrdgPolygon").handler.layer.features.length > 0) {
					gMap.getControl("selBrdgPolygon").handler.layer.removeAllFeatures();
				}
				
			}else {
				try {

				    // 검색할 내용이 선택된 경우
			        for(var i=0; i<res.data.length; i++){

			            if( res.data[i].results == undefined
			                    || res.data[i].results.length < 1 ) {

			                continue;

			            } else if ( res.data[i].results.length > 500 ) {

		                    alert("검색할 교량이 너무 많습니다.");
		                    gMap.cleanMap();

		                    return;

		                }

			            gMap.cleanMap();
			            var evt = gMap.getControl(res.object.id).handler.evt;
			            set_dataField(res.data[i].results);

			            for ( var j=0; j<res.data[i].results.length; j++ ) {

			            	add_brdg_feature(res.data[i].results[j].feature, evt);

			            }

			        }

			        if ( option.callback != undefined && option.callback != "" ) {

			            // 함수 콜백
			            option.iframe[option.callback](option);

		            }

				} catch(err) {

				    alert(err);

				}
			}

		} else {
			//요청 실패
			alert('요청 실패');
		}
	};

	// ====================================================================== //


	/**
     * @description 2019년 고도화 사업 - 포트홀신고정보(시군관리) 편집용 컨트롤 생성
     */
    var init_editPothole = function() {

    	var defaultStyle ={
    			"Point" : {
    				featureType : "Point",
    				pointRadius: 4,
    				graphicName: "circle",
    				fillColor : "#0033ff",
    				fillOpacity : 0.5,
    				strokeWidth: 1,
    				strokeOpacity : 1,
    				strokeColor : "#0033ff",
    			}
    	};

    	// 포트홀신고정보(시군관리) 편집용 레이어 추가
        var GPthEditLayer = new GVector("GPthEditLayer", {
      		styleMap : new OpenLayers.StyleMap( {
    			'default' : new OpenLayers.Style(null, {
    				rules : [ new OpenLayers.Rule( {
    					symbolizer : {
                        	//externalGraphic: '/gpms/images/icon_marker1.png',
    						externalGraphic: '/gpms/images/icon_loc.png',
                            graphicWidth: '34',
                            graphicHeight: '34',
                            rotation: "${angle}",
                            graphicXOffset: -17,
                            graphicYOffset: -17,
                            graphicOpacity: 1,
    					},
    					filter : new OpenLayers.Filter.Comparison( {
    						type : "==",
    						property : "featureType",
    						value : "Point"
    					})
    				})]
    			})
    		})
      	});

    	// 포트홀신고정보(시군관리) 편집용 컨트롤 추가
    	var ePthControls = [
	        new GDrawFeature(GPthEditLayer, GPoint, {
     			id : "drawPoint",
     			handlerOptions : {
     				attributes : defaultStyle["Point"]
     			}
     		})

      	];

    	var mPthControls = [
	        new GDrawFeature(GPthEditLayer, GPoint, {
     			id : "drawInsertEdit",
     			handlerOptions : {
     				attributes : defaultStyle["Point"]
     			}
     		})

      	];

    	var SttemntLayer = gMap.getLayerByName("SttemntLayer");
    	var DmgtLayer = gMap.getLayerByName("DmgtLayer");

    	var modifyControls = [
      			new GModifyFeature(SttemntLayer, {
      			//new OpenLayers.Control.ModifyFeature(GPthEditLayer, {
      			id : 'drawEditPoint',
      			mode : OpenLayers.Control.ModifyFeature.RESHAPE
      			}),
      			new GModifyFeature(DmgtLayer, {
      			//new OpenLayers.Control.ModifyFeature(GPthEditLayer, {
      			id : 'drawEditPointDmgt',
      			mode : OpenLayers.Control.ModifyFeature.RESHAPE
         		})
      	];

    	gMap.addLayer(GPthEditLayer);
    	gMap.addControls(ePthControls);
    	gMap.addControls(mPthControls);
    	gMap.addControls(modifyControls);
    	gMap.addControls([
    	                  new OpenLayers.Control.KeyboardDefaults({
                              observeElement: 'map'
                          })
                      	]);

    	for ( var i in ePthControls) {
    		ePthControls[i].events.on( {
    			"featureadded" : event_editFeaturePthmnt,
				"mousemove" : function(){}
			});

		}

    	for ( var i in mPthControls) {
    		mPthControls[i].events.on( {
				"featureadded" : event_modifyFeaturePthmnt,
				"mousemove" : function(){}
			});

		}

    	SttemntLayer.events.on( {
			"featuremodified" : event_modifyFeaturePthmnt2
		});

    	DmgtLayer.events.on( {
			"featuremodified" : event_modifyFeaturePthmnt2
		});

    };

    /**
     * @description 2019년 고도화 사업 - 포트홀신고정보(시군관리) 편집용 컨트롤 event
     */
     var event_editFeaturePthmnt = function() {

    	//위치그린거 가져오기
		var layer = "GPthEditLayer";
		var pointfeature = gMap.getLayerByName(layer).features[0];

		try{

			// 1. 관할구역 확인
 			GRequest.WFS.getFeatureByGeometry(
				//CONFIG.fn_get_serviceUrl(),
				CONFIG.fn_get_wfsServiceUrl(),
				{
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CMPTNC_ZONE"],
					values : [pointfeature.geometry]
				},function(res){

					if(res.data.length == 0){
						alert("관할구역 이외의 지역에 입력하실 수 없습니다.");
						gMap.activeControls("drag");
						gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
						return;
					}

					var area_chk = false;
					for(var i=0;i<res.data[0].results.length;i++){

						var f_deptCode = res.data[0].results[0].fields.DEPT_CODE;
						var userDeptCode = $("#USER_DEPT_CODE").val();

						if(f_deptCode == userDeptCode){

							area_chk = true;

							var answer = confirm("포트홀 신고 등록 하시겠습니까?\r\n위치를 다시 입력하시려면 취소를 선택하십시오.");
					 		if(answer){

					 			var fields = ["CRDNT_X","CRDNT_Y","CORTN_X","CORTN_Y",
					 			              "RCEPT_CN"];
					 			var values = [];
					 			var g2_id = "";
					 			var cptg2Id = "0";	//관할구역 ID 기본값 0

					 			var vertices = pointfeature.geometry.getVertices();
					 			var resultX = vertices[0].x;
					 			var resultY = vertices[0].y;

					 			oLatLng = new OpenLayers.LonLat(resultX, resultY);
					 			var oTrans_sLatLng = oLatLng.transform(gMap.getProjection(), MAP.fn_get_daumMap().projection.projCode);
					 			//alert("chk : " + oTrans_sLatLng.lon + " / " + oTrans_sLatLng.lat);

					 			values.push(oTrans_sLatLng.lon);
					 			values.push(oTrans_sLatLng.lat);
					 			values.push(oTrans_sLatLng.lon);
					 			values.push(oTrans_sLatLng.lat);
					 			values.push("시군관리 포트홀 신고입니다.");

				 				//포트홀 위치 등록
				 				GRequest.WFS.insert(
				 						//CONFIG.fn_get_serviceUrl(),
				 						CONFIG.fn_get_wfsServiceUrl(),
				 						pointfeature,
				 						CONFIG.fn_get_dataHouseName(),
				 						"PTH_CTSMNT",
				 						fields,
				 						values,
				 						function(res) {
				 							if(res.ids[0] == ""){
				 								alert("포트홀 신고 위치 입력 중 오류가 발생하였습니다.\r\n위치를 다시 입력해주십시오. ");
				 								gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
				 								return false;
				 							}
				 							g2_id = res.ids[0];
				 							//alert("g2_id : " + g2_id);

				 							for(var i in gMap.controls){
				 								gMap.controls[i].deactivate();
				 							}

				 							var urlParam = "pthmode=C&imode=ADD&GG_ID=" + g2_id;
				 							urlParam += "&cptg2Id=" + cptg2Id;
				 							COMMON_UTIL.cmWindowOpen('포트홀 신고정보 등록', contextPath + "pothole/sttemnt/selectSttemntUpdate.do?" + urlParam, 600, 1200, false, null, 'left');

				 							fnCheckFeatures();
				 							$("#dvMapLoading").hide();
				 						});

							}else{

					 			gMap.activeControls("drag");
								gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
					 		}
			 				break;
						}
					}

					if(!area_chk) {
						alert("관할구역 이외의 지역에 입력하실 수 없습니다.");
						gMap.activeControls("drag");
						gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
					}
			});
		}catch(e){
			alert("위치저장실패");
 			gMap.activeControls("drag");
			gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
		}

     };


     /**
      * @description 2019년 고도화 사업 - 포트홀신고정보(시군관리) 편집용 컨트롤 event
      */
      var event_modifyFeaturePthmnt = function() {

     	//위치그린거 가져오기
 		var layer = "GPthEditLayer";
 		var pointfeature = gMap.getLayerByName(layer).features[0];
 		var MGG_ID = "";
 		var MPTH_RG_NO = "";

 		if($("#btn_pthSelect").parent().hasClass("on")){

 			MGG_ID = $("#MGG_ID").val();
 			MPTH_RG_NO = $("#MPTH_RG_NO").val();
 			alert("MGG_ID : " + MGG_ID);

 		}

     	var answer = confirm("포트홀 신고 위치를 수정 하시겠습니까?\r\n위치를 다시 입력하시려면 취소를 선택하십시오.");
  		if(answer){
  			var fields = ["GID","CRDNT_X","CRDNT_Y","CORTN_X","CORTN_Y",
  			              "RCEPT_CN"];
  			var values = [];
  			var cptg2Id = "0";	//관할구역 ID 기본값 0

  			var vertices = pointfeature.geometry.getVertices();
  			var resultX = vertices[0].x;
  			var resultY = vertices[0].y;

  			oLatLng = new OpenLayers.LonLat(resultX, resultY);
  			var oTrans_sLatLng = oLatLng.transform(gMap.getProjection(), MAP.fn_get_daumMap().projection.projCode);
  			//alert("chk : " + oTrans_sLatLng.lon + " / " + oTrans_sLatLng.lat);

  			values.push(MGG_ID);
  			values.push(oTrans_sLatLng.lon);
  			values.push(oTrans_sLatLng.lat);
  			values.push(oTrans_sLatLng.lon);
  			values.push(oTrans_sLatLng.lat);
  			values.push("시군관리 포트홀 신고입니다.");

  			try{
  				//포트홀 위치 수정
  				GRequest.WFS.update(
  						//CONFIG.fn_get_serviceUrl(),
  						CONFIG.fn_get_wfsServiceUrl(),
  						pointfeature,
  						CONFIG.fn_get_dataHouseName(),
  						"PTH_CTSMNT",
  						fields,
  						values,
  						MGG_ID,
  						function(res) {

  							//tn_pothole_ctsemnt의 좌표 정보를 update 해준다.

  							var postData = { "GG_ID" : MGG_ID
		  			                       , "CORTN_X" : oTrans_sLatLng.lon
		  			                       , "CORTN_Y" : oTrans_sLatLng.lat
		  			                       , "TM_X" : resultX
		  			                       , "TM_Y" : resultY
		  			                       , "pthmode" : "C"
		  			        };

  							$.ajax({
  					            url: contextPath + 'api/pothole/sttemnt/updatePotholeXY.do'
  					            , type: 'post'
  					            , dataType: 'json'
  					            , contentType : 'application/json; charset=UTF-8'
  					            , data : JSON.stringify(postData)
  					            , success : function(res2) {

  					                if ( res2 != 1 ) {
  					                    alert("포트홀 신고 위치 수정 중 오류가 발생하였습니다.\r\n위치를 다시 입력해주십시오.");
  					                    gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
  					                    return false;
  					                }
  					            }
  					        });

			                if(res && res.count && res.count > 0) {
  								alert('포트홀 신고 위치가 수정되었습니다.');

  								fnMakerSync(MGG_ID, MPTH_RG_NO, resultX, resultY, "C");

  							} else {
  								alert("포트홀 신고 위치 수정 중 오류가 발생하였습니다.\r\n위치를 다시 입력해주십시오. ");
  								gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
  								return false;
  							}

			                for(var i in gMap.controls){
  								gMap.controls[i].deactivate();
  							}

  							//var urlParam = "pthmode=C&imode=&GG_ID=" + MGG_ID;
  							//urlParam += "&cptg2Id=" + cptg2Id;
  							//COMMON_UTIL.cmWindowOpen('포트홀 신고정보 등록', contextPath + "pothole/sttemnt/selectSttemntUpdate.do?" + urlParam, 600, 1200, false, null, 'left');
  						});
  			}catch(e){ alert("위치저장실패");
	  			gMap.activeControls("drag");
  			}
  		}else{
  			gMap.activeControls("drag");
  		}

  		fnCheckFeatures();
  		gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
  		gMap.activeControls("drag");
 		$("#dvMapLoading").hide();
      };


	  var event_modifyFeaturePthmnt2 = function() {

	   	//위치그린거 가져오기
		var layer = $("#status").hasClass("on") ? "SttemntLayer" : "DmgtLayer";
		var pointfeature = gMap.getLayerByName(layer).features[0];
		var MGG_ID = "";
		var MPTH_RG_NO = "";

		if($("#btn_pthUpdate").parent().hasClass("on")){

			MGG_ID = $("#MGG_ID").val();
			MPTH_RG_NO = $("#MPTH_RG_NO").val();
			//alert("MGG_ID : " + MGG_ID);

		}

	   	var answer = confirm("포트홀 신고 위치를 수정 하시겠습니까?\r\n위치를 다시 입력하시려면 취소를 선택하십시오.");
			if(answer){
				var fields = ["GID","CRDNT_X","CRDNT_Y","CORTN_X","CORTN_Y",
				              "RCEPT_CN"];
				var values = [];
				var cptg2Id = "0";	//관할구역 ID 기본값 0

				var vertices = pointfeature.geometry.getVertices();
				var resultX = vertices[0].x;
				var resultY = vertices[0].y;

				oLatLng = new OpenLayers.LonLat(resultX, resultY);
				var oTrans_sLatLng = oLatLng.transform(gMap.getProjection(), MAP.fn_get_daumMap().projection.projCode);
				//alert("chk : " + oTrans_sLatLng.lon + " / " + oTrans_sLatLng.lat);

				values.push(MGG_ID);
				values.push(oTrans_sLatLng.lon);
				values.push(oTrans_sLatLng.lat);
				values.push(oTrans_sLatLng.lon);
				values.push(oTrans_sLatLng.lat);
				values.push("시군관리 포트홀 신고입니다.");

				try{
					//포트홀 위치 수정
					GRequest.WFS.update(
							//CONFIG.fn_get_serviceUrl(),
							CONFIG.fn_get_wfsServiceUrl(),
							pointfeature,
							CONFIG.fn_get_dataHouseName(),
							"PTH_CTSMNT",
							fields,
							values,
							MGG_ID,
							function(res) {

								//tn_pothole_ctsemnt의 좌표 정보를 update 해준다.

								var postData = { "GG_ID" : MGG_ID
		  			                       , "CORTN_X" : oTrans_sLatLng.lon
		  			                       , "CORTN_Y" : oTrans_sLatLng.lat
		  			                       , "TM_X" : resultX
		  			                       , "TM_Y" : resultY
		  			                       , "pthmode" : "C"
		  			        };

								$.ajax({
						            url: contextPath + 'api/pothole/sttemnt/updatePotholeXY.do'
						            , type: 'post'
						            , dataType: 'json'
						            , contentType : 'application/json; charset=UTF-8'
						            , data : JSON.stringify(postData)
						            , success : function(res2) {

						                if ( res2 != 1 ) {
						                    alert("포트홀 신고 위치 수정 중 오류가 발생하였습니다.\r\n위치를 다시 입력해주십시오.");
						                    gMap.getLayerByName(layer).removeAllFeatures();
						                    return false;
						                }
						            }
						        });

								if(res && res.count && res.count > 0) {
									alert('포트홀 신고 위치가 수정되었습니다.');

									fnMakerSync(MGG_ID, MPTH_RG_NO, resultX, resultY, "C");

								} else {
									alert("포트홀 신고 위치 수정 중 오류가 발생하였습니다.\r\n위치를 다시 입력해주십시오. ");
									gMap.getLayerByName(layer).removeAllFeatures();
									return false;
								}

			                	for(var i in gMap.controls){
									gMap.controls[i].deactivate();
								}

							});
				}catch(e){
					alert("위치수정실패 : " + e);
				}

				fnClearStep1Data();
				$("#btn_pthSelect").parent().addClass("on");
				fnCheckFeatures();
				gMap.getLayerByName(layer).removeAllFeatures();
		 		$("#dvMapLoading").hide();

			} else{

				gMap.getLayerByName(layer).removeAllFeatures();
				gMap.getLayerByName(layer).redraw();
			}

	    };


	    /**
		* @description 포트홀 신고등록 - 삭제 처리
		*/
	    var deleteFeaturePthmnt = function() {

	    	//위치선택 가져오기
			var layer = $("#status").hasClass("on") ? "SttemntLayer" : "DmgtLayer";
			var pointfeature = gMap.getLayerByName(layer).features[0];
			var MGG_ID = "";
			var MPTH_RG_NO = "";
			var MRPRDTLS_MNG_NO = "";

			if($("#btn_pthDelete").parent().hasClass("on")){

				MGG_ID = $("#MGG_ID").val();
				MPTH_RG_NO = $("#MPTH_RG_NO").val();
				MRPRDTLS_MNG_NO = $("#MRPRDTLS_MNG_NO").val();
				//alert("MGG_ID : " + MGG_ID + " / MRPRDTLS_MNG_NO : " + MRPRDTLS_MNG_NO);

				if(MGG_ID == "" || MPTH_RG_NO == "" || MRPRDTLS_MNG_NO == ""){
					alert("삭제 실패하였습니다.");
					return;
				}
			}

	    	if ( confirm("신고 정보 및 보수정보가 모두 삭제됩니다.\r\n삭제하시겠습니까?") ) {

	            var postData = { "PTH_RG_NO" : MPTH_RG_NO
	                           , "RPRDTLS_MNG_NO" : MRPRDTLS_MNG_NO
	                           , "pthmode" : "C"
	            };

	            $.ajax({
	                url: contextPath + 'api/pothole/sttemnt/deletePotholeSttemntAll.do'
	                , type: 'post'
	                , dataType: 'json'
	                , contentType : 'application/json; charset=UTF-8'
	                , data : JSON.stringify(postData)
	                , success : function(res) {

	                    if ( res != 1 ) {
	                        alert("삭제 실패하였습니다.");
	                        return;
	                    }

	                    //위치 정보 삭제
	                    if(MGG_ID != "") {

	                    	//위치정보 삭제하지 않음.
	                    	//MAP.fn_del_feature("PTH_CTSMNT",MGG_ID);
	                    	fnSelectLayer();

	                    	gMap.activeControls("drag");
	                        gMap.getLayerByName('SttemntLayer').removeAllFeatures();
	                        gMap.getLayerByName('DmgtLayer').removeAllFeatures();

	                    }
	                    alert( "삭제되었습니다.");

	                }
	            });
	        } else {

	        	gMap.activeControls("drag");
	            gMap.getLayerByName('SttemntLayer').removeAllFeatures();
	            gMap.getLayerByName('DmgtLayer').removeAllFeatures();
	            gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
	        }

	    };


	/**
	* @description 셀 속성 편집
	*/
	var init_selSectControls = function(){
		var selSectControls = {
				point : new GGetFeature(GPoint, {
					persist	: true,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_SECT"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selSectPoint"
				}),
				polygon : new GGetFeature(GPolygon, {
					persist	: false,
					//serviceUrl : CONFIG.fn_get_serviceUrl(),
					serviceUrl : CONFIG.fn_get_wfsServiceUrl(),
					prefix : CONFIG.fn_get_dataHouseName(),
					tables : ["CELL_SECT"],
					excepts : [ "boundedby", "objectid", "shape_area", "shape_len" ],
					id : "selSectPolygon"
				})
			};

		for ( var i in selSectControls) {
			selSectControls[i].events.on( {
				"callback" : event_selSectFeature,
				"mousemove" : function(){}
			});

		    // 속성 조회 컨트롤 추가
			gMap.addControl(selSectControls[i]);

		}
	};


	var set_option = function(_oData){
	   option =  _oData;
	}

	/**
	* @description 셀 속성 컨트롤 event
	*/
	var event_selSectFeature = function(res){
		if(typeof $("#dvMapLoading") == "object") $("#dvMapLoading").hide();
		if (res.success()) {
			if (res.data.length == "0") {
				//영역내에 해당하는 자료가 없음
				alert('검색 결과가 없습니다.');

				//점 피쳐 삭제
				if(gMap.getControl("selSectPoint") && gMap.getControl("selSectPoint").handler && gMap.getControl("selSectPoint").handler.layer && gMap.getControl("selSectPoint").handler.layer.features && gMap.getControl("selSectPoint").handler.layer.features.length > 0) {
					gMap.getControl("selSectPoint").handler.layer.removeAllFeatures();
				}

				//폴리곤 피쳐 삭제
				if(gMap.getControl("selSectPolygon") && gMap.getControl("selSectPolygon").handler && gMap.getControl("selSectPolygon").handler.layer && gMap.getControl("selSectPolygon").handler.layer.features && gMap.getControl("selSectPolygon").handler.layer.features.length > 0) {
					gMap.getControl("selSectPolygon").handler.layer.removeAllFeatures();
				}
				
			}else {
				try {
				 // 검색할 내용이 선택된 경우
			        for(var i=0; i<res.data.length; i++){

			            if( res.data[i].results == undefined
			                    || res.data[i].results.length < 1 ) {

			                continue;

		            	} else if ( res.data[i].results.length > 500 ) {

		                    alert("검색할 섹션이 너무 많습니다.");
		                    gMap.cleanMap();

		                    return;

		                }

			            gMap.cleanMap();
			            var evt = gMap.getControl(res.object.id).handler.evt;
			            set_dataField(res.data[i].results);

			            for ( var j=0; j<res.data[i].results.length; j++ ) {

			                add_sect_feature(res.data[i].results[j].feature, evt);

			            }

			        }

			        if ( option.callback != undefined && option.callback != "" ) {

			            // 함수 콜백
			            option.iframe[option.callback](option);

		            }

				} catch(err) {

				    alert(err);

				}
			}

		} else {
			//요청 실패
			alert('요청 실패');
		}
	};


	return {
	    oCellsPerRD                 :   oCellsPerRD,
        ms_ui_listDef               :   listDef,

		fn_add_mapControl			:			fn_add_mapControl,

		fn_init_attr				:			fn_init_attr,
		fn_event_attr				:			fn_event_attr,
		fn_create_attrTitle			:			fn_create_attrTitle,
		fn_create_attrContent		:			fn_create_attrContent,
		fn_create_dtlinfo			:			fn_create_dtlinfo,

		fn_get_cdeval				:			fn_get_cdeval,

		fn_cursor_map				:			fn_cursor_map,

		init_cellSel				:			init_cellSel,
		activate_cellSel			:			activate_cellSel,
		select_mouseEvent			:			select_mouseEvent,
		event_selFeature			:			event_selFeature,

		add_feature					:			add_feature,
		remove_feature				:			remove_feature,

		check_intersection			:			check_intersection,
		check_routeInfo				:			check_routeInfo,
		check_routeInfo_new         :           check_routeInfo_new,

		set_dataField				:			set_dataField,
		get_cellIds					:			get_cellIds,
		get_crossCellFeat			:			get_crossCellFeat,

		// 통합정보조회 ================================================= //
		set_option                  :           set_option,

		init_inteSelRoad            :           init_inteSelRoad,
        event_selFeatureRoad        :           event_selFeatureRoad,

        init_inteSelResearch        :           init_inteSelResearch,
        event_selFeatureResearch    :           event_selFeatureResearch,

        // YYK 2180219
        init_inteSelSttemnt         :           init_inteSelSttemnt,
        event_selFeatureSttemnt     :           event_selFeatureSttemnt,
        //init_inteSelDmgt            :           init_inteSelDmgt,
        //event_selFeatureDmgt        :           event_selFeatureDmgt,

        //2019년 고도화 사업 - 포트홀신고정보(시군관리) 편집용 컨트롤, 이벤트
        init_editPothole         :           	init_editPothole,
        event_editFeaturePthmnt    :          	event_editFeaturePthmnt,
        event_modifyFeaturePthmnt  :			event_modifyFeaturePthmnt,
        event_modifyFeaturePthmnt2  :			event_modifyFeaturePthmnt2,
        deleteFeaturePthmnt			:			deleteFeaturePthmnt,

        check_researchInfo          :           check_researchInfo,
        set_researchInfo_single     :           set_researchInfo_single,
        select_SctnMouseEvent       :           select_SctnMouseEvent,
        get_multiCellFeat           :           get_multiCellFeat,

        init_inteSelRange           :           init_inteSelRange,
        event_selFeatureRange       :           event_selFeatureRange,

        init_inteSelBrdg			:			init_inteSelBrdg,
		event_selFeatureBrdg		:			event_selFeatureBrdg,
		// ============================================================== //

		init_selSectControls        :           init_selSectControls,
		event_selSectFeature		:			event_selSectFeature,

		init_cellSelLonLat            :           init_cellSelLonLat,
		event_selFeatureLonLat        :           event_selFeatureLonLat


	}
}(jQuery));
