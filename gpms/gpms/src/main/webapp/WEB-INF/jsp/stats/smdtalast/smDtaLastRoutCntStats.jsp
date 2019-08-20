<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장상태 예측 통계</title>
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
<input type="hidden" id="prc_mode" name="prc_mode" value="ROUTE"/>
<input type="hidden" id="PREDCT_SEND_ROUTE_CODE" name="ROUTE_CODE" value=""/>
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>노선별 포장상태 예측</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 예측</span>
			<strong>노선별 포장상태 예측</strong>
		</p>
		<!-- 그래프 START -->
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
                <li style="float: none; width:97%;">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="sLGradGPCIChart" class="cont_ConBx2" style="height: 310px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:100%;">노선별 포장상태 평가 예측 추이(10년)</h4>
                </li>
                <li style="float: none; margin-top: 20px; width:97%;">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="sLRoutGPCIChart" class="cont_ConBx2" style="height: 305px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:100%;">노선별 포장상태 평가 예측 추이(10년)</h4>
                </li>
			</ul>
		</div>
		<!-- 그래프 END -->
	</div>
	<!-- 표 START -->
	<div id="gradGpciDiagram" style="display: none;">
		<div class="cont_ListBx">
			<table class="tblist" border="1">
				 <colgroup>
			 		<col width="31%"/>
			 		<col width="23%"/>
			 		<col width="23%"/> 
			 			<col width="23%"/>
			 		</colgroup>
					<thead style="text-align: center;">
						<tr>
							<th scope="col" rowspan="2">예측 년도</th>	
							<th scope="col" colspan="3">GPCI</th>
						</tr>	
						<tr>
							<th scope="col">국지도</th>
							<th scope="col">지방도</th>
							<th scope="col">경기도 전체</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
			</table>
		</div>
	</div>
	<div id="routeGpciDiagram" style="display: none;">
		<div class="cont_ListBx">
			<table class="tblist" border="1">
				 <colgroup>
			 		<col width="40%"/>
			 		<col width="30%"/>
			 		<col width="30%"/> 
		 		</colgroup>
				<thead style="text-align: center;">
					<tr>
						<th scope="col">예측년도</th>
						<th scope="col">노선번호</th>
						<th scope="col">GPCI</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
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
    
    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");
    parent.$("#predctRoutCnt").show();
    parent.$("#predctDeptCnt").hide();
    parent.$("#PREDCT_ROAD_GRAD").val("");
    parent.$("#PREDCT_ROAD_NO").val("");
    parent.$("#PREDCT_ROAD_NAME").val("");
    
    var roadGrad = parent.$("#PREDCT_ROAD_GRAD").val();
    var roadNo = parent.$("#PREDCT_ROAD_NO").val();
    
    $("#divStatChart").height($(parent.window).height() - 170);
    
    // 도로구분별 GPCI 차트 조회
    fnGradGPCISearch();
 	// 노선별 GPCI 차트 조회(기본값은 391번 노선)
    fnRouteGPCISearch('0391');
    
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
	$("#divStatChart").height($(parent.window).height() - 170);
	
    var routeCode = $("#PREDCT_SEND_ROUTE_CODE").val();
    
    fnGradGPCISearch();
    fnRouteGPCISearch(routeCode);
        
});

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

/**
 * 도로구분별 GPCI 통계 조회
 * author : skc
 * 2017-11-24
 */
function fnGradGPCISearch() {
    
    var data = {"prc_mode" : "ROUTE"};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/smdtalaststtus/selectSmDtaLastStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (jdata) {
            
            if(jdata.length !=0){
                
                drawGradGPCIChart(jdata);
                drawGradGpciTable(jdata);
                
            }else{
                
                ntcNo += 1;
                
            }
        },
        error: function () {
            errNo += 1;
        }
    });
}

/**
 * 노선별 GPCI 통계 조회
 * author : skc
 * 2017-11-24
 * routeCode : 노선 번호
 */
function fnRouteGPCISearch(routeCode) {
	if(routeCode == "" || typeof routeCode == "undefined"){
		alert("노선번호를 선택해주시기 바랍니다.");
		return;
	}
	
	$("#PREDCT_SEND_ROUTE_CODE").val(routeCode);
    
    var data = {"ROUTE_CODE" : routeCode, "prc_mode" : "ROUTE"};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/smdtalaststtus/selectSmDtaLastStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (jdata) {
            
            if(jdata.length !=0){
                
                drawRouteGPCIChart(jdata);
                drawRouteGpciTable(jdata);
                
            }else{
            	alert("해당 노선 정보가 존재하지 않습니다");
                ntcNo += 1;
                
            }
        },
        error: function () {
            errNo += 1;
        }
    });
}


