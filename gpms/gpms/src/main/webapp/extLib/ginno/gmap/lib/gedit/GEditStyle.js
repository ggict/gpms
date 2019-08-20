/****************************************************************
 *
 * 파일명 : GEditStyle.js
 * 설  명 : 편집에 사용되는 vector레이어상의 feature 스타일 정의
 ****************************************************************
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2016.03.18      최재훈       1.0             최초생성
 */

/**
 * 기본심볼 정의
 * */

OpenLayers.Feature.Vector.style = {
    'default': {
        fillColor: "#ee9900",
        fillOpacity: 0.4,
        hoverFillColor: "white",
        hoverFillOpacity: 0.8,
        strokeColor: "#ee9900",
        strokeOpacity: 1,
        strokeWidth: 1,
        strokeLinecap: "round",
        strokeDashstyle: "solid",
        hoverStrokeColor: "red",
        hoverStrokeOpacity: 1,
        hoverStrokeWidth: 0.2,
        pointRadius: 6,
        hoverPointRadius: 1,
        hoverPointUnit: "%",
        pointerEvents: "visiblePainted",
        cursor: "inherit",
        fontColor: "#000000",
        labelAlign: "cm",
        labelOutlineColor: "white",
        labelOutlineWidth: 3
    },
    'select': {
        fillColor: "#FF7373",
        fillOpacity: 0.6,
        hoverFillColor: "white",
        hoverFillOpacity: 0.8,
        strokeColor: "#FF4545",
        strokeOpacity: 1,
        strokeWidth: 4,
        strokeLinecap: "round",
        strokeDashstyle: "solid",
        hoverStrokeColor: "red",
        hoverStrokeOpacity: 1,
        hoverStrokeWidth: 0.2,
        pointRadius: 6,
        hoverPointRadius: 1,
        hoverPointUnit: "%",
        pointerEvents: "visiblePainted",
        cursor: "pointer",
        fontColor: "#000000",
        labelAlign: "cm",
        labelOutlineColor: "white",
        labelOutlineWidth: 3

    },
    'temporary': {
        fillColor: "#66cccc",
        fillOpacity: 0.2,
        hoverFillColor: "white",
        hoverFillOpacity: 0.8,
        strokeColor: "#66cccc",
        strokeOpacity: 1,
        strokeLinecap: "round",
        strokeWidth: 2,
        strokeDashstyle: "solid",
        hoverStrokeColor: "red",
        hoverStrokeOpacity: 1,
        hoverStrokeWidth: 0.2,
        pointRadius: 6,
        hoverPointRadius: 1,
        hoverPointUnit: "%",
        pointerEvents: "visiblePainted",
        cursor: "inherit",
        fontColor: "#000000",
        labelAlign: "cm",
        labelOutlineColor: "white",
        labelOutlineWidth: 3

    },
    'delete': {
        display: "none"
    }
};

GEditStyle = {

		'_default' : {
			fillColor: '#00CCFF',
            fillOpacity: 0.5,
            strokeColor: '#00AAFF',
            strokeWidth: 2,
            graphicZIndex: 1,
            pointRadius: 5
		},

		'select' : {
			fillColor: '#DE6868',
            fillOpacity: 0.7,
            strokeColor: '#CC0000',
            strokeWidth: 2,
            graphicZIndex: 1,
            pointRadius: 5
		},

		'defaultLabel' : {
            fillColor: '#EC5781',
            fillOpacity: 0.8,
            strokeColor: '#E11A51',
            strokeWidth: 2,
            graphicZIndex: 11,
            pointRadius: 0,
            cursor: 'default',
            label: '${label}',
            fontColor: '#000000',
            fontSize: '11px',
            fontFamily: 'Verdana, Arial, Helvetica, sans-serif',
            fontWeight: 'bold',
            labelAlign: 'cm',
            labelXOffset: '${xOffset}',
            labelYOffset: '${yOffset}',
            labelOutlineColor: '#FFFFFF',
            labelOutlineWidth: 4,
            labelSelect: false
		},

		'selectLabel' : {
            fillColor: '#fc0',
            fillOpacity: 0.8,
            strokeColor: '#f70',
            strokeWidth: 2,
            graphicZIndex: 2,
			pointRadius: 5,
            cursor: 'default',
            label: '${label}',
            fontColor: 'black',
            fontSize: '11px',
            fontFamily: 'Verdana, Arial, Helvetica, sans-serif',
            fontWeight: 'bold',
            labelAlign: 'cm',
            labelXOffset: '${xOffset}',
            labelYOffset: '${yOffset}',
            labelOutlineColor: '#fc0',
            labelOutlineWidth: 6,
            labelSelect: true
		}
};

/**********************************************************************************
 * 설 명 : 벡터레이어의 StyleMap 구성을 위한 정의
 * 인 자 : N/A
 * 사용법 : 각 함수별 정의. ex) GEditStyle.Util.getSymbolizerLineString('#F26161', 'solid', 3, 1)
 * 수정일				수정자			수정내용
 * ----------------------------------------------------------------------
 * 2015.11.19			윤은희		최초 생성
 *
 **********************************************************************************/
