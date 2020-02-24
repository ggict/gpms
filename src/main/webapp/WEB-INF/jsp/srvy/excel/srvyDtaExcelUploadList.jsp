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

<form id="filefrm" name="filefrm" method="post" action="" style="display:none;">
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
</form>


		<div class="xlsUp">
			<Strong>파일 목록</Strong>
			<div>
				<c:set var="cnt" value="0"/>
				<c:forEach items="${files}" var="files">
					<li style="list-style:none;">
		           	    <span class="filename">
						        <a href="#" onclick="fn_fileclick(this);return false;" data-filename="${files}">${files}</a>
		           	    </span>
		           	</li>
		        </c:forEach>
           	</div>

			<form id="searchForm" name="searchForm" action="<c:url value="/srvy/srvyDtaFileUpload.do" />" method="post" enctype="multipart/form-data">
				 <div class="btnAreaTop">
					<%-- <a href="#" class="whitebtn fr mt10" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 50);" ><img src="<c:url value='/images/ic_folder.png'/>" alt="" /> 파일선택</a> --%>
					<input type="file" accept=".zip" style="display:none;" class="whitebtn fr mt10" id="addFile" name="files" style="width:80px;"/>
				</div>
			</form>

			<div id="file_list">
           		<ul name="fileSet">
           		</ul>
           	</div>

		    <p class="note">※ 첨부 파일은 압축(zip)파일만 업로드 가능합니다.</p>

		    <div class="btnArea">
		    	<input type="button" class="btn pri btnFile" onclick="fn_file_select();" value="파일선택">
		    	<input type="button" class="btn" onclick="fn_file_upload();" value="파일업로드">
			</div>

		</div>

<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$( document ).ready(function() {
});

function fn_fileclick(target){
	COMMON_FILE.clearMultiFile($("#file_list", parent.content_area.document), $("#files", parent.content_area.document));

	parent.content_area.file_change($(target).data("filename"));
	$("#fileMode", parent.content_area.document).val("SERVER");
	$("#fileName", parent.content_area.document).val($(target).data("filename"));
	fn_close_dialog();
}

function fn_file_select() {
	COMMON_FILE.addMultiFile($("#file_list", parent.content_area.document), $("#files", parent.content_area.document), 1);
	$("#fileMode", parent.content_area.document).val("LOCAL");
	fn_close_dialog();
}

function fn_file_upload(){
    var files = COMMON_FILE.getMultiFileIns();

    var form = $('#filefrm')[0];
    var formData = new FormData(form);

    var len = 0;
    for(var i in files){
        if(files[i] == i){continue;}
        formData.append("addFile", files[i]);
        len ++;
    }

    if(len < 1){
        alert("조사자료 파일을 선택해주세요.");
        return;
    }

    $("#searchForm").submit();

}

function fn_close_dialog(){
    var wnd_id = $("#wnd_id").val();
    COMMON_UTIL.cmWindowClose(wnd_id);
}

</script>
</body>
</html>