<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>신고자기준 신고현황</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
</head>
<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidxexcel" name="sidxexcel" value=""/>
<input type="hidden" id="sordexcel" name="sordexcel" value=""/>

<div class="tabcont bsnmPeriod">
	<div class="fl bgsch" style="height: 1300px">
	    <h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">

	        	<li class="wid100">
                    <label>접수경로</label>
                    <select id="PTHMODE" name="PTHMODE" class="input" style="width:75%;" onkeydown="fnCheckEnter(event);" onchange="fnpthmodenm();" >
                        <option value="">== 전체 ==</option>
                        <c:forEach items="${mtdtList}" var="mtdt">
                            <option value="${mtdt.CODE_VAL}">${mtdt.CODE_NM}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" id="pthmodenm">
                </li>

	            <li class="wid100">
	                <label>신고자</label>
	                <input type="text" name="BSNM_NM" id="BSNM_NM" value="" style="width: 73%;" class="MX_80 CS_50 input" onkeydown="fnCheckEnter(event);" />
	            </li>

	            <li class="wid100" style="margin: 4px 0px;">
	                <label>신고기간</label>
	                <a href="#" id="btnPeriod" class="stmstBsnm on" onclick="fnChangePeriod($(this)); return false;" style="color: white;">기간지정</a>
	                <a href="#" id="btnQuarter" class="stmstBsnm" onclick="fnChangePeriod($(this)); return false;" style="padding: 4px 35px; color: white;">분기별</a>
	            </li>

	            <!-- 기간지정 -->
	            <li class="wid100" id="dvPeriod">
                    <label></label>
                    <span class="calendar" style="height: 22px">
                        <input type="text" id="STTEMNT_DT_START" name="STTEMNT_DT_START" style="width:70px; font-size: 8px;" onkeydown="fnCheckEnter(event);" />
                    </span> ~
                    <span class="calendar" style="height: 22px">
                        <input type="text" id="STTEMNT_DT_END" name="STTEMNT_DT_END" style="width:70px; font-size: 8px;" onkeydown="fnCheckEnter(event);" />
                    </span>
	            </li>

	            <!-- 분기별 -->
	            <li class="wid100" id="dvQuarter" style="display: none;">
                    <label></label>

                    <select style="width:37%;" id="year">
                    </select>

                    <select style="width:37%;" id="qutr">
                        <option value="qt1" id="qt1">1분기</option>
                        <option value="qt2" id="qt2">2분기</option>
                        <option value="qt3" id="qt3">3분기</option>
                        <option value="qt4" id="qt4">4분기</option>
                    </select>
                </li>

	            <li class="wid100" style="margin-top: 10px;">
	                <a href="#" class="schbtn dpb" onclick="javascript: fn_search(); ">검색</a>
	            </li>

	            <li class="wid100" style="margin-top: 12px;text-align: center;">
                    <span style="font-size: 13px;color: #676a6d;font-weight: bold;">19년 00월부터 도민모니터링단 조회가능</span>
                </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>신고자기준 신고현황</h3>
	    <div class="mt5 ml10 mr10">

            <div class="btnbx mb10" style="margin-right: 25px; text-align: left;">
            	<span id="tl1" class="sttsth2" ><span id="pthmodenmnm"></span></span>
                <span class="sttsth2" >신고자기준 신고현황 조회 (<span id="strtDt"></span> ~ <span id="endDt"></span>)</span>

                <!-- 버튼 -->
                <a href="#" onclick="fnShowTable();" class="schbtn bsnm-period-chart" style="position: relative; top: 8px; margin-right: 5px; float: right;">상세조회</a> &nbsp;
	            <a href="#" onclick="fnShowChart();" class="schbtn bsnm-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">차트조회</a> &nbsp;
	            <a href="#" onclick="fnExcel();" class="schbtn bsnm-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">엑셀다운로드</a>
	        </div>
	        <div class="bsnm-period-chart" style="text-align: center; display: none;">
                <div class="legend-box" style="background-color: #383838;"></div><div class="legend-text">전체신고</div>
                <div class="legend-box" style="background-color: #c00000;"></div><div class="legend-text">신고</div>
                <div class="legend-box" style="background-color: #ffc000;"></div><div class="legend-text">접수</div>
                <div class="legend-box" style="background-color: #92d050;"></div><div class="legend-text">처리완료</div>
                <div class="legend-box" style="background-color: #4bacc6;"></div><div class="legend-text">보수예정</div>
                <div class="legend-box" style="background-color: #8064a2;"></div><div class="legend-text">재배정요청</div>
                <div class="legend-box" style="background-color: #f381e5;"></div><div class="legend-text">기타</div>
                <div class="legend-box" style="background-color: #aaaaaa;"></div><div class="legend-text">좌표오류</div>
	        </div>

            <!-- 테이블 START -->
            <div class="bsnm-period-list" id="div_grid" style="width:99%; height:206px;">
				<table id="gridArea"></table>
				<!-- <div id="gridPager"></div> -->
			</div>
			<!-- 테이블 END -->

			<!-- 그래프 START -->
            <div id="chartArea" class="bsnm-period-chart" style="overflow-y: hidden; overflow-x: auto; width: 1600px;">
                <ul class="statsbx">
                    <li style="float: none;">
                        <div>
                            <div id="bsnmChart" class="cont_ConBx2" style="height: 600px; margin-left:20px;"></div>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- 그래프 END -->
        </div>
    </div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript" language="javascript" defer="defer">

