<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="stmenuarea">

    <!-- 통계 > 노선현황 -->
    <div id="sub_stat_route">
        <h2>노선현황 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="height: 100px"><a href="#sch_cnt01" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectRouteStats.do"/>');">노선별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt01" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectDeptStats.do"/>');">관리기관별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt02" class="tab3" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectTrackStats.do"/>');">차로별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt03" class="tab4" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectAdmStatsTable.do"/>');">시군구별<br />통계</a></li>
        </ul>

        <h3>검색조건</h3>
        <ul class="sch">
            <li class="wid100"><label>국토부</br>  통계연도</label></li>
            <li class="wid100">
                <select name="SCH_STATS_YEAR" id="SCH_STATS_YEAR" style="width: 120px;">
                    <c:forEach var="selectData" items="${statsYear}">
                        <option value="${selectData.STATS_YEAR}">${selectData.STATS_YEAR}</option>
                    </c:forEach>
                </select>
            </li>
            <li class="wid100">
                <a href="#" class="schbtn dpb" onclick="fnRoutStatsSearch();">검색</a>
            </li>
        </ul>
    </div>

    <!-- 포장공사이력관리 > 포장공사통계조회, 통계 > 포장공사이력 -->
    <div id="sub_stat">
        <h2>포장공사이력 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="height: 100px"><a href="#sch_cnt01" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="cntrwkdtl/selectCntrwkRoutCntStats.do"/>');">노선별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt02" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="cntrwkdtl/selectCntrwkDeptCntStats.do"/>');">관리기관별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt03" class="tab3" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="cntrwkdtl/selectCntrwkMthdCntStats.do"/>');">포장공법별<br />통계</a></li>
        </ul>

        <h3>검색조건</h3>
        <ul class="sch">
            <li class="wid100"><label id="schDp1" >관리기관</label></li>
            <li id="schDp2" class="wid100">
                <select name="SCH_DEPT_CODE" id="SCH_DEPT_CODE" style="width: 120px;">
                    <option value="">== 전체 ==</option>
                    <c:forEach var="selectData" items="${deptCdList}">
                        <option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
                    </c:forEach>
                </select>
            </li>
            <li id="schMs" class="wid100" style="display: none;">
                <select name="SCH_RPAIR_MTHD" id="SCH_RPAIR_MTHD" style="width: 120px;">
                    <option value="">== 전체 ==</option>
                    <c:forEach var="selectData" items="${RpairMthds}">
                        <c:if test="${selectData.MSRC_CL_NM ne null}">
                            <option value="${selectData.RPAIR_MTHD_CODE}">${selectData.MSRC_CL_NM}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </li>
            <li class="wid100"><label>공사기간</label></li>
            <li class="wid100">
                <span class="calendar" style="width: 100px; line-height: normal;">
                    <input type="text" id="SCH_STRWRK_DE" name="SCH_STRWRK_DE" style="width:86px" />
                </span>
            </li>
            <li class="wid100">
                &nbsp;~&nbsp;
                <span class="calendar" style="width: 100px; line-height: normal;">
                    <input type="text" id="SCH_COMPET_DE" name="SCH_COMPET_DE" style="width:86px" />
                </span>
            </li>
            <li class="wid100">
                <a href="#" class="schbtn dpb" onclick="fnCntrwkHistStatsSearch();">검색</a>
            </li>
        </ul>
    </div>

    <!-- 통계 > 포장상태조사구간 -->
    <div id="sub_stat_survey">
        <h2>조사구간 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="display:none;"><a href="#sch_cnt01" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectSurveyAllLenStatsTable.do"/>');">전체<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt02" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectSurveyRoutLenStats.do"/>');">노선별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt03" class="tab3" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectSurveyDeptLenStats.do"/>');">관리기관별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt03" class="tab4" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="stats/selectSurveyRoadLenStats.do"/>');">도로등급별<br />통계</a></li>
        </ul>

        <div id="divSearch" style="display: none;">
            <h3>검색조건</h3>
            <ul class="sch">

                <div id="divSrvyRoutSearch">
                         <li class="wid100">
	                <label>도로등급</label>
	                <select id="SCH_ROAD_GRAD" name="SCH_ROAD_GRAD" alt="도로등급" onchange="fn_change_roadNo();" class="input" style="width:100px;">
	                	<option value="">== 전체 ==</option>
		        		<c:forEach items="${roadGradList }" var="roadGrad">
		        			<option value="${roadGrad.CODE_VAL }">${roadGrad.CODE_NM }</option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li class="wid100">
	                <label>노선번호</label>
	                <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input" style="width:100px;">
		                <option value="">== 전체 ==</option>
		        		<c:forEach items="${roadNoList }" var="roadNo">
		        			<option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
		        		</c:forEach>
	                </select>
	            </li>
                    <%-- <li class="wid100">
                        <label>도로등급</label>
                        <select name="SCH_ROAD_CODE" id="SCH_ROAD_CODE" onchange="fn_change_roadNo();"style="width: 120px;">
                            <option value="">== 전체 ==</option>
                            <c:forEach var="selectData" items="${roadGradList}">
                                <option value="${selectData.CODE_VAL}">${selectData.CODE_NM}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li class="wid100">
                        <label>노선번호</label>
                       <!--  <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="COMMON_UTIL.fn_change_roadNm('ROAD_NO', 'ROAD_NAME', 'SCH_ROAD_GRAD');" style="width: 120px;" class="input"> -->
                       <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" style="width: 120px;" class="input">
                            <option value="">== 전체 ==</option>
                            <c:forEach items="${roadNoList }" var="roadNo">
                                <option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
                            </c:forEach>
                       </select>
                    </li> --%>
                    <li>
                       <label>노선명</label>
                       <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width: 112px;" class="MX_80 CS_50 input" />
                    </li>
                </div>

                <div id="divSrvyDeptSearch">
                    <li class="wid100">
                    <label>관리기관</label>
                        <select name="SCH_DEPT_CODE1" id="SCH_DEPT_CODE1" style="width: 120px;">
                            <option value="">== 전체 ==</option>
                            <c:forEach var="selectData" items="${deptCdList}">
                                <option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
                            </c:forEach>
                        </select>
                    </li>
                </div>

                <div id="divSrvyRoadSearch">
                    <li class="wid100">
                        <label>도로등급</label>
                        <select name="SCH_ROAD_CODE" id="SCH_ROAD_CODE" style="width: 120px;">
                            <option value="">== 전체 ==</option>
                            <c:forEach var="selectData" items="${roadGradList}">
                                <option value="${selectData.CODE_VAL}">${selectData.CODE_NM}</option>
                            </c:forEach>
                        </select>
                    </li>
                </div>

                <li class="wid100"><label>조사기간</label></li>
                <li class="wid100">
                    <span class="calendar" style="width: 100px; line-height: normal;">
                        <input type="text" id="SCH_STRSRVY_DE" name="SCH_STRSRVY_DE" style="width:86px" />
                    </span>
                </li>
                <li class="wid100">
                    &nbsp;~&nbsp;
                    <span class="calendar" style="width: 100px; line-height: normal;">
                        <input type="text" id="SCH_ENDSRVY_DE" name="SCH_ENDSRVY_DE" style="width:86px" />
                    </span>
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="fnSrvyRoutStatsSearch();">검색</a>
                </li>
            </ul>
        </div>
    </div>

    <!-- 통계 > 포장상태 평가 -->
    <div id="sub_stat_mumm">
        <h2>포장상태평가 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="height: 150px"><a href="#sch_cnt01" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummRoutCntStats.do"/>');">노선별<br />통계</a></li>
            <li style="height: 150px"><a href="#sch_cnt02" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummDeptCntStats.do"/>');">관리기관별<br />통계</a></li>
            <li style="height: 150px"><a href="#sch_cnt03" class="tab3" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="mumm/mummAdmCntStats.do"/>');">시군구별<br />통계</a></li>
        </ul>

        <div id="schRoutCnt">
            <h3>검색조건</h3>
            <ul class="sch">
                <li class="wid100">
                    <label>도로등급</label>
                    <select name="MUMM_ROAD_GRAD" id="MUMM_ROAD_GRAD" style="width: 120px;" onchange="COMMON_UTIL.fn_change_roadNo('MUMM_ROAD_GRAD', 'MUMM_ROAD_NO', 'MUMM_ROAD_NAME');">
                        <option value="">== 전체 ==</option>
                        <c:forEach var="selectData" items="${ roadGradList }">
                            <option value="${ selectData.CODE_VAL }">${ selectData.CODE_NM }</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <label>노선번호</label>
                    <select id="MUMM_ROAD_NO" name="MUMM_ROAD_NO" alt="노선번호" onchange="COMMON_UTIL.fn_change_roadNm('MUMM_ROAD_NO', 'MUMM_ROAD_NAME', 'MUMM_ROAD_GRAD');" style="width: 120px;" class="input">
                        <option value="">== 전체 ==</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>노선명</label>
                    <input type="text" name="MUMM_ROAD_NAME" id="MUMM_ROAD_NAME" readonly="readonly" value="" style="width: 112px;" class="MX_80 CS_50 input" />
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="fnMummSearch();">검색</a>
                </li>
            </ul>
        </div>

        <div id="schDeptCnt" style="display: none;">
            <h3>검색조건</h3>
            <ul class="sch">
                <li class="wid100">
                    <label>관리기관</label>
                    <select name="MUMM_DEPT_CODE" id="MUMM_DEPT_CODE" style="width: 120px;">
                        <option value="">== 전체 ==</option>
                        <c:forEach var="selectData" items="${deptCdList}">
                            <option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="fnMummSearch();">검색</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="sub_stat_predct">
        <h2>포장상태예측 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="height: 150px"><a href="#" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="smdtalaststtus/selectSmDtaLastRoutCntStats.do"/>');">노선별<br />포장상태 예측</a></li>
            <li style="height: 150px"><a href="#" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="smdtalaststtus/selectSmDtaLastDeptCntStats.do"/>');">관리기관별<br />포장상태 예측</a></li>
        </ul>
        <div id="predctRoutCnt">
            <h3>검색조건</h3>
            <ul class="sch">
                <li class="wid100">
                    <label>도로등급</label>
                    <select name="PREDCT_ROAD_GRAD" id="PREDCT_ROAD_GRAD" style="width: 120px;"  onchange="COMMON_UTIL.fn_change_roadNo('PREDCT_ROAD_GRAD', 'PREDCT_ROAD_NO', 'PREDCT_ROAD_NAME');">
                    <!-- <select name="PREDCT_ROAD_GRAD" id="PREDCT_ROAD_GRAD" style="width: 120px;"  onchange="fn_change_roadNo();"> -->
                        <option value="">== 전체 ==</option>
                        <c:forEach var="roadGrad" items="${ roadGradList }">
                            <option value="${ roadGrad.CODE_VAL }">${ roadGrad.CODE_NM }</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <label>노선번호</label>
                    <select id="PREDCT_ROAD_NO" name="PREDCT_ROAD_NO" alt="노선번호" onchange="COMMON_UTIL.fn_change_roadNm('PREDCT_ROAD_NO', 'PREDCT_ROAD_NAME', 'PREDCT_ROAD_GRAD');" style="width: 120px;" class="input">                        
					<!-- <select id="PREDCT_ROAD_NO" name="PREDCT_ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" style="width: 120px;" class="input"> -->
	                    <option value="">== 전체 ==</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>노선명</label>
                    <input type="text" name="PREDCT_ROAD_NAME" id="PREDCT_ROAD_NAME" readonly="readonly" value="" style="width: 112px;" class="MX_80 CS_50 input" />
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="fnPredctSearch();">검색</a>
                </li>
            </ul>
        </div>
        <div id="predctDeptCnt" style="display: none;">
            <h3>검색조건</h3>
            <ul class="sch">
                <li class="wid100">
                    <label>관리기관</label>
                    <select name="PREDCT_DEPT_CODE" id="PREDCT_DEPT_CODE" style="width: 120px;">
                        <option value="">== 전체 ==</option>
                        <c:forEach var="selectData" items="${deptCdList}">
                            <option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
                        </c:forEach>
                    </select>
                </li>
                <li class="wid100">
                    <label>노선번호</label>
                    <select id="PREDCT_D_ROAD_NO" name="PREDCT_D_ROAD_NO" alt="노선번호" onchange="COMMON_UTIL.fn_change_roadNm('PREDCT_D_ROAD_NO', 'PREDCT_D_ROAD_NAME');" style="width: 120px;" class="input">
						<option value="">== 전체 ==</option>
						<c:forEach items="${ roadNoList }" var="roadNo">
						    <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
						</c:forEach>
                    </select>
                </li>
                <li>
                    <label>노선명</label>
                    <input type="text" name="PREDCT_D_ROAD_NAME" id="PREDCT_D_ROAD_NAME" readonly="readonly" value="" style="width: 112px;" class="MX_80 CS_50 input" />
                </li>
                <li class="wid100">
                    <a href="#" class="schbtn dpb" onclick="fnPredctSearch();">검색</a>
                </li>
            </ul>
        </div>
    </div>

    <!-- 통계 > 보수대상 선정 (2019신규) -->
    <div id="sub_stat_rpair">
        <h2>보수대상 선정 통계</h2>
        <h4>통계 종류</h4>
        <ul class="btab_menu" style="min-height: 300px">
            <li style="height: 100px"><a href="#sch_cnt01" class="tab1" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="rpairtrgetgroup/rpairRoutLenStats.do"/>');">노선별<br />통계</a></li>
            <li style="height: 100px"><a href="#sch_cnt02" class="tab2" onclick="COMMON_UTIL.statsMenuUrlContent('<c:url value="rpairtrgetgroup/rpairDeptLenStats.do"/>');">관리기관별<br />통계</a></li>
        </ul>

        <h3>검색조건</h3>
        <ul class="sch">
            <li class="wid100"><label>선정년도</label></li>
            <li class="wid100">
                <select name="SCH_STATS_YEAR" id="SCH_STATS_YEAR" style="width: 120px;">
                    <c:forEach var="selectData" items="${statsYear}">
                        <option value="${selectData.STATS_YEAR}">${selectData.STATS_YEAR}</option>
                    </c:forEach>
                </select>
            </li>
            <li class="wid100">
                <a href="#" class="schbtn dpb" onclick="fnRpairStatsSearch();">검색</a>
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript" defer="defer">
$( document ).ready(function() {
    //달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE', 'SCH_COMPET_DE', 30);
    COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRSRVY_DE', 'SCH_ENDSRVY_DE', 30);

    $("#SCH_STATS_YEAR option:eq(0)").attr("selected", "selected");
});

