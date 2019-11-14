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
<!-- <title>조사자료상세조회 </title> -->
<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/egovCvpl.css'/>"/>
 -->
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	// 달력 생성
	COMMON_UTIL.cmCreateDatepickerLinked('SCH_SRVY_DE1', 'SCH_SRVY_DE2', 30);
	
	// 달력 생성
	$("#SCH_SRVY_DE1").val(fnGetTimeStamp(-30));//30일 전으로세팅
	$("#SCH_SRVY_DE2").val(fnGetTimeStamp());   //오늘날짜
	
	//최근 등록날짜 검색
	fn_searchCreatDt();
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/srvyDtaExcelList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SRVY_NO", "파일명", "조사일자", "등록일자", "자료건수",  "포장상태 평가 여부", "다운로드(excel)"]
	   	,colModel:[
			{name:'SRVY_NO',index:'SRVY_NO', hidden: true}
			//,{name:'FILE_NO',index:'FILE_NO', align:'left', width:50, sortable: true}
			,{name:'FILE_NM',index:'FILE_NM', align:'center', width:200, sortable: true}
			,{name:'SRVY_DE',index:'SRVY_DE', align:'center', width:100, sortable: true, formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'CREAT_DT',index:'CREAT_DT', align:'center', width:100, sortable: true}
			,{name:'DATA_CO',index:'DATA_CO', align:'right', width:80, sortable: true,
				formatter: 'number', formatoptions: { decimalSeparator: ".", thousandsSeparator: ",", decimalPlaces: 0, defaultValue: '0' }}
			,{name:'EVL_PROCESS_AT',index:'EVL_PROCESS_AT', align:'center', width:100, sortable: false, formatter: fnYNtoKr}
			,{name:'btn_dwn',index:'btn_dwn', align:'center', width:70, sortable:false, formatter: fn_create_btn_down} 
	   	]
		,async : false
		,sortname: 'CREAT_DT'
	    ,sortorder: "desc" 
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
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
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);
	
	}); 
	
	//검색 처리
	function fnSearch() {
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		//,postData:  JSON.stringify( $("#frm").cmSerializeObject())  
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		$('.fr h3').text('조사자료 이력 목록(총'+$("#gridArea").getGridParam("records")+'건)');
	   		
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}


function fnGetTimeStamp(add) {
	var d = new Date();
	if(add != null){
		d.setDate(add + d.getDate());
	}
	var s = leadingZeros(d.getFullYear(), 4) + "-" + leadingZeros(d.getMonth() + 1, 2) + "-" + leadingZeros(d.getDate(), 2);
	return s;
}

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();
	
	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}


//파일 다운로드 버튼 생성
function fn_create_btn_down(cellValue, options, rowObject) {
	var btn = "";
	
	if(rowObject.FILE_NO == null || rowObject.FILE_NO == ''){
		btn +='';
	}else{
		// 클릭시 파일 다운로드
		btn = "<a href='#' onclick=\"fn_file_download('" + rowObject.FILE_NO + "');\"><img src='../images/ic_download.png' style='height:13px;cursor:pointer;' alt='다운로드' title='다운로드'></a>";
	}
	
	return btn;
}

//파일 다운로드
function fn_file_download(fileNo){
	COMMON_FILE.cmFileDownloadByNo(fileNo);
}

function fn_setCreatDt(creatDtId){
	//선택한 등록 날짜 active
	fn_liActAt(creatDtId);
	//선택한 조사자료 등록 날짜에 대한 조회 
	$("#SRVY_DE").val($("#"+creatDtId).text());
	fnSearch();
}

function fn_liActAt(creatDtId){
//선택한 등록 날짜 active
	$(".lst li").removeClass("active");
	$("#"+creatDtId).addClass("active");
}

