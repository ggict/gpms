<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>부서관리 </title>
<!--
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
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
	<div id="container">
		<div class="admin_content">
			<h2>경기도 조직 부서 관리</h2>
			<!--검색영역-->
			<ul class="admin_sch">
				<li>
					<label>부서</label>
					<select id="DEPT_1" alt="부서" class="input" onchange="COMMON_UTIL.fn_change_dept('DEPT_1', 'DEPT_2', '3', 'DEPT_3');" onkeydown="fnCheckEnter(event);">
		        		<option value="">====== 전체 ======</option>
		        		<c:forEach items="${deptCdList }" var="deptCd">
		        			<option value="${deptCd.DEPT_CODE }">${deptCd.LOWEST_DEPT_NM }</option>
		        		</c:forEach>
		        	</select>
		        	<select id="DEPT_2" style="width: 190px;" class="input" onchange="COMMON_UTIL.fn_change_dept('DEPT_2', 'DEPT_3', '4');" onkeydown="fnCheckEnter(event);">
			        	<option value="">====== 전체 ======</option>
			        </select>
			        <select id="DEPT_3" style="width: 190px;" class="input" onkeydown="fnCheckEnter(event);">
			        	<option value="">====== 전체 ======</option>
		        	</select>
				</li>
				<li>
					<label>부서 명</label>
					<input type="text" name="DEPT_NM" id="DEPT_NM" class="MX_50 CS_25 input" onkeypress="if(event.keyCode==13) {fn_search(); return false;}" onkeydown="fnCheckEnter(event);" />
				</li>
				<li class="fr"><a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a></li>
			</ul>

			<div id="div_grid" style="width: 100%;">
				<table class="adminlist" id="gridArea"></table>
				<div id="gridPager"></div>
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
    fnAdminMenuSelect("menu1", 2);

    var postData = {"USE_AT":"Y"};

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/dept/selectDeptList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: JSON.stringify( $("#frm").cmSerializeObject())
        ,postData: $("#frm").cmSerializeObject()
        ,ignoreCase: true
        ,colNames:["부서명","행정 코드","연락처","팩스 번호"]
        ,colModel:[
            {name:'DEPT_NM',index:'DEPT_NM', align:'left', width:200, sortable: true}
            ,{name:'ADSTRD_CODE',index:'ADSTRD_CODE', align:'center', width:70, sortable: true}
            ,{name:'TEL_NO',index:'TEL_NO', align:'center', width:70, sortable:false}
            ,{name:'FAX_NO',index:'FAX_NO', align:'center', width:70, sortable:false}
        ]
        , sortname: "DEPT_NM"
        , sortorder: "asc"
        ,async : false
        ,rowNum: 50
        ,rowList: [20,50,100,500]
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
        ,loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
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

//부서 값 입력
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

//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}

</script>
</body>
</html>