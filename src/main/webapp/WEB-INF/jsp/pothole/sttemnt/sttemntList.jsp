<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포트홀 신고관리</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="data_list" name="data_list" value="${data_list}"/>

<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidxexcel" name="sidxexcel" value=""/>
<input type="hidden" id="sordexcel" name="sordexcel" value=""/>
<input type="hidden" id="PTH_RG_NO_LAYER" name="PTH_RG_NO_LAYER" value='${pthRgNo}'/>

<div class="tabcont sttemntList">
    <div class="fl bgsch">
        <h3>검색조건</h3>
        <div class="schbx mt10">
            <ul class="sch">
                <li class="wid100">
                    <label>등록번호</label>
                    <input type="text" name="PTH_RG_NO" id="PTH_RG_NO" value="" style="width: 72%;" class="MX_80 CS_50 input" onkeydown="fnCheckEnter(event);" />
                </li>
                <li class="wid100">
                    <label>신고자</label>
                    <input type="text" name="BSNM_NM" id="BSNM_NM" value="" style="width: 22%;" class="MX_80 CS_50 input" onkeydown="fnCheckEnter(event);" />

                    <label>차량번호</label>
                    <input type="text" name="VHCLE_NO" id="VHCLE_NO" value="" style="width: 22%;" class="MX_80 CS_50 input" onkeydown="fnCheckEnter(event);" />
                </li>
                <li class="wid100">
                    <label>접수경로</label>
                    <select id="pthmode" name="pthmode" class="input" style="width: 75%;" onkeydown="fnCheckEnter(event);" >
                        <option value="">= 전체 =</option>
                        <c:forEach items="${prrtList}" var="prrt">
                            <option value="${prrt.CODE_VAL}">${prrt.CODE_NM}</option>
                        </c:forEach>
                    </select>
                </li>
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

                <li>
                    <label>처리상태</label>
                    <select id="PRCS_STTUS" name="PRCS_STTUS" class="input" style="width: 25%;" onkeydown="fnCheckEnter(event);" >
                        <option value="">= 전체 =</option>
                        <c:forEach items="${prcsList}" var="prcs">
                            <option value="${prcs.CODE_VAL}">${prcs.CODE_NM}</option>
                        </c:forEach>
                    </select>
                    <label>파손유형</label>
                    <select id="DMG_TYPE" name="DMG_TYPE" class="input" style="width: 25%;" onkeydown="fnCheckEnter(event);" >
                        <option value="">= 전체 =</option>
                        <c:forEach items="${dmgtList}" var="dmgt">
                            <option value="${dmgt.CODE_VAL}">${dmgt.CODE_NM}</option>
                        </c:forEach>
                    </select>
                </li>

                <li class="wid100">
                    <label>신고기간</label>
                    <span class="calendar">
                        <input type="text" id="STTEMNT_DT_START" name="STTEMNT_DT_START" style="width:70px; font-size: 10px;" onkeydown="fnCheckEnter(event);" />
                    </span> ~
                    <span class="calendar">
                        <input type="text" id="STTEMNT_DT_END" name="STTEMNT_DT_END" style="width:70px; font-size: 10px;" onkeydown="fnCheckEnter(event);" />
                    </span>
                </li>

                <li class="wid100">
                    <label>보수기간</label>
                    <span class="calendar">
                        <input type="text" id="RPAIR_DT_START" name="RPAIR_DT_START" style="width:70px; font-size: 10px;" onkeydown="fnCheckEnter(event);" />
                    </span> ~
                    <span class="calendar">
                        <input type="text" id="RPAIR_DT_END" name="RPAIR_DT_END" style="width:70px; font-size: 10px;" onkeydown="fnCheckEnter(event);" />
                    </span>
                </li>

                <li class="wid100" style="margin-top: 10px;">
                    <!-- <a href="#" id="btnSch" class="schbtn dpb">검색</a> -->
                    <a href="#" id="btnSch" onclick='fn_btnSchClick();' class="schbtn dpb">검색</a>

                </li>

                <li class="wid100" style="margin-top: 10px;">
                    <span style="font-size: 12px;color: #676a6d;font-weight: bold;">19년 00월 부터 국토부앱, 시·군관리 신고 조회가능</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="fr listbx">
        <h3>
            <span id="" class="" style="letter-spacing: -1.5px;">포트홀 신고관리
                <span id="" class="sttemnth3">
	                ( <span id="startDt" class=""></span> ~ <span id="endDt" class=""></span> )&nbsp;&nbsp;&nbsp;
		                &nbsp;신고 : <span id="prcs1" class="" style="margin-right:10px;"></span>
		                &nbsp;접수 : <span id="prcs2" class="" style="margin-right:10px;"></span>
		                &nbsp;처리완료 : <span id="prcs3" class="" style="margin-right:10px;"></span>
		                &nbsp;보수예정 : <span id="prcs4" class="" style="margin-right:10px;"></span>
		                &nbsp;재배정요청 : <span id="prcs5" class="" style="margin-right:10px;"></span>
		                &nbsp;기타 : <span id="prcs6" class="" style="margin-right:10px;"></span>
		                &nbsp;좌표오류 : <span id="prcs7" class="" style="margin-right:10px;"></span>
		                &nbsp;취하완료 : <span id="prcs8" class="" style="margin-right:10px;"></span>
                </span>
            </span>
        </h3>
        <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
                <table id="gridArea"></table>
                <div id="gridPager"></div>
            </div>
            <div class="mt10 tc">
                <a href="#" onclick="fn_sttemntExcel(1);" class="schbtn" style="float: left;">관할기관별 전(前)일 신고자료 다운로드</a>
                <div class="fr">
                    <a href="#" onclick="fn_sttemntExcel();" class="schbtn" style="float: right">엑셀다운로드</a>
                </div>
            </div>
        </div>
    </div>
