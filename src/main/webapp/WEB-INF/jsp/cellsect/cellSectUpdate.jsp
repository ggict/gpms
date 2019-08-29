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
<!-- <title>셀속성편집 </title> -->
<!--
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

//======================================== DEFAULT SETTING ======================================== //
//[ DEFAULT ] div 초기화
var grid = "";
grid += "<table id='gridArea'></table>"
    + "<div id='gridPager' style='width: 100%;'></div>";

//선택한 행번호 변수
var selRow = 0;
//페이지 로딩 초기 설정
$( document ).ready(function() {
    //지도 우측 툴 셀 속성 아이콘 활성화
    parent.$("#editCellInfo").parent().addClass("active");

    // 지도 초기화
    parent.gMap.cleanMap();
    parent.gMap.activeControls("drag");
    parent.$("#map .left_tool li").removeClass("active");
    parent.$("#mCtrlPan").parent().addClass("active");

    // 하단 팝업 초기화
    parent.bottomHide();

    //1단계 섹션셀 활성화
    $("#BTN_CELL_SECT").parent().addClass("on");

    $(".selbtn").click(function () {
        //
        // Button Toggle
        $(this).parent().siblings('li').removeClass('on');

        // $(this) id, class, num of class
        var id = $(this).attr("id");
        var classArr = $(this).parent().attr("class");

        // 2단계 버튼 점형 선택
        if ( id == "BTN_SECT_POINT") {
            // 1단계 버튼 on check
            var res = fnStep1();

            option = {
                    iframe : window,
                    callback : "fnCheckFeatures",
                    clearMap : false
            };

            if ( res == -1 ) {return; }
            else {
                parent.gMap.cleanMap();
                parent.MAP.CONTROL.set_option(option);
                // Change Mouse point
                parent.gMap.activeControls("selSectPoint");

                // Change Range
                var range = $("#pointArea").val();
                parent.gMap.getControl("selSectPoint").setDistance(range);
                $(this).parent().addClass("on");

                // 조회결화 리셋 후 재조회
                $("#div_grid").html(grid);
                $("#step3").hide();
                fnPopupResize(215);

            }
        // 2단계 버튼 면형 선택
        } else if ( id == "BTN_SECT_POLYGON") {
            // 1단계 버튼 on check
            var res = fnStep1();

            option = {
                    iframe : window,
                    callback : "fnCheckFeatures",
                    clearMap : false
            };

            if ( res == -1 ) {
                return;
            }
            else {
                parent.gMap.cleanMap();
                parent.MAP.CONTROL.set_option(option);
                // Change Mouse point
                parent.gMap.activeControls("selSectPolygon");

                // Change Range
                $(this).parent().addClass("on");

                // 조회결화 리셋 후 재조회
                $("#div_grid").html(grid);
                $("#step3").hide();
                fnPopupResize(215);
            }
        // 1단계 선택
        } else if ( id == "BTN_CELL_SECT" && classArr.indexOf(" on") == -1 ) {
            $(this).parent().toggleClass('on');
        }

    });

 // STEP 2 점 반경범위 변경
    $("#pointArea").change(function(){

        // point mode
        if ( $("#BTN_SECT_POINT").parent().hasClass("on") ) {

            var range = $("#pointArea").val();

            if ( range == "" || range == undefined || range == "0" ) {

                alert ("반경 범위를 입력해 주세요.");
                return;

            }

            parent.gMap.getControl("selSectPoint").setDistance(range);

        }

    });

    // STEP 3 버튼 토글 이벤트
    $(".togglebtn").click(function () {

        var wndId = $("#wnd_id").val();

        if ( $(this).hasClass("on") ) {

            if ( $("#gridArea").html() != "" ) {

                fnPopupResize(260);
                $(this).removeClass("on");
                $(this).attr("title", "열기");

            }

        } else {

            fnPopupResize(520);
            $(this).addClass("on");
            $(this).attr("title", "닫기");

        }

        $("#result").slideToggle("slow");
    });
});

