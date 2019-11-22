<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>보수대상선정 통계</title>
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
        <h3>보수대상 선정 관리기관별 통계</h3>
        <p class="location">
            <span>통계</span>
            <span>보수대상 선정</span>
            <strong>관리기관별 통계</strong>
        </p>
        
        <div id="divStatChart" style="overflow-y:auto;">
            <ul class="statsbx">
                <li style="float:none; width:97%">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="rpairDeptChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">관리기관별 보수대상 선정 통계</h4>
                </li>
            </ul>
        </div>
    </div>
    <!-- 표 -->
     <div class="cont_ListBx" style="display: none;">
        <table class="tblist" border="1" id="diagram">
            <colgroup>
                <col width="25%"/>
                <col width="25%"/>
                <col width="25%"/>
            </colgroup>
            <thead style="text-align: center;">
                <tr>
                    <th scope="col">관리기관</th>
                    <th scope="col">연장</th>
                    <th scope="col">구간갯수</th>
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
    
    fnDeptSearch('','','',rw);//노선조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
        $("#divStatChart").height($(parent.window).height() - 170);
    
        var rw = $(window).width()/3;
        var deptCd = $("#SCH_DEPT_CODE").val();
        var strDt = $("#SCH_STRWRK_DE").val();
        var endDt = $("#SCH_COMPET_DE").val();
        
        fnDeptSearch(deptCd,strDt,endDt,rw);
});

//조건에 맞는 검색조회
function fnDeptSearch(deptCd,strDt,endDt,rw){
    //검색 조건 값 set
    $("#SCH_DEPT_CODE").val(deptCd);
    $("#SCH_STRWRK_DE").val(strDt);
    $("#SCH_COMPET_DE").val(endDt);
    
    fnRpairDeptSearch(deptCd,strDt,endDt,rw);//노선조회
}

require.config({
       paths: {
            echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
        }
    });

//검색 처리
function fnRpairDeptSearch(deptCd,strDt,endDt,rw) {
    var data = {"SCH_DEPT_CODE" : deptCd, "SCH_STRWRK_DE" : strDt, "SCH_COMPET_DE" : endDt};
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/rpairtrgetgroup/selectRpairDeptLenStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        //,data: JSON.stringify( $("#frm").cmSerializeObject())
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (data) {
            var dataList = data.rows;
            if(dataList.length !=0){
                drawDeptChart(dataList,rw);
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

function drawDeptChart(dataList,rw){
    var gDeptNm    = dataList.map(function(elem){ return elem.dept_nm; });       
    var groupLenData    = dataList.map(function(elem){ return elem.group_len; });
    var maxOfGroupLenData = groupLenData.reduce(function(prev, curr) { return (prev > curr) ? prev : curr; });
    var groupCntData    = dataList.map(function(elem){ return elem.group_cnt; });
    var maxOfGroupCntData = groupCntData.reduce(function(prev, curr) { return (prev > curr) ? prev : curr; });
    var degree      = (dataList.length < 10) ? 0 : -90;
    
    require([   'echarts','echarts/chart/bar', 'echarts/chart/line'   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('rpairDeptChart'));
                 myChart.setOption({
                        title   : { text: '관리기관별 통계'   },
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
                                    data : gDeptNm
                                }],
                        yAxis : [
                            { name: '연장', type: 'value', min: 0, max: maxOfGroupLenData+10000      },
                            { name: '구간갯수', type: 'value',  min: 0, max: maxOfGroupCntData }
                            ],
                        series : [
                            {
                                name: '연장',
                                type: 'bar',
                                data: groupLenData
                            },
                            {
                                name: '구간갯수',
                                type: 'line',
                                yAxisIndex: 1,
                                data: groupCntData
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
        tHtml   += '<td align="center" class="bg">'             +   mainData[i].dept_nm                    + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].group_len        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].group_cnt        + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/rpairtrgetgroup/rpairDeptLenStatsExcel.do'/>", "");
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