<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>조사자료상세조회 </title>
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javascript">

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
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab1.do'/>')" class="Tab_01">도로포장 조사내역</a></li>
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab2.do'/>')" class="Tab_01_se">차로별 조사현황</a></li>
						<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab3.do'/>')" class="Tab_01">10m 세부내역</a></li>
					</ul>
				</div>
			</div>
			<div>&nbsp;</div>

			<h2 class="popup_title">차로별 조사현황</h2>
 			<div class="PopTb_left">
				<table>
					<colgroup>
						<col width="20%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
					</colgroup>
					<tr>
						<th>구분</th>
						<th>차로</th>
						<th>CR(%)</th>
						<th>RD(mm)</th>
						<th>IRI(m/km)</th>
						<th>GPCI</th>
					</tr>
					<c:forEach var="data" items="${data_list}">
					<tr>
						<td><c:out value="${data.DIRECTION}" /></td>
						<td><c:out value="${data.TRACK}" /></td>
						<td><c:out value="${data.CR_VAL}" /></td>
						<td><c:out value="${data.RD_VAL}" /></td>
						<td><c:out value="${data.IRI_VAL}" /></td>
						<td><font color="red"><c:out value="${data.GPCI}" /></font></td>
					</tr>
					</c:forEach>
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