//노선현황
function fnRoutStatsSearch(){
    var rw = $(window).width()/3;
    var rw1 = $(window).width()-400;
    var sYear = $("#SCH_STATS_YEAR").val();

    //노선별 통계가 선택되었을 시
    if($('.btab_menu li.sel a').hasClass("tab1") == true){
        parent.content_stArea.fnRoutStatsSearch(sYear);
    }
    //관리기관별 통계가 선택되었을 시
    else if($('.btab_menu li.sel a').hasClass("tab2") == true){
        parent.content_stArea.fnDeptStatsSearch(sYear);
    }
    //차로별 통계가 선택되었을 시
    else if($('.btab_menu li.sel a').hasClass("tab3") == true){
        parent.content_stArea.fnTrackStatsSearch(sYear);
    }
    //시군구별 통계가 선택되었을 시
    else if($('.btab_menu li.sel a').hasClass("tab4") == true){
        parent.content_stArea.fnAdmStatsSearch(sYear);
    }
}

//포장공사이력
function fnCntrwkHistStatsSearch(){
    var rw = $(window).width()/3;
    var deptCd = $("#SCH_DEPT_CODE").val();
    var rmthd = $("#SCH_RPAIR_MTHD").val();
    var strDt = $("#SCH_STRWRK_DE").val();
    var endDt = $("#SCH_COMPET_DE").val();

    //날짜 '-'삭제
    strDt = strDt.replace(/[^0-9]/g,'');
    endDt = endDt.replace(/[^0-9]/g,'');

    //노선별 통계가 선택되었을 시
    if($('.btab_menu li.sel a').hasClass("tab1") == true){
        parent.content_stArea.fnRoutSearch(deptCd,strDt,endDt,rw);
    }
    //관리기관별 통계가 선택되었을 시
    else if($('.btab_menu li.sel a').hasClass("tab2") == true){
        parent.content_stArea.fnDeptSearch(deptCd,strDt,endDt,rw);
    }
    //포장공법별 통계가 선택되었을 시
    else if($('.btab_menu li.sel a').hasClass("tab3") == true){
        parent.content_stArea.fnMthdSearch(rmthd,strDt,endDt,rw);
    }
}

