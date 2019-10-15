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
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 상세보기로 넘어온 경우 파라미터 받기
    var param = {};
    param["CELL_ID"] = "${mummSctnSrvyDtaVO.CELL_ID}";

    // 조사정보에서 넘어온 경우 '조사정보 상세보기' 버튼 show
    var objId = "${mummSctnSrvyDtaVO.OBJECT_ID}";
    var directFlag = "${mummSctnSrvyDtaVO.DIRECT_FLAG}";

    if ( directFlag == "N" ) {
        $("#btnGotoResearchInfo").show();

    } else if ( directFlag == "NY" ) {
        $("#btnGotoResearchInfo").show();
        $("#btnShowImg").show();

    }

    // 포장상태 평가정보 (평균)
    fnSelectAvg(param);

    // 포장상태 예측정보
    fnSelectPredctCen(param);

    // 공사정보 jqGrid
    fnSelectCntrwkInfo(param);

    // btnGotoResearchInfo 클릭 이벤트
    /* $("#btnGotoResearchInfo").click( function() {

        parent.bottomOpen();

        parent.$(".btmenuarea").find("div").hide();
        parent.$("#sub_mummSctnSrvyDtaDetail").show();
        parent.$("#sub_mummSctnSrvyDtaDetail").find("li:eq(0)").attr("class", "sel");

        var flag = "N";

        if ( $("#btnShowImg").css("display") != "none" ) {

            flag = "NY";

        }

        parent.COMMON_UTIL.cmMenuUrlContent('mng/mummsctnsrvydta/mummSctnSrvyDtaDetail.do?OBJECT_ID=' + objId + '&DIRECT_FLAG=' + flag, true);

    }); */

    $(window).on('resize', function(){
    	fnSelectPredctCen(param);
	});

});

