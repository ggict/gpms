<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사평가 통계</title>
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

	<header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">통계</option>
                </select>
                <select name="">
                    <option value="">포장상태 평가</option>
                </select>
                <select name="">
                    <option value="">노선별 통계</option>
                </select>
            </span>
        </div>
    </header>
	    
	<!-- container2 S -->
	<div class="container2">
	
	    <div class="table searchBox top">
	        <table>
	            <tbody>
	                <tr>
	                    <td class="th">
	                        <label for="SRVY_YEAR">기준년도</label>
	                    </td>
	                    <td>
	                        <select id="SRVY_YEAR">
                                <c:forEach items="${slctnYearList}" var="slctnYear">
                                    <option value="${slctnYear}">${slctnYear}</option>
                                </c:forEach>
	                        </select>
	                    </td>
	                    <td class="th">
	                        <label for="SCH_ROAD_GRAD">도로등급</label>
	                    </td>
	                    <td>
                            <select id="SCH_ROAD_GRAD" name="SCH_ROAD_GRAD" alt="도로등급" onchange="fn_change_roadNo();">
		                        <option value="">== 전체 ==</option>
		                        <c:forEach items="${roadGradList }" var="roadGrad">
		                            <option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
		                        </c:forEach>
                            </select>
	                    </td>
	                    <td class="th">
	                        <label for="ROAD_NO">노선번호</label>
	                    </td>
	                    <td>
	                        <select id="ROAD_NO" name="ROAD_NO" onchange="fn_change_roadNm();" alt="노선번호"  onchange="fn_change_roadNm();">
	                            <option value="">== 전체 ==</option>
	                            <c:forEach items="${roadNoList }" var="roadNo">
	                                <option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
	                            </c:forEach>
	                        </select>
	                    </td>
	                    <td class="th">
	                       <label for="ROAD_NAME">노선명</label>
	                    </td>
	                    <td><input type="text" id="ROAD_NAME" name="ROAD_NAME" readonly disabled value="" /></td>
	                    <td class="btnCell"><button type="button" id="btnSearch" class="btn pri">검색</button></td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	    
	    <!-- <div class="tab">
			<a href="#statsChart" onclick="fnShowTable();" class="on">상세보기</a>
			<a href="#statsTable" onclick="fnToggle('Chart')">그래프보기</a>			
		</div> -->
	    
	</div>
	    
	    
   
	<div id="sch_cnt01" class="tabcont">
        <div id="divStatChart" style="overflow-y:auto;">
            <ul class="statsbx">
                <li style="float:none; width:97%">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="mummRoutGpciChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">노선별 포장상태 평가 통계</h4>
                </li>
                <li style="float:none; margin-top: 40px; width:97%">
                    <div class="graylinebx p10" style="width:100%">
                        <div id="mummRoutDfctChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">노선별 포장상태 파손원인 통계</h4>
                </li>
            </ul>
        </div>
    </div>
    <!-- 표 -->
     <div class="cont_ListBx" style="display: none;">
        <table class="tblist" border="1" id="diagram">
            <colgroup>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
            </colgroup>
            <thead style="text-align: center;">
                <tr>
                    <th scope="col">노선번호</th>
                    <th scope="col">노선명</th>
                    <th scope="col">GPCI</th>
                    <th scope="col">거북등균열</th>
                    <th scope="col">선형균열</th>
                    <th scope="col">패칭</th>
                    <th scope="col">포트홀</th>
                    <th scope="col">소성변형</th>
                    <th scope="col">종단평탄성</th>
                    <th scope="col">블럭균열</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript" defer="defer">
var errNo=0;    //에러 메시지 변수
var ntcNo=0;    //경고 메시지 변수
var myChart1;   // gpci 차트 obj
var myChart2;   // 파손형태 차트 obj

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	$("#divStatChart").height($(parent.window).height() - 170);
    fnMummRoutSearch(); //노선조회
    
    $('#btnSearch').click(function() {
    	fnMummRoutSearch();
    })
}); 

//창 조절시 차트 resize
$(window).resize(function(){
    if(this.resizeTO) {
        clearTimeout(this.resizeTO);
    }
    this.resizeTO = setTimeout(function() {
        $(this).trigger('resizeEnd');
    }, 500);
})
$(window).on("resizeEnd", function(){
	$("#divStatChart").height($(parent.window).height() - 170);
	myChart1.resize();
	myChart2.resize();
})

function fnMummRoutSearch() {
	var SRVY_YEAR = $('#SRVY_YEAR option:selected').val();
	var ROAD_GRAD = $('#SCH_ROAD_GRAD option:selected').val();
	var ROUTE_CODE = $('#ROAD_NO option:selected').val();
	var data = { "SRVY_YEAR": SRVY_YEAR, "ROAD_GRAD" : ROAD_GRAD, "ROUTE_CODE" : ROUTE_CODE };
	
    $.ajax({
         url: '<c:url value="/"/>'+'api/mumm/mummRoutCntStatsGPCI.do'
        ,type: 'post'
        ,contentType: 'application/json'
        //,data: JSON.stringify( $("#frm").cmSerializeObject())
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (data) {
            var dataList = data.rows;
            if(dataList.length !=0){
            	drawRoutGpciChart(dataList);    // GPCI
            	drawRoutDfctChart(dataList);    // 파손형태
                drawTable(dataList);    // echarts 테이블
            }else{
                ntcNo += 1;
                COMMON_UTIL.fn_msgNtc(ntcNo);
            }
        },
        error: function () {
            errNo += 1;
            COMMON_UTIL.fn_msgErr(errNo);
        }
    });
}

