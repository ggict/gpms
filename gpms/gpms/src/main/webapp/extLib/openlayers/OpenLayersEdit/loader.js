(function () {

    var scripts = document.getElementsByTagName("script");
    var src = scripts[scripts.length - 1].src;
    var path = "/extLib/openlayers/OpenLayersEdit/";

    var files = [
        'compat.js',
        'iefixes.js',
        'Editor.js',
        'Editor/Control/CleanFeature.js',
        'Editor/Control/ContextMenu.js',
        'Editor/Control/DragFeature.js',
        'Editor/Control/DeleteFeature.js',
        'Editor/Control/DeleteAllFeatures.js',
        'Editor/Control/Dialog.js',
        'Editor/Control/DrawHole.js',
        'Editor/Control/DrawPolygon.js',
        'Editor/Control/DrawPath.js',
        'Editor/Control/DrawPoint.js',
        'Editor/Control/DrawText.js',
        'Editor/Control/DrawRegular.js',
        'Editor/Control/EditorPanel.js',
        'Editor/Control/ImportFeature.js',
        'Editor/Control/LayerSettings.js',
        'Editor/Control/MergeFeature.js',
        'Editor/Control/TransformFeature.js',
        'Editor/Control/FixedAngleDrawing.js',
        'Editor/Layer.js',
        'Editor/Layer/Snapping.js',
        'Editor/Control/SnappingSettings.js',
        'Editor/Control/DownloadFeature.js',
        'Editor/Control/UploadFeature.js',
        'Editor/Control/SplitFeature.js',
        'Editor/Control/UndoRedo.js',
        'Editor/Control/CADTools.js',
        'Editor/Control/ParallelDrawing.js',
        'OpenLayersEditCustom.js' //Customized File
    ];

    // Load translations if HTML page defines a language
    language = document.documentElement.getAttribute('lang') ? 
        document.documentElement.getAttribute('lang') : 'en';
    files.unshift('Editor/Lang/en.js');
    if (OpenLayers.Lang[language] === undefined) {
        OpenLayers.Lang[language] = {};
    }

    var tags = new Array(files.length);

    var el = document.getElementsByTagName("head").length ?
            document.getElementsByTagName("head")[0] :
            document.body;
    var scriptFullPath;
    
    for (var i = 0, len = files.length; i < len; i++) {
    	scriptFullPath = path + files[i];
    	if (i == (len-1))
    		loadedJavascript(scriptFullPath,fn_set_loadScript,"euc-kr");
    	else
    		loadJavascript(scriptFullPath,"euc-kr");
    		
    	/*var scriptElements = document.createElement("script");
    	scriptElements.type = "text/javascript";
    	scriptElements.src = path + files[i];
    	el.appendChild(scriptElements);*/
        //tags[i] = "<script src='" + path + files[i] + "'></script>";
    }
    
    function loadJavascript(url, charset) {
        //console.log('loadJavascript');
        var scriptElements= document.createElement('script');
        scriptElements.type= 'text/javascript';
        if (charset != null) {
        	scriptElements.charset = "euc-kr";
        }
        var loaded = false;
        scriptElements.onreadystatechange= function () {
            if (this.readyState == 'loaded' || this.readyState == 'complete') {
                console.log('loaded || complete');
                if (loaded) {
                    return;
                }
                loaded = true;
            }
            else{

                console.log('this.readyState => ' + this.readyState);
            }
        }
        scriptElements.src = url;
        el.appendChild(scriptElements);
    }
    
    function loadedJavascript(url, callback, charset) {
        //console.log('loadedJavascript===========> load complete!!');
        //var head= document.getElementsByTagName('head')[0];
        var scriptElements= document.createElement('script');
        scriptElements.type= 'text/javascript';
        if (charset != null) {
        	scriptElements.charset = "euc-kr";
        }
        var loaded = false;
        scriptElements.onreadystatechange= function () {
            if (this.readyState == 'loaded' || this.readyState == 'complete') {
                if (loaded) {
                    return;
                }
                loaded = true;
                callback();
            }
        }
        scriptElements.onload = function () {
            callback();
        }
        scriptElements.src = url;
        el.appendChild(scriptElements);
    }
    
    function fn_set_loadScript(){
    	console.log('load complete!!');
    }

})();
