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

<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="<c:out value="${cntrwkDtlVO.CNTRWK_ID}" />"/>
<input type="hidden" id="RPAIR_BEGIN_DE" name="RPAIR_BEGIN_DE"/>
<input type="hidden" id="RPAIR_END_DE" name="RPAIR_END_DE"/>
<input type="hidden" id="SPRVISOR_NM" name="SPRVISOR_NM"/>
<input type="hidden" id="SPRVISOR_TELNO" name="SPRVISOR_TELNO"/>
<input type="hidden" id="SPRVISN_CO_NO" name="SPRVISN_CO_NO"/>
<input type="hidden" id="SPRVISN_CO_NM" name="SPRVISN_CO_NM"/>
<input type="hidden" id="SPRVISN_CO_RPRSNT_NO" name="SPRVISN_CO_RPRSNT_NO"/>
<input type="hidden" id="SPRVISN_CO_RPRSNTV_NM" name="SPRVISN_CO_RPRSNTV_NM"/>
<input type="hidden" id="CNSTRCT_CO_NO" name="CNSTRCT_CO_NO"/>
<input type="hidden" id="CNSTRCT_CO_NM" name="CNSTRCT_CO_NM"/>
<input type="hidden" id="CNSTRCT_CO_RPRSNTV_NM" name="CNSTRCT_CO_RPRSNTV_NM"/>
<input type="hidden" id="CNSTRCT_CO_TELNO" name="CNSTRCT_CO_TELNO"/>
<input type="hidden" id="SPT_AGENT_NM" name="SPT_AGENT_NM"/>
<input type="hidden" id="SPT_AGENT_TELNO" name="SPT_AGENT_TELNO"/>
		<div class="xlsUp">
			 <div class="btnAreaTop">
				<%-- <a href="#" class="whitebtn fr mt10" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 50);" ><img src="<c:url value='/images/ic_folder.png'/>" alt="" /> 파일선택</a> --%>				
				<input multiple="multiple" type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display:none;" class="whitebtn fr mt10" id="addFile" style="width:80px;"/>
			</div>
			<div id="file_list">
           		<ul name="fileSet">
           		</ul>
           	</div>
		    <p class="note">※ 첨부 파일은 엑셀(xlsx)파일만 업로드 가능합니다.</p>
		    
		    <div class="btnArea">
		    	<input type="button" class="btn pri btnFile" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 50);" value="파일선택">
				<input type="button" class="btn" onclick="fn_file_upload()" value="파일전송">
				<!--
				<input type="button" class="btn" onclick="javascript: fn_close_dialog();" value="닫기">
				-->
			</div>
		            	  					
		</div>
</form>
<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$( document ).ready(function() {
	
	var postDatas =  {CNTRWK_ID : $('#CNTRWK_ID').val()};

    $.ajax({
        url: "<c:url value='/api/cntrwk/selectCntrwk.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {
            if (jdata !=null && jdata.CNTRWK_ID!="")
          	 {
          		//alert('검색된 포장공사 정보가  있습니다.');
          		$('#RPAIR_BEGIN_DE').val(jdata.STRWRK_DE);/* 공사정보.착공_일자 */
          		$('#RPAIR_END_DE').val(jdata.COMPET_DE); /* 공사정보.준공_일자 */
          		 
          		$('#SPRVISOR_NM').val(jdata.SPRVISR_NM);/* 공사정보.감독원_명 */           		
          		$('#SPRVISOR_TELNO').val(jdata.SPRVISR_TELNO);/* 공사정보.감독원_전화번호 */
          		
          		$('#SPRVISN_CO_NO').val(jdata.SPRVISN_CO_NO); /* 공사정보.감리_업체_번호 */
          		$('#SPRVISN_CO_NM').val(jdata.SPRVISN_CO_NM); /* 공사정보.감리_업체_명 */           		
          		$('#SPRVISN_CO_RPRSNT_NO').val(jdata.SPRVISN_CO_RPRSNT_TELNO);/* 공사정보.감리_업체_대표_전화번호 */
          		$('#SPRVISN_CO_RPRSNTV_NM').val(jdata.SPRVISN_CO_RPRSNTV_NM); /* 공사정보.감리_업체_대표자_명 */
          		
          		$('#CNSTRCT_CO_NO').val(jdata.CNSTRCT_CO_NO); /* 공사정보.시공_업체_번호 */
          		$('#CNSTRCT_CO_NM').val(jdata.CNSTRCT_CO_NM); /* 공사정보.시공_업체_명 */
          		$('#CNSTRCT_CO_RPRSNTV_NM').val(jdata.CNSTRCT_CO_RPRSNTV_NM); /* 공사정보.시공_업체_대표자_명 */
          		$('#CNSTRCT_CO_TELNO').val(jdata.CNSTRCT_CO_TELNO);/* 공사정보.시공_업체_전화번호 */
          		$('#SPT_AGENT_NM').val(jdata.SPT_AGENT_NM); /* 공사정보.현장_대리인_명 */
          		$('#SPT_AGENT_TELNO').val(jdata.SPT_AGENT_TELNO); /* 공사정보.현장_대리인_전화번호 */

          	 }
            else
               {
           	 alert('검색된 포장공사 정보가 없습니다.');
               }
        },

        error: function () {
            alert("값을 가져올 수 없습니다.");
            return;
        }
    });
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
    
    var params = new Array ("CNTRWK_ID","RPAIR_BEGIN_DE","RPAIR_END_DE","SPRVISOR_NM","SPRVISOR_TELNO","SPRVISN_CO_NO","SPRVISN_CO_NM","SPRVISN_CO_RPRSNT_NO","SPRVISN_CO_RPRSNTV_NM","CNSTRCT_CO_NO","CNSTRCT_CO_NM","CNSTRCT_CO_RPRSNTV_NM","CNSTRCT_CO_TELNO","SPT_AGENT_NM","SPT_AGENT_TELNO");
    
    for(var j in params){
    	if ($('#'+ params[j]).val() != "" ) {
    		formData.append(params[j], $('#' + params[j]).val());
    	}
    }
    
	if(len < 1){
		alert("포장공사 이력 파일을 선택해주세요.");
		return;
	}
	
	var url = contextPath + '/cntrwkdtl/cntrwkDtlExcelUpload.do'
	 $.ajax({
	       url: url,	
	       contentType: false,
	       processData: false,
	       data: formData,
	       type: 'POST',
	       async: false,
	       success: function(result){
	    	
	    	    alert(result.resultMsg);
	    	    
		   	    $('#filefrm')[0].reset(); //폼 초기화(리셋);
		   		$('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
		   		COMMON_FILE.clearMultiFile('#file_list', '#addFile');		   	
		   		COMMON_UTIL.cmHideProgressBar();
				
		   		var opener_id = $('#opener_id').val();
				var wnd_id = $('#wnd_id').val();
				COMMON_UTIL.cmGetWindowOpener(opener_id).fnSearch();
		   	    COMMON_UTIL.cmWindowClose(wnd_id);
				
	       },
	        error: function () {
	        	alert("등록에 실패하였습니다.");
	            return;
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