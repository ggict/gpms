<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body>

<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="<c:out value="${result.CNTRWK_ID}"/>"/>
<!-- 기타 구분 파라메터 -->
<input type="hidden" id="CELL_TYPE" name="CELL_TYPE" value="<c:out value="${result.CELL_TYPE}"/>"/>
<input type="hidden" id="EXT_TAB" name="EXT_TAB" value="<c:out value="${param.EXT_TAB}"/>"/>
<input type="hidden" id="FILE_NO" name="FILE_NO" value="<c:out value="${result.FILE_NO}"/>"/>
<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<h3>
			포장공사 기본정보 등록
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기"/></a>
		</h3>
	    <p class="location">
	        <span>포장공사 이력관리</span>
	        <span>포장공사 이력관리</span>
	        <strong>포장공사 기본정보 등록</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
	    	<div class="scroll" style="height:230px;">
		        <table class="tbview" summary="포장공사 기본정보를 등록합니다.">
		            <caption>포장공사 기본정보 등록</caption>
		            <colgroup>
		                <col width="15%" />
		                <col width="35%" />
		                <col width="15%" />
		                <col width="35%" />
		            </colgroup>
		            <tbody>
		                <tr>
		                    <th scope="row">
		                        	사업소<span class="fcred"> *</span>
		                    </th>
		                    <td>
		                        <select name="DEPT_CODE" id="DEPT_CODE" class="select" style="width: 100%;">
									<option value="">===== 전체 =====</option>
									<c:forEach var="selectData" items="${deptList}">
									<option value="${selectData.DEPT_CODE}" <c:if test = "${selectData.DEPT_CODE == result.DEPT_CODE}"> selected="selected" </c:if> >${selectData.LOWEST_DEPT_NM}</option>
									</c:forEach>
								</select>
		                    </td>
		                    <th scope="row">
		                        	공사년도<span class="fcred"> *</span>
		                    </th>
		                    <td>
		                        <select name="CNTRWK_YEAR" id="CNTRWK_YEAR" style="width:100px" class="select">
									<option value="">== 전체 ==</option>
									<c:forEach var="selectData" items="${cntrwkYears}">
									<option value="${selectData}" <c:if test = "${selectData == result.CNTRWK_YEAR}"> selected="selected" </c:if> >${selectData}</option>
									</c:forEach>
								</select> 년 
								<select name="HT_SE" id="HT_SE" class="select" style="width:100px; margin-left: 5px;" >
								<option value="">== 전체 ==</option>
									<c:forEach var="code" items="${codesHTSE}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == result.HT_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">
		                        	공사구분<span class="fcred"> *</span>
		                    </th>
		                    <td>
		                        <select name="CNTRWK_SE" id="CNTRWK_SE" class="select" style="width: 100%;">
									<option value="">===== 전체 =====</option>
									<c:forEach var="code" items="${codesCWSE}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == result.CNTRWK_SE}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
		                    </td>
		                    <th scope="row">
		                        	공사분류<span class="fcred"> *</span>
		                    </th>
		                    <td>
		                        <select name="CNTRWK_CL" id="CNTRWK_CL" class="select" style="width: 100%;">
									<option value="">===== 전체 =====</option>
									<c:forEach var="code" items="${codesCWCL}">
									<option value="${code.CODE_VAL}" <c:if test = "${code.CODE_VAL == result.CNTRWK_CL}"> selected="selected" </c:if> >${code.CODE_NM}</option>
									</c:forEach>
								</select>
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row">
		                        	공사명<span class="fcred"> *</span>
		                    </th>
							<td colspan="3">
								<label for="FULL_CNTRWK_NM"></label>
								<input type="text" name="FULL_CNTRWK_NM" id="FULL_CNTRWK_NM" maxlength="200" value="<c:out value="${result.FULL_CNTRWK_NM}"/>" class="MX_200 CS_50 input" style="width: 99.4%;"  />	<!-- placeholder="예) 2016년 노후포장도로 정비공사" -->
								<label for="DETAIL_CNTRWK_NM"></label>
								<input type="text" name="DETAIL_CNTRWK_NM" id="DETAIL_CNTRWK_NM"  maxlength="200" value="<c:out value="${result.FULL_CNTRWK_NM}"/>" class="MX_200 CS_50 input" style="display: none; width: 99.4%;" />	<!-- placeholder="예) 2016년 노후포장도로 정비공사" -->
							</td>
		                </tr>
		                <tr>
		               		<th scope="row">
		                        	착공일 ~ 준공일<span class="fcred"> *</span>
		                    </th>
							<td>
								<label for="STRWRK_DE"></label>
								<input type="text" name="STRWRK_DE" id="STRWRK_DE" value="<c:out value="${result.STRWRK_DE}"/>" class="DT_DATE input" style="margin-right: 5px;"/>
								-
								<label for="COMPET_DE"></label>
								<input type="text" name="COMPET_DE" id="COMPET_DE" value="<c:out value="${result.COMPET_DE}"/>" class="DT_DATE input" style="margin: 0px 5px;" />
							</td>
							<th scope="row">
		                        	시공사<span class="fcred"> *</span>
		                    </th>
							<td>
								<input type="hidden" name="CNSTRCT_CO_NM" id="CNSTRCT_CO_NM" />
								<label for="CNSTRCT_CO_NO"></label>
								<select name="CNSTRCT_CO_NO" id="CNSTRCT_CO_NO" onchange="fnChangeCompany('CNSTRCT_CO')" class="select" style="width: 100%;">
									<option value="">===== 전체 =====</option>
									<c:forEach var="company" items="${companyList}">
									<option value="${company.CO_NO}" <c:if test = "${company.CO_NO == result.CNSTRCT_CO_NO}"> selected="selected" </c:if> >${company.CO_NM}</option>
									</c:forEach>
								</select>
							</td>
		                </tr>
		                <%-- <tr>
							<th scope="row">
								하자기간
							</th>
							<td colspan="3">
								<label for="FLAW_BEGIN_DE"></label>
								<input type="text" name="FLAW_BEGIN_DE" id="FLAW_BEGIN_DE" value="<c:out value="${result.FLAW_BEGIN_DE}"/>" class="DT_DATE input" />
								-
								<label for="FLAW_END_DE"></label>
								<input type="text" name="FLAW_END_DE" id="FLAW_END_DE" value="<c:out value="${result.FLAW_END_DE}"/>" class="DT_DATE input" />
							</td>
						</tr> --%>
						<%-- <tr>	
							
							<th scope="row">감리사</th>
							<td>
								<input type="hidden" name="SPRVISN_CO_NM" id="SPRVISN_CO_NM" />
								<label for="SPRVISN_CO_NO"></label>
								<select name="SPRVISN_CO_NO" id="SPRVISN_CO_NO" onchange="fnChangeCompany('SPRVISN_CO')" class="select" style="width: 100%;" >
									<option value="">선택</option>
									<c:forEach var="company" items="${companyList}">
									<option value="${company.CO_NO}" <c:if test = "${company.CO_NO == result.SPRVISN_CO_NO}"> selected="selected" </c:if> >${company.CO_NM}</option>
									</c:forEach>
								</select>
							</td>
						</tr> --%>
						<tr>
							<th scope="row">
		                        	시공사 대표자<span class="fcred"> *</span>
		                    </th>
							<td>
								<label for="CNSTRCT_CO_RPRSNTV_NM"></label>
								<input type="text" name="CNSTRCT_CO_RPRSNTV_NM" id="CNSTRCT_CO_RPRSNTV_NM" value="<c:out value="${result.CNSTRCT_CO_RPRSNTV_NM}"/>" class="MX_20 CS_25 input" style="width: 98.5%;" />
							</td>
							<th scope="row">
		                        	시공사 대표번호<span class="fcred"> *</span>
		                    </th>
							<td>
								<label for="CNSTRCT_CO_TELNO"></label>
								<input type="text" name="CNSTRCT_CO_TELNO" id="CNSTRCT_CO_TELNO" value="<c:out value="${result.CNSTRCT_CO_TELNO}"/>" class="MX_13 CS_25 input" style="width: 98.5%;" />
							</td>
							<%-- <th scope="row">감리사 대표자</th>
							<td>
								<label for="SPRVISN_CO_RPRSNTV_NM"></label>
								<input type="text" name="SPRVISN_CO_RPRSNTV_NM" id="SPRVISN_CO_RPRSNTV_NM" value="<c:out value="${result.SPRVISN_CO_RPRSNTV_NM}"/>" class="MX_20 CS_25 input" style="width: 98.5%;" />
							</td> --%>
						</tr>
						<%-- <tr>
							
							<th scope="row">감리사 대표번호</th>
							<td>
								<label for="SPRVISN_CO_RPRSNT_TELNO"></label>
								<input type="text" name="SPRVISN_CO_RPRSNT_TELNO" id="SPRVISN_CO_RPRSNT_TELNO" value="<c:out value="${result.SPRVISN_CO_RPRSNT_TELNO}"/>" class="MX_15 CS_25 input" style="width: 98.5%;" />
							</td>
						</tr> --%>
						<tr>
							<th scope="row">
		                        	포장두께<span class="fcred"> *</span>
		                    </th>
							<td colspan="3">
								<input type="text" name="RPAIR_THICK_DC" id="RPAIR_THICK_DC" value="<c:out value="${result.RPAIR_THICK_DC}"/>" class="MX_50 CS_25 input" placeholder="예) 표층 5~10cm, 기층 6cm" style="width: 99.4%;" />
							</td>
						</tr>
						<c:if test="${sessionScope.userinfo.IS_EXT == 'Y' && action_flag == 'UPDATE'}">
						<tr>
							<th scope="row">준공문서</th>
							<td colspan="3">
								<!-- VIEW / DOWNLOAD_ONLY / DOWNLOAD_DELETE -->
								<c:import url="/common/selectFileList.do" >
									<c:param name="FILE_NO" value="${result.FILE_NO}" />
									<c:param name="FILE_PATH" value="EXT_IBM" />
									<c:param name="FILE_MODE" value="DOWNLOAD_DELETE" />
									<c:param name="FILE_LIMIT_COUNT" value="1" />
								</c:import>
							</td>
						</tr>
						</c:if>
		        </table>
	        </div>
	        <div class="mt10 tc">
	            <div class="fr">
	            	<c:if test = "${action_flag == 'UPDATE'}">
						<c:if test="${sessionScope.userinfo.IS_ADMIN=='Y' || ( sessionScope.userinfo.DEPT_CODE==result.DEPT_CODE)}">
							<a href="#" onclick="fnSave();" class="schbtn" >저장</a>
						</c:if>
						<c:if test="${sessionScope.userinfo.IS_EXT == 'Y'}">
							<a href="#" onclick="fnSave();" class="schbtn" >저장</a>
						</c:if>
						<a href="#" onclick="COMMON_UTIL.cmMovePage('frm','<c:url value='/cntrwk/ibmView.do'/>');" class="btn_gray" >취소</a>
					</c:if>
					<c:if test = "${action_flag != 'UPDATE'}">
						<a href="#" onclick="check = false; fnSave();" class="schbtn" >등록</a>
						<%-- <c:if test="${sessionScope.userinfo.IS_EXT == 'Y'}">
							<a href="#" onclick="fnSave();" class="schbtn" >등록</a>
						</c:if> --%>
						<a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do');" class="graybtn" >취소</a>
					</c:if>
	           	</div>
	        </div>
		</div>
    </div>
	<!-- // Content -->
