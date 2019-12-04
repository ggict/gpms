<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>조사요청구간 관리 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="sidx" name="sidx" value=""/>
<input type="hidden" id="sord" name="sord" value=""/>
<!-- KEY 파라메터 -->
<input type="hidden" id="SRVY_REQUST_SCTN_NO" name="SRVY_REQUST_SCTN_NO" value="<c:out value="${srvyRequstSctnVO.SRVY_REQUST_SCTN_NO}"/>"/>
<input type="hidden" id="ROUTE_CODE" name="ROUTE_CODE" value="<c:out value="${srvyRequstSctnVO.ROUTE_CODE}"/>"/>
<input type="hidden" id="DIRECT_CODE" name="DIRECT_CODE" value="<c:out value="${srvyRequstSctnVO.DIRECT_CODE}"/>"/>
<input type="hidden" id="TRACK" name="TRACK" value="<c:out value="${srvyRequstSctnVO.TRACK}"/>"/>
<input type="hidden" id="STRTPT" name="STRTPT" value="<c:out value="${srvyRequstSctnVO.STRTPT}"/>"/>
<input type="hidden" id="ENDPT" name="ENDPT" value="<c:out value="${srvyRequstSctnVO.ENDPT}"/>"/>
<input type="hidden" id="PAV_CELL_ID" name="PAV_CELL_ID" value=""/>


<div class="tabcont">

    <header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">조사자료관리</option>
                </select>
                <select name="">
                    <option value="">조사요청구간등록</option>
                </select>
                <h2 class="h2">조사요청구간등록</h2>
            </span>
        </div>
    </header>
    
    <div class="contents container">
    
        <article class="div3">
            <h3 class="h3">검색조건</h3>
            <div class="table">
                <table>
                    <tbody>
                    <tr>
                        <td class="th"><label for="SRVY_NM">조사명</label></td>
                        <td>
                            <input type="text" name="SRVY_NM" id="SRVY_NM">
                        </td>
                    </tr>
                    <tr>
                        <td class="th"><label for="SRVY_REQUST_INSTT">조사요청기관</label></td>
                        <td>
                            <input type="text" name="SRVY_REQUST_INSTT" id="SRVY_REQUST_INSTT">
                        </td>
                    </tr>
                    <tr>
                        <td class="th"><label for="SRVY_CN">조사내용</label></td>
                        <td>
                            <input type="text" name="SRVY_CN" id="SRVY_CN">
                        </td>
                    </tr>
                     <tr>
                        <td class="th"><label for="SRVY_REQUST_DE">조사접수일</label></td>
                        <td>
                            <input type="text" name="SRVY_REQUST_DE" id="SRVY_REQUST_DE">
                        </td>
                    </tr>
                    </tbody>    
                </table>
            </div>
        </article>
        
        <article class="div9">
            <h3 class="h3 hidden">조사요청구간 목록</h3>
            <div class="table searchBox">
            <table>
                <tr>
                    <td class="th"><label for="">행선</label></td>
                    <td>
                        <select name="search_direct" id="search_direct" onchange="javascript:;">
                            <option value="">전체</option>
                            <option value="상행">상행</option>
                            <option value="하행">하행</option>
                        </select>
                    </td>
                    <td class="th">
                        <label for="search_track">차로</label>
                    </td>
                    <td>
                        <input type="text" name="search_track" id="search_track" value="" />
                    </td>
                    <td class="th">
                        <label for="search_strtpt">시점(m)</label>
                    </td>
                    <td>
                        <input type="text" name="search_strtpt" id="search_strtpt" value="" />
                    </td>
                    <td class="th">
                        <label for="search_endpt">종점(m)</label>
                    </td>
                    <td>
                        <input type="text" name="search_endpt" id="search_endpt" value="" />
                    </td>
                    <td class="btnCell">
                        <input type="button" id="search_btn" class="btn pri" value="검색" />
                    </td>
                </tr>
            </table>
            </div>

            <div class="table" id="div_grid">
                <table id="gridArea"></table>               
            </div>
            <div class="btnArea">
                <a href="javascript:;" onclick="removeCheck();" class="btn wrn">삭제</a>
                <a href="javascript:;" onclick="fnAddCell();" class="btn pri">조사요청구간 추가</a>
                <a href="javascript:;" onclick="fnViewLocation();" class="btn pri">조사요청구간 지도위치보기</a>
                <a href="javascript:;" onclick="fnSave();" class="btn pri">조사요청구간 등록</a>
            </div>
            <div id="gridPager"></div>
        </div>
        </article>
        
    </div>






