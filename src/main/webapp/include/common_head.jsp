<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<title><c:out value="${screen_title}"/></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jstree/default/style.min.css'/>"/>
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/reset.css'/>"/> --%>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common2.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/jquery.ui.custom.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/jquery.window.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/ui.jqgrid.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/magnific-popup.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/button.css'/>"/>


<%-- <script src="<c:url value='/extLib/jquery/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/extLib/jquery/jquery-ui.min.js'/>"></script> --%>
<script src="<c:url value='/extLib/jquery/jquery.ginno.js'/>" charset='utf-8' ></script>
<script src="<c:url value='/extLib/jquery/jquery.ui.ginno.js'/>" charset='utf-8' ></script>

<script src="<c:url value='/extLib/jqgrid/i18n/grid.locale-kr.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/jqgrid/jquery.jqGrid.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/jquery/jquery.magnific-popup.min.js'/>" charset='utf-8'></script>

<script src="<c:url value='/extLib/jquery/placeholders.jquery.min.js'/>" charset='utf-8'></script>

<script src="<c:url value='/extLib/plugin/jquery.mask.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/plugin/jquery.form.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/plugin/jquery.mThumbnailScroller.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/plugin/jquery.multifile.min.js'/>" charset='utf-8'></script>
<script src="<c:url value='/extLib/plugin/spin.js'/>" charset='utf-8'></script>

<script src="<c:url value='/js/date.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/common/lang.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/common/util.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/common/file.js'/>" charset='utf-8'></script>
<%-- <script src="<c:url value='/js/common.lang.js'/>"></script>
<script src="<c:url value='/js/common.util.js'/>"></script>
<script src="<c:url value='/js/common.file.js'/>"></script> --%>

<script src="<c:url value='/extLib/window/jquery.window.js'/>" charset='utf-8'></script>
<%-- <script src="<c:url value='/extLib/echarts/echarts.min.js'/>"></script> --%>
<script src="<c:url value='/extLib/window.util.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/iasp.js'/>" charset='utf-8'></script>

<!-- ì§ë ê´ë ¨ ì¤í¬ë¦½í¸ -->
<script src="<c:url value='/js/map/config.js'/>" charset='utf-8'></script>

<script type="text/javascript" src="<c:url value='/extLib/proj4js/proj4js.js'/>" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/proj4js/defs.js'/>" charset='utf-8'></script>

<!-- =========openlayers add=============== -->
<script type="text/javascript" src="<c:url value='https://cdn.rawgit.com/bjornharrtell/jsts/gh-pages/lib/0.16.0/javascript.util.min.js'/>" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/jsts/jsts.js'/>" charset='utf-8'></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/extLib/openlayers/theme/default/style.css'/>" charset='utf-8'/>


<!-- 2018.04.22 YYK. openlayers3 적용연습 -->
<!--
<link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">
<script src="https://openlayers.org/en/v4.6.5/build/ol.js" type="text/javascript"></script>
 -->
<!-- <link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css"> -->
<!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<!-- <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script> -->
<!-- <script src="https://openlayers.org/en/v4.6.5/build/ol.js" charset='utf-8'></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.3/FileSaver.min.js" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/openlayers3/canvas-toBlob.js'/>" charset='utf-8'></script> <!-- YYK. 지도저장 toBlob (ie용) -->


<script src="<c:url value='/extLib/polyfill.min.js'/>"></script>

<!-- Openlayer -->
<%-- <script type="text/javascript" src="<c:url value='/extLib/openlayers/OpenLayers.debug.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/extLib/openlayers/OpenLayers.js'/>" charset='utf-8'></script>
<%-- <script type="text/javascript" src="<c:url value='/extLib/ginno/gmap/GMap.debug.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/extLib/ginno/gmap/GMap.js'/>" charset='utf-8'></script>

<script type="text/javascript" src="<c:url value='/extLib/ginno/gutil/GUtil.js'/>" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/ginno/gmap/lib/gmashup/util/GMahsupUtil.js'/>" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/ginno/gmap/lib/gmashup/GDaumMap.js'/>" charset='utf-8'></script>
<script type="text/javascript" src="<c:url value='/extLib/ginno/gutil/GError.js'/>" charset='utf-8'></script>

<!-- =======openlayers end================= -->

<script type="text/javascript" src="<c:url value='/extLib/excel/jquery.techbytarun.excelexportjs.js'/>" charset='utf-8'></script>

<!-- cookie -->
<script src="<c:url value='/extLib/jquery/jquery.cookie.js'/>" charset='utf-8'></script>
<!-- jstree -->
<script src="<c:url value='/extLib/jstree/jquery.jstree.js'/>" charset='utf-8'></script>

<!-- ì¬ì©ì ì ì ì¤í¬ë¦½í¸ -->
<script src="<c:url value='/js/common.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/map/map.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/map/layer.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/map/control.js'/>" charset='utf-8'></script>
<script src="<c:url value='/js/srvy/srvyDta.js'/>" charset='utf-8'></script>

<!-- 다음 지도 APi -->
<!-- <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=53ac1925d1cd02c5c4cc1ecdfcd1a3cd&libraries=services"></script> -->
<!-- JOY. 2017. 11. 24. -->
<!-- 실서버키 -->
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c181366299c0ab716080d381011e79b2&libraries=services"></script> -->
<!-- 개발키 -->
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=673dc0ed61508aed5450602c57997402&libraries=services" charset='utf-8'></script>  -->
<!-- [2019-08-21] local서버 개발용 임시 key 발급 by yslee -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=61cea915165ed9516e107ce6b620ef06&libraries=services" charset='utf-8'></script>

<!-- 새 kakkao api 2018.06.05 김태규 -->
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=61cea915165ed9516e107ce6b620ef06&libraries=services"></script> -->

<!-- JOY. 2018. 05. 28. POTHOLE MAP UTIL -->
<script src="<c:url value='/js/common/pthUtil.js'/>" charset='utf-8'></script>

<!-- xml to json Lib -->
<script type="text/javascript" src="<c:url value='/extLib/xml-js.min.js'/>" charset='utf-8'></script>

<script type="text/javascript" charset='utf-8'>
var contextPath="${ pageContext.request.contextPath}/";
</script>
