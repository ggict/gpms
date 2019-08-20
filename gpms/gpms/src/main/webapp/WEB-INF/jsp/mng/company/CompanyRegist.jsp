<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<%
 /**
  * @Class Name : ClCodeRegister.jsp
  * @Description : ClCode Register 화면
  * @Modification Information
  *
  * @since 2017-05-25
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

</script>
</head>

<body class="cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<div id="Pop_wrap">
	<br />
	<!-- Content -->
	<div class="ConBx">
		<div class="Pop_Section">
			<div class="PopTitBx">
				<!-- <p>외부업체 추가</p> -->
				<div class="content" id="company_Regist-div">
					<form:form commandName="companyVO" id="company_Regist-form">
						<table class="tbview" summary="업체정보 정보를 등록/수정합니다.">
							<caption>업체정보 정보를 등록/수정</caption>
							<colgroup>
								<col width="30%" />
								<col width="70%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span class="fcred">* </span>업체 명</th>
									<td><form:input path="CO_NM" maxLength="200"
											cssClass="input notnull" /> <form:errors path="CO_NM" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>대표자 명</th>
									<td><form:input path="RPRSNTV_NM" maxLength="200"
											cssClass="input notnull" /> <form:errors path="RPRSNTV_NM" />
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>영업 소재지</th>
									<td><form:input path="BIZ_LOC" maxLength="200"
											cssClass="input notnull" /> <form:errors path="BIZ_LOC" />
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>대표 전화 번호</th>
									<td><form:input type="tel" path="RPRSNT_TEL_NO"
											maxLength="15" cssClass="input notnull" /> <form:errors
											path="RPRSNT_TEL_NO" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>사업자 번호</th>
									<td><form:input type="tel" path="BIZ_NO" maxLength="12"
											cssClass="input notnull" /> <form:errors path="BIZ_NO" />
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>담당자 명</th>
									<td><form:input path="CHARGER_NM" maxLength="200"
											cssClass="input notnull" /> <form:errors path="CHARGER_NM" />
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>담당자 전화 번호</th>
									<td><form:input type="tel" path="CHARGER_TEL_NO"
											maxLength="15" cssClass="input notnull" /> <form:errors
											path="CHARGER_TEL_NO" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>업체 상태 코드</th>
									<td><form:select path="CO_STTUS_CODE"
											style="width: 98.8%;">
											<form:option value="" label="===== 전체 =====" />
											<form:options items="${codesCOST}" itemValue="CODE_VAL"
												itemLabel="CODE_NM" />
										</form:select> <form:errors path="CO_STTUS_CODE" /></td>
								</tr>

								<tr>
									<th scope="row"><span class="fcred">* </span>등록 업종</th>
									<td><form:input path="REGIST_INDUTY" maxLength="100"
											cssClass="input notnull" /> <form:errors
											path="REGIST_INDUTY" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>주력 공사</th>
									<td><form:input path="MFRC_CNTRWK" maxLength="100" /> <form:errors
											path="MFRC_CNTRWK" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>주요 공사 지역</th>
									<td><form:input path="MAJOR_CNTRWK_AREA" maxLength="100"
											cssClass="input notnull" /> <form:errors
											path="MAJOR_CNTRWK_AREA" /></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>사용 여부</th>
									<td><form:select path="USE_AT" style="width: 98.8%;">
											<form:option value="" label="===== 전체 =====" />
											<form:option value="Y" label="예" selected="selected" />
											<form:option value="N" label="아니오" />
										</form:select> <form:errors path="USE_AT" /></td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>

			<div class="btnbx" style="margin-right:15px;">
				<a href="#"	class="schbtn" onclick="document.getElementById('company_Regist-form').reset(); return false;">초기화</a>
				<a href="#" class="schbtn" onclick="fn_company_save(); return false;">등록</a>
				<a href="#" class="schbtn" onclick="fn_close_dialog(); return false;">닫기</a>
				<%-- <a href="#" class="schbtn" onclick="if (confirm('<spring:message code="warn.cancel.msg" />')) {javascript:fn_close_dialog();} else {return;}">닫기</a> --%>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" defer="defer">
	<!--//	//-->
		//<!--
function fn_close_dialog(){
			
	//17.11.23 공통 메세지로 알림창 띄우기로 변경			
	if (confirm('<spring:message code="warn.cancel.msg" />')) {		
		var vform = $('#company_Regist-form');
		window.parent.fn_search(COMMON_LANG.trimdata( vform.find('#CO_NO').val()));
		var wnd_id = $("#wnd_id").val();
		$('#'+wnd_id, window.parent.document).remove();
	}
}
		
// 사용X
/* function resize(obj) {
    
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
} */

$(document).ready(function(){
	$(".button > a").button().click(function(event) {
		event.preventDefault();
		//alert(this.id);
		if (this.id == "btn_aaa") {
			//fn_aaa_click();
		}
	});
	$(".sch_bth > a").button().click(function(event) {
		event.preventDefault();
		//alert(this.id);
		if (this.id == "btn_search_code") {
			
		}
	});
	
});
 
