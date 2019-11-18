<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body>

<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<div class="tabcont">
	<!-- Content -->
	<div class="content">
	    <h3>노선 데이터로 검색
	    	<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('/routeinfo/selectRouteInfoList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기"/></a>
	    </h3>
	    <p class="location">
	        <span>노선검색</span>
	        <strong>노선정보 수정</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
	    	<div class="scroll" style="height:240px;">
		        <table class="tbview" summary="노선정보를 수정합니다.">
		            <caption>노선정보 수정</caption>
		            <colgroup>
		                <col width="15%" />
		                <col width="35%" />
		                <col width="15%" />
		                <col width="35%" />
		            </colgroup>
		            <tbody>
						<tr>
							<th scope="row">노선번호</th>
							<td>
								<label for="ROAD_NO_VAL"></label>
								<c:out value="${routeInfoVO.ROAD_NO_VAL}"/>
							</td>
							<th scope="row">노선명</th>
							<td>
								<label for="ROAD_NAME"></label>
								<c:out value="${routeInfoVO.ROAD_NAME}"/>
							</td>
						</tr>
						<tr>
							<th scope="row">시점명</th>
							<td>
								<label for="ST_POINT"></label>
								<c:out value="${routeInfoVO.ST_POINT}"/>
							</td>
							<th scope="row">종점명</th>
							<td>
								<label for="ED_POINT"></label>
								<c:out value="${routeInfoVO.ED_POINT}"/>
							</td>
						</tr>
						<tr>
							<th scope="row">총 연장(km)</th>
							<td>
								<label for="ROAD_TOT_LEN_JYG_Y"></label>
								<c:out value="${routeInfoVO.ROAD_TOT_LEN_JYG_Y}"/>
							</td>
							<th scope="row">도 관리구간(km)</th>
							<td>
								<label for="DO_MANAGE_SCTN_LEN"></label>
								<c:out value="${routeInfoVO.DO_MANAGE_SCTN_LEN}"/>
							</td>
						</tr>
					</tbody>
		        </table>
	        </div>
	    </div>  
	</div>


	 
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">	
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
});	

</script>
</body>
</html>