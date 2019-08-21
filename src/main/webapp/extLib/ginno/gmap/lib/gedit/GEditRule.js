/**********************************************************************************
 * 파일명 : GEditRule.js
 * 설 명 : 편집 Rule 
 * 필요 라이브러리 : JSTS
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2016.03.25			윤은희				1.0					최초 생성
 * 
 * 
**********************************************************************************/

var GEditRule = {

		/**
		 * Spatial Operation Type
		 */
		spatialOperType : {
			TOUCHES : 0,
			CONTAINS : 1,
			INTERSECTS : 2
		},
		
		/**
		 *  Rule 체크할 geometry
		 */		
		editingGeometry : {},
		
		/**
		 * 기본 offset 값
		 */
		offset : {x:0, y:0},
		
		/**
		 * Rule 체크 결과 geometry
		 */
		resultGeometry : {},
		
		/**
		 * Rule 체크 결과 Boolean 
		 */
		resultState : false,
		
		/**
		 * 허용 오차, 단위(m)
		 */
		tolerance : 0.01,
		
		
		/**
		 * vertices 간 거리
		 */
		dist: 0,

		/**********************************************************************************
		 * 함수명 : checkRelationGeometry
		 * 설 명 : 지오메트리와 관련 레이어의 기존 지오메트리와의 공간관계
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열), _sOperator(수행할 연산 - TOUCHES, CONTAINS, INTERSECTS)
		 * 반환값 : 
		 * 			-> 룰 성공 : 값이 존재하는 결과 객체 리턴 - 공간 연산 성립하는 Obj(레이어별-객체들) 리턴
		 * 			-> 룰 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴
		 * 사용법 : checkRelationGeometry(_oSourceFeature, _aCompLayer, _sOperator)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometry : function(_oSourceFeature, _aCompLayer, _sOperator){
			
			var oResultGeom = {};
			
			switch(_sOperator){			
				case 0 : 
					oResultGeom = this.checkRelationGeometryTouches(_oSourceFeature, _aCompLayer); 
					break; 
				case 1 : 
					oResultGeom = this.checkRelationGeometryContains(_oSourceFeature, _aCompLayer);
					break; 
				case 2 : 
					oResultGeom = this.checkRelationGeometryIntersects(_oSourceFeature, _aCompLayer);
					break;
			}

			return this.resultGeometry = oResultGeom;
		},
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryTouches
		 * 설 명 : 선형 지오메트리 상에 위치(연결)하는지 (LL)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : LINE 과 LINE 사이의 연결성은 Intersect연산으로 할 수 없고(Cross 객체도 true 이므로) 반드시 Touches 연산으로만 수행해야함
		 * 			2) 현 편집 객체가 대상 객체와 touch되어 있는지 체크하여 상태 리턴.
		 * 				A) 상수시설물 : 급수관로와 연결 확인, 배수관과 연결확인, 기존관로와 연결확인
		 * 				B) 하수시설물 : 하수관거-기존관로와연결확인, 하수연결관-기존하수관거에 인입
		 * 				C) 도로시설물 : 도로중심선-기존 도로중심선과 연결
		 * 				D) 기타 : 이외, 연산 대상 레이어가 배열(복수개 레이어)일 경우도 해당함.
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP 1. 편집을 수행하는(추가/수정) 객체 기준으로 룰 체크할 대상객체를 찾는다.
		 * 				STEP 1-1. JSTS 연산을 통해 대상 객체를 찾기 위해 편집 객체를 GeoJson 객체로 생성 및 Buffer연산 수행 후 Openlayers 객체로 변환한다. 
		 * 				STEP 1-2. 편집을 수행하는(추가/수정) 객체 기준으로 룰 체크할 대상객체를 찾는다.
		 * 			STEP 2. 찾은 대상 객체와 편집을 수행하는(추가/수정) 객체간의 거리를 측정하여 오차율 범위안에 존재하면 
		 * 						-> 룰 성공 : 값이 존재하는 결과 객체 리턴 - Touches 성립하는 Obj(레이어별-객체들) 리턴
		 * 						-> 룰 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴 
		 * 
		 * 인 자 	: _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열)
		 * 반환값 : 
		 * 			-> 룰 성공 : 값이 존재하는 결과 객체 리턴 - Touches 성립하는 Obj(레이어별-객체들) 리턴
		 * 			-> 룰 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴
		 * 사용법 : checkRelationGeometryTouches(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryTouches : function(_oSourceFeature, _aCompLayer){
			
			var oResultGeom = {};
			
			// STEP 1.
			// STEP 1-1.   
			var oInputGeom = GGeoJson.getGeoJson('LineString', _oSourceFeature.geometry.components);        
			var oBufferGeom = GGeomJSTSOper.calcGeomBuffer(oInputGeom, 1); // 1m
			var oCalcPolyGeom = GGeomJSTSOper.makeGeoJsonGeom(oBufferGeom);
			var oGInnerFeature = editor.makeFeatureByPosList('polygon', oCalcPolyGeom.coordinates[0], MAP_EDITOR.fn_get_fidByFeature(_oSourceFeature));

			// STEP 1-2.		
			var oGData = this.checkRelationGeometryIntersects(oGInnerFeature, _aCompLayer);
			if(oGData.data.length >0){

				// STEP 2.							
				var oInputGeom = GGeoJson.getGeoJson('LineString', _oSourceFeature.geometry.components);
				var oCompGeom = {};							

				// Distance 오차 범위안에(Touches 성립) 존재하는 각 레이어별 Feature를 찾아내서 최종 결과 oResultGeom에 담는다.
				for(var i=oGData.data.length-1; 0<=i; i--){		// Layer 별,

					var oResults = oGData.data[i].results;
					for(var j=oResults.length-1; 0<=j; j--){		// feature 별,

						oCompGeom = GGeoJson.getGeoJson('LineString', oResults[j].feature.geometry.components);    

						var nDistGeom = GGeomJSTSOper.calcGeomDistance(oInputGeom, oCompGeom);
						var bCrossesGeom =  GGeomJSTSOper.calcGeomCrosses(oInputGeom, oCompGeom);

						// nDistGeom이 0이면서 bCorssesGeom이 true인 경우가 있어서(=현 지도 축척에서 육안으로 확인 불가한 cross상태인 시설물들-상수관에 붙어있는 급수관), nDistGeom이 0이면 Touches성립으로 간주하도록 함.
						// 단, 동일 시설물들끼리는 이 조건에서 제외시킴. 비교하는 시설물이 서로 다른 시설물일 경우, nDistGeom이 0이면 cross상태를 무시하도록 하여 Touche로 판단함.
						if(MAP_EDITOR.fn_get_tblNameByFeature(_oSourceFeature) !== MAP_EDITOR.fn_get_tblNameByFeature(oResults[j].feature))
							if(nDistGeom === 0) 
								continue;

						if(nDistGeom > GEditRule.tolerance || bCrossesGeom){		// 허용오차범위를 벗어나거나 Crosses된 객체면 해당 feature는 결과 Obj에서 제거함.
							oResults.splice(j,1);
							if(oResults.length === 0)
								oGData.data.splice(i,1);
						}
					}
				}

				if(oGData.data.length === 0)
					oGData = {};

				oResultGeom = oGData;  // oGData = 위의 for 수행이 끝난 최종 결과를 담고 있는 obj
			}
			
			return oResultGeom;	
		},
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryIntersects
		 * 설 명 : 점형(선형) 지오메트리와 선형(점형) 지오메트리와의 INTERSECTS 체크(Anything)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황(예) : 점형 지오메트리가 선형 지오메트리 상(위)에 위치하는지  
		 * 			2) 현 편집 객체가 대상 객체와 INTERSECTS되어 있는지 체크하여 상태 리턴.  예를 들어,  
		 * 				A) 상수시설물 : 
		 * 						- 상수관로와 연결확인 				: 가압장/스탠드파이프/변류시설/유량계/수압계/수원지/취수장/가압장/배수지/상수관로심도/누수지점 및 복구내역/신축관실 
		 * 						- 급수관로와 연결확인 				: 급수전계량기/소화전/급수탑/지수전
		 * 				B) 하수시설물 : 
		 * 						- 하수관거와 연결확인 				: 하수관거심도/하수맨홀/환기구/하수펌프장/유수지/역사이펀/토구/하수처리장/측구 
		 * 						- 하수관거(합류관)과 연결확인 	: 우수토실 
		 * 						- 하수관거(차집관거)와 연결확인 	: 하수처리장 
		 * 						- 하수연결관(말)						: 물받이
		 * 				C) 도로시설물 :
		 * 						- 도로중심선 						: 교차시설
		 * 
		 * 			[ 처리 단계 ]
		 * 			설명 생략		
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열)
		 * 반환값 : 
		 * 			-> 룰 성공 : 값이 존재하는 결과 객체 리턴 - Intersects 성립하는 Obj(레이어별-객체들) 리턴
		 * 			-> 룰 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴
		 * 사용법 : checkRelationGeometryIntersects(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryIntersects : function(_oSourceFeature, _aCompLayer){
	    	
			this.addResultOnGData = function(_oLayerFeature, _sCase, _oGDatasData){
				var sLayer = MAP_EDITOR.fn_get_tblNameByFeature(_oLayerFeature);
				var sFId = MAP_EDITOR.fn_get_fidByFeature(_oLayerFeature); 
				var sG2Id = MAP_EDITOR.fn_get_g2idByFeature(_oLayerFeature);

				var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(_oLayerFeature, sFId, '');
				// true = wfs vectorLayer, false = editLayer or styleLayer
				!_oLayerFeature.featureType ? oGFeature.fields = _oLayerFeature.attributes : oGFeature.fields = editor.editingFeatures[sLayer][sG2Id].properties;							

				if(_sCase === 'all'){
					var oGResult = MAP_EDITOR.fn_get_objFactory().Util.createGResult();
					oGResult.table = sLayer;
					oGResult.results.push(oGFeature);
					oGData.data.push(oGResult);
				}
				else if(_sCase === 'result')
					_oGDatasData.results.push(oGFeature);
			}

			var oResultGeom = {};
			var aCompLayer =  _aCompLayer;
			var aSearchingLayer = [], oSearchingLayer = {};
			var oGData = MAP_EDITOR.fn_get_objFactory().Util.createGData();			

			if(!(aCompLayer instanceof Array)){
				if(!aCompLayer)
					return oResultGeom;
				else if(typeof aCompLayer === 'string'){
					aCompLayer = [];
					aCompLayer.push(_aCompLayer);
				}									
			}

			for(var t=0, tLen=aCompLayer.length; t<tLen; t++){
				var oLayer = map.getLayerByName(aCompLayer[t]);
				if(oLayer)
					aSearchingLayer.push(oLayer);
			}
			aSearchingLayer.push(editor.styleLayer);
			aSearchingLayer.push(editor.editLayer);

			for(var j=0, jLen=aSearchingLayer.length; j<jLen; j++){
				oSearchingLayer = aSearchingLayer[j];
				for(var i=0, len=oSearchingLayer.features.length ; i<len; i++){
					var oLayerFeature = oSearchingLayer.features[i];

					if(MAP_EDITOR.fn_get_fidByFeature(_oSourceFeature) !== MAP_EDITOR.fn_get_fidByFeature(oLayerFeature)){
						var sLayer = MAP_EDITOR.fn_get_tblNameByFeature(oLayerFeature);
						var sFId = MAP_EDITOR.fn_get_fidByFeature(oLayerFeature);
						
						if($.inArray(sLayer, aCompLayer) > -1){
							if (!oLayerFeature.getVisibility())
								continue;

							if (_oSourceFeature.geometry.intersects(oLayerFeature.geometry)) {	
								var isExist = false;

								// intersects 결과 만들기
								if(sLayer !== ''){
									if(oGData.data.length === 0)
										this.addResultOnGData(oLayerFeature, 'all');
									else{
										for(var k=0, kLen=oGData.data.length; k<kLen; k++){
											var oGResultObj = oGData.data[k];
											if(oGResultObj.table === sLayer){
												for(var t=0, tLen=oGResultObj.results.length; t<tLen; t++){
													var oGFeatureObj = oGResultObj.results[t];
													if(oGFeatureObj.feature.attributes.fid === sFId){
														isExist = true;
														break;
													}
												}
												if(!isExist)
													this.addResultOnGData(oLayerFeature, 'result', oGResultObj);
											}
											else
												this.addResultOnGData(oLayerFeature, 'all');
										}
									}
								}
							}
						}
					}
				}
			}

			return oResultGeom = oGData;
		},
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryWithin
		 * 설 명 : 면형 지오메트리 상(위)에 위치하는지 (AP/AL/AA)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : POLYGON 과 POINT/LINE/POLYGON 과의 관계성 체크
		 * 			2) 현 편집 객체가 대상 객체와 Contain되어 있는지 체크하여 상태 리턴.
		 * 				A) 도로시설물 : 
		 * 					Case 1) 도로면 내에 위치 - 교통표지판/기타시설(점)/도로표지판/보안등/신호등/점용시설(점)/정류장/공동구/교통광장/기타시설(면)/점용시설(선)//자전거도로/절개면/성토면/점용시설(면)/주차장/지하보도/포장
		 * 					Case 2) 도로면(보도면) 내에 위치 - CCTV/가로수/자전거보관소
		 * 					Case 3) 도로면(차도면) 내에 위치 - 과속방지턱/미끄럼방지시설/중앙분리대/횡단보도 
		 * 
		 * 			[ 처리 단계 ]
		 * 			설명 생략		
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열)
		 * 사용법 : checkRelationGeometryIntersects(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryContains : function(_oSourceFeature, _aCompLayer){

			var oResultGeom = {};			
			
			GRequest.WFS.getFeatureByContains(
					CONFIG.fn_get_serviceUrl(), 
					{
						prefix : CONFIG.fn_get_dataHouseName(),
						tables : [_aCompLayer],						
						values : [_oSourceFeature.geometry]
					}, 
					function(_oRes) {
						
						if(_oRes.data.length > 0)
							oResultGeom = _oRes;
					},
					{
						alias : '',
						titles : ''
					},
					true
			);
			
			return oResultGeom;
		},



		/**********************************************************************************
		 * 함수명 : checkRelationGeometryEnd
		 * 설 명 : 점형 지오메트리와 관련 선형 지오메트리의 끝점과의 공간관계 (PL)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형 객체의 끝점과 연결되는 지
		 * 			2) 현 편집 객체가 대상 객체의 끝점과 touch되어 있는지 체크하여 결과 obj 리턴.
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP 1. 추가/수정 객체(point) 기준으로 연산대상시설물(ex.급수관) 찾기
		 * 				STEP 1-1. 기준 객체 생성 : 추가/수정한 객체(geometry)를 JTS 연산을 위한 GeoJson 형태의 객체로 변환
		 * 			STEP 2. 찾은 연산 대상 시설물(ex.급수관)의 양 끝점과 추가/수정 객체점과의 거리연산 -> 오차 범위안에 있는 점 선정 -> 없으면 룰 위배! -> empty obj return
		 * 			STEP 3. 찾은 연산 대상 시설물의 끝점 찾기 
		 * 				STEP 3-1. 찾은 점이 인접한 연관시설물(ex.상수관)이 있으면 끝점에 입력한게 아니므로 -> 룰 위배! -> empty obj return
		 * 				STEP 3-2. 찾은 점이 인접한 연관시설물(ex.상수관)이 없으면 끝점에 입력하였으므로 -> 룰 성공! -> 찾은 연산 대상 시설물(ex.급수관)의 끝점 obj return
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열), _aRefLayer(연산 대상 레이어의 연관 레이어)
		 * 반환값 : 
		 * 			-> 룰 성공 : 값이 존재하는 결과 객체 리턴 - 추가/수정 객체(point) 기준으로 찾은 연산 대상 시설물(ex.급수관)의 끝점 obj return
		 * 			-> 룰 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴
		 * 사용법 : checkRelationGeometryEnd(_oSourceFeature, _aCompLayer, _aRefLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryEnd : function(_oSourceFeature, _aCompLayer, _aRefLayer){

			var oResultGeom = {};
			var oCompGeom1 = {}, oCompGeom2 = {};
			var oInputGeomClone = MAP_EDITOR.fn_clone_featureToGInnerFeature(_oSourceFeature);

			// Step 1.
			// Step 1-1.			
			var oInputGeom = GGeoJson.getGeoJson('Point', _oSourceFeature.geometry);
			var oBufferGeom = GGeomJSTSOper.calcGeomBuffer(oInputGeom, 0.5); // 50cm (인접 객체를 찾기 위한 수치: 실제로는 떨어져 있는 시설물이지만 웹 편집시스템의 제한 축척(최대 1:708)으로 인해 육안으로는 붙어있는것으로 보이는 거리이므로 50cm범위안에서 찾음)
			var oCalcPolyGeom = GGeomJSTSOper.makeGeoJsonGeom(oBufferGeom);
			var oGInnerFeature = editor.makeFeatureByPosList('Polygon', oCalcPolyGeom.coordinates[0], MAP_EDITOR.fn_get_fidByFeature(_oSourceFeature));
			
			var oGData = this.checkRelationGeometryIntersects(oGInnerFeature, _aCompLayer);			
			if(oGData.data.length >0){
				// STEP 2.
				var oResults = oGData.data[0].results;
				for(var i=0, len=oResults.length;  i<len; i++){ // feature별(비교 객체)
					var oPoint1 = oResults[i].feature.geometry.components[0];
					oCompGeom1 = GGeoJson.getGeoJson('Point', oPoint1);
					var oPoint2 = oResults[i].feature.geometry.components[oResults[i].feature.geometry.components.length-1];   
					oCompGeom2 = GGeoJson.getGeoJson('Point', oPoint2); 					

					var nDist1 = GGeomJSTSOper.calcGeomDistance(oInputGeom, oCompGeom1);
					var nDist2 = GGeomJSTSOper.calcGeomDistance(oInputGeom, oCompGeom2);
					var nDist;
					var oEndPointGeom = {};

					if(nDist1 < nDist2){
						nDist = nDist1;
						oEndPointGeom = oPoint1;	        							
					}
					else{
						nDist = nDist2;
						oEndPointGeom = oPoint2;
					}

					if(nDist < GEditRule.tolerance){
						// STEP 3.
						// STEP 3-1. & 3-2.
						var oSearchGeom = GGeoJson.getGeoJson('Point', oEndPointGeom);
						var oBufferGeom = GGeomJSTSOper.calcGeomBuffer(oSearchGeom, 0.5);
						var oCalcPolyGeom = GGeomJSTSOper.makeGeoJsonGeom(oBufferGeom);
						var oGInnerFeature = editor.makeFeatureByPosList('Polygon', oCalcPolyGeom.coordinates[0], MAP_EDITOR.fn_get_fidByFeature(oResults[i].feature));
						
						var oGData2 = this.checkRelationGeometryIntersects(oGInnerFeature, _aRefLayer);						
						if(oGData2.data.length === 0){ // 끝점으로 확인되어 결과 obj 에 담음.	 입력점이 끝점으로 판정되면 대부분 입력점(_oSourceFeature)과 동일하나 혹 오차로 인해 다를 경우를 대비해 아래와 같이 처리함. 												

							oInputGeomClone.geometry =  oEndPointGeom;

							var oGResult = MAP_EDITOR.fn_get_objFactory().Util.createGResult('');
							var oGFeature = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(oInputGeomClone, 'TEMP_LAYER.0', '');  

							oGResult.results.push(oGFeature);
							oGData2.data.push(oGResult);
							oResultGeom = oGData2;
						}
					}
				}
			}

			return this.resultGeometry = oResultGeom;
		},
		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationAddPointOnGeometryEnd
		 * 설 명 : 선형 지오메트리의 끝점 찾기
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형 객체의 끝점(관말)에 점형시설추가 - ex) 급수관 추가시 관말에 계량기 자동 생성.
		 * 			2) 현 편집 객체(Line)의 끝점 Point obj 리턴.
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP 1. 입력 객체(Line)의 양 끝점을 가져와서 연산 대상 시설물(ex.상수관)과 인접하지 않는 끝점이 입력 객체의 실제 끝점(관말)임. 해당 점을 찾음.
		 * 			STEP 2. 연산 대상 시설물과 떨어져 있는 시설물일 경우(메인 관에 붙어 있어야 하는데 떨어져 있는 잘못된 관들의 경우에 해당함) 입력 객체의 순서상 끝점을 끝점(관말)으로 지정함.
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어)
		 * 반환값 : 끝점 Point Object 
		 * 			-> 성공 : 값이 존재하는 결과 객체 리턴 - 끝점 Ponit Object
		 * 			-> 실패 : 값이 존재하지 않는 결과 객체 리턴 - Empty obj 리턴
		 * 사용법 : checkRelationAddPointOnGeometryEnd(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.05.02			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationAddPointOnGeometryEnd : function(_oSourceFeature, _aCompLayer){
			
			var oResultGeom = {};
			var oEndPointGeom = {};
			var nGeomLength = _oSourceFeature.geometry.components.length;
			var nChkCnt = 0;
			
			// STEP 1.
			for(var i=0; i<2; i++){
				i === 0 ? oEndPointGeom = _oSourceFeature.geometry.components[i] : oEndPointGeom = _oSourceFeature.geometry.components[nGeomLength-1];
				
				var oInputGeom = GGeoJson.getGeoJson('Point', oEndPointGeom);
				var oBufferGeom = GGeomJSTSOper.calcGeomBuffer(oInputGeom, 0.5); // 50cm (인접 객체를 찾음. 웹 편집시스템의 제한 축척(최대 1:708)으로 인해 떨어져 있는 시설물이지만 육안으로는 확인되지 않는 거리이므로.)
				var oCalcPolyGeom = GGeomJSTSOper.makeGeoJsonGeom(oBufferGeom);
				var oGInnerFeature = editor.makeFeatureByPosList('Polygon', oCalcPolyGeom.coordinates[0], MAP_EDITOR.fn_get_fidByFeature(_oSourceFeature));

				
				var oGData = this.checkRelationGeometryIntersects(oGInnerFeature, _aCompLayer);
				if(oGData.data.length === 0){	
					oResultGeom = oEndPointGeom;		// 끝점으로 확인되어 결과 obj 에 담음.
					nChkCnt++;
				}
			}
			
			// STEP 2.
			if(nChkCnt === 2) // 연관시설물과 떨어져 있는 시설물일 경우(메인 관에 붙어 있어야 하는데 떨어져 있는 잘못된 관들의 경우에 해당함)
				oResultGeom = _oSourceFeature.geometry.components[nGeomLength-1];			

			return this.resultGeometry = oResultGeom;
		},

		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryMove
		 * 설 명 : 선형 지오메트리상의(위에 존재하는 혹은, 연결된) 지오메트리(L,P) 동시 이동
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형시설물(상수관로,급수관로,하수관거,하수연결관) 이동시 연결된(혹은, 위에 존재하는) 선/점형 시설물 동시이동
		 * 				A) 상수시설물 : 상수관로 - 상수관로 위에 존재하는 시설물, 연결된 급수관, 급수관로에 연결된 시설물(관말시설)
		 * 								   급수관로 - 급수관로에 연결된 시설물(관말시설)
		 * 				B) 하수시설물 : 하수관거 - 하수관거 위에 존재하는 시설물, 연결된 하수연결관, 하수연결관에 연결된 시설물(관말시설)
		 * 								   하수연결관 - 하수연결관에 연결된 시설물(관말시설)
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP1. 현 편집(수정)객체 이동 및 그 '위'에 존재하는 객체 이동
		 * 				STEP1-1. 현 편집(수정)객체 이동 : 현 편집(수정) 객체는 이동 후 '중간저장 갱신'시켜야 함. - "정점편집 or 개체이동" 기능을 통해 편집 중인 객체들은 수행하지 않음(각 custom class에서 자기 자신을 이동시킴)
		 * 						   현 현 편집(수정)객체 위치이동 후 속성 중 공간정보를 자동으로 변경
		 * 				STEP1-2. 현 편집(수정)객체와의 연관 객체 찾기 - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP1-3. STEP1-2에서 찾은 연관 객체 이동
		 * 				
		 * 			STEP2. 현 편집(수정)객체와 연결된 객체 이동		
		 * 				STEP2-1. 현 편집(수정)객체와 연결된 객체 찾기 - TOUCHES 연산수행
		 * 				STEP2-2. STEP2-1에서 찾은 객체와 연관 객체 찾기(관말객체) - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP2-3. STEP2-2-1에서 찾은 객체 이동(관말 객체)
		 * 				STEP2-4. 현 편집(수정)객체와 연결된 객체 이동
		 * 
		 * 인 자 : _oSourceObj(연산할 기준 Feature를 담고 있는 Obj : oRes 혹은 Feature 둘 중 하나임), _oOffset(이동 수치)
		 * 사용법 : checkRelationGeometryMove(_oSourceObj, _oOffset)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryMove : function(_oSourceObj, _oOffset){			
			var oSourceFeature = {};								// 인자로 넘어온 객체에서(_oSourceObj) 연산할 기준 Feature를 담기 위한 용도.
			var sSourceFeatureLayer='', sSourceFeatureG2Id='', sSourceFeatureType='', aCompMainLayer=[], sCompLayer='', aCompSubLayer=[], oOriginSourceRes={};
			var oTouchesGeom = {}, oIntersectsGeom = {};
		
			if(_oSourceObj.data instanceof Array){				// _oSourceObj = 결과Obj 일 경우(ex.시설물 검색결과-oRes) - 좌표이동 전 객체
				oOriginSourceRes = COMMON.deepCloneObject(_oSourceObj);
				
				sSourceFeatureType = 'gResObject';
				sSourceFeatureLayer = _oSourceObj.data[0].table;
				sSourceFeatureG2Id = _oSourceObj.data[0].results[0].g2id;
				oSourceFeature = _oSourceObj.data[0].results[0].feature;	
			}
			else{														// _oSourceObj = Feature일 경우(ex.정점편집/개체이동을 통해 넘어온 객체)
				oSourceFeature = _oSourceObj;
				
				sSourceFeatureType = 'oGInnerFeature';
				sSourceFeatureLayer = MAP_EDITOR.fn_get_tblNameByFeature(oSourceFeature);
				sSourceFeatureG2Id = MAP_EDITOR.fn_get_g2idByFeature(oSourceFeature);				

				if(oSourceFeature.modified.geometry){
					oSourceFeature.layer.editingFeatureGeometry = oSourceFeature.geometry.clone();
					oSourceFeature.geometry = oSourceFeature.modified.geometry; // modified에는 original geometry정보가 담겨있음('정점편집/개체이동'을 통한 좌표이동 전 geometry)
				}
			}

			if(EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer instanceof Object){
				var oRelatedLayer = EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer;
				if(oRelatedLayer.length === undefined){
					sCompLayer = oRelatedLayer.mainlayer.toString();
					aCompSubLayer = oRelatedLayer.onlayer;	
				}				
			}
			aCompMainLayer = EditRuleRelatedLayer[sSourceFeatureLayer].onlayer;
			
			/*var oSourceFeatureClone = oSourceFeature.clone(); 			// 좌표값 이동전 원본(original) feature
			oSourceFeatureClone = MAP_EDITOR.fn_deepClone_featureToGInnerFeature(oSourceFeatureClone);*/
			var oSourceFeatureClone = MAP_EDITOR.fn_deepClone_featureToGInnerFeature(oSourceFeature); // 좌표값 이동전 원본(original) feature
			

			// STEP1.
			// STEP1-1.	
			if(sSourceFeatureType === 'gResObject'){
				// _oOffset만큼 이동 및 중간저장
				this.checkRelationGeometryMoveToByOffset(oOriginSourceRes, _oOffset, true);

				// 중간저장 정보 갱신 - 윗 라인 수행으로 oSourceFeature 위치정보가 변경되었으므로 갱신함 : 공간정보 clone이 아니기에 oOriginSourceRes과 oSourceFeature는 같은 공간을 참조하고 있음.
				MAP_EDITOR.fn_check_SpatialValueChange(oSourceFeature, sSourceFeatureLayer, sSourceFeatureG2Id);	
				MAP_EDITOR.fn_call_saveMiddleBridge(sSourceFeatureLayer, sSourceFeatureG2Id);				
	        	this.checkRelationLocFldValue(oSourceFeature);	// 편집하는 feature의 위치 속성 자동 갱신(행정동/법정동/도엽번호)
			
				var sFId = sSourceFeatureLayer.concat('.',sSourceFeatureG2Id);

				// 변경된 위치 기준으로 다시 그리기 - editlayer, stylelayer, effectlayer
				editor.editLayer.removeAllFeatures();
				editor.addDrawFeature(editor.editLayer, oSourceFeature, 'select');

				var oCurStyleFeature = editor.styleLayer.getFeaturesByAttribute('fid', sFId);
				editor.styleLayer.destroyFeatures(oCurStyleFeature, {silent: true});
				var oGInnerNewStyleFeature = editor.createFeature(oSourceFeature, sFId);
				editor.addDrawFeature(editor.styleLayer, oGInnerNewStyleFeature, sSourceFeatureLayer);
				
				if(editor.getGeometryType(oSourceFeature) !== 'point')
					MAP_EDITOR.fn_draw_oneFeatureBorder(oSourceFeature);
			}
			
			// STEP1-2.
			oIntersectsGeom = this.checkRelationGeometry(oSourceFeatureClone, aCompMainLayer, GEditRule.spatialOperType.INTERSECTS);
			if(COMMON.isEmptyObject(oIntersectsGeom) === false){ //존재하면
				if(oIntersectsGeom.data.length > 0){

					var aRelateLayer = [];
					var sRelateLayer = '';
					for(var t=0,tLen=oIntersectsGeom.data.length; t<tLen; t++){
						aRelateLayer.push(COMMON.fn_get_EditKorLayerNm(oIntersectsGeom.data[t].table));
					}
					sRelateLayer = aRelateLayer.join(',');

					// STEP1-3.
					if (confirm(COMMON.fn_get_EditKorLayerNm(sSourceFeatureLayer)+' 위에 [' + sRelateLayer + ']  존재하는데 같이 이동하시겠습니까?'))    // 복수 시설물 존재함.
						this.checkRelationGeometryMoveToByOffset(oIntersectsGeom, _oOffset, false, oSourceFeature);
				}
			}

			// STEP2.
			// STEP2-1.
			if(sCompLayer !== '')
				oTouchesGeom = this.checkRelationGeometry(oSourceFeatureClone, sCompLayer, GEditRule.spatialOperType.TOUCHES);

			// STEP2-2.
			if(COMMON.isEmptyObject(oTouchesGeom) === false){
				if(oTouchesGeom.data.length > 0){

					var nTouchesGeomCnt = oTouchesGeom.data[0].results.length;
					if (confirm('연결된 [' + COMMON.fn_get_EditKorLayerNm(sCompLayer) + '] ' + nTouchesGeomCnt + '개 존재하는데 같이 이동하시겠습니까?')){

						for(var k=0,kLen=nTouchesGeomCnt; k<kLen; k++){	// 단일 시설물이며, Feature 별로,
							// 연산 대상을 찾아야 하므로 JSTS 연산을 사용할 수 없음.
							oIntersectsGeom = this.checkRelationGeometry(oTouchesGeom.data[0].results[k].feature, aCompSubLayer, GEditRule.spatialOperType.INTERSECTS);

							// STEP2-3.
							if(COMMON.isEmptyObject(oIntersectsGeom) === false) // 관말이 존재하면,
								this.checkRelationGeometryMoveToByOffset(oIntersectsGeom, _oOffset, false, oSourceFeature);
						}

						// STEP2-4.
						this.checkRelationGeometryMoveToByOffset(oTouchesGeom, _oOffset, false, oSourceFeature);
					}
				}								
			}
			
			// editor.oSearchResults를 위치이동이 끝난 위치로 공간을 갱신해야 하는 여부....
			// 1. [객체이동]을 통해 들어온 _oSourceObj는 GDragFeature 클래스에서 좌표이동된 위치로 변경갱신함.
			// 2. [시설물 검색]을 통해 들어온 _oSourceObj는 checkRelationGeometryMoveToByOffset()를 통해 offset 만큼 위치이동 된 위치로 좌표가 갱신되어 있으므로 별도 작업 필요하지 않음.
		},

		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryMoveToByOffset
		 * 설 명 : 레이어별 Feature이동 - Offset 만큼 이동시킨 후 편집 모니터 등록.
		 * 
		 * 			[ 처리 단계 ]
		 * 			레이어별 Feature를 이동 시킨 후 WFS Layer의 Filter를 업데이트 시킨 후, 중간저장 DB에 저장시킴.
		 * 			중간저장 단계 : 중간저장 DB Insert -> 정상 insert 개체에 대해 WFS Layer에서 삭제-> Style Layer에 추가 -> 편집모니터 등록
		 * 			중간저장 시킨 객체에 대해 위치속성을 갱신시킴 : 갱신시킬 위치 정보는 편집 기준 객체의 위치임.
		 * 
		 * 인 자 : _oRes(이동시킬 레이어 Object), _oOffset(이동시킬 OffSet수치), _bAdd(editor.editLayer에 추가시킬지 여부, true=추가, false=추가시키지 않음)
		 * 			_oSourceFeature(기준객체 : 이동시킨 객체 속성 갱신 및 ) 
		 * 반환값 : 인수로 받았던 '이동시킬 레이어 Object'를 Offset만큼 이동된 결과 Object
		 * 사용법 : checkRelationGeometryMoveToByOffset(_oRes, _oOffset, _bAdd)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryMoveToByOffset : function(_oRes, _oOffset, _bAdd, _oSourceFeature){
			
			/*this.addPoint = function(_oGInnerFeature, _nStartIdx, _nEndIdx, _nDist, _nAddIdx){
				var sx, sy, nx, ny, angle;				
				var objResult = {};
				var posList = [];
				
				sx = _oGInnerFeature.geometry.components[_nStartIdx].x;			//시작점 X좌표
				sy = _oGInnerFeature.geometry.components[_nStartIdx].y;			//시작점 Y좌표
				nx = _oGInnerFeature.geometry.components[_nEndIdx].x;			//다음점 X좌표
				ny = _oGInnerFeature.geometry.components[_nEndIdx].y;			//다음점 Y좌표

				angle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));

				if(_nAddIdx === 1){	// 선분 중앙에 신규 point 추가
					var gapNextPoint = COMMON.fn_get_DistanceBy2Point(sx, sy, nx, ny); //현재지점과 다음지점과의 거리					
					_nDist = gapNextPoint/2;
				}
				
				objResult.x = sx + _nDist * Math.cos(angle);
				objResult.y = sy + _nDist * Math.sin(angle);					
				posList.push(objResult);
				var tmpGeometry = editor.getGeometryByPoint(posList);
				_oGInnerFeature.geometry.addComponent(tmpGeometry, _nAddIdx);		
			};*/
			
			
			var oResultGeom = {};
			var oCurLayer = {}, oCurGFeature = {};
			
			// _oRes가 복수개 레이어를 담고 있을경우, 
			// 레이어단위별로 가져올 때 _oRes.data[i]로 값을 읽어오게되므로 oGData에 값을 담기 위해서는 다시 data 속성을 생성해줘야 함.
			var oGData = MAP_EDITOR.fn_get_objFactory().Util.createGData();
		
			if(_oSourceFeature &&  _oSourceFeature.modified && _oSourceFeature.modified.control){ 	// '정점편집/객체이동'을 통해 편집된 객체의 geometry
				if(COMMON.isEmptyObject(_oSourceFeature.layer.editingFeatureGeometry) === false)		// 위치이동이 끝난 geomtry
					_oSourceFeature.geometry = _oSourceFeature.layer.editingFeatureGeometry;
			}
			
			for(var i=0,len=_oRes.data.length; i<len; i++){ // 레이어 별로,				
				oCurLayer = _oRes.data[i];				
				for(var j=0, jLen=oCurLayer.results.length; j<jLen; j++){	 // Feature 별로,								
					oCurGFeature = oCurLayer.results[j];
					/*
					// Modified Action을 통해 넘어온 경우, offset 계산하기( geom type 체크 : 점이면 선으로 변환 -> 선은 연장선을 만들어서 modified 지점과 교차점 찾기)	
					if(_oSourceFeature &&  _oSourceFeature.modified && _oSourceFeature.modified.control){ // '정점편집'을 통해 편집된 객체의 geometry
						var oEditingGeom = _oSourceFeature.geometry;								// '정점편집' 통해 위치 이동된 geometry
						var oOriginGeom = _oSourceFeature.modified.geometry;						// '정점편집' 통해 위치 이동된 geometry의 편집 전 geomtry
						var oCurGInnerFeatureClone = oCurGFeature.feature.clone();				// '정점편집' 통해 위치 이동된 geometry에 따라 같이 이동시킬 연관 시설 객체
						
						if(editor.getGeometryType(oCurGInnerFeatureClone) === 'point'){
							;
						}
						else if(editor.getGeometryType(oCurGInnerFeatureClone) === 'linestring'){
							// 2점만 있는 선분일 경우, 3점으로 분할
							if(oCurGInnerFeatureClone.geometry.components.length === 2)
								this.addPoint(oCurGInnerFeatureClone, 0, 1, 0, 1);
							
							// 시작점과 끝점 각각으로부터 연장 점 생성하여 추가함.
							var moveDist = this.dist;	 															// 이동 거리. GModifyFeature에서 정점편집 vertex의 이동 거리						
							var len = oCurGInnerFeatureClone.geometry.components.length;
							this.addPoint(oCurGInnerFeatureClone, 0, 1, moveDist+10, 0);					// 시작점에 추가
							this.addPoint(oCurGInnerFeatureClone, len-1, len-2, moveDist+10, len-1);		// 끝점에 추가
						}
					}*/
					
					
					this.geometryTranslate(oCurGFeature.feature.geometry, _oOffset);	// Feature의 geometry vertex 이동 - offset 만큼,
					MAP_EDITOR.fn_update_filterOnWFSLayer(oCurLayer.table, oCurGFeature.g2id);
				}
				
				// 레이어 단위로 중간 저장 DB에 Insert
				oGData.data.push(oCurLayer);
				
				if(_bAdd)
					MAP_EDITOR.fn_save_middleAll(oGData);							// editor.editLayer에 추가함.					
				else
					MAP_EDITOR.fn_save_middleAll(oGData, 'excludeEditLayer');		// editor.editLayer에 추가하지 않음.
				
				// editor.editingFeatures에 등록이 되어 있어야 하므로 fn_save_middleAll() 수행 후 실행함.
				if(_oSourceFeature){
					for(var k=0, kLen=oCurLayer.results.length; k<kLen; k++){	 // Feature 별로,	 편집개체(_oSourceFeature)의 공간속성값으로 연관된 oCurFeature를 모두 갱신.
						var oCurFeature = oCurLayer.results[k].feature;
						
						// 편집하는 feature 및 이와 연관된 feature의 위치 속성 자동 갱신(행정동/법정동/도엽번호)
			        	this.checkRelationLocFldValue(_oSourceFeature, oCurFeature.attributes.fid);
					}
				}
			}
			
			oResultGeom = _oRes;
			return this.resultGeometry = oResultGeom;
		},
		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryMoveEndPoint
		 * 설 명 : 선형 지오메트리 끝점에 존재하는 점형 지오메트리 이동시 선형 지오메트리 끝점 동시 이동(PL)
		 * 			혹은, 선형 지오메트리 이동시 선형 지오메트리 끝점에 존재하는 점형 지오메트리 동시 이동(LP)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 점형시설물(급수전계량기)이동시 연결된 선형시설물(급수관로) 끝점 동시이동 혹은, 선형시설물(급수관로) 끝점 이동시 연결된 점형시설물(급수전계량기) 동시 이동
		 * 			2) 현 편집 객체가 대상 객체와의 INTERSECTS결과를 찾아서 현 편집 객체의 이동된 좌표점으로 찾은 대상 객체의 끝점 변경하여 대상 객체(Object) 리턴. 
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP 1. 편집 객체 기준으로 연산대상객체 찾기 :  편집 객체의 이동전 좌표값으로 연산대상객체를 찾음.
		 * 
		 * 			Case 1 > PL 				
		 * 				STEP 2. 찾은 연산 대상 객체(ex.급수관)의 끝점 찾기 : 양 끝점과 편집 객체(수정객체-좌표 이동전 point)와의 거리연산 -> 오차 범위안에 있는 점 선정(jsts distance)
		 * 				STEP 3. 찾은 연산 대상 객체의 끝점을 편집 객체(수정객체-좌표 이동 후 point)의 좌표점으로 변경
		 * 
		 * 		 	Case 2 > LP 				
		 * 				STEP 2. 찾은 연산 대상 객체(ex.급수전계량기) 점과 편집 객체 양끝점(수정객체-좌표 이동전 양 끝 point)과 거리계산 -> 오차 범위안에 있는 점 선정(jsts distance)
		 * 				STEP 3. 찾은 연산 대상 객체의 좌표점을 편집 객체(수정객체-좌표 이동 후 point)의 Step2 에서 찾은 좌표점으로 변경  
		 * 
		 * 			STEP 4. (끝점이) 변경된 연산 대상 객체를 WFS Filter 갱신 -> 편집 객체로 전환(편집모니터 등록 등) -> 중간저장 혹은 중간저장 갱신
		 * 			STEP 5. 편집하는 feature 및 이와 연관된 feature의 위치 속성 자동 갱신(행정동/법정동/도엽번호)
		 * 			STEP 6. 반환값 생성 : (끝점이) 변경된 연산 대상 객체 반환 
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어)
		 * 반환값 : 성공시 -> (끝점이) 변경된 연산 대상 객체(oGData) 반환, 실패시 -> Feature가 없는 빈 oGData 반환
		 * 사용법 : checkRelationGeometryMoveEndPoint(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryMoveEndPoint : function(_oSourceFeature, _aCompLayer){
			var oResultGeom = {};
			var oRelatedResult = {};
			
			// STEP 1.
			var oInputGeomClone = MAP_EDITOR.fn_clone_featureToGInnerFeature(_oSourceFeature);			
			var oSourceFeatureOriginGeom = _oSourceFeature.modified.geometry;
			oInputGeomClone.geometry = oSourceFeatureOriginGeom;
			
			var oCalcResGeom = this.checkRelationGeometry(oInputGeomClone, _aCompLayer, GEditRule.spatialOperType.INTERSECTS);
			var sCompLayer = _aCompLayer[0];

			// STEP 2.
			if(oCalcResGeom.data.length >0){	// ex. 찾은 급수관이 있으면 or 급수전 계량기가 있으면,

				oRelatedResult = oCalcResGeom.data[0].results[0];
				oRelatedResult.feature.attributes.fid=sCompLayer.concat('.',oRelatedResult.g2id);
				
				var sSourceType = _oSourceFeature.geometry.CLASS_NAME.replace('OpenLayers.Geometry.','');
				var oRelatedFeature = oRelatedResult.feature;

				if(sSourceType === 'Point'){
					var oRelatedFeatureComponents = oRelatedFeature.geometry.components;
					var oInputOriginGeomP = GGeoJson.getGeoJson('Point', oSourceFeatureOriginGeom);
					var oCompGeomP1 = GGeoJson.getGeoJson('Point', oRelatedFeatureComponents[0]);
					var oCompGeomP2 = GGeoJson.getGeoJson('Point', oRelatedFeatureComponents[oRelatedFeatureComponents.length-1]);

					//  STEP 2. ~ STEP 3.
					if(GGeomJSTSOper.calcGeomDistance(oCompGeomP1, oInputOriginGeomP) < this.tolerance){		                			 
						oRelatedFeatureComponents[0] = _oSourceFeature.geometry;
					}		                			 
					else if(GGeomJSTSOper.calcGeomDistance(oCompGeomP2, oInputOriginGeomP) < this.tolerance){
						oRelatedFeatureComponents[oRelatedFeatureComponents.length-1] = _oSourceFeature.geometry;
					}
				}
				else if(sSourceType === 'LineString'){
					var oInputOriginGeomP1 = GGeoJson.getGeoJson('Point', oSourceFeatureOriginGeom.components[0]);
					var oInputOriginGeomP2 = GGeoJson.getGeoJson('Point', oSourceFeatureOriginGeom.components[oSourceFeatureOriginGeom.components.length-1]);
					var oCompGeomP = GGeoJson.getGeoJson('Point', oRelatedFeature.geometry);					

					//  STEP 2. ~ STEP 3.
					if(GGeomJSTSOper.calcGeomDistance(oCompGeomP, oInputOriginGeomP1) < this.tolerance){
						oRelatedFeature.geometry = _oSourceFeature.geometry.components[0];
					}		                			 
					else if(GGeomJSTSOper.calcGeomDistance(oCompGeomP, oInputOriginGeomP2) < this.tolerance){
						oRelatedFeature.geometry = _oSourceFeature.geometry.components[_oSourceFeature.geometry.components.length-1];
					}
				}				

				// STEP 4.
				var sFId = MAP_EDITOR.fn_get_fidByFeature(oRelatedFeature);
				var sG2Id = oCalcResGeom.data[0].results[0].g2id;
								
				if(editor.editingFeatures[sCompLayer] && editor.editingFeatures[sCompLayer][sG2Id]){		// 중간 저장 갱신					
					MAP_EDITOR.fn_check_SpatialValueChange(oRelatedFeature, sCompLayer, sG2Id);
					MAP_EDITOR.fn_call_saveMiddleBridge(sCompLayer, sG2Id);
					
					var oStyleFeature = editor.getFeatureByFid(editor.styleLayer, sFId);
			    	if(oStyleFeature){
			    		editor.styleLayer.destroyFeatures(oStyleFeature, {silent: true}); 
		    			var oStyleGFeature = editor.createFeature(oRelatedFeature, sFId);
		            	editor.addDrawFeature(editor.styleLayer, oStyleGFeature, sCompLayer);
			    	}
				}
				else{																			// 신규 중간 저장
					MAP_EDITOR.fn_update_filterOnWFSLayer(sCompLayer, sG2Id);
					MAP_EDITOR.fn_start_editFeature(oCalcResGeom, sG2Id, sCompLayer, true);
					MAP_EDITOR.fn_save_middle('insert', sCompLayer, sG2Id);	
				}
				
				// STEP 5.
	        	this.checkRelationLocFldValue(oRelatedFeature, sFId);
			}
			
			// STEP 6.	
        	var oGData = MAP_EDITOR.fn_get_objFactory().Util.createGData();
        	var oGResult = MAP_EDITOR.fn_get_objFactory().Util.createGResult('');
			oGResult.results.push(oRelatedResult);
			oGData.data.push(oGResult);
			oResultGeom = oGData;
			
			return this.resultGeometry = oResultGeom;
		},


		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryDelete
		 * 설 명 : 선형 지오메트리상에 존재 혹은 연결된 연관 지오메트리 삭제 확인(LL, LP)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형 데이타 상 시설물 삭제
		 * 			2) 현 편집 객체상에 존재 혹은 연결된 객체 삭제 수행. 반환값 없음.
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP1. 현 편집(수정)객체위에 존재하는 객체 삭제
		 * 				STEP1-1. 현 편집(수정)객체와의 연관 객체 찾기 - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP1-2. STEP1-1에서 찾은 연관 객체 삭제
		 * 			STEP2. 현 편집(수정)객체와 연결된 객체 삭제		
		 * 				STEP2-1. 현 편집(수정)객체와 연결된 객체 찾기 - TOUCHES 연산수행
		 * 				STEP2-2. STEP2-1에서 찾은 객체와 연관 객체 찾기(관말객체) - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP2-3. STEP2-2-1에서 찾은 객체 삭제(관말 객체)
		 * 				STEP2-4. 현 편집(수정)객체와 연결된 객체 삭제
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체 : Feature임 - attributes, geometry, layer등을 보유하고 있음)
		 * 반환값 : this.resultState 값(Boolean)
		 * 사용법 : checkRelationGeometryDelete(_oSourceFeature)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryDelete : function(_oSourceFeature){

			var sSourceFeatureLayer= '';
			var aCompMainLayer = [], sCompLayer = '', aCompSubLayer = [];
			var oTouchesGeom = {}, oIntersectsGeom = {};
															
			sSourceFeatureLayer = MAP_EDITOR.fn_get_tblNameByFeature(_oSourceFeature);	
			
			if(EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer instanceof Object){
				var oRelatedLayer = EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer;
				if(oRelatedLayer.length === undefined){
					sCompLayer = oRelatedLayer.mainlayer.toString();
					aCompSubLayer = oRelatedLayer.onlayer;	
				}				
			}
			aCompMainLayer = EditRuleRelatedLayer[sSourceFeatureLayer].onlayer;

				
			var oSourceFeatureClone = MAP_EDITOR.fn_clone_featureToGInnerFeature(_oSourceFeature);	// 원본 Feature
			
			// STEP1.
			// STEP1-1.
			oIntersectsGeom = this.checkRelationGeometry(_oSourceFeature, aCompMainLayer, GEditRule.spatialOperType.INTERSECTS);
			if(COMMON.isEmptyObject(oIntersectsGeom) === false){ //존재하면
				if(oIntersectsGeom.data.length > 0){
					
					var aRelateLayer = [];
					var sRelateLayer = '';
					for(var t=0,tLen=oIntersectsGeom.data.length; t<tLen; t++){
						aRelateLayer.push(COMMON.fn_get_EditKorLayerNm(oIntersectsGeom.data[t].table));
					}
					sRelateLayer = aRelateLayer.join(',');
					
					// STEP1-2.
					if (confirm(COMMON.fn_get_EditKorLayerNm(sSourceFeatureLayer)+' 위에 [' + sRelateLayer + ']  존재하는데 같이 삭제하시겠습니까?')){    // 복수 시설물 존재함.		
						
						this.deleteGeometry(oIntersectsGeom, false);
					}
				}				
			}		
			
			// STEP2. 
			// STEP2-1.
			if(sCompLayer !== '')
				oTouchesGeom = this.checkRelationGeometry(oSourceFeatureClone, sCompLayer, GEditRule.spatialOperType.TOUCHES);

			// STEP2-2.
			if(COMMON.isEmptyObject(oTouchesGeom) === false){
				if(oTouchesGeom.data.length > 0){
					
					var nTouchesGeomCnt = oTouchesGeom.data[0].results.length;
					if (confirm('연결된 [' + COMMON.fn_get_EditKorLayerNm(sCompLayer) + '] ' + nTouchesGeomCnt + '개 존재하는데 같이 삭제하시겠습니까?')){

						for(var k=0,kLen=nTouchesGeomCnt; k<kLen; k++){	// 단일 시설물이며, Feature 별로,
							// 연산 대상을 찾아야 하므로 JSTS 연산을 사용할 수 없음.							
							oIntersectsGeom = this.checkRelationGeometry(oTouchesGeom.data[0].results[k].feature, aCompSubLayer, GEditRule.spatialOperType.INTERSECTS);

							// STEP2-3.
							if(COMMON.isEmptyObject(oIntersectsGeom) === false) // 관말이 존재하면,	
								this.deleteGeometry(oIntersectsGeom, false);															
						}
						
						// STEP2-4.
						this.deleteGeometry(oTouchesGeom, false);
					}
				}								
			}
			
			return this.resultState = true;
		},
		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryDoNotDelete
		 * 설 명 : 선형 지오메트리상에 존재하는 다른 지오메트리 삭제 여부 확인(PL,LL) : INTERSECTS
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형 데이타 상 시설물 삭제, 선형 데이타 상 시설물 삭제 불가
		 * 			2) 현 편집 객체가 대상 객체와 INTERSECTS되어 있는지 체크하여 결과(Boolean) 리턴.
		 * 
		 * 			[ 처리 단계 ]
		 * 			설명 생략
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _aCompLayer(연산 대상 레이어 배열)
		 * 사용법 : checkRelationGeometryDoNotDelete(_oSourceFeature, _aCompLayer)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryDoNotDelete : function(_oSourceFeature, _aCompLayer){

			var oChkResult = this.checkRelationGeometry(_oSourceFeature, _aCompLayer, GEditRule.spatialOperType.INTERSECTS);

			// 편집시설물 삭제 불가하도록 Rule 연산 결과 Return
			if(oChkResult.data.length >0)
				return this.resultState = false;
			else
				return this.resultState = true;
		},
		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationLocFldValue
		 * 설 명 : 지오메트리 위치 변경시 속성 중 공간정보를 자동으로 변경하며, 연결 지오메트리의 공간속성도 동일 정보로 변경처리
		 * 			연관된 geometry의 정보(fid)가 인자로 넘어오지 않을 경우, 편집하는 geometry의 위치 속성만 갱신함.
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 급수관로 공간편집 시 연결된 급수전계량기 공간 속성정보(행정동, 법정동, 도엽번호) 변경처리 or 급수전계량기 공간 편집 시 연결된 급수관로 공간 속성정보 변경처리
		 * 
		 * 			[ 처리 단계 ]
		 * 			설명 생략
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체), _sRelatedLayerFId(연관 레이어의 fid)
		 * 사용법 : checkRelationLocFldValue(_oSourceFeature, _sRelatedLayerFId)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationLocFldValue : function(_oSourceFeature, _sRelatedLayerFId){			
			this.checkFldValueChange = function(_obj, _sLayer, _sG2Id, _sFldName){
				var bChgState = false;
				var sLblFldName = MAP_EDITOR.fn_replace_fieldName(_sLayer, _sFldName);
				var sType 		= editor.layerColumnInfo[_sLayer].fieldInfo[_sFldName].g2_DATATYPE;
				var nDataLength = editor.layerColumnInfo[_sLayer].fieldInfo[_sFldName].g2_LENGTH;
				var nPrecision 	= editor.layerColumnInfo[_sLayer].fieldInfo[_sFldName].g2_PRECISION;
				var nScale 		= editor.layerColumnInfo[_sLayer].fieldInfo[_sFldName].g2_SCALE;				
				
				return bChgState = MAP_EDITOR.fn_check_fldValueChange(_obj, _sLayer, _sG2Id, sLblFldName, sType, nDataLength, nPrecision, nScale);				
			};
			
			this.initPreValueObj = function(){
				oPreValue = {
						id : '',									// FieldName
						value : ''								// FieldValue
				}
			};

			var bRuleChkState = false;
			var sSourceFeatureLayer = MAP_EDITOR.fn_get_tblNameByFeature(_oSourceFeature);
			var sSourceFeatureG2Id = MAP_EDITOR.fn_get_g2idByFeature(_oSourceFeature);
			var sRelatedFeatureLayer = '', sRelatedG2Id = '';
			var oPreValue = {};
		
			if(_sRelatedLayerFId  && _sRelatedLayerFId !== ''){
				sRelatedFeatureLayer = _sRelatedLayerFId.split('.')[0];
				sRelatedG2Id = _sRelatedLayerFId.split('.')[1];
			}

			this.initPreValueObj();
			editor.setPreValue(oPreValue);

			var aPositionLayerInfo = MAP_EDITOR.fn_get_aPositionLayerInfo();
			for(var idx in aPositionLayerInfo){
				var sSearchLayer = aPositionLayerInfo[idx]['layer'];
				var sSearchFldName = aPositionLayerInfo[idx]['field'];

				if(editor.layerColumnInfo[sSourceFeatureLayer].fieldInfo[sSearchFldName] !== undefined){
					oPreValue.id = sSearchFldName;
					oPreValue.value = MAP_EDITOR.fn_get_layerPositionInfo(sSearchLayer, sSearchFldName, _oSourceFeature);
					bRuleChkState = this.checkFldValueChange(oPreValue, sSourceFeatureLayer, sSourceFeatureG2Id, sSearchFldName);
				}

				if(sRelatedFeatureLayer !== ''){
					if(oPreValue.value !== '' && editor.layerColumnInfo[sRelatedFeatureLayer].fieldInfo[sSearchFldName] !== undefined)
						bRuleChkState = this.checkFldValueChange(oPreValue, sRelatedFeatureLayer, sRelatedG2Id, sSearchFldName);
				}

				this.initPreValueObj();
				editor.setPreValue(oPreValue);
			}

			return this.resultState = bRuleChkState;
		},
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationFldValue
		 * 설 명 : 지오메트리의 속성을 지정한 값으로 변경처리
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 급수관로의  속성정보(ex. 폐전여부, 수전번호, etc) 변경처리
		 * 
		 * 			[ 처리 단계 ]
		 * 			설명 생략
		 * 
		 * 인 자 : _sRelatedLayerFId(변경할 레이어의 FID), _sField(변경할 필드), _sValue(변경할 속성값)
		 * 사용법 : checkRelationFldValue(sRelateLayerFId, _sField, _sValue)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationFldValue : function(_sRelatedLayerFId, _sField, _sValue){

			var bRuleChkState = false;
			
			var sLayer = _sRelatedLayerFId.split('.')[0];
			var sG2Id = _sRelatedLayerFId.split('.')[1];
			
			var sLblFldName = MAP_EDITOR.fn_replace_fieldName(sLayer, _sField);
			var sType 		= editor.layerColumnInfo[sLayer].fieldInfo[_sField].g2_DATATYPE;
			var nDataLength = editor.layerColumnInfo[sLayer].fieldInfo[_sField].g2_LENGTH;
			var nPrecision 	= editor.layerColumnInfo[sLayer].fieldInfo[_sField].g2_PRECISION;
			var nScale 		= editor.layerColumnInfo[sLayer].fieldInfo[_sField].g2_SCALE;
	    	
			var oPreValue = {
					id : _sField.toLowerCase(),			// FieldName
					value : ''								// FieldValue
			}

			editor.setPreValue(oPreValue);
			
			oPreValue.value =_sValue;
			
			return this.resultState = bRuleChkState = MAP_EDITOR.fn_check_fldValueChange(oPreValue, sLayer, sG2Id, sLblFldName, sType, nDataLength, nPrecision, nScale);					
		},
		
		
		/**********************************************************************************
		 * 함수명 : geometryTranslate
		 * 설 명 : 개별 지오메트리 Vertex Offset 만큼 이동
		 * 
		 * 인 자 : _oGeometry(이동할 geometry), _oOffset(이동시킬 Offset수치)
		 * 사용법 : geometryTranslate(_oGeometry, _oOffset)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		geometryTranslate : function(_oGeometry, _oOffset){
			this.newCoordinates = function(sx, sy, nx, ny){
				var objResult = {};															// 거리와 각을 이용해서 계산한 새로운 좌표점(x,y)		
				var nAngle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));		//현재지점과 다음지점과의 각		
				var nDist = 5;
				objResult.x = sx + nDist * Math.cos(nAngle);
				objResult.y = sy + nDist * Math.sin(nAngle);

				return objResult;
			}
			
			var nOffsetX = parseFloat(_oOffset.x);
			var nOffsetY = parseFloat(_oOffset.y);
			
			if(typeof _oOffset !== 'object')
				_oOffset = {x:0, y:0};

			var sSourceType = _oGeometry.CLASS_NAME.replace('OpenLayers.Geometry.','');
			
			switch(sSourceType){
				case 'Point' :
					_oGeometry.x += nOffsetX;
					_oGeometry.y += nOffsetY;
					break; 
				case 'LineString' :
					for(var i=0,len=_oGeometry.components.length; i<len; i++){
						_oGeometry.components[i].x += nOffsetX;
						_oGeometry.components[i].y += nOffsetY;
					}
					break;
				case 'Polygon' :
		    		var oComponents = _oGeometry.components;
		    		if(oComponents.length > 0) {
		    			for(var i=0, len=oComponents.length; i<len; i++){
		    				var oComponent = oComponents[i];
		        			for(var j=0, jLen=oComponent.components.length-1; j<jLen; j++){
		        				var oInnerComponent = oComponent.components[j];
			    				oInnerComponent.x += nOffsetX;
			    				oInnerComponent.y += nOffsetY;
		        			}
		    			}
		    		}
					break;
			}
			_oGeometry.bounds = null;
			_oGeometry.bounds = editor.getBoundsByNewGeometry(_oGeometry);

			return _oGeometry;
		},

		
		/**********************************************************************************
		 * 함수명 : deleteGeometry
		 * 설 명 : 레이어별 Feature 삭제
		 * 
		 * 			[ 처리 단계 ]
		 * 			레이어별 Feature를 삭제 시킨 후 WFS Layer의 Filter를 업데이트 시킨 후, 중간저장 DB에 저장시킴.
		 * 			중간저장 단계 : 중간저장 DB Insert(삭제객체 상태로 : state = 4) -> 정상 insert 개체에 대해 WFS Layer에서 삭제-> Style Layer에 추가 -> 편집모니터 등록
		 * 
		 * 인 자 : _oRes(연산 결과를 가지고 있는 삭제할 Object), _bAdd(editor.editLayer에 추가시킬지 여부, true=추가, false=추가시키지 않음)
		 * 사용법 : deleteGeometry(_oRes)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		deleteGeometry : function(_oRes, _bAdd){
			
			var oCurLayer = {}, oCurGFeature = {};
			
			// _oRes가 복수개 레이어를 담고 있을경우, 
			// 레이어단위별로 가져올 때 _oRes.data[i]로 값을 읽어오게되므로 oGData에 값을 담기 위해서는 다시 data 속성을 생성해줘야 함.
			var oGData = MAP_EDITOR.fn_get_objFactory().Util.createGData();
			
			for(var i=0,len=_oRes.data.length; i<len; i++){ // 레이어 별로,	
				
				oCurLayer = _oRes.data[i];				
				
				for(var j=0, jLen=oCurLayer.results.length; j<jLen; j++){	 // Feature 별로,								
					oCurGFeature = oCurLayer.results[j];	
					MAP_EDITOR.fn_update_filterOnWFSLayer(oCurLayer.table, oCurGFeature.g2id);								
				}
				
				// 레이어 단위로 중간 저장 DB에 Insert
				oGData.data.push(oCurLayer);
				
				if(_bAdd)
					MAP_EDITOR.fn_save_middleAll(oGData);								// editor.editLayer에 추가함.					
				else
					MAP_EDITOR.fn_save_middleAll(oGData, 'excludeEditLayer', 4);		// editor.editLayer에 추가하지 않음.				
			}
		},
		
		
		
		
		
		
		/**********************************************************************************
		 * 여기서 부터는 수원 프로젝트에서만 사용하는 Customized 된 함수들 정의 
		 **********************************************************************************/
		
		
		
		
		/**********************************************************************************
		 * 함수명 : checkRelationGeometryDeleteForSuwon
		 * 설 명 : 선형 지오메트리상에 존재 혹은 연결된 연관 지오메트리 삭제 확인(LL, LP)
		 * 
		 * 			[ 상세 설명 ]
		 * 			1) 적용 상황 : 선형 데이타 상 시설물 삭제 & 급수전계량기가 연결되어 있는 경우 급수관로 삭제 불가처리 
		 * 			2) 현 편집 객체상에 존재 혹은 연결된 객체 삭제 수행. 반환값 없음.
		 * 
		 * 			[ 처리 단계 ]
		 * 			STEP1. 현 편집(수정)객체위에 존재하는 객체 삭제
		 * 				STEP1-1. 현 편집(수정)객체와의 연관 객체 찾기 - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP1-2. STEP1-1에서 찾은 연관 객체 삭제
		 * 			STEP2. 현 편집(수정)객체와 연결된 객체 삭제		
		 * 				STEP2-1. 현 편집(수정)객체와 연결된 객체 찾기 - TOUCHES 연산수행
		 * 				STEP2-2. STEP2-1에서 찾은 객체와 연관 객체 찾기(관말객체) - '위'에 존재하는 객체(INTERSECT 연산수행)
		 * 				STEP2-3. STEP2-2-1에서 찾은 객체 삭제(관말 객체)
		 * 				STEP2-4. 현 편집(수정)객체와 연결된 객체 삭제(ex. 급수관)
		 * 
		 * 인 자 : _oSourceFeature(연산할 기준 객체 : Feature임 - attributes, geometry, layer등을 보유하고 있음)
		 * 반환값 : this.resultState(Boolean) 값
		 * 사용법 : checkRelationGeometryDeleteForSuwon(_oSourceFeature)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
		checkRelationGeometryDeleteForSuwon : function(_oSourceFeature){	
			
			this.makeResObjForDelete = function(_oGFeature){

				var oGData = MAP_EDITOR.fn_get_objFactory().Util.createGData();
				var oGResult = MAP_EDITOR.fn_get_objFactory().Util.createGResult(MAP_EDITOR.fn_get_tblNameByFeature(_oGFeature.feature));
				
				oGResult.results.push(_oGFeature);
				oGData.data.push(oGResult);	
				return oGData;
			};	
			
			
			
			var sSourceFeatureLayer= '';
			var aCompMainLayer = [], sCompLayer = '', aCompSubLayer = [];
			var oTouchesGeom = {}, oIntersectsGeom = {};
															
			sSourceFeatureLayer = MAP_EDITOR.fn_get_tblNameByFeature(_oSourceFeature);		
			
			if(EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer instanceof Object){
				var oRelatedLayer = EditRuleRelatedLayer[sSourceFeatureLayer].connectedlayer;
				if(oRelatedLayer.length === undefined){
					sCompLayer = oRelatedLayer.mainlayer.toString();
					aCompSubLayer = oRelatedLayer.onlayer;	
				}				
			}
			aCompMainLayer = EditRuleRelatedLayer[sSourceFeatureLayer].onlayer;
				
			var oSourceFeatureClone = MAP_EDITOR.fn_clone_featureToGInnerFeature(_oSourceFeature);	// 원본 Feature			
			
			// STEP1.
			// STEP1-1.
			oIntersectsGeom = this.checkRelationGeometry(_oSourceFeature, aCompMainLayer, GEditRule.spatialOperType.INTERSECTS);
			if(COMMON.isEmptyObject(oIntersectsGeom) === false){ //존재하면
				this.resultState = true;
				if(oIntersectsGeom.data.length > 0){
					
					var aRelateLayer = [];
					var sRelateLayer = '';
					for(var t=0,tLen=oIntersectsGeom.data.length; t<tLen; t++){
						aRelateLayer.push(COMMON.fn_get_EditKorLayerNm(oIntersectsGeom.data[t].table));
					}
					sRelateLayer = aRelateLayer.join(',');
					
					// STEP1-2.
					if(sSourceFeatureLayer === 'WTL_SPLY_LS'){						
						COMMON.showMessage('편집오류 & ' + COMMON.fn_get_EditKorLayerNm(sSourceFeatureLayer)+' 와 연결된 [' + sRelateLayer + ']가 존재하므로 삭제할 수 없습니다.',4000);	
						this.resultState = false;
					}
					else{
						if (confirm(COMMON.fn_get_EditKorLayerNm(sSourceFeatureLayer)+' 위에 [' + sRelateLayer + ']  존재하는데 같이 삭제하시겠습니까?')){    // 복수 시설물 존재함.		
							
							this.deleteGeometry(oIntersectsGeom, false);
							this.resultState = true;
						}	
					}
				}				
			}		
			
			// STEP2. 
			// STEP2-1.
			if(sCompLayer !== '')
				oTouchesGeom = this.checkRelationGeometry(oSourceFeatureClone, sCompLayer, GEditRule.spatialOperType.TOUCHES);

			// STEP2-2.
			var oDoNotDelLayer = {};
			if(COMMON.isEmptyObject(oTouchesGeom) === false){
				if(oTouchesGeom.data.length > 0){
					
					var nTouchesGeomCnt = oTouchesGeom.data[0].results.length;
					if (confirm('연결된 [' + COMMON.fn_get_EditKorLayerNm(sCompLayer) + '] ' + nTouchesGeomCnt + '개 존재하는데 같이 삭제하시겠습니까?')){

						for(var k=0,kLen=nTouchesGeomCnt; k<kLen; k++){	// 단일 시설물이며, Feature 별로,
							// 연산 대상을 찾아야 하므로 JSTS 연산을 사용할 수 없음.							
							oIntersectsGeom = this.checkRelationGeometry(oTouchesGeom.data[0].results[k].feature, aCompSubLayer, GEditRule.spatialOperType.INTERSECTS);

							// STEP2-3.							
							if(COMMON.isEmptyObject(oIntersectsGeom) === false && oIntersectsGeom.data.length > 0){ 	 // 관말이 존재하면,								
								
								if(sCompLayer === 'WTL_SPLY_LS')
									oDoNotDelLayer[oTouchesGeom.data[0].results[k].g2id] = oIntersectsGeom.data[0].results[0].g2id;
								else{
									this.deleteGeometry(oIntersectsGeom, false);									
									// STEP2-4.
									this.deleteGeometry(this.makeResObjForDelete(oTouchesGeom.data[0].results[k]), false);
									this.resultState = true;
								}
							}
							else{	// STEP2-4.			//관말이 존재하지 않으면
								this.deleteGeometry(this.makeResObjForDelete(oTouchesGeom.data[0].results[k]), false);
								this.resultState = true;
							}		
						}
						
						var sMsg = [];				
						$.each(oDoNotDelLayer, function(key, value){
							sMsg.push(key + '-' + value + '</br> ');
						});
						COMMON.showMessage('편집오류 & 연결된 급수전 계량기 존재시 급수관로 삭제 불가</br> 불가 시설물관리번호 : [급수관로-급수전계량기] </br>'+ sMsg.join(''),5000);						
					}
				}								
			}	
			
			return this.resultState;
		}
		
};