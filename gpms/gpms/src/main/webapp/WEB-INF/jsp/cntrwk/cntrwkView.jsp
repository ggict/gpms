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

<!-- 필수 파라메터(END) --> 
<div>
	<div class="posiR">
       	<ul class="ctab_menu">
            <li class="sel"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkView.do?CNTRWK_ID=${cntrwkVO.CNTRWK_ID}')">기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtlList.do?CNTRWK_ID=${cntrwkVO.CNTRWK_ID}')">세부공사</a></li>
			<%-- <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flaw/selectFlaw.do?CNTRWK_ID=${cntrwkVO.CNTRWK_ID}')">하자기본정보</a></li>
			<li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('flawcntrwk/selectFlawCntrwkList.do?CNTRWK_ID=${cntrwkVO.CNTRWK_ID}')">하자보수공사</a></li> --%>
       	</ul>
       	<h5 class="info" style="left: 310px;">
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
		</h5>
		<p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력조회</span>
	        <strong>포장공사 기본정보</strong>
	    </p>
   </div>
	<!-- Content -->
	<div class="ctab_wrap">
		<div class="tabcont">
	    	<div class="scroll" style="height:240px;">
	    	<form:form commandName="cntrwkVO" id="cntrwk_Regist-form">
		        <table class="tbview" summary="포장공사 기본정보를 조회합니다.">
		            <caption>포장공사 기본정보</caption>
		            <colgroup>
		                <col width="15%" />
		                <col width="35%" />
		                <col width="15%" />
		                <col width="35%" />
		            </colgroup>
		            <tbody>
		                <tr>
						<th scope="row">사업소</th>
						<td>
							<select name="DEPT_CODE" id="DEPT_CODE" class="select" disabled="true" style="width: 100%;">
								<option value="">===== 전체 =====</option>
								<c:forEach var="selectData" items="${deptList}">
								<option value="${selectData.DEPT_CODE}" <c:if test = "${selectData.DEPT_CODE == cntrwkVO.DEPT_CODE}"> selected="selected" </c:if> >${selectData.LOWEST_DEPT_NM}</option>
								</c:forEach>
							</select>
						</td>
						<th scope="row">공사년도</th>
							<td>
								<select name="CNTRWK_YEAR" id="CNTRWK_YEAR" style="width:100px" class="select"  disabled="true">
									<option value="">== 전체 ==</option>
									<c:forEach var="selectData" items="${cntrwkYears}">
									<option value="${selectData}" <c:if test = "${selectData == cntrwkVO.CNTRWK_YEAR}"> selected="selected" </c:if> >${selectData}</option>
									</c:forEach>
								</select> 년
								<select name="HT_SE" id="HT_SE" class="select" style="width:100px; margin-left: 5px;" disabled="true">
								<option value="">== 전체 ==</option>
									<c:forEach var="code" items="${codesHTSE}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == cntrwkVO.HT_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
						<th scope="row">공사구분</th>
						<td>
							<select name="CNTRWK_SE" id="CNTRWK_SE" class="select"  disabled="true" style="width: 100%;">
								<option value="">===== 전체 =====</option>
								<c:forEach var="code" items="${codesCWSE}">
								<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == cntrwkVO.CNTRWK_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
								</c:forEach>
							</select>
						</td>
						<th scope="row">공사분류</th>
							<td>
								<select name="CNTRWK_CL" id="CNTRWK_CL" class="select"  disabled="true" style="width: 100%;">
									<option value="">===== 전체 =====</option>
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
							<%-- <th scope="row">세부공사명</th>
							<td>
								<label for="DETAIL_CNTRWK_NM"></label>
								<c:out value="${cntrwkVO.DETAIL_CNTRWK_NM}"/>
								<input type="text" name="DETAIL_CNTRWK_NM" id="DETAIL_CNTRWK_NM" value="<c:out value="${cntrwkVO.FULL_CNTRWK_NM}"/>" class="MX_50 CS_50 input" style="display: none;" />	<!-- placeholder="예) 2016년 노후포장도로 정비공사" -->
							</td> --%>
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
		    </form:form>
	        </div>
	        <div class="mt10 tc">
	            <div class="fr mr10">
		           	<a href="#" onclick="fnViewLocation();" class="schbtn">위치조회</a>
					<a href="#" class="schbtn" onclick="fnUpdate();">수정</a>
					<a href="#" class="graybtn" onclick="fnDelete();">삭제</a>
	           	</div>
	        </div>
		</div>
	</div>
</div>

<script type="text/javascript" defer="defer">

/* function resize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
} */

$(document).ready(function(){
});

//신규 등록 화면 이동 [수정:선택] url
function fnUpdate() {	
	COMMON_UTIL.cmMoveUrl("cntrwk/updateCntrwkView.do?CNTRWK_ID="+$("#CNTRWK_ID").val());
	//COMMON_UTIL.cmWindowOpen( "포장공사정보 수정", "<c:url value='/cntrwk/updateCntrwkView.do'/>?CNTRWK_ID="+$("#CNTRWK_ID").val(), 1024, 530, false, $("#wnd_id").val(), 'center');	

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


/* function fn_select_cell(){
	
	var action = '<c:url value="/api/cntrwk/selectCntrwkCellId.do"/>';
	
	$.ajax({
        url: action,
        contentType: 'application/json',
        data: JSON.stringify({"CNTRWK_ID" : $("#CNTRWK_ID").val()}),
        dataType: "json",
        type: 'POST',
        success : function (res) {
			if (res.res.length == 0) {
				alert("공사 위치가 없습니다.");
			} else {
				//cellId 배열 선언
				var tables=new Array();
				var fields=new Array();
				var cellList=new Array();
				for(var i in res.res){
					tables[i] = "CELL_10";
					fields[i] = "CELL_ID";
					cellList[i] = res.res[i].CELL_ID;
				}
				
				// 모든 팝업창 최소화
				parent.wWindowHideAll();
				// 하단 목록 창 내리기
				parent.bottomClose();
				//
				MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, cellList);
			}
		},
        error: function () {
            alert("지도 이동 오류가 발생하였습니다. 다시 화면 갱신후 진행하십시오.");
            return;
        }
	});
} */

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


