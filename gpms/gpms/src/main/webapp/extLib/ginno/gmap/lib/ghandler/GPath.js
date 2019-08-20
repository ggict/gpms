GPath = OpenLayers.Class(OpenLayers.Handler.Path, {
	
	attributes : null,
	
	finalize: function(cancel) {
        var key = cancel ? "cancel" : "done";
        this.drawing = false;
        this.mouseDown = false;
        this.lastDown = null;
        this.lastUp = null;
        this.callback(key, [this.geometryClone(), this.attributes]);
        if(cancel || !this.persist) {
            this.destroyFeature();
        }
    },
    
    mousedown: function(evt) {
        // ignore double-clicks
        if (this.lastDown && this.lastDown.equals(evt.xy)) {
            return false;
        }
        if(this.lastDown == null) {
            if(this.persist) {
                this.destroyFeature();
            }
            this.createFeature(evt.xy);
        } else if((this.lastUp == null) || !this.lastUp.equals(evt.xy)) {
            this.addPoint(evt.xy);
        }
        this.mouseDown = true;
        this.lastDown = evt.xy;
        this.drawing = true;
        
        //mousedown callback 추가
        this.callback("mousedown", [this.point.geometry, this.getGeometry()]);
        
        //마우스 우클릭 일때 실행
        if(evt.button == "2") {
			this.rightclick(evt);
	        return true;
		}
        
        return false;
    },
    
    rightclick: function(evt) {
    	this.dblclick(evt);
    	return false;
    },
    
    mouseup: function (evt) {
        this.mouseDown = false;
        if(this.drawing) {
            if(this.freehandMode(evt)) {
                this.removePoint();
                this.finalize();
            } else {
                if(this.lastUp == null) {
                   this.addPoint(evt.xy);
                }
                this.lastUp = evt.xy;
            }
            
            //mouseup callback 추가
            this.callback("mouseup", [this.point.geometry, this.getGeometry()]);
            
            return false;
        }
        
        //mouseup callback 추가
        if(this.point && this.point.geometry && this.getGeometry()) {
        	this.callback("mouseup", [this.point.geometry, this.getGeometry()]);
        }
        
        return true;
    },
    
    finish : function() {
    	var index = this.line.geometry.components.length - 1;
        this.line.geometry.removeComponent(this.line.geometry.components[index]);
        this.removePoint();
        this.finalize();
        return false;
    },
    
	/**********************************************************************************
	 * 함수명 : addPoint
	 * 설 명 : LinearRing에 point 추가, 
	 * 			교차옵션-'교차' 사용여부에 따라 입력 point기준으로 생성된 path를 divide 처리
	 * 			교차옵션-'상월' 사용여부에 따라 입력 point기준으로 생성된 path와 교차하는 line 발견시 교차점을 기준으로 상월처리
	 * 인 자 : pixel (사용자 입력지점의 OpenLayers.Pixel)
	 * 사용법 : addPoint(pixel)
	 * 수정일			수정자			수정내용
	 * ----------------------------------------------------------------------
	 * 2016.08.29		윤은희			최초 생성
	 **********************************************************************************/
    /*addPoint: function(pixel) {
    	// 객체 추가시, 교차옵션적용[교차,상월,하월]
    	if(GDrawPath.mode && GDrawPath.crossOption !== 'cut'){
    		!GDrawPath.forDivideFeature ? GDrawPath.forDivideFeature = this.line.clone() : GDrawPath.forDivideFeature.geometry.components.push(this.point.geometry.clone());	
    	}    	
    	
    	this.layer.removeFeatures([this.point]);
        var lonlat = this.layer.getLonLatFromViewPortPx(pixel); 
        this.point = new OpenLayers.Feature.Vector(
            new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat)
        );
        this.line.geometry.addComponent(
            this.point.geometry, this.line.geometry.components.length
        );
        this.layer.addFeatures([this.point]);
        this.callback("point", [this.point.geometry, this.getGeometry()]);
        this.callback("modify", [this.point.geometry, this.getSketch()]);
        this.drawFeature();
        delete this.redoStack;
        
        this.replaceStartPoint = function(_nX, _nY){
        	for(var i=0, len=this.line.geometry.components.length; i<len; i++){
				if((this.point.geometry.x === this.line.geometry.components[i].x) && (this.point.geometry.y === this.line.geometry.components[i].y)){
					this.line.geometry.components[i].x = _nX;
					this.line.geometry.components[i].y = _nY;
				}
			}
			this.layer.features[3].geometry.x = _nX;
			this.layer.features[3].geometry.y = _nY;        						
			this.point = this.layer.features[3];
        }

		this.newCoordinates = function(sx, sy, nx, ny, flag){			// 시작점 X좌표, 시작점 Y좌표, 다음점 X좌표, 다음점 Y좌표, dist 연산 여부
			var objResult = {};			// 거리와 각을 이용해서 계산한 새로운 좌표점(x,y)
			var nGap = 0.5; 			// 50cm
			var nDist = 0;
			var nAngle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));							//현재지점과 다음지점과의 각
			
			nDist = flag ? COMMON.fn_get_DistanceBy2Point(sx, sy, nx, ny)-nGap : nGap;		//현재지점과 다음지점과의 거리
			objResult.x = sx + nDist * Math.cos(nAngle);
			objResult.y = sy + nDist * Math.sin(nAngle);

			return objResult;
		}

		
        // 객체 추가시, 교차옵션적용[교차, 상월, 하월] : path의 segment마다 교차옵션 적용하여 처리함.
        if(GDrawPath.mode && GDrawPath.crossOption !== 'cut'){
        	var sEditLayer = COMMON.fn_get_editingLayer();
        	if(GDrawPath.forDivideFeature && GDrawPath.forDivideFeature.geometry.components.length > 1){			

        		// intersect판단은 tolerance가 적용된 가상의 지점과 교차여부 판단하도록 처리함.
        		// 교차점에 일정 tolerance를 줘서 이전 입력 point 지점은 교차점 판단 지점에서 제외되도록 처리함. intersect 연산을 위해서만 사용함. 
        		var oTmpPointFeatureForCalc = GDrawPath.forDivideFeature.clone();
        		var sx, sy, nx, ny, angle, nDist;

        		sx = oTmpPointFeatureForCalc.geometry.components[0].x;			//시작점 X좌표
        		sy = oTmpPointFeatureForCalc.geometry.components[0].y;			//시작점 Y좌표
        		nx = oTmpPointFeatureForCalc.geometry.components[1].x;			//다음점 X좌표
        		ny = oTmpPointFeatureForCalc.geometry.components[1].y;			//다음점 Y좌표
        		nDist = GEditRule.tolerance;

        		angle = COMMON.fn_get_angleBy2Dist((ny-sy), (nx-sx));					
        		oTmpPointFeatureForCalc.geometry.components[0].x = sx + nDist * Math.cos(angle);
        		oTmpPointFeatureForCalc.geometry.components[0].y = sy + nDist * Math.sin(angle);

        		var oIntersectsGeom = GEditRule.checkRelationGeometry(oTmpPointFeatureForCalc, sEditLayer, GEditRule.spatialOperType.INTERSECTS);
        		if(COMMON.isEmptyObject(oIntersectsGeom) === false && oIntersectsGeom.data.length === 1){
        			if(oIntersectsGeom.data[0].results.length >= 2){
        				// 방금 입력점 삭제 : this.line(=this.layer.feature.geometry.components중 3번째 item이랑 동일)에 동일점이 두번 add되어 있는데 둘다 삭제하지 않고 먼저 add한 점만 삭제해야 하므로
        				var index = this.line.geometry.components.length - 2;
        				this.line.geometry.removeComponent(this.line.geometry.components[index]);
        				this.removePoint();

        				if(this.line.geometry.components.length > 1)
        					this.drawFeature();

        				var index = GDrawPath.forDivideFeature.geometry.components.length - 1;        				
        				OpenLayers.Util.removeItem(GDrawPath.forDivideFeature.geometry.components, GDrawPath.forDivideFeature.geometry.components[index]);        				

        				COMMON.showMessage('편집오류 &  ' + COMMON.fn_get_EditKorLayerNm(sEditLayer) + '를 2개 이상 만나면 처리를 할 수 없습니다.');
        			}
        			else{        				
        				// 입력 feature의 시작점과 intersect된 객체와의 거리가 입력 feature의 끝점보다 가깝고 tolerance보다 작으면, 시작점이 접점(ex. 스냅을 걸었을 경우)에 해당하므로 divide 수행하지 않음.	    							
        				var oSPointGeom = GGeoJson.getGeoJson('Point', GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-2]);	    							
        				var oEPointGeom = GGeoJson.getGeoJson('Point', GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-1]);	    							
        				var oCompLineStringGeom = GGeoJson.getGeoJson('LineString', oIntersectsGeom.data[0].results[0].feature.geometry.components);

        				var nSCompDist = GGeomJSTSOper.calcGeomDistance(oCompLineStringGeom, oSPointGeom);
        				var nECompDist = GGeomJSTSOper.calcGeomDistance(oCompLineStringGeom, oEPointGeom);

        				// if(nSCompDist < GEditRule.tolerance)이면, 접점(ex. 스냅을 걸었을 경우)에 해당하는 경우이며 이때는 'do nothing'
        				if((nSCompDist > nECompDist) || (nSCompDist < nECompDist && nSCompDist > GEditRule.tolerance)){
        					switch(GDrawPath.crossOption){
        					case 'cross' : 
        									// #0. 입력선분할
        									var aDividedPosList = [], aDividedFrontPosList;
			        						if(GDrawPath.forDivideFeature.geometry.components.length > 2) 
						        			{
			        							var oTmpCloneCal = GDrawPath.forDivideFeature.geometry.clone();
			        							var oTmpCloneResult = GDrawPath.forDivideFeature.geometry.clone();
			        							for(var i=0, len=oTmpCloneCal.components.length-2; i<len; i++){	// 입력선의 마지막 segment를 가지고 분할 연산하는데 사용
			        								OpenLayers.Util.removeItem(oTmpCloneCal.components, oTmpCloneCal.components[0]);
			        							}
			        							aDividedPosList = editor.getDivideLine(oTmpCloneCal.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
			        							
			        							// 분할 결과(aDividedPosList)를 입력선(oTmpCloneResult)에 합치기
			        							var aTmpDividedPosList = [];
			        							for(var j=0, jLen=oTmpCloneResult.components.length-1; j<jLen; j++){	// 마지막 점은 분할 객체 중 2번째 분할 객체의 끝점에 해당하므로 제외시킴 
			        								aTmpDividedPosList.push([oTmpCloneResult.components[j].x, oTmpCloneResult.components[j].y]);
			        							}			        		    				
			        							aTmpDividedPosList.push(aDividedPosList[0][aDividedPosList.length-1]);		// 분할 객체 중 1번째 선분의 끝점
			        							aDividedFrontPosList = aTmpDividedPosList;
					        				}
			        						else{
			        							aDividedPosList = editor.getDivideLine(GDrawPath.forDivideFeature.geometry.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
			        							aDividedFrontPosList = aDividedPosList[0];
			        						}

			        						// #1. divided 객체(입력된 line feature)는 신규 관로로 (편집모니터에) 추가.
			        						var sG2Id = MAP_EDITOR.fn_get_newG2Id();
			        						var oGInnerFeature = editor.makeFeatureByPosList('linestring', aDividedFrontPosList, sEditLayer.concat('.', sG2Id));
			        						editor.addDrawFeature(editor.editLayer, oGInnerFeature, sEditLayer);
			        						MAP_EDITOR.fn_add_featureToEditMonitor(oGInnerFeature, sEditLayer, sG2Id);
			
			        						// #2. this.point 좌표점을 찾아서 분할지점(교차되었던) 좌표점으로 대체			        						
			        						var aDividedFrontObjEndPoint = aDividedFrontPosList[aDividedFrontPosList.length-1];
			        						this.replaceStartPoint(aDividedFrontObjEndPoint[0], aDividedFrontObjEndPoint[1]);
			        						this.drawFeature();
			
			        						// #3. 교차된 기존 시설물(oIntersectsGeom) divide 수행
			        						var oGFeatureForDivideBaseLine, oGInnerFeatureForDivideTarget;
			        						var sFId = MAP_EDITOR.fn_get_fidByFeature(GDrawPath.forDivideFeature);
			        						if(GDrawPath.forDivideFeature.geometry.components.length > 2){
			        							var oTmpCloneCal = MAP_EDITOR.fn_clone_featureToGInnerFeature(GDrawPath.forDivideFeature);			        							
			        							for(var i=0, len=oTmpCloneCal.geometry.components.length-2; i<len; i++){	// 입력선의 마지막 segment를 가지고 분할 연산하는데 사용
			        								OpenLayers.Util.removeItem(oTmpCloneCal.geometry.components, oTmpCloneCal.geometry.components[0]);
			        							}			        							
			        							oGFeatureForDivideBaseLine = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(oTmpCloneCal, sFId, oTmpCloneCal.state);
			        						}
			        						else{
			        							oGFeatureForDivideBaseLine = MAP_EDITOR.fn_convert_olFeatureTOoGFeature(GDrawPath.forDivideFeature, sFId, GDrawPath.forDivideFeature.state);				        										        						
			        						}
			        						editor.addUnDrawFeature(editor.refLayer, oGFeatureForDivideBaseLine.feature);
			        						oGInnerFeatureForDivideTarget = oIntersectsGeom.data[0].results[0].feature;
			        						editor.divideLine(oGFeatureForDivideBaseLine, oGInnerFeatureForDivideTarget);
			        						
			        						break;
        					case 'over' :
		        						// #1. 선 분할
		        						var aDividedPosList = editor.getDivideLine(GDrawPath.forDivideFeature.geometry.components, oIntersectsGeom.data[0].results[0].feature.geometry.components);
		        						var aDividedFrontPosList = aDividedPosList[0];
		        						var aDividedBackPosList = aDividedPosList[1];
		
		        						// 분할된 첫번째 선의 끝 segment 정보 가져오기
		        						var aFrontPoint1 = aDividedFrontPosList[aDividedFrontPosList.length-2];  		// 끝 segment 시작점
		        						var aFrontPoint2 = aDividedFrontPosList[aDividedFrontPosList.length-1];		// 끝 segment 끝점
		
		        						// 분할된 두번째 선의 첫번째 segment 정보 가져오기
		        						var aBackPoint1 = aDividedBackPosList[0];  		// 첫 segment 시작점
		        						var aBackPoint2 = aDividedBackPosList[1];			// 첫 segment 끝점
		
		        						// #2. 교차점 찾아서 상월 geometry 생성
		        						var oForDivideLineStringGeom = GGeoJson.getGeoJson('LineString', GDrawPath.forDivideFeature.geometry.components);
		        						var oCrossPoint = GGeomJSTSOper.calcGeomIntersection(oForDivideLineStringGeom, oCompLineStringGeom);
		
		        						if(oCrossPoint.coordinate){
		        							var nAngle = COMMON.fn_get_angleToDegreeByDist((aFrontPoint2[1]-aFrontPoint1[1]), (aFrontPoint2[0]-aFrontPoint1[0]));	// fn_get_angleToDegreeByDist((ny-sy), (nx-sx));
		        							var oOpt = {
		        									cx : oCrossPoint.coordinate.x,
		        									cy : oCrossPoint.coordinate.y,
		        									radius : 5,
		        									startAngle : nAngle+180,	// Degree (생성시킬 arc를 입력 geometry의 진행방향으로 poslist를 생성하기 위한 start 각도)
		        									endAngle :  nAngle,		// Degree
		        									segments : 10
		        							};
		        							var oFeatureArc = editor.getArcFeature(new OpenLayers.Geometry.Point(oOpt.cx,oOpt.cy), oOpt.radius, oOpt.startAngle, oOpt.endAngle, oOpt.segments);
		
		        							// 입력선의 마지막 segment의 시작점 - 여기가 교체 시작지점
		        							var oSeg1 = GDrawPath.forDivideFeature.geometry.components[GDrawPath.forDivideFeature.geometry.components.length-2];
		
		        							// 생성된 상월을 교차점이 존재하는 입력선 segment에 merge
		        							for(var i=0, len=this.line.geometry.components.length; i<len; i++){
		        								if((oSeg1.x === this.line.geometry.components[i].x) && (oSeg1.y === this.line.geometry.components[i].y)){
		        									var aCompo1=[], aCompo2=[];
		        									if(len === 3){												// 사용자 입력선(GDrawPath.forDivideFeature)이 1개의 segment만 가지고 있었을경우, this.line은 중복점이 존재하므로 length=3임
		        										aCompo1.push(this.line.geometry.components[0]);
		        										aCompo2 = this.line.geometry.components.slice(1, this.line.geometry.components.length);
		        									}
		        									else{
		        										aCompo1 = this.line.geometry.components.slice(0,i+1);
		        										aCompo2 = this.line.geometry.components.slice(i+1, this.line.geometry.components.length);
		        									}
		
		        									for(var j=0, jLen=oFeatureArc.geometry.components.length; j<jLen; j++){
		        										aCompo1.push(oFeatureArc.geometry.components[j]);
		        									}
		        									this.line.geometry.components = aCompo1.concat(aCompo2);
		        									
		        									break;
		        								}
		        							}
		        						}
		        						
		        						break;
        					case 'under' : break;
        					}
        					
        					// 초기화
            				var oPointGeomClone = this.point.geometry.clone();
            				GDrawPath.forDivideFeature.geometry.components = [];
            				GDrawPath.forDivideFeature.geometry.components.push(oPointGeomClone);
        				}
        			}
        		}
        	}
        }
    },*/
    CLASS_NAME: "GPath"
});