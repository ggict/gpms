<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
<head>
<title>사용자 등록 요청</title>
<%@ include file="/include/common_head.jsp" %>
<style>
.btnChk {
	background-color:#dfedfc;
	-moz-border-radius:4px;
	-webkit-border-radius:4px;
	border-radius:4px;
	border:1px solid #B5B2FF;
	display:inline-block;
	font-size:11px;
	font-weight:normal;
	padding:1px 3px 2px 3px;
	text-decoration:none;
	height:13px;
	color:black;
	line-height:1.3em;
	cursor:pointer;
	margin-left:4px;
}
</style>
</head>
<body class="bgnone cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="" style="height: 80%;">
<input type="hidden" id="DEPT_CODE" name="DEPT_CODE" value="" />
<input type="hidden" id="CNTRWK_CO_NM" name="CNTRWK_CO_NM" value="" />
<input type="hidden" id="USER_SE_CODE" name="USER_SE_CODE" value="in" />

<div class="tabcont">
<!-- <div class="upopup" style="width:400px; height:500px; overflow-y: auto;"> -->
    <div class="content" style="padding: 10px 10px;">
        <div class="ml10 mr10">
        	<div class="titbx">
	<!-- <div class="p_cont">
           <div class="tbspace"> -->
			    <%-- <table class="tbview">
				    <colgroup>
					    <col width="30%;" />
					    <col width="70%;" />
				    </colgroup>
	                   <tbody>
				        <tr>
					        <th align="left" colspan="2" style="height: 22px; text-align: center;">
					        	<font color="red">*</font> 외부업체 구분 선택
					        	</th>
				        </tr>
				        <tr>
				        	<td align="left" colspan="2" style="padding: 10px; border-left: solid 1px #d9d9d9;">
				        		<label for="IN" class="pointer"><input type="radio" onchange="fn_change_item();" name="USER_SE_CODE" id="in" value="in" checked="checked"/> 내부 </label>&nbsp;
				        		<label for="OUT" class="pointer"><input type="radio" onchange="fn_change_item();" name="USER_SE_CODE" id="out" value="out" /> 외부 </label>&nbsp;
				        	</td>
				        </tr>
					</tbody>
				</table> --%>
				<h4>사용자 일반정보</h4>
				<table id="tb_cont" class="tbview" summary="사용자 일반정보를 입력한다.">
				    <colgroup>
					    <col width="20%;" />
					    <col width="70%;" />
				    </colgroup>
	                   <tbody>
				        <tr>
					        <th align="left"><font color="red">*</font> 아이디</th>
					        <td align="left">
					        	<label for="USER_ID"> </label>
						        <input type="text" maxlength="20" id="USER_ID" name="USER_ID" alt="아이디" class="MX_20 input notnull" style="width:67%;" />
						        <a href="#" id="idCheck" style="background: rgba(143, 143, 143, 1); margin: 0px; padding: 4px 15px; border-radius: 5px; color: white; font-weight: bold;">
                                    중복체크
						            <%-- <img src='<c:url value="/images/intro/check.gif" />' alt="중복확인"/> --%>
						        </a>
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 암호</th>
					        <td align="left">
					        	<label for="USER_PW"> </label>
						        <input type="password" maxlength="20" id="USER_PW" name="SECRET_NO" alt="암호" style="ime-mode:disabled; width:67%;" class="MX_20 input notnull" />
						        <font color="red">영문 또는 숫자</font>
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 암호확인</th>
					        <td align="left">
					        	<label for="USER_PW2"> </label>
						        <input type="password" maxlength="20" id="USER_PW2" style="ime-mode:disabled; width:67%;" alt="암호확인" class="MX_20 input notnull" />
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 성명</th>
					        <td align="left">
					        	<label for="USER_NAME"> </label>
						        <input type="text" maxlength="30" id="USER_NAME" name="USER_NM" alt="성명" class="input notnull" />
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 생년월일</th>
					        <td align="left">
					        	<label for="BRTHDY"> </label>
						        <input type="text" id="BRTHDY" name="BRTHDY" alt="생년월일" class="input notnull" maxlength="8" placeholder="예)19XX0101"/>
					        </td>
				        </tr>
				        <tr class="in">
					        <th align="left"><font color="red">*</font> 부서</th>
					        <td align="left">
					        	<div>
						        	<label for="DEPT_1"> </label>
						        	<select id="DEPT_1" alt="부서" class="input notnull" style="width: 100%;" onchange="COMMON_UTIL.fn_change_dept('DEPT_1', 'DEPT_2', '3', 'DEPT_3');">
						        		<option value="">====== 전체 ======</option>
						        		<c:forEach items="${deptCdList }" var="deptCd">
						        			<option value="${deptCd.DEPT_CODE }">${deptCd.LOWEST_DEPT_NM }</option>
						        		</c:forEach>
						        	</select>
					        	</div>
					        	<div>
						        	<label for="DEPT_2"> </label>
						        	<select id="DEPT_2" alt="" class="input" style="width: 100%;" onchange="COMMON_UTIL.fn_change_dept('DEPT_2', 'DEPT_3', '4');">
						        		<option value="">====== 전체 ======</option>
						        	</select>
					        	</div>
					        	<div>
						        	<label for="DEPT_3"> </label>
						        	<select id="DEPT_3" alt="" style="width: 100%;" class="input">
						        		<option value="">====== 전체 ======</option>
						        	</select>
					        	</div>
					        </td>
				        </tr>
				        <tr class="in">
					        <th align="left"><font color="red">*</font> 담당업무</th>
					        <td align="left">
					        	<label for="CHRG_JOB"> </label>
								<input  type="text" name="CHRG_JOB" id="CHRG_JOB" alt="담당업무" maxlength="100" class="MX_50 input notnull" />
					        </td>
				        </tr>
				        <tr class="out">
					        <th align="left"><font color="red">*</font> 공사업체명</th>
					        <td align="left">
					        	<label for="CO_NO"> </label>
					        	<select name="CO_NO" id="CO_NO" onchange="fn_change_comp('CO_NO', 'CNTRWK_CO_NM')" alt="공사업체" class="input notnull">
					        		<option value="">====== 전체 ======</option>
					        		<c:forEach items="${compList }" var="comp">
					        			<option value="${comp.CO_NO }">${comp.CO_NM }</option>
					        		</c:forEach>
					        	</select>
								<%-- <input  type="text" name="CNTRWK_CO_NM" id="CNTRWK_CO_NM" alt="공사업체명" value="<c:out value="${result.USER_DEPT}"/>" class="MX_50 input notnull" /> --%>
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 전화번호</th>
					        <td align="left">
					        	<label for="CTTPC"> </label>
						        <input type="tel" id="CTTPC" name="CTTPC" alt="전화번호" class="input notnull" />
					        </td>
				        </tr>
				        <tr>
					        <th align="left"><font color="red">*</font> 이메일</th>
					        <td align="left">
					        	<label for="EMAIL"> </label>
						        <input type="email" id="EMAIL" name="EMAIL" alt="이메일" class="input notnull" />
					        </td>
				        </tr>
				        <tr class="ud3 out" >
					        <th align="left"><font color="red">*</font> 담당감독자</th>
					        <td align="left">
					        	<label for="CHRG_MANGR_NM"> </label>
						        <input type="text" id="CHRG_MANGR_NM" name="CHRG_MANGR_NM" alt="담당감독자" class="input notnull" />
					        </td>
				        </tr>
				        <tr class="ud3 out">
					        <th align="left"><font color="red">*</font> 계약명</th>
					        <td align="left">
					        	<label for="CNTRCT_NM"> </label>
						        <input type="text" id="CNTRCT_NM" name="CNTRCT_NM" alt="계약명" class="input MX_100 notnull" />
					        </td>
				        </tr>
				        <tr class="ud3 out">
					        <th align="left"><font color="red">*</font> 계약기간</th>
					        <td align="left">
					        	<label for="CNTRCT_BEGIN_DE"> </label>
						        <input type="text" id="CNTRCT_BEGIN_DE" name="CNTRCT_BEGIN_DE" alt="계약시작일" class="DT_DATE input notnull" /> ~
						        <label for="CNTRCT_END_DE"> </label>
						        <input type="text" id="CNTRCT_END_DE" name="CNTRCT_END_DE" alt="계약종료일" class="DT_DATE input notnull" />
						        <br/><font color="red" style="line-height: 20px;">해당 계정으로 계약기간 동안 로그인 가능</font>
					        </td>
				        </tr>
				        <tr>
					        <th align="left"> 기타 관련정보</th>
					        <td align="left">
					        	<label for="RM"> </label>
						        <input type="text" id="RM" name="RM" alt="기타 관련정보" class="input" />
					        </td>
				        </tr>
	                   </tbody>
			    </table>
			</div>
		    <div class="titbx mt5">
				<h4>사용자 권한정보</h4>
		        <table class="tbview" summary="사용자 권한정보를 입력한다.">
		        	<colgroup>
					    <col width="20%;" />
					    <col width="70%;" />
				    </colgroup>
                   	<tbody>
                   		<tr>
                   			<th align="left" scope="row"><font color="red">*</font> 사용자 그룹</th>
                   			<td>
                   				<c:forEach items="${groupList }" var="group">
                   				    <span class="authUser" id="${group.AUTHOR_ID}" <c:if test="${group.AUTHOR_ID == 'ROLE_USER_SGG' }">style="display: none;"</c:if>><input type="radio" name="REQ_USER_GRP" id="REQ_USER_GRP<c:out value="${group.AUTHOR_ID }" />" value="${group.AUTHOR_ID }" class="input notnull" <c:if test="${group.AUTHOR_ID == 'ROLE_ADMIN' }">checked="checked"</c:if>/><label for="REQ_USER_GRP<c:out value="${group.AUTHOR_ID }" />"><c:out value="${group.AUTHOR_NM }" /></label></span>
                   				</c:forEach>
                   			</td>
                   		</tr>
                   		<tr>
                   			<th align="left" scope="row">메뉴접근 권한</th>
                   			<td class="authCheck">
                   				<input type="hidden" name="REQ_MENUACC_ROLE" id="REQ_MENUACC_ROLE" value=""/>
                   				<c:forEach items="${menuAuthList }" var="menu">
                   					<span class="authArea <c:if test="${menu.AUTHOR_ID == 'ROLE_USER_POTHOLE' }">pothole</c:if>" id="${menu.AUTHOR_ID}" <c:if test="${menu.AUTHOR_ID == 'ROLE_USER_POTHOLE' }">style="display: none;"</c:if> ><input type="checkbox" id= "MENUACC<c:out value="${menu.AUTHOR_ID }" />" value="${menu.AUTHOR_ID }" style="margin-right:5px;" /><label for="MENUACC<c:out value="${menu.AUTHOR_ID }" />"><c:out value="${menu.AUTHOR_NM }" /></label><br/></span>
                   				</c:forEach>
                   			</td>
                   		</tr>
                   	</tbody>
		        </table>
		    </div>

		    <!-- <div class="mt5 tr">
                <a href="#"><input type="button" id="changeBtn" class="schbtn" onclick="fnChangeSystem($(this));" style="font-size: 13px; pointer: cursor;" /></a>
            </div> -->

            <div class="uinfobx mt5">
			※ <font color="red">*</font> 는 필수입력사항입니다.<br/>
			※ <font color="blue">아이디</font>는 2~10자의 한글 또는 4~20자의 영문/숫자<br />
			※ <font color="blue">암호</font>는 8~20자의 <font color="red">영문 또는 숫자만</font> 입력 가능합니다.<br />
			</div>

			<div style="text-align: left; line-height:1.2em; font-family: '돋움'; font-size: 12px;">
				<div style="margin: 7px 5px; color: black; line-height:20px">
					<input type="checkbox" id="STPLAT_AGRE_AT" name="STPLAT_AGRE_AT" value="Y" onclick=""/><label for="STPLAT_AGRE_AT"><span id="allowPage" style="cursor: pointer; text-decoration:underline;" onclick="fn_popup_agree();">개인정보 수집 및 이용에 동의합니다.</span><font color="red"> (필수)</font></label><br/>
				</div>
			</div>

			<div class="tr">
		        <a href="#"><input type="button" class="schbtn" onclick="check = false; fn_regist()" value="등록" style="font-size: 13px;" /></a>
		        <a href="#"><input type="button" class="schbtn" onclick="check = false; fn_close_dialog()" value="닫기" style="font-size: 13px;" /></a>
		    </div>
		</div>
	</div>
	<!-- <div class="btnbx mt10 mr10">
		<a href="#"><input type="button" class="schbtn" onclick="fn_regist()" value="등록" style="font-size: 13px;" /></a>
	</div> -->
