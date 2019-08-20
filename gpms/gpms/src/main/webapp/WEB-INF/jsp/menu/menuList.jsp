<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>시스템메뉴 </title>
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
 
</head>

<body id="wrap">
<!-- 공통 (START)-->
<%@ include file="/include/topMenu.jsp" %>
<!-- 공통 (END)-->
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="UPPER_MENU_ID" name="UPPER_MENU_ID" value="<c:out value="${param.UPPER_MENU_ID}"/>"/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div id="container">
 <div class="admin_content">
		<h2>${screen_title}</h2>
	<!--검색영역-->
		<ul class="admin_sch">
			<li><label>시스템구분</label>
				<select name="SCH_MENU_D" id="SCH_MENU_D" class="select sBx120" style="width: 150px; margin-left: 5px;">
					<option value="">===== 전체 =====</option>
					<c:forEach var="codeInfo" items="${codesSYSM}">
						<option value="${codeInfo.CODE_VAL}">${codeInfo.CODE_NM}</option>
					</c:forEach>
				</select>
			</li>
			<li><label>상위메뉴</label>
				<select name="SCH_MENU_S" id="SCH_MENU_S" class="select sBx120" style="width: 150px; margin-left: 5px;">
					<option value="">===== 전체 =====</option>
					<c:forEach var="selectData" items="${menu_s_list}">
						<option value="${selectData.MENU_ID}"
							<c:if test = "${selectData.CODE_VAL == menuVO.SYS_CODE}"> selected="selected" </c:if>>${selectData.MENU_NM}</option>
					</c:forEach>
				</select>
			</li>
			<li><label>메뉴명</label>
				<input type="text" name="MENU_NM" id="MENU_NM" value="" class="MX_50 CS_25 input" />
			</li>
			<li><label>URL</label>
				<input type="text" name="SCH_URL" id="SCH_URL" value="" class="MX_50 CS_25 input" />
			</li>
			<li class="fr"><a href="#" class="schbtn dpb" onclick="javascript:fnSearch();">검색</a></li>
		</ul>
				
		<div id="div_grid" style="width: 100%;">
			<table class="adminlist" id="gridArea"></table>
			<div id="gridPager"></div>
		</div>
		
	<div class="btnbx">
		<a href="#" onclick="fn_menuDadd();" class="schbtn">메뉴추가</a>
		<a href="#" onclick="fn_menuSadd();" class="schbtn">하위메뉴추가</a>
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
    
    // 메뉴 select
    fnAdminMenuSelect("menu2", 0);
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/menu/selectMenuListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["메뉴ID", "상위메뉴ID", "메뉴명", "URL", "메뉴 설명", "사용여부", "삭제여부", "수정자ID", "수정일시", "생성자번호", "생성일시","시스템코드"]
	   	,colModel:[
	   	    {name:'MENU_ID',index:'MENU_ID', align:'center', width:50, sortable:false}   
		   ,{name:'UPPER_MENU_ID',index:'UPPER_MENU_ID', align:'center', width:60, sortable:false}
		   ,{name:'MENU_NM',index:'MENU_NM', width:80, sortable:false}
		   ,{name:'URL',index:'URL', align:'left', width:120, sortable:false}
		   ,{name:'MENU_DC',index:'MENU_DC', align:'left', width:150, sortable:false}
		   ,{name:'USE_AT',index:'USE_AT', align:'center', width:40, sortable:false, formatter: fnYNtoKr}
		   ,{name:'DELETE_AT',index:'DELETE_AT', align:'center', width:10, sortable:false, hidden:true}
		   ,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'center', width:50, sortable:false, hidden:true}
		   ,{name:'UPDT_DT',index:'UPDT_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:70, sortable:false}
		   ,{name:'CRTR_NO',index:'CRTR_NO', align:'center', width:10, sortable:false, hidden:true}
		   ,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:30, sortable:false, hidden:true}
		   ,{name:'SYS_CODE',index:'SYS_CODE', align:'center', width:50, sortable:false, hidden:true}
	   	]
		,async : false
		,sortname: 'MENU_ID'
	    ,sortorder: "asc" 
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnView(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
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
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}	   			
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#frm").cmSerializeObject();
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		,scroll: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 500);
	
	fnSearch();
}); 

//검색 처리
function fnSearch() {
	var postData = {"USE_AT":"Y"};
	//alert(JSON.stringify( $("#frm").cmSerializeObject()));
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#frm").cmSerializeObject())  
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

//상세 조회
function fnView(rowId) {
	if( $.type(rowId) === "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var menuId = rowData["MENU_ID"];
		
		COMMON_UTIL.cmWindowOpen('시스템 메뉴 관리', "<c:url value='/manage/menu/selectMenu.do'/>?MENU_ID="+menuId, 550, 400, false, $("#wnd_id").val(), 'center');
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}

//---------------------------
//처리 후 callback 함수들 (필수)
//---------------------------
function fnDeleteCallBack() {
	// 목록 화면 재검색
	try {
		// 오프너 윈도우 추출
		COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearch();
		// 현재 윈도우 닫기
		COMMON_UTIL.cmWindowClose( $('#wnd_id').val() );
	}catch(E) {alert(E);}
}


$("#SCH_MENU_D").change(function(){
	fn_selectMenuList();//하위 메뉴조회
});


//메뉴리스트 조회
function fn_selectMenuList(){
	$("#SCH_MENU_S").find('option').remove(); //데이타 제거
	$("#SCH_MENU_S").append("<option value=''>전체</option>");
	
	$.ajax({
		type: "get",
		dataType: "xml",
		data: "param=" + $("#SCH_MENU_D").val(),
		url: contextPath+'api/menu/selectMenuNmList.do',
		success: function(xml) {
			if($(xml).find("code").find("item").length > 0) {
				//loop
				$(xml).find("code").find("item").each(function() {
					var code_value = $(this).find("code_value").text();
					var code_name = $(this).find("code_name").text();

					$("#SCH_MENU_S").append("<option value='"+code_value+"'>"+code_name+"</option>");
				});  
			}
			
			try {
				$("#SCH_MENU_S").val(selected_value);
			} catch(E) {}
		},
		error: function(xhr, status, error) {
			alert(status);
			alert(error);
		}
	});
}

//추가
function fn_menuDadd() {
	var page = "<c:url value='/manage/menu/addMenuView.do'/>";
	COMMON_UTIL.cmWindowOpen('시스템 메뉴 관리', page, 600, 255, false, $("#wnd_id").val(), 'center');
}

function fn_menuSadd(){
	var rowId = $("#gridArea").getGridParam( "selrow" );
	if( rowId != null ) { 
		var rowData = $("#gridArea").getRowData(rowId);
		// 상세 조회용 키 컬럼 변경 필수
		var menuId = rowData["MENU_ID"];
		var sysCode = rowData["SYS_CODE"];
		var page = "<c:url value='/manage/menu/addMenuView.do' />"+"?UPPER_MENU_ID="+menuId+"&SYS_CODE="+sysCode;
		COMMON_UTIL.cmWindowOpen('시스템 메뉴 관리', page, 600, 255, false, $("#wnd_id").val(), 'center');
	}
	else{
		alert('<spring:message code="warn.checkplz.msg" />');
	}
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