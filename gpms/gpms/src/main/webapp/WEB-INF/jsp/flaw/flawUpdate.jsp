<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp"%>

<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성 (전체)
	COMMON_UTIL.cmCreateDatepickerLinked('WARNT_BEGIN_DE','WARNT_BEGIN_DE', 10);
	COMMON_UTIL.cmCreateDatepickerLinked('WARNT_END_DE','WARNT_END_DE', 10);
});

//글 등록
function fn_add() {
	
	//null check
	if(COMMON_UTIL.fn_check_notnull("tr")) {
		return;	
	}
	
	if( confirm('<spring:message code="warn.insert.msg" />') ) {
		// 진행 프로그래스바 생성
		$.ajax({
			url: contextPath + 'api/flaw/addFlaw.do'
			,type: 'post'
			,contentType: 'application/json'
			,data: JSON.stringify( $("#frm").cmSerializeObject()) 
			,dataType: 'json'
			,success: function(res){				
				if (res != null) {
	            	alert(res.resultMSG);
	            	COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=' + res.CNTRWK_ID);
	                return;
	            }
			}
			,error: function(a,b,msg){
				
			}
		});
	}			
}


//글 수정
function fn_update() {
	
	//null check
	if(COMMON_UTIL.fn_check_notnull("tr")) {
		return;	
	}
	
	if( confirm('<spring:message code="warn.update.msg" />') ) {
		$.ajax({
			url: contextPath + '/api/flaw/updateFlaw.do'
			,type: 'post'
			,contentType: 'application/json'
			,data: JSON.stringify( $("#frm").cmSerializeObject()) 
			,dataType: 'json'
			,success: function(res){				
				if (res != null) {
	            	alert(res.resultMSG);
	            	COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=' + res.CNTRWK_ID);
	                return;
	            }
			}
			,error: function(a,b,msg){
				
			}
		});
	}			
}


// 삭제 처리
function fn_Delete() {
	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		$.ajax({
			url: contextPath + 'api/flaw/deleteFlaw.do'
			,type: 'post'
			,contentType: 'application/json'
			,data: JSON.stringify( $("#frm").cmSerializeObject()) 
			,dataType: 'json'
			,success: function(res){				
				if (res != null) {
	            	alert(res.resultMSG);
	            	COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=' + res.CNTRWK_ID);
	                return;
	            }
			}
			,error: function(a,b,msg){
				
			}
		});
	}
}

</script>
</head>