</div>
<!-- // wrap -->
</form>

<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성 (현재 년도에 해당하는 달력만 표시)
	var nowDate = new Date();
    var getYear = nowDate.getFullYear();
    //cmCreateFixYearDatepicker('STRWRK_DE', 'COMPET_DE', getYear, 10);
    //cmCreateFixYearDatepicker('FLAW_BEGIN_DE', 'FLAW_END_DE', getYear*1+2, 10);
    
    // 달력 생성 (전체)
	COMMON_UTIL.cmCreateDatepickerLinked('STRWRK_DE','COMPET_DE', 10);
	COMMON_UTIL.cmCreateDatepickerLinked('FLAW_BEGIN_DE','FLAW_END_DE', 10);
	
	// 하자일자 자동 등록 처리
	$('#STRWRK_DE').change(function() {
		var d = $.datepicker.parseDate('yy-mm-dd', $(this).val() );
		d.setFullYear( d.getFullYear() + 2 );

		$('#FLAW_BEGIN_DE').datepicker('setDate', d);
		
		// 공사년도와 상/하반기 구분 자동 세팅
		try {
			$('#CNTRWK_YEAR').val( $(this).val().substr(0,4) );

			if( parseInt( $(this).val().substr(5,2) ) < 7 )
				$('#HT_SE').val( 'HTSE0001' );//상반기
			else
				$('#HT_SE').val( 'HTSE0002' );//하반기
		} catch(E) {}
			
	});
	$('#COMPET_DE').change(function() {
		var d = $.datepicker.parseDate('yy-mm-dd', $(this).val() );
		d.setFullYear( d.getFullYear() + 2 );

		$('#FLAW_END_DE').datepicker('setDate', d);
	});
	
	// 공사명 초기 입력
	if("${action_flag}"=='INSERT') {
		var bscode = $("#DEPT_CODE option:selected");
			bscode = $(bscode).val()?" ("+COMMON_LANG.trimdata($(bscode).text())+")":"";
		$("#FULL_CNTRWK_NM").val( COMMON_LANG.trimdata($("#CNTRWK_YEAR").val()) + "년 " + COMMON_LANG.trimdata($("#HT_SE  option:selected").text()) + " " + COMMON_LANG.trimdata($("#CNTRWK_SE option:selected").text()) + bscode );
	}
	
	// 기타일때 텍스트박스 활성
	if( $("#CNTRWK_SE").val() == "CWSE0006" ){
		$("#DETAIL_CNTRWK_NM").show();
		$("#FULL_CNTRWK_NM").hide();
		$("#DETAIL_CNTRWK_NM").attr("disabled", false);
		$("#FULL_CNTRWK_NM").attr("disabled", true);
	} else {
		$("#DETAIL_CNTRWK_NM").attr("disabled", true);
	}
	
	// 공사명 입력 처리
	$("#CNTRWK_YEAR, #HT_SE, #CNTRWK_SE, #DEPT_CODE").change(function(){;
		var year  = $("#CNTRWK_YEAR").val()+"년";
		var gubun = COMMON_LANG.trimdata($("#HT_SE option:selected").text());
		var cate  = COMMON_LANG.trimdata($("#CNTRWK_SE option:selected").text());
		var code  = $("#CNTRWK_SE option:selected").val();
		var bscode = $("#DEPT_CODE option:selected");
			bscode = $(bscode).val()?"("+COMMON_LANG.trimdata($(bscode).text())+")":"";
		
		if( code=="CWSE0006" ){	//기타일때, 공사명 직접입력
			$("#DETAIL_CNTRWK_NM").show();
			$("#FULL_CNTRWK_NM").hide();
			$("#DETAIL_CNTRWK_NM").attr("disabled", false);
			$("#FULL_CNTRWK_NM").attr("disabled", true);
			
		} else {			//공사명 자동입력
			$("#DETAIL_CNTRWK_NM").hide();
			$("#FULL_CNTRWK_NM").show();
			$("#DETAIL_CNTRWK_NM").attr("disabled", true);
			$("#FULL_CNTRWK_NM").attr("disabled", false);
			$("#FULL_CNTRWK_NM").val( year + " " + gubun + " " + cate + " " + bscode );
		}
	});
	
	// 전화번호
	$('#CNSTRCT_CO_TELNO, #SPRVISN_CO_RPRSNT_TELNO').keyup(function(e) {
		
	    var s = $(this).val();
	    s = COMMON_UTIL.phoneNumFomatter(s);
	    $(this).val(s);
	});
	
});

