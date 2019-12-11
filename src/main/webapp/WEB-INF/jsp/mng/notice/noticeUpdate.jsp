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

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	// 파일첨부 시 이미지 로드
	$(":file").change(function() {
	    // YYK. 파일첨부시 제한
	    if ( this.files[0].type != 'application/pdf'
	         && this.files[0].type != 'application/haansoftdoc'
	         && this.files[0].type != 'application/haansoftppt'
	         && this.files[0].type != 'application/haansoftpptx'
	         && this.files[0].type != 'application/haansoftxls'
	         && this.files[0].type != 'application/haansoftxlsx'
	         && this.files[0].type != 'application/haansofthwp'
	         && this.files[0].type != 'image/jpg'
	         && this.files[0].type != 'image/jpeg'
	         && this.files[0].type != 'image/bmp'
	         && this.files[0].type != 'image/png'
	         && this.files[0].type != 'image/gif'
	    ) {
	        alert('문서파일(pdf/doc/ppt/xls/hwp 타입) 및 \n이미지파일(jpg/jpeg/bmp/png/gif 타입)만 첨부가 가능합니다.');
// 	        $("#" + this.id).replaceWith( $("#" + this.id).clone(true) );
	        $("#" + this.id).val("");
	        return
	    }
// 	    readURL(this);
	});

});





//글 수정
function fn_update() {

	if( confirm('<spring:message code="warn.update.msg" />') ) {
		// 진행 프로그래스바 생성
		//cmShowProgressBar();

		fn_set_del_file("file_notice", "POS");

		COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", contextPath + "api/notice/updateNotice.do", "fn_save_callback");

	}
}

// 저장 callback
function fn_save_callback(){
	window.parent.fnSearch();
	COMMON_UTIL.cmWindowClose($("#wnd_id").val());
}

// 파일 삭제 처리
function fn_set_del_file(colNm, inputId){

	if($("#DEL_"+colNm).is(":checked")){
		$("#"+inputId).val("");
	}
}


//공지사항 삭제
function fn_delete(){

	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		$.ajax({
			url: contextPath + 'api/notice/deleteNotice.do'
			,data: JSON.stringify({"SEQ_NO" : $("#SEQ_NO").val()})
			,type: 'post'
			,dataType: 'json'
			,contentType : 'application/json'
			,success: function(res){
				if (res != null) {
					alert("삭제되었습니다.");
					window.parent.fnSearch();
					COMMON_UTIL.cmWindowClose($("#wnd_id").val());
					return;
				}
			}
			,error: function(a,b,msg){

			}
		});
	}
}

//팝업창 닫기
function fn_close_dialog(){
    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {
	window.parent.fnSearch();
	COMMON_UTIL.cmWindowClose($("#wnd_id").val());
    }
}

/* function fn_cnResize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
}
 */


</script>
</head>

<body class="cu">

<form id="frm" name="frm" method="post" action="" style="height:80%;">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->
<input type="hidden" id="SEQ_NO" name="SEQ_NO" value="${noticeVO.SEQ_NO}"/>
<!-- 기타 구분 파라메터 -->


<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="ml10 mr10">
			<table style="height: 440px;" class="tbview" summary="공지사항 정보를 수정한다.">
	            <caption>공지사항 정보 수정</caption>
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
							<input type="text" name="WRTER" id="WRTER" value="${noticeVO.WRTER }" readonly="true" style="width: 95%;" />
						</td>
						<th scope="row">등록일</th>
						<td>
							<input type="text" name="REGIST_DT" id="REGIST_DT" value="${noticeVO.REGIST_DT }" readonly="true" style="width: 95%;" />
						</td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3">
							<input type="text" name="SJ" id="SJ" value="${noticeVO.SJ }" style="width: 98%;"/>
						</td>
					</tr>
					<tr style="min-height: 263px;">
						<th scope="row">내용</th>
						<td colspan="3">
							<%-- <input type="text" name="CN" id="CN" value="${noticeVO.CN }" /> --%>
							<textarea id="CN" name="CN" maxLength="1000" style="resize:none; width:96.5%; height: 95%;">${noticeVO.CN }</textarea>
						</td>
					</tr>
					<tr>
			     	  	<th scope="row" class="tx_center">첨부파일</th>
			     	  	<td colspan="3" style="line-height: 20px;">
			     	  		<input type="hidden" name="POS" id="POS" value="${noticeVO.POS}"/>
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="FILE_EDIT" />
								<c:param name="FILE_COLUMN" value="file_notice" />
								<c:param name="FILE_NO" value="${noticeVO.POS}" />
								<c:param name="FILE_PATH" value="NOTICE" />
								<c:param name="FILE_TYPE" value="NOTICE_FILE" />
							</c:import>
			     	  	</td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td colspan="3">
							<input type="text" name="RDCNT" id="RDCNT" value="${noticeVO.RDCNT}" readonly="true"/>
						</td>

					</tr>
				</tbody>
			</table>

			<div class="mt10 tc">
				<div class="fr">
					<a href="#" onclick="$('#frm').reset(); return false;" class="schbtn edit">초기화</a>
					<a href="#" onclick="check = false; fn_update(); return false;" class="schbtn edit">수정</a>
					<a href="#" onclick="check = false; fn_delete(); return false;" class="schbtn edit">삭제</a>
					<a href="#" onclick="fn_close_dialog();" class="schbtn">닫기</a>
				</div>
			</div>
		</div>

	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->
</form>

<%@ include file="/include/common.jsp" %>
</body>
</html>