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
<script type="text/javascript" src="<c:url value='/js/toolRight/researchInfo.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/jstree/jstree.min.js'/>" charset='utf-8'></script>
<%
    String flag = "gpms";
    session.setAttribute("sFlag", flag);
%>
</head>
<body>
	<div id="wrap">
		<!-- 공통 (START)-->
		<%@ include file="/include/topMenu.jsp"%>
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
<%-- 						<%@ include file="/include/leftMenu.jsp"%> --%>
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
            
            <header class="loc">
		        <div class="container">
		            <span class="locationHeader">
		                <select name="">
		                    <option value="">보수대상선정</option>
		                </select>
		                <select name="">
		                    <option value="">보수대상선정</option>
		                </select>
		                <!-- <h2 class="h2">조사자료 등록 대상목록</h2> -->
		            </span>		
		        </div>
		    </header>

                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseRt" onclick="javascript:repairtargetsHideBottom()"><img src="images/btn_pleftclose.png" alt="닫기" title="닫기"/></a>
                        <a href="#" class="hidden" id="leftOpenRt" onclick="javascript:repairtargetsShow()"><img src="images/btn_pleftopen.png" alt="열기" title="열기"/></a>
                    </div>
                    <div id="btab01">
                    	<%-- 왼쪽 세부 메뉴 --%>보수대상선정
						<c:import url="/rpairtrgetslctn/common_repairtarget.do" />
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
				<ul class="mapTool">
                    <!-- <li><a href="#" class="t1 maptool" id="mCtrlIdentify"><span class="hidden">셀속성조회</span></a></li> -->
                    <li><a href="#" class="mtBtn sideDiv locSearch" id="mCtrlLocSearch" title="위치 통합검색">위치검색</a></li>
                    <li><a href="#" class="mtBtn sideDiv position" id="mCtrlLonLatMove" title="경위도 좌표 이동">좌표이동</a></li>
                    <li class="active"><a href="#" class="mtBtn move" id="mCtrlPan" title="이동">이동</a></li>
                    <li><a href="#" class="mtBtn zoomIn" id="mCtrlZoomIn" title="확대">확대</a></li>
                    <li><a href="#" class="mtBtn zoomOut" id="mCtrlZoomOut" title="축소">축소</a></li>
                    <!--
                    <li><a href="#" class="mtBtn before" id="mCtrlPrev" title="이전">이전</a></li>
                    <li><a href="#" class="mtBtn next" id="mCtrlNext" title="다음">다음</a></li>
                     -->
                    <li><a href="#" class="mtBtn viewAll" id="mCtrlFullExt" title="전체보기">전체</a></li>
                    <li><a href="#" class="mtBtn distance" id="mCtrlMesureLength" title="거리재기">거리</a></li>
                    <li><a href="#" class="mtBtn partView" id="mCtrlMesureArea" title="면적">면적</a></li>
                    <li><a href="#" class="mtBtn refresh" id="mCtrlClear" title="초기화">초기화</a></li>
                    <!--
                    <li><a href="#" class="mtBtn print" id="mCtrlMapPrint" title="현재화면 프린트">프린트</a></li>
                    <li><a href="#" class="mtBtn save" id="mCtrlPrint" title="현재화면 저장">저장</a></li>
                    -->
                </ul>

				<!--
                <ul class="right_tool">
                    <li><a id="integrated" href="#" class="t1 selecttool" title="통합 정보 조회" onclick="COMMON_UTIL.cmWindowOpen('통합 정보 조회', contextPath + 'topmenu/selectIntegratedView.do', 623, 215, false, null, 'integrated');"><span class="hidden">정보통합조회</span></a></li>
                    <li><a id="researchInfo" href="#" class="t2 selecttool" title="조사정보조회"><span class="hidden">조사정보조회</span></a></li>
                    <li><a id="editCellInfo" href="#" class="t3 selecttool" title="셀 속성 편집" onclick="COMMON_UTIL.cmWindowOpen('셀 속성 편집', contextPath + 'cellsect/selectCellSectUpdate.do', 623, 270, false, null, 'updCell');"><span class="hidden">셀속성편집</span></a></li>
                    <li><a id="thememap" href="#" class="t4 selecttool" title="PMS 테마지도" onclick="COMMON_UTIL.cmWindowOpen('PMS 테마지도', contextPath + 'topmenu/selectThemeMap.do', 400, 730, false, null, 'thememap');"><span class="hidden">PMS 테마지도</span></a></li>
                </ul>

                <ul class="select_map">
                    <li id="mCtrlSatenomalMap" class="selected_btn" onclick="MAP.fn_show_externalSateliteMap('roadmap')"><a href="#" class="map1"><span class="hidden">일반</span></a></li>
                    <li id="mCtrlSateliteMap" onclick="MAP.fn_show_externalSateliteMap('skyview')"><a href="#" class="map2"><span class="hidden">위성</span></a></li>
                </ul>
                -->

                <nav class="mapTool2">
	                <div class="group toggleBtn">
	                    <article>
	                        <button class="mt2Btn generalMap on" id="mCtrlSatenomalMap" onclick="MAP.fn_show_externalSateliteMap('roadmap')">일반지도</button>
	                    </article>
	                    <article>
	                        <button class="mt2Btn satelliteMap" id="mCtrlSateliteMap" onclick="MAP.fn_show_externalSateliteMap('skyview')">위성사진</button>
	                    </article>
	                </div>
	                <div class="group">
	                    <article>
	                        <a href="#totalSearch" id="integrated" class="mt2Btn sideDiv totalSearch" onclick="COMMON_UTIL.cmWindowOpen('통합 정보 조회', contextPath + 'topmenu/selectIntegratedView.do', 623, 215, false, null, 'integrated');">통합조회</a>
	                    </article>
	                    </article>
	                    <article>
	                        <button class="mt2Btn searchInfo" id="researchInfo">조사정보</button>
	                    </article>
	                    <article>
	                        <button class="mt2Btn cellEdit" id="editCellInfo" onclick="COMMON_UTIL.cmWindowOpen('셀 속성 편집', contextPath + 'cellsect/selectCellSectUpdate.do', 623, 270, false, null, 'updCell');" title="셀 속성 편집">셀편집</button>
	                    </article>
	                    <article>
	                        <button class="mt2Btn themeMap" id="thememap" onclick="COMMON_UTIL.cmWindowOpen('PMS 테마지도', contextPath + 'topmenu/selectThemeMap.do', 400, 730, false, null, 'thememap');" title="PMS 테마지도">테마지도</button>
	                    </article>
	                </div>
            	</nav>



                <!-- indexMapWrap S -->
	            <nav class="indexMapWrap">
	                <article class="indexMap">
	                    <button>인덱스맵</button>
	                    <div class="ctn">
		                    <div id="dvIndexMap"></div>
		                      	<div class="inmapBx3">
								축척 1 : <input type="text" id="scale" onclick="this.select();" />
								<p>일반지도는 유사축척으로 변경됨</p>
								<p>
									<label id="lblLonlat1"></label><br />
									<label id="lblLonlat2"></label><br />
									<label id="lblXY"></label>
								</p>
							</div>
						</div>
	                </article>
	                <!--
	                <article class="layerMng">
	                    <button>레이어관리</button>
	                    <div class="ctn" id="toolArea">
	                    	<div id="dvLayerList" class="LayerList">
	                    </div>
	                </article>
	                -->
	            </nav>
            	<!-- indexMapWrap E -->

				<!-- jstree
                <div id="divLayerTool" >
	                <div id="dvLayerList" class="LayerList">
	                </div>
                </div>
                 -->

                <div id="divLayerTool">
	                <div id="divLayerMngList" class="divlayermng">
	                	<ul id="divLayerMngList_ul">
	                	</ul>
	                </div>
                </div>

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
$(parent).resize(function() {
    var width = $(window).width();
    $(".select_map").css("left", ( width - 188 - 108 ) + "px");
    $(".right_tool").css("left", ( width - 11 - 180 ) + "px");
    //$("#snbacc").css("left", ( width - 20 - 210 ) + "px");
});

