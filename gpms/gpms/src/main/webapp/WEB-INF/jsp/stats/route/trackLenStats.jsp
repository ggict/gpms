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
	
	//검색조건
	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
	if(sYear != ''){
		$("#label").text("도로등급별 도로연장 통계("+sYear+")");
	}
	
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
	fnLenSearch(sYear,rw);//국토부연장조회
	fnGpmsLenSearch(rw);//GPMS 총연장 조회
	fnAdmLenSearch(sYear,rw1);//국토부 관리기관별 시군구 총연장조회
	fnAdmGpmsLenSearch(rw1);//GPMS 관리기관별 시군구 총연장조회
	
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		$("#divStatChart").height($(parent.window).height() - 220);
	
		var rw1 = $(window).width()-400;
    	var rw = $(window).width()/3;
    	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
    	
    	fnTrackStatsSearch(sYear,rw1,rw);
});

//조건에 맞는 검색조회
function fnTrackStatsSearch(sYear,rw1,rw){
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
	fnLenSearch(sYear,rw);//국토부연장조회
	fnGpmsLenSearch(rw);//GPMS 총연장 조회
	fnAdmLenSearch(sYear,rw1);//국토부 관리기관별 시군구 총연장조회
	fnAdmGpmsLenSearch(rw1);//GPMS 관리기관별 시군구 총연장조회
	
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
function fnGradLenSearch(sYear,rw) {
	
	$("#STATS_YEAR").val(sYear);
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectSrvyUniRoadLenStatsResult.do',
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
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].ROAD_NAME});
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
		                    data:['국지도','지방도']
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
		                        radius : ['0%', '70%'],		                        
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
function fnGpmsGradLenSearch(deptCd,strDt,endDt,rw) {
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectGradLenStatsResult.do',
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
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}

function drawGpmsGradLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].ROAD_NAME});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mRoadLenChart'));
				 myChart.setOption({
		            	title 	: {	text: 'GPMS 총연장(km)'	},
		                tooltip : {	trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
		                legend: {
		                    orient : 'vertical',
		                    x : 'right',
		                    y : 'center',
		                    data:['국지도','지방도']
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
		                        radius : ['0%', '70%'],		                        
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
function fnLenSearch(sYear,rw) {
	$("#STATS_YEAR").val(sYear);
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectUniTrackLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		//,data: JSON.stringify(data)
		,dataType: 'json'
		,success: function (data) {
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
	var gNm 	= [];		
	var lenData		= [];
	var degree		= 0;
	if(dataList.length < 10){
		degree = 0;
	}
	
	for(var i=0; i<dataList.length; i++){
		if(dataList[i].LEN != '' && dataList[i].LEN != null){
			gNm.push(dataList[i].TRACK+'차로');
			lenData.push(dataList[i].LEN);
		}
	}

	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mUniTrackLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '국토부 총연장(km)'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	width : rw+'px',
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
		                			data : gNm
		                		}],
		                yAxis : [{	name : 'km',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
		                                      '#157ea8','#157ea8','#157ea8','#157ea8',
		                                      '#157ea8','#157ea8','#157ea8','#157ea8',
		                                      '#157ea8','#157ea8','#157ea8','#157ea8'
		                                    ];
		                                    return colorList[params.dataIndex]
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
function fnGpmsLenSearch(deptCd,strDt,endDt,rw) {
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectTrackLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		//,data: JSON.stringify(data)
		,dataType: 'json'
		,success: function (data) {
    	var dataList = data.data;
			if(dataList.length !=0){
				drawGpmsLenChart(dataList,rw);
			}else{
				ntcNo += 1;
			}
    },
    error: function () {
  	  errNo += 1;
    }
	});
}

function drawGpmsLenChart(dataList,rw){
	var gNm 	= [];		
	var lenData		= [];
	var degree		= 0;
	if(dataList.length < 10){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		if(dataList[i].TRACK != '' && dataList[i].TRACK != null){
			gNm.push(dataList[i].TRACK+'차로');
			lenData.push(dataList[i].LEN);
		}
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mTrackLenChart'));
				 myChart.setOption({
		            	title 	: {	text: 'GPMS 총연장(km)'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	/* width : rw+'px',
					    	x : 50, */
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : gNm
		                		}],
		                yAxis : [{	name : 'km',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
												'#157ea8','#157ea8','#157ea8','#157ea8',
												'#157ea8','#157ea8','#157ea8','#157ea8',
												'#157ea8','#157ea8','#157ea8','#157ea8'
		                                    ];
		                                    return colorList[params.dataIndex]
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
function fnAdmLenSearch(sYear,rw) {
	$("#STATS_YEAR").val(sYear);
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectUniAdmGradLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		//,data: JSON.stringify(data)
		,dataType: 'json'
		,success: function (data) {
    	var dataList = data.data;
			if(dataList.length !=0){
				drawUniLenChart(dataList,rw);
			}else{
				ntcNo += 1;
			}
    },
    error: function () {
  	  errNo += 1;
    }
	});
}

function drawUniLenChart(dataList,rw){
	var admNm 		= [];		
	var lenData		= [];
	var degree		= 90;
	var rowData		= new Object();
	var admLen      = [];
	
	if(dataList.length < 10){
		degree = 0;
	}
		if(dataList[0].ROAD_GRAD == '국지도'){
			rowData.name = '국지도';
			rowData.type = 'bar';
			rowData.stack = 'bar';
			
			for(var i=0; i<dataList.length; i++){
				if(dataList[i].ROAD_GRAD == '국지도'){
					admNm.push(dataList[i].ADM_NM);
					admLen.push(dataList[i].LEN);
				}
			}
			
	  		rowData.data = admLen;
			lenData.push(rowData);
			rowData		= new Object();
			admLen		= [];
		}
		
		if(dataList[1].ROAD_GRAD == '지방도') {
			rowData.name = '지방도';
			rowData.type = 'bar';
			rowData.stack = 'bar';
			for(var i=0; i<dataList.length; i++){
				if(dataList[i].ROAD_GRAD == '지방도'){
					admLen.push(dataList[i].LEN);
				}
			}
			
			rowData.data = admLen;
			lenData.push(rowData);
			rowData		= new Object();
			admLen		= [];
		}
		
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('lenBarChart'));
				 myChart.setOption({
		            	title 	: {	text: '국토부 총연장(km)'	},
		                tooltip : {	trigger: 'axis'				},
		                legend: {
		                    orient : 'vertical',
		                    x : 'right',
		                    y : 'center',
		                    data:['지방도','국지도']
		                },
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	/* width : rw+'px',
					    	x : 50, */
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : admNm
		                		}],
		                yAxis : [{	name : 'km',		type : 'value'		}],
		                series : lenData
		            });
				 
			});
}

