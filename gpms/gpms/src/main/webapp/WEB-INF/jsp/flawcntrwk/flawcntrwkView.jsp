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
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	$("select[name=DETAIL_CNTRWK_ID]").attr("disabled",true);
	$("select[name=ROUTE_CODE]").attr("disabled",true);
	$("select[name=DIRECT_CODE]").attr("disabled",true);
	$("select[name=TRACK]").attr("disabled",true);
	$("select[name=RPAIR_MTHD_CODE]").attr("disabled",true);
	$("select[name=PAV_MATRL_ASCON_CODE]").attr("disabled",true);
	$("select[name=PAV_MATRL_CNTR_CODE]").attr("disabled",true);
	$("select[name=PAV_MATRL_BASE_CODE]").attr("disabled",true);
	
	fn_change_cntrwkDtl('<c:out value="${flawCntrwkVO.ROUTE_CODE}"/>','<c:out value="${flawCntrwkVO.DIRECT_CODE}"/>','<c:out value="${flawCntrwkVO.TRACK}"/>');
	
	COMMON_UTIL.fn_show_etcBox('RPIR0000', 'RPAIR_MTHD_CODE', 'PAV_MSRC_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC');
});

//공사면적 자동 계산 처리
function fn_sum_flawCntrwk_ar() {
	try {
		var tot = COMMON_UTIL.cmFormatFloat( $('#RPAIR_LEN').val() ) * COMMON_UTIL.cmFormatFloat( $('#RPAIR_BT').val() );
		if( isNaN(tot) )	tot = 0;
		$('#RPAIR_AR').val( tot ).trigger('keyup');
	} catch(E) {alert( E );}
}

//글 등록
function fn_save() {
	
	//null check
	if(COMMON_UTIL.fn_check_notnull("tr")) {
		return;	
	}

	if( confirm('<spring:message code="warn.insert.msg" />') ) {
		// 진행 프로그래스바 생성
		//cmShowProgressBar();

		COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", contextPath + "api/flawcntrwk/addFlawCntrwk.do", "fn_file_upload_callback");
		
	}

}

// 파일 전송 callback
function fn_file_upload_callback(){
	var wnd_id = $("#wnd_id").val();
	$('#'+wnd_id, window.parent.document).remove();
	
}

function fn_change_routeCode() {
	$("#DIRECT_CODE option").remove();
	$("#TRACK option").remove();
	
	$.ajax({
		url: contextPath + 'api/flawcntrwk/selectCntrwkDtl.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $('#CNTRWK_ID').val()
							 , "DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val()
							 , "ROUTE_CODE" : $('#ROUTE_CODE').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			if( res.length>0 ) {
				$.each(res, function(k,v) {
					$("#DIRECT_CODE").append("<option value='"+v.DIRECT_CODE+"'>" + v.DIRECT_NM + "</option>");
				});
				
				fn_change_track();
			}
			else {
				$("#DIRECT_CODE").append("<option value=''></option>");
			}
		}
		,error: function(a,b,msg){
			alert( "정보를 가져올 수 없습니다.("+msg+")" );
		}		
	});
}

function fn_change_track() {
	$("#TRACK option").remove();
	
	$.ajax({
		url: contextPath + 'api/flawcntrwk/selectCntrwkDtl.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $('#CNTRWK_ID').val()
							 , "DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val()
							 , "ROUTE_CODE" : $('#ROUTE_CODE').val()
							 , "TRACK" : $('#TRACK').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			if( res.length>0 ) {
				$.each(res, function(k,v) {
					$("#TRACK").append("<option value='"+v.TRACK+"'>"+v.TRACK+" 차로</option>");
				});
			}
			else {
				$("#TRACK").append("<option value=''></option>");
			}
		}
		,error: function(a,b,msg){
			alert( "정보를 가져올 수 없습니다.("+msg+")" );
		}		
	});
}

