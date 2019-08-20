<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관할기관기준 처리현황</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
</head>
<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidxexcel" name="sidxexcel" value=""/>
<input type="hidden" id="sordexcel" name="sordexcel" value=""/>


<div class="tabcont datePeriod" >
    <div class="fl bgsch" style="height: 1300px">
        <h3>검색조건</h3>
        <div class="schbx mt10">
            <ul class="sch">
                <!-- 접수경로 -->
                <li class="wid100">
                    <label>관할기관</label>
                    <select id="DEPT_CODE" name="DEPT_CODE" class="input" style="width: 75%;" onkeydown="fnCheckEnter(event);" >
                        <option value="">== 전체 ==</option>
                        <option value="9999999">관할구역 없음</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.DEPT_CODE}">${dept.LOWEST_DEPT_NM}</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <label>접수경로</label>
                    <select id="PTHMODE" name="PTHMODE" class="input" style="width:75%;" onkeydown="fnCheckEnter(event);" onchange="fnpthmodenm();" >
                        <option value="">== 전체 ==</option>
                        <c:forEach items="${prrtList}" var="prrt">
                            <option value="${prrt.CODE_VAL}">${prrt.CODE_NM}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" id="pthmodenm">
                </li>

                <li class="wid100" style="margin: 4px 0px;">
                    <label>신고기간</label>
                    <a href="#" id="btnPeriod" class="stmstDate on" onclick="fnChangePeriod($(this)); return false;" style="padding: 4px 8px; color: white;">기간지정</a>
                    <a href="#" id="btnMonth" class="stmstDate" onclick="fnChangePeriod($(this)); return false;" style="padding: 4px 8px; color: white;">월별</a>
                    <a href="#" id="btnQuarter" class="stmstDate" onclick="fnChangePeriod($(this)); return false;" style="padding: 4px 8px; color: white;">분기별</a>
                    <a href="#" id="btnYear" class="stmstDate" onclick="fnChangePeriod($(this)); return false;" style="padding: 4px 8px; color: white;">연간</a>
                </li>

                <!-- 기간지정 -->
                <li class="wid100" id="dvPeriod">
                    <label></label>
                    <span class="calendar" style="height: 22px">
                        <input type="text" id="STTEMNT_DT_START" name="STTEMNT_DT_START" style="width:70px; font-size: 8px;" onkeydown="fnCheckEnter(event);" />
                    </span> ~
                    <span class="calendar" style="height: 22px">
                        <input type="text" id="STTEMNT_DT_END" name="STTEMNT_DT_END" style="width:70px; font-size: 8px;" onkeydown="fnCheckEnter(event);" />
                    </span>
                </li>

                <!-- 월별 -->
                <li class="wid100" id="dvMonth" style="display: none;">
                    <span>
		                <label></label>
	                    <select id="dvMonth_Year"  style="width:36%;">
	                    </select>
                    </span>

                    <span>
	                    <select id="dvMonth_Month" style="width:36%;">
	                        <option value="01"  id="mt1" >1월</option>
	                        <option value="02"  id="mt2" >2월</option>
	                        <option value="03"  id="mt3" >3월</option>
	                        <option value="04"  id="mt4" >4월</option>
	                        <option value="05"  id="mt5" >5월</option>
	                        <option value="06"  id="mt6" >6월</option>
	                        <option value="07"  id="mt7" >7월</option>
	                        <option value="08"  id="mt8" >8월</option>
	                        <option value="09"  id="mt9" >9월</option>
	                        <option value="10"  id="mt10">10월</option>
	                        <option value="11"  id="mt11">11월</option>
	                        <option value="12"  id="mt12">12월</option>
	                    </select>
                    </span>
                </li>

                <!-- 분기별 -->
                <li class="wid100" id="dvQuarter" style="display: none;">
                    <span>
                        <label></label>
                        <select id="dvQuarter_Year" style="width:36%;">
                        </select>
                    </span>
                    <span>
	                    <select id="dvQuarter_Quarter" style="width:36%;">
	                        <option value="1"  id="qt_q1" >1분기</option>
                            <option value="2"  id="qt_q2" >2분기</option>
                            <option value="3"  id="qt_q3" >3분기</option>
                            <option value="4"  id="qt_q4" >4분기</option>
	                    </select>
                    </span>
                </li>

                <!-- 연간 -->
                <li class="wid100" id="dvYear" style="display: none;">
                    <label></label>
                    <select id="year_sel" style="width:76%;">
                        <option value="">== 전체 ==</option>
                    </select>
                    <label>처리상태</label>
                    <select id="PRCS_STTUS" name="PRCS_STTUS" class="input" style="width:76%;" onkeydown="fnCheckEnter(event);" >
                        <option value="PRCS0000">전체신고</option>
                        <c:forEach items="${prcsList}" var="prcs">
                            <option value="${prcs.CODE_VAL}">${prcs.CODE_NM}</option>
                        </c:forEach>
                    </select>
                </li>

                <li class="wid100" style="margin-top: 10px;">
                    <a href="#" class="schbtn dpb" onclick="javascript: fn_search(); fnDateChart();">검색</a>
                </li>
                <li class="wid100" style="margin-top: 10px;">
                    <span style="font-size: 12px;color: #676a6d;font-weight: bold;">19년 00월 부터 국토부앱, 시·군관리 신고 조회가능</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="fr listbx">
        <h3>관할기관기준 처리현황</h3>
        <div class="mt5 ml10 mr10">
