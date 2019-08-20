<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관할구역 검색</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->

<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>

<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="sidxexcel" name="sidxexcel" value=""/>
<input type="hidden" id="sordexcel" name="sordexcel" value=""/>

<div class="tabcont">
    <div class="fl bgsch">
        <h3>검색조건</h3>
        <div class="schbx mt10">
            <ul class="sch">
                <li class="wid100">
                    <label>관할기관</label>
                    <select id="DEPT_CODE" name="DEPT_CODE" class="input" style="width:204px;" onkeydown="fnCheckEnter(event);" >
                        <option value="">== 전체 ==</option>
                        <c:forEach items="${deptcodeList }" var="deptcode">
                            <option value="${deptcode.DEPT_CODE }">${deptcode.LOWEST_DEPT_NM }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>담당자</label>
                    <input type="text" name="SCH_CHARGER_NM" id="SCH_CHARGER_NM" value="" style="width:197px;" class="MX_80 CS_50 input" onkeydown="fnCheckEnter(event);" />
                </li>
                <li class="wid100" style="margin-top: 10px;">
                    <a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a>
                </li>
                <li class="wid100" style="margin-top: 10px;">
                    <span style="font-size: 12px;color: #676a6d;font-weight: bold;">19년 00월 부터 국토부앱, 시·군관리 신고 조회가능</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="fr listbx">
        <h3>관할구역 상세조회
            <a href="#" onclick='fnLocDraw()' id='locDraw' style='float:right; line-height:20px; margin:10px 80px; ' class='graybtn' >선택영역 표시 지우기</a>
            <!-- <a href='fnLocDraw()' id='locDraw' style='float:right; line-height:20px; margin:10px 80px; display:none;' class='graybtn' >선택영역 표시 지우기</a> -->
        </h3>
        <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
                <table id="gridArea"></table>
                <div id="gridPager"></div>
            </div>
            <div class="mt10 tc">
                <div class="fr">
                    <a href="#" onclick="fn_cnptncExcel();" class="schbtn">엑셀다운로드</a>
                </div>
            </div>
        </div>
    </div>
</div>

</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javascript" language="javascript" defer="defer">

var rowId = 0;
var lastRow = 0;
var g2Id;
var total_cnt = 0;


var sessionScope = "${sessionScope}";
var session_deptCode = "${sessionScope.userinfo.DEPT_CODE}";

var sessionRole = "${sessionScope.userinfo.REQ_MENUACC_ROLE}";
var roleArr = sessionRole.trim().split(",");
var sessionSys = "${sessionScope.system}";

// 2018. 03. 09. YYK. 관리자권한에 따른 수정 가능여부설정
var usrGrp = "${sessionScope.userinfo.REQ_USER_GRP}";


