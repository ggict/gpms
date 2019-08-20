<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사평가 통계</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>포장상태 평가 노선별 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 평가</span>
			<strong>노선별 통계</strong>
		</p>

	    <div style="margin-top: 10px; margin-bottom: 45px;">
	        <a href="#" onclick="fnShowTable();" class="schbtn" style="float: right; margin-right: 8px;">상세조회</a>
	    </div>
		<!-- 그래프 START -->
		<div id="chartArea" style="overflow-y: auto; overflow-x: hidden;">
			<ul class="statsbx">
                <li style="float: none;">
                    <div class="graylinebx p10" style="width:195%;">
                        <div id="mRoutGPCIChart" class="cont_ConBx2" style="height: 310px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:195%;">노선별 포장상태 평가 통계</h4>
                </li>
                <li style="float: none; margin-top: 20px;">
                    <div class="graylinebx p10" style="width:195%;">
                        <div id="mRoutDMGChart" class="cont_ConBx2" style="height: 305px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:195%;">노선별 포장상태 파손원인 통계</h4>
                </li>
			</ul>
		</div>
		<!-- 그래프 END -->
	</div>
	<!-- 표 START -->
	<div class="cont_ListBx" style="display: none;">
		<table class="tblist" border="1" id="diagram">
			<colgroup>
				<col width="15%"/>
				<col width="25%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
			</colgroup>
            <thead style="text-align: center;">
				<tr>
					<th scope="col" rowspan="2">노선번호</th>
					<th scope="col" rowspan="2">노선명</th>
					<th scope="col" rowspan="2">GPCI</th>
					<th scope="col" colspan="3">파손원인</th>
				</tr>
				<tr>
                    <th scope="col">교통량/하부불량</th>
                    <th scope="col">기후</th>
                    <th scope="col">기타</th>
                </tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 표 END -->
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript" defer="defer">

//에러 메시지 변수
var errNo=0;
//경고 메시지 변수
var ntcNo=0;

//페이지 로딩 초기 설정
$( document ).ready(function() {

    parent.schFlag = 1;
    var height = $(window).height() - 370;
    $("#chartArea").height(height + "px");
    
    var height = 0;
    
    if ( parent.window.innerHeight < 700 ) {
    
        height = parent.window.innerHeight - 200;
        
    } else {
        
        height = parent.window.innerHeight - 100;
        
    }
    
    $("#chartArea").height(height + "px");

    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");
    /* parent.$("#schRoutCnt").show();
    parent.$("#schDeptCnt").hide();
    parent.$("#MUMM_ROAD_GRAD").val("");
    parent.$("#MUMM_ROAD_NO").val("");
    parent.$("#MUMM_ROAD_NAME").val(""); */

    var rw = parent.window.innerWidth - 320;
    var roadGrad = parent.$("#MUMM_ROAD_GRAD").val();
    var roadNo = parent.$("#MUMM_ROAD_NO").val();

    // 초기 그래프 search
    fnRoutSearch(roadGrad, roadNo, rw);

});

//창 조절시 차트 resize
$(window).on('resize', function(){

        var rw = parent.window.innerWidth - 320;
        var height = 0;
        
        if ( parent.window.innerHeight < 700 ) {
        
            height = parent.window.innerHeight - 200;
            
        } else {
            
            height = parent.window.innerHeight - 100;
            
        }
        $("#chartArea").height(height + "px");
        var roadGrad = parent.$("#MUMM_ROAD_GRAD").val();
        var routeCode = parent.$("#MUMM_ROAD_NO").val();

        fnRoutSearch(roadGrad, routeCode, rw);

});

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

// 검색 처리
function fnRoutSearch(roadGrad, routeCode, rw) {

    var data = {"ROAD_GRAD" : roadGrad, "ROUTE_CODE" : routeCode};

    $.ajax({
         url: '<c:url value="/"/>'+'api/mumm/mummRoutCntStatsGPCI.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (jdata) {

            if(jdata.length !=0){

                drawRoutGPCIChart(jdata, rw);
                drawRoutDMGChart(jdata, rw);
                drawTable(jdata);

            }else{

                ntcNo += 1;

            }
        },
        error: function () {
            errNo += 1;
        }
    });
}

