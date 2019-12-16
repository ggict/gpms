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
<script src="<c:url value='/extLib/echarts/echarts.min.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

// 페이지 로딩 초기 설정
$( document ).ready(function() {
    /*
    var directFlag = "${mummSctnSrvyDtaVO.DIRECT_FLAG}";
    if ( directFlag == "NY" ) {
        $("#btnShowImg").show();
    }
    $("#btnGotoEvaluation").click(function() {
	    fnGotoEvaluation(param);
	});
	*/

    // 상세보기로 넘어온 경우 파라미터 받기
    var cellId = "${smDtaGnlSttusVO.CELL_ID}";

    //포장상태 기본정보, 조사정보 상세내용
    fnSelectSrvyDetail();

    //포장상태 평가정보 상세내용
    fnSelectEvaluationDetail();

 	//소성변형, 종단평탄성 데이터,궤적정보
 	chartInfoObj.getData();
	geoInfoObj.clickevt();
});

//궤적정보
var geoInfoObj = {
	clickevt: function(){
		$('#geoinfo').click(geoInfoObj.getData);
	}
	,getData : function(){
	    var cell_id = $("#CELL_ID").val();
	    var srvy_year = $("#SRVY_YEAR").val();
	    $.ajax({
	        url: contextPath + 'api/mumm/getrdairival.do'
	        ,type: 'post'
	        ,dataType: 'json'
	        ,contentType : 'application/json'
	        ,data : JSON.stringify({CELL_ID : cell_id, SRVY_YEAR: srvy_year})
	        ,success: function(data){
	        	var geojson = null;
	        	if(data.succ){
	        		if(data.res){
	        			geojson = data.res;
	        		}
	        	}
	        	geoInfoObj.grid(geojson);
	        }
	        ,error: function(a,b,msg){
				console.log(a);
	        }
	    });
	}
	,grid : function(geometrys){
		//var geoms = geoInfoObj.sampleGeom;
		var geoms = geometrys;
		if(geoms && geoms.length > 0 ){
			try{
	        	var gMap = parent.gMap;
	        	var layer = gMap.getLayerByName('GAttrLayer');
	        	var format = new OpenLayers.Format.GeoJSON();
	        	var features = [];
	        	for(var i=0; i< geoms.length; i++){
	        		var geojson = geoms[i].GEOJSON;
		        	var feature = format.read(geojson)[0];
		        	feature.attributes = {
		        		fillColor : '#ff0000',
		        		strokeColor : '#ff0000',
		        		pointRadius : '2'
		        	};
		        	features.push(feature);
	        	}
	        	gMap.cleanMap();
	        	layer.addFeatures(features);
	        	gMap.zoomToExtent(layer.getDataExtent());
	        	parent.bottomClose();
			}catch(e){
				console.log(e);
			}
		}else{
			alert('위치정보가 없습니다.');
		}
	}
	,sampleGeom : [
		 {"type":"Point","coordinates":[200326.7244,498724.473650001]}
		,{"type":"Point","coordinates":[200317.912292839,498729.300408196]}
		,{"type":"Point","coordinates":[200308.8797,498733.6956]}
		,{"type":"Point","coordinates":[200299.786572703,498737.970960377]}
		,{"type":"Point","coordinates":[200290.52175,498741.8496]}
		,{"type":"Point","coordinates":[200281.219376299,498745.519435337]}
		,{"type":"Point","coordinates":[200271.812429153,498749.016715579]}
		,{"type":"Point","coordinates":[200262.2937,498752.20715]}
		,{"type":"Point","coordinates":[200252.8121,498755.38505]}
		,{"type":"Point","coordinates":[202097.207715085,485988.470075922]}
	]
};

