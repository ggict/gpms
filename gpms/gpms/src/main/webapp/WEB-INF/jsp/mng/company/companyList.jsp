<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>업체조회 </title>
<!--
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
	<div id="container">
		<div class="admin_content">
			<h2>공사 업체 관리</h2>
			<!--검색영역-->
			<ul class="admin_sch">
				<li>
					<label>업체상태</label>
					<select name="CO_STTUS_CODE" id="CO_STTUS_CODE" class="select" onkeydown="fnCheckEnter(event);">
						<option value="">== 전체 ==</option>
						<c:forEach items="${codesCOST }" var="codeInfo">
							<option value="${codeInfo.CODE_VAL }">${codeInfo.CODE_NM }</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>업체 명</label>
					<input type="text" name="CO_NM"	id="CO_NM" class="MX_50 CS_25 input" onkeydown="fnCheckEnter(event);"/>
				</li>
				<li>
					<label>사업자 번호</label>
					<input type="text" name="BIZ_NO" id="BIZ_NO" class="MX_50 CS_25 input" onkeydown="fnCheckEnter(event);"/>
				</li>
				<li>
					<label>담당자 명</label>
					<input type="text" name="CHARGER_NM" id="CHARGER_NM" class="MX_50 CS_25 input" onkeydown="fnCheckEnter(event);"/>
				</li>
				<li class="fr"><a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a></li>
			</ul>

			<div id="div_grid" style="width: 100%;">
				<table class="adminlist" id="gridArea"></table>
				<div id="gridPager"></div>
			</div>

			<div class="btnbx">
				<a href="#" class="schbtn" onclick="fn_addCompany();">외부업체 추가</a>
			</div>
		</div>
	</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu1", 3);

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
		,colNames:["CO_NO","업체 명","대표자 명","영업 소재지","대표 전화 번호","사업자 번호","담당자 명", "담당자 전화 번호","업체 상태","수정자 ID", "수정 일시"]
	   	,colModel:[
			{name:'CO_NO',index:'CO_NO', hidden: true}
			,{name:'CO_NM',index:'CO_NM', align:'left', width:120, sortable:true}
			,{name:'RPRSNTV_NM',index:'RPRSNTV_NM', align:'left', width:70, sortable:true}
			,{name:'BIZ_LOC',index:'BIZ_LOC', align:'left', width:100, sortable:true}
			,{name:'RPRSNT_TEL_NO',index:'RPRSNT_TEL_NO', align:'center', width:70, sortable: false}
			,{name:'BIZ_NO',index:'BIZ_NO', align:'left', width:70, sortable: false}
			,{name:'CHARGER_NM',index:'CHARGER_NM', align:'left', width:70, sortable: true}
			,{name:'CHARGER_TEL_NO',index:'CHARGER_TEL_NO', align:'center', width:90, sortable: false}
			,{name:'CO_STTUS_NM',index:'CO_STTUS_CODE', align:'center', width:50, sortable: true}
			,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'left', width:70, sortable:false, hidden: true}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" },  width:120, sortable:false}

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

	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

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
function fn_addCompany() {
	COMMON_UTIL.cmWindowOpen('외부업체 상세 정보', "<c:url value='/manage/company/addCompanyView.do'/>", 650, 510, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
}

//상세 조회
function fn_view(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );

	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);

		// 상세 조회용 키 컬럼 변경 필수
		var no = rowData["CO_NO"];

		COMMON_UTIL.cmWindowOpen('외부업체 상세 정보', "<c:url value='/manage/company/updateCompanyView.do'/>?CO_NO="+no, 650, 580, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}

</script>
</body>
</html>