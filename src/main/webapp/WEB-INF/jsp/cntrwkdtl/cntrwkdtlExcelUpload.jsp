<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/include/common_head.jsp" %>
<title>Insert title here</title>
</head>
<body class="cu">
<form id="filefrm" name="filefrm" method="post" action="" >
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- Key  -->
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="<c:out value="${cntrwkVO.CNTRWK_ID}" />"/>	
		<div class="fl bgsch">
			 <ul class="sch">
				<a href="#" class="whitebtn fr mt10" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 50);" ><img src="<c:url value='/images/ic_folder.png'/>" alt="" /> 파일선택</a>
				<input multiple="multiple" type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display:none;" class="whitebtn fr mt10" id="addFile" style="width:80px;"/>
			</ul>
			<div class="schbx mt10">
				<ul class="sch">
		       	 <li>
		            	<div class="btfilebx scroll" style="width:258px; height:215px" id="file_list">
		            		<ul name="fileSet">
		            		</ul>
		            	</div>
		        	</li>
		       		<li class="wid100 af">
						※ 첨부 파일은 엑셀(xlsx)파일만 업로드 가능합니다.
		        	</li>
					<div class="fr">
						<a href="#" onclick="fn_file_upload()" class="schbtn">파일전송</a>
						<a href="#" class="schbtn" onclick="javascript: fn_close_dialog();">닫기</a>
					</div>	
				</ul>	
			</div>	    					
		</div>
		
</form>		
<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$( document ).ready(function() {
	alert(${cntrwkDtlVO});
	$('#CNTRWK_ID').val(${cntrwkDtlVO.CNTRWK_ID});
});
$("#addFile").change(function(){
	var extn = ["xlsx"];
	COMMON_FILE.setMultiFiles('#file_list', this, extn, 'N', 50);
});

//파일 전송
function fn_file_upload(){
	
	var files = COMMON_FILE.getMultiFileIns();

	var form = $('#filefrm')[0];
    var formData = new FormData(form);

    var len = 0;
    for(var i in files){
    	if(files[i] == i){continue;}
    	formData.append("files", files[i]);
    	len ++;
    }

	if(len < 1){
		alert("포장공사 이력 파일을 선택해주세요.");
		return;
	}
	
	var cntrwk_id = $('#CNTRWK_ID').val();
	alert(cntrwk_id);
	
	 $.ajax({
	      url: "<c:url value='/cntrwkdtl/cntrwkDtlExcelUpload.do'/>",
	       contentType: false,
	       data: formData,
	       dataType: "text",
	       type: 'POST',
	       success: function(result){
	    	   var returnMsg = result;
	    	    alert(returnMsg);
		   	    $('#filefrm')[0].reset(); //폼 초기화(리셋);
		   		$('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
		   		COMMON_FILE.clearMultiFile('#file_list', '#addFile');
		   	
		   		var opener_id = $("#opener_id").val();
		   		var wnd_id = $("#wnd_id").val();
		   		COMMON_UTIL.cmGetWindowOpener(opener_id).fnSearch();
		   	    COMMON_UTIL.cmWindowClose(wnd_id);
	       }
	   });
}

function fn_close_dialog(){
    if( confirm('<spring:message code="warn.cancel.msg" />') ) {
    var wnd_id = $("#wnd_id").val();
    COMMON_UTIL.cmWindowClose(wnd_id);
    }
}

</script>
</body>
</html>