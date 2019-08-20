<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>경기도로 모니터링단 시스템</title>

<%-- 공통 스크립트 --%>
<%@ include file="/include/common_head.jsp"%>

<!-- JSTREE -->
<script src="<c:url value='/extLib/jstree/jstree.min.js'/>" charset="utf-8"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>

<%
    String flag = "good";
    session.setAttribute("sFlag", flag);
%>
</head>

<body id="goodMap">

    <div id="wrap">
        <!-- 공통 (START)-->
        <%@ include file="/include/topMenu.jsp"%>
        <!-- 공통 (END)-->
        <!-- container start -->
        <div id="container">
            <h2 class="hidden">본문컨텐츠-지도</h2>
            <h2 class="hidden">메인메뉴</h2>

            <div id="bottom">
                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseBt" onclick="bottomClose()"><img
                            src="images/btn_pleftclose.png" alt="닫기" title="닫기" /></a> <a
                            href="#" class="hidden" id="leftOpenBt" onclick="bottomOpen()"><img
                            src="images/btn_pleftopen.png" alt="열기" title="열기" /></a>
                    </div>
                    <div id="btab01">
                        <%-- 왼쪽 세부 메뉴 --%>
                        <%@ include file="/include/leftMenu.jsp"%>
                        <div>
                            <div class="tab_wrap ">
                                <iframe id="content_area" name="content_area"
                                    style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border: 0; width: 100%; height: 560px; overflow: hidden; z-index: 9999;"
                                    scrolling=no></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 통계 DIV -->
            <div id="good_stats" class="hidden">
                <div id="bottom_cont">
                    <div class="bottomBtArea">
                        <a href="#" id="leftCloseSt" onclick="goodStatsClose()"><img
                            src="images/btn_pleftclose.png" alt="닫기" title="닫기" /></a> <a
                            href="#" class="hidden" id="leftOpenSt" onclick="goodStatsOpen()"><img
                            src="images/btn_pleftopen.png" alt="열기" title="열기" /></a>
                    </div>
                    <div id="btab01">
                        <div>
                            <div class="tab_wrap ">
                                <iframe id="content_stArea" name="content_stArea"
                                    style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; border: 0; width: 100%; height: 915px; overflow: hidden; z-index: 998;"
                                    scrolling=no></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 지도기능 -->
            <div id="daumMap"></div>
            <div id="map">
                <ul id="" class="left_tool" style="top: 75px">
                    <li><a href="#" class="t1 maptool" id="mCtrlLocSearch"
                        title="위치 통합검색"><span class="hidden">위치통합검색</span></a></li>
                    <li><a href="#" class="t2 maptool" id="mCtrlLonLatMove"
                        title="경위도 좌표 이동"><span class="hidden">경위도 좌표 이동</span></a></li>
                    <li class="active"><a href="#" class="t3 maptool"
                        id="mCtrlPan" title="이동"><span class="hidden">이동</span></a></li>
                    <li><a href="#" class="t4 maptool" id="mCtrlZoomIn" title="확대"><span
                            class="hidden">확대</span></a></li>
                    <li><a href="#" class="t5 maptool" id="mCtrlZoomOut"
                        title="축소"><span class="hidden">축소</span></a></li>
                    <li><a href="#" class="t6 maptool" id="mCtrlPrev" title="이전"><span
                            class="hidden">이전</span></a></li>
                    <li><a href="#" class="t7 maptool" id="mCtrlNext" title="다음"><span
                            class="hidden">다음</span></a></li>
                    <li><a href="#" class="t8 maptool" id="mCtrlFullExt"
                        title="전체"><span class="hidden">전체</span></a></li>
                    <li><a href="#" class="t9 maptool" id="mCtrlMesureLength"
                        title="거리"><span class="hidden">거리</span></a></li>
                    <li><a href="#" class="t10 maptool" id="mCtrlMesureArea"
                        title="면적"><span class="hidden">면적</span></a></li>
                    <li><a href="#" class="t11 maptool" id="mCtrlClear"
                        title="초기화"><span class="hidden">초기화</span></a></li>
                    <li><a href="#" class="t12 maptool" id="mCtrlMapPrint"
                        title="현재화면 인쇄"><span class="hidden">인쇄</span></a></li>
                    <li><a href="#" class="t13 maptool" id="mCtrlPrint"
                        title="현재화면 저장"><span class="hidden">저장</span></a></li>
                </ul>

                <!-- 2018. 02. 14. JOY. 신고정보 현황 조회 -->
                <ul class="top-tool period">
                    <li class="tab-title"><span style="line-height: 35px;">기간</span></li>
                    <li>
                        <ul class="innerPeriod">
                            <li style="position: relative; top: -5px;">

                                <form id="pth-frm" name="pth-frm" method="post" action="">
                                    <!-- 포트홀 신고관리 메뉴의 검색조건 setting START -->
                                    <input type="hidden"    id="PTH_RG_NO"         name="PTH_RG_NO"         value="" />
                                    <input type="hidden"    id="BSNM_NM"           name="BSNM_NM"           value="" />
                                    <input type="hidden"    id="VHCLE_NO"          name="VHCLE_NO"          value="" />
                                    <input type="hidden"    id="DEPT_CODE"         name="DEPT_CODE"         value="" />
                                    <input type="hidden"    id="USER_DEPT_CODE"    name="USER_DEPT_CODE"    value="" />
                                    <input type="hidden"    id="PRCS_STTUS"        name="PRCS_STTUS"        value="" />
                                    <input type="hidden"    id="DMG_TYPE"          name="DMG_TYPE"          value="" />
                                    <input type="hidden"    id="RPAIR_DT_START"    name="RPAIR_DT_START"    value="" />
                                    <input type="hidden"    id="RPAIR_DT_END"      name="RPAIR_DT_END"      value="" />
                                    <input type="hidden"    id="PTH_RG_NO_LAYER"   name="PTH_RG_NO_LAYER"   value="" />
                                    <input type="hidden"    id="MGG_ID"            name="MGG_ID"            value="" />
                                    <input type="hidden"    id="MPTH_RG_NO"        name="MPTH_RG_NO"        value="" />
                                    <input type="hidden"    id="MRPRDTLS_MNG_NO"   name="MRPRDTLS_MNG_NO"   value="" />
                                    <input type="hidden"    id="pthmode"           name="pthmode"           value="" />

                                    <!-- 포트홀 신고관리 메뉴의 검색조건 setting END -->

                                    <input type="text" id="STTEMNT_DT_START" name="STTEMNT_DT_START" onkeydown="fnCheckEnter(event);" />
                                    <span style="left: 2px; top: 6px; position: relative;">~</span>
                                    <br />
                                    <input type="text" id="STTEMNT_DT_END" name="STTEMNT_DT_END" onkeydown="fnCheckEnter(event);" />
                                </form>

                            </li>

                            <li style="line-height: 25px; margin:0;">
                                <a href="#" onclick="fnSelectLayer();" class="schbtn">조회</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <ul class="top-tool total">

                    <!-- 전체건수 -->
                    <li class="mr5 ml3 total-count total-status" style="text-align: center; width: 55px;"><span class="lh16" >총 신고</span><br /><span id="status-count"></span>건</li>
                    <li class="mr5 ml3 total-count total-type" style="text-align: center; width: 55px; display: none;"><span class="lh16" >총 보수</span><br /><span id="type-count"></span>건</li>

                    <!-- 포트홀 신고 현황 -->
                    <li class="status-tab tab-title on" id="status" style="width: 64px;"><span class="lh16">포트홀<br />신고현황</span></li>
                    <li style="display: block;" class="innerStatus" id="sttemnt_tool">
                        <ul>
                            <li class="area">
                                <span class="sttCircle PRCS0001 <c:if test='${sessionScope.userinfo.REQ_USER_GRP == "ROLE_ADMIN"}'>bgchk</c:if>" id="sttemnt1" style="background-color: rgba(192, 0, 0, 1);"></span>
                                <span class="top-title">신고</span>
                                <span class="top-context"><span id="PRCS_STTUS1"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="sttCircle PRCS0002 bgchk" id="sttemnt2" style="background-color: rgba(255, 192, 0, 1)"></span>
                                <span class="top-title">접수</span>
                                <span class="top-context"><span id="PRCS_STTUS2"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="sttCircle PRCS0003" id="sttemnt3" style="background-color: rgba(146, 208, 80, 1);"></span>
                                <span class="top-title">처리완료</span>
                                <span class="top-context"><span id="PRCS_STTUS3"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="sttCircle PRCS0004" id="sttemnt4" style="background-color: rgba(75, 172, 198, 1)"></span>
                                <span class="top-title">보수예정</span>
                                <span class="top-context"><span id="PRCS_STTUS4"></span>건</span>
                            </li>

                        </ul>

                        <ul style="top: -5px;">

                            <li class="area">
                                <span class="sttCircle PRCS0005" id="sttemnt5" style="background-color: rgba(128, 100, 162, 1)"></span>
                                <span class="top-title">재배정요청</span>
                                <span class="top-context"><span id="PRCS_STTUS5"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="sttCircle PRCS0006" id="sttemnt6" style="background-color: rgba(243, 129, 229, 1)"></span>
                                <span class="top-title">기타</span>
                                <span class="top-context"><span id="PRCS_STTUS6"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="top-title" >&nbsp;-&nbsp; &nbsp;좌표오류</span>
                                <span class="top-context"><span id="PRCS_STTUS7"></span>건</span>
                            </li>

                        </ul>
                    </li>

                    <!-- 파손유형 별 신고 현황 -->
                    <li class="status-tab tab-title" id="dmg" style="margin-left: 5px;"><span class="lh16">파손유형별<br />신고현황</span></li>
                    <li style="display: none;" class="innerType" id="dmgt_tool">
                        <ul>

                            <li class="area" style="width:155px;">
                                <span class="dmgCircle DMGT0001" id="dmgtype1" style="background-color: rgba(192, 0, 0, 1);"></span>
                                <span class="top-title">다짐부족</span>
                                <span class="top-context"><span id="DMG_TYPE1"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="dmgCircle DMGT0003" id="dmgtype3" style="background-color: rgba(146, 208, 80, 1);"></span>
                                <span class="top-title">기층파손</span>
                                <span class="top-context"><span id="DMG_TYPE3"></span>건</span>
                            </li>

                            <li class="area" style="width: 143px">
                                <span class="dmgCircle DMGT0006" id="dmgtype6" style="background-color: rgba(243, 129, 229, 1)"></span>
                                <span class="top-title">아스팔트박리</span>
                                <span class="top-context"><span id="DMG_TYPE6"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="dmgCircle DMGT0005" id="dmgtype5" style="background-color: rgba(128, 100, 162, 1)"></span>
                                <span class="top-title">국부적결함</span>
                                <span class="top-context"><span id="DMG_TYPE5"></span>건</span>
                            </li>

                        </ul>

                        <ul style="top: -5px;">

                            <li class="area" style="width:155px;">
                                <span class="dmgCircle DMGT0002" id="dmgtype2" style="background-color: rgba(255, 192, 0, 1)"></span>
                                <span class="top-title">포트홀 외 신고건</span>
                                <span class="top-context"><span id="DMG_TYPE2"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="dmgCircle DMGT0004" id="dmgtype4" style="background-color: rgba(75, 172, 198, 1)"></span>
                                <span class="top-title">집중호우</span>
                                <span class="top-context"><span id="DMG_TYPE4"></span>건</span>
                            </li>

                            <li class="area" style="width: 143px">
                                <span class="dmgCircle DMGT0007" id="dmgtype7" style="background-color: rgba(254, 153, 153, 1)"></span>
                                <span class="top-title">지하매설물하자</span>
                                <span class="top-context"><span id="DMG_TYPE7"></span>건</span>
                            </li>

                            <li class="area">
                                <span class="dmgCircle DMGT0008" id="dmgtype8" style="background-color: rgba(93, 93, 93, 1)"></span>
                                <span class="top-title">파손없음</span>
                                <span class="top-context"><span id="DMG_TYPE8"></span>건</span>
                            </li>

                        </ul>
                    </li>

                    <!--
                    //신고관리탭
                    <li id="dmgt_tool" class="dv_pothole_regi">
                        <ul style="top: 2px; position: absolute;">
                            <li class="area" style="margin:0;">
                                <span class="dmgCircle DMGT0001" id="dmgtype1" style="background-color: rgba(192, 0, 0, 1);"></span>
                                <span class="top-title" style="margin-right:0">스마트카드</span>
                            </li>
                        </ul>

                        <ul style="top: 21px;position: absolute;">
                            <li class="area" style="margin:0;">
                                <span class="dmgCircle DMGT0002" id="dmgtype2" style="background-color: rgba(255, 192, 0, 1)"></span>
                                <span class="top-title" style="margin-right:0">국토부신고</span>
                            </li>
                        </ul>
                    </li> -->
                </ul>

                <ul class="top-tool route">
                        <li style="line-height: 17px;">
                            <span style="position: absolute;top: 3px;left: 76px;">포트홀 신고 정보</span>
                        </li>
                        <ul class="innerRoute" style="height: 10.5px;top: 10px;">
                       				<li class="area" style="width: 48px;position: absolute;top: -1px;left:-1px;">
                        				<!-- <input class="prrtCircle A bgchk" type="checkbox" id="pthmode0" checked> -->
                                        <span class="prrtCircle A" id="pthmode0"><span id="bgcircle1" style="font-size: 11.5px;color: white;position: relative;left: -1px;top: -8px;">●</span></span>
                                        <span class="top-title" style="margin-right:7px;">전체</span>
                                    </li>
                                    <li class="area" style="width: 48px;position: absolute;top: -1px;left:42px;">
                                    	<!-- <input class="prrtCircle T bgchk" type="checkbox" id="pthmode1" checked> -->
                                        <span class="prrtCircle T" id="pthmode1"><span id="bgcircle2" style="font-size: 11.5px;color: white;position: relative;left: -1px;top: -8px;">●</span></span>
                                        <span class="top-title" style="margin-right:7px;">택시</span>
                                    </li>
                                    <li class="area" style="width: 80px;position: absolute;top: -1px;left:84px;">
                                    	<!-- <input class="prrtCircle C bgchk" type="checkbox" id="pthmode2" checked> -->
                                        <span class="prrtCircle C" id="pthmode2"><span id="bgcircle3" style="font-size: 11.5px;color: white;position: relative;left: -1px;top: -8px;">●</span></span>
                                        <span class="top-title" style="margin-right:7px;">31개 시·군</span>
                                    </li>
                                    <li class="area" style="width: 61px;position: absolute;top: -1px;left:158px;">
                                    	<!-- <input class="prrtCircle M bgchk" type="checkbox" id="pthmode3" checked> -->
                                        <span class="prrtCircle M" id="pthmode3"><span id="bgcircle4" style="font-size: 11.5px;color: white;position: relative;left: -1px;top: -8px;">●</span></span>
                                        <span class="top-title" style="margin:0;">국토부앱</span>
                                    </li>
                        </ul>
                        <li style="line-height: 17px;">
                            <span style="position: absolute;top: 38px;left: 32px;font-size: 4px;font-weight: 100;">19년00월부터 시군,국토부앱 조회가능</span>
                        </li>
                </ul>

                <!-- <ul class="right_tool" style="top: 75px;">

                    //<li><a id="sttemntMap" href="#" class="t1 selecttool"
                    //    title="신고정보 공간검색"
                    //    onclick="COMMON_UTIL.cmWindowOpen('신고정보 공간검색', contextPath + 'pothole/map/selectSttemntMapView.do', 340, 290, false, null, 'sttemnt');"><span
                    //        class="hidden">신고정보 공간검색</span></a></li>

                    <li>
                        <a id="thememap" href="#" class="t4 selecttool" style='border-radius: 5px;' title="테마지도" onclick="COMMON_UTIL.cmWindowOpen('테마지도', contextPath + 'topmenu/selectThemeMap.do', 400, 730, false, null, 'thememap_pth');">
                            <span class="hidden">테마지도</span>
                        </a>
                    </li>
                </ul> -->

                <ul class="select_map" style="top: 75px;">
                    <li id="mCtrlSatenomalMap" class="selected_btn" onclick="MAP.fn_show_externalSateliteMap('roadmap')"><a href="#" class="map1"><span class="hidden">일반</span></a></li>
                    <li id="mCtrlSateliteMap"  class="btn" onclick="MAP.fn_show_externalSateliteMap('skyview')"><a href="#" class="map2"><span class="hidden">위성</span></a></li>
                </ul>

                <!-- 20190611  -->
                <ul class="pothole-regi" >
                    <li class="tab-title"><span style="line-height: 30px; margin: 0 5px">포트홀 신고등록</span></li>
                    <li style='width: 100%; background-color: white;' class="pthole">
                        <ul class="tblst selbx" >
                            <li style=" border-left:0px; margin: 7px 0 5px 0;" >
                                <span class="roundbx" style='width: 8px; height: 30px; line-height: 0px; margin:0px;' ></span>
                            </li>
                            <li style=" border-left:0px; margin: 7px 0 5px 0;" class="brl tc">
                                <a class="btncursor" id="btn_pthInsert" onclick="fn_pthInsert();" >
                                    <span class="roundbx" style='width: 45px; height: 30px; line-height: 30px; margin:0px;' ><i title="위치입력" class="material-icons pth1" style="font-size:30px; cursor: pointer;">place</i></span>
                                </a>
                            </li>
                            <!-- li style=" border-left:0px; margin: 7px 0 5px 0;" class="brl tc">
                                <a class="btncursor" id="btn_pthMove" onclick="fn_pthInsert_test();" >
                                    <span class="roundbx" style='width: 40px; height: 30px; line-height: 30px; margin:0px;' ><i title="이동" class="material-icons pth2" style="font-size:30px; cursor: pointer;">zoom_out_map</i></span>
                                </a>
                            </li-->
                            <li style=" border-left:0px; margin: 8px 0 5px 0;" class="brl tc">
                                <a class="btncursor" id="btn_pthSelect" onclick="fn_pthSelect();" >
                                    <span class="roundbx" style='width: 50px; height: 30px; line-height: 30px; margin:0px;' ><i title="선택" class="fa fa-mouse-pointer pth3" style="font-size:24px; cursor: pointer;"></i></span>
                                </a>
                            </li>
                            <li style=" border-left:0px; margin: 7px 0 5px 0;" class="brl tc">
                                <a class="btncursor" id="btn_pthUpdate" onclick="fn_pthUpdate();" >
                                    <span class="roundbx" style='width: 50px; height: 30px; line-height: 30px; margin:0px;' ><i title="수정" class="material-icons pth4" style="padding: 3px; font-size:24px; cursor: pointer;">border_color</i></span>
                                </a>
                            </li>
                            <li style=" border-left:0px; border-right:0px; margin: 9px 0 5px 0;" class="brl tc">
                                <a class="btncursor" id="btn_pthDelete" onclick="fn_pthDelete();" >
                                    <span class="roundbx" style='width: 45px; height: 30px; line-height: 30px; margin:0px;' ><i title="삭제" class='fas fa-trash-alt pth5' style="font-size:24px; cursor: pointer;"></i></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <!-- 20180419 공간검색을 팝업 -> goodmap 기본화면에 띄워주는 방식으로 변경 -->
                <ul class="space-search" >
                    <li class="tab-title"><span style="line-height: 30px; margin: 0 5px">신고정보 공간검색</span></li>
                    <li style='width: 100%; background-color: white;'>
                        <ul class="tblst selbx" >
                            <li style="width:50%; border-left:0px; margin: 7px 0;" class="brl tc">
                                <a class="selbtn btncursor" id="btn_selPoint" onclick="fn_selPoint();" >
                                    <!-- 노선정보 / 교량정보 -->
                                    <span class="roundbx normalmode" style='width: 30px; height: 30px; line-height: 30px;' ><img src="<c:url value='/images/ic_shape1.png' />" style="width:25px" alt="점" /></span>
                                    <span class="normalmode">점 <input type="text" name="pointArea" id="pointArea" maxLength="4" value="100" style="width:33px" /> m</span>
                                </a>

                            </li>
                            <li style="width:48%; margin: 7px 0;" class="tc">
                                <a class="selbtn btncursor" id="btn_selPolygon" onclick="fn_selPolygon();">
                                    <!-- 노선정보 / 교량정보 -->
                                    <span class="roundbx normalmode" style='width: 30px; height: 30px; line-height: 30px;'><img src="<c:url value='/images/ic_shape2.png' />" style="width:25px" alt="다각형" /></span>
                                    <span class="normalmode" style="line-height:22.5px">다각형</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>


                <dl id="snbacc" style="">
                    <dt>인덱스맵</dt>
                    <dd>
                        <!-- <p class="locationtxt">위치 확인 중...</p> -->
                        <div class="indexmap" style="height: 230px;">
                            <div class="inmapBx2" id="dvIndexMap"></div>
                            <div class="inmapBx3">
                                축척 1 : <input type="text" id="scale" onclick="this.select();" />
                                <p style="font-size: 10px;">일반지도는 유사축척으로 변경됨</p>
                                <p style="margin-top: 5px; font-size: 11px; color: black;">
                                    <label id="lblLonlat1"></label><br /> <label id="lblLonlat2"></label><br />
                                    <label id="lblXY"></label>
                                </p>
                            </div>
                        </div>
                    </dd>
                    <dt>레이어관리</dt>
