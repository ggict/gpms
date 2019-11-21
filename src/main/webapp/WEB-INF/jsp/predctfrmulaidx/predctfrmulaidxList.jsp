<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>공용성예측모델 조회</title>
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
               	<h2>공용성예측모델 조회</h2>
               	※ 파손율 = 계수A + 계수B*(공용개월수)^계수C<br/>
               	※ GPCI = 계수A - 계수B*(공용개월수)^계수C
           		<div id="div_grid" style="width:100%; height:206px;">
					<table class="adminlist" id="gridArea"></table>
				</div>
           </div>
	</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">
var lastsel;
//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu4", 1);

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/predctfrmulaidx/selectPredctFrmulaIdxList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData:  $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["PREDCT_IDX_NO","수식 적용대상","섹션 구분","계수 A","계수 B","계수 C","수정자명","수정 일시"]
	   	,colModel:[
	   	    {name:'PREDCT_IDX_NO',index:'PREDCT_IDX_NO', hidden: true, editable:true}
	   	 	,{name:'PRDTFML_TRGET',index:'PRDTFML_TRGET', align:'center', width:70, sortable:false}
			,{name:'CELL_TYPE',index:'CELL_TYPE', align:'center', width:70, sortable:false}
			,{name:'RK_A',index:'RK_A', align:'center', width:60, sortable:false, editable:true}
			,{name:'RK_B',index:'RK_B', align:'center', width:60, sortable:false, editable:true}
			,{name:'RK_C',index:'RK_C', align:'center', width:60, sortable:false, editable:true}
			,{name:'UPDUSR_NM',index:'UPDUSR_NM', align:'center', width:50, sortable:false}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center', width:50, sortable:false}
	   	]
		,async : false
		,sortname: 'PRDTFML_TRGET,CELL_TYPE'
	    ,sortorder: "asc"
	   	,rowNum: 999999
	   	//,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	//,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			//fnView(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
	   		
			jQuery('#gridArea').jqGrid('restoreRow',lastsel);
			jQuery('#gridArea').jqGrid('editRow',rowId,true);
			lastsel=rowId;
			
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
		,scroll: true
		,grouping:true
	   	,groupingView : {
	   		groupField : ['PRDTFML_TRGET']
	   	}
	   	, editurl: '<c:url value="/"/>'+'predctfrmulaidx/updatePredctFrmulaIdx.do'
	}); //.navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

	fn_search();
});

//검색 처리
function fn_search() {

	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData: $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

</script>
</body>
</html>
