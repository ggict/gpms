<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>노선별 통계 </title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript">
var chkcell={cellId:undefined, chkval:undefined};

//페이지 로딩 초기 설정
$( document ).ready(function() {

    var postData = {"USE_AT":"Y"};

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/stats/selectRoutStatsPageList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["도로등급","노선번호","노선명","총연장(km)","계","소계","2차로","4차로","공사구간","미개통구간","시도구간"]
        ,colModel:[
        	{name:'road_grad',index:'road_grad', align:'center', width:60, sortable:false,summaryType:'count', summaryTpl : ' ',cellattr:jsFormatterCell}
        	,{name:'route_code',index:'route_code', align:'center', width:60, sortable:false,summaryType:'count', summaryTpl : '전체'}
        	,{name:'route_name',index:'route_name', align:'center', width:60, sortable:false,summaryType:'count', summaryTpl : '{0}개 노선'}
        	,{name:'total_l',index:'total_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'sum_l',index:'sum_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'sub_sum_l',index:'sub_sum_l', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'track2_len',index:'track2_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'track4_len',index:'track4_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'cntrwk_len',index:'cntrwk_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'unopn_len',index:'unopn_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        	,{name:'sido_len',index:'sido_len', align:'center', width:50, sortable:false,formatter:'number',formatoptions:{decimalPlaces: 3},summaryType:'sum'}
        ]
        ,async : false
        ,sortname: 'route_code'
        ,sortorder: "desc"
        ,rowNum: 100
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
            }
        }
       ,grouping: true
       ,groupingView : {
            groupField : ['road_grad'],
            groupColumnShow : [true],
            groupText : ['<b>{0}</b>'],
            groupSummary : [true]
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

    var height = $(parent.window).height() - 300;
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);

    fnSearch();

    $("#gridArea").jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'road_grad', numberOfColumns: 4, titleText: ''},
                    {startColumnName: 'sum_l', numberOfColumns: 6, titleText: '도 관리구간(km)'}
                ]
            }).jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'sub_sum_l', numberOfColumns: 3, titleText: '포장구간'}
                ]
            })
});

//창 조절시 차트 resize
$(window).resize(function(){
    if(this.resizeTO) {
        clearTimeout(this.resizeTO);
    }
    this.resizeTO = setTimeout(function() {
        $(this).trigger('resizeEnd');
    }, 500);
})
$(window).on("resizeEnd", function(){
    //테이블 크기 조정
    var height = $(parent.window).height() - 300;
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
})

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
                var spans = $('td[rowspanid="'+this.id+'"]',grid).length+2;
                if(spans>1){
                 $(this).attr('rowspan',spans);
                }
            });

	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}


//도로등급 row 병합
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


//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectRoutStatsExcel.do'/>", "");
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
	                    <option value="">노선별통계</option>
	                </select>
	            </span>
	        </div>
	    </header>

	<!-- container2 S -->
	<div class="container2">

		<div class="tab">
			<a class="on" href="#div_grid" onclick="location.replace('<c:url value="viewRoutLenStats.do"/>');">상세보기</a>
			<a href="#divStatChart" onclick="location.replace('<c:url value="viewRoutLenStatsChart.do"/>');">그래프보기</a>
		</div>

		<div class="cont_ListBx">

            <div class="btnArea_top tabR">
                <a href="javascript:;" class="schbtn" onclick="fnExcel();">엑셀저장</a>
            </div>

            <div id="div_grid" >
                <table class="adminlist" id="gridArea"></table>
            </div>
		</div>

	</div>
	<!-- container2 E -->

</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>