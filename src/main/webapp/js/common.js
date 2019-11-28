// JOY. 2018. 05. 01. 공간검색 시 geometry
var spaceGeo = null;

// 위치통합검색 마커 배열
var markersLoc = [];
// JOY. 2017. 11. 23. 페이지 새로고침 시 경고창 허용 여부 Flag
// 0 : 경고창 O, X : 경고창 X
var check = true;
// JOY. 2017. 11. 22. 테마지도 팝업 최초 접근 확인 Flag
// 0 : 최초, 1 : 최초 아님
var themeFlag = 0;
//181123 wijy 테마지도 selectbox 설정값 저장
//0 : 10m Cell 설정값
//1 : Section Cell 설정값
var themeSelectValue = ["", ""];

// JOY. 2017. 11. 28. 통계 검색기능 Flag
// 0 : 표, 1 : 차트
var schFlag = 0;

var rshInfoCnt = 0;

// 2018.04.18. YYK. 브라우저 정보
var chkBrowser = get_version_of_IE ();



$(document).ready(function () {
    resize();
    $(window).resize(function () {
        resize();
    });
    style();
    snbacc();
    $("#gnb > ul").accessibleDropDown();
    $("#admingnb > ul").accessibleDropDown();

    $("html").click(function(e){

        if ( e.target.className.indexOf("user-area") < 0 ) {

            $(".user a").removeClass("on");
            $('.userBx').hide();
        }

    });

    $('.user a').click(function (e) {
        $(this).toggleClass('on')
        if ($(this).hasClass('on')) {
            $('.userBx').show();

        }
        else {
            $('.userBx').hide();
        }
    });

    tabCont("btab01");
    bottomClose();


    $('.lst li').click(function (e) {
        if ($(this).hasClass('active')) {

        } else {
            $('.lst li').removeClass('active');
            $(this).addClass("active");
        }
    });

    //프로그래스바
    $("#dvProgress").dialog({
        width : 170,
        height : 'auto',
        modal : true,
        open: function (event, ui) {
            $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        }
    });
    $("#dvProgress").dialog("close");

    // 테마지도 레이어 on/off
    $('.onoff').click(function (e) {
        if ($(this).hasClass('off')) {
            $(this).removeClass('off').toggleClass('on');
            $(this).attr("src", $(this).attr("src").replace("_off.png", "_on.png"));
            $(this).attr("alt", $(this).attr("alt").replace("off", "on"));
        } else {
            $(this).removeClass('on').toggleClass('off');
            $(this).attr("src", $(this).attr("src").replace("_on.png", "_off.png"));
            $(this).attr("alt", $(this).attr("alt").replace("on", "off"));
        }
    });

});

// 테마지도 레이어 on/off function
function fnOnOffAction(button, flag) {

    if (flag == 'on') {
        button.removeClass('off').toggleClass('on');
        button.attr("src", button.attr("src").replace("_off.png", "_on.png"));
        button.attr("alt", button.attr("alt").replace("off", "on"));
    } else {
        button.removeClass('on').toggleClass('off');
        button.attr("src", button.attr("src").replace("_on.png", "_off.png"));
        button.attr("alt", button.attr("alt").replace("on", "off"));
    }

}


