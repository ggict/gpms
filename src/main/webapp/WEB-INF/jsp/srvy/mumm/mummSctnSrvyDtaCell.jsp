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
<!-- <title>조사자료상세조회 </title> -->
<!--
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

var curSelRow = null;
var features = null;

//페이지 로딩 초기 설정
$( document ).ready(function() {

	var cellId = "${mummSctnSrvyDtaVO.CELL_ID}";

    // 셀 목록
    fnShowCellList(cellId);

});

function fnShowCellList(cellId) {

	// 전송 데이터 조합
	var postData = {};
	postData["CELL_ID"] = cellId;

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/cell10/selectCellList.do'
		,width: true
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: postData
		,ignoreCase: true
		,colNames:["셀번호","노선명","방향","차로","시점","종점","조사년도min", "조사년도max", "조사년도"]
        ,colModel:[
            {name:'CELL_ID'			,index:'CELL_ID'		, align:'center'	, width:50	, sortable:false}
            ,{name:'ROAD_NAME'		,index:'ROAD_NAME'		, align:'center'	, width:50	, sortable:false}
            ,{name:'DIRECT_CODE'	,index:'DIRECT_CODE'	, align:'center'	, width:20	, sortable:false	, formatter:fnFormatter}
            ,{name:'TRACK'			,index:'TRACK'			, align:'center'	, width:20	, sortable:false}
            ,{name:'STRTPT'			,index:'STRTPT'			, align:'center'	, width:25	, sortable:false, formatter:'currency'}
            ,{name:'ENDPT'			,index:'ENDPT'			, align:'center'	, width:25	, sortable:false, formatter:'currency'}
            /* ,{name:'TRTS_BAC_CR'	,index:'TRTS_BAC_CR'	, hidden:true}
            ,{name:'VRTCAL_CR'		,index:'VRTCAL_CR'		, hidden:true}
            ,{name:'BLOCK_CR'		,index:'BLOCK_CR'		, hidden:true}
            ,{name:'HRZNTAL_CR'		,index:'HRZNTAL_CR'		, hidden:true}
            ,{name:'PTCHG_CR'		,index:'PTCHG_CR'		, hidden:true}
            ,{name:'POTHOLE_CR'		,index:'POTHOLE_CR'		, hidden:true}
            ,{name:'RD_VAL'			,index:'RD_VAL'			, hidden:true}
            ,{name:'IRI_VAL'		,index:'IRI_VAL'		, hidden:true} */
            ,{name:'SRVY_YEAR_MIN'  ,index:'SRVY_YEAR_MIN'  , hidden:true}
            ,{name:'SRVY_YEAR_MAX'  ,index:'SRVY_YEAR_MAX'  , hidden:true}
            ,{name:'SRVY_YEAR'      ,index:'SRVY_YEAR'      , align:'center'    , width:50  , sortable:false    , formatter:fnFormatter}
            //"TRTS_BAC_CR","VRTCAL_CR","BLOCK_CR","HRZNTAL_CR","PTCHG_CR","POTHOLE_CR","RD_VAL","IRI_VAL"
        ]
		,async : false
		,sortname: 'CELL_ID'
	    ,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
		,ondblClickRow: function( rowId ) {

		}
	   	,onSelectRow: function(rowId) {
	   	    /* // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
                fnSelect(rowData);
            } */
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
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);

	fnSearchCellList(cellId);
	//features 설정
	features = parent.parent.gMap.getLayerByName("GAttrLayer").features;
}

// 검색 처리 - 공사정보
function fnSearchCellList(cellId) {

	var postData = {};
	postData["CELL_ID"] = cellId;

	$("#gridArea").jqGrid("setGridParam", {
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData: postData
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		// 첫 행 검색
	   		$("#gridArea").jqGrid("setSelection", 1);
	   		var ids = $("#gridArea").jqGrid("getDataIDs");
	   		curSelRow = ids[0];

	   		fnView(ids[0]);

	   		//총 건수를 목록 상단에 표시
	   		$('#count').text('검색 결과 :'+$("#gridArea").getGridParam("records")+'건');
	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager_left").css('width', '');

	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

	   	 	$("#gridPager").css("width", "100%");

	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager_left").css('width', '');
	   		$("#gridPager_center").css('width', '100%');

	   	}
		, loadError: function(res) {
			alert("조회에 실패했습니다.");
			return;
		},onSelectRow: function(rowId) {
	        // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
                fnSelect(rowData);
            }
        }
	}).trigger("reloadGrid");
}