</div>
</form>
<%@ include file="/include/common.jsp" %>
<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javascript">
$(document).ready( function(){

    // 2018. 01. 05. JOY.
    // 경기도로 모니터링단 시스템 Flag 처리
    if ( parent.sFlag == "good" ) {

        $("#ROLE_USER_SGG").show();
        $(".authArea").hide();
        $("#ROLE_USER_POTHOLE").show();
        $("#ROLE_USER_POTHOLE input").attr({"checked" : "checked", "disabled" : "true"});
        $("#changeBtn").addClass("pothole");
        $("#allowPage").addClass("pothole");
    }

    if ( $("#changeBtn").hasClass("pothole") ) {

        $("#changeBtn").val("경기도 포장관리시스템");

    } else {

        $("#changeBtn").val("경기도로 모니터링단 시스템");

    }

    // input, select 항목 init
    COMMON_UTIL.cmFormObjectInit("frm");

    // 달력 생성 (전체)
    COMMON_UTIL.cmCreateDatepickerLinked('CNTRCT_BEGIN_DE','CNTRCT_END_DE', 10);

    // 중복체크 확인용 플래그
    idCheckFlag = "N";

    // 아이디중복체크
    $("#idCheck").click(function(){
        var id = $("#USER_ID").val();

        if( id=="" || id==null ) {
            alert("아이디를 입력해주세요.");
            $("#USER_ID").focus();
            return false;
        }

        $.ajax({
            url: contextPath + 'api/sysuser/checkUserIdOverlap.do'
            ,data:{USER_ID:id}
            ,dataType:'json'
            ,type:'post'
            ,success:function(data){
                var check = data.result;
                if(!check) {
                    alert("중복된 아이디입니다. 다시 입력해주세요.");
                    $("#USER_ID").focus();
                } else {
                    alert("사용 가능한 아이디입니다.");
                    idCheckFlag = "Y";
                }
            }
            ,error:function(a,b,msg){
                alert("중복 체크에 실패했습니다.");
                //
            }
        });
    });

    // 아이디 변경되면 중복체크여부 N으로 변경
    $("#USER_ID").keyup(function(){
        idCheckFlag = "N";
    });

    //생년월일 숫자만 가능하도록 제한
    $("#BRTHDY").keyup(function(e){
        COMMON_UTIL.removeChar(e);
    })

    // 전화번호
    $('#CTTPC').keyup(function(e) {
        var s = $(this).val();
        s = COMMON_UTIL.phoneNumFomatter(s);
        $(this).val(s);
    });
    
     $("input:radio[name=REQ_USER_GRP]").click(function(){
			
    	if($("input:radio[name=REQ_USER_GRP]:checked").val() == 'ROLE_USER'){
    		var classnm = document.querySelectorAll('.authArea');
    	    i = 0,
    	    l = classnm.length;

    		for (i; i < l; i++) {
    			classnm[i].style.display = 'none';
    		}	
		}else{
			var classnm = document.querySelectorAll('.authArea');
			var pothole = document.querySelector('#ROLE_USER_POTHOLE')
    	    i = 0,
    	    l = classnm.length;

    		for (i; i < l; i++) {
    
    			classnm[i].style.display = 'inline';
    			
    		}
    		pothole.style.display='none';
		} 
    }); 	
    //사용자 구분에 따른 항목 변경
    fn_change_item();
});