<!--
                    <br>

                    <div>
                        <a id="export-png" class="btn btn-primary" download="map.png" role="button">Export Map</a>
                    </div>
 -->
                    <dd>
                        <div class="inBx" id="toolArea">
                            <div id="dvLayerList" class="LayerList" style="max-height: 210px;"></div>
                        </div>
                    </dd>
                </dl>
            </div>

            <%-- 팝업 관련 --%>
            <%@include file="/include/gis/mapPopupUI.jsp"%>
            <!-- 공통 (START)-->
            <%@ include file="/include/common.jsp"%>
            <!-- 공통 (END)-->

        </div>
        <!-- container end -->
    </div>


    <!-- wrap end -->
</body>
<script type="text/javascript" charset="utf-8">

var usrGrp = "${sessionScope.userinfo.REQ_USER_GRP}";

var iconId = [];

$(parent).resize(function() {

    var width = $(window).width();

    // 2018. 03. 05. JOY. 신고현황 위치 조정
    $(".top-tool.period").css("left", ((width / 2) - 644) + "px");
    $(".top-tool.total").css("left", ((width / 2) - 449) + "px");
    $(".top-tool.company").css("left", ((width / 2) + 412) + "px");
    $(".top-tool.route").css("left", ((width / 2) + 412) + "px");

    // 2018. 01. 04. JOY. 상단메뉴 위치 조정
    if ( $("#gnb").hasClass("good-header") ) {

        $("#gnb").css({"position" : "relative", "left" : (( width - 1590 ) / 2 ) + "px"});

    }
/*
//yyk. 통계탭 클릭후 최소화 -> 이후 window resize 시 css 깨짐현상 떄문에 주석처리
    $(".select_map").css( {"right" : "129px", "top" : "75px"} );
    $(".right_tool").css( {"right" : "18px", "top" : "75px"});
    $("#snbacc").css( {"right" : "17px", "top" : "238px"} );
 */
});