//포장상태 평가정보 (평균)
function fnSelectAvg(param) {

	var postDatas = { "CELL_ID": param.CELL_ID };
	
	$.ajax({
	    url: "<c:url value='/api/mummsctnsrvydta/selectMummSctnSrvyDtaListByItgrtnAvg.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {

			// 평균 데이터 바인딩
            if ( jdata != undefined && jdata != null ) {
                if ( jdata.AC_IDX == null || jdata.BC_IDX == null || jdata.LC_IDX == null || jdata.TC_IDX == null
                        || jdata.PTCHG_IDX == null || jdata.POTHOLE_IDX == null || jdata.RD_IDX == null || jdata.RCI == null ) {
                    // null인 값이 있는 경우 초기값 그대로 표출
                    return;

                }

	   		    // 주 파손
	   		    var crVal = "";

	   		    if ( jdata.CNTL_DFECT != "DFCT0009" ) {
					var codeNm = jdata.CODE_NM;

					if ( codeNm == "AC" ) {
					    crVal = "거북등균열";

					} else if ( codeNm == "BC" ) {
					    crVal = "블럭균열";

					} else if ( codeNm == "LC" ) {
					    crVal = "선형균열";

					} else if ( codeNm == "PTCHG" ) {
					    crVal = "패칭";

					} else if ( codeNm == "POTHOLE" ) {
					    crVal = "포트홀";

					} else if ( codeNm == "RD" ) {
					    crVal = "소성변형";

					} else if ( codeNm == "RCI" || codeNm == "IRI" ) {
					    crVal = "종단평탄성";

					}

	   		    } else {
		   		     var maxVal = Math.max( jdata.AC_IDX, jdata.BC_IDX, jdata.LC_IDX, jdata.PTCHG_IDX, jdata.POTHOLE_IDX, jdata.RD_IDX, jdata.RCI );
	                 var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
	                 var valArr = [ jdata.AC_IDX, jdata.BC_IDX, jdata.LC_IDX, jdata.PTCHG_IDX, jdata.POTHOLE_IDX, jdata.RD_IDX, jdata.RCI ];

	   		        if ( maxVal == 0 ) {
	   		            // max 값이 0인 경우는 파손없음
	   		            crVal += "파손없음";

	   		        } else {
		   		        crVal += "<br />복합파손 <br /> (";

				   		for ( var i = 0; i < valArr.length; i++ ) {
				   		    // max값과 같은 경우 텍스트 추가
				   		    if ( valArr[i] == maxVal ) {
				   		     	if ( i != 0 ) {
					   		        crVal += ", ";
					   		    }
				   		        crVal += nameArr[i];
				   		    }
				   		}
				   		crVal += ")";

				   		$("#crVal").css({"line-height": "11px", "font-size": "14px"});
	   		        }
	   		    }

	   		    // 파손원인
	   		 	var clmt = jdata.DMG_CUZ_CLMT;
	   			var vmtc = jdata.DMG_CUZ_VMTC;
	   			var etc = jdata.DMG_CUZ_ETC;
	   			var cuz = "";

	   			if ( clmt == vmtc && vmtc == etc ) {
	   			    if ( etc == 0 ) {
	   			     	cuz = "파손없음";

	   			    } else if ( etc != 0 ) {
	   			     	cuz = "<br />교통량/하부불량,<br />기후, 기타";

	   			    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});
	   			    }

	   			} else if ( clmt > vmtc && clmt > etc ) {
	   				cuz = "기후";

	   			} else if ( vmtc > clmt && vmtc > etc ) {
	   				cuz = "교통량/하부불량";
	   				$("#dmgCuz").css({"font-size": "14px"});

	   			} else if ( etc > clmt && etc > vmtc ) {
	   				cuz = "기타";

	   			} else if ( clmt == vmtc && clmt > etc ) {
	   			    cuz = "<br />교통량/하부불량,<br />기후";
	   			    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	   			} else if ( clmt == etc && clmt > vmtc ) {
	   			 	cuz = "기후, 기타";

	   			} else if ( vmtc == etc && vmtc > clmt ) {
	   			 	cuz = "<br />교통량/하부불량,<br />기타";
	   			    $("#dmgCuz").css({"line-height": "11px", "font-size": "14px"});

	   			} else {
	   				cuz = "";

	   			}

	   			$("#gpci").html( parseFloat(jdata.GPCI).toFixed(2) );
	   			$("#crVal").html(crVal);
	   			$("#dmgCuz").html(cuz);

                var table = $("#avgTable td");
                table.eq(0).html( parseFloat(10 - jdata.AC_IDX).toFixed(2) );
                table.eq(1).html( parseFloat(10 - jdata.LC_IDX).toFixed(2) );
                table.eq(2).html( parseFloat(10 - jdata.BC_IDX).toFixed(2) );
                table.eq(3).html( parseFloat(10 - jdata.PTCHG_IDX).toFixed(2) );
                table.eq(4).html( parseFloat(10 - jdata.POTHOLE_IDX).toFixed(2) );
                table.eq(5).html( parseFloat(10 - jdata.RD_IDX).toFixed(2) );
                table.eq(6).html( parseFloat(10 - jdata.RCI).toFixed(2) );

			} else {
				alert('검색된 포장공사 정보가 없습니다.');
				return;
			}
		},
		error: function () {
			alert("값을 가져올 수 없습니다.");
			return;
		}
	});
}

//포장상태 예측정도
function fnSelectPredctCen(param) {

	$.ajax({
		 url: '<c:url value="/"/>'+'api/mummsctnsrvydta/selectPredctCentry.do'
		,type: 'post'
		,contentType: 'application/json'
		,data: JSON.stringify({"CELL_ID" : param.CELL_ID})
		,dataType: 'json'
		,success: function (data) {
			if(data.length < 1){return;}

			drawPredctChart(data);
			drawTable(data);
       },
       error: function () {
       	alert("오류가 발생하였습니다. 재검색 하시기 바랍니다.");
           return;
       }
	});
}

