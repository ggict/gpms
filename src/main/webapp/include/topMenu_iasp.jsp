<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

        <!-- GPMS TOP MENU START -->
        <div id="header">
            <img src="<c:url value='/images/logo.png'/>" alt="경기도 포장관리시스템" class="logo" />
            <h2 class="hidden">메인메뉴</h2>
            <div id="gnb">
                <ul>
                    <li class="noSub"><a href="#" class="menu1 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_route', '<c:url value="routeinfo/selectRouteInfoList.do"/>')"><span class="hidden">위치검색</span></a></li>
                    <li class="noSub"><a href="#" class="menu2 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_srvyEvl', '<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>')"><span class="hidden">도로상태정보</span></a></li>
                    <li class="noSub"><a href="#" class="menu3 top" onclick="COMMON_UTIL.fn_set_subMenu('sub_srvyPredct', '<c:url value="smdtalaststtus/selectSrvyDtaLastSttusList.do"/>')"><span class="hidden">포트홀정보</span></a></li>


                </ul>
            </div>
            <div class="submenubg"></div>
        </div>


<script type="text/javascript" charset='utf-8'>
//JOY. 2018. 03. 13. 시스템 구분 Flag
/* var sFlag = "${sessionScope.sFlag}";

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

} */

</script>
