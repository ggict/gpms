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
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="SM_NO" name="SM_NO" value="${smDtaGnlSttusVO.SM_NO }"/>
<!-- 필수 파라메터(END) -->
<div class="tabcont">
	<div class="">
	    <h3>포장상태 평가정보 상세조회1</h3>
	    <h5 class="info" style="left: 230px;">
	       <a href='#' class='whitebtn dpib ml10 vm' id="back"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
	    </h5>
	    <div class="ytabbx" style="left: 280px; position:absolute; top:0px;">
		    <ul class="ytab fl">
		    	<c:forEach items="${srvyYearList }" var="srvyYear">
		    		<li <c:if test="${srvyYear.SRVY_YEAR eq smDtaGnlSttusVO.SRVY_YEAR }">class="on"</c:if>>
		    			<a href="#" onclick="COMMON_UTIL.cmMoveUrl('srvy/selectSrvyDtaEvlInfoDetail.do'+param.replace('SRVY_YEAR', 'SCH_SRVY_YEAR')+'&SM_NO=${smDtaGnlSttusVO.SM_NO }&SRVY_YEAR=${srvyYear.SRVY_YEAR }')">${srvyYear.SRVY_YEAR }</a>
		    		</li>
		    	</c:forEach>
	        </ul>
        </div>
	    <p class="location">
	        <span>포장상태 평가</span>
	        <span>포장상태 평가정보 조회</span>
	        <strong>포장상태 평가정보 상세조회</strong>
	    </p>

	    <div class="mt10 ml10">

            <!-- 섹션 기본정보 START -->
            <div id="baseInfo" style="width:24%; height: 210px; float: left; padding-right: 10px;">
                <h3 class="h3">기본정보 </h3>
                <div class="table">
	                <table class="table20">
	                    <caption class="hidden">포장상태 기본정보</caption>
	                    <colgroup>
	                        <col width="21%" />
	                        <col width="29%" />
	                        <col width="21%" />
	                        <col width="29%" />
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th colspan="2" scope="row" style="border-right: solid 0px">조사년월</th>
	                            <td colspan="2">${smDtaGnlSttusVO.SRVY_YEAR }. ${smDtaGnlSttusVO.SRVY_MT }</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">노선번호</th>
	                            <td>${smDtaGnlSttusVO.ROAD_NO_VAL }</td>
	                            <th scope="row">노선명</th>
	                            <td>${smDtaGnlSttusVO.ROAD_NM }</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">행선</th>
	                            <td>${smDtaGnlSttusVO.DIRECT_NM }</td>
	                            <th scope="row">차로</th>
	                            <td>${smDtaGnlSttusVO.TRACK }</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">도로등급</th>
	                            <td>${smDtaGnlSttusVO.ROAD_GRAD }</td>
	                            <th scope="row">이정(m)</th>
	                            <td>${smDtaGnlSttusVO.STRTPT } ~ ${smDtaGnlSttusVO.ENDPT }</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">관리기관</th>
	                            <td>${smDtaGnlSttusVO.DEPT_CODE }</td>
	                            <th scope="row">셀종류</th>
	                            <td>${smDtaGnlSttusVO.CELL_TYPE }</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">교통용량</th>
	                            <td>${smDtaGnlSttusVO.VMTC_GRAD }</td>
	                            <th scope="row">행정구역</th>
	                            <td>${smDtaGnlSttusVO.ADM_CODE }</td>
	                        </tr>
	                        <tr>
	                        	<th scope="row">관리도로</th>
	                        	<td colspan="3">${smDtaGnlSttusVO.MRG_RD_NM }</td>
	                        </tr>
	                        <tr>
	                        	<th scope="row">공사구간</th>
	                        	<td>${smDtaGnlSttusVO.CNTRWK_AT }</td>
	                        	<th scope="row">미개통구간</th>
	                        	<td>${smDtaGnlSttusVO.UNOPN_AT }</td>
	                        </tr>
	                    </tbody>
	
	                </table>
                </div>
            </div>
            <!-- 섹션 기본정보 END -->

            <!-- 포장상태 조사자료 START -->
            <div id="srvyData" style="width:24%; height: 210px; float: left; padding-right: 10px; border-radius: 5px;">
                <h3 class="h3">조사자료</h3>
                <div class="table">
	                <table class="table20">
	                    <caption class="hidden">포장상태 조사자료</caption>
	                    <colgroup>
	                        <col width="25%" />
	                        <col width="25%" />
	                        <col width="25%" />
	                        <col width="25%" />
	                    </colgroup>
	                    <tbody>
	                        <tr style="height: auto;">
	                            <th scope="row">거북등균열<br/>(㎡)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.TRTS_BAC_CR }" type="number" var="TRTS_BAC_CR" />
	                                <fmt:formatNumber value="${TRTS_BAC_CR }" minFractionDigits="2" />
	                            </td>
	                            <th scope="row">선형균열<br/>(m)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.VRTCAL_CR }" type="number" var="VRTCAL_CR" />
	                                <fmt:formatNumber value="${VRTCAL_CR }" minFractionDigits="2" />
	                            </td>
	                        </tr>
	                        <tr style="height: auto;">
	                            <th scope="row">블럭균열<br/>(㎡)</th>
	                            <td colspan="3">
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.BLOCK_CR }" type="number" var="BLOCK_CR" />
	                                <fmt:formatNumber value="${BLOCK_CR }" minFractionDigits="2" />
	                            </td>
	                        </tr>
	                        <tr style="height: auto;">
	                            <th scope="row">패칭(㎡)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.PTCHG_CR }" type="number" var="PTCHG_CR" />
	                                <fmt:formatNumber value="${PTCHG_CR }" minFractionDigits="2" />
	                            </td>
	                            <th scope="row">포트홀(㎡)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.POTHOLE_CR }" type="number" var="POTHOLE_CR" />
	                                <fmt:formatNumber value="${POTHOLE_CR }" minFractionDigits="2" />
	                            </td>
	                        </tr>
	                        <tr style="height: 20%;">
	                            <th scope="row">소성변형<br/>(mm)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.RD_VAL }" type="number" var="RD_VAL" />
	                                <fmt:formatNumber value="${RD_VAL }" minFractionDigits="2" />
	                            </td>
	                            <th scope="row">종단평탄성<br/>(m/km)</th>
	                            <td>
	                                <fmt:parseNumber value="${smDtaGnlSttusVO.IRI_VAL }" type="number" var="IRI_VAL" />
	                                <fmt:formatNumber value="${IRI_VAL }" minFractionDigits="2" />
	                            </td>
	                        </tr>
	                        <tr style="height: auto;">
	                            <th scope="row">교통량</th>
	                            <td colspan="3">-</td>
	                        </tr>
	                    </tbody>
	
	                </table>
	              </div>
            </div>
            <!-- 포장상태 조사자료 END -->

            <!-- 포장상태 평가자료 START -->
            <div id="mummAvg" style="width:25%; float: left; height: 210px; padding-right: 10px;">
                <h3>포장상태 평가정보 <span>(평가단위:section셀)</span></h3>
		        <!-- <div class="ytabbx"> -->
		            <ul class="ytab">
	                    <li class="on"><a href="javascript:;" onclick="fnSelectLastSttus($(this), 1);" >조사평가정보조회</a></li>
	                    <li><a href="javascript:;" onclick="fnSelectLastSttus($(this), 2);">수시평가정보조회</a></li>
		            </ul>
		        <!-- </div> -->
                <!-- <a href="#" style="float:right; line-height: 11px; margin-top: 5px;" class="titbtn" onclick="">수시평가정보조회</a> -->
                <ul class="tblst mt15" style="clear: both">
                    <li style="width:21%;border-left:0px" class="brl tc">
                        <span class="circle bc6" id="gpci" style="width: 85%; height: 40px; line-height: 40px; font-size: 16px;">0</span>
                        <span>GPCI</span>
                    </li>
                    <li style="width:37%" class="brl tc">
                        <span class="circle bc5" id="crVal" style="width: 85%; line-height: 40px; font-size: 16px;">없음</span>
                        <span>주 파손</span>
                    </li>
                    <li style="width:40%" class="tc">
                        <span class="circle bc7" id="dmgCuz" style="width: 85%; height: 40px; line-height: 40px; font-size: 15px;">없음</span>
                        <span>파손원인</span>
                    </li>
                </ul>

                <table class="tbview" style="width: 100%; height: 121px; margin-top: 7px;">
                    <caption class="hidden">포장상태 평가정보</caption>
                    <colgroup>
                        <col width="30%" />
                        <col width="20%" />
                        <col width="30%" />
                        <col width="20%" />
                    </colgroup>
                    <tbody style="text-align: center;">
                        <tr>
                            <th scope="row">거북등균열</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.AC_RDUCT_RATE }" type="number" var="AC_RDUCT_RATE" />
                                <fmt:formatNumber value="${AC_RDUCT_RATE }" minFractionDigits="2" /></td>
                            <th scope="row">선형균열</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.LC_RDUCT_RATE }" type="number" var="LC_RDUCT_RATE" />
                                <fmt:formatNumber value="${LC_RDUCT_RATE }" minFractionDigits="2" /></td>
                        </tr>
                        <tr>
                            <th scope="row">블럭균열</th>
                            <td colspan="3"><fmt:parseNumber value="${smDtaGnlSttusVO.BC_RDUCT_RATE }" type="number" var="BC_RDUCT_RATE" />
                                <fmt:formatNumber value="${BC_RDUCT_RATE }" minFractionDigits="2" /></td>
                        </tr>
                        <tr>
                            <th scope="row">패칭</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.PTCHG_RDUCT_RATE }" type="number" var="PTCHG_RDUCT_RATE" />
                                <fmt:formatNumber value="${PTCHG_RDUCT_RATE }" minFractionDigits="2" /></td>
                            <th scope="row">포트홀</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.POT_RDUCT_RATE }" type="number" var="POT_RDUCT_RATE" />
                                <fmt:formatNumber value="${POT_RDUCT_RATE }" minFractionDigits="2" /></td>
                        </tr>
                        <tr>
                            <th scope="row">소성변형</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.RD_RDUCT_RATE }" type="number" var="RD_RDUCT_RATE" />
                                <fmt:formatNumber value="${RD_RDUCT_RATE }" minFractionDigits="2" /></td>
                            <th scope="row">종단평탄성</th>
                            <td><fmt:parseNumber value="${smDtaGnlSttusVO.RCI_RDUCT_RATE }" type="number" var="RCI_RDUCT_RATE" />
                                <fmt:formatNumber value="${RCI_RDUCT_RATE }" minFractionDigits="2" /></td>
                        </tr>
                    </tbody>
                </table>

                <span style="font-size: 11px; margin-top: 5px; float: right;">* 포장파손형태 별 포장상태지수 감소값 (10점 만점)</span>
            </div>
            <!-- 포장상태 평가자료 END -->

            <!-- 포장공사 이력 START -->
            <div id="mummCntrwkList" style="width:24%; float: left; height: 210px; ">
	            <h3 style="line-height: 30px; font-size: 15px;">포장공사 이력</h3>
	            <div id="div_grid">
					<table id="gridArea"></table>
					<div id="gridPager" style="width: 100%;"></div>
				</div>
			</div>
			<!-- 포장공사 이력 END -->
        </div>
    </div>
