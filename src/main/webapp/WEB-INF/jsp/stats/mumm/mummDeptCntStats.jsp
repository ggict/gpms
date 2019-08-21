<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사평가 통계</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div id="container">
    <div class="tabcont">
        <h3>포장상태 평가 관리기관별 통계</h3>
        <p class="location">
            <span>포장공사 이력관리</span>
            <span>포장공사 통계조회</span>
            <strong>관리기관별 통계</strong>
        </p>
        
        <div class="btnbx mb10" style="margin-right: 25px;">
            <a href="#" onclick="fnShowChart();" class="schbtn" style="float: right;">그래프 보기</a> &nbsp;
            <a href="#" onclick="fnExcel();" class="schbtn" style="float: right; margin-right: 5px;">엑셀저장</a>
        </div>
        <div class="cont_ListBx" style="margin: 15px 25px; overflow-y: auto; overflow-x: hidden;">
            <div id="div_grid" style="width: 100%; margin-top: 20px;">
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

<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
    
    parent.schFlag = 0;
    
    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");
    parent.$("#schDeptCnt").show();
    parent.$("#schRoutCnt").hide();
    parent.$("#MUMM_DEPT_CODE").val("");
    
    // 차트페이지에서 넘어온 경우
    var param = "${mummSctnSrvyDtaVO.DEPT_CODE}";
    
    if ( param != null && param != "" ) {
        
        parent.$("#MUMM_DEPT_CODE").val(param);    
        
    }
    
    var deptCode = parent.$("#MUMM_DEPT_CODE").val();
    
    var postData = {"DEPT_CODE" : deptCode};
    
    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/mumm/mummDeptCntStats.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: JSON.stringify(postData)
        ,ignoreCase: true
        ,colNames:["관리부서코드","관리부서명","GPCI","교통량/하부불량","기후", "기타"]
        ,colModel:[
            {name:'DEPT_CODE',index:'DEPT_CODE', align:'center', width: 100, sortable:false, hidden: true}
            ,{name:'DEPT_NM',index:'DEPT_NM', align:'center', width:100, sortable:false}
            ,{name:'GPCI',index:'GPCI', align:'center', width:100, sortable:false, formatter: fnFloat}
            ,{name:'DMG_CUZ_VMTC',index:'DMG_CUZ_VMTC', align:'center', width:100, sortable:false, formatter: fnFloat}
            ,{name:'DMG_CUZ_CLMT',index:'DMG_CUZ_CLMT', align:'center', width:100, sortable:false, formatter: fnFloat}
            ,{name:'DMG_CUZ_ETC',index:'DMG_CUZ_ETC', align:'center', width:100, sortable:false, formatter: fnFloat}
        ]
        ,async : false
        ,sortname: 'ROAD_GRAD'
        ,sortorder: "asc"
        ,rowNum: 100
        ,rowList: [100]
        ,viewrecords: true
        //,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) { }
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
    
    $("#gridArea").jqGrid('setGroupHeaders', {
        useColSpanStyle: true, 
        groupHeaders:[
            {startColumnName: 'DMG_CUZ_VMTC', numberOfColumns: 3, titleText: '파손원인'}
        ]   
    }); 
    
    var height = $(parent.window).height() - 300;
    
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
    
    fn_search(postData);
    
    $(window).resize(function(){
        
        var height = $(parent.window).height() - 300;
        
        COMMON_UTIL.cmInitGridSize('gridArea','div_grid', height);
    }); 
}); 

//검색 처리
function fn_search(postData) {
    
    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData:   JSON.stringify(postData)
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

// 그래프 보기
function fnShowChart() {
    
    COMMON_UTIL.cmMoveUrl('<c:url value="mumm/mummDeptCntStatsChart.do"/>');
    
}

// 엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/mumm/mummDeptCntStatsExcel.do'/>", "");
    }
}

function fnFloat(cellValue, options, rowObject) {
    
    // 0.xx 표현을 위한 formatter
    var nm = options.colModel.name;
    
    var val = rowObject.GPCI;
    return parseFloat(val).toFixed(2);
        
}
</script>

</body>
</html>