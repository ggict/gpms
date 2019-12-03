<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>시스템메뉴 접근권한 </title>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div id="container">
	<div class="admin_content">
	<h2>시스템메뉴 권한관리</h2>
		<form:form commandName="roleMenuVO" id="rolemenuform">
			<ul class="admin_sch">
			    <li>
			    	<label for="SCH_AUTH">시스템메뉴 접근권한 그룹</label> 
			    	<form:select path="SCH_AUTH">
			    		<c:forEach var="codeInfo" items="${auth_list}">
							<form:option value="${codeInfo.CODE_VAL}">${codeInfo.CODE_NM}</form:option>
						</c:forEach>
			    	</form:select>
			    	<a href="#" class="btn pri posR" onclick="javascript:fnSearch();">검색</a>
			    </li>
			</ul>
		</form:form>
		<div>
		    <div class="tbc" style="width:550px" id="div_gridN">
            	<h4>권한 미지정 메뉴</h4>
            	<table id="gridAreaN" class="schtblist" summary="권한 미지정 메뉴 목록을 조회합니다."></table>
				<!-- <div id="gridPagerN"></div> -->
			</div>
            <div class="arrowbx" style="width:100px;height:400px;">
                <a href="#" class="arrowbtn"><img src="<c:url value='/images/ic_a1.png'/>" alt="권한 지정" title="권한 지정" onclick="fnauthAdd()"/></a>
                <a href="#" class="arrowbtn"><img src="<c:url value='/images/ic_a2.png'/>" alt="권한 지정 해제" title="권한 지정 해제" onclick="fnauthDel()" /></a>
            </div>
            <div class="tbc" style="width:550px" id="div_gridY">
            	<h4>권한 지정 메뉴</h4>
            	<table id="gridAreaY" class="schtblist" summary="권한 지정 메뉴 목록을 조회합니다."></table>
				<!-- <div id="gridPagerY"></div>			 -->
			</div>
		</div>
	</div>
	<!-- 공통 (START)-->
	<%@ include file="/include/common.jsp" %>
	<!-- 공통 (END)-->
		
</div>
<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {
    
    // 메뉴 select
    fnAdminMenuSelect("menu2", 1);
    
	var rowData;
	
	fnInitClCodeGrid();
	fnInitCodeGrid();
}); 
function fnInitClCodeGrid(){
	//검색 목록 그리드 구성
	$("#gridAreaN").jqGrid({
		url: '<c:url value="/"/>'+'api/rolemenu/selectRoleMenuListNPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["ROLE_NO","메뉴ID", "메뉴명", "사용 여부", "수정일"] //"ROLE_NO","메뉴ID", "메뉴명", "생성자", "사용</br>여부", "수정자", "수정일"
	   	,colModel:[
			{name:'ROLE_NO',index:'ROLE_NO', hidden: true}
			,{name:'MENU_ID',index:'MENU_ID', align:'center', width:70}
			,{name:'MENU_NM',index:'MENU_NM', align:'left', width:150}
			/* ,{name:'CRTR_NO',index:'CRTR_NO', align:'center', width:50} 
			,{name:'UPDUSR_NO',index:'UPDUSR_NO',align:'center', width:50} */
			,{name:'USE_AT',index:'USE_AT',align:'center',width:50, formatter: fnYNtoKr}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center',width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd" }}
	   	]
		,async : false
	   	,sortname: ''
	    ,sortorder: ""
	   	,rowNum: 999999
	   	//,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	//,pager: '#gridPagerN'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
	   	,onSelectRow: function(rowId) {		// 클릭 처리
		}
		,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridAreaN");
	   		 	$grid.jqGrid('setSelection', rowid, false);
	   			return false;
	   		} 
	   	    return true;
	   	}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: true
		,multiboxonly: false
		,scroll: true
	});//.navGrid('#gridPagerN',{edit:false,add:false,del:false,search:false,refresh:false});
	
	COMMON_UTIL.cmInitGridSize('gridAreaN','div_gridN', 500);
	
	fnSearchN();
}

