<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidx" name="sidx" value=""/>
<input type="hidden" id="sord" name="sord" value=""/>
<!-- 필수 파라메터(END) -->
<div class="tabcont">
	<c:if test="${empty mummSctnSrvyDtaVO.CELL_ID }">
	<div class="fl bgsch">
    	<h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	           <li class="wid100">
                    <label>관리기관</label>
                    <select id="DEPT_CODE" name="DEPT_CODE" alt="관리기관" style="width: 74.5%;" class="input">
                        <option value="">===== 전체 =====</option>
                        <c:forEach items="${ deptList }" var="dept">
                            <option value="${ dept.DEPT_CODE }">${ dept.LOWEST_DEPT_NM }</option>
                        </c:forEach>
                    </select>
                    <label>조사년도</label>
                    <select id="SRVY_YEAR" name="SRVY_YEAR" alt="조사년도" style="width: 74.5%;" class="input">
                        <option value="">===== 전체 =====</option>
                        <c:forEach items="${ srvyYearList }" var="srvyYear">
                            <option value="${ srvyYear.SRVY_YEAR }">${ srvyYear.SRVY_YEAR }</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <label>도로등급</label>
                    <select id="ROAD_GRAD" name="ROAD_GRAD" alt="도로등급" onchange="fn_change_roadNo();" style="width:25%;" class="input">
                        <option value="">= 전체 =</option>
                        <c:forEach items="${ roadGradList }" var="roadGrad">
                            <option value="${ roadGrad.CODE_VAL }">${ roadGrad.CODE_NM }</option>
                        </c:forEach>
                    </select>

                    <label>노선번호</label>
                    <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" style="width:25%;" class="input">
                        <option value="">= 전체 =</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>노선명</label>
                    <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width:194px;" class="MX_80 CS_50 input" />
                </li>
                <li>
                	<label>관리도로</label>
                	<select id="MNG_RD_CD" name="MNG_RD_CD" title="관리도로" style="width:110px;" class="input">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${mngRdList }" var="mngRd">
		        			<option value="${mngRd.CODE_VAL}">${mngRd.CODE_NM}</option>
		        		</c:forEach>
	                </select></li>
                <li>
                    <label>행선</label>
                    <select id="DIRECT_CODE" name="DIRECT_CODE" alt="행선" style="width:70px;" class="input">
                        <option value="">= 전체 =</option>
                        <option value="S">상행</option>
                        <option value="E">하행</option>
                    </select>

                    <label>차로</label>
                    <input type="number" name="TRACK" id="TRACK" value="" style="width:57px;" onkeydown="fnCheckNumber(this);" maxLength="1" class="MX_80 CS_50 DT_INT input" />
                </li>
                <li class="wid100">
                    <label>시점(m)</label>
                    <input type="text" name="STRTPT" id="STRTPT" value="" style="width:62px;" onkeydown="fnCheckNumber(this);" maxLength="5" class="MX_80 CS_50 DT_INT input" />

                    <label>~종점(m)</label>
                    <input type="text" name="ENDPT" id="ENDPT" value="" style="width:57px;" onkeydown="fnCheckNumber(this);" maxLength="5" class="MX_80 CS_50 DT_INT input" />
                </li>
                <li class="wid100">
                    <label>주 파손</label>
                    <select id="CNTL_DFECT" name="CNTL_DFECT" alt="주파손" style="width:74.5%;" class="input">
                        <option value="">===== 전체=====</option>
                        <option value="DFCT0001">거북등균열</option>
                        <option value="DFCT0002">선형균열</option>
                        <option value="DFCT0004">패칭</option>
                        <option value="DFCT0005">포트홀</option>
                        <option value="DFCT0006">소성변형</option>
                        <option value="DFCT0007">종단평탄성</option>
                        <option value="DFCT0008">블럭균열</option>
                        <option value="DFCT0009">복합파손</option>
                    </select>
                </li>
                <li class="wid100">
                    <label></label>
                    <input type="text" name="MINGPCI" id="MINGPCI" value="" style="width:59px;" onkeydown="fnCheckNumberGPCI(this);" maxLength="2" class="MX_80 CS_50 DT_INT input" />
                    <label> ≤ GPCI ≤ </label>
                    <input type="text" name="MAXGPCI" id="MAXGPCI" value="" style="width:59px;" onkeydown="fnCheckNumberGPCI(this);" maxLength="2" class="MX_80 CS_50 DT_INT input" />
                </li>
                <li class="wid100 mt10">
                    <a href="#" class="schbtn dpb" onclick="javascript: fn_search();">검색</a>
                </li>


	        </ul>
	    </div>
	</div>
	</c:if>
	<c:if test="${empty mummSctnSrvyDtaVO.CELL_ID }">
	<div class="fr listbx">
	</c:if>
	<c:if test="${not empty mummSctnSrvyDtaVO.CELL_ID }">
	<div class="" style="position: absolute; left: 0px; width: 100%;">
	</c:if>
	    <h3>포장상태 평가정보 조회</h3>
	    <p class="location">
	        <span>포장상태 평가</span>
	        <strong>포장상태 평가정보 조회</strong>
	    </p>

	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:190px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
			<div class="tc" style="margin-top: 58px">
			<div class="fr">

            <a href="#" class="schbtn" onclick="fnExcel();">엑셀저장</a>
			</div>
			</div>
        </div>

  </div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer" charset="utf-8">