//gnb
$.fn.accessibleDropDown = function () {

    // 2017. 11. 08. JOY
    // 서브메뉴 선택 시 active
    $(".sub").click(function() {

        $(this).parent().siblings().removeClass("active");
        $(this).parent().addClass("active");

        fnWindowPopupClose();

    });

    // 2017. 11. 08. JOY
    // topmenu 영역 벗어났을 시,
    // menuon 상태인 메뉴가 있으면 그 메뉴 선택. (서브메뉴 복구)
    $('#gnb > ul').mouseleave(function() {

        var activeMenu = $('#gnb > ul > li.menuon');

        if ( activeMenu.length > 0 ) {

            activeMenu.siblings().removeClass('menuon');
            activeMenu.siblings().removeClass('active');
            activeMenu.addClass('menuon');
            activeMenu.addClass('active');

            $('#gnb div.submenu').hide();

            if ( activeMenu.hasClass('noSub') ) {

                // 경기도로 모니터링단 시스템 - 지도메뉴 위치이동
                if ( activeMenu.parent().parent().hasClass("good-header") && $(".top-tool").css("top") != "10px" ) {

                    $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                    $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                }

                $('.submenubg').hide();

            } else {

                // 경기도로 모니터링단 시스템 - 지도메뉴 위치이동
                if ( activeMenu.parent().parent().hasClass("good-header") && $(".top-tool").css("top") != "43px" ) {

                    $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                    $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                }

                activeMenu.find("div.submenu").show();
                $('.submenubg').show();
            }

        } else {

            // 경기도로 모니터링단 시스템 - 지도메뉴 위치이동
            if ( $(this).parent().hasClass("good-header") && $(".top-tool").css("top") != "10px" ) {

                $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) - 33 ) + "px");
            }
        }

    });

    $('#gnb > ul > li').mouseenter(function(){
        $(this).addClass('active');

        $('#gnb div.submenu').hide();

        if ( $(this).hasClass('noSub') ) {

            // 경기도로 모니터링단 시스템 - 지도메뉴 위치이동
            if ( $(this).parent().parent().hasClass("good-header") && $(".top-tool").css("top") != "10px" ) {

                $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) - 33 ) + "px");
                $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) - 33 ) + "px");
            }

            $('.submenubg').hide();

        } else {

            // 경기도로 모니터링단 시스템 - 지도메뉴 위치이동
            if ( $(this).parent().parent().hasClass("good-header") && $(".top-tool").css("top") != "43px" ) {

                $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) + 33 ) + "px");
                $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) + 33 ) + "px");
            }

            $(this).find("div.submenu").show();
            $('.submenubg').show();

        }

    }).mouseleave(function(){
        if ( !$(this).hasClass('menuon') ) {

            $(this).removeClass('active');
            $(this).removeClass('menuon')
            $('.submenu').hide();
            $('.submenubg').hide();
        }
    });

    $('#gnb > ul > li').click(function() {

        $(this).siblings().removeClass('menuon');
        $(this).siblings().removeClass('active');
        $(this).siblings().find("li").removeClass('active');
        $(this).addClass('menuon');

        $('#gnb div.submenu').hide();

        if ( $(this).hasClass('noSub') ) {

            $('.submenubg').hide();

        } else {

            $(this).find("div.submenu").show();
            $('.submenubg').show();

        }

        fnWindowPopupClose();
        
        //레이어 관리 show/hide
        var layerBoxHiddenMenuList = ['menu4', 'menu6'];
        var length = layerBoxHiddenMenuList.length;
        for(var i=0; i<length; i++){
        	var menu = layerBoxHiddenMenuList[i];
        	var isMenuClass = $(this).find('a').hasClass(menu);
        	if(isMenuClass){
        		$("#divLayerTool").dialog('close');
        		break;
        	}else{
        		$("#divLayerTool").dialog('open');
        	}
        }

    });

    // 새로고침/초기 로그인 시 랜덤으로 topmenu에 focus되는 경우가 있어서 focus 시 select 되는 것은 보류.
    /*$("#gnb > ul > li > a").focus(function() {
        $(this).parents('li').addClass('active');
        $('#gnb li.active div').show();
        if($(this).hasClass('top')){
            $('.submenu').not($(this).parent("li").find(".submenu")).hide();
        }

        if(!$(this).parent("li").hasClass('noSub')){
            $('.submenubg').show();
        }

    });

    $('#gnb > ul > li > a').focusout(function() {
        $(this).parents('li').removeClass('active');
        //$('.submenubg').hide(); //일부 submenu가 show 상태 이므로 submenubg hide되면 안됨

            if($(this).hasClass('top')){
        $('.submenu').not($(this).parent("li").find(".submenu")).hide();
            }else{
        $('.submenu').not($(this).parents('.submenu')).hide();
            }

    });*/

    // 2017. 11. 09. JOY
    // topmenu 영역 벗어났을 시,
    // menuon 상태인 메뉴가 있으면 그 메뉴 선택. (서브메뉴 복구)
    $('#admingnb > ul').mouseleave(function() {

        var activeMenu = $('#admingnb > ul > li.menuon');

        if ( activeMenu.length > 0 ) {

            activeMenu.siblings().removeClass('menuon');
            activeMenu.siblings().removeClass('active');
            activeMenu.addClass('menuon');
            activeMenu.addClass('active');

            $('#admingnb div.submenu').hide();

            if ( activeMenu.hasClass('noSub') ) {

                $('.submenubg').hide();

            } else {

                activeMenu.find("div.submenu").show();
                $('.submenubg').show();
            }

        }

    });

    $('#admingnb > ul > li').mouseenter(function(){
        $(this).addClass('active');

        $('#admingnb div.submenu').hide();

        if ( $(this).hasClass('noSub') ) {

            $('.submenubg').hide();

        } else {

            $(this).find("div.submenu").show();
            $('.submenubg').show();
        }

    }).mouseleave(function(){
        if ( !$(this).hasClass('menuon') ) {
            $(this).removeClass('active');
            $(this).removeClass('menuon')

            $('.submenu').hide();
            $('.submenubg').hide();
        }
    });

    /*$("#admingnb > ul > li > a").focus(function () {
        $(this).parents('li').addClass('active');
        $('#admingnb li.active div').show();
        if ($(this).hasClass('top')) {
            $('.submenu').not($(this).parent("li").find(".submenu")).hide();
        }

        if(!$(this).parent("li").hasClass('noSub')){
            $('.submenubg').show();
        }
    });

    $('#admingnb > ul > li > a').focusout(function () {
        $(this).parents('li').removeClass('active');
        //$('.submenubg').hide(); //일부 submenu가 show 상태 이므로 submenubg hide되면 안됨

        if ($(this).hasClass('top')) {
            $('.submenu').not($(this).parent("li").find(".submenu")).hide();
        } else {
            $('.submenu').not($(this).parents('.submenu')).hide();
        }

    });*/

    $('li:last-child > div > ul > li > a:last ').focusout(function(){
        $('.submenu').hide();
        $('.submenubg').hide();
    });

    $('div.submenu > ul > li:last-child > a').addClass('bgnone');

}

