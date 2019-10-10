<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>경기도 포장관리시스템</title>

<%-- 공통 스크립트 --%>
<%@ include file="/include/common_head.jsp"%>

<!-- 2017. 10. 23. JOY : 조사정보 조회 스크립트 -->
<script type="text/javascript" src="<c:url value='/js/toolRight/researchInfo.js'/>" charset='utf-8'></script>

<!-- JSTREE -->
<script src="<c:url value='/extLib/jstree/jstree.min.js'/>" charset='utf-8'></script>

</head>

<body onload="fn_unCheckLabel();">
	<div id="wrap">
		<!-- 공통 (START)-->
		<%@ include file="/include/topMenu_iasp.jsp"%>
		<!-- 공통 (END)-->
		<!-- container start -->
		<div id="container">
			<h2 class="hidden">본문컨텐츠-지도</h2>
			<h2 class="hidden">메인메뉴</h2>
			<div id="bottom">
                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseBt" onclick="bottomClose()"><img src="images/btn_pleftclose.png" alt="닫기" title="닫기" /></a>
                        <a href="#" class="hidden" id="leftOpenBt" onclick="bottomOpen()"><img src="images/btn_pleftopen.png" alt="열기" title="열기" /></a>
                    </div>
                    <div id="btab01">
                    	<%-- 왼쪽 세부 메뉴 --%>
						<%@ include file="/include/leftMenu.jsp"%>
                        <div>
                         	<div class="tab_wrap ">
                        		<iframe id="content_area" name="content_area" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border:0; width:100%; height:560px; overflow: hidden; z-index:9999;" scrolling=no></iframe>
                        	</div>
                        </div>
                    </div>
                    <div id="detail_integrated" style="display: none;">
                    	<%-- 왼쪽 세부 메뉴 --%>
						<div style="width: 140px; float: left;">
					       <h2>포장상태 평가(종합)</h2>
					       <ul class="btab_menu">
					           <li style="height:291px"><a href="#" class="tab1">평가자료<br>상세조회</a></li>
					           <!-- <li style="height:145px"><a href="#" class="tab2">평가자료<br>다운로드</a></li> -->
					       </ul>
					    </div>
                        <div>
                         	<div class="tab_wrap">
                        		<iframe id="integrated_area" name="integrated_area" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border:0; width:100%; height:560px; overflow: hidden; z-index:9999;" scrolling=no></iframe>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="stats">
                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseSt" onclick="statsClose()"><img src="images/btn_pleftclose.png" alt="닫기" title="닫기"/></a>
                        <a href="#" class="hidden" id="leftOpenSt" onclick="statsOpen()"><img src="images/btn_pleftopen.png" alt="열기" title="열기"/></a>
                    </div>
                    <div id="btab01">
                    	<%-- 왼쪽 세부 메뉴 --%>
						<%@ include file="/include/stats/common_cntrwk.jsp" %>
                        <div>
                         	<div class="tab_wrap ">
                        		<iframe id="content_stArea" name="content_stArea" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border:0; width:100%; height:850px; overflow: hidden; z-index:9999;" scrolling=no></iframe>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="repairtargets" style="display:none;">

                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseRt" onclick="javascript:repairtargetsHideBottom()"><img src="images/btn_pleftclose.png" alt="닫기" title="닫기"/></a>
                        <a href="#" class="hidden" id="leftOpenRt" onclick="javascript:repairtargetsShow()"><img src="images/btn_pleftopen.png" alt="열기" title="열기"/></a>
                    </div>
                    <div id="btab01">
                    	<%-- 왼쪽 세부 메뉴 --%>보수대상선정
						<%@ include file="/WEB-INF/jsp/repairtarget/common_repairtarget.jsp" %>
                        <div style="margin-top: -14px;" >
                         	<div class="rtab_wrap ">
                        		<iframe id="content_repairtargets" name="content_repairtargets" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border:0; width:100%; height:860px; overflow: hidden; z-index:9999;" scrolling=no></iframe>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="unptcSenario" style="display:none;">
            	<div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseUs" onclick="unptcSenarioHideBtn()"><img src="images/btn_pleftclose.png" alt="닫기" title="닫기"/></a>
                        <a href="#" class="hidden" id="leftOpenUs" onclick="unptcSenarioShow()"><img src="images/btn_pleftopen.png" alt="열기" title="열기"/></a>
                    </div>
                    <div id="btab01">
                    	<iframe id="content_unptcSenario" name="content_unptcSenario" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border:0; width:100%; height:850px; overflow: hidden; z-index:9999;" scrolling=no></iframe>
                    </div>
                </div>
            </div>
			<div id="daumMap"></div>
			<div id="map">
				<ul class="left_tool">
					<!-- <li><a href="#" class="t1 maptool" id="mCtrlIdentify"><span class="hidden">셀속성조회</span></a></li> -->
					<li><a href="#" class="t1 maptool" id="mCtrlLocSearch_iasp" title="위치 통합검색"><span class="hidden">위치통합검색</span></a></li>
                    <li><a href="#" class="t2 maptool" id="mCtrlLonLatMove" title="경위도 좌표 이동"><span class="hidden">경위도 좌표 이동</span></a></li>
                    <li class="active"><a href="#" class="t3 maptool" id="mCtrlPan" title="이동"><span class="hidden">이동</span></a></li>
                    <li><a href="#" class="t4 maptool" id="mCtrlZoomIn" title="확대"><span class="hidden">확대</span></a></li>
                    <li><a href="#" class="t5 maptool" id="mCtrlZoomOut" title="축소"><span class="hidden">축소</span></a></li>
                    <li><a href="#" class="t6 maptool" id="mCtrlPrev" title="이전"><span class="hidden">이전</span></a></li>
                    <li><a href="#" class="t7 maptool" id="mCtrlNext" title="다음"><span class="hidden">다음</span></a></li>
                    <li><a href="#" class="t8 maptool" id="mCtrlFullExt" title="전체"><span class="hidden">전체</span></a></li>
                    <li><a href="#" class="t9 maptool" id="mCtrlMesureLength" title="거리"><span class="hidden">거리</span></a></li>
                    <li><a href="#" class="t10 maptool" id="mCtrlMesureArea" title="면적"><span class="hidden">면적</span></a></li>
                    <li><a href="#" class="t11 maptool" id="mCtrlClear" title="초기화"><span class="hidden">초기화</span></a></li>
                    <li><a href="#" class="t12 maptool" id="mCtrlMapPrint" title="현재화면 인쇄"><span class="hidden">인쇄</span></a></li>
                    <li><a href="#" class="t13 maptool" id="mCtrlPrint" title="현재화면 저장"><span class="hidden">저장</span></a></li>
                </ul>

                <ul class="right_tool">
                    <li><a id="integrated" href="#" class="t1 selecttool" title="통합 정보 조회" onclick="COMMON_UTIL.cmWindowOpen('통합 정보 조회', contextPath + 'topmenu/selectIntegratedView.do', 623, 215, false, null, 'integrated');"><span class="hidden">정보통합조회</span></a></li>
                    <li><a id="researchInfo" href="#" class="t2 selecttool" title="조사정보조회"><span class="hidden">조사정보조회</span></a></li>
                    <li><a id="editCellInfo" href="#" class="t3 selecttool" title="셀 속성 편집" onclick="COMMON_UTIL.cmWindowOpen('셀 속성 편집', contextPath + 'cellsect/selectCellSectUpdate.do', 623, 270, false, null, 'updCell');"><span class="hidden">셀속성편집</span></a></li>
                    <li><a id="thememap" href="#" class="t4 selecttool" title="테마지도" onclick="COMMON_UTIL.cmWindowOpen('테마지도', contextPath + 'topmenu/selectThemeMap.do', 400, 730, false, null, 'thememap');"><span class="hidden">테마지도</span></a></li>
                </ul>

                <ul class="select_map">
                    <li id="mCtrlSatenomalMap" class="selected_btn" onclick="MAP.fn_show_externalSateliteMap('roadmap')"><a href="#" class="map1"><span class="hidden">일반</span></a></li>
                    <li id="mCtrlSateliteMap"  class="btn" onclick="MAP.fn_show_externalSateliteMap('skyview')"><a href="#" class="map2"><span class="hidden">위성</span></a></li>
                </ul>

                <!-- <div class="custom_typecontrol radius_border" style="z-index:999;">
			        <span id="mCtrlSatenomalMap" class="selected_btn" onclick="MAP.fn_show_externalSateliteMap('roadmap')">일반</span>
			        <span id="mCtrlSateliteMap"  class="btn" onclick="MAP.fn_show_externalSateliteMap('skyview')">위성</span>
			    </div> -->

                <dl id="snbacc">
                    <dt>인덱스맵</dt>
                    <dd>
                        <!-- <p class="locationtxt">위치 확인 중...</p> -->
                        <div class="indexmap" style="height: 230px; ">
                        	<div class="inmapBx2" id="dvIndexMap"></div>
                        	<div class="inmapBx3">
								축척 1 : <input type="text" id="scale" onclick="this.select();" />
								<p style="font-size: 10px;">일반지도는 유사축척으로 변경됨</p>
								<p style="margin-top: 5px; font-size: 11px; color: black;">
									<label id="lblLonlat1"></label><br /> <label id="lblLonlat2"></label><br />
									<label id="lblXY"></label>
								</p>
							</div>
                        </div>
                    </dd>
                    <dt>레이어관리</dt>
                    <dd>
						<div class="inBx" id="toolArea">
							<div id="dvLayerList" class="LayerList">
							</div>
						</div>
                    </dd>
                </dl>
			</div>


			<%-- 팝업 관련 --%>
         	<%@include file="/include/gis/mapPopupUI.jsp" %>
			<!-- 공통 (START)-->
			<%@ include file="/include/common.jsp"%>
			<!-- 공통 (END)-->

		</div>
		<!-- container end -->
	</div>
	<!-- wrap end -->