<!--
            <div class="btnbx mb10" style="margin-right: 25px; text-align: left; height: 40px;">
                <span id="tl1" class="sttsth2" >기간별 처리현황 조회 (<span id="strtDt"></span> ~ <span id="endDt"></span>)</span>
                <span id="tl2" class="sttsth2" style="display:none">월별 처리현황 조회 (<span id=""></span>)</span>
                <span id="tl3" class="sttsth2" style="display:none">분기별 처리현황 조회 (<span id=""></span>)</span>
                <span id="tl4" class="sttsth2" style="display:none">연간 처리현황 조회 ( 관할기관 : <span id=""></span>)</span>

                <a href="#" onclick="fnShowTable();" class="schbtn date-period-chart" style="position: relative; top: 8px; margin-right: 5px; float: right;">상세조회</a> &nbsp;
                <a href="#" onclick="fnShowChart();" class="schbtn date-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">차트조회</a> &nbsp;
                <a href="#" onclick="fnExcel();" class="schbtn date-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">엑셀저장</a>
            </div>
 -->
            <div class="" style="margin-top:0px;">
            	<span id="tl5" class="sttsth2" ><span id="pthmodenmnm"></span></span>
                <span id="tl1" class="sttsth2" >기간별 처리현황 조회 (<span id="strtDt"></span> ~ <span id="endDt"></span>)</span>
                <span id="tl2" class="sttsth2" style="display:none">월별 처리현황 조회 (<span id=""></span>)</span>
                <span id="tl3" class="sttsth2" style="display:none">분기별 처리현황 조회 (<span id=""></span>)</span>
                <span id="tl4" class="sttsth2" style="display:none"></span>
                <span>
	                <a href="#" onclick="fnShowTable();" class="schbtn date-period-chart" style="position: relative; top: 8px; margin-right: 5px; float: right;">상세조회</a> &nbsp;
	                <a href="#" onclick="fnShowChart();" class="schbtn date-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">차트조회</a> &nbsp;
	                <a href="#" onclick="fnExcel();" class="schbtn date-period-list" style="position: relative; top: 8px; margin-right: 5px; float: right;">엑셀다운로드</a>
	            </span>
            </div>

            <div id="chart1_legend" class="date-period-chart" style="text-align: center; display: none;">
                <div class="legend-box" style="background-color: #383838;"></div><div class="legend-text">전체신고</div>
                <div class="legend-box" style="background-color: #c00000;"></div><div class="legend-text">신고</div>
                <div class="legend-box" style="background-color: #ffc000;"></div><div class="legend-text">접수</div>
                <div class="legend-box" style="background-color: #92d050;"></div><div class="legend-text">처리완료</div>
                <div class="legend-box" style="background-color: #4bacc6;"></div><div class="legend-text">보수예정</div>
                <div class="legend-box" style="background-color: #8064a2;"></div><div class="legend-text">재배정요청</div>
                <div class="legend-box" style="background-color: #f381e5;"></div><div class="legend-text">기타</div>
                <div class="legend-box" style="background-color: #aaaaaa;"></div><div class="legend-text">좌표오류</div>
            </div>

            <div class="date-period-list" style="">

	            <!-- grid1(기간별, 월별, 분기별) START -->
	            <div class="" id="div_grid1" style="width:100%; height:206px;">
	                <table id="gridArea1"></table>
	                <!-- <div id="gridPager1"></div> -->
	            </div>
	            <!-- grid1 END -->
	            <!-- grid2(연별) START -->
	            <div class="" id="div_grid2" style="width:100%; display:none; height:206px;">
	                <table id="gridArea2"></table>
	                <!-- <div id="gridPager2"></div> -->
	            </div>
	            <!-- grid2 END -->
            </div>

            <!-- 그래프 START -->
            <div class="date-period-chart" style="display:none; margin-top:9px; ">
	            <div id="chartArea1" class="" style="overflow-y: hidden; overflow-x: auto; display:none">
	                <ul class="statsbx" style="margin:-5px 0;">
	                    <li style="float: none;">
	                            <div id="dateChart1" class="cont_ConBx2" style="height: 600px; margin-left:20px;"></div>
	                    </li>
	                </ul>
	            </div>
	            <div id="chartArea2" class="" style="display:none; overflow-y: hidden; overflow-x: auto; margin-top:-22px;">
                    <ul class="statsbx">
                        <li style="float: none;">
                                <div id="dateChart2" class="cont_ConBx2" style="height: 600px; margin-left:20px;"></div>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- 그래프 END -->
        </div>
    </div>