/**
 * 도로구분별 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawGradGPCIChart(dataList) {
    
   // var colors = ['#ffa126'];
    
    var NPRgpciData    = []; //국지도 GPCI 데이터
    var PRgpciData    = [];	//지방도 GPCI 데이터
    var AllGpciData    = []; //전체 GPCI 데이터
    var PredctYearData    = []; //예측년도 데이터
    
    for ( var i = 0; i < dataList.length; i++ ) {
        
    	NPRgpciData.push(parseFloat(dataList[i].ROAD_GPCI_1));
    	PRgpciData.push(parseFloat(dataList[i].ROAD_GPCI_2));
    	AllGpciData.push(parseFloat(dataList[i].ROAD_GPCI_3));
    	PredctYearData.push(dataList[i].PREDCT_YEAR);
    }
    
    require([   'echarts','echarts/chart/bar', "echarts/chart/line"   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('sLGradGPCIChart'));
                 myChart.setOption({
                        title   : { text: '경기도 전체(55개 노선)'   
                        			, x : 'center'},
                        tooltip : { trigger: 'axis'             },
                        legend: {
			            	   x: 'right',
			                   y: 'center',
			                   orient : 'vertical',
			                   data: ['국지도', '지방도', '경기도 전체'] 
			               },
                        toolbox : { show: true,
                            feature: {
                                dataView : {id: 'gradGpciDiagram',show: true, readOnly: false},   // 상세조회
                                saveAsExcel : {show: true},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }   
                        },
                        grid :{
					        x2 : 150,
					        containLabel: true
                        },
                        xAxis : [{  
                        			name : '공용연수',
                                    type : 'category',
                                    axisLabel : {
                                        show:true,
                                        interval: 0,
                                        rotate: 0
                                    },
                                    data : PredctYearData
                                }],
                        yAxis : [{  name : 'GPCI',     type : 'value'      }],
                        series : [
    				                   {
    				                       name: '국지도',
    				                       type: 'line',
    				                       symbol:'emptyCircle',
    				                       data: NPRgpciData
    				                   },
    				                   {
    				                       name: '지방도',
    				                       type: 'line',
    				                       symbol:'emptyCircle',
    				                       data: PRgpciData
    				                   },
    				                   {
    				                       name: '경기도 전체',
    				                       type: 'line',
    				                       symbol:'emptyCircle',
    				                       data: AllGpciData
    				                   }
    				               ]
                    });
                 
            });
}


/**
 * 도로구분별 GPCI 표 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawGradGpciTable(dataList) {
	var mainData    = dataList;
    var tHtml      ='';    
    
    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].PREDCT_YEAR + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].ROAD_GPCI_1) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].ROAD_GPCI_2) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].ROAD_GPCI_3) + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#gradGpciDiagram .tblist tbody').empty().append(tHtml);
}

/**
 * 노선별 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawRouteGPCIChart(dataList) {
        
	    var gradNm    = dataList[0].ROAD_GRAD; //도로구분
	    var routeCode  = parseInt(dataList[0].ROUTE_CODE);	//노선코드
	    var titleNm = gradNm + " " + routeCode + "호선";
	    var gpciData    = []; //전체 GPCI 데이터
	    var PredctYearData    = []; //예측년도 데이터
	    
	    for ( var i = 0; i < dataList.length; i++ ) {
	        
	    	gpciData.push(parseFloat(dataList[i].GPCI));
	    	PredctYearData.push(dataList[i].PREDCT_YEAR);
	    }
	    
	    require([   'echarts','echarts/chart/bar', "echarts/chart/line"   ],
	            function (ec) {
	                 var myChart = ec.init(document.getElementById('sLRoutGPCIChart'));
	                 myChart.setOption({
	                        title   : { text: titleNm   
	                        			, x : 'center'},
	                        tooltip : { trigger: 'axis'             },
	                        legend: {
				            	   x: 'right',
				                   y: 'center',
				                   orient : 'vertical',
				                   data: [titleNm] 
				               },
	                        toolbox : { show: true,
	                            feature: {
	                                dataView : {id: 'routeGpciDiagram',show: true, readOnly: false},   // 상세조회
	                                saveAsExcel : {show: true},                 // 엑셀저장
	                                saveAsImage: {show: true}                   // 이미지저장
	                            }   
	                        },
	                        grid :{
						        x2 : 150,
						        containLabel: true
	                        },
	                        xAxis : [{  
	                        			name : '공용연수',
	                                    type : 'category',
	                                    axisLabel : {
	                                        show:true,
	                                        interval: 0,
	                                        rotate: 0
	                                    },
	                                    data : PredctYearData
	                                }],
	                        yAxis : [{  name : 'GPCI',     type : 'value'      }],
	                        series : [
	    				                   {
	    				                       name: titleNm,
	    				                       type: 'line',
	    				                       symbol:'emptyCircle',
	    				                       data: gpciData
	    				                   }
	    				               ]
	                    });
	                 
	            });
	}

/**
 * 노선별 GPCI 표 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawRouteGpciTable(dataList) {
	var mainData    = dataList;
    var tHtml      ='';    
    
    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].PREDCT_YEAR + '</td>';
        tHtml   += '<td>' + parseInt(mainData[i].ROUTE_CODE) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].GPCI) + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#routeGpciDiagram .tblist tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel(se) {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
    	if(se == "sLGradGPCIChart"){
        	COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/smdtalaststtus/selectSmDtaLastStatsGradExcel.do'/>", "");
    	}else if(se == "sLRoutGPCIChart"){
    		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/smdtalaststtus/selectSmDtaLastStatsRouteExcel.do'/>", "");
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