<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>
<%-- <script src="<c:url value='/js/common/cu_alert.js'/>"></script> --%>
</head>
<body>

<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<div>
<div class="tabcont">
	<!-- Content -->
	<div class="content">
	    <h3>노선 데이터로 검색
	    	<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('/routeinfo/selectRouteInfoList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기"/></a>
	    </h3>
	    <p class="location">
	        <span>노선검색</span>
	        <strong>노선정보 수정</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
	    	<div class="scroll" style="height:240px;">
		        <table class="tbview" summary="노선정보를 수정합니다.">
		            <caption>노선정보 수정</caption>
		            <colgroup>
		                <col width="15%" />
		                <col width="35%" />
		                <col width="15%" />
		                <col width="35%" />
		            </colgroup>
		            <tbody>
						<tr>
							<th scope="row">노선번호</th>
							<td>
								<label for="ROAD_NO_VAL"></label>
								<c:out value="${routeInfoVO.ROAD_NO_VAL}"/>
								<input type="hidden" id="ROAD_NO" name="ROAD_NO" value="<c:out value="${routeInfoVO.ROAD_NO}"/>"/>
							</td>
							<th scope="row"><span class="fcred"> *</span>노선명</th>
							<td>
								<label for="ROAD_NAME"></label>
								<input type="text" id="ROAD_NAME" name="ROAD_NAME" value="<c:out value="${routeInfoVO.ROAD_NAME}"/>"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="fcred"> *</span>시점명</th>
							<td>
								<label for="ST_POINT"></label>
								<input type="text" id="ST_POINT" name="ST_POINT" value="<c:out value="${routeInfoVO.ST_POINT}"/>"/>
							</td>
							<th scope="row"><span class="fcred"> *</span>종점명</th>
							<td>
								<label for="ED_POINT"></label>
								<input type="text" id="ED_POINT" name="ED_POINT" value="<c:out value="${routeInfoVO.ED_POINT}"/>"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="fcred"> *</span>총 연장(km)</th>
							<td>
								<label for="ROAD_TOT_LEN_JYG_Y"></label>
								<input type="number" class="MX_50 CS_25 input" id="ROAD_TOT_LEN_JYG_Y" name="ROAD_TOT_LEN_JYG_Y" value="<c:out value="${routeInfoVO.ROAD_TOT_LEN_JYG_Y}"/>"/>
							</td>
							<th scope="row"><span class="fcred"> *</span>도 관리구간(km)</th>
							<td>
								<label for="DO_MANAGE_SCTN_LEN"></label>
								<input type="number" class="MX_50 CS_25 input" id="DO_MANAGE_SCTN_LEN" name="DO_MANAGE_SCTN_LEN" value="<c:out value="${routeInfoVO.DO_MANAGE_SCTN_LEN}"/>"/>
							</td>
						</tr>
					</tbody>
		        </table>
	        </div>
	    </div>
	        <div class="mt10 tc">
	            <div class="fr mr10">
	            	<a href="#" onclick="fnSave();" class="schbtn" >수정</a>
	            </div>
	        </div>    
		</div>
	</div> 

	 
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">	
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
});	

function fnSave(){
	var msg = '<spring:message code="warn.update.msg" />';
	if(!validateCntrwk("frm")){
		return;
	}else{
		if( confirm(msg) ) {
			// 진행 프로그래스바 생성
			COMMON_UTIL.cmShowProgressBar();
			$('#action_flag').val('UPDATE');
			try {
				// multipart/form-data 아닌 경우, mask 처리 값을 제거하여 폼 데이터를 전송 처리함
				COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", "<c:url value='/routeinfo/updateRouteInfo.do'/>","fnSaveCallback");
			} catch(E) {
				alert("폼데이터 변환중 오류가 발생하였습니다. :" +E);
			}
		}
	}
}

function validateCntrwk(frmId){
	var vform = $('#'+frmId);

	//노선명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ROAD_NAME').val()))){
		alert("노선명을 입력하세요.");
		return false;
	}
	//시점명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ST_POINT').val()))){
		alert("시점명을 입력하세요.");
		return false;
	}
	//종점명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ED_POINT').val()))){
		alert("종점명을 입력하세요.");
		return false;
	}
	//총_연장
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ROAD_TOT_LEN_JYG_Y').val()))){
		alert("총 연장을 입력하세요.");
		return false;
	}
	//전산화_완료연장
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DO_MANAGE_SCTN_LEN').val()))){
		alert("도 관리구간을 입력하세요.");
		return false;
	}
	
	
	return true;
}

function fnSaveCallback() {
	// 노선 재검색
	COMMON_UTIL.cmMoveUrl( "/routeinfo/selectRouteInfoList.do");
}
</script>
</body>
</html>