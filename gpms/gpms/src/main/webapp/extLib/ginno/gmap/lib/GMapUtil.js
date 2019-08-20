OpenLayers.Util.getImagesLocation = function() {
	return "";
    //return OpenLayers.ImgPath || (OpenLayers._getScriptLocation() + "img/");
};


/**
 * DPI 재설정
 */
OpenLayers.DOTS_PER_INCH = 96;

var GMapUtil = {
		sendProxyGet: function(serviceUrl, params, callback) {
	        $.get(CONFIG.fn_get_getProxyUrl(), {
	            url: encodeURIComponent(serviceUrl),
	            params: encodeURIComponent(params)
	        }, function(res) {
	        	if(!GMapUtil.hasErr(res, this))
	        		callback(res)
	        })
	    },
	    sendProxyPost: function(serviceUrl, params, callback) {
	        $.post(CONFIG.fn_get_postProxyUrl(), {
	            url: encodeURIComponent(serviceUrl),
	            params: encodeURIComponent(params)
	        }, function(res) {
	        	if(!GMapUtil.hasErr(res, this))
	        		callback(res)
	        })
	    },
	    sendProxyPostSync: function(serviceUrl, params, callback) {
	        $.ajax({
	            type: "post",
	            dataType: "xml",
	            data: {
	                url: encodeURIComponent(serviceUrl),
	                params: encodeURIComponent(params)
	            },
	            async: false,
	            url: CONFIG.fn_get_postProxyUrl(),
	            success: function(res) {
	                callback(res)
	            },
	            error: function(xhr, status, error) {
	                alert("sendProxyPostSync \uc624\ub958\ubc1c\uc0dd.\n check2!. status = " + status + ", error=" + error)
	            }
	        })
	    },
	    hasErr: function(objRes, objReq) {
	    	var nResElementSize  = $(objRes).length;
	    	var blnHasError = false;
	    	for(var i=0;i<nResElementSize;i++){
	    		var oTmp = $(objRes)[i];
	    		if(oTmp.attributes){
	    			if(oTmp.getElementsByClassName("error").length > 0){
	        			alert("Error Message - [" + oTmp.getElementsByClassName("error")[0].innerText +"]\n\n" + decodeURIComponent(decodeURIComponent(objReq.data)));
	        			blnHasError = true;
	        			break;
	        		}
	    		}
	    	}
	    	return blnHasError;
	    }
}
