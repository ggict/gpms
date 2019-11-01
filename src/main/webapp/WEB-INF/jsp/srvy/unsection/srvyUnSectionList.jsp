<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>미조사구간 조회</title>
<%@ include file="/include/common_head.jsp" %>
</head>
<body id="wrap">

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<div class="tabcont">
	<div class="fl bgsch">
	    <h3>검색조건</h3>
	    <div class="schbx mt10">
	        <ul class="sch">
	            <li class="wid100">
	                <label>조사연도</label>
	                <select id="SRVY_YEAR" name="SRVY_YEAR" alt="조사연도" class="input" style="width:100px;">
	                	<!-- <option value="">== 전체 ==</option> -->
		        		<c:forEach items="${srvyYearList }" var="srvyYear" varStatus="status">
		        			
		        			<option value="${srvyYear.SRVY_YEAR }" <c:if test="${status.last}"> selected</c:if>>${srvyYear.SRVY_YEAR } </option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li class="wid100">
	                <label>노선번호</label>
	                <select id="ROAD_NO" name="ROAD_NO" alt="노선번호" onchange="fn_change_roadNm();" class="input" style="width:100px;">
		                <option value="">== 전체 ==</option>
		        		<c:forEach items="${roadNoList }" var="roadNo">
		        			<option value="${roadNo.ROAD_NO }">${roadNo.ROAD_NO_VAL }</option>
		        		</c:forEach>
	                </select>
	            </li>
	            <li>
	                <label>노선명</label>
	                <input type="text" name="ROAD_NAME" id="ROAD_NAME" readonly="readonly" value="" style="width:197px;" class="MX_80 CS_50 input" />
	            </li>
	            <li class="wid100" style="margin-top: 10px;">
	                <a href="#" class="schbtn dpb" onclick="javascript:fn_search();">검색</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="fr listbx">
	    <h3>미조사구간 조회</h3>
	    <p class="location">
	        <span>조사자료 관리</span>
	        <strong>미조사구간 조회</strong>
	    </p>
	    <div class="mt10 ml10 mr10">
            <div id="div_grid" style="width:100%; height:240px;">
				<table id="gridArea"></table>
				<div id="gridPager"></div>
			</div>
			<div class="mt10 tc">
	            <div class="fr">
	            	<a href="#" onclick="fnSave();" class="schbtn">차트</a>
	           	</div>
	        </div>
        </div>        
	</div>

</form>

<!-- 막대그래프 -->
<div id="gpmsLenBarChart" class="cont_ConBx2" style="height: 300px; margin-left:20px;"></div>

<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">

//페이지 로딩 초기 설정
$( document ).ready(function() {

	try{
		parent.gMap.cleanMap();
	}catch(e){
		//console.log(e);
	}

    var postData = {"USE_AT":"Y"};
    postData = $("#frm").cmSerializeObject();

    // 검색 목록 그리드 구성
    $("#gridArea").jqGrid({
        url: '<c:url value="/"/>'+'api/srvyunsection/selectSrvyUnSectionList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: JSON.stringify( $("#frm").cmSerializeObject())
        ,postData: postData
        ,ignoreCase: true
        ,colNames:["ROAD_NO","SRVY_YEAR","노선 번호","노선 명","시점 명","종점 명","총연장(km)","조사구간(km)","조사비율","조사위치보기","미조사구간위치보기"]
        ,colModel:[
            {name:'ROAD_NO',index:'ROAD_NO', hidden: true}
            ,{name:'SRVY_YEAR',index:'SRVY_YEAR', hidden: true}
            ,{name:'ROAD_NO',index:'ROAD_NO', align:'center', width:50, sortable:true}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:100, sortable:true}
            ,{name:'ST_POINT',index:'ST_POINT', align:'left', width:120, sortable:true}
            ,{name:'ED_POINT',index:'ED_POINT', align:'left', width:120, sortable:true}
            ,{name:'TOTAL_ROAD_L',index:'TOTAL_ROAD_L', align:'center', width:80, sortable:true}
            ,{name:'ROAD_L',index:'ROAD_L', align:'center', width:80, sortable:true}
            ,{name:'PERSENT',index:'PERSENT', align:'center', width:80, sortable:true}
            ,{name:'btn_loc',index:'btn_loc', align:'center', width:50, sortable:false, formatter: fn_create_btn}
            ,{name:'btn_unloc',index:'btn_unloc', align:'center', width:50, sortable:false, formatter: fn_create_btn}
        ]
        ,async : false
        ,sortname: 'ROAD_NO'
        ,sortorder: "asc"
        ,rowNum: 50
        ,rowList: [20,50,100,500]
        ,viewrecords: true
        ,pager: '#gridPager'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {
        	
        }
        ,onSelectRow: function(rowId) {
        	
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
    COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 190);

    setTimeout(function() {
         fn_search();
    }, 500);
});

//검색 처리
function fn_search() {
    var postData = {"USE_AT":"Y"};
    postData = $("#frm").cmSerializeObject();

    $("#gridArea").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        ,postData: postData
        ,mtype: "POST"
        ,loadComplete: function(data) {

        }
    }).trigger("reloadGrid");
}

