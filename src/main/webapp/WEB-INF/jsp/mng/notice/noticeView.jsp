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

<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	//조회수 증가
	fn_Rdcnt_Add();
});

//팝업창 닫기 
function fn_close_dialog(){
	COMMON_UTIL.cmWindowClose($("#wnd_id").val());
}

//조회수 증가
function fn_Rdcnt_Add(){
	var rd = parseInt($("#RDCNT").text());
	rd = rd+1;
	
	$.ajax({
		url: contextPath + 'api/notice/updateNoticeRdcnt.do'
		,data: JSON.stringify({"SEQ_NO" : $("#SEQ_NO").val(), "RDCNT" : rd}) 
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			
		}
		,error: function(a,b,msg){
			
		}
	});
}


</script>
</head>

<body>

<form id="frm" name="frm" method="post" action="" >
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
							<c:out value="${noticeVO.WRTER}"/>
						</td>
						<th scope="row">등록일</th>
						<td>
							<c:out value="${noticeVO.REGIST_DT}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3">
							<c:out value="${noticeVO.SJ}"/>
						</td>
					</tr>
					<tr style="height: 263px;">
						<th scope="row">내용</th>
						<td colspan="3" style="vertical-align: top; width: 515px; white-space: pre-line; -ms-word-break: break-all; word-break: break-all;"><c:out value="${noticeVO.CN}"/>
						</td>
					</tr>
					<tr> 
			     	  	<th scope="row" class="tx_center">첨부파일</th>			     	  	
			     	  	<td colspan="3" style="vertical-align:center;/* min-height:70px; */">
			     	  		<input type="hidden" name="POS" id="POS" value="${noticeVO.POS}"/>
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="VIEW" />
								<c:param name="FILE_COLUMN" value="file_notice" />
								<c:param name="FILE_NO" value="${noticeVO.POS}" />
								<c:param name="FILE_PATH" value="NOTICE" />
								<c:param name="FILE_TYPE" value="ETC" />
							</c:import>
			     	  	</td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td id ="RDCNT"  colspan="3">
							<c:out value="${noticeVO.RDCNT}"/>
						</td>
											
					</tr>
				</tbody>
			</table>
			
			<div class="mt10 tc">
				<div class="fr">
					<a href="#" style="margin-bottom: 20px;" onclick="fn_close_dialog();" class="schbtn">닫기</a>
				</div>
			</div>
		</div>
	
	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->
</form>

<%-- <%@ include file="/include/common.jsp" %> --%>
</body>
</html>