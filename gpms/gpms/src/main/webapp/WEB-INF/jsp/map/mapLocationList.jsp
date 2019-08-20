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
<style type="text/css">
#pagination {background-color: ivory;text-align: center;width: 263px;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>
<!-- <title>위치 통합조회 </title> -->
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/map/mapLocSearch.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
    parent.$("#mCtrlLocSearch").parent().siblings().removeClass("active");
    parent.$("#mCtrlLocSearch").parent().addClass("active");
    
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
	$("#menu_wrap").height(parent.$("#ifr_locsearch").height()-100);
	$("#menu_wrap").width(parent.$("#ifr_locsearch").width()-30);
	$(".tsch").width(parent.$("#ifr_locsearch").width()-30);
	$(".resultTxt").width(parent.$("#ifr_locsearch").width()-30);
});

</script>
</head>
<body id="wrap" class="left-tool searchLoc">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div class="content" style="width: 269px;" >
    <div class="tsch" style="width: 293px" >
        <input type="text" id="keyword" name="keyword" style="width: 255px" onkeypress="if(event.keyCode==13) {searchPlaces($(this)); return false;}"/>
        <a href="#" id="sch" onclick="searchPlaces($(this));"><img src="../images/btn_sch.gif" alt="검색" title="검색" /></a>
    </div>
    <div style="width:250px;display:none" id="result">
        <div class="resultTxt">
            <span class="fl">검색결과</span>
            <span class="resultnum">
                <strong></strong>건 (최대45건)
            </span>
        </div>
        <div id="menu_wrap" class="resultbx" style="overflow-y:auto;">
            <ul id ="placesList"></ul>
            <div id="pagination" style="padding: 5px 0px;"></div>
        </div>
    </div>
</div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>