$(document).ready(function() {

    // 2018. 04. 24. JOY. 신고현황 DEPT_CODE 세팅
    if ( usrGrp != 'ROLE_ADMIN' ) {

        // 관리자가 아닌 경우엔 부서 setting
        $("#DEPT_CODE").val("${usrDeptCode}");

    }
    $("#USER_DEPT_CODE").val("${usrDeptCode}");

    // 초기화
    $("#daumMap").css("z-index", "-1");

    MAP.fn_update_resizeMap();

    $(window).resize(function () {
        MAP.fn_update_resizeMap();
    });

    //하단 메뉴 hide
    bottomHide();

    // 2018. 03. 05. JOY. 신고현황 위치 조정
    var width = $(window).width();

    $(".top-tool.period").css("left", ((width / 2) - 644) + "px");
    $(".top-tool.total").css("left", ((width / 2) - 449) + "px");
    $(".top-tool.company").css("left", ((width / 2) + 412) + "px");
    $(".top-tool.route").css("left", ((width / 2) + 412) + "px");

    // 2018. 01. 04. JOY. 상단메뉴 위치 조정
    if ( $("#gnb").hasClass("good-header") ) {

        $("#gnb").css({"position" : "relative", "left" : (( width - 1590 ) / 2 ) + "px"});

    }

    $(".select_map").css( {"right" : "129px", "top" : "75px"} );
    $(".right_tool").css( {"right" : "18px", "top" : "75px"});
    $("#snbacc").css( {"right" : "17px", "top" : "343px"} );

    // 2018. 01. 19. JOY. 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('STTEMNT_DT_START', 'STTEMNT_DT_END', 30);

    // 2주일 전
    //$('#STTEMNT_DT_START').val($.datepicker.formatDate('yy-mm-dd', new Date( new Date - ( 3600000 * 24 * 1 ) )));
    $('#STTEMNT_DT_START').val($.datepicker.formatDate('yy-mm-dd', new Date( new Date - ( 3600000 * 24 * 14 ) ) ));
    $('#STTEMNT_DT_END').val($.datepicker.formatDate('yy-mm-dd', new Date()));

    // 2018. 02. 26. JOY. 포트홀 신고현황 / 파손유형별 신고현황 탭 클릭 이벤트
    $(".status-tab").click(function(event) {

        gMap.removeAllPopups();
        $("#dvMapLoading").show();

        if ( $(this).attr("id") == "status" ) {

            $(this).addClass("on");
            $("#dmg").removeClass("on");

            gMap.getLayerByName("GStatusLayer").setVisibility(true);
            gMap.getLayerByName("GTypeLayer").setVisibility(false);

            // 2018.03.15. YYK. 포트홀신고현황 / 파손유형별신고현황 탭 전환시 공간검색의 마커 변환..
            gMap.getLayerByName("SttemntLayer").setVisibility(true);
            gMap.getLayerByName("DmgtLayer").setVisibility(false);

            $(".innerType").animate({width: 1}, 300);

            setTimeout(function() {

                $(".total-status").show();
                $(".total-type").hide();
                $(".innerType").hide();
                $(".innerStatus").show();
                $(".innerStatus").animate({width: 625}, 500);

            }, 301);

            //gMap.activeControls(['stteSelectFeature', 'drag']);
            gMap.activeControls('drag');

        } else {

            $(this).addClass("on");
            $("#status").removeClass("on");

            gMap.getLayerByName("GStatusLayer").setVisibility(false);
            gMap.getLayerByName("GTypeLayer").setVisibility(true);

            // 2018.03.15. YYK. 포트홀신고현황 / 파손유형별신고현황 탭 전환시 공간검색의 마커 변환..
            gMap.getLayerByName("SttemntLayer").setVisibility(false);
            gMap.getLayerByName("DmgtLayer").setVisibility(true);

            $(".innerStatus").animate({width: 1}, 300);

            setTimeout(function() {

                $(".total-status").hide();
                $(".total-type").show();
                $(".innerStatus").hide();
                $(".innerType").show();
                $(".innerType").animate({width: 625}, 500);

            }, 301);

            //gMap.activeControls(['dmgtSelectFeature', 'drag']);
            gMap.activeControls('drag');

        }

        $("#dvMapLoading").hide();

    });

    // 2018. 02. 20. JOY. 포트홀 신고 현황 on / off 클릭 이벤트
    $(".sttCircle").click(function(event) {

        $("#dvMapLoading").show();

        if ( $(this).hasClass("bgchk") ) {

            $(this).removeClass("bgchk");

        } else {

            $(this).addClass("bgchk");

        }

        fnSetStyle("GStatusLayer");

        $("#dvMapLoading").hide();

    });

    // 2018. 02. 26. JOY. 파손유형별 신고 현황 on / off 클릭 이벤트
    $(".dmgCircle").click(function(event) {

        $("#dvMapLoading").show();

        if ( $(this).hasClass("bgchk") ) {

            $(this).removeClass("bgchk");

        } else {

            $(this).addClass("bgchk");

        }

        fnSetStyle("GTypeLayer");

        $("#dvMapLoading").hide();

    });

 // 2019년 고도화 사업 - 접수경로별 현황 추가
    $(".prrtCircle").click(function(event) {

        $("#dvMapLoading").show();
        var pthmode0 = $(".prrtCircle.A").hasClass("bgcircle");
        var pthmode1 = $(".prrtCircle.T").hasClass("bgcircle");
        var pthmode2 = $(".prrtCircle.M").hasClass("bgcircle");
        var pthmode3 = $(".prrtCircle.C").hasClass("bgcircle");

        var pthmode = $("#pthmode").val();

        $(this).addClass("bgcircle");

        if ( $(this).hasClass("A") ) {
        	$("#bgcircle1").show();
        	$("#bgcircle2").show();
        	$("#bgcircle3").show();
            $("#bgcircle4").show();
            $("#pthmode").val("");
        }
        if ( $(this).hasClass("T") ) {
        	$("#bgcircle1").hide();
        	$("#bgcircle2").show();
        	$("#bgcircle3").hide();
            $("#bgcircle4").hide();
            $("#pthmode").val("T");
        }
        if ( $(this).hasClass("M") ) {
        	$("#bgcircle1").hide();
        	$("#bgcircle2").hide();
        	$("#bgcircle3").hide();
            $("#bgcircle4").show();
            $("#pthmode").val("M");
        }
        if ( $(this).hasClass("C") ) {
        	$("#bgcircle1").hide();
        	$("#bgcircle2").hide();
            $("#bgcircle3").show();
            $("#bgcircle4").hide();
            $("#pthmode").val("C");
        }

        parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #pthmode").val($('#pthmode').val());

        //alert($("#pthmode").val());
        fnSelectLayer();

        $("#dvMapLoading").hide();

    });

});