//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
    var btn = "";
    var nm = options.colModel.name;
    switch(nm) {
    case "btn_loc" :
            btn = "<a href='#' onclick=\"fn_select_route('" + rowObject.ROAD_NO +"','"+ rowObject.SRVY_YEAR + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
        break;
    case "btn_unloc" :
            btn = "<a href='#' onclick=\"fn_unselect_route('" + rowObject.ROAD_NO +"','"+ rowObject.SRVY_YEAR + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
        break;
    default :     
        break;
    }

    return btn;
}

//도로등급 변경 시 노선번호 자동 조회
function fn_change_roadNo(val) {
    var roadGrad = $("#ROAD_GRAD").val();

    $.ajax({
        url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify({ROAD_GRAD : roadGrad})
        ,success: function(data){
            var txtHtml = "<option value=''>== 전체 ==</option>";

            for(var i=0; i < data.length; i++){
                txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
            }

            var no = $("#ROAD_NO").val();
            var name = $("#ROAD_NAME").val();

            $("#ROAD_NO").html(txtHtml);
            $("#ROAD_NAME").val("");

            if(val != undefined){
                $("#ROAD_NO").val(val);
                fn_change_roadNm();
            }
        }
        ,error: function(a,b,msg){

        }
    });
}

//노선 번호 변경 시 노선명 자동 조회
function fn_change_roadNm() {
    var roadNo = $("#ROAD_NO").val();
    var roadGrad = $("#ROAD_GRAD").val();

    if(roadNo == "") {
        $("#ROAD_NAME").val("");
        $("#ROAD_GRAD").val("");
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
            $("#ROAD_GRAD").val(data.ROAD_GRAD);
        }
        ,error: function(a,b,msg){
            $("#ROAD_NAME").val("");
            $("#ROAD_GRAD").val("");
        }
    });
}

//조사구간 위치이동
function fn_select_route(route_no, srvy_year){
	
	if(!srvy_year || srvy_year == 'null'){
		alert('조사구간 위치가 없습니다');
		return ;
	}
	
	var params = {"ROUTE_CODE" : route_no, "SRVY_YEAR": srvy_year};
    $.ajax({
        url: contextPath + '/api/srvyunsection/sectionlocation.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
        ,data : JSON.stringify(params)
        ,success: function(data){
        	if(data.succ){
        		var item = data.item;
        		if(item && item.GEOJSON){
        			try{
		        		var geojson = item.GEOJSON;
			        	var format = new OpenLayers.Format.GeoJSON();
			        	var feature = format.read(geojson)[0]; 
			        	feature.attributes = {
			        		fillColor : '#FF0000',
			        		strokeColor : '#FF0000'
			        	};
			        	
			        	var gMap = parent.gMap;
			        	gMap.cleanMap();
			        	var layer = gMap.getLayerByName('GAttrLayer');
			        	layer.addFeatures(feature);
			        	gMap.zoomToExtent(layer.getDataExtent());
			        	
        			}catch(e){
        				console.log(e);
        			}
        		}else{
        			alert('조사구간 위치가 없습니다');
        		}
        	}
        }
        ,error: function(a,b,msg){}
    });
}