// 페이지 로딩 초기 설정/*

var param = "";

// 뒤로가기로 페이지 접근 시 검색조건
var DEPT_CODE       = "${mummSctnSrvyDtaVO.DEPT_CODE}";
var ROAD_GRAD       = "${mummSctnSrvyDtaVO.ROAD_GRAD}";
var ROAD_NO         = "${mummSctnSrvyDtaVO.ROAD_NO}";
var DIRECT_CODE     = "${mummSctnSrvyDtaVO.DIRECT_CODE}";
var TRACK           = "${mummSctnSrvyDtaVO.TRACK}";
var STRTPT          = "${mummSctnSrvyDtaVO.STRTPT}";
var ENDPT           = "${mummSctnSrvyDtaVO.ENDPT}";
var CNTL_DFECT      = "${mummSctnSrvyDtaVO.CNTL_DFECT}";
var MINGPCI         = "${mummSctnSrvyDtaVO.MINGPCI}";
var MAXGPCI         = "${mummSctnSrvyDtaVO.MAXGPCI}"
var SRVY_YEAR         = "${mummSctnSrvyDtaVO.SRVY_YEAR}"

$( document ).ready(function() {

    // 뒤로가기로 페이지 접근 시 검색조건 세팅
    if ( isNotNull(DEPT_CODE) )     { $("#DEPT_CODE").val(DEPT_CODE); }
    if ( isNotNull(DIRECT_CODE) )   { $("#DIRECT_CODE").val(DIRECT_CODE); }
    if ( isNotNull(TRACK) )         { $("#TRACK").val(TRACK); }
    if ( isNotNull(STRTPT) )        { $("#STRTPT").val(STRTPT); }
    if ( isNotNull(ENDPT) )         { $("#ENDPT").val(ENDPT); }
    if ( isNotNull(CNTL_DFECT) )    { $("#CNTL_DFECT").val(CNTL_DFECT); }
    if ( isNotNull(MINGPCI) )       { $("#MINGPCI").val(MINGPCI); }
    if ( isNotNull(MAXGPCI) )       { $("#MAXGPCI").val(MAXGPCI); }
    if ( isNotNull(SRVY_YEAR) )       { $("#SRVY_YEAR").val(SRVY_YEAR); }

    if ( isNotNull(ROAD_GRAD) ) {
        $("#ROAD_GRAD").val(ROAD_GRAD);
        fn_change_roadNo();
    }

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'srvy/api/srvyDtaEvlInfoDetailList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SRVY_NO", "SM_NO", "SRVY_YEAR", "OBJECT_ID", "CELL_ID", "조사년도", "관리기관", "도로등급", "노선번호", "노선명", "행선", "차로", "관리도로" ,"시점(km)", "종점(km)", "교통량" , "GPCI", "주 파손", "파손원인", "위치"
		           , "CNTL_DFECT", "CODE_NM", "AC_IDX", "LC_IDX", "BC_IDX", "PTCHG_IDX", "POTHOLE_IDX", "RD_IDX", "RCI", "DMG_CUZ_CLMT", "DMG_CUZ_VMTC", "DMG_CUZ_ETC" ]
	   	,colModel:[
			{name:'SRVY_NO',index:'SRVY_NO', hidden: true}
			,{name:'SM_NO',index:'SM_NO', hidden: true}
			,{name:'SRVY_YEAR',index:'SRVY_YEAR', hidden: true}
			,{name:'OBJECT_ID',index:'OBJECT_ID', hidden: true}
			,{name:'CELL_ID',index:'CELL_ID', hidden: true}
			,{name:'SRVY_DE',index:'SRVY_DE', align:'center', width:70, sortable: true}
			,{name:'DEPT_CODE',index:'DEPT_CODE', align:'center', width:80, sortable: true}
			,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:70, sortable: true}
			,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:60, sortable: true}
			,{name:'ROAD_NM',index:'ROAD_NM', align:'center', width:70, sortable: true}
			,{name:'DIRECT_CODE',index:'DIRECT_CODE', align:'center', width:50, sortable: true}
			,{name:'TRACK',index:'TRACK', align:'center', width:40, sortable: true}
			,{name:'MRG_RD_NM',index:'MRG_RD_NM', align:'center', width:40, sortable: true}
			,{name:'STRTPT',index:'STRTPT', align:'center', width:60, sortable:false, formatter: fnConvertKm}
			,{name:'ENDPT',index:'ENDPT', align:'center', width:60, sortable: true, formatter: fnConvertKm}
			,{name:'TRNSPORT_QY',index:'TRNSPORT_QY', align:'center', width:60, sortable: true, formatter: fnConvertKm}
			,{name:'GPCI',index:'GPCI', align:'center', width:50, sortable: true, formatter: fnFloat}
			,{name:'CR',index:'CR', align:'center', width:70, sortable: false, formatter: fnFormatter}
			,{name:'CUZ',index:'CUZ', align:'center', width:100, sortable: false, formatter: fnFormatter}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable: false, formatter: fnFormatter}

			,{name:'CNTL_DFECT',index:'CNTL_DFECT', hidden: true}
			,{name:'CODE_NM',index:'CODE_NM', hidden: true}
			,{name:'AC_IDX',index:'AC_IDX', hidden: true}
			,{name:'LC_IDX',index:'LC_IDX', hidden: true}
			,{name:'BC_IDX',index:'BC_IDX', hidden: true}
			,{name:'PTCHG_IDX',index:'PTCHG_IDX', hidden: true}
			,{name:'POTHOLE_IDX',index:'POTHOLE_IDX', hidden: true}
			,{name:'RD_IDX',index:'RD_IDX', hidden: true}
			,{name:'RCI',index:'RCI', hidden: true}
			,{name:'DMG_CUZ_CLMT',index:'DMG_CUZ_CLMT', hidden: true}
			,{name:'DMG_CUZ_VMTC',index:'DMG_CUZ_VMTC', hidden: true}
			,{name:'DMG_CUZ_ETC',index:'DMG_CUZ_ETC', hidden: true}
	   	]
		,async : false
	   	,sortname: 'STRTPT'
	    ,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "포장상태 평가대상 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리

		    var obj = $("#gridArea").getRowData(rowId);
		    /*  var row = {
			    OBJECT_ID : obj.OBJECT_ID,
			    CELL_ID : obj.CELL_ID,
			    AC_IDX : obj.AC_IDX,
			    LC_IDX : obj.LC_IDX,
			    BC_IDX : obj.BC_IDX,
			    PTCHG_IDX : obj.PTCHG_IDX,
			    POTHOLE_IDX : obj.POTHOLE_IDX,
			    RD_IDX : obj.RD_IDX,
	            RCI : obj.RCI,

	            GPCI : obj.GPCI,
	            CNTL_DFECT_DETAIL : obj.CNTL_DFECT,
	            CODE_NM : obj.CODE_NM,
	            DMG_CUZ_CLMT : obj.DMG_CUZ_CLMT,
	            DMG_CUZ_VMTC : obj.DMG_CUZ_VMTC,
	            DMG_CUZ_ETC : obj.DMG_CUZ_ETC
		    };
		    var str = "";
		    for ( var key in row ) {
		        str += key + "=" + row[key] + "&";
		    } */

		    param = param.replace("SRVY_YEAR", "SCH_SRVY_YEAR");

		    //fnSelectLoc(obj.CELL_ID);
		    parent.COMMON_UTIL.cmMenuUrlContent('srvy/selectSrvyDtaEvlInfoDetail.do?SM_NO=' + obj.SM_NO + '&SRVY_YEAR=' + obj.SRVY_YEAR + "&" + param, true);

		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
			}
		}
	   	,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridArea");
	   		 	$grid.jqGrid('setSelection', rowid, false);
	   			return false;
	   		}
	   	    return true;
	   	}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			$("#sidx").val(this.p.sortname);
	   			this.p.postData.sord = this.p.sortorder;
	   			$("#sord").val(this.p.sortorder);
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: true
		,shrinkToFit : false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 190);

	fn_search();

	$(window).resize(function () {
        fnSetGridWith();
    });

});