GEditStyle.Util = {
		/**
		 * 포인트 타입 심볼라이저 obj 리턴
		 * */
		getSymbolizerPoint : function(_oGraphic, _nSize, _nOpacity){
			var oSymbolizer = {
				externalGraphic: 'data:image/png;base64'.concat(',', _oGraphic.replace(/\r?\n/g, '')),
				graphicWidth: parseInt(_nSize,10),
				graphicHeight: parseInt(_nSize,10),
				fillOpacity: parseFloat(_nOpacity).toFixed(2)
			  };

			return oSymbolizer;
		},
		/**
		 * 라인 타입 심볼라이저 obj 리턴
		 * */
		getSymbolizerLineString : function(_sColor, _sDashStyle, _nWidth, _nOpacity, _sLinecap, _nIndex){
			var oSymbolizer = {
				fillColor: _sColor,
				strokeColor: _sColor,
				strokeDashstyle : _sDashStyle,
				strokeWidth: parseInt(_nWidth,10),
				strokeOpacity: parseFloat(_nOpacity).toFixed(2),
				strokeLinecap: _sLinecap,
				graphicZIndex: parseInt(_nIndex,10)
			};

			return oSymbolizer;
		},

		/**
		 * 폴리곤 타입 심볼라이저 obj 리턴
		 * */
		getSymbolizerPolygon : function(_oPolygon){
			var oSymbolizer = {
				fillColor: _oPolygon.fillColor,
				fillOpacity: parseFloat(_oPolygon.fillOpacity).toFixed(2),
			};
			if(_oPolygon.strokeWidth) {
				$.extend(true,oSymbolizer,{
					strokeColor: _oPolygon.stroke,
					strokeDashstyle : _oPolygon.strokeDashstyle,
					strokeWidth: parseInt(_oPolygon.strokeWidth,10),
					strokeOpacity: parseFloat(_oPolygon.strokeOpacity).toFixed(2),
					strokeLinecap: _oPolygon.strokeLinecap
				});
			} else {
				oSymbolizer.stroke = false;
			}
			return oSymbolizer;
		},

		/**
		 * 텍스트 타입 심볼라이저 obj 리턴
		 * */
		getSymbolizerText : function(_sLabel, _sColor, _nSize, _sFontFamily, _sFontWeight,_sCodeDomain){
			var oSymbolizer = {
				label : _sLabel,
				fontColor: _sColor,
				fontSize: parseInt(_nSize,10),
				fontFamily: _sFontFamily,
				fontWeight: _sFontWeight,
				codeDomain: _sCodeDomain
			};

			return oSymbolizer;
		},

		/**
		 * 심볼 필터 obj 리턴
		 */
		getStyleFilter : function(_sFilterType, _sProperty, _sValue){
			var oFilter = {};

			if (_sFilterType !== OpenLayers.Filter.Comparison.BETWEEN) {
				oFilter = new OpenLayers.Filter.Comparison({
					type: _sFilterType,
					property: _sProperty,
					value: _sValue
				  });
			}

			return oFilter;
		},

		/**
		 * 기본 심볼 obj 리턴
		 */
		getObjectStyleMap : function(_oStyleMap){

			var oStyleMap = new OpenLayers.StyleMap({
				'default': new OpenLayers.Style(
						_oStyleMap
				)
			});

			return oStyleMap;
		},

		/**
		 * 룰을 포함한 심볼 obj 리턴
		 */
		getObjectStyleMapHasRules : function(_oStyleMap, _oRules){
			var aRuleArray = [];
			var aRules = _oRules.rules;

			for(var i in aRules){
				aRuleArray.push(new OpenLayers.Rule(aRules[i]));
			}

			var oStyleMap = new OpenLayers.StyleMap({
				'default': new OpenLayers.Style(
						_oStyleMap,
						{
							rules: aRuleArray
						})
			});

			return oStyleMap;
		},

		getSymbolizerPointShape : function(_oPoint) {
			var oSymbolizer = {
					graphicName : _oPoint.graphicName,
					fillColor : _oPoint.fillColor,
					fillOpacity : parseFloat(_oPoint.fillOpacity).toFixed(2),
					pointRadius : parseInt(_oPoint.size,10)/2,
				};
			if(_oPoint.strokeWidth) {
				$.extend(true,oSymbolizer,{
					strokeWidth :parseInt( _oPoint.strokeWidth,10),
					strokeColor : _oPoint.strokeColor,
					strokeOpacity : parseFloat(_oPoint.strokeOpacity).toFixed(2)
				});
			} else {
				oSymbolizer.stroke = false;
			}
			return oSymbolizer;
		},

		getSymbolizer : function(_oRule) {
			var oSymbolizer = {};

			if(_oRule.point) {
				var oPoint = _oRule.point;
				if(oPoint.externalGraphic) {
					$.extend(true,oSymbolizer,GEditStyle.Util.getSymbolizerPoint(oPoint.externalGraphic, oPoint.size, oPoint.opacity));
				} else {
					$.extend(true,oSymbolizer,GEditStyle.Util.getSymbolizerPointShape(oPoint));
				}
			} else if(_oRule.line) {
				var oLine = _oRule.line;
				$.extend(true,oSymbolizer,GEditStyle.Util.getSymbolizerLineString(oLine.stroke, oLine.strokeDashArray, oLine.strokeWidth, oLine.strokeOpacity, oLine.strokeLinecap, 1));
			} else if(_oRule.polygon) {
				var oPolygon = _oRule.polygon;
				$.extend(true,oSymbolizer,GEditStyle.Util.getSymbolizerPolygon(oPolygon));
			}

			return oSymbolizer;
		}

};