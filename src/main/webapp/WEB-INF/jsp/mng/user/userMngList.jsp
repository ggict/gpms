<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>사용자관리 </title>
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
<input type="hidden" name="DEPT_CODE" id="DEPT_CODE" value="" />
<input type="hidden" name="USER_SE_CODE" id="USER_SE_CODE" value="URSE0001" />
<!-- container start -->
<div class="container"> <!-- id="container" -->
    <div class="admin_content">
        <h2>사용자 관리</h2>
        <!--검색영역-->
        <ul class="admin_sch">
            <%-- <li><label>사용자 구분</label> <select id="USER_SE_CODE" onchange="fn_change_userSe(this.value)" name="USER_SE_CODE" class="select">
                    <option value="">전체</option>
                    <c:forEach items="${userSeList }" var="userSe">
                        <option value="${userSe.CODE_VAL }">${userSe.CODE_NM }</option>
                    </c:forEach>
            </select></li> --%>
            <li><label>사용자명</label> <input type="text" id="USER_NM" name="USER_NM" /></li>
            <li class="out"><label>업체</label> <select id="CO_NO" name="CO_NO" onchange="COMMON_UTIL.fn_set_value(this.value, 'CO_NO')"  onkeydown="fnCheckEnter(event);">
                    <option value="">전체</option>
                    <c:forEach items="${compList }" var="comp">
                        <option value="${comp.CO_NO }">${comp.CO_NM }</option>
                    </c:forEach>
            </select></li>
            <li class="out"><label>계약명</label> <input type="text"   onkeyup="COMMON_UTIL.fn_set_value(this.value, 'CNTRCT_NM')" onkeydown="fnCheckEnter(event);" />
                <a href="#" class="btn pri" onclick="javascript:fn_search();">검색</a>
            </li>
            

            <li class="in"><label>소속기관</label> <select id="DEPT_1" onchange="COMMON_UTIL.fn_change_dept('DEPT_1', 'DEPT_2', '3', 'DEPT_3');"  onkeydown="fnCheckEnter(event);">
                    <option value="">전체</option>
                    <c:forEach items="${deptCdList }" var="deptCd">
                        <option value="${deptCd.DEPT_CODE }">${deptCd.LOWEST_DEPT_NM }</option>
                    </c:forEach>
            </select>
            <select id="DEPT_2" onchange="COMMON_UTIL.fn_change_dept('DEPT_2', 'DEPT_3', '4');"  onkeydown="fnCheckEnter(event);">
                    <option value="">전체</option>
            </select>
            <select id="DEPT_3" onkeydown="fnCheckEnter(event);">
                    <option value="">전체</option>
            </select>
            <a href="#" class="btn pri" onclick="javascript:fn_search();">검색</a>
            </li>
        </ul>
        <div id="div_grid">
            <table class="adminlist" id="gridArea"></table>
            <div id="gridPager"></div>
        </div>
        <div class="btnbx">
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
    fnAdminMenuSelect("menu1", 0);

    // 시스템 Flag
    sFlag = "${sysUserVO.sFlag}";

    var postData = {"USE_AT":"Y"};

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/sysuser/selectSysUserListPage.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["USER_NO","USER_FLAG", "사용자ID","사용자명","소속기관","담당업무","생년월일", "연락처","이메일","수정자ID","수정자 일시", "비밀번호초기화"] //"구분","업체","승인권한여부"
        ,colModel:[
            {name:'USER_NO',index:'USER_NO', hidden: true}
            ,{name:'USER_FLAG',index:'USER_FLAG', hidden: true}
            /* ,{name:'USER_SE_NM',index:'USER_SE_NM', align:'center', width:50, sortable:true} */
            ,{name:'USER_ID',index:'USER_ID', align:'center', width:60, sortable:true}
            ,{name:'USER_NM',index:'USER_NM', align:'center', width:60, sortable:true}
            ,{name:'DEPT_NM',index:'DEPT_NM', align:'left', width:120, sortable:true}
            ,{name:'CHRG_JOB',index:'CHRG_JOB', align:'left', width:60, sortable:true}
            /* ,{name:'CNTRWK_CO_NM',index:'CNTRWK_CO_NM', align:'left', width:100, sortable:true} */
            ,{name:'BRTHDY',index:'BRTHDY', align:'center', width:60, sortable:false, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'CTTPC',index:'CTTPC', align:'center', width:60, sortable:false}
            ,{name:'EMAIL',index:'EMAIL', align:'left', width:80, sortable:false}
            /* ,{name:'CONFM_AUTHOR_AT',index:'CONFM_AUTHOR_AT', align:'center', width:70, sortable:true} */
            ,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'center', width:60, sortable:false, hidden: true}
            ,{name:'UPDT_DT',index:'UPDT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:80, sortable:false}
            ,{name:'btn_reset',index:'btn_reset', align:'center', width:50, sortable:false, formatter: fnCreatResetBtn}
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
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
                if( rowId != null ) {
                    // 상세 조회용 키 컬럼 변경 필수
                    var user_no = rowData["USER_NO"];
                    if(user_no!=undefined && user_no !=null && user_no !=""){
                        fn_view(rowId); // 대장 조회
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
        ,multiselect: false
        ,multiboxonly: false
        //,scroll: true
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);

    fn_search();

    fn_change_userSe("URSE0001");
});

//검색 처리
function fn_search() {
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
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

// 2018. 05. 10. JOY. 비밀번호 초기화 버튼 생성
function fnCreatResetBtn(cellValue, options, rowObject) {
    return "<a href='#' class='schbtn' onclick=\"fnResetPswd(" + rowObject.USER_NO +");\" >초기화</a>";
}

// 2018. 05. 10. JOY. 비밀번호 초기화
function fnResetPswd(userNo) {
    
    if ( confirm("해당 사용자의 비밀번호를 초기화하시겠습니까?") ) {
        
        var postData = {"USER_NO" : userNo };

        $.ajax({
            url : contextPath + 'api/sysuser/updateUserPswd.do'
            , type : 'post'
            , dataType: 'json'
            , contentType : 'application/json'
            , data : JSON.stringify(postData)
            , success : function(jdata) {

                alert("비밀번호가 [ qwer1234 ]로 초기화 되었습니다.");
                return;

            }
            , error: function(a, b, msg) {
                console.log(msg);
            }
        });
        
    }
    
}

//상세 조회
function fn_view(rowId) {
    if( $.type(rowId)=== "undefined" || rowId=="" )
         rowId = $("#gridArea").getGridParam( "selrow" );

    if( rowId != null ) {
        var rowData = $("#gridArea").getRowData(rowId);

        // 상세 조회용 키 컬럼 변경 필수
        var no = rowData["USER_NO"];
        var flag = rowData["USER_FLAG"];

        //window.open("<c:url value='/manage/sysuser/selectSysUser.do'/>?USER_NO="+no, '', 'width=650,height=700');
        COMMON_UTIL.cmWindowOpen('사용자 상세 정보', "<c:url value='/manage/sysuser/selectSysUser.do'/>?USER_NO="+no + "&USER_FLAG=" + flag, 540, 860, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
    }
    else
        alert('<spring:message code="warn.checkplz.msg" />');
}

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

//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}


</script>

</body>
</html>