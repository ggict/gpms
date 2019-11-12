<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap" class="right-tool">

    <!-- 필수 파라메터(START) -->
    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
    <input type="hidden" id="opener_id" name="opener_id" value=""/>
    <input type="hidden" id="wnd_id" name="wnd_id" value=""/>
    <!-- 필수 파라메터(END) -->

    <div class="content">
        <div style="width:600px">
            <ul class="stepbx af">

                <li style="width:50%" id="step1">
                    <div class="stbx">
                        <h4><span class="step">단계1</span>항목선택</h4>
                        <div style="height:80px; padding: 10px 2px;">
                            <ul class="tblst selbx" id="step1_sel">
                                <li style="width:33%" class="tc on">
                                    <a class="selbtn btncursor" id="btn_selRouteInfo">
                                        <span class="roundbx"><img src="<c:url value='/images/ic_sel1.png' />" alt="노선정보"/></span>
                                        <span>노선정보</span>
                                    </a>
                                </li>
                                <li style="width:33%" class="brl tc">
                                    <a class="selbtn btncursor" id="btn_selStatusEvaluation">
                                        <span class="roundbx"><img src="<c:url value='/images/ic_sel2.png' />" alt="포장상태평가" /></span>
                                        <span>포장상태평가<br />(구간 종합)</span>
                                    </a>
                                </li>
                                <li style="width:33%" class="tc">
                                    <a class="selbtn btncursor" id="btn_selBridgeInfo">
                                        <span class="roundbx"><img src="<c:url value='/images/ic_sel3.png' />" alt="교량정보" /></span>
                                        <span>BMS연계정보<br />(교량)</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>

                <li style="width:50%" id="step2">
                    <div class="stbx">
                        <h4><span class="step">단계2</span>선택방법</h4>
                        <div style="height:80px">
                            <ul class="tblst selbx" id="step2_sel">
                                <li style="width:50%; border-left:0px" class="brl tc">
                                    <a class="selbtn btncursor" id="btn_selPoint">
                                        <!-- 노선정보 / 교량정보 -->
                                        <span class="roundbx normalmode"><img src="<c:url value='/images/ic_shape1.png' />" alt="점" /></span>
                                        <span class="normalmode">점 <input type="text" name="pointArea" id="pointArea" maxLength="4" value="100" style="width:33px" /> m</span>
                                        <!-- 포장상태평가 -->
                                        <span class="roundbx statusmode" style="display: none;"><img src="<c:url value='/images/ic_t1.png'/>" alt="단일선택" /></span>
                                        <span class="statusmode" style="display: none;">단일선택</span>
                                    </a>

                                </li>
                                <li style="width:48%;" class="tc">
                                    <a class="selbtn btncursor" id="btn_selPolygon">
                                        <!-- 노선정보 / 교량정보 -->
                                        <span class="roundbx normalmode"><img src="<c:url value='/images/ic_shape2.png' />" alt="다각형" /></span>
                                        <span class="normalmode">다각형</span>
                                        <!-- 포장상태평가 -->
                                        <span class="roundbx statusmode" style="display: none;"><img src="<c:url value='/images/ic_t2.png'/>" alt="다중선택" /></span>
                                        <span class="statusmode" style="display: none;">다중선택</span>
                                    </a>
                                </li>
                                <!-- 포장상태평가 -->
                                <li style="width:25%; border-left:0px; display: none;" class="brl tc">
                                    <a class="selbtn btncursor" id="btn_selRelease">
                                        <span class="roundbx"><img src="<c:url value='/images/ic_t3.png'/>" alt="선택해제" /></span>
                                        <span>선택해제</span>
                                    </a>
                                </li>
                                <li style="width:24%; display: none;" class="tc">
                                    <a class="selbtn btncursor" id="btn_selEnd">
                                        <span class="roundbx"><img src="<c:url value='/images/ic_t4.png'/>" alt="선택완료" /></span>
                                        <span>선택완료</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>

            </ul>

            <!-- 다중선택도구 -->
            <ul class="stepbx mt10" id="dv_multiSelectTools" style="display: none;">
                <li style="width: 100%;">
                    <div class="stbx">
                        <h4>
                            <span class="step">2-1</span>다중선택도구
                        </h4>
                        <div style="height: 100px">
                            <ul class="tblst selbx" id="step_2_1_multiSelect">
                                <li style="width: 18%; height: 90px; padding-top: 10px; border-left: 0px;" class="brl tc btn_ms_tools"><a class="selbtn btncursor" id="btn_selectPoly"> <span class="roundbx"><img src="<c:url value='/images/ic_shape2.png'/>" alt="다각형선택" /></span> <span>다각형선택</span>
                                </a></li>
                                <li style="width: 18%; height: 90px; padding-top: 10px; border-left: 0px;" class="brl tc btn_ms_tools"><a class="selbtn btncursor" id="btn_selectline"> <span class="roundbx"><img src="<c:url value='/images/ic_shape6.png'/>" alt="선형선택" /></span> <span>선형선택</span>
                                </a></li>
                                <li style="width: 18%; height: 90px; padding-top: 10px; border-left: 0px;" class="brl tc btn_ms_tools"><a class="selbtn btncursor" id="btn_userInput"> <span class="roundbx"><img src="<c:url value='/images/ic_shape7.png'/>" alt="시종점입력" /></span> <span>시종점입력</span>
                                </a></li>
                                <li style="border-left: 0px; height: 90px; padding-left: 10px; padding-top: 5px; display:none;" class="tc" id="dv_userInput">
                                    <p style="margin-bottom: 3px;">
                                        노선 :
                                        <select style="width: 150px;" id="sel_ms_ui_route">
                                            <option value="">노선명을 선택하세요</option>
                                        </select>
                                    </p>
                                    <p style="margin-bottom: 3px;">
                                        방향 :
                                        <select style="width: 150px;" id="sel_ms_ui_dir">
                                            <option value=""></option>
                                        </select>
                                    </p>
                                    <p style="margin-bottom: 3px;">
                                        차로 :
                                        <select style="width: 150px;" id="sel_ms_ui_track">
                                            <option value=""></option>
                                        </select>
                                    </p>
                                    <p>
                                        시/종점 : <input type="text" style="width: 50px;" value="0" placeholder="시점" id="ip_ms_ui_strtpt" />m ~ <input type="text" style="width: 50px;" value="4000" placeholder="종점" id="ip_ms_ui_endpt" />m
                                    </p>
                                    <div style="position:absolute; top:10px; right:10px; width:50px;" id="btn_ms_ui_search">
                                        <span class='schbtn' style='cursor:pointer;'>검색</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>

            <!-- STEP 3 START -->
            <ul class="stepbx mt10" id="step3" style="display: none;">
                <li class="wid100">
                    <div class="stbx">
                        <h4>
                            <span class="step">단계3</span>조회결과
                            <!-- <span>최근 조사자료(2017년) 기준 평가정보</span> -->
                            <a href="#" class="togglebtn on" id="toggle" title="닫기"><span class="hidden">열고닫기</span></a>
                        </h4>

                        <div id="result">
                            <!-- 노선조회 START -->
                            <div class="result" id="divRouteInfo" style="display: none;">
                                <form id="frm" name="frm" method="post" action="">
                                    <div id="div_grid" style="width:100%; float: block; padding-top: 5px;">
                                        <table id="gridArea"></table>
                                        <div id="gridPager" style='width: 100%;'></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 노선조회 END -->

                            <!-- 포장상태평가 START -->
                            <div class="result" id="divStatusEvaluation" style="display: none;">
                                <form id="frm2" name="frm2" method="post" action="">
                                    <div id="statusArea">
                                        <h5>
                                            검색결과
                                            <a href="#" onclick="fnSaveExcel();" class="schbtn" style="float: right;">엑셀저장</a>
                                            <form id="frm2" name="frm2" method="post" action="">
                                                <input type="hidden" id="CELL_ID" name="CELL_ID" />
                                            </form>
                                            <span style="float: right; font-size: 10.5px; margin-top: 4px; margin-right: 4px; color: gray;"><span style="color: red;">*</span> 최근 조사자료(2017년) 기준 평가정보</span>
                                        </h5>
                                        <div id="div_grid2" style="width:100%; float: block; padding-top: 5px;">
                                            <table id="gridArea2"></table>
                                            <div id="gridPager2" style='width: 100%;'></div>
                                        </div>
                                        <h5 class="mt15">포장상태 평가 (평가단위:10m셀)</h5>
                                        <ul class="tblst mt15">
                                            <li style="width:33%;border-left:0px" class="brl tc">
                                                <span class="circle bc6" id="gpci" style="width: 150px; height: 55px; line-height: 55px;">0</span>
                                                <span>GPCI</span>
                                            </li>
                                            <li style="width:33%" class="brl tc">
                                                <span class="circle bc5" id="crVal" style="width: 150px; height: 55px; line-height: 55px;">없음</span>
                                                <span>주 파손</span>
                                            </li>
                                            <li style="width:33%" class="tc">
                                                <span class="circle bc7" id="dmgCuz" style="width: 150px; height: 55px; line-height: 55px;">없음</span>
                                                <span>파손원인</span>
                                            </li>
                                        </ul>
                                        <a href="#" class="selmore" id="selmore" onclick="fnSelectMore();"><img src="<c:url value='/images/btn_more.png' />" alt="더보기" title="더보기"/></a>
                                    </div>
                                </form>
                            </div>
                            <!-- 포장상태평가 END -->

                            <!-- 교량조회 START -->
                            <div class="result" id="divBridgeInfo" style="display: none;">
                                <form id="frm3" name="frm3" method="post" action="">
                                    <h4 id="count">검색 결과 : 0건</h4>
                                    <div id="div_grid3" style="width:100%; float: block; padding-top: 5px;">
                                        <table id="gridArea3"></table>
                                        <div id="gridPager3"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 교량조회 END -->
                        </div>

                    </div>

                    <div id="btnResearchDetail" style="display: none;">
                        <a href="#" onclick="fnShowDetailData();" style="margin: 3px; padding: 5px 20px; border-radius: 5px; color: white; font-weight: bold; float: right; background-color: rgba(69, 135, 255, 1);">조사정보조회</a>
                    </div>
                </li>
            </ul>
            <!-- STEP 3 END -->

        </div>
    </div>

