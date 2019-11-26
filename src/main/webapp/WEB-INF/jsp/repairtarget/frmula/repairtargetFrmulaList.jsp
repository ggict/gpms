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
<form id="frm" name="frm" method="post" action="" style="height:80%">
<!-- 필수 파라메터(END) -->
<div class="content">
        <div class="bctab">
            <ul>
                <li id="tab1"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('rpairmthduntpc/selectRpairMthdUntpcList.do')">보수공법 단가</a></li>
                <li class="on" id="tab2"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('rpairmthdfrmula/selectRpairMthdFrmulaList.do')">임계값 설정</a></li>
            </ul>
        </div>
        <div id="tabcont2">
            <h4 class="stit">보수공법 선정 알고리즘</h4>
            <div class="graybx posiR">
                <img src="<c:url value='/images/flow1.png'/>" />
                <a href="#" class="probtn1 posiA frmulaBtn" style="bottom:10px;left:10px;width:165px" id="BTN_SPATIAL" onclick="fn_initFrmula('SPATIAL');" >① 특별관리도로 보수공법</a>
                <a href="#" class="probtn1 posiA frmulaBtn" style="bottom:10px;left:215px;width:160px" id="BTN_INTERSECT" onclick="fn_initFrmula('INTERSECT');">② 교차로 보수공법</a>
                <a href="#" class="probtn1 posiA frmulaBtn" style="bottom:10px;left:420px;width:160px" id="BTN_1TRACK" onclick="fn_initFrmula('1TRACK');">③ 1차로 보수공법</a>
                <a href="#" class="probtn2 posiA frmulaBtn" style="top:37px;left:640px;width:160px" id="BTN_NOMAL" onclick="fn_initFrmula('NOMAL');">④ 일반도로 보수공법</a>
            </div>
            <!-- 일반도로 보수공법 Start -->
            <div id="DIV_NOMAL" class="posiR divFrmula">
                <img src="<c:url value='/images/flow2.png'/>" />
                <div class="posiA GPCI" style="top:50px;left:55px">
                	<input type="text" id="NOMAL_GPCI" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA LCTC1" style="top:170px;left:55px;" >
                	<input type="text" id="NOMAL_LCTC1" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA RD" style="top:50px;left:440px;">
                	<input type="text" id="NOMAL_RD" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA LCTC2" style="top:170px;left:440px;" >
                	<input type="text" id="NOMAL_LCTC2" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA AC" style="top:290px;left:440px;">
                	<input type="text" id="NOMAL_AC" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
            </div>
            <!-- 일반도로 보수공법 End -->
            <!-- 특별관리도로 보수공법 Start -->
            <div id="DIV_SPATIAL" class="posiR divFrmula" style="display:none;">
                <img src="<c:url value='/images/flow2.png'/>" />
                <div class="posiA GPCI" style="top:50px;left:55px">
                	<input type="text" id="SPATIAL_GPCI" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA LCTC1" style="top:170px;left:55px;" >
                	<input type="text" id="SPATIAL_LCTC1" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA RD" style="top:50px;left:440px;">
                	<input type="text" id="SPATIAL_RD" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE  DD_2input frmidx" />
                </div>
                <div class="posiA LCTC2" style="top:170px;left:440px;" >
                	<input type="text" id="SPATIAL_LCTC2" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA AC" style="top:290px;left:440px;">
                	<input type="text" id="SPATIAL_AC" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
            </div>
            <!-- 특별관리도로 보수공법 End -->
            <!-- 교차로 보수공법 Start -->
            <div id="DIV_INTERSECT" class="posiR divFrmula" style="display:none;">
                <img src="<c:url value='/images/flow3.png'/>" />
                <div class="posiA GPCI" style="top:50px;left:55px">
                	<input type="text" id="INTERSECT_GPCI" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA LCTC" style="top:170px;left:55px;" >
                	<input type="text" id="INTERSECT_LCTC" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA RD" style="top:50px;left:440px;">
                	<input type="text" id="INTERSECT_RD" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE  DD_2input frmidx" />
                </div>
            </div>
            <!-- 교차로 보수공법 End -->
            <!-- 1차로 보수공법 Start -->
            <div id="DIV_1TRACK" class="posiR divFrmula" style="display:none;">
                <img src="<c:url value='/images/flow4.png'/>" />
                <div class="posiA GPCI" style="top:50px;left:55px">
                	<input type="text" id="1TRACK_GPCI" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE DD_2 input frmidx" />
                </div>
                <div class="posiA RD" style="top:50px;left:440px;">
                	<input type="text" id="1TRACK_RD" value="" style="width:30px" class="MX_80 CS_50 DT_DOUBLE  DD_2input frmidx" />
                </div>
            </div>
            <!-- 1차로 보수공법 End -->
        </div>
        <div class="mr10 fr mt10">
            <a href="#" class="schbtn" onclick="fnRepairTargetFrmulaStart();" style="width:50px">저장</a>
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
	COMMON_UTIL.cmFormObjectInit("frm");
	
	fn_initFrmula("NOMAL");
}); 

/**
 * 수식 초기화
 * skc@muhanit.kr
 * 2017-11-20
 * type : 수식 조회 타입 (NOMAL, SPATIAL, INTERSECT, 1TRACK)
 */
