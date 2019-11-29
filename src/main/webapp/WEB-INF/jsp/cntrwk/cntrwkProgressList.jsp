<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사이력관리</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frmRpairTrgetGroup" name="frmRpairTrgetGroup" method="post" action="">
<div class="tabcont">

	<header class="loc">
        <div class="container">
            <span class="locationHeader">
                <select name="">
                    <option value="">포장공사이력관리</option>
                </select>
                <select name="">
                    <option value="">포장공사진행현황</option>
                </select>
                <h2 class="h2">포장공사 진행현황 목록</h2>
            </span>
        </div>
    </header>
    
    <div class="contents container">
    
    	<article class="div3">
    		<h3 class="h3">검색조건</h3>
    		<div class="table">
    			<table>
    				<tbody>
    				<tr>
    					<td class="th"><label for="SLCTN_YEAR">기준년도</labed></td>
    					<td>
                             <select id="SLCTN_YEAR" name="SLCTN_YEAR" alt="기준년도" class="input" style="width:100px;">
                                <c:forEach items="${slctnYearList }" var="slctnYear">
                                    <option value="${slctnYear.TRGET_SLCTN_NO }">${slctnYear.SLCTN_YEAR }년</option>
                                </c:forEach>
                             </select>      
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="ROAD_GRAD">도로등급</labed></td>
    					<td>
                             <select id="ROAD_GRAD" name="ROAD_GRAD" alt="도로등급" onchange="fn_change_roadNo();" class="input" style="width:100px;">
                                <option value="">== 전체 ==</option>
                                <c:forEach items="${roadGradList }" var="roadGrad">
                                    <option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
                                </c:forEach>
                             </select>               
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="ROAD_NO">노선번호</label></td>
    					<td>
                             <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input" style="width:100px;">
                                <option value="">== 전체 ==</option>
                                <c:forEach items="${roadNoList }" var="roadNo">
                                    <option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
                                </c:forEach>
                             </select>               
                        </td>
    				</tr>
    				<tr>
    					<td class="th"><label for="ROAD_NAME">노선명</label></td>
    					<td>
                            <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="">
                        </td>
    				</tr>
    				</tbody>	
    			</table>
                <div class="btnArea">
                    <button type="button" class="btn pri" onclick="javascript:fn_search();">검색</button>
                </div>
    		</div>
    	</article>
    	
    	<article class="div9">
    		<h3 class="h3">노선데이터로 검색</h3>
    		<div id="div_grid" class="table">
				<table id="gridArea"></table>				
			</div>
			<div id="gridPager"></div>
        </div>
    	</article>
    	
    </div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">

var cnt = 0;

