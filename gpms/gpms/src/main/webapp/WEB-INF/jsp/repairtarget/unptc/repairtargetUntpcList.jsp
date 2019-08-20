<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장관리시스템</title>
<%@ include file="/include/common_head.jsp" %>

</head>
<body >
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="untpcfrm" name="untpcfrm" method="post" action="" style="height:80%">
<!-- 필수 파라메터(END) -->
<div class="content">
    <div class="bctab">
        <ul>
            <li class="on" id="tab1"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('rpairmthduntpc/selectRpairMthdUntpcList.do')">보수공법 단가</a></li>
            <li id="tab2"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('rpairmthdfrmula/selectRpairMthdFrmulaList.do')">임계값 설정</a></li>
        </ul>
    </div>
    <div id="tabcont1">
        <table class="adminlist mt10" summary="정보를 제공합니다.">
            <caption>정보</caption>
            <colgroup>
                <col width="15%" />
                <col width="*" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">포장상태</th>
                    <th scope="col">보수공법 종류</th>
                    <th scope="col">공사비</th>
                    <th scope="col">차선도색</th>
                    <th scope="col">기타</th>
                    <th scope="col">합계(원)</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>매우 양호<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpcDoNot.UNTPC_MANAGE_NO }"/></td>
                    <td>Do Nothing<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpcDoNot.RPAIR_MTHD_CODE }"/></td>
                    <td>N/A<input type="hidden" name="CNTRWK_AMOUNT" class="amount" value="${untpcDoNot.CNTRWK_AMOUNT }"/></td>
                    <td>N/A<input type="hidden" name="PAINT_AMOUNT" class="amount" value="${untpcDoNot.PAINT_AMOUNT }"/></td>
                    <td>N/A<input type="hidden" name="ETC_AMOUNT" class="amount" value="${untpcDoNot.ETC_AMOUNT }"/></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpcDoNot.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
                <tr>
                    <td>양호<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpcSeal.UNTPC_MANAGE_NO }"/></td>
                    <td>균열 Sealing<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpcSeal.RPAIR_MTHD_CODE }"/></td>
                    <td><input type="text" name="CNTRWK_AMOUNT" value="${untpcSeal.CNTRWK_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td>N/A<input type="hidden" name="PAINT_AMOUNT" class="amount" value="${untpcSeal.PAINT_AMOUNT }"/></td>
                    <td>N/A<input type="hidden" name="ETC_AMOUNT" class="amount" value="${untpcSeal.ETC_AMOUNT }"/></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpcSeal.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
                <tr>
                    <td>보통<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpcSurf.UNTPC_MANAGE_NO }"/></td>
                    <td>표면처리<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpcSurf.RPAIR_MTHD_CODE }"/></td>
                    <td><input type="text" name="CNTRWK_AMOUNT" value="${untpcSurf.CNTRWK_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td>N/A<input type="hidden" name="PAINT_AMOUNT" class="amount" value="${untpcSurf.PAINT_AMOUNT }"/></td>
                    <td>N/A<input type="hidden" name="ETC_AMOUNT" class="amount" value="${untpcSurf.ETC_AMOUNT }"/></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpcSurf.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
                <tr>
                    <td rowspan="2">불량</td>
                    <td>50mm 덧씌우기
                    	<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpc50.UNTPC_MANAGE_NO }"/>
                    	<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpc50.RPAIR_MTHD_CODE }"/>
                    </td>
                    <td><input type="text" name="CNTRWK_AMOUNT" value="${untpc50.CNTRWK_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="PAINT_AMOUNT" value="${untpc50.PAINT_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td>N/A<input type="hidden" name="ETC_AMOUNT" class="amount" value="${untpc50.ETC_AMOUNT }" /></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpc50.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
                <tr>
                    <td>50mm 절삭 덧씌우기
                    	<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpc50CO.UNTPC_MANAGE_NO }"/>
                    	<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpc50CO.RPAIR_MTHD_CODE }"/>
                    </td>
                    <td><input type="text" name="CNTRWK_AMOUNT" value="${untpc50CO.CNTRWK_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="PAINT_AMOUNT" value="${untpc50CO.PAINT_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="ETC_AMOUNT" value="${untpc50CO.ETC_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpc50CO.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
                <tr>
                    <td>매우불량<input type="hidden" name="UNTPC_MANAGE_NO" value="${untpc75CO.UNTPC_MANAGE_NO }"/></td>
                    <td>75mm 절삭 덧씌우기<input type="hidden" name="RPAIR_MTHD_CODE" value="${untpc75CO.RPAIR_MTHD_CODE }"/></td>
                    <td><input type="text" name="CNTRWK_AMOUNT" value="${untpc75CO.CNTRWK_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="PAINT_AMOUNT" value="${untpc75CO.PAINT_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="ETC_AMOUNT" value="${untpc75CO.ETC_AMOUNT }" class="MX_80 CS_50 DT_INT input amount"/></td>
                    <td><input type="text" name="TOT_AMOUNT" value="${untpc75CO.TOT_AMOUNT }" class="MX_80 CS_50 DT_INT input totAmount"/></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="mr10 fr mt10">
        <a href="#" class="schbtn" onclick="fnRepairTargetUntpcStart();" style="width:50px">저장</a>
        <a href="#" class="graybtn" onclick="fn_close_dialog();" style="width:50px">취소</a>
    </div>
</div>
</form>  

<div id="divRTFDialog" class="content " style="display:none;z-index:9999;">
<div class="txtbx" id="txtRTFContents" >컨텐츠</div>
<div class="tc mt20" id="divRTFBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
<div class="tc mt20" id="divRTFButtn">버튼</div>
</div>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	//frm 초기화
	COMMON_UTIL.cmFormObjectInit("untpcfrm");
	
	//보수공법 단가 자동계산
	$(".amount").keyup(function(){
		var totAmount = 0;
		if($(this).val() == ""){
			$(this).val("0");
		}
		var amount = $(this).parent().parent().find(".amount");
		for(var i=0; i<amount.length; i++){
			totAmount += parseInt($(amount[i]).val().replace(/,/g,""));
		}
		
		$(this).parent().parent().find(".totAmount").val(COMMON_UTIL.cmAddComma(totAmount));
		
	});
	
}); 