</body>

<%@ include file="/include/common.jsp" %>

<script type="text/javascript">

//======================================== DEFAULT SETTING ======================================== //
// [ DEFAULT ] div 초기화
var grid1 = "";
grid1 += "<table id='gridArea'></table>"
       + "<div id='gridPager' style='width: 100%;'></div>";

var grid2 = "";
grid2 += "<table id='gridArea2'></table>"
       + "<div id='gridPager2' style='width: 100%;'></div>";

var brdgHtml = "";
    brdgHtml += "<form id='frm3' name='frm3' method='post' action=''>"
                + "<div id='div_grid3' style='width:100%; float: block; padding-top: 5px;'>"
                + "<table id='gridArea3'></table>"
                + "<div id='gridPager3'></div>"
                + "</div>"
                + "</form>";

// [ DEFAULT ] 전역변수
var features = null;
var cellIds = "";
var objectIds = "";
var option = {};

// [ DEFAULT ] 10셀 레이어 선택
$(document).ready(function() {

    parent.$("#integrated").parent().addClass("active");

    // 지도 초기화
    parent.gMap.cleanMap();
    parent.gMap.activeControls("drag");
    parent.$("#map .left_tool li").removeClass("active");
    parent.$("#mCtrlPan").parent().addClass("active");

    // 레이어 트리 구조 수정 후 레이어 on 될수 있도록 수정
    /* if(parent.$("#dvLayerList").find("li[id$='_MV_SRVYDTA_10']").hasClass('jstree-unchecked') == true){
        parent.$("#dvLayerList").find("li[id$='_MV_SRVYDTA_10'] a ins.jstree-checkbox")[0].click();
    } */

    // 하단 팝업 초기화
    parent.bottomHide();

});

// [ DEFAULT ] 팝업 크기 및 위치 변경
$(parent).resize(function() {

    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();

    // 윈도우창
    if ( ww > 1240 ) {
        ww -= 15;
    }
    ww -= 623;
    wnd.move(ww, 159);

});

