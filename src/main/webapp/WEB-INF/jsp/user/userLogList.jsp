<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>사용자 접속로그 조회 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<!-- container start -->
<div id="container">
	<div class="admin_content">
		<h2>사용자 접속로그 조회</h2>
		<!--검색영역-->
		<ul class="admin_sch">
			<li>
				<label>상위메뉴</label>
				<select id="SCH_MENU_ID" name="SCH_MENU_ID"  class="select sBx120" onkeydown="fnCheckEnter(event);">
					<option value="">전체</option>
					<c:forEach var="selectData" items="${menu_list}">
						<option value="${selectData.MENU_ID}">${selectData.MENU_NM}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>메뉴명</label>
				<input type="text" id="SCH_MENU_NM" name="SCH_MENU_NM" onkeydown="fnCheckEnter(event);" />
			</li>
			<li>
				<label>접속일자</label>
					    <a href="#" id="btnPeriod" class="userLog on" onclick="fnChangePeriod($(this)); return false;">기간지정</a>
	                    <a href="#" id="btnMonth" class="userLog" onclick="fnChangePeriod($(this)); return false;">월별</a>
	                    <a href="#" id="btnQuarter" class="userLog" onclick="fnChangePeriod($(this)); return false;">분기별</a>
               
                    <!-- 기간지정 -->
                    <span id="dvPeriod">
						<span class="calendar">
							<input type="text" id="SCH_STRCON_DE" name="SCH_STRCON_DE" onkeydown="fnCheckEnter(event);"/>
						</span>~
						<span class="calendar">
							<input type="text" id="SCH_ENDCON_DE" name="SCH_ENDCON_DE" onkeydown="fnCheckEnter(event);"/>
						</span>
					</span>

					<!-- 월별 -->
					<span id="dvMonth" style="display: none;">
                        <span>
	                        <select id="dvMonth_Year"  onkeydown="fnCheckEnter(event);">
	                        </select>
	                    </span>
	                    <span>
	                        <select id="dvMonth_Month"onkeydown="fnCheckEnter(event);">
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
                    </span>

                    <!-- 분기별 -->
                    <span id="dvQuarter" style="display: none; " >
	                    <span>	                        
	                        <select id="dvQuarter_Year"onkeydown="fnCheckEnter(event);">
	                        </select>
	                    </span>
	                    <span>
	                        <select id="dvQuarter_Quarter" onkeydown="fnCheckEnter(event);">
	                            <option value="1"  id="qt_q1" >1분기</option>
	                            <option value="2"  id="qt_q2" >2분기</option>
	                            <option value="3"  id="qt_q3" >3분기</option>
	                            <option value="4"  id="qt_q4" >4분기</option>
	                        </select>
	                    </span>
                    </span>
			<a href="#" class="btn pri posR" onclick="javascript:fn_search();">검색</a>
			</li>
		</ul>
		<div id="div_grid" >
			<table class="adminlist" id="gridArea"></table>
			<div id="gridPager"></div>
		</div>
		<div class="btnArea">
           	 	<a href="#" class="btn pri" onclick="fn_usrLogExcel();">엑셀저장</a>
        </div>
	</div>
</div>
</form>


<script type="text/javascript">

var startDt ;
var endDt ;
var dataCnt;

