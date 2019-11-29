<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>관리자 페이지 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="USER_NO" name="USER_NO" value=""/>
<input type="hidden" id="USER_SE_CODE" name="USER_SE_CODE" value="URSE0001"/>

<form id="frm" name="frm" method="post" action="">
<input type="hidden" name="DEPT_CODE" id="DEPT_CODE" value="" />
    <div class="container"><!-- id="container" -->
        <div class="admin_content">
            <h2>사용자 신청 관리</h2>
            <ul class="admin_sch">
                <%-- <li><label>사용자 구분</label>
                    <select id="USER_SE_CODE" onchange="fn_change_userSe(this.value)" name="USER_SE_CODE" class="select">
                        <option value="">선택</option>
                        <c:forEach items="${userSeList }" var="userSe">
                            <option value="${userSe.CODE_VAL }">${userSe.CODE_NM }</option>
                        </c:forEach>
                    </select>
                </li> --%>
                <li><label>사용자명</label>
                   <input type="text" id="USER_NM" name="USER_NM" onkeydown="fnCheckEnter(event);"/>
               </li>
               <li class="out"><label for="CO_NO">업체</label>
                    <select id="CO_NO" name="CO_NO" onchange="COMMON_UTIL.fn_set_value(this.value, 'CO_NO')">
                        <option value="">전체</option>
                        <c:forEach items="${compList }" var="comp">
                            <option value="${comp.CO_NO }">${comp.CO_NM }</option>
                        </c:forEach>
                    </select>
                    <label>계약명</label>
                     <input type="text" onkeyup="COMMON_UTIL.fn_set_value(this.value, 'CNTRCT_NM')" onkeydown="fnCheckEnter(event);"/>
                </li>
                <li class="in"><label for="DEPT_1">소속기관</label>
                    <select id="DEPT_1" onchange="COMMON_UTIL.fn_change_dept('DEPT_1', 'DEPT_2', '3', 'DEPT_3');"  onkeydown="fnCheckEnter(event);">
                        <option value="">전체</option>
                        <c:forEach items="${deptCdList }" var="deptCd">
                            <option value="${deptCd.DEPT_CODE }">${deptCd.LOWEST_DEPT_NM }</option>
                        </c:forEach>
                    </select>
                    <select id="DEPT_2" onchange="COMMON_UTIL.fn_change_dept('DEPT_2', 'DEPT_3', '4');"  onkeydown="fnCheckEnter(event);">
                        <option value="">전체</option>
                    </select>
                    <select id="DEPT_3"  onkeydown="fnCheckEnter(event);">
                        <option value="">전체</option>
                    </select>

                    <a href="#" class="btn pri posR" onclick="javascript:fn_search();">검색</a></li>
            </ul>

            <div id="div_grid">
                <table class="adminlist" id="gridArea"></table>
                <div id="gridPager"></div>
            </div>

            <div class="btnArea">
                <a href="#" class="btn pri" onclick="fn_view();">상세조회</a>
            </div>
        </div>
    </div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu1", 1);

    // 시스템 Flag
    sFlag = "${sysUserVO.sFlag}";

    var postData = {"USE_AT":"Y"};

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/sysuser/selectApplyUser.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["USER_NO","USER_FLAG", "사용자ID","사용자명","소속기관","담당업무","생년월일","연락처","이메일","신청 일시","승인여부"] //,"구분","업체"
        ,colModel:[
            {name:'USER_NO',index:'USER_NO', hidden: true}
            ,{name:'USER_FLAG',index:'USER_FLAG', hidden: true}
            /* ,{name:'USER_SE_NM',index:'USER_SE_NM', align:'center', width:50, sortable:true} */
            ,{name:'USER_ID',index:'USER_ID', align:'center', width:70, sortable:true}
            ,{name:'USER_NM',index:'USER_NM', align:'center', width:70, sortable:true}
            ,{name:'DEPT_NM',index:'DEPT_NM', align:'left', width:200, sortable:true}
            ,{name:'CHRG_JOB',index:'CHRG_JOB', align:'left', width:100, sortable:true}
            /* ,{name:'CNTRWK_CO_NM',index:'CNTRWK_CO_NM', align:'left', width:100, sortable:true} */
            ,{name:'BRTHDY',index:'BRTHDY', align:'center', width:70, sortable:false, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'CTTPC',index:'CTTPC', align:'center', width:100, sortable:false}
            ,{name:'EMAIL',index:'EMAIL', align:'left', width:100, sortable:false}
            ,{name:'CREAT_DT',index:'CREAT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:150, sortable: true}
            ,{name:'CONFM_AT',index:'CONFM_AT', align:'center', width:60, sortable:true, formatter: fnYNtoKr}
        ]
        ,async : false
        ,sortname: 'USER_NM'
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
            fn_view(rowId); // 대장 조회
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
        /* ,multiselect: true
        ,multiboxonly: true */
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

    fn_search();

    fn_change_userSe('URSE0001');
});