//차트
require.config({
       paths: {
            echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
        }
    });
function drawRoutGpciChart(dataList){
    var gRouteNm    = dataList.map(function(elem){ return Number(elem.route_code)+"호선"; });       
    var gpciData    = dataList.map(function(elem){ return elem.gpci; });
    var degree      = (dataList.length < 10) ? 0 : -90;
    
    require([   'echarts','echarts/chart/bar'   ], function (ec) {
    	myChart1 = ec.init(document.getElementById('mummRoutGpciChart'));
        myChart1.setOption({
            title   : { text: 'GPCI'   },
            tooltip : { trigger: 'axis'             },
            toolbox : { show: true,
                feature: {
                    dataView : {show: true, readOnly: false},   // 상세조회
                    saveAsExcel : {show: true},                 // 엑셀저장
                    saveAsImage: {show: true}                   // 이미지저장
                }   
            },
            grid :{
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
                        data : gRouteNm
                    }],
            yAxis : [{  name : '',     type : 'value'      }],
            series : [
                {
                    name: '',
                    type: 'bar',
                    data: gpciData
                }
            ]
        });
    });
}

function drawRoutDfctChart(dataList){
	var gRouteNm    = dataList.map(function(elem){ return Number(elem.route_code)+"호선"; });      
    var ac_idx_data    = dataList.map(function(elem){ return elem.ac_idx; });
    var lc_tc_idx_data    = dataList.map(function(elem){ return elem.lc_tc_idx; });
    var ptchg_idx_data    = dataList.map(function(elem){ return elem.ptchg_idx; });
    var pothole_idx_data    = dataList.map(function(elem){ return elem.pothole_idx; });
    var rd_idx_data    = dataList.map(function(elem){ return elem.rd_idx; });
    var iri_val_data    = dataList.map(function(elem){ return elem.iri_val; });
    var bc_idx_data    = dataList.map(function(elem){ return elem.rd_idx; });
    var degree      = (dataList.length < 10) ? 0 : -90;
    
    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 myChart2 = ec.init(document.getElementById('mummRoutDfctChart'));
                 myChart2.setOption({
                        title   : { text: '파손형태' },
                        tooltip : { trigger: 'axis'             },
                        toolbox : { show: true,
                            feature: {
                                dataView : {show: true, readOnly: false},   // 상세조회
                                saveAsExcel : {show: true},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }   
                        },
                        legend: {
                        	data: ['거북등균열', '선형균열', '패칭', '포트홀', '소성변형', '종단평탄성', '블럭균열'/*, '복합파손'*/]
                        },
                        grid :{
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
                                    data : gRouteNm
                                }],
                        yAxis : [{  name : '',       type : 'value'      }],
                        series : [
                            {
                                name: '거북등균열',
                                type: 'bar',
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: ac_idx_data
                            },
                            {
                                name: '선형균열',
                                type: 'bar',
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: lc_tc_idx_data
                            },
                            {
                                name: '패칭',
                                type: 'bar',
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: ptchg_idx_data
                            },
                            {
                                name: '포트홀',
                                type: 'bar',
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: pothole_idx_data
                            },
                            {
                                name: '종단평탄성',
                                type: 'bar',    
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: rd_idx_data
                            },
                            {
                                name: '소성변형',
                                type: 'bar',    
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: iri_val_data
                            },
                            {
                                name: '블럭균열',
                                type: 'bar',    
                                stack: '합계',
                                itemStyle: { normal: {label : {show: true, position: 'insideRight'}}},
                                data: bc_idx_data
                            }
                        ]
                    });

            });
}

function drawTable(dataList){

    var mainData    = dataList;
    var tHtml       = '';
    
    for(var i=0; i<mainData.length; i++){
        tHtml   += '<tr>';
        tHtml   += '<td align="center" class="bg">'             +   Number(mainData[i].route_code)+"호선"+ '</td>';
        tHtml   += '<td align="center" class="bg">'             +   mainData[i].road_nm                    + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].gpci        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].ac_idx        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].lc_tc_idx    + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].ptchg_idx                        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].pothole_idx                        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].rd_idx                        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].iri_val                        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].rd_idx                        + '</td>';
        tHtml   += '</tr>';
    }
    $('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/mumm/mummRoutCntStatsExcel.do'/>", "");
    }
}

//도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo(val) {
    var roadGrad = $("#SCH_ROAD_GRAD").val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>== 전체 ==</option>";

            for(var i=0; i < data.length; i++){
                txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
            }
            
            $("#ROAD_NO").html(txtHtml);
            $("#ROAD_NAME").val("");

            if(val != undefined){
                $("#ROAD_NO").val(val);
                fn_change_roadNm();
            }
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
    var roadGrad = $("#SCH_ROAD_GRAD").val();

    if(roadNo == "") {
        $("#ROAD_NAME").val("");
        $("#SCH_ROAD_GRAD").val("");
        return;
    }

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfo.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_NO : roadNo})
        ,success: function(data){
            $("#ROAD_NAME").val(data.ROAD_NAME);
            
            $("#SCH_ROAD_GRAD").val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){

        }
    });
}

</script>

</body>
</html>