var listCnt = 0;
var list = null;
var mode = 'table'; // table : 상세보기, chart : 그래프

var startDt;
var endDt;

//창 조절시 차트 resize
$(window).on('resize', function(){

    drawBsnmPeriodChart(list, parent.innerWidth - 310);

});

$( document ).ready(function() {

    // 2018. 01. 22. JOY. 경기도로 모니터링단 시스템
    // 필수액션 : 서브메뉴 버튼영역 hide =============== //
    parent.$(".tab_wrap").css("margin-left", "0px");
    // ================================================= //

    // 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('STTEMNT_DT_START', 'STTEMNT_DT_END', 30);
    COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_DT_START', 'RPAIR_DT_END', 30);

    // 30일 전 Default Setting
    $('#STTEMNT_DT_START').val($.datepicker.formatDate('yy-mm-dd', new Date(new Date-(3600000*24*30))));
    $('#STTEMNT_DT_END').val($.datepicker.formatDate('yy-mm-dd', new Date()));

    // title에 날짜 setting
    $("#strtDt").text($("#STTEMNT_DT_START").val());
    $("#endDt").text($("#STTEMNT_DT_END").val());
    $("#pthmodenmnm").text($("#pthmodenm").val());

    // 분기 setting
    var minDate = "${minyear}";
    var maxDate = "${maxyear}";

    var minYear = minDate.substr(0, 4) * 1; // 최소년도
    var maxYear = maxDate.substr(0, 4) * 1; // 최대년도

    var minMonth = minDate.substr(4, 2) * 1; // 최소년도 최소월
    var maxMonth = maxDate.substr(4, 2) * 1; // 최대년도 최대월

    var minQuater = (( minMonth / 3 ) <= 1 ) ? 1 : ((( minMonth / 3 ) <= 2 ) ? 2 : (( minMonth / 3 ) <= 3 ? 3 : 4 ) ); // 최소분기
    var maxQuater = (( maxMonth / 3 ) <= 1 ) ? 1 : ((( maxMonth / 3 ) <= 2 ) ? 2 : (( maxMonth / 3 ) <= 3 ? 3 : 4 ) ); // 최대분기

    var optionHtml = "";

    // 년도 setting
    for ( var i = minYear; i <= maxYear; i++ ) {

        // 분기 setting
        /* var minIdx = 1;
        var maxIdx = 4;

        if ( i == minYear ) {

            minIdx = minQuater;

        }

        if ( i == maxYear ) {

            maxIdx = maxQuater;

        }

        for ( var j = minIdx; j <= maxIdx; j++ ) {

            if ( i == maxYear && j == maxIdx ) {

                optionHtml += "<option value='qt" + j + "-" + i + "' selected='selected'>" + i + "년도-" + j + "분기</option>";

            } else {

                optionHtml += "<option value='qt" + j + "-" + i + "'>" + i + "년도-" + j + "분기</option>";

            }

        } */

        optionHtml += "<option value='" + i + "'>" + i + "년도</option>";

    }

    $("#dvQuarter select#year").html(optionHtml);
    $(".bsnm-period-chart").hide();

    fnGridArea();

});