</div>

</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript" language="javascript" defer="defer">


var listCnt = 0;
var list = null;
var mode = 'table'; // table : 상세보기, chart : 그래프
var lowDate = 0;

var titleText;
var titleTextYear;

var startDt;
var endDt;

$(window).on('resize', function(){
    if ( list[0].PRCS_STTUS1 ) {
    	drawDatePeriodChart1(list, parent.innerWidth - 310, titleText);
    }else {
    	drawDatePeriodChart2(list, parent.innerWidth - 310, titleTextYear);
    }

	//drawDatePeriodChart2(list, parent.innerWidth - 310);
});

$( document ).ready(function() {

    // 2018. 01. 26. YYK. 경기도로 모니터링단 시스템
    // 필수액션 : 서브메뉴 버튼영역 hide =============== //
    parent.$(".tab_wrap").css("margin-left", "0px");
    // ================================================= //
/*
    // 2018. 02. 05. JOY. 검색조건 부서 setting
    var usrGrp = "${sessionScope.userinfo.REQ_USER_GRP}";

    if ( usrGrp != 'ROLE_ADMIN' ) {
        // 관리자가 아닌 경우엔 부서 setting
        var usrDept = "${sessionScope.userinfo.DEPT_CODE}";

        if ( !usrDept.startsWith('7') ) {
            // 일반 시 내부 사용자
            var setDept = usrDept.substr(0, 3) + '0000';
            $("#DEPT_CODE").val(setDept);

        } else {
            // 포트홀 추가 사용자
            $("#DEPT_CODE").val(usrDept);
        }
    }
 */
    // 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('STTEMNT_DT_START', 'STTEMNT_DT_END', 30);
    COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_DT_START', 'RPAIR_DT_END', 30);

    // 30일 전 Default Setting
    $('#STTEMNT_DT_START').val($.datepicker.formatDate('yy-mm-dd', new Date(new Date-(3600000*24*30))));
    $('#STTEMNT_DT_END').val($.datepicker.formatDate('yy-mm-dd', new Date()));


    var d = new Date();


    // 최대 최소년도
    var minDate = "${minyear}";
    var maxDate = "${maxyear}";

    var minYear = minDate.substr(0, 4) * 1; // 최소년도
    var maxYear = maxDate.substr(0, 4) * 1; // 최대년도

    var minMonth = minDate.substr(4, 2) * 1; // 최소년도 최소월
    var maxMonth = maxDate.substr(4, 2) * 1; // 최대년도 최대월

    var minQuarter = (minMonth / 3 <= 1)? 1 : ((minMonth / 3 <= 2)? 2 : ((minMonth / 3 <= 3)? 3 : 4 )) ;
    var maxQuarter = (maxMonth / 3 <= 1)? 1 : ((maxMonth / 3 <= 2)? 2 : ((maxMonth / 3 <= 3)? 3 : 4 )) ;


    // ======  기간별 연도 setting  ======

    var yr = d.getFullYear();

    for(var i=0; i<= maxYear-minYear; i++){
        $("#dvMonth_Year").append('<option id="#mt_y'+i+'" value="'+(maxYear-i)+'">'+(maxYear-i)+'년</option>');
        $("#dvQuarter_Year").append('<option id="#qt_y'+i+'" value="'+(maxYear-i)+'">'+(maxYear-i)+'년</option>');
        $("#dvYear #year_sel").append('<option id="#yr'+i+'" value="'+(maxYear-i)+'">'+(maxYear-i)+'년</option>');
    }

    $(".date-period-list").show();
    $(".date-period-chart").hide();

    fnGridArea1();
    fnGridArea2();
    //fnDateChart();

});

function fnpthmodenm(){
	if($("#PTHMODE").val() == 'T'){
		$("#pthmodenm").val('스마트 카드');
	} else if($("#PTHMODE").val() == 'M'){
		$("#pthmodenm").val('국토부 앱');
	} else if($("#PTHMODE").val() == 'C'){
		$("#pthmodenm").val('31개 시·군관리');
	} else {
		$("#pthmodenm").val('');
	}
}


