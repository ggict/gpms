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
<script type="text/javaScript" language="javascript" defer="defer" charset="utf-8">

</script>
</head>

<body class="cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="UPPER_MENU_ID" name="UPPER_MENU_ID" value="<c:out value="${param.UPPER_MENU_ID}"/>"/>
<!-- 필수 파라메터(END) -->
<div id="Pop_wrap">
	<br/>
	<!-- Content -->
	<div class="ConBx">
		<div class="Pop_Section">
			<!-- <div class="PopTitBx"><p>시스템 하위메뉴 정보</p>	 -->
			<div class="content" id="menu_Regist-div" style="padding: 0px 10px;">
				<form:form commandName="menuVO" id="menu_Regist-form">
					<table class="tbview" summary="시스셈메뉴정보를 제공합니다.">
						<caption>시스셈메뉴</caption>
						<colgroup>
							 <col width="30%" />
		                	 <col width="70%" />
						</colgroup>
						<tbody>
		                 <tr>
							<th scope="row">시스템구분</th>
								<td>
								<form:input path="SYS_NM" maxLength="100" readonly="true" />
									<form:errors path="SYS_NM" />
								</td>
						 </tr>
						<tr>
							<th scope="row">상위메뉴ID</th>
							<td><form:input path="UPPER_MENU_ID" maxLength="100" readonly="true"/>
								<form:errors path="UPPER_MENU_ID" />
							</td>
						</tr>
							<th scope="row">메뉴ID</th>
							<td><form:input path="MENU_ID" maxLength="100" cssClass="iptxt" readonly="true"/>
								<form:errors path="MENU_ID" />
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="fcred">* </span>URL</th>
							<td><form:input path="URL" maxLength="100" cssClass="iptxt" style="resize:none;" />
								<form:errors path="URL" />
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="fcred">* </span>메뉴명</th>
							<td>
							<form:input path="MENU_NM" maxLength="100" cssClass="iptxt" style="resize:none; text-transform:lowercase" />
								<form:errors path="MENU_NM" />
							</td>
						</tr>
						<tr>
							<th scope="row">메뉴설명</th>
							<td>
							<form:textarea path="MENU_DC" maxLength="1000" style="resize:none; width:96%;" onkeyup="resize(this)"/>
								<form:errors path="MENU_DC" />
							</td>
						</tr>
						<tr>
							<th scope="row">사용여부</th>
							<td>
								<form:select path="USE_AT" style="width: 99.5%;">
								<form:option value="" label="===== 전체 =====" />
								<form:option value="Y" label="예" selected="selected" />
								<form:option value="N" label="아니오" />
								</form:select>
								<form:errors path="USE_AT" />
							</td>
						</tr>
						<tr>
							<th scope="row">수정자ID</th>
							<td>${menuVO.UPDUSR_ID}</td>
						</tr>
						<tr>
							<th scope="row">수정일시</th>
							<td><fmt:formatDate value="${menuVO.UPDT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					  </tbody>
					</table>
				</form:form>
			</div>
			</div>

			<div class="btnbx mr10">
				<a href="#"  class="schbtn"  onclick="$('#menu_Regist-form').reset(); return false;">초기화</a>
				<a href="#"  class="schbtn"  onclick="check = false; fn_menucode_save(); return false;">저장</a>
				<a href="#"  class="schbtn"  onclick="check = false; fn_menucode_delete(); return false;">삭제</a>
				<a href="#"  class="schbtn" onclick="javascript:fn_close_dialog();">닫기</a></div>
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
}
 */
$(document).ready(function(){
});


/* 글 등록 function */
function fn_menucode_save() {
	var vform = $('#menu_Regist-form');

	/* TODO Validation기능 보완 */
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#URL').val()))){
		alert("URL 값을 입력하세요.");
		return;
	}
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#MENU_NM').val()))){
		alert("메뉴명을 입력하세요.");
		return;
	}
	/* if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#MENU_DC').val()))){
		alert("메뉴설명을 입력하세요.");
		return;
	} */
	if( confirm('<spring:message code="warn.update.msg" />') ) {
		$.ajax({
	        //url: action,
	        url:'<c:url value="/"/>'+'api/menu/updateMenu.do',
	        contentType: 'application/json',
	        data: JSON.stringify( vform.cmSerializeObject())  ,
	        dataType: "json",
	        cache: false,
	        type: 'POST',
	        processData: false,
	        success: function (jdata) {
	            if (jdata != null) {
	            	alert("수정하였습니다.");
	            	COMMON_UTIL.cmWindowClose($("#wnd_id").val());
	                return;
	            }
	        },

	        error: function () {
	            alert("수정 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

function fn_menucode_delete(){
	var vform = $('#menu_Regist-form');
	if(confirm("선택한 코드("+ COMMON_LANG.trimdata( vform.find('#MENU_NM').val()) +" )를 삭제하시겠습니까?")){
		var action = '<c:url value="/api/menu/deleteMenu.do" />';

		vform.find('#DELETE_AT').val("Y");
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
	            	alert("삭제하였습니다.");

	            	fn_close_dialog();
	            	COMMON_UTIL.cmWindowClose($("#wnd_id").val());
	                return;
	            }
	        },

	        error: function () {
	            alert("삭제 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

//-->
</script>
</body>
</html>


