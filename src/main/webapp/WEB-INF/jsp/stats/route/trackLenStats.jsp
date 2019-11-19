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

//에러 메시지 변수
var errNo=0;
//경고 메시지 변수
var ntcNo=0;

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	 $("#divStatChart").height($(parent.window).height() - 220);

	//창 조절시 차트 width 
	var rw1 = $(window).width()-400;
	var rw = $(window).width()/3;
	
	
	fnTrackStatsSearch(rw);
	
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		$("#divStatChart").height($(parent.window).height() - 220);
	
		var rw1 = $(window).width()-400;
    	var rw = $(window).width()/3;
    	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
    	
    	fnTrackStatsSearch(rw);
});

//조건에 맞는 검색조회
function fnTrackStatsSearch(sYear,rw1,rw){
	fnGpmsGradLenSearch(rw);//GPMS 관리기관별 시군구 총연장조회
	
	if(sYear != ''){
		$("#label").text("도로등급별 도로연장 통계("+sYear+")");
	}else{
		$("#label").text("도로등급별 도로연장 통계(전체)");
	}
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});



//검색 처리
function fnGpmsGradLenSearch(rw) {
    
     $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectTrackLenStatsResult.do',
        data: JSON.stringify( $("#frm").cmSerializeObject()),
        //data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
            var dataList = data.data;
            if(dataList.length !=0){
                drawLenChart(dataList,rw);
            }else{
                ntcNo += 1;
                fn_msgNtc();
            }
        },
        error: function () {
            errNo += 1;
            fn_msgErr();
        }
    });
}

function drawLenChart(dataList,rw){
    var gDeptNm    = [];       
    var pavData     = [];
    var cntrwkData      = [];
    var unopnData = [];
    var degree = (dataList.length > 10) ? 40 : 0;
    
    for(var i=0; i<dataList.length; i++){
        gDeptNm.push(dataList[i].adm_nm);
        pavData.push(Number(dataList[i].total_l));
        cntrwkData.push(Number(dataList[i].cntrwk_len));
        unopnData.push(Number(dataList[i].unopn_len));
    }
    require([   'echarts','echarts/chart/bar'   ],
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
                data: ['포장구간', '공사구간', '미개통구간']
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
                        data : gDeptNm
                    }],
            yAxis : [{  name : 'km',        type : 'value'      }],
            series : [
                {
                    name: '포장구간',
                    type: 'bar',
                    stack: '합계',
                    itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                    data: pavData
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

//에러 메시지
function fn_msgErr(){
	if(errNo >= 1){
		alert("오류가 발생하였습니다.\n새로고침 하시기 바랍니다.");
		return;
	}else {
		return;
	}
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
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>차로별 도로연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>노선 현황</span>
			<strong>차로별 통계</strong>
		</p>
	</div>
	<div class="cont_ListBx">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectTrackStats.do"/>');">상세보기</a>
        </div>
        <div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">


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