// 2018. 03. 07. JOY. 시스템 변경
function fnChangeSystem(obj) {

    var spanlist = $(".authCheck span");

    if ( obj.hasClass("pothole") ) {

        alert("접근권한이 없습니다.");
        return;
        /* for ( var i = 0; i < spanlist.length; i++ ) {

            if ( spanlist.eq(i).hasClass("pothole") ) {

                spanlist.eq(i).hide();

            } else {

                spanlist.eq(i).show();
                spanlist.eq(i).find("input").prop("checked", false);

            }

        }

        $("#changeBtn").val("경기도로 모니터링단 시스템");
        $("#ROLE_USER_SGG").hide();
        obj.removeClass("pothole"); */

    } else {
        
        for ( var i = 0; i < spanlist.length; i++ ) {

            if ( spanlist.eq(i).hasClass("pothole") ) {

                spanlist.eq(i).show();
                spanlist.eq(i).find("input").prop("checked", true);
                spanlist.eq(i).find("input").attr("disabled", "disabled");

            } else {

                spanlist.eq(i).hide();

            }

        }

        $("#changeBtn").val("경기도 포장관리시스템");
        $("#ROLE_USER_SGG").show();
        obj.addClass("pothole");

    }

}

/************************ 개인정보 동의 팝업***************************/
//개인정보수집화면 팝업
function fn_popup_agree(){
    /* var w = window.open( contextPath + 'user/sysuser/infoAgreeCheck.do', '', 'toolbar=no,location=no,status=no,scroll=no,width=800,height=830');
    if(w == null) {
        alert("차단된 팝업을 허용해주세요.");
    } else {
        w.focus();
    } */
    
    if ( $("#allowPage").hasClass("pothole") ) {
        
        COMMON_UTIL.cmWindowOpen('개인정보 수집 및 이용 동의서', contextPath + 'user/sysuser/infoAgreeCheckPth.do', 800, 850, true, $("#wnd_id").val(), 'center', null, 'N');
        
    } else {
        
        COMMON_UTIL.cmWindowOpen('개인정보 수집 및 이용 동의서', contextPath + 'user/sysuser/infoAgreeCheck.do', 800, 850, true, $("#wnd_id").val(), 'center', null, 'N');
        
    }
}

