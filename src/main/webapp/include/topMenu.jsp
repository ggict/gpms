<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${sessionScope.system == 'mng' }">
        <!-- ADMIN TOP MENU START -->
        <header class="header">
        <div class="container">
            <h1 class="h1">
                <img src="<c:url value='/images/orgLogo.png'/>" alt="세계속의경기도"><span>포장관리시스템</span>
            </h1>
            <div class="navBg"></div>
            <nav class="nav">
                <ul>
                    <li class="a00">
                        <a href="#none" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/sysUserList.do"/>')"><span>사용자 관리</span></a>
                        <ul>
                            <li class="s10">
                                <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/sysUserList.do"/>')">사용자 관리</a>
                            </li>
                            <li class="s20">
                                <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/applyUserList.do"/>')">사용자 신청 관리</a>
                            </li>
                            <li class="s30"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/selectSysUserLog.do"/>')">사용자 접속로그 조회</a></li>
                        </ul>
                    </li>
                    <li class="b00">
                        <a href="#none" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/menu/selectUserMenuList.do"/>')"><span>시스템메뉴 관리</span></a>
                        <ul>
                            <li class="s10">
                                <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/menu/selectUserMenuList.do"/>')">시스템메뉴 관리</a>
                            </li>
                            <li class="s20">
                                <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/rolemenu/selectRoleMenuList.do"/>')">시스템메뉴 권한관리</a>
                            </li>
                        </ul>
                    </li>
                    <li class="c00">
                        <a href="#none" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/clcode/codeList.do"/>')"><span>코드정보관리</span></a>
                        <ul>
                            <li class="s10" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/clcode/codeList.do"/>')"><a href="#none">코드정보관리</a></li>
                        </ul>
                    </li>
                    <li class="c00">
                        <a href="#none" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/notice/selectNoticeList.do"/>')"><span>공지사항관리</span></a>
                        <ul>
                            <li class="s10" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/notice/selectNoticeList.do"/>')"><a href="#none">공지사항관리</a></li>
                        </ul>
                    </li>
                    <li class="d00">
                        <a href="#none" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="srvy/srvyDtaEvlFmla.do"/>')"><span>수식관리</span></a>
                        <ul>
                            <ul>
                                <li class="s10">
                                    <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="srvy/srvyDtaEvlFmla.do"/>')">포장상태 평가수식조회</a>
                                </li>
                                <li class="s20">
                                    <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="predctfrmulaidx/selectPredctFrmulaIdxList.do"/>')">공용성예측모델 수식조회</a>
                                </li>
                            </ul>
                        </ul>
                    </li>
                </ul>
            </nav>
            <span class="topMenu">
                <a href="#none" class="btnMonitoring" title="새창">모니터링시스템</a>
                <span class="userArea">
                    <a id="userDept" href="#none" ></a><c:out value="" />
                    <span class="user-area">
    				    <a href="#" onclick="COMMON_UTIL.cmWindowOpen('사용자 정보', contextPath +'/api/sysuser/updateUserView.do', 420, 350, true, null, 'center');"> 사용자 정보</a>
    				    <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/applyUserList.do"/>');" style="display: none;">신청<span id="usrCnt"></span>건</a>
    				   <a href="#" onclick="fnMap();">지도</a>
    				</span>
                </span>
                <button  class="btnLogout" onclick="fnLogout();">로그아웃</button>
            </span>

        </div>
    </header>
        <!-- ADMIN TOP MENU END -->
    </c:when>

    <c:when test="${sessionScope.system == 'map' }">

        <!-- GPMS TOP MENU START -->
