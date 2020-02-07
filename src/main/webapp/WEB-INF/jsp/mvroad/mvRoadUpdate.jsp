<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>특별구간 관리 </title>

<%@ include file="/include/common_head.jsp" %>

<script>

var total = ${result.PAV_THICK_ASCON} + ${result.PAV_THICK_BASE} + ${result.PAV_THICK_ASSTNBASE};

$( document ).ready(function() {
	cmCreateDatepicker('BSNS_BEGIN_YEAR', 10, "/images/btn_calendar.gif", "yy");
    cmCreateDatepicker('BSNS_END_YEAR', 10, "/images/btn_calendar.gif", "yy");
    cmCreateDatepicker('COMPET_DE', 15, "/images/btn_calendar.gif", "yy-mm-dd");

	$('#PAV_TOTAL').text(total);

	$(".selbtn").click(function () {
        //
        // Button Toggle
        $(this).parent().siblings('li').removeClass('on');

        // $(this) id, class, num of class
        var id = $(this).attr("id");
        var classArr = $(this).parent().attr("class");

        // 2단계 버튼 점형 선택
        if ( id == "BTN_SECT_POINT") {

        	//gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
            //gMap.getLayerByName('GOverlapLayer').removeAllFeatures();

            option = {};
            option = {
                        iframe : window,
                        callback : "fnCheckFeatures",
                        clearMap : false
            };
            MAP.CONTROL.set_option(option);

            parent.$("#MV_ROAD_CELT0012").parent().addClass("on");

            parent.gMap.activeControls("selMyRoadPopSelect");

        }

    });
});

function fn_insert(){
	if($('#BSNS_NM').val() == '' ) {
        alert("사업명을 입력해주세요.");
        $('#BSNS_NM').focus();
        return;
    }if($('#CNTRWK_SCTN').val() == '' ) {
        alert("공사구간을 입력해주세요.");
        $('#CNTRWK_SCTN').focus();
        return;
    }if($('#ROUTE_CODE').val() == '' ) {
        alert("노선명을 입력해주세요.");
        $('#ROUTE_CODE').focus();
        return;
    }if($('#TRACK_CO').val() == '' ) {
        alert("차로수를 입력해주세요.");
        $('#TRACK_CO').focus();
        return;
    }if($('#BSNS_QY').val() == '' ) {
        alert("사업량을 입력해주세요.");
        $('#BSNS_QY').focus();
        return;
    }if($('#TOT_WCT').val() == '' ) {
        alert("총사업비를 입력해주세요.");
        $('#TOT_WCT').focus();
        return;
    }if($('#BSNS_BEGIN_YEAR').val() == '' ) {
        alert("사업기간 시작 년도를 입력해주세요.");
        $('#BSNS_BEGIN_YEAR').focus();
        return;
    }if($('#BSNS_END_YEAR').val() == '' ) {
        alert("사업기간 끝 년도를 입력해주세요.");
        $('#BSNS_END_YEAR').focus();
        return;
    }if($('#COMPET_DE').val() == '' ) {
        alert("준공년월일을 입력해주세요.");
        $('#COMPET_DE').focus();
        return;
    }if($('#STRTPT_NM').val() == '' ) {
        alert("위치 시점을 입력해주세요.");
        $('#STRTPT_NM').focus();
        return;
    }if($('#MAJOR_PASAGEPAPR').val() == '' ) {
        alert("주요 통과지를 입력해주세요.");
        $('#MAJOR_PASAGEPAPR').focus();
        return;
    }if($('#ENDPT_NM').val() == '' ) {
        alert("시점 종점을 입력해주세요.");
        $('#ENDPT_NM').focus();
        return;
    }if($('#PAV_MTRQLT').val() == '' ) {
        alert("포장재질을 입력해주세요.");
        $('#PAV_MTRQLT').focus();
        return;
    }if($('#PAV_THICK_ASCON').val() == '' ) {
        alert("포장두계표층을 입력해주세요.");
        $('#PAV_THICK_ASCON').focus();
        return;
    }if($('#PAV_THICK_BASE').val() == '' ) {
        alert("포장두계기층을 입력해주세요.");
        $('#PAV_THICK_BASE').focus();
        return;
    }if($('#PAV_THICK_ASSTNBASE').val() == '' ) {
        alert("포장두께보조기층을 입력해주세요.");
        $('#PAV_THICK_ASSTNBASE').focus();
        return;
    }if($('#TUNNEL_KND').val() == '' ) {
        alert("터널종류를 선택해주세요.");
        $('#TUNNEL_KND').focus();
        return;
    }if($('#TUNNEL_CO').val() == '' ) {
        alert("터널 수를 입력해주세요.");
        $('#TUNNEL_CO').focus();
        return;
    }if($('#TUNNEL_LEN').val() == '' ) {
        alert("터널 연장을 입력해주세요.");
        $('#TUNNEL_LEN').focus();
        return;
    }if($('#BRIDGE_KND').val() == '' ) {
        alert("교량 종류를 선택해주세요.");
        $('#BRIDGE_KND').focus();
        return;
    }if($('#BRIDGE_CO').val() == '' ) {
        alert("교량 수를 입력해주세요.");
        $('#BRIDGE_CO').focus();
        return;
    }if($('#BRIDGE_LEN').val() == '' ) {
        alert("교량 연장을 입력해주세요.");
        $('#BRIDGE_LEN').focus();
        return;
    }

	$("#insertfrm").submit();
}

