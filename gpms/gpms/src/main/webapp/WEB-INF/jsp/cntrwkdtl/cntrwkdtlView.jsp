<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	//도색물량합계
	//fn_paintSum();
	
	//scroll 문제로 인해 적용
	setTimeout(function(){
		 $("#gridArea").jqGrid({
				url: '<c:url value="/"/>'+'api/cntrwkcellinfo/selectPavYearListByCellId.do'
				,autoencode: true
				,contentType : 'application/json'
				,datatype: "local"
				,mtype: "POST"
				,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
				//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
				,postData: $("#cellFrm").cmSerializeObject()
				,ignoreCase: true
				,colNames:["포장셀_관리_ID","포장년도","위치보기"]
			   	,colModel:[
					{name:'PAV_CELL_ID',index:'PAV_CELL_ID', align:'center', width:120, sortable:false}
					,{name:'PAV_YEAR',index:'PAV_YEAR', align:'center', width:70, sortable:false}
					,{name:'btn_loc',index:'btn_loc', align:'center', width:70, sortable:false, formatter: fn_create_btn}
			   	]
				,async : false
			   	,sortname: ''
			    ,sortorder: ""
			   	,rowNum: 50
			   	,rowList: [20,50,100,500]
			    ,viewrecords: true
			   	,pager: '#gridPager'
			    ,rownumbers: true
			    ,loadtext: "검색 중입니다."
				,emptyrecords: "검색된 데이터가 없습니다."
				,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
				,ondblClickRow: function(rowId) {		// 더블클릭 처리
					fn_view(rowId);	// 대장 조회
				}
			   	,onSelectRow: function(rowId) {		// 클릭 처리
					if( rowId != null ) {
						var rowData =$( "#gridArea" ).getRowData(rowId);
					}
				}
			   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
			   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
			   			delete this.p.postData.nd;
			   			delete this.p.postData._search;
			   			this.p.postData.sidx = this.p.sortname;
			   			this.p.postData.sord = this.p.sortorder;
			   			if(this.p.postData.pageUnit != this.p.postData.rows){
			   				this.p.postData.pageUnit = this.p.postData.rows;
			   			}
			   			ajaxParam.data = JSON.stringify(this.p.postData);
			   		}
			   	}
				,multiselect: false
				,multiboxonly: false
				//,scroll: true
			}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
			
			COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);
			
			fn_search();	
	 }, 300);
	
	
});

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
		case "btn_loc" :
			btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.PAV_CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
			break;
	}
	
	return btn;
}

function fn_select_cell(cell_id){
	var tables = ["CELL_10"];
	var fields = ["CELL_ID"];
	var values = [cell_id];
	
	// 모든 팝업창 최소화
	parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	var attribute_base = {
	        attributes : {
	            fillColor : '#ffffff',
                strokeColor : '#ffffff'
			}
	};
	
	//MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
	MAP.fn_get_selectFeatureByAttrMulti(parent.gMap, tables, fields, values, null, "AND", attribute_base, true, 0, 1);
}