function fnpthmodenm(){
	if($("#PTHMODE").val() == 'MTDT001'){
		$("#pthmodenm").val('스마트 카드');
	} else if($("#PTHMODE").val() == 'MTDT002'){
		$("#pthmodenm").val('도민모니터링단');
	} else {
		$("#pthmodenm").val('');
	}
}

/* ===================================================================== */
/* =============================== TABLE =============================== */
// Grid Table 그리기
function fnGridArea() {

    var postData = $("#frm").cmSerializeObject();

    // 분기별 기간 setting
    startDt = $("#STTEMNT_DT_START").val();
    endDt = $("#STTEMNT_DT_END").val();

    var year = $("#year").val();

    if ( $("#btnQuarter").hasClass("on") ) {

        var quater = $("#dvQuarter select#qutr").val();
        // var year = quater.substr(4, 4);

        if ( quater.startsWith("qt1") ) {

            startDt = year + "-01-01";
            endDt = year + "-03-31";

        } else if ( quater.startsWith("qt2") ) {

            startDt = year + "-04-01";
            endDt = year + "-06-30";

        } else if ( quater.startsWith("qt3") ) {

            startDt = year + "-07-01";
            endDt = year + "-09-30";

        } else {

            startDt = year + "-10-01";
            endDt = year + "-12-31";

        }

    }

    postData["STTEMNT_DT_START"] = startDt;
    postData["STTEMNT_DT_END"] = endDt;
    postData["sidx"] = "DEPT_CODE";
    postData["sord"] = "ASC";

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/pothole/sttst/selectBsnmPeriodList.do'
        , autoencode: true
        , contentType : 'application/json'
        , datatype: "local"
        , mtype: "POST"
        , ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        , postData: postData
        , ignoreCase: true
        , colNames:["부서코드", "지역구분","신고자","차량번호","전체신고(건)", "신고(건)","접수(건)","처리완료(건)", "보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율<br/>(처리완료÷전체)"]
        , colModel:[
            {name: 'DEPT_CODE'          , index: 'DEPT_CODE'        , align: 'center'   , hidden: true  , sortable: true}
            , {name: 'LOWEST_DEPT_NM'   , index: 'LOWEST_DEPT_NM'   , align: 'center'   , width: 100    , sortable: true}
            , {name: 'BSNM_NM'          , index: 'BSNM_NM'          , align: 'center'   , width: 100    , sortable: true}
            , {name: 'VHCLE_NO'         , index: 'VHCLE_NO'         , align: 'center'   , width: 100    , sortable: true}
            , {name: 'PRCS_STTUS'       , index: 'PRCS_STTUS'       , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS1'      , index: 'PRCS_STTUS1'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS2'      , index: 'PRCS_STTUS2'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS3'      , index: 'PRCS_STTUS3'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS4'      , index: 'PRCS_STTUS4'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS5'      , index: 'PRCS_STTUS5'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS6'      , index: 'PRCS_STTUS6'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PRCS_STTUS7'      , index: 'PRCS_STTUS7'      , align: 'center'   , width: 100    , sortable: true, formatter: 'number', formatoptions: {decimalPlaces: 0}}
            , {name: 'PERCENT'          , index: 'PERCENT'          , align: 'center'   , width: 100    , sortable: true, formatter: fnPercent}
        ]
        , async : false
        , sortname: 'BSNM_NM'
        , sortorder: "asc"
        , rowNum: 1000
        , rowList: [1000]
        , viewrecords: true
        , rownumbers: true
        , pager: '#gridPager'
        , loadtext: "검색 중입니다."
        , emptyrecords: "검색된 데이터가 없습니다."
        , recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        , ondblClickRow: function(rowId) { }
        , onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
            }
        }
        , loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                $("#sidxexcel").val(this.p.postData.sidx);
                this.p.postData.sord = this.p.sortorder;
                $("#sordexcel").val(this.p.postData.sord);
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        , multiselect: false
        , multiboxonly: false

    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    var height = $(parent.window).height() - 300;
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);

    fn_search();

    $(window).resize(function(){

        var height = $(parent.window).height() - 300;
        COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);

    });

}

