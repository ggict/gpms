<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장관리시스템</title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
</head>
<body >
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="UNTPCS" name="UNTPCS" value=""/>
<input type="hidden" id="UNTPC_DEPT" name="UNTPC_DEPT" value=""/>
<!-- 필수 파라메터(END) -->
<div id="btab01">
	<div id="sch_cnt01" class="tabcont">
	    <h3 class="tc">예산 수준별 시나리오 분석</h3>
	    <p class="location">
	        <span id="repairUntpc" class="spanUntpc">보수대상 선정</span>
	        <span id="statUntpc" class="spanUntpc" style="display:none;">통계</span>
	        <strong>예산 수준별 시나리오 분석</strong>
	    </p>
	    <div style="min-height:600px;overflow-y: auto;">
	        <div class="bgsch2 af" style="height:200px;">
                <h4 class="stit">예산 수준별 시나리오 작성</h4>
                <div class="posiR m10">
                    <div class="btbx posiA" style="left:0px;right:440px">
                        <h4 style="padding:10px" class="tc">경기도 전체 포장도로 정보</h4>
                        <div style="height:98px;background:#fff">
                            <table class="tbin" style="height:98px;">
                                <caption>조사자료</caption>
                                <colgroup>
                                    <col width="25%" />
                                    <col width="25%" />
                                    <col width="25%" />
                                    <col width="25%" />
                                </colgroup>
                                <tbody>
                                    <tr>
                                    	<td class="sp">
                                            <b class="txtit">예산 집행 기관</b>
                                            <select name="DEPT_CODE" id="DEPT_CODE" class="input">
                                            	<option value="">===== 전체 =====</option>
                                            	<c:forEach items="${deptCdList }" var="dept">
                                            		<option value="${dept.DEPT_CODE }">${dept.LOWEST_DEPT_NM }</option>
                                            	</c:forEach>
                                            </select>
                                        </td>
                                        <td class="sp">
                                            <b class="txtit">경기도 전체 포장도로 연장</b>
                                            <ul class="tblst">
                                                <li><img src="<c:url value='/images/ic_r1.png'/>" alt="아이콘" class="mr25" /></li>
                                                <li class="mt10 fr"><strong class="fcred f15 mr5"><fmt:formatNumber value="${cellSectVO.ROAD_L }" pattern="#,###" /></strong>m </li>
                                            </ul>
                                        </td>
                                        <td class="sp">
                                            <b class="txtit">경기도 전체 포장도로 면적</b>
                                            <ul class="tblst">
                                                <li><img src="<c:url value='/images/ic_r2.png'/>" alt="아이콘" class="mr25" /></li>
                                                <li class="mt10 fr"><strong class="fcred f15 mr5"><fmt:formatNumber value="${cellSectVO.ROAD_A }" pattern="#,###" /></strong> ㎡</li>
                                            </ul>
                                        </td>
                                        <td class="sp">
                                            <b class="txtit">평균 공사단가</b>
                                            <ul class="tblst">
                                                <li><img src="<c:url value='/images/ic_r3.png'/>" alt="아이콘" class="mr25" /></li>
                                                <li class="mt10 fr"><strong class="fcred f15 mr5"><fmt:formatNumber value="${avgUntpc }" pattern="#,###" /></strong> 원/㎡</li>
                                            </ul>
                                        </td>
                                        
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="fr mr10"> 
                        <div class="btbx fl" style="width:260px">
                            <h4 style="padding:10px" class="tc">투입예산(원)</h4>
                            <div class="scroll" style="height:98px;background:#fff">
                                <table class="tbinlst2">
                                    <caption>조사자료</caption>
                                    <colgroup>
                                        <col width="40%" />
                                        <col width="30%" />
                                        <col width="30%" />
                                    </colgroup>
                                    <tbody id="unptcTbody">
                                        <tr>
                                            <td><input type="text" name="untpcValue" style="width:83%"  class="MX_80 CS_50 DT_INT input" /> <a href="#" onclick="fn_addUntpc()"><img src="<c:url value='/images/ic_add.png'/>" alt="추가" title="추가" /></a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <a href="#" class="schbtn dpb fl ml40" onclick="fn_analyze();" style="height:128px;line-height:128px;width:70px">분석</a>
                    </div>
                </div>
            </div>
            <h4 class="stit mt10">예산 수준별 시나리오 분석 결과</h4>
            <div id="untpcGPCIChart" class="tc pt70" style="width:100%;">
            	<img src="<c:url value='/images/nodata.png'/>" />
            </div>
        </div>
    </div>
</div>
</form>  

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {
	COMMON_UTIL.cmFormObjectInit("frm");
	
	$("#untpcGPCIChart").height($(parent.window).height() - 450);
	
	$(".spanUntpc").hide();
	$("#" + "${type}").show();
}); 