// GPCI 차트 그리기
function drawRoutGPCIChart(dataList, rw) {

    var colors = ['#ffa126'];

    var gRouteNm    = [];
    var gpciData    = [];
    var degree      = 45;

    if ( dataList.length < 10 ) {

        degree = 0;

    }

    for ( var i = 0; i < dataList.length; i++ ) {

        gRouteNm.push(dataList[i].ROAD_NM + "(" + parseInt(dataList[i].ROUTE_CODE) + ")");
        gpciData.push(dataList[i].GPCI);

    }

    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('mRoutGPCIChart'));
                 myChart.setOption({
                        title   : { text: 'GPCI'   },
                        color   : colors,
                        tooltip : { trigger: 'axis'             },
                        toolbox : { show: true,
                            feature: {
                                dataView : {show: false, readOnly: false},   // 상세조회
                                saveAsExcel : {show: false},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }
                        },
                        grid :{
                            width : rw + 'px',
                            x : 50,
                            y2 : 80
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
                        yAxis : [{  name : '값',     type : 'value'      }],
                        series : [
                            {
                                name: '',
                                type: 'bar',
                                data: gpciData
                            }
                        ]
                    });

            });
}

// DMG 차트 그리기
function drawRoutDMGChart(dataList, rw) {

    var colors = ['#6495ed', '#32cd32', '#da70d6'];

    var gRouteNm     = [];
    var dmgClmtData  = [];
    var dmgVmtcData  = [];
    var dmgEtcData   = [];
    var degree       = 45;

    if ( dataList.length < 10 ) {

        degree = 0;

    }

    for ( var i = 0; i < dataList.length; i++ ) {

        gRouteNm.push(dataList[i].ROAD_NM + "(" + parseInt(dataList[i].ROUTE_CODE) + ")");
        dmgClmtData.push(dataList[i].DMG_CUZ_CLMT);
        dmgVmtcData.push(dataList[i].DMG_CUZ_VMTC);
        dmgEtcData.push(dataList[i].DMG_CUZ_ETC);

    }

    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('mRoutDMGChart'));
                 myChart.setOption({
                        title   : { text: '파손원인'   },
                        color    : colors,
                        tooltip : { trigger: 'axis'             },
                        toolbox : { show: true,
                            feature: {
                                dataView : {show: false, readOnly: false},   // 상세조회
                                saveAsExcel : {show: false},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }
                        },
                        legend: {
                            data : [ '교통량/하부불량', '기후', '기타' ]
                        },
                        grid :{
                            width : rw + 'px',
                            x : 50,
                            y2 : 80
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
                        yAxis : [{  name : '값',       type : 'value'      }],
                        series : [
                            {
                                name: '기타',
                                type: 'bar',
                                stack: 'cuz',
                                data: dmgEtcData
                            },
                            {
                                name: '기후',
                                type: 'bar',
                                stack: 'cuz',
                                data: dmgClmtData
                            },
                            {
                                name: '교통량/하부불량',
                                type: 'bar',
                                stack: 'cuz',
                                data: dmgVmtcData
                            }
                        ]
                    });

            });
}

function drawTable(dataList) {

    var mainData    = dataList;
    var tHtml       = '';

    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].ROUTE_CODE + '</td>';
        tHtml   += '<td>' + mainData[i].ROAD_NM + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].GPCI) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_VMTC) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_CLMT) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_ETC) + '</td>';
        tHtml   += '</tr>';
    }

    $('#diagram tbody').empty().append(tHtml);
}

//테이블 보기
function fnShowTable() {

    var roadGrad = parent.$("#MUMM_ROAD_GRAD").val();
    var roadNo = parent.$("#MUMM_ROAD_NO").val();
    var roadName = parent.$("#MUMM_ROAD_NAME").val();

    COMMON_UTIL.cmMoveUrl('<c:url value="mumm/mummRoutCntStats.do?ROAD_GRAD=' + roadGrad + '&ROAD_NO=' + roadNo + '&ROAD_NAME=' + roadName + '"/>');

}

//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/mumm/mummRoutCntStatsExcel.do'/>", "");
    }
}

//노선변호 String > Integer로 형변환
function fn_castRouteCode(routeCd){
    var routeNo = routeCd*1;
    return routeNo;
}


//에러 메시지
function fn_msgErr(){
    if(errNo >= 1){
        alert("오류가 발생하였습니다. 새로고침 하시기 바랍니다.");
        return;
    }else {
        return;
    }
}

//경고 메시지
function fn_msgNtc(){
    if(ntcNo >= 1){
        alert("해당 조건에 검색 결과가 없습니다. 검색 조건을 변경하여 조회 하시기 바랍니다.");
        return;
    }else {
        return;
    }
}

</script>

</body>
</html>