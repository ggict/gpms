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
<input type="hidden" id="prc_mode" name="prc_mode" value="DEPT"/>
<input type="hidden" id="PREDCT_SEND_DEPT_CODE" name="DEPT_CODE" value=""/>
<input type="hidden" id="PREDCT_SEND_ROUTE_CODE" name="ROUTE_CODE" value=""/>
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>관리기관별 포장상태 예측</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 예측</span>
			<strong>관리기관별 포장상태 예측</strong>
		</p>
		<!-- 그래프 START -->
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
                <li style="float: none; width:97%;">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="sLDeptListGPCIChart" class="cont_ConBx2" style="height: 310px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:100%;">관리기관별 포장상태 평가 예측 추이(10년)</h4>
                </li>
                <li style="float: none; margin-top: 20px; width:97%;">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="sLDeptGPCIChart" class="cont_ConBx2" style="height: 305px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:100%;">관리기관별 포장상태 평가 예측 추이(10년)</h4>
                </li>
                 <li style="float: none; margin-top: 20px; width:97%;">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="sLRoutGPCIChart" class="cont_ConBx2" style="height: 305px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:100%;">관리기관별 포장상태 평가 예측 추이(10년)</h4>
                </li>
			</ul>
		</div>
		<!-- 그래프 END -->
	</div>
	<!-- 표 START -->
	<div id="deptListGpciDiagram" style="display: none;">
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
							<th scope="col">북부도로과</th>
							<th scope="col">도로건설과</th>
							<th scope="col">경기도 전체</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
			</table>
		</div>
	</div>
	<div id="deptGpciDiagram" style="display: none;">
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
						<th scope="col">관리기관명</th>
						<th scope="col">GPCI</th>
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
    parent.$("#predctRoutCnt").hide();
    parent.$("#predctDeptCnt").show();
    parent.$("#PREDCT_DEPT_CODE").val("");
    parent.$("#PREDCT_D_ROAD_NO").val("");
    parent.$("#PREDCT_D_ROAD_NAME").val("");
    
    var deptCd = parent.$("#PREDCT_DEPT_CODE").val();
    var roadNo = parent.$("#PREDCT_D_ROAD_NO").val();
        
    $("#divStatChart").height($(parent.window).height() - 170);
    
    // 부서별 전체 GPCI 차트 조회
    fnDeptListGPCISearch();
 	// 부서별 GPCI 차트 조회
    fnDeptGPCISearch('6411799');
 	// 노선별 GPCI 차트 조회(기본값은 391번 노선)
    fnRouteGPCISearch('0391');
 	
    //창 조절시 차트 resize
    $(window).on('resize', function(){
    	$("#divStatChart").height($(parent.window).height() - 170);
    	
    	var deptCd = $("#PREDCT_SEND_DEPT_CODE").val();
        var roadNo = $("#PREDCT_SEND_ROUTE_CODE").val();
        fnDeptListGPCISearch();
        fnDeptGPCISearch(deptCd);
        fnRouteGPCISearch(roadNo);
            
    });
}); 



require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

/**
 * 관리기관별 전체 GPCI 통계 조회
 * author : skc
 * 2017-11-24
 */
