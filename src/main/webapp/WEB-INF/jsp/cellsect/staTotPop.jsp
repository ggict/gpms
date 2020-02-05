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

</script>
</head>
<body id="wrap" class="right-tool">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
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
            </ul>
        </div>
    </div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>