// 2017. 11. 09. JOY
// 관리자 메뉴 select 함수
function fnAdminMenuSelect(menu, subidx) {

    var menu = $("#admingnb ." + menu).parent();
    menu.addClass("menuon");
    menu.addClass("active");

    $('#admingnb div.submenu').hide();

    if ( menu.hasClass('noSub') ) {

        $('.submenubg').hide();

    } else {

        menu.find("li").eq(subidx).addClass("active");
        menu.find("div.submenu").show();
        $('.submenubg').show();
    }

}

// 2017. 11. 09. JOY
// topmenu 선택 시 첫 번째 서브메뉴 활성화
function fnSelectFirst(menu) {

    menu.parent().find('li').removeClass('active');
    menu.parent().find('li').eq(0).addClass('active');

}

// 2017. 11. 09. JOY
// 메뉴 선택 시 전체 팝업 닫기 (right-tool, left-tool, research)
function fnWindowPopupClose() {

    var wndpop = $.window.getAll();
    var len = wndpop.length;

    for ( var i = len - 1; i >= 0; i-- ) {

        var wndid = wndpop[i].getWindowId();

        if ( $("#" + wndid).find("iframe").contents().find("body").hasClass("right-tool")
                || $("#" + wndid).find("iframe").contents().find("body").hasClass("left-tool")
                || $("#" + wndid).find("iframe").contents().find("body").hasClass("research") ) {

            if ( $("#" + wndid).find("iframe").contents().find("body").hasClass("research") ) {

                rshInfoCnt--;
                $(".selecttool").parent("li").removeClass("active");
                $("#mCtrlPan").parent().addClass("active");
                gMap.cleanMap();
                gMap.activeControls("drag");
                bottomClose();

            }

            wndpop[i].close();

        }

    }

}

function snbacc() {
    $("dd:not(:first)").css("display", "none");
    $("dt:first").addClass("selected");
    $("dl dt").click(function () {
        if ($("+dd", this).css("display") == "none") {
            $("dd").slideUp("fast");
            $("+dd", this).slideDown("fast");
            $("dt").removeClass("selected");
            $(this).addClass("selected");
        }
    }).mouseover(function () {
        $(this).addClass("over");
    }).mouseout(function () {
        $(this).removeClass("over");
    });
}





