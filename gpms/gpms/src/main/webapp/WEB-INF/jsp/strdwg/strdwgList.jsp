<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>노선 통합 검색</title>
<%@ include file="/include/common_head.jsp" %>

<script type="text/javascript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	var postData = {"USE_AT":"Y"};
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/strdwg/selectStrDwgList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["ROAD_NO","노선 번호","구간 번호","도면 파일명","도면 명칭"]
	   	,colModel:[
	   	    {name:'ROAD_NO',index:'ROAD_NO', hidden: true}
	   	 	,{name:'ROAD_NO_VAL',index:'ROAD_NO_VAL', align:'center', width:80, sortable:true}
			,{name:'SECT',index:'SECT', align:'center', width:80, sortable:true}
			,{name:'DWG_FILE',index:'DWG_FILE', align:'center', width:120, sortable:true}
			,{name:'DWG_NAME',index:'DWG_NAME', align:'center', width:120, sortable:true}
	   	]
		,async : false
		,sortname: 'ROAD_NO'
	    ,sortorder: "asc" 
	    ,rowNum: ''
	   	//,rowList: [20,50,100,500]
	    ,viewrecords: false
	   	//,pager: '#gridPager'
	    ,rownumbers: false
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			//fnView(rowId);	// 대장 조회
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
			}
		}
	   	,beforeSelectRow: function(rowid, e) {
	   		if(e.type == "click"){
	   			var $grid = $("#gridArea");
	   		 	$grid.jqGrid('setSelection', rowid, false);
	   			return false;
	   		} 
	   	    return true;
	   	}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: true
		,multiboxonly: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);
	
	fn_search();
}); 

//검색 처리
function fn_search() {
	$(".cbox").prop("checked", false);
	$("#DWG_FILE").val("")
	
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
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
	
	//구간정보 위치 조회
	var roadNo = $("#ROAD_NO").val();
	var sect = $("#SECT").val();
	
	var tables = ["DORO_TOT_GRS80_50"];
	var fields = ["ROAD_NO"];
	var values = [roadNo];
	var attirbute = 
     		{ 
				fillColor : '#0033ff',
				strokeColor : '#0033ff',
				table : "DORO_TOT_GRS80_50",
		    	field : "SECT",
		    	value : sect,
				attributes : {
						fillColor : '#ff0000',
						strokeColor : '#ff0000'
					}
			};
	
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, null, attirbute);
}

//도면 다운로드
function fn_download() {
	var seqList = "";
	var seqListArr = [];
	var obj = $("#gridArea");
	var idx = obj.jqGrid('getGridParam', 'selarrrow');
	
	if(obj.jqGrid("getGridParam","records")==0 || idx.length<1){
		alert("다운로드할 구조물 도면을 선택해주세요");
		return false;
	}
	
	for(var i=0; i<idx.length; i++){
		var value = obj.jqGrid('getCell', idx[i], 'DWG_FILE' );
		seqListArr.push( value );
	}
	
	seqList = seqListArr.join(",");
	$("#DWG_FILE").val(seqList);
		
	
	$.ajax({
		url: contextPath + 'api/strdwg/checkStrDwg.do'
		,type: 'post'
		,dataType: 'json'
		,data: {"ROAD_NO": $("#ROAD_NO").val(),"DWG_FILE": seqList}
		,success: function(data){
			
			if(data.fileList.length > 0 ){
				var msg = (idx.length*2) + "개의 도면 중 " + data.fileList[0] +" 외 총 " + (data.fileList.length-1) + "개의 도면 파일이 없습니다.\n계속 다운로드를 진행하시겠습니까?";
				
				if(confirm(msg)){    	
					$("#frm").attr("action",contextPath + "api/strdwg/downloadStrDwg.do");
					$("#frm").attr("target","proc_frm");
					$("#frm").submit();
				}	
			}else{
				var msg = (idx.length*2) + "개의 구조물 도면을 다운로드 하시겠습니까?";
				
				if(confirm(msg)){    	
					$("#frm").attr("action",contextPath + "api/strdwg/downloadStrDwg.do");
					$("#frm").attr("target","proc_frm");
					$("#frm").submit();
				}		
			}
			
			$("#DWG_NAME").val("");
		}
		,error: function(a,b,msg){
		}
	});
}

</script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="ROAD_NO" name="ROAD_NO" value="${strDwgVO.ROAD_NO }"/>
<input type="hidden" id="DWG_FILE" name="DWG_FILE" value=""/>
<div id="sch_cnt01" class="tabcont">
   <div class="bctab">
       	<ul>
           <li><a href="#" onclick="COMMON_UTIL.cmMoveUrl('dwginfo/selectDwgInfoList.do?ROAD_NO=${strDwgVO.ROAD_NO}')">단위도면 정보</a></li>
           <li class="on"><a href="#" onclick="COMMON_UTIL.cmMoveUrl('strdwg/selectStrDwgList.do?ROAD_NO=${strDwgVO.ROAD_NO}')" >구조물 도면 정보</a></li>
       	</ul>
		<h3 class="info">
			<a href="#" class="whitebtn dpib ml10 vm" onclick="COMMON_UTIL.cmMoveUrl('routeinfo/selectRouteInfoList.do')"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
		</h3>
   </div>
   <p class="location">
       <span>노선검색</span>
       <span>노선 데이터로 검색</span>
       <strong>구조물 도면 정보</strong>
   </p>
   <div class="mt10 ml10 mr10">
       <div class="tr mb5">
           <label class="mr10">구간</label>
           <select id="SECT" name="SECT" alt="구간" class="input" style="width:95px;">
           		<c:forEach items="${dwgSectList }" var="dwgSect">
	       			<option value="${dwgSect.SECT }">${dwgSect.SECT }</option>
	       		</c:forEach>
           </select>
            <a href="#" class="schbtn" onclick="javascript:fn_search();">구간 위치보기</a>
       </div>
	   <div style="width: 100%;">
			<div id="div_grid" style="width:100%; height:204px;">
				<table id="gridArea"></table>
			</div>
		</div>
        <div class="mt10 tc">
            <div class="fr"><a href="#" onclick="fn_download();" class="schbtn">다운로드</a></div>
        </div>
    </div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>