<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<%
 /**
  * @Class Name : ClCodeRegister.jsp
  * @Description : ClCode Register 화면
  * @Modification Information
  * 
  * @author leehb1592@gmail.com
  * @since 2017-05-25
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

</script>
</head>

<body>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<input type="hidden" id="DETAIL_CNTRWK_ID" name="DETAIL_CNTRWK_ID" value="${cntrwkVO.DETAIL_CNTRWK_ID}">
<!-- 필수 파라메터(END) --> 




<div class="tabcont">

	<header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">포장공사이력관리</option>
                </select>
                <select name="">
                    <option value="">포장공사이력관리</option>
                </select>
                <h2 class="h2">포장공사이력 조회</h2>
                <h3 class="h3"><c:out value="${cntrwkVO.FULL_CNTRWK_NM}"/></h3> 
                <input type="button" class="btnDetailView" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${cntrwkVO.CNTRWK_ID}&CNTRWK_SE=${cntrwkVO.CNTRWK_SE}')" value="세부공사">
            </span>
        </div>
    </header>
    
    <div class="contents container">
    
    	<article class="div5" style="height:300px; overflow-y: auto">
    		<form:form commandName="cntrwkVO" id="cntrwk_Regist-form">
    		<div class="table">
		        <table class="tbview" summary="포장공사 기본정보를 조회합니다.">
		            <caption>포장공사 기본정보</caption>
		            <colgroup>
		                <col width="20%" />
		                <col width="30%" />
		                <col width="20%" />
		                <col width="30%" />
		            </colgroup>
		            <tbody>
		                <tr>
						<th scope="row">사업소</th>
						<td>
							<select name="DEPT_CODE" id="DEPT_CODE" class="select" disabled="true" >
								<option value="">전체</option>
								<c:forEach var="selectData" items="${deptList}">
								<option value="${selectData.DEPT_CODE}" <c:if test = "${selectData.DEPT_CODE == cntrwkVO.DEPT_CODE}"> selected="selected" </c:if> >${selectData.LOWEST_DEPT_NM}</option>
								</c:forEach>
							</select>
						</td>
						<th scope="row">공사년도</th>
							<td>
								<select name="CNTRWK_YEAR" id="CNTRWK_YEAR" class="select"  disabled="true">
									<option value="">전체</option>
									<c:forEach var="selectData" items="${cntrwkYears}">
									<option value="${selectData}" <c:if test = "${selectData == cntrwkVO.CNTRWK_YEAR}"> selected="selected" </c:if> >${selectData}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="SRVY_YEAR" name="SRVY_YEAR" value="${cntrwkVO.CNTRWK_YEAR}">
								<select name="HT_SE" id="HT_SE" class="select" style="width:80px; margin-left: 5px;" disabled="true">
								<option value="">전체</option>
									<c:forEach var="code" items="${codesHTSE}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == cntrwkVO.HT_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
						<th scope="row">공사구분</th>
						<td>
							<select name="CNTRWK_SE" id="CNTRWK_SE" class="select"  disabled="true" >
								<option value="">전체</option>
								<c:forEach var="code" items="${codesCWSE}">
								<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == cntrwkVO.CNTRWK_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
								</c:forEach>
							</select>
						</td>
						<th scope="row">공사분류</th>
							<td>
								<select name="CNTRWK_CL" id="CNTRWK_CL" class="select"  disabled="true">
									<option value="">전체</option>
									<c:forEach var="code" items="${codesCWCL}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == cntrwkVO.CNTRWK_CL}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr> 
							<th scope="row">공사명</th>
							<td colspan="3">
								<label for="FULL_CNTRWK_NM"></label>
								<c:out value="${cntrwkVO.FULL_CNTRWK_NM}"/>
							</td>								
								<c:out value="${cntrwkVO.DETAIL_CNTRWK_NM}"/>
								<input type="hidden" name="DETAIL_CNTRWK_NM" id="DETAIL_CNTRWK_NM" value="<c:out value="${cntrwkVO.FULL_CNTRWK_NM}"/>" class="MX_50 CS_50 input" style="display: none;" />	<!-- placeholder="예) 2016년 노후포장도로 정비공사" -->
							
						</tr>
						<tr>
							<th scope="row">착공일 ~ 준공일</th>
							<td>
								<label for="STRWRK_DE"></label>
								<fmt:parseDate var="STRWRK_DE" value="${cntrwkVO.STRWRK_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${STRWRK_DE}" pattern="yyyy-MM-dd" />
								~
								<label for="COMPET_DE"></label>
								<fmt:parseDate var="COMPET_DE" value="${cntrwkVO.COMPET_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${COMPET_DE}" pattern="yyyy-MM-dd" />
							</td>
							<th scope="row">시공사</th>
							<td>
								<input type="hidden" name="CNSTRCT_CO_NO" id="CNSTRCT_CO_NO" />
								<label for="CNSTRCT_CO_NM"></label>
								<c:out value="${cntrwkVO.CNSTRCT_CO_NM}"/>
							</td>
						</tr>
						<%-- <tr>
							<th scope="row"> 하자기간</th>
							<td colspan="3">
								<label for="FLAW_BEGIN_DE"></label>
								<fmt:parseDate var="FLAW_BEGIN_DE" value="${cntrwkVO.FLAW_BEGIN_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${FLAW_BEGIN_DE}" pattern="yyyy-MM-dd" />
								~
								<label for="FLAW_END_DE"></label>
								<fmt:parseDate var="FLAW_END_DE" value="${cntrwkVO.FLAW_END_DE}" pattern="yyyyMMdd" />
								<fmt:formatDate value="${FLAW_END_DE}" pattern="yyyy-MM-dd" />
							</td>
						</tr> 
						<tr>							
							
							 <th scope="row">감리사</th>
							<td>
								<input type="hidden" name="SPRVISN_CO_NO" id="SPRVISN_CO_NO" />
								<label for="SPRVISN_CO_NM"></label>
								<c:out value="${cntrwkVO.SPRVISN_CO_NM}"/>
							</td>
						</tr> --%>
						<tr>
							<th scope="row">시공사 대표자</th>
							<td>
								<label for="CNSTRCT_CO_RPRSNTV_NM"></label>
								<c:out value="${cntrwkVO.CNSTRCT_CO_RPRSNTV_NM}"/>
							</td>
							<%-- <th scope="row">감리사 대표자</th>
							<td>
								<label for="SPRVISN_CO_RPRSNTV_NM"></label>
								<c:out value="${cntrwkVO.SPRVISN_CO_RPRSNTV_NM}"/>
							</td> --%>
							<th scope="row">시공사 대표번호</th>
							<td>
								<label for="CNSTRCT_CO_TELNO"></label>
								<c:out value="${cntrwkVO.CNSTRCT_CO_TELNO}"/>
							</td>
						</tr>
						<%-- <tr>
							
							<th scope="row">감리사 대표번호</th>
							<td>
								<label for="SPRVISN_CO_RPRSNT_TELNO"></label>
								<c:out value="${cntrwkVO.SPRVISN_CO_RPRSNT_TELNO}"/>
							</td>
						</tr> --%>
						<tr>
							<th scope="row">총 공사연장 (자동계산)</th>
							<td>
								<c:if test="${!empty cntrwkVO.TRACK_LEN}">
								<fmt:formatNumber value="${cntrwkVO.TRACK_LEN}" type="number"/> km
								</c:if>
							</td>
							<th scope="row">총 공사금액 (자동계산)</th>
							<td>
								<c:if test="${!empty cntrwkVO.TOT_AMOUNT}">
								<fmt:formatNumber value="${cntrwkVO.TOT_AMOUNT}" type="number"/> 천원
								</c:if>
							</td>
						</tr>
						<tr>
							<th scope="row">총 공사면적 (자동계산)</th>
							<td>
								<c:if test="${!empty cntrwkVO.RPAIR_AR}">
								<fmt:formatNumber value="${cntrwkVO.RPAIR_AR}" type="number"/> ㎡
								</c:if>
							</td>
							<th scope="row">포장두께</th>
							<td>
								<c:out value="${cntrwkVO.RPAIR_THICK_DC}"/>
							</td>
						</tr>
						<tr style="display: none">
							<th scope="row">공사ID</th>
							<td>
								<form:input path="CNTRWK_ID" maxLength="100" style="width:150px;" readonly="true" />
								<form:errors path="CNTRWK_ID" />
							</td>
							<th scope="row">노선번호</th>
							<td>
								<form:input path="ROUTE_CODE" maxLength="100" style="width:150px;" readonly="true" />
								<form:errors path="ROUTE_CODE" />
							</td>
						</tr>
					</tbody>
		        </table>
		    </div>
		    </form:form>
    	</article>
    	
    	<article class="div7" style="height:300px; overflow-y: auto">
    		<h3 class="h3">이전 공사이력</h3>
    		<form id="cellFrm2">
	    		<div id="div_grid2" class="table">
					<table id="gridArea2"></table>			
				</div>
				<div id="gridPager"></div>
			</form>
    	</article>
    	
    </div>



<script type="text/javascript" defer="defer">

/* function resize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
} */

