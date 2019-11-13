﻿<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- <title>출력 미리보기 </title> -->
<%@ include file="/include/common_head.jsp" %>

</head>
<body id="wrap" class="left-tool mapPrint">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="imgUrl" name="imgUrl" value="${imgUrl}"/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="mapType" name="mapType" value="${param.mapType }"/>

<div class="content" >
        <div id="print_content" >
            <div class="af">
                <table class="tbview fl" summary="정보를 제공합니다." style="width:92%">
                    <caption>정보</caption>
                    <colgroup>
                        <col width="15%" />
                        <col width="75%" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" style="border-radius: 5px 0 0 5px  ;">
                               	 제목
                            </th>
                            <td style="border-radius: 0 5px 5px 0 ;">
                                <input type="text" style="width: 99%;" onkeypress="if(event.keyCode==13) {fn_print(); return false;}"/>
                            </td>
                        </tr>
						<!-- 
                        <tr>
                            <th scope="row" style="border-radius: 5px 0 0 5px  ;">
                               	 신고위치
                            </th>
                            <td style="border-radius: 0 5px 5px 0 ;">
                                <input type="text" style="width: 99%; border:0; background-color: #ffffff;" id="address" disabled />
                            </td>
                        </tr>
                         -->
                    </tbody>
                </table>
                <a href="#" class="schbtn ml5" style="height:55px;line-height:53px;width:5%" onclick="fn_print();">인쇄</a>
            </div>
            <div id="dv_imgPrint" class="mt10 graylinebx" >
            <!-- <div id="dv_imgPrint" class="mt10 graylinebx" style="width:100%; height:100%;"> -->
            </div>
            <div class="mt10">
                <span class="fcblue">※업무 참고용으로만 활용하시기 바랍니다.</span>
                <span class="fr"  style="float: right;">
                    <span class="mr20" > <b>출력자</b> : ${userNm}</span>
                    <span>
                        <b>출력일시</b> : ${toDay}
                    </span>
                </span>
            </div>
        </div>
    </div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer">

var popWidth = $(window).width();
var popHeight = $(window).height();

//지도 저장 툴
var gSaveTool = null;
gSaveTool = new GSaveTool(parent.gMap);
var baseLayerOn = 0;
var themeLayerOn = 0;
var GAttrLayerOn = 0;

//페이지 로딩 초기 설정
$( document ).ready(function() {

	$("#print_content").css('width', popWidth * 0.98 + 'px') ;
	$("#print_content").css('height', popHeight * 0.90 + 'px') ;

    parent.$("#mCtrlMapPrint").parent().siblings().removeClass("active");
    parent.$("#mCtrlMapPrint").parent().addClass("active");

    fn_createMapBase64Image(function(_oRes){
        var imgId = "imgPrint";
        var imgWidth = $("#print_content").width();
        var imgHeight = $("#print_content").height();

        var printViewImg = $("<img id='"+imgId +"'/>")[0];
        printViewImg.style.width = imgWidth -5 + "px";
        printViewImg.style.height = imgHeight*0.9 + "px";
        printViewImg.src = _oRes;

        document.getElementById("dv_imgPrint").appendChild(printViewImg);
    },$("#print_content").width(),$("#print_content").height());
});

/**
* @method
* @description 지도 이미지를 base64로 리턴해주는 함수
* @author 이상호(2015.10.12)
* @param {Function} _fCallback : 콜백함수
* @param {Number} _nWidth : 리사이즈시 가로 크기
* @param {Number} _nHeight : 리사이즈시 세로 크기
*/
function fn_createMapBase64Image(_fCallback,_nWidth,_nHeight) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        data: {datas:encodeURIComponent(fn_encodingMap()),width:_nWidth,height:_nHeight},
        url: contextPath + "map/saveImageToView.do",
        async: false,
        success: function(_oRes) {
            if(typeof _fCallback == 'function') {
                _fCallback(_oRes.base64);
            }
        }
    });
}


function fn_encodingMap(){

	// ============ 지도인쇄/저장 후 배경 레이어 띄움 ============
	/*	
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
	*/
    var gData = null;
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
    gData = gSaveTool.getXML({
        type : 'daum',
        url : sTileMapUrl
    });

    // ============ 지도인쇄/저장 후 배경 레이어 지움 ============
	/*
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
	*/
    return gData;
}


function fn_print(){
    //인쇄 버튼 출력 안되도록 hide
    $(".ml5").hide();
    //제목 박스 늘리기
    $(".fl").css("width","1500px");
    //하단 메세지 박스 사이즈 조절
    $(".mt10").css("width","1500px");
    //지도 틀 사이즈 조절
    $("#dv_imgPrint").css("width","1500px");
    $("#dv_imgPrint").css("height","900px");
    //지도 이미지 사이즈 조절
    $("#imgPrint").css("width","1500px");
    $("#imgPrint").css("height","900px");
    window.print();


  //print 후 창 닫기
    COMMON_UTIL.cmWindowClose($("#wnd_id").val());

}

$(parent).resize(function() {

    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();

    ww = ( ww / 2 ) - 610;
    wh = ( wh / 2 ) - 370;

    wnd.move(ww, wh);

});
</script>
</body>
</html>
