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
//cell rowspan 중복 체크
//에러 메시지 변수
var errNo=0;
//경고 메시지 변수
var ntcNo=0;
var chkcell={cellId:undefined, chkval:undefined};
var nYear;
var rw;
var rw1;

var routeStatsTable_Utils = {
	toFiexd : function(arg, n){
		var formatNum = (arg) ? arg : 0;  
		return Number.parseFloat(formatNum).toFixed(n);
	}	 
 };
	 
//페이지 로딩 초기 설정
$( document ).ready(function() {
    
    //검색조건초기화
    parent.$("#SCH_STATS_YEAR option:eq(0)").attr("selected", "selected");
    nYear = parent.$("#SCH_STATS_YEAR option:selected").val();
    
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
        ,colNames:["도로등급","노선번호","노선명","총연장(km)","계","소계","2차로","4차로","공사구간","미개통구간"]
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
    
    var height = $(parent.window).height() - 250;
    
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
    
    $(window).resize(function(){
        var height = $(parent.window).height() - 250;
        
        COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
    });
    
}); 

//창 조절시  resize
$(window).on('resize', function(){
		
		//테이블 크기 조정
		var height = $(parent.window).height() - 250;		
		COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
		
		//차트크기 조정
		$("#divStatChart").height($(parent.window).height() - 220);	
    	rw = $(window).width()/3;
		rw1 = $(window).width()-400;
    	
});


require.config({
	   paths: {
	        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
	    }
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
                var spans = $('td[rowspanid="'+this.id+'"]',grid).length+2;
                if(spans>1){
                 $(this).attr('rowspan',spans);
                }
            });  
                        
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

	   		//차트그리기
	   		//var totalList = data.chartTotal;
        	//var dataList = data.chartData;
			//drawGradLenChart(totalList,rw);
			//drawGpmsGradLenChart(totalList,rw);
			//drawLenChart(dataList,rw);
	   		drawLenChart(data.rows,rw);

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
                    {startColumnName: 'road_grad', numberOfColumns: 4, titleText: ''},
                    {startColumnName: 'sum_l', numberOfColumns: 6, titleText: '도 관리구간(km)'}
                ]   
            }).jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'sub_sum_l', numberOfColumns: 3, titleText: '포장구간'}
                ]   
            })
}

function fnRoutStatsSearch(sYear){
		
	$("#STATS_YEAR").val(sYear);
	changRoutCol(sYear);
	
	if(sYear != ''){
		$("#label").text("도로등급별 도로연장 통계("+sYear+")");
	}else{
		$("#label").text("도로등급별 도로연장 통계(전체)");
	}
	
	fnSearch();
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
 
 function fnToggle(type){
	 if(type=='Chart'){
		 $("#statsChart").show();
		 $("#statsTable").hide();
	 }else {
		 $("#statsChart").hide(); 
		 $("#statsTable").show();
	 }
 }
 
 //국토부 
 function drawLenChart(dataList,rw){
    var gRouteNm    = [];       
    var twoData     = [];
    var fourData      = [];
    var cntrwkData      = [];
    var unopnData = [];
    var degree = (dataList.length > 10) ? 40 : 0;
    
    for(var i=0; i<dataList.length; i++){
        gRouteNm.push(dataList[i].route_code+" "+ dataList[i].route_name);
        twoData.push(Number(dataList[i].track2_len));
        fourData.push(Number(dataList[i].track4_len));
        cntrwkData.push(Number(dataList[i].cntrwk_len));
        unopnData.push(Number(dataList[i].unopn_len));
    }
 	require([	'echarts','echarts/chart/bar'	],
            function (ec) {
        var myChart = ec.init(document.getElementById('lenBarChart'));
        myChart.setOption({
            //color: ['#003366', '#4cabce'], 
            title  : { text: '총연장(km)' },
            tooltip : { trigger: 'axis'             },
            toolbox : { show: true,
                   feature: {
                       //dataView : {show: true, readOnly: false},     // 상세조회
                       //saveAsExcel : {show: true},                   // 엑셀저장
                       saveAsImage: {show: true}                   // 이미지저장
                   }   
            },
            legend: {
                data: ['2차로', '4차로', '공사구간', '미개통구간']
            },
            grid :{
                /* width : rw+'px',
                x : 50, */
                y2 : 100
            },
            xAxis : [{  
                        type : 'category',
                        axisLabel : {
                            show:true,
                            interval: 0,
                            rotate: degree
                        },
                        data : gRouteNm
                    }],
            yAxis : [{  name : 'km',        type : 'value'      }],
            series : [
                {
                    name: '2차로',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: twoData
                },
                {
                    name: '4차로',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: fourData
                },
                {
                    name: '공사구간',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: cntrwkData
                },
                {
                    name: '미개통구간',
                    type: 'bar',    
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: unopnData
                }
            ]
        });
        
   });
 }
 
//경고 메시지
 function fn_msgNtc(){
 	if(ntcNo >= 1){
 		alert("해당 조건에 검색 결과가 없습니다.\n검색 조건을 변경하여 조회 하시기 바랍니다.");
 		return;
 	}else {
 		return;
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
<input type="hidden" id="STATS_YEAR" name="STATS_YEAR" value=""/>
<!-- container start -->
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>노선별 도로연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>노선 현황</span>
			<strong>노선별 통계</strong>
		</p>
	</div>
	<div class="cont_ListBx"  id="statsTable">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="fnToggle('Chart')">그래프보기</a>
          	<a href="#" class="schbtn" onclick="fnExcel();">엑셀저장</a>
        </div>
		<div id="div_grid" >
			<table class="adminlist" id="gridArea"></table>
		</div>
	</div>
	<div class="cont_ListBx" id="statsChart">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="fnToggle('Table')">상세보기</a>
        </div>
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li style="margin-left: 1px;">
					<div class="graylinebx p10" style="width:195%;">
						<div id="lenBarChart" class="cont_ConBx2" style="height: 500px; margin-left:20px;"></div>
					</div>
				</li>
			</ul>
		</div>
	</div>	
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>