<div class="tabcont">

    <div class="fr listbx" style="left:400px;">
        <h3>조사요청구간 목록 2</h3>
        <p class="location">
            <span>조사자료 관리</span>
            <strong>조사요청구간 관리</strong>
        </p>
        <div class="mt10 ml10 mr10">
            
            <div style="width:100%; height:210px;">
                <table id="gridArea"></table>
                <div id="gridPager"></div>
            </div>
            <div class="mt10 tc">
                    
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
var _routeCd="", _directCd="", _track="", _strtpt="", _endpt="", _cellIdList="", Cnt =0;

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
    // 달력 생성
    cmCreateDatepickerLinked('SRVY_REQUST_DE', 10, 205);
    

    // 검색 목록 그리드 구성
    var cell_id_arrays = $('#PAV_CELL_ID').val() && $('#PAV_CELL_ID').val().split(',');
    var srvy_requst_sctn_no_value = $('#SRVY_REQUST_SCTN_NO').val();
    var route_code_value = $('#ROUTE_CODE').val();
    var postData = {"ROUTE_CODE":route_code_value, "PAV_CELL_ID_LIST":cell_id_arrays};
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/srvyrequstsctncellinfo/selectSrvyRequstSctnCellInfoAllList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: $("#frm").cmSerializeObject()
        ,postData: postData
        ,ignoreCase: true
        ,colNames:["CELL_ID","노선번호","노선명","행선","차로","시점(m)","종점(m)","위치보기"]
        ,colModel:[
            {name:'CELL_ID', index:'CELL_ID', hidden:true}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:50, sortable:false, formatter: 'integer'}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:70, sortable:false}
            ,{name:'DIRECT_NM',index:'DIRECT_NM', align:'center', width:70, sortable:false}
            ,{name:'TRACK',index:'TRACK', align:'center', width:70, sortable:false}
            ,{name:'STRTPT',index:'STRTPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'ENDPT',index:'ENDPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'btn_loc_cell',index:'btn_loc_cell', align:'center', width:70, sortable:false, formatter: fn_create_btn}
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
        ,onSelectRow: function(rowId, status, e) {     // 클릭 처리
        	fnCalDetailInfo();
        }
        ,onSelectAll: function(aRowIds, status) {
            //console.log('[onSelectAll] ' + status + ' = ' + aRowIds.length);
            fnCalDetailInfo();
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
        ,loadonce: true
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 153);

    //fnSearch();
    addBtnEventHandler();
});

function removeCheck(){
	var recs = jQuery("#gridArea").jqGrid('getGridParam', 'selarrrow');
    var rows = recs.length;
    for (var i = rows - 1; i >= 0; i--) {
		$('#gridArea').jqGrid('delRowData', recs[i]);
	}
} 

var cmCreateDatepickerLinked = function(_oStartId, _oSize, _loc_top, _loc_left, _imgPath){

	var today = $.datepicker.formatDate('yy-mm-dd', new Date());

	// 디폴트 이미지 경로
	var imgPath = contextPath+ "/images/btn_calendar.gif" ;

	if ( _imgPath ){
		imgPath = _imgPath
	}

    $( "#"+_oStartId ).datepicker({
        changeMonth: true,changeYear: true,numberOfMonths: 1,showOn: "button",buttonImage: imgPath ,buttonImageOnly: true, maxDate : today
        ,onClose: function( selectedDate ) {
            //$( "#"+_oEndId ).parent().find("img").css("margin-bottom", "3px");
        }
        ,beforeShow : function(input){
            var offset = $(input).offset();
            var height = $(input).height();
            window.setTimeout(function () {
                $("#ui-datepicker-div").css({ top: (offset.top + height- _loc_top) + 'px', left: (offset.left +_loc_left )+ 'px' }) }, 1); }
    });
};
//검색 처리
function fnSearch() {
    var cell_id_arrays = $('#PAV_CELL_ID').val() && $('#PAV_CELL_ID').val().split(',');
    var srvy_requst_sctn_no_value = $('#SRVY_REQUST_SCTN_NO').val();
    var route_code_value = $('#ROUTE_CODE').val();
    var postData = {"ROUTE_CODE":route_code_value, "PAV_CELL_ID_LIST":cell_id_arrays};
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
        ,gridComplete: function() {
        	
        }	
    }).trigger("reloadGrid");
}