//검색 처리
function fn_search() {

    if ( !fnFormCheck($("#frm")) ) { return; }

    param = $("#frm").fnGetParameter();

	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {

	   	    fnSetGridWith();

	   	    COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

	   	}
	}).trigger("reloadGrid");
}

// jqgrid 하단 및 넓이 설정
function fnSetGridWith() {
    var grid = $("#gridArea");

    var gridWidth = window.innerWidth - 320;

    grid.setGridWidth(gridWidth);

    $(".ui-jqgrid-htable").css("width", gridWidth-18);
    $(".ui-jqgrid-btable").css("width", gridWidth-18);
    $(".ui-jqgrid-ftable").css("width", gridWidth-18);
}

function fnFormCheck(form) {

    var strtpt = $("#STRTPT").val();
    var endpt = $("#ENDPT").val();
    var mingpci = $("#MINGPCI").val();
    var maxgpci = $("#MAXGPCI").val();

    if ( strtpt != "" && endpt !="" && parseInt(strtpt) > parseInt(endpt) ) {
        alert("종점 값은 시점 값 보다 크게 입력해주시기 바랍니다.");
        return false;
    }

    if ( mingpci != "" && maxgpci !="" && parseInt(mingpci) > parseInt(maxgpci) ) {
        alert("최대 GPCI 값은 최소 GPCI 값 보다 크게 입력해주시기 바랍니다.");
        return false;
    }

    if ( COMMON_LANG.trimdata( form.find('#TRACK').val() ).length > 1 ) {
        alert("차로 값은 최대 1자까지 입력할 수 있습니다.");
        return false;
    }

    if ( COMMON_LANG.trimdata( form.find('#STRTPT').val() ).length > 5 ) {
        alert("시점 값은 최대 5자까지 입력할 수 있습니다.");
        return false;
    }

    if ( COMMON_LANG.trimdata( form.find('#ENDPT').val() ).length > 5 ) {
        alert("종점 값은 최대 5자까지 입력할 수 있습니다.");
        return false;
    }

    if ( COMMON_LANG.trimdata( form.find('#MINGPCI').val() ).length > 2 ) {
        alert("최소 GPCI 값은 최대 2자까지 입력할 수 있습니다.");
        return false;
    }

    if ( COMMON_LANG.trimdata( form.find('#MAXGPCI').val() ).length > 2 ) {
        alert("최대 GPCI 값은 최대 2자까지 입력할 수 있습니다.");
        return false;
    }

    return true;

}

