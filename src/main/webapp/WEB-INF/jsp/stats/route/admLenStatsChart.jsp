<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선별 통계 </title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript" defer="defer">
var errNo=0; //에러 메시지 변수
var ntcNo=0; //경고 메시지 변수
var myChart;

//페이지 로딩 초기 설정
$( document ).ready(function() {

    $("#divStatChart").height($(parent.window).height() - 220);

    var selectedAdm = $('#selectAdm option:selected').val();
    fnAdmStatsSearch(selectedAdm);

    $('#selectAdm').change(function() {
        var selectedAdm = this.value;
        fnAdmStatsSearch(selectedAdm);
    })
    $('#btnSearch').click(function() {
        var selectedAdm = $('#selectAdm option:selected').val();
        fnAdmStatsSearch(selectedAdm);
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
    $("#divStatChart").height($(parent.window).height() - 220);
    myChart.resize();
})

//검색 처리
function fnAdmStatsSearch(selectedAdm) {

    var data = { 'ADM_CODE': selectedAdm };

    $.ajax({
       url: '<c:url value="/"/>'+'api/stats/selectAdmStatsPageChart.do',
       //data: JSON.stringify( $("#frm").cmSerializeObject()),
       data: JSON.stringify(data),
       contentType: 'application/json',
       dataType: "json",
       cache: false,
       type: 'POST',
       processData: false,
       success: function (data) {
           var dataList = data.rows;
           if(dataList.length !=0){
               drawLenChart(dataList);
           }else{
               ntcNo += 1;
               COMMON_UTIL.fn_msgNtc(ntcNo);
           }
       },
       error: function () {
           errNo += 1;
           COMMON_UTIL.fn_msgErr(errNo);
       }
   });
}

// 차트
require.config({
	   paths: {
	        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
	    }
	});
function drawLenChart(dataList){
	var degree = (dataList.length > 10) ? 40 : 0;

    var admList = dataList.map(function(elem) { return elem.adm_name });
    var roadGradLlist = dataList.map(function(elem) { return elem.road_grad_nm });
    var routeCodeList = dataList.map(function(elem) { return elem.route_nm });
    var track2Data = dataList.map(function(elem) { return elem.track2_len });
    var track4Data = dataList.map(function(elem) { return elem.track4_len });
    var track6Data = dataList.map(function(elem) { return elem.track6_len });
    var cntrwkData = dataList.map(function(elem) { return elem.cntrwk_len });
    var unopnData = dataList.map(function(elem) { return elem.unopn_len });
    var sidoData = dataList.map(function(elem) { return elem.sido_len });

    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
        myChart = ec.init(document.getElementById('lenBarChart'));
        myChart.setOption({
            //color: ['#003366', '#4cabce'],
            //title  : { text: '차로 연장', x:'left' },
            title    : { text: admList[0], x:'left' },
            tooltip : { trigger: 'axis'             },
            toolbox : { show: true,
                   feature: {
                       //dataView : {show: true, readOnly: false},     // 상세조회
                       //saveAsExcel : {show: true},                   // 엑셀저장
                       saveAsImage: {show: true}                   // 이미지저장
                   }
            },
            legend: {
                data: ['2차로', '4차로', '6차로', '공사구간', '미개통구간', '시도구간'],
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'bottom',
                borderWidth: 0
            },
            grid :{
//                  width : '50%',
                y2 : 100,
                align: 'center'
            },
            xAxis : [
            	{
                    type : 'category',
                    position: 'bottom',
                    offset: 0,
                    axisLabel : {
                        inside: true,
                        show:true,
                        interval: 0,
                        rotate: degree
                    },
                    data : routeCodeList
                }
            	],
            yAxis : [{   name: '총연장(km)',        type : 'value'      }],
            series : [
                {
                    name: '2차로',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: track2Data
                },
                {
                    name: '4차로',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: track4Data
                },
                {
                    name: '6차로',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: track6Data
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
                },
                {
                    name: '시도구간',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: sidoData
                }
            ]
        });

   });
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
<input type="hidden" id="ADM_CODE" name="ADM_CODE" value=""/>

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

        <div class="table searchBox top">
            <table>
                <tbody>
                    <tr>
                        <td class="th" style="width:50%;">
                            <label for="temp"></label>
                        </td>
                        <td class="th">
                            <label for="selectAdm">시군구</label>
                        </td>
                        <td>
                            <select id="selectAdm">
                                <c:forEach items="${admList}" var="adm">
                                <option value="${adm.CODE_VAL}">${adm.CODE_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="btnCell"><button type="button" id="btnSearch"  class="btn pri">검색</button></td>
                    </tr>
                </tbody>
            </table>
        </div>

		<div class="tab">
				<a href="#div_grid" onclick="location.replace('<c:url value="viewAdmLenStats.do"/>');">상세보기</a>
				<a class="on" href="#divStatChart" onclick="location.replace('<c:url value="viewAdmLenStatsChart.do"/>');">그래프보기</a>
		</div>

        <div style="text-align: center;">
          <div id="lenBarChart" class="cont_ConBx2" style="height: 500px;margin:30px 0 auto;"></div>
        </div>

		<!--
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li>
					<div class="graylinebx p10">
						<div id="gradLenPieChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 id='label' style="text-align: center;background:none;">도로등급별 도로연장 통계()</h4>
				</li>
				<li>
					<div class="graylinebx p10">
						<div id="gpmsGradLenPieChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;">도로등급별 도로연장 통계(당해년도)</h4>
				</li>
				<li>
					<div class="graylinebx p10" style="width:195%;">
						<div id="lenBarChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
				</li>
				<li style="margin-left: 1px;">
					<div class="graylinebx p10" style="width:195%;">
						<div id="gpmsLenBarChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
				</li>
			</ul>
		</div>
		 -->

	</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>