/* 탭 메뉴
---------------------------------------------------------*/
function initTabMenu(tabContainerID, useImgRoll, useOnMouse) {
    var tabContainer = document.getElementById(tabContainerID);
    var tabAnchor = tabContainer.getElementsByTagName("a");
    var i = 0;
    var imgRoll = 0;
    var onMouse = 0;

    for (i = 0; i < tabAnchor.length; i++) {
        if (tabAnchor.item(i).className == "tab")
            thismenu = tabAnchor.item(i);
        else
            continue;

        thismenu.container = tabContainer;
        thismenu.targetEl = document.getElementById(tabAnchor.item(i).href.split("#")[1]);
        thismenu.targetEl.style.display = "none";
        thismenu.imgEl = thismenu.getElementsByTagName("img").item(0);

        /* tab menu event */
        onMouse = useOnMouse;
        if (onMouse) {
            thismenu.onmouseover = thismenu.onfocus = function tabMenuClick() {
                currentmenu = this.container.current;
                if (currentmenu == this)
                    return false;

                if (currentmenu) {
                    currentmenu.targetEl.style.display = "none";
                    if (currentmenu.imgEl) {
                        currentmenu.imgEl.src = currentmenu.imgEl.src.replace("_on.gif", ".gif");
                    } else {
                        currentmenu.className = currentmenu.className.replace(" on", "");
                    }
                }
                this.targetEl.style.display = "";
                if (this.imgEl) {
                    this.imgEl.src = this.imgEl.src.replace("_on.gif", ".gif");
                    this.imgEl.src = this.imgEl.src.replace(".gif", "_on.gif");
                } else {
                    this.className += " on";
                }
                this.container.current = this;

                return false;
            }
        }
        else {
            thismenu.onclick = function tabMenuClick() {
                currentmenu = this.container.current;
                if (currentmenu == this)
                    return false;

                if (currentmenu) {
                    currentmenu.targetEl.style.display = "none";
                    if (currentmenu.imgEl) {
                        currentmenu.imgEl.src = currentmenu.imgEl.src.replace("_on.gif", ".gif");
                    } else {
                        currentmenu.className = currentmenu.className.replace(" on", "");
                    }
                }
                this.targetEl.style.display = "";
                if (this.imgEl) {
                    this.imgEl.src = this.imgEl.src.replace("_on.gif", ".gif");
                    this.imgEl.src = this.imgEl.src.replace(".gif", "_on.gif");
                } else {
                    this.className += " on";
                }
                this.container.current = this;

                return false;
            }

            /* tab img rollover */
            imgRoll = useImgRoll;
            if (imgRoll) {
                thismenu.onmouseover = thismenu.onfocus = function () {
                    tabImg = this.getElementsByTagName("img").item(0);
                    tabImg.src = tabImg.src.replace("_on.gif", ".gif");
                    tabImg.src = tabImg.src.replace(".gif", "_on.gif");
                }
                thismenu.onmouseout = thismenu.onblur = function () {
                    currentmenu = this.container.current;
                    if (currentmenu == this)
                        return false;

                    tabImg = this.getElementsByTagName("img").item(0);
                    tabImg.src = tabImg.src.replace("_on.gif", ".gif");
                }
            }
            /* end tab img rollover */
        }
        /* end tab menu event */

        if (!thismenu.container.first)
            thismenu.container.first = thismenu;
    }
    if (tabContainer.first) {
        if (onMouse)
            tabContainer.first.onmouseover();
        else
            tabContainer.first.onclick();
    }
}



function style() {
    //$('.tblist thead th:last-child, .tblist tbody td:last-child, .tblist tfoot td:last-child').addClass('brnone');
    //$('.tblist2 thead th:last-child, .tblist2 tbody td:last-child, .tblist2 tfoot td:last-child').addClass('brnone');
    //$('.tblist3 thead th:last-child, .tblist3 tbody td:last-child, .tblist3 tfoot td:last-child').addClass('brnone');
    //$('.wm_tblist thead th:last-child, .wm_tblist tbody td:last-child').addClass('brnone');
    //$('.tbview tbody th:last-child, .tbview tbody td:last-child').addClass('brnone');
    //$('.tbview tbody tr:last-child th, .tbview tbody tr:last-child td').addClass('btcolor');
    //$('.popTb tbody tr:last-child th, .popTb tbody tr:last-child td').addClass('btcolor');


    $('.resultbx ul li:last-child').addClass('brnone');

}



function leftOpen() {
    $('#left_tab').css("left", "0px");
    $('#leftCloseBt').removeClass('hidden');
    $('#leftOpenBt').addClass('hidden');
    $('#left').css("width", "322px");
}
function leftClose() {
    $('#left_tab').css("left", "-312px");
    $('#leftCloseBt').addClass('hidden');
    $('#leftOpenBt').removeClass('hidden');
    $('#left').css("width", "12px");
}