<body>
	<!-- 필수 파라메터(START) -->
	<input type="hidden" id="action_flag" name="action_flag"
		value="<c:out value="${action_flag}"/>" />
	<input type="hidden" id="callBackFunction" name="callBackFunction"
		value="" />
	<input type="hidden" id="opener_id" name="opener_id" value="" />
	<input type="hidden" id="wnd_id" name="wnd_id" value="" />
	<!-- 필수 파라메터(END) -->

	<form id="frm" name="frm" method="post" action="">
		<!-- KEY 파라메터 -->
		<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID"
			value="<c:out value="${result.CNTRWK_ID}"/>" /> <input type="hidden"
			id="FLAW_ID" name="FLAW_ID"
			value="<c:out value="${result.FLAW_ID}"/>" />
		<div>
			<div class="posiR">
		       	<ul class="ctab_menu">
		            <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkView.do?CNTRWK_ID=${result.CNTRWK_ID}')">기본정보</a></li>
					<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${result.CNTRWK_ID}')">세부공사</a></li>
					<%-- <li class="sel"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${result.CNTRWK_ID}')">하자기본정보</a></li>
					<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flawcntrwk/selectFlawCntrwkList.do?CNTRWK_ID=${result.CNTRWK_ID}')">하자보수공사</a></li> --%>
		       	</ul>
		       	<h5 class="info" style="left: 310px;">
					<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${result.CNTRWK_ID}')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
				</h5>
				<p class="location">
			        <span>포장공사 이력관리</span>
			        <span>포장공사 이력관리</span>
			        <strong>포장공사 하자기본정보 수정</strong>
			    </p>
		    </div>
			<!-- Content -->
			<div class="ctab_wrap">
				<div class="tabcont">
					<table class="tbview" summary="포장공사 하자기본정보를 수정한다.">
			            <caption>포장공사 하자기본정보 수정</caption>
			            <colgroup>
			                <col width="15%" />
			                <col width="35%" />
			                <col width="15%" />
			                <col width="35%" />
			            </colgroup>
			            <tbody>
							<tr>
								<th scope="row">연도</th>
								<td colspan="3"><c:if test="${!empty result.CNTRWK_YEAR}">
										<c:out value="${result.CNTRWK_YEAR}" />년 <c:out
											value="${result.HT_SE_NM}" />
									</c:if></td>
							</tr>
							<tr>
								<th scope="row">공사분류</th>
								<td><c:out value="${result.CNTRWK_CL_NM}" /></td>
								<th scope="row">공사구분</th>
								<td><c:out value="${result.CNTRWK_SE_NM}" /></td>
							</tr>
							<tr>
								<th scope="row">공사명</th>
								<td colspan="3"><c:out value="${result.FULL_CNTRWK_NM}" />
								</td>
							</tr>
							<tr>
								<th scope="row">사업소</th>
								<td><c:out value="${result.DEPT_NM}" /></td>
								<th scope="row">시공업체</th>
								<td><c:out value="${result.CNSTRCT_CO_NM}" /></td>
							</tr>
							<tr>
								<th scope="row">하자기간</th>
								<td colspan="3">
									<c:out value="${result.FLAW_BEGIN_DE}" /> ~ <c:out value="${result.FLAW_END_DE}" />
							</tr>
							<tr>
								<th scope="row">하자보증금(만원)<span class="fcred"> *</span></th>
								<td><input type="text" name=FLAW_GTN id="FLAW_GTN" alt="하자보증금"
									value="<c:out value="${result.FLAW_GTN}"/>"
									class="MX_10 CS_10 DT_INT input notnull" /> </td>
								<th scope="row">하자보증금종류<span class="fcred"> *</span></th>
								<td><select name="FLAW_GTN_KND" id="FLAW_GTN_KND" alt="하자보증금종류"
									class="select notnull">
										<c:forEach var="fgkd" items="${fgkdList}">
											<option value="${fgkd.CODE_VAL}"
												<c:if test = "${fgkd.CODE_VAL == result.FLAW_GTN_KND}"> selected="selected" </c:if>>${fgkd.CODE_NM}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th scope="row">하자담보기간<span class="fcred"> *</span></th>
								<td colspan="3"><input type="text" id="WARNT_BEGIN_DE"
									name="WARNT_BEGIN_DE" alt="하자담보시작일"
									value="${result.WARNT_BEGIN_DE}" class="DT_DATE input notnull" />
									~ <input type="text" id="WARNT_END_DE" name="WARNT_END_DE"
									alt="하자담보종료일" value="${result.WARNT_END_DE}"
									class="DT_DATE input notnull" /></td>
							</tr>
						</tbody>
					</table>
					<div class="mt10 tc">
						<div class="fr">
							<c:if test="${action_flag == 'UPDATE'}">
								<c:if
									test="${sessionScope.userinfo.IS_ADMIN=='Y' || (sessionScope.userinfo.DEPT_CODE==result.DEPT_CODE)}">
									<a href="#" onclick="fn_update()" class="schbtn">수정</a>
									<a href="#" onclick="fn_Delete();" class="graybtn">삭제</a>
								</c:if>
								<a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${result.CNTRWK_ID}')" class="graybtn">취소</a>
							</c:if>
							<c:if test="${action_flag != 'UPDATE'}">
								<a href="#" onclick="fn_add()" class="schbtn">등록</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- // Content -->
		<!-- // wrap -->
	</form>

	<%@ include file="/include/common.jsp"%>
</body>
</html>