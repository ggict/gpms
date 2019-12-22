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
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/*
$( document ).ready(function() {
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'srvy/api/analDataPopupResultList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: JSON.stringify( $("#frm").cmSerializeObject())
		//,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		//,colNames:["작업일자","성공 건수","실패 건수", "등록자", "CRTR_NO"]
		,colNames:["시점","종점","거북등균열","종방향균열","횡방향균열","패칭","소성변형","종단평탄성","블록균열","노선번호"]
	   	,colModel:[
			 {name:'strtpt',index:'strtpt', align:'center', width:50}
			,{name:'endpt',index:'endpt', align:'center', width:50}
			,{name:'ac_TOT',index:'ac_TOT', align:'center', width:50, formatter: 'integer'}
			,{name:'lc_TOT',index:'lc_TOT', align:'center', width:50, formatter: 'integer'}
			,{name:'tc_TOT',index:'tc_TOT', align:'center', width:50, formatter: 'integer'}
			,{name:'pothole_CR',index:'pothole_CR', align:'center', width:50, formatter: 'integer'}
			,{name:'rd_VAL',index:'rd_VAL', align:'center', width:50, formatter: 'integer'}
			,{name:'iri_VAL',index:'iri_VAL', align:'center', width:50, formatter: 'integer'}
			,{name:'block_CR',index:'block_CR', align:'center', width:50, formatter: 'integer'}
			,{name:'route_CODE',index:'route_CODE', hidden: true}
	   	]
		,async : false
	   	,sortname: 'strtpt'
	    ,sortorder: 'asc'
	   	,rowNum: 50
	   	,rowList: [10,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
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

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea', 'div_grid', 200);

	fn_search();
});

function fn_search() {
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		var str = "";
	   		str +="<td> "+data.routeCode+"</td>";
	   		str +="<td> "+data.roadName+"</td>";
	   		str +="<td> "+data.directCode+"</td>";
	   		str +="<td> "+data.track+"</td>";
	   		$("#dataTr").html(str);
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

</script>
</head>
<body style="height:326px;">
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

	<div style="margin:0 2%">
		<div class="table" style="margin:0 0 20px 0">
			<table class="table20">
				<thead>
					<tr>
						<th scope="col">노선번호</th>
						<th scope="col">노선명</th>
						<th scope="col">행선</th>
						<th scope="col">차로</th>
					</tr>
				<thead>
				<tbody>
					<tr id="dataTr"></tr>
				</tbody>
			</table>
		</div>



   		<form id="frm" name="frm" method="post" action="">
   			<input type="hidden" id="SRVY_NO" name="SRVY_NO" value="${srvyDtaVO.SRVY_NO}"/>
		    <div id="div_grid" style="max-height:400px">
		        <table id="gridArea"></table>
				<div id="gridPager"></div>
            </div>
        </form>


	</div>
</body>
</html>