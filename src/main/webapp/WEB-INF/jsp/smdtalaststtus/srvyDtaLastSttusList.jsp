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
<script src="<c:url value='/js/common.js'/>"></script>
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
	<div class="fl bgsch">
    	<h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
                <li class="wid100">
                    <label>도로등급</label>
                    <select id="ROAD_GRAD" name="ROAD_GRAD" alt="도로등급" style="width:25%;" onchange="fn_change_roadNo();" class="input">
                        <option value="">= 전체 =</option>
                        <c:forEach items="${ roadGradList }" var="roadGrad">
                            <option value="${ roadGrad.CODE_VAL }">${ roadGrad.CODE_NM }</option>
                        </c:forEach>
                    </select>

                    <label>노선번호</label>
                    <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" style="width:25%;"onchange="fn_change_roadNm();" class="input">
                        <option value="">= 전체 =</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                </li>

                <li class="wid100">
                    <label>노선명</label>
                    <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width:194px;" class="MX_80 CS_50 input" />
                </li>

                <li class="wid100">
                    <label>관리기관</label>
                    <select id="DEPT_CODE" name="DEPT_CODE" alt="관리기관" style="width: 74.5%;" class="input">
                        <option value="">===== 전체 =====</option>
                        <c:forEach items="${ deptList }" var="dept">
                            <option value="${ dept.DEPT_CODE }">${ dept.LOWEST_DEPT_NM }</option>
                        </c:forEach>
                    </select>
                </li>

                <li class="wid100">
                    <label>섹션구분</label>
                    <select id="SECT_SE" name="SECT_SE" alt="섹션구분" style="width: 74.5%;" class="input">
                        <option value="">===== 전체 =====</option>
                        <c:forEach items="${ sectionType }" var="sect">
                            <option value="${ sect.CODE_VAL }">${ sect.CODE_NM }</option>
                        </c:forEach>
                    </select>
                </li>
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
                <li class="wid100 mt10">
                    <a href="#" class="schbtn dpb" onclick="javascript: fn_search();">검색</a>
                </li>

	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>포장상태 예측정보 조회</h3>
	    <p class="location">
	        <span>포장상태 예측</span>
	        <strong>포장상태 예측정보 조회</strong>
	    </p>

	    <div class="mt10 ml10 mr10" style="">
            <div id="div_grid" style="width:100%; height:240px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
	<div class="tc" style="margin-top: 14px">
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

