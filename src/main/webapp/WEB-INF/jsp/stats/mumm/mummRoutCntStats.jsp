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
	    <h3>포장상태 평가 노선별 통계</h3>
	    <p class="location">
	        <span>통계</span>
	        <span>포장상태 평가</span>
	        <strong>노선별 통계</strong>
	    </p>
        
        <div class="btnbx mb10" style="margin-right: 25px;">
            <a href="#" onclick="fnShowChart();" class="schbtn" style="float: right;">그래프 보기</a> &nbsp;
            <a href="#" onclick="fnExcel();" class="schbtn" style="float: right; margin-right: 5px;">엑셀저장</a>
        </div>
	    <div class="cont_ListBx" style="margin: 15px 25px; overflow-y: auto; overflow-x: hidden;">
		    <div id="div_grid">
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
    parent.$("#schRoutCnt").show();
    parent.$("#schDeptCnt").hide();
    parent.$("#MUMM_ROAD_GRAD").val("");
    parent.$("#MUMM_ROAD_NO").val("");
    parent.$("#MUMM_ROAD_NAME").val("");
    
    // 차트페이지에서 넘어온 경우
    var param1 = "${mummSctnSrvyDtaVO.ROAD_GRAD}";
    var param2 = "${mummSctnSrvyDtaVO.ROAD_NO}";
    var param3 = "${mummSctnSrvyDtaVO.ROAD_NAME}";
    
    if ( param1 != null && param1 != "" && param1 != undefined ) {
        
        parent.$("#MUMM_ROAD_GRAD").val(param1);    
        
    }
    
    if ( param2 != null && param2 != "" && param2 != undefined ) {
        
        parent.$("#MUMM_ROAD_NO").val(param2);    
        
    }
    
    if ( param3 != null && param3 != "" && param3 != undefined ) {
        
        parent.$("#MUMM_ROAD_NAME").val(param3);    
        
    }
    
    var roadGrad = parent.$("#MUMM_ROAD_GRAD").val();
    var roadNo = parent.$("#MUMM_ROAD_NO").val();
    
    var postData = {"ROAD_GRAD" : roadGrad, "ROAD_NO" : roadNo};
    
    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/mumm/mummRoutCntStatsGPCI.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: JSON.stringify(postData)
        ,ignoreCase: true
        ,colNames:["도로등급","노선명","노선번호","GPCI","교통량/하부불량","기후", "기타"]
        ,colModel:[
            {name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width: 100, sortable:false, formatter: fnRoadGrade}
            ,{name:'ROAD_NM',index:'ROAD_NM', align:'center', width:100, sortable:false}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:100, sortable:false}
            ,{name:'GPCI',index:'GPCI', align:'center', width:100, sortable:false}
            ,{name:'DMG_CUZ_VMTC',index:'DMG_CUZ_VMTC', align:'center', width:100, sortable:false}
            ,{name:'DMG_CUZ_CLMT',index:'DMG_CUZ_CLMT', align:'center', width:100, sortable:false}
            ,{name:'DMG_CUZ_ETC',index:'DMG_CUZ_ETC', align:'center', width:100, sortable:false}
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
    
    COMMON_UTIL.cmMoveUrl('<c:url value="mumm/mummRoutCntStatsChart.do"/>');
    
}

// 엑셀 다운로드
function fnExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/mumm/mummRoutCntStatsExcel.do'/>", "");
    }
}

function fnRoadGrade(cellValue, options, rowObject) {
    
    var nm = options.colModel.name;
    
    if ( nm == "ROAD_GRAD" ) {
        
        if ( rowObject.ROAD_GRAD == "RDGD0001" ) {
            
            return "국지도";
            
        } else if ( rowObject.ROAD_GRAD == "RDGD0002" ) {
            
            return "지방도";
            
        }
        
    }
    
    return "";
    
}
</script>

</body>
</html>