function bottomOpen() {
    $('#leftCloseBt').removeClass('hidden');
    $('#leftOpenBt').addClass('hidden');
    $('#bottom').css("height", "346px");
    $('#stats').addClass('hidden');
    /* full windows style 적용    */
    $('#repairtargets').addClass('hidden');
    $('#unptcSenario').addClass('hidden');

    $("#btab01").show();
    $("#detail_integrated").hide();

    wWindowsResizeMinHeight(346);
}
function bottomClose() {
    $('#leftCloseBt').addClass('hidden');
    $('#leftOpenBt').removeClass('hidden');
    $('#bottom').css("height", "44px");
    wWindowsResizeMinHeight(44);
}
function goodStatsOpen() {
    $('#leftCloseSt').removeClass('hidden');
    $('#leftOpenSt').addClass('hidden');
    $('#good_stats').removeClass('hidden');
    $('#good_stats').css("top", "97px");
    /* full windows style 적용    */
    $('#bottom').css("height", "0px");
    wWindowsResizeMinHeight(0);
}
function goodStatsClose() {
    $('#leftCloseSt').addClass('hidden');
    $('#leftOpenSt').removeClass('hidden');
    $('#good_stats').css("top", $(window).height()-55);
    wWindowsResizeMinHeight(50);
}
function statsOpen() {
    $('#leftCloseSt').removeClass('hidden');
    $('#leftOpenSt').addClass('hidden');
    $('#stats').removeClass('hidden');
    $('#stats').css("top", "97px");
    /* full windows style 적용    */
    $('#repairtargets').addClass('hidden');
    $('#unptcSenario').addClass('hidden');
    $('#bottom').css("height", "0px");
    wWindowsResizeMinHeight(0);
    $("#divLayerTool").dialog('close');
}
function statsClose() {
    $('#leftCloseSt').addClass('hidden');
    $('#leftOpenSt').removeClass('hidden');
    $('#stats').css("top", $(window).height()-44);
    wWindowsResizeMinHeight(50);
    $("#divLayerTool").dialog('open');
}
/* full windows style 적용    */
function repairtargetsOpen() {
    $('#leftCloseRt').removeClass('hidden');
    $('#leftOpenRt').addClass('hidden');
    $('#repairtargets').css("display", "");


    $('#repairtargets').removeClass('hidden');
    $('#repairtargets').css("top", "97px");
    $('#stats').addClass('hidden');
    $('#unptcSenario').addClass('hidden');
    $('#bottom').css("height", "0px");
    wWindowsResizeMinHeight(0);
    initRepairTargets();
}
function repairtargetsClose() {
    $('#leftCloseRt').addClass('hidden');
    $('#leftOpenRt').removeClass('hidden');
    $('#repairtargets').css("top", $(window).height()-44);
    $('#repairtargets').css("display", "none");

    wWindowsResizeMinHeight(50);
}
function repairtargetsShow() {
    $('#leftCloseRt').removeClass('hidden');
    $('#leftOpenRt').addClass('hidden');
    $('#repairtargets').css("display", "");
    $('#repairtargets').removeClass('hidden');
    $('#repairtargets').css("top", "97px");
    wWindowsResizeMinHeight(0);
    $("#divLayerTool").dialog('close');

}
function repairtargetsHideBottom() {
    $('#leftCloseRt').addClass('hidden');
    $('#leftOpenRt').removeClass('hidden');
    //$('#repairtargets').css("height", "50px");
    $('#repairtargets').css("top", $(window).height()-44);
    wWindowsResizeMinHeight(50);
    $("#divLayerTool").dialog('open');
}

function unptcSenarioOpen(type) {
    $('#leftCloseUs').removeClass('hidden');
    $('#leftOpenUs').addClass('hidden');
    $('#unptcSenario').css("display", "");


    $('#unptcSenario').removeClass('hidden');
    $('#unptcSenario').css("top", "97px");
    $('#stats').addClass('hidden');
    $('#repairtargets').addClass('hidden');
    $('#bottom').css("height", "0px");
    wWindowsResizeMinHeight(0);
    COMMON_UTIL.untpcMenuUrlContent("rpairmthduntpc/selectRpairMthdUntpcSenario.do?type=" + type);


}
function unptcSenarioClose() {
    $('#leftCloseUs').addClass('hidden');
    $('#leftOpenUs').removeClass('hidden');
    $('#unptcSenario').css("top", $(window).height()-55);
    $('#unptcSenario').css("display", "none");

    wWindowsResizeMinHeight(50);
}

