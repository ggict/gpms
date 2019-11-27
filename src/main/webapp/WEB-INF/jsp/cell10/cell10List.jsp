<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>

<script type="text/javascript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	COMMON_UTIL.cmFormObjectInit("frm");
	
	var postData = {"USE_AT":"Y"};
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/cell10/selectCell10List.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["CELL_ID","노선 번호","노선 명","관리주체","행정 구역","도로 등급","교통 용량","섹션 구분","행선", "차로","구간", "시점(m)", "종점(m)", "위치보기"]
	   	,colModel:[
	   	    /* sortable 사용 안함 (기본 : 노선 번호, 행선, 차료, 시점, 좀점 순으로 sort) */
	   	    {name:'CELL_ID',index:'CELL_ID', hidden: true}
	   	 	,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:100, sortable:false}
			,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:100, sortable:false}
			,{name:'DEPT_NM',index:'DEPT_NM', align:'center', width:120, sortable:false}
			,{name:'ADM_NM',index:'ADM_NM', align:'center', width:60, sortable:false}
			,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:60, sortable:false}
			,{name:'VMTC_GRAD',index:'VMTC_GRAD', align:'center', width:80, sortable:false}
			,{name:'CELL_TYPE',index:'CELL_TYPE', align:'center', width:80, sortable:false}
			,{name:'DIRECT_CODE',index:'DIRECT_CODE', align:'center', width:50, sortable:false}
			,{name:'TRACK',index:'TRACK', align:'center', width:50, sortable:false}
			,{name:'SCTN_NM',index:'SCTN_NM', align:'center', width:50, sortable:false}
			,{name:'STRTPT',index:'STRTPT', align:'center', width:60, sortable:false, formatter: 'number', formatoptions: { thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'ENDPT',index:'ENDPT', align:'center', width:60, sortable:false, formatter: 'number', formatoptions: { thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fn_create_btn}
	   	]
		,async : false
		,sortname: 'CELL_ID'
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
			//fnView(rowId);	// 대장 조회
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
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 190);
	
	fn_search();
}); 

//검색 처리
function fn_search() {
	var strtpt = $("#STRTPT").val().replace(/,/gi, "");
	var endpt = $("#ENDPT").val().replace(/,/gi, "");;
	
	if(strtpt != "" && endpt !="" && parseInt(strtpt) > parseInt(endpt)){
		alert("종점 값은 시점 값 보다 크게 입력해주시기 바랍니다.");
		return;
	}
	
	var postData = {"USE_AT":"Y"};
	postData = $("#frm").cmSerializeObject();
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

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
	case "btn_loc" :
		btn = "<a href='#' onclick=\"fn_select_route('" + rowObject.ROUTE_CODE + "','" + rowObject.CELL_ID + "')\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
		break;
	}
	
	return btn;
}

function fn_select_route(route_no, cell_id){
	var tables = ["DORO_TOT_GRS80_50", "CELL_10"];
	var fields = ["ROAD_NO", "CELL_ID"];
	var values = [route_no, cell_id];
	var attirbute = 
		{ 
		fillColor : '#0033ff',
		strokeColor : '#0033ff',
		table : "CELL_10",
    	field : "CELL_ID",
    	value : cell_id,
		attributes : {
				fillColor : '#ff0000',
				strokeColor : '#ff0000'
			}
	};
	// 모든 팝업창 최소화
	parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, "CELL_10", null, attirbute);
}