//검색 처리
function fn_search() {

    var postData = $("#frm").cmSerializeObject();

    // 분기별 기간 setting
    startDt = $("#STTEMNT_DT_START").val();
    endDt = $("#STTEMNT_DT_END").val();

    var year = $("#year").val();

    if ( $("#btnQuarter").hasClass("on") ) {

        var quater = $("#dvQuarter select#qutr").val();
        //var year = quater.substr(4, 4);

        if ( quater.startsWith("qt1") ) {

            startDt = year + "-01-01";
            endDt = year + "-03-31";

        } else if ( quater.startsWith("qt2") ) {

            startDt = year + "-04-01";
            endDt = year + "-06-30";

        } else if ( quater.startsWith("qt3") ) {

            startDt = year + "-07-01";
            endDt = year + "-09-30";

        } else {

            startDt = year + "-10-01";
            endDt = year + "-12-31";

        }

    }

    postData["STTEMNT_DT_START"] = startDt;
    postData["STTEMNT_DT_END"] = endDt;
    postData["sidx"] = "DEPT_CODE";
    postData["sord"] = "ASC";

    $("#strtDt").text(startDt);
    $("#endDt").text(endDt);
    $("#pthmodenmnm").text($("#pthmodenm").val());

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

            listCnt = data.records;
            list = data.rows;

            var rw = parent.innerWidth - 310;
            drawBsnmPeriodChart(data.rows, rw);

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

        }
    }).trigger("reloadGrid");

}

// 퍼센트
function fnPercent(cellValue, options, rowObject) {

    return rowObject.PERCENT + "%";

}

// 그래프 보기
function fnShowChart() {

    mode = 'chart';
    $(".bsnm-period-list").hide();
    $(".bsnm-period-chart").show();

}

// 엑셀 다운로드
function fnExcel() {

    if ( listCnt == 0 ) {

        alert("검색된 데이터가 없습니다.");
        return;

    }

    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
    	$('#STTEMNT_DT_START').attr("disabled", "disabled");
        $('#STTEMNT_DT_END').attr("disabled", "disabled");
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectBsnmPeriodListExcel.do'/>?STTEMNT_DT_START="+startDt+"&STTEMNT_DT_END="+endDt+"&sidx=" + $("#sidxexcel").val() + "&sord=" + $("#sordexcel").val(), "");

        $('#STTEMNT_DT_START').removeAttr("disabled");
        $('#STTEMNT_DT_END').removeAttr("disabled");
    }
}

/* ===================================================================== */
/* =============================== CHART =============================== */

// Script
require.config({
    paths: {
         echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
     }
 });