//조사자료 등록 날짜 조회
function fn_searchCreatDt(){
	var creatDtList	= '';
	
	$.ajax({
		 url: '<c:url value="/"/>'+'api/srvy/searchCreatDt.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify( $("#frm").cmSerializeObject())
		,dataType: 'json'
		,success: function (data) {
       	var dataList = data.data;
			if(dataList.length !=0){
				for(var i=0; i<dataList.length; i++){
					creatDtList	+= '<li id='+i+' onclick=fn_setCreatDt('+i+')><img src="<%=request.getContextPath() %>/images/ic_date.png" alt="날짜선택" class="vm"/>'+data.data[i].SRVY_DE+'</li>';
				}
				$(".lst").empty().append(creatDtList);
				
				//최근 등록 조사자료 조회
				fn_setCreatDt(0);
			}else{
				//조회된 data가 없을 경우
				$(".lst").empty().append('');
				$("#SRVY_DE").val('');
				fnSearch();
				alert('해당 기간에 등록된 데이터가 없습니다. 재검색 하시기 바랍니다.');
			}
       },
       error: function () {
           return;
       }
	});
	
}


//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
	var roadNo = $("#ROAD_NO").val();
	
	if(roadNo == "") {
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
		}
		,error: function(a,b,msg){
			
		}
	});
}

function fnYNtoKr(cellValue, options, rowObject) {
    
    if ( rowObject.EVL_PROCESS_AT == "Y" ) {
        
        return "예";
        
    } else {
        
        return "아니오";
        
    }
    
    return "";
    
}
function fnExcel() {
	
	var roadNo = $("#ROAD_NO").val();
	var schSrvyDE1 = $("#SCH_SRVY_DE1").val();
	var schSrvyDE2 = $("#SCH_SRVY_DE2").val();
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/srvy/srvyDtaExcelListExcel.do?roadNo"+roadNo+"&schSrvyDE1="+schSrvyDE1+"&schSrvyDE2="+schSrvyDE2+"'/>", "");
		
		//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/stats/selectSurveyAllLenStatsExcel.do'/>", "");
	}
}
</script>
</head>
<body id="wrap">
<form id="frm" name="frm" method="post" action="">
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SRVY_DE" name="SRVY_DE" value=""/>

<div id="sch_cnt01" class="tabcont">
<div class="fl bgsch" style="width:250px">
	<h3>검색조건</h3>
	<!--검색영역-->
	 <div class="schbx mt10">
	 	<ul class="sch">
	 		<li class="wid100">
                <label>노선번호</label>
                <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" style="width: 116px;" class="input">
	                <option value="">== 전체 ==</option>
	        		<c:forEach items="${roadNoList }" var="roadNo">
	        			<option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
	        		</c:forEach>
                </select>
            </li>
            <li>
                <label>노선명</label>
                <input type="text" id="ROAD_NAME"  name="ROAD_NAME" readonly="readonly" value="" style="width: 108px;" class="MX_80 CS_50 input" />
	            </li>
	 		</li>
			<li class="wid100">
                <label>조사일자</label>
            </li>
            <li>
                <span class="calendar">
                    <input type="text" id="SCH_SRVY_DE1" name="SCH_SRVY_DE1" style="width:60px" />

                </span> ~
                <span class="calendar">
                    <input type="text" id="SCH_SRVY_DE2" name="SCH_SRVY_DE2" style="width:60px" />
                </span>
            </li>
			<li class="wid100">
                <a href="#" class="schbtn dpb" onclick="fn_searchCreatDt();"> 검색</a>
            </li>
		</ul>
	</div>
</div>
		<div class="fr listbx">
			<h3>조사자료 이력조회</h3>
			<p class="location">
				<span>조사자료 관리</span>
				<span>조사자료 이력조회</span>
				<strong>조사자료 이력 목록</strong>
	        </p>
		    <div class="mt10 mr10">
				<div id="div_grid" style="position:left; width: 100%; height: 240px;">
					<table id="gridArea"></table>
					<div id="gridPager"></div>
				</div>	
			</div>
			<div class="tc mr10" >
			<div class="fr">
            <a href="#" class="schbtn" onclick="fnExcel();">엑셀저장</a>
			</div>
			</div>
		</div>
</div>
</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->


</body>
</html>