<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>하자보수공사목록 </title>
<%@ include file="/include/common_head.jsp" %>
 
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${param.CNTRWK_ID }" />
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div>
	<div class="posiR">
       	<ul class="ctab_menu">
            <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkView.do?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}')">기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}')">세부공사</a></li>
			<%-- <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}')">하자기본정보</a></li>
			<li class="sel"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flawcntrwk/selectFlawCntrwkList.do?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}')">하자보수공사</a></li> --%>
       	</ul>
       	<h5 class="info" style="left: 310px;">
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
		</h5>
		<p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <strong>포장 하자보수공사 목록 조회</strong>
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
					<div class="fl">
						<a href="#" onclick="fn_cntrwkExcel();" class="schbtn">엑셀저장</a>
					</div>
		            <div class="fr">
						<a href="#" class="schbtn" onclick="fnWrite();">등록</a>
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
		url: '<c:url value="/"/>'+'api/flawcntrwk/selectFlawCntrwkListPage.do?CNTRWK_ID='+$("#CNTRWK_ID").val()
		//url: '<c:url value="/"/>'+'api/flawcntrwk/selectFlawCntrwkListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["셀관리번호","공사ID","세부공사ID","하자ID","하자공사ID","노선번호","노선명","행선","차로","보수공법","보수재료","보수일자","연장(m)","폭(m)","면적(㎡ )","표층","중간층","기층"] //,"위치조회"
	   	,colModel:[
	   	    {name:'CELL_ID',index:'CELL_ID', width:30, hidden: true}
	   	    ,{name:'CNTRWK_ID',index:'CNTRWK_ID', width:30, hidden: true}
			,{name:'DETAIL_CNTRWK_ID',index:'DETAIL_CNTRWK_ID', align:'center', width:30, hidden: true}
			,{name:'FLAW_ID',index:'FLAW_ID', align:'center', width:100, hidden: true}
			,{name:'FLAW_CNTRWK_ID',index:'FLAW_CNTRWK_ID', align:'center', width:50, hidden: true}
			,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:50,
				formatter: 'integer', formatoptions: { defaultValue: ' ' }}
			,{name:'ROUTE_NM',index:'ROUTE_NM', align:'center', width:50}
			,{name:'DIRECT_NM',index:'DIRECT_NM', align:'center', width:50}
			,{name:'TRACK',index:'TRACK', align:'center', width:50}
			,{name:'RPAIR_MTHD_NM',index:'RPAIR_MTHD_NM', align:'center', width:100}
			,{name:'PAV_MATRL_ASCON_NM',index:'PAV_MATRL_ASCON_NM', align:'center', width:100}
			,{name:'RPAIR_DE',index:'RPAIR_DE', align:'center', width:100, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'RPAIR_LEN',index:'RPAIR_LEN', align:'right', width:100, 
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' } }
			,{name:'RPAIR_BT',index:'RPAIR_BT', align:'center', width:60}
			,{name:'RPAIR_AR',index:'RPAIR_AR', align:'center', width:60, 
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' } }
			,{name:'RPAIR_THICK_ASCON',index:'RPAIR_THICK_ASCON', align:'center', width:40}
			,{name:'RPAIR_THICK_CNTR',index:'RPAIR_THICK_CNTR', align:'center', width:40}
			,{name:'RPAIR_THICK_BASE',index:'RPAIR_THICK_BASE', align:'center', width:40}
			//,{name:'btn_loc',index:'btn_loc', align:'center', width:50, formatter: fn_create_btn, sortable: false}
	   	]
		,async : false
		,sortname: 'ROUTE_NM'
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
		,shrinkToFit : false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	$("#gridArea").jqGrid('setGroupHeaders', {
		useColSpanStyle: true, 
		groupHeaders:[
			{startColumnName: 'RPAIR_THICK_ASCON', numberOfColumns: 3, titleText: '<em>포장두께(cm)</em>'}
		]	
	});
		
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 160);
	
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
	   		
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   		
	   		fnSetGridWith();
	   	}
	}).trigger("reloadGrid");
}

//jqgrid 하단 및 넓이 설정 
function fnSetGridWith() {

	var grid = $("#gridArea");
	
	var gridWidth = window.innerWidth - 30;
	
	grid.setGridWidth(gridWidth);
	
	$(".ui-jqgrid-htable").css("width", gridWidth-20);
	$(".ui-jqgrid-btable").css("width", gridWidth-20);
}

//신규 등록 화면 이동 [수정:선택] 키가 복수개 이거나 명칭이 다른 경우
function fnWrite() {
	COMMON_UTIL.cmWindowOpen( "하자보수공사 등록", "<c:url value='/flawcntrwk/addFlawCntrwkView.do'/>?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}", 850, 550, false, $("#wnd_id").val(), 'center');
}

function fnView(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId); 
		var keyId = rowData["FLAW_CNTRWK_ID"];	// 상세 조회용 키 컬럼 변경 필수
		var keyIdSub = rowData["CNTRWK_ID"];	// 상세 조회용 키 컬럼 변경 필수
		
		COMMON_UTIL.cmWindowOpen( "하자보수공사상세조회", "<c:url value='/flawcntrwk/selectFlawCntrwk.do'/>?CNTRWK_ID="+keyIdSub+"&FLAW_CNTRWK_ID="+keyId, 850, 550, false, $("#wnd_id").val(), 'center');
		//COMMON_UTIL.cmWindowOpen( "하자보수공사", "<c:url value='/flawcntrwk/updateFlawCntrwkView.do'/>?CNTRWK_ID="+keyIdSub+"&FLAW_CNTRWK_ID="+keyId, 700, 550, false, $("#wnd_id").val(), 'center');
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//공사목록 엑셀로 출력
function fn_cntrwkExcel(){
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/flawcntrwk/downloadexcel.do'/>?CNTRWK_ID=${flawCntrwkVO.CNTRWK_ID}", "");
	}
}

//위치이동 버튼 생성
/* function fn_create_btn(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"fn_select_cell('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}

function fn_select_cell(cell_id){
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
</script>
</body>
</html>