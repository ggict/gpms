<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사통계 </title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");

	// 달력 생성
	//COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE','SCH_COMPET_DE', 10);

	//창 조절시 차트 width
	var rw = $(window).width()/3;


});

//창 조절시 차트 resize
$(window).on('resize', function(){

});


</script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SCH_DEPT_CODE" name="SCH_DEPT_CODE" value=""/>
<input type="hidden" id="SCH_STRWRK_DE" name="SCH_STRWRK_DE" value=""/>
<input type="hidden" id="SCH_COMPET_DE" name="SCH_COMPET_DE" value=""/>
<form id="frm" name="frm" method="post" action="">

<!-- 		<div id="sch_cnt01" class="tabcont"> -->
<!-- 			<h3>보수대상 선정 전체 PROCESS 설명 화면</h3> -->
<!-- 			<div class="img_rpair"> -->
<%-- 				<img src="<c:url value='/images/img_rpair.png' />" /> --%>
<!-- 			</div> -->
<!-- 		</div> -->

</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>