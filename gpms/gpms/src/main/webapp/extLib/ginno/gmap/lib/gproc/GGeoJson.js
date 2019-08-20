/**********************************************************************************
 * 파일명 : GGeoJson.js
 * 설 명 : GeoJson 객체를 생성/관리한다.
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2016.03.25			윤은희				1.0					최초 생성
 * 
 * 
**********************************************************************************/


var GGeoJson = (function($,undefined) {
	
	/**
	 * 생성된 GeoJson 객체를 담고 있는 Object
	 */
	var oGeoJson = {};
     
     
     /**********************************************************************************
		 * 함수명 : getGeoJson
		 * 설 명 : GeoJson 객체를 생성하기 위한 Main 함수
		 * 인 자 : _sGeomType(Geometry 타입 - Point, LineString, Polygon), _oGeometries(좌표 List Object - ex. [POINT(412164.082 229838.719),POINT(412146.581 229840.085)])
		 * 사용법 : getGeoJson(_sGeomType, _oGeometries)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
	var getGeoJson = function(_sGeomType, _oGeometries){
    	 return oGeoJson = makeGeoJson(_sGeomType, _oGeometries);    	 
     };
     
     
     /**********************************************************************************
		 * 함수명 : makeGeoJson
		 * 설 명 : GeoJson 객체를 생성.
		 * 인 자 : _sGeomType(Geometry 타입 - Point, LineString, Polygon), _oGeometries(좌표 List Object - ex. [POINT(412164.082 229838.719),POINT(412146.581 229840.085)])
		 * 사용법 : makeGeoJson(_sGeomType, _oGeometries)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
     var makeGeoJson = function(_sGeomType, _oGeometries){
    	 
    	 var geoJson = {
    		  'type': 'Feature',
    		  'geometry': {
    		    'type': _sGeomType,
    		    'coordinates': getCoordinates(_sGeomType, _oGeometries)
    		  },
    		  'properties': {}
    		};
    	 
    	 return geoJson;
     };
     

     /**********************************************************************************
		 * 함수명 : getCoordinates
		 * 설 명 : Geometry 타입별로 Coordinate 값을 리턴.
		 * 인 자 : _sGeomType(Geometry 타입 - Point, LineString, Polygon), _oGeometries(좌표 List Object - ex. [POINT(412164.082 229838.719),POINT(412146.581 229840.085)])
		 * 사용법 : getCoordinates(_sGeomType, _oGeometries)
		 * 수정일				수정자			수정내용
		 * ----------------------------------------------------------------------
		 * 2016.03.25			윤은희		최초 생성
		 * 
		 **********************************************************************************/
     var getCoordinates = function(_sGeomType, _oGeometries){
    	 var aCoordinates = [];    	
    	 
    	 switch(_sGeomType.toLowerCase()){
	    	 case 'point' : 
	    		 	aCoordinates = [_oGeometries.x, _oGeometries.y]; 
	    		 	break;
	    	 case 'linestring' : 
	    	 		for(var i=0,len=_oGeometries.length;i<len;i++)
	    	 			aCoordinates.push([_oGeometries[i].x, _oGeometries[i].y]);
	    	 		break
	    	 case 'polygon': 
	    		 var aArr = [];
	    		 for(var i=0,len=_oGeometries.length;i<len;i++)
	    			 aArr.push([_oGeometries[i].x, _oGeometries[i].y]); 
	    		 aCoordinates.push(aArr);
	    		 break;
    	 }    	 
    	 return aCoordinates;
     };     
     
     
     /**
 	 * 외부 노출 함수
 	 */
     return {    	
    	 getGeoJson : getGeoJson
     }	
     
}(jQuery));
