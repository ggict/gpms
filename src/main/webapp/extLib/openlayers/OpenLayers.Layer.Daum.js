/* Copyright (c) 2006-2012 by OpenLayers Contributors (see authors.txt for 
 * full list of contributors). Published under the 2-clause BSD license.
 * See license.txt in the OpenLayers distribution or repository for the
 * full text of the license. */

/**
 * @requires OpenLayers/Layer/XYZ.js
 */

OpenLayers.Layer.Daum = OpenLayers.Class(OpenLayers.Layer.XYZ, {

    name: "DaumMap",
    url: [
		"http://i0.maps.daum-img.net/map/image/G03/i/2015eight/L${z}/${y}/${x}.png",
		"http://i1.maps.daum-img.net/map/image/G03/i/2015eight/L${z}/${y}/${x}.png",
		"http://i2.maps.daum-img.net/map/image/G03/i/2015eight/L${z}/${y}/${x}.png",
		"http://i3.maps.daum-img.net/map/image/G03/i/2015eight/L${z}/${y}/${x}.png"
    ],
	sphericalMercator: false,
	transitionEffect: "resize",
	buffer: 1,
	displayOutsideMaxExtent: true,
    initialize: function(name, options) {
		if (!options) options = {resolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]};
		else if (!options.resolutions) options.resolutions = [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25];
        var newArgs = [name, null, options];
        OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
    },
    clone: function(obj) {
        if (obj == null) {
            obj = new OpenLayers.Layer.Daum(
                this.name, this.getOptions());
        }
        obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
        return obj;
    },

	getXYZ: function(bounds) {
        var res = this.getServerResolution();
        var x = Math.round((bounds.left - this.maxExtent.left) /
            (res * this.tileSize.w));
        var y = Math.round((bounds.bottom - this.maxExtent.bottom) /
            (res * this.tileSize.h));
        var z = 14 - this.getServerZoom();

        if (this.wrapDateLine) {
            var limit = Math.pow(2, z);
            x = ((x % limit) + limit) % limit;
        }

        return {'x': x, 'y': y, 'z': z};
    },
	
    CLASS_NAME: "OpenLayers.Layer.Daum"
});
