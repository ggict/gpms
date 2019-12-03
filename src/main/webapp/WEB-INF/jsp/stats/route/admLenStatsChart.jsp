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

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	$("#divStatChart").height($(parent.window).height() - 220);

	//창 조절시 차트 width 
	var rw = $(window).width()/3;
	var rw1 = $(window).width()-400;
	
	//검색조건
	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
	if(sYear != ''){
		$("#label").text("도로등급 도로연장 통계("+sYear+")");
	}
	
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
	fnLenSearch(sYear,rw1);//국토부연장조회
	fnGpmsLenSearch(rw1);//GPMS 총연장 조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		$("#divStatChart").height($(parent.window).height() - 220);
	
    	var rw = $(window).width()/3;
    	var rw1 = $(window).width()-400;
    	var sYear = parent.document.getElementById("SCH_STATS_YEAR").value;
    	
    	fnAdmStatsSearch(sYear,rw,rw1);
});

//조건에 맞는 검색조회
function fnAdmStatsSearch(sYear,rw,rw1){
	fnGradLenSearch(sYear,rw);//국토부 도로등급별 도로 연장통계 조회
	fnGpmsGradLenSearch(rw);//GPMS 도로등급별 도로연장 통계 조회
	fnLenSearch(sYear,rw1);//국토부연장조회
	fnGpmsLenSearch(rw1);//GPMS 총연장 조회
	
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
		lenData.push({"value" : Number(dataList[i].LEN), "name" : dataList[i].ROAD_NAME});
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
		if(dataList[i].ROAD_NAME != '' && dataList[i].ROAD_NAME != null){
			lenData.push({"value" : Number(dataList[i].LEN), "name" : dataList[i].ROAD_NAME});
		}
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
		 url: '<c:url value="/"/>'+'api/cell10/selectUniAdmLenStatsResult.do'
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
			}
      },
      error: function () {
    	  errNo += 1;
      }
	});
}

function drawLenChart(dataList,rw){
	var gRouteNm 	= [];		
	var lenData		= [];
	var degree		= 90;
	if(dataList.length < 10){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		if(dataList[i].ADM_NM != '' && dataList[i].ADM_NM != null){
			gRouteNm.push(dataList[i].ADM_NM);
			lenData.push(Number(dataList[i].LEN));
		}
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('lenBarChart'));
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
		                                       '#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC'
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
		 url: '<c:url value="/"/>'+'api/cell10/selectAdmLenStatsResult.do'
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
				fn_msgNtc();
			}
      },
      error: function () {
    	  errNo += 1;
    	  fn_msgErr();
      }
	});
}

function drawGpmsLenChart(dataList,rw){
	var gAdmNm 	= [];		
	var lenData		= [];
	var degree		= 90;
	if(dataList.length < 10){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		if(dataList[i].ADM_NM != '' && dataList[i].ADM_NM != null){
			gAdmNm.push(dataList[i].ADM_NM);
			lenData.push(Number(dataList[i].LEN));
		}
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('gpmsLenBarChart'));
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
		                			data : gAdmNm
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
		                                       '#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC'
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
<input type="hidden" id="ROUTE_CODE" name="ROUTE_CODE" value=""/>
	
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
                        <td class="th">
                            <label for="admSelect">노선번호</label>
                        </td>
                        <td>
                            <select id="admSelect"><option>전체</option>
                                <c:forEach items="${admList}" var="adm">
                                <option value="${adm.CODE_VAL}">${adm.CODE_NM}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="btnCell"><button type="button" class="btn pri">검색</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
	    
		<div class="tab">
				<a href="#div_grid" onclick="location.replace('<c:url value="selectAdmStatsTable.do"/>');">상세보기</a>
				<a class="on" href="#divStatChart" onclick="location.replace('<c:url value="selectAdmStats.do"/>');">그래프보기</a>	
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
		
		


	</div>

</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>