//========================== 공간검색 start =============================

//jQuery
$(function () {

 // = INPUT BOX EVENT =
 // [ STEP 1 ] 점 반경범위 변경
 $("#pointArea").change(function(){
    // point mode
    if ( $("#pointArea").parent().parent().parent().hasClass("on") ) {
        var range = $("#pointArea").val();

        if ( range == "" || range == undefined || range == "0" ) {
            alert ("반경 범위를 입력해 주세요.");
            parent.gMap.activeControls("drag");
            return;
            }
        gMap.getControl("selPointSttemnt").setDistance(range);
        }
    });

});

$('.left_tool').click(function(e){
    $("#btn_selPolygon").parent().removeClass("on");
    $("#btn_selPoint").parent().removeClass("on");
})

//= STEP 1 EVENT =
//[ STEP 1 ] 점
//$("#btn_selPoint").click(function() {
function fn_selPoint(){

    // 팝업 닫기
    var wnd = $.window.getAll();

    for ( var i = 0; i < wnd.length; i++ ) {

        if ( !$("#" + wnd[i].getWindowId()).find("iframe").contents().find("body").hasClass("searchLoc") ) {

            $.window.getWindow(wnd[i].getWindowId()).close();

        }

    }

    var range = $("#pointArea").val();

    gMap.getControl("selPointSttemnt").setDistance(range);

    // 탭 현황 별 클리어
    if ( $("#status").hasClass("on") ) {

        // 신고현황인 경우 SttemntLayer Clear
        gMap.getLayerByName('SttemntLayer').removeAllFeatures();

    } else {

        // 파손유형별 신고현황인 경우 DmtyLayer Clear
        gMap.getLayerByName('DmgtLayer').removeAllFeatures();

    }

    option = {};
    option = {
                iframe : window,
                callback : "fnCheckFeatures",
                clearMap : false
    };
    MAP.CONTROL.set_option(option);

    gMap.activeControls("selPointSttemnt");

    $("#map .left_tool li").removeClass("active");

    fnClearStep1Data();

    $("#btn_selPoint").parent().addClass("on");
    $("#point_explain").show();
    $("#polygon_explain").hide();
};