function fnCheckNumberGPCI(obj){
    $(obj).keyup(function() {

        var value = $(this).val().replace(/[^0-9]/g, "");
        $(this).val(value);

        if ( value > 10 ) {
            alert("GPCI 값은 10 이하 값만 입력 가능합니다.");
            $(this).val("");
            return;
        }
    });
}

function fnCheckNumber(obj){

    $(obj).keyup(function() {
        var value = $(this).val().replace(/[^0-9]/g, "");
        $(this).val(value);
    });
}


// 도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo(val) {
    var roadGrad = $("#ROAD_GRAD").val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>전체</option>";

            for(var i=0; i < data.length; i++){
                txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
            }

            var no = $("#ROAD_NO").val();
            var name = $("#ROAD_NAME").val();

            $("#ROAD_NO").html(txtHtml);
            $("#ROAD_NAME").val("");

            if(val != undefined){
                $("#ROAD_NO").val(val);
                fn_change_roadNm();
            }

            if ( isNotNull(ROAD_NO) ) {
                $("#ROAD_NO").val(ROAD_NO);
                fn_change_roadNm();

                ROAD_NO = "";
            }
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
    var roadGrad = $("#ROAD_GRAD").val();

    if(roadNo == "" /* || roadGrad == "" */) {
        $("#ROAD_NAME").val("");
        //$("#ROAD_GRAD").val("");
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
            $("#ROAD_GRAD").val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){

        }
    });
}