/* ===================================================================== */
/* =============================== TABLE =============================== */
// Grid Table 그리기
function fnGridArea1() {

    var postData = $("#frm").cmSerializeObject();


    // 검색 목록 그리드 구성
    $("#gridArea1").jqGrid({
        url: '<c:url value="/"/>'+'api/pothole/sttst/selectDatePeriodList.do'
        , autoencode: true
        , contentType : 'application/json'
        , datatype: "local"
        , mtype: "POST"
        , ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        , postData: postData
        , ignoreCase: true
        , colNames:["부서코드", "관할기관", "전체신고(건)", "신고(건)","접수(건)","처리완료(건)", "보수예정(건)", "재배정요청(건)", "기타(건)", "좌표오류(건)", "백분율<br/>(처리완료÷전체신고)"]
        , colModel:[
            {name: 'DEPT_CODE'          , index: 'DEPT_CODE'        , align: 'center'   , hidden: true  , sortable: true}
            , {name: 'LOWEST_DEPT_NM'   , index: 'LOWEST_DEPT_NM'   , align: 'center'   , width: 100    , sortable: true}
            , {name: 'PRCS_SUM'         , index: 'PRCS_SUM'         , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS1'       , index: 'PRCS_STTUS1'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS2'       , index: 'PRCS_STTUS2'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS3'       , index: 'PRCS_STTUS3'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS4'       , index: 'PRCS_STTUS4'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS5'       , index: 'PRCS_STTUS5'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS6'       , index: 'PRCS_STTUS6'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PRCS_STTUS7'       , index: 'PRCS_STTUS7'       , align: 'center'   , width: 50    , sortable: true ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
            , {name: 'PERCENT'          , index: 'PERCENT'          , align: 'center'   , width: 60    , sortable: true  ,formatter: fnPercent }
        ]
        , async : false
        , sortname: 'deptnum'  //YYK 관할기관 순서 경기도청에 맞추기 위해... (쿼리에서 확인할 수 있다.)
        , sortorder: "asc"
        , rowNum: 100
        , rowList: [100]
        , viewrecords: true
        , rownumbers: true
        , pager: '#gridPager'
        , loadtext: "검색 중입니다."
        , emptyrecords: "검색된 데이터가 없습니다."
        , recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        , ondblClickRow: function(rowId) { }
        , onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea1" ).getRowData(rowId);
            }
        }
        , loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                $("#sidxexcel").val(this.p.postData.sidx);
                this.p.postData.sord = this.p.sortorder;
                $("#sordexcel").val(this.p.postData.sord);
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        , multiselect: false
        , multiboxonly: false

    }).navGrid('#gridPager1',{edit:false,add:false,del:false,search:false,refresh:false});

    var height = $(parent.window).height() - 300;
    COMMON_UTIL.cmInitGridSize('gridArea1','div_grid1', height);

    fn_search();

    $(window).resize(function(){

        var height = $(parent.window).height() - 300;
        COMMON_UTIL.cmInitGridSize('gridArea1','div_grid1', height);

    });
}


function fnGridArea2() {
    var postData = $("#frm").cmSerializeObject();

    // 검색 목록 그리드 구성
    $("#gridArea2").jqGrid({
        url: '<c:url value="/"/>'+'api/pothole/sttst/selectDatePeriodList.do'
        , autoencode: true
        , contentType : 'application/json'
        , datatype: "local"
        , mtype: "POST"
        , ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        , postData: postData
        , ignoreCase: true
        , colNames:[ "연도" , "신고(건)"]
        , colModel:[
              {name: 'STTEMNT_DT'   , index: 'STTEMNT_DT'   , align: 'center'   , width: 100    , sortable: true  }
            , {name: 'PRCS_COUNT'   , index: 'PRCS_COUNT'   , align: 'center'   , width: 100    , sortable: true  ,  formatter:'number',formatoptions:{decimalPlaces: 0}}
        ]
        , async : false
        , sortname: 'STTEMNT_DT'
        , sortorder: "desc"
        , rowNum: 100
        , rowList: [100]
        , viewrecords: true
        , rownumbers: true
        , pager: '#gridPager'
        , loadtext: "검색 중입니다."
        , emptyrecords: "검색된 데이터가 없습니다."
        , recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        , ondblClickRow: function(rowId) { }
        , onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea2" ).getRowData(rowId);
            }
        }
        , loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                $("#sidxexcel").val(this.p.postData.sidx);
                this.p.postData.sord = this.p.sortorder;
                $("#sordexcel").val(this.p.postData.sord);
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        , multiselect: false
        , multiboxonly: false

    }).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});

    var height = $(parent.window).height() - 300;
    COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', height);

    fn_search();

    $(window).resize(function(){

        var height = $(parent.window).height() - 300;
        COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', height);

    });
}