$(document).ready(function(){
	var pav_year = $('#SRVY_YEAR').val();
	var cntrwk_id = $('#CNTRWK_ID').val();
    var postData2 = {"CNTRWK_ID":cntrwk_id, "PAV_YEAR":pav_year};
    // 리스트에서 셀 선택 grid
    $("#gridArea2").jqGrid({
        url: '<c:url value="/"/>'+'api/cntrwkcellinfo/selectCntrwkBeforeCellInfoList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: $("#cellFrm2").cmSerializeObject()
        ,postData: postData2
        ,ignoreCase: true
        ,colNames:["CELL_ID","노선번호","노선명","행선","차로","시점(m)","종점(m)","GPCI","위치보기"]
        ,colModel:[
            {name:'CELL_ID', index:'CELL_ID', hidden:true}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:50, sortable:false, formatter: 'integer'}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:70, sortable:false}
            ,{name:'DIRECT_CODE',index:'DIRECT_CODE', align:'center', width:70, sortable:false}
            ,{name:'TRACK',index:'TRACK', align:'center', width:70, sortable:false}
            ,{name:'STRTPT',index:'STRTPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'ENDPT',index:'ENDPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'GPCI',index:'GPCI', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'btn_loc_cell',index:'btn_loc_cell', align:'center', width:70, sortable:false, formatter: fn_create_btn}
        ]
        ,async : false
        ,sortname: ''
        ,sortorder: ""
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager2'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
            //fn_view(rowId); // 대장 조회
        }
        ,onSelectRow: function(rowId, status, e) {     // 클릭 처리
            //fnCalDetailInfo();
        }
        ,onSelectAll: function(aRowIds, status) {
            //console.log('[onSelectAll] ' + status + ' = ' + aRowIds.length);
            //fnCalDetailInfo();
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
        //,loadonce: true
        //,scroll: true
    }).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});  
	
	COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', 150);

	fn_search();		
});

