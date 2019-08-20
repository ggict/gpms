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
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/common.js'/>"></script>
<script src="<c:url value='/js/srvy/srvyDtaAnal.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/* 
$( document ).ready(function() {
	fn_init_svryDtaAnal();
});

</script>
</head>
<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div class="cont_PopLeft">
	<div class="cont_TitBx">
		<p><c:out value="${screen_title}"/></p>
	</div>
	<div class="cont_Section">
		
	    <%@ include file="/include/leftMenu.jsp" %>
	    
	    <div style="margin:10px 210px;">
			<div>
				<h2 class="popup_title">조사자료 분석</h2>
				<div id="search" style="width:700px; height:75px; padding:10px 0; color: black; background-color: #E4EFF8">
					<div style="margin:5px;">※ <font style="font-weight: bold;">선택하신 조사자료를 적용하여</font> 100m 집계 및 산정작업을 진행합니다.</div> 
					<div style="margin:5px;">※ 산정자료의 년/월은 조사자료의 년/월을 기준으로 설정됩니다.</div>
					<div style="margin:5px;">※ 조사자료가 여러 건 일 경우 순서대로 등록하셔야 합니다.</div>
					<div style="margin:5px;">※ 데이터를 분석하는데 많은 시간이 소요될 수 있습니다. 분석이 완료 될때까지 기다려주세요.</div>
				</div>
				<div class="cont_ListBx">
					<table id="gridArea"></table>
					<div id="gridPager"></div>
				</div>
				<div>
					<div class="Btn">
						<a href="#" class="Btn_01 popup_btn" onclick="fn_search()">새로고침</a>
						<a href="#" class="Btn_01 popup_btn" onclick="fn_analyze()">분석하기</a>
					</div>
				</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
			</div>
			<div>
				<h2 class="popup_title">진행상황 보기</h2>
				<div>
					<div id="logArea" style="width:700px; height:80px; border: 1px solid #d3d3d3; padding: 10px; overflow: auto;"></div>
				</div>
			</div>
			<div style="display: none;">
				<div id="dvProgress" title="진행 중 ...">
					<div style="font-size:12px; padding-bottom:7px; color: black; margin: 2px;">
						<br/><span id="t_progress"></span><br/>잠시만 기다려 주세요.
					</div>
					<div style="text-align: center;"><br/><img src='<c:url value="/images/loading/progress.gif" />' /></div>
				</div>
			</div>
	    </div>
	</div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>