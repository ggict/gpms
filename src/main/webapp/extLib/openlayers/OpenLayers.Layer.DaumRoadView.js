/* Copyright (c) 2006-2012 by OpenLayers Contributors (see authors.txt for 
 * full list of contributors). Published under the 2-clause BSD license.
 * See license.txt in the OpenLayers distribution or repository for the
 * full text of the license. */

/**
 * @requires OpenLayers/Layer/XYZ.js
 * http://i2.maps.daum-img.net/map/image/G03/i/1.20/L3/1969/862.png
 */

OpenLayers.Layer.DaumRoadView = OpenLayers.Class(OpenLayers.Layer.XYZ, {

    name: "Daum Street Map",
    url: [
        "http://t0.maps.daum-img.net/map/image/G03/t/2.00/L${z}/${y}/${x}.png",
        "http://t1.maps.daum-img.net/map/image/G03/t/2.00/L${z}/${y}/${x}.png",
        "http://t2.maps.daum-img.net/map/image/G03/t/2.00/L${z}/${y}/${x}.png",
        "http://t3.maps.daum-img.net/map/image/G03/t/2.00/L${z}/${y}/${x}.png",
    ],
	resolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25],
	attribution: '<a target="_blank" href="http://local.daum.net/map/index.jsp" '
		+ 'style="float: left; width: 38px; height: 17px; cursor: pointer; background-image: url(http://i1.daumcdn.net/localimg/localimages/07/2008/map/n_local_img_03_b.png); background-repeat: no-repeat no-repeat; " '
		+ 'title="Daum 지도로 보시려면 클릭하세요."></a>' 
		+ 'ⓒ 2013 Daum',
	sphericalMercator: false,
	buffer: 1,
	numZoomLevels: 14,
	minResolution: 0.25,
	maxResolution: 2048,
	units: "m",
	transitionEffect : "resize",
	isBaseLayer : false,
	projection: new OpenLayers.Projection("EPSG:5181"),
	displayOutsideMaxExtent: true,
	maxExtent: new OpenLayers.Bounds(-30000, -60000, 494288, 988576),
    initialize: function(name, options) {
		if (!options) options = {resolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]};
		else if (!options.resolutions) options.resolutions = [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25];
        var newArgs = [name, null, options];
        OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
    },
    clone: function(obj) {
        if (obj == null) {
            obj = new OpenLayers.Layer.DaumLoadView(
                this.name, this.getOptions());
        }
        obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
        return obj;
    },

	getXYZ: function(bounds) {
        var res = this.getServerResolution();
        var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
        var y = Math.round((bounds.bottom - this.maxExtent.bottom) / (res * this.tileSize.h));
        var z = this.numZoomLevels - this.getServerZoom();

        if (this.wrapDateLine) {
            var limit = Math.pow(2, z);
            x = ((x % limit) + limit) % limit;
        }

        return {'x': x, 'y': y, 'z': z};
    },
	
    CLASS_NAME: "OpenLayers.Layer.DaumRoadView"
});