function drawPredctChart(dataList){
	var gPredctYear	= [];
	/* var acData		= [];
	var lcData		= [];
	var rciData		= []; */
	var gpciData	= [];
	var gpci;

	for(var i=0; i<dataList.length; i++){
		gPredctYear.push(dataList[i].PREDCT_YEAR);
		/* acData.push(parseFloat(dataList[i].AC_IDX).toFixed(2));
		lcData.push(parseFloat(dataList[i].LC_IDX).toFixed(2));
		rciData.push(parseFloat(dataList[i].RCI).toFixed(2)); */

		gpci = parseFloat(dataList[i].GPCI);
		gpciData.push(gpci.toFixed(2));
	}

	require.config({	paths: {echarts: contextPath + 'extLib/echarts'}	});

	require([	"echarts", "echarts/chart/bar", "echarts/chart/line"	],
	        function (ec) {
					 var myChart = ec.init(document.getElementById('predctChart'));
					 myChart.setOption({
				           	title 	: {	text: '포장상태 예측정보'
				           			  , padding : 10
			           			  	  , itemGap : 10
			           			  	  ,	textStyle : {
				           				  color : '#222222'
				           				, fontSize : 15
			           			  	}},
				               tooltip : {	trigger: 'axis'				},
				               legend: {
				            	   x: 'right',
				                   y: 'center',
				                   data: ['GPCI'] //'거북등균열','선형균열','종단평탄성',
				               },
				               toolbox : {	show: true,
				            	   x : "150",
				            	   padding : 10,
				    			feature: {
					    			dataView : {show: true, readOnly: false} 	// 상세조회
					    		}
				    		},
				    		textStyle : {fontFamily : "NanumGothicBold"},
						    grid: {
						    	/* width : rw+'px',
						    	x : 25,
						    	y2 : 50 */
						    	left: '3%',
						        right: '20%',
						        bottom: '3%',
						        containLabel: true
						    },
				               xAxis : [{
				            	   		name : '공용연수',
				               			type : 'category',
					            		axisLabel : {
					                		show:true,
					                		interval: 0,
					                		rotate: -45
					            		},
				               			data : gPredctYear
				               		}],
				               yAxis : [{	name : 'GPCI',		type : 'value'		}],
				               series : [
				              /*      {
				                       name: '거북등균열',
				                       type: 'line',
				                       symbol:'emptyCircle',
				                       data: acData
				                   },
				                   {
				                       name: '선형균열',
				                       type: 'line',
				                       symbol:'emptyCircle',
				                       data: lcData
				                   },
				                   {
				                       name: '종단평탄성',
				                       type: 'line',
				                       symbol:'emptyCircle',
				                       data: rciData
				                   }, */
				                   {
				                       name: 'GPCI',
				                       type: 'line',
				                       symbol:'emptyCircle',
				                       data: gpciData
				                   }
				               ]
				           });
	});
}

function drawTable(dataList){

		var mainData	= dataList;
		var tHtml 		= '';

		for(var i=0; i<mainData.length; i++){
			if(mainData[i].RPAIR_TA_AT == 'Y'){
				tHtml	+= '<tr style="background-color:#ffe45c;">';
			}else{
				tHtml	+= '<tr>';
			}

			if(i == 0){
				tHtml	+= '<td align="center" class="bg">평가년도</td>';
			}else{
				tHtml	+= '<td align="center" class="bg">예측 ' + i + '년</td>';
			}

			var gpci = parseFloat(mainData[i].GPCI);

			tHtml	+= '<td align="center" class="bg">'				+	mainData[i].PREDCT_YEAR	+ '</td>';
			/* tHtml	+= '<td align="center" class="bg">'				+	parseFloat(mainData[i].AC_IDX).toFixed(2) + '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	parseFloat(mainData[i].LC_IDX).toFixed(2) + '</td>';
			tHtml	+= '<td align="center" class="bg">'				+	parseFloat(mainData[i].RCI).toFixed(2)	+ '</td>'; */
			tHtml	+= '<td align="center" class="bg">'				+	gpci.toFixed(2)	+ '</td>';
			tHtml	+= '</tr>';
		}

		$('#diagram tbody').empty().append(tHtml);
	}


function fnSelectCntrwkInfo(param) {

	// 전송 데이터 조합
	var postData = $("#frm2").cmSerializeObject();
	postData["CELL_ID"] = param.CELL_ID;

	// 검색 목록 그리드 구성
	$("#gridArea2").jqGrid({

		url: '<c:url value="/"/>'+'api/cntrwk/selectCntrwkListByCell.do'
		,width: true
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: JSON.stringify(postData)
		,ignoreCase: true
		,colNames:["공사ID","세부공사ID","셀번호","공사명","착공일","준공일","위치보기"]
	   	,colModel:[
	   	    {name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
	   	    ,{name:'DETAIL_CNTRWK_ID',index:'DETAIL_CNTRWK_ID', hidden: true}
	   	 	,{name:'CELL_ID',index:'CELL_ID', align:'center', width:90}
			,{name:'FULL_CNTRWK_NM',index:'FULL_CNTRWK_NM', align:'left', width:100}
			,{name:'STRWRK_DE',index:'STRWRK_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'COMPET_DE',index:'COMPET_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false , formatter: fnCreateBtnCntrwk}
	   	]
		,async : false
		,sortname: 'CELL_ID'
	    ,sortorder: "asc"
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager2'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
		,ondblClickRow: function( rowId ) {

		    fnView(rowId);	// 대장 조회

		}
	   	,onSelectRow: function(rowId) { }
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
	   	, loadComplete: function() {

	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager2_left").css('width', '');
	   		$("#gridPager2_center").css('width', '100%');
	   	}
		,multiselect: false
		,multiboxonly: false
	}).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', 180);

	fnSearchCntrwk(param.CELL_ID);

}


// 검색 처리 - 공사정보
function fnSearchCntrwk(cellIds) {

	var postData = $("#frm2").cmSerializeObject();
	postData["CELL_ID"] = cellIds;

	$("#gridArea2").jqGrid("setGridParam", {

		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData: postData
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		//총 건수를 목록 상단에 표시
	   		$('#count').text('검색 결과 :'+$("#gridArea2").getGridParam("records")+'건');
	   		//페이지 box가 중간에 오도록 css로 해결
	   		$("#gridPager2_left").css('width', '');

	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, data.records);

	   	 	$("#gridPager2").css("width", "100%");
	   	}
	}).trigger("reloadGrid");
}