//평가상태조사구간
function fnSrvyRoutStatsSearch(){

    var rw = $(window).width()/3;
    var rw1 = $(window).width()-200;
    var roadNo = $("#ROAD_NO").val();
    var deptCd = $("#SCH_DEPT_CODE1").val();
    var roadCd = $("#SCH_ROAD_CODE").val();
    var strDt = $("#SCH_STRSRVY_DE").val();
    var endDt = $("#SCH_ENDSRVY_DE").val();

    //날짜 '-'삭제
    strDt = strDt.replace(/[^0-9]/g,'');
    endDt = endDt.replace(/[^0-9]/g,'');

    $("#divSearch").show();

    //노선별 통계가 선택되었을 시
    if($('.btab_menu li.sel a').hasClass("tab2") == true){
        parent.content_stArea.fnSrvyRoutSearch(roadCd,roadNo,strDt,endDt,rw1);
    }

    //관리기관별 통계가 선택되었을 시
    if($('.btab_menu li.sel a').hasClass("tab3") == true){
        parent.content_stArea.fnSrvyDeptSearch(deptCd,strDt,endDt,rw1);
    }

    //도로등급별 통계가 선택되었을 시
    if($('.btab_menu li.sel a').hasClass("tab4") == true){
        parent.content_stArea.fnSrvyRoadSearch(roadCd,strDt,endDt,rw1);
    }
}