</div>

</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">

var pthRgNoLayerCount = 0;
var mkrTimeout;
var startDt ;
var endDt ;
var arr_pth_rg_no = [];

//페이지 로딩 초기 설정
$( document ).ready(function() {
	var a = [];

    // 2018. 02. 05. JOY. 검색조건 부서 setting
    var usrGrp = "${sessionScope.userinfo.REQ_USER_GRP}";
    //var usrDept = "${usrDeptCode}" ;

    //YYK. 부서셋팅을 goodmap 에서 함
    /* if ( usrGrp != 'ROLE_ADMIN' ) {
        // 관리자가 아닌 경우엔 부서 setting
        $("#DEPT_CODE").val(usrDept);
    } */

    // top-tool 의 값을 신고관리와 연동시킴
    $("#DEPT_CODE").val(parent.$('.top-tool #DEPT_CODE').val());
    $("#PTH_RG_NO").val(parent.$('.top-tool #PTH_RG_NO').val());
    $("#BSNM_NM").val(parent.$('.top-tool #BSNM_NM').val());
    $("#VHCLE_NO").val(parent.$('.top-tool #VHCLE_NO').val());
    $("#PRCS_STTUS").val(parent.$('.top-tool #PRCS_STTUS').val());
    $("#DMG_TYPE").val(parent.$('.top-tool #DMG_TYPE').val());
    $("#RPAIR_DT_START").val(parent.$('.top-tool #RPAIR_DT_START').val());
    $("#RPAIR_DT_END").val(parent.$('.top-tool #RPAIR_DT_END').val());
    $("#pthmode").val(parent.$('.top-tool #pthmode').val());

	var total_cnt = 0;

    // 2018. 01. 04. JOY. 경기도로 모니터링단 시스템
    // 필수액션 : 서브메뉴 버튼영역 hide =============== //
    parent.$(".tab_wrap").css("margin-left", "0px");
    // ================================================= //
    // 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('STTEMNT_DT_START', 'STTEMNT_DT_END', 30, 185);
    COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_DT_START', 'RPAIR_DT_END', 30, 185);

    // 2018.03.16 YYK 상단 신고현황 탭의 날짜와 연동
    $('#STTEMNT_DT_START').val(parent.$('.top-tool #STTEMNT_DT_START').val());
    $('#STTEMNT_DT_END').val(parent.$('.top-tool #STTEMNT_DT_END').val());


    var postData = $("#frm").cmSerializeObject();
    postData["PTH_RG_NO_LAYER"] = [];

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({

        url: '<c:url value="/"/>'+'api/pothole/sttemnt/selectSttemntList.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: postData
        ,ignoreCase: true
        // 접수경로 : pthmode 추가 (스마트카드, 국토부, 시군관리) (2019년 고도화 사업)
        ,colNames:["DEPT_CODE","등록번호","차량번호","신고자", "좌표X", "좌표Y", "TM_X", "TM_Y", "도로명", "지번주소", "신고일시", "보수일자", "처리일시", "처리기간", "접수경로", "관할기관", "파손유형", "처리상태", "비고", "위치", "헤딩", "중복여부", "접수경로구분"]
        ,colModel:[
            {name:'DEPT_CODE'           , index:'DEPT_CODE'         , hidden: true}
            ,{name:'PTH_RG_NO'          , index:'PTH_RG_NO'         , align:'center'    , width:35  , sortable:true}
            ,{name:'VHCLE_NO'           , index:'VHCLE_NO'          , align:'center'    , width:35  , sortable:true}
            ,{name:'BSNM_NM'            , index:'BSNM_NM'           , align:'center'    , width:25  , sortable:true}
            ,{name:'CORTN_X'            , index:'CORTN_X'           , align:'center'    , width:25  , sortable:true}
            ,{name:'CORTN_Y'            , index:'CORTN_Y'           , align:'center'    , width:25  , sortable:true}
            ,{name:'TM_X'               , index:'TM_X'              , align:'center'    , hidden: true}
            ,{name:'TM_Y'               , index:'TM_Y'              , align:'center'    , hidden: true}
            ,{name:'RN_ADRES'           , index:'RN_ADRES'          , align:'center'    , width:20  , sortable:true}
            ,{name:'LNM_ADRES'          , index:'LNM_ADRES'         , align:'center'    , width:80  , sortable:true}
            ,{name:'STTEMNT_DT'         , index:'STTEMNT_DT'        , align:'center'    , width:50  , sortable:true}
            ,{name:'RPAIR_DT'           , index:'RPAIR_DT'          , align:'center'    , width:35  , sortable:true}
            ,{name:'PROCESS_DT'         , index:'PROCESS_DT'        , align:'center'    , width:50  , sortable:true, formatter: fnProcessDt}
            ,{name:'DAYCNT'             , index:'DAYCNT'            , align:'center'    , width:30  , sortable:true, formatter: fnDayCnt}
            ,{name:'RCPT_ROUTE'         , index:'RCPT_ROUTE'        , align:'center'    , width:35  , sortable:true}
            ,{name:'LOWEST_DEPT_NM'     , index:'LOWEST_DEPT_NM'    , align:'center'    , width:35  , sortable:true}
            ,{name:'DMG_TYPE_NM'        , index:'DMG_TYPE_NM'       , align:'center'    , width:35  , sortable:true}
            ,{name:'PRCS_STTUS_NM'      , index:'PRCS_STTUS_NM'     , align:'center'    , width:30  , sortable:true}
            ,{name:'RM'                 , index:'RM'                , align:'center'    , width:25  , sortable:false}
            ,{name:'btn_loc'            , index:'btn_loc'           , align:'center'    , width:15  , sortable:false, formatter: fn_create_btn}
            ,{name:'HEADG'              , index:'HEADG'             , hidden: true}
            ,{name:'DPLCT_STTEMNT_AT'   , index:'DPLCT_STTEMNT_AT'  , hidden: true}
            ,{name:'pthmode'            , index:'pthmode'           , hidden: true}
        ]
        ,async : false
        ,sortname: 'PTH_RG_NO'
        ,sortorder: "desc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,ondblClickRow: function(rowId) {
            fnView(rowId);

        }
        ,onSelectRow: function(rowId) {
            // 클릭 처리
            if( rowId != null ) {
                var rowData =$("#gridArea").getRowData(rowId);
            }
        }
        ,loadBeforeSend:function(tsObj, ajaxParam, settings){
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
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 190);


// ===================== fn_search() 전 조건 변경 =================================

    var pthRgNo = [];

    // 신고정보공간검색(팝업) 조회일 경우
    if ( '${pthRgNo}' != '[]'){
        pthRgNo = ${pthRgNo};
        pthRgNoLayerCount = pthRgNo.length;
        $('#STTEMNT_DT_START').val(parent.$('.top-tool #STTEMNT_DT_START').val());
        $('#STTEMNT_DT_END').val(parent.$('.top-tool #STTEMNT_DT_END').val());
    }

    // 민자도로사업자 신고정보조회 -> 상세조회 클릭 : 관할기관 dept_code(7000300) + 기간변경
    if ('${prv_deptCode}' == 7000300 ){

        // 관할기관을 '민자도로사업자'로 셋팅
        $('#DEPT_CODE').val('${prv_deptCode}');

        // 처리상태를 '신고'로 셋팅
        //$('#PRCS_STTUS').val('PRCS0001');

        // 신고기간을 '지도- 포트홀신고현황'의 날짜로 셋팅
        $('#STTEMNT_DT_START').val(parent.$('.top-tool #STTEMNT_DT_START').val());
        $('#STTEMNT_DT_END').val(parent.$('.top-tool #STTEMNT_DT_END').val());
    }


 // ===================== fn_search() =================================
    // YYK 공간검색으로 접근 시 sflag 넘김
    if ('${sflag}' == 'Y') {
    	fn_search(pthRgNo, 'Y');
    }
    else{
    	fn_search(pthRgNo);
    }
});

//$("#btnSch").click(function() {

function fn_btnSchClick(){
    // 지도 - 신고현황 검색조건 setting
    parent.$("#PTH_RG_NO").val( $("#PTH_RG_NO").val() );
    parent.$("#BSNM_NM").val( $("#BSNM_NM").val() );
    parent.$("#VHCLE_NO").val( $("#VHCLE_NO").val() );
    parent.$("#pthmode").val( $("#pthmode").val() );
    parent.$("#DEPT_CODE").val( $("#DEPT_CODE").val() );
    parent.$("#PRCS_STTUS").val( $("#PRCS_STTUS").val() );
    parent.$("#DMG_TYPE").val( $("#DMG_TYPE").val() );
    parent.$("#RPAIR_DT_START").val( $("#RPAIR_DT_START").val() );
    parent.$("#RPAIR_DT_END").val( $("#RPAIR_DT_END").val() );
    parent.$("#pthmode").val( $("#pthmode").val() );

    fn_search();
    parent.fnSelectLayer(); // 지도 - 신고현황 조회
};
//});


//검색 처리
function fn_search(pthRgNo, sflag) {

	var postData = $("#frm").cmSerializeObject();
	postData["USE_AT"] = "Y";


    if (pthRgNo == '') {
    	postData["PTH_RG_NO_LAYER"] = [];
    	$("#PTH_RG_NO_LAYER").val(pthRgNo)
    }
    else if (pthRgNo == undefined){
    	postData["PTH_RG_NO_LAYER"] = [null];
    	$("#PTH_RG_NO_LAYER").val(pthRgNo)
    }
    else if (sflag == 'Y'){
        postData["PTH_RG_NO_LAYER"] = pthRgNo;
        $("#PTH_RG_NO_LAYER").val(pthRgNo)
    }
    else {
    	postData["PTH_RG_NO_LAYER"] = ${pthRgNo};
    	$("#PTH_RG_NO_LAYER").val(pthRgNo)
    }

    // YYK. 2018.03.23. 크기 확인
    if (postData["PTH_RG_NO_LAYER"].length > 500){
    	alert('값이 너무 큽니다.');
    	return;
    }

    /* if( postData["PTH_RG_NO_LAYER"].length == 0 || postData["PTH_RG_NO_LAYER"][0] == null  ){
        postData["PTH_RG_NO"] = searchPthRgNo(postData["PTH_RG_NO"]);
    } */
    // PTH_RG_NO 값 변환
    postData["PTH_RG_NO"] = searchPthRgNo(postData["PTH_RG_NO"]);


    $("#gridArea").jqGrid("setGridParam",{

        datatype: "json"
        , ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        , contentType: "application/json"
        , page: 1
        , postData: postData
        , mtype: "POST"
        ,traditional: true
        , loadComplete: function(data) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

            // jqGrid Table 사이즈 조절
            $("#div_grid").bind("resize", function() {

                $("#gridArea").setGridWidth(100, false);
                $("#gridArea").setGridWidth($("#div_grid").width(), false);

            }).trigger("resize");

            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager_left").css('width', '');

            // recode 수 (엑셀 저장 자료 없을 경우 경고 출력용)
            total_cnt = data.records;

            // 타이틀 제목 설정
            $('#startDt').text($('#STTEMNT_DT_START').val());
            $('#endDt').text($('#STTEMNT_DT_END').val());

            if ( sflag == 'Y'){
            	/* $('#prcs1').text( parent.$('#PRCS_STTUS1').text() );
            	$('#prcs2').text( parent.$('#PRCS_STTUS2').text() );
            	$('#prcs3').text( parent.$('#PRCS_STTUS3').text() );
            	$('#prcs4').text( parent.$('#PRCS_STTUS4').text() );
	            $('#prcs5').text( parent.$('#PRCS_STTUS5').text() );
	            $('#prcs6').text( parent.$('#PRCS_STTUS6').text() );
	            $('#prcs7').text( parent.$('#PRCS_STTUS7').text() ); */
            	$('#prcs1').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS1"]) + " 건  ") ;
                $('#prcs2').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS2"]) + " 건  ") ;
                $('#prcs3').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS3"]) + " 건  ") ;
                $('#prcs4').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS4"]) + " 건  ") ;
                $('#prcs5').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS5"]) + " 건  ") ;
                $('#prcs6').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS6"]) + " 건  ") ;
                $('#prcs7').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS7"]) + " 건  ") ;
                $('#prcs8').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS8"]) + " 건  ") ;

            }
            else{
                $('#prcs1').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS1"]) + " 건  ") ;
                $('#prcs2').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS2"]) + " 건  ") ;
                $('#prcs3').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS3"]) + " 건  ") ;
                $('#prcs4').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS4"]) + " 건  ") ;
                $('#prcs5').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS5"]) + " 건  ") ;
                $('#prcs6').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS6"]) + " 건  ") ;
                $('#prcs7').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS7"]) + " 건  ") ;
                $('#prcs8').text( COMMON_UTIL.cmAddComma( data.prcs[0]["PRCS_STTUS8"]) + " 건  ") ;
            }

            $("#gridArea tbody tr td").attr("title", "더블클릭하면 신고 상세정보를 확인 할 수 있습니다.");
            // 초기화하지 않으면 이전값이 dummy로 남아 검색한 결과보다 더 많은 리스트가 표출될 수 있다.\
            //this.p.postData.PTH_RG_NO_LAYER = [];
        }
        , loadError: function (jqXHR, textStatus, errorThrown) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, 1);
            $("#gridPager_left").find("div").html("총 <font color='#f42200'>0</font> 건 (1-1)");
        }
    }).trigger("reloadGrid");

}

