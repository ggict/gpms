<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
</head>

<body id="wrap" class="right-tool thememap">

    <!-- 필수 파라메터(START) -->
    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
    <input type="hidden" id="opener_id" name="opener_id" value=""/>
    <input type="hidden" id="wnd_id" name="wnd_id" value=""/>
    <!-- 필수 파라메터(END) -->

    <div class="content" style="width: 379px; height: 658px; -ms-overflow-y: scroll; overflow-y: scroll;">

	    <!-- 구간 종류별 지도 조회 START -->
	    <div class="titbx" id="MV_CELL_SECT_TYPE">
            <h4 style="background-color: #636873;">
                <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="구간 종류별 지도조회" style="position: relative; top: -4px;" /></a> 구간 종류별 지도조회
            </h4>
            <div>
                <ul class="legend">
                    <li class="tRule"><span class="smcircle" style="background-color:#f4b183"></span><span class="rNameKor">교차로</span><span style="font-size: 11px; color: red; float: right;"></span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#0e9611"></span><span class="rNameKor">지하차도</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#bf9000"></span><span class="rNameKor">교량</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#c9c9c9"></span><span class="rNameKor">고가도</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#385723"></span><span class="rNameKor">터널</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#002060"></span><span class="rNameKor">일반차로</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#ffc000"></span><span class="rNameKor">특별관리구간</span></li>
                </ul>

            </div>
        </div>
        <!-- 구간 종류별 지도 조회 END -->

        <!-- 연도별 조사구역 지도 조회 START -->
        <div class="titbx mt10" id="MV_THM_YEAR_10">
            <h4 style="background-color: #636873;">
                <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="연도별 조사구역 지도조회" style="position: relative; top: -4px;" /></a> 연도별 조사구역 지도조회
            </h4>
            <div>
                <ul class="legend">
                    <li class="tRule"><span class="smcircle" style="background-color:#f8cbad"></span><span class="rNameKor">2017년 조사구역</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#b4c7e7"></span><span class="rNameKor">2018년 조사구역</span></li>
                    <li class="tRule"><span class="smcircle" style="background-color:#c55a11"></span><span class="rNameKor">2019년 조사구역</span></li>
                </ul>

            </div>
        </div>
        <!-- 연도별 조사구역 지도 조회 END -->

        <!-- 포장상태 평가정보 지도 조회 START -->
        <!-- CELL START -->
        <div class="titbx mt10" id="MV_SRVYDTA_10">
            <h4 style="background-color: #636873;">포장상태 평가정보 지도조회 10m셀</h4>
            <div>
            	<div style="margin:5px 10px;">
            		<label for="">노선명</label>
            		<select style="width:200px; margin:3px;" class="sel_routeInfo" id="selRoute_MV_SRVYDTA_10"></select>
            	</div>

	            <!-- 2017년도 평가정보 10m셀 START -->
				<div class="titbx pt5 pl5 pr5" id="MV_SRVYDTA_10_2017">
					<h4>
					   <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="2017년도 포장상태 평가정보 지도조회 10m셀" style="position: relative; top: -4px;" /></a> 2017년도 평가정보
					</h4>
					<div>
						<ul class="legend">
							<li class="tRule"><span class="smcircle" style="background-color:#00b050"></span><span class="rNameKor">8.00 이상</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#0070c0"></span><span class="rNameKor">7.99 ~ 7.00</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#ffff00"></span><span class="rNameKor">6.99 ~ 6.00</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#ffc000"></span><span class="rNameKor">5.99 ~ 5.00</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#ff6600"></span><span class="rNameKor">4.99 ~ 4.00</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#ff0000"></span><span class="rNameKor">3.99 이하</span></li>
							<li class="tRule"><span class="smcircle" style="background-color:#bbbbbb"></span><span class="rNameKor">정보없음</span></li>
					    </ul>
					</div>
				</div>
				<!-- 2017년도 평가정보 10m셀 END -->

				<!-- 2018년도 평가정보 10m셀 START -->
	            <div class="titbx pt5 pl5 pr5 pb5" id="MV_SRVYDTA_10_2018">
	                <h4>
	                   <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="2018년도 포장상태 평가정보 지도조회 10m셀" style="position: relative; top: -4px;" /></a> 2018년도 평가정보
	                </h4>
	                <div>
	                    <ul class="legend">
	                        <li class="tRule"><span class="smcircle" style="background-color:#00b050"></span><span class="rNameKor">8.00 이상</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#0070c0"></span><span class="rNameKor">7.99 ~ 7.00</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#ffff00"></span><span class="rNameKor">6.99 ~ 6.00</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#ffc000"></span><span class="rNameKor">5.99 ~ 5.00</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#ff6600"></span><span class="rNameKor">4.99 ~ 4.00</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#ff0000"></span><span class="rNameKor">3.99 이하</span></li>
	                        <li class="tRule"><span class="smcircle" style="background-color:#bbbbbb"></span><span class="rNameKor">정보없음</span></li>
	                    </ul>
	                </div>
	            </div>
	            <!-- 2018년도 평가정보 10m셀 END -->
            </div>
        </div>
        <!-- CELL END -->

        <!-- SECTION START -->
        <div class="titbx mt10 mb10" id="MV_GNLSTTUS_SECT">
            <h4 style="background-color: #636873;">포장상태 평가정보 지도조회 200m (섹션)</h4>
            <div>
            	<div style="margin:5px 10px;">
            		<label for="">노선명</label>
            		<select style="width:200px; margin:3px;" class="sel_routeInfo" id="selRoute_MV_GNLSTTUS_SECT"></select>
            	</div>
                <!-- 2017년도 평가정보 섹션셀 START -->
                <div class="titbx pt5 pl5 pr5" id="MV_GNLSTTUS_SECT_2017">
                    <h4>
                        <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="2017년도 포장상태 평가정보 지도조회 200m (섹션)" style="position: relative; top: -4px;" /></a> 2017년도 평가정보
                    </h4>
                    <div>
                        <ul class="legend">
                            <li class="tRule"><span class="smcircle" style="background-color:#00b050"></span><span class="rNameKor">8.00 이상</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#0070c0"></span><span class="rNameKor">7.99 ~ 7.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ffff00"></span><span class="rNameKor">6.99 ~ 6.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ffc000"></span><span class="rNameKor">5.99 ~ 5.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ff6600"></span><span class="rNameKor">4.99 ~ 4.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ff0000"></span><span class="rNameKor">3.99 이하</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#bbbbbb"></span><span class="rNameKor">정보없음</span></li>
                        </ul>
                    </div>
                </div>
                <!-- 2017년도 평가정보 섹션셀 END -->

                <!-- 2018년도 평가정보 섹션셀 START -->
                <div class="titbx pt5 pl5 pr5 pb5" id="MV_GNLSTTUS_SECT_2018">
                    <h4>
                        <a href="#" class="tLayer"><img src="<c:url value='/images/thememap/map_off.png'/>" alt="off" class="mt5 mr10 onoff off" title="2018년도 포장상태 평가정보 지도조회 200m (섹션)" style="position: relative; top: -4px;" /></a> 2018년도 평가정보
                    </h4>
                    <div>
                        <ul class="legend">
                            <li class="tRule"><span class="smcircle" style="background-color:#00b050"></span><span class="rNameKor">8.00 이상</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#0070c0"></span><span class="rNameKor">7.99 ~ 7.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ffff00"></span><span class="rNameKor">6.99 ~ 6.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ffc000"></span><span class="rNameKor">5.99 ~ 5.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ff6600"></span><span class="rNameKor">4.99 ~ 4.00</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#ff0000"></span><span class="rNameKor">3.99 이하</span></li>
                            <li class="tRule"><span class="smcircle" style="background-color:#bbbbbb"></span><span class="rNameKor">정보없음</span></li>
                        </ul>
                    </div>
                </div>
                <!-- 2018년도 평가정보 섹션셀 END -->
            </div>
        </div>
        <!-- SECTION END -->
        <!-- 포장상태 평가정보 지도 조회 END -->

    </div>

    <%@ include file="/include/common.jsp" %>

	<script type="text/javascript">

	// 레이어
	var themeList = {
	        "MV_CELL_SECT_TYPE"        : "Section셀_종류",
	        "MV_THM_YEAR_10"           : "10m셀_테마지도",
	        "MV_SRVYDTA_10_2018"       : "10m셀_포장상태_2018",
	        "MV_SRVYDTA_10_2017"       : "10m셀_포장상태_2017",
	        "MV_GNLSTTUS_SECT_2018"    : "Section셀_포장상태_2018",
	        "MV_GNLSTTUS_SECT_2017"    : "Section셀_포장상태_2017"
	};

	$(document).ready(function() {
	    parent.$("#thememap").parent().addClass("active");

	    // 지도 초기화
	    //parent.gMap.cleanMap();
	    parent.gMap.activeControls("drag");
	    parent.$("#map .left_tool li").removeClass("active");
	    parent.$("#mCtrlPan").parent().addClass("active");

	    // 하단 팝업 초기화
	    parent.bottomHide();

	    //--- 지도세팅 ----
	    fn_showMapLoading(true);

	    if ( parent.themeFlag == 0 ) {
	        // 테마지도 최초 로딩 시 themeLayer off 처리
	        parent.gMap.getLayerByName("themeLayer").setVisibility(true);
		    for ( var key in themeList ) {
		        var oNamedLayer = parent.MAP.LAYER.fn_find_sldNameLayer(key);
		        parent.MAP.LAYER.fn_toggle_allRuleWmsLayer('off',oNamedLayer);
		    }

		    // 교차로 on 처리
		    //var sLayerName = parent.MAP.LAYER.fn_get_EditEngLayerNm(themeList["MV_CELL_SECT_TYPE"]);
		    parent.MAP.LAYER.fn_toggle_wmsRule('on', "MV_CELL_SECT_TYPE", "교차로");
		    $("#MV_CELL_SECT_TYPE").find("li:eq(0) > span.smcircle").addClass("bgchk");
		    fnOnOffAction( $("#MV_CELL_SECT_TYPE").find("h4 > a > img") , 'on' );
		    fn_redrawMap();

		    parent.themeFlag++;

	    } else {
	        // 선택된 룰/레이어 check
	        var sld = parent.layerTool.sld.namedLayers;

	        // 레이어
	        for ( var i = 0; i < sld.length; i++ ) {
	            var name = sld[i].featureTypeName;

	            if  ( name == "MV_SRVYDTA_10_2018" || name == "MV_SRVYDTA_10_2017"
	                    || name == "MV_GNLSTTUS_SECT_2018" || name == "MV_GNLSTTUS_SECT_2017"
	                    || name == "MV_THM_YEAR_10" || name == "MV_CELL_SECT_TYPE" ) {
	                var style = sld[i].userStyle[0].rules;

	                // 룰
	                for ( var j = 0; j < style.length; j++ ) {
	                    // hidden이 false인 Rule의 버튼 check 처리
	                    if ( !style[j].hidden ) {
	                        $("#" + name).find("li:eq(" + j + ") > span.smcircle").addClass("bgchk");
	                        fnOnOffAction( $("#" + name).find("h4 > a > img") , 'on' );
	                    }
	                }
	            }
	        }
	    }


	    //181123 wijy : 노선목록 세팅
	    fn_setRouteList();

	    fn_showMapLoading(false);
	    //----- 지도세팅 END -------
	});

	// [ DEFAULT ] 팝업 크기 및 위치 변경
	$(parent).resize(function() {

	    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
	    var wndId = $("#wnd_id").val();
	    var wnd = parent.$.window.getWindow(wndId);
	    var ww = $(parent).width();
	    var wh = $(parent).height();

	    // 윈도우창
	    if ( ww > 1240 ) {
	        ww -= 15;
	    }

	    ww -= 405;
	    wnd.move(ww, 185);
	});

	//Map loading On/Off
	function fn_showMapLoading(_bOn) {
		if(_bOn) parent.$("#dvMapLoading").show();
		else parent.$("#dvMapLoading").hide();
	}

	$(function() {
		//레이어 On/Off 선택시
		$(".tLayer").on("click", function(e) {
			fn_showMapLoading(true);

			var id = $(this).parent().parent().attr("id");
	        var bOff = $(this).find("img").hasClass("off");

	        //이미지 변경
	        if(bOff)	$(this).parent().parent().find("li > span.smcircle").removeClass("bgchk");	// 선택 → 해제
	        else 		$(this).parent().parent().find("li > span.smcircle").addClass("bgchk");		// 해제 → 선택

	        //레이어 On/OFF처리
	        fn_setThemeLayerOnOff(bOff, id);
	        //레이어 reload
	        fn_redrawMap();

	        fn_showMapLoading(false);
		});

		//레이어 Rule On/Off
		$("li.tRule").on("click", function(e) {
			fn_showMapLoading(true);

			var id = $(this).parent().parent().parent().attr("id");
	        var sRuleName = "";
	        var sLayerName = "";

	        sRuleName = $(this).find("span").eq(1).text();

	        if ( id == "MV_THM_YEAR_10" ) {
	        	sRuleName = sRuleName.split(" ")[0] + "도";
	            sLayerName = "10m셀_테마지도";
	        } else {
	        	sLayerName = themeList[id];
	        }

	     	// 레이어 한글명 → 영문명 변경
	        sLayerName = parent.MAP.LAYER.fn_get_EditEngLayerNm(sLayerName);
	     	var bIsOn = $(this).find("span.smcircle").hasClass("bgchk");

	     	if(bIsOn) {
	     		// 교차로는 on/off 없이 계속 보이도록
	            if ( sRuleName == "교차로" ) {
	                $(this).find("span").eq(2).text("교차로는 구간 종류별 지도조회 off로만 off할 수 있습니다.");
	                fn_showMapLoading(false);
	                return;
	            }

	         	// 선택 → 해제
	            $(this).find("span.smcircle").removeClass("bgchk");

	         	// 전체가 해제 된 경우 off으로 변경
	            if ( $(this).parent().find("li > .bgchk").length ==  0 ) {
	                fnOnOffAction( $(this).parent().parent().parent().find("h4 > a > img") , 'off' );
	            }

	            parent.MAP.LAYER.fn_toggle_wmsRule('off',sLayerName,sRuleName);
	     	} else {
	     		// 해제 → 선택
	            $(this).find("span.smcircle").addClass("bgchk");
	            fnOnOffAction( $(this).parent().parent().parent().find("h4 > a > img") , 'on' );
	            parent.MAP.LAYER.fn_toggle_wmsRule('on',sLayerName,sRuleName);
	     	}
	     	//레이어 reload
	        fn_redrawMap();

	        fn_showMapLoading(false);
		});

		//노선 셀렉트박스 변경 이벤트
		$(".sel_routeInfo").on("change", cb_selRouteChange);

	});


	//노선 셀렉트박스 변경 callback
	function cb_selRouteChange(e) {
		fn_showMapLoading(true);
		var bIsOn = $(this).find("span.smcircle").hasClass("bgchk");

		//설정값 저장
		var sThisId = $(this).attr("id");
		var sThisVal = $(this).find("option:selected").val();

		//10m셀 대상인지 판단
		var bIs10m = (sThisId.indexOf("MV_SRVYDTA_10") > 0 ? true : false);

		//On된 레이어 확인
		var arrTargets = $(this).parent("div").siblings();
		var arrOnLyrs = [];
		for(var i = 0; i<arrTargets.length; i++) {
			var $l = $(arrTargets[i]);
			var isOff = $l.find("img.onoff").hasClass("off");
			if(!isOff) {
				arrOnLyrs.push($l.attr("id"));
			}
		}

		if(bIs10m) {
			//10m Cell
			parent.themeSelectValue[0] = sThisVal;		//flag 저장
		} else {
			//SectionCell
			parent.themeSelectValue[1] = sThisVal;		//flag 저장
		}

		//레이어 상태 변경
		for(var i in arrOnLyrs) {
			fn_setThemeLayerOnOff(false, arrOnLyrs[i]);
		}

		//레이어 reload
        fn_redrawMap();
        fn_showMapLoading(false);
	}


	//노선 selectbox 세팅
	function fn_setRouteList() {
		$.ajax({
			url : parent.contextPath + "api/routeinfo/selectRouteInfoListByGrad.do",
			type : "POST",
			dataType : "json",
			data : JSON.stringify({}),
			contentType : "application/json",
			success : cb_setRouteList,
			error : function(a,b,c) {
				//err
			}
		});
	}

	//노선 selectbox 세팅 callback
	function cb_setRouteList(data) {
		if(data != null && data.length > 0) {
			var sHtml = "<option value=''>전체</option>";

			for(var i in data) {
				var oData = data[i];
				sHtml += "<option value='" + oData.ROAD_NO + "'>[" + oData.ROAD_NO + "] " + oData.ROAD_NAME + "</option>";
			}
			//TODO html 세팅
			$(".sel_routeInfo").html(sHtml);
		} else {
			//err
		}

		//selectbox Setting
        for(var i in parent.themeSelectValue) {
        	var sThisId = parent.themeSelectValue[i];
        	if(sThisId != "") {
        		$(".sel_routeInfo").eq(i).find("option[value='" + sThisId + "']").prop("selected", true);
        	}
        }
	}


	//레이어 On/OFF처리
	// param0 : _bOff (Off상태인 경우 true),
	// param1 : _layerId (레이어Id)
	function fn_setThemeLayerOnOff(_bOff, _layerId) {
		var sLayerName = themeList[_layerId];
     	// 레이어 한글명 → 영문명 변경
     	var sLayerNmEng = parent.MAP.LAYER.fn_get_EditEngLayerNm(sLayerName);
        var oNamedLayer = parent.MAP.LAYER.fn_find_sldNameLayer(sLayerNmEng);
        var oTargetLyr = parent.gMap.getLayerByName("themeLayer");

        parent.MAP.LAYER.fn_toggle_allRuleWmsLayer((_bOff ? "off" : "on"),oNamedLayer);

        /*
        노선선택이 들어간 경우

        "MV_SRVYDTA_10_2018"       : "10m셀_포장상태_2018",
        "MV_SRVYDTA_10_2017"       : "10m셀_포장상태_2017",
        "MV_GNLSTTUS_SECT_2018"    : "Section셀_포장상태_2018",
        "MV_GNLSTTUS_SECT_2017"    : "Section셀_포장상태_2017"
        */
        if(!_bOff) {
        	if(	sLayerNmEng == "MV_SRVYDTA_10_2018" ||
               	sLayerNmEng == "MV_SRVYDTA_10_2017" ||
               	sLayerNmEng == "MV_GNLSTTUS_SECT_2018" ||
               	sLayerNmEng == "MV_GNLSTTUS_SECT_2017") {
               	var sSeElPrefix="selRoute_";
               	var sTargetSelElType = sLayerNmEng.substring(0, sLayerNmEng.lastIndexOf("_"));

               	var sRouteCode = $("select[id='" + sSeElPrefix + sTargetSelElType + "'] option:selected").val();
       	        var sSldBody = new XMLSerializer().serializeToString(parent.layerTool.sld.xml.cloneNode(true));
       	        var xml = new OpenLayers.Format.XML();
       	        var oSldJson = xml2js(sSldBody);

       	        var oRetSld = fn_setRouteCodeToSld(oSldJson, sLayerNmEng, {routeCode : sRouteCode});
       	        sSldBody = js2xml(oRetSld);
       	        var sldObj = xml.read(sSldBody);

       	        var sThemeList = parent.layerTool.getThemeShowList("", "themeLayer").join();
       	        var oBefSld = parent.layerTool.getSld();
       	        var oChgSld = parent.GRequest.WMS.parseGetStyles(sldObj);

       	   		//해당 레이어 정보만 변경함
       	        var oChgNamedLyr = {};
       	        for(var i in oChgSld.namedLayers) {
       	        	var oChgLyr = oChgSld.namedLayers[i];
       	        	if(oChgLyr.name == sLayerNmEng) {
       	        		oChgNamedLyr = oChgLyr;
       	        	}
       	        }
       	        for(var i in oChgNamedLyr.userStyle[0].rules) {
       	        	oChgNamedLyr.userStyle[0].rules[i]["hidden"] = _bOff;
       	        }

       	        for(var i in oBefSld.namedLayers) {
       	        	var oBefLyr = oBefSld.namedLayers[i];
       	        	if(oBefLyr.name == sLayerNmEng) {
       	        		oBefSld.namedLayers[i] = oChgNamedLyr;
       	        	}
       	        }

       	        oBefSld.xml = sldObj;
       	        parent.layerTool.setSld(oBefSld);

       	        oTargetLyr.mergeNewParams({
       				layers : sThemeList,
       				styles : sThemeList,
       				sld_body : sSldBody
       			});
			}
        }
	}

	//redrawMap
	function fn_redrawMap() {
		// 레이어 다시 그리기
        parent.MAP.LAYER.fn_redraw_wms("themeLayer");
	}


	//노선번호 적용된 sldbody 생성
	function fn_setRouteCodeToSld(_sldJson, _targetLyr, _param) {

		var arrEle = _sldJson.elements;
		var sNm = _sldJson.name;
		if(typeof sNm == "undefined" || sNm == null) sNm = "";

		for(var i in arrEle) {
			var ele = arrEle[i];
			ele = fn_serSldJson(ele, _targetLyr, _param);
		}
		return _sldJson;
	}


	//트리 탐색해서 sld변경
	function fn_serSldJson(_sldJson, _targetLyr, _param) {
		var arrEle = _sldJson.elements;
		var sNm = _sldJson.name;

		var sValue = _param.routeCode;
		if(sValue == null || sValue == "") {
			sValue = "*";
		} else {
			sValue = parent.GUtil.fn_set_lpad(_param.routeCode, 4, "0");
		}


		if(sNm == "sld:NamedLayer" || sNm == "NamedLayer") {
			var sLyrNm = arrEle[0].elements[0].text;
			if(sLyrNm == _targetLyr) {
				var oStyle = arrEle[1];
				var oFeatStyle = oStyle.elements[2];

				var arrTarget = oFeatStyle.elements;
				for(var j in arrTarget) {
					var arrEl = arrTarget[j].elements;
					for(var k in arrEl) {
						var thisEl = arrEl[k];
						var sElNm = thisEl.name;
						if(sElNm == "ogc:Filter") {
							var oQ1 = thisEl.elements;
							if(oQ1[0].name == "ogc:And" || oQ1[0].name == "And") {
								var isPropertyIsLike = false;
								var arrQ = oQ1[0].elements;
								for(var i in arrQ) {
									var oThisQ = arrQ[i];
									if(oThisQ.name == "ogc:PropertyIsLike" || oThisQ.name == "PropertyIsLike") {
										for(var j in oThisQ.elements) {
											if(oThisQ.elements[j].name == "ogc:Literal" || oThisQ.elements[j].name == "Literal") {
												oThisQ.elements[j].elements = [{
													type : "text",
													text : sValue
												}];
											}
										}
										
										isPropertyIsLike = true;
									}
								}
								
								if(!isPropertyIsLike){
									arrQ.push({
										elements : [{
											elements : [{type : "text", text : "route_code"}],
											name : "ogc:PropertyName",
											type : "element"
										}, {
											elements : [{type : "text", text : sValue}],
											name : "ogc:Literal",
											type : "element"
										}],
										name : "ogc:PropertyIsLike",
										type : "element"
									});
								}
							} else {
								var oSetParam = {
									elements : oQ1,
									type : "element",
									name : "ogc:And"
								};

								oSetParam.elements.push({
									elements : [{
										elements : [{type : "text", text : "route_code"}],
										name : "ogc:PropertyName",
										type : "element"
									}, {
										elements : [{type : "text", text : sValue}],
										name : "ogc:Literal",
										type : "element"
									}],
									name : "ogc:PropertyIsLike",
									type : "element"
								});
								thisEl.elements = [oSetParam];
							}
						}
					}
				}
			}
		} else {
			for(var i in arrEle) {
				arrEle[i] = fn_serSldJson(arrEle[i], _targetLyr, _param);
			}
		}
		return _sldJson;
	}

	</script>
</body>
</html>
