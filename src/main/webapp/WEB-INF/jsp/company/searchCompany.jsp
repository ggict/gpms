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
</head>

<body>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) --> 
<div id="Pop_wrap">
	<br/>
	<!-- Content -->
	<div class="ConBx">
		<div class="Pop_Section">
			<div class="PopTitBx"><p>공사업체 검색</p>
		</div>
			
	<!--검색영역-->
		<div class="cont_search">
			<div class="Con_Bt">
				<img src="<c:url value='/images/btn_search3.gif' />" alt="검색" onclick="javascript:fn_search();" />
			</div>
			<form id="frm" name="frm" method="post" action="">
				<input type="hidden" id="searchType" name="searchType"  value="${companyVO.searchType}" />
				<dl style="width:600px;">
					<dt class="CTx2">업체상태</dt>
					<dt class="W180">
						<select name="CO_STTUS_CODE" id="CO_STTUS_CODE" class="select">
							<option value="">== 전체 ==</option>
							<c:forEach items="${codesCOST }" var="codeInfo">
								<option value="${codeInfo.CODE_VAL }">${codeInfo.CODE_NM }</option>
							</c:forEach>
						</select>
					</dt>
					<dt class="CTx2">업체 명</dt>
					<dt class="W180">
						<input type="text" name="CO_NM" id="CO_NM" class="MX_50 CS_25 input" />
					</dt>
					<dt class="CTx2">사업자 번호</dt>
					<dt class="W180">
						<input type="text" name="BIZ_NO" id="BIZ_NO" class="MX_50 CS_25 input" />
					</dt>
					<dt class="CTx2">담당자 명</dt>
					<dt class="W180">
						<input type="text" name="CHARGER_NM" id="CHARGER_NM" class="MX_50 CS_25 input" />
					</dt>
				</dl>
			
			</form>
		</div>
		<div style="margin-top: 120px; width: 100%;">
			<div>&nbsp;</div>
			<div id="div_grid" style="width:100%; height:60%;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
		</div>
		<div class="Btn_pd">
			<div class="Btn_R2">
				<div class="Btn">
				<a href="#"  class="Btn_01" onclick="javascript:fn_close_dialog();">닫기</a></div>
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript" defer="defer">
//<!--
function fn_close_dialog(){
	var wnd_id = $("#wnd_id").val();
	$('#'+wnd_id, window.parent.document).remove();
}
/* function resize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
}
 */
//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	var postData = {"USE_AT":"Y"};
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/company/selectCompanyList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["CO_NO","업체명","대표자명","영업소재지","대표전화번호","사업자번호","담당자명", "담당자전화번호","업체상태"]
	   	,colModel:[
			{name:'CO_NO',index:'CO_NO', hidden: true}
			,{name:'CO_NM',index:'CO_NM', align:'left', width:120, sortable:true}
			,{name:'RPRSNTV_NM',index:'RPRSNTV_NM', align:'left', width:70, sortable:true}
			,{name:'BIZ_LOC',index:'BIZ_LOC', align:'left', width:70, sortable:true}
			,{name:'RPRSNT_TEL_NO',index:'RPRSNT_TEL_NO', align:'center', width:70, sortable:true}
			,{name:'BIZ_NO',index:'BIZ_NO', align:'left', width:70, sortable:true}
			,{name:'CHARGER_NM',index:'CHARGER_NM', align:'left', width:70, sortable:false}
			,{name:'CHARGER_TEL_NO',index:'CHARGER_TEL_NO', align:'center', width:90, sortable:false}
			,{name:'CO_STTUS_NM',index:'CO_STTUS_NM', align:'center', width:50, sortable:false}
	   	]
		,async : false
	   	,sortname: 'CO_NM'
	    ,sortorder: "asc"
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
	
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 300);
	
	fn_search();
}); 

//검색 처리
function fn_search() {
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}


//상세 조회
function fn_view(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var vform = $('#frm');
		//alert(COMMON_LANG.trimdata( vform.find('#searchType').val()));
		COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearchCompanyCallback(COMMON_LANG.trimdata( vform.find('#searchType').val()), rowData);
		fn_close_dialog();
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//-->
</script>	
</body>
</html>


