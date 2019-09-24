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
	
    parent.$("#ROAD_GRAD").val("");
    parent.$("#ROAD_NO").val("");
    parent.$("#ROAD_NAME").val("");
    parent.$("#SCH_STRSRVY_DE").val("");
    parent.$("#SCH_ENDSRVY_DE").val("");
	
	$("#divStatChart").height($(parent.window).height() - 200);
	
	//창 조절시 차트 width 
	var rw1 = $(window).width()-200;
	
	fnRoutLenSearch('','','','',rw1);//노선별조사거리조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
	 	$("#divStatChart").height($(parent.window).height() - 200);
	 
		var rw1 = $(window).width()-150;
    	var roadGr = $("#SCH_ROAD_GRAD").val();
    	var roadNo = $("#SCH_ROUTE_CODE").val();
    	var strDt = $("#SCH_STRSRVY_DE").val();
    	var endDt = $("#SCH_ENDSRVY_DE").val();
    	
    	fnRoutLenSearch(roadGr,roadNo,strDt,endDt,rw1);//노선별조사거리조회
});

//조건에 맞는 검색조회
function fnSrvyRoutSearch(roadGr,roadNo,strDt,endDt,rw){
	
	/* if(roadNo == '' || roadNo == null){
		alert("노선번호를 선택하세요.");
		return;
	} */
	
	//검색 조건 값 set
	$("#SCH_ROAD_GRAD").val(roadGr);
	$("#SCH_ROUTE_CODE").val(roadNo);
	$("#SCH_STRSRVY_DE").val(strDt);
	$("#SCH_ENDSRVY_DE").val(endDt);
	
	fnRoutLenSearch(roadGr,roadNo,strDt,endDt,rw);//노선별조사거리조회
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnRoutLenSearch(roadGr,roadNo,strDt,endDt,rw) {
	
	var data = {"SCH_ROAD_GRAD" : roadGr, "SCH_ROUTE_CODE" : roadNo ,"SCH_STRSRVY_DE" : strDt, "SCH_ENDSRVY_DE" : endDt };
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/cell10/selectSrvyRoutLenStatsResult.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify(data)
		,dataType: 'json'
		,success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawRoutLenChart(dataList,rw);
				drawTable(dataList);
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

function drawRoutLenChart(dataList,rw){
	var gRouteNm 	= [];		
	var lenData		= [];
	var degree		= -45;
	if(dataList.length < 10){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		if(dataList[i].ROAD_NAME != '' && dataList[i].ROAD_NAME != null){
			gRouteNm.push(dataList[i].ROAD_NAME + "(" + dataList[i].ROUTE_CODE + ")");
			lenData.push(dataList[i].LEN);
		}
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('routLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '연장(km)'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
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

function drawTable(dataList){

	var mainData	= dataList;
	var tHtml 		= '';
	
	for(var i=0; i<mainData.length; i++){
		tHtml	+= '<tr>';
		tHtml	+= '<td align="center" class="bg">'				+	fn_castRouteCode(mainData[i].ROUTE_CODE)+ '</td>';
		tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_NAME					+ '</td>';
		tHtml	+= '<td style="text-align:right" class="bg2">'	+	Number(mainData[i].LEN)					        + '</td>';
		tHtml	+= '</tr>';
	}
	
	$('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectSrvyRoutLenStatsExcel.do'/>", "");
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
<input type="hidden" id="SCH_ROAD_GRAD" name="SCH_ROAD_GRAD" value=""/>
<input type="hidden" id="SCH_ROUTE_CODE" name="SCH_ROUTE_CODE" value=""/>
<input type="hidden" id="SCH_STRSRVY_DE" name="SCH_STRSRVY_DE" value=""/>
<input type="hidden" id="SCH_ENDSRVY_DE" name="SCH_ENDSRVY_DE" value=""/>
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>노선별 조사구간 연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 조사구간</span>
			<strong>노선별 통계</strong>
		</p>
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li>
					<div class="graylinebx p10" style="width:195%;">
						<div id="routLenChart" class="cont_ConBx2" style="height: 500px;"></div>
					</div>
					<h4 style="text-align: center;background:none;width:195%;">노선별 조사거리 통계</h4>
				</li>
			</ul>
		</div>
	</div>
	<!-- 표 -->
	 <div class="cont_ListBx" style="display: none;">
		<table class="tblist" border="1" id="diagram">
			<colgroup>
				<col width="20%"/>
				<col width="60%"/>
				<col width="20%"/>
			</colgroup>
			<thead style="text-align: center;">
				<tr>
					<th scope="col">노선번호</th>
					<th scope="col">노선명</th>
					<th scope="col">연장(km)</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>