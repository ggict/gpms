<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사통계 </title>
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
	
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	//검색조건 모두 hide
	var divSearch = parent.document.getElementById("divSearch");
	divSearch.style.display="none";

	//창 조절시 차트 width 
	var rw = $(window).width()/3;
	var rw1 = $(window).width()-150;
	
	fnRoutLenSearch(rw1);//노선별조사거리조회
	fnDeptLenSearch(rw);//관리기관도로연장조회
	fnRoadLenSearch(rw);//도로등급별도로연장조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		var rw1 = $(window).width()-150;
    	var rw = $(window).width()/3;
    	
    	fnRoutSearch(rw1,rw);
});

//조건에 맞는 검색조회
function fnRoutSearch(rw1,rw){
	$("#divStatChart").height($(parent.window).height() - 220);
	
	fnRoutLenSearch(rw1);//노선별조사거리조회
	fnDeptLenSearch(rw);//관리기관도로연장조회
	fnRoadLenSearch(rw);//도로등급별도로연장조회
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnRoutLenSearch(rw) {
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectSrvyRoutLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		,dataType: 'json'
		,success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawRoutLenChart(dataList,rw);
				//drawTable(dataList,'rout');
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
	});
}

function drawRoutLenChart(dataList,rw){
	var gRouteNm 	= [];		
	var lenData		= [];
	var degree		= -45;
	if(dataList.length < 10){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		
		gRouteNm.push(dataList[i].ROAD_NAME + "(" + dataList[i].ROUTE_CODE + ")");
		lenData.push(dataList[i].LEN);
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mRoutLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '연장(km)'	},
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
					    	x : 20, */
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
		                yAxis : [{	name : 'km',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
											'#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
											'#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
											'#FE2752','#7EEA59','#F2B731','#D8C35C','#41C0AE',
											'#FE8422','#6EEA54','#F1B541','#D6C32C','#73C1AE',
											'#FD8610','#CDEF60','#FD6ACE','#E6D410','#20C5AD',
											'#DD810E','#CCDA80','#BE88CA','#D21810','#31D2BA',
											'#EE91AE','#EFCBCD','#CDE1BB','#CDE991','#98613B',
											'#FADE06','#BB78FF','#ECCDC0','#FBA001','#E6734C',
											'#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC',
											'#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
											'#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
											'#FE2752','#7EEA59','#F2B731','#D8C35C','#41C0AE',
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
function fnDeptLenSearch(rw) {
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectSrvyDeptLenStatsResult.do',
        data: JSON.stringify( $("#frm").cmSerializeObject()),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawDeptLenChart(dataList,rw);
				//drawTable(dataList,'dept');
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}

function drawDeptLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].DEPT_NM});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mDeptLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '연장(km)'	},
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
		                        	}
		                        },
		                        data: lenData
		                    }
		                ]
		            });

			});	
}

//검색 처리
function fnRoadLenSearch(rw) {
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectSrvyRoadLenStatsResult.do',
        data: JSON.stringify( $("#frm").cmSerializeObject()),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawRoadLenChart(dataList,rw);
				//drawTable(dataList,'road');
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

function drawRoadLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].ROAD_NAME});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mRoadLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '연장(km)'	},
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

function drawTable(dataList,se){
	var mainData	= dataList;
	var tHtml 		= '';
	
	if(se == 'rout'){
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	fn_castRouteCode(mainData[i].ROUTE_CODE)+ '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_NAME					+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	mainData[i].LEN					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram tbody').empty().append(tHtml);
	}else if(se == 'dept'){
		
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	fn_castRouteCode(mainData[i].DEPT_CODE)	+ '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].DEPT_NM						+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	mainData[i].LEN					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram1 tbody').empty().append(tHtml);
	}else if(se == 'road'){
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_GRAD					+ '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_NAME					+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	mainData[i].LEN					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram2 tbody').empty().append(tHtml);
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
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>조사구간 통계정보</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 조사구간</span>
			<strong>조사구간 통계정보</strong>
		</p>
	</div>
	<div class="cont_ListBx">
		<div class="btnbx mb10">
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectSurveyAllLenStatsTable.do"/>');">상세보기</a>
        </div>	
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li style="float: none;">
					<div class="graylinebx p10" style="width:195%;">
						<div id="mRoutLenChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;width:195%;">노선별 조사거리 통계</h4>
				</li>
				<li style="margin-top:30px;">
					<div class="graylinebx p10">
						<div id="mDeptLenChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;">관리기관별 도로 연장 통계</h4>
				</li>
				<li style="margin-top:30px;">
					<div class="graylinebx p10">
						<div id="mRoadLenChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					</div>
					<h4 style="text-align: center;background:none;">도로등급별 도로 연장 통계</h4>
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