<header class="header">
        <div class="container">
            <h1 class="h1">
                <img src="images/orgLogo.png" alt="세계속의경기도"><span>포장관리시스템</span>
            </h1>
            <div class="navBg"></div>
            <nav class="nav">
                <ul>
                    <li class="a00">
                        <a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="routeinfo/selectRouteInfoList.do"/>');return false;"><span>노선검색</span></a>
                        <ul>
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="routeinfo/selectRouteInfoList.do"/>');return false;">데이터로검색</a></li>
<%--                             <li class="s20"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cell10/selectCell10List.do"/>');return false;">셀단위로검색</a></li> --%>
                        </ul>
                    </li>
                    <li class="b00">
                        <a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvydtaexcel/selectSrvyDtaExcelList.do"/>');return false;"><span>조사자료관리</span></a>
                        <ul>
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvydtaexcel/selectSrvyDtaExcelList.do"/>');return false;">조사자료등록</a></li>
                            <li class="s20"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyExcelList.do"/>');return false;">조사자료이력조회 </a></li>
                            <li class="s30"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/srvyunsectionlist.do"/>');return false;">미조사구간조회</a></li>
                            <li class="s40"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvyrequstsctn/addSrvyRequstSctnView.do"/>');return false;">조사요청구간등록</a></li>
                            <li class="s50"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvyrequstsctn/selectSrvyRequstSctnList.do"/>');return false;">조사요청구간관리</a></li>
                        </ul>
                    </li>
                    <li class="c00">
                        <a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>');return false;"><span>포장상태평가</span></a>
                        <ul>
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>');return false;">평가정보조회</a></li>
                            <li class="s10"><a href="#none" onclick="fn_evlStats('2019');return false;">평가상태도</a>
                                <ul>
                                    <%
                                        // 선정년도
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                                        String year = dateFormat.format(new Date());
                                        List<Integer> slctnYearList = new ArrayList<Integer>();
                                        for ( int i = Integer.valueOf(year); i >= 2019; i-- ) {
                                            slctnYearList.add(i);
                                        }
                                    %>
                                    <c:set var="slctnYearList" value="<%=slctnYearList%>" />
                                    <c:forEach var="slctnYear" items="${slctnYearList}">
                                        <li><a href="#none" onclick="fn_evlStats('<c:out value="${slctnYear}" />');return false;"><c:out value="${slctnYear}" />년도</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <%-- <li class="s20"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyDtaEvlList.do"/>');return false;">포장상태 평가</a></li> --%>
                        </ul>
                    </li>
                    <li class="e00">
                        <a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cntrwk/selectCntrwkList.do"/>');return false;"><span>포장공사이력관리</span></a>
                        <ul>
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cntrwk/selectCntrwkList.do"/>');return false;">포장공사이력관리</a></li>
<%--                             <li class="s20"><a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cntrwk/cntrwkProgressList.do"/>');return false;">포장공사진행현황</a></li> --%>
                        </ul>
                    </li>
                    <li class="d00">
                        <a href="#none" onclick="COMMON_UTIL.fn_set_subMenu('sub_repairtargets','<c:url value="rpairtrgetslctn/intro.do"/>');return false;"><span>보수대상선정</span></a>
                        <ul>
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.fn_set_subMenu('sub_repairtargets','<c:url value="rpairtrgetslctn/intro.do"/>');return false;">보수대상선정</a></li>
                            <li class="s20"><a href="#none" onclick="COMMON_UTIL.cmWindowOpen('유지보수 실적집계 엑셀출력', contextPath +'/cntrwk/setDownloadReport.do', 390, 220, true, null, 'center');return false;">유지보수 실적집계</a></li>
                        </ul>
                    </li>
                    <li class="d00">
                        <a href="#none" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="predctModel/predctModelList.do"/>');return false;"><span>예측모델</span></a>
                    </li>
                    <li class="f00">
                        <a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/viewRoutLenStats.do"/>');return false;"><span>통계</span></a>
                        <ul>
                            <!-- sub_stat_route -->
                            <li class="s10"><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent( '<c:url value="stats/viewRoutLenStats.do"/>');return false;">노선별 현황</a>
                            	<ul>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/viewRoutLenStats.do"/>');return false;">노선별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/viewDeptLenStats.do"/>');return false;">관리기관별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/viewTrackLenStats.do"/>');return false;">차로별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/viewAdmLenStats.do"/>');return false;">시군구별 통계</a></li>
                                </ul>
                            </li>
                            <!-- sub_stat_mumm -->
                            <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummRoutCntStats.do"/>');return false;">포장상태 평가</a>
                                <ul>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummRoutCntStats.do" />');return false;">노선별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummDeptCntStats.do" />');return false;">관리기관별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummAdmCntStats.do" />');">시군구별 통계</a></li>
                                </ul>
                            </li>
                            <!-- sub_stat_rpair -->
                            <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="rpairtrgetgroup/rpairRoutLenStats.do"/>');return false;">보수대상 선정</a>
                                    <ul>
                                        <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="rpairtrgetgroup/rpairRoutLenStats.do"/>');return false;">노선별 통계</a></li>
                                        <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="rpairtrgetgroup/rpairDeptLenStats.do"/>');return false;">관리기관별 통계</a></li>
                                    </ul>
                            </li>
                            <!-- sub_stat_cntrwk -->
                            <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="cntrwkdtl/selectCntrwkRoutLenStats.do"/>')">포장공사 이력</a>
                                <ul>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('cntrwkdtl/selectCntrwkRoutLenStats.do');">노선별 통계</a></li>
                                    <li><a href="#none" onclick="COMMON_UTIL.statsMenuUrlContent('cntrwkdtl/selectCntrwkDeptLenStats.do');">관리기관별 통계</a></li>
                                </ul>
                            </li>
                            <!-- <li><a href="#" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat', '<c:url value="cntrwkdtl/selectCntrwkRoutCntStats.do"/>')">포장공사 이력</a></li>  -->
                            <!-- <li><a href="#" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_predct', '<c:url value="smdtalaststtus/selectSmDtaLastRoutCntStats.do"/>')">포장상태 예측</a></li>  -->
                            <!-- <li><a href="#" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_survey', '<c:url value="stats/selectSurveyAllLenStatsTable.do"/>')">포장상태 조사구간</a></li>  -->
                        </ul>
                    </li>
                </ul>
            </nav>
            <span class="topMenu">
                <span class="userArea">
                    <a id="userDept" href="#none" ></a><c:out value="" />
                    <span class="user-area">
    				    <a href="#" onclick="$('.userBx').hide(); COMMON_UTIL.cmWindowOpen('사용자 정보', contextPath +'/api/sysuser/updateUserView.do', 420, 350, true, null, 'center');"> 사용자 정보</a>
    				    <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/applyUserList.do?sFlag=gpms"/>');" style="display: none;">신청<span id="usrCnt"></span>건</a>
    				    <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/sysUserList.do?sFlag=gpms"/>');">관리자</a>
    				</span>
                </span>
                <button  class="btnLogout" onclick="fnLogout();">로그아웃</button>
            </span>

        </div>
    </header>

        <!-- GPMS TOP MENU END -->
    </c:when>

    <c:otherwise>
        <!-- GOODMORNING TOP MENU START -->
        <div id="header">
			<img src="<c:url value='/images/logo3.png'/>" alt="경기도로 모니터링 시스템" class="logo" />
            <h2 class="hidden">메인메뉴</h2>
            <div id="gnb" class="good-header">
                <ul>
                    <li style="float: right;"><a href="#" class="menu10 top" onclick="fnSelectFirst($(this)); COMMON_UTIL.fn_set_subMenu('good_stat', '<c:url value="pothole/sttst/selectDatePeriodList.do"/>',null, 'N')"><span class="hidden">통계</span></a>
                        <div class="submenu sub10" id="sub10">
                            <ul>
                                <li><a href="#" class="sm10 sub" onclick="COMMON_UTIL.fn_set_subMenu('good_stat', '<c:url value="pothole/sttst/selectDatePeriodList.do"/>', null, 'N')">관할기관기준 처리현황</a></li>
                                <li><a href="#" class="sm10 sub" onclick="COMMON_UTIL.fn_set_subMenu('good_stat', '<c:url value="pothole/sttst/selectBsnmPeriodList.do"/>', null, 'N')">신고자기준 신고현황</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="noSub" id='sub9' style="float: right;"><a href="#" class="menu9 top" onclick="COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/sttemnt/selectSttemntList.do"/>' ,null, 'N')"><span class="hidden">포트홀 신고관리</span></a></li>
                    <li class="noSub" id='sub8' style="float: right;"><a href="#" class="menu8 top" onclick="COMMON_UTIL.fn_set_subMenu('good', '<c:url value="pothole/cmptnc/selectCmptncList.do"/>' ,null, 'N')"><span class="hidden">관할구역 검색</span></a></li>
                </ul>
            </div>
            <div class="submenubg"></div>
            <div class="logobx" style='line-height:63px; cursor:pointer;'>
                <img src="<c:url value='images/sys1.png' />" id='logobx1' style='vertical-align:middle; width:90px;' >
            </div>
            <div class="htbx user-area">
                <h2 class="hidden">사용자정보</h2>
                <div class="user user-area">
                    <a href="#" title="사용자" class="user-area"><span class="hidden">사용자</span></a>
                </div>
            </div>
            <div class="userBx user-area" style="width: 230px;">
                <img src="images/user_tail.png" class="user-area"/>
                <div class="userinfo user-area">
                    <span class="user-area" style="height: 20px;">
                        <span id="userDept" style="margin: 0px; width: 150px; float: left;"></span><c:out value="" />
                        <a href="#" onclick="fnLogout();" class="btn_blue user-area">로그아웃</a>
                    </span>
                    <span class="user-area">
                        <a href="#" onclick="COMMON_UTIL.cmWindowOpen('사용자 정보', contextPath +'/api/sysuser/updateUserView.do', 420, 350, true, null, 'center');" class="br"> 사용자 정보</a>
                        <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/applyUserList.do?sFlag=good"/>');" style="display: none;">신청<span class="usrCnt" id="usrCnt"></span>건</a>
                        <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/sysUserList.do?sFlag=good"/>');" >관리자</a>
                    </span>
                </div>
            </div>
        </div>
        <!-- GOODMORNING TOP MENU END -->
    </c:otherwise>