// 도로인지 램프인지 체크 처리
function fnCellTypeCheck(obj) {
	if( $(obj).is(':checked') )
		$('#CELL_TYPE').val('RAMP');
	else
		$('#CELL_TYPE').val('');
}

//글 등록
function fnSave() {
	var msg = '<spring:message code="warn.insert.msg" />';
	
	if ( ( $("#STRWRK_DE").val().substring(0,4) && $("#COMPET_DE").val().substring(0,4) ) != $("#CNTRWK_YEAR").val() ) {
		msg = "공사년도와 착공일,준공일이 일치하지 않습니다. \n계속 "+msg;
	}
	
	/* if($("#COMPET_DE").val() > $("#FLAW_BEGIN_DE").val()){
		alert("하자시작 기간이 계약완료 기간 보다 빠릅니다. 하자기간을 재입력하시기 바랍니다.");
		$("#FLAW_BEGIN_DE").focus();
		return;
	} */
 
	if(!validateCntrwk("frm")){
        return;
	}else{
		if( confirm(msg) ) {
			// 진행 프로그래스바 생성
			COMMON_UTIL.cmShowProgressBar();

			try {
				 $('#action_flag').val('INSERT');
				// multipart/form-data 아닌 경우, mask 처리 값을 제거하여 폼 데이터를 전송 처리함
				COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", "<c:url value='/cntrwk/addCntrwk.do'/>","fnSaveCallback");
			} catch(E) {
				alert("폼데이터 변환중 오류가 발생하였습니다. :" +E);
			}
		}
	}
}

