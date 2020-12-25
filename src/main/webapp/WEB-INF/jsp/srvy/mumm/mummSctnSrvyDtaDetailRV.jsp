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
		    var x = "${mummSctnSrvyDtaVO.x}";
		    var y = "${mummSctnSrvyDtaVO.y}";

		    var rvheight = $(this).height();
		    var rvwidth = $(this).width();
		    $("#imgDiv").css("height", rvheight - 33 + "px");
		    $("#imgDiv").css("width", rvwidth + "px");

		    // 로드뷰
		    fnShowRoadview(x, y);
		    setTimeout(function() {
		    	var img_front = $("#roadView div[id^='_fc_front'] img").attr("src");
		   		$("#roadImg").attr("src",img_front);
		   		$("#front").focus();
		  	}, 2000);
		    $("#front").click(function(){
		    	var img_front = $("#roadView div[id^='_fc_front'] img").attr("src");
		   		$("#roadImg").attr("src",img_front);
		    });

		    $("#back").click(function(){
		    	var img_back = $("#roadView div[id^='_fc_back'] img").attr("src");
		    	$("#roadImg").attr("src",img_back);
		    });

		});

		$(parent).resize(function() {

		    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
		    var wndId = $("#wnd_id").val();

		    // 위치조정
		    var wnd = parent.$.window.getWindow(wndId);

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

		    wnd.move(width - w - 2, 63.5);

		    // 팝업 크기
		    parent.$("#" + wndId).css({"width" : w + "px", "height" : h + "px"});
		    // iframe 크기
		    parent.$("#" + wndId).find("iframe").css({"height" : (h - 10)});
		    // div 크기
		    $("#imgDiv").css("height", (h - 43) + "px");


		});

		parent.$("#leftCloseBt").click(function(){

		    var wndId = $("#wnd_id").val();
		    var h = $(parent).height();

		    // 팝업 크기
		    parent.$("#" + wndId).css({"height" : (h - 110) + "px"});
		    // iframe 크기
		    parent.$("#" + wndId).find("iframe").css({"height" : (h - 146)});
		    // div 크기
		    $("#imgDiv").css({"height" : (h - 179) + "px"});
		});

		parent.$("#leftOpenBt").click(function(){

		    var wndId = $("#wnd_id").val();
		    var h = $(parent).height();

		    // 팝업 크기
		    parent.$("#" + wndId).css({"height" : (h - 407) + "px"});
		    // iframe 크기
		    parent.$("#" + wndId).find("iframe").css({"height" : (h - 447)});
		    // div 크기
		    $("#imgDiv").css({"height" : (h - 480) + "px"});

		});

		// 로드뷰
		function fnShowRoadview(x, y) {

		    // 로드뷰 표출
		    GDaumMap.RoadView.prototype.getRoadViewByItgrtn(x, y);

		}

	</script>
</head>

<body class="research">

	<!-- 필수 파라메터(START) -->
	<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
	<input type="hidden" id="opener_id" name="opener_id" value=""/>
	<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
	<!-- 필수 파라메터(END) -->
	<div id ="imgDiv" style="width:100%;overflow:scroll;position:relative;">
		<div style ="position: absolute;top:9px;left:8px;">
			<button class="btn pri" id="front">로드뷰-1</button>
			<button class="btn pri" id="back">로드뷰-2</button>

		</div>
		<img id= "roadImg" src="" style="width:100%;height:100%;">
	</div>

	<div id="roadView" style="width: 100%; display:none"></div>

	<%@ include file="/include/common.jsp" %>
	<!-- 공통 (END)-->

</body>
</html>
