GProtocolWFS_v1_1_0 = OpenLayers.Class(OpenLayers.Protocol.WFS.v1_1_0,{
	read: function(options) {
        OpenLayers.Protocol.prototype.read.apply(this, arguments);
        options = OpenLayers.Util.extend({}, options);
        OpenLayers.Util.applyDefaults(options, this.options || {});
        var response = new OpenLayers.Protocol.Response({requestType: "read"});

        var data = OpenLayers.Format.XML.prototype.write.apply(
            this.format, [this.format.writeNode("wfs:GetFeature", options)]
        );

        response.priv = GRequest.POST({
            url: options.url,
            callback: this.createCallback(this.handleRead, response, options),
            params: options.params,
            headers: options.headers,
            data: data,
        });

        return response;
    },
	CLASS_NAME: "GProtocolWFS_v1_1_0"
});