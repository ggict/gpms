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
<style type="text/css">
    .progress { position:relative; width:100%; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
    .bar { background-color: #b4c8f5; width:0%; height:20px; border-radius: 3px; }
    .percent { position:absolute; display:inline-block; top:3px; left:48%; }
</style>
<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정/*
$( document ).ready(function() {

    cmCreateDatepicker('SRVY_DE', 15, "/images/btn_calendar.gif", "+0D");

    COMMON_FILE.clearMultiFile('#file_list', '#files');

    $("#files").change(function(){
        var extn = ["zip"];
        COMMON_FILE.setMultiFiles('#file_list', this, extn, 'N', 1);
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
        ,colNames:["노선번호","노선명","행선","차로","조사명","성공여부","분석진행률","조사일자","등록자","분석자료","재분석","포장상태<br/>재평가","등록번호","조사번호"]
        ,colModel:[
             {name:'route_CODE',index:'route_CODE', align:'center', width:70}
            ,{name:'road_NAME',index:'road_NAME', align:'center', width:70}
            ,{name:'direct_CODE',index:'direct_CODE', align:'center', width:70}
            ,{name:'track',index:'TRACK', align:'center', width:70}
            ,{name:'srvy_NM',index:'srvy_nm', align:'left', width:150}
            ,{name:'success_KND',index:'success_KND', align:'center', width:70, formatter:fn_btn_formatter_fail}
            ,{name:'data_CO',index:'data_CO', align:'center', width:70, formatter:fn_data_progrs_cnt}
            ,{name:'srvy_DE',index:'srvy_DE', align:'center', width:70}
            ,{name:'crtr_NM',index:'crtr_NM', align:'center', width:50}
            ,{name:'분석자료',index:'분석자료', align:'center', width:50, formatter:fn_btn_anal_data}
            ,{name:'재분석',index:'재분석', align:'center', width:50, formatter:fn_btn_anal_reset}
            ,{name:'포장상태 재평가',index:'포장상태 재평가', align:'center', width:50, formatter:fn_btn_eval_reset}
            ,{name:'CRTR_NO',index:'CRTR_NO', hidden: true}
            ,{name:'SRVY_NO',index:'SRVY_NO', hidden: true}
        ]
        ,async : false
        ,sortname: 'srvy_DE'
        ,sortorder: "desc"
        ,rowNum: 50
        ,rowList: [10,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
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

    // #####################################
    // ## 파일업로드 초기화
    // #####################################
    var bar = $('.bar');
    var percent = $('.percent');
    var status = $('#status');

    $('#searchForm').ajaxForm({
        beforeSend: function() {
            status.empty();
            var percentVal = '0%';
            bar.width(percentVal)
            percent.html(percentVal);
        },
        uploadProgress: function(event, position, total, percentComplete) {
            var percentVal = percentComplete + '%';
            bar.width(percentVal)
            percent.html(percentVal);
        },
        success: function(data) {
            var percentVal = '100%';
            bar.width(percentVal)
            percent.html(percentVal);

            $('#filefrm')[0].reset(); //폼 초기화(리셋);
            $('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
            COMMON_FILE.clearMultiFile('#file_list', '#files');
            parent.$("#dvProgress").dialog("close");

            //alert(data.resultCode);
            alert(data.resultMsg);
            if(!data.result) {
                return;
            } else {
                fn_search();
            }
        },
        complete: function(data) {
//             status.html(xhr.responseText);
        }
    });
    // #####################################
});

//파일 전송
function fn_file_upload(){
    var files = COMMON_FILE.getMultiFileIns();

    var form = $('#filefrm')[0];
    var formData = new FormData(form);

    var len = 0;
    for(var i in files){
        if(!files[i].name || files[i].name == ''){ continue; }
		// 조사파일 중복 업로드 확인
		var isExists = false;
        $.ajax({
            type : 'POST',
            url : contextPath + 'srvy/srvyDtaFileCheck.do',
            data : {
                'srvyDtaFileName' : files[i].name
            },
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            dataType : 'json',
            async    : false,
            cache    : false, /*ie11버전에선 이 설정이 없으면 ajax 파싱 안됨. ie버그*/
            success : function(res) {
            	if (res && res.existsAt == 'Y'){
                	isExists = true;
            	}
            },

            error: function(xhr) {
                console.error(xhr + ' error!');
            }
        });

        if (isExists){
            alert('동일한 조사자료 파일이 존재합니다.');
            //$('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
            //COMMON_FILE.clearMultiFile('#file_list', '#addFile');
            return;
        }

        formData.append('files', files[i]);
        len ++;
    }

    if($('#SRVY_DE').val() == '' ) {
        alert('조사일자를 선택하세요');
        $('#SRVY_DE').focus();
        return;
    }
    if($('#ROAD_NO').val() == '' ) {
        alert('노선번호를 선택하세요');
        $('#ROAD_NO').focus();
        return;
    }
    if($('#DIRECT_CODE').val() == '' ) {
        alert('행선을 선택하세요');
        $('#DIRECT_CODE').focus();
        return;
    }
    if($('#TRACK').val() == '' ) {
        alert('차로를 입력하세요');
        $('#TRACK').focus();
        return;
    }

    formData.append('SRVY_DE', $('#SRVY_DE').val());
    formData.append('ROAD_NO', $('#ROAD_NO').val());
    formData.append('ROAD_NAME', $('#ROAD_NAME').val());
    formData.append('DIRECT_CODE', $('#DIRECT_CODE').val());
    formData.append('TRACK', $('#TRACK').val());

    if(len < 1){
        alert('조사자료 파일을 선택해주세요.');
        return;
    }

    parent.$('#dvProgress').dialog('open');
    parent.$('#t_progress').text('파일 전송 중 입니다.');

	//     $.ajax({
	//        url: contextPath + "srvy/srvyDtaFileUpload.do",
	//        processData: false,
	//        contentType: false,
	//        data: formData,
	//        type: 'POST',
	//        success: function(data){
	//          $('#filefrm')[0].reset(); //폼 초기화(리셋);
	//          $('#filefrm input:file').MultiFile('reset'); //멀티파일 초기화
	//          COMMON_FILE.clearMultiFile('#file_list', '#addFile');
	//          parent.$("#dvProgress").dialog("close");

	//          //alert(data.resultCode);
	//              alert(data.resultMsg);
	//          if(!data.result) {
	//              return;
	//          } else {
	//              fn_search();
	//          }

	//        }
	//          ,error: function(a,b,msg){

	//      }
	//    });

    $('#searchForm').submit();
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
	var postData = $("#frm").cmSerializeObject();

	var srvyDe = $('#SRVY_DE').val().replace(/-/gi, "");
	var roadNo = $('#ROAD_NO').val();
	var directCode = $('#DIRECT_CODE').val();
	var track = $('#TRACK').val();

	if (srvyDe != null && srvyDe != ''){
		postData["SCH_SRVY_DE"] = srvyDe;
	} else {
		postData["SCH_SRVY_DE"] = '';
	}

	if (roadNo != null && roadNo != ''){
		postData["SCH_ROAD_NO"] = roadNo;
	} else {
		postData["SCH_ROAD_NO"] = '';
	}

	if (directCode != null && directCode != ''){
		postData["SCH_DIRECT_CODE"] = directCode;
	} else {
		postData["SCH_DIRECT_CODE"] = '';
	}

	if (track != null && track != ''){
		postData["SCH_TRACK"] = track;
	} else {
		postData["SCH_TRACK"] = '';
	}

	if ($("input:checkbox[id='chkNotFinish']").is(':checked')){
		postData["CHK_NOT_FINISH"] = 'Y';
	} else {
		postData["CHK_NOT_FINISH"] = 'N';
	}

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
        }
    }).trigger("reloadGrid");
}


