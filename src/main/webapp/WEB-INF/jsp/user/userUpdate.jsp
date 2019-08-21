<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>로그인 </title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
<%@ include file="/include/common_head.jsp" %>

</head>


<body class="loginBg" style="overflow-y: hidden; ">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->

<div class="content">
    <form name="frm" id="frm" method="post" action="" style="height: 80%;">
    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>

    <table class="tbview" summary="사용자 정보수정를 제공합니다.">
        <caption>사용자 정보수정</caption>
        <colgroup>
            <col width="30%" />
            <col width="70%" />
        </colgroup>
        <tbody>
            <tr>
                <th scope="row">이전암호</th>
                <td><input type="password" style="width: 97%" name="BEF_SECRET_NO" id="BEF_PW"
                    value="" class="ilogin" /></td>
            </tr>
            <tr>
                <th scope="row">변경할암호</th>
                <td><input type="password" style="width: 97%" name="SECRET_NO" id="USER_PW"
                    value="" class="ilogin" /></td>
            </tr>
            <tr>
                <th scope="row">변경할암호확인</th>
                <td><input type="password" style="width: 97%" name="USER_PW2" id="USER_PW2"
                    value="" class="ilogin" />
                <div>
                        ※ 암호는 <font color="red">영문 또는 숫자만</font> 입력 가능합니다.
                    </div></td>
            </tr>
        </tbody>
    </table>
    <div class="loginfooter">
        <%-- <p class="txt_copyright">
            <img src="<c:url value='/images/intro/txt_copyright.gif' />"
                alt="Copyright(c) Government All Rights Reserved." />
        </p> --%>
        
        <p class="tr mt10">
          <a href="#" onclick="fnRegCheck();" class="schbtn">비밀번호 저장</a>
        </p>
    </div>
    <%-- <div class="tr">
        <a href="#" style="margin-top: -25px;" onclick="fnRegCheck();" class="schbtn">저장</a>
        &nbsp; |&nbsp;<a href="<c:url value='/main.do'/>" class="schbtn">HOME</a> //팝업창으로 디자인 적용 후 필요없는 버튼이므로 주석 처리
    </div> --%>
    </form>
    
    <!-- 2018. 03. 12. JOY. 추가 -->
    <table class="tbview mt10" summary="사용자 정보수정를 제공합니다.">
        <caption>사용자 정보수정</caption>
        <colgroup>
            <col width="30%" />
            <col width="70%" />
        </colgroup>
        <tbody>
            <tr>
                 <th scope="row">메뉴접근권한</th>
                 <td>
                     <c:forEach items="${menuAuthList}" var="menu">
                         <input type="checkbox" name="APPLY_MENUACC" style="margin-right: 5px;" <c:if test="${result.REQ_USER_GRP == 'ROLE_ADMIN' }">disabled="disabled"</c:if>  value="${menu.AUTHOR_ID}" <c:if test="${fn: indexOf(result.REQ_MENUACC_ROLE, menu.AUTHOR_ID) > -1}">checked="checked"</c:if>/><c:out value="${menu.AUTHOR_NM }" /><br/>
                     </c:forEach>
                 </td>
            </tr>
        </tbody>
    </table>
    <div class="loginfooter">
        <p class="tr mt10" id="chg-admin" style="display: none;">
            <span style="background: #aaaaaa; border: 1px solid rgba(255, 255, 255, 0.2); margin-top: 0px; margin-bottom: 0px; border-image: none; " class="schbtn">권한변경요청</span>
            <span style="background: #aaaaaa; border: 1px solid rgba(255, 255, 255, 0.2); margin-top: 0px; margin-bottom: 0px; border-image: none; " class="schbtn">요청취소</span>
        </p>
        <p class="tr mt10" id="chg-off" style="display: none;">
            <span style="background: #aaaaaa; border: 1px solid rgba(255, 255, 255, 0.2); margin-top: 0px; margin-bottom: 0px; border-image: none; " class="schbtn">권한변경요청</span>
            <a href="#" onclick="fnAuthCancel();" class="schbtn">요청취소</a>
        </p>
        <p class="tr mt10" id="chg-on" style="display: none;">
            <a href="#" onclick="fnAuthCheck();" class="schbtn ">권한변경요청</a>
        </p>
    </div>
</div>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript">

$(parent).resize(function() {
    
    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();
    
    ww = ( ww / 2 ) - 210;
    wh = ( wh / 2 ) - 107;
    
    wnd.move(ww, wh);
    
});

