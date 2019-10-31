<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>세부공사목록 </title>
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
 
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>

<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${param.CNTRWK_ID }" />
<div>
	<div class="posiR">
       	<ul class="ctab_menu">
            <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkView.do?CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}')">기본정보</a></li>
			<li class="sel"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}')">세부공사</a></li>
			<%-- <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}')">하자기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flawcntrwk/selectFlawCntrwkList.do?CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}')">하자보수공사</a></li> --%>
       	</ul>
       	<h5 class="info" style="left: 310px;">
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
		</h5>
		<p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력조회</span>
	        <strong>포장공사 세부공사 목록</strong>
	    </p>
   </div>
	<!-- Content -->
	<div class="ctab_wrap">
		<div class="tabcont">
			<div class="mt10 ml10 mr10">
				<div id="div_grid" style="width:100%; height:240px;">
					<table id="gridArea"></table>
					<div id="gridPager"></div>
				</div>
				<div class="mt10 tc">
		            <div class="fr">
		           		<a href="#" onclick="fnFileSave();" class="schbtn">엑셀업로드</a>
		            	<a href="#" onclick="fn_cntrwkExcel();" class="schbtn">엑셀저장</a>
		            	<a href="#" onclick="fnWrite();" class="schbtn" >등록</a>
		            </div>
		        </div>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'cntrwkdtl/selectCntrwkDtlListPage.do?CNTRWK_ID='+$("#CNTRWK_ID").val()
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["CELL_ID","DETAIL_CNTRWK_ID","CNTRWK_ID","세부위치","노선번호","노선명","행선","차로","시점<br>(m)","종점<br>(m)","공사착공일","공사준공일","공사비<br>(천원)","연장<br>(m)","보수폭<br>(m)","보수면적<br>(㎡)","표층","중간층","기층","위치조회"]
	   	,colModel:[
	   	     {name:'CELL_ID',index:'CELL_ID', hidden: true}
	   	    ,{name:'DETAIL_CNTRWK_ID',index:'DETAIL_CNTRWK_ID', hidden: true}
	   	    ,{name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
	   		,{name:'DETAIL_CNTRWK_NM',index:'DETAIL_CNTRWK_NM', align:'center', width:150}
	   		,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:80,
	   			formatter: 'integer', formatoptions: { defaultValue: ' ' }}
			,{name:'ROUTE_NM',index:'ROUTE_NM', align:'center', width:80}
			,{name:'DIRECT_NM',index:'DIRECT_CODE', align:'center', width:30}
			,{name:'TRACK',index:'TRACK', align:'center', width:30}
			,{name:'STRTPT',index:'STRTPT', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'ENDPT',index:'ENDPT', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'RPAIR_BEGIN_DE',index:'RPAIR_BEGIN_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'RPAIR_END_DE',index:'RPAIR_END_DE',align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false, extTxt:"합계"}}
			,{name:'CNTRWK_AMOUNT',index:'CNTRWK_AMOUNT', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'TRACK_LEN',index:'TRACK_LEN', align:'center', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'RPAIR_BT',index:'RPAIR_BT', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'RPAIR_AR',index:'RPAIR_AR', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'RPAIR_THICK_ASCON',index:'RPAIR_THICK',align:'center', width:30,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'RPAIR_THICK_CNTR',index:'RPAIR_THICK_CNTR',align:'center', width:30,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'RPAIR_THICK_BASE',index:'RPAIR_THICK_BASE',align:'center', width:30,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, formatter: fn_create_btn, sortable: false}
	   	]
		,async : false
		,sortname: 'DETAIL_CNTRWK_NM'
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
			fnView(rowId);	// 대장 조회
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
		,footerrow : true
		,shrinkToFit : false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	$("#gridArea").jqGrid('setGroupHeaders', {
		useColSpanStyle: true, 
		groupHeaders:[
			{startColumnName: 'RPAIR_THICK_ASCON', numberOfColumns: 3, titleText: '포장두께(cm)'}
		]	
	}); 
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 140);
	
	fnSearch();
	
	$(window).resize(function () {
		fnSetGridWith();
    });
}); 