function fn_data_progrs_cnt(cellValue, options, rowObject){
    var str ="";
    str += rowObject.data_PROGRS_CNT + " / " + rowObject.data_CO;
    return str;
}

function fn_btn_anal_data(cellValue, options, rowObject){
    var btn ="";
    if(rowObject.success_KND == 'S') {
        btn += "<a href='#' class='schbtn' onclick=\"fn_anal_data_popup('" + rowObject.SRVY_NO + "');\"><font color=white>분석자료</font></a>";
    }
    return btn;
}

function fn_anal_data_popup(srvyNo){
    $("#SRVY_NO").val(srvyNo);
    COMMON_UTIL.cmWindowOpen('분석자료 조회', "<c:url value='/analDataPopupList.do'/>?SRVY_NO="+srvyNo, 1500, 1200, false, null, 'center');
}

function fn_btn_anal_reset(cellValue, options, rowObject){
    var btn ="";
    if(rowObject.success_KND == 'S') {
        btn += "<a href='#' class='schbtn' onclick=\"fn_anal_reset('" + rowObject.SRVY_NO + "');\"><font color=white>재분석</font></a>";
    }
    return btn;
}

function fn_anal_reset(srvyNo) {
	if ( confirm("기존 자료를 삭제하고 분석작업을 다시 진행합니다.\r분석작업은 많은 시간이 소요됩니다.\r분석작업을 다시 진행하시겠습니까?") ) {
    	$.ajax({
            url: contextPath + 'srvy/api/analReset.do'
            ,type: 'post'
            ,dataType: 'json'
            ,contentType : 'application/json'
            ,data : JSON.stringify({SRVY_NO : srvyNo})
            ,success: function(data){
                if ( data ) {
                	alert(data.resultMsg);
                }
            }
            ,error: function(a,b,msg){
                alert("오류가 발생하였습니다.");
            }
        });
	}
}

