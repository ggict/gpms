<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<style type="text/css">
#pagination {
	background-color: ivory;
	text-align: center;
	width: 263px;
}

#pagination a {
	display: inline-block;
	margin-right: 10px;
}

#pagination .on {
	font-weight: bold;
	cursor: default;
	color: #777;
}
</style>
<!-- <title>위치 통합조회 </title> -->
<%@ include file="/include/common_head.jsp"%>
<script src="<c:url value='/js/map/mapLocSearch.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	//페이지 로딩 초기 설정
	$(document).ready(function() {

		parent.$("#mCtrlLocSearch").parent().siblings().removeClass("active");
		parent.$("#mCtrlLocSearch").parent().addClass("active");

	});

	//창 조절시 차트 resize
	$(window).on('resize', function() {
		$("#menu_wrap").height(parent.$("#ifr_locsearch").height() - 230);
		$("#menu_wrap").width(parent.$("#ifr_locsearch").width() - 30);
		$(".tsch").width(parent.$("#ifr_locsearch").width() - 30);
		$(".resultTxt").width(parent.$("#ifr_locsearch").width() - 30);
	});
	function fn_localSearch_sh(a) {

		if (a == "totalSearch") {
			$("#" + a).css('display', 'block');
			$("#jusoSearch").css('display', 'none');
			$("#newJusoSearch").css('display', 'none');
		} else if (a == "jusoSearch") {
			$("#" + a).css('display', 'block');
			$("#totalSearch").css('display', 'none');
			$("#newJusoSearch").css('display', 'none');
			$("#newJusoSearch").css('display', 'none');
			$(".bgsch").css('height','110px');
			
		} else if (a == "newJusoSearch") {
			$("#" + a).css('display', 'block');
			$("#totalSearch").css('display', 'none');
			$("#jusoSearch").css('display', 'none');
			$(".bgsch").css('height','110px');
		}

	}
</script>
</head>
<body id="wrap" class="left-tool searchLoc">
	<!-- 필수 파라메터(START) -->
	<input type="hidden" id="callBackFunction" name="callBackFunction"
		value="" />
	<input type="hidden" id="opener_id" name="opener_id" value="" />
	<input type="hidden" id="wnd_id" name="wnd_id" value="" />
	<!-- 필수 파라메터(END) -->
	<div id="header">
		<div id="gnb" style="width:1500px">
			<ul>
				<li onclick="fn_localSearch_sh('totalSearch');" style="padding:20px; font-size:20px">통합검색</li>
				<li onclick="fn_localSearch_sh('jusoSearch');" style="padding:20px; font-size:20px" >주소검색</li>
				<li onclick="fn_localSearch_sh('newJusoSearch');" style="padding:20px; font-size:20px">새주소검색</li>
				<!-- 
		<span><a hef="#" onclick="fn_localSearch_sh('totalSearch');">통합검색</a></span>
		<span><a hef="#" onclick="fn_localSearch_sh('jusoSearch');">주소검색</a></span>
		<span><a hef="#" onclick="fn_localSearch_sh('newJusoSearch');">새주소검색</a></span> -->
			</ul>
		</div>

		<div class="content" style="width: 269px;">
			<div id="totalSearch" class="tsch"
				style="width: 293px; display: block">
				<input type="text" id="keyword" name="keyword" style="width: 250px"
					onkeypress="if(event.keyCode==13) {searchPlaces($(this)); return false;}" />
				<a href="#" id="sch" onclick="searchJusoPlaces($(this));"><img
					src="../images/btn_sch.gif" alt="검색" title="검색" /></a>
			</div>

			<div id="jusoSearch" class="tsch" style="width: 293px; display: none">
				<!-- <input type="select" id="keyword" name="keyword" style="width: 255px"
				onkeypress="if(event.keyCode==13) {searchPlaces($(this)); return false;}" /> -->
				<div class="fl bgsch" style="width: 500px">
					<div class="schbx mt10">
						<ul class="sch">
							<span class="wid100"> <label>시</label> 
							<select	id="ADM_CODE" name="ADM_CODE" alt="시"
								onchange="fn_change_sigungu();" class="input"
								style="width: 100px;">
									<option value="">== 전체 ==</option>
									<c:forEach items="${admCodeList}" var="admCode">
										<option value="${admCode.CODE_VAL}">${admCode.CODE_NM}</option>
									</c:forEach>
							</select>
							</span>
							<span class="wid100"> <label>구/군</label> <select
								id="GU_CODE" name="GU_CODE" alt="구/군" class="input"
								style="width: 100px;">
									<option value="">== 전체 ==</option>
							</select>
							</span>
							<span class="wid100"> <label>읍/면/동</label> <input
								type="text" name="DONG" id="DONG" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span>
							<br>
							<span class="wid100"> <label>본번</label> <input type="text"
								name="BON" id="BON" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span> -
							<span class="wid100"> <label>부번</label> <input type="text"
								name="BU" id="BU" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span>
							<li class="wid100" id="jusoKeyword" style="margin-top: 10px;">
								<a href="#" class="schbtn dpb"
								onclick="searchJusoPlaces($(this));">검색</a>
							</li>
						</ul>
					</div>
				</div>
				</div>
		<div id="newJusoSearch" class="tsch"
			style="width: 293px; display: none">
			<div class="fl bgsch" style="width: 500px">
					<div class="schbx mt10">
						<ul class="sch">
							<span class="wid100"> <label>시</label> <select
								id="ADM_CODE" name="ADM_CODE" alt="시"
								onchange="fn_change_sigungu();" class="input"
								style="width: 100px;">
									<option value="">== 전체 ==</option>
									<c:forEach items="${admCodeList}" var="admCode">
										<option value="${admCode.CODE_VAL}">${admCode.CODE_NM}</option>
									</c:forEach>
							</select>
							</span>
							<span class="wid100"> <label>구/군</label> <select
								id="GU_CODE" name="GU_CODE" alt="구/군" class="input"
								style="width: 100px;">
									<option value="">== 전체 ==</option>
							</select>
							</span>
							<span class="wid100"> <label>로</label> <input
								type="text" name="RO" id="RO" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span>
							<br>
							<span class="wid100"> <label>본번</label> <input type="text"
								name="BON" id="BON" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span> -
							<span class="wid100"> <label>부번</label> <input type="text"
								name="BU" id="BU" value="" style="width: 100px;"
								class="MX_80 CS_50 input" />
							</span>
							<li class="wid100" id="newJusoKeyword" style="margin-top: 10px;">
								<a href="#" class="schbtn dpb"
								onclick="searchNewJusoPlaces($(this));">검색</a>
							</li>
						</ul>
					</div>
				</div>
		</div>
		
			<div style="width: 250px; display: none" id="result">
				<div class="resultTxt">
					<span class="fl">검색결과</span> <span class="resultnum"> <strong></strong>건
						(최대45건)
					</span>
				</div>
				<div id="menu_wrap" class="resultbx"
					style="overflow-y: auto; height: 350px;">
					<ul id="placesList"></ul>
					<div id="pagination" style="padding: 5px 0px;"></div>
				</div>
			</div>
	</div>
	<%-- </form> --%>
	<!-- 공통 (START)-->
	<%@ include file="/include/common.jsp"%>
	<!-- 공통 (END)-->


</body>
</html>