<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>조사요청구간 관리 </title>
<!--
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>


<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidx" name="sidx" value=""/>
<input type="hidden" id="sord" name="sord" value=""/>


<div class="tabcont">
    <div class="fl bgsch">
        <h3>검색조건</h3>
        <div class="schbx mt10">
            <ul class="sch">
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
                <li class="wid100">
                    <label>조사요청일자</label>
                    <input type="text" name="SRVY_REQUST_DE1" id="SRVY_REQUST_DE1" style="width:70px; margin-right: 3px;" class="DT_DATE input" /> ~
                    <input type="text" name="SRVY_REQUST_DE2" id="SRVY_REQUST_DE2" style="width:70px; margin-right: 3px;" class="DT_DATE input" />
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="javascript:fnSearch();">검색</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="fr listbx">
        <h3>조사요청구간 목록</h3>
        <p class="location">
            <span>조사자료 관리</span>
            <strong>조사요청구간 관리</strong>
        </p>
        <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
                <table id="gridArea"></table>
                <div id="gridPager"></div>
            </div>
            <div class="mt10 tc">
                <div class="fl">
                    <!-- <a href="javascript" onclick="fn_cntrwkExcel();" class="schbtn">엑셀저장</a>  -->
                    <a href="javascript:;" onclick="fnDelete();" class="schbtn">삭제</a>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
    // 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('SRVY_REQUST_DE1', 'SRVY_REQUST_DE2', 10);

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/srvyrequstsctn/selectSrvyRequstSctnList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: JSON.stringify( $("#frm").cmSerializeObject())
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["SRVY_REQUST_SCTN_NO","조사명","노선번호","노선명","행선","차로","시점(m)","종점(m)","조사요청일자","위치보기"]
        ,colModel:[
            {name:'SRVY_REQUST_SCTN_NO',index:'SRVY_REQUST_SCTN_NO', hidden: true}
            ,{name:'SRVY_NM',index:'SRVY_NM', align:'center', width:60}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:50, sortable:false, formatter: 'integer'}
            ,{name:'ROAD_NAME',index:'ROAD_NAME',  align:'center', width:70, sortable:false}
            ,{name:'DIRECT_NM',index:'DIRECT_NM', align:'center', width:70, sortable:false}
            ,{name:'TRACK',index:'TRACK', align:'center', width:70, sortable:false}
            ,{name:'STRTPT',index:'STRTPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'ENDPT',index:'ENDPT', align:'center', width:70, sortable:false, formatter: 'integer'}      
            ,{name:'SRVY_REQUST_DE',index:'SRVY_REQUST_DE', align:'center', width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, formatter: fn_create_btn, sortable: false}
        ]
        ,async : false
        ,sortname: ''
        ,sortorder: ""
        ,rowNum: 99999
        ,rowList: []
        ,pgbuttons: false
        ,pgtext: null
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
            //fnView(rowId);  // 대장 조회
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
                $("#sidx").val(this.p.postData.sidx);
                this.p.postData.sord = this.p.sortorder;
                $("#sord").val(this.p.postData.sord);
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        ,multiselect: true
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);

    fnSearch();
});

//검색 처리
function fnSearch() {
	var road_no = $('#ROAD_NO').val();
	var srvy_requst_de_strt = $('#SRVY_REQUST_DE1').val() && $('#SRVY_REQUST_DE1').val().replace(/-/gi, '');
	var srvy_requst_de_endt = $('#SRVY_REQUST_DE2').val() && $('#SRVY_REQUST_DE2').val().replace(/-/gi, '')
    var postData = {"ROUTE_CODE":road_no, "SRVY_REQUST_DE_STRT":srvy_requst_de_strt, "SRVY_REQUST_DE_ENDT":srvy_requst_de_endt};
    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        //,postData:   $("#frm").cmSerializeObject()
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

//공사목록 엑셀로 출력
function fn_cntrwkExcel(){
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/cntrwk/cntrwkListExcel.do'/>", "");
    }
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
    return "<a href='#' onclick=\"fnViewLocation('" + rowObject.SRVY_REQUST_SCTN_NO + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}
//도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo() {
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
        }
        ,error: function(a,b,msg){

        }
    });
}
//위치조회
function fnViewLocation(val){
    var srvy_requst_sctn_no = val;

    $.ajax({
        url: contextPath + 'api/srvyrequstsctncellinfo/selectPavYearListAll.do'
        ,type: 'post'
        ,data: JSON.stringify({"SRVY_REQUST_SCTN_NO" : srvy_requst_sctn_no })
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,success: function(res){
            var tables = ["CELL_10"];
            var fields = [];
            var values = [];

            if(res.length < 1){
                alert("위치 정보가 존재하지 않습니다.");
                return;
            }

            if(res.length > 1){
                for(var i in res){
                    var data = res[i];
                    fields.push("CELL_ID");
                    values.push(data.PAV_CELL_ID);
                }
            }else{
                fields = "CELL_ID";
                values = res[0].PAV_CELL_ID;
            }

            // 모든 팝업창 최소화
            parent.wWindowHideAll();
            // 하단 목록 창 내리기
            parent.bottomClose();

            MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [fields], [values], null, "OR");
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
 
//신규 등록 화면 이동 [수정:선택] 키가 복수개 이거나 명칭이 다른 경우
 function fnWrite() {
     $.ajax({
         url: contextPath + 'userauth/checkAuth.do'
         ,type: 'post'
         ,dataType: 'json'
         ,data : {"url" : "/srvyrequstsctn/add.do"}
         ,success: function(res){                
             if(!res.result){
                 alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                 return;
             }
             
             var option = {
                     iframe : window,
                     callback : "fn_add_cntrwkDtl",
                     CNTRWK_ID : $("#CNTRWK_ID").val(),
                     clearMap : false
             };
             
             parent.MAP.CONTROL.activate_cellSel("CELL_10", option);
         }
         ,error: function(a,b,msg){
             
         }
     });
 }

// 그리드 체크 선택된 항목 삭제
function fnDelete() {
	var rowIds = $('#gridArea').jqGrid('getGridParam', 'selarrrow');
	if (rowIds && rowIds.length > 0 ) {
        var rowData = [];
        rowIds.forEach(function(elem) { rowData.push($('#gridArea').jqGrid('getRowData', elem)); });
        
        var srvy_requst_sctn_no_list = rowData.map(function(elem) { return elem.SRVY_REQUST_SCTN_NO; }).join();
        var postData = {"SRVY_REQUST_SCTN_NO_LIST": srvy_requst_sctn_no_list };
        
        var msg = "삭제를 진행하시겠습니까?";
        if (confirm(msg)) {
            $.ajax({
                url: contextPath + 'api/srvyrequstsctn/deleteSrvyRequstSctn.do'
                ,data: JSON.stringify(postData)
                ,type: 'post'
                ,dataType: 'json'
                ,contentType: "application/json;charset=UTF-8"
                ,success: function(res){
                	if (res != null) {
	                    alert("조사요청구간 정보가 삭제되었습니다.");
	                    COMMON_UTIL.cmMoveUrl('srvyrequstsctn/selectSrvyRequstSctnList.do');
	                    return;
                	}
                }
                ,error: function(a,b,msg){
                    alert("삭제처리에 문제가 발생하였습니다.");
                }
            });        	
        }
   
	} else {
		alert('삭제하려는 데이터를 선택해주세요.');
		return;
	}

}
</script>
</body>
</html>