<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>조사구간현황</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.min.js'/>"></script>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SRVY_YEAR" name="SRVY_YEAR" value="${srvyUnSectionVO.SRVY_YEAR}"/>
<input type="hidden" id="ROAD_NO" name="ROAD_NO" value="${srvyUnSectionVO.ROAD_NO}"/>

<form id="frm" name="frm" method="post" action="">
	<!-- 막대그래프 -->
	<div style="width: 700px;" >
		<div id="lenBarChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
	</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	//차트 데이터
	fu_getChartData();
	
});

function fu_getChartData(){
	var srvy_year = $('#SRVY_YEAR').val();
	var road_no = $('#ROAD_NO').val();
	var usePage = 'false';
	
    $.ajax({
        url: contextPath + 'api/srvyunsection/selectsrvyunsectionchartlist.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_NO : road_no, SRVY_YEAR: srvy_year, usePage: usePage})
        ,success: function(data){
        	if(data && data.rows){
        		//차트 그리기	
	        	var rows = data.rows;
	        	drawLenChart(rows);
        	}
        }
        ,error: function(a,b,msg){
        	
        }
    });
	
}

//차트
function drawLenChart(dataList,rw){
 	var gRouteNm 	= [];		
 	var lenData		= [];
 	var GpmlenData	= [];
 	var ManageLen	= [];
 	for(var i=0; i<dataList.length; i++){
 			lenData.push(Number(dataList[i].TOTAL_ROAD_L));
 			GpmlenData.push(Number(dataList[i].ROAD_L));
 			gRouteNm.push(dataList[i].dept_NAME);
 			ManageLen.push(Number(dataList[i].DO_MANAGE_SCTN_LEN));
 	}
	 
	var myChart = echarts.init(document.getElementById('lenBarChart'));
	myChart.setOption({
		color : [ '#003366', '#4cabce', '#FF0000' ],
		title : {
			text : '조사구간현황'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
			//dataView : {show: true, readOnly: false}, 	// 상세조회
			//saveAsExcel : {show: true},					// 엑셀저장
			//saveAsImage: {show: true}					// 이미지저장
			}
		},
		legend : {
			data : [ '총연장', '도 관리구간 연장' ,'조사연장' ]
		},
		grid : {
			/* width : rw+'px',
			x : 50, */
			y2 : 100
		},
		xAxis : [ {
			type : 'category',
			axisLabel : {
				show : true,
				interval : 0
			},
			data : gRouteNm
		} ],
		yAxis : [ {
			name : 'km',
			type : 'value'
		} ],
		series : [ {
			name : '총연장',
			type : 'bar',
			data : lenData
		}, {
			name : '도 관리구간 연장',
			type : 'bar',
			data : ManageLen
		}, {
			name : '조사연장',
			type : 'bar',
			data : GpmlenData
		} ]
	});
}
</script>
</body>
</html>