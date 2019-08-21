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

<body>
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div id="Pop_wrap">
    <br/>
    <!-- Content -->
    <div class="ConBx">
        <div class="content">
        <form id="frm" name="frm" method="post" action="">
            <ul class="toolbtn" style="position: relative; top: 8px;">
                <li style="margin: 0px 25px;">
                    <a href="javascript:fn_mntEntrst('Y');" id="btn_MntEntrst_Y"><img src="<c:url value='/images/mntEntY.png'/>" alt="단원 위촉" /></a>
                    <span>단원 위촉</span>
                </li>
                <li style="margin: 0px 25px;">
                    <a href="javascript:fn_mntEntrst('N');" id="btn_MntEntrst_N"><img src="<c:url value='/images/mntEntN.png'/>" alt="위촉 해제" /></a>
                    <span>위촉 해제</span>
                </li>
            </ul>
        </form>
        </div>
    </div>
    <!-- // Content -->
</div>

<!-- // wrap -->

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {


});


//팝업창 닫기
function fn_close_dialog(){

    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {

        var wnd_id = $("#wnd_id").val();
        COMMON_UTIL.cmWindowClose(wnd_id);
    }
}


//단원위촉 or 위촉해제 버튼 클릭 시
function fn_mntEntrst(YN){
	var rowId = parent.$("#gridArea").getGridParam("selarrrow");

	var vhcle_no = [];
	var bsnm_nm = [];
	var entrst_at = YN;

	if( confirm("정말 변경하시겠습니까?") ) {
		for(var i=0; i<rowId.length; i++){
			var rowData = parent.$("#gridArea").getRowData(rowId[i]);
			vhcle_no.push(rowData["VHCLE_NO"]);
			bsnm_nm.push(rowData["BSNM_NM"]);
		}

		dataList = {"VHCLE_NO_ARR" : vhcle_no, "BSNM_NM_ARR" : bsnm_nm, "ENTRST_AT" : entrst_at, "ENTRST_DE" : null } ;

		// 위촉일시를 넣자
        if( entrst_at == 'Y' ) {
        	var date = new Date();
        	dataList["ENTRST_DE"] = date;
        }

	    $.ajax({
	        url: contextPath + 'monitor/updateMntrngMbrEntrst.do'
	        ,data: JSON.stringify(dataList)
	        ,contentType: "application/json;charset=UTF-8"
	        ,dataType: "json"
	        ,cache: false
	        ,type: 'POST'
	        ,processData: false
	        ,success: function(res){
	            alert("모니터링단원 정보가 수정되었습니다.");
	            window.parent.fn_search();
	            var wnd_id = $("#wnd_id").val();
	            COMMON_UTIL.cmWindowClose(wnd_id);
	        }
	        ,error: function(a,b,msg){
	            alert("수정처리에 문제가 발생하였습니다.");

	        }
	    });
	}
};

</script>


<%@ include file="/include/common.jsp" %>
</body>
</html>