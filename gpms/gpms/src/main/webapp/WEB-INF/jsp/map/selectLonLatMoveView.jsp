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
<!-- <title>경위도 좌표 이동</title> -->
<%@ include file="/include/common_head.jsp" %>

</head>
<body id="wrap" class="left-tool searchLoc">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="imgMap_frm" name="imgMap_frm" method="post" action="">
<div class="content">
        <table class="tbview fl" summary="경위도 좌표 이동 합니다." style="width:400px">
        <caption>정보</caption>
        <colgroup>
            <col width="15%" />
            <col width="35%" />
            <col width="15%" />
            <col width="35%" />
        </colgroup>
        <tbody>
                <input type="text" hidden="true" style="width: 0.1px;height: 0.1px;"/>
            <tr>
                <th scope="row">
                    경도
                </th>
                <td>
                    <input type="text" id="lon" name="lon" style="width: 96%;color:#a3a4a5;" onkeypress="if(event.keyCode==13) {fn_move(); return false;}" value='127' onfocus="this.value=''"/>
                </td>
                <th scope="row">
                    위도
                </th>
                <td>
                   <input type="text" id="lat" name="lat" style="width: 96%;color:#a3a4a5;" onkeypress="if(event.keyCode==13) {fn_move(); return false;}"value='38' onfocus="this.value=''"/>
                </td>
            </tr>
        </tbody>
        </table>
     <a href="#" class="schbtn ml5" onclick="fn_move();" style="height:23px;line-height:23px;width:50px;">이동</a>
     <a href="#" class="schbtn ml5" onclick="fnSelect();" style="height:23px;line-height:23px;width:70px">셀정보조회</a>
</div>

<div class="mt10 ml10 mr10">
	<div id="div_grid" style="width:57%; height:50px;">
		<table id="gridArea"></table>
		<!-- <div id="gridPager"></div> -->
	</div>
</div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</form>

<script type="text/javaScript" language="javascript" defer="defer">

var strtFlag = 0;

//페이지 로딩 초기 설정
$( document ).ready(function() {
    parent.$("#mCtrlLonLatMove").parent().siblings().removeClass("active");
    parent.$("#mCtrlLonLatMove").parent().addClass("active");

});


// 이동
function fn_move(){
    var lon = $("#lon").val();
    var lat = $("#lat").val();

    if(lon == '' || lat == ''){
        alert("경위도를 모두 입력 하세요.");
    }else{
        fn_search(null);
        MAP.fn_LonLatmove(lon,lat,$("#wnd_id").val());
    }
}


// 셀속성
function fnSelect() {
    parent.gMap.cleanMap();
    var option = {
            iframe : window,
            callback : "fnShowList",
            clearMap : false
    };

    parent.MAP.CONTROL.set_option(option);

    parent.gMap.activeControls("cellPointLL");

}

function fnShowList() {

    var cellId = [];
    var features = parent.gMap.getLayerByName('GAttrLayerMulti').features;

    if (features.length == 0) {
        alert("선택된 셀이 없습니다.");
        parent.gMap.activeControls("drag");
        return;
    }

    for(var i=0; i<features.length; i++){
        cellId.push(features[0].data.CELL_ID);
    }

    parent.gMap.activeControls("drag");
    parent.gMap.cleanMap();

    fn_search(cellId);
}



function fnGridArea(cell_id) {

    var postData = null;

    if ( cell_id != undefined && cell_id != null && cell_id != "" ) {
        postData = {
                "CELL_ID_ARR" : cell_id
        };
    } else {
        postData = {
            "lon" : $("#lon").val()
            ,"lat" : $("#lat").val()
            , "CELL_ID" : ""
        };
    }

    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/cell10/selectCellInfoByLonLat.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "json"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: postData
        ,ignoreCase: true
        ,colNames:["CELL_ID","노선번호","노선명","방향", "차로", "시점(m)","종점(m)", "위치보기"]
        ,colModel:[
            { name:'CELL_ID',index:'ROAD_NO', hidden: true}
            ,{ name:'ROUTE_CODE'     ,index:'ROUTE_CODE'     , align:'center'    , width:80      , sortable:false }
            ,{ name:'ROUTE_NAME'     ,index:'ROUTE_NAME'     , align:'center'    , width:100     , sortable:false }
            ,{ name:'DIRECT_CODE'    ,index:'DIRECT_CODE'    , align:'center'    , width:50      , sortable:false    , formatter: fnFormatter }
            ,{ name:'TRACK'          ,index:'TRACK'          , align:'center'    , width:50      , sortable:false }
            ,{ name:'STRTPT'         ,index:'STRTPT'         , align:'center'    , width:50      , sortable:false }
            ,{ name:'ENDPT'          ,index:'ENDPT'          , align:'center'    , width:50      , sortable:false }
            ,{ name:'loc'            ,index:'loc'          , align:'center'      , width:50      , sortable:false    , formatter: fnFormatter }
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
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {
            // 더블클릭 처리
        }
        ,onSelectRow: function(rowId) {
            // 클릭 처리
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
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 50);

    // change popup size
    var wndId = $("#wnd_id").val();
    parent.$("#" + wndId).animate({ height : 240 }, 500);
    parent.$("#" + wndId).find("iframe").animate({ height : 240 - 30 }, 400);

    fn_search();
}



function fn_search(cell_id) {
    if (strtFlag == 0) {
        strtFlag++;
        fnGridArea(cell_id);
    }

    var postData = null;

    //cell_id 값이 있을경우 grid 값 , 없을 경우 좌표값
    if ( cell_id != undefined && cell_id != null && cell_id != "" ) {
        postData = {
                "CELL_ID_ARR" : cell_id
        };
    } else {
        postData = {
            "lon" : $("#lon").val()
            ,"lat" : $("#lat").val()
            , "CELL_ID" : ""
        };
    }

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

        }
        ,loadError: function(a,b,msg) {
            console.log(msg);
        }
    }).trigger("reloadGrid");
}


function fnFormatter(cellValue, options, rowObject) {
    var cellNm = options.colModel.name;

    switch(cellNm) {
	    case "DIRECT_CODE" : {
	        if ( rowObject.DIRECT_CODE == "S" )
	            return "상행";
	        else if ( rowObject.DIRECT_CODE == "E" ) {
	            return "하행";
	        }
	        else
	            return "";
	        break;
	    }
	    case "loc" : {
	        return "<a href='#' onclick=\"fnLocation('" + rowObject.CELL_ID + "');\"><img src='/gpms/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
	    }
    }
}

//////////////////////////////////////////////////////////////////////////
function fnLocation(cellId) {

    // 선택한 셀을 보여줌
    var tables = ["CELL_10"];
    var fields = ["CELL_ID"];
    var values = [cellId];
    var attribute = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

}


//////////////////////////////////////////////////////////////////////////
</script>
</body>
</html>