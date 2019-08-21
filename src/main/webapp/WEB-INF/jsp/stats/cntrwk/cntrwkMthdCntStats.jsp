<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장공사통계 </title>
<%@ include file="/include/common_head.jsp" %>
<script src="<c:url value='/extLib/echarts/echarts.js'/>"></script>
<script type="text/javascript" defer="defer">

//에러 메시지 변수
var errNo=0;
//경고 메시지 변수
var ntcNo=0;

//페이지 로딩 초기 설정
$( document ).ready(function() {
	$("#divStatChart").height($(parent.window).height() - 170);
	
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성
	//COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE','SCH_COMPET_DE', 10);
	
	//창 조절시 차트 width 
	var rw = $(window).width()/3;
	
	fnMthdCntSearch('','','',rw);//공법별건수조회
	fnMthdLenSearch('','','',rw);//공법별연장조회
	fnMthdAmountSearch('','','',rw);//공법별예산조회
}); 

//창 조절시 차트 resize
$(window).on('resize', function(){
	$("#divStatChart").height($(parent.window).height() - 170);
	
  	var rw = $(window).width()/3;
  	var rmthd = $("#SCH_RPAIR_MTHD").val();
  	var strDt = $("#SCH_STRWRK_DE").val();
  	var endDt = $("#SCH_COMPET_DE").val();
  	
  	fnMthdSearch(rmthd,strDt,endDt,rw);
});

//조건에 맞는 검색조회
function fnMthdSearch(rmthd,strDt,endDt,rw){
	//검색 조건 값 set
	$("#SCH_RPAIR_MTHD").val(rmthd);
	$("#SCH_STRWRK_DE").val(strDt);
	$("#SCH_COMPET_DE").val(endDt);
	
	fnMthdCntSearch(rmthd,strDt,endDt,rw);
	fnMthdLenSearch(rmthd,strDt,endDt,rw);
	fnMthdAmountSearch(rmthd,strDt,endDt,rw);
}

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnMthdCntSearch(rmthd,strDt,endDt,rw) {
	var data = {"SCH_RPAIR_MTHD" : rmthd, "SCH_STRWRK_DE" : strDt, "SCH_COMPET_DE" : endDt};

	$.ajax({
        //url: action,
        url: '<c:url value="/"/>'+'api/cntrwkdtl/selectCntrwkMthdStatsResult.do',
        //data: JSON.stringify( $("#frm").cmSeriatrgetlizeObject()),
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawMthdCntChart(dataList,rw);
				drawTable(dataList);
			}else{
				ntcNo += 1;
			}
        },
        error: function () {
        	errNo += 1;
        }
    });
}

function drawMthdCntChart(dataList,rw){
	var gMthdNm 	= [];		
	var cntData		= [];
	var degree		= -90;
	if(dataList.length < 7){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		gMthdNm.push(dataList[i].RPAIR_MTHD_NM);
		cntData.push(dataList[i].CNT);
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mthdCntChart'));
				 myChart.setOption({
		            	title 	: {	text: '공법별 공사 건수'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	width : rw+'px',
					    	x : 50,
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : gMthdNm
		                		}],
		                yAxis : [{	name : '건',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
		                                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
		                                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		                                       '#FE2752','#7EEA59','#F2B731','#D8C35C','#41C0AE',
		                                       '#FE8422','#6EEA54','#F1B541','#D6C32C','#73C1AE',
		                                       '#FD8610','#CDEF60','#FD6ACE','#E6D410','#20C5AD',
		                                       '#DD810E','#CCDA80','#BE88CA','#D21810','#31D2BA',
		                                       '#EE91AE','#EFCBCD','#CDE1BB','#CDE991','#98613B',
		                                       '#FADE06','#BB78FF','#ECCDC0','#FBA001','#E6734C',
		                                       '#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC'
		                                    ];
		                                    return colorList[params.dataIndex]
		                                }
		                            }
		                        },
		                        data: cntData
		                    }
		                ]
		            });

			});
}


//검색 처리
function fnMthdLenSearch(rmthd,strDt,endDt,rw) {
	var data = {"SCH_RPAIR_MTHD" : rmthd, "SCH_STRWRK_DE" : strDt, "SCH_COMPET_DE" : endDt};
	
	 $.ajax({
      url: '<c:url value="/"/>'+'api/cntrwkdtl/selectCntrwkMthdStatsResult.do',
      //data: JSON.stringify( $("#frm").cmSerializeObject()),
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false,
      type: 'POST',
      processData: false,
      success: function (data) {
      	var dataList = data.data;
			if(dataList.length !=0){
				drawMthdLenChart(dataList,rw);
				drawTable(dataList);
			}else{
				ntcNo += 1;
			}
      },
      error: function () {
    	errNo += 1;
      }
  });
}