// 2018.11.20 위치조회 GAttrLayer 사용하도록 수정
function fnSelect(rowData) {

    // Base로 선택한 노선 보여줌
    var fArr = [];
    var vArr = [];

    for ( var i = 0 ; i < features.length ; i++ ) {
        fArr[i] = "CELL_ID";
        vArr[i] = features[i].data.CELL_ID;
    }

    var tables_base = ["CELL_10"];
    var fields_base = [fArr];
    var values_base = [vArr];
    var attribute_base = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };
    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base, false, 1, 0);

    // 선택한 셀을 보여줌
    var tables = ["CELL_10"];
    var fields = ["CELL_ID"];
    var values = [rowData.CELL_ID];
    var attribute = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };
    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

/*
    var featArr = parent.parent.gMap.getLayerByName("GAttrLayer").features;
    //var featArr = parent.parent.gMap.getLayerByName("GAttrLayerMulti").features;
    var cellId = rowData.CELL_ID;

    for ( var i = 0; i < featArr.length; i++ ) {
        if ( featArr[i].data.CELL_ID == cellId ) {
            featArr[i].attributes.fillColor = "#ffffff";
            featArr[i].attributes.strokeColor = "#ffffff";
        } else {
        	featArr[i].attributes.fillColor = "#0033ff";
            featArr[i].attributes.strokeColor = "#0033ff";
        }
    }
    parent.parent.gMap.getLayerByName("GAttrLayer").redraw();
    //parent.parent.gMap.getLayerByName("GAttrLayerMulti").redraw();
 */

}


function fnView(id) {
    curSelRow = id;
	var data = $("#gridArea").jqGrid("getRowData", id);

	if ( data != null ) {
		var srvyYear = $("#SRVY_YEAR_" + id).val();

		// 조사정보
		fnSelectSrvy(data.CELL_ID, srvyYear);

		// 평가정보s
		fnSelectAvg(data.CELL_ID);

		// 공사정보
		fnSelectCntrwkInfo(data.CELL_ID);
	}
}


//조사정보
function fnSelectSrvy(cellId, srvyYear) {

    var postDatas = { "CELL_ID": cellId, "SRVY_YEAR" : srvyYear };

    $.ajax({
        url: "<c:url value='/api/mummsctnsrvydta/selectMummSctnSrvyDtaByCell.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (data) {
            var srvydta = $("#mummAvgSrvyData").find("tbody td");

            srvydta.eq(0).html(Number(data.TRTS_BAC_CR));
            srvydta.eq(1).html(Number(data.VRTCAL_CR));
            srvydta.eq(2).html(Number(data.BLOCK_CR));
            srvydta.eq(3).html(Number(data.HRZNTAL_CR));
            srvydta.eq(4).html(Number(data.PTCHG_CR));
            srvydta.eq(5).html(Number(data.POTHOLE_CR));
            srvydta.eq(6).html(Number(data.RD_VAL));
            srvydta.eq(7).html(Number(data.TRTS_BAC_CR));
        },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;
        }
    });
}


