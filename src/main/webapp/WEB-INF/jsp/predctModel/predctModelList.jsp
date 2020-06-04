<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div class="tabcont">

    <header class="loc">
        <div class="container">
            <span class="locationHeader">
                <h2 class="h2">예측모델</h2>
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
                        <td class="th"><label for="">대상년도</labed></td>
                        <td>
                            <select id="SLCTN_YEAR" name="SLCTN_YEAR">
                                <c:forEach var="slctnYear" items="${slctnYearList}">
                                    <option value="${slctnYear}">${slctnYear}년</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="btnArea">
                    <button type="button" class="btn pri" onclick="javascript:fn_showChartPredct($('#SLCTN_YEAR').val());">검색</button>
                </div>
            </div>
        </article>

        <article class="div9">
            <h3 class="h3">예측모델</h3>
            <div id="div_grid" class="table">
                <a href="#" class="schbtn" onclick="javascript:fnToggleChart();" id="btnToggleChart" style="float: right;">표로 보기</a>
                <div class="graylinebx p10">
                    <div id="rtChartPredct" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
                    <div id="rtGridPredct" class="cont_ConBx2 rtGridPredct"  style=" margin:30px auto;width:80%;display:none;">
                    <table id="rtGridPredctArea"  class="" style="width:80%;" ></table>
                    </div>
                </div>
            </div>
        </article>

    </div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript" language="javascript" defer="defer">
require.config({
    paths: {
         echarts: '<c:url value="/extLib/echarts" />' //js 파일 경로
    }
 });

var isChart = true;
var myChartPredct = null;

//경고 메시지 변수
var ntcNo=0;

//페이지 로딩 초기 설정
$( document ).ready(function() {
    fn_showChartPredct($("#SLCTN_YEAR").val());
});

/**
 * 공용성 예측 모델 차트/표 조회
 */
function fn_showChartPredct(slctn_year){
    var postData = {
        "SLCTN_YEAR" : slctn_year
    };

    var actionUrl = '<c:url value="/api/predctModel/selectPredctModelList.do"  />';
    $.ajax({
        url: actionUrl,
        data: JSON.stringify(postData),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (datas) {
            hideRTProgress();

            if ( !datas.lciData && !datas.aciData && !datas.patiData && !datas.rutiData && !datas.gpciData ) {
                fnShowMsgDialog("예측모델 조회", '데이터가 충분하지 차트가 표시되지 않습니다.', function() {
                });
                ntcNo += 1;
            } else {
                drawChartPredct(datas);
                showGridPredct(datas);
            }
        },
        error: function () {
            errNo += 1;
        }
    });
}

/**
 * 공용성 예측 모델 차트 조회
 */

function drawChartPredct(dataList){
    var rw = $(window).width() / 3;

    if ( myChartPredct != null ) {
        myChartPredct.clear();
        myChartPredct.dispose();
        myChartPredct = null;
    }

    var dataX = (dataList.lciData ? dataList.lciData.x : (dataList.aciData ? dataList.aciData.x : (dataList.patiData ? dataList.patiData.x : (dataList.rutiData ? dataList.rutiData.x : dataList.gpciData.x))));
    var series = [];
    $.each(dataList, function() {
        if ( this ) {
            var yData = {
                name: this.predctModelKndSe,
                type: 'line',
                smooth: true,
                data: this.y
            }
            series.push(yData);
        }
    });

    require(['echarts', 'echarts/chart/line' ], function(ec) {
        var option2 = {
            title   : { text: '예측모델'   },
            tooltip : {
//                 trigger : 'item', formatter : "경과년도 : {b}<br/>예측 ACI : {c}"
                trigger : 'axis'
            },
            toolbox : {
                show : true, feature : {
                    saveAsImage : {
                        show : true
                    }
                // 이미지저장
                }
            },
            legend: { data: ["LCI", "ACI", "PATI", "RUTI", "GPCI"] },
            grid :{
                x : 50,
                y2 : 80
            },
            xAxis : {
                        name: '경과년도',
                        type : 'category',
                        data : dataX
                    },
            yAxis :
                { name: '예측수치', type: 'value', min: 0, max: 10 }
            ,
            series : series
        }

        myChartPredct = ec.init(document.getElementById('rtChartPredct'));
        myChartPredct.setOption(option2);
    });
}

