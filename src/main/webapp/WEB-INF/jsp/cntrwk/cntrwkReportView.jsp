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
 
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {
      $("#CNTRWK_YEAR").val(String(new Date().getFullYear()));
});

function fn_download_report_excel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		// SUBMIT
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/cntrwk/downloadReport.do'/>", "");
	}
}

$(parent).resize(function() {
    
    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();
    
    ww = ( ww / 2 ) - 160;
    wh = ( wh / 2 ) - 75;
    
    wnd.move(ww, wh);
    
});
</script>
</head>

<body>

<form id="frm" name="frm" method="post" action="" style="height:60px">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<!-- KEY 파라메터 -->

<!-- Content -->
<div class="content">
	<div class="table">
        <table>
            <tbody>
                <tr>
                    <td class="th">
                        <label for="CNTRWK_YEAR">기간</label>
                    </th>
                    <td>
                        <select name="CNTRWK_YEAR" id="CNTRWK_YEAR">
    						<c:forEach var="selectData" items="${yearList}">
    						<option value="${selectData.CNTRWK_YEAR}">${selectData.CNTRWK_YEAR}</option>
    						</c:forEach>
    					</select>년
                    </td>
                </tr>
                <tr>
                    <td class="th">
                        <label for="DEPT_CODE">관리 기관</label>
                    </th>
                    <td>
                        <select name="DEPT_CODE" id="DEPT_CODE">
    						<option value="">전체</option>
    						<c:forEach var="selectData" items="${deptList}">
    						<option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
    						</c:forEach>
    					</select>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="btnArea">
    	<input type="button" class="btn pri" onclick="fn_download_report_excel();" value="엑셀저장" >
    </div>
    
</div>
</form>
<%@ include file="/include/common.jsp" %>
</body>
</html>