// 상세조회
function fnView( rowId ) {
    if ( rowId ) {
        var rowData = $("#gridArea").getRowData(rowId);

        if ( rowData.PTH_RG_NO ){

            // YYK. 마커 띄우기위해 윈도우 팝업 close()
	        for( var i=0; i<parent.$.window.getAll().length; i++ ) {
	            if( parent.$.window.getAll()[i].getTitle() == '포트홀 신고정보 상세조회' ){
                    var exist_id = parent.$.window.getAll()[i].getWindowId();
                    parent.$.window.getWindow(exist_id).close()
	            }
	        }

        	parent.bottomClose();

        	// 좌표오류인 경우 extent 조절 x
        	if ( rowData.PRCS_STTUS_NM != "좌표오류") {

	            var layer = parent.gMap.getLayerByName("GOverlapLayer");

	            var layerStyle = new OpenLayers.Style(null);
	            var ruleArr = [MAP.overlapStyle[0]];

	            layerStyle.addRules(ruleArr);
	            layer.options.styleMap.styles.default = layerStyle;

	            layer.removeAllFeatures();

	            layer.addFeatures(
	                new OpenLayers.Feature.Vector(
	                    new OpenLayers.Geometry.Point( rowData.TM_X, rowData.TM_Y )
	                        , {
	                            image      : 'icon_marker_bk_ggam.gif'
	                            , angle    : rowData.HEADG * 1 - 90
	                            , pthRgNo  : cvtPthRgNo(rowData.PTH_RG_NO)
	                            , pthmode  : rowData.pthmode
	                            , ggId     : rowData.GG_ID
	                            , data     : rowData.DPLCT_STTEMNT_AT
	                        }
	                    )
	            );

	            parent.gMap.zoomToExtent(layer.getDataExtent());
	            layer.setVisibility(true);
	            layer.redraw();

        	}

        	//상세조회 파라미터 - 접수경로 구분값 추가
        	var urlParam = "&pthmode=" + rowData.pthmode;

        	COMMON_UTIL.cmWindowOpen('포트홀 신고정보 상세조회', "<c:url value='/pothole/sttemnt/selectSttemntView.do'/>?PTH_RG_NO=" + rowData.PTH_RG_NO + urlParam , 600, 1200, false, $("#wnd_id").val(), 'left');

        }
    }
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {

    // 2019년 고도화 사업 - 취하완료 추가 (위치이동 x)
	if (( rowObject.PRCS_STTUS == "PRCS0007" ) ||
		( rowObject.PRCS_STTUS == "PRCS0008" )){

        return "";

    } else {

        return "<a href='#' onclick=\"fn_select_pthSttmnt('" + rowObject.PTH_RG_NO + "', '" + rowObject.PRCS_STTUS + "', '" + rowObject.HEADG + "', '" + rowObject.TM_X + "', '" + rowObject.TM_Y + "', '" + rowObject.DPLCT_STTEMNT_AT + "', '" + rowObject.pthmode + "', '" + rowObject.DMG_TYPE + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";

    }
}

function fnProcessDt(cellValue, options, rowObject) {

    if ( rowObject.PRCS_STTUS == "PRCS0003" /* || ( rowObject.RPAIR_DT != "" && rowObject.RPAIR_DT != null && rowObject.RPAIR_DT != undefined ) */ ) {

        return rowObject.PROCESS_DT;

    } else {

        return "";
    }

}

// 처리기간 일수 표시
function fnDayCnt(cellValue, options, rowObject) {

    if ( rowObject.PRCS_STTUS == "PRCS0003" /* || ( rowObject.RPAIR_DT != "" && rowObject.RPAIR_DT != null && rowObject.RPAIR_DT != undefined ) */ ) {

	    if ( rowObject.DAYCNT != null && rowObject.DAYCNT != "" && rowObject.DAYCNT != undefined ) {

	        return rowObject.DAYCNT + "일";

	    } else {

	        return "";

	    }

    }  else {

        return "";

    }

}

//엑셀저장
function fn_sttemntExcel(num){


	var pth_rg_no = searchPthRgNo($("#PTH_RG_NO").val());


	// 전일신고자료 다운로드
    if ( num != undefined ) {
        //db 날짜가 yy-mm-dd hh:mm:ss 일 경우
        startDt = new Date(new Date()-1 * 1000 * 60 * 60 * 24).format("yyyy-MM-dd");
        endDt = new Date(new Date()-1 * 1000 * 60 * 60 * 24).format("yyyy-MM-dd");

        var postData = $("#frm").cmSerializeObject();
        postData["STTEMNT_DT_START"] = startDt ;
        postData["STTEMNT_DT_END"] = endDt ;
        postData["PTH_RG_NO_LAYER"] = ${pthRgNo};  // PHT_RG_NO 배열값

        // PTH_RG_NO 값 변환
        postData["PTH_RG_NO"] = searchPthRgNo(postData["PTH_RG_NO"]);

        $.ajax({
            url : contextPath + 'api/sttemnt/sttemntViewExcelCnt.do'
            , type: 'post'
            , dataType : 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(res) {
                if (res.total_count > 0){
                	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
                		$('#STTEMNT_DT_START').attr("disabled", "disabled");
                        $('#STTEMNT_DT_END').attr("disabled", "disabled");
                        $('#PTH_RG_NO').attr("disabled", "disabled");
                        //COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/sttemnt/sttemntListExcel.do'/>?sidx=" + $("#sidxexcel").val() + "&sord=" + $("#sordexcel").val(), "");
                        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/sttemnt/sttemntListExcel.do'/>?STTEMNT_DT_START="+startDt+"&STTEMNT_DT_END="+endDt+"&PTH_RG_NO="+pth_rg_no+"&sidx=" + $("#sidxexcel").val() + "&sord=" + $("#sordexcel").val(), "");
                        $('#STTEMNT_DT_START').removeAttr("disabled");
                        $('#STTEMNT_DT_END').removeAttr("disabled");
                        $('#PTH_RG_NO').removeAttr("disabled");
                    }
                }
                else {
                    alert("데이터가 없습니다.");
                }
            }
            , error : function() {
                return;
            }
        })

    } else { // 일반 엑셀 다운로드
    	if (total_cnt > 0){
    		if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
    		    $('#PTH_RG_NO').attr("disabled", "disabled");
    		    COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/sttemnt/sttemntListExcel.do'/>?PTH_RG_NO="+pth_rg_no+ "&sidx=" + $("#sidxexcel").val() + "&sord=" + $("#sordexcel").val(), "");
    	        $('#PTH_RG_NO').removeAttr("disabled");
    		}
        }
    	else {
            alert("데이터가 없습니다.");
        }
    }
}