</div>
</form>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer" charset="utf-8">
// 페이지 로딩 초기 설정

var cellId = "${smDtaGnlSttusVO.CELL_ID}";
var param = "";
param += "?DEPT_CODE="      + "${mummSctnSrvyDtaVO.DEPT_CODE}"
       + "&ROAD_GRAD="      + "${mummSctnSrvyDtaVO.ROAD_GRAD}"
       + "&ROAD_NO="        + "${mummSctnSrvyDtaVO.ROAD_NO}"
       + "&ROAD_NAME="      + "${mummSctnSrvyDtaVO.ROAD_NAME}"
       + "&DIRECT_CODE="    + "${mummSctnSrvyDtaVO.DIRECT_CODE}"
       + "&TRACK="          + "${mummSctnSrvyDtaVO.TRACK}"
       + "&STRTPT="         + "${mummSctnSrvyDtaVO.STRTPT}"
       + "&ENDPT="          + "${mummSctnSrvyDtaVO.ENDPT}"
       + "&CNTL_DFECT="     + "${mummSctnSrvyDtaVO.CNTL_DFECT}"
       + "&MINGPCI="        + "${mummSctnSrvyDtaVO.MINGPCI}"
       + "&MAXGPCI="        + "${mummSctnSrvyDtaVO.MAXGPCI}"
       + "&SRVY_YEAR="      + "${mummSctnSrvyDtaVO.SRVY_YEAR}";