//검색 처리
function fnSearch() {
    var postData = {"USE_AT":"Y"};
    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData:   $("#frm").cmSerializeObject()
        //,postData:  JSON.stringify( $("#frm").cmSerializeObject())
        ,mtype: "POST"
        ,loadComplete: function(data) {
            $('#count').text('검색 결과 :'+$("#gridArea").getGridParam("records")+'건');

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}


//1단계 버튼 on check
function fnStep1(){
    if ( $("#BTN_CELL_SECT").parent().attr("class").indexOf(" on") == -1 ) {
    alert("1단계를 선택하세요.");
    return -1;
  }return 1;
}

//1단계 2단계 둘다 선택 했을 경우 3단계 show
function fnStep3(){ 
    // OBJECT_ID 조합
    var obId = "";

    // input cell Id from features
    for ( var i in features ) {
        if ( i != 0 ) {
            obId += ",";
        }
        //181106 wijy 수정 : OBJECT_ID가 아닌 g2id를 사용하도록 변경
        obId += parseInt(features[i].g2id);
        //obId += parseInt(features[i].data.OBJECT_ID);
    }

    // 전송 데이터 조합
    var postData = $("#frm").cmSerializeObject();
    /* postData["OBJECT_ID"] = obId; */
    postData["GID"] = obId;

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/selectCellSectList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: postData
        //,postData:  JSON.stringify( $("#frm").cmSerializeObject())
        ,ignoreCase: true
        ,colNames:["GID","행선","차로","시점","종점","노선</br>번호","노선명","관리</br>주체","행정</br>구역","도로</br>등급","교통</br>용량","교통량","SECTION_CD","SECTION구분","위치</br>보기"]
        ,colModel:[
            {name:'GID',index:'GID', hidden: true}
            ,{name:'DIRECT_CODE',index:'DIRECT_CODE', hidden: true}
            ,{name:'TRACK',index:'TRACK', hidden: true}
            ,{name:'STRTPT',index:'STRTPT', hidden: true}
            ,{name:'ENDPT',index:'ENDPT', hidden: true}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE',align:'center',width:30, sortable:true}
            ,{name:'ROUTE_NM',index:'ROUTE_NM', align:'center',width:50, sortable:true}
            ,{name:'DEPT_CODE',index:'DEPT_CODE', align:'center', width:50, sortable:true}
            ,{name:'ADM_CODE',index:'ADM_CODE', align:'center', width:30, sortable:true}
            ,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:50, sortable:true}
            ,{name:'VMTC_GRAD',index:'VMTC_GRAD', align:'center', width:50, sortable:true}
            ,{name:'VNTC_GR',index:'VNTC_GR', align:'center', width:40, sortable:true}
            ,{name:'CELL_TYPE',index:'CELL_TYPE', hidden: true}
            ,{name:'CELL_TYPE_NM',index:'CELL_TYPE_NM', align:'center', width:80, sortable:true,
                   editable: true, edittype:"select", editoptions:{dataUrl:"/gpms/api/code/selectCellType.do", buildSelect:setSelectCombo,
                   dataEvents:[{
                        type: 'change', fn: function (e) {
                            fn_saveSectse();
                       }
                    }]}}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:40, formatter: fn_create_btn, sortable: false}
        ]
        ,async : false
        ,sortname: 'ROUTE_CODE'
        ,sortorder: "desc"
        ,rowNum: 10
        ,rowList: [10,20,50,100]
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
        ,onCellSelect: function(rowId, index, contents, event){
            var cellNm = $(this).jqGrid('getGridParam', 'colModel');
            if(cellNm[index].name == 'CELL_TYPE_NM')
            {
                jQuery('#gridArea').jqGrid('editRow',rowId,true);
                selRow=rowId;
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
        ,loadComplete:function() {
        	//dvMapLoading hide
        }
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 150);

    fnSearch();
}

//위치이동
function fn_select_route(g2Id){

    var tables = ["CELL_SECT"];
    var fields = ["GID"];
    var values = [g2Id];

    // 모든 팝업창 최소화
    //parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomClose();

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values);

}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
    return "<a href='#' onclick=\"fn_select_cellSect('" + rowObject.GID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}

function fn_saveSectse(){
    var rowData = $("#gridArea").getRowData(selRow);
    var g2Id = rowData["GID"];
    var routeCd = rowData["ROUTE_CODE"];
    var dirCd = rowData["DIRECT_CODE"];
    var track = rowData["TRACK"];
    var st = rowData["STRTPT"];
    var et = rowData["ENDPT"];

    if(g2Id != "" || g2Id != null){
        var action = '<c:url value="/api/cellSect/updateSectSe.do"/>';
    }

    var sectSe = $("#"+selRow+"_CELL_TYPE_NM option:selected" ).val();
    var data = {"GID" : g2Id,"CELL_TYPE": sectSe
                ,"ROUTE_CODE" : routeCd, "DIRECT_CODE" : dirCd
                ,"TRACK" : track, "STRTPT" : st, "ENDPT" : et};

    $.ajax({
        url: action
        ,data: JSON.stringify(data)
        ,type: 'post'
        ,contentType : "application/json"
        ,success: function(data){
            alert("SECTION_구분이 수정되었습니다.");
            fnSearch();
            return;
        }
        ,error: function(a,b,msg){
            alert( "수정에 문제가 발생하였습니다.관리자에게 문의하시기 바랍니다.");
            fnSearch();
        }
    });
}