/* 
//공사업체 조회 팝업
function fnSearchCompany(searchType){
	COMMON_UTIL.cmWindowOpen('공사업체 검색', "<c:url value='/company/searchCompany.do'/>?searchType="+searchType, 850, 600, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
}

//공사업체 조회 Callback
function fnSearchCompanyCallback(searchType, company){
	$('#' + searchType +'_NO').val(company['CO_NO']);
	$('#' + searchType +'_NM').val(company['CO_NM']);
	$('#' + searchType +'_RPRSNTV_NM').val(company['RPRSNTV_NM']);
	$('#' + searchType +'_TELNO').val(company['RPRSNT_TEL_NO']);

}

//공사업체 정보 초기화
function fnClearCompany(searchType){	
	$('#' + searchType +'_NO').val('');
	$('#' + searchType +'_NM').val('');
	$('#' + searchType +'_RPRSNTV_NM').val('');
	$('#' + searchType +'_TELNO').val('');

}
 */
//세부공사목록 엑셀로 출력
function fn_cntrwkExcel(){
	window.open(encodeURI('<c:url value="/flawcntrwk/downloadexcel.do"/>'));
}

function fn_update(){
	
	COMMON_UTIL.cmMovePage( "frm", "<c:url value='/flawcntrwk/updateFlawCntrwkView.do'/>");
}

function fn_delete(){
	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		$.ajax({
			url: contextPath + 'api/flawcntrwk/deleteFlawCntrwk.do'
			,type: 'post'
			,contentType: 'application/json'
			,data: JSON.stringify( {FLAW_CNTRWK_ID : $("#FLAW_CNTRWK_ID").val()}) 
			,dataType: 'json'
			,success: function(res){				
				if (res != null) {
	            	alert(res.resultMSG);
	            	COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearch();
	            	var wnd_id = $("#wnd_id").val();
	            	$('#'+wnd_id, window.parent.document).remove();
	            }
			}
			,error: function(a,b,msg){
				
			}
		});
	}
}

//세부공사 선택에 따른 노선명 등의 셀렉트 박스 내용 변경
function fn_change_cntrwkDtl(selected_nscode,selected_banghyang,selected_charo) {
	$("#ROUTE_CODE option").remove();
	$("#DIRECT_CODE option").remove();
	$("#TRACK option").remove();
	
	$.ajax({
		url: contextPath + 'api/flawcntrwk/selectCntrwkDtl.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $('#CNTRWK_ID').val(), "DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			var bf_val = "";

			if( res.length>0 ) {
				$.each(res, function(k,v) {
					$("#ROUTE_CODE").append("<option value='"+v.ROUTE_CODE+"'>"+v.ROAD_NM+"</option>");
				});
				
				fn_change_routeCode(selected_nscode,selected_banghyang,selected_charo);
				
				if( selected_nscode!=null && selected_nscode!='' )
					$("#ROUTE_CODE").val( selected_nscode );
			}
			
		}
		,error: function(a,b,msg){
			alert( "정보를 가져올 수 없습니다.("+msg+")" );
		}		
	});
}

function fn_change_routeCode(selected_nscode,selected_banghyang,selected_charo) {
	$("#DIRECT_CODE option").remove();
	$("#TRACK option").remove();
	
	$("#ROAD_NO").val(parseInt($("#ROUTE_CODE").val()));
	
	$.ajax({
		url: contextPath + 'api/flawcntrwk/selectCntrwkDtl.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $('#CNTRWK_ID').val()
							 , "DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val()
							 , "ROUTE_CODE" : $('#ROUTE_CODE').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			if( res.length>0 ) {
				$.each(res, function(k,v) {
					$("#DIRECT_CODE").append("<option value='"+v.DIRECT_CODE+"'>" + v.DIRECT_NM + "</option>");
				});
				
				fn_change_track(selected_nscode,selected_banghyang,selected_charo);
				
				if( selected_banghyang!=null && selected_banghyang!='' )
					$("#DIRECT_CODE").val( selected_banghyang );
			}
			else {
				$("#DIRECT_CODE").append("<option value=''></option>");
			}
		}
		,error: function(a,b,msg){
			alert( "정보를 가져올 수 없습니다.("+msg+")" );
		}		
	});
}

</script>
</head>

<body style="overflow: auto;">