// jQuery
$(function () {

    // ======================================== INPUT BOX EVENT ======================================== //
    // [ STEP 2 ] 점 반경범위 변경
    $("#pointArea").change(function(){

        // point mode
        if ( $("#pointArea").parent().parent().parent().hasClass("on") ) {

            var range = $("#pointArea").val();

            if ( range == "" || range == undefined || range == "0" ) {
                alert ("반경 범위를 입력해 주세요.");
                return;
            }

            if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {

                 // 1단계가 노선정보인 경우
                parent.gMap.getControl("selPointRoad").setDistance(range);
                parent.gMap.activeControls("selPointRoad");
                parent.$("#map .left_tool li").removeClass("active");

            } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {

                 // 1단계가 교량인 경우
                 parent.gMap.getControl("selBrdgPoint").setDistance(range);
                 parent.gMap.activeControls("selBrdgPoint");
                 parent.$("#map .left_tool li").removeClass("active");

             } else {

                 // 1단계가 상태평가인 경우
                 parent.gMap.getControl("selPoint").setDistance(range);
                 parent.gMap.activeControls("selPoint");
                 parent.$("#map .left_tool li").removeClass("active");

             }
         }
     });

     // ======================================== SLIDE EVENT ======================================== //
     // [ STEP 3 ] 버튼 토글 이벤트
     $(".togglebtn").click(function () {

         if ( !$(this).hasClass("on") ) {
             if ( $("#btn_selRouteInfo").parent().hasClass("on") && $("#gridArea").html() != "" ) {
                 fnResizePopup(560);

             } else if ( $("#btn_selStatusEvaluation").parent().hasClass("on") && $("#gridArea2").html() != "" ) {
                 fnResizePopup(700);

             } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") && $("#gridArea3").html() != "" ) {
                 fnResizePopup(550);

             }

             $(this).addClass("on");
             $(this).attr("title", "닫기");

         } else {

             fnResizePopup(300);
             $(this).removeClass("on");
             $(this).attr("title", "열기");

         }

         $("#result").slideToggle("slow");

     });

    // ======================================== STEP 1 EVENT ======================================== //
    // [ STEP 1 ] 노선정보
    $("#btn_selRouteInfo").click(function() {

        // initial state
        parent.gMap.activeControls("drag");
        parent.$("#map .left_tool li").removeClass("active");
        parent.$("#mCtrlPan").parent().addClass("active");

        // 현재가 off인 경우 -> on 상태로 변경
        fnClearStep1Data();
        fnChangeMethod("btn_selRouteInfo");
        $("#btn_selRouteInfo").parent().addClass("on");

    });

    // [ STEP 1 ] 포장상태평가
    $("#btn_selStatusEvaluation").click(function() {

        // initial state
        parent.gMap.activeControls("drag");
        parent.$("#map .left_tool li").removeClass("active");
        parent.$("#mCtrlPan").parent().addClass("active");

        // 현재가 off인 경우 -> on 상태로 변경
        fnClearStep1Data();
        fnChangeMethod("btn_selStatusEvaluation");
        $("#btn_selStatusEvaluation").parent().addClass("on");

    });

    // [ STEP 1 ] 교량정보
    $("#btn_selBridgeInfo").click(function() {

        // initial state
        parent.gMap.activeControls("drag");
        parent.$("#map .left_tool li").removeClass("active");
        parent.$("#mCtrlPan").parent().addClass("active");

        // 레이어가 선택되어 있지 않은 경우 자동으로 선택
        if(parent.$("#dvLayerList").find("li[id$='_M_CALS_T']").hasClass('jstree-unchecked') == true){
            parent.$("#dvLayerList").find("li[id$='_M_CALS_T'] a ins.jstree-checkbox")[0].click();
        }

        // 현재가 off인 경우 -> on 상태로 변경
        fnClearStep1Data();
        fnChangeMethod("btn_selBridgeInfo");
        $("#btn_selBridgeInfo").parent().addClass("on");

    });

    // ======================================== STEP 2 EVENT ======================================== //
    // [ STEP 2 ] 점
    $("#btn_selPoint").click(function() {

        // initial state
        parent.gMap.activeControls("drag");
        parent.$("#mCtrlPan").parent().addClass("active");

        option = {};

        // 상태평가모드가 아닌 경우 범위 선택시 바로 검색 - 옵션
        if ( !$("#btn_selStatusEvaluation").parent().hasClass("on") ) {
            option = {
                        iframe : window,
                        callback : "fnCheckFeatures",
                        clearMap : false
           };
        }

        // STEP 1 이 선택되어 있는 경우
        if ( checkStep1() ) {

            // 이미 STEP 2 가 선택되어 있는 경우
            if ( $("#btn_selPoint").parent().hasClass("on") ) {

                 parent.MAP.CONTROL.set_option(option);

                 if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {
                    // 노선정보
                    parent.gMap.activeControls("selPointRoad");
                    parent.$("#map .left_tool li").removeClass("active");
                    parent.gMap.cleanMap();
                    fnClearStep2Data();

                 } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {
                    // 교량정보
                    parent.gMap.activeControls("selBrdgPoint");
                    parent.$("#map .left_tool li").removeClass("active");
                    parent.gMap.cleanMap();
                    fnClearStep2Data();

                 } else {
                    // 포장상태 평가
                    parent.gMap.activeControls("selPoint");
                    parent.$("#map .left_tool li").removeClass("active");

                 }

                 $("#btn_selPoint").parent().addClass("on");

            } else {

                // STEP 2 가 선택되어 있지 않았을 경우
                var range = 1;

                if ( !$("#btn_selStatusEvaluation").parent().hasClass("on") ) {

                    // 상태평가모드가 아닌 경우
                    // 1. 반경범위에 따라 검색
                    // 2. 점 - 다각형 변경 시 선택된 내용 초기화

                    // 반경범위 체크
                    range = $("#pointArea").val();

                    if ( range == "" || range == undefined || range == "0" ) {
                        alert ("반경 범위를 입력해 주세요.");
                        return;
                    }

                     // initial state
                    parent.gMap.cleanMap();
                    parent.gMap.activeControls("drag");
                    parent.$("#mCtrlPan").parent().addClass("active");

                }

                // 현재가 off인 경우 -> on으로 상태 변경
                fnClearStep2Data();
                $("#btn_selPoint").parent().addClass("on");

                // 범위 setting 및 포인터 변경
                parent.MAP.CONTROL.set_option(option);

                if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {
                    // 1단계가 노선정보인 경우
                    parent.gMap.getControl("selPointRoad").setDistance(range);
                    parent.gMap.activeControls("selPointRoad");
                    parent.$("#map .left_tool li").removeClass("active");

                } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {
                    // 1단계가 교량인 경우
                    parent.gMap.getControl("selBrdgPoint").setDistance(range);
                    parent.gMap.activeControls("selBrdgPoint");
                    parent.$("#map .left_tool li").removeClass("active");

                } else {
                    // 1단계가 상태평가인 경우
                    parent.gMap.getControl("selPoint").setDistance(range);
                    parent.gMap.activeControls("selPoint");
                    parent.$("#map .left_tool li").removeClass("active");
                }
                parent.gMap.cleanMap();
            }
        } else {
            // STEP 1 이 선택되어 있지 않은 경우
            alert("단계1을 먼저 선택해 주세요.");
            return;
        }
    });

    // [ STEP 2 ] 다각형
    $("#btn_selPolygon").click(function() {

        // initial state
        parent.gMap.activeControls("drag");
        parent.$("#mCtrlPan").parent().addClass("active");

        option = {};

         // 상태평가모드가 아닌 경우 범위 선택시 바로 검색 - 옵션
        if ( !$("#btn_selStatusEvaluation").parent().hasClass("on") ) {
            option = {
                    iframe : window,
                    callback : "fnCheckFeatures",
                    clearMap : false
            };
        }

        if ( checkStep1() ) {

            // STEP 1 이 선택되어 있는 경우
            if ( $("#btn_selPolygon").parent().hasClass("on") ) {

                parent.MAP.CONTROL.set_option(option);

                if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {

                    // 노선정보
                    parent.gMap.activeControls("selPolygonRoad");
                    parent.$("#map .left_tool li").removeClass("active");
                    parent.gMap.cleanMap();
                    fnClearStep2Data();

                } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {

                    // 교량정보
                    parent.gMap.activeControls("selBrdgPolygon");
                    parent.$("#map .left_tool li").removeClass("active");
                    parent.gMap.cleanMap();
                    fnClearStep2Data();

                } else {

                    // 포장상태평가정보
                    /* parent.gMap.activeControls("selPolygon");
                    parent.$("#map .left_tool li").removeClass("active"); */
                    fnSetMutiSelectTools();
                    fnResizePopup(400);

                }

                $("#btn_selPolygon").parent().addClass("on");

            } else {

                if ( !$("#btn_selStatusEvaluation").parent().hasClass("on") ) {

                    // 상태평가모드가 아닌 경우
                    // 1. 점 - 다각형 변경 시 선택된 내용 초기화

                    // initial state
                    parent.gMap.cleanMap();
                    parent.gMap.activeControls("drag");
                    parent.$("#mCtrlPan").parent().addClass("active");

                }

                 // 현재가 off인 경우 -> on으로 상태 변경
                fnClearStep2Data();
                $("#btn_selPolygon").parent().addClass("on");

                parent.MAP.CONTROL.set_option(option);

                if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {

                    // 1단계가 노선정보인 경우
                    parent.gMap.activeControls("selPolygonRoad");
                    parent.$("#map .left_tool li").removeClass("active");

                } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {

                    // 1단계가 교량인 경우
                    parent.gMap.activeControls("selBrdgPolygon");
                    parent.$("#map .left_tool li").removeClass("active");

                } else {

                    // 1단계가 상태평가인 경우
                    /* parent.gMap.activeControls("selPolygon");
                    parent.$("#map .left_tool li").removeClass("active"); */

                    //다중선택도구 open
                    fnSetMutiSelectTools();
                    fnResizePopup(400);

                }

            }

        } else {

            // STEP 1 이 선택되어 있지 않은 경우
            alert("단계1을 먼저 선택해 주세요.");
            return;

        }

    });

    // [ STEP 2 ] 선택해제
    $("#btn_selRelease").click(function() {

        // initial state
        parent.gMap.cleanMap();
        parent.gMap.activeControls("drag");
        parent.$("#mCtrlPan").parent().addClass("active");

        fnClearStep2Data();
        $("#btn_selRelease").parent().addClass("on");

        parent.gMap.cleanMap();

    });

    // [ STEP 2 ] 선택완료
    $("#btn_selEnd").click(function() {

        $("#btn_selPoint").parent().removeClass("on");
        $("#btn_selPolygon").parent().removeClass("on");
        $("#btn_selRelease").parent().removeClass("on");
        $("#btn_selEnd").parent().addClass("on");

        parent.gMap.activeControls("drag");
        parent.$("#mCtrlPan").parent().addClass("active");
        if(fnCheckFeatures() < 0) {
        	alert("선택된 셀이 없습니다.");
        	return;
        }

        // 다중선택도구 닫기
        $('#dv_multiSelectTools').hide();

    });

    // 180718 wijy
    // [ STEP 2  ] 다중선택도구 버튼 event
    // 공통event
    $(".btn_ms_tools").on("click", function(e) {
        $(".btn_ms_tools").removeClass("on");
        $(this).addClass("on");
    });

    // 다각형 선택
    $("#btn_selectPoly").on("click", function(e) {
        fnResetMultiSelectTools();
        parent.gMap.activeControls("selPolygon");
        parent.$("#map .left_tool li").removeClass("active");
    });

    // 선형 선택
    $("#btn_selectline").on("click", function(e) {
        fnResetMultiSelectTools();
        parent.gMap.activeControls("selPath");
        parent.$("#map .left_tool li").removeClass("active");
    });

    // 직접입력버튼
    $("#btn_userInput").on("click", function(e) {
        alert("조회할 노선 시/종점을 지도에서 선택하세요.\r\n시/종점 조회는 동일노선, 동일방향, 동일차로 데이터만 조회 가능합니다.");
        fnResetMultiSelectTools();
        // 직접입력 검색도구 활성화
        $("#dv_userInput").show();
        // 180809 wijy
        // 지도 컨트롤 활성화
        parent.gMap.activeControls(["drag","selRouteControl"]);
    });

    fnSetUserInputRoute();

  //노선 selectbox
    $("#sel_ms_ui_route").on("change", function(e) {
        var sSelected = $("#sel_ms_ui_route option:selected").val();
        if(sSelected != "") {
            fnSetUserInputRouteDir(sSelected);
        }
    })

    //방향 selectbox
    $("#sel_ms_ui_dir").on("change", function(e) {
        var sRouteCode = $("#sel_ms_ui_route option:selected").val();
        var sRouteDir = $("#sel_ms_ui_dir option:selected").val();

        if(sRouteCode != "" && sRouteDir != "") {
            fnSetUserInputTrack(sRouteCode, sRouteDir);
        }
    });

    //직접입력 검색
    $("#btn_ms_ui_search").on("click", function(e) {
        var sRouteCode = $("#sel_ms_ui_route option:selected").val();
        var sRouteDir = $("#sel_ms_ui_dir option:selected").val();
        var sTrack = $("#sel_ms_ui_track option:selected").val();
        var sStrtpt = $("#ip_ms_ui_strtpt").val();
        var sEndpt = $("#ip_ms_ui_endpt").val();

        if(sStrtpt == '') sStrtpt = 0;
        if(sEndpt == '') sEndpt = (sStrtpt * 1) + 4000;

        if(((sEndpt * 1) - (sStrtpt * 1)) > 4000) {
            alert("검색 결과가 200개 이상입니다. 시/종점을 조정해 주십시오");
            return false;
        }

        MAP.fn_get_getFeatureAndDrawByAttr({
            map : parent.gMap,
            layerNm : "GAttrLayer",
            types : [["==", "==", "==", ">=", "<="]],
            tables : ["CELL_10"],
            fields : [["ROUTE_CODE", "DIRECT_CODE", "TRACK", "STRTPT", "ENDPT"]],
            values : [[sRouteCode, sRouteDir, sTrack, sStrtpt, sEndpt]],
            attribute : {
                attributes : {
                     fillColor : '#0033ff',
                     strokeColor : '#0033ff'
                }
            },
            clearBeforeDraw : true,
            callback : cb_ms_ui_search20Cells
        });
    });

    // 초기 로딩 시 default
    $("#btn_selStatusEvaluation").click();
    $("#btn_selPoint").click();

});