// 상세 조회
function fnView ( rowId ) {

	if( $.type( rowId ) === "undefined" || rowId== "" ) {
	    rowId = $("#gridArea2").getGridParam( "selrow" );
	}

	if ( rowId != null ) {
		var rowData = $("#gridArea2").getRowData(rowId);
		var deCntrwkId = rowData["DETAIL_CNTRWK_ID"];
		var cellId = rowData["CELL_ID"];

		COMMON_UTIL.cmWindowOpen('상세정보 조회', "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>?DETAIL_CNTRWK_ID="+deCntrwkId+"&CELL_ID="+cellId, 1000, 1200, false, $("#wnd_id").val(), 'center');

	} else{
	    alert('<spring:message code="warn.checkplz.msg" />');
	}
}

// 위치이동 버튼 생성
function fnCreateBtnCntrwk(cellValue, options, rowObject) {
	return "<a href='#' onclick=\"fnViewLocation('" + rowObject.CNTRWK_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";

}

// 위치조회
function fnViewLocation(val){
	var cntrwkId = val;

	$.ajax({
		url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
		, type: 'post'
		, data: JSON.stringify({"CNTRWK_ID" : cntrwkId })
		, dataType: 'json'
		, contentType : 'application/json'
		, success: function ( res ) {

			var tables = ["CELL_10"];
			var fields = [];
			var values = [];

			if(res.length < 1){
				alert("위치 정보가 존재하지 않습니다.");
				return;
			}

			if(res.length > 1){
				for(var i in res){
					var data = res[i];
					fields.push("CELL_ID");
					values.push(data.PAV_CELL_ID);
				}
			}else{
				fields = "CELL_ID";
				values = res[0].PAV_CELL_ID;
			}

			var attribute = {
		            attributes : {
		                fillColor : '#ffffff',
		                strokeColor : '#ffffff'
		            }
		    };

			// 하단 목록 창 내리기
			parent.bottomClose();

			MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, [fields], [values], null, "OR", attribute);
		}
		, error: function (a, b, msg) { }
	});
}

/* function fnShowImg() {

    $("#btnShowImg").hide();

    var height = $(parent).height();
    var width = $(parent).width();

    parent.$("#dvCellInfoImg").dialog();
    parent.$("#dvCellInfoImg").css("padding", "0px");
    parent.$("#dvCellInfoImg").parent().css({
        "width"     : (width - 2) + "px",
        "height"    : (height - 405) + "px",
        "z-index"   : "9999",
        "left"      : "0px",
        "top"       : "63px"
    });

    parent.$("#dvCellInfoImg").dialog("open");
    parent.$("#btnShowImg").hide();

} */

//평균/개별 모드 전환
function fnShowData() {
  cmMenuUrlMummSctnSrvyDta('mng/mummsctnsrvydta/mummSctnSrvyDtaCell.do?CELL_ID=' + "${mummSctnSrvyDtaVO.CELL_ID}", true);
}

</script>
</head>