function fn_btn_eval_reset(cellValue, options, rowObject){
    var btn ="";
    if(rowObject.success_KND == 'S') {
        btn += "<a href='#' class='schbtn' onclick=\"fn_eval_reset('" + rowObject.SRVY_NO + "');\"><font color=white>재평가</font></a>";
    }
    return btn;
}

function fn_eval_reset(srvyNo) {
	if ( confirm("재평가를 진행하시겠습니까?") ) {
		parent.$("#dvProgress").dialog("open");
	    parent.$("#t_progress").text("재평가 중입니다.");

        $.ajax({
            url: contextPath + 'srvy/api/evalReset.do'
            ,type: 'post'
            ,dataType: 'json'
            ,contentType : 'application/json'
            ,data : JSON.stringify({SRVY_NO : srvyNo})
            ,success: function(data){
                parent.$("#dvProgress").dialog("close");
            	if ( data ) {
                    alert(data.resultMsg);
                }
            }
            ,error: function(a,b,msg){
                parent.$("#dvProgress").dialog("close");
            	alert("오류가 발생하였습니다.");
            }
        });
	}
}

//버튼생성
function fn_btn_formatter_fail(cellValue, options, rowObject){
    var btn ="", knd="";

    if(rowObject.success_KND == 'F') knd="실패";
    else knd="성공";
    btn  = "<span style='width:50px;display:inline-block;'><font>" + knd + "</font></span>";
    if(rowObject.success_KND == 'F') {
        btn += "<a href='#' class='schbtn' onclick=\"fn_upd_res_log('" + rowObject.SRVY_NO + "' , '" + rowObject.CRTR_NO + "');\"><font color=white>상세조회</font></a>";
    }

    return btn;
}