/************************ 입력정보 관련 event ***************************/



// 사용자 구분별 항목 변경
function fn_change_item(){
    //var type = $(':radio[name="USER_SE_CODE"]:checked').val();
    var type = "in";
    var target = "";

    switch(type){
        case "in" :
            target = "out";
            break;
        case "out" :
            target = "in";
            break;
    }

    $("." + type).show();
    $("." + target).hide();

    //값 초기화
    $("." + target).find("input").val("");
    $("." + target).find("select").val("");
    $("#DEPT_CODE").val("");
    $("#CNTRWK_CO_NM").val("");

}

//부서 값 입력
function fn_get_deptCd(){
    var value = "";
    var dept1 = $("#DEPT_1").val();
    var dept2 = $("#DEPT_2").val();
    var dept3 = $("#DEPT_3").val();

    if(dept3 != ""){
        value = dept3;
    }else if(dept2 != ""){
        value = dept2;
    }else {
        value = dept1;
    }

    $("#DEPT_CODE").val(value);

}

//업체정보 변경
function fn_change_comp(checkDivId, targetDivId){
    var value = $("#" + checkDivId + " option:checked").text();
    $("#" + targetDivId).val(value);
}

// 동의 여부 체크
function fn_set_agreAt(txtAgreAt){
    $('input:checkbox[id="STPLAT_AGRE_AT"]').each(function(){
        switch(txtAgreAt){
        case "Y":
            this.checked = true;
            break;
        case "N":
            this.checked = false;
            break;
        }
    });

}