//미조사구간 위치이동
function fn_unselect_route(route_no, srvy_year){
	
	if(!srvy_year || srvy_year == 'null'){
		fn_routeLocation_move(route_no);
		return ;
	}
	
	var params = {"ROUTE_CODE" : route_no, "SRVY_YEAR": srvy_year};
    $.ajax({
        url: contextPath + 'api/srvyunsection/unsectionlocation.do'
        ,type: 'post'
        ,dataType: 'json'
        ,contentType : 'application/json'
		,data : JSON.stringify(params)
        ,success: function(data){
        	if(data.succ){
        		var item = data.item;
        		if(item && item.GEOJSON){
        			try{
		        		var geojson = item.GEOJSON;
			        	var format = new OpenLayers.Format.GeoJSON();
			        	var feature = format.read(geojson)[0]; 
			        	feature.attributes = {
			        		fillColor : '#0033ff',
			        		strokeColor : '#0033ff'
			        	};
			        	
			        	var gMap = parent.gMap;
			        	gMap.cleanMap();
			        	var layer = gMap.getLayerByName('GAttrLayer');
			        	layer.addFeatures(feature);
			        	gMap.zoomToExtent(layer.getDataExtent());
			        	
        			}catch(e){
        				console.log(e);
        			}
        		}else{
            		alert('미조사구간 위치가 없습니다');
        		}
        	}
        }
        ,error: function(a,b,msg){
        	
        }
    });
}

//노선 위치이동
function fn_routeLocation_move(route_no){
    parent.bottomClose();
    
    var tables = ["DORO_TOT_GRS80_50"];
    var fields = ["ROAD_NO"];
    var values = [route_no];
    var attribute = {
		attributes : {
		    fillColor : '#0033ff',
		    strokeColor : '#0033ff'
		}
    };
    MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, null, attribute);
}
<%-- 
//차트
require.config({
   paths: {
        echarts: '<%=request.getContextPath() %>/extLib/echarts' //js 파일 경로
    }
});
function drawGpmsLenChart(dataList,rw){
    var gRouteNm    = [];       
    var lenData     = [];
    var degree      = 40;
    if(dataList.length < 10){
        degree = 0;
    }
    for(var i=0; i<dataList.length; i++){
            gRouteNm.push(dataList[i].ROUTE_CODE+" "+dataList[i].ROAD_NAME);
            lenData.push(Number(dataList[i].LEN));
    }
    
	require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
		var myChart = ec.init(document.getElementById('gpmsLenBarChart'));
		myChart.setOption({
			title : {
				text : 'GPMS 총연장(km)'
			},
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : false,
				feature : {
					//dataView : {show: true, readOnly: false},     // 상세조회
					//saveAsExcel : {show: true},                   // 엑셀저장
					saveAsImage : {
						show : true
					}
				// 이미지저장
				}
			},
			grid : {
				/* width : rw+'px',
				x : 50, */
				y2 : 80
			},
			xAxis : [ {
				type : 'category',
				axisLabel : {
					show : true,
					interval : 0,
					rotate : degree
				},
				data : gRouteNm
			} ],
			yAxis : [ {
				name : 'km',
				type : 'value'
			} ],
			series : [ {
				name : '',
				type : 'bar',
				itemStyle : {
					normal : {
						color : function(params) {
							var colorList = [ '#C1232B', '#B5C334',
									'#FCCE10', '#E87C25', '#27727B',
									'#FE8463', '#9BCA63', '#FAD860',
									'#F3A43B', '#60C0DD', '#FE2752',
									'#7EEA59', '#F2B731', '#D8C35C',
									'#41C0AE', '#FE8422', '#6EEA54',
									'#F1B541', '#D6C32C', '#73C1AE',
									'#FD8610', '#CDEF60', '#FD6ACE',
									'#E6D410', '#20C5AD', '#DD810E',
									'#CCDA80', '#BE88CA', '#D21810',
									'#31D2BA', '#EE91AE', '#EFCBCD',
									'#CDE1BB', '#CDE991', '#98613B',
									'#FADE06', '#BB78FF', '#ECCDC0',
									'#FBA001', '#E6734C', '#FFBBEE',
									'#991122', '#DDEECC', '#AABCD5',
									'#484ABC' ];
							return colorList[params.dataIndex]
						}
					}
				},
				data : lenData
			} ]
		});
	});
 }
  --%>
</script>
</body>
</html>