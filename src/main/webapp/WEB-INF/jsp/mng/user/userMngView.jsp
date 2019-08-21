<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body class="cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" name="USER_FLAG" id="USER_FLAG" value="${result.USER_FLAG}" />

<div id="Pop_wrap">
	<br/>
	<!-- Content -->
	<div class="ConBx">
		<div class="Pop_Section">
			<div class="PopTitBx apply"><!-- <p>사용자 정보</p> -->
					<div class="content" style="padding: 0px 10px;">
						<div class="titbx">
							<h4>사용자 일반정보</h4>
							<table class="tbview" summary="정보를 제공합니다.">
								<caption>정보</caption>
								<colgroup>
									<col width="30%" />
									<col width="70%" />
								</colgroup>
								<tbody>
									<%-- <tr>
										<th scope="row"><span class="fcred">* </span>사용자 구분
							            </th>
										<td id="USER_SE_CODE2"><c:out value="${result.USER_SE_NM}" /></td>
									</tr> --%>
									<tr>
										<th scope="row">사용자ID</th>
										<td><c:out value="${result.USER_ID}" /></td>
									</tr>
									<tr>
										<th scope="row">사용자명</th>
										<td><c:out value="${result.USER_NM}" /></td>
									</tr>
									<c:if test="${type == 'I' }">
										<tr>
											<th scope="row">소속기관</th>
											<td><c:out value="${result.DEPT_NM}" /></td>
										</tr>
										<tr>
											<th scope="row">담당업무</th>
											<td><c:out value="${result.CHRG_JOB}" /></td>
										</tr>
										<%-- <tr>
											<th scope="row">승인권한여부</th>
											<td><c:out value="${result.CONFM_AUTHOR_AT}" /></td>
										</tr> --%>
									</c:if>
									<c:if test="${type == 'O' }">
										<tr>
											<th scope="row">공사업체명</th>
											<td><c:out value="${result.CNTRWK_CO_NM}" /></td>
										</tr>
										<tr>
											<th scope="row">계약명</th>
											<td><c:out value="${result.CNTRCT_NM}" /></td>
										</tr>
										<tr>
											<th scope="row">계약기간</th>
											<td><c:out value="${result.CNTRCT_BEGIN_DE}" /> ~ <c:out
													value="${result.CNTRCT_END_DE}" /></td>
										</tr>
										<tr>
											<th scope="row">담당감독관명</th>
											<td><c:out value="${result.CHRG_MANGR_NM}" /></td>
										</tr>
									</c:if>
									<tr>
										<th scope="row">생년월일</th>
										<td>
										<fmt:parseDate value="${result.BRTHDY}" var="dateBRTHDY" pattern="yyyyMMdd" />
										<fmt:formatDate value="${dateBRTHDY}" pattern="yyyy-MM-dd" />
										</td>
									</tr>
									<%-- <tr>
										<th scope="row"><span class="fcred">* </span>시스템메뉴 접근권한그룹</th>
										<td>
											<select id="APPLY_AUTHOR_ID" alt="시스템메뉴 접근권한그룹" class="input" style="width: 99.5%;">
												<option value="">====== 전체 ======</option>
												<c:forEach items="${authList }" var="auth">
									        			<option value="${auth.AUTHOR_ID }">${auth.AUTHOR_NM }</option>
									        		</c:forEach>
								        	</select>
										</td> --%>
									<%-- <c:if test="${type == 'I' }">
										<td>도청사용자</td>
									</c:if>
									<c:if test="${type == 'O' }">
										<td>업체사용자</td>
									</c:if> --%>
										<%-- <td><c:out value="${result.AUTHOR_NM}" /></td> --%>
									<!-- </tr> -->
									<tr>
										<th scope="row">연락처</th>
										<td><c:out value="${result.CTTPC}" /></td>
									</tr>
									<tr>
										<th scope="row">이메일</th>
										<td><c:out value="${result.EMAIL}" /></td>
									</tr>
									<tr>
										<th scope="row">비고</th>
										<td style="white-space: pre-line; word-break: break-all; -ms-word-break: break-all;"><c:out value="${result.RM}" /></td>
									</tr>
									<tr>
										<th scope="row">약관동의여부</th>
										<td>
                                            <%-- <c:out value="${result.STPLAT_AGRE_AT}" /> --%>
										    <c:if test="${result.STPLAT_AGRE_AT eq 'Y'}">예</c:if>
                                            <c:if test="${result.STPLAT_AGRE_AT ne 'Y'}">아니오</c:if>
										</td>
									</tr>
									<tr>
										<th scope="row">약관동의일자</th>
										<td><fmt:formatDate value="${result.STPLAT_AGRE_DT}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									<tr>
										<th scope="row">등록일자</th>
										<td><fmt:formatDate value="${result.CREAT_DT}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									<tr>
										<th scope="row">승인여부</th>
										<td>
										  <c:if test="${result.CONFM_AT eq 'Y'}">예</c:if>
										  <c:if test="${result.CONFM_AT ne 'Y'}">아니오</c:if>
										  <%-- <c:out value="${result.CONFM_AT}" /> --%>
										</td>
									</tr>
									<tr>
										<th scope="row">승인일자</th>
										<td><fmt:formatDate value="${result.CONFM_DT}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="titbx mt20">
							<h4>사용자 권한정보</h4>
					        <table class="tbview" summary="사용자 권한정보를 입력한다.">
					        	<colgroup>
								    <col width="30%;" />
								    <col width="70%;" />
							    </colgroup>
			                   	<tbody>
			                   		<tr>
			                   			<th align="center" scope="row"><font color="red">*</font> 사용자 그룹</th>
			                   			<td>
			                   				<c:forEach items="${groupList }" var="group">
			                   					<input type="radio" name="APPLY_REQ_USER_GRP" id="APPLY_REQ_USER_GRP" style="margin-right:5px;" value="${group.AUTHOR_ID }" class="input notnull" <c:if test="${group.AUTHOR_ID == result.REQ_USER_GRP }">checked="checked"</c:if>/><c:out value="${group.AUTHOR_NM }" /><span style="margin-right:5px;"></span>
			                   				</c:forEach>
			                   			</td>
			                   		</tr>
			                   		<tr>
			                   			<th align="center" scope="row">메뉴접근 권한</th>
			                   			<td>
			                   				<c:forEach items="${menuAuthList }" var="menu">
			                   					<input type="checkbox" name="APPLY_MENUACC" style="margin-right:5px;" value="${menu.AUTHOR_ID }" <c:if test="${fn: indexOf(result.REQ_MENUACC_ROLE, menu.AUTHOR_ID) > -1}">checked="checked"</c:if>/><c:out value="${menu.AUTHOR_NM }" /><br/>
			                   				</c:forEach>
			                   			</td>
			                   		</tr>
			                   	</tbody>
					        </table>
					    </div>
					</div>
			</div>
			<form id="frm" name="frm" method="post" action="">
				<input type="hidden" id="USER_NO" name="USER_NO" value="<c:out value="${result.USER_NO}"/>"/>
				<input type="hidden" class="in" id="DEPT_CODE" name="DEPT_CODE" value="${result.DEPT_CODE }" />
				<input type="hidden" class="out" id="CNTRWK_CO_NM" name="CNTRWK_CO_NM" value="${result.CNTRWK_CO_NM }" />
				<input type="hidden" id="USER_SE_CODE" name="USER_SE_CODE" value="${result.USER_SE_CODE }" />
				<div class="PopTitBx edit"><!-- <p>사용자 정보</p> -->
					<div class="content" style="padding: 0px 10px;">
						<div class="titbx">
							<h4>사용자 일반정보</h4>
					        <table class="tbview" summary="정보를 제공합니다.">
					            <caption>정보</caption>
					            <colgroup>
					                <col width="30%" />
					                <col width="70%" />
					            </colgroup>
					            <tbody>
									<%-- <tr>
										<th scope="row"><span class="fcred">* </span>사용자 구분</th>
										<td>
											<select id="USER_SE_CODE" onchange="fn_change_userSe(this.value)" name="USER_SE_CODE" alt="사용자 구분" class="input notnull">
								        		<c:forEach items="${userSeList }" var="userSe">
									        		<option value="${userSe.CODE_VAL }" <c:if test="${userSe.CODE_VAL == result.USER_SE_CODE }"> selected="selected" </c:if>>${userSe.CODE_NM }</option>
								        		</c:forEach>
								        	</select>
										</td>
									</tr> --%>
									<tr>
										<th scope="row">사용자ID</th>
										<td>
											<input type="text" maxlength="20" alt="사용자ID" value="${result.USER_ID}" class="input notnull" readonly="readonly"  />
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="fcred">* </span>사용자명</th>
										<td>
											<input type="text" id="USER_NM" name="USER_NM" maxlength="30" onkeyup="COMMON_UTIL.fn_set_value(this.value, 'USER_NM');" alt="사용자명" value="${result.USER_NM}" class="input notnull"  />
										</td>
									</tr>
									<tr class="in">
										<th scope="row"><span class="fcred">* </span>소속기관</th>
										<td>
											<div>
									        	<select style="width: 99.5%;" id="DEPT_1" alt="소속기관" class="input notnull" onchange="fn_changeDept('DEPT_1', 'DEPT_2', '3', 'DEPT_3');">
									        		<option value="">====== 전체 ======</option>
									        		<c:forEach items="${deptCdList }" var="deptCd">
									        			<option value="${deptCd.DEPT_CODE }" <c:if test="${deptCd.DEPT_CODE == dept_1.DEPT_CODE }">selected="selected"</c:if>>${deptCd.DEPT_NM }</option>
									        		</c:forEach>
									        	</select>
								        	</div>
								        	<div>
									        	<select id="DEPT_2" alt="" style="width: 99.5%;" class="input" onchange="fn_changeDept('DEPT_2', 'DEPT_3', '4');">
									        		<option value="">====== 전체 ======</option>
									        	</select>
								        	</div>
								        	<div>
									        	<select id="DEPT_3" alt="" style="width: 99.5%;" class="input">
									        		<option value="">====== 전체 ======</option>
									        	</select>
								        	</div>
										</td>
									</tr>
									<tr class="in">
										<th scope="row"><span class="fcred">* </span>담당업무</th>
										<td>
											<input type="text" name="CHRG_JOB" id="CHRG_JOB" alt="담당업무" maxlength="100" value="${result.CHRG_JOB }" class="MX_50 input notnull"  />
										</td>
									</tr>
									<%-- <tr class="in">
										<th scope="row"><span class="fcred">* </span>승인권한여부</th>
										<td>
											<select style="width: 99.5%;" id="CONFM_AUTHOR_AT" name="CONFM_AUTHOR_AT" alt="승인권한여부" class="input notnull" >
								        		<option value="Y" <c:if test="${result.CONFM_AUTHOR_AT == 'Y' }"> selected="selected" </c:if>>Y</option>
								        		<option value="N" <c:if test="${result.CONFM_AUTHOR_AT == 'N' }"> selected="selected" </c:if>>N</option>
								        	</select>
										</td>
									</tr> --%>
									<%-- <tr>
										<th scope="row"><span class="fcred">* </span>시스템메뉴 접근권한그룹</th>
										<td>
											<select style="width: 99.5%;" id="AUTHOR_ID" name="AUTHOR_ID" alt="시스템메뉴 접근권한그룹" class="input notnull" >
												<option value="">====== 전체 ======</option>
												<c:forEach items="${authList }" var="auth">
									        			<option value="${auth.AUTHOR_ID }" <c:if test="${auth.AUTHOR_ID == result.AUTHOR_ID }">selected="selected"</c:if>>${auth.AUTHOR_NM }</option>
									        		</c:forEach>
								        	</select>
										</td>
									</tr> --%>
									<tr class="out">
										<th scope="row"><span class="fcred">* </span>공사업체</th>
										<td>
											<select style="width: 99.5%;" id="CO_NO" name="CO_NO" onchange="fn_change_comp('CO_NO', 'CNTRWK_CO_NM')" alt="공사업체" class="input notnull">
								        		<c:forEach items="${compList }" var="comp">
									        		<option value="${comp.CO_NO }" <c:if test="${comp.CO_NO == result.CO_NO }"> selected="selected" </c:if>>${comp.CO_NM }</option>
								        		</c:forEach>
								        	</select>
										</td>
									</tr>
									<tr class="out">
										<th scope="row"><span class="fcred">* </span>계약명</th>
										<td>
											<input type="text" id="CNTRCT_NM" name="CNTRCT_NM" value="${result.CNTRCT_NM}" alt="계약명" class="input MX_100 notnull" />
										</td>
									</tr>
									<tr class="out">
										<th scope="row"><span class="fcred">* </span>계약기간</th>
										<td>
											<input type="text" id="CNTRCT_BEGIN_DE" name="CNTRCT_BEGIN_DE" alt="계약시작일" value="${result.CNTRCT_BEGIN_DE}" class="DT_DATE input notnull" /> ~
											<input type="text" id="CNTRCT_END_DE" name="CNTRCT_END_DE" alt="계약종료일" value="${result.CNTRCT_END_DE}" class="DT_DATE input notnull" />
										</td>
									</tr>
									<tr class="out">
										<th scope="row"><span class="fcred">* </span>담당감독관명</th>
										<td>
											<input type="text" id="CHRG_MANGR_NM" name="CHRG_MANGR_NM" alt="담당감독자" value="${result.CHRG_MANGR_NM}" class="input notnull"  />
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="fcred">* </span>생년월일</th>
										<td>
										<fmt:parseDate value="${result.BRTHDY}" var="dateObject"   pattern="yyyyMMdd" />
										<input type="text" id="BRTHDY" name="BRTHDY" alt="생년월일" value="<fmt:formatDate value="${dateObject}" pattern="yyyyMMdd" />" class="input notnull" maxlength="8"  />
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="fcred">* </span>연락처</th>
										<td>
											<input type="tel" id="CTTPC" name="CTTPC" id="divTel" alt="전화번호" value="${result.CTTPC}" class="input notnull"  />
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="fcred">* </span>이메일</th>
										<td>
											<input type="email" id="EMAIL" name="EMAIL" id="divEmail" alt="이메일" value="${result.EMAIL}" class="input notnull"  />
										</td>
									</tr>
									<tr>
										<th scope="row">비고</th>
										<td>
											<textarea id="RM" name="RM" maxLength="1000" style="resize:none; width:95.5%;">${result.RM}</textarea>
										</td>
									</tr>
									<tr>
										<th scope="row">약관동의여부 </th>
										<td>
											<%-- <input type="text" alt="약관동의여부" value="${result.STPLAT_AGRE_AT}" class="input" readonly="readonly"/> --%>
											<c:if test="${result.STPLAT_AGRE_AT eq 'Y'}"><input type="text" alt="약관동의여부" value="예" class="input" readonly="readonly" /></c:if>
                                            <c:if test="${result.STPLAT_AGRE_AT ne 'Y'}"><input type="text" alt="약관동의여부" value="아니오" class="input" readonly="readonly" /></c:if>
										</td>
									</tr>
									<tr>
										<th scope="row">약관동의일자 </th>
										<td>

											<input type="text" alt="약관동의일자" value="<fmt:formatDate value="${result.STPLAT_AGRE_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input notnull" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<th scope="row">등록일자</th>
										<td>
											<input type="text" alt="등록일자" value="<fmt:formatDate value="${result.CREAT_DT}"  pattern="yyyy-MM-dd HH:mm:ss"/>"class="input notnull" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<th scope="row">승인여부</th>
										<td>
											<%-- <input type="text" alt="승인여부" value="${result.CONFM_AT}" class="input" readonly="readonly"/> --%>
											<c:if test="${result.CONFM_AT eq 'Y'}"><input type="text" alt="승인여부" value="예" class="input" readonly="readonly" /></c:if>
                                            <c:if test="${result.CONFM_AT ne 'Y'}"><input type="text" alt="승인여부" value="아니오" class="input" readonly="readonly" /></c:if>
										</td>
									</tr>
									<tr>
										<th scope="row">승인일자</th>
										<td>

											<input type="text" alt="승인일자" value="<fmt:formatDate value="${result.CONFM_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<th scope="row">수정자ID</th>
										<td>
											<c:out value="${result.UPDUSR_ID}"/>
										</td>
									</tr>
									<tr>
										<th scope="row">수정일시</th>
										<td>
											<fmt:formatDate value="${result.UPDT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="titbx mt20">
							<h4>사용자 권한정보</h4>
					        <table class="tbview" summary="사용자 권한정보를 입력한다.">
					        	<colgroup>
								    <col width="30%;" />
								    <col width="70%;" />
							    </colgroup>
			                   	<tbody>
			                   		<tr>
			                   			<th align="center" scope="row"><font color="red">*</font> 사용자 그룹</th>
			                   			<td>
			                   				<c:forEach items="${groupList }" var="group">
			                   					<input type="radio" name="USER_GRP" id="USER_GRP" style="margin-right:5px;" value="${group.AUTHOR_ID }" class="input notnull" title="${result.USER_GRP}" <c:if test="${group.AUTHOR_ID == result.USER_GRP }">checked="checked"</c:if>/><c:out value="${group.AUTHOR_NM }" /><span style="margin-right:5px;"></span>
			                   				</c:forEach>
			                   			</td>
			                   		</tr>
			                   		<tr>
			                   			<th align="center" scope="row">메뉴접근 권한</th>
			                   			<td>
			                   				<input type="hidden" name="MENUACC_ROLE" id="MENUACC_ROLE" value="" />
			                   				<c:forEach items="${menuAuthList }" var="menu">
			                   					<input type="checkbox" name="MENUACC" value="${menu.AUTHOR_ID }" style="margin-right:5px;" <c:if test="${fn: indexOf(result.MENUACC_ROLE, menu.AUTHOR_ID) > -1}">checked="checked"</c:if>/><c:out value="${menu.AUTHOR_NM }" /><br/>
			                   				</c:forEach>
			                   			</td>
			                   		</tr>
			                   	</tbody>
					        </table>
					    </div>
					</div>
				</div>
			</form>

			<div class="btnbx" style="margin-right:15px;">
				<a href="#" onclick="$('#frm').reset(); return false;" class="schbtn edit">초기화</a>
				<a href="#" onclick="check = false; fn_update(); return false;" class="schbtn edit">수정</a>
				<a href="#" onclick="check = false; fn_delete(); return false;" class="schbtn">삭제</a>
				<c:if test="${sessionScope.userinfo.IS_ADMIN == 'Y' }">
					<a href="#" onclick="check = false; fn_confm();" class="schbtn apply">신청 승인</a>
				</c:if>
				<a href="#" onclick="fn_close_dialog();" class="schbtn">닫기</a>
			</div>
		</div>
	</div>
</div>
	<!-- // Content -->
<!-- // wrap -->


<%@ include file="/include/common.jsp" %>
<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {
    var test = "${result.USER_GRP}";
    var grouplist = "${groupList}"
    var rrr = "${result}";
    var referrer =  document.referrer;

    if(referrer.indexOf("apply") < 0 ){ //사용자 조회
        $(".apply").hide();
        $(".edit").show();

    }
    else { // 사용자 신청 조회
        $(".apply").show();
        $(".edit").hide();
    }

    // 달력 생성 (전체)
    COMMON_UTIL.cmCreateDatepickerLinked('CNTRCT_BEGIN_DE','CNTRCT_BEGIN_DE', 10);
    COMMON_UTIL.cmCreateDatepickerLinked('CNTRCT_END_DE','CNTRCT_END_DE', 10);

    fn_change_userSe("${result.USER_SE_CODE}");
    fn_changeDept('DEPT_1', 'DEPT_2', '3', 'DEPT_3', '${dept_2.DEPT_CODE}');

    setTimeout(function(){
        fn_changeDept('DEPT_2', 'DEPT_3', '4', null, '${dept_3.DEPT_CODE}')
        }, 500);

    $("#BRTHDY").keyup(function(e){
        COMMON_UTIL.removeChar(e);
    })

});

//팝업창 닫기
function fn_close_dialog(){

    if ( $("body").hasClass("cu") ) {
	    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {

	        var wnd_id = $("#wnd_id").val();
	        COMMON_UTIL.cmWindowClose(wnd_id);

	    }
    } else {
        var wnd_id = $("#wnd_id").val();
        COMMON_UTIL.cmWindowClose(wnd_id);
    }

}


// 사용자 구분 변경에 따른 검색 항목 변경
function fn_change_userSe(val){

    $(".in").hide();
    $(".out").hide();

    switch(val){
        case "URSE0001":
            $(".in").show();

            $(".out").find("input").val("");
            $(".out").find("select").val("");
            $("#CNTRCT_NM").val("");
            $("#CO_NO").val("");
            break;
        case "URSE0002":
            $(".out").show();

            $(".in").find("input").val("");
            $(".in").find("select").val("");
            $("#DEPT_CODE").val("");
            $("#CLSF_CODE").val("");
            $("#CHRG_JOB").val("");
            break;
    }

    //사용자 구분에 따른 시스템 접근권한 변경
    //fn_sysAuth(val);

}

//사용자 신청 승인
function fn_confm(){
    //window.parent.fn_apply_confirm();

    var userNo = $("#USER_NO").val();
    var userGrp = $("input:radio[name=APPLY_REQ_USER_GRP]:checked").val();

    if(userNo == ""){
        alert("사용자 선택에 실패하였습니다. 상세조회를 다시 해주시기 바랍니다.");
        return;
    }

    if(typeof userGrp == "undefined" || userGrp == ""){
        alert("사용자 그룹을 선택해주시기 바랍니다.");
        return;
    }

    var menuAccList = [];

    $('input:checkbox[name="APPLY_MENUACC"]').each(function() {
        if(this.checked){
            menuAccList.push($(this).val());
        }
    });

    var msg = "사용자 신청 승인 처리를 진행하시겠습니까?";

    if(confirm(msg)){
        $.ajax({
            url: '<c:url value="/api/sysuser/applyConfirm.do"/>'
            ,data: {"USER_NO" : userNo,
                    "REQ_USER_GRP" : userGrp,
                    "REQ_MENUACC_ROLE" : menuAccList.join(",")}
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(data){
                $("body").removeClass("cu");
                alert( "사용자 신청 승인이 완료 되었습니다.");
                window.parent.fn_search();

                fn_close_dialog();
            }
            ,error: function(a,b,msg){
                $("body").removeClass("cu");
                alert( "승인 처리에 문제가 발생하였습니다.");
                window.parent.fn_search();
            }
        });
    }

}


//사용자 삭제
function fn_delete(){

    var userNo = $("#USER_NO").val();
    var userFlag = $("#USER_FLAG").val();

    var msg = "사용자 삭제를 진행하시겠습니까?";

    if(confirm(msg)){
        $.ajax({
            url: contextPath + 'api/sysuser/delSysUser.do'
            ,data: {"USER_NO" : userNo, "USER_FLAG" : userFlag }
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(res){

                $("body").removeClass("cu");
                alert("사용자가 삭제되었습니다.");
                window.parent.fn_search();
                fn_close_dialog();
            }
            ,error: function(a,b,msg){

            }
        });
    }

}

//사용자 정보 수정
function fn_update(){
    //null check
    if(COMMON_UTIL.fn_check_notnull("tr")) {
        return;
    }

    if(!COMMON_LANG.fn_checkDate("BRTHDY","yyyyMMdd")){
        alert("생년월일의 값이 날짜형태가 아닙니다.");
        return;
    }

    //이메일 체크
    if($("#EMAIL").val() != "" && !COMMON_UTIL.fn_check_format("email", "EMAIL")){ return; }

    //전화번호 체크
    if($("#CTTPC").val() != "" && !COMMON_UTIL.fn_check_format("tel", "CTTPC")){ return; }

    fn_get_deptCd();
    fn_change_comp('CO_NO', 'CNTRWK_CO_NM');

    /* //계약기간 format 변경
    $("#CNTRCT_BEGIN_DE").val($("#CNTRCT_BEGIN_DE").val().replace(/-/gi, '/').substring(2));
    $("#CNTRCT_END_DE").val($("#CNTRCT_END_DE").val().replace(/-/gi, '/').substring(2)); */

    var menuAccList = [];

    $('input:checkbox[name="MENUACC"]').each(function() {
        if(this.checked){
            menuAccList.push($(this).val());
        }
    });

    $("#MENUACC_ROLE").val(menuAccList.join(","));

    if(confirm('<spring:message code="warn.update.msg" />')){

        $.ajax({
            url: contextPath + 'api/sysuser/updtSysUser.do'
            ,data: $("#frm").cmSerializeObject()
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(res){
                $("body").removeClass("cu");
                alert("사용자 정보가 수정되었습니다.");
                window.parent.fn_search();
                fn_close_dialog();
            }
            ,error: function(a,b,msg){

            }
        });
    }
}

//소속기관 값 입력
function fn_get_deptCd(){
    var value = "";
    var dept1 = $("#DEPT_1").val();
    var dept2 = $("#DEPT_2").val();
    var dept3 = $("#DEPT_3").val();

    if(dept3 != ""){
        value = dept3;
    }else if(dept2 != ""){
        value = dept2;
    }else {
        value = dept1;
    }

    $("#DEPT_CODE").val(value);

}


//업체정보 변경
function fn_change_comp(checkDivId, targetDivId){
    var value = $("#" + checkDivId + " option:checked").text();
    $("#" + targetDivId).val(value);
}

/* //사용자 구분에 따른 시스템 접근권한 변경
function fn_sysAuth(val){

    var innerHtml = "<option value='' selected='selected'>====== 전체 ======</option>";
    var gtxt = $("#DEPT_1 option:selected").text();//소속기관명

     $.ajax({
        url: contextPath + 'api/authority/selectAuthList.do'
        ,data: JSON.stringify()
        ,type: 'post'
        ,dataType: 'json'
        ,success: function(res){

            if(res.length < 1) {return;}

            for(var i=0; i<res.length; i++){
                //내부일때
                if(val == "URSE0001" && res[i].USER_SE == "URSE0001" && gtxt.indexOf("경기도") != -1){
                    innerHtml += "<option value='"+res[i].AUTHOR_ID+"'>"+res[i].AUTHOR_NM + "</option>";
                }else if(val == "URSE0002" && res[i].USER_SE == "URSE0002" ){//외부일때
                    innerHtml = '';
                    innerHtml += "<option value='"+res[i].AUTHOR_ID+"'>"+res[i].AUTHOR_NM + "</option>";
                }
            }

            $("#SYS_AUTHOR_AT").empty().append(innerHtml);
        }
        ,error: function(a,b,msg){

        }
    });
} */

function fn_changeDept(_oParentDivId, _oDivId, _oOdr, _oLastDivId, _oSelVal){

    //부서 항목 초기화
    if(_oOdr == "3"){
        $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
        $("#" + _oLastDivId).html("<option value=''>====== 전체 ======</option>");
    }else{
        $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
    }

    var sDeptCd = $("#" + _oParentDivId).val();
    var postdata = { "SEHIGH_DEPT_CODE" : sDeptCd
                    , "ODR" : _oOdr};

    if(sDeptCd == ""){
        $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
        return;
    }

    $.ajax({
        url: contextPath + 'api/dept/selectDeptCdList.do'
        ,data: postdata
        ,type: 'post'
        ,dataType: 'json'
        ,success: function(res){
            var innerHtml = "<option value=''>====== 전체 ======</option>";

            if(res.length < 1) {return;}

            for(var i=0; i<res.length; i++){
                if(typeof _oSelVal != "undefined" && _oSelVal != "" && _oSelVal == res[i].DEPT_CODE){
                    innerHtml += "<option value='"+res[i].DEPT_CODE+"' selected='selected' >"+res[i].LOWEST_DEPT_NM + "</option>";
                }else {
                    innerHtml += "<option value='"+res[i].DEPT_CODE+"'>"+res[i].LOWEST_DEPT_NM + "</option>";
                }

            }

            $("#" + _oDivId).html(innerHtml);
        }
        ,error: function(a,b,msg){

        }
    });

    //소속기관에 따른 시스템 메뉴 접근권한 그룹 조회
    //fn_sysAuth($("#USER_SE_CODE").val());
};

</script>
</body>
</html>