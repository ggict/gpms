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
  * @Class Name : CodeUsemapRegister.jsp
  * @Description : CodeUsemap Update 화면
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
				<!-- <p>분류코드 정보</p> -->
				<div class="content" id="codeusemap_Regist-div" style="padding: 0px 10px;">
					<form:form commandName="codeUsemapVO" id="codeusemap_Regist-form">
						<table class="tbview" summary="코드사용맵 정보를 등록/수정합니다.">
							<caption>코드사용맵 정보를 등록/수정</caption>
							<colgroup>
								<col width="30%" />
								<col width="70%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span class="fcred">* </span>테이블 명</th>
									<td><form:select path="TABLE_NM" style="width: 99%;">
											<form:option value="" label="===== 전체 =====" />
											<form:options items="${tables}" itemValue="TABLE_NM"
												itemLabel="TABLE_COMMENTS" />
										</form:select></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>칼럼 명</th>
									<td><form:select path="COLMN_NM" style="width: 99%;">
											<form:option value="" label="===== 전체 =====" />
										</form:select></td>
								</tr>
								<tr>
									<th scope="row"><span class="fcred">* </span>분류 코드</th>
									<td><form:input path="CL_CODE" maxLength="50"
											cssClass="essentiality" readonly="true" /></td>
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
				<a href="#" class="schbtn" onclick="document.getElementById('codeusemap_Regist-form').reset(); return false;">초기화</a>
				<a href="#" class="schbtn" onclick="check = false; fn_codeusemap_save(); return false;">등록</a>
				<a href="#"	class="schbtn" onclick="javascript:fn_close_dialog();">닫기</a>
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
		if (this.id == "btn_search_codeusemap") {
			btn_search_codeusemap();
		}
	});
	
	/* 진행구분 선택 시 변환, 변환준비, 변환완료 인 경우에만 변환구분을 선택할 수 있게 설정 */
    $("#TABLE_NM").change(function (event) {
        var thisObj = $("#TABLE_NM");
        var vform = $('#codeusemap_Regist-form');

        if (thisObj.val() == "") {
            vform.find("#COLMN_NM").html("<option value=''>-- 선택하세요 --</option>");
            alert("테이블을 선택하십시오.");
            return;
        }


        var postDatas = {
            "TABLE_NM": thisObj.val()
        };

        $.ajax({
            url: "<c:url value='/api/codeusemap/selectColumnListRest.do' />",
            contentType: 'application/json',
            data: JSON.stringify(postDatas),
            dataType: "json",
            cache: false,
            type: 'POST',
            processData: false,
            success: function (jdata) {
                if (jdata != null) {
                    vform.find("#COLMN_NM").html("<option value=''>-- 선택하세요 --</option>");
                    for (key in jdata) {
                        vform.find("#COLMN_NM").append("<option value='" + jdata[key].COLMN_NM + "' label='" + jdata[key].COLUMN_COMMENTS + "' />");
                    }
                }
            },

            error: function () {
                alert("값을 가져올 수 없습니다.");
                return;
            }
        });
    });
	
});
 
/* 글 등록 function */
function fn_codeusemap_save() {	
	var vform = $('#codeusemap_Regist-form');
	
	/* TODO Validation기능 보완 */	
	//테이블_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#TABLE_NM').val()))){
		alert("테이블 명에 값을 입력하세요.");
		return;
	}
	if(COMMON_LANG.trimdata( vform.find('#TABLE_NM').val()).length>50){
		alert("테이블 명 값은 최대 50자까지 입력할 수 있습니다.");
		return;
	}
	
	//칼럼_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#COLMN_NM').val()))){
		alert("칼럼 명에 값을 입력하세요.");
		return;
	}
	if(COMMON_LANG.trimdata( vform.find('#COLMN_NM').val()).length>50){
		alert("칼럼 명 값은 최대 50자까지 입력할 수 있습니다.");
		return;
	}
	
	//분류_코드
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()))){
		alert("분류 코드에 값을 입력하세요.");
		return;
	}
	if(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()).length>4){
		alert("분류 코드 값은 최대 4자까지 입력할 수 있습니다.");
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
		var action = '<c:url value="/api/codeusemap/addCodeUsemap.do" />';
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
	            	alert("코드맵을 등록하였습니다.");
	            	
	            	var vform = $('#codeusemap_Regist-form');
	            	window.parent.fnSearchCodeMap(COMMON_LANG.trimdata( vform.find('#CL_CODE').val()));
	            	var wnd_id = $("#wnd_id").val();
	            	$('#'+wnd_id, window.parent.document).remove();
	                return;
	            }
	        },
	
	        error: function () {
	            alert("코드맵을 등록 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}


//-->
</script>	
</body>
</html>