function fn_initFrmula(type){
	 
	 $.ajax({
			url: contextPath + 'api/rpairmthdfrmula/selectRpairMthdFrmulaList.do'
			,type: 'post'
			,dataType: 'json'
			,contentType : 'application/json'
			,data: JSON.stringify({"FRMULA_TYPE" : type})
			,success: function(data){
				//수식 조회 화면 설정
				fn_setFrmulaView(type);
				
				//수삭 값 입력
				fn_setFrmulaIdx(type, "GPCI", data.gpciList);
				fn_setFrmulaIdx(type, "RD", data.rdList);
				
				
				if(type == "NOMAL" || type == "SPATIAL"){
					fn_setFrmulaIdx(type, "LCTC1", data.lctcList1);
					fn_setFrmulaIdx(type, "LCTC2", data.lctcList2);
					fn_setFrmulaIdx(type, "AC", data.acList);
				}else if(type == "INTERSECT"){
					fn_setFrmulaIdx(type, "LCTC", data.lctcList);
				}
				
				//수식 key event 추가
				$(".frmidx").keyup(function(){
					if($(this).val() == ""){
						$(this).val("0");
					}
					var inputs = $(this).parent().find("div").find("input");
					for(var i=0; i<inputs.length; i++){
						if((inputs[i].name == "BEGIN_VAL" || inputs[i].name == "END_VAL")
								&& $(inputs[i]).val() != "" && $(inputs[i]).val() != null){
							$(inputs[i]).val($(this).val());
						}
					}
				});
				
			}
			,error: function(a,b,msg){
				
			}
		});
	fn_setFrmulaView(type);
}

/**
 * 수식 조회 화면 설정
 * skc@muhanit.kr
 * 2017-11-20
 * type : 수식 조회 타입 (NOMAL, SPATIAL, INTERSECT, 1TRACK)
 */
function fn_setFrmulaView(type){
	$(".frmulaBtn").removeClass("probtn2").addClass("probtn1");
	$("#BTN_" + type).addClass("probtn2");
	 
	$(".divFrmula").hide();
	$("#DIV_" + type).show();
}

/**
 * 수식 값 설정
 * skc@muhanit.kr
 * 2017-11-20
 * type : 수식 조회 타입 (NOMAL, SPATIAL, INTERSECT, 1TRACK)
 * dataKnd : 수식종류
 * data : 수식 데이터
 */
function fn_setFrmulaIdx(type, dataKnd, data){
	var parentDiv = $("#DIV_" + type);
	var txtHtml = "";
	var value;
	for(var i=0; i<data.length; i++){
		txtHtml += "<div>"
				 + "	<input type='hidden' name='STEP_STTUS_CODE' value='" + data[i].STEP_STTUS_CODE + "' />"
				 + "	<input type='hidden' name='BEGIN_VAL' value='" + data[i].BEGIN_VAL + "' />"
				 + "	<input type='hidden' name='END_VAL' value='" + data[i].END_VAL + "' />"
				 + "</div>";
	}
	
	if(data[0].BEGIN_VAL != null && data[0].BEGIN_VAL != ""){
		value = data[0].BEGIN_VAL;
	}
	else{
		value = data[0].END_VAL;
	}
	
	parentDiv.find("." + dataKnd).append(txtHtml.replace(/null/g , ""));
	$("#" + type + "_" + dataKnd).val(value);
}

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
function fnRepairTargetFrmulaStart(){
	var msgContents = "변경하신 임계값을 저장하시겠습니까?<br/>저장된 임계값 적용을 위해서는 [범위 내 선정]을 다시 시작하시기 바랍니다.<br />";
	$("#divRTFDialog").dialog({title : "보수대상 선정 - 임계값 설정" , width: "380px" });
	var buttonTags = '<a href="#" class="schbtn" onclick="fn_save();" style="width:70px" >저장</a>&nbsp;&nbsp;';
	buttonTags += '<a href="#" class="graybtn" onclick="fnRepairTargetFrmulaClose();" style="width:70px" >취소</a>';
	$("#divRTFButtn").html(buttonTags);
	$("#txtRTFContents").html(msgContents);
	$("#divRTFDialog").dialog("open");
}

/**
 *  저장 팝업창 닫기
 */
function fnRepairTargetFrmulaClose(){
	$("#divRTFDialog").dialog("close");
}

/**
 * 보수공법 수식 저장
 * skc@muhanit.kr
 * 2017-11-20
 */
function fn_save(){
	$("#divRTFDialog").dialog("close");
	COMMON_UTIL.cmShowProgressBar();
	var frmulaList = [];
	var frmula = "";
	
	var targetNm = $(".probtn2")[0].id.replace("BTN_", "");
	
	var divList = $("#DIV_" + targetNm).find("div");
	
	for(var i=0; i<divList.length; i++){
		frmula = "{";
		var input = $(divList[i]).find("input");
		
		if(input.length < 1){continue;}
		
		for(var j=0; j<input.length; j++){
			var name = input[j].name;
			
			if(typeof name == "undefined" || name == ""){continue;}
			
			var value = $(input[j]).val();
			
			var obj = "'" + name + "' : '" + value.replace(/,/g, "") + "',";
			frmula += obj;
		}
		frmula = frmula.substring(0, frmula.length - 1) + "}";
		frmulaList.push(eval("("+ frmula +")"));
	}
	 
	$.ajax({
		url: contextPath + 'api/rpairmthdfrmula/updateRpairMthdFrmula.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify({"rpairMthdFrmulaList" : frmulaList})
		,success: function(data){
			COMMON_UTIL.cmHideProgressBar();
			alert(data.resultMSG);
		}
		,error: function(a,b,msg){
			COMMON_UTIL.cmHideProgressBar();
			alert("임계값 저장 시 오류가 발생했습니다.");
		}
	});
}

</script>
</body>
</html>