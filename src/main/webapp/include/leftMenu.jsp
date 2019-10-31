<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="btmenuarea">

    <div id="sub_route">
        <h2>노선 검색</h2>
        <ul class="btab_menu">
            <li style="height:145px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="routeinfo/selectRouteInfoList.do"/>');">노선 데이터로<br />검색</a></li>
            <li style="height:146px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cell10/selectCell10List.do"/>');">셀단위로<br />검색</a></li>
        </ul>
    </div>

    <div id="sub_srvyEvl">
        <h2>포장상태 평가</h2>
        <ul class="btab_menu">
            <li style="height:146px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyDtaEvlInfoList.do"/>');">포장상태<br />평가정보 조회</a></li>
            <li style="height:145px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyDtaEvlList.do"/>');">포장상태 평가</a></li>
        </ul>
    </div>

    <div id="sub_srvyPredct">
        <h2>포장상태 예측</h2>
        <ul class="btab_menu">
            <li style="height:146px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="smdtalaststtus/selectSrvyDtaLastSttusList.do"/>');">포장상태<br />예측정보 조회</a></li>
            <li style="height:145px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="smdtalaststtus/selectSrvyDtaPredctList.do"/>');">예측자료 생성</a></li>
        </ul>
    </div>

    <div id="sub_cntrwk">
        <h2>포장공사 이력관리</h2>
        <ul class="btab_menu">
            <li style="height:146px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cntrwk/selectCntrwkList.do"/>');">포장공사<br />이력조회</a></li>
            <li style="height:145px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="cntrwk/selectCntrwkList.do"/>');">포장공사<br />진행현황</a></li>
        </ul>
    </div>

    <div id="sub_srvy_mng">
        <h2>조사요청구간관리</h2>
        <ul class="btab_menu">
            <li style="height:145px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/mng/srvyRqstSctnMngList.do"/>');">조사요청구간 관리</a></li>
            <li style="height:146px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/mng/srvyRqstSctnMngRgst.do"/>');">조사요청구간 등록</a></li>
        </ul>
    </div>

	<div id="sub_reg_srvy">
	    <h2>조사자료 관리</h2>
	    <ul class="btab_menu">
	        <li style="height:146px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvy/selectSrvyExcelList.do"/>');">조사자료<br />이력조회</a></li>
	        <li style="height:145px"><a href="#" class="tab2" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="srvydtaexcel/selectSrvyDtaExcelList.do"/>');">조사자료<br />등록</a></li>
	    </ul>
    </div>




	<div id="sub_anal_srvy">
	    <h2>조사자료분석</h2>
	    <ul class="btab_menu">
	        <li style="height:97px"><a href="#" class="tab1">조사자료<br />적용집계/산정</a></li>
	        <li style="height:97px"><a href="#" class="tab2">선택년도<br />적용집계/산정 </a></li>
	        <li style="height:97px"><a href="#" class="tab3">포장상태현황<br />상세조회</a></li>
	    </ul>
    </div>

    <div id="sub_mummSctnSrvyDtaDetail">
        <h2>포장상태 조사정보</h2>
        <ul class="btab_menu">
            <li style="height:291px"><a href="#" class="tab1">포장상태<br />조사정보<br />상세조회</a></li>
            <%-- <li style="height:291px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="mng/mummsctnsrvydta/mummSctnSrvyDtaDetail.do"/>');">포장상태<br />조사정보<br />상세조회</a></li> --%>
        </ul>
    </div>

    <!-- 2018. 01. 04. 경기도로 모니터링단 시스템 -->
    <%-- <div id="sub_cmptnc">
        <h2>관할구역 검색</h2>
        <ul class="btab_menu">
            <li style="height:291px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="pothole/cmptnc/selectCmpncList.do"/>');">관할구역 검색</a></li>
        </ul>
    </div>

    <div id="sub_sttemnt">
        <h2>포트홀 신고관리</h2>
        <ul class="btab_menu">
            <li style="height:291px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="pothole/sttemnt/selectSttemntList.do"/>');">포트홀 신고관리</a></li>
        </ul>
    </div>

    <div id="sub_sttst">
        <h2>통계</h2>
        <ul class="btab_menu">
            <li style="height:291px"><a href="#" class="tab1" onclick="COMMON_UTIL.cmMenuUrlContent('<c:url value="pothole/sttst/selectDatePeriodList.do"/>');">통계</a></li>
        </ul>
    </div> --%>

</div>