//조사요청구간 지도에서 셀 선택
function fnAddCell() {
   var option = {
           iframe : window,
           callback : "fn_add_srvyrequstsctn",
           //CNTRWK_ID : $("#CNTRWK_ID").val(),
           clearMap : false
   };
   
   parent.MAP.CONTROL.activate_cellSel("CELL_10", option);
}
//지도에서 선택한 셀 리스트 조회
function fn_add_srvyrequstsctn(cellIdList, param){
	
    $.ajax({
        url: contextPath + 'api/cell10/selectRouteInfos.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data: JSON.stringify( {CELL_IDS : cellIdList}) 
        ,success: function(data){
            if(data.length < 1) {return;}
            
            if(data.length > 1){
                var roadNM = "";
                for(var i in data){
                    if(i!=0){roadNM += ",";}
                    roadNM += data[i].ROAD_NAME + "(" + parseInt(data[i].ROUTE_CODE) + ")";
                }
                alert( '단일 노선이 선택되지 않았습니다.('+roadNM+')' );
                return;
            }
            
            var routeCd = data[0].ROUTE_CODE;
            var roadNm = data[0].ROAD_NAME;
            var directCd = data[0].DIRECT_CODE;
            var track = data[0].TRACK;
            var strtpt = parseInt(data[0].STRTPT);
            var endpt = parseInt(data[0].ENDPT);
            
            if(Cnt == 0) {
            	_routeCd = routeCd;	        
            	_directCd = directCd;
            	_track = track;
            	_strtpt = strtpt;
            	_endpt = endpt;
            	_cellIdList = cellIdList;
            	
           	 	$('#ROUTE_CODE').val(routeCd);
            	$('#DIRECT_CODE').val(directCd);
            	$('#TRACK').val(track);
            	$('#STRTPT').val(strtpt);
            	$('#ENDPT').val(endpt);
            	$('#PAV_CELL_ID').val(cellIdList);
            
            } else {
            	_routeCd += "," + routeCd;	        
            	_directCd += "," + directCd;
            	_track += "," + track;
            	_strtpt += "," + strtpt;
            	_endpt += "," + endpt;
            	_cellIdList += "," + cellIdList;
            	
                var routeCdSplit = _routeCd.split(',');
                var directCdSplit = _routeCd.split(',');
                var trackSplit = _routeCd.split(',');
                var strtptSplit = _routeCd.split(',');
                var endptSplit = _routeCd.split(',');
                
                
                for ( var i in routeCdSplit ) {
                	
                	$('#ROUTE_CODE').val(routeCdSplit[i]);
                    $('#DIRECT_CODE').val(directCdSplit);
                    $('#TRACK').val(trackSplit);
                    $('#STRTPT').val(strtptSplit);
                    $('#ENDPT').val(endptSplit);
                }
                
                $('#PAV_CELL_ID').val(_cellIdList);
            }
            Cnt++;
            
            // 기존 grid data 리셋 후 reload
            $('#gridArea').jqGrid('clearGridData');
            fnSearch();
            
        }
        ,error: function(a,b,msg){
        }
    });
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
    return "<a href='#' onclick=\"fn_select_cell('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
}
function fn_select_cell(cell_id){
    var tables = ["CELL_10"];
    var fields = ["CELL_ID"];
    var values = [cell_id];
    
    // 모든 팝업창 최소화
    parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomClose();
    
    var attribute_base = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };
    
    //MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
    MAP.fn_get_selectFeatureByAttrMulti(parent.gMap, tables, fields, values, null, "AND", attribute_base, true, 0, 1);
}

