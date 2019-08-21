<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
});


</script>
</head>

<body>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<form id="frm" name="frm" method="post" action="">
<div>
	<div class="posiR">
       	<ul class="ctab_menu">
            <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkView.do?CNTRWK_ID=${flawVO.CNTRWK_ID}')">기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${flawVO.CNTRWK_ID}')">세부공사</a></li>
			<%-- <li class="sel"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${flawVO.CNTRWK_ID}')">하자기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flawcntrwk/selectFlawCntrwkList.do?CNTRWK_ID=${flawVO.CNTRWK_ID}')">하자보수공사</a></li> --%>
       	</ul>
       	<h5 class="info" style="left: 310px;">
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
		</h5>
		<p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <strong>포장공사 하자기본정보 조회</strong>
	    </p>
   </div>
	<!-- Content -->
	<div class="ctab_wrap">
		<div class="tabcont">
			<table class="tbview" summary="포장공사 하자기본정보를 조회한다.">
	            <caption>포장공사 하자기본정보</caption>
	            <colgroup>
	                <col width="15%" />
	                <col width="35%" />
	                <col width="15%" />
	                <col width="35%" />
	            </colgroup>
	            <tbody>
					<tr>
						<th scope="row">연도</th>
						<td colspan="3">
							<c:if test="${!empty flawVO.CNTRWK_YEAR}">
							<c:out value="${flawVO.CNTRWK_YEAR}"/>년 <c:out value="${flawVO.HT_SE_NM}"/>
							</c:if>								
						</td>
					</tr>
					<tr>
						<th scope="row">공사분류</th>
						<td><c:out value="${flawVO.CNTRWK_CL_NM}"/></td>
						<th scope="row">공사구분</th>
						<td><c:out value="${flawVO.CNTRWK_SE_NM}"/></td>
					</tr>
					<tr>
						<th scope="row">공사명</th>
						<td colspan="3">
							<c:out value="${flawVO.FULL_CNTRWK_NM}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">사업소</th>
						<td><c:out value="${flawVO.DEPT_NM}"/></td>
						<th scope="row">시공업체</th>
						<td><c:out value="${flawVO.CNSTRCT_CO_NM}"/></td>
					</tr>
					<tr>
						<th scope="row">하자기간</th>
						<td colspan="3">
							<fmt:parseDate var="FLAW_BEGIN_DE" value="${flawVO.FLAW_BEGIN_DE}" pattern="yyyyMMdd" />
							<fmt:formatDate value="${FLAW_BEGIN_DE}" pattern="yyyy-MM-dd" />
							 ~ 
							<fmt:parseDate var="FLAW_END_DE" value="${flawVO.FLAW_END_DE}" pattern="yyyyMMdd" />
							<fmt:formatDate value="${FLAW_END_DE}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<th scope="row">하자보증금</th>
						<td>
							<fmt:formatNumber value="${flawVO.FLAW_GTN}" type="number"/>만원
						</td>
						<th scope="row">하자보증금종류</th>
						<td>
							<c:out value="${flawVO.FLAW_GTN_KND_NM}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">하자담보기간</th>
						<td colspan="3">
							<c:if test="${flawVO.WARNT_BEGIN_DE ne emtpy}">
								<fmt:parseDate var="WARNT_BEGIN_DE" value="${flawVO.WARNT_BEGIN_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${WARNT_BEGIN_DE}" pattern="yyyy-MM-dd" />
								~
								<fmt:parseDate var="WARNT_END_DE" value="${flawVO.WARNT_END_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${WARNT_END_DE}" pattern="yyyy-MM-dd" />
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="mt10 tc">
				<div class="fr">
					<a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/updateFlawView.do?CNTRWK_ID=${flawVO.CNTRWK_ID}')" class="schbtn" >수정</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- // wrap -->
</form>

<%@ include file="/include/common.jsp" %>
</body>
</html>