//페이지 로딩 초기 설정
$( document ).ready(function() {
/*
	// 선택영역 표시 지우기 활성화
    if (parent.gMap.getLayerByName('GAttrLayer').features.length > 0) {
        $('#locDraw').show();
    }
 */
/*
    // 초기 dept_code 설정
    $('#DEPT_CODE').val(session_deptCode);
 */
    $('#gridPager_center .ui-icon').click(function(e){
        lastsel = 0;
    })

    // 전화번호 입력
    $(document).on("keyup", "#gridArea tr input[name='CTTPC']", function(e){

        var s = $(this).val();
        s = COMMON_UTIL.phoneNumFomatter(s);
        $(this).val(s);
    });


  // 2018. 01. 04. JOY. 경기도로 모니터링단 시스템
  // 필수액션 : 서브메뉴 버튼영역 hide =============== //
  parent.$(".tab_wrap").css("margin-left", "0px");
  // ================================================= //

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/pothole/cmptnc/selectCmptncInfoList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: $("#frm").cmSerializeObject()
        //,postData: postData
        ,ignoreCase: true
        ,colNames:["G2_ID","CPT_MNG_NO","DEPT_CODE",/*"노선등급","노선번호","노선명", "시점명","종점명", */ "관할기관","담당자","연락처",/* "연장(km)", */"면적(㎡)", "위치보기", "수정하기"]
        ,colModel:[
            {name:'G2_ID',index:'G2_ID', hidden: true }
            ,{name:'CPT_MNG_NO',index:'CPT_MNG_NO', hidden: true }
            ,{name:'DEPT_CODE',index:'DEPT_CODE', hidden: true}
            //,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:50, sortable:true}
            //,{name:'ROUTE_NO',index:'ROUTE_NO', align:'left', width:80, sortable:true}
            //,{name:'ROUTE_NM',index:'ROUTE_NM', align:'left', width:80, sortable:true}
            /* ,{name:'STRTPT_NM',index:'STRTPT_NM', align:'left', width:80, sortable:true}
            ,{name:'ENDPT_NM',index:'ENDPT_NM', align:'left', width:80, sortable:true} */
            ,{name:'LOWEST_DEPT_NM',index:'LOWEST_DEPT_NM', align:'center', width:100, sortable:true
            	/*
            	, editable:true, edittype:'select'
                , editoptions:{
                    dataUrl: '<c:url value="/"/>' + 'pothole/cmptnc/selectCmptncListGrid.do'
                    , buildSelect: function (data) {
                        value = jQuery.parseJSON(data).list;
                        var result = '<select>';
                            result += '<option value="">없음</option>' ;
                        for(var idx=0; idx < value.length; idx++) {
                        	if ( value[idx].DEPT_CODE != '7000300' ){  // YYK 민자도로사업자 7000300 은 안나오게함
                                result += '<option value="' + value[idx].DEPT_CODE + '">' + value[idx].LOWEST_DEPT_NM + '</option>';
                        	}
                        }
                        result += '</select>';
                        return result;
                    }
                }
             */
            }
            ,{name:'CHARGER_NM',index:'CHARGER_NM', align:'center', width:50, sortable:true, editable:true}
            ,{name:'CTTPC',index:'CTTPC', align:'center', width:80, sortable: false, editable:true}
            //,{name:'LEN',index:'LEN', align:'center', width:50, sortable:true}
            ,{name:'AR',index:'AR', align:'right', width:50, sortable:true, formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0' }}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:40, sortable:false, formatter: fn_create_btn_loc
            	,cellattr: function(rowId, tv, rowObject, cm, rdata) {
            	    if ( rowObject.AR <= 0 ) { return 'colspan=3 , style="text-align:center;"' }
            	}
            }
            ,{name:'btn_update',index:'btn_update', align:'center', width:40, sortable:false, formatter: fn_create_btn_update
            	,cellattr: function(rowId, tv, rowObject, cm, rdata) {
                    if ( rowObject.AR <= 0 ) { return 'style="display:none;"' }
                }
            }
        ]
        ,async : false
        ,sortname: 'deptnum'  //YYK 관할기관 순서 경기도청에 맞추기 위해... (쿼리에서 확인할 수 있다.)
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
            //fnView(rowId);    // 대장 조회
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리

        }
        ,loadBeforeSend:function(tsObj, ajaxParam, settings){
            if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
                delete this.p.postData.nd;
                delete this.p.postData._search;
                this.p.postData.sidx = this.p.sortname;
                $("#sidxexcel").val(this.p.postData.sidx);
                this.p.postData.sord = this.p.sortorder;
                $("#sordexcel").val(this.p.postData.sord);
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});

    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 195);

    setTimeout(function() {
         fn_search();
    }, 500);

});


//검색 처리
function fn_search() {

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: $("#frm").cmSerializeObject()
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);

         // recode 수 (엑셀 저장 자료 없을 경우 경고 출력용)
            total_cnt = data.records;
        }
    }).trigger("reloadGrid");

    lastRow = 0;
}


//위치이동 버튼 생성
function fn_create_btn_loc(cellValue, options, rowObject) {
    if (rowObject.AR > 0 ){ // 수정할 데이터가 없는 경우 수정 버튼 숨김
        return "<a href='#' onclick=\"fn_select_cmptnc('"+ rowObject.DEPT_CODE + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    }
    else{
        return '데이터가 없습니다.';
    }
}


//수정 버튼 생성
function fn_create_btn_update(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"fn_update_btn(" + options.rowId +");\" class='graybtn' >수정</a>";
}



//수정 버튼
function fn_update_btn(rowId){

	if ( usrGrp != 'ROLE_ADMIN' ) {
	    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
	    return;
    }


	var html = '';
	html += '<a href="#" onclick="fn_submit('   ;
	html += rowId   ;
	html += ')" class="schbtn" style="margin-right:3px;" '   ;
	html += ' ">저장</a>' ;
	html += '<a href="#" onclick="fn_submit_cancel();" class="schbtn">취소</a>' ;

	if ( !$('#gridArea tr a').hasClass("schbtn") ){
        $('#gridArea tr:eq('+rowId+') td:last-child').html(html);
        $('#gridArea tr td').removeClass('tdClick');
        $('#gridArea .graybtn').parents('td').siblings().addClass('tdClick');
        $('#gridArea').jqGrid('editRow',rowId, false);
        lastRow = rowId;


	/* 	   if (num == 1){
		        $('#gridArea tr:eq('+rowId+') a').eq(1).text("저장").attr("onclick", "fn_submit(" + rowId +")").removeClass("graybtn").addClass("schbtn");
		        $('#gridArea tr td').removeClass('tdClick');
		        $('#gridArea .graybtn').parents('td').siblings().addClass('tdClick');
		        $('#gridArea').jqGrid('editRow',rowId, false);
		        lastRow = rowId;
		    }
		    */
/*
	    // 엔터 입력시 submit
	    $(document).on('keydown', '#gridArea input, #gridArea select', function(key){
	        if(key.keyCode == 13){
	            fn_submit(rowId, deptCode)
	        }
	    });
 */

		//$('#gridArea a[class!=schbtn]').click(function(e){
		//외부 클릭시 수정취소 경고
		$('#gridArea .tdClick').click(function(e){
		   if (lastRow != 0  ){
			   fn_submit_cancel();
		   }
		})
    }

	// 엔터입력시 수정
	$("body .editable").on('keydown', function(e){
	    if ( event.keyCode == 13 ) {
	    	  fn_submit(rowId);
	    }
	});
}

