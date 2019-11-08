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
<input type="hidden" id="SCH_DEPT_CODE" name="SCH_DEPT_CODE" value=""/>
<input type="hidden" id="SCH_STRWRK_DE" name="SCH_STRWRK_DE" value=""/>
<input type="hidden" id="SCH_COMPET_DE" name="SCH_COMPET_DE" value=""/>
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
    <!--  그래프 -->
    <div id="sch_cnt01" class="tabcont">
        <h3>포장상태 평가 시군구별 통계</h3>
        <p class="location">
            <span>포장공사 이력관리</span>
            <span>포장공사 통계조회</span>
            <strong>시군구별 통계</strong>
        </p>
        
        <div id="divStatChart" style="overflow-y:auto;">
            <ul class="statsbx">
                <li style="float:none; width:97%">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="mummAdmGpciChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">시군구별 포장상태 평가 통계</h4>
                </li>
                <li style="float:none; margin-top: 40px; width:97%">
                    <div class="graylinebx p10" style="width:100%">
                        <div id="mummAdmDfctChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">시군구별 포장상태 파손원인 통계</h4>
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
            </colgroup>
            <thead style="text-align: center;">
                <tr>
                    <th scope="col">시군구</th>
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
    
    $("#divStatChart").height($(parent.window).height() - 170);
    
    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");
    
    //창 조절시 차트 width 
    var rw = $(window).width()/3;
    
    fnMummAdmSearch('','','',rw);//관리기관 조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
        $("#divStatChart").height($(parent.window).height() - 170);
    
        var rw = $(window).width()/3;
        var deptCd = $("#SCH_DEPT_CODE").val();
        var strDt = $("#SCH_STRWRK_DE").val();
        var endDt = $("#SCH_COMPET_DE").val();
        
        fnAdmSearch(deptCd,strDt,endDt,rw);
});

//조건에 맞는 검색조회
function fnAdmSearch(deptCd,strDt,endDt,rw){
    //검색 조건 값 set
    $("#SCH_DEPT_CODE").val(deptCd);
    $("#SCH_STRWRK_DE").val(strDt);
    $("#SCH_COMPET_DE").val(endDt);
    
    fnMummAdmCntSearch(deptCd,strDt,endDt,rw);//관리기관 조회
}

require.config({
       paths: {
            echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
        }
    });

//검색 처리
function fnMummAdmSearch(deptCd,strDt,endDt,rw) {
    var data = {"SCH_DEPT_CODE" : deptCd, "SCH_STRWRK_DE" : strDt, "SCH_COMPET_DE" : endDt};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/mumm/mummAdmCntStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        //,data: JSON.stringify( $("#frm").cmSerializeObject())
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (data) {
            var dataList = data.rows;
            if(dataList.length !=0){
                drawAdmGpciChart(dataList,rw);
                drawAdmDfctChart(dataList,rw);
                
                drawTable(dataList);
            }else{
                ntcNo += 1;
            }
        },
        error: function () {
            errNo += 1;
        }
    });
}

function drawAdmGpciChart(dataList,rw){
    var gAdmNm    = dataList.map(function(elem){ return elem.adm_nm; });       
    var gpciData    = dataList.map(function(elem){ return elem.gpci; });
    var degree      = (dataList.length < 10) ? 0 : -90;
    
    require([   'echarts','echarts/chart/bar'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('mummAdmGpciChart'));
                 myChart.setOption({
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
                                    data : gAdmNm
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

function drawAdmDfctChart(dataList,rw){
    var gAdmNm    = dataList.map(function(elem){ return elem.adm_nm; });      
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
                 var myChart = ec.init(document.getElementById('mummAdmDfctChart'));
                 myChart.setOption({
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
                                    data : gAdmNm
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
        tHtml   += '<td align="center" class="bg">'             +   mainData[i].adm_nm+ '</td>';
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
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/mumm/mummAdmCntStatsExcel.do'/>", "");
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