var preCrVal = "";
var preDmgCuz = "";

$( document ).ready(function() {

    $("#back").click(function() {

        var url = 'srvy/selectSrvyDtaEvlInfoList.do' + param;
        COMMON_UTIL.cmMoveUrl(url);

    });

    fnSelectLoc(cellId);
    fnSelectData();
    fnCntrwkDtl();

});

// 포장상태 기본정보, 조사자료
function fnSelectData() {

    var postDatas = { "CELL_ID": cellId };

/*     $.ajax({
        url: "<c:url value='/srvy/api/srvyDtaEvlResearchInfo.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {

            if ( jdata != undefined && jdata != null && jdata != "" ) { */

                var tb1 = $("#baseInfo").find("table td");
                var tb2 = $("#srvyData").find("table td");
                var li = $("#mummAvg").find("li");
                var tb = $("#mummAvg").find("table td");

                /* // 기본정보
                tb1.eq(0).html(jdata.SRVY_YEAR + ". " + jdata.SRVY_MT);
                tb1.eq(1).html(jdata.ROAD_NO_VAL);
                tb1.eq(2).html(jdata.ROAD_NM);
                tb1.eq(3).html(jdata.DIRECT_NM);
                tb1.eq(4).html(jdata.TRACK);
                tb1.eq(5).html(jdata.ROAD_GRAD);
                tb1.eq(6).html(jdata.STRTPT + " ~ " + jdata.ENDPT);
                tb1.eq(7).html(jdata.DEPT_CODE);
                tb1.eq(8).html(jdata.CELL_TYPE);
                tb1.eq(9).html(jdata.VMTC_GRAD);
                tb1.eq(10).html(jdata.ADM_CODE);
                */


                // 평가정보 값
                var CNTL_DFECT = "${smDtaGnlSttusVO.CNTL_DFECT}";
                var CODE_NM = "${smDtaGnlSttusVO.CNTL_DFECT_NM}";
                var AC_IDX = "${smDtaGnlSttusVO.AC_IDX}";
                var LC_IDX = "${smDtaGnlSttusVO.LC_IDX}";
                var BC_IDX = "${smDtaGnlSttusVO.BC_IDX}";
                var PTCHG_IDX = "${smDtaGnlSttusVO.PTCHG_IDX}";
                var POTHOLE_IDX = "${smDtaGnlSttusVO.POTHOLE_IDX}";
                var RD_IDX = "${smDtaGnlSttusVO.RD_IDX}";
                var RCI = "${smDtaGnlSttusVO.RCI}";
                var DMG_CUZ_CLMT = "${smDtaGnlSttusVO.DMG_CUZ_CLMT}";
                var DMG_CUZ_VMTC = "${smDtaGnlSttusVO.DMG_CUZ_VMTC}";
                var DMG_CUZ_ETC = "${smDtaGnlSttusVO.DMG_CUZ_ETC}";

                // 주 파손
                var crVal = "";

                if ( CNTL_DFECT != "DFCT0009" ) {

                    var codeNm = CODE_NM;

                    if ( codeNm == "AC" ) {
                        crVal = "거북등균열";

                    } else if ( codeNm == "BC" ) {
                        crVal = "블럭균열";

                    } else if ( codeNm == "LC" ) {
                        crVal = "선형균열";

                    } else if ( codeNm == "PTCHG" ) {
                        crVal = "패칭";

                    } else if ( codeNm == "POTHOLE" ) {
                        crVal = "포트홀";

                    } else if ( codeNm == "RD" ) {
                        crVal = "소성변형";

                    } else if ( codeNm == "RCI" || codeNm == "IRI" ) {
                        crVal = "종단평탄성";

                    }

                } else {

                    var maxVal = Math.max( AC_IDX, BC_IDX, LC_IDX, PTCHG_IDX, POTHOLE_IDX, RD_IDX, RCI );
                    var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
                    var valArr = [ AC_IDX, BC_IDX, LC_IDX, PTCHG_IDX, POTHOLE_IDX, RD_IDX, RCI ];

                    if ( maxVal == 0 ) {
                        // max 값이 0인 경우는 파손없음
                        crVal += "파손없음";

                    } else {
                        crVal += "<br />복합파손 <br /> (";
                        var codeNames = [];
                        for ( var i = 0; i < valArr.length; i++ ) {
                            // max값과 같은 경우 텍스트 추가
                            //if ( valArr[i] == minVal ) {
                            if ( valArr[i] == maxVal ) {
                                /* 
                            	if ( i != 0 ) {
                                    crVal += ", ";
                                }
                                crVal += nameArr[i];
                                 */
                                codeNames.push(nameArr[i]);
                            }
                        }
                            
                        crVal += codeNames.join(',');
                        crVal += ")";
                        $("#crVal").css({"line-height": "11px", "font-size": "14px"});
                    }
                }

                // 파손원인
                var clmt = DMG_CUZ_CLMT;
                var vmtc = DMG_CUZ_VMTC;
                var etc = DMG_CUZ_ETC;
                var cuz = "";

                if ( clmt == vmtc && vmtc == etc ) {
                    if ( etc == 0 ) {
                        cuz = "파손없음";

                    } else if ( etc != 0 ) {
                        cuz = "<br />교통량/하부불량,<br />기후, 기타";
                        $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});
                    }

                } else if ( clmt > vmtc && clmt > etc ) {
                    cuz = "기후";

                } else if ( vmtc > clmt && vmtc > etc ) {
                    cuz = "교통량/하부불량";
                    $("#dmgCuz").css({"font-size": "14px"});

                } else if ( etc > clmt && etc > vmtc ) {
                    cuz = "기타";

                } else if ( clmt == vmtc && clmt > etc ) {
                    cuz = "<br />교통량/하부불량,<br />기후";
                    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

                } else if ( clmt == etc && clmt > vmtc ) {
                    cuz = "기후, 기타";

                } else if ( vmtc == etc && vmtc > clmt ) {
                    cuz = "<br />교통량/하부불량,<br />기타";
                    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

                } else {
                    cuz = "";

                }

                // 평가정보
                $("#gpci").text(fnFloat("${smDtaGnlSttusVO.GPCI}"));
                $("#crVal").html(crVal);
                $("#dmgCuz").text(cuz);

                /* tb.eq(0).html(fnFloat(AC_IDX));
                tb.eq(1).html(fnFloat(LC_IDX));
                tb.eq(2).html(fnFloat(BC_IDX));
                tb.eq(3).html(fnFloat(PTCHG_IDX));
                tb.eq(4).html(fnFloat(POTHOLE_IDX));
                tb.eq(5).html(fnFloat(RD_IDX));
                tb.eq(6).html(fnFloat(RCI)); */