</body>

<script type="text/javascript" charset="utf-8">

$("#mCtrlLocSearch_iasp").bind("click", function(){
	
	COMMON_UTIL.cmWindowOpenIasp('위치검색', contextPath + 'gmap/selectLocation_iasp.do',519, 262, false, null, 'locsearch');

});

$(parent).resize(function() {

    var width = $(window).width();

    $(".select_map").css("left", ( width - 188 - 108 ) + "px");
    $(".right_tool").css("left", ( width - 11 - 180 ) + "px");
    $("#snbacc").css("left", ( width - 20 - 210 ) + "px");

});

$(document).ready(function() {
    var width = $(window).width();

    $(".select_map").css("left", ( width - 188 - 108 ) + "px");
    $(".right_tool").css("left", ( width - 11 - 180 ) + "px");
    $("#snbacc").css("left", ( width - 20 - 210 ) + "px");

	MAP.fn_update_resizeMap();

	$(window).resize(function () {
        MAP.fn_update_resizeMap();
    });

	//하단 메뉴 hide
	bottomHide();

});

//주석 레이어 해제
function fn_unCheckLabel(){
	$("#dvLayerList").find("li[id$='_text'] a ins.jstree-checkbox").click();


}

var MAIN = (function(_mod_map, $, undefined) {

	//레이어 관리 목록 조회
	var	oLayerInfoList,
	oLayerInfoListTheme,
	oTmapInfoList,
	oTmapGroupInfoList,
	oEditLayerInfoList,
	oLayerGroups;

	/* oLayerInfoListTheme = {
	        <c:forEach items="${layerInfoList}" var="layerInfo" varStatus="status">
		        "${layerInfo.table}" : {
		            <c:forEach items="${layerInfo}" var="layer" >
		            '${layer.key}' : '${layer.value}',</c:forEach>
				},
		    </c:forEach>
	}; */
/* 
	oLayerInfoList = {
            <c:forEach items="${layerInfoListTheme}" var="layerInfo" varStatus="status">
                "${layerInfo.table}" : {
                    <c:forEach items="${layerInfo}" var="layer" >
                    '${layer.key}' : '${layer.value}',</c:forEach>
                },
            </c:forEach>
    };
 */
 	
 	oLayerInfoList = CONFIG.fn_get_serviceLayerInfo();
 
	/* oLayerGroups =
		[<c:forEach items="${layerGroupInfoList}" var="layerGroupInfo">
			{<c:forEach items="${layerGroupInfo}" var="layerGroup" >
				'${layerGroup.key}' : '${layerGroup.value}',</c:forEach>
			},
		</c:forEach>
		]; */

	layerTool = new GTMapLayerTool(oLayerInfoList, oTmapInfoList, oTmapGroupInfoList, oLayerGroups, {
		tMapId : 1,
		serviceUrl : CONFIG.fn_get_serviceUrl(),	// GeoGate 주소
		callback : function(_oRes, _bUserStyle){
			MAP.fn_init_map(_oRes, _bUserStyle);
		},
		userStyle : ''
	});

	var fn_get_layerInfoList = function (_oLayer){
		return oLayerInfoList[_oLayer];
	}

	var fn_get_layerTotInfoList = function (){
		return oLayerInfoList;
	}

	/* var fn_get_layerInfoListTheme = function (_oLayer){
        return oLayerInfoListTheme[_oLayer];
    }

	var fn_get_layerTotInfoListTheme = function (){
        return oLayerInfoListTheme;
    } */

	//------------------------------------------------------------------------------------------------------------------
	//## public 메소드
	//------------------------------------------MAP_EDITOR.fn_init_timeLine(); ------------------------------------------------------------------------
	_mod_map.fn_get_layerInfoList			=	fn_get_layerInfoList;
	_mod_map.fn_get_layerTotInfoList		=	fn_get_layerTotInfoList;
	/* _mod_map.fn_get_layerInfoListTheme          =   fn_get_layerInfoListTheme;
    _mod_map.fn_get_layerTotInfoListTheme        =   fn_get_layerTotInfoListTheme; */
	//------------------------------------------------------------------------------------------------------------------

	return _mod_map;

}(MAIN || {}, jQuery));

</script>
</html>