
(function() {
    var singleFile = (typeof GMap == "object" && GMap.singleFile);
    
    var scriptName = (!singleFile) ? "extLib/ginno/gmap/GMap.js" : "GMap.js";
    var jsFiles = window.GMap;

    window.GMap = {
        _getScriptLocation: (function() {
            var r = new RegExp("(^|(.*?\\/))(" + scriptName + ")(\\?|$)"),
                s = document.getElementsByTagName('script'),
                src, m, l = "";
            for(var i=0, len=s.length; i<len; i++) {
                src = s[i].getAttribute('src');
                if(src) {
                    m = src.match(r);
                    if(m) {
                        l = m[1];
                        break;
                    }
                }
            }
            return (function() { return l; });
        })()
    };

    if(!singleFile) {
        if (!jsFiles) {
            jsFiles = [
				"GMap.js",
				"GIndexMap.js",
				"GMapUtil.js",
				"gformat/GSLD.js",
				"gformat/gsld/v1_1.js",
				"gformat/gsld/v1_1_0.js",
				"GRequest.js",
				"gbasetypes/GLonLat.js",
				"gbasetypes/GBounds.js",
				"gbasetypes/GPixel.js",
				"gbasetypes/GSize.js",
				"gtile/image/IFrame.js",
				"glayer/GWMS.js",
				"glayer/GWMSPost.js",
				"glayer/GTileCache.js",
				"glayer/GVector.js",
				"gcontrol/GDrag.js",
				"gcontrol/GZoomIn.js",
				"gcontrol/GZoomOut.js",
				"gcontrol/GMeasure.js",
				"gcontrol/GNavigationHistory.js",
				"gcontrol/GZoomBoxIndex.js",
				"gcontrol/GDrawFeature.js",
				"gcontrol/GPanZoomBar.js",
				"gcontrol/GSelectFeature.js",
				"gcontrol/GModifyFeature.js",
				"gcontrol/GGetFeature.js",
				"gcontrol/GAcss.js",
				"gcontrol/GAlis.js",
				"gcontrol/GProfile.js",
				"ghandler/GBox.js",
				"ghandler/GPath.js",
				"ghandler/GPathMeasure.js",
				"ghandler/GPolygonMeasure.js",
				"ghandler/GPoint.js",
				"ghandler/GPolygon.js",
				"ghandler/GPolygonDraw.js",
				"ghandler/GRegularPolygonDraw.js",
				"ghandler/GRegularPolygonDrawAttr.js",
				"gpopup/GPopup.js",
				"gtool/GDrawTool.js",
				"gtool/GSaveTool.js",
				"gtool/GLengendTool.js",
				"gtool/GTMapLayerTool.js",
				"gtool/GSLDTool.js",
				"gmashup/util/GMahsupUtil.js",
				"gmashup/GDaumMap.js"
            ]; // etc.
        }

        // use "parser-inserted scripts" for guaranteed execution order
        // http://hsivonen.iki.fi/script-execution/
        var scriptTags = new Array(jsFiles.length);
        var host = GMap._getScriptLocation() + "extLib/ginno/gmap/lib/";
        for (var i=0, len=jsFiles.length; i<len; i++) {
            scriptTags[i] = "<script src='" + host + jsFiles[i] +
                                   "'></script>"; 
        }
        if (scriptTags.length > 0) {
            document.write(scriptTags.join(""));
        }
    }
})();