function drawMthdLenChart(dataList,rw){
	var gMthdNm 	= [];		
	var lenData		= [];
	var degree		= -90;
	if(dataList.length < 7){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		gMthdNm.push(dataList[i].RPAIR_MTHD_NM);
		lenData.push(dataList[i].TRACK_LEN);
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mthdLenChart'));
				 myChart.setOption({
		            	title 	: {	text: '공법별 공사 연장'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	width : rw+'px',
					    	x : 50,
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : gMthdNm
		                		}],
		                yAxis : [{	name : '연장(m)',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
		                                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
		                                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		                                       '#FE2752','#7EEA59','#F2B731','#D8C35C','#41C0AE',
		                                       '#FE8422','#6EEA54','#F1B541','#D6C32C','#73C1AE',
		                                       '#FD8610','#CDEF60','#FD6ACE','#E6D410','#20C5AD',
		                                       '#DD810E','#CCDA80','#BE88CA','#D21810','#31D2BA',
		                                       '#EE91AE','#EFCBCD','#CDE1BB','#CDE991','#98613B',
		                                       '#FADE06','#BB78FF','#ECCDC0','#FBA001','#E6734C',
		                                       '#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC'
		                                    ];
		                                    return colorList[params.dataIndex]
		                                }
		                            }
		                        },
		                        data: lenData
		                    }
		                ]
		            });

			});
}


//검색 처리
function fnMthdAmountSearch(rmthd,strDt,endDt,rw) {
	var data = {"SCH_RPAIR_MTHD" : rmthd, "SCH_STRWRK_DE" : strDt, "SCH_COMPET_DE" : endDt};
	
	 $.ajax({
      url: '<c:url value="/"/>'+'api/cntrwkdtl/selectCntrwkMthdStatsResult.do',
      //data: JSON.stringify( $("#frm").cmSerializeObject()),
      data:JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false,
      type: 'POST',
      processData: false,
      success: function (data) {
      	var dataList = data.data;
			if(dataList.length !=0){
				drawMthdAmountChart(dataList,rw);
				drawTable(dataList);
			}else{
				ntcNo += 1;
				fn_msgNtc();
			}
      },
      error: function () {
   		errNo += 1;
   		fn_msgErr();
      }
  });
}

function drawMthdAmountChart(dataList,rw){
	var gMthdNm 	= [];		
	var amountData		= [];
	var degree		= -90;
	if(dataList.length < 7){
		degree = 0;
	}
	for(var i=0; i<dataList.length; i++){
		gMthdNm.push(dataList[i].RPAIR_MTHD_NM);
		amountData.push(dataList[i].CNTRWK_AMOUNT);
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('mthdAmountChart'));
				 myChart.setOption({
		            	title 	: {	text: '공법별 공사 예산'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	width : rw+'px',
					    	x : 50,
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: degree
				            		},
		                			data : gMthdNm
		                		}],
		                yAxis : [{	name : '공사비(천원)',		type : 'value'		}],
		                series : [
		                    {
		                        name: '',
		                        type: 'bar',
		                        itemStyle: {
		                            normal: {
		                                color: function(params) {
		                                    var colorList = [
		                                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
		                                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		                                       '#FE2752','#7EEA59','#F2B731','#D8C35C','#41C0AE',
		                                       '#FE8422','#6EEA54','#F1B541','#D6C32C','#73C1AE',
		                                       '#FD8610','#CDEF60','#FD6ACE','#E6D410','#20C5AD',
		                                       '#DD810E','#CCDA80','#BE88CA','#D21810','#31D2BA',
		                                       '#EE91AE','#EFCBCD','#CDE1BB','#CDE991','#98613B',
		                                       '#FADE06','#BB78FF','#ECCDC0','#FBA001','#E6734C',
		                                       '#FFBBEE','#991122','#DDEECC','#AABCD5','#484ABC'
		                                    ];
		                                    return colorList[params.dataIndex]
		                                }
		                            }
		                        },
		                        data: amountData
		                    }
		                ]
		            });

			});
}

function drawTable(dataList){

	var mainData	= dataList;
	var tHtml 		= '';
	
	for(var i=0; i<mainData.length; i++){
		tHtml	+= '<tr>';
		tHtml	+= '<td align="center" class="bg">'				+	mainData[i].RPAIR_MTHD_NM				+ '</td>';
		tHtml	+= '<td style="text-align:right" class="bg2">'	+	mainData[i].CNT					        + '</td>';
		tHtml	+= '<td style="text-align:right">'				+	COMMON_UTIL.cmAddComma(mainData[i].TRACK_LEN)	    + '</td>';
		tHtml	+= '<td style="text-align:right">'				+	COMMON_UTIL.cmAddComma(mainData[i].CNTRWK_AMOUNT)	+ '</td>';
		tHtml	+= '<td style="text-align:right">'				+	mainData[i].PERC						+ '</td>';
		tHtml	+= '</tr>';
	}
	
	$('#diagram tbody').empty().append(tHtml);
}