//다중선택도구 활성화
function fnSetMutiSelectTools() {
    //창 open
    $('#dv_multiSelectTools').css("display", "block");
    //다각형선택 기본선택
    $("#btn_selectPoly").trigger("click");
}

//다중선택도구 컨트롤 초기화
function fnResetMultiSelectTools() {
    fnClearMultiSelectUserInput();

    parent.gMap.cleanMap();
    parent.gMap.activeControls("drag");
    parent.$("#mCtrlPan").parent().addClass("active");
}

//직접입력창 노선검색 세팅
function fnSetUserInputRoute() {
    $.ajax({
        url : '<c:url value="/"/>' + 'api/ms/selRouteName.do',
        type : 'POST',
        dataType : 'json',
        success :cbSetUserInputRoute,
        error : function(a, b, c) {
            //error
        }
    });
}

function cbSetUserInputRoute(data) {
    var list = data.routeNm;
    if(list != null && list.length > 0) {
        var sHtml = '<option value="">노선 선택</option>';
        for(var i in list) {
            var oData = list[i];
            var code_val = oData.CODE_VAL || oData.code_val;
            var code_nm = oData.CODE_NM || oData.code_nm;
            
            sHtml += '<option value="' + fillLeft(code_val, 4) + '">' + '[' +  code_val + ']  ' + code_nm + '</option>';
        }
        $('#sel_ms_ui_route').html(sHtml);
    } else {
        //error
    }
    parent.MAP.CONTROL.ms_ui_listDef[0].resolve(data);
    return parent.MAP.CONTROL.ms_ui_listDef[0].promise();
}

//노선방향검색 세팅
function fnSetUserInputRouteDir(routeCode) {
	
    $.ajax({
        url : contextPath + '/api/ms/selRouteDirection.do',
        type : 'POST',
        dataType : 'json',
        data : {ROUTE_CODE : routeCode},
        success : cbSetUserInputRouteDir,
        error : function(a, b, c) {
        }
    });
}

function cbSetUserInputRouteDir(data) {
    var list = data.routeDir;
    if(list != null && list.length > 0) {
        var sHtml = '';
        for(var i in list) {
            var oData = list[i];
            sHtml += '<option value="' + oData.CODE + '">' + oData.VAL + '</option>';
        }
        $("#sel_ms_ui_dir").html(sHtml);
        $("#sel_ms_ui_dir option:eq(0)").trigger("change");
    } else {
        //error
    }

    parent.MAP.CONTROL.ms_ui_listDef[1].resolve(data);
    return parent.MAP.CONTROL.ms_ui_listDef[1].promise();
}

//차로검색 세팅
function fnSetUserInputTrack(routeCode, directionCode) {
    $.ajax({
        url : '<c:url value="/"/>' + 'api/ms/selRouteTrack.do',
        type : 'POST',
        dataType : 'json',
        data : {ROUTE_CODE : routeCode, DIRECT_CODE : directionCode},
        success : cbSetUserInputTrack,
        error : function(a, b, c) {
            //error
        }
    });
}

function cbSetUserInputTrack(data) {
    var list = data.track;
    if(list != null && list.length > 0) {
        var sHtml = '';
        for(var i in list) {
            var oData = list[i];
            sHtml += '<option value="' + oData.track + '">' + oData.track + '</option>';
        }
        $("#sel_ms_ui_track").html(sHtml);
        $("#sel_ms_ui_track option:eq(0)").trigger("change");
    } else {
        //error
    }
    parent.MAP.CONTROL.ms_ui_listDef[2].resolve(data);
    return parent.MAP.CONTROL.ms_ui_listDef[2].promise();
}

//노선검색 callback
function cb_ms_ui_search20Cells(data) {
    var oRes = data.result;
    if(oRes == null || oRes.code == 'NO_RESULT') {
        alert("결과가 없습니다. 검색값을 확인해 주십시오.");
    } else if (data.features.length > 0 && data.features[0].resultFeature.length > 0) {
    }
}

//직접입력창 초기화
function fnClearMultiSelectUserInput() {
    $("#dv_userInput").hide();
    $("#sel_ms_ui_route option:eq(0)").prop("selected", true);
    $("#sel_ms_ui_dir").html("<option value=''></option>");
    $("#sel_ms_ui_track").html("<option value=''></option>");
    $("#ip_ms_ui_strtpt").val("0");
    $("#ip_ms_ui_endpt").val("4000");
}

