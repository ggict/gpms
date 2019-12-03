<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>코드 관리 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div id="container">
            <div class="admin_content">
		<h2>코드정보관리</h2>
	<!--검색영역-->
        <ul class="admin_sch">
		<form:form commandName="clCodeVO" id="clcodeform">
		<li>
			<label>분류코드</label>

			<form:select path="CL_CODE">
                <form:option value="" label="전체" />
                <form:options items="${items}" itemValue="CL_CODE" itemLabel="CL_CODE_NM" />
            </form:select>
			<label>분류코드명</label>
				<input type="text" name="CL_CODE_NM" id="CL_CODE_NM" value="<c:out value="${param.CL_CODE_NM}"/>" />
			<label>사용여부</label>
				<select name="USE_AT" id="USE_AT">
					<option value="">전체</option>
					<option value="Y">예</option>
					<option value="N">아니오</option>
				</select>
				<a href="#" class="btn pri posR" onclick="javascript:fnSearch();">검색</a>
			</li>
		</form:form>
	</ul>	
	
	<div class="tbc" style="width:100%;">
			<table style="width:100%;" >
				 <colgroup>
	                <col width="50%" />
	                <col width="50%" />
	            </colgroup>
				<tr>
					<td style="width:800px; vertical-align:top;" id="td_gridClCode">
						<h4>분류코드</h4>
						<table class="schtblist" id="gridClCode"></table>
						<div class="Btn_R" style="width: 100%; position: relative; top: 645px;">
							<!-- <div class="Btn"  style="float: right;"><a href="#" onclick="fnAddClCode();" class="schbtn">추가</a></div> -->
							  <div class="btnbx"><a href="#" onclick="fnAddClCode();" class="schbtn">분류코드 추가</a></div>
						</div>
					</td>
					<td style="width:600px; vertical-align:top;  padding-left:30px; position: relative;">
						<h4>상세코드</h4>
						<div style="width:600px; position: relative;" id="dv_gridCode">
						<table class="schtblist" id="gridCode"></table>						
						</div>
						<div class="Btn_R" style="width: 100%;height:50px;vertical-align:top;position: relative;" >
							<!-- <div class="Btn"  style="float: right;position: relative;align:right; padding:3px;"><a href="#" onclick="fnAddCode();" class="schbtn">추가</a></div> -->
							<div class="btnbx"><a href="#" onclick="fnAddCode();" class="schbtn">상세코드 추가</a></div>
						</div>
						
						<h4>코드 활용맵</h4>
						<div style="width:600px; position: relative;"  id="dv_gridCodeMap">
						  <table class="schtblist" id="gridCodeMap"></table>						  
						</div>
						<div class="Btn_R" style="width: 100%; position: relative;">
							<!-- <div class="Btn"  style="float: right;position: relative; padding:3px;"><a href="#" onclick="fnAddCodeMap();" class="schbtn">추가</a></div> -->
							<div class="btnbx"><a href="#" onclick="fnAddCodeMap();" class="schbtn">코드 활용맵 추가</a></div>
						</div>
					</td>
				</tr>
				<!-- <tr>
					<td style="width:600px; vertical-align:top; position: relative;"  >
						<div style="width:600px; position: relative;"  id="dv_gridCodeMap">
						  <table class="schtblist" id="gridCodeMap"></table>						  
						</div>
						<div class="Btn_R" style="width: 100%; position: relative;">
							<div class="Btn"  style="float: right;position: relative; padding:3px;"><a href="#" onclick="fnAddCodeMap();" class="schbtn">추가</a></div>
						</div>
					</td>
				</tr> -->
			</table>
	</div>
	