//포장공사 이력관리 > 노선별 현황
$("#sub_stat .tab1").bind("click", function() {
    $("#schDp1").text("관리기관")
    $("#schDp2").css("display","block");
    $("#schMs").css("display","none");
});

//포장공사 이력관리 > 관리기관별 현황
$("#sub_stat .tab2").bind("click", function() {
    $("#schDp1").text("관리기관")
    $("#schDp2").css("display","block");
    $("#schMs").css("display","none");
});

//포장공사 이력관리 > 관리기관별 현황
$("#sub_stat .tab3").bind("click", function() {
    $("#schDp1").text("포장공법")
    $("#schDp2").css("display","none");
    $("#schMs").css("display","block");
});


//평가상태조사구간 > 노선별 현황
$("#sub_stat_survey .tab2").bind("click", function() {
    $("#divSearch").show();
    $("#divSrvyRoutSearch").show();
    $("#divSrvyDeptSearch").hide();
    $("#divSrvyRoadSearch").hide();
    $("#divSrvyDate").hide();
});

//평가상태조사구간 > 관리기관별 현황
$("#sub_stat_survey .tab3").bind("click", function() {
    $("#divSearch").show();
    $("#divSrvyDeptSearch").show();
    $("#divSrvyRoutSearch").hide();
    $("#divSrvyRoadSearch").hide();
    $("#divSrvyDate").hide();
});

