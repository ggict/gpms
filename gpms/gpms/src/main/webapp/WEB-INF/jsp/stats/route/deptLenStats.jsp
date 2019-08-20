<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관리기관별 통계 </title>
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
	
	//검색조건
	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
	if(sYear != ''){
		$("#label").text("관리기관별 도로연장 통계("+sYear+")");
	}
	
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		$("#divStatChart").height($(parent.window).height() - 220);
		
		var rw1 = $(window).width()-400;
    	var rw = $(window).width()/3;
    	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
    	
    	fnDeptStatsSearch(sYear,rw1,rw);
});

//조건에 맞는 검색조회
function fnDeptStatsSearch(sYear,rw1,rw){
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
	
	if(sYear != ''){
		$("#label").text("관리기관별 도로연장 통계("+sYear+")");
	}else{
		$("#label").text("관리기관별 도로연장 통계(전체)");
	}
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnGradLenSearch(sYear,rw) {
	$("#STATS_YEAR").val(sYear);
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectSrvyUniDeptLenStatsResult.do',
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
				drawGradLenChart(dataList,rw);
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}

function drawGradLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].DEPT_NM});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('gradLenPieChart'));
				 myChart.setOption({
		            	title 	: {	text: '국토부 총연장(km)'	},
		                tooltip : {	trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
		                legend: {
		                    orient : 'vertical',
		                    x : 'right',
		                    y : 'center',
		                    data:['경기도건설본부']
		                },
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
			    		calculable : true,
		                series : [
		                    {
		                        name: '',
		                        type: 'pie',
		                        radius : ['30%', '70%'],		                        
		                        itemStyle: {
		                        	normal : {
		                        		label : {
		                        			show : true,
		                        			formatter: '{a}{b} \n {c} \n({d}%)',
		                        			position: 'inner',
			                        		textStyle : {
					                        	color:'#000000'
		                        			}
		                        		},
		                        		labelLine : {
		                        			show : false
		                        		}
		                        	},
		                        	emphasis : {
		                        		label : {
		                        			show : false,
		                        			position : 'center',
		                        			textStyle : {
		                        				fontSize : '50',
		                        				fontWeight : 'bold'
		                        			}
		                        		}
		                        	}
		                        },
		                        data: lenData
		                    }
		                ]
		            });

			});	
}

//검색 처리
function fnGpmsGradLenSearch(rw) {
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectDeptLenStatsResult.do',
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
				drawGpmsGradLenChart(dataList,rw);
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

function drawGpmsGradLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].DEPT_NM});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('gpmsGradLenPieChart'));
				 myChart.setOption({
		            	title 	: {	text: 'GPMS 총연장(km)'	},
		                tooltip : {	trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
		                legend: {
		                    orient : 'vertical',
		                    x : 'right',
		                    y : 'center',
		                    data:['북부도로과','도로건설과']
		                },
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
			    		calculable : true,
		                series : [
		                    {
		                        name: '',
		                        type: 'pie',
		                        radius : ['30%', '70%'],		                        
		                        itemStyle: {
		                        	normal : {
		                        		label : {
		                        			show : true,
		                        			formatter: '{a}{b} \n {c} \n({d}%)',
		                        			position: 'inner',
			                        		textStyle : {
					                        	color:'#000000'
		                        			}
		                        		},
		                        		labelLine : {
		                        			show : false
		                        		}
		                        	},
		                        	emphasis : {
		                        		label : {
		                        			show : false,
		                        			position : 'center',
		                        			textStyle : {
		                        				fontSize : '50',
		                        				fontWeight : 'bold'
		                        			}
		                        		}
		                        	}
		                        },
		                        data: lenData
		                    }
		                ]
		            });

			});	
}

//노선변호 String > Integer로 형변환
function fn_castRouteCode(routeCd){
	var routeNo = routeCd*1;
	return routeNo;
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
		<h3>관리기관별 도로연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>노션 현황</span>
			<strong>관리기관별 통계</strong>
		</p>
	</div>
	<div class="cont_ListBx">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectDeptStats.do"/>');">상세보기</a>
        </div>
        <div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li>
					<div class="graylinebx p10">
						<div id="gradLenPieChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 id ='label'  style="text-align: center;background:none;">관리기관별 도로연장 통계()</h4>
				</li>
				<li>
					<div class="graylinebx p10">
						<div id="gpmsGradLenPieChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;">관리기관별 도로연장 통계(당해년도)</h4>
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