//[ STEP 1 ] 다각형
//$("#btn_selPolygon").click(function() {
function fn_selPolygon(){

    // 팝업 닫기
    var wnd = $.window.getAll();

    for ( var i = 0; i < wnd.length; i++ ) {

        if ( !$("#" + wnd[i].getWindowId()).find("iframe").contents().find("body").hasClass("searchLoc") ) {

            $.window.getWindow(wnd[i].getWindowId()).close();

        }

    }

    // 탭 현황 별 클리어
    if ( $("#status").hasClass("on") ) {

        // 신고현황인 경우 SttemntLayer Clear
        gMap.getLayerByName('SttemntLayer').removeAllFeatures();

    } else {

        // 파손유형별 신고현황인 경우 DmtyLayer Clear
        gMap.getLayerByName('DmgtLayer').removeAllFeatures();

    }

    option = {};

    option = {
            iframe : window,
            callback : "fnCheckFeatures",
            clearMap : false
    };

    MAP.CONTROL.set_option(option);

    gMap.activeControls("selPolygonSttemnt");

    $("#map .left_tool li").removeClass("active");
    fnClearStep1Data();

    $("#btn_selPolygon").parent().addClass("on");
    $("#point_explain").hide();
    $("#polygon_explain").show();
};



//= CHECK FEATURES =
//[ STEP 2 ] 공통
function fnCheckFeatures() {

    var features ;

    //포트홀신고현황일 경우, 파손유형별 신고 현황일 경우 두가지 features
    if ( parent.$('#status').hasClass('on') ){
        features = parent.gMap.getLayerByName('SttemntLayer').features;
        gMap.getLayerByName("SttemntLayer").setVisibility(true);
        gMap.getLayerByName("DmgtLayer").setVisibility(false);
    }

    else if ( parent.$('#dmg').hasClass('on') ){
        features = parent.gMap.getLayerByName('DmgtLayer').features;
        gMap.getLayerByName("SttemntLayer").setVisibility(false);
        gMap.getLayerByName("DmgtLayer").setVisibility(true);
    }

    if ( features.length == 0 ) {
        return -1;

    } else {
        // 검색할 내용이 선택된 경우
        gMap.activeControls("drag");
        $("#mCtrlPan").parent().addClass("active");

        // set cell count
        option.cellCount = features.length;

        try {
            fnStep2Data(features);

        } catch (err) {
            alert (err);
            return;
        }
        return 1;
    }
}