// 상세조회
function fn_upd_res_log(srvyNo,crtrNo){
    $("#SRVY_NO").val(srvyNo);
    $("#CRTR_NO").val(crtrNo);
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
<input type="hidden" id="PROCESS_STTUS" name="PROCESS_STTUS" value="PCST0003"/>
<input type="hidden" id="SRVY_NO" name="SRVY_NO" value=""/>
<input type="hidden" id="CRTR_NO" name="CRTR_NO" value=""/>

</form>
<form id="filefrm" name="filefrm" method="post" action="" style="display:none;">
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
</form>



<div class="tabcont">
    <header class="loc">
            <div class="container">
                <span class="locationHeader">
                    <select name="">
                        <option value="">조사자료관리</option>
                    </select>
                    <select name="">
                        <option value="">조사자료등록</option>
                    </select>
                    <h2 class="h2">조사자료 등록 대상목록</h2>
                </span>
                <a href="#" class="btnRefresh" onclick="fn_search();"><img src="/gpms/images/ic_reset.png" alt="새로고침"></a>
                <label style="margin-left: 20px;">분석 완료 제외</label><input type="checkbox" id="chkNotFinish" style="-webkit-appearance: auto;">
            </div>
        </header>

        <div class="contents container">
          <article class="div3">
            <form id="searchForm" name="searchForm" action="<c:url value="/srvy/srvyDtaFileUpload.do" />" method="post" enctype="multipart/form-data">
                <h3 class="h3">파일첨부</h3>
                <span class="haderBtn">
                    <a href="#" class="whitebtn fr mt10" onclick="COMMON_FILE.addMultiFile('#file_list', '#files', 1);" ><img src="<c:url value='/images/ic_folder.png'/>" alt="" /> 파일선택</a>
                    <!-- <input type="button" class="btn pri btnFile" onclick="COMMON_FILE.addMultiFile('#file_list', '#addFile', 1);"  value="파일선택"> -->
                    <input id="files" name="files" multiple="multiple" type="file" accept=".zip" style="display:none;" class="whitebtn fr mt10" style="width:80px;"/>
                </span>

                <div class="table">
                    <table>
                        <tbody>
                            <tr>
                                <td class="th"><label for="SRVY_DE">조사일자</labed></td>
                                <td>
                                    <span class="calendar btn_calendar">
                                        <input type="text" id="SRVY_DE" name="SRVY_DE" />
                                    </span>
                                </td>
                                <td class="th"><label for="ROAD_NO">노선번호</label></td>
                                <td>
                                    <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input">
                                        <option value="">전체</option>
                                        <c:forEach items="${ roadNoList }" var="roadNo">
                                            <option value="${ roadNo.ROAD_NO }">${ roadNo.ROAD_NO_VAL }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="th"><label for="ROAD_NAME">노선명</label></td>
                                <td colspan="3">
                                    <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td class="th"><label>행선</label></td>
                                <td>
                                    <select id="DIRECT_CODE" name="DIRECT_CODE">
                                        <option value="">전체</option>
                                        <option value="S">상행</option>
                                        <option value="E">하행</option>
                                    </select>
                                </td>
                                <td class="th"><label>차로</label></td>
                                <td>
                                    <input type="number" name="TRACK" id="TRACK" value="" style="width:57px;" onkeydown="fnCheckNumber(this);" maxLength="1" class="MX_80 CS_50 DT_INT input" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="btfilebx" id="file_list">
                        <ul name="fileSet"></ul>
                        <input type="button" class="btn pri" onclick="fn_file_upload()" value="전송" />
                    </div>

                    <p>※ 첨부 파일은 압축(zip) 파일만 업로드 가능합니다.</p>

                    <div class="progress">
                        <div class="bar"></div >
                        <div class="percent">0%</div >
                    </div>

                    <div id="status"></div>

                </div>
            </form>
        </article>

        <article class="div9">
            <h3 class="h3">조사자료 등록 대상목록</h3>
            <form id="frm" name="frm" method="post" action="">
                <div id="div_grid" class="table">
                    <table id="gridArea"></table>
                </div>
                <div id="gridPager"></div>
            </form>
        </article>
    </div>
</div>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>