/*
                tb.eq(0).html(fnFloat(AC_RDUCT_RATE));
                tb.eq(1).html(fnFloat(LC_RDUCT_RATE));
                tb.eq(2).html(fnFloat(BC_RDUCT_RATE));
                tb.eq(3).html(fnFloat(PTCHG_RDUCT_RATE));
                tb.eq(4).html(fnFloat(POT_RDUCT_RATE));
                tb.eq(5).html(fnFloat(RD_RDUCT_RATE));
                tb.eq(6).html(fnFloat(RCI_RDUCT_RATE)); */

            /* } */

       /*  },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;

        }
    }); */

    //fnSelectLoc(cellId);

}

// 포장공사이력 조회
function fnCntrwkDtl() {

    var postDatas = { "CELL_ID": cellId };
    //var postDatas = { "OBJECT_ID": objId };

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({

        url: '<c:url value="/"/>'+'api/cntrwk/selectCntrwkListBySctn.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: JSON.stringify(postDatas)
        ,ignoreCase: true
        ,colNames:["공사ID","세부공사ID","셀번호","공사명","세부공사명","착공일","준공일","위치보기"]
        ,colModel:[
            {name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
            ,{name:'DETAIL_CNTRWK_ID',index:'DETAIL_CNTRWK_ID', hidden: true}
            ,{name:'CELL_ID',index:'CELL_ID', align:'center', width:100}
            ,{name:'FULL_CNTRWK_NM',index:'FULL_CNTRWK_NM', align:'left', width:100}
            ,{name:'DETAIL_CNTRWK_NM',index:'DETAIL_CNTRWK_NM', align:'left', width:100, hidden: true}
            ,{name:'STRWRK_DE',index:'STRWRK_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'COMPET_DE',index:'COMPET_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:30, sortable:false , formatter: fnFormatter}
        ]
        ,async : false
        ,sortname: 'STRTPT'
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,ondblClickRow: function( rowId ) {

            fnView(rowId);  // 대장 조회

        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
            if( rowId != null ) {
                var rowData =$( "#gridArea" ).getRowData(rowId);
            }
        }
        ,beforeSelectRow: function(rowid, e) {
            if(e.type == "click"){
                var $grid = $("#gridArea");
                $grid.jqGrid('setSelection', rowid, false);
                return false;
            }
            return true;
        }
        ,loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                this.p.postData.sord = this.p.sortorder;
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        , loadComplete: function() {

            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager_left").css('width', '');
            $("#gridPager_center").css('width', '100%');
        }
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 182);

    fn_search(cellId);

}

//검색 처리
function fn_search(cellId) {

    var postDatas = { "CELL_ID": cellId };

    $("#gridArea").jqGrid("setGridParam", {

        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postDatas
        ,mtype: "POST"
        ,loadComplete: function(data) {

            //총 건수를 목록 상단에 표시
            $('#count').text('검색 결과 :'+$("#gridArea").getGridParam("records")+'건');
            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager_left").css('width', '');

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

            $("#gridPager").css("width", "100%");

        }

    }).trigger("reloadGrid");
}

//상세 조회
function fnView ( rowId ) {

    if( $.type( rowId ) === "undefined" || rowId== "" ) {
        rowId = $("#gridArea").getGridParam( "selrow" );

    }

    if ( rowId != null ) {

        var rowData = $("#gridArea").getRowData(rowId);
        var deCntrwkId = rowData["DETAIL_CNTRWK_ID"];
        var cellId = rowData["CELL_ID"];

        COMMON_UTIL.cmWindowOpen('상세정보 조회', "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>?DETAIL_CNTRWK_ID="+deCntrwkId+"&CELL_ID="+cellId, 1000, 1200, false, $("#wnd_id").val(), 'center');

    } else{
        alert('<spring:message code="warn.checkplz.msg" />');
    }

}

// 현행정보조회
function fnSelectLastSttus(obj, idx) {

    if (obj.closest('li').hasClass('on')) {
    	return;
    } else {
        obj.closest('ul').find('li').removeClass('on');
        obj.closest('li').addClass("on");
    }
	
	if ( idx === 2 ) {

	    var postDatas = { "CELL_ID" : cellId };

	    $.ajax({
	        url: "<c:url value='/api/smdtalaststtus/selectSmDtaLastSttusBySrvy.do' />",
	        contentType: 'application/json',
	        data: JSON.stringify(postDatas),
	        dataType: "json",
	        cache: false,
	        processData: false,
	        type: 'POST',
	        async: false,
	        success: function (jdata) {

	            if ( jdata != undefined && jdata != null && jdata != "" ) {

	                // 주 파손
	                var crVal = "";

	                if ( jdata.CNTL_DFECT != "DFCT0009" ) {
	                    var codeNm = jdata.CODE_NM;

	                    if ( codeNm == "AC" ) {
	                        crVal = "거북등균열";

	                    } else if ( codeNm == "BC" ) {
	                        crVal = "블럭균열";

	                    } else if ( codeNm == "LC" ) {
	                        crVal = "선형균열";

	                    } else if ( codeNm == "PTCHG" ) {
	                        crVal = "패칭";

	                    } else if ( codeNm == "POTHOLE" ) {
	                        crVal = "포트홀";

	                    } else if ( codeNm == "RD" ) {
	                        crVal = "소성변형";

	                    } else if ( codeNm == "RCI" || codeNm == "IRI" ) {
	                        crVal = "종단평탄성";

	                    }

	                } else {

	                    var maxVal = Math.max( jdata.AC_IDX, jdata.BC_IDX, jdata.LC_IDX, jdata.PTCHG_IDX, jdata.POTHOLE_IDX, jdata.RD_IDX, jdata.RCI );
	                    var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
	                    var valArr = [ jdata.AC_IDX, jdata.BC_IDX, jdata.LC_IDX, jdata.PTCHG_IDX, jdata.POTHOLE_IDX, jdata.RD_IDX, jdata.RCI ];

	                    if ( maxVal == 0 ) {
	                        // max 값이 0인 경우는 파손없음
	                        crVal += "파손없음";

	                    } else {
	                        crVal += "<br />복합파손 <br /> (";
	                        var codeNames = [];

	                        for ( var i = 0; i < valArr.length; i++ ) {

	                            // max값과 같은 경우 텍스트 추가
	                            /* 	
	                            //if ( valArr[i] == minVal ) {
	                            if ( valArr[i] == maxVal ) {
	                                if ( i != 0 ) {
	                                    crVal += ", ";

	                                }
	                                crVal += nameArr[i];
	                            }
 								*/
 								codeNames.push(nameArr[i]);
	                        }
	                            
	                        crVal += codeNames.join(',');
	                        crVal += ")";
	                        $("#crVal").css({"line-height": "11px", "font-size": "14px"});
	                    }
	                }

	                // 파손원인
	                var clmt = jdata.DMG_CUZ_CLMT;
	                var vmtc = jdata.DMG_CUZ_VMTC;
	                var etc = jdata.DMG_CUZ_ETC;
	                var cuz = "";

	                if ( clmt == vmtc && vmtc == etc ) {
	                    if ( etc == 0 ) {
	                        cuz = "파손없음";

	                    } else if ( etc != 0 ) {
	                        cuz = "<br />교통량/하부불량,<br />기후, 기타";
	                        $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	                    }

	                } else if ( clmt > vmtc && clmt > etc ) {
	                    cuz = "기후";

	                } else if ( vmtc > clmt && vmtc > etc ) {
	                    cuz = "교통량/하부불량";
	                    $("#dmgCuz").css({"font-size": "14px"});

	                } else if ( etc > clmt && etc > vmtc ) {
	                    cuz = "기타";

	                } else if ( clmt == vmtc && clmt > etc ) {
	                    cuz = "<br />교통량/하부불량,<br />기후";
	                    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	                } else if ( clmt == etc && clmt > vmtc ) {
	                    cuz = "기후, 기타";

	                } else if ( vmtc == etc && vmtc > clmt ) {
	                    cuz = "<br />교통량/하부불량,<br />기타";
	                    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	                } else {
	                    cuz = "";

	                }

	                // 이전정보 저장
	                preCrVal = $("#crVal").text();
	                preDmgCuz = $("#dmgCuz").text();

	                // 평가정보
	                $("#gpci").html(fnFloat(jdata.GPCI));
	                $("#crVal").html(crVal);
	                $("#dmgCuz").html(cuz);

	                var tb = $("#mummAvg").find("table td");
	                tb.eq(0).html(fnFloat(jdata.AC_RDUCT_RATE));
	                tb.eq(1).html(fnFloat(jdata.LC_RDUCT_RATE));
	                tb.eq(2).html(fnFloat(jdata.BC_RDUCT_RATE));
	                tb.eq(3).html(fnFloat(jdata.PTCHG_RDUCT_RATE));
	                tb.eq(4).html(fnFloat(jdata.POT_RDUCT_RATE));
	                tb.eq(5).html(fnFloat(jdata.RD_RDUCT_RATE));
	                tb.eq(6).html(fnFloat(jdata.RCI_RDUCT_RATE));

	                //obj.addClass("on");
	                //obj.text("조사평가정보조회");
	            }
	        },
	        error: function () {

	            alert("데이터를 조회할 수 없습니다.");
	            return;
	        }
	    });

    } else {

        $("#gpci").html(fnFloat("${smDtaGnlSttusVO.GPCI}"));
        $("#crVal").html(preCrVal);
        $("#dmgCuz").html(preDmgCuz);

        var tb = $("#mummAvg").find("table td");
        tb.eq(0).html(fnFloat("${smDtaGnlSttusVO.AC_RDUCT_RATE}"));
        tb.eq(1).html(fnFloat("${smDtaGnlSttusVO.LC_RDUCT_RATE}"));
        tb.eq(2).html(fnFloat("${smDtaGnlSttusVO.BC_RDUCT_RATE}"));
        tb.eq(3).html(fnFloat("${smDtaGnlSttusVO.PTCHG_RDUCT_RATE}"));
        tb.eq(4).html(fnFloat("${smDtaGnlSttusVO.POT_RDUCT_RATE}"));
        tb.eq(5).html(fnFloat("${smDtaGnlSttusVO.RD_RDUCT_RATE}"));
        tb.eq(6).html(fnFloat("${smDtaGnlSttusVO.RCI_RDUCT_RATE}"));

        //obj.removeClass("on");
        //obj.text("수시평가정보조회");
    }
}


// Formatter Fuction
function fnConvertKm(cellValue, options, rowObject) {

    var nm = options.colModel.name;

    if ( nm == "STRTPT" ) {

        var val = rowObject.STRTPT;
        val *= 1;
        val = (val / 1000).toFixed(2);

        return val;

    } else if ( nm == "ENDPT" ) {

        var val = rowObject.ENDPT;
        val *= 1;
        val = (val / 1000).toFixed(2);

        return val;

    } else {
        return "";
    }
}

function fnFormatter(cellValue, options, rowObject) {

    var html = "";
    var nm = options.colModel.name;

    if ( nm == "btn_loc" ) {
        html = "<a href='#' onclick=\"fnSelectLoc('" + rowObject.OBJECT_ID + ", cntrwk');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
    return html;
}

// 위치보기 2가지 flag처리
function fnSelectLoc(cellId, flag) {

    var fColor = '#ff0000' ;
    var sColor = '#ff0000' ;

    // 선택한 셀을 보여줌
    var tables = ["CELL_SECT"];
    var fields = ["CELL_ID"];
    var values = [cellId];

    if (flag == 'cntrwk'){
    	fColor = '#ffffff' ;
        sColor = '#ffffff' ;
    }

    var attribute = {
            attributes : {
                fillColor : fColor,
                strokeColor : sColor
            }
    };
    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);
}



function fnFloat(val) {
    return parseFloat(val).toFixed(2);

}

</script>
</body>
</html>