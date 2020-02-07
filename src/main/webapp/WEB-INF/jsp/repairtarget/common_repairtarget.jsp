<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="srtmenuarea">
    <div id="sub_repairtargets">
        <a href="#" class="leftmenu" onclick="fnOpenRpairConfig();">환경설정</a>
        <h3 class="h3 borderNone">보수대상 선정</h3>
    		<div class="table">
    			<table>
    				<tbody>
    				<tr>
    					<td class="th"><label>분석단위</label></td>
    					<td>
    					 	<input type="hidden" id="SLCTN_MTH" name="SLCTN_MTH" value=""/>
			                <input type="hidden" id="SLCTN_STTUS" name="SLCTN_STTUS" value="RTSS0001"/>  <%-- 보수대상선정상태(RTSS)[시작(RTSS0001), 임시저장(RTSS0002), 보수대상선정(RTSS0003)...] --%>
			                <input type="hidden" id="SLCTN_PURPS" name="SLCTN_PURPS" value="RTSP0002"/>  <%-- 보수대상선정목적(RTSP)[차트용(시스템이 생성)(RTSP0001), 사용자 보수 대상 선정(RTSP0002)] --%>
			                <input type="hidden" id="TRGET_SLCTN_NO" name="TRGET_SLCTN_NO" value=""/>

			            <c:forEach var="anun" items="${anunList}" varStatus="status">
			                <input type="radio" id="ANALS_UNIT_CODE<c:out value="${status.index}" />" name="ANALS_UNIT_CODE" value="<c:out value="${anun.CODE_VAL}" />" data-anals_unit_nm="<c:out value="${anun.CODE_NM}" />" /><label for="ANALS_UNIT_CODE<c:out value="${status.index}" />" class="wid30" ><c:out value="${anun.CODE_NM}" /></label>
			            </c:forEach>
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="SLCTN_YEAR">대상연도</label></td>
    					<td>
	    					<select id="SLCTN_YEAR" name="SLCTN_YEAR">
			                    <c:forEach var="slctnYear" items="${slctnYearList}">
			                        <option value="${slctnYear}">${slctnYear}년</option>
			                    </c:forEach>
			                </select>
                        </td>
    				</tr>
    				</tbody>
    			</table>
                <div class="btnArea">
                     <input type="button" class="btn pri" value="선정시작" onclick="fnRepairTargetStart();">
                </div>
    		</div>


    		<h3 class="h3">보수대상선정이력</h3>
    		<div class="scroll" id="repairTargetList"></div>

</div>
</div>
<script type="text/javascript" defer="defer">
$( document ).ready(function() {
	// 보수대상선정이력 출력크기 조절
    $("#repairTargetList").height($(parent.window).height() - 510);
});

$(window).resize(function(){
	// 보수대상선정이력 출력크기 조절
    $("#repairTargetList").height($(parent.window).height() - 510);
});

/**
 * 보수대상선정 최초 로딩시 intro 화면 호출
 * (common.js -> repairtargetsOpen(), utils -> fn_open_subMenu())
 */
function initRepairTargets(){
    var currentDate = new Date();
    var yyyyMMddNow = currentDate.format("yyyy-MM-dd");
    $("#sub_repairtargets").find("#SLCTN_DT").val(yyyyMMddNow);
    $("#sub_repairtargets").find("#TRGET_SLCTN_NO").val('');
    $("#sub_repairtargets").find("#SLCTN_OPERT_NM").val('');
    $("#sub_repairtargets").find("#SLCTN_OPERT_NM").text('');

    $("#repairTargetList").css("display", "");
    COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/intro.do"/>');
    $("#repairTargetList").css("display", "");
    fnRefreshRepairTarget();
}

/**
 * 보수대상선정이력 목록
 */
function fnRefreshRepairTarget(){
    var action = '<c:url value="/api/rpairtrgetslctn/selectRpairTrgetSlctnList.do"/>';

    var postData ={"USE_AT":"Y"};
    $.ajax({
        url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function(data){
        	$("#repairTargetList").empty();
        	// 년도이력 출력
        	$.each(data, function() {
//         	    var yearHis = '<h5 class="pt10 pb10"><a href="javascript:loadRepairTargets(' + this.SLCTN_YEAR + ', ' + this.TRGET_SLCTN_NO + ')"><img src="images/ic_date.png" alt="날짜선택" class="vm" style="padding-left: 15px;" />&nbsp;&nbsp;'+ this.SLCTN_YEAR +'년</a></h5>';
        	    var yearHis = '<h5 class="pt10 pb10"><a href="javascript:fnRepairTargetLoad(' + this.TRGET_SLCTN_NO + ')"><img src="images/ic_date.png" alt="날짜선택" class="vm" style="padding-left: 15px;" />&nbsp;&nbsp;'+ this.SLCTN_YEAR +'년 '+ this.ANALS_UNIT_NM + '</a></h5>';
                $("#repairTargetList").append(yearHis);
        	});

        	if ( $("#repairTargetList h5 a").length > 0 ) {
    		   $("#repairTargetList h5 a").get(0).click();
        	}
        },
        error: function(a,b,msg){
            console.log(a);
            console.log(b);
            alert(msg);
        }
    });
}

