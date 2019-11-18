<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"  />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>경기도 포장관리시스템</title>
<%@ include file="/include/header.jsp" %>
</head>

<body class="login">
<header>
        <div class="container">
            <h2 class="orgLogo"><span>새로운경기 공정한 세상</span>경기도 건설국 도로안전과</h2>
            <h1>지능형 도로포장상태 <span>예측 및 예방시스템</span></h1>
        </div>
    </header>
    <section>
        <div class="container">
            <article class="loginWrap">
                <h3 class="hidden">로그인</h3>




                <form name="loginFrm" id="loginFrm" method="post" action="">
                    <!-- 필수 파라메터(START) -->
                    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
                    <input type="hidden" id="opener_id" name="opener_id" value=""/>
                    <input type="hidden" id="wnd_id" name="wnd_id" value=""/>

                    <div>
                    	<label for="id">ID</label>
                     	<input type="text" class="iplogin" name="USER_ID" id="id">
                    </div>
                    <div>
                    	<label for="password">PW</label>
                     	<input type="password" class="iplogin" name="SECRET_NO" id="password" onkeypress="if(event.keyCode==13) {fn_login(); return false;}"/>
                    </div>

                    <input type="checkbox" id="idSaveCheck" /><label for="idSaveCheck">아이디저장</label>
                    <a href="#" onclick="fn_login();" class="btnLogin">LOGIN</a>

                    <span class="loginEctMenu">
						<a href="#" onclick="fn_regUser()" class="mr5">사용자신청</a>
						<a href="#" onclick="fn_selUserInfo()">아이디/비밀번호 찾기</a>
                    </span>
                </form>

            </article>

            <article class="noticeWrap">
                <h3 class="h3">공지</h3>
                <span class="noticePaging">
                    <button id="prev" type="button" name="button" onclick="javascript:fnNoticeListSearch('prev')">이전</button>
                    <button id="next" type="button" name="button" onclick="javascript:fnNoticeListSearch('next')">다음</button>
                </span>
                <div class="notice">
                    <ul>
                        <%-- <c:forEach var="notice" items="${noticeList}" varStatus="status">
                        </c:forEach> --%>
                    </ul>
                </div>
                <!-- <ul>
                    <li>
                        <a href="">전체 사용자 권한 재지정은 이러쿵 저러쿵 해 주면 됩니다. 등등등 </a>
                        <span>2019.01.01</span>
                    </li>
                    <li>
                        <a href="">누락된 포장셀 구축요청 안내 이러쿵 저러쿵 해 주면 됩니다. 등등등</a>
                        <span>2019.01.01</span>
                    </li>
                    <li>
                        <a href="">경기도 포장도로관리시스템 이러쿵 저러쿵 해 주면 됩니다. 등등등</a>
                        <span>2019.01.01</span>
                    </li>
                </ul> -->
            </article>
        </div>
    </section>

    <footer>
       <div class="container">
           <p class="copyright">COPYRIGHT(c)2019 ALL RIGHTS RESERVED.</p>
       </div>
       <%@ include file="/include/common.jsp" %>
   </footer>

    <%-- <ul class="bn">
         <li><a href="#"><img src="<c:url value='/images/bn_login1.gif'/>" alt="도로대장관리 시스템" title="도로대장관리 시스템" onclick="window.open('http://105.0.111.83:3200')"/></a></li>
         <li><a href="#"><img src="<c:url value='/images/bn_login2.gif'/>" alt="교량관리 시스템" title="교량관리 시스템" onclick="window.open('http://nbms.kict.re.kr/nbms/index.jsp')"/></a></li>
         <li><a href="#"><img src="<c:url value='/images/bn_login3.gif'/>" alt="TMS 시스템" title="TMS 시스템" onclick="window.open('http://gits.gg.go.kr/web/main/index.do')"/></a></li>
         <li><a href="#"><img src="<c:url value='/images/bn_login4_good.gif'/>" alt="포트홀신고 시스템" title="포트홀신고 시스템" id="good"  onclick="window.open('http://105.0.111.7:3300/gpms/')"/></a></li>
    </ul> --%>


<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/main.js'/>" charset="utf-8"></script>



<script type="text/javaScript" language="javascript" defer="defer" charset="utf-8">
$(document).ready(function() {

    // JOY. 2018. 03. 13. 시스템 Flag
    sFlag = "gpms";

    //id 저장 체크 되어 있을 경우 id 쿠키로 바인딩
    var userInputId = $.cookie('cookie');

    if(userInputId != undefined || userInputId != ''){
        $("#id").val(userInputId);
        $("#idSaveCheck").attr("checked", true);
    }

    if(userInputId == undefined || userInputId == ''){
        $("#idSaveCheck").attr("checked", false);
    }

    //아이디 쿠키 저장 추가
    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("#id").val();
            $.cookie('cookie', userInputId, { expires: 3650 });
        }else{ // ID 저장하기 체크 해제 시,
            $.removeCookie("cookie");
        }
    });

    //공지사항 조회
    fnNoticeListSearch("firstPage");

    // 2018. 01. 02. JOY.
    // 경기도로 모니터링단 시스템 Flag 처리
    /* $("#good").click(function() {

        if ( $(this).hasClass("on") ) {

            // 로고변경 : 경기도로 -> 경기포장
            $("#gpmsLogo").show();
            $("#goodLogo").hide();
            $(this).removeClass("on");
            $(this).attr("src", "<c:url value='/images/bn_login4.gif'/>");
            document.title = '경기도 포장관리시스템';

        } else {

            // 로고변경 : 경기포장 -> 경기도로
            $("#gpmsLogo").hide();
            $("#goodLogo").show();
            $(this).addClass("on");
            $(this).attr("src", "<c:url value='/images/bn_login4-2.png'/>");
            document.title = '경기도로 모니터링단 시스템';

        }

    }); */

    // 2018. 09. 10. 공지사항UP
    //fn_view(61);

});


