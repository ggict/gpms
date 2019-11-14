/*
 * 포장상태 조사정보조회
 */

var features;
var option;

$(document).ready(function() {
    // 포장상태 조사정보 조회 버튼 클릭 이벤트
    $("#researchInfo").click(function() {
		option = {
			iframe : window,
			callback : "fnCheckFeatures",
			clearMap : false
		};
		gMap.cleanMap();
		bottomClose();
		MAP.CONTROL.set_option(option);
		gMap.getControl("selPointResearch").setDistance(1);
		gMap.activeControls(["drag","selPointResearch"]);
		$(".left_tool li").removeClass("active");
    });
});

function fnCheckFeatures() {
    gMap.activeControls("drag");
    parent.$("#mCtrlPan").parent().addClass("active");
    
    features = parent.gMap.getLayerByName('GAttrLayerMulti').features;
    if(features.length == 0){
        return -1;
    }else{
        try {
        	option.cellCount = features.length;
            fnSelectSection();
        } catch (err) {
            alert (err);
            return;
        }
        return 1;
    }
}

// 조사정보 Section Select
function fnSelectSection() {
    var cell_id = features[0].data.CELL_ID || features[0].data.cell_id;
    var postData = { "CELL_ID" : cell_id };

    $.ajax({
        url: contextPath + '/api/mummsctnsrvydta/mummSctnSrvyDtaSctnByCell.do',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {
            if ( jdata.rstFlag == 1 ) {
                parent.gMap.cleanMap();
                var objId = jdata.SECT_CELL_ID || jdata.sect_cell_id;
                var cellId = jdata.CELL_ID || jdata.cell_id;
                // Section 그리기
                var tables_base = ["CELL_SECT"];
                var fields_base = ["CELL_ID"];
                var values_base = [objId];
                var attribute_base = {
                    attributes : {
                        fillColor : '#0033ff',
                        strokeColor : '#0033ff'
                    }
                };
                MAP.fn_get_selectFeatureByAttr(parent.parent.gMap, tables_base, fields_base, values_base, null, "OR", attribute_base);

                // CELL 그리기
                var tables = ["CELL_10"];
                var fields = ["CELL_ID"];
                var values = [cellId];
                var attribute = {
                    attributes : {
                        fillColor : '#ffffff',
                        strokeColor : '#ffffff'
                    }
                };
                MAP.fn_get_selectFeatureByAttrMulti(parent.parent.gMap, tables, fields, values, null, "OR", attribute, false, 0, 1);

                // 하단팝업 정보조회
                fnSearchResearchDetail(objId);
                // 상단팝업 조사정보이미지 + 로드뷰
                fnSearchResearchView(jdata);
            }else{
                alert("선택한 셀이 포함 된 섹션에 조사정보가 존재하지 않습니다.");
                $("#researchInfo").parent('li').removeClass('active');
                parent.gMap.cleanMap();
                return;
            }
        },
        error: function (err) {
            return;
        }
    });

}

// 팝업 내용 조회
function fnSearchResearchDetail(objId) {
    bottomOpen();
    $(".btmenuarea").find("div").hide();
    $("#sub_mummSctnSrvyDtaDetail").show();
    $("#sub_mummSctnSrvyDtaDetail").find("li:eq(0)").attr("class", "sel");
    COMMON_UTIL.cmMenuUrlContent('mng/mummsctnsrvydta/mummSctnSrvyDtaDetail.do?CELL_ID=' + objId, true);
}

// 조사정보 상단팝업 조회 - 바꿀 예정
function fnSearchResearchView(jdata) {
    // 팝업 크기 지정
    var height = $(parent).height();
    var width = $(parent).width();

    var h = height - 406;
    var w = 0;
    if ( width < 1400 ) {
        w = 400
    } else {
        w = ( width / 2 ) - 300;
    }

    // center 구하기
    var l = features[0].geometry.bounds.left;
    var r = features[0].geometry.bounds.right;
    var t = features[0].geometry.bounds.top;
    var b = features[0].geometry.bounds.bottom;

    var x = ( l + r ) / 2;
    var y = ( t + b ) / 2;

    COMMON_UTIL.cmWindowOpen('노면분석이미지', contextPath + 'mng/mummsctnsrvydta/mummSctnSrvyDtaDetailIMG.do?DTA_NO=' + jdata.DTA_NO, w, h, false, null, 'researchImg');
    COMMON_UTIL.cmWindowOpen('로드뷰', contextPath + 'mng/mummsctnsrvydta/mummSctnSrvyDtaDetailRV.do?x=' + x + "&y=" + y, w, h, false, null, 'roadView');
}