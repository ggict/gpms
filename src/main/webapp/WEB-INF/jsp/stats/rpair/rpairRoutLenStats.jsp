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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="SLCTN_YEAR" name="SLCTN_YEAR" value="" />

    <header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">통계</option>
                </select>
                <select name="">
                    <option value="">보수대상 선정</option>
                </select>
                <select name="">
                    <option value="">노선별 통계</option>
                </select>
            </span>
        </div>
    </header>
    
    <div class="container2">
    
        <div class="table searchBox top">
            <table>
                <tbody>
                    <tr>
                        <td style="width:50%;">
                        </td>
                        <td class="th">
                            <label for="SLCTN_YEAR_SELECT">선정년도</label>
                        </td>
                        <td>
                            <select id="SLCTN_YEAR_SELECT">
                                <option value="2019">2019</option>
                                <option value="2018">2018</option>
                                <option value="2017">2017</option>
                            </select>
                        </td>
                        <td class="btnCell"><button type="button" id="btnSearch" class="btn pri">검색</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
	    
	    <div id="divStatChart" style="overflow-y:auto;">
            <ul class="statsbx">
                <li style="float:none; width:97%">
                    <div class="graylinebx p10" style="width:100%;">
                        <div id="rpairRoutChart" class="cont_ConBx2" style="height: 320px; margin-left:20px;"></div>
                    </div>
                    <h4 style="text-align: center;background:none; width:100%;">노선별 보수대상 선정 통계</h4>
                </li>
            </ul>
        </div>
        
        <!-- 표 -->
        <div class="cont_ListBx" style="display: none;">
	        <table class="tblist" border="1" id="diagram">
	            <colgroup>
	                <col width="25%"/>
	                <col width="25%"/>
	                <col width="25%"/>
	                <col width="25%"/>
	            </colgroup>
	            <thead style="text-align: center;">
	                <tr>
	                    <th scope="col">노선번호</th>
	                    <th scope="col">노선명</th>
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
var errNo=0;    //에러 메시지 변수
var ntcNo=0;    //경고 메시지 변수
var myChart;    // echarts

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
    $("#divStatChart").height($(parent.window).height() - 170);
    fnRpairRoutSearch();   //노선조회
    
    $('#btnSearch').click(function() {
        fnRpairRoutSearch();
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
    myChart.resize();
})

//검색 처리
function fnRpairRoutSearch() {
	
    var SLCTN_YEAR = $('#SLCTN_YEAR_SELECT option:selected').val();
    $('#SLCTN_YEAR').val(SLCTN_YEAR);
    var data = { "SLCTN_YEAR": SLCTN_YEAR };
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/rpairtrgetgroup/selectRpairRoutLenStats.do'
        ,type: 'post'
        ,contentType: 'application/json'
        //,data: JSON.stringify( $("#frm").cmSerializeObject())
        ,data: JSON.stringify(data)
        ,dataType: 'json'
        ,success: function (data) {
            var dataList = data.rows;
            if(dataList.length !=0){
            	drawRoutChart(dataList);
                drawTable(dataList);   // echarts 테이블
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

// 차트
require.config({
       paths: {
            echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
        }
    });

function drawRoutChart(dataList){
    var gRouteNm    = dataList.map(function(elem){ return Number(elem.route_code)+"호선"; });       
    var groupLenData    = dataList.map(function(elem){ return elem.group_len; });
    var maxOfGroupLenData = groupLenData.reduce(function(prev, curr) { return (prev > curr) ? prev : curr; });
    var groupCntData    = dataList.map(function(elem){ return elem.group_cnt; });
    var maxOfGroupCntData = groupCntData.reduce(function(prev, curr) { return (prev > curr) ? prev : curr; });
    var degree      = (dataList.length < 10) ? 0 : -90;
    
    require([   'echarts','echarts/chart/bar', 'echarts/chart/line'   ],
            function (ec) {
                 myChart = ec.init(document.getElementById('rpairRoutChart'));
                 myChart.setOption({
                        title   : { text: '노선별 통계'   },
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
        tHtml   += '<td align="center" class="bg">'             +   Number(mainData[i].route_code)+"호선"+ '</td>';
        tHtml   += '<td align="center" class="bg">'             +   mainData[i].road_nm                    + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].group_len        + '</td>';
        tHtml   += '<td style="text-align:right">'              +   mainData[i].group_cnt        + '</td>';
        tHtml   += '</tr>';
    }
    
    $('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/rpairtrgetgroup/rpairRoutLenStatsExcel.do'/>", "");
    }
}

</script>

</body>
</html>