//소성변형, 종단평탄성 차트
var chartInfoObj = {
	getData : function(){
	    var cell_id = $("#CELL_ID").val();
	    var srvy_year = $("#SRVY_YEAR").val();
	    $.ajax({
	        url: contextPath + 'api/mumm/getrdairival.do'
	        ,type: 'post'
	        ,dataType: 'json'
	        ,contentType : 'application/json'
	        ,data : JSON.stringify({CELL_ID : cell_id, SRVY_YEAR: srvy_year})
	        ,success: function(data){
	        	var obj = chartInfoObj;
	        	obj.drawRdChart(data);
	        	obj.drawIRIChart(data);
	        }
	        ,error: function(a,b,msg){
				console.log(a);
	        }
	    });
	}
	,drawRdChart: function(data){
		var dataList = data.res;
		var xAxisData =[];
	 	var lineData = [];
	 	for(var i=0; i<dataList.length; i++){
	 		xAxisData.push(dataList[i].RD_VAL);
	 		lineData.push(Number(dataList[i].RD_VAL));
	 	}
	 	if(xAxisData.length == 0) xAxisData = [0.00];
	 	if(lineData.length == 0) lineData = [0.00];

		var myChart = echarts.init(document.getElementById('rdChart'));
		myChart.setOption({
			color : [ '#003366', '#4cabce' ],
			title : {
				text : '소형변형'
				,textStyle: {
					fontSize: 12
				}
			},
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : true,
				feature : {
					//saveAsImage: {show: true}					// 이미지저장
				}
			},
			legend : {
				data : ['소형변형']
			},
			grid : {
				top : 5
				,bottom: 10
			},
			xAxis : [ {
				type : 'category',
				data : xAxisData
			} ],
			yAxis : [{
				type : 'value'
			}],
			series : [ {
				name : '소형변형',
				type : 'line',
				data : lineData
			}]
		});
	}
	,drawIRIChart: function(data){
		var dataList = data.res;
	 	var xAxisData =[];
	 	var lineData = [];
	 	for(var i=0; i<dataList.length; i++){
	 		xAxisData.push(dataList[i].IRI_VAL);
	 		lineData.push(Number(dataList[i].IRI_VAL));
	 	}
	 	if(xAxisData.length == 0) xAxisData = [0.00];
	 	if(lineData.length == 0) lineData = [0.00];

		var myChart = echarts.init(document.getElementById('iriChart'));
		myChart.setOption({
			color : [ '#003366', '#4cabce' ],
			title : {
				text : '중단평탄성'
				,textStyle: {
					fontSize: 12
				}
			},
			tooltip : {
				trigger : 'axis'
			},
			toolbox : {
				show : true,
				feature : {
					//saveAsImage: {show: true}					// 이미지저장
				}
			},
			legend : {
				data : ['중단평탄성']
			},
			grid : {
				top : 5
				,bottom: 10
			},
			xAxis : [ {
				type : 'category',
				data : xAxisData
			} ],
			yAxis : [{
				type : 'value'
			}],
			series : [ {
				name : '중단평탄성',
				type : 'line',
				data : lineData
			}]
		});
	}
};



