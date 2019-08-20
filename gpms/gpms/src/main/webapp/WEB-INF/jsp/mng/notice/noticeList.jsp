<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>공지사항관리 </title>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javascript">

//페이지 로딩 초기 설정
$( document ).ready(function() {
    
    // 메뉴 select
    fnAdminMenuSelect("menu5");
	
	var postData = {"USE_AT":"Y"};
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/notice/selectNoticeListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["POS","번호","제목","작성자","등록일","첨부","조회"]
	   	,colModel:[
			{name:'POS',index:'POS', align:'center', width:30, sortable:true, hidden:true}
			,{name:'SEQ_NO',index:'SEQ_NO', align:'center', width:30, sortable:true, hidden:true}
			,{name:'SJ',index:'SJ', align:'left', width:200, sortable:true}
			,{name:'WRTER',index:'WRTER', align:'center', width:70, sortable:true}
			,{name:'REGIST_DT',index:'REGIST_DT', align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd" }, width:50, sortable:true}
			,{name:'btn_down',index:'btn_down', align:'center', width:50, sortable:false, formatter: fn_create_btn}
			,{name:'RDCNT',index:'RDCNT', align:'center', width:30, sortable:true}
	   	]
		,async : false
	   	,sortname: 'REGIST_DT'
	    ,sortorder: "desc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fn_view(rowId);	// 대장 조회
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
	
	fnSearch();
	
}); 

//검색 처리
function fnSearch() {
	
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

//상세 조회
function fn_view(rowId) {
	if( $.type(rowId)=== "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );
		
	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		
		// 상세 조회용 키 컬럼 변경 필수
		var no = rowData["SEQ_NO"];
		
		COMMON_UTIL.cmWindowOpen('공지사항 상세 정보', "<c:url value='/api/notice/selectNoticeUpdate.do'/>?SEQ_NO="+no, 500, 550, false, $("#wnd_id").val(), 'center');
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}
 
//신규 등록 화면 이동
 function fnWrite() {
 	COMMON_UTIL.cmWindowOpen( "공지사항 등록", "<c:url value='/notice/noticeRegist.do'/>", 500, 550, false, $("#wnd_id").val(), 'center');
 }
 
//위치이동 버튼 생성
 function fn_create_btn(cellValue, options, rowObject) {
	var result='';
 	 
	if(rowObject.POS == null || rowObject.POS == ''){
	 	result +='';
	}else{
		result += "<a href='#' onclick=\"fn_file_down('" + rowObject.POS + "');\"><img src='" + contextPath +"/images/ic_download.png' alt='첨부파일' title='첨부파일' /></a>"
	}
	 
 	return result;
 }
 
 function fn_file_down(val){
		COMMON_UTIL.cmMoveUrl("attachfile/downloadFile.do?FILE_NO="+val);
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
<!-- container start -->
<div id="container">
	<div class="admin_content">
		<h2>공지사항관리</h2>
		<!--검색영역-->
		<ul class="admin_sch">
			<li>
				<label>구분</label>
					<select id="SCH_SJCN" name="SCH_SJCN" class="input">
						<option value="0">== 전체 ==</option>
						<option value="1">제목</option>
						<option value="2">내용</option>
					</select>
			</li>
			<li><input type="text" id="SCH_SJCN_TXT" name="SCH_SJCN_TXT" class="MX_50 CS_25 input" /></li>
			<li class="fr"><a href="#" class="schbtn dpb" onclick="javascript:fnSearch();">검색</a></li>	
		</ul>
		<div id="div_grid" >
			<table class="adminlist" id="gridArea"></table>
			<div id="gridPager"></div>
		</div>
		<div class="btnbx">
           	 	<a href="#" class="schbtn" onclick="fnWrite();">등록</a>
           	</div>
	</div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>