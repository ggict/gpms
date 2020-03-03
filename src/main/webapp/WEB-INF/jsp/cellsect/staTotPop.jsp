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
<!-- <title>셀속성편집 </title> -->
<!--
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

//======================================== DEFAULT SETTING ======================================== //
//[ DEFAULT ] div 초기화
var grid = "";
grid += "<table id='gridArea'></table>"
    + "<div id='gridPager' style='width: 100%;'></div>";

//선택한 행번호 변수
var selRow = 0;
//페이지 로딩 초기 설정
$( document ).ready(function() {
    //지도 우측 툴 셀 속성 아이콘 활성화
    parent.$("#editCellInfo").parent().addClass("active");

    var mapReset = function(){
	    parent.gMap.cleanMap(); // 지도 초기화
	    parent.gMap.activeControls("drag");
	    parent.$("#map .left_tool li").removeClass("active");
	    parent.$("#mCtrlPan").parent().addClass("active");
    }
    mapReset();

    // 하단 팝업 초기화
    parent.bottomHide();

    $(".selbtn").click(function () {
        //
        // Button Toggle
        $(this).parent().siblings('li').removeClass('on');

        // $(this) id, class, num of class
        var id = $(this).attr("id");
        var classArr = $(this).parent().attr("class");

        // 2단계 버튼 점형 선택
        if ( id == "BTN_SECT_POINT") {

        	//gMap.getLayerByName('GPthEditLayer').removeAllFeatures();
            //gMap.getLayerByName('GOverlapLayer').removeAllFeatures();

            option = {};
            option = {
                        iframe : window,
                        callback : "fnCheckFeatures",
                        clearMap : false
            };
            MAP.CONTROL.set_option(option);

            parent.$("#STA_TOT_GRS80_50").parent().addClass("on");

            parent.gMap.activeControls("selStaTotPopEdit");

        }

    });
});

/**
 * DWG 파일 다운로드
 * dwg_type - 01: 단위도, 02: 지하시설물, 03: 용지도, 04: 구간도, 05: 지형고시도
 */
function fn_road_dwg_down(dwg_type) {
	$("#DWG_TYPE").val(dwg_type);

    COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cellsect/staTotDwgDownloadFile.do'/>", "");
}

</script>
</head>
<body id="wrap" class="right-tool">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>

<form id="frm" name="frm" style="display: none;">
    <input type="hidden" id="ROAD_NO" name="ROAD_NO" value=""/>
    <input type="hidden" id="SECT" name="SECT" value=""/>
    <input type="hidden" id="SECT_ST" name="SECT_ST" value=""/>
    <input type="hidden" id="DWG_TYPE" name="DWG_TYPE" value=""/>
</form>

<!-- 필수 파라메터(END) -->
<%-- <form id="frm" name="frm" method="post" action=""> --%>
    <div class="content">
        <div style="width:600px">
            <ul class="stepbx af">
                <li style="width:38%">
                    <div class="stbx">
                    	<h4><span class="step">단계1</span>도로대장(점) 선택</h4>
                        <div style="height:100px">
                            <ul class="tblst selbx">
                                <li style="width:100%" class="tc">
                                    <a class="selbtn btncursor" id="BTN_SECT_POINT">
                                        <span class="roundbx"><img src="../images/ic_shape1.png" alt="점" /></span>
                                        <span class="dpb">선택 </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div id="staTotDiv">
                        <a href="#" onclick="fn_road_dwg_down('01');" class="btn pri tc">단위도<br/>(P)</a>
                        <a href="#" onclick="fn_road_dwg_down('02');" class="btn pri tc">지하시설물<br/>(U)</a>
                        <a href="#" onclick="fn_road_dwg_down('03');" class="btn pri tc">용지도<br/>(Y)</a>
                        <a href="#" onclick="fn_road_dwg_down('04');" class="btn pri tc">구간도<br/>(CONT)</a>
                        <a href="#" onclick="fn_road_dwg_down('05');" class="btn pri tc">지형고시도<br/>(TOP)</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>