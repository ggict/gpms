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

function fn_delete(spcl_no){
	var result = confirm('삭제하시겠습니까?');
	var spcl_no = spcl_no;

	if(result){
		location.replace('<c:url value="/mvroad/deleteMvRoad.do?spcl_no='+ spcl_no +'"/>');
	}
}

function fn_update(spcl_no){
	location.replace('<c:url value="/mvroad/updatePgMvRoad.do?spcl_no='+ spcl_no +'"/>');
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
      text-align: center;
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

	<input type="hidden" id="SPCL_NO" name="SPCL_NO" value="${result.SPCL_NO}">

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
                        <table class="tableH">
						   <tr>
						      <th colspan="2">사업명</th>
						      <td>
						      		${result.BSNS_NM}
						      </td>
						      <th>공사구간</th>
						      <td>
						            ${result.CNTRWK_SCTN}
						      </td>
						   </tr>
						   <tr>
						      <th colspan="2">노선명</th>
						      <td>
									${result.ROUTE_NM}
						      </td>
						      <th>차로수</th>
						      <td>
						      		${result.TRACK_CO}
						      </td>
						   </tr>
						   <tr>
						      <th colspan="2">사업량<br>(km)</th>
						      <td>
						      		${result.BSNS_QY}
						      </td>
						      <th>총사업비<br>(백만원)</th>
						      <td>
						      		${result.TOT_WCT}
						      </td>
						   </tr>
						   <tr>
						      <th colspan="2">사업기간<br>(준공년월일)</th>
						      <td>
						      		<div>
							      		${result.BSNS_BEGIN_YEAR} ~ ${result.BSNS_END_YEAR}
						      		</div>
						      		(${result.COMPET_DE})
						      </td>
						      <th>사업시행자</th>
						      <td>
						      		${result.BSNS_OPERTN_MAN}
						      </td>
						   </tr>
						   <tr>
						      <th rowspan="2">위치</th>
						      <th>시점</th>
						      <td>
						      		${result.STRTPT_NM}
						      </td>
						      <th rowspan="2">주요 통과지</th>
						      <td rowspan="2">
						      		${result.MAJOR_PASAGEPAPR}
						      </td>
						   </tr>
						   <tr>
						      <th>종점</th>
						      <td>
						      		${result.ENDPT_NM}
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
									${result.PAV_MTRQLT}
						      </td>
						      <td>
									<span id="PAV_TOTAL"></span>
						      </td>
						      <td>
						      		${result.PAV_THICK_ASCON}
						      </td>
						      <td>
						      		${result.PAV_THICK_BASE}
						      </td>
						      <td>
						      		${result.PAV_THICK_ASSTNBASE}
						      </td>
						      <td>
						      		${result.TUNNEL_KND}
						      </td>
						      <td>
						      		${result.TUNNEL_CO}
						      </td>
						      <td>
						      		${result.TUNNEL_LEN}
						      </td>
						      <td>
						      		<c:if test="${result.BRIDGE_KND eq 'etc'}">그 밖의 교량</c:if>
						      		<c:if test="${result.BRIDGE_KND ne 'etc'}">${result.BRIDGE_KND}</c:if>
						      </td>
						      <td>
						      		${result.BRIDGE_CO}
						      </td>
						      <td>
						      		${result.BRIDGE_LEN}
						      </td>
						   </tr>
						</table>
                    </div>
                </li>
            </ul>
        </div>

	<div style="margin-top: 20px;margin-bottom: 20px;text-align: right;" class="btfilebx">
		<input type="button" class="btn pri" value="삭제" onclick="fn_delete(${result.SPCL_NO});" style="margin-left:10px;">
		<input type="button" class="btn pri" value="수정" onclick="fn_update(${result.SPCL_NO});">
	</div>

</div>


</body>
</html>