//페이지 로딩 초기 설정
$( document ).ready(function() {

    parent.gMap.cleanMap();

    //검색 목록 그리드 구성
    var vForm = $("#frmRpairTrgetGroup");
    var vPostData = {
    		"TRGET_SLCTN_NO":$('#SLCTN_YEAR').val(),
            "ROAD_GRAD":$("#ROAD_GRAD").val(),
            "ROUTE_CODE":$('#ROAD_NO option:selected').val()
            };

    var colModels = new Array();
    var colIndex = 0;
    colModels[colIndex++]={name: "btn_check",  index: "btn_check", comments: "선택",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.행정구역코드 */
    colModels[colIndex++]={name: "DEPT_NM",  index: "DEPT_NM", comments: "관리<br/>기관",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.부서코드 */
    colModels[colIndex++]={name: "ROAD_GRAD",  index: "ROAD_GRAD", comments: "도로등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.도로등급 */
    colModels[colIndex++]={name: "ROAD_GRAD_NM",  index: "ROAD_GRAD_NM", comments: "도로<br/>등급",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.도로등급 */
    colModels[colIndex++]={name: "ROUTE_CODE",  index: "ROUTE_CODE", comments: "노선<br/>번호",  width: 50,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.노선_코드 */
    colModels[colIndex++]={name: "ROAD_NO_VAL",  index: "ROAD_NO_VAL", comments: "노선<br/>번호",  width: 50,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.노선_코드 */
    colModels[colIndex++]={name: "ROAD_NM",  index: "ROAD_NM", comments: "노선<br/>명",  width: 50,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.노선_명 */

    colModels[colIndex++]={name: "DIRECT_CODE",  index: "DIRECT_CODE", comments: "행선",  width: 40,align: "center", hidden: false, sortable :false ,  formatter: fn_grid_item}; /*보수_대상_항목_그룹.행선_코드 */
    colModels[colIndex++]={name: "TRACK",  index: "TRACK", comments: "차로",  width: 40,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.차로 */
    //colModels[colIndex++]={name: "MNG_RD_NM",  index: "MNG_RD_NM", comments: "관리도로",  width: 80,align: "center", hidden: false, sortable :false }; /*보수_대상_항목_그룹.관리도로 */
    //colModels[colIndex++]={name: "CELL_TYPE",  index: "CELL_TYPE", comments: "셀_타입",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.셀_타입 */
    //colModels[colIndex++]={name: "CELL_TYPE_NM",  index: "CELL_TYPE_NM", comments: "포장셀<br/>구분",  width: 90,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.셀_타입 */
    colModels[colIndex++]={name: "STRTPT",  index: "STRTPT", comments: "시점<br/>(m)",  width: 60,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.시점 */
    colModels[colIndex++]={name: "ENDPT",  index: "ENDPT", comments: "종점<br/>(m)",  width: 60,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.종점 */
    //colModels[colIndex++]={name: "DEPT_CODE",  index: "DEPT_CODE", comments: "부서코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.부서코드 */
    colModels[colIndex++]={name: "VMTC_GRAD",  index: "VMTC_GRAD", comments: "교통량등급",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.교통량등급 */
    colModels[colIndex++]={name: "VMTC_GRAD_NM",  index: "VMTC_GRAD_NM", comments: "교통<br/>용량",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.교통량등급 */
    colModels[colIndex++]={name: "ADM_CODE",  index: "ADM_CODE", comments: "행정구역코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.행정구역코드 */
    colModels[colIndex++]={name: "ADM_NM",  index: "ADM_NM", comments: "행정<br/>구역",  width: 80,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.행정구역코드 */
    colModels[colIndex++]={name: "CNTRWK_YEAR",  index: "CNTRWK_YEAR", comments: "최근공사<br/>년도",  width: 80,align: "right", hidden: false, sortable :false }; /*보수_대상_항목_그룹.공사_년도 */
    colModels[colIndex++]={name: "KILLO_LEN",  index: "KILLO_LEN", comments: "연장<br/>(km)",  width: 80,align: "right", hidden: false, sortable :false, formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}; /*보수_대상_항목_그룹.연장 */
    colModels[colIndex++]={name: "AR",  index: "AR", comments: "면적<br/>(m<sup>2</sup>)",  width: 80,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' } }; /*보수_대상_항목_그룹.면적 */
    //colModels[colIndex++]={name: "RPAIR_MTHD_CODE",  index: "RPAIR_MTHD_CODE", comments: "보수_공법_코드",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.보수_공법_코드 */
    //colModels[colIndex++]={name: "MSRC_CL_NM",  index: "MSRC_CL_NM", comments: "보수<br/>공법",  width: 150,align: "left", hidden: false, sortable :false }; /*보수_대상_항목_그룹.보수_공법_코드 */
    //colModels[colIndex++]={name: "RPAIR_FEE",  index: "RPAIR_FEE", comments: "단가<br/>(원/m<sup>2</sup>)",  width: 80,align: "right", hidden: false, sortable :false,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.단가(원/m2) */
    colModels[colIndex++]={name: "FIX_BUDGET_ASIGN",  index: "FIX_BUDGET_ASIGN", comments: "금액산정 <br/>(원)",  width: 80,align: "right", hidden: true, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.예산_배정 */
    colModels[colIndex++]={name: "FIX_AMOUNT_CALC",  index: "FIX_AMOUNT_CALC", comments: "금액산정 <br/>(원)",  width: 100,align: "right", hidden: false, sortable :false ,  formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}; /*보수_대상_항목_그룹.예산_배정 */
    colModels[colIndex++]={name: "CALC_GPCI",  index: "CALC_GPCI", comments: "GPCI",  width: 50,align: "right", hidden: false, sortable :false }; /*보수_대상_항목_그룹.산정_GPCI */
    colModels[colIndex++]={name: "TRNSPORT_QY",  index: "TRNSPORT_QY", comments: "교통_량",  width: 80,align: "center", hidden: false, sortable :true }; /*보수_대상_항목_그룹.교통_량 */
    colModels[colIndex++]={name: "POTHOLE_QY",  index: "POTHOLE_QY", comments: "포트홀_량",  width: 80,align: "center", hidden: false, sortable :true }; /*보수_대상_항목_그룹.포트홀_량 */
    colModels[colIndex++]={name: "PAV_CELL_LEN",  index: "PAV_CELL_LEN", comments: "포장공사_진행량",  width: 80,align: "center", hidden: true, sortable :false }; /*보수_대상_항목_그룹.포장공사_진행량 */
    colModels[colIndex++]={name: "PAV_CELL_PERCENT",  index: "PAV_CELL_PERCENT", comments: "포장공사<br/>진행률",  width: 80,align: "center", hidden: false, sortable :true, formatter: function(cellvalue,options,rowObject){ return Math.ceil(rowObject.PAV_CELL_LEN/rowObject.LEN *100)+'%'; } };/*보수_대상_항목_그룹.포장공사_진행률 */
    //colModels[colIndex++]={name: "PRIORT",  index: "PRIORT", comments: "우선순위",  width: 80,align: "center", hidden: false, sortable :false, formatter: fn_priort_txt}; /*보수_대상_항목_그룹.우선순위 */
//  colModels[colIndex++]={name: "btn_loc",  index: "btn_loc", comments: "위치<br/>보기",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.행정구역코드 */
    colModels[colIndex++]={name: "btn_loc2",  index: "btn_loc2", comments: "보수선정<br/>위치보기",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.보수선정위치보기 */
    colModels[colIndex++]={name: "btn_loc3",  index: "btn_loc3", comments: "포장공사<br/>위치보기",  width: 50,align: "center", hidden: false, sortable :false,  formatter: fn_grid_btn}; /*보수_대상_항목_그룹.포장공사위치보기 */

    colModels[colIndex++]={name: "DMG_VAL",  index: "DMG_VAL", comments: "파손도",  width: 50,align: "right", hidden: true, sortable :false }; /*보수_대상_항목_그룹.파손도_값 */
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
        url: '<c:url value="/"/>'+'api/cntrwk/selectCntrwkProgressList.do'
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
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
            fnView(rowId);  // 대장 조회
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
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
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);

    setTimeout(function() {
         fn_search();
    }, 500);
});

//검색 처리
function fn_search() {
    var vForm = $("#frmRpairTrgetGroup");
    var vPostData = {
            "TRGET_SLCTN_NO":$('#SLCTN_YEAR').val(),
            "ROAD_GRAD":$("#ROAD_GRAD").val(),
            "ROUTE_CODE":$('#ROAD_NO option:selected').val()
            };


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

// jqGrid 도면 다운로드, 위치이동 버튼 생성 custom-formatter
function fn_grid_btn(cellValue, options, rowObject) {
    var btn = "";
    var nm = options.colModel.name;

    switch (nm) {
    case "btn_down":// 파일 다운로드
        btn = "<a href='#' onclick=\"fn_select_dwg('" + rowObject.ROAD_NO + "')\"><img src='" + contextPath + "/images/ic_download.png' alt='다운로드' title='다운로드' ></a>";
        break;
    case "btn_loc": { // 위치보기(미사용)
        btn = "<a href='#' onclick=\"javascript:fn_select_cellSectRange('" + rowObject.ROUTE_CODE + "', '" + rowObject.DIRECT_CODE + "', '" + rowObject.TRACK + "', '" + rowObject.STRTPT + "', '" + rowObject.ENDPT + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
        break;
    case "btn_check": { // row선택 체크박스
        if (rowObject.TMPR_SLCTN_AT == "Y") {// || rowObject.SLCTN_AT=="Y"
            btn = "<input type='checkbox' id='ck" + rowObject.GROUP_ITEM_NO + "' checked onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" /><label for='ck" + rowObject.GROUP_ITEM_NO + "' class='hiddenLabel onlyCk'>선택</label>";
        } else {
            btn = "<input type='checkbox' id='ck" + rowObject.GROUP_ITEM_NO + "' onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" /><label for='ck" + rowObject.GROUP_ITEM_NO + "' class='hiddenLabel onlyCk'>선택</label>";
        }
    }
        break;
    case "btn_loc2": { // 보수선정 위치보기
        btn = "<a href='#' onclick=\"javascript:fn_select_cellSectFilter('" + rowObject.TRGET_SLCTN_NO + "', '" + rowObject.GROUP_ITEM_NO + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
        break;
    case "btn_loc3": { // 포장공사 위치보기
        btn = "<a href='#' onclick=\"javascript:fn_select_pav_cellSectFilter('" + rowObject.PAV_CELL_IDS + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
        break;
    }


    return btn;
}
// jqGrid 상.하행 버튼 생성 custom-formatter
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
// jqGrid 보수선정 위치보기 버튼 
function fn_select_cellSectFilter(trgetSlctnNo, groupItemNo){

    var vForm = $("#frmRpairTrgetSlctn");
    var postData = {
        "TRGET_SLCTN_NO" : trgetSlctnNo,
        "GROUP_ITEM_NO" : groupItemNo
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
//          alert(JSON.stringify(datas));
            var cellids = [];
            if (datas != null && datas.length > 0) {
                cellids = $.map(datas, function (data) {
                    return data.CELL_ID;
                });

                console.log(cellids);
                fn_select_cellSectIDS(cellids);
            }
        }, error : function(a, b, msg) {
            alert(JSON.stringify(data));
        }
    });
}
// jqGrid 포장공사 위치보기 버튼 
function fn_select_pav_cellSectFilter(pavCellIds){
	if (pavCellIds && pavCellIds.length > 0) {
		var pavCellIdArrays = pavCellIds.split(',');
		fn_select_cellSectIDS(pavCellIdArrays);
	}
}
/*
 * CELL 선정 ID(복수) 위치조회
 */
function fn_select_cellSectIDS(cell_ids){
    var tables = [ "CELL_10" ];
    var fields = [];
    var values = cell_ids;
    var cellList = cell_ids;
    console.log(cellList.length);
    for (var i = 0; i < cellList.length; i++) {
        fields.push("CELL_ID");
//      values.push(cellList[i]);
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



//ROAD_GRAD selectbox 변경시 - 노선번호 자동 조회
function fn_change_roadNo() {
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

            $("#ROAD_NO").html(txtHtml);
            $("#ROAD_NAME").val("");
        }
        ,error: function(a,b,msg){
            console.log(a, b);
            alert(msg);
        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
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
            $("#ROAD_NAME").val("");
            $("#ROAD_GRAD").val("");
        }
    });
}

function fn_select_dwg(val){
    COMMON_UTIL.cmMoveUrl("dwginfo/selectDwgInfoList.do?ROAD_NO="+val);
}

function fn_select_route(route_no){
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [route_no];

    // 모든 팝업창 최소화
    //parent.wWindowHideAll();
    // 하단 목록 창 내리기
    parent.bottomClose();

    var attribute = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, null, attribute);
}

// 통합정보조회 - 상세조회 로 접근했을 때 위치조회
function fn_select_route_nDirect(route_no){

    var features = parent.gMap.getLayerByName('GAttrLayer').features;

    // 하단 목록 창 내리기
    parent.bottomClose();

    // Base로 선택한 노선 보여줌
    var fArr = [];
    var vArr = [];

    for ( var i = 0 ; i < features.length ; i++ ) {

        fArr[i] = "ROAD_NO";
        vArr[i] = features[i].data.ROAD_NO;

    }

    var tables_base = ["DORO_TOT_GRS80_50"];
    var fields_base = [fArr];
    var values_base = [vArr];
    var attribute_base = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base, true, 1, 0);

    // 선택한 노선을 보여줌
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [route_no];
    var attribute = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, "OR", attribute);


}
</script>
</body>
</html>