// ======================================== CHECK FEATURES ======================================== //
// [ STEP 2 ] 공통
function fnCheckFeatures() {
    parent.$("#dvMapLoading").hide();

    // Change Mouse Pointer
    parent.gMap.activeControls("drag");
    parent.$("#mCtrlPan").parent().addClass("active");

    // get Layer by features
    //181107 GAttrLayer 사용하도록 수정
    features = parent.gMap.getLayerByName('GAttrLayer').features;
    //features = parent.gMap.getLayerByName('GAttrLayerMulti').features;

    if ( features.length == 0 ) {
        return -1;

    } else {

        // 검색할 내용이 선택된 경우
        parent.gMap.activeControls("drag");
        parent.$("#mCtrlPan").parent().addClass("active");

        // set cell count
        option.cellCount = features.length;

        try {
            // [ STEP 2 ] 메뉴 별 함수
            if ( $("#btn_selRouteInfo").parent().hasClass("on") ) {

                fnShowStep2Data("btn_selRouteInfo", "divRouteInfo");
                fnStep3RouteInfo();
                fnResizePopup(560);
                $("#step3").css("display", "block");

            } else if ( $("#btn_selStatusEvaluation").parent().hasClass("on") ) {

                fnShowStep2Data("btn_selStatusEvaluation", "divStatusEvaluation");
                $("#btn_selPoint").parent().removeClass("on");
                $("#btn_selPolygon").parent().removeClass("on");
                $("#btn_selEnd").parent().addClass("on");

                fnStep3StatusEvaluation();
                fnResizePopup(700);
                $("#step3").css("display", "block");

            } else if ( $("#btn_selBridgeInfo").parent().hasClass("on") ) {
                fnShowStep2Data("btn_selBridgeInfo", "divBridgeInfo");
                fnStep3BridgeInfo();
                fnResizePopup(550);
                $("#step3").css("display", "block");

            }
        } catch (err) {
            alert (err);
            return;
        }
        return 1;
    }
}


//======================================== MENU FUNCTION : ROUTE INFO ======================================== //
// [ STEP 3 ] 노선정보
function fnStep3RouteInfo() {

    // 노선번호 조합
    var routeCode = "";

    // input cell Id from features
    for ( var i in features ) {
        if ( i != 0 ) {
            routeCode += ",";
        }
        //routeCode += features[i].data.ROUTE_CODE;
        var roadNo = features[i].data.ROAD_NO || features[i].data.road_no;
        routeCode += roadNo;
    }

    // 전송 데이터 조합
    var postData = $("#frm").cmSerializeObject();
    postData["ROAD_NOS"] = routeCode;

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({

        url: '<c:url value="/"/>'+'api/routeinfo/selectRouteInfoListRange.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: postData
        ,ignoreCase: true
        //,colNames:["ROAD_NO","노선","도로 등급","노선 번호","노선 명","시점 명","종점 명","총연장(km)","전산화</br>완료연장(km)","위치보기"]
        ,colNames:["ROAD_NO","노선","도로 등급","노선 번호","노선 명","시점 명","종점 명","총연장(km)","위치보기"]
        ,colModel:[
            {name:'ROAD_NO',index:'ROAD_NO', hidden: true}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', hidden: true}
            ,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:50, sortable:false, formatter: fnRoadGrade}
            ,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:50, sortable:false}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:100, sortable:false}
            ,{name:'ST_POINT',index:'ST_POINT', align:'left', width:120, sortable:false}
            ,{name:'ED_POINT',index:'ED_POINT', align:'left', width:120, sortable:false}
            ,{name:'ROAD_TOT_LEN_JYG_Y',index:'ROAD_TOT_LEN_JYG_Y', align:'center', width:80, sortable:false}
            //,{name:'ROAD_LEN_CMPT',index:'ROAD_LEN_CMPT', align:'center', width:80, sortable:false}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fnCreateBtnRoute}

           ]
        ,async : false
        ,sortname: 'ROAD_NO'
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,ondblClickRow: function(rowId) {

            // 더블클릭 처리 - 하단 팝업에 상세보기
            var rowRoadNo = $("#gridArea").getRowData(rowId).ROAD_NO;

            parent.bottomOpen();

            parent.$(".btmenuarea").find("div").hide();
            parent.$("#sub_route").show();
            parent.$("#sub_route").find("li:eq(0)").attr("class", "sel");

            parent.COMMON_UTIL.cmMenuUrlContent('routeinfo/selectRouteInfoList.do?DIRECT_FLAG=N&ROAD_NO=' + rowRoadNo, true);

        }
           ,onSelectRow: function(rowId) {
               // 클릭 처리
            if( rowId != null ) {
                var rowData =$("#gridArea").getRowData(rowId);
            }
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
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 206);

    fnSearchRoute(routeCode);

}

// [ STEP 3 ] 노선정보 검색
function fnSearchRoute(routeCode) {

    var postData = $("#frm").cmSerializeObject();
    postData["ROAD_NOS"] = routeCode;

    $("#gridArea").jqGrid("setGridParam", {

        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

            parent.$("#dvMapLoading").hide();

            // 전체 노선에 대하여 zoom
            var fArr = [];
            var vArr = [];

            for ( var i = 0 ; i < features.length ; i++ ) {
                fArr[i] = "ROAD_NO";
                vArr[i] = features[i].data.ROAD_NO || features[i].data.road_no;
            }

            var tables_base = ["DORO_TOT_GRS80_50"];
            var fields_base = [fArr];
            var values_base = [vArr];
            var attribute_base = {
                    attributes : {
                        fillColor : '#0033ff',
                        strokeColor : '#0033ff'
                    }
            };

            MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base);

            // 데이터 없는 경우
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

            // jqGrid Table 사이즈 조절
            $("#divRouteInfo").bind("resize", function() {

                $("#gridArea").setGridWidth(100, false);
                $("#gridArea").setGridWidth($("#divRouteInfo").width(), false);

            }).trigger("resize");

            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager_left").css('width', '');
        }
        , loadError : function(xhr, status, error) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, 1);
            $("#gridPager_left").find("div").html("총 <font color='#f42200'>0</font> 건 (1-1)");

            parent.$("#dvMapLoading").hide();

        }

    }).trigger("reloadGrid");

}

// [ STEP 3 ] 노선정보 위치이동 버튼생성
function fnCreateBtnRoute(cellValue, options, rowObject) {

    var btn = "";
    var nm = options.colModel.name;

    if ( nm == "btn_loc" ) {
        btn = "<a href='#' onclick=\"fnSelectRoute('" + rowObject.ROAD_NO + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
    return btn;
}


// [ STEP 3 ] 노선정보 위치이동
function fnSelectRoute(road_no) {

    // 하단 목록 창 내리기
    parent.bottomHide();

     // Base로 선택한 노선 보여줌
    /* var fArr = [];
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
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    }; */

    //MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base);

    // 선택한 노선을 보여줌
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [road_no];
    var attribute = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables, fields, values, null, "OR", attribute, false, 0, 1);

}