// Formatter Fuction
function fnConvertKm(cellValue, options, rowObject) {

    var nm = options.colModel.name;

    if ( nm == "STRTPT" ) {
        var val = rowObject.STRTPT;
        val *= 1;
        val = (val / 1000).toFixed(2);

        return val;

    } else if ( nm == "ENDPT" ) {
        var val = rowObject.ENDPT;
        val *= 1;
        val = (val / 1000).toFixed(2);

        return val;

    } else {
        return "";

    }
}

function fnFloat(cellValue, options, rowObject) {

    // 0.xx 표현을 위한 formatter
    var nm = options.colModel.name;

    if ( nm == "GPCI" ) {
        var val = rowObject.GPCI;
        return parseFloat(val).toFixed(2);
    }
    return "";
}

function fnFormatter(cellValue, options, rowObject) {

    var html = "";
    var nm = options.colModel.name;

    if ( nm == "CR" ) { //주 파손

        if ( rowObject.CNTL_DFECT != "DFCT0009" ) {

            var codeNm = rowObject.CODE_NM;

            if ( codeNm == "AC" ) {
                html = "거북등균열";

            } else if ( codeNm == "BC" ) {
                html = "블럭균열";

            } else if ( codeNm == "LC" ) {
                html = "선형균열";

            } else if ( codeNm == "PTCHG" ) {
                html = "패칭";

            } else if ( codeNm == "POTHOLE" ) {
                html = "포트홀";

            } else if ( codeNm == "RD" ) {
                html = "소성변형";

            } else if ( codeNm == "RCI" || codeNm == "IRI" ) {
                html = "종단평탄성";
            }

        } else {
            var minVal = Math.min( rowObject.AC_IDX, rowObject.BC_IDX, rowObject.LC_IDX, rowObject.PTCHG_IDX, rowObject.POTHOLE_IDX, rowObject.RD_IDX, rowObject.RCI );
            var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
            var valArr = [ rowObject.AC_IDX, rowObject.BC_IDX, rowObject.LC_IDX, rowObject.PTCHG_IDX, rowObject.POTHOLE_IDX, rowObject.RD_IDX, rowObject.RCI ];

            if ( minVal == 100 ) {
                // max 값이 0인 경우는 파손없음
                html += "파손없음";

            } else {
                html += "복합파손 <br /> (";
                var codeNames = [];

                for ( var i = 0; i < valArr.length; i++ ) {
                    // min값과 같은 경우 텍스트 추가
                    if ( valArr[i] == minVal ) {
                    	/* 
                        if ( i != 0 ) {
                            html += ", ";
                        }
                        html += nameArr[i];
                        */
                    	codeNames.push(nameArr[i]);
                    }
                }
                html += codeNames.join(',');
                html += ")";
            }
        }
    } else if ( nm == "CUZ" ) {
     // 파손원인
        var clmt = rowObject.DMG_CUZ_CLMT;
        var vmtc = rowObject.DMG_CUZ_VMTC;
        var etc = rowObject.DMG_CUZ_ETC;

        if ( clmt == vmtc && vmtc == etc ) {
            if ( etc == 0 ) {
                html = "파손없음";

            } else if ( etc != 0 ) {
                html = "교통량/하부불량,<br />기후, 기타";
            }
        } else if ( clmt > vmtc && clmt > etc ) {
            html = "기후";

        } else if ( vmtc > clmt && vmtc > etc ) {
            html = "교통량/하부불량";

        } else if ( etc > clmt && etc > vmtc ) {
            html = "기타";

        } else if ( clmt == vmtc && clmt > etc ) {
            html = "교통량/하부불량,<br />기후";

        } else if ( clmt == etc && clmt > vmtc ) {
            html = "기후, 기타";

        } else if ( vmtc == etc && vmtc > clmt ) {
            html = "교통량/하부불량,<br />기타";

        } else {
            html = "";

        }
    } else if ( nm == "btn_loc" ) {
        html = "<a href='#' onclick=\"fnSelectLoc('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }

    return html;
}


function fnSelectLoc(cellId) {

    // 하단 목록 창 내리기
    parent.bottomClose();

    // 선택한 셀을 보여줌
    var tables = ["CELL_SECT"];
    var fields = ["CELL_ID"];
    var values = [cellId];
    var attribute = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

}

function isNotNull(value) {
    if ( value == undefined || value == "" || value == null ) {
        return false;
    }

    return true;
}

function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/srvy/selectSrvyDtaEvlInfoListExcel.do'/>", "");

		//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectSurveyAllLenStatsExcel.do'/>", "");
	}
}
</script>
</body>
</html>