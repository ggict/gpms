<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script src="<c:url value='/js/common.js'/>"></script>
</head>

<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>

<!-- 필수 파라메터(END) -->
<div class="tabcont">
    <div class="">
        <h3>포장상태 예측정보 상세조회</h3>
        <h5 class="info" style="left: 230px;">
           <a href='#' class='whitebtn dpib ml10 vm' id="back"><img src="<c:url value='/images/ic_back.png'/>" alt="뒤로가기" title="뒤로가기" /></a>
        </h5>
        <p class="location">
            <span>포장상태 예측</span>
            <span>포장상태 예측정보 조회</span>
            <strong>포장상태 예측정보 상세조회</strong>
        </p>
        
        <div class="mt10 ml10 mr10">
        
            <!-- 섹션 기본정보 START -->
            <div  style="width: 33%; float: left;">
            <h3 style="line-height: 30px; font-size: 15px; margin-bottom: 5px;">기본정보</h3>
            <div class="btbx" id="baseInfo">
                <!-- <h4 style="padding: 10px;">기본정보</h4> -->
                <div style="border-radius: 5px; height: 232px; border-top-color: rgb(217, 218, 218); border-top-width: 1px; border-top-style: solid;">
                    <table class="tbin">
                        <caption>조사자료</caption>
                        <colgroup>
                            <col width="15%" />
                            <col width="19%" />
                            <col width="15%" />
                            <col width="19%" />
                            <col width="15%" />
                            <col width="19%" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th class="btth">노선<br/>번호</th>
                                <td>-</td>
                                <th class="btth">노선<br/>명</th>
                                <td>-</td>
                                <th class="btth">관리<br/>기관</th>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th class="btth">차로</th>
                                <td>-</td>
                                <th class="btth">행선</th>
                                <td>-</td>
                                <th class="btth">시종점</th>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th class="btth">도로<br/>등급</th>
                                <td>-</td>
                                <th class="btth">섹션<br/>구분</th>
                                <td colspan="3">-</td>
                            </tr>
                            <tr style="height: 40%;">
                                <td colspan="6" style="padding:15px 0px;">
                                    <ul class="tblst">
                                        <li style="width:27%;border-left:0px" class="brl tc">
                                            <span class="round bc5">
                                                <span><img src="<c:url value='/images/ic_p1.png'/>" alt="주파손" class="ml5" /></span>
                                                <span id="crVal" style="font-size:12px">-</span>
                                            </span>
                                            <span>주 파손</span>
                                        </li>
                                        <li style="width:30%" class="tc">
                                            <span class="round bc7">
                                                <span><img src="<c:url value='/images/ic_p2.png'/>" alt="파손원인" class="ml5" /></span>
                                                <span id="dmgCuz">-</span>
                                            </span>
                                            <span>파손원인</span>
                                        </li>

                                        <li style="width:22%" class="brl tc">
                                            <span class="round bc6">
                                                <img src="<c:url value='/images/ic_p3.png'/>" alt="예측시작년도" class="ml5" />
                                                <span id="calcYear">-</span>
                                            </span>
                                            <span>예측시작년도</span>
                                        </li>
                                        <li style="width:20%" class="tc">
                                            <span class="round bc2">
                                                <img src="<c:url value='/images/ic_p4.png'/>" alt="보수도래시기" class="ml5" />
                                                <span id="prairTa">-</span>
                                            </span>
                                            <span>보수도래시기</span>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
            
            <!-- 섹션 기본정보 END -->
            
            <!-- 포장상태 예측정보 START -->
            <!-- <div id="graph" style="width: 33%; float: left;">
                test
            </div> -->
            <div id="mummPredctList" style="width:33%; float: left;">
                <div id="predctChart" class="cont_ConBx2" style="width:100%; height:267px; float: left;">
                </div>
                <div class="cont_ListBx" style="display: none;">
                	<h3 style="line-height: 30px; font-size: 15px;margin-bottom: 5px;">포장상태 조사자료 이력</h3>
                	<table class="tblist" border="1" class="mb10">
                	<colgroup>
                            <col style="width:30%;" />
                            <col style="width:35%;" />
                            <col style="width:35%;" />
                        </colgroup>
                        <thead style="text-align: center;">
                            <tr>
                                <th scope="col">구분</th>
                                <th scope="col">조사년도</th>
                                <th scope="col">GPCI</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${srvyList}" var="srvy">
								<tr>
									<td align="center" class="bg">조사자료</td>
									<td align="center" class="bg">${srvy.SRVY_YEAR}</td>
									<td align="center" class="bg">${srvy.GPCI}</td>
								</tr>
							</c:forEach>
                        </tbody>
					</table>
					<h3 style="line-height: 30px; font-size: 15px; margin-top:10px; margin-bottom: 5px;">포장상태 예측정보</h3>
                    <table class="tblist" border="1" id="diagram">
                        <colgroup>
                            <col style="width:30%;" />
                            <col style="width:35%;" />
                            <%-- <col style="width:15%;" />
                            <col style="width:15%;" />
                            <col style="width:15%;" /> --%>
                            <col style="width:35%;" />
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
            <!-- 포장상태 예측정보 END -->
            
            <!-- 포장공사 이력 START -->
            <div id="mummCntrwkList" style="width:33%; float: left; height: 210px; ">
                <form id="frm" name="frm" method="post" action="">
	                <h3 style="line-height: 30px; font-size: 15px;">포장공사 이력</h3>
	                <div id="div_grid">
	                    <table id="gridArea"></table>
	                    <div id="gridPager" style="width: 100%;"></div>
	                </div>
                </form>
            </div>
            <!-- 포장공사 이력 END -->
        </div>
    </div>