//======================================== MENU FUNCTION : STATUS EVALUATION ======================================== //
// [ STEP 3 ] 상태평가
function fnStep3StatusEvaluation() {

    // 노선번호 조합
    cellIds = "";

    // input cell Id from features
    for ( var i in features ) {
        var cell_id = features[i].data.CELL_ID || features[i].data.cell_id;
        if ( i != 0 ) {
            cellIds += ",";
        }
        cellIds += cell_id;
    }

    // 2018. 09. 13.
    $("#CELL_ID").val(cellIds);

    // 전송 데이터 조합
    var postData = $("#frm2").cmSerializeObject();
    postData["CELL_ID"] = cellIds;

    // 검색 목록 그리드 구성
    $("#gridArea2").jqGrid({

        url: '<c:url value="/"/>'+'api/mummsctnsrvydta/selectMummSctnSrvyDtaListByItgrtn.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: JSON.stringify(postData)
        ,ignoreCase: true
        ,colNames:["ROAD_NO","셀번호","도로 등급","노선 번호","노선 명","행선","차로","시점(km)","종점(km)","위치보기"]
        ,colModel:[
            {name:'ROAD_NO',index:'ROAD_NO', hidden: true}
            ,{name:'CELL_ID',index:'CELL_ID', hidden: true}
            ,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:80, sortable:false, formatter: fnRoadGrade}
            ,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:60, sortable:false}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:80, sortable:false}
            ,{name:'DIRECT_CODE',index:'DIRECT_CODE', align:'center', width:60, sortable:false, formatter: fnDirectName}
            ,{name:'TRACK',index:'TRACK', align:'center', width:60, sortable:false}
            ,{name:'STRTPT',index:'STRTPT', align:'center', width:80, sortable:false, formatter: fnConvertKm}
            ,{name:'ENDPT',index:'ENDPT', align:'center', width:80, sortable:false, formatter: fnConvertKm}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fnCreateBtnCell}
           ]
        ,async : false
        ,sortname: 'ROAD_NO_VAL'
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager2'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        /* ,emptyrecords: "" */
        ,emptyrecords: "선택한 구간에 평가정보가 없습니다. (조사자료 없음)"
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,ondblClickRow: function(rowId) { }
        ,onSelectRow: function(rowId) { }
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
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', 206);

    fnSearchStatus(cellIds);

}

// [ STEP 3 ] 상태평가 검색
function fnSearchStatus(cellIds) {

    var postData = $("#frm2").cmSerializeObject();
    postData["CELL_ID"] = cellIds;

    $("#gridArea2").jqGrid("setGridParam", {

        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, data.records);

            // jqGrid Table 사이즈 조절
            $("#divStatusEvaluation").bind("resize", function() {

                $("#gridArea2").setGridWidth(100, false);
                $("#gridArea2").setGridWidth($("#divStatusEvaluation").width(), false);

            }).trigger("resize");

            // 검색 데이터가 없는 경우 empty 메시지 출력하지 않고 0건 보이도록
            if ( data.rows.length == 0 ) {
            	$("#selmore").hide();
                $("#gridPager2_left").find("div").html("총 <font color='#f42200'>0</font> 건 (1-1)");
                return;
            }
            else{
            	$("#selmore").show();
            }
            //return; // 수정 2018.11.02 return 위치 수정...


            parent.$("#dvMapLoading").hide();

             var length = data.rows.length

             if ( length != 0 ) {

                 // 평균 데이터 바인딩
                 var avg = data.userData;

                 if ( avg != undefined && avg != "" ) {

                     // 주 파손, 파손원인 값 도출
                     if ( avg.AC_IDX == null || avg.BC_IDX == null || avg.LC_IDX == null || avg.TC_IDX == null
                             || avg.PTCHG_IDX == null || avg.POTHOLE_IDX == null || avg.RD_IDX == null || avg.RCI == null ) {

                         // null인 값이 있는 경우 초기값 그대로 표출
                         return;

                     }

                     // 주 파손
                     var crVal = "";

                     if ( avg.CNTL_DFECT != "DFCT0009" ) {
                         var codeNm = avg.CODE_NM;

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

                         var maxVal = Math.max( avg.AC_IDX, avg.BC_IDX, avg.LC_IDX, avg.PTCHG_IDX, avg.POTHOLE_IDX, avg.RD_IDX, avg.RCI );
                         var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
                         var valArr = [ avg.AC_IDX, avg.BC_IDX, avg.LC_IDX, avg.PTCHG_IDX, avg.POTHOLE_IDX, avg.RD_IDX, avg.RCI ];

                         if ( maxVal == 0 ) {

                             // max 값이 0인 경우는 파손없음
                             crVal += "파손없음";

                         } else {

                             crVal += "<br />복합파손 <br /> (";
                             var codeNames = [];

                             for ( var i = 0; i < valArr.length; i++ ) {
                                 // max값과 같은 경우 텍스트 추가
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

                             $("#crVal").css({"line-height": "15px", "font-size": "19px"});
                         }
                     }

                     // 파손원인
                     var clmt = avg.DMG_CUZ_CLMT;
                     var vmtc = avg.DMG_CUZ_VMTC;
                     var etc = avg.DMG_CUZ_ETC;
                     var cuz = "";

                     if ( clmt == vmtc && vmtc == etc ) {
                         if ( etc == 0 ) {
                             cuz = "파손없음";

                         } else if ( etc != 0 ) {
                             cuz = "<br/>교통량/하부불량,<br/> 기후, 기타";
                             $("#dmgCuz").css({"line-height": "15px", "font-size": "19px"});
                         }

                     } else if ( clmt > vmtc && clmt > etc ) {
                         cuz = "기후";

                     } else if ( vmtc > clmt && vmtc > etc ) {
                         cuz = "교통량/하부불량";
                         $("#dmgCuz").css({"font-size": "21px"});

                     } else if ( etc > clmt && etc > vmtc ) {
                         cuz = "기타";

                     } else if ( clmt == vmtc && clmt > etc ) {
                         cuz = "<br />교통량/하부불량,<br />기후";
                         $("#dmgCuz").css({"line-height": "15px", "font-size": "19px"});

                     } else if ( clmt == etc && clmt > vmtc ) {
                         cuz = "기후, 기타";

                     } else if ( vmtc == etc && vmtc > clmt ) {
                         cuz = "<br />교통량/하부불량,<br />기타";
                         $("#dmgCuz").css({"line-height": "15px", "font-size": "19px"});

                     } else {
                         cuz = "";
                     }

                     // 평가정보
                     $("#gpci").text( parseFloat(avg.GPCI).toFixed(2) );
                     $("#crVal").html(crVal);
                     $("#dmgCuz").html(cuz);
                }
             }
         }
        , loadError : function(xhr, status, error) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, 1);
            $("#gridPager2_left").find("div").html("총 <font color='#f42200'>0</font> 건 (1-1)");
            $("#selmore").hide();

            parent.$("#dvMapLoading").hide();
        }
  }).trigger("reloadGrid");

  //페이지 box가 중간에 오도록 css로 해결
  $("#gridPager2_left").css('width', '');
  $("#gridPager2_Center").css('width', '100%');

}

// [ STEP 3 ] 상태평가 위치이동 버튼생성
function fnCreateBtnCell(cellValue, options, rowObject) {

    var btn = "";
    var nm = options.colModel.name;
    var cell_id = rowObject.CELL_ID || rowObject.cell_id;
    if ( nm == "btn_loc" ) {
        btn = "<a href='#' onclick=\"fnSelectCell('" + cell_id + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
    return btn;
}

// [ STEP 3 ] 상태평가 위치이동
function fnSelectCell(cell_id) {

    // 하단 목록 창 내리기
    parent.bottomHide();

     // Base로 선택한 노선 보여줌
    var fArr = [];
    var vArr = [];

    for ( var i = 0 ; i < features.length ; i++ ) {
        fArr[i] = "CELL_ID";
        vArr[i] = features[i].data.CELL_ID || features[i].data.cell_id;
    }

    var tables_base = ["CELL_10"];
    var fields_base = [fArr];
    var values_base = [vArr];
    var attribute_base = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base, false, 1, 0);

    // 선택한 셀을 보여줌
    var tables = ["CELL_10"];
    var fields = ["CELL_ID"];
    var values = [cell_id];
    var attribute = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

}