//메인 공지사항 상세 조회
function fn_view(seqNo) {
    if( seqNo != null ) {
        COMMON_UTIL.noticeWindowOpen('공지사항 상세 정보', "<c:url value='/api/notice/selectNoticeView.do'/>?SEQ_NO="+seqNo, 500, 550, true, null, 'center');
    }
    else
        alert('<spring:message code="warn.checkplz.msg" />');
}

function fn_file_down(val){
    if(typeof val != "undefined" || val != null){
        COMMON_UTIL.fileMoveUrl("attachfile/downloadFile.do?FILE_NO="+val);
    }else{
        alert("첨부파일이 없습니다.");
    }
}


//검색 처리
var firIndex;//첫 번째 인덱스
var pUnit; //페이지 단위
var dataCnt=0;//공지사항전체건수
function fnNoticeListSearch(param) {

    var srcPrev1 = "<c:url value='/images/ln_prev1.gif'/>";
    var srcPrev2 = "<c:url value='/images/ln_prev2.gif'/>";
    var srcNext1 = "<c:url value='/images/ln_next1.gif'/>";
    var srcNext2 = "<c:url value='/images/ln_next2.gif'/>";

    if( param == "firstPage")
        {
            firIndex = 0;
            pUnit = 3;
        }else if(param == "prev"){

            if(firIndex > 1){
                firIndex = pUnit - 5;
                pUnit = pUnit - 3;
                $("#prev").attr("src","/gpms/images/ln_prev2.gif");
                $("#next").attr("src","/gpms/images/ln_next2.gif");

                if(firIndex == -1){
                    firIndex = 0 ;
                    $("#prev").attr("src","/gpms/images/ln_prev1.gif");
                    $("#next").attr("src","/gpms/images/ln_next2.gif");
                }
            }

        }else if(param == "next"){
            if(dataCnt>=firIndex && dataCnt<=pUnit){
                return;
            }
            else{
                firIndex = pUnit+1;
                pUnit = pUnit + 3;
                $("#next").attr("src","/gpms/images/ln_next2.gif");
                $("#prev").attr("src","/gpms/images/ln_prev2.gif");
            }
        }

        if(firIndex == 1){
            $("#prev").attr("src","/gpms/images/ln_prev1.gif");
            $("#next").attr("src","/gpms/images/ln_next2.gif");
        }

    var data = {"firstIndex" : firIndex, "recordCountPerPage" : pUnit};

     $.ajax({
        url: contextPath +'api/notice/selectMainNoticeList.do', //'<c:url value="/"/>' 세션 값으로 인한 URL 문제 발생으로 contextPath로 변경
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (res) {
            //다운로드 이미지 경로
            var src = "<c:url value='/images/ic_down.png'/>";
            //전체 건수
            dataCnt = res.totCnt;
            //데이터 리스트
            var dataList = res.data;

            if(dataList.length > 0){
                pUnit = res.cntpage;
                var noticeList = '';

                for(var i=0; i<dataList.length; i++){
                    noticeList += '<li><a href="#none" onclick="fn_view('+ dataList[i].SEQ_NO+');">' + dataList[i].SJ + '</a>';
                    noticeList += '<span>'+dataList[i].REGIST_DT+'<a href="#none" class="ml10">';

                    if(dataList[i].POS == null || dataList[i].POS == ''){
                        noticeList +='';
                    }else{
                        noticeList += '<img src='+src+' onclick="fn_file_down('+dataList[i].POS+');" alt="첨부파일" title="다운로드" /></a></span></li>';
                    }
                }

                $('.notice ul').empty().append(noticeList);
            }else{

                $("#next").attr("src","/gpms/images/ln_next1.gif");
                $("#prev").attr("src","/gpms/images/ln_prev2.gif");
            }
        },
        error: function () {
            alert("오류가 발생하였습니다. 재검색 하시기 바랍니다.");
            return;
        }
    });

    if(dataCnt !=0 && (dataCnt>=firIndex && dataCnt<=pUnit)){
        $("#next").attr("src","/gpms/images/ln_next1.gif");
        $("#prev").attr("src","/gpms/images/ln_prev2.gif");

        return;
    }
}


</script>
</body>
</html>