//위치조회
function fn_select_cellSect(g2Id){
    var tables = ["CELL_SECT"];
    var fields = ["GID"];
    var values = [g2Id];
    var attribute = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    // 모든 팝업창 최소화
    // parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomHide();

    var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
    var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

    parent.gMap.setLayerIndex(attrLayer, 1);
    parent.gMap.setLayerIndex(multiLayer, 0);

    MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, "OR", attribute);
}

//선택된 도형 체크
function fnCheckFeatures() {

    // Change Mouse Pointer
    parent.gMap.activeControls("drag");

    // get Layer by features
    features = parent.gMap.getLayerByName('GAttrLayerMulti').features;

    if ( features.length == 0 ) {

        // 검색할 내용이 선택되지 않은 경우
        if ( $("#BTN_CELL_SECT").parent().hasClass("on") ) {

            parent.gMap.activeControls("selSectPoint");

        } else if ( $("#BTN_SECT_POINT").parent().hasClass("on") ) {

            parent.gMap.activeControls("selSectPolygon");

        }

        alert("검색할 SECTION 레이어를 선택해 주세요.");
        return -1;

    } else {

        // 검색할 내용이 선택된 경우
        //parent.gMap.activeControls("drag");

        // set cell count
        option.cellCount = features.length;

        try {

            // clear map when clearMap is undefined or true
            if( option.clearMap == undefined || option.clearMap ) {

                parent.gMap.cleanMap();

            }

            // 1,2단계 모두 선택 되었을 경우 3단계조회
            if ( $(".selbtn").parent().hasClass("on") ) {
                fnStep3();
                $("#result").css("display", "block");
                $("#step3").show();
                fnPopupResize(520);
            }

        } catch (err) {

            alert (err);
            return;

        }

        return 1;

    }

}

function setSelectCombo(data) {
    value = jQuery.parseJSON(data);

    var result = '<select>';
    result += '<option value="">==선택==</option>';

    for(var i=0; i < value.typeList.length; i++) {
        result += '<option value="' + value.typeList[i].CODE_VAL + '">' + value.typeList[i].CODE_NM + '</option>';
    }
    result += '</select>';

    return result;

}

</script>
</head>
<body id="wrap" class="right-tool">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<%-- <form id="frm" name="frm" method="post" action=""> --%>
    <div class="content">
    	<div style="width:600px;color:#f00;padding:3px 0;margin-bottom:5px;line-height: 20px;">
	    ※ 본 기능은 <b>[Section 셀]</b>을 대상으로 조회하는 기능입니다.<br>
	    ※ 원활한 사용을 위해 <b>[레이어관리]</b> 에서 <b>[10m 셀]</b>을 끄고 조작해 주십시오.
	    </div>
    
        <div style="width:600px">
            <ul class="stepbx af">
                <li style="width:38%">
                    <div class="stbx">
                        <h4><span class="step">단계1</span>편집항목</h4>
                        <div style="height:80px">
                            <ul class="tblst selbx">
                                <li style="width:100%" class="tc">
                                    <a class="selbtn btncursor" id="BTN_CELL_SECT">
                                        <span class="roundbx"><img src="../images/ic_sel1.png" alt="SECTION 셀"/></span>
                                        <span>SECTION 셀</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li style="width:62%">
                    <div class="stbx">
                        <h4><span class="step">단계2</span>선택방법</h4>
                        <div style="height:80px">
                            <ul class="tblst selbx">
                                <li style="width:50%;border-left:0px" class="brl tc">
                                    <a class="selbtn btncursor" id="BTN_SECT_POINT">
                                        <span class="roundbx"><img src="../images/ic_shape1.png" alt="점" /></span>
                                        <span class="dpb">점 <input type="text" name="pointArea" id="pointArea" maxLength="3" value="10" style="width:35px" /> m</span>
                                    </a>

                                </li>
                                <li style="width:48%;" class="tc">
                                    <a class="selbtn btncursor" id="BTN_SECT_POLYGON">
                                        <span class="roundbx"><img src="../images/ic_shape2.png" alt="다각형" /></span>
                                        <span>다각형</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
            <ul class="stepbx mt10">
                <li class="wid100">
                    <div class="stbx" style="display: none;" id="step3">
                        <h4><span class="step">단계3</span>조회결과
                            <a href="#" class="togglebtn on" title="닫기"><span class="hidden">열고닫기</span></a>
                        </h4>
                        <div id="result" style="display: none;">
                            <h4 id="count">검색 결과 : 0건</h4>
                            <div id="div_grid" style="width:100%; float: block; padding-top: 5px;">
                                <table id="gridArea"></table>
                                <div id="gridPager"></div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>