// 포장상태 기본정보, 조사정보 상세내용
function fnSelectSrvyDetail() {

	/* var postDatas = { "CELL_ID": param.CELL_ID };

	$.ajax({
        url: "<c:url value='/api/mummsctnsrvydta/mummSctnSrvyDtaSctnList.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {

            if ( jdata != undefined && jdata != null && jdata != "" ) {

                var tb1 = $("#baseInfo").find("table td");*/
                var tb2 = $("#srvyData").find("table td");

                /* // 기본정보
                tb1.eq(0).html(jdata[0].SRVY_YEAR + ". " + jdata[0].SRVY_MT);
                tb1.eq(1).html(jdata[0].ROAD_NO_VAL);
                tb1.eq(2).html(jdata[0].ROAD_NM);
                tb1.eq(3).html(jdata[0].DIRECT_NM);
                tb1.eq(4).html(jdata[0].TRACK);
                tb1.eq(5).html(jdata[0].ROAD_GRAD);
                tb1.eq(6).html(jdata[0].STRTPT + " ~ " + jdata[0].ENDPT);
                tb1.eq(7).html(jdata[0].DEPT_CODE);
                tb1.eq(8).html(jdata[0].CELL_TYPE);
                tb1.eq(9).html(jdata[0].VMTC_GRAD);
                tb1.eq(10).html(jdata[0].ADM_CODE); */

                var AC_MED = "${smDtaGnlSttusVO.AC_MED}";
                var LC_MED = "${smDtaGnlSttusVO.LC_MED}";
                var BLOCK_CR_MED = "${smDtaGnlSttusVO.BLOCK_CR_MED}";
                var PTCHG_CR = "${smDtaGnlSttusVO.PTCHG_CR}";
                var POTHOLE_CR = "${smDtaGnlSttusVO.POTHOLE_CR}";
                var RD_VAL = "${smDtaGnlSttusVO.RD_VAL}";
                var IRI_VAL = "${smDtaGnlSttusVO.IRI_VAL}";

             	// 조사정보
                tb2.eq(0).html( fnFloat(AC_MED) );        // 거북등균열
                tb2.eq(1).html( fnFloat(LC_MED) );        // 선형균열
                tb2.eq(2).html( fnFloat(BLOCK_CR_MED) );  // 블럭균열
                tb2.eq(3).html( fnFloat(PTCHG_CR) );      // 패칭
                tb2.eq(4).html( fnFloat(POTHOLE_CR) );    // 포트홀
                tb2.eq(5).html( fnFloat(RD_VAL) );        // 소성변형
                tb2.eq(6).html( fnFloat(IRI_VAL) );       // 종단평탄성
                //tb2.eq(7).html( parseFloat(jdata[0].aaa).toFixed(2) );       // 교통량
            /*
            }

        },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;

        }
    });
	 */
}

