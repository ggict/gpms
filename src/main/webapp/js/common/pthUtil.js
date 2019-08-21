/* 2018. 05. 28. JOY. 포트홀 지도 > 현황 탭 관리 Util */
var PTH_UTIL = {

        // 전체 현황 값, 피처
        featureUtil : function(mode, oper, pthmode, pthRgNo, tmX, tmY, headg, aftValue) {

            var layer = null; // 레이어
            var totNm = null; // 총 건

            if ( mode == "status" ) {

                layer = gMap.getLayerByName("GStatusLayer");
                totNm = "status-count";

            } else if ( mode == "type" ) {

                layer = gMap.getLayerByName("GTypeLayer");
                totNm = "type-count";

            } else {

                return;

            }

            var totCnt = parseInt( $("#" + totNm).text().replace(",", "") );

            if ( oper == "add" ) {

                // 값 증가
                $("#" + totNm).text( COMMON_UTIL.cmAddComma( totCnt + 1 ) );

                // 피쳐 추가
                var postData = { "PTH_RG_NO" : cvtPthRgNo(pthRgNo),
                		         "pthmode"   : pthmode
                			   };

                $.ajax({
                    url : contextPath + 'api/map/selectSttemntData.do'
                    , type : 'post'
                    , dataType : 'json'
                    , contentType : 'application/json; charset=UTF-8'
                    , data : JSON.stringify(postData)
                    , success : function(jdata) {

                        layer.addFeatures(
                                new OpenLayers.Feature.Vector(
                                        new OpenLayers.Geometry.Point( tmX, tmY )
                                        , {
                                            allData     : jdata.data
                                            , angle     : headg * 1 - 90
                                            , pthRgNo   : cvtPthRgNo(pthRgNo)
                                            , pthmode	: pthmode
                                            , data      : aftValue
                                        }
                                )
                        );

                        layer.redraw();

                    }

                });

            } else if ( oper = "rmv" ) {

                // 값 감소
                $("#" + totNm).text( COMMON_UTIL.cmAddComma( totCnt - 1 ) );

                // 피쳐 삭제
                var features = layer.features;

                for ( var i = 0; i < features.length; i++ ) {

                    if (( features[i].attributes.pthRgNo == cvtPthRgNo(pthRgNo) )
                    		&& ( features[i].attributes.pthmode == pthmode ) ){

                        features[i].destroy();

                        break;

                    }

                }

            } else {

                return;

            }

            layer.redraw();

        },

        // 개별 현황
        countUtil : function(mode, bfrValue, aftValue) {

            // id 값
            var idNm = null;

            if ( mode == "status" ) {

                idNm = "PRCS_STTUS";

            } else if ( mode == "type" ) {

                idNm = "DMG_TYPE";

            } else {

                return;

            }

            // 값 변경
            if ( bfrValue != "" && bfrValue != null && bfrValue != undefined ) {

                var flag = bfrValue.substring(7, 8);
                $("#" + idNm + flag).text( COMMON_UTIL.cmAddComma( parseInt( $("#" + idNm + flag).text().replace(",", "") ) - 1 ) );

            }

            if ( aftValue != "" && aftValue != null && aftValue != undefined ) {

                var flag = aftValue.substring(7, 8);
                $("#" + idNm + flag).text( COMMON_UTIL.cmAddComma( parseInt( $("#" + idNm + flag).text().replace(",", "") ) + 1 ) );

            }

        },

        // 마커 변경
        changeMarker : function(mode, pthmode, pthRgNo, aftValue) {

            var layer = null; // 레이어

            if ( mode == "status" ) {

                layer = gMap.getLayerByName("GStatusLayer");

            } else if ( mode == "type" ) {

                layer = gMap.getLayerByName("GTypeLayer");

            } else {

                return;

            }

            // 값 변경
            var features = layer.features;

            for ( var i = 0; i < features.length; i++ ) {

                if (( features[i].attributes.pthRgNo == cvtPthRgNo(pthRgNo) )
                		&& ( features[i].attributes.pthmode == pthmode ) ){

                    features[i].data.data = aftValue;
                    features[i].attributes.data = aftValue;

                    if ( mode == "status" ) {

                        features[i].data.allData.PRCS_STTUS = aftValue;
                        features[i].attributes.allData.PRCS_STTUS = aftValue;

                    } else if ( mode == "type" ) {

                        features[i].data.allData.DMG_TYPE = aftValue;
                        features[i].attributes.allData.DMG_TYPE = aftValue;

                    } else {

                        return;

                    }

                    break;

                }

            }

            layer.redraw();

        }

}