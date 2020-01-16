<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>시군구별 통계 </title>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javascript">
var nYear;
var chkcell={cellId:undefined, chkval:undefined};
var chkcell2={cellId:undefined, chkval:undefined};
//페이지 로딩 초기 설정
$( document ).ready(function() {

	//검색조건초기화
	parent.$("#SCH_STATS_YEAR option:eq(0)").attr("selected", "selected");
	nYear = parent.$("#SCH_STATS_YEAR option:selected").val();

	var postData = {"USE_AT":"Y"};

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/stats/selectAdmStatsPageList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["시·군구별","도로등급","노선명","총연장(km)","계","소계","2차로","4차로","6차로","공사구간","미개통구간","시도구간"]
	   	,colModel:[
			{name:'adm_name',index:'adm_name', align:'center', width:60, sortable:false, cellattr: jsFormatterCell}
			,{name:'road_grad_nm',index:'road_grad_nm', align:'center', width:60, sortable:false, cellattr: jsFormatterCell2}
			,{name:'route_nm',index:'route_nm', align:'center', width:60, sortable:false}
			,{name:'total_l',index:'total_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'sum_l',index:'sum_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'sub_sum_l',index:'sub_sum_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'track2_len',index:'track2_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'track4_len',index:'track4_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'track6_len',index:'track6_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'cntrwk_len',index:'cntrwk_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'unopn_len',index:'unopn_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
			,{name:'sido_len',index:'sido_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3}}
	   	]
		,async : false
	   	,sortname: 'adm_nm'
	    ,sortorder: "desc"
	   	,rowNum: 999
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    //,rownumbers: true
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

    $("#gridArea").jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'adm_name', numberOfColumns: 4, titleText: ''},
                    {startColumnName: 'sum_l', numberOfColumns: 7, titleText: '도 관리구간(km)'}
                ]
            }).jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'sub_sum_l', numberOfColumns: 4, titleText: '포장구간'}
                ]
            })


	var height = $(parent.window).height() - 280;

	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);

	fnSearch();

	$(window).resize(function(){
		var height = $(parent.window).height() - 280;

		COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
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
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
            var grid = this;
            $('td[name="cellRowspan"]', grid).each(function() {
                var spans = $('td[rowspanid="'+this.id+'"]',grid).length+1;
                if(spans>1){
                 $(this).attr('rowspan',spans);
                }
            });
            $('td[name="cellRowspan2"]', grid).each(function() {
                var spans = $('td[rowspanid2="'+this.id+'"]',grid).length+1;
                if(spans>1){
                 $(this).attr('rowspan',spans);
                }
            });


	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}


function changRoutCol(yy){
	nYear = yy;
	var colModel = $("#gridArea").jqGrid('getGridParam', 'colModel');
    $("#gridArea").jqGrid('destroyGroupHeader'); //헤더 삭제(초기화 같은..)

	$("#gridArea").jqGrid('setGroupHeaders', {
		useColSpanStyle: true,
		  groupHeaders:[
			{startColumnName: 'SUM_L',
			 numberOfColumns: 3,
			 titleText: '<table style="width:100%;">'
			 			 +'<tr><td id="h0" colspan="3">국토부('+ nYear +')</td></tr>'
			 			 +'<tr><td> </td></tr></table>'}
			,{startColumnName: 'SUM_LEN',
			 numberOfColumns: 4,
			 titleText: '<table style="width:100%;">'
			 			 +'<tr><td id="h0" colspan="3">GPMS(당해년도)</td></tr>'
			 			 +'<tr><td></td><td id="h1" >중용구간(m)</td><td></td><td></td></tr></table>'
			}
		  ]
	});
}

function fnAdmStatsSearch(sYear){
	$("#STATS_YEAR").val(sYear);
	changRoutCol(sYear);
	fnSearch();
}

function jsFormatterCell(rowid, val, rowObject, cm, rdata){

	var result = "";

   if(chkcell.chkval != val){ //check 값이랑 비교값이 다른 경우
       var cellId = this.id + '_row_'+rowid+'-'+cm.name;
       result = ' rowspan="1" id ="'+cellId+'" + name="cellRowspan"';
       chkcell = {cellId:cellId, chkval:val};
   }else{
       result = 'style="display:none"  rowspanid="'+chkcell.cellId+'"'; //같을 경우 display none 처리
   }
   return result;
}

function jsFormatterCell2(rowid, val, rowObject, cm, rdata){
    var result = "";
    var concatValue = Object.entries(rdata).reduce(function(a,b) { return a+b[1]; }, '');

   if(chkcell2.chkval != concatValue){ //check 값이랑 비교값이 다른 경우
       var cellId = this.id + '_row_'+rowid+'-'+cm.name;
       result = ' rowspan="1" id ="'+cellId+'" + name="cellRowspan2"';
       chkcell2 = {cellId: cellId, chkval: concatValue};
   }else{
       result = 'style="display:none"  rowspanid2="'+chkcell2.cellId+'"'; //같을 경우 display none 처리
   }
   return result;
}

//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectAdmStatsPageListExcel.do'/>", "");
	}
}
</script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="ROUTE_CODE" name="ROUTE_CODE" value=""/>
<!-- container start -->
	<header class="loc">
	        <div class="container">
	            <span class="locationHeader">
	                <select name="">
	                    <option value="">통계</option>
	                </select>
	                <select name="">
	                    <option value="">노선별현황</option>
	                </select>
	                <select name="">
	                    <option value="">시군구별 통계</option>
	                </select>
	            </span>
	        </div>
	</header>

	<div class="container2">

		<div class="tab">
				<a class="on" href="#div_grid" onclick="location.replace('<c:url value="viewAdmLenStats.do"/>');">상세보기</a>
				<a href="#divStatChart" onclick="location.replace('<c:url value="viewAdmLenStatsChart.do"/>');">그래프보기</a>
		</div>
		<div class="btnArea_top tabR">
				<a href="#" class="schbtn" onclick="fnExcel();">엑셀저장</a>
		</div>
		<div id="div_grid" >
			<table class="adminlist" id="gridArea"></table>
		</div>
	</div>


<%-- 	<div id="sch_cnt01" class="tabcont">
		<h3>시군구별 도로연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>노선 현황</span>
			<strong>시군구별 통계 상세보기</strong>
		</p>
	</div>
	<div class="cont_ListBx">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectAdmStats.do"/>');">그래프보기</a>
          	<a href="#" class="schbtn" onclick="fnExcel();">엑셀저장</a>
        </div>
		<div id="div_grid" >
			<table class="adminlist" id="gridArea"></table>
		</div>
	</div>
</div> --%>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>