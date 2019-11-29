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
  * @Class Name : menuRegist.jsp
  * @Description : Menu Register 화면
  * @Modification Information
  * 
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
<script type="text/javaScript" language="javascript" defer="defer">

</script>
<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
</head>

<body class="cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) --> 
<div id="Pop_wrap">
	<br/>
	<!-- Content -->
	<div class="ConBx">
		<div class="Pop_Section">
			<div class="PopTitBx"><!-- <p>시스템메뉴 정보</p> -->
				<div id="menu_Regist-div" style="padding: 0px 10px;">
				<form:form commandName="menuVO" id="menu_Regist-form">
				<div class="table">
					<table>
						<caption class="hidden">시스템메뉴 정보</caption>
						<colgroup>
			                <col width="30%" />
			                <col width="70%" />
			            </colgroup>
			            <tbody>
							<tr style="display:none;">
								<th scope="row">단계</th>
								<td>
									<form:input path="MENU_DP" maxLength="50" cssClass="iptxt" readonly="true"/>
								</td>		
							</tr>
							<tr style="display:none;">
								<th scope="row">상위메뉴ID</th>
								<td>
									<form:input path="UPPER_MENU_ID" maxLength="50" cssClass="iptxt" readonly="true" />
								</td>		
							</tr>
							<tr>
								<th scope="row"><span class="fcred">* </span>시스템구분</th>
								<td>
									<form:select path="SYS_CODE">
										<form:option value="" label="전체" />
										<form:options items="${codesSYSM}" itemValue="CODE_VAL" itemLabel="CODE_NM" />
									</form:select>
								</td>		
							</tr>
							<tr>
								<th scope="row"><span class="fcred">* </span>메뉴명</th>
								<td>
									<form:input path="MENU_NM" maxLength="50" cssClass="iptxt"  readonly="false" />
								</td>
							</tr>
							<tr>
								<th scope="row"><span class="fcred">* </span>URL</th>
								<td>
									<form:input path="URL" maxLength="50" cssClass="iptxt"  style="text-transform:lowercase" readonly="false" />
								</td>			
							</tr>
							<tr>
								<th scope="row">메뉴설명</th>
								<td>
									<form:textarea path="MENU_DC" maxLength="1000" style="resize:none; width:96%;"/>
										<form:errors path="MENU_DC" />
								</td>			
							</tr>
						</tbody>
					</table>
					</div>
				</form:form>
			</div>
			</div>
			<div class="btnArea">
				<a href="#"  class="btn"  onclick="document.getElementById('menu_Regist-form').reset(); return false;">초기화</a>				
				<a href="#"  class="btn" onclick="javascript: fn_close_dialog();">닫기</a>
				<a href="#"  class="btn pri"  onclick="check = false; fn_menu_save(); return false;">등록</a>
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
        $('#'+wnd_id, window.parent.document).remove();
       
    }
}
/* function resize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
} */
$(document).ready(function(){
	// 상위메뉴 id가있을때는 시스템구분을 비활성화
	if( $("#UPPER_MENU_ID").val() == null ||  $("#UPPER_MENU_ID").val() == ""){
		$("#SYS_CODE").attr("disabled", false);
	} else {
		$("#SYS_CODE").attr("disabled", true);
	}
	
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
});
 

/* 글 등록 function */
function fn_menu_save() {	
	var vform = $('#menu_Regist-form');
	
	/* TODO Validation기능 보완 */	
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata(vform.find('#SYS_CODE').val()))){
		alert("시스템구분을 선택하세요.");
		return;
	}
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#URL').val()))){
		alert("url를 입력하세요.");
		return;
	}
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#MENU_NM').val()))){
		alert("메뉴명을 입력하세요.");
		return;
	}
	if(COMMON_LANG.trimdata( vform.find('#MENU_DC').val()).length>1000){
		alert("메뉴 설명을 1000자 이내에 입력하시기 바랍니다.");
		return;
	}
	
	var action = '<c:url value="/api/menu/addMenu.do"/>';
	
	if( confirm('<spring:message code="warn.insert.msg" />') ) {

		$.ajax({
	        url: action,
	        contentType: 'application/json',
	        data: JSON.stringify(vform.cmSerializeObject()),
	        dataType: "json",
	        cache: false,
	        type: 'POST',
	        processData: false,
	        success: function (jdata) {
	            if (jdata != null) {
	            	alert("시스템메뉴를 등록하였습니다.");
	                return;
	            }
	        },
	        error: function () {
	            alert("시스템메뉴를 등록 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

//-->
</script>	
</body>
</html>


