<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/*
$( document ).ready(function() {
	
	cmCreateDatepicker('SRVY_DE', 15, "/images/btn_calendar.gif", "+0D");

	COMMON_FILE.clearMultiFile('#file_list', '#addFile');
	
	$("#addFile").change(function(){
		var extn = ["zip"];
		COMMON_FILE.setMultiFiles('#file_list', this, extn, 'N', 50);
	});

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'srvy/api/srvyDtaUploadResultList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject())
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		//,colNames:["작업일자","성공 건수","실패 건수", "등록자", "CRTR_NO"]
		,colNames:["노선번호","노선명","행선","차로","성공여부","진행률","등록일자","등록자","분석자료","CRTR_NO"]
	   	,colModel:[
			 {name:'route_CODE',index:'route_CODE', align:'center', width:70}
			,{name:'road_NAME',index:'road_NAME', align:'center', width:70}
			,{name:'direct_CODE',index:'direct_CODE', align:'center', width:70}
			,{name:'track',index:'TRACK', align:'center', width:70}
			,{name:'success_KND',index:'success_KND', align:'center', width:70, formatter:fn_btn_formatter_fail}
			,{name:'data_CO',index:'data_CO', align:'center', width:70}
			,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:70}
			,{name:'crtr_NM',index:'crtr_NM', align:'center', width:50}
			,{name:'CRTR_NO',index:'CRTR_NO', align:'center', width:50}
			,{name:'CRTR_NO',index:'CRTR_NO', hidden: true}
	   	]
		,async : false
	   	,sortname: 'CREAT_DT'
	    ,sortorder: "desc"
	   	,rowNum: 50
	   	,rowList: [10,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
		//,scroll: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea', 'div_grid', 200);

	fn_search();

	//fn_change_roadNm();
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

    formData.append("SRVY_DE", $('#SRVY_DE').val());
    formData.append("ROAD_NO", $('#ROAD_NO').val());
    formData.append("ROAD_NAME", $('#ROAD_NAME').val());
    formData.append("DIRECT_CODE", $('#DIRECT_CODE').val());
    formData.append("TRACK", $('#TRACK').val());

	if(len < 1){
		alert("조사자료 파일을 선택해주세요.");
		return;
	}

	parent.$("#dvProgress").dialog("open");
	parent.$("#t_progress").text("파일 전송 중 입니다.");


    $.ajax({
       url: contextPath + "srvy/srvyDtaFileUpload.do",
       processData: false,
       contentType: false,
       data: formData,
       type: 'POST',
       success: function(data){
	   	    $('#filefrm')[0].reset(); //폼 초기화(리셋);
	   		$('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
	   		COMMON_FILE.clearMultiFile('#file_list', '#addFile');
	   		parent.$("#dvProgress").dialog("close");
	   		
	   		alert(data.resultCode);
   			alert(data.resultMsg);
		   	if(!data.result) {
		   		return;
		   	} else {
	   	        fn_search();
		   	}
   			
       }
       	,error: function(a,b,msg){

    	}
   });

}

/* //파일 전송 callback
function fn_file_upload_callback(){
	$('#filefrm')[0].reset(); //폼 초기화(리셋);
	fn_search();
	$('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
	COMMON_FILE.clearMultiFile('#file_list', '#addFile');
	$("#dvProgress").dialog("close");

}
 */
//검색 처리
function fn_search() {
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}


//버튼생성
function fn_btn_formatter_comp(cellValue, options, rowObject){
	var btn ="";
	btn  = "<span style='width:50px;display:inline-block;'><font>" + rowObject.SUCCESS_CNT + "</font></span>";
	btn += "<a href='#' class='schbtn' onclick=\"fn_upd_res_log('" + rowObject.CREAT_DT + "', '" + rowObject.CRTR_NO + "', 'COMP', '" + rowObject.SUCCESS_CNT + "');\"><font color=white>상세조회</font></a>";
	return btn;
}

function fn_btn_formatter_fail(cellValue, options, rowObject){
	var btn ="";
	btn  = "<span style='width:50px;display:inline-block;'><font>" + rowObject.success_KND + "</font></span>";
	if(rowObject.success_KND == 'F') {
		btn += "<a href='#' class='schbtn' onclick=\"fn_upd_res_log('" + rowObject.CREAT_DT + "', '" + rowObject.CRTR_NO + "');\"><font color=white>상세조회</font></a>";
	}
	
	return btn;
}

// 상세조회
function fn_upd_res_log(creatDt, creatNum){
	$("#CREAT_DT").val(creatDt);
	$("#CRTR_NO").val(creatNum);
	COMMON_UTIL.cmMovePage("viewForm", contextPath + "srvydtaexcel/selectSrvyDtaExcelFailList.do");
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();

    if(roadNo == "" /* || roadGrad == "" */) {
        $("#ROAD_NAME").val("");
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
            //$("#ROAD_GRAD").val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){

        }
    });
}

