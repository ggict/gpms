<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<title>사용자 권한 신청</title>
<%@ include file="/include/common_head.jsp" %>
<style type="text/css" media="print">
	@page a4sheet {size:21.0cm 29.7cm}
	.a4 {page: a4sheet; page-break-after: always;}
</style>
<style>
	.printTitle	{font-size:20px; color:black; font-family:'NanumGothic'; text-align:center; margin:50px 30px;
				 text-decoration: none; padding-bottom: 3px; border-bottom: 1px solid black;}
	.printCont p {font-size:16px; color:black; font-family:'NanumGothic'; text-align:left; margin-top:40px; margin-bottom:10px;}
	.printCont table	{font-size: 14px; color: black; font-family:'NanumGothic'; border: solid 1px #d9d9d9; padding: 30px; }
	.printCont tbody tr th {height:30px; border: solid 1px #d9d9d9;}
	.printCont tbody tr td {border: solid 1px #d9d9d9; padding-left: 20px;}
	.printCont span.msg {font-size: 15px; color: black; font-family:'NanumGothic'; padding:20px;}
	.btnAgree {
		background-color:#A6A6A6;
		-moz-border-radius:4px;
		-webkit-border-radius:4px;
		border-radius:4px;
		border:1px solid #949494;
		display:inline-block;
		font-family:'NanumGothic';
		font-size:15px;
		font-weight:normal;
		padding:1px 3px 2px 3px;
		text-decoration:none;
		height:20px;
		color:white;
		line-height:1.3em;
		cursor:pointer;
		margin-left:4px;
	}
</style>
<script type="text/javascript">

function fn_check_agree(type) {
	var wnd_id = $("#wnd_id").val();
	var opener_id = $("#opener_id").val();
	
	COMMON_UTIL.cmGetWindowOpener(opener_id).fn_set_agreAt(type);
	
	COMMON_UTIL.cmWindowClose(wnd_id);
	//$("#"+wnd_id, window.parent.document).remove();
}

</script>
</head>
<body class="bgnone"><!-- 팝업이 들어갈 경우에는 class="bgnone"를 body에 추가 해줘야 함 -->
<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->


<div style="padding: 20px;" class="a4">
	<div class="printTitle">
		<p>개인정보 수집·이용 동의서</p>
	</div>
	
	<div class="printCont">
		<p>◉ 개인 정보 수집 동의</p>
		<span class="msg">포장관리시스템에서는 사용자 확인, 권한관리 등 서비스 제공을 위해 아래와 같은 최소한의 개인정보를 수집하고 있습니다.</span>		
		
		<p>1. 수집하는 개인정보의 항목</p>
		<span class="msg">성명, 부서, 전화번호, 이메일 등</span>
		
		<p>2. 개인정보 수집 방법</p>
		<span class="msg">포장관리시스템은 다음과 같은 방법으로 개인정보를 수집합니다.
 - 사용자 등록 시 시스템을 통한 정보 수집</span>
		
		<p>3. 개인정보의 수집 및 이용 목적</p>
		<span class="msg"> 개인정보의 수집은 아래와 같은 목적을 위하여 수집하며
 이외의 목적으로는 사용되지 않습니다.
 - 권한처리 및 관리를 위한 정보 활용</span>
		
		<p>4. 개인정보의 보유 및 이용기간</p>
		<span class="msg">저장된 개인정보는 사용자 삭제 전까지 준영구 보전 합니다.</span>
		
		<p>◉ 개인 정보 제 3자 제공 안내</p>
		<span class="msg">포장관리시스템에서는 수집된 정보를 제3자에게 제공하지
  않습니다.</span>
		
		<p style="text-align: center; height: 50px;">
			*동의를 거부할 수 있으며, 동의 거부 시 제공되는 서비스가 일부 제한 될 수 있습니다.
		</p>
	</div>
	
	<!-- 동의/미동의 버튼 -->
	<div id="divAgree" style="border: none; float: right; margin: 10px; color:#004994; font-size: 14px;">
		<a href="#" onclick="fn_check_agree('Y'); return false;"><span class="btnAgree">동의</span></a>
		<a href="#" onclick="fn_check_agree('N'); return false;"><span class="btnAgree">동의하지 않음</span></a>
	</div>
</div>

</form>

<%@ include file="/include/common.jsp" %>
</body>
</html>