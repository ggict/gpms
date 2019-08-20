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
	
	// 달력 생성 : 하자검사일자, 보수일자
	COMMON_UTIL.cmCreateDatepickerLinked('FLAW_CHCK_DE', 'FLAW_CHCK_DE', 10);
	COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_DE', 'RPAIR_DE', 10);
	
	// 공사면적 계산
	$('#RPAIR_LEN').keyup(function () { fn_sum_flawCntrwk_ar(); });
	$('#RPAIR_BT').keyup(function () { fn_sum_flawCntrwk_ar(); });
	$('#FLAW_CO_NM').keyup(function () { $("#FLAW_CO_NO").val(''); });

	
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
	COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearch();
	var wnd_id = $("#wnd_id").val();
	$('#'+wnd_id, window.parent.document).remove();
	
}


// 세부공사 선택에 따른 노선명 등의 셀렉트 박스 내용 변경
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
				
				// 포장재료 자동세팅
				if( res[0].RPAIR_MTHD_CODE!=null )
					$("#RPAIR_MTHD_CODE").val( res[0].RPAIR_MTHD_CODE );
				// 포장공법 자동세팅
				if( res[0].PAV_MATRL_ASCON_CODE )
					$("#PAV_MATRL_ASCON_CODE").val( res[0].PAV_MATRL_ASCON_CODE );
				
				fn_change_routeCode();
			}

			if( selected_nscode!=null && selected_nscode!='' )
				$("#ROUTE_CODE").val( selected_nscode );
		}
		,error: function(a,b,msg){
			alert( "정보를 가져올 수 없습니다.("+msg+")" );
		}		
	});
}