/**
 * 보수대상 선정이력 불러오기
 */
function loadRepairTargets(slctn_opert_nm, trget_slctn_no){
    var msgContents = "\""+slctn_opert_nm + "\"년 불러오기를 수행 하시겠습니까?<br/>";
    $("#divRTDialog").dialog({title : "보수대상 선정 – 보수대상 선정 이력 불러오기" , width: "380px" });
    var buttonTags = '<a href="#" class="schbtn" onclick="fnRepairTargetLoad(\''+trget_slctn_no+'\');" style="width:70px" >불러오기</a>&nbsp;&nbsp;';
    buttonTags += '<a href="#" class="graybtn" onclick="closeRTDialog();" style="width:70px" >취소</a>';
    $("#divRTButtn").html(buttonTags);
    $("#txtRTContents").html(msgContents);
    $("#divRTDialog").dialog("open");
}

/**
 * 보수대상 선정시작 팝업
 */
function fnRepairTargetStart(){
	var slctnYear = $("#SLCTN_YEAR").val();
	var analsUnitNm = $("input[name='ANALS_UNIT_CODE']:checked").data("anals_unit_nm");
    var msgContents = "보수대상 선정작업을 시작하시겠습니까?<br/>" + slctnYear + "년 " + analsUnitNm + "로 선정된 데이터가 존재한다면 초기화됩니다.<br />";
    $("#divRTDialog").dialog({title : "보수대상 선정 - 선정 시작" , width: "380px" });
    var buttonTags = '<a href="#" class="schbtn" onclick="repairTargetStart();" style="width:70px" >선정 시작</a>&nbsp;&nbsp;';
    buttonTags += '<a href="#" class="graybtn" onclick="closeRTDialog();" style="width:70px" >취소</a>';
    $("#divRTButtn").html(buttonTags);
    $("#txtRTContents").html(msgContents);
    $("#divRTDialog").dialog("open");
}

/**
 * 보수대상 선정시작 처리
 */
function repairTargetStart(){
    var vForm = $("#sub_repairtargets");
    var postData ={
		"SLCTN_OPERT_NM"  : vForm.find('#SLCTN_OPERT_NM').val(),
        "SLCTN_DT"        : vForm.find('#SLCTN_DT').val(),
        "SLCTN_MTH"       : vForm.find('#SLCTN_MTH').val(),
        "SLCTN_STTUS"     : vForm.find('#SLCTN_STTUS').val(),
        "SLCTN_PURPS"     : vForm.find('#SLCTN_PURPS').val(),
        "ANALS_UNIT_CODE" : vForm.find('input[id^=ANALS_UNIT_CODE]:checked').val(),
        "SLCTN_YEAR"      : vForm.find('#SLCTN_YEAR').val(),
    };
    if ( COMMON_LANG.isnotempty(postData.ANALS_UNIT_CODE) == false ) {
        alert("분석단위를 입력하십시오.");
        return;
    }
    if ( COMMON_LANG.isnotempty(postData.SLCTN_YEAR) == false ) {
        alert("보수대상 선정년도를 선택 하십시오.");
        return;
    }

    // 임시선정여부 사용안함.
//     if($("#content_repairtargets")[0].contentWindow.fnInitRangeSelection!=null){
//         if(typeof($("#content_repairtargets")[0].contentWindow.fnInitRangeSelection)=="function" ){
//             $("#content_repairtargets")[0].contentWindow.fnInitRangeSelection(false);
//         }
//     }

    $.ajax({
        url           : "<c:url value="/api/rpairtrgetslctn/addRpairTrgetSlctn.do"/>",
        contentType   : "application/json",
        data          : JSON.stringify(postData),
        dataType      : "json",
        cache         : false,
        type          : 'POST',
        processData   : false,
        success       : function(data){
        	if ( data.resultSuccess == "true" ) {
        	    alert(data.resultMSG);

                vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
                fnRefreshRepairTarget();
                closeRTDialog();
                COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
        	} else {
        	    closeRTDialog();
        	    alert(data.resultMSG);
        	}
        },
        error: function(a,b,msg){
            alert(JSON.stringify(data));
        }
    });
}

/**
 * 보수대상선정 그룹 목록 불러오기.
 */
function fnRepairTargetLoad(trget_slctn_no){
    COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+trget_slctn_no);
//     closeRTDialog();
}

/**
 * 보수대상 선정시작 팝업 닫기.
 */
function closeRTDialog(){
    $("#lodingbar").hide();
    $("#divRTDialog").dialog("close");
}

/**
 * 보수대상 환경설정 창 생성
 */
function fnOpenRpairConfig(){
    COMMON_UTIL.cmWindowOpen("보수대상 환경설정", contextPath + "rpairmthduntpc/selectRpairMthdUntpcList.do", "900", "750", true, null);
}

</script>
<%-- --%>