//= MENU FUNCTION : Sttemnt =
//[ STEP 2 ] 포트홀 신고관리 하단 창 띄우기
function fnStep2Data(features) {

    // PTH_RG_NO 조합
    var pthRgNo = [];
    var urlParam = "&pthmode=";

    // input cell Id from features
    // 포트홀신고등록 - 선택/수정의 경우 feature 1개  select
    if($("#btn_pthUpdate").parent().hasClass("on")){

    	$("#MGG_ID").val(features[0].data.allData.GG_ID);
        $("#MPTH_RG_NO").val(features[0].data.pthRgNo);
        $("#MRPRDTLS_MNG_NO").val(features[0].data.allData.RPRDTLS_MNG_NO);

        //컨트롤 초기화
        $('#mCtrlPan').trigger('click') ;

        // 포트홀신고등록 수정의 경우 modify 컨트롤 활성화
        if ( parent.$('#status').hasClass('on') ){

        	gMap.activeControls("drawEditPoint");

        } else{

        	gMap.activeControls("drawEditPointDmgt");
        }

        return;

    } else if($("#btn_pthDelete").parent().hasClass("on")){

        $("#MGG_ID").val(features[0].data.allData.GG_ID);
        $("#MPTH_RG_NO").val(features[0].data.pthRgNo);
        $("#MRPRDTLS_MNG_NO").val(features[0].data.allData.RPRDTLS_MNG_NO);

        //컨트롤 초기화
        $('#mCtrlPan').trigger('click');

        //MAP.CONTROL.deleteFeaturePthmnt();
        $("#dvMapLoading").hide();

        //지도로딩바 hide되지 않아 setTimeout 적용함.
        setTimeout(function() {
        	MAP.CONTROL.deleteFeaturePthmnt();
        }, 301);

        return;

    } else if($("#btn_pthSelect").parent().hasClass("on")){

        pthRgNo.push("C_" + features[0].data.pthRgNo);
        urlParam += "C";

    } else {

        for ( var i in features ) {
            // 2019년 고도화 사업 - PTH_RG_NO_LAYER : pthmode_pthRgNo 조합으로 변경함.
            //pthRgNo.push(features[i].data.pthRgNo);
            pthRgNo.push(features[i].data.pthmode + "_" + features[i].data.pthRgNo);
        }
    }

    /*
var bottomPop = document.getElementById("content_area").contentWindow;
    //if ( bottomPop.length > 0 ) {

    if (parent.$('#goodMap .good-header > ul > li:eq(1)').hasClass('active')){

        bottomPop.$("#PTH_RG_NO").val( '' );
        //bottomPop.$("#PTH_RG_NO_LAYER").val( pthRgNo );
        bottomPop.fn_search(pthRgNo, 'Y');
        //fnSelectLayer(); // 상단과 매핑 : 상단은 전체마커와 매칭되어야하기에 주석
        //bottomPop.$("#PTH_RG_NO").val( '' );
        bottomOpen();
    }
    else{ */
        sel_pothole_sttemnt();

        COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/sttemnt/selectSttemntList.do?PTH_RG_NO_LAYER='+pthRgNo+ '&sflag=Y' + urlParam + '" />', null, 'N');
/*  } */


    // 이미 포트홀 신고관리 일 경우 새 창을 열지 않고, bottomopen함....
//  if (parent.$('#goodMap .good-header > ul > li:eq(1)').hasClass('active')){
//      bottomOpen();
//  }else {
        //COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/sttemnt/selectSttemntList.do?PTH_RG_NO_LAYER='+pthRgNo+ '&sflag=Y" />', null, 'N');
    //}

    //컨트롤 초기화
    $('#mCtrlPan').trigger('click') ;
    /*
    if ( features[0].layer.name == 'SttemntLayer') {
        gMap.activeControls(['stteSelectFeature', 'drag']);
    }
    else {
        //gMap.activeControls(['dmgtSelectFeature', 'drag']);
    }
     */

}


// CLEAR FUNCTION

//[ STEP 2 ] 초기화
function fnClearStep1Data() {
    if ( $('#bottom').height() > 0 ) {
        bottomClose();
    }

    // STEP 2 초기화
    $("#btn_selPoint").parent().removeClass("on");
    $("#btn_selPolygon").parent().removeClass("on");
    $("#btn_pthSelect").parent().removeClass("on");
    $("#btn_pthUpdate").parent().removeClass("on");
    $("#btn_pthDelete").parent().removeClass("on");
    $("#MGG_ID").val("");
    $("#MPTH_RG_NO").val("");    $("#MRPRDTLS_MNG_NO").val("");

}

//========================== 공간검색 end =============================


//========================== 포트홀 신고 등록 start ========================

//2019년 고도화 사업 - 포트홀신고등록 기능 추가
//포트홀_신고_등록_위치입력
function fn_pthInsert() {

    // 팝업 닫기
    var wnd = $.window.getAll();

    for ( var i = 0; i < wnd.length; i++ ) {

        if ( !$("#" + wnd[i].getWindowId()).find("iframe").contents().find("body").hasClass("searchLoc") ) {

            $.window.getWindow(wnd[i].getWindowId()).close();

        }

    }

    // 탭 현황 별 클리어
    if ( $("#status").hasClass("on") ) {

        // 신고현황인 경우 SttemntLayer Clear
        gMap.getLayerByName('SttemntLayer').removeAllFeatures();

    } else {

        // 파손유형별 신고현황인 경우 DmtyLayer Clear
        gMap.getLayerByName('DmgtLayer').removeAllFeatures();

    }

    gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
    gMap.getLayerByName('GOverlapLayer').removeAllFeatures();

    $("#map .left_tool li").removeClass("active");
    fnClearStep1Data();

    //gMap.cleanMap();
    gMap.activeControls("drawPoint");


}

//2019년 고도화 사업 - 포트홀신고등록 기능 추가
//포트홀_신고_등록_위치선택
function fn_pthSelect() {

    // 팝업 닫기
    var wnd = $.window.getAll();

    for ( var i = 0; i < wnd.length; i++ ) {

        if ( !$("#" + wnd[i].getWindowId()).find("iframe").contents().find("body").hasClass("searchLoc") ) {

            $.window.getWindow(wnd[i].getWindowId()).close();

        }

    }

    // 탭 현황 별 클리어
    if ( $("#status").hasClass("on") ) {

        // 신고현황인 경우 SttemntLayer Clear
        gMap.getLayerByName('SttemntLayer').removeAllFeatures();

    } else {

        // 파손유형별 신고현황인 경우 DmtyLayer Clear
        gMap.getLayerByName('DmgtLayer').removeAllFeatures();

    }

    gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
    gMap.getLayerByName('GOverlapLayer').removeAllFeatures();

    option = {};
    option = {
                iframe : window,
                callback : "fnCheckFeatures",
                clearMap : false
    };
    MAP.CONTROL.set_option(option);

    gMap.activeControls("selPointPthEdit");

    $("#map .left_tool li").removeClass("active");
    fnClearStep1Data();

    $("#btn_pthSelect").parent().addClass("on");

    /*
    gMap.cleanMap();

    gMap.getLayerByName("baseLayer").mergeNewParams( {
        layers : ["PTH_CTSMNT"],
        styles : ["PTH_CTSMNT"]
    });
    gMap.getLayerByName("baseLayer").setVisibility(true);


    gMap.activeControls("drawEdit",true);
    */


}

//2019년 고도화 사업 - 포트홀신고등록 기능 추가
//포트홀_신고_수정_위치입력
function fn_pthUpdate() {

	//위치 선택 --> 수정 control 활성화할 수 있도록 함.
	fn_pthSelect();
    $("#btn_pthUpdate").parent().addClass("on");
}

//포트홀_신고_삭제_위치입력
function fn_pthDelete() {

	//위치 선택 --> 삭제 control 활성화할 수 있도록 함.
    fn_pthSelect();
    $("#btn_pthDelete").parent().addClass("on");



}