//위치조회
function fnViewLocation(){
	
	var $pavCellId = $('#PAV_CELL_ID');
	var res = $pavCellId && $pavCellId.val().split(',');
	
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
            values.push(data);
        }
    }else{
        fields = "CELL_ID";
        values = res[0];
    }
    
    // 모든 팝업창 최소화
    parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomClose();
    
    MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [fields], [values], null, "OR");
}
//셀 선택에 따라 공사 위치정보 수치 자동 계산
function fnCalDetailInfo() {
    
    var rowIds = $('#gridArea').jqGrid('getGridParam', 'selarrrow');
    if (rowIds && rowIds.length > 0 ) {
        
        var rowData = [];
        rowIds.forEach(function(elem) { rowData.push($('#gridArea').jqGrid('getRowData', elem)); });
        
        // 보수시점(m)
        var strtptData = rowData.map(function(elem) { return elem.STRTPT });
        var minStrtpt = strtptData.slice(0).sort(function compare(a,b) {return a-b;})[0];
        $('#STRTPT').val(minStrtpt);
        // 보수종점(m)
        var endptData = rowData.map(function(elem) { return elem.ENDPT });
        var maxEndpt = endptData.slice(0).sort(function compare(a,b) {return a-b;}).reverse()[0];
        $('#ENDPT').val(maxEndpt);
        // 공사차로수 / 공사폭(m) / 행선
        var directAndTrackData = rowData.map(function(elem) { return ""+elem.DIRECT_NM+"_"+elem.TRACK });
        var filteredData = directAndTrackData.filter(function(value, idx, self) { return self.indexOf(value) === idx });
        $('#TRACK').val(filteredData.length);
        var directData = filteredData.map(function(elem) { return elem.split("_")[0] })
                                     .filter(function(value, idx, self) { return self.indexOf(value) === idx });
        if (directData.length > 1) {
            //$('#DIRECT_NM').val('양방향');
            $('#DIRECT_CODE').val('SE');
        } else if (directData.length > 0 && directData[0] === '상행') {
            //$('#DIRECT_NM').val('상행');
            $('#DIRECT_CODE').val('S');
        } else if (directData.length > 0 && directData[0] === '하행') {
            //$('#DIRECT_NM').val('하행');
            $('#DIRECT_CODE').val('E');             
        }
        
    } else {
        $('#STRTPT').val(0);
        $('#ENDPT').val(0);
        $('#TRACK').val('');
        //$('#DIRECT_NM').val('');
        $('#DIRECT_CODE').val('');
    }
    
    //COMMON_UTIL.cmFormObjectInit("frm", true);
}
function addBtnEventHandler() {
	    
	    $('#search_btn').click(function() {
	        var search_direct = $('#search_direct option:selected').val();
	        var search_track = $('#search_track').val();
	        var search_strtpt = $('#search_strtpt').val();
	        var search_endpt = $('#search_endpt').val();
	        
	        var f = { groupOp: "AND", rules: [] };
	        if (search_direct && search_direct != '') {
	            f.rules.push({field: 'DIRECT_NM', op: 'cn', data: search_direct});
	        }
	        if (search_track && search_track != '') {
	            f.rules.push({field: 'TRACK', op: 'cn', data: search_track});
	        }
	        if (search_strtpt && search_strtpt != '') {
	            f.rules.push({field: 'STRTPT', op: 'cn', data: search_strtpt});
	        }
	        if (search_endpt && search_endpt != '') {
	            f.rules.push({field: 'ENDPT', op: 'cn', data: search_endpt});
	        }
	        
	        // reload grid
	        var grid = $('#gridArea'); 
	        grid[0].p.search = f.rules.length > 0; 
	        $.extend(grid[0].p.postData, { filters: JSON.stringify(f) }); 
	        grid.trigger("reloadGrid", [{ page: 1 }]);

	    })
	}


function validateInsert(frmId){
    var vform = $('#'+frmId);
    
    //조사명 - SRVY_NM
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#SRVY_NM').val()))){
        alert("조사명에 값을 입력하세요.");
        return false;
    }
    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SRVY_NM').val()).length>200){
        alert("조사명 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }

    //조사내용 - SRVY_CN
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#SRVY_CN').val()))){
        alert("조사내용에 값을 입력하세요.");
        return false;
    }
    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SRVY_CN').val()).length>200){
        alert("조사명 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }

    //조사요청일자 - SRVY_REQUST_DE
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#SRVY_REQUST_DE').val()))){
        alert("조사요청일자에 값을 입력하세요.");
        return false;
    }
    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SRVY_REQUST_DE').val()).length>10){
        alert("조사요청일자 값은 최대 10자까지 입력할 수 있습니다.");
        return false;
    }

    // [2019-10-31] 셀 선택 체크 추가
    var selectedCellArr = $('#gridArea').jqGrid('getGridParam','selarrrow');
    if (selectedCellArr && selectedCellArr.length > 0) {
        var selectedCellArrToString = selectedCellArr.map(function(elem) { return $('#gridArea').jqGrid('getRowData', elem).CELL_ID }).join();
        $('#PAV_CELL_ID').val(selectedCellArrToString);
    } else {
        alert('셀을 한 개 이상 체크해주세요');
        return false;       
    }
    
    
    return true;

}

//글 등록
function fnSave() {
    
    //위치등록 기능 구현 후 주석 제거
    if(!validateInsert('frm')){return;}
    
    if( confirm('<spring:message code="warn.insert.msg" />') ) {
        // 진행 프로그래스바 생성
        COMMON_UTIL.cmShowProgressBar();
        
        try {
            parent.gMap.cleanMap(); 
            // multipart/form-data 아닌 경우, mask 처리 값을 제거하여 폼 데이터를 전송 처리함
            COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/srvyrequstsctn/addSrvyRequstSctn.do'/>","fnSaveCallback");
            
        } catch(E) {
            alert("폼데이터 변환중 오류가 발생하였습니다. :" +E);
        }
    }
    
}
//---------------------------
//처리 후 callback 함수들 (필수)
//---------------------------
function fnSaveCallback( insertKey ) {
	
	COMMON_UTIL.cmHideProgressBar();
	
    // 목록 화면 재검색
	COMMON_UTIL.cmMoveUrl('<c:url value="/srvyrequstsctn/selectSrvyRequstSctnList.do" />');
}


</script>
</body>
</html>