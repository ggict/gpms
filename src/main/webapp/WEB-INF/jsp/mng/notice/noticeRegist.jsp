<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body class="cu">

<form id="frm" name="frm" method="post" action="" style="height:80%;">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->
<%-- <input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${flawCntrwkVO.CNTRWK_ID}"/> --%>

<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="ml10 mr10">
	        <table style="height: 440px;" class="tbview" summary="공지사항 정보를 등록한다.">
	            <caption>공지사항 정보 등록</caption>
	            <colgroup>
	                <col width="15%" />
	                <col width="35%" />
	                <col width="15%" />
	                <col width="35%" />
	            </colgroup>
	            <tbody>
					<tr>
						<th scope="row">등록자</th>
						<td>
							<input type="text" id="WRTER" name="WRTER" value="${noticeVO.WRTER}" readonly=true; style="width: 95%;"  />
						</td>
						<th scope="row">등록일</th>
						<td>
							<input type="text" id="REGIST_DT" name="REGIST_DT" value="${noticeVO.REGIST_DT}" readonly=true; style="width: 95%;"  />
						</td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3">
							<input type="text" id="SJ" name="SJ" class="MX_100 CS_50 input notnull" style="width: 98%;" />
						</td>
					</tr>
					<tr style="height: 263px;">
						<th scope="row">내용</th>
						<td colspan="3">
							<textarea id="CN" name="CN" maxLength="1000"  style="resize:none; width:96.5%; height: 95%;" ></textarea>
						</td>
					</tr>
					<tr> 
			     	  	<th scope="row" class="tx_center">첨부파일</th>
			     	  	<td colspan="3">
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="FILE_EDIT" />
								<c:param name="FILE_COLUMN" value="file_notice" />
								<%-- <c:param name="FILE_NO" value="${flawCntrwkVO.OPERT_BFE_PHOTO_NO}" /> --%>
								<c:param name="FILE_NO" value="${noticeVO.POS}" />
								<c:param name="FILE_PATH" value="NOTICE" />
								<c:param name="FILE_TYPE" value="ETC" />
							</c:import>
			     	  	</td>
					</tr>
					<tr>
						<th scope="row">조회</th>
						<td colspan="3">
							<input type="text" id="RDCNT" name="RDCNT" value="0" readonly=true; style="width: 98%;" />
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="mt10 tc">
				<div class="fr">
					<a href="#" onclick="javascript: fn_check_from();" class="schbtn" >등록</a>
					<a href="#" class="schbtn" onclick="javascript: fn_close_dialog();">닫기</a>
				</div>
			</div>
		</div>
	
	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->
</form>

<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
    
});

//글 등록
function fn_save() {
    if( confirm('<spring:message code="warn.insert.msg" />') ) {
        COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", contextPath + "api/notice/addNotice.do", "fn_file_upload_callback");
    }
    
    /* var action = '<c:url value="/api/notice/addNotice.do" />';
    
    $.ajax({
        url: action,
        contentType: 'application/json',
        data: JSON.stringify( $("#frm").cmSerializeObject())  ,
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (jdata) {
            if (jdata != null) {
                alert("공지사항을 등록하였습니다.");
                window.parent.fnSearch();
                return;
            }
        },

        error: function () {
            alert("공지사항 등록 시 오류가 발생하였습니다.");
            return;
        }
    }); */
}
function fn_check_from(){
	if($('#SJ').val() == "" || $('#SJ').val() == null){
		alert("제목을 입력하세요.");
		$('#SJ').focus();
		return;
	}
	if($('#CN').val() == "" || $('#CN').val() == null){
		alert("내용을 입력하세요.");
		$('#CN').focus();
		return;
	}
	fn_save();
}
//파일 전송 callback
function fn_file_upload_callback(){
    
    window.parent.fnSearch();
    var wnd_id = $("#wnd_id").val();
    COMMON_UTIL.cmWindowClose(wnd_id);
    return;
}

/* function fn_cnResize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
} */

function fn_close_dialog(){
    if( confirm('<spring:message code="warn.cancel.msg" />') ) {
    var wnd_id = $("#wnd_id").val();
    COMMON_UTIL.cmWindowClose(wnd_id);
    }
}
</script>
</body>
</html>