//평가상태조사구간 > 도로등급별 현황
$("#sub_stat_survey .tab4").bind("click", function() {
    $("#divSearch").show();
    $("#divSrvyRoadSearch").show();
    $("#divSrvyDeptSearch").hide();
    $("#divSrvyRoutSearch").hide();
    $("#divSrvyDate").hide();
});



// JOY 2017. 11. 17. 포장상태 평가 통계 검색
function fnMummSearch() {

    var rw = $(window).width()-350;
    var roadGrad = $("#MUMM_ROAD_GRAD").val();
    var roadNo = $("#MUMM_ROAD_NO").val();
    var deptCode = $("#MUMM_DEPT_CODE").val();

    // 노선별 통계가 선택되었을 시
    if( $('#sub_stat_mumm .btab_menu li.sel a').hasClass("tab1") == true ) {

        if ( schFlag == 0 ) {

            var postData = { "ROAD_GRAD" : roadGrad, "ROUTE_CODE" : roadNo };
            parent.content_stArea.fn_search(postData);

        } else {

            parent.content_stArea.fnRoutSearch(roadGrad, roadNo, rw);

        }
    }

    // 관리기관별 통계가 선택되었을 시
    if( $('#sub_stat_mumm .btab_menu li.sel a').hasClass("tab2") == true ) {

        if ( schFlag == 0 ) {

            var postData = { "DEPT_CODE" : deptCode };
            parent.content_stArea.fn_search(postData);

        } else {

            parent.content_stArea.fnDeptSearch(deptCode, rw);

        }
    }

}

