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
				<!-- <p>상세코드 정보</p> -->
				<div class="content" id="code_Regist-div" style="padding: 0px 10px;">
					<form:form commandName="codeVO" id="code_Regist-form">
						<table class="tbview" summary="상세코드 정보를 등록/수정합니다.">
							<caption>상세코드 정보</caption>
							<colgroup>
								<col width="30%" />
								<col width="70%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span class="fcred">* </span> 코드 값</th>
									<td><form:input path="CODE_VAL" maxLength="8"
											cssClass="iptxt" readonly="false" /></td>
								</tr>
								<tr>
									<th scope="row">분류 코드</th>
									<td><form:input path="CL_CODE" maxLength="4"
											cssClass="iptxt" readonly="true" /> <form:errors
											path="CL_CODE" /></td>
								</tr>
								<tr>
									<th scope="row">우선 순위</th>
									<td><form:input type="number" id="PRIOR_RANK"
											path="PRIOR_RANK" min="1" max="9999" class="iptxt" value="1" />
										<form:errors path="PRIOR_RANK" /></td>
								</tr>
								<tr>
									<th scope="row">코드 명</th>
									<td><form:input path="CODE_NM" maxLength="200"
											cssClass="iptxt" /> <form:errors path="CODE_NM" /></td>
								</tr>
								<tr>
									<th scope="row">속성 값</th>
									<td><form:input path="ATRB_VAL" maxLength="500"
											cssClass="iptxt" /> <form:errors path="ATRB_VAL" /></td>
								</tr>
								<tr>
									<th scope="row">내용</th>
									<td><form:textarea path="CN" maxLength="1000"
											style="resize:none; width:96%;" onkeyup="fn_cnResize(this)" />
										<form:errors path="CN" /></td>
								</tr>
								<tr>
									<th scope="row">사용 여부</th>
									<td><form:select path="USE_AT" style="width: 99%;">
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

			<div class="btnbx" style="margin-right: 15px;">
				<a href="#" class="schbtn"
					onclick="document.getElementById('code_Regist-form').reset(); return false;">초기화</a>
				<a href="#" class="schbtn" onclick="check = false; fn_code_save(); return false;">등록</a>
				<a href="#" class="schbtn" onclick="javascript:fn_close_dialog();">닫기</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" defer="defer">
<!--//	//-->
//<!--
function fn_close_dialog(){
	
	if( confirm('<spring:message code="warn.cancel.msg" />') ) {
	var wnd_id = $("#wnd_id").val();
	COMMON_UTIL.cmWindowClose(wnd_id);
    }
}


function fn_cnResize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
}

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
			fn_search_code();
		}
	});
	
});
 
/* 글 등록 function */
function fn_code_save() {	
	var vform = $('#code_Regist-form');

	/* TODO Validation기능 보완 */	
	//코드_값
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CODE_VAL').val()))){
		alert("코드 값에 값을 입력하세요.");
		return;
	}
	if(COMMON_LANG.trimdata( vform.find('#CODE_VAL').val()).length>8){
		alert("코드 값 값은 최대 8자까지 입력할 수 있습니다.");
		return;
	}
	
	//java.lang.String
	
	//분류_코드
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()))){
		alert("분류 코드에 값을 입력하세요.");
		return;
	}
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()).length>4){
		alert("분류 코드 값은 최대 4자까지 입력할 수 있습니다.");
		return;
	}	
	//우선_순위
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PRIOR_RANK').val()))){
		alert("우선 순위에 값을 입력하세요.");
		return;
	}

	//java.math.BigDecimal
	if(COMMON_LANG.trimdata( vform.find('#PRIOR_RANK').val()).length>22){
		alert("우선 순위 값은 최대 22자까지 입력할 수 있습니다.");
		return;
	}
	//코드_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CODE_NM').val()))){
		alert("코드 명에 값을 입력하세요.");
		return;
	}

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CODE_NM').val()).length>200){
		alert("코드 명 값은 최대 200자까지 입력할 수 있습니다.");
		return;
	}	
	
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#ATRB_VAL').val()).length>500){
		alert("속성 값 값은 최대 500자까지 입력할 수 있습니다.");
		return;
	}	//내용
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CN').val()))){
		alert("내용에 값을 입력하세요.");
		return;
	}
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CN').val()).length>1000){
		alert("내용 값은 최대 1000자까지 입력할 수 있습니다.");
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
		var action = '<c:url value="/api/code/addCode.do" />';
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
	            	alert("코드를 등록하였습니다.");
	            	var vform = $('#code_Regist-form');
	            	window.parent.fnSearchCode(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()));
	                return;
	            }
	        },
	
	        error: function () {
	            alert("코드를 등록 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}



//-->
</script>	
</body>
</html>


