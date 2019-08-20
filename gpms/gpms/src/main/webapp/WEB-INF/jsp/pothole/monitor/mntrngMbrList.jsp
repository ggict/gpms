<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>모니터링단원 관리 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<!-- container start -->
<div id="container">
    <div class="admin_content">
        <h2>모니터링단원 관리</h2>
        <!--검색영역-->
        <ul class="admin_sch">
            <li>
                <label>차량번호</label>
                <input type="text" id="VHCLE_NO" name="VHCLE_NO"  class="MX_50 CS_25 input" onkeydown="fnCheckEnter(event);" />
            </li>
            <li>
                <label>사업자명</label>
                <input type="text" id="BSNM_NM" name="BSNM_NM"  class="MX_50 CS_25 input" onkeydown="fnCheckEnter(event);" />
            </li>
            <li>
                <label>지역</label>
                <select id="DEPT_CODE" name="DEPT_CODE"  class="select sBx120" style="width: 150px; margin-left: 5px;" onkeydown="fnCheckEnter(event);">
                    <option value="">===== 전체 =====</option>
                    <c:forEach var="selectData" items="${ RegionList }">
                       <option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
                    </c:forEach>
                </select>
            </li>
            <li class="fr">
                <a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a>
            </li>
        </ul>
        <div id="div_grid" >
            <table class="adminlist" id="gridArea"></table>
            <div id="gridPager"></div>
        </div>
        <div class="btnbx">
            <a href="#" class="schbtn" onclick="fn_mntEntrstEdit();">모니터링단원 위촉여부 변경</a>
            <a href="#" class="schbtn" onclick="fn_mntRegist();">모니터링단원 등록</a>
        </div>
    </div>

</div>
</form>

<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu1", 5);

    var postData = {"USE_AT":"Y"};

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'monitor/selectMntrngMbrList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:[ "DEPT_CODE", "지역", "사업자명", "차량번호", "주소", "연락처", "미터기제작사", "모델명", "카드제작사", "장착여부", "위촉여부", "위촉일자", "비고" ]
        ,colModel:[
            {name:'DEPT_CODE',index:'DEPT_CODE', hidden: true}
            ,{name:'LOWEST_DEPT_NM',index:'LOWEST_DEPT_NM', align:'center', width:30, sortable:true}
            ,{name:'BSNM_NM',index:'BSNM_NM', align:'center', width:45, sortable:true}
            ,{name:'VHCLE_NO',index:'VHCLE_NO', align:'center', width:60, sortable:true}
            ,{name:'ADRES',index:'ADRES', align:'center', width:140, sortable:false}
            ,{name:'CTTPC',index:'CTTPC', align:'center', width:55, sortable:false}
            ,{name:'METER_MAKR',index:'METER_MAKR', align:'center', width:50, sortable:true}
            ,{name:'MODEL_NM',index:'MODEL_NM', align:'center', width:50, sortable:true}
            ,{name:'CARD_MAKR',index:'CARD_MAKR', align:'center', width:50, sortable:true}
            ,{name:'MNTNG_AT',index:'MNTNG_AT', align:'center', width:40, sortable:true}
            ,{name:'ENTRST_AT',index:'ENTRST_AT', align:'center', width:40, sortable:true}
            ,{name:'ENTRST_DE',index:'ENTRST_DE', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd" }, width:50, sortable:true}
            ,{name:'RM',index:'RM', align:'center', width:90, sortable:false}
        ]
        ,async : false
        ,sortname: 'BSNM_NM'
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
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
                if( rowId != null ) {
                    // 상세 조회용 키 컬럼 변경 필수
                	var CTTPC = rowData["CTTPC"];
                    if(CTTPC!=undefined && CTTPC !=null && CTTPC !=""){
                        fn_view(rowId); // 상세 조회
                    }
                }
            }
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
        ,multiselect: true
        ,multiboxonly: false
        //,scroll: true
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

    fn_search();

});

//검색 처리
function fn_search() {
    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData:   $("#frm").cmSerializeObjectExChkbox()
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}


// 모니터링단원 위촉여부 변경
function fn_mntEntrstEdit(){
    var rowId = $("#gridArea").getGridParam("selarrrow");
    if(rowId.length < 1){
        alert("위촉여부를 변경할 단원을 선택해주세요.");
        return;
    }
    COMMON_UTIL.cmWindowOpen('모니터링단원 위촉여부 변경', "<c:url value='/monitor/mntrngMbrEntrust.do'/>", 240, 160, false, $("#wnd_id").val(), 'center');



    /*
    $("#dvMntEntrstEdit").dialog({
        close : function() {
            // x 버튼으로 창 복원 추가 (2015.12.18)
            try {
                wWindowShowAll();
                if(option.clearMap == undefined || option.clearMap){
                    $("#mCtrlClear").click();
                }
                bottomOpen();
            } catch(e){}
        },
        position: {
        },
        width: "220px"
    }).dialog("open");
     */

}


// 모니터링단원 등록
function fn_mntRegist(){
    COMMON_UTIL.cmWindowOpen('모니터링단원 등록', "<c:url value='/monitor/mntrngMbrView.do'/>", 540, 560, false, $("#wnd_id").val(), 'center');
}


// 상세조회 (수정/삭제)
function fn_view(rowId) {
    if( $.type(rowId)=== "undefined" || rowId=="" )
         rowId = $("#gridArea").getGridParam( "selrow" );

    if( rowId != null ) {
        var rowData = $("#gridArea").getRowData(rowId);

        // 상세 조회용 키 컬럼 변경 필수
        var no = rowData["VHCLE_NO"];
        var nm = rowData["BSNM_NM"];
        var ph = rowData["CTTPC"];

        COMMON_UTIL.cmWindowOpen('모니터링단원 수정', "<c:url value='/monitor/mntrngMbrView.do'/>?VHCLE_NO="+no+"&BSNM_NM="+nm+"&CTTPC="+ph, 540, 560, false, $("#wnd_id").val(), 'center');
    }
    else
        alert('<spring:message code="warn.checkplz.msg" />');
}


// enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}



</script>


<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>

<!-- 공통 (END)-->



</body>
</html>