function validateCntrwk(frmId){
	var vform = $('#'+frmId);

	//부서_코드
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DEPT_CODE').val()))){
		alert("사업소에 값을 선택하세요.");
		return false;
	}
	//공사_연도
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNTRWK_YEAR').val()))){
		alert("공사년도를 선택하세요.");
		return false;
	}
	//반기_구분
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#HT_SE').val()))){
		alert("반기구분을 선택하세요.");
		return false;
	}
	//공사_구분
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNTRWK_SE').val()))){
		alert("공사구분을 선택하세요.");
		return false;
	}
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNTRWK_CL').val()))){
		alert("공사분류를 선택하세요.");
		return false;
	}
	

	if( $("#CNTRWK_SE").val() == "CWSE0006" ){
		//세부_공사_명
		if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DETAIL_CNTRWK_NM').val()))){
			alert("공사명에 값을 입력하세요.");
			return false;
		}

		//java.lang.String
		if(COMMON_LANG.trimdata( vform.find('#DETAIL_CNTRWK_NM').val()).length>200){
			alert("공사명 값은 최대 200자까지 입력할 수 있습니다.");
			return false;
		}	
		vform.find('#FULL_CNTRWK_NM').val(vform.find('#DETAIL_CNTRWK_NM').val());
	} else {
		//전체_공사_명
		if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#FULL_CNTRWK_NM').val()))){
			alert("공사명 값을 입력하세요.");
			return false;
		}
		//java.lang.String
		if(COMMON_LANG.trimdata( vform.find('#FULL_CNTRWK_NM').val()).length>200){
			alert("공사명 값은 최대 200자까지 입력할 수 있습니다.");
			return false;
		}	
	}
