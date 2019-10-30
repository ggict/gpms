<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div class="tabcont">
	<div class="fl bgsch">
	    <h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <li class="wid100">
	                <label>도로등급</label>
	                <select id="ROAD_GRAD" name="ROAD_GRAD" alt="도로등급" onchange="fn_change_roadNo();" class="input" style="width:100px;">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${roadGradList }" var="roadGrad">
		        			<option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li class="wid100">
	                <label>노선번호</label>
	                <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input" style="width:100px;">
		                <option value="">== 전체 ==</option>
		        		<c:forEach items="${roadNoList }" var="roadNo">
		        			<option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li>
	                <label>노선명</label>
	                <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width:197px;" class="MX_80 CS_50 input" />
	            </li>
	            <li class="wid100" style="margin-top: 10px;">
	                <a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>노선 데이터로 검색</h3>
	    <p class="location">
	        <span>노선검색</span>
	        <strong>노선 데이터로 검색</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:206px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
        </div>
    </div>
</div>

</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">

var param = {};
var cnt = 0;
var directFlag = "${routeInfoVO.DIRECT_FLAG}";

//페이지 로딩 초기 설정
$( document ).ready(function() {

    //if ( directFlag != "N") { parent.gMap.cleanMap() };
    parent.gMap.cleanMap();

    if ( directFlag == "N") {

        // 상세보기로 넘어온 경우 파라미터 받기
        $("#ROAD_GRAD").val("${routeInfoVO.ROAD_GRAD}");
        fn_change_roadNo("${routeInfoVO.ROAD_NO}");
        fn_select_route("${routeInfoVO.ROAD_NO}");

    }

    var postData = {"USE_AT":"Y"};
    postData = $("#frm").cmSerializeObject();

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/routeinfo/selectRouteInfoList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: JSON.stringify( $("#frm").cmSerializeObject())
        ,postData: postData
        ,ignoreCase: true
        ,colNames:["ROAD_NO","노선 번호","노선 명","시점 명","종점 명","총연장(km)","전산화</br>완료연장(km)","위치보기"]
        ,colModel:[
            {name:'ROAD_NO',index:'ROAD_NO', hidden: true}
            ,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:50, sortable:true}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:100, sortable:true}
            ,{name:'ST_POINT',index:'ST_POINT', align:'left', width:120, sortable:true}
            ,{name:'ED_POINT',index:'ED_POINT', align:'left', width:120, sortable:true}
            ,{name:'ROAD_TOT_LEN_JYG_Y',index:'ROAD_TOT_LEN_JYG_Y', align:'center', width:80, sortable:true}
            ,{name:'ROAD_LEN_CMPT',index:'ROAD_LEN_CMPT', align:'center', width:80, sortable:true}
            /* ,{name:'btn_down',index:'btn_down', align:'center', width:50, sortable:false, formatter: fn_create_btn} */
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fn_create_btn}
        ]
        ,async : false
        ,sortname: 'ROAD_NO'
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
        	fnUpdate(rowId);  
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
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 206);

    setTimeout(function() {
         fn_search();
    }, 500);
});

//검색 처리
function fn_search() {
    var postData = {"USE_AT":"Y"};
    postData = $("#frm").cmSerializeObject();

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        //,postData:  JSON.stringify( $("#frm").cmSerializeObject())
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

            if ( !parent.$(".btab_menu li:eq(0)").hasClass("sel") ) {

                parent.$(".btab_menu li:eq(0)").addClass("sel");

            }

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
            
            // 2018. 09. 19.
            var ids = $("#gridArea").getDataIDs();
            
            for ( var i = 0; i < ids.length; i++ ) {
                
                var rowData = $("#gridArea").getRowData(ids[i]);
                var roadName = rowData.ROAD_NAME;
                
                if ( roadName == "" || roadName == undefined || roadName == null ) {
                    
                    var colData = $("#gridArea").find("#" + ids[i]);
                    
                    colData.find("td:eq(3)").attr("colspan", "6");
                    colData.find("td:eq(4)").hide();
                    colData.find("td:eq(5)").hide();
                    colData.find("td:eq(6)").hide();
                    colData.find("td:eq(7)").hide();
                    colData.find("td:eq(8)").hide();
                    
                    colData.find("td:eq(3)").html("속성정보가 없습니다.");
                    
                }
                
            }

        }
    }).trigger("reloadGrid");
}
// 수정
function fnUpdate(rowId) {
	if( $.type(rowId) === "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );

	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var roadNo = rowData["ROAD_NO"];

		COMMON_UTIL.cmMoveUrl("/routeinfo/routeInfoView.do?ROAD_NO="+roadNo);
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}
//도면 다운로드, 위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
    var btn = "";
    var nm = options.colModel.name;

    switch(nm) {
    case "btn_down" :
        // 클릭시 파일 다운로드
        btn = "<a href='#' onclick=\"fn_select_dwg('" + rowObject.ROAD_NO + "')\"><img src='" + contextPath +"/images/ic_download.png' alt='다운로드' title='다운로드' /></a>";
        break;
    case "btn_loc" :

        //if ( directFlag != "N" ) {

            btn = "<a href='#' onclick=\"fn_select_route('" + rowObject.ROAD_NO + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";

        /* } else {

            btn = "<a href='#' onclick=\"fn_select_route_nDirect('" + rowObject.ROAD_NO + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";

        } */
        break;
    }

    return btn;
}

//도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo(val) {
    var roadGrad = $("#ROAD_GRAD").val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>== 전체 ==</option>";

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
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
    var roadGrad = $("#ROAD_GRAD").val();

    if(roadNo == "") {
        $("#ROAD_NAME").val("");
        $("#ROAD_GRAD").val("");
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
            $("#ROAD_NAME").val("");
            $("#ROAD_GRAD").val("");
        }
    });
}

function fn_select_dwg(val){
    COMMON_UTIL.cmMoveUrl("dwginfo/selectDwgInfoList.do?ROAD_NO="+val);
}

function fn_select_route(route_no){
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [route_no];

    // 모든 팝업창 최소화
    //parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomClose();

    var attribute = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, null, attribute);
}

// 통합정보조회 - 상세조회 로 접근했을 때 위치조회
function fn_select_route_nDirect(route_no){

    var features = parent.gMap.getLayerByName('GAttrLayer').features;

    // 하단 목록 창 내리기
    parent.bottomClose();

    // Base로 선택한 노선 보여줌
    var fArr = [];
    var vArr = [];

    for ( var i = 0 ; i < features.length ; i++ ) {

        fArr[i] = "ROAD_NO";
        vArr[i] = features[i].data.ROAD_NO;

    }

    var tables_base = ["DORO_TOT_GRS80_50"];
    var fields_base = [fArr];
    var values_base = [vArr];
    var attribute_base = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base, true, 1, 0);

    // 선택한 노선을 보여줌
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [route_no];
    var attribute = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, "OR", attribute);


}
</script>
</body>
</html>