// 포장상태 평가정보 상세내용
function fnSelectEvaluationDetail() {

    /* var postDatas = { "CELL_ID": cellId }; */


   /*  $.ajax({
        url: "<c:url value='/api/mummsctnsrvydta/selectMummSctnSrvyDtaListBySrvyAvg.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {

            if ( jdata != undefined && jdata != null && jdata != "" ) { */

                var li = $("#mummAvg").find("li");
                var tb = $("#mummAvg").find("table td");

                /* // 주 파손, 파손원인 값 도출
                if ( jdata.AC_IDX == null || jdata.BC_IDX == null || jdata.LC_IDX == null || jdata.TC_IDX == null
                        || jdata.PTCHG_IDX == null || jdata.POTHOLE_IDX == null || jdata.RD_IDX == null || jdata.RCI == null ) {

                    // null인 값이 있는 경우 초기값 그대로 표출
                    return;

                }
                 */
                // 평가정보 값
                var CNTL_DFECT = "${smDtaGnlSttusVO.CNTL_DFECT}";
                var CODE_NM = "${smDtaGnlSttusVO.CNTL_DFECT_NM}";
                var AC_IDX = "${smDtaGnlSttusVO.AC_IDX}";
                var LC_IDX = "${smDtaGnlSttusVO.LC_IDX}";
                var BC_IDX = "${smDtaGnlSttusVO.BC_IDX}";
                var PTCHG_IDX = "${smDtaGnlSttusVO.PTCHG_IDX}";
                var POTHOLE_IDX = "${smDtaGnlSttusVO.POTHOLE_IDX}";
                var RD_IDX = "${smDtaGnlSttusVO.RD_IDX}";
                var RCI = "${smDtaGnlSttusVO.RCI}";
                var DMG_CUZ_CLMT = "${smDtaGnlSttusVO.DMG_CUZ_CLMT}";
                var DMG_CUZ_VMTC = "${smDtaGnlSttusVO.DMG_CUZ_VMTC}";
                var DMG_CUZ_ETC = "${smDtaGnlSttusVO.DMG_CUZ_ETC}";

                // 주 파손
                var crVal = "";

                if ( CNTL_DFECT != "DFCT0009" ) {
                    var codeNm = CODE_NM;

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
                    var maxVal = Math.max( AC_IDX, BC_IDX, LC_IDX, PTCHG_IDX, POTHOLE_IDX, RD_IDX, RCI );
                    var nameArr = [ "거북등균열", "블럭균열", "선형균열", "패칭", "포트홀", "소성변형", "종단평탄성" ];
                    var valArr = [ AC_IDX, BC_IDX, LC_IDX, PTCHG_IDX, POTHOLE_IDX, RD_IDX, RCI ];

                    if ( maxVal == 0 ) {
                        // max 값이 0인 경우는 파손없음
                        crVal += "파손없음";

                    } else {
                        crVal += "<br />복합파손 <br /> (";
                        var codeNames = [];

                        for ( var i = 0; i < valArr.length; i++ ) {
                            // max값과 같은 경우 텍스트 추가
                            if ( valArr[i] == maxVal ) {
                                /*
                            	if ( i != 0 ) {
                                    crVal += ", ";

                                }
                                crVal += nameArr[i];
 								*/
                            	codeNames.push(nameArr[i]);
                            }
                        }

                        crVal += codeNames.join(',');
                        crVal += ")";

                        $("#crVal").css({"line-height": "11px", "font-size": "14px"});
                    }
                }

                // 파손원인
                var clmt = DMG_CUZ_CLMT;
                var vmtc = DMG_CUZ_VMTC;
                var etc = DMG_CUZ_ETC;
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

                // 평가정보
                //$("#gpci").text( parseFloat(GPCI).toFixed(2) );
                $("#crVal").text(crVal);
                $("#dmgCuz").text(cuz);

                tb.eq(0).html( parseFloat(10 - AC_IDX).toFixed(2) );
                tb.eq(1).html( parseFloat(10 - LC_IDX).toFixed(2) );
                tb.eq(2).html( parseFloat(10 - BC_IDX).toFixed(2) );
                tb.eq(3).html( parseFloat(10 - PTCHG_IDX).toFixed(2) );
                tb.eq(4).html( parseFloat(10 - POTHOLE_IDX).toFixed(2) );
                tb.eq(5).html( parseFloat(10 - RD_IDX).toFixed(2) );
                tb.eq(6).html( parseFloat(10 - RCI).toFixed(2) );

            /* }

        },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;

        }
    }); */

}

