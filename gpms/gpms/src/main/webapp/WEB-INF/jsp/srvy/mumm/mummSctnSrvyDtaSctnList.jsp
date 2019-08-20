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
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>

</head>

<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div class="tabcont">
    <div id="res_SctnList"></div>
</div>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javaScript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
    
    // 상세보기로 넘어온 경우 파라미터 받기
    var param = {};
    param["OBJECT_ID"] = "${mummSctnSrvyDtaVO.OBJECT_ID}";
    
    fnSelectList(param);
    
});

// 포장상태 조사정보 섹션 리스트
function fnSelectList(param) {

    var postData = { "OBJECT_ID": param.OBJECT_ID };

    $.ajax({
        url: "<c:url value='/api/mummsctnsrvydta/mummSctnSrvyDtaSctnList.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {
            
            if ( jdata != undefined && jdata != null && jdata != "" ) {

                var html = "";
                html += "<table class='tblist' style='background-color:#fff;'>"
                      + "    <colgroup>" 
                      + "        <col width='10%'/>" 
                      + "        <col width='20%'/>" 
                      + "        <col width='20%'/>"
                      + "        <col width='12%'/>" 
                      + "        <col width='12%'/>"
                      + "        <col width='13%'/>"
                      + "        <col width='13%'/>"
                      + "    </colgroup>"
                      + "    <thead>"
                      + "        <tr>"
                      + "            <th scope='col'>노선번호</th>"
                      + "            <th scope='col'>노선명</th>"
                      + "            <th scope='col'>도로등급</th>"
                      + "            <th scope='col'>차로</th>"
                      + "            <th scope='col'>행선</th>"
                      + "            <th scope='col'>시점</th>"
                      + "            <th scope='col'>종점</th>"
                      + "        </tr>"
                      + "    </thead>"
                      + "    <tbody>";
                
                for ( var i = 0; i < jdata.length; i++ ) {
                    
                    html += "<tr onclick='fnSearchStatusResearch(\"" + jdata[i].OBJECT_ID + "\")'>"
                          + "    <td>" + jdata[i].ROAD_NO_VAL + "</td>"
                          + "    <td>" + jdata[i].ROAD_NM + "</td>"
                          + "    <td>" + jdata[i].ROAD_GRAD + "</td>"
                          + "    <td>" + jdata[i].TRACK + "</td>"
                          + "    <td>" + jdata[i].DIRECT_NM + "</td>"
                          + "    <td>" + jdata[i].STRTPT + "</td>"
                          + "    <td>" + jdata[i].ENDPT + "</td>"
                          + "</tr>";
                    
                }
                
                html += "</tbody>"
                      + "</table>";
                
                
                $("#res_SctnList").html(html);
                
            }
            
        },
        error: function (err) {

            alert("값을 가져올 수 없습니다.");
            parent.gMap.cleanMap();
            return;

        }
    });
    
}

//STEP 3 조사정보 - 정보 조회
function fnSearchStatusResearch(objectIds) {
    
    var opener = $("#opener_id").val();
    
    parent.$("#" + opener).find("iframe").get(0).contentWindow.fnSearchStatusResearch(objectIds);
    
}

</script>
</body>
</html>