/**
 * 공용성 예측 모델 표 조회
 */
function showGridPredct(dataList){
    //rtGridDeptCode
    $("#rtGridPredctArea").jqGrid({
        datatype: "local"
        , ignoreCase: true
        , height : 139
        , width : 1000
        , colNames:['종류','경과년도','예측수치']
        , colModel:[
            {name:'predctModelKndSe',index:'predctModelKndSe', width:200, align: "center", hidden: false, sortable:false}
            ,{name:'x',index:'x', width:200, align: "center", hidden: false, sortable:false}
            ,{name:'y',index:'y',  width: 200,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2 }}
        ]
        , loadtext: "검색 중입니다."
        , footerrow: false
        , userDataOnFooter : false
        //,emptyrecords: "검색된 데이터가 없습니다."
        , multiselect: false
        , multiboxonly: false
    });
    $('#rtGridPredctArea').jqGrid('clearGridData');
    var i = 0;
    for (i = 0; dataList.lciData && i < dataList.lciData.x.length; i++){
        $("#rtGridPredctArea").jqGrid('addRowData', i + 1, {predctModelKndSe: "LCI", x: dataList.lciData.x[i], y: dataList.lciData.y[i]});
    }
    for (i = 0; dataList.aciData && i < dataList.aciData.x.length; i++){
        $("#rtGridPredctArea").jqGrid('addRowData', i + 1, {predctModelKndSe: "ACI", x: dataList.aciData.x[i], y: dataList.aciData.y[i]});
    }
    for (i = 0; dataList.patiData && i < dataList.patiData.x.length; i++){
        $("#rtGridPredctArea").jqGrid('addRowData', i + 1, {predctModelKndSe: "PATI", x: dataList.patiData.x[i], y: dataList.patiData.y[i]});
    }
    for (i = 0; dataList.rutiData && i < dataList.rutiData.x.length; i++){
        $("#rtGridPredctArea").jqGrid('addRowData', i + 1, {predctModelKndSe: "RUTI", x: dataList.rutiData.x[i], y: dataList.rutiData.y[i]});
    }
    for (i = 0; dataList.gpciData && i < dataList.gpciData.x.length; i++){
        $("#rtGridPredctArea").jqGrid('addRowData', i + 1, {predctModelKndSe: "GPCI", x: dataList.gpciData.x[i], y: dataList.gpciData.y[i]});
    }
}

/**
 * 차트 와 표 다이얼로그 토글 표시
 */
function fnToggleChart() {
    isChart = !isChart;
    fnShowChart();
}


/**
 * 메시지 다이얼로그 열기
 */
function fnShowMsgDialog(confirmTitle, msgContents, callback) {
    $("#divConfirmDialog").dialog({
        title : confirmTitle, width : "380px", close : callback
    });
    var buttonTags = "";

    buttonTags += '<a href="#" class="schbtn" onclick="closeConfirmDialog();" style="min-width:70px" >닫기</a>';

    $("#divConfirmButtns").html(buttonTags);
    $("#txtConfirmContents").html(msgContents);
    $("#divConfirmDialog").dialog("open");
}

function hideRTProgress(){
    parent.$("#dvProgress").dialog("close");
}

function fnShowChart(){

    if (isChart==false) {
        $('#rtGridPredct').show();
        $('#rtChartPredct').hide();
        $('#btnToggleChart').html("차트로 보기");
    } else {
        $('#rtGridPredct').hide();
        $('#rtChartPredct').show();
        $('#btnToggleChart').html("표로 보기");
    }
}

/**
 * 확인 다이얼로그 닫기
 */
function closeConfirmDialog() {
    $("#divConfirmDialog").dialog("close");
}
</script>

<div id="divConfirmDialog" class="content " style="display:none;z-index:9999;">
    <div class="txtbx" id="txtConfirmContents" >컨텐츠</div>
    <div class="tc mt20" id="divConfirmBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
    <div class="tc mt20" id="divConfirmButtns">버튼</div>
</div>
</body>
</html>