// Draw Chart
function drawBsnmPeriodChart(dataList, rw) {

    var height = parent.innerHeight - 250;
    $("#bsnmChart").height( (height - 50) + "px" );
    $("#chartArea").height( height + "px");

    var width = parent.innerWidth - 320;
    $("#chartArea").width(width + "px");

    if  ( listCnt <= 12 ) {

        $("#bsnmChart").width( (width - 50) + "px" );

    } else {

        $("#bsnmChart").width( (92 * listCnt) + "px" );

    }

    var colors = ['#ffa126'];

    var gBsnmNm    = [];
    var gPrcsSttus = [];
    var gPrcsSttus1 = [];
    var gPrcsSttus2 = [];
    var gPrcsSttus3 = [];
    var gPrcsSttus4 = [];
    var gPrcsSttus5 = [];
    var gPrcsSttus6 = [];
    var gPrcsSttus7 = [];
    var degree      = 0;

    for ( var i = 0; i < dataList.length; i++ ) {

        if ( dataList[i].BSNM_NM != "합계" ) {

	        gBsnmNm.push(dataList[i].BSNM_NM);
	        gPrcsSttus.push(dataList[i].PRCS_STTUS);
	        gPrcsSttus1.push(dataList[i].PRCS_STTUS1);
	        gPrcsSttus2.push(dataList[i].PRCS_STTUS2);
	        gPrcsSttus3.push(dataList[i].PRCS_STTUS3);
	        gPrcsSttus4.push(dataList[i].PRCS_STTUS4);
	        gPrcsSttus5.push(dataList[i].PRCS_STTUS5);
	        gPrcsSttus6.push(dataList[i].PRCS_STTUS6);
	        gPrcsSttus7.push(dataList[i].PRCS_STTUS7);

        }

    }

    // 차트영역 show (hide 상태에서는 차트가 안그려짐)
    //$(".bsnm-period-chart").show();

    require([   'echarts','echarts/chart/bar'   ],
    function (ec) {
         var myChart = ec.init(document.getElementById('bsnmChart'));
         myChart.setOption({

                /* title   : { text: '신고자기준 신고현황' }, */
                tooltip : {
                trigger : 'axis',
                axisPointer : {
                     type : 'shadow'
                }
             },
             toolbox : { show: true,
                    feature: {
                        dataView : {show: false, readOnly: false},   // 상세조회
                        saveAsExcel : {show: false},                 // 엑셀저장
                        saveAsImage: {show: false}                   // 이미지저장
                    }
             } ,
            /* legend: {
                data: ['신고', '접수', '처리완료', '보수예정', '재배정요청', '기타', '좌표오류']
            }, */
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%'
            },
            xAxis:  {
                //type: 'value'
                type: 'category'
                ,axisLabel : {
                    show:true,
                    interval: 0,
                    rotate: degree
                },
                data: gBsnmNm
            },
            yAxis: {
                //type: 'category',
                //data: gBsnmNm
                type: 'value'
            },
            series: [

                {
                    name: '전체　신고',
                    type: 'bar',
                    data: gPrcsSttus,
                    itemStyle : {
                        normal: {
                            color : "#383838",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '신　　　고',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus1,
                    itemStyle : {
                        normal: {
                            color : "#c00000",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '접　　　수',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus2,
                    itemStyle : {
                        normal: {
                            color : "#ffc000",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '처리　완료',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus3,
                    itemStyle : {
                        normal: {
                            color : "#92d050",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '보수　예정',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus4,
                    itemStyle : {
                        normal: {
                            color : "#4bacc6",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '재배정요청',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus5,
                    itemStyle : {
                        normal: {
                            color : "#8064a2",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '기　　　타',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus6,
                    itemStyle : {
                        normal: {
                            color : "#f381e5",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                },
                {
                    name: '좌표　오류',
                    type: 'bar',
                    stack: '总量',
                    data: gPrcsSttus7,
                    itemStyle : {
                        normal: {
                            color : "#aaaaaa",
                            label : {
                                show: true,
                                position: 'insideBottom',
                                formatter: '{c}건'
                            }
                        }
                    }
                }
            ]

            }
         );

         if ( mode == 'table' ) {

          $(".bsnm-period-list").show();
          $(".bsnm-period-chart").hide();

         } else if ( mode == 'chart' ) {

             $(".bsnm-period-list").hide();
             $(".bsnm-period-chart").show();

         }
    });

}

// 테이블 보기
function fnShowTable() {

    mode = 'table';
    $(".bsnm-period-list").show();
    $(".bsnm-period-chart").hide();

}

/* ===================================================================== */
/* =============================== COMMON ============================== */

// 검색조건 enter check function
function fnCheckEnter(event) {

    if ( event.keyCode == 13 ) {

        fn_search();
    }
}

// 버튼 on off function
function fnChangePeriod(obj) {

    // on 클래스 초기화
    obj.parent().find("a").removeClass("on");
    obj.addClass("on");

    if ( obj.attr("id") == "btnPeriod" ) {

        $("#dvPeriod").show();
        $("#dvQuarter").hide();

    } else if ( obj.attr("id") == "btnQuarter" ) {

        $("#dvPeriod").hide();
        $("#dvQuarter").show();
    }

}

</script>

</body>
</html>