</c:choose>

<script type="text/javascript" charset='utf-8'>
//JOY. 2018. 03. 13. 시스템 구분 Flag
var sFlag = "${sessionScope.sFlag}";

var contextPath="${ pageContext.request.contextPath}/";

$( document ).ready(function() {

    // 2018. 03. 22. JOY. 관리자 메뉴인 경우 로고 처리
    if ( "${sessionScope.system}" == 'mng' ) {

        if ( sFlag == "gpms" ) {

            $("#gpms-logo").show();

        } else {

            $("#good-logo").show();

        }

    }

    //setInterval(COMMON_UTIL.fn_check_session(), 1000);
    // YYK. setInterval ie9 에서 안먹히는 현상 해결
/*
    setInterval(function(){
    	COMMON_UTIL.fn_check_session()
    } , 1000);
 */


    // 2018. 01. 09. JOY.
    // 바로가기 버튼 액션 추가.
    var sessionRole = "${sessionScope.userinfo.REQ_MENUACC_ROLE}";
    var roleArr = sessionRole.trim().split(",");
    var sessionSys = "${sessionScope.system}";

    $(".logobx").click(function() {

        // 접근 가능 여부 Flag
        var allow = false;

        if ( sessionSys == 'map' ) {

            // 현재 시스템이 '경기도 포장관리시스템' 인 경우
            for ( var i = 0; i < roleArr.length; i++ ) {

                if ( roleArr[i] == 'ROLE_USER_POTHOLE' ) {

                    allow = true;
                    break;

                }

            }

            if ( allow ) {

                // 접근 가능한 경우 페이지 이동
                $(top.location).attr('href', contextPath + "goodmap.do");

            } else {

                // 접근 불가능한 경우 alert
                alert("접근 권한이 없습니다.");
                return;

            }

        } else {

            // 현재 시스템이 '경기도로 모니터링단 시스템' 인 경우
            if ( roleArr.length != 1 ) {

                allow = true;

            }

            if ( allow ) {

                // 접근 가능한 경우 페이지 이동
                $(top.location).attr('href', contextPath + "map.do");

            } else {

                // 접근 불가능한 경우 alert
                alert("접근 권한이 없습니다.");
                return;

            }
        }

    });


    // YYK 마우스 오버스 이미지 전환
    $('.logobx').mouseover(function(e){
        if ( sessionSys == 'map' ) {
            $('.logobx #logobx2').attr('src', 'images/sys2_on.png');
        } else if (sessionSys == 'goodmap' ) {
            $('.logobx #logobx1').attr('src', 'images/sys1_on.png');
        }
    })

    $('.logobx').mouseout(function(e){
        if ( sessionSys == 'map' ) {
            $('.logobx #logobx2').attr('src', 'images/sys2.png');
        } else if (sessionSys == 'goodmap' ) {
            $('.logobx #logobx1').attr('src', 'images/sys1.png');
        }
    })

    // 2018. 01. 09. JOY.
    // 관리자인 경우 '신청건수' 조회 부분 추가.
    var sysGrp = "${sessionScope.userinfo.REQ_USER_GRP}";

    if ( sysGrp == 'ROLE_ADMIN' ) {

        $(".userBx").css("width", "235px");
        $(".userBx a:eq(2)").addClass("br");
        $("#usrCnt").parent().show();

        // 사용자 신청 건수 매핑
        var postData = {"CONFM_AT" : "N"};

        $.ajax({
            url : contextPath + 'api/sysuser/selectApplyUserCnt.do'
            , type : 'post'
            , dataType: 'json'
            , contentType : 'application/json'
            , data : JSON.stringify(postData)
            , success : function(jdata) {

                if ( jdata != undefined && jdata != "" && jdata != null ) {

                    $("#usrCnt").text(jdata);

                } else {

                    $("#usrCnt").text("0");
                }

            }
            , error: function(a, b, msg) {

                $("#usrCnt").text("0");

            }
        });
    }

    // 2018. 05. 10. JOY 사용자 부서 표시
    var postData = {"DEPT_CODE" : "${sessionScope.userinfo.DEPT_CODE}"};

    $.ajax({
        url : contextPath + 'api/sysuser/selectApplyUserDept.do'
        , type : 'post'
        , dataType: 'json'
        , contentType : 'application/json'
        , data : JSON.stringify(postData)
        , success : function(jdata) {

            if ( jdata != null  && jdata != "" && jdata != undefined ) {

                $("#userDept").text(jdata.LOWEST_DEPT_NM + " " + "${sessionScope.userinfo.USER_NM}");
            }

        }
        , error: function(a, b, msg) {
            console.log(msg);
        }
    });


    // 탭 이동 시 팝업 닫기
    $("#gnb").click(function() {

        var wndArr = $.window.getAll();

        for ( var i = 0; i < wndArr.length; i++ ) {

            var wid = wndArr[i].getWindowId();

            if ( !$("#" + wid).find("iframe").contents().find("body").hasClass("thememap")
                    && !$("#" + wid).find("iframe").contents().find("body").hasClass("space-search")
                    && !$("#" + wid).find("iframe").contents().find("body").hasClass("left-tool")) {

                $("#" + wid).find("iframe").contents().find("body").removeClass("cu");
                var wnd = $.window.getWindow(wid).close();

            }
        }
    });

// 상단 탭 클릭(이동)시 기능

    $("#gnb > ul > li").click(function() {
    	// 2018.04.22. YYK. 관할구역 위치보기 초기화 기능
    	gMap.getLayerByName('GAttrLayer').removeAllFeatures();

    	// 컨트롤 초기화
    	$('#mCtrlPan').trigger('click') ;
    });

});