//검색 처리
function fn_search() {
	
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#cellFrm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

// 삭제 처리 [수정:가능]
function fnDelete() {
	$.ajax({
		url: contextPath + 'userauth/checkAuth.do'
		,type: 'post'
		,dataType: 'json'
		,data : {"url" : "/api/cntrwkdtl/deleteCntrwkDtl.do"}
		,success: function(res){				
			if(!res.result){
				alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
				return;
			}
			
			fnDeleteProc()
		}
		,error: function(a,b,msg){
			
		}
	});

}

//삭제 처리 [수정:가능]
function fnDeleteProc() {

	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		
		$.ajax({
			url: contextPath + 'api/cntrwkdtl/deleteCntrwkDtl.do'
			,type: 'post'
			,contentType: 'application/json'
			,data: JSON.stringify( {DETAIL_CNTRWK_ID : $("#DETAIL_CNTRWK_ID").val()
								  , CNTRWK_ID : $("#CNTRWK_ID").val()}) 
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

//도색물량합계
function fn_paintSum(){
	var paintSum=0;
	var arr =[$("#PAINT_YLWSLLN_AR").html()
	  		,$("#PAINT_YLWDASHLN_AR").html()
			,$("#PAINT_WHSLLN_AR").html()
			,$("#PAINT_CRSLK_AR").html()
			,$("#PAINT_STOPLN_AR").html()
			,$("#PAINT_SPDBMP_AR").html()
			,$("#PAINT_WHDASHLN_AR").html()
			,$("#PAINT_CHRCTRSYMBL_AR").html()];
	
	for(var i in arr){
		paintSum += Number(arr[i]);
	}
	
	document.getElementById("paintSum").innerHTML = paintSum;
}

function fnUpdate(){
	//COMMON_UTIL.cmMovePage('frm','<c:url value='/cntrwkdtl/updateCntrwkDtlView.do'/>?DETAIL_CNTRWK_ID='+$("#DETAIL_CNTRWK_ID").val());
	COMMON_UTIL.cmMovePage('frm',"<c:url value='/cntrwkdtl/updateCntrwkDtlView.do'/>");
}

//위치조회
function fnViewLocation(){
	var dtlCntrwkId = $("#DETAIL_CNTRWK_ID").val();
	
	$.ajax({
		url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
		,type: 'post'
		,data: JSON.stringify({"DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			var tables = ["CELL_10"];
			var fields = [];
			var values = [];
			
			if(res.length < 1){
				alert("위치 정보가 존재하지 않습니다.");
				return;
			}
			
			if(res.length > 1){
				for(var i in res){
					var data = res[i];
					fields.push("CELL_ID");
					values.push(data.PAV_CELL_ID);
				}
			}else{
				fields = "CELL_ID";
				values = res[0].PAV_CELL_ID;
			}
			
			// 모든 팝업창 최소화
			parent.wWindowHideAll();
			// 하단 목록 창 내리기
			parent.bottomClose();
			
			MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [fields], [values], null, "OR");
		}
		,error: function(a,b,msg){
		}		
	});
}
</script>
</head>

<body>

<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="mt10 ml10 mr10">
			<form id="frm" name="frm" method="post" action="">
				<!-- 필수 파라메터(START) -->
				<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
				<input type="hidden" id="opener_id" name="opener_id" value=""/>
				<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
				<!-- 필수 파라메터(END) -->
				<!-- KEY 파라메터 -->
				<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${cntrwkDtlVO.CNTRWK_ID}"/>
				<input type="hidden" id="DETAIL_CNTRWK_ID" name="DETAIL_CNTRWK_ID" value="${cntrwkDtlVO.DETAIL_CNTRWK_ID}"/>
				<%-- <input type="hidden" id="CELL_ID" name="CELL_ID" value="${param.CELL_ID }"/> --%>
				<div class="titbx">
					<h4>기본정보</h4>
			        <table class="tbview" summary="포장 세부공사 기본정보를 조회한다.">
			            <caption>포장 세부공사 기본정보</caption>
			            <colgroup>
			                <col width="20%" />
			                <col width="30%" />
			                <col width="20%" />
			                <col width="30%" />
			            </colgroup>
			            <tbody>
			            	<tr>
								<th scope="row">착공일 ~ 준공일</th>
								<td colspan="3">
									<fmt:parseDate var="RPAIR_BEGIN_DE" value="${cntrwkDtlVO.RPAIR_BEGIN_DE}" pattern="yyyyMMdd" />
									<fmt:formatDate value="${RPAIR_BEGIN_DE}" pattern="yyyy-MM-dd" />
									 ~ 
									<fmt:parseDate var="RPAIR_END_DE" value="${cntrwkDtlVO.RPAIR_END_DE}" pattern="yyyyMMdd" />
									<fmt:formatDate value="${RPAIR_END_DE}" pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>							
								<th scope="row">시공사</th>
								<td>
									<c:out value="${cntrwkDtlVO.CNSTRCT_CO_NM}"/>
								</td>
								<th scope="row">도급비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.OUTSRCCT}" type="number"/>
								</td>
								<%-- <th scope="row">감리사</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPRVISN_CO_NM}"/>
								</td> --%>
							</tr>
							<tr>
								<th scope="row">시공사 대표자</th>
								<td>
									<c:out value="${cntrwkDtlVO.CNSTRCT_CO_RPRSNTV_NM}"/>
								</td>
								<th scope="row">관급비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.GVSLCT}" type="number"/>
								</td>
								<%-- <th scope="row">감리사 대표자</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPRVISN_CO_RPRSNTV_NM}"/>
								</td> --%>
							</tr>
							<tr>							
								<th scope="row">시공사 대표번호</th>
								<td>
									<c:out value="${cntrwkDtlVO.CNSTRCT_CO_TELNO}"/>
								</td>
								<th scope="row">총 공사비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.CNTRWK_AMOUNT}" type="number"/>
								</td>
								<%-- <th scope="row">감리사 대표번호</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPRVISN_CO_RPRSNT_NO}"/>
								</td> --%>
							</tr>
							<%-- <tr>
								<th scope="row">현장소장 성명</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPT_HDCH_NM}"/>
								</td>
								<th scope="row">감리원 성명</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPRVISOR_NM}"/>
								</td>
							</tr>
							<tr>
								<th scope="row">현장소장 전화번호</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPT_HDCH_TELNO}"/>
								</td>							
								<th scope="row">감리원 전화번호</th>
								<td>
									<c:out value="${cntrwkDtlVO.SPRVISOR_TELNO}"/>
								</td>
							</tr>
							<tr>
								<th scope="row">도급비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.OUTSRCCT}" type="number"/>
								</td>
								<th scope="row">관급비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.GVSLCT}" type="number"/>
								</td>
							</tr>
							<tr>
								<th scope="row">기타 이설비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.ETC_RLOCATCT}" type="number"/>
								</td>
								<th scope="row">총 공사비(천원)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.CNTRWK_AMOUNT}" type="number"/>
								</td>
							</tr> --%>
						</tbody>
					</table>
				</div>
				<div class="titbx mt20">
					<h4>위치정보</h4>
			        <table class="tbview" summary="포장 세부공사 위치정보를 조회한다.">
			            <caption>포장 세부공사 위치정보</caption>
			            <colgroup>
			                <col width="20%" />
			                <col width="30%" />
			                <col width="20%" />
			                <col width="30%" />
			            </colgroup>
			            <tbody>
							<tr>
								<th scope="row">세부위치</th>
								<td colspan="3">
									<c:out value="${cntrwkDtlVO.DETAIL_CNTRWK_NM}"/>
								</td>
							</tr>
							<tr>
								<th scope="row" >(노선번호) 노선명</th>
								<td>
									(
									<fmt:parseNumber value="${cntrwkDtlVO.ROUTE_CODE}" type="number"/>
									)<c:out value="${cntrwkDtlVO.ROUTE_NM}"/>
								</td>
								<th scope="row">도로명</th>
								<td>
									<c:out value="${cntrwkDtlVO.ROAD_NM}"/>
								</td>
							</tr>
							<tr>
								<th scope="row">행선</th>
								<td>
									<c:out value="${cntrwkDtlVO.DIRECT_NM}"/>
								</td>
								<th scope="row">공사 차로 수</th>
								<td>
									<c:out value="${cntrwkDtlVO.TRACK}"/> 차로
								</td>
							</tr>
							<tr>
								<th scope="row">보수시점(m)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.STRTPT}" type="number"/>
								</td>
								<th scope="row">보수종점(m)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.ENDPT}" type="number"/>
								</td>
							</tr>
							<tr>
								<th scope="row">공사연장(m)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.TRACK_LEN}" type="number"/>
								</td>
								<th scope="row">공사폭(m)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.RPAIR_BT}" type="number"/>
								</td>
							</tr>							
							<tr>
								<th scope="row">공사면적(㎡)</th>
								<td>
									<fmt:formatNumber value="${cntrwkDtlVO.RPAIR_AR}" type="number"/>
								</td>
								<th scope="row">포장공법</th>
								<td>
									<c:out value="${cntrwkDtlVO.RPAIR_MTHD_NM}"/>
								</td>
							</tr>
							<tr>
								<th scope="row" rowspan="3">포장두께(cm)</th>
								<td>표층　 : 
									<fmt:formatNumber value="${cntrwkDtlVO.RPAIR_THICK_ASCON}" type="number"/> 
								</td>
								<th scope="row" rowspan="3">포장재료</th>
								<td>표층　 :
									<c:out value="${cntrwkDtlVO.PAV_MATRL_ASCON_NM}"/>
								</td>
							</tr>
							<tr>	
								<td>중간층 : 
									<fmt:formatNumber value="${cntrwkDtlVO.RPAIR_THICK_CNTR}" type="number"/>
								</td>
								<td>중간층 :
									<c:out value="${cntrwkDtlVO.PAV_MATRL_CNTR_NM}"/>
								</td>
							</tr>
							<tr>
								<td>기층　 :
									<fmt:formatNumber value="${cntrwkDtlVO.RPAIR_THICK_BASE}" type="number"/>
								</td>
								<td>기층　 :
									<c:out value="${cntrwkDtlVO.PAV_MATRL_BASE_NM}"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<%-- <div class="titbx mt20">
					<h4>도색물량 총괄</h4>
			        <table class="tbview" summary="포장 세부공사 도색물량 총괄을 조회한다.">
			            <caption>포장 세부공사 도색물량 총괄</caption>
			            <colgroup>
			                <col width="15%" />
			                <col width="35%" />
			                <col width="15%" />
			                <col width="35%" />
			            </colgroup>
			            <tbody>
			            	<tr>
								<th scope="row">황색실선(㎡)</th>
								<td id="PAINT_YLWSLLN_AR"><c:out value="${cntrwkDtlVO.PAINT_YLWSLLN_AR}" /></td>
								<th scope="row">백색실선(㎡)</th>
								<td id="PAINT_WHSLLN_AR"><c:out value="${cntrwkDtlVO.PAINT_WHSLLN_AR}" /></td>
							</tr>
							<tr>
								<th scope="row">황색파선(㎡)</th>
								<td id="PAINT_YLWDASHLN_AR"><c:out value="${cntrwkDtlVO.PAINT_YLWDASHLN_AR}" /></td>
								<th scope="row">백색파선(㎡)</th>
								<td id="PAINT_WHDASHLN_AR"><c:out value="${cntrwkDtlVO.PAINT_WHDASHLN_AR}" /></td>
							</tr>
							<tr>
								<th scope="row" rowspan="2">횡단보도</th>
								<td>개소　 :
									<c:out value="${cntrwkDtlVO.PAINT_CRSLK_CO}" />
								</td>
								<th scope="row" rowspan="2">방지턱</th>
								<td>개소　 :
									<c:out value="${cntrwkDtlVO.PAINT_SPDBMP_CO}" />
								</td>
							</tr>
							<tr>
								<td id="PAINT_CRSLK_AR">㎡　 :
									<c:out value="${cntrwkDtlVO.PAINT_CRSLK_AR}" />
								</td>
								<td id="PAINT_SPDBMP_AR">㎡　 :
									<c:out value="${cntrwkDtlVO.PAINT_SPDBMP_AR}" />
								</td>
							</tr>
							<tr>
								<th scope="row" rowspan="2">정지선</th>
								<td>개소　 :
									<c:out value="${cntrwkDtlVO.PAINT_STOPLN_CO}" />
								</td>
								<th scope="row" rowspan="2">문자기호</th>
								<td>개소　 :
									<c:out value="${cntrwkDtlVO.PAINT_CHRCTRSYMBL_CO}" />
								</td>
							</tr>
							<tr>
								<td id="PAINT_STOPLN_AR">㎡　 :
									<c:out value="${cntrwkDtlVO.PAINT_STOPLN_AR}" />
								</td>
								<td id="PAINT_CHRCTRSYMBL_AR">㎡　 :
									<c:out value="${cntrwkDtlVO.PAINT_CHRCTRSYMBL_AR}" />
								</td>
							</tr>
							<tr>
								<th scope="row">차선도색공법</th>
								<td colspan="3"><c:out value="${cntrwkDtlVO.PAINT_MSRC}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="titbx mt20">
					<h4>공사사진</h4>
			        <table class="tbview" summary="포장 세부공사 공사사진을 조회한다.">
			            <caption>포장 세부공사 공사사진</caption>
			            <colgroup>
			                <col width="15%" />
			                <col width="35%" />
			                <col width="15%" />
			                <col width="35%" />
			            </colgroup>
			            <tbody>	
			            	<tr> 
					     	  	<th scope="row" colspan="2" class="tx_center">공사 전 사진</th>
					     	  	<th scope="row" colspan="2" class="tx_center">공사 후 사진</th>
				     	  	</tr>
				     	  	<tr> 
					     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
					     	  		<c:import url="/attachfile/getfileForm.do" >
										<c:param name="FILE_MODE" value="VIEW" />
										<c:param name="FILE_COLUMN" value="file_before" />
										<c:param name="FILE_NO" value="${cntrwkDtlVO.OPERT_BFE_PHOTO_NO}" />
										<c:param name="FILE_TYPE" value="IMAGE" />
									</c:import>
					     	  	</td>
					     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
					     	  		<c:import url="/attachfile/getfileForm.do" >
										<c:param name="FILE_MODE" value="VIEW" />
										<c:param name="FILE_COLUMN" value="file_after" />
										<c:param name="FILE_NO" value="${cntrwkDtlVO.OPERT_AFT_PHOTO_NO}" />
										<c:param name="FILE_TYPE" value="IMAGE" />
									</c:import>
					     	  	</td>
							</tr>
						</tbody>
					</table>
				</div> --%>
				<div class="titbx mt20">
					<h4>기타</h4>
			        <table class="tbview" summary="포장 세부공사 기타를 조회한다.">
			            <caption>포장 세부공사 기타</caption>
			            <colgroup>
			                <col width="20%" />
			                <col width="30%" />
			                <col width="20%" />
			                <col width="30%" />
			            </colgroup>
			            <tbody>		
							<%-- <tr>
								<th scope="row">공급플랜트</th>
								<td>
									<c:out value="${cntrwkDtlVO.RPAIR_MATRL_PRDCT_CO_NM}"/>
								</td>
								<th scope="row">연락처</th>
								<td>
									<c:out value="${cntrwkDtlVO.PRDCT_CO_TELNO}"/>
								</td>
							</tr> --%>
							<tr>
								<th scope="row">공사시간</th>
								<td><c:out value="${cntrwkDtlVO.CNTRWK_TIME}" /></td>
								<th scope="row">비고</th>
								<td><c:out value="${cntrwkDtlVO.RM}"/></td>
								<%-- <th scope="row">날씨</th>
								<td><c:out value="${cntrwkDtlVO.CNTRWK_WETHR}" /></td> --%>
							</tr>
							<%-- <tr>
								<th scope="row">비고</th>
								<td colspan="3"><c:out value="${cntrwkDtlVO.RM}"/></td>
							</tr>
							<tr>
								<th scope="row" id="a1">기타 첨부파일</th>
								<td colspan="3">
									<c:import url="/attachfile/getfileForm.do" >
										<c:param name="FILE_MODE" value="VIEW" />
										<c:param name="FILE_COLUMN" value="file_no" />
										<c:param name="FILE_NO" value="${cntrwkDtlVO.FILE_NO}" />
										<c:param name="FILE_TYPE" value="ETC" />						
									</c:import>
								</td>
							</tr> --%>
						</tbody>
					</table>
				</div>
				<div class="titbx mt20">
					<h4>수정이력</h4>
					<table class="tbview">
						<colgroup>
							<col style="width:34%;" />
							<col style="width:33%;" />
						</colgroup>
						<tbody>
							<tr>							
								<th scope="row" class="center">최종수정자명</th>
								<th scope="row" class="center">최종수정일자</th>
							</tr>
							<tr>
								<td align="center"><c:out value="${cntrwkDtlVO.UPDUSR_ID}" /></td>
								<td align="center"><fmt:formatDate value="${cntrwkDtlVO.UPDT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<form id="cellFrm">
				<input type="hidden" name="DETAIL_CNTRWK_ID" value="${cntrwkDtlVO.DETAIL_CNTRWK_ID}"/>
				<div class="titbx mt20">
					<h4>포장셀 직전포장년도</h4>
					<div style="width: 100%;">
						<div id="div_grid" style="width:100%; height:240px;">
							<table id="gridArea"></table>
							<div id="gridPager"></div>
						</div>
					</div>
				</div>
			</form>		
			<div class="mt10 tc">
				<div class="fl" ><a href="#" onclick="fnViewLocation();"class="schbtn">위치보기</a></div>
				<div class="fr">
					<a href="#" onclick="fnUpdate();" class="schbtn" >수정</a>
					<a href="#" onclick="fnDelete();" class="graybtn">삭제</a>
					<a href="#" onclick="COMMON_UTIL.cmWindowClose( $('#wnd_id').val() );" class="graybtn">닫기</a>
				</div>
			</div>
		</div>
	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->


<%@ include file="/include/common.jsp" %>
</body>
</html>