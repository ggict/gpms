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
<!-- <title>조사자료상세조회 </title> -->
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	
	//상세조회화면 hide
	$("#dv_area").hide();
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/mummsctnsrvydta/selectMummSctnSrvyDtaTab3ListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		/* ,postData : {
			SCH_PERIOD_1 : $("#SCH_PERIOD_1").val()
			, SCH_PERIOD_2 : $("#SCH_PERIOD_2").val()
		} */
		,ignoreCase: true
		,colNames:["시점<br/>(m)","종점<br/>(m)","AC<br/>(%)","BLOCK<br/>(%)","LC<br/>(%)","TC<br/>(%)","PATCHING<br/>(%)","POTHOLE<br/>(%)","RD<br/>(%)","IRI<br/>(m/km)","GPCI"]
	   	,colModel:[
	   	      {name:'STRTPT',index:'STRTPT', align:'right', width:80,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			 ,{name:'ENDPT',index:'ENDPT', align:'right', width:80, sortable:false,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
		   	 ,{name:'AC_IDX',index:'AC_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'BLOCK_CR',index:'BLOCK_CR', align:'right', width:40, sortable:false}
		   	 ,{name:'LC_IDX',index:'LC_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'TC_IDX',index:'TC_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'PTCHG_IDX',index:'PTCHG_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'POTHOLE_IDX',index:'POTHOLE_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'RD_IDX',index:'RD_IDX', align:'right', width:40, sortable:false}
		   	 ,{name:'IRI_VAL',index:'IRI_VAL', align:'right', width:40, sortable:false}
		   	 ,{name:'GPCI',index:'GPCI', align:'center', width:80, sortable:false}
	   	]
		,async : false
	   	,sortname: 'STRTPT'
	    ,sortorder: "asc"
	   	,rowNum: 20
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
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}	   			
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#frm").cmSerializeObject();
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	//cmInitPopupGrid('gridArea',10);
	//cmInitPopupGridIncludeLeft('gridArea', 100, -120);
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid',70);
	
	fnSearch();
}); 

//검색 처리
function fnSearch() {
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

function fnGetTimeStamp(add) {
	var d = new Date();
	if(add != null){
		d.setDate(add + d.getDate());
	}
	var s = leadingZeros(d.getFullYear(), 4) + "-" + leadingZeros(d.getMonth() + 1, 2) + "-" + leadingZeros(d.getDate(), 2);
	return s;
}

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();
	
	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

function fnDvAreaClose(){
	$("#dv_area").hide();
}
</script>
</head>

<body style="height:350px;">
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="SRVY_NO" name="SRVY_NO" value="<c:out value="${param.SRVY_NO}"/>"/>
<input type="hidden" id="SRVY_DE" name="SRVY_DE" value="<c:out value="${param.SRVY_DE}"/>"/>
<input type="hidden" id="ROUTE_CODE" name="ROUTE_CODE" value="<c:out value="${param.ROUTE_CODE}"/>"/>
<input type="hidden" id="ENDPT" name="ENDPT" value="<c:out value="${param.ENDPT}"/>"/>
<input type="hidden" id="STRTPT" name="STRTPT" value="<c:out value="${param.STRTPT}"/>"/>

<div class="cont_PopLeft">
	<%-- <div class="cont_TitBx">
		<p><c:out value="${screen_title}"/></p>
	</div> --%>
	<div class="cont_Section">
		
	<%-- <%@ include file="/include/leftMenu.jsp" %> --%>
	<!--// Tab -->
   	<div class="Pop_Section">
		<div class="cont_TabBx">
			<ul>
				<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab1.do'/>')" class="Tab_01">도로포장 조사내역</a></li>
				<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab2.do'/>')" class="Tab_01">차로별 조사현황</a></li>
				<li><a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/mng/mummsctnsrvydta/selectMummSctnSrvyDtaTab3.do'/>')" class="Tab_01_se">10m 세부내역</a></li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
	<div style="margin:0px 210px;">
		<!--검색영역-->
		<div class="cont_search"  style="width: 630px; margin-left: -200px;">
			<div class="Con_Bt"  style="padding: 0 5px 0 0;">
				<img src="<%=request.getContextPath() %>/images/portal/btn_search3.gif" alt="검색" onclick="javascript:fnSearch();"/>
			</div>
			<dl>
				<dt class="CTx1">시점</dt>
				<dt class="W90">
					<input type="text" name="SCH_STRTPT" id="SCH_STRTPT" class="input" style="width:80px;"/>
				</dt>
				<dt class="CTx1">종점</dt>
				<dt class="W90">
					<input type="text" name="SCH_ENDPT" id="SCH_ENDPT" class="input" style="width:80px;"/>
				</dt>
			</dl>
		</div>
		<div>&nbsp;</div>
		<div class="PopTb_left" style="width: 630px; margin-left: -200px;">
				<table>
					<colgroup>
						<col width="23%">
						<col width="21%">
						<col width="23%">
						<col width="33%">
					</colgroup>
					<tr>
						<th>조사명</th>
						<td colspan="3"><c:out value="${mummSctnSrvyDtaVO.SRVY_NM}" /></td>
					</tr>
					<tr>
						<th>노선번호</th>
						<td><fmt:parseNumber value="${mummSctnSrvyDtaVO.ROUTE_CODE}" type="number"/></td>
						<th>노선명</th>
						<td><c:out value="${mummSctnSrvyDtaVO.ROUTE_NAME}" /></td>
					</tr>
					<tr>
						<th>행선</th>
						<td><fmt:parseNumber value="${mummSctnSrvyDtaVO.DIRECT_NM}" type="number"/></td>
						<th>차로</th>
						<td><c:out value="${mummSctnSrvyDtaVO.TRACK}" /></td>
					</tr>
					<tr>
						<th>시점</th>
						<td><fmt:formatNumber value="${mummSctnSrvyDtaVO.STRTPT}" type="number"/>m</td>
						<th>종점</th>
						<td><fmt:formatNumber value="${mummSctnSrvyDtaVO.ENDPT}" type="number"/>m</td>
					</tr>
					<tr>
						<th>조사연장</th>
						<td colspan='3'><fmt:formatNumber value="${mummSctnSrvyDtaVO.LENGTH}" type="number"/>m</td>
					</tr>
				</table>
			</div>
		<div>&nbsp;</div>
		<div id="div_grid" style="width: 630px; margin-left: -200px;">
			<table id="gridArea"></table>
			<div id="gridPager"></div>
		</div>
	</div>

	<div id="dv_area" style="margin:-500px 220px; position:absolute; background-color: white; border: 2px solid #d9d9d9;">
	<div>
		<input type="button" value="닫기" onclick="fnDvAreaClose();" style="top:15px;right:20px; position:absolute; background-color: #005cbb; padding:5px 10px; color: white; border: none; font-weight: bold;"></input>
		<iframe src="" id="iframe_popup" style="width:680px; height:450px; border:none;"></iframe>
	</div>
</div>
</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>