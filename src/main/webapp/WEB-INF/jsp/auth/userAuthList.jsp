<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>사용자관리 </title>
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	var postData = {"USE_AT":"Y"};
	var selRow;
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/userauth/selectUserAuthListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["USER_NO","사용자명","사용자ID", "생년월일", "권한ID", "권한"]
	   	,colModel:[
			{name:'USER_NO',index:'USER_NO', hidden: true}
			,{name:'USER_NM',index:'USER_NM', align:'center', width:70, sortable:true}
			,{name:'USER_ID',index:'USER_ID', align:'center', width:70, sortable:true}
			,{name:'BRTHDY',index:'BRTHDY', align:'center', width:70, sortable:false}
			,{name:'AUTHOR_ID',index:'AUTHOR_ID', hidden: true, width:70, sortable:false}
			,{name:'AUTHOR_NM',index:'AUTHOR_NM', align:'center', width:70, sortable: true,
			       editable: true, edittype:"select", editoptions:{value:"ROLE_USER:도청사용자;ROLE_USER_EXT:외부사용자;ROLE_ADMIN:관리자;ROLE_USER_SGG:시군구 사용자"}}
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
	   	,onSelectRow: function(rowId) {		// 클릭 처리
	   		/* if(rowId){
				jQuery('#gridArea').jqGrid('restoreRow',rowId);
			} */
	   		 //if(rowId && rowId!==selRow){
				jQuery('#gridArea').jqGrid('restoreRow',selRow);
				jQuery('#gridArea').jqGrid('editRow',rowId,true);
				
				selRow=rowId;
				fn_saveAuthor(selRow);
			//} 
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
	
}); 

//검색 처리
function fn_search() {
	/* fn_get_deptCd(); */
	
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

function fn_saveAuthor(selRow){
	var rowData = $("#gridArea").getRowData(selRow);
	var userNo = rowData["USER_NO"];
	var author_id = rowData["AUTHOR_ID"];
	
	$("#"+selRow+"_AUTHOR_NM").change(function(){
		if(author_id == "" || author_id == null){
			//기존 권한이 비어있을 경우 추가
			var action = '<c:url value="/api/userauth/addUserAuth.do"/>';
		}else{
			//기존 권한이 있을 경우 수정
			var action = '<c:url value="/api/userauth/updateUserAuth.do"/>';
		}
		
		var authorId = $("#"+selRow+"_AUTHOR_NM option:selected" ).val();
		var data = {"USER_NO" : userNo, "AUTHOR_ID" : authorId};
		
		$.ajax({
			url: action
			,data: JSON.stringify(data)
			,type: 'post'
			,contentType : "application/json"
			,success: function(data){				
				alert("권한이 수정되었습니다.");
				fn_search();
			}
			,error: function(a,b,msg){
				alert( "권한수정에 문제가 발생하였습니다.");
				fn_search();
			}
		});
	});
}

</script>
</head>

<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<!-- <input type="hidden" name="DEPT_CODE" id="DEPT_CODE" value="" /> -->
<div style="margin: 120px 20px 0 20px;">
	<div class="cont_TitBx">
		<p>사용자 권한</p>
	</div>
	<!--검색영역-->
	<div class="cont_search">
		<div class="Con_Bt">
			<!-- <img src="/images/btn_search3.gif" alt="리셋" onclick="javascript:cmFormReset('frm');" /> -->
			<img src="<c:url value='/images/btn_search3.gif' />" alt="검색" onclick="javascript:fn_search();" />
		</div>
		<dl style="width:1200px;">
			<dt class="CTx2">사용자명</dt>
			<dt class="W180">
				<input type="text" id="SCH_USER_NM" name="SCH_USER_NM" class="MX_50 CS_25 input" />
			</dt>
		</dl>
	</div>
		<div style="margin-top: 120px; width: 100%;">
			<div>&nbsp;</div>
			<div id="div_grid" style="width:100%; height:60%;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
		</div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>

</html>