//[ STEP 3 ] 상태평가 상세정보조회
function fnSelectMore() {
	parent.bottomOpen();
    parent.$("#btab01").hide();
    parent.$(".btmenuarea").find("div").hide();
    var bottomtab = parent.$("#detail_integrated");
    bottomtab.show();
    bottomtab.find("li").eq(0).attr("class", "sel");

    cmMenuUrlMummSctnSrvyDta('mng/mummsctnsrvydta/mummSctnSrvyDtaList.do?CELL_ID=' + cellIds, true);
}

/*
function fnSelectMore() {
    parent.bottomOpen();

    parent.$(".btmenuarea").find("div").hide();
    parent.$("#sub_srvyEvl").show();
    parent.$("#sub_srvyEvl").find("li:eq(1)").attr("class", "sel");

    parent.COMMON_UTIL.cmMenuUrlContent('mng/mummsctnsrvydta/mummSctnSrvyDtaList.do?CELL_ID=' + cellIds, true);
}

function fnSelectMore() {
	parent.bottomOpen();
	parent.$("#btab01").hide();
	parent.$(".btmenuarea").find("div").hide();
	var bottomtab = parent.$("#detail_integrated");
	bottomtab.show();
	//parent.$("#sub_srvyEvl").show();
	var $lis = bottomtab.find("li");
	$lis.removeClass("sel");
	$lis.eq(0).attr("class", "sel");

	$lis.off("click");
	$lis.eq(0).on("click",function(){
	    $lis.removeClass("sel");
	    $(this).addClass("sel");
	    cmMenuUrlMummSctnSrvyDta('srvy/selectSrvyDtaEvlInfoList.do?CELL_ID=' + cellIds, true);
	});

	$lis.eq(1).on("click",function(){
	    $lis.removeClass("sel");
	    $(this).addClass("sel");
	    cmMenuUrlMummSctnSrvyDta('srvy/selectSrvyDtaEvlList.do?CELL_ID=' + cellIds, true);
	});

	cmMenuUrlMummSctnSrvyDta('mng/mummsctnsrvydta/mummSctnSrvyDtaList.do?CELL_ID=' + cellIds, true);
}
 */

//======================================== MENU FUNCTION : BRIDGE INFO ======================================== //
// [ STEP 3 ] 교량정보
function fnStep3BridgeInfo() {

    // 교량번호 조합
    var brdgSeq = "";

    // input Bridge Id from features
    for ( var i in features ) {

        if ( i != 0 ) {

            brdgSeq += ",";

        }

        brdgSeq += features[i].data.BRDG_SEQ || features[i].data.brdg_seq;

    }

    // 전송 데이터 조합
    var postData = $("#frm3").cmSerializeObject();
    postData["BRDG_SEQ"] = brdgSeq;

    // 검색 목록 그리드 구성
    $("#gridArea3").jqGrid({

        url: '<c:url value="/"/>'+'api/selectMCalsTList.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: postData
        ,ignoreCase: true
        ,colNames:["BMS코드","노선명","관할구분","법정동명","교량명","교량유형","경간번호","바닥판포장명","바닥판<br/>포장두께(cm)","위치보기"]
           ,colModel:[
               {name:'BRDG_SEQ',index:'BRDG_SEQ', hidden: true}
               ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:50, sortable:false}
               ,{name:'TYPE_NAME',index:'TYPE_NAME', align:'center', width:60, sortable:false}
               ,{name:'BJNAME',index:'BJNAME', align:'center', width:150, sortable:false}
               ,{name:'BRDG_NAME',index:'BRDG_NAME', align:'center', width:60, sortable:false}
               ,{name:'BRDG_TYPENM',index:'BRDG_TYPENM', align:'center', width:60, sortable:false}
                ,{name:'SPAN_NUM',index:'SPAN_NUM', align:'center', width:60, sortable:false}
                ,{name:'DECK_PAVTY_NAME',index:'DECK_PAVTY_NAME', align:'center', width:60, sortable:false}
                ,{name:'DECK_PAV',index:'DECK_PAV', align:'center', width:60, sortable:false}
                ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fnCreateBtnBrdg}
           ]
        ,async : false
        ,sortname: 'SPAN_NUM'
        ,sortorder: "asc"
        ,rowNum: 10
        ,rowList: [10,20,50,100]
        ,viewrecords: true
        ,pager: '#gridPager3'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,shrinkToFit : false
        ,ondblClickRow: function(rowId) {
        }
        ,onSelectRow: function(rowId) {
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
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager3',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea3','div_grid3', 206);

    fnSearchCals(brdgSeq);

    /* $(window).resize(function () {
        fnSetGridWith();
    }); */
}

// [ STEP 3 ] 교량정보 검색
function fnSearchCals(brdgSeq) {

    var postData = $("#frm3").cmSerializeObject();
    postData["BRDG_SEQ"] = brdgSeq;

    $("#gridArea3").jqGrid("setGridParam", {

        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

            parent.$("#dvMapLoading").hide();

            //총 건수를 목록 상단에 표시
            $('#count').text('검색 결과 :'+$("#gridArea3").getGridParam("records")+'건');
            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager3_left").css('width', '');

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea3', $("#gridArea3").jqGrid("getGridParam").emptyrecords, data.records);
        }
        , loadError : function(xhr, status, error) {

            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea3', $("#gridArea3").jqGrid("getGridParam").emptyrecords, 1);
            $("#gridPager3_left").find("div").html("총 <font color='#f42200'>0</font> 건 (1-1)");
            $("#selmore").hide();

            parent.$("#dvMapLoading").hide();

        }
    }).trigger("reloadGrid");

}

// [ STEP 3 ] 교량정보 위치이동 버튼생성
function fnCreateBtnBrdg(cellValue, options, rowObject) {

    var btn = "";
    var nm = options.colModel.name;

    if ( nm == "btn_loc" ) {

        btn = "<a href='#' onclick=\"fnSelectBrdg('" + rowObject.BRDG_SEQ + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";

    }

    return btn;

}

// [ STEP 3 ] 교량정보 위치이동
function fnSelectBrdg(brdgSeq) {

    // 하단 목록 창 내리기
    parent.bottomHide();

    // Base로 선택한 교량 보여줌
    var fArr = [];
    var vArr = [];

    for ( var i = 0; i < features.length; i++ ) {

        fArr[i] = "BRDG_SEQ";
        vArr[i] = features[i].data.BRDG_SEQ || features[i].data.brdg_seq;

    }

    var tables_base = ["M_CALS_T"];
    var fields_base = [fArr];
    var values_base = [vArr];
    var attribute_base = {
            attributes : {
                fillColor : '#0033ff',
                strokeColor : '#0033ff'
            }
    };

    MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base, false, 1, 0);

    // 선택한 셀을 보여줌
    var tables = ["M_CALS_T"];
    var fields = ["BRDG_SEQ"];
    var values = [brdgSeq];
    var attribute = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };

    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

}

//======================================== BUTTON ON CHECK ======================================== //
//[ STEP 1 ] STEP 1 on 체크
function checkStep1() {

    if ( !$("#btn_selRouteInfo").parent().hasClass("on")
            && !$("#btn_selStatusEvaluation").parent().hasClass("on")
            && !$("#btn_selBridgeInfo").parent().hasClass("on") ) {

        alert("1단계를 먼저 선택해주세요.");
        return -1;

    }

    return 1;

}