<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/*

var param = "";

// 뒤로가기로 페이지 접근 시 검색조건
var ROAD_GRAD       = "${smDtaLastSttusVO.ROAD_GRAD}";
var ROAD_NO         = "${smDtaLastSttusVO.ROAD_NO}";
var DEPT_CODE       = "${smDtaLastSttusVO.DEPT_CODE}";
var SECT_SE         = "${smDtaLastSttusVO.SECT_SE}";
var DIRECT_CODE     = "${smDtaLastSttusVO.DIRECT_CODE}";
var TRACK           = "${smDtaLastSttusVO.TRACK}";

$( document ).ready(function() {

    parent.gMap.cleanMap();

    // 뒤로가기로 페이지 접근 시 검색조건 세팅
    if ( isNotNull(DEPT_CODE) )     { $("#DEPT_CODE").val(DEPT_CODE); }
    if ( isNotNull(SECT_SE) )       { $("#SECT_SE").val(SECT_SE); }
    if ( isNotNull(DIRECT_CODE) )   { $("#DIRECT_CODE").val(DIRECT_CODE); }
    if ( isNotNull(TRACK) )         { $("#TRACK").val(TRACK); }

    if ( isNotNull(ROAD_GRAD) ) {
        $("#ROAD_GRAD").val(ROAD_GRAD);
        fn_change_roadNo();
    }


	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>' + 'api/smdtalaststtus/selectSrvyDtaLastSttusList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SM_NO", "OBJECT_ID", "CELL_ID", "산정년도", "노선번호", "노선명", "도로등급", "관리기관", "섹션구분", "행선", "차로", "시점(km)", "종점(km)", "GPCI", "주 파손", "파손원인", "보수도래<br/>시기", "위치"
		           , "CNTL_DFECT", "CODE_NM", "AC_IDX", "LC_IDX", "BC_IDX", "PTCHG_IDX", "POTHOLE_IDX", "RD_IDX", "RCI", "DMZ_CUZ_CLMT", "DMZ_CUZ_VMTC", "DMZ_CUZ_ETC" ]
	   	,colModel:[
			{name:'SM_NO',index:'SM_NO', hidden: true}
			,{name:'OBJECT_ID',index:'OBJECT_ID', width:35, hidden: true}
			,{name:'CELL_ID',index:'CELL_ID', width:35, hidden: true}
			,{name:'CALC_YEAR',index:'CALC_YEAR', align:'center', width:30, sortable: true}
			,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:30, sortable: true}
			,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:35, sortable: true}
            ,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:30, sortable: true}
            ,{name:'DEPT_CODE',index:'DEPT_CODE', align:'center', width:35, sortable: true}
            ,{name:'SECT_SE',index:'SECT_SE', align:'center', width:35, sortable: true}
			,{name:'DIRECT_CODE',index:'DIRECT_CODE', align:'center', width:20, sortable: true}
			,{name:'TRACK',index:'TRACK', align:'center', width:20, sortable: true}
			,{name:'STRTPT',index:'STRTPT', align:'center', width:30, sortable:false, formatter: fnConvertKm}
			,{name:'ENDPT',index:'ENDPT', align:'center', width:30, sortable: true, formatter: fnConvertKm}
			,{name:'GPCI',index:'GPCI', align:'center', width:25, sortable: true, formatter: fnFloat}
			,{name:'CR',index:'CR', align:'center', width:35, sortable: false, formatter: fnFormatter}
			,{name:'CUZ',index:'CUZ', align:'center', width:50, sortable: false, formatter: fnFormatter}
			,{name:'RPAIR_TA',index:'RPAIR_TA', align:'center', width:30, sortable: true, formatter: fnRpairTa}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:25, sortable: false, formatter: fnFormatter}

			,{name:'CNTL_DFECT',index:'CNTL_DFECT', hidden: true}
			,{name:'CODE_NM',index:'CODE_NM', hidden: true}
			,{name:'AC_IDX',index:'AC_IDX', hidden: true}
			,{name:'LC_IDX',index:'LC_IDX', hidden: true}
			,{name:'BC_IDX',index:'BC_IDX', hidden: true}
			,{name:'PTCHG_IDX',index:'PTCHG_IDX', hidden: true}
			,{name:'POTHOLE_IDX',index:'POTHOLE_IDX', hidden: true}
			,{name:'RD_IDX',index:'RD_IDX', hidden: true}
			,{name:'RCI',index:'RCI', hidden: true}
			,{name:'DMZ_CUZ_CLMT',index:'DMZ_CUZ_CLMT', hidden: true}
			,{name:'DMZ_CUZ_VMTC',index:'DMZ_CUZ_VMTC', hidden: true}
			,{name:'DMZ_CUZ_ETC',index:'DMZ_CUZ_ETC', hidden: true}
	   	]
		,async : false
	   	,sortname: 'STRTPT'
	    ,sortorder: "ASC"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "포장상태 예측정보 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리

		    var objId = $("#gridArea").getRowData(rowId).OBJECT_ID;
		    var cellId = $("#gridArea").getRowData(rowId).CELL_ID;
		    var smNo = $("#gridArea").getRowData(rowId).SM_NO;
		    //fnSelectLoc(objId);
		    fnSelectLoc(cellId);
		    parent.COMMON_UTIL.cmMenuUrlContent('smdtalaststtus/selectSrvyDtaLastSttusDetail.do?SM_NO=' + smNo + '&OBJECT_ID=' + objId + '&CELL_ID=' + cellId + param, true);

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
	   			$("#sidx").val(this.p.postData.sidx);
	   			this.p.postData.sord = this.p.sortorder;
	   			$("#sord").val(this.p.postData.sord);
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

    if ( COMMON_LANG.trimdata( form.find('#TRACK').val() ).length > 1 ) {
        alert("차로 값은 최대 1자까지 입력할 수 있습니다.");
        return false;
    }

    return true;

}

function fnCheckNumber(obj){

    $(obj).keyup(function() {

        $(this).val($(this).val().replace(/[^0-9]/g, ""));

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

function fnFormatter(cellValue, options, rowObject) {

    var html = "";
    var nm = options.colModel.name;

    if ( nm == "CR" ) {

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

                for ( var i = 0; i < valArr.length; i++ ) {

                    // min값과 같은 경우 텍스트 추가
                    if ( valArr[i] == minVal ) {

                        if ( i != 0 ) {

                            html += ", ";

                        }

                        html += nameArr[i];

                    }

                }

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

function fnFloat(cellValue, options, rowObject) {

    // 0.xx 표현을 위한 formatter
    var nm = options.colModel.name;

    if ( nm == "GPCI" ) {

        var val = rowObject.GPCI;
        return parseFloat(val).toFixed(2);

    }

    return "";
}

function fnRpairTa(cellValue, options, rowObject) {

    // 0.xx 표현을 위한 formatter
    var nm = options.colModel.name;

    if ( nm == "RPAIR_TA" ) {

        var val = rowObject.RPAIR_TA;
        return val + "년";

    }

    return "";
}

function fnSelectLoc(cellId) {

    // 하단 목록 창 내리기
    parent.bottomClose();

    // 선택한 셀을 보여줌
    var tables = ["CELL_SECT"];
    var fields = ["CELL_ID"];
    var values = [cellId];

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values);

}

function isNotNull(value) {

    if ( value == undefined || value == "" || value == null ) {

        return false;

    }

    return true;

}


function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/smdtalaststtus/selectSrvyDtaLastSttusListExcel.do'/>", "");

		//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectSurveyAllLenStatsExcel.do'/>", "");
	}
}

</script>
</body>
</html>