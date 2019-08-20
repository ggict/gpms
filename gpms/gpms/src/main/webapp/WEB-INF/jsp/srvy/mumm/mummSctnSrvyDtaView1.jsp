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
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/* 
$( document ).ready(function() {
	
});

</script>
</head>

<body>
<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SRVY_NO" name="SRVY_NO" value="<c:out value="${param.SRVY_NO}"/>"/>
<input type="hidden" id="SRVY_DE" name="SRVY_DE" value="<c:out value="${param.SRVY_DE}"/>"/>
<input type="hidden" id="ROUTE_CODE" name="ROUTE_CODE" value="<c:out value="${param.ROUTE_CODE}"/>"/>
<input type="hidden" id="ENDPT" name="ENDPT" value="<c:out value="${param.ENDPT}"/>"/>
<input type="hidden" id="STRTPT" name="STRTPT" value="<c:out value="${param.STRTPT}"/>"/>

<div class="Pop_wrap">
	<div class="cont_Section">
	    <div style="margin:10px;">
	    	<!--// Tab -->
	    	<div class="Pop_Section">
				<div class="cont_TabBx">
					<ul>
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab1.do'/>')" class="Tab_01_se">도로포장 조사내역</a></li>
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab2.do'/>')" class="Tab_01">차로별 조사현황</a></li>
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab3.do'/>')" class="Tab_01">10m 세부내역</a></li>
					</ul>
				</div>
			</div>
			<div>&nbsp;</div>
			
	    	<h2 class="popup_title">도로포장 조사내역</h2>
 			<div class="PopTb_left">
				<table>
					<colgroup>
						<col width="23%">
						<col width="21%">
						<col width="23%">
						<col width="33%">
					</colgroup>
					<tr>
						<th>조사명</th>
						<td colspan="3"><c:out value="${mummSctnSrvyDtaVO.SRVY_NM}" /></td>
					</tr>
					<tr>
						<th>노선명</th>
						<td><c:out value="${mummSctnSrvyDtaVO.ROUTE_NAME}" /></td>
						<th>도로명</th>
						<td><c:out value="${mummSctnSrvyDtaVO.ROAD_NM}" /></td>
					</tr>
					<tr>
						<th>노선번호</th>
						<td><fmt:parseNumber value="${mummSctnSrvyDtaVO.ROUTE_CODE}" type="number"/></td>
						<th>조사구간</th>
						<td><c:out value="${mummSctnSrvyDtaVO.STRTPTNL}" /></td>
					</tr>
					<tr>
						<th>시점</th>
						<td><fmt:formatNumber value="${mummSctnSrvyDtaVO.STRTPT}" type="number"/>m</td>
						<th>종점</th>
						<td><fmt:formatNumber value="${mummSctnSrvyDtaVO.ENDPT}" type="number"/>m</td>
					</tr>
					<tr>
						<th>조사일자</th>
						<td><c:out value="${mummSctnSrvyDtaVO.SRVY_DE}" /></td>
						<th>조사연장</th>
						<td><fmt:formatNumber value="${mummSctnSrvyDtaVO.LENGTH}" type="number"/>m</td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="3"><c:out value="${mummSctnSrvyDtaVO.MEMO}" /></td>
					</tr>
				</table>
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