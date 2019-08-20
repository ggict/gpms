<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
<c:if test = "${resultCode == 'DUPLICATE_USER'}">
	<form name="dupleFrm" id="dupleFrm" style="height:0px;" method="post">
		<input type="hidden" name="USER_ID" value="${userid }">
		<input type="hidden" name="SECRET_NO" value="${userpw }">
		<input type="hidden" name="callBackFunction" value="${callBackFunction }">
	</form>
</c:if>
<script src="<c:url value='/extLib/jquery/jquery.ginno.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer" charset='utf-8'>
var contextPath="${ pageContext.request.contextPath}/";
<c:set var="search" value='"' />
<c:set var="replace" value='\"' />
// 페이지 로딩 초기 설정
$( document ).ready(function() {
    try {
        //parent.cmHideProgressBar();/* 포르그래스바 숨기기 */
    } catch(E){}
    try {
<c:choose>
    <c:when test = "${resultCode == 'SAVE_SUCCESS'}">
        alert("<c:if test = "${!empty resultCnt}"><c:out value="${resultCnt}"/>건</c:if> <spring:message code="info.insert.msg" />");
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>('<c:out value="${insertKey}"/>');
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'UPDATE_SUCCESS'}">
        alert("<c:if test = "${!empty resultCnt}"><c:out value="${resultCnt}"/>건</c:if> <spring:message code="info.update.msg" />");
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>();
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'DELETE_SUCCESS'}">
        alert("<spring:message code="info.delete.msg" arguments="${resultCnt}" />");
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>();
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'DELETE_SUCCESS2'}">
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>();
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'FILE_DELETED'}">
        alert("<spring:message code="info.delete.file.msg" />");
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>();
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'NO_DATA'}">
        alert("<spring:message code="info.nodata.msg" />");
    </c:when>
    <c:when test = "${resultCode == 'ERROR'}">
        alert("<spring:message code="message.error.msg" arguments="${fn:replace(resultMsg, search, replace)}" />");
    </c:when>
    <c:when test = "${resultCode == 'VALIDATION'}">
        alert("${resultMsg}");
    </c:when>
    <c:when test = "${resultCode == 'MSG'}">
        <c:if test = "${!empty resultMsg}">
        alert("${resultMsg}");
        </c:if>
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>(<c:if test = "${!empty resultExtSysId}">"<c:out value="${resultExtSysId}"/>"</c:if>);
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'CALLBACK'}">
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>(<c:if test = "${!empty resultExtSysId}">"<c:out value="${resultExtSysId}"/>"</c:if>);
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'IMAGE_SAVE_SUCCESS'}">
        alert("<spring:message code="info.insert.msg" />");
        <c:if test = "${!empty callBackFunction}">
        parent.<c:out value="${callBackFunction}"/>('<c:out value="${insertKey}"/>');
        </c:if>
    </c:when>
    <c:when test = "${resultCode == 'IMAGE_SAVE_FAIL'}">
    alert("파일등록에 실패하였습니다 (유효하지 않은 파일 확장자)");
    <c:if test = "${!empty callBackFunction}">
    parent.<c:out value="${callBackFunction}"/>('<c:out value="${insertKey}"/>');
    </c:if>
    </c:when>
    <c:when test = "${resultCode == 'LOGIN_FAIL'}">
        alert("${resultMsg}");
        /* parent.location.href = contextPath + "main.do"; */
        </c:when>
    <c:when test = "${resultCode == 'DUPLICATE_USER'}">
        if(confirm("${resultMsg}")){
            $("#dupleFrm").attr("action", contextPath +"setDupleUserPorc.do");
            $("#dupleFrm").submit();
        }else{
            parent.location.href = contextPath + "main.do";
        };

    </c:when>
</c:choose>
    } catch(E) {alert(E);}
});
</script>
</body>
</html>