<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<%-- <form id="frm" name="frm" method="post" action=""> --%>
<div class="tabcont">
	<div class="">
	    <h3>
	       포장상태 평가정보 상세조회
	       <!-- <a href="#" id="btnGotoResearchInfo" style="display: none; margin: 0px 5px; padding: 5px 10px; border-radius: 50px; width: 150px; text-align: center; color: rgb(255, 255, 255); font-size: 11px; font-weight: bolder; background-color: rgba(69, 135, 255, 1);">조사정보 상세보기</a>
	       <a href="#" id="btnShowImg" onclick="fnShowImg();" style="display: none; padding: 5px 10px; border-radius: 50px; width: 150px; text-align: center; color: rgb(255, 255, 255); font-size: 11px; font-weight: bolder; background-color: gray;">균열분석 이미지</a> -->
        </h3>
        <div class="ytabbx" style="left: 360px; position:absolute; top:0px;">
            <ul class="ytab fl" style="margin-top: 21px;">
                <li class="on">
                    <a href="#" onclick="" style="font-size: 14px;">평균평가정보</a>
                </li>
                <li>
                    <a href="#" onclick="fnShowData();" style="font-size: 14px;">개별평가정보</a>
                </li>
            </ul>
        </div>
	    <p class="location">
	        <span>통합 정보 조회</span>
	        <span>포장상태 평가정보 조회</span>
	        <strong>포장상태 평가정보 상세조회</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
	    	<div id="mummAvg" style="width:25%; float: left; padding: 0px 30px;">
	    		<h3 style="line-height: 30px; font-size: 15px;">포장상태 평가정보 (평가단위:10m셀)</h3>

                <ul class="tblst mt15">
                    <li style="width:21%;border-left:0px" class="brl tc">
                        <span class="circle bc6" id="gpci" style="width: 90%; font-size: 17px;">0</span>
                        <span>GPCI</span>
                    </li>
                    <li style="width:37%" class="brl tc">
                        <span class="circle bc5" id="crVal" style="width: 90%; font-size: 17px;">없음</span>
                        <span>주 파손</span>
                    </li>
                    <li style="width:40%" class="tc">
                        <span class="circle bc7" id="dmgCuz" style="width: 90%; font-size: 17px;">없음</span>
                        <span>파손원인</span>
                    </li>
                </ul>

                <table class="tbview" style="width: 100%; margin-top: 20px; height: 118px;" id="avgTable">
	                <caption>포장상태 평가정보</caption>
	                <colgroup>
	                    <col width="30%" />
	                    <col width="20%" />
	                    <col width="30%" />
	                    <col width="20%" />
	                </colgroup>
	                <tbody style="text-align: center;">
		    			<tr>
		    				<th scope="row">거북등균열</th>
		    				<td>0</td>
		    				<th scope="row">선형균열</th>
		    				<td>0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">블럭균열</th>
		    				<td colspan="3">0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">패칭</th>
		    				<td>0</td>
		    				<th scope="row">포트홀</th>
		    				<td>0</td>
		    			</tr>
		    			<tr>
		    				<th scope="row">소성변형</th>
		    				<td>0</td>
		    				<th scope="row">종단평탄성</th>
		    				<td>0</td>
		    			</tr>
	    			</tbody>

	    		</table>

	    		<span style="font-size: 11px; margin-top: 5px; float: right;">* 포장파손형태 별 포장상태지수 감소값 (10점 만점)</span>
	    	</div>

            <div id="mummPredctList" style="width:33%; float: left;">
            	<div id="predctChart" class="cont_ConBx2" style="width:100%; height:267px; float: left;">
				</div>
				<div class="cont_ListBx" style="display: none;">
					<table class="tblist" border="1" id="diagram">
						<colgroup>
							<col style="width:30%;"	/>
							<col style="width:35%;"	/>
							<%-- <col style="width:15%;"	/>
							<col style="width:15%;"	/>
							<col style="width:15%;"	/> --%>
							<col style="width:35%;"	/>
						</colgroup>
						<thead style="text-align: center;">
							<tr>
								<th scope="col">구분</th>
								<th scope="col">예측년도</th>
								<!-- <th scope="col">거북등균열(%)</th>
								<th scope="col">선형균열(%)</th>
								<th scope="col">종단평탄성(%)</th> -->
								<th scope="col">GPCI</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>

			<div id="mummCntrwkList" style="width:33%; padding-left: 30px; float: left">
				<form id="frm2" name="frm2" method="post" action="">
					<div style="float: left;">
		    			<h3 style="line-height: 30px; font-size: 15px;">포장공사 이력</h3>
						<div id="div_grid2" style="width:100%; float: block; padding-top: 5px;">
							<table id="gridArea2"></table>
							<div id="gridPager2" style='width: 100%;'></div>
						</div>
					</div>
				</form>
			</div>
        </div>
    </div>
</div>

<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>