</div>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
    // 메뉴 select
    fnAdminMenuSelect("menu3");
	
	fnInitClCodeGrid();
	fnInitCodeGrid();
	fnInitCodeUseMapGrid();

	
	fnSearch();
}); 
function fnInitClCodeGrid(){
var postData = {"DELETE_AT":"N"};
	
	// 검색 목록 그리드 구성
	$("#gridClCode").jqGrid({
		url: '<c:url value="/"/>'+'api/clcode/selectClCodeList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#clcodeform").cmSerializeObject()) 
		,postData: $("#clcodeform").cmSerializeObject()
		,ignoreCase: true
		,colNames:["분류코드","우선순위","분류코드명","내용","수정자ID","사용여부","수정일시","삭제여부"]
	   	,colModel:[
			{name:'CL_CODE',index:'CL_CODE', align:'center', width:50, sortable:true}
			,{name:'PRIOR_RANK',index:'PRIOR_RANK', align:'center', width:50, sortable: true}
			,{name:'CL_CODE_NM',index:'CL_CODE_NM', align:'center', width:80, sortable: true}
			,{name:'CN',index:'CN', align:'left', width:100, sortable: true}
			,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'left', width:40, sortable:false, hidden: true}
			,{name:'USE_AT',index:'USE_AT', align:'center', width:50, sortable: true, formatter: fnYNtoKr}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center',  formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:90, sortable:false}
			,{name:'DELETE_AT',index:'DELETE_AT', align:'center', width:30, sortable:false, hidden: true}
	   	]
		,autoWidth : false
		,async : false
	   	,sortname: 'CL_CODE_NM'
	    ,sortorder: "asc"
	   	,rowNum: 9999999
//	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
//	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnViewClCode(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridClCode" ).getRowData(rowId);
				fnChangeClassCode(rowId);	// 대장 조회
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}	   			
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#clcodeform").cmSerializeObject();
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		,scroll: true
	});//.navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridClCode','td_gridClCode', 620);
	 //$('#gridClCode').setGridWidth(500); 

}
function fnInitCodeGrid(){
var postData = {"DELETE_AT":"N"};
	
	// 검색 목록 그리드 구성
	$("#gridCode").jqGrid({
		url: '<c:url value="/"/>'+'api/code/selectCodeList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#clcodeform").cmSerializeObject()) 
		,postData: $("#clcodeform").cmSerializeObject()
		,ignoreCase: true
		,colNames:["코드값","코드명","속성값","분류</br>코드","우선</br>순위","내용","수정자ID","사용</br>여부","수정</br>일시","삭제여부"]
	   	,colModel:[
			{name:'CODE_VAL',index:'CODE_VAL', align:'center', width:75, sortable: true}
			,{name:'CODE_NM',index:'CODE_NM', align:'center', width:120, sortable: true}
			,{name:'ATRB_VAL',index:'ATRB_VAL', align:'center', width:70, sortable: true}
			,{name:'CL_CODE',index:'CL_CODE', align:'center', width:70, sortable:false}
			,{name:'PRIOR_RANK',index:'PRIOR_RANK', align:'center', width:55, sortable: true}			
			,{name:'CN',index:'CN', align:'left', width:150, sortable: true}
			,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'left', width:40, sortable:false, hidden: true}
			,{name:'USE_AT',index:'USE_AT', align:'center', width:50, sortable: true, formatter: fnYNtoKr}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" },  width:150, sortable:false}
			,{name:'DELETE_AT',index:'DELETE_AT', align:'center', width:30, sortable:false, hidden: true}
	   	]
		,autoWidth : false
		,async : false
	   	,sortname: 'PRIOR_RANK'
	    ,sortorder: "asc"
	   	,rowNum: 9999999
//	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
//	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnViewCode(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridCode" ).getRowData(rowId);
				//fnChangeClassCode(rowId);	// 대장 조회
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}	   			
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#clcodeform").cmSerializeObject();
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		,scroll: true
	}).navGrid({edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridCode','dv_gridCode', 250);
}

function fnInitCodeUseMapGrid(){
var postData = {"USE_AT":"Y"};
	
	// 검색 목록 그리드 구성
	$("#gridCodeMap").jqGrid({
		url: '<c:url value="/"/>'+'api/codeusemap/selectCodeUsemapList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#clcodeform").cmSerializeObject()) 
		,postData: postData
		,ignoreCase: true
		,colNames:["분류코드","테이블명","테이블주석","컬럼명","컬럼주석","수정자ID","수정일시","사용여부" ]
	   	,colModel:[
			{name:'CL_CODE',index:'CL_CODE', align:'center', width:40, sortable: false}
			,{name:'TABLE_NM',index:'TABLE_NM', align:'center', width:100, sortable: true}
			,{name:'TABLE_COMMENTS',index:'TABLE_COMMENTS', align:'center', width:100, sortable: true}
			,{name:'COLMN_NM',index:'COLMN_NM', align:'center', width:60, sortable: true}
			,{name:'COLUMN_COMMENTS',index:'COLUMN_COMMENTS', align:'center', width:60, sortable: true}
			,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'left', width:40, sortable:false, hidden: true}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center', width:60,  formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, sortable:false, hidden: true}
			,{name:'USE_AT',index:'USE_AT', align:'center', width:30, sortable:false, hidden: true, formatter: fnYNtoKr} 
	   	]
		,autoWidth : false
		,async : false
	   	,sortname: 'TABLE_NM'
	    ,sortorder: "asc"
	   	,rowNum: 9999999
//	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
//	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnViewCodeMap(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridCodeMap" ).getRowData(rowId);
				//fnChangeClassCode(rowId);	// 대장 조회
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}	   			
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#clcodeform").cmSerializeObject();
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		,scroll: true
	});//.navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridCodeMap','dv_gridCodeMap', 250);
}
//검색 처리
function fnSearch() {
	var postData = {"DELETE_AT":"N"};
	//alert(JSON.stringify( $("#clcodeform").cmSerializeObject()));
	$("#gridClCode").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#clcodeform").cmSerializeObject())  
		,postData:   $("#clcodeform").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridClCode', $("#gridClCode").jqGrid("getGridParam").emptyrecords, data.records);
	   		$("#gbox_gridClCode").css("position", "fixed");
	   	}
	}).trigger("reloadGrid");
}