function unptcSenarioShow() {
    $('#leftCloseUs').removeClass('hidden');
    $('#leftOpenUs').addClass('hidden');
    $('#unptcSenario').css("display", "");


    $('#unptcSenario').removeClass('hidden');
    $('#unptcSenario').css("top", "97px");

    wWindowsResizeMinHeight(0);

}
function unptcSenarioHideBtn() {
    $('#leftCloseUs').addClass('hidden');
    $('#leftOpenUs').removeClass('hidden');
    $('#unptcSenario').css("top", $(window).height()-55);
    wWindowsResizeMinHeight(50);
}


function tabCont(tabId, usebg) {
    var chkid = tabId;
    var chkbg = usebg;
    if (chkbg == 0) {

        $('#' + chkid + ' .ctab_wrap > div.tabcont').hide();
        $('#' + chkid + ' .ctab_wrap > div.tabcont:first').show();
    }
    else {
        $('#' + chkid + ' .tab_wrap > div.tabcont').hide();
        $('#' + chkid + ' .tab_wrap > div.tabcont:first').show();
    }

    $('#' + chkid + ' .btab_menu li:first').addClass('sel');
    $('#' + chkid + ' .ctab_menu li:first').addClass('sel');

    $('#' + chkid + ' .ctab_menu li a').click(function () {

        var currentTab = $(this).attr('href');

        if ($(this).parent().hasClass('sel')) {
            //$('.tab_menu li').removeClass('sel');
            //$(currentTab).hide();
        }
        else {
            $('#' + chkid + ' .ctab_wrap > div.tabcont').hide();
            $('#' + chkid + ' .ctab_menu li').removeClass('sel');
            $(this).parent().addClass('sel');
            $(currentTab).show();
        }

        return false;
    });

    $('#' + chkid + ' .btab_menu li a').click(function () {
        var currentTab = $(this).attr('href');

        if ($(this).parent().hasClass('sel')) {
            //$('.tab_menu li').removeClass('sel');
            //$(currentTab).hide();
        }
        else {
            $('#' + chkid + ' .tab_wrap > div.tabcont').hide();
            $('#' + chkid + ' .btab_menu li').removeClass('sel');
            $(this).parent().addClass('sel');
            $(currentTab).show();
        }

        return false;
    });

}


/* 지도팝업 리사이즈
-----------------------------------------------*/
function resize() {
    var doc_height = $(window).height();
    var leftcont_height;
    if (doc_height < 785) { leftcont_height = 785 - 130; }
    else { leftcont_height = doc_height - 130; }

    $('.leftBtArea').css("top", (doc_height / 2) - 22 + "px");
    $('.stmenuarea').css("height", $('#stats').height() + "px");
    /* full windows style 적용    */
    $('.srtmenuarea').css("height", $('#repairtargets').height() + "px");

    // 통계 페이지 resize
    if($("#stats").hasClass("active") && $("#leftCloseSt").hasClass("hidden")){
        $('#stats').css("top", $(window).height()-55);
    }else if(!$("#stats").hasClass("active")){
        $('#stats').css("top", $(window).height());
    }

    // 포트홀 통계 페이지 resize
    if($("#good_stats").hasClass("active") && $("#leftCloseSt").hasClass("hidden")){
        $('#good_stats').css("top", $(window).height()-55);
    }else if(!$("#good_stats").hasClass("active")){
        $('#good_stats').css("top", $(window).height());
    }

    // 보수공법 페이지 resize
    if($("#repairtargets").hasClass("active") && $("#leftCloseRt").hasClass("hidden")){
        $('#repairtargets').css("top", $(window).height()-55);
    }else if(!$("#repairtargets").hasClass("active")){
        $('#repairtargets').css("top", $(window).height());
    }

}


/* 지도 속성 편집창 리사이즈 추가(지도>셀속성편집)
-----------------------------------------------*/
function fnPopupResize(h) {
    // change popup size
    var wndId = $("#wnd_id").val();
    parent.$("#" + wndId).animate({ height : h }, 500);
    parent.$("#" + wndId).find("iframe").animate({ height : h-30 }, 400);
}

/* 지도 load 후 하단 메뉴 hide 추가
-----------------------------------------------*/
function bottomHide() {
    $('#leftCloseBt').addClass('hidden');
    $('#leftOpenBt').removeClass('hidden');
    $('#bottom').css("height", "0px");
    $('#stats').css("top", $(window).height());
    /* full windows style 적용    */
    $('#repairtargets').css("top", $(window).height());
}