//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu1", 4);

    //달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRCON_DE', 'SCH_ENDCON_DE', 30);

    // YYK 달력 초기값 설정 : 한달
    $('#SCH_STRCON_DE').val($.datepicker.formatDate('yy-mm-dd', new Date(new Date-(3600000*24*30))));
    $('#SCH_ENDCON_DE').val($.datepicker.formatDate('yy-mm-dd', new Date()));


    // 최대 최소년도
    var minDate = "${minyear}";
    var maxDate = "${maxyear}";

    var minYear = minDate.substr(0, 4) * 1; // 최소년도
    var maxYear = maxDate.substr(0, 4) * 1; // 최대년도

    var minMonth = minDate.substr(4, 2) * 1; // 최소년도 최소월
    var maxMonth = maxDate.substr(4, 2) * 1; // 최대년도 최대월

    var minQuarter = (minMonth / 3 <= 1)? 1 : ((minMonth / 3 <= 2)? 2 : ((minMonth / 3 <= 3)? 3 : 4 )) ;
    var maxQuarter = (maxMonth / 3 <= 1)? 1 : ((maxMonth / 3 <= 2)? 2 : ((maxMonth / 3 <= 3)? 3 : 4 )) ;


    // ====트박스 날짜세팅 ============
    //var d = new Date();
    //var yr = d.getFullYear();

    for(var i=0; i<= maxYear-minYear; i++){
        $("#dvMonth_Year").append('<option id="#mt_y'+i+'" value="'+(maxYear-i)+'">'+(maxYear-i)+'년</option>');
        $("#dvQuarter_Year").append('<option id="#qt_y'+i+'" value="'+(maxYear-i)+'">'+(maxYear-i)+'년</option>');
    }

    var postData = {"USE_AT":"Y"};


    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/sysuser/selectSysUserLogList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["사용자ID","사용자명","소속기관","접속메뉴","접속일시"]
        ,colModel:[
            {name:'USER_ID',index:'USER_ID', align:'center', width:50, sortable:true}
            ,{name:'USER_NM',index:'USER_NM', align:'center', width:50, sortable:true}
            ,{name:'DEPT_NM',index:'DEPT_NM', align:'center', width:100, sortable:true}
            ,{name:'MENU_NM',index:'MENU_NM', align:'center', width:100, sortable:true}
            //,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:50, sortable:true} 
            ,{name:'CREAT_DT',index:'CREAT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:50, sortable:true}
        ]
        ,async : false
        ,sortname: 'CREAT_DT'
        ,sortorder: "desc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
            }
        }
        ,loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                this.p.postData.sord = this.p.sortorder;
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        ,multiselect: false
        ,multiboxonly: false
        //,scroll: true
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

    fn_search();

});

//검색 처리
function fn_search() {
	var postData = $("#frm").cmSerializeObject();
	postData["USE_AT"]= "Y" ;


// =값 셋팅 ===========
	// 1. 기간별
    if ( $("#btnPeriod").hasClass("on") ) {
        startDt = $("#SCH_STRCON_DE").val();
        endDt = $("#SCH_ENDCON_DE").val();
    }

    // 2. 월별
    if ( $("#btnMonth").hasClass("on") ) {
        var selYear = $("#dvMonth_Year").val();
        var selMonth = $("#dvMonth_Month").val();
        startDt = new Date(selYear, selMonth-1, 1).format("yyyy-MM-dd");
        endDt = new Date(selYear, selMonth, 0).format("yyyy-MM-dd");
    }

    // 3. 분기별
    if ( $("#btnQuarter").hasClass("on") ) {
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
    }

    postData["SCH_STRCON_DE"] = startDt;
    postData["SCH_ENDCON_DE"] = endDt;

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {
        	dataCnt = data.total
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

//엑셀 출력
function fn_usrLogExcel(){
	if ( dataCnt > 0 ){
		if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
	        $('#SCH_STRCON_DE').attr("disabled", "disabled");
	        $('#SCH_ENDCON_DE').attr("disabled", "disabled");
	        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/mng/sysuser/selectSysUserLogListExcel.do'/>?SCH_STRCON_DE="+startDt+"&SCH_ENDCON_DE=" +endDt, "");
	        $('#SCH_STRCON_DE').removeAttr("disabled");
	        $('#SCH_ENDCON_DE').removeAttr("disabled");
	    }
	}
	else{
	    alert("데이터가 없습니다.");
	}

}

// 기간지정, 월별, 분기별 선택 시 함수
function fnChangePeriod(obj){

	// on 클래스 초기화
	obj.parent().find("a").removeClass("on");
	obj.addClass("on");

    if ( obj.attr("id") == "btnPeriod" ) {
        $("#dvPeriod").show();
        $("#dvMonth").hide();
        $("#dvQuarter").hide();

    } else if ( obj.attr("id") == "btnMonth" ) {
        $("#dvPeriod").hide();
        $("#dvMonth").show();
        $("#dvQuarter").hide();

    } else if ( obj.attr("id") == "btnQuarter" ) {
        $("#dvPeriod").hide();
        $("#dvMonth").hide();
        $("#dvQuarter").show();
    }
}

//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}

</script>


<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>