//검색 처리
function fn_search() {
    //체크박스 초기화
    $(".cbox").prop("checked", false);
    fn_get_deptCd();

    var postData = {"USE_AT":"Y"};

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData:   $("#frm").cmSerializeObject()
        ,mtype: "POST"
        ,loadComplete: function(data) {
            $("#usrCnt").text(data.records);
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

//상세 조회
function fn_view(rowId) {
    if( $.type(rowId)=== "undefined" || rowId=="" )
         rowId = $("#gridArea").getGridParam( "selrow" );

    if( rowId != null ) {
        var rowData = $("#gridArea").getRowData(rowId);
        var no = rowData["USER_NO"];

        //window.open("<c:url value='/manage/sysuser/selectSysUser.do'/>?USER_NO="+no, '', 'width=650,height=700');
        COMMON_UTIL.cmWindowOpen('사용자 상세 정보', "<c:url value='/manage/sysuser/selectSysUser.do'/>?USER_FLAG=" + rowData["USER_FLAG"] + "&USER_NO="+no, 540, 700, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
    }
    else
        alert('<spring:message code="warn.checkplz.msg" />');
}


//신청 승인
/* function fn_apply_confirm(){
    var seqList = "";
    var seqListArr = [];
    var obj = $("#gridArea");
    var idx = obj.jqGrid('getGridParam', 'selarrrow');

    if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
        alert("승인할 사용자를 선택해주세요");
        return false;
    }

    // SEQ List
    for(var i=0; i<idx.length; i++){
        var value = obj.jqGrid('getCell', idx[i], 'USER_NO' );
        seqListArr.push( value );
    }
        seqList = seqListArr.join(",");

    var msg = idx.length + "명의 사용자 신청 승인 처리를 진행하시겠습니까?";

    if(confirm(msg)){
        $.ajax({
            url: '<c:url value="/api/sysuser/applyConfirm.do"/>'
            ,data: {"USER_NO" : seqList}
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(data){
                alert( data.totCnt + "명의 사용자 신청 승인이 되었습니다.");
                fn_search();
            }
            ,error: function(a,b,msg){
                alert( "승인 처리에 문제가 발생하였습니다.");
                fn_search();
            }
        });
    }
} */

// 사용자 구분 변경에 따른 검색 항목 변경
function fn_change_userSe(val){
    fn_clear_val();

    $(".in").hide();
    $(".out").hide();

    switch(val){
        case "URSE0001":
            $(".in").show();

            $(".out").find("input").val("");
            $(".out").find("select").val("");
            $("#CNTRCT_NM").val("");
            $("#CO_NO").val("");
            break;
        case "URSE0002":
            $(".out").show();

            $(".in").find("input").val("");
            $(".in").find("select").val("");
            $("#DEPT_CODE").val("");
            break;
    }

}

//소속기관 값 입력
function fn_get_deptCd(){
    var value = "";
    var dept1 = $("#DEPT_1").val();
    var dept2 = $("#DEPT_2").val();
    var dept3 = $("#DEPT_3").val();

    if(dept3 != ""){
        value = dept3;
    }else if(dept2 != ""){
        value = dept2;
    }else {
        value = dept1;
    }

    $("#DEPT_CODE").val(value);

}

//값 초기화
function fn_clear_val(){
    $(".in").find("input,select").val("");
    $(".out").find("input,select").val("");
}

function fnYNtoKr(cellValue, options, rowObject) {

    if ( rowObject.CONFM_AT == "Y" ) {

        return "예";

    } else {

        return "아니오";

    }

    return "";

}


//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}

</script>
</body>
</html>