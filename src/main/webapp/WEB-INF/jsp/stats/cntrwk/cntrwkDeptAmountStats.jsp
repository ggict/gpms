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

//페이지 로딩 초기 설정
$( document ).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성
	COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE','SCH_COMPET_DE', 10);
	
	fnSearch();
	
	$("#Org").children().addClass("selected");
	$("#oms").addClass("selected2");
	$("#Ns_list").css("display","none");
	$("#W_list").css("display","none");
}); 

require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});

//검색 처리
function fnSearch() {
  	 $.ajax({
        url: '<c:url value="/"/>'+'api/cntrwkdtl/selectCntrwkDeptStatsResult.do',
        data: JSON.stringify( $("#frm").cmSerializeObject()),
        contentType: 'application/json',
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
        success: function (data) {
        	var dataList = data.data;
			if(dataList.length !=0){
				drawChart(dataList);
				drawTable(dataList);
			}else{
				alert('검색된 값이 존재하지 않습니다.');
			}
        },
        error: function () {
        	alert("오류가 발생하였습니다. 재검색 하시기 바랍니다.");
            return;
        }
    });
}

function drawChart(dataList){
	var gDeptNm 	= [];		
	var amountData		= [];
	for(var i=0; i<dataList.length; i++){
		gDeptNm.push(dataList[i].DEPT_NM);
		amountData.push(dataList[i].CNTRWK_AMOUNT);
	}
	require([	'echarts','echarts/chart/bar'	],
	        function (ec) {
				 var myChart = ec.init(document.getElementById('main'));
				 myChart.setOption({
		            	title 	: {	text: '관리기관별 공사 예산'	},
		                tooltip : {	trigger: 'axis'				},
		                toolbox : {	show: true,
			    			feature: {
				    			dataView : {show: true, readOnly: false}, 	// 상세조회
				    			saveAsExcel : {show: true},					// 엑셀저장
				    			saveAsImage: {show: true}					// 이미지저장
				    		}	
			    		},
					    grid :{
					    	width : 750,
					    	x : 50,
					    	y2 : 80
					    },
		                xAxis : [{	
		                			type : 'category',
				            		axisLabel : {
				                		show:true,
				                		interval: 0,
				                		rotate: -90
				            		},
		                			data : gDeptNm
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
		tHtml	+= '<td align="center" class="bg">'				+	mainData[i].DEPT_NM				        + '</td>';
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
		COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/cntrwkdtl/cntrwkDeptCntStatsExcel.do'/>", "");
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
<form id="frm" name="frm" method="post" action="">
<div style="margin: 0px 20px 0 20px;">
	<div class="Pop_Section" style="padding-bottom:15px;">
		</div>
	<div class="cont_TitBx">
		<p>포장공사통계조회</p>
	</div>
	<div class="cont_Section">
    <!--검색영역-->
    <div class="cont_SearchBx">
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
    </div>
    <!--// 검색영역-->
    <!--왼쪽메뉴영역-->
    <%@ include file="/include/stats/common_cntrwk.jsp" %>
    <!--// 왼쪽메뉴영역-->
	<!-- 그래프 -->
	<div id="main" class="cont_ConBx2"></div>
	<!-- 표 -->
	<div class="cont_ListBx" style="display: none;">
		<table border="1" id="diagram">
			<colgroup>
				<col style="width:20%;"	/>
				<col style="width:16%;"	/>
				<col style="width:24%;"	/>
				<col style="width:24%;"	/>
				<col style="width:16%;"	/>
			</colgroup>
			<thead>
				<tr>
					<th>관리기관명</th>
					<th>공사건수(건)</th>
					<th>연장(m)</th>
					<th>공사비(원)</th>
					<th>공사빈도(%)</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
</body>
</html>