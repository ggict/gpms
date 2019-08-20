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
<script src="<c:url value='/js/common.js'/>"></script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(END) -->
<div class="tabcont">
	<div class="fl bgsch">
    	<h3>포장상태 평가 방법 선정</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <li class="wid100">
	            	<label>평가방법</label>
	            	<select id="EVL_TYPE" onchange="fn_change_type();" class="input" style="width:180px;">
	            		<option value="srvy">조사자료단위 평가(미 평가 자료)</option>
	            		<option value="road" selected="selected">노선단위 수시평가</option>
	            	</select>
	            </li>
	            <li class="wid100">
	                <label>노선번호</label>
	                <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" style="width:70px;" class="input">
		        		<c:forEach items="${roadNoList }" var="roadNo">
		        			<option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li>
	                <label>노선명</label>
	                <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width:180px;" class="MX_80 CS_50 input" />
	            </li>
	            <li class="wid100 mt10">
	                <a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a>
	            </li>
	            <li class="wid100 mt10">	            	
					[평가 소요시간]<br/>
					40~50분/1건<br/>
					<br/>
					※ 현 시점의 포장상태를 평가하게 됩니다.(포장상태 평가정보 최신화)<br/> 
					※ 데이터를 분석하는데 많은 시간이 소요될 수 있습니다. 분석이 완료 될때까지 기다려주세요.
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>포장상태 평가대상 목록
	    	<a href="#" class="whitebtn dpib ml10 vm">
	    		<img id="imgBack" src="<%=request.getContextPath() %>/images/ic_reset.png" onclick="fn_search();" alt="새로고침" title="새로고침" />
    		</a>
	    </h3>
	    <p class="location">
	        <span>포장상태 평가</span>
	        <strong>포장상태 평가대상 목록</strong>
	    </p>
	    
	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
			<div class="mt10 tc">
	            <div class="fr">
	            	<a href="#" onclick="fnSave();" class="schbtn">포장상태 평가</a>
	           	</div>
	        </div>
        </div>
    </div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/* 
$( document ).ready(function() {
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'api/smdtalaststtus/selectRouteEvlList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["ROAD_NO", "노선번호", "노선명", "평가년도(현시점)<br/>포장상태 수시평가", "위치보기"]
	   	,colModel:[
			{name:'ROAD_NO',index:'ROAD_NO', hidden: true}
			,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:80, sortable: true, title: false}
			,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:80, sortable: true, title: false}
			,{name:'cell_sttus',index:'cell_sttus', align:'center', width:150, sortable: false, formatter: fn_create_cell, title: false}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fn_create_cell, title: false}
	   	]
		,async : false
	   	,sortname: 'ROAD_NO_VAL'
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
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
	   		if($('#jqg_gridArea_'+rowId).css("display") == "none"){
	   			$('#jqg_gridArea_'+rowId).removeAttr("checked");
	   			
	   			$("#gridArea")[0].p.selarrrow = $("#gridArea").find("tr.jqgrow:has(td > input.cbox:checked)").map(function() { return this.id; }).get();
	   		}
		}
	   	,onSelectAll:  function(rowIds, status) {		// 클릭 처리
		   	 if (status) {
		   		for(var i=0; i<rowIds.length; i++){
			   		if($('#jqg_gridArea_'+rowIds[i]).css("display") == "none"){
			   			$('#jqg_gridArea_'+rowIds[i]).removeAttr("checked");
			   		}
		   		}
		   		$("#gridArea")[0].p.selarrrow = $("#gridArea").find("tr.jqgrow:has(td > input.cbox:checked)").map(function() { return this.id; }).get();
		   	 }
	   	}
	   	,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridArea");
	   		 	$grid.jqGrid('setSelection', rowid, false);
	   			return false;
	   		} 
	   	    return true;
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
		,multiselect: true
		,multiboxonly: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
		
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 160);
	
	fn_change_roadNm();
	fn_search();
});

//검색 처리
function fn_search() {
	$(".cbox").prop("checked", false);
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		var grid = $("#gridArea");
			var ids = grid.jqGrid('getDataIDs');
		    for (var i=0;i<ids.length;i++) {
		        var id=ids[i];
		        var rowData = grid.jqGrid('getRowData',id);
		        if(rowData.cell_sttus == "N/A"){
		        	$('#'+id,grid[0]).attr('title', '해당 노선은 포장상태 평가정보가 존재하지 않아 수시평가 대상에서 제외됩니다.\n[평가방법-조사자료단위 평가(미 평가자료)]에 평가할 자료가 존재하는지 확인해주세요.');
		        	$('#jqg_gridArea_'+id).css("display", "none");
		        	$('#'+id,grid[0]).css("background", "#e6e6e6");
		        }
		    }
		    
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

//도면 다운로드, 위치이동 버튼 생성
function fn_create_cell(cellValue, options, rowObject) {
	var txtHtml = "";
	var nm = options.colModel.name;
	
	switch(nm) {
	case "cell_sttus" :
		// 클릭시 파일 다운로드
		if(rowObject.CALC_STTUS == "N/A"){
			txtHtml = rowObject.CALC_STTUS;
		}else{
			txtHtml = rowObject.CALC_YEAR + "-" + rowObject.CALC_MT + "\n" + rowObject.CALC_STTUS;	
		}
		break;
	case "btn_loc" :
		txtHtml = "<a href='#' onclick=\"fn_select_route('" + rowObject.ROAD_NO + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
	}
	
	return txtHtml;
}


//조사자료 평가
function fnSave(){	
	var seqListArr = [];
	var obj = $("#gridArea");
	var idx = obj.jqGrid('getGridParam', 'selarrrow');
	 
	if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
		alert("포장상태를 평가할 노선을 선택해주세요.");
		return false;
	}
	
	if(idx.length > 1){
		alert("포장상태를 평가할 노선을 하나만 선택해주세요.");
		return false;
	}
	
	var roadNo = obj.jqGrid('getCell', idx[0], 'ROAD_NO' );
		
	var option = {
			iframe : window,
			callback : "fn_search",
			ROAD_NO : roadNo
	}
	
	parent.SRVY.setOption(option);
	
	parent.$("#lodingbar").show();
	parent.SRVY.hideAllDiv();
	parent.$("#divEvlRoute").show();
	
}


//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
	var roadNo = $("#ROAD_NO").val();
	
	if(roadNo == "") {
		$("#ROAD_NAME").val("");
		return;
	}
	
	$.ajax({
		url: contextPath + 'api/routeinfo/selectRouteInfo.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data : JSON.stringify({ROAD_NO : roadNo})
		,success: function(data){
			$("#ROAD_NAME").val(data.ROAD_NAME);
		}
		,error: function(a,b,msg){}
	});
}

//평가 방법 변경시 페이지 이동
function fn_change_type() {
	var evlType = $("#EVL_TYPE").val();
	
	switch(evlType){
		case "srvy":
			COMMON_UTIL.cmMoveUrl('<c:url value="srvy/selectSrvyDtaEvlList.do"/>');
			break;
		case "road":
			COMMON_UTIL.cmMoveUrl('<c:url value="smdtalaststtus/selectRouteEvlList.do"/>');
			break;
	}
}

function fn_select_route(route_no){
	var tables = ["DORO_TOT_GRS80_50"];
	var fields = ["ROAD_NO"];
	var values = [route_no];
	
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
}

</script>
</body>
</html>