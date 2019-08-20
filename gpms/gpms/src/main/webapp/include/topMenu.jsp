<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${sessionScope.system == 'mng' }">
        <!-- ADMIN TOP MENU START -->
        <div id="header">
			<img src="<c:url value='/images/logo.png'/>" alt="경기도 포장관리시스템" class="logo" id="gpms-logo" style="display: none;" />
            <img src="<c:url value='/images/logo3.png'/>" alt="경기도로 모니터링 시스템" class="logo" id="good-logo" style="display: none;" />
            <h2 class="hidden">메인메뉴</h2>
            <div id="admingnb">
                <ul>
                    <li>
                        <a href="#" class="menu1 top" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/sysUserList.do"/>')"><span class="hidden">사용자 관리 </span></a>
                        <div class="submenu sub1" id="sub1">
                            <ul>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/sysUserList.do"/>')" class="sm14">사용자 관리</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/applyUserList.do"/>')" class="sm15">사용자 신청 관리</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/dept/selectDeptList.do"/>')" class="sm12">경기도 조직 부서 관리</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/company/companyList.do"/>')" class="sm16">공사 업체 관리</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/sysuser/selectSysUserLog.do"/>')" class="sm17">사용자 접속로그 조회</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/monitor/mntrngMbr.do"/>')" class="sm18">모니터링단원 관리</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="menu2 top" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/menu/selectUserMenuList.do"/>')"><span class="hidden">시스템메뉴 관리</span></a>
                        <div class="submenu sub2" id="sub2">
                            <ul>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/menu/selectUserMenuList.do"/>')" class="sm18">시스템메뉴 관리</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/rolemenu/selectRoleMenuList.do"/>')" class="sm19">시스템메뉴 권한관리</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="noSub"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/clcode/codeList.do"/>')" class="menu3 top"><span class="hidden">코드정보관리</span></a></li>
                    <li>
                        <a href="#" class="menu4 top" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="srvy/srvyDtaEvlFmla.do"/>')"><span class="hidden">수식관리</span></a>
                        <div class="submenu sub3" id="sub3">
                            <ul>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="srvy/srvyDtaEvlFmla.do"/>')" class="sm20">포장상태 평가수식조회</a></li>
                                <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="predctfrmulaidx/selectPredctFrmulaIdxList.do"/>')" class="sm21">공용성예측모델 수식조회</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="noSub"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="mng/notice/selectNoticeList.do"/>')" class="menu5 top"><span class="hidden">공지사항관리</span></a></li>
                </ul>
            </div>
            <div class="submenubg"></div>
            <div class="htbx user-area">
                <h2 class="hidden">사용자정보</h2>
                <div class="user user-area">
                    <a href="#" title="사용자" class="user-area"><span class="hidden user-area">사용자</span></a>
                </div>
            </div>
            <div class="userBx user-area" style="width: 230px;">
                <img src="<c:url value='/images/user_tail.png' />" class="user-area" />
                <div class="userinfo user-area">
                    <span class="user-area" style="height: 20px;">
                        <span id="userDept" style="margin: 0px; width: 150px; float: left;"></span><c:out value="" />
                        <a href="#" onclick="fnLogout();" class="btn_blue user-area">로그아웃</a>
                    </span>
                    <span class="user-area">
                        <a href="#" onclick="COMMON_UTIL.cmWindowOpen('사용자 정보', contextPath +'/api/sysuser/updateUserView.do', 420, 350, true, null, 'center');" class="br"> 사용자 정보</a>
                        <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/applyUserList.do"/>');" style="display: none;">신청<span class="usrCnt" id="usrCnt"></span>건</a>
                        <a href="#" onclick="fnMap();">지도</a>
                    </span>
                </div>
            </div>
        </div>
        <!-- ADMIN TOP MENU END -->
    </c:when>

    <c:when test="${sessionScope.system == 'map' }">
        <!-- GPMS TOP MENU START -->
        <div id="header">
            <img src="<c:url value='/images/logo.png'/>" alt="경기도 포장관리시스템" class="logo" />
            <h2 class="hidden">메인메뉴</h2>
            <div id="gnb">
                <ul>
                    <li class="noSub"><a href="#" class="menu1 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_route', '<c:url value="routeinfo/selectRouteInfoList.do"/>')"><span class="hidden">노선검색</span></a></li>
                    <li class="noSub"><a href="#" class="menu2 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_srvyEvl', '<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>')"><span class="hidden">포장상태 평가</span></a></li>
                    <li class="noSub"><a href="#" class="menu3 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_srvyPredct', '<c:url value="smdtalaststtus/selectSrvyDtaLastSttusList.do"/>')"><span class="hidden">포장상태 예측</span></a></li>
                    <%--/* full windows style 적용    */ --%>
                    <li>
                        <a href="#" class="menu4 top" onclick="fnSelectFirst($(this)); COMMON_UTIL.fn_set_subMenu('sub_repairtargets','<c:url value="rpairtrgetslctn/intro.do"/>')"><span class="hidden">보수대상 선정</span></a>
                        <div class="submenu sub4" id="sub4">
                            <ul>
                                <li class=""><a href="#" class="sm9 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_repairtargets','<c:url value="rpairtrgetslctn/intro.do"/>')">보수대상 선정</a></li>
                                <li><a href="#" class="sm4 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_unptcSenario','<c:url value="rpairmthduntpc/selectRpairMthdUntpcSenario.do"/>', 'repairUntpc')">예산 수준별 시나리오 분석</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="menu5 top" onclick="fnSelectFirst($(this)); COMMON_UTIL.fn_set_subMenu('sub_cntrwk','<c:url value="cntrwk/selectCntrwkList.do"/>')"><span class="hidden">포장공사 이력관리</span></a>
                        <div class="submenu sub5" id="sub5">
                            <ul>
                                <li><a href="#" class="sm7 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_cntrwk', '<c:url value="cntrwk/selectCntrwkList.do"/>')">포장공사 이력관리</a></li>
                                <li><a href="#" class="sm5 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat', '<c:url value="cntrwkdtl/selectCntrwkRoutCntStats.do"/>')">포장공사 통계조회</a></li>
                                <li><a href="#" class="sm4 sub" onclick="COMMON_UTIL.cmWindowOpen('유지보수 실적집계 엑셀출력', contextPath +'/cntrwk/setDownloadReport.do', 390, 150, true, null, 'center');">유지보수 실적집계</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#" class="menu6 top" onclick="fnSelectFirst($(this)); COMMON_UTIL.fn_set_subMenu('sub_stat_route', '<c:url value="stats/selectRouteAllStats.do"/>')"><span class="hidden">통계</span></a>
                        <div class="submenu sub6" id="sub6">
                            <ul>
                                <li><a href="#" class="sm10 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_route', '<c:url value="stats/selectRouteAllStats.do"/>')">노선 현황</a></li>
                                <li><a href="#" class="sm7 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat', '<c:url value="cntrwkdtl/selectCntrwkRoutCntStats.do"/>')">포장공사 이력</a></li>
                                <li><a href="#" class="sm7 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_survey', '<c:url value="stats/selectSurveyAllLenStatsTable.do"/>')">포장상태 조사구간</a></li>
                                <li><a href="#" class="sm11 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_mumm', '<c:url value="mumm/mummRoutCntStats.do"/>')">포장상태 평가</a></li>
                                <li><a href="#" class="sm12 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_stat_predct', '<c:url value="smdtalaststtus/selectSmDtaLastRoutCntStats.do"/>')">포장상태 예측</a></li>
                                <li><a href="#" class="sm13 sub" onclick="COMMON_UTIL.fn_set_subMenu('sub_unptcSenario','<c:url value="rpairmthduntpc/selectRpairMthdUntpcSenario.do"/>', 'statUntpc')">예산 수준별 시나리오 분석</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="noSub"><a href="#" class="menu7 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_reg_srvy', '<c:url value="srvy/selectSrvyExcelList.do"/>')"><span class="hidden">조사자료 관리</span></a></li>
                </ul>
            </div>
            <div class="submenubg"></div>
            <div class="logobx" style='line-height:63px; cursor:pointer;'>
                <img src="<c:url value='images/sys2.png' />" id='logobx2' style='vertical-align:middle; width:90px;' >
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
                        <a href="#" onclick="$('.userBx').hide(); COMMON_UTIL.cmWindowOpen('사용자 정보', contextPath +'/api/sysuser/updateUserView.do', 420, 350, true, null, 'center');" class="br"> 사용자 정보</a>
                        <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/applyUserList.do?sFlag=gpms"/>');" style="display: none;">신청<span class="usrCnt" id="usrCnt"></span>건</a>
                        <a href="#" onclick="COMMON_UTIL.cmMoveUrl('<c:url value="/mng/sysuser/sysUserList.do?sFlag=gpms"/>');">관리자</a>
                    </span>
                </div>
            </div>
        </div>
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

</script>