//검색 처리
function fn_search() {

	// colNames 값 setting
    var prcsSttus = $("#PRCS_STTUS").val();
	$('#jqgh_gridArea2_PRCS_COUNT').text($("#PRCS_STTUS option[value='"+prcsSttus+"']").text() + "(건)");

// ========== grid show, hide 영역 ==========
    var num = 1;
	$("#div_grid1, #div_grid2").hide();

	if ($("#btnYear").hasClass("on")){
	    num = 2;
	}

	$("#div_grid"+ num).show();

    var postData = $("#frm").cmSerializeObject();
    postData["PRCS_STTUS"] = '';



// =============== 신고기간에 따른 변화 ===============

	titleText ; // echart 타이틀명 설정용도

	// =========== 1. 기간별 ===========
    if ( $("#btnPeriod").hasClass("on") ) {
        $("#tl1").show();
        $("#tl2").hide();
        $("#tl3").hide();
        $("#tl4").hide();

        startDt = $("#STTEMNT_DT_START").val();
        endDt = $("#STTEMNT_DT_END").val();

        //  title에 날짜 setting
        $("#strtDt").text($("#STTEMNT_DT_START").val());
        $("#endDt").text($("#STTEMNT_DT_END").val());
        $("#pthmodenmnm").text($("#pthmodenm").val());

        titleText = $('#tl1').text();
    }

    // =========== 2. 월별 ===========
    if ( $("#btnMonth").hasClass("on") ) {
        $("#tl1").hide();
        $("#tl2").show();
        $("#tl3").hide();
        $("#tl4").hide();

        var selYear = $("#dvMonth_Year").val();
        var selMonth = $("#dvMonth_Month").val();
        startDt = new Date(selYear, selMonth-1, 1).format("yyyy-MM-dd");
        endDt = new Date(selYear, selMonth, 0).format("yyyy-MM-dd");

        //  title에 날짜 setting
        $("#tl2 span").text($("#dvMonth select").val()+"년 " + $("#dvMonth_Month").val() +"월" );

        titleText = $('#tl2').text();

        $("#pthmodenmnm").text($("#pthmodenm").val());
    }

    // =========== 3. 분기별 ===========
    if ( $("#btnQuarter").hasClass("on") ) {
        $("#tl1").hide();
        $("#tl2").hide();
        $("#tl3").show();
        $("#tl4").hide();

        var selYear = $("#dvQuarter_Year").val();
        var selQuarter = $("#dvQuarter_Quarter").val();

        if ( selQuarter == 1 ) {
            startDt = selYear + "-01-01";
            endDt = selYear + "-03-31";
        } else if ( selQuarter == 2 ) {
            startDt = selYear + "-04-01";
            endDt = selYear + "-06-30";
        } else if ( selQuarter == 3 ) {
            startDt = selYear + "-07-01";
            endDt = selYear + "-09-30";
        } else {
            startDt = selYear + "-10-01";
            endDt = selYear + "-12-31";
        }

        //  title에 날짜 setting
        $("#tl3 span").text($("#dvQuarter select").val()+ "년 " + $("#dvQuarter_Quarter").val() +"분기");

        titleText = $('#tl3').text();

        $("#pthmodenmnm").text($("#pthmodenm").val());
    }

    // =========== 4. 연별 ===========
    if ( $("#btnYear").hasClass("on") ) {
        $("#tl1").hide();
        $("#tl2").hide();
        $("#tl3").hide();
        $("#tl4").show();

    	var selYear = $("#dvYear #year_sel").val() ;
        if ( selYear == '' || !selYear  ){
        	startDt = '';
            endDt = '';
        }
        else {
        	startDt = selYear + "-01-01";
            endDt = selYear + "-12-31";
        }
        postData["PRCS_STTUS"] = $("#frm").cmSerializeObject().PRCS_STTUS;
        postData["STTEMNT_DT"] = $("#dvYear #year_sel").val();

        //  title에 처리상태 + 신고기간 setting
        var prcsVal = $('#PRCS_STTUS').val() ;
        var prcsText = $('#PRCS_STTUS option[VALUE='+ prcsVal +']').text() ;
        var deptCodeVal = $("#DEPT_CODE").val() ;
        var deptCodeText = $("#DEPT_CODE option[value='"+deptCodeVal+"']").text().replace(/=/g, '')

        titleTextYear = '연간 '+ prcsText +' 현황 조회 ( 관할기관 : '+ deptCodeText +' )' ;
        $("#tl4").text(titleTextYear);

        $("#pthmodenmnm").text($("#pthmodenm").val());
    }

    postData["STTEMNT_DT_START"] = startDt;
    postData["STTEMNT_DT_END"] = endDt;


    $("#gridArea"+num ).jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {
            var rw = $(window).width() - 210;

			for (var i=0; i<data.rows.length; i++){
			    if (!data.rows[i].LOWEST_DEPT_NM ){
			        data.rows[i].LOWEST_DEPT_NM = '해당없음';
			    }
			}

            listCnt = data.records;
            list = data.rows;

	        if (!$("#btnYear").hasClass("on")){
            	drawDatePeriodChart1(data.rows, rw, titleText);
            }
            else{
            	drawDatePeriodChart2(data.rows, rw, titleTextYear);
            }

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea'+ num , $("#gridArea" +num ).jqGrid("getGridParam").emptyrecords, data.records);
        }

    }).trigger("reloadGrid");

}