//신규 등록 화면 이동 [수정:선택] url
function fnUpdate() {	
	COMMON_UTIL.cmMoveUrl("cntrwk/updateCntrwkView.do?CNTRWK_ID="+$("#CNTRWK_ID").val());
	//COMMON_UTIL.cmWindowOpen( "포장공사정보 수정", "<c:url value='/cntrwk/updateCntrwkView.do'/>?CNTRWK_ID="+$("#CNTRWK_ID").val(), 1024, 530, false, $("#wnd_id").val(), 'center');	

}

//검색 처리
function fn_search() {
	
    var cntrwk_id = $('#CNTRWK_ID').val();
    var postData2 = {"CNTRWK_ID":cntrwk_id};
    $("#gridArea2").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        //,postData:   $("#cellFrm2").cmSerializeObject()
        ,postData:   postData2
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}

//삭제 - 수정필요
function fnDelete(){
	$.ajax({
		url: contextPath + 'userauth/checkAuth.do'
		,type: 'post'
		,dataType: 'json'
		,data : {"url" : "/api/cntrwk/deleteCntrwk.do"}
		,success: function(res){				
			if(!res.result){
				alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
				return;
			}
			
			fnDeleteProc()
		}
		,error: function(a,b,msg){
			
		}
	});
}

//삭제 처리 [수정:가능]
function fnDeleteProc() {

var vform = $('#cntrwk_Regist-form');
	
	var action = '<c:url value="/api/cntrwk/deleteCntrwk.do" />';
	
	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		$.ajax({
	        url: action,
	        contentType: 'application/json',
	        data: JSON.stringify( vform.cmSerializeObject())  ,
	        dataType: "json",
	        cache: false,
	        type: 'POST',
	        processData: false,
	        success: function (jdata) {
	            if (jdata != null) {
	            	alert("삭제하였습니다.");
	            	
	            	COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do');
	                return;
	            }
	        },
	
	        error: function () {
	            alert("삭제 시 오류가 발생하였습니다.");
	            return;
	        }
	    });
	}
}

//위치 이동
function fn_select_cell(cell_id){
	var tables = ["CELL_10"];
	var fields = ["CELL_ID"];
	var values = [cell_id];
	
	// 모든 팝업창 최소화
	parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	var attribute_base = {
	        attributes : {
	            fillColor : '#ffffff',
                strokeColor : '#ffffff'
			}
	};
	
	//MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
	MAP.fn_get_selectFeatureByAttrMulti(parent.gMap, tables, fields, values, null, "AND", attribute_base, true, 0, 1);
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
		case "btn_loc" :
			btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.PAV_CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
			break;
        case "btn_loc_cell" :
            btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
            break;
	}
	
	return btn;
}
//위치조회
function fnViewLocation(){
	
	$.ajax({
		url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
		,type: 'post'
		,data: JSON.stringify({"CNTRWK_ID" : $("#CNTRWK_ID").val() })
		,dataType: 'json'
		,contentType : 'application/json'
		,success: function(res){
			var tables = ["CELL_10"];
			var fields = [];
			var values = [];
			
			if(res.length < 1){
				alert("위치 정보가 존재하지 않습니다.");
				return;
			}
			
			if(res.length > 1){
				for(var i in res){
					var data = res[i];
					fields.push("CELL_ID");
					values.push(data.PAV_CELL_ID);
				}
			}else{
				fields = "CELL_ID";
				values = res[0].PAV_CELL_ID;
			}
			
			// 모든 팝업창 최소화
			parent.wWindowHideAll();
			// 하단 목록 창 내리기
			parent.bottomClose();
			
			MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [fields], [values], null, "OR");
		}
		,error: function(a,b,msg){
		}		
	});
}
</script>	
</body>
</html>