function fnCheckNumber(obj){

    $(obj).keyup(function() {
        var value = $(this).val().replace(/[^0-9]/g, "");
        $(this).val(value);
    });
}

function fnPrcProcess(srvyNo) {
	
	var params = {
			"SRVY_NO" : srvyNo
 		};
	
	$.ajax({
        url: contextPath + 'srvy/saveSrvyDta.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify(params)
        ,success: function(data){
            alert(data.result);
            alert(data.resultMSG);
            fn_search();
        }
        ,error: function(a,b,msg){

        }
    });
}

var cmCreateDatepicker = function(_oId, _oSize, imgPath, maxDate){
    var vbtnImg = contextPath+ "/images/ico_date.png";
    if(imgPath!=null && imgPath!=undefined  && imgPath!=""){
        vbtnImg = contextPath+ imgPath;
    }
    $( "#"+_oId ).width(_oSize*8).datepicker({
         changeMonth: true
        ,changeYear: true
        ,numberOfMonths: 1
        ,showOn: "button"
        ,buttonImage: vbtnImg
        ,buttonImageOnly: true
        ,maxDate: maxDate
    });
};


</script>
</head>
<body style="height:326px;">
<form id="viewForm" name="viewForm" style="display:none;">
<input type="hidden" id="CREAT_DT" name="CREAT_DT" value=""/>
<input type="hidden" id="CRTR_NO" name="CRTR_NO" value=""/>
<input type="hidden" id="PROCESS_STTUS" name="PROCESS_STTUS" value=""/>
<input type="hidden" id="cnt" name="cnt" value=""/>
</form>
<form id="filefrm" name="filefrm" method="post" action="" style="display:none;">
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
</form>



<div class="tabcont">
	<div class="fl bgsch">
			<h3>파일첨부
				<a href="#" class="whitebtn fr mt10" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 50);" ><img src="<c:url value='/images/ic_folder.png'/>" alt="" /> 파일선택</a>
				<input multiple="multiple" type="file" accept=".zip" style="display:none;" class="whitebtn fr mt10" id="addFile" style="width:80px;"/>
			</h3>
			
			
			<ul >
				<li >
				<label>조사일자</label>
				<span class="calendar btn_calendar">
			 		<input type="text" id="SRVY_DE" name="SRVY_DE" /></span>
				</li>
                <li >
                <label>노선번호</label>
                    <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input">
                        <option value="">= 전체 =</option>
                        <c:forEach items="${ roadNoList }" var="roadNo">
                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>노선명</label>
                    <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" />
                </li>
                
                <li>
                    <label>행선</label>
                    <select id="DIRECT_CODE" name="DIRECT_CODE" alt="행선" style="width:70px;" class="input">
                        <option value="">= 전체 =</option>
                        <option value="S">상행</option>
                        <option value="E">하행</option>
                    </select>

                    <label>차로</label>
                    <input type="number" name="TRACK" id="TRACK" value="" style="width:57px;" onkeydown="fnCheckNumber(this);" maxLength="1" class="MX_80 CS_50 DT_INT input" />
                </li>
                
			</ul>
	        
	           
			<div class="schbx mt10">
			    <ul class="sch" style="padding: 55px 10px 5px;">
			        <li>
			            <div class="btfilebx scroll" style="width:258px; height:40px" id="file_list">
			            	<ul name="fileSet">
			            	</ul>
			            </div>
			        </li>
			        <li class="wid100 af">
			        	※ 첨부 파일은 압축(zip) 파일만 업로드 가능합니다.
			        </li>
			        <li class="wid100 af">
			            <a href="#" onclick="fn_file_upload()" class="schbtn fr" style="width:43%">파일전송</a>
		            </li>
		        </ul>
		    </div>

	</div>
	<div class="fr listbx">
	    <h3>조사자료 등록 대상목록
	        <a href="#" class="whitebtn dpib ml10 vm" onclick="fn_search();"><img src="<c:url value='/images/ic_reset.png'/>" alt="새로고침" title="새로고침" /></a>
	    </h3>
	    <p class="location">
	        <span>조사자료 관리</span>
	        <span>조사자료 등록</span>
	        <strong>조사자료 등록 대상목록</strong>
	    </p>
    	<div class="mt10 ml10 mr10">
    		<form id="frm" name="frm" method="post" action="">
			    <div id="div_grid" style="width:100%; height:206px">
			        <table id="gridArea"></table>
					<div id="gridPager"></div>
	            </div>
            </form>
      	</div>
	</div>

</div>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>