//퍼센트
function fnPercent(cellValue, options, rowObject) {
	if ( rowObject.PERCENT > 0.001 && rowObject.PERCENT < 0.01){
		return "0" + rowObject.PERCENT + "%";
	}
    return rowObject.PERCENT + "%";
}

//엑셀 다운로드
function fnExcel() {

    if ( listCnt == 0 ) {
        alert("검색된 데이터가 없습니다.");
        return;
    }

    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        //$("#STTEMNT_DT_START").val(startDt);
        //$("#STTEMNT_DT_END").val(endDt);
        //$("#STTEMNT_DT").val($("#dvYear #year_sel").val());
        $('#STTEMNT_DT_START').attr("disabled", "disabled");
        $('#STTEMNT_DT_END').attr("disabled", "disabled");

//        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectDatePeriodListExcel.do'/>?STTEMNT_DT_START="+startDt+"&STTEMNT_DT_END="+endDt+"&sidx="+$("#sidxexcel").val()+"&sord="+$("#sordexcel").val(), "");

        // 연별 조회가 아닐경우
        if (!$("#btnYear").hasClass("on")){
        	COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectDatePeriodListExcel.do'/>?STTEMNT_DT_START="+startDt+"&STTEMNT_DT_END="+endDt+"&sidx="+$("#sidxexcel").val()+"&sord="+$("#sordexcel").val(), "");
        	//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectDatePeriodListExcel.do'/>?sidx="+$("#sidxexcel").val()+"&sord="+$("#sordexcel").val(), "");
        }
        else{ // 연별 조회시
        	COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectDatePeriodListExcelYear.do'/>?STTEMNT_DT_START="+startDt+"&STTEMNT_DT_END="+endDt+"&sidx="+$("#sidxexcel").val()+"&sord="+$("#sordexcel").val(), "");
        	//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/pothole/sttst/selectDatePeriodListExcelYear.do'/>?sidx="+$("#sidxexcel").val()+"&sord="+$("#sordexcel").val(), "");
        }

        $('#STTEMNT_DT_START').removeAttr("disabled");
        $('#STTEMNT_DT_END').removeAttr("disabled");
    }
}


/* ===================================================================== */
/* =============================== CHART =============================== */

function fnDateChart() {

    var postData = $("#frm").cmSerializeObject();
}


//Script
require.config({
	paths: {
	    echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
	}
});

/*
//창 조절시 차트 resize
$(window).on('resize', function(){
	var rw = $(window).width() - 210;
	var height = $(window).height() - 200;
	//$("#chartArea1, #chartArea2").height(height + "px");
	//$("#dateChart1, #dateChart2").height((height - 50) + "px")
	$("#chartArea2").height(height + "px");
    $("#dateChart2").height((height - 50) + "px")
	fn_search();

});
 */