//도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo(val) {
    var roadGrad = $("#SCH_ROAD_GRAD").val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>== 전체 ==</option>";

            for(var i=0; i < data.length; i++){
                txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
            }

            var no = $("#ROAD_NO").val();
            var name = $("#ROAD_NAME").val();
			
            $("#ROAD_NO").html(txtHtml);
            $("#ROAD_NAME").val("");

            if(val != undefined){
                $("#ROAD_NO").val(val);
                fn_change_roadNm();
            }
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
    var roadGrad = $("#SCH_ROAD_GRAD").val();

    if(roadNo == "") {
        $("#ROAD_NAME").val("");
        $("#SCH_ROAD_GRAD").val("");
        return;
    }

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfo.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_NO : roadNo})
        ,success: function(data){
            $("#ROAD_NAME").val(data.ROAD_NAME);
            
            $("#SCH_ROAD_GRAD").val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){

        }
    });
}
/*
function fn_change_roadNo(gradId, noId, nameId) {
    var roadGrad = $("#" + gradId).val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>== 전체 ==</option>";

            for(var i=0; i < data.length; i++){
                txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
            }

            $("#" + noId).html(txtHtml);
            $("#" + nameId).val("");
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm(noId, nameId, gradId) {
    var roadNo = $("#" + noId).val();

    if(roadNo == "") {
        $("#" + nameId).val("");
        return;
    }

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfo.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_NO : roadNo})
        ,success: function(data){
            $("#" + nameId).val(data.ROAD_NAME);
            $("#" + gradId).val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){

        }
    });
}
 */
//skc 2017. 11. 23. 포장상태 예측 통계 검색
function fnPredctSearch() {

    var rw = $(window).width()-350;
    var roadGrad = $("#PREDCT_ROAD_GRAD").val();
    var roadNo = $("#PREDCT_ROAD_NO").val();
    var deptCode = $("#PREDCT_DEPT_CODE").val();
    var deptRoadNo = $("#PREDCT_D_ROAD_NO").val();

    // 노선별 통계가 선택되었을 시
    if($('#sub_stat_predct .btab_menu li.sel a').hasClass("tab1") == true){
        parent.content_stArea.fnRouteGPCISearch(roadNo);
    }

    // 관리기관별 통계가 선택되었을 시
    if($('#sub_stat_predct .btab_menu li.sel a').hasClass("tab2") == true){
        parent.content_stArea.fnDeptGPCISearch(deptCode);
        parent.content_stArea.fnRouteGPCISearch(deptRoadNo);
    }

}
</script>