//위치조회
function fn_select_pthSttmnt(pthRgNo, prcsSttus, headg, tmX, tmY, dplctSttemntAt, pthmode, dmtType){

    mkrTimeout = null;

    var pthRgNo = pthRgNo.substring(0, 8) + pad(pthRgNo.substring(9), 6);

	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();

	parent.gMap.getLayerByName('GAttrLayer').removeAllFeatures();

	// 2019년 고도화 사업 - 취하완료 추가 (위치이동 x)
	if (( prcsSttus != "PRCS0007" ) &&
	    ( prcsSttus != "PRCS0008" )){


		//2019년 고도화 사업 - 위치 이동 시 해당 신고현황 마커 off --> on
        if ( parent.$("#status").hasClass("on") ) {

        	//포트홀 신고현황
        	if ( !parent.$(".sttCircle." + prcsSttus).hasClass("bgchk") ) {

	        	parent.$(".sttCircle." + prcsSttus).addClass("bgchk");

	        }
	        parent.fnSetStyle("GStatusLayer");
        } else {

	        //파손유형별 신고현황
	        if(dmtType != ""){
		        if ( !parent.$(".dmgCircle." + dmtType).hasClass("bgchk") ) {

		        	parent.$(".dmgCircle." + dmtType).addClass("bgchk");

		        }
		        parent.fnSetStyle("GTypeLayer");
	        }
        }

		var layer = parent.gMap.getLayerByName("GOverlapLayer");

	    var layerStyle = new OpenLayers.Style(null);
	    var ruleArr = [MAP.overlapStyle[0]];

	    layerStyle.addRules(ruleArr);
	    layer.options.styleMap.styles.default = layerStyle;

	    layer.removeAllFeatures();

	    layer.addFeatures(
	        new OpenLayers.Feature.Vector(
	            new OpenLayers.Geometry.Point( tmX, tmY )
	                , {
	            	    image      : 'icon_marker_bk_ggam.gif'
	            	    //image      : 'icon_marker_bk.png'
	            	    , angle    : headg * 1 - 90
	                    , pthRgNo  : pthRgNo
	                    , pthmode  : pthmode
	                    , data     : dplctSttemntAt
	                }
	            )
	    );

	    parent.gMap.zoomToExtent(layer.getDataExtent());

	    layer.setVisibility(true);
	    layer.redraw();

	    mkrTimeout = setTimeout(function() {
	        layer.setVisibility(false);
	    }, 10 * 1000);

	}

}

    // 2018.03.16 YYK 상단 신고현황 탭의 날짜와 연동 (동적변경)
    $('#STTEMNT_DT_START, #STTEMNT_DT_END').on('keyup change', function(e){
    	parent.$('.top-tool #STTEMNT_DT_START').val($('#STTEMNT_DT_START').val());
    	parent.$('.top-tool #STTEMNT_DT_END').val($('#STTEMNT_DT_END').val());
    });

    // ==================== 검색조건 신고현황과 연동 =========================== //
    $('#RPAIR_DT_START, #RPAIR_DT_END').on('keyup change', function(e){
        parent.$('.top-tool #RPAIR_DT_START').val($('#RPAIR_DT_START').val());
        parent.$('.top-tool #RPAIR_DT_END').val($('#RPAIR_DT_END').val());
    });

    $('#PTH_RG_NO').on('change', function(e){
        parent.$('.top-tool #PTH_RG_NO').val($('#PTH_RG_NO').val());
    });

    $('#BSNM_NM').on('change', function(e){
        parent.$('.top-tool #BSNM_NM').val($('#BSNM_NM').val());
    });

    $('#VHCLE_NO').on('change', function(e){
        parent.$('.top-tool #VHCLE_NO').val($('#VHCLE_NO').val());
    });

    $('#DEPT_CODE').on('change', function(e){
        parent.$('.top-tool #DEPT_CODE').val($('#DEPT_CODE').val());
    });

    $('#PRCS_STTUS').on('change', function(e){
        parent.$('.top-tool #PRCS_STTUS').val($('#PRCS_STTUS').val());
    });

    $('#DMG_TYPE').on('change', function(e){
        parent.$('.top-tool #DMG_TYPE').val($('#DMG_TYPE').val());
    });

    $('#pthmode').on('change', function(e){
        parent.$('.top-tool #pthmode').val($('#pthmode').val());

        if($('#pthmode').val() == "") {

	        parent.$(".prrtCircle.T").addClass("bgchk");
	        parent.$(".prrtCircle.M").addClass("bgchk");
	        parent.$(".prrtCircle.C").addClass("bgchk");

        } else if($('#pthmode').val() == "T") {

        	parent.$(".prrtCircle.T").addClass("bgchk");
            parent.$(".prrtCircle.M").removeClass("bgchk");
            parent.$(".prrtCircle.C").removeClass("bgchk");

        } else if($('#pthmode').val() == "M") {

            parent.$(".prrtCircle.T").removeClass("bgchk");
            parent.$(".prrtCircle.M").addClass("bgchk");
            parent.$(".prrtCircle.C").removeClass("bgchk");

        } else if($('#pthmode').val() == "C") {

            parent.$(".prrtCircle.T").removeClass("bgchk");
            parent.$(".prrtCircle.M").removeClass("bgchk");
            parent.$(".prrtCircle.C").addClass("bgchk");

        }
    });


function fnCheckEnter(event) {

    if ( event.keyCode == 13 ) {

        // 지도 - 신고현황 검색조건 setting
        parent.$("#PTH_RG_NO").val( $("#PTH_RG_NO").val() );
        parent.$("#BSNM_NM").val( $("#BSNM_NM").val() );
        parent.$("#VHCLE_NO").val( $("#VHCLE_NO").val() );
        parent.$("#DEPT_CODE").val( $("#DEPT_CODE").val() );
        parent.$("#PRCS_STTUS").val( $("#PRCS_STTUS").val() );
        parent.$("#DMG_TYPE").val( $("#DMG_TYPE").val() );
        parent.$("#RPAIR_DT_START").val( $("#RPAIR_DT_START").val() );
        parent.$("#RPAIR_DT_END").val( $("#RPAIR_DT_END").val() );
        parent.$("#pthmode").val( $("#pthmode").val() );

        fn_search();
        parent.fnSelectLayer(); // 지도 - 신고현황 조회
    }
}

</script>
</body>
</html>