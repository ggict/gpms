<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>경기도로 모니터링단 시스템</title>
<%@ include file="/include/header.jsp" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/reset.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/login.css'/>" />

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- Save for Web Slices (bg_login_pothole.png) -->

<table>
    <tr>
        <td colspan="3">
            <img src='<c:url value="/images/change/bg_change_01.gif" />'>
        </td>
    </tr>
    <tr>
        <td rowspan="2">
            <img src='<c:url value="/images/change/bg_change_02.gif" />'>
        <td>
            <a href="http://105.0.111.7:3300/gpms/pothole-main.do"><img src='<c:url value="/images/change/bg_change_03.png" />'></a>
        </td>
        <td rowspan="2">
            <img src='<c:url value="/images/change/bg_change_04.gif" />'>
        </td>
    </tr>
    <tr>
        <td>
            <img src='<c:url value="/images/change/bg_change_05.gif" />'>
        </td>
    </tr>
</table>

<script>

$(document).ready(function() {
    
    var width = $(document).width() / 2;
    $("table").css( "margin-left", (width - 500) + "px" );
    
})

$(window).resize(function() {
    
    var width = $(document).width() / 2;
    $("table").css( "margin-left", (width - 500) + "px" );
    
})

</script>
</body>
</html>
1