//포장상태 평가정보 (평균)
function fnSelectAvg(cellId) {
	var postDatas = { "CELL_ID": cellId };

	$.ajax({
	    url: "<c:url value='/api/mummsctnsrvydta/selectMummSctnSrvyDtaListByItgrtnAvg.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (evldata) {
			// 평균 데이터 바인딩
            if ( evldata != undefined && evldata != null ) {
                if ( evldata.AC_IDX == null || evldata.BC_IDX == null || evldata.LC_IDX == null || evldata.TC_IDX == null
                        || evldata.PTCHG_IDX == null || evldata.POTHOLE_IDX == null || evldata.RD_IDX == null || evldata.RCI == null ) {
                    // null인 값이 있는 경우 초기값 그대로 표출
                    return;
                }

	   		    // 주 파손
	   		    var crVal = "";

	   		    if ( evldata.CNTL_DFECT != "DFCT0009" ) {
					var codeNm = evldata.CNTL_DFECT;
					if ( codeNm == "DFCT0001" ) {
					    crVal = "거북등균열";

					} else if ( codeNm == "DFCT0008" ) {
					    crVal = "블럭균열";

					} else if ( codeNm == "DFCT0002" ) {
					    crVal = "종방향균열";

					} else if ( codeNm == "DFCT0003" ) {
					    crVal = "횡방향균열";

					} else if ( codeNm == "DFCT0004" ) {
					    crVal = "패칭";

					} else if ( codeNm == "DFCT0005" ) {
					    crVal = "포트홀";

					} else if ( codeNm == "DFCT0006" ) {
					    crVal = "소성변형";

					} else if ( codeNm == "DFCT0007" ) {
					    crVal = "종단평탄성";

					}

					$("#crVal").css({"line-height": "50px", "font-size": "15px"});

	   		    } else {

		   		     var minVal = Math.min( evldata.AC_IDX, evldata.BC_IDX, evldata.LC_IDX, evldata.TC_IDX, evldata.PTCHG_IDX, evldata.POTHOLE_IDX, evldata.RD_IDX, evldata.RCI );
	                 var nameArr = [ "거북등균열", "블럭균열", "종방향균열", "횡방향균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
	                 var valArr = [ evldata.AC_IDX, evldata.BC_IDX, evldata.LC_IDX, evldata.TC_IDX, evldata.PTCHG_IDX, evldata.POTHOLE_IDX, evldata.RD_IDX, evldata.RCI ];

	   		        if ( minVal == 10 ) {
	   		            // min 값이 0인 경우는 파손없음
	   		            crVal += "파손없음";

	   		        } else {
		   		        crVal += "<br />복합파손 <br /> (";
				   		var val = [];

				   		for ( var i = 0; i < valArr.length; i++ ) {
				   		    // min값과 같은 경우 텍스트 추가
				   		    if ( valArr[i] == minVal ) {
				   		        val.push(nameArr[i]);
				   		    }
				   		}
				   		crVal += val.join();
				   		crVal += ")";

				   		$("#crVal").css({"line-height": "11px", "font-size": "11px"});
	   		        }
	   		    }

	   		    // 파손원인
	   		 	var clmt = evldata.DMG_CUZ_CLMT;
	   			var vmtc = evldata.DMG_CUZ_VMTC;
	   			var etc = evldata.DMG_CUZ_ETC;
	   			var cuz = "";

	   			if ( clmt == vmtc && vmtc == etc ) {
	   			    if ( etc == 0 ) {
	   			     	cuz = "파손없음";

	   			    } else if ( etc != 0 ) {
	   			     	cuz = "<br />교통량/하부불량,<br />기후, 기타";
	   			        $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});
	   			    }

	   			}else if ( clmt > vmtc && clmt > etc ) {
                    cuz = "기후";
                    $("#dmgCuz").css({"line-height": "50px"});

                } else if ( vmtc > clmt && vmtc > etc ) {
                    cuz = "교통량/하부불량";
                    $("#dmgCuz").css({"line-height": "50px", "font-size": "14px"});

                } else if ( etc > clmt && etc > vmtc ) {
                    cuz = "기타";
                    $("#dmgCuz").css({"line-height": "50px"});

	   			} else if ( clmt == vmtc && clmt > etc ) {
	   			    cuz = "<br />교통량/하부불량,<br />기후";
	   			    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	   			} else if ( clmt == etc && clmt > vmtc ) {
	   			 	cuz = "기후, 기타";

	   			} else if ( vmtc == etc && vmtc > clmt ) {
	   			 	cuz = "<br />교통량/하부불량,<br />기타";
	   			    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	   			} else {
	   				cuz = "";
	   			}

	   			$("#GPCI").html( parseFloat(evldata.GPCI).toFixed(2) );
	   			$("#crVal").html(crVal);
	   			$("#dmgCuz").html(cuz);

                var table = $("#avgTable td");
                table.eq(0).html( parseFloat(10 - evldata.AC_IDX).toFixed(2) );
                table.eq(1).html( parseFloat(10 - evldata.LC_IDX).toFixed(2) );
                table.eq(2).html( parseFloat(10 - evldata.BC_IDX).toFixed(2) );
                table.eq(3).html( parseFloat(10 - evldata.TC_IDX).toFixed(2) );
                table.eq(4).html( parseFloat(10 - evldata.PTCHG_IDX).toFixed(2) );
                table.eq(5).html( parseFloat(10 - evldata.POTHOLE_IDX).toFixed(2) );
                table.eq(6).html( parseFloat(10 - evldata.RD_IDX).toFixed(2) );
                table.eq(7).html( parseFloat(10 - evldata.RCI).toFixed(2) );

			} else {
				alert('검색된 포장공사 정보가 없습니다.');
				return;
			}
		},
		error: function () {
			alert("값을 가져올 수 없습니다.");
			return;
		}
	});
}