// 기간별,월별,분기별 차트
function drawDatePeriodChart1(dataList, rw, titleText) {

	var height = parent.innerHeight - 250;
	$("#dateChart1").height( (height - 30) + "px" );
	$("#chartArea1").height( height + "px");

	var width = parent.innerWidth - 320;
	$("#chartArea1").width(width + "px");

	if  ( listCnt <= 12 ) {

	    $("#dateChart1").width( (width - 50) + "px" );

	} else {
	    $("#dateChart1").width( (100 * listCnt) + "px" );
	}


    var colors = ['#ffa126'];

    var gDateNm    = [];
    var gPrcsSum = [];
    var gPrcsSttus1 = [];
    var gPrcsSttus2 = [];
    var gPrcsSttus3 = [];
    var gPrcsSttus4 = [];
    var gPrcsSttus5 = [];
    var gPrcsSttus6 = [];
    var gPrcsSttus7 = [];
    var degree      = -20;  // 회전 설정

    if ( dataList.length < 10 ) {
        degree = 0;
    }

    for ( var i = 1; i < dataList.length; i++ ) {

        gDateNm.push(dataList[i].LOWEST_DEPT_NM);
        gPrcsSum.push(dataList[i].PRCS_SUM);
        gPrcsSttus1.push(dataList[i].PRCS_STTUS1);
        gPrcsSttus2.push(dataList[i].PRCS_STTUS2);
        gPrcsSttus3.push(dataList[i].PRCS_STTUS3);
        gPrcsSttus4.push(dataList[i].PRCS_STTUS4);
        gPrcsSttus5.push(dataList[i].PRCS_STTUS5);
        gPrcsSttus6.push(dataList[i].PRCS_STTUS6);
        gPrcsSttus7.push(dataList[i].PRCS_STTUS7);

    }

	// 차트영역 show (hide 상태에서는 차트가 안그려짐)
	$(".date-period-chart").show();
	$("#chartArea1").show();
	$("#chartArea2").hide();

    require([   'echarts','echarts/chart/bar'   ],
	function (ec) {
	     var myChart = ec.init(document.getElementById('dateChart1'));
	     myChart.setOption({
	    /*
            title   : { text: titleText
                        //,y : 20
            }
	      */
            color   : colors
            ,tooltip : {
                        trigger: 'axis'
                        ,axisPointer : { type : 'shadow' }
            }
            ,toolbox : { show: true,
                feature: {
                    dataView : {show: false, readOnly: false},   // 상세조회
                    saveAsExcel : {show: false},                 // 엑셀저장
                    saveAsImage: {show: false}                   // 이미지저장
                }
            }
            /*
            ,legend: {
                data: ['전체　신고', '신　　　고', '접　　　수', '처리　완료', '보수　예정', '재배정요청', '기　　　타', '좌표　오류']
                , y : 35
                , x : parent.innerWidth / 3 - 200
            }
             */
            ,grid :{
                left: '3%',
                right: '4%',
                bottom: '1%'
                ,y : 10
                ,y2 : 100
                ,x : 20
                //,y2 : 120
            }
            ,xAxis:  {
                type: 'category'
                ,axisLabel : {
                    show:true,
                    interval: 0,
                    rotate: degree
                },
                data: gDateNm
            }
            ,yAxis: {
                type: 'value'
            }
            ,series: [
                 {
                     name: '전체　신고',
                     type: 'bar',
                     data: gPrcsSum,
                     itemStyle: {
                         normal: {
                             color: '#383838'
                             ,label : {
                                 show: true
                                 ,position: 'insideBottom'
                                 ,formatter: '{c}건'
                                 /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
                             }
                         }
                     }
                 },
                 {
                     name: '신　　　고',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus1,
                     itemStyle: {
                         normal: {
                             color: '#c00000'
                             ,label : {
                                 show: true
                                 ,position: 'insideBottom'
                                 ,formatter: '{c}건'
                                 /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
                             }
                         }
                     }
                 },
                 {
                     name: '접　　　수',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus2,
                     itemStyle: {
                         normal: {
                             color: '#ffc000'
                             ,label : {
                                 show: true
                                 ,position: 'insideBottom'
                                 ,formatter: '{c}건'
                                 /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
                             }
                         }
                     }
                 },
                 {
                     name: '처리　완료',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus3,
                     itemStyle: {
                         normal: {
                             color: '#92d050'
                             ,label : {
                                 show: true
                                 ,position: 'insideBottom'
                                 ,formatter: '{c}건'
                                 /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
                             }
                         }
                     }
                 },
                 {
                     name: '보수　예정',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus4,
                     itemStyle: {
                         normal: {
                             color: '#4bacc6'
	                         ,label : {
	                             show: true
	                             ,position: 'insideBottom'
	                             ,formatter: '{c}건'
	                             /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
	                         }
                         }
                     }
                 },
                 {
                     name: '재배정요청',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus5,
                     itemStyle: {
                         normal: {
                             color: '#8064a2'
                             ,label : {
	                             show: true
	                             ,position: 'insideBottom'
	                             ,formatter: '{c}건'
	                             /* ,textStyle : {
                                         fontSize : '12',
                                         color : 'black',
                                         fontWeight : 'bold'
                                 } */
	                         }
                         }
                     }
                 },
                 {
                     name: '기　　　타',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus6,
                     itemStyle: {
                         normal: {
                             color: '#f381e5'
                         },
                         label : {
                             show: true
                             ,position: 'insideBottom'
                             ,formatter: '{c}건'
                             /* ,textStyle : {
                                     fontSize : '12',
                                     color : 'black',
                                     fontWeight : 'bold'
                             } */
                         }
                     }
                 },
                 {
                     name: '좌표　오류',
                     type: 'bar',
                     stack: '总量',
                     data: gPrcsSttus7,
                     itemStyle: {
                         normal: {
                             color: '#aaaaaa'
                         },
                          label : {
                             show: true
                             ,position: 'insideBottom'
                             ,formatter: '{c}건'
                             /* ,textStyle : {
                                     fontSize : '12',
                                     color : 'black',
                                     fontWeight : 'bold'
                             } */
                         }
                     }
                 }
             ]
        });
        if ( mode == 'table' ) {
            $(".date-period-list").show();
            $(".date-period-chart").hide();

        } else if ( mode == 'chart' ) {
            $(".date-period-list").hide();
            $(".date-period-chart").show();
        }
    });
}