$(document).ready(function() {
    
    // 2018. 03. 16. JOY. 버튼처리
    var chgAuth = "${chgAuth}";
    
    if ( "${result.REQ_USER_GRP}" == "ROLE_ADMIN" ) {
        
        $("#chg-admin").show();
        
    } else if ( chgAuth != undefined && chgAuth != "" && chgAuth != null ) {
        
        $("#chg-off").show();
        
        $("input[name=APPLY_MENUACC]").attr("checked", false);
        
        for ( var i = 0; i < $("input[name=APPLY_MENUACC]").length; i++ ) {
            
            var auth = "${chgAuth.REQ_MENUACC_ROLE}"; 
            
            if ( auth.indexOf($("input[name=APPLY_MENUACC]").eq(i).val()) > -1 ) {
            
                $("input[name=APPLY_MENUACC]").eq(i).click();
                
            }
            
        }
        
        $("input[name=APPLY_MENUACC]").attr("disabled", "disabled");
        
    } else {
        
        $("#chg-on").show();
        
    }
    
});

//등록 전 입력값 체크
function fnRegCheck(){
    
    var bef_pw = $("#BEF_PW").val();                        // 입력한 이전 암호
    var new_pw1 = $("#USER_PW").val();                      // 변경할 암호
    var new_pw2 = $("#USER_PW2").val();                     // 변경할 암호 확인
    
    if( !bef_pw || !new_pw1 || !new_pw2 ){
        alert("암호를 입력해주세요.");
        return false;
    }
    if( new_pw1 != new_pw2 ) {
        alert("변경할암호와 변경할암호확인 항목이 일치하지 않습니다.");
        $("#USER_PW").focus(); return false;
    }
    if( bef_pw == new_pw1 ) {
        alert("이전암호와 변경할암호 항목이 동일하면 안됩니다. \n암호를 변경해주세요.");
        $("#USER_PW").focus(); return false;
    }
    if( COMMON_UTIL.fnMsgRangeChk( $("#USER_PW").val(), 8, 20, '암호' )==false) {
        $("#USER_PW").focus(); return false;
    }
    if( COMMON_UTIL.fn_chkCharWithNum( $("#USER_PW").val() )==false) {
        alert("암호는 반드시 영어와 숫자를 혼용해서 사용해야합니다.");
        $("#USER_PW").focus(); return false;
    }
    
    if( confirm('<spring:message code="warn.update.msg" />') ) {
        // 등록
        fnRegist();
    }
}

function fnRegist(){
    
    $("#frm").attr("action","<c:url value='/api/sysuser/updateUserSec.do'/>");
    $("#frm").attr("target","proc_frm");
    $("#frm").submit();
    
    fnSaveCallback();
}

//callback - 전체폼초기화
function fnSaveCallback(){
    document.frm.reset();
}

// 2018. 03. 12. JOY. 사용자 권한 요청
function fnAuthCheck() {
    
    var menuAccList = [];
    
    $('input:checkbox[name="APPLY_MENUACC"]').each(function() {
        if(this.checked){
            menuAccList.push($(this).val());
        }
    });
    
    var roleStr = menuAccList.join(",");
    
    if (roleStr == "${result.REQ_MENUACC_ROLE}") {
        
        alert("변경된 권한이 없습니다.");
        return;
        
    } else {
        
        if ( confirm("권한 변경 요청을 진행하시겠습니까?") ) {
            var postData = {
                "USER_NO" : "${result.USER_NO}"
                , "REQ_USER_GRP" : "${result.REQ_USER_GRP}"
                , "REQ_MENUACC_ROLE" : roleStr
            };
            
            
            $.ajax({
                url : '<c:url value="/api/sysuser/updateUserAuth.do"/>'
                , type : 'post'
                , dataType : 'json'
                , contentType : 'application/json; charset=UTF-8'
                , data : JSON.stringify(postData)
                , success : function(data) {
                    
                    $("#chg-off").show();
                    $("#chg-on").hide();
                    
                    $("input[name=APPLY_MENUACC]").attr("disabled", "disabled");
                    
                    alert( data.msg );
                    return;
                    
                }
                , error : function(a, b, msg) {
                    alert( "승인 처리에 문제가 발생하였습니다.");
                }
            });
            
            
        }
    }
    
}

function fnAuthCancel() {
    
    if ( confirm("권한 요청을 취소하시겠습니까?") ) {
        
        var postData = { "USER_NO" : "${result.USER_NO}" };
        
        $.ajax({
            url : '<c:url value="/api/sysuser/updateUserAuthCancel.do"/>'
            , type : 'post'
            , dataType : 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(data) {
                
                $("#chg-off").hide();
                $("#chg-on").show();
                
                $("input[name=APPLY_MENUACC]").attr("disabled", false);
                
                alert( data.msg );
                return;
                
            }
            , error : function(a, b, msg) {
                alert( "승인 처리에 문제가 발생하였습니다.");
            }
        });
    }
    
}

</script>

</body>
</html>