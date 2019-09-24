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
	
	$("#divSurvey").height($(parent.window).height() - 250);
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
		var rw1 = $(window).width()-150;
    	var rw = $(window).width()/3;
    	
    	$("#divSurvey").height($(parent.window).height() - 250);
    	
    	fnRoutSearch(rw1,rw);
});

//조건에 맞는 검색조회
function fnRoutSearch(rw1,rw){
	
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
				//drawRoutLenChart(dataList,rw);
				drawTable(dataList,'rout');
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
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
				//drawDeptLenChart(dataList,rw);
				drawTable(dataList,'dept');
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
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
				//drawRoadLenChart(dataList,rw);
				drawTable(dataList,'road');
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

function drawTable(dataList,se){
	var mainData	= dataList;
	var tHtml 		= '';
	
	if(se == 'rout'){
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	fn_castRouteCode(mainData[i].ROUTE_CODE)+ '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_NAME					+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	Number(mainData[i].LEN)					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram tbody').empty().append(tHtml);
	}else if(se == 'dept'){
		
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].DEPT_NM						+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	Number(mainData[i].LEN)					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram1 tbody').empty().append(tHtml);
	}else if(se == 'road'){
		for(var i=0; i<mainData.length; i++){
			tHtml	+= '<tr>';
			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].ROAD_NAME					+ '</td>';
			tHtml	+= '<td style="text-align:right" class="bg2">'	+	Number(mainData[i].LEN)					        + '</td>';
			tHtml	+= '</tr>';
		}
		
		$('#diagram2 tbody').empty().append(tHtml);
	}
}

//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectSurveyAllLenStatsExcel.do'/>", "");
		
		/* if(se == 'rout'){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectSrvyRoutLenStatsExcel.do'/>", "");
		}else if(se == 'dept'){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectSrvyDeptLenStatsExcel.do'/>", "");
		}else if(se == 'road'){
			COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cell10/selectSrvyRoadLenStatsExcel.do'/>", "");
		} */
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
          	<a href="#" class="schbtn" onclick="location.replace('<c:url value="selectSurveyAllLenStats.do"/>');">그래프보기</a>
          	<a href="#" class="schbtn" onclick="fnExcel();"/>엑셀저장</a>
        </div>	
        <!-- 표 -->
		<div id="divSurvey" style="height:600px;overflow-y: scroll;">
			 <div class="cont_ListBx">
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
				</br>
				<table class="tblist" border="1" id="diagram1">
					<colgroup>
						<col width="80%"/>
						<col width="20%"/>
					</colgroup>
					<thead style="text-align: center;">
						<tr>
							<th scope="col">관리기관명</th>
							<th scope="col">연장(km)</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				</br>
				<table class="tblist" border="1" id="diagram2">
					<colgroup>
						<col width="80%"/>
						<col width="20%"/>
					</colgroup>
					<thead style="text-align: center;">
						<tr>
							<th scope="col">도로등급명</th>
							<th scope="col">연장(km)</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>