function fn_delete(spcl_no){
	var result = confirm('삭제하시겠습니까?');
	var spcl_no = spcl_no;

	if(result){
		location.replace('<c:url value="/mvroad/deleteMvRoad.do?spcl_no='+ spcl_no +'"/>');
	}
}

function fn_plus(){
	var total = (parseFloat($('#PAV_THICK_ASCON').val())||0) + (parseFloat($('#PAV_THICK_BASE').val())||0) + (parseFloat($('#PAV_THICK_ASSTNBASE').val())||0);
	$('#PAV_TOTAL').text(total);
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadName = $("#ROUTE_CODE option:selected").data("roadname");
    $("#ROAD_NAME").val(roadName);
}

var cmCreateDatepicker = function(_oId, _oSize, imgPath, dateFormat){
    var vbtnImg = contextPath+ "/images/ico_date.png";
    if(imgPath!=null && imgPath!=undefined  && imgPath!=""){
        vbtnImg = contextPath+ imgPath;
    }

    // 년도만 출력할 경우
    if ( dateFormat == "yy" ) {
        $("table.ui-datepicker-calendar").hide();
    } else {
        $("table.ui-datepicker-calendar").show();
    }

    $( "#"+_oId ).width(_oSize*8).datepicker({
         changeMonth: true
        ,changeYear: true
        ,numberOfMonths: 1
        ,showOn: "button"
        ,buttonImage: vbtnImg
        ,buttonImageOnly: true
        ,dateFormat:dateFormat
    });
};
</script>

</head>

<body>

<style>
   .tableH {
      width: 100%;
      border-collapse: collapse;
      border-top: 1px solid #222222;
   }

   .tableH th {
      border: 1px solid #ccc;
      padding: 5px;
      vertical-align: middle;
      background: #fafafa;
      color: #777;
      font-size: 13px;
      text-align: center;
   }

   .tableH td {
      color: #333;
      text-align: left;
      vertical-align: middle;
      width: 33%;
      font-size: 12px;
      border: #e1e1e0 solid 1px;
      padding: 6px 6px;
      line-height: 1.3em;
   }

   .tableH td div {
      color: #333;
      font-size: 12px;
      line-height: 1.3em;
   }

   .tableh {
      width: 100%;
      border-collapse: collapse;
   }

   .tableh th {
      border: 1px solid #ccc;
      padding: 5px;
      vertical-align: middle;
      background: #fafafa;
      color: #777;
      font-size: 13px;
      text-align: center;
   }

   .tableh td {
      color: #333;
      text-align: left;
      vertical-align: middle;
      font-size: 12px;
      border: #e1e1e0 solid 1px;
      padding: 6px 6px;
      line-height: 1.3em;
   }

   .tableh td span {
      color: #333;
      font-size: 12px;
      line-height: 1.3em;
   }

	.pri {
	    background: #6aa2ec;
	    cursor: pointer;
	}

	.btn {
	    color: #fff;
	    border: none;
	    border-radius: 2px;
	    padding: 5px 15px;
	    display: inline-block;
	}