/*    

	//java.math.BigDecimal
	if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_NO').val()).length>22){
		alert("감리 업체 번호 값은 최대 22자까지 입력할 수 있습니다.");
		return false;
	}	
  */


	//java.lang.String
	/* if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_NM').val()).length>200){
		alert("감리_업체_명 값은 최대 200자까지 입력할 수 있습니다.");
		return false;
	}	
 */
 

/* 
	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_RPRSNT_TELNO').val()).length>15){
		alert("감리 업체 대표 전화번호 값은 최대 15자까지 입력할 수 있습니다.");
		return false;
	}	
 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_RPRSNTV_NM').val()).length>200){
		alert("감리 업체 대표자 명 값은 최대 200자까지 입력할 수 있습니다.");
		return false;
	}	
 */

	//시공_업체_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_NO').val()))){
		alert("시공사를 선택하세요.");
		return false;
	}

 
/* 

	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_NM').val()).length>200){
		alert("시공_업체_명 값은 최대 200자까지 입력할 수 있습니다.");
		return false;
	}	

 */

	//시공_업체_대표자_명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_RPRSNTV_NM').val()))){
		alert("시공사 대표자에 값을 입력하세요.");
		return false;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_RPRSNTV_NM').val()).length>200){
		alert("시공사 대표자 값은 최대 200자까지 입력할 수 있습니다.");
		return false;
	}	



	//시공_업체_전화번호
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_TELNO').val()))){
		alert("시공사 대표번호에 값을 입력하세요.");
		return false;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#CNSTRCT_CO_TELNO').val()).length>15){
		alert("시공사 대표번호 값은 최대 15자까지 입력할 수 있습니다.");
		return false;
	}	



  

	//착공_일자
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#STRWRK_DE').val()))){
		alert("착공일자에 값을 입력하세요.");
		return false;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#STRWRK_DE').val()).length>10){
		alert("착공일자 값은 최대 10자까지 입력할 수 있습니다.");
		return false;
	}	
	else if(COMMON_LANG.fn_checkDate('STRWRK_DE', 'yyyy-MM-dd' )==false){
		alert("착공일자 값은 날짜형식(yyyy-MM-dd)으로 입력되어야 합니다.");
		return false;
	}


	//준공_일자
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#COMPET_DE').val()))){
		alert("준공일자에 값을 입력하세요.");
		return false;
	}
	
 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#COMPET_DE').val()).length>10){
		alert("준공일자 값은 최대 10자까지 입력할 수 있습니다.");
		return false;
	}else if(COMMON_LANG.fn_checkDate('COMPET_DE', 'yyyy-MM-dd' )==false){
		alert("준공일자 값은 날짜형식(yyyy-MM-dd)으로 입력되어야 합니다.");
		return false;
	}	


  
 /* 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#FLAW_BEGIN_DE').val()).length>10){
		alert("하자시작일자 값은 최대 8자까지 입력할 수 있습니다.");
		return false;
	}	

	if(COMMON_LANG.isnotempty('#FLAW_BEGIN_DE') && COMMON_LANG.fn_checkDate('FLAW_BEGIN_DE', 'yyyy-MM-dd' )==false){
		alert("하자시작일자 값은 날짜형식(yyyy-MM-dd)으로 입력되어야 합니다.");
		return false;
	}	
 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#FLAW_END_DE').val()).length>10){
		alert("하자종료일자 값은 최대 8자까지 입력할 수 있습니다.");
		return false;
	}	

	if(COMMON_LANG.isnotempty('#FLAW_END_DE') && COMMON_LANG.fn_checkDate('FLAW_END_DE', 'yyyy-MM-dd' )==false){
		alert("하자종료일자 값은 날짜형식(yyyy-MM-dd)으로 입력되어야 합니다.");
		return false;
	}	
 
 */


	//보수_두께_설명
	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_DC').val()))){
		alert("포장두께에 값을 입력하세요.");
		return false;
	}

 


	//java.lang.String
	if(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_DC').val()).length>200){
		alert("포장두께 값은 최대 200자까지 입력할 수 있습니다.");
		return false;
	}	
 

 	return true;
}
// 삭제 처리 [수정:선택] 삭제후 콜백 함수명
function fnDelete() {
	if( confirm('<spring:message code="warn.delete.msg" />') ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cntrwk/ibmProcDelete.do'/>","fnDeleteCallBack");
	}
}
//---------------------------
//처리 후 callback 함수들 (필수)
//---------------------------
function fnSaveCallback( insertKey ) {
	// 목록 화면 재검색
	/* try {
		COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearch();
	}catch(E) {alert(E);}
	*/
	
	if( $('#action_flag').val()=='INSERT' )
		$("#CNTRWK_ID").val( insertKey );	// 정상 등록시 등록된 키 값을 세팅하고 해당 상세 화면으로 이동 

	COMMON_UTIL.cmMoveUrl( "cntrwk/selectCntrwkView.do?CNTRWK_ID="+$("#CNTRWK_ID").val()+"&EXT_TAB="+$("#EXT_TAB").val() );
}
function fnDeleteCallBack() {
	// 목록 화면 재검색
	try {
		/* COMMON_UTIL.cmGetWindowOpener( $("#opener_id").val() ).fnSearch();
		COMMON_UTIL.cmWindowClose( $('#wnd_id').val() ); */
		COMMON_UTIL.cmMoveUrl('cntrwk/selectCntrwkList.do');
	}catch(E) {alert(E);}
}

function fnChangeCompany(searchType){
	var co_no = $("#" + searchType + "_NO").val();
	$.ajax({
		url: contextPath + 'api/company/selectCompany.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify({CO_NO : co_no})
		,success: function(data){
			$('#' + searchType + '_NM').val(data.CO_NM);
			$('#' + searchType + '_RPRSNTV_NM').val(data.RPRSNTV_NM);
			$('#' + searchType + '_TELNO').val(data.RPRSNT_TEL_NO);
			$('#' + searchType + '_RPRSNT_TELNO').val(data.RPRSNT_TEL_NO);
		}
		,error: function(a,b,msg){
		}
	});
}
</script>
</body>
</html>