<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>특별구간 관리 </title>

<%@ include file="/include/common_head.jsp" %>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$( document ).ready(function() {
	$(".togglebtn").click(function () {

        var wndId = $("#wnd_id").val();

        if ( $(this).hasClass("on") ) {

            if ( $("#gridArea").html() != "" ) {

                fnPopupResize(250);
                $(this).removeClass("on");
                $(this).attr("title", "열기");

            }

        } else {

            fnPopupResize(630);
            $(this).addClass("on");
            $(this).attr("title", "닫기");

        }

        $("#result").slideToggle("slow");
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
	var total = parseInt($('#PAV_THICK_ASCON').val()) + parseInt($('#PAV_THICK_BASE').val()) + parseInt($('#PAV_THICK_ASSTNBASE').val());
	$('#PAV_TOTAL').text(total);
}
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
      text-align: center;
      vertical-align: middle;
      width: 33%;
      font-size: 12px;
      border: #e1e1e0 solid 1px;
      padding: 6px 6px;
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
      text-align: center;
      vertical-align: middle;
      font-size: 12px;
      border: #e1e1e0 solid 1px;
      padding: 6px 6px;
      line-height: 1.3em;
   }

   .input {
   	    border: 0;
    	width: 100%;
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

<form id="insertfrm" name="insertfrm" method="post" action="<c:url value="/mvroad/insertMvRoad.do"/>">
	<input type="hidden" id="SPCL_NO" name="SPCL_NO" value="${mvroadVO.SPCL_NO}">
	<table class="tableH">
	   <tr>
	      <th colspan="2">사업명</th>
	      <td>
	      		<input type="text" id="BSNS_NM" name="BSNS_NM" value="" class="input">
	      </td>
	      <th>공사구간</th>
	      <td>
	            <input type="text" id="CNTRWK_SCTN" name="CNTRWK_SCTN" value="" class="input">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">노선명</th>
	      <td>
	      		<input type="text" id="ROUTE_CODE" name="ROUTE_CODE" value="" class="input">
	      </td>
	      <th>차로수</th>
	      <td>
	      		<input type="text" id="TRACK_CO" name="TRACK_CO" value="" class="input">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">사업량<br>(km)</th>
	      <td>
	      		<input type="text" id="BSNS_QY" name="BSNS_QY" value="" class="input">
	      </td>
	      <th>총사업비<br>(백만원)</th>
	      <td>
	      		<input type="text" id="TOT_WCT" name="TOT_WCT" value="" class="input">
	      </td>
	   </tr>
	   <tr>
	      <th colspan="2">사업기간<br>(준공년월일)</th>
	      <td>
	      		<div>
		      		<input type="text" id="BSNS_BEGIN_YEAR" name="BSNS_BEGIN_YEAR" value="" class="input" style="width: 40%;"> ~
		      		<input type="text" id="BSNS_END_YEAR" name="BSNS_END_YEAR" value="" class="input" style="width: 40%;">
	      		</div>
	      		<input type="text" id="COMPET_DE" name="COMPET_DE" value="" class="input">
	      </td>
	      <th>사업시행자</th>
	      <td>
	      		<input type="text" id="BSNS_OPERTN_MAN" name="BSNS_OPERTN_MAN" value="" class="input">
	      </td>
	   </tr>
	   <tr>
	      <th rowspan="2">위치</th>
	      <th>시점</th>
	      <td>
	      		<input type="text" id="STRTPT_NM" name="STRTPT_NM" value="" class="input">
	      </td>
	      <th rowspan="2">주요 통과지</th>
	      <td rowspan="2">
	      		<input type="text" id="MAJOR_PASAGEPAPR" name="MAJOR_PASAGEPAPR" value="" class="input">
	      </td>
	   </tr>
	   <tr>
	      <th>종점</th>
	      <td>
	      		<input type="text" id="ENDPT_NM" name="ENDPT_NM" value="" class="input">
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
	      		<input type="text" id="PAV_MTRQLT" name="PAV_MTRQLT" value="" class="input">
	      </td>
	      <td>
				<span id="PAV_TOTAL"></span>
	      </td>
	      <td>
	      		<input type="text" id="PAV_THICK_ASCON" name="PAV_THICK_ASCON" value="" class="input" onchange="fn_plus();">
	      </td>
	      <td>
	      		<input type="text" id="PAV_THICK_BASE" name="PAV_THICK_BASE" value="" class="input" onchange="fn_plus();">
	      </td>
	      <td>
	      		<input type="text" id="PAV_THICK_ASSTNBASE" name="PAV_THICK_ASSTNBASE" value="" class="input" onchange="fn_plus();">
	      </td>
	      <td>
	      		<select id="TUNNEL_KND" name="TUNNEL_KND">
	      			<option>2차로</option>
					<option>3차로</option>
					<option>4차로</option>
					<option>5차로</option>
					<option>이상</option>
	      		</select>
	      </td>
	      <td>
	      		<input type="text" id="TUNNEL_CO" name="TUNNEL_CO" value="" class="input">
	      </td>
	      <td>
	      		<input type="text" id="TUNNEL_LEN" name="TUNNEL_LEN" value="" class="input">
	      </td>
	      <td>
	      		<select id="BRIDGE_KND" name="BRIDGE_KND">
	      			<option>강교</option>
					<option>철근</option>
					<option>콘크리트교</option>
					<option>합성교</option>
					<option value="etc">그 밖의 교량</option>
	      		</select>
	      </td>
	      <td>
	      		<input type="text" id="BRIDGE_CO" name="BRIDGE_CO" value="" class="input">
	      </td>
	      <td>
	      		<input type="text" id="BRIDGE_LEN" name="BRIDGE_LEN" value="" class="input">
	      </td>
	   </tr>
	</table>

<div style="margin-top: 20px;text-align: right;" class="btfilebx">
	<input type="button" class="btn pri" value="등록" onclick="fn_insert();">
</div>

</form>
</div>
        </li>
    </ul>
</div>
</div>

</body>
</html>