function fn_change_routeCode() {
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
					$("#DIRECT_CODE").append("<option value='"+v.DIRECT_CODE+"'>"+v.DIRECT_NM+"</option>");
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
	$("#STRTPT").val('');
	$("#ENDPT").val('');
	
	$.ajax({
		url: contextPath + 'api/flawcntrwk/selectCntrwkDtl.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $('#CNTRWK_ID').val()
							 , "DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val()
							 , "ROUTE_CODE" : $('#ROUTE_CODE').val()
							 , "DIRECT_CODE" : $('#DIRECT_CODE').val()
							 , "TRACK" : $('#TRACK').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			if( res.length>0 ) {
				$.each(res, function(k,v) {
					$("#TRACK").append("<option value='"+v.TRACK+"'>"+v.TRACK+" 차로</option>");
				});
				$("#STRTPT").val(res[0].STRTPT);
				$("#ENDPT").val(res[0].ENDPT);
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

function fnChangeCompany(searchType){
	var co_no = $("#" + searchType + "_NO").val();
	$.ajax({
		url: contextPath + 'api/company/selectCompany.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify({CO_NO : co_no})
		,success: function(data){
			$('#' + searchType + '_NM').val(data.CO_NM);
			$('#' + searchType + '_RPRSNTV_NM').val(data.RPRSNTV_NM);
			$('#' + searchType + '_TELNO').val(data.RPRSNT_TEL_NO);
			$('#' + searchType + '_RPRSNT_TELNO').val(data.RPRSNT_TEL_NO);
		}
		,error: function(a,b,msg){
		}
	});
}

</script>
</head>

<body>

<form id="frm" name="frm" method="post" action="" style="height:80%;">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${flawCntrwkVO.CNTRWK_ID}"/>
<input type="hidden" id="FLAW_ID" name="FLAW_ID" value="${flawVO.FLAW_ID}"/>
<!-- 기타 구분 파라메터 -->
<input type="hidden" name="STRTPT" id="STRTPT" value=""/>
<input type="hidden" name="ENDPT" id="ENDPT" value=""/>

<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="ml10 mr10">
	        <table class="tbview" summary="포장 하자보수공사 정보를 등록한다.">
	            <caption>포장 하자보수공사 정보 등록</caption>
	            <colgroup>
	                <col width="15%" />
	                <col width="35%" />
	                <col width="15%" />
	                <col width="35%" />
	            </colgroup>
	            <tbody>
					<tr>
						<th scope="row">세부공사<span class="fcred"> *</span></th>
						<td colspan="3">
							<select name="DETAIL_CNTRWK_ID" id="DETAIL_CNTRWK_ID" alt="세부공사" class="select notnull" onchange="fn_change_cntrwkDtl();">
								<option value="">관련 세부공사 선택</option>
								<c:forEach var="cntrwkDtl" items="${cntrwkDtlList}">
								<option value="${cntrwkDtl.DETAIL_CNTRWK_ID}">${cntrwkDtl.DETAIL_CNTRWK_NM}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">공사구간</th>
						<td colspan="3">
							노선번호: <input type="text" id="ROAD_NO" value="" style="width:50px" readonly="readonly" class="MX_8 CS_8 input"/>
							노선명: <select name="ROUTE_CODE" id="ROUTE_CODE" style="width:120px" class="select" onchange="fn_change_routeCode();"></select>
							행선: <select name="DIRECT_CODE" id="DIRECT_CODE" style="width:70px" class="select" onchange="fn_change_track();"></select>
							차로: <select name="TRACK" id="TRACK" style="width:70px" class="select"></select>
						</td>
					</tr>
					<tr>
						<th scope="row">하자업체<span class="fcred"> *</span></th>
						<td>
							<input type="hidden" name="FLAW_CO_NM" id="FLAW_CO_NM" value="" />
							<select name="FLAW_CO_NO" id="FLAW_CO_NO" onchange="fnChangeCompany('FLAW_CO')" alt="하자업체" class="select notnull">
								<option value="">선택</option>
								<c:forEach var="company" items="${companyList}">
								<option value="${company.CO_NO}">${company.CO_NM}</option>
								</c:forEach>
							</select>
						</td>
						<th scope="row">하자검사일자<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="FLAW_CHCK_DE" id="FLAW_CHCK_DE" value="" alt="하자검사일자" class="DT_DATE input notnull" />
						</td>
					</tr>
					<tr>
						<th scope="row">하자원인<span class="fcred"> *</span></th>
						<td colspan="3">
							<input type="text" name="FLAW_CAUSE" id="FLAW_CAUSE" value="" alt="하자원인" class="MX_100 CS_50 input notnull" placeholder="예) 포장두께 부족으로 인한 균열, 배수불량"/>
						</td>
					</tr>
					<tr>
						<th scope="row">보수위치<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="RPAIR_LC" id="RPAIR_LC" value="" alt="보수위치" class="MX_100 CS_20 input notnull" />
						</td>
						<th scope="row">보수일자<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="RPAIR_DE" id="RPAIR_DE" value="" alt="보수일자" class="DT_DATE input notnull" />
						</td>
					</tr>
					<tr>
						<th scope="row">보수연장(m)<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="RPAIR_LEN" id="RPAIR_LEN" value="" alt="보수연장" class="MX_8 CS_8 DT_FLOAT input notnull" />
						</td>
						<th scope="row">보수폭(m)<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="RPAIR_BT" id="RPAIR_BT" value="" alt="보수폭" class="MX_5 DD_2 CS_5 DT_FLOAT input notnull" />
						</td>
					</tr>
					<tr>
						<th scope="row">보수면적(㎡)<span class="fcred"> *</span></th>
						<td>
							<input type="text" name="RPAIR_AR" id="RPAIR_AR" value="" alt="보수면적" class="MX_10 CS_10 DT_FLOAT input notnull" />
						</td>
						<th scope="row">포장공법<span class="fcred"> *</span></th>
						<td>
							<select name="RPAIR_MTHD_CODE" id="RPAIR_MTHD_CODE" alt="포장공법" class="select notnull" onchange="COMMON_UTIL.fn_show_etcBox('RPIR0000', 'RPAIR_MTHD_CODE', 'PAV_MSRC_ETC')">
								<option value="">선택</option>
								<c:forEach var="rpairMthd" items="${rpairMthdList}">
								<option value="${rpairMthd.RPAIR_MTHD_CODE}" >${rpairMthd.MSRC_CL_NM}</option>
								</c:forEach>
							</select>
							<input type="text" name="PAV_MSRC_ETC" id="PAV_MSRC_ETC" value="" class="MX_100 CS_20 input"  style="display: none;"/>
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="3">포장두께(cm)<span class="fcred"> *</span></th>
						<td>
							표층　 : <input type="text" name="RPAIR_THICK_ASCON" id="RPAIR_THICK_ASCON" style="width: 120px;" value="" alt="포장두께"  class="MX_5 DD_2 CS_5 DT_FLOAT input notnull" placeholder="예) 5.5"/>
						</td>
						<th scope="row" rowspan="3">포장재료</th>
						<td> 표층 <span class="fcred"> *</span>: 
							<select name="PAV_MATRL_ASCON_CODE" id="PAV_MATRL_ASCON_CODE" alt="포장재료" class="select notnull" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" >${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<input type="text" name="PAV_MATRL_ASCON_ETC" id="PAV_MATRL_ASCON_ETC" value="" class="MX_100 CS_20 input" style="display: none;" />
						</td>
					</tr>
					<tr>
						<td>
							중간층 : <input type="text" name="RPAIR_THICK_CNTR" id="RPAIR_THICK_CNTR" style="width: 120px;" value="" alt="포장두께" class="MX_5 DD_2 CS_5 DT_FLOAT input notnull" placeholder="예) 5.5"/>
						</td>
						<td> 중간층 : 
							<select name="PAV_MATRL_CNTR_CODE" id="PAV_MATRL_CNTR_CODE" class="select" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" >${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<input type="text" name="PAV_MATRL_CNTR_ETC" id="PAV_MATRL_CNTR_ETC" value="" class="MX_100 CS_20 input" style="display: none;"/>
						</td>
					</tr>
					<tr>
						<td>
							기층　 : <input type="text" name="RPAIR_THICK_BASE" id="RPAIR_THICK_BASE" style="width: 120px;" value="" alt="포장두께" class="MX_5 DD_2 CS_5 DT_FLOAT input notnull" placeholder="예) 5.5"/>
						</td>
						<td> 기층　 : 
							<select name="PAV_MATRL_BASE_CODE" id="PAV_MATRL_BASE_CODE" class="select" style="width: 120px;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC')">
								<option value="">선택</option>
								<c:forEach var="pavMatrl" items="${pavMatrlList}">
								<option value="${pavMatrl.PAV_MATRL_CODE}" >${pavMatrl.PAV_MATRL_NM}</option>
								</c:forEach>
							</select>
							<input type="text" name="PAV_MATRL_BASE_ETC" id="PAV_MATRL_BASE_ETC" value="" class="MX_100 CS_20 input"  style="display: none;"/>
						</td>
					</tr>
					<tr> 
			     	  	<th scope="row" colspan="2" class="tx_center">공사 전 사진</th>
			     	  	<th scope="row" colspan="2" class="tx_center">공사 후 사진</th>
		     	  	</tr>
		     	  	<tr> 
			     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="FILE_EDIT" />
								<c:param name="FILE_COLUMN" value="file_before" />
								<c:param name="FILE_NO" value="${flawCntrwkVO.OPERT_BFE_PHOTO_NO}" />
								<c:param name="FILE_PATH" value="HJBSM" />
								<c:param name="FILE_TYPE" value="IMAGE" />
							</c:import>
			     	  	</td>
			     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
			     	  		<c:import url="/attachfile/getfileForm.do" >
								<c:param name="FILE_MODE" value="FILE_EDIT" />
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
					<a href="#" onclick="fn_save();" class="schbtn" >등록</a>
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