<form id="frm" name="frm" method="post" action="" style="height:80%;">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${param.CNTRWK_ID}"/>
<input type="hidden" id="FLAW_CNTRWK_ID" name="FLAW_CNTRWK_ID" value="${param.FLAW_CNTRWK_ID}"/>
<!-- 기타 구분 파라메터 -->

<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="ml10 mr10">
			<table class="tbview" summary="포장 하자보수공사 정보를 조회한다.">
	            <caption>포장 하자보수공사 정보</caption>
	            <colgroup>
	                <col width="15%" />
	                <col width="35%" />
	                <col width="15%" />
	                <col width="35%" />
	            </colgroup>
	            <tbody>
					<tr>
						<th scope="row">세부공사</th>
						<td colspan="3">
							<select name="DETAIL_CNTRWK_ID" id="DETAIL_CNTRWK_ID" alt="세부공사" class="select notnull">
								<option value="">관련 세부공사 선택</option>
								<c:forEach var="cntrwkDtl" items="${cntrwkDtlList}">
								<option value="${cntrwkDtl.DETAIL_CNTRWK_ID}" <c:if test="${cntrwkDtl.DETAIL_CNTRWK_ID == flawCntrwkVO.DETAIL_CNTRWK_ID }">selected="selected"</c:if> >${cntrwkDtl.DETAIL_CNTRWK_NM}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">공사구간</th>
						<td colspan="3">
							노선번호: <input type="text" id="ROAD_NO" value="" style="width:50px" readonly="readonly" class="MX_8 CS_8 input"/>
							노선명: <select name="ROUTE_CODE" id="ROUTE_CODE" style="width:120px" class="select"></select>
							행선: <select name="DIRECT_CODE" id="DIRECT_CODE" style="width:70px" class="select"></select>
							차로: <select name="TRACK" id="TRACK" style="width:70px" class="select"></select>
						</td>
					</tr>
					<tr>
						<th scope="row">하자업체</th>
						<td>
							<c:out value="${flawCntrwkVO.FLAW_CO_NM}" />
						</td>
						<th scope="row">하자검사일자</th>
						<td>
							<fmt:parseDate var="FLAW_CHCK_DE" value="${flawCntrwkVO.FLAW_CHCK_DE}" pattern="yyyyMMdd" />
							<fmt:formatDate value="${FLAW_CHCK_DE}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<th scope="row">하자원인</th>
						<td colspan="3">
							<c:out value="${flawCntrwkVO.FLAW_CAUSE}" />
						</td>
					</tr>
					<tr>
						<th scope="row">보수위치</th>
						<td>
							<c:out value="${flawCntrwkVO.RPAIR_LC}" />
						</td>
						<th scope="row">보수일자</th>
						<td>
							<fmt:parseDate var="RPAIR_DE" value="${flawCntrwkVO.RPAIR_DE}" pattern="yyyyMMdd" />
							<fmt:formatDate value="${RPAIR_DE}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<th scope="row">보수연장(m)</th>
						<td>
							<fmt:formatNumber value="${flawCntrwkVO.RPAIR_LEN}" type="number"/>
						</td>
						<th scope="row">보수폭(m)</th>
						<td>
							<c:out value="${flawCntrwkVO.RPAIR_BT}" />
						</td>
					</tr>
					<tr>
						<th scope="row">보수면적(m²)</th>
						<td>
							<fmt:formatNumber value="${flawCntrwkVO.RPAIR_AR}" type="number"/>
						</td>
						<th scope="row">포장공법</th>
						<td>
							<select name="RPAIR_MTHD_CODE" id="RPAIR_MTHD_CODE" alt="포장공법" class="select notnull">
								<option value="">선택</option>
								<c:forEach var="rpairMthd" items="${rpairMthdList}">
								<option value="${rpairMthd.RPAIR_MTHD_CODE}" <c:if test="${rpairMthd.RPAIR_MTHD_CODE == flawCntrwkVO.RPAIR_MTHD_CODE }">selected="selected"</c:if>>${rpairMthd.MSRC_CL_NM}</option>
								</c:forEach>
							</select>
							<input type="text" name="PAV_MSRC_ETC" id="PAV_MSRC_ETC" value="${flawCntrwkVO.PAV_MSRC_ETC }" class="MX_100 CS_20 input"  style="display: none;"/>
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="3">포장두께(cm)</th>
						<td>
							표층　 : <c:out value="${flawCntrwkVO.RPAIR_THICK_ASCON}" />
						</td>
						<th scope="row" rowspan="3">포장재료</th>
						<td> 표층 : 
							<select name="PAV_MATRL_ASCON_CODE" id="PAV_MATRL_ASCON_CODE" alt="포장재료" class="select notnull" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" <c:if test="${pavMatrl.PAV_MATRL_CODE == flawCntrwkVO.PAV_MATRL_ASCON_CODE }">selected="seleted"</c:if>>${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<c:out value="${flawCntrwkVO.PAV_MATRL_ASCON_ETC}" />
						</td>
					</tr>
					<tr>
						<td>
							중간층 : <c:out value="${flawCntrwkVO.RPAIR_THICK_CNTR}" />
						</td>
						<td> 중간층 : 
							<select name="PAV_MATRL_CNTR_CODE" id="PAV_MATRL_CNTR_CODE" class="select" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" <c:if test="${pavMatrl.PAV_MATRL_CODE == flawCntrwkVO.PAV_MATRL_CNTR_CODE }">selected="seleted"</c:if>>${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<c:out value="${flawCntrwkVO.PAV_MATRL_CNTR_ETC}" />
						</td>
					</tr>
					<tr>
						<td>
							기층　 : <c:out value="${flawCntrwkVO.RPAIR_THICK_BASE}" />
						</td>
						<td> 기층　 : 
							<select name="PAV_MATRL_BASE_CODE" id="PAV_MATRL_BASE_CODE" class="select" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" <c:if test="${pavMatrl.PAV_MATRL_CODE == flawCntrwkVO.PAV_MATRL_BASE_CODE }">selected="seleted"</c:if>>${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<c:out value="${flawCntrwkVO.PAV_MATRL_BASE_ETC}" />
						</td>
					</tr>
					<tr> 
			     	  	<th scope="row" colspan="2" class="tx_center">공사 전 사진</th>
			     	  	<th scope="row" colspan="2" class="tx_center">공사 후 사진</th>
		     	  	</tr>
		     	  	<tr> 
			     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
			     	  		<input type="hidden" name="OPERT_BFE_PHOTO_NO" id="OPERT_BFE_PHOTO_NO" value="${flawCntrwkVO.OPERT_BFE_PHOTO_NO}"/>
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="VIEW" />
								<c:param name="FILE_COLUMN" value="file_before" />
								<c:param name="FILE_NO" value="${flawCntrwkVO.OPERT_BFE_PHOTO_NO}" />
								<c:param name="FILE_TYPE" value="IMAGE" />
							</c:import>
			     	  	</td>
			     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
			     	  		<input type="hidden" name="OPERT_AFT_PHOTO_NO" id="OPERT_AFT_PHOTO_NO" value="${flawCntrwkVO.OPERT_AFT_PHOTO_NO}"/>
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="VIEW" />
								<c:param name="FILE_COLUMN" value="file_after" />
								<c:param name="FILE_NO" value="${flawCntrwkVO.OPERT_AFT_PHOTO_NO}" />
								<c:param name="FILE_TYPE" value="IMAGE" />
							</c:import>
			     	  	</td>
					</tr>
				</tbody>
			</table>
			
			<div class="mt10 tc">
				<div class="fr">
					<c:if test="${sessionScope.userinfo.IS_ADMIN=='Y' || sessionScope.userinfo.DEPT_CODE==result.DEPT_CODE }">
						<a href="#" onclick="fn_update()" class="schbtn" >수정</a>
						<a href="#" onclick="fn_delete()" class="graybtn" >삭제</a>
					</c:if>
					<a href="#" onclick="COMMON_UTIL.cmWindowClose( $('#wnd_id').val() );" class="graybtn" >닫기</a>
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