//공사정보
function fnSelectCntrwkInfo(cellId) {

	// 전송 데이터 조합
	/* var postData = $("#frm2").cmSerializeObject();
	postData["CELL_ID"] = param.CELL_ID; */
	var postData = { "CELL_ID" : cellId }

	// 검색 목록 그리드 구성
	$("#gridArea2").jqGrid({

		url: '<c:url value="/"/>'+'api/cntrwk/selectCntrwkListByCell.do'
		,width: true
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: JSON.stringify(postData)
		,ignoreCase: true
		,colNames:["공사ID","세부공사ID","셀번호","도로구분","공사명","세부공사명","착공일","준공일","위치보기"]
        ,colModel:[
            {name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
            ,{name:'DETAIL_CNTRWK_GROUP',index:'DETAIL_CNTRWK_GROUP', hidden: true}
            ,{name:'CELL_ID',index:'CELL_ID', align:'center', width:60, sortable:false}
            ,{name:'ROAD_SE',index:'ROAD_SE', align:'center', width:50, sortable:false}
            ,{name:'FULL_CNTRWK_NM',index:'FULL_CNTRWK_NM', align:'left', width:100, hidden: true}
            ,{name:'DETAIL_CNTRWK_NM',index:'DETAIL_CNTRWK_NM', align:'left', width:120, sortable:false}
            ,{name:'STRWRK_DE',index:'STRWRK_DE', align:'center', width:50, sortable:false , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'COMPET_DE',index:'COMPET_DE', align:'center', width:50, sortable:false , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:30, sortable:false , formatter: fnCreateBtnCntrwk}
        ]
		,async : false
		,sortname: 'CELL_ID'
	    ,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager2'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
		,ondblClickRow: function( rowId ) {
		    fnView2(rowId);	// 대장 조회

		}
	   	,onSelectRow: function(rowId) { }
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
	   	, loadComplete: function() {
	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager2_left").css('width', '');
	   		$("#gridPager2_center").css('width', '100%');
	   	}
		,multiselect: false
		,multiboxonly: false
	}).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', 180);

	fnSearchCntrwk(cellId);
}


// 검색 처리 - 공사정보
function fnSearchCntrwk(cellId) {

	var postData = { "CELL_ID": cellId };

	$("#gridArea2").jqGrid("setGridParam", {

		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData: postData
		,mtype: "POST"
	   	,loadComplete: function(data) {

	   		//총 건수를 목록 상단에 표시
	   		$('#count').text('검색 결과 :'+$("#gridArea2").getGridParam("records")+'건');
	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager2_left").css('width', '');

	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, data.records);

	   	 	$("#gridPager2").css("width", "100%");
	   	}
	}).trigger("reloadGrid");
}