//[ STEP 1 ] 방법 변경
function fnChangeMethod(id) {

    if ( id == "btn_selStatusEvaluation" ) {

        $("#step2_sel li:eq(0)").css("width", "24%");
        $("#step2_sel li:eq(1)").css("width", "25%");
        $("#step2_sel li:eq(1)").css("border-left", "0px");
        $("#step2_sel li:eq(1)").addClass("brl");
        $("#step2_sel li:eq(2)").css("display", "block");
        $("#step2_sel li:eq(3)").css("display", "block");
        $("#btn_selPolygon").css("display", "block");
        $("#btn_selRelease").css("display", "block");
        $("#btn_selEnd").css("display", "block");
        $(".statusmode").css("display", "block");
        $(".normalmode").css("display", "none");

        $("#btnResearchDetail").css("display", "none");

    } else {

        $("#step2_sel li:eq(0)").css("width", "50%");
        $("#step2_sel li:eq(1)").css("width", "48%");
        $("#step2_sel li:eq(1)").css("border-left", "");
        $("#step2_sel li:eq(1)").removeClass("brl");
        $("#step2_sel li:eq(2)").css("display", "none");
        $("#step2_sel li:eq(3)").css("display", "none");
        $("#btn_selPolygon").css("display", "block");
        $("#btn_selRelease").css("display", "block");
        $(".statusmode").css("display", "none");
        $(".normalmode").css("display", "block");

        $("#btnResearchDetail").css("display", "none");

    }

}

// ======================================== CLEAR FUNCTION ======================================== //
// [ STEP 1 ] 초기화
function fnClearStep1Data() {

    // initial state
    parent.gMap.cleanMap();
    parent.gMap.activeControls("drag");
    parent.$("#mCtrlPan").parent().addClass("active");
    parent.bottomHide();

    // STEP 1 초기화
    $("#btn_selRouteInfo").parent().removeClass("on");
    $("#btn_selStatusEvaluation").parent().removeClass("on");
    $("#btn_selBridgeInfo").parent().removeClass("on");

    // STEP 2 초기화
    $("#btn_selPoint").parent().removeClass("on");
    $("#btn_selPolygon").parent().removeClass("on");
    $("#btn_selRelease").parent().removeClass("on");
    $("#btn_selEnd").parent().removeClass("on");

    // STEP 3 초기화
    $("#step3").css("display", "none");
    $("#divRouteInfo").css("display", "none");
    $("#divStatusEvaluation").css("display", "none");
    $("#divBridgeInfo").css("display", "none");

    // STEP 3 영역 초기화
    $("#div_grid1").html(grid1);
    $("#div_grid2").html(grid2);
    $("#gpci").html("0");
    $("#crVal").html("없음");
    $("#dmgCuz").html("없음"); // 상태평가 초기값
    $("#divBridgeInfo").html(brdgHtml);

    // POPUP 초기화
    fnResizePopup(215);

}

// [ STEP 2 ] 초기화
function fnClearStep2Data() {

    parent.bottomHide();
	$('#dv_multiSelectTools').hide();

    // STEP 2 초기화
    $("#btn_selPoint").parent().removeClass("on");
    $("#btn_selPolygon").parent().removeClass("on");
    $("#btn_selRelease").parent().removeClass("on");
    $("#btn_selEnd").parent().removeClass("on");
    
    // STEP 3 초기화
    $("#step3").css("display", "none");
    $("#divRouteInfo").css("display", "none");
    $("#divStatusEvaluation").css("display", "none");
    $("#divBridgeInfo").css("display", "none");

    // STEP 3 영역 초기화
    $("#div_grid1").html(grid1);
    $("#div_grid2").html(grid2);
    $("#gpci").html("0");
    $("#crVal").html("없음");
    $("#dmgCuz").html("없음"); // 상태평가 초기값
    $("#divBridgeInfo").html(brdgHtml);

    // POPUP 초기화
    fnResizePopup(215);

}

// [ STEP 3 ] 데이터 보여주기
function fnShowStep2Data(btnId, divId) {

    parent.bottomHide();

    // STEP 3 초기화
    $("#divRouteInfo").css("display", "none");
    $("#divStatusEvaluation").css("display", "none");
    $("#divBridgeInfo").css("display", "none");

    // STEP 3 영역 초기화
    $("#div_grid1").html(grid1);
    $("#div_grid2").html(grid2);
    $("#gpci").html("0");
    $("#crVal").html("없음");
    $("#dmgCuz").html("없음"); // 상태평가 초기값
    $("#divBridgeInfo").html(brdgHtml);

    // show
    $("#" + btnId).parent().addClass("on");
    $("#" + divId).css("display", "block");

    // toggle 버튼이 닫혀있는 경우 열어줌
    if ( !$("#toggle").hasClass("on") ) {

        $("#toggle").click();

    }

}

//======================================== COMMON FUNCTION ======================================== //
// [ COMMON ] 팝업 사이즈 변경
function fnResizePopup(h) {

    // change popup size
    var wndId = $("#wnd_id").val();
    parent.$("#" + wndId).animate({ height : h }, 500);
    parent.$("#" + wndId).find("iframe").animate({ height : h-30 }, 400);

}

// [ COMMON ] 도로등급 표시
function fnRoadGrade(cellValue, options, rowObject) {

    var nm = options.colModel.name;

    if ( nm == "ROAD_GRAD" ) {

        if ( rowObject.ROAD_GRAD == "RDGD0001" ) {

            return "국지도";

        } else if ( rowObject.ROAD_GRAD == "RDGD0002" ) {

            return "지방도";

        }

    }

    return "";

}

// [ COMMON ] 상/하행 표시
function fnDirectName(cellValue, options, rowObject) {

    var nm = options.colModel.name;

    if (nm == "DIRECT_CODE") {

        if(rowObject.DIRECT_CODE == "S"){
            return "상행";
        }
        else if(rowObject.DIRECT_CODE == "E"){
            return  "하행";
        }
        else if(rowObject.DIRECT_CODE == "SE"){
            return  "양방향";
        }

    }

}

// [ COMMON ] 시점/종점 km로 변환
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

// [ COMMON ] 날짜 형식 변환
function fnChangeDate(cellValue, options, rowObject) {

    var date = "";
    var nm = options.colModel.name;

    if ( nm == "SRVY_DE" ) {

        var dstr = rowObject.SRVY_DE;

        var year = dstr.substr(0, 4);
        var month = dstr.substr(4, 2);

        date = year + "-" + month;

    }

    return date;

}

function fillLeft(value, n) {

    var len = value.length;
    var max = parseInt(n);

    if ( len > max ) {

        return value;

    } else {

        var result = "";

        for ( var i = 0; i < max - len; i++ ) {

            result += "0";
        }

        return result + value;

    }

}

var cmMenuUrlMummSctnSrvyDta = function(_oUrl,_oClearFlag){
    COMMON_UTIL.fn_check_session()

    if (_oClearFlag == undefined || _oClearFlag == false) {
        if(gMap != undefined && gMap != null){
            gMap.cleanMap();
        }
    }

    if(contextPath.endsWith("/")){
        if(_oUrl.startsWith("/")){
            _oUrl = contextPath + _oUrl.substring(1);
        }
        else
            _oUrl = contextPath + _oUrl;
    }
    else{
        if(_oUrl.startsWith("/")){
            _oUrl = contextPath+_oUrl;
        }
        else
            _oUrl = contextPath+"/"+_oUrl;
    }

    var strUrl = "";
    if(_oUrl.indexOf("?") > -1){
        strUrl = _oUrl.substring(0, _oUrl.indexOf("?"));
    }else{
        strUrl = _oUrl;
    }

    $.ajax({
        url: contextPath + 'userauth/checkAuth.do'
        ,type: 'post'
        ,dataType: 'json'
        ,data : {"url" : strUrl}
        ,success: function(res){
            if(!res.result){
                alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                return;
            }

            parent.$("#integrated_area").attr("src", _oUrl);

        }
        ,error: function(a,b,msg){

        }
    });
};


function fnSaveExcel() {
    if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
        COMMON_UTIL.cmFormSubmit("frm2", "proc_frm", "<c:url value='/api/mumm/integratedListExcel.do'/>", "");
    }
}
</script>
</body>
</html>