/* pth_rg_no 값 변환 000000000-000 -> 000000000000 형태
-----------------------------------------------*/
function pad(n, width) {
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}

/* null 또는 undefined 일 경우 '' 빈값 넣기
-----------------------------------------------*/
function nullToEmpty(value) {
	if ( value == null || value == undefined ){
		return '';
	}
	else {
		return value;
	}
}

/* pth_rg_no 값 변환 000000000-000 -> 000000000000 형태
-----------------------------------------------*/
function Lpad(n, width) {
    n = n + '';
    return '-' + parseInt(n.substring(width));
}

// JOY. LPAD <-> CONVERT
function cvtPthRgNo(pthRgNo) {

    if ( pthRgNo.length == 14 ) {

        return pthRgNo.substr(0, 8) + '-' + parseInt(pthRgNo.substr(8));

    } else {

        var no = pthRgNo.substr(9);

        while ( no.length < 6 ) {

            no = '0' + no;

        }

        return pthRgNo.substr(0, 8) + '' + no;

    }

}


//YYK. 포트홀등록번호 조건검색의 경우
function searchPthRgNo(pthRgNo) {

	// no가 있고, -가 있는 경우
	if ( pthRgNo != '' && pthRgNo.indexOf('-') != -1 ) {

		// '-'가 맨앞이나 뒤에 있는 경우 : '-' 삭제
    	if ( pthRgNo.substr( -1, 1 ) == '-' || pthRgNo.substr( 0, 1 ) == '-' ){
    		pthRgNo = pthRgNo.replace('-', '');
    		return pthRgNo ;
    	}

    	// '-'가 중간에 있는 경우 :
    	else {
    		var arr = [];
    		arr = pthRgNo.split('-');

    		var pth_end = '';
    		pth_end = arr[1];

    		while ( pth_end.length < 6 ){
    			pth_end = '0' + pth_end;
    		}
    		return arr[0] + pth_end;
    	}
    }
	else {
		return pthRgNo ;
	}
}



// YYK. ie 11 ~ 11이하 버전 구분 함수
function get_version_of_IE () {

	var word;

	var agent = navigator.userAgent.toLowerCase();

	// IE old version ( IE 10 or Lower )
	if ( navigator.appName == "Microsoft Internet Explorer" ){
		word = "msie";
	}

	 // IE 11
	else if ( agent.search( "trident" ) > -1 ) {
		word = "msie11";
    }

	// Microsoft Edge
	else if ( agent.search( "edge/" ) > -1 ) {
		word = "msieEdge";
	}

	// 그외, IE가 아니라면 ( If it's not IE or Edge )
	else {
		return -1;
	}

	//var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" );
	//if (  reg.exec( agent ) != null  ) return parseFloat( RegExp.$1 + RegExp.$2 );

	return word;
}


// YYK. 포트홀 신고관리 active
function sel_pothole_sttemnt(){

	// 통계가 선택된 상태인 경우 CSS도 수정
	if (parent.$('#goodMap .good-header > ul > li:eq(0)').hasClass('active')){
	    $('.submenubg').hide();
	    $('#gnb div.submenu').hide();

	    $(".left_tool").css("top", (($(".left_tool").css("top").substr( 0,  $(".left_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    $(".top-tool").css("top", (($(".top-tool").css("top").substr( 0,  $(".top-tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    //$(".right_tool").css("top", (($(".right_tool").css("top").substr( 0,  $(".right_tool").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    $(".select_map").css("top", (($(".select_map").css("top").substr( 0,  $(".select_map").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    $("#snbacc").css("top", (($("#snbacc").css("top").substr( 0,  $("#snbacc").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    $(".space-search").css("top", (($(".space-search").css("top").substr( 0,  $(".space-search").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	    $(".pothole-regi").css("top", (($(".pothole-regi").css("top").substr( 0,  $(".pothole-regi").css("top").length - 2 ) * 1 ) - 33 ) + "px");
	}

	parent.$('#goodMap .good-header > ul > li:eq(1)').siblings().removeClass('menuon');
	parent.$('#goodMap .good-header > ul > li:eq(1)').siblings().removeClass('active');
	parent.$('#goodMap .good-header > ul > li:eq(1)').siblings().find("li").removeClass('active');
	parent.$('#goodMap .good-header > ul > li:eq(1)').addClass('menuon active');

    fnWindowPopupClose();
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