</script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div class="tabcont">

	<header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">노선검색</option>
                </select>
                <select name="">
                    <option value="">셀단위로검색</option>
                </select>
                <h2 class="h2">셀단위로검색</h2>
            </span>
        </div>
    </header>

    <div class="contents container">

    	<!-- div3, 왼쪽 3/12 S -->
    	<article class="div3 arrange1">
    		<h3 class="h3">검색조건</h3>
    		<div class="table">
    			<table>
    				<tbody>
    					<tr>
    						<td class="th">
    							<label for="DEPT_CODE">관리주체</label>
    						</td>
    						<td>
				                <select id="DEPT_CODE" name="DEPT_CODE">
				                	<option value="">전체</option>
					        		<c:forEach items="${deptList }" var="dept">
					        			<option value="${dept.DEPT_CODE }">${dept.LOWEST_DEPT_NM }</option>
					        		</c:forEach>
				                </select>    							
    						</td>
    						<td class="th">
    							 <label>행정구역</label>	                
    						</td>
    						<td>
				                 <select id="ADM_CODE" name="ADM_CODE" >
				                	<option value="">전체</option>
					        		<c:forEach items="${admList }" var="adm">
					        			<option value="${adm.CODE_VAL }">${adm.CODE_NM }</option>
					        		</c:forEach>
				                </select>
    						</td>
    					</tr>
			            <tr>
					            	<td class="th">
    						                <label>교통용량</label>
    						            </td>
    						            <td>
    						                <select id="VMTC_GRAD" name="VMTC_GRAD">
    						                	<option value="">전체</option>
    							        		<c:forEach items="${vntcList }" var="vntc">
    							        			<option value="${vntc.CODE_VAL }">${vntc.CODE_NM }</option>
    							        		</c:forEach>
    						                </select>
    						           </td>
					            	<td class="th">
    						                <label>섹션구분</label>
    						            </td>
    						            <td>
    						                <select id="CELL_TYPE" name="CELL_TYPE">
    						                	<option value="">전체</option>
    							        		<c:forEach items="${celtList }" var="celt">
    							        			<option value="${celt.CODE_VAL }">${celt.CODE_NM }</option>
    							        		</c:forEach>
    						                </select>
    						           </td>
			            </tr>
			            <tr>
			            
					            	<td class="th">
    						                <label>도로등급</label>
    						            </td>
    						            <td>
    						                <select id="ROAD_GRAD" name="ROAD_GRAD" onchange="COMMON_UTIL.fn_change_roadNo('ROAD_GRAD', 'ROUTE_CODE', 'ROAD_NAME');">
    						                	<option value="">전체</option>
    							        		<c:forEach items="${roadGradList }" var="roadGrad">
    							        			<option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
    							        		</c:forEach>
    						                </select>
    						           </td>
					            	<td class="th">
    					                    <label>노선번호</label>
    					                </td>
    					                <td>
    					                    <select id="ROUTE_CODE" name="ROUTE_CODE" onchange="COMMON_UTIL.fn_change_roadNm('ROUTE_CODE', 'ROAD_NAME', 'ROAD_GRAD');" >
    											<option value="">전체</option>
    											<c:forEach items="${ roadNoList }" var="roadNo">
    											    <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
    											</c:forEach>
    					                    </select>
    					                    <input type="text" id="ROAD_NAME" readonly="readonly" value="">
    					               </td>
    					 </tr>
    					 <tr>
						           <td class="th">
    						                <label>행선</label>
    						            </td>
    						            <td>
    						                <select id="DIRECT_CODE" name="DIRECT_CODE">
    						                	<option value="">전체</option>
    							        		<option value="S">상행</option>
    							        		<option value="E">하행</option>
    						                </select>
    						           </td>
						            	<td class="th">
    						                <label>차로</label>
    						            </td>
    						            <td>
    						                <input type="text" name="TRACK" id="TRACK" value="" class="MX_80 CS_50 DT_INT input" />
    						           </td>
			 	         
    					 <tr>
    						            
    						            	<td class="th" colspan="4">
    						                <label>시점(m)</label>
    						                <input type="text" name="STRTPT" id="STRTPT" value="" style="width:53px;" class="MX_80 CS_50 DT_INT input" />
    						                 ~ 
    						                <label>종점(m)</label>
    						                <input type="text" name="ENDPT" id="ENDPT" value="" style="width:53px;" class="MX_80 CS_50 DT_INT input" />
    						           <button type="button" class="btn pri fr" onclick="javascript:fn_search();">검색</button>
    						       </td>
    					</tr>
    				</tbody>
    			</table>
    		</div>
    	</article>
    	<!-- div3, 왼쪽 3/12 E -->

    	<!-- div9, 왼쪽 9/12 S -->
    	<article class="div9">

    		<h3 class="h3">10m셀로 검색</h3>
    		<div id="div_grid" class="table">
				<table id="gridArea"></table>				
			</div>
			<div id="gridPager"></div>


    	</article>
    	<!-- div9, 왼쪽 9/12 E -->
    </div>

</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>