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
	<c:if test="${empty srvyDtaExcelVO.CELL_ID}">
	<div class="fl bgsch">
    	<h3>포장상태 평가 방법</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <!-- <li class="wid100">
	            	<label>평가방법</label>
	            	<select id="EVL_TYPE" onchange="fn_change_type();" class="input" style="width:180px;">
	            		<option value="srvy" selected="selected">조사자료단위 평가(미 평가 자료)</option>
	            		<option value="road">노선단위  수시평가</option>
	            	</select>
	            </li> -->
	            <li class="wid100 mt10">
	            	※노선 연장 및 노면분석 자료양에 따라 평가 소요시간은 달라질 수 있습니다.
					<br><br> 
					※ 데이터를 분석하는데 많은 시간이 소요될 수 있으므로 분석이 완료 될때까지 기다려주세요.
					<br><br>     	
	            	<strong>[포장상태 평가 진행단계]</strong><br>
					포장상태 평가(GPCI) -> GPS 공간보정 -> 200m 집계(Section 셀)<br>
					<br>
					<strong>[평가 소요시간]</strong><br>
					 5~6분/10km<br>
	            </li>
	        </ul>
	    </div>
	</div>
	</c:if>

	<c:if test="${empty srvyDtaExcelVO.CELL_ID}">
	<div class="fr listbx2">
	</c:if>
	<c:if test="${not empty srvyDtaExcelVO.CELL_ID}">
	<div class="" style="width: 100%; position: absolute; left: 0px;">
	</c:if>
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
				<div class="fl">
	            	<a href="#" onclick="fn_delete();" class="graybtn">삭제</a>
	           	</div>
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
		url: contextPath + 'srvy/api/srvyDtaRegList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SRVY_NO", "FILE_NO", "조사자료 파일명", "자료(행)", "자료 등록일", "조사일자", "평가년도", "등록자", "다운로드", "상태평가", "공간보정", "Section<br>집계"]
	   	,colModel:[
			{name:'SRVY_NO',index:'SRVY_NO', hidden: true}
			,{name:'FILE_NO',index:'FILE_NO', hidden: true}
			,{name:'FILE_NM',index:'FILE_NM', align:'center', width:120, sortable: true}
			,{name:'DATA_CO',index:'DATA_CO', align:'center', width:50, sortable: true,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:70, sortable: true}
			,{name:'SRVY_DE',index:'SRVY_DE', align:'center', width:70, sortable: true, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'EVL_YEAR',index:'EVL_YEAR', align:'center', width:50, sortable: true}
			,{name:'USER_NM',index:'USER_NM', align:'center', width:50, sortable: true}
			,{name:'btn_dwn',index:'btn_dwn', align:'center', width:50, sortable:false, formatter: fn_create_btn_down}
			,{name:'EVL_PROCESS',index:'EVL_PROCESS', align:'center', width:50, sortable: true}
			,{name:'GPS_CORTN_PROCESS',index:'GPS_CORTN_PROCESS', align:'center', width:50, sortable: true}
			,{name:'SM_PROCESS',index:'SM_PROCESS', align:'center', width:50, sortable: true}
	   	]
		,async : false
	   	,sortname: 'CREAT_DT'
	    ,sortorder: "desc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "포장상태 평가대상 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
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
		,shrinkToFit : false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	$("#gridArea").jqGrid('setGroupHeaders', {
		useColSpanStyle: true, 
		groupHeaders:[
			{startColumnName: 'EVL_PROCESS', numberOfColumns: 3, titleText: '포장상태 평가'}
		]	
	}); 
	
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 160);
	
	fn_search();
	
	$(window).resize(function () {
		fnSetGridWith();
    });
});

//검색 처리
function fn_search() {
	$('#frm')[0].reset(); //폼 초기화(리셋);
	$(".cbox").prop("checked", false);
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   		
	   		fnSetGridWith();
	   	}
	}).trigger("reloadGrid");
}

//jqgrid 하단 및 넓이 설정 
function fnSetGridWith() {
	// Sum 처리
	var grid = $("#gridArea");
	var gridWidth = window.innerWidth - 310;
	
	grid.setGridWidth(gridWidth);
	
	$(".ui-jqgrid-htable").css("width", gridWidth - 20);
	$(".ui-jqgrid-btable").css("width", gridWidth - 20);
}

//파일 다운로드 버튼 생성
function fn_create_btn_down(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
	case "btn_dwn" :
		// 클릭시 파일 다운로드
		btn = "<a href='#' onclick=\"fn_file_download('" + rowObject.FILE_NO + "');\"><img src='" + contextPath +"/images/ic_download.png' alt='다운로드' title='다운로드' ></a>";
		break;
	}
	
	return btn;
}

//파일 다운로드
function fn_file_download(fileNo){
	COMMON_FILE.cmFileDownloadByNo(fileNo);
}

//파일 삭제
function fn_delete(){
	var seqListArr = [];
	var sucessCnt = 0;
	var obj = $("#gridArea");
	var idx = obj.jqGrid('getGridParam', 'selarrrow');
	
	if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
		alert("삭제할 조사자료를 선택해주세요");
		return false;
	}
	
	// SEQ List
	for(var i=0; i<idx.length; i++){
		var value = obj.jqGrid('getCell', idx[i], 'SRVY_NO' );
		seqListArr.push( value );
		
		if(obj.jqGrid('getCell', idx[i], 'EVL_PROCESS' ) == "성공" ||
				obj.jqGrid('getCell', idx[i], 'GPS_CORTN_PROCESS' ) == "성공" ||
				obj.jqGrid('getCell', idx[i], 'SM_PROCESS' ) == "성공"){
			sucessCnt++;
		};
	}
	
	var option = {
			iframe : window,
			callback : "fn_search"
	}
	
	parent.SRVY.setOption(option);
	parent.SRVY.setSrvyList(seqListArr);
	
	parent.$("#lodingbar").show();
	parent.SRVY.hideAllDiv();
	parent.$("#divSrvyDelete").show();
	
	var msg = idx.length + "건의 조사자료를 삭제 하시겠습니까?";
	if(sucessCnt > 0){
		msg = "성공한 내역이 있는 조사자료 " + sucessCnt + "건이 포함되어있습니다./n"+msg;
	}
	
	parent.$("#t_srvydelete").html(msg);
}


//조사자료 평가
function fnSave(){	
	var seqListArr = [];
	var obj = $("#gridArea");
	var idx = obj.jqGrid('getGridParam', 'selarrrow');
	 
	if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
		alert("등록할 조사자료를 선택해주세요");
		return false;
	}
	
	// SEQ List
	for(var i=0; i<idx.length; i++){
		var value = obj.jqGrid('getCell', idx[i], 'SRVY_NO' );
		seqListArr.push( value );
	}
	
	var option = {
			iframe : window,
			callback : "fn_search"
	}
	
	parent.SRVY.setOption(option);
	parent.SRVY.setSrvyList(seqListArr);
	
	parent.$("#lodingbar").show();
	parent.SRVY.hideAllDiv();
	parent.$("#divSrvyProc1").show();
	
	parent.$("#s_srvyProcCnt").text(seqListArr.length);
}

//평가 방법 변경시 페이지 이동
/* function fn_change_type() {
	var evlType = $("#EVL_TYPE").val();
	
	switch(evlType){
		case "srvy":
			COMMON_UTIL.cmMoveUrl('<c:url value="srvy/selectSrvyDtaEvlList.do"/>');
			break;
		case "road":
			COMMON_UTIL.cmMoveUrl('<c:url value="smdtalaststtus/selectRouteEvlList.do"/>');
			break;
	}
} */

</script>
</body>
</html>