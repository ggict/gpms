/* Copyright (c) 2006-2010 by OpenLayers Contributors (see authors.txt for 
 * full list of contributors). Published under the Clear BSD license.  
 * See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

 
/**
 * @requires OpenLayers/Layer/WMS.js
 * @requires OpenLayers/Tile/Image/IFrame.js
 */

/**
 * Class: OpenLayers.Layer.WMS.Post
 * Instances of OpenLayers.Layer.WMS.Post are used to retrieve data from OGC
 * Web Mapping Services via HTTP-POST (application/x-www-form-urlencoded). 
 * Create a new WMS layer with the <OpenLayers.Layer.WMS.Post> constructor.
 *
 * Inherits from:
 *  - <OpenLayers.Layer.WMS>
 */
GWMSPost = OpenLayers.Class(OpenLayers.Layer.WMS, {

    /**
     * Property: tileClass
     * {Object} Class, used to create tiles.
     */
    tileClass: null,

    /**
     * APIProperty: unsupportedBrowsers
     * {Array} Array with browsers, which should use the HTTP-GET protocol 
     * instead of HTTP-POST for fetching tiles from a WMS .
     * Defaults to ["mozilla", "firefox", "opera"], because Opera is not able 
     * to show transparent images in IFrames and Firefox/Mozilla has some ugly 
     * effects of viewport-shaking when panning the map. Both browsers, Opera
     * and Firefox/Mozilla, have no problem with long urls, which is the reason
     * for using POST instead of GET. The strings to pass to this array are
     * the ones returned by <OpenLayers.Util.getBrowserName()>.
     */
    unsupportedBrowsers: [],

    /**
     * Property: SUPPORTED_TRANSITIONS
     * {Array} 
     * no supported transitions for this type of layer, because it is not
     * possible to modify the initialized tiles (iframes)
     */
    SUPPORTED_TRANSITIONS: [],

    /**
     * Constructor: OpenLayers.Layer.WMS.Post
     * Creates a new WMS layer object.
     *
     * Example:
     * (code)
     * var wms = new OpenLayers.Layer.WMS.Post(
     *  "NASA Global Mosaic",
     *  "http://wms.jpl.nasa.gov/wms.cgi",
     *  {layers: "modis, global_mosaic"});
     * (end)
     *
     * Parameters:
     * name - {String} A name for the layer
     * url - {String} Base url for the WMS
     *                (e.g. http://wms.jpl.nasa.gov/wms.cgi)
     * params - {Object} An object with key/value pairs representing the
     *                   GetMap query string parameters and parameter values.
     * options - {Object} Hashtable of extra options to tag onto the layer.
     */
    initialize: function(name, url, params, options) {
        var newArguments = [];
        newArguments.push(name, url, params, options);
        OpenLayers.Layer.WMS.prototype.initialize.apply(this, newArguments);

        this.tileClass = OpenLayers.Util.indexOf(
            this.unsupportedBrowsers, OpenLayers.Util.getBrowserName()) != -1
                ? OpenLayers.Tile.Image
                : OpenLayers.Tile.Image.IFrame;
    },
    
    /**
     * Method: addTile
     * addTile creates a tile, initializes it and adds it as iframe to the
     * layer div.
     *
     * Parameters:
     * bounds - {<OpenLayers.Bounds>}
     * position - {<OpenLayers.Pixel>}
     *
     * Returns:
     * {<OpenLayers.Tile.Image.IFrame>} The added OpenLayers.Tile.Image.IFrame
     */
    addTile: function(bounds,position) {
        return new this.tileClass(
            this, position, bounds, null, this.tileSize);
    },
    
    /**********************************************************************************
     * 함수명 : getParam
     * 설 명 : WMS 호출 파라미터 반환
     * 인 자 : property (반환할 프로퍼티 명)
     * 사용법 : getParam(property)
     * 
     * 작성일 : 2011.04.21
     * 작성자 : 기술개발팀 최원석
     * 수정일				수정자			수정내용
     * ----------------------------------------------------------------------
     * 2011.04.21		최원석		최초 생성
     * 								
     **********************************************************************************/
    getParam: function(property) {
    	if(property) {
    		for(var i in this.params) {
    			if(i.toUpperCase() == property.toUpperCase()) {
    				return this.params[i];
    			}
    		}
    		/* 에러 처리 방안 후 일괄 처리
    		alert('GWMS 레이어 : 현재 레이어에 지정한 Property 가 없습니다.');
    		*/
    		return false;
    	}
    	else {
    		/* 에러 처리 방안 후 일괄 처리
    		alert('GWMS 레이어 : property를 지정하여 주십시오.');
    		*/
    	}
    },

    /**********************************************************************************
     * 함수명 : getParams
     * 설 명 : WMS 호출 파라미터들 반환
     * 사용법 : getParams()
     * 
     * 작성일 : 2011.04.21
     * 작성자 : 기술개발팀 최원석
     * 수정일				수정자			수정내용
     * ----------------------------------------------------------------------
     * 2011.04.21		최원석		최초 생성
     * 								
     **********************************************************************************/
    getParams: function() {
    	return this.params;
    },

    /**********************************************************************************
     * 함수명 : chkParams
     * 설 명 : options 을 체크 하고 변형 생성한다.
     * 인 자 : name (레이어 명), url (타일 서비스 주소), params (WMS 호출 파라미터), options (Layer options 들)
     * 사용법 : chkParams(options)
     * 작성일 : 2011.04.21
     * 작성자 : 기술개발팀 최원석
     * 수정일				수정자			수정내용
     * ----------------------------------------------------------------------
     * 2011.04.19		최원석		최초생성
     * 
     **********************************************************************************/
    chkParams : function(name, url, params, options){
    	//name 체크
    	if(!name) {
    		GError.create_obj(this, "Layer Name(레이어 명)");
    	}
    	else if(!url) {
    		GError.create_obj(this, "Url (서비스 주소)");
    	}
    	else if(!(params && params.layers)) {
    		GError.create_obj(this, "Parameter layers (요청 레이어 명 리스트)");
    	}
    },

    CLASS_NAME: 'GWMSPost'
});