//위치 편집 후 마커 위치를 수정한다.
function fnMakerSync(gg_id, pth_rg_no, x, y, pthmode) {

    // 신고현황 마커 위치 변경
    var sLyr = parent.gMap.getLayerByName("GStatusLayer");
    var sFts = sLyr.features;

    for ( var i = 0; i < sFts.length; i++ ) {

        if  ( gg_id == sFts[i].data.allData.GG_ID) {

            sFts[i].geometry.x = x;
            sFts[i].geometry.y = y;

            sFts[i].data.allData.TM_X = x;
            sFts[i].data.allData.TM_Y = y;

            break;

        }

    }

    sLyr.redraw();

    // 파손유형 마커 위치 변경
    var tLyr = parent.gMap.getLayerByName("GTypeLayer");
    var tFts = tLyr.features;

    for ( var i = 0; i < tFts.length; i++ ) {

        if  ( gg_id == tFts[i].data.allData.GG_ID ) {

            tFts[i].geometry.x = x;
            tFts[i].geometry.y = y;

            tFts[i].data.allData.TM_X = x;
            tFts[i].data.allData.TM_Y = y;

            break;

        }

    }

    tLyr.redraw();

    // 공간검색 - 신고현황 마커 위치 변경
    var ssLyr = parent.gMap.getLayerByName("SttemntLayer");
    var ssFts = ssLyr.features;

    for ( var i = 0; i < ssFts.length; i++ ) {

        if  ( gg_id == ssFts[i].data.allData.GG_ID ) {

            ssFts[i].geometry.x = x;
            ssFts[i].geometry.y = y;

            ssFts[i].data.allData.TM_X = x;
            ssFts[i].data.allData.TM_Y = y;

            break;

        }

    }

    ssLyr.redraw();

    // 공간검색 - 파손유형 마커 위치 변경
    var stLyr = parent.gMap.getLayerByName("DmgtLayer");
    var stFts = stLyr.features;

    for ( var i = 0; i < stFts.length; i++ ) {

        if  ( gg_id == stFts[i].data.allData.GG_ID ) {

            stFts[i].geometry.x = x;
            stFts[i].geometry.y = y;

            stFts[i].data.allData.TM_X = x;
            stFts[i].data.allData.TM_Y = y;

            break;

        }

    }

    // 현재 신고정보 위치 변경
    var oLyr = parent.gMap.getLayerByName("GOverlapLayer");
    var oFts = oLyr.features;

    for ( var i = 0; i < oFts.length; i++ ) {

        if  (( pth_rg_no == oFts[i].data.pthRgNo )
                && (pthmode == oFts[i].data.pthmode)){

            oFts[i].geometry.x = x;
            oFts[i].geometry.y = y;

            break;

        }

    }

    oLyr.redraw();

}


//========================== 포트홀 신고 등록 end ==========================



// ============================ 상단 현황 ===============================
function fnSelectLayer(){

    var postData = {
            "PTH_RG_NO"             : $("#PTH_RG_NO").val()
            , "BSNM_NM"             : $("#BSNM_NM").val()
            , "VHCLE_NO"            : $("#VHCLE_NO").val()
            , "DEPT_CODE"           : $("#DEPT_CODE").val()
            , "SCH_PRCS_STTUS"      : $("#PRCS_STTUS").val()
            , "SCH_DMG_TYPE"        : $("#DMG_TYPE").val()
            , "RPAIR_DT_START"      : $("#RPAIR_DT_START").val()
            , "RPAIR_DT_END"        : $("#RPAIR_DT_END").val()
            , "STTEMNT_DT_START"    : $("#STTEMNT_DT_START").val()
            , "STTEMNT_DT_END"      : $("#STTEMNT_DT_END").val()
            , "pthmode"             : $("#pthmode").val()
    };
/*
    if ( postData["PTH_RG_NO"] != '' && postData["PTH_RG_NO"].indexOf('-') == -1 ) {
        //postData["PTH_RG_NO"] = postData["PTH_RG_NO"].concat('-');
        $('#PTH_RG_NO').val($('#PTH_RG_NO').val() + '-');
    }
 */
    // 건수 check
    $.ajax({
        url : contextPath + 'api/map/selectPrcsSttusListCnt.do'
        , type : 'post'
        , dataType : 'json'
        , contentType : 'application/json; charset=UTF-8'
        , data : JSON.stringify(postData)
        , success : function(jdata) {

            if ( jdata.totCnt > 500 ) {

                if ( confirm("신고정보가 500건 이상 조회되어 느려질 수 있습니다.\n계속하시겠습니까?") ) {

                    // 조회기간이 1년을 초과할 경우 경고
                    var start_dt = ($("#STTEMNT_DT_START").val()).split('-');
                    var end_dt = ($("#STTEMNT_DT_END").val()).split('-');
                    start_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
                    end_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);
                    if ( end_dt - start_dt >= 365 * 24 * 60 * 60 * 1000
                         && (parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #DEPT_CODE").val() == ""
                            || parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #DEPT_CODE").val() == undefined )
                    ) {

                        alert('조회기간이 1년을 초과할 경우 관할기관을 선택한 후 조회해야만 합니다.');
                        if ( !parent.$('#goodMap .good-header > ul > li:eq(1)').hasClass('active') ){

                            sel_pothole_sttemnt();
                            COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/sttemnt/selectSttemntList.do"/>' ,null, 'N') ;

                        }

                        bottomOpen();
                        return;

                    }
                    else {
                        fnSelectSttemnt();
                    }
                }

            } else {

                fnSelectSttemnt();

            }
        }
    });

}

