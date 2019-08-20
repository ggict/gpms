<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/common.js'/>"></script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(END) -->
<div class="tabcont">
	<div class="fl bgsch">
    	<h3>예측자료 생성 방법</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <li class="wid100 mt10">
	            	※ 등록된 조사자료 중 예측자료가 생성되지 않은 자료를 기준으로 예측자료를 생성합니다.<br/>
					<br/>
					※ 데이터를 분석하는데 많은 시간이 소요될 수 있습니다. 분석이 완료 될때까지 기다려주세요.<br/>
					<br/>
					<strong>[평가 소요시간]</strong><br/>
					 5~6분/1건<br/>
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>예측 대상 자료
	    	<a href="#" class="whitebtn dpib ml10 vm">
	    		<img id="imgBack" src="<%=request.getContextPath() %>/images/ic_reset.png" onclick="fn_search();" alt="새로고침" title="새로고침" />
    		</a>
	    </h3>
	    <p class="location">
	        <span>포장상태 예측</span>
	        <strong>예측자료 생성</strong>
	    </p>
	    
	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
			<div class="mt10 tc">
	            <div class="fr">
	            	<a href="#" onclick="fnSave();" class="schbtn">예측자료 생성</a>
	           	</div>
	        </div>
        </div>
    </div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/* 
$( document ).ready(function() {
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'api/smdtalaststtus/selectSrvyDtaPredctList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SRVY_NO", "조사자료 파일명", "조사년도", "포장상태 평가년도", "예측할 년도(10년)", "자료(행)", "예측자료 생성"]
	   	,colModel:[
			{name:'SRVY_NO',index:'SRVY_NO', hidden: true}
			,{name:'FILE_NM',index:'FILE_NM', align:'center', width:150, sortable: true}
			,{name:'SRVY_DE',index:'SRVY_DE', align:'center', width:70, sortable: true}
			,{name:'CALC_DE',index:'CALC_DE', align:'center', width:70, sortable: false}
			,{name:'PREDCT_DE',index:'PREDCT_DE', align:'center', width:70, sortable: false}
			,{name:'DATA_CO',index:'DATA_CO', align:'center', width:70, sortable: true,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'PRDTMDL_PROCESS',index:'PRDTMDL_PROCESS', align:'center', width:70, sortable: true}
	   	]
		,async : false
	   	,sortname: 'SRVY_NO'
	    ,sortorder: "desc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "예측 대상 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
			}
		}
	   	,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridArea");
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
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: true
		,multiboxonly: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 190);
	
	fn_search();
});

//검색 처리
function fn_search() {
	$(".cbox").prop("checked", false);
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

//예측자료 생성
function fnSave(){	
	var seqListArr = [];
	var obj = $("#gridArea");
	var idx = obj.jqGrid('getGridParam', 'selarrrow');
	 
	if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
		alert("예측자료 생성대상 조사자료를 선택해주세요");
		return false;
	}
	
	// SEQ List
	for(var i=0; i<idx.length; i++){
		var value = obj.jqGrid('getCell', idx[i], 'SRVY_NO' );
		seqListArr.push( value );
	}
	
	var option = {
			iframe : window,
			callback : "fn_search"
	}
	
	parent.SRVY.setOption(option);
	parent.SRVY.setSrvyList(seqListArr);
	
	parent.$("#lodingbar").show();
	parent.SRVY.hideAllDiv();
	parent.$("#divPredct").show();
	
	parent.$("#s_predctCnt").text(seqListArr.length); 
}



</script>
</body>
</html>