//팝업창 닫기 
function fn_close_dialog(){
    
    //if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {
        var wnd_id = $("#wnd_id").val();
        COMMON_UTIL.cmWindowClose(wnd_id);     
    //}
	
}

/**
 *  저장 팝업창 생성
 */
function fnRepairTargetUntpcStart(){
	var msgContents = "변경하신 보수공법 단가를 저장하시겠습니까?<br/>저장된 단가적용을 위해서는 [범위 내 선정]을 다시 시작하시기 바랍니다.<br />";
	$("#divRTFDialog").dialog({title : "보수대상 선정 - 보수공법 단가" , width: "380px" });
	var buttonTags = '<a href="#" class="schbtn" onclick="fn_save();" style="width:70px" >저장</a>&nbsp;&nbsp;';
	buttonTags += '<a href="#" class="graybtn" onclick="fnRepairTargetUntpcClose();" style="width:70px" >취소</a>';
	$("#divRTFButtn").html(buttonTags);
	$("#txtRTFContents").html(msgContents);
	$("#divRTFDialog").dialog("open");
}

/**
 *  저장 팝업창 닫기
 */
function fnRepairTargetUntpcClose(){
	$("#divRTFDialog").dialog("close");
}


/**
 * 보수공법 단가 저장
 * skc@muhanit.kr
 * 2017-11-20
 */
function fn_save(){
	$("#divRTFDialog").dialog("close");
	COMMON_UTIL.cmShowProgressBar();
	var untpcList = [];
	var untpc = "";
	
	var trList = $("#untpcfrm").find("tr");
	
	for(var i=0; i<trList.length; i++){
		untpc = "{";
		var input = $(trList[i]).find("input");
		
		if(input.length < 1){continue;}
		
		for(var j=0; j<input.length; j++){
			var name = input[j].name;
			
			var value = $(input[j]).val();
			
			var obj = "'" + name + "' : '" + value.replace(/,/g, "") + "',";
			untpc += obj;
		}
		untpc = untpc.substring(0, untpc.length - 1) + "}";
		untpcList.push(eval("("+ untpc +")"));
	}
	
	$.ajax({
		url: contextPath + 'api/rpairmthduntpc/updateRpairMthdUntpc.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify({"UNTPC_LIST" : untpcList})
		,success: function(data){
			COMMON_UTIL.cmHideProgressBar();
			alert(data.resultMSG);
		}
		,error: function(a,b,msg){
			COMMON_UTIL.cmHideProgressBar();
			alert("보수공법 단가 저장 시 오류가 발생했습니다.");
		}
	});
	
}

$(parent).resize(function() {
    
    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();
    
    ww = ( ww / 2 ) - 450;
    wh = ( wh / 2 ) - 375;
    
    wnd.move(ww, wh);
    
});
</script>
</body>
</html>