$(document).ready(function() {
    "${sessionScope.sFlat}";

    var width = $(window).width();
    $(".select_map").css("left", ( width - 188 - 108 ) + "px");
    $(".right_tool").css("left", ( width - 11 - 180 ) + "px");
    //$("#snbacc").css("left", ( width - 20 - 210 ) + "px");

	MAP.fn_update_resizeMap();
	$(window).resize(function () {
        MAP.fn_update_resizeMap();
    });

	//하단 메뉴 hide
	bottomHide();

});

var MAIN = (function(_mod_map, $, undefined) {

	//레이어 관리 목록 조회
	var oTmapInfoList, oTmapGroupInfoList, oLayerGroups;
 	var oLayerInfoList = CONFIG.fn_get_serviceLayerInfo();
	layerTool = new GTMapLayerTool(oLayerInfoList, oTmapInfoList, oTmapGroupInfoList, oLayerGroups, {
		tMapId : 1,
		serviceUrl : CONFIG.fn_get_serviceUrl(),
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

	_mod_map.fn_get_layerInfoList			=	fn_get_layerInfoList;
	_mod_map.fn_get_layerTotInfoList		=	fn_get_layerTotInfoList;

	return _mod_map;

}(MAIN || {}, jQuery));


//레이어 목록 만들기
var layersMngObj = {
	init: function(){
		var layerinfos = CONFIG.fn_get_serviceLayerInfo();
		var sThemeList = layerTool.getThemeShowList();
		var layers = [];
		for(var i=0; i<sThemeList.length; i++){
			var layername = sThemeList[i];
			layers.push(layerinfos[layername]);
		}

		//정렬
		function sort(a, b) {
			if (a.seq == b.seq) { return 0 }
			return a.seq > b.seq ? 1 : -1;
		}
		layers.sort(sort);

		var templates = [];
		for(var i=0; i<layers.length; i++){
			var json = layers[i];
			var table = json.table;
			var alias = json.alias;
			var show = json.show;
			var active = (show == '1') ? 'active' : '';

			var format =
				this.template().replace(/{title}/g, alias)
					.replace(/{layernm}/g, table)
					.replace(/{class}/g, active);

			templates.push(format);
		}
		$('#divLayerMngList_ul').html(templates.join(""));
		this.clickevt();
	}
	,template : function() {
		var template = ''
				+ '<li class="{class}">'
				+ '<a href="#"><p data-layer="{layernm}">{title}</p></a>'
				+ '</li>';
		return template;
	}
	,clickevt: function(){
		var fnCall = function(){
			var obj = $(this);
			var layernm = obj.find('p').attr('data-layer');
			var baseLayer = gMap.getLayerByName("baseLayer");
			var params = baseLayer.getParams();
			var layers = (params.LAYERS) ? params.LAYERS.split(',') : [];

			if(obj.hasClass('active')){
				obj.removeClass('active');
				if(layers.indexOf(layernm) > -1){
					if(layers.length == 1 ) layers = [];
					else layers.splice(layers.indexOf(layernm), 1)
				}
			}else{
				obj.addClass('active');
				if(layers.indexOf(layernm) == -1){
					layers.push(layernm);
				}
			}

			if(layers.length == 0) baseLayer.setVisibility(false);
			else baseLayer.setVisibility(true);
			baseLayer.mergeNewParams({
				LAYERS: layers.join()
				,STYLES: ''
			});

		};
		$('#divLayerMngList_ul').find('li').click(fnCall);
	}
};
layersMngObj.init();


</script>
</html>