// 상세 조회
function fnView2( rowId ) {

	if( $.type( rowId ) === "undefined" || rowId== "" ) {
	    rowId = $("#gridArea2").getGridParam( "selrow" );
	}

	if ( rowId != null ) {
		var rowData = $("#gridArea2").getRowData(rowId);
        var deCntrwkId = rowData["DETAIL_CNTRWK_GROUP"];
        var cntrwkId = rowData["CNTRWK_ID"];

        COMMON_UTIL.cmWindowOpen('상세정보 조회', "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>?DETAIL_CNTRWK_GROUP="+deCntrwkId+"&CNTRWK_ID="+cntrwkId, 1000, 1200, false, $("#wnd_id").val(), 'center');

	} else{
	    alert('<spring:message code="warn.checkplz.msg" />');
	}
}


// 위치이동 버튼 생성
function fnCreateBtnCntrwk(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"COMMON_UTIL.select_cell('" + rowObject.CELL_ID + "');\"><img src='" + contextPath + "images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}

function fnFormatter(cellValue, options, rowObject) {

    var mode = options.colModel.name;

    if ( mode == "DIRECT_CODE" ) {
		var dc = rowObject.DIRECT_CODE;

		if ( dc == "E" )
			return "하행";
		else if ( dc == "S" )
			return "상행";
		else
			return "";

    } else if ( mode == "SRVY_YEAR") {

        var minYear = parseInt(rowObject.SRVY_YEAR_MIN);
        var maxYear = parseInt(rowObject.SRVY_YEAR_MAX);
        var html = "<select id='SRVY_YEAR_" + options.rowId + "' name='SRVY_YEAR_" + options.rowId + "' alt='조사년도' onchange='fnSearchInfo($(this));'>";

        for ( var i = minYear; i <= maxYear; i++ ) {
            html += "<option value='" + i + "'>" + i + "년</option>";
            }
        html += "</select>";
        return html;

    } else {
        return cellValue;
    }
}

function fnFloat(val) {
    return parseFloat(val).toFixed(2);
}

//평균/개별 모드 전환
function fnShowData() {
	cmMenuUrlMummSctnSrvyDta('mng/mummsctnsrvydta/mummSctnSrvyDtaList.do?CELL_ID=' + "${mummSctnSrvyDtaVO.CELL_ID}", true);
}

// 검색
function fnSearchInfo(obj) {
    var cellId = obj.parent().parent().find("td[aria-describedby=gridArea_CELL_ID]").text();
    var selCellId = $("#gridArea").getRowData(curSelRow).CELL_ID;

    if ( cellId == selCellId ) {
        var srvyYear = obj.val();
        fnSelectSrvy(cellId, srvyYear);
    }
}

</script>
</head>

<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<%-- <form id="frm" name="frm" method="post" action=""> --%>
<div class="tabcont">
	<div class="">
	    <h3>
	       포장상태 평가정보 상세조회
        </h3>
        <div class="ytabbx" style="left: 240px; position:absolute; top:0px;">
		    <ul class="ytab fl" style="margin-top: 21px;">
	    		<li>
	    			<a href="#" onclick="fnShowData();" style="font-size: 14px;">평균평가정보</a>
	    		</li>
	    		<li class="on">
	    			<a href="#" onclick="" style="font-size: 14px;">개별평가정보</a>
	    		</li>
	        </ul>
        </div>
	    <p class="location">
	        <span>통합 정보 조회</span>
	        <span>포장상태 평가정보 조회</span>
	        <strong>포장상태 평가정보 상세조회</strong>
	    </p>
	    <div class="mt10 ml10 mr10">

	    	<div id="mummCellList" style="width:24%; padding-right: 5px; float: left">
    			<h3 style="line-height: 30px; font-size: 15px;">선택 셀 목록</h3>
				<div id="div_grid">
					<table id="gridArea"></table>
					<div id="gridPager" style='width: 100%;'></div>
				</div>
			</div>

	    	<div id="mummAvgSrvyData" style="width:24%; float: left; padding: 0px 5px;">
                <h3 style="line-height: 30px; font-size: 15px;">조사자료</h3>
                <table class="tbview" style="width: 100%; margin-top: 20px; height: 118px;">
                    <caption>포장상태 조사자료</caption>
                    <colgroup>
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />
                    </colgroup>
                    <tbody>
                        <tr style="height: auto;">
                            <th scope="row">거북등균열<br/>(㎡)</th>
                            <td class="center">0</td>
                            <th scope="row">종방향균열<br/>(m)</th>
                            <td class="center">0</td>
                        </tr>
                        <tr style="height: auto;">
                            <th scope="row">블럭균열<br/>(㎡)</th>
                            <td class="center">0</td>
                            <th scope="row">횡방향균열<br/>(m)</th>
                            <td class="center">0</td>
                        </tr>
                        <tr style="height: auto;">
                            <th scope="row">패칭(㎡)</th>
                            <td class="center">0</td>
                            <th scope="row">포트홀(㎡)</th>
                            <td class="center">0</td>
                        </tr>
                        <tr style="height: 20%;">
                            <th scope="row">소성변형<br/>(mm)</th>
                            <td class="center">0</td>
                            <th scope="row">종단평탄성<br/>(m/km)</th>
                            <td class="center">0</td>
                        </tr>
                        <tr style="height: auto;">
                            <th scope="row">교통량</th>
                            <td class="center" colspan="3">-</td>
                        </tr>
                    </tbody>

                </table>
            </div>

	    	<div id="mummAvg" style="width:24%; float: left; padding: 0px 5px;">
	    		<h3 style="line-height: 30px; font-size: 15px;">포장상태 평가 구간 평균</h3>

                <ul class="tblst mt15">
                    <li style="width:21%;border-left:0px" class="brl tc">
                        <span class="circle bc6" id="GPCI" style="width: 90%; font-size: 17px;">0</span>
                        <span>GPCI</span>
                    </li>
                    <li style="width:37%" class="brl tc">
                        <span class="circle bc5" id="crVal" style="width: 90%; font-size: 17px;">없음</span>
                        <span>주 파손</span>
                    </li>
                    <li style="width:40%" class="tc">
                        <span class="circle bc7" id="dmgCuz" style="width: 90%; font-size: 17px;">없음</span>
                        <span>파손원인</span>
                    </li>
                </ul>

                <table class="tbview" style="width: 100%; margin-top: 20px; height: 118px;" id="avgTable">
	                <caption>포장상태 평가 구간 평균</caption>
	                <colgroup>
	                    <col width="30%" />
	                    <col width="20%" />
	                    <col width="30%" />
	                    <col width="20%" />
	                </colgroup>
	                <tbody style="text-align: center;">
		    			<tr>
		    				<th scope="row">거북등균열</th>
		    				<td>0</td>
		    				<th scope="row">종방향균열</th>
		    				<td>0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">블럭균열</th>
		    				<td>0</td>
		    				<th scope="row">횡방향균열</th>
		    				<td>0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">패칭</th>
		    				<td>0</td>
		    				<th scope="row">포트홀</th>
		    				<td>0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">소성변형</th>
		    				<td>0</td>
		    				<th scope="row">종단평탄성</th>
		    				<td>0</td>
		    			</tr>
	    			</tbody>

	    		</table>

	    		<span style="font-size: 11px; margin-top: 5px; float: right;">* 포장파손형태 별 포장상태지수 감소값 (10점 만점)</span>
	    	</div>

			<div id="mummCntrwkList" style="width:24%; padding-left: 5px; float: left">
				<form id="frm2" name="frm2" method="post" action="">
					<div style="float: left;">
		    			<h3 style="line-height: 30px; font-size: 15px;">포장공사 이력</h3>
						<div id="div_grid2">
							<table id="gridArea2"></table>
							<div id="gridPager2" style='width: 100%;'></div>
						</div>
					</div>
				</form>
			</div>
        </div>
    </div>
</div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>