//현행정보조회
function fnSelectLastSttus(obj) {

    if ( !obj.hasClass("on") ) {

	    var postDatas = { "CELL_ID" : "${smDtaGnlSttusVO.CELL_ID}" };

	    $.ajax({
	        url: "<c:url value='/api/smdtalaststtus/selectSmDtaLastSttusBySrvy.do' />",
	        contentType: 'application/json',
	        data: JSON.stringify(postDatas),
	        dataType: "json",
	        cache: false,
	        processData: false,
	        type: 'POST',
	        async: false,
	        success: function (jdata) {

	            if ( jdata != undefined && jdata != null && jdata != "" ) {

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
							var codeNames = [];
	                        for ( var i = 0; i < valArr.length; i++ ) {

	                            // max값과 같은 경우 텍스트 추가
	                            if ( valArr[i] == maxVal ) {
	                            	/*
	                                if ( i != 0 ) {
	                                    crVal += ", ";
	                                }
	                                crVal += nameArr[i];
	                                 */
	                                 codeNames.push(nameArr[i]);

	                            }
	                        }

	                        crVal += codeNames.join(',');
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

	                // 이전정보 저장
	                preCrVal = $("#crVal").text();
	                preDmgCuz = $("#dmgCuz").text();

	                // 평가정보
	                $("#gpci").html(fnFloat(jdata.GPCI));
	                $("#crVal").html(crVal);
	                $("#dmgCuz").html(cuz);

	                var tb = $("#mummAvg").find("table td");
	                tb.eq(0).html(fnFloat(10 - jdata.AC_IDX));
	                tb.eq(1).html(fnFloat(10 - jdata.LC_IDX));
	                tb.eq(2).html(fnFloat(10 - jdata.BC_IDX));
	                tb.eq(3).html(fnFloat(10 - jdata.PTCHG_IDX));
	                tb.eq(4).html(fnFloat(10 - jdata.POTHOLE_IDX));
	                tb.eq(5).html(fnFloat(10 - jdata.RD_IDX));
	                tb.eq(6).html(fnFloat(10 - jdata.RCI));

	                obj.addClass("on");
	                obj.text("조사평가정보조회");
	            }
	        },
	        error: function () {
	            alert("데이터를 조회할 수 없습니다.");
	            return;
	        }
	    });

    } else {

        $("#gpci").text(fnFloat("${smDtaGnlSttusVO.GPCI}"));
        $("#crVal").text(preCrVal);
        $("#dmgCuz").text(preDmgCuz);

        var tb = $("#mummAvg").find("table td");
        tb.eq(0).html(fnFloat(10 - "${smDtaGnlSttusVO.AC_IDX}"));
        tb.eq(1).html(fnFloat(10 - "${smDtaGnlSttusVO.LC_IDX}"));
        tb.eq(2).html(fnFloat(10 - "${smDtaGnlSttusVO.BC_IDX}"));
        tb.eq(3).html(fnFloat(10 - "${smDtaGnlSttusVO.PTCHG_IDX}"));
        tb.eq(4).html(fnFloat(10 - "${smDtaGnlSttusVO.POTHOLE_IDX}"));
        tb.eq(5).html(fnFloat(10 - "${smDtaGnlSttusVO.RD_IDX}"));
        tb.eq(6).html(fnFloat(10 - "${smDtaGnlSttusVO.RCI}"));

        obj.removeClass("on");
        obj.text("수시평가정보조회");

    }

}

/* function fnGotoEvaluation(param) {

    var cellIds = "";
    var postDatas = { "CELL_ID": param.CELL_ID };

    $.ajax({
        url: "<c:url value='/api/mummsctnsrvydta/mummSctnSrvyDtaCellInfo.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {

            if ( jdata != undefined && jdata != null && jdata != "" ) {

                for ( var i = 0; i < jdata.length; i++ ) {

                    if ( i != 0 ) {

                        cellIds += ",";

                    }

                    cellIds += jdata[i].CELL_ID;

                }

                parent.bottomOpen();

                parent.$(".btmenuarea").find("div").hide();
                parent.$("#sub_srvyEvl").show();
                parent.$("#sub_srvyEvl").find("li:eq(0)").attr("class", "sel");

                var directFlag = "N";

                if ( $("#btnShowImg").css("display") != "none" ) {

                    directFlag = "NY";

                }


                parent.COMMON_UTIL.cmMenuUrlContent('mng/mummsctnsrvydta/mummSctnSrvyDtaList.do?CELL_ID=' + cellIds + '&OBJECT_ID=' + param.OBJECT_ID + "&DIRECT_FLAG=" + directFlag, true);

            }

        },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;

        }
    });

} */

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

function fnFloat(val) {

    return parseFloat(val).toFixed(2);

}

</script>
</head>

<body>