</div>
<input type="hidden" name="RPAIR_TA" id="RPAIR_TA" value="${result.RPAIR_TA }" />
<form id="frmParam" name="frmParam" style="height:0px;">
	<input type="hidden" name="ROUTE_CODE" id="ROUTE_CODE" value="${result.ROUTE_CODE}" />
	<input type="hidden" name="DIRECT_CODE" id="DIRECT_CODE" value="${result.DIRECT_CODE}" />
	<input type="hidden" name="TRACK" id="TRACK" value="${result.TRACK}" />
	<input type="hidden" name="STRTPT" id="STRTPT" value="${result.STRTPT}" />
	<input type="hidden" name="ENDPT" id="ENDPT" value="${result.ENDPT}" />
	<input type="hidden" name="CALC_YEAR" id="CALC_YEAR" value="${result.CALC_YEAR}" />
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

<script type="text/javaScript" language="javascript" defer="defer">
// 페이지 로딩 초기 설정 

var objId = "${smDtaLastSttusVO.OBJECT_ID}";
var cellId = "${smDtaLastSttusVO.CELL_ID}";
var param = "";
param += "?ROAD_GRAD="      + "${smDtaLastSttusVO.ROAD_GRAD}"
       + "&ROAD_NO="        + "${smDtaLastSttusVO.ROAD_NO}"
       + "&ROAD_NAME="      + "${smDtaLastSttusVO.ROAD_NAME}"
       + "&DEPT_CODE="      + "${smDtaLastSttusVO.DEPT_CODE}"
       + "&SECT_SE="        + "${smDtaLastSttusVO.SECT_SE}"
       + "&DIRECT_CODE="    + "${smDtaLastSttusVO.DIRECT_CODE}"
       + "&TRACK="          + "${smDtaLastSttusVO.TRACK}";

$( document ).ready(function() {
    
    $("#back").click(function() {
        
        var url = 'smdtalaststtus/selectSrvyDtaLastSttusList.do' + param;
        COMMON_UTIL.cmMoveUrl(url);
        
    });
        
    // 기본정보
    fnSelectData();
    
    // 포장공사이력
    fnCntrwkDtl();
    
    fnSelectLoc(cellId);
    
    $(window).on('resize', function(){
    	fnSelectPredctCen();
	});
    
});

