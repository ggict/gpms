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
  * @author leehb1592@gmail.com
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
				<!-- <p>분류코드 정보</p> -->
				<div class="content" id="clcode_Regist-div" style="padding: 0px 10px;">
					<form:form commandName="clCodeVO" id="clcode_Regist-form">
						<table class="tbview" summary="분류코드 정보를 등록/수정합니다.">
							<caption>분류코드</caption>
							<colgroup>
								<col width="30%" />
								<col width="70%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span class="fcred"> *</span> 분류 코드</th>
									<td><form:input path="CL_CODE" maxLength="4"
											cssClass="iptxt" style="text-transform:uppercase"
											readonly="true" /></td>
								</tr>


								<tr>
									<th scope="row"><span class="fcred"> *</span> 우선 순위</th>
									<td><form:input type="number" id="PRIOR_RANK"
											path="PRIOR_RANK" min="1" max="9999" class="iptxt" /> <form:errors
											path="PRIOR_RANK" /></td>
								</tr>



								<tr>
									<th scope="row"><span class="fcred"> *</span> 분류 코드 명</th>
									<td><form:input path="CL_CODE_NM" maxLength="100"
											cssClass="iptxt" /> <form:errors
											path="CL_CODE_NM" /></td>
								</tr>

								<tr>
									<th scope="row"><span class="fcred"> *</span> 내용</th>
									<td><form:textarea path="CN" maxLength="1000"
											style="resize:none; width:96%;"
											onkeyup="fn_cnResize(this)" /> <form:errors path="CN" /></td>
								</tr>



								<tr>
									<th scope="row">사용 여부</th>
									<td><form:select path="USE_AT" style="width: 99%;">
											<form:option value="" label="===== 전체 =====" />
											<form:option value="Y" label="예" selected="selected" />
											<form:option value="N" label="아니오" />
										</form:select> <form:errors path="USE_AT" /></td>
								</tr>
								<tr>
									<th scope="row">수정자 ID</th>
									<td>${clCodeVO.UPDUSR_ID}</td>
								</tr>
								<tr>
									<th scope="row">수정 일시</th>
									<td><fmt:formatDate value="${clCodeVO.UPDT_DT}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>

						<div class="btnbx" style="margin-right: 15px;">
							<a href="#" class="schbtn"
								onclick="check = false; fn_clcode_save(); return false;">수정</a> <a href="#"
								class="schbtn" onclick="check = false; fn_clcode_delete(); return false;">삭제</a>
							<a href="#" class="schbtn"
								onclick="document.getElementById('clcode_Regist-form').reset(); return false;">초기화</a>
							<a href="#" class="schbtn"
								onclick="javascript:fn_close_dialog();">닫기</a>
						</div>
			</div>
		</div>
	</div>
	<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="clCodeVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script type="text/javascript" defer="defer">
<!--//	//-->
//<!--
function fn_close_dialog(){
	if( confirm('<spring:message code="warn.cancel.msg" />') ) {
        
        window.parent.fnSearch();
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
		if (this.id == "btn_search_clcode") {
			fn_search_clcode();
		}
	});
	//$(".datepicker").kodatepicker("<c:url value='/' />");
});
 

/* 글 등록 function */
function fn_clcode_save() {	
	var vform = $('#clcode_Regist-form');
	
	/* TODO Validation기능 보완 */	
	//분류_코드
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()))){
		alert("분류 코드에 값을 입력하세요.");
		return;
	}
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
	if(COMMON_LANG.trimdata( vform.find('#PRIOR_RANK').val()).length>4){
		alert("우선 순위 값은 최대 4자까지 입력할 수 있습니다.");
		return;
	}	



	//분류_코드_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CL_CODE_NM').val()))){
		alert("분류 코드 명에 값을 입력하세요.");
		return;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CL_CODE_NM').val()).length>100){
		alert("분류 코드 명 값은 최대 100자까지 입력할 수 있습니다.");
		return;
	}	



	//내용
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CN').val()))){
		alert("내용에 값을 입력하세요.");
		return;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CN').val()).length>1000){
		alert("내용 값은 최대1000자까지 입력할 수 있습니다.");
		return;
	}	



	//사용_여부
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#USE_AT').val()))){
		alert("사용 여부에 값을 입력하세요.");
		return;
	} 

	if( confirm('<spring:message code="warn.update.msg" />') ) {

		var action = '<c:url value="/api/clcode/updateClCode.do" />';
		
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
	            	alert("분류코드를 수정하였습니다.");
	            	window.parent.fnSearch();
	                return;
	            }
	        },
	
	        error: function () {
	            alert("분류코드를 수정 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

function fn_clcode_delete(){
	var vform = $('#clcode_Regist-form');
	if(confirm("선택한 코드("+ COMMON_LANG.trimdata( vform.find('#CL_CODE_NM').val()) +" )를 삭제하시겠습니까?")){
		var action = '<c:url value="/api/clcode/deleteClCode.do" />';
		
		 
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
	            	alert("분류코드를 삭제하였습니다.");
	            	
	            	fn_close_dialog();
	                return;
	            }
	        },

	        error: function () {
	            alert("분류코드를 삭제 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

//-->
</script>	
</body>
</html>