// 시스템 Flag 처리
function fnMap() {

    if ( sFlag == "good" ) {

        COMMON_UTIL.cmMoveUrl('<c:url value="/goodmap.do"/>');

    } else {

        COMMON_UTIL.cmMoveUrl('<c:url value="/map.do"/>');

    }

}

// 시스템 별 로그아웃
function fnLogout() {

    if ( sFlag == "good" ) {

        COMMON_UTIL.cmMoveUrl('<c:url value="/pothole-logoutok.do"/>');

    } else {

        COMMON_UTIL.cmMoveUrl('<c:url value="/logout.do"/>');

    }

}

// 평가상태도 선택시
function fn_evlStats(year) {
	// 레이어 초기화
//     gMap.getLayerByName('GAttrLayer').removeFeatures(gMap.getLayerByName('GAttrLayer').features);
    var oLayer = gMap.getLayerByName('GAttrLayer');
    oLayer.removeAllFeatures();
    oLayer.removeFeatures(oLayer.features);
    var baseLayer = parent.gMap.getLayerByName('baseLayer');
    baseLayer.setVisibility(false);
    var themeLayer = parent.gMap.getLayerByName('themeLayer');
    themeLayer.setVisibility(false);

	var baseLayer = gMap.getLayerByName("baseLayer");
	baseLayer.setVisibility(true);
	baseLayer.mergeNewParams({
        LAYERS: "MV_GNLSTTUS_SECT"   // CELL_SECT
        ,STYLES: "MV_GNLSTTUS_SECT"  // CELL_SECT
    	,CQL_FILTER: " gpci <> '999' and srvy_year =" + year    // gpci가 존재하는 데이터만 출력
    });

	$.each($("#divLayerTool ul li.active"), function() {
        $(this).removeClass('active');
	});


// 	var tables = ["MV_GNLSTTUS_SECT_2019"];
//     var fields = [[]];
//     var values = [[]];

//     // 모든 팝업창 최소화
//     //parent.wWindowHideAll();
//     // 하단 목록 창 내리기
//     parent.bottomClose();

//     var attribute = {
//             attributes : {
//                 fillColor : '#0033ff',
//                 strokeColor : '#0033ff'
//             }
//     };

//     MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, null, attribute);

}

</script>