//검색 처리
function fnSearch() {
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#frm").cmSerializeObject())  
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
		,loadComplete: function(data) {
			
		    fnSetGridWith();
			
			COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

//jqgrid 하단 및 넓이 설정 
function fnSetGridWith() {
	var grid = $("#gridArea");
		
	// Sum 처리
	grid.jqGrid('footerData','set', {
		RPAIR_END_DE: '합계', 
		CNTRWK_AMOUNT: grid.jqGrid('getCol', 'CNTRWK_AMOUNT', false, 'sum'),
		TRACK_LEN: grid.jqGrid('getCol', 'TRACK_LEN', false, 'sum'),
		RPAIR_BT: grid.jqGrid('getCol', 'RPAIR_BT', false, 'sum'),
		RPAIR_AR: grid.jqGrid('getCol', 'RPAIR_AR', false, 'sum'),
	});	   
	
	var gridWidth = window.innerWidth - 30;
	
	grid.setGridWidth(gridWidth);
	
	$(".ui-jqgrid-htable").css("width", gridWidth-20);
	$(".ui-jqgrid-btable").css("width", gridWidth-20);
	$(".ui-jqgrid-ftable").css("width", gridWidth-20);
}

//상세 조회
function fnView(rowId) {
	if( $.type(rowId) === "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var deCntrwkId = rowData["DETAIL_CNTRWK_ID"];
		var cellId = rowData["CELL_ID"];
		
		COMMON_UTIL.cmWindowOpen('상세정보 조회', "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>?DETAIL_CNTRWK_ID="+deCntrwkId+"&CELL_ID="+cellId, 700, 1200, false, $("#wnd_id").val(), 'center');
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//엑셀업로드
function fnFileSave() {
	var cntrwk_id = $("#CNTRWK_ID").val();
	COMMON_UTIL.cmWindowOpen('포장공사 이력 엑셀 업로드', "<c:url value='/cntrwkdtl/cntrwkDtlExcelUploadForm.do'/>?CNTRWK_ID="+cntrwk_id, 290, 380, true, $("#wnd_id").val(), 'center');	

} 
//신규 등록 화면 이동 [수정:선택] 키가 복수개 이거나 명칭이 다른 경우
function fnWrite() {
	$.ajax({
		url: contextPath + 'userauth/checkAuth.do'
		,type: 'post'
		,dataType: 'json'
		,data : {"url" : "/cntrwkdtl/addCntrwkDtlView.do"}
		,success: function(res){				
			if(!res.result){
				alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
				return;
			}
			
			var option = {
					iframe : window,
					callback : "fn_add_cntrwkDtl",
					CNTRWK_ID : $("#CNTRWK_ID").val(),
					clearMap : false
			};
			
			parent.MAP.CONTROL.activate_cellSel("CELL_10", option);
		}
		,error: function(a,b,msg){
			
		}
	});
}

//엑셀 출력
function fn_cntrwkExcel(){
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cntrwkdtl/downloadexcel.do'/>", "");
	}
}

//포장공사상세조회 등록 창 
function fn_add_cntrwkDtl(cellIdList, param){
    
	$.ajax({
		url: contextPath + 'api/cell10/selectRouteInfos.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify( {CELL_IDS : cellIdList}) 
		,success: function(data){
			if(data.length < 1) {return;}
			
			if(data.length > 1){
				var roadNM = "";
				for(var i in data){
					if(i!=0){roadNM += ",";}
					roadNM += data[i].ROAD_NAME + "(" + parseInt(data[i].ROUTE_CODE) + ")";
				}
				alert( '단일 노선이 선택되지 않았습니다.('+roadNM+')' );
				return;
			}
			
			var routeCd = data[0].ROUTE_CODE;
			var roadNm = data[0].ROAD_NAME;
			var directCd = data[0].DIRECT_CODE;
			var directNm = data[0].DIRECT_NM;
			var track = data[0].TRACK;
			var strtpt = parseInt(data[0].STRTPT);
			var endpt = parseInt(data[0].ENDPT);
			var trackLen = parseInt(endpt) - parseInt(strtpt);
			var rpairBt = 3.5 * parseInt(track);
			var rpairAr = trackLen * rpairBt;
			
			var initData = {
				CNTRWK_ID 	: 	param.CNTRWK_ID,
				PAV_CELL_ID	:	cellIdList,
				PAV_CELL_IDS:	cellIdList,
				ROUTE_CODE	:	routeCd,
				ROUTE_CODE1	:	parseInt(routeCd),
				ROUTE_NM	:	roadNm,
				DIRECT_CODE	:	directCd,
				DIRECT_NM	:	directNm,
				TRACK		:	track,
				STRTPT		:	strtpt,
				ENDPT		:	endpt,
				STRTPT_KM	:	COMMON_UTIL.cmAddComma(strtpt),
				ENDPT_KM	:	COMMON_UTIL.cmAddComma(endpt),
				TRACK_LEN	:	COMMON_UTIL.cmAddComma(trackLen),
				RPAIR_BT	:	COMMON_UTIL.cmAddComma(rpairBt),
				RPAIR_AR	:	COMMON_UTIL.cmAddComma(rpairAr)
			};
			
			COMMON_UTIL.cmWindowOpen('상세정보 등록', "<c:url value='/cntrwkdtl/addCntrwkDtlView.do'/>?CNTRWK_ID="+$("#CNTRWK_ID").val(), 700, 1200, false, $("#wnd_id").val(), 'center', initData);
			
		}
		,error: function(a,b,msg){
		}
	});
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"fnViewLocation('" + rowObject.DETAIL_CNTRWK_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}

/* function fn_select_cell(cell_id){
	var tables = ["CELL_10"];
	var fields = ["CELL_ID"];
	var values = [cell_id];
	
	// 모든 팝업창 최소화
	parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
	
	if(cell_id == "null"){
		alert("셀관리번호가 없습니다. 관리자에게 문의 하시기 바랍니다.");
	}
}
 */

//위치조회
function fnViewLocation(val){
	var dtlCntrwkId = val;
	
	$.ajax({
		url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
		,type: 'post'
		,data: JSON.stringify({"DETAIL_CNTRWK_ID" : dtlCntrwkId })
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
</body>
</html>