/* 글 등록 function */
function fn_company_save() {	
	var vform = $('#company_Regist-form');

	//alert(vform.find('#CO_NO').val());
	//alert(vform.find('#CO_NM').val());
	//alert(vform.find('#RPRSNTV_NM').val());
	//alert(vform.find('#BIZ_LOC').val());
	//alert(vform.find('#RPRSNT_TEL_NO').val());
	//alert(vform.find('#BIZ_NO').val());
	//alert(vform.find('#CHARGER_NM').val());
	//alert(vform.find('#CHARGER_TEL_NO').val());
	//alert(vform.find('#CO_STTUS_CODE').val());
	//alert(vform.find('#REGIST_INDUTY').val());
	//alert(vform.find('#MFRC_CNTRWK').val());
	//alert(vform.find('#MAJOR_CNTRWK_AREA').val());
	//alert(vform.find('#CRTR_NO').val());
	//alert(vform.find('#CREAT_DT').val());
	//alert(vform.find('#UPDUSR_NO').val());
	//alert(vform.find('#UPDT_DT').val());
	//alert(vform.find('#USE_AT').val());
	//alert(vform.find('#DELETE_AT').val());
	

	//업체_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CO_NM').val()))){
		alert("업체 명에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CO_NM').val()).length>200){
		alert("업체 명 값은 최대 200자까지 입력할 수 있습니다.");
		return;
	}	
	//대표자_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPRSNTV_NM').val()))){
		alert("대표자 명에 값을 입력하세요.");
		return;
	}
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#RPRSNTV_NM').val()).length>200){
		alert("대표자 명 값은 최대 200자까지 입력할 수 있습니다.");
		return;
	}	
	//영업_소재지
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#BIZ_LOC').val()))){
		alert("영업 소재지에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#BIZ_LOC').val()).length>200){
		alert("영업 소재지 값은 최대 200자까지 입력할 수 있습니다.");
		return;
	}	

	//대표_전화_번호
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPRSNT_TEL_NO').val()))){
		alert("대표 전화 번호에 값을 입력하세요.");
		return;
	}
	if(COMMON_UTIL.validatePhone(COMMON_LANG.trimdata( vform.find('#RPRSNT_TEL_NO').val()))==false){
		alert("대표 전화 번호 값이 잘못 입력되었습니다. 올바른 값을 입력하십시오.");
		return;
	}
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#RPRSNT_TEL_NO').val()).length>15){
		alert("대표 전화 번호 값은 최대 15자까지 입력할 수 있습니다.");
		return;
	}	

	//사업자_번호
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#BIZ_NO').val()))){
		alert("사업자 번호에 값을 입력하세요.");
		return;
	}
	/*
	if(COMMON_UTIL.checkBizID(COMMON_LANG.trimdata( vform.find('#BIZ_NO').val()))==false){
		alert("사업자_번호 값은 최대 12자까지 입력할 수 있습니다.");
		return;
	}
	*/
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#BIZ_NO').val()).length>12){
		alert("사업자 번호 값은 최대 12자까지 입력할 수 있습니다.");
		return;
	}	
	//담당자_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CHARGER_NM').val()))){
		alert("담당자 명에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CHARGER_NM').val()).length>200){
		alert("담당자 명 값은 최대 200자까지 입력할 수 있습니다.");
		return;
	}	

	//담당자_전화_번호
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CHARGER_TEL_NO').val()))){
		alert("담당자 전화 번호에 값을 입력하세요.");
		return;
	}
	
	if(COMMON_UTIL.validatePhone(COMMON_LANG.trimdata( vform.find('#CHARGER_TEL_NO').val()))==false){
		alert("담당자 전화 번호 값이 잘못 입력되었습니다. 올바른 값을 입력하십시오.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CHARGER_TEL_NO').val()).length>15){
		alert("담당자 전화 번호 값은 최대 15자까지 입력할 수 있습니다.");
		return;
	}	

	//업체_상태_코드
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CO_STTUS_CODE').val()))){
		alert("업체 상태 코드에 값을 입력하세요.");
		return;
	}


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CO_STTUS_CODE').val()).length>8){
		alert("업체 상태 코드 값은 최대 8자까지 입력할 수 있습니다.");
		return;
	}	
	//등록_업종
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#REGIST_INDUTY').val()))){
		alert("등록 업종에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#REGIST_INDUTY').val()).length>100){
		alert("등록 업종 값은 최대 100자까지 입력할 수 있습니다.");
		return;
	}	
	//주력_공사
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#MFRC_CNTRWK').val()))){
		alert("주력 공사에 값을 입력하세요.");
		return;
	}
	
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#MFRC_CNTRWK').val()).length>100){
		alert("주력 공사 값은 최대 100자까지 입력할 수 있습니다.");
		return;
	}	

	//주요_공사_지역
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#MAJOR_CNTRWK_AREA').val()))){
		alert("주요 공사 지역에 값을 입력하세요.");
		return;
	}


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#MAJOR_CNTRWK_AREA').val()).length>100){
		alert("주요 공사 지역 값은 최대 100자까지 입력할 수 있습니다.");
		return;
	}	

	//사용_여부
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#USE_AT').val()))){
		alert("사용 여부에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#USE_AT').val()).length>1){
		alert("사용 여부 값은 최대 1자까지 입력할 수 있습니다.");
		return;
	}	
 
	if( confirm('<spring:message code="warn.insert.msg" />') ) {
		
		var action = '<c:url value="/api/company/addCompany.do" />';
		$.ajax({
	        url: action,
	        contentType: 'application/json',
	        data: JSON.stringify( vform.cmSerializeObject())  ,
	        dataType: "json",
	        cache: false,
	        type: 'POST',
	        processData: false,
	        success: function (jdata) {
	            if (jdata != null) {
	            	alert("공사업체정보를 등록하였습니다.");
	                return;
	            }
	        },
	
	        error: function () {
	            alert("공사업체정보를 등록 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}
 





	//-->
	</script>	
</body>
</html>