function fnDeptListGPCISearch() {
    
    var data = {"prc_mode" : "DEPT"};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/smdtalaststtus/selectSmDtaLastStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (jdata) {
            
            if(jdata.length !=0){
                
                drawDeptListGPCIChart(jdata);
                drawDeptListGpciTable(jdata);
                
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
 * 관리기관별 GPCI 통계 조회
 * author : skc
 * 2017-11-24
 * routeCode : 노선 번호
 */
function fnDeptGPCISearch(deptCode) {
	if(deptCode == "" || typeof deptCode == "undefined"){
		return;
	}

	$("#PREDCT_SEND_DEPT_CODE").val(deptCode);
	
    var data = {"DEPT_CODE" : deptCode, "prc_mode" : "DEPT"};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/smdtalaststtus/selectSmDtaLastStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (jdata) {
            
            if(jdata.length !=0){
                
                drawDeptGPCIChart(jdata);
                drawDeptGpciTable(jdata);
                
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
 * 관리기관별 전체 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawDeptListGPCIChart(dataList) {
    
   // var colors = ['#ffa126'];
    
    var NgpciData    = []; //북부도로과 GPCI 데이터
    var SgpciData    = [];	//도로건설과 GPCI 데이터
    var AllGpciData    = []; //전체 GPCI 데이터
    var PredctYearData    = []; //예측년도 데이터
    
    for ( var i = 0; i < dataList.length; i++ ) {
        
    	NgpciData.push(parseFloat(dataList[i].DEPT_GPCI_1));
    	SgpciData.push(parseFloat(dataList[i].DEPT_GPCI_2));
    	AllGpciData.push(parseFloat(dataList[i].DEPT_GPCI_3));
    	PredctYearData.push(dataList[i].PREDCT_YEAR);
    }
    
    require([   'echarts','echarts/chart/bar', "echarts/chart/line"   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('sLDeptListGPCIChart'));
                 myChart.setOption({
                        title   : { text: '경기도 전체(55개 노선)'   
                        			, x : 'center'},
                        tooltip : { trigger: 'axis'             },
                        legend: {
			            	   x: 'right',
			                   y: 'center',
			                   orient : 'vertical',
			                   data: ['북부도로과', '도로건설과', '경기도 전체'] 
			               },
                        toolbox : { show: true,
                            feature: {
                                dataView : {id: 'deptListGpciDiagram',show: true, readOnly: false},   // 상세조회
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
    				                       name: '북부도로과',
    				                       type: 'line',
    				                       symbol:'emptyCircle',
    				                       data: NgpciData
    				                   },
    				                   {
    				                       name: '도로건설과',
    				                       type: 'line',
    				                       symbol:'emptyCircle',
    				                       data: SgpciData
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
 * 관리기관별 전체 GPCI 표 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawDeptListGpciTable(dataList) {
	var mainData    = dataList;
    var tHtml      ='';    
    
    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].PREDCT_YEAR + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DEPT_GPCI_1) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DEPT_GPCI_2) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DEPT_GPCI_3) + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#deptListGpciDiagram .tblist tbody').empty().append(tHtml);
}


/**
 * 관리기관별 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawDeptGPCIChart(dataList) {
	 	var color  = ["#6799FF"];
        
	    var DeptNm    = dataList[0].DEPT_NM; //국지도 GPCI 데이터
	    var gpciData    = []; //전체 GPCI 데이터
	    var PredctYearData    = []; //예측년도 데이터
	    
	    for ( var i = 0; i < dataList.length; i++ ) {
	        
	    	gpciData.push(parseFloat(dataList[i].GPCI));
	    	PredctYearData.push(dataList[i].PREDCT_YEAR);
	    }
	    
	    require([   'echarts','echarts/chart/bar', "echarts/chart/line"   ],
	            function (ec) {
	                 var myChart = ec.init(document.getElementById('sLDeptGPCIChart'));
	                 myChart.setOption({
	                        title   : { text: DeptNm   
	                        			, x : 'center'},
	                        tooltip : { trigger: 'axis'             },
	                        legend: {
				            	   x: 'right',
				                   y: 'center',
				                   orient : 'vertical',
				                   data: [DeptNm] 
				               },
				            color : color,
	                        toolbox : { show: true,
	                            feature: {
	                                dataView : {id: 'deptGpciDiagram',show: true, readOnly: false},   // 상세조회
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
	    				                       name: DeptNm,
	    				                       type: 'line',
	    				                       symbol:'emptyCircle',
	    				                       data: gpciData
	    				                   }
	    				               ]
	                    });
	                 
	            });
	}

/**
 * 관리기관별 GPCI 표 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawDeptGpciTable(dataList) {
	var mainData    = dataList;
    var tHtml      ='';    
    
    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].PREDCT_YEAR + '</td>';
        tHtml   += '<td>' + mainData[i].DEPT_NM + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].GPCI) + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#deptGpciDiagram .tblist tbody').empty().append(tHtml);
}

/**
 * 노선별 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawRouteGPCIChart(dataList) {
        
	    var gradNm    = dataList[0].ROAD_GRAD; //국지도 GPCI 데이터
	    var routeCode  = parseInt(dataList[0].ROUTE_CODE);	//지방도 GPCI 데이터
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
    	if(se == "sLDeptListGPCIChart"){
        	COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/smdtalaststtus/selectSmDtaLastStatsDeptListExcel.do'/>", "");
    	}else if(se == "sLDeptGPCIChart"){
    		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/smdtalaststtus/selectSmDtaLastStatsDeptExcel.do'/>", "");
    	}else if(se == "sLRoutGPCIChart"){
    		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/smdtalaststtus/selectSmDtaLastStatsRouteExcel.do'/>", "");
    	}
    }
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