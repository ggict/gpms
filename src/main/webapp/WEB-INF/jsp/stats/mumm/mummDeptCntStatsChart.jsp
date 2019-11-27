<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사 통계</title>
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
<div style="margin: 0px 20px 0 20px;">
	<div id="sch_cnt01" class="tabcont">
		<h3>포장상태 평가 관리기관별 통계</h3>
        <p class="location">
            <span>포장공사 이력관리</span>
            <span>포장공사 통계조회</span>
            <strong>관리기관별 통계</strong>
        </p>
	    <div style="margin-top: 10px; margin-bottom: 45px;">
	        <a href="#" onclick="fnShowTable();" class="schbtn" style="float: right; margin-right: 8px;">상세조회</a>
	    </div>
		<!-- 그래프 -->
		<div id="chartArea" style="overflow-y: auto; overflow-x: hidden; height:100%;">
            <ul class="statsbx">
                <li style="float: none;">
                    <div class="graylinebx p10" style="width:195%;">
                        <div id="mDeptGPCIChart" class="cont_ConBx2" style="height: 310px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:195%;">관리기관별 포장상태 평가 통계</h4>
                </li>
                <li style="float: none; margin-top: 20px;">
                    <div class="graylinebx p10" style="width:195%;">
                        <div id="mDeptDMGChart" class="cont_ConBx2" style="height: 305px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none;width:195%;">관리기관별 포장상태 파손원인 통계</h4>
                </li>
            </ul>
        </div>
        <!-- 그래프 END -->
	</div>
    <!-- 표 START -->
    <div class="cont_ListBx" style="display: none;">
        <table class="tblist" border="1" id="diagram">
            <colgroup>
                <col width="15%"/>
                <col width="25%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
            </colgroup>
            <thead style="text-align: center;">
                <tr>
                    <th scope="col" rowspan="2">관리부서코드</th>
                    <th scope="col" rowspan="2">관리부서명</th>
                    <th scope="col" rowspan="2">GPCI</th>
                    <th scope="col" colspan="3">파손원인</th>
                </tr>
                <tr>
                    <th scope="col">교통량/하부불량</th>
                    <th scope="col">기후</th>
                    <th scope="col">기타</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
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
    
    parent.schFlag = 1;     //{0:표, 1:차트}
  //  var height = $(window).height() - 370;
   // $("#chartArea").height(height + "px");
    
    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");
    
    var rw = $(window).width() - 210;
    var deptCode = parent.$("#MUMM_DEPT_CODE").val();
    
    // 초기 그래프 search
    fnDeptSearch(deptCode, rw);
 
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
    
	var rw = $(window).width() - 210;
	var height = $(window).height() - 370;
 //   $("#chartArea").height(height + "px");
	var deptCode = parent.$("#MUMM_DEPT_CODE").val();
	
	fnDeptSearch(deptCode, rw);
	
});

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

// 검색 처리
function fnDeptSearch(deptCode, rw) {
    
    var data = {"DEPT_CODE" : deptCode};
    
     $.ajax({
        url: '<c:url value="/"/>'+'api/mumm/mummDeptCntStats.do',
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (jdata) {
            
            if(jdata.length !=0){
                
                drawDeptGPCIChart(jdata, rw);
                drawDeptDMGChart(jdata, rw);
                drawTable(jdata);
                
            }else{
                
                ntcNo += 1;
                
            }            
        },
        error: function () {
            errNo += 1;
        }
    });
}