// 기본정보
function fnSelectData() {

    $.ajax({
        url: '<c:url value="/"/>' + 'api/smdtalaststtus/selectSrvyDtaLastSttusList.do'
        , type: 'post'
        , dataType: 'json'
        , contentType : 'application/json'
        , data : JSON.stringify({OBJECT_ID : objId})
        , success: function(jdata) {
            
            var data = jdata.rows[0];
            var table = $("#baseInfo").find("td");
            
            table.eq(0).html(data.ROAD_NO_VAL);         // 노선번호
            table.eq(1).html(data.ROAD_NAME);           // 노선명
            table.eq(2).html(data.DEPT_CODE);           // 관리기관
            table.eq(3).html(data.TRACK);               // 차로
            table.eq(4).html(data.DIRECT_CODE);         // 행선
            
            var strtpt = (parseInt(data.STRTPT) / 1000).toFixed(2);
            var endpt = (parseInt(data.ENDPT) / 1000).toFixed(2);
            
            table.eq(5).html(strtpt + "~" + endpt + "km");  // 시종점
            table.eq(6).html(data.ROAD_GRAD);               // 도로등급
            table.eq(7).html(data.SECT_SE);               // 섹션구분
            
            $("#calcYear").html(data.CALC_YEAR);            // 산정년도
            $("#prairTa").html(data.RPAIR_TA + "년");       // 보수도래시기
            
            // 그래프 검색 값
            $("#ROUTE_CODE").val(data.ROUTE_CODE);
            if ( data.DIRECT_CODE == '하행' ) {
                $("#DIRECT_CODE").val('E');
            } else if ( data.DIRECT_CODE == '상행' ) {
                $("#DIRECT_CODE").val('S');
            } else {
                $("#DIRECT_CODE").val('SE');
            }
            $("#TRACK").val(data.TRACK);
            $("#STRTPT").val(data.STRTPT);
            $("#ENDPT").val(data.ENDPT);
            $("#CALC_YEAR").val(data.CALC_YEAR);
            
            // 주 파손
            var crVal = "";
            
            if ( data.CNTL_DFECT != "DFCT0009" ) {
                
                var codeNm = data.CODE_NM;
                
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
                
                var minVal = Math.min( data.AC_IDX, data.BC_IDX, data.LC_IDX, data.PTCHG_IDX, data.POTHOLE_IDX, data.RD_IDX, data.RCI );
                var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
                var valArr = [ data.AC_IDX, data.BC_IDX, data.LC_IDX, data.PTCHG_IDX, data.POTHOLE_IDX, data.RD_IDX, data.RCI ];
                
                if ( minVal == 100 ) {
                    
                    // max 값이 0인 경우는 파손없음
                    crVal += "파손없음";
                    
                } else {
                
                    crVal += "복합파손 <br /> (";
                    
                    for ( var i = 0; i < valArr.length; i++ ) {
                        
                        // min값과 같은 경우 텍스트 추가
                        if ( valArr[i] == minVal ) {
                            
                            if ( i != 0 ) {
                                
                                crVal += ", ";
                                
                            }
                            
                            crVal += nameArr[i];
                            
                        }
                        
                    }
                    
                    crVal += ")";
                    
                    $("#crVal").parent().css({ "line-height" : "15px" });
                    $("#crVal").parent().find("span").eq(0).css({ "position" : "relative", "top" : "5px" });
                    $("#crVal").parent().find("span").eq(1).css({ "line-height" : "20px", "font-size" : "13px", "position" : "fixed"}); //, "margin-top" : "5px" 
                    
                }
            }
            
            // 파손원인
            var dmgCuz = "";
            
	        var clmt = data.DMG_CUZ_CLMT;
	        var vmtc = data.DMG_CUZ_VMTC;
	        var etc = data.DMG_CUZ_ETC;
	        
	        if ( clmt == vmtc && vmtc == etc ) {
	            
	            if ( etc == 0 ) {
	                
	                dmgCuz = "파손없음";
	                
	            } else if ( etc != 0 ) {
	                
	                dmgCuz = "교통량/하부불량,<br />기후, 기타";
	                
	                $("#dmgCuz").parent().css({ "line-height" : "15px" });
                    $("#dmgCuz").parent().find("span").eq(0).css({ "position" : "relative", "top" : "5px" });
                    $("#dmgCuz").parent().find("span").eq(1).css({ "line-height" : "20px", "font-size" : "11px", "position" : "fixed"}); //, "margin-top" : "5px" 
	                //$("#dmgCuz div").css({ "line-height" : "20px", "font-size" : "18px", "padding-top" : "15px" });
	                
	            }
	            
	        } else if ( clmt > vmtc && clmt > etc ) {
	            
	            dmgCuz = "기후";
	            
	        } else if ( vmtc > clmt && vmtc > etc ) {
	            
	            dmgCuz = "교통량<br/>/하부불량";
	            
	            $("#dmgCuz").parent().css({ "line-height" : "15px" });
                $("#dmgCuz").parent().find("span").eq(0).css({ "position" : "relative", "top" : "5px" });
                $("#dmgCuz").parent().find("span").eq(1).css({ "line-height" : "20px", "font-size" : "13px", "position" : "fixed"}); //, "margin-top" : "5px" 
	            //$("#dmgCuz div").css({ "font-size" : "18px" });
	            
	        } else if ( etc > clmt && etc > vmtc ) {
	            
	            dmgCuz = "기타";
	            
	        } else if ( clmt == vmtc && clmt > etc ) {
	            
	            dmgCuz = "교통량/하부불량,<br />기후";
	            
	            $("#dmgCuz").parent().css({ "line-height" : "15px" });
                $("#dmgCuz").parent().find("span").eq(0).css({ "position" : "relative", "top" : "5px" });
                $("#dmgCuz").parent().find("span").eq(1).css({ "line-height" : "20px", "font-size" : "11px", "position" : "fixed"}); //, "margin-top" : "5px" 
	            //$("#dmgCuz div").css({ "line-height" : "20px", "font-size" : "18px", "padding-top" : "15px" });
	        
	        } else if ( clmt == etc && clmt > vmtc ) {
	                
	            dmgCuz = "기후, 기타";
	                
	        } else if ( vmtc == etc && vmtc > clmt ) {
	                
	            dmgCuz = "교통량/하부불량,<br />기타";
	            
	            $("#dmgCuz").parent().css({ "line-height" : "15px" });
                $("#dmgCuz").parent().find("span").eq(0).css({ "position" : "relative", "top" : "5px" });
                $("#dmgCuz").parent().find("span").eq(1).css({ "line-height" : "20px", "font-size" : "11px", "position" : "fixed"}); //, "margin-top" : "5px" 
	            //$("#dmgCuz div").css({ "line-height" : "20px", "font-size" : "18px", "padding-top" : "15px" });
	                            
	        } else {
	            
	            dmgCuz = "";
	            
	        }
	        
	        $("#crVal").html(crVal);
	        $("#dmgCuz").html(dmgCuz);
	        

	        // 예측정보 ( 섹션 )
	        fnSelectPredctCen();
            
        }
        ,error: function(a,b,msg){
            
            alert(msg);
            return;
            
        }
    });
}

