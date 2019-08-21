<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- <title>지도 이미지 저장 </title> -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

var baseLayerOn = 0;
var themeLayerOn = 0;
var GAttrLayerOn = 0;

//페이지 로딩 초기 설정
$( document ).ready(function() {


    parent.$("#mCtrlPrint").parent().siblings().removeClass("active");
    parent.$("#mCtrlPrint").parent().addClass("active");

});

// 지도 저장 툴
var gSaveTool = null;
gSaveTool = new GSaveTool(parent.gMap);

function fn_imgSave(){

	var imgSubmit = function(callback) {

		$("#imgMap_frm").find("#data").val(encodeURIComponent(fn_encodingMap()));
		$("#imgMap_frm").attr("action", '<c:url value="/"/>'+"saveMapImage.do");
		$("#imgMap_frm").submit();

		callback("succ");
	}

	 imgSubmit(function(result) {
		//이미지 submit 성공시 창 닫기
		if(result == "succ"){
			window.setTimeout("fn_close()", 4000);
		}
	})
}

function fn_close(){
	COMMON_UTIL.cmWindowClose($("#wnd_id").val());

}

function fn_encodingMap(){

	// ============ 인쇄/저장시 배경 레이어 띄움 ============
    parent.gMap.getLayerByName("backgroundLayer").setVisibility(true);

    if ( parent.gMap.getLayerByName("baseLayer").visibility == true ){
        parent.gMap.getLayerByName("baseLayer").setVisibility(false);
        baseLayerOn = 1;
    }
    if ( parent.gMap.getLayerByName("themeLayer").visibility == true ){
        parent.gMap.getLayerByName("themeLayer").setVisibility(false);
        themeLayerOn = 1;
    }
    if ( parent.gMap.getLayerByName("GAttrLayer").visibility == true ){
        parent.gMap.getLayerByName("GAttrLayer").setVisibility(false);
        GAttrLayerOn = 1;
    }

	var gData = null;
/*
	var oTempDiv = parent.document.createElement("div");
	oTempDiv.style.visibility = "hidden";
	oTempDiv.style.height = $(parent.gMap.div).height() + "px";
	oTempDiv.style.width = $(parent.gMap.div).width() + "px";

	var oTempMap = parent.daumMap.getStaticMap(oTempDiv);

	if($("#mapType").val() == "skyView"){
		oTempMap.setMapTypeId(parent.daum.maps.MapTypeId.SKYVIEW);
	}

	sTileMapUrl = $(oTempDiv).find("img").attr("src");
	sTileMapUrl = sTileMapUrl.replace("IW=0", "IW=" + $(parent.gMap.div).width());
	sTileMapUrl = sTileMapUrl.replace("IH=0", "IH=" + $(parent.gMap.div).height());
	$(oTempMap).remove();
 */
	gData = gSaveTool.getXML({
		/*
		type : 'daum',
		url : sTileMapUrl
		 */
	});


    // ============ 지도인쇄/저장 후 배경 레이어 지움 ============
    parent.gMap.getLayerByName("backgroundLayer").setVisibility(false);
    if (baseLayerOn == 1 ){
        parent.gMap.getLayerByName("baseLayer").setVisibility(true);
    }
    if (themeLayerOn == 1 ){
        parent.gMap.getLayerByName("themeLayer").setVisibility(true);
    }
    if (GAttrLayerOn == 1 ){
        parent.gMap.getLayerByName("GAttrLayer").setVisibility(true);
    }

	return gData;
}

$(parent).resize(function() {

    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();

    ww = ( ww / 2 ) - 175;
    wh = ( wh / 2 ) - 50;

    wnd.move(ww, wh);

});

</script>
</head>
<body id="wrap" class="left-tool saveImgMap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="mapType" name="mapType" value="${param.mapType }"/>

<form id="imgMap_frm" name="imgMap_frm" method="post" action="">
<div class="content" style='position: fixed;' >
 <div style="float: left; font-size: 13px; line-height: 30px; font-weight:bold;">파일명 :&nbsp</div>
    <div class="tsch" style="float: left; width: 200px;">
       <input type="text" id="imgNm" name="imgNm" onkeypress="if(event.keyCode==13) {fn_imgSave(); return false;}" style='width: 96%;'/>
       <input type="hidden" id="data" name="data" value=""/>
    </div>
     <a href="#" class="schbtn ml5" onclick="fn_imgSave();" style="height:23px;line-height:23px;width:50px">저장</a>
</div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</form>
</body>
</html>