//검색 처리
function fnAdmGpmsLenSearch(deptCd,strDt,endDt,rw) {
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectAdmGradLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		//,data: JSON.stringify(data)
		,dataType: 'json'
		,success: function (data) {
    	var dataList = data.data;
			if(dataList.length !=0){
				drawDeptGpmsLenChart(dataList,rw);
			}else{
				ntcNo += 1;
			}
    },
    error: function () {
  	  errNo += 1;
    }
	});
}

function drawDeptGpmsLenChart(dataList,rw){
	var admNm 	= [];		
	var lenData		= [];
	var degree		= 90;
	var rowData		= new Object();
	var admLen      = [];
	
	if(dataList.length < 10){
		degree = 0;
	}
	
	if(dataList[0].ROAD_GRAD == '국지도'){
		rowData.name = '국지도';
		rowData.type = 'bar';
		rowData.stack = 'bar';
		
		for(var i=0; i<dataList.length; i++){
			if(dataList[i].ROAD_GRAD == '국지도'){
				admNm.push(dataList[i].ADM_NM);
				admLen.push(dataList[i].LEN);
			}
		}
		
  		rowData.data = admLen;
		lenData.push(rowData);
		rowData		= new Object();
		admLen		= [];
	}
	
	if(dataList[1].ROAD_GRAD == '지방도') {
		rowData.name = '지방도';
		rowData.type = 'bar';
		rowData.stack = 'bar';
		for(var i=0; i<dataList.length; i++){
			if(dataList[i].ROAD_GRAD == '지방도'){
				admLen.push(dataList[i].LEN);
			}
		}
		
		rowData.data = admLen;
		lenData.push(rowData);
		rowData		= new Object();
		admLen		= [];
	}
		
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('gpmsLenBarChart'));
				 myChart.setOption({
		            	title 	: {	text: 'GPMS 총연장(km)'	},
		                tooltip : {	trigger: 'axis'				},
		                legend: {
		                    orient : 'vertical',
		                    x : 'right',
		                    y : 'center',
		                    data:['지방도','국지도']
		                },
		                toolbox : {	show: true,
			    			feature: {
				    			//dataView : {show: true, readOnly: false}, 	// 상세조회
				    			//saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	/* width : rw+'px',
					    	x : 50, */
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : admNm
		                		}],
		                yAxis : [{	name : 'km',		type : 'value'		}],
		                series : lenData
		            });
			});
}


//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		if(se == "road"){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectGradLenStatsResultExcel.do'/>", "");
		}else if(se == "dept"){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectDeptLenStatsResultExcel.do'/>", "");
		}else if(se == "track"){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectTrackLenStatsResultExcel.do'/>", "");
		}
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
			<span>노션 현황</span>
			<strong>차로별 통계</strong>
		</p>
	</div>
	<div class="cont_ListBx">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectTrackStats.do"/>');">상세보기</a>
        </div>
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
						<div id="mRoadLenChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;">도로등급별 도로 연장 통계(당해년도)</h4>
				</li>
				<li>
					<div class="graylinebx p10">
						<div id="mUniTrackLenChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
				</li>
				<li>
					<div class="graylinebx p10">
						<div id="mTrackLenChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
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
	</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>