//엑셀 다운로드
function fnExcel() {
	if( confirm("엑셀 파일로 저장하시겠습니까?") ) {
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cntrwkdtl/cntrwkMthdCntStatsExcel.do'/>", "");
	}
}

//에러 메시지
function fn_msgErr(){
	if(errNo == 1){
		alert("오류가 발생하였습니다. 재검색 하시기 바랍니다.");
		return;
	}else {
		return;
	}
}

//경고 메시지
function fn_msgNtc(){
	if(ntcNo >= 1){
		alert("해당 조건에 검색 결과가 없습니다. 검색 조건을 변경하여 조회 하시기 바랍니다.");
		return;
	}else {
		return;
	}
}


</script>
</head>
<body id="wrap">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="SCH_RPAIR_MTHD" name="SCH_RPAIR_MTHD" value=""/>
<input type="hidden" id="SCH_STRWRK_DE" name="SCH_STRWRK_DE" value=""/>
<input type="hidden" id="SCH_COMPET_DE" name="SCH_COMPET_DE" value=""/>
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
	<div style="margin: 0px 20px 0 20px;">
		<div id="sch_cnt01" class="tabcont">
			<h3>포장공법별 통계</h3>
		<p class="location">
			<span>포장공사 이력관리</span>
            <span>포장공사 통계조회</span>
            <strong>포장공법별 통계</strong>
		</p>
    <!--검색영역-->
    <%-- <div class="cont_SearchBx">
        <div class="cont_search">
			<div class="Con_Bt2">
				<img src="<%=request.getContextPath() %>/images/btn_search3.gif" alt="검색" onclick="javascript:fnSearch();" />
			</div>
            <dl>
                <dt class="CTx1">관리기관</dt>
                <dt class="W90">
                	<select name="SCH_DEPT_CODE" id="SCH_DEPT_CODE" class="select sBx120">
					<option value="">선택</option>
					<c:forEach var="selectData" items="${deptCdList}">
						<option value="${selectData.DEPT_CODE}">${selectData.LOWEST_DEPT_NM}</option>
					</c:forEach>
				</select>
			</dl>
			<dl>	
				</dt>
				<dt class="CTx1">공사기간</dt>
				<dt class="W220">
					<input type="text" name="SCH_STRWRK_DE" id="SCH_STRWRK_DE" value="<c:out value="${param.SCH_STRWRK_DE}"/>" class="DT_DATE input" />
					-
					<input type="text" name="SCH_COMPET_DE" id="SCH_COMPET_DE" value="<c:out value="${param.SCH_COMPET_DE}"/>" class="DT_DATE input" />
				</dt>
            </dl>
        </div>
    </div> --%>
    <!--// 검색영역-->
    <!--왼쪽메뉴영역-->
    <%-- <%@ include file="/include/stats/common_cntrwk.jsp" %> --%>
    <!--// 왼쪽메뉴영역-->
	<!-- 그래프 -->
	<div  id="divStatChart" style="overflow-y:auto;">
		<ul class="statsbx">
			<li>
				<div class="graylinebx p10">
					<div id="mthdCntChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
				</div>
				<h4 style="text-align: center;background:none;">공사건수 통계</h4>
			</li>
			<li>
				<div class="graylinebx p10">
					<div id="mthdLenChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>
				</div>
				<h4 style="text-align: center;background:none;">공사연장 통계</h4>
			</li>
			<li>
				<div class="graylinebx p10">
					<div id="mthdAmountChart" class="cont_ConBx2"  style="height: 300px; margin-left:20px;"></div>
				</div>
				<h4 style="text-align: center;background:none;">공사예산 통계</h4>
			</li>
		</ul>
	</div>
	<!-- 표 -->
	<div class="cont_ListBx" style="display: none;">
		<table class="tblist" border="1" id="diagram">
			<colgroup>
				<col width="20%"	/>
				<col width="16%"	/>
				<col width="24%"	/>
				<col width="24%"	/>
				<col width="16%"	/>
			</colgroup>
			<thead style="text-align: center;">
				<tr>
					<th scope="col">공법명</th>
					<th scope="col">공사건수(건)</th>
					<th scope="col">연장(m)</th>
					<th scope="col">공사비(천원)</th>
					<th scope="col">공사빈도(%)</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
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