/** 
 * 메인 페이지 JS
 * 
 */

/* 로그인 관련 */
function fn_login() {
	var id = $.trim($("#id").val());
	var pw = $.trim($("#password").val());
	
	if(typeof id == "undefined" || id == "" || typeof pw == "undefined" || pw == ""){
		alert("아이디나 패스워드를 입력해주세요.");
		return;
	}
	
	$("#password").val(encodeURIComponent(pw));
	
	// 2018. 01. 02. JOY.
	// 경기도로 모니터링단 시스템 Flag 처리
	if ( sFlag == "good" ) {
	    
	    COMMON_UTIL.cmFormSubmit("loginFrm", "proc_frm", contextPath+"goodlogin.do", "fn_login_callback");
	    
	} else {
	    
	    COMMON_UTIL.cmFormSubmit("loginFrm", "proc_frm", contextPath+"login.do", "fn_login_callback");
	    
	}
	
}

// 로그인 callback 함수
function fn_login_callback() {
	try {
	    
	    // 2018. 01. 02. JOY.
	    // 경기도로 모니터링단 시스템 Flag 처리
	    if ( sFlag == "good" ) {
	        
	        $(top.location).attr('href', contextPath+"goodmap.do");
	        
	    } else {
	        
	        $(top.location).attr('href', contextPath+"map.do");
	        
	    }
		
	} catch(E) {alert(E);}
}

// 사용자 등록
function fn_regUser() {
	COMMON_UTIL.cmWindowOpen('사용자 신청', contextPath + "user/sysuser/registSysUser.do", 450, 750, true, $("#wnd_id").val(), 'center', null, 'N'); //밑에가있음..;
}

// 아이디 / 비밀번호 문의
function fn_selUserInfo() {
	alert("아이디 및 비밀번호 문의는 관리자 이메일로 요청 바랍니다.\n관리자 이메일 : 0000@0000.00");
}

function fn_selUserInfoPth() {
    alert("아이디 및 비밀번호 문의는 관리자에게 연락 바랍니다.\n경기도청 도로관리과 (031-8030-3974)");
}