//포장상태 예측정도
function fnSelectPredctCen() {
    
    $.ajax({
         url: '<c:url value="/"/>'+'api/smcalcpredct/selectSmCalcPredctListGraph.do'
        ,type: 'post'
        ,contentType: 'application/json'
        ,data: JSON.stringify($("#frmParam").cmSerializeObject())
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
	
	// 2018. 09. 17.
	var srvyYear = "${srvyList[ fn:length(srvyList) - 1 ].SRVY_YEAR}";
	var srvyGpci = "${srvyList[ fn:length(srvyList) - 1 ].GPCI}";
	gPredctYear.push(srvyYear);
	gpciData.push(parseFloat(srvyGpci).toFixed(2));
	
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
				           			  	}
				           			  
				           			  },
				               tooltip : {	trigger: 'axis'				},
				               legend: {
				                   data:['GPCI'], //'거북등균열','선형균열','종단평탄성',
				                   x: 'right',
				                   y: 'center'
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
			var RPAIR_TA = parseInt($("#RPAIR_TA").val());
			var CALC_YEAR = parseInt($("#CALC_YEAR").val());
			var RPAIR_TA_YEAR = RPAIR_TA + CALC_YEAR
						
			if(mainData[i].PREDCT_YEAR == RPAIR_TA_YEAR){
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

//포장공사이력 조회
/* function fnCntrwkDtl() {
    
    var cellIds = "";
    
    $.ajax({
        url: '/gpms/' + 'api/mummsctnsrvydta/mummSctnSrvyDtaCellInfo.do'
        , type: 'post'
        , dataType: 'json'
        , contentType : 'application/json'
        , data : JSON.stringify({OBJECT_ID : objId})
        , success: function(jdata) {
            
            if ( jdata != undefined && jdata != null && jdata != "" ) {

                for ( var i = 0; i < jdata.length; i++ ) {
                    
                    if (i != 0) {
                        
                        cellIds += ",";
                    }
                    
                    cellIds += jdata[i].CELL_ID;
                    
                }
                
                fnShowCntrwkList(cellIds);
                
            }
            
        }
        , error : function(a, b, msg) {
            alert(msg);
            return;
        }
    });
    
} */

/* function fnShowCntrwkList(cellIds) { */
function fnCntrwkDtl() {
    
    var postDatas = { "CELL_ID": cellId };
    
    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        
        url: '/gpms/'+'api/cntrwk/selectCntrwkListBySctn.do'
        ,width: true
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,postData: JSON.stringify(postDatas)
        ,ignoreCase: true
        ,colNames:["공사ID","세부공사ID","셀번호","공사명","세부공사명","착공일","준공일","위치보기"]
        ,colModel:[
            {name:'CNTRWK_ID',index:'CNTRWK_ID', hidden: true}
            ,{name:'DETAIL_CNTRWK_ID',index:'DETAIL_CNTRWK_ID', hidden: true}
            ,{name:'CELL_ID',index:'CELL_ID', align:'center', width:90}
            ,{name:'FULL_CNTRWK_NM',index:'FULL_CNTRWK_NM', align:'left', width:100}
            ,{name:'DETAIL_CNTRWK_NM',index:'DETAIL_CNTRWK_NM', align:'left', width:100, hidden: true}
            ,{name:'STRWRK_DE',index:'STRWRK_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'COMPET_DE',index:'COMPET_DE', align:'center', width:50 , formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions : {tgtFormat: "yyyy-MM-dd", dateYN:false}}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false , formatter: fnFormatter}
        ]
        ,async : false
        ,sortname: 'STRTPT'
        ,sortorder: "asc" 
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 ({0}-{1})"
        ,ondblClickRow: function( rowId ) {
            
            fnView(rowId);  // 대장 조회
            
        }
        ,onSelectRow: function(rowId) {     // 클릭 처리
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
                if(this.p.postData.pageUnit != this.p.postData.rows){
                    this.p.postData.pageUnit = this.p.postData.rows;
                }
                ajaxParam.data = JSON.stringify(this.p.postData);
            }
        }
        , loadComplete: function() {
            
            //페이지 box가 중간에 오도록 css로 해결
            $("#gridPager_left").css('width', '');
            $("#gridPager_center").css('width', '100%');
        }
        ,multiselect: false
        ,multiboxonly: false
    }).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
    
    // 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 182);
    
    fn_search(cellId);
    
    $(window).resize(function () {
        fnSetGridWith();
    });
    
}

//검색 처리
function fn_search(cellId) {
    
    var postDatas = { "CELL_ID": cellId };
    
    $("#gridArea").jqGrid("setGridParam", {
        
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postDatas
        ,mtype: "POST"
        ,loadComplete: function(data) {
            
            fnSetGridWith();
            
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
                        
        }
        
    }).trigger("reloadGrid");
}

// jqgrid 하단 및 넓이 설정 
function fnSetGridWith() {
    
    var grid = $("#gridArea");
    var gridWidth = $("#baseInfo").width();
    
    grid.setGridWidth(gridWidth);
    
    $(".ui-jqgrid-htable").css("width", gridWidth-18);
    $(".ui-jqgrid-btable").css("width", gridWidth-18);
    $(".ui-jqgrid-ftable").css("width", gridWidth-18);
}

// 상세 조회
function fnView ( rowId ) {
    
    if( $.type( rowId ) === "undefined" || rowId== "" ) {
        
        rowId = $("#gridArea").getGridParam( "selrow" );
        
    }
        
    if ( rowId != null ) {
        
        var rowData = $("#gridArea").getRowData(rowId);
        var deCntrwkId = rowData["DETAIL_CNTRWK_ID"];
        var cellId = rowData["CELL_ID"];
        
        COMMON_UTIL.cmWindowOpen('상세정보 조회', "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>?DETAIL_CNTRWK_ID="+deCntrwkId+"&CELL_ID="+cellId, 1000, 1200, false, $("#wnd_id").val(), 'center');
        
    } else{
        
        alert('<spring:message code="warn.checkplz.msg" />');
        
    }
        
}

// Formatter Fuction
function fnConvertKm(cellValue, options, rowObject) {
    
    var nm = options.colModel.name;
    
    if ( nm == "STRTPT" ) {
        
        var val = rowObject.STRTPT;
        val *= 1;
        val = (val / 1000).toFixed(2);
        
        return val;
        
    } else if ( nm == "ENDPT" ) {
        
        var val = rowObject.ENDPT;
        val *= 1;
        val = (val / 1000).toFixed(2);
        
        return val;
        
    } else {
        
        return "";
        
    }
}

function fnFormatter(cellValue, options, rowObject) {
    
    var html = "";
    var nm = options.colModel.name;
    
    if ( nm == "btn_loc" ) {

        html = "<a href='#' onclick=\"fnSelectLoc('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
    
    }
    
    return html;
    
}

function fnSelectLoc(cell_id) {

    // 하단 목록 창 내리기
    //parent.bottomClose();
    
    // 선택한 셀을 보여줌
    var tables = ["CELL_SECT"];
    var fields = ["CELL_ID"];
    var values = [cell_id];
    var attribute = {
            attributes : {
                fillColor : '#ffffff',
                strokeColor : '#ffffff'
            }
    };
    
    MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables, fields, values, null, null, attribute);

}

</script>
</body>
</html>