<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="CELL_ID" name="CELL_ID" value="${smDtaGnlSttusVO.CELL_ID }"/>
<input type="hidden" id="SRVY_YEAR" name="SRVY_YEAR" value="${smDtaGnlSttusVO.SRVY_YEAR}"/>
<div class="tabcont">
    <div class="">
        <h3>
            포장상태 조사정보 상세조회
            <!-- <a href="#" id="btnShowImg" onclick="fnShowImg();" style="display: none; margin-left: 5px; padding: 5px 10px; border-radius: 50px; width: 150px; text-align: center; color: rgb(255, 255, 255); font-size: 11px; font-weight: bolder; background-color: gray;">균열분석 이미지</a> -->
        </h3>
        <div class="ytabbx" style="left: 280px; position:absolute; top:0px;">
		    <ul class="ytab fl">
		    	<c:forEach items="${srvyYearList }" var="srvyYear">
		    		<li <c:if test="${srvyYear.SRVY_YEAR eq smDtaGnlSttusVO.SRVY_YEAR }">class="on"</c:if>>
		    			<a href="#" onclick="COMMON_UTIL.cmMoveUrl('/mng/mummsctnsrvydta/mummSctnSrvyDtaDetail.do?CELL_ID=${smDtaGnlSttusVO.CELL_ID }&SRVY_YEAR=${srvyYear.SRVY_YEAR }')">${srvyYear.SRVY_YEAR }</a>
		    		</li>
		    	</c:forEach>
	        </ul>
        </div>
        <p class="location">
            <!--
            <span>조사정보조회</span>
            <span>포장상태 조사정보 조회</span>
            <strong>포장상태 조사정보 상세조회</strong>
             -->
            <input type="button" class="btn pri" id="geoinfo" value="궤적정보"/>
        </p>
        <div class="mt10 ml10 mr10">

            <!-- 섹션 기본정보 START -->
            <div id="baseInfo" style="width:34%; height: 210px; float: left; padding-right: 10px;">
                <h3 class="h3">기본정보</h3>
                <table class="table">
                    <caption class="hidden">포장상태 기본정보</caption>
                    <colgroup>
                        <col width="20%" />
                        <col width="30%" />
                        <col width="20%" />
                        <col width="30%" />
                    </colgroup>
                    <tbody style="text-align: center;">
                        <tr>
                            <th colspan="2" scope="row" style="border-right: solid 0px">조사년월</th>
                            <td colspan="2">${smDtaGnlSttusVO.SRVY_YEAR }. ${smDtaGnlSttusVO.SRVY_MT }</td>
                        </tr>
                        <tr>
                            <th scope="row">노선번호</th>
                            <td>${smDtaGnlSttusVO.ROAD_NO_VAL }</td>
                            <th scope="row">노선명</th>
                            <td>${smDtaGnlSttusVO.ROAD_NM }</td>
                        </tr>
                        <tr>
                            <th scope="row">행선</th>
                            <td>${smDtaGnlSttusVO.DIRECT_NM }</td>
                            <th scope="row">차로</th>
                            <td>${smDtaGnlSttusVO.TRACK }</td>
                        </tr>
                        <tr>
                            <th scope="row">도로등급</th>
                            <td>${smDtaGnlSttusVO.ROAD_GRAD }</td>
                            <th scope="row">이정(m)</th>
                            <td>${smDtaGnlSttusVO.STRTPT } ~ ${smDtaGnlSttusVO.ENDPT }</td>
                        </tr>
                        <tr>
                            <th scope="row">관리기관</th>
                            <td>${smDtaGnlSttusVO.DEPT_CODE }</td>
                            <th scope="row">셀종류</th>
                            <td>${smDtaGnlSttusVO.CELL_TYPE }</td>
                        </tr>
                        <tr>
                            <th scope="row">교통용량</th>
                            <td>${smDtaGnlSttusVO.VMTC_GRAD }</td>
                            <th scope="row">행정구역</th>
                            <td>${smDtaGnlSttusVO.ADM_CODE }</td>
                        </tr>
                    </tbody>

                </table>
            </div>
            <!-- 섹션 기본정보 END -->

            <!-- 포장상태 조사자료 START -->
            <div id="srvyData" style="width:32%; height: 210px; float: left; padding-right: 10px; border-radius: 5px;">
                <h3 class="h3">조사자료</h3>
                <table class="table">
                    <caption class="hidden">포장상태 조사자료</caption>
                    <colgroup>
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />
                    </colgroup>
                    <tbody>
                        <tr style="height: 20%;">
                            <th scope="row">거북등균열(㎡)</th>
                            <td>${smDtaGnlSttusVO.AC_MED }</td>
                            <th scope="row">선형균열(m)</th>
                            <td>${smDtaGnlSttusVO.LC_MED }</td>
                        </tr>
                        <tr style="height: 20%;">
                            <th scope="row">블럭균열(㎡)</th>
                            <td colspan="3">${smDtaGnlSttusVO.BLOCK_CR_MED }</td>
                        </tr>
                        <tr style="height: 20%;">
                            <th scope="row">패칭(㎡)</th>
                            <td>${smDtaGnlSttusVO.PTCHG_CR }</td>
                            <th scope="row">포트홀(㎡)</th>
                            <td>${smDtaGnlSttusVO.POTHOLE_CR }</td>
                        </tr>
                        <tr style="height: 20%;">
                            <th scope="row">소성변형(mm)</th>
                            <td>${smDtaGnlSttusVO.RD_VAL }</td>
                            <th scope="row">종단평탄성(m/km)</th>
                            <td>${smDtaGnlSttusVO.IRI_VAL }</td>
                        </tr>
                        <tr style="height: 20%;">
                            <th scope="row">교통량</th>
                            <td colspan="3">-</td>
                        </tr>
                    </tbody>

                </table>
            </div>
            <!-- 포장상태 조사자료 END -->

            <!-- 포장상태 평가자료 START -->
            <div id="mummAvg" style="width:30%; float: left; height: 210px; ">
                <!-- <h3 style="width: 100%; line-height: 30px; font-size: 15px; float: left;">포장상태 평가정보</h3> -->
                <%--
                <h3 style="width: 100%; line-height: 30px; font-size: 15px; float: left; padding-right: 0px;"><span>포장상태 평가정보</span><a href="#" style="float:right; line-height: 11px; margin-top: 5px;" class="titbtn" onclick="fnSelectLastSttus($(this));">수시평가정보조회</a></h3>
                <ul class="tblst mt15" style="padding-top: 35px;">
                    <li style="width:33%;border-left:0px" class="brl tc">
                        <span class="circle bc6" id="gpci" style="width: 85%; height: 40px; line-height: 40px; font-size: 16px;">${smDtaGnlSttusVO.GPCI }</span>
                        <span>GPCI</span>
                    </li>
                    <li style="width:33%" class="brl tc">
                        <span class="circle bc5" id="crVal" style="width: 85%; height: 40px; line-height: 40px; font-size: 16px;">없음</span>
                        <span>주 파손</span>
                    </li>
                    <li style="width:33%" class="tc">
                        <span class="circle bc7" id="dmgCuz" style="width: 85%; height: 40px; line-height: 40px; font-size: 16px;">없음</span>
                        <span>파손원인</span>
                    </li>
                </ul>

                <table class="tbview" style="width: 100%; height: 121px; margin-top: 10px;">
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
                            <td  colspan="3">0</td>
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
                 --%>
                <!-- <a href="#" id="btnGotoEvaluation" style="padding: 5px; border-radius: 50px; width: 150px; text-align: center; color: rgb(255, 255, 255); font-size: 11px; font-weight: bolder; margin-top: 5px; float: right; background-color: rgba(69, 135, 255, 1);">평가정보 상세보기</a> -->



				<div id="rdChart" class="cont_ConBx2" style="height: 150px;"></div>
				<div id="iriChart" class="cont_ConBx2" style="height: 150px;"></div>
            </div>
            <!-- 포장상태 평가자료 END -->

        </div>
    </div>
</div>
</form>
<%-- </form> --%>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->

</body>
</html>
