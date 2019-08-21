<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/* 
$( document ).ready(function() {
	
	// 검색 목록 그리드 구성
	// 엑셀 업로드 실패 파일 목록 조회
	$("#gridArea").jqGrid({
		url: contextPath + 'srvy/api/srvyDtaUploadFileList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#fail_frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["파일명", "로그메세지", "작업 일자","작업자"]
	   	,colModel:[
			{name:'FILE_NM',index:'FILE_NM', align:'left', width:220, sortable:false}
			,{name:'LOG_MSSAGE',index:'LOG_MSSAGE', align:'left', width:120, sortable:false}
			,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:60, sortable:false} 
			,{name:'USER_NM',index:'USER_NM', align:'center', width:60, sortable:false} 
	   	]
		,async : false
		,sortname: 'FILE_NM'
		,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [10,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
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
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea', 'div_grid', 200);
	
	fn_search();
});

//검색 처리
function fn_search() {
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

</script>
</head>
<body>

<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">

<input type="hidden" id="CREAT_DT" name="CREAT_DT" value="${srvyDtaExcelVO.CREAT_DT}"/>
<input type="hidden" id="CRTR_NO" name="CRTR_NO" value="${srvyDtaExcelVO.CRTR_NO}"/>
<input type="hidden" id="PROCESS_STTUS" name="PROCESS_STTUS" value="${srvyDtaExcelVO.PROCESS_STTUS}"/>
<div class="tabcont">
	<div class="content">
	    <h3>조사자료 등록 실패(${cnt }건) - 상세조회 | 등록일자 : ${srvyDtaExcelVO.CREAT_DT }
	        <a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('/srvydtaexcel/selectSrvyDtaExcelList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
	    </h3>
	    <p class="location">
	        <span>조사자료 관리</span>
	        <span>조사자료 등록</span>
	         <span>조사자료 등록 대상목록</span>
	        <strong>조사자료 등록 실패 목록</strong>
	    </p>
    	<div class="mt10 ml10 mr10">
		    <div id="div_grid" style="width:100%; height:206px">
		        <table id="gridArea"></table>
				<div id="gridPager"></div>
           	</div>
        </div>
	</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>