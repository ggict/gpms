<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사통계 </title>
<%@ include file="/include/common_head.jsp" %>

<style type="text/css"  >


.notempty-price {background-color:#E6E6E6;}
.statsbx .ui-jqgrid .ui-jqgrid-hbox{
	padding-right:0px;
}

.rtGridDept .ui-jqgrid-htable, .rtGridDept .ui-jqgrid-ftable,  .rtGridDept .ui-jqgrid-btable{
width:100% !important;
}
.rtGridDept .ui-jqgrid-htable tr.jqgrow td, .rtGridDept .ui-jqgrid-ftable tr.jqgrow td,  .rtGridDept .ui-jqgrid-btable tr.jqgrow td{
height: 5.7em !important;
}

.rtGridDept .ui-jqgrid .ui-jqgrid-hbox{
padding-right:0px;
}
.rtGridDept .ui-jqgrid tr.jqgrow td {
    font-weight: normal;
    white-space: pre;
    height: 22px;
    border-bottom-width: 1px;
    border-bottom-color: inherit;
    border-bottom-style: solid;
    overflow: hidden;

    padding-top: 0px;
    padding-right: 2px;
    padding-bottom: 0px;
    padding-left: 2px;
}

.rtGridDept .ui-jqgrid tr.footrow-ltr td:first-child{
     width:160px !important;
     padding-top: 0px;
    padding-right: 2px;
    padding-bottom: 0px;
    padding-left: 20px;
}
.rtGridDept .ui-jqgrid tr.footrow-ltr td:nth-child(2){
     width:161px !important;
     padding-top: 0px;
    padding-right: 20px;
    padding-bottom: 0px;
    padding-left: 2px;
}
.rtGridDept .ui-jqgrid tr td:first-child{
 	width:160px !important;
    padding-left: 20px;
}
.rtGridDept .ui-jqgrid tr td:nth-child(2){
	width:161px !important;
    padding-right: 20px;
}



.rtGridMethod .ui-jqgrid-htable, .rtGridMethod .ui-jqgrid-ftable,  .rtGridMethod .ui-jqgrid-btable{
width:100% !important;
}
/*
.rtGridDept .ui-jqgrid-htable tr.jqgrow td, .rtGridDept .ui-jqgrid-ftable tr.jqgrow td,  .rtGridDept .ui-jqgrid-btable tr.jqgrow td{
height: 5.7em !important;
}
*/
.rtGridMethod .ui-jqgrid .ui-jqgrid-hbox{
	padding-right:0px;
}
.rtGridMethod .ui-jqgrid tr.jqgrow td {
    font-weight: normal;
    white-space: pre;
    height: 22px;
    border-bottom-width: 1px;
    border-bottom-color: inherit;
    border-bottom-style: solid;
    overflow: hidden;

    padding-top: 0px;
    padding-right: 2px;
    padding-bottom: 0px;
    padding-left: 2px;
}
.rtGridMethod .ui-jqgrid tr.footrow-ltr td:first-child{
     width:160px !important;
     padding-top: 0px;
    padding-right: 2px;
    padding-bottom: 0px;
    padding-left: 20px;
}
.rtGridMethod .ui-jqgrid tr.footrow-ltr td:nth-child(2){
     width:161px !important;
     padding-top: 0px;
    padding-right: 20px;
    padding-bottom: 0px;
    padding-left: 2px;
}
.rtGridMethod .ui-jqgrid tr td:first-child{
 	width:160px !important;
    padding-left: 20px;
}
.rtGridMethod .ui-jqgrid tr td:nth-child(2){
	width:161px !important;
    padding-right: 20px;
}


.rtGridAdmin .ui-jqgrid-htable, .rtGridAdmin .ui-jqgrid-ftable,  .rtGridAdmin .ui-jqgrid-btable{
width:100% !important;
}

.rtGridAdmin .ui-jqgrid .ui-jqgrid-hbox{
	padding-right:0px;
}
.rtGridAdmin .ui-jqgrid tr.jqgrow td {
    font-weight: normal;
    white-space: pre;
    height: 22px;
    border-bottom-width: 1px;
    border-bottom-color: inherit;
    border-bottom-style: solid;
    overflow: hidden;

    padding-top: 0px;
    padding-right: 2px;
    padding-bottom: 0px;
    padding-left: 2px;
}
.rtGridAdmin .ui-jqgrid tr.footrow-ltr td:first-child{
     width:470px !important;
     padding-top: 0px;
    padding-right: 1px;
    padding-bottom: 0px;
    padding-left: 20px;
}
.rtGridAdmin .ui-jqgrid tr.footrow-ltr td:nth-child(2){
     width:486px !important;
     padding-top: 0px;
    padding-right: 20px;
    padding-bottom: 0px;
    padding-left: 2px;
}
.rtGridAdmin .ui-jqgrid tr td:first-child{
 	width:471px !important;
    padding-left: 20px;
}
.rtGridAdmin .ui-jqgrid tr td:nth-child(2){
	width:470px !important;
    padding-right: 20px;
}
</style>

</head>
<body >
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SCH_STRWRK_DE" name="SCH_STRWRK_DE" value=""/>
<input type="hidden" id="SCH_COMPET_DE" name="SCH_COMPET_DE" value=""/>
<div id="sch_cnt01" class="tabcont">
	<h3 class="tc">(<fmt:formatDate pattern = "yyyy-MM-dd" value = "${rpairTrgetSlctnVO.SLCTN_DT}" />) <c:if test = "${rpairTrgetSlctnVO.SLCTN_STTUS ne 'RTSS0010'}">(작업중)</c:if> 보수대상 선정 과정을 진행합니다.</h3>
	<p class="location">
	    <span>보수대상선정</span>
	    <strong>보수대상선정</strong>
	</p>
	<div>
	    <div class="m20">
	    	<form id="frmRpairTrgetGroup" name="frmRpairTrgetGroup" method="post" action="">
	    	<input type="hidden" name="useFilter" id="useFilter" value="false" />
	    	<input type="hidden" name="TRGET_SLCTN_NO" id="TRGET_SLCTN_NO" value="${rpairTrgetSlctnVO.TRGET_SLCTN_NO}" />
	    	<input type="hidden" name="TMPR_SLCTN_AT" id="TMPR_SLCTN_AT" />
	        <div class="fbx">
	            <ul class="sch">
	                <li><label style="width:40px">필터 : </label>
	                <label>도로등급</label>
	                <select id="ROAD_GRAD" name="ROAD_GRAD" title="도로등급"  onchange="fn_change_roadNo();" style="width:120px;" class="input">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${roadGradList }" var="roadGrad">
		        			<option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
		        		</c:forEach>
	                </select>
	                </li>
	                <li class="ml20">
	                    <label>노선번호</label>
	                    <select id="ROUTE_CODE" name="ROUTE_CODE" title="노선번호"  onchange="fn_change_roadNm();" style="width:110px;"   class="input">
                        <option value="">== 전체 ==</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                    	<input type="hidden" style="width:110px" id=ROAD_NO_VAL name="ROAD_NO_VAL">
	                    <input type="text" style="width:110px" readonly="readonly" id="ROAD_NAME" name="ROAD_NAME" >
	                </li>
	                <li class="ml20"><label>행정구역</label>
	                <select id="ADM_CODE" name="ADM_CODE" title="행정구역" style="width:110px;" class="input">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${admList }" var="adm">
		        			<option value="${adm.CODE_VAL }">${adm.CODE_NM }</option>
		        		</c:forEach>
	                </select></li>
	                <li class="ml20"><label>관리도로</label>
	                <select id="MNG_RD_CD" name="MNG_RD_CD" title="관리도로" style="width:110px;" class="input">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${mngRdList }" var="mngRd">
		        			<option value="${mngRd.CODE_VAL}">${mngRd.CODE_NM}</option>
		        		</c:forEach>
	                </select></li>
	                <li class="ml10 fr">
	                    <a href="#" onclick="javascript:fnFilterApply();" class="schbtn" id="btnFilterSearch" >필터적용</a>
	                </li>
	            </ul>
	        </div>
	        </form>
	        <div style="width: 100%;" id="divGridHeight"  >
				<div id="div_grid" style="width:100%; height:80%;">
					<table id="gridArea"></table>
					<div id="gridPager"></div>
				</div>
			</div>
			<div class="mt10 tc" style="display:block;padding-top:2px;">
                <div class="fl">
                    <a href="#" class="schbtn" onclick="javascript:fnInitRangeSelection(true);">상태 초기화</a>
                    <a href="#" class="schbtn" onclick="javascript:fn_select_cellSectFilter();" >선정구간 지도위치보기</a>
                    <a href="#" class="schbtn" onclick="javascript:fn_showChart_RepairTarget();">차트 조회</a>
                    <c:if test = "${rpairTrgetSlctnVO.SLCTN_STTUS ne 'RTSS0010'}"><a href="#" class="graybtn"  disabled >엑셀 저장</a></c:if>
                    <c:if test = "${rpairTrgetSlctnVO.SLCTN_STTUS eq 'RTSS0010'}"><a href="#" class="schbtn"  onclick="javascript:fndownload_RepairTarget();"  >엑셀 저장</a></c:if>
                </div>
                <div class="fr">
                    <a href="#" class="schbtn" onclick="javascript:fnSaveComplete();" >보수대상 선정(저장)</a>
                </div>
            </div>
    	</div>
	</div>
</div>
<div id="repairtargetStats">
<div class="fr"><a href="#" class="schbtn" onclick="javascript:fnToggleChart();" id="btnToggleChart">표로 보기</a>&nbsp;<a href="#" class="schbtn" onclick="javascript:fn_closeChart_RepairTarget();" >닫기</a></div><br/>
		<ul><li></li></ul>
		<ul class="statsbx" style="width:100%;">
			<li  style="width:50%;">
				<h4>예산 집행기관별 유지보수 집행 현황</h4>
				<div class="graylinebx p10">
					<div id="rtChartDeptCode" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					<div id="rtGridDeptCode" class="cont_ConBx2 rtGridDept" style=" margin:30px auto ;width:60%;display:none;">
					<table id="rtGridDeptCodeArea" class="" style="width:60%;" ></table>
					</div>
				</div>
			</li>
			<li  style="width:50%;">
				<h4>보수공법별 유지보수비 집행 현황</h4>
				<div class="graylinebx p10">
					<div id="rtChartMethodCode" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
					<div id="rtGridMethodCode" class="cont_ConBx2 rtGridMethod" style=" margin:30px auto;width:60%;display:none;">
					<table id="rtGridMethodCodeArea"  class="" style="width:60%;" ></table>
					</div>
				</div>
			</li>
			<li style="width:100%;">
				<h4>단위행정구역별 유지보수비 집행 현황</h4>
				<div class="graylinebx p10">
					<div id="rtChartAdminCode" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
					<div id="rtGridAdminCode" class="cont_ConBx2 rtGridAdmin"  style=" margin:30px auto;width:80%;display:none;">
					<table id="rtGridAdminCodeArea"  class="" style="width:80%;" ></table>
					</div>
				</div>
			</li>
		</ul>
	</div>

    <!--// 검색영역-->
    <!--왼쪽메뉴영역-->
    <%-- <%@ include file="/include/stats/common_cntrwk.jsp" %> --%>
    <!--// 왼쪽메뉴영역-->
	<!-- 그래프 -->

	<!-- 표 -->



<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript" defer="defer">
require.config({
	   paths: {
	        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
	    }
	});
//에러 메시지 변수
var errNo=0;
//경고 메시지 변수
var ntcNo=0;
/**
 * 차트 여부 플래그
 */
var isChart = true;
var myChartDept = null;
var myChartMethod = null;
var myChartAdmin = null;

//페이지 로딩 초기 설정
$( document ).ready(function() {
	var minChartWidth = 1350;
	minChartWidth = Math.max(minChartWidth, $(window).width() - 150);

	/* 차트 조회 팝업 */
	$("#repairtargetStats").dialog({
		autoOpen : false,
		height : $(window).height() - 150, // 850,
		width : minChartWidth, // 1024,
		modal : true, title : "[챠트 조회] 예산에 근거한 유지보수 구간 선정 과정을 진행합니다."

	});

	//검색 목록 그리드 구성
	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {"TRGET_SLCTN_NO":vForm.find("#TRGET_SLCTN_NO").val(),
			"ROAD_GRAD":vForm.find("#ROAD_GRAD").val(),
			"ROUTE_CODE":vForm.find("#ROUTE_CODE").val(),
			"ROAD_NO_VAL":vForm.find("#ROAD_NO_VAL").val(),
			"ADM_CODE":vForm.find("#ADM_CODE").val(),
			"MNG_RD_CD":vForm.find("#MNG_RD_CD").val()};

	var colModels = new Array();
	var colIndex = 0;
	colModels[colIndex++]={name: "btn_check",  index: "btn_check", comments: "선택",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++]={name: "DEPT_NM",  index: "DEPT_NM", comments: "관리<br/>기관",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.부서코드 */
	colModels[colIndex++]={name: "ROAD_GRAD",  index: "ROAD_GRAD", comments: "도로등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.도로등급 */
	colModels[colIndex++]={name: "ROAD_GRAD_NM",  index: "ROAD_GRAD_NM", comments: "도로<br/>등급",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.도로등급 */
	colModels[colIndex++]={name: "ROUTE_CODE",  index: "ROUTE_CODE", comments: "노선<br/>번호",  width: 50,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.노선_코드 */
	colModels[colIndex++]={name: "ROAD_NO_VAL",  index: "ROAD_NO_VAL", comments: "노선<br/>번호",  width: 50,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.노선_코드 */

	colModels[colIndex++]={name: "DIRECT_CODE",  index: "DIRECT_CODE", comments: "행선",  width: 40,align: "center", hidden: false, sortable :false ,  formatter: fn_grid_item}; /*보수_대상_항목_그룹.행선_코드 */
	colModels[colIndex++]={name: "TRACK",  index: "TRACK", comments: "차로",  width: 40,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.차로 */
	colModels[colIndex++]={name: "MNG_RD_NM",  index: "MNG_RD_NM", comments: "관리도로",  width: 80,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.관리도로 */
	colModels[colIndex++]={name: "CELL_TYPE",  index: "CELL_TYPE", comments: "셀_타입",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.셀_타입 */
	colModels[colIndex++]={name: "CELL_TYPE_NM",  index: "CELL_TYPE_NM", comments: "포장셀<br/>구분",  width: 90,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.셀_타입 */
	colModels[colIndex++]={name: "STRTPT",  index: "STRTPT", comments: "시점<br/>(m)",  width: 60,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.시점 */
	colModels[colIndex++]={name: "ENDPT",  index: "ENDPT", comments: "종점<br/>(m)",  width: 60,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.종점 */
	colModels[colIndex++]={name: "DEPT_CODE",  index: "DEPT_CODE", comments: "부서코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.부서코드 */
	colModels[colIndex++]={name: "VMTC_GRAD",  index: "VMTC_GRAD", comments: "교통량등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.교통량등급 */
	colModels[colIndex++]={name: "VMTC_GRAD_NM",  index: "VMTC_GRAD_NM", comments: "교통<br/>용량",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.교통량등급 */
	colModels[colIndex++]={name: "ADM_CODE",  index: "ADM_CODE", comments: "행정구역코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++]={name: "ADM_NM",  index: "ADM_NM", comments: "행정<br/>구역",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++]={name: "CNTRWK_YEAR",  index: "CNTRWK_YEAR", comments: "최근공사<br/>년도",  width: 80,align: "right", hidden: false, sortable :false }; /*보수_대상_항목_그룹.공사_년도 */
	colModels[colIndex++]={name: "KILLO_LEN",  index: "KILLO_LEN", comments: "연장<br/>(km)",  width: 80,align: "right", hidden: false, sortable :false, formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}; /*보수_대상_항목_그룹.연장 */
	colModels[colIndex++]={name: "AR",  index: "AR", comments: "면적<br/>(m<sup>2</sup>)",  width: 80,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' } }; /*보수_대상_항목_그룹.면적 */
	colModels[colIndex++]={name: "RPAIR_MTHD_CODE",  index: "RPAIR_MTHD_CODE", comments: "보수_공법_코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++]={name: "MSRC_CL_NM",  index: "MSRC_CL_NM", comments: "보수<br/>공법",  width: 150,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++]={name: "RPAIR_FEE",  index: "RPAIR_FEE", comments: "단가<br/>(원/m<sup>2</sup>)",  width: 80,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.단가(원/m2) */
	colModels[colIndex++]={name: "FIX_BUDGET_ASIGN",  index: "FIX_BUDGET_ASIGN", comments: "금액산정 <br/>(원)",  width: 80,align: "right", hidden: true, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.예산_배정 */
	colModels[colIndex++]={name: "FIX_AMOUNT_CALC",  index: "FIX_AMOUNT_CALC", comments: "금액산정 <br/>(원)",  width: 100,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.예산_배정 */
	colModels[colIndex++]={name: "CALC_GPCI",  index: "CALC_GPCI", comments: "GPCI",  width: 50,align: "right", hidden: false, sortable :false }; /*보수_대상_항목_그룹.산정_GPCI */
	colModels[colIndex++]={name: "DMG_VAL",  index: "DMG_VAL", comments: "파손도",  width: 50,align: "right", hidden: false, sortable :false }; /*보수_대상_항목_그룹.파손도_값 */
//	colModels[colIndex++]={name: "btn_loc",  index: "btn_loc", comments: "위치<br/>보기",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++]={name: "btn_loc2",  index: "btn_loc2", comments: "위치<br/>보기",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.행정구역코드 */

	colModels[colIndex++]={name: "GROUP_ITEM_NO",  index: "GROUP_ITEM_NO", comments: "그룹_항목_번호",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.그룹_항목_번호 */
	colModels[colIndex++]={name: "TRGET_SLCTN_NO",  index: "TRGET_SLCTN_NO", comments: "대상_선정_번호",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.대상_선정_번호 */
	colModels[colIndex++]={name: "SLCTN_STEP",  index: "SLCTN_STEP", comments: "선정_단계",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.선정_단계 */
	colModels[colIndex++]={name: "ITEM_SLCTN_STTUS",  index: "ITEM_SLCTN_STTUS", comments: "항목_선정_상태",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.항목_선정_상태 */
	colModels[colIndex++]={name: "SRVY_MT",  index: "SRVY_MT", comments: "조사_월",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.조사_월 */
	colModels[colIndex++]={name: "SRVY_YEAR",  index: "SRVY_YEAR", comments: "조사_년도",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.조사_년도 */
	colModels[colIndex++]={name: "GPCI",  index: "GPCI", comments: "GPCI",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.GPCI */
	colModels[colIndex++]={name: "PC_GRAD",  index: "PC_GRAD", comments: "포장상태등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.포장상태등급 */
	colModels[colIndex++]={name: "CALC_YEAR",  index: "CALC_YEAR", comments: "산정_년도",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.산정_년도 */
	colModels[colIndex++]={name: "CALC_MT",  index: "CALC_MT", comments: "산정_월",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.산정_월 */
	colModels[colIndex++]={name: "CALC_PC_GRAD",  index: "CALC_PC_GRAD", comments: "산정_포장상태등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.산정_포장상태등급 */
	colModels[colIndex++]={name: "TRNSPORT_QY",  index: "TRNSPORT_QY", comments: "교통_량",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.교통_량 */
	colModels[colIndex++]={name: "RPAIR_MTHD_CODE",  index: "RPAIR_MTHD_CODE", comments: "보수_공법_코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++]={name: "MSR_DM_CODE",  index: "MSR_DM_CODE", comments: "공법선정비율_결정방식_코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.공법선정비율_결정방식_코드 */
	colModels[colIndex++]={name: "THRHLD",  index: "THRHLD", comments: "임계값",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.임계값 */
	colModels[colIndex++]={name: "FIXING_AT",  index: "FIXING_AT", comments: "고정_여부",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.고정_여부 */
	colModels[colIndex++]={name: "BUDGET_CECK",  index: "BUDGET_CECK", comments: "예산_체크",  width: 80,align: "center", hidden: true, sortable :false , summaryType:'sum', summaryTpl: 'Totals :'}; /*보수_대상_항목_그룹.예산_체크 */
	colModels[colIndex++]={name: "ACCMLT_CALC",  index: "ACCMLT_CALC", comments: "누적_산정",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.누적_산정 */
	colModels[colIndex++]={name: "SLCTN_AT",  index: "SLCTN_AT", comments: "선정_여부",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.선정_여부 */
	colModels[colIndex++]={name: "SLCTN_DT",  index: "SLCTN_DT", comments: "선정_일시",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.선정_일시 */
	colModels[colIndex++]={name: "TMPR_SLCTN_AT",  index: "TMPR_SLCTN_AT", comments: "임시_선정_여부",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.임시_선정_여부 */
	colModels[colIndex++]={name: "NODE_CO",  index: "NODE_CO", comments: "노드_개수",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.노드_개수 */
	colModels[colIndex++]={name: "SLCTN_ORDR",  index: "SLCTN_ORDR", comments: "선정_순서",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.선정_순서 */
	colModels[colIndex++]={name: "DELETE_AT",  index: "DELETE_AT", comments: "삭제_여부",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.삭제_여부 */
	colModels[colIndex++]={name: "USE_AT",  index: "USE_AT", comments: "사용_여부",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.사용_여부 */
	colModels[colIndex++]={name: "CRTR_NO",  index: "CRTR_NO", comments: "생성자_번호",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.생성자_번호 */
	colModels[colIndex++]={name: "CREAT_DT",  index: "CREAT_DT", comments: "생성_일시",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.생성_일시 */
	colModels[colIndex++]={name: "UPDUSR_NO",  index: "UPDUSR_NO", comments: "수정자_번호",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.수정자_번호 */
	colModels[colIndex++]={name: "UPDT_DT",  index: "UPDT_DT", comments: "수정_일시",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.수정_일시 */
	colModels[colIndex++]={name: "CELL_IDS",  index: "CELL_IDS", comments: "셀목록",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.셀목록 */


	var colNameList = new Array();
	for (var i = 0; i < colModels.length; i++) {
		colNameList[i] = colModels[i].comments;
	}

	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/rpairtrgetgroup/selectRpairTrgetGroupListPage.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: vPostData
		,ignoreCase: true
		,colNames:colNameList
	   	,colModel:colModels
		,async : false
		,sortname: ''
	    ,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
	    ,footerrow: true
        ,userDataOnFooter : true
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		, afterInsertRow: function(id, data){
			if (data.TMPR_SLCTN_AT == 'Y') {
				$('tr#' + id).addClass('notempty-price');
			}
				/*
	            if(parseInt(data.FIX_BUDGET_ASIGN) > 0) {
	                $('tr#' + id).addClass('notempty-price');
	            }else

	            else if(data.SLCTN_AT == 'Y') {
	                $('tr#' + id).addClass('notempty-price');
	            }

				*/
	    }
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnView(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
	   		if (rowId != null) {
				var rowData = $("#gridArea").getRowData(rowId);
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if (this.p.mtype === "POST" && $.type(this.p.postData) !== "string") {

				var vForm = $("#frmRpairTrgetGroup");

				var vUseFilter = vForm.find("#useFilter").val();
				if (vUseFilter != "true") {
					delete this.p.postData.TMPR_SLCTN_AT;
				}

				delete this.p.postData.nd;
				delete this.p.postData._search;
				this.p.postData.sidx = this.p.sortname;
				this.p.postData.sord = this.p.sortorder;
	   			//{"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}
	   			//this.p.postData = JSON.stringify(this.p.postData);
	   			//var pData=$("#frm").cmSerializeObject();
	   			if (this.p.postData.pageUnit != this.p.postData.rows) {
					this.p.postData.pageUnit = this.p.postData.rows;
				}
				ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		,scroll: true
		//,height : '100%'
	}).navGrid('#gridPager', {
		edit : false, add : false, del : false, search : false, refresh : false
	});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)

	COMMON_UTIL.cmInitGridSize('gridArea', 'div_grid', $("#div_grid").height());

	fnSearch();
});

//도면 다운로드, 위치이동 버튼 생성
function fn_grid_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;

	switch (nm) {
	case "btn_down":
		// 클릭시 파일 다운로드
		btn = "<a href='#' onclick=\"fn_select_dwg('" + rowObject.ROAD_NO + "')\"><img src='" + contextPath + "/images/ic_download.png' alt='다운로드' title='다운로드' ></a>";
		break;
	case "btn_loc": {
		btn = "<a href='#' onclick=\"javascript:fn_select_cellSectRange('" + rowObject.ROUTE_CODE + "', '" + rowObject.DIRECT_CODE + "', '" + rowObject.TRACK + "', '" + rowObject.STRTPT + "', '" + rowObject.ENDPT + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
	}
		break;
	case "btn_check": {
		if (rowObject.TMPR_SLCTN_AT == "Y") {// || rowObject.SLCTN_AT=="Y"
			btn = "<input type='checkbox' checked onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" />";
		} else {
			btn = "<input type='checkbox' onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" />";
		}
	}
		break;
	case "btn_loc2": {
		btn = "<a href='#' onclick=\"javascript:fn_select_cellSectID('" + rowObject.CELL_IDS + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
	}
		break;
	}

	return btn;
}
//도면 다운로드, 위치이동 버튼 생성
function fn_grid_item(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;

	switch (nm) {
	case "DIRECT_CODE": {
		if (cellValue == "S")
			return "상행";
		else if (cellValue == "E")
			return "하행";
		else
			return "";
	}

		break;
	}

	return btn;
}


function fn_checkItem(objThis, group_item_no, tmpr_slctn_at, fix_amount_calc){
	var footData = $('#gridArea').jqGrid('footerData', 'get');
	var checked = objThis.checked;
	var action = '<c:url value="/api/rpairtrgetgroup/updateToggleTMPR_SLCTN_AT.do"/>';

	var trget_slctn_no = $("#TRGET_SLCTN_NO").val();
	var postData = {
		"GROUP_ITEM_NO" : group_item_no, "TRGET_SLCTN_NO" : trget_slctn_no
	};
	var totalAmount = parseInt(footData.FIX_AMOUNT_CALC.replace(/,/gi, ""));
	if (checked) {
		totalAmount += parseInt(fix_amount_calc);
		$(objThis).closest('tr').addClass('notempty-price');
	} else {
		totalAmount -= parseInt(fix_amount_calc);
		$(objThis).closest('tr').removeClass('notempty-price');
	}
	$('#gridArea').jqGrid('footerData', 'set', {
		DEPT_NM : '합계', FIX_AMOUNT_CALC : totalAmount
	});
	//
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
			if (data.resultSuccess == "true") {
				var slctn_budget_1 = parseInt(data.SLCTN_BUDGET_1);
				$("#SLCTN_BUDGET_1").text(COMMON_UTIL.cmAddComma(slctn_budget_1));
				var slctn_budget_2 = parseInt(data.SLCTN_BUDGET_2);
				$("#SLCTN_BUDGET_2").text(COMMON_UTIL.cmAddComma(slctn_budget_2));
				var slctn_budget_3 = parseInt(data.SLCTN_BUDGET_3);
				$("#SLCTN_BUDGET_3").text(COMMON_UTIL.cmAddComma(slctn_budget_3));
			}
//			alert(JSON.stringify(data));
//			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
//			parent.fnRefreshRepairTarget();
//			parent.closeRTDialog();
//			parent.COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});

}

function fnInitRangeSelection(isSearch){
	var action = '<c:url value="/api/rpairtrgetgroup/updateInitTMPR_SLCTN_AT.do"/>';
	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {
		"TRGET_SLCTN_NO" : vForm.find("#TRGET_SLCTN_NO").val()
	};
	if (vPostData.TRGET_SLCTN_NO == null || vPostData.TRGET_SLCTN_NO == "")
		return;
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(vPostData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
//			alert(JSON.stringify(data));
//			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
//			parent.fnRefreshRepairTarget();
//			parent.closeRTDialog();
//			parent.COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
			alert("초기화 하였습니다.");
			if(isSearch){
				fnSearch();
			}
		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});
}

function showRTProgress(txt){
	parent.$("#dvProgress").dialog("open");
	parent.$("#t_progress").text(txt);
}

function hideRTProgress(){
	parent.$("#dvProgress").dialog("close");
}
//검색 처리
function fnSearch() {
	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {"TRGET_SLCTN_NO":vForm.find("#TRGET_SLCTN_NO").val(),
			"ROAD_GRAD":vForm.find("#ROAD_GRAD").val(),
			"ROUTE_CODE":vForm.find("#ROUTE_CODE").val(),
			"ROAD_NO_VAL":vForm.find("#ROAD_NO_VAL").val(),
			"ADM_CODE":vForm.find("#ADM_CODE").val(),
			"MNG_RD_CD":vForm.find("#MNG_RD_CD").val()};
	var vUseFilter = vForm.find("#useFilter").val();
	if (vUseFilter == "true") {
//		vPostData["TMPR_SLCTN_AT"] = "Y";
	}


	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#frm").cmSerializeObject())
		,postData:   vPostData
		,ignoreCase: true
		,async : false
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   		//this.jqGrid('footerData','set', {ID: 'Total:', amount: 1000000000});
	   		//var moneySum = $("#gridArea").jqGrid('getCol','TOTAL_BUDGET_ASIGN,TOTAL_AMOUNT_CALC,TOTAL_TMPR_FIX_BUDGET_ASIGN', false, 'cellvalue');
	   		var total_budget_asign = 0;
			if (data.records != null && data.records > 0) {
				total_budget_asign = data.rows[0].TOTAL_TMPR_FIX_BUDGET_ASIGN;
			}

			$('#gridArea').jqGrid('footerData', 'set', {
				DEPT_NM : '합계', FIX_AMOUNT_CALC : total_budget_asign
			});

	   	}
	}).trigger("reloadGrid");
}

//상세 조회
function fnView(rowId) {
	if( $.type(rowId) === "undefined" || rowId=="" )
		 rowId = $("#gridArea").getGridParam( "selrow" );

	if( rowId != null ) {
		var rowData = $("#gridArea").getRowData(rowId);
		var group_item_no = rowData["GROUP_ITEM_NO"];

		//alert(group_item_no);
		//COMMON_UTIL.cmWindowOpen('시스템 메뉴 관리', "<c:url value='/manage/menu/selectMenu.do'/>?MENU_ID="+menuId, 550, 400, false, $("#wnd_id").val(), 'center');
	}
	else
		alert('<spring:message code="warn.checkplz.msg" />');
}



function fnRangeSelection(){

	if($("#SLCTN_BUDGET").val().includes(".")){
		alert("정수로 입력하세요.");
		$("#SLCTN_BUDGET").val(0);
		return;
	}
	var vSLCTN_BUDGET =  parseFloat($("#SLCTN_BUDGET").val().replace(/,/g,""));
	var vForm = $("#frmRpairTrgetSlctn");
	var postData ={"TRGET_SLCTN_NO":vForm.find('#TRGET_SLCTN_NO').val()
			,"SLCTN_BUDGET":vForm.find('#SLCTN_BUDGET').val().replace(/,/g,"")
			,"DEPT_CODE":vForm.find('#DEPT_CODE').val()
			,"DECSN_MTHD_1_RATE":vForm.find('#DECSN_MTHD_1_RATE').val()
			,"DECSN_MTHD_2_RATE":vForm.find('#DECSN_MTHD_2_RATE').val()
			,"DECSN_MTHD_3_RATE":vForm.find('#DECSN_MTHD_3_RATE').val()};
	var action = '<c:url value="/api/rpairtrgetslctn/rangeSelection.do"/>';

	//vForm.find('#TRGET_SLCTN_NO').val("");
	if (COMMON_LANG.isnotempty(postData.SLCTN_BUDGET) == false) {
		alert("총 예산을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_1_RATE) == false) {
		alert("Worst-First 예산분배 비율을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_2_RATE) == false) {
		alert("Best-First 예산분배 비율을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_3_RATE) == false) {
		alert("Best-First 예산분배 비율을 입력하십시오.");
		return;
	}
	showRTProgress("보수대상 선정 과정을 진행합니다.");
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
//			alert(JSON.stringify(data));
			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
//			parent.fnRefreshRepairTarget();
//			parent.closeRTDialog();
			hideRTProgress();
			parent.COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
		},
		error: function(a,b,msg){
			hideRTProgress();
			alert(JSON.stringify(data));
		}
	});

}

function dofndownloadAll(){
	var vform = $("#frmRpairTrgetGroup");
	vform.find("#TMPR_SLCTN_AT").val('');
	var action = '';
	action += '<c:url value='/'/>rpairtrgetgroup/downloadExcel.do';
	vform.prop("action", action);
	vform.submit();
}
function dofndownloadSelection(){
	var vform = $("#frmRpairTrgetGroup");
	vform.find("#TMPR_SLCTN_AT").val('Y');
	var action = '';
	action += '<c:url value='/'/>rpairtrgetgroup/downloadExcel.do';
	vform.prop("action", action);
	vform.submit();
}
function fndownload_RepairTarget(){

	var confirmTitle, msgContents, btnTitles, btnCancleTitle, callOKs, callBackCancel;
	btnTitles = [
			"보수대상 구간 전체 내보내기", "범위내 선정 구간만 내보내기" ];
	callOKs = [
			"dofndownloadAll", "dofndownloadSelection" ];
	confirmTitle = "보수대상 선정 - 엑셀 저장";
	msgContents = '보수대상 구간 전체 또는 범위내 선정 구간만 엑셀로 다운로드 할 수 있습니다.';

	fnSwitchDialog(confirmTitle, msgContents, btnTitles, btnCancleTitle, callOKs, callBackCancel);

}

function fnSaveComplete(){
	//fnRefreshRepairTarget
	var confirmTitle, msgContents, btnOkTitle, btnCancleTitle, callOK, callBackCancel;
	confirmTitle = "보수대상 선정 - 선정 저장";
	msgContents = "저장하겠습니까?";
	btnOkTitle = "저장";
	callOK = "dofnSaveComplete";
	fnConfirmDialog(confirmTitle, msgContents, btnOkTitle, btnCancleTitle, callOK, callBackCancel);
}

function dofnSaveComplete(){

	var vForm = $("#frmRpairTrgetSlctn");
	var postData = {
		"TRGET_SLCTN_NO" : vForm.find('#TRGET_SLCTN_NO').val()
	};
	var action = '<c:url value="/api/rpairtrgetslctn/saveComplete.do"/>';


	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
//			alert(JSON.stringify(data));
			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			// parent.fnRefreshRepairTarget();
			// parent.closeRTDialog();
			fnShowMsgDialog("보수대상 선정 - 선정 저장", '저장되었습니다.', function() {
				parent.initRepairTargets();
				parent.COMMON_UTIL.repairMenuUrlContent('<c:url value="rpairtrgetslctn/intro.do"/>');
			});
			// parent.COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);

		}, error : function(a, b, msg) {
			alert(JSON.stringify(data));
		}
	});
}

function fnBudgetRateChange(objNo){
	//alert($("#DECSN_MTHD_"+ objNo+"_RATE").val());
	var rateLimit = 100;
	var rateSum = 0;
	if (objNo > 0) {
		for (var i = 1; i <= 3; i++) {

			if ($("#DECSN_MTHD_" + i + "_RATE").val().includes(".")) {
				alert("정수로 입력하세요.");
				$("#DECSN_MTHD_" + objNo + "_RATE").val(0);
				return;
			}
			rateSum += parseInt($("#DECSN_MTHD_" + i + "_RATE").val());
			if (rateSum > rateLimit) {
				alert("예산분배 비율의 합은 100을 넘을 수 없습니다.");
				$("#DECSN_MTHD_" + objNo + "_RATE").val(0);
				return;
			}
		}
	}

	var rateVal = 0;
	var vSLCTN_BUDGET = parseInt($("#SLCTN_BUDGET").val().replace(/,/g, ""));
	for (var i = 1; i <= 3; i++) {
		if ($("#DECSN_MTHD_" + i + "_RATE").val() != "") {
			rateVal = parseInt($("#DECSN_MTHD_" + i + "_RATE").val());
			$("#DECSN_MTHD_" + i + "_BUDGET").text(COMMON_UTIL.cmAddComma(vSLCTN_BUDGET * rateVal / 100));
		}
	}
}


function fn_select_cellSectFilter(){

	var vForm = $("#frmRpairTrgetSlctn");
	var postData = {
		"TRGET_SLCTN_NO" : vForm.find('#TRGET_SLCTN_NO').val(), "TMPR_SLCTN_AT" : "Y", "USE_AT" : "Y", "DELETE_AT" : "N"
	};
	var action = '<c:url value="/api/rpairtrgetgroup/selectRpairTrgetGroupCELLListRest.do"/>';


	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(datas){
//			alert(JSON.stringify(datas));
			var cellids = "";
			if (datas != null && datas.length > 0) {
				for (var i = 0; i < datas.length; i++) {
					var item = datas[i];
					if (cellids != "") {
						if (item.CELL_IDS != "" && item.CELL_IDS != null) {
							cellids += "," + item.CELL_IDS;
						}
					} else {
						if (item.CELL_IDS != "" && item.CELL_IDS != null) {
							cellids = item.CELL_IDS;
						}
					}
				}
				fn_select_cellSectIDS(cellids);
			}
		}, error : function(a, b, msg) {
			alert(JSON.stringify(data));
		}
	});

}
/*
 * 선정 ID(복수) 위치조회
 */
function fn_select_cellSectID(cell_ids){
	var tables = [ "CELL_SECT" ];
	var fields = [];
	var values = [];
	var cellList = cell_ids.split(",");
	for (var i = 0; i < cellList.length; i++) {
		fields.push("CELL_ID");
		values.push(cellList[i]);
	}

	var attribute = {
		attributes : {
			fillColor : '#ff0000', strokeColor : '#ff0000'
		}
	};
	var operator = " ";
	if (cellList.length > 1) {
		operator = "OR";
	}
	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.repairtargetsHideBottom();

	var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
	var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

	parent.gMap.setLayerIndex(attrLayer, 1);
	parent.gMap.setLayerIndex(multiLayer, 0);
	//
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, operator, attribute, true, 1, 0);
}
/*
 * 선정 ID(복수) 위치조회
 */
function fn_select_cellSectIDS(cell_ids){
	var tables = [ "CELL_SECT" ];
	var fields = [];
	var values = [];
	var cellList = cell_ids.split(",");
	for (var i = 0; i < cellList.length; i++) {
		fields.push("CELL_ID");
		values.push(cellList[i]);
	}

	var attribute = {
		attributes : {
			fillColor : '#ff0000', strokeColor : '#ff0000'
		}
	};
	var operator = " ";
	if (cellList.length > 1) {
		operator = "OR";
	}
	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.repairtargetsHideBottom();

	var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
	var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

	parent.gMap.setLayerIndex(attrLayer, 1);
	parent.gMap.setLayerIndex(multiLayer, 0);
	//
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [ fields ], [ values ], null, operator, attribute, true, 1, 0);
}

/**
 * 선정구간 위치조회
 */
function fn_select_cellSectRange(route_code, direct_code, track, strtpt, endpt){
	var tables = [ "CELL_SECT" ];
	var fields = [ [
			"ROUTE_CODE", "DIRECT_CODE", "TRACK" ] ];
	var boundaryFields = [ [
			"STRTPT", "ENDPT" ] ];
	var boundaryValues = [ [
			strtpt, endpt ] ];
	var values = [ [
			route_code, direct_code, track ] ];
	var attribute = {
		attributes : {
			fillColor : '#ff0000', strokeColor : '#ff0000'
		}
	};

	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.repairtargetsHideBottom();

	var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
	var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

	parent.gMap.setLayerIndex(attrLayer, 1);
	parent.gMap.setLayerIndex(multiLayer, 0);
	//
	MAP.fn_get_selectFeatureByAttrBoundary(parent.gMap, tables, fields, values, null, "AND", attribute, true, 1, 0, boundaryFields, boundaryValues);
}



//필터적용
function fnFilterApply(){
	//btnFilterSearch
	var vForm = $("#frmRpairTrgetGroup");

	var vUseFilter = vForm.find("#useFilter").val();
	if(vUseFilter=="false"){
		vForm.find("#useFilter").val(true);
		$("#btnFilterSearch").html("필터해제");
	}
	else{
		vForm.find("#useFilter").val(false);
		vForm.find("#ROAD_GRAD").val("");
		vForm.find("#ROUTE_CODE").val("");
		vForm.find("#ROAD_NO_VAL").val("");
		vForm.find("#ROAD_NAME").val("");
		vForm.find("#ADM_CODE").val("");
		vForm.find("#MNG_RD_CD").val("");
		$("#btnFilterSearch").html("필터적용");
	}
	fnSearch();
}
function fnFilterSelect(){
	fnSearch();
}

function fn_showChart_RepairTarget() {
	$("#repairtargetStats").dialog("open");
	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {
		"TRGET_SLCTN_NO" : vForm.find("#TRGET_SLCTN_NO").val()
	};
	showRTProgress("차트를 조회합니다.");
	isChart = true;
	fnShowChart();
	fn_showChartDepts(vPostData.TRGET_SLCTN_NO);
	fn_showChartMethods(vPostData.TRGET_SLCTN_NO);
	fn_showChartAdmins(vPostData.TRGET_SLCTN_NO);

}
function fn_closeChart_RepairTarget() {
	$("#repairtargetStats").dialog("close");
}


/**
 * 관리기관별 차트/표 조회
 */
function fn_showChartDepts(trget_slctn_no){
	var postData = {
		"TRGET_SLCTN_NO" : trget_slctn_no, "TMPR_SLCTN_AT" : "Y"
	};

	var actionUrl = '<c:url value="/api/rpairtrgetgroup/selectRpairTrgetDeptStatistics.do"  />';
	$.ajax({
        url: actionUrl,
        data: JSON.stringify(postData),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (datas) {
        	var dataList = datas;
			if(dataList.length !=0){
				drawChartDepts(dataList);
				showGridDepts(dataList);
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });

}


/**
 * 공법별 차트/표 조회
 */
function fn_showChartMethods(trget_slctn_no){
	var postData = {
		"TRGET_SLCTN_NO" : trget_slctn_no, "TMPR_SLCTN_AT" : "Y"
	};

	var actionUrl = '<c:url value="/api/rpairtrgetgroup/selectRpairTrgetMethodStatistics.do"  />';
	$.ajax({
        url: actionUrl,
        data: JSON.stringify(postData),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (datas) {
        	var dataList = datas;
			if(dataList.length !=0){
				drawChartMethods(dataList);
				showGridMethods(dataList);

			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}

/**
 * 단위행정구역 차트/표 조회
 */
function fn_showChartAdmins(trget_slctn_no){
	var postData = {
		"TRGET_SLCTN_NO" : trget_slctn_no, "TMPR_SLCTN_AT" : "Y"
	};

	var actionUrl = '<c:url value="/api/rpairtrgetgroup/selectRpairTrgetAdminStatistics.do"  />';
	$.ajax({
        url: actionUrl,
        data: JSON.stringify(postData),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (datas) {
        	var dataList = datas;
			if(dataList.length !=0){
				drawChartAdmins(dataList);
				showGridAdmins(dataList);
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}
/**
 * 관리기관별 차트 조회
 */

function drawChartDepts(dataList){
	var rw = $(window).width() / 3;

	var chartData = [];
	for (var i = 0; i < dataList.length; i++) {
		if (dataList[i].DEPT_NM != '' && dataList[i].DEPT_NM != null) {
			chartData.push({
				"value" : dataList[i].AMOUNT_CALC, "name" : dataList[i].DEPT_NM
			});
		}
	}

	if(myChartDept!=null){
		myChartDept.clear();
		myChartDept.dispose();
		myChartDept = null;
	}
	require(['echarts', 'echarts/chart/pie' ], function(ec) {
		var option2 = {
			center : [
					'50%', '50%' ],
			radius : [
					0, '75%' ],
			clockWise : true,
			startAngle : 90,
			minAngle : 0,
			selectedOffset : 10,

			title : {
				text : '예산(원)'
			},
			tooltip : {
				trigger : 'item', formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical', x : 'right', y : 'center', data : [
						'북부도로과', '도로건설과' ]
			},
			toolbox : {
				show : true, feature : {
					// dataView : {show: true, readOnly: false}, // 상세조회
					saveAsExcel : {
						show : true
					}, // 엑셀저장
					saveAsImage : {
						show : true
					}
				// 이미지저장
				}
			},
			series : [ {
				name : '',
				type : 'pie',
				radius : '70%',
				center : [
						'50%', '60%' ],
				// color: ['#8fa2d4','#3B64AD'] ,
				// color:['#fff', '#f00'],

				itemStyle : {
					normal : {
						borderColor : '#fff',
						borderWidth : 1,
						color : function(params) {
							var colorList = [
									'#8FA2D4', '#3B64AD', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
									'#FE2752', '#7EEA59', '#F2B731', '#D8C35C', '#41C0AE', '#FE8422', '#6EEA54', '#F1B541', '#D6C32C', '#73C1AE',
									'#FD8610', '#CDEF60', '#FD6ACE', '#E6D410', '#20C5AD', '#DD810E', '#CCDA80', '#BE88CA', '#D21810', '#31D2BA',
									'#EE91AE', '#EFCBCD', '#CDE1BB', '#CDE991', '#98613B', '#FADE06', '#BB78FF', '#ECCDC0', '#FBA001', '#E6734C',
									'#FFBBEE', '#991122', '#DDEECC', '#AABCD5', '#484ABC' ];
							return colorList[params.dataIndex]
						}, label : {
							show : true, position : 'inner', formatter : function(params) {
								return params.name + '\n' + COMMON_UTIL.cmAddComma(params.value) + '원\n'
							}, textStyle : {
								baseline : 'bottom'
							}
						}, labelLine : {
							show : false, length : 20, lineStyle : {
								width : 1, type : 'solid'
							}
						}
					}, emphasis : {
						borderColor : 'rgba(0,0,0,0)', borderWidth : 1, label : {
							show : false
						// position: 'outer'
						}, label : {
							show : false, position : 'center', textStyle : {
								fontSize : '50', fontWeight : 'bold'
							}
						}, labelLine : {
							show : false, length : 20, lineStyle : {
								width : 1, type : 'solid'
							}
						}
					}
				}, data : chartData
			} ]
		}

		myChartDept = ec.init(document.getElementById('rtChartDeptCode'));
		myChartDept.setOption(option2);
		if( myChartDept != null &&  myChartMethod != null && myChartAdmin != null){
			hideRTProgress();
		}

	});
}
/**
 * 보수공법별 차트 조회
 */

function drawChartMethods(dataList){
	var rw = $(window).width() / 3;

	var chartData = [];
	var legendName = [];
	for (var i = 0; i < dataList.length; i++) {
		if (dataList[i].MSRC_CL_NM != '' && dataList[i].MSRC_CL_NM != null) {
			chartData.push({
				"value" : dataList[i].AMOUNT_CALC, "name" : dataList[i].MSRC_CL_NM
			});
			legendName.push(dataList[i].MSRC_CL_NM);
		}
	}


	if(myChartMethod!=null){
		myChartMethod.clear();
		myChartMethod.dispose();
		myChartMethod = null;
	}
	require(['echarts', 'echarts/chart/pie' ], function(ec) {

		var option2 = {
			center : [
					'40%', '30%' ],
			radius : [
					0, '60%' ],
			clockWise : true,
			startAngle : 90,
			minAngle : 0,
			selectedOffset : 10,

			title : {
				text : '예산(원)'
			},
			tooltip : {
				trigger : 'item', formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',// horizontal
				x : 'right', y : 'center', data : legendName
			},
			toolbox : {
				show : true, feature : {
					// dataView : {show: true, readOnly: false}, // 상세조회
					saveAsExcel : {
						show : true
					}, // 엑셀저장
					saveAsImage : {
						show : true
					}
				// 이미지저장
				}
			},
			series : [ {
				name : '',
				type : 'pie',
				radius : '70%',
				center : [
						'45%', '55%' ],
				// color: ['#8fa2d4','#3B64AD'] ,
				// color:['#fff', '#f00'],

				itemStyle : {
					normal : {
						borderColor : '#fff',
						borderWidth : 1,
						color : function(params) {
							var colorList = [
									'#507E32', '#5F933B', '#6BA543', '#88B76E', '#ACCA9E', '#C9DBC1', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
									'#FE2752', '#7EEA59', '#F2B731', '#D8C35C', '#41C0AE', '#FE8422', '#6EEA54', '#F1B541', '#D6C32C', '#73C1AE',
									'#FD8610', '#CDEF60', '#FD6ACE', '#E6D410', '#20C5AD', '#DD810E', '#CCDA80', '#BE88CA', '#D21810', '#31D2BA',
									'#EE91AE', '#EFCBCD', '#CDE1BB', '#CDE991', '#98613B', '#FADE06', '#BB78FF', '#ECCDC0', '#FBA001', '#E6734C',
									'#FFBBEE', '#991122', '#DDEECC', '#AABCD5', '#484ABC' ];
							return colorList[params.dataIndex]
						}, label : {
							show : true, position : 'outer', formatter : function(params) {
								return params.name + '\n' + COMMON_UTIL.cmAddComma(params.value) + '원\n'
							}, textStyle : {
								baseline : 'top'
							}
						}, labelLine : {
							show : true, length : 10, lineStyle : {
								width : 1, type : 'solid'
							}
						}
					}, emphasis : {
						borderColor : 'rgba(0,0,0,0)', borderWidth : 1, label : {
							show : false
						// position: 'outer'
						}, label : {
							show : false, position : 'center', textStyle : {
								fontSize : '50', fontWeight : 'bold'
							}
						}, labelLine : {
							show : false, length : 20, lineStyle : {
								width : 1, type : 'solid'
							}
						}
					}
				}, data : chartData
			} ]
		};

		myChartMethod = ec.init(document.getElementById('rtChartMethodCode'));
		myChartMethod.setOption(option2);
		if( myChartDept != null &&  myChartMethod != null && myChartAdmin != null){
			hideRTProgress();
		}
	});
}
/**
 * 단위행정구역 차트 조회
 */

function drawChartAdmins(dataList){
	var yLabelWidth = 100;
	var xLabelHeight = 80;
	var rw = $(window).width() / 1.20 - yLabelWidth;
	var minRW = 1100;
	rw = Math.max(rw, minRW);
	var degree = 45;
	if (dataList.length < 10) {
		degree = 0;
	}
	var chartData = [];
	var legendName = [];
	for (var i = 0; i < dataList.length; i++) {
		if (dataList[i].ADM_NM != '' && dataList[i].ADM_NM != null) {
			chartData.push({
				"value" : dataList[i].AMOUNT_CALC, "name" : dataList[i].ADM_NM
			});
			legendName.push(dataList[i].ADM_NM);
		}
	}
	if(myChartAdmin!=null){
		myChartAdmin.clear();
		myChartAdmin.dispose();
		myChartAdmin = null;
	}

	require([	'echarts','echarts/chart/bar'	], function (ec) {
		var option2 = {
			title : {
				text : '예산(원)', x : "center"
			},
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : true, feature : {
					// dataView : {show: true, readOnly: false}, // 상세조회
					saveAsExcel : {
						show : true
					}, // 엑셀저장
					saveAsImage : {
						show : true
					}
				// 이미지저장
				}
			},
			grid : {
				width : rw + 'px', x : yLabelWidth, y2 : xLabelHeight
			},
			xAxis : [ {
				type : 'category', axisLabel : {
					show : true, interval : 0, rotate : degree
				}, data : legendName
			} ],
			yAxis : [ {
				name : '원', type : 'value'
			} ],
			series : [ {
				name : '',
				type : 'bar',
				itemStyle : {
					normal : {
						color : function(params) {
							var colorList = [
									'#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
									'#FE2752', '#7EEA59', '#F2B731', '#D8C35C', '#41C0AE', '#FE8422', '#6EEA54', '#F1B541', '#D6C32C', '#73C1AE',
									'#FD8610', '#CDEF60', '#FD6ACE', '#E6D410', '#20C5AD', '#DD810E', '#CCDA80', '#BE88CA', '#D21810', '#31D2BA',
									'#EE91AE', '#EFCBCD', '#CDE1BB', '#CDE991', '#98613B', '#FADE06', '#BB78FF', '#ECCDC0', '#FBA001', '#E6734C',
									'#FFBBEE', '#991122', '#DDEECC', '#AABCD5', '#484ABC' ];
							// return colorList[params.dataIndex]
							// 값 고정
							return '#5B9BD5';
						}
					}
				}, data : chartData
			} ]
		};

		myChartAdmin = ec.init(document.getElementById('rtChartAdminCode'));
		myChartAdmin.setOption(option2);

		if( myChartDept != null &&  myChartMethod != null && myChartAdmin != null){
			hideRTProgress();
		}
	});
}

/*
 * 도로등급 변경 시 노선번호 자동 조회
 */
function fn_change_roadNo(val) {
	var roadGrad = $("#ROAD_GRAD").val();

	$.ajax({
		url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data : JSON.stringify({ROAD_GRAD : roadGrad})
		,success: function(data){
			var txtHtml = "<option value=''>== 전체 ==</option>";

			for(var i=0; i < data.length; i++){
				txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
			}

			var no = $("#ROUTE_CODE").val();
			var name = $("#ROAD_NAME").val();

			$("#ROUTE_CODE").html(txtHtml);
			$("#ROAD_NAME").val("");

			if(val != undefined){
				$("#ROUTE_CODE").val(val);
				fn_change_roadNm();
			}
		}
		,error: function(a,b,msg){

		}
	});
}

/*
 * 노선 번호 변경 시 노선명 자동 조회
 */
function fn_change_roadNm() {
	var roadNo = $("#ROUTE_CODE").val();
	var roadGrad = $("#ROAD_GRAD").val();

	if(roadNo == "") {
		$("#ROAD_NAME").val("");
		$("#ROAD_GRAD").val("");
		return;
	}

	$.ajax({
		url: contextPath + 'api/routeinfo/selectRouteInfo.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data : JSON.stringify({ROAD_NO : roadNo})
		,success: function(data){
			$("#ROAD_NAME").val(data.ROAD_NAME);
			$("#ROAD_GRAD").val(data.ROAD_GRAD);
		}
		,error: function(a,b,msg){

		}
	});
}
/**
 * 예산집행기관 표 조회
 */
function showGridDepts(dataList){
	//rtGridDeptCode
	$("#rtGridDeptCodeArea").jqGrid({
		datatype: "local"
		, ignoreCase: true
		, height : 139
		, width : 400
	   	, colNames:['예산집행기관','예산(원)','TOTAL_AMOUNT_CALC']
	   	, colModel:[
	   		{name:'DEPT_NM',index:'DEPT_NM', width:200, align: "center", hidden: false, sortable:false}
	   		,{name:'AMOUNT_CALC',index:'AMOUNT_CALC',  width: 200,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'TOTAL_AMOUNT_CALC',index:'TOTAL_AMOUNT_CALC',  width: 80,align: "right", hidden: true, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
	   	]
		, loadtext: "검색 중입니다."
	    , footerrow: false
		, userDataOnFooter : false
		//,emptyrecords: "검색된 데이터가 없습니다."
	   	, multiselect: false
		, multiboxonly: false
	});
	$('#rtGridDeptCodeArea').jqGrid('clearGridData');
	var i = 0;
	for (i = 0; i <= dataList.length; i++){
		$("#rtGridDeptCodeArea").jqGrid('addRowData', i + 1, dataList[i]);
	}


	var total_amount_calc = 0;
	if (dataList.length != null && dataList.length > 0) {
		total_amount_calc = dataList[0].TOTAL_AMOUNT_CALC;
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridDeptCodeArea', $("#rtGridDeptCodeArea").jqGrid("getGridParam").emptyrecords, dataList.length);
	} else {
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridDeptCodeArea', $("#rtGridDeptCodeArea").jqGrid("getGridParam").emptyrecords, 0);
	}
	$("#rtGridDeptCodeArea").jqGrid('addRowData', i + 1, {
		DEPT_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
	/*
	$('#rtGridDeptCodeArea').jqGrid('footerData', 'set', {
		DEPT_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
    */
	/**
	var grid = $("#rtGridDeptCodeArea");
	var  ids = grid.getDataIDs();
	for (var i = 0; i < ids.length; i++) {
	    grid.setRowData(ids[i], false, { height : 40 + (i * 2) });
	}
	*/
}
/**
 * 보수공법종류 표 조회
 */
function showGridMethods(dataList){
	//rtGridMethodCode
	$("#rtGridMethodCodeArea").jqGrid({
		datatype: "local"
		, ignoreCase: true
		, height : 139
		, width : 400
	   	, colNames:['보수공법종류','예산(원)','TOTAL_AMOUNT_CALC']
	   	, colModel:[
	   		{name:'MSRC_CL_NM',index:'MSRC_CL_NM', width:200, align: "center", hidden: false, sortable:false}
	   		,{name:'AMOUNT_CALC',index:'AMOUNT_CALC',  width: 200,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'TOTAL_AMOUNT_CALC',index:'TOTAL_AMOUNT_CALC',  width: 80,align: "right", hidden: true, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
	   	]
		, loadtext: "검색 중입니다."
	    , footerrow: false
		, userDataOnFooter : false
		//,emptyrecords: "검색된 데이터가 없습니다."
	   	, multiselect: false
		, multiboxonly: false
	});
	$('#rtGridMethodCodeArea').jqGrid('clearGridData');
	var i = 0;
	for (i = 0; i <= dataList.length; i++)
		$("#rtGridMethodCodeArea").jqGrid('addRowData', i + 1, dataList[i]);

	var total_amount_calc = 0;
	if (dataList.length != null && dataList.length > 0) {
		total_amount_calc = dataList[0].TOTAL_AMOUNT_CALC;
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridMethodCodeArea', $("#rtGridMethodCodeArea").jqGrid("getGridParam").emptyrecords, dataList.length);
	} else {
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridMethodCodeArea', $("#rtGridMethodCodeArea").jqGrid("getGridParam").emptyrecords, 0);
	}
	$("#rtGridMethodCodeArea").jqGrid('addRowData', i + 1, {
		MSRC_CL_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
	/*
	$('#rtGridMethodCodeArea').jqGrid('footerData', 'set', {
		MSRC_CL_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
	*/
}
/**
 * 단위행정구역 표 조회
 */
function showGridAdmins(dataList){
	//rtGridAdminCode
	$("#rtGridAdminCodeArea").jqGrid({
		datatype: "local"
		, ignoreCase: true
		, height : 139
		, width : 1004
	   	, colNames:['단위행정구역','예산(원)','TOTAL_AMOUNT_CALC']
	   	, colModel:[
	   		{name:'ADM_NM',index:'ADM_NM', width:500, align: "center", hidden: false, sortable:false}
	   		,{name:'AMOUNT_CALC',index:'AMOUNT_CALC',  width: 500,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'TOTAL_AMOUNT_CALC',index:'TOTAL_AMOUNT_CALC',  width: 80,align: "right", hidden: true, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
	   	]
		, loadtext: "검색 중입니다."
	    , footerrow: false
		, userDataOnFooter : false
	   	, multiselect: false
		, multiboxonly: false
	});
	$('#rtGridAdminCodeArea').jqGrid('clearGridData');
	var i = 0;
	for (i = 0; i <= dataList.length; i++)
		$("#rtGridAdminCodeArea").jqGrid('addRowData', i + 1, dataList[i]);

	var total_amount_calc = 0;
	if (dataList.length != null && dataList.length > 0) {
		total_amount_calc = dataList[0].TOTAL_AMOUNT_CALC;
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridAdminCodeArea', $("#rtGridAdminCodeArea").jqGrid("getGridParam").emptyrecords, dataList.length);
	} else {
		COMMON_UTIL.fn_set_grid_noRowMsg('rtGridAdminCodeArea', $("#rtGridAdminCodeArea").jqGrid("getGridParam").emptyrecords, 0);
	}
	$("#rtGridAdminCodeArea").jqGrid('addRowData', i + 1, {
		ADM_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
	/*
	$('#rtGridAdminCodeArea').jqGrid('footerData', 'set', {
		ADM_NM : '합계', AMOUNT_CALC : total_amount_calc
	});
	*/
}

/**
 * 차트 와 표 다이얼로그 토글 표시
 */
function fnToggleChart() {


	isChart = !isChart;
	fnShowChart();
}

function fnShowChart(){

	if (isChart==false) {
		$('#rtGridDeptCode').show();
		$('#rtGridMethodCode').show();
		$('#rtGridAdminCode').show();
		$('#rtChartDeptCode').hide();
		$('#rtChartMethodCode').hide();
		$('#rtChartAdminCode').hide();
		$('#btnToggleChart').html("차트로 보기");
	} else {
		$('#rtGridDeptCode').hide();
		$('#rtGridMethodCode').hide();
		$('#rtGridAdminCode').hide();
		$('#rtChartDeptCode').show();
		$('#rtChartMethodCode').show();
		$('#rtChartAdminCode').show();
		$('#btnToggleChart').html("표로 보기");
	}
}

/**
 * 차트용 엑셀 다운로드 열기: 함수명은 고정, se로 분기할 것.
 */
function fnExcel(se) {
	// alert(se);
	var vform = $("#frmRpairTrgetGroup");
	var vOldTMPR_SLCTN_AT = vform.find("#TMPR_SLCTN_AT").val();
	var action = '';
	if (confirm("엑셀 파일로 저장하시겠습니까?")) {

		if (se == "rtChartDeptCode") {
			action = '<c:url value='/'/>rpairtrgetgroup/downloadExcelRpairTrgetDeptStatistics.do';
			vform.prop("action", action);
			vform.submit();
		} else if (se == "rtChartMethodCode") {
			action = '<c:url value='/'/>rpairtrgetgroup/downloadExcelRpairTrgetMethodStatistics.do';
			vform.prop("action", action);
			vform.submit();
		} else if (se == "rtChartAdminCode") {
			action = '<c:url value='/'/>rpairtrgetgroup/downloadExcelRpairTrgetAdminStatistics.do';
			vform.prop("action", action);
			vform.submit();
		}
		//vform.find("#TMPR_SLCTN_AT").val(vOldTMPR_SLCTN_AT);
	}
}
/**
 * 복수 확인 다이얼로그 열기
 */
function fnSwitchDialog(confirmTitle, msgContents, btnTitles, btnCancleTitle, callOKs, callBackCancel) {
	var calcWidth = 140;
	for (var idx = 0; idx < btnTitles.length; idx++) {
		calcWidth += btnTitles[idx].length * 14;
	}
	$("#divConfirmDialog").dialog({
		title : confirmTitle, width : calcWidth + "px"
	});
	var buttonTags = "";
	if (btnTitles != null && callOKs != null) {
		for (var idx = 0; idx < btnTitles.length; idx++) {
			buttonTags += '<a href="#" class="schbtn" onclick="' + callOKs[idx] + '();" style="min-width:70px" >' + btnTitles[idx] + '</a>&nbsp;&nbsp;';
		}
	}
	if (btnCancleTitle != null && btnCancleTitle != "" && callBackCancel != null) {
		buttonTags += '<a href="#" class="schbtn" onclick="' + callBackCancel + '();" style="min-width:70px" >' + btnCancleTitle + '</a>&nbsp;&nbsp;';
	} else {
		buttonTags += '<a href="#" class="graybtn" onclick="closeConfirmDialog();" style="min-width:70px" >닫기</a>';
	}

	$("#divConfirmButtns").html(buttonTags);
	$("#txtConfirmContents").html(msgContents);
	$("#divConfirmDialog").dialog("open");
}

/**
 * 확인 다이얼로그 열기
 */
function fnConfirmDialog(confirmTitle, msgContents, btnOkTitle, btnCancleTitle, callOK, callBackCancel) {

	$("#divConfirmDialog").dialog({
		title : confirmTitle, width : "380px"
	});
	var buttonTags = "";
	if (btnOkTitle != null && btnOkTitle != "" && callOK != null) {
		buttonTags = '<a href="#" class="schbtn" onclick="' + callOK + '();" style="min-width:70px" >' + btnOkTitle + '</a>&nbsp;&nbsp;';
	}
	if (btnCancleTitle != null && btnCancleTitle != "" && callBackCancel != null) {
		buttonTags += '<a href="#" class="schbtn" onclick="' + callBackCancel + '();" style="min-width:70px" >' + btnCancleTitle + '</a>&nbsp;&nbsp;';
	} else {
		buttonTags += '<a href="#" class="graybtn" onclick="closeConfirmDialog();" style="min-width:70px" >닫기</a>';
	}

	$("#divConfirmButtns").html(buttonTags);
	$("#txtConfirmContents").html(msgContents);
	$("#divConfirmDialog").dialog("open");
}
/**
 * 확인 다이얼로그 닫기
 */
function closeConfirmDialog() {
	$("#divConfirmDialog").dialog("close");
}

/**
 * 메시지 다이얼로그 열기
 */
function fnShowMsgDialog(confirmTitle, msgContents, callback) {
	$("#divConfirmDialog").dialog({
		title : confirmTitle, width : "380px", close : callback
	});
	var buttonTags = "";

	buttonTags += '<a href="#" class="schbtn" onclick="closeConfirmDialog();" style="min-width:70px" >닫기</a>';

	$("#divConfirmButtns").html(buttonTags);
	$("#txtConfirmContents").html(msgContents);
	$("#divConfirmDialog").dialog("open");
}
</script>
<div id="divConfirmDialog" class="content " style="display:none;z-index:9999;">
<div class="txtbx" id="txtConfirmContents" >컨텐츠</div>
<div class="tc mt20" id="divConfirmBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
<div class="tc mt20" id="divConfirmButtns">버튼</div>
</div>
</body>
</html>