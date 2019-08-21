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
	
    parent.$("#SCH_DEPT_CODE1").val("");
    parent.$("#SCH_STRSRVY_DE").val("");
    parent.$("#SCH_ENDSRVY_DE").val("");
	
	$("#divStatChart").height($(parent.window).height() - 170);

	//창 조절시 차트 width 
	var rw = $(window).width()-400;
	
	fnDeptLenSearch('','','',rw);//관리기관도로연장조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		$("#divStatChart").height($(parent.window).height() - 170);
		
    	var rw = $(window).width()/2;
    	var deptCd = $("#SCH_DEPT_CODE1").val();
    	var strDt = $("#SCH_STRSRVY_DE").val();
    	var endDt = $("#SCH_ENDSRVY_DE").val();
    	
    	fnDeptLenSearch(deptCd,strDt,endDt,rw);//관리기관도로연장조회
});

//조건에 맞는 검색조회
function fnSrvyDeptSearch(deptCd,strDt,endDt,rw){
	//검색 조건 값 set
	$("#SCH_DEPT_CODE1").val(deptCd);
	$("#SCH_STRSRVY_DE").val(strDt);
	$("#SCH_ENDSRVY_DE").val(endDt);
	
	fnDeptLenSearch(deptCd,strDt,endDt,rw);//관리기관도로연장조회
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnDeptLenSearch(deptCd,strDt,endDt,rw) {
	
	var data = {"SCH_DEPT_CODE1" : deptCd, "SCH_STRSRVY_DE" : strDt, "SCH_ENDSRVY_DE" : endDt };
	
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cell10/selectSrvyDeptLenStatsResult.do',
        //data: JSON.stringify( $("#frm").cmSerializeObject()),
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawDeptLenChart(dataList,rw);
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

function drawDeptLenChart(dataList,rw){
	var lenData	= [];
	for(var i=0; i<dataList.length; i++){
		lenData.push({"value" : dataList[i].LEN, "name" : dataList[i].DEPT_NM});
	}
	require([	'echarts','echarts/chart/pie'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('deptLenChart'));
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
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
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

function drawTable(dataList){

	var mainData	= dataList;
	var tHtml 		= '';
	
	for(var i=0; i<mainData.length; i++){
		tHtml	+= '<tr>';
		tHtml	+= '<td align="center" class="bg">'				+	fn_castRouteCode(mainData[i].DEPT_CODE)+ '</td>';
		tHtml	+= '<td align="center" class="bg">'				+	mainData[i].DEPT_NM					+ '</td>';
		tHtml	+= '<td style="text-align:right" class="bg2">'	+	mainData[i].LEN					        + '</td>';
		tHtml	+= '</tr>';
	}
	
	$('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectSrvyDeptLenStatsExcel.do'/>", "");
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
<input type="hidden" id="SCH_DEPT_CODE1" name="SCH_DEPT_CODE1" value=""/>
<input type="hidden" id="SCH_STRSRVY_DE" name="SCH_STRSRVY_DE" value=""/>
<input type="hidden" id="SCH_ENDSRVY_DE" name="SCH_ENDSRVY_DE" value=""/>
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>관리기관별 조사구간 연장 통계</h3>
		<p class="location">
			<span>통계</span>
			<span>포장상태 조사구간</span>
			<strong>관리기관별 통계</strong>
		</p>
		<div id="divStatChart" style="overflow-y:auto;">
			<ul class="statsbx">
				<li>
					<div class="graylinebx p10" style="width:195%;">
						<div id="deptLenChart" class="cont_ConBx2" style="height: 500px;"></div>
					</div>
					<h4 style="text-align: center;background:none;width:195%;">관리기관별 조사거리 통계</h4>
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
					<th scope="col">관리기관번호</th>
					<th scope="col">관리기관명</th>
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