function fnInitCodeGrid(){
	//검색 목록 그리드 구성
	$("#gridAreaY").jqGrid({
		url: '<c:url value="/"/>'+'api/rolemenu/selectRoleMenuListYPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["ROLE_NO","메뉴ID", "메뉴명", "사용 여부", "수정일"] //"ROLE_NO","메뉴ID", "메뉴명", "생성자", "삭제</br>여부", "수정자", "수정일"
	   	,colModel:[
			{name:'ROLE_NO',index:'ROLE_NO', hidden: true}
			,{name:'MENU_ID',index:'MENU_ID', align:'center', width:70}
			,{name:'MENU_NM',index:'MENU_NM', align:'left', width:150}
			/* ,{name:'CRTR_NO',index:'CRTR_NO', align:'center', width:50, hidden: true}
			,{name:'UPDUSR_NO',index:'UPDUSR_NO',align:'center', width:50, hidden: true} */
			,{name:'USE_AT',index:'USE_AT',align:'center',width:50, formatter: fnYNtoKr}
			,{name:'UPDT_DT',index:'UPDT_DT', align:'center',width:50, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd" }}
	   	]
		,async : false
	   	,sortname: 'MENU_ID'
	    ,sortorder: "asc"
	   	,rowNum: 9999999
	   	//,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	//,pager: '#gridPagerY'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
	   	,onSelectRow: function(rowId) {		// 클릭 처리
		}
		,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridAreaY");
	   		 	$grid.jqGrid('setSelection', rowid, false);
	   			return false;
	   		} 
	   	    return true;
	   	}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: true
		,multiboxonly: false
		,scroll: true
	});//.navGrid('#gridAreaY',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridAreaY','div_gridY', 500);
	
	fnSearchY();
}

//검색 처리
function fnSearchN() {
	$("#gridAreaN").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:$("#rolemenuform").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridAreaN', '미지정된 권한이 없습니다.', data.records);
	   	}
	}).trigger("reloadGrid");
}

//검색 처리
function fnSearchY() {
	$("#gridAreaY").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:$("#rolemenuform").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridAreaY', '지정된 권한이 없습니다.', data.records);
	   	}
	}).trigger("reloadGrid");
}

//검색
function fnSearch(){
	fnSearchN();
	fnSearchY();
}

//권한추가
function fnauthAdd(){
	var rowId = $("#gridAreaN").getGridParam("selarrrow");
	
	if(rowId.length < 1){
		alert("권한을 추가할 메뉴를 선택해주세요.");
		return;
	}
	
	for(var i=0; i<rowId.length; i++){
		if( rowId[i] != null ) {
			var rowData = $("#gridAreaN").getRowData(rowId[i]);
			// 상세 조회용 키 컬럼 변경 필수
			var menuId = rowData["MENU_ID"];
			var authId = $("#SCH_AUTH").val();
			var dataList = {"MENU_ID" : menuId, "AUTHOR_ID" : authId};
			
			var action = '<c:url value="/api/rolemenu/addRoleMenu.do"/>';
			
			$.ajax({
		        url: action,
		        contentType: 'application/json',
		        data: JSON.stringify(dataList),
		        dataType: "json",
		        /* cache: false, */
		        type: 'POST',
		       /*   processData: false, */
		        success: function (jdata) {
		        	
		        },
		        error: function () {
		            alert("메뉴권한 추가 시 오류가 발생하였습니다.");
		            return;
		        }
		    });
		}else{
			alert("추가할 메뉴를 선택하시고 다시 시작하시기 바랍니다.");
		}
	}
	
	//success 메세지 한번만 나오게 for문밖에 alert호출
	alert("메뉴권한을 추가하였습니다.");
	fnSearch();
    return;
}

//권한삭제
function fnauthDel(){
	var rowId = $("#gridAreaY").getGridParam("selarrrow");
	
	if(rowId.length < 1){
		alert("권한을 삭제할 메뉴를 선택해주세요.");
		return;
	}
	
	for(var i=0; i<rowId.length; i++){
		if( rowId[i] != null ) {
				var rowData = $("#gridAreaY").getRowData(rowId[i]);
				// 상세 조회용 키 컬럼 변경 필수
				var menuId = rowData["MENU_ID"];
				var authId = $("#SCH_AUTH").val();
				var dataList = {"MENU_ID" : menuId, "AUTHOR_ID" : authId};
				
				var action = '<c:url value="/api/rolemenu/deleteRoleMenu.do"/>';
				
				$.ajax({
			        url: action,
			        contentType: 'application/json',
			        data:JSON.stringify(dataList),
			        dataType: "json",
			        cache: false,
			        type: 'POST',
			        processData: false,
			        success: function (jdata) {
			        },
			        error: function () {
			            alert(menuId + " 메뉴권한 삭제 시 오류가 발생하였습니다.");
			            return;
			        }
			    });
		}else{
			alert("삭제할 메뉴를 선택하시고 다시 시작하시기 바랍니다.");
			return;
		}
	}
	
	//success 메세지 한번만 나오게 for문밖에 alert호출
	alert("메뉴권한을 삭제하였습니다.");
	fnSearch();
	return;
	
}

function fnYNtoKr(cellValue, options, rowObject) {
    
    if ( rowObject.USE_AT == "Y" ) {
        
        return "예";
        
    } else {
        
        return "아니오";
        
    }
    
    return "";
    
}
</script>
</body>
</html>