function fn_close_dialog(){

    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {
    COMMON_UTIL.cmWindowClose($("#wnd_id").val());
    }
}

/************************ 사용자 등록 ***************************/

// 사용자 등록
function fn_regist(){
    //null check
    if(COMMON_UTIL.fn_check_notnull("tr")) {
        return;
    }

    //개인정보 수집 및 이용 동의 여부
    if($("input:checkbox[id='STPLAT_AGRE_AT']").is(":checked") == false){
        alert("개인정보 수집 및 이용 동의를 체크하시기 바랍니다.");
        return;
    }

    //길이 체크
    var id = $("#USER_ID").val();
    var pw = $("#USER_PW").val();
    var pw_confm = $("#USER_PW2").val();

    if(!COMMON_UTIL.fnMsgRangeChk(id, 4, 20, "아이디")){return;}
    if(!COMMON_UTIL.fnMsgRangeChk(pw, 8, 20, "암호")){return;}

    if(COMMON_UTIL.fn_chkSpecialChar(id)){
        alert("아이디는 2~10자의 한글 또는 4~20자의 영문/숫자를 사용해야 합니다.");
        return;
    }

    if(COMMON_UTIL.fn_chkSpecialChar(pw)){
        alert("암호는 8~20자의 영문 또는 숫자만 입력 가능합니다.");
        return;
    }

    //아이디 중복 체크
    if(idCheckFlag == "N"){
        alert("아이디 중복체크를 해주시기 바랍니다.");
        return;
    }

    //암호 체크
    if(pw != pw_confm){
        alert("암호와 암호확인 값이 다릅니다.");
        return;
    }

    if(!COMMON_LANG.fn_checkDate("BRTHDY","yyyyMMdd")){
        alert("생년월일의 값이 날짜형태가 아닙니다.");
        return;
    }

    //이메일 체크
    if($("#EMAIL").val() != "" && !COMMON_UTIL.fn_check_format("email", "EMAIL")){ return; }

    //전화번호 체크
    if($("#CTTPC").val() != "" && !COMMON_UTIL.fn_check_format("tel", "CTTPC")){ return; }


    fn_get_deptCd();

    var menuAccList = [];


    $('input:checkbox[name="MENUACC"]').each(function() {
        if(this.checked){
            menuAccList.push($(this).val());
        }
    });

    $("#REQ_MENUACC_ROLE").val(menuAccList.join(","));


    $.ajax({
        url: contextPath + 'api/sysuser/regSysUser.do'
        ,data: $("#frm").cmSerializeObject()
        ,type: 'post'
        ,dataType: 'json'
        ,success: function(res){
            alert("사용자 등록이 완료되었습니다. 관리자의 승인 처리 진행 후 로그인하실 수 있습니다.");

            // 팝업 종료
            var wnd_id = $("#wnd_id").val();
            COMMON_UTIL.cmWindowClose(wnd_id);
        }
        ,error: function(a,b,msg){

        }
    });

}

$(parent).resize(function() {

    // 부모페이지 사이즈 변경 시 부모창 크기에 맞춰서 팝업 위치 조정
    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getWindow(wndId);
    var ww = $(parent).width();
    var wh = $(parent).height();

    ww = ( ww / 2 ) - 225;
    wh = ( wh / 2 ) - 375;

    wnd.move(ww, wh);

});

</script>

</body>
</html>