// GPCI 차트 그리기
function drawDeptGPCIChart(dataList, rw){
    
    var colors = ['#4f81bd'];
    
    var gDeptNm     = [];       
    var gpciData    = [];
    var degree      = -90;
    
    if ( dataList.length < 10 ) {
        
        degree = 0;
        
    }
    
    for ( var i = 0; i < dataList.length; i++) {
        
        gDeptNm.push(dataList[i].DEPT_NM);
        gpciData.push(dataList[i].GPCI);
        
    }
    
    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('mDeptGPCIChart'));
                 myChart.setOption({
                        title   : { text: 'GPCI' },
                        color   : colors,
                        tooltip : { trigger: 'axis'             },
                        toolbox : { show: true,
                            feature: {
                                dataView : {show: false, readOnly: false},   // 상세조회
                                saveAsExcel : {show: false},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }   
                        },
                        grid :{
                            width : rw + 'px',
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
                                    data : gDeptNm
                                }],
                        yAxis : [{  name : '값',     type : 'value'      }],
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

// DMG 차트 그리기
function drawDeptDMGChart(dataList, rw) {
    
    var colors = ['#c1232b', '#b5c334', '#fcce10'];
    
    var gDeptNm      = [];       
    var dmgClmtData  = [];
    var dmgVmtcData  = [];
    var dmgEtcData   = [];
    var degree       = -90;
    
    if ( dataList.length < 10 ) {
        
        degree = 0;
        
    }
    
    for ( var i = 0; i < dataList.length; i++ ) {
        
        gDeptNm.push(dataList[i].DEPT_NM);
        dmgClmtData.push(parseFloat(dataList[i].DMG_CUZ_CLMT));
        dmgVmtcData.push(parseFloat(dataList[i].DMG_CUZ_VMTC));
        dmgEtcData.push(parseFloat(dataList[i].DMG_CUZ_ETC));
        
    }
    
    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('mDeptDMGChart'));
                 myChart.setOption({
                        title   : { text: '파손원인' },
                        color   : colors,
                        tooltip : { trigger: 'axis'             },
                        toolbox : { show: true,
                            feature: {
                                dataView : {show: false, readOnly: false},   // 상세조회
                                saveAsExcel : {show: false},                 // 엑셀저장
                                saveAsImage: {show: true}                   // 이미지저장
                            }   
                        },
                        legend: {
                            data : [ '교통량/하부불량', '기후', '기타' ]  
                          },
                        grid :{
                            width : rw + 'px',
                            x : 50,
                            y2 : 80
                        },
                        xAxis : [{  
                                    type : 'category',
                                    axisTick : { alignWithLabel: true },
                                    axisLabel : {
                                        show:true,
                                        interval: 0,
                                        rotate: degree
                                    },
                                    data : gDeptNm
                                }],
                        yAxis : [{  name : '값',     type : 'value'      }],
                        series : [
                            {
                                name: '교통량/하부불량',
                                type: 'bar',
                                data: dmgVmtcData
                            },
                            {
                                name: '기후',
                                type: 'bar',
                                data: dmgClmtData
                            },
                            {
                                name: '기타',
                                type: 'bar',
                                data: dmgEtcData
                            }
                        ]
                    });

            });
}

function drawTable(dataList) {

    var mainData    = dataList;
    var tHtml       = '';
    
    for ( var i = 0; i < mainData.length; i++ ) {
        tHtml   += '<tr class="center">';
        tHtml   += '<td>' + mainData[i].DEPT_CODE + '</td>';
        tHtml   += '<td>' + mainData[i].DEPT_NM + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].GPCI) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_VMTC) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_CLMT) + '</td>';
        tHtml   += '<td>' + parseFloat(mainData[i].DMG_CUZ_ETC) + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#diagram tbody').empty().append(tHtml);
}

// 테이블 보기
function fnShowTable() {
    
    var deptCode = parent.$("#MUMM_DEPT_CODE").val();
    COMMON_UTIL.cmMoveUrl('<c:url value="mumm/mummDeptCntStats.do?DEPT_CODE=' + deptCode + '"/>');
    
}

//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/mumm/mummDeptCntStatsExcel.do'/>", "");
    }
}

//에러 메시지
function fn_msgErr(){
    if(errNo == 1){
        alert("오류가 발생하였습니다. 재검색 하시기 바랍니다.");
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