$(window).on('resize', function(){
	$("#untpcGPCIChart").height($(parent.window).height() - 450);
	fn_getAnalyzeResult();
});

require.config({
	   paths: {
	        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
	    }
	});

/**
 * 투입 예산 입력 칸 추가
 * author : skc
 * 2017-11-27
 */
function fn_addUntpc(){
	var tHtml = '<tr>'
		      + '	<td><input type="text" name="untpcValue" style="width:83%"  class="MX_80 CS_50 DT_INT input"  /> <a href="#" onclick="fn_addUntpc()"><img src="' + contextPath + 'images/ic_add.png" alt="추가" /></a></td>'
		      + '</tr>';
	$("#unptcTbody").append(tHtml);
	
	COMMON_UTIL.cmFormObjectInit("frm");
	
	var input = $("#unptcTbody").find("input");
	input[input.length-1].focus();
}


/**
 * 투입 예산 수준별 시나리오 분석
 * author : skc
 * 2017-11-27
 */
function fn_analyze(){
	 var inputList = $("#unptcTbody").find("input");
	 var untpcList = [];
	 
	 for(var i=0; i<inputList.length; i++){
		 var value = $(inputList[i]).val();
		 if(value == ""){
			 alert("투입예산을 입력해주시기 바랍니다.");
			 return false;
		 }
		 
		 untpcList.push(value.replace(/,/g, ""));
	 }
	 
	 $("#untpcGPCIChart").empty();
	 $("#untpcGPCIChart").removeClass("pt70");
	 
	 $("#UNTPCS").val(untpcList.join(","));
	 $("#UNTPC_DEPT").val($("#DEPT_CODE").val());
	 fn_getAnalyzeResult();
 }
 
/**
 * 투입 예산 수준별 시나리오 분석 결과 조회
 * author : skc
 * 2017-11-27
 */
function fn_getAnalyzeResult(){
	var untpcs = $("#UNTPCS").val();
	var deptCd = $("#UNTPC_DEPT").val();
	
	if(untpcs == ""){return;}
	
	$.ajax({
        url: '<c:url value="/"/>'+'api/rpairmthduntpc/selectStatUntpc.do'
       ,type: 'post'
       ,contentType: 'application/json'
       ,data: JSON.stringify({"UNTPCS" : untpcs, "DEPT_CODE" : deptCd})
       ,dataType: 'json'
       ,success: function (jdata) {
           
           if(jdata.length !=0){
               
               drawUntpcGPCIChart(jdata);
               
           }else{
               alert("분석 결과가 존재하지 않습니다.");
           }
       },
       error: function () {
    	   alert("분석에 실패하였습니다.");
       }
   });
}

/**
 * 예산별 GPCI 차트 그리기
 * author : skc
 * 2017-11-24
 * dataList : 통계정보 리스트
 */
function drawUntpcGPCIChart(dataList) {
    
	var objList = [];
	var untpcList = $("#UNTPCS").val().split(",");
	var titleList = [];
	var predctYearList = [];
    
	//data parsing
	
   	for (var j = 0; j < untpcList.length; j++){
   		var untpc = untpcList[j];
    	var gpciList = [];
    	predctYearList = [];
   		
    	for ( var i = 0; i < dataList.length; i++ ) {
    		var data = dataList[i];
    	
    		if(data.CNTRWK_UNTPC == untpc){
    			gpciList.push(parseFloat(data.GPCI));  
    			predctYearList.push(data.PREDCT_YEAR);
    		}
    	}
    	
    	var obj = {
				name: "예산 " + parseInt((parseInt(untpc)/100000000)) + "억 원/연",
                type: 'line',
                symbol:'emptyCircle',
                data: gpciList
		};
		
		objList.push(obj);
    }
	
	//타이틀 데이터 생성
	for(var i=0; i<untpcList.length; i++){
		titleList.push("예산 " + parseInt((parseInt(untpcList[i])/100000000)) + "억 원/연");
	}
    
    require([   'echarts','echarts/chart/bar', "echarts/chart/line"   ],
            function (ec) {
                 var myChart = ec.init(document.getElementById('untpcGPCIChart'));
                 myChart.setOption({
                	 	tooltip : { trigger: 'axis'             },
                        legend: {
			            	   x: 'center',
			                   y: 'bottom',
			                   orient : 'horizontal',
			                   data: titleList 
			               },
                        toolbox : { show: false
                        },
                        grid :{
                        	y2 : 50,
					        containLabel: true
                        },
                        xAxis : [{  
                        			name : '공용연수',
                                    type : 'category',
                                    axisLabel : {
                                        show:true,
                                        interval: 0,
                                        rotate: 0
                                    },
                                    data : predctYearList
                                }],
                        yAxis : [{  name : 'GPCI',     type : 'value'      }],
                        series : objList
                    });
                 
            });
}

 
</script>
</body>
</html>