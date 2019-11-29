<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사이력조회 </title>
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
<input type="hidden" id="sidx" name="sidx" value=""/>
<input type="hidden" id="sord" name="sord" value=""/>




<div class="tabcont e10">

	<header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">포장공사이력관리</option>
                </select>
                <select name="">
                    <option value="">포장공사이력관리</option>
                </select>
                <h2 class="h2">포장공사이력 조회</h2>
            </span>
        </div>
    </header>
    
    <div class="contents container">
    
    	<article class="div3">
    		<h3 class="h3">검색조건</h3>
    		<div class="table">
    			<table>
    				<tbody>
    				<tr>
    					<td class="th"><label for="SCH_CNTRWK_SE">공사구분</label></td>
    					<td>
			                <select name="SCH_CNTRWK_SE" id="SCH_CNTRWK_SE">
			                	<option value="">전체</option>
								<c:forEach var="selectData" items="${codesCWSE}">
									<option value="${selectData.CODE_VAL}">${selectData.CODE_NM}</option>
								</c:forEach>
			                </select>
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="SCH_CNTRWK_NM">공사명</label></td>
    					<td>
    						 <input type="text" name="SCH_CNTRWK_NM" id="SCH_CNTRWK_NM" value="<c:out value="${param.SCH_CNTRWK_NM}"/>" />
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="SCH_CO_NM">시공사</label></td>
    					<td>
                             <input type="text" name="SCH_CO_NM" id="SCH_CO_NM" value="<c:out value="${param.SCH_CO_NM}"/>" />
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="SCH_STRWRK_DE">계약기간</label></td>
    					<td>
                            <input type="text" name="SCH_STRWRK_DE" id="SCH_STRWRK_DE" /> ~
            				<input type="text" name="SCH_COMPET_DE" id="SCH_COMPET_DE" />
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for=""></label></td>
    					<td>
                            
                        </td>
    				</tr>
    				</tbody>	
    			</table>
                <div class="btnArea">
                     <input type="button" class="btn pri" value="검색" onclick="javascript:fnSearch();"/>
                </div>
    		</div>
    	</article>
    	
    	<article class="div9">
    		<h3 class="h3">포장공사 이력조회</h3>
    		<div id="div_grid" class="table">
				<table id="gridArea"></table>				
			</div>
			<div id="gridPager"></div>
        </div>
    	</article>
    	
    </div>






<div class="tabcont">
	<div class="fl bgsch">
	    <h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <li class="wid100">
	                
	            </li>
	            <li class="wid100">
	                <label>공사명</label>
	               
	            </li>
	            <li class="wid100">
	                <label></label>
	               
	            </li>
	            <li class="wid100">
	                <label>계약기간</label>
	               
	            </li>
	            <li class="wid100">
	                <a href="#" class="schbtn dpb" onclick="javascript:fnSearch();">검색</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>포장공사 이력조회1</h3>
	    <p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <strong>포장공사 이력조회</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
			<div class="mt10 tc">
	            <div class="fr">
	            	<a href="#" onclick="fn_cntrwkExcel();" class="schbtn">엑셀저장</a>
	            	<a href="#" onclick="fnWrite();" class="schbtn">포장공사 신규등록</a>
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
	// 달력 생성
	COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE', 'SCH_COMPET_DE', 10, 205);

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/cntrwk/selectCntrwkList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject())
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["공사ID","노선번호","공사구분","포장공사명","착공일","준공일","시공사","총 공사연장</br>(km)","총 보수금액</br>(천원)","총 공사면적</br>(㎡)","위치보기"] //,"하자시작일","하자종료일"
	   	,colModel:[
	   	    {name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
	   	 	,{name:'ROUTE_CODE',index:'ROUTE_CODE', hidden: true}
			,{name:'CNTRWK_SE',index:'CNTRWK_SE', align:'center', width:60}
			,{name:'FULL_CNTRWK_NM',index:'FULL_CNTRWK_NM', align:'left', width:100}
			,{name:'STRWRK_DE',index:'STRWRK_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'COMPET_DE',index:'COMPET_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			/* ,{name:'FLAW_BEGIN_DE',index:'FLAW_BEGIN_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'FLAW_END_DE',index:'FLAW_END_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}} */
			,{name:'CNSTRCT_CO_NM',index:'CNSTRCT_CO_NM', align:'left', width:70}
			,{name:'TRACK_LEN',index:'TRACK_LEN', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'TOT_AMOUNT',index:'TOT_AMOUNT', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'RPAIR_AR',index:'RPAIR_AR', align:'right', width:50,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, formatter: fn_create_btn, sortable: false}
	   	]
		,async : false
		,sortname: 'STRWRK_DE'
	    ,sortorder: "desc"
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
	   			$("#sidx").val(this.p.postData.sidx);
	   			this.p.postData.sord = this.p.sortorder;
	   			$("#sord").val(this.p.postData.sord);
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);

	fnSearch();
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
	   	}
	}).trigger("reloadGrid");
}

//상세 조회
function fnView(rowId) {
	if( $.type(rowId) === "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );

	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var cntrwkId = rowData["CNTRWK_ID"];
		var rotCode = rowData["ROUTE_CODE"];

		COMMON_UTIL.cmMoveUrl("cntrwk/selectCntrwkView.do?CNTRWK_ID="+cntrwkId);
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//공사목록 엑셀로 출력
function fn_cntrwkExcel(){
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
	    COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/cntrwk/cntrwkListExcel.do'/>", "");
	}
}

//신규 등록 화면 이동 [수정:선택] url
function fnWrite() {
	if (parent.is_ext == "Y") {
		COMMON_UTIL.cmMoveUrl("cntrwk/addCntrwkView.do");
	} else {
		COMMON_UTIL.cmMoveUrl("cntrwk/addCntrwkView.do");
	}
}

//유지보수 실적집계 엑셀 출력
function fn_cntrwkReportExcel(){
	COMMON_UTIL.cmWindowOpen("유지보수 실적집계 엑셀출력", "<c:url value='/cntrwk/setDownloadReport.do'/>", 320, 300, true, $("#wnd_id").val(), 'center');
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"fnViewLocation('" + rowObject.CNTRWK_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}
/*
function fn_select_cell(cntrwk_id){

	var action = '<c:url value="/api/cntrwk/selectCntrwkCellId.do"/>';

	$.ajax({
        url: action,
        contentType: 'application/json',
        data: JSON.stringify({"CNTRWK_ID" : cntrwk_id}),
        dataType: "json",
        type: 'POST',
        success : function (res) {
			if (res.res.length == 0) {
				alert("공사 위치가 없습니다.");
			} else {
				//cellId 배열 선언
				var tables=new Array();
				var fields=new Array();
				var cellList=new Array();
				for(var i in res.res){
					tables[i] = "CELL_10";
					fields[i] = "CELL_ID";
					cellList[i] = res.res[i].CELL_ID;
				}

				// 모든 팝업창 최소화
				parent.wWindowHideAll();
				// 하단 목록 창 내리기
				parent.bottomClose();
				//
				MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, cellList);
			}
		},
        error: function () {
            alert("지도 이동 오류가 발생하였습니다. 다시 화면 갱신후 진행하십시오.");
            return;
        }
	});
}
 */
//위치조회
function fnViewLocation(val){
	var cntrwkId = val;

	$.ajax({
		url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : cntrwkId })
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