function fnChangeClassCode(rowId){
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridClCode").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridClCode").getRowData(rowId);
		
		// 상세 조회용 키 컬럼 변경 필수
		var cl_code = rowData["CL_CODE"];
		
		fnSearchCode(cl_code);
		fnSearchCodeMap(cl_code);
	}
	
}

//검색 처리
function fnSearchCode(cl_code) {
	var postData = {"DELETE_AT":"N", "CL_CODE": cl_code};
	//alert(JSON.stringify( $("#clcodeform").cmSerializeObject()));
	$("#gridCode").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#clcodeform").cmSerializeObject())  
		,postData:   postData
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridCode', $("#gridCode").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

function fnSearchCodeMap(cl_code) {
	var postData2 = {"USE_AT":"Y", "CL_CODE": cl_code};
	//alert(JSON.stringify( $("#clcodeform").cmSerializeObject()));
	$("#gridCodeMap").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#clcodeform").cmSerializeObject())  
		,postData:   postData2
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridCodeMap', $("#gridCodeMap").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}
 
function fnAddClCode(){
	
	var page = "<c:url value='/manage/clcode/addClCodeView.do' />";
	COMMON_UTIL.cmWindowOpen('코드정보 관리', page, 650, 285, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
}

//상세 조회
function fnViewClCode(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridClCode").getGridParam( "selrow" );
		
	if( rowId != null ) { 
		var rowData = $("#gridClCode").getRowData(rowId);
		
		// 상세 조회용 키 컬럼 변경 필수
		var cl_code = rowData["CL_CODE"];
		
		var page = "<c:url value='/manage/clcode/updateClCodeView.do' />"+"?CL_CODE="+cl_code;
		COMMON_UTIL.cmWindowOpen('분류코드 수정', page, 650, 340, false, $("#wnd_id").val(), 'center'); //밑에가있음..;	
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

function fnAddCode(){
	var rowId = $("#gridClCode").getGridParam( "selrow" );
	if( rowId != null ) { 
		var rowData = $("#gridClCode").getRowData(rowId);
		// 상세 조회용 키 컬럼 변경 필수
		var cl_code = rowData["CL_CODE"];
		var page = "<c:url value='/manage/code/addCodeView.do' />"+"?CL_CODE="+cl_code;
		COMMON_UTIL.cmWindowOpen('코드정보 관리', page, 650, 345, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
	}
	else{
		alert('<spring:message code="warn.checkplz.msg" />');
	}
}
//상세 조회
function fnViewCode(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridCode").getGridParam( "selrow" );
		
	if( rowId != null ) { 
		var rowData = $("#gridCode").getRowData(rowId);
		
		// 상세 조회용 키 컬럼 변경 필수
		var code_val = rowData["CODE_VAL"];
		
		var page = "<c:url value='/manage/code/updateCodeView.do' />"+"?CODE_VAL="+code_val;
		COMMON_UTIL.cmWindowOpen('상세코드 수정', page, 650, 405, false, $("#wnd_id").val(), 'center'); //밑에가있음..;	
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

function fnAddCodeMap(){
	var rowId = $("#gridClCode").getGridParam( "selrow" );
	if( rowId != null ) { 
		var rowData = $("#gridClCode").getRowData(rowId);
		// 상세 조회용 키 컬럼 변경 필수
		var cl_code = rowData["CL_CODE"];
		var page = "<c:url value='/manage/codeusemap/addCodeUsemapView.do' />"+"?CL_CODE="+cl_code;
		COMMON_UTIL.cmWindowOpen('코드정보 관리', page, 650, 240, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
	}
	else{
		alert('<spring:message code="warn.checkplz.msg" />');
	}
}

//상세 조회
function fnViewCodeMap(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridCodeMap").getGridParam( "selrow" );
		
	if( rowId != null ) { 
		var rowData = $("#gridCodeMap").getRowData(rowId);
		
		// 상세 조회용 키 컬럼 변경 필수
		var cl_code = rowData["CL_CODE"];
		var table_nm = rowData["TABLE_NM"];
		var colmn_nm = rowData["COLMN_NM"];
		var page = "<c:url value='/manage/codeusemap/updateCodeUsemapView.do' />"+"?CL_CODE="+cl_code+"&TABLE_NM="+table_nm+"&COLMN_NM="+colmn_nm;
		COMMON_UTIL.cmWindowOpen('코드 활용맵 수정', page, 650, 300, false, $("#wnd_id").val(), 'center'); //밑에가있음..;	
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

function fnYNtoKr(cellValue, options, rowObject) {
    
    if ( rowObject.USE_AT == "Y" ) {
        
        return "예";
        
    } else {
        
        return "아니오";
        
    }
    
    return "";
    
}
</script>
<div id="clCodeDialog"></div>
</body>
</html>