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
<!-- <title>조사자료상세조회 </title> -->
<!--
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

// 페이지 로딩 초기 설정
$( document ).ready(function() {

    parent.rshInfoCnt++;

    // 상세보기로 넘어온 경우 파라미터 받기
    var imgNm = "${mummSctnSrvyDtaVO.RDSRFC_IMG_FILE_NM_1}";
    var imgYear = "${mummSctnSrvyDtaVO.SRVY_YEAR}";
    var imgRoadno = "${mummSctnSrvyDtaVO.ROUTE_CODE}";

    // 균열분석 이미지
    fnShowImage(imgNm, imgYear, imgRoadno);

});

$(parent).resize(function() {

    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();

    // 위치조정
    var wnd = parent.$.window.getWindow(wndId);
    wnd.move(0, 63.5);

    // 팝업 크기 지정
    var height = $(parent).height();
    var width = $(parent).width();

    var h = height - 407;
    var w = 0;

    if ( width < 1400 ) {

        w = 400

    } else {

        w = ( width / 2 ) - 300;

    }

    // 팝업 크기
    parent.$("#" + wndId).css({"width" : w + "px", "height" : h + "px"});
    // iframe 크기
    parent.$("#" + wndId).find("iframe").css({"height" : (h - 10)});
    // div 크기
    $("#srvyImage").css("height", (h - 43) + "px");
    // 이미지 크기
    $("#srvyImage > img").width( ( $("#srvyImage").width() ) + "px" );
});

parent.$("#leftCloseBt").click(function(){

    var wndId = $("#wnd_id").val();
    var h = $(parent).height();

    // 팝업 크기
    parent.$("#" + wndId).css({"height" : (h - 110) + "px"});
    // iframe 크기
    parent.$("#" + wndId).find("iframe").css({"height" : (h - 146)});
    // div 크기
    $("#srvyImage").css({"height" : (h - 179) + "px"});
    // 이미지 크기
    $("#srvyImage > img").width( ( $("#srvyImage").width() ) + "px" );

});

parent.$("#leftOpenBt").click(function(){

    var wndId = $("#wnd_id").val();
    var h = $(parent).height();

    // 팝업 크기
    parent.$("#" + wndId).css({"height" : (h - 407) + "px"});
    // iframe 크기
    parent.$("#" + wndId).find("iframe").css({"height" : (h - 447)});
    // div 크기
    $("#roadView").css({"height" : (h - 480) + "px"});

});

// 균열분석 이미지
function fnShowImage(imgNm, imgYear, imgRoadno) {

    var html = "";
    var defaultUrl = "/PMS_UploadFile/" + imgYear + "/" + imgRoadno + "/" + imgNm;

    html += "<img src='" + defaultUrl + "' style='width: " + ( $("#srvyImage").width() - 20 ) + "px' />";
    //html += "<img src='/PMS_UploadFile/2017/0391/국지도56호선_0100상_01(김포cc삼거리~하성교차로)_s000070000.jpg' style='width: " + ( $("#srvyImage").width() - 20 ) + "px' />";

//     $("#srvyImage").html(html);

}

</script>
</head>

<body class="research">
<div class="imageSample"><img src='/gpms/images/b.jpg' width="100%" /></div>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<!-- 셀이미지팝업 -->
<div id="srvyImage" style="width: 100%;">
	<img src='/gpms/images/a.png' />
</div>

<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>