// 연별 차트
function drawDatePeriodChart2(dataList ,rw, titleTextYear) {

    var height = parent.innerHeight - 200;
    $("#dateChart2").height( (height - 30) + "px" );
    $("#chartArea2").height( height + "px");

    var width = parent.innerWidth - 320;
    $("#chartArea2").width(width + "px");

    if  ( listCnt <= 12 ) {

        $("#dateChart2").width( (width - 50) + "px" );

    } else {
        $("#dateChart2").width( (100 * listCnt) + "px" );
    }

	// var colors = ['#ffa126'];

    var legendData = [];  // 선 이름
    var seriesList = [];  // data 값

    for ( var i = 0; i < dataList.length; i++ ) {
    	seriesList[i] = {
            name: dataList[i].STTEMNT_DT,
            type: 'line',
            symbol:'emptyCircle',
            // yyk. data 속성 편집(값 추가)
            data: [ { value: dataList[i].PRCS_COUNT1  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
            	   ,{ value: dataList[i].PRCS_COUNT2  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT3  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT4  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT5  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT6  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT7  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT8  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT9  ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT10 ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT11 ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                   ,{ value: dataList[i].PRCS_COUNT12 ,itemStyle: {normal:{label:{show:true ,formatter: '{c}건' } } } }
                  ]
        }
    	legendData[i] = dataList[i].STTEMNT_DT;
    }

    // 차트영역 show (hide 상태에서는 차트가 안그려짐)
    $(".date-period-chart").show();
    $("#chartArea1").hide();
    $("#chartArea2").show();

    require([   'echarts',/* 'echarts/chasrt/bar', */ "echarts/chart/line"   ],
        function (ec) {
            var myChart = ec.init(document.getElementById('dateChart2'));
            myChart.setOption({
            	/*
				title   : { text: titleTextYear
				            //, x : 'center'
				},
				 */
				tooltip : { trigger: 'axis' },
				legend: {
				       x: 'center',
				       y: 12,
				       data: legendData
				   },

				toolbox : { show: true,
				    feature: {
				        dataView : {show: false, readOnly: false},   // 상세조회
				        saveAsExcel : {show: false},                 // 엑셀저장
				        saveAsImage: {show: false}                   // 이미지저장
				    }
				},
				grid :{
					x : 60
				    ,x2 : 40
				    ,y2 : 30
				    ,containLabel: true
				},
				xAxis : [{
				            //name : '',
				            type : 'category',
				            axisLabel : {
				                show:true,
				                interval: 0,
				                rotate: 0
				            },
				            data : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
				        }],
				yAxis : [{  name : '(건)',     type : 'value'      }],
				series : seriesList

				});
		if ( mode == 'table' ) {
		    $(".date-period-list").show();
		    $(".date-period-chart").hide();

		} else if ( mode == 'chart' ) {
		    $(".date-period-list").hide();
		    $(".date-period-chart").show();
		}
    });
}


//그래프 보기
function fnShowChart() {
	$(".date-period-list").hide();
	$(".date-period-chart").show();
	if ( $('#btnYear').hasClass('on') ){
		$('#chart1_legend').hide();
	}
}


// 테이블 보기
function fnShowTable() {
    $(".date-period-list").show();
    $(".date-period-chart").hide();
}

/* ===================================================================== */
/* =============================== COMMON ============================== */

// 검색조건 enter check function
function fnCheckEnter(event) {

    if ( event.keyCode == 13 ) {

        fn_search();
    }
}

// 버튼 on off function
function fnChangePeriod(obj) {

    // on 클래스 초기화
    obj.parent().find("a").removeClass("on");
    obj.addClass("on");

    if ( obj.attr("id") == "btnPeriod" ) {
        $("#dvPeriod").show();
        $("#dvMonth").hide();
        $("#dvQuarter").hide();
        $("#dvYear").hide();

    } else if ( obj.attr("id") == "btnMonth" ) {
        $("#dvPeriod").hide();
        $("#dvMonth").show();
        $("#dvQuarter").hide();
        $("#dvYear").hide();

    } else if ( obj.attr("id") == "btnQuarter" ) {
	    $("#dvPeriod").hide();
	    $("#dvMonth").hide();
	    $("#dvQuarter").show();
	    $("#dvYear").hide();

    } else if ( obj.attr("id") == "btnYear" ) {
    	$("#dvPeriod").hide();
	    $("#dvMonth").hide();
	    $("#dvQuarter").hide();
	    $("#dvYear").show();
	}

}
</script>

</body>
</html>