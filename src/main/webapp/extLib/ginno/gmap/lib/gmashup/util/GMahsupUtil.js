/**********************************************************************************
 * 파일명 : GMahsupUtil.Position
 * 설 명 : div position을 다루기 위한 클래스 
 * 필요 라이브러리 : OpenLayers
 * 
 * 수정일				수정자				version				Function 명
 * --------------------------------------------------------------------------------
 * 2015.10.06			임상수				1.0					최초 생성
 * 
 * 참고 자료
 * --------------------------------------------------------------------------------
 * OpenLayers
 * 출처 : http://openlayers.org/
 * 
 * 
**********************************************************************************/
GMahsupUtil = OpenLayers.Class({
	CLASS_NAME: "GMahsupUtil"
});

GMahsupUtil.Position = OpenLayers.Class({
	/**
     * Property: top
     * {Number}
     */
	top : 0,
	
	/**
     * Property: left
     * {Number}
     */
	left : 0,
	
	/**
     * Property: bottom
     * {Number}
     */
	bottom : 0,
	
	/**
     * Property: right
     * {Number}
     */
	right : 0,
	
	 /**
     * Constructor: GMahsupUtil.Position
     * 위치를 저장하기 위한 클래스 이며 생성시 값의 순서는 left, bottom, right, top 이다.
     *
     * Parameters (four arguments):
     * left - {Number} left 값
     * bottom - {Number} bottom 값
     * right - {Number} The y-axis coordinate in map units.  If your map is in
     * top - {Number} The y-axis coordinate in map units.  If your map is in
     *
     * Parameters (single argument):
     * position - {Array(Number)} [left, bottom, right, top]
     */
	initialize : function(left, bottom, right, top) {
		if (OpenLayers.Util.isArray(left)) {
            left = lon[0];
            bottom = lon[1];
            right = lon[2];
            top = lon[3];
        }
		
		this.left = left | this.left;
		this.bottom = bottom | this.bottom;
		this.right = right | this.right;
		this.top = top | this.top;
		
	},	
	CLASS_NAME: "GMahsupUtil.Position"
});