</style>

<div class="content">
        <div style="width:99%">
            <ul class="stepbx af">
                <li style="width:33%">
                    <div class="stbx">
                    	<h4><span class="step">단계1</span>특별관리 구간 선택</h4>
                        <div style="height:100px">
                            <ul class="tblst selbx">
                                <li style="width:100%" class="tc">
                                    <a class="selbtn btncursor" id="BTN_SECT_POINT">
                                        <span class="roundbx"><img src="../images/ic_shape1.png" alt="점" /></span>
                                        <span class="dpb">선택 </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
            <ul class="stepbx mt10">
                <li class="wid100">
                    <div class="stbx" id="step3">
                        <h4><span class="step">단계2</span>조회결과</h4>
<form id="insertfrm" name="insertfrm" method="post" action="<c:url value="/mvroad/updateMvRoad.do"/>">
	<input type="hidden" id="SPCL_NO" name="SPCL_NO" value="${result.SPCL_NO}">
	<table class="tableH">
	   <tr>
	      <th colspan="2">사업명</th>
	      <td>
	      		<input type="text" id="BSNS_NM" name="BSNS_NM" value="${result.BSNS_NM}" class="input" maxlength="200">
	      </td>
	      <th>공사구간</th>
	      <td>
	            <input type="text" id="CNTRWK_SCTN" name="CNTRWK_SCTN" value="${result.CNTRWK_SCTN}" class="input" maxlength="1000">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">노선명</th>
	      <td>
	      		<select id="ROUTE_CODE" name="ROUTE_CODE" alt="노선번호" onchange="fn_change_roadNm();" class="input" style="width:100px;">
                    <option value="">선택</option>
                    <c:forEach items="${roadNoList}" var="roadNo">
                        <option value="${roadNo.ROAD_NO }" data-roadname="<c:out value="${roadNo.ROAD_NAME}"/>" <c:if test="${ roadNo.ROAD_NO eq result.ROUTE_CODE }">selected="selected"</c:if>>${roadNo.ROAD_NO_VAL }</option>
                        <c:if test="${ roadNo.ROAD_NO eq result.ROUTE_CODE }">
                            <c:set var="ROAD_NAME" value="${roadNo.ROAD_NAME }" />
                        </c:if>
                    </c:forEach>
                 </select>
                 <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="${ROAD_NAME }" style="width: 50%">
	      </td>
	      <th>차로수</th>
	      <td>
	      		<input type="number" id="TRACK_CO" name="TRACK_CO" value="${result.TRACK_CO}" class="input" maxlength="2">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">사업량<br>(km)</th>
	      <td>
	      		<input type="number" id="BSNS_QY" name="BSNS_QY" value="${result.BSNS_QY}" class="input" step="0.01">
	      </td>
	      <th>총사업비<br>(백만원)</th>
	      <td>
	      		<input type="number" id="TOT_WCT" name="TOT_WCT" value="${result.TOT_WCT}" class="input" maxlength="10">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">사업기간<br>(준공년월일)</th>
	      <td>
	      		<div>
		      		<input type="text" id="BSNS_BEGIN_YEAR" name="BSNS_BEGIN_YEAR" value="${result.BSNS_BEGIN_YEAR}" class="input" maxlength="4"> ~
		      		<input type="text" id="BSNS_END_YEAR" name="BSNS_END_YEAR" value="${result.BSNS_END_YEAR}" class="input" maxlength="4">
	      		</div>
	      		(<input type="text" id="COMPET_DE" name="COMPET_DE" value="${result.COMPET_DE}" class="input" maxlength="10">)
	      </td>
	      <th>사업시행자</th>
	      <td>
	      		<input type="text" id="BSNS_OPERTN_MAN" name="BSNS_OPERTN_MAN" value="${result.BSNS_OPERTN_MAN}" class="input" maxlength="100">
	      </td>
	   </tr>
	   <tr>
	      <th rowspan="2">위치</th>
	      <th>시점</th>
	      <td>
	      		<input type="text" id="STRTPT_NM" name="STRTPT_NM" value="${result.STRTPT_NM}" class="input" maxlength="100">
	      </td>
	      <th rowspan="2">주요 통과지</th>
	      <td rowspan="2">
	      		<input type="text" id="MAJOR_PASAGEPAPR" name="MAJOR_PASAGEPAPR" value="${result.MAJOR_PASAGEPAPR}" class="input" maxlength="100">
	      </td>
	   </tr>
	   <tr>
	      <th>종점</th>
	      <td>
	      		<input type="text" id="ENDPT_NM" name="ENDPT_NM" value="${result.ENDPT_NM}" class="input" maxlength="100">
	      </td>
	   </tr>
	</table>
	<table class="tableh">
	   <tr>
	      <th colspan="11" style="border-top:0;">포장도로</th>
	   </tr>
	   <tr>
	      <th rowspan="2">포장<br>재질</th>
	      <th colspan="4">포장두께(mm)</th>
	      <th colspan="3">터널</th>
	      <th colspan="3">교량</th>
	   </tr>
	   <tr>
	      <th>계</th>
	      <th>표층</th>
	      <th>기층</th>
	      <th>보조기층</th>
	      <th>종류</th>
	      <th>개소</th>
	      <th>연장<br>(m)</th>
	      <th>종류</th>
	      <th>개소</th>
	      <th>연장<br>(m)</th>
	   </tr>
	   <tr>
	      <td>
	      		<input type="text" id="PAV_MTRQLT" name="PAV_MTRQLT" value="${result.PAV_MTRQLT}" class="input" maxlength="100">
	      </td>
	      <td>
				<span id="PAV_TOTAL"></span>
	      </td>
	      <td>
	      		<input type="number" id="PAV_THICK_ASCON" name="PAV_THICK_ASCON" value="${result.PAV_THICK_ASCON}" class="input" onchange="fn_plus();" maxlength="6" step="0.01">
	      </td>
	      <td>
	      		<input type="number" id="PAV_THICK_BASE" name="PAV_THICK_BASE" value="${result.PAV_THICK_BASE}" class="input" onchange="fn_plus();" maxlength="6" step="0.01">
	      </td>
	      <td>
	      		<input type="number" id="PAV_THICK_ASSTNBASE" name="PAV_THICK_ASSTNBASE" value="${result.PAV_THICK_ASSTNBASE}" class="input" onchange="fn_plus();" maxlength="6" step="0.01">
	      </td>
	      <td>
                <input type="text" id="TUNNEL_KND" name="TUNNEL_KND" value="${result.TUNNEL_KND}" class="input" maxlength="100">
	      </td>
	      <td>
	      		<input type="number" id="TUNNEL_CO" name="TUNNEL_CO" value="${result.TUNNEL_CO}" class="input" maxlength="2">
	      </td>
	      <td>
	      		<input type="number" id="TUNNEL_LEN" name="TUNNEL_LEN" value="${result.TUNNEL_LEN}" class="input" maxlength="8" step="0.01">
	      </td>
	      <td>
	      		<input type="text" id="BRIDGE_KND" name="BRIDGE_KND" value="${result.BRIDGE_KND}" class="input" maxlength="100">
	      </td>
	      <td>
	      		<input type="number" id="BRIDGE_CO" name="BRIDGE_CO" value="${result.BRIDGE_CO}" class="input" maxlength="2">
	      </td>
	      <td>
	      		<input type="number" id="BRIDGE_LEN" name="BRIDGE_LEN" value="${result.BRIDGE_LEN}" class="input" maxlength="8" step="0.01">
	      </td>
	   </tr>
	</table>

<div style="margin-top: 20px;margin-bottom: 20px;text-align: right;" class="btfilebx">
	<input type="submit" class="btn pri" value="수정">
</div>

</form>
</div>
        </li>
    </ul>
</div>
</div>

</body>
</html>