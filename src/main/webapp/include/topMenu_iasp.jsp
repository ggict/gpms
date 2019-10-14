<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

        <!-- GPMS TOP MENU START -->
        <div id="header">
            <img src="<c:url value='/images/logo.png'/>" alt="경기도 포장관리시스템" class="logo" />
            <h2 class="hidden">메인메뉴</h2>
            <div id="gnb">
                <ul>
                    <li class="noSub"><a href="#" id="mCtrlLocSearch_iasp" class="menu1 top"><span class="hidden">위치검색</span></a></li>
                    <li class="noSub"><a href="#" class="menu2 top" onclick="COMMON_UTIL.fn_set_subMenu_iasp('sub_srvyEvl', '<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>')"><span class="hidden">도로상태정보</span></a></li>
                    <li class="noSub"><a href="#" class="menu3 top" onclick="COMMON_UTIL.fn_set_subMenu_iasp('sub_srvyPredct', '<c:url value="smdtalaststtus/selectSrvyDtaLastSttusList.do"/>')"><span class="hidden">포트홀정보</span></a></li>


                </ul>
            </div>
            <div class="submenubg"></div>
        </div>


<script type="text/javascript" charset='utf-8'>

$("#mCtrlLocSearch_iasp").bind("click", function(){
	
	COMMON_UTIL.cmWindowOpenIasp('위치검색', contextPath + 'gmap/selectLocation_iasp.do',519, 262, false, null, 'locsearch');

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


</script>
