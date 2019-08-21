GProtocolWFS = function(options){
	options = OpenLayers.Util.applyDefaults(
	        options, OpenLayers.Protocol.WFS.DEFAULTS
	    );
	options.url = CONFIG.fn_get_postProxyUrl() + "?url="+encodeURIComponent(options.url);
	return new GProtocolWFS_v1_1_0(options);
}