function fnSelectSttemnt() {

    $("#dvMapLoading").show();

    // 하단 팝업 검색 함수 실행
    var bottomPop = document.getElementById("content_area").contentWindow;

    if ( bottomPop.length > 0 ) {

        /* bottomPop.$("#PTH_RG_NO").val( $("#PTH_RG_NO").val() );
        bottomPop.$("#BSNM_NM").val( $("#BSNM_NM").val() );
        bottomPop.$("#VHCLE_NO").val( $("#VHCLE_NO").val() );
        bottomPop.$("#DEPT_CODE").val( $("#DEPT_CODE").val() );
        bottomPop.$("#PRCS_STTUS").val( $("#PRCS_STTUS").val() );
        bottomPop.$("#DMG_TYPE").val( $("#DMG_TYPE").val() );
        bottomPop.$("#RPAIR_DT_START").val( $("#RPAIR_DT_START").val() );
        bottomPop.$("#RPAIR_DT_END").val( $("#RPAIR_DT_END").val() );
        bottomPop.$("#STTEMNT_DT_START").val( $("#STTEMNT_DT_START").val() );
        bottomPop.$("#STTEMNT_DT_END").val( $("#STTEMNT_DT_END").val() ); */

        bottomPop.fn_search();
    }

    var postData = {

            "PTH_RG_NO"             : $("#PTH_RG_NO").val()
            , "BSNM_NM"             : $("#BSNM_NM").val()
            , "VHCLE_NO"            : $("#VHCLE_NO").val()
            , "DEPT_CODE"           : $("#DEPT_CODE").val()
            , "SCH_PRCS_STTUS"      : $("#PRCS_STTUS").val()
            , "SCH_DMG_TYPE"        : $("#DMG_TYPE").val()
            , "RPAIR_DT_START"      : $("#RPAIR_DT_START").val()
            , "RPAIR_DT_END"        : $("#RPAIR_DT_END").val()
            , "STTEMNT_DT_START"    : $("#STTEMNT_DT_START").val()
            , "STTEMNT_DT_END"      : $("#STTEMNT_DT_END").val()
            , "pthmode"             : $("#pthmode").val()

    };
    postData["PTH_RG_NO"] = searchPthRgNo(postData["PTH_RG_NO"]);

    $.ajax({
        url : contextPath + 'api/map/selectPrcsSttusList.do'
        , type : 'post'
        , dataType : 'json'
        , contentType : 'application/json; charset=UTF-8'
        , data : JSON.stringify(postData)
        , success : function(jdata) {

            if ( jdata != null ) {

                // ========================= 처리상태별 =========================
                // 레이어 조회
                var GStatusLayer = gMap.getLayerByName("GStatusLayer");
                var data = jdata.prcsSttus;

                // 레이어 clear
                GStatusLayer.removeAllFeatures();

                // 건수 setting
                $("#status-count").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus.length));
                $("#PRCS_STTUS1").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus1.length));
                $("#PRCS_STTUS2").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus2.length));
                $("#PRCS_STTUS3").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus3.length));
                $("#PRCS_STTUS4").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus4.length));
                $("#PRCS_STTUS5").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus5.length));
                $("#PRCS_STTUS6").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus6.length));
                $("#PRCS_STTUS7").text(COMMON_UTIL.cmAddComma(jdata.prcsSttus7.length));

                // filter setting
                fnSetStyle("GStatusLayer");

                // marker setting
                for ( var i = 0; i < data.length; i++ ) {

                    GStatusLayer.addFeatures(
                            new OpenLayers.Feature.Vector(
                                    new OpenLayers.Geometry.Point( data[i].TM_X, data[i].TM_Y )
                                    , {
                                        allData     : data[i]
                                        , angle     : data[i].HEADG * 1 - 90
                                        , pthRgNo   : data[i].PTH_RG_NO
                                        , pthmode   : data[i].pthmode
                                        , data      : data[i].PRCS_STTUS
                                    }
                            )
                    );

                }

                // 레이어 redraw
                GStatusLayer.redraw();

                // ========================= 파손유형별 =========================
                // select layer
                var GTypeLayer = gMap.getLayerByName("GTypeLayer");
                var data = jdata.dmgType;

                // 레이어 clear
                GTypeLayer.removeAllFeatures();

                // 건수 setting
                $("#type-count").text(COMMON_UTIL.cmAddComma(jdata.dmgType.length));
                $("#DMG_TYPE1").text(COMMON_UTIL.cmAddComma(jdata.dmgType1.length));
                $("#DMG_TYPE2").text(COMMON_UTIL.cmAddComma(jdata.dmgType2.length));
                $("#DMG_TYPE3").text(COMMON_UTIL.cmAddComma(jdata.dmgType3.length));
                $("#DMG_TYPE4").text(COMMON_UTIL.cmAddComma(jdata.dmgType4.length));
                $("#DMG_TYPE5").text(COMMON_UTIL.cmAddComma(jdata.dmgType5.length));
                $("#DMG_TYPE6").text(COMMON_UTIL.cmAddComma(jdata.dmgType6.length));
                $("#DMG_TYPE7").text(COMMON_UTIL.cmAddComma(jdata.dmgType7.length));
                $("#DMG_TYPE8").text(COMMON_UTIL.cmAddComma(jdata.dmgType8.length));

                // filter setting
                fnSetStyle("GTypeLayer");

                // marker setting
                for ( var i = 0; i < data.length; i++ ) {

                    GTypeLayer.addFeatures(
                            new OpenLayers.Feature.Vector(
                                    new OpenLayers.Geometry.Point( data[i].TM_X, data[i].TM_Y )
                                    , {
                                        allData     : data[i]
                                        , angle     : data[i].HEADG * 1 - 90
                                        , pthRgNo   : data[i].PTH_RG_NO
                                        , pthmode   : data[i].pthmode
                                        , data      : data[i].DMG_TYPE
                                    }
                            )
                    );

                }

                // 레이어 redraw
                GTypeLayer.redraw();

                // 민자도로사업자 신고 건 setting
                $("#PRV_RD_OPRT").text(COMMON_UTIL.cmAddComma(jdata.prvRdOprtCnt));

                $("#dvMapLoading").hide();

            }

        }
        , error : function(err) {
            alert(err);
        }
    })

}
// 필터 스타일 세팅
function fnSetStyle(layername) {

    // on 되어있는 조건 check - Filtering
    var layerStyle = new OpenLayers.Style(null);
    var ruleArr = [];

    var area = "";
    var styleArr = [];

    if ( layername == "GStatusLayer" ) {

        // 포트홀 편집인 경우
        area = "sttCircle";
        styleArr = MAP.statusStyle;

    } else {

        // 파손유형별 조회 인 경우
        area = "dmgCircle";
        styleArr = MAP.dmgtStyle;

    }

    var layer = gMap.getLayerByName(layername);
    var obj = $("." + area + ".bgchk");

    for ( var i = 0; i < obj.length; i++ ) {

        // on 인 경우 색상 변경
        var tobj = obj.eq(i);
        var len = tobj.attr("id").length;
        var index = tobj.attr("id").substr(len - 1, len);

        ruleArr.push(styleArr[index]);

    }

    // 해당 필터가 없는 경우 투명마커
    if ( ruleArr.length == 0 ) {

        ruleArr.push(styleArr[0]);

    }

    layerStyle.addRules(ruleArr);
    layer.options.styleMap.styles.default = layerStyle;

    layer.redraw();

}

// 2018. 02. 20. JOY. 민자도로사업자 정보 조회
function fnSelectCompany() {
    // YYK 민자도로사업자 조회 : DEPT_CODE > 7000300 AND DEPT_CODE < 7000400
    parent.COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/sttemnt/selectSttemntList.do"/>?DEPT_CODE=7000300', null, 'N');
}

// 2018.03.16 YYK 상단 신고현황 탭의 날짜와 연동 (동적 변경)
$('#STTEMNT_DT_START, #STTEMNT_DT_END').on('keyup change', function(e){
    parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #STTEMNT_DT_START").val($('#STTEMNT_DT_START').val());
    parent.$('#goodMap').find("iframe[name='content_area']").contents().find(".sttemntList #STTEMNT_DT_END").val($('#STTEMNT_DT_END').val());
});



// enter check
function fnCheckEnter(event) {

    if ( event.keyCode == 13 ) {

        fnSelectLayer();
    }
}


var MAIN = (function(_mod_map, $, undefined) {

    //레이어 관리 목록 조회
    var oLayerInfoList,
    oLayerInfoListTheme,
    oTmapInfoList,
    oTmapGroupInfoList,
    oEditLayerInfoList,
    oLayerGroups;


    oLayerInfoList = {
            <c:forEach items="${layerInfoListTheme}" var="layerInfo" varStatus="status">
                "${layerInfo.table}" : {
                    <c:forEach items="${layerInfo}" var="layer" >
                    '${layer.key}' : '${layer.value}',</c:forEach>
                },
            </c:forEach>
    };
    // YYK 초기 로딩시  show = '0'
    oLayerInfoList['CMPTNC_ZONE'].show = "0"

    layerTool = new GTMapLayerTool(oLayerInfoList, oTmapInfoList, oTmapGroupInfoList, oLayerGroups, {
        tMapId : 1,
        serviceUrl : CONFIG.fn_get_serviceUrl(),    // GeoGate 주소
        callback : function(_oRes, _bUserStyle, layerName){

            MAP.fn_init_map(_oRes, _bUserStyle, 'cmptncLayer');

            // 2018. 02. 20. JOY. 포트홀신고현황
            fnSelectLayer();

        },
        userStyle : ''

    });


    var fn_get_layerInfoList = function (_oLayer){

        return oLayerInfoList[_oLayer];
    }

    var fn_get_layerTotInfoList = function (){

        return oLayerInfoList;
    }

    //------------------------------------------------------------------------------------------------------------------
    //## public 메소드
    //------------------------------------------MAP_EDITOR.fn_init_timeLine(); ------------------------------------------------------------------------
    _mod_map.fn_get_layerInfoList           =   fn_get_layerInfoList;
    _mod_map.fn_get_layerTotInfoList        =   fn_get_layerTotInfoList;
    //------------------------------------------------------------------------------------------------------------------


    return _mod_map;

}(MAIN || {}, jQuery));

</script>
</html>