// 수정 취소
function fn_submit_cancel(){
	//if (confirm('저장 버튼을 누를 경우, 수정이 취소되고 원래 상태로 돌아갑니다. 정말 취소하시겠습니까?')){
	if (confirm('취소하시겠습니까?')){
        fn_search();
	}
}

// 수정
function fn_submit(rowId){

    if (confirm('저장하시겠습니까?')){

        var rowData = $("#gridArea").getRowData(rowId);
        var inputData = $("#gridArea tr[id="+rowId+"]").cmSerializeObject();
        var dataSum = $.extend({},rowData , inputData);

        $.ajax({
            url: contextPath + 'pothole/cmptnc/updateCmptnc.do'
            ,data : dataSum
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(ajaxData){
                alert(ajaxData.searchVO.resultMSG);
                if(ajaxData.searchVO.resultSuccess == 'true'){
                	rowId = 0;
                	lastRow = 0;
                    fn_search()
                }
            }
            ,error: function(a,b,msg){}
        });
    }
}



//위치조회
function fn_select_cmptnc(deptCode){

	var tables = ["CMPTNC_ZONE"];
	var attribute = {
	        attributes : {
	            fillColor : '#ff0000',
	            strokeColor : '#ff0000'
	        }
	};

	// 하단 목록 창 내리기
	parent.bottomClose();

	parent.gMap.getLayerByName('GOverlapLayer').removeFeatures(parent.gMap.getLayerByName('GOverlapLayer').features);

	// 시 선택시 구영역 포함 select (2019년 고도화 사업)
	if(deptCode == "3740000") {

		deptCode = "3740000,3750000,3760000,3770000,5610000";

	} else if(deptCode == "3940000") {

		deptCode = "3940000,3950000,3960100,4100100";

    } else if(deptCode == "4050000") {

    	deptCode = "4050000,5620000,5630000,5640000";

    } else if(deptCode == "3780000") {

    	deptCode = "3780000,3790000,3800000,3810000";

    } else if(deptCode == "3930000") {

    	deptCode = "3930000,5550000,5560000";

    } else if(deptCode == "3830000") {

    	deptCode = "3830000,3840000,3850000";

    } else if(deptCode == "5530000") {

        deptCode = "5530072,5530073,5530074";

    } else if(deptCode == "3910000") {

        deptCode = "3910019,3910029,3910038";

    }

	var value = deptCode;
	var vArr = [];

	vArr = value.split(",");

    var fArr = [];

    for ( var i = 0 ; i < vArr.length ; i++ ) {
        fArr[i] = "DEPT_CODE";
    }

    var fields = [fArr];
    var arr_value = [vArr];

    if(vArr.length > 1) {

    	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, arr_value, null, "OR", attribute );

    } else {
    	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fArr, vArr, null, "OR", attribute );
    }

}

//2018.03.26 YYK. 선택영역 표시 지우기 버튼 클릭시 위치보기 레이어와 선택영역 표시 지우기 버튼 제거
function fnLocDraw(){
    parent.gMap.getLayerByName('GAttrLayer').removeFeatures(parent.gMap.getLayerByName('GAttrLayer').features);

}

//엑셀 다운로드
function fn_cnptncExcel(){
	if (total_cnt > 0){
		if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
            COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/api/cmptnc/cmptncListExcel.do'/>?sidx=" + $("#sidxexcel").val() + "&sord=" + $("#sordexcel").val(), "");
	    }
	}
	else {
        alert("데이터가 없습니다.");
    }
}

//enter key